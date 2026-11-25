#!/usr/bin/env python3
"""
Comprehensive defensive binary architecture & compiler-hardening audit across
ALL native ELF (.so) shared libraries in Zalopay 11.17.0 using LIEF & pyelftools.
"""
import hashlib
import json
import math
import subprocess
from collections import Counter
from pathlib import Path

import lief
from elftools.elf.dynamic import DynamicSection
from elftools.elf.elffile import ELFFile
from elftools.elf.notes import iter_notes

ROOT = Path("/home/user/-")
SO_DIR = ROOT / "tmp_work/apks/config.armeabi_v7a/lib/armeabi-v7a"
OUT_PATH = ROOT / "data/native_elf_analysis.json"


def entropy(data: bytes) -> float:
    if not data:
        return 0.0
    c = Counter(data)
    n = len(data)
    return round(-sum((k / n) * math.log2(k / n) for k in c.values()), 4)


def categorize_library(name: str) -> str:
    n = name.lower()
    if any(k in n for k in ("mlkit", "barhopper", "face_detector", "picodet", "ncnn", "onnx", "vfacelib", "trueid")):
        return "On-Device AI / Computer Vision / eKYC"
    if any(k in n for k in ("bshield", "security", "taibmcf", "fgcpkjvqe", "a97d", "ljsfrcwmw", "lytcuhe", "ohxbee")):
        return "RASP / App Shielding & Obfuscated Native Modules"
    if "sqlcipher" in n:
        return "Encrypted Storage (SQLCipher)"
    if "crashlytics" in n:
        return "Native Crash Telemetry (Firebase Crashlytics)"
    if any(k in n for k in ("rive", "image", "native-filters", "graphics")):
        return "Graphics / Vector Animation / Media"
    if "c++_shared" in n:
        return "NDK C++ Standard Runtime"
    return "Other Native Module"


def audit_so(so_path: Path) -> dict:
    raw = so_path.read_bytes()
    sha = hashlib.sha256(raw).hexdigest()
    lief_bin = lief.parse(str(so_path))

    info = {
        "filename": so_path.name,
        "category": categorize_library(so_path.name),
        "size_bytes": len(raw),
        "sha256": sha,
        "whole_file_entropy": entropy(raw),
        "elf_valid": isinstance(lief_bin, lief.ELF.Binary),
        "architecture": None,
        "elf_type": None,
        "soname": None,
        "dt_needed": [],
        "runpath_rpath": [],
        "section_count": 0,
        "has_symtab": False,
        "dynsym_count": 0,
        "exported_symbols_count": 0,
        "imported_symbols_count": 0,
        "jni_exported_functions": 0,
        "has_jni_onload": False,
        "hardening": {
            "pie_et_dyn": False,
            "nx_non_exec_stack": False,
            "relro": "none",
            "stack_canary": False,
            "fortify_source": False,
            "stripped_symtab": True,
        },
        "compiler_comment": None,
        "ndk_note": None,
        "sections_entropy": {},
    }

    with so_path.open("rb") as f:
        ef = ELFFile(f)
        info["architecture"] = f"{ef.get_machine_arch()} ({ef.elfclass}-bit, { 'little' if ef.little_endian else 'big' })"
        info["elf_type"] = str(ef.header["e_type"])
        info["section_count"] = ef.num_sections()
        info["hardening"]["pie_et_dyn"] = ef.header["e_type"] == "ET_DYN"

        # Program headers: PT_GNU_STACK, PT_GNU_RELRO
        has_relro_seg = False
        has_exec_stack = True
        has_gnu_stack = False
        for seg in ef.iter_segments():
            p_type = seg["p_type"]
            if p_type == "PT_GNU_RELRO":
                has_relro_seg = True
            elif p_type == "PT_GNU_STACK":
                has_gnu_stack = True
                # PF_X == 1
                has_exec_stack = bool(seg["p_flags"] & 0x1)
        info["hardening"]["nx_non_exec_stack"] = has_gnu_stack and (not has_exec_stack)

        # Dynamic tags: BIND_NOW, NEEDED, SONAME
        has_bind_now = False
        for sec in ef.iter_sections():
            sname = sec.name
            if sname in (".text", ".rodata", ".data", ".bss"):
                try:
                    info["sections_entropy"][sname] = {
                        "size": sec["sh_size"],
                        "entropy": entropy(sec.data()) if sname != ".bss" else 0.0,
                    }
                except Exception:
                    pass
            if sname == ".symtab":
                info["has_symtab"] = True
                info["hardening"]["stripped_symtab"] = False
            elif sname == ".comment":
                txt = sec.data().replace(b"\x00", b" | ").decode("utf-8", "replace").strip(" |")
                info["compiler_comment"] = txt[:240]
            elif sname.startswith(".note.android"):
                try:
                    notes = []
                    for note in sec.iter_notes():
                        notes.append(f"{note['n_name']} type={note['n_type']} desc_len={len(note['n_desc'])}")
                    info["ndk_note"] = "; ".join(notes)
                except Exception:
                    pass
            elif isinstance(sec, DynamicSection):
                for tag in sec.iter_tags():
                    t = tag.entry.d_tag
                    if t == "DT_NEEDED":
                        info["dt_needed"].append(tag.needed)
                    elif t == "DT_SONAME":
                        info["soname"] = tag.soname
                    elif t in ("DT_RPATH", "DT_RUNPATH"):
                        info["runpath_rpath"].append(getattr(tag, "rpath", getattr(tag, "runpath", "")))
                    elif t == "DT_BIND_NOW":
                        has_bind_now = True
                    elif t == "DT_FLAGS" and (tag.entry.d_val & 0x8):  # DF_BIND_NOW
                        has_bind_now = True
                    elif t == "DT_FLAGS_1" and (tag.entry.d_val & 0x1):  # DF_1_NOW
                        has_bind_now = True

        if has_relro_seg and has_bind_now:
            info["hardening"]["relro"] = "full"
        elif has_relro_seg:
            info["hardening"]["relro"] = "partial"
        else:
            info["hardening"]["relro"] = "none"

    if lief_bin is not None:
        dynsyms = list(lief_bin.dynamic_symbols)
        info["dynsym_count"] = len(dynsyms)
        exp_cnt = 0
        imp_cnt = 0
        jni_cnt = 0
        for s in dynsyms:
            name = s.name or ""
            if s.imported:
                imp_cnt += 1
                if name in ("__stack_chk_fail", "__stack_chk_guard"):
                    info["hardening"]["stack_canary"] = True
                if name.endswith("_chk") and name.startswith("__"):
                    info["hardening"]["fortify_source"] = True
            elif s.exported:
                exp_cnt += 1
                if name.startswith("Java_"):
                    jni_cnt += 1
                if name == "JNI_OnLoad":
                    info["has_jni_onload"] = True
        info["exported_symbols_count"] = exp_cnt
        info["imported_symbols_count"] = imp_cnt
        info["jni_exported_functions"] = jni_cnt

    return info


