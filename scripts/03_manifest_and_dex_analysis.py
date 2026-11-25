#!/usr/bin/env python3
"""
Deep static reverse engineering of ALL 5 AndroidManifest.xml files,
ALL 4 resources.arsc tables, and ALL 20 Dalvik (.dex) files using Androguard.
Focuses strictly on architectural taxonomy, permissions, component surfaces,
DEX metrics, third-party SDK ecosystem, R8/ProGuard obfuscation metrics, and API footprint.
"""
import hashlib
import json
import math
import os
import re
import struct
import zipfile
from collections import Counter, defaultdict
from pathlib import Path

from androguard.core.axml import ARSCParser, AXMLPrinter
from androguard.core.dex import DEX
from lxml import etree

ROOT = Path("/home/user/-")
XAPK_DIR = ROOT / "tmp_work/xapk_contents"
APKS_DIR = ROOT / "tmp_work/apks"

DANGEROUS_PERMISSIONS = {
    "android.permission.READ_CONTACTS",
    "android.permission.WRITE_CONTACTS",
    "android.permission.ACCESS_FINE_LOCATION",
    "android.permission.ACCESS_COARSE_LOCATION",
    "android.permission.CAMERA",
    "android.permission.RECORD_AUDIO",
    "android.permission.READ_PHONE_STATE",
    "android.permission.READ_BASIC_PHONE_STATE",
    "android.permission.READ_EXTERNAL_STORAGE",
    "android.permission.WRITE_EXTERNAL_STORAGE",
    "android.permission.READ_CALENDAR",
    "android.permission.WRITE_CALENDAR",
    "android.permission.POST_NOTIFICATIONS",
    "android.permission.READ_MEDIA_AUDIO",
}

SPECIAL_OR_SENSITIVE_PERMISSIONS = {
    "android.permission.QUERY_ALL_PACKAGES",
    "android.settings.action.MANAGE_OVERLAY_PERMISSION",
    "android.permission.USE_BIOMETRIC",
    "android.permission.USE_FINGERPRINT",
    "android.permission.NFC",
    "android.permission.DETECT_SCREEN_CAPTURE",
    "com.zing.zalo.permission.ACCESS_THIRD_PARTY_APP_AUTHORIZATION",
    "com.google.android.gms.permission.AD_ID",
    "android.permission.ACCESS_ADSERVICES_ATTRIBUTION",
    "android.permission.ACCESS_ADSERVICES_AD_ID",
    "android.permission.ACCESS_ADSERVICES_CUSTOM_AUDIENCE",
    "android.permission.ACCESS_ADSERVICES_TOPICS",
}

SDK_TAXONOMY_RULES = [
    ("ZaloPay First-Party", ("vn.com.vng.zalopay", "vn.zalopay", "com.vng.zalopay", "vn.vng.zalopay")),
    ("VNG / Zalo Ecosystem", ("com.zing.", "com.vng.", "vn.com.vng.")),
    ("Ant Group / Alipay+ / Griver", ("com.alibaba.", "com.alipay.", "com.iap.", "com.anta.", "com.taoba", "com.uc.", "com.ut.")),
    ("AndroidX / Jetpack / Kotlin", ("androidx.", "kotlin.", "kotlinx.", "android.support.")),
    ("Google / GMS / Firebase / MLKit", ("com.google.", "com.android.billingclient", "com.android.installreferrer")),
    ("React Native / Hermes / Facebook", ("com.facebook.", "com.swmansion.", "com.reactnative", "com.horcrux.", "com.oblador.", "com.BV.", "com.learnium.")),
    ("Payments / Card / eKYC / Biometrics", ("com.visa.", "org.bouncycastle.", "net.sqlcipher.", "com.trueid", "vn.vnpt", "com.zing.zalo.zalosdk", "com.nimbusds", "io.jsonwebtoken")),
    ("Analytics / Attribution / Crash", ("com.appsflyer.", "com.clevertap.", "io.sentry.", "com.adjust.", "com.mixpanel.", "com.datadog.")),
    ("Networking / Serialization / Concurrency", ("okhttp3.", "okio.", "retrofit2.", "com.squareup.", "io.reactivex.", "com.google.gson", "com.fasterxml.", "io.grpc.", "io.netty.", "org.apache.", "com.airbnb.lottie", "app.rive.")),
    ("Bytecode / Obfuscated Short Names", ()),
]


