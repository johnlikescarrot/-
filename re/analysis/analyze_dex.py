#!/usr/bin/env python3
"""
Stage 02 — DEX-level static reverse engineering.

Decodes every DEX payload of the base APK and produces the analytical datasets
used by the report and the film:

  006-dex-inventory.json       per-DEX header stats + digests
  007-class-inventory.json    every class, grouped by top-level namespace,
                              split into "app" (vn.com.vng.zalopay) vs 3rd-party
  008-strings-urls.json       extracted HTTP(S) endpoints, domains, and
                              keyword-classified interesting strings
  009-sdk-inventory.json      third-party SDK / library detection by namespace
  010-app-method-index.json   method inventory of the app's own namespace

Design notes
------------
* `androguard.core.dex.DEX` is fed the raw bytes (androguard 4.x no longer
  accepts a path).
* Work is done per-DEX and flushed to disk after each file so a partial run is
  still usable evidence.
* Only the app's own namespace gets method-level enumeration; third-party
  namespaces are summarised by class count to keep the run bounded.
"""

from __future__ import annotations

import hashlib
import json
import logging
import os
import re
import sys
import time
from collections import Counter, defaultdict
from urllib.parse import urlparse

ROOT = os.path.dirname(os.path.dirname(os.path.dirname(os.path.abspath(__file__))))
WORK = os.path.join(ROOT, "work")
DEX_DIR = os.path.join(WORK, "extract", "dex")
OUT = os.path.join(ROOT, "re", "analysis")
BASE_APK = os.path.join(WORK, "extract", "xapk", "vn.com.vng.zalopay.apk")

APP_PREFIX = "vn/com/vng/zalopay"

URL_RE = re.compile(r"https?://[^\s\"'<>\)\]\}]{4,}")
DOMAIN_RE = re.compile(r"^(?:[a-z0-9](?:[a-z0-9-]{0,61}[a-z0-9])?\.)+[a-z]{2,}$", re.I)

# namespace prefix -> label (used for SDK / library fingerprinting)
SDK_SIGNATURES = [
    ("com/google/firebase", "Firebase"),
    ("com/google/android/gms", "Google Play Services"),
    ("com/google/android/material", "Google Material Components"),
    ("com/facebook", "Meta / Facebook SDK"),
    ("com/facebook/sdk", "Meta / Facebook SDK"),
    ("okhttp3", "OkHttp"),
    ("retrofit2", "Retrofit"),
    ("com/squareup/retrofit", "Retrofit"),
    ("com/squareup/okhttp", "OkHttp"),
    ("com/squareup/moshi", "Moshi"),
    ("com/squareup/picasso", "Picasso"),
    ("com/squareup/okio", "Okio"),
    ("dagger", "Dagger"),
    ("com/google/dagger", "Dagger"),
    ("androidx", "AndroidX"),
    ("android/arch", "Android Architecture"),
    ("kotlinx", "Kotlin stdlib / coroutines"),
    ("io/reactivex", "RxJava"),
    ("io/grpc", "gRPC"),
    ("io/sentry", "Sentry"),
    ("com/anet/crop", "ANet (Alibaba)"),
    ("com/alibaba", "Alibaba SDK"),
    ("com/alipay", "Alipay SDK"),
    ("com/tencent", "Tencent SDK"),
    ("com/tencent/mm", "WeChat SDK"),
    ("com/jpush", "JPush (Aurora)"),
    ("cn/jpush", "JPush (Aurora)"),
    ("com/umeng", "Umeng"),
    ("com/bytedance", "ByteDance"),
    ("com/ss/android", "ByteDance (Douyin)"),
    ("tv/danmaku", "Bilibili"),
    ("com/bilibili", "Bilibili"),
    ("io/agora", "Agora"),
    ("com/netease", "NetEase"),
    ("com/snap", "Snap"),
    ("com/byted", "ByteDance"),
    ("org/webrtc", "WebRTC"),
    ("com/google/vr", "Google VR"),
    ("com/vng", "VNG internal"),
    ("vn/com/vng", "VNG internal"),
    ("com/vng", "VNG internal"),
    ("io/flutter", "Flutter engine"),
    ("com/google/zxing", "ZXing"),
    ("org/greenrobot/eventbus", "EventBus"),
    ("com/bumptech/glide", "Glide"),
    ("com/github", "GitHub SDK"),
    ("com/auth0", "Auth0"),
    ("com/stripe", "Stripe"),
    ("io/moshi", "Moshi"),
    ("com/parse", "Parse"),
    ("com/crashlytics", "Crashlytics"),
    ("com/instabug", "Instabug"),
    ("com/adcolony", "AdColony"),
    ("com/unity3d", "Unity"),
    ("com/mopub", "MoPub"),
    ("com/applovin", "AppLovin"),
    ("com/ironsource", "IronSource"),
    ("com/unity3d", "Unity"),
]

