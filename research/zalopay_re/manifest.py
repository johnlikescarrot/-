"""Binary AndroidManifest.xml / resources.arsc decoding and manifest analysis.

AXML decoding is delegated to androguard's battle-tested chunk parser; the
analysis on top is ours: it turns the decoded tree into a *security contract*
view (exported components, intent-filter reachability, dangerous permissions,
network/backup/debug posture, deep-link surface, <queries> visibility).
"""

from __future__ import annotations

import io
import re
import zipfile
from collections import Counter, defaultdict
from typing import Dict, List, Optional
from xml.etree import ElementTree as ET

ANDROID_NS = "{http://schemas.android.com/apk/res/android}"

# Android 9..16 dangerous permissions (protectionLevel="dangerous"), grouped
# by the runtime-permission group they belong to.
DANGEROUS: Dict[str, str] = {
    "android.permission.READ_CONTACTS": "CONTACTS",
    "android.permission.WRITE_CONTACTS": "CONTACTS",
    "android.permission.READ_CALENDAR": "CALENDAR",
    "android.permission.WRITE_CALENDAR": "CALENDAR",
    "android.permission.CAMERA": "CAMERA",
    "android.permission.RECORD_AUDIO": "MICROPHONE",
    "android.permission.ACCESS_FINE_LOCATION": "LOCATION",
    "android.permission.ACCESS_COARSE_LOCATION": "LOCATION",
    "android.permission.ACCESS_BACKGROUND_LOCATION": "LOCATION",
    "android.permission.READ_PHONE_STATE": "PHONE",
    "android.permission.READ_PHONE_NUMBERS": "PHONE",
    "android.permission.CALL_PHONE": "PHONE",
    "android.permission.ANSWER_PHONE_CALLS": "PHONE",
    "android.permission.READ_CALL_LOG": "CALL_LOG",
    "android.permission.WRITE_CALL_LOG": "CALL_LOG",
    "android.permission.PROCESS_OUTGOING_CALLS": "CALL_LOG",
    "android.permission.BODY_SENSORS": "SENSORS",
    "android.permission.ACTIVITY_RECOGNITION": "ACTIVITY_RECOGNITION",
    "android.permission.SEND_SMS": "SMS",
    "android.permission.RECEIVE_SMS": "SMS",
    "android.permission.READ_SMS": "SMS",
    "android.permission.RECEIVE_MMS": "SMS",
    "android.permission.RECEIVE_WAP_PUSH": "SMS",
    "android.permission.READ_EXTERNAL_STORAGE": "STORAGE",
    "android.permission.WRITE_EXTERNAL_STORAGE": "STORAGE",
    "android.permission.READ_MEDIA_IMAGES": "MEDIA",
    "android.permission.READ_MEDIA_VIDEO": "MEDIA",
    "android.permission.READ_MEDIA_AUDIO": "MEDIA",
    "android.permission.POST_NOTIFICATIONS": "NOTIFICATIONS",
    "android.permission.NEARBY_WIFI_DEVICES": "NEARBY_WIFI",
    "android.permission.BLUETOOTH_CONNECT": "BLUETOOTH",
    "android.permission.BLUETOOTH_SCAN": "BLUETOOTH",
    "android.permission.BLUETOOTH_ADVERTISE": "BLUETOOTH",
    "android.permission.UWB_RANGING": "UWB",
    "android.permission.ACCESS_MEDIA_LOCATION": "MEDIA",
}

# permissions that materially widen the attack surface for a *financial* app
FINANCIAL_RED_FLAGS = {
    "android.permission.SYSTEM_ALERT_WINDOW": "draws over other apps (tap-jacking / overlay)",
    "android.permission.REQUEST_INSTALL_PACKAGES": "can prompt APK sideloading",
    "android.permission.BIND_ACCESSIBILITY_SERVICE": "accessibility service = UI automation",
    "android.permission.BIND_DEVICE_ADMIN": "device administrator",
    "android.permission.PACKAGE_USAGE_STATS": "app-usage telemetry",
    "android.permission.READ_SMS": "SMS OTP interception surface",
    "android.permission.QUERY_ALL_PACKAGES": "enumerates every installed app",
}

