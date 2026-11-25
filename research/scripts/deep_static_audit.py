#!/usr/bin/env python3
"""
Comprehensive Static Reverse Engineering Audit for Zalopay v11.17.0 XAPK Bundle.
Analyzes ALL files across:
  1. Outer ZIP -> Inner XAPK -> manifest.json -> icon.png -> all 5 APKs
  2. Binary AndroidManifest.xml (base + all 4 split APKs) via Androguard
  3. resources.arsc & decoded res/xml/* (base + vi + en + xhdpi)
  4. All 20 DEX files (headers, package trees, R8 obfuscation metrics, Dalvik opcode census, SBOM, defensive security APIs)
  5. All 124 assets/, 56 res/raw/, META-INF/, and Griver .amr / NCNN / TFLite / Visa CBP artifacts
  6. All 27 ARMv7 native .so binaries (LIEF, pyelftools, per-section Shannon entropy, Capstone ARM/Thumb telemetry)
"""

import hashlib
import io
import json
import math
import os
import re
import struct
import tarfile
import xml.etree.ElementTree as ET
import zipfile
from collections import Counter
from concurrent.futures import ProcessPoolExecutor
from pathlib import Path

from loguru import logger
logger.disable("androguard")

from androguard.core.axml import AXMLPrinter, ARSCParser
from androguard.core.dex import DEX
from asn1crypto import cms
from capstone import Cs, CS_ARCH_ARM, CS_MODE_ARM, CS_MODE_THUMB
from elftools.elf.elffile import ELFFile
import lief
from PIL import Image

REPO_ROOT = Path("/home/user/-")
OUTER_ZIP = REPO_ROOT / "Zalopay-Thanh+toán+&+Tài+chính_11.17.0_APKPure.zip"
XAPK_DIR = Path("/tmp/zalopay_re/xapk")
EXTRACTED_DIR = Path("/tmp/zalopay_re/extracted")
OUT_DIR = REPO_ROOT / "research/evidence/deep"
OUT_DIR.mkdir(parents=True, exist_ok=True)


def sha256_bytes(data: bytes) -> str:
    return hashlib.sha256(data).hexdigest()


def sha256_file(path: Path) -> str:
    h = hashlib.sha256()
    with open(path, "rb") as f:
        while chunk := f.read(1 << 20):
            h.update(chunk)
    return h.hexdigest()


def shannon_entropy(data: bytes) -> float:
    if not data:
        return 0.0
    counts = Counter(data)
    n = len(data)
    return -sum((c / n) * math.log2(c / n) for c in counts.values())


# ---------------------------------------------------------------------------
# 1. Outer ZIP, Inner XAPK, manifest.json, icon.png, and All 5 APKs Inventory
# ---------------------------------------------------------------------------
def audit_bundle_and_apks():
    print("[1/6] Auditing outer ZIP, XAPK, manifest.json, icon.png, and all 5 APKs...")
    outer_stat = OUTER_ZIP.stat()
    with zipfile.ZipFile(OUTER_ZIP) as oz:
        outer_members = [
            {
                "filename": i.filename,
                "file_size": i.file_size,
                "compress_size": i.compress_size,
                "compress_type": i.compress_type,
                "crc32": f"0x{i.CRC:08x}",
            }
            for i in oz.infolist()
        ]

    xapk_path = Path("/tmp/zalopay_re") / outer_members[0]["filename"]
    xapk_members = []
    with zipfile.ZipFile(xapk_path) as xz:
        for i in xz.infolist():
            xapk_members.append({
                "filename": i.filename,
                "file_size": i.file_size,
                "compress_size": i.compress_size,
                "compress_type": i.compress_type,
                "crc32": f"0x{i.CRC:08x}",
                "sha256": sha256_file(XAPK_DIR / i.filename),
            })

    manifest_json = json.loads((XAPK_DIR / "manifest.json").read_text("utf-8"))

    icon_path = XAPK_DIR / "icon.png"
    icon_bytes = icon_path.read_bytes()
    with Image.open(icon_path) as im:
        icon_meta = {
            "bytes": len(icon_bytes),
            "sha256": sha256_bytes(icon_bytes),
            "format": im.format,
            "mode": im.mode,
            "size": list(im.size),
        }
    png_chunks = []
    pos = 8
    while pos + 8 <= len(icon_bytes):
        length = struct.unpack(">I", icon_bytes[pos : pos + 4])[0]
        ctype = icon_bytes[pos + 4 : pos + 8].decode("ascii", "replace")
        png_chunks.append({"type": ctype, "length": length})
        pos += 12 + length
    icon_meta["png_chunks"] = png_chunks

    apk_inventories = {}
    for apk_name in sorted(p.name for p in XAPK_DIR.glob("*.apk")):
        apk_path = XAPK_DIR / apk_name
        ext_counts = Counter()
        ext_bytes = Counter()
        dir_counts = Counter()
        dir_bytes = Counter()
        comp_types = Counter()
        total_uncompressed = 0
        total_compressed = 0
        cert_details = []

        with zipfile.ZipFile(apk_path) as z:
            infos = z.infolist()
            for i in infos:
                total_uncompressed += i.file_size
                total_compressed += i.compress_size
                comp_types["STORED" if i.compress_type == 0 else "DEFLATED"] += 1
                top = i.filename.split("/")[0] if "/" in i.filename else "(root)"
                dir_counts[top] += 1
                dir_bytes[top] += i.file_size
                ext = Path(i.filename).suffix.lower() or "(none)"
                ext_counts[ext] += 1
                ext_bytes[ext] += i.file_size

                if i.filename.endswith((".RSA", ".DSA", ".EC")):
                    raw_sig = z.read(i.filename)
                    ci = cms.ContentInfo.load(raw_sig)
                    sd = ci["content"]
                    for cert_choice in sd["certificates"]:
                        c = cert_choice.chosen
                        tbs = c["tbs_certificate"]
                        cert_der = c.dump()
                        cert_details.append({
                            "file": i.filename,
                            "sha256_fingerprint": sha256_bytes(cert_der),
                            "sha1_fingerprint": hashlib.sha1(cert_der).hexdigest(),
                            "subject": tbs["subject"].human_friendly,
                            "issuer": tbs["issuer"].human_friendly,
                            "serial_hex": hex(tbs["serial_number"].native),
                            "not_before": str(tbs["validity"]["not_before"].native),
                            "not_after": str(tbs["validity"]["not_after"].native),
                            "signature_algo": c["signature_algorithm"]["algorithm"].native,
                            "public_key_algo": tbs["subject_public_key_info"]["algorithm"]["algorithm"].native,
                        })

        subdir = "base" if apk_name == "vn.com.vng.zalopay.apk" else apk_name.replace("config.", "").replace(".apk", "")
        stamp_path = EXTRACTED_DIR / subdir / "stamp-cert-sha256"
        stamp_hex = stamp_path.read_bytes().hex() if stamp_path.exists() else None

        apk_inventories[apk_name] = {
            "bytes": apk_path.stat().st_size,
            "sha256": sha256_file(apk_path),
            "entry_count": len(infos),
            "total_uncompressed": total_uncompressed,
            "total_compressed": total_compressed,
            "compression_methods": dict(comp_types),
            "top_directories": [
                {"dir": k, "count": dir_counts[k], "uncompressed_bytes": dir_bytes[k]}
                for k, _ in dir_bytes.most_common()
            ],
            "top_extensions": [
                {"ext": k, "count": ext_counts[k], "uncompressed_bytes": ext_bytes[k]}
                for k, _ in ext_bytes.most_common(20)
            ],
            "v1_certificates": cert_details,
            "stamp_cert_sha256_hex": stamp_hex,
        }

    result = {
        "outer_zip": {
            "filename": OUTER_ZIP.name,
            "bytes": outer_stat.st_size,
            "sha256": sha256_file(OUTER_ZIP),
            "members": outer_members,
        },
        "xapk": {
            "filename": xapk_path.name,
            "bytes": xapk_path.stat().st_size,
            "sha256": sha256_file(xapk_path),
            "members": xapk_members,
            "manifest_json": manifest_json,
            "icon_png": icon_meta,
        },
        "apks": apk_inventories,
    }
    (OUT_DIR / "01_bundle_and_signatures.json").write_text(json.dumps(result, indent=2, ensure_ascii=False))
    return result


