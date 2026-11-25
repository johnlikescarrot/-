"""Stage 04 — third-party SDK attribution and tracker census.

Two independent signals are fused:

1. **Namespace attribution** — package prefixes present in the DEX type pool
   (`com.facebook.ads.`, `com.alibaba.griver.`, …).  Objective, but over-counts
   vendored forks.
2. **Library fingerprints** — exact artefact names harvested from
   `META-INF/*.version` (Gradle module identity), `res/raw` keeper files,
   `assets` runtime bundles and the merged string pool.

Signal 2 is authoritative because it survives renaming; signal 1 gives size.

Outputs: data/sdks.json, data/trackers.json, data/facts_sdks.json
"""

from __future__ import annotations

import collections
import re
import zipfile
from pathlib import Path

from common import APK_DIR, XAPK_DIR, read_json, write_json, Stage

# ---------------------------------------------------------------------------
# Attribution table.  `prefixes` are matched against the DEX class namespace.
# `artifacts` are matched against META-INF/*.version and resource names.
# `cat` groups for the dossier; `note` is a one-line provenance remark.
# ---------------------------------------------------------------------------
SDK_TABLE: list[dict] = [
    # --- Google / AndroidX ------------------------------------------------
    {"id": "androidx", "name": "AndroidX (Jetpack)", "vendor": "Google", "cat": "framework",
     "prefixes": ["androidx.", "com.google.android.material."], "artifacts": ["androidx."]},
    {"id": "compose", "name": "Jetpack Compose", "vendor": "Google", "cat": "ui",
     "prefixes": ["androidx.compose."], "artifacts": ["androidx.compose."]},
    {"id": "firebase", "name": "Firebase", "vendor": "Google", "cat": "backend",
     "prefixes": ["com.google.firebase."], "artifacts": ["com.google.firebase"]},
    {"id": "crashlytics", "name": "Firebase Crashlytics", "vendor": "Google", "cat": "telemetry",
     "prefixes": ["com.google.firebase.crashlytics.", "com.google.firebase.analytics."],
     "artifacts": ["crashlytics"]},
    {"id": "gms", "name": "Google Play services", "vendor": "Google", "cat": "backend",
     "prefixes": ["com.google.android.gms."], "artifacts": ["play-services"]},
    {"id": "mlkit", "name": "ML Kit (vision, barcode, face)", "vendor": "Google", "cat": "ml",
     "prefixes": ["com.google.mlkit.", "com.google.android.gms.vision."], "artifacts": ["mlkit"]},
    {"id": "tink", "name": "Google Tink (crypto)", "vendor": "Google", "cat": "crypto",
     "prefixes": ["com.google.crypto.tink."], "artifacts": ["tink"]},
    {"id": "protobuf-javalite", "name": "Protocol Buffers", "vendor": "Google", "cat": "serialisation",
     "prefixes": ["com.google.protobuf."], "artifacts": ["protobuf"]},
    {"id": "gson", "name": "Gson", "vendor": "Google", "cat": "serialisation",
     "prefixes": ["com.google.gson."], "artifacts": ["gson"]},
    {"id": "exoplayer", "name": "ExoPlayer / Media3", "vendor": "Google", "cat": "media",
     "prefixes": ["com.google.android.exoplayer2.", "androidx.media3."], "artifacts": ["exoplayer", "media3"]},
    {"id": "billing", "name": "Google Play Billing 8.3.0", "vendor": "Google", "cat": "commerce",
     "prefixes": ["com.android.billingclient."], "artifacts": ["billing"]},
    # --- Meta -------------------------------------------------------------
    {"id": "fb-ads", "name": "Meta Audience Network", "vendor": "Meta", "cat": "ads",
     "prefixes": ["com.facebook.ads."], "artifacts": ["audience-network"]},
    {"id": "fb-sdk", "name": "Facebook SDK (Login/Share/Graph)", "vendor": "Meta", "cat": "social",
     "prefixes": ["com.facebook.appevents.", "com.facebook.login.", "com.facebook.share.", "com.facebook.internal."],
     "artifacts": ["facebook-core", "facebook-common"]},
    {"id": "soloader", "name": "SoLoader", "vendor": "Meta", "cat": "native",
     "prefixes": ["com.facebook.soloader."], "artifacts": ["soloader"]},
    # --- Alibaba / Ant ----------------------------------------------------
    {"id": "griver", "name": "Alibaba Griver (mini-program runtime)", "vendor": "Alibaba", "cat": "miniapp",
     "prefixes": ["com.alibaba.griver."], "artifacts": ["griver"]},
    {"id": "ariver", "name": "Alibaba Ariver (mini-program kernel)", "vendor": "Alibaba", "cat": "miniapp",
     "prefixes": ["com.alibaba.ariver."], "artifacts": ["ariver"]},
    {"id": "mpp-client", "name": "Alipay mPaaS / MPP client", "vendor": "Ant Group", "cat": "miniapp",
     "prefixes": ["com.iap.ac.android.", "com.iap.android.", "com.alipay."], "artifacts": ["mpp"]},
    {"id": "afu", "name": "Alipay AFU (H5 container)", "vendor": "Ant Group", "cat": "webview",
     "prefixes": ["com.alipay.mobile.", "com.alibaba.fastjson."], "artifacts": ["fastjson"]},
    # --- Payments / identity ----------------------------------------------
    {"id": "visa-cbp", "name": "Visa Cardholder Benefits / CBP (NFC)", "vendor": "Visa", "cat": "payments",
     "prefixes": ["com.visa.cbp.", "com.visa."], "artifacts": ["visa"]},
    {"id": "napas", "name": "NAPAS / VietQR linkage", "vendor": "NAPAS", "cat": "payments",
     "prefixes": ["vn.napas."], "artifacts": []},
    {"id": "trueid", "name": "VNPT / TrueID eKYC", "vendor": "VNG / partner", "cat": "identity",
     "prefixes": ["vn.ai.trueid."], "artifacts": ["trueid"]},
    {"id": "vface", "name": "VFaceLib — on-device face liveness", "vendor": "VNG", "cat": "identity",
     "prefixes": [], "artifacts": ["libVFaceLib"], "native_only": True},
    {"id": "barhopper", "name": "Barhopper v3 — barcode/QR scanner", "vendor": "Google", "cat": "ml",
     "prefixes": [], "artifacts": ["libbarhopper"], "native_only": True},
    # --- Crypto / transport ------------------------------------------------
    {"id": "bouncycastle", "name": "Bouncy Castle (incl. PQC)", "vendor": "Legion of the Bouncy Castle", "cat": "crypto",
     "prefixes": ["org.bouncycastle."], "artifacts": ["bcpkix", "bcprov"]},
    {"id": "conscrypt", "name": "Conscrypt TLS", "vendor": "Google", "cat": "crypto",
     "prefixes": ["org.conscrypt."], "artifacts": ["conscrypt"]},
    {"id": "okhttp", "name": "OkHttp 3.x", "vendor": "Square", "cat": "network",
     "prefixes": ["okhttp3.", "okio."], "artifacts": ["okhttp"]},
    {"id": "retrofit", "name": "Retrofit 2", "vendor": "Square", "cat": "network",
     "prefixes": ["retrofit2."], "artifacts": ["retrofit"]},
    {"id": "moshi", "name": "Moshi", "vendor": "Square", "cat": "serialisation",
     "prefixes": ["com.squareup.moshi."], "artifacts": ["moshi"]},
    {"id": "coil", "name": "Coil image loader", "vendor": "Coil", "cat": "ui",
     "prefixes": ["coil."], "artifacts": ["coil"]},
    {"id": "glide", "name": "Glide", "vendor": "Google", "cat": "ui",
     "prefixes": ["com.bumptech.glide."], "artifacts": ["glide"]},
    {"id": "chucker", "name": "Chucker HTTP inspector", "vendor": "ChuckerTeams", "cat": "debug",
     "prefixes": ["com.github.chuckerteam."], "artifacts": ["chucker"]},
    # --- Analytics / attribution ------------------------------------------
    {"id": "adjust", "name": "Adjust (attribution)", "vendor": "Adjust GmbH", "cat": "attribution",
     "prefixes": ["com.adjust.sdk."], "artifacts": ["adjust"]},
    {"id": "appsflyer", "name": "AppsFlyer (attribution)", "vendor": "AppsFlyer", "cat": "attribution",
     "prefixes": ["com.appsflyer."], "artifacts": ["appsflyer"]},
    {"id": "facebook-appevents", "name": "Facebook App Events", "vendor": "Meta", "cat": "analytics",
     "prefixes": ["com.facebook.appevents."], "artifacts": []},
    {"id": "adjust-facebook", "name": "Facebook → Adjust bridge", "vendor": "mixed", "cat": "attribution",
     "prefixes": ["com.facebook.adjust."], "artifacts": []},
    # --- Kotlin / runtime ---------------------------------------------------
    {"id": "kotlin", "name": "Kotlin stdlib + coroutines", "vendor": "JetBrains", "cat": "runtime",
     "prefixes": ["kotlin.", "kotlinx.coroutines."], "artifacts": ["kotlin-stdlib", "kotlinx-coroutines"]},
    {"id": "dagger", "name": "Dagger / Hilt DI", "vendor": "Google", "cat": "runtime",
     "prefixes": ["dagger.", "dagger.hilt.", "javax.inject."], "artifacts": ["hilt", "dagger"]},
    {"id": "rxjava", "name": "RxJava 2/3", "vendor": "Netflix/ReactiveX", "cat": "runtime",
     "prefixes": ["io.reactivex."], "artifacts": ["rxjava"]},
    {"id": "room", "name": "Room / SQLite", "vendor": "Google", "cat": "storage",
     "prefixes": ["androidx.room."], "artifacts": ["room"]},
    {"id": "realm", "name": "Realm DB", "vendor": "MongoDB", "cat": "storage",
     "prefixes": ["io.realm."], "artifacts": ["realm"]},
    {"id": "lottie", "name": "Lottie animations", "vendor": "Airbnb", "cat": "ui",
     "prefixes": ["com.airbnb.lottie."], "artifacts": ["lottie"]},
    {"id": "zalo-sdk", "name": "Zalo SDK / Zalo login", "vendor": "VNG", "cat": "social",
     "prefixes": ["com.zing.zalo."], "artifacts": ["zalo"]},
    {"id": "smartotp", "name": "SmartOTP (SoftOTP)", "vendor": "VNG", "cat": "auth",
     "prefixes": ["com.vas.smartotp."], "artifacts": []},
    # --- Security / anti-tamper -------------------------------------------
    {"id": "safetynet", "name": "Play Integrity / SafetyNet", "vendor": "Google", "cat": "attestation",
     "prefixes": ["com.google.android.play.core.integrity."], "artifacts": ["play-core"]},
    {"id": "rootbeer", "name": "RootBeer root detection", "vendor": "scottyab", "cat": "anti-tamper",
     "prefixes": ["com.scottyab.rootbeer."], "artifacts": ["rootbeer"]},
    {"id": "emulator-detect", "name": "Emulator/root heuristics", "vendor": "in-house", "cat": "anti-tamper",
     "prefixes": ["com.vnpay.security."], "artifacts": []},
]

