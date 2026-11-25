"""Pipeline entry point.

    python -m research.zalopay_re run            # full pipeline -> data/
    python -m research.zalopay_re run --fast     # skip deep DEX bytecode walk
    python -m research.zalopay_re report         # regenerate reports only

Stage order matters: every stage only reads the container and the JSON that
previous stages wrote, so any stage can be re-run in isolation.
"""

from __future__ import annotations

import argparse
import gzip
import hashlib
import io
import json
import os
import shutil
import sys
import time
import zipfile
from collections import Counter, defaultdict

ROOT = os.path.dirname(os.path.dirname(os.path.dirname(os.path.abspath(__file__))))
DATA = os.path.join(ROOT, "data")
WORK = os.path.join(ROOT, "work")
XAPK_DIR = os.path.join(WORK, "xapk")


def log(msg: str) -> None:
    print(f"[{time.strftime('%H:%M:%S')}] {msg}", flush=True)


def write_json(name: str, obj) -> None:
    os.makedirs(DATA, exist_ok=True)
    path = os.path.join(DATA, name)
    with open(path, "w", encoding="utf-8") as fh:
        json.dump(obj, fh, indent=1, ensure_ascii=False, sort_keys=False)
    log(f"  -> {os.path.relpath(path, ROOT)} ({os.path.getsize(path) / 1024:.0f} KiB)")


def find_container() -> str:
    for f in os.listdir(ROOT):
        if f.lower().endswith(".zip") and "zalopay" in f.lower():
            return os.path.join(ROOT, f)
    raise SystemExit("container zip not found at repo root")


def ensure_extracted(zip_path: str) -> str:
    """Unzip outer .zip -> .xapk -> {base.apk, config.*.apk, manifest.json}."""
    os.makedirs(XAPK_DIR, exist_ok=True)
    marker = os.path.join(XAPK_DIR, ".extracted")
    if os.path.exists(marker):
        return XAPK_DIR
    with zipfile.ZipFile(zip_path) as z:
        xapk_name = [n for n in z.namelist() if n.lower().endswith(".xapk")][0]
        xapk_bytes = z.read(xapk_name)
    with zipfile.ZipFile(io.BytesIO(xapk_bytes)) as zx:
        for info in zx.infolist():
            if info.is_dir():
                continue
            with zx.open(info) as src, open(os.path.join(XAPK_DIR, info.filename), "wb") as dst:
                shutil.copyfileobj(src, dst)
    open(marker, "w").write("ok")
    return XAPK_DIR


def stage_container(zip_path: str, xapk_dir: str) -> dict:
    from .container import container_tree

    log("stage 1/9 container + entry inventory")
    tree = container_tree(zip_path)
    xapk_path = None
    with zipfile.ZipFile(zip_path) as z:
        xapk_path = [n for n in z.namelist() if n.lower().endswith(".xapk")][0]
    tree["inner"] = {"name": xapk_path}
    apks = sorted(f for f in os.listdir(xapk_dir) if f.endswith(".apk"))
    inner = []
    for apk in apks:
        p = os.path.join(xapk_dir, apk)
        with open(p, "rb") as fh:
            h = hashlib.sha256(fh.read()).hexdigest()
        inner.append({"name": apk, "size": os.path.getsize(p), "sha256": h})
    tree["inner"]["members"] = inner
    mf = os.path.join(xapk_dir, "manifest.json")
    if os.path.exists(mf):
        tree["inner"]["apkpure_manifest"] = json.load(open(mf, encoding="utf-8"))

    entries = []
    from .container import inventory_apk, summarize
    from .manifest import analyze_manifest

    labels = {}
    per_apk_manifest = {}
    for apk in apks:
        p = os.path.join(xapk_dir, apk)
        with zipfile.ZipFile(p) as z:
            try:
                m = analyze_manifest(z.read("AndroidManifest.xml"), apk)
            except KeyError:
                m = None
        if m:
            m["split_label"] = m["manifest"].get("split") or "base"
        per_apk_manifest[apk] = m
        label = (m["split_label"] if m else apk)
        labels[apk] = label
        rows = inventory_apk(p, label)
        log(f"  {apk}: {len(rows)} entries (label={label})")
        entries.extend(rows)
    summary = summarize(entries)
    write_json("01_container.json", tree)
    write_json("01_inventory_summary.json", summary)
    with gzip.open(os.path.join(DATA, "01_entries.jsonl.gz"), "wt", encoding="utf-8") as fh:
        for e in entries:
            fh.write(json.dumps(e.to_json(), ensure_ascii=False) + "\n")
    log(f"  inventory: {summary['entries']} entries, "
        f"{summary['total_uncompressed']/1e6:.1f} MB uncompressed")
    return {"tree": tree, "summary": summary, "manifests": per_apk_manifest, "labels": labels}


