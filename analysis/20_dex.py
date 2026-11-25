#!/usr/bin/env python3
"""
Stage 20 -- DEX layer: code mass, obfuscation index, SDK/third-party map and
sensitive-API surface.

Three independent lenses, deliberately kept separate so that a claim in the
report can always be traced to one of them:

1. *Structure*  -- class/method/field/string counts per `classesN.dex`, plus
   the identifier-shape statistics that quantify obfuscation without guessing.
2. *Lineage*    -- which dex carries which vendor namespace, and an SBOM built
   from `META-INF/*.version` + `*.properties` markers shipped by the build.
3. *Reachability surface* -- the union of external classes/methods referenced
   by the bytecode, matched against a curated catalogue of high-consequence
   platform APIs (telephony identifiers, reflection, dynamic code loading,
   cryptography, JNI, WebView bridges, clipboard/keyguard, root heuristics).
"""

from __future__ import annotations

import json
import logging
import re
from collections import Counter, defaultdict
from pathlib import Path

from androguard.core.dex import DEX

from common import UNPACKED, XAPK_DIR, banner, dump, human  # noqa: E402

logging.disable(logging.CRITICAL)
BASE = UNPACKED / "vn.com.vng.zalopay"

# --------------------------------------------------------------------------- #
# curated catalogue of high-consequence API surfaces
# --------------------------------------------------------------------------- #
API_CATALOGUE: dict[str, list[str]] = {
    "dynamic_code_loading": [
        "Ldalvik/system/DexClassLoader;", "Ldalvik/system/PathClassLoader;",
        "Ldalvik/system/InMemoryDexClassLoader;", "Ldalvik/system/DexFile;",
        "Ljava/lang/ClassLoader;", "Ldalvik/system/BaseDexClassLoader;",
    ],
    "reflection": [
        "Ljava/lang/reflect/Method;", "Ljava/lang/Class;",
        "Ljava/lang/reflect/Field;", "Ljava/lang/invoke/MethodHandle;",
    ],
    "crypto_symmetric": [
        "Ljavax/crypto/Cipher;", "Ljavax/crypto/spec/SecretKeySpec;",
        "Ljavax/crypto/spec/IvParameterSpec;", "Ljavax/crypto/KeyGenerator;",
        "Ljavax/crypto/spec/GCMParameterSpec;",
    ],
    "crypto_asymmetric": [
        "Ljava/security/KeyPairGenerator;", "Ljava/security/KeyFactory;",
        "Ljava/security/Signature;", "Ljava/security/interfaces/RSAPublicKey;",
        "Ljava/security/interfaces/ECPublicKey;",
    ],
    "crypto_digest": [
        "Ljava/security/MessageDigest;", "Ljavax/crypto/Mac;",
        "Ljava/security/SecureRandom;",
    ],
    "tls_pinning": [
        "Lokhttp3/CertificatePinner;", "Lokhttp3/CertificatePinner$Builder;",
        "Ljavax/net/ssl/X509TrustManager;", "Ljavax/net/ssl/SSLSocketFactory;",
        "Landroid/net/http/HttpResponseCache;",
    ],
    "webauthn_biometric": [
        "Landroidx/biometric/BiometricPrompt;", "Landroid/hardware/biometrics/BiometricPrompt;",
        "Landroid/hardware/fingerprint/FingerprintManager;",
        "Landroid/security/keystore/KeyGenParameterSpec;",
        "Ljava/security/KeyStore;",
        "Lcom/google/android/gms/auth/api/phone/SmsRetriever;",
    ],
    "device_identifiers": [
        "Landroid/telephony/TelephonyManager;", "Landroid/provider/Settings$Secure;",
        "Landroid/os/Build;", "Landroid/net/wifi/WifiInfo;",
        "Lcom/google/android/gms/ads/identifier/AdvertisingIdClient;",
        "Landroid/provider/MediaStore;",
    ],
    "location": ["Landroid/location/LocationManager;", "Lcom/google/android/gms/location/FusedLocationProviderClient;"],
    "contacts_calendar": ["Landroid/provider/ContactsContract;", "Landroid/provider/CalendarContract;"],
    "screen_capture_guard": ["Landroid/view/WindowManager$LayoutParams;", "Landroid/app/Activity;"],
    "webview": ["Landroid/webkit/WebView;", "Landroid/webkit/WebViewClient;",
                "Landroid/webkit/JavascriptInterface;", "Landroid/webkit/WebChromeClient;"],
    "clipboard": ["Landroid/content/ClipboardManager;", "Landroid/text/ClipboardManager;"],
    "root_and_tamper": ["Ljava/lang/Runtime;", "Ljava/lang/ProcessBuilder;",
                        "Ljava/io/File;"],
    "jni": ["Ljava/lang/System;"],
    "okhttp": ["Lokhttp3/OkHttpClient;", "Lokhttp3/Interceptor;", "Lokhttp3/Request$Builder;"],
    "sqlcipher": ["Lnet/sqlcipher/database/SQLiteDatabase;", "Lnet/sqlcipher/sqlite3/SQLiteDatabase;"],
    "code_push": ["Lcom/google/firebase/remoteconfig/FirebaseRemoteConfig;",
                  "Lcom/tencent/bugly/", "Lcom/google/firebase/crashlytics/FirebaseCrashlytics;"],
    "ml_media": ["Lorg/tensorflow/lite/Interpreter;", "Lcom/google/mlkit/",
                 "Lcom/tencent/ncnn/", "Lcom/google/mediapipe/"],
}

