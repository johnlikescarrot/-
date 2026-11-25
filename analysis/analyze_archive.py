#!/usr/bin/env python3
"""
Empirical Structural & Architectural Analysis Engine for Android XAPK Bundles.
Performs 100% file-coverage static packaging, manifest, multi-DEX structural,
ELF binary-ecosystem, and resource/asset telemetry across all split APKs.

Ethical & Scientific Scope:
- Strictly static structural metadata & empirical software-engineering telemetry.
- Performs NO Dalvik bytecode decompilation or proprietary source reconstruction.
- Performs NO native machine-code disassembly or security-bypass analysis.
- Performs NO credential/secret/API-endpoint extraction or vulnerability auditing.
"""
from __future__ import annotations

import hashlib
import io
import json
import os
import struct
import sys
import zipfile
from collections import Counter, defaultdict
from datetime import datetime, timezone
from pathlib import Path
from typing import Any, Dict, List, Tuple

sys.path.insert(0, str(Path(__file__).resolve().parent.parent))

from analysis.parsers.arsc_and_assets_parser import (
    detect_magic_type,
    inspect_apk_signing_block,
    inspect_lottie_json,
    parse_arsc_structural,
    parse_font_family,
)
from analysis.parsers.axml_parser import XmlElement, parse_axml
from analysis.parsers.dex_parser import classify_namespace, parse_dex_structural
from analysis.parsers.elf_parser import parse_elf_structural

ARCHIVE_PATH = Path("Zalopay-Thanh+toán+&+Tài+chính_11.17.0_APKPure.zip")
DATA_DIR = Path("data")

DANGEROUS_PERMISSIONS = {
    "android.permission.POST_NOTIFICATIONS",
    "android.permission.READ_CONTACTS",
    "android.permission.WRITE_CONTACTS",
    "android.permission.READ_PHONE_STATE",
    "android.permission.WRITE_EXTERNAL_STORAGE",
    "android.permission.READ_EXTERNAL_STORAGE",
    "android.permission.ACCESS_COARSE_LOCATION",
    "android.permission.ACCESS_FINE_LOCATION",
    "android.permission.WRITE_CALENDAR",
    "android.permission.READ_CALENDAR",
    "android.permission.RECORD_AUDIO",
    "android.permission.CAMERA",
    "android.permission.READ_MEDIA_AUDIO",
    "android.permission.READ_MEDIA_IMAGES",
    "android.permission.READ_MEDIA_VIDEO",
}

SPECIAL_OR_PRIVILEGED_PERMISSIONS = {
    "android.settings.action.MANAGE_OVERLAY_PERMISSION",
    "android.permission.SYSTEM_ALERT_WINDOW",
    "android.permission.WRITE_SETTINGS",
    "android.permission.QUERY_ALL_PACKAGES",
    "android.permission.DETECT_SCREEN_CAPTURE",
}


def classify_permission_domain(perm: str) -> Tuple[str, str]:
    """Return (protection_class, functional_domain) for a declared Android permission."""
    p = perm.upper()
    if perm in DANGEROUS_PERMISSIONS:
        prot = "Runtime (Dangerous)"
    elif perm in SPECIAL_OR_PRIVILEGED_PERMISSIONS:
        prot = "Special / Restricted"
    elif perm.startswith("android.permission."):
        prot = "Normal / Platform"
    elif perm.startswith(("com.google.", "com.android.vending")):
        prot = "Google / Play Ecosystem"
    elif perm.startswith(("com.samsung.", "com.huawei.", "com.bbk.", "com.xiaomi.", "com.oppo.")):
        prot = "OEM Vendor Integration"
    elif perm.startswith(("vn.com.vng.", "com.zing.")):
        prot = "First-Party / VNG Ecosystem"
    else:
        prot = "Third-Party / Custom"

    if any(k in p for k in ("BIOMETRIC", "FINGERPRINT", "SCREEN_CAPTURE")):
        domain = "Identity, Biometrics & Anti-Screen-Capture"
    elif any(k in p for k in ("NFC", "BILLING")):
        domain = "Payments, NFC & Commerce"
    elif any(k in p for k in ("CAMERA", "RECORD_AUDIO", "MODIFY_AUDIO")):
        domain = "eKYC, QR Scanning & Audio/Voice"
    elif any(k in p for k in ("LOCATION", "MAPS")):
        domain = "Geolocation & Merchant Discovery"
    elif any(k in p for k in ("CONTACTS", "CALENDAR")):
        domain = "Social P2P Transfers & Bill Reminders"
    elif any(k in p for k in ("ADSERVICES", "AD_ID", "INSTALL_REFERRER", "FEATURE_SURVEY")):
        domain = "Attribution, Privacy Sandbox & Analytics"
    elif any(k in p for k in ("INTERNET", "WIFI", "NETWORK", "PHONE_STATE")):
        domain = "Networking & Device Connectivity"
    elif any(k in p for k in ("NOTIFICATION", "C2DM", "WAKE_LOCK", "BOOT_COMPLETED", "FOREGROUND_SERVICE", "VIBRATE")):
        domain = "Messaging, Push & Background Lifecycle"
    elif any(k in p for k in ("STORAGE", "READ_MEDIA")):
        domain = "Media & Local Storage"
    elif any(k in p for k in ("SHORTCUT", "LAUNCHER", "OVERLAY", "REORDER_TASKS", "WRITE_SETTINGS", "READ_SETTINGS")):
        domain = "Launcher, Shortcuts & System UI"
    elif any(k in p for k in ("ZALO", "ZALOPAY", "QUERY_ALL_PACKAGES", "COMMON_DATA")):
        domain = "Super-App Ecosystem & Inter-App Discovery"
    else:
        domain = "Other Platform Capabilities"

    return prot, domain


