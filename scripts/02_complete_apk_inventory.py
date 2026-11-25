#!/usr/bin/env python3
"""
Complete 100% file inventory, entropy, magic-signature, deduplication,
and cryptographic signing audit for Zalopay 11.17.0 XAPK/ZIP bundle.
"""
import hashlib
import json
import math
import os
import struct
import tarfile
import zipfile
from collections import Counter, defaultdict
from pathlib import Path

from asn1crypto import cms, x509
from cryptography import x509 as crypto_x509
from cryptography.hazmat.primitives import hashes

ROOT = Path("/home/user/-")
ZIP_PATH = ROOT / "Zalopay-Thanh+toán+&+Tài+chính_11.17.0_APKPure.zip"
XAPK_DIR = ROOT / "tmp_work/xapk_contents"
APKS_DIR = ROOT / "tmp_work/apks"
NESTED_DIR = ROOT / "tmp_work/nested"
OUT_PATH = ROOT / "data/full_file_inventory.json"


def shannon_entropy(data: bytes) -> float:
    if not data:
        return 0.0
    counts = Counter(data)
    total = len(data)
    return round(-sum((c / total) * math.log2(c / total) for c in counts.values()), 4)


def sha256_bytes(data: bytes) -> str:
    return hashlib.sha256(data).hexdigest()


def sha256_file(path: Path) -> str:
    h = hashlib.sha256()
    with path.open("rb") as f:
        for chunk in iter(lambda: f.read(1024 * 1024), b""):
            h.update(chunk)
    return h.hexdigest()


def detect_magic(data: bytes, ext: str) -> str:
    if len(data) == 0:
        return "empty"
    if data.startswith(b"\x7fELF"):
        return "ELF"
    if data.startswith(b"dex\n"):
        return f"DEX ({data[:8].decode('ascii', 'replace').strip()})"
    if data.startswith(b"PK\x03\x04") or data.startswith(b"PK\x05\x06"):
        return "ZIP"
    if data.startswith(b"\x03\x00\x08\x00"):
        return "AXML (Binary Android XML)"
    if data.startswith(b"\x02\x00\x0c\x00"):
        return "ARSC (Compiled Android Resources)"
    if data.startswith(b"\x89PNG\r\n\x1a\n"):
        return "PNG"
    if data.startswith(b"RIFF") and data[8:12] == b"WEBP":
        return "WEBP"
    if data.startswith(b"\xff\xd8\xff"):
        return "JPEG"
    if data.startswith(b"GIF8"):
        return "GIF"
    if data.startswith(b"\x00\x01\x00\x00") or data.startswith(b"OTTO"):
        return "Font (TTF/OTF)"
    if data.startswith(b"RIVE"):
        return "RIVE"
    if data.startswith(b"\xca\xfe\xba\xbe"):
        return "Java Classfile"
    if data.startswith(b"pro\x00"):
        return "ART Baseline Profile (.prof)"
    if data.startswith(b"prm\x00"):
        return "ART Baseline Profile Metadata (.profm)"
    if data.startswith(b"ID3") or data[:2] in (b"\xff\xfb", b"\xff\xf3"):
        return "MP3"
    if data.startswith(b"\x1f\x8b"):
        return "GZIP"
    if len(data) >= 8 and data[4:8] == b"TFL3":
        return "TFLite FlatBuffer (TFL3)"
    if ext in (".json", ".js", ".html", ".txt", ".md", ".yaml", ".yml", ".svg", ".properties", ".version", ".proto", ".param"):
        return f"Text ({ext})"
    return f"Binary/Other (0x{data[:4].hex()})"


def categorize_file(rel_path: str, ext: str, magic: str) -> str:
    p = rel_path.lower()
    if p.endswith(".dex"):
        return "Dalvik Bytecode (DEX)"
    if p.startswith("lib/") and p.endswith(".so"):
        return "Native Libraries (ELF .so)"
    if p == "androidmanifest.xml" or p.startswith("res/") or p == "resources.arsc":
        return "Android Manifest & Compiled Resources"
    if p.startswith("meta-inf/") or p == "stamp-cert-sha256":
        return "Signing & Dependency Metadata (META-INF)"
    if any(k in p for k in ("mlkit", "tflite", ".tfl", ".ncnn.", ".binarypb", "custom_models", ".fb", ".lstm_model", ".conv_model", ".emd")):
        return "On-Device ML & Vision Models"
    if p.startswith("assets/griver") or p.endswith(".amr") or p.endswith(".js") or p.endswith(".html") or p == "assets/android.1.zip":
        return "Hybrid Web / React Native / Griver Mini-Apps"
    if p.startswith("assets/fonts/") or ext in (".ttf", ".otf"):
        return "Fonts & Typography"
    if p.startswith("assets/dexopt/") or p == "debugprobeskt.bin":
        return "ART Runtime & Kotlin Tooling"
    if p.startswith("assets/"):
        return "Application Assets & Configs"
    return "Other Root Package Files"