# ---------------------------------------------------------------------------
# 2. AndroidManifest.xml Full Analysis (Base + All 4 Split APKs)
# ---------------------------------------------------------------------------
ANDROID_NS = "{http://schemas.android.com/apk/res/android}"

DANGEROUS_PERMISSIONS = {
    "android.permission.READ_CONTACTS",
    "android.permission.WRITE_CONTACTS",
    "android.permission.READ_CALENDAR",
    "android.permission.WRITE_CALENDAR",
    "android.permission.CAMERA",
    "android.permission.RECORD_AUDIO",
    "android.permission.ACCESS_FINE_LOCATION",
    "android.permission.ACCESS_COARSE_LOCATION",
    "android.permission.READ_PHONE_STATE",
    "android.permission.READ_BASIC_PHONE_STATE",
    "android.permission.READ_EXTERNAL_STORAGE",
    "android.permission.WRITE_EXTERNAL_STORAGE",
    "android.permission.READ_MEDIA_AUDIO",
    "android.permission.POST_NOTIFICATIONS",
}


def classify_permission(p: str) -> str:
    if p in DANGEROUS_PERMISSIONS:
        return "dangerous_runtime"
    if p.startswith("android.permission.") or p.startswith("android.settings."):
        if any(k in p for k in ("BIOMETRIC", "FINGERPRINT", "NFC", "OVERLAY", "QUERY_ALL_PACKAGES", "DETECT_SCREEN_CAPTURE")):
            return "special_or_security"
        if "ADSERVICES" in p:
            return "adservices_privacy_sandbox"
        return "normal_system"
    if p.startswith(("com.samsung.", "com.bbk.", "com.huawei.", "com.android.launcher.")):
        return "oem_launcher_vendor"
    if p.startswith(("com.zing.", "vn.com.vng.")):
        return "first_party_zalo_ecosystem"
    return "third_party_sdk"