KEYWORD_PATTERNS = {
    "api_endpoint": r"(?i)(api|gateway|endpoint|rest|graphql)",
    "auth_token": r"(?i)(access[_-]?token|auth[_-]?token|refresh[_-]?token|bearer|jwt)",
    "crypto": r"(?i)(aes|rsa|ecb|cbc|gcm|hmac|sha256|md5|encrypt|decrypt|cipher)",
    "signing": r"(?i)(\bsign(ature)?\b|signkey|app[_-]?sign|hmac)",
    "root_anti_debug": r"(?i)(root|su\b|magisk|xposed|frida|substrate|antidebug|isdebugger|Debug.isDebuggerConnected)",
    "ssl_pinning": r"(?i)(pin|pinning|certificate|trustmanager|sslcontext|hostnameverifier)",
    "payment": r"(?i)(payment|pay\b|transfer|transaction|wallet|gateway|vnpay|momo|zalopay|scan)",
    "storage": r"(?i)(sharedpreferences|database|room|realm|sqlite|fileprovider)",
    "telemetry": r"(?i)(track|analytics|metric|report|logcat|telemetry|sentry|crash)",
    "native_bridge": r"(?i)(jni|native|system\.loadlibrary|jnibridge|bridge)",
    "obfuscation": r"(?i)(proguard|r8|mapping|obfuscat)",
}


def ensure_out() -> None:
    os.makedirs(OUT, exist_ok=True)


def dump(name: str, payload) -> None:
    path = os.path.join(OUT, name)
    with open(path, "w", encoding="utf-8") as fh:
        json.dump(payload, fh, indent=2, ensure_ascii=False, sort_keys=False,
                  default=lambda o: sorted(o) if isinstance(o, (set, frozenset, tuple)) else str(o))
        fh.write("\n")
    print(f"  wrote {path}", flush=True)


def strip_class(name: str) -> str:
    """Dalvik class names look like `Lvn/com/vng/zalopay/Foo;` — strip the
    leading `L` and trailing `;` so we can work with plain package paths."""
    if name.startswith("L") and name.endswith(";"):
        return name[1:-1]
    return name.rstrip(";")


def class_to_pkg(name: str, depth: int = 4) -> str:
    """`Lvn/com/vng/zalopay/foo/Bar;` -> `vn.com.vng.zalopay`"""
    parts = strip_class(name).split("/")
    return ".".join(parts[:depth]) if parts else ""


def sdk_label(pkg_dotted: str) -> str:
    """Match a dotted namespace against the SDK signature table."""
    slash = pkg_dotted.replace(".", "/")
    for prefix, label in SDK_SIGNATURES:
        if slash == prefix or slash.startswith(prefix + "/"):
            return label
    return None


def clean_url(u: str) -> str:
    return u.rstrip(".,;:!?\"'<>)]}")


