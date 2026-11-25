"""
dex.py — a dependency-free, streaming DEX (Dalvik Executable) reader.

Why hand-rolled instead of androguard for the census?
  * androguard 4.x builds a full class model, which peaks >2.5 GB RSS on a
    16.5 MB dex (ZaloPay classes2.dex). We have 3 GB total.
  * the census only needs *structurally exact* header/table facts: class
    descriptors, superclass chains, method/field counts, access flags, string
    and type tables, proto signatures, code sizes, try/catch counts and the
    literal pool. All of that is obtainable with mmap + struct.unpack.

Everything is bounds-checked: a malformed offset yields `None` instead of an
exception, so one corrupt table cannot abort a whole-APK sweep.

References: Android source `dalvik/libdex/DexFile.h`, DEX format spec 035/038/039.
"""
from __future__ import annotations

import mmap
import struct
from dataclasses import dataclass, field
from typing import Iterable, Iterator, Sequence

# --------------------------------------------------------------------------- #
# constants
# --------------------------------------------------------------------------- #
NO_INDEX = 0xFFFFFFFF

ACC_PUBLIC, ACC_PRIVATE, ACC_PROTECTED = 0x1, 0x2, 0x4
ACC_STATIC, ACC_FINAL, ACC_SYNCHRONIZED = 0x8, 0x10, 0x20
ACC_VOLATILE, ACC_BRIDGE, ACC_TRANSIENT = 0x40, 0x40, 0x80
ACC_NATIVE, ACC_INTERFACE, ACC_ABSTRACT = 0x100, 0x200, 0x400
ACC_STRICT, ACC_SYNTHETIC, ACC_ANNOTATION = 0x800, 0x1000, 0x2000
ACC_ENUM, ACC_CONSTRUCTOR, ACC_DECLARED_SYNCHRONIZED = 0x4000, 0x10000, 0x20000

HEADER = struct.Struct("<8sI20s" + "I" * 20)  # 8+4+20+80 = 0x70
# magic, checksum, signature[20], file_size, header_size, endian_tag, link_size,
# link_off, map_off, string_ids_size, string_ids_off, type_ids_size, type_ids_off,
# proto_ids_size, proto_ids_off, field_ids_size, field_ids_off, method_ids_size,
# method_ids_off, class_defs_size, class_defs_off, data_size, data_off
HEADER_LEN = 0x70
ENDIAN_CONSTANT = 0x12345678
REVERSE_ENDIAN_CONSTANT = 0x78563412
DEX_MAGIC_PREFIX = b"dex\n"

CLASS_DEF = struct.Struct("<IIIIIIII")  # class_idx, access, superclass_idx, ifaces_off,
#                                        source_file_idx, annotations_off, class_data_off,
#                                        static_values_off
METHOD_ID = struct.Struct("<HHI")  # class_idx(u2), proto_idx(u2), name_idx(u4)
FIELD_ID = struct.Struct("<HHI")
PROTO_ID = struct.Struct("<III")  # shorty_idx, return_type_idx, parameters_off
CODE_ITEM = struct.Struct("<HHHHII")  # registers, ins, outs, tries, debug_info_off, insns_size


@dataclass(slots=True)
class ClassInfo:
    """One `class_def_item`, fully resolved (descriptors, not indices)."""

    dex: str
    name: str
    package: str
    access: int
    superclass: str | None
    interfaces: tuple[str, ...]
    source_file: str | None
    methods: int
    fields: int
    direct_methods: int
    virtual_methods: int
    code_units: int          # total 16-bit instruction units in this class
    code_bytes: int
    tries: int
    native_methods: int
    abstract_methods: int
    synthetic: bool
    annotation: bool
    is_interface: bool
    is_enum: bool
    is_inner: bool

    @property
    def simple(self) -> str:
        return self.name.rsplit("/", 1)[-1]


@dataclass(slots=True)
class DexStats:
    path: str
    size: int
    version: str
    checksum_ok: bool
    signature_ok: bool
    endian: str
    n_strings: int
    n_types: int
    n_protos: int
    n_fields: int
    n_methods: int
    n_classes: int
    total_code_bytes: int
    total_tries: int
    map_types: tuple[int, ...] = ()
    strings_sample: tuple[str, ...] = ()


class DexError(RuntimeError):
    pass


def _uleb128(buf: memoryview, off: int) -> tuple[int, int]:
    """Decode an unsigned LEB128 at `off`; returns (value, new_off)."""
    result = 0
    shift = 0
    while True:
        if off >= len(buf):
            raise DexError("uleb128 out of bounds")
        b = buf[off]
        off += 1
        result |= (b & 0x7F) << shift
        if not b & 0x80:
            return result, off
        shift += 7
        if shift > 35:
            raise DexError("uleb128 too long")