def parse_component(elem, pkg_name: str, comp_type: str):
    name = elem.get(ANDROID_NS + "name") or ""
    if name.startswith(".") and pkg_name:
        fqcn = pkg_name + name
    elif "." not in name and pkg_name:
        fqcn = pkg_name + "." + name
    else:
        fqcn = name
    exported = elem.get(ANDROID_NS + "exported")
    permission = elem.get(ANDROID_NS + "permission")
    process = elem.get(ANDROID_NS + "process")
    enabled = elem.get(ANDROID_NS + "enabled")

    intent_filters = []
    schemes = []
    hosts = []
    actions = []
    for f in elem.findall("intent-filter"):
        f_acts = [a.get(ANDROID_NS + "name") for a in f.findall("action") if a.get(ANDROID_NS + "name")]
        f_cats = [c.get(ANDROID_NS + "name") for c in f.findall("category") if c.get(ANDROID_NS + "name")]
        f_data = []
        for d in f.findall("data"):
            d_obj = {}
            for attr in ("scheme", "host", "port", "path", "pathPrefix", "pathPattern", "mimeType"):
                val = d.get(ANDROID_NS + attr)
                if val is not None:
                    d_obj[attr] = val
            if "scheme" in d_obj:
                schemes.append(d_obj["scheme"])
            if "host" in d_obj:
                hosts.append(d_obj["host"])
            if d_obj:
                f_data.append(d_obj)
        actions.extend(f_acts)
        intent_filters.append({
            "autoVerify": f.get(ANDROID_NS + "autoVerify"),
            "actions": f_acts,
            "categories": f_cats,
            "data": f_data,
        })

    has_filter = len(intent_filters) > 0
    effectively_exported = (exported == "true") or (exported is None and has_filter)
    return {
        "type": comp_type,
        "name": fqcn,
        "exported_attr": exported,
        "effectively_exported": effectively_exported,
        "permission": permission,
        "process": process,
        "enabled": enabled,
        "intent_filter_count": len(intent_filters),
        "actions": sorted(set(actions)),
        "schemes": sorted(set(schemes)),
        "hosts": sorted(set(hosts)),
        "intent_filters": intent_filters if (effectively_exported or schemes) else [],
        "authorities": elem.get(ANDROID_NS + "authorities") if comp_type == "provider" else None,
        "grantUriPermissions": elem.get(ANDROID_NS + "grantUriPermissions") if comp_type == "provider" else None,
    }


def sanitize_metadata_value(key: str, val: str | None) -> str | None:
    if val is None:
        return None
    kl = key.lower()
    if any(s in kl for s in ("key", "secret", "token", "appid", "app_id", "license", "password", "hash", "salt", "client_id", "api_")):
        if len(val) > 8 and not val.startswith(("@", "res/", "true", "false")):
            return f"<REDACTED:{len(val)}chars:{val[:3]}...>"
    return val


def audit_manifests():
    print("[2/6] Auditing binary AndroidManifest.xml across all 5 APKs...")
    manifests = {}
    for apk_name in sorted(p.name for p in XAPK_DIR.glob("*.apk")):
        apk_path = XAPK_DIR / apk_name
        with zipfile.ZipFile(apk_path) as z:
            axml_bytes = z.read("AndroidManifest.xml")
        xml_raw = AXMLPrinter(axml_bytes).get_xml()
        xml_str = xml_raw.decode("utf-8", "replace") if isinstance(xml_raw, bytes) else xml_raw
        root = ET.fromstring(xml_str)
        pkg = root.get("package") or ""
        uses_sdk = root.find("uses-sdk")

        if apk_name != "vn.com.vng.zalopay.apk":
            manifests[apk_name] = {
                "package": pkg,
                "versionCode": root.get(ANDROID_NS + "versionCode"),
                "versionName": root.get(ANDROID_NS + "versionName"),
                "split": root.get("split"),
                "configForSplit": root.get("configForSplit"),
                "isFeatureSplit": root.get(ANDROID_NS + "isFeatureSplit"),
                "splitTypes": root.get(ANDROID_NS + "splitTypes"),
                "minSdkVersion": uses_sdk.get(ANDROID_NS + "minSdkVersion") if uses_sdk is not None else None,
                "raw_xml": xml_str,
            }
            continue

        perms = []
        for tag in ("uses-permission", "uses-permission-sdk-23"):
            for el in root.findall(tag):
                pname = el.get(ANDROID_NS + "name")
                if pname:
                    perms.append({
                        "name": pname,
                        "tag": tag,
                        "maxSdkVersion": el.get(ANDROID_NS + "maxSdkVersion"),
                        "category": classify_permission(pname),
                    })
        custom_perms = [
            {
                "name": el.get(ANDROID_NS + "name"),
                "protectionLevel": el.get(ANDROID_NS + "protectionLevel"),
            }
            for el in root.findall("permission")
        ]
        features = [
            {
                "name": el.get(ANDROID_NS + "name"),
                "required": el.get(ANDROID_NS + "required"),
            }
            for el in root.findall("uses-feature")
        ]
        queries_packages = []
        queries_schemes = []
        queries_actions = []
        for q in root.findall("queries"):
            for p in q.findall("package"):
                queries_packages.append(p.get(ANDROID_NS + "name"))
            for i in q.findall("intent"):
                for a in i.findall("action"):
                    queries_actions.append(a.get(ANDROID_NS + "name"))
                for d in i.findall("data"):
                    if d.get(ANDROID_NS + "scheme"):
                        queries_schemes.append(d.get(ANDROID_NS + "scheme"))

        app = root.find("application")
        app_flags = {
            "name": app.get(ANDROID_NS + "name"),
            "appComponentFactory": app.get(ANDROID_NS + "appComponentFactory"),
            "debuggable": app.get(ANDROID_NS + "debuggable") or "false(default)",
            "allowBackup": app.get(ANDROID_NS + "allowBackup"),
            "usesCleartextTraffic": app.get(ANDROID_NS + "usesCleartextTraffic"),
            "networkSecurityConfig": app.get(ANDROID_NS + "networkSecurityConfig"),
            "extractNativeLibs": app.get(ANDROID_NS + "extractNativeLibs"),
            "requestLegacyExternalStorage": app.get(ANDROID_NS + "requestLegacyExternalStorage"),
            "fullBackupContent": app.get(ANDROID_NS + "fullBackupContent"),
            "dataExtractionRules": app.get(ANDROID_NS + "dataExtractionRules"),
            "largeHeap": app.get(ANDROID_NS + "largeHeap"),
            "enableOnBackInvokedCallback": app.get(ANDROID_NS + "enableOnBackInvokedCallback"),
        }

        components = []
        for ctype in ("activity", "activity-alias", "service", "receiver", "provider"):
            for el in app.findall(ctype):
                components.append(parse_component(el, pkg, ctype))

        comp_summary = {}
        for ctype in ("activity", "activity-alias", "service", "receiver", "provider"):
            sub = [c for c in components if c["type"] == ctype]
            exp = [c for c in sub if c["effectively_exported"]]
            exp_noperm = [c for c in exp if not c["permission"]]
            comp_summary[ctype] = {
                "total": len(sub),
                "exported": len(exp),
                "exported_without_permission": len(exp_noperm),
            }

        metadata = []
        for md in app.findall("meta-data"):
            k = md.get(ANDROID_NS + "name") or ""
            v = md.get(ANDROID_NS + "value") or md.get(ANDROID_NS + "resource")
            metadata.append({"name": k, "value": sanitize_metadata_value(k, v)})

        all_schemes = sorted({s for c in components for s in c["schemes"]})
        all_hosts = sorted({h for c in components for h in c["hosts"]})
        processes = sorted({c["process"] for c in components if c["process"]})
        perm_cats = Counter(p["category"] for p in perms)

        manifests[apk_name] = {
            "package": pkg,
            "versionCode": root.get(ANDROID_NS + "versionCode"),
            "versionName": root.get(ANDROID_NS + "versionName"),
            "compileSdkVersion": root.get(ANDROID_NS + "compileSdkVersion"),
            "compileSdkVersionCodename": root.get(ANDROID_NS + "compileSdkVersionCodename"),
            "minSdkVersion": uses_sdk.get(ANDROID_NS + "minSdkVersion") if uses_sdk is not None else None,
            "targetSdkVersion": uses_sdk.get(ANDROID_NS + "targetSdkVersion") if uses_sdk is not None else None,
            "application_flags": app_flags,
            "permissions_count": len(perms),
            "permission_categories": dict(perm_cats),
            "permissions": perms,
            "custom_permissions": custom_perms,
            "uses_features": features,
            "queries": {
                "packages_count": len(queries_packages),
                "packages": sorted(set(queries_packages)),
                "intent_actions": sorted(set(queries_actions)),
                "intent_schemes": sorted(set(queries_schemes)),
            },
            "component_summary": comp_summary,
            "total_components": len(components),
            "multi_processes": processes,
            "deep_link_schemes": all_schemes,
            "deep_link_hosts": all_hosts,
            "exported_components": [c for c in components if c["effectively_exported"]],
            "meta_data_count": len(metadata),
            "meta_data": metadata,
        }
    (OUT_DIR / "02_manifest_and_attack_surface.json").write_text(json.dumps(manifests, indent=2, ensure_ascii=False))
    return manifests


