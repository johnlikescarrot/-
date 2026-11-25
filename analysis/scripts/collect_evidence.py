#!/usr/bin/env python3
"""Collect reproducible static-evidence for an Android XAPK bundle.

Everything written by this script is derived only from the bytes of the input
archive.  No network access, no device, no execution of target code.

Usage:
  python3 analysis/scripts/collect_evidence.py --xapk <file.xapk> --out analysis/evidence

Dependencies: androguard 4.1.3, pyelftools (see analysis/requirements.txt).
"""
from __future__ import annotations

import argparse
import collections
import hashlib
import io
import json
import os
import re
import struct
import sys
import zipfile
from datetime import datetime, timezone
from pathlib import Path

try:
    from loguru import logger

    logger.remove()
except ImportError:  # pragma: no cover
    pass

from androguard.core.apk import APK
from elftools.elf.elffile import ELFFile

ANDROID_NS = "{http://schemas.android.com/apk/res/android}"

URL_RE = re.compile(rb"https?://[A-Za-z0-9][A-Za-z0-9._~:/?#\[\]@!$&'()*+,;=%-]{5,180}")
HOST_RE = re.compile(rb"^[A-Za-z0-9][A-Za-z0-9._-]{2,120}$")


def sha256_bytes(data: bytes) -> str:
    return hashlib.sha256(data).hexdigest()


def sha256_file(path: Path) -> str:
    h = hashlib.sha256()
    with path.open("rb") as fh:
        for chunk in iter(lambda: fh.read(1024 * 1024), b""):
            h.update(chunk)
    return h.hexdigest()


def dump(name: str, out: Path, payload: object) -> None:
    (out / name).write_text(
        json.dumps(payload, indent=2, ensure_ascii=False, sort_keys=True) + "\n",
        encoding="utf-8",
    )
    print(f"[evidence] {name}: {os.path.getsize(out / name):,} bytes", flush=True)


# --------------------------------------------------------------------------
# Container: outer ZIP, XAPK, split APKs and every member
# --------------------------------------------------------------------------
def collect_container(outer_zip: Path, xapk: Path, out: Path) -> dict:
    outer = zipfile.ZipFile(outer_zip)
    bundle = zipfile.ZipFile(xapk)
    entries = []
    for info in bundle.infolist():
        entries.append(
            {
                "name": info.filename,
                "uncompressed_bytes": info.file_size,
                "compressed_bytes": info.compress_size,
                "compression": "deflate" if info.compress_type == zipfile.ZIP_DEFLATED else "stored",
                "crc32": f"{info.CRC:08x}",
                "date_time": list(info.date_time),
                "sha256": sha256_bytes(bundle.read(info)),
            }
        )
    manifest = json.loads(bundle.read("manifest.json").decode("utf-8"))
    payload = {
        "source": {
            "outer_zip": outer_zip.name,
            "outer_zip_sha256": sha256_file(outer_zip),
            "outer_zip_bytes": outer_zip.stat().st_size,
            "outer_members": [i.filename for i in outer.infolist()],
            "xapk_sha256": sha256_file(xapk),
            "xapk_bytes": xapk.stat().st_size,
        },
        "xapk_version": manifest.get("xapk_version"),
        "package": manifest.get("package_name"),
        "version_name": manifest.get("version_name"),
        "version_code": manifest.get("version_code"),
        "min_sdk": manifest.get("min_sdk_version"),
        "target_sdk": manifest.get("target_sdk_version"),
        "declared_total_size": manifest.get("total_size"),
        "split_configs": manifest.get("split_configs", []),
        "permissions_declared_in_manifest_json": manifest.get("permissions", []),
        "members": entries,
        "member_count": len(entries),
        "total_uncompressed_bytes": sum(e["uncompressed_bytes"] for e in entries),
        "integrity": {
            "crc_errors": bundle.testzip(),
            "all_members_verified": bundle.testzip() is None,
        },
    }
    dump("container.json", out, payload)
    return payload


