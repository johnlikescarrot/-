#!/usr/bin/env python3
"""
Surgical Follow-up Probes:
  1. Unpack assets/android.1.zip (React Native main.jsbundle) & assets/Griver/*.amr -> /tmp/zalopay_re/js_extract/
  2. Map System.loadLibrary(...) and native JNI class references across all 20 DEX files
  3. Compare liblytcuhe.so vs librcmefqonnx.so & profile sections/entropy of libtaibmcf.so, libfgcpkjvqe.so, liba97d.so, libljsfrcwmw.so, libohxbee.so
  4. Inspect TFLite model metadata, NCNN model layers, Visa CBP / AppsFlyer / bshield / trueidlib assets, and baseline.prof
"""

import hashlib
import io
import json
import math
import re
import struct
import tarfile
import zipfile
from collections import Counter, defaultdict
from pathlib import Path

from loguru import logger
logger.disable("androguard")

from androguard.core.dex import DEX
from elftools.elf.elffile import ELFFile

REPO_ROOT = Path("/home/user/-")
XAPK_DIR = Path("/tmp/zalopay_re/xapk")
EXTRACTED_DIR = Path("/tmp/zalopay_re/extracted")
JS_EXTRACT = Path("/tmp/zalopay_re/js_extract")
OUT_FILE = REPO_ROOT / "research/evidence/deep/06_surgical_probes.json"


def shannon(b: bytes) -> float:
    if not b:
        return 0.0
    c = Counter(b)
    n = len(b)
    return round(-sum((v / n) * math.log2(v / n) for v in c.values()), 4)


def unpack_js_and_griver():
    base = EXTRACTED_DIR / "base"
    rn_dir = JS_EXTRACT / "react_native"
    griver_dir = JS_EXTRACT / "griver_amr"
    bridges_dir = JS_EXTRACT / "bridges"
    rn_dir.mkdir(parents=True, exist_ok=True)
    griver_dir.mkdir(parents=True, exist_ok=True)
    bridges_dir.mkdir(parents=True, exist_ok=True)

    # 1. React Native bundle (assets/android.1.zip)
    with zipfile.ZipFile(base / "assets/android.1.zip") as z:
        z.extractall(rn_dir)
    jsb_path = rn_dir / "main.jsbundle"
    jsb_text = jsb_path.read_text("utf-8", "replace")
    # Extract React Native module markers / Hermes vs JSC / __d() Metro module count
    metro_defs = len(re.findall(r"__d\s*\(", jsb_text))
    rn_pkg_hints = sorted(set(re.findall(r"node_modules/([@a-zA-Z0-9_.-]+(?:/[a-zA-Z0-9_.-]+)?)", jsb_text)))[:120]
    rn_zlp_screens = sorted(set(re.findall(r"zalopay[A-Za-z0-9_/-]{3,60}", jsb_text, re.I)))[:80]
    rn_header = jsb_text[:400]

    # 2. Griver .amr bundles
    amr_summaries = {}
    for amr in sorted((base / "assets/Griver").glob("*.amr")):
        sub = griver_dir / amr.stem
        sub.mkdir(parents=True, exist_ok=True)
        with zipfile.ZipFile(amr) as az:
            az.extractall(sub)
        cert_info = {}
        manifest_txt = ""
        tar_files = []
        if (sub / "CERT.json").exists():
            cert_raw = json.loads((sub / "CERT.json").read_text("utf-8"))
            cert_info = {"files_signed": sorted(cert_raw.keys()), "count": len(cert_raw)}
        if (sub / "Manifest.xml").exists():
            manifest_txt = (sub / "Manifest.xml").read_text("utf-8", "replace")
        for tf_path in sub.glob("*.tar"):
            tar_out = sub / "tar_content"
            tar_out.mkdir(exist_ok=True)
            with tarfile.open(tf_path, "r:*") as tf:
                members = tf.getmembers()
                for m in members:
                    tar_files.append({"name": m.name, "size": m.size})
                tf.extractall(tar_out)
        amr_summaries[amr.name] = {
            "cert_files": cert_info,
            "manifest_xml": manifest_txt,
            "tar_members": tar_files[:40],
            "tar_member_count": len(tar_files),
        }

    # 3. Copy standalone JS bridges for REA JS analysis
    for jsf in sorted((base / "assets").rglob("*.js")):
        rel = jsf.relative_to(base / "assets")
        dest = bridges_dir / rel
        dest.parent.mkdir(parents=True, exist_ok=True)
        dest.write_bytes(jsf.read_bytes())

    return {
        "react_native_bundle": {
            "main_jsbundle_bytes": jsb_path.stat().st_size,
            "sha256": hashlib.sha256(jsb_path.read_bytes()).hexdigest(),
            "metro_module_define_count": metro_defs,
            "header_preview": rn_header,
            "node_modules_hints_sample": rn_pkg_hints,
        },
        "griver_amrs_unpacked": amr_summaries,
    }


