"""
Pure-Python Binary Android XML (AXML) structural parser.
Parses compiled AndroidManifest.xml (RES_XML_TYPE = 0x0003) without external binaries.
"""
from __future__ import annotations

import struct
from dataclasses import dataclass, field
from typing import Any, Dict, List, Optional, Tuple

RES_NULL_TYPE = 0x0000
RES_STRING_POOL_TYPE = 0x0001
RES_XML_TYPE = 0x0003
RES_XML_START_NAMESPACE_TYPE = 0x0100
RES_XML_END_NAMESPACE_TYPE = 0x0101
RES_XML_START_ELEMENT_TYPE = 0x0102
RES_XML_END_ELEMENT_TYPE = 0x0103
RES_XML_CDATA_TYPE = 0x0104
RES_XML_RESOURCE_MAP_TYPE = 0x0180

TYPE_NULL = 0x00
TYPE_REFERENCE = 0x01
TYPE_ATTRIBUTE = 0x02
TYPE_STRING = 0x03
TYPE_FLOAT = 0x04
TYPE_DIMENSION = 0x05
TYPE_FRACTION = 0x06
TYPE_INT_DEC = 0x10
TYPE_INT_HEX = 0x11
TYPE_INT_BOOLEAN = 0x12
TYPE_FIRST_COLOR_INT = 0x1C
TYPE_LAST_COLOR_INT = 0x1F

UTF8_FLAG = 1 << 8

ANDROID_ATTR_IDS = {
    0x01010003: "name",
    0x01010001: "label",
    0x01010002: "icon",
    0x01010010: "exported",
    0x0101000e: "enabled",
    0x01010011: "process",
    0x01010012: "taskAffinity",
    0x0101001d: "launchMode",
    0x0101001e: "screenOrientation",
    0x0101001f: "configChanges",
    0x01010025: "authorities",
    0x0101020c: "minSdkVersion",
    0x0101021b: "versionCode",
    0x0101021c: "versionName",
    0x01010270: "targetSdkVersion",
    0x01010280: "allowBackup",
    0x01010281: "hardwareAccelerated",
    0x0101028e: "required",
    0x010102b7: "installLocation",
    0x0101035a: "largeHeap",
    0x010103af: "supportsRtl",
    0x010104ea: "extractNativeLibs",
    0x010104ec: "usesCleartextTraffic",
    0x01010572: "compileSdkVersion",
    0x01010573: "compileSdkVersionCodename",
    0x0101057a: "appComponentFactory",
    0x01010599: "foregroundServiceType",
    0x01010603: "requestLegacyExternalStorage",
}


def decode_string_pool(data: bytes, offset: int) -> Tuple[List[str], int]:
    """Decode an Android ResStringPool chunk starting at offset."""
    chunk_type, header_size, chunk_size = struct.unpack_from("<HHI", data, offset)
    if chunk_type != RES_STRING_POOL_TYPE:
        raise ValueError(f"Expected string pool 0x0001, got 0x{chunk_type:04x}")
    (
        string_count,
        style_count,
        flags,
        strings_start,
        styles_start,
    ) = struct.unpack_from("<IIIII", data, offset + 8)
    is_utf8 = bool(flags & UTF8_FLAG)
    offsets_base = offset + header_size
    strings_base = offset + strings_start
    strings: List[str] = []

    for i in range(string_count):
        str_off = struct.unpack_from("<I", data, offsets_base + i * 4)[0]
        pos = strings_base + str_off
        if is_utf8:
            # Skip UTF-16 char length (1 or 2 bytes)
            b0 = data[pos]
            pos += 2 if (b0 & 0x80) else 1
            # Read UTF-8 byte length (1 or 2 bytes)
            b1 = data[pos]
            if b1 & 0x80:
                byte_len = ((b1 & 0x7F) << 8) | data[pos + 1]
                pos += 2
            else:
                byte_len = b1
                pos += 1
            raw = data[pos : pos + byte_len]
            strings.append(raw.decode("utf-8", errors="replace"))
        else:
            u16_len = struct.unpack_from("<H", data, pos)[0]
            pos += 2
            if u16_len & 0x8000:
                u16_len = ((u16_len & 0x7FFF) << 16) | struct.unpack_from("<H", data, pos)[0]
                pos += 2
            raw = data[pos : pos + u16_len * 2]
            strings.append(raw.decode("utf-16-le", errors="replace"))
    return strings, offset + chunk_size


