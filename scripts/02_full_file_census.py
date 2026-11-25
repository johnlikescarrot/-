#!/usr/bin/env python3
"""Exhaustive 100% file-by-file forensic census across the entire ZaloPay 11.17.0 archive hierarchy."""

import csv
import gzip
import hashlib
import io
import json
import math
import tarfile
import zipfile
from collections import Counter, defaultdict
from pathlib import Path

REPO_ROOT = Path.cwd()
CORPUS = Path("/tmp/zalopay_corpus")
OUT_DIR = REPO_ROOT / "data"
OUT_DIR.mkdir(parents=True, exist_ok=True)

OUTER_ZIP = REPO_ROOT / "Zalopay-Thanh+toán+&+Tài+chính_11.17.0_APKPure.zip"


def shannon_entropy(data: bytes) -> float:
    n = len(data)
    if n == 0:
        return 0.0
    counts = Counter(data)
    ent = 0.0
    for c in counts.values():
        p = c / n
        ent -= p * math.log2(p)
    return round(ent, 4)


def detect_magic(data: bytes, path: str) -> str:
    if len(data) == 0:
        return "empty"
    head = data[:16]
    ext = Path(path).suffix.lower()

    if head.startswith(b"\x7fELF"):
        ei_class = data[4] if len(data) > 4 else 0
        return "elf32" if ei_class == 1 else ("elf64" if ei_class == 2 else "elf")
    if head.startswith(b"dex\n"):
        ver = data[4:7].decode("ascii", errors="replace")
        return f"dex_{ver}"
    if head.startswith(b"PK\x03\x04") or head.startswith(b"PK\x05\x06"):
        return "zip_container"
    if len(data) > 265 and data[257:262] == b"ustar":
        return "tar_archive"
    if head.startswith(b"\x89PNG\r\n\x1a\n"):
        return "png"
    if head[:4] == b"RIFF" and data[8:12] == b"WEBP":
        return "webp"
    if head.startswith(b"\xff\xd8\xff"):
        return "jpeg"
    if head.startswith(b"ID3") or head[:2] in (b"\xff\xfb", b"\xff\xf3", b"\xff\xf2"):
        return "mp3_audio"
    if head.startswith(b"GIF87a") or head.startswith(b"GIF89a"):
        return "gif"
    if head.startswith(b"\x1f\x8b"):
        return "gzip"
    if head.startswith(b"\x03\x00\x08\x00"):
        return "android_binary_xml"
    if head.startswith(b"\x02\x00\x0c\x00"):
        return "android_arsc_table"
    if head.startswith(b"pro\x00"):
        return "android_art_profile"
    if len(data) >= 8 and data[4:8] == b"TFL3":
        return "tflite_flatbuffer"
    if head.startswith(b"RIVE"):
        return "rive_animation"
    if head.startswith(b"\x00\x01\x00\x00") and ext in (".ttf", ".ttc"):
        return "truetype_font"
    if head.startswith(b"OTTO"):
        return "opentype_font"
    if head.startswith(b"wOF2"):
        return "woff2_font"
    if head.startswith(b"-----BEGIN "):
        return "pem_encoded"
    if head.startswith(b"0\x82"):
        return "der_asn1_pkcs7"
    if path.endswith(".ncnn.bin"):
        return "ncnn_weights_bin"
    if path.endswith(".ncnn.param"):
        return "ncnn_graph_param"

    sample = data[:4096]
    try:
        txt = sample.decode("utf-8")
        stripped = txt.lstrip()
        if ext == ".json" or stripped.startswith("{") or stripped.startswith("["):
            return "json_or_text"
        if ext in (".html", ".htm") or stripped.lower().startswith("<!doctype") or stripped.lower().startswith("<html"):
            return "html"
        if ext in (".js", ".jsbundle"):
            return "javascript"
        if ext == ".css":
            return "css"
        if ext == ".svg" or "<svg" in stripped[:256].lower():
            return "svg"
        if ext == ".xml" or stripped.startswith("<?xml"):
            return "xml_text"
        if ext == ".yaml" or stripped.startswith("%YAML"):
            return "yaml_text"
        if ext == ".properties":
            return "java_properties"
        if ext in (".kotlin_builtins", ".kotlin_module"):
            return "kotlin_metadata_bin"
        if all((32 <= ord(ch) < 127) or ch in "\r\n\t" for ch in txt[:256]):
            return "utf8_ascii_text"
    except UnicodeDecodeError:
        pass

    if ext in (".kotlin_builtins", ".kotlin_module"):
        return "kotlin_metadata_bin"
    if ext == ".pb":
        return "protobuf_bin"
    return "opaque_binary"


