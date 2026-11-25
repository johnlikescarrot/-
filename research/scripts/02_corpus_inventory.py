#!/usr/bin/env python3
"""
100% Corpus Inventory & Cryptographic Profiling across:
- Outer ZIP container
- XAPK archive entries (7 entries)
- All 5 APKs inside the XAPK (every single ZIP member: 6,464 files total)
Computes SHA-256, compressed & uncompressed size, compression method, CRC-32,
magic bytes classification, Shannon entropy, and subsystem category.
"""
import hashlib
import json
import math
import os
import zipfile
from collections import Counter, defaultdict
from pathlib import Path

ROOT = Path("/home/user/-")
OUTER_ZIP = ROOT / "Zalopay-Thanh+toán+&+Tài+chính_11.17.0_APKPure.zip"
XAPK_DIR = Path("/tmp/zalopay_re/xapk")
OUT_JSON = ROOT / "research/data/corpus_inventory.json"


def sha256_bytes(data: bytes) -> str:
    return hashlib.sha256(data).hexdigest()


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


def detect_magic(data: bytes, filename: str) -> str:
    if len(data) == 0:
        return "empty"
    if data[:4] == b"\x7fELF":
        return "ELF"
    if data[:4] == b"dex\n":
        return f"DEX ({data[:8].decode('ascii', 'replace').strip()})"
    if data[:4] == b"PK\x03\x04" or data[:4] == b"PK\x05\x06":
        return "ZIP/JAR/APK"
    if data[:4] == b"\x03\x00\x08\x00":
        return "Android Binary XML (AXML)"
    if data[:4] == b"\x02\x00\x0c\x00":
        return "Android Resource Table (ARSC)"
    if data[:4] == b"RIFF" and data[8:12] == b"WEBP":
        return "WebP"
    if data[:8] == b"\x89PNG\r\n\x1a\n":
        return "PNG"
    if data[:3] == b"\xff\xd8\xff":
        return "JPEG"
    if data[:6] in (b"GIF87a", b"GIF89a"):
        return "GIF"
    if data[:4] == b"RIVE":
        return "Rive Animation (.riv)"
    if data[:4] in (b"\x00\x01\x00\x00", b"OTTO", b"true", b"ttcf"):
        return "TrueType/OpenType Font"
    if data[4:8] == b"TFL3":
        return "TensorFlow Lite FlatBuffer (TFL3)"
    if data[:4] == b"pro\x00" or data[:4] == b"prm\x00":
        return "ART Baseline Profile"
    if data[:2] == b"0\x82":
        return "DER PKCS#7 / X.509 ASN.1"
    if filename.endswith(".xml"):
        return "XML (compiled/text)"
    if filename.endswith(".json"):
        return "JSON"
    if filename.endswith(".js"):
        return "JavaScript"
    if filename.endswith(".html") or filename.endswith(".htm"):
        return "HTML"
    if filename.endswith(".amr"):
        return "Alipay/Griver AMR Archive (ZIP)"
    if filename.endswith(".version"):
        return "AndroidX/Library Version Marker"
    if filename.endswith(".properties"):
        return "Java/Maven Properties"
    if filename.endswith(".kotlin_builtins"):
        return "Kotlin Builtins Metadata"
    if filename.endswith(".proto"):
        return "Protobuf Definition/Descriptor"
    if data.lstrip()[:1] in (b"{", b"["):
        try:
            json.loads(data.decode("utf-8"))
            return "JSON (verified)"
        except Exception:
            pass
    # Printable ASCII/UTF-8 check
    sample = data[:512]
    printable = sum(1 for b in sample if 32 <= b < 127 or b in (9, 10, 13))
    if len(sample) > 0 and printable / len(sample) > 0.92:
        return "UTF-8/ASCII Text"
    return f"Binary (magic={data[:4].hex()})"


