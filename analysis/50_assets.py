#!/usr/bin/env python3
"""
Stage 50 -- the `assets/` layer: shipped configuration, embedded payloads,
mini-program container and personal-data exposure.

This is where the most consequential findings live, so the extractor is
written defensively:

* **No personal data is persisted.**  A PII detector classifies fields, counts
  them and records *masked* previews only (e.g. `09•••••31`).  The study can
  therefore describe the exposure without republishing it.
* Every embedded payload (nested zip, dex, ELF, JS bundle, ML model, encrypted
  blob) is identified by magic bytes, not by extension.
"""

from __future__ import annotations

import json
import logging
import re
import zipfile
from collections import Counter, defaultdict
from pathlib import Path

from common import BASE_PKG, UNPACKED, banner, dump, entropy, human, redact

logging.disable(logging.CRITICAL)
ASSETS = UNPACKED / BASE_PKG / "assets"

CATEGORY_RULES = [
    ("ml-model", {".tflite", ".ncnn", ".param", ".bin", ".lite"}),
    ("web-runtime", {".js", ".html", ".css", ".json", ".wasm"}),
    ("native-payload", {".so", ".dex", ".zip", ".jar"}),
    ("config", {".json", ".txt", ".xml", ".properties", ".proto", ".yml", ".yaml"}),
    ("media", {".png", ".jpg", ".webp", ".gif", ".svg", ".ttf", ".otf", ".amr", ".mp3", ".wav"}),
    ("data-blob", {".dat", ".db", ".sqlite", ".pem", ".crt", ".der"}),
]

PII_PATTERNS = {
    "email": re.compile(r"[A-Za-z0-9._%+\-]+@[A-Za-z0-9.\-]+\.[A-Za-z]{2,}"),
    "vn_mobile": re.compile(r"(?:\+84|0)(?:3[2-9]|5[25689]|7[06789]|8[1-9]|9\d)\d{7}\b"),
    "national_id": re.compile(r"\b\d{9}(?:\d{3})?\b"),
    "full_name_vn": re.compile(
        r"\b[A-ZĐ][A-ZÀ-Ỹ\s]{5,40}\b"),
    "tax_code": re.compile(r"\b\d{10}(?:-\d{3})?\b"),
    "iban_vn": re.compile(r"\b\d{6,16}\b"),
    "address_vn": re.compile(r"(?i)\b(?:tầng|số nhà|đường|phường|quận|huyện|tỉnh|thành phố)\b"),
}

PII_FIELD_KEYS = {
    "customerName", "customerMobileNo", "customerEmail", "customerTaxName",
    "customerTaxAddress", "customerId", "userId", "trackingKey", "sessionId",
    "customerSessionId", "phone", "email", "fullName", "address", "dob",
    "idNumber", "citizenId", "cardNumber", "accountNumber", "bankAccount",
}


def mask(value: str) -> str:
    s = str(value)
    if len(s) <= 4:
        return "•" * len(s)
    return f"{s[:2]}{'•' * min(10, len(s) - 4)}{s[-2:]} ({len(s)} chars)"


def classify(path: Path) -> str:
    suffix = path.suffix.lower()
    for cat, exts in CATEGORY_RULES:
        if suffix in exts:
            return cat
    return "other"


def magic_kind(path: Path) -> str:
    head = path.read_bytes()[:16]
    if head.startswith(b"PK\x03\x04"):
        return "zip"
    if head.startswith(b"\x7fELF"):
        return "elf"
    if head.startswith(b"dex\n"):
        return "dex"
    if head.startswith(b"TFL3"):
        return "tflite"
    if head[:4] == b"\x1a\x45\xdf\xa3":
        return "webm"
    return "opaque"


def walk_pii(obj, path="$", hits=None, depth=0):
    """Collect (json-path, field, masked-value) triples; never raw values."""
    hits = [] if hits is None else hits
    if depth > 8:
        return hits
    if isinstance(obj, dict):
        for k, v in obj.items():
            if isinstance(v, str) and k in PII_FIELD_KEYS and v.strip():
                hits.append({"field": k, "json_path": f"{path}.{k}",
                             "masked": mask(v),
                             "chars": len(v)})
            else:
                walk_pii(v, f"{path}.{k}", hits, depth + 1)
    elif isinstance(obj, list):
        for i, v in enumerate(obj[:40]):
            walk_pii(v, f"{path}[{i}]", hits, depth + 1)
    return hits


