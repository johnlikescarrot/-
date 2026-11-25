#!/usr/bin/env python3
"""Reproducible, evidence-preserving static analysis of the supplied XAPK.

Scope (deliberate): archive inventory, manifest metadata, Android components,
permission surface, DEX/native/resource structure, third-party library markers
and signing metadata. The script deliberately does NOT dump decompiled source
code, extract credentials, or attempt to defeat any application protection.

Usage:
    python3 -m venv .venv && .venv/bin/pip install -r analysis/requirements.txt
    .venv/bin/python analysis/analyze.py --xapk .work/app.xapk \
        --outer 'Zalopay-Thanh+toán+&+Tài+chính_11.17.0_APKPure.zip' \
        --out analysis/findings.json --report analysis/REPORT.md
"""

from __future__ import annotations

import argparse
import hashlib
import json
import re
import struct
import zipfile
from collections import Counter
from datetime import datetime, timezone
from pathlib import Path
from xml.etree import ElementTree as ET

from loguru import logger

logger.remove()  # androguard is extremely verbose by default

from androguard.core.apk import APK  # noqa: E402
from androguard.core.axml import AXMLPrinter  # noqa: E402

ANDROID_NS = "{http://schemas.android.com/apk/res/android}"

DANGEROUS_PERMISSIONS = {
    "android.permission.READ_CALENDAR", "android.permission.WRITE_CALENDAR",
    "android.permission.CAMERA", "android.permission.READ_CONTACTS",
    "android.permission.WRITE_CONTACTS", "android.permission.ACCESS_FINE_LOCATION",
    "android.permission.ACCESS_COARSE_LOCATION", "android.permission.RECORD_AUDIO",
    "android.permission.READ_PHONE_STATE", "android.permission.READ_PHONE_NUMBERS",
    "android.permission.CALL_PHONE", "android.permission.ANSWER_PHONE_CALLS",
    "android.permission.READ_SMS", "android.permission.SEND_SMS",
    "android.permission.RECEIVE_SMS", "android.permission.READ_EXTERNAL_STORAGE",
    "android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.READ_MEDIA_AUDIO",
    "android.permission.READ_MEDIA_IMAGES", "android.permission.READ_MEDIA_VIDEO",
    "android.permission.POST_NOTIFICATIONS", "android.permission.BODY_SENSORS",
    "android.permission.ACCESS_BACKGROUND_LOCATION", "android.permission.USE_BIOMETRIC",
    "android.permission.USE_FINGERPRINT", "android.permission.NFC",
}

# Byte markers used only as *counts* of technology adoption, never for code extraction.
LIBRARY_MARKERS = {
    "Kotlin stdlib": [b"Lkotlin/"],
    "Kotlin coroutines": [b"Lkotlinx/coroutines/"],
    "AndroidX": [b"Landroidx/"],
    "OkHttp": [b"Lokhttp3/"],
    "Retrofit": [b"Lretrofit2/", b"Lcom/squareup/retrofit2/"],
    "Gson": [b"Lcom/google/gson/"],
    "Moshi": [b"Lcom/squareup/moshi/"],
    "RxJava": [b"Lio/reactivex/"],
    "Dagger / Hilt": [b"Ldagger/", b"Ldagger/hilt/"],
    "Room": [b"Landroidx/room/"],
    "WorkManager": [b"Landroidx/work/"],
    "Firebase": [b"Lcom/google/firebase/"],
    "Crashlytics": [b"Lcom/google/firebase/crashlytics/"],
    "Google Play services": [b"Lcom/google/android/gms/"],
    "Facebook SDK": [b"Lcom/facebook/"],
    "Zalo SDK": [b"Lcom/zing/zalo/"],
    "React Native": [b"Lcom/facebook/react/"],
    "Flutter": [b"Lio/flutter/"],
    "Glide": [b"Lcom/bumptech/glide/"],
    "Coil": [b"Lcoil/"],
    "MMKV": [b"Lcom/tencent/mmkv/"],
    "Protobuf": [b"Lcom/google/protobuf/"],
    "WebView / Chromium": [b"Lcom/google/android/gms/internal/", b"Landroid/webkit/"],
    "Biometric prompt": [b"Landroidx/biometric/"],
}


def sha256(path: Path) -> str:
    digest = hashlib.sha256()
    with path.open("rb") as handle:
        for block in iter(lambda: handle.read(1024 * 1024), b""):
            digest.update(block)
    return digest.hexdigest()


