#!/usr/bin/env python3
"""Bounded static Android manifest, DEX-header, ELF and bundle analysis.

Input is the provided ZIP. No target code is run; no archive paths are
extracted. DEX method IDs are references in tables, NOT observed invocations.
"""
from __future__ import annotations

import argparse
import hashlib
import io
import json
import re
import struct
import zipfile
import zlib
from collections import Counter
from pathlib import Path

from androguard.core.apk import APK
from androguard.core.axml import AXMLPrinter
from elftools.elf.elffile import ELFFile
from loguru import logger
from lxml import etree

logger.remove()
A = "{http://schemas.android.com/apk/res/android}"
NAMESPACES = {
    "AndroidX": "Landroidx/",
    "Zalopay (vn.com.vng.zalopay)": "Lvn/com/vng/zalopay/",
    "React Native": "Lcom/facebook/react/",
    "Firebase": "Lcom/google/firebase/",
    "Google ML Kit": "Lcom/google/mlkit/",
    "Alibaba Griver": "Lcom/alibaba/griver/",
    "OkHttp": "Lokhttp3/",
}
TARGET_METHODS = {
    "Landroid/webkit/WebView;": {"addJavascriptInterface", "loadUrl", "evaluateJavascript"},
    "Landroid/webkit/WebSettings;": {"setJavaScriptEnabled"},
    "Landroid/content/ContentResolver;": {"query", "openInputStream"},
    "Landroid/media/MediaRecorder;": {"start", "setAudioSource"},
    "Landroid/hardware/Camera;": {"open"},
    "Landroid/hardware/camera2/CameraManager;": {"openCamera"},
    "Landroid/telephony/TelephonyManager;": {"getDeviceId", "getImei"},
    "Ldalvik/system/DexClassLoader;": {"<init>"},
    "Ljava/lang/System;": {"loadLibrary", "load"},
    "Ljavax/net/ssl/SSLContext;": {"init"},
}


def sha(data: bytes) -> str:
    return hashlib.sha256(data).hexdigest()


def u32(data: bytes, offset: int) -> int:
    return struct.unpack_from("<I", data, offset)[0]


def dex_facts(name: str, data: bytes) -> dict:
    if len(data) < 112 or not data.startswith(b"dex\n") or u32(data, 0x28) != 0x12345678:
        raise ValueError(f"unsupported or malformed DEX header: {name}")
    if u32(data, 0x20) != len(data):
        raise ValueError(f"DEX declared size mismatch: {name}")
    strings_n, strings_off = u32(data, 0x38), u32(data, 0x3C)
    types_n, types_off = u32(data, 0x40), u32(data, 0x44)
    methods_n, methods_off = u32(data, 0x58), u32(data, 0x5C)
    classes_n, classes_off = u32(data, 0x60), u32(data, 0x64)
    for off, count, width in ((strings_off, strings_n, 4), (types_off, types_n, 4),
                              (methods_off, methods_n, 8), (classes_off, classes_n, 32)):
        if off + count * width > len(data):
            raise ValueError(f"DEX table out of bounds: {name}")
    cache: dict[int, str] = {}

    def string(idx: int) -> str:
        if idx >= strings_n:
            raise ValueError("invalid string index")
        if idx not in cache:
            pos = u32(data, strings_off + idx * 4)
            # Skip ULEB128 UTF-16 length (max 5 bytes). Only class/method
            # identifiers are used below; their ASCII spellings are exact.
            for _ in range(5):
                b = data[pos]
                pos += 1
                if not b & 0x80:
                    break
            else:
                raise ValueError("invalid ULEB128")
            end = data.find(b"\0", pos, min(len(data), pos + 1024 * 1024))
            if end < 0:
                raise ValueError("unterminated DEX string")
            cache[idx] = data[pos:end].decode("utf-8", errors="replace")
        return cache[idx]

    def descriptor(type_idx: int) -> str:
        if type_idx >= types_n:
            raise ValueError("invalid type index")
        return string(u32(data, types_off + type_idx * 4))

    ns_counts = Counter()
    for index in range(classes_n):
        desc = descriptor(u32(data, classes_off + index * 32))
        for label, prefix in NAMESPACES.items():
            if desc.startswith(prefix):
                ns_counts[label] += 1
    target_types = {}
    for index in range(types_n):
        desc = descriptor(index)
        if desc in TARGET_METHODS:
            target_types[index] = desc
    refs = Counter()
    for index in range(methods_n):
        offset = methods_off + index * 8
        class_idx = struct.unpack_from("<H", data, offset)[0]
        desc = target_types.get(class_idx)
        if desc is not None:
            method_name = string(u32(data, offset + 4))
            if method_name in TARGET_METHODS[desc]:
                refs[f"{desc}->{method_name}"] += 1
    return {
        "path": name, "bytes": len(data), "sha256": sha(data),
        "dex_version": data[4:7].decode("ascii", errors="replace"),
        "declared_sha1_matches": hashlib.sha1(data[32:]).digest() == data[12:32],
        "declared_adler32_matches": (zlib.adler32(data[12:]) & 0xFFFFFFFF) == u32(data, 8),
        "string_ids": strings_n, "type_ids": types_n, "method_ids": methods_n,
        "class_definitions": classes_n,
        "class_definitions_by_selected_prefix": dict(sorted(ns_counts.items())),
        "selected_method_id_references": dict(sorted(refs.items())),
    }


