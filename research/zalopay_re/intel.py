"""Software-composition, asset and configuration intelligence.

Three complementary sources are fused here:

1. **Code** - the java package namespace of every DEX class, matched against a
   hand-curated vendor registry (``VENDOR_PREFIXES``).  This is how you tell
   *which* SDKs are inside without symbols, even after R8 obfuscation, because
   library namespaces are (almost) never renamed.
2. **Stamps** - ``META-INF/<group>_<artifact>.version`` files emitted by the
   Android Gradle Plugin, plus ``*.properties`` side-cars.  These are exact
   version attestations written at build time, so they double as an SBOM.
3. **Assets** - bundled models, JS bundles, fonts, JSON config and keys.  We
   fingerprint them, and for JSON we mine *key paths* and *hostnames* only
   (no secret values are ever written to disk).
"""

from __future__ import annotations

import hashlib
import io
import json
import os
import re
import zipfile
from collections import Counter, defaultdict
from typing import Dict, Iterable, List, Optional, Tuple

# ---------------------------------------------------------------------------
# vendor registry: prefix -> (vendor, category, note)
# ---------------------------------------------------------------------------
VENDOR_PREFIXES: Dict[str, Tuple[str, str, str]] = {
    "android.": ("AOSP / AndroidX", "platform", "framework + jetpack"),
    "androidx.": ("AndroidX (Google)", "framework", "Jetpack"),
    "com.google.android.material": ("Google", "ui", "Material Components"),
    "com.google.android.gms": ("Google Play services", "platform-sdk", "GMS core"),
    "com.google.android.play": ("Google Play Core", "platform-sdk", "in-app updates/review"),
    "com.google.firebase": ("Firebase", "backend", "crashlytics/analytics/remote-config"),
    "com.google.android.recaptcha": ("Google", "security", "reCAPTCHA enterprise"),
    "com.google.gson": ("Google", "serialization", "Gson"),
    "com.google.protobuf": ("Google", "serialization", "protobuf-javalite"),
    "com.google.common": ("Google", "utility", "Guava"),
    "com.google.mlkit": ("Google ML Kit", "on-device-ml", "vision/text/barcode"),
    "com.google.android.odml": ("Google", "on-device-ml", "ML runtime"),
    "com.google.zxing": ("ZXing", "qr", "barcode"),
    "com.google.android.exoplayer": ("Google", "media", "ExoPlayer"),
    "com.google.android.ads": ("Google", "advertising", "Mobile Ads"),
    "com.google.android.ump": ("Google", "advertising", "User Messaging Platform"),
    "com.google.android.engage": ("Google", "platform-sdk", "Engage SDK"),
    "com.google.ads.interactivemedia": ("Google", "advertising", "IMA"),
    "com.google.auto.value": ("Google", "codegen", ""),
    "com.google.crypto.tink": ("Google Tink", "crypto", "AEAD / keyset"),
    "com.facebook.": ("Meta", "sdk", "Facebook SDK / React Native"),
    "com.facebook.react": ("Meta", "runtime", "React Native"),
    "com.facebook.hermes": ("Meta", "runtime", "Hermes JS engine"),
    "com.facebook.soloader": ("Meta", "runtime", "native loader"),
    "com.facebook.animated": ("Meta", "ui", "Fresco animated GIF"),
    "com.facebook.fresco": ("Meta", "ui", "Fresco image pipeline"),
    "com.alibaba.ariver": ("Alibaba mPaaS", "miniapp-runtime", "Ariver JS container"),
    "com.alibaba.griver": ("Alibaba mPaaS", "miniapp-runtime", "Griver mini-program host"),
    "com.alibaba.fastjson": ("Alibaba", "serialization", "fastjson"),
    "com.alibaba.wireless": ("Alibaba", "network", "API gateway"),
    "com.alipay.": ("Ant Group", "payments", "Alipay SDK"),
    "com.taobao.": ("Alibaba", "sdk", ""),
    "com.bytedance.": ("ByteDance", "sdk", "attribution/ads"),
    "com.appsflyer": ("AppsFlyer", "attribution", "MMP"),
    "com.adjust.sdk": ("Adjust", "attribution", "MMP"),
    "com.branch.": ("Branch", "attribution", "deep links"),
    "io.branch.": ("Branch", "attribution", "deep links"),
    "com.onesignal": ("OneSignal", "push", ""),
    "com.clevertap": ("CleverTap", "analytics", ""),
    "com.mixpanel": ("Mixpanel", "analytics", ""),
    "com.amplitude": ("Amplitude", "analytics", ""),
    "com.segment.": ("Segment", "analytics", ""),
    "io.sentry": ("Sentry", "observability", "crash"),
    "com.bugsnag": ("Bugsnag", "observability", "crash"),
    "com.datadog": ("Datadog", "observability", ""),
    "com.newrelic": ("New Relic", "observability", ""),
    "com.microsoft.appcenter": ("Microsoft", "observability", "App Center"),
    "com.microsoft.": ("Microsoft", "sdk", ""),
    "com.huawei.": ("Huawei", "oem-sdk", "HMS push/maps"),
    "com.samsung.": ("Samsung", "oem-sdk", ""),
    "com.sec.android": ("Samsung", "oem-sdk", ""),
    "com.xiaomi.": ("Xiaomi", "oem-sdk", ""),
    "com.oppo.": ("OPPO", "oem-sdk", ""),
    "com.heytap.": ("OPPO/Realme", "oem-sdk", ""),
    "com.vivo.": ("vivo", "oem-sdk", ""),
    "com.zing.": ("VNG", "group", "Zalo ecosystem"),
    "com.vng.": ("VNG", "in-house", "publisher"),
    "vn.com.vng.": ("VNG / ZaloPay", "first-party", "the app itself"),
    "com.zalo.": ("VNG", "group", "Zalo"),
    "com.visa.": ("Visa", "card-network", "Visa Checkout / CBP"),
    "com.mastercard.": ("Mastercard", "card-network", ""),
    "com.napas.": ("NAPAS", "card-network", "Vietnam switch"),
    "okhttp3": ("Square", "network", "OkHttp"),
    "okio": ("Square", "network", "Okio"),
    "retrofit2": ("Square", "network", "Retrofit"),
    "com.squareup.": ("Square", "utility", ""),
    "io.reactivex": ("ReactiveX", "async", "RxJava"),
    "kotlin.": ("JetBrains", "language", "Kotlin stdlib"),
    "kotlinx.": ("JetBrains", "language", "coroutines/serialization"),
    "org.jetbrains": ("JetBrains", "tooling", ""),
    "dagger.": ("Google Dagger", "di", ""),
    "javax.inject": ("JSR-330", "di", ""),
    "org.koin": ("Koin", "di", ""),
    "org.bouncycastle": ("BouncyCastle", "crypto", ""),
    "org.conscrypt": ("Google Conscrypt", "crypto", "TLS provider"),
    "org.apache.commons": ("Apache", "utility", ""),
    "org.apache.http": ("Apache", "network", ""),
    "com.squareup.picasso": ("Square", "ui", ""),
    "io.coil-kt": ("Coil", "ui", "image loading"),
    "com.bumptech.glide": ("Bumptech", "ui", "Glide"),
    "com.airbnb.android.lottie": ("Airbnb", "ui", "Lottie"),
    "com.airbnb.lottie": ("Airbnb", "ui", "Lottie"),
    "org.tensorflow.lite": ("Google", "on-device-ml", "TFLite runtime"),
    "com.tencent.": ("Tencent", "sdk", ""),
    "tencent.": ("Tencent", "sdk", "ncnn"),
    "com.umeng.": ("Umeng", "analytics", ""),
    "com.tendcloud.": ("TalkingData", "analytics", ""),
    "com.appmetrica": ("Yandex AppMetrica", "analytics", ""),
    "io.grpc": ("gRPC", "network", ""),
    "org.json": ("JSON.org", "serialization", ""),
    "com.fasterxml.jackson": ("FasterXML", "serialization", ""),
    "com.jakewharton": ("Jake Wharton", "utility", "timber/butterknife"),
    "io.github": ("OSS", "utility", ""),
    "com.github": ("OSS", "utility", ""),
    "org.slf4j": ("SLF4J", "logging", ""),
    "io.flutter": ("Google", "runtime", "Flutter engine"),
    "org.chromium": ("Google", "runtime", "Chromium WebView glue"),
    "com.iab.": ("Google", "billing", "Play Billing library"),
    "com.android.billingclient": ("Google", "billing", "Play Billing"),
    "net.sqlcipher": ("Zetetic", "crypto", "SQLCipher"),
    "io.realm": ("MongoDB", "database", "Realm"),
    "androidx.room": ("Google", "database", "Room"),
    "androidx.security": ("Google", "crypto", "Jetpack Security / EncryptedPrefs"),
    "androidx.biometric": ("Google", "auth", "BiometricPrompt"),
    "androidx.work": ("Google", "background", "WorkManager"),
    "androidx.camera": ("Google", "media", "CameraX"),
    "androidx.media3": ("Google", "media", "Media3"),
    "androidx.compose": ("Google", "ui", "Jetpack Compose"),
    "androidx.navigation": ("Google", "ui", "Navigation"),
    "androidx.paging": ("Google", "data", "Paging3"),
    "androidx.datastore": ("Google", "storage", "DataStore"),
    "androidx.webkit": ("Google", "webview", "WebKit compat"),
    "androidx.profileinstaller": ("Google", "perf", "baseline profiles"),
    "com.scottyab.rootbeer": ("RootBeer", "security", "root detection"),
    "com.scottyab": ("OSS", "security", ""),
    "de.robv.android.xposed": ("Xposed", "instrumentation", ""),
    "com.saurik.substrate": ("Cydia Substrate", "instrumentation", ""),
    "com.guardsquare": ("Guardsquare", "hardening", "ProGuard/DexGuard"),
    "com.paypal.": ("PayPal", "payments", ""),
    "com.stripe.": ("Stripe", "payments", ""),
    "com.google.android.datatransport": ("Google", "telemetry", "Transport runtime"),
    "com.google.android.downloader": ("Google", "network", "Play downloader"),
    "com.google.android.renderscript": ("Google", "gpu", ""),
    "org.greenrobot": ("greenrobot", "utility", "EventBus/greenDAO"),
    "com.hbb20": ("OSS", "ui", "country code picker"),
    "com.airbnb.paris": ("Airbnb", "ui", ""),
    "com.yalantis": ("OSS", "ui", ""),
    "jp.wasabeef": ("OSS", "ui", "transformers"),
    "com.google.accompanist": ("Google", "ui", ""),
    "com.google.maps": ("Google", "maps", ""),
    "org.opencv": ("OpenCV", "vision", ""),
    "org.tensorflow": ("Google", "on-device-ml", ""),
    "ai.picovoice": ("Picovoice", "voice", ""),
    "com.vnpay.": ("VNPAY", "payments", ""),
    "com.momo.": ("MoMo", "payments", ""),
    "com.xq.": ("unknown", "unknown", "random-looking namespace - see findings"),
}