HIGH_RISK_CAPS = {
    "android.permission.READ_SMS", "android.permission.RECEIVE_SMS",
    "android.permission.BIND_ACCESSIBILITY_SERVICE",
    "android.permission.SYSTEM_ALERT_WINDOW",
    "android.permission.REQUEST_INSTALL_PACKAGES",
}


def axml_to_tree(data: bytes) -> ET.Element:
    from androguard.core.axml import AXMLPrinter

    printer = AXMLPrinter(data)
    xml = printer.get_xml()
    if isinstance(xml, bytes):
        xml = xml.decode("utf-8", "replace")
    return ET.fromstring(xml)


def attr(el: ET.Element, name: str, default: Optional[str] = None) -> Optional[str]:
    v = el.get(ANDROID_NS + name)
    if v is None:
        v = el.get(name)
    return default if v is None else v


def truthy(v: Optional[str]) -> Optional[bool]:
    if v is None:
        return None
    return str(v).lower() in ("true", "1")


def describe_component(el: ET.Element, kind: str) -> dict:
    namefull = attr(el, "name", "")
    filters = []
    for i in el.findall("intent-filter"):
        actions = [attr(a, "name", "") for a in i.findall("action")]
        cats = [attr(c, "name", "") for c in i.findall("category")]
        datas = []
        for d in i.findall("data"):
            datas.append({k: attr(d, k) for k in
                          ("scheme", "host", "path", "pathPrefix", "pathPattern", "port", "mimeType")
                          if attr(d, k) is not None})
        filters.append({
            "actions": actions, "categories": cats, "data": datas,
            "autoVerify": truthy(attr(i, "autoVerify")),
            "priority": attr(i, "priority"),
        })
    exported = truthy(attr(el, "exported"))
    has_filter = bool(filters)
    # AOSP default: exported defaults to True when an intent-filter is present
    effective_exported = exported if exported is not None else has_filter
    return {
        "kind": kind,
        "name": namefull,
        "exported_attr": exported,
        "exported_effective": effective_exported,
        "permission": attr(el, "permission"),
        "readPermission": attr(el, "readPermission"),
        "writePermission": attr(el, "writePermission"),
        "grantUriPermissions": truthy(attr(el, "grantUriPermissions")),
        "authorities": attr(el, "authorities"),
        "process": attr(el, "process"),
        "taskAffinity": attr(el, "taskAffinity"),
        "launchMode": attr(el, "launchMode"),
        "excludeFromRecents": truthy(attr(el, "excludeFromRecents")),
        "filter_trusted": truthy(attr(el, "filterTrusted")),
        "intent_filters": filters,
        "directBootAware": truthy(attr(el, "directBootAware")),
        "enabled": truthy(attr(el, "enabled")),
    }


