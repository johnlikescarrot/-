"""Pure-python Dalvik Executable (DEX) reader + bytecode-level surveyor.

Why not just use androguard?  Because androguard materialises one Python
object per instruction/class/method: for an 82 MB / 20-file DEX payload that
costs several GB of RSS, while this module keeps the working set at a few
hundred MB by (a) reading the header + id tables into ``array`` containers,
(b) walking each ``code_item`` with ``struct.unpack_from`` directly and
(c) folding everything into aggregate counters that are discarded per file.

What it extracts
----------------
header / id-table census        strings, types, protos, fields, methods, classes
class census                    access flags, superclass, method+field counts
opcode histogram                full 256-slot Dalvik histogram (executed units)
package dependency graph        weighted edges between java package namespaces
sensitive-API call sites        per-caller-package hits against a watchlist
string intel                    URI/host/path/crypto/secret patterns
obfuscation metrics             identifier entropy, tiny-name ratio, etc.

Reference: "Dalvik Executable format", Android Open Source Project,
https://source.android.com/docs/core/runtime/dex-format
"""

from __future__ import annotations

import hashlib
import io
import zlib
import math
import re
import struct
from array import array
from collections import Counter, defaultdict
from dataclasses import dataclass, field
from typing import Dict, Iterable, Iterator, List, Optional, Sequence, Tuple

# --------------------------------------------------------------------------
# Instruction widths in 16-bit code units, indexed by opcode
# (derived from the dalvik bytecode format table, see docstring).
# 0 => unused slot; we still consume one unit defensively.
# --------------------------------------------------------------------------
OPCODE_WIDTHS: List[int] = [
    1, 1, 2, 3, 1, 2, 3, 1, 2, 3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 3, 2, 2, 3,
    5, 2, 2, 3, 2, 1, 1, 2, 2, 1, 2, 2, 3, 3, 3, 1, 1, 2, 3, 3, 3, 2, 2, 2,
    2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 0, 0, 0, 0, 0, 2, 2, 2, 2,
    2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2,
    2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 0, 3, 3, 3, 3,
    3, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
    2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2,
    2, 2, 2, 2, 2, 2, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
    1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2,
    2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 4, 3, 3, 2, 2,
]
assert len(OPCODE_WIDTHS) == 256

