#!/usr/bin/env python3
"""Low-memory static scanner for the ZaloPay XAPK corpus.

Design notes
------------
* Whole-program analysis (CFG/xref) of ~100 MB / 20 DEX files is not feasible in a
  constrained runner. We read DEX headers and the string/type/class tables directly:
  O(bytes) streaming, no call graph, no decompiler.
* Binary AndroidManifest.xml is parsed with androguard's APK class only.
* Every emitted number is derived from the artefact; no value is hand-written.
* URLs are reduced to hostnames; opaque/secret-looking strings are counted only.
"""
from __future__ import annotations

import argparse
import contextlib
import io
import json
import logging
import re
import struct
import zipfile
from collections import Counter
from pathlib import Path

logging.disable(logging.CRITICAL)

# Class names are normalised to dotted form (Lcom/foo/Bar; -> com.foo.Bar).
SDK_PATTERNS = {
    "OkHttp": ("okhttp3.",),
    "Retrofit": ("retrofit2.",),
    "Gson": ("com.google.gson.",),
    "RxJava/Reactive": ("io.reactivex.", "rx."),
    "Kotlin stdlib": ("kotlin.",),
    "Kotlin coroutines": ("kotlinx.coroutines.",),
    "Dagger/Hilt": ("dagger.", "hilt_"),
    "Room": ("androidx.room.",),
    "WorkManager": ("androidx.work.",),
    "Firebase": ("com.google.firebase.",),
    "Google Play services": ("com.google.android.gms.",),
    "Meta Audience/Facebook": ("com.facebook.",),
    "AppsFlyer": ("com.appsflyer.",),
    "Adjust": ("com.adjust.",),
    "Crashlytics": ("com.crashlytics.",),
    "Protobuf": ("com.google.protobuf.",),
    "Glide": ("com.bumptech.glide.",),
    "Coil": ("coil.",),
    "ExoPlayer/Media3": ("com.google.android.exoplayer.", "androidx.media3."),
    "ZXing": ("com.google.zxing.",),
    "Conscrypt/Tink/BC": ("org.conscrypt.", "com.google.crypto.tink.", "org.bouncycastle."),
    "Zalo platform SDK": ("com.zing.zalo.", "vn.com.vng.zalosdk."),
    "WebView/AndroidX": ("androidx.webkit.",),
}

DANGEROUS_PERMS = {
    "android.permission.READ_CONTACTS", "android.permission.WRITE_CONTACTS",
    "android.permission.READ_PHONE_STATE", "android.permission.READ_BASIC_PHONE_STATE",
    "android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_COARSE_LOCATION",
    "android.permission.CAMERA", "android.permission.RECORD_AUDIO",
    "android.permission.READ_CALENDAR", "android.permission.WRITE_CALENDAR",
    "android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE",
    "android.permission.READ_MEDIA_AUDIO", "android.permission.POST_NOTIFICATIONS",
    "android.permission.QUERY_ALL_PACKAGES", "android.permission.USE_BIOMETRIC",
    "android.permission.USE_FINGERPRINT",
}

URL_RE = re.compile(r"https?://([A-Za-z0-9._-]{4,})", re.I)
SENSITIVE_KEY_RE = re.compile(
    r"(api[_-]?key|secret|password|passwd|access[_-]?token|bearer|private[_-]?key)", re.I)
OPAQUE_RE = re.compile(r"(?<![A-Za-z0-9])[A-Fa-f0-9]{40,}(?![A-Za-z0-9])")
HOST_SKIP_RE = re.compile(r"^(localhost|127\.0\.0\.1|0\.0\.0\.0)$")


def uleb128(data: bytes, pos: int) -> tuple[int, int]:
    result = 0
    shift = 0
    while True:
        b = data[pos]
        pos += 1
        result |= (b & 0x7F) << shift
        if not b & 0x80:
            return result, pos
        shift += 7


def mutf8(data: bytes, pos: int) -> tuple[str, int]:
    _, pos = uleb128(data, pos)
    start = pos
    while data[pos] != 0:
        pos += 1
    raw = data[start:pos]
    return raw.decode("utf-8", errors="replace"), pos + 1