def parse_apk_signing_block(apk_path: Path) -> dict:
    """Inspect ZIP central directory + APK Signature Block v2/v3/v3.1 scheme IDs and v1 RSA cert."""
    info = {
        "v1_jar_signing": {},
        "apk_signing_block": {
            "present": False,
            "block_size_bytes": None,
            "scheme_ids_hex": [],
            "schemes_detected": [],
        },
    }
    data = apk_path.read_bytes()
    # Find End of Central Directory (0x06054b50)
    eocd_off = data.rfind(b"\x50\x4b\x05\x06")
    if eocd_off != -1 and len(data) >= eocd_off + 22:
        cd_offset = struct.unpack_from("<I", data, eocd_off + 16)[0]
        if cd_offset >= 32 and data[cd_offset - 16 : cd_offset] == b"APK Sig Block 42":
            size_end = struct.unpack_from("<Q", data, cd_offset - 24)[0]
            block_start = cd_offset - 8 - size_end
            if block_start >= 0:
                size_start = struct.unpack_from("<Q", data, block_start)[0]
                if size_start == size_end:
                    info["apk_signing_block"]["present"] = True
                    info["apk_signing_block"]["block_size_bytes"] = size_end + 8
                    pos = block_start + 8
                    limit = cd_offset - 24
                    scheme_map = {
                        0x7109871A: "APK Signature Scheme v2",
                        0xF05368C0: "APK Signature Scheme v3",
                        0x1B93AD61: "APK Signature Scheme v3.1",
                        0x42726577: "Verity Padding Block",
                        0x504B4453: "Dependency Info Block (Play Metadata)",
                        0x6DFF800D: "Source Stamp Block v1",
                        0x9D6303F7: "Source Stamp Block v2",
                    }
                    while pos + 12 <= limit:
                        pair_len = struct.unpack_from("<Q", data, pos)[0]
                        pair_id = struct.unpack_from("<I", data, pos + 8)[0]
                        hex_id = f"0x{pair_id:08x}"
                        info["apk_signing_block"]["scheme_ids_hex"].append(hex_id)
                        info["apk_signing_block"]["schemes_detected"].append(
                            scheme_map.get(pair_id, f"Unknown Block ({hex_id})")
                        )
                        pos += 8 + pair_len

    # Parse v1 META-INF/*.RSA or *.DSA
    with zipfile.ZipFile(apk_path) as z:
        for name in z.namelist():
            if name.startswith("META-INF/") and name.upper().endswith((".RSA", ".DSA", ".EC")):
                raw_sig = z.read(name)
                try:
                    ci = cms.ContentInfo.load(raw_sig)
                    sd = ci["content"]
                    certs = []
                    for c in sd["certificates"]:
                        if c.name == "certificate":
                            der = c.chosen.dump()
                            xc = crypto_x509.load_der_x509_certificate(der)
                            certs.append({
                                "subject": xc.subject.rfc4514_string(),
                                "issuer": xc.issuer.rfc4514_string(),
                                "serial_number_hex": hex(xc.serial_number),
                                "not_valid_before": xc.not_valid_before_utc.isoformat(),
                                "not_valid_after": xc.not_valid_after_utc.isoformat(),
                                "signature_algorithm": xc.signature_algorithm_oid._name,
                                "public_key_type": type(xc.public_key()).__name__,
                                "public_key_size_bits": getattr(xc.public_key(), "key_size", None),
                                "sha256_fingerprint": xc.fingerprint(hashes.SHA256()).hex(),
                                "sha1_fingerprint": xc.fingerprint(hashes.SHA1()).hex(),
                            })
                    info["v1_jar_signing"] = {
                        "signature_file": name,
                        "signer_count": len(sd["signer_infos"]),
                        "certificates": certs,
                    }
                except Exception as e:
                    info["v1_jar_signing"] = {"signature_file": name, "error": str(e)}
    return info