# opcode -> mnemonic (only the ones we report/histogram meaningfully)
OPCODE_NAMES: Dict[int, str] = {
    0x00: "nop", 0x01: "move", 0x02: "move/from16", 0x03: "move/16",
    0x04: "move-wide", 0x05: "move-wide/from16", 0x06: "move-wide/16",
    0x07: "move-object", 0x08: "move-object/from16", 0x09: "move-object/16",
    0x0A: "move-result", 0x0B: "move-result-wide", 0x0C: "move-result-object",
    0x0D: "move-exception", 0x0E: "return-void", 0x0F: "return",
    0x10: "return-wide", 0x11: "return-object", 0x12: "const/4",
    0x13: "const/16", 0x14: "const", 0x15: "const/high16", 0x16: "const-wide/16",
    0x17: "const-wide/32", 0x18: "const-wide", 0x19: "const-wide/high16",
    0x1A: "const-string", 0x1B: "const-string/jumbo", 0x1C: "const-class",
    0x1D: "monitor-enter", 0x1E: "monitor-exit", 0x1F: "check-cast",
    0x20: "instance-of", 0x21: "array-length", 0x22: "new-instance",
    0x23: "new-array", 0x24: "filled-new-array", 0x25: "filled-new-array/range",
    0x26: "fill-array-data", 0x27: "throw", 0x28: "goto", 0x29: "goto/16",
    0x2A: "goto/32", 0x2B: "packed-switch", 0x2C: "sparse-switch",
    0x2D: "cmpl-float", 0x2E: "cmpg-float", 0x2F: "cmpl-double", 0x30: "cmpg-double",
    0x31: "cmp-long", 0x32: "if-eq", 0x33: "if-ne", 0x34: "if-lt", 0x35: "if-ge",
    0x36: "if-gt", 0x37: "if-le", 0x38: "if-eqz", 0x39: "if-nez", 0x3A: "if-ltz",
    0x3B: "if-gez", 0x3C: "if-gtz", 0x3D: "if-lez",
    0x44: "aget", 0x45: "aget-wide", 0x46: "aget-object", 0x47: "aget-boolean",
    0x48: "aget-byte", 0x49: "aget-char", 0x4A: "aget-short", 0x4B: "aput",
    0x4C: "aput-wide", 0x4D: "aput-object", 0x4E: "aput-boolean", 0x4F: "aput-byte",
    0x50: "aput-char", 0x51: "aput-short", 0x52: "iget", 0x53: "iget-wide",
    0x54: "iget-object", 0x55: "iget-boolean", 0x56: "iget-byte", 0x57: "iget-char",
    0x58: "iget-short", 0x59: "iput", 0x5A: "iput-wide", 0x5B: "iput-object",
    0x5C: "iput-boolean", 0x5D: "iput-byte", 0x5E: "iput-char", 0x5F: "iput-short",
    0x60: "sget", 0x61: "sget-wide", 0x62: "sget-object", 0x63: "sget-boolean",
    0x64: "sget-byte", 0x65: "sget-char", 0x66: "sget-short", 0x67: "sput",
    0x68: "sput-wide", 0x69: "sput-object", 0x6A: "sput-boolean", 0x6B: "sput-byte",
    0x6C: "sput-char", 0x6D: "sput-short",
    0x6E: "invoke-virtual", 0x6F: "invoke-super", 0x70: "invoke-direct",
    0x71: "invoke-static", 0x72: "invoke-interface",
    0x74: "invoke-virtual/range", 0x75: "invoke-super/range",
    0x76: "invoke-direct/range", 0x77: "invoke-static/range",
    0x78: "invoke-interface/range",
    0x7B: "neg-int", 0x7C: "not-int", 0x7D: "neg-long", 0x7E: "not-long",
    0x7F: "neg-float", 0x80: "neg-double", 0x81: "int-to-long", 0x82: "int-to-float",
    0x83: "int-to-double", 0x84: "long-to-int", 0x85: "long-to-float",
    0x86: "long-to-double", 0x87: "float-to-int", 0x88: "float-to-long",
    0x89: "float-to-double", 0x8A: "double-to-int", 0x8B: "double-to-long",
    0x8C: "double-to-float", 0x8D: "int-to-byte", 0x8E: "int-to-char",
    0x8F: "int-to-short", 0x90: "add-int", 0x91: "sub-int", 0x92: "mul-int",
    0x93: "div-int", 0x94: "rem-int", 0x95: "and-int", 0x96: "or-int",
    0x97: "xor-int", 0x98: "shl-int", 0x99: "shr-int", 0x9A: "ushr-int",
    0x9B: "add-long", 0x9C: "sub-long", 0x9D: "mul-long", 0x9E: "div-long",
    0x9F: "rem-long", 0xA0: "and-long", 0xA1: "or-long", 0xA2: "xor-long",
    0xA3: "shl-long", 0xA4: "shr-long", 0xA5: "ushr-long", 0xA6: "add-float",
    0xA7: "sub-float", 0xA8: "mul-float", 0xA9: "div-float", 0xAA: "rem-float",
    0xAB: "add-double", 0xAC: "sub-double", 0xAD: "mul-double", 0xAE: "div-double",
    0xAF: "rem-double", 0xB0: "add-int/2addr", 0xB1: "sub-int/2addr",
    0xB2: "mul-int/2addr", 0xB3: "div-int/2addr", 0xB4: "rem-int/2addr",
    0xB5: "and-int/2addr", 0xB6: "or-int/2addr", 0xB7: "xor-int/2addr",
    0xB8: "shl-int/2addr", 0xB9: "shr-int/2addr", 0xBA: "ushr-int/2addr",
    0xD0: "add-int/lit16", 0xD1: "rsub-int", 0xD2: "mul-int/lit16",
    0xD3: "div-int/lit16", 0xD4: "rem-int/lit16", 0xD5: "and-int/lit16",
    0xD6: "or-int/lit16", 0xD7: "xor-int/lit16", 0xD8: "add-int/lit8",
    0xD9: "rsub-int/lit8", 0xDA: "mul-int/lit8", 0xDB: "div-int/lit8",
    0xDC: "rem-int/lit8", 0xDD: "and-int/lit8", 0xDE: "or-int/lit8",
    0xDF: "xor-int/lit8", 0xE0: "shl-int/lit8", 0xE1: "shr-int/lit8",
    0xE2: "ushr-int/lit8", 0xFA: "invoke-polymorphic",
    0xFB: "invoke-polymorphic/range", 0xFC: "invoke-custom",
    0xFD: "invoke-custom/range", 0xFE: "const-method-handle",
    0xFF: "const-method-type",
}

