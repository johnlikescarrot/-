#!/usr/bin/env python3
"""Generate research/COMPLETE_FILE_CATALOG.md directly from verified census & analysis datasets."""

import csv
import gzip
import json
from collections import defaultdict
from pathlib import Path

DATA = Path("data")
OUT = Path("research/COMPLETE_FILE_CATALOG.md")

census = json.loads((DATA / "census_summary.json").read_text(encoding="utf-8"))
native = json.loads((DATA / "native_elf_analysis.json").read_text(encoding="utf-8"))
dex = json.loads((DATA / "dex_ecology.json").read_text(encoding="utf-8"))
aml = json.loads((DATA / "assets_and_ml.json").read_text(encoding="utf-8"))

with gzip.open(DATA / "full_file_census.csv.gz", "rt", encoding="utf-8") as f:
    rows = list(csv.DictReader(f))

lines = []
lines.append("# Complete File & Artifact Catalog — ZaloPay 11.17.0 (`Zalopay-Thanh+toán+&+Tài+chính_11.17.0_APKPure.zip`)")
lines.append("")
lines.append(f"This catalog accounts for **100% of all {len(rows):,} indexed member files** across every archive layer. The complete machine-readable per-file table (including SHA-256, CRC32, compression ratio, magic signature, and Shannon entropy for all {len(rows):,} files) is stored in [`data/full_file_census.csv.gz`](../data/full_file_census.csv.gz).")
lines.append("")

lines.append("## 1. Top-Level Archive & Split APK Inventory")
lines.append("")
lines.append("| Container | Member Files | Uncompressed Bytes | Compressed Bytes |")
lines.append("| :--- | ---: | ---: | ---: |")
for cname, vals in census["by_container"].items():
    lines.append(f"| `{cname}` | {vals['file_count']:,} | {vals['uncompressed_bytes']:,} | {vals['compressed_bytes']:,} |")
lines.append("")

lines.append("## 2. Complete Directory & Subsystem Breakdown Across All 5 APKs (6,464 Files)")
lines.append("")
lines.append("| Subsystem | Files | Uncompressed Bytes | Compressed Bytes | Share of APK Bytes |")
lines.append("| :--- | ---: | ---: | ---: | ---: |")
total_apk_bytes = census["totals"]["apk_member_uncompressed_bytes"]
for sub, vals in census["by_subsystem_across_apks"].items():
    pct = vals["uncompressed_bytes"] / total_apk_bytes * 100
    lines.append(f"| `{sub}` | {vals['file_count']:,} | {vals['uncompressed_bytes']:,} | {vals['compressed_bytes']:,} | {pct:.2f}% |")
lines.append("")

lines.append("## 3. All 7 Inner XAPK Members (`inner_xapk`)")
lines.append("")
lines.append("| Path | Bytes | Compression | CRC32 | SHA-256 |")
lines.append("| :--- | ---: | :--- | :--- | :--- |")
for r in rows:
    if r["container"] == "inner_xapk":
        lines.append(f"| `{r['path']}` | {int(r['uncompressed_bytes']):,} | {r['compression']} | `{r['crc32']}` | `{r['sha256']}` |")
lines.append("")

lines.append("## 4. All 20 Dalvik Executable (`.dex`) Files")
lines.append("")
lines.append("| File | DEX Ver | Bytes | Classes | Methods | Native Methods | R8 Obfuscated (%) | SHA-1 |")
lines.append("| :--- | :---: | ---: | ---: | ---: | ---: | ---: | :--- |")
for d in dex["per_dex"]:
    lines.append(f"| `{d['dex_file']}` | `{d['dex_version']}` | {d['file_size']:,} | {d['defined_classes']:,} | {d['defined_methods']:,} | {d['native_methods']:,} | {d['obfuscation_ratio']*100:.2f}% | `{d['sha1'][:16]}...` |")
lines.append("")

lines.append("## 5. All 27 Native ARM32 ELF Shared Objects (`config.armeabi_v7a.apk`)")
lines.append("")
lines.append("| Library | Bytes | Entropy (File / `.text` / `.rodata`) | NX / RELRO / Canary / Stripped | `JNI_OnLoad` | `Java_*` Exports | Category |")
lines.append("| :--- | ---: | :---: | :---: | :---: | ---: | :--- |")
for lib in sorted(native["libraries"], key=lambda x: x["bytes"], reverse=True):
    h = lib["hardening"]
    h_str = f"{h.get('nx','?')[:3]}/{h.get('relro','?')[:3]}/{h.get('stack_protector','?')[:3]}/{h.get('stripped','?')[:3]}"
    lines.append(
        f"| `{lib['name']}` | {lib['bytes']:,} | {lib['file_entropy']:.3f} / {lib['text_entropy'] or 0:.3f} / {lib['rodata_entropy'] or 0:.3f} | `{h_str}` | `{lib['has_jni_onload']}` | {lib['static_java_jni_export_count']} | {lib['vendor_category']} |"
    )
lines.append("")