def stage_manifest(xapk_dir: str, man: dict) -> dict:
    log("stage 2/9 manifests")
    base = None
    per_apk = {}
    for apk, m in man.items():
        if not m:
            continue
        slim = {k: m[k] for k in ("manifest", "uses_sdk", "permission_summary", "component_counts",
                                  "exported_counts", "app_link_domains", "queries")}
        slim["components"] = m["components"]
        slim["permissions"] = m["permissions"]
        slim["application"] = m["application"]
        slim["deep_links"] = m["deep_links"]
        slim["authorities"] = m["authorities"]
        per_apk[apk] = slim
        if m.get("split_label") == "base":
            base = m
    out = {"base": base, "per_apk": per_apk,
           "totals": {
               "components": sum(v["component_counts"].get(k, 0) for v in per_apk.values() for k in
                                 ("activity", "activity-alias", "service", "receiver", "provider")),
               "permissions": len(base["permissions"]),
               "dangerous": base["permission_summary"]["dangerous"],
               "exported": sum(sum(v["exported_counts"].values()) for v in per_apk.values()),
               "deep_links": len(base["deep_links"]),
               "authorities": len(base["authorities"]),
           }}
    write_json("02_manifest.json", out)
    return out


def stage_resources(xapk_dir: str) -> dict:
    log("stage 3/9 resource table + res/xml policy files")
    from .arsc import ResTable
    from .manifest import analyze_res_xml

    base_apk = os.path.join(xapk_dir, "vn.com.vng.zalopay.apk")
    out = {"base": None, "splits": {}, "res_xml": {}, "resolved": {}}
    with zipfile.ZipFile(base_apk) as z:
        tbl = ResTable(z.read("resources.arsc"))
        out["base"] = tbl.census()
        out["res_xml"] = analyze_res_xml(z, limit=200)
        app_refs = {}
        try:
            from .manifest import analyze_manifest
            m = analyze_manifest(z.read("AndroidManifest.xml"), "base")
            for key in ("networkSecurityConfig", "dataExtractionRules", "fullBackupContent"):
                ref = m["application"].get(key)
                if ref and ref.startswith("0x"):
                    ent = tbl.resolve(ref)
                    app_refs[key] = ent.to_json() if ent else ref
                elif ref:
                    app_refs[key] = ref
        except Exception as exc:  # pragma: no cover
            app_refs["error"] = str(exc)
        out["resolved"] = app_refs
    for f in sorted(os.listdir(xapk_dir)):
        if f.startswith("config.") and f.endswith(".apk"):
            with zipfile.ZipFile(os.path.join(xapk_dir, f)) as z:
                try:
                    out["splits"][f] = ResTable(z.read("resources.arsc")).census()
                except KeyError:
                    pass
    out["split_locales"] = {k: list(v["locales"].items())[:20] for k, v in out["splits"].items()}
    write_json("03_resources.json", out)
    return out


