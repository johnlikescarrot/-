#!/usr/bin/env python3
"""
Stage 01 — Artifact seizure, inventory and manifest extraction.

Part of the ZaloPay 11.17.0 reverse-engineering pipeline. This script performs
the *forensic intake* phase of the study:

  * enumerates every file inside the delivered XAPK bundle (the user asked for
    ALL files, so the inventory is exhaustive and hash-bound),
  * computes MD5 / SHA-1 / SHA-256 digests for chain-of-custody,
  * parses the APKPure bundle manifest (`manifest.json`),
  * parses `AndroidManifest.xml` (binary AXML) of the base APK and every split
    configuration APK,
  * parses the **APK Signing Block** manually (no third-party dependency) to
    identify which signature schemes are present (v1 / v2 / v3 / v3.1 / v4),
  * parses **DEX headers** manually to enumerate the code payloads.

Everything is written as JSON under `re/evidence/` so that every downstream
stage (analysis, report, video) is fully reproducible and auditable.

Usage:
    python3 re/evidence/collect_evidence.py

No Java installation is required: AXML and DEX handling is done with androguard
(pure Python) plus hand-written parsers for the signing block and DEX headers.
"""

from __future__ import annotations

import hashlib
import json
import logging
import os
import re
import struct
import sys
import zipfile
from datetime import datetime, timezone

ROOT = os.path.dirname(os.path.dirname(os.path.dirname(os.path.abspath(__file__))))
WORK = os.path.join(ROOT, "work")
EXTRACT = os.path.join(WORK, "extract")
OUT = os.path.join(ROOT, "re", "evidence")

BUNDLE_ZIP = os.path.join(
    ROOT, "Zalopay-Thanh+toán+&+Tài+chính_11.17.0_APKPure.zip"
)

# APK Signing Block IDs (canonical AOSP / Play values). The ID is stored as a
# little-endian uint32 inside the block, so keys are ints.
# Sources: AOSP frameworks/base + tools/apksig, and the avast/apkverifier
# signingblock reference implementation.
SIG_BLOCK_IDS = {
    0x7109871A: "APK Signature Scheme v2",
    0xF05368C0: "APK Signature Scheme v3",
    0x1B93AD61: "APK Signature Scheme v3.1",
    0x6FF80038: "APK Signature Scheme v4",
    0x6DFF800D: "Source Stamp v2",
    0x2B09189E: "Source Stamp v1 (deprecated)",
    0x42726577: "Verity padding block",
    0x2146444E: "Google Play Frosting (Play-signed)",
    0x504B4453: "Dependency metadata (Play)",
    0x71777777: "Meituan metadata",
    0x9D6303F7: "Proof-of-rotation attribute",
}


# --------------------------------------------------------------------------- #
# helpers
# --------------------------------------------------------------------------- #
def sha256_file(path: str, chunk: int = 1 << 20) -> str:
    h = hashlib.sha256()
    with open(path, "rb") as fh:
        for block in iter(lambda: fh.read(chunk), b""):
            h.update(block)
    return h.hexdigest()


def digests(path: str) -> dict:
    md5, sha1 = hashlib.md5(), hashlib.sha1()
    with open(path, "rb") as fh:
        for block in iter(lambda: fh.read(1 << 20), b""):
            md5.update(block)
            sha1.update(block)
    return {"md5": md5.hexdigest(), "sha1": sha1.hexdigest(), "sha256": sha256_file(path)}


def ensure_out() -> None:
    os.makedirs(OUT, exist_ok=True)


def dump(name: str, payload: dict) -> str:
    path = os.path.join(OUT, name)
    with open(path, "w", encoding="utf-8") as fh:
        json.dump(
            payload, fh, indent=2, ensure_ascii=False, sort_keys=False,
            default=lambda o: sorted(o) if isinstance(o, (set, frozenset, tuple)) else str(o),
        )
        fh.write("\n")
    print(f"  wrote {path}")
    return path


def safe_call(obj, name: str, *args, default=None):
    """Call `obj.name(*args)` defensively — androguard 4.x renamed several
    accessors, so a missing method must not abort the whole evidence run."""
    try:
        value = getattr(obj, name)(*args)
        return default if value is None else value
    except Exception:  # noqa: BLE001
        return default


