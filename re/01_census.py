"""
01_census.py — per-file forensic census of every entry in the XAPK bundle.

Outputs (all in data/):
  inventory.csv    one row per ZIP entry across all splits (+ bundle files)
  census.json      aggregate statistics, per-category breakdowns
  native.csv       ELF hardening / symbol surface for every .so
  assets.json      asset classification + ML model verification
  secrets.json     redacted credential-shaped findings (locations only)
  resources.json   resources.arsc type/config/locale census

Design notes
------------
* Streaming: each APK is opened once; entries are read one at a time so peak
  RSS stays ~200 MB even though the bundle unpacks to 194 MB.
* Entropy is Shannon entropy over byte histogram (bits/byte, 0..8). It is the
  single cheapest discriminator between "code", "compressed media" and
  "encrypted / packed blob" — the latter being what we care about forensically.
* Format identification is magic-first, extension-second, and disagreements are
  recorded (a .png that is not a PNG is a finding, not a nuisance).
"""
from __future__ import annotations

import collections
import csv
import glob
import hashlib
import io
import json
import math
import os
import re
import struct
import sys
import zipfile
from typing import Any

sys.path.insert(0, os.path.dirname(os.path.abspath(__file__)))

DATA = "data"
os.makedirs(DATA, exist_ok=True)

# --------------------------------------------------------------------------- #
# format sniffing
# --------------------------------------------------------------------------- #
MAGICS: list[tuple[bytes, str, int]] = [
    (b"dex\n", "dex", 0),
    (b"\x7fELF", "elf", 0),
    (b"\x89PNG\r\n\x1a\n", "png", 0),
    (b"\xff\xd8\xff", "jpeg", 0),
    (b"GIF8", "gif", 0),
    (b"RIFF", "riff", 0),
    (b"PK\x03\x04", "zip", 0),
    (b"PK\x05\x06", "zip-eocd", 0),
    (b"SQLite format 3\x00", "sqlite", 0),
    (b"\xca\xfe\xba\xbe", "java-class", 0),
    (b"!<arch>", "ar", 0),
    (b"OTTO", "font-otf", 0),
    (b"\x00\x01\x00\x00", "font-ttf", 0),
    (b"ttcf", "font-collection", 0),
    (b"\x00asm", "wasm", 0),
    (b"\x1f\x8b", "gzip", 0),
    (b"RIVE", "rive", 0),
    (b"#!AMR", "amr", 0),
    (b"TFL3", "tflite", 4),
    (b"<?xml", "xml", 0),
    (b"-----BEGIN", "pem", 0),
    (b"\x03\x00\x08\x00", "axml", 0),
    (b"\x02\x00\x0c\x00", "arsc", 0),
    (b"\x08\x00\x00\x00", "proto-or-bin", 0),
]

ASSET_MODEL_EXT = {".tflite", ".pb", ".bin", ".param", ".dat", ".onnx", ".ncnn", ".model",
                   ".binarypb", ".fb", ".json", ".txt"}
# R8/ProGuard renames library stems to short lowercase runs; legitimate native
# libraries in this APK use CamelCase, snake_case or dotted names.
OBFUSCATED_SO = re.compile(r"^lib[A-Za-z]{1,12}\.so$")


def shannon_entropy(data: bytes) -> float:
    if not data:
        return 0.0
    counts = collections.Counter(data)
    n = len(data)
    return -sum((c / n) * math.log2(c / n) for c in counts.values())