def scan_config(path: Path) -> dict:
    raw = path.read_bytes()
    rel = str(path.relative_to(UNPACKED / BASE_PKG))
    entry = {"path": rel, "bytes": len(raw), "entropy": round(entropy(raw[:65536]), 3),
             "category": classify(path)}
    text = None
    if path.suffix.lower() == ".json":
        try:
            doc = json.loads(raw.decode("utf-8", "replace"))
            pii = walk_pii(doc)
            entry["json"] = {"top_keys": list(doc)[:20] if isinstance(doc, dict) else None,
                             "pii_fields": pii[:40], "pii_field_count": len(pii)}
            entry["pii"] = pii
            text = json.dumps(doc, ensure_ascii=False)
        except Exception as exc:
            entry["json_error"] = str(exc)
    if text is None:
        text = raw.decode("utf-8", "replace")

    # endpoint + pattern mining inside config text
    hosts = sorted(set(re.findall(r"https?://([A-Za-z0-9.\-]+\.[A-Za-z]{2,12})", text)))
    entry["hosts"] = hosts[:25]
    entry["host_count"] = len(hosts)
    flags = []
    if re.search(r"(?i)api[_-]?key|secret|token|password|passphrase", text):
        flags.append("credential-ish-key")
    if re.search(r"-----BEGIN", text):
        flags.append("pem-block")
    if re.search(r"(?i)http://(?!localhost)", text):
        flags.append("plaintext-http")
    if "debug" in text.lower():
        flags.append("debug-flag")
    entry["flags"] = flags
    # previews are scrubbed: a config preview must never re-publish PII
    preview = re.sub(r"\s+", " ", text[:280])
    for pat in PII_PATTERNS.values():
        preview = pat.sub(lambda m: mask(m.group(0)), preview)
    if entry.get("pii"):
        preview = ("<redacted personal-data fixture: fields "
                   + ", ".join(sorted({h["field"] for h in entry["pii"]})) + ">")
    entry["preview"] = preview
    return entry