VENDOR_PREFIXES.update({
    "com.google": ("Google", "platform-sdk", "catch-all"),
    "com.google.android": ("Google", "platform-sdk", "AOSP glue / support libs"),
    "com.facebook": ("Meta", "sdk", "catch-all"),
    "com.alibaba": ("Alibaba", "sdk", "catch-all"),
    "com.alipay": ("Ant Group", "payments", "catch-all"),
    "com.visa": ("Visa", "card-network", "catch-all"),
    "com.iap": ("Ant Group", "payments", "Alipay+ catch-all"),
    "com.tiktok": ("ByteDance", "sdk", "TikTok"),
    "com.vnpay": ("VNPAY", "payments", "catch-all"),
    "org.junit": ("JUnit", "test", "test framework shipped by mistake"),
    "org.objectweb.asm": ("OW2", "tooling", "ASM bytecode lib"),
    "org.jacoco": ("JaCoCo", "tooling", "coverage runtime"),
    "javax.annotation": ("JSR-250", "annotations", ""),
    "com.example": ("sample code", "unknown", "Picodet sample package left in build"),
    "com.google.android.exoplayer2": ("Google", "media", "ExoPlayer 2"),
    "com.google.android.gms": ("Google Play services", "platform-sdk", "GMS"),
    "com.google.android.ads": ("Google", "advertising", "Mobile Ads"),
    "com.google.android.libraries.ads": ("Google", "advertising", "Ads SDK"),
    "com.google.android.finsky": ("Google Play", "platform-sdk", "store glue"),
    "com.google.android.libraries": ("Google", "platform-sdk", "libraries"),
    "com.google.ads": ("Google", "advertising", "legacy Ads"),
    "com.google.crypto": ("Google Tink", "crypto", "AEAD/keyset"),
    "com.google.auto": ("Google", "codegen", ""),
    "com.facebook.ads": ("Meta", "advertising", "Audience Network"),
    "com.facebook.appevents": ("Meta", "analytics", "App Events"),
    "com.facebook.internal": ("Meta", "sdk", "SDK core"),
    "com.visa.cbp": ("Visa", "card-network", "Checkout / CBP"),
    "com.visa.visapaysdk": ("Visa", "card-network", "VisaPay SDK"),
    "com.iap.ac": ("Ant Group", "payments", "Alipay+ / AC pay"),
    "com.iap.android": ("Ant Group", "payments", "Alipay+ wrapper"),
    "com.alipay.plus": ("Ant Group", "payments", "mPaaS payments"),
    "com.alipay.mobile": ("Ant Group", "payments", "Alipay wallet core"),
    "com.alipay.android": ("Ant Group", "payments", "Alipay SDK"),
    "com.alibaba.exthub": ("Alibaba mPaaS", "miniapp-runtime", "ext hub"),
    "com.alibaba.android": ("Alibaba", "ui", ""),
    "vn.ai.trueid": ("VNG TrueID", "eKYC", "ID document capture + liveness"),
    "vn.ai.faceauth": ("VNG", "eKYC", "face auth"),
    "vn.zalopay": ("VNG / ZaloPay", "first-party", "legacy namespace"),
    "vn.com.zalopay": ("VNG / ZaloPay", "first-party", ""),
    "com.nimbusds": ("Nimbus", "crypto", "JOSE / JWT"),
    "org.jmrtd": ("JMRTD", "eid", "ICAO 9303 ePassport reader"),
    "net.sf.scuba": ("SCUBA", "eid", "smart-card access"),
    "org.ejbca.cvc": ("EJBCA", "eid", "ePassport CV certificates"),
    "app.rive.runtime": ("Rive", "ui", "vector animation"),
    "org.jsoup": ("jsoup", "html", ""),
    "com.tiktok.appevents": ("ByteDance", "analytics", "TikTok App Events"),
    "com.fpt.fci": ("FPT.AI", "eKYC", "Vietnamese AI vendor"),
    "net.zetetic.database": ("Zetetic", "crypto", "SQLCipher"),
    "com.android.volley": ("Google", "network", "Volley"),
    "com.karumi.dexter": ("Karumi", "permissions", "Dexter"),
    "com.caverock.androidsvg": ("Caverock", "ui", "AndroidSVG"),
    "com.vnpay.vntaxi": ("VNPAY", "payments", "taxi module (shared code)"),
    "com.miui.referrer": ("Xiaomi", "attribution", "install referrer"),
    "com.android.installreferrer": ("Google", "attribution", "Install Referrer"),
    "com.google.android.installreferrer": ("Google", "attribution", "Install Referrer"),
    "org.bouncycastle": ("BouncyCastle", "crypto", "full JCE provider"),
    "io.reactivex.rxjava3": ("ReactiveX", "async", "RxJava3"),
    "com.squareup.okhttp3": ("Square", "network", "OkHttp"),
    "com.squareup.moshi": ("Square", "serialization", "Moshi"),
    "com.airbnb.android": ("Airbnb", "ui", ""),
    "com.github.bumptech.glide": ("Bumptech", "ui", "Glide"),
    "androidx.webkit": ("Google", "webview", "WebKit compat"),
})