# ---------------------------------------------------------------------------
# 3. Resources, Localization Splits, & Decoded res/xml Policies
# ---------------------------------------------------------------------------
def audit_resources_and_xml():
    print("[3/6] Auditing resources.arsc across base/vi/en/xhdpi and decoded res/xml policies...")
    decoded_xmls = {}
    with zipfile.ZipFile(XAPK_DIR / "vn.com.vng.zalopay.apk") as z:
        for name in sorted(n for n in z.namelist() if n.startswith("res/xml") and n.endswith(".xml")):
            try:
                raw = AXMLPrinter(z.read(name)).get_xml()
                decoded_xmls[name] = raw.decode("utf-8", "replace") if isinstance(raw, bytes) else raw
            except Exception as e:
                decoded_xmls[name] = f"<decode error: {e}>"

        base_raw_list = [
            {"name": i.filename, "bytes": i.file_size}
            for i in z.infolist()
            if i.filename.startswith("res/raw/")
        ]

    arsc_stats = {}
    lang_strings = {}
    for apk_name in ("vn.com.vng.zalopay.apk", "config.vi.apk", "config.en.apk", "config.xhdpi.apk"):
        with zipfile.ZipFile(XAPK_DIR / apk_name) as z:
            if "resources.arsc" not in z.namelist():
                arsc_stats[apk_name] = {"present": False}
                continue
            raw_arsc = z.read("resources.arsc")
        arsc = ARSCParser(raw_arsc)
        pkgs = arsc.get_packages_names()
        pkg = pkgs[0] if pkgs else None
        locales = list(arsc.get_locales(pkg)) if pkg else []

        resource_types_counts = {}
        if pkg and pkg in arsc.resource_keys:
            resource_types_counts = {t: len(keys) for t, keys in sorted(arsc.resource_keys[pkg].items())}

        resolved_strings_summary = {}
        if apk_name in ("config.vi.apk", "config.en.apk"):
            try:
                rs = arsc.get_resolved_strings()
                if pkg and pkg in rs:
                    for loc, mapping in rs[pkg].items():
                        loc_lbl = loc if loc != "\x00\x00" else "default"
                        resolved_strings_summary[loc_lbl] = {
                            "count": len(mapping),
                            "sample": {str(k): str(v)[:100] for k, v in list(mapping.items())[:15]},
                        }
            except Exception as e:
                resolved_strings_summary["error"] = str(e)

        arsc_stats[apk_name] = {
            "present": True,
            "bytes": len(raw_arsc),
            "packages": pkgs,
            "locales": [l if l != "\x00\x00" else "default" for l in locales],
            "resource_types_counts": resource_types_counts,
            "total_resource_keys": sum(resource_types_counts.values()),
        }
        if resolved_strings_summary:
            lang_strings[apk_name] = resolved_strings_summary

    with zipfile.ZipFile(XAPK_DIR / "config.xhdpi.apk") as z:
        xhdpi_dirs = Counter(
            i.filename.split("/")[1] if "/" in i.filename and i.filename.startswith("res/") else i.filename
            for i in z.infolist()
        )
        xhdpi_exts = Counter(Path(i.filename).suffix.lower() or "(none)" for i in z.infolist())

    result = {
        "decoded_res_xml": decoded_xmls,
        "res_raw_inventory": sorted(base_raw_list, key=lambda x: -x["bytes"]),
        "resources_arsc": arsc_stats,
        "localization_splits_comparison": lang_strings,
        "config_xhdpi_directories": dict(xhdpi_dirs.most_common()),
        "config_xhdpi_extensions": dict(xhdpi_exts.most_common()),
    }
    (OUT_DIR / "03_resources_and_localization.json").write_text(json.dumps(result, indent=2, ensure_ascii=False))
    return result


