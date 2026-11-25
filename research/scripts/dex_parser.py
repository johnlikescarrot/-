"""Minimal, dependency-free DEX (035/037/038/039) reader.

Implements only what the study needs:
  * header + section tables
  * string / type / proto / field / method / class_def decoding
  * class access flags, superclass, interfaces, source_file
  * method-level access flags + native detection
  * string constant harvesting (URLs, hosts, scheme, crypto markers)

Design notes
------------
DEX is little-endian, all tables are (offset,count) pairs in the header.
`string_ids` hold *offsets* to MUTF-8 (uleb128 length prefixed) payloads.
`type_ids` are indexes into string_ids, `proto_ids` are shorty/return/params.
`class_defs` give (class_idx, access, superclass_idx, interfaces_off,
source_file_idx, annotations_off, class_data_off, static_values_off).
`class_data` is uleb128 encoded, grouped by (static fields, instance fields,
direct methods, virtual methods) — the method entries carry the access flags
we need for JNI (`ACC_NATIVE = 0x100`).
"""
from __future__ import annotations

import struct
from dataclasses import dataclass, field
from pathlib import Path
from typing import Iterable, Iterator

ACC_PUBLIC = 0x1
ACC_PRIVATE = 0x2
ACC_PROTECTED = 0x4
ACC_STATIC = 0x8
ACC_FINAL = 0x10
ACC_SYNCHRONIZED = 0x20
ACC_VOLATILE = 0x40
ACC_BRIDGE = 0x40
ACC_TRANSIENT = 0x80
ACC_VARARGS = 0x80
ACC_NATIVE = 0x100
ACC_INTERFACE = 0x200
ACC_ABSTRACT = 0x400
ACC_STRICT = 0x800
ACC_SYNTHETIC = 0x1000
ACC_ANNOTATION = 0x2000
ACC_ENUM = 0x4000
ACC_CONSTRUCTOR = 0x10000
ACC_DECLARED_SYNCHRONIZED = 0x20000