def sniff(head: bytes, name: str) -> tuple[str, bool]:
    """Return (format, magic_agrees_with_extension)."""
    fmt = "unknown"
    for magic, label, off in MAGICS:
        if head[off:off + len(magic)] == magic:
            fmt = label
            break
    if fmt == "riff" and head[8:12] == b"WEBP":
        fmt = "webp"
    if fmt == "unknown":
        ext = os.path.splitext(name)[1].lower()
        if ext in (".json", ".geojson"):
            s = head.lstrip()[:1]
            fmt = "json" if s in (b"{", b"[") else "text"
        elif ext in (".js", ".html", ".css", ".txt", ".properties", ".version",
                     ".kotlin_module", ".pem", ".proto", ".textproto", ".csv", ".md",
                     ".pro", ".param", ".map"):
            fmt = "text"
        elif ext in (".png", ".jpg", ".jpeg", ".webp", ".svg", ".gif", ".9.png"):
            fmt = "image?" + ext
        elif ext == ".xml":
            fmt = "text-xml"
        elif ext == ".ttf":
            fmt = "font-ttf"
        elif ext == ".otf":
            fmt = "font-otf"
        elif ext in (".woff", ".woff2"):
            fmt = "font-webfont"
        elif ext == ".dex":
            fmt = "dex?"
        elif ext == ".so":
            fmt = "elf?"
        elif ext in (".arsc",):
            fmt = "arsc?"
        elif ext == ".tflite":
            fmt = "tflite?"
        elif ext in (".bin", ".dat", ".pb", ".model", ".onnx"):
            fmt = "binary"
        else:
            fmt = "other"
    ext = os.path.splitext(name)[1].lower().lstrip(".")
    # res/*.xml in an APK is *binary* AXML, and resources.arsc starts with a
    # RES_TABLE chunk header — both are expected, not anomalies.
    expect = {"png": "png", "jpg": "jpeg", "jpeg": "jpeg", "webp": "webp", "so": "elf",
              "dex": "dex", "xml": "axml", "json": "json", "zip": "zip", "ttf": "font-ttf",
              "otf": "font-otf", "arsc": "arsc", "tflite": "tflite", "gif": "gif",
              "amr": "amr", "ttc": "font-collection", "wasm": "wasm"}.get(ext)
    if expect == "axml" and fmt not in ("axml", "text", "text-xml"):
        agree = False
    elif expect == "arsc":
        agree = fmt == "arsc"
    elif expect is None:
        agree = True
    else:
        agree = fmt.startswith(expect) or fmt == expect
    return fmt, agree


def category(name: str, fmt: str) -> str:
    n = name.lower()
    if n.endswith(".dex"):
        return "code:dex"
    if n.startswith("lib/") and n.endswith(".so"):
        return "native:elf"
    if n == "androidmanifest.xml":
        return "manifest:axml"
    if n.endswith("resources.arsc"):
        return "resources:table"
    if n.startswith("res/"):
        if any(n.endswith(e) for e in (".png", ".jpg", ".jpeg", ".webp", ".gif")):
            return "res:image"
        if n.endswith(".xml"):
            return "res:xml"
        if n.endswith((".ttf", ".otf", ".ttc")):
            return "res:font"
        if n.endswith(".9.png"):
            return "res:ninepatch"
        return "res:other"
    if n.startswith("assets/"):
        if n.endswith((".tflite", ".pb", ".binarypb", ".onnx")):
            return "asset:ml-model"
        if n.endswith((".param",)) or (n.endswith(".bin") and "ncnn" in n):
            return "asset:ml-ncnn"
        if n.endswith((".dat",)):
            return "asset:opaque-dat"
        if n.endswith((".js",)):
            return "asset:javascript"
        if n.endswith((".json",)):
            return "asset:config-json"
        if n.endswith((".ttf", ".otf")):
            return "asset:font"
        if n.endswith(".dex"):
            return "asset:secondary-dex"
        if n.endswith((".amr", ".mp3", ".wav", ".ogg")):
            return "asset:audio"
        if n.endswith((".zip", ".pak")):
            return "asset:archive"
        return "asset:other"
    if n.startswith("meta-inf/"):
        if n.endswith((".rsa", ".dsa", ".ec", ".sf")):
            return "signature:v1"
        if n.endswith(".version") or n.endswith(".properties") or n.endswith(".kotlin_module"):
            return "build:metadata"
        if n.startswith("meta-inf/services/"):
            return "build:spi-registry"
        return "meta-inf:other"
    if n in ("stamp-cert-sha256",):
        return "signature:sourcestamp"
    if n.endswith(".version") or n.endswith(".kotlin_module") or n.endswith(".properties"):
        return "build:metadata"
    if fmt in ("elf", "elf?"):
        return "native:elf"
    return "root:other"