def as_str_list(value) -> list:
    """Normalise whatever androguard returned into a sorted list of strings.

    Permission/component getters can return strings, dicts or objects; this keeps
    the evidence JSON serialisable and deduplicated without crashing.
    """
    out = set()
    for item in value or []:
        if isinstance(item, str):
            out.add(item)
        elif isinstance(item, dict):
            for key in ("name", "value", "value_name", "title"):
                if isinstance(item.get(key), str):
                    out.add(item[key])
                    break
            else:
                out.add(str(item))
        elif hasattr(item, "get_name"):
            out.add(str(item.get_name()))
        else:
            out.add(str(item))
    return sorted(out)


# --------------------------------------------------------------------------- #
# APK Signing Block parser (hand-written, mirrors AOSP ApkSigningBlockUtils)
# --------------------------------------------------------------------------- #
def parse_signing_block(path: str) -> dict:
    """Locate and decode the APK Signing Block that sits immediately before the
    ZIP central directory. Returns the scheme IDs found plus raw offsets."""
    result = {
        "signing_block_present": False,
        "schemes_found": [],
        "offsets": {},
        "detail": [],
    }
    try:
        with open(path, "rb") as fh:
            data = fh.read()
    except OSError as exc:
        result["error"] = str(exc)
        return result

    eocd = data.rfind(b"PK\x05\x06")
    if eocd < 0:
        result["error"] = "EOCD not found"
        return result

    cd_offset = struct.unpack_from("<I", data, eocd + 16)[0]
    result["offsets"] = {"eocd": eocd, "central_directory_offset": cd_offset}

    if cd_offset < 24:
        result["error"] = "central directory offset too small"
        return result

    block_end = cd_offset
    magic = data[block_end - 16 : block_end]
    if magic != b"APK Sig Block 42":
        result["signing_block_present"] = False
        result["detail"].append(
            "No 'APK Sig Block 42' magic immediately preceding the central "
            "directory — v2/v3 signature block absent (v1-only APK or unsigned)."
        )
        return result

    block_size = struct.unpack_from("<Q", data, block_end - 24)[0]
    block_start = block_end - block_size - 8
    result["signing_block_present"] = True
    result["offsets"].update({"block_start": block_start, "block_end": block_end,
                              "block_size": block_size})

    pos = block_start + 8
    id_value_pairs = []
    while pos < block_end - 24:
        (pair_len,) = struct.unpack_from("<Q", data, pos)
        pair_id = struct.unpack_from("<I", data, pos + 8)[0]
        value = data[pos + 12 : pos + 8 + pair_len]
        if len(value) != pair_len - 4:
            break
        id_value_pairs.append((pair_id, value))
        pos += 8 + pair_len

    for pair_id, value in id_value_pairs:
        label = SIG_BLOCK_IDS.get(pair_id, f"unknown block (0x{pair_id:08x})")
        item = {"id": f"0x{pair_id:08x}", "name": label, "value_bytes": len(value)}
        # Digest the value so the evidence is bound to the artifact but does not
        # expose signature material in the report.
        item["sha256_of_value"] = hashlib.sha256(value).hexdigest()

        # Certificate digests (first certificate for v2/v3 blocks) — the digests
        # are public fingerprints, not private material.
        certs = extract_cert_digests(value)
        if certs:
            item["certificates"] = certs
        result["schemes_found"].append(label)
        result["detail"].append(item)

    return result


def extract_cert_digests(value: bytes) -> list:
    """Pull the signing certificate SHA-256 fingerprint out of a v2/v3 block.

    The signing-block value is a length-prefixed sequence of signers; each
    signer starts with a 4-byte length, then a 4-byte cert count, then the
    certificates. We only emit digests (public fingerprints).
    """
    try:
        (signer_len,) = struct.unpack_from("<I", value, 0)
        if signer_len < 8 or signer_len > len(value):
            return []
        signer = value[4 : 4 + signer_len]
        (cert_count,) = struct.unpack_from("<I", signer, 4)
        offset = 8
        out = []
        for _ in range(min(cert_count, 8)):
            if offset + 4 > len(signer):
                break
            (cert_len,) = struct.unpack_from("<I", signer, offset)
            offset += 4
            cert = signer[offset : offset + cert_len]
            offset += cert_len
            if len(cert) < 4:
                break
            out.append(
                {
                    "sha256": hashlib.sha256(cert).hexdigest(),
                    "sha1": hashlib.sha1(cert).hexdigest(),
                    "md5": hashlib.md5(cert).hexdigest(),
                    "size_bytes": len(cert),
                    "first_bytes": cert[:8].hex(),
                }
            )
        return out
    except Exception:
        return []


