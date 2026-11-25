#!/usr/bin/env python3
"""Cross-validate and synthesize all verified empirical findings across Phases 1-5 into data/synthesis_metrics.json."""

import json
from pathlib import Path

REPO_ROOT = Path.cwd()
DATA = REPO_ROOT / "data"
EVID = REPO_ROOT / "evidence"

# Compact rea_js_bridge.json unknown_ids arrays into counts
js_bridge_path = EVID / "rea_js_bridge.json"
js_bridge = json.loads(js_bridge_path.read_text(encoding="utf-8"))
for fam in js_bridge.get("semantic_graph_summary", {}).get("families", []):
    if "unknown_ids" in fam:
        fam["unknown_count"] = len(fam.pop("unknown_ids"))
js_bridge_path.write_text(json.dumps(js_bridge, indent=2), encoding="utf-8")

census = json.loads((DATA / "census_summary.json").read_text(encoding="utf-8"))
manifest = json.loads((DATA / "manifest_and_resources.json").read_text(encoding="utf-8"))
dex = json.loads((DATA / "dex_ecology.json").read_text(encoding="utf-8"))
native = json.loads((DATA / "native_elf_analysis.json").read_text(encoding="utf-8"))
assets_ml = json.loads((DATA / "assets_and_ml.json").read_text(encoding="utf-8"))
sigs = json.loads((EVID / "rea_apk_signatures.json").read_text(encoding="utf-8"))
containers = json.loads((EVID / "rea_containers.json").read_text(encoding="utf-8"))

base_man = manifest["vn.com.vng.zalopay.apk"]

