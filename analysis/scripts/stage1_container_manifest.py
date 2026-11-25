#!/usr/bin/env python3
"""
Stage 1 — Container + Manifest forensics for the ZaloPay 11.17.0 APK bundle.

Input : the APKPure ZIP -> XAPK -> split APKs (see analysis/README.md for extraction)
Output: analysis/data/01_container.json, analysis/data/02_manifest.json

The script is deterministic and defensive: every probe is isolated so a single
malformed structure cannot abort the whole run. Only derived, small artefacts are
written; the APK payload itself never enters Git.
"""
import io, json, os, zipfile, struct, hashlib, re, sys, logging, collections

logging.disable(logging.CRITICAL)
from loguru import logger as _lg; _lg.remove()
from androguard.core.apk import APK
from androguard.core.axml import AXMLPrinter

ROOT = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
DATA = os.path.join(ROOT, "data")
WORK = os.environ.get("ZPAY_WORK", "/home/user/work")
XAPK = os.path.join(WORK, "xapk")
BASE = os.path.join(XAPK, "vn.com.vng.zalopay.apk")
os.makedirs(DATA, exist_ok=True)


def sha256_file(p, limit=None):
    h = hashlib.sha256()
    with open(p, "rb") as f:
        while True:
            b = f.read(1 << 20)
            if not b:
                break
            h.update(b)
    return h.hexdigest()


def entropy(b):
    if not b:
        return 0.0
    c = collections.Counter(b)
    n = len(b)
    import math
    return round(-sum((v / n) * math.log2(v / n) for v in c.values()), 4)


# ─────────────────────────────────────────────────────────────────────────────
# 1a. Container: ZIP/XAPK structure, compression, alignment, hashes
# ─────────────────────────────────────────────────────────────────────────────
def container_report():
    out = {"xapk_dir": os.path.relpath(XAPK, WORK), "splits": [], "base_zip": {}}
    for fn in sorted(os.listdir(XAPK)):
        if not fn.endswith(".apk"):
            continue
        p = os.path.join(XAPK, fn)
        if fn == "vn.com.vng.zalopay.apk":
            with zipfile.ZipFile(p) as z:
                entries = z.infolist()
                stored = sum(1 for e in entries if e.compress_type == 0)
                deflated = sum(1 for e in entries if e.compress_type == 8)
                by_ext = collections.Counter()
                bytes_by_ext = collections.Counter()
                for e in entries:
                    ext = e.filename.rsplit(".", 1)[-1].lower() if "." in e.filename else "(none)"
                    by_ext[ext] += 1
                    bytes_by_ext[ext] += e.file_size
                # local header alignment probe on the first N entries
                raw = open(p, "rb").read(4 << 20)
                aligned = []
                for e in entries[:400]:
                    off = e.header_offset
                    if off + 30 > len(raw):
                        continue
                    name_len, extra_len = struct.unpack("<HH", raw[off + 26: off + 30])
                    data_off = off + 30 + name_len + extra_len
                    aligned.append({"name": e.filename, "data_off": data_off,
                                    "mod4": data_off % 4, "mod4096": data_off % 4096})
                out["base_zip"] = {
                    "entries": len(entries),
                    "stored": stored, "deflated": deflated,
                    "comment_len": len(z.comment),
                    "total_uncompressed": sum(e.file_size for e in entries),
                    "total_compressed": sum(e.compress_size for e in entries),
                    "extension_census": [
                        {"ext": k, "count": v, "bytes": bytes_by_ext[k]}
                        for k, v in by_ext.most_common(40)],
                    "aligned_probe": aligned[:60],
                }
            dex = [f for f in os.listdir(os.path.join(WORK, "apk", "vn.com.vng.zalopay")) if re.fullmatch(r"classes\d*\.dex", f)]
            dex.sort(key=lambda s: (len(s), s))
            out["dex_multidex"] = {"count": len(dex), "files": dex}
        out["splits"].append({
            "file": fn, "size": os.path.getsize(p), "sha256": sha256_file(p),
            "entropy": entropy(open(p, "rb").read(1 << 20)),
            "role": ("base" if fn == "vn.com.vng.zalopay.apk" else fn[:-4]),
        })
    return out