lines.append("## 6. Non-Code Root, Split, Asset, and Nested Package Files")
lines.append("")
lines.append("### 6.1 Small Split APKs (`config.en.apk`, `config.vi.apk`, `config.armeabi_v7a.apk`) Complete File List")
lines.append("")
for cname in ("config.en.apk", "config.vi.apk", "config.armeabi_v7a.apk"):
    lines.append(f"#### `{cname}`")
    lines.append("")
    lines.append("| Path | Bytes | Magic | Entropy | SHA-256 |")
    lines.append("| :--- | ---: | :--- | ---: | :--- |")
    for r in rows:
        if r["container"] == cname:
            lines.append(f"| `{r['path']}` | {int(r['uncompressed_bytes']):,} | `{r['magic_type']}` | {float(r['shannon_entropy']):.4f} | `{r['sha256'][:16]}...` |")
    lines.append("")

lines.append("### 6.2 `config.xhdpi.apk` Directory & Extension Rollup (258 Files)")
lines.append("")
xhdpi_agg = defaultdict(lambda: [0, 0])
for r in rows:
    if r["container"] == "config.xhdpi.apk":
        top = "/".join(r["path"].split("/")[:2]) if "/" in r["path"] else r["path"]
        xhdpi_agg[top][0] += 1
        xhdpi_agg[top][1] += int(r["uncompressed_bytes"])
lines.append("| Prefix | Files | Bytes |")
lines.append("| :--- | ---: | ---: |")
for k, (cnt, b) in sorted(xhdpi_agg.items(), key=lambda kv: -kv[1][1]):
    lines.append(f"| `{k}` | {cnt:,} | {b:,} |")
lines.append("")

lines.append("### 6.3 `vn.com.vng.zalopay.apk` Complete Top-Level Folder / Prefix Rollup (6,162 Files)")
lines.append("")
base_agg = defaultdict(lambda: [0, 0])
for r in rows:
    if r["container"] == "vn.com.vng.zalopay.apk":
        parts = r["path"].split("/")
        key = "/".join(parts[:2]) if len(parts) > 1 else (
            "*.properties (Maven descriptors)" if r["path"].endswith(".properties") else (
                "classes*.dex (Primary multidex)" if r["path"].endswith(".dex") else r["path"]
            )
        )
        base_agg[key][0] += 1
        base_agg[key][1] += int(r["uncompressed_bytes"])
lines.append("| Path Prefix / Group | Files | Uncompressed Bytes |")
lines.append("| :--- | ---: | ---: |")
for k, (cnt, b) in sorted(base_agg.items(), key=lambda kv: -kv[1][1]):
    lines.append(f"| `{k}` | {cnt:,} | {b:,} |")
lines.append("")

lines.append("### 6.4 All 14 TensorFlow Lite + NCNN + OpenCV Models")
lines.append("")
lines.append("| Path | Bytes | Engine / Magic |")
lines.append("| :--- | ---: | :--- |")
for m in aml["ml_and_computer_vision"]["tflite_models"]:
    lines.append(f"| `{m['path']}` | {m['bytes']:,} | TFLite (`{m['magic']}`) |")
lines.append(f"| `assets/FastestDet.ncnn.bin` | {aml['ml_and_computer_vision']['ncnn_fastestdet']['bin_bytes']:,} | NCNN Weights ({aml['ml_and_computer_vision']['ncnn_fastestdet']['declared_layers']} layers) |")
lines.append(f"| `assets/FastestDet.ncnn.param` | {aml['ml_and_computer_vision']['ncnn_fastestdet']['param_bytes']:,} | NCNN Graph Param |")
for m in aml["ml_and_computer_vision"]["opencv_classical_models"]:
    lines.append(f"| `{m['path']}` | {m['bytes']:,} | OpenCV Classical Cascade/LBF |")
lines.append("")

lines.append("### 6.5 Nested Containers (`assets/android.1.zip` & Griver `.amr`/`.tar` Archives)")
lines.append("")
for cname in ("assets/android.1.zip", "amr:68687209_2.9.7.8.amr", "amr:66666692_1.24.8.amr", "amr:66666693_2.0.7.amr"):
    crows = [r for r in rows if r["container"] == cname]
    lines.append(f"#### `{cname}` ({len(crows)} files, {sum(int(x['uncompressed_bytes']) for x in crows):,} bytes)")
    lines.append("")
    if len(crows) <= 25:
        lines.append("| Path | Bytes | Magic | Entropy |")
        lines.append("| :--- | ---: | :--- | ---: |")
        for r in crows:
            lines.append(f"| `{r['path']}` | {int(r['uncompressed_bytes']):,} | `{r['magic_type']}` | {float(r['shannon_entropy']):.4f} |")
    else:
        agg = defaultdict(lambda: [0, 0])
        for r in crows:
            k = r["path"].split("/")[0] if "/" in r["path"] else r["path"]
            agg[k][0] += 1
            agg[k][1] += int(r["uncompressed_bytes"])
        lines.append("| Bucket | Files | Bytes |")
        lines.append("| :--- | ---: | ---: |")
        for k, (cnt, b) in sorted(agg.items(), key=lambda kv: -kv[1][1]):
            lines.append(f"| `{k}` | {cnt:,} | {b:,} |")
    lines.append("")

OUT.write_text("\n".join(lines), encoding="utf-8")
print(f"Wrote {OUT} ({len(lines)} lines)")
