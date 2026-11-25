"""
02_dex.py — multi-DEX structural intelligence for all 20 `*.dex` in the bundle.

Produces data/dex.json:
  * per-dex header integrity (adler32 + SHA-1), sizes, table counts
  * whole-program class/field/method census with package histograms at depth 1-4
  * source-file evidence (Kotlin/Java file names survive R8 unless `-renamesourcefileattribute`)
  * superclass roots and framework-vs-vendor split
  * obfuscation metrics: single-letter package share, synthetic share, inner-class share
  * security-relevant class inventory (crypto, network, ekyc, payment, webview bridges)
  * literal-pool mining for indicator families (TLS pinning, root detection, gRPC, protobuf)

Run:  python re/02_dex.py [--limit N]
"""
from __future__ import annotations

import collections
import glob
import json
import os
import re
import sys
import time

sys.path.insert(0, os.path.dirname(os.path.abspath(__file__)))
from dex import DexFile, DexError  # noqa: E402

DATA = "data"

# --- package ownership map: who wrote the code ----------------------------- #
VENDOR_PREFIXES: list[tuple[str, str]] = [
    ("Lvn/com/vng/", "ZaloPay (first-party)"),
    ("Lvn/com/zalopay", "ZaloPay (first-party)"),
    ("Lcom/vng/", "VNG group"),
    ("Lcom/google/", "Google (AndroidX/GMS/Firebase)"),
    ("Landroid/", "Android platform"),
    ("Landroidx/", "AndroidX"),
    ("Lkotlin", "Kotlin stdlib"),
    ("Lkotlinx/", "KotlinX"),
    ("Lcom/alibaba/", "Alibaba (Griver/ARiver)"),
    ("Lcom/alipay/", "Alipay"),
    ("Lcom/iap/", "Alipay IAPConnect"),
    ("Lorg/bouncycastle/", "Bouncy Castle"),
    ("Lcom/nimbusds/", "Nimbus JOSE+JWT"),
    ("Lcom/visa/", "Visa CBP"),
    ("Lvn/ai/trueid", "TrueID eKYC"),
    ("Lcom/vnpay", "VNPAY"),
    ("Lcom/appsflyer/", "AppsFlyer"),
    ("Lcom/facebook/", "Meta SDK"),
    ("Lokhttp3/", "OkHttp"),
    ("Lokio/", "Okio"),
    ("Lretrofit2/", "Retrofit"),
    ("Lio/reactivex/", "RxJava"),
    ("Ldagger/", "Dagger"),
    ("Lcom/squareup/", "Square (Moshi/OkHttp/LeakCanary)"),
    ("Lcom/bumptech/", "Glide"),
    ("Lcom/airbnb/", "Lottie"),
    ("Lcom/scottyab/", "RootBeer"),
    ("Lcom/scottyab/rootbeer", "RootBeer (root detection)"),
    ("Lorg/tensorflow/", "TensorFlow Lite"),
    ("Lcom/google/mlkit", "ML Kit"),
    ("Lcom/google/android/exoplayer", "ExoPlayer/Media3"),
    ("Lio/grpc/", "gRPC"),
    ("Lcom/google/protobuf/", "Protobuf"),
    ("Lorg/chromium/", "Chromium/WebView"),
    ("Lio/flutter/", "Flutter"),
    ("Lcom/bugsnag", "Bugsnag"),
    ("Lio/sentry", "Sentry"),
    ("Lcom/newrelic", "New Relic"),
    ("Lcom/adjust", "Adjust"),
    ("Lcom/braze", "Braze"),
    ("Lcom/clevertap", "CleverTap"),
    ("Lcom/mixpanel", "Mixpanel"),
    ("Lcom/amplitude", "Amplitude"),
    ("Lio/realm", "Realm"),
    ("Lcom/tencent/", "Tencent"),
    ("Lcom/huawei", "Huawei HMS"),
    ("Lcom/samsung", "Samsung"),
    ("Lorg/jetbrains/", "JetBrains (Kotlin tooling)"),
    ("Ljavax/", "Java EE"),
    ("Ljava/", "Java SE"),
    ("Lorg/json", "JSON"),
    ("Lcom/fasterxml", "Jackson"),
    ("Lorg/apache/", "Apache Commons/HTTP"),
    ("Lcom/airbnb/lottie", "Lottie (animation)"),
    ("Lcom/google/android/material", "Material Components"),
    ("Lcom/google/gson", "Gson"),
    ("Lcom/google/firebase", "Firebase"),
    ("Lcom/google/android/gms", "Google Play Services"),
]

