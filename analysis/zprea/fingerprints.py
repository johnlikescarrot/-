"""Fingerprint databases: third-party SDK attribution and security-relevant API patterns.

Every entry is a *descriptive* fingerprint — a package-prefix or symbol-pattern
that identifies a capability. Nothing here is an exploit or a bypass; the output
is a bill-of-materials suitable for a supply-chain and privacy report.
"""

from __future__ import annotations

import re
from typing import Dict, List, Tuple

# --------------------------------------------------------------------------- SDK
# package prefix -> (vendor, category, evidence note)
SDK_FINGERPRINTS: List[Tuple[str, str, str, str]] = [
    ("com.google.firebase", "Google", "Analytics/Crash/Push", "Firebase suite"),
    ("com.google.android.gms", "Google", "Platform services", "Google Play services"),
    ("com.google.mlkit", "Google", "On-device ML", "ML Kit (OCR/text/barcode)"),
    ("com.google.android.play.core", "Google", "Platform services", "Play Core / app bundle"),
    ("com.android.billingclient", "Google", "Payments", "Play Billing"),
    ("com.google.zxing", "Google", "Barcode", "ZXing"),
    ("com.crashlytics", "Google", "Crash reporting", "Crashlytics"),
    ("com.google.firebase.crashlytics", "Google", "Crash reporting", "Crashlytics"),
    ("com.appsflyer", "AppsFlyer", "Attribution", "AppsFlyer SDK"),
    ("com.facebook", "Meta", "Ads/Social", "Facebook SDK incl. Audience Network"),
    ("com.adjust.sdk", "Adjust", "Attribution", "Adjust SDK"),
    ("com.applovin", "AppLovin", "Ads", "AppLovin mediation"),
    ("com.branch", "Branch", "Deep linking", "Branch.io"),
    ("com.braze", "Braze", "CRM/Push", "Braze"),
    ("com.airbnb.android", "Airbnb", "UI", "Lottie animations"),
    ("com.alipay", "Ant Group", "Payments/Mini-app", "Alipay lineage"),
    ("com.ant.", "Ant Group", "Mini-app container", "Ant mobile framework"),
    ("com.alibaba.ariver", "Ant Group", "Mini-app container", "Ariver mini-program runtime"),
    ("com.alibaba.fastjson", "Alibaba", "Serialization", "FastJSON"),
    ("com.alibaba.mpaas", "Ant Group", "mPaaS", "Ant mPaaS financial stack"),
    ("com.alibaba", "Alibaba", "Utility", "Alibaba OSS/utilities"),
    ("com.taobao", "Alibaba", "Utility", "Taobao/Alibaba networking"),
    ("mtopsdk", "Alibaba", "Networking", "MTOP gateway SDK"),
    ("anet.channel", "Alibaba", "Networking", "ANet channel SDK"),
    ("com.mpaas", "Ant Group", "mPaaS", "Ant mPaaS financial stack"),
    ("com.alipay.mobile.nebula", "Ant Group", "Mini-app", "Nebula/H5 container"),
    ("com.squareup.okhttp3", "Square", "Networking", "OkHttp"),
    ("com.squareup.okio", "Square", "IO", "Okio"),
    ("com.squareup.retrofit2", "Square", "Networking", "Retrofit"),
    ("com.squareup.picasso", "Square", "Images", "Picasso"),
    ("com.facebook.fresco", "Meta", "Images", "Fresco"),
    ("com.facebook.ads", "Meta", "Ads", "Audience Network (redex-obfuscated)"),
    ("com.facebook.react", "Meta", "Hybrid", "React Native"),
    ("app.rive", "Rive", "UI/Animation", "Rive runtime"),
    ("com.rive", "Rive", "UI/Animation", "Rive runtime"),
    ("com.airbnb.lottie", "Airbnb", "UI/Animation", "Lottie animations"),
    ("com.google.android.material", "Google", "UI", "Material Components"),
    ("com.google.android.datatransport", "Google", "Telemetry", "DataTransport"),
    ("com.google.android.odml", "Google", "On-device ML", "ODML image library"),
    ("com.squareup.moshi", "Square", "Serialization", "Moshi"),
    ("com.bumptech.glide", "Bumptech", "Images", "Glide"),
    ("io.reactivex", "ReactiveX", "Reactive", "RxJava"),
    ("io.sentry", "Sentry", "Crash reporting", "Sentry"),
    ("org.greenrobot.eventbus", "greenrobot", "IPC/events", "EventBus"),
    ("org.jetbrains.kotlin", "JetBrains", "Language runtime", "Kotlin stdlib"),
    ("kotlinx.", "JetBrains", "Language runtime", "Kotlin coroutines/serialization"),
    ("androidx.", "Google", "Platform services", "AndroidX"),
    ("com.zing.", "VNG", "Zalo platform", "Zalo/VNG internal"),
    ("vn.com.vng.zalo", "VNG", "Zalo platform", "Zalo SDK"),
    ("com.zalo.", "VNG", "Zalo platform", "Zalo SDK"),
    ("com.vng.", "VNG", "VNG platform", "VNG internal"),
    ("com.zing.zalopay", "VNG", "First-party", "ZaloPay first-party"),
    ("vn.com.vng.zalopay", "VNG", "First-party", "ZaloPay first-party"),
    ("com.visa", "Visa", "Payments", "Visa CBP / Visa SDK"),
    ("com.paypal", "PayPal", "Payments", "PayPal SDK"),
    ("org.webrtc", "Google", "RTC", "WebRTC"),
    ("com.naver", "Naver", "UI", "Naver libraries"),
    ("com.tencent", "Tencent", "Mixed", "Tencent SDK"),
    ("com.huawei", "Huawei", "Platform services", "HMS"),
    ("com.samsung", "Samsung", "Platform services", "Samsung SDK"),
    ("com.xiaomi", "Xiaomi", "Platform services", "Mi Push"),
    ("com.heytap", "OPPO", "Platform services", "OPPO/Heytap push"),
    ("com.vivo", "vivo", "Platform services", "vivo push"),
    ("com.meizu", "Meizu", "Platform services", "Meizu push"),
    ("org.tensorflow", "TensorFlow", "On-device ML", "TFLite runtime"),
    ("com.google.ar", "Google", "AR", "ARCore"),
    ("net.sqlcipher", "SQLCipher", "Storage crypto", "SQLCipher"),
    ("com.scottyab", "ScottyAB", "Security", "rootbeer / secure-preferences"),
    ("com.griver", "Ant Group", "Mini-app container", "Griver container"),
    ("com.iap", "Ant Group", "Mini-app", "IAP Connect"),
    ("org.apache.cordova", "Apache", "Hybrid", "Cordova"),
    ("com.reactnative", "Meta", "Hybrid", "React Native"),
    ("com.facebook.react", "Meta", "Hybrid", "React Native"),
    ("io.flutter", "Google", "Hybrid", "Flutter"),
    ("com.onesignal", "OneSignal", "Push", "OneSignal"),
    ("com.airwallex", "Airwallex", "Payments", "Airwallex"),
    ("com.stripe", "Stripe", "Payments", "Stripe"),
    ("io.jsonwebtoken", "Auth0", "Auth", "JJWT"),
    ("com.auth0", "Auth0", "Auth", "Auth0 Android"),
    ("com.google.crypto.tink", "Google", "Crypto", "Tink"),
    ("org.bouncycastle", "BouncyCastle", "Crypto", "BouncyCastle provider"),
    ("org.conscrypt", "Google", "Crypto/TLS", "Conscrypt"),
]


