"""
Shared helpers for the ZaloPay 11.17.0 reverse-engineering pipeline.

Design notes
------------
Everything in this package is *artifact-driven*: each stage reads immutable
inputs (the ZIP/XAPK/APK bytes) and emits a single JSON document under
``research/data``.  Downstream stages (the technical report and the film
renderer) never touch the APK again -- they only read those JSON documents.
That separation is what makes the study reproducible and auditable.

Ethics: every extractor that can encounter credential material routes through
``redact()``.  No raw secret value ever reaches disk outside ``work/``.
"""

from __future__ import annotations

import hashlib
import json
import math
import os
import re
import struct
import subprocess
import sys
from collections import Counter
from pathlib import Path

ROOT = Path(__file__).resolve().parent.parent
WORK = ROOT / "work"
UNPACKED = WORK / "unpacked"
XAPK_DIR = WORK / "xapk"
DATA = ROOT / "research" / "data"
DATA.mkdir(parents=True, exist_ok=True)

OUTER_ZIP = ROOT / "Zalopay-Thanh+toán+&+Tài+chính_11.17.0_APKPure.zip"
_xapk_candidates = sorted(WORK.glob("*.xapk")) if WORK.exists() else []
XAPK = _xapk_candidates[0] if _xapk_candidates else None

BASE_PKG = "vn.com.vng.zalopay"


# --------------------------------------------------------------------------- #
# provenance
# --------------------------------------------------------------------------- #
def sha256_file(path: Path, chunk: int = 1 << 20) -> str:
    h = hashlib.sha256()
    with open(path, "rb") as fh:
        while True:
            b = fh.read(chunk)
            if not b:
                break
            h.update(b)
    return h.hexdigest()


def sha256_bytes(b: bytes) -> str:
    return hashlib.sha256(b).hexdigest()


def entropy(data: bytes) -> float:
    """Shannon entropy in bits/byte (0.0 .. 8.0)."""
    if not data:
        return 0.0
    counts = Counter(data)
    n = len(data)
    return -sum((c / n) * math.log2(c / n) for c in counts.values())


def entropy_file(path: Path, cap: int = 4 << 20) -> float:
    with open(path, "rb") as fh:
        return entropy(fh.read(cap))


# --------------------------------------------------------------------------- #
# type detection (no libmagic dependency)
# --------------------------------------------------------------------------- #
MAGICS = [
    (b"PK\x03\x04", "zip"),
    (b"\x7fELF", "elf"),
    (b"\x1f\x8b", "gzip"),
    (b"\x89PNG", "png"),
    (b"\xff\xd8\xff", "jpeg"),
    (b"RIFF", "riff"),
    (b"dex\n", "dex"),
    (b"\x03\x00\x08\x00", "arsc"),
    (b"<?xml", "xml-plain"),
    (b"SQLite format 3", "sqlite"),
    (b"\x1a\x45\xdf\xa3", "webm/mkv"),
    (b"\x00\x00\x00\x0cjP", "jpeg2000"),
    (b"ftyp", "mp4"),
    (b"\x28\xb5\x2f\xfd", "zstd"),
    (b"\x18\x00\x00\x00", "protobuf"),
    (b"TFL3", "tflite-magic"),
]


def sniff(path: Path) -> str:
    try:
        with open(path, "rb") as fh:
            head = fh.read(64)
    except OSError:
        return "unreadable"
    for magic, name in MAGICS:
        if head.startswith(magic):
            return name
        if magic == b"ftyp" and head[4:8] == b"ftyp":
            return "mp4"
    ext = path.suffix.lower()
    if ext in {".so", ".dex", ".arsc", ".ttf", ".otf", ".amr", ".wav", ".mp3",
               ".js", ".json", ".html", ".txt", ".bin", ".dat", ".ncnn", ".param",
               ".pem", ".crt", ".xml", ".properties", ".proto", ".lite"}:
        return f"ext:{ext[1:]}"
    # entropy heuristic: dense high-entropy blobs are compressed or encrypted
    try:
        e = entropy_file(path, 8192)
    except OSError:
        return "unknown"
    if e > 7.5:
        return "high-entropy-blob"
    return "text-or-data" if e < 6.0 else "binary"


# --------------------------------------------------------------------------- #
# redaction -- the single choke point for sensitive strings
# --------------------------------------------------------------------------- #
SENSITIVE_PATTERNS = [
    ("google_api_key", re.compile(r"AIza[0-9A-Za-z_\-]{35}")),
    ("aws_access_key", re.compile(r"(?:AKIA|ASIA)[0-9A-Z]{16}")),
    ("private_key_pem", re.compile(r"-----BEGIN (?:RSA |EC |)PRIVATE KEY-----")),
    ("jwt", re.compile(r"eyJ[A-Za-z0-9_\-]{8,}\.eyJ[A-Za-z0-9_\-]{8,}\.[A-Za-z0-9_\-]{8,}")),
    ("bearer_literal", re.compile(r"[Bb]earer\s+[A-Za-z0-9\-._~+/]{20,}")),
    ("slack_token", re.compile(r"xox[abprs]-[0-9A-Za-z\-]{10,}")),
    ("zalo_oa_secret", re.compile(r"(?i)(?:app_?secret|oa_?secret|secret_?key)\s*[=:]\s*[\"']?([A-Za-z0-9]{16,})")),
]


def redact(value: str, keep: int = 4) -> str:
    """Irreversibly summarise a sensitive value: length + prefix + SHA-256/8."""
    if not value:
        return ""
    return (
        f"{value[:keep]}…[{len(value)} chars]"
        f"#{hashlib.sha256(value.encode()).hexdigest()[:8]}"
    )


# --------------------------------------------------------------------------- #
# misc
# --------------------------------------------------------------------------- #
def dump(name: str, obj) -> Path:
    p = DATA / name
    p.write_text(json.dumps(obj, indent=1, ensure_ascii=False, sort_keys=False))
    size = p.stat().st_size
    print(f"  → {p.relative_to(ROOT)} ({size/1024:.1f} KiB)", flush=True)
    return p


def human(n: float) -> str:
    for unit in ("B", "KiB", "MiB", "GiB"):
        if abs(n) < 1024:
            return f"{n:.1f} {unit}" if unit != "B" else f"{int(n)} B"
        n /= 1024
    return f"{n:.1f} TiB"


def banner(title: str) -> None:
    print(f"\n\033[1m▶ {title}\033[0m", flush=True)


def run(cmd: list[str], **kw) -> subprocess.CompletedProcess:
    return subprocess.run(cmd, capture_output=True, text=True, **kw)


__all__ = [
    "ROOT", "WORK", "UNPACKED", "XAPK_DIR", "XAPK", "DATA", "OUTER_ZIP",
    "BASE_PKG", "sha256_file", "sha256_bytes", "entropy", "entropy_file",
    "sniff", "SENSITIVE_PATTERNS", "redact", "dump", "human", "banner", "run",
]
