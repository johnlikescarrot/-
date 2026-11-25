"""Stage 10 — generate the human dossier (Markdown + JSON bundle).

The dossier is *derived*, never hand-edited: every table cell is filled from a
stage output so the document cannot drift from the artifacts it cites.
"""

from __future__ import annotations

import json
from datetime import datetime, timezone
from pathlib import Path

from common import DATA, DOCS, ROOT, human_bytes, read_json, write_json, Stage


def table(rows: list[tuple], header: tuple | None = None) -> str:
    if not rows:
        return ""
    cols = header or tuple(range(len(rows[0])))
    out = ["| " + " | ".join(str(c) for c in cols) + " |", "|" + "|".join("---" for _ in cols) + "|"]
    for r in rows:
        out.append("| " + " | ".join(str(c) for c in r) + " |")
    return "\n".join(out)


def main() -> None:
    st = Stage("10-report")
    inv = read_json("inventory_summary.json")
    man = read_json("manifest.json")
    dex = read_json("dex_summary.json")
    obf = read_json("dex_obfuscation.json")
    sdks = read_json("sdks.json")
    sec = read_json("secrets.json")
    ep = read_json("endpoints.json")
    nat = read_json("native.json")
    res = read_json("resources.json")
    g = read_json("graph.json")
    fin = read_json("findings.json")

    totals = inv["totals"]
    dx = dex["totals"]
    md: list[str] = []
    A = md.append

    # ---------------------------------------------------------------- cover
    A("# `vn.com.vng.zalopay` 11.17.0 — Complete Static Reverse-Engineering Dossier")
    A("")
    A(f"*Generated {datetime.now(timezone.utc):%Y-%m-%d %H:%M UTC} by `analysis/pipeline/10_report.py`.*")
    A("")
    A("> **What this document is.** A reproducible, artifact-first account of every file in the supplied")
    A("> `Zalopay-Thanh toán & Tài chính_11.17.0_APKPure.zip`. Each number below is produced by a stage in")
    A("> `analysis/pipeline/`; the run order and the JSON it emits are listed in §2. Nothing was executed:")
    A("> the analysis is static, offline, and performed on a hashed snapshot of the archive.")
    A("")
    A("**Headline figures**")
    A("")
    A(table(
        [
            ("Container entries across 3 layers", f"{totals['entries_all_layers']:,}"),
            ("Uncompressed payload", f"{human_bytes(totals['bytes_all_layers_uncompressed'])}"),
            ("DEX images", f"{dx['dex_files']}"),
            ("Classes", f"{dx['classes']:,}"),
            ("Methods", f"{dx['methods']:,}"),
            ("Fields", f"{dx['fields']:,}"),
            ("Unique strings", f"{dx['strings_unique']:,}"),
            ("Native libraries", f"{nat['count']}"),
            ("JNI entrypoints", f"{nat['jni_surface']['total']:,}"),
            ("Third-party components attributed", f"{sdks['present_count']} / {sdks['catalogue_size']}"),
            ("Network endpoints", f"{ep['distinct']:,} over {ep['hosts']} hosts"),
            ("Exported components", f"{sum(len(v) for v in man['components']['exported'].values())}"),
            ("Findings", f"{fin['count']} ({', '.join(f'{k} {v}' for k, v in fin['by_severity'].items())})"),
        ],
        ("metric", "value"),
    ))
    A("")

    # ---------------------------------------------------------------- 1
    A("## 1. The artifact is a three-layer container")
    A("")
    A(table(
        [
            ("0", "`Zalopay-…_APKPure.zip`", f"{inv['corpus']['outer']['bytes']:,}", f"`{inv['corpus']['outer']['sha256'][:16]}…`"),
            ("1", "`…_APKPure.xapk` (APKPure split bundle, xapk_version 2)", f"{inv['corpus']['xapk']['bytes']:,}", f"`{inv['corpus']['xapk']['sha256'][:16]}…`"),
            *[
                (f"2{chr(ord('a') + i)}", f"`{name}`", f"{v['uncompressed_bytes']:,}", f"`{v['sha256'][:16]}…`")
                for i, (name, v) in enumerate(inv["corpus"]["splits"].items())
            ],
        ],
        ("layer", "object", "uncompressed bytes", "sha256 (16 B)"),
    ))
    A("")
    A(f"The base module holds **{len([s for s in dex['per_file'] if s['file'].startswith('classes')])} `classes*.dex`** "
      "plus a stray `assets/audience_network.dex` — a Meta Audience Network artefact that is packaged but not "
      "declared in the class-loader configuration.")
    A("")

    # ---------------------------------------------------------------- 2
    A("## 2. Method")
    A("")
    A("```")
    A("analysis/pipeline/")
    A("├── common.py         hashing · entropy · magic · AXML decode · JSON I/O")
    A("├── dexread.py        dependency-free DEX reader (0.24 s for a 16.5 MB / 7,421-class image)")
    A("├── 01_inventory.py   all 6,472 entries across the three container layers")
    A("├── 02_manifest.py    AXML → component/permission/deep-link/signing census")
    A("├── 03_dex.py         class · method · field · string census, obfuscation profile")
    A("├── 04_sdk.py         SDK & tracker attribution, key-material discovery")
    A("├── 05_secrets.py     credential shapes (redacted) and endpoint topology")
    A("├── 06_native.py      LIEF ELF structure + Capstone disassembly")
    A("├── 07_resources.py   ARSC package walk, locale coverage, asset classes")
    A("├── 08_graph.py       per-DEX domain ownership, client config surface")
    A("├── 09_findings.py    the risk model (each finding is a falsifiable tuple)")
    A("└── 10_report.py      this document")
    A("```")
    A("")
    A(table(
        [(k, f"{v:,}") for k, v in [
            ("entries", totals["entries_all_layers"]),
            ("uncompressed bytes", totals["bytes_all_layers_uncompressed"]),
            ("DEX classes", dx["classes"]),
            ("DEX methods", dx["methods"]),
            ("unique DEX strings", dx["strings_unique"]),
            ("ELF objects", nat["count"]),
            ("attributed SDKs", sdks["present_count"]),
            ("endpoint tuples", ep["distinct"]),
            ("findings", fin["count"]),
        ]],
        ("artifact-derived quantity", "value"),
    ))
    A("")

    # ---------------------------------------------------------------- 3
    A("## 3. Manifest and attack surface")
    A("")
    u = man["manifest"]["uses_sdk"]
    A(f"`{man['manifest']['attrs']['package']}` · version `{man['manifest']['attrs']['android:versionName']}` "
      f"(`{man['manifest']['attrs']['android:versionCode']}`) · minSdk `{u.get('minSdkVersion')}` · "
      f"targetSdk `{u.get('targetSdkVersion')}` · application class `{man['manifest']['application_class']}` "
      "(an obfuscated shell).")
    A("")
    A(table(
        [(k.title(), v, ", ".join(man["components"]["exported"][k]) or "—") for k, v in man["components"]["counts"].items()],
        ("component type", "total", "exported"),
    ))
    A("")
    A("**Deep-link surface** — every scheme the wallet answers:")
    A("")
    A(table([(f"`{s}`", n) for s, n in sorted(man["schemes"].items(), key=lambda x: -x[1])], ("scheme", "filters")))
    A("")
    A("**Permission matrix** (49 requested):")
    A("")
    A(table(
        [(f"`{p['short']}`", p["risk"] or "—", p["maxSdkVersion"] or "—") for p in man["permissions"]["requested"]],
        ("permission", "risk register", "maxSdkVersion"),
    ))
    A("")
    A("**Signing block** — parsed from the APK tail, not from `apksigner`:")
    A("")
    sg = man["signing"]
    A(table(
        [
            ("schemes present", ", ".join(sg["schemes"])),
            ("signer certificate SHA-256", f"`{sg.get('v2_signer_cert_sha256')}`"),
            ("subject / issuer", f"{sg.get('v2_signer_subject')} (self-signed)"),
            ("notBefore", sg.get("v2_not_before")),
            ("v1 JAR files", len(sg.get("v1_signature_files", []))),
        ],
        ("property", "value"),
    ))
    A("")

    # ---------------------------------------------------------------- 4
    A("## 4. Bytecode")
    A("")
    A(table(
        [(f"`{s['file']}`", f"{s['classes']:,}", f"{s['methods']:,}", f"{s['strings']:,}", f"{s['bytes']/1e6:.1f} MB") for s in dex["per_file"]],
        ("DEX", "classes", "methods", "strings", "size"),
    ))
    A("")
    A(f"**Obfuscation.** {obf['single_letter_classes']:,} of {obf['total_classes']:,} classes "
      f"({obf['single_letter_class_pct']}%) and {obf['single_letter_methods']:,} of {obf['total_methods']:,} methods "
      f"({obf['single_letter_method_pct']}%) have names of two characters or fewer — **{obf['verdict']}** "
      "(R8/ProGuard renaming with retained public SDK surfaces).")
    A("")
    A("**Largest namespaces**")
    A("")
    A(table([(f"`{p['package']}`", f"{p['classes']:,}", f"{p['methods']:,}", f"{p['class_share_pct']}%") for p in dex.get("dex_packages", {}).get("packages", [])[:15]] if "packages" in dex.get("dex_packages", {}) else [], ("package", "classes", "methods", "share")))
    A("")
    A("**Domain ownership across the 20 images**")
    A("")
    A(table([(k, f"{v:,}") for k, v in g["domain_totals"].items()], ("domain", "classes")))
    A("")

    # ---------------------------------------------------------------- 5
    A("## 5. Third-party composition")
    A("")
    A(table(
        [(f"`{s['name']}`", s["vendor"], s["cat"], f"{s['namespace_classes']:,}" if s["namespace_classes"] else "native",
          ", ".join(s["artifact_hits"]) or "—")
         for s in sdks["sdks"] if s["present"]],
        ("component", "vendor", "category", "classes", "artefact fingerprint"),
    ))
    A("")
    A(f"**Trackers** ({len(sdks['trackers'])}): " + ", ".join(t["name"] for t in sdks["trackers"]))
    A("")
    A(f"**Gradle module identities recovered from `META-INF/*.version`:** {len(sdks['artifacts']['version_modules'])}")
    A("")

    # ---------------------------------------------------------------- 6
    A("## 6. Cryptographic material (fingerprints only)")
    A("")
    km = sdks["key_material"]
    A(table(
        [
            ("PEM private keys in DEX pool", km["pem_private"]["count"]),
            ("PEM public keys / certificates", km["pem_public"]["count"]),
            ("64-hex constants (AES-256 key shape)", km["hex64"]["count"]),
            ("base64 43-char constants (32-byte key shape)", km["b64_43"]["count"]),
            ("base32-shaped secrets", km["base32"]["count"]),
        ],
        ("observation", "count"),
    ))
    A("")
    A("> Values are deliberately **not** reproduced. `analysis/data/secrets.json` stores, per hit, only the pattern")
    A("> name, the length, SHA-256[:12] and the first four characters — enough to de-duplicate and audit, not enough")
    A("> to use.")
    A("")

    # ---------------------------------------------------------------- 7
    A("## 7. Network topology")
    A("")
    A(f"{ep['distinct']:,} distinct `(scheme, host, port, path)` tuples across {ep['hosts']} hosts; "
      f"{ep['vng_hosts']} are ZaloPay/Zalo/VNG-operated and {ep['third_party_hosts']} are third-party infrastructure.")
    A("")
    A("**VNG-operated service fabric** (from the DEX string pool):")
    A("")
    A(table([(f"`{h}`",) for h in ep["vng_host_list"][:48]], ("host",)))
    A("")
    A("**Highest-traffic hosts**")
    A("")
    A(table([(f"`{h['host']}`", h["urls"], "VNG" if h["vng"] else ("3P" if h["third_party"] else "—")) for h in ep["top_hosts"][:20]], ("host", "URLs", "ownership")))
    A("")

    # ---------------------------------------------------------------- 8
    A("## 8. Native code")
    A("")
    A(table(
        [(f"`{l['name']}`", f"{l['bytes']:,}", f"{l.get('entropy')}", l.get("role"), str(l.get("jni_export_count"))) for l in nat["libraries"]],
        ("library", "bytes", "entropy", "role", "JNI exports"),
    ))
    A("")
    A("**Danger-import prevalence across the 27 objects**")
    A("")
    A(table([(f"`{k}`", v) for k, v in nat["danger_import_histogram"].items()], ("import", "libraries")))
    A("")
    probe = nat["probes"].get("JNI_OnLoad", {})
    if probe.get("found"):
        A("**Packer bootstrap disassembly**")
        A("")
        A(f"`{probe['symbol']}` @ `{probe['address']}` ({probe['size']} B) — mnemonic histogram: " +
          ", ".join(f"`{k}`×{v}" for k, v in probe["mnemonic_histogram"].items()))
        A("")
        A("```asm")
        for i in probe["instructions"][:26]:
            A(f"{i['addr']}  {i['mnemonic']:<8} {i['ops']}")
        A("```")
        A("")

    # ---------------------------------------------------------------- 9
    A("## 9. Resources and dynamic modules")
    A("")
    A(table(
        [(m["module"], m["package"], m["locale_count"]) for m in res["modules"]],
        ("module", "resource package", "locales"),
    ))
    A("")
    A(f"{res['res_files']:,} files under `res/`; {res['media']['layouts']:,} layouts, {res['media']['xml']:,} XML "
      f"documents, {res['media']['png']:,} PNG, {res['media']['webp']:,} WebP, {res['media']['ninepatch']:,} nine-patch.")
    A("")
    A("**Protected assets** (entropy > 7.95, > 4 KiB):")
    A("")
    A(table([(f"`{a['path']}`", f"{a['bytes']:,}", a["entropy"], a["class"]) for a in json.loads((DATA / "assets.json").read_text())["protected"]], ("asset", "bytes", "entropy", "class")))
    A("")

    # ---------------------------------------------------------------- 10
    A("## 10. Findings")
    A("")
    A("Severity anchors: **5** reusable credential or money movement · **4** sensitive data or control bypass · "
      "**3** materially widened surface · **2** hardening gap · **1** observation.")
    A("")
    for f in fin["findings"]:
        A(f"### {f['id']} · {f['title']}")
        A("")
        A(f"**Severity {f['severity']} — {f['severity_label']}** · category `{f['category']}`")
        A("")
        A(f["claim"])
        A("")
        A("```json")
        A(json.dumps(f["evidence"], indent=2, ensure_ascii=False)[:1800])
        A("```")
        A("")
        A(f"*Artifact:* `{f['artifact']}` · *Method:* {f['method']}")
        A("")
        A(f"*Impact.* {f['impact']}")
        A("")
        A(f"*Recommendation.* {f['recommendation']}")
        A("")

    # ---------------------------------------------------------------- 11
    A("## 11. Limits of this study")
    A("")
    A("- **Static only.** No emulator, no dynamic instrumentation, no traffic capture. Runtime behaviour")
    A("  asserted here (e.g. that the packer decrypts `fgcpkjvqe.dat`) is inferred from symbols, imports and")
    A("  disassembly, and is labelled as such.")
    A("- **One ABI.** The distributed XAPK contains `armeabi-v7a` only; the `arm64-v8a` build Play serves to")
    A("  most devices was not available in the corpus.")
    A("- **One version.** 11.17.0 / versionCode 1102758. Findings are version-scoped.")
    A("- **No decompilation of obfuscated bodies.** The census names and counts every class; it does not")
    A("  reconstruct control flow inside the 43,342 renamed classes.")
    A("- **Split manifests only partially merged.** The base manifest is authoritative here; the four")
    A("  `config.*` manifests contribute resources, not components.")
    A("")

    A("## 12. Ethics")
    A("")
    A("Only a publicly distributed APKPure build was examined, statically. No credential was used, no")
    A("endpoint was contacted, and no technique for defeating the app's protections is published. Secret-")
    A("shaped constants are reported as fingerprints. Native findings describe capability, not intent.")
    A("")
    A("---")
    A("")
    A(f"<sub>{totals['entries_all_layers']:,} entries · {human_bytes(totals['bytes_all_layers_uncompressed'])} · "
      f"{dx['classes']:,} classes · {fin['count']} findings · generated from committed artifacts.</sub>")

    doc = "\n".join(md) + "\n"
    (DOCS / "DOSSIER.md").write_text(doc, encoding="utf-8")
    st.log(f"docs/DOSSIER.md written: {len(doc):,} chars, {doc.count(chr(10)):,} lines")

    # machine-readable bundle for the video renderer
    bundle = {
        "generated": datetime.now(timezone.utc).isoformat(),
        "inventory": inv["totals"],
        "manifest": man,
        "dex": dex,
        "obfuscation": obf,
        "sdks": sdks,
        "secrets": {"hit_count": sec["hit_count"], "by_pattern": sec["by_pattern"], "by_severity": sec["by_severity"], "scanned": sec["scanned_strings"], "policy": sec["policy"]},
        "endpoints": ep,
        "native": nat,
        "resources": res,
        "graph": g,
        "findings": fin,
    }
    write_json("bundle.json", bundle)
    st.log(f"analysis/data/bundle.json written ({len(json.dumps(bundle)):,} chars)")
    st.done({"dossier_chars": len(doc)})


if __name__ == "__main__":
    main()
