"""Shared helpers for the Zalopay 11.17.0 static-analysis pipeline.

Design goals (aligned with the *evidence-first* methodology popularised by
morluto/rea): every number that appears in the report or the video must be
re-derivable from the on-disk artifacts by re-running a stage in this package.
No stage reaches the network, and no stage mutates the input corpus.
"""

from __future__ import annotations

import gzip
import hashlib
import json
import math
import os
import re
import unicodedata
from collections import Counter
from dataclasses import dataclass, field, asdict
from pathlib import Path
from typing import Any, Iterable, Iterator, Sequence

# --------------------------------------------------------------------------
# Repository layout
# --------------------------------------------------------------------------

ROOT = Path(__file__).resolve().parents[2]
WORK = ROOT / "work"
DATA = ROOT / "analysis" / "data"
DOCS = ROOT / "docs"

INPUT_ZIP = ROOT / "Zalopay-Thanh+toán+&+Tài+chính_11.17.0_APKPure.zip"
STAGE = WORK / "extract"
XAPK_DIR = STAGE / "xapk"
APK_DIR = WORK / "apk"          # exploded base APK
NATIVE_DIR = WORK / "native"    # exploded armeabi-v7a split

for _d in (DATA, WORK, DOCS):
    _d.mkdir(parents=True, exist_ok=True)

# --------------------------------------------------------------------------
# Hashing, entropy, magic detection
# --------------------------------------------------------------------------

MAGIC: list[tuple[bytes, str, str]] = [
    (b"PK\x03\x04", "zip", "ZIP / APK / JAR container"),
    (b"PK\x05\x06", "zip-empty", "ZIP end-of-central-directory"),
    (b"dex\n", "dex", "Dalvik executable"),
    (b"\x7fELF", "elf", "ELF shared object"),
    (b"\x89PNG\r\n\x1a\n", "png", "Portable Network Graphics"),
    (b"\xff\xd8\xff", "jpeg", "JPEG image"),
    (b"dex\n035", "dex035", "Dalvik executable, format 035"),
    (b"\x03\x00\x08\x00", "arsc", "Android binary resource table"),
    (b"\x02\x00\x0c\x00", "axml", "Android binary XML"),
    (b"{\n  \"xapk_version", "xapk-manifest", "XAPK manifest (JSON)"),
    (b"<?xml", "xml", "XML document"),
    (b"\x1f\x8b", "gzip", "gzip stream"),
    (b"RIFF", "riff", "RIFF container (WAV/WebP)"),
    (b"SQLite format 3\x00", "sqlite", "SQLite database"),
    (b"RSA", "rsa", "PKCS#7 signature"),
    (b"-----BEGIN", "pem", "PEM encoded material"),
    (b"\xd0\xcf\x11\xe0", "ole", "OLE compound document"),
    (b"protobuf", "proto", "Protocol Buffers (heuristic)"),
]


def sha256_bytes(b: bytes) -> str:
    return hashlib.sha256(b).hexdigest()


def sha256_file(path: Path, chunk: int = 1 << 20) -> str:
    h = hashlib.sha256()
    with open(path, "rb") as fh:
        while True:
            blk = fh.read(chunk)
            if not blk:
                break
            h.update(blk)
    return h.hexdigest()


def md5_file(path: Path, chunk: int = 1 << 20) -> str:
    h = hashlib.md5()
    with open(path, "rb") as fh:
        while True:
            blk = fh.read(chunk)
            if not blk:
                break
            h.update(blk)
    return h.hexdigest()


def shannon_entropy(data: bytes) -> float:
    if not data:
        return 0.0
    counts = Counter(data)
    n = len(data)
    return -sum((c / n) * math.log2(c / n) for c in counts.values())


def detect_type(head: bytes, name: str = "") -> tuple[str, str]:
    for sig, kind, desc in MAGIC:
        if head.startswith(sig):
            return kind, desc
    if name.endswith(".so"):
        return "elf?", "ELF expected by extension"
    if name.endswith((".kt", ".java", ".txt", ".md", ".properties", ".pro")):
        return "text", "UTF-8 text"
    return "data", "unrecognised (high-entropy or proprietary)"


