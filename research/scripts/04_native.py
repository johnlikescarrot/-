#!/usr/bin/env python3
"""Stage 4 — native library anatomy (ELF), obfuscation and hardening signals."""
from __future__ import annotations

import json
import math
import re
from collections import Counter
from pathlib import Path

from elftools.elf.elffile import ELFFile

ROOT = Path(__file__).resolve().parents[2]
DATA = ROOT / "research" / "data"
LIBDIR = ROOT / "work" / "libs" / "lib" / "armeabi-v7a"

# Known-purpose map built from exported symbol / asset correlation.
PURPOSE = {
    "libtaibmcf.so": ("Alibaba Security Guard (mcf) — risk & anti-fraud engine", "security"),
    "libfgcpkjvqe.so": ("Randomised-name obfuscated payload (paired with assets/fgcpkjvqe.dat)", "obfuscated"),
    "libmlkitcommonpipeline.so": ("ML Kit shared pipeline", "ml"),
    "libmlkit_google_ocr_pipeline.so": ("ML Kit Google OCR pipeline", "ml"),
    "libface_detector_v2_jni.so": ("ML Kit face detection", "ml"),
    "libbarhopper_v3.so": ("ML Kit barcode scanning (barhopper)", "ml"),
    "libpicodet_ncnn.so": ("PicoDet detector on NCNN (card/document localisation)", "ml"),
    "libsqlcipher.so": ("SQLCipher encrypted database", "storage"),
    "librive-android.so": ("Rive animation runtime", "ui"),
    "libVFaceLib.so": ("TrueID eKYC liveness/face", "kyc"),
    "libtrueidlib.so": ("TrueID eKYC SDK", "kyc"),
    "liba97d.so": ("Randomised-name support library", "obfuscated"),
    "libc++_shared.so": ("libc++ runtime", "runtime"),
    "libljsfrcwmw.so": ("Randomised-name support library", "obfuscated"),
    "liblytcuhe.so": ("Randomised-name support library", "obfuscated"),
    "librcmefqonnx.so": ("ONNX Runtime shim (randomised name)", "ml"),
    "libohxbee.so": ("Randomised-name support library", "obfuscated"),
    "libcrashlytics.so": ("Crashlytics native handler", "observability"),
    "libcrashlytics-common.so": ("Crashlytics native handler", "observability"),
    "libcrashlytics-handler.so": ("Crashlytics native handler", "observability"),
    "libnative-imagetranscoder.so": ("Fresco image transcoder", "ui"),
    "libnative-filters.so": ("Fresco filters", "ui"),
    "libsecurity_lite.so": ("Vendor security lite", "security"),
    "libimage_processing_util_jni.so": ("CameraX image processing", "media"),
    "libandroidx.graphics.path.so": ("AndroidX path native", "ui"),
}

HARDENING = {
    "RELRO": lambda sec: sec.get("relro", False),
    "BIND_NOW": lambda sec: sec.get("bind_now", False),
    "NX stack": lambda sec: sec.get("nx", False),
    "PIE": lambda sec: sec.get("pie", False),
    "Stack canary": lambda sec: sec.get("canary", False),
}

RANDOM_NAME = re.compile(r"^lib[a-z0-9]{6,9}\.so$")


def entropy(b: bytes) -> float:
    if not b:
        return 0.0
    c = Counter(b)
    n = len(b)
    return -sum((v / n) * math.log2(v / n) for v in c.values())


def main() -> None:
    rows = []
    for p in sorted(LIBDIR.glob("*.so")):
        with open(p, "rb") as f:
            elf = ELFFile(f)
            sec_flags = {}
            for sec in elf.iter_sections():
                if sec.name == ".text":
                    sec_flags["text_exec"] = bool(sec["sh_flags"] & 0x4)
            dyn = elf.get_section_by_name(".dynamic")
            flags = []
            relro = bind_now = False
            if dyn:
                for tag in dyn.iter_tags():
                    if tag.entry.d_tag == "DT_FLAGS":
                        if tag.entry.d_val & 0x8:
                            bind_now = True
                    if tag.entry.d_tag == "DT_BIND_NOW":
                        bind_now = True
                    if tag.entry.d_tag == "DT_FLAGS_1" and tag.entry.d_val & 0x1:
                        bind_now = True
            has_relro = elf.get_section_by_name(".data.rel.ro") is not None
            segment_types = [s["p_type"] for s in elf.iter_segments()]
            pie = "PT_INTERP" not in segment_types and elf.header["e_type"] == "ET_DYN"
            canary = False
            _sym = elf.get_section_by_name(".dynsym")
            if _sym is not None:
                canary = any(sym.name == "__stack_chk_fail" for sym in _sym.iter_symbols())
            exports = []
            dynsym = elf.get_section_by_name(".dynsym")
            if dynsym:
                for sym in dynsym.iter_symbols():
                    if sym["st_info"]["bind"] in ("STB_GLOBAL", "STB_WEAK") and sym.name:
                        exports.append(sym.name)
            f.seek(0)
            blob = f.read()
            row = {
                "lib": p.name,
                "bytes": p.stat().st_size,
                "purpose": PURPOSE.get(p.name, ("unclassified", "unknown"))[0],
                "category": PURPOSE.get(p.name, ("unclassified", "unknown"))[1],
                "randomised_name": bool(RANDOM_NAME.match(p.name)),
                "elf": {
                    "class": elf.elfclass, "type": elf.header["e_type"],
                    "machine": elf.header["e_machine"],
                    "sections": elf.num_sections(), "segments": elf.num_segments(),
                },
                "dynamic_symbols": len(exports),
                "symbol_sample": exports[:12],
                "hardening": {
                    "relro": has_relro, "bind_now": bind_now, "nx": sec_flags.get("text_exec", False),
                    "pie": pie, "stack_canary": bool(canary),
                },
                "entropy_bits_per_byte": round(entropy(blob), 3),
                "stripped": elf.get_section_by_name(".symtab") is None,
            }
            rows.append(row)
    # companion assets that pair with libs (same random stem)
    assets = ROOT / "work" / "dex" / "assets"
    pairs = []
    if assets.exists():
        for a in sorted(assets.glob("*")):
            pairs.append({"asset": a.name, "bytes": a.stat().st_size})

    cats = Counter(r["category"] for r in rows)
    out = {
        "abi": "armeabi-v7a (32-bit, split delivered by XAPK)",
        "count": len(rows),
        "total_bytes": sum(r["bytes"] for r in rows),
        "categories": dict(cats.most_common()),
        "libraries": rows,
        "hardening_summary": {
            k: sum(1 for r in rows if r["hardening"][kk]) for k, kk in
            [("relro", "relro"), ("bind_now", "bind_now"), ("nx", "nx"), ("pie", "pie"), ("stack_canary", "stack_canary")]
        },
        "stripped_pct": round(100 * sum(1 for r in rows if r["stripped"]) / max(1, len(rows)), 1),
        "entropy_max": max(rows, key=lambda r: r["entropy_bits_per_byte"]),
        "randomised_names": [r["lib"] for r in rows if r["randomised_name"]],
    }
    (DATA / "04_native.json").write_text(json.dumps(out, indent=1, ensure_ascii=False), encoding="utf-8")
    print(f"native: {len(rows)} libs, {out['total_bytes']/2**20:.1f} MiB, stripped {out['stripped_pct']}%, "
          f"hardening {out['hardening_summary']}, randomised names {len(out['randomised_names'])}")


if __name__ == "__main__":
    main()