def compute_zip_data_offset(apk_bytes: bytes, info: zipfile.ZipInfo) -> int:
    """Compute exact byte offset of entry payload inside an APK ZIP stream."""
    lh_off = info.header_offset
    if lh_off + 30 > len(apk_bytes):
        return -1
    fname_len, extra_len = struct.unpack_from("<HH", apk_bytes, lh_off + 26)
    return lh_off + 30 + fname_len + extra_len


def summarize_manifest(root: XmlElement, apk_name: str) -> Dict[str, Any]:
    """Extract architectural metadata from a parsed AndroidManifest.xml element tree."""
    m_attrs = root.attrs
    out: Dict[str, Any] = {
        "apk": apk_name,
        "package": m_attrs.get("package", ""),
        "split": m_attrs.get("split", ""),
        "version_code": m_attrs.get("versionCode"),
        "version_name": m_attrs.get("versionName", ""),
        "compile_sdk_version": m_attrs.get("compileSdkVersion"),
        "compile_sdk_codename": m_attrs.get("compileSdkVersionCodename", ""),
        "is_split_required": m_attrs.get("isSplitRequired"),
        "min_sdk": None,
        "target_sdk": None,
        "permissions": [],
        "permission_by_protection": Counter(),
        "permission_by_domain": Counter(),
        "custom_permissions_defined": [],
        "uses_features": [],
        "uses_libraries": [],
        "queries_packages_count": 0,
        "queries_intents_count": 0,
        "queries_providers_count": 0,
        "application_flags": {},
        "components_total": Counter(),
        "components_by_domain": defaultdict(Counter),
        "meta_data_count": 0,
        "foreground_service_types": Counter(),
        "processes_declared": Counter(),
    }

    for child in root.children:
        tag = child.tag
        if tag == "uses-sdk":
            out["min_sdk"] = child.attrs.get("minSdkVersion")
            out["target_sdk"] = child.attrs.get("targetSdkVersion")
        elif tag in ("uses-permission", "uses-permission-sdk-23"):
            pname = str(child.attrs.get("name", ""))
            if pname:
                prot, dom = classify_permission_domain(pname)
                out["permissions"].append({"name": pname, "protection": prot, "domain": dom})
                out["permission_by_protection"][prot] += 1
                out["permission_by_domain"][dom] += 1
        elif tag == "permission":
            pname = str(child.attrs.get("name", ""))
            if pname:
                out["custom_permissions_defined"].append(pname)
        elif tag == "uses-feature":
            fname = str(child.attrs.get("name", child.attrs.get("glEsVersion", "")))
            req = child.attrs.get("required", True)
            out["uses_features"].append({"name": fname, "required": bool(req)})
        elif tag == "queries":
            for q in child.children:
                if q.tag == "package":
                    out["queries_packages_count"] += 1
                elif q.tag == "intent":
                    out["queries_intents_count"] += 1
                elif q.tag == "provider":
                    out["queries_providers_count"] += 1
        elif tag == "application":
            for k in (
                "extractNativeLibs",
                "largeHeap",
                "hardwareAccelerated",
                "supportsRtl",
                "allowBackup",
                "appComponentFactory",
                "requestLegacyExternalStorage",
                "hasCode",
            ):
                if k in child.attrs:
                    out["application_flags"][k] = child.attrs[k]

            for ac in child.children:
                atag = ac.tag
                if atag in ("activity", "activity-alias", "service", "receiver", "provider"):
                    out["components_total"][atag] += 1
                    cname = str(ac.attrs.get("name", ""))
                    pkg = ".".join(cname.split(".")[:-1]) if "." in cname else cname
                    dom = classify_namespace(pkg)
                    out["components_by_domain"][atag][dom] += 1
                    proc = ac.attrs.get("process")
                    if proc:
                        out["processes_declared"][str(proc)] += 1
                    if atag == "service" and "foregroundServiceType" in ac.attrs:
                        out["foreground_service_types"][str(ac.attrs["foregroundServiceType"])] += 1
                elif atag == "meta-data":
                    out["meta_data_count"] += 1
                elif atag in ("uses-library", "uses-native-library"):
                    out["uses_libraries"].append({
                        "tag": atag,
                        "name": str(ac.attrs.get("name", "")),
                        "required": bool(ac.attrs.get("required", True)),
                    })

    out["permission_by_protection"] = dict(out["permission_by_protection"])
    out["permission_by_domain"] = dict(out["permission_by_domain"])
    out["components_total"] = dict(out["components_total"])
    out["components_by_domain"] = {k: dict(v) for k, v in out["components_by_domain"].items()}
    out["foreground_service_types"] = dict(out["foreground_service_types"])
    out["processes_declared"] = dict(out["processes_declared"])
    return out