# ---------------------------------------------------------------------------
# 4. All 20 DEX Files: Headers, Packages, R8 Obfuscation, Opcodes, SBOM, Security APIs
# ---------------------------------------------------------------------------
def classify_dalvik_opcode(op: int) -> str:
    if op == 0x00:
        return "nop"
    if 0x01 <= op <= 0x0D:
        return "move_and_result"
    if 0x0E <= op <= 0x11:
        return "return"
    if 0x12 <= op <= 0x1C:
        return "const_load"
    if 0x1D <= op <= 0x1E:
        return "monitor_sync"
    if 0x1F <= op <= 0x26:
        return "type_check_and_array"
    if 0x27 <= op <= 0x2D:
        return "throw_goto_switch_cmp"
    if 0x2E <= op <= 0x3D:
        return "branch_if"
    if 0x44 <= op <= 0x51:
        return "array_get_put"
    if 0x52 <= op <= 0x5F:
        return "instance_field_get_put"
    if 0x60 <= op <= 0x6D:
        return "static_field_get_put"
    if 0x6E <= op <= 0x78:
        return "invoke_call"
    if 0x7B <= op <= 0x8F:
        return "unary_conversion"
    if 0x90 <= op <= 0xE2:
        return "binary_arithmetic_logic"
    if op in (0xFA, 0xFB, 0xFC, 0xFD, 0xFE, 0xFF):
        return "invoke_polymorphic_custom"
    return "other"


SEC_PATTERNS = {
    "biometric_auth": ("androidx/biometric/", "android/hardware/biometrics/", "android/hardware/fingerprint/"),
    "android_keystore": ("android/security/keystore/", "java/security/KeyStore"),
    "jca_crypto": ("javax/crypto/", "java/security/Cipher", "java/security/Signature", "java/security/MessageDigest"),
    "tls_pinning_trust": ("okhttp3/CertificatePinner", "javax/net/ssl/X509TrustManager", "javax/net/ssl/SSLContext", "android/net/http/SslError"),
    "play_integrity_safetynet": ("com/google/android/play/core/integrity/", "com/google/android/gms/safetynet/"),
    "sqlcipher_encrypted_db": ("net/sqlcipher/", "net/zetetic/database/"),
    "alipay_griver_miniapp": ("com/alibaba/griver/", "com/alibaba/ariver/", "com/alipay/"),
    "visa_cbp_nfc_hce": ("com/visa/", "android/nfc/cardemulation/"),
    "vng_zalopay_first_party": ("vn/com/vng/zalopay/", "com/zing/zalo/"),
    "trueid_faceauth_ekyc": ("vn/ai/trueid/", "vn/ai/faceauth/"),
    "appsflyer_attribution": ("com/appsflyer/",),
    "google_mlkit_vision": ("com/google/mlkit/", "com/google/android/libraries/vision/", "com/google/android/libraries/barhopper/"),
    "rive_lottie_motion": ("app/rive/runtime/", "com/airbnb/lottie/"),
    "jetpack_compose": ("androidx/compose/",),
    "coroutines_kotlin": ("kotlinx/coroutines/", "kotlin/"),
}


