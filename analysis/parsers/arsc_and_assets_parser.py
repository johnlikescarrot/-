"""
Pure-Python structural parsers for:
- Android resources.arsc (RES_TABLE_TYPE = 0x0002)
- APK Signing Block (APK Signature Schemes v1/v2/v3/v3.1 & metadata block IDs)
- OpenType / TrueType font family identification
- Magic-byte file type classification & Lottie / media / ML structural profiling
"""
from __future__ import annotations

import json
import struct
from collections import Counter
from typing import Any, Dict, List, Optional, Set, Tuple

from .axml_parser import RES_STRING_POOL_TYPE, decode_string_pool

RES_TABLE_TYPE = 0x0002
RES_TABLE_PACKAGE_TYPE = 0x0200
RES_TABLE_TYPE_TYPE = 0x0201
RES_TABLE_TYPE_SPEC_TYPE = 0x0202

APK_SIG_MAGIC = b"APK Sig Block 42"
APK_SIG_BLOCK_IDS = {
    0x7109871A: "APK Signature Scheme v2",
    0xF05368C0: "APK Signature Scheme v3",
    0x1B93AD61: "APK Signature Scheme v3.1",
    0x504B4453: "Play Dependency Metadata (Protobuf)",
    0x6DFF800D: "APK Source Stamp v2",
    0x2B09189E: "APK Source Stamp v1",
    0x42726577: "Verity Padding Block",
    0x71777777: "APK Channel / Frosting Metadata",
}

DENSITY_NAMES = {
    0: "default",
    120: "ldpi",
    160: "mdpi",
    213: "tvdpi",
    240: "hdpi",
    320: "xhdpi",
    480: "xxhdpi",
    640: "xxxhdpi",
    65534: "anydpi",
    65535: "nodpi",
}