# --------------------------------------------------------------------------
# Manifest: binary XML decoded, components, flags, metadata
# --------------------------------------------------------------------------
def _attr(el, key):
    return el.get(ANDROID_NS + key)


def _component_records(root, tag: str) -> list[dict]:
    records = []
    for el in root.iter(tag):
        filters = el.findall("intent-filter")
        records.append(
            {
                "name": _attr(el, "name"),
                "exported": _attr(el, "exported"),
                "permission": _attr(el, "permission"),
                "enabled": _attr(el, "enabled"),
                "process": _attr(el, "process"),
                "intent_filter_count": len(filters),
                "action_names": sorted(
                    set(str(_attr(a, "name")) for a in el.iter("action") if _attr(a, "name"))
                ),
                "authorities": [str(_attr(p, "authorities")) for p in el.iter("provider") if _attr(p, "authorities")],
            }
        )
    return records


def collect_manifest(apk_path: Path, out: Path) -> dict:
    apk = APK(str(apk_path))
    root = apk.get_android_manifest_xml()
    app = root.find("application")
    application = {}
    if app is not None:
        for key in (
            "name", "debuggable", "allowBackup", "networkSecurityConfig",
            "usesCleartextTraffic", "extractNativeLibs", "requestLegacyExternalStorage",
            "largeHeap", "persistent", "fullBackupContent", "dataExtractionRules",
            "resizeableActivity", "theme", "label", "icon", "appCategory",
        ):
            value = _attr(app, key)
            if value is not None:
                application[key] = str(value)

    components = {
        "activity": _component_records(root, "activity"),
        "activity-alias": _component_records(root, "activity-alias"),
        "service": _component_records(root, "service"),
        "receiver": _component_records(root, "receiver"),
        "provider": _component_records(root, "provider"),
    }
    exported_without_permission = []
    for kind, records in components.items():
        for rec in records:
            if rec["exported"] == "true" and not rec["permission"]:
                exported_without_permission.append({"kind": kind, "name": rec["name"]})

    permissions = []
    for el in root.iter("uses-permission"):
        permissions.append({"name": _attr(el, "name"), "maxSdkVersion": _attr(el, "maxSdkVersion")})

    features = [str(_attr(e, "name")) for e in root.iter("uses-feature") if _attr(e, "name")]
    libraries = [str(_attr(e, "name")) for e in root.iter("uses-library") if _attr(e, "name")]
    queries = sorted(
        set(str(_attr(p, "name")) for e in root.iter("queries") for p in e.iter("package") if _attr(p, "name"))
    )
    meta = []
    for parent_name in ("application", "activity", "service", "receiver", "provider"):
        for parent in root.iter(parent_name):
            for el in parent.findall("meta-data"):
                meta.append({"scope": parent_name, "name": _attr(el, "name")})

    payload = {
        "package": apk.get_package(),
        "version_name": apk.get_androidversion_name(),
        "version_code": apk.get_androidversion_code(),
        "min_sdk": apk.get_min_sdk_version(),
        "target_sdk": apk.get_target_sdk_version(),
        "compile_sdk": root.get(ANDROID_NS + "compileSdkVersion"),
        "application": application,
        "permissions": permissions,
        "permission_count": len(permissions),
        "declared_permissions": [str(_attr(e, "name")) for e in root.iter("permission") if _attr(e, "name")],
        "features": features,
        "libraries": libraries,
        "queries": queries,
        "meta_data_names": sorted(set(m["name"] for m in meta if m["name"])),
        "component_counts": {k: len(v) for k, v in components.items()},
        "components": components,
        "exported_without_permission_count": len(exported_without_permission),
        "exported_without_permission_sample": exported_without_permission[:40],
        "main_activity": str(apk.get_main_activity() or ""),
    }
    dump("manifest.json", out, payload)
    return payload


