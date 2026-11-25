#!/usr/bin/env python3
"""
Stage 2 — Multi-dex static analysis (the analytical core).

Parses all 19 classes*.dex of ZaloPay 11.17.0 and derives:
  * per-dex census (classes / methods / fields / strings / code units)
  * namespace tree + feature-module attribution
  * third-party SDK inventory with version fingerprints
  * string-derived intelligence: hosts, API paths, keys, pinning, crypto
  * security-control map (root / emulator / Frida / debugger / pinning / keystore)
  * obfuscation metrics (identifier entropy, single-letter rate)

All outputs are aggregates — no raw extracted payloads are committed.
Run:  python3 analysis/scripts/stage2_dex.py
"""
import os, re, json, sys, math, collections, logging, hashlib

logging.disable(logging.CRITICAL)
from loguru import logger as _lg
_lg.remove()
from androguard.core.dex import DEX

ROOT = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
DATA = os.path.join(ROOT, "data")
WORK = os.environ.get("ZPAY_WORK", "/home/user/work")
DEXDIR = os.path.join(WORK, "apk", "vn.com.vng.zalopay")
os.makedirs(DATA, exist_ok=True)

FIRST_PARTY = "vn.com.vng.zalopay"

# Known SDK fingerprints: (regex on class name, product, vendor)
SDK_SIGS = [
    (r"^com/google/firebase/|^com/google/android/gms/", "Google Firebase / Play Services", "Google"),
    (r"^com/google/android/material/", "Material Components", "Google"),
    (r"^androidx/", "AndroidX/Jetpack", "Google"),
    (r"^kotlin/|^kotlinx/coroutines/", "Kotlin stdlib & coroutines", "JetBrains"),
    (r"^okhttp3/|^okio/", "OkHttp / Okio", "Square"),
    (r"^retrofit2/", "Retrofit", "Square"),
    (r"^com/squareup/", "Square libraries (Moshi / LeakCanary / Wire)", "Square"),
    (r"^dagger/|^javax/inject/", "Dagger / javax.inject", "Google"),
    (r"^io/reactivex/|^rx/", "RxJava", "ReactiveX"),
    (r"^io/realm/", "Realm DB", "MongoDB"),
    (r"^com/facebook/", "Facebook SDK", "Meta"),
    (r"^com/zing/zalo/", "Zalo SDK / Zalo OAuth", "VNG"),
    (r"^com/alibaba/android/", "Alibaba Griver / mini-app container", "Alibaba"),
    (r"^com/alipay/", "Alipay SDK", "Ant Group"),
    (r"^com/tencent/|^com/tenpay/", "Tencent / Tenpay", "Tencent"),
    (r"^com/unionpay/|^com/chinamobile/", "UnionPay / China Mobile", "UnionPay"),
    (r"^com/vnpay/|^vn/vnpay/", "VNPAY", "VNPAY"),
    (r"^com/mo/", "MoMo (Vietnam)", "M-Service"),
    (r"^com/visa/|^com/mastercard/", "Visa / Mastercard", "Card networks"),
    (r"^com/fpt/|^vn/ai/trueid/|^com/fpt/fci/", "FPT.AI eKYC / trueID", "FPT"),

    (r"^com/firebase/|^com/cleartext/", "Firebase", "Google"),
    (r"^com/akamai/|^com/cloudflare/", "Akamai / Cloudflare", "CDN"),
    (r"^io/grpc/", "gRPC", "Google"),
    (r"^org/bouncycastle/|^com/google/crypto/|^org/conscrypt/", "BouncyCastle / Tink / Conscrypt", "Crypto"),
    (r"^io/github/", "Open-source (io.github.*)", "OSS"),
    (r"^com/google/mlkit/|^com/google/android/gms/vision/", "ML Kit (barcode/OCR/face)", "Google"),
    (r"^org/tensorflow/|^org/ncnn/", "TensorFlow Lite / NCNN runtime", "TF/OSS"),
    (r"^com/google/gson/|^org/json/", "Gson / org.json", "Google"),
    (r"^com/bumptech/glide/", "Glide image loader", "OSS"),
    (r"^coil/", "Coil image loader", "OSS"),
    (r"^de/hdodenhof/", "CircleImageView", "OSS"),
    (r"^org/timber/", "Timber logger", "OSS"),
    (r"^net/sqlcipher/|^net/zetetic/", "SQLCipher", "Zetetic"),
    (r"^com/karumi/dexter/", "Dexter permissions", "OSS"),
    (r"^lottie/|^com/airbnb/lottie/", "Lottie", "Airbnb"),
    (r"^com/bytedance/", "ByteDance SDK", "ByteDance"),
    (r"^com/huawei/", "Huawei HMS", "Huawei"),
    (r"^com/samsung/", "Samsung SDK", "Samsung"),
    (r"^com/appsflyer/|^com/adjust/|^com/facebook/appevents/", "Mobile attribution", "AppsFlyer/Adjust"),
    (r"^com/google/android/play/core/", "Play Core", "Google"),
    (r"^androidx/security/", "AndroidX Security (BiometricPrompt/Keystore)", "Google"),
    (r"^com/google/zxing/|^com/google/mlkit/vision/barcode/", "ZXing / MLKit barcode", "OSS/Google"),
    (r"^com/airbnb/", "Airbnb", "Airbnb"),
    (r"^org/apache/", "Apache Commons / HttpClient", "Apache"),
    (r"^com/nimbusds/|^com/auth0/jwt/", "JOSE/JWT stack", "OSS"),
    (r"^io/ktor/", "Ktor client", "JetBrains"),
    (r"^com/jakewharton/", "Retrofit/OkHttp extensions", "OSS"),
    (r"^org/slf4j/|^org/logback/", "Logging", "OSS"),
    (r"^com/google/protobuf/|^com/google/flatbuffers/|^com/squareup/wire/", "Serialization (proto/flatbuf/wire)", "Google/Square"),
    (r"^com/google/accompanist/", "Accompanist (Compose)", "Google"),
    (r"^androidx/compose/", "Jetpack Compose", "Google"),
    (r"^org/jetbrains/", "JetBrains annotations/compose compiler", "JetBrains"),
    (r"^com/microsoft/|^com/azure/", "Microsoft SDK", "Microsoft"),
    (r"^io/sentry/", "Sentry crash reporting", "Sentry"),
    (r"^com/bugsnag/", "Bugsnag", "SmartBear"),
    (r"^com/crashlytics/|^com/google/firebase/crashlytics/", "Crashlytics", "Google"),
    (r"^com/unity3d/", "Unity engine", "Unity"),
    (r"^com/bunny/|^io/branch/", "Branch deep links", "Branch"),
    (r"^com/scottyab/|^com/github/", "RootBeer/OSS (github)", "OSS"),
    (r"^com/secure/|^com/check/", "Security checks", "OSS"),
    (r"^com/baidu/|^com/amap/|^com/autonavi/", "Baidu/AMap", "Baidu/Alibaba"),
    (r"^org/bouncycastle/", "SpongyCastle", "BC"),
]

