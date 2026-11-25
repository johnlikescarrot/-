#!/usr/bin/env python3
"""Deep ELF32 native library dissection (LIEF + pyelftools) correlated with morluto/rea evidence & DEX JNI methods."""

import hashlib
import json
import math
import re
from collections import Counter, defaultdict
from pathlib import Path

import lief
from elftools.elf.elffile import ELFFile

REPO_ROOT = Path.cwd()
SO_DIR = Path("/tmp/zalopay_corpus/apks/config.armeabi_v7a/lib/armeabi-v7a")
ASSETS_DIR = Path("/tmp/zalopay_corpus/apks/vn.com.vng.zalopay/assets")
OUT_DIR = REPO_ROOT / "data"

rea_native = json.loads((REPO_ROOT / "evidence/rea_native_audit.json").read_text(encoding="utf-8"))
rea_by_name = {lib["name"]: lib for lib in rea_native["libraries"]}
dex_data = json.loads((OUT_DIR / "dex_ecology.json").read_text(encoding="utf-8"))


def shannon_entropy(data: bytes) -> float:
    n = len(data)
    if n == 0:
        return 0.0
    counts = Counter(data)
    ent = 0.0
    for c in counts.values():
        p = c / n
        ent -= p * math.log2(p)
    return round(ent, 4)


def classify_so_vendor(name: str, exports: list[str], needed: list[str], comment_str: str) -> str:
    if name.startswith("libcrashlytics"):
        return "Crash Reporting (Firebase Crashlytics)"
    if name in ("libmlkit_google_ocr_pipeline.so", "libmlkitcommonpipeline.so", "libface_detector_v2_jni.so", "libbarhopper_v3.so", "libimage_processing_util_jni.so"):
        return "Google ML Kit / Vision / Barcode Pipeline"
    if name in ("libVFaceLib.so", "libtrueidlib.so", "libpicodet_ncnn.so"):
        return "On-Device Biometric eKYC & NCNN Detection (TrueID / VFace / PicoDet)"
    if name in ("libimagepipeline.so", "libnative-filters.so", "libnative-imagetranscoder.so"):
        return "Fresco Native Image Pipeline (Meta)"
    if name == "librive-android.so":
        return "Rive Vector Animation Engine"
    if name == "libsqlcipher.so":
        return "SQLCipher Encrypted Local Database"
    if name == "libc++_shared.so":
        return "Android NDK C++ Shared Runtime (LLVM libc++)"
    if name == "libandroidx.graphics.path.so":
        return "AndroidX Jetpack Graphics Path"
    if name == "libsecurity_lite.so":
        return "Alipay / IAP Security Lite SDK"
    if name in ("libtaibmcf.so", "libfgcpkjvqe.so", "liba97d.so", "libljsfrcwmw.so", "liblytcuhe.so", "libohxbee.so", "librcmefqonnx.so"):
        return "Randomized-Name / Shielded / Obfuscated Native Module"
    return "Other Native Module"


def jni_java_symbol_to_class(sym_name: str) -> str | None:
    # Java_pkg_cls_method -> pkg.cls (best-effort structural parsing without assuming inner $)
    if not sym_name.startswith("Java_"):
        return None
    body = sym_name[5:]
    # Strip JNI overload suffix __...
    body = body.split("__", 1)[0]
    parts = body.replace("_1", "\x00").split("_")
    if len(parts) < 2:
        return None
    # Class + package is everything except last segment (method)
    return ".".join(p.replace("\x00", "_") for p in parts[:-1])


# Build lookup of DEX native-declaring classes (normalized for JNI matching)
dex_native_classes = dex_data["native_declarations_by_class"]
dex_norm_lookup = {}
for cls_name, meths in dex_native_classes.items():
    norm = cls_name.replace("$", "_00024")
    dex_norm_lookup[norm] = (cls_name, meths)
    dex_norm_lookup[cls_name] = (cls_name, meths)

libraries_out = []
jni_static_matches = []
section_entropy_summary = []