def stage_dex(xapk_dir: str, fast: bool) -> dict:
    log("stage 4/9 dex bytecode survey")
    from .dex import Dex, DexSurvey, StringIntel, Watchlist, survey_dex

    base_apk = os.path.join(xapk_dir, "vn.com.vng.zalopay.apk")
    agg = DexSurvey()
    strings = StringIntel()
    watch = Watchlist()
    files = []
    with zipfile.ZipFile(base_apk) as z:
        dex_names = sorted([n for n in z.namelist() if n.endswith(".dex")],
                           key=lambda n: (len(n), n))
        for name in dex_names:
            blob = z.read(name)
            d = Dex(name, blob)
            t0 = time.time()
            pf = survey_dex(d, agg, watch, collect_strings=not fast)
            # full string-pool intel scan (cheap, better recall than const-string only)
            for s in d.iter_strings():
                strings(s, name)
            del d, blob
            k = len(pf.pop("class_list", []))
            pf["classes"] = max(k, pf["classes"])
            pf["seconds"] = round(time.time() - t0, 2)
            files.append(pf)
            log(f"  {name}: {pf['classes']} classes, {pf['insns']} insns, "
                f"{pf['seconds']}s ({len(files)}/{len(dex_names)})")
    total_classes = sum(f["classes"] for f in files)
    out = {
        "files": files,
        "totals": {
            "dex_files": len(files),
            "bytes": sum(f["size"] for f in files),
            "classes": total_classes,
            "methods": agg.method_count,
            "fields": agg.field_count,
            "strings": sum(f["strings"] for f in files),
            "insns": agg.insn_count,
            "code_units": agg.code_units,
            "packages": len(agg.pkg_classes),
        },
        "opcode_hist": dict(agg.opcode_hist.most_common(60)),
        "package_classes": dict(agg.pkg_classes.most_common(60)),
        "api_hits": dict(agg.api_hits.most_common()),
        "api_by_caller": {f"{c}|{p}": n for (c, p), n in agg.api_by_caller.most_common(200)},
        "api_examples": {k: v[:12] for k, v in agg.api_examples.items()},
        "edges": [{"src": a, "dst": b, "w": w} for (a, b), w in agg.pkg_edges.most_common(400)],
        "app_edges": [{"src": a, "dst": b, "w": w} for (a, b), w in agg.app_edges.most_common(300)],
        "obfuscation": {
            "tiny_class_names": agg.tiny_names,
            "tiny_ratio": round(agg.tiny_names / max(1, total_classes), 4),
            "classes_with_annotations": agg.kotlin_meta,
        },
        "strings_intel": strings.to_json(),
    }
    write_json("04_dex.json", out)
    return out


def stage_native(xapk_dir: str) -> dict:
    log("stage 5/9 native libraries")
    from .elf import parse_elf

    libs = []
    for f in sorted(os.listdir(xapk_dir)):
        if not f.endswith(".apk"):
            continue
        with zipfile.ZipFile(os.path.join(xapk_dir, f)) as z:
            for n in z.namelist():
                if n.endswith(".so"):
                    blob = z.read(n)
                    try:
                        info = parse_elf(n, blob)
                        row = info.to_json()
                    except Exception as exc:
                        # e.g. assets/trueidlib.so is a 392-byte placeholder, not ELF
                        row = {"name": n, "size": len(blob), "error": str(exc),
                               "sha256": hashlib.sha256(blob).hexdigest(),
                               "hardening_score": 0, "jni_symbols": [], "needed": [],
                               "is_pie": False, "nx_stack": None, "relro": False,
                               "full_relro": False, "stack_canary": False, "fortify": False,
                               "stripped": None, "compiler": None,
                               "max_section_entropy": 0.0, "summary_note": "non-ELF payload"}
                    libs.append(row)
    libs.sort(key=lambda r: -r["size"])
    cnt = len(libs)
    def frac(key: str) -> int:
        return sum(1 for l in libs if l.get(key))
    out = {
        "libraries": libs,
        "summary": {
            "count": cnt,
            "total_bytes": sum(l["size"] for l in libs),
            "pie": frac("is_pie"), "nx": frac("nx_stack"), "relro": frac("relro"),
            "full_relro": frac("full_relro"), "canary": frac("stack_canary"),
            "fortify": frac("fortify"), "stripped": frac("stripped"),
            "avg_hardening": round(sum(l["hardening_score"] for l in libs) / max(1, cnt), 2),
            "jni_exports": sum(len(l["jni_symbols"]) for l in libs),
            "high_entropy": [l["name"] for l in libs if l["max_section_entropy"] > 7.2][:20],
            "compilers": dict(Counter(l["compiler"] for l in libs if l.get("compiler")).most_common(10)),
            "needed": dict(Counter(dep for l in libs for dep in l["needed"]).most_common(20)),
        },
    }
    write_json("05_native.json", out)
    return out