def classify_package(pkg: str) -> str:
    if not pkg:
        return "Default / Root Package"
    first_seg = pkg.split(".")[0]
    if len(first_seg) <= 2 and first_seg not in ("io", "vn"):
        return "R8/ProGuard Short-Obfuscated Namespace"
    for cat, prefixes in SDK_TAXONOMY_RULES:
        if prefixes and pkg.startswith(prefixes):
            return cat
    return "Other Third-Party Libraries"


def parse_manifest(manifest_bytes: bytes) -> dict:
    axml = AXMLPrinter(manifest_bytes)
    xml_obj = axml.get_xml_obj()
    ns = "{http://schemas.android.com/apk/res/android}"

    pkg = xml_obj.attrib.get("package")
    ver_code = xml_obj.attrib.get(f"{ns}versionCode")
    ver_name = xml_obj.attrib.get(f"{ns}versionName")
    compile_sdk = xml_obj.attrib.get(f"{ns}compileSdkVersion")
    compile_sdk_codename = xml_obj.attrib.get(f"{ns}compileSdkVersionCodename")
    split_name = xml_obj.attrib.get("split")

    uses_sdk = xml_obj.find("uses-sdk")
    min_sdk = uses_sdk.attrib.get(f"{ns}minSdkVersion") if uses_sdk is not None else None
    target_sdk = uses_sdk.attrib.get(f"{ns}targetSdkVersion") if uses_sdk is not None else None

    perms = []
    for p in xml_obj.findall("uses-permission") + xml_obj.findall("uses-permission-sdk-23"):
        name = p.attrib.get(f"{ns}name")
        if name:
            cat = (
                "dangerous" if name in DANGEROUS_PERMISSIONS
                else "sensitive_or_oem" if name in SPECIAL_OR_SENSITIVE_PERMISSIONS or not name.startswith("android.permission.")
                else "normal"
            )
            perms.append({"name": name, "category": cat})

    custom_perms = [
        {
            "name": p.attrib.get(f"{ns}name"),
            "protectionLevel": p.attrib.get(f"{ns}protectionLevel"),
        }
        for p in xml_obj.findall("permission")
    ]

    features = [
        {
            "name": f.attrib.get(f"{ns}name"),
            "required": f.attrib.get(f"{ns}required"),
            "glEsVersion": f.attrib.get(f"{ns}glEsVersion"),
        }
        for f in xml_obj.findall("uses-feature")
    ]

    queries_pkgs = [
        p.attrib.get(f"{ns}name")
        for q in xml_obj.findall("queries")
        for p in q.findall("package")
        if p.attrib.get(f"{ns}name")
    ]
    queries_intents = len([
        i for q in xml_obj.findall("queries") for i in q.findall("intent")
    ])

    app = xml_obj.find("application")
    app_flags = {}
    components = {"activity": [], "activity-alias": [], "service": [], "receiver": [], "provider": []}
    schemes = set()
    hosts = set()
    meta_data_keys = []

    if app is not None:
        app_flags = {
            "name": app.attrib.get(f"{ns}name"),
            "allowBackup": app.attrib.get(f"{ns}allowBackup"),
            "debuggable": app.attrib.get(f"{ns}debuggable"),
            "usesCleartextTraffic": app.attrib.get(f"{ns}usesCleartextTraffic"),
            "networkSecurityConfig": app.attrib.get(f"{ns}networkSecurityConfig"),
            "largeHeap": app.attrib.get(f"{ns}largeHeap"),
            "extractNativeLibs": app.attrib.get(f"{ns}extractNativeLibs"),
            "requestLegacyExternalStorage": app.attrib.get(f"{ns}requestLegacyExternalStorage"),
            "enableOnBackInvokedCallback": app.attrib.get(f"{ns}enableOnBackInvokedCallback"),
        }
        for md in app.findall("meta-data"):
            k = md.attrib.get(f"{ns}name")
            if k:
                meta_data_keys.append(k)
        for comp_type in components:
            for elem in app.findall(comp_type):
                name = elem.attrib.get(f"{ns}name")
                exported = elem.attrib.get(f"{ns}exported")
                perm = elem.attrib.get(f"{ns}permission")
                proc = elem.attrib.get(f"{ns}process")
                intent_filters = elem.findall("intent-filter")
                has_if = len(intent_filters) > 0
                for inf in intent_filters:
                    for data in inf.findall("data"):
                        sc = data.attrib.get(f"{ns}scheme")
                        hs = data.attrib.get(f"{ns}host")
                        if sc:
                            schemes.add(sc)
                        if hs and not hs.startswith("@"):
                            hosts.add(hs)
                components[comp_type].append({
                    "name": name,
                    "exported": exported,
                    "permission": perm,
                    "process": proc,
                    "intent_filter_count": len(intent_filters),
                    "implicitly_or_explicitly_exported": (exported == "true") or (exported is None and has_if),
                })

    comp_summary = {}
    for k, lst in components.items():
        comp_summary[k] = {
            "total": len(lst),
            "exported_true": sum(1 for c in lst if c["exported"] == "true"),
            "exported_false": sum(1 for c in lst if c["exported"] == "false"),
            "permission_protected": sum(1 for c in lst if c["permission"] is not None),
            "custom_process_isolated": sum(1 for c in lst if c["process"] is not None),
        }

    return {
        "package": pkg,
        "split": split_name,
        "versionCode": ver_code,
        "versionName": ver_name,
        "compileSdkVersion": compile_sdk,
        "compileSdkVersionCodename": compile_sdk_codename,
        "minSdkVersion": min_sdk,
        "targetSdkVersion": target_sdk,
        "permissions_total": len(perms),
        "permissions_by_category": dict(Counter(p["category"] for p in perms)),
        "permissions": perms,
        "custom_permissions": custom_perms,
        "uses_features": features,
        "queries_package_count": len(queries_pkgs),
        "queries_packages_sample": queries_pkgs[:40],
        "queries_intent_count": queries_intents,
        "application_security_flags": app_flags,
        "component_summary": comp_summary,
        "isolated_processes": sorted({c["process"] for lst in components.values() for c in lst if c["process"]}),
        "deep_link_schemes": sorted(schemes),
        "deep_link_hosts_count": len(hosts),
        "deep_link_hosts_sample": sorted(hosts)[:30],
        "meta_data_count": len(meta_data_keys),
        "meta_data_prefixes": dict(Counter(".".join(k.split(".")[:2]) for k in meta_data_keys).most_common(20)),
    }