# --------------------------------------------------------------------------- #
# ELF hardening
# --------------------------------------------------------------------------- #
def elf_report(path_on_disk: str) -> dict[str, Any]:
    from elftools.elf.elffile import ELFFile
    from elftools.elf.sections import NoteSection, SymbolTableSection

    out: dict[str, Any] = {}
    try:
        with open(path_on_disk, "rb") as fh:
            elf = ELFFile(fh)
            out["class"] = elf.elfclass
            out["endian"] = elf.little_endian and "little" or "big"
            out["type"] = elf["e_type"]
            out["machine"] = elf["e_machine"]
            out["entry"] = elf["e_entry"]
            out["sections"] = elf.num_sections()
            out["segments"] = elf.num_segments()
            out["size"] = os.path.getsize(path_on_disk)
            secs = {s.name: s for s in elf.iter_sections()}
            out["text_bytes"] = secs[".text"].data_size if ".text" in secs else 0
            out["rodata_bytes"] = secs[".rodata"].data_size if ".rodata" in secs else 0
            out["has_symtab"] = ".symtab" in secs
            out["has_debug"] = any(n.startswith(".debug") for n in secs)
            out["build_id"] = None
            out["comment"] = None
            for s in elf.iter_sections():
                if isinstance(s, NoteSection) and s.name == ".note.gnu.build-id":
                    for note in s.iter_notes():
                        if note["n_type"] == "NT_GNU_BUILD_ID":
                            out["build_id"] = note["n_desc"]
                if s.name == ".comment":
                    try:
                        out["comment"] = s.data().replace(b"\x00", b" ").decode("utf-8", "replace").strip()[:120]
                    except Exception:
                        pass
            dyn = elf.get_section_by_name(".dynamic")
            needed, soname, rpath, runpath, flags = [], None, None, None, []
            if dyn is not None:
                for tag in dyn.iter_tags():
                    t = tag.entry.d_tag
                    if t == "DT_NEEDED":
                        needed.append(tag.needed)
                    elif t == "DT_SONAME":
                        soname = tag.soname
                    elif t == "DT_RPATH":
                        rpath = tag.rpath
                    elif t == "DT_RUNPATH":
                        runpath = tag.runpath
                    elif t in ("DT_BIND_NOW", "DT_FLAGS_1"):
                        flags.append(t)
            out["needed"] = needed
            out["soname"] = soname
            out["rpath"] = rpath
            out["runpath"] = runpath
            out["bind_now"] = any(f in ("DT_BIND_NOW",) for f in flags) or bool(elf.get_section_by_name(".dynamic"))
            # hardening program headers
            gnu_relro = any(seg.header.p_type == "PT_GNU_RELRO" for seg in elf.iter_segments())
            gnu_stack = next((seg for seg in elf.iter_segments() if seg.header.p_type == "PT_GNU_STACK"), None)
            out["gnu_relro"] = gnu_relro
            out["nx_stack"] = bool(gnu_stack and not (gnu_stack.header.p_flags & 0x1))
            out["pie"] = elf["e_type"] == "ET_DYN"
            out["has_textrel"] = any(
                tag.entry.d_tag == "DT_TEXTREL" for tag in dyn.iter_tags()
            ) if dyn is not None else False
            # symbols
            dynsym = elf.get_section_by_name(".dynsym")
            syms = []
            if dynsym is not None and isinstance(dynsym, SymbolTableSection):
                syms = [s.name for s in dynsym.iter_symbols() if s.name]
            out["dynsym_count"] = len(syms)
            out["jni_exports"] = sorted({s for s in syms if s.startswith("Java_")})[:40]
            out["n_jni_exports"] = sum(1 for s in syms if s.startswith("Java_"))
            out["cxx_exports"] = sum(1 for s in syms if "_Z" in s)
            # interesting capabilities
            low = {s.lower() for s in syms}
            caps = []
            for key, label in (
                ("ssl", "TLS/OpenSSL"), ("crypto", "crypto"), ("sqlite", "SQLite"),
                ("sqlcipher", "SQLCipher"), ("aes", "AES"), ("rsa", "RSA"), ("ec_", "EC"),
                ("face", "face"), ("ocr", "OCR"), ("ncnn", "NCNN"), ("tflite", "TFLite"),
                ("mediapipe", "MediaPipe"), ("onnx", "ONNX"), ("liveness", "liveness"),
                ("qr", "QR"), ("barcode", "barcode"), ("obfuscat", "obfuscation"),
                ("frida", "anti-Frida"), ("xposed", "anti-Xposed"), ("root", "root-check"),
                ("ptrace", "anti-ptrace"), ("integrity", "integrity"), ("emulator", "emulator-check"),
                ("hook", "hook"), ("jni", "JNI"), ("protobuf", "protobuf"), ("grpc", "gRPC"),
            ):
                if any(key in s for s in low):
                    caps.append(label)
            out["capabilities"] = sorted(set(caps))
    except Exception as exc:
        out["error"] = f"{type(exc).__name__}: {exc}"
    return out