def dex_header_stats(blob: bytes) -> dict:
    """Read only the DEX header (no code parsing, no decompilation)."""
    if len(blob) < 112 or blob[:3] != b"dex":
        return {"valid": False}
    (endian,) = struct.unpack_from("<I", blob, 40)
    fmt = "<" if endian == 0x12345678 else ">"
    fields = ("string_ids_size", "type_ids_size", "proto_ids_size",
              "field_ids_size", "method_ids_size", "class_defs_size")
    out = {"valid": True, "magic": blob[:8].decode("ascii", "replace"),
           "file_size_declared": struct.unpack_from(fmt + "I", blob, 32)[0]}
    for i, name in enumerate(fields):
        out[name] = struct.unpack_from(fmt + "I", blob, 56 + i * 8)[0]
    return out


def manifest_tree(apk: APK) -> ET.Element:
    raw = apk.get_file("AndroidManifest.xml")
    xml = AXMLPrinter(raw).get_xml()
    return ET.fromstring(xml)


def attr(node: ET.Element, name: str, default=None):
    return node.get(ANDROID_NS + name, default)


def component_info(root: ET.Element) -> dict:
    app = root.find("application")
    app = app if app is not None else ET.Element("application")
    out = {"application_flags": {
        "allowBackup": attr(app, "allowBackup"),
        "debuggable": attr(app, "debuggable"),
        "usesCleartextTraffic": attr(app, "usesCleartextTraffic"),
        "requestLegacyExternalStorage": attr(app, "requestLegacyExternalStorage"),
        "largeHeap": attr(app, "largeHeap"),
        "networkSecurityConfig": attr(app, "networkSecurityConfig"),
    }}
    for tag in ("activity", "service", "receiver", "provider"):
        nodes = app.findall(tag)
        exported = 0
        with_filter = 0
        for node in nodes:
            filters = node.findall("intent-filter")
            if filters:
                with_filter += 1
            flag = attr(node, "exported")
            if flag == "true" or (flag is None and filters):
                exported += 1
        out[tag] = {"total": len(nodes), "exported_or_implicit": exported,
                    "with_intent_filter": with_filter}
    actions, categories, schemes, hosts = Counter(), Counter(), Counter(), Counter()
    for node in app.iter("intent-filter"):
        for child in node:
            if child.tag == "action":
                value = attr(child, "name", "")
                actions[value.split(".")[-1]] += 1
            elif child.tag == "category":
                categories[attr(child, "name", "").split(".")[-1]] += 1
            elif child.tag == "data":
                scheme = attr(child, "scheme")
                host = attr(child, "host")
                if scheme:
                    schemes[scheme] += 1
                if host:
                    hosts[host] += 1
    out["intent_filters"] = {
        "top_actions": actions.most_common(12),
        "top_categories": categories.most_common(12),
        "schemes": schemes.most_common(20),
        "distinct_hosts": len(hosts),
        "top_hosts": hosts.most_common(10),
    }
    return out


def certificate_info(apk: APK) -> list[dict]:
    result = []
    try:
        certs = apk.get_certificates() or []
    except Exception:
        certs = []
    for cert in certs:
        try:
            subject = cert.subject.human_friendly if hasattr(cert.subject, "human_friendly") else str(cert.subject)
            result.append({
                "subject": subject[:240],
                "sha256_fingerprint": getattr(cert, "sha256", b"").hex()[:32],
                "not_valid_before": str(getattr(cert, "not_valid_before", ""))[:32],
                "not_valid_after": str(getattr(cert, "not_valid_after", ""))[:32],
            })
        except Exception:
            continue
    return result


def resource_breakdown(zf: zipfile.ZipFile) -> dict:
    counts, sizes = Counter(), Counter()
    for info in zf.infolist():
        if info.is_dir():
            continue
        name = info.filename.rsplit("/", 1)[-1]
        ext = "." + name.rsplit(".", 1)[-1].lower() if "." in name else "(none)"
        if name.startswith("classes") and name.endswith(".dex"):
            ext = ".dex"
        counts[ext] += 1
        sizes[ext] += info.file_size
    return {
        "entries": sum(counts.values()),
        "by_count": counts.most_common(15),
        "by_bytes": sizes.most_common(15),
    }


