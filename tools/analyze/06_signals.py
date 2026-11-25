#!/usr/bin/env python3
"""Static signal mining over the 243k unique DEX strings.

Signal families: TLS/pinning, trust-manager overrides, keystore/biometrics,
crypto primitives, integrity attestation, root/hook/emulator detection,
WebView & JSBridge surface, data-collection APIs, cleartext endpoints,
secret-shaped material (reported masked, never printed), domain inventory.
"""
from __future__ import annotations
import json, os, re
from collections import Counter, defaultdict

ROOT = os.path.dirname(os.path.dirname(os.path.dirname(os.path.abspath(__file__))))
DATA = os.path.join(ROOT, "data")

SIGNALS: dict[str, dict[str, list[str]]] = {
    "tls_pinning": {"markers": ["CertificatePinner", "sha256/", "sha1/", "TrustKit", "pinning",
                                "PublicKeyPinner", "pin-set", "pinSet"]},
    "trust_manager_overrides": {"markers": ["checkServerTrusted", "X509TrustManager", "TrustManagerImpl",
                                            "getAcceptedIssuers"]},
    "ssl_error_handling": {"markers": ["onReceivedSslError", "SslErrorHandler", "proceed()", "cancel()"],
                           "note": "presence alone is not a bypass; needs control-flow proof"},
    "keystore_hardware": {"markers": ["AndroidKeyStore", "KeyGenParameterSpec", "setUserAuthenticationRequired",
                                      "setIsStrongBoxBacked", "StrongBox", "KeyProperties", "keymaster", "TEE"]},
    "biometrics": {"markers": ["BiometricPrompt", "BiometricManager", "setAllowedAuthenticators",
                               "FingerprintManager", "CryptoObject"]},
    "crypto_primitives": {"markers": ["AES/GCM/NoPadding", "AES/CBC/PKCS5Padding", "AES/CBC/PKCS7Padding",
                                      "RSA/ECB/PKCS1Padding", "RSA/ECB/OAEPWithSHA-256AndMGF1Padding",
                                      "HmacSHA256", "HmacSHA512", "PBKDF2WithHmacSHA256", "AES/GCM",
                                      "SHA-256", "SHA-1", "MD5", "ECIES", "SM4", "SM2", "SM3", "ChaCha20"]},
    "integrity_attestation": {"markers": ["SafetyNet", "PlayIntegrity", "IntegrityManager", "Attestation",
                                          "requestIntegrityToken", "attest", "deviceCheck"]},
    "root_detection": {"markers": ["/system/bin/su", "/system/xbin/su", "superuser", "Superuser.apk",
                                   "test-keys", "busybox", "RootBeer", "isRooted", "ro.debuggable",
                                   "Magisk", "/sbin/su", "daemonsu"]},
    "hook_debug_detection": {"markers": ["frida", "xposed", "substrate", "TracerPid", "ptrace",
                                         "isDebuggerConnected", "waitForDebugger", "XposedBridge",
                                         "de.robv.android", "libhook"]},
    "emulator_detection": {"markers": ["goldfish", "qemu", "generic_x86", "Genymotion", "BlueStacks",
                                       "ro.kernel.qemu", "emulator", "sdk_gphone"]},
    "screenshot_protection": {"markers": ["FLAG_SECURE", "setFlags", "DETECT_SCREEN_CAPTURE",
                                          "setRecentsScreenshotEnabled"]},
    "overlay_abuse_controls": {"markers": ["TYPE_APPLICATION_OVERLAY", "canDrawOverlays", "SYSTEM_ALERT_WINDOW"]},
    "accessibility_surface": {"markers": ["AccessibilityService", "AccessibilityNodeInfo", "performAction"]},
    "webview_surface": {"markers": ["addJavascriptInterface", "setJavaScriptEnabled", "shouldOverrideUrlLoading",
                                    "onPageStarted", "WebViewClient", "WebChromeClient", "setAllowFileAccess",
                                    "setAllowUniversalAccessFromFileURLs", "evaluateJavascript"]},
    "jsbridge": {"markers": ["JSBridge", "jsbridge", "Griver", "postMessage", "webViewBridge", "@JavascriptInterface"]},
    "data_collection": {"markers": ["getDeviceId", "getImei", "getSubscriberId", "getSimSerialNumber",
                                    "getMacAddress", "getAccounts", "ContactsContract", "READ_SMS",
                                    "getLastKnownLocation", "ClipboardManager", "getInstalledPackages",
                                    "queryUsageStats", "getAdvertisingId"]},
    "payment_sensitive": {"markers": ["cvv", "cardNumber", "PAN", "expiryDate", "otp", "PIN", "CVV",
                                      "tokenization", "tokenize", "3DS", "EMV"]},
    "jwt_oauth": {"markers": ["Bearer ", "refresh_token", "access_token", "id_token", "JWT", "jose",
                              "openid", "oauth2", "PKCE"]},
    "hardening_vendor": {"markers": ["bshield", "Bshield", "libsheild", "taibmcf", "whitebox", "WhiteBox",
                                     "dexguard", "DexGuard", "guardsquare", "SecNeo", "jiagu"]},
    "anti_tamper_packaging": {"markers": ["getPackageInfo", "signatures", "GET_SIGNATURES", "PackageManager",
                                          "checkSignature", "signatureHash"]},
    "network_stack": {"markers": ["okhttp3", "OkHttpClient", "HttpURLConnection", "Cronet", "cronet",
                                  "quic", "HTTP/2", "h2", "tls1.3", "TLSv1.2", "TLSv1.3"]},
}

