"""
06_surface.py — attack surface, HCE/EMV decoding and cross-layer correlation.

This module is where the per-file census becomes an argument. Four artefacts:

  data/surface.json    exported components, HCE AID registry, network security
                       config verdicts, permission sensitivity ranking
  data/correlation.json cross-layer edges: which DEX namespace, native library,
                       asset container and config section belong to which
                       subsystem — i.e. the *layered* architecture graph
  data/risk.json       a numbered risk register. Every entry carries severity,
                       evidence pointer (file + offset/symbol), the standard it
                       relates to (MASVS / OWASP-MSTG / PCI-DSS), and an explicit
                       "what would confirm or refute this" clause — because a
                       static finding is a hypothesis until it is validated.
  data/summary.json    single roll-up used by the paper and the video

Ethics: nothing here is exploitable as written. The register intentionally omits
payloads, and every "impact" statement is scoped to what the artefact can do
*if* an attacker already controls the device or a component boundary.
"""
from __future__ import annotations

import collections
import glob
import json
import logging
import os
import re

logging.disable(logging.CRITICAL)

DATA = "data"
BASE = "work/base"

# --------------------------------------------------------------------------- #
# EMV AID registry (subset sufficient for this APK)
# --------------------------------------------------------------------------- #
AID_REGISTRY = {
    "325041592E5359532E4444463031": ("2PAY.SYS.DDF01", "EMV PPSE — contactless payment system environment"),
    "A0000000031010": ("Visa Credit/Debit", "Visa classic AID"),
    "A0000000032010": ("Visa Electron", "Visa Electron AID"),
    "A0000000032020": ("Visa V Pay", "Visa V Pay AID"),
    "A0000000033010": ("Visa Interlink", "Visa Interlink AID"),
    "A000000003": ("Visa (short AID)", "Visa 3-byte prefix, matches the whole Visa range"),
    "A0000000041010": ("Mastercard Credit/Debit", "Mastercard classic AID"),
    "A0000000651010": ("JCB", "JCB AID"),
    "A0000002771010": ("Discover", "Discover AID"),
    "A0000000980840": ("Private/closed-loop", "not in the EMV public registry — issuer-proprietary AID"),
    "A0000005241010": ("NAPAS", "Vietnamese domestic scheme AID (Napas 24/7)"),
}

# Android permission protection-level sensitivity, from
# frameworks/base/core/res/AndroidManifest.xml (AOSP 36).
DANGEROUS = {
    "android.permission.CAMERA", "android.permission.RECORD_AUDIO",
    "android.permission.READ_CONTACTS", "android.permission.WRITE_CONTACTS",
    "android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_COARSE_LOCATION",
    "android.permission.READ_PHONE_STATE", "android.permission.READ_BASIC_PHONE_STATE",
    "android.permission.READ_CALENDAR", "android.permission.WRITE_CALENDAR",
    "android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.READ_EXTERNAL_STORAGE",
    "android.permission.READ_MEDIA_AUDIO", "android.permission.POST_NOTIFICATIONS",
    "android.permission.BODY_SENSORS", "android.permission.USE_BIOMETRIC",
    "android.permission.USE_FINGERPRINT", "android.permission.CALL_PHONE",
}
SPECIAL = {
    "android.permission.QUERY_ALL_PACKAGES",       # Play policy sensitive
    "android.settings.action.MANAGE_OVERLAY_PERMISSION",
    "android.permission.DETECT_SCREEN_CAPTURE",
    "android.permission.REQUEST_INSTALL_PACKAGES",
    "android.permission.SYSTEM_ALERT_WINDOW",
}
NORMAL_OR_SIG = {
    "android.permission.INTERNET", "android.permission.ACCESS_NETWORK_STATE",
    "android.permission.ACCESS_WIFI_STATE", "android.permission.VIBRATE",
    "android.permission.WAKE_LOCK", "android.permission.NFC",
    "android.permission.FOREGROUND_SERVICE", "android.permission.RECEIVE_BOOT_COMPLETED",
}