def analyze_dex_files() -> dict:
    dex_paths = sorted(
        list((APKS_DIR / "vn.com.vng.zalopay").glob("classes*.dex"))
        + [APKS_DIR / "vn.com.vng.zalopay/assets/audience_network.dex"],
        key=lambda p: (0 if p.name.startswith("classes") else 1, p.name),
    )

    per_dex = []
    pkg_counter = Counter()
    sdk_class_counter = Counter()
    all_classes_count = 0
    all_methods_count = 0
    all_fields_count = 0
    all_strings_count = 0
    short_obf_classes = 0
    kotlin_classes = 0
    compose_classes = 0
    crypto_api_refs = Counter()
    network_api_refs = Counter()
    webview_api_refs = Counter()

    for dp in dex_paths:
        raw = dp.read_bytes()
        magic = raw[:8].decode("ascii", "replace").replace("\n", "\\n")
        # DEX header offsets:
        # 0x38: string_ids_size, 0x40: type_ids_size, 0x48: proto_ids_size,
        # 0x50: field_ids_size, 0x58: method_ids_size, 0x60: class_defs_size
        string_ids, _, type_ids, _, proto_ids, _, field_ids, _, method_ids, _, class_defs, _ = struct.unpack_from(
            "<12I", raw, 0x38
        )
        d = DEX(raw)
        classes = list(d.get_classes())
        obf_cnt = 0
        kt_cnt = 0
        for c in classes:
            cname = c.get_name()  # e.g. Lcom/foo/Bar;
            if not (cname.startswith("L") and cname.endswith(";")):
                continue
            fqcn = cname[1:-1].replace("/", ".")
            parts = fqcn.split(".")
            cls_simple = parts[-1]
            pkg = ".".join(parts[:-1])
            top2 = ".".join(parts[:2]) if len(parts) >= 2 else parts[0]
            pkg_counter[top2] += 1
            sdk_cat = classify_package(pkg)
            sdk_class_counter[sdk_cat] += 1

            # Check obfuscation heuristic: short 1-2 char simple class name or short obfuscated package
            base_simple = cls_simple.split("$")[0]
            if len(base_simple) <= 2 or sdk_cat == "R8/ProGuard Short-Obfuscated Namespace":
                obf_cnt += 1
            if "$Companion" in cls_simple or cls_simple.endswith("Kt") or "$lambda" in cls_simple:
                kt_cnt += 1
            if "compose" in fqcn.lower():
                compose_classes += 1

        # Scan method & class references safely for architectural API usage (aggregate counts only)
        for s in d.get_strings():
            if not isinstance(s, str):
                continue
            if s.startswith(("Ljavax/crypto/", "Ljava/security/")):
                crypto_api_refs[s.split(";")[0][1:].replace("/", ".")] += 1
            elif s.startswith(("Lokhttp3/", "Lretrofit2/", "Ljava/net/HttpURLConnection", "Ljavax/net/ssl/")):
                network_api_refs[s.split(";")[0][1:].replace("/", ".")] += 1
            elif s.startswith(("Landroid/webkit/WebView", "Landroid/webkit/JavascriptInterface")):
                webview_api_refs[s.split(";")[0][1:].replace("/", ".")] += 1

        all_classes_count += class_defs
        all_methods_count += method_ids
        all_fields_count += field_ids
        all_strings_count += string_ids
        short_obf_classes += obf_cnt
        kotlin_classes += kt_cnt

        per_dex.append({
            "filename": str(dp.relative_to(APKS_DIR / "vn.com.vng.zalopay")),
            "size_bytes": len(raw),
            "sha256": hashlib.sha256(raw).hexdigest(),
            "dex_magic": magic,
            "string_ids_count": string_ids,
            "type_ids_count": type_ids,
            "proto_ids_count": proto_ids,
            "field_ids_count": field_ids,
            "method_ids_count": method_ids,
            "class_defs_count": class_defs,
            "obfuscated_short_classes": obf_cnt,
            "obfuscated_ratio": round(obf_cnt / class_defs, 4) if class_defs else 0.0,
            "kotlin_marker_classes": kt_cnt,
        })

    return {
        "total_dex_files": len(per_dex),
        "total_dex_bytes": sum(x["size_bytes"] for x in per_dex),
        "aggregate_counts": {
            "total_class_defs": all_classes_count,
            "total_method_ids_sum": all_methods_count,
            "total_field_ids_sum": all_fields_count,
            "total_string_ids_sum": all_strings_count,
            "short_obfuscated_classes": short_obf_classes,
            "short_obfuscated_ratio": round(short_obf_classes / all_classes_count, 4) if all_classes_count else 0.0,
            "kotlin_marker_classes": kotlin_classes,
            "compose_related_classes": compose_classes,
        },
        "per_dex_metrics": per_dex,
        "sdk_ecosystem_class_distribution": dict(sdk_class_counter.most_common()),
        "top_40_package_prefixes": dict(pkg_counter.most_common(40)),
        "crypto_api_class_refs_top15": dict(crypto_api_refs.most_common(15)),
        "network_api_class_refs_top15": dict(network_api_refs.most_common(15)),
        "webview_api_class_refs": dict(webview_api_refs.most_common(15)),
    }