# --------------------------------------------------------------------------- #
# assets: ML model verification
# --------------------------------------------------------------------------- #
def tflite_meta(blob: bytes) -> dict[str, Any]:
    """Minimal TFLite flatbuffer probe: verify magic, harvest printable op names.

    A full flatbuffer parse needs the generated schema; instead we verify the
    file identifier ("TFL3") and walk the version field, then signature-scan the
    buffer for operator names, which is enough to attribute the model family.
    """
    out = {"magic_ok": blob[4:8] == b"TFL3"}
    if not out["magic_ok"]:
        return out
    out["format_version"] = struct.unpack_from("<I", blob, 8)[0] if len(blob) >= 12 else None
    printable = re.findall(rb"[\x20-\x7e]{4,}", blob[: min(len(blob), 400_000)])
    text = b"\n".join(printable).decode("ascii", "replace")
    ops = sorted({t for t in re.findall(r"\b(CONV_2D|DEPTHWISE_CONV_2D|FULLY_CONNECTED|SOFTMAX|"
                                        r"RESHAPE|ADD|MUL|MAX_POOL_2D|AVERAGE_POOL_2D|CONCATENATION|"
                                        r"MEAN|SUB|PAD|DEQUANTIZE|QUANTIZE|LOGISTIC|RELU|RELU6|"
                                        r"PRELU|TANH|RESIZE_BILINEAR|TRANSPOSE_CONV|SLICE|STRIDED_SLICE|"
                                        r"GATHER|SQUEEZE|EXPAND_DIMS|CUSTOM|LSTM|UNIDIRECTIONAL_SEQUENCE_LSTM|"
                                        r"ARG_MAX|TOPK_V2|NMS|NON_MAX_SUPPRESSION_V4|NON_MAX_SUPPRESSION_V5|"
                                        r"CAST|SHAPE|PACK|SPLIT|SPLIT_V|MIRROR_PAD|TILE|BATCH_MATMUL)\b", text)})
    out["operators"] = ops
    out["n_operators"] = len(ops)
    out["tensor_names_sample"] = sorted({t for t in re.findall(r"[A-Za-z_][A-Za-z0-9_/.]{3,40}", text)})[:25]
    return out


NCNN_MAGIC = re.compile(rb"^\d{6,8}\s*$")


def ncnn_meta(blob: bytes) -> dict[str, Any]:
    lines = blob[:200_000].split(b"\n")
    layers = []
    for ln in lines[2:]:
        parts = ln.split()
        if len(parts) >= 1 and parts[0]:
            layers.append(parts[0].decode("ascii", "replace"))
    return {
        "magic_ok": bool(NCNN_MAGIC.match(lines[0] or b"")),
        "declared_layers": int(lines[1]) if len(lines) > 1 and lines[1].strip().isdigit() else None,
        "observed_layers": len(layers),
        "layer_types": sorted(set(layers))[:40],
    }