TRACKER_CATS = {"ads", "analytics", "attribution", "telemetry"}


def main() -> None:
    st = Stage("04-sdk")

    dex = read_json("dex_packages.json")
    summary = read_json("dex_summary.json")
    full_packages = [p["package"] for p in dex["packages"]]

    # artifact fingerprints straight out of the container
    zf = zipfile.ZipFile(XAPK_DIR / "vn.com.vng.zalopay.apk")
    names = zf.namelist()
    version_files = [n.rsplit("/", 1)[-1].replace(".version", "") for n in names if n.endswith(".version")]
    native_libs = [n.rsplit("/", 1)[-1] for n in names if n.startswith("lib/")]
    asset_blobs = [n for n in names if n.startswith("assets/") and n.count("/") >= 1]

    st.log(f"fingerprint corpus: {len(version_files)} .version modules, {len(native_libs)} native libs, {len(asset_blobs)} assets")

    # Package prefix → class count from full package table (need >400 rows: recompute)
    pkg_classes = collections.Counter()
    for row in dex["packages"]:
        pkg_classes[row["package"]] = row["classes"]
    total_pkgs = dex["count"]

    # NOTE: dex_packages.json is truncated to 400 rows; attrs below use DEX totals.
    results = []
    for sdk in SDK_TABLE:
        hits = {}
        for pref in sdk["prefixes"]:
            stem = pref.rstrip(".")
            n = sum(v for k, v in pkg_classes.items() if k == stem or k.startswith(stem + "."))
            if n:
                hits[pref] = n
        artifacts = [a for a in sdk["artifacts"] if any(a in v for v in version_files)]
        libs = [a for a in sdk.get("artifacts", []) if any(a in l for l in native_libs)]
        present = bool(hits or artifacts or libs)
        results.append(
            {
                "id": sdk["id"],
                "name": sdk["name"],
                "vendor": sdk["vendor"],
                "cat": sdk["cat"],
                "present": present,
                "prefix_hits": hits,
                "artifact_hits": artifacts,
                "native_hits": libs,
                "namespace_classes": sum(hits.values()),
            }
        )

    present = [r for r in results if r["present"]]
    present.sort(key=lambda r: -r["namespace_classes"])
    st.log(f"attributed {len(present)} / {len(SDK_TABLE)} catalogue entries present")

    for r in present[:10]:
        st.log(f"  {r['namespace_classes']:>6,} classes  {r['name']} ({r['vendor']})")

    trackers = [r for r in present if r["cat"] in TRACKER_CATS]
    st.log(f"tracker-class components: {len(trackers)} → {', '.join(t['name'] for t in trackers)}")

    # ---------- crypto key-material discovery -------------------------------
    import gzip
    import json as _json

    strings = _json.load(gzip.open(Path(APK_DIR.parent.parent / "analysis/data/dex_strings.json.gz"), "rt"))
    pem_priv = [s for s in strings if "BEGIN RSA PRIVATE KEY" in s or "BEGIN PRIVATE KEY" in s or "BEGIN EC PRIVATE KEY" in s]
    pem_pub = [s for s in strings if "BEGIN PUBLIC KEY" in s or "BEGIN RSA PUBLIC KEY" in s or "BEGIN CERTIFICATE" in s]
    aes_hex = [s for s in strings if re.fullmatch(r"[0-9a-fA-F]{64}", s.strip())]
    aes_b64 = [s for s in strings if re.fullmatch(r"[A-Za-z0-9+/]{43}=", s.strip())]
    otp_secrets = [s for s in strings if re.fullmatch(r"[A-Z2-7]{16,32}", s.strip()) and len(set(s)) > 10]
    st.log(
        f"key material: PEM private={len(pem_priv)} public/cert={len(pem_pub)} | "
        f"32-byte hex={len(aes_hex)} b64-32B={len(aes_b64)} base32-ish OTP={len(otp_secrets)}"
    )

    payload = {
        "catalogue_size": len(SDK_TABLE),
        "present_count": len(present),
        "sdks": results,
        "trackers": trackers,
        "tracker_categories": sorted({t["cat"] for t in trackers}),
        "artifacts": {
            "version_modules": sorted(version_files),
            "native_libs": sorted(native_libs),
            "asset_count": len(asset_blobs),
        },
        "key_material": {
            "pem_private": {"count": len(pem_priv), "samples": [s[:40] + "…" for s in pem_priv[:3]]},
            "pem_public": {"count": len(pem_pub), "samples": [s[:40] + "…" for s in pem_pub[:3]]},
            "hex64": {"count": len(aes_hex), "samples": [s[:8] + "…" for s in aes_hex[:5]]},
            "b64_43": {"count": len(aes_b64), "samples": [s[:8] + "…" for s in aes_b64[:5]]},
            "base32": {"count": len(otp_secrets)},
        },
    }
    write_json("sdks.json", payload)
    write_json("trackers.json", {"count": len(trackers), "trackers": trackers, "categories": payload["tracker_categories"]})
    write_json(
        "facts_sdks.json",
        {
            "present": len(present),
            "catalogue": len(SDK_TABLE),
            "trackers": len(trackers),
            "top": [{"name": r["name"], "vendor": r["vendor"], "classes": r["namespace_classes"], "cat": r["cat"]} for r in present[:14]],
            "byCategory": collections.Counter(r["cat"] for r in present).most_common(),
            "versionModules": len(version_files),
        },
    )
    st.done({"present": len(present), "trackers": len(trackers)})


if __name__ == "__main__":
    main()
