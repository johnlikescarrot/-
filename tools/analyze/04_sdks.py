#!/usr/bin/env python3
"""Third-party SDK / framework inventory by multi-signal fingerprinting."""
from __future__ import annotations
import json, os, re
from collections import Counter

ROOT = os.path.dirname(os.path.dirname(os.path.dirname(os.path.abspath(__file__))))
DATA = os.path.join(ROOT, "data")

# (vendor, category, [package prefixes], [supporting string markers])
FINGERPRINTS = [
    ("Google Play services / GMS", "Platform", ["Lcom/google/android/gms/"], ["com.google.android.gms.version"]),
    ("Google Firebase", "Analytics/Backend", ["Lcom/google/firebase/"], ["firebase"]),
    ("Firebase Crashlytics", "Crash reporting", ["Lcom/google/firebase/crashlytics/"], ["crashlytics"]),
    ("ML Kit (barcode/OCR/face)", "On-device ML", ["Lcom/google/mlkit/", "Lcom/google/android/gms/vision/"],
     ["mlkit-google-ocr-models", "libbarhopper_v3.so"]),
    ("AndroidX / Jetpack", "AndroidX", ["Landroidx/"], ["androidx"]),
    ("Jetpack Compose", "UI toolkit", ["Landroidx/compose/"], ["androidx.compose"]),
    ("Kotlin stdlib/coroutines", "Language runtime", ["Lkotlin/", "Lkotlinx/"], ["kotlin.Metadata"]),
    ("OkHttp 3/4", "Networking", ["Lokhttp3/", "Lokio/"], ["okhttp3"]),
    ("Retrofit", "Networking", ["Lretrofit2/"], ["retrofit2"]),
    ("gRPC / Protobuf", "RPC", ["Lio/grpc/", "Lcom/google/protobuf/"], ["io.grpc"]),
    ("BouncyCastle", "Cryptography", ["Lorg/bouncycastle/"], ["BouncyCastle"]),
    ("Nimbus JOSE+JWT", "Cryptography", ["Lcom/nimbusds/"], ["nimbusds"]),
    ("Alipay SDK", "Payments", ["Lcom/alipay/", "Lcom/alipay/sdk/"], ["alipay"]),
    ("Visa CyberSource (CBP)", "Payments", ["Lcom/visa/cbp/"], ["visa"]),
    ("VNPAY SDK", "Payments", ["Lcom/vnpay/"], ["vnpay"]),
    ("IAP (VNG in-app purchase)", "Payments", ["Lcom/iap/"], ["iapconnect"]),
    ("Facebook SDK", "Social/Analytics", ["Lcom/facebook/"], ["fbconnect"]),
    ("AppsFlyer", "Attribution", ["Lcom/appsflyer/"], ["appsflyer"]),
    ("Adjust", "Attribution", ["Lcom/adjust/"], ["adjust"]),
    ("Branch.io", "Deep linking", ["Lio/branch/"], ["onelink"]),
    ("Alibaba ARouter / mPaaS", "Modularisation", ["Lcom/alibaba/"], ["arouter"]),
    ("UC/Griver WebView container", "WebView", ["Lcom/alibaba/griver/", "Lcom/uc/"], ["GriverBridge", "jsbridge.js"]),
    ("Zalo (Zing) third-party auth", "Social/Payments", ["Lcom/zing/", "Lcom/vng/"], ["com.zing.zalo"]),
    ("Glide", "Imaging", ["Lcom/bumptech/glide/"], ["glide"]),
    ("Fresco / ImagePipeline", "Imaging", ["Lcom/facebook/imagepipeline/"], ["libimagepipeline.so"]),
    ("Lottie", "Animation", ["Lcom/airbnb/lottie/"], ["lottie"]),
    ("Room / SQLite", "Storage", ["Landroidx/room/"], ["room"]),
    ("SQLCipher", "Storage encryption", ["Lnet/sqlcipher/", "Lnet/zetetic/"], ["libsqlcipher.so"]),
    ("Realm", "Storage", ["Lio/realm/"], ["realm"]),
    ("Dagger/Hilt", "DI", ["Ldagger/"], ["dagger"]),
    ("RxJava", "Async", ["Lio/reactivex/"], ["rxjava"]),
    ("Media3 / ExoPlayer", "Media", ["Landroidx/media3/"], ["media3"]),
    ("CameraX", "Camera", ["Landroidx/camera/"], ["camerax"]),
    ("Google Maps / Places", "Maps", ["Lcom/google/android/gms/maps/", "Lcom/google/android/libraries/places/"], ["maps"]),
    ("ZXing", "Barcode", ["Lcom/google/zxing/"], ["zxing"]),
    ("TensorFlow Lite / NCNN", "On-device ML", ["Lorg/tensorflow/", "Lcom/tencent/ncnn/"], ["FastestDet.ncnn.bin", "libpicodet_ncnn.so"]),
    ("Google Play Billing", "Commerce", ["Lcom/android/billingclient/"], ["com.android.vending.BILLING"]),
    ("Play Core (split/asset delivery)", "Platform", ["Lcom/google/android/play/core/"], ["play.core"]),
    ("Samsung/Huawei/OEM push", "Push", ["Lcom/huawei/hms/", "Lcom/samsung/"], ["huawei"]),
    ("Tencent Bugly / X5", "WebView/Crash", ["Lcom/tencent/"], ["tencent"]),
    ("JUnit / test artifacts leaked", "Build hygiene", ["Lorg/junit/", "Ljunit/"], ["LICENSE-junit.txt"]),
    ("Apache Commons Codec", "Utilities", ["Lorg/apache/commons/codec/"], ["commons"]),
    ("Bundletool signature artefacts", "Build", [], ["BNDLTOOL.RSA"]),
    ("Bangcle/SecNeo-style app shielding", "App hardening", [], ["bshield.dat", "libsheild"]),
    ("Rive", "Animation", ["Lapp/rive/"], ["librive-android.so"]),
    ("PicoDet / NCNN vision", "On-device ML", [], ["libpicodet_ncnn.so"]),
    ("VFace / face liveness", "Biometrics", [], ["libVFaceLib.so", "libface_detector_v2_jni.so"]),
    ("TrueID / eKYC", "Identity", [], ["libtrueidlib.so"]),
]