def analyze_manifest(data: bytes, label: str) -> dict:
    root = axml_to_tree(data)
    manifest_attrs = {
        "package": root.get("package"),
        "versionCode": attr(root, "versionCode"),
        "versionName": attr(root, "versionName"),
        "compileSdkVersion": attr(root, "compileSdkVersion"),
        "platformBuildVersionCode": attr(root, "platformBuildVersionCode"),
        "installLocation": attr(root, "installLocation"),
        "split": root.get("split") or attr(root, "split"),
        "isSplitRequired": attr(root, "isSplitRequired"),
        "isolatedSplits": attr(root, "isolatedSplits"),
    }
    uses_sdk = {}
    for el in root.findall("uses-sdk"):
        uses_sdk = {k: attr(el, k) for k in ("minSdkVersion", "targetSdkVersion",
                                             "maxSdkVersion", "compileSdkVersion")}

    permissions = []
    for el in root.findall("uses-permission") + root.findall("uses-permission-sdk-23"):
        n = attr(el, "name")
        if not n:
            continue
        permissions.append({
            "name": n,
            "maxSdkVersion": attr(el, "maxSdkVersion"),
            "dangerous_group": DANGEROUS.get(n),
            "red_flag": FINANCIAL_RED_FLAGS.get(n),
            "high_risk": n in HIGH_RISK_CAPS,
        })
    # de-dup, keeping the most informative row
    seen, uniq = set(), []
    for p in permissions:
        if p["name"] in seen:
            continue
        seen.add(p["name"])
        uniq.append(p)

    features = []
    for el in root.findall("uses-feature"):
        features.append({"name": attr(el, "name"), "required": truthy(attr(el, "required", "true"))})

    queries = []
    for q in root.findall("queries"):
        for el in q.findall("package"):
            queries.append(el.get(ANDROID_NS + "name") or el.get("name"))
        for el in q.findall("intent"):
            queries.append("intent:" + ",".join(
                (attr(a, "name") or "?") for a in el.findall("action")))

    app = root.find("application")
    app_attrs = {}
    components: List[dict] = []
    meta_data = []
    if app is not None:
        app_attrs = {
            k: attr(app, k) for k in (
                "name", "allowBackup", "fullBackupContent", "dataExtractionRules",
                "debuggable", "usesCleartextTraffic", "networkSecurityConfig",
                "extractNativeLibs", "largeHeap", "hardwareAccelerated",
                "requestLegacyExternalStorage", "preserveLegacyExternalStorage",
                "enableOnBackInvokedCallback", "appComponentFactory",
                "backupAgent", "killAfterRestore", "hasFragileUserData",
                "testOnly", "vmSafeMode", "directBootAware",
                "usesNonSdkApi", "zygotePreloadName",
            ) if attr(app, k) is not None
        }
        for kind, tag in (("activity", "activity"), ("activity-alias", "activity-alias"),
                          ("service", "service"), ("receiver", "receiver"),
                          ("provider", "provider")):
            for el in app.findall(tag):
                components.append(describe_component(el, kind))
        for el in app.findall("meta-data"):
            meta_data.append({"name": attr(el, "name"), "value": (attr(el, "value") or "")[:200],
                              "resource": attr(el, "resource")})
        for el in app.findall("uses-library"):
            meta_data.append({"name": "uses-library:" + (attr(el, "name") or ""),
                              "value": str(truthy(attr(el, "required")))})

    exported = [c for c in components if c["exported_effective"]]
    deep_links = []
    for c in components:
        for f in c["intent_filters"]:
            if "android.intent.action.VIEW" in f["actions"]:
                for d in f["data"]:
                    if d.get("scheme") and d.get("host"):
                        deep_links.append({
                            "component": c["name"], "kind": c["kind"],
                            "uri": f"{d['scheme']}://{d['host']}{d.get('path') or d.get('pathPrefix') or d.get('pathPattern') or ''}",
                            "scheme": d["scheme"], "host": d["host"],
                            "exported": c["exported_effective"],
                            "permission": c["permission"],
                        })
    app_links = sorted({f"https://{d['host']}" for c in components
                        for f in c["intent_filters"] for d in f["data"]
                        if d.get("scheme") in ("https", "http") and d.get("host")})

    providers = [c for c in components if c["kind"] == "provider"]
    authorities = []
    for p in providers:
        if p["authorities"]:
            for a in p["authorities"].split(";"):
                authorities.append({"authority": a.strip(), "provider": p["name"],
                                    "exported": p["exported_effective"],
                                    "grantUriPermissions": p["grantUriPermissions"],
                                    "readPermission": p["readPermission"],
                                    "writePermission": p["writePermission"]})

    return {
        "label": label,
        "manifest": manifest_attrs,
        "uses_sdk": uses_sdk,
        "permissions": uniq,
        "permission_summary": {
            "total": len(uniq),
            "dangerous": sum(1 for p in uniq if p["dangerous_group"]),
            "dangerous_groups": dict(Counter(p["dangerous_group"] for p in uniq if p["dangerous_group"])),
            "red_flags": [p["name"] for p in uniq if p["red_flag"]],
            "high_risk": [p["name"] for p in uniq if p["high_risk"]],
            "custom": [p["name"] for p in uniq if not p["name"].startswith("android.")],
        },
        "features": features,
        "queries": queries,
        "application": app_attrs,
        "components": components,
        "component_counts": dict(Counter(c["kind"] for c in components)),
        "exported_components": [c for c in exported],
        "exported_counts": dict(Counter(c["kind"] for c in exported)),
        "deep_links": deep_links,
        "app_link_domains": app_links,
        "authorities": authorities,
        "meta_data": meta_data[:400],
        "xml_root_tag": root.tag,
    }