def stage_signing(xapk_dir: str) -> dict:
    log("stage 6/9 signing schemes")
    from .apksig import parse_cert, parse_signing_block, v1_certificates

    out = {"per_apk": {}, "schemes": [], "certificates": [], "block_pairs": [],
           "source_stamp": False, "consistent": True}
    seen_certs = {}
    for f in sorted(os.listdir(xapk_dir)):
        if not f.endswith(".apk"):
            continue
        p = os.path.join(xapk_dir, f)
        data = open(p, "rb").read()
        sb = parse_signing_block(data)
        with zipfile.ZipFile(p) as z:
            v1 = v1_certificates(z)
        certs = []
        for scheme in sb.get("schemes", []):
            if scheme["scheme"] not in out["schemes"]:
                out["schemes"].append(scheme["scheme"])
            for signer in scheme["signers"]:
                for der_hex in signer["certificates_der"]:
                    if der_hex in seen_certs:
                        certs.append(seen_certs[der_hex])
                        continue
                    c = parse_cert(bytes.fromhex(der_hex))
                    c["first_seen"] = f
                    seen_certs[der_hex] = c
                    certs.append(c)
        for blk in v1.get("blocks", []):
            if "v1" not in out["schemes"]:
                out["schemes"].append("v1")
            certs.extend(blk["certs"])
        out["per_apk"][f] = {"schemes": [s["scheme"] for s in sb.get("schemes", [])],
                             "v1": v1, "cert_sha256": [c["sha256"] for c in certs],
                             "block_pairs": sb.get("pairs", []), "signed": sb.get("present", False)}
        if f.endswith("zalopay.apk"):
            out["block_pairs"] = sb.get("pairs", [])
            out["source_stamp"] = any(pr["id"] == "0x6dff800d" for pr in sb.get("pairs", []))
            out["signing_block_size"] = sb.get("size")
            out["offset"] = sb.get("offset")
    cert_sets = {tuple(sorted(set(v["cert_sha256"]))) for v in out["per_apk"].values()}
    out["consistent"] = len(cert_sets) <= 1
    out["module_schemes"] = {k: v["schemes"] for k, v in out["per_apk"].items()}
    out["v1_modules"] = [k for k, v in out["per_apk"].items() if v["v1"].get("blocks")]
    out["v1_files"] = sorted({b["file"] for v in out["per_apk"].values() for b in v["v1"].get("blocks", [])})
    out["certificates"] = list(seen_certs.values())
    out["split_signature_identical"] = out["consistent"]
    write_json("06_signing.json", out)
    return out


def stage_intel(xapk_dir: str, dex: dict, res: dict) -> dict:
    log("stage 7/9 composition intelligence")
    from .intel import (analyze_assets, classify_host, host_taxonomy,
                        library_versions, package_census)

    base_apk = os.path.join(xapk_dir, "vn.com.vng.zalopay.apk")
    classes, descriptors = [], []
    with gzip.open(os.path.join(DATA, "04_classes.jsonl.gz"), "rt", encoding="utf-8") as fh:
        for line in fh:
            row = json.loads(line)
            classes.append(row["class"])
            descriptors.append(row["descriptor"])
    with zipfile.ZipFile(base_apk) as z:
        libs = library_versions(z)
        assets = analyze_assets(z)
    census = package_census({}, descriptors)

    # ---- integrity / anti-tamper hints ----------------------------------
    si = dex.get("strings_intel", {})
    pool = []
    for kind in ("url", "host", "path", "pem", "crypto_alg", "ipv4", "root_hint", "pinning_hint"):
        pool.extend(si.get("kinds", {}).get(kind, {}).get("items", {}).keys())
    pool.extend(si.get("secret_like_names", {}).keys())
    pool.extend(assets.get("hosts", []))
    pool.extend(assets.get("urls", []))
    class_blob = "\n".join(descriptors)
    hints = {}
    for label, needles in {
        "play_integrity": ["playintegrity", "integritymanager", "safetynet", "attestation"],
        "root_detection": ["rootbeer", "isrooted", "superuser", "magisk", "/su", "rootshell"],
        "hook_detection": ["frida", "xposed", "lsposed", "substrate", "ptrace"],
        "emulator_detection": ["emulator", "goldfish", "qemu", "genymotion"],
        "screen_capture_guard": ["flag_secure", "detect_screen_capture", "setflags"],
        "biometric_gate": ["biometric", "fingerprint", "keystore"],
        "certificate_pinning": ["pinning", "certificatepinner", "sha256/"],
        "code_obfuscation": ["dexguard", "proguard", "r8", "obfusc"],
        "screenshot_or_hook_strings": ["magisk", "frida-server", "xposed"],
    }.items():
        n = sum(1 for s in pool if any(x in s.lower() for x in needles))
        n += sum(1 for d in descriptors if any(x in d.lower() for x in needles))
        if n:
            hints[label] = n

    # ---- host taxonomy ---------------------------------------------------
    all_hosts = set(assets.get("hosts", []))
    all_hosts |= set(si.get("kinds", {}).get("host", {}).get("items", {}).keys())
    taxonomy = host_taxonomy(all_hosts)

    out = {"libraries": libs, "assets": assets, "census": census,
           "integrity_hints": hints, "classes_total": len(classes),
           "host_taxonomy": taxonomy,
           "host_class_counts": {k: v["count"] for k, v in taxonomy.items()},
           "first_party_classes": sum(1 for d in descriptors
                                      if d.startswith(("Lvn/com/vng/zalopay", "Lvn/zalopay",
                                                       "Lvn/com/zalopay"))) }
    write_json("07_intel.json", out)
    return out