def analyze_apk(path: Path) -> dict:
    apk = APK(str(path))
    root = manifest_tree(apk)
    permissions = sorted(set(apk.get_permissions() or []))
    with zipfile.ZipFile(path) as zf:
        dex_entries = [i for i in zf.infolist() if i.filename.startswith("classes") and i.filename.endswith(".dex")]
        dex_stats = []
        for info in dex_entries:
            stats = dex_header_stats(zf.read(info))
            stats["name"] = info.filename
            stats["bytes"] = info.file_size
            dex_stats.append(stats)
        native = [{"name": i.filename, "bytes": i.file_size}
                  for i in zf.infolist() if i.filename.endswith(".so")]
        markers = {k: 0 for k in LIBRARY_MARKERS}
        descriptor_total = 0
        short_descriptors = 0
        for info in dex_entries:
            blob = zf.read(info)
            for name, needles in LIBRARY_MARKERS.items():
                markers[name] += sum(blob.count(n) for n in needles)
            descriptor_total += len(re.findall(rb"L[a-zA-Z0-9_$]+/[a-zA-Z0-9_/$]+;", blob))
            short_descriptors += len(re.findall(rb"L[a-z]{1,3}/[a-z]{1,4};", blob))
        resources = resource_breakdown(zf)

    version_code = attr(root, "versionCode") or apk.get_androidversion_code()
    uses_sdk = root.find("uses-sdk")
    result = {
        "file": path.name,
        "bytes": path.stat().st_size,
        "sha256": sha256(path),
        "manifest": {
            "package": root.get("package"),
            "version_name": attr(root, "versionName"),
            "version_code": str(version_code),
            "min_sdk": attr(uses_sdk, "minSdkVersion") if uses_sdk is not None else None,
            "target_sdk": attr(uses_sdk, "targetSdkVersion") if uses_sdk is not None else None,
            "compile_sdk": attr(root, "compileSdkVersion"),
            "split_name": attr(root, "splitName"),
            "split_types": attr(root, "splitTypes"),
            "required_split_types": attr(root, "requiredSplitTypes"),
        },
        "permissions": {
            "total": len(permissions),
            "dangerous_count": sum(p in DANGEROUS_PERMISSIONS for p in permissions),
            "all": permissions,
        },
        "components": component_info(root),
        "dex": {
            "count": len(dex_entries),
            "bytes": sum(d["bytes"] for d in dex_stats),
            "class_defs": sum(d.get("class_defs_size", 0) for d in dex_stats),
            "method_ids": sum(d.get("method_ids_size", 0) for d in dex_stats),
            "string_ids": sum(d.get("string_ids_size", 0) for d in dex_stats),
            "files": dex_stats,
        },
        "native_libraries": {
            "count": len(native), "bytes": sum(n["bytes"] for n in native),
            "names": sorted({n["name"].rsplit("/", 1)[-1] for n in native})[:40],
        },
        "resources": resources,
        "technology_markers": {k: v for k, v in sorted(markers.items(), key=lambda kv: -kv[1]) if v},
        "dex_descriptor_metrics": {
            "descriptor_matches": descriptor_total,
            "very_short_descriptors": short_descriptors,
            "short_descriptor_ratio": round(short_descriptors / descriptor_total, 4) if descriptor_total else 0,
        },
        "signing": {
            "v1_signature_names": apk.get_signature_names() or [],
            "certificates": certificate_info(apk),
        },
    }
    return result