def analyze_resources() -> dict:
    res_report = {}
    for apk_dir in sorted(APKS_DIR.iterdir()):
        arsc_path = apk_dir / "resources.arsc"
        if not arsc_path.exists():
            continue
        raw = arsc_path.read_bytes()
        arsc = ARSCParser(raw)
        pkgs = arsc.get_packages_names()
        pkg_info = {}
        for p in pkgs:
            locales = sorted(arsc.get_locales(p))
            types = sorted(arsc.get_types(p, "\x00\x00")) if "\x00\x00" in locales else []
            pkg_info[p] = {
                "locales_count": len(locales),
                "locales": [l if l != "\x00\x00" else "default" for l in locales],
                "default_types": types,
            }
        res_report[apk_dir.name] = {
            "arsc_size_bytes": len(raw),
            "string_pool_count": len(arsc.get_string_pool_main() or []) if hasattr(arsc, "get_string_pool_main") else None,
            "packages": pkg_info,
        }
    return res_report


def main():
    print("Parsing all 5 AndroidManifest.xml files...")
    manifests = {}
    for apk_dir in sorted(APKS_DIR.iterdir()):
        mpath = apk_dir / "AndroidManifest.xml"
        if mpath.exists():
            manifests[apk_dir.name] = parse_manifest(mpath.read_bytes())

    print("Parsing all 4 resources.arsc tables...")
    resources = analyze_resources()

    manifest_out = ROOT / "data/manifest_analysis.json"
    manifest_out.write_text(json.dumps({
        "manifests": manifests,
        "resources_arsc": resources,
    }, indent=2, ensure_ascii=False))
    print(f"Wrote {manifest_out}")

    print("Analyzing all 20 DEX files...")
    dex_data = analyze_dex_files()
    dex_out = ROOT / "data/dex_analysis.json"
    dex_out.write_text(json.dumps(dex_data, indent=2, ensure_ascii=False))
    print(f"Wrote {dex_out}")
    print("DEX Aggregate:", json.dumps(dex_data["aggregate_counts"], indent=2))
    print("SDK Distribution:", json.dumps(dex_data["sdk_ecosystem_class_distribution"], indent=2))


if __name__ == "__main__":
    main()
