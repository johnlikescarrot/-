"""A minimal, dependency-free reader for the Dalvik Executable (DEX) container.

Reference: https://source.android.com/docs/core/runtime/dex-format

Only the *index* structures are parsed — string_ids, type_ids, method_ids and
class_defs. That is enough to recover the full symbol surface of a binary
(every class descriptor, method name and string literal in a DEX lives in the
string table) while staying roughly three orders of magnitude faster than a
full disassembler, which matters when a single sample ships 20 DEX files
totalling ~70 MB.
"""

from __future__ import annotations

import struct
from dataclasses import dataclass, field
from typing import Dict, Iterator, List, Optional, Tuple

#: Valid DEX magic numbers, oldest first.
MAGICS = (b"dex\n035\x00", b"dex\n036\x00", b"dex\n037\x00", b"dex\n038\x00", b"dex\n039\x00")

# class_def_item field offsets (item is 32 bytes).
_CD_CLASS_IDX = 0
_CD_ACCESS = 4
_CD_SUPERCLASS = 8

# Access flags (dex_format "access_flags definitions").
ACC_PUBLIC = 0x0001
ACC_PRIVATE = 0x0002
ACC_PROTECTED = 0x0004
ACC_STATIC = 0x0008
ACC_FINAL = 0x0010
ACC_INTERFACE = 0x0200
ACC_ABSTRACT = 0x0400
ACC_SYNTHETIC = 0x1000
ACC_ANNOTATION = 0x2000
ACC_ENUM = 0x4000


def uleb128(data: bytes, off: int) -> Tuple[int, int]:
    """Decode an unsigned LEB128 value; return (value, offset_after)."""
    result = 0
    shift = 0
    while True:
        b = data[off]
        off += 1
        result |= (b & 0x7F) << shift
        if not (b & 0x80):
            return result, off
        shift += 7


@dataclass
class DexStats:
    """Container-level statistics for one DEX file."""

    name: str
    version: str
    file_size: int
    string_ids: int
    type_ids: int
    proto_ids: int
    field_ids: int
    method_ids: int
    class_defs: int
    strings_decoded: int = 0
    string_bytes: int = 0
    extra: Dict[str, object] = field(default_factory=dict)

    def as_dict(self) -> Dict[str, object]:
        return dict(self.__dict__)


class DexFile:
    """Read-only view over the index tables of one DEX file."""

    def __init__(self, data: bytes, name: str = "<memory>") -> None:
        if not any(data.startswith(m) for m in MAGICS):
            raise ValueError(f"{name}: not a DEX file (magic={data[:8]!r})")
        self.data = data
        self.name = name
        self.version = data[4:7].decode("ascii", "replace")
        u = struct.unpack_from
        self.file_size = u("<I", data, 0x20)[0]
        self.map_off = u("<I", data, 0x34)[0]
        self.string_ids_size, self.string_ids_off = u("<II", data, 0x38)
        self.type_ids_size, self.type_ids_off = u("<II", data, 0x40)
        self.proto_ids_size, self.proto_ids_off = u("<II", data, 0x48)
        self.field_ids_size, self.field_ids_off = u("<II", data, 0x50)
        self.method_ids_size, self.method_ids_off = u("<II", data, 0x58)
        self.class_defs_size, self.class_defs_off = u("<II", data, 0x60)
        self._cache: Dict[int, str] = {}

    # ------------------------------------------------------------------ strings
    def _string_off(self, idx: int) -> int:
        return struct.unpack_from("<I", self.data, self.string_ids_off + idx * 4)[0]

    def string(self, idx: int) -> str:
        """Decode string #idx (MUTF-8), memoised."""
        hit = self._cache.get(idx)
        if hit is not None:
            return hit
        _, p = uleb128(self.data, self._string_off(idx))
        end = self.data.index(b"\x00", p)
        val = self.data[p:end].decode("utf-8", "replace")
        self._cache[idx] = val
        return val

    def iter_strings(self) -> Iterator[str]:
        """Stream every string in the table without retaining them all."""
        data = self.data
        for i in range(self.string_ids_size):
            _, p = uleb128(data, self._string_off(i))
            end = data.index(b"\x00", p)
            yield data[p:end].decode("utf-8", "replace")

    # -------------------------------------------------------------------- types
    def iter_types(self) -> Iterator[str]:
        d = self.data
        base = self.type_ids_off
        for i in range(self.type_ids_size):
            yield self.string(struct.unpack_from("<I", d, base + i * 4)[0])

    # ------------------------------------------------------------------ methods
    def iter_methods(self) -> Iterator[Tuple[str, str]]:
        """Yield (declaring_class_descriptor, method_name) for every method id."""
        d = self.data
        base = self.method_ids_off
        for i in range(self.method_ids_size):
            class_idx, _proto, name_idx = struct.unpack_from("<HHI", d, base + i * 8)
            type_idx = struct.unpack_from("<I", d, self.type_ids_off + class_idx * 4)[0]
            yield self.string(type_idx), self.string(name_idx)

    # ------------------------------------------------------------------ classes
    def iter_classes(self) -> Iterator[Tuple[str, int, str]]:
        """Yield (class_descriptor, access_flags, superclass_descriptor)."""
        d = self.data
        base = self.class_defs_off
        for i in range(self.class_defs_size):
            off = base + i * 32
            class_idx, access, super_idx = struct.unpack_from("<III", d, off)
            ct = struct.unpack_from("<I", d, self.type_ids_off + class_idx * 4)[0]
            if super_idx == 0xFFFFFFFF:
                sup = "<none>"
            else:
                st = struct.unpack_from("<I", d, self.type_ids_off + super_idx * 4)[0]
                sup = self.string(st)
            yield self.string(ct), access, sup

    def stats(self) -> DexStats:
        return DexStats(
            name=self.name,
            version=self.version,
            file_size=self.file_size,
            string_ids=self.string_ids_size,
            type_ids=self.type_ids_size,
            proto_ids=self.proto_ids_size,
            field_ids=self.field_ids_size,
            method_ids=self.method_ids_size,
            class_defs=self.class_defs_size,
        )


def descriptor_to_dotted(desc: str) -> str:
    """`Lvn/com/vng/zalopay/Foo;` -> `vn.com.vng.zalopay.Foo`."""
    if desc.startswith("L") and desc.endswith(";"):
        return desc[1:-1].replace("/", ".")
    return desc


def package_prefix(desc: str, depth: int = 2) -> str:
    """First `depth` segments of a dotted class name, e.g. `vn.com`."""
    dotted = descriptor_to_dotted(desc)
    parts = dotted.split(".")
    return ".".join(parts[:depth]) if len(parts) > depth else dotted
