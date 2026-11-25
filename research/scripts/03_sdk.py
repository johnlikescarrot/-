#!/usr/bin/env python3
"""Stage 3 — SDK fingerprinting by string signature + first-party endpoint map.

Method: for every DEX string constant we test a set of SDK signatures
(package prefixes and distinctive literals). A signature is "present" only
when we can show >=1 concrete string constant as evidence, so nothing here is
inferred from filenames alone.
"""
from __future__ import annotations

import json
import re
import sys
from collections import Counter, defaultdict
from pathlib import Path

sys.path.insert(0, str(Path(__file__).resolve().parent))
from dex_parser import DexFile  # noqa: E402

ROOT = Path(__file__).resolve().parents[2]
DATA = ROOT / "research" / "data"
DEXDIR = ROOT / "work" / "dex"

# (display name, vendor/category, [signatures])
RULES: list[tuple[str, str, list[str]]] = [
    ("Firebase / Google Play services", "Google · platform", ["com/google/firebase", "com/google/android/gms"]),
    ("ML Kit (barcode, OCR, face, object)", "Google · on-device ML", ["com/google/mlkit", "com/google/android/gms/vision"]),
    ("Google Play Integrity", "Google · anti-tamper", ["com/google/android/play/core/integrity", "StandardIntegrityManager"]),
    ("Google Play Billing", "Google · payments", ["com/android/billingclient"]),
    ("Google Play In-App Update / Review", "Google · lifecycle", ["com/google/android/play/core/review", "com/google/android/play/core/appupdate"]),
    ("AdMob / GMA", "Google · ads", ["com/google/android/gms/ads"]),
    ("Meta Audience Network", "Meta · ads", ["com/facebook/ads", "com/facebook/ads/audience_network"]),
    ("Facebook Login / Graph", "Meta · identity", ["com/facebook/login", "com/facebook/AccessToken"]),
    ("Facebook App Events", "Meta · analytics", ["com/facebook/appevents"]),
    ("AppsFlyer", "attribution", ["com/appsflyer"]),
    ("Adjust", "attribution", ["com/adjust/sdk"]),
    ("Branch", "attribution / deep links", ["io/branch/referral"]),
    ("TikTok / Pangle ads", "ByteDance · ads", ["com/tiktok/", "com/bytedance/sdk/openadsdk", "com/pangle"]),
    ("ironSource / LevelPlay", "ads mediation", ["com/ironsource"]),
    ("AppLovin MAX", "ads mediation", ["com/applovin"]),
    ("Unity Ads", "ads", ["com/unity3d/ads"]),
    ("InMobi", "ads", ["com/inmobi/ads"]),
    ("Vungle / Liftoff", "ads", ["com/vungle"]),
    ("Chartboost", "ads", ["com/chartboost"]),
    ("Mintegral", "ads", ["com/mbridge", "com/mintegral"]),
    ("OkHttp 4", "networking", ["okhttp3/OkHttpClient", "okhttp3/internal"]),
    ("Retrofit 2", "networking", ["retrofit2/Retrofit"]),
    ("Moshi / Gson", "serialization", ["com/squareup/moshi", "com/google/gson"]),
    ("Protocol Buffers", "serialization", ["com/google/protobuf"]),
    ("gRPC", "networking", ["io/grpc"]),
    ("RxJava 3", "async", ["io/reactivex/rxjava3"]),
    ("RxJava 2", "async", ["io/reactivex"]),
    ("Kotlin coroutines", "async", ["kotlinx/coroutines"]),
    ("Dagger / Hilt", "DI", ["dagger/hilt", "dagger/internal"]),
    ("Room", "persistence", ["androidx/room"]),
    ("SQLCipher", "persistence · encrypted", ["net/sqlcipher", "net/zetetic"]),
    ("SQLDelight / SQLite", "persistence", ["androidx/sqlite"]),
    ("DataStore (Preferences/Proto)", "persistence", ["androidx/datastore"]),
    ("MMKV", "persistence · KV", ["com/tencent/mmkv"]),
    ("WorkManager", "background", ["androidx/work"]),
    ("Navigation Compose", "UI", ["androidx/navigation"]),
    ("Jetpack Compose", "UI", ["androidx/compose"]),
    ("Lottie", "UI · animation", ["com/airbnb/lottie"]),
    ("Rive", "UI · animation", ["app/rive", "librive"]),
    ("Glide / Picasso", "UI · imaging", ["com/bumptech/glide", "com/squareup/picasso"]),
    ("CameraX", "media", ["androidx/camera"]),
    ("ExoPlayer / Media3", "media", ["androidx/media3", "com/google/android/exoplayer2"]),
    ("Accompanist / Coil", "UI · imaging", ["coil3", "coil/"]),
    ("Bouncy Castle", "crypto · library", ["org/bouncycastle"]),
    ("Tink", "crypto · library", ["com/google/crypto/tink"]),
    ("Conscrypt", "crypto · TLS", ["org/conscrypt"]),
    ("JWT (jjwt / nimbus)", "crypto · tokens", ["io/jsonwebtoken", "com/nimbusds"]),
    ("RootBeer", "security · root detection", ["com/scottyab/rootbeer"]),
    ("Play Integrity + SafetyNet legacy", "security", ["SafetyNet"]),
    ("TrueID eKYC", "KYC · VN", ["ai/trueid", "trueidlib", "com/trueid"]),
    ("TrueFace liveness", "KYC · liveness", ["trueface", "TrueFace"]),
    ("Alibaba mPaaS / ARiver (mini-app runtime)", "super-app runtime", ["com/alibaba/ariver", "com/alibaba/aliweex"]),
    ("Alipay SDK", "payments · CN", ["com/alipay/sdk", "com/alipay/android"]),
    ("Weex (Alibaba)", "UI runtime", ["com/taobao/weex"]),
    ("Crashlytics", "observability", ["com/google/firebase/crashlytics", "com/crashlytics"]),
    ("Sentry", "observability", ["io/sentry"]),
    ("Datadog RUM", "observability", ["com/datadog"]),
    ("New Relic", "observability", ["com/newrelic"]),
    ("Mixpanel", "product analytics", ["com/mixpanel"]),
    ("Amplitude", "product analytics", ["com/amplitude"]),
    ("CleverTap", "product analytics", ["com/clevertap"]),
    ("MoEngage", "product analytics", ["com/moengage"]),
    ("Google Analytics 4 (Firebase)", "product analytics", ["app-measurement.com", "google_app_measurement"]),
    ("OneSignal", "push", ["com/onesignal"]),
    ("Huawei HMS / Push", "push · OEM", ["com/huawei/hms", "com/huawei/agconnect"]),
    ("Samsung Push / IAP", "push · OEM", ["com/samsung/android/sdk"]),
    ("Xiaomi / OPPO / vivo push", "push · OEM", ["com/xiaomi/mipush", "com/color/os", "com/vivo/push"]),
    ("Zalo SDK (ZaloLogin / ZaloPay share)", "identity · VN", ["com/zing/zalo", "com/zing/vng"]),
    ("VNPAY QR / tokenization", "payments · VN", ["vnpay", "vn/anphat"]),
    ("Mastercard MDES", "card tokenization", ["com/mastercard"]),
    ("Visa SDK", "card tokenization", ["com/visa"]),
    ("Stripe", "payments · global", ["com/stripe"]),
    ("Adyen", "payments · global", ["com/adyen"]),
    ("PayPal / Braintree", "payments · global", ["com/braintreepayments", "com/paypal"]),
    ("card.io", "payments · card scan", ["io/card"]),
    ("Microblink (document scan)", "KYC · doc scan", ["com/microblink"]),
    ("Regula (document forensics)", "KYC · doc forensics", ["com/regula"]),
    ("Innovatrics (face)", "KYC · face", ["com/innovatrics"]),
    ("FaceTec", "KYC · liveness", ["com/facetec"]),
    ("PaddlePaddle Lite / PicoDet (NCNN)", "on-device ML", ["picodet", "paddle"]),
    ("TensorFlow Lite", "on-device ML", ["org/tensorflow/lite", "tflite"]),
    ("ONNX Runtime", "on-device ML", ["onnxruntime", "ai/onnxruntime"]),
    ("NCNN", "on-device ML", ["ncnn"]),
    ("NFC / HCE host card emulation", "payments · NFC", ["HostApduService", "android/nfc/cardemulation"]),
    ("FIDO / WebAuthn / passkeys", "identity · standard", ["fido", "webauthn", "CredentialManager"]),
    ("BiometricPrompt", "identity · local auth", ["androidx/biometric", "BiometricPrompt"]),
    ("Google Maps / Places", "maps", ["com/google/android/gms/maps", "com/google/android/libraries/places"]),
    ("Goong / Map4D (VN maps)", "maps · VN", ["goong", "map4d"]),
    ("jsoup (HTML parsing)", "data", ["org/jsoup"]),
    ("Apache Commons", "utility", ["org/apache/commons"]),
    ("Kotlin stdlib", "language", ["kotlin/jvm/internal", "kotlin/collections"]),
    ("AndroidX core", "platform", ["androidx/core"]),
]