# --------------------------------------------------------------------------
# Signing identity.  Only public certificate metadata is retained.
# --------------------------------------------------------------------------
def _cert_summary(cert) -> dict:
    result: dict = {}
    for key, attr in (
        ("subject", "subject"), ("issuer", "issuer"), ("serial_number", "serial_number"),
        ("sha256", "sha256"), ("valid_from", "valid_from"), ("valid_until", "valid_until"),
    ):
        try:
            value = getattr(cert, attr)
            if hasattr(value, "human_friendly"):
                value = value.human_friendly
            elif isinstance(value, bytes):
                value = value.hex()
            else:
                value = str(value)
            result[key] = value
        except Exception as exc:
            result[key] = f"<unavailable: {type(exc).__name__}>"
    return result


def collect_signature(apk_path: Path, out: Path) -> dict:
    apk = APK(str(apk_path))
    payload = {
        "signed_v1": bool(apk.is_signed_v1()),
        "signed_v2": bool(apk.is_signed_v2()),
        "signed_v3": bool(apk.is_signed_v3()),
        "certificates_v1": [_cert_summary(c) for c in (apk.get_certificates_v1() or [])],
        "certificates_v2": [_cert_summary(c) for c in (apk.get_certificates_v2() or [])],
        "certificates_v3": [_cert_summary(c) for c in (apk.get_certificates_v3() or [])],
    }
    try:
        payload["v1_verification"] = bool(apk.verify_signature())
    except Exception as exc:
        payload["v1_verification"] = f"unavailable: {type(exc).__name__}"
    with zipfile.ZipFile(apk_path) as z:
        if "stamp-cert-sha256" in z.namelist():
            payload["stamp_cert_sha256"] = z.read("stamp-cert-sha256").decode("utf-8", "replace").strip()
        payload["meta_inf_entries"] = [
            n for n in z.namelist() if n.startswith("META-INF/") and n.upper().endswith((".RSA", ".SF", ".MF"))
        ][:20]
    dump("signature.json", out, payload)
    return payload


# --------------------------------------------------------------------------
# DEX: header-level quantitative facts without executing or fully decompiling
# --------------------------------------------------------------------------
def _dex_header(data: bytes) -> dict | None:
    if data[:3] != b"dex" or len(data) < 112:
        return None
    u4 = lambda off: struct.unpack_from("<I", data, off)[0]
    return {
        "magic": data[:8].decode("ascii", "replace"),
        "declared_file_size": u4(32),
        "header_size": u4(36),
        "endian_tag": hex(u4(40)),
        "string_ids": u4(56),
        "type_ids": u4(64),
        "prototype_ids": u4(72),
        "field_ids": u4(80),
        "method_ids": u4(88),
        "class_defs": u4(96),
        "data_bytes": u4(104),
    }


def collect_dex(apk_path: Path, out: Path) -> dict:
    dex_files, hosts, trademarkers = [], collections.Counter(), collections.Counter()
    keywords = {
        "okhttp": b"okhttp3", "retrofit": b"retrofit2", "kotlin": b"kotlin/",
        "firebase": b"com/google/firebase", "gms": b"com/google/android/gms",
        "coroutines": b"kotlinx/coroutines", "hilt": b"dagger/hilt",
        "protobuf": b"com/google/protobuf", "webview": b"android/webkit/WebView",
        "mlkit": b"com/google/mlkit", "room": b"androidx/room",
        "compose": b"androidx/compose", "facebook": b"com/facebook",
        "zalo_sdk": b"com/zing/zalo", "flutter": b"io/flutter",
        "react_native": b"com/facebook/react",
    }
    with zipfile.ZipFile(apk_path) as z:
        for info in z.infolist():
            if not (info.filename.startswith("classes") and info.filename.endswith(".dex")):
                continue
            data = z.read(info)
            header = _dex_header(data)
            header.update(
                {
                    "name": info.filename,
                    "actual_bytes": len(data),
                    "compressed_bytes": info.compress_size,
                    "sha256": sha256_bytes(data),
                    "header_consistent": header["declared_file_size"] == len(data),
                }
            )
            dex_files.append(header)
            for match in URL_RE.findall(data):
                host = match.split(b"://", 1)[1].split(b"/", 1)[0].split(b":", 1)[0]
                if HOST_RE.fullmatch(host):
                    hosts[host.decode("ascii", "replace").lower()] += 1
            for label, needle in keywords.items():
                count = data.count(needle)
                if count:
                    trademarkers[label] += count

    totals = {
        key: sum(d[key] for d in dex_files)
        for key in ("string_ids", "type_ids", "prototype_ids", "field_ids", "method_ids", "class_defs", "actual_bytes")
    }
    payload = {
        "dex_count": len(dex_files),
        "dex_files": dex_files,
        "totals": totals,
        "uri_host_frequencies": [{"host": h, "count": c} for h, c in hosts.most_common(60)],
        "distinct_uri_hosts": len(hosts),
        "framework_markers": dict(trademarkers.most_common()),
    }
    dump("dex.json", out, payload)
    return payload


