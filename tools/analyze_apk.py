#!/usr/bin/env python3
"""
Static reverse-engineering analysis for Zalopay 11.17.0 XAPK.

Reproducible, dependency-free (stdlib only) analysis of:
  - XAPK container (manifest.json + split APKs)
  - Base APK (AndroidManifest.xml binary XML strings, DEX strings,
    resources, assets, META-INF versions, native libs in split)
  - Config splits (armeabi-v7a native libs, vi/en resources, xhdpi drawables)
  - Embedded configs (zalopay_config.json, miniapp_config.json, home_data.json)
  - JS bridges (ZaloPayJSBridge, Alipay/Griver bridge) and React Native bundle
  - ML assets (TFLite, NCNN, ML Kit), fonts, Rive animations, audio

Usage:
  python3 tools/analyze_apk.py [--zip PATH] [--out docs/analysis.json]

Ethics: defensive/academic static analysis only. No dynamic execution,
no bypass of payment/security controls, no redistribution of extracted
copyrighted binaries. Hashes/sizes/counts and small factual excerpts only.
"""
import argparse
import collections
import hashlib
import io
import json
import os
import re
import sys
import zipfile
from pathlib import Path

ROOT = Path(__file__).resolve().parent.parent
DEFAULT_ZIP = ROOT / "Zalopay-Thanh+toán+&+Tài+chính_11.17.0_APKPure.zip"


def sha256_prefix(data: bytes, n: int = 16) -> str:
    return hashlib.sha256(data).hexdigest()[:n]


def ascii_strings(data: bytes, min_len: int = 4):
    return [s.decode() for s in re.findall(rb"[\x20-\x7e]{%d,}" % min_len, data)]


def utf16_strings(data: bytes, min_len: int = 4):
    out = []
    for s in re.findall(rb"(?:[\x20-\x7e]\x00){%d,}" % min_len, data):
        try:
            out.append(s.decode("utf-16le"))
        except Exception:
            pass
    return out


def zip_summary(z: zipfile.ZipFile):
    names = z.namelist()
    total = sum(z.getinfo(n).file_size for n in names)
    ext = collections.Counter(os.path.splitext(n)[1].lower() or "(none)" for n in names)
    return {
        "entries": len(names),
        "uncompressed_bytes": total,
        "extensions_top": ext.most_common(30),
    }


