#!/usr/bin/env python3
"""
Zalopay 11.17.0 (APKPure XAPK) — static forensic collector.

Produces the machine-readable evidence set consumed by the TypeScript film
renderer (film/src/**). Every number that appears on screen in the film is
traced back to one of the JSON files emitted here.

Usage:  python3 film/tools/collect.py <path-to-xapk> <out-data-dir>
Requires: androguard 4.x (pip install androguard), python3 stdlib, openssl
"""
from __future__ import annotations

import collections
import hashlib
import json
import logging
import os
import re
import struct
import subprocess
import sys
import zipfile
from pathlib import Path

logging.disable(logging.CRITICAL)

OUT = Path(sys.argv[2] if len(sys.argv) > 2 else "film/data")
OUT.mkdir(parents=True, exist_ok=True)
XAPK = Path(sys.argv[1])
BASE = Path(os.environ.get("ZP_BASE", XAPK.parent.parent / "unpacked" / "base"))


def dump(name, obj):
    p = OUT / name
    p.write_text(json.dumps(obj, indent=1, ensure_ascii=False), encoding="utf-8")
    print(f"  wrote {p}  ({p.stat().st_size/1024:.1f} KB)")


def sha256_file(p: Path) -> str:
    h = hashlib.sha256()
    with open(p, "rb") as f:
        for chunk in iter(lambda: f.read(1 << 20), b""):
            h.update(chunk)
    return h.hexdigest()


# --------------------------------------------------------------------------
# 1. container: outer zip -> xapk -> split APKs
# --------------------------------------------------------------------------
def container():
    outer = Path(sys.argv[3]) if len(sys.argv) > 3 else None
    info = {"xapk_path": str(XAPK), "xapk_size": XAPK.stat().st_size,
            "xapk_sha256": sha256_file(XAPK), "xapk_entries": []}
    with zipfile.ZipFile(XAPK) as z:
        for i in z.infolist():
            if i.is_dir():
                continue
            info["xapk_entries"].append({
                "name": i.filename, "size": i.file_size,
                "compressed": i.compress_size,
                "ratio": round(i.compress_size / i.file_size, 4) if i.file_size else 0,
                "method": i.compress_type, "crc": format(i.CRC, "08x"),
            })
    info["xapk_entries"].sort(key=lambda e: -e["size"])
    if outer and outer.exists():
        info["outer"] = {"name": outer.name, "size": outer.stat().st_size,
                         "sha256": sha256_file(outer)}
        with zipfile.ZipFile(outer) as z:
            info["outer"]["entries"] = [
                {"name": i.filename, "size": i.file_size,
                 "compressed": i.compress_size} for i in z.infolist()]
    dump("01_container.json", info)


# --------------------------------------------------------------------------
# 2. manifest: identity, signing, components, attack surface
# --------------------------------------------------------------------------
def manifest():
    from androguard.core.apk import APK
    a = APK(str(XAPK))
    d = {
        "package": a.get_package(), "version_name": a.get_androidversion_name(),
        "version_code": a.get_androidversion_code(), "min_sdk": a.get_min_sdk_version(),
        "target_sdk": a.get_target_sdk_version(), "app_name": a.get_app_name(),
        "main_activity": a.get_main_activity(),
        "permissions": sorted(a.get_permissions()),
        "declared_permissions": sorted(a.get_declared_permissions()),
        "activities": sorted(a.get_activities()), "services": sorted(a.get_services()),
        "receivers": sorted(a.get_receivers()), "providers": sorted(a.get_providers()),
        "libraries": sorted(a.get_libraries()),
        "features": sorted(a.get_features()),
        "signature_names": sorted(a.get_signature_names()),
        "signed_v1": a.is_signed_v1(), "signed_v2": a.is_signed_v2(), "signed_v3": a.is_signed_v3(),
        "counts": {}, "files": len(a.get_files()),
        "xml_attributes": {},
    }
    d["counts"] = {"activities": len(d["activities"]), "services": len(d["services"]),
                   "receivers": len(d["receivers"]), "providers": len(d["providers"]),
                   "permissions": len(d["permissions"])}
    # signing certificate identity
    try:
        from cryptography import x509
        from cryptography.hazmat.primitives import hashes
        der = a.get_certificates_der_v3() or a.get_certificates_der_v2()
        if der:
            cert = x509.load_der_x509_certificate(der[0])
            fp = cert.fingerprint(hashes.SHA256()).hex()
            d["certificate"] = {
                "sha256": fp,
                "sha1": hashlib.sha1(der[0]).hexdigest(),
                "md5": hashlib.md5(der[0]).hexdigest(),
                "subject": cert.subject.rfc4514_string(),
                "issuer": cert.issuer.rfc4514_string(),
                "serial": format(cert.serial_number, "x"),
                "not_before": cert.not_valid_before_utc.isoformat(),
                "not_after": cert.not_valid_after_utc.isoformat(),
                "sig_alg": cert.signature_algorithm_oid._name,
                "pubkey_bits": getattr(cert.public_key(), "key_size", None),
                "pubkey_type": type(cert.public_key()).__name__,
                "der_len": len(der[0]),
            }
    except Exception as e:  # pragma: no cover
        d["certificate_error"] = repr(e)
    # security-relevant application flags
    try:
        raw = a.get_android_manifest_xml()
        app = raw.find("application")
        interesting = ["debuggable", "allowBackup", "usesCleartextTraffic",
                       "networkSecurityConfig", "extractNativeLibs", "largeHeap",
                       "requestLegacyExternalStorage", "fullBackupContent",
                       "dataExtractionRules", "hardwareAccelerated", "testOnly",
                       "usesNonSdkApi", "name", "label", "theme"]
        ns = "{http://schemas.android.com/apk/res/android}"
        for k in interesting:
            v = app.get(ns + k) if app is not None else None
            if v is not None:
                d["xml_attributes"][k] = v
        # exported component census (needs binary manifest attribution)
        exp = {"activity": 0, "service": 0, "receiver": 0, "provider": 0}
        for tag, key in (("activity", "activity"), ("service", "service"),
                         ("receiver", "receiver"), ("provider", "provider")):
            for el in raw.iter(tag):
                if el.get(ns + "exported") == "true":
                    exp[key] += 1
        d["exported_declared_true"] = exp
        d["tencent_like_patch"] = None
    except Exception as e:
        d["xml_error"] = repr(e)
    dump("02_manifest.json", d)
    return d