def main():
    os.makedirs(NESTED_DIR, exist_ok=True)
    xapk_files = list(XAPK_DIR.glob("*.xapk"))
    # Actually the .xapk is in tmp_work/zip_contents
    xapk_path = next((ROOT / "tmp_work/zip_contents").glob("*.xapk"))
    manifest_json = json.loads((XAPK_DIR / "manifest.json").read_text())

    top_level = {
        "outer_zip": {
            "filename": ZIP_PATH.name,
            "size_bytes": ZIP_PATH.stat().st_size,
            "sha256": sha256_file(ZIP_PATH),
        },
        "xapk_container": {
            "filename": xapk_path.name,
            "size_bytes": xapk_path.stat().st_size,
            "sha256": sha256_file(xapk_path),
            "xapk_manifest": manifest_json,
            "top_entries": [],
        },
    }

    with zipfile.ZipFile(xapk_path) as zx:
        for zi in zx.infolist():
            raw = zx.read(zi.filename)
            top_level["xapk_container"]["top_entries"].append({
                "filename": zi.filename,
                "uncompressed_bytes": zi.file_size,
                "compressed_bytes": zi.compress_size,
                "compress_type": zi.compress_type,
                "crc32": f"0x{zi.CRC:08x}",
                "sha256": sha256_bytes(raw),
            })

    apk_summaries = {}
    all_entries = []
    hash_locations = defaultdict(list)
    category_stats = defaultdict(lambda: {"count": 0, "uncompressed_bytes": 0, "compressed_bytes": 0})
    ext_stats = defaultdict(lambda: {"count": 0, "uncompressed_bytes": 0, "compressed_bytes": 0})
    magic_stats = defaultdict(lambda: {"count": 0, "uncompressed_bytes": 0})

    for apk_path in sorted(XAPK_DIR.glob("*.apk")):
        apk_name = apk_path.name
        sig_info = parse_apk_signing_block(apk_path)
        entries_count = 0
        total_uncomp = 0
        total_comp = 0
        apk_cats = defaultdict(lambda: {"count": 0, "uncompressed_bytes": 0})
        entropies = []

        with zipfile.ZipFile(apk_path) as za:
            for zi in za.infolist():
                if zi.is_dir():
                    continue
                raw = za.read(zi.filename)
                sha = sha256_bytes(raw)
                ent = shannon_entropy(raw)
                ext = Path(zi.filename).suffix.lower() or "(no_ext)"
                magic = detect_magic(raw, ext)
                cat = categorize_file(zi.filename, ext, magic)

                entries_count += 1
                total_uncomp += zi.file_size
                total_comp += zi.compress_size
                entropies.append(ent)

                apk_cats[cat]["count"] += 1
                apk_cats[cat]["uncompressed_bytes"] += zi.file_size

                category_stats[cat]["count"] += 1
                category_stats[cat]["uncompressed_bytes"] += zi.file_size
                category_stats[cat]["compressed_bytes"] += zi.compress_size

                ext_stats[ext]["count"] += 1
                ext_stats[ext]["uncompressed_bytes"] += zi.file_size
                ext_stats[ext]["compressed_bytes"] += zi.compress_size

                magic_stats[magic]["count"] += 1
                magic_stats[magic]["uncompressed_bytes"] += zi.file_size

                hash_locations[sha].append({
                    "apk": apk_name,
                    "path": zi.filename,
                    "bytes": zi.file_size,
                })

                all_entries.append({
                    "apk": apk_name,
                    "path": zi.filename,
                    "bytes": zi.file_size,
                    "compressed_bytes": zi.compress_size,
                    "compress_method": "STORED" if zi.compress_type == 0 else "DEFLATED" if zi.compress_type == 8 else str(zi.compress_type),
                    "sha256": sha,
                    "entropy": ent,
                    "ext": ext,
                    "magic": magic,
                    "category": cat,
                })

        stamp_path = APKS_DIR / apk_path.stem / "stamp-cert-sha256"
        stamp_hex = stamp_path.read_bytes().hex() if stamp_path.exists() else None

        apk_summaries[apk_name] = {
            "apk_size_bytes": apk_path.stat().st_size,
            "apk_sha256": sha256_file(apk_path),
            "entry_count": entries_count,
            "total_uncompressed_bytes": total_uncomp,
            "total_compressed_bytes": total_comp,
            "compression_ratio": round(total_comp / total_uncomp, 4) if total_uncomp else 0.0,
            "avg_entropy": round(sum(entropies) / len(entropies), 4) if entropies else 0.0,
            "high_entropy_files_ge_7_5": sum(1 for e in entropies if e >= 7.5),
            "stamp_cert_sha256_hex": stamp_hex,
            "signing": sig_info,
            "categories": dict(sorted(apk_cats.items(), key=lambda x: -x[1]["uncompressed_bytes"])),
        }

    # Nested containers: assets/android.1.zip & assets/Griver/*.amr -> *.tar
    nested_containers = {}
    rn_zip = APKS_DIR / "vn.com.vng.zalopay/assets/android.1.zip"
    rn_out = NESTED_DIR / "android_1_zip"
    rn_out.mkdir(parents=True, exist_ok=True)
    with zipfile.ZipFile(rn_zip) as zr:
        zr.extractall(rn_out)
        rn_files = [i for i in zr.infolist() if not i.is_dir()]
        rn_ext = Counter(Path(i.filename).suffix.lower() or "(no_ext)" for i in rn_files)
        nested_containers["assets/android.1.zip"] = {
            "type": "React Native Bundle Archive",
            "entry_count": len(rn_files),
            "total_uncompressed_bytes": sum(i.file_size for i in rn_files),
            "extension_counts": dict(rn_ext),
            "js_bundles": [
                {"path": i.filename, "bytes": i.file_size, "sha256": sha256_bytes(zr.read(i.filename))}
                for i in rn_files if i.filename.endswith((".jsbundle", ".js", ".hbc"))
            ],
        }

    for amr in sorted((APKS_DIR / "vn.com.vng.zalopay/assets/Griver").glob("*.amr")):
        amr_out = NESTED_DIR / f"griver_{amr.stem}"
        amr_out.mkdir(parents=True, exist_ok=True)
        with zipfile.ZipFile(amr) as za:
            za.extractall(amr_out)
            amr_info = {"entries": [], "tar_contents": []}
            for zi in za.infolist():
                if zi.is_dir():
                    continue
                raw = za.read(zi.filename)
                amr_info["entries"].append({
                    "name": zi.filename,
                    "bytes": zi.file_size,
                    "sha256": sha256_bytes(raw),
                })
                if zi.filename.endswith(".tar"):
                    tar_out = amr_out / "tar_extracted"
                    tar_out.mkdir(parents=True, exist_ok=True)
                    with tarfile.open(amr_out / zi.filename) as tf:
                        tf.extractall(tar_out)
                        for m in tf.getmembers():
                            if m.isfile():
                                amr_info["tar_contents"].append({
                                    "name": m.name,
                                    "bytes": m.size,
                                })
            nested_containers[f"assets/Griver/{amr.name}"] = amr_info

    # Duplicate content across APK entries (same SHA-256)
    duplicates = []
    for sha, locs in hash_locations.items():
        if len(locs) > 1:
            duplicates.append({
                "sha256": sha,
                "count": len(locs),
                "bytes_each": locs[0]["bytes"],
                "wasted_bytes": locs[0]["bytes"] * (len(locs) - 1),
                "locations": locs,
            })
    duplicates.sort(key=lambda d: -d["wasted_bytes"])

    # Largest 40 files across all APKs
    largest_files = sorted(all_entries, key=lambda x: -x["bytes"])[:40]
    # Highest entropy files (>= 7.9, size >= 1000)
    highest_entropy = sorted(
        [x for x in all_entries if x["bytes"] >= 1024 and x["entropy"] >= 7.85],
        key=lambda x: (-x["entropy"], -x["bytes"]),
    )[:30]

    # Save lightweight per-file manifest separately so main summary JSON stays crisp
    manifest_dump_path = ROOT / "data/all_entries_catalog.json"
    manifest_dump_path.write_text(json.dumps({
        "total_entries_across_all_apks": len(all_entries),
        "entries": all_entries,
    }, indent=2))

    result = {
        "top_level": top_level,
        "aggregate_totals": {
            "split_apk_count": len(apk_summaries),
            "total_apk_entries": len(all_entries),
            "unique_content_sha256_count": len(hash_locations),
            "duplicate_sha256_groups": len(duplicates),
            "duplicate_wasted_bytes": sum(d["wasted_bytes"] for d in duplicates),
            "total_uncompressed_bytes": sum(a["total_uncompressed_bytes"] for a in apk_summaries.values()),
            "total_compressed_bytes": sum(a["total_compressed_bytes"] for a in apk_summaries.values()),
            "nested_container_files_count": (
                nested_containers["assets/android.1.zip"]["entry_count"]
                + sum(len(v["tar_contents"]) + len(v["entries"]) for k, v in nested_containers.items() if k.endswith(".amr"))
            ),
        },
        "apk_summaries": apk_summaries,
        "category_breakdown": dict(sorted(category_stats.items(), key=lambda x: -x[1]["uncompressed_bytes"])),
        "extension_breakdown_top30": dict(sorted(ext_stats.items(), key=lambda x: -x[1]["uncompressed_bytes"])[:30]),
        "magic_breakdown": dict(sorted(magic_stats.items(), key=lambda x: -x[1]["uncompressed_bytes"])),
        "nested_containers": nested_containers,
        "top_duplicates_by_wasted_bytes": duplicates[:25],
        "largest_40_entries": largest_files,
        "highest_entropy_entries": highest_entropy,
    }
    OUT_PATH.write_text(json.dumps(result, indent=2, ensure_ascii=False))
    print(f"Wrote {OUT_PATH} and {manifest_dump_path}")
    print(json.dumps(result["aggregate_totals"], indent=2))
    print("\n=== TOP DUPLICATES ===")
    for d in duplicates[:10]:
        print(f"  {d['bytes_each']} B x{d['count']} (sha={d['sha256'][:12]}): {[l['path'] for l in d['locations'][:4]]}")


if __name__ == "__main__":
    main()
