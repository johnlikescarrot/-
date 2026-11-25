#!/usr/bin/env python3
"""
Stage 02b — Manifest & security-configuration reverse engineering.

Consumes the decoded `AndroidManifest.xml` produced in stage 01 and the raw
resources inside the base APK, and emits the component / attack-surface
dataset:

  011-components-exported.json   exported activities, services, receivers,
                                 providers with their intent filters and
                                 deep-link (scheme/host/path) inventory
  012-permissions.json            requested + declared permissions, grouped
  013-application-flags.json     application-level security flags
  014-resource-configs.json      decoded security-relevant resource XML
                                 (network security config, file paths, …)

The manifest is parsed with `xml.etree`; resource XML is binary AXML and is
decoded with androguard's `AXMLPrinter`.
"""

from __future__ import annotations

import hashlib
import json
import logging
import os
import re
import sys
import zipfile
import xml.etree.ElementTree as ET

ROOT = os.path.dirname(os.path.dirname(os.path.dirname(os.path.abspath(__file__))))
WORK = os.path.join(ROOT, "work")
EVIDENCE = os.path.join(ROOT, "re", "evidence")
OUT = os.path.join(ROOT, "re", "analysis")
BASE_APK = os.path.join(WORK, "extract", "xapk", "vn.com.vng.zalopay.apk")
MANIFEST_XML = os.path.join(
    EVIDENCE, "manifest-xml-vn.com.vng.zalopay.apk.xml"
)

ANDROID_NS = "{http://schemas.android.com/apk/res/android}"

# attributes worth surfacing verbatim
COMPONENT_ATTRS = (
    "name", "exported", "enabled", "permission", "process", "directBootAware",
    "multiprocess", "isolatedProcess", "singleUser", "authorities",
    "grantUriPermissions", "exported",
)


def ensure_out() -> None:
    os.makedirs(OUT, exist_ok=True)


def dump(name: str, payload) -> None:
    path = os.path.join(OUT, name)
    with open(path, "w", encoding="utf-8") as fh:
        json.dump(payload, fh, indent=2, ensure_ascii=False, sort_keys=False,
                  default=lambda o: sorted(o) if isinstance(o, (set, frozenset, tuple)) else str(o))
        fh.write("\n")
    print(f"  wrote {path}", flush=True)


def attrs(elem) -> dict:
    out = {}
    for k, v in elem.attrib.items():
        out[k.replace(ANDROID_NS, "android:")] = v
    return out


def parse_deeplink(data_elem) -> dict:
    """Extract scheme/host/port/path/mimeType from a <data> element."""
    a = attrs(data_elem)
    scheme = a.get("android:scheme")
    host = a.get("android:host")
    port = a.get("android:port")
    path = a.get("android:path")
    path_prefix = a.get("android:pathPrefix")
    path_pattern = a.get("android:pathPattern")
    mime = a.get("android:mimeType")
    return {
        "scheme": scheme,
        "host": host,
        "port": port,
        "path": path,
        "pathPrefix": path_prefix,
        "pathPattern": path_pattern,
        "mimeType": mime,
        "uri": "{}://{}{}".format(scheme or "", host or "", path or "") if scheme else None,
    }


def intent_filter_summary(intent) -> dict:
    actions = []
    categories = []
    datas = []
    for child in intent:
        tag = child.tag.replace(ANDROID_NS, "")
        if tag == "action":
            actions.append(child.get(ANDROID_NS + "name"))
        elif tag == "category":
            categories.append(child.get(ANDROID_NS + "name"))
        elif tag == "data":
            datas.append(parse_deeplink(child))
    return {"actions": actions, "categories": categories, "data": datas}