# --------------------------------------------------------------------------
# 3. DEX survey
# --------------------------------------------------------------------------
def dex_survey():
    from androguard.core.dex import DEX
    base = BASE
    files = sorted(base.glob("classes*.dex"),
                   key=lambda p: int("".join(c for c in p.stem if c.isdigit()) or 0))
    res = {"dex_files": [], "total_classes": 0, "total_methods": 0,
           "total_fields": 0, "total_strings": 0, "packages": {}, "classes": []}
    pkg = collections.Counter()
    for f in files:
        d = DEX(f.read_bytes())
        cls = d.get_classes()
        m = sum(len(c.get_methods()) for c in cls)
        fl = sum(len(c.get_fields()) for c in cls)
        st = len(d.get_strings())
        res["total_classes"] += len(cls); res["total_methods"] += m
        res["total_fields"] += fl; res["total_strings"] += st
        for c in cls:
            n = c.get_name()[1:-1].replace("/", ".")
            res["classes"].append(n)
            parts = n.split(".")
            pkg[".".join(parts[:2]) if len(parts) > 1 else n] += 1
        res["dex_files"].append({"name": f.name, "bytes": f.stat().st_size,
                                 "classes": len(cls), "methods": m, "fields": fl,
                                 "strings": st})
    res["packages"] = dict(pkg.most_common(120))
    res["class_count_by_root"] = dict(
        collections.Counter(".".join(c.split(".")[:3]) for c in res["classes"]).most_common(60))
    # obfuscation metric: share of classes whose simple name is 1-2 chars
    short = sum(1 for c in res["classes"] if len(c.split(".")[-1].split("$")[-1]) <= 2)
    res["obfuscation"] = {"short_named_classes": short,
                          "share_pct": round(100 * short / max(1, len(res["classes"])), 2)}
    dump("03_dex.json", res)
    return res