def analyze_all() -> None:
    if not ARCHIVE_PATH.exists():
        raise FileNotFoundError(f"Missing target archive: {ARCHIVE_PATH}")

    DATA_DIR.mkdir(parents=True, exist_ok=True)
    outer_size = ARCHIVE_PATH.stat().st_size
    outer_sha256 = hashlib.sha256(ARCHIVE_PATH.read_bytes()).hexdigest()

    print(f"[1/6] Opening outer archive: {ARCHIVE_PATH} ({outer_size:,} bytes)")
    with zipfile.ZipFile(ARCHIVE_PATH, "r") as outer_zip:
        outer_entries = outer_zip.infolist()
        xapk_info = outer_entries[0]
        xapk_bytes = outer_zip.read(xapk_info.filename)
        xapk_sha256 = hashlib.sha256(xapk_bytes).hexdigest()

    xapk_summary: Dict[str, Any] = {
        "outer_zip_filename": ARCHIVE_PATH.name,
        "outer_zip_size_bytes": outer_size,
        "outer_zip_sha256": outer_sha256,
        "xapk_filename": xapk_info.filename,
        "xapk_compressed_bytes": xapk_info.compress_size,
        "xapk_uncompressed_bytes": xapk_info.file_size,
        "xapk_sha256": xapk_sha256,
        "xapk_entries": [],
        "xapk_manifest": {},
    }

    apks_data: Dict[str, bytes] = {}
    with zipfile.ZipFile(io.BytesIO(xapk_bytes), "r") as xapk_zip:
        for info in xapk_zip.infolist():
            raw = xapk_zip.read(info.filename)
            entry_rec = {
                "filename": info.filename,
                "compress_type": "STORED" if info.compress_type == 0 else f"METHOD_{info.compress_type}",
                "compressed_bytes": info.compress_size,
                "uncompressed_bytes": info.file_size,
                "sha256": hashlib.sha256(raw).hexdigest(),
                "is_apk": info.filename.endswith(".apk"),
            }
            xapk_summary["xapk_entries"].append(entry_rec)
            if info.filename == "manifest.json":
                xapk_summary["xapk_manifest"] = json.loads(raw.decode("utf-8"))
            elif info.filename.endswith(".apk"):
                apks_data[info.filename] = raw

    print(f"[2/6] Discovered {len(apks_data)} split APKs inside XAPK v2 container.")

    # Aggregators covering 100% of files across all APKs
    apk_summaries: List[Dict[str, Any]] = []
    manifests: List[Dict[str, Any]] = []
    dex_shards: List[Dict[str, Any]] = []
    elf_libraries: List[Dict[str, Any]] = []
    arsc_tables: List[Dict[str, Any]] = []
    fonts_catalog: List[Dict[str, Any]] = []
    lottie_catalog: List[Dict[str, Any]] = []
    ml_assets: List[Dict[str, Any]] = []
    web_hybrid_assets: List[Dict[str, Any]] = []
    library_versions: Dict[str, str] = {}
    kotlin_modules: List[str] = []

    global_magic_counts: Counter = Counter()
    global_magic_uncomp: Counter = Counter()
    global_magic_comp: Counter = Counter()
    global_topdir_counts: Counter = Counter()
    global_topdir_uncomp: Counter = Counter()
    global_topdir_comp: Counter = Counter()
    global_ext_counts: Counter = Counter()
    global_ext_uncomp: Counter = Counter()
    global_compression_counts: Counter = Counter()
    global_compression_uncomp: Counter = Counter()
    global_compression_comp: Counter = Counter()

    # SHA-256 deduplication across ALL files in ALL split APKs
    hash_occurrences: Dict[str, List[Tuple[str, str, int, int]]] = defaultdict(list)

    # Image format analysis
    image_stats: Dict[str, Dict[str, int]] = defaultdict(lambda: {"count": 0, "uncompressed_bytes": 0, "compressed_bytes": 0})

    # Assets directory breakdown
    assets_subdir_counts: Counter = Counter()
    assets_subdir_bytes: Counter = Counter()

    # Res directory breakdown
    res_subdir_counts: Counter = Counter()
    res_subdir_bytes: Counter = Counter()

    total_files_scanned = 0
    total_uncomp_bytes = 0
    total_comp_bytes = 0

    for apk_name, apk_bytes in sorted(apks_data.items(), key=lambda x: -len(x[1])):
        print(f"    -> Scanning {apk_name} ({len(apk_bytes):,} bytes)...")
        apk_file_count = 0
        apk_uncomp = 0
        apk_comp = 0
        apk_stored_count = 0
        apk_deflated_count = 0
        apk_topdirs_bytes: Counter = Counter()
        apk_magic_bytes: Counter = Counter()
        has_v1_sig = False

        with zipfile.ZipFile(io.BytesIO(apk_bytes), "r") as zf:
            infos = zf.infolist()
            for info in infos:
                if info.is_dir():
                    continue
                fname = info.filename
                raw = zf.read(fname)
                u_sz = len(raw)
                c_sz = info.compress_size
                is_comp = info.compress_type != 0
                comp_label = "DEFLATED" if info.compress_type == zipfile.ZIP_DEFLATED else ("STORED" if info.compress_type == 0 else f"METHOD_{info.compress_type}")

                apk_file_count += 1
                apk_uncomp += u_sz
                apk_comp += c_sz
                if is_comp:
                    apk_deflated_count += 1
                else:
                    apk_stored_count += 1

                total_files_scanned += 1
                total_uncomp_bytes += u_sz
                total_comp_bytes += c_sz

                topdir = fname.split("/", 1)[0] + "/" if "/" in fname else "<root>"
                ext = ("." + fname.rsplit(".", 1)[-1].lower()) if "." in fname.rsplit("/", 1)[-1] else "<none>"
                if fname.lower().endswith(".9.png"):
                    ext = ".9.png"
                magic_type = detect_magic_type(fname, raw[:32])

                apk_topdirs_bytes[topdir] += u_sz
                apk_magic_bytes[magic_type] += u_sz

                global_magic_counts[magic_type] += 1
                global_magic_uncomp[magic_type] += u_sz
                global_magic_comp[magic_type] += c_sz

                global_topdir_counts[topdir] += 1
                global_topdir_uncomp[topdir] += u_sz
                global_topdir_comp[topdir] += c_sz

                global_ext_counts[ext] += 1
                global_ext_uncomp[ext] += u_sz

                global_compression_counts[comp_label] += 1
                global_compression_uncomp[comp_label] += u_sz
                global_compression_comp[comp_label] += c_sz

                sha = hashlib.sha256(raw).hexdigest()
                hash_occurrences[sha].append((apk_name, fname, u_sz, c_sz))

                if fname.startswith("META-INF/") and fname.upper().endswith((".RSA", ".DSA", ".EC", ".SF")):
                    has_v1_sig = True

                # Library version telemetry from META-INF/*.version & *.kotlin_module
                if fname.startswith("META-INF/") and fname.endswith(".version") and u_sz < 128:
                    lib_key = fname[len("META-INF/") : -len(".version")]
                    library_versions[lib_key] = raw.decode("utf-8", errors="ignore").strip()
                elif fname.startswith("META-INF/") and fname.endswith(".kotlin_module"):
                    kotlin_modules.append(fname[len("META-INF/") : -len(".kotlin_module")])

                # AndroidManifest.xml
                if fname == "AndroidManifest.xml":
                    root_elem = parse_axml(raw)
                    if root_elem is not None:
                        manifests.append(summarize_manifest(root_elem, apk_name))

                # Multi-DEX structural telemetry
                if fname.endswith(".dex") and raw.startswith(b"dex\n"):
                    dex_rec = parse_dex_structural(raw, f"{apk_name}:{fname}")
                    dex_rec["apk"] = apk_name
                    dex_rec["filename"] = fname
                    dex_rec["placement"] = "root_multidex" if ("/" not in fname and fname.startswith("classes")) else "embedded_asset_dex"
                    dex_rec["compressed_bytes"] = c_sz
                    dex_rec["compression"] = comp_label
                    dex_shards.append(dex_rec)

                # Native ELF .so structural telemetry
                if fname.endswith(".so") and raw.startswith(b"\x7fELF"):
                    data_off = compute_zip_data_offset(apk_bytes, info)
                    aligned_4k = (data_off >= 0) and (data_off % 4096 == 0)
                    aligned_16k = (data_off >= 0) and (data_off % 16384 == 0)
                    elf_rec = parse_elf_structural(raw, fname, aligned_4k, aligned_16k, is_comp, c_sz)
                    elf_rec["apk"] = apk_name
                    elf_rec["zip_data_offset"] = data_off
                    elf_libraries.append(elf_rec)

                # resources.arsc
                if fname == "resources.arsc":
                    arsc_rec = parse_arsc_structural(raw, apk_name)
                    arsc_rec["compressed_bytes"] = c_sz
                    arsc_rec["compression"] = comp_label
                    arsc_tables.append(arsc_rec)

                # Fonts
                if magic_type == "TrueType / OpenType Font":
                    finfo = parse_font_family(raw) or {"family": "Unknown", "subfamily": "Regular", "full_name": fname}
                    fonts_catalog.append({
                        "apk": apk_name,
                        "path": fname,
                        "size_bytes": u_sz,
                        **finfo,
                    })

                # Images & VectorDrawables
                if magic_type in ("WebP Image", "PNG Raster Image", "Nine-Patch PNG (.9.png)", "JPEG Image", "GIF Animation", "SVG Vector Graphic"):
                    image_stats[magic_type]["count"] += 1
                    image_stats[magic_type]["uncompressed_bytes"] += u_sz
                    image_stats[magic_type]["compressed_bytes"] += c_sz
                elif fname.startswith("res/") and magic_type == "Binary Android XML (AXML)" and ("drawable" in fname or "ic_" in fname):
                    # Detect compiled VectorDrawable XML quickly
                    if b"vector" in raw[:512] or b"v\x00e\x00c\x00t\x00o\x00r\x00" in raw[:512]:
                        image_stats["Android VectorDrawable (Binary XML)"]["count"] += 1
                        image_stats["Android VectorDrawable (Binary XML)"]["uncompressed_bytes"] += u_sz
                        image_stats["Android VectorDrawable (Binary XML)"]["compressed_bytes"] += c_sz

                # Lottie animations
                if fname.lower().endswith(".json") and (fname.startswith("assets/") or fname.startswith("res/raw")):
                    lot = inspect_lottie_json(raw)
                    if lot is not None:
                        lot["apk"] = apk_name
                        lot["path"] = fname
                        lottie_catalog.append(lot)

                # On-device ML / AI assets
                if magic_type in ("TensorFlow Lite Model (.tflite)", "On-Device ML Model Asset") or (
                    fname.startswith("assets/") and any(k in fname.lower() for k in ("tflite", "onnx", "ncnn", "mnn", "model", "face", "ocr", "mlkit", "barhopper", "ekyc", "liveness"))
                ):
                    ml_assets.append({
                        "apk": apk_name,
                        "path": fname,
                        "magic_type": magic_type,
                        "uncompressed_bytes": u_sz,
                        "compressed_bytes": c_sz,
                    })

                # Hybrid Web / JS / WASM / Mini-App assets
                if magic_type in ("JavaScript / Hermes Bundle", "Web HTML / CSS Asset", "WebAssembly Module (.wasm)") or (
                    fname.startswith("assets/") and any(k in fname.lower() for k in ("flutter_assets", "index.android.bundle", "webapp", "miniapp", "h5", "zmp", ".html", ".js", ".css", ".wasm"))
                ):
                    web_hybrid_assets.append({
                        "apk": apk_name,
                        "path": fname,
                        "magic_type": magic_type,
                        "uncompressed_bytes": u_sz,
                        "compressed_bytes": c_sz,
                    })

                # Subdirectory trees for assets/ and res/
                if fname.startswith("assets/"):
                    sub = fname.split("/")[1] if len(fname.split("/")) >= 3 else "<assets-root>"
                    assets_subdir_counts[sub] += 1
                    assets_subdir_bytes[sub] += u_sz
                elif fname.startswith("res/"):
                    sub = fname.split("/")[1] if len(fname.split("/")) >= 3 else "<res-root>"
                    res_subdir_counts[sub] += 1
                    res_subdir_bytes[sub] += u_sz

        sig_info = inspect_apk_signing_block(apk_bytes, has_v1_sig)
        apk_summaries.append({
            "apk": apk_name,
            "apk_disk_bytes": len(apk_bytes),
            "file_count": apk_file_count,
            "stored_files": apk_stored_count,
            "deflated_files": apk_deflated_count,
            "entries_uncompressed_bytes": apk_uncomp,
            "entries_compressed_bytes": apk_comp,
            "compression_ratio_pct": round((1.0 - apk_comp / max(1, apk_uncomp)) * 100.0, 2),
            "signing": sig_info,
            "top_directories_uncompressed": dict(apk_topdirs_bytes.most_common(15)),
            "top_magic_types_uncompressed": dict(apk_magic_bytes.most_common(15)),
        })

    print(f"[3/6] Scanned 100% of files: {total_files_scanned:,} entries ({total_uncomp_bytes:,} bytes uncompressed).")

    # Aggregate Multi-DEX metrics
    def _dex_sort_key(d: Dict[str, Any]) -> Tuple[str, int, int, str]:
        fn = d["filename"]
        if d.get("placement") == "root_multidex":
            num_str = fn.replace("classes", "").replace(".dex", "") or "1"
            num = int(num_str) if num_str.isdigit() else 999
            return (d["apk"], 0, num, fn)
        return (d["apk"], 1, 999, fn)

    dex_shards.sort(key=_dex_sort_key)
    agg_domains: Counter = Counter()
    agg_prefix2: Counter = Counter()
    agg_prefix3: Counter = Counter()
    agg_roles: Counter = Counter()
    total_dex_bytes = sum(d["file_size_bytes"] for d in dex_shards)
    total_dex_comp = sum(d["compressed_bytes"] for d in dex_shards)
    total_classes = sum(d["class_defs"] for d in dex_shards)
    total_minified = sum(d["minified_classes"] for d in dex_shards)
    total_inner = sum(d["inner_classes"] for d in dex_shards)
    total_defined_methods = sum(d["defined_methods_total"] for d in dex_shards)
    total_defined_fields = sum(d["defined_fields_total"] for d in dex_shards)
    total_method_refs = sum(d["method_ids"] for d in dex_shards)
    total_string_refs = sum(d["string_ids"] for d in dex_shards)

    for d in dex_shards:
        agg_domains.update(d["domains"])
        agg_prefix2.update(d["top_prefix2"])
        agg_prefix3.update(d["top_prefix3"])
        agg_roles.update(d["roles"])

    dex_summary = {
        "shard_count": len(dex_shards),
        "dex_versions": sorted(set(d["dex_version"] for d in dex_shards)),
        "total_uncompressed_bytes": total_dex_bytes,
        "total_compressed_bytes": total_dex_comp,
        "total_class_defs": total_classes,
        "total_defined_methods": total_defined_methods,
        "total_defined_fields": total_defined_fields,
        "sum_method_id_refs_across_shards": total_method_refs,
        "sum_string_id_refs_across_shards": total_string_refs,
        "total_minified_classes": total_minified,
        "overall_minified_pct": round((total_minified / max(1, total_classes)) * 100.0, 2),
        "total_inner_classes": total_inner,
        "inner_class_pct": round((total_inner / max(1, total_classes)) * 100.0, 2),
        "mean_method_64k_utilization_pct": round(
            sum(d["method_64k_utilization_pct"] for d in dex_shards) / max(1, len(dex_shards)), 2
        ),
        "roles": dict(agg_roles),
        "domains": dict(agg_domains.most_common()),
        "top_prefix2": dict(agg_prefix2.most_common(35)),
        "top_prefix3": dict(agg_prefix3.most_common(45)),
        "shards": [
            {k: v for k, v in d.items() if k not in ("top_prefix2", "top_prefix3")}
            for d in dex_shards
        ],
    }

    # Aggregate Native ELF metrics
    elf_libraries.sort(key=lambda e: -e["file_size_bytes"])
    elf_by_abi: Counter = Counter()
    elf_by_abi_bytes: Counter = Counter()
    elf_by_cat_counts: Counter = Counter()
    elf_by_cat_bytes: Counter = Counter()
    elf_toolchains: Counter = Counter()
    elf_dt_needed_freq: Counter = Counter()
    elf_sections_total: Counter = Counter()
    elf_16kb_ready = 0
    elf_4kb_only = 0
    elf_zip_16kb_aligned = 0
    elf_zip_4kb_aligned = 0
    elf_stored_count = 0
    elf_stripped_count = 0

    for e in elf_libraries:
        if not e.get("valid"):
            continue
        elf_by_abi[e["abi"]] += 1
        elf_by_abi_bytes[e["abi"]] += e["file_size_bytes"]
        elf_by_cat_counts[e["category"]] += 1
        elf_by_cat_bytes[e["category"]] += e["file_size_bytes"]
        elf_toolchains[e["toolchain"]] += 1
        for dep in e["dt_needed"]:
            elf_dt_needed_freq[dep] += 1
        for sk, sv in e["sections_summary"].items():
            elf_sections_total[sk] += sv
        if e["elf_16kb_aligned"]:
            elf_16kb_ready += 1
        else:
            elf_4kb_only += 1
        if e["zip_16kb_aligned"]:
            elf_zip_16kb_aligned += 1
        if e["zip_4kb_aligned"]:
            elf_zip_4kb_aligned += 1
        if not e["compressed"]:
            elf_stored_count += 1
        if e["stripped_symtab"]:
            elf_stripped_count += 1

    elf_summary = {
        "total_libraries": len(elf_libraries),
        "total_uncompressed_bytes": sum(e["file_size_bytes"] for e in elf_libraries),
        "total_compressed_bytes": sum(e["compressed_size_bytes"] for e in elf_libraries),
        "total_virtual_mem_bytes": sum(e["virtual_mem_bytes"] for e in elf_libraries),
        "by_abi_count": dict(elf_by_abi),
        "by_abi_bytes": dict(elf_by_abi_bytes),
        "by_category_count": dict(elf_by_cat_counts.most_common()),
        "by_category_bytes": dict(elf_by_cat_bytes.most_common()),
        "elf_16kb_pt_load_aligned_count": elf_16kb_ready,
        "elf_4kb_only_pt_load_count": elf_4kb_only,
        "elf_16kb_ready_pct": round((elf_16kb_ready / max(1, len(elf_libraries))) * 100.0, 2),
        "zip_4kb_aligned_count": elf_zip_4kb_aligned,
        "zip_16kb_aligned_count": elf_zip_16kb_aligned,
        "stored_uncompressed_count": elf_stored_count,
        "stripped_symtab_count": elf_stripped_count,
        "sections_total_bytes": dict(elf_sections_total),
        "top_dt_needed": dict(elf_dt_needed_freq.most_common(25)),
        "toolchains": dict(elf_toolchains.most_common(15)),
        "libraries": elf_libraries,
    }

    # Deduplication analysis
    dup_groups = 0
    dup_extra_files = 0
    dup_wasted_uncomp = 0
    dup_wasted_comp = 0
    top_dups = []
    for sha, occ in hash_occurrences.items():
        if len(occ) > 1:
            dup_groups += 1
            extra = len(occ) - 1
            dup_extra_files += extra
            u_waste = occ[0][2] * extra
            c_waste = sum(x[3] for x in occ[1:])
            dup_wasted_uncomp += u_waste
            dup_wasted_comp += c_waste
            top_dups.append({
                "sha256_prefix": sha[:12],
                "copies": len(occ),
                "single_size_bytes": occ[0][2],
                "wasted_uncompressed_bytes": u_waste,
                "sample_paths": [f"{a}:{p}" for a, p, _, _ in occ[:4]],
            })
    top_dups.sort(key=lambda x: -x["wasted_uncompressed_bytes"])

    dedup_summary = {
        "unique_file_hashes": len(hash_occurrences),
        "total_file_entries": total_files_scanned,
        "duplicate_hash_groups": dup_groups,
        "redundant_file_copies": dup_extra_files,
        "wasted_uncompressed_bytes": dup_wasted_uncomp,
        "wasted_compressed_bytes": dup_wasted_comp,
        "top_duplicates": top_dups[:20],
    }

    # Jetpack / AndroidX version taxonomy
    androidx_versions = {k: v for k, v in sorted(library_versions.items()) if k.startswith("androidx.")}
    other_versions = {k: v for k, v in sorted(library_versions.items()) if not k.startswith("androidx.")}

    print(f"[4/6] Parsed {len(dex_shards)} DEX shards ({total_classes:,} classes), {len(elf_libraries)} native ELF .so binaries, {len(arsc_tables)} ARSC tables.")

    # Assemble master empirical dataset
    report: Dict[str, Any] = {
        "metadata": {
            "generated_at_utc": datetime.now(timezone.utc).isoformat(),
            "methodology": "Static Structural, Packaging, Software-Composition & Binary-Ecosystem Telemetry (100% File Coverage)",
            "ethical_scope": "Non-exploitative empirical software engineering study. No proprietary source decompilation, no native disassembly, no secret extraction, no vulnerability auditing.",
            "target_package": xapk_summary["xapk_manifest"].get("package_name", "vn.com.vng.zalopay"),
            "target_app_name": xapk_summary["xapk_manifest"].get("name", "Zalopay"),
            "version_name": xapk_summary["xapk_manifest"].get("version_name", "11.17.0"),
            "version_code": xapk_summary["xapk_manifest"].get("version_code", "1102758"),
        },
        "rq1_packaging": {
            "container": xapk_summary,
            "totals": {
                "split_apk_count": len(apk_summaries),
                "total_files_scanned": total_files_scanned,
                "total_entries_uncompressed_bytes": total_uncomp_bytes,
                "total_entries_compressed_bytes": total_comp_bytes,
                "overall_compression_savings_pct": round((1.0 - total_comp_bytes / max(1, total_uncomp_bytes)) * 100.0, 2),
            },
            "split_apks": apk_summaries,
            "by_magic_type": [
                {
                    "type": t,
                    "count": global_magic_counts[t],
                    "uncompressed_bytes": global_magic_uncomp[t],
                    "compressed_bytes": global_magic_comp[t],
                    "share_uncompressed_pct": round((global_magic_uncomp[t] / max(1, total_uncomp_bytes)) * 100.0, 2),
                }
                for t, _ in global_magic_uncomp.most_common()
            ],
            "by_top_directory": [
                {
                    "directory": d,
                    "count": global_topdir_counts[d],
                    "uncompressed_bytes": global_topdir_uncomp[d],
                    "compressed_bytes": global_topdir_comp[d],
                    "share_uncompressed_pct": round((global_topdir_uncomp[d] / max(1, total_uncomp_bytes)) * 100.0, 2),
                }
                for d, _ in global_topdir_uncomp.most_common(25)
            ],
            "by_compression_method": [
                {
                    "method": m,
                    "count": global_compression_counts[m],
                    "uncompressed_bytes": global_compression_uncomp[m],
                    "compressed_bytes": global_compression_comp[m],
                }
                for m in global_compression_counts
            ],
            "top_extensions": [
                {"extension": ext, "count": global_ext_counts[ext], "uncompressed_bytes": global_ext_uncomp[ext]}
                for ext, _ in global_ext_uncomp.most_common(25)
            ],
            "deduplication": dedup_summary,
        },
        "rq2_manifest_platform": {
            "manifests": manifests,
        },
        "rq3_multidex_sca": dex_summary,
        "rq4_native_elf": elf_summary,
        "rq5_resources_assets": {
            "arsc_tables": arsc_tables,
            "image_formats": dict(image_stats),
            "fonts": {
                "count": len(fonts_catalog),
                "total_bytes": sum(f["size_bytes"] for f in fonts_catalog),
                "catalog": sorted(fonts_catalog, key=lambda x: -x["size_bytes"]),
            },
            "lottie_animations": {
                "count": len(lottie_catalog),
                "total_bytes": sum(l["size_bytes"] for l in lottie_catalog),
                "avg_layers": round(sum(l["layers"] for l in lottie_catalog) / max(1, len(lottie_catalog)), 2),
                "avg_duration_sec": round(sum(l["duration_sec"] for l in lottie_catalog) / max(1, len(lottie_catalog)), 2),
                "top_by_size": sorted(lottie_catalog, key=lambda x: -x["size_bytes"])[:20],
            },
            "ml_ai_assets": {
                "count": len(ml_assets),
                "total_uncompressed_bytes": sum(m["uncompressed_bytes"] for m in ml_assets),
                "items": sorted(ml_assets, key=lambda x: -x["uncompressed_bytes"])[:40],
            },
            "web_hybrid_assets": {
                "count": len(web_hybrid_assets),
                "total_uncompressed_bytes": sum(w["uncompressed_bytes"] for w in web_hybrid_assets),
                "by_type": dict(Counter(w["magic_type"] for w in web_hybrid_assets)),
                "top_by_size": sorted(web_hybrid_assets, key=lambda x: -x["uncompressed_bytes"])[:30],
            },
            "assets_subdirectories": [
                {"subdir": s, "count": assets_subdir_counts[s], "uncompressed_bytes": assets_subdir_bytes[s]}
                for s, _ in assets_subdir_bytes.most_common(30)
            ],
            "res_subdirectories": [
                {"subdir": s, "count": res_subdir_counts[s], "uncompressed_bytes": res_subdir_bytes[s]}
                for s, _ in res_subdir_bytes.most_common(30)
            ],
            "build_metadata": {
                "androidx_library_count": len(androidx_versions),
                "androidx_versions": androidx_versions,
                "other_library_versions": other_versions,
                "kotlin_module_count": len(kotlin_modules),
                "kotlin_modules_sample": sorted(kotlin_modules)[:40],
            },
        },
    }

    out_path = DATA_DIR / "analysis_report.json"
    out_path.write_text(json.dumps(report, indent=2, ensure_ascii=False), encoding="utf-8")
    print(f"[5/6] Wrote comprehensive dataset -> {out_path} ({out_path.stat().st_size:,} bytes)")

    # Print executive console digest
    base_manifest = next((m for m in manifests if m["apk"] == "vn.com.vng.zalopay.apk"), manifests[0] if manifests else {})
    print("\n================ EMPIRICAL DIGEST ================")
    print(f"Package: {report['metadata']['target_package']} v{report['metadata']['version_name']} (code {report['metadata']['version_code']})")
    print(f"SDK: min={base_manifest.get('min_sdk')} | target={base_manifest.get('target_sdk')} | compile={base_manifest.get('compile_sdk_version')}")
    print(f"Files scanned (100% coverage): {total_files_scanned:,} across {len(apk_summaries)} split APKs")
    print(f"Total Uncompressed: {total_uncomp_bytes/1e6:.2f} MB | Compressed Entries: {total_comp_bytes/1e6:.2f} MB")
    print(f"Multi-DEX: {dex_summary['shard_count']} shards | {dex_summary['total_class_defs']:,} classes | {dex_summary['total_defined_methods']:,} defined methods | {dex_summary['overall_minified_pct']}% R8-minified")
    print(f"Native ELF (.so): {elf_summary['total_libraries']} libs | {elf_summary['total_uncompressed_bytes']/1e6:.2f} MB | 16KB PT_LOAD aligned: {elf_summary['elf_16kb_pt_load_aligned_count']}/{elf_summary['total_libraries']} ({elf_summary['elf_16kb_ready_pct']}%)")
    print(f"Permissions: {len(base_manifest.get('permissions', []))} | Components: {base_manifest.get('components_total', {})}")
    print(f"Fonts: {len(fonts_catalog)} | Lottie animations: {len(lottie_catalog)} | ML assets: {len(ml_assets)} | Hybrid/Web assets: {len(web_hybrid_assets)}")
    print(f"AndroidX artifacts in META-INF: {len(androidx_versions)} | Kotlin modules: {len(kotlin_modules)}")
    print("==================================================\n")
    print("[6/6] Analysis complete.")


if __name__ == "__main__":
    analyze_all()