def elf_facts(path: str, data: bytes) -> dict:
    elf = ELFFile(io.BytesIO(data))
    sections = {section.name for section in elf.iter_sections()}
    dynamic = elf.get_section_by_name(".dynamic")
    needed = []
    if dynamic:
        needed = sorted(tag.needed for tag in dynamic.iter_tags() if tag.entry.d_tag == "DT_NEEDED")
    dynsym = elf.get_section_by_name(".dynsym")
    exported = 0
    if dynsym:
        for sym in dynsym.iter_symbols():
            if sym.name and sym["st_info"]["bind"] in ("STB_GLOBAL", "STB_WEAK") and sym["st_shndx"] != "SHN_UNDEF":
                exported += 1
    return {"path": path, "bytes": len(data), "sha256": sha(data), "elf_class_bits": elf.elfclass,
            "little_endian": elf.little_endian, "machine": elf.header["e_machine"],
            "elf_type": elf.header["e_type"], "needed": needed, "dynamic_exported_symbols": exported,
            "has_symtab": ".symtab" in sections,
            "has_debug_sections": any(n.startswith(".debug_") for n in sections)}


def resolve_xml(apk: APK, value: str | None) -> dict | None:
    if not value or not value.startswith("@"):
        return None
    try:
        resource_id = int(value[1:], 16)
        configs = apk.get_android_resources().get_resolved_res_configs(resource_id)
        paths = [v for _, v in configs if isinstance(v, str) and v.startswith("res/")]
    except (ValueError, TypeError, KeyError):
        return {"resource_reference": value, "resolution": "failed"}
    variants = []
    for path in paths:
        raw = apk.get_file(path)
        node = AXMLPrinter(raw).get_xml_obj()
        variants.append({"path": path, "sha256": sha(raw),
                         "decoded_xml": etree.tostring(node, encoding="unicode", pretty_print=True)})
    return {"resource_reference": value, "resolution": "resolved" if variants else "no file",
            "variants": variants}


def apk_manifest(data: bytes) -> tuple[APK, dict]:
    apk = APK(data, raw=True)
    root = apk.get_android_manifest_xml()
    app = root.find("application")
    perms = []
    for node in root:
        if node.tag in ("uses-permission", "uses-permission-sdk-23"):
            perms.append({"tag": node.tag, "name": node.get(A + "name"),
                          "max_sdk_version": node.get(A + "maxSdkVersion"),
                          "uses_permission_flags": node.get(A + "usesPermissionFlags")})
    kinds = ("activity", "activity-alias", "service", "receiver", "provider")
    counts = Counter()
    exported = []
    if app is not None:
        for node in app:
            if node.tag not in kinds:
                continue
            counts[node.tag] += 1
            if node.get(A + "exported") == "true":
                exported.append({"kind": node.tag, "name": node.get(A + "name"),
                                 "permission": node.get(A + "permission"),
                                 "intent_filters": len(node.findall("intent-filter"))})
    appattrs = {} if app is None else {
        key: app.get(A + key) for key in ("name", "debuggable", "allowBackup", "fullBackupContent",
            "usesCleartextTraffic", "networkSecurityConfig", "dataExtractionRules", "extractNativeLibs")}
    facts = {
        "package": root.get("package"), "split": root.get("split"),
        "version_name": root.get(A + "versionName"), "version_code": root.get(A + "versionCode"),
        "min_sdk": apk.get_min_sdk_version(), "target_sdk": apk.get_target_sdk_version(),
        "application_attributes": appattrs, "permissions": perms,
        "component_counts": dict(sorted(counts.items())), "explicitly_exported_true": exported,
        "network_security_config": resolve_xml(apk, appattrs.get("networkSecurityConfig")),
        "data_extraction_rules": resolve_xml(apk, appattrs.get("dataExtractionRules")),
    }
    return apk, facts


