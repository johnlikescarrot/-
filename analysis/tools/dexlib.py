"""dexlib — dependency-free parsers for Android DEX and ELF (ARM) artifacts.

Written for the ZaloPay 11.17.0 static-analysis study.  Everything here is
pure ``struct`` work: no decompiler, no JVM, no native toolchain.  The goal is
*measurement* — counts, inventories, symbol tables, section-level hardening —
that can be re-derived byte-for-byte from the published artifact hashes.

Reference formats:
  * DEX  : https://source.android.com/docs/core/runtime/dex-format
  * ELF32: System V gABI + ARM ELF ABI (IHI 0044)
"""

from __future__ import annotations

import hashlib
import struct
import zipfile
from dataclasses import dataclass, field
from typing import Iterable, Iterator

# --------------------------------------------------------------------------- #
# DEX
# --------------------------------------------------------------------------- #

ACC_PUBLIC = 0x0001
ACC_PRIVATE = 0x0002
ACC_PROTECTED = 0x0004
ACC_STATIC = 0x0008
ACC_FINAL = 0x0010
ACC_NATIVE = 0x0100
ACC_INTERFACE = 0x0200
ACC_ABSTRACT = 0x0400
ACC_SYNTHETIC = 0x1000


def _uleb128(buf: bytes, off: int) -> tuple[int, int]:
    result = 0
    shift = 0
    while True:
        b = buf[off]
        off += 1
        result |= (b & 0x7F) << shift
        if not b & 0x80:
            return result, off
        shift += 7


def _mutf8(buf: bytes, off: int) -> tuple[str, int]:
    """Decode a DEX MUTF-8 string (NUL-terminated) at ``off``."""
    # utf16_size is a uleb128 prefix; skip it, then scan to the NUL terminator.
    _, p = _uleb128(buf, off)
    end = buf.find(b"\x00", p)
    raw = buf[p:end]
    if b"\xc0\x80" in raw:
        raw = raw.replace(b"\xc0\x80", b"\x00")
    try:
        s = raw.decode("utf-8", "surrogatepass")
    except UnicodeDecodeError:
        s = raw.decode("utf-8", "replace")
    return s, end + 1


@dataclass
class DexStats:
    name: str
    size: int
    sha256: str
    string_ids: int
    type_ids: int
    proto_ids: int
    field_ids: int
    method_ids: int
    class_defs: int
    classes: list[str] = field(default_factory=list)
    native_methods: list[str] = field(default_factory=list)
    strings: list[str] = field(default_factory=list)

    def to_json(self, *, keep_strings: bool = True) -> dict:
        d = {
            "name": self.name,
            "size": self.size,
            "sha256": self.sha256,
            "string_ids": self.string_ids,
            "type_ids": self.type_ids,
            "proto_ids": self.proto_ids,
            "field_ids": self.field_ids,
            "method_ids": self.method_ids,
            "class_defs": self.class_defs,
            "classes": self.classes,
        }
        if keep_strings:
            d["strings"] = self.strings
        return d