def read_head(path: Path, n: int = 16) -> bytes:
    with open(path, "rb") as fh:
        return fh.read(n)


# --------------------------------------------------------------------------
# Manifest parsing (binary AXML) — uses androguard's decoder
# --------------------------------------------------------------------------

def decode_axml(path: Path) -> dict[str, Any]:
    from androguard.core.axml import AXMLPrinter  # local import: slow module

    raw = path.read_bytes()
    ap = AXMLPrinter(raw)
    return {
        "xml": ap.get_xml().decode("utf-8", "replace") if ap.get_xml() else "",
        "package": ap.get_package() if hasattr(ap, "get_package") else None,
    }


# --------------------------------------------------------------------------
# JSON helpers
# --------------------------------------------------------------------------

def write_json(name: str, obj: Any) -> Path:
    p = DATA / name
    p.write_text(json.dumps(obj, indent=2, ensure_ascii=False, sort_keys=False), encoding="utf-8")
    return p


def write_json_gz(name: str, obj: Any) -> Path:
    p = DATA / name
    with gzip.open(p, "wt", encoding="utf-8", compresslevel=9) as fh:
        json.dump(obj, fh, ensure_ascii=False, separators=(",", ":"))
    return p


def read_json(name: str) -> Any:
    return json.loads((DATA / name).read_text(encoding="utf-8"))


# --------------------------------------------------------------------------
# Name / string utilities
# --------------------------------------------------------------------------

_UUID_RE = re.compile(r"^[0-9a-f]{8}-?[0-9a-f]{4}-?[0-9a-f]{4}-?[0-9a-f]{4}-?[0-9a-f]{12}$", re.I)


def looks_garbage(name: str) -> bool:
    """Heuristic for single-purpose obfuscation names (a, b, ab, a1b2, ...)."""
    if not name:
        return True
    if _UUID_RE.match(name):
        return True
    return len(name) <= 2


def name_entropy(name: str) -> float:
    return shannon_entropy(name.encode("utf-8"))


def is_pii_key(key: str) -> bool:
    low = key.lower()
    return any(t in low for t in ("password", "passwd", "secret", "token", "apikey", "api_key"))


def redact(value: str, keep: int = 4) -> str:
    """Never publish a live secret: keep a stable short fingerprint instead."""
    if len(value) <= keep:
        return "*" * len(value)
    return f"{value[:keep]}{'*' * 8}[len={len(value)},sha256={sha256_bytes(value.encode())[:12]}]"


def tidy(text: str, limit: int = 200) -> str:
    text = unicodedata.normalize("NFC", text).replace("\n", " ").strip()
    return text if len(text) <= limit else text[: limit - 1] + "…"


# --------------------------------------------------------------------------
# Progress reporting
# --------------------------------------------------------------------------

@dataclass
class Stage:
    name: str
    notes: list[str] = field(default_factory=list)

    def log(self, msg: str) -> None:
        self.notes.append(msg)
        print(f"[{self.name}] {msg}", flush=True)

    def done(self, extra: dict[str, Any] | None = None) -> None:
        payload = {"stage": self.name, "notes": self.notes}
        if extra:
            payload["result"] = extra
        print(json.dumps({"stage": self.name, "ok": True, **(extra or {})}, ensure_ascii=False))


def iter_zip_entries(path: Path) -> Iterator[tuple[str, int, int, str]]:
    """Yield (name, compressed_size, file_size, crc_hex) for a ZIP-like container."""
    import zipfile

    with zipfile.ZipFile(path) as zf:
        for info in zf.infolist():
            yield info.filename, info.compress_size, info.file_size, f"{info.CRC:08x}"


def human_bytes(n: float) -> str:
    for unit in ("B", "KiB", "MiB", "GiB"):
        if abs(n) < 1024.0:
            return f"{n:3.1f} {unit}"
        n /= 1024.0
    return f"{n:.1f} TiB"