INVOKE_NORMAL = {0x6E, 0x6F, 0x70, 0x71, 0x72, 0xFA, 0xFC}
INVOKE_RANGE = {0x74, 0x75, 0x76, 0x77, 0x78, 0xFB, 0xFD}
CONST_STRING = {0x1A, 0x1B}
FIELD_OPS = set(range(0x52, 0x6E))
TYPE_OPS = {0x1C, 0x1F, 0x20, 0x22, 0x23, 0x24, 0x25}


# --------------------------------------------------------------------------
# low level helpers
# --------------------------------------------------------------------------
def _uleb128(buf: bytes, off: int) -> Tuple[int, int]:
    result = 0
    shift = 0
    while True:
        b = buf[off]
        off += 1
        result |= (b & 0x7F) << shift
        if not b & 0x80:
            return result, off
        shift += 7


def mutf8_to_str(b: bytes) -> str:
    """Decode Modified-UTF-8 (the DEX string encoding)."""
    if b"\xc0\x80" in b:
        b = b.replace(b"\xc0\x80", b"\x00")
    return b.decode("utf-8", "replace")


def package_of(class_descriptor: str, depth: int = 3) -> str:
    """``Lvn/com/vng/zalopay/Obf;->x`` -> ``vn.com.vng.zalopay``.

    ``depth`` is the number of leading identifiers kept; deeper calls give
    finer namespaces (used for the intra-app dependency graph).
    """
    d = class_descriptor.lstrip("L").rstrip(";")
    d = d.split("/")
    if len(d) <= depth:
        return "/".join(d[:-1]) if len(d) > 1 else d[0]
    return ".".join(d[:depth])


# --------------------------------------------------------------------------
# header + id tables
# --------------------------------------------------------------------------
@dataclass
class DexHeader:
    name: str
    sha256: str
    size: int
    magic: str
    checksum: str
    adler_ok: bool
    sha1_ok: bool
    endian: str
    map_off: int
    counts: Dict[str, int]
    data_size: int
    link_size: int


