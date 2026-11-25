"""
Pure-Python Android DEX (Dalvik Executable) structural telemetry & SCA parser.
Reads binary DEX headers, string/type/class tables for architectural metrics,
software composition analysis (SCA), multi-DEX partitioning, and R8/ProGuard
identifier-minification statistics. Performs NO Dalvik bytecode disassembly or
source-code decompilation.
"""
from __future__ import annotations

import struct
from collections import Counter
from typing import Any, Dict, List, Tuple

ACC_PUBLIC = 0x0001
ACC_FINAL = 0x0010
ACC_INTERFACE = 0x0200
ACC_ABSTRACT = 0x0400
ACC_SYNTHETIC = 0x1000
ACC_ANNOTATION = 0x2000
ACC_ENUM = 0x4000


def read_uleb128(data: bytes, offset: int) -> Tuple[int, int]:
    result = 0
    shift = 0
    while offset < len(data):
        b = data[offset]
        offset += 1
        result |= (b & 0x7F) << shift
        if not (b & 0x80):
            break
        shift += 7
    return result, offset


def decode_dex_string(data: bytes, string_data_off: int) -> str:
    _, pos = read_uleb128(data, string_data_off)
    end = data.find(b"\x00", pos)
    if end == -1:
        end = len(data)
    return data[pos:end].decode("utf-8", errors="replace")


