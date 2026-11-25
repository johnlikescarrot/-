#!/usr/bin/env python3
"""Cross-cutting synthesis: concentration metrics, rubric scoring, findings register."""
from __future__ import annotations
import csv, json, math, os, re
from collections import Counter

ROOT = os.path.dirname(os.path.dirname(os.path.dirname(os.path.abspath(__file__))))
D = os.path.join(ROOT, "data")


def load(name: str) -> dict:
    with open(os.path.join(D, name)) as fh:
        return json.load(fh)


def main() -> None:
    prov, man, dex, sdk = load("01-provenance.json"), load("02-manifest.json"), load("03-dex.json"), load("04-sdks.json")
    nat, sig, res = load("05-native.json"), load("06-signals.json"), load("07-resources.json")

    classes = dex["classes"]
    total = len(classes)
    first_party = sum(1 for c in classes if c["name"].startswith("Lvn/com/vng/zalopay/"))
    internal = sum(1 for c in classes if c["name"].startswith("Lvn/"))
    roots = Counter()
    for c in classes:
        pkg = c["pkg"].split(".")
        root = ".".join(pkg[:2]) if pkg[0] in ("com", "org", "net", "io", "android", "androidx") else pkg[0]
        roots[root] += 1
    shares = [(r, n / total) for r, n in roots.most_common()]
    hhi = sum(s * s for _, s in shares)
    top10 = sum(s for _, s in shares[:10])

    # --- rubric -------------------------------------------------------------
    def has(fam: str) -> bool:
        return sig["signals"][fam]["present"]

    pinning = sig["signals"]["tls_pinning"]["markers"]
    dimensions = [
        {"id": "signing_provenance", "weight": 0.14, "score": (
            100 if prov["base_apk"]["signing_block"].get("scheme_blocks") and
            any(b["id"] == "v3" for b in prov["base_apk"]["signing_block"]["scheme_blocks"]) else 55),
         "evidence": [b["id"] for b in prov["base_apk"]["signing_block"]["scheme_blocks"]]},
        {"id": "platform_hardening", "weight": 0.16,
         "score": round(100 * (0.4 * nat["hardening"]["all_pie"] + 0.3 * nat["hardening"]["all_nx_stack"]
                               + 0.2 * (nat["hardening"]["bind_now_count"] / nat["lib_count"])
                               + 0.1 * (nat["hardening"]["stripped_count"] / nat["lib_count"])), 1),
         "evidence": {"pie": nat["hardening"]["all_pie"], "nx": nat["hardening"]["all_nx_stack"],
                      "bind_now": nat["hardening"]["bind_now_count"], "libs": nat["lib_count"]}},
        {"id": "transport_security", "weight": 0.16,
         "score": 90 if pinning else 60,
         "evidence": list(pinning)[:5]},
        {"id": "device_integrity", "weight": 0.14,
         "score": 95 if has("integrity_attestation") else 40,
         "evidence": list(sig["signals"]["integrity_attestation"]["markers"])[:6]},
        {"id": "anti_tamper_runtime", "weight": 0.10,
         "score": min(100, 25 * (has("root_detection") + has("hook_debug_detection")
                                 + has("emulator_detection") + has("hardening_vendor"))),
         "evidence": {"root": has("root_detection"), "hook": has("hook_debug_detection"),
                      "emulator": has("emulator_detection"), "shield": has("hardening_vendor")}},
        {"id": "data_minimisation", "weight": 0.14,
         "score": 55,
         "evidence": {"permissions": man["permission_count"], "sensitive_apis": list(sig["signals"]["data_collection"]["markers"])[:6],
                      "trackers": [h for h in sig["domains"]["by_class"] if "Ads" in h or "Analytics" in h]}},
        {"id": "code_hygiene", "weight": 0.10,
         "score": 45,
         "evidence": {"exported_test_components": 4, "legacy_flags": ["requestLegacyExternalStorage", "extractNativeLibs"],
                      "build_path_leaks": sum(l["build_path_leak_count"] for l in nat["libs"])}},
        {"id": "supply_chain", "weight": 0.06,
         "score": round(100 * (1 - top10), 1),
         "evidence": {"sdks": sdk["sdk_count"], "hhi": round(hhi, 3), "top10_share": round(top10, 3)}},
    ]
    weighted = round(sum(d["weight"] * d["score"] for d in dimensions) / sum(d["weight"] for d in dimensions), 1)

    findings = [
        {"id": "F-01", "title": "AndroidX test harness exported in a production payment build",
         "severity": "medium", "category": "code hygiene",
         "evidence": [c["name"] for c in man["exported_components"]["activities"] if "androidx.test" in c["name"]] +
                     [c["name"] for c in man["exported_components"]["activities"] if "compose.ui.tooling" in c["name"]],
         "why_it_matters": "Instrumentation/test activities ship with the release APK and are reachable by any app declaring the intent-filter action. They cannot disclose user data by themselves, but they widen the reachable surface of a payments app and signal a release-variant configuration gap.",
         "recommendation": "Strip test-only artefacts from release variants (androidTestImplementation to debugImplementation, tools:node=\"remove\" for tooling preview activities)."},
        {"id": "F-02", "title": "Static client credentials embedded in the manifest",
         "severity": "info", "category": "configuration",
         "evidence": [f"{s['name']} -> {s['masked']}" for s in man["secret_shaped_meta_data"]],
         "why_it_matters": "Google Maps and AppsFlyer dev keys are client-side by design; risk depends entirely on server-side key restriction. They are reported masked so this study does not republish credentials.",
         "recommendation": "Keep application-restricted keys, rotate if unrestricted, and monitor quota anomalies."},
        {"id": "F-03", "title": "Vendor build paths and internal component names leaked in native code",
         "severity": "low", "category": "information disclosure",
         "evidence": [{"lib": l["name"], "count": l["build_path_leak_count"], "sample": l["build_path_leaks"][:1]}
                      for l in nat["libs"] if l["build_path_leak_count"]][:6],
         "why_it_matters": "Debug paths disclose the shielding vendor's CI naming (BshieldAzure/vsts build agents) and confirm a native-only app-shield layer, which shortens an attacker's reconnaissance step.",
         "recommendation": "Strip debug paths with -ffile-prefix-map / --strip-debug in the consumer-proguard + CMake pipeline."},
        {"id": "F-04", "title": "Third-party code dominates the binary (98.4%)",
         "severity": "medium", "category": "supply chain",
         "evidence": {"first_party_classes": first_party, "total": total,
                      "top_roots": roots.most_common(8), "hhi": round(hhi, 3)},
         "why_it_matters": "Effective trust boundary is not ZaloPay's own code but ~59 700 inherited classes from 30+ vendors; each is an independent vulnerability channel inside the payment process.",
         "recommendation": "SBOM per release + vendor CVE SLA + feature-flag kill switches for SDK-heavy surfaces."},
        {"id": "F-05", "title": "Native-only application shielding with randomized library names",
         "severity": "info", "category": "anti-analysis",
         "evidence": {"bshield_asset": "assets/bshield.dat", "libs": [l["name"] for l in nat["libs"]
                                                                     if re.fullmatch(r"lib[a-z0-9]{6,10}\.so", l["name"])],
                      "markers": nat["hardening"]["protect_marker_union"]},
         "why_it_matters": "The shield lives entirely in .so + opaque assets, so Java-level review under-reports the real behaviour; auditors must budget native analysis. Opaque hardening also complicates the vendor's own incident response.",
         "recommendation": "Document the shield in the app's security whitepaper; keep deterministic, reproducible builds for the rest of the APK."},
        {"id": "F-06", "title": "Layered runtime integrity checks (root, hook, emulator, attestation)",
         "severity": "positive", "category": "defence in depth",
         "evidence": {k: len(v["markers"]) for k, v in sig["signals"].items()
                      if k in ("root_detection", "hook_debug_detection", "emulator_detection", "integrity_attestation")},
         "why_it_matters": "Multiple independent detection families plus server-side attestation raise the cost of instrumented analysis; string-level evidence is not proof of enforcement, so this is scored as capability rather than guarantee.",
         "recommendation": "Keep enforcement server-side; treat client checks as friction, never as the trust decision."},
        {"id": "F-07", "title": "Legacy manifest flags survive a targetSdk 36 migration",
         "severity": "low", "category": "platform posture",
         "evidence": ["requestLegacyExternalStorage=true", "extractNativeLibs=true",
                      "androidx.test components", f"targetSdk={man['target_sdk']}"],
         "why_it_matters": "Legacy storage and extraction flags have no effect on modern devices but indicate release-configuration drift, the same class of drift that produced F-01.",
         "recommendation": "Audit manifest merge output in CI (diff of merged manifest between variants)."},
        {"id": "F-08", "title": "Development endpoint of a payment partner embedded in release config",
         "severity": "low", "category": "information disclosure",
         "evidence": [s for s in sig["domains"]["cleartext_samples"] if "alipay" in s][:3],
         "why_it_matters": "A partner sandbox host (dev.alipay.net) is compiled into the production artefact. It is not reachable by the app at runtime for payments, but it reveals partner infrastructure naming and shows an unreviewed config patch.",
         "recommendation": "Gate partner sandbox configuration behind build variants; add secret/endpoint linting to the pipeline."},
        {"id": "F-09", "title": "No JAR (v1) signature in the base APK; v2 + v3 only",
         "severity": "positive", "category": "signing",
         "evidence": {"v1_certificates_in_base": len(prov["base_apk"]["v1_signature"]["certificates"]),
                      "split_v1_certificates": prov["split_apks"]["config.vi.apk"]["v1_certs"],
                      "v3_sdk_range": prov["base_apk"]["signing_block"]["scheme_blocks"][1]["signers"][0]["sdk_range_on_signer"]},
         "why_it_matters": "With minSdk 26, v1 adds no install coverage and only adds parsing ambiguity; the split APKs retain bundletool v1 blocks, which is expected for Play-delivered splits.",
         "recommendation": "None; document v3 key-rotation readiness."},
    ]

    # citation dataset for downstream reuse
    rows = [
        ("container", "xapk_bytes", prov["container"]["xapk_bytes"]),
        ("container", "base_apk_entries", prov["base_apk"]["zip"]["entries"]),
        ("container", "base_apk_uncompressed_bytes", prov["base_apk"]["zip"]["uncompressed_bytes"]),
        ("code", "dex_files", dex["dex_count"]),
        ("code", "classes", dex["total"]["classes"]),
        ("code", "methods", dex["total"]["methods"]),
        ("code", "fields", dex["total"]["fields"]),
        ("code", "unique_strings", dex["total"]["unique_strings"]),
        ("code", "first_party_classes", first_party),
        ("code", "third_party_share_pct", round(100 * (1 - first_party / total), 2)),
        ("code", "dependency_hhi", round(hhi, 4)),
        ("manifest", "permissions", man["permission_count"]),
        ("manifest", "activities", man["component_counts"]["activities"]),
        ("manifest", "services", man["component_counts"]["services"]),
        ("manifest", "receivers", man["component_counts"]["receivers"]),
        ("manifest", "providers", man["component_counts"]["providers"]),
        ("manifest", "exported_activities", man["exported_counts"]["activities"]),
        ("manifest", "deeplinks", man["deeplink_count"]),
        ("native", "libraries", nat["lib_count"]),
        ("native", "native_bytes", nat["hardening"]["total_bytes"]),
        ("native", "bind_now_libs", nat["hardening"]["bind_now_count"]),
        ("native", "stripped_libs", nat["hardening"]["stripped_count"]),
        ("resources", "locales", res["resource_table"]["locale_count"]),
        ("resources", "resource_entries", res["resource_table"]["entry_total"]),
        ("resources", "ml_models", len(res["assets"]["models"])),
        ("resources", "asset_bytes", res["assets"]["total_bytes"]),
        ("sdk", "vendors", sdk["sdk_count"]),
        ("privacy", "unique_hosts", sig["domains"]["unique_hosts"]),
        ("privacy", "vietnamese_strings", sig["vietnamese_strings"]),
        ("rubric", "weighted_score", weighted),
    ]
    with open(os.path.join(D, "dataset.csv"), "w", newline="") as fh:
        w = csv.writer(fh)
        w.writerow(["group", "metric", "value"])
        for r in rows:
            w.writerow(r)

    summary = {
        "artifact": {"package": man["package"], "version_name": man["version_name"],
                     "version_code": man["version_code"], "min_sdk": man["min_sdk"], "target_sdk": man["target_sdk"],
                     "sha256_base_apk": prov["split_apks"]["vn.com.vng.zalopay.apk"]["sha256"],
                     "sha256_xapk": prov["container"]["xapk_sha256"]},
        "concentration": {"hhi": round(hhi, 4), "top10_share": round(top10, 4),
                          "first_party_share_pct": round(100 * first_party / total, 2),
                          "vn_namespace_classes": internal,
                          "top_roots": roots.most_common(15)},
        "rubric": {"dimensions": dimensions, "weighted_score": weighted,
                   "scale": "0-100, higher = stronger posture; weights in data/08-summary.json"},
        "findings": findings,
        "metrics_csv": "data/dataset.csv",
        "counts": {"sdks": sdk["sdk_count"], "hosts": sig["domains"]["unique_hosts"],
                   "models": len(res["assets"]["models"]), "locales": res["resource_table"]["locale_count"]},
    }
    with open(os.path.join(D, "08-summary.json"), "w") as fh:
        json.dump(summary, fh, indent=2, ensure_ascii=False)
    print(json.dumps({"score": weighted,
                      "dimensions": {d["id"]: d["score"] for d in dimensions},
                      "hhi": round(hhi, 4), "top10": round(top10, 3),
                      "first_party_pct": round(100 * first_party / total, 2)}, indent=1))
    print("findings:", len(findings))


if __name__ == "__main__":
    main()
