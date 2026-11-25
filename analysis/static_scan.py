#!/usr/bin/env python3
"""Stage 2: static scan of each APK split (stdlib always; androguard if installed).
Outputs: reports/static_scan.json, reports/file_inventory.csv
Safety: strings are triaged into COUNTS + redacted domain/package aggregates only."""
import csv, hashlib, json, pathlib, re, sys, zipfile

ROOT = pathlib.Path(__file__).resolve().parent.parent
OUT = ROOT / "extracted"
REPORTS = ROOT / "reports"

try:
    from androguard.core.apk import APK  # type: ignore
    HAVE_AG = True
    try:
        from loguru import logger as _lg
        _lg.remove()
    except Exception:
        pass
except Exception as e:
    print(f"[warn] androguard unavailable ({e}); manifest/dex deep-parse will be skipped")
    HAVE_AG = False

def _safe(fn, default=None):
    try:
        return fn()
    except Exception:
        return default

def _manifest_text(a) -> str:
    try:
        x = a.get_android_manifest_xml()
    except Exception:
        return ""
    try:
        b = x.get_buff()
        if isinstance(b, (bytes, bytearray)):
            return bytes(b).decode("utf-8", "ignore")
    except Exception:
        pass
    try:
        return str(x)
    except Exception:
        return ""

def _manifest_flags(a) -> dict:
    mt = _manifest_text(a)
    m = re.search(r'extractNativeLibs\s*=\s*"(true|false)"', mt)
    return {
        "debuggable": bool(re.search(r'debuggable\s*=\s*"true"', mt)),
        "allow_backup": not bool(re.search(r'allowBackup\s*=\s*"false"', mt)),
        "uses_cleartext_traffic": bool(re.search(r'usesCleartextTraffic\s*=\s*"true"', mt)),
        "network_security_config": bool(re.search(r'networkSecurityConfig', mt)),
        "extract_native_libs": (m.group(1) if m else None),
        "request_legacy_storage": bool(re.search(r'requestLegacyExternalStorage\s*=\s*"true"', mt)),
    }

def _name_str(n) -> str:
    try:
        nat = n.native  # asn1crypto -> dict like {'common_name': ...}
        if isinstance(nat, dict):
            return ", ".join(f"{k}={v}" for k, v in nat.items())[:300]
    except Exception:
        pass
    try:
        return str(n)[:300]
    except Exception:
        return "?"

def _certs(a) -> list:
    ders: list = []
    try:
        for c in (a.get_certificates() or []):
            try:
                d = c.dump() if hasattr(c, "dump") else bytes(c)
                ders.append((bytes(d), _name_str(getattr(c, "subject", "?")), _name_str(getattr(c, "issuer", "?"))))
            except Exception:
                pass
    except Exception:
        pass
    if not ders:
        for meth in ("get_certificates_der_v2", "get_certificates_der_v3"):
            try:
                for d in (getattr(a, meth)() or []):
                    ders.append((bytes(d), "?", "?"))
            except Exception:
                pass
    return [{"sha256": hashlib.sha256(d).hexdigest(), "subject": s, "issuer": i} for d, s, i in ders]

ASCII_RE = re.compile(rb"[ -~]{6,}")
URL_RE = re.compile(rb"https?://[^\s\"'<>\\^`{|}]+")
DOMAIN_RE = re.compile(r"^https?://([^/:?#]+)")
SECRET_PATTERNS = [re.compile(p, re.I) for p in
    [rb"api[_-]?key", rb"secret", rb"passwd|password", rb"private[_-]?key",
     rb"access[_-]?token", rb"auth[_-]?token", rb"bearer ", rb"BEGIN [A-Z ]*PRIVATE KEY"]]

def sha256_bytes(b: bytes) -> str:
    return hashlib.sha256(b).hexdigest()

def elf_info(data: bytes) -> dict:
    if len(data) < 64 or data[:4] != b"\x7fELF":
        return {"elf": False}
    ei_class = {1: "ELF32", 2: "ELF64"}.get(data[4], f"class-{data[4]}")
    ei_data = {1: "LE", 2: "BE"}.get(data[5], f"data-{data[5]}")
    import struct
    endian = "<" if data[5] == 1 else ">"
    e_machine = struct.unpack(endian + "H", data[18:20])[0]
    e_type = struct.unpack(endian + "H", data[16:18])[0]
    mach = {3: "x86", 40: "armeabi", 62: "x86_64", 183: "aarch64"}.get(e_machine, str(e_machine))
    return {"elf": True, "class": ei_class, "endian": ei_data, "machine": mach,
            "type": {2: "EXEC", 3: "DYN"}.get(e_type, str(e_type))}