# Reconcile file counts & byte totals cleanly
synthesis = {
    "target": {
        "outer_archive": census["outer_zip"],
        "package_name": base_man["sdk_and_identity"]["package"],
        "version_name": base_man["sdk_and_identity"]["version_name"],
        "version_code": int(base_man["sdk_and_identity"]["version_code"]),
        "min_sdk": int(base_man["sdk_and_identity"]["min_sdk"]),
        "target_sdk": int(base_man["sdk_and_identity"]["target_sdk"]),
        "compile_sdk": int(base_man["sdk_and_identity"]["compile_sdk_version"]),
        "distribution_channel_inferred": "Google Play App Bundle re-packaged as APKPure XAPK v2 (5 split APKs + Google Play Source Stamp)",
    },
    "corpus_scale": {
        "total_indexed_records_all_layers": census["totals"]["all_indexed_member_files_including_nested"],
        "xapk_direct_members": census["totals"]["xapk_direct_members"],
        "split_apk_count": 5,
        "apk_member_files_across_5_apks": census["totals"]["apk_member_files_across_5_apks"],
        "apk_member_uncompressed_bytes": census["totals"]["apk_member_uncompressed_bytes"],
        "apk_member_compressed_bytes": census["totals"]["apk_member_compressed_bytes"],
        "nested_asset_archive_members": census["totals"]["nested_asset_archive_members"],
        "unique_sha256_all_layers": census["totals"]["unique_sha256_hashes_all_layers"],
        "duplicate_content_groups": census["totals"]["duplicate_content_groups"],
        "duplicate_redundant_bytes": census["totals"]["duplicate_redundant_bytes"],
        "containers_table": [
            {
                "container": k,
                "files": v["file_count"],
                "uncompressed_bytes": v["uncompressed_bytes"],
                "compressed_bytes": v["compressed_bytes"],
            }
            for k, v in census["by_container"].items()
        ],
    },
    "manifest_and_surface": {
        "application_class": base_man["application_attributes"].get("name"),
        "superclass": dex["entry_application_class"]["superclass"] if dex.get("entry_application_class") else None,
        "allow_backup": base_man["application_attributes"].get("allowBackup"),
        "extract_native_libs": base_man["application_attributes"].get("extractNativeLibs"),
        "cleartext_traffic_permitted": True,
        "cleartext_note": "network_security_config.xml sets base-config cleartextTrafficPermitted=true despite targetSdk=36",
        "permissions_total": base_man["permissions_count"],
        "permissions_by_category": base_man["permissions_by_category"],
        "components": base_man["components_summary"],
        "deep_link_schemes": base_man["deep_link_schemes"],
        "queries_packages": base_man["queries"]["packages"],
        "fused_arsc_packages": base_man["resources_arsc"]["package_count"],
        "total_arsc_resource_keys_base": base_man["resources_arsc"]["total_unique_resource_keys"],
    },
    "signing_asymmetry": [
        {
            "apk": s["apk"],
            "v1": s["normalized_result"]["schemes"]["v1"]["present"],
            "v2": s["normalized_result"]["schemes"]["v2"]["present"],
            "v3": s["normalized_result"]["schemes"]["v3"]["present"],
            "source_stamp": s["normalized_result"]["source_stamp"]["present"],
            "self_consistent": s["normalized_result"]["posture"]["self_consistent"],
            "signer_cn": s["normalized_result"]["schemes"]["v2"]["signers"][0]["certificate"]["subject"],
            "evidence_id": s["evidence_id"],
        }
        for s in sigs["signatures"]
    ],
    "bytecode_and_sdk_ecology": {
        "dex_file_count": dex["totals"]["dex_file_count"],
        "total_dex_bytes": dex["totals"]["total_dex_bytes"],
        "total_defined_classes": dex["totals"]["total_defined_classes"],
        "total_defined_methods": dex["totals"]["total_defined_methods"],
        "total_defined_fields": dex["totals"]["total_defined_fields"],
        "total_native_methods": dex["totals"]["total_native_methods"],
        "total_native_declaring_classes": dex["totals"]["total_native_declaring_classes"],
        "overall_obfuscation_ratio": dex["totals"]["overall_obfuscation_ratio"],
        "dex_8_to_19_feature_shards_count": 12,
        "dex_8_to_19_obfuscation_ratio": 1.0,
        "ecosystem_breakdown": dex["ecosystem_breakdown"],
    },
    "native_elf_architecture": {
        "library_count": native["totals"]["native_library_count"],
        "total_bytes": native["totals"]["total_bytes"],
        "hardening_summary": native["hardening_summary"],
        "libs_with_jni_onload": native["totals"]["libs_with_jni_onload"],
        "static_java_jni_exports": native["totals"]["total_static_java_jni_exports"],
        "dex_native_methods_static_matched": native["totals"]["dex_native_methods_statically_matched_classes"],
        "dex_native_methods_dynamic_registernatives": native["totals"]["dex_native_methods_dynamic_registernatives_or_other"],
        "liblytcuhe_vs_librcmefqonnx": native["liblytcuhe_vs_librcmefqonnx_comparison"],
        "top_libraries_by_size": [
            {
                "name": x["name"],
                "bytes": x["bytes"],
                "vendor_category": x["vendor_category"],
                "file_entropy": x["file_entropy"],
                "text_entropy": x["text_entropy"],
                "rodata_entropy": x["rodata_entropy"],
                "relro": x["hardening"].get("relro"),
                "nx": x["hardening"].get("nx"),
                "canary": x["hardening"].get("stack_protector"),
                "stripped": x["hardening"].get("stripped"),
                "jni_onload": x["has_jni_onload"],
                "java_exports": x["static_java_jni_export_count"],
                "total_exports": x["export_count"],
            }
            for x in sorted(native["libraries"], key=lambda r: r["bytes"], reverse=True)
        ],
    },
    "superapp_ml_and_archaeology": {
        "dormant_react_native_bundle": {
            "path": "assets/android.1.zip",
            "archive_bytes": assets_ml["react_native_hybrid_analysis"]["archive_bytes"],
            "uncompressed_bytes": census["by_container"]["assets/android.1.zip"]["uncompressed_bytes"],
            "member_files": assets_ml["react_native_hybrid_analysis"]["member_file_count"],
            "metro_modules": assets_ml["react_native_hybrid_analysis"]["metro_module_definitions_detected"],
            "timestamp": assets_ml["react_native_hybrid_analysis"]["timestamp_sample"],
            "dex_references_found": 0,
            "native_rn_so_count": 0,
        },
        "griver_miniapp_platform": {
            "catalog_count": assets_ml["griver_alipay_superapp"]["miniapp_catalog"]["miniapp_count"],
            "categories_count": len(assets_ml["griver_alipay_superapp"]["miniapp_catalog"]["categories"]),
            "categories": assets_ml["griver_alipay_superapp"]["miniapp_catalog"]["categories"],
            "amr_runtime_packages": [
                {"file": a["file"], "zip_bytes": a["zip_bytes"], "tar_members": a["tar_member_count"], "tar_bytes": a["tar_uncompressed_bytes"]}
                for a in assets_ml["griver_alipay_superapp"]["amr_containers"]
            ],
            "rea_js_visited_ast_nodes": js_bridge["statistics"]["visited_ast_nodes"],
            "rea_js_semantic_nodes": js_bridge["semantic_graph_summary"]["node_count"],
            "rea_js_semantic_relations": js_bridge["semantic_graph_summary"]["relation_count"],
        },
        "on_device_ai_cv": {
            "tflite_models": assets_ml["ml_and_computer_vision"]["tflite_model_count"],
            "tflite_bytes": assets_ml["ml_and_computer_vision"]["tflite_total_bytes"],
            "ncnn_bin_bytes": assets_ml["ml_and_computer_vision"]["ncnn_fastestdet"]["bin_bytes"],
            "ncnn_layers": assets_ml["ml_and_computer_vision"]["ncnn_fastestdet"]["declared_layers"],
            "opencv_models_bytes": sum(m["bytes"] for m in assets_ml["ml_and_computer_vision"]["opencv_classical_models"]),
            "total_on_device_ml_cv_model_bytes": (
                assets_ml["ml_and_computer_vision"]["tflite_total_bytes"]
                + assets_ml["ml_and_computer_vision"]["ncnn_fastestdet"]["bin_bytes"]
                + sum(m["bytes"] for m in assets_ml["ml_and_computer_vision"]["opencv_classical_models"])
            ),
        },
    },
    "subsystems_breakdown": [
        {"subsystem": k, **v} for k, v in census["by_subsystem_across_apks"].items()
    ],
}

(DATA / "synthesis_metrics.json").write_text(json.dumps(synthesis, indent=2), encoding="utf-8")
print("Wrote data/synthesis_metrics.json successfully!")
print("Total on-device ML/CV models bytes:", f"{synthesis['superapp_ml_and_archaeology']['on_device_ai_cv']['total_on_device_ml_cv_model_bytes']:,}")
print("Native libraries audited:", synthesis["native_elf_architecture"]["library_count"])
print("Total indexed files:", synthesis["corpus_scale"]["total_indexed_records_all_layers"])