def analyze_single_dex(args):
    dex_name, dex_bytes = args
    magic = dex_bytes[:8].decode("ascii", "replace").rstrip("\x00\n")
    checksum, sha1 = struct.unpack_from("<I20s", dex_bytes, 8)
    (
        file_size,
        header_size,
        endian_tag,
        link_size,
        link_off,
        map_off,
        string_ids_size,
        string_ids_off,
        type_ids_size,
        type_ids_off,
        proto_ids_size,
        proto_ids_off,
        field_ids_size,
        field_ids_off,
        method_ids_size,
        method_ids_off,
        class_defs_size,
        class_defs_off,
        data_size,
        data_off,
    ) = struct.unpack_from("<20I", dex_bytes, 32)

    d = DEX(dex_bytes)
    classes = d.get_classes()
    root_pkgs = Counter()
    sub_pkgs = Counter()
    short_class_names = 0
    obfuscated_pkgs = 0
    total_classes = len(classes)
    sec_api_hits = Counter()

    for cls in classes:
        cname = cls.get_name()
        inner = cname[1:-1] if cname.startswith("L") and cname.endswith(";") else cname
        parts = inner.split("/")
        leaf = parts[-1]
        if len(parts) == 1:
            root_pkgs["(default_root)"] += 1
            sub_pkgs["(default_root)"] += 1
            obfuscated_pkgs += 1
        else:
            root_pkgs[parts[0]] += 1
            prefix3 = "/".join(parts[:3]) if len(parts) >= 3 else "/".join(parts[:2])
            sub_pkgs[prefix3] += 1
            if len(parts[0]) <= 2:
                obfuscated_pkgs += 1
        if len(leaf.split("$")[0]) <= 2:
            short_class_names += 1

        for label, prefixes in SEC_PATTERNS.items():
            if any(inner.startswith(p) for p in prefixes):
                sec_api_hits[label] += 1

    all_strings = d.get_strings()
    sec_type_refs = Counter()
    for i in range(type_ids_size):
        str_idx = struct.unpack_from("<I", dex_bytes, type_ids_off + i * 4)[0]
        t = all_strings[str_idx]
        t_clean = t[1:-1] if t.startswith("L") and t.endswith(";") else t
        for label, prefixes in SEC_PATTERNS.items():
            if any(t_clean.startswith(p) for p in prefixes):
                sec_type_refs[label] += 1

    opcode_counts = Counter()
    family_counts = Counter()
    total_methods = 0
    methods_with_code = 0
    total_instructions = 0
    short_method_names = 0

    for m in d.get_encoded_methods():
        total_methods += 1
        mname = m.get_name()
        if len(mname) <= 2 and not mname.startswith("<"):
            short_method_names += 1
        code = m.get_code()
        if code is not None:
            methods_with_code += 1
            bc = code.get_bc()
            for ins in bc.get_instructions():
                op = ins.get_op_value()
                opcode_counts[ins.get_name()] += 1
                family_counts[classify_dalvik_opcode(op)] += 1
                total_instructions += 1

    return {
        "dex_name": dex_name,
        "magic": magic,
        "file_size": file_size,
        "sha1": sha1.hex(),
        "counts": {
            "string_ids": string_ids_size,
            "type_ids": type_ids_size,
            "proto_ids": proto_ids_size,
            "field_ids": field_ids_size,
            "method_ids": method_ids_size,
            "class_defs": class_defs_size,
            "defined_methods": total_methods,
            "methods_with_code": methods_with_code,
            "total_dalvik_instructions": total_instructions,
        },
        "obfuscation": {
            "total_classes": total_classes,
            "short_class_names_le2": short_class_names,
            "obfuscated_root_pkg_classes": obfuscated_pkgs,
            "short_method_names_le2": short_method_names,
        },
        "root_packages": dict(root_pkgs),
        "sub_packages_top": dict(sub_pkgs.most_common(40)),
        "security_and_framework_classes": dict(sec_api_hits),
        "security_and_framework_type_refs": dict(sec_type_refs),
        "opcode_families": dict(family_counts),
        "top_opcodes": dict(opcode_counts.most_common(30)),
    }


def audit_dex_and_sbom():
    print("[4/6] Auditing ALL 20 DEX files + Dalvik opcodes + META-INF/properties SBOM...")
    base_apk = XAPK_DIR / "vn.com.vng.zalopay.apk"
    dex_inputs = []
    sbom_versions = {}
    root_properties = {}
    meta_services = {}

    with zipfile.ZipFile(base_apk) as z:
        for i in sorted(z.infolist(), key=lambda x: x.filename):
            if i.filename.endswith(".dex"):
                dex_inputs.append((i.filename, z.read(i.filename)))
            elif i.filename.startswith("META-INF/") and i.filename.endswith(".version"):
                sbom_versions[i.filename.replace("META-INF/", "").replace(".version", "")] = z.read(i.filename).decode("utf-8", "replace").strip()
            elif "/" not in i.filename and i.filename.endswith(".properties"):
                txt = z.read(i.filename).decode("utf-8", "replace").strip()
                props = {}
                for line in txt.splitlines():
                    if "=" in line and not line.startswith("#"):
                        k, v = line.split("=", 1)
                        props[k.strip()] = v.strip()
                root_properties[i.filename] = props
            elif i.filename.startswith("META-INF/services/"):
                meta_services[i.filename.replace("META-INF/services/", "")] = [
                    l.strip() for l in z.read(i.filename).decode("utf-8", "replace").splitlines() if l.strip() and not l.startswith("#")
                ]
        kotlin_tooling = json.loads(z.read("kotlin-tooling-metadata.json").decode("utf-8"))

    with ProcessPoolExecutor(max_workers=4) as pool:
        per_dex = list(pool.map(analyze_single_dex, dex_inputs))

    agg_root = Counter()
    agg_sub = Counter()
    agg_sec_cls = Counter()
    agg_sec_refs = Counter()
    agg_fam = Counter()
    agg_ops = Counter()
    tot = Counter()
    aud_dex = None

    for d in per_dex:
        if d["dex_name"] == "assets/audience_network.dex":
            aud_dex = d
            continue
        for k, v in d["counts"].items():
            tot[k] += v
        tot["total_dex_bytes"] += d["file_size"]
        tot["short_class_names_le2"] += d["obfuscation"]["short_class_names_le2"]
        tot["obfuscated_root_pkg_classes"] += d["obfuscation"]["obfuscated_root_pkg_classes"]
        tot["short_method_names_le2"] += d["obfuscation"]["short_method_names_le2"]
        agg_root.update(d["root_packages"])
        agg_sub.update(d["sub_packages_top"])
        agg_sec_cls.update(d["security_and_framework_classes"])
        agg_sec_refs.update(d["security_and_framework_type_refs"])
        agg_fam.update(d["opcode_families"])
        agg_ops.update(d["top_opcodes"])

    result = {
        "kotlin_tooling_metadata": kotlin_tooling,
        "app_dex_aggregate": {
            "dex_count": sum(1 for d in per_dex if d["dex_name"] != "assets/audience_network.dex"),
            "totals": dict(tot),
            "obfuscation_rates": {
                "short_class_name_pct": round(100.0 * tot["short_class_names_le2"] / max(1, tot["class_defs"]), 2),
                "obfuscated_root_pkg_pct": round(100.0 * tot["obfuscated_root_pkg_classes"] / max(1, tot["class_defs"]), 2),
                "short_method_name_pct": round(100.0 * tot["short_method_names_le2"] / max(1, tot["defined_methods"]), 2),
            },
            "root_packages_top25": dict(agg_root.most_common(25)),
            "sub_packages_top60": dict(agg_sub.most_common(60)),
            "security_and_framework_classes": dict(agg_sec_cls),
            "security_and_framework_type_refs": dict(agg_sec_refs),
            "opcode_families": dict(agg_fam.most_common()),
            "top_opcodes": dict(agg_ops.most_common(30)),
        },
        "audience_network_dynamic_dex": aud_dex,
        "per_dex_summary": [
            {k: v for k, v in d.items() if k not in ("sub_packages_top", "root_packages", "top_opcodes")}
            for d in per_dex
        ],
        "sbom": {
            "meta_inf_version_count": len(sbom_versions),
            "meta_inf_versions": sbom_versions,
            "root_properties_count": len(root_properties),
            "root_properties": root_properties,
            "meta_inf_services_count": len(meta_services),
            "meta_inf_services": meta_services,
        },
    }
    (OUT_DIR / "04_dex_architecture_and_sbom.json").write_text(json.dumps(result, indent=2, ensure_ascii=False))
    return result


