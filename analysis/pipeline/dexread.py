"""A self-contained Dalvik Executable (DEX) reader.

Why not just use androguard?  Because androguard materialises every annotation,
debug-info stream and code item.  For a 74 MB / 19-file corpus that costs minutes
and gigabytes.  This reader touches only the indexed tables that static triage
needs — string/type/proto/field/method/class pools and the class_data_item
linked lists — and parses the whole corpus in seconds with a flat memory profile.

Format reference: `Dalvik Executable format`, Android Open Source Project
(art/runtime/dex_file.h).  All offsets are little-endian.

Layout used here::

    header (0x70 bytes)
      magic 8 | checksum 4 | signature 20 | file_size 4 | header_size 4
      endian_tag 4 | link_size 4 | link_off 4 | map_off 4
      string_ids_size 4 | string_ids_off 4
      type_ids_size 4 | type_ids_off 4
      proto_ids_size 4 | proto_ids_off 4
      field_ids_size 4 | field_ids_off 4
      method_ids_size 4 | method_ids_off 4
      class_defs_size 4 | class_defs_off 4
      data_size 4 | data_off 4
"""

from __future__ import annotations

import hashlib
import struct
from dataclasses import dataclass, field
from pathlib import Path
from typing import Iterator

# Access flags (art/runtime/access_flags.h)
ACC_PUBLIC = 0x0001
ACC_PRIVATE = 0x0002
ACC_PROTECTED = 0x0004
ACC_STATIC = 0x0008
ACC_FINAL = 0x0010
ACC_SYNCHRONIZED = 0x0020
ACC_VOLATILE = 0x0040
ACC_BRIDGE = 0x0040
ACC_TRANSIENT = 0x0080
ACC_VARARGS = 0x0080
ACC_NATIVE = 0x0100
ACC_INTERFACE = 0x0200
ACC_ABSTRACT = 0x0400
ACC_STRICT = 0x0800
ACC_SYNTHETIC = 0x1000
ACC_ANNOTATION = 0x2000
ACC_ENUM = 0x4000
ACC_CONSTRUCTOR = 0x10000
ACC_DECLARED_SYNCHRONIZED = 0x20000

DEX_MAGICS = {b"dex\n035\x00", b"dex\n036\x00", b"dex\n037\x00", b"dex\n038\x00", b"dex\n039\x00", b"dex\n040\x00"}


def _uleb128(buf: memoryview, off: int) -> tuple[int, int]:
    result = shift = 0
    while True:
        b = buf[off]
        off += 1
        result |= (b & 0x7F) << shift
        if not b & 0x80:
            return result, off
        shift += 7


def _mutf8(raw: bytes) -> str:
    """Decode Modified UTF-8 (the DEX string encoding).

    MUTF-8 differs from UTF-8 in three ways: NUL is encoded as C0 80, BMP
    characters use the 1–3 byte forms only, and code points above the BMP are
    stored as *two* 3-byte surrogate halves (CESU-8).  The surrogate halves are
    recombined here so the result is valid UTF-8 / valid Python str.
    """
    try:
        strv = raw.decode("utf-8")
        if not any(0xD800 <= ord(ch) <= 0xDFFF for ch in strv):
            return strv
    except UnicodeDecodeError:
        pass
    units: list[int] = []
    i, n = 0, len(raw)
    while i < n:
        b = raw[i]
        if b < 0x80:
            units.append(b)
            i += 1
        elif b & 0xE0 == 0xC0 and i + 1 < n:
            units.append(((b & 0x1F) << 6) | (raw[i + 1] & 0x3F))
            i += 2
        elif b & 0xF0 == 0xE0 and i + 2 < n:
            units.append(((b & 0x0F) << 12) | ((raw[i + 1] & 0x3F) << 6) | (raw[i + 2] & 0x3F))
            i += 3
        else:
            units.append(0xFFFD)
            i += 1
    # recombine CESU-8 surrogate pairs; lone halves become U+FFFD
    out: list[str] = []
    j = 0
    while j < len(units):
        u = units[j]
        if 0xD800 <= u <= 0xDBFF and j + 1 < len(units) and 0xDC00 <= units[j + 1] <= 0xDFFF:
            out.append(chr(0x10000 + ((u - 0xD800) << 10) + (units[j + 1] - 0xDC00)))
            j += 2
        elif 0xD800 <= u <= 0xDFFF:
            out.append("\ufffd")
            j += 1
        else:
            out.append(chr(u))
            j += 1
    return "".join(out)


