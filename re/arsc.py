"""
arsc.py — a compact, dependency-free `resources.arsc` reader.

`resources.arsc` is a RES_TABLE chunk containing packages, each of which holds a
key string pool, a type name pool, a TYPE_SPEC per type and one TYPE chunk per
(type, configuration) pair. The configuration blob starts with a 4-byte size,
then `language[2]` and `country[2]` — enough to enumerate every locale the app
ships translations for without instantiating androguard's full resource model.

Chunk type constants (frameworks/base/libs/androidfw/include/androidfw/ResourceTypes.h):
  0x0001 RES_STRING_POOL_TYPE   0x0002 RES_TABLE_TYPE
  0x0200 RES_TABLE_PACKAGE_TYPE 0x0201 RES_TABLE_TYPE_TYPE
  0x0202 RES_TABLE_TYPE_SPEC_TYPE 0x0203 RES_TABLE_LIBRARY_TYPE
  0x0204 RES_TABLE_OVERLAYABLE_TYPE 0x0205 RES_TABLE_OVERLAYABLE_POLICY_TYPE
  0x0206 RES_TABLE_STAGED_ALIAS_TYPE
"""
from __future__ import annotations

import struct
from collections import Counter
from typing import Any

RES_STRING_POOL = 0x0001
RES_TABLE = 0x0002
RES_TABLE_PACKAGE = 0x0200
RES_TABLE_TYPE = 0x0201
RES_TABLE_TYPE_SPEC = 0x0202
RES_TABLE_LIBRARY = 0x0203

CHUNK_NAMES = {
    0x0001: "string_pool", 0x0002: "table", 0x0003: "xml", 0x0200: "package",
    0x0201: "type", 0x0202: "type_spec", 0x0203: "library", 0x0204: "overlayable",
    0x0205: "overlayable_policy", 0x0206: "staged_alias",
}

# Android config struct: after `size`, language occupies bytes 8..10, country 10..12
CONFIG_LANG_OFF, CONFIG_CC_OFF = 8, 10


def _chunks(blob: bytes, start: int, end: int):
    """Yield (type, header_size, size, offset) for the chunk stream in [start,end)."""
    p = start
    while p + 8 <= end:
        ctype, hsize, size = struct.unpack_from("<HHI", blob, p)
        if size < 8 or p + size > end:
            break
        yield ctype, hsize, size, p
        p += size


def _string_pool(blob: bytes, off: int) -> dict[str, Any]:
    ctype, hsize, size = struct.unpack_from("<HHI", blob, off)
    count, style_count, flags, strings_start, styles_start = struct.unpack_from("<IIIII", blob, off + 8)
    utf8 = bool(flags & 0x100)
    offsets = struct.unpack_from(f"<{count}I", blob, off + hsize)
    base = off + strings_start
    out = []
    for o in offsets[:count]:
        p = base + o
        try:
            if utf8:
                # utf16 length (varint), then utf8 length (varint), then bytes
                n = blob[p]; p += 2 if n & 0x80 else 1
                n = blob[p]; p += 2 if n & 0x80 else 1
                out.append(blob[p:p + n].decode("utf-8", "replace"))
            else:
                (n,) = struct.unpack_from("<H", blob, p)
                p += 2
                if n & 0x8000:
                    n = ((n & 0x7FFF) << 16) | struct.unpack_from("<H", blob, p)[0]
                    p += 2
                out.append(blob[p:p + n * 2].decode("utf-16-le", "replace"))
        except Exception:
            out.append("")
    return {"count": count, "utf8": utf8, "strings": out}