def classify_subsystem(container: str, path: str, magic: str) -> str:
    p = path.lower()
    if container == "outer_zip":
        return "xapk_container"
    if container == "inner_xapk":
        return "split_apk_package" if p.endswith(".apk") else "xapk_metadata"
    if container == "assets/android.1.zip":
        return "react_native_bundle" if p == "main.jsbundle" else "react_native_asset"
    if container.startswith("amr:"):
        return "griver_amr_tar_member" if p.endswith(".tar") or "/" in p else "griver_amr_metadata"
    if p == "androidmanifest.xml":
        return "manifest"
    if p == "resources.arsc":
        return "resource_table"
    if p.startswith("meta-inf/"):
        return "signing_and_meta_inf"
    if p.endswith(".dex") and "/" not in p:
        return "primary_multidex_bytecode"
    if p.startswith("lib/") and p.endswith(".so"):
        return "native_elf_library"
    if p.startswith("org/bouncycastle/pqc/"):
        return "bouncycastle_pqc_table"
    if p.endswith(".properties") and "/" not in p:
        return "maven_version_properties"
    if p.startswith("kotlin/") or p == "kotlin-tooling-metadata.json" or p == "debugprobeskt.bin":
        return "kotlin_runtime_metadata"
    if p.startswith("res/"):
        if magic == "android_binary_xml":
            return "res_compiled_xml"
        if magic in ("png", "webp", "jpeg", "gif"):
            return "res_raster_image"
        if magic in ("truetype_font", "opentype_font", "woff2_font"):
            return "res_font"
        if p in ("res/raw/lbfmodel.yaml", "res/raw/haarcascade_frontalface_default.xml"):
            return "res_raw_opencv_cv_models"
        if magic == "rive_animation" or (p.startswith("res/raw/") and p.endswith(".json")):
            return "res_raw_lottie_rive_json"
        if magic == "mp3_audio":
            return "res_raw_audio"
        return "res_other"
    if p.startswith("assets/"):
        if p.startswith("assets/griver"):
            return "assets_griver_miniapp_engine"
        if p.endswith("jsbridge.js") or p == "assets/webapp.js":
            return "assets_js_bridge"
        if p.startswith("assets/containererrorpage"):
            return "assets_miniapp_error_pages"
        if p.startswith("assets/mlkit") or p.startswith("assets/models_bundled") or p.startswith("assets/custom_models") or "ncnn" in p:
            return "assets_on_device_ml_cv"
        if p == "assets/android.1.zip":
            return "assets_react_native_zip"
        if p == "assets/audience_network.dex":
            return "assets_secondary_dex"
        if p.startswith("assets/dexopt/"):
            return "assets_art_baseline_profile"
        if p.startswith("assets/com/visa/cbp/"):
            return "assets_visa_cbp_hce_nfc"
        if p.endswith(".dat") or p == "assets/trueidlib.so" or p in ("assets/taibmcf.json", "assets/vdfmoer.json"):
            return "assets_shield_or_companion_blob"
        if p.startswith("assets/fonts") or p.startswith("assets/tinyfont") or p.startswith("assets/h5iconfont") or magic in ("truetype_font", "opentype_font", "woff2_font"):
            return "assets_fonts"
        if p.endswith(".pem") or p.endswith(".public"):
            return "assets_crypto_public_key"
        return "assets_config_media_other"
    if p.startswith("okhttp3/") or p.startswith("google/") or p.startswith("org/") or p.startswith("junit/") or p.startswith("vn/"):
        return "java_classpath_resource"
    return "root_misc"


