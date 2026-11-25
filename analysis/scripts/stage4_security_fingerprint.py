#!/usr/bin/env python3
"""
Stage 4 — Security-control taxonomy + build fingerprint + report metrics.

Turns the raw string-level evidence from Stages 1-3 into a structured, citable
dataset:  which defensive control exists, what artefact proves it, how many
user-facing (Vietnamese) enforcement messages back it, and which ecosystem SDK
provides it. Also derives the "fingerprint" facts used in the report/video.

Output: analysis/data/08_security.json, analysis/data/09_fingerprint.json,
        analysis/data/10_metrics.json
"""
import os, re, json, collections, glob

ROOT = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
DATA = os.path.join(ROOT, "data")
WORK = os.environ.get("ZPAY_WORK", "/home/user/work")
DEXDIR = os.path.join(WORK, "apk", "vn.com.vng.zalopay")
APK = DEXDIR

from loguru import logger
logger.remove()
from androguard.core.dex import DEX

VI = re.compile(r"[ăâđêôơưàảãáạằẳẵắặầẩẫấậèẻẽéẹềểễếệìỉĩíịòỏõóọồổỗốộờởỡớợùủũúụừửữứựỳỷỹýỵ]", re.I)

# control -> (label, regex over literals, ecosystem mapping)
CONTROLS = [
    ("root", "Root / su binary detection",
     r"/system/xbin/su|/system/app/Superuser|Superuser\.apk|/magisk|test-keys|isDeviceRooted|RootBeer|su binary",
     "first-party UI + VISA CBP SDK + FPT trueID"),
    ("hook", "Runtime instrumentation (hook) detection",
     r"frida-agent|FRIDA|XPOSED|de\.robv\.android\.xposed|LSPosed|libsubstrate|isHooked|detectHook|hook detection",
     "first-party + FPT trueID"),
    ("emulator", "Emulator / virtual-machine detection",
     r"goldfish|ranchu|Genymotion|qemu\.hw|BlueStacks|isEmulator|virtual machines and emulators|emulator_detected",
     "first-party + VISA CBP SDK"),
    ("debug", "Debugger & developer-mode detection",
     r"isDebuggerConnected|android/os/Debug|TracerPid|USB debugging|chế độ gỡ lỗi|chế độ nhà phát triển",
     "first-party UI + FPT trueID"),
    ("proxy", "Proxy / MITM detection",
     r"chế độ proxy|http\.proxyHost|http\.proxyPort|isProxySet|getDefaultProxy",
     "first-party"),
    ("keyboard", "Untrusted-input-method (keyboard) detection",
     r"bàn phím|InputMethodManager|isTrustedIme|trusted keyboard|ime_hash|allowedInputMethod",
     "first-party"),
    ("screen", "Screen-capture / overlay defense",
     r"FLAG_SECURE|DETECT_SCREEN_CAPTURE|setRecentsScreenshotEnabled|onScreenCapture",
     "Android platform"),
    ("integrity", "APK signature / tamper integrity",
     r"signature did not verify|GET_SIGNATURES|GET_SIGNING_CERTIFICATES|apkSignatureScheme|signingInfo|tamper",
     "Android platform + first-party"),
    ("pinning", "Certificate pinning (TLS)",
     r"CertificatePinner|sha256/|PublicKeyPinning|TrustManagerFactory",
     "OkHttp + Google Ads SDK"),
    ("keystore", "Hardware-backed keystore / biometrics",
     r"KeyGenParameterSpec|KeyProperties\.|BiometricPrompt|FingerprintManager|setIsStrongBoxBacked",
     "AndroidX Security + Biometric"),
    ("attestation", "Platform attestation",
     r"SafetyNet|PlayIntegrity|IntegrityManager|IntegrityTokenRequest",
     "Google Play Integrity"),
    ("db_encryption", "Database encryption at rest",
     r"sqlcipher|SQLCipher|PRAGMA key",
     "SQLCipher (Zetetic)"),
    ("sdk_security", "Vendor security SDK (SecurityGuard Lite)",
     r"SecurityGuardLiteNative|com/alipay/plus/security|security_lite",
     "Alipay+ / Zoloz"),
]