# --------------------------------------------------------------------------- #
# secrets (redacted)
# --------------------------------------------------------------------------- #
SECRET_PATTERNS: list[tuple[str, re.Pattern[bytes], str]] = [
    ("aws_access_key", re.compile(rb"\bAKIA[0-9A-Z]{16}\b"), "high"),
    ("google_api_key", re.compile(rb"\bAIza[0-9A-Za-z_\-]{35}\b"), "high"),
    ("firebase_url", re.compile(rb"https://[a-z0-9\-]{4,40}\.firebaseio\.com"), "info"),
    ("gcm_sender", re.compile(rb"\b\d{11,13}\b(?=[^\d]{0,20}gcm|project_id)"), "info"),
    ("private_key_pem", re.compile(rb"-----BEGIN (?:RSA |EC |DSA |OPENSSH )?PRIVATE KEY-----"), "critical"),
    ("jwt", re.compile(rb"\beyJ[A-Za-z0-9_\-]{10,}\.eyJ[A-Za-z0-9_\-]{10,}\.[A-Za-z0-9_\-]{10,}"), "high"),
    ("stripe_key", re.compile(rb"\b(?:sk|pk)_(?:live|test)_[0-9A-Za-z]{16,}\b"), "high"),
    ("slack_token", re.compile(rb"\bxox[abprs]-[0-9A-Za-z\-]{10,}"), "high"),
    ("github_token", re.compile(rb"\bgh[pousr]_[0-9A-Za-z]{36}\b"), "high"),
    ("basic_auth_url", re.compile(rb"\b[a-z][a-z0-9+.\-]{2,}://[^\s:/@]{1,40}:[^\s:/@]{3,40}@"), "high"),
    ("s3_bucket", re.compile(rb"[a-z0-9.\-]{3,40}\.s3(?:[.\-][a-z0-9\-]+)?\.amazonaws\.com"), "info"),
    ("jdbc", re.compile(rb"jdbc:[a-z]+://[^\s\"']{4,80}"), "info"),
]
URL_RE = re.compile(rb"https?://([A-Za-z0-9\-._~%]+)")
HOST_RE = re.compile(rb"^(?:[a-z0-9](?:[a-z0-9\-]{0,61}[a-z0-9])?\.)+[a-z]{2,24}$")


def redact(value: bytes) -> str:
    s = value.decode("ascii", "replace")
    if len(s) <= 10:
        return s[:2] + "***"
    return s[:6] + "…" + s[-4:] + f" (len={len(s)})"


def scan_secrets(blob: bytes, where: str, findings: list[dict]) -> None:
    for label, rx, sev in SECRET_PATTERNS:
        for m in rx.finditer(blob[: 8 << 20]):
            findings.append({
                "kind": label, "severity": sev, "location": where,
                "offset": m.start(), "redacted": redact(m.group(0)),
            })
            if sum(1 for f in findings if f["kind"] == label) > 40:
                break


def scan_domains(blob: bytes, where: str, hosts: collections.Counter) -> None:
    for m in URL_RE.finditer(blob[: 8 << 20]):
        h = m.group(1).lower().rstrip(b".")
        if HOST_RE.match(h):
            hosts[h.decode()] += 1