# ─────────────────────────────────────────────────────────────────────────────
# 1b. Signing identity (v1 absent, v2/v3 present -> APK Signature Scheme)
# ─────────────────────────────────────────────────────────────────────────────
def signing_report():
    a = APK(BASE)
    res = {"v1": a.is_signed_v1(), "v2": a.is_signed_v2(), "v3": a.is_signed_v3(), "certs": []}
    try:
        for c in a.get_certificates():
            ci = c
            try:
                from asn1crypto import x509
                x = x509.Certificate.load(ci.dump())
                res["certs"].append({
                    "subject": x.subject.human_friendly,
                    "issuer": x.issuer.human_friendly,
                    "serial": str(x.serial_number),
                    "sha1": ci.sha1_fingerprint.replace(" ", ""),
                    "sha256": ci.sha256_fingerprint.replace(" ", ""),
                    "not_before": str(x["tbs_certificate"]["validity"]["not_before"].native),
                    "not_after": str(x["tbs_certificate"]["validity"]["not_after"].native),
                    "signature_algo": x["signature_algorithm"]["algorithm"].native,
                    "pubkey_algo": x["tbs_certificate"]["subject_public_key_info"]["algorithm"]["algorithm"].native,
                })
            except Exception as e:  # pragma: no cover
                res["certs"].append({"error": repr(e), "raw": repr(ci)[:400]})
    except Exception as e:
        res["cert_error"] = repr(e)
    return res


# ─────────────────────────────────────────────────────────────────────────────
# 1c. Manifest: full decode to XML + structured extraction
# ─────────────────────────────────────────────────────────────────────────────
def manifest_xml():
    with zipfile.ZipFile(BASE) as z:
        raw = z.read("AndroidManifest.xml")
    pr = AXMLPrinter(raw)
    return pr.get_xml().decode("utf-8", "replace")


A = "{http://schemas.android.com/apk/res/android}"


def text_of(el, tag):
    return el.get(A + tag)


def manifest_report(xml_text: str):
    import xml.etree.ElementTree as ET
    root = ET.fromstring(xml_text)
    m = {"package": root.get("package"),
         "versionName": root.get(A + "versionName"),
         "versionCode": root.get(A + "versionCode"),
         "compileSdk": root.get(A + "compileSdkVersion"),
         "platformBuildVersion": root.get(A + "platformBuildVersionName") or root.get("platformBuildVersionName"),
         "installLocation": root.get(A + "installLocation")}
    uses_sdk = root.find("uses-sdk")
    if uses_sdk is not None:
        m["uses_sdk"] = {"min": text_of(uses_sdk, "minSdkVersion"),
                         "target": text_of(uses_sdk, "targetSdkVersion")}

    perms = []
    for p in root.findall("uses-permission") + root.findall("uses-permission-sdk-23"):
        perms.append({"name": p.get(A + "name"), "maxSdk": p.get(A + "maxSdkVersion")})
    m["permissions"] = perms
    m["custom_permissions"] = [
        {"name": p.get(A + "name"), "protection": p.get(A + "protectionLevel")}
        for p in root.findall("permission")]
    m["features"] = [{"name": f.get(A + "name"), "req": f.get(A + "required")}
                     for f in root.findall("uses-feature")]

    app = root.find("application")
    m["application"] = {
        "name": app.get(A + "name"),
        "theme": app.get(A + "theme"),
        "label": app.get(A + "label"),
        "allowBackup": app.get(A + "allowBackup"),
        "usesCleartextTraffic": app.get(A + "usesCleartextTraffic"),
        "networkSecurityConfig": app.get(A + "networkSecurityConfig"),
        "largeHeap": app.get(A + "largeHeap"),
        "debuggable": app.get(A + "debuggable"),
        "extractNativeLibs": app.get(A + "extractNativeLibs"),
        "requestLegacyExternalStorage": app.get(A + "requestLegacyExternalStorage"),
        "appComponentFactory": app.get(A + "appComponentFactory"),
    }
    nsc = app.get(A + "networkSecurityConfig")
    if nsc:
        m["application"]["nsc_ref"] = nsc

    comps = {"activity": [], "activity-alias": [], "service": [], "receiver": [], "provider": []}
    alias_targets = {}
    for tag in comps:
        for c in app.findall(tag):
            entry = {
                "name": c.get(A + "name"),
                "exported": c.get(A + "exported"),
                "permission": c.get(A + "permission"),
                "process": c.get(A + "process"),
                "directBootAware": c.get(A + "directBootAware"),
                "intent_filters": [],
            }
            if tag == "activity-alias":
                entry["targetActivity"] = c.get(A + "targetActivity")
                alias_targets[entry["name"]] = entry["targetActivity"]
            for f in c.findall("intent-filter"):
                actions = [x.get(A + "name") for x in f.findall("action")]
                cats = [x.get(A + "name") for x in f.findall("category")]
                datas = []
                for d in f.findall("data"):
                    datas.append({k: v for k, v in d.attrib.items()})
                prio = f.get(A + "priority")
                auto = f.get(A + "autoVerify")
                entry["intent_filters"].append({"actions": actions, "categories": cats,
                                                "data": datas, "priority": prio, "autoVerify": auto})
            comps[tag].append(entry)
    m["components"] = comps
    m["alias_targets"] = alias_targets
    m["meta_data"] = [{"name": x.get(A + "name"), "value": x.get(A + "value")}
                      for x in app.findall("meta-data")]
    m["queries"] = len(root.findall("queries"))
    m["xml_bytes"] = len(xml_text.encode())
    return m


