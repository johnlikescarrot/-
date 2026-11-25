#!/usr/bin/env python3
"""
Stage 10 -- AndroidManifest.xml semantics.

The manifest is the app's *declared* attack surface: what it is allowed to do
(permissions), what it exposes to the rest of the device (exported components,
deep links, content providers) and what hardening switches it flipped
(cleartext, backup, debuggable).  We resolve the binary AXML into a
`manifest_facts` document that later stages and the film both consume.
"""

from __future__ import annotations

import logging
import re
from collections import Counter, defaultdict

from androguard.core.apk import APK
from androguard.core.axml import AXMLPrinter

from common import (BASE_PKG, DATA, UNPACKED, XAPK_DIR, banner, dump, human,
                    redact)

logging.disable(logging.CRITICAL)

# Runtime (dangerous) permissions on API 26..36 that matter for a privacy read.
DANGEROUS = {
    "android.permission.READ_CONTACTS", "android.permission.WRITE_CONTACTS",
    "android.permission.READ_CALENDAR", "android.permission.WRITE_CALENDAR",
    "android.permission.CAMERA", "android.permission.RECORD_AUDIO",
    "android.permission.ACCESS_FINE_LOCATION",
    "android.permission.ACCESS_COARSE_LOCATION",
    "android.permission.READ_PHONE_STATE",
    "android.permission.READ_EXTERNAL_STORAGE",
    "android.permission.WRITE_EXTERNAL_STORAGE",
    "android.permission.READ_MEDIA_AUDIO", "android.permission.READ_MEDIA_IMAGES",
    "android.permission.READ_MEDIA_VIDEO", "android.permission.POST_NOTIFICATIONS",
    "android.permission.BODY_SENSORS", "android.permission.SEND_SMS",
    "android.permission.READ_SMS", "android.permission.CALL_PHONE",
    "android.permission.ACCESS_BACKGROUND_LOCATION",
    "android.permission.BLUETOOTH_CONNECT", "android.permission.NEARBY_WIFI_DEVICES",
}

SPECIAL_ACCESS = {
    "android.permission.QUERY_ALL_PACKAGES": "package-visibility bypass",
    "android.permission.DETECT_SCREEN_CAPTURE": "screen-recording detection",
    "android.permission.SYSTEM_ALERT_WINDOW": "overlay",
    "android.permission.MANAGE_EXTERNAL_STORAGE": "all-files access",
    "android.permission.ACCESS_ADSERVICES_AD_ID": "advertising id",
    "com.google.android.gms.permission.AD_ID": "advertising id",
}


def truthy(v) -> bool:
    return str(v).lower() in {"true", "1"}


def intent_filters(elem) -> list[dict]:
    out = []
    for f in elem.findall("intent-filter"):
        actions = [a.get("{%s}name" % ANDROID) for a in f.findall("action")]
        cats = [c.get("{%s}name" % ANDROID) for c in f.findall("category")]
        datas = []
        for d in f.findall("data"):
            datas.append({k.split("}")[-1]: v for k, v in d.attrib.items()})
        out.append({"actions": [a for a in actions if a],
                    "categories": [c for c in cats if c],
                    "data": datas,
                    "autoVerify": truthy(f.get("{%s}autoVerify" % ANDROID))})
    return out


ANDROID = "{http://schemas.android.com/apk/res/android}"