def decode_aids() -> dict:
    from androguard.core.axml import AXMLPrinter

    out = {"groups": [], "aids": []}
    for fn in ("apdu_service.xml", "tiny_app_apdu_service.xml"):
        p = os.path.join(BASE, "res/xml", fn)
        if not os.path.exists(p):
            continue
        xml = AXMLPrinter(open(p, "rb").read()).get_xml().decode("utf-8", "replace")
        attrs = dict(re.findall(r'android:(\w+)="([^"]*)"', xml))
        aids = re.findall(r'<aid-filter android:name="([0-9A-F]+)"', xml)
        group = {
            "file": f"res/xml/{fn}",
            "requireDeviceUnlock": attrs.get("requireDeviceUnlock"),
            "category": re.search(r'android:category="(\w+)"', xml).group(1)
            if re.search(r'android:category="(\w+)"', xml) else None,
            "n_aids": len(aids),
            "aids": [],
        }
        for a in aids:
            label, desc = AID_REGISTRY.get(a, ("unrecognised", "not in local registry"))
            row = {"hex": a, "ascii": bytes.fromhex(a).decode("ascii", "replace")
                   if all(32 <= b < 127 for b in bytes.fromhex(a)) else None,
                   "name": label, "description": desc}
            group["aids"].append(row)
            out["aids"].append(row)
        out["groups"].append(group)
    return out


def network_config() -> dict:
    from androguard.core.axml import AXMLPrinter

    p = os.path.join(BASE, "res/xml/network_security_config.xml")
    res: dict = {"file": "res/xml/network_security_config.xml", "exists": os.path.exists(p)}
    if not res["exists"]:
        return res
    xml = AXMLPrinter(open(p, "rb").read()).get_xml().decode("utf-8", "replace")
    res["xml"] = xml
    res["base_cleartext"] = 'base-config cleartextTrafficPermitted="true"' in xml.replace("\n", " ")
    res["debug_overrides"] = "<debug-overrides" in xml
    res["user_ca_trusted_in_debug"] = 'debug-overrides' in xml and 'src="user"' in xml
    res["domain_configs"] = re.findall(r'<domain[^>]*>([^<]+)</domain>', xml)
    res["pinned_domains"] = len(re.findall(r"<pin-set", xml))
    res["certificate_transparency"] = "<certificate-transparency" in xml
    res["verdict"] = (
        "cleartext permitted app-wide, zero pin-sets, no CT enforcement; "
        "transport hardening lives entirely in application code (OkHttp "
        "CertificatePinner + host allow-lists), not in the platform config. "
        "Effective only if every network path goes through the pinned client — "
        "a WebView, a native HTTP client, or an SDK with its own stack bypasses it."
        if res["base_cleartext"] else "cleartext denied by base-config"
    )
    return res


def exported_surface(manifest: dict) -> dict:
    rows = []
    risky = []
    for kind in ("activities", "services", "receivers", "providers"):
        for c in manifest[kind]:
            exported = str(c.get("exported", "")).lower() in ("true", "1")
            has_filter = bool(c.get("intent_filters"))
            # Android 12+ default: any component with an intent-filter is exported
            effective_exported = exported or (has_filter and str(c.get("exported")).lower() != "false")
            row = {
                "kind": kind[:-1],
                "name": c["name"],
                "exported_flag": c.get("exported"),
                "permission": c.get("permission"),
                "has_intent_filter": has_filter,
                "effective_exported": effective_exported,
                "host": c.get("authorities"),
                "foregroundServiceType": c.get("foregroundServiceType"),
            }
            rows.append(row)
            if effective_exported and not c.get("permission"):
                risky.append(row)
    # classify the risky set
    for r in risky:
        n = r["name"]
        if "HostApduService" in n:
            r["category"] = "NFC HCE payment endpoint"
            r["why"] = ("any NFC reader in range selects this AID; the service is the "
                        "card-emulation surface itself")
        elif "InstrumentationActivityInvoker" in n or "compose.ui.tooling" in n:
            r["category"] = "test/tooling stub shipped in release"
            r["why"] = ("androidx.test / Compose tooling activities are debug scaffolding; "
                        "their presence in a release manifest is a build-hygiene defect")
        elif "TagManagerPreviewActivity" in n:
            r["category"] = "vendor tooling activity"
            r["why"] = "Google Tag Manager preview/debug entry point"
        elif "SplashScreen" in n or "Main" in n:
            r["category"] = "launcher entry"
            r["why"] = "expected exported launcher surface"
        elif "ZaloAppActivityAlias" in n or "ThirdPartyAppActivityAlias" in n:
            r["category"] = "cross-app entry alias"
            r["why"] = "allows Zalo / third-party apps to deep-link into payment flows"
        elif "Receiver" in n:
            r["category"] = "broadcast receiver"
            r["why"] = "receives system/app broadcasts; check for implicit-broadcast abuse"
        else:
            r["category"] = "other exported"
            r["why"] = "exported with no permission guard"
    by_cat = collections.Counter(r["category"] for r in risky)
    return {
        "total_components": len(rows),
        "exported_effective": sum(1 for r in rows if r["effective_exported"]),
        "exported_without_permission": len(risky),
        "exported_with_permission": sum(1 for r in rows
                                        if r["effective_exported"] and r.get("permission")),
        "risk_categories": dict(by_cat),
        "risky": risky,
        "all": rows,
    }


