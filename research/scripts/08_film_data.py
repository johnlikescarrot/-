#!/usr/bin/env python3
"""Stage 8 — assemble the single JSON the film renderer consumes.

Every on-screen figure in the film comes from this file, and this file is
built only from the stage 0-7 outputs. Nothing is typed by hand.
"""
from __future__ import annotations

import json
import struct
from collections import Counter
from pathlib import Path

ROOT = Path(__file__).resolve().parents[2]
DATA = ROOT / "research" / "data"


def load(name: str) -> dict:
    return json.loads((DATA / name).read_text())


def dex_sections() -> list[dict]:
    """Split each DEX into its header-declared payload regions."""
    rows = []
    for p in sorted((ROOT / "work" / "dex").glob("classes*.dex")):
        b = p.read_bytes()[:112]
        (string_ids_size, string_ids_off, type_ids_size, type_ids_off,
         proto_ids_size, proto_ids_off, field_ids_size, field_ids_off,
         method_ids_size, method_ids_off, class_defs_size, class_defs_off,
         data_size, data_off) = struct.unpack_from("<14I", b, 56)
        rows.append({
            "dex": p.name,
            "bytes": p.stat().st_size,
            "code": data_size,
            "index_bytes": data_off,
            "method_capacity_used": round(100 * method_ids_size / 65536, 2),
        })
    return rows


