#!/usr/bin/env python3
"""
Synthesizes and cross-validates ALL REA (morluto/rea) and Python static analysis evidence
into a single canonical dataset: research/evidence/zalopay_summary_dataset.json
"""

import json
from pathlib import Path

ROOT = Path("/home/user/-")
DEEP = ROOT / "research/evidence/deep"
REA = ROOT / "research/evidence/rea"
OUT = ROOT / "research/evidence/zalopay_summary_dataset.json"

f1 = json.loads((DEEP / "01_bundle_and_signatures.json").read_text())
f2 = json.loads((DEEP / "02_manifest_and_attack_surface.json").read_text())
f3 = json.loads((DEEP / "03_resources_and_localization.json").read_text())
f4 = json.loads((DEEP / "04_dex_architecture_and_sbom.json").read_text())
f5 = json.loads((DEEP / "05_native_elf_and_assets_deep.json").read_text())
f6 = json.loads((DEEP / "06_surgical_probes.json").read_text())

rea_sigs = json.loads((REA / "rea_apk_signatures_and_flutter.json").read_text())
rea_nat = json.loads((REA / "rea_native_elf_suite.json").read_text())
rea_js = json.loads((REA / "rea_js_bridges.json").read_text())

native_table = []
for so_name, deep_info in sorted(f5["native_libraries"].items()):
    rea_info = rea_nat.get(so_name, {})
    hardening_rows = ((rea_info.get("hardening") or {}).get("images") or [{}])[0].get("rows", [])
    hard_map = {r["property"]: r["state"] for r in hardening_rows}
    needed = [n["name"] for n in (rea_info.get("dependencies") or {}).get("needed", [])]
    inits_cov = (rea_info.get("initializers") or {}).get("coverage", {})
    rea_exp_cov = (rea_info.get("exports") or {}).get("coverage", {})
    notes_tables = (rea_info.get("notes") or {}).get("tables", [])

    build_id = None
    for tbl in notes_tables:
        for note in tbl.get("notes", []):
            if note.get("type_name") == "NT_GNU_BUILD_ID":
                build_id = note.get("description_hex") or note.get("build_id")

    cap = deep_info.get("capstone_text_profile") or {}
    svcs = cap.get("thumb_svc_count", 0) + cap.get("arm_svc_count", 0)
    text_sec = next((s for s in deep_info["sections_top"] if s["name"] == ".text"), {})
    rodata_sec = next((s for s in deep_info["sections_top"] if s["name"] == ".rodata"), {})

    native_table.append({
        "name": so_name,
        "bytes": deep_info["bytes"],
        "sha256": deep_info["sha256"],
        "overall_entropy": deep_info["overall_entropy"],
        "text_bytes": text_sec.get("size", 0),
        "text_entropy": text_sec.get("entropy", 0.0),
        "rodata_bytes": rodata_sec.get("size", 0),
        "rodata_entropy": rodata_sec.get("entropy", 0.0),
        "section_count": deep_info["section_count"],
        "needed_libs": needed,
        "init_count": inits_cov.get("total_entries", 0),
        "exported_functions": deep_info["exported_functions_count"],
        "rea_dynamic_exports": rea_exp_cov.get("total_entries", 0),
        "jni_exports": deep_info["jni_exports"],
        "hardening": {
            "nx": hard_map.get("nx"),
            "relro": hard_map.get("relro"),
            "stack_protector": hard_map.get("stack_protector"),
            "fortify": hard_map.get("fortify"),
            "stripped": hard_map.get("stripped"),
            "rpath": hard_map.get("rpath"),
            "runpath": hard_map.get("runpath"),
        },
        "dominant_isa": cap.get("dominant_isa_mode"),
        "svc_syscalls_sampled": svcs,
        "keywords": deep_info["toolchain_and_telemetry_markers"]["keyword_hits"],
        "build_id": build_id,
    })

native_table.sort(key=lambda x: -x["bytes"])

base_m = f2["vn.com.vng.zalopay.apk"]
dex_agg = f4["app_dex_aggregate"]
aud_dex = f4["audience_network_dynamic_dex"]