def main() -> None:
    banner("Stage 10 · manifest semantics")
    apk = APK(str(XAPK_DIR / f"{BASE_PKG}.apk"))
    man = apk.get_android_manifest_xml()
    app = man.find("application")

    perms = sorted(apk.get_permissions())
    perm_rows = []
    for p in perms:
        short = p.split(".")[-1]
        perm_rows.append({
            "name": p,
            "tier": ("dangerous" if p in DANGEROUS else
                     "special" if p in SPECIAL_ACCESS or short.startswith("ACCESS_ADSERVICES") else
                     "signature-or-vendor" if not p.startswith("android.permission") else
                     "normal"),
            "rationale": SPECIAL_ACCESS.get(p, ""),
        })
    tier_counts = Counter(r["tier"] for r in perm_rows)

    # ---- components ---------------------------------------------------------
    def collect(tag: str) -> list[dict]:
        rows = []
        for c in app.findall(tag):
            name = c.get(f"{ANDROID}name")
            if not name:
                continue
            rows.append({
                "name": name,
                "exported_declared": (None if c.get(f"{ANDROID}exported") is None
                                      else truthy(c.get(f"{ANDROID}exported"))),
                "permission": c.get(f"{ANDROID}permission"),
                "authorities": c.get(f"{ANDROID}authorities"),
                "grant_uri_permissions": truthy(c.get(f"{ANDROID}grantUriPermissions")),
                "process": c.get(f"{ANDROID}process"),
                "direct_boot_aware": truthy(c.get(f"{ANDROID}directBootAware")),
                "filters": intent_filters(c),
                "meta": [{k.split("}")[-1]: v for k, v in m.attrib.items()}
                         for m in c.findall("meta-data")],
            })
        return rows

    activities = collect("activity") + collect("activity-alias")
    services = collect("service")
    receivers = collect("receiver")
    providers = collect("provider")

    # An implicit export happens when a filter exists and exported is unset.
    for group in (activities, services, receivers):
        for c in group:
            c["exported_effective"] = bool(
                c["exported_declared"] if c["exported_declared"] is not None
                else bool(c["filters"]))

    exported = [c["name"] for c in activities + services + receivers
                if c["exported_effective"]]
    exported_without_perm = [
        c["name"] for c in activities + services + receivers
        if c["exported_effective"] and not c["permission"]
    ]

    # ---- deep links ---------------------------------------------------------
    schemes, hosts, applinks = Counter(), Counter(), []
    for a in activities:
        for f in a["filters"]:
            for d in f["data"]:
                if d.get("scheme"):
                    schemes[d["scheme"]] += 1
                if d.get("host") and d["host"] != "*":
                    hosts[d["host"]] += 1
                if d.get("host"):
                    applinks.append({"activity": a["name"],
                                     "scheme": d.get("scheme"),
                                     "host": d["host"],
                                     "pathPrefix": d.get("pathPrefix") or d.get("pathPattern"),
                                     "autoVerify": f["autoVerify"]})

    # ---- application-level flags -------------------------------------------
    flags = {}
    for key in ("allowBackup", "fullBackupContent", "dataExtractionRules",
                "debuggable", "usesCleartextTraffic", "networkSecurityConfig",
                "largeHeap", "hardwareAccelerated", "extractNativeLibs",
                "requestLegacyExternalStorage", "testOnly", "appComponentFactory",
                "enableOnBackInvokedCallback", "localeConfig", "supportsRtl",
                "usesNonSdkApi", "killAfterRestore", "hasFragileUserData",
                "preserveLegacyExternalStorage", "allowNativeHeapPointerTagging"):
        v = app.get(f"{ANDROID}{key}")
        if v is not None:
            flags[key] = v
    meta = []
    for m in app.findall("meta-data"):
        d = {k.split("}")[-1]: v for k, v in m.attrib.items()}
        if "value" in d and re.search(r"(?i)(key|secret|token|client_id)", d.get("name", "")):
            d["value"] = redact(d["value"], 6)
        meta.append(d)

    uses_library = [u.get(f"{ANDROID}name") for u in app.findall("uses-library")]
    uses_native = [u.get(f"{ANDROID}name") for u in app.findall("uses-native-library")]
    queries = []
    for q in man.findall("queries"):
        for tag in ("package", "intent", "provider"):
            for e in q.findall(tag):
                queries.append({"type": tag,
                                **{k.split("}")[-1]: v for k, v in e.attrib.items()}})
    features = [{k.split("}")[-1]: v for k, v in f.attrib.items()}
                for f in man.findall("uses-feature")]

    # ---- network security config (binary XML → text) ------------------------
    nsc = None
    for arsc_path in (UNPACKED / BASE_PKG / "res" / "xml").glob("*.xml"):
        try:
            txt = AXMLPrinter(arsc_path.read_bytes()).get_xml().decode("utf-8", "replace")
        except Exception:
            continue
        if "network-security-config" in txt:
            nsc = {"file": str(arsc_path.relative_to(UNPACKED / BASE_PKG)),
                   "cleartext_permitted": ("cleartextTrafficPermitted=\"true\"" in txt),
                   "pinned_domains": len(re.findall(r"<pin ", txt)),
                   "trust_anchors_user": "certificates src=\"user\"" in txt,
                   "xml": txt[:4000]}
            break

    # ---- split metadata -----------------------------------------------------
    splits = []
    for p in sorted(XAPK_DIR.glob("*.apk")):
        a = APK(str(p))
        splits.append({"file": p.name, "package": a.get_package(),
                       "split": a.get_android_manifest_xml()
                       .get(f"{{http://schemas.android.com/apk/res/android}}split")
                       or ("base" if p.name.startswith(BASE_PKG) else None),
                       "bytes": p.stat().st_size})

    doc = {
        "identity": {
            "package": apk.get_package(),
            "version_name": apk.get_androidversion_name(),
            "version_code": apk.get_androidversion_code(),
            "min_sdk": apk.get_min_sdk_version(),
            "target_sdk": apk.get_target_sdk_version(),
            "effective_sdk": apk.get_effective_target_sdk_version(),
            "main_activity": apk.get_main_activity(),
            "app_name": apk.get_app_name(),
        },
        "permissions": {
            "count": len(perms),
            "by_tier": dict(tier_counts),
            "items": perm_rows,
        },
        "components": {
            "counts": {"activities": len(activities), "services": len(services),
                       "receivers": len(receivers), "providers": len(providers)},
            "exported_total": len(exported),
            "exported_without_permission": len(exported_without_perm),
            "exported_without_permission_list": sorted(exported_without_perm)[:60],
            "providers": [{"name": p["name"], "authorities": p["authorities"],
                           "exported": p["exported_declared"],
                           "grant_uri_permissions": p["grant_uri_permissions"]}
                          for p in providers],
            "activities": activities,
            "services": services,
            "receivers": receivers,
        },
        "deep_links": {
            "schemes": dict(schemes.most_common()),
            "hosts": dict(hosts.most_common(30)),
            "links": applinks[:80],
            "count": len(applinks),
            "verified_applinks": sum(1 for x in applinks if x["autoVerify"]),
        },
        "application_flags": flags,
        "meta_data": meta[:60],
        "uses_library": uses_library,
        "uses_native_library": uses_native,
        "queries": queries[:40],
        "features": features,
        "network_security_config": nsc,
        "splits": splits,
    }
    dump("10_manifest.json", doc)

    print(f"  package={doc['identity']['package']} v{doc['identity']['version_name']} "
          f"(code {doc['identity']['version_code']}) sdk {doc['identity']['min_sdk']}→{doc['identity']['target_sdk']}")
    print(f"  permissions={len(perms)} {dict(tier_counts)}")
    print(f"  components={doc['components']['counts']} exported={len(exported)} "
          f"(no-permission: {len(exported_without_perm)})")
    print(f"  deeplinks={len(applinks)} schemes={dict(schemes)} hosts={len(hosts)}")
    print(f"  flags={flags}")
    print(f"  NSC: {nsc and {k: v for k, v in nsc.items() if k != 'xml'}}")


if __name__ == "__main__":
    main()