# ---------------------------------------------------------------------------
# 5. All 27 Native ELF Libraries + Assets Deep Inspection
# ---------------------------------------------------------------------------
def inspect_arm_text_capstone(text_bytes: bytes, base_addr: int, sample_cap: int = 262144):
    chunk = text_bytes[:sample_cap]
    md_thumb = Cs(CS_ARCH_ARM, CS_MODE_THUMB)
    md_arm = Cs(CS_ARCH_ARM, CS_MODE_ARM)

    def profile(md):
        cats = Counter()
        count = 0
        svcs = 0
        for ins in md.disasm(chunk, base_addr):
            count += 1
            mnem = ins.mnemonic
            if mnem.startswith("svc"):
                svcs += 1
                cats["syscall_svc"] += 1
            elif mnem.startswith(("b", "bl", "bx", "cbz", "cbnz", "it")):
                cats["control_flow"] += 1
            elif mnem.startswith(("ldr", "str", "ldm", "stm", "push", "pop")):
                cats["memory_load_store"] += 1
            elif mnem.startswith(("v", "f")):
                cats["neon_vfp_simd"] += 1
            elif mnem.startswith(("add", "sub", "mul", "mla", "sdiv", "udiv")):
                cats["arithmetic"] += 1
            elif mnem.startswith(("and", "orr", "eor", "bic", "lsl", "lsr", "asr", "ror", "mov", "mvn", "cmp", "tst")):
                cats["logic_move_cmp"] += 1
            else:
                cats["other"] += 1
        return count, svcs, dict(cats)

    t_cnt, t_svc, t_cats = profile(md_thumb)
    a_cnt, a_svc, a_cats = profile(md_arm)
    mode = "thumb2" if t_cnt >= a_cnt else "arm32"
    return {
        "sampled_bytes": len(chunk),
        "dominant_isa_mode": mode,
        "thumb_decoded_ins": t_cnt,
        "thumb_svc_count": t_svc,
        "thumb_categories": t_cats,
        "arm_decoded_ins": a_cnt,
        "arm_svc_count": a_svc,
        "arm_categories": a_cats,
    }


def extract_safe_string_markers(data: bytes):
    ascii_strings = re.findall(rb"[ -~]{5,120}", data)
    decoded = [s.decode("ascii", "ignore") for s in ascii_strings]
    KEYWORDS = (
        "Android clang", "GCC:", "NDK", "OpenCV", "NCNN", "ncnn", "SQLCipher", "SQLite",
        "Rive", "HarfBuzz", "miniaudio", "Crashlytics", "Breakpad", "Crashpad",
        "SecurityGuard", "Alipay", "Griver", "Visa", "TrueID", "VFace", "Fresco",
        "Barhopper", "MLKit", "Drishti", "MediaPipe", "FlatBuffers", "TensorFlow",
        "JNI_OnLoad", "BoringSSL", "OpenSSL", "mbedTLS", "libcrypto", "frida", "xposed",
        "magisk", "/proc/self/maps", "ptrace", "ro.debuggable", "SSL_CTX", "X25519",
    )
    hits = Counter()
    samples = {}
    for s in decoded:
        for kw in KEYWORDS:
            if kw in s:
                hits[kw] += 1
                if kw not in samples and len(s) < 100:
                    samples[kw] = s
    return {
        "total_printable_strings_ge5": len(decoded),
        "keyword_hits": dict(hits),
        "sample_markers": samples,
    }