SECURITY_APIS = {
    "hashing/MAC": [r"^Ljavax/crypto/Mac;", r"^Ljava/security/MessageDigest;", r"HMAC", r"^Ljavax/crypto/spec/SecretKeySpec;"],
    "symmetric-cipher": [r"^Ljavax/crypto/Cipher;", r"^Ljavax/crypto/spec/IvParameterSpec;", r"^Ljavax/crypto/CipherInputStream;"],
    "asymmetric": [r"^Ljava/security/KeyPairGenerator;", r"^Ljava/security/Signature;", r"^Ljava/security/interfaces/RSAPublicKey;", r"^Ljava/security/spec/X509EncodedKeySpec;"],
    "keystore": [r"^Ljava/security/KeyStore;", r"^Landroid/security/keystore/KeyGenParameterSpec", r"^Landroid/security/keystore/KeyProperties"],
    "biometric": [r"BiometricPrompt", r"^Landroid/hardware/biometrics/", r"FingerprintManager"],
    "ssl-pinning": [r"CertificatePinner", r"^Ljavax/net/ssl/X509TrustManager;", r"TrustManagerFactory", r"pinSet|pinning|PublicKeyPinning"],
    "root-detection": [r"superuser", r"/system/xbin/su", r"^Lcom/scottyab/rootbeer/", r"isDeviceRooted|checkRoot|RootBeer", r"test-keys"],
    "emulator-detection": [r"goldfish", r"ranchu", r"^Landroid/os/Build;.*FINGERPRINT", r"isEmulator|checkEmulator"],
    "frida-detection": [r"frida", r"/proc/self/maps", r"27042"],
    "debugger-detection": [r"Debug\\.isDebuggerConnected|isDebuggerConnected", r"android/os/Debug"],
    "integrity": [r"SafetyNet|PlayIntegrity|IntegrityManager", r"signature.*verify|verifySignature", r"getPackageInfo.*GET_SIGNATURES"],
    "sqlcipher": [r"^Lnet/sqlcipher/"],
    "obfuscation": [r"^Lcom/tencent/|^Lcom/alibaba/android/jsbridge/"],
}