# --------------------------------------------------------------------------
# Native libraries: ELF class/machine, dependencies, build ids
# --------------------------------------------------------------------------
def iter_inner_apks(xapk: Path):
    """Yield (member name, ZipFile) for every APK stored inside the XAPK."""
    with zipfile.ZipFile(xapk) as bundle:
        for name in bundle.namelist():
            if name.endswith(".apk"):
                yield name, zipfile.ZipFile(io.BytesIO(bundle.read(name)))


def collect_native(xapk: Path, out: Path) -> dict:
    libs = []
    for apk_name, z in iter_inner_apks(xapk):
        for info in z.infolist():
            if not info.filename.startswith("lib/") or not info.filename.endswith(".so"):
                continue
            data = z.read(info)
            record = {
                "apk": apk_name,
                "name": info.filename,
                "uncompressed_bytes": info.file_size,
                "compressed_bytes": info.compress_size,
                "sha256": sha256_bytes(data),
            }
            try:
                elf = ELFFile(io.BytesIO(data))
                header = elf.header
                record.update(
                    {
                        "elf_class": header["e_ident"]["EI_CLASS"],
                        "machine": header["e_machine"],
                        "elf_type": header["e_type"],
                        "entry": hex(header["e_entry"]),
                        "section_count": header["e_shnum"],
                    }
                )
                needed = []
                if elf.has_dynamic_section():
                    for tag in elf.get_section_by_name(".dynamic").iter_tags():
                        if tag.entry.d_tag == "DT_NEEDED":
                            needed.append(tag.needed)
                record["dt_needed"] = needed
                build_id = None
                for section in elf.iter_sections():
                    if "GNU" in section.name and "build-id" in section.name:
                        build_id = section.data().hex()
                        break
                record["gnu_build_id"] = build_id
            except Exception as exc:
                record["elf_error"] = f"{type(exc).__name__}: {exc}"
            libs.append(record)
    payload = {
        "library_count": len(libs),
        "total_uncompressed_bytes": sum(x["uncompressed_bytes"] for x in libs),
        "architectures": sorted(set(x["name"].split("/")[1] for x in libs if "/" in x["name"])),
        "libraries": libs,
        "common_dependencies": dict(
            collections.Counter(dep for x in libs for dep in x.get("dt_needed", [])).most_common(30)
        ),
    }
    dump("native.json", out, payload)
    return payload


# --------------------------------------------------------------------------
# Assets, property manifests, resource XML and network-security configuration
# --------------------------------------------------------------------------
def collect_assets(xapk: Path, out: Path) -> dict:
    assets, properties, resource_xml, base_names = [], [], [], []
    for apk_name, z in iter_inner_apks(xapk):
        base_names.append(apk_name)
        for info in z.infolist():
            name = info.filename
            if info.is_dir():
                continue
            if name.lower().endswith(".properties"):
                properties.append(f"{apk_name}:{name.split('/')[-1]}")
            if name.startswith("assets/"):
                assets.append(
                    {
                        "apk": apk_name,
                        "name": name,
                        "bytes": info.file_size,
                        "sha256": sha256_bytes(z.read(info)) if info.file_size < 4_000_000 else None,
                    }
                )
            if name.startswith("res/xml/") and name.lower().endswith(".xml"):
                resource_xml.append(f"{apk_name}:{name}")
    payload = {
        "asset_count": len(assets),
        "asset_bytes": sum(a["bytes"] for a in assets),
        "assets": assets,
        "library_property_files": sorted(set(properties)),
        "property_file_count": len(set(properties)),
        "resource_xml_files": resource_xml[:60],
        "apk_members": base_names,
    }
    dump("assets.json", out, payload)
    return payload