SECURITY_KEYWORDS = {
    "crypto": ("crypto", "cipher", "keypair", "keystore", "signature", "hmac", "aes", "rsa", "ecdsa", "sha256"),
    "tls_pinning": ("pinning", "pinmanager", "certificatepin", "trustmanager", "hostnameverifier", "sslpin"),
    "root_detection": ("rootbeer", "rootdetect", "isrooted", "superuser", "magisk"),
    "tamper": ("integrity", "tamper", "attest", "safetynet", "playintegrity", "bshield"),
    "obfuscation": ("obfusc", "stringfog", "javacrypt", "stringcrypt"),
    "biometric": ("biometric", "fingerprint", "liveness", "faceid", "selfie", "ekyc", "ocr"),
    "nfc": ("nfc", "apdu", "hce", "hostapdu", "emv"),
    "payments": ("cashier", "payment", "transfer", "wallet", "zalopay", "napas", "vietqr", "bnpl", "installment"),
    "miniapp": ("miniapp", "griver", "ariver", "h5app", "tinyapp", "appx"),
    "analytics": ("appsflier", "appsflyer", "firebase", "analytics", "attribution", "tracking"),
    "webview_bridge": ("jsbridge", "javascriptinterface", "webviewclient", "addjavascriptinterface"),
    "db_encryption": ("sqlcipher", "sqlitedatabase", "roomdatabase", "encrypteddatabase"),
    "device_id": ("deviceid", "imei", "androidid", "oaid", "idfa", "fingerprintid"),
    "qr": ("qrcode", "qrscan", "barcode", "zxing", "barhopper"),
}

INDICATORS = {
    "tls_pinning": re.compile(rb"(?i)(sha256/[A-Za-z0-9+/=]{20,}|pin-sha256|certificatePinner|OkHostnameVerifier|TrustKit)"),
    "root_checks": re.compile(rb"(?i)(/system/xbin/su|/system/bin/su|/system/app/Superuser|magisk|"
                              rb"ro\.debuggable|/su/bin|busybox|test-keys)"),
    "emulator_checks": re.compile(rb"(?i)(goldfish|ranchu|qemu|genymotion|bluestacks|nox|isEmulator|Build\.FINGERPRINT)"),
    "hook_checks": re.compile(rb"(?i)(frida-server|frida-agent|libfrida|XposedBridge|de\.robv\.android\.xposed|"
                              rb"TracerPid|/proc/self/maps|com\.saurik\.substrate|ptrace)"),
    "pinning_impl": re.compile(rb"(?i)(CertificatePinner|pin-sha256|SslPinning|ssl_pinning|TrustManagerImpl|"
                               rb"X509TrustManager|checkServerTrusted)"),
    "attestation": re.compile(rb"(?i)(SafetyNet|PlayIntegrity|IntegrityManager|attestation|BShield|bshield)"),
    "emulator_impl": re.compile(rb"(?i)(goldfish|ranchu|ro\.kernel\.qemu|ro\.hardware\.(goldfish|ranchu)|"
                                rb"Genymotion|isEmulator|emulatorDetect)"),
    "crypto_suites": re.compile(rb"(?i)(AES/GCM/NoPadding|AES/CBC/PKCS5Padding|RSA/ECB/PKCS1Padding|RSA/ECB/OAEPWithSHA-256|HmacSHA256|SHA-256|secp256r1|Ed25519)"),
    "grpc": re.compile(rb"(?i)(application/grpc|grpc-timeout|grpc-status)"),
    "graphql": re.compile(rb"(?i)(__schema|query\s*\{\s*viewer|graphql)"),
    "firebase": re.compile(rb"(?i)(firebase|fcm|gcm\.googleapis)"),
    "deep_links": re.compile(rb"(?i)(zalopay://[a-z0-9/_{}\-]{2,60})"),
    "deeplink_https": re.compile(rb"(?i)(https://onelink\.zalopay\.vn/[a-z0-9/\-_]{1,40})"),
}


def owner(cls_name: str) -> str:
    for pref, label in VENDOR_PREFIXES:
        if cls_name.startswith(pref):
            return label
    # single-letter / short random packages => R8-relocated vendor code
    body = cls_name.lstrip("L")
    first = body.split("/")[0] if "/" in body else body
    if len(first) <= 3:
        return "R8-obfuscated (relocated)"
    return "third-party (other)"


def short_pkg(pkg: str) -> str:
    return pkg.replace("/", ".")