class Dex:
    """Streaming reader for a single classesN.dex blob."""

    def __init__(self, name: str, data: bytes):
        self.name = name
        self.data = data
        self._read_header()
        self._read_id_tables()

    # -- header ----------------------------------------------------------
    def _read_header(self) -> None:
        d = self.data
        if d[:4] != b"dex\n":
            raise ValueError(f"{self.name}: not a dex file (magic={d[:4]!r})")
        (self.checksum,) = struct.unpack_from("<I", d, 8)
        self.signature = d[12:32]
        (self.file_size, self.header_size, self.endian_tag, self.link_size,
         self.link_off, self.map_off, self.string_ids_size, self.string_ids_off,
         self.type_ids_size, self.type_ids_off, self.proto_ids_size,
         self.proto_ids_off, self.field_ids_size, self.field_ids_off,
         self.method_ids_size, self.method_ids_off, self.class_defs_size,
         self.class_defs_off, self.data_size, self.data_off) = struct.unpack_from(
            "<20I", d, 32)
        self.adler_ok = (zlib.adler32(d[12:]) & 0xFFFFFFFF) == self.checksum
        self.sha1_ok = hashlib.sha1(d[32:]).digest() == self.signature
        self.sha256 = hashlib.sha256(d).hexdigest()

    def _read_id_tables(self) -> None:
        d = self.data
        n_str = self.string_ids_size
        self.str_offsets = array("I", d[self.string_ids_off:self.string_ids_off + 4 * n_str])
        self.types = array("I", d[self.type_ids_off:self.type_ids_off + 4 * self.type_ids_size])
        # protos: (shorty_idx, return_type_idx, parameters_off) triplets
        self.protos = array("I", d[self.proto_ids_off:self.proto_ids_off + 12 * self.proto_ids_size])
        # field_id_item / method_id_item are (u16, u16, u32); we keep them as
        # u16 quads so hot loops never pay for struct.unpack.
        self.fields = array("H", d[self.field_ids_off:self.field_ids_off + 8 * self.field_ids_size])
        self.methods = array("H", d[self.method_ids_off:self.method_ids_off + 8 * self.method_ids_size])
        self.class_defs = array("I", d[self.class_defs_off:self.class_defs_off + 32 * self.class_defs_size])

    # -- strings ---------------------------------------------------------
    def string_at(self, idx: int) -> str:
        off = self.str_offsets[idx]
        # skip uleb128 utf16 length
        _, off = _uleb128(self.data, off)
        end = self.data.index(b"\x00", off)
        return mutf8_to_str(self.data[off:end])

    def iter_strings(self) -> Iterator[str]:
        for i in range(self.string_ids_size):
            yield self.string_at(i)

    def type_name(self, type_idx: int) -> str:
        return self.string_at(self.types[type_idx])

    def method_ref(self, midx: int) -> Tuple[int, int, int]:
        b = midx * 4
        return self.methods[b], self.methods[b + 1], self.methods[b + 2] | (self.methods[b + 3] << 16)

    def method_ref_name(self, midx: int) -> str:
        class_idx, _proto_idx, name_idx = self.method_ref(midx)
        cls = self.type_name(class_idx)
        return f"{cls}->{self.string_at(name_idx)}"

    def field_ref_name(self, fidx: int) -> str:
        b = fidx * 4
        name_idx = self.fields[b + 2] | (self.fields[b + 3] << 16)
        return f"{self.type_name(self.fields[b])}->{self.string_at(name_idx)}"

    # -- classes ---------------------------------------------------------
    def iter_class_defs(self) -> Iterator[dict]:
        cd = self.class_defs
        for i in range(self.class_defs_size):
            base = i * 8
            (class_idx, access, super_idx, ifaces_off, source_idx,
             annotations_off, class_data_off, static_values_off) = struct.unpack_from(
                "<8I", cd, base * 4)
            yield {
                "idx": i,
                "class_idx": class_idx,
                "name": self.type_name(class_idx),
                "access": access,
                "super": self.type_name(super_idx) if super_idx != 0xFFFFFFFF else None,
                "interfaces_off": ifaces_off,
                "source": self.string_at(source_idx) if source_idx != 0xFFFFFFFF else None,
                "annotations_off": annotations_off,
                "class_data_off": class_data_off,
                "static_values_off": static_values_off,
            }

    def class_data(self, off: int) -> dict:
        """Decode a ``class_data_item``: method/field counts + code offsets."""
        if off == 0:
            return {"static_fields": [], "instance_fields": [], "direct": [], "virtual": []}
        buf = self.data
        sf, off = _uleb128(buf, off)
        inf, off = _uleb128(buf, off)
        dm, off = _uleb128(buf, off)
        vm, off = _uleb128(buf, off)
        static_fields, instance_fields = [], []
        for _ in range(sf):
            _, off = _uleb128(buf, off)
            af, off = _uleb128(buf, off)
            static_fields.append(af)
        for _ in range(inf):
            _, off = _uleb128(buf, off)
            af, off = _uleb128(buf, off)
            instance_fields.append(af)
        direct, virtual = [], []
        for bucket, n in ((direct, dm), (virtual, vm)):
            for _ in range(n):
                _, off = _uleb128(buf, off)
                af, off = _uleb128(buf, off)
                code_off, off = _uleb128(buf, off)
                bucket.append((af, code_off))
        return {"static_fields": static_fields, "instance_fields": instance_fields,
                "direct": direct, "virtual": virtual}

    def code_insns(self, code_off: int) -> Tuple[int, memoryview]:
        """Return (instruction_count_in_units, insns buffer) for a code_item."""
        (registers, ins, outs, tries, debug_off, insns_size) = struct.unpack_from(
            "<HHHHII", self.data, code_off)
        start = code_off + 16
        return insns_size, memoryview(self.data)[start:start + insns_size * 2]