# vendor namespaces -> label (longest-prefix wins)
VENDORS: list[tuple[str, str]] = [
    ("Lvn/com/vng/zalopay/", "ZaloPay first-party"),
    ("Lvn/com/vng/", "VNG group"),
    ("Lvn/zalopay/", "ZaloPay (legacy pkg)"),
    ("Lcom/vng/", "VNG shared"),
    ("Lcom/google/firebase/", "Firebase"),
    ("Lcom/google/android/gms/", "Google Play services"),
    ("Lcom/google/android/material/", "Material Components"),
    ("Lcom/google/android/play/", "Play Core"),
    ("Lcom/google/mlkit/", "ML Kit"),
    ("Lcom/google/gson/", "Gson"),
    ("Lcom/google/protobuf/", "Protobuf"),
    ("Lcom/google/common/", "Guava"),
    ("Lcom/google/", "Google (other)"),
    ("Landroidx/", "AndroidX"),
    ("Landroid/support/", "Support library (legacy)"),
    ("Lkotlin", "Kotlin stdlib"),
    ("Lkotlinx/", "kotlinx"),
    ("Lokhttp3/", "OkHttp"),
    ("Lokio/", "Okio"),
    ("Lretrofit2/", "Retrofit"),
    ("Lcom/facebook/", "Meta SDK"),
    ("Lcom/appsflyer/", "AppsFlyer"),
    ("Lcom/adjust/sdk/", "Adjust"),
    ("Lcom/samsung/", "Samsung SDK"),
    ("Lio/reactivex/", "RxJava"),
    ("Ldagger/", "Dagger"),
    ("Ljavax/inject/", "javax.inject"),
    ("Lorg/jetbrains/", "JetBrains"),
    ("Lorg/json/", "org.json"),
    ("Lorg/bouncycastle/", "BouncyCastle"),
    ("Lcom/squareup/", "Square (Moshi/Picasso)"),
    ("Lcom/bumptech/glide/", "Glide"),
    ("Lcom/airbnb/lottie/", "Lottie"),
    ("Lcom/alibaba/", "Alibaba (Griver/Accs)"),
    ("Lcom/alipay/", "Alipay SDK"),
    ("Lcom/tencent/", "Tencent (Bugly/X5)"),
    ("Lcom/huawei/", "Huawei HMS"),
    ("Lcom/fasterxml/", "Jackson"),
    ("Lio/grpc/", "gRPC"),
    ("Lcom/apollographql/", "Apollo GraphQL"),
    ("Lcom/stripe/", "Stripe"),
    ("Lcom/visa/", "Visa SDK"),
    ("Lcom/mastercard/", "Mastercard SDK"),
    ("Lcom/napster/", "Napster (eKYC)"),
    ("Lcom/fpt/", "FPT eKYC"),
    ("Lvn/ai/trueid/", "TrueID (VN eKYC)"),
    ("Lio/realm/", "Realm"),
    ("Lcom/scottyab/", "RootBeer (root detection)"),
    ("Lde/", "German-namespace libs"),
    ("Lcom/clevertap/", "CleverTap"),
    ("Lcom/braze/", "Braze"),
    ("Lcom/mixpanel/", "Mixpanel"),
    ("Lcom/amplitude/", "Amplitude"),
    ("Lio/branch/", "Branch"),
    ("Lcom/applovin/", "AppLovin"),
    ("Lcom/unity3d/", "Unity"),
    ("Lcom/shopify/", "Shopify"),
]