def main() -> int:
    logging.getLogger("androguard").setLevel(logging.CRITICAL)
    ensure_out()
    print("== stage 02b: manifest & security-config analysis ==")

    if not os.path.exists(MANIFEST_XML):
        print("ERROR: decoded manifest not found; run stage 01 first")
        return 1

    tree = ET.parse(MANIFEST_XML)
    root = tree.getroot()
    manifest_attrs = attrs(root)

    application = root.find("application")
    app_attrs = attrs(application) if application is not None else {}

    # ---- application-level flags ---------------------------------------- #
    flag_keys = [
        "android:debuggable", "android:allowBackup", "android:allowUserVisibleFingerprints",
        "android:usesCleartextTraffic", "android:networkSecurityConfig",
        "android:extractNativeLibs", "android:appComponentFactory",
        "android:requestLegacyExternalStorage", "android:fullBackupContent",
        "android:dataExtractionRules", "android:hardwareAccelerated",
        "android:largeHeap", "android:supportsRtl", "android:testOnly",
        "android:extractNativeLibs", "android:allowBackup",
    ]
    flags = {}
    for k in flag_keys:
        if k in app_attrs:
            flags[k] = app_attrs[k]
    # collect everything else too (useful for the report)
    flags["_all_application_attributes"] = app_attrs
    dump("013-application-flags.json", {
        "manifest_package": manifest_attrs.get("package"),
        "manifest_platform_version": manifest_attrs.get("platformBuildVersionName")
        or manifest_attrs.get("platformBuildVersionCode"),
        "security_relevant_flags": flags,
    })

    # ---- components ---------------------------------------------------- #
    components = {"activities": [], "services": [], "receivers": [], "providers": []}
    tag_to_key = {
        "activity": "activities", "activity-alias": "activities",
        "service": "services", "receiver": "receivers", "provider": "providers",
    }
    for tag in ("activity", "activity-alias", "service", "receiver", "provider"):
        for elem in application.iter(tag) if application is not None else []:
            a = attrs(elem)
            name = a.get("android:name", "")
            # resolve relative class names
            if name.startswith("."):
                name = manifest_attrs.get("package", "") + name
            exported = a.get("android:exported")
            has_filter = elem.find("intent-filter") is not None
            rec = {
                "type": tag,
                "name": name,
                "exported": exported,
                "enabled": a.get("android:enabled", "true"),
                "permission": a.get("android:permission"),
                "process": a.get("android:process"),
                "directBootAware": a.get("android:directBootAware"),
                "isolatedProcess": a.get("android:isolatedProcess"),
                "multiprocess": a.get("android:multiprocess"),
                "intent_filters": [],
            }
            if tag == "provider":
                rec["authorities"] = a.get("android:authorities")
                rec["grantUriPermissions"] = a.get("android:grantUriPermissions")
                rec["exported"] = exported if exported is not None else ("true" if has_filter else None)
            # intent filters
            for intent in elem.findall("intent-filter"):
                rec["intent_filters"].append(intent_filter_summary(intent))
            # exported inference (pre-Android-12 default rules)
            if exported is None:
                if tag in ("activity", "receiver") and has_filter:
                    rec["exported_inferred"] = "true (has intent-filter)"
                elif tag == "provider":
                    rec["exported_inferred"] = "true if authorities set (default)"
            elif exported == "true":
                rec["exported_inferred"] = "explicitly exported"
            components[tag_to_key[tag]].append(rec)

    exported_summary = {
        tag: [c["name"] for c in comps if str(c.get("exported")).lower() == "true"
              or c.get("exported_inferred")]
        for tag, comps in components.items()
    }
    deeplinks = []
    for tag, comps in components.items():
        for c in comps:
            for intent in c["intent_filters"]:
                for d in intent["data"]:
                    if d["scheme"]:
                        deeplinks.append({
                            "component": c["name"], "type": tag,
                            "scheme": d["scheme"], "host": d["host"],
                            "path": d["path"], "port": d["port"],
                        })
    dump("011-components-exported.json", {
        "counts": {k: len(v) for k, v in components.items()},
        "exported_counts": {k: len(v) for k, v in exported_summary.items()},
        "exported_names": exported_summary,
        "components": components,
        "deep_link_inventory": deeplinks,
    })

    # ---- permissions -------------------------------------------------- #
    permissions = {"requested": [], "declared": []}
    for uses_perm in root.findall("uses-permission"):
        a = attrs(uses_perm)
        permissions["requested"].append({
            "name": a.get("android:name"),
            "maxSdkVersion": a.get("android:maxSdkVersion"),
        })
    for uses_perm in root.findall("uses-permission-sdk-23"):
        a = attrs(uses_perm)
        permissions["requested"].append({
            "name": a.get("android:name"),
            "maxSdkVersion": a.get("android:maxSdkVersion"),
        })
    for perm in (root.findall("permission") or []):
        a = attrs(perm)
        permissions["declared"].append({
            "name": a.get("android:name"),
            "protectionLevel": a.get("android:protectionLevel"),
        })
    dump("012-permissions.json", permissions)

    # ---- security-relevant resource XML -------------------------------- #
    print("  scanning resources for security-relevant AXML ...")
    configs = {}
    try:
        from androguard.core.axml import AXMLPrinter

        with zipfile.ZipFile(BASE_APK) as zf:
            targets = [
                n for n in zf.namelist()
                if n.endswith(".xml") and (
                    "network_security_config" in n.lower()
                    or "file_paths" in n.lower()
                    or "data_extraction_rules" in n.lower()
                    or "backup_rules" in n.lower()
                    or "full_backup_content" in n.lower()
                    or n.lower().endswith(".xml") and "/xml/" in n
                )
            ]
            for t in sorted(set(targets)):
                try:
                    blob = zf.read(t)
                    if not blob.startswith(b"<"):
                        printed = AXMLPrinter(blob).get_xml()
                    else:
                        printed = blob
                    configs[t] = printed.decode("utf-8", "replace")
                except Exception as exc:  # noqa: BLE001
                    configs[t] = {"error": f"{type(exc).__name__}: {exc}"}
    except Exception as exc:  # noqa: BLE001
        configs["_error"] = str(exc)
    dump("014-resource-configs.json", configs)

    print(f"  activities={len(components['activities'])} "
          f"services={len(components['services'])} "
          f"receivers={len(components['receivers'])} "
          f"providers={len(components['providers'])}")
    print(f"  requested permissions={len(permissions['requested'])} "
          f"declared={len(permissions['declared'])}")
    print(f"  deep links={len(deeplinks)}")
    print("== stage 02b complete ==")
    return 0


if __name__ == "__main__":
    sys.exit(main())