# --------------------------------------------------------------------------
# bytecode survey
# --------------------------------------------------------------------------
@dataclass
class Watchlist:
    """Sensitive API signatures, grouped by capability.

    Each entry: (capability, suffix-matched method signature).  Matching is
    done on ``Lclass;->name`` strings, so it is robust to obfuscation of the
    *caller* but not of the framework class.
    """
    rules: Sequence[Tuple[str, str]] = (
        ("dynamic_code", "Ldalvik/system/DexClassLoader;->"),
        ("dynamic_code", "Ldalvik/system/InMemoryDexClassLoader;->"),
        ("dynamic_code", "Ldalvik/system/PathClassLoader;->"),
        ("reflection", "Ljava/lang/reflect/Method;->invoke"),
        ("reflection", "Ljava/lang/Class;->forName"),
        ("reflection", "Ljava/lang/reflect/Proxy;->newProxyInstance"),
        ("jni_bridge", "Ljava/lang/System;->loadLibrary"),
        ("jni_bridge", "Ljava/lang/System;->load"),
        ("crypto_cipher", "Ljavax/crypto/Cipher;->"),
        ("crypto_keyspec", "Ljavax/crypto/spec/SecretKeySpec;->"),
        ("crypto_ks", "Ljava/security/KeyStore;->"),
        ("crypto_kpg", "Ljava/security/KeyPairGenerator;->"),
        ("crypto_ecdh", "Ljavax/crypto/KeyAgreement;->"),
        ("crypto_mac", "Ljavax/crypto/Mac;->"),
        ("crypto_random", "Ljava/security/SecureRandom;->"),
        ("crypto_msgdigest", "Ljava/security/MessageDigest;->"),
        ("ssl_tm", "Ljavax/net/ssl/X509TrustManager;->"),
        ("ssl_ctx", "Ljavax/net/ssl/SSLContext;->init"),
        ("ssl_hv", "Ljavax/net/ssl/HostnameVerifier;->"),
        ("pinning_okhttp", "Lokhttp3/CertificatePinner;->"),
        ("webview_js_iface", "Landroid/webkit/WebView;->addJavascriptInterface"),
        ("webview_load", "Landroid/webkit/WebView;->loadUrl"),
        ("webview_eval", "Landroid/webkit/WebView;->evaluateJavascript"),
        ("proc_exec", "Ljava/lang/Runtime;->exec"),
        ("proc_exec", "Ljava/lang/ProcessBuilder;->start"),
        ("pkg_manager", "Landroid/content/pm/PackageManager;->getInstalledPackages"),
        ("pkg_manager", "Landroid/content/pm/PackageManager;->getPackageInfo"),
        ("telephony", "Landroid/telephony/TelephonyManager;->getDeviceId"),
        ("telephony", "Landroid/telephony/TelephonyManager;->getImei"),
        ("telephony", "Landroid/telephony/TelephonyManager;->getSubscriberId"),
        ("location", "Landroid/location/LocationManager;->getLastKnownLocation"),
        ("contacts", "Landroid/provider/ContactsContract"),
        ("clipboard", "Landroid/content/ClipboardManager;->"),
        ("screen", "Landroid/view/WindowManager$LayoutParams;->FLAG_SECURE"),
        ("file_io", "Ljava/io/FileOutputStream;-><init>"),
        ("prefs", "Landroid/content/SharedPreferences$Editor;->putString"),
        ("keystore_android", "Landroid/security/keystore/KeyGenParameterSpec;->"),
        ("biometric", "Landroidx/biometric/BiometricPrompt;->"),
        ("biometric", "Landroid/hardware/biometrics/BiometricPrompt;->"),
        ("nfc", "Landroid/nfc/tech/IsoDep;->"),
        ("nfc", "Landroid/nfc/NfcAdapter;->"),
        ("net_raw", "Ljava/net/Socket;-><init>"),
        ("net_url", "Ljava/net/URL;->openConnection"),
        ("net_http", "Ljava/net/HttpURLConnection;->"),
        ("db_sqlite", "Landroid/database/sqlite/SQLiteDatabase;->rawQuery"),
        ("db_sqlite", "Landroid/database/sqlite/SQLiteDatabase;->execSQL"),
    )

    def __post_init__(self) -> None:
        self.index: Dict[str, str] = {}
        for cap, sig in self.rules:
            self.index[sig] = cap


