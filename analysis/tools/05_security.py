#!/usr/bin/env python3
"""Stage 5 — security & capability census over the DEX string corpus.

String-level evidence only: for every theme we report the count of distinct
strings, a whitelist-safe sample, and the owning dex file(s).  This is a
*capability* census, not a verdict — a hit means "the binary can do this",
not "the app does this to you".

Output: data/05_security.json
"""
from __future__ import annotations

import json
import os
import re
from collections import Counter, defaultdict
from datetime import datetime, timezone

ROOT = os.path.abspath(os.path.join(os.path.dirname(__file__), "..", ".."))
OUT = os.path.join(ROOT, "data")
STRDIR = os.path.join(OUT, "dex_strings")

THEMES: dict[str, dict] = {
    "symmetric_crypto": {
        "rx": r"\b(AES|AES/CBC/PKCS5Padding|AES/GCM/NoPadding|AES/ECB|DESede|ARC4|ChaCha20|SM4)\b",
        "note": "JCA/BouncyCastle transformation names reachable from app code.",
    },
    "asymmetric_crypto": {
        "rx": r"\b(RSA/ECB/PKCS1Padding|RSA/None/OAEPWithSHA-256|ECIES|SM2|Ed25519|Secp256r1|Secp256k1|secp384r1)\b",
        "note": "Public-key primitives, including the Chinese SM2 suite.",
    },
    "digest_and_kdf": {
        "rx": r"\b(SHA-256|SHA-512|SHA-1|MD5|PBKDF2WithHmacSHA|HmacSHA256|HmacSHA512|Argon2|scrypt|SM3)\b",
        "note": "Hashing and key-derivation algorithms referenced by name.",
    },
    "tls_pinning": {
        "rx": r"(?i)(sha256/|CertificatePinner|X509TrustManager|TrustManagerImpl|pinning|NetworkSecurityTrustManager)",
        "note": "Certificate pinning / custom trust-manager surface.",
    },
    "root_emulator_detection": {
        "rx": r"(?i)(/system/bin/su|/system/xbin/su|Superuser\.apk|magisk|busybox|test-keys|ro\.debuggable|isRooted|RootBeer|goldfish|qemu|genymotion|blue ?stacks|isEmulator)",
        "note": "Integrity probes for rooted devices and emulators.",
    },
    "hook_detection": {
        "rx": r"(?i)(frida|xposed|substrate|libhook|ptrace|/proc/self/maps|TracerPid)",
        "note": "Anti-instrumentation surface.",
    },
    "dynamic_code": {
        "rx": r"(?i)(DexClassLoader|InMemoryDexClassLoader|PathClassLoader|loadDex|createPackageContext|ariver|griver|mini_?program)",
        "note": "Dynamic class loading and the mini-app runtime (Alipay mPaaS / Ariver).",
    },
    "webview_bridge": {
        "rx": r"(?i)(addJavascriptInterface|WebViewClient|shouldOverrideUrlLoading|postWebMessage|evaluateJavascript)",
        "note": "JavaScript bridge surface — where web content can call into native.",
    },
    "biometric_and_keystore": {
        "rx": r"(?i)(BiometricPrompt|KeyGenParameterSpec|AndroidKeyStore|fingerprint|FaceID|setUserAuthenticationRequired)",
        "note": "Hardware-backed key storage and biometric gating.",
    },
    "payments_and_emv": {
        "rx": r"(?i)(EMV|APDU|iso7816|NFC|Napas|VietQR|QRPay|cardholder|issuer|acquirer|3DS|OTP)",
        "note": "Card-present / card-not-present payment vocabulary.",
    },
    "ekyc_face": {
        "rx": r"(?i)(liveness|facematch|selfie|OCR|CCCD|CMND|passport|trueid|faceauth)",
        "note": "Identity-verification (eKYC) surface, Vietnamese document types.",
    },
    "device_attestation": {
        "rx": r"(?i)(SafetyNet|PlayIntegrity|Attestation|KeyAttestation|deviceFingerprint|umid|oaid|imei|androidId)",
        "note": "Device-integrity and identifier collection.",
    },
    "analytics_and_ads": {
        "rx": r"(?i)(FirebaseAnalytics|appsflyer|adjust|facebook\.appevents|crashlytics|pangle|unityads|ironsource|applovin)",
        "note": "Measurement and monetisation SDKs present in the code.",
    },
    "obfuscation": {
        "rx": r"^L[a-z0-9]{1,2}/[a-z0-9]{1,3};$",
        "note": "Single-letter package/class names produced by R8 shrinking.",
    },
}


