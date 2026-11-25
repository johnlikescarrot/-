#!/usr/bin/env bash
# End-to-end reproduction of every number in research/ from the ZIP in repo root.
# Requirements: python3.11+, unzip. Python deps: androguard, pyelftools, pillow,
# cryptography, matplotlib (see requirements.txt). No network at run time.
set -euo pipefail
cd "$(dirname "$0")/../.."

PY=${PY:-.venv/bin/python}
ZIP="Zalopay-Thanh+toán+&+Tài+chính_11.17.0_APKPure.zip"
[ -x "$PY" ] || PY=python3

echo "[0/9] unpacking container"
mkdir -p work/zip work/apk work/lib
unzip -o -q "$ZIP" -d work/zip
XAPK=$(find work/zip -maxdepth 1 -name '*.xapk' | head -1)
unzip -o -q "$XAPK" -d work/zip/xapk
unzip -o -q work/zip/xapk/vn.com.vng.zalopay.apk -d work/apk
unzip -o -q work/zip/xapk/config.armeabi_v7a.apk -d work/lib

for s in 01_provenance 02_manifest 03_dex 04_sdks 05_native 06_signals 07_resources 08_assembly 09_figures; do
  echo "[$s]"
  "$PY" "tools/analyze/$s.py"
done
echo "done → data/*.json, data/figures/*.png, data/dataset.csv"
