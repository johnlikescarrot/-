#!/usr/bin/env python3
"""P3 evidence builder: deterministic excerpts from the artifact for the citable report.
Produces research/evidence/excerpts.json — each entry quotes a string/route and the
DEX file + string index it was recovered from (index = line number in dex-strings.txt)."""
import json, os, re

ROOT = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
RES = os.path.join(ROOT, "research")
OUT = os.path.join(RES, "evidence")
os.makedirs(OUT, exist_ok=True)

raw = open(os.path.join(RES, "dex-strings.txt"), encoding="utf-8", errors="replace").read().splitlines()
idx = {}
for i, s in enumerate(raw, 1):
    if re.match(r"^[ -~]{4,}$", s):
        idx.setdefault(s, i)

QUERIES = {
    "env.staging_endpoints": r"^https://(stg|sandbox|socialstg|socialdev|devcps)",
    "env.prod_endpoints": r"^https://(api|sapi|openapi|social|simg|upload-img|uudai|cps|scard)\.zalopay",
    "deeplink.launch": r"^zalopay://launch/app/-?\d+",
    "deeplink.cashier": r"^zalopay://cashier",
    "integrity.root": r"^(/system/(x)?bin/su|/system/app/Superuser\.apk|/magisk)$",
    "integrity.emulator": r"(Genymotion|Android Emulator \(AVD\)|Emulator Detected)",
    "integrity.hook": r"^(FRIDA|frida|Xposed|xposed)",
    "kyc.vneid": r"VNeID|vneid|vnid",
    "kyc.nfc_emrtd": r"(MRZ|BAC|PACE|eMRTD|jmrtd|NFC (?:DOB|NAME|GENDER))",
    "nfc.visa": r"com\.visa|tap.?to.?pay|isShowSkipNFC",
    "miniapp.runtime": r"(Griver|AppContainer|appx-ng)",
    "crypto.stack": r"^(AES|RSA|SHA-256|HMAC|PBKDF|ECIES|X25519|Ed25519)",
    "pinning": r"(SSLPinning|certificatePinner|sha256/)",
    "transport": r"(OkHttp|Retrofit|gRPC|HTTP/2|quic)",
    "obs.analytics": r"(appsflyer|adjust|firebase|crashlytics|sentry|adjust\.com)",
    "payment.rails": r"(napas|ibft|vietqr|vnpay|alipayplus|visa|mastercard|jcb)",
}
res = {}
for name, pat in QUERIES.items():
    rx = re.compile(pat, re.I)
    hits = [(s, idx[s]) for s in idx if rx.search(s)]
    hits.sort(key=lambda t: t[1])
    res[name] = [{"string": s[:220], "string_index": i} for s, i in hits[:40]]
    print(f"{name}: {len(hits)} hits (capped {len(res[name])})")
json.dump(res, open(os.path.join(OUT, "excerpts.json"), "w"), indent=1, ensure_ascii=False)