SDK_HEURISTICS = {
    "miniapp": ("mPaaS/Ariver/Griver mini-program host", "com.alibaba.ariver|com.alibaba.griver|assets/Griver"),
    "rn": ("React Native / Hermes", "com.facebook.react|libhermes|libreact"),
    "flutter": ("Flutter", "io.flutter|flutter_assets"),
}


_VENDOR = {k.rstrip("."): v for k, v in VENDOR_PREFIXES.items()}


def vendor_for(pkg: str) -> Tuple[str, str, str]:
    """Longest dot-boundary prefix match against the vendor registry."""
    parts = pkg.split(".")
    best = None
    cand = ""
    for n in range(1, min(len(parts), 6) + 1):
        cand = parts[n - 1] if n == 1 else cand + "." + parts[n - 1]
        meta = _VENDOR.get(cand)
        if meta is not None:
            best = meta
    if best is None:
        best = GENERIC_PREFIXES.get(parts[0], ("unattributed", "unknown", ""))
    return best


_PREFIX_CACHE: Dict[str, Tuple[str, str, str]] = {}

# last-resort single-segment map: keeps R8-shrunk first-party code attributable
GENERIC_PREFIXES: Dict[str, Tuple[str, str, str]] = {
    "com": ("unattributed", "unknown", ""),
    "google": ("Google", "platform-sdk", ""),
    "org": ("unattributed", "unknown", ""),
    "androidx": ("AndroidX (Google)", "framework", ""),
    "kotlin": ("JetBrains", "language", "Kotlin"),
    "kotlinx": ("JetBrains", "language", "Kotlin coroutines/serialization"),
    "io": ("unattributed", "unknown", ""),
    "net": ("unattributed", "unknown", ""),
    "vn": ("unattributed", "unknown", ""),
    "vng": ("VNG", "in-house", ""),
}