def main() -> None:
    parser = argparse.ArgumentParser(description=__doc__)
    parser.add_argument("archive", type=Path)
    parser.add_argument("--out", type=Path, default=Path("research/android-analysis.json"))
    args = parser.parse_args()
    with zipfile.ZipFile(args.archive) as outer:
        entries = [i for i in outer.infolist() if i.filename.endswith(".xapk") and not i.is_dir()]
        if len(entries) != 1 or entries[0].file_size > 128 * 1024**2:
            raise ValueError("expected one bounded XAPK")
        xb = outer.read(entries[0])
    with zipfile.ZipFile(io.BytesIO(xb)) as x:
        manifest = json.loads(x.read("manifest.json"))
        apk_infos = [i for i in x.infolist() if i.filename.endswith(".apk") and not i.is_dir()]
        apks = {}
        base_data = None
        abi_data = None
        for info in apk_infos:
            if info.file_size > 128 * 1024**2:
                raise ValueError("APK exceeds limit")
            data = x.read(info)
            _, fact = apk_manifest(data)
            fact["sha256"] = sha(data)
            apks[info.filename] = fact
            if fact["split"] is None:
                if base_data is not None:
                    raise ValueError("multiple base APKs")
                base_data = data
            if info.filename == "config.armeabi_v7a.apk":
                abi_data = data
        if base_data is None or abi_data is None:
            raise ValueError("base or ABI split missing")
    base = zipfile.ZipFile(io.BytesIO(base_data))
    dexes = [dex_facts(i.filename, base.read(i)) for i in base.infolist()
             if i.filename.endswith(".dex") and not i.is_dir()]
    abi = zipfile.ZipFile(io.BytesIO(abi_data))
    elves = [elf_facts(i.filename, abi.read(i)) for i in abi.infolist()
             if i.filename.endswith(".so") and not i.is_dir() and abi.read(i)[:4] == b"\x7fELF"]
    embedded = zipfile.ZipFile(io.BytesIO(base.read("assets/android.1.zip")))
    js = embedded.read("main.jsbundle")
    markers = ("__d(", "__r(", "ReactNative", "NativeModules", "sourceMappingURL")
    models = [{"path": i.filename, "bytes": i.file_size} for i in base.infolist()
              if i.filename.startswith("assets/") and i.filename.endswith((".tflite", ".tfl"))]
    main = {
        "schema_version": 1,
        "xapk": {k: manifest.get(k) for k in ("xapk_version", "package_name", "name", "version_name",
                    "version_code", "min_sdk_version", "target_sdk_version", "split_configs")},
        "apks": apks,
        "dex": dexes,
        "elf": elves,
        "javascript_bundle": {"path": "assets/android.1.zip!/main.jsbundle", "bytes": len(js),
             "sha256": sha(js), "literal_marker_counts": {m: js.count(m.encode()) for m in markers},
             "note": "Literal byte counts, not a count of reachable modules or behavior."},
        "asset_models_by_extension": models,
        "limitations": ["Static declarations and identifiers are not runtime observations.",
             "DEX method IDs are table entries and do not demonstrate a call, reachability, or data flow.",
             "DEX SHA-1/Adler-32 are integrity checks, not publisher authentication.",
             "ELF headers, dependencies and symbols are metadata, not full native-code analysis.",
             "The embedded JavaScript bundle is counted by markers; it is not exhaustively semantically analyzed."],
    }
    args.out.parent.mkdir(parents=True, exist_ok=True)
    args.out.write_text(json.dumps(main, ensure_ascii=False, indent=2) + "\n", encoding="utf-8")
    print(f"Wrote {args.out}: {len(apks)} APKs, {len(dexes)} DEX, {len(elves)} ELF")


if __name__ == "__main__":
    main()