def parse_arsc_structural(data: bytes, apk_name: str) -> Dict[str, Any]:
    """Parse structural summary of an Android resources.arsc table."""
    if len(data) < 12:
        return {"apk": apk_name, "valid": False}
    ctype, hdr_size, total_size = struct.unpack_from("<HHI", data, 0)
    if ctype != RES_TABLE_TYPE:
        return {"apk": apk_name, "valid": False}
    package_count = struct.unpack_from("<I", data, 8)[0]

    pos = hdr_size
    global_string_count = 0
    global_string_pool_bytes = 0
    packages: List[Dict[str, Any]] = []

    while pos + 8 <= len(data):
        st, shdr, ssize = struct.unpack_from("<HHI", data, pos)
        if ssize < 8 or pos + ssize > len(data):
            break
        if st == RES_STRING_POOL_TYPE:
            global_string_count = struct.unpack_from("<I", data, pos + 8)[0]
            global_string_pool_bytes = ssize
        elif st == RES_TABLE_PACKAGE_TYPE:
            pkg_id = struct.unpack_from("<I", data, pos + 8)[0]
            raw_name = data[pos + 12 : pos + 12 + 256]
            pkg_name = raw_name.decode("utf-16-le", errors="ignore").split("\x00", 1)[0]
            type_strings_off, last_pub_type, key_strings_off, last_pub_key = struct.unpack_from(
                "<IIII", data, pos + 268
            )
            type_names: List[str] = []
            key_string_count = 0
            if type_strings_off and pos + type_strings_off + 8 <= len(data):
                try:
                    type_names, _ = decode_string_pool(data, pos + type_strings_off)
                except Exception:
                    type_names = []
            if key_strings_off and pos + key_strings_off + 8 <= len(data):
                key_string_count = struct.unpack_from("<I", data, pos + key_strings_off + 8)[0]

            # Walk sub-chunks inside package
            sub_pos = pos + shdr
            pkg_end = min(len(data), pos + ssize)
            type_spec_counts: Dict[str, int] = {}
            type_config_counts: Counter = Counter()
            locales: Set[str] = set()
            densities: Set[str] = set()
            night_configs = 0
            total_type_chunks = 0

            while sub_pos + 8 <= pkg_end:
                ct, ch, cs = struct.unpack_from("<HHI", data, sub_pos)
                if cs < 8 or sub_pos + cs > pkg_end:
                    break
                if ct == RES_TABLE_TYPE_SPEC_TYPE and sub_pos + 16 <= pkg_end:
                    tid = data[sub_pos + 8]
                    entry_count = struct.unpack_from("<I", data, sub_pos + 12)[0]
                    tname = type_names[tid - 1] if 1 <= tid <= len(type_names) else f"type_{tid}"
                    type_spec_counts[tname] = type_spec_counts.get(tname, 0) + entry_count
                elif ct == RES_TABLE_TYPE_TYPE and sub_pos + 48 <= pkg_end:
                    total_type_chunks += 1
                    tid = data[sub_pos + 8]
                    tname = type_names[tid - 1] if 1 <= tid <= len(type_names) else f"type_{tid}"
                    type_config_counts[tname] += 1
                    # ResTable_config starts at sub_pos + 20
                    cfg_off = sub_pos + 20
                    cfg_size = struct.unpack_from("<I", data, cfg_off)[0]
                    if cfg_size >= 28 and cfg_off + 28 <= pkg_end:
                        lang_bytes = data[cfg_off + 8 : cfg_off + 10]
                        country_bytes = data[cfg_off + 10 : cfg_off + 12]
                        if lang_bytes != b"\x00\x00":
                            lang = _decode_arsc_lang(lang_bytes)
                            ctry = _decode_arsc_lang(country_bytes) if country_bytes != b"\x00\x00" else ""
                            locales.add(f"{lang}-{ctry}" if ctry else lang)
                        density = struct.unpack_from("<H", data, cfg_off + 14)[0]
                        if density:
                            densities.add(DENSITY_NAMES.get(density, f"{density}dpi"))
                        if cfg_size >= 32 and cfg_off + 32 <= pkg_end:
                            ui_mode = data[cfg_off + 27]
                            if (ui_mode & 0x30) == 0x20:
                                night_configs += 1
                sub_pos += cs

            packages.append({
                "id": f"0x{pkg_id:02x}",
                "name": pkg_name,
                "key_string_count": key_string_count,
                "total_resource_ids": sum(type_spec_counts.values()),
                "total_config_variants": total_type_chunks,
                "type_entry_counts": dict(sorted(type_spec_counts.items(), key=lambda x: -x[1])),
                "type_variant_counts": dict(type_config_counts.most_common(25)),
                "locales_detected": sorted(locales),
                "densities_detected": sorted(densities),
                "night_mode_type_chunks": night_configs,
            })
        pos += ssize

    return {
        "apk": apk_name,
        "valid": True,
        "file_size_bytes": len(data),
        "package_count": package_count,
        "global_string_count": global_string_count,
        "global_string_pool_bytes": global_string_pool_bytes,
        "packages": packages,
    }


def _decode_arsc_lang(b: bytes) -> str:
    if len(b) != 2:
        return ""
    if b[0] & 0x80:
        # 3-letter ISO-639-2 packed code
        c1 = ((b[1] & 0x1F) + ord("a"))
        c2 = ((((b[1] & 0xE0) >> 5) | ((b[0] & 0x03) << 3)) + ord("a"))
        c3 = (((b[0] & 0x7C) >> 2) + ord("a"))
        return chr(c1) + chr(c2) + chr(c3)
    return b.decode("ascii", errors="ignore").strip("\x00")