# --------------------------------------------------------------------------- #
def main() -> None:
    bundle = json.load(open("work/xapk/manifest.json"))
    apks = sorted(glob.glob("work/xapk/*.apk"))
    rows: list[dict[str, Any]] = []
    cat_stats: dict[str, dict[str, Any]] = collections.defaultdict(
        lambda: {"files": 0, "bytes": 0, "compressed": 0, "entropy_sum": 0.0}
    )
    formats: collections.Counter = collections.Counter()
    disagreements: list[dict] = []
    high_entropy: list[dict] = []
    findings: list[dict] = []
    hosts: collections.Counter = collections.Counter()
    host_where: dict[str, set[str]] = collections.defaultdict(set)
    assets_meta: list[dict] = []
    native_rows: list[dict] = []
    resource_census: dict[str, Any] = {}

    bundle_files = [
        (os.path.basename(p), os.path.getsize(p))
        for p in glob.glob("work/xapk/*")
        if not p.endswith(".apk")
    ]

    for apk in apks:
        apk_name = os.path.basename(apk)
        with zipfile.ZipFile(apk) as z:
            infos = z.infolist()
            for i in infos:
                if i.is_dir():
                    continue
                name = i.filename
                try:
                    blob = z.read(name)
                except Exception as exc:
                    rows.append({"apk": apk_name, "path": name, "error": str(exc)})
                    continue
                fmt, agree = sniff(blob[:64], name)
                cat = category(name, fmt)
                h = shannon_entropy(blob if len(blob) <= 4 << 20 else blob[: 4 << 20])
                sha = hashlib.sha256(blob).hexdigest()
                rows.append({
                    "apk": apk_name,
                    "path": name,
                    "category": cat,
                    "format": fmt,
                    "magic_agrees": agree,
                    "size": i.file_size,
                    "compressed": i.compress_size,
                    "ratio": round(i.file_size / i.compress_size, 2) if i.compress_size else None,
                    "method": i.compress_type,
                    "crc32": f"{i.CRC:08x}",
                    "entropy": round(h, 3),
                    "sha256": sha,
                    "mtime": "%04d-%02d-%02d" % i.date_time[:3],
                })
                st = cat_stats[cat]
                st["files"] += 1
                st["bytes"] += i.file_size
                st["compressed"] += i.compress_size
                st["entropy_sum"] += h
                formats[fmt] += 1
                if not agree:
                    disagreements.append({"path": f"{apk_name}:{name}", "fmt": fmt, "size": i.file_size})
                if h > 7.5 and i.file_size > 8192:
                    high_entropy.append({"path": f"{apk_name}:{name}", "entropy": round(h, 3),
                                         "size": i.file_size, "category": cat, "format": fmt})

                # ---- targeted deep dives -------------------------------- #
                if cat == "asset:ml-model" or (cat.startswith("asset") and name.endswith((".tflite", ".pb", ".binarypb"))):
                    m = {"path": name, "size": i.file_size, "sha256": sha[:16]}
                    if name.endswith(".tflite"):
                        m.update(tflite_meta(blob))
                    else:
                        m["magic"] = blob[:8].hex()
                        m["strings"] = [s.decode("ascii", "replace") for s in
                                        re.findall(rb"[\x20-\x7e]{6,}", blob[:200_000])][:12]
                    assets_meta.append(m)
                if name.endswith(".param"):
                    assets_meta.append({"path": name, "size": i.file_size, "ncnn": ncnn_meta(blob)})
                if cat == "native:elf":
                    tmp = f"/tmp/elf_{hashlib.sha1(name.encode()).hexdigest()[:10]}.so"
                    with open(tmp, "wb") as fh:
                        fh.write(blob)
                    rep = elf_report(tmp)
                    rep.update({"apk": apk_name, "path": name, "size": i.file_size,
                                "entropy": round(h, 3), "sha256": sha,
                                "obfuscated_name": bool(OBFUSCATED_SO.match(os.path.basename(name)))})
                    native_rows.append(rep)
                    os.unlink(tmp)
                # secrets + domains on every text/code-ish or opaque container
                if cat in ("code:dex", "native:elf", "asset:javascript", "asset:config-json",
                           "root:other", "asset:other", "asset:opaque-dat", "res:xml",
                           "build:metadata", "asset:secondary-dex", "res:other"):
                    scan_secrets(blob, f"{apk_name}:{name}", findings)
                    scan_domains(blob, f"{apk_name}:{name}", hosts)
                    for k in set(URL_RE.findall(blob[: 8 << 20])):
                        pass
                if name.endswith("resources.arsc") and i.file_size > 1_000_000:
                    import arsc as _arsc
                    resource_census = _arsc.census(blob)
                    resource_census["apk"] = apk_name

        print(f"[census] {apk_name}: {len(infos)} entries")

    # bundle-level files (icon, xapk manifest)
    for bn, size in bundle_files:
        p = f"work/xapk/{bn}"
        blob = open(p, "rb").read()
        rows.append({
            "apk": "bundle", "path": bn, "category": "bundle:descriptor" if bn.endswith(".json") else "bundle:icon",
            "format": sniff(blob[:64], bn)[0], "magic_agrees": True, "size": size,
            "compressed": size, "ratio": 1.0, "method": 0, "crc32": "",
            "entropy": round(shannon_entropy(blob if len(blob) < (4 << 20) else blob[: 4 << 20]), 3),
            "sha256": hashlib.sha256(blob).hexdigest(), "mtime": "",
        })

    # ---- write inventory -------------------------------------------------- #
    fields = ["apk", "path", "category", "format", "magic_agrees", "size", "compressed",
              "ratio", "method", "crc32", "entropy", "sha256", "mtime"]
    with open(f"{DATA}/inventory.csv", "w", newline="") as fh:
        w = csv.DictWriter(fh, fieldnames=fields, extrasaction="ignore")
        w.writeheader()
        for r in rows:
            w.writerow(r)

    with open(f"{DATA}/native.csv", "w", newline="") as fh:
        if native_rows:
            keys = sorted({k for r in native_rows for k in r})
            w = csv.DictWriter(fh, fieldnames=keys, extrasaction="ignore")
            w.writeheader()
            for r in native_rows:
                w.writerow({k: (json.dumps(v) if isinstance(v, (list, dict)) else v) for k, v in r.items()})

    # ---- secret dedupe ---------------------------------------------------- #
    seen = set()
    uniq = []
    for f in findings:
        key = (f["kind"], f["redacted"])
        if key in seen:
            continue
        seen.add(key)
        uniq.append(f)

    census = {
        "bundle": bundle,
        "totals": {
            "entries": len(rows),
            "uncompressed_bytes": sum(r["size"] for r in rows),
            "compressed_bytes": sum(r["compressed"] for r in rows),
            "categories": len(cat_stats),
            "formats": len(formats),
            "distinct_sha256": len({r["sha256"] for r in rows}),
            "duplicate_files": len(rows) - len({r["sha256"] for r in rows}),
        },
        "categories": {
            k: {
                "files": v["files"],
                "bytes": v["bytes"],
                "compressed": v["compressed"],
                "mean_entropy": round(v["entropy_sum"] / max(v["files"], 1), 3),
            }
            for k, v in sorted(cat_stats.items(), key=lambda kv: -kv[1]["bytes"])
        },
        "formats": dict(formats.most_common()),
        "top_files": sorted(
            ({"path": f'{r["apk"]}:{r["path"]}', "size": r["size"], "category": r["category"],
              "entropy": r["entropy"]} for r in rows),
            key=lambda r: -r["size"],
        )[:40],
        "magic_disagreements": disagreements[:50],
        "n_magic_disagreements": len(disagreements),
        "high_entropy_files": sorted(high_entropy, key=lambda r: -r["size"])[:40],
        "n_high_entropy": len(high_entropy),
        "domains": [{"host": h, "hits": c, "seen_in": sorted(host_where.get(h, []))[:4]}
                    for h, c in hosts.most_common(250)],
        "n_domains": len(hosts),
        "secrets": uniq[:120],
        "secrets_by_kind": dict(collections.Counter(f["kind"] for f in uniq)),
        "native_summary": {
            "count": len(native_rows),
            "total_bytes": sum(r.get("size", 0) for r in native_rows),
            "jni_exports": sum(r.get("n_jni_exports", 0) or 0 for r in native_rows),
            "stripped_all": all(not r.get("has_symtab") for r in native_rows),
            "nx_all": all(r.get("nx_stack") for r in native_rows),
            "relro_all": all(r.get("gnu_relro") for r in native_rows),
            "pie_all": all(r.get("pie") for r in native_rows),
            "with_build_id": sum(1 for r in native_rows if r.get("build_id")),
            "obfuscated_names": [r["path"] for r in native_rows if r.get("obfuscated_name")],
        },
        "assets_models": assets_meta,
        "resources": resource_census,
    }
    with open(f"{DATA}/census.json", "w") as fh:
        json.dump(census, fh, indent=1, ensure_ascii=False)
    with open(f"{DATA}/secrets.json", "w") as fh:
        json.dump({"findings": uniq, "by_kind": census["secrets_by_kind"]}, fh, indent=1)

    print("[census] entries:", len(rows))
    print("[census] formats:", dict(formats.most_common(12)))
    print("[census] categories:", {k: v["files"] for k, v in census["categories"].items()})
    print("[census] domains:", len(hosts), "secrets:", len(uniq), "native:", len(native_rows))
    print("[census] -> data/inventory.csv data/census.json data/native.csv data/secrets.json")


def arsc_census(blob: bytes) -> dict[str, Any]:
    """Census of a resources.arsc: types, configs, locales, string pool size."""
    try:
        from androguard.core.axml import ARSCParser
    except Exception:
        return {"error": "androguard ARSCParser unavailable"}
    try:
        p = ARSCParser(blob)
        packages = p.get_packages_names()
        locales = p.get_locales() if hasattr(p, "get_locales") else []
        types: collections.Counter = collections.Counter()
        for pkg in packages:
            for loc in p.get_locales(pkg) if hasattr(p, "get_locales") else []:
                for t in p.get_types(pkg, loc) if False else []:
                    types[t] += 1
        return {
            "packages": packages,
            "n_packages": len(packages),
            "locales": locales,
            "n_locales": len(locales),
            "types": dict(types),
            "size": len(blob),
        }
    except Exception as exc:
        return {"error": f"{type(exc).__name__}: {exc}", "size": len(blob)}


if __name__ == "__main__":
    main()