def attribute(dotted_or_descriptor: str) -> Tuple[str, str, str] | None:
    """Return (vendor, category, note) for the longest matching prefix."""
    d = dotted_or_descriptor
    if d.startswith("L") and d.endswith(";"):
        d = d[1:-1].replace("/", ".")
    best: Tuple[int, Tuple[str, str, str]] | None = None
    for prefix, vendor, cat, note in SDK_FINGERPRINTS:
        if d.startswith(prefix) and (best is None or len(prefix) > best[0]):
            best = (len(prefix), (vendor, cat, note))
    return best[1] if best else None


# ------------------------------------------------------------------ API patterns
# name -> compiled regex over "ClassDescriptor->methodName" or raw string
SECURITY_API_PATTERNS: Dict[str, str] = {
    # --- cryptography
    "crypto.cipher.getInstance": r"javax/crypto/Cipher;?->getInstance|javax\.crypto\.Cipher",
    "crypto.mac": r"javax/crypto/Mac;?->|javax\.crypto\.Mac",
    "crypto.messagedigest": r"java/security/MessageDigest;?->|java\.security\.MessageDigest",
    "crypto.keygenerator": r"javax/crypto/KeyGenerator;?->|javax\.crypto\.KeyGenerator",
    "crypto.keypairgenerator": r"javax/crypto/KeyPairGenerator|java\.security\.KeyPairGenerator",
    "crypto.secureRandom": r"java/security/SecureRandom|java\.security\.SecureRandom",
    "crypto.aes.ecb.mode": r"AES/ECB|AES/None|DES/ECB|RC4",
    "crypto.weak.algo": r"\bDES\b|\bDESede\b|\bMD5\b|\bSHA-?1\b|\bRC4\b|\bBlowfish\b",
    "crypto.tls.sslcontext": r"javax/net/ssl/SSLContext|javax\.net\.ssl\.SSLContext",
    "crypto.trustmanager.custom": r"X509TrustManager|checkServerTrusted",
    "crypto.hostname.verifier": r"HostnameVerifier|ALLOW_ALL_HOSTNAME",
    "crypto.keystore.android": r"AndroidKeyStore",
    "crypto.biometric": r"androidx/biometric|android/hardware/biometrics|BiometricPrompt",
    # --- storage
    "storage.sharedprefs": r"SharedPreferences|getSharedPreferences",
    "storage.sqlitedb": r"SQLiteDatabase|openOrCreateDatabase",
    "storage.external.write": r"getExternalStorage|Environment\.getExternal",
    # --- dynamic code loading
    "dyn.dexclassloader": r"DexClassLoader|PathClassLoader|InMemoryDexClassLoader",
    "dyn.dexfile": r"dalvik/system/DexFile|DexFile;->loadDex",
    "dyn.native.load": r"System;->loadLibrary|System;->load\b|Runtime\.getRuntime\(\)\.load",
    "dyn.reflection": r"java/lang/reflect/Method;?->invoke|java\.lang\.reflect\.Method",
    "dyn.classforname": r"Class;->forName|Class\.forName",
    # --- webview / hybrid
    "web.js_interface": r"addJavascriptInterface",
    "web.js_enabled": r"setJavaScriptEnabled",
    "web.allow_file_access": r"setAllowFileAccess|setAllowUniversalAccessFromFileURLs",
    "web.mixed_content": r"setMixedContentMode|MIXED_CONTENT_ALWAYS_ALLOW",
    "web.shouldoverrideurl": r"shouldOverrideUrlLoading",
    # --- IPC / components
    "ipc.pendingintent.mutable": r"PendingIntent;->get(Activity|Service|Broadcast)",
    "ipc.localbroadcast": r"LocalBroadcastManager|sendBroadcast",
    "ipc.contentprovider": r"ContentProvider;->|FileProvider",
    # --- device / identity
    "device.imei_phone": r"getDeviceId|getImei|getSubscriberId|READ_PHONE_STATE",
    "device.android_id": r"ANDROID_ID|Settings\$Secure",
    "device.advertising_id": r"AdvertisingIdClient|getAdvertisingIdInfo|ad_id",
    "device.installed_apps": r"getInstalledPackages|getInstalledApplications|QUERY_ALL_PACKAGES",
    "device.location": r"getLastKnownLocation|requestLocationUpdates|FusedLocationProvider",
    "device.contacts": r"ContactsContract|READ_CONTACTS",
    "device.camera": r"android/hardware/Camera|Camera2?API|CAMERA",
    "device.mic": r"MediaRecorder|AudioRecord|RECORD_AUDIO",
    "device.nfc": r"android/nfc/|NfcAdapter",
    "device.calendar": r"CalendarContract|WRITE_CALENDAR",
    # --- anti-analysis / hardening
    "hard.rootcheck": r"/system/xbin/su|/system/bin/su|com\.noshufou\.android\.su|Superuser\.apk|magisk",
    "hard.emulatorcheck": r"Build\.FINGERPRINT|generic/sdk_gphone|goldfish|ranchu|vbox86",
    "hard.debuggercheck": r"TracerPid|isDebuggerConnected|Debug;->isDebuggerConnected|ptrace",
    "hard.hookframework": r"frida|xposed|substrate|de\.robv|gum-js-loop|riru|zygisk",
    "hard.tampercheck": r"getPackageInfo|signatures|GET_SIGNATURES|Signature\[\]|checkSignature",
    "hard.screenshotdetect": r"FLAG_SECURE|DETECT_SCREEN_CAPTURE|WindowManager\.LayoutParams\.FLAG_SECURE",
    "hard.jailbreak_native": r"libc;?->ptrace|anti_debug|is_rooted",
    # --- network
    "net.cleartext": r"http://|usesCleartextTraffic",
    "net.proxy": r"Proxy\.NO_PROXY|setProxy",
}