def classify_namespace(pkg: str) -> str:
    """Classify a Java/Kotlin package prefix into an architectural ecosystem domain."""
    if not pkg or pkg == "<default>":
        return "Minified / Root Package (R8)"

    parts = pkg.split(".")
    # Detect R8/ProGuard minified package names (e.g., a, o, p000a, defpackage, X, b.c)
    if len(parts[0]) <= 2 and parts[0] not in ("vn", "io", "ai", "rx", "ok", "ru"):
        return "Minified / Obfuscated (R8/ProGuard)"
    if parts[0] in ("defpackage", "p000a", "p001b"):
        return "Minified / Obfuscated (R8/ProGuard)"
    if len(parts) >= 2 and all(len(p) <= 2 for p in parts[:2]) and parts[0] not in ("vn", "io"):
        return "Minified / Obfuscated (R8/ProGuard)"

    low = pkg.lower()

    # First-party & VNG/Zalo ecosystem
    if low.startswith((
        "vn.com.vng.zalopay",
        "vn.zalopay",
        "com.zalopay",
        "vn.com.zalopay",
        "com.vng.zalopay",
        "com.zion",
        "vn.zion",
    )):
        return "First-Party: ZaloPay Core"
    if low.startswith((
        "vn.com.vng",
        "com.vng",
        "com.zing",
        "com.zalo",
        "vn.zalo",
        "me.zalo",
        "com.adtima",
        "com.zmp",
    )):
        return "VNG / Zalo Ecosystem SDKs"

    # AndroidX / Jetpack / Android Support
    if low.startswith(("androidx.", "android.support.", "com.google.android.material")):
        return "AndroidX / Jetpack & Material"
    if low.startswith(("android.", "dalvik.", "java.", "javax.", "sun.", "org.w3c", "org.xml", "org.json")):
        return "Android / JVM Platform Shims"

    # Kotlin & Reactive / Concurrency
    if low.startswith(("kotlin.", "kotlinx.")):
        return "Kotlin Stdlib & Coroutines"
    if low.startswith(("io.reactivex", "rx.", "org.reactivestreams", "dagger.", "javax.inject", "org.koin")):
        return "Reactive, DI & Concurrency"

    # Google Play Services & Firebase
    if low.startswith((
        "com.google.firebase",
        "com.google.android.gms",
        "com.google.android.play",
        "com.google.android.datatransport",
        "com.google.mlkit",
        "com.google.android.libraries",
        "com.google.android.ump",
    )):
        return "Google Play Services & Firebase"
    if low.startswith(("com.google.protobuf", "com.google.gson", "com.google.common", "com.google.crypto", "com.google.zxing", "com.google.flatbuffers", "com.google.errorprone")):
        return "Google Core Libraries (Protobuf/Gson/Guava/ZXing)"

    # Hybrid / Cross-platform / Web / Mini-App Engines
    if low.startswith((
        "io.flutter",
        "com.facebook.react",
        "com.facebook.hermes",
        "com.facebook.yoga",
        "com.facebook.soloader",
        "com.facebook.jni",
        "com.facebook.fresco",
        "org.chromium",
        "com.tencent.smtt",
        "com.bytedance",
        "io.capacitor",
        "org.apache.cordova",
        "com.swmansion",
        "com.reactnative",
    )):
        return "Hybrid / Cross-Platform & UI Runtimes"

    # Networking & RPC
    if low.startswith((
        "okhttp3",
        "okio",
        "retrofit2",
        "io.grpc",
        "io.netty",
        "org.apache.http",
        "com.squareup.okhttp",
        "com.squareup.okio",
        "com.squareup.wire",
        "com.squareup.moshi",
        "com.android.volley",
        "org.java_websocket",
        "io.socket",
    )):
        return "Networking, HTTP/2, gRPC & Serialization"

    # Media, Graphics & Animations
    if low.startswith((
        "com.airbnb.lottie",
        "com.bumptech.glide",
        "com.google.android.exoplayer2",
        "androidx.media3",
        "com.squareup.picasso",
        "coil.",
        "org.webrtc",
        "com.caverock.androidsvg",
        "jp.wasabeef",
        "com.github.bumptech",
        "pl.droidsonroids.gif",
    )):
        return "Media, Imaging, Lottie & WebRTC"

    # Analytics, Attribution, Ads & Observability
    if low.startswith((
        "com.appsflyer",
        "com.adjust",
        "com.facebook.",
        "com.clevertap",
        "com.mixpanel",
        "com.amplitude",
        "io.sentry",
        "com.datadog",
        "com.newrelic",
        "com.braze",
        "com.onesignal",
        "com.huawei.",
        "com.samsung.",
        "com.xiaomi.",
        "com.heytap",
        "com.vivo.",
        "com.oppo.",
        "io.branch",
        "com.moengage",
        "com.insider",
        "com.useinsider",
        "com.urbanairship",
    )):
        return "Analytics, Attribution, Push & OEM SDKs"

    # Biometrics, CV, OCR, ML, Security & Payments
    if low.startswith((
        "org.tensorflow",
        "com.googlecode.tesseract",
        "org.opencv",
        "com.journeyapps",
        "com.budiyev",
        "me.dm7",
        "com.hyperverge",
        "com.ekyc",
        "com.iproov",
        "com.facetec",
        "com.jumio",
        "com.onfido",
        "com.veriff",
        "com.sensetime",
        "com.megvii",
        "com.vnpt",
        "com.fpt",
        "org.bouncycastle",
        "org.spongycastle",
        "org.conscrypt",
        "net.sqlcipher",
        "com.scottyab",
        "com.emv",
        "com.visa",
        "com.mastercard",
        "com.napas",
        "com.cybersource",
        "com.stripe",
        "com.adyen",
        "com.cardinalcommerce",
        "com.threatmetrix",
        "com.lexisnexis",
        "com.iovation",
        "com.trustdecision",
        "cn.tongdun",
        "com.pay",
    )):
        return "Fintech, Biometrics/ML, Crypto & Compliance SDKs"

    # Storage & Databases
    if low.startswith((
        "io.realm",
        "io.objectbox",
        "com.tencent.mmkv",
        "org.greenrobot",
        "com.j256",
        "com.github.benmanes",
        "org.sqlite",
    )):
        return "Local Storage & High-Speed KV Engines"

    return "Other Third-Party / Utility Libraries"


def is_minified_identifier(class_desc: str) -> bool:
    """Heuristic check for R8/ProGuard minified class descriptor (e.g., La/b/c; or Lcom/foo/a;)."""
    if not class_desc.startswith("L") or not class_desc.endswith(";"):
        return False
    inner = class_desc[1:-1]
    parts = inner.split("/")
    simple = parts[-1].split("$")[0]
    if len(parts) == 1 and len(simple) <= 3:
        return True
    if len(parts[0]) <= 2 and parts[0] not in ("vn", "io", "ai", "rx", "ok"):
        return True
    if len(simple) <= 2 and simple.islower():
        return True
    if len(simple) == 1 and simple.isalpha():
        return True
    return False