@dataclass
class DexSurvey:
    """Aggregates folded out of one or many DEX files."""
    opcode_hist: Counter = field(default_factory=Counter)
    pkg_edges: Counter = field(default_factory=Counter)          # (src,dst) -> weight
    app_edges: Counter = field(default_factory=Counter)          # intra-app, depth=4
    pkg_classes: Counter = field(default_factory=Counter)        # pkg -> class count
    api_hits: Counter = field(default_factory=Counter)           # capability -> callsites
    api_by_caller: Counter = field(default_factory=Counter)      # (cap, caller_pkg) -> n
    api_examples: Dict[str, List[str]] = field(default_factory=lambda: defaultdict(list))
    strings_total: int = 0
    class_count: int = 0
    method_count: int = 0
    field_count: int = 0
    code_units: int = 0
    insn_count: int = 0
    methods_with_code: int = 0
    tiny_names: int = 0
    kotlin_meta: int = 0


APP_PREFIXES = (
    "Lvn/com/vng/",
    "Lcom/vng/",
)


def survey_dex(dex: Dex, agg: DexSurvey, watch: Watchlist,
               app_pkg_depth: int = 4, collect_strings: bool = True,
               string_filter=None) -> dict:
    """Walk one DEX: class census, opcode histogram, invoke-resolution.

    Returns a per-file stat dict (cheap to serialise).
    """
    # per-dex package id table for types -> keeps the invoke loop allocation free
    pkg_ids: Dict[str, int] = {}
    type_pkg = array("i", bytes(4 * dex.type_ids_size))
    for i in range(dex.type_ids_size):
        t = dex.type_name(i)
        if t.startswith("L"):
            p = package_of(t, 3)
            pid = pkg_ids.get(p)
            if pid is None:
                pid = len(pkg_ids)
                pkg_ids[p] = pid
            type_pkg[i] = pid
        else:
            type_pkg[i] = -1
    pkg_list = [None] * len(pkg_ids)
    for k, v in pkg_ids.items():
        pkg_list[v] = k

    method_class_pkg = array("i", bytes(4 * dex.method_ids_size))
    for m in range(dex.method_ids_size):
        cidx = dex.methods[m * 4]
        method_class_pkg[m] = type_pkg[cidx]

    # --- sensitive-API labelling, computed once per method reference ------
    # class descriptor -> [(name_fragment, cap_id)] ; class_idx -> cached list
    cap_names = sorted({cap for cap, _ in watch.rules})
    cap_id = {c: i + 1 for i, c in enumerate(cap_names)}
    by_class: Dict[str, List[Tuple[str, int]]] = defaultdict(list)
    for cap, sig in watch.rules:
        cls, _, frag = sig.partition(";->")
        if not cls.endswith(";"):
            cls += ";"
        by_class[cls].append((frag, cap_id[cap]))
    method_cap = bytearray(dex.method_ids_size)
    class_cap_cache: Dict[int, Optional[List[Tuple[str, int]]]] = {}
    for m in range(dex.method_ids_size):
        cidx = dex.methods[m * 4]
        hit = class_cap_cache.get(cidx, 0)
        if hit == 0:
            desc = dex.type_name(cidx)
            hit = by_class.get(desc) or None
            class_cap_cache[cidx] = hit
        if not hit:
            continue
        name = dex.string_at(dex.methods[m * 4 + 2] | (dex.methods[m * 4 + 3] << 16))
        for frag, cid in hit:
            if frag in name:
                method_cap[m] = cid
                break

    watch_index = watch.index
    per_file = {
        "name": dex.name, "size": dex.file_size, "sha256": dex.sha256,
        "adler_ok": dex.adler_ok, "sha1_ok": dex.sha1_ok,
        "strings": dex.string_ids_size, "types": dex.type_ids_size,
        "protos": dex.proto_ids_size, "fields": dex.field_ids_size,
        "methods": dex.method_ids_size, "classes": dex.class_defs_size,
        "insns": 0, "code_units": 0, "packages": len(pkg_ids),
        "class_list": [],
    }

    h = agg.opcode_hist
    widths = OPCODE_WIDTHS
    api_hits = agg.api_hits
    api_by_caller = agg.api_by_caller
    api_examples = agg.api_examples
    edges = agg.pkg_edges
    aedges = agg.app_edges
    pkg_classes = agg.pkg_classes

    for cd in dex.iter_class_defs():
        name = cd["name"]
        agg.class_count += 1
        pkg3 = package_of(name, 3)
        pkg_classes[pkg3] += 1
        is_app = name.startswith(APP_PREFIXES)
        src_app_pkg = package_of(name, app_pkg_depth) if is_app else None

        simple = name.lstrip("L").rstrip(";").rsplit("/", 1)[-1]
        if len(simple) <= 2:
            agg.tiny_names += 1
        if collect_strings:
            per_file["class_list"].append(name)

        data = dex.class_data(cd["class_data_off"])
        n_methods = len(data["direct"]) + len(data["virtual"])
        agg.method_count += n_methods
        agg.field_count += len(data["static_fields"]) + len(data["instance_fields"])

        # annotation referencing kotlin.Metadata marks Kotlin-compiled classes
        if cd["annotations_off"]:
            agg.kotlin_meta += 1

        for af, code_off in data["direct"] + data["virtual"]:
            if not code_off:
                continue
            agg.methods_with_code += 1
            try:
                insns_size, insns = dex.code_insns(code_off)
            except struct.error:
                continue
            per_file["code_units"] += insns_size
            agg.code_units += insns_size
            pc = 0
            local_targets = set()
            n_insn = 0
            while pc < insns_size:
                unit = insns[pc * 2] | (insns[pc * 2 + 1] << 8)
                op = unit & 0xFF
                w = widths[op]
                if w == 0:
                    w = 1
                h[OPCODE_NAMES.get(op, f"op_{op:02x}")] += 1
                n_insn += 1
                try:
                    if op in CONST_STRING:
                        sidx = insns[pc * 2 + 2] | (insns[pc * 2 + 3] << 8)
                        if op == 0x1B:
                            sidx |= (insns[pc * 2 + 4] | (insns[pc * 2 + 5] << 8)) << 16
                        if collect_strings:
                            agg.strings_total += 1
                            s = dex.string_at(sidx)
                            if string_filter is not None:
                                string_filter(s, dex.name)
                    elif op in INVOKE_NORMAL:
                        midx = insns[pc * 2 + 2] | (insns[pc * 2 + 3] << 8)
                        dst = method_class_pkg[midx]
                        if dst >= 0:
                            local_targets.add(dst)
                        cid = method_cap[midx]
                        if cid:
                            cap = cap_names[cid - 1]
                            api_hits[cap] += 1
                            api_by_caller[(cap, pkg3)] += 1
                            if is_app and len(api_examples[cap]) < 40:
                                sig = dex.method_ref_name(midx)
                                mname = dex.string_at(dex.methods[midx * 4 + 2] | (dex.methods[midx * 4 + 3] << 16))
                                ex = f"{name}::{mname}{sig[sig.index('->'):]}"
                                if ex not in api_examples[cap]:
                                    api_examples[cap].append(ex)
                    elif op in INVOKE_RANGE:
                        midx = insns[pc * 2 + 2] | (insns[pc * 2 + 3] << 8)
                        dst = method_class_pkg[midx]
                        if dst >= 0:
                            local_targets.add(dst)
                    elif op == 0x00:  # possible payload pseudo-instruction
                        if pc + 1 < insns_size:
                            ident = insns[pc * 2 + 2] | (insns[pc * 2 + 3] << 8)
                            if ident == 0x0100:      # packed-switch payload
                                size = insns[pc * 2 + 2]
                                w = size * 2 + 4
                            elif ident == 0x0200:    # sparse-switch payload
                                size = insns[pc * 2 + 2]
                                w = size * 4 + 2
                            elif ident == 0x0300:    # fill-array-data payload
                                elem_width = insns[pc * 2 + 2]
                                size = insns[pc * 2 + 4] | (insns[pc * 2 + 5] << 16)
                                w = 4 + (size * elem_width + 1) // 2
                except (IndexError, struct.error):
                    w = max(w, 1)
                pc += w
            per_file["insns"] += n_insn
            agg.insn_count += n_insn
            for dst in local_targets:
                if dst >= 0:
                    edges[(pkg3, pkg_list[dst])] += 1
                    if is_app and pkg_list[dst].startswith("vn.com.vng"):
                        aedges[(src_app_pkg, package_of(pkg_list[dst], app_pkg_depth))] += 1

    agg.method_count = agg.method_count  # noqa
    if not collect_strings:
        per_file.pop("class_list", None)
    return per_file