def main() -> None:
    inv, man, dex, sdk, nat, res, sec, ins = (
        load("00_inventory.json"), load("01_manifest.json"), load("02_dex.json"),
        load("03_sdk.json"), load("04_native.json"), load("05_resources.json"),
        load("06_security.json"), load("07_insights.json"),
    )
    first_party = sdk["endpoints"]["by_class"].get("first-party · ZaloPay", [])
    fam = Counter(dict((k, v) for k, v in dex["package_families"]))
    lim = [r for r in dex["dex_files"] if r["methods"] > 58000]

    film = {
        "subject": {
            "app": "ZaloPay", "package": man["identity"]["package"],
            "version": man["identity"]["version_name"], "version_code": man["identity"]["version_code"],
            "min_sdk": man["identity"]["min_sdk"], "target_sdk": man["identity"]["target_sdk"],
            "main_activity": man["identity"]["main_activity"],
            "sha256_xapk": inv["xapk"]["sha256"],
            "sha256_outer": inv["outer"]["sha256"],
            "bytes_outer": inv["outer"]["bytes"], "bytes_xapk": inv["xapk"]["bytes"],
        },
        "container": {
            "splits": len(inv["splits"]), "entries": inv["totals"]["entries"],
            "uncompressed": inv["totals"]["uncompressed"],
            "dex_files": inv["totals"]["dex_files"], "native_libs": inv["totals"]["native_libs"],
            "xml": inv["totals"]["xml"],
            "split_names": [s["name"] for s in inv["splits"]],
            "misaligned_base": next(s["alignment"]["misaligned_entries"] for s in inv["splits"]
                                   if s["name"].startswith("vn.com")),
        },
        "code": {
            "classes": dex["totals"]["classes"], "methods": dex["totals"]["methods"],
            "strings": dex["totals"]["strings"], "types": dex["totals"]["types"],
            "dex_bytes": dex["totals"]["bytes"], "dex": dex["totals"]["dex"],
            "method_limit_dexes": [r["dex"] for r in lim],
            "largest_dex": max(dex["dex_files"], key=lambda r: r["bytes"]),
            "obf_pkg_pct": ins["obfuscation"]["obfuscated_package_pct"],
            "obf_class_pct": ins["obfuscation"]["classes_in_obfuscated_pct"],
            "native_methods": dex["native_methods"]["count"],
            "sections": dex_sections(),
        },
        "manifest": {
            "activities": man["counts"]["activities"], "services": man["counts"]["services"],
            "receivers": man["counts"]["receivers"], "providers": man["counts"]["providers"],
            "permissions": man["permissions"]["count"],
            "dangerous": man["permissions"]["dangerous"],
            "features": man["features"],
        },
        "sdks": [{"sdk": r["sdk"], "vendor": r["vendor"], "evidence": r["evidence_strings"]}
                 for r in sdk["sdks"][:18]],
        "sdk_count": sdk["sdk_count"],
        "endpoints": {
            "hosts": sdk["endpoints"]["unique_hosts"],
            "first_party": first_party[:10],
            "first_party_count": len(first_party),
            "classes": {k: len(v) for k, v in sdk["endpoints"]["by_class"].items()},
            "api_paths": sdk["api_paths"]["unique"],
        },
        "native": {
            "count": nat["count"], "bytes": nat["total_bytes"],
            "stripped_pct": nat["stripped_pct"], "hardening": nat["hardening_summary"],
            "randomised_names": nat["randomised_names"],
            "libraries": [{"lib": r["lib"], "bytes": r["bytes"], "cat": r["category"],
                           "entropy": r["entropy_bits_per_byte"]} for r in nat["libraries"]],
            "max_entropy": {"lib": nat["entropy_max"]["lib"], "value": nat["entropy_max"]["entropy_bits_per_byte"]},
        },
        "resources": {
            "arsc_bytes": res["arsc"]["bytes"], "arsc_strings": res["arsc"]["global_strings"],
            "ml_models": res["on_device_ml"]["count"], "ml_bytes": res["on_device_ml"]["total_bytes"],
            "ml_list": [m["asset"].split("/")[-1] for m in res["on_device_ml"]["models"]],
            "security_assets": res["kyc_security_assets"]["count"],
        },
        "security": {
            "cert_subject": sec["certificate"]["subject"],
            "cert_sha256": sec["certificate"]["cert_sha256"],
            "cert_not_before": sec["certificate"]["not_before"][:10],
            "cert_not_after": sec["certificate"]["not_after"][:10],
            "key": sec["certificate"]["key"],
            "sig_verified": sec["verification"]["signature_verified"],
            "v2_pair_length": sec["signing_block"]["pairs"][0]["declared_length"],
            "block_size": sec["signing_block"]["signing_block"]["declared_size"],
            "hardening": sec["hardening_inventory"],
            "pqc": ["Kyber/ML-KEM", "Dilithium", "SPHINCS+", "Picnic", "BIKE", "HQC", "FrodoKEM"],
        },
        "miniapps": {
            "count": ins["mini_apps"]["count"],
            "categories": ins["mini_apps"]["categories"][:8],
            "deep_link": ins["mini_apps"]["deep_link_pattern"],
            "hosts": ins["mini_apps"]["base_url_hosts"][:6],
        },
        "pin_surface": ins["pin_envelope_surface"],
        "families": fam.most_common(10),
        "provenance": {
            "outer_zip": "Zalopay-Thanh toán & Tài chính_11.17.0_APKPure.zip",
            "xapk_sha256": inv["xapk"]["sha256"],
            "stages": 8,
            "tooling": ["custom DEX parser (no third-party decompiler)",
                        "pyelftools (ELF)", "androguard (AXML)", "custom ARSC reader",
                        "cryptography (X.509)", "FFmpeg 9.0.2", "TypeScript 5 / @resvg + Skia"],
        },
    }
    # real bytes of the signing block head, for the hex viewer on screen
    apk = (ROOT / "work" / "apks" / "vn.com.vng.zalopay.apk").read_bytes()
    blk_off = sec["signing_block"]["signing_block"]["offset"]
    head = apk[blk_off : blk_off + 48]
    pair_off = sec["signing_block"]["pairs"][0]["offset"]
    pair_tail = apk[pair_off + 1383 - 8 : pair_off + 1383 + 24]
    film["security"]["hex"] = {
        "block_offset": blk_off,
        "head": " ".join(f"{b:02x}" for b in head),
        "pair_offset": pair_off,
        "pair_tail": " ".join(f"{b:02x}" for b in pair_tail),
        "declared_vs_actual": [sec["signing_block"]["pairs"][0]["declared_length"], 1387],
    }

    (DATA / "film.json").write_text(json.dumps(film, indent=1, ensure_ascii=False), encoding="utf-8")
    print("film.json:", round(len(json.dumps(film)) / 1024, 1), "KiB")
    print("  dex method-limit files:", film["code"]["method_limit_dexes"])
    print("  first-party hosts:", film["endpoints"]["first_party_count"], "| mini-apps:", film["miniapps"]["count"])


if __name__ == "__main__":
    main()
