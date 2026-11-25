"""Minimal ARSC (resources.arsc) parser: type/config/entry census + locales.

Only chunk headers are decoded, which keeps a 1.8 MB table parse in the
millisecond range and avoids depending on androguard's full decoder.
"""
from __future__ import annotations
import struct
from collections import Counter

RES_STRING_POOL = 0x0001
RES_TABLE = 0x0002
RES_TABLE_PACKAGE = 0x0200
RES_TABLE_TYPE = 0x0201
RES_TABLE_TYPE_SPEC = 0x0202
RES_TABLE_LIBRARY = 0x0203
RES_TABLE_OVERLAYABLE = 0x0204
RES_XML = 0x0003

LANG_CODES = {}  # packed 2-char language encoding
for _i in range(26):
    LANG_CODES[bytes([0x61 + _i, 0x00]).decode("latin-1")] = chr(0x61 + _i)


def decode_locale(config: bytes) -> str:
    lang = config[8:10]
    country = config[10:12]
    if lang == b"\x00\x00":
        return "default"
    if lang[0] & 0x80:  # packed 3-letter code (rare)
        return f"packed:{lang.hex()}"
    l = lang.decode("latin-1").strip("\x00")
    c = country.decode("latin-1").strip("\x00")
    return f"{l}-{c}" if c else l


def parse(path: str) -> dict:
    data = open(path, "rb").read()
    ctype, header_size, size = struct.unpack_from("<HHI", data, 0)
    assert ctype == RES_TABLE, "not a resource table"
    off = header_size
    packages = []
    global_strings = 0
    while off + 8 <= len(data):
        ctype, chs, csz = struct.unpack_from("<HHI", data, off)
        if csz == 0:
            break
        if ctype == RES_STRING_POOL:
            count = struct.unpack_from("<I", data, off + 8)[0]
            global_strings = max(global_strings, count)
        elif ctype == RES_TABLE_PACKAGE:
            pkg_id = struct.unpack_from("<I", data, off + 8)[0]
            name = data[off + 12: off + 12 + 128].decode("utf-16-le").rstrip("\x00")
            # ResTable_package: header(8) + id(4) + name[128] (128 uint16 = 256 B)
            # -> typeStrings at +268, lastPublicType +272, keyStrings +276, lastPublicKey +280
            type_strings_off, _last_type, key_strings_off, _last_key = struct.unpack_from("<IIII", data, off + 268)
            type_names: list[str] = []
            if type_strings_off:
                sp = off + type_strings_off
                scount = struct.unpack_from("<I", data, sp + 8)[0]
                type_names = _read_pool(data, sp, scount)
            p = off + chs
            types: Counter = Counter()
            configs: Counter = Counter()
            type_ids = set()
            pkg_info = {"id": pkg_id, "name": name, "types": {}, "config_count": 0, "entry_total": 0}
            while p + 8 <= off + csz:
                t2, h2, s2 = struct.unpack_from("<HHI", data, p)
                if s2 == 0:
                    break
                if t2 == RES_TABLE_TYPE:
                    tid, flags, res = struct.unpack_from("<BBH", data, p + 8)
                    entry_count = struct.unpack_from("<I", data, p + 12)[0]
                    cfg_size = struct.unpack_from("<I", data, p + 20)[0]
                    cfg = data[p + 20: p + 20 + cfg_size]
                    loc = decode_locale(cfg)
                    tname = type_names[tid - 1] if 0 < tid <= len(type_names) else f"type{tid}"
                    types[tname] += entry_count
                    configs[loc] += 1
                    type_ids.add(tid)
                    pkg_info["entry_total"] += entry_count
                elif t2 == RES_TABLE_TYPE_SPEC:
                    tid = struct.unpack_from("<B", data, p + 8)[0]
                    if tid and tid <= len(type_names):
                        type_names[tid - 1] = type_names[tid - 1]
                p += s2
            pkg_info["types"] = dict(sorted(types.items(), key=lambda kv: -kv[1]))
            pkg_info["config_count"] = sum(configs.values())
            pkg_info["locales"] = dict(configs.most_common())
            pkg_info["type_count"] = len(types)
            packages.append(pkg_info)
        elif ctype == RES_TABLE_LIBRARY:
            pass
        off += csz
    return {"packages": packages, "global_string_pool_size": global_strings,
            "table_bytes": len(data)}


def _read_pool(data: bytes, sp: int, count: int) -> list[str]:
    """Decode a RES_STRING_POOL_TYPE at sp -> list of strings (UTF-8 or UTF-16)."""
    hsize, = struct.unpack_from("<H", data, sp + 2)
    _csize, = struct.unpack_from("<I", data, sp + 4)
    string_count, style_count = struct.unpack_from("<II", data, sp + 8)
    flags, strings_start, _styles_start = struct.unpack_from("<III", data, sp + 16)
    utf8 = bool(flags & 0x100)
    offsets = [struct.unpack_from("<I", data, sp + hsize + 4 * i)[0] for i in range(min(string_count, count))]
    out = []
    for o in offsets:
        p = sp + strings_start + o
        try:
            if utf8:
                # utf16 length (uleb), then utf8 byte length (uleb)
                n, p2 = _uleb(data, p)
                n2, p2 = _uleb(data, p2)
                out.append(data[p2:p2 + n2].decode("utf-8", "replace"))
            else:
                # UTF-16 pool: uint16 character count, then UTF-16LE code units
                n = struct.unpack_from("<H", data, p)[0]
                p2 = p + 2
                out.append(data[p2:p2 + n * 2].decode("utf-16-le", "replace"))
        except Exception:  # noqa: BLE001
            out.append("")
    return out


def _uleb(data: bytes, p: int) -> tuple[int, int]:
    v = s = 0
    while True:
        b = data[p]; p += 1
        v |= (b & 0x7F) << s
        if not b & 0x80:
            return v, p
        s += 7


def _uleb16(data: bytes, p: int) -> tuple[int, int]:
    v = data[p]; p += 1
    if v & 0x80:
        v = ((v & 0x7F) << 8) | data[p]; p += 1
    return v, p