JUNK = re.compile(r"^(?:[A-Za-z0-9_$]{1,3}|[a-z]{1,2})$")


def shannon(s):
    if not s:
        return 0.0
    c = collections.Counter(s)
    n = len(s)
    return -sum((v / n) * math.log2(v / n) for v in c.values())


def human_type(desc):
    """Normalise a method descriptor to a coarse category."""
    if desc.startswith("("):
        return "method"
    return "field"


def main():
    dexes = sorted([f for f in os.listdir(DEXDIR) if re.fullmatch(r"classes\d*\.dex", f)],
                   key=lambda s: (int(re.sub(r"\D", "", s) or 1)))
    per_dex, all_strings, class_pkgs = [], collections.Counter(), collections.Counter()
    sdk_hits = collections.defaultdict(set)
    total = dict(classes=0, methods=0, fields=0, strings=0)
    name_scores, single_letter, class_names_sample = [], 0, 0
    sec = collections.Counter()
    sec_examples = collections.defaultdict(set)

    for i, fn in enumerate(dexes):
        p = os.path.join(DEXDIR, fn)
        d = DEX(open(p, "rb").read())
        cls = d.get_classes(); meth = d.get_methods(); fld = d.get_fields(); strs = d.get_strings()
        total["classes"] += len(cls); total["methods"] += len(meth)
        total["fields"] += len(fld); total["strings"] += len(strs)
        cnames = [c.get_name() for c in cls]
        for n in cnames:
            pkg = n.rsplit("/", 1)[0].replace("/", ".") if "/" in n else "(default)"
            class_pkgs[pkg] += 1
            simple = n.rsplit("/", 1)[-1].split("$")[0]
            if JUNK.match(simple) or JUNK.match(simple.lower()):
                single_letter += 1
            name_scores.append(shannon(simple))
        for s in strs:
            all_strings[s] += 1
        # SDK attribution (class-name based, first match wins)
        for n in cnames:
            nn = n.lstrip("L").rstrip(";")
            for rx, prod, vendor in SDK_SIGS:
                if re.search(rx, nn):
                    sdk_hits[f"{prod}§{vendor}"].add(fn)
                    break
        per_dex.append({
            "file": fn, "bytes": os.path.getsize(p),
            "classes": len(cls), "methods": len(meth), "fields": len(fld), "strings": len(strs),
            "code_bytes": getattr(d.header, "data_size", None),
            "checksum": getattr(d.header, "checksum", None),
            "dex_version": getattr(d.header, "dex_version", None),
        })
        print(f"  [{i+1:>2}/{len(dexes)}] {fn:<14} {len(cls):>6} cls {len(meth):>7} mth {len(strs):>7} str")

    # ── string intelligence ────────────────────────────────────────────────
    host_rx = re.compile(r"^(?:\*\.)?([a-z0-9](?:[a-z0-9-]{0,60})\.)+(?:vn|com|net|io|org|me|app|dev|cloud|gg|co|info|xyz|site|online|ai|so|ly|link|pro|asia|sg|jp|hk|id|th|my|ph|kr|tw|cn)$", re.I)
    url_rx = re.compile(r"https?://[^\s\"'<>\\)]{4,200}")
    hosts, urls, api_paths = collections.Counter(), collections.Counter(), collections.Counter()
    for s, n in all_strings.items():
        sl = s.strip()
        if len(sl) > 300 or " " in sl[:40]:
            pass
        for m in url_rx.finditer(sl):
            u = m.group(0).rstrip(".,);'\"")
            urls[u] += n
        # bare hostnames
        if 4 < len(sl) < 120 and "/" not in sl and " " not in sl and host_rx.match(sl):
            hosts[sl.lower()] += n
        for m in re.finditer(r'https?://([^/\s"\'<>]+)(/[^\s"\'<>\\)]*)', sl):
            h = m.group(1).lower()
            hosts[h] += n
            if m.group(2):
                api_paths[m.group(2)] += n
    # wildcard / suffix hosts
    dom_rollup = collections.Counter()
    for h, n in hosts.items():
        parts = h.split(".")
        dom_rollup[".".join(parts[-2:])] += n

    # ── security API usage ────────────────────────────────────────────────
    marker_blob = list(all_strings.keys())
    for cat, pats in SECURITY_APIS.items():
        for pat in pats:
            rx = re.compile(pat, re.I)
            hits = [s for s in marker_blob if rx.search(s)]
            if hits:
                sec[cat] += len(hits)
                for h in hits[:6]:
                    sec_examples[cat].add(h[:120])

    # ── interesting literals (keys, tokens, pins) ─────────────────────────
    interesting = []
    pats = {
        "google_api_key": r"AIza[0-9A-Za-z_\-]{35}",
        "firebase_url": r"https://[a-z0-9-]+\.firebaseio\.com",
        "gcm_sender": r"\b\d{12}\b",
        "sha256_pin": r"^[A-Za-z0-9+/]{43}=$",
        "jwt_like": r"eyJ[A-Za-z0-9_\-]{10,}\.[A-Za-z0-9_\-]{10,}",
        "private_key": r"-----BEGIN [A-Z ]*PRIVATE KEY-----",
        "aws_key": r"AKIA[0-9A-Z]{16}",
        "stripe_key": r"(?:sk|pk)_(?:live|test)_[0-9A-Za-z]{10,}",
        "supabase": r"https://[a-z0-9]+\.supabase\.co",
        "sentry_dsn": r"https://[0-9a-f]{32}@[a-z0-9.\-]+/\d+",
        "zalopay_api": r"zalopay\.vn[^\s\"']{0,60}",
    }
    for s in marker_blob:
        for k, rx in pats.items():
            if re.search(rx, s):
                interesting.append({"kind": k, "value": s[:160]})
    # dedupe
    seen = set(); interesting_d = []
    for it in interesting:
        k = (it["kind"], it["value"])
        if k in seen:
            continue
        seen.add(k); interesting_d.append(it)

    # ── attribution of top packages ───────────────────────────────────────
    top_pkgs = class_pkgs.most_common(60)
    grouped = collections.Counter()
    for pkg, n in class_pkgs.items():
        root2 = ".".join(pkg.split(".")[:2])
        grouped[root2] += n
    sdk_table = []
    for k, files in sdk_hits.items():
        prod, vendor = k.split("§")
        sdk_table.append({"product": prod, "vendor": vendor,
                          "dex_files": sorted(files, key=lambda x: (len(x), x))})
    sdk_table.sort(key=lambda x: x["product"])

    out = {
        "multidex": per_dex,
        "totals": total,
        "namespace_tree": [{"pkg": p, "classes": n} for p, n in top_pkgs],
        "namespace_rollup": [{"root": p, "classes": n} for p, n in grouped.most_common(40)],
        "sdk_inventory": sdk_table,
        "obfuscation": {
            "classes_total": total["classes"],
            "short_identifier_classes": single_letter,
            "short_identifier_ratio": round(single_letter / max(1, total["classes"]), 4),
            "mean_name_entropy_bits": round(sum(name_scores) / max(1, len(name_scores)), 3),
            "median_name_entropy_bits": round(sorted(name_scores)[len(name_scores) // 2], 3) if name_scores else 0,
            "first_party_prefix": FIRST_PARTY,
        },
        "security_control_markers": {k: {"hits": v, "examples": sorted(sec_examples[k])[:8]} for k, v in sec.items()},
        "hosts": [{"host": h, "count": n} for h, n in hosts.most_common(120)],
        "domain_rollup": [{"domain": d, "count": n} for d, n in dom_rollup.most_common(40)],
        "urls": [{"url": u, "count": n} for u, n in urls.most_common(80)],
        "api_paths": [{"path": p, "count": n} for p, n in api_paths.most_common(60)],
        "interesting_literals": interesting_d[:120],
        "unique_strings": len(all_strings),
    }
    with open(os.path.join(DATA, "03_dex.json"), "w") as f:
        json.dump(out, f, indent=1, ensure_ascii=False)
    print(f"[stage2] {total['classes']:,} classes | {total['methods']:,} methods | "
          f"{total['strings']:,} strings | {len(hosts)} hosts | {len(sdk_table)} SDKs")


if __name__ == "__main__":
    main()