def inspect_apk_signing_block(apk_bytes: bytes, has_v1_sig: bool) -> Dict[str, Any]:
    """Locate and inspect the APK Signing Block (v2/v3/v3.1) directly from APK bytes."""
    # Find ZIP End of Central Directory (0x06054b50)
    search_start = max(0, len(apk_bytes) - 65557)
    eocd_pos = apk_bytes.rfind(b"PK\x05\x06", search_start)
    if eocd_pos == -1 or eocd_pos + 22 > len(apk_bytes):
        return {"v1_jar_sig": has_v1_sig, "has_apk_sig_block": False, "blocks": []}

    cd_offset = struct.unpack_from("<I", apk_bytes, eocd_pos + 16)[0]
    if cd_offset < 24 or cd_offset > len(apk_bytes):
        return {"v1_jar_sig": has_v1_sig, "has_apk_sig_block": False, "blocks": []}

    magic = apk_bytes[cd_offset - 16 : cd_offset]
    if magic != APK_SIG_MAGIC:
        return {"v1_jar_sig": has_v1_sig, "has_apk_sig_block": False, "blocks": []}

    block_size_footer = struct.unpack_from("<Q", apk_bytes, cd_offset - 24)[0]
    block_start = cd_offset - block_size_footer - 8
    if block_start < 0:
        return {"v1_jar_sig": has_v1_sig, "has_apk_sig_block": True, "blocks": []}

    pos = block_start + 8
    end = cd_offset - 24
    blocks: List[Dict[str, Any]] = []
    while pos + 12 <= end:
        pair_len = struct.unpack_from("<Q", apk_bytes, pos)[0]
        if pair_len < 4 or pos + 8 + pair_len > end:
            break
        pair_id = struct.unpack_from("<I", apk_bytes, pos + 8)[0]
        blocks.append({
            "id_hex": f"0x{pair_id:08x}",
            "label": APK_SIG_BLOCK_IDS.get(pair_id, f"Custom Block 0x{pair_id:08x}"),
            "size_bytes": pair_len - 4,
        })
        pos += 8 + pair_len

    return {
        "v1_jar_sig": has_v1_sig,
        "has_apk_sig_block": True,
        "sig_block_total_bytes": block_size_footer + 8,
        "blocks": blocks,
    }


def parse_font_family(data: bytes) -> Optional[Dict[str, str]]:
    """Extract font family and subfamily name from TTF/OTF 'name' table."""
    if len(data) < 12:
        return None
    sfnt = data[:4]
    if sfnt not in (b"\x00\x01\x00\x00", b"OTTO", b"true"):
        return None
    num_tables = struct.unpack_from(">H", data, 4)[0]
    name_off = name_len = 0
    for i in range(num_tables):
        rec_off = 12 + i * 16
        if rec_off + 16 > len(data):
            break
        tag = data[rec_off : rec_off + 4]
        if tag == b"name":
            _, _, name_off, name_len = struct.unpack_from(">4I", data, rec_off)
            break
    if not name_off or name_off + 6 > len(data):
        return None
    _, count, str_offset = struct.unpack_from(">HHH", data, name_off)
    str_base = name_off + str_offset
    family = subfamily = full_name = ""
    for i in range(count):
        r = name_off + 6 + i * 12
        if r + 12 > len(data):
            break
        plat_id, enc_id, lang_id, name_id, length, offset = struct.unpack_from(">6H", data, r)
        if name_id not in (1, 2, 4):
            continue
        raw = data[str_base + offset : str_base + offset + length]
        if plat_id == 3 or plat_id == 0:
            val = raw.decode("utf-16-be", errors="ignore").strip()
        elif plat_id == 1:
            val = raw.decode("latin-1", errors="ignore").strip()
        else:
            continue
        if name_id == 1 and (not family or plat_id == 3):
            family = val
        elif name_id == 2 and (not subfamily or plat_id == 3):
            subfamily = val
        elif name_id == 4 and (not full_name or plat_id == 3):
            full_name = val
    return {"family": family or full_name or "Unknown", "subfamily": subfamily or "Regular", "full_name": full_name or family}