def build_report(data: dict) -> str:
    base = data["base_apk"]
    m = base["manifest"]
    agg = data["aggregate"]
    lines = [
        "# Báo cáo phân tích tĩnh — XAPK ZaloPay 11.17.0",
        "",
        "> Nghiên cứu minh bạch, chỉ dùng phân tích tĩnh. Không giải mã nội dung nhạy cảm,",
        "> không trích xuất bí mật, không can thiệp cơ chế bảo vệ của ứng dụng.",
        "",
        f"*Sinh lúc:* {data['generated_at']}  ",
        f"*Công cụ:* {data['tools']}",
        "",
        "## 1. Tóm tắt",
        "",
        f"- Gói: `{m['package']}` — phiên bản {m['version_name']} (`versionCode={m['version_code']}`).",
        f"- SDK: min {m['min_sdk']}, target {m['target_sdk']}, compile {m['compile_sdk']}.",
        f"- Tổng {agg['apk_count']} APK trong XAPK; {agg['dex_count']} DEX; ~{agg['class_defs']:,} class_def;",
        f" ~{agg['method_ids']:,} method_id; ~{agg['string_ids']:,} string_id.",
        f"- Quyền: {base['permissions']['total']} ({base['permissions']['dangerous_count']} thuộc nhóm dangerous theo phân loại nghiên cứu).",
        f"- Thành phần (base): {base['components']['activity']['total']} activity,",
        f" {base['components']['service']['total']} service, {base['components']['receiver']['total']} receiver,",
        f" {base['components']['provider']['total']} provider.",
        f"- Thư viện native: {agg['native_count']} tệp, {agg['native_bytes'] / 1024 / 1024:.1f} MiB.",
        "",
        "## 2. Kiểm kê tệp tin",
        "",
        "| Tệp | Kích thước (bytes) | SHA-256 |",
        "|---|---:|---|",
    ]
    for item in data["inventory"]:
        lines.append(f"| `{item['name']}` | {item['bytes']:,} | `{item['sha256'][:24]}…` |")
    lines += [
        "",
        "## 3. Cấu trúc từng APK",
        "",
        "| APK | Bytes | DEX | class_def | method_id | .so | Quyền |",
        "|---|---:|---:|---:|---:|---:|---:|",
    ]
    for item in data["apks"]:
        lines.append(
            f"| `{item['file']}` | {item['bytes']:,} | {item['dex']['count']} | {item['dex']['class_defs']:,} "
            f"| {item['dex']['method_ids']:,} | {item['native_libraries']['count']} | {item['permissions']['total']} |"
        )
    lines += [
        "",
        "## 4. Bề mặt quyền (base APK)",
        "",
        "Các quyền được liệt kê để làm bằng chứng phân loại, không phải để đánh giá",
        "hành vi runtime (vì phân tích tĩnh không quan sát được yêu cầu lúc chạy).",
        "",
    ]
    lines += [f"- `{p}`" + (" *(dangerous)*" if p in DANGEROUS_PERMISSIONS else "") for p in base["permissions"]["all"]]
    lines += [
        "",
        "## 5. Thành phần và intent",
        "",
    ]
    for tag in ("activity", "service", "receiver", "provider"):
        c = base["components"][tag]
        lines.append(f"- **{tag}**: {c['total']} tổng, {c['exported_or_implicit']} exported/ẩn định, {c['with_intent_filter']} có intent-filter.")
    filters = base["components"]["intent_filters"]
    lines += [
        "",
        f"- Scheme deep link phổ biến: {', '.join(f'`{k}` ({v})' for k, v in filters['schemes'][:10]) or 'không ghi nhận'}.",
        f"- Số host duy nhất trong intent-filter: {filters['distinct_hosts']}.",
        f"- Action phổ biến: {', '.join(f'`{k}`' for k, _ in filters['top_actions'][:8])}.",
        "",
        "Cờ ứng dụng: " + ", ".join(f"`{k}={v}`" for k, v in base["components"]["application_flags"].items()) + ".",
        "",
        "## 6. Công nghệ và mức độ tách lớp",
        "",
        "Số liệu là số lần xuất hiện của marker byte trong DEX — thước định lượng mức độ",
        "hiện diện của framework, **không** phải số lời gọi hay mức độ sử dụng thực tế.",
        "",
        "| Marker | Số lần xuất hiện |",
        "|---|---:|",
    ]
    for name, count in base["technology_markers"].items():
        lines.append(f"| {name} | {count:,} |")
    ratio = base["dex_descriptor_metrics"]["short_descriptor_ratio"]
    lines += [
        "",
        f"Tỷ lệ descriptor cực ngắn (gợi ý tên lớp bị rút gọn): {ratio:.2%} — chỉ là tín hiệu định tính.",
        "",
        "## 7. Ký số",
        "",
        f"- Signature scheme V1 (META-INF): {base['signing']['v1_signature_names'] or 'không ghi nhận'}.",
    ]
    for cert in base["signing"]["certificates"]:
        lines.append(f"- Chứng thư: `{cert['subject']}` — SHA-256 `{cert['sha256_fingerprint']}…` — hạn đến {cert['not_valid_after']}.")
    lines += [
        "",
        "## 8. Phương pháp, giới hạn và đạo đức",
        "",
        "1. ZIP → XAPK → APK được liệt kê và băm SHA-256 để bảo chứng chuỗi bằng chứng (chain of evidence).",
        "2. AndroidManifest.xml (binary XML) được đọc bằng Androguard; DEX chỉ đọc header.",
        "3. Marker công nghệ được đếm trực tiếp trên byte DEX; không dịch ngược mã máy sang nguồn.",
        "4. Giới hạn: phân tích tĩnh không quan sát được luồng dữ liệu runtime, mã hóa động,",
        "   hay hành vi phụ thuộc máy chủ; số lớp/method là thước cấu trúc, không phải độ phức tạp",
        "   hay chất lượng mã nguồn.",
        "5. Đạo đức: báo cáo không chứa bí mật, khóa, token, endpoint nhạy cảm hay mã nguồn",
        "   tái tạo được của bên thứ ba; mọi kết luận chỉ dùng cho nghiên cứu giáo dục.",
        "",
        "## 9. Tài liệu tham khảo",
        "",
        "- Androguard — https://github.com/androguard/androguard",
        "- Android developers — App manifests / App signing: https://developer.android.com/guide/topics/manifest/manifest-intro",
        "- REA (Reverse Engineer Anything) — https://github.com/morluto/rea",
        "",
    ]
    return "\n".join(lines)