def analyze_arsc(arsc_bytes: bytes) -> dict:
    """Census of resources.arsc via our own ResTable reader (see arsc.py)."""
    from .arsc import ResTable
    return ResTable(arsc_bytes).census()


def analyze_res_xml(zf, limit: int = 400) -> dict:
    """Decode every res/xml/*.xml (network security config, backup rules, ...).

    This is where ``android:networkSecurityConfig`` lives: cleartext policy,
    per-domain trust anchors and - crucially - ``<pin-set>`` declarations.
    """
    out = {"scanned": 0, "network_security_config": [], "backup_rules": [],
           "other_interesting": [], "errors": 0, "tag_histogram": {}}
    import re as _re
    from collections import Counter
    names = [i for i in zf.infolist()
             if i.filename.startswith("res/") and i.filename.endswith(".xml")]
    targeted = [i for i in names if _re.search(r"(security|network|pin|ssl|trust|cert|backup|extraction)", i.filename)]
    rest = [i for i in names if i not in targeted][:limit]
    tags: Counter = Counter()
    for info in targeted + rest:
        p = info.filename
        try:
            tree = axml_to_tree(zf.read(info))
        except Exception:
            out["errors"] += 1
            continue
        out["scanned"] += 1
        tag = tree.tag
        tags[tag] += 1
        if tag == "network-security-config":
            pins = []
            for ps in tree.iter("pin-set"):
                expiration = ps.get("expiration")
                for pin in ps.iter("pin"):
                    pins.append({"digest": (pin.get("digest") or "")[:120],
                                 "expiration": expiration})
            domains = []
            for dc in tree.iter("domain-config"):
                for d in dc.iter("domain"):
                    domains.append({"domain": d.text, "cleartext": dc.get("cleartextTrafficPermitted"),
                                    "includesSubdomains": dc.get("includeSubdomains")})
            out["network_security_config"].append({
                "file": p,
                "base_cleartext": tree.get("cleartextTrafficPermitted"),
                "trust_anchors_present": bool(len(list(tree.iter("trust-anchors")))),
                "debug_overrides": bool(tree.get("debug-overrides") is not None),
                "pins": pins,
                "domain_configs": domains[:60],
            })
        elif tag in ("data-extraction-rules", "full-backup-content", "backup-rules"):
            out["backup_rules"].append({"file": p, "tag": tag,
                                        "children": [c.tag for c in list(tree)][:20]})
        else:
            text = _re.sub(r"\s+", " ", tree.tag + " " + " ".join(
                f"{k}={v}" for k, v in list(tree.attrib.items()))) [:300]
            if any(k in p for k in ("security", "cert", "pin", "ssl", "trust", "network")):
                out["other_interesting"].append({"file": p, "root": tree.tag, "attrs": text})
    out["tag_histogram"] = dict(tags.most_common(20))
    return out