def library_versions(zf: zipfile.ZipFile) -> dict:
    """META-INF/*.version + *.properties -> SBOM rows."""
    rows = []
    props = []
    for n in zf.namelist():
        if n.startswith("META-INF/") and n.endswith(".version"):
            name = n[len("META-INF/"):-len(".version")]
            try:
                ver = zf.read(n).decode("utf-8", "replace").strip()
            except Exception:
                ver = "?"
            group, _, artifact = name.partition("_")
            rows.append({"group": group or name, "artifact": artifact or name,
                         "version": ver, "file": n})
        elif n.endswith(".properties") and "/" not in n:
            try:
                body = zf.read(n).decode("utf-8", "replace")
            except Exception:
                continue
            props.append({"file": n, "lines": [l.strip() for l in body.splitlines() if l.strip()][:8]})
    rows.sort(key=lambda r: (r["group"], r["artifact"]))
    versions = Counter(r["version"] for r in rows)
    return {"libraries": rows, "count": len(rows),
            "distinct_groups": len({r["group"] for r in rows}),
            "version_histogram": dict(versions.most_common(30)),
            "properties": props}


# ---------------------------------------------------------------------------
# assets
# ---------------------------------------------------------------------------
ASSET_CLASSES = [
    (re.compile(r"\.tflite$"), "on-device-ml (TensorFlow Lite)"),
    (re.compile(r"\.ncnn\.(bin|param)$"), "on-device-ml (ncnn)"),
    (re.compile(r"\.(pt|onnx|mnn)$"), "on-device-ml (other)"),
    (re.compile(r"mlkit-.*(\.tflite|\.binarypb|\.labelmap)$"), "on-device-ml (ML Kit model)"),
    (re.compile(r"\.(js|bundle)$"), "javascript-bundle"),
    (re.compile(r"\.(ttf|otf|woff2?)$"), "font"),
    (re.compile(r"\.json$"), "configuration-json"),
    (re.compile(r"\.(yaml|yml)$"), "model-config-yaml"),
    (re.compile(r"\.(pem|crt|cer|p12|bks|jks|keystore)$"), "trust-material"),
    (re.compile(r"\.(txt|cfg|ini|xml)$"), "text-config"),
    (re.compile(r"\.(png|webp|jpg|jpeg|gif|svg)$"), "image"),
    (re.compile(r"\.(so|a)$"), "native"),
    (re.compile(r"\.(zip|jar|aar|apk|dex)$"), "nested-package"),
    (re.compile(r"\.(bin|dat|pak|blob)$"), "opaque-binary"),
]