summary = {
    "target": {
        "outer_zip": f1["outer_zip"],
        "xapk": {
            "filename": f1["xapk"]["filename"],
            "bytes": f1["xapk"]["bytes"],
            "sha256": f1["xapk"]["sha256"],
            "member_count": len(f1["xapk"]["members"]),
            "members": f1["xapk"]["members"],
            "icon_png": f1["xapk"]["icon_png"],
            "manifest_json": {
                "package_name": f1["xapk"]["manifest_json"]["package_name"],
                "name": f1["xapk"]["manifest_json"]["name"],
                "version_code": f1["xapk"]["manifest_json"]["version_code"],
                "version_name": f1["xapk"]["manifest_json"]["version_name"],
                "min_sdk": f1["xapk"]["manifest_json"]["min_sdk_version"],
                "target_sdk": f1["xapk"]["manifest_json"]["target_sdk_version"],
                "split_configs": f1["xapk"]["manifest_json"]["split_configs"],
            },
        },
        "apks": {
            name: {
                "bytes": info["bytes"],
                "sha256": info["sha256"],
                "entry_count": info["entry_count"],
                "total_uncompressed": info["total_uncompressed"],
                "top_directories": info["top_directories"],
                "rea_signature_posture": rea_sigs[name]["signature"]["normalized_result"]["posture"],
                "rea_strongest_scheme": rea_sigs[name]["signature"]["normalized_result"]["posture"]["strongest_scheme"],
                "signer_subject": (
                    rea_sigs[name]["signature"]["normalized_result"]["schemes"]["v3"]["signers"][0]["certificate"]["subject"]
                    if rea_sigs[name]["signature"]["normalized_result"]["schemes"]["v3"]["signers"]
                    else None
                ),
                "signer_sha256": (
                    rea_sigs[name]["signature"]["normalized_result"]["schemes"]["v3"]["signers"][0]["certificate"]["sha256_fingerprint"]
                    if rea_sigs[name]["signature"]["normalized_result"]["schemes"]["v3"]["signers"]
                    else None
                ),
                "source_stamp_org": (
                    rea_sigs[name]["signature"]["normalized_result"]["source_stamp"]["certificate"]["subject"]
                    if rea_sigs[name]["signature"]["normalized_result"]["source_stamp"]["present"]
                    else None
                ),
            }
            for name, info in f1["apks"].items()
        },
    },
    "manifest": {
        "min_sdk": base_m["minSdkVersion"],
        "target_sdk": base_m["targetSdkVersion"],
        "compile_sdk": base_m["compileSdkVersion"],
        "application_flags": base_m["application_flags"],
        "permissions_count": base_m["permissions_count"],
        "permission_categories": base_m["permission_categories"],
        "component_summary": base_m["component_summary"],
        "total_components": base_m["total_components"],
        "multi_processes": base_m["multi_processes"],
        "queries_packages": base_m["queries"]["packages"],
        "deep_link_schemes": base_m["deep_link_schemes"],
        "deep_link_hosts": base_m["deep_link_hosts"],
        "meta_data_count": base_m["meta_data_count"],
        "network_security_config": f3["decoded_res_xml"].get("res/xml/network_security_config.xml"),
        "apdu_service": f3["decoded_res_xml"].get("res/xml/apdu_service.xml"),
    },
    "resources": {
        "arsc": f3["resources_arsc"],
        "localization_splits": f3["localization_splits_comparison"],
        "raw_count": len(f3["res_raw_inventory"]),
        "raw_total_bytes": sum(r["bytes"] for r in f3["res_raw_inventory"]),
        "top_raw": f3["res_raw_inventory"][:12],
    },
    "dex_and_bytecode": {
        "kotlin_tooling": f4["kotlin_tooling_metadata"],
        "primary_dex_count": dex_agg["dex_count"],
        "primary_totals": dex_agg["totals"],
        "obfuscation_rates": dex_agg["obfuscation_rates"],
        "root_packages_top20": list(dex_agg["root_packages_top25"].items())[:20],
        "sub_packages_top25": list(dex_agg["sub_packages_top60"].items())[:25],
        "framework_and_security_classes": dex_agg["security_and_framework_classes"],
        "framework_and_security_type_refs": dex_agg["security_and_framework_type_refs"],
        "opcode_families": dex_agg["opcode_families"],
        "top_opcodes": list(dex_agg["top_opcodes"].items())[:15],
        "audience_network_dynamic_dex": {
            "bytes": aud_dex["file_size"],
            "counts": aud_dex["counts"],
            "obfuscation": aud_dex["obfuscation"],
        },
        "native_methods_in_dex": f6["native_and_dex_links"]["total_native_methods"],
        "native_method_classes": f6["native_and_dex_links"]["total_classes_with_native_methods"],
        "top_native_method_classes": list(f6["native_and_dex_links"]["top_classes_with_native_methods"].items())[:12],
        "sbom_counts": {
            "meta_inf_versions": f4["sbom"]["meta_inf_version_count"],
            "root_properties": f4["sbom"]["root_properties_count"],
            "meta_inf_services": f4["sbom"]["meta_inf_services_count"],
        },
    },
    "hybrid_runtimes_and_ai": {
        "react_native": f6["js_and_griver"]["react_native_bundle"],
        "griver_amrs": f6["js_and_griver"]["griver_amrs_unpacked"],
        "rea_js_bridges_stats": rea_js["normalized_result"]["statistics"],
        "ncnn_fastestdet": f5["ncnn_model_inspection"],
        "tflite_object_labeler": f6["special_assets"]["tflite_object_labeler"],
        "encrypted_or_high_entropy_assets": [
            a for a in f5["assets_inventory"] if a["entropy"] >= 7.5
        ],
        "assets_total_count": f5["assets_count"],
        "assets_total_bytes": sum(a["bytes"] for a in f5["assets_inventory"]),
    },
    "native_elf_summary": {
        "count": len(native_table),
        "total_bytes": sum(n["bytes"] for n in native_table),
        "libraries": native_table,
        "liblytcuhe_vs_librcmefqonnx_diff_bytes": f6["native_and_dex_links"]["liblytcuhe_vs_librcmefqonnx_diff_bytes"],
    },
}

OUT.write_text(json.dumps(summary, indent=2, ensure_ascii=False))
print("Wrote canonical summary dataset:", OUT, f"({OUT.stat().st_size} bytes)")
