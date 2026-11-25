"""
03_sbom.py — Software Bill of Materials reconstructed from *evidence in the APK*.

Sources, in decreasing order of trust:
  1. `META-INF/<group>_<artifact>.version` — written by AGP for every AndroidX /
     Jetpack module, giving an exact semantic version.
  2. `META-INF/*.properties` (rxkotlin.properties) — full implementation
     metadata with origin URL and commit.
  3. `META-INF/com/android/build/gradle/app-metadata.properties` — pins the
     Android Gradle Plugin that produced the artifact (build-provenance).
  4. `META-INF/services/*` — Java SPI registrations; reveals which providers are
     wired in (e.g. `java.security.Provider` => a JCA provider is installed).
  5. DEX package presence — third-party SDKs identified by namespace, with a
     version inferred from embedded version strings when available.
  6. `assets/*.json` configuration — server endpoints and feature flags.

Outputs data/sbom.json + data/sbom.csv (CycloneDX-ish flat list, not valid C-DX).
"""
from __future__ import annotations

import collections
import csv
import glob
import json
import os
import re
import zipfile

DATA = "data"
VERSION_FILE = re.compile(r"^META-INF/(?P<coord>[A-Za-z0-9_.\-]+)\.version$")

# SDK namespaces observed in the DEX -> human identity + upstream project
DEX_SDK_MAP: dict[str, tuple[str, str, str]] = {
    "Lcom/google/android/gms/": ("Google Play Services", "Google LLC", "com.google.android.gms"),
    "Lcom/google/firebase/": ("Firebase", "Google LLC", "com.google.firebase"),
    "Lcom/google/mlkit/": ("ML Kit", "Google LLC", "com.google.mlkit"),
    "Lcom/google/android/material/": ("Material Components", "Google LLC", "com.google.android.material"),
    "Lcom/google/protobuf/": ("Protocol Buffers", "Google LLC", "com.google.protobuf"),
    "Lcom/google/android/exoplayer": ("ExoPlayer / Media3", "Google LLC", "androidx.media3"),
    "Lcom/google/gson/": ("Gson", "Google LLC", "com.google.code.gson"),
    "Lcom/alibaba/griver/": ("Griver mini-app container", "Alibaba Group", "com.alibaba.griver"),
    "Lcom/alibaba/ariver/": ("Ariver runtime", "Alibaba Group", "com.alibaba.ariver"),
    "Lcom/iap/ac/": ("IAPConnect (Alipay+)", "Ant Group", "com.alipay.iap"),
    "Lorg/bouncycastle/": ("Bouncy Castle", "Legion of the Bouncy Castle", "org.bouncycastle"),
    "Lcom/nimbusds/": ("Nimbus JOSE+JWT", "Connect2id", "com.nimbusds"),
    "Lcom/visa/cbp/": ("Visa Consumer Brand Payment SDK", "Visa Inc.", "com.visa.cbp"),
    "Lvn/ai/trueid": ("TrueID eKYC SDK", "VNG / TrueID", "vn.ai.trueid"),
    "Lvn/ai/faceauth": ("FaceAuth SDK (BShield)", "VNG", "vn.ai.faceauth"),
    "Lcom/vnpay": ("VNPAY taxi module", "VNPAY", "com.vnpay"),
    "Lcom/appsflyer/": ("AppsFlyer attribution", "AppsFlyer", "com.appsflyer"),
    "Lcom/facebook/": ("Meta Audience Network / Login", "Meta Platforms", "com.facebook.android"),
    "Lokhttp3/": ("OkHttp", "Square", "com.squareup.okhttp3"),
    "Lokio/": ("Okio", "Square", "com.squareup.okio"),
    "Lretrofit2/": ("Retrofit", "Square", "com.squareup.retrofit2"),
    "Lcom/squareup/moshi": ("Moshi", "Square", "com.squareup.moshi"),
    "Lcom/squareup/picasso": ("Picasso", "Square", "com.squareup.picasso"),
    "Lio/reactivex/": ("RxJava", "ReactiveX", "io.reactivex"),
    "Ldagger/": ("Dagger", "Google LLC", "com.google.dagger"),
    "Lcom/bumptech/glide": ("Glide", "Bumptech", "com.github.bumptech.glide"),
    "Lcom/airbnb/lottie": ("Lottie", "Airbnb", "com.airbnb.android:lottie"),
    "Lorg/tensorflow/": ("TensorFlow Lite", "Google LLC", "org.tensorflow"),
    "Lcom/scottyab/": ("RootBeer", "Scott Alexander-Brown", "com.scottyab:rootbeer"),
    "Lorg/chromium/": ("AndroidX WebKit boundary", "Google LLC", "androidx.webkit"),
    "Lio/grpc/": ("gRPC-Java", "gRPC authors", "io.grpc"),
    "Lcom/bugsnag": ("Bugsnag", "SmartBear", "com.bugsnag"),
    "Lio/sentry": ("Sentry", "Sentry.io", "io.sentry"),
    "Lcom/adjust": ("Adjust", "Adjust GmbH", "com.adjust.sdk"),
    "Lcom/tencent": ("Tencent SDK", "Tencent", "com.tencent"),
    "Lcom/huawei": ("Huawei HMS", "Huawei", "com.huawei.hms"),
    "Lorg/jetbrains/": ("JetBrains annotations", "JetBrains", "org.jetbrains"),
    "Lorg/apache/": ("Apache Commons", "Apache Software Foundation", "org.apache"),
    "Lcom/fasterxml": ("Jackson", "FasterXML", "com.fasterxml.jackson"),
    "Lcom/clevertap": ("CleverTap", "CleverTap", "com.clevertap"),
    "Lcom/braze": ("Braze", "Braze Inc.", "com.braze"),
    "Lcom/mixpanel": ("Mixpanel", "Mixpanel", "com.mixpanel"),
    "Lcom/amplitude": ("Amplitude", "Amplitude", "com.amplitude"),
    "Lio/realm": ("Realm DB", "MongoDB", "io.realm"),
    "Lcom/vng/": ("VNG internal", "VNG Corp.", "com.vng"),
    "Lvn/com/vng/zalopay": ("ZaloPay core", "VNG Corp.", "vn.com.vng.zalopay"),
    "Lcom/journeyapps/": ("ZXing Android Embedded", "JourneyApps", "com.journeyapps:zxing-android-embedded"),
    "Lcom/google/zxing/": ("ZXing", "ZXing authors", "com.google.zxing"),
    "Lid/barcode/": ("barcode scanning wrapper", "third-party", ""),
}