FIRST_PARTY_HOST_RE = re.compile(r"(^|\.)(zalopay\.(com\.)?vn|zalo\.me|zapps\.vn|vng\.com\.vn)$")
THIRD_PARTY_HOST_HINTS = {
    "google": "Google", "gstatic": "Google", "facebook": "Meta", "fbcdn": "Meta",
    "appsflyer": "AppsFlyer", "adjust": "Adjust", "branch": "Branch", "tiktok": "ByteDance",
    "app-measurement": "Google Analytics", "crashlytics": "Crashlytics", "sentry": "Sentry",
    "alipay": "Alipay", "alibaba": "Alibaba", "alicdn": "Alibaba", "mmstat": "Alibaba",
    "fpt.ai": "FPT Smart Cloud (VN eKYC)", "trueid": "TrueID (VN eKYC)", "vnpay": "VNPAY",
    "marmot-cloud": "Marmot (Alibaba mini-program CDN)", "doubleclick": "Google Ads",
}


def domain_class(host: str) -> str:
    for key, label in THIRD_PARTY_HOST_HINTS.items():
        if key in host:
            return f"third-party · {label}"
    if FIRST_PARTY_HOST_RE.search(host) or host.endswith("zalopay.com.vn"):
        return "first-party · ZaloPay"
    return "unclassified"