@dataclass(slots=True)
class ClassRef:
    name: str            # dotted, e.g. vn.com.vng.zalopay.MainActivity
    descriptor: str      # raw, e.g. Lvn/com/vng/zalopay/MainActivity;
    access: int
    super_name: str | None
    interfaces: list[str] = field(default_factory=list)
    source_file: str | None = None
    method_count: int = 0
    native_methods: list[str] = field(default_factory=list)
    field_count: int = 0

    def flags(self) -> list[str]:
        f = []
        for bit, label in (
            (ACC_PUBLIC, "public"), (ACC_FINAL, "final"), (ACC_INTERFACE, "interface"),
            (ACC_ABSTRACT, "abstract"), (ACC_ANNOTATION, "annotation"), (ACC_ENUM, "enum"),
            (ACC_SYNTHETIC, "synthetic"),
        ):
            if self.access & bit:
                f.append(label)
        return f


class DexFile:
    """Streaming reader over one DEX image."""

    __slots__ = (
        "path", "name", "data", "buf", "size", "sha256", "checksum",
        "string_ids_size", "string_ids_off", "type_ids_size", "type_ids_off",
        "proto_ids_size", "proto_ids_off", "field_ids_size", "field_ids_off",
        "method_ids_size", "method_ids_off", "class_defs_size", "class_defs_off",
        "_strings", "_types",
    )

    def __init__(self, path: Path, name: str | None = None):
        self.path = path
        self.name = name or path.name
        self.data = path.read_bytes()
        self.buf = memoryview(self.data)
        self.size = len(self.data)
        magic = self.data[:8]
        if magic not in DEX_MAGICS:
            raise ValueError(f"{self.name}: not a DEX image (magic={magic!r})")
        hdr = struct.unpack_from("<I I 20s I I I", self.data, 8)  # noqa: E741
        self.checksum = hdr[0]
        self.sha256 = hashlib.sha256(self.data[32:]).hexdigest()  # signature field
        off = 0x38
        (self.string_ids_size, self.string_ids_off,
         self.type_ids_size, self.type_ids_off,
         self.proto_ids_size, self.proto_ids_off,
         self.field_ids_size, self.field_ids_off,
         self.method_ids_size, self.method_ids_off,
         self.class_defs_size, self.class_defs_off) = struct.unpack_from("<12I", self.data, off)
        self._strings: list[str] | None = None
        self._types: list[str] | None = None

    # -- string pool -------------------------------------------------------
    def _load_strings(self) -> list[str]:
        if self._strings is not None:
            return self._strings
        out = []
        b = self.buf
        for i in range(self.string_ids_size):
            (s_off,) = struct.unpack_from("<I", b, self.string_ids_off + 4 * i)
            _utf16_len, p = _uleb128(b, s_off)
            end = p
            while b[end]:
                end += 1
            out.append(_mutf8(bytes(b[p:end])))
        self._strings = out
        return out

    @property
    def strings(self) -> list[str]:
        return self._load_strings()

    # -- type pool ---------------------------------------------------------
    @property
    def types(self) -> list[str]:
        if self._types is None:
            strings = self.strings
            self._types = [
                strings[struct.unpack_from("<I", self.buf, self.type_ids_off + 4 * i)[0]]
                for i in range(self.type_ids_size)
            ]
        return self._types

    @property
    def protos(self) -> list[tuple[str, str, list[str]]]:
        """(shorty, return_type, [param_types]) per proto_id."""
        strings, types = self.strings, self.types
        out = []
        for i in range(self.proto_ids_size):
            shorty_idx, ret_idx, params_off = struct.unpack_from("<III", self.buf, self.proto_ids_off + 12 * i)
            params: list[str] = []
            if params_off:
                (n,) = struct.unpack_from("<I", self.buf, params_off)
                params = [types[struct.unpack_from("<H", self.buf, params_off + 4 + 2 * k)[0]] for k in range(n)]
            out.append((strings[shorty_idx], types[ret_idx], params))
        return out

    def fields(self) -> Iterator[tuple[str, str, str]]:
        """(class, name, type)"""
        strings, types = self.strings, self.types
        for i in range(self.field_ids_size):
            cls, typ, nm = struct.unpack_from("<HHI", self.buf, self.field_ids_off + 8 * i)
            yield types[cls], strings[nm], types[typ]

    def methods(self) -> Iterator[tuple[str, str, str]]:
        """(class, name, proto-shorty)"""
        strings, types = self.strings, self.types
        for i in range(self.method_ids_size):
            cls, proto, nm = struct.unpack_from("<HHI", self.buf, self.method_ids_off + 8 * i)
            shorty_idx = struct.unpack_from("<I", self.buf, self.proto_ids_off + 12 * proto)[0]
            yield types[cls], strings[nm], strings[shorty_idx]

    # -- class definitions -------------------------------------------------
    def classes(self) -> Iterator[ClassRef]:
        strings, types = self.strings, self.types
        b = self.buf
        for i in range(self.class_defs_size):
            base = self.class_defs_off + 32 * i
            class_idx, access, super_idx, iface_off, src_idx, _ann, class_data_off, _sv = struct.unpack_from(
                "<IIIIIIII", b, base
            )
            desc = types[class_idx]
            sup = types[super_idx] if super_idx != 0xFFFFFFFF else None
            interfaces = []
            if iface_off:
                (n,) = struct.unpack_from("<I", b, iface_off)
                interfaces = [types[struct.unpack_from("<H", b, iface_off + 4 + 2 * k)[0]] for k in range(n)]
            ref = ClassRef(
                name=desc[1:-1].replace("/", ".") if desc.startswith("L") else desc,
                descriptor=desc,
                access=access,
                super_name=(sup[1:-1].replace("/", ".") if sup and sup.startswith("L") else sup),
                interfaces=[x[1:-1].replace("/", ".") for x in interfaces if x.startswith("L")],
                source_file=strings[src_idx] if src_idx != 0xFFFFFFFF else None,
            )
            if class_data_off:
                p = class_data_off
                sf, p = _uleb128(b, p)
                inf, p = _uleb128(b, p)
                dm, p = _uleb128(b, p)
                vm, p = _uleb128(b, p)
                ref.field_count = sf + inf
                ref.method_count = dm + vm
                # skip static + instance fields
                for _ in range(sf + inf):
                    _, p = _uleb128(b, p)
                    _, p = _uleb128(b, p)
                for count, direct in ((dm, True), (vm, False)):
                    idx = 0
                    for _ in range(count):
                        diff, p = _uleb128(b, p)
                        idx += diff
                        flags, p = _uleb128(b, p)
                        code_off, p = _uleb128(b, p)
                        if flags & ACC_NATIVE:
                            # resolve the method name lazily only for natives (rare)
                            cls_i, proto_i, name_i = struct.unpack_from("<HHI", b, self.method_ids_off + 8 * idx)
                            ref.native_methods.append(strings[name_i])
            yield ref

    # -- header summary ----------------------------------------------------
    def summary(self) -> dict:
        return {
            "file": self.name,
            "bytes": self.size,
            "checksum": f"{self.checksum:08x}",
            "signature_sha256": self.sha256,
            "strings": self.string_ids_size,
            "types": self.type_ids_size,
            "protos": self.proto_ids_size,
            "fields": self.field_ids_size,
            "methods": self.method_ids_size,
            "classes": self.class_defs_size,
        }