def main() -> None:
    per_theme: dict[str, dict] = {}
    per_dex: dict[str, Counter] = defaultdict(Counter)
    all_strings: set[str] = set()

    files = sorted(os.listdir(STRDIR))
    for fn in files:
        with open(os.path.join(STRDIR, fn), errors="replace") as fh:
            strings = [s for s in fh.read().split("\n") if s]
        all_strings.update(strings)
        for theme, spec in THEMES.items():
            rx = re.compile(spec["rx"])
            hit = [s for s in strings if rx.search(s)]
            per_dex[fn][theme] += len(hit)
            slot = per_theme.setdefault(theme, {"note": spec["note"], "count": 0,
                                                "unique": set(), "samples": [], "dex": Counter()})
            slot["count"] += len(hit)
            slot["unique"].update(hit[:4000])
            if len(slot["samples"]) < 14:
                slot["samples"].extend([s[:160] for s in hit[:14 - len(slot["samples"])]])
            slot["dex"][fn] += len(hit)

    for theme, slot in per_theme.items():
        slot["unique"] = len(slot["unique"])
        slot["dex"] = dict(slot["dex"].most_common(5))

    # third-party SDK census by string namespace
    sdk_markers = {
        "Firebase / Google Play services": r"com/google/firebase|com/google/android/gms",
        "Alipay mPaaS (Ariver) mini-app runtime": r"com/alibaba/ariver|com/alibaba/griver",
        "Alipay IAP (international acquirer SDK)": r"com/iap/ac",
        "BouncyCastle (post-quantum build present)": r"org/bouncycastle/pqc",
        "Visa Click-to-Pay SDK": r"com/visa/cbp",
        "Crashlytics": r"com/google/firebase/crashlytics",
        "Rive animation runtime": r"app/rive",
        "OkHttp / Retrofit stack": r"okhttp3|retrofit2",
        "Barcode / ML Kit vision": r"com/google/mlkit|barhopper",
        "TrueID eKYC": r"vn/ai/trueid",
        "VFace liveness SDK": r"vn/ai/faceauth",
    }
    joined = "\n".join(sorted(all_strings))
    sdk_rows = []
    for label, rx in sdk_markers.items():
        n = len(re.findall(rx, joined))
        sdk_rows.append({"sdk": label, "string_hits": n})
    sdk_rows.sort(key=lambda r: -r["string_hits"])

    result = {
        "generated_at": datetime.now(timezone.utc).isoformat(),
        "corpus": {"dex_files": len(files), "unique_strings": len(all_strings)},
        "themes": {k: {kk: (sorted(vv) if isinstance(vv, set) else vv)
                       for kk, vv in v.items() if kk != "samples"}
                   for k, v in per_theme.items()},
        "theme_samples": {k: v["samples"] for k, v in per_theme.items()},
        "per_dex": {k: dict(v) for k, v in per_dex.items()},
        "sdk_census": sdk_rows,
        "limits": [
            "String presence proves reachability of a code path, not its activation.",
            "R8 obfuscation renames classes; string literals survive but call sites may not.",
            "No dynamic execution: behaviour claims require on-device instrumentation.",
        ],
    }
    with open(os.path.join(OUT, "05_security.json"), "w") as fh:
        json.dump(result, fh, indent=1, ensure_ascii=False)
    for k, v in per_theme.items():
        print(f"{k:<28} count={v['count']:>7} unique={v['unique']:>6}")
    print("sdk:", [(r['sdk'], r['string_hits']) for r in sdk_rows[:6]])


if __name__ == "__main__":
    main()