for so_path in sorted(SO_DIR.glob("*.so")):
    name = so_path.name
    raw = so_path.read_bytes()
    sha = hashlib.sha256(raw).hexdigest()
    rea_entry = rea_by_name[name]

    binary = lief.parse(raw)
    elffile = ELFFile(so_path.open("rb"))

    sections = []
    comment_text = ""
    non_standard_sections = []
    std_prefixes = (".", "")
    std_names = {
        "", ".interp", ".note.android.ident", ".note.gnu.build-id", ".dynsym", ".dynstr",
        ".gnu.version", ".gnu.version_r", ".gnu.hash", ".hash", ".rel.dyn", ".rel.plt",
        ".plt", ".text", ".ARM.extab", ".ARM.exidx", ".rodata", ".eh_frame", ".eh_frame_hdr",
        ".fini_array", ".init_array", ".data.rel.ro", ".dynamic", ".got", ".data", ".bss",
        ".comment", ".shstrtab", ".symtab", ".strtab", ".debug_frame", ".note.ABI-tag",
        ".preinit_array", ".tdata", ".tbss", ".gnu_debuglink",
    }

    text_ent = None
    rodata_ent = None
    data_ent = None

    for sec in elffile.iter_sections():
        sname = sec.name
        ssize = sec["sh_size"]
        sflags = sec["sh_flags"]
        sdata = b""
        if sec["sh_type"] != "SHT_NOBITS" and ssize > 0:
            try:
                sdata = sec.data()
            except Exception:
                sdata = b""
        sent = shannon_entropy(sdata) if sdata else 0.0
        if sname == ".text":
            text_ent = sent
        elif sname == ".rodata":
            rodata_ent = sent
        elif sname == ".data":
            data_ent = sent
        elif sname == ".comment" and sdata:
            comment_text = " | ".join(
                s.decode("utf-8", errors="replace").strip()
                for s in sdata.split(b"\x00")
                if s.strip()
            )
        if sname not in std_names and not sname.startswith(".note") and not sname.startswith(".rel") and not sname.startswith(".gnu"):
            non_standard_sections.append({"name": sname, "size": ssize, "entropy": sent})

        sections.append({
            "name": sname,
            "type": str(sec["sh_type"]),
            "size": ssize,
            "offset": sec["sh_offset"],
            "flags": sflags,
            "entropy": sent,
        })

    # Exported symbols & JNI static symbol correlation
    dyn_exports = []
    if binary is not None:
        for sym in binary.exported_symbols:
            if sym.name:
                dyn_exports.append(sym.name)

    has_jni_onload = "JNI_OnLoad" in dyn_exports
    java_exports = [s for s in dyn_exports if s.startswith("Java_")]

    matched_dex_classes = set()
    for jsym in java_exports:
        # Match against DEX native declarations
        best_match = None
        for dex_cls in dex_native_classes:
            # JNI mangling replaces _ with _1 and . with _ and $ with _00024
            mangled_prefix = "Java_" + dex_cls.replace("_", "_1").replace(".", "_").replace("$", "_00024") + "_"
            if jsym.startswith(mangled_prefix):
                best_match = dex_cls
                break
        if best_match:
            matched_dex_classes.add(best_match)
            jni_static_matches.append({
                "library": name,
                "symbol": jsym,
                "dex_class": best_match,
            })

    # Inspect companion asset relationships
    companions = []
    stem = name[3:-3] if name.startswith("lib") and name.endswith(".so") else name
    for cand in sorted(ASSETS_DIR.iterdir()):
        if stem.lower() in cand.name.lower() or (name == "libpicodet_ncnn.so" and "ncnn" in cand.name.lower()):
            companions.append(str(cand.relative_to(ASSETS_DIR.parent)))

    # Check if randomized name & inspect exported sample names
    export_sample = [s for s in dyn_exports if s not in ("JNI_OnLoad", "JNI_OnUnload")][:15]
    # Check Android NDK / Clang version in comment or notes
    notes_list = rea_entry.get("notes", [])
    ndk_info = None
    build_id = None
    for n in notes_list:
        if n.get("type_name") == "NT_GNU_BUILD_ID":
            build_id = n.get("build_id") or n.get("description_hex")
        if "android" in str(n).lower():
            ndk_info = n.get("parsed") or n

    rec = {
        "name": name,
        "sha256": sha,
        "bytes": len(raw),
        "vendor_category": classify_so_vendor(name, dyn_exports, rea_entry["dependencies"]["needed"], comment_text),
        "hardening": rea_entry["hardening"],
        "needed_libs": rea_entry["dependencies"]["needed"],
        "import_count": rea_entry["dependencies"]["import_count"],
        "export_count": len(dyn_exports),
        "has_jni_onload": has_jni_onload,
        "static_java_jni_export_count": len(java_exports),
        "matched_dex_classes": sorted(matched_dex_classes),
        "initializer_count": rea_entry["initializers"]["initializer_count"],
        "finalizer_count": rea_entry["initializers"]["finalizer_count"],
        "unwind_range_count": rea_entry["unwind_ranges_summary"]["range_count"],
        "file_entropy": rea_entry["byte_entropy"]["overall"].get("shannon_entropy") if isinstance(rea_entry["byte_entropy"]["overall"], dict) else shannon_entropy(raw),
        "text_entropy": text_ent,
        "rodata_entropy": rodata_ent,
        "data_entropy": data_ent,
        "build_id": build_id,
        "comment": comment_text[:300] if comment_text else None,
        "non_standard_sections": non_standard_sections,
        "companion_assets": companions,
        "sample_exports": export_sample,
        "major_sections": [s for s in sections if s["size"] > 0],
    }
    libraries_out.append(rec)
    print(f"  {name:34s} | {len(raw):10,d}B | ent={rec['file_entropy']:.3f} .text={text_ent or 0:.3f} .ro={rodata_ent or 0:.3f} | JNI_OnLoad={str(has_jni_onload):5s} Java_*={len(java_exports):3d} matched_cls={len(matched_dex_classes):2d} | {rec['vendor_category']}")