COMPILED_SECURITY_PATTERNS = {k: re.compile(v) for k, v in SECURITY_API_PATTERNS.items()}

# Literal-substring twin of the table above. A multi-dex sample exposes ~1.2M
# method identifiers; running 50 regexes over each is ~60M matches. One combined
# alternation over literal needles needs a single pass per identifier instead.
SECURITY_NEEDLES: Dict[str, str] = {
    "javax/crypto/Cipher": "crypto.cipher.getInstance",
    "javax/crypto/Mac": "crypto.mac",
    "java/security/MessageDigest": "crypto.messagedigest",
    "javax/crypto/KeyGenerator": "crypto.keygenerator",
    "javax/crypto/KeyPairGenerator": "crypto.keypairgenerator",
    "java/security/KeyPairGenerator": "crypto.keypairgenerator",
    "java/security/SecureRandom": "crypto.secureRandom",
    "AES/ECB": "crypto.aes.ecb.mode",
    "DES/ECB": "crypto.aes.ecb.mode",
    "javax/net/ssl/SSLContext": "crypto.tls.sslcontext",
    "X509TrustManager": "crypto.trustmanager.custom",
    "checkServerTrusted": "crypto.trustmanager.custom",
    "HostnameVerifier": "crypto.hostname.verifier",
    "AndroidKeyStore": "crypto.keystore.android",
    "BiometricPrompt": "crypto.biometric",
    "SharedPreferences": "storage.sharedprefs",
    "SQLiteDatabase": "storage.sqlitedb",
    "getExternalStorage": "storage.external.write",
    "DexClassLoader": "dyn.dexclassloader",
    "InMemoryDexClassLoader": "dyn.dexclassloader",
    "dalvik/system/DexFile": "dyn.dexfile",
    "loadLibrary": "dyn.native.load",
    "java/lang/reflect/Method": "dyn.reflection",
    "forName": "dyn.classforname",
    "addJavascriptInterface": "web.js_interface",
    "setJavaScriptEnabled": "web.js_enabled",
    "setAllowUniversalAccessFromFileURLs": "web.allow_file_access",
    "setMixedContentMode": "web.mixed_content",
    "shouldOverrideUrlLoading": "web.shouldoverrideurl",
    "PendingIntent": "ipc.pendingintent.mutable",
    "sendBroadcast": "ipc.localbroadcast",
    "FileProvider": "ipc.contentprovider",
    "getSubscriberId": "device.imei_phone",
    "getDeviceId": "device.imei_phone",
    "getImei": "device.imei_phone",
    "ANDROID_ID": "device.android_id",
    "AdvertisingIdClient": "device.advertising_id",
    "getInstalledPackages": "device.installed_apps",
    "getInstalledApplications": "device.installed_apps",
    "getLastKnownLocation": "device.location",
    "requestLocationUpdates": "device.location",
    "ContactsContract": "device.contacts",
    "MediaRecorder": "device.mic",
    "AudioRecord": "device.mic",
    "NfcAdapter": "device.nfc",
    "CalendarContract": "device.calendar",
    "usesCleartextTraffic": "net.cleartext",
    "TracerPid": "hard.debuggercheck",
    "isDebuggerConnected": "hard.debuggercheck",
    "frida": "hard.hookframework",
    "xposed": "hard.hookframework",
    "substrate": "hard.hookframework",
    "magisk": "hard.rootcheck",
    "/system/xbin/su": "hard.rootcheck",
    "/system/bin/su": "hard.rootcheck",
    "Superuser.apk": "hard.rootcheck",
    "goldfish": "hard.emulatorcheck",
    "ranchu": "hard.emulatorcheck",
    "GET_SIGNATURES": "hard.tampercheck",
    "FLAG_SECURE": "hard.screenshotdetect",
    "ptrace": "hard.debuggercheck",
}