def probe_native_and_dex_links():
    so_dir = EXTRACTED_DIR / "armeabi-v7a/lib/armeabi-v7a"
    # Compare liblytcuhe.so vs librcmefqonnx.so & inspect interesting .so section layouts
    targets = [
        "libtaibmcf.so", "libfgcpkjvqe.so", "liba97d.so", "libljsfrcwmw.so",
        "liblytcuhe.so", "libohxbee.so", "librcmefqonnx.so", "libsecurity_lite.so",
        "libtrueidlib.so", "libVFaceLib.so", "librive-android.so", "libsqlcipher.so",
    ]
    so_profiles = {}
    for name in targets:
        p = so_dir / name
        raw = p.read_bytes()
        with open(p, "rb") as f:
            elf = ELFFile(f)
            secs = []
            for s in elf.iter_sections():
                sd = s.data() if s.header["sh_type"] != "SHT_NOBITS" else b""
                secs.append({
                    "name": s.name,
                    "type": s.header["sh_type"],
                    "size": s.header["sh_size"],
                    "entropy": shannon(sd) if sd else 0.0,
                })
        so_profiles[name] = {
            "bytes": len(raw),
            "sha256": hashlib.sha256(raw).hexdigest(),
            "sections": secs,
        }

    # Check if liblytcuhe.so and librcmefqonnx.so differ and by how many bytes
    b1 = (so_dir / "liblytcuhe.so").read_bytes()
    b2 = (so_dir / "librcmefqonnx.so").read_bytes()
    diff_bytes = sum(1 for x, y in zip(b1, b2) if x != y) if len(b1) == len(b2) else -1

    # Search DEX string pools and loadLibrary callers for obfuscated library names
    lib_stems = [
        "taibmcf", "fgcpkjvqe", "a97d", "ljsfrcwmw", "lytcuhe", "ohxbee",
        "rcmefqonnx", "security_lite", "trueidlib", "VFaceLib", "sqlcipher", "picodet_ncnn", "rive-android",
    ]
    dex_lib_hits = defaultdict(list)
    with zipfile.ZipFile(XAPK_DIR / "vn.com.vng.zalopay.apk") as z:
        for dex_name in sorted(n for n in z.namelist() if n.endswith(".dex") and not n.startswith("assets/")):
            d = DEX(z.read(dex_name))
            strs = d.get_strings()
            str_set = set(strs)
            for stem in lib_stems:
                if stem in str_set or f"lib{stem}.so" in str_set:
                    dex_lib_hits[stem].append(dex_name)
            # Also find which classes declare native methods matching JNI or loadLibrary
            for cls in d.get_classes():
                cname = cls.get_name()
                for m in cls.get_methods():
                    if m.get_access_flags() & 0x0100:  # ACC_NATIVE
                        pass
                # Check if class name hints at obfuscated shields
                if any(k in cname.lower() for k in ("taibmcf", "fgcpkjvqe", "xcmzqsawpqoefcds", "jwbmqnsll", "bshield", "securityguard", "trueid", "faceauth")):
                    dex_lib_hits[f"class:{cname}"] = dex_lib_hits.get(f"class:{cname}", []) + [dex_name]

    # Collect classes that have ACC_NATIVE methods (top 30 classes by native method count!)
    native_method_classes = Counter()
    with zipfile.ZipFile(XAPK_DIR / "vn.com.vng.zalopay.apk") as z:
        for dex_name in sorted(n for n in z.namelist() if n.endswith(".dex") and not n.startswith("assets/")):
            d = DEX(z.read(dex_name))
            for m in d.get_encoded_methods():
                if m.get_access_flags() & 0x0100:  # ACC_NATIVE
                    native_method_classes[m.get_class_name()] += 1

    return {
        "so_section_profiles": so_profiles,
        "liblytcuhe_vs_librcmefqonnx_diff_bytes": diff_bytes,
        "dex_library_string_hits": dict(dex_lib_hits),
        "top_classes_with_native_methods": dict(native_method_classes.most_common(45)),
        "total_classes_with_native_methods": len(native_method_classes),
        "total_native_methods": sum(native_method_classes.values()),
    }