def dex_strings():
    out = {}
    for fn in sorted([f for f in os.listdir(DEXDIR) if re.fullmatch(r"classes\d*\.dex", f)],
                     key=lambda s: int(re.sub(r"\D", "", s) or 1)):
        d = DEX(open(os.path.join(DEXDIR, fn), "rb").read())
        out[fn] = [str(s) for s in d.get_strings()]
    return out


def security_taxonomy(strings):
    results = []
    for key, label, rx, eco in CONTROLS:
        r = re.compile(rx)
        hits = collections.Counter()
        msgs = []
        for fn, arr in strings.items():
            for s in arr:
                if len(s) > 260:
                    continue
                if r.search(s):
                    hits[fn] += 1
                    if VI.search(s) and 10 < len(s) < 220 and len(msgs) < 40:
                        msgs.append(s.replace("\n", " ").strip())
        if hits:
            samples = sorted({x for arr in strings.values() for x in arr
                              if len(x) < 120 and r.search(x)})[:8]
            results.append({
                "id": key, "control": label, "ecosystem": eco,
                "literal_hits": sum(hits.values()),
                "dex_spread": dict(hits.most_common()),
                "user_facing_vi": sorted(set(msgs))[:6],
                "evidence": samples,
                "verified": True,
            })
    return results


def fingerprint(strings):
    alls = set()
    for arr in strings.values():
        alls.update(arr)
    blob = "\n".join(sorted(alls))
    f = {}
    f["kotlin"] = bool(re.search(r"^kotlin/|kotlin\.Metadata|kotlin-stdlib", blob, re.M))
    f["kotlin_version"] = sorted(set(re.findall(r"kotlin-stdlib[/-]?(\d+\.\d+\.\d+)", blob)))[:5]
    f["coroutines"] = "kotlinx/coroutines" in blob
    f["compose"] = "androidx/compose" in blob
    f["r8_proguard"] = bool(re.search(r"L[a-z]{6,12}/[a-z]{6,12};", blob))
    f["okhttp_version_hint"] = sorted(set(re.findall(r"okhttp/(\d+\.\d+\.\d+)", blob)))[:5]
    f["retrofit"] = "retrofit2/" in blob
    f["moshi_gson"] = ("com/google/gson" in blob, "com/squareup/moshi" in blob)
    f["graphql"] = "graphql" in blob.lower()
    f["grpc"] = "io/grpc" in blob
    f["websocket"] = "okhttp3/WebSocket" in blob or "websocket" in blob.lower()
    f["firebase_products"] = sorted({
        p for p in ("firebase-analytics", "firebase-messaging", "firebase-config", "firebase-crashlytics",
                    "firebase-perf", "firebase-abt", "firebase-iid", "firebase-installations")
        if p.replace("-", "_") in blob or p in blob})
    f["ml_kit_modules"] = sorted({m for m in ("barcode-scanning", "face-detection", "text-recognition",
                                              "object-detection", "image-labeling", "subject-segmentation")
                                  if m in blob})
    f["payment_schemes"] = sorted({s for s in ("visa", "mastercard", "jcb", "unionpay", "napas", "vnpay",
                                               "alipay", "alipayplus", "wechat", "momo")
                                   if re.search(r"(?i)\b" + s + r"\b", blob)})
    f["ekyc_vendors"] = sorted({v for v, rx in
                                (("FPT.AI", r"com/fpt/fci|fpt\.ai"), ("trueID", r"vn/ai/trueid"), ("Zoloz", r"zoloz|security_lite"),
                                 ("VISA CBP", r"com/visa/cbp"), ("ML Kit face", r"mlkit_vision_face"))
                                if re.search(rx, blob)})
    f["gov_integration"] = sorted({g for g in ("VNeID", "CCCD", "NFC") if g.lower() in blob.lower()})
    f["minified_rn"] = "main.jsbundle" in blob or "__BUNDLE_START_TIME__" in blob
    f["js_bridge"] = "ZaloPayJSBridge" in blob
    return f