def scan_dex(data: bytes) -> dict:
    """Read only DEX metadata tables; returns counters, class names, host candidates."""
    if data[:4] != b"dex\n":
        raise ValueError("not a DEX file")
    u32 = lambda off: struct.unpack_from("<I", data, off)[0]
    string_count, string_off = u32(0x38), u32(0x3C)
    type_count, type_off = u32(0x40), u32(0x44)
    method_count = u32(0x58)
    class_count, class_off = u32(0x60), u32(0x64)
    magic = data[4:8].decode("ascii", errors="replace")

    strings: list[str] = []
    for i in range(string_count):
        off = u32(string_off + 4 * i)
        text, _ = mutf8(data, off)
        strings.append(text)

    string_len_hist = Counter(min(len(s) // 10 * 10, 100) for s in strings)
    hosts: Counter = Counter()
    sensitive_markers = 0
    opaque_values = 0
    zalopay_terms = 0
    for s in strings:
        if SENSITIVE_KEY_RE.search(s):
            sensitive_markers += 1
        if OPAQUE_RE.search(s):
            opaque_values += 1
        if "zalopay" in s.lower() or "vnpay" in s.lower():
            zalopay_terms += 1
        for host in URL_RE.findall(s):
            host = host.lower().strip(".")
            if not HOST_SKIP_RE.match(host) and "." in host:
                hosts[host] += 1

    type_ids = [strings[u32(type_off + 4 * i)] for i in range(type_count)]
    classes = []
    for i in range(class_count):
        idx = u32(class_off + 32 * i)
        if 0 <= idx < len(type_ids):
            desc = type_ids[idx]
            if desc.startswith("L") and desc.endswith(";"):
                classes.append(desc[1:-1].replace("/", "."))

    short_names = sum(1 for c in classes if len(c.rsplit(".", 1)[-1]) <= 2)
    return {
        "magic": magic,
        "string_count": string_count,
        "type_count": type_count,
        "method_id_count": method_count,
        "class_count": class_count,
        "class_names": classes,
        "string_length_histogram_bucket10": dict(sorted(string_len_hist.items())),
        "short_class_name_count": short_names,
        "short_class_name_ratio": round(short_names / class_count, 4) if class_count else 0,
        "url_hosts": dict(hosts),
        "sensitive_keyword_strings": sensitive_markers,
        "long_hex_strings": opaque_values,
        "brand_term_strings": zalopay_terms,
    }


def cert_summary(certs: list) -> list[dict]:
    out = []
    for c in certs:
        def safe(fn, default="n/a"):
            try:
                return str(fn())
            except Exception:
                return default
        out.append({
            "subject": safe(lambda: c.subject.native),
            "issuer": safe(lambda: c.issuer.native),
            "not_before": safe(lambda: c.not_valid_before),
            "not_after": safe(lambda: c.not_valid_after),
            "signature_algorithm": safe(lambda: c.signature_algo),
            "sha256": safe(c.sha256.hex),
        })
    return out


def analyze_apk(path: Path, role: str) -> dict:
    from androguard.core.apk import APK

    with contextlib.redirect_stderr(io.StringIO()):
        apk = APK(str(path))
    result: dict = {"role": role, "file": path.name, "bytes": path.stat().st_size}
    result["package"] = apk.get_package()
    result["version_name"] = apk.get_androidversion_name()
    result["version_code"] = apk.get_androidversion_code()
    result["min_sdk"] = apk.get_min_sdk_version()
    result["target_sdk"] = apk.get_target_sdk_version()

    perms = sorted(set(apk.get_permissions()))
    result["permissions"] = {
        "total": len(perms),
        "list": perms,
        "dangerous_or_sensitive": sorted(set(perms) & DANGEROUS_PERMS),
        "third_party": sorted(p for p in perms if not p.startswith("android.permission.")),
    }
    components = {}
    exported_counts = {}
    for tag, getter in (("activity", apk.get_activities), ("service", apk.get_services),
                        ("receiver", apk.get_receivers), ("provider", apk.get_providers)):
        names = sorted(set(getter()))
        components[tag] = names
        with contextlib.redirect_stderr(io.StringIO()):
            vals = list(apk.get_all_attribute_value(tag, "exported"))
        # androguard applies the package prefix to boolean values in AXML.
        exported_counts[tag] = {
            "declared_true": sum(str(v).lower().endswith(".true") or str(v).lower() == "true"
                                 for v in vals),
            "declared_false": sum(str(v).lower().endswith(".false") or str(v).lower() == "false"
                                  for v in vals),
            "no_explicit_attribute": len(names) - len(vals),
            "with_intent_filter": sum(
                1 for n in names if apk.get_intent_filters(tag, n)),
        }
    result["components"] = {k: {"count": len(v), "names": v} for k, v in components.items()}
    result["exported_declarations"] = exported_counts

    app_flags = {}
    for attr in ("allowBackup", "fullBackupContent", "dataExtractionRules", "debuggable",
                 "usesCleartextTraffic", "networkSecurityConfig", "extractNativeLibs",
                 "largeHeap", "requestLegacyExternalStorage"):
        with contextlib.redirect_stderr(io.StringIO()):
            app_flags[attr] = apk.get_attribute_value("application", attr)
    result["application_flags"] = app_flags
    result["signing"] = {
        "v1": apk.is_signed_v1(), "v2": apk.is_signed_v2(),
        "v3": apk.is_signed_v3(), "v3_1": apk.is_signed_v31(),
        "certificates": cert_summary(apk.get_certificates() or []),
    }

    with zipfile.ZipFile(path) as zf:
        entries = [(i.filename, i.file_size) for i in zf.infolist()]
        dex_data = {n: zf.read(n) for n, _ in entries if n.endswith(".dex")}
        result["native_libs"] = [
            {"name": n, "bytes": s} for n, s in entries if n.startswith("lib/")]
        result["assets_sample"] = [n for n, _ in entries if n.startswith("assets/")][:60]
        result["asset_count"] = sum(1 for n, _ in entries if n.startswith("assets/"))
        result["resource_count"] = sum(1 for n, _ in entries if n.startswith("res/"))
        result["zip_entries"] = len(entries)

    dex_results = {name: scan_dex(blob) for name, blob in dex_data.items()}
    all_classes = [c for d in dex_results.values() for c in d["class_names"]]
    sdk_counts = {sdk: sum(1 for c in all_classes if any(c.startswith(p) for p in pats))
                  for sdk, pats in SDK_PATTERNS.items()}
    hosts: Counter = Counter()
    for d in dex_results.values():
        hosts.update(d["url_hosts"])
    result["dex"] = {
        "file_count": len(dex_results),
        "files": {name: {k: v for k, v in d.items() if k != "class_names" and k != "url_hosts"}
                  for name, d in dex_results.items()},
        "totals": {
            "string_ids": sum(d["string_count"] for d in dex_results.values()),
            "method_ids": sum(d["method_id_count"] for d in dex_results.values()),
            "class_defs": len(all_classes),
            "unique_classes": len(set(all_classes)),
            "short_class_name_ratio": round(
                sum(d["short_class_name_count"] for d in dex_results.values())
                / max(len(all_classes), 1), 4),
        },
        "sdk_class_prefix_counts": dict(sorted(sdk_counts.items(), key=lambda kv: -kv[1])),
        "top_url_hosts": dict(hosts.most_common(25)),
        "distinct_url_hosts": len(hosts),
        "sensitive_keyword_strings": sum(d["sensitive_keyword_strings"] for d in dex_results.values()),
        "long_hex_strings": sum(d["long_hex_strings"] for d in dex_results.values()),
        "brand_term_strings": sum(d["brand_term_strings"] for d in dex_results.values()),
    }
    return result


def render_report(data: dict) -> str:
    base = data["apks"]["base"]
    t = base["dex"]["totals"]
    lines = [
        "# ZaloPay 11.17.0 — static analysis findings", "",
        "> Generated by `tools/analyze_apk.py`. Values are measurements of the artefact,",
        "> not claims about runtime behaviour. URLs are redacted to hostnames only.", "",
        "## 1. Corpus and identity", "",
        f"- Package: `{base['package']}` — version `{base['version_name']}` "
        f"(code `{base['version_code']}`).",
        f"- minSdk {base['min_sdk']} / targetSdk {base['target_sdk']}.",
    ]
    lines += [
        f"- Base APK: {base['bytes']:,} bytes, {base['zip_entries']:,} ZIP entries, "
        f"{base['resource_count']:,} resource files, {base['asset_count']:,} assets.",
        f"- Splits: {', '.join(k for k in data['apks'] if k != 'base')}.",
        "",
        "## 2. Manifest attack surface (base APK)", "",
        f"- {base['components']['activity']['count']} activities, "
        f"{base['components']['service']['count']} services, "
        f"{base['components']['receiver']['count']} receivers, "
        f"{base['components']['provider']['count']} providers.",
        f"- {base['permissions']['total']} permissions; "
        f"{len(base['permissions']['dangerous_or_sensitive'])} are runtime-sensitive "
        f"or high-impact (contacts, location, camera, microphone, storage, biometrics, "
        f"phone state, package visibility, notifications).",
        "- Export declarations by component type "
        "(`true` / `false` / no explicit attribute / with intent filter):",
    ]
    for tag, counts in base["exported_declarations"].items():
        lines.append(f"  - {tag}: {counts['declared_true']} / {counts['declared_false']} / "
                     f"{counts['no_explicit_attribute']} / {counts['with_intent_filter']}")
    lines += [
        f"- Application flags: `{base['application_flags']}`.",
        "",
        "## 3. Bytecode scale and structure", "",
        f"- {base['dex']['file_count']} DEX files in the base APK; "
        f"{t['string_ids']:,} string IDs, {t['method_ids']:,} method IDs, "
        f"{t['class_defs']:,} class definitions ({t['unique_classes']:,} unique).",
        f"- Short (1–2 character) class-name ratio: "
        f"{t['short_class_name_ratio']:.1%} — an obfuscation indicator, not a verdict.",
        "",
        "| SDK family (class-prefix heuristic) | Classes |",
        "| --- | ---: |",
    ]
    for sdk, count in list(base["dex"]["sdk_class_prefix_counts"].items())[:16]:
        lines.append(f"| {sdk} | {count:,} |")
    lines += [
        "",
        "## 4. Network-facing strings (redacted)", "",
        f"- {base['dex']['distinct_url_hosts']} distinct hosts extracted from URL literals.",
        "| Host (hostname only) | Occurrences |", "| --- | ---: |",
    ]
    for host, count in list(base["dex"]["top_url_hosts"].items())[:12]:
        lines.append(f"| `{host}` | {count:,} |")
    lines += [
        "",
        "## 5. Hardening observations", "",
        f"- Signature schemes present: "
        f"{', '.join(k.upper() for k, v in base['signing'].items() if k in ('v1','v2','v3','v3_1') and v)}.",
        f"- Signer subject: `{base['signing']['certificates'][0]['subject'] if base['signing']['certificates'] else 'n/a'}`.",
        f"- Backup disabled: `allowBackup={base['application_flags'].get('allowBackup')}`, "
        f"`fullBackupContent={base['application_flags'].get('fullBackupContent')}`.",
        f"- Explicit network security configuration referenced: "
        f"`{base['application_flags'].get('networkSecurityConfig')}` (presence only; "
        f"the XML policy itself is not evaluated here).",
        f"- Debuggable flag: `{base['application_flags'].get('debuggable')}` "
        f"(absent means the default, not a guarantee).",
        "",
        "## 6. Interpretation limits", "",
        "Class-prefix counts are heuristic; shaded or renamed SDKs will be missed or "
        "double-counted. No decompiled business logic, secret, credential, or bypass "
        "detail is published. See `docs/ETHICS.md`.", "",
    ]
    return "\n".join(lines)


def main() -> None:
    ap = argparse.ArgumentParser()
    ap.add_argument("--xapk", type=Path, required=True)
    ap.add_argument("--out", type=Path, default=Path("research"))
    args = ap.parse_args()
    args.out.mkdir(parents=True, exist_ok=True)

    with zipfile.ZipFile(args.xapk) as zf:
        names = zf.namelist()
        apk_names = [n for n in names if n.endswith(".apk")]
        work = args.out / "_apk_work"
        work.mkdir(parents=True, exist_ok=True)
        for n in apk_names:
            target = work / Path(n).name
            if not target.exists():
                target.write_bytes(zf.read(n))
        manifest = json.loads(zf.read("manifest.json")) if "manifest.json" in names else None

    apks = {}
    for n in apk_names:
        name = Path(n).name
        role = "base" if name.startswith("vn.com.") else name
        apks[role] = analyze_apk(work / name, role)

    data = {
        "tool": "tools/analyze_apk.py",
        "method": "DEX header/table streaming + androguard manifest parsing (no CFG)",
        "xapk_manifest": manifest,
        "apks": apks,
    }
    (args.out / "findings.json").write_text(
        json.dumps(data, indent=2, ensure_ascii=False) + "\n", encoding="utf-8")
    (args.out / "FINDINGS.md").write_text(render_report(data), encoding="utf-8")
    t = apks["base"]["dex"]["totals"]
    print(json.dumps({"apk_count": len(apks), "dex_files": apks["base"]["dex"]["file_count"],
                      "class_defs": t["class_defs"], "hosts": apks["base"]["dex"]["distinct_url_hosts"]},
                     indent=2))


if __name__ == "__main__":
    main()
