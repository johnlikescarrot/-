#!/usr/bin/env python3
"""Deep static analysis of JS bridges, React Native legacy/hybrid bundle, Griver .amr containers, ML/CV models, and asset configs."""

import io
import json
import re
import struct
import tarfile
import zipfile
from collections import Counter
from pathlib import Path

from cryptography.hazmat.primitives import serialization

REPO_ROOT = Path.cwd()
BASE_DIR = Path("/tmp/zalopay_corpus/apks/vn.com.vng.zalopay")
ASSETS_DIR = BASE_DIR / "assets"
RAW_DIR = BASE_DIR / "res/raw"
OUT_DIR = REPO_ROOT / "data"

dex_data = json.loads((OUT_DIR / "dex_ecology.json").read_text(encoding="utf-8"))
rea_js = json.loads((REPO_ROOT / "evidence/rea_js_bridge.json").read_text(encoding="utf-8"))

# 1. Verify React Native presence in DEX vs Fresco
fb_packages = [p for p in dex_data["top_150_packages"] if p["package"].startswith("com.facebook")]
has_fb_react_in_prefixes = any(
    "facebook.react" in p["prefix"] or "facebook.hermes" in p["prefix"]
    for p in dex_data["top_120_prefixes"]
)

# 2. Analyze assets/android.1.zip & main.jsbundle Metro modules
rn_zip_path = ASSETS_DIR / "android.1.zip"
with zipfile.ZipFile(rn_zip_path) as zrn:
    rn_infos = [i for i in zrn.infolist() if not i.is_dir()]
    bundle_bytes = zrn.read("main.jsbundle").decode("utf-8", errors="replace")
    # Count Metro __d(function(...){...}, <id>, [...]) module definitions
    metro_ids = [int(m) for m in re.findall(r"__d\(function\([^)]*\)\{.*?\},(\d+),\[", bundle_bytes)]
    # Actually match tail indices safely using __d(...,ID,[ pattern
    metro_entries = re.findall(r"\},(\d+),\[([^\]]*)\]\);", bundle_bytes)
    entry_requires = re.findall(r"__r\((\d+)\);", bundle_bytes[-500:])
    rn_drawable_dirs = Counter(Path(i.filename).parts[0] for i in rn_infos if "/" in i.filename)
    rn_asset_modules = Counter()
    for i in rn_infos:
        fn = Path(i.filename).name
        if fn.endswith(".png"):
            prefix = fn.split("_")[0]
            rn_asset_modules[prefix] += 1

android1_summary = {
    "archive_bytes": rn_zip_path.stat().st_size,
    "member_file_count": len(rn_infos),
    "main_jsbundle_bytes": len(bundle_bytes.encode("utf-8")),
    "metro_module_definitions_detected": len(metro_entries),
    "max_metro_module_id": max((int(m[0]) for m in metro_entries), default=None),
    "entry_require_ids": [int(x) for x in entry_requires],
    "timestamp_sample": f"{rn_infos[0].date_time[0]:04d}-{rn_infos[0].date_time[1]:02d}-{rn_infos[0].date_time[2]:02d}",
    "drawable_buckets": dict(rn_drawable_dirs),
    "top_asset_feature_prefixes": dict(rn_asset_modules.most_common(15)),
    "bundle_json": json.loads((ASSETS_DIR / "bundle.json").read_text(encoding="utf-8")),
    "facebook_packages_in_dex_top150": fb_packages,
    "has_facebook_react_prefix_in_dex": has_fb_react_in_prefixes,
}

