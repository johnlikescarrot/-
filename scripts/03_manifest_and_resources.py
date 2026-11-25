#!/usr/bin/env python3
"""Deep AndroidManifest.xml & resources.arsc analysis across all 5 split APKs."""

import json
import xml.etree.ElementTree as ET
from collections import Counter, defaultdict
from pathlib import Path

from loguru import logger
logger.disable('androguard')
from androguard.core.apk import APK
from androguard.core.axml import AXMLPrinter, ARSCParser

ANDROID_NS = "{http://schemas.android.com/apk/res/android}"
REPO_ROOT = Path.cwd()
XAPK_DIR = Path("/tmp/zalopay_corpus/xapk")
OUT_DIR = REPO_ROOT / "data"

PERMISSION_CATEGORIES = {
    "android.permission.INTERNET": "Network & Connectivity",
    "android.permission.ACCESS_NETWORK_STATE": "Network & Connectivity",
    "android.permission.ACCESS_WIFI_STATE": "Network & Connectivity",
    "android.permission.CHANGE_WIFI_STATE": "Network & Connectivity",
    "android.permission.NFC": "Payments & Hardware Security",
    "android.permission.USE_BIOMETRIC": "Payments & Hardware Security",
    "android.permission.USE_FINGERPRINT": "Payments & Hardware Security",
    "android.permission.DETECT_SCREEN_CAPTURE": "Anti-Fraud & Screen Integrity",
    "android.settings.action.MANAGE_OVERLAY_PERMISSION": "Anti-Fraud & Screen Integrity",
    "android.permission.CAMERA": "Camera, Audio & Biometric eKYC",
    "android.permission.RECORD_AUDIO": "Camera, Audio & Biometric eKYC",
    "android.permission.MODIFY_AUDIO_SETTINGS": "Camera, Audio & Biometric eKYC",
    "android.permission.ACCESS_FINE_LOCATION": "Location & Risk Telemetry",
    "android.permission.ACCESS_COARSE_LOCATION": "Location & Risk Telemetry",
    "android.permission.READ_CONTACTS": "Contacts, Calendar & Storage",
    "android.permission.WRITE_CONTACTS": "Contacts, Calendar & Storage",
    "android.permission.READ_CALENDAR": "Contacts, Calendar & Storage",
    "android.permission.WRITE_CALENDAR": "Contacts, Calendar & Storage",
    "android.permission.READ_EXTERNAL_STORAGE": "Contacts, Calendar & Storage",
    "android.permission.WRITE_EXTERNAL_STORAGE": "Contacts, Calendar & Storage",
    "android.permission.READ_MEDIA_AUDIO": "Contacts, Calendar & Storage",
    "android.permission.READ_PHONE_STATE": "Device & Telephony Identity",
    "android.permission.READ_BASIC_PHONE_STATE": "Device & Telephony Identity",
    "android.permission.QUERY_ALL_PACKAGES": "Installed App Visibility & Ecosystem",
    "com.zing.zalo.permission.ACCESS_THIRD_PARTY_APP_AUTHORIZATION": "Installed App Visibility & Ecosystem",
}


def classify_permission(name: str) -> str:
    if name in PERMISSION_CATEGORIES:
        return PERMISSION_CATEGORIES[name]
    if "ADSERVICES" in name or "AD_ID" in name or "INSTALL_REFERRER" in name:
        return "Advertising & Attribution"
    if "BILLING" in name or "c2dm" in name or "POST_NOTIFICATIONS" in name or "BOOT" in name or "WAKE_LOCK" in name or "FOREGROUND" in name:
        return "Background, Push & Billing"
    if "launcher" in name.lower() or "samsung" in name.lower() or "huawei" in name.lower() or "bbk" in name.lower():
        return "OEM / Launcher Vendor Extensions"
    return "System / Misc"


def parse_intent_filters(elem):
    filters = []
    for iflt in elem.findall("intent-filter"):
        actions = [a.get(ANDROID_NS + "name") for a in iflt.findall("action") if a.get(ANDROID_NS + "name")]
        cats = [c.get(ANDROID_NS + "name") for c in iflt.findall("category") if c.get(ANDROID_NS + "name")]
        data_items = []
        for d in iflt.findall("data"):
            dinfo = {}
            for k in ("scheme", "host", "port", "path", "pathPrefix", "pathPattern", "mimeType"):
                val = d.get(ANDROID_NS + k)
                if val is not None:
                    dinfo[k] = val
            if dinfo:
                data_items.append(dinfo)
        filters.append({"actions": actions, "categories": cats, "data": data_items})
    return filters


