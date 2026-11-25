#!/usr/bin/env bash
# Full static-analysis pipeline. Idempotent; writes research/data/*.json.
set -euo pipefail
cd "$(dirname "$0")/.."

if [ ! -d .venv ]; then python3 -m venv .venv; fi
.venv/bin/pip install -q --upgrade pip
.venv/bin/pip install -q -r research/requirements.txt

echo "== 0. extracting archive =="
python3 - <<'PY'
import pathlib, zipfile
root = pathlib.Path('.')
outer = next(root.glob('*.zip'))
out = root / 'work/extracted'; out.mkdir(parents=True, exist_ok=True)
with zipfile.ZipFile(outer) as z: z.extractall(out)
xapk = next(out.glob('*.xapk'))
splits = root / 'work/extracted/xapk'; splits.mkdir(parents=True, exist_ok=True)
with zipfile.ZipFile(xapk) as z: z.extractall(splits)
apks = root / 'work/apks'; apks.mkdir(parents=True, exist_ok=True)
libs = root / 'work/libs'; libs.mkdir(parents=True, exist_ok=True)
dexdir = root / 'work/dex'; dexdir.mkdir(parents=True, exist_ok=True)
for p in sorted(splits.glob('*.apk')):
    (apks / p.name).write_bytes(p.read_bytes())
    with zipfile.ZipFile(p) as z:
        for n in z.namelist():
            if n.endswith('.so'): z.extract(n, libs)
        for n in z.namelist():
            if n.endswith('.dex') or (n.startswith('assets/') and len(n.split('/')) > 1 and '.' not in n.split('/')[-1]):
                pass
with zipfile.ZipFile(apks / 'vn.com.vng.zalopay.apk') as z:
    for n in z.namelist():
        if n.endswith('.dex'): z.extract(n, dexdir)
        if n.startswith('assets/') and '/' in n[7:]:
            z.extract(n, dexdir)
print('extracted')
PY

for s in 00_inventory 01_manifest 02_dex 03_sdk 04_native 05_resources 06_security 07_insights 08_film_data 09_report; do
  echo "== $s =="; .venv/bin/python "research/scripts/${s}.py"
done
echo "analysis complete → research/data/"