# 3. Analyze Griver / Alipay+ mini-app runtime & .amr containers + miniapp_config.json
amr_summaries = []
for amr in sorted((ASSETS_DIR / "Griver").glob("*.amr")):
    with zipfile.ZipFile(amr) as zf:
        manifest_txt = zf.read("Manifest.xml").decode("utf-8", errors="replace") if "Manifest.xml" in zf.namelist() else ""
        cert_json = json.loads(zf.read("CERT.json").decode("utf-8")) if "CERT.json" in zf.namelist() else {}
        tar_files = []
        for n in zf.namelist():
            if n.endswith(".tar"):
                with tarfile.open(fileobj=io.BytesIO(zf.read(n))) as tf:
                    for m in tf.getmembers():
                        if m.isfile():
                            tar_files.append({"path": m.name, "bytes": m.size})
        amr_summaries.append({
            "file": amr.name,
            "zip_bytes": amr.stat().st_size,
            "cert_signed_entries": list(cert_json.keys()),
            "cert_sig_b64_length": len(next(iter(cert_json.values()))) if cert_json else 0,
            "manifest_xml": manifest_txt.strip(),
            "tar_member_count": len(tar_files),
            "tar_uncompressed_bytes": sum(x["bytes"] for x in tar_files),
            "tar_members": tar_files,
        })

miniapp_cfg = json.loads((ASSETS_DIR / "config/miniapp_config.json").read_text(encoding="utf-8"))
miniapps_list = miniapp_cfg.get("data", []) if isinstance(miniapp_cfg, dict) else []
miniapp_cats = Counter(item.get("category_name") or "(uncategorized)" for item in miniapps_list)
miniapp_hosts = Counter()
for item in miniapps_list:
    bu = item.get("base_url", "")
    m = re.match(r"https?://([^/]+)", bu)
    if m:
        miniapp_hosts[m.group(1)] += 1

griver_summary = {
    "rea_js_evidence_id": rea_js["evidence_id"],
    "rea_js_sources_analyzed": [s["key"] for s in rea_js["discovered_sources"]],
    "rea_js_graph": rea_js["application_graph_summary"],
    "rea_js_semantic_families": rea_js["semantic_graph_summary"]["families"],
    "appx_config": json.loads((ASSETS_DIR / "Griver/appx.json").read_text(encoding="utf-8")),
    "amr_containers": amr_summaries,
    "miniapp_catalog": {
        "config_file_bytes": (ASSETS_DIR / "config/miniapp_config.json").stat().st_size,
        "miniapp_count": len(miniapps_list),
        "categories": dict(miniapp_cats.most_common()),
        "top_base_hosts": dict(miniapp_hosts.most_common(12)),
    },
}

# 4. Inspect ALL on-device ML / Computer Vision models (.tflite, .ncnn.*, OpenCV XML/YAML)
tflite_models = []
for p in sorted(ASSETS_DIR.rglob("*.tflite")):
    raw = p.read_bytes()
    magic = raw[4:8].decode("ascii", errors="replace") if len(raw) >= 8 else ""
    # Extract readable operator/layer strings inside FlatBuffer
    ops = sorted(set(re.findall(rb"[A-Z][A-Z0-9_]{3,35}", raw)))
    op_strs = [o.decode("ascii") for o in ops if any(k in o for k in (b"CONV", b"POOL", b"SOFTMAX", b"ADD", b"MUL", b"RESHAPE", b"CONCAT", b"RESIZE", b"QUANT", b"DEQUANT", b"PAD", b"LOGISTIC", b"LSTM", b"TFL", b"DETECTION"))]
    tflite_models.append({
        "path": str(p.relative_to(BASE_DIR)),
        "bytes": len(raw),
        "magic": magic,
        "detected_op_tokens": op_strs[:25],
    })

ncnn_param_txt = (ASSETS_DIR / "FastestDet.ncnn.param").read_text(encoding="utf-8", errors="replace")
ncnn_lines = [ln.strip() for ln in ncnn_param_txt.splitlines() if ln.strip()]
ncnn_header = ncnn_lines[1].split() if len(ncnn_lines) > 1 else []
ncnn_layer_types = Counter(ln.split()[0] for ln in ncnn_lines[2:] if ln.split())

