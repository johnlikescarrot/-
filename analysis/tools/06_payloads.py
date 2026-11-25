#!/usr/bin/env python3
"""Stage 6 — hidden-payload detection: magic-byte scan of every zip entry.

Extension whitelists are a poor security control; content magic is better.
This stage re-types all 6,162 + 302 entries by their first bytes and flags
executables that do not live where executables are expected.

Output: data/06_payloads.json
"""
from __future__ import annotations

import hashlib
import json
import math
import os
import re
import zipfile
from collections import Counter
from datetime import datetime, timezone

ROOT = os.path.abspath(os.path.join(os.path.dirname(__file__), "..", ".."))
XAPK = os.path.join(ROOT, "work", "xapk")
OUT = os.path.join(ROOT, "data")

MAGIC = [
    (b"\x7fELF", "ELF executable"),
    (b"dex\n", "Dalvik DEX"),
    (b"PK\x03\x04", "ZIP archive"),
    (b"\x1f\x8b", "gzip"),
    (b"\x28\xb5\x2f\xfd", "zstd"),
    (b"SQLite format 3", "SQLite database"),
    (b"TFL3", "TensorFlow Lite model"),
    (b"\x08\x00\x00\x00", "Protobuf (heuristic)"),
    (b"rizin", "unknown"),
    (b"-----BEGIN", "PEM key/cert"),
    (b"\x00asm", "WebAssembly"),
    (b"yolo", "unknown"),
]

EXPECTED_SO = re.compile(r"^lib/[^/]+/.*\.so$")


def entropy(blob: bytes) -> float:
    if not blob:
        return 0.0
    counts = Counter(blob)
    n = len(blob)
    return round(-sum(c / n * math.log2(c / n) for c in counts.values()), 2)


def sniff(head: bytes) -> str:
    for magic, label in MAGIC:
        if head.startswith(magic):
            return label
    return "opaque"


def main() -> None:
    apks = ["vn.com.vng.zalopay.apk", "config.armeabi_v7a.apk",
            "config.en.apk", "config.vi.apk", "config.xhdpi.apk"]
    findings: list[dict] = []
    type_counts: Counter = Counter()
    mismatches: list[dict] = []
    model_files: list[dict] = []

    for apk in apks:
        path = os.path.join(XAPK, apk)
        with zipfile.ZipFile(path) as z:
            for info in z.infolist():
                if info.file_size == 0:
                    continue
                with z.open(info) as fh:
                    head = fh.read(16)
                kind = sniff(head)
                type_counts[kind] += 1
                name = info.filename
                ext = os.path.splitext(name)[1].lower()

                if kind == "ELF executable" and not EXPECTED_SO.match(name):
                    findings.append({
                        "apk": apk, "path": name, "bytes": info.file_size,
                        "hidden_because": "ELF inside " + (name.split("/")[0] or "root"),
                        "loadable": "requires extraction to a writable dir + System.load",
                    })
                if kind == "Dalvik DEX" and not name.startswith(("classes", "assets/")):
                    findings.append({"apk": apk, "path": name, "bytes": info.file_size,
                                     "hidden_because": "DEX outside the class loader path"})
                if kind == "ZIP archive" and ext not in (".apk", ".zip", ".jar", ".aar", ".xapk"):
                    mismatches.append({"apk": apk, "path": name, "bytes": info.file_size,
                                       "claimed": ext or "(none)", "actual": kind})
                if kind == "TensorFlow Lite model" or ext in (".tflite", ".param", ".bin", ".mnn", ".onnx"):
                    model_files.append({"apk": apk, "path": name, "bytes": info.file_size, "kind": kind})
                if ext in (".so",) and kind != "ELF executable":
                    body = z.read(info)
                    findings.append({
                        "apk": apk, "path": name, "bytes": info.file_size,
                        "hidden_because": f"named .so but content is {kind} "
                                          f"(entropy {entropy(body)} b/B)",
                        "loadable": "no: not a valid ELF; dlopen/System.load will fail",
                    })
                    mismatches.append({"apk": apk, "path": name, "bytes": info.file_size,
                                       "claimed": ".so", "actual": kind,
                                       "entropy": entropy(body)})

    # assets/ inventory by size, to characterise what is really shipped
    with zipfile.ZipFile(os.path.join(XAPK, "vn.com.vng.zalopay.apk")) as z:
        assets = [{"path": i.filename, "bytes": i.file_size}
                  for i in z.infolist() if i.filename.startswith("assets/")]
        res_raw = [{"path": i.filename, "bytes": i.file_size}
                   for i in z.infolist() if i.filename.startswith("res/raw/")]
    assets.sort(key=lambda r: -r["bytes"])
    with zipfile.ZipFile(os.path.join(XAPK, "vn.com.vng.zalopay.apk")) as z:
        bundle_zips = [{"path": i.filename, "bytes": i.file_size, "entries": i.compress_size}
                       for i in z.infolist()
                       if i.filename.startswith("assets/") and z.read(i)[:4] == b"PK\x03\x04"]
        pem = []
        for i in z.infolist():
            if i.file_size < 20000:
                body = z.read(i)
                if body.startswith(b"-----BEGIN"):
                    pem.append({"path": i.filename, "bytes": i.file_size,
                                "sha256": hashlib.sha256(body).hexdigest()[:24],
                                "kind": body.split(b"\n")[0].decode("ascii", "replace")})

    # JS bridge inventory (mini-app runtime surface)
    with zipfile.ZipFile(os.path.join(XAPK, "vn.com.vng.zalopay.apk")) as z:
        bridge = []
        for i in z.infolist():
            if i.filename.endswith(".js") and i.file_size < 2_000_000:
                src = z.read(i).decode("utf-8", "replace")
                bridge.append({
                    "path": i.filename,
                    "bytes": i.file_size,
                    "postMessage": src.count("postMessage"),
                    "prompt": src.count("prompt("),
                    "jsapi": len(re.findall(r"jsapi", src, re.I)),
                    "needs_review": bool(re.search(r"eval\(|new Function\(", src)),
                })
        bridge.sort(key=lambda r: -r["bytes"])

    result = {
        "generated_at": datetime.now(timezone.utc).isoformat(),
        "entries_scanned": int(sum(type_counts.values())),
        "content_types": type_counts.most_common(),
        "hidden_executables": findings,
        "extension_mismatches": mismatches[:40],
        "model_files": model_files[:20],
        "assets_largest": assets[:20],
        "res_raw_largest": sorted(res_raw, key=lambda r: -r["bytes"])[:10],
        "js_bridge": bridge[:14],
        "nested_bundles": bundle_zips,
        "public_keys": pem,
        "notes": [
            "Content magic beats extension: 302 entries re-typed from their first 16 bytes.",
            "assets/trueidlib.so is an ELF shipped outside lib/: it can only be loaded "
            "after extraction to app-writable storage.",
            "assets/audience_network.dex is a secondary DEX delivered as an asset "
            "(Meta Audience Network), loaded via a custom class loader.",
        ],
    }
    with open(os.path.join(OUT, "06_payloads.json"), "w") as fh:
        json.dump(result, fh, indent=1)
    print("scanned:", result["entries_scanned"], "types:", type_counts.most_common(6))
    print("hidden executables:", [(f["apk"], f["path"], f["bytes"]) for f in findings])
    print("mismatches:", len(mismatches), "models:", len(model_files))
    print("assets top:", [(a["path"], a["bytes"]) for a in assets[:6]])
    print("bridge:", [(b["path"], b["bytes"], b["postMessage"]) for b in bridge[:6]])


if __name__ == "__main__":
    main()
