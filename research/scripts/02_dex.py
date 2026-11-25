#!/usr/bin/env python3
"""Stage 2 — DEX corpus statistics, package map, native methods, string harvest."""
from __future__ import annotations

import json
import re
import sys
from collections import Counter, defaultdict
from pathlib import Path

sys.path.insert(0, str(Path(__file__).resolve().parent))
from dex_parser import ACC_NATIVE, DexFile  # noqa: E402

ROOT = Path(__file__).resolve().parents[2]
DATA = ROOT / "research" / "data"
DEXDIR = ROOT / "work" / "dex"

URL_RE = re.compile(rb"https?://[A-Za-z0-9._~:/?#\[\]@!$&'()*+,;=%-]{4,120}")
HOST_RE = re.compile(rb"\b(?:[a-z0-9](?:[a-z0-9-]{0,61}[a-z0-9])?\.)+(?:vn|com|net|io|sg|co|org|dev|cloud|app|ai)\b")
PATH_RE = re.compile(rb"^/?(?:v\d|api|oauth|graphql|sdk)[A-Za-z0-9_\-/{}.]{2,80}$")

THIRD_PARTY = {
    "com.google": "Google (Play services, Firebase, ML Kit, GMS)",
    "com.facebook": "Meta SDK (login, ads, Graph)",
    "com.appsflyer": "AppsFlyer attribution",
    "com.adjust": "Adjust attribution",
    "com.squareup": "Square (OkHttp, Retrofit, Moshi)",
    "okhttp3": "OkHttp",
    "retrofit2": "Retrofit",
    "io.reactivex": "RxJava",
    "io.grpc": "gRPC",
    "com.airbnb": "Lottie",
    "com.bumptech": "Glide",
    "org.bouncycastle": "Bouncy Castle (crypto, post-quantum)",
    "com.scottyab": "RootBeer (root detection)",
    "com.alibaba": "Alibaba ARouter / mPaaS",
    "com.alipay": "Alipay SDK",
    "com.tencent": "Tencent (bugly, mmkv, x5)",
    "com.huawei": "Huawei HMS",
    "com.samsung": "Samsung SDK",
    "com.mastercard": "Mastercard (MDES tokenization)",
    "com.visa": "Visa SDK",
    "com.stripe": "Stripe",
    "com.adyen": "Adyen",
    "com.braintreepay": "Braintree/PayPal",
    "io.card": "card.io (card scanning)",
    "com.microblink": "Microblink (document/ID scan)",
    "com.regula": "Regula (document forensics)",
    "com.innovatrics": "Innovatrics (face)",
    "com.trueface": "TrueFace (face liveness)",
    "ai.trueid": "TrueID (eKYC)",
    "com.taobao": "Alibaba Weex",
    "com.uc": "UC WebView",
    "com.zing.zalo": "Zalo platform integration",
    "com.vng": "VNG (ZaloPay owner)",
    "vn.com.vng": "VNG / ZaloPay first-party",
    "io.flutter": "Flutter",
    "com.unity": "Unity",
    "com.crashlytics": "Crashlytics",
    "io.sentry": "Sentry",
    "com.datadog": "Datadog RUM",
    "com.newrelic": "New Relic",
    "com.apptimize": "Apptimize",
    "com.optimizely": "Optimizely",
    "com.mixpanel": "Mixpanel",
    "com.amplitude": "Amplitude",
    "com.clevertap": "CleverTap",
    "com.moengage": "MoEngage",
    "com.onesignal": "OneSignal",
    "com.pushwoosh": "Pushwoosh",
    "com.urbanairship": "Airship",
    "com.kochava": "Kochava",
    "com.branch": "Branch",
    "com.singular": "Singular",
    "com.devtodev": "DevToDev",
    "com.flurry": "Flurry",
    "com.inmobi": "InMobi",
    "com.mopub": "MoPub",
    "com.unity3d": "Unity Ads",
    "com.vungle": "Vungle",
    "com.chartboost": "Chartboost",
    "com.ironsource": "ironSource",
    "com.applovin": "AppLovin",
    "com.tapjoy": "Tapjoy",
    "com.pangle": "Pangle (ByteDance ads)",
    "com.bytedance": "ByteDance SDK",
    "com.growingio": "GrowingIO",
    "cn.jpush": "JPush",
    "com.xiaomi": "Xiaomi push",
    "com.oppo": "OPPO push",
    "com.vivo": "vivo push",
    "com.meizu": "Meizu push",
    "com.hihonor": "HONOR push",
    "androidx": "AndroidX / Jetpack",
    "kotlin": "Kotlin stdlib",
    "kotlinx": "kotlinx (coroutines)",
    "dagger": "Dagger / Hilt DI",
    "javax": "Java/Jakarta",
    "org.jetbrains": "JetBrains annotations",
    "com.jakewharton": "JakeWharton libs",
    "com.github": "GitHub-hosted libs",
    "org.apache": "Apache Commons",
    "org.json": "JSON",
    "com.fasterxml": "Jackson",
    "io.jsonwebtoken": "JWT",
    "com.nimbusds": "Nimbus JOSE+JWT",
    "org.conscrypt": "Conscrypt TLS",
    "com.google.crypto": "Tink (crypto)",
    "net.sqlcipher": "SQLCipher (encrypted DB)",
    "net.zetetic": "SQLCipher",
    "io.socket": "Socket.IO client",
    "com.tinder": "Tinder Scarlet (websockets)",
    "org.java_websocket": "Java WebSocket",
}