def stage_classes(xapk_dir: str) -> int:
    """Second pass over DEX: emit the full class list (gzipped JSONL)."""
    log("stage 4b/9 class list export")
    from .dex import Dex

    base_apk = os.path.join(xapk_dir, "vn.com.vng.zalopay.apk")
    n = 0
    with zipfile.ZipFile(base_apk) as z, gzip.open(
            os.path.join(DATA, "04_classes.jsonl.gz"), "wt", encoding="utf-8") as out:
        for name in sorted([x for x in z.namelist() if x.endswith(".dex")]):
            d = Dex(name, z.read(name))
            for cd in d.iter_class_defs():
                data = d.class_data(cd["class_data_off"])
                out.write(json.dumps({
                    "class": cd["name"].lstrip("L").rstrip(";").replace("/", "."),
                    "descriptor": cd["name"],
                    "dex": name,
                    "access": cd["access"],
                    "super": (cd["super"] or "").lstrip("L").rstrip(";").replace("/", ".") or None,
                    "methods": len(data["direct"]) + len(data["virtual"]),
                    "fields": len(data["static_fields"]) + len(data["instance_fields"]),
                }, ensure_ascii=False) + "\n")
                n += 1
            del d
    log(f"  {n} classes exported")
    return n


def stage_findings(all_data: dict) -> dict:
    log("stage 8/9 findings")
    from .findings import build_findings, summarize

    findings = build_findings(all_data)
    out = {"findings": [f.to_json() for f in findings], "summary": summarize(findings)}
    write_json("08_findings.json", out)
    return out