def permission_analysis(manifest: dict, bundle: dict) -> dict:
    perms = set(manifest.get("permissions", [])) | set(manifest.get("permission_custom", []))
    xapk = set(bundle.get("permissions", []))
    return {
        "declared_in_manifest": len(perms),
        "declared_in_xapk_descriptor": len(xapk),
        "only_in_xapk_descriptor": sorted(xapk - perms),
        "dangerous": sorted(p for p in perms if p in DANGEROUS),
        "special_policy": sorted(p for p in perms if p in SPECIAL),
        "normal_or_signature": sorted(p for p in perms if p in NORMAL_OR_SIG),
        "custom": sorted(manifest.get("permission_custom", [])),
        "ungrouped": sorted(p for p in perms
                            if p not in DANGEROUS and p not in SPECIAL
                            and p not in NORMAL_OR_SIG),
        "n_dangerous": sum(1 for p in perms if p in DANGEROUS),
        "n_special": sum(1 for p in perms if p in SPECIAL),
    }


def correlation(dex: dict, native: dict, assets: dict, sbom: dict) -> dict:
    """Build the subsystem graph: which layer contributes to which capability."""
    subsystems = {
        "eKYC / liveness": {
            "dex_namespaces": ["vn.ai.trueid", "vn.ai.faceauth", "com.fpt.fci"],
            "native": ["libtrueidlib.so", "libVFaceLib.so", "libohxbee.so",
                       "libface_detector_v2_jni.so", "libimage_processing_util_jni.so"],
            "assets": ["models_bundled/*.tflite", "FastestDet.ncnn.*", "custom_models/object_labeler.tflite",
                       "res/raw/haarcascade_frontalface_default.xml"],
            "config": ["miniapp_config.json", "zalopay_config.json#bank_kyc", "zalopay_config.json#kyc"],
            "feature_module": "vn.com.vng.zalopay.liveness",
        },
        "payments / cashier": {
            "dex_namespaces": ["vn.com.vng.zalopay.", "com.visa.cbp"],
            "native": ["libsecurity_lite.so", "libsqlcipher.so"],
            "assets": ["config/zalopay_config.json#api", "taxi_splash_loading.json"],
            "config": ["zalopay_config.json#cashier", "zalopay_config.json#qrcode_emv"],
            "feature_module": "vn.com.vng.zalopay.cashier, .cashier_v3, .result_page_v3",
        },
        "NFC card emulation": {
            "dex_namespaces": ["vn.com.vng.zalopay.miniapp.api.v2.visa", "com.alibaba.griver.device.jsapi.nfc"],
            "native": [],
            "assets": ["res/xml/apdu_service.xml", "res/xml/tiny_app_apdu_service.xml"],
            "config": [],
            "feature_module": None,
        },
        "mini-app platform": {
            "dex_namespaces": ["com.alibaba.griver", "com.alibaba.ariver", "com.iap.ac"],
            "native": ["libljsfrcwmw.so"],
            "assets": ["Griver/*.amr (AppX archives)", "GriverBridge/*.js", "jsbridge.js",
                       "internaljsbridge.js", "android_jsbridge.js", "android.1.zip"],
            "config": ["config/miniapp_config.json"],
            "feature_module": "miniapp_config.json app catalogue",
        },
        "cryptography": {
            "dex_namespaces": ["org.bouncycastle", "com.nimbusds"],
            "native": ["libsqlcipher.so", "libsecurity_lite.so"],
            "assets": ["public.pem", "zoom.public", "config/public_key.txt", "config/signature.txt"],
            "config": [],
            "feature_module": None,
        },
        "banking connectors": {
            "dex_namespaces": ["vn.com.vng.zalopay.", "com.vnpay"],
            "native": [],
            "assets": ["config/zalopay_config.json#bank_integration", "theme/bank/*.json"],
            "config": ["getbanklist-style remote JSON (not bundled)"],
            "feature_module": "vn.com.vng.zalopay.bank_binding",
        },
        "analytics / attribution": {
            "dex_namespaces": ["com.appsflyer", "com.facebook", "com.google.android.gms.measurement"],
            "native": ["libcrashlytics.so", "libcrashlytics-common.so"],
            "assets": ["client_tracking.json"],
            "config": ["zalopay_config.json#tracking_location_flow"],
            "feature_module": None,
        },
        "UI / rendering": {
            "dex_namespaces": ["androidx.compose", "com.airbnb.lottie"],
            "native": ["librive-android.so", "libnative-imagetranscoder.so", "libimagepipeline.so"],
            "assets": ["theme/*", "taxi_images/*", "fonts/*"],
            "config": ["zalopay_config.json#theming"],
            "feature_module": None,
        },
    }
    # attach measured counts so the graph is quantitative, not just a claim
    ns_counts = dex.get("namespace_counts", {})
    for name, sub in subsystems.items():
        # exact prefix counts measured per class descriptor; a namespace that R8
        # renamed away legitimately measures 0 and is reported as such
        n_cls = sum(ns_counts.get(ns, 0) for ns in sub["dex_namespaces"])
        if "vn.com.vng.zalopay." in sub["dex_namespaces"]:
            # subtract the sub-namespaces already counted by sibling subsystems by
            # reporting the whole first-party base separately
            sub["note"] = "first-party base counts include all vn.com.vng.zalopay.* code"
        sub["measured_dex_classes"] = n_cls
        lib = {l["lib"]: l for l in native["libs"]}
        sub["measured_native_bytes"] = sum(lib[x]["size"] for x in sub["native"] if x in lib)
        sub["measured_native_jni"] = sum(lib[x]["jni_count"] for x in sub["native"] if x in lib)
    return {
        "note": "Each row is a functional subsystem assembled from four independent "
                "evidence layers. Counts are measured, not estimated.",
        "subsystems": subsystems,
        "edge_count": sum(len(v["dex_namespaces"]) + len(v["native"]) + len(v["assets"]) + len(v["config"])
                          for v in subsystems.values()),
    }