def classify_asset(path: str) -> str:
    for rx, label in ASSET_CLASSES:
        if rx.search(path):
            return label
    return "other"


URL_RX = re.compile(r"https?://[A-Za-z0-9._~%:/?#\[\]@!$&'()*+,;=-]{4,200}")
HOST_RX = re.compile(r"\b(?:[a-z0-9](?:[a-z0-9-]{0,61}[a-z0-9])?\.)+(?:com|vn|net|io|org|sg|co|me|dev|cloud|app|xyz|info|biz|ai|tv)\b")

SECRET_KEY_RX = re.compile(r"(?i)(secret|token|password|passwd|api[_-]?key|private[_-]?key|client[_-]?secret|hmac|salt)")


def json_key_paths(obj, prefix: str = "", depth: int = 0, out: Optional[Counter] = None,
                   limit: int = 3000) -> Counter:
    if out is None:
        out = Counter()
    if depth > 5 or len(out) > limit:
        return out
    if isinstance(obj, dict):
        for k, v in list(obj.items())[:400]:
            p = f"{prefix}.{k}" if prefix else k
            out[p] += 1
            json_key_paths(v, p, depth + 1, out, limit)
    elif isinstance(obj, list):
        for v in obj[:20]:
            json_key_paths(v, prefix + "[]", depth + 1, out, limit)
    return out