def main():
    so_files = sorted(SO_DIR.glob("*.so"))
    results = [audit_so(p) for p in so_files]

    total = len(results)
    hardening_summary = {
        "total_elf_libraries": total,
        "total_bytes": sum(r["size_bytes"] for r in results),
        "pie_enabled_count": sum(1 for r in results if r["hardening"]["pie_et_dyn"]),
        "nx_enabled_count": sum(1 for r in results if r["hardening"]["nx_non_exec_stack"]),
        "full_relro_count": sum(1 for r in results if r["hardening"]["relro"] == "full"),
        "partial_relro_count": sum(1 for r in results if r["hardening"]["relro"] == "partial"),
        "no_relro_count": sum(1 for r in results if r["hardening"]["relro"] == "none"),
        "stack_canary_count": sum(1 for r in results if r["hardening"]["stack_canary"]),
        "fortify_source_count": sum(1 for r in results if r["hardening"]["fortify_source"]),
        "stripped_symtab_count": sum(1 for r in results if r["hardening"]["stripped_symtab"]),
        "jni_onload_count": sum(1 for r in results if r["has_jni_onload"]),
        "dt_needed_frequency": dict(Counter(dep for r in results for dep in r["dt_needed"]).most_common()),
        "category_byte_totals": {},
    }
    for r in results:
        c = r["category"]
        hardening_summary["category_byte_totals"][c] = hardening_summary["category_byte_totals"].get(c, 0) + r["size_bytes"]

    # Check assets/trueidlib.so stub as well
    asset_stub = ROOT / "tmp_work/apks/vn.com.vng.zalopay/assets/trueidlib.so"
    stub_raw = asset_stub.read_bytes()

    out = {
        "summary": hardening_summary,
        "libraries": results,
        "assets_trueidlib_so_anomaly": {
            "path": "assets/trueidlib.so",
            "size_bytes": len(stub_raw),
            "sha256": hashlib.sha256(stub_raw).hexdigest(),
            "entropy": entropy(stub_raw),
            "is_elf": stub_raw.startswith(b"\x7fELF"),
            "note": "Non-ELF opaque/encrypted payload disguised or companion-named alongside lib/armeabi-v7a/libtrueidlib.so",
        },
    }
    OUT_PATH.write_text(json.dumps(out, indent=2, ensure_ascii=False))
    print(f"Wrote {OUT_PATH}")
    print(json.dumps(hardening_summary, indent=2))


if __name__ == "__main__":
    main()