def main() -> None:
    apks = sorted(glob.glob("work/xapk/*.apk"))
    versions: list[dict] = []
    props: dict[str, dict] = {}
    spi: dict[str, list[str]] = {}
    build_meta: dict[str, str] = {}
    licenses: list[str] = []

    for apk in apks:
        with zipfile.ZipFile(apk) as z:
            for n in z.namelist():
                m = VERSION_FILE.match(n)
                if m:
                    coord = m.group("coord")
                    raw = z.read(n).decode("utf-8", "replace").strip()
                    versions.append({"split": os.path.basename(apk), "coord": coord, "version": raw,
                                     "size": len(raw)})
                elif n.endswith("app-metadata.properties"):
                    txt = z.read(n).decode("utf-8", "replace")
                    for line in txt.splitlines():
                        if "=" in line:
                            k, v = line.split("=", 1)
                            build_meta[k.strip()] = v.strip()
                elif n.endswith(".properties") and n.startswith("META-INF/") and n.count("/") == 1:
                    if n.count("/") == 1:
                        try:
                            txt = z.read(n).decode("utf-8", "replace")
                        except Exception:
                            continue
                        kv = {}
                        for line in txt.splitlines():
                            if "=" in line:
                                k, v = line.split("=", 1)
                                kv[k.strip()] = v.strip()
                        props[n] = kv
                elif n.startswith("META-INF/services/"):
                    try:
                        txt = z.read(n).decode("utf-8", "replace")
                    except Exception:
                        continue
                    impls = [ln.strip() for ln in txt.splitlines() if ln.strip() and not ln.startswith("#")]
                    spi[n.replace("META-INF/services/", "")] = impls
                elif "/LICENSE" in n or n.startswith("META-INF/LICENSE"):
                    licenses.append(n)

    # dedupe version rows (version files are duplicated in base for every split)
    uniq_versions: dict[str, str] = {}
    for v in versions:
        uniq_versions.setdefault(v["coord"], v["version"])

    dex = json.load(open(f"{DATA}/dex.json"))
    pkg3 = dict(dex.get("pkg_depth3_full") or {})
    pkg1 = dict(dex["pkg_depth1"])

    def namespace_classes(namespace: str) -> int:
        """Exact class count for a package path, e.g. 'com.adjust' -> com.adjust.*."""
        ns = namespace.lstrip("L").rstrip("/").replace("/", ".")
        if not ns:
            return 0
        if ns.count(".") >= 2:
            return pkg3.get(ns, 0)
        total = 0
        for k, v in pkg3.items():
            if k == ns or k.startswith(ns + "."):
                total += v
        return total

    sbom_rows: list[dict] = []
    for coord, ver in sorted(uniq_versions.items()):
        group, _, artifact = coord.partition("_")
        group = group.replace("_", ".")
        sbom_rows.append({
            "component": coord, "ecosystem": "maven", "group": group,
            "artifact": artifact, "version": ver, "evidence": "META-INF/*.version",
            "classes": 0, "confidence": "exact",
        })
    # non-version properties (rxkotlin etc.)
    for n, kv in props.items():
        title = kv.get("Implementation-Title")
        if not title:
            continue
        sbom_rows.append({
            "component": title, "ecosystem": "maven", "group": kv.get("Module-Source", ""),
            "artifact": title, "version": kv.get("Implementation-Version", ""),
            "evidence": n, "classes": 0, "confidence": "exact",
            "origin": kv.get("Module-Origin"), "built": kv.get("Build-Date"),
        })

    # third-party SDKs inferred from DEX namespaces
    for prefix, (name, vendor, coord) in DEX_SDK_MAP.items():
        n_classes = namespace_classes(prefix)
        if n_classes == 0:
            continue  # absent from this build: do not emit phantom components
        sbom_rows.append({
            "component": name, "ecosystem": "android-embedded", "group": vendor,
            "artifact": coord, "version": "", "evidence": "DEX namespace",
            "classes": n_classes, "confidence": "namespace",
        })

    sbom_rows.sort(key=lambda r: (-r["classes"], r["component"]))
    with open(f"{DATA}/sbom.csv", "w", newline="") as fh:
        w = csv.DictWriter(fh, fieldnames=["component", "ecosystem", "group", "artifact", "version",
                                           "evidence", "classes", "confidence"], extrasaction="ignore")
        w.writeheader()
        for r in sbom_rows:
            w.writerow(r)

    # ---- license / attribution census ------------------------------------ #
    license_files = collections.Counter()
    for n in licenses:
        if n.endswith("LICENSE.txt"):
            license_files["Apache-2.0 (AndroidX LICENSE.txt)"] += 1
        else:
            license_files[n] += 1

    out = {
        "counts": {
            "versioned_maven_components": len(uniq_versions),
            "property_components": len(props),
            "dex_inferred_sdks": sum(1 for r in sbom_rows if r["evidence"] == "DEX namespace"),
            "spi_registrations": len(spi),
            "license_files": len(licenses),
        },
        "build_provenance": build_meta,
        "jetpack": [{"coord": k, "version": v} for k, v in sorted(uniq_versions.items())],
        "jetpack_major_families": dict(collections.Counter(k.split("_")[0].split(".")[0]
                                                          for k in uniq_versions).most_common()),
        "properties": props,
        "spi": spi,
        "spi_security_providers": [v for k, v in spi.items() if "java.security" in k or "Provider" in k],
        "licenses": license_files.most_common(),
        "components": sbom_rows,
    }
    with open(f"{DATA}/sbom.json", "w") as fh:
        json.dump(out, fh, indent=1, ensure_ascii=False)

    print("[sbom] jetpack components:", len(uniq_versions))
    print("[sbom] build provenance:", build_meta)
    print("[sbom] SPI registrations:", len(spi), "security providers:", out["spi_security_providers"])
    print("[sbom] licenses:", license_files.most_common(6))
    top = [c for c in sbom_rows if c["evidence"] == "DEX namespace"][:12]
    for c in top:
        print(f"    {c['classes']:>6} cls  {c['component']:<34} {c['group']}")
    print("[sbom] -> data/sbom.json data/sbom.csv")


if __name__ == "__main__":
    main()
