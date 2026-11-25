#!/usr/bin/env python3
"""Stage 3: static_scan.json + chain_of_custody.json -> REPORT.md + FINDINGS.json"""
import json, pathlib

ROOT = pathlib.Path(__file__).resolve().parent.parent
REPORTS = ROOT / "reports"

DANGEROUS = {"READ_CALENDAR","WRITE_CALENDAR","CAMERA","READ_CONTACTS","WRITE_CONTACTS",
"GET_ACCOUNTS","ACCESS_FINE_LOCATION","ACCESS_COARSE_LOCATION","ACCESS_BACKGROUND_LOCATION",
"RECORD_AUDIO","READ_PHONE_STATE","READ_PHONE_NUMBERS","CALL_PHONE","READ_CALL_LOG","WRITE_CALL_LOG",
"SEND_SMS","RECEIVE_SMS","READ_SMS","RECEIVE_WAP_PUSH","RECEIVE_MMS","BODY_SENSORS",
"ACTIVITY_RECOGNITION","READ_EXTERNAL_STORAGE","WRITE_EXTERNAL_STORAGE","ACCESS_MEDIA_LOCATION",
"POST_NOTIFICATIONS","READ_MEDIA_IMAGES","READ_MEDIA_VIDEO","READ_MEDIA_AUDIO","NEARBY_WIFI_DEVICES",
"BLUETOOTH_SCAN","BLUETOOTH_CONNECT","BLUETOOTH_ADVERTISE"}
SPECIAL = {"SYSTEM_ALERT_WINDOW","REQUEST_INSTALL_PACKAGES","MANAGE_EXTERNAL_STORAGE",
"PACKAGE_USAGE_STATS","BIND_ACCESSIBILITY_SERVICE","BIND_DEVICE_ADMIN","INSTALL_SHORTCUT",
"USE_BIOMETRIC","USE_FINGERPRINT","QUERY_ALL_PACKAGES"}

def short_perm(p: str) -> str:
    return p.split(".")[-1]

def flag(p: str) -> str:
    s = short_perm(p)
    if s in DANGEROUS: return "dangerous"
    if s in SPECIAL: return "special/sensitive"
    return "normal/signature"

