#!/usr/bin/env python3
"""
00_unpack.py — reproducible unpacking of the APKPure bundle.

ZIP -> XAPK -> 5 split APKs; base split -> file tree; ABI split -> work/native/.
Everything lands under work/ (git-ignored) so the repository keeps only the
original 98 MB ZIP as the single authoritative input artifact.
"""
from __future__ import annotations

import glob
import hashlib
import json
import os
import zipfile

ZIP = glob.glob("Zalopay-*APKPure.zip")
assert ZIP, "input bundle not found in repo root"
ZIP = ZIP[0]


def sha256(path: str) -> str:
    h = hashlib.sha256()
    with open(path, "rb") as fh:
        for b in iter(lambda: fh.read(1 << 20), b""):
            h.update(b)
    return h.hexdigest()


def main() -> None:
    os.makedirs("work/extract", exist_ok=True)
    with zipfile.ZipFile(ZIP) as z:
        inner = [n for n in z.namelist() if n.lower().endswith(".xapk")][0]
        z.extract(inner, "work/extract")
    xapk = os.path.join("work/extract", inner)
    os.makedirs("work/xapk", exist_ok=True)
    with zipfile.ZipFile(xapk) as z:
        z.extractall("work/xapk")
    base_name = "vn.com.vng.zalopay.apk"
    os.makedirs("work/base", exist_ok=True)
    with zipfile.ZipFile(os.path.join("work/xapk", base_name)) as z:
        z.extractall("work/base")
    os.makedirs("work/native", exist_ok=True)
    n_so = 0
    for apk in glob.glob("work/xapk/config.*.apk"):
        with zipfile.ZipFile(apk) as z:
            for i in z.infolist():
                if i.filename.startswith("lib/") and i.filename.endswith(".so"):
                    out = os.path.join("work/native", os.path.basename(i.filename))
                    with open(out, "wb") as fh:
                        fh.write(z.read(i.filename))
                    n_so += 1
    bundle = json.load(open("work/xapk/manifest.json"))
    report = {
        "input_zip": {"name": os.path.basename(ZIP), "bytes": os.path.getsize(ZIP), "sha256": sha256(ZIP)},
        "xapk": {"name": os.path.basename(xapk), "bytes": os.path.getsize(xapk), "sha256": sha256(xapk)},
        "splits": {},
        "native_extracted": n_so,
        "bundle": bundle,
    }
    for apk in sorted(glob.glob("work/xapk/*.apk")):
        report["splits"][os.path.basename(apk)] = {
            "bytes": os.path.getsize(apk), "sha256": sha256(apk),
            "entries": len(zipfile.ZipFile(apk).infolist()),
        }
    os.makedirs("data", exist_ok=True)
    json.dump(report, open("data/unpack.json", "w"), indent=1)
    print(f"[unpack] {len(report['splits'])} splits, {n_so} native libs -> data/unpack.json")


if __name__ == "__main__":
    main()