URL_RE = re.compile(r"(?i)\b(https?|wss?)://([a-z0-9.\-]+\.[a-z]{2,24})(:[0-9]{1,5})?([^\s\"'<>\\)\\]*)")
HOST_RE = re.compile(r"^[a-z0-9](?:[a-z0-9\-]{0,61}[a-z0-9])?(?:\.[a-z0-9](?:[a-z0-9\-]{0,61}[a-z0-9])?)*\.[a-z]{2,12}$")
REAL_TLDS = {"vn","com","net","org","io","co","me","app","dev","cloud","sg","asia","info",
             "xyz","ai","ly","tv","cc","site","us","uk","de","cn","jp","kr","edu","gov",
             "tech","online","store","pro","mobi","biz","one","page","link","top","vip",
             "work","live","space","fun","shop","icu","club","digital","id","my","th"}
JAVA_ROOT = {"com","org","net","vn","android","java","javax","kotlin","kotlinx","io",
             "cn","de","me","co","tv","sg","ai","ly","cc","us","id","my","th","jp","kr"}
SECOND_LEVEL = {"com.vn","com.sg","co.uk","com.my","co.id","com.au","org.vn","net.vn",
                "edu.vn","gov.vn","com.vn"}


def registrable(host: str) -> str:
    parts = host.split(".")
    if len(parts) >= 3 and ".".join(parts[-2:]) in SECOND_LEVEL:
        return ".".join(parts[-3:])
    return ".".join(parts[-2:])


def java_class_like(host: str) -> bool:
    labels = host.split(".")
    if any(l[:1].isupper() for l in labels):
        return True
    if labels[0] in JAVA_ROOT and len(labels) <= 4:
        # com.facebook.app / vn.com.vn style descriptor debris
        tail = labels[-1]
        return tail in JAVA_ROOT or len(tail) <= 3
    return False


def simple_class_name(desc: str) -> str:
    """Lcom/foo/Bar$Inner; -> Bar$Inner"""
    s = desc.strip("L;")
    return s.rsplit("/", 1)[-1]


def is_obfuscated(name: str) -> bool:
    base = name.rsplit("/", 1)[-1].split("$")[0]
    if not base:
        return False
    if len(base) <= 2:
        return True
    # single-char classes packed under single-letter packages
    return False


def vendor_of(desc: str) -> str:
    for prefix, label in VENDORS:
        if desc.startswith(prefix):
            return label
    head = desc[1:].split("/")[0]
    return "unattributed/obfuscated" if len(head) <= 3 else f"other:{head}"