def main() -> None:
    strings: list[str] = []
    for p in sorted(DEXDIR.glob("classes*.dex")):
        strings.extend(DexFile(p).strings)
    joined = "\n".join(strings)

    sdk_rows = []
    for name, vendor, sigs in RULES:
        evidence = [s for s in sigs if s in joined]
        if evidence:
            # count concrete string matches for each signature
            counts = {s: joined.count(s) for s in evidence}
            sdk_rows.append({
                "sdk": name, "vendor": vendor, "signatures": evidence,
                "evidence_strings": sum(counts.values()),
                "present": True, "confidence": "high" if any(k == 1 for k in counts) else "medium",
            })
    sdk_rows.sort(key=lambda r: -r["evidence_strings"])

    # endpoints
    url_re = re.compile(r"https?://[A-Za-z0-9._~:/?#\[\]@!$&'()*+,;=%-]{4,160}")
    host_re = re.compile(r"^https?://([A-Za-z0-9.\-]+)")
    hosts = Counter()
    for s in strings:
        for m in url_re.finditer(s):
            hm = host_re.match(m.group(0))
            if hm:
                hosts[hm.group(1).lower()] += 1
    domains = defaultdict(list)
    for host, n in hosts.most_common():
        domains[domain_class(host)].append({"host": host, "hits": n})

    paths = Counter()
    for s in strings:
        if re.fullmatch(r"/?(?:v\d{1,2}|api|oauth|graphql|sdk|gw)/[A-Za-z0-9_\-/{}.]{2,80}", s):
            paths[s] += 1

    out = {
        "sdks": sdk_rows,
        "sdk_count": len(sdk_rows),
        "endpoints": {
            "unique_hosts": len(hosts),
            "by_class": {k: v for k, v in sorted(domains.items(), key=lambda kv: -sum(x['hits'] for x in kv[1]))},
        },
        "api_paths": {"unique": len(paths), "top": paths.most_common(60)},
    }
    (DATA / "03_sdk.json").write_text(json.dumps(out, indent=1, ensure_ascii=False), encoding="utf-8")
    fp = out["endpoints"]["by_class"].get("first-party · ZaloPay", [])
    print(f"sdk: {len(sdk_rows)} SDKs fingerprinted | hosts {len(hosts)} | "
          f"first-party hosts {len(fp)} | api paths {len(paths)}")
    print("top SDKs:", ", ".join(r["sdk"] for r in sdk_rows[:12]))


if __name__ == "__main__":
    main()