def classify_subsystem(apk_name: str, entry_name: str, magic: str) -> str:
    if entry_name == "AndroidManifest.xml":
        return "manifest"
    if entry_name == "resources.arsc":
        return "resource_table"
    if entry_name.startswith("classes") and entry_name.endswith(".dex"):
        return "dex_bytecode"
    if entry_name.startswith("lib/") and entry_name.endswith(".so"):
        return "native_elf_so"
    if entry_name.startswith("META-INF/"):
        if entry_name.endswith(".version"):
            return "meta_inf_version_marker"
        if entry_name.endswith((".SF", ".RSA", ".DSA", ".EC", "MANIFEST.MF")):
            return "meta_inf_signature"
        if entry_name.startswith("META-INF/services/"):
            return "meta_inf_spi_service"
        return "meta_inf_other"
    if entry_name.startswith("assets/"):
        if "Griver" in entry_name or "containererrorpage" in entry_name or entry_name.endswith("jsbridge.js"):
            return "assets_miniapp_griver_web"
        if "mlkit" in entry_name or entry_name.endswith((".tflite", ".ncnn.bin", ".ncnn.param")):
            return "assets_ml_models"
        if entry_name.endswith(".dex"):
            return "assets_dynamic_dex"
        if entry_name.endswith(".ttf") or entry_name.endswith(".otf"):
            return "assets_fonts"
        if entry_name.endswith(".json") or entry_name.startswith("assets/config/"):
            return "assets_config_json"
        if entry_name.startswith("assets/dexopt/"):
            return "assets_dexopt_profile"
        return "assets_other_binary"
    if entry_name.startswith("res/"):
        parts = entry_name.split("/")
        res_group = parts[1].split("-")[0] if len(parts) > 1 else "res"
        return f"res_{res_group}"
    if entry_name.endswith(".properties"):
        return "root_maven_properties"
    if entry_name.startswith("kotlin/") or entry_name == "DebugProbesKt.bin" or entry_name == "kotlin-tooling-metadata.json":
        return "kotlin_runtime_metadata"
    if entry_name.startswith(("org/", "google/", "okhttp3/", "junit/", "vn/")) or entry_name in ("stamp-cert-sha256", "client_analytics.proto", "getbanklist_zpm_success.json", "LICENSE-junit.txt"):
        return "root_bundled_resources"
    return "other"