def main() -> int:
    logging.getLogger("androguard").setLevel(logging.CRITICAL)
    ensure_out()
    started = time.time()
    print(f"== stage 02: DEX static analysis ({time.strftime('%Y-%m-%dT%H:%M:%SZ', time.gmtime())}) ==")

    dex_files = sorted(f for f in os.listdir(DEX_DIR) if f.endswith(".dex"))
    print(f"[1/5] {len(dex_files)} DEX payloads to analyse")

    dex_inventory = []
    class_counter = Counter()
    namespace_counter = Counter()
    own_classes = []
    all_strings: set[str] = set()
    url_counter = Counter()
    keyword_hits = {k: set() for k in KEYWORD_PATTERNS}
    method_index = defaultdict(list)
    sdk_class_counter = Counter()
    per_dex_stats = []

    for name in dex_files:
        path = os.path.join(DEX_DIR, name)
        raw = open(path, "rb").read()
        t0 = time.time()
        from androguard.core.dex import DEX

        dex = DEX(raw)
        classes = dex.get_classes()
        strings = dex.get_strings()
        n_classes = len(classes)
        n_strings = len(strings)

        rec = {
            "file": name,
            "size_bytes": len(raw),
            "sha256": hashlib.sha256(raw).hexdigest(),
            "classes": n_classes,
            "strings": n_strings,
        }
        print(f"  {name}: {n_classes} classes, {n_strings} strings "
              f"({time.time() - t0:.1f}s)", flush=True)

        for c in classes:
            cname = c.get_name()
            class_counter[cname] += 1
            sname = strip_class(cname)
            ns = class_to_pkg(sname)
            namespace_counter[ns] += 1
            if sname.startswith(APP_PREFIX + "/") or sname == APP_PREFIX:
                own_classes.append(sname)
                label = sdk_label(ns)
                if label:
                    sdk_class_counter[label] += 1
                # method-level enumeration only for the app namespace
                try:
                    for m in c.get_methods():
                        method_index[sname].append(
                            {"method": m.get_name(), "descriptor": m.get_descriptor()}
                        )
                except Exception:  # noqa: BLE001
                    pass
            else:
                label = sdk_label(ns)
                if label:
                    sdk_class_counter[label] += 1

        for s in strings:
            if not isinstance(s, str):
                s = str(s)
            all_strings.add(s)
            for m in URL_RE.findall(s):
                url_counter[clean_url(m)] += 1
            for key, pattern in KEYWORD_PATTERNS.items():
                if re.search(pattern, s):
                    keyword_hits[key].add(s)

        rec["top_namespaces"] = namespace_counter.most_common(10)
        per_dex_stats.append(rec)
        # flush partial progress
        dump("006-dex-inventory.json", {
            "generated_at_utc": time.strftime("%Y-%m-%dT%H:%M:%SZ", time.gmtime()),
            "base_apk": os.path.relpath(BASE_APK, ROOT),
            "app_namespace": APP_PREFIX,
            "dex_files": per_dex_stats,
        })

    print(f"[2/5] {len(all_strings)} unique strings, {len(url_counter)} unique URLs")

    # ---- class inventory ------------------------------------------------- #
    print("[3/5] class inventory ...")
    top_namespaces = namespace_counter.most_common(40)
    third_party = Counter()
    app_ns_count = 0
    jvm_roots = ("android.", "java.", "javax.", "kotlin.", "kotlinx.", "org.json",
                 "org.w3c", "org.xml", "sun.", "com.sun.", "org.apache.http",
                 "org.bouncycastle", "org.conscrypt", "org.khronos", "org.libpng")
    for ns, count in namespace_counter.items():
        if ns == "vn.com.vng.zalopay" or ns.startswith("vn.com.vng"):
            app_ns_count += count
        elif not any(ns == r[:-1] or ns.startswith(r) for r in jvm_roots):
            third_party[ns] += count
    dump("007-class-inventory.json", {
        "total_classes": sum(class_counter.values()),
        "unique_classes": len(class_counter),
        "app_namespace_classes": app_ns_count,
        "own_namespace_sample": sorted(own_classes)[:400],
        "own_namespace_count": len(own_classes),
        "top_namespaces": [{"namespace": ns, "classes": c} for ns, c in top_namespaces],
        "third_party_namespace_counts": [
            {"namespace": ns, "classes": c} for ns, c in third_party.most_common(60)
        ],
        "duplicate_class_names": [
            {"class": k, "count": v} for k, v in class_counter.most_common(20) if v > 1
        ],
    })

    # ---- URLs / strings -------------------------------------------------- #
    print("[4/5] endpoint & keyword extraction ...")
    domains = Counter()
    for url in url_counter:
        try:
            host = urlparse(url).netloc.lower()
            if host:
                domains[host] += url_counter[url]
        except Exception:  # noqa: BLE001
            pass
    # classify domains
    domain_class = {}
    for host in domains:
        domain_class[host] = classify_domain(host)
    dump("008-strings-urls.json", {
        "unique_string_count": len(all_strings),
        "unique_url_count": len(url_counter),
        "top_urls": [{"url": u, "count": c} for u, c in url_counter.most_common(120)],
        "domain_counts": [{"domain": d, "occurrences": c, "class": domain_class.get(d)}
                          for d, c in domains.most_common(120)],
        "keyword_hits": {
            k: {"count": len(v), "sample": sorted(v)[:25]} for k, v in keyword_hits.items()
        },
    })

    # ---- SDK inventory -------------------------------------------------- #
    print("[5/5] SDK / library inventory ...")
    dump("009-sdk-inventory.json", {
        "detected_sdks": [{"sdk": s, "classes": c} for s, c in sdk_class_counter.most_common()],
        "method_index_classes": len(method_index),
        "method_index_total_methods": sum(len(v) for v in method_index.values()),
    })
    dump("010-app-method-index.json", {
        "app_namespace": APP_PREFIX,
        "classes_with_methods": len(method_index),
        "methods": [{"class": k, "methods": v} for k, v in method_index.items()],
    })

    print(f"== stage 02 complete in {time.time() - started:.1f}s ==")
    print(f"   unique classes : {len(class_counter)}")
    print(f"   app classes    : {app_ns_count}")
    print(f"   unique strings : {len(all_strings)}")
    print(f"   unique urls    : {len(url_counter)}")
    print(f"   top domain     : {domains.most_common(1)}")
    return 0