def census(blob: bytes) -> dict[str, Any]:
    res: dict[str, Any] = {
        "size": len(blob),
        "packages": [],
        "chunk_histogram": {},
        "n_type_chunks": 0,
        "n_type_spec": 0,
        "locales": {},
        "n_locales": 0,
        "type_names": {},
        "key_count": 0,
        "string_pool": {},
        "density_buckets": {},
    }
    hist: Counter = Counter()
    locales: Counter = Counter()
    if len(blob) < 12:
        return res
    t_type, t_hsize, t_size = struct.unpack_from("<HHI", blob, 0)
    if t_type != RES_TABLE:
        return {**res, "error": f"not a RES_TABLE (type={t_type:#x})"}
    res["package_count_header"] = struct.unpack_from("<I", blob, 8)[0]
    top_pool = None
    for ctype, hsize, size, off in _chunks(blob, t_hsize, t_size):
        hist[CHUNK_NAMES.get(ctype, hex(ctype))] += 1
        if ctype == RES_STRING_POOL and top_pool is None:
            sp = _string_pool(blob, off)
            top_pool = sp
            res["string_pool"] = {"count": sp["count"], "utf8": sp["utf8"]}
        elif ctype == RES_TABLE_PACKAGE:
            pkg_id, name = struct.unpack_from("<I", blob, off + 8)[0], blob[off + 12:off + 12 + 256].decode("utf-16-le", "replace").split("\x00")[0]
            pkg = {"id": pkg_id, "name": name, "types": 0, "type_specs": 0, "keys": 0, "strings": 0,
                   "entries": 0, "aliases": 0}
            for c2, h2, s2, o2 in _chunks(blob, off + hsize, off + size):
                hist[CHUNK_NAMES.get(c2, hex(c2))] += 1
                if c2 == RES_STRING_POOL:
                    sp = _string_pool(blob, o2)
                    if pkg["keys"] == 0:
                        pkg["keys"] = sp["count"]
                    else:
                        pkg["strings"] = sp["count"]
                elif c2 == RES_TABLE_TYPE:
                    pkg["types"] += 1
                    res["n_type_chunks"] += 1
                    try:
                        tid, _flags, _res, entry_count, entries_start = struct.unpack_from("<BBHII", blob, o2 + 8)
                        tname = _type_name(blob, off, hsize, tid)
                        res["type_names"][tname] = res["type_names"].get(tname, 0) + 1
                        cfg_off = o2 + 20
                        cfg_size = struct.unpack_from("<I", blob, cfg_off)[0]
                        lang = blob[cfg_off + CONFIG_LANG_OFF:cfg_off + CONFIG_LANG_OFF + 2]
                        cc = blob[cfg_off + CONFIG_CC_OFF:cfg_off + CONFIG_CC_OFF + 2]
                        if lang != b"\x00\x00":
                            loc = lang.decode("ascii", "replace")
                            if cc != b"\x00\x00":
                                loc += "-" + cc.decode("ascii", "replace")
                            locales[loc] += 1
                        # density qualifier lives at byte 14 of the config (uint16)
                        dens = struct.unpack_from("<H", blob, cfg_off + 14)[0]
                        key = {0: "default", 120: "ldpi", 160: "mdpi", 213: "tvdpi",
                               240: "hdpi", 320: "xhdpi", 480: "xxhdpi", 640: "xxxhdpi",
                               0xFFFE: "anydpi", 0xFFFF: "nodpi"}.get(dens, str(dens))
                        res["density_buckets"][key] = res["density_buckets"].get(key, 0) + 1
                        pkg["entries"] += entry_count
                    except Exception:
                        pass
                elif c2 == RES_TABLE_TYPE_SPEC:
                    pkg["type_specs"] += 1
                    res["n_type_spec"] += 1
                elif c2 == RES_TABLE_LIBRARY:
                    pkg["aliases"] += 1
            res["packages"].append(pkg)
    res["chunk_histogram"] = dict(hist)
    res["locales"] = dict(locales.most_common())
    res["n_locales"] = len(locales)
    res["key_count"] = sum(p["keys"] for p in res["packages"])
    return res


def _type_name(blob: bytes, pkg_off: int, pkg_hsize: int, type_id: int) -> str:
    """Resolve a 1-based type id through the package's type-string pool."""
    try:
        # first string pool inside the package is the type name pool
        for ctype, hsize, size, off in _chunks(blob, pkg_off + pkg_hsize, pkg_off + len(blob)):
            if ctype == RES_STRING_POOL:
                sp = _string_pool(blob, off)
                names = sp["strings"]
                return names[type_id - 1] if 0 < type_id <= len(names) else f"type{type_id}"
            break
    except Exception:
        pass
    return f"type{type_id}"


if __name__ == "__main__":
    import sys

    b = open(sys.argv[1], "rb").read()
    c = census(b)
    print("packages:", c["packages"])
    print("locales:", c["n_locales"], c["locales"])
    print("type chunks:", c["n_type_chunks"], "specs:", c["n_type_spec"])
    print("types:", sorted(c["type_names"].items(), key=lambda kv: -kv[1])[:15])
    print("density:", c["density_buckets"])
