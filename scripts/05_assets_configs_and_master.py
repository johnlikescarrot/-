#!/usr/bin/env python3
"""
Analyzes remaining assets (META-INF versions, ML models, Griver mini-apps,
React Native bundle, Baseline Profiles, Network Security Config XML, Lottie/media)
and synthesizes the unified data/master_metrics.json benchmark dataset.
Strictly non-exploitative: redacts any key/certificate/token strings.
"""
import json
import re
from collections import Counter, defaultdict
from pathlib import Path

from androguard.core.axml import AXMLPrinter

ROOT = Path("/home/user/-")
BASE_APK = ROOT / "tmp_work/apks/vn.com.vng.zalopay"
NESTED = ROOT / "tmp_work/nested"


def analyze_androidx_versions() -> dict:
    meta_dir = BASE_APK / "META-INF"
    versions = {}
    groups = Counter()
    for p in sorted(meta_dir.glob("*.version")):
        val = p.read_text(errors="replace").strip().splitlines()[0] if p.stat().st_size > 0 else ""
        name = p.stem
        versions[name] = val
        prefix = name.split("_")[0]
        groups[prefix] += 1
    compose_libs = {k: v for k, v in versions.items() if "compose" in k}
    camera_libs = {k: v for k, v in versions.items() if "camera" in k}
    return {
        "total_version_files": len(versions),
        "group_counts_top15": dict(groups.most_common(15)),
        "jetpack_compose_versions": compose_libs,
        "camerax_versions": camera_libs,
        "all_versions": versions,
    }


def analyze_ml_models() -> dict:
    ml_exts = {".tflite", ".tfl", ".bin", ".param", ".emd", ".fb", ".binarypb", ".conv_model", ".lstm_model", ".pb"}
    models = []
    by_engine = defaultdict(lambda: {"count": 0, "bytes": 0})
    for p in sorted(BASE_APK.rglob("*")):
        if not p.is_file():
            continue
        rel = str(p.relative_to(BASE_APK))
        if rel == "DebugProbesKt.bin":
            continue
        ext = p.suffix.lower()
        is_lbf = rel == "res/raw/lbfmodel.yaml"
        if ext in ml_exts or is_lbf or "mlkit" in rel.lower() or "models_bundled" in rel.lower():
            sz = p.stat().st_size
            if "mlkit-google-ocr" in rel:
                eng = "Google ML Kit OCR (GOCR / Taser / Aksara)"
            elif "mlkit_barcode" in rel:
                eng = "Google ML Kit Barcode SSD"
            elif "mlkit_odt" in rel or "custom_models" in rel:
                eng = "Google ML Kit Object Detection & Custom TFLite"
            elif "models_bundled" in rel or is_lbf:
                eng = "Face Detection / Liveness / OpenCV LBF"
            elif "ncnn" in rel.lower():
                eng = "Tencent NCNN (FastestDet)"
            else:
                eng = "Other ML / Protobuf Metadata"
            by_engine[eng]["count"] += 1
            by_engine[eng]["bytes"] += sz
            models.append({"path": rel, "bytes": sz, "engine": eng})
    return {
        "total_model_artifacts": len(models),
        "total_model_bytes": sum(m["bytes"] for m in models),
        "by_engine": dict(sorted(by_engine.items(), key=lambda x: -x[1]["bytes"])),
        "largest_models": sorted(models, key=lambda m: -m["bytes"])[:20],
    }


def analyze_hybrid_containers() -> dict:
    # React Native main.jsbundle + drawable node_modules packages
    rn_dir = NESTED / "android_1_zip"
    rn_bundle = rn_dir / "main.jsbundle"
    rn_text = rn_bundle.read_text(errors="replace")
    rn_ver_match = re.search(r"react-native[^0-9]*([0-9]+\.[0-9]+\.[0-9]+)", rn_text)
    node_pkg_counts = Counter()
    for p in rn_dir.rglob("node_modules_*"):
        parts = p.name.split("_")
        if len(parts) >= 3:
            node_pkg_counts[parts[2]] += 1
    feature_prefixes = Counter()
    for p in rn_dir.rglob("*.png"):
        if not p.name.startswith("node_modules_"):
            feature_prefixes[p.name.split("_")[0]] += 1

    # Griver mini-programs
    griver_apps = {}
    for gdir in sorted(NESTED.glob("griver_*")):
        app_id = gdir.name
        mxml = (gdir / "Manifest.xml").read_text(errors="replace") if (gdir / "Manifest.xml").exists() else ""
        cert = json.loads((gdir / "CERT.json").read_text()) if (gdir / "CERT.json").exists() else {}
        tar_files = list((gdir / "tar_extracted").rglob("*"))
        tar_file_list = [
            {"path": str(f.relative_to(gdir / "tar_extracted")), "bytes": f.stat().st_size}
            for f in tar_files if f.is_file()
        ]
        griver_apps[app_id] = {
            "manifest_xml_bytes": len(mxml),
            "cert_keys": list(cert.keys()),
            "tar_file_count": len(tar_file_list),
            "tar_total_bytes": sum(x["bytes"] for x in tar_file_list),
            "tar_files": sorted(tar_file_list, key=lambda x: -x["bytes"])[:15],
        }

    # miniapp_config.json structural summary (no secrets)
    mc_path = BASE_APK / "assets/config/miniapp_config.json"
    mc_summary = {}
    if mc_path.exists():
        mc = json.loads(mc_path.read_text(errors="replace"))
        if isinstance(mc, dict):
            mc_summary = {
                "size_bytes": mc_path.stat().st_size,
                "top_keys": list(mc.keys()),
                "list_counts": {k: len(v) for k, v in mc.items() if isinstance(v, (list, dict))},
            }
        elif isinstance(mc, list):
            mc_summary = {
                "size_bytes": mc_path.stat().st_size,
                "root_type": "list",
                "entry_count": len(mc),
                "sample_item_keys": list(mc[0].keys()) if mc and isinstance(mc[0], dict) else [],
            }

    return {
        "react_native_bundle": {
            "bundle_bytes": rn_bundle.stat().st_size,
            "bundle_lines": rn_text.count("\n") + 1,
            "detected_rn_version_hint": rn_ver_match.group(1) if rn_ver_match else "minified/hermes-jsc",
            "__d_module_definitions_count": len(re.findall(r"__d\(function", rn_text)),
            "asset_node_modules_packages": dict(node_pkg_counts.most_common(25)),
            "asset_feature_modules": dict(feature_prefixes.most_common(20)),
        },
        "griver_mini_programs": griver_apps,
        "miniapp_config_schema": mc_summary,
    }


