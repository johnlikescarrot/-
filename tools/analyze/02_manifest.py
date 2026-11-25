#!/usr/bin/env python3
"""AndroidManifest.xml + resource-table analysis (AXML decode via androguard)."""
from __future__ import annotations
import hashlib, json, os, re, sys
from collections import Counter

ROOT = os.path.dirname(os.path.dirname(os.path.dirname(os.path.abspath(__file__))))
APK = os.path.join(ROOT, "work/zip/xapk/vn.com.vng.zalopay.apk")
OUT = os.path.join(ROOT, "data")

ANDROID_NS = "{http://schemas.android.com/apk/res/android}"


def main() -> None:
    sys.setrecursionlimit(10000)
    from androguard.core.apk import APK as AndroAPK

    apk = AndroAPK(APK)
    xml = apk.get_android_manifest_xml()
    perms = sorted(apk.get_permissions())
    perm_details = []
    for p in perms:
        perm_details.append({"name": p})
    activities = sorted(apk.get_activities())
    services = sorted(apk.get_services())
    receivers = sorted(apk.get_receivers())
    providers = sorted(apk.get_providers())
    exported = {"activities": [], "services": [], "receivers": [], "providers": []}
    for tag, bucket in (("activity", "activities"), ("service", "services"),
                        ("receiver", "receivers"), ("provider", "providers")):
        for node in xml.iter(tag):
            name = node.get(ANDROID_NS + "name") or node.get("name")
            exp = node.get(ANDROID_NS + "exported")
            intent_filters = []
            for flt in node.findall("intent-filter"):
                actions = [a.get(ANDROID_NS + "name") for a in flt.findall("action")]
                cats = [c.get(ANDROID_NS + "name") for c in flt.findall("category")]
                data = [{k.split("}")[-1]: v for k, v in d.attrib.items()} for d in flt.findall("data")]
                intent_filters.append({"actions": actions, "categories": cats, "data": data})
            # exported defaults to True when an intent-filter exists and no explicit flag (pre-API 31 semantics)
            effective = exp if exp is not None else ("true" if intent_filters else "false")
            if str(effible := effective).lower() == "true":
                exported[bucket].append({"name": name, "declared_exported": exp,
                                         "permission": node.get(ANDROID_NS + "permission"),
                                         "intent_filter_count": len(intent_filters),
                                         "intent_filters": intent_filters,
                                         "authorities": node.get(ANDROID_NS + "authorities")})
    app = xml.find("application")
    app_attrs = {k.split("}")[-1]: v for k, v in (app.attrib if app is not None else {}).items()}
    app_children = Counter(child.tag for child in app) if app is not None else Counter()
    sdk = xml.find("uses-sdk")
    features = [f.get(ANDROID_NS + "name") for f in xml.findall("uses-feature")]
    libs = [l.get(ANDROID_NS + "name") for l in xml.findall("uses-library")]
    native_libs = [l.get(ANDROID_NS + "name") for l in xml.findall("uses-native-library")]
    qnode = xml.find("queries")
    queries = [q.tag for q in qnode] if qnode is not None else []
    queries_pkgs = [p.get(ANDROID_NS + "name") for p in xml.findall("queries/package")]
    queries_providers = [p.get(ANDROID_NS + "authorities") for p in xml.findall("queries/provider")]
    queries_intents = len(xml.findall("queries/intent"))
    # deep links
    deeplinks = []
    for act in xml.iter("activity"):
        name = act.get(ANDROID_NS + "name")
        for flt in act.findall("intent-filter"):
            for d in flt.findall("data"):
                scheme = d.get(ANDROID_NS + "scheme")
                host = d.get(ANDROID_NS + "host")
                if scheme or host:
                    deeplinks.append({"activity": name, "scheme": scheme, "host": host,
                                      "path": d.get(ANDROID_NS + "path"),
                                      "pathPrefix": d.get(ANDROID_NS + "pathPrefix"),
                                      "autoVerify": flt.get(ANDROID_NS + "autoVerify")})
    schemes = Counter(d["scheme"] for d in deeplinks if d["scheme"])
    hosts = Counter(d["host"] for d in deeplinks if d["host"])
    # meta-data keys of interest
    meta = []
    secret_shaped = []
    SECRET_RX = re.compile(r"^(AIza[0-9A-Za-z_\-]{35}|[A-Za-z0-9]{20,64})$")
    for node in xml.iter("meta-data"):
        name = node.get(ANDROID_NS + "name")
        value = node.get(ANDROID_NS + "value") or ""
        record = {"name": name, "value": value[:120], "resource": node.get(ANDROID_NS + "resource")}
        # Never persist credential-shaped literals: keep a masked fingerprint only.
        if value and not value.startswith("@") and SECRET_RX.match(value) and len(value) >= 20:
            record["value"] = value[:4] + "*" * 8 + value[-2:]
            record["redacted"] = True
            secret_shaped.append({"name": name, "masked": record["value"], "length": len(value),
                                  "sha256_12": hashlib.sha256(value.encode()).hexdigest()[:12]})
        meta.append(record)
    pkg = apk.get_package()
    version_name = apk.get_androidversion_name()
    version_code = apk.get_androidversion_code()
    signed = apk.is_signed()
    report = {
        "package": pkg, "version_name": version_name, "version_code": version_code,
        "min_sdk": sdk.get(ANDROID_NS + "minSdkVersion") if sdk is not None else None,
        "target_sdk": sdk.get(ANDROID_NS + "targetSdkVersion") if sdk is not None else None,
        "compile_sdk": sdk.get(ANDROID_NS + "compileSdkVersion") if sdk is not None else None,
        "application": app_attrs,
        "component_counts": {"activities": len(activities), "services": len(services),
                             "receivers": len(receivers), "providers": len(providers),
                             "manifest_children": dict(app_children.most_common())},
        "permissions": perm_details,
        "permission_count": len(perms),
        "permission_groups": dict(Counter(p.split(".")[1] if p.startswith("android.permission.") and len(p.split(".")) > 2 else "other" for p in perms).most_common()),
        "exported_components": exported,
        "exported_counts": {k: len(v) for k, v in exported.items()},
        "features": features, "uses_library": libs, "uses_native_library": native_libs,
        "queries": {"children": queries, "packages": queries_pkgs, "providers": queries_providers,
                    "intent_count": queries_intents},
        "deeplinks": deeplinks[:200], "deeplink_count": len(deeplinks),
        "deeplink_schemes": dict(schemes.most_common()),
        "deeplink_hosts": dict(hosts.most_common(20)),
        "meta_data_count": len(meta),
        "meta_data_sample": meta[:60],
        "secret_shaped_meta_data": secret_shaped,
        "signed": signed,
    }
    with open(os.path.join(OUT, "02-manifest.json"), "w") as fh:
        json.dump(report, fh, indent=2, ensure_ascii=False)
    print(json.dumps({k: report[k] for k in ("package", "version_name", "min_sdk", "target_sdk",
                                             "permission_count", "component_counts", "exported_counts",
                                             "deeplink_count", "deeplink_schemes")}, indent=2, ensure_ascii=False)[:2500])
    print("app attrs:", {k: v for k, v in app_attrs.items()})
    print("deeplink hosts:", report["deeplink_hosts"])


if __name__ == "__main__":
    main()