SECRET_PATTERNS = {
    "google_api_key": re.compile(r"AIza[0-9A-Za-z_\-]{35}"),
    "aws_access_key": re.compile(r"AKIA[0-9A-Z]{16}"),
    "private_key_block": re.compile(r"-----BEGIN (?:RSA |EC |PGP )?PRIVATE KEY-----"),
    "jwt_token": re.compile(r"eyJ[A-Za-z0-9_-]{10,}\.[A-Za-z0-9_-]{10,}\.[A-Za-z0-9_-]{6,}"),
    "firebase_url": re.compile(r"https://[a-z0-9-]+\.firebaseio\.com"),
    "slack_token": re.compile(r"xox[baprs]-[0-9A-Za-z-]{10,}"),
    "stripe_live": re.compile(r"sk_live_[0-9A-Za-z]{10,}"),
    "basic_auth_url": re.compile(r"[a-z][a-z0-9+.-]*://[^\s/:@]{2,}:([^\s/@]{3,})@", re.I),
}

DOMAIN_CLASSES = [
    ("ZaloPay first-party", re.compile(r"(^|\.)zalopay\.(vn|com\.vn)$")),
    ("ZaloPay CDN/static", re.compile(r"(^|\.)(simg|scdn|static|img)\.zalopay\.(vn|com\.vn)$")),
    ("VNG group", re.compile(r"(^|\.)(vng|zing|zalo)\.(vn|com|me)$")),
    ("Payment scheme / PSP", re.compile(r"(^|\.)(visa|mastercard|napas|alipay|wechat|unionpay|vnpay|momo)\.(com|vn|cn|com\.vn)$")),
    ("Google", re.compile(r"(^|\.)(google|googleapis|gstatic|googletagmanager|google-analytics|doubleclick|ggpht|youtube|android|firebase|crashlytics|app-measurement)\.(com|net|cn|co)$")),
    ("Facebook/Meta", re.compile(r"(^|\.)(facebook|fbcdn|fb\.me|messenger)\.(com|net)$")),
    ("Analytics/attribution", re.compile(r"(^|\.)(appsflyer|adjust|branch|clevertap|mixpanel|amplitude|amplitude|segment|bugsnag|sentry|onesignal|kochava|singular|smartlook|hotjar|cloudflareinsights)\.(com|io|net|me)$")),
    ("Ads", re.compile(r"(^|\.)(adcolony|applovin|unityads|ironsrc|inmobi|adnxs|mopub|vungle|chartboost|mintegral|pangle|bytedance|tiktok|snssdk)\.(com|net)")),
    ("Cloud infra", re.compile(r"(^|\.)(amazonaws|cloudfront|akamai|akamaized|cloudflare|azureedge|googleusercontent|1e100|gvt1|fastly)\.(com|net)$")),
    ("Developer/docs noise", re.compile(r"(^|\.)(w3\.org|xml\.org|schema\.org|github\.com|apache\.org|json-schema\.org|ietf\.org|rfc-editor\.org|unicode\.org|opensource\.org|kotlinlang\.org|gradle\.org|maven\.org|example\.com|localhost)$")),
]


def classify(host: str) -> str:
    for name, rx in DOMAIN_CLASSES:
        if rx.search(host):
            return name
    return "Other/unclassified"