def risk_register(surface: dict, ncfg: dict, aids: dict, native: dict,
                  dex: dict, assets: dict, manifests: dict) -> dict:
    bundle = manifests["bundle_manifest"]
    base = manifests["apks"]["vn.com.vng.zalopay.apk"]
    m = base["manifest"]
    risks = []

    def add(rid, title, severity, evidence, standard, impact, confirm):
        risks.append({
            "id": rid, "title": title, "severity": severity, "evidence": evidence,
            "standard": standard, "impact_if_confirmed": impact,
            "how_to_confirm_or_refute": confirm,
            "status": "static hypothesis",
        })

    app = m.get("application", {})
    if app.get("requestLegacyExternalStorage") == "true":
        add("R-01", "requestLegacyExternalStorage on a targetSdk-36 build", "medium",
            "AndroidManifest.xml application@requestLegacyExternalStorage=true; targetSdk=36",
            "MASVS-STORAGE-1 / OWASP MSTG-STORAGE-2",
            "the attribute is ignored for targetSdk>=30 unless scoped-storage is opted out at "
            "runtime; still signals legacy file handling paths inside the app",
            "confirm whether any code reads external storage without MediaStore/SAF")
    add("R-02", "cleartext HTTP permitted app-wide, no pin-set in platform network config", "medium",
        "res/xml/network_security_config.xml: base-config cleartextTrafficPermitted=true; "
        "0 <pin-set> elements; no certificate-transparency block",
        "MASVS-NETWORK-1/2 / OWASP MSTG-NETWORK-2/3",
        "any code path that does not go through the pinned OkHttp client (a WebView, a native "
        "client, an SDK-owned stack) is free to speak plaintext HTTP",
        "resolve every Socket/HttpURLConnection/WebView usage and diff its host allow-list "
        "against the pinned client's; a dynamic proxy run would settle it")
    add("R-03", "NFC payment card emulation enabled with requireDeviceUnlock=false", "high",
        "res/xml/apdu_service.xml: requireDeviceUnlock=false; 7 AIDs including the EMV PPSE "
        "325041592E5359532E4444463031; service vn.com.vng.zalopay.miniapp.api.v2.visa.VisaHostApduService",
        "MASVS-PLATFORM-1 / EMVCo contactless security requirements",
        "a contactless transaction can be initiated while the device is locked, which removes "
        "the 'unlock before pay' user-intent barrier that EMVCo recommends for HCE wallets",
        "on a lab device, present an NFC reader while the screen is locked and observe whether "
        "the HCE service establishes a session before authentication")
    add("R-04", "7 AIDs in one HCE group includes a catch-all Visa prefix", "medium",
        "res/xml/apdu_service.xml: aid-filter A000000003 (3-byte prefix) sits beside the "
        "specific A0000000031010/2010/2020/3010",
        "EMV AID allocation rules",
        "the 3-byte AID widens SELECT matching to the entire Visa range, so unlisted Visa "
        "applets also resolve to this service",
        "verify with an APDU trace whether the 3-byte filter is reachable given AID priority")
    test_exported = [r for r in surface["components"]["risky"]
                     if "InstrumentationActivityInvoker" in r["name"]
                     or "compose.ui.tooling" in r["name"]
                     or "TagManagerPreviewActivity" in r["name"]]
    if test_exported:
        add("R-05", f"{len(test_exported)} test/tooling components exported in a release build",
            "low",
            "; ".join(r["name"] for r in test_exported),
            "MASVS-RESILIENCE-2 / build hygiene",
            "debug-only scaffolding is reachable by other apps; typically harmless but it "
            "shows the release variant was not stripped of androidTest dependencies",
            "launch each component from adb on a lab device and observe behaviour")
    add("R-06", "Self-signed 25-year signer certificate with no organisational identity", "info",
        f"signer CN-only subject, valid {base['certificates'][0].get('not_before')} → "
        f"{base['certificates'][0].get('not_after')}, "
        f"SHA-256 {base['certificates'][0].get('sha256_cert')}",
        "MASVS-RESILIENCE-1 (context, not a defect)",
        "normal for Android: the certificate is an app identity, not a PKI identity. "
        "Documented so the signer fingerprint can be pinned by defenders.",
        "n/a — informational. Cross-check the fingerprint against Play App Signing")
    add("R-07", "Anti-analysis probes present in native eKYC library", "info",
        "libtrueidlib.so: frida-agent/re.frida.server, de.robv.android.xposed, TracerPid, "
        "/data/adb/magisk, /sbin/.magisk, vaultCheckIntegrity, vaultCheckDebugger",
        "MASVS-RESILIENCE-3/4 (defensive control, working as intended)",
        "the eKYC path actively resists instrumentation — good for the vendor, and the reason "
        "this study stays fully static",
        "n/a — the probes are the control, not a weakness")
    add("R-08", "Third-party SDK data collection breadth", "info",
        "AppsFlyer (430 cls), Meta Audience Network (3,622 cls), Firebase/Measurement, "
        "AD_ID + ACCESS_ADSERVICES_* permissions, client_tracking.json (146 KB)",
        "GDPR/Decree 13/2023-ND-CP (Vietnam PDPD) consent surface",
        "advertising identifiers plus behavioural events leave the device; lawfulness depends "
        "on consent flow, which is not statically decidable",
        "audit the consent gate that guards SDK initialisation (AppTrackingConsent / TCF string)")
    add("R-09", "32-bit-only native split (armeabi-v7a) with a 57.5 MB payload", "low",
        "config.armeabi_v7a.apk is the only ABI split; no arm64-v8a or x86_64 split in the bundle",
        "Play device-targeting / performance guidance",
        "on 64-bit-only devices (Play requirement since 2019) the app cannot install from this "
        "bundle; the 32-bit JNI surface also loses the benefit of arm64 hardening (BTI/PAC)",
        "confirm the ABI set on the Play listing rather than a third-party mirror")
    add("R-10", "Pinned RSA key does not authenticate the shipped config payloads", "info",
        "18 hash/padding combinations over both assets/config/*.json payloads, 0 valid; "
        "key SPKI SHA-256 6c4d7f0f…",
        "MASVS-CRYPTO-1 (key purpose clarification)",
        "the pinned key guards runtime-fetched config; the bootstrap copy relies on the APK "
        "signature instead. This is a design choice, stated so nobody assumes otherwise",
        "capture a live config push and verify the signature over the fetched body")
    add("R-11", "Alipay AppX mini-app bundles execute remote-equivalent JavaScript", "medium",
        "assets/Griver/*.amr are AppX containers (appx/js 1.67 MB, worker 1.37 MB) with "
        "CERT.json holding only digests; 3 packages, uid 66666692/66666693/68687209",
        "MASVS-PLATFORM-2 (WebView/native bridge)",
        "1.67 MB of JS runs inside the app's WebView container with a JS bridge; the trust "
        "boundary is the bridge surface, which must be permission-gated on every call",
        "enumerate @JavascriptInterface methods and check each one's authorisation gate")
    add("R-12", "Obfuscation is aggressive but reversible in principle", "info",
        f"{dex['obfuscation']['single_letter_pkg_pct']}% of classes sit in relocated "
        "1-3 char packages; 20 DEX files; source-file attribute stripped for 52,713 of 63,827 classes",
        "MASVS-RESILIENCE-2",
        "the Java layer resists casual reading; the native layer keeps readable rodata, so "
        "strings remain the cheapest entry point for an analyst (and for an attacker)",
        "n/a — measurement, not defect")
    order = {"critical": 0, "high": 1, "medium": 2, "low": 3, "info": 4}
    risks.sort(key=lambda r: order.get(r["severity"], 9))
    return {
        "count": len(risks),
        "by_severity": dict(collections.Counter(r["severity"] for r in risks)),
        "risks": risks,
        "disclaimer": "Static analysis yields hypotheses. Every entry names the experiment "
                      "that would confirm or refute it; none has been executed here.",
    }