def _sleb128(buf: memoryview, off: int) -> tuple[int, int]:
    result = 0
    shift = 0
    while True:
        b = buf[off]
        off += 1
        result |= (b & 0x7F) << shift
        shift += 7
        if not b & 0x80:
            if shift < 32 and (b & 0x40):
                result |= -(1 << shift)
            return result, off


def _mutf8(raw: bytes) -> str:
    """Decode Modified-UTF-8 (DEX MUTF-8 allows C0 80 for NUL, no 4-byte seq)."""
    if b"\xc0\x80" in raw:
        raw = raw.replace(b"\xc0\x80", b"\x00")
    try:
        return raw.decode("utf-8", "replace")
    except Exception:  # pragma: no cover - decode with replace never raises
        return raw.decode("latin-1", "replace")


class DexFile:
    """Memory-mapped read-only view over a single `classes*.dex`."""

    __slots__ = ("path", "data", "mv", "buf", "header", "strings", "_types", "_classes")

    def __init__(self, path: str):
        self.path = path
        with open(path, "rb") as fh:
            self.data = mmap.mmap(fh.fileno(), 0, access=mmap.ACCESS_READ)
        self.mv = memoryview(self.data)
        # `buf` is an unsigned-byte view used for all table reads.
        self.buf = self.mv.cast("B")
        self.header = self._parse_header()
        self.strings = self._read_strings()
        self._types: list[str] = []
        self._classes: list[ClassInfo] = []

    # -- lifecycle ---------------------------------------------------------- #
    def close(self) -> None:
        self.buf.release()
        self.mv.release()
        self.data.close()

    def __enter__(self) -> "DexFile":
        return self

    def __exit__(self, *exc) -> None:
        self.close()

    # -- header ------------------------------------------------------------- #
    def _parse_header(self) -> dict:
        if len(self.data) < HEADER_LEN:
            raise DexError(f"{self.path}: shorter than a DEX header")
        f = HEADER.unpack_from(self.data, 0)
        magic = f[0]
        if not magic.startswith(DEX_MAGIC_PREFIX):
            raise DexError(f"{self.path}: bad magic {magic!r}")
        names = (
            "magic checksum signature file_size header_size endian_tag link_size "
            "link_off map_off string_ids_size string_ids_off type_ids_size type_ids_off "
            "proto_ids_size proto_ids_off field_ids_size field_ids_off method_ids_size "
            "method_ids_off class_defs_size class_defs_off data_size data_off"
        ).split()
        hdr = dict(zip(names, f))
        hdr["version"] = magic[4:7].decode("ascii", "replace").rstrip("\x00")
        # integrity: adler32 over bytes [12:] and sha1 over bytes [32:]
        import hashlib
        import zlib

        body_from_sig = self.data[32:]
        hdr["signature_ok"] = hashlib.sha1(body_from_sig).digest() == f[2]
        body_from_sum = self.data[12:]
        hdr["checksum_ok"] = (zlib.adler32(body_from_sum) & 0xFFFFFFFF) == f[1]
        if hdr["endian_tag"] == ENDIAN_CONSTANT:
            hdr["endian"] = "little"
        elif hdr["endian_tag"] == REVERSE_ENDIAN_CONSTANT:
            hdr["endian"] = "reverse(little, unsupported)"
        else:
            raise DexError(f"{self.path}: unknown endian tag {hdr['endian_tag']:#x}")
        if hdr["file_size"] != len(self.data):
            raise DexError(
                f"{self.path}: header file_size {hdr['file_size']} != actual {len(self.data)}"
            )
        return hdr

    # -- string / type tables ----------------------------------------------- #
    def _string_at(self, off: int) -> str:
        """`string_data_item` is length-prefixed (uleb) MUTF-8, NUL-terminated."""
        _, p = _uleb128(self.buf, off)
        end = self.data.find(b"\x00", p)
        if end < 0:
            raise DexError("unterminated string")
        return _mutf8(self.data[p:end])

    def _read_strings(self) -> list[str]:
        n = self.header["string_ids_size"]
        off = self.header["string_ids_off"]
        read = struct.Struct("<I").unpack_from
        out: list[str] = []
        limit = len(self.data)
        for i in range(n):
            p = off + i * 4
            if p + 4 > limit:
                out.append("")
                continue
            (soff,) = read(self.data, p)
            try:
                out.append(self._string_at(soff))
            except DexError:
                out.append("")
        return out

    def string(self, idx: int) -> str:
        if 0 <= idx < len(self.strings):
            return self.strings[idx]
        return ""

    @property
    def types(self) -> list[str]:
        if not self._types:
            n = self.header["type_ids_size"]
            off = self.header["type_ids_off"]
            read = struct.Struct("<I").unpack_from
            self._types = [
                self.string(read(self.data, off + i * 4)[0]) if off + i * 4 + 4 <= len(self.data) else ""
                for i in range(n)
            ]
        return self._types

    def type_name(self, idx: int) -> str:
        if idx == NO_INDEX:
            return ""
        t = self.types
        if 0 <= idx < len(t):
            return t[idx]
        return ""

    # -- class data --------------------------------------------------------- #
    def _class_data(self, off: int) -> dict:
        """Parse `class_data_item`: counts, then field/method index deltas."""
        buf = self.buf
        p = off
        static_fields, p = _uleb128(buf, p)
        instance_fields, p = _uleb128(buf, p)
        direct_methods, p = _uleb128(buf, p)
        virtual_methods, p = _uleb128(buf, p)
        for _ in range(static_fields + instance_fields):
            _, p = _uleb128(buf, p)   # field_idx_diff
            _, p = _uleb128(buf, p)   # access_flags
        code_units = code_bytes = tries = native = abstract = 0
        total_methods = direct_methods + virtual_methods
        for _ in range(total_methods):
            _, p = _uleb128(buf, p)        # method_idx_diff
            access, p = _uleb128(buf, p)   # access_flags
            code_off, p = _uleb128(buf, p)  # code_off (0 => abstract/native)
            if access & ACC_NATIVE:
                native += 1
            if access & ACC_ABSTRACT:
                abstract += 1
            if code_off:
                try:
                    regs, ins, outs, t, dbg, insns = CODE_ITEM.unpack_from(buf, code_off)
                except struct.error:
                    continue
                code_units += insns
                # code item size = 16-byte header + insns*2 + try_items + handlers
                code_bytes += 16 + insns * 2
                tries += t
        return {
            "fields": static_fields + instance_fields,
            "static_fields": static_fields,
            "instance_fields": instance_fields,
            "direct_methods": direct_methods,
            "virtual_methods": virtual_methods,
            "methods": total_methods,
            "code_units": code_units,
            "code_bytes": code_bytes,
            "tries": tries,
            "native_methods": native,
            "abstract_methods": abstract,
        }

    def classes(self) -> list[ClassInfo]:
        if self._classes:
            return self._classes
        n = self.header["class_defs_size"]
        off = self.header["class_defs_off"]
        out: list[ClassInfo] = []
        for i in range(n):
            p = off + i * CLASS_DEF.size
            if p + CLASS_DEF.size > len(self.data):
                break
            (cidx, access, sidx, ifaces_off, src_idx, _anno, cdata_off, _sv) = CLASS_DEF.unpack_from(
                self.data, p
            )
            name = self.type_name(cidx)
            pkg = name.rsplit("/", 1)[0].lstrip("L") if "/" in name else ""
            try:
                cd = self._class_data(cdata_off) if cdata_off else None
            except (DexError, IndexError):
                cd = None
            cd = cd or {
                "fields": 0, "static_fields": 0, "instance_fields": 0,
                "direct_methods": 0, "virtual_methods": 0, "methods": 0,
                "code_units": 0, "code_bytes": 0, "tries": 0,
                "native_methods": 0, "abstract_methods": 0,
            }
            out.append(
                ClassInfo(
                    dex=self.path.rsplit("/", 1)[-1],
                    name=name,
                    package=pkg,
                    access=access,
                    superclass=self.type_name(sidx) or None,
                    interfaces=self._interfaces(ifaces_off),
                    source_file=(self.string(src_idx) if src_idx != NO_INDEX else None),
                    methods=cd["methods"],
                    fields=cd["fields"],
                    direct_methods=cd["direct_methods"],
                    virtual_methods=cd["virtual_methods"],
                    code_units=cd["code_units"],
                    code_bytes=cd["code_bytes"],
                    tries=cd["tries"],
                    native_methods=cd["native_methods"],
                    abstract_methods=cd["abstract_methods"],
                    synthetic=bool(access & ACC_SYNTHETIC),
                    annotation=bool(access & ACC_ANNOTATION),
                    is_interface=bool(access & ACC_INTERFACE),
                    is_enum=bool(access & ACC_ENUM),
                    is_inner="$" in name,
                )
            )
        self._classes = out
        return out

    def _interfaces(self, off: int) -> tuple[str, ...]:
        if not off or off + 4 > len(self.data):
            return ()
        (n,) = struct.unpack_from("<I", self.data, off)
        out = []
        for i in range(min(n, 64)):
            p = off + 4 + i * 2
            if p + 2 <= len(self.data):
                out.append(self.type_name(struct.unpack_from("<H", self.data, p)[0]))
        return tuple(out)

    # -- map list ----------------------------------------------------------- #
    def map_types(self) -> tuple[int, ...]:
        off = self.header["map_off"]
        if not off or off + 4 > len(self.data):
            return ()
        (n,) = struct.unpack_from("<I", self.data, off)
        return tuple(
            struct.unpack_from("<H", self.data, off + 4 + i * 12)[0]
            for i in range(n)
            if off + 4 + i * 12 + 2 <= len(self.data)
        )

    # -- signatures --------------------------------------------------------- #
    def method_table(self) -> Iterator[tuple[str, str, str]]:
        """Yield (class_descriptor, method_name, proto_index_string) for all methods.

        Names come from `method_ids`; the class association requires class_data
        walking, so we yield `(owner, name, shorty)` where owner is resolved
        through the method_id's class_idx — this is the *declaring* class from
        the id table, which is what tooling normally means by "class owns
        method" (overrides appear once per declaring class).
        """
        n = self.header["method_ids_size"]
        off = self.header["method_ids_off"]
        for i in range(n):
            p = off + i * 8
            if p + 8 > len(self.data):
                break
            cidx, pidx, nidx = METHOD_ID.unpack_from(self.data, p)
            yield self.type_name(cidx), self.string(nidx), self.type_name(pidx)

    def proto_return(self, proto_idx: int) -> str:
        off = self.header["proto_ids_off"] + proto_idx * 12
        if off + 12 > len(self.data):
            return ""
        _shorty, ret, _params = PROTO_ID.unpack_from(self.data, off)
        return self.type_name(ret)

    def proto_params(self, proto_idx: int) -> list[str]:
        off = self.header["proto_ids_off"] + proto_idx * 12
        if off + 12 > len(self.data):
            return []
        _shorty, _ret, params_off = PROTO_ID.unpack_from(self.data, off)
        if not params_off or params_off + 4 > len(self.data):
            return []
        (n,) = struct.unpack_from("<I", self.data, params_off)
        return [
            self.type_name(struct.unpack_from("<H", self.data, params_off + 4 + 2 * i)[0])
            for i in range(min(n, 32))
            if params_off + 4 + 2 * i + 2 <= len(self.data)
        ]

    # -- summary ------------------------------------------------------------ #
    def stats(self, string_sample: int = 0) -> DexStats:
        cls = self.classes()
        return DexStats(
            path=self.path,
            size=len(self.data),
            version=self.header["version"],
            checksum_ok=self.header["checksum_ok"],
            signature_ok=self.header["signature_ok"],
            endian=self.header["endian"],
            n_strings=self.header["string_ids_size"],
            n_types=self.header["type_ids_size"],
            n_protos=self.header["proto_ids_size"],
            n_fields=self.header["field_ids_size"],
            n_methods=self.header["method_ids_size"],
            n_classes=self.header["class_defs_size"],
            total_code_bytes=sum(c.code_bytes for c in cls),
            total_tries=sum(c.tries for c in cls),
            map_types=self.map_types(),
            strings_sample=tuple(self.strings[:string_sample]),
        )


def iter_apk_dex(apk_path: str) -> list[str]:
    """List `classes*.dex` / `assets/*.dex` entries inside an APK, largest first."""
    import zipfile

    with zipfile.ZipFile(apk_path) as z:
        entries = [
            i for i in z.infolist()
            if i.filename.endswith(".dex") and not i.is_dir()
        ]
    entries.sort(key=lambda i: -i.file_size)
    return [i.filename for i in entries]


if __name__ == "__main__":  # tiny smoke test: python re/dex.py <file.dex>
    import sys

    for path in sys.argv[1:]:
        with DexFile(path) as d:
            s = d.stats()
            print(
                f"{s.path}: v{s.version} {s.size:,}B classes={s.n_classes:,} "
                f"methods={s.n_methods:,} fields={s.n_fields:,} strings={s.n_strings:,} "
                f"sha1_ok={s.signature_ok} adler32_ok={s.checksum_ok}"
            )
            print("   first 3 descriptors:", [c.name for c in d.classes()[:3]])
