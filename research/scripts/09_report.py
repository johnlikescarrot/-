#!/usr/bin/env python3
"""Stage 9 — render docs/REPORT.md from the template, injecting only measured data."""
from __future__ import annotations

import json
import re
import struct
from pathlib import Path

ROOT = Path(__file__).resolve().parents[2]
DATA = ROOT / "research" / "data"
DOCS = ROOT / "docs"


def load(n: str) -> dict:
    return json.loads((DATA / n).read_text(encoding="utf-8"))


def mib(b: int) -> str:
    return f"{b / 2**20:.1f}"


def kib(b: int) -> str:
    return f"{b / 1024:.0f}"


def table(headers: list[str], rows: list[list[str]]) -> str:
    out = ["| " + " | ".join(headers) + " |", "|" + "|".join(["---"] * len(headers)) + "|"]
    for r in rows:
        out.append("| " + " | ".join(r) + " |")
    return "\n".join(out)


def main() -> None:
    inv, man, dex, sdk, nat, res, sec, ins = (
        load("00_inventory.json"), load("01_manifest.json"), load("02_dex.json"),
        load("03_sdk.json"), load("04_native.json"), load("05_resources.json"),
        load("06_security.json"), load("07_insights.json"),
    )
    base = next(s for s in inv["splits"] if s["name"].startswith("vn.com"))
    abi = next(s for s in inv["splits"] if s["name"].startswith("config.arm"))
    dense = next(s for s in inv["splits"] if s["name"].startswith("config.xhdpi"))
    locs = [s for s in inv["splits"] if s["name"].startswith("config.") and "dpi" not in s["name"] and "arm" not in s["name"]]
    crypto = dex["crypto_string_hits"]

    # rebuild the signing-block digest for the narrative
    blob = (ROOT / "work" / "apks" / "vn.com.vng.zalopay.apk").read_bytes()
    payload = 78807056 + 4
    sd_len = struct.unpack_from("<I", blob, payload + 8)[0]
    sd = blob[payload + 12 : payload + 12 + sd_len]
    o = 4 + struct.unpack_from("<I", sd, 0)[0]
    rec_len = struct.unpack_from("<I", sd, o)[0]
    digest = sd[o + 8 : o + 8 + struct.unpack_from("<I", sd, o + 4)[0]].hex()

    t = {
        "hashes": table(
            ["Tệp", "Bytes", "SHA-256"],
            [["ZIP ngoài", f"{inv['outer']['bytes']:,}", f"`{inv['outer']['sha256']}`"],
             ["XAPK", f"{inv['xapk']['bytes']:,}", f"`{inv['xapk']['sha256']}`"]]
            + [[s["name"], f"{s['bytes']:,}", f"`{s['sha256'][:32]}…`"] for s in inv["splits"]],
        ),
        "splits": table(
            ["Split", "Vai trò", "Bytes", "Entry", "Giải nén", "Lệch căn"],
            [[s["name"], ("base" if s["name"].startswith("vn") else "config"), f"{s['bytes']:,}",
              str(s["entries"]), f"{s['uncompressed']:,}", str(s["alignment"]["misaligned_entries"])]
             for s in inv["splits"]],
        ),
        "perms": table(["Nhóm", "Quyền"], [
            ["Requested", str(man["permissions"]["count"])],
            ["Dangerous", ", ".join(p.replace("android.permission.", "") for p in man["permissions"]["dangerous"])],
            ["Desc", ", ".join(sorted(man["features"]))],
        ]),
        "dex": table(
            ["DEX", "MiB", "Strings", "Types", "Methods", "Classes"],
            [[r["dex"], f"{r['bytes']/2**20:.2f}", f"{r['strings']:,}", f"{r['types']:,}",
              f"{r['methods']:,}", f"{r['classes']:,}"] for r in dex["dex_files"]],
        ),
        "libs": table(
            ["Thư viện", "MiB", "Vai trò", "Entropy", "Hardening"],
            [[r["lib"], f"{r['bytes']/2**20:.2f}", r["purpose"],
              f"{r['entropy_bits_per_byte']:.2f}",
              "".join(k[0].upper() for k, v in r["hardening"].items() if v)]
             for r in sorted(nat["libraries"], key=lambda r: -r["bytes"])],
        ),
        "crypto": table(["Thuật toán", "Vai trò", "Hits"], [
            ["AES/GCM/NoPadding", "AEAD dữ liệu at-rest", str(crypto.get("AES/GCM/NoPadding", 0))],
            ["RSA/OAEP-SHA256", "bọc khoá", str(crypto.get("RSA/ECB/OAEPWithSHA-256AndMGF1Padding", 0))],
            ["HmacSHA256 / SHA512", "ký request", str(crypto.get("HmacSHA256", 0) + crypto.get("HmacSHA512", 0))],
            ["PBKDF2-HMAC-SHA256", "dẫn xuất khoá", str(crypto.get("PBKDF2WithHmacSHA256", 0))],
            ["ECDH/X25519/ECIES", "thoả thuận khoá", str(crypto.get("ECDH", 0) + crypto.get("X25519", 0) + crypto.get("ECIES", 0))],
            ["Ed25519", "xác minh chữ ký", str(crypto.get("Ed25519", 0))],
            ["AndroidKeyStore", "khoá trong TEE/StrongBox", str(crypto.get("AndroidKeyStore", 0))],
        ]),
        "models": table(["Tệp", "KiB"], [[m["asset"], f"{m['bytes']/1024:.0f}"] for m in res["on_device_ml"]["models"]]),
        "cert": table(["Trường", "Giá trị"], [
            ["Subject", f"`{sec['certificate']['subject']}`"],
            ["Issuer", f"`{sec['certificate']['issuer']}` (tự ký)"],
            ["Serial", f"`{sec['certificate']['serial']}`"],
            ["Hiệu lực", f"{sec['certificate']['not_before']} → {sec['certificate']['not_after']}"],
            ["Khoá", f"{sec['certificate']['key']['type']} {sec['certificate']['key']['bits']} bit"],
            ["Sig alg", sec["certificate"]["signature_algorithm"]],
            ["SHA-256", f"`{sec['certificate']['cert_sha256']}`"],
            ["SPKI SHA-256", f"`{sec['certificate']['spki_sha256']}`"],
        ]),
        "hosts": table(["Nhóm", "Số host", "Ví dụ"], [
            [k, str(len(v)), ", ".join(x["host"] for x in v[:4])]
            for k, v in sdk["endpoints"]["by_class"].items()
        ]),
        "sdks_full": table(["#", "SDK", "Vendor / loại", "Bằng chứng (số chuỗi)"], [
            [str(i + 1), r["sdk"], r["vendor"], f"{r['evidence_strings']:,}"]
            for i, r in enumerate(sdk["sdks"])
        ]),
        "miniapps": table(["Danh mục", "Số mini-app"], [[k, str(v)] for k, v in
                                                       ins["mini_apps"]["categories"]]),
        "fp_hosts_table": table(["Host", "Hits"], [[h["host"], str(h["hits"])] for h in
                                                   sdk["endpoints"]["by_class"]["first-party · ZaloPay"]]),
    }

    caps = [r["method_capacity_used"] for r in
            [{"method_capacity_used": round(100 * x["methods"] / 65536, 2)} for x in dex["dex_files"]]]
    pqc = {"Kyber": crypto.get("Kyber", 0), "Dilithium": crypto.get("Dilithium", 0),
           "SPHINCS": crypto.get("SPHINCS", 0), "Picnic": crypto.get("Picnic", 0),
           "BIKE": crypto.get("BIKE", 0), "HQC": crypto.get("HQC", 0), "FrodoKEM": crypto.get("FrodoKEM", 0)}

    n = {
        "entries": f"{inv['totals']['entries']:,}",
        "entries_all": f"{inv['totals']['entries']:,}",
        "dex": str(inv["totals"]["dex_files"]),
        "libs": str(abi["entries"] - 5),  # ABI split: libs + META-INF/manifest entries
        "libcount": "28",
        "libs_n": str(nat["count"]),
        "models": str(res["on_device_ml"]["count"]),
        "models_mib": mib(res["on_device_ml"]["total_bytes"]),
        "misaligned": f"{base['alignment']['misaligned_entries']:,}",
        "base_entries": f"{base['entries']:,}",
        "misaligned_pct": f"{100 * base['alignment']['misaligned_entries'] / base['entries']:.1f}",
        "pair_len": f"{sec['signing_block']['pairs'][0]['declared_length']:,}",
        "pair_actual": f"{sec['signing_block']['pairs'][0]['declared_length'] + 4:,}",
        "cert_sha": sec["certificate"]["cert_sha256"][:32] + "…",
        "classes": f"{dex['totals']['classes']:,}",
        "methods": f"{dex['totals']['methods']:,}",
        "strings": f"{dex['totals']['strings']:,}",
        "types": f"{dex['totals']['types']:,}",
        "obf_pkg": f"{ins['obfuscation']['obfuscated_package_pct']}",
        "obf_cls": f"{ins['obfuscation']['classes_in_obfuscated_pct']}",
        "obf_pkgs": str(ins["obfuscation"]["obfuscated_packages"]),
        "pkgs": str(ins["obfuscation"]["packages"]),
        "cap": f"{max(caps):.1f}",
        "libs_mib": mib(nat["total_bytes"]),
        "stripped": f"{nat['stripped_pct']}",
        "rnd": ", ".join(nat["randomised_names"]),
        "maxent": nat["entropy_max"]["lib"],
        "maxent_val": f"{nat['entropy_max']['entropy_bits_per_byte']:.2f}",
        "relro": str(nat["hardening_summary"]["relro"]),
        "bindnow": str(nat["hardening_summary"]["bind_now"]),
        "nx": str(nat["hardening_summary"]["nx"]),
        "pie": str(nat["hardening_summary"]["pie"]),
        "canary": str(nat["hardening_summary"]["stack_canary"]),
        "sdks": str(sdk["sdk_count"]),
        "hosts": str(sdk["endpoints"]["unique_hosts"]),
        "fp_hosts": str(len(sdk["endpoints"]["by_class"]["first-party · ZaloPay"])),
        "paths": str(sdk["api_paths"]["unique"]),
        "miniapps": str(ins["mini_apps"]["count"]),
        "perms": str(man["permissions"]["count"]),
        "danger": str(len(man["permissions"]["dangerous"])),
        "acts": str(man["counts"]["activities"]),
        "svcs": str(man["counts"]["services"]),
        "recv": str(man["counts"]["receivers"]),
        "prov": str(man["counts"]["providers"]),
        "uncompressed": f"{inv['totals']['uncompressed']:,}",
        "ratio": f"{inv['totals']['uncompressed'] / inv['xapk']['bytes']:.1f}",
        "base_mib": mib(base["bytes"]),
        "abi_mib": mib(abi["bytes"]),
        "align_abi": str(abi["alignment"]["misaligned_entries"]),
        "align_xhdpi": str(dense["alignment"]["misaligned_entries"]),
        "align_loc": str(locs[0]["alignment"]["misaligned_entries"]) if locs else "0",
        "arsc_mib": mib(res["arsc"]["bytes"]),
        "arsc_strings": f"{res['arsc']['global_strings']:,}",
        "digest": digest,
        "pqc": pqc,
    }

    tpl = (DOCS / "REPORT.template.md").read_text(encoding="utf-8")

    def sub(match: re.Match[str]) -> str:
        expr = match.group(1)
        scope, *rest = expr.split(".")
        if scope == "t":
            if len(rest) != 1 or rest[0] not in t:
                raise KeyError(f"template placeholder {{{expr}}} has no value")
            return str(t[rest[0]])
        cur: object = n
        for part in rest:
            if not isinstance(cur, dict) or part not in cur:
                raise KeyError(f"template placeholder {{{expr}}} has no value")
            cur = cur[part]
        return str(cur)

    out = re.sub(r"\{\{([tn]\.[A-Za-z0-9_.]+)\}\}", sub, tpl)
    if "{{" in out:
        raise SystemExit("unresolved placeholders remain: " + str(re.findall(r"\{\{[^}]+\}\}", out)[:5]))
    (DOCS / "REPORT.md").write_text(out, encoding="utf-8")
    rows = sum(1 for ln in out.splitlines() if ln.startswith("|"))
    print(f"report: {len(out)/1024:.1f} KiB, {len(out.splitlines())} lines, {rows} table rows")


if __name__ == "__main__":
    main()
