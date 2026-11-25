"""resources.arsc (ResTable) reader.

The Android resource table is a tree of ``ResChunk_header`` structures:

    RES_TABLE          global value string pool + N package chunks
      RES_TABLE_PACKAGE  type-string pool, key-string pool,
                         RES_TABLE_TYPE_SPEC_* + RES_TABLE_TYPE_*
      RES_TABLE_TYPE     one (type, config) pair: a sorted offset array into
                         its EntriesStart, each entry = ResValue|ResTable_map

We decode it because the interesting security artifacts hide in it:

  * ``android:networkSecurityConfig="@0x7f16000d"`` -> which res/xml file?
  * 13 resource *packages* inside one APK means 13 Gradle modules shipped
    with their own resource namespaces (feature/module structure leak).
  * the locale set tells you which markets the build was cut for.
"""

from __future__ import annotations

import struct
from collections import Counter, defaultdict
from dataclasses import dataclass, field
from typing import Dict, List, Optional, Tuple

RES_STRING_POOL_TYPE = 0x0001
RES_TABLE_TYPE = 0x0002
RES_TABLE_PACKAGE_TYPE = 0x0200
RES_TABLE_TYPE_TYPE = 0x0201
RES_TABLE_TYPE_SPEC_TYPE = 0x0202
RES_TABLE_LIBRARY_TYPE = 0x0203

TYPE_NAMES = ("null", "reference", "attribute", "string", "float", "dimension",
              "fraction", "dynamic_reference", "dynamic_attribute", "int_dec",
              "int_hex", "int_boolean", "color_arsc", "color_rgb8", "color_rgb4",
              "color_rgb", "int_color", "int_color_arsc")


def _decode_len8(buf: bytes, off: int) -> Tuple[int, int]:
    b = buf[off]
    if b & 0x80:
        return ((b & 0x7F) << 8) | buf[off + 1], off + 2
    return b, off + 1


def read_string_pool(buf: bytes, off: int) -> Tuple[List[str], int, bool]:
    """Return (strings, header_size, utf8)."""
    (_type, header_size, size) = struct.unpack_from("<HHI", buf, off)
    (string_count, style_count, flags, strings_start, styles_start) = struct.unpack_from(
        "<IIIII", buf, off + 8)
    utf8 = bool(flags & 0x100)
    offsets = struct.unpack_from(f"<{string_count}I", buf, off + header_size)
    base = off + strings_start
    out: List[str] = []
    for o in offsets:
        p = base + o
        if utf8:
            _n, p = _decode_len8(buf, p)       # utf-16 length
            n, p = _decode_len8(buf, p)        # utf-8 length
            out.append(buf[p:p + n].decode("utf-8", "replace"))
        else:
            n, p = struct.unpack_from("<H", buf, p)[0], p + 2
            out.append(buf[p:p + n * 2].decode("utf-16-le", "replace"))
    return out, header_size, utf8


def decode_config(blob: bytes) -> dict:
    size = len(blob)
    if size < 4:
        return {}
    cfg = {}
    if size >= 8:
        mcc, mnc = struct.unpack_from("<HH", blob, 4)
        if mcc:
            cfg["mcc"] = mcc
        if mnc:
            cfg["mnc"] = mnc
    if size >= 12:
        lang = blob[8:10]
        country = blob[10:12]
        if lang != b"\x00\x00":
            cfg["language"] = lang.decode("ascii", "replace")
        if country != b"\x00\x00":
            cfg["country"] = country.decode("ascii", "replace")
    if size >= 16:
        orientation, touchscreen, density = struct.unpack_from("<BBH", blob, 12)
        if density:
            cfg["density"] = density
        if orientation:
            cfg["orientation"] = orientation
    if size >= 20:
        _kb, _nav, _ih, _iw = struct.unpack_from("<BBHH", blob, 16)
    if size >= 24:
        sdk, minor = struct.unpack_from("<HH", blob, 20)
        if sdk:
            cfg["sdk"] = sdk
    return cfg


def locale_key(cfg: dict) -> str:
    lang = cfg.get("language")
    if not lang:
        return "default"
    c = cfg.get("country")
    return f"{lang}-{c}" if c else lang


@dataclass
class ResEntry:
    res_id: int
    type_name: str
    key: str
    config: dict
    value_type: Optional[int] = None
    data: Optional[int] = None
    string_value: Optional[str] = None

    def to_json(self) -> dict:
        return {"res_id": hex(self.res_id), "type": self.type_name, "key": self.key,
                "config": self.config, "value_type": self.value_type,
                "string_value": self.string_value}