def detect_magic_type(filename: str, head: bytes) -> str:
    """Identify binary/file format from magic bytes + filename extension."""
    low = filename.lower()
    if head.startswith(b"\x7fELF"):
        return "ELF Native Shared Library (.so)"
    if head.startswith(b"dex\n"):
        return "Dalvik Executable (.dex)"
    if head.startswith(b"\x03\x00\x08\x00"):
        return "Binary Android XML (AXML)"
    if head.startswith(b"\x02\x00\x0c\x00"):
        return "Android Compiled Resource Table (.arsc)"
    if head.startswith(b"\x89PNG\r\n\x1a\n"):
        return "Nine-Patch PNG (.9.png)" if low.endswith(".9.png") else "PNG Raster Image"
    if head.startswith(b"RIFF") and len(head) >= 12 and head[8:12] == b"WEBP":
        return "WebP Image"
    if head.startswith(b"\xff\xd8\xff"):
        return "JPEG Image"
    if head.startswith((b"GIF87a", b"GIF89a")):
        return "GIF Animation"
    if head.startswith((b"\x00\x01\x00\x00", b"OTTO")) and low.endswith((".ttf", ".otf")):
        return "TrueType / OpenType Font"
    if head.startswith(b"PK\x03\x04"):
        return "Nested ZIP / JAR / Bundle"
    if head.startswith(b"SQLite format 3\x00"):
        return "SQLite Database"
    if len(head) >= 8 and head[4:8] == b"TFL3":
        return "TensorFlow Lite Model (.tflite)"
    if head.startswith(b"\x00asm"):
        return "WebAssembly Module (.wasm)"
    if head.startswith(b"OggS") or head.startswith(b"ID3") or (len(head) >= 8 and head[4:8] == b"ftyp"):
        return "Audio / Video Media Container"
    if low.endswith(".prof") or low.endswith(".profm") or head.startswith(b"pro\x00") or head.startswith(b"prm\x00"):
        return "ART Baseline / Cloud Profile"
    if low.endswith(".json"):
        return "JSON Data / Lottie / Config"
    if low.endswith(".xml"):
        return "XML Resource / Config"
    if low.endswith((".js", ".mjs", ".cjs", ".hbc", ".bundle")):
        return "JavaScript / Hermes Bundle"
    if low.endswith((".html", ".htm", ".css")):
        return "Web HTML / CSS Asset"
    if low.endswith((".onnx", ".ort", ".mnn", ".ncnn", ".param", ".tflite", ".mlmodel")):
        return "On-Device ML Model Asset"
    if low.endswith(".kotlin_builtins") or low.endswith(".kotlin_module"):
        return "Kotlin Compiler Metadata"
    if low.endswith((".properties", ".version", ".txt", ".md")):
        return "Build / Library Metadata Text"
    if low.endswith((".sf", ".rsa", ".dsa", ".ec", ".mf")):
        return "JAR Signature / Manifest Metadata"
    if low.endswith(".pem") or low.endswith(".crt") or low.endswith(".cer") or low.endswith(".der") or low.endswith(".bks"):
        return "Trust Store / Certificate Asset"
    if low.endswith(".svg"):
        return "SVG Vector Graphic"
    if low.endswith(".pb") or low.endswith(".proto"):
        return "Protobuf Binary Asset"
    return "Other Binary / Data Asset"


def inspect_lottie_json(data: bytes) -> Optional[Dict[str, Any]]:
    """Detect whether a JSON file is a Bodymovin/Lottie animation and return structural complexity metrics."""
    if len(data) > 5 * 1024 * 1024:
        return None
    try:
        obj = json.loads(data.decode("utf-8"))
    except Exception:
        return None
    if not isinstance(obj, dict):
        return None
    if "layers" in obj and "fr" in obj and "ip" in obj and "op" in obj:
        fr = float(obj.get("fr") or 0)
        ip = float(obj.get("ip") or 0)
        op = float(obj.get("op") or 0)
        duration = round((op - ip) / fr, 2) if fr > 0 else 0.0
        return {
            "version": str(obj.get("v", "")),
            "fps": fr,
            "width": obj.get("w", 0),
            "height": obj.get("h", 0),
            "layers": len(obj.get("layers", [])) if isinstance(obj.get("layers"), list) else 0,
            "assets": len(obj.get("assets", [])) if isinstance(obj.get("assets"), list) else 0,
            "duration_sec": duration,
            "size_bytes": len(data),
        }
    return None