def main() -> None:
    limit = None
    if "--limit" in sys.argv:
        limit = int(sys.argv[sys.argv.index("--limit") + 1])

    dex_paths = sorted(
        [p for p in glob.glob("work/base/*.dex")] + glob.glob("work/base/assets/*.dex"),
        key=lambda p: -os.path.getsize(p),
    )[:limit] if limit else sorted(
        [p for p in glob.glob("work/base/*.dex")] + glob.glob("work/base/assets/*.dex"),
        key=lambda p: -os.path.getsize(p),
    )
    print(f"[dex] {len(dex_paths)} dex files")

    per_dex: list[dict] = []
    pkg1 = collections.Counter()
    pkg2 = collections.Counter()
    pkg3 = collections.Counter()
    owners = collections.Counter()
    owner_classes: collections.Counter = collections.Counter()
    owner_methods: collections.Counter = collections.Counter()
    root_classes = collections.Counter()
    source_files = collections.Counter()
    source_ext = collections.Counter()
    totals = collections.Counter()
    security_classes: dict[str, list[str]] = {k: [] for k in SECURITY_KEYWORDS}
    security_hits = collections.Counter()
    native_classes: list[str] = []
    interfaces = enums = abstracts = synthetics = inner = 0
    interface_impl = collections.Counter()
    indicators: dict[str, list[str]] = {k: [] for k in INDICATORS}
    big_classes: list[tuple[int, str, str]] = []

    for path in dex_paths:
        t0 = time.time()
        name = os.path.basename(path)
        try:
            with DexFile(path) as d:
                st = d.stats()
                cls = d.classes()
                per_dex.append({
                    "file": name,
                    "size": st.size,
                    "version": st.version,
                    "sha1_ok": st.signature_ok,
                    "adler32_ok": st.checksum_ok,
                    "strings": st.n_strings,
                    "types": st.n_types,
                    "protos": st.n_protos,
                    "fields": st.n_fields,
                    "methods": st.n_methods,
                    "classes": st.n_classes,
                    "code_bytes": st.total_code_bytes,
                    "tries": st.total_tries,
                    "map_types": list(st.map_types),
                })
                for c in cls:
                    totals["classes"] += 1
                    totals["methods"] += c.methods
                    totals["fields"] += c.fields
                    totals["code_bytes"] += c.code_bytes
                    totals["native_methods"] += c.native_methods
                    totals["abstract_methods"] += c.abstract_methods
                    totals["tries"] += c.tries
                    o = owner(c.name)
                    owner_classes[o] += 1
                    owner_methods[o] += c.methods
                    body = c.name.lstrip("L")
                    parts = body.split("/")
                    if len(parts) >= 1:
                        pkg1[short_pkg(parts[0])] += 1
                    if len(parts) >= 2:
                        pkg2[short_pkg("/".join(parts[:2]))] += 1
                    if len(parts) >= 3:
                        pkg3[short_pkg("/".join(parts[:3]))] += 1
                    if c.superclass:
                        root_classes[c.superclass] += 1
                    for iface in c.interfaces:
                        interface_impl[iface] += 1
                    if c.source_file:
                        source_files[c.source_file] += 1
                        source_ext[os.path.splitext(c.source_file)[1] or "(none)"] += 1
                    if c.native_methods:
                        native_classes.append(c.name)
                    if c.code_bytes > 40_000:
                        big_classes.append((c.code_bytes, c.name, name))
                    low = c.name.lower()
                    for family, keys in SECURITY_KEYWORDS.items():
                        if any(k in low for k in keys):
                            security_hits[family] += 1
                            if len(security_classes[family]) < 200:
                                security_classes[family].append(c.name)
                # literal pool mining
                for s in d.strings:
                    if len(s) < 6 or len(s) > 400:
                        continue
                    b = s.encode("utf-8", "ignore")
                    for k, rx in INDICATORS.items():
                        if rx.search(b):
                            bucket = indicators[k]
                            if len(bucket) < 120 and s not in bucket:
                                bucket.append(s)
        except DexError as exc:
            print(f"[dex] SKIP {name}: {exc}")
            continue
        # interface/enum/synthetic counters need the class list again
        print(f"[dex] {name:<22} {per_dex[-1]['classes']:>7,} classes  "
              f"{per_dex[-1]['methods']:>8,} methods  {time.time()-t0:.1f}s")

    # second pass counters (cheap because classes() is cached per DexFile, but we
    # already closed the files; recompute from the stored ClassInfo lists)
    for path in dex_paths:
        with DexFile(path) as d:
            for c in d.classes():
                interfaces += c.is_interface
                enums += c.is_enum
                abstracts += c.abstract_methods > 0 and c.is_interface
                synthetics += c.synthetic
                inner += c.is_inner

    # Obfuscation metric. Bare 1-2 letter packages (`com`, `cn`, `io`, `me`, `wu`,
    # `ii`, ...) are legal real namespaces (com.example.foo puts "com" first), so
    # they are excluded; the discriminator is "whose root package is a
    # non-dictionary 1-3 char token". Comparing against a fixed vocabulary of the
    # 40 real TLDs/namespaces present in Java/Kotlin code keeps the metric honest.
    REAL_ROOTS = {"com", "org", "net", "java", "javax", "android", "androidx", "kotlin",
                  "kotlinx", "dalvik", "sun", "junit", "io", "me", "de", "fr", "uk", "co",
                  "edu", "gov", "mil", "libcore", "org2", "okhttp3", "okio", "retrofit2",
                  "dagger", "rx", "io2", "grpc", "protobuf", "reactivex", "bouncycastle"}
    nonframework = {k: v for k, v in pkg1.items() if k.split(".")[0] not in REAL_ROOTS}
    obf_single_letter = sum(v for k, v in nonframework.items() if len(k) <= 3)
    result = {
        "per_dex": per_dex,
        "totals": {
            **totals,
            "dex_files": len(per_dex),
            "dex_bytes": sum(p["size"] for p in per_dex),
            "interfaces": interfaces,
            "enums": enums,
            "abstract_class_ish": abstracts,
            "synthetic_classes": synthetics,
            "inner_classes": inner,
            "native_classes": len(native_classes),
            "all_sha1_ok": all(p["sha1_ok"] for p in per_dex),
            "all_adler32_ok": all(p["adler32_ok"] for p in per_dex),
        },
        "owners": [
            {"owner": k, "classes": v, "methods": owner_methods[k],
             "pct_classes": round(100 * v / totals["classes"], 2)}
            for k, v in owner_classes.most_common()
        ],
        "pkg_depth1": pkg1.most_common(60),
        "pkg_depth2": pkg2.most_common(60),
        "pkg_depth3": pkg3.most_common(60),
        "obfuscation": {
            "single_letter_pkg_classes": obf_single_letter,
            "single_letter_pkg_pct": round(100 * obf_single_letter / totals["classes"], 2),
            "nonframework_roots": collections.Counter(nonframework).most_common(40),
            "real_root_classes": sum(v for k, v in pkg1.items() if k.split(".")[0] in REAL_ROOTS),
            "synthetic_pct": round(100 * synthetics / totals["classes"], 2),
            "inner_class_pct": round(100 * inner / totals["classes"], 2),
            "note": "single-letter top-level packages are the R8 relocation pool; "
                    "their share is the best available static proxy for how much "
                    "first-party code was renamed.",
        },
        "superclass_roots": root_classes.most_common(40),
        "source_files": {
            "count": len(source_files),
            "extensions": source_ext.most_common(),
            "sample": source_files.most_common(120),
            "first_party_sample": [s for s, _ in source_files.most_common(400)
                                   if any(k in s for k in ("ZaloPay", "Zalopay", "Payment", "Cashier",
                                                           "Wallet", "Transfer", "EKYC", "Ekyc", "Face",
                                                           "Liveness", "MiniApp", "Griver"))][:80],
        },
        "security": {
            "family_class_counts": dict(security_hits),
            "examples": {k: v[:25] for k, v in security_classes.items()},
        },
        "indicators": {k: v[:80] for k, v in indicators.items()},
        "biggest_classes": [
            {"code_bytes": b, "class": c, "dex": d} for b, c, d in sorted(big_classes, reverse=True)[:30]
        ],
        "native_classes_sample": native_classes[:80],
    }
    os.makedirs(DATA, exist_ok=True)
    with open(f"{DATA}/dex.json", "w") as fh:
        json.dump(result, fh, indent=1, ensure_ascii=False)

    print("\n[dex] TOTALS:", dict(totals.most_common()))
    print("[dex] owners:")
    for o in result["owners"][:14]:
        print(f"    {o['pct_classes']:6.2f}%  {o['classes']:>7,} classes  {o['owner']}")
    print("[dex] single-letter pkg share: %.2f%%" % result["obfuscation"]["single_letter_pkg_pct"])
    print("[dex] source files seen:", len(source_files), source_ext.most_common(5))
    print("[dex] security families:", dict(security_hits))
    print("[dex] -> data/dex.json")


if __name__ == "__main__":
    main()