# One pass, longest needle first so `AES/ECB` is not shadowed by shorter matches.
NEEDLE_RE = re.compile(
    "|".join(re.escape(k) for k in sorted(SECURITY_NEEDLES, key=len, reverse=True))
)


def classify_identifier(text: str) -> List[str]:
    """Categories hit by a single class/method identifier (deduplicated)."""
    seen = []
    for m in NEEDLE_RE.finditer(text):
        cat = SECURITY_NEEDLES[m.group(0)]
        if cat not in seen:
            seen.append(cat)
    return seen

# ------------------------------------------------------------- secret detection
# These classify *potential* embedded credentials so they can be redacted.
SECRET_PATTERNS: Dict[str, str] = {
    "aws_access_key": r"\b(AKIA|ASIA)[0-9A-Z]{16}\b",
    "google_api_key": r"\bAIza[0-9A-Za-z\-_]{35}\b",
    "firebase_url": r"https://[a-z0-9-]+\.firebaseio\.com",
    "private_key_pem": r"-----BEGIN (?:RSA |EC |OPENSSH |DSA )?PRIVATE KEY-----",
    "jwt_token": r"\beyJ[A-Za-z0-9_\-]{10,}\.[A-Za-z0-9_\-]{10,}\.[A-Za-z0-9_\-]{5,}\b",
    "slack_token": r"\bxox[baprs]-[0-9A-Za-z-]{10,}",
    "generic_secret_kv": r"(?i)\b(api[_-]?key|apikey|secret[_-]?key|client[_-]?secret|access[_-]?token|password|passwd|pwd)\b\s*[:=]\s*[\"']?[A-Za-z0-9/+_\-]{12,}",
    "bearer_token": r"(?i)\bbearer\s+[A-Za-z0-9\-_.]{16,}",
}
COMPILED_SECRET_PATTERNS = {k: re.compile(v) for k, v in SECRET_PATTERNS.items()}

URL_RE = re.compile(r"https?://[A-Za-z0-9._~:/?#\[\]@!$&'()*+,;=%\-]{4,256}")
DOMAIN_RE = re.compile(r"^[A-Za-z0-9][A-Za-z0-9.\-]{1,253}$")
