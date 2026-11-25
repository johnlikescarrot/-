#!/usr/bin/env python3
"""
run_all.py — single-command reproduction of the entire static study.

    python3 re/run_all.py            # full pipeline
    python3 re/run_all.py --from 04  # resume at a stage

Stages (each is idempotent and independently runnable):
  00  unpack      ZIP -> XAPK -> split APKs -> base tree + lib/*.so
  01  census      per-file forensic census (6,466 entries)
  02  dex         multi-DEX structural intelligence (20 DEX)
  03  sbom        SBOM + build provenance
  04  assets      keys, AppX mini-apps, ML models, cascade, remote config
  05  native      ELF capability attribution (27 libs)
  06  surface     attack surface + correlation + risk register + summary
  07  re                        figure generation
  08  paper       report rendering
"""
from __future__ import annotations

import glob
import hashlib
import json
import os
import subprocess
import sys
import time
import zipfile

ROOT = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
os.chdir(ROOT)
PY = sys.executable

STAGES = [
    ("00", "unpack", "re/00_unpack.py"),
    ("01", "census", "re/01_census.py"),
    ("02", "dex", "re/02_dex.py"),
    ("03", "sbom", "re/03_sbom.py"),
    ("04", "assets", "re/04_assets.py"),
    ("05", "native", "re/05_native.py"),
    ("06", "surface", "re/06_surface.py"),
]


def sha256(path: str) -> str:
    h = hashlib.sha256()
    with open(path, "rb") as fh:
        for b in iter(lambda: fh.read(1 << 20), b""):
            h.update(b)
    return h.hexdigest()


def main() -> None:
    start = "00"
    if "--from" in sys.argv:
        start = sys.argv[sys.argv.index("--from") + 1]
    t0 = time.time()
    ran = []
    for code, name, script in STAGES:
        if code < start:
            continue
        print(f"\n{'='*72}\n== stage {code} {name}  ({script})\n{'='*72}")
        t = time.time()
        rc = subprocess.call([PY, script])
        if rc != 0:
            print(f"!! stage {code} {name} failed with rc={rc}")
            sys.exit(rc)
        ran.append({"stage": code, "name": name, "script": script,
                    "seconds": round(time.time() - t, 2)})
        print(f"== stage {code} done in {ran[-1]['seconds']}s")
    manifest = {
        "generated_utc": time.strftime("%Y-%m-%dT%H:%M:%SZ", time.gmtime()),
        "total_seconds": round(time.time() - t0, 2),
        "stages": ran,
        "python": sys.version.split()[0],
        "artifacts": {os.path.basename(p): {"bytes": os.path.getsize(p), "sha256": sha256(p)}
                      for p in sorted(glob.glob("data/*"))},
    }
    with open("data/run_manifest.json", "w") as fh:
        json.dump(manifest, fh, indent=1)
    print(f"\n[run_all] {len(ran)} stages in {manifest['total_seconds']}s -> data/run_manifest.json")


if __name__ == "__main__":
    main()