# --------------------------------------------------------------------------- #
# DEX header parser (hand-written)
# --------------------------------------------------------------------------- #
def parse_dex_header(path: str) -> dict:
    with open(path, "rb") as fh:
        magic = fh.read(8)
        if not magic.startswith(b"dex\n"):
            return {"valid": False, "magic": magic.hex()}
        fh.seek(0)
        blob = fh.read(0x70)
        # explicit offsets: checksum@0x08, SHA-1 signature@0x0C (20 bytes),
        # then 20 little-endian uint32 fields starting at 0x20
        u32 = lambda off: struct.unpack_from("<I", blob, off)[0]  # noqa: E731
        (checksum, file_size, header_size, endian_tag, link_size,
         link_off, map_off, string_ids_size, string_ids_off, type_ids_size,
         type_ids_off, proto_ids_size, proto_ids_off, field_ids_size,
         field_ids_off, method_ids_size, method_ids_off, class_defs_size,
         class_defs_off, data_size, data_off) = (
            u32(0x08), u32(0x20), u32(0x24), u32(0x28), u32(0x2C), u32(0x30),
            u32(0x34), u32(0x38), u32(0x3C), u32(0x40), u32(0x44), u32(0x48),
            u32(0x4C), u32(0x50), u32(0x54), u32(0x58), u32(0x5C), u32(0x60),
            u32(0x64), u32(0x68), u32(0x6C),
        )
        signature_hex = blob[0x0C:0x20].hex()
        return {
            "valid": True,
            "magic": magic.decode("latin1"),
            "checksum": f"0x{checksum:08x}",
            "sha1_signature": signature_hex,
            "file_size": file_size,
            "header_size": header_size,
            "endian_tag": "little" if endian_tag == 0x12345678 else f"0x{endian_tag:08x}",
            "link_size": link_size,
            "link_off": link_off,
            "map_off": map_off,
            "string_ids_count": string_ids_size,
            "string_ids_off": string_ids_off,
            "type_ids_count": type_ids_size,
            "type_ids_off": type_ids_off,
            "proto_ids_count": proto_ids_size,
            "proto_ids_off": proto_ids_off,
            "field_ids_count": field_ids_size,
            "field_ids_off": field_ids_off,
            "method_ids_count": method_ids_size,
            "method_ids_off": method_ids_off,
            "class_defs_count": class_defs_size,
            "class_defs_off": class_defs_off,
            "data_size": data_size,
            "data_off": data_off,
        }