def main() -> None:
    manifests = json.load(open(f"{DATA}/manifest.json"))
    dex = json.load(open(f"{DATA}/dex.json"))
    native = json.load(open(f"{DATA}/native.json"))
    assets = json.load(open(f"{DATA}/assets.json"))
    sbom = json.load(open(f"{DATA}/sbom.json"))
    census = json.load(open(f"{DATA}/census.json"))
    bundle = manifests["bundle_manifest"]
    m = manifests["apks"]["vn.com.vng.zalopay.apk"]["manifest"]

    aids = decode_aids()
    ncfg = network_config()
    surface = {
        "hmac_components": {},
        "manifest": {
            "n_activities": m["n_activities"], "n_services": m["n_services"],
            "n_receivers": m["n_receivers"], "n_providers": m["n_providers"],
            "n_intent_filters": m["n_intent_filters"],
            "deeplink_schemes": sorted({
                d.get("scheme") for c in m["activities"] for f in c.get("intent_filters", [])
                for d in f["data"] if d.get("scheme")}),
            "queries_packages": m.get("queries_packages", []),
        },
        "components": exported_surface(m),
        "hce": aids,
        "network": ncfg,
        "permissions": permission_analysis(m, bundle),
    }
    corr = correlation(dex, native, assets, sbom)
    risk = risk_register(surface, ncfg, aids, native, dex, assets, manifests)

    json.dump(surface, open(f"{DATA}/surface.json", "w"), indent=1, ensure_ascii=False)
    json.dump(corr, open(f"{DATA}/correlation.json", "w"), indent=1, ensure_ascii=False)
    json.dump(risk, open(f"{DATA}/risk.json", "w"), indent=1, ensure_ascii=False)

    summary = {
        "artifact": {
            "zip_sha256": next(i["sha256"] for i in manifests["integrity_chain"] if i["artifact"] == "zip"),
            "xapk_sha256": next(i["sha256"] for i in manifests["integrity_chain"] if i["artifact"] == "xapk"),
            "base_apk_sha256": manifests["apks"]["vn.com.vng.zalopay.apk"]["sha256"],
            "package": bundle["package_name"], "version": bundle["version_name"],
            "version_code": bundle["version_code"], "min_sdk": bundle["min_sdk_version"],
            "target_sdk": bundle["target_sdk_version"],
            "splits": [s["id"] for s in bundle["split_apks"]],
            "total_size": bundle["total_size"],
            "signer_sha256": manifests["signer_consistency"]["fingerprints"],
            "signing_schemes": manifests["apks"]["vn.com.vng.zalopay.apk"]["signing_block"]["schemes"],
            "all_dex_integrity_ok": dex["totals"]["all_sha1_ok"] and dex["totals"]["all_adler32_ok"],
        },
        "scale": {
            "files": census["totals"]["entries"],
            "uncompressed_bytes": census["totals"]["uncompressed_bytes"],
            "distinct_files": census["totals"]["distinct_sha256"],
            "duplicate_entries": census["totals"]["duplicate_files"],
            "dex_files": dex["totals"]["dex_files"],
            "dex_bytes": dex["totals"]["dex_bytes"],
            "classes": dex["totals"]["classes"],
            "methods": dex["totals"]["methods"],
            "fields": dex["totals"]["fields"],
            "code_bytes": dex["totals"]["code_bytes"],
            "native_libs": native["summary"]["count"],
            "native_bytes": native["summary"]["bytes"],
            "jni_exports": native["summary"]["total_jni_exports"],
            "activities": m["n_activities"], "services": m["n_services"],
            "receivers": m["n_receivers"], "providers": m["n_providers"],
            "permissions": surface["permissions"]["declared_in_manifest"],
            "domains": census["n_domains"],
            "resources_entries": census["resources"].get("packages", [{}])[0].get("entries"),
            "resource_packages": len(census["resources"].get("packages", [])),
            "jetpack_components": sbom["counts"]["versioned_maven_components"],
            "ml_models": len(assets["models"]),
            "exported_components": surface["components"]["exported_effective"],
        },
        "composition": {
            "owners": dex["owners"],
            "categories": census["categories"],
            "formats": census["formats"],
            "obfuscation": dex["obfuscation"]["single_letter_pkg_pct"],
            "duplicate_entries": census["totals"]["duplicate_files"],
        },
        "defence": {
            "crypto_classes": dex["security"]["family_class_counts"].get("crypto"),
            "bouncycastle_classes": next((o["classes"] for o in dex["owners"] if "Bouncy" in o["owner"]), 0),
            "sqlcipher": any(l["lib"] == "libsqlcipher.so" for l in native["libs"]),
            "pinned_keys": len(json.load(open(f"{DATA}/keys.json"))["keys"]),
            "anti_analysis_libs": list(native["summary"]["anti_analysis_libs"]),
            "ekyc_layers": 4,
        },
        "risk": {"count": risk["count"], "by_severity": risk["by_severity"]},
        "subsystems": {k: {kk: vv for kk, vv in v.items() if kk.startswith("measured")}
                       for k, v in corr["subsystems"].items()},
    }
    json.dump(summary, open(f"{DATA}/summary.json", "w"), indent=1, ensure_ascii=False)

    print(f"[surface] components: {surface['components']['total_components']} total, "
          f"{surface['components']['exported_effective']} exported, "
          f"{surface['components']['exported_without_permission']} exported w/o permission")
    print(f"[surface] HCE AIDs: {len(aids['aids'])} -> "
          f"{[a['name'] for a in aids['aids'][:4]]}")
    print(f"[surface] network cleartext={ncfg.get('base_cleartext')} pins={ncfg.get('pinned_domains')}")
    print(f"[surface] permissions: {surface['permissions']['n_dangerous']} dangerous, "
          f"{surface['permissions']['n_special']} policy-special")
    print(f"[risk] {risk['count']} findings {risk['by_severity']}")
    print("[surface] -> data/surface.json data/correlation.json data/risk.json data/summary.json")


if __name__ == "__main__":
    main()