def main() -> None:
    scan = json.loads((REPORTS / "static_scan.json").read_text())
    custody = json.loads((REPORTS / "chain_of_custody.json").read_text())
    try: xapk = json.loads((REPORTS / "xapk_manifest.json").read_text())
    except Exception: xapk = {}
    base = next((a for a in scan["apks"] if a["file"].startswith("vn.com.vng.zalopay")), scan["apks"][0])
    ag = base.get("ag", {}) if isinstance(base.get("ag"), dict) else {}

    L = []
    L.append("# Báo cáo phân tích tĩnh — ZaloPay 11.17.0 (APKPure XAPK)\n")
    L.append("> Static reverse-engineering report (evidence-first, static-only). "
             "Machine-readable highlights: `FINDINGS.json`. Full inventory: `file_inventory.csv`. "
             "Hashes: `chain_of_custody.json`.\n")
    L.append("## 0. Tóm tắt / Abstract\n")
    L.append(f"- Gói nguồn: `{custody['source_zip']['name']}` "
             f"({custody['source_zip']['bytes']/1024/1024:.1f} MB, "
             f"SHA-256 `{custody['source_zip']['sha256'][:16]}…`).")
    L.append(f"- Bên trong: {len(custody['inner'])} file XAPK → "
             f"{sum(len(x.get('contains', [])) for x in custody['inner'])} mục "
             f"(base APK + {len(scan['apks'])-1} split configs).")
    if ag:
        L.append(f"- Base APK: package `{ag.get('package')}`, version `{ag.get('version_name')}` "
                 f"({ag.get('version_code')}), minSdk {ag.get('min_sdk')}, "
                 f"targetSdk {ag.get('target_sdk')}, mainActivity `{ag.get('main_activity')}`.")
        L.append(f"- Permissions: {len(ag.get('permissions', []))} | Activities: {len(ag.get('activities', []))} | "
                 f"Services: {len(ag.get('services', []))} | Receivers: {len(ag.get('receivers', []))} | "
                 f"Providers: {len(ag.get('providers', []))}.")
    tot_so_n = sum(len(a["native_libs"]) for a in scan["apks"])
    tot_so_b = sum(a["native_total_bytes"] for a in scan["apks"])
    all_abis = sorted({x for a in scan["apks"] for x in a["native_abis"]})
    L.append(f"- DEX: {len(base['dex_files'])} file, {base['dex_total_bytes']/1024/1024:.1f} MB | "
             f"Native: {tot_so_n} .so trên mọi split ({tot_so_b/1024/1024:.1f} MB; ABI: "
             f"{', '.join(all_abis) or '—'}) | "
             f"resources.arsc (base): {(base.get('resources.arsc_bytes') or 0)/1024:.0f} KB.")
    L.append("- EN Abstract: This report documents a fully static, reproducible analysis of the "
             "ZaloPay 11.17.0 split-APK bundle. We inventory every container entry, verify SHA-256 "
             "hashes, parse the binary manifest (permissions/components/SDK levels), summarise DEX "
             "headers, native libraries, resources and signing certificates, and triage DEX strings "
             "into redacted aggregates (domain/package/keyword counts only). No code is executed, "
             "no protection is bypassed, and no secrets are published.\n")

    L.append("## 1. Chuỗi bảo quản (Chain of custody)\n")
    L.append("| File | Bytes | SHA-256 (rút gọn) |")
    L.append("|---|---|---|")
    L.append(f"| `{custody['source_zip']['name']}` | {custody['source_zip']['bytes']} | "
             f"`{custody['source_zip']['sha256'][:32]}…` |")
    for inner in custody["inner"]:
        L.append(f"| `{inner['name']}` | {inner['bytes']} | `{inner['sha256'][:32]}…` |")
        for c in inner.get("contains", []):
            L.append(f"| ↳ `{c['name']}` | {c['bytes']} | `{c['sha256'][:32]}…` |")
    L.append("")
    if xapk:
        L.append("## 2. XAPK manifest\n```json")
        L.append(json.dumps(xapk, indent=2, ensure_ascii=False)[:4000])
        L.append("```\n")
    L.append("## 3. Hồ sơ từng APK split\n")
    L.append("| APK | MB | Entries | DEX (MB) | .so (MB) | ARSC (KB) | Manifest (B) |")
    L.append("|---|---|---|---|---|---|---|")
    for a in scan["apks"]:
        L.append(f"| `{a['file']}` | {a['bytes']/1024/1024:.1f} | {a['zip_entries']} | "
                 f"{a['dex_total_bytes']/1024/1024:.1f} | {a['native_total_bytes']/1024/1024:.1f} | "
                 f"{(a.get('resources.arsc_bytes') or 0)/1024:.0f} | {a.get('AndroidManifest.xml_bytes')} |")
    L.append("")
    if ag:
        perms = ag.get("permissions", [])
        L.append(f"## 4. Permissions ({len(perms)})\n")
        L.append("| Permission | Nhóm heuristic |")
        L.append("|---|---|")
        for p in sorted(perms):
            L.append(f"| `{p}` | {flag(p)} |")
        L.append("")
        for key, title in (("activities","Activities"),("services","Services"),
                           ("receivers","Receivers"),("providers","Providers")):
            items = ag.get(key, []) or []
            L.append(f"## 5.{key} — {title} ({len(items)})\n")
            L.append(f"- mainActivity: `{ag.get('main_activity')}` | app label: `{ag.get('app_label')}` | "
                     f"debuggable: `{ag.get('debuggable')}` | allowBackup: `{ag.get('allow_backup')}`")
            L.append(f"<details><summary>Danh sách đầy đủ ({len(items)})</summary>\n")
            for c in sorted(items):
                L.append(f"- `{c}`")
            L.append("</details>\n")
        if ag.get("certificates"):
            L.append("## 6. Chữ ký (Certificates — fingerprints)\n")
            for c in ag["certificates"]:
                L.append(f"- SHA-256: `{c.get('sha256','?')[:32]}…` | subject: `{c.get('subject')}` | "
                         f"issuer: `{c.get('issuer')}`")
            L.append("")
    L.append("## 7. DEX (header + ids counts)\n")
    L.append("| File | MB | strings | types | protos | fields | methods | classes | size_match |")
    L.append("|---|---|---|---|---|---|---|---|---|")
    totals = {"strings": 0, "types": 0, "protos": 0, "fields": 0, "methods": 0, "classes": 0}
    for d in base.get("dex_headers", []):
        bsz = [x["bytes"] for x in base["dex_files"] if x["name"] == d["name"]]
        mb = (bsz[0] / 1024 / 1024) if bsz else 0
        for k in ("string_ids", "type_ids", "proto_ids", "field_ids", "method_ids", "class_defs"):
            totals[{"string_ids": "strings", "type_ids": "types", "proto_ids": "protos",
                    "field_ids": "fields", "method_ids": "methods", "class_defs": "classes"}[k]] += d.get(k, 0) or 0
        L.append(f"| `{d.get('name')}` | {mb:.1f} | {d.get('string_ids')} | {d.get('type_ids')} | "
                 f"{d.get('proto_ids')} | {d.get('field_ids')} | {d.get('method_ids')} | {d.get('class_defs')} | "
                 f"{d.get('size_match')} |")
    L.append(f"| **Tổng** | **{base['dex_total_bytes']/1024/1024:.1f}** | **{totals['strings']}** | "
             f"**{totals['types']}** | **{totals['protos']}** | **{totals['fields']}** | "
             f"**{totals['methods']}** | **{totals['classes']}** | — |")
    L.append("")
    L.append("## 8. Native libraries (gộp mọi split — base không chứa .so, .so nằm ở split ABI)\n")
    L.append("| APK | .so | ABI | Bytes | ELF | machine | type |")
    L.append("|---|---|---|---|---|---|---|")
    for a in scan["apks"]:
        for n in a.get("native_libs", [])[:60]:
            L.append(f"| `{a['file']}` | `{n['name'].split('/')[-1]}` | {n.get('abi')} | {n['bytes']} | "
                     f"{n.get('elf')} | {n.get('machine','')} | {n.get('type','')} |")
    L.append("")
    L.append("## 9. Tài nguyên (Resources)\n")
    L.append(f"- `resources.arsc`: {(base.get('resources.arsc_bytes') or 0)/1024:.0f} KB; "
             f"`res/`: {base['res_count']} file ({base['res_bytes']/1024/1024:.1f} MB); "
             f"`assets/`: {base['assets_count']} file ({base['assets_bytes']/1024/1024:.1f} MB).")
    L.append("- Top phần mở rộng (theo số lượng): " +
             ", ".join(f"`{k}`×{v}" for k, v in list(base['ext_counts'].items())[:15]))
    L.append("")
    s = base.get("strings", {})
    if s and "error" not in s:
        L.append("## 10. Strings triage (tổng hợp đã kiểm duyệt — không công bố giá trị nhạy cảm)\n")
        L.append(f"- ASCII strings (DEX): {s['ascii_strings_total']} (đã quét {s['ascii_strings_scanned']}); "
                 f"URL: {s['url_count']}.")
        L.append("- Top domain tails: " + (", ".join(f"`{k}`×{v}" for k, v in list(s['domains_top'].items())[:20]) or "—"))
        L.append("- Top package prefix: " + (", ".join(f"`{k}`×{v}" for k, v in list(s['package_prefix_top'].items())[:25]) or "—"))
        L.append("- Crypto keywords: " + (", ".join(f"`{k}`×{v}" for k, v in s['crypto_keyword_counts'].items()) or "—"))
        L.append("- Sensitive-pattern hits (chỉ đếm, đã che giá trị): " +
                 (", ".join(f"`{k}`×{v}" for k, v in s['sensitive_pattern_counts'].items()) or "không phát hiện"))
        L.append("")
    L.append("## 11. Nhận định hardening (mức khuyến nghị, không PoC)\n")
    L.append("- Kiểm tra `debuggable=false`, `allowBackup` chính sách, exported components tối thiểu, "
             "targetSdk hiện đại, và networkSecurityConfig (nếu có). Chi tiết xem FINDINGS.json.")
    L.append("")
    L.append("## 12. Giới hạn & Tái lập\n")
    L.append("- Static-only: không suy luận hành vi runtime; package-prefix ⇒ heuristic.")
    L.append("- Tái lập: `python3 analysis/extract.py && python3 analysis/static_scan.py && python3 analysis/build_report.py`.")
    L.append("")
    (REPORTS / "REPORT.md").write_text("\n".join(L), encoding="utf-8")

    findings = {
        "package": ag.get("package"), "version_name": ag.get("version_name"),
        "version_code": ag.get("version_code"), "min_sdk": ag.get("min_sdk"),
        "target_sdk": ag.get("target_sdk"), "main_activity": ag.get("main_activity"),
        "counts": {
            "permissions": len(ag.get("permissions", [])) if ag else 0,
            "dangerous_permissions": sum(1 for p in (ag.get("permissions", []) if ag else []) if flag(p) == "dangerous"),
            "activities": len(ag.get("activities", []) or []), "services": len(ag.get("services", []) or []),
            "receivers": len(ag.get("receivers", []) or []), "providers": len(ag.get("providers", []) or []),
            "dex_files": len(base["dex_files"]), "dex_mb": round(base["dex_total_bytes"]/1024/1024, 2),
            "dex_method_ids_total": sum((d.get("method_ids") or 0) for d in base.get("dex_headers", [])),
            "dex_class_defs_total": sum((d.get("class_defs") or 0) for d in base.get("dex_headers", [])),
            "dex_string_ids_total": sum((d.get("string_ids") or 0) for d in base.get("dex_headers", [])),
            "native_libs": sum(len(a["native_libs"]) for a in scan["apks"]),
            "native_mb": round(sum(a["native_total_bytes"] for a in scan["apks"])/1024/1024, 2),
            "zip_entries_base": base["zip_entries"], "apk_splits": len(scan["apks"]),
        },
        "debuggable": ag.get("debuggable"), "allow_backup": ag.get("allow_backup"),
        "manifest_flags": ag.get("manifest_flags"),
        "certificates": ag.get("certificates"),
        "native_abis": sorted({x for a in scan["apks"] for x in a["native_abis"]}),
        "top_domains": list((base.get("strings", {}) or {}).get("domains_top", {}).items())[:15],
        "top_package_prefixes": list((base.get("strings", {}) or {}).get("package_prefix_top", {}).items())[:15],
        "source": {"zip": custody["source_zip"]["name"], "sha256": custody["source_zip"]["sha256"]},
        "confidence": "static-only; heuristics labeled",
    }
    (REPORTS / "FINDINGS.json").write_text(json.dumps(findings, indent=2, ensure_ascii=False))
    print("Wrote REPORT.md + FINDINGS.json")

if __name__ == "__main__":
    main()