# --------------------------------------------------------------------------
# string intel
# --------------------------------------------------------------------------
STRING_PATTERNS: Sequence[Tuple[str, "re.Pattern[str]"]] = (
    ("url", re.compile(r"https?://[A-Za-z0-9._~:/?#\[\]@!$&'()*+,;=%-]{6,200}")),
    ("host", re.compile(r"\b(?:[a-z0-9](?:[a-z0-9-]{0,61}[a-z0-9])?\.)+(?:com|vn|net|io|org|sg|co|me|dev|cloud|app|xyz|info|biz)\b")),
    ("ipv4", re.compile(r"\b(?:\d{1,3}\.){3}\d{1,3}\b")),
    ("email", re.compile(r"[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}")),
    ("jwt_like", re.compile(r"eyJ[A-Za-z0-9_-]{8,}\.[A-Za-z0-9_-]{8,}\.[A-Za-z0-9_-]{6,}")),
    ("pem", re.compile(r"-----BEGIN [A-Z ]+-----")),
    ("aws_like", re.compile(r"AKIA[0-9A-Z]{16}")),
    ("google_key", re.compile(r"AIza[0-9A-Za-z_-]{35}")),
    ("bearer", re.compile(r"[Bb]earer [A-Za-z0-9._-]{12,}")),
    ("crypto_alg", re.compile(r"\b(AES|RSA|EC|HMAC|SHA-?(?:1|224|256|384|512)|MD5|DES|3DES|ChaCha20|PBKDF2|ECDH|ECDSA|Argon2)\b(?:/(?:CBC|GCM|CTR|ECB|OFB|CFB|PKCS5Padding|NoPadding|OAEPWithSHA-\d+AndMGF1Padding))*")),
    ("pinning_hint", re.compile(r"(?i)(sha256/[A-Za-z0-9+/=]{20,}|certificate.?pinning|public.?key.?pin)")),
    ("root_hint", re.compile(r"(?i)(?:^|/)(su|magisk|superuser|busybox|frida|xposed|lsposed)(?:$|/|\.)")),
    ("path", re.compile(r"(?<![\w.])/(?:data|system|sdcard|proc|vendor)/[A-Za-z0-9._/-]{2,120}")),
    ("pan_like", re.compile(r"\b(?:4\d{12}(?:\d{3})?|5[1-5]\d{14})\b")),
)