def scan_json_strings(obj, urls: set, hosts: set, depth: int = 0) -> None:
    if depth > 6:
        return
    if isinstance(obj, dict):
        for k, v in list(obj.items())[:600]:
            if isinstance(v, str):
                urls.update(URL_RX.findall(v))
                hosts.update(HOST_RX.findall(v))
            else:
                scan_json_strings(v, urls, hosts, depth + 1)
    elif isinstance(obj, list):
        for v in obj[:200]:
            scan_json_strings(v, urls, hosts, depth + 1)


def analyze_assets(zf: zipfile.ZipFile, max_json: int = 6 << 20) -> dict:
    rows = []
    urls, hosts = set(), set()
    key_paths = Counter()
    model_shapes = []
    js_stats = []
    keys_found = []
    for info in zf.infolist():
        p = info.filename
        if not p.startswith("assets/") or info.is_dir():
            continue
        cls = classify_asset(p)
        row = {"path": p, "size": info.file_size, "class": cls}
        blob = b""
        if info.file_size <= max_json:
            blob = zf.read(info)
            row["sha256"] = hashlib.sha256(blob).hexdigest()
            row["entropy"] = round(_ent(blob), 3)
        rows.append(row)
        if cls == "configuration-json" and blob:
            try:
                obj = json.loads(blob.decode("utf-8", "replace"))
            except Exception:
                continue
            json_key_paths(obj, out=key_paths)
            scan_json_strings(obj, urls, hosts)
        elif cls == "javascript-bundle" and blob:
            text = blob.decode("utf-8", "replace")
            js_stats.append({"path": p, "size": len(blob),
                             "sha256": hashlib.sha256(blob).hexdigest(),
                             "urls": sorted(set(URL_RX.findall(text)))[:40],
                             "hosts": sorted(set(HOST_RX.findall(text)))[:40],
                             "lines": text.count("\n") + 1,
                             "has_eval": "eval(" in text,
                             "has_websocket": "WebSocket" in text})
            urls.update(URL_RX.findall(text))
            hosts.update(HOST_RX.findall(text))
        elif cls.startswith("on-device-ml") and blob:
            model_shapes.append({"path": p, "size": info.file_size,
                                 "magic": blob[:8].hex(),
                                 "is_tflite": blob[4:8] == b"TFL3"})
        elif cls in ("trust-material", "text-config") and blob and info.file_size < 20000:
            keys_found.append({"path": p, "size": info.file_size,
                               "sha256": hashlib.sha256(blob).hexdigest(),
                               "looks_pkcs8_pem": b"-----BEGIN" in blob,
                               "preview_redacted": (blob[:40].decode("utf-8", "replace")
                                                    .replace("\n", " ") + "…")})
    classes = Counter(r["class"] for r in rows)
    return {
        "entries": len(rows),
        "classes": dict(classes),
        "total_bytes": sum(r["size"] for r in rows),
        "rows": sorted(rows, key=lambda r: -r["size"])[:400],
        "hosts": sorted(hosts)[:300],
        "urls": sorted(urls)[:300],
        "json_key_paths": dict(key_paths.most_common(300)),
        "secret_shaped_keys": sorted({k for k in key_paths if SECRET_KEY_RX.search(k)})[:120],
        "models": sorted(model_shapes, key=lambda r: -r["size"])[:40],
        "js_bundles": js_stats[:20],
        "trust_material": keys_found[:60],
    }