# Deep comparison of liblytcuhe.so vs librcmefqonnx.so
lyt = next(x for x in libraries_out if x["name"] == "liblytcuhe.so")
rcm = next(x for x in libraries_out if x["name"] == "librcmefqonnx.so")
lyt_b = (SO_DIR / "liblytcuhe.so").read_bytes()
rcm_b = (SO_DIR / "librcmefqonnx.so").read_bytes()
diff_offsets = [i for i in range(min(len(lyt_b), len(rcm_b))) if lyt_b[i] != rcm_b[i]]
lyt_vs_rcm = {
    "both_bytes": len(lyt_b),
    "differing_byte_count": len(diff_offsets),
    "similarity_ratio": round(1.0 - len(diff_offsets) / len(lyt_b), 6),
    "first_20_diff_offsets": diff_offsets[:20],
    "lyt_build_id": lyt["build_id"],
    "rcm_build_id": rcm["build_id"],
    "lyt_sample_exports": lyt["sample_exports"][:10],
    "rcm_sample_exports": rcm["sample_exports"][:10],
}

# Summary of hardening & JNI linkage
hardening_counts = defaultdict(Counter)
for lib in libraries_out:
    for prop, st in lib["hardening"].items():
        hardening_counts[prop][st] += 1

matched_class_set = {m["dex_class"] for m in jni_static_matches}
unmatched_dex_native_classes = sorted(set(dex_native_classes.keys()) - matched_class_set)
unmatched_native_method_total = sum(len(dex_native_classes[c]) for c in unmatched_dex_native_classes)
matched_native_method_total = sum(len(dex_native_classes[c]) for c in matched_class_set)

out = {
    "totals": {
        "native_library_count": len(libraries_out),
        "total_bytes": sum(x["bytes"] for x in libraries_out),
        "libs_with_jni_onload": sum(1 for x in libraries_out if x["has_jni_onload"]),
        "total_static_java_jni_exports": sum(x["static_java_jni_export_count"] for x in libraries_out),
        "dex_native_classes_total": len(dex_native_classes),
        "dex_native_classes_statically_matched_to_so": len(matched_class_set),
        "dex_native_classes_unmatched_dynamic_or_external": len(unmatched_dex_native_classes),
        "dex_native_methods_statically_matched_classes": matched_native_method_total,
        "dex_native_methods_dynamic_registernatives_or_other": unmatched_native_method_total,
    },
    "hardening_summary": {k: dict(v) for k, v in hardening_counts.items()},
    "liblytcuhe_vs_librcmefqonnx_comparison": lyt_vs_rcm,
    "libraries": libraries_out,
    "jni_static_matches_count": len(jni_static_matches),
    "jni_static_matches_sample": jni_static_matches[:100],
    "top_unmatched_dynamic_jni_classes": [
        {"class": c, "method_count": len(dex_native_classes[c]), "dex": dex_native_classes[c][0]["dex"]}
        for c in sorted(unmatched_dex_native_classes, key=lambda c: len(dex_native_classes[c]), reverse=True)[:40]
    ],
}

(OUT_DIR / "native_elf_analysis.json").write_text(json.dumps(out, indent=2), encoding="utf-8")
print("\n=== NATIVE ELF & JNI CORRELATION SUMMARY ===")
print(json.dumps(out["totals"], indent=2))
print("liblytcuhe.so vs librcmefqonnx.so:", json.dumps(lyt_vs_rcm, indent=2))
print("Hardening counts:", json.dumps(out["hardening_summary"], indent=2))