def triage_strings(blob: bytes, cap_strings: int = 400000) -> dict:
    strs = ASCII_RE.findall(blob)
    total = len(strs)
    if len(strs) > cap_strings:
        strs = strs[:cap_strings]
    urls = URL_RE.findall(blob[:50_000_000])
    domains: dict[str, int] = {}
    ok = re.compile(r"^[a-z0-9]([a-z0-9\-\.]*[a-z0-9])?$")
    for u in urls[:20000]:
        try:
            m = DOMAIN_RE.match(u.decode("ascii", "ignore").rstrip(",);:'\"!?."))
            if not m:
                continue
            d = m.group(1).lower().rstrip(".")
            if "*" in d or " " in d or "\x00" in d or "." not in d or not ok.match(d):
                continue
            # keep only last 2 labels to avoid leaking host-specific paths
            parts = d.split(".")
            tail = ".".join(parts[-2:])
            domains[tail] = domains.get(tail, 0) + 1
        except Exception:
            pass
    pkgs: dict[str, int] = {}
    for s in strs:
        if s.startswith((b"com.", b"io.", b"org.", b"vn.", b"android.", b"androidx.", b"kotlin.", b"okhttp3.", b"retrofit2.")) and b"/" not in s and len(s) < 120:
            try:
                t = s.decode("ascii")
                # class-name or package: keep first 3 labels
                pre = t.split(";")[0].split("$")[0]
                labels = pre.split(".")
                key = ".".join(labels[:3])
                pkgs[key] = pkgs.get(key, 0) + 1
            except Exception:
                pass
    top_pkgs = dict(sorted(pkgs.items(), key=lambda kv: -kv[1])[:60])
    crypto_kw = {}
    low = blob[:50_000_000].lower()
    for kw in [b"aes", b"rsa", b"hmac", b"sha256", b"ssl", b"pinning", b"keystore", b"pbkdf2", b"oauth"]:
        crypto_kw[kw.decode()] = low.count(kw)
    secret_hits = {}
    for p in SECRET_PATTERNS:
        c = len(p.findall(blob[:50_000_000]))
        if c:
            secret_hits[p.pattern.decode(errors="ignore")] = c
    return {"ascii_strings_total": total, "ascii_strings_scanned": len(strs),
            "url_count": len(urls), "domains_top": dict(sorted(domains.items(), key=lambda kv: -kv[1])[:40]),
            "package_prefix_top": top_pkgs, "crypto_keyword_counts": crypto_kw,
            "sensitive_pattern_counts": secret_hits,
            "note": "Values redacted by design; only aggregate counts are reported."}