def main() -> None:
    parser = argparse.ArgumentParser()
    parser.add_argument("--xapk", required=True, type=Path)
    parser.add_argument("--outer", required=True, type=Path)
    parser.add_argument("--out", default=Path("analysis/findings.json"), type=Path)
    parser.add_argument("--report", default=Path("analysis/REPORT.md"), type=Path)
    args = parser.parse_args()

    with zipfile.ZipFile(args.outer) as zf:
        outer = [{"name": i.filename, "bytes": i.file_size, "compressed": i.compress_size}
                 for i in zf.infolist() if not i.is_dir()]
    with zipfile.ZipFile(args.xapk) as zf:
        inner = [{"name": i.filename, "bytes": i.file_size, "compressed": i.compress_size}
                 for i in zf.infolist() if not i.is_dir()]

    xdir = args.xapk.parent / "xapk"
    apk_paths = sorted(xdir.glob("*.apk"))
    apks = [analyze_apk(p) for p in apk_paths]
    # The base split is the APK that carries the bytecode; config splits carry
    # resources/native libraries only and may omit splitName from their manifest.
    base = max(apks, key=lambda a: a["dex"]["count"])

    inventory = []
    for item in outer + inner:
        candidate = xdir / Path(item["name"]).name
        item["sha256"] = sha256(candidate) if candidate.exists() else sha256(args.xapk)
        inventory.append(item)

    agg = {
        "apk_count": len(apks),
        "dex_count": sum(a["dex"]["count"] for a in apks),
        "class_defs": sum(a["dex"]["class_defs"] for a in apks),
        "method_ids": sum(a["dex"]["method_ids"] for a in apks),
        "string_ids": sum(a["dex"]["string_ids"] for a in apks),
        "native_count": sum(a["native_libraries"]["count"] for a in apks),
        "native_bytes": sum(a["native_libraries"]["bytes"] for a in apks),
        "unique_permissions": len({p for a in apks for p in a["permissions"]["all"]}),
    }
    data = {
        "generated_at": datetime.now(timezone.utc).isoformat(timespec="seconds"),
        "tools": {"androguard": "4.1.4", "python": "3.11", "method": "static-only"},
        "outer_archive": {"file": args.outer.name, "bytes": args.outer.stat().st_size, "sha256": sha256(args.outer)},
        "xapk": {"file": args.xapk.name, "bytes": args.xapk.stat().st_size, "sha256": sha256(args.xapk)},
        "inventory": inventory,
        "aggregate": agg,
        "base_apk": base,
        "apks": apks,
    }
    args.out.parent.mkdir(parents=True, exist_ok=True)
    args.out.write_text(json.dumps(data, ensure_ascii=False, indent=2), encoding="utf-8")
    args.report.parent.mkdir(parents=True, exist_ok=True)
    args.report.write_text(build_report(data), encoding="utf-8")
    print(f"OK: {args.out} ({args.out.stat().st_size:,} bytes), {args.report}")


if __name__ == "__main__":
    main()