@dataclass
class XmlElement:
    tag: str
    attrs: Dict[str, Any] = field(default_factory=dict)
    children: List["XmlElement"] = field(default_factory=list)


def parse_axml(data: bytes) -> Optional[XmlElement]:
    """Parse binary Android XML bytes into a tree of XmlElement."""
    if len(data) < 8:
        return None
    chunk_type, header_size, file_size = struct.unpack_from("<HHI", data, 0)
    if chunk_type != RES_XML_TYPE:
        return None

    pos = header_size
    strings: List[str] = []
    res_map: List[int] = []
    stack: List[XmlElement] = []
    root: Optional[XmlElement] = None

    while pos + 8 <= len(data):
        ctype, chdr, csize = struct.unpack_from("<HHI", data, pos)
        if csize < 8 or pos + csize > len(data):
            break

        if ctype == RES_STRING_POOL_TYPE:
            strings, _ = decode_string_pool(data, pos)
        elif ctype == RES_XML_RESOURCE_MAP_TYPE:
            count = (csize - chdr) // 4
            res_map = list(struct.unpack_from(f"<{count}I", data, pos + chdr))
        elif ctype == RES_XML_START_ELEMENT_TYPE:
            # Ext header: ns(4), name(4), attrStart(2), attrSize(2), attrCount(2), idIdx(2), classIdx(2), styleIdx(2)
            ext = pos + chdr
            ns_idx, name_idx, attr_start, attr_size, attr_count = struct.unpack_from(
                "<IIHHH", data, ext
            )
            tag = strings[name_idx] if 0 <= name_idx < len(strings) else f"tag_{name_idx}"
            attrs: Dict[str, Any] = {}
            attr_pos = ext + attr_start
            for _ in range(attr_count):
                if attr_pos + 20 > pos + csize:
                    break
                a_ns, a_name, a_raw, tv_size, tv_res0, tv_type, tv_data = struct.unpack_from(
                    "<IIIHBBI", data, attr_pos
                )
                aname = strings[a_name] if 0 <= a_name < len(strings) else ""
                if not aname and 0 <= a_name < len(res_map):
                    aname = ANDROID_ATTR_IDS.get(res_map[a_name], f"res_0x{res_map[a_name]:08x}")

                if tv_type == TYPE_STRING:
                    val: Any = strings[tv_data] if 0 <= tv_data < len(strings) else ""
                elif a_raw != 0xFFFFFFFF and 0 <= a_raw < len(strings):
                    val = strings[a_raw]
                elif tv_type == TYPE_INT_BOOLEAN:
                    val = bool(tv_data)
                elif tv_type == TYPE_INT_DEC:
                    val = struct.unpack("<i", struct.pack("<I", tv_data))[0]
                elif tv_type == TYPE_INT_HEX:
                    val = f"0x{tv_data:x}"
                elif tv_type == TYPE_REFERENCE:
                    val = f"@0x{tv_data:08x}"
                elif tv_type == TYPE_FLOAT:
                    val = struct.unpack("<f", struct.pack("<I", tv_data))[0]
                else:
                    val = tv_data

                attrs[aname] = val
                attr_pos += attr_size

            elem = XmlElement(tag=tag, attrs=attrs)
            if stack:
                stack[-1].children.append(elem)
            else:
                root = elem
            stack.append(elem)
        elif ctype == RES_XML_END_ELEMENT_TYPE:
            if stack:
                stack.pop()

        pos += csize

    return root