def _ent(blob: bytes) -> float:
    from .elf import shannon_entropy
    return shannon_entropy(blob)


def package_census(kg: dict, class_names: Iterable[str]) -> dict:
    """Aggregate the DEX class list into vendor / namespace statistics."""
    vendor_classes = Counter()
    vendor_method_hint = Counter()
    namespaces = Counter()
    unattributed = Counter()
    for name in class_names:
        pkg = name.lstrip("L").rstrip(";")
        parts = pkg.split("/")
        if len(parts) < 2:
            unattributed["<default>"] += 1
            continue
        p2 = ".".join(parts[:2])
        p3 = ".".join(parts[:4])
        vendor, category, _note = vendor_for(p2 if vendor_for(p2)[0] != "unattributed" else p3)
        if vendor == "unattributed":
            unattributed[p3] += 1
        vendor_classes[vendor] += 1
        vendor_method_hint[category] += 1
        namespaces[p3] += 1
    return {
        "vendor_classes": dict(vendor_classes.most_common(60)),
        "category_classes": dict(vendor_method_hint.most_common(30)),
        "top_namespaces": dict(namespaces.most_common(60)),
        "unattributed_namespaces": dict(unattributed.most_common(40)),
    }


# ---------------------------------------------------------------------------
# host taxonomy
# ---------------------------------------------------------------------------
BANK_TOKENS = ("bidv", "vietin", "vpbank", "vietcapital", "techcombank", "mbbank", "acb",
               "sacombank", "tpbank", "agribank", "vib", "shb", "msb", "ocb", "seabank",
               "cake", "timo", "napas", "vnpay", "momo", "shinhan", "woori", "hsbc",
               "standardchartered", "uob", "cimb", "pvcombank", "eximbank", "lpbank",
               "namabank", "kienlongbank", "baovietbank", "scb", "abbank", "hdbank",
               "vietcombank", "vietinbank", "bvbank", "pgbank", "saigonbank", "coopbank")
CARD_TOKENS = ("visa", "mastercard", "maestro", "jcb", "unionpay", "americanexpress", "amex")
SOCIAL_TOKENS = ("facebook", "instagram", "tiktok", "zalo", "youtube", "twitter", "linkedin",
                 "telegram", "whatsapp", "snapchat")
CLOUD_TOKENS = ("googleapis", "gstatic", "googleusercontent", "cloudfront", "akamai",
                "aliyun", "alibaba", "amazonaws", "azureedge", "fastly", "cloudflare",
                "marmot-cloud", "appspot", "firebaseio", "edgevnpay")
ADS_TOKENS = ("doubleclick", "googleads", "googlesyndication", "appsflyer", "adjust",
              "unityads", "byteoversea", "pangle", "adnxs", "mopub", "ironsrc", "vungle",
              "applovin", "chartboost", "adcolony", "inner-active", "smartadserver")
OBS_TOKENS = ("crashlytics", "sentry", "bugsnag", "datadog", "newrelic", "instabug",
              "appcenter", "firebase", "app-measurement")


def classify_host(host: str) -> str:
    h = host.lower()
    if any(t in h for t in ("zalopay.vn", "zalopay.com.vn", "zalo.me", "zapps.vn", "vng.com.vn")):
        return "first-party (ZaloPay/VNG)"
    if any(t in h for t in BANK_TOKENS):
        return "banking / payment rails"
    if any(t in h for t in CARD_TOKENS):
        return "card network"
    if any(t in h for t in ADS_TOKENS):
        return "advertising / attribution"
    if any(t in h for t in OBS_TOKENS):
        return "telemetry / observability"
    if any(t in h for t in SOCIAL_TOKENS):
        return "social platform"
    if any(t in h for t in CLOUD_TOKENS):
        return "cloud / CDN"
    return "other third-party"


def host_taxonomy(hosts) -> dict:
    buckets: Dict[str, List[str]] = defaultdict(list)
    for h in sorted(set(hosts)):
        buckets[classify_host(h)].append(h)
    return {k: {"count": len(v), "hosts": v[:40]} for k, v in
            sorted(buckets.items(), key=lambda kv: -len(kv[1]))}