# ─────────────────────────────────────────────────────────────────────────────
def deep_links(m):
    """Deep links are declared as <data android:scheme=.. android:host=..> inside
    intent filters. Reconstruct the URL templates per component."""
    links = []
    for kind, lst in m["components"].items():
        for c in lst:
            for f in c["intent_filters"]:
                for d in f["data"]:
                    sch = d.get(A + "scheme")
                    host = d.get(A + "host")
                    path = d.get(A + "path") or d.get(A + "pathPrefix") or d.get(A + "pathPattern")
                    if sch or host:
                        links.append({"component": c["name"], "kind": kind,
                                      "scheme": sch, "host": host, "path": path,
                                      "actions": f["actions"], "autoVerify": f["autoVerify"]})
    # dedupe
    seen, uniq = set(), []
    for l in links:
        k = (l["component"], l["scheme"], l["host"], l["path"])
        if k not in seen:
            seen.add(k)
            uniq.append(l)
    return uniq


def obfuscation_metrics(m):
    """How many manifest-referenced classes live in non-descriptive 1-2 segment
    namespaces (classic R8/ProGuard renaming)? A useful signature of the build."""
    names = [m["application"]["name"]]
    for kind, lst in m["components"].items():
        names += [c["name"] for c in lst]
    names = [n for n in names if n]
    weird = [n for n in names if re.fullmatch(r"[a-z]{6,12}\.[a-z]{6,12}(\.\w+)?", n or "")]
    return {"total_named": len(names), "obfuscated_top_level": sorted(set(weird))[:40],
            "count": len(set(weird))}


def main():
    print("[stage1] container …")
    cont = container_report()
    print("[stage1] signing …")
    cont["signing"] = signing_report()
    print("[stage1] manifest …")
    xml_text = manifest_xml()
    with open(os.path.join(DATA, "AndroidManifest.decoded.xml"), "w") as f:
        f.write(xml_text)
    man = manifest_report(xml_text)
    man["deep_links"] = deep_links(man)
    man["obfuscation"] = obfuscation_metrics(man)
    with open(os.path.join(DATA, "01_container.json"), "w") as f:
        json.dump(cont, f, indent=1, ensure_ascii=False)
    with open(os.path.join(DATA, "02_manifest.json"), "w") as f:
        json.dump(man, f, indent=1, ensure_ascii=False)
    print("[stage1] done:",
          len(cont["splits"]), "splits;",
          sum(len(v) for v in man["components"].values()), "components;",
          len(man["deep_links"]), "deep links")


if __name__ == "__main__":
    main()