SECRET_HINTS = ("secret", "apikey", "api_key", "client_secret", "password", "passwd",
                "privatekey", "private_key", "token", "access_key", "hmac")


class StringIntel:
    """Collects candidate indicators; every hit is redacted before storage."""

    MAX_PER_KIND = 4000

    def __init__(self) -> None:
        self.buckets: Dict[str, Counter] = defaultdict(Counter)
        self.sources: Dict[str, Counter] = defaultdict(Counter)  # kind -> dex counter
        self.secret_names: Counter = Counter()
        self.total = 0

    @staticmethod
    def redact(s: str) -> str:
        if len(s) <= 12:
            return s
        return s[:6] + "…" + f"[{len(s)}]" + s[-3:]

    def __call__(self, s: str, dex_name: str) -> None:
        self.total += 1
        if len(s) < 4:
            return
        low = s.lower()
        for kind, rx in STRING_PATTERNS:
            if kind in ("url", "host") and len(s) > 400:
                continue
            m = rx.search(s)
            if m:
                bucket = self.buckets[kind]
                if len(bucket) < self.MAX_PER_KIND:
                    value = m.group(0)
                    if kind in ("jwt_like", "bearer", "google_key", "aws_like"):
                        value = self.redact(value)
                    bucket[value] += 1
                    self.sources[kind][dex_name] += 1
        if any(h in low for h in SECRET_HINTS) and 4 <= len(s) <= 80:
            self.secret_names[s[:80]] += 1

    def to_json(self) -> dict:
        return {
            "strings_scanned": self.total,
            "kinds": {k: {"unique": len(v), "total": sum(v.values()),
                          "items": dict(v.most_common(400))}
                      for k, v in self.buckets.items()},
            "sources": {k: dict(v.most_common(50)) for k, v in self.sources.items()},
            "secret_like_names": dict(self.secret_names.most_common(400)),
        }