CRYPTO_TOKENS = [
    "AES/GCM", "AES/CBC", "AES/ECB", "AES/GCM/NoPadding", "RSA/ECB/PKCS1Padding",
    "RSA/ECB/OAEPWithSHA-256AndMGF1Padding", "SHA-256", "SHA-1", "HmacSHA256",
    "HmacSHA512", "PBKDF2WithHmacSHA1", "PBKDF2WithHmacSHA256", "ECDH", "ECIES",
    "Ed25519", "X25519", "secp256r1", "prime256v1", "Argon2", "scrypt", "Kyber",
    "ML-KEM", "Dilithium", "SPHINCS", "Picnic", "BIKE", "HQC", "FrodoKEM",
    "KeyStore", "AndroidKeyStore", "StrongBox", "TEE",
]


def pkg_family(pkg: str) -> str:
    for prefix, label in sorted(THIRD_PARTY.items(), key=lambda kv: -len(kv[0])):
        if pkg == prefix or pkg.startswith(prefix + "/") or pkg.startswith(prefix):
            return label
    return "third-party (other)" if pkg else "(root)"


def main() -> None:
    dexes = sorted(DEXDIR.glob("classes*.dex"))
    rows, packages, natives, strings_all = [], Counter(), [], []
    classes_per_dex = {}
    kotlin = java = 0
    total_methods = total_classes = 0

    for p in dexes:
        d = DexFile(p)
        cls = list(d.classes())
        classes_per_dex[p.name] = len(cls)
        total_classes += len(cls)
        total_methods += d.method_ids_size
        for c in cls:
            pkg = c.name.strip("L;").rsplit("/", 1)[0] if "/" in c.name else ""
            packages[pkg.split("/")[0]] += 1
            if c.source_file and c.source_file.endswith(".kt"):
                kotlin += 1
            else:
                java += 1
            for m in c.methods:
                if m.access & ACC_NATIVE:
                    natives.append({"class": c.name, "method": m.name, "proto": m.proto})
        rows.append({
            "dex": p.name, "bytes": p.stat().st_size, "version": d.version,
            "checksum": f"{d.checksum:08x}", "sha1": d.sha1,
            "strings": d.string_ids_size, "types": d.type_ids_size,
            "methods": d.method_ids_size, "classes": d.class_defs_size,
        })
        strings_all.extend(d.strings)

    urls = sorted({m.group(0).decode("ascii", "replace") for s in strings_all
                   for m in URL_RE.finditer(s.encode("utf-8", "surrogatepass"))})
    hosts = Counter(m.group(0).decode("ascii", "replace") for s in strings_all
                    for m in HOST_RE.finditer(s.encode("utf-8", "surrogatepass")))
    crypto = {t: sum(1 for s in strings_all if t in s) for t in CRYPTO_TOKENS}

    fam = Counter()
    for pkg, n in packages.items():
        fam[pkg_family(pkg)] += n

    out = {
        "dex_files": rows,
        "totals": {
            "dex": len(rows), "bytes": sum(r["bytes"] for r in rows),
            "classes": total_classes, "methods": total_methods,
            "strings": sum(r["strings"] for r in rows),
            "types": sum(r["types"] for r in rows),
        },
        "language": {"kotlin": kotlin, "java": java,
                     "kotlin_pct": round(100 * kotlin / max(1, kotlin + java), 1)},
        "top_level_packages": packages.most_common(40),
        "package_families": fam.most_common(30),
        "native_methods": {"count": len(natives), "sample": natives[:40]},
        "urls": {"count": len(urls), "sample": urls[:200]},
        "hosts": {"unique": len(hosts), "top": hosts.most_common(60)},
        "crypto_string_hits": crypto,
    }
    (DATA / "02_dex.json").write_text(json.dumps(out, indent=1, ensure_ascii=False), encoding="utf-8")
    print(f"dex: {len(rows)} files, {total_classes:,} classes, {total_methods:,} methods, "
          f"kotlin {out['language']['kotlin_pct']}%, native methods {len(natives)}, urls {len(urls)}, hosts {len(hosts)}")


if __name__ == "__main__":
    main()
