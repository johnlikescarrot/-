#!/usr/bin/env python3
"""
Stage 03 — Aggregation: architecture map & security indicators.

Folds the raw evidence from stages 01–02b into the two datasets the report and
the film are built on:

  015-app-architecture.json   the app's own code broken down by feature module,
                              plus its class/method footprint
  016-security-indicators.json a curated, evidence-bounded set of security
                              observations (cleartext policy, backup, exported
                              surface, NFC payment, root/anti-debug hints,
                              SSL-pinning hints, endpoint inventory)

Everything here is derived from artefacts already produced on disk, so the
stage is cheap and fully reproducible.
"""

from __future__ import annotations

import json
import os
import sys
from collections import Counter

ROOT = os.path.dirname(os.path.dirname(os.path.dirname(os.path.abspath(__file__))))
ANALYSIS = os.path.join(ROOT, "re", "analysis")
EVIDENCE = os.path.join(ROOT, "re", "evidence")
OUT = ANALYSIS


def load(name: str):
    path = os.path.join(ANALYSIS if name.startswith("0") else EVIDENCE, name)
    if not os.path.exists(path):
        path = os.path.join(EVIDENCE, name)
        if not os.path.exists(path):
            return None
    with open(path, encoding="utf-8") as fh:
        return json.load(fh)


def dump(name: str, payload) -> None:
    path = os.path.join(OUT, name)
    with open(path, "w", encoding="utf-8") as fh:
        json.dump(payload, fh, indent=2, ensure_ascii=False, sort_keys=False,
                  default=lambda o: sorted(o) if isinstance(o, (set, frozenset, tuple)) else str(o))
        fh.write("\n")
    print(f"  wrote {path}", flush=True)


def main() -> int:
    print("== stage 03: aggregation (architecture + security indicators) ==")

    methods = load("010-app-method-index.json")
    classes = load("007-class-inventory.json")
    strings = load("008-strings-urls.json")
    comps = load("011-components-exported.json")
    perms = load("012-permissions.json")
    flags = load("013-application-flags.json")
    configs = load("014-resource-configs.json")
    manifest = load("005-android-manifest.json")
    dex_inv = load("006-dex-inventory.json")
    sdk = load("009-sdk-inventory.json")

    # ---- architecture -------------------------------------------------- #
    own_classes = classes.get("own_namespace_sample", []) if classes else []
    # rebuild the full own-class list from the method index (superset)
    own_from_methods = list(methods.get("methods", []) and
                            [m["class"] for m in methods.get("methods", [])] or [])
    own_all = sorted(set(own_classes) | set(own_from_methods))
    module_counter = Counter()
    for c in own_all:
        parts = c.split("/")
        # vn/com/vng/zalopay/<module>/...
        if len(parts) >= 5:
            module_counter[parts[4]] += 1
        else:
            module_counter["(root)"] += 1
    architecture = {
        "app_namespace": "vn.com.vng.zalopay",
        "own_classes_total": len(own_all),
        "own_methods_total": methods.get("methods") and
                            sum(len(m["methods"]) for m in methods["methods"]) or 0,
        "modules": [{"module": m, "classes": c} for m, c in module_counter.most_common()],
        "total_classes_in_app": classes.get("total_classes") if classes else None,
        "third_party_class_share": (
            round(100.0 * (1 - (len(own_all) / max(classes.get("total_classes", 1), 1))), 2)
            if classes else None
        ),
    }
    dump("015-app-architecture.json", architecture)

    # ---- security indicators ------------------------------------------ #
    sr = (flags or {}).get("security_relevant_flags", {})
    nsc = ""
    if isinstance(configs, dict):
        for k, v in configs.items():
            if "network_security_config" in k and isinstance(v, str):
                nsc = v
    indicators = {
        "package": (flags or {}).get("manifest_package"),
        "version": ((manifest or {}).get("apks", {})
                    .get("work/extract/xapk/vn.com.vng.zalopay.apk", {})
                    .get("version_name")),
        "signing": {
            "schemes": (manifest or {}).get("apks", {})
            .get("work/extract/xapk/vn.com.vng.zalopay.apk", {}).get("is_signed"),
            "v1_jar_signature_files": (manifest or {}).get("apks", {})
            .get("work/extract/xapk/vn.com.vng.zalopay.apk", {}).get("v1_signature_files"),
        },
        "backup": {
            "allowBackup": sr.get("android:allowBackup"),
            "fullBackupContent": sr.get("android:fullBackupContent"),
            "dataExtractionRules": sr.get("android:dataExtractionRules"),
        },
        "network": {
            "usesCleartextTraffic": sr.get("android:usesCleartextTraffic"),
            "networkSecurityConfig": sr.get("android:networkSecurityConfig"),
            "base_config_cleartext_permitted": (
                "cleartextTrafficPermitted=\"true\"" in nsc and "<base-config" in nsc
            ),
            "network_security_config_xml": nsc,
        },
        "exported_surface": {
            "activities": comps["exported_counts"]["activities"] if comps else None,
            "services": comps["exported_counts"]["services"] if comps else None,
            "receivers": comps["exported_counts"]["receivers"] if comps else None,
            "providers": comps["exported_counts"]["providers"] if comps else None,
            "deep_links": len(comps["deep_link_inventory"]) if comps else 0,
        },
        "permissions": {
            "requested_count": len(perms["requested"]) if perms else None,
            "declared_count": len(perms["declared"]) if perms else None,
        },
        "nfc_payment": {
            "apdu_service_present": any(
                "apdu_service" in k for k in (configs or {})
            ),
            "aid_filters": extract_aids(configs),
        },
        "hardening_hints": {
            "keyword_hits": {
                k: v["count"] for k, v in (strings["keyword_hits"].items() if strings else [])
            },
        },
        "third_party_sdks": [x["sdk"] for x in (sdk or {}).get("detected_sdks", [])],
        "dex_payloads": len((dex_inv or {}).get("dex_files", [])),
    }
    dump("016-security-indicators.json", indicators)

    print(f"  own classes={architecture['own_classes_total']} "
          f"modules={len(architecture['modules'])} "
          f"methods={architecture['own_methods_total']}")
    print(f"  cleartext(base-config)={indicators['network']['base_config_cleartext_permitted']}")
    print("== stage 03 complete ==")
    return 0


def extract_aids(configs) -> list:
    if not isinstance(configs, dict):
        return []
    aids = []
    for k, v in configs.items():
        if "apdu_service" in k and isinstance(v, str):
            import re
            for m in re.findall(r'android:name="([0-9A-Fa-f]+)"', v):
                aids.append(m)
    return aids


if __name__ == "__main__":
    sys.exit(main())