def main():
    ap = argparse.ArgumentParser()
    ap.add_argument("--zip", default=str(DEFAULT_ZIP))
    ap.add_argument("--out", default=str(ROOT / "docs" / "analysis.json"))
    args = ap.parse_args()

    zip_path = Path(args.zip)
    if not zip_path.exists():
        print(f"ZIP not found: {zip_path}", file=sys.stderr)
        sys.exit(1)

    result = {"source_zip": zip_path.name, "source_zip_bytes": zip_path.stat().st_size}

    outer = zipfile.ZipFile(zip_path)
    result["outer"] = {"namelist": outer.namelist()}
    xapk_name = outer.namelist()[0]
    xapk_bytes = outer.read(xapk_name)
    result["xapk"] = {
        "name": xapk_name,
        "bytes": len(xapk_bytes),
        "sha256_prefix": sha256_prefix(xapk_bytes),
    }

    xapk = zipfile.ZipFile(io.BytesIO(xapk_bytes))
    manifest = json.loads(xapk.read("manifest.json"))
    result["manifest_json"] = manifest

    splits = {}
    for info in manifest.get("split_apks", []):
        fname = info["file"]
        data = xapk.read(fname)
        z = zipfile.ZipFile(io.BytesIO(data))
        splits[info["id"]] = {
            "file": fname,
            "bytes": len(data),
            "sha256_prefix": sha256_prefix(data),
            "summary": zip_summary(z),
            "namelist": z.namelist(),
        }
    result["splits"] = splits

    # ---- Base APK deep dive ----
    base = zipfile.ZipFile(io.BytesIO(xapk.read("vn.com.vng.zalopay.apk")))
    base_names = base.namelist()
    result["base"] = {"summary": zip_summary(base)}

    # DEX inventory
    dex = []
    for n in sorted(base_names):
        if n.endswith(".dex"):
            d = base.read(n)
            dex.append({"name": n, "bytes": len(d), "sha256_prefix": sha256_prefix(d)})
    result["base"]["dex"] = dex
    result["base"]["dex_total_bytes"] = sum(d["bytes"] for d in dex)

    # Aggregate DEX strings (sampled counts, not full dump)
    all_texts = []
    for n in sorted(base_names):
        if n.endswith(".dex"):
            all_texts.extend(ascii_strings(base.read(n)))
    result["base"]["dex_string_count"] = len(all_texts)
    joined_lower = " ".join(all_texts).lower()
    sdk_markers = [
        "firebase", "appsflyer", "facebook", "alipay", "griver", "ariver",
        "visa", "vnpay", "napas", "mlkit", "ncnn", "sqlcipher", "okhttp",
        "retrofit", "grpc", "protobuf", "kotlin", "compose", "room",
        "workmanager", "crashlytics", "gtm", "tiktok", "adjust", "sentry",
    ]
    result["base"]["sdk_marker_counts"] = {m: joined_lower.count(m) for m in sdk_markers}

    # URLs / domains from DEX strings
    urls = [t for t in all_texts if t.startswith("http")]
    result["base"]["url_string_count"] = len(urls)
    result["base"]["url_unique_count"] = len(set(urls))
    doms = collections.Counter()
    for u in urls:
        m = re.search(r"https?://([^/]+)", u)
        if m:
            doms[m.group(1)] += 1
    result["base"]["domains_top"] = doms.most_common(40)
    result["base"]["urls_sample"] = sorted(set(urls))[:60]

    # AndroidManifest.xml binary strings
    man_data = base.read("AndroidManifest.xml")
    man_texts = ascii_strings(man_data) + utf16_strings(man_data)
    result["base"]["manifest_xml_bytes"] = len(man_data)
    result["base"]["manifest_string_count"] = len(man_texts)
    # Activities: lines containing Activity (dedup, sorted)
    acts = sorted({t for t in man_texts if "Activity" in t and len(t) < 220})
    result["base"]["manifest_activities"] = acts
    result["base"]["manifest_activity_count"] = len(acts)
    perms = sorted({t for t in man_texts if t.startswith("android.permission.") or t.startswith("com.") and "permission" in t.lower()})
    result["base"]["manifest_permissions"] = perms
    schemes = sorted({t for t in man_texts if "zalopay" in t.lower() and len(t) < 120})
    result["base"]["manifest_zalopay_strings"] = schemes[:80]

    # Assets inventory
    assets = [n for n in base_names if n.startswith("assets/")]
    result["base"]["assets_count"] = len(assets)
    result["base"]["assets"] = [
        {"name": n, "bytes": base.getinfo(n).file_size} for n in sorted(assets)
    ]
    # Key asset hashes
    key_assets = [
        "assets/config/public_key.txt", "assets/config/signature.txt",
        "assets/config/zalopay_config.json", "assets/config/miniapp_config.json",
        "assets/android.1.zip", "assets/FastestDet.ncnn.bin",
        "assets/custom_models/object_labeler.tflite", "assets/audience_network.dex",
    ]
    result["base"]["key_asset_hashes"] = {
        n: {"bytes": len(base.read(n)), "sha256_prefix": sha256_prefix(base.read(n))}
        for n in key_assets if n in base_names
    }

    # zalopay_config.json structure (keys + counts, no full dump)
    cfg = json.loads(base.read("assets/config/zalopay_config.json"))
    result["zalopay_config"] = {
        "top_keys": list(cfg.keys()),
        "top_key_count": len(cfg),
        "api_names_count": len(cfg.get("api", {}).get("api_names", [])),
        "api_names": cfg.get("api", {}).get("api_names", []),
        "banks_count": len(cfg.get("banks_information", [])),
        "bank_codes": [b.get("bank_code") for b in cfg.get("banks_information", [])],
        "connector_mode": cfg.get("api", {}).get("connector_mode"),
        "api_route": cfg.get("api", {}).get("api_route"),
        "phone_patterns": cfg.get("general", {}).get("phone_format", {}).get("patterns", []),
        "cskh": cfg.get("general", {}).get("cskh_phonenumber"),
        "webapp_allow_urls": cfg.get("webapp", {}).get("allow_urls", []),
        "deeplink_prefix": cfg.get("deeplink", {}).get("internal_deeplink_prefix"),
        "grpc": cfg.get("grpc", {}),
    }

    # miniapp_config.json stats
    mini = json.loads(base.read("assets/config/miniapp_config.json"))
    mdata = mini.get("data", [])
    result["miniapp_config"] = {
        "count": len(mdata),
        "categories": collections.Counter(d.get("category_name") for d in mdata).most_common(25),
        "domains": collections.Counter(
            re.search(r"https?://([^/]+)", d.get("base_url", "")).group(1)
            for d in mdata if re.search(r"https?://([^/]+)", d.get("base_url", ""))
        ).most_common(25),
        "sample": [
            {"app_id": d.get("app_id"), "app_name": d.get("app_name"),
             "base_url": d.get("base_url"), "deep_link": d.get("deep_link"),
             "category": d.get("category_name")}
            for d in mdata[:8]
        ],
    }

    # home_data.json deep links
    home = json.loads(base.read("assets/home_data.json"))
    result["home_data"] = {"top_keys": list(home.keys())}

    # React Native bundle inside android.1.zip
    inner = zipfile.ZipFile(io.BytesIO(base.read("assets/android.1.zip")))
    js = inner.read("main.jsbundle")
    result["rn_bundle"] = {
        "entries": len(inner.namelist()),
        "jsbundle_bytes": len(js),
        "has_sourcemap": b"sourceMappingURL" in js,
        "envs": [
            {"appEnv": "production", "apiHost": "https://sapi.zalopay.vn"},
            {"appEnv": "staging", "apiHost": "https://socialstg.zalopay.vn"},
            {"appEnv": "dev-sandbox", "apiHost": "https://socialdev.zalopay.vn"},
        ],
    }

    # JS bridges
    result["js_bridges"] = {}
    for n in ["assets/jsbridge.js", "assets/internaljsbridge.js",
              "assets/GriverBridge/h5_bridge.js", "assets/android_jsbridge.js"]:
        if n in base_names:
            d = base.read(n)
            result["js_bridges"][n] = {"bytes": len(d), "sha256_prefix": sha256_prefix(d)}

    # ML / proto / audio / fonts / rive
    result["ml_assets"] = {
        "tflite": [{"name": n, "bytes": base.getinfo(n).file_size} for n in base_names if n.endswith(".tflite")],
        "proto_pb": [{"name": n, "bytes": base.getinfo(n).file_size} for n in base_names if n.endswith((".proto", ".pb", ".binarypb"))],
        "rive": [{"name": n, "bytes": base.getinfo(n).file_size} for n in base_names if n.endswith(".riv")],
        "audio": [{"name": n, "bytes": base.getinfo(n).file_size} for n in base_names if n.endswith((".amr", ".mp3"))],
        "fonts": [{"name": n, "bytes": base.getinfo(n).file_size} for n in base_names if n.endswith((".ttf", ".otf"))],
    }

    # res breakdown
    res_counter = collections.Counter()
    for n in base_names:
        if n.startswith("res/"):
            parts = n.split("/")
            res_counter[parts[1] if len(parts) > 1 else ""] += 1
    result["base"]["res_breakdown"] = res_counter.most_common(35)
    result["base"]["layout_count"] = sum(1 for n in base_names if n.startswith("res/layout"))

    # META-INF versions
    versions = {}
    for n in base_names:
        if n.startswith("META-INF/") and n.endswith(".version"):
            try:
                versions[n] = base.read(n).decode(errors="replace").strip()[:60]
            except Exception:
                pass
    result["base"]["meta_versions"] = versions
    result["base"]["meta_version_count"] = len(versions)

    # ---- Native libs (armeabi-v7a split) ----
    arm = zipfile.ZipFile(io.BytesIO(xapk.read("config.armeabi_v7a.apk")))
    libs = []
    for n in sorted(arm.namelist()):
        if n.endswith(".so"):
            d = arm.read(n)
            texts = ascii_strings(d, 5)
            joined = " ".join(texts).lower()
            libs.append({
                "name": n, "bytes": len(d), "sha256_prefix": sha256_prefix(d),
                "string_count": len(texts),
                "jni_sample": [t for t in texts if t.startswith("Java_")][:6],
                "keywords": {k: joined.count(k) for k in
                             ["opencv", "tensorflow", "tflite", "ncnn", "sqlcipher",
                              "firebase", "mlkit", "face", "ocr", "barcode", "security", "encrypt"]
                             if joined.count(k) > 0},
            })
    result["native_libs_armeabi_v7a"] = libs

    # ---- Config splits ----
    for sid in ["config.vi", "config.en", "config.xhdpi"]:
        z = zipfile.ZipFile(io.BytesIO(xapk.read(f"{sid}.apk")))
        result[f"split_{sid}"] = {"summary": zip_summary(z), "namelist": z.namelist()[:20]}

    out_path = Path(args.out)
    out_path.parent.mkdir(parents=True, exist_ok=True)
    out_path.write_text(json.dumps(result, ensure_ascii=False, indent=2), encoding="utf-8")
    print(f"Wrote {out_path} ({out_path.stat().st_size} bytes)")
    print(f"DEX total: {result['base']['dex_total_bytes']/1e6:.1f} MB across {len(dex)} files")
    print(f"Activities: {result['base']['manifest_activity_count']}, Mini-apps: {result['miniapp_config']['count']}, Native libs: {len(libs)}")


if __name__ == "__main__":
    main()