def probe_special_assets():
    base = EXTRACTED_DIR / "base"
    info = {}
    # 1. assets/trueidlib.so (392 bytes), assets/bshield.dat (929 bytes), assets/fgcpkjvqe.dat
    for rel in ("assets/trueidlib.so", "assets/bshield.dat", "assets/fgcpkjvqe.dat", "assets/config/public_key.txt", "assets/config/signature.txt"):
        p = base / rel
        raw = p.read_bytes()
        info[rel] = {
            "bytes": len(raw),
            "sha256": hashlib.sha256(raw).hexdigest(),
            "entropy": shannon(raw),
            "head_hex": raw[:48].hex(),
            "ascii_preview": raw[:120].decode("ascii", "replace") if rel.endswith(".txt") else None,
        }
    # 2. TFLite custom model object_labeler.tflite
    tflite = (base / "assets/custom_models/object_labeler.tflite").read_bytes()
    # Look for embedded label strings or TFL3 identifier
    info["tflite_object_labeler"] = {
        "bytes": len(tflite),
        "magic_offset4": tflite[4:8].decode("ascii", "replace"),
        "embedded_strings_sample": [s.decode("ascii", "ignore") for s in re.findall(rb"[A-Za-z0-9_ .\-]{4,40}", tflite)][:30],
    }
    # 3. Visa CBP & AppsFlyer obfuscated internal assets
    for group, glob_pat in [("visa_cbp", "assets/com/visa/cbp/*"), ("appsflyer_internal", "assets/com/appsflyer/internal/*")]:
        items = []
        for p in sorted(base.glob(glob_pat)):
            b = p.read_bytes()
            items.append({
                "name": p.name,
                "bytes": len(b),
                "entropy": shannon(b),
                "magic_hex": b[:16].hex(),
            })
        info[group] = items
    # 4. Baseline ART profile
    prof = base / "assets/dexopt/baseline.prof"
    if prof.exists():
        pb = prof.read_bytes()
        info["baseline_prof"] = {"bytes": len(pb), "magic_ascii": pb[:8].decode("ascii", "replace"), "magic_hex": pb[:16].hex()}
    return info


def main():
    res = {
        "js_and_griver": unpack_js_and_griver(),
        "native_and_dex_links": probe_native_and_dex_links(),
        "special_assets": probe_special_assets(),
    }
    OUT_FILE.write_text(json.dumps(res, indent=2, ensure_ascii=False))
    print("Wrote", OUT_FILE)
    print("React Native main.jsbundle:", res["js_and_griver"]["react_native_bundle"]["main_jsbundle_bytes"], "bytes, Metro modules:", res["js_and_griver"]["react_native_bundle"]["metro_module_define_count"])
    print("liblytcuhe vs librcmefqonnx diff bytes:", res["native_and_dex_links"]["liblytcuhe_vs_librcmefqonnx_diff_bytes"])
    print("Total native methods in DEX:", res["native_and_dex_links"]["total_native_methods"], "across", res["native_and_dex_links"]["total_classes_with_native_methods"], "classes")
    print("Top native-method classes:", list(res["native_and_dex_links"]["top_classes_with_native_methods"].items())[:20])
    print("DEX library hits:", res["native_and_dex_links"]["dex_library_string_hits"])


if __name__ == "__main__":
    main()