def main():
    outer_bytes = OUTER_ZIP.read_bytes()
    outer_info = {
        "filename": OUTER_ZIP.name,
        "bytes": len(outer_bytes),
        "sha256": sha256_bytes(outer_bytes),
        "entropy": shannon_entropy(outer_bytes[:1024 * 1024]),  # 1MB sample + note
    }

    with zipfile.ZipFile(OUTER_ZIP, "r") as ozf:
        outer_members = [
            {
                "name": zi.filename,
                "file_size": zi.file_size,
                "compress_size": zi.compress_size,
                "compress_type": zi.compress_type,
                "crc32": f"{zi.CRC:08x}",
            }
            for zi in ozf.infolist()
        ]

    xapk_path = next(Path("/tmp/zalopay_re/zip_out").glob("*.xapk"))
    xapk_bytes = xapk_path.read_bytes()
    xapk_info = {
        "filename": xapk_path.name,
        "bytes": len(xapk_bytes),
        "sha256": sha256_bytes(xapk_bytes),
        "members": [],
    }
    with zipfile.ZipFile(xapk_path, "r") as xzf:
        for zi in xzf.infolist():
            data = xzf.read(zi.filename)
            xapk_info["members"].append({
                "name": zi.filename,
                "file_size": zi.file_size,
                "compress_size": zi.compress_size,
                "compress_type": zi.compress_type,
                "crc32": f"{zi.CRC:08x}",
                "sha256": sha256_bytes(data),
                "entropy": shannon_entropy(data),
                "magic": detect_magic(data, zi.filename),
            })

    apk_summaries = {}
    all_entries = []
    subsystem_stats = defaultdict(lambda: {"count": 0, "uncompressed_bytes": 0, "compressed_bytes": 0, "entropies": []})
    magic_stats = Counter()

    for apk_path in sorted(XAPK_DIR.glob("*.apk")):
        apk_name = apk_path.name
        with zipfile.ZipFile(apk_path, "r") as azf:
            infos = azf.infolist()
            apk_uncomp = 0
            apk_comp = 0
            apk_subsystems = Counter()
            for zi in infos:
                data = azf.read(zi.filename)
                ent = shannon_entropy(data)
                magic = detect_magic(data, zi.filename)
                sub = classify_subsystem(apk_name, zi.filename, magic)
                h = sha256_bytes(data)

                apk_uncomp += zi.file_size
                apk_comp += zi.compress_size
                apk_subsystems[sub] += 1

                subsystem_stats[sub]["count"] += 1
                subsystem_stats[sub]["uncompressed_bytes"] += zi.file_size
                subsystem_stats[sub]["compressed_bytes"] += zi.compress_size
                subsystem_stats[sub]["entropies"].append(ent)
                magic_stats[magic] += 1

                all_entries.append({
                    "apk": apk_name,
                    "path": zi.filename,
                    "size": zi.file_size,
                    "compressed": zi.compress_size,
                    "method": "STORE" if zi.compress_type == 0 else f"DEFLATE({zi.compress_type})",
                    "crc32": f"{zi.CRC:08x}",
                    "sha256": h,
                    "entropy": ent,
                    "magic": magic,
                    "subsystem": sub,
                })

            apk_summaries[apk_name] = {
                "apk_file_bytes": apk_path.stat().st_size,
                "apk_sha256": sha256_bytes(apk_path.read_bytes()),
                "entry_count": len(infos),
                "total_uncompressed_bytes": apk_uncomp,
                "total_compressed_bytes": apk_comp,
                "subsystem_counts": dict(sorted(apk_subsystems.items(), key=lambda x: -x[1])),
            }

    subsystem_summary = {}
    for sub, st in sorted(subsystem_stats.items(), key=lambda x: -x[1]["uncompressed_bytes"]):
        ents = st["entropies"]
        subsystem_summary[sub] = {
            "count": st["count"],
            "uncompressed_bytes": st["uncompressed_bytes"],
            "compressed_bytes": st["compressed_bytes"],
            "pct_uncompressed": round(100.0 * st["uncompressed_bytes"] / sum(v["uncompressed_bytes"] for v in subsystem_stats.values()), 3),
            "avg_entropy": round(sum(ents) / len(ents), 4) if ents else 0.0,
            "max_entropy": max(ents) if ents else 0.0,
            "min_entropy": min(ents) if ents else 0.0,
        }

    # Check high-entropy (>7.8) non-media files (potential encrypted/packed payloads)
    high_entropy_non_media = [
        e for e in all_entries
        if e["entropy"] >= 7.8 and e["magic"] not in ("PNG", "WebP", "JPEG", "GIF") and not e["path"].endswith((".png", ".webp", ".jpg", ".jpeg"))
    ]
    high_entropy_non_media.sort(key=lambda x: -x["size"])

    result = {
        "outer_zip": outer_info,
        "outer_zip_members": outer_members,
        "xapk": xapk_info,
        "total_apk_inner_files": len(all_entries),
        "total_apk_uncompressed_bytes": sum(e["size"] for e in all_entries),
        "total_apk_compressed_bytes": sum(e["compressed"] for e in all_entries),
        "apk_summaries": apk_summaries,
        "subsystem_summary": subsystem_summary,
        "magic_type_counts": dict(magic_stats.most_common()),
        "high_entropy_non_media_top50": high_entropy_non_media[:50],
        "files": all_entries,
    }

    OUT_JSON.parent.mkdir(parents=True, exist_ok=True)
    OUT_JSON.write_text(json.dumps(result, indent=2), encoding="utf-8")
    print(f"[CORPUS] Audited {len(all_entries)} inner APK files + {len(xapk_info['members'])} XAPK entries + outer ZIP -> {OUT_JSON}")
    print(f"[CORPUS] Total uncompressed inner APK payload: {result['total_apk_uncompressed_bytes']:,} bytes")


if __name__ == "__main__":
    main()