def parse_dex(name: str, blob: bytes) -> DexStats:
    if blob[:4] != b"dex\n":
        raise ValueError(f"{name}: not a DEX file")
    (string_ids_size, string_ids_off,
     type_ids_size, type_ids_off,
     proto_ids_size, proto_ids_off,
     field_ids_size, field_ids_off,
     method_ids_size, method_ids_off,
     class_defs_size, class_defs_off) = struct.unpack_from("<12I", blob, 56)

    strings: list[str] = []
    for i in range(string_ids_size):
        (data_off,) = struct.unpack_from("<I", blob, string_ids_off + 4 * i)
        s, _ = _mutf8(blob, data_off)
        strings.append(s)

    types = [strings[struct.unpack_from("<I", blob, type_ids_off + 4 * i)[0]]
             for i in range(type_ids_size)]

    classes: list[str] = []
    native_methods: list[str] = []
    for i in range(class_defs_size):
        base = class_defs_off + 32 * i
        (class_idx, _access, _super, _ifaces, _src,
         _annos, class_data_off, _static) = struct.unpack_from("<8I", blob, base)
        if class_idx >= len(types):
            continue
        cls = types[class_idx]
        classes.append(cls)
        if not class_data_off:
            continue
        # class_data_item: static fields, instance fields, direct methods, virtual methods
        p = class_data_off
        sf, p = _uleb128(blob, p)
        inf, p = _uleb128(blob, p)
        dm, p = _uleb128(blob, p)
        vm, p = _uleb128(blob, p)
        idx = 0
        for _ in range(sf):
            _, p = _uleb128(blob, p)
            _, p = _uleb128(blob, p)
        idx = 0
        for _ in range(inf):
            _, p = _uleb128(blob, p)
            _, p = _uleb128(blob, p)
        idx = 0
        for count, is_direct in ((dm, True), (vm, False)):
            idx = 0
            for _ in range(count):
                diff, p = _uleb128(blob, p)
                access, p = _uleb128(blob, p)
                _code, p = _uleb128(blob, p)
                idx += diff
                if is_direct and access & ACC_NATIVE:
                    midx = idx
                    if midx < method_ids_size:
                        mo = method_ids_off + 8 * midx
                        _cls, _proto, name_idx = struct.unpack_from("<HHI", blob, mo)
                        if name_idx < len(strings):
                            native_methods.append(f"{cls}->{strings[name_idx]}")
    return DexStats(
        name=name,
        size=len(blob),
        sha256=hashlib.sha256(blob).hexdigest(),
        string_ids=string_ids_size,
        type_ids=type_ids_size,
        proto_ids=proto_ids_size,
        field_ids=field_ids_size,
        method_ids=method_ids_size,
        class_defs=class_defs_size,
        classes=classes,
        native_methods=native_methods,
        strings=strings,
    )


# --------------------------------------------------------------------------- #
# ELF
# --------------------------------------------------------------------------- #

SHT_DYNSYM = 11
SHT_GNU_versym = 0x6FFFFFFF
DT_BIND_NOW = 24
DT_FLAGS = 30
DT_FLAGS_1 = 0x6FFFFFFB
DF_BIND_NOW = 0x8
DF_1_NOW = 0x1
PT_GNU_STACK = 0x6474E551
PT_GNU_RELRO = 0x6474E552


@dataclass
class ElfInfo:
    name: str
    size: int
    sha256: str
    bits: int
    machine: str
    type: str
    soname: str | None
    needed: list[str]
    exported: list[str]
    jni_exports: list[str]
    pie: bool
    nx: bool
    relro: str
    bind_now: bool
    canary: bool
    fortify_symbols: int
    stripped: bool
    strings: list[str] = field(default_factory=list)

    def to_json(self, *, keep_strings: bool = True) -> dict:
        d = {
            "name": self.name, "size": self.size, "sha256": self.sha256,
            "bits": self.bits, "machine": self.machine, "type": self.type,
            "soname": self.soname, "needed": self.needed,
            "exported_count": len(self.exported),
            "jni_export_count": len(self.jni_exports),
            "jni_exports_sample": self.jni_exports[:40],
            "pie": self.pie, "nx": self.nx, "relro": self.relro,
            "bind_now": self.bind_now, "canary": self.canary,
            "fortify_symbols": self.fortify_symbols,
            "stripped": self.stripped,
        }
        if keep_strings:
            d["strings"] = self.strings
        return d


_MACHINES = {40: "ARM", 183: "AArch64", 3: "x86", 62: "x86-64"}
_ETYPES = {1: "REL", 2: "EXEC", 3: "DYN", 4: "CORE"}


def _cstr(buf: bytes, off: int) -> str:
    end = buf.find(b"\x00", off)
    return buf[off:end].decode("utf-8", "replace")