def analyze_network_security_configs() -> list:
    findings = []
    for p in sorted((BASE_APK / "res/xml").glob("*.xml")):
        raw = p.read_bytes()
        if raw.startswith(b"\x03\x00\x08\x00"):
            try:
                xml_str = AXMLPrinter(raw).get_xml().decode("utf-8", "replace")
                if "network-security-config" in xml_str:
                    domains = re.findall(r"<domain[^>]*>([^<]+)</domain>", xml_str)
                    cleartext_flags = re.findall(r'cleartextTrafficPermitted="(true|false)"', xml_str)
                    pin_sets = len(re.findall(r"<pin-set", xml_str))
                    pins = len(re.findall(r"<pin\b", xml_str))
                    findings.append({
                        "file": str(p.relative_to(BASE_APK)),
                        "cleartext_flags": cleartext_flags,
                        "domain_count": len(domains),
                        "domains_sample": domains[:15],
                        "pin_set_count": pin_sets,
                        "pin_digest_count": pins,
                    })
            except Exception:
                pass
    return findings


def main():
    androidx = analyze_androidx_versions()
    ml = analyze_ml_models()
    hybrid = analyze_hybrid_containers()
    nsc = analyze_network_security_configs()

    # Check Lottie animations & shielding data files
    shield_dats = []
    for p in sorted((BASE_APK / "assets").glob("*.dat")):
        shield_dats.append({"path": str(p.relative_to(BASE_APK)), "bytes": p.stat().st_size})

    assets_report = {
        "androidx_jetpack_versions": androidx,
        "ml_vision_pipeline": ml,
        "hybrid_containers": hybrid,
        "network_security_configs": nsc,
        "shield_dat_payloads": shield_dats,
    }
    (ROOT / "data/assets_analysis.json").write_text(json.dumps(assets_report, indent=2, ensure_ascii=False))

    # Build unified master_metrics.json for paper & TypeScript video engine
    inv = json.loads((ROOT / "data/full_file_inventory.json").read_text())
    rea = json.loads((ROOT / "data/rea_summary.json").read_text())
    man = json.loads((ROOT / "data/manifest_analysis.json").read_text())
    dex = json.loads((ROOT / "data/dex_analysis.json").read_text())
    elf = json.loads((ROOT / "data/native_elf_analysis.json").read_text())

    base_man = man["manifests"]["vn.com.vng.zalopay"]
    base_sig = inv["apk_summaries"]["vn.com.vng.zalopay.apk"]["signing"]

    master = {
        "study_metadata": {
            "target_app": "ZaloPay (Thanh toán & Tài chính)",
            "package_name": base_man["package"],
            "version_name": base_man["versionName"],
            "version_code": int(base_man["versionCode"]),
            "min_sdk": int(base_man["minSdkVersion"]),
            "target_sdk": int(base_man["targetSdkVersion"]),
            "compile_sdk": int(base_man["compileSdkVersion"]),
            "source_archive": inv["top_level"]["outer_zip"]["filename"],
            "source_archive_sha256": inv["top_level"]["outer_zip"]["sha256"],
            "xapk_sha256": inv["top_level"]["xapk_container"]["sha256"],
            "rea_version": rea["rea_tool"],
        },
        "container_scale": {
            "outer_zip_bytes": inv["top_level"]["outer_zip"]["size_bytes"],
            "xapk_bytes": inv["top_level"]["xapk_container"]["size_bytes"],
            "split_apk_count": inv["aggregate_totals"]["split_apk_count"],
            "total_apk_entries": inv["aggregate_totals"]["total_apk_entries"],
            "nested_container_files": inv["aggregate_totals"]["nested_container_files_count"],
            "total_analyzed_artifacts": inv["aggregate_totals"]["total_apk_entries"] + inv["aggregate_totals"]["nested_container_files_count"],
            "unique_sha256_count": inv["aggregate_totals"]["unique_content_sha256_count"],
            "duplicate_groups": inv["aggregate_totals"]["duplicate_sha256_groups"],
            "duplicate_wasted_bytes": inv["aggregate_totals"]["duplicate_wasted_bytes"],
            "total_uncompressed_bytes": inv["aggregate_totals"]["total_uncompressed_bytes"],
            "total_compressed_bytes": inv["aggregate_totals"]["total_compressed_bytes"],
            "split_apks": [
                {
                    "name": k,
                    "apk_bytes": v["apk_size_bytes"],
                    "entries": v["entry_count"],
                    "uncompressed_bytes": v["total_uncompressed_bytes"],
                    "avg_entropy": v["avg_entropy"],
                    "signing_schemes": v["signing"]["apk_signing_block"]["schemes_detected"],
                }
                for k, v in inv["apk_summaries"].items()
            ],
            "categories": [
                {
                    "category": k,
                    "count": v["count"],
                    "uncompressed_bytes": v["uncompressed_bytes"],
                    "share_pct": round(100 * v["uncompressed_bytes"] / inv["aggregate_totals"]["total_uncompressed_bytes"], 2),
                }
                for k, v in inv["category_breakdown"].items()
            ],
        },
        "signing_integrity": {
            "schemes": base_sig["apk_signing_block"]["schemes_detected"],
            "v1_signer": base_sig["v1_jar_signing"].get("certificates", [{}])[0],
            "rea_graph_sha256_base_apk": rea["containers"].get("vn.com.vng.zalopay.apk", {}).get("graph_sha256"),
            "rea_integrity_contradictions": sum(
                len(v.get("integrity_contradictions", [])) for v in rea["containers"].values() if v.get("ok")
            ),
        },
        "manifest_surface": {
            "permissions_total": base_man["permissions_total"],
            "permissions_by_category": base_man["permissions_by_category"],
            "components": base_man["component_summary"],
            "isolated_processes": base_man["isolated_processes"],
            "deep_link_schemes": base_man["deep_link_schemes"],
            "allow_backup": base_man["application_security_flags"]["allowBackup"],
            "application_entrypoint": base_man["application_security_flags"]["name"],
            "network_security_configs": nsc,
        },
        "dex_architecture": {
            "total_dex_files": dex["total_dex_files"],
            "total_dex_bytes": dex["total_dex_bytes"],
            "aggregate": dex["aggregate_counts"],
            "sdk_distribution": [
                {
                    "ecosystem": k,
                    "classes": v,
                    "share_pct": round(100 * v / dex["aggregate_counts"]["total_class_defs"], 2),
                }
                for k, v in dex["sdk_ecosystem_class_distribution"].items()
            ],
            "per_dex": dex["per_dex_metrics"],
        },
        "native_elf_hardening": {
            "summary": elf["summary"],
            "libraries": [
                {
                    "name": lib["filename"],
                    "category": lib["category"],
                    "bytes": lib["size_bytes"],
                    "entropy": lib["whole_file_entropy"],
                    "pie": lib["hardening"]["pie_et_dyn"],
                    "nx": lib["hardening"]["nx_non_exec_stack"],
                    "relro": lib["hardening"]["relro"],
                    "stack_canary": lib["hardening"]["stack_canary"],
                    "fortify": lib["hardening"]["fortify_source"],
                    "stripped": lib["hardening"]["stripped_symtab"],
                    "jni_exports": lib["jni_exported_functions"],
                    "dt_needed": lib["dt_needed"],
                    "compiler": lib["compiler_comment"],
                }
                for lib in elf["libraries"]
            ],
            "assets_trueidlib_stub": elf["assets_trueidlib_so_anomaly"],
        },
        "hybrid_and_ai_stack": {
            "androidx_version_manifests": androidx["total_version_files"],
            "ml_total_artifacts": ml["total_model_artifacts"],
            "ml_total_bytes": ml["total_model_bytes"],
            "ml_by_engine": ml["by_engine"],
            "react_native": hybrid["react_native_bundle"],
            "griver_mini_programs": hybrid["griver_mini_programs"],
            "miniapp_config_schema": hybrid["miniapp_config_schema"],
            "shield_dat_payloads": shield_dats,
        },
    }

    (ROOT / "data/master_metrics.json").write_text(json.dumps(master, indent=2, ensure_ascii=False))
    print("Wrote data/assets_analysis.json and data/master_metrics.json")
    print("ML total bytes:", ml["total_model_bytes"], "| Artifacts:", ml["total_model_artifacts"])
    print("RN modules:", hybrid["react_native_bundle"]["__d_module_definitions_count"])
    print("NSC:", nsc)


if __name__ == "__main__":
    main()