def load_classes() -> list[str]:
    with open(os.path.join(DATA, "03-dex.json")) as fh:
        return [c["name"] for c in json.load(fh)["classes"]]


def main() -> None:
    classes = load_classes()
    with open(os.path.join(ROOT, "work/all-strings.txt"), encoding="utf-8", errors="replace") as fh:
        strings = [line.split("\t", 1)[1] if "\t" in line else line for line in fh]
        strings = [s.strip() for s in strings]
    str_blob = "\n".join(strings).lower()
    libs = sorted(os.listdir(os.path.join(ROOT, "work/apk/lib"))) if os.path.isdir(os.path.join(ROOT, "work/apk/lib")) else []
    split_libs = []
    for abi in ("armeabi-v7a",):
        p = os.path.join(ROOT, "work/lib", abi)
        if os.path.isdir(p):
            split_libs += os.listdir(p)
    assets = os.listdir(os.path.join(ROOT, "work/apk/assets"))
    inventory = []
    for vendor, category, prefixes, markers in FINGERPRINTS:
        prefix_counts = {p: sum(1 for c in classes if c.startswith(p)) for p in prefixes}
        hits = [p for p, n in prefix_counts.items() if n]
        clsv = sum(prefix_counts.values())
        marker_hits = [m for m in markers if m.lower() in str_blob or m in assets or m in split_libs]
        if prefixes and not hits:
            continue
        if not prefixes and not marker_hits:
            continue
        inventory.append({"vendor": vendor, "category": category,
                          "evidence": {"class_prefixes": hits, "class_count": clsv,
                                       "markers": marker_hits},
                          "confidence": "high" if (hits and marker_hits) or clsv > 200 else "medium"})
    inventory.sort(key=lambda i: -i["evidence"]["class_count"])
    domains = Counter()
    for s in strings:
        for m in re.finditer(r"https?://([A-Za-z0-9._-]+\.[A-Za-z]{2,})", s):
            domains[m.group(1).lower()] += 1
    report = {"inventory": inventory,
              "sdk_count": len(inventory),
              "host_domain_count": len(domains),
              "top_hosts": domains.most_common(60),
              "assets_entries": len(assets), "assets_sample": sorted(assets)[:40],
              "native_libs": split_libs}
    with open(os.path.join(DATA, "04-sdks.json"), "w") as fh:
        json.dump(report, fh, indent=2, ensure_ascii=False)
    for item in inventory:
        print(f"{item['evidence']['class_count']:>7}  {item['vendor'][:44]:<44} {item['evidence']['markers'][:2]}")
    print("\nhosts:", report["host_domain_count"], report["top_hosts"][:25])


if __name__ == "__main__":
    main()