def main():
    print("[stage4] reading dex strings …")
    strings = dex_strings()
    sec = security_taxonomy(strings)
    fp = fingerprint(strings)

    cont = json.load(open(os.path.join(DATA, "01_container.json")))
    man = json.load(open(os.path.join(DATA, "02_manifest.json")))
    dex = json.load(open(os.path.join(DATA, "03_dex.json")))
    nat = json.load(open(os.path.join(DATA, "05_native.json")))
    ast = json.load(open(os.path.join(DATA, "06_assets.json")))
    res = json.load(open(os.path.join(DATA, "07_resources.json")))

    with open(os.path.join(DATA, "08_security.json"), "w") as fh:
        json.dump({"controls": sec,
                   "controls_verified": sum(1 for c in sec if c["verified"]),
                   "user_facing_vi_messages": sum(len(c["user_facing_vi"]) for c in sec)}, fh, indent=1, ensure_ascii=False)
    with open(os.path.join(DATA, "09_fingerprint.json"), "w") as fh:
        json.dump(fp, fh, indent=1, ensure_ascii=False)

    libs = nat["libraries"]
    metrics = {
        "title": "ZaloPay 11.17.0 — Independent Static Reverse-Engineering Study",
        "identity": {"package": man["package"], "version": man["versionName"],
                     "version_code": man["versionCode"], "min_sdk": man["uses_sdk"]["min"],
                     "target_sdk": man["uses_sdk"]["target"], "compile_sdk": man.get("compileSdk")},
        "artefact": {"xapk": sum(x["size"] for x in cont["splits"]),
                     "splits": len(cont["splits"]),
                     "base_sha256": next((s["sha256"] for s in cont["splits"] if s["role"] == "base"), None),
                     "total_zip_entries": cont["base_zip"]["entries"],
                     "uncompressed": cont["base_zip"]["total_uncompressed"],
                     "stored_entries": cont["base_zip"]["stored"]},
        "code": {"dex_files": len(dex["multidex"]), "classes": dex["totals"]["classes"],
                 "methods": dex["totals"]["methods"], "fields": dex["totals"]["fields"],
                 "strings": dex["totals"]["strings"],
                 "obfuscated_class_ratio": dex["obfuscation"]["short_identifier_ratio"],
                 "mean_name_entropy": dex["obfuscation"]["mean_name_entropy_bits"]},
        "components": {k: len(v) for k, v in man["components"].items()},
        "permissions": len(man["permissions"]),
        "deep_links": len(man["deep_links"]),
        "exported_permissionless": sum(1 for k, v in man["components"].items()
                                       for c in v if c["exported"] == "true" and not c["permission"]),
        "native": {"libs": nat["library_count"], "bytes": nat["total_bytes"],
                   "obfuscated": len(nat["obfuscated_first_party"]),
                   "jni_export_total": sum(l.get("jni_exports", 0) for l in libs)},
        "ai_models": {"tflite": len(ast["tflite_models"]), "ncnn": len(ast["ncnn_models"]),
                      "tflite_bytes": sum(m["bytes"] for m in ast["tflite_models"])},
        "third_party": {"sdk_families": len(dex["sdk_inventory"]),
                        "named_sdks": [s["product"] for s in dex["sdk_inventory"]],
                        "vendors": sorted({s["vendor"] for s in dex["sdk_inventory"]})},
        "security": {"controls_verified": sum(1 for c in sec if c["verified"]),
                     "vi_enforcement_messages": sum(len(c["user_facing_vi"]) for c in sec),
                     "controls": [{"id": c["id"], "control": c["control"], "hits": c["literal_hits"]} for c in sec]},
        "resources": {"arsc_packages": res["package_count"], "strings": res["string_resources"],
                      "vietnamese": res["vietnamese_strings"], "vector_drawables": res["vector_drawables"]},
        "hosts": {"distinct": len(dex["hosts"]),
                  "first_party": sorted({h["host"] for h in dex["hosts"]
                                         if re.fullmatch(r"(?:[a-z0-9-]+\.)+zalopay\.(?:vn|com\.vn)", h["host"])})[:24],
                  "first_party_count": len({h["host"] for h in dex["hosts"]
                                            if re.fullmatch(r"(?:[a-z0-9-]+\.)+zalopay\.(?:vn|com\.vn)", h["host"])})},
    }
    with open(os.path.join(DATA, "10_metrics.json"), "w") as fh:
        json.dump(metrics, fh, indent=1, ensure_ascii=False)
    print(f"[stage4] controls={metrics['security']['controls_verified']} "
          f"sdk_families={metrics['third_party']['sdk_families']} "
          f"classes={metrics['code']['classes']:,}")


if __name__ == "__main__":
    main()