def build_summary(out: Path, pieces: dict) -> None:
    container, manifest, signature, dex, native, assets = (
        pieces["container"], pieces["manifest"], pieces["signature"],
        pieces["dex"], pieces["native"], pieces["assets"],
    )
    summary = {
        "generated_utc": datetime.now(timezone.utc).isoformat(),
        "subject": {
            "package": container["package"],
            "version": container["version_name"],
            "version_code": container["version_code"],
            "min_sdk": container["min_sdk"],
            "target_sdk": container["target_sdk"],
            "sha256": container["source"]["xapk_sha256"],
        },
        "bundle": {
            "xapk_bytes": container["source"]["xapk_bytes"],
            "uncompressed_bytes": container["total_uncompressed_bytes"],
            "member_count": container["member_count"],
            "split_apks": container["split_configs"],
            "integrity_ok": container["integrity"]["all_members_verified"],
        },
        "manifest": {
            "permission_count": manifest["permission_count"],
            "component_counts": manifest["component_counts"],
            "exported_without_permission_count": manifest["exported_without_permission_count"],
            "application": manifest["application"],
            "queries_count": len(manifest["queries"]),
        },
        "code": {
            "dex_count": dex["dex_count"],
            "totals": dex["totals"],
            "distinct_uri_hosts": dex["distinct_uri_hosts"],
            "framework_markers": dex["framework_markers"],
        },
        "native": {
            "library_count": native["library_count"],
            "architectures": native["architectures"],
            "total_uncompressed_bytes": native["total_uncompressed_bytes"],
        },
        "assets": {
            "asset_count": assets["asset_count"],
            "property_file_count": assets["property_file_count"],
            "library_property_files": assets["library_property_files"],
        },
        "signing": {
            "signed_v1": signature["signed_v1"],
            "signed_v2": signature["signed_v2"],
            "signed_v3": signature["signed_v3"],
            "v1_verification": signature["v1_verification"],
            "certificate_subjects": sorted(
                set(c.get("subject", "") for key in ("certificates_v1", "certificates_v2", "certificates_v3")
                    for c in signature[key])
            ),
        },
    }
    dump("summary.json", out, summary)


def main() -> int:
    parser = argparse.ArgumentParser()
    parser.add_argument("--xapk", required=True, type=Path)
    parser.add_argument("--outer-zip", type=Path, default=None)
    parser.add_argument("--out", type=Path, default=Path("analysis/evidence"))
    parser.add_argument("--base-apk", type=Path, default=None)
    args = parser.parse_args()
    args.out.mkdir(parents=True, exist_ok=True)
    if args.base_apk is None:
        nested = args.xapk.parent / "extracted" / "vn.com.vng.zalopay.apk"
        adjacent = args.xapk.parent / "vn.com.vng.zalopay.apk"
        args.base_apk = nested if nested.exists() else adjacent
    if args.outer_zip is None:
        candidates = list(args.xapk.parent.glob("*.zip")) + list(Path.cwd().glob("*.zip"))
        if not candidates:
            parser.error("outer zip not found; provide --outer-zip")
        args.outer_zip = candidates[0]

    pieces = {
        "container": collect_container(args.outer_zip, args.xapk, args.out),
        "manifest": collect_manifest(args.base_apk, args.out),
        "signature": collect_signature(args.base_apk, args.out),
        "dex": collect_dex(args.base_apk, args.out),
        "native": collect_native(args.xapk, args.out),
        "assets": collect_assets(args.xapk, args.out),
    }
    build_summary(args.out, pieces)
    return 0


if __name__ == "__main__":
    sys.exit(main())