def main() -> None:
    banner("Stage 20 · DEX layer")
    dex_files = sorted(BASE.glob("classes*.dex"),
                       key=lambda p: (len(p.stem), p.stem))
    per_dex, sdk_matrix, class_counter = [], defaultdict(Counter), Counter()
    total_strings, total_classes, total_methods, total_fields = 0, 0, 0, 0
    obf_classes = obf_nonalpha = 0
    api_hits = Counter()
    api_evidence = defaultdict(lambda: defaultdict(set))  # cat -> class -> example
    strings_all: list[str] = []
    ext_method_refs = Counter()
    pkg_hist = Counter()

    for path in dex_files:
        d = DEX(path.read_bytes())
        n_cls, n_m, n_f, n_s = len(d.get_classes()), len(d.get_methods()), \
            len(d.get_fields()), len(d.get_strings())
        total_classes += n_cls
        total_methods += n_m
        total_fields += n_f
        total_strings += n_s

        vc = Counter()
        obf = 0
        for c in d.get_classes():
            name = c.get_name()
            vc[vendor_of(name)] += 1
            class_counter[name] += 1
            simple = simple_class_name(name)
            if is_obfuscated(name):
                obf += 1
            if any(ord(ch) > 127 for ch in name):
                obf_nonalpha += 1
            pkg_hist["/".join(name[1:].split("/")[:3])] += 1
        obf_classes += obf
        for v, k in vc.items():
            sdk_matrix[v][path.stem] = k

        # referencing classes (method_ids) -> API surface
        for m in d.get_methods():
            try:
                cn = m.get_class_name()
            except Exception:
                continue
            ext_method_refs[cn.rsplit(";", 1)[0] + ";"] += 1

        for s in d.get_strings():
            txt = s if isinstance(s, str) else s.decode("utf-8", "replace")
            strings_all.append(txt)

        per_dex.append({
            "dex": path.stem, "bytes": path.stat().st_size,
            "classes": n_cls, "methods": n_m, "fields": n_f, "strings": n_s,
            "obfuscated_classes": obf,
            "obfuscated_share": round(obf / n_cls, 3) if n_cls else 0,
            "vendors": dict(vc.most_common(8)),
            "dominant_vendor": vc.most_common(1)[0][0] if vc else None,
        })

    # ---- API surface --------------------------------------------------------
    for cn, refs in ext_method_refs.items():
        for cat, prefixes in API_CATALOGUE.items():
            if any(cn.startswith(p) for p in prefixes):
                api_hits[cat] += refs
                if len(api_evidence[cat][cn]) < 3:
                    api_evidence[cat][cn].add(refs)

    # ---- strings: endpoints, crypto smells, secrets -------------------------
    url_hosts, url_samples, bare_hosts = Counter(), {}, Counter()
    schemes = Counter()
    for st in strings_all:
        if len(st) > 400 or "//" not in st and "." not in st:
            continue
        for m in URL_RE.finditer(st):
            scheme, host, port, rest = m.group(1), m.group(2), m.group(3), m.group(4)
            host = host.lower()
            if java_class_like(host) or host.split(".")[-1] not in REAL_TLDS:
                continue
            url_hosts[host] += 1
            schemes[f"{scheme.lower()}://"] += 1
            url_samples.setdefault(host, (scheme + "://" + host + (port or "") +
                                          re.sub(r"[?&][^?&/]*", "?…", rest or ""))[:120])
        if "/" not in st and " " not in st and ";" not in st and len(st) < 90:
            low = st.lower()
            if HOST_RE.match(low) and not java_class_like(low) \
                    and low.split(".")[-1] in REAL_TLDS:
                bare_hosts[low] += 1

    hosts = Counter(url_hosts)
    for h, c in bare_hosts.items():
        hosts[h] += c
    families = Counter()
    for h, c in hosts.items():
        families[registrable(h)] += c
    first_party = {"zalopay.vn", "zalopay.com.vn", "vng.com.vn", "zalo.me", "zing.vn",
                   "zalopay.me", "zpa.vn"}

    crypto_markers = Counter()
    marker_patterns = {
        "AES/GCM/NoPadding": "AEAD (AES-GCM)", "AES/CBC/PKCS5Padding": "AES-CBC",
        "AES/CBC/PKCS7Padding": "AES-CBC", "RSA/ECB/PKCS1Padding": "RSA-PKCS1",
        "RSA/ECB/OAEPWithSHA-256AndMGF1Padding": "RSA-OAEP-256",
        "HmacSHA256": "HMAC-SHA256", "HmacSHA512": "HMAC-SHA512",
        "SHA-256": "SHA-256", "SHA-1": "SHA-1", "MD5": "MD5",
        "PBKDF2WithHmacSHA1": "PBKDF2", "PBKDF2WithHmacSHA256": "PBKDF2-256",
        "EC/P-256": "ECDSA P-256", "secp256r1": "ECDSA P-256",
        "BEGIN PUBLIC KEY": "PEM public key literal",
        "BEGIN RSA PRIVATE KEY": "PEM RSA private key literal",
        "BEGIN CERTIFICATE": "PEM certificate literal",
        "CertificatePinner": "OkHttp pinning",
        "ssl_pin": "pinning config",
        "X-Client-Key": "custom client key header",
        "deviceId": "device identifier",
        "root": "root heuristic",
        "magisk": "Magisk detection",
        "frida": "Frida detection",
        "xposed": "Xposed detection",
    }
    for s in strings_all:
        for needle, label in marker_patterns.items():
            if needle in s:
                crypto_markers[label] += 1

    # ---- SBOM from META-INF markers ----------------------------------------
    sbom = []
    seen = set()
    for vf in sorted((BASE / "META-INF").glob("*.version")):
        group_artifact = vf.name[:-len(".version")]
        ver = vf.read_text(errors="replace").strip()
        if ver and (group_artifact, ver) not in seen:
            seen.add((group_artifact, ver))
            sbom.append({"artifact": group_artifact, "version": ver})
    props = []
    for pf in sorted(BASE.glob("*.properties")):
        txt = pf.read_text(errors="replace")[:400].strip()
        props.append({"file": pf.name, "bytes": pf.stat().st_size, "head": txt})

    doc = {
        "totals": {
            "dex_files": len(dex_files),
            "bytes": sum(p["bytes"] for p in per_dex),
            "classes": total_classes,
            "methods": total_methods,
            "fields": total_fields,
            "strings": total_strings,
            "external_classes_referenced": len(ext_method_refs),
            "external_method_refs": sum(ext_method_refs.values()),
            "obfuscated_classes": obf_classes,
            "obfuscated_share": round(obf_classes / total_classes, 4) if total_classes else 0,
            "non_ascii_identifiers": obf_nonalpha,
        },
        "per_dex": per_dex,
        "vendor_matrix": {k: dict(v) for k, v in
                          sorted(sdk_matrix.items(), key=lambda kv: -sum(kv[1].values()))},
        "vendor_totals": sorted(
            ({"vendor": k, "classes": sum(v.values()),
              "dexes": len(v)} for k, v in sdk_matrix.items()),
            key=lambda r: -r["classes"]),
        "api_surface": {
            "categories": {k: {"refs": api_hits.get(k, 0),
                               "classes": sorted(api_evidence.get(k, {}).keys())[:8]}
                           for k in API_CATALOGUE},
            "total_refs": sum(api_hits.values()),
        },
        "endpoints": {
            "distinct_hosts": len(hosts),
            "distinct_url_hosts": len(url_hosts),
            "top_hosts": [{"host": h, "hits": c, "sample": url_samples.get(h, "")}
                          for h, c in hosts.most_common(50)],
            "families": [{"family": f, "hits": c,
                          "first_party": f in first_party}
                         for f, c in families.most_common(40)],
            "first_party_share": round(
                sum(c for f, c in families.items() if f in first_party) /
                max(1, sum(families.values())), 4),
            "schemes": dict(schemes),
            "http_plaintext_urls": sum(1 for h, smp in url_samples.items()
                                       if smp.startswith("http://")),
        },
        "crypto_markers": dict(crypto_markers.most_common(40)),
        "sbom": {"libraries": sbom, "properties_markers": props},
        "top_packages": [{"package": p, "classes": c} for p, c in pkg_hist.most_common(40)],
    }
    dump("20_dex.json", doc)

    print(f"  dex={len(dex_files)} classes={total_classes:,} methods={total_methods:,} "
          f"strings={total_strings:,} ({human(doc['totals']['bytes'])})")
    print(f"  obfuscated classes: {obf_classes:,} ({doc['totals']['obfuscated_share']*100:.1f}%)")
    print(f"  external classes referenced: {len(ext_method_refs):,}")
    print(f"  vendors (top): {[(r['vendor'], r['classes']) for r in doc['vendor_totals'][:8]]}")
    print(f"  distinct hosts in string pool: {len(hosts):,} "
          f"(url-hosts {len(url_hosts)})  first-party share "
          f"{doc['endpoints']['first_party_share']*100:.1f}%")
    print(f"  API surface refs: {doc['api_surface']['total_refs']:,} "
          f"{[(k, v['refs']) for k, v in doc['api_surface']['categories'].items() if v['refs']]}")


if __name__ == "__main__":
    main()