records = []


def add_record(container_name, path, raw, compressed_bytes, comp_method, crc_val):
    sha = hashlib.sha256(raw).hexdigest()
    magic = detect_magic(raw, path)
    ent = shannon_entropy(raw)
    ext = Path(path).suffix.lower() or "(none)"
    subsys = classify_subsystem(container_name, path, magic)
    records.append({
        "container": container_name,
        "path": path,
        "extension": ext,
        "uncompressed_bytes": len(raw),
        "compressed_bytes": compressed_bytes,
        "compression": comp_method,
        "crc32": crc_val,
        "sha256": sha,
        "magic_type": magic,
        "shannon_entropy": ent,
        "subsystem": subsys,
    })


def process_zip(container_name: str, zf: zipfile.ZipFile):
    for info in zf.infolist():
        if info.is_dir():
            continue
        raw = zf.read(info.filename)
        comp = "STORED" if info.compress_type == zipfile.ZIP_STORED else ("DEFLATED" if info.compress_type == zipfile.ZIP_DEFLATED else f"TYPE_{info.compress_type}")
        add_record(container_name, info.filename, raw, info.compress_size, comp, f"{info.CRC:08x}")

        # Recurse into Griver .amr ZIP/TAR mini-program containers
        if container_name == "vn.com.vng.zalopay.apk" and info.filename.endswith(".amr") and raw.startswith(b"PK\x03\x04"):
            amr_name = f"amr:{Path(info.filename).name}"
            with zipfile.ZipFile(io.BytesIO(raw), "r") as z_amr:
                for ainfo in z_amr.infolist():
                    if ainfo.is_dir():
                        continue
                    araw = z_amr.read(ainfo.filename)
                    acomp = "STORED" if ainfo.compress_type == zipfile.ZIP_STORED else "DEFLATED"
                    add_record(amr_name, ainfo.filename, araw, ainfo.compress_size, acomp, f"{ainfo.CRC:08x}")
                    if ainfo.filename.endswith(".tar"):
                        with tarfile.open(fileobj=io.BytesIO(araw), mode="r:*") as tf:
                            for tmem in tf.getmembers():
                                if not tmem.isfile():
                                    continue
                                traw = tf.extractfile(tmem).read()
                                add_record(amr_name, f"{ainfo.filename}::{tmem.name}", traw, len(traw), "TAR_MEMBER", "00000000")


outer_bytes = OUTER_ZIP.read_bytes()
outer_sha = hashlib.sha256(outer_bytes).hexdigest()
with zipfile.ZipFile(OUTER_ZIP, "r") as z_outer:
    process_zip("outer_zip", z_outer)

xapk_path = next(CORPUS.glob("*.xapk"))
with zipfile.ZipFile(xapk_path, "r") as z_xapk:
    process_zip("inner_xapk", z_xapk)

for apk_path in sorted((CORPUS / "xapk").glob("*.apk")):
    with zipfile.ZipFile(apk_path, "r") as z_apk:
        process_zip(apk_path.name, z_apk)

nested_rn_zip = CORPUS / "apks/vn.com.vng.zalopay/assets/android.1.zip"
with zipfile.ZipFile(nested_rn_zip, "r") as z_rn:
    process_zip("assets/android.1.zip", z_rn)

csv_path = OUT_DIR / "full_file_census.csv.gz"
fieldnames = [
    "container", "path", "extension", "uncompressed_bytes", "compressed_bytes",
    "compression", "crc32", "sha256", "magic_type", "shannon_entropy", "subsystem",
]
with gzip.open(csv_path, "wt", newline="", encoding="utf-8") as f:
    writer = csv.DictWriter(f, fieldnames=fieldnames)
    writer.writeheader()
    writer.writerows(records)