# --------------------------------------------------------------------------- #
# stage 01
# --------------------------------------------------------------------------- #
def main() -> int:
    ensure_out()
    logging.getLogger("androguard").setLevel(logging.WARNING)
    run_ts = datetime.now(timezone.utc).isoformat(timespec="seconds")
    print(f"== stage 01: artifact seizure & inventory ({run_ts}) ==")

    # ---- 1. the delivered bundle ---------------------------------------- #
    print("[1/6] digesting delivered bundle ...")
    bundle = {
        "source_file": os.path.basename(BUNDLE_ZIP),
        "size_bytes": os.path.getsize(BUNDLE_ZIP),
        "collected_at_utc": run_ts,
        "digests": digests(BUNDLE_ZIP),
        "entries": [],
    }
    with zipfile.ZipFile(BUNDLE_ZIP) as zf:
        for info in zf.infolist():
            entry = {
                "path": info.filename,
                "size_bytes": info.file_size,
                "compressed_size_bytes": info.compress_size,
                "compression": info.compress_type,
                "date_time": f"{info.date_time[0]:04d}-{info.date_time[1]:02d}-{info.date_time[2]:02d}",
            }
            if not info.is_dir():
                with zf.open(info) as fh:
                    blob = fh.read()
                entry["digests"] = {
                    "md5": hashlib.md5(blob).hexdigest(),
                    "sha1": hashlib.sha1(blob).hexdigest(),
                    "sha256": hashlib.sha256(blob).hexdigest(),
                }
            bundle["entries"].append(entry)
    bundle["entry_count"] = len(bundle["entries"])
    dump("001-artifact-inventory.json", bundle)

    # ---- 2. XAPK bundle manifest ----------------------------------------- #
    print("[2/6] parsing XAPK manifest.json ...")
    xapk_manifest = None
    xm_path = os.path.join(EXTRACT, "xapk", "manifest.json")
    if os.path.exists(xm_path):
        with open(xm_path, encoding="utf-8") as fh:
            xapk_manifest = json.load(fh)
        dump("003-xapk-manifest.json", xapk_manifest)
    else:
        dump("003-xapk-manifest.json", {"error": "manifest.json not found"})

    # ---- 3. APK-level inventory (every file inside every APK) ----------- #
    print("[3/6] enumerating every file inside every APK ...")
    apks = [
        os.path.join(EXTRACT, "xapk", name)
        for name in sorted(os.listdir(os.path.join(EXTRACT, "xapk")))
        if name.endswith(".apk")
    ]
    apk_inventory = []
    for apk in apks:
        entry = {
            "path": os.path.relpath(apk, ROOT),
            "size_bytes": os.path.getsize(apk),
            "digests": digests(apk),
            "entry_count": 0,
            "entries": [],
            "dex_files": [],
        }
        with zipfile.ZipFile(apk) as zf:
            for info in zf.infolist():
                item = {
                    "path": info.filename,
                    "size_bytes": info.file_size,
                    "compressed_size_bytes": info.compress_size,
                    "compression": info.compress_type,
                }
                if info.filename.endswith(".dex"):
                    entry["dex_files"].append(item["path"])
                entry["entries"].append(item)
            entry["entry_count"] = len(entry["entries"])
            entry["total_uncompressed_bytes"] = sum(i["size_bytes"] for i in entry["entries"])
            # DEX header parse for each dex
            dex_summaries = []
            for name in entry["dex_files"]:
                inner = zf.read(name)
                tmp = os.path.join(WORK, "cache", "_dex_" + name.replace("/", "_"))
                os.makedirs(os.path.dirname(tmp), exist_ok=True)
                with open(tmp, "wb") as fh:
                    fh.write(inner)
                summary = parse_dex_header(tmp)
                summary["path"] = name
                summary["size_bytes"] = len(inner)
                summary["sha256"] = hashlib.sha256(inner).hexdigest()
                dex_summaries.append(summary)
            entry["dex_headers"] = dex_summaries
        apk_inventory.append(entry)
    dump("002-apk-file-inventory.json", {"apks": apk_inventory})

    # ---- 4. APK Signing Block ------------------------------------------- #
    print("[4/6] parsing APK Signing Block ...")
    signing = {"apks": {}}
    for entry in apk_inventory:
        signing["apks"][entry["path"]] = parse_signing_block(
            os.path.join(ROOT, entry["path"])
        )
    dump("004-apk-signing-block.json", signing)

    # ---- 5. AndroidManifest (AXML) via androguard ----------------------- #
    print("[5/6] decoding AndroidManifest.xml (binary AXML) ...")
    try:
        from androguard.core.apk import APK  # type: ignore

        manifest_out = {"apks": {}}
        for entry in apk_inventory:
            path = os.path.join(ROOT, entry["path"])
            rec = {"file": entry["path"], "size_bytes": entry["size_bytes"]}
            try:
                apk = APK(path)
                rec["package"] = safe_call(apk, "get_package")
                rec["application_name"] = safe_call(apk, "get_app_name")
                rec["application_icon"] = safe_call(apk, "get_app_icon")
                rec["version_name"] = safe_call(apk, "get_androidversion_name")
                rec["version_code"] = safe_call(apk, "get_androidversion_code")
                rec["min_sdk"] = safe_call(apk, "get_min_sdk_version")
                rec["target_sdk"] = safe_call(apk, "get_target_sdk_version")
                rec["effective_target_sdk"] = safe_call(apk, "get_effective_target_sdk_version")
                rec["max_sdk"] = safe_call(apk, "get_max_sdk_version")
                rec["main_activity"] = safe_call(apk, "get_main_activity")
                rec["main_activities"] = as_str_list(
                    safe_call(apk, "get_main_activities")
                )
                rec["is_signed"] = safe_call(apk, "is_signed")
                rec["is_signed_v2"] = safe_call(apk, "is_signed_v2")
                rec["is_signed_v3"] = safe_call(apk, "is_signed_v3")
                rec["signature_names"] = as_str_list(safe_call(apk, "get_signature_names"))
                rec["signature_name"] = safe_call(apk, "get_signature_name")
                rec["is_valid_zip"] = safe_call(apk, "is_valid_zip")
                rec["permissions"] = as_str_list(safe_call(apk, "get_permissions"))
                rec["declared_permissions"] = as_str_list(
                    safe_call(apk, "get_declared_permissions")
                )
                rec["requested_aosp_permissions"] = as_str_list(
                    safe_call(apk, "get_requested_aosp_permissions")
                )
                rec["activities"] = as_str_list(safe_call(apk, "get_activities"))
                rec["activity_aliases"] = as_str_list(
                    safe_call(apk, "get_activity_aliases")
                )
                rec["services"] = as_str_list(safe_call(apk, "get_services"))
                rec["receivers"] = as_str_list(safe_call(apk, "get_receivers"))
                rec["providers"] = as_str_list(safe_call(apk, "get_providers"))
                rec["libraries"] = as_str_list(safe_call(apk, "get_libraries"))
                rec["features"] = as_str_list(safe_call(apk, "get_features"))
                # v1 (JAR) signing artefacts live under META-INF
                try:
                    with zipfile.ZipFile(path) as zf:
                        meta_inf = [n for n in zf.namelist() if n.upper().startswith("META-INF/")]
                        rec["meta_inf_entries"] = sorted(meta_inf)[:60]
                        rec["v1_signature_files"] = sorted(
                            n for n in meta_inf
                            if n.upper().endswith((".RSA", ".DSA", ".EC", ".SF", ".MF"))
                        )
                except Exception as exc:  # noqa: BLE001
                    rec["meta_inf_error"] = str(exc)
                # certificate fingerprints (public material only). androguard 4.x
                # exposes the raw DER blobs directly, which is what we hash.
                certs = []
                for getter in ("get_certificates_der_v2", "get_certificates_der_v3",
                               "get_certificates_der_v31", "get_certificates_v1"):
                    for der in safe_call(apk, getter, default=[]) or []:
                        if isinstance(der, (bytes, bytearray)) and der:
                            certs.append({
                                "scheme": getter,
                                "sha256": hashlib.sha256(der).hexdigest(),
                                "sha1": hashlib.sha1(der).hexdigest(),
                                "size_bytes": len(der),
                            })
                        elif der is not None and hasattr(der, "dump"):
                            blob = der.dump()
                            certs.append({
                                "scheme": getter,
                                "sha256": hashlib.sha256(blob).hexdigest(),
                                "sha1": hashlib.sha1(blob).hexdigest(),
                                "size_bytes": len(blob),
                            })
                    if certs:
                        break
                rec["certificates"] = certs[:4]
                # decode the binary AXML into human-readable XML and derive the
                # <meta-data> key set straight from the text
                try:
                    raw = apk.get_android_manifest_axml().get_xml()
                    if isinstance(raw, str):
                        raw = raw.encode("utf-8")
                    rec["manifest_xml_bytes"] = len(raw)
                    rec["manifest_xml_sha256"] = hashlib.sha256(raw).hexdigest()
                    out_xml = os.path.join(
                        OUT, "manifest-xml-" + os.path.basename(entry["path"]) + ".xml"
                    )
                    with open(out_xml, "wb") as fh:
                        fh.write(raw)
                    rec["meta_data_keys"] = sorted(
                        set(re.findall(rb'android:name="([^"]+)"', raw))
                    )
                    rec["meta_data_keys"] = [k.decode("utf-8", "replace") for k in rec["meta_data_keys"]]
                except Exception as exc:  # noqa: BLE001
                    rec["manifest_xml_error"] = str(exc)
            except Exception as exc:  # noqa: BLE001
                rec["error"] = f"{type(exc).__name__}: {exc}"
            manifest_out["apks"][entry["path"]] = rec
        dump("005-android-manifest.json", manifest_out)
    except Exception as exc:  # noqa: BLE001
        dump("005-android-manifest.json", {"error": f"androguard unavailable: {exc}"})

    # ---- 6. summary ------------------------------------------------------ #
    print("[6/6] summary ...")
    # The *base* APK is the one that is not a split/configuration APK.
    base = next(
        (a for a in apk_inventory if not os.path.basename(a["path"]).startswith("config.")),
        apk_inventory[0],
    )
    summary = {
        "collected_at_utc": run_ts,
        "bundle": os.path.basename(BUNDLE_ZIP),
        "bundle_sha256": bundle["digests"]["sha256"],
        "apks_analysed": len(apk_inventory),
        "total_apk_files_enumerated": sum(a["entry_count"] for a in apk_inventory),
        "dex_count": sum(len(a["dex_files"]) for a in apk_inventory),
        "base_apk": base["path"],
        "base_apk_sha256": base["digests"]["sha256"],
    }
    if "apks" in signing and base["path"] in signing["apks"]:
        summary["base_apk_schemes"] = signing["apks"][base["path"]]["schemes_found"]
    dump("000-summary.json", summary)
    print("== stage 01 complete ==")
    return 0


if __name__ == "__main__":
    sys.exit(main())