# --------------------------------------------------------------------------
# 4. ELF / native survey
# --------------------------------------------------------------------------
def elf_survey():
    base = BASE
    libs = sorted(base.glob("lib/*/*.so"))
    out = []
    for p in libs:
        b = p.read_bytes()
        rec = {"name": p.name, "bytes": len(b), "sha256": hashlib.sha256(b).hexdigest(),
               "magic_ok": b[:4] == b"\x7fELF"}
        if rec["magic_ok"]:
            machine = struct.unpack_from("<H", b, 0x12)[0]
            rec["machine"] = {40: "arm32", 183: "arm64", 3: "x86"}.get(machine, str(machine))
            e_shoff = struct.unpack_from("<I", b, 0x20)[0]
            e_shentsize = struct.unpack_from("<H", b, 0x2e)[0]
            e_shnum = struct.unpack_from("<H", b, 0x30)[0]
            e_shstrndx = struct.unpack_from("<H", b, 0x32)[0]
            secs = []
            for i in range(e_shnum):
                off = e_shoff + i * e_shentsize
                if off + 40 > len(b):
                    break
                nm, typ, flags, addr, offset, size, link, info, align, ent = struct.unpack_from("<IIIIIIIIII", b, off)
                secs.append([nm, typ, flags, addr, offset, size, link, info, align, ent])
            shstr = secs[e_shstrndx]
            blob = b[shstr[4]:shstr[4] + shstr[5]]

            def sname(o):
                e = blob.find(b"\0", o)
                return blob[o:e].decode("latin1")

            names = {}
            for s in secs:
                names[sname(s[0])] = s
            rec["sections"] = len(secs)
            rec["dynsym"] = names[".dynsym"][5] // names[".dynsym"][9] if ".dynsym" in names and names[".dynsym"][9] else 0
            rec["symtab"] = names[".symtab"][5] // names[".symtab"][9] if ".symtab" in names and names[".symtab"][9] else 0
            rec["has_symtab"] = rec["symtab"] > 0
            if ".rodata" in names:
                rb = b[names[".rodata"][4]:names[".rodata"][4] + names[".rodata"][5]]
                cnt = collections.Counter(rb)
                n = len(rb)
                rec["rodata_entropy"] = round(-sum((v / n) * __import__("math").log2(v / n) for v in cnt.values()), 3) if n else 0
            # exported JNI symbols
            jni = []
            if ".dynsym" in names and ".dynstr" in names:
                ds = names[".dynstr"]; dsb = b[ds[4]:ds[4] + ds[5]]
                sym = names[".dynsym"]
                for i in range(rec["dynsym"]):
                    o = sym[4] + i * sym[9]
                    if o + 16 > len(b):
                        break
                    st_name, st_value, st_size, st_info, st_other, st_shndx = struct.unpack_from("<IIIBBH", b, o)
                    e = dsb.find(b"\0", st_name)
                    nmn = dsb[st_name:e].decode("latin1")
                    if nmn.startswith("Java_") or nmn in ("JNI_OnLoad", "JNI_OnUnload"):
                        jni.append(nmn)
            rec["jni_symbols"] = jni[:40]
            rec["jni_count"] = len(jni)
            # library dependencies
            needed = []
            if ".dynstr" in names:
                dsb = b[names[".dynstr"][4]:names[".dynstr"][4] + names[".dynstr"][5]]
                needed = sorted({x.decode("latin1") for x in dsb.split(b"\0")
                                 if x.endswith(b".so") and len(x) > 4})
            rec["needed"] = needed[:20]
        out.append(rec)
    out.sort(key=lambda r: -r["bytes"])
    dump("04_native.json", {"count": len(out), "total_bytes": sum(r["bytes"] for r in out),
                            "libs": out})


# --------------------------------------------------------------------------
# 5. assets & resources census
# --------------------------------------------------------------------------
def assets():
    base = BASE
    a = base / "assets"
    files = []
    for p in sorted(a.rglob("*")):
        if p.is_file():
            files.append({"path": str(p.relative_to(a)), "bytes": p.stat().st_size})
    ext = collections.Counter(Path(f["path"]).suffix.lower() or "(none)" for f in files)
    res = base / "res"
    resext = collections.Counter()
    resn = 0
    for p in res.rglob("*"):
        if p.is_file():
            resn += 1
            resext[p.suffix.lower() or "(none)"] += 1
    d = {"assets_count": len(files), "assets_bytes": sum(f["bytes"] for f in files),
         "assets_by_ext": dict(ext.most_common(30)),
         "largest_assets": sorted(files, key=lambda f: -f["bytes"])[:25],
         "res_files": resn, "res_by_ext": dict(resext.most_common(15)),
         "arsc_bytes": (base / "resources.arsc").stat().st_size,
         "total_files_in_base_apk": sum(1 for _ in base.rglob("*") if _.is_file())}
    # interesting cleartext asset configs the film can quote
    quotes = {}
    for name in ("config/zalopay_config.json", "config/miniapp_config.json",
                 "safemode_registrator_com_iap_ac_config.json", "Griver/appx.json",
                 "bundle.json", "taibmcf.json", "vdfmoer.json"):
        p = a / name
        if p.exists():
            try:
                quotes[name] = p.read_text(encoding="utf-8", errors="replace")[:400]
            except Exception:
                pass
    d["config_quotes"] = quotes
    pub = a / "config/public_key.txt"
    if pub.exists():
        txt = pub.read_text(errors="replace")
        d["config_public_key"] = {"pem_head": txt.splitlines()[0],
                                  "pem_tail": txt.strip().splitlines()[-1],
                                  "lines": len(txt.splitlines()),
                                  "declared_bits": 2048 if "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8A" in txt else None}
    dump("05_assets.json", d)