def parse_elf(name: str, blob: bytes) -> ElfInfo:
    ei_class = blob[4]
    ei_data = blob[5]
    endian = "<" if ei_data == 1 else ">"
    bits = 32 if ei_class == 1 else 64
    e_type, e_machine = struct.unpack_from(endian + "HH", blob, 16)
    if bits == 32:
        (_ver, e_entry, e_phoff, e_shoff, _flags, _ehsize, e_phentsize, e_phnum,
         e_shentsize, e_shnum, e_shstrndx) = struct.unpack_from(endian + "5I6H", blob, 20)
    else:
        (_ver, e_entry, e_phoff, e_shoff, _flags, _ehsize, e_phentsize, e_phnum,
         e_shentsize, e_shnum, e_shstrndx) = struct.unpack_from(endian + "IQQQIHHHHHH", blob, 20)

    sections = []
    for i in range(e_shnum):
        off = e_shoff + i * e_shentsize
        if bits == 32:
            s_name, s_type, s_flags, s_addr, s_off, s_size, s_link, s_info, s_align, s_entsize = \
                struct.unpack_from(endian + "10I", blob, off)
        else:
            s_name, s_type, s_flags, s_addr, s_off, s_size, s_link, s_info, s_align, s_entsize = \
                struct.unpack_from(endian + "IIQQQQIIQQ", blob, off)
        sections.append(dict(name_off=s_name, type=s_type, flags=s_flags, off=s_off,
                             size=s_size, link=s_link, entsize=s_entsize))
    shstr = sections[e_shstrndx] if 0 <= e_shstrndx < len(sections) else None
    for s in sections:
        s["name"] = _cstr(blob, shstr["off"] + s["name_off"]) if shstr else ""

    dynsym = next((s for s in sections if s["type"] == SHT_DYNSYM), None)
    dynstr = sections[dynsym["link"]] if dynsym and dynsym["link"] < len(sections) else None
    symbols: list[str] = []
    if dynsym and dynstr and dynsym["entsize"]:
        n = dynsym["size"] // dynsym["entsize"]
        for i in range(n):
            o = dynsym["off"] + i * dynsym["entsize"]
            if bits == 32:
                st_name, st_value, st_size, st_info, st_other, st_shndx = struct.unpack_from(endian + "IIIBBH", blob, o)
            else:
                st_name, st_info, st_other, st_shndx, st_value, st_size = struct.unpack_from(endian + "IBBHQQ", blob, o)
            if not st_name:
                continue
            nm = _cstr(blob, dynstr["off"] + st_name)
            bind = st_info >> 4
            if bind in (1, 2) and st_shndx != 0:  # GLOBAL / WEAK, defined
                symbols.append(nm)
    exported = sorted(set(symbols))
    jni = [s for s in exported if s.startswith("Java_")]

    # dynamic section
    dyn = next((s for s in sections if s["type"] == 6), None)
    needed: list[str] = []
    soname = None
    bind_now = False
    if dyn and dynstr:
        for i in range(dyn["size"] // 8):
            tag, val = struct.unpack_from(endian + "iI" if bits == 32 else endian + "qQ", blob, dyn["off"] + i * 8)
            if tag == 0:
                break
            if tag == 1:      # DT_NEEDED
                needed.append(_cstr(blob, dynstr["off"] + val))
            elif tag == 14:   # DT_SONAME
                soname = _cstr(blob, dynstr["off"] + val)
            elif tag == DT_BIND_NOW:
                bind_now = True
            elif tag == DT_FLAGS and val & DF_BIND_NOW:
                bind_now = True
            elif tag == DT_FLAGS_1 and val & DF_1_NOW:
                bind_now = True

    # program headers
    has_relro = False
    nx = False
    for i in range(e_phnum):
        off = e_phoff + i * e_phentsize
        if bits == 32:
            p_type, _p_off, _p_vaddr, _p_paddr, _p_filesz, _p_memsz, p_flags, _p_align = \
                struct.unpack_from(endian + "8I", blob, off)
        else:
            p_type, p_flags, _p_off, _p_vaddr, _p_paddr, _p_filesz, _p_memsz, _p_align = \
                struct.unpack_from(endian + "IIQQQQQQ", blob, off)
        if p_type == PT_GNU_RELRO:
            has_relro = True
        if p_type == PT_GNU_STACK:
            nx = not (p_flags & 0x1)

    text = blob
    canary = b"__stack_chk_fail" in text
    fortify = sum(text.count(s) for s in (b"__memcpy_chk", b"__strcpy_chk", b"__sprintf_chk", b"__vsnprintf_chk"))

    strings = sorted({s.decode("utf-8", "replace") for s in _extract_ascii_strings(blob, 6, 160)})
    return ElfInfo(
        name=name, size=len(blob), sha256=hashlib.sha256(blob).hexdigest(),
        bits=bits, machine=_MACHINES.get(e_machine, str(e_machine)),
        type=_ETYPES.get(e_type, str(e_type)), soname=soname, needed=needed,
        exported=exported, jni_exports=jni,
        pie=(e_type == 3), nx=nx,
        relro="full" if (has_relro and bind_now) else ("partial" if has_relro else "none"),
        bind_now=bind_now, canary=canary, fortify_symbols=fortify,
        stripped=not any(s["name"] == ".symtab" for s in sections),
        strings=strings,
    )


def _extract_ascii_strings(blob: bytes, min_len: int = 6, max_len: int = 512) -> Iterator[bytes]:
    cur: list[bytes] = []
    run = 0
    for b in blob:
        if 32 <= b < 127:
            if run < max_len:
                cur.append(bytes((b,)))
            run += 1
        else:
            if run >= min_len:
                yield b"".join(cur)
            cur, run = [], 0
    if run >= min_len:
        yield b"".join(cur)


# --------------------------------------------------------------------------- #
# APK / APK Signing Block
# --------------------------------------------------------------------------- #

APK_SIG_V2 = 0x7109871A
APK_SIG_V3 = 0xF05368C0
APK_SIG_V31 = 0x1B93AD61


def apk_signing_block(path: str) -> dict:
    """Parse the APK Signing Block and report which schemes are present."""
    with open(path, "rb") as fh:
        fh.seek(0, 2)
        end = fh.tell()
        fh.seek(max(0, end - 64))
        tail = fh.read()
        pos = tail.rfind(b"PK\x05\x06")
        if pos < 0:
            return {"present": False}
        cd_size, cd_off = struct.unpack_from("<II", tail, pos + 12)
        if cd_off < 32:
            return {"present": False}
        fh.seek(cd_off - 24)
        footer = fh.read(24)
        if footer[8:] != b"APK Sig Block 42":
            return {"present": False}
        size = struct.unpack_from("<Q", footer, 0)[0]
        block_off = cd_off - size - 8
        fh.seek(block_off)
        block = fh.read(size + 8)
    pairs = block[8:-24]
    found = {}
    p = 0
    while p + 12 <= len(pairs):
        ln = struct.unpack_from("<Q", pairs, p)[0]
        pid = struct.unpack_from("<I", pairs, p + 8)[0]
        val = pairs[p + 12: p + 8 + ln]
        if pid == APK_SIG_V2:
            found["v2"] = len(val)
        elif pid in (APK_SIG_V3, APK_SIG_V31):
            found["v3"] = len(val)
        else:
            found[f"0x{pid:08x}"] = len(val)
        p += 8 + ln
    return {"present": True, "central_directory_offset": cd_off,
            "central_directory_size": cd_size, "schemes": sorted(found)}


def apk_inventory(path: str) -> dict:
    with zipfile.ZipFile(path) as z:
        infos = z.infolist()
        total = sum(i.file_size for i in infos)
        by_ext: dict[str, dict[str, int]] = {}
        for i in infos:
            ext = ("." + i.filename.rsplit(".", 1)[1].lower()) if "." in i.filename.rsplit("/", 1)[-1] else "(none)"
            slot = by_ext.setdefault(ext, {"count": 0, "bytes": 0})
            slot["count"] += 1
            slot["bytes"] += i.file_size
        return {
            "path": path,
            "entries": len(infos),
            "uncompressed_bytes": total,
            "compressed_bytes": sum(i.compress_size for i in infos),
            "by_extension": dict(sorted(by_ext.items(), key=lambda kv: -kv[1]["bytes"])),
            "largest": [
                {"name": i.filename, "bytes": i.file_size}
                for i in sorted(infos, key=lambda i: -i.file_size)[:25]
            ],
        }


def sha256_file(path: str, chunk: int = 1 << 20) -> str:
    h = hashlib.sha256()
    with open(path, "rb") as fh:
        while True:
            b = fh.read(chunk)
            if not b:
                break
            h.update(b)
    return h.hexdigest()