def build_summary(container, man, res, dex, native, signing, intel, findings) -> dict:
    base_man = man["base"]
    s = {
        "artifact": {
            "package": base_man["manifest"]["package"],
            "version": base_man["manifest"]["versionName"],
            "version_code": base_man["manifest"]["versionCode"],
            "min_sdk": base_man["uses_sdk"].get("minSdkVersion"),
            "target_sdk": base_man["uses_sdk"].get("targetSdkVersion"),
            "compile_sdk": base_man["manifest"].get("compileSdkVersion"),
            "sha256_outer": container["tree"]["sha256"],
            "size_outer": container["tree"]["size"],
            "apks": len(container["tree"]["inner"]["members"]),
            "entries": container["summary"]["entries"],
            "uncompressed_bytes": container["summary"]["total_uncompressed"],
            "categories": container["summary"]["by_category"],
        },
        "manifest": {
            "permissions": len(base_man["permissions"]),
            "dangerous": base_man["permission_summary"]["dangerous"],
            "dangerous_groups": base_man["permission_summary"]["dangerous_groups"],
            "components": base_man["component_counts"],
            "exported": base_man["exported_counts"],
            "deep_links": len(base_man["deep_links"]),
            "app_link_domains": base_man["app_link_domains"],
            "authorities": len(base_man["authorities"]),
            "queries": len(base_man["queries"]),
            "application": base_man["application"],
        },
        "dex": dex["totals"],
        "resources": {
            "packages": res["base"]["package_count"] if res.get("base") else 0,
            "entries": res["base"]["total_entries"] if res.get("base") else 0,
            "strings": res["base"]["global_strings"] if res.get("base") else 0,
            "locales_split": res.get("split_locales", {}),
        },
        "native": native["summary"],
        "signing": {
            "schemes": signing["schemes"],
            "certificates": signing["certificates"],
            "source_stamp": signing["source_stamp"],
            "consistent": signing["consistent"],
            "block_pairs": signing["block_pairs"],
        },
        "host_taxonomy": intel.get("host_class_counts", {}),
        "host_taxonomy_detail": intel.get("host_taxonomy", {}),
        "supply_chain": {
            "libraries": intel["libraries"]["count"],
            "distinct_groups": intel["libraries"]["distinct_groups"],
            "top_groups": dict(Counter(r["group"] for r in intel["libraries"]["libraries"]).most_common(15)),
            "vendor_classes": intel["census"]["vendor_classes"],
            "unattributed": intel["census"]["unattributed_namespaces"],
            "integrity_hints": intel["integrity_hints"],
        },
        "assets": {
            "count": intel["assets"]["entries"],
            "classes": intel["assets"]["classes"],
            "models": intel["assets"]["models"],
            "js_bundles": intel["assets"]["js_bundles"],
            "hosts": intel["assets"]["hosts"][:80],
            "top_keys": dict(list(intel["assets"]["json_key_paths"].items())[:40]),
        },
        "findings": findings["summary"],
        "api_hits": dex["api_hits"],
        "opcode_hist": dex["opcode_hist"],
        "package_classes": dict(list(dex["package_classes"].items())[:30]),
        "edges": dex["edges"][:200],
        "strings_intel": {k: {"unique": v["unique"], "total": v["total"]}
                          for k, v in dex["strings_intel"]["kinds"].items()},
        "generated_at": time.strftime("%Y-%m-%dT%H:%M:%SZ", time.gmtime()),
        "tool_version": __import__("research.zalopay_re", fromlist=["__version__"]).__version__,
    }
    write_json("09_summary.json", s)
    return s


def run(fast: bool = False) -> None:
    t0 = time.time()
    zip_path = find_container()
    log(f"container: {os.path.basename(zip_path)}")
    xapk_dir = ensure_extracted(zip_path)
    container = stage_container(zip_path, xapk_dir)
    man = stage_manifest(xapk_dir, container["manifests"])
    res = stage_resources(xapk_dir)
    dex = stage_dex(xapk_dir, fast)
    if not fast:
        stage_classes(xapk_dir)
    native = stage_native(xapk_dir)
    signing = stage_signing(xapk_dir)
    intel = stage_intel(xapk_dir, dex, res)
    summary = build_summary(container, man, res, dex, native, signing, intel, {"summary": {}})
    all_data = {
        "manifest": {"base": man["base"], "per_apk": man["per_apk"]},
        "signing": signing, "dex": dex, "native": native, "resources": res,
        "intel": intel, "assets": intel["assets"], "inventory": container,
    }
    findings = stage_findings(all_data)
    summary["findings"] = findings["summary"]
    write_json("09_summary.json", summary)
    log(f"done in {time.time() - t0:.1f}s")
    try:
        from . import report
        report.build_all()
        log("reports regenerated")
    except Exception as exc:
        log(f"report stage skipped: {exc}")


def main(argv=None) -> int:
    ap = argparse.ArgumentParser(prog="zalopay_re")
    ap.add_argument("command", nargs="?", default="run", choices=["run", "report"])
    ap.add_argument("--fast", action="store_true", help="skip dense DEX/bytecode work")
    args = ap.parse_args(argv)
    if args.command == "report":
        from . import report
        report.build_all()
        return 0
    run(fast=args.fast)
    return 0


if __name__ == "__main__":
    sys.exit(main())