class Reader:
    __slots__ = ("buf", "pos")

    def __init__(self, buf: bytes, pos: int = 0) -> None:
        self.buf = buf
        self.pos = pos

    def uleb(self) -> int:
        buf, pos = self.buf, self.pos
        result = buf[pos]
        if result > 0x7F:
            result &= 0x7F
            shift = 7
            while True:
                b = buf[pos + shift // 7]
                result |= (b & 0x7F) << shift
                shift += 7
                if b <= 0x7F:
                    break
        self.pos += (result.bit_length() + 6) // 7 or 1
        return result

    def uleb2(self) -> int:
        # explicit uleb128, used by class_data
        result = 0
        shift = 0
        buf, pos = self.buf, self.pos
        while True:
            b = buf[pos + (shift // 7)]
            result |= (b & 0x7F) << shift
            shift += 7
            if b <= 0x7F:
                break
        self.pos += (shift // 7) + 1
        return result


def read_uleb(buf: bytes, pos: int) -> tuple[int, int]:
    result = 0
    shift = 0
    while True:
        b = buf[pos]
        pos += 1
        result |= (b & 0x7F) << shift
        if b <= 0x7F:
            return result, pos
        shift += 7


def read_mutf8(buf: bytes, pos: int) -> str:
    _, pos = read_uleb(buf, pos)
    end = buf.index(b"\x00", pos)
    raw = buf[pos:end]
    # MUTF-8 differs from UTF-8 only for surrogates and NUL; decode leniently.
    return raw.decode("utf-8", errors="replace")


@dataclass(slots=True)
class DexMethod:
    name: str
    proto: str
    access: int
    class_name: str

    @property
    def is_native(self) -> bool:
        return bool(self.access & ACC_NATIVE)


@dataclass(slots=True)
class DexClass:
    name: str
    access: int
    superclass: str | None
    interfaces: list[str]
    source_file: str | None
    dex: str
    methods: list[DexMethod] = field(default_factory=list)
    fields_count: int = 0


class DexFile:
    def __init__(self, path: Path) -> None:
        self.path = Path(path)
        self.buf = self.path.read_bytes()
        b = self.buf
        (
            self.string_ids_size,
            self.string_ids_off,
            self.type_ids_size,
            self.type_ids_off,
            self.proto_ids_size,
            self.proto_ids_off,
            self.field_ids_size,
            self.field_ids_off,
            self.method_ids_size,
            self.method_ids_off,
            self.class_defs_size,
            self.class_defs_off,
            self.data_size,
            self.data_off,
        ) = struct.unpack_from("<14I", b, 56)
        self.version = b[4:7].decode("ascii")
        self.checksum = struct.unpack_from("<I", b, 8)[0]
        self.sha1 = b[12:32].hex()
        self._strings: list[str] | None = None
        self._types: list[str] | None = None

    # ---- primitive tables -------------------------------------------------
    @property
    def strings(self) -> list[str]:
        if self._strings is None:
            b = self.buf
            offs = struct.unpack_from(f"<{self.string_ids_size}I", b, self.string_ids_off)
            self._strings = [read_mutf8(b, off) for off in offs]
        return self._strings

    @property
    def types(self) -> list[str]:
        if self._types is None:
            b = self.buf
            idx = struct.unpack_from(f"<{self.type_ids_size}I", b, self.type_ids_off)
            s = self.strings
            self._types = [s[i] for i in idx]
        return self._types

    def proto(self, idx: int) -> str:
        b = self.buf
        shorty, ret, params_off = struct.unpack_from("<III", b, self.proto_ids_off + idx * 12)
        types = self.types
        params: list[str] = []
        if params_off:
            size = struct.unpack_from("<I", b, params_off)[0]
            ids = struct.unpack_from(f"<{size}H", b, params_off + 4)
            params = [types[i] for i in ids]
        return f"({''.join(params)}){types[ret]}"

    def method_ref(self, idx: int) -> tuple[str, str, str]:
        b = self.buf
        class_idx, proto_idx, name_idx = struct.unpack_from("<HHI", b, self.method_ids_off + idx * 8)
        return self.types[class_idx], self.strings[name_idx], self.proto(proto_idx)

    # ---- classes ----------------------------------------------------------
    def classes(self) -> Iterator[DexClass]:
        b = self.buf
        s = self.strings
        t = self.types
        for i in range(self.class_defs_size):
            (
                class_idx,
                access,
                super_idx,
                interfaces_off,
                source_idx,
                _annotations_off,
                class_data_off,
                _static_values,
            ) = struct.unpack_from("<8I", b, self.class_defs_off + i * 32)
            interfaces: list[str] = []
            if interfaces_off:
                n = struct.unpack_from("<I", b, interfaces_off)[0]
                ids = struct.unpack_from(f"<{n}H", b, interfaces_off + 4)
                interfaces = [t[j] for j in ids]
            dc = DexClass(
                name=t[class_idx],
                access=access,
                superclass=t[super_idx] if super_idx != 0xFFFFFFFF else None,
                interfaces=interfaces,
                source_file=s[source_idx] if source_idx != 0xFFFFFFFF else None,
                dex=self.path.name,
            )
            if class_data_off:
                self._read_class_data(b, class_data_off, dc)
            yield dc

    def _read_class_data(self, b: bytes, off: int, dc: DexClass) -> None:
        pos = off
        n_static, pos = read_uleb(b, pos)
        n_instance, pos = read_uleb(b, pos)
        n_direct, pos = read_uleb(b, pos)
        n_virtual, pos = read_uleb(b, pos)
        dc.fields_count = n_static + n_instance
        for _ in range(n_static + n_instance):
            _, pos = read_uleb(b, pos)  # field_idx diff
            _, pos = read_uleb(b, pos)  # access
        for n_methods in (n_direct, n_virtual):
            idx = 0
            for _ in range(n_methods):
                diff, pos = read_uleb(b, pos)
                idx += diff
                access, pos = read_uleb(b, pos)
                code_off, pos = read_uleb(b, pos)
                owner, name, proto = self.method_ref(idx)
                dc.methods.append(DexMethod(name=name, proto=proto, access=access, class_name=owner))
                if code_off:
                    pass  # bytecode deliberately not decoded (static metadata study)


def package_of(descriptor: str) -> str:
    """Lcom/foo/Bar; -> com/foo"""
    d = descriptor.strip("L;")
    return d.rsplit("/", 1)[0] if "/" in d else ""