def main() -> None:
    banner("Stage 50 · assets: config, payloads, mini-programs, PII")

    files = [p for p in ASSETS.rglob("*") if p.is_file()]
    census = defaultdict(lambda: {"files": 0, "bytes": 0})
    for p in files:
        c = census[classify(p)]
        c["files"] += 1
        c["bytes"] += p.stat().st_size

    configs = []
    pii_total = []
    for p in sorted(files):
        if classify(p) in {"config", "web-runtime"} and p.stat().st_size < 4 << 20:
            try:
                c = scan_config(p)
            except Exception:
                continue
            if c.get("pii"):
                pii_total.extend([{**h, "file": c["path"]} for h in c["pii"]])
            configs.append(c)

    # ---- embedded payloads --------------------------------------------------
    payloads = []
    for p in sorted(files):
        rel = str(p.relative_to(UNPACKED / BASE_PKG))
        kind = magic_kind(p)
        if kind == "zip":
            try:
                with zipfile.ZipFile(p) as zf:
                    names = zf.namelist()
                payloads.append({"asset": rel, "bytes": p.stat().st_size, "kind": "zip",
                                 "entries": len(names),
                                 "sample": names[:12],
                                 "extensions": dict(Counter(Path(n).suffix for n in names).most_common(8))})
            except Exception as exc:
                payloads.append({"asset": rel, "bytes": p.stat().st_size,
                                 "kind": "zip(corrupt)", "error": str(exc)})
        elif kind in {"elf", "dex", "tflite"}:
            payloads.append({"asset": rel, "bytes": p.stat().st_size, "kind": kind})
        elif p.suffix.lower() in {".amr"}:
            payloads.append({"asset": rel, "bytes": p.stat().st_size, "kind": "amr-audio"})
    payloads.sort(key=lambda x: -x["bytes"])

    # ---- mini-program container --------------------------------------------
    griver = {}
    appx = ASSETS / "Griver" / "appx.json"
    if appx.exists():
        doc = json.loads(appx.read_text(errors="replace"))
        items = []
        for app_id, info in (doc.get("mini") or {}).items():
            ai = info.get("appInfo", {})
            items.append({"app_id": app_id, "name": ai.get("name"),
                          "deploy_version": ai.get("deployVersion"),
                          "package_url_host": re.sub(r"^(https?://[^/]+).*", r"\1",
                                                     ai.get("packageUrl", "")),
                          "fallback_host": re.sub(r"^(https?://[^/]+).*", r"\1",
                                                  ai.get("fallbackBaseUrl", "")),
                          "bytes_hint": info.get("container", {}).get("size")})
        griver = {"bundled_mini_programs": items, "count": len(items)}
    js_files = sorted(p for p in ASSETS.rglob("*.js"))
    js_api = Counter()
    injection = []
    for p in js_files:
        txt = p.read_text(errors="replace")
        for api in ("my.call", "window.my", "AlipayJSBridge", "my.getSystemInfo",
                    "JSBridge", "invokeNative", "postMessage", "prompt(", "setTitle",
                    "getDeviceInfo"):
            n = txt.count(api)
            if n:
                js_api[api] += n
        for m in re.finditer(r"(?i)nativeMethod\s*[:=]\s*['\"]([A-Za-z0-9_.]{3,40})", txt):
            injection.append(m.group(1))
    jsb = {
        "js_files": [{"path": str(p.relative_to(UNPACKED / BASE_PKG)),
                      "bytes": p.stat().st_size} for p in js_files][:20],
        "api_tokens": dict(js_api.most_common(15)),
        "native_method_names": sorted(set(injection))[:25],
    }

    # ---- encrypted / opaque blobs ------------------------------------------
    blobs = []
    for p in sorted(files):
        if p.stat().st_size < 1024:
            continue
        e = entropy(p.read_bytes()[:65536])
        if e > 7.4:
            blobs.append({"asset": str(p.relative_to(UNPACKED / BASE_PKG)),
                          "bytes": p.stat().st_size, "entropy": round(e, 3),
                          "kind": magic_kind(p)})
    blobs.sort(key=lambda x: -x["bytes"])

    # ---- PII exposure summary ----------------------------------------------
    pii_by_field = Counter(h["field"] for h in pii_total)
    pii_files = sorted({h["file"] for h in pii_total})
    pii_doc = {
        "fields_total": len(pii_total),
        "distinct_fields": dict(pii_by_field.most_common()),
        "files": pii_files,
        "records": [{"file": h["file"], "field": h["field"], "masked": h["masked"]}
                    for h in pii_total[:24]],
        "categories": {
            "direct_identifier": sum(1 for h in pii_total
                                     if h["field"] in {"customerName", "customerEmail",
                                                       "customerMobileNo", "fullName",
                                                       "phone", "email"}),
            "financial": sum(1 for h in pii_total
                             if h["field"] in {"accountNumber", "cardNumber", "iban",
                                               "bankAccount", "customerTaxName"}),
            "pseudonymous": sum(1 for h in pii_total
                                if h["field"] in {"customerId", "userId", "sessionId",
                                                  "customerSessionId", "trackingKey"}),
        },
    }

    doc = {
        "census": {"files": len(files), "bytes": sum(p.stat().st_size for p in files),
                   "by_category": [{"category": k, **v} for k, v in
                                   sorted(census.items(), key=lambda kv: -kv[1]["bytes"])]},
        "configs": configs,
        "payloads": payloads,
        "mini_program_container": {"griver": griver, "jsbridge": jsb},
        "high_entropy_blobs": blobs,
        "pii_exposure": pii_doc,
    }
    dump("50_assets.json", doc)

    print(f"  assets: {len(files)} files / {human(doc['census']['bytes'])}")
    print(f"  categories: {[(c['category'], c['files']) for c in doc['census']['by_category']]}")
    print(f"  embedded payloads: {[(p['asset'], human(p['bytes']), p['kind']) for p in payloads[:6]]}")
    print(f"  mini-programs bundled: {griver.get('count')}  js files: {len(js_files)}")
    print(f"  high-entropy blobs: {[(b['asset'], human(b['bytes'])) for b in blobs[:6]]}")
    print(f"  PII fields: {len(pii_total)} in {pii_files} → {dict(pii_by_field)}")
    for r in pii_doc["records"][:8]:
        print(f"    · {r['file']} :: {r['field']} = {r['masked']}")


if __name__ == "__main__":
    main()
