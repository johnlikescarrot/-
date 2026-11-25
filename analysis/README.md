# ZaloPay 11.17.0 — reverse-engineering pipeline

Everything here is **derived data + reproducible code**. The 98.8 MB APK payload is deliberately
excluded (see `../.gitignore`); the scripts re-create every number in `REPORT.md` from the public
APKPure bundle.

## Quick start

```bash
python3 -m venv .venv && . .venv/bin/activate
pip install androguard pyelftools loguru

# 1. unpack ZIP -> XAPK -> split APKs
python3 - <<'PY'
import zipfile
zipfile.ZipFile("Zalopay-Thanh+toán+&+Tài+chính_11.17.0_APKPure.zip").extractall("work/outer")
import glob, os
x = glob.glob("work/outer/*.xapk")[0]
zipfile.ZipFile(x).extractall("work/xapk")
for a in glob.glob("work/xapk/*.apk"):
    d = "work/apk/" + os.path.basename(a)[:-4]
    zipfile.ZipFile(a).extractall(d)
PY

# 2. run the stages
export ZPAY_WORK=$PWD/work
for s in analysis/scripts/stage*.py; do python3 "$s"; done
python3 analysis/scripts/report.py     # -> analysis/REPORT.md
```

## Stages

| Stage | Script | Output |
|---|---|---|
| 1 | `stage1_container_manifest.py` | `data/01_container.json`, `data/02_manifest.json`, `data/AndroidManifest.decoded.xml` |
| 2 | `stage2_dex.py` | `data/03_dex.json` |
| 2b | `stage2b_dex_xref.py` | `data/04_dex_xref.json` (custom dependency-free DEX walker) |
| 3 | `stage3_native_assets_res.py` | `data/05_native.json`, `06_assets.json`, `07_resources.json` |
| 4 | `stage4_security_fingerprint.py` | `data/08_security.json`, `09_fingerprint.json`, `10_metrics.json` |
| 5 | `stage5_ecosystem.py` | `data/11_ecosystem.json` |
| 6 | `report.py` | `REPORT.md` |

**Stage 2b** deserves a note: R8 renames identifiers, so intent survives only in the string constant
pool. `stage2b` is a from-scratch DEX reader (header → class_data → code_item) that decodes
`const-string` (0x1a) and `const-string/jumbo` (0x1b) to build a class→literal index. That is how the
study attributes SPKI pins to `com.google.android.gms.internal.ads.*` (and proves there is **no**
first-party pin set) instead of reporting a false positive.

## Ethics

Static analysis only; no service contact, no credential extraction, no bypass tooling. Public keys
shipped by the client are noted as build facts and not reproduced.