def classify_domain(host: str) -> str:
    h = host.split(":")[0]
    known = {
        "api.zalopay.vn": "ZaloPay production API",
        "developers.zalopay.vn": "ZaloPay developer portal",
        "sq.zalopay.vn": "ZaloPay",
        "capi.zalopay.vn": "ZaloPay",
        "pay.zalo.me": "ZaloPay",
        "zalo.me": "Zalo",
        "graph.facebook.com": "Meta Graph API",
        "connect.facebook.net": "Meta",
        "edge-chat.facebook.com": "Meta",
        "graph.instagram.com": "Meta",
        "oauth.facebook.com": "Meta",
        "firebase.googleapis.com": "Firebase",
        "firebasedynamiclinks.googleapis.com": "Firebase Dynamic Links",
        "fcm.googleapis.com": "Firebase Cloud Messaging",
        "iid.googleapis.com": "Firebase",
        "googleapis.com": "Google API",
        "google-analytics.com": "Google Analytics",
        "crashlytics.com": "Crashlytics",
        "sentry.io": "Sentry",
        "sentry-cdn.com": "Sentry",
        "connectivitycheck.gstatic.com": "Google connectivity probe",
        "dl.google.com": "Google",
        "play.google.com": "Google Play",
        "www.google.com": "Google",
        "pay.google.com": "Google Pay",
        "ssl.gstatic.com": "Google",
    }
    if h in known:
        return known[h]
    if "zalopay" in h or "zalo" in h:
        return "ZaloPay / Zalo domain"
    if "facebook" in h or "fbcdn" in h or "fb.com" in h:
        return "Meta domain"
    if "google" in h or "gstatic" in h or "gvt1" in h:
        return "Google domain"
    if "amazonaws" in h:
        return "AWS"
    if "cloudfront" in h:
        return "AWS CloudFront"
    if "azure" in h or "microsoft" in h:
        return "Microsoft Azure"
    if "gcp" in h or "googleusercontent" in h:
        return "Google Cloud"
    return "other"


if __name__ == "__main__":
    sys.exit(main())
