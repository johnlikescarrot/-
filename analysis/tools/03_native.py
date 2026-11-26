#!/usr/bin/env python3
"""Stage 3 — native ELF layer: hardening, JNI surface, dependency graph, aliases.

Output: data/03_native.json
"""
from __future__ import annotations

import json
import os
import re
import sys
import zipfile
from datetime import datetime, timezone

sys.path.insert(0, os.path.join(os.path.dirname(__file__)))
from dexlib import parse_elf  # noqa: E402

ROOT = os.path.abspath(os.path.join(os.path.dirname(__file__), "..", ".."))
XAPK = os.path.join(ROOT, "work", "xapk")
OUT = os.path.join(ROOT, "data")

OBF = re.compile(r"^lib[a-z0-9]{8}\.so$")
VENDOR = [
    ("libsqlcipher", "SQLCipher (encrypted SQLite)", "Zetetic-derived"),
    ("libmlkit", "ML Kit pipeline", "Google"),
    ("libbarhopper", "Barcode / QR decoding", "Google"),
    ("libface_detector", "Face detection", "Google ML Kit"),
    ("libimage_processing_util", "CameraX image conversion", "AndroidX"),
    ("libimagepipeline", "Fresco image pipeline", "Facebook"),
    ("libnative-imagetranscoder", "Fresco transcoder", "Facebook"),
    ("libnative-filters", "Fresco filters", "Facebook"),
    ("libcrashlytics", "Crash reporting", "Google Firebase"),
    ("librive", "Rive animation runtime", "Rive"),
    ("libpicodet", "PicoDet detector (ncnn)", "PicoDet / Tencent ncnn"),
    ("libVFaceLib", "Face alignment", "VN AI face SDK"),
    ("libtrueidlib", "TrueID liveness / eKYC", "VN AI TrueID"),
    ("libsecurity_lite", "SecurityGuardLite crypto bridge", "Alipay"),
    ("libtaibmcf", "Anti-fraud / device attestation blob", "Alipay (Tai)" ),
    ("libandroidx.graphics.path", "Path parsing", "AndroidX"),
    ("libc++_shared", "C++ runtime", "LLVM"),
]


# Libraries whose ASCII payload is dominated by JNI type descriptors and
# reflective lookups: the signature of an in-app code-protection layer
# (VMP / packer shell) rather than an ML or media library.
PROTECTOR = {"libljsfrcwmw.so", "libohxbee.so", "liba97d.so",
             "librcmefqonnx.so", "liblytcuhe.so", "libfgcpkjvqe.so", "libtaibmcf.so"}


def descriptor_density(strings: list[str]) -> float:
    """Share of strings that look like JNI signatures or Java type names."""
    if not strings:
        return 0.0
    hits = sum(1 for s in strings
               if s.startswith("(") and ";" in s or s.startswith("L") and s.endswith(";"))
    return round(hits / len(strings), 4)


def classify(name: str) -> tuple[str, str]:
    for prefix, label, vendor in VENDOR:
        if name.startswith(prefix):
            return label, vendor
    if name in PROTECTOR:
        return "Code-protection / JNI bridge payload", "ZaloPay (name-stripped)"
    if OBF.match(name):
        return "Obfuscated app-private library", "ZaloPay (name-stripped)"
    return "Unclassified", "unknown"


def demangle(jni: str) -> str:
    """Java_<pkg>_<Class>_<method> → package.Class#method (approximate)."""
    body = jni[len("Java_"):]
    body = body.replace("_00024", "$").replace("_0003c", "<").replace("_0003e", ">")
    body = body.replace("_1", "\x00").replace("_2", "\x01").replace("_3", "\x02")
    parts = body.split("_")
    parts = [p.replace("\x00", "_").replace("\x01", ";").replace("\x02", "[") for p in parts]
    if len(parts) >= 3:
        return ".".join(parts[:-2]) + "." + parts[-2] + "#" + parts[-1]
    return body


def main() -> None:
    os.makedirs(OUT, exist_ok=True)
    apk = os.path.join(XAPK, "config.armeabi_v7a.apk")
    libs = []
    with zipfile.ZipFile(apk) as z:
        for name in sorted(n for n in z.namelist() if n.endswith(".so")):
            info = parse_elf(name, z.read(name))
            short = name.split("/")[-1]
            label, vendor = classify(short)
            d = info.to_json(keep_strings=False)
            d.update({
                "short": short,
                "descriptor_density": descriptor_density(info.strings),
                "strings": [x for x in info.strings if x.startswith(("(", "L"))][:60],
                "family": label,
                "vendor": vendor,
                "obfuscated_name": bool(OBF.match(short)),
                "exports": info.exported[:400],
                "jni_demangled": [demangle(s) for s in info.jni_exports],
            })
            libs.append(d)

    # aliases: identical content under different names
    by_hash: dict[str, list[str]] = {}
    for d in libs:
        by_hash.setdefault(d["sha256"], []).append(d["short"])
    aliases = {h: v for h, v in by_hash.items() if len(v) > 1}

    bundled = {d["short"] for d in libs}
    graph = []
    for d in libs:
        edges = [n for n in d["needed"] if n in bundled]
        if edges:
            graph.append({"from": d["short"], "to": edges})

    hardened = [d for d in libs if d["relro"] == "full" and d["nx"] and d["pie"]]
    no_relro = [d["short"] for d in libs if d["relro"] != "full"]
    unstripped = [d["short"] for d in libs if not d["stripped"]]
    canary_free = [d["short"] for d in libs if not d["canary"]]

    result = {
        "generated_at": datetime.now(timezone.utc).isoformat(),
        "lib_count": len(libs),
        "total_bytes": sum(d["size"] for d in libs),
        "libs": libs,
        "aliases": aliases,
        "dependency_graph": graph,
        "jni_index": sorted({j for d in libs for j in d["jni_demangled"]}),
        "totals": {
            "hardened_full": len(hardened),
            "hardening_pct": round(100 * len(hardened) / max(1, len(libs)), 1),
            "relro_exceptions": no_relro,
            "unstripped": unstripped,
            "canary_free": canary_free,
            "exported_symbols": sum(len(d["exports"]) for d in libs),
            "jni_symbols": sum(d["jni_export_count"] for d in libs),
            "obfuscated_libs": [d["short"] for d in libs if d["family"].startswith("Code-protection")],
            "protector_bytes": sum(d["size"] for d in libs if d["family"].startswith("Code-protection")),
            "protector_density": {d["short"]: d["descriptor_density"]
                                  for d in libs if d["family"].startswith("Code-protection")},
            "obfuscated_bytes": sum(d["size"] for d in libs if d["obfuscated_name"]),
            "largest": sorted(((d["size"], d["short"]) for d in libs), reverse=True)[:10],
        },
    }
    with open(os.path.join(OUT, "03_native.json"), "w") as fh:
        json.dump(result, fh, indent=1)
    print("libs:", len(libs), "bytes:", result["total_bytes"])
    print("hardening:", result["totals"]["hardening_pct"], "% | exceptions:", no_relro)
    print("obfuscated:", result["totals"]["obfuscated_libs"], result["totals"]["obfuscated_bytes"])
    print("aliases:", aliases)
    print("graph edges:", len(graph))
    print("jni symbols:", result["totals"]["jni_symbols"])


if __name__ == "__main__":
    main()