def audit_native_and_assets():
    print("[5/6] Auditing all 27 ARMv7 ELF libraries (LIEF + PyELFtools + Capstone) & 124 assets...")
    so_dir = EXTRACTED_DIR / "armeabi-v7a/lib/armeabi-v7a"
    native_audit = {}
    for so_path in sorted(so_dir.glob("*.so")):
        raw = so_path.read_bytes()
        bin_lief = lief.parse(str(so_path))
        sections = []
        text_bytes = b""
        text_va = 0
        with open(so_path, "rb") as f:
            elf = ELFFile(f)
            for sec in elf.iter_sections():
                sdata = sec.data() if sec.header["sh_type"] != "SHT_NOBITS" else b""
                ent = round(shannon_entropy(sdata), 4) if sdata else 0.0
                sections.append({
                    "name": sec.name,
                    "type": sec.header["sh_type"],
                    "size": sec.header["sh_size"],
                    "entropy": ent,
                    "flags": sec.header["sh_flags"],
                })
                if sec.name == ".text":
                    text_bytes = sdata
                    text_va = sec.header["sh_addr"]

        exported_funcs = [s.name for s in bin_lief.exported_functions] if bin_lief else []
        jni_exports = [n for n in exported_funcs if n.startswith(("Java_", "JNI_OnLoad", "JNI_OnUnload"))]
        imported_funcs = [s.name for s in bin_lief.imported_functions] if bin_lief else []
        capstone_stats = inspect_arm_text_capstone(text_bytes, text_va) if text_bytes else None
        markers = extract_safe_string_markers(raw)

        native_audit[so_path.name] = {
            "bytes": len(raw),
            "sha256": sha256_bytes(raw),
            "overall_entropy": round(shannon_entropy(raw), 4),
            "section_count": len(sections),
            "sections_top": sorted(sections, key=lambda x: -x["size"])[:12],
            "exported_functions_count": len(exported_funcs),
            "jni_exports": jni_exports[:40],
            "imported_functions_count": len(imported_funcs),
            "imported_sample": imported_funcs[:25],
            "capstone_text_profile": capstone_stats,
            "toolchain_and_telemetry_markers": markers,
        }

    base_apk = XAPK_DIR / "vn.com.vng.zalopay.apk"
    assets_audit = []
    griver_amrs = {}
    ncnn_param_summary = {}
    android_1_zip_summary = {}
    config_json_schemas = {}

    with zipfile.ZipFile(base_apk) as z:
        for i in sorted(z.infolist(), key=lambda x: x.filename):
            if not i.filename.startswith("assets/"):
                continue
            data = z.read(i.filename)
            magic_hex = data[:16].hex()
            ent = round(shannon_entropy(data), 4)
            assets_audit.append({
                "path": i.filename,
                "bytes": len(data),
                "sha256": sha256_bytes(data),
                "entropy": ent,
                "magic_hex": magic_hex,
            })

            if i.filename.endswith(".amr"):
                amr_info = {"bytes": len(data), "entropy": ent, "magic_hex": magic_hex}
                try:
                    with tarfile.open(fileobj=io.BytesIO(data), mode="r:*") as tf:
                        members = tf.getmembers()
                        amr_info["format"] = "tar"
                        amr_info["members_count"] = len(members)
                        amr_info["members"] = [{"name": m.name, "size": m.size} for m in members[:30]]
                except Exception:
                    try:
                        with zipfile.ZipFile(io.BytesIO(data)) as az:
                            amr_info["format"] = "zip"
                            amr_info["members_count"] = len(az.infolist())
                            amr_info["members"] = [{"name": m.filename, "size": m.file_size} for m in az.infolist()[:30]]
                    except Exception:
                        amr_info["format"] = "encrypted_or_custom_container"
                griver_amrs[i.filename] = amr_info

            elif i.filename == "assets/android.1.zip":
                try:
                    with zipfile.ZipFile(io.BytesIO(data)) as az:
                        android_1_zip_summary = {
                            "format": "zip",
                            "entry_count": len(az.infolist()),
                            "entries_sample": [{"name": m.filename, "size": m.file_size} for m in az.infolist()[:40]],
                        }
                except Exception as e:
                    android_1_zip_summary = {"format": "encrypted_or_non_standard_zip", "error": str(e), "magic_hex": magic_hex}

            elif i.filename == "assets/FastestDet.ncnn.param":
                lines = [l.strip() for l in data.decode("utf-8", "replace").splitlines() if l.strip()]
                layer_types = Counter(l.split()[0] for l in lines[2:] if l.split())
                ncnn_param_summary = {
                    "magic": lines[0] if lines else None,
                    "layer_and_blob_counts": lines[1] if len(lines) > 1 else None,
                    "total_lines": len(lines),
                    "layer_type_histogram": dict(layer_types.most_common()),
                }

            elif i.filename in ("assets/config/miniapp_config.json", "assets/config/zalopay_config.json", "assets/Griver/appx.json", "assets/GriverTheme/theme.json", "assets/bundle.json"):
                try:
                    parsed = json.loads(data.decode("utf-8"))
                    if isinstance(parsed, dict):
                        config_json_schemas[i.filename] = {
                            "type": "object",
                            "top_keys": list(parsed.keys())[:40],
                            "key_count": len(parsed),
                        }
                    elif isinstance(parsed, list):
                        config_json_schemas[i.filename] = {
                            "type": "array",
                            "length": len(parsed),
                            "sample_keys": list(parsed[0].keys())[:20] if parsed and isinstance(parsed[0], dict) else None,
                        }
                except Exception as e:
                    config_json_schemas[i.filename] = {"parse_error": str(e)}

    result = {
        "native_libraries_count": len(native_audit),
        "native_libraries": native_audit,
        "assets_count": len(assets_audit),
        "assets_inventory": assets_audit,
        "griver_amr_inspection": griver_amrs,
        "android_1_zip_inspection": android_1_zip_summary,
        "ncnn_model_inspection": ncnn_param_summary,
        "config_json_schemas": config_json_schemas,
    }
    (OUT_DIR / "05_native_elf_and_assets_deep.json").write_text(json.dumps(result, indent=2, ensure_ascii=False))
    return result


def main():
    b = audit_bundle_and_apks()
    m = audit_manifests()
    r = audit_resources_and_xml()
    d = audit_dex_and_sbom()
    n = audit_native_and_assets()
    print("[6/6] ALL DEEP STATIC AUDIT REPORTS GENERATED IN research/evidence/deep/")
    print("  - Base APK DEX totals:", d["app_dex_aggregate"]["totals"])
    print("  - Obfuscation rates:", d["app_dex_aggregate"]["obfuscation_rates"])
    print("  - Components summary:", m["vn.com.vng.zalopay.apk"]["component_summary"])
    print("  - Native .so count:", n["native_libraries_count"])
    print("  - Assets count:", n["assets_count"])


if __name__ == "__main__":
    main()