def scan_apk(apk_path: pathlib.Path) -> dict:
    raw = apk_path.read_bytes()
    rec = {"file": apk_path.name, "bytes": len(raw), "sha256": sha256_bytes(raw)}
    z = zipfile.ZipFile(__import__("io").BytesIO(raw))
    infos = z.infolist()
    rec["zip_entries"] = len(infos)
    rec["uncompressed_bytes"] = sum(i.file_size for i in infos)
    ext: dict[str, int] = {}
    ext_bytes: dict[str, int] = {}
    top: dict[str, int] = {}
    dex, native, metainf, assets, res = [], [], [], [], []
    rows = []
    for i in infos:
        name = i.filename
        suffix = name.rsplit(".", 1)[-1].lower() if "." in name.rsplit("/", 1)[-1] else "(none)"
        ext[suffix] = ext.get(suffix, 0) + 1
        ext_bytes[suffix] = ext_bytes.get(suffix, 0) + i.file_size
        top[name.split("/")[0]] = top.get(name.split("/")[0], 0) + 1
        rows.append((apk_path.name, name, i.file_size, i.compress_size))
        if name.startswith("classes") and name.endswith(".dex"):
            dex.append({"name": name, "bytes": i.file_size})
        elif name.startswith("lib/") and name.endswith(".so"):
            parts = name.split("/")
            native.append({"name": name, "abi": parts[1] if len(parts) > 2 else "?", "bytes": i.file_size})
        elif name.startswith("META-INF/"):
            metainf.append({"name": name, "bytes": i.file_size})
        elif name.startswith("assets/"):
            assets.append(i.file_size)
        elif name.startswith("res/"):
            res.append(i.file_size)
    rec["ext_counts"] = dict(sorted(ext.items(), key=lambda kv: -kv[1])[:40])
    rec["ext_bytes_top"] = dict(sorted(ext_bytes.items(), key=lambda kv: -kv[1])[:20])
    rec["toplevel_dirs"] = top
    rec["dex_files"] = sorted(dex, key=lambda d: -d["bytes"])
    rec["dex_total_bytes"] = sum(d["bytes"] for d in dex)
    rec["native_libs"] = sorted(native, key=lambda d: -d["bytes"])
    rec["native_total_bytes"] = sum(d["bytes"] for d in native)
    rec["native_abis"] = sorted({d["abi"] for d in native})
    rec["metainf"] = metainf
    rec["assets_count"] = len(assets)
    rec["assets_bytes"] = sum(assets)
    rec["res_count"] = len(res)
    rec["res_bytes"] = sum(res)
    for cand in ("resources.arsc", "AndroidManifest.xml"):
        try:
            rec[cand + "_bytes"] = z.getinfo(cand).file_size
        except KeyError:
            rec[cand + "_bytes"] = None
    # ELF headers for .so (small count, safe)
    for n in rec["native_libs"][:40]:
        try:
            n.update(elf_info(z.read(n["name"])[:4096]))
        except Exception:
            pass
    # DEX header quick-parse (magic, file_size, header_size, endian) per file
    import struct
    dex_hdrs = []
    for d in rec["dex_files"]:
        try:
            b = z.read(d["name"])[:112]
            magic = b[0:8]
            file_size, header_size = struct.unpack("<II", b[32:40])
            endian_tag = b[40:44].hex()
            # sizes live at 0x38/0x40/0x48/0x50/0x58/0x60 (offsets interleaved between size/off pairs)
            sids, tids, pids, fids, mids, cdefs = (
                struct.unpack("<I", b[o:o + 4])[0] for o in (56, 64, 72, 80, 88, 96))
            dex_hdrs.append({"name": d["name"], "magic": magic.decode("ascii", "replace"),
                             "header_file_size": file_size, "header_size": header_size,
                             "endian_tag": endian_tag, "size_match": file_size == d["bytes"],
                             "string_ids": sids, "type_ids": tids, "proto_ids": pids,
                             "field_ids": fids, "method_ids": mids, "class_defs": cdefs})
        except Exception as e:
            dex_hdrs.append({"name": d["name"], "error": str(e)})
    rec["dex_headers"] = dex_hdrs
    # Strings triage over concatenated DEX (bounded)
    try:
        blob = b"".join(z.read(d["name"]) for d in rec["dex_files"])
        rec["strings"] = triage_strings(blob)
    except Exception as e:
        rec["strings"] = {"error": str(e)}
    # Androguard deep parse (field-by-field, fully defensive across 4.x API variants)
    if HAVE_AG:
        try:
            a = APK(str(apk_path))
            flags = _manifest_flags(a)
            rec["ag"] = {
                "package": _safe(a.get_package), "version_name": _safe(a.get_androidversion_name),
                "version_code": _safe(a.get_androidversion_code),
                "min_sdk": _safe(a.get_min_sdk_version), "target_sdk": _safe(a.get_target_sdk_version),
                "max_sdk": _safe(a.get_max_sdk_version),
                "permissions": sorted(set(_safe(a.get_permissions, []) or [])),
                "activities": _safe(a.get_activities, []) or [], "services": _safe(a.get_services, []) or [],
                "receivers": _safe(a.get_receivers, []) or [], "providers": _safe(a.get_providers, []) or [],
                "main_activity": _safe(a.get_main_activity),
                "app_label": _safe(a.get_app_name),
                "debuggable": flags["debuggable"], "allow_backup": flags["allow_backup"],
                "manifest_flags": flags,
                "certificates": _certs(a),
            }
        except Exception as e:
            rec["ag_error"] = str(e)
    else:
        rec["ag_error"] = "androguard not installed"
    return rec, rows

def main() -> None:
    REPORTS.mkdir(exist_ok=True)
    apks = sorted(OUT.glob("*.apk"))
    if not apks:
        print("No APKs in extracted/ — run extract.py first", file=sys.stderr); sys.exit(1)
    out = {"have_androguard": HAVE_AG, "apks": []}
    all_rows = [("apk", "entry", "size", "compressed")]
    for p in apks:
        print(f"Scanning {p.name} ({p.stat().st_size/1024/1024:.1f} MB)...")
        rec, rows = scan_apk(p)
        out["apks"].append(rec)
        all_rows += rows
    (REPORTS / "static_scan.json").write_text(json.dumps(out, indent=2, ensure_ascii=False)[:50_000_000])
    with open(REPORTS / "file_inventory.csv", "w", newline="") as f:
        csv.writer(f).writerows(all_rows)
    print(f"Wrote static_scan.json ({(REPORTS/'static_scan.json').stat().st_size/1024:.0f} KB), "
          f"file_inventory.csv ({len(all_rows)-1} rows)")

if __name__ == "__main__":
    main()
