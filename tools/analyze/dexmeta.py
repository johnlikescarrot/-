"""Minimal, dependency-free DEX metadata parser.

Parses only what the forensic inventory needs: header fields, string/type/proto/
field/method tables and class_defs. Deliberately avoids loading bytecode so that
76 MB of multidex can be audited in seconds on a 2-vCPU sandbox.

Format reference: https://source.android.com/docs/core/runtime/dex-format
"""
from __future__ import annotations
import struct
from dataclasses import dataclass, field

NO_INDEX = 0xFFFFFFFF


@dataclass
class DexHeader:
    version: str
    checksum: int
    signature_hex: str
    file_size: int
    header_size: int
    string_ids_size: int
    type_ids_size: int
    proto_ids_size: int
    field_ids_size: int
    method_ids_size: int
    class_defs_size: int
    data_size: int
    map_off: int


@dataclass
class DexClass:
    name: str
    superclass: str | None
    interfaces: list[str]
    access_flags: int
    source_file: str | None
    method_count: int
    field_count: int
    kind: str  # "class" | "interface" | "enum" | "annotation"


def _uleb128(buf: bytes, off: int) -> tuple[int, int]:
    result = shift = 0
    while True:
        b = buf[off]
        off += 1
        result |= (b & 0x7F) << shift
        if not b & 0x80:
            return result, off
        shift += 7


class DexFile:
    def __init__(self, data: bytes):
        if data[:4] != b"dex\n":
            raise ValueError("not a dex file")
        self.data = data
        (self.header,) = struct.unpack_from("<8sI20s20sIII", data, 0), 
        # unpack individually for clarity
        self.header = DexHeader(
            version=data[4:8].decode("ascii", "replace").rstrip("\x00"),
            checksum=struct.unpack_from("<I", data, 8)[0],
            signature_hex=data[12:32].hex(),
            file_size=struct.unpack_from("<I", data, 32)[0],
            header_size=struct.unpack_from("<I", data, 36)[0],
            string_ids_size=struct.unpack_from("<I", data, 56)[0],
            type_ids_size=struct.unpack_from("<I", data, 64)[0],
            proto_ids_size=struct.unpack_from("<I", data, 72)[0],
            field_ids_size=struct.unpack_from("<I", data, 80)[0],
            method_ids_size=struct.unpack_from("<I", data, 88)[0],
            class_defs_size=struct.unpack_from("<I", data, 96)[0],
            data_size=struct.unpack_from("<I", data, 104)[0],
            map_off=struct.unpack_from("<I", data, 52)[0],
        )
        self._method_ids_off = struct.unpack_from("<I", data, 92)[0]
        self._string_ids_off = struct.unpack_from("<I", data, 60)[0]
        self._type_ids_off = struct.unpack_from("<I", data, 68)[0]
        self._class_defs_off = struct.unpack_from("<I", data, 100)[0]
        self._strings: list[str] | None = None

    # -- tables ---------------------------------------------------------
    def string(self, idx: int) -> str:
        if self._strings is None:
            self._load_strings()
        return self._strings[idx]  # type: ignore[index]

    def _load_strings(self) -> None:
        data, off = self.data, self._string_ids_off
        out = []
        for i in range(self.header.string_ids_size):
            (data_off,) = struct.unpack_from("<I", data, off + i * 4)
            n, p = _uleb128(data, data_off)  # utf16 length, then MUTF-8
            end = data.index(b"\x00", p)
            out.append(data[p:end].decode("utf-8", "replace"))
        self._strings = out

    def type_name(self, idx: int) -> str:
        (string_idx,) = struct.unpack_from("<I", self.data, self._type_ids_off + idx * 4)
        return self.string(string_idx)

    def type_list(self, off: int) -> list[str]:
        if not off:
            return []
        (size,) = struct.unpack_from("<I", self.data, off)
        return [self.type_name(struct.unpack_from("<H", self.data, off + 4 + i * 2)[0])
                for i in range(size)]

    def method_name(self, idx: int) -> str:
        (class_idx, proto_idx, name_idx) = struct.unpack_from("<HHI", self.data, self._method_ids_off + idx * 8)
        return f"{self.type_name(class_idx)}->{self.string(name_idx)}"

    def classes(self) -> list[DexClass]:
        data, off = self.data, self._class_defs_off
        out = []
        for i in range(self.header.class_defs_size):
            (class_idx, access, super_idx, ifaces_off, source_idx,
             _annotations, class_data_off, _static_values) = struct.unpack_from("<8I", data, off + i * 32)
            name = self.type_name(class_idx)
            superclass = self.type_name(super_idx) if super_idx != NO_INDEX else None
            source = self.string(source_idx) if source_idx != NO_INDEX else None
            methods = fields = 0
            if class_data_off:
                p = class_data_off
                sf, p = _uleb128(data, p)
                inf, p = _uleb128(data, p)
                dm, p = _uleb128(data, p)
                vm, p = _uleb128(data, p)
                fields, methods = sf + inf, dm + vm
            flags = access
            if flags & 0x2000:
                kind = "annotation"
            elif flags & 0x0400:
                kind = "enum"
            elif flags & 0x0200:
                kind = "interface"
            else:
                kind = "class"
            out.append(DexClass(name=name, superclass=superclass,
                                interfaces=self.type_list(ifaces_off),
                                access_flags=flags, source_file=source,
                                method_count=methods, field_count=fields, kind=kind))
        return out


def parse(path: str) -> DexFile:
    with open(path, "rb") as fh:
        return DexFile(fh.read())