def load_strings() -> list[str]:
    out = []
    with open(os.path.join(ROOT, "work/all-strings.txt"), encoding="utf-8", errors="replace") as fh:
        for line in fh:
            _, _, s = line.partition("\t")
            out.append(s.rstrip("\n"))
    return out


def main() -> None:
    strings = load_strings()
    lowered = [s.lower() for s in strings]
    report: dict = {"string_universe": len(strings), "signals": {}, "secrets": {}, "domains": {}}
    for family, spec in SIGNALS.items():
        entry = {"markers": {}, "total_hits": 0}
        for m in spec["markers"]:
            ml = m.lower()
            if re.fullmatch(r"[A-Za-z0-9_]{1,8}", m):
                rx = re.compile(r"(?<![a-z0-9_])" + re.escape(ml) + r"(?![a-z0-9_])")
                hits = [s for s, low in zip(strings, lowered) if rx.search(low)]
            else:
                hits = [s for s, low in zip(strings, lowered) if ml in low]
            if hits:
                entry["markers"][m] = {"count": len(hits),
                                       "samples": [h[:110] for h in hits[:3]]}
                entry["total_hits"] += len(hits)
        if spec.get("note"):
            entry["note"] = spec["note"]
        entry["present"] = bool(entry["markers"])
        report["signals"][family] = entry
    for name, rx in SECRET_PATTERNS.items():
        found = []
        for s in strings:
            for m in rx.finditer(s):
                v = m.group(0)
                # never persist the raw secret: keep a masked fingerprint only
                masked = (v[:6] + "*" * max(len(v) - 10, 0) + v[-4:]) if len(v) > 12 else "*" * len(v)
                found.append({"masked": masked, "len": len(v),
                              "sha256_prefix": __import__("hashlib").sha256(v.encode()).hexdigest()[:12]})
        report["secrets"][name] = {"count": len(found),
                                   "unique": len({f["sha256_prefix"] for f in found}),
                                   "examples_masked": found[:5]}
    hosts = Counter()
    for s in strings:
        for m in re.finditer(r"https?://([A-Za-z0-9._-]+\.[A-Za-z]{2,})", s):
            hosts[m.group(1).lower()] += 1
    by_class: dict[str, dict[str, int]] = defaultdict(dict)
    for host, n in hosts.items():
        by_class[classify(host)][host] = n
    report["domains"] = {
        "unique_hosts": len(hosts),
        "by_class": {k: {"host_count": len(v), "refs": sum(v.values()),
                         "top": sorted(v.items(), key=lambda kv: -kv[1])[:10]}
                     for k, v in sorted(by_class.items(), key=lambda kv: -sum(kv[1].values()))},
        "cleartext_http_refs": sum(1 for s in strings
                                   if re.search(r"http://(?!schemas\.android\.com|www\.w3\.org|xml\.org|ns\.adobe\.com|schemas\.xmlsoap)[A-Za-z0-9]", s)),
        "cleartext_samples": [s[:100] for s in strings
                              if re.search(r"http://(?!schemas\.android\.com|www\.w3\.org|xml\.org|ns\.adobe\.com|schemas\.xmlsoap)[A-Za-z0-9]", s)][:10],
    }
    # Vietnamese-language UI string density (diacritics heuristic)
    viet = sum(1 for s in strings if re.search(r"[ăâđêôơưàảãáạằẳẵắặầẩẫấậèẻẽéẹềểễếệìỉĩíịòỏõóọồổỗốộờởỡớợùủũúụừửữứựỳỷỹýỵ]", s, re.I | re.UNICODE))
    report["vietnamese_strings"] = viet
    report["vietnamese_string_pct"] = round(100 * viet / max(len(strings), 1), 2)
    with open(os.path.join(DATA, "06-signals.json"), "w") as fh:
        json.dump(report, fh, indent=2, ensure_ascii=False)
    for fam, e in report["signals"].items():
        print(f"{fam:<26} hits={e['total_hits']:>6}  markers={len(e['markers'])}")
    print("\nsecrets:", {k: v["count"] for k, v in report["secrets"].items()})
    print("hosts:", report["domains"]["unique_hosts"], "cleartext refs:", report["domains"]["cleartext_http_refs"])
    print("viet strings:", report["vietnamese_strings"], f"({report['vietnamese_string_pct']}%)")
    for cls, v in report["domains"]["by_class"].items():
        print(f"  {cls:<28} hosts={v['host_count']:>3} refs={v['refs']:>4}")


if __name__ == "__main__":
    main()