def analyze_apk(apk_path: Path) -> dict:
    apk = APK(str(apk_path))
    xml_root = apk.get_android_manifest_xml()
    app = xml_root.find("application")

    uses_sdk = xml_root.find("uses-sdk")
    sdk_info = {
        "package": xml_root.get("package"),
        "version_code": xml_root.get(ANDROID_NS + "versionCode"),
        "version_name": xml_root.get(ANDROID_NS + "versionName"),
        "compile_sdk_version": xml_root.get(ANDROID_NS + "compileSdkVersion"),
        "compile_sdk_codename": xml_root.get(ANDROID_NS + "compileSdkVersionCodename"),
        "split_name": xml_root.get("split"),
        "is_feature_split": xml_root.get(ANDROID_NS + "isFeatureSplit"),
        "isolated_splits": xml_root.get(ANDROID_NS + "isolatedSplits"),
        "min_sdk": uses_sdk.get(ANDROID_NS + "minSdkVersion") if uses_sdk is not None else apk.get_min_sdk_version(),
        "target_sdk": uses_sdk.get(ANDROID_NS + "targetSdkVersion") if uses_sdk is not None else apk.get_target_sdk_version(),
    }

    app_attrs = {}
    if app is not None:
        for attr in (
            "name", "allowBackup", "usesCleartextTraffic", "networkSecurityConfig",
            "extractNativeLibs", "largeHeap", "requestLegacyExternalStorage",
            "preserveLegacyExternalStorage", "appComponentFactory", "hasCode",
            "supportsRtl", "hardwareAccelerated", "debuggable", "testOnly",
        ):
            val = app.get(ANDROID_NS + attr)
            if val is not None:
                app_attrs[attr] = val

    perms = []
    for tag in ("uses-permission", "uses-permission-sdk-23"):
        for p in xml_root.findall(tag):
            pname = p.get(ANDROID_NS + "name")
            if pname:
                perms.append({
                    "name": pname,
                    "tag": tag,
                    "max_sdk": p.get(ANDROID_NS + "maxSdkVersion"),
                    "category": classify_permission(pname),
                })

    custom_perms = [
        {
            "name": p.get(ANDROID_NS + "name"),
            "protectionLevel": p.get(ANDROID_NS + "protectionLevel"),
        }
        for p in xml_root.findall("permission")
    ]

    features = [
        {
            "name": f.get(ANDROID_NS + "name"),
            "required": f.get(ANDROID_NS + "required"),
        }
        for f in xml_root.findall("uses-feature")
    ]

    queries_packages = []
    queries_intents = []
    queries_providers = []
    for q in xml_root.findall("queries"):
        for pkg in q.findall("package"):
            pn = pkg.get(ANDROID_NS + "name")
            if pn:
                queries_packages.append(pn)
        for prov in q.findall("provider"):
            auth = prov.get(ANDROID_NS + "authorities")
            if auth:
                queries_providers.append(auth)
        for it in q.findall("intent"):
            acts = [a.get(ANDROID_NS + "name") for a in it.findall("action")]
            dats = [
                {k: d.get(ANDROID_NS + k) for k in ("scheme", "host", "mimeType") if d.get(ANDROID_NS + k)}
                for d in it.findall("data")
            ]
            queries_intents.append({"actions": acts, "data": dats})

    components = {}
    deep_link_schemes = Counter()
    deep_link_hosts = Counter()
    exported_components = defaultdict(list)

    if app is not None:
        for comp_tag in ("activity", "activity-alias", "service", "receiver", "provider"):
            items = app.findall(comp_tag)
            exp_true = 0
            exp_false = 0
            exp_implicit = 0
            for el in items:
                cname = el.get(ANDROID_NS + "name")
                exp = el.get(ANDROID_NS + "exported")
                iflts = parse_intent_filters(el)
                is_exp = exp == "true" or (exp is None and len(iflts) > 0)
                if exp == "true":
                    exp_true += 1
                elif exp == "false":
                    exp_false += 1
                else:
                    exp_implicit += 1
                if is_exp:
                    entry = {
                        "name": cname,
                        "explicit_exported": exp,
                        "permission": el.get(ANDROID_NS + "permission"),
                        "intent_filter_count": len(iflts),
                    }
                    if comp_tag == "provider":
                        entry["authorities"] = el.get(ANDROID_NS + "authorities")
                    if comp_tag == "service":
                        entry["foregroundServiceType"] = el.get(ANDROID_NS + "foregroundServiceType")
                    if iflts:
                        entry["sample_filters"] = iflts[:3]
                    exported_components[comp_tag].append(entry)
                for flt in iflts:
                    for d in flt["data"]:
                        if "scheme" in d:
                            deep_link_schemes[d["scheme"]] += 1
                        if "host" in d:
                            deep_link_hosts[d["host"]] += 1

            components[comp_tag] = {
                "total": len(items),
                "exported_true": exp_true,
                "exported_false": exp_false,
                "exported_omitted": exp_implicit,
                "effective_exported": len(exported_components[comp_tag]),
            }

        meta_keys = []
        for md in app.findall("meta-data"):
            k = md.get(ANDROID_NS + "name")
            if k:
                # Never emit secret-like API key strings; record key names and value type only
                val = md.get(ANDROID_NS + "value")
                res = md.get(ANDROID_NS + "resource")
                meta_keys.append({
                    "name": k,
                    "has_resource_ref": res is not None,
                    "value_kind": "resource" if res is not None else ("boolean" if val in ("true", "false") else ("numeric" if (val and val.isdigit()) else "string_or_config")),
                })
    else:
        meta_keys = []

    # Check network_security_config XML if referenced in res/xml/
    netsec_summary = None
    if app_attrs.get("networkSecurityConfig"):
        for npath in apk.get_files():
            if "network_security_config" in npath.lower() and npath.endswith(".xml"):
                raw_xml = apk.get_file(npath)
                axml = AXMLPrinter(raw_xml).get_xml_obj()
                netsec_summary = {
                    "path": npath,
                    "decoded_xml": ET.tostring(axml, encoding="unicode"),
                }
                break

    # Resources.arsc analysis
    arsc_summary = None
    if "resources.arsc" in apk.get_files():
        arsc_bytes = apk.get_file("resources.arsc")
        arsc = ARSCParser(arsc_bytes)
        pkgs = arsc.get_packages_names()
        type_counts = Counter()
        locales = set()
        configs_total = 0
        for pkg in pkgs:
            try:
                locales.update(arsc.get_locales(pkg))
            except Exception:
                pass
            if pkg in arsc.packages:
                for table_type_map in arsc.packages[pkg]:
                    pass
            # Use resource keys directly via get_resolved_res_configs or internal resource structures
            try:
                res_types = arsc.get_types(pkg)
                for cfg, tnames in res_types.items():
                    configs_total += 1
                    for tn in tnames:
                        ids = arsc.get_type_configs(pkg, tn)
                        type_counts[tn] = max(type_counts[tn], len(ids))
            except Exception:
                pass

        # More exact entry enumeration via resource_keys
        exact_types = Counter()
        per_package_counts = {}
        try:
            for pkg in pkgs:
                p_counter = Counter()
                for t_name, entries in arsc.resource_keys[pkg].items():
                    cnt = len(entries)
                    exact_types[t_name] += cnt
                    p_counter[t_name] += cnt
                per_package_counts[pkg] = {
                    "total_keys": sum(p_counter.values()),
                    "by_type": dict(p_counter.most_common()),
                }
        except Exception:
            exact_types = type_counts

        arsc_summary = {
            "bytes": len(arsc_bytes),
            "package_count": len(pkgs),
            "packages": pkgs,
            "locales": sorted(x for x in locales if x),
            "locale_count": len([x for x in locales if x]),
            "resource_type_counts": dict(exact_types.most_common()),
            "total_unique_resource_keys": sum(exact_types.values()),
            "per_package_summary": per_package_counts,
        }

    return {
        "apk_file": apk_path.name,
        "sdk_and_identity": sdk_info,
        "application_attributes": app_attrs,
        "permissions_count": len(perms),
        "permissions_by_category": dict(Counter(p["category"] for p in perms)),
        "permissions": perms,
        "custom_permissions": custom_perms,
        "uses_features": features,
        "queries": {
            "package_count": len(queries_packages),
            "packages": sorted(queries_packages),
            "providers": sorted(queries_providers),
            "intent_count": len(queries_intents),
            "intents": queries_intents,
        },
        "components_summary": components,
        "exported_components": dict(exported_components),
        "deep_link_schemes": dict(deep_link_schemes.most_common()),
        "deep_link_hosts": dict(deep_link_hosts.most_common()),
        "meta_data_count": len(meta_keys),
        "meta_data_keys": meta_keys,
        "network_security_config": netsec_summary,
        "resources_arsc": arsc_summary,
    }


results = {}
for apk_file in sorted(XAPK_DIR.glob("*.apk")):
    print(f"Analyzing {apk_file.name}...")
    results[apk_file.name] = analyze_apk(apk_file)

(OUT_DIR / "manifest_and_resources.json").write_text(json.dumps(results, indent=2), encoding="utf-8")

base = results["vn.com.vng.zalopay.apk"]
print("\n=== BASE APK SUMMARY ===")
print("Package:", base["sdk_and_identity"])
print("App attrs:", base["application_attributes"])
print("Permissions:", base["permissions_count"], base["permissions_by_category"])
print("Components:", base["components_summary"])
print("Deep link schemes:", base["deep_link_schemes"])
print("Queries packages count:", base["queries"]["package_count"], "sample:", base["queries"]["packages"][:15])
print("Meta-data count:", base["meta_data_count"])
print("Network security config:", base["network_security_config"])
print("ARSC:", base["resources_arsc"])
print("\n=== SPLIT APKS ARSC ===")
for k, v in results.items():
    if k != "vn.com.vng.zalopay.apk":
        print(f"  {k}: split={v['sdk_and_identity']['split_name']} arsc={v['resources_arsc']}")