class ResTable:
    def __init__(self, buf: bytes):
        self.buf = buf
        (_t, self.header_size, self.total_size, self.package_count) = struct.unpack_from("<HHII", buf, 0)
        self.global_strings: List[str] = []
        self.packages: List[dict] = []
        self.entries: Dict[int, ResEntry] = {}
        self._parse()

    def _parse(self) -> None:
        buf = self.buf
        off = self.header_size
        end = min(len(buf), self.total_size)
        pkg_chunks = []
        while off + 8 <= end:
            ctype, hsize, csize = struct.unpack_from("<HHI", buf, off)
            if csize < 8:
                break
            if ctype == RES_STRING_POOL_TYPE:
                self.global_strings, _hs, _u = read_string_pool(buf, off)
            elif ctype == RES_TABLE_PACKAGE_TYPE:
                pkg_chunks.append(off)
            off += csize
        for pkg_off in pkg_chunks:
            self._parse_package(pkg_off)

    def _parse_package(self, pkg_off: int) -> None:
        buf = self.buf
        (_t, hsize, csize) = struct.unpack_from("<HHI", buf, pkg_off)
        pkg_id = struct.unpack_from("<I", buf, pkg_off + 8)[0]
        name = buf[pkg_off + 12:pkg_off + 12 + 256].decode("utf-16-le", "replace").split("\x00")[0]
        type_strings_off = struct.unpack_from("<I", buf, pkg_off + 268)[0]
        key_strings_off = struct.unpack_from("<I", buf, pkg_off + 276)[0]
        type_strings: List[str] = []
        key_strings: List[str] = []
        if type_strings_off:
            type_strings, _hs, _u = read_string_pool(buf, pkg_off + type_strings_off)
        if key_strings_off:
            key_strings, _hs, _u = read_string_pool(buf, pkg_off + key_strings_off)

        pkg = {"id": pkg_id, "name": name, "types": Counter(), "entries": 0,
               "configs": set(), "specs": 0}
        off = pkg_off + hsize
        pkg_end = pkg_off + csize
        while off + 8 <= pkg_end:
            ctype, chsize, csize2 = struct.unpack_from("<HHI", buf, off)
            if csize2 < 8:
                break
            if ctype == RES_TABLE_TYPE_SPEC_TYPE:
                pkg["specs"] += 1
            elif ctype == RES_TABLE_TYPE_TYPE:
                self._parse_type(off, pkg_id, type_strings, key_strings, pkg)
            off += csize2
        pkg["configs"] = sorted(pkg["configs"])
        pkg["typeCount"] = len(pkg["types"])
        pkg["types"] = dict(pkg["types"].most_common())
        self.packages.append(pkg)

    def _parse_type(self, off: int, pkg_id: int, type_strings: List[str],
                    key_strings: List[str], pkg: dict) -> None:
        buf = self.buf
        (_t, hsize, csize) = struct.unpack_from("<HHI", buf, off)
        type_id = buf[off + 8]
        flags = buf[off + 9]
        entry_count, entries_start = struct.unpack_from("<II", buf, off + 12)
        cfg_size = struct.unpack_from("<I", buf, off + 20)[0]
        cfg = decode_config(buf[off + 20:off + 20 + cfg_size])
        pkg["configs"].add(locale_key(cfg))
        tname = type_strings[type_id - 1] if 0 < type_id <= len(type_strings) else f"type{type_id}"
        pkg["types"][tname] += entry_count
        pkg["entries"] += entry_count
        idx = off + hsize
        for i in range(entry_count):
            if idx + 4 > off + csize:
                break
            (eoff,) = struct.unpack_from("<I", buf, idx)
            idx += 4
            if eoff == 0xFFFFFFFF:
                continue
            pos = off + entries_start + eoff
            if pos + 8 > len(buf):
                continue
            esize, eflags, ekey = struct.unpack_from("<HHI", buf, pos)
            key = key_strings[ekey] if ekey < len(key_strings) else f"key{ekey}"
            res_id = (pkg_id << 24) | (type_id << 16) | i
            ent = ResEntry(res_id=res_id, type_name=tname, key=key, config=cfg)
            if eflags & 0x0001:  # complex (bag/map)
                ent.value_type = None
            else:
                vpos = pos + esize
                if vpos + 8 <= len(buf):
                    _vs, _r0, vtype, vdata = struct.unpack_from("<HBBI", buf, vpos)
                    ent.value_type = vtype
                    ent.data = vdata
                    if vtype == 0x03 and vdata < len(self.global_strings):
                        ent.string_value = self.global_strings[vdata]
            prev = self.entries.get(res_id)
            # prefer the default config, otherwise keep the first seen
            if prev is None or (locale_key(cfg) == "default" and locale_key(prev.config) != "default"):
                self.entries[res_id] = ent

    # -- queries -----------------------------------------------------------
    def resolve(self, res_id: str) -> Optional[ResEntry]:
        try:
            rid = int(res_id, 0)
        except ValueError:
            return None
        return self.entries.get(rid)

    def find_by_key(self, key: str, type_name: Optional[str] = None) -> List[ResEntry]:
        return [e for e in self.entries.values()
                if e.key == key and (type_name is None or e.type_name == type_name)]

    def census(self) -> dict:
        locales = Counter()
        type_totals = Counter()
        for e in self.entries.values():
            locales[locale_key(e.config)] += 1
            type_totals[e.type_name] += 1
        return {
            "package_count": self.package_count,
            "packages": [{"id": p["id"], "name": p["name"], "typeCount": p["typeCount"],
                          "entries": p["entries"], "specs": p["specs"],
                          "locales": p["configs"][:40],
                          "types": dict(list(p["types"].items())[:25])} for p in self.packages],
            "total_entries": len(self.entries),
            "global_strings": len(self.global_strings),
            "type_totals": dict(type_totals.most_common(40)),
            "locales": dict(locales.most_common(60)),
            "locale_count": len([l for l in locales if l != "default"]),
        }