def parse_dex_structural(data: bytes, name: str) -> Dict[str, Any]:
    """Parse structural metadata, table metrics, and package taxonomy from a single DEX file."""
    if len(data) < 112 or not data.startswith(b"dex\n"):
        return {"name": name, "valid": False}

    version = data[4:7].decode("ascii", errors="replace")
    (
        file_size,
        header_size,
        endian_tag,
        link_size,
        link_off,
        map_off,
        string_ids_size,
        string_ids_off,
        type_ids_size,
        type_ids_off,
        proto_ids_size,
        proto_ids_off,
        field_ids_size,
        field_ids_off,
        method_ids_size,
        method_ids_off,
        class_defs_size,
        class_defs_off,
        data_size,
        data_off,
    ) = struct.unpack_from("<20I", data, 32)

    # Pre-read only string offsets needed by type_ids
    type_descriptors: List[str] = []
    if type_ids_size > 0 and type_ids_off + type_ids_size * 4 <= len(data):
        type_str_indices = struct.unpack_from(f"<{type_ids_size}I", data, type_ids_off)
        for s_idx in type_str_indices:
            if s_idx < string_ids_size:
                str_data_off = struct.unpack_from("<I", data, string_ids_off + s_idx * 4)[0]
                type_descriptors.append(decode_dex_string(data, str_data_off))
            else:
                type_descriptors.append("")

    domain_counts: Counter = Counter()
    prefix2_counts: Counter = Counter()
    prefix3_counts: Counter = Counter()
    role_counts: Counter = Counter()
    minified_classes = 0
    inner_classes = 0
    total_classes = 0
    defined_methods_total = 0
    defined_fields_total = 0

    for i in range(class_defs_size):
        off = class_defs_off + i * 32
        if off + 32 > len(data):
            break
        class_idx, access_flags, super_idx, iface_off, src_idx, annot_off, class_data_off, static_off = (
            struct.unpack_from("<8I", data, off)
        )
        total_classes += 1
        desc = type_descriptors[class_idx] if class_idx < len(type_descriptors) else ""

        if access_flags & ACC_INTERFACE:
            role_counts["interface"] += 1
        elif access_flags & ACC_ENUM:
            role_counts["enum"] += 1
        elif access_flags & ACC_ANNOTATION:
            role_counts["annotation"] += 1
        elif access_flags & ACC_ABSTRACT:
            role_counts["abstract_class"] += 1
        else:
            role_counts["concrete_class"] += 1

        if access_flags & ACC_SYNTHETIC:
            role_counts["synthetic"] += 1

        if "$" in desc:
            inner_classes += 1
        if is_minified_identifier(desc):
            minified_classes += 1

        # Extract package prefix
        if desc.startswith("L") and desc.endswith(";"):
            parts = desc[1:-1].split("/")
            pkg = ".".join(parts[:-1]) if len(parts) > 1 else "<default>"
            domain = classify_namespace(pkg)
            domain_counts[domain] += 1
            if len(parts) >= 3:
                prefix2_counts[".".join(parts[:2])] += 1
                prefix3_counts[".".join(parts[:3])] += 1
            elif len(parts) == 2:
                prefix2_counts[parts[0]] += 1
                prefix3_counts[parts[0]] += 1
            else:
                prefix2_counts["<root>"] += 1
                prefix3_counts["<root>"] += 1

        # Read ONLY the 4 ULEB128 header counts in class_data_item (no bytecode!)
        if class_data_off and class_data_off < len(data):
            p = class_data_off
            sf, p = read_uleb128(data, p)
            if_, p = read_uleb128(data, p)
            dm, p = read_uleb128(data, p)
            vm, p = read_uleb128(data, p)
            defined_fields_total += sf + if_
            defined_methods_total += dm + vm

    return {
        "name": name,
        "valid": True,
        "dex_version": version,
        "file_size_bytes": file_size,
        "string_ids": string_ids_size,
        "type_ids": type_ids_size,
        "proto_ids": proto_ids_size,
        "field_ids": field_ids_size,
        "method_ids": method_ids_size,
        "class_defs": class_defs_size,
        "data_size_bytes": data_size,
        "method_64k_utilization_pct": round((method_ids_size / 65536.0) * 100.0, 2),
        "field_64k_utilization_pct": round((field_ids_size / 65536.0) * 100.0, 2),
        "type_64k_utilization_pct": round((type_ids_size / 65536.0) * 100.0, 2),
        "defined_methods_total": defined_methods_total,
        "defined_fields_total": defined_fields_total,
        "minified_classes": minified_classes,
        "minified_pct": round((minified_classes / max(1, total_classes)) * 100.0, 2),
        "inner_classes": inner_classes,
        "roles": dict(role_counts),
        "domains": dict(domain_counts),
        "top_prefix2": dict(prefix2_counts.most_common(40)),
        "top_prefix3": dict(prefix3_counts.most_common(60)),
    }