apk_containers = {"vn.com.vng.zalopay.apk", "config.armeabi_v7a.apk", "config.xhdpi.apk", "config.en.apk", "config.vi.apk"}
by_container = defaultdict(lambda: {"file_count": 0, "uncompressed_bytes": 0, "compressed_bytes": 0})
by_magic_apk = defaultdict(lambda: {"file_count": 0, "uncompressed_bytes": 0})
by_subsystem_apk = defaultdict(lambda: {"file_count": 0, "uncompressed_bytes": 0, "compressed_bytes": 0})
by_compression_apk = Counter()
sha_map = defaultdict(list)

for r in records:
    c = by_container[r["container"]]
    c["file_count"] += 1
    c["uncompressed_bytes"] += r["uncompressed_bytes"]
    c["compressed_bytes"] += r["compressed_bytes"]
    if r["container"] in apk_containers:
        m = by_magic_apk[r["magic_type"]]
        m["file_count"] += 1
        m["uncompressed_bytes"] += r["uncompressed_bytes"]
        s = by_subsystem_apk[r["subsystem"]]
        s["file_count"] += 1
        s["uncompressed_bytes"] += r["uncompressed_bytes"]
        s["compressed_bytes"] += r["compressed_bytes"]
        by_compression_apk[r["compression"]] += 1
    sha_map[r["sha256"]].append({"container": r["container"], "path": r["path"], "bytes": r["uncompressed_bytes"]})

duplicates = [
    {
        "sha256": sha,
        "bytes": items[0]["bytes"],
        "occurrences": len(items),
        "wasted_bytes": items[0]["bytes"] * (len(items) - 1),
        "locations": items[:15],
    }
    for sha, items in sha_map.items()
    if len(items) > 1 and items[0]["bytes"] > 0
]
duplicates.sort(key=lambda x: x["wasted_bytes"], reverse=True)

apk_only = [r for r in records if r["container"] in apk_containers]
nested_only = [r for r in records if r["container"] not in apk_containers and r["container"] not in ("outer_zip", "inner_xapk")]

summary = {
    "outer_zip": {"filename": OUTER_ZIP.name, "bytes": len(outer_bytes), "sha256": outer_sha},
    "totals": {
        "all_indexed_member_files_including_nested": len(records),
        "xapk_direct_members": by_container["inner_xapk"]["file_count"],
        "apk_member_files_across_5_apks": len(apk_only),
        "apk_member_uncompressed_bytes": sum(r["uncompressed_bytes"] for r in apk_only),
        "apk_member_compressed_bytes": sum(r["compressed_bytes"] for r in apk_only),
        "nested_asset_archive_members": len(nested_only),
        "nested_asset_archive_uncompressed_bytes": sum(r["uncompressed_bytes"] for r in nested_only),
        "unique_sha256_hashes_all_layers": len(sha_map),
        "duplicate_content_groups": len(duplicates),
        "duplicate_redundant_bytes": sum(d["wasted_bytes"] for d in duplicates),
    },
    "by_container": dict(by_container),
    "by_magic_type_across_apks": dict(sorted(by_magic_apk.items(), key=lambda kv: kv[1]["uncompressed_bytes"], reverse=True)),
    "by_subsystem_across_apks": dict(sorted(by_subsystem_apk.items(), key=lambda kv: kv[1]["uncompressed_bytes"], reverse=True)),
    "compression_methods_across_apks": dict(by_compression_apk),
    "top_40_largest_apk_files": sorted(apk_only, key=lambda x: x["uncompressed_bytes"], reverse=True)[:40],
    "top_35_highest_entropy_apk_files_ge_512b": sorted(
        [r for r in apk_only if r["uncompressed_bytes"] >= 512],
        key=lambda x: (-x["shannon_entropy"], -x["uncompressed_bytes"]),
    )[:35],
    "top_30_duplicate_sha256_groups": duplicates[:30],
}

(OUT_DIR / "census_summary.json").write_text(json.dumps(summary, indent=2), encoding="utf-8")
print(f"Total indexed records (incl. nested android.1.zip + Griver .amr/.tar): {len(records)}")
for c_name, c_val in by_container.items():
    print(f"  {c_name:35s} files={c_val['file_count']:5d}  bytes={c_val['uncompressed_bytes']:12,d}")