ml_cv_summary = {
    "tflite_model_count": len(tflite_models),
    "tflite_total_bytes": sum(m["bytes"] for m in tflite_models),
    "tflite_models": tflite_models,
    "ncnn_fastestdet": {
        "bin_bytes": (ASSETS_DIR / "FastestDet.ncnn.bin").stat().st_size,
        "param_bytes": (ASSETS_DIR / "FastestDet.ncnn.param").stat().st_size,
        "declared_layers": int(ncnn_header[0]) if len(ncnn_header) >= 1 and ncnn_header[0].isdigit() else None,
        "declared_blobs": int(ncnn_header[1]) if len(ncnn_header) >= 2 and ncnn_header[1].isdigit() else None,
        "layer_type_counts": dict(ncnn_layer_types.most_common()),
    },
    "opencv_classical_models": [
        {"path": "res/raw/haarcascade_frontalface_default.xml", "bytes": (RAW_DIR / "haarcascade_frontalface_default.xml").stat().st_size},
        {"path": "res/raw/lbfmodel.yaml", "bytes": (RAW_DIR / "lbfmodel.yaml").stat().st_size},
    ],
}

# 5. Inspect res/raw animations & audio + cryptographic public keys in assets/
raw_lottie = sorted(p.name for p in RAW_DIR.glob("*.json"))
raw_rive = sorted(p.name for p in RAW_DIR.glob("*.riv"))
raw_audio = sorted(p.name for p in RAW_DIR.glob("*.mp3"))
raw_svg = sorted(p.name for p in RAW_DIR.glob("*.svg"))

pub_keys = []
for kf in ("public.pem", "zoom.public"):
    kp = ASSETS_DIR / kf
    if kp.exists():
        txt = kp.read_text(encoding="utf-8", errors="replace")
        try:
            key = serialization.load_pem_public_key(txt.encode("utf-8"))
            pub_keys.append({
                "file": kf,
                "bytes": kp.stat().st_size,
                "key_type": type(key).__name__,
                "key_size_bits": getattr(key, "key_size", None),
            })
        except Exception as e:
            pub_keys.append({"file": kf, "bytes": kp.stat().st_size, "parse_note": str(e)})

# 6. Inspect Shield / Obfuscated companion assets (entropy + structural fingerprint only, no key/secret extraction)
companion_blobs = []
for rel in ("assets/fgcpkjvqe.dat", "assets/taibmcf.dat", "assets/bshield.dat", "assets/trueidlib.so", "assets/taibmcf.json", "assets/vdfmoer.json"):
    p = BASE_DIR / rel
    if p.exists():
        raw = p.read_bytes()
        companion_blobs.append({
            "path": rel,
            "bytes": len(raw),
            "head_hex": raw[:16].hex(),
            "is_ascii_json": rel.endswith(".json"),
        })

out = {
    "react_native_hybrid_analysis": android1_summary,
    "griver_alipay_superapp": griver_summary,
    "ml_and_computer_vision": ml_cv_summary,
    "res_raw_multimedia": {
        "lottie_json_count": len(raw_lottie),
        "rive_count": len(raw_rive),
        "rive_files": raw_rive,
        "audio_count": len(raw_audio),
        "audio_files": raw_audio,
        "svg_count": len(raw_svg),
        "svg_files": raw_svg,
    },
    "public_key_assets": pub_keys,
    "companion_shield_assets": companion_blobs,
}

(OUT_DIR / "assets_and_ml.json").write_text(json.dumps(out, indent=2), encoding="utf-8")
print("=== ASSETS, JS BRIDGE, MINI-APP & ML SUMMARY ===")
print("React Native android.1.zip:", {k: android1_summary[k] for k in ("archive_bytes", "member_file_count", "main_jsbundle_bytes", "metro_module_definitions_detected", "max_metro_module_id", "timestamp_sample", "has_facebook_react_prefix_in_dex")})
print("Griver AMR packages:", [(a["file"], a["tar_member_count"], a["tar_uncompressed_bytes"]) for a in amr_summaries])
print("Miniapp catalog count:", griver_summary["miniapp_catalog"]["miniapp_count"], "categories:", griver_summary["miniapp_catalog"]["categories"])
print("TFLite models:", ml_cv_summary["tflite_model_count"], "total bytes:", ml_cv_summary["tflite_total_bytes"])
print("NCNN FastestDet:", ml_cv_summary["ncnn_fastestdet"])
print("Public keys:", pub_keys)