# --------------------------------------------------------------------------
# 6. string intelligence: domains, crypto, anti-tamper, telemetry
# --------------------------------------------------------------------------
def strings_intel():
    base = BASE
    from androguard.core.dex import DEX
    allstr = []
    for f in sorted(base.glob("classes*.dex")):
        d = DEX(f.read_bytes())
        for s in d.get_strings():
            allstr.append(str(s.get() if hasattr(s, "get") else s))
    blob = "\n".join(allstr)
    hosts = collections.Counter(re.findall(r"https?://([a-zA-Z0-9._-]+)", blob))
    hosts_zp = {h: c for h, c in hosts.items() if "zalopay" in h or "zalo" in h or "vng" in h}
    hosts_3p = {h: c for h, c in hosts.items() if not ("zalopay" in h or "zalo" in h or "vng" in h)}
    kws = ["frida", "xposed", "magisk", "isRooted", "emulator", "isDebuggerConnected",
           "SafetyNet", "PlayIntegrity", "certificatePinner", "ssl_pinning", "tamper",
           "integrity", "SQLCipher", "Keystore", "AES/GCM", "HmacSHA256", "SHA-256",
           "ECIES", "ECDSA", "RSA/ECB", "Sec-Fetch", "device_id", "deviceId",
           "X-Request-Signature", "signV2", "attestation", "biometric",
           "promptInfo", "PromptInfo", "Cipher", "PBKDF2", "Argon2", "scrypt"]
    hits = {k: len(re.findall(re.escape(k), blob, re.I)) for k in kws}
    # crypto algorithm identifiers actually embedded
    algos = collections.Counter(re.findall(
        r"\b(?:AES|DESede|DES|RSA|EC|HmacSHA1|HmacSHA256|HmacSHA512|SHA-?1|SHA-?256|SHA-?512|MD5|PBKDF2WithHmacSHA1|ChaCha20)(?:/[A-Za-z0-9]+)*(?:/[A-Za-z0-9]+)*\b", blob))
    d = {"string_total": len(allstr), "unique": len(set(allstr)),
         "hosts_zalopay": dict(sorted(hosts_zp.items(), key=lambda kv: -kv[1])[:40]),
         "hosts_third_party": dict(sorted(hosts_3p.items(), key=lambda kv: -kv[1])[:40]),
         "hosts_total": len(hosts), "keyword_hits": hits,
         "crypto_algorithms": dict(algos.most_common(40)),
         "class_like_strings": len([s for s in allstr if s.startswith("L") and s.endswith(";")])}
    dump("06_strings.json", d)


# --------------------------------------------------------------------------
# 7. SDK inventory from *.properties manifests
# --------------------------------------------------------------------------
def sdks():
    base = BASE
    out = []
    for p in sorted(base.glob("*.properties")):
        kv = {}
        try:
            for line in p.read_text(errors="replace").splitlines():
                if "=" in line:
                    k, v = line.split("=", 1)
                    kv[k.strip()] = v.strip()
        except Exception:
            continue
        out.append({"artifact": p.stem, "version": kv.get("version"),
                    "group": kv.get("group"), "name": kv.get("name")})
    out = [o for o in out if o["version"] or o["name"]]
    out.sort(key=lambda o: o["artifact"])
    dump("07_sdks.json", {"count": len(out), "sdks": out})


# --------------------------------------------------------------------------
# 8. Zalopay first-party class map + hot classes worth quoting
# --------------------------------------------------------------------------
def first_party(dex):
    cls = dex["classes"]
    zp = sorted(c for c in cls if c.startswith("vn.com.vng.zalopay") or c.startswith("vn.zalopay"))
    roots = collections.Counter(".".join(c.split(".")[:4]) for c in zp)
    domains = sorted({c.split(".")[3] for c in zp if c.count(".") > 3})
    dump("08_firstparty.json", {
        "count": len(zp),
        "top_subpackages": dict(roots.most_common(40)),
        "feature_domains": domains,
        "classes": zp,
    })


def main():
    print("[1/8] container")
    container()
    print("[2/8] manifest")
    m = manifest()
    print("[3/8] dex")
    d = dex_survey()
    print("[4/8] native")
    elf_survey()
    print("[5/8] assets")
    assets()
    print("[6/8] strings")
    strings_intel()
    print("[7/8] sdks")
    sdks()
    print("[8/8] first party")
    first_party(d)
    print("done; components:", m["counts"])


if __name__ == "__main__":
    main()
