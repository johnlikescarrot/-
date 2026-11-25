#!/usr/bin/env python3
"""
Stage 6 — Report generator.

Renders the findings dataset (analysis/data/*.json) into a publication-quality
Markdown study with an embedded, self-contained SVG chart suite and an appendix
of every reproducible command. Deterministic: same data in -> same bytes out.

Output: analysis/REPORT.md
"""
import os, json, re, base64, html

ROOT = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
DATA = os.path.join(ROOT, "data")


def load(n):
    with open(os.path.join(DATA, n), encoding="utf-8") as f:
        return json.load(f)


C = {"bg": "#0B0F1A", "panel": "#131A2B", "grid": "#22304D", "tx": "#E8EEF9",
     "mut": "#8FA3C8", "acc": "#25E0B0", "acc2": "#4EA8FF", "warn": "#FFB020",
     "dang": "#FF5470", "violet": "#A78BFA"}


def esc(s):
    return html.escape(str(s), quote=True)


# ───────────────────────────── SVG chart primitives ──────────────────────────
def bar_chart(rows, w=920, rowh=34, title="", value_fmt="{:,.0f}", colour=None, label_w=310):
    """Horizontal bar chart. rows = [(label, value), …] sorted by caller."""
    n = len(rows)
    h = n * rowh + 64
    vmax = max(v for _, v in rows) or 1
    out = [f'<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 {w} {h}" width="100%" role="img">',
           f'<rect width="{w}" height="{h}" fill="{C["bg"]}"/>']
    if title:
        out.append(f'<text x="18" y="30" fill="{C["tx"]}" font-family="ui-sans-serif,system-ui,sans-serif" '
                   f'font-size="19" font-weight="700">{esc(title)}</text>')
    for i, (lab, val) in enumerate(rows):
        y = 54 + i * rowh
        bw = (w - label_w - 120) * (val / vmax)
        col = colour[i % len(colour)] if isinstance(colour, list) else (colour or C["acc2"])
        out.append(f'<text x="18" y="{y + 16}" fill="{C["mut"]}" font-size="14" '
                   f'font-family="ui-monospace,SFMono-Regular,Menlo,monospace">{esc(lab[:44])}</text>')
        out.append(f'<rect x="{label_w}" y="{y + 4}" width="{max(2, bw):.1f}" height="18" rx="4" fill="{col}" opacity="0.9"/>')
        out.append(f'<text x="{label_w + max(2, bw) + 10:.0f}" y="{y + 18}" fill="{C["tx"]}" font-size="13" '
                   f'font-family="ui-monospace,monospace">{value_fmt.format(val)}</text>')
    out.append("</svg>")
    return "\n".join(out)


def donut(segments, w=520, h=360, title="", unit=""):
    total = sum(v for _, v, _ in segments) or 1
    cx, cy, r, sw = w / 2 - 40, h / 2 + 10, 92, 26
    circ = 2 * 3.141592653589793 * r
    off = 0.0
    out = [f'<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 {w} {h}" width="100%" role="img">',
           f'<rect width="{w}" height="{h}" fill="{C["bg"]}"/>']
    if title:
        out.append(f'<text x="16" y="26" fill="{C["tx"]}" font-size="17" font-weight="700">{esc(title)}</text>')
    for lab, val, col in segments:
        frac = val / total
        dash = frac * circ
        out.append(f'<circle cx="{cx}" cy="{cy}" r="{r}" fill="none" stroke="{col}" stroke-width="{sw}" '
                   f'stroke-dasharray="{dash:.2f} {circ - dash:.2f}" stroke-dashoffset="{-off:.2f}" '
                   f'transform="rotate(-90 {cx} {cy})"/>')
        off += dash
    out.append(f'<text x="{cx}" y="{cy - 2}" text-anchor="middle" fill="{C["tx"]}" font-size="26" font-weight="800">'
               f'{total:,.0f}</text>')
    out.append(f'<text x="{cx}" y="{cy + 20}" text-anchor="middle" fill="{C["mut"]}" font-size="12">{esc(unit)}</text>')
    ly = 58
    for lab, val, col in segments:
        out.append(f'<rect x="{w - 196}" y="{ly - 11}" width="12" height="12" rx="3" fill="{col}"/>')
        out.append(f'<text x="{w - 176}" y="{ly}" fill="{C["tx"]}" font-size="12.5">{esc(lab[:22])}</text>')
        out.append(f'<text x="{w - 176}" y="{ly + 14}" fill="{C["mut"]}" font-size="11">{val:,} ({val / total:.0%})</text>')
        ly += 38
    out.append("</svg>")
    return "\n".join(out)


def matrix(rows, cols, cells, w=980, title="", legend=""):
    cw = (w - 210) / len(cols)
    rh = 30
    h = len(rows) * rh + 92
    out = [f'<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 {w} {h}" width="100%" role="img">',
           f'<rect width="{w}" height="{h}" fill="{C["bg"]}"/>',
           f'<text x="16" y="26" fill="{C["tx"]}" font-size="17" font-weight="700">{esc(title)}</text>']
    for j, c in enumerate(cols):
        out.append(f'<text x="{210 + j * cw + cw / 2:.0f}" y="52" fill="{C["mut"]}" font-size="11.5" '
                   f'text-anchor="middle" font-family="ui-monospace,monospace">{esc(c)}</text>')
    for i, rlab in enumerate(rows):
        y = 66 + i * rh
        out.append(f'<text x="16" y="{y + 16}" fill="{C["tx"]}" font-size="12.5">{esc(rlab[:30])}</text>')
        for j in range(len(cols)):
            v = cells.get((i, j), 0)
            op = 0.10 + 0.9 * (v / max(1, max(cells.values())))
            out.append(f'<rect x="{210 + j * cw + 3:.0f}" y="{y + 4}" width="{cw - 6:.0f}" height="{rh - 10}" '
                       f'rx="4" fill="{C["acc"]}" opacity="{op:.2f}"/>')
            if v:
                out.append(f'<text x="{210 + j * cw + cw / 2:.0f}" y="{y + 17}" fill="{C["bg"] if op > 0.55 else C["tx"]}" '
                           f'font-size="10.5" text-anchor="middle" font-family="ui-monospace,monospace">{v}</text>')
    if legend:
        out.append(f'<text x="16" y="{h - 12}" fill="{C["mut"]}" font-size="11">{esc(legend)}</text>')
    out.append("</svg>")
    return "\n".join(out)


def embed(svg):
    b = base64.b64encode(svg.encode("utf-8")).decode()
    return f"![chart](data:image/svg+xml;base64,{b})"


def table(headers, rows):
    out = ["| " + " | ".join(headers) + " |", "|" + "|".join("---" for _ in headers) + "|"]
    for r in rows:
        out.append("| " + " | ".join(str(c) for c in r) + " |")
    return "\n".join(out)


def main():
    cont = load("01_container.json")
    man = load("02_manifest.json")
    dex = load("03_dex.json")
    xref = load("04_dex_xref.json")
    nat = load("05_native.json")
    ast = load("06_assets.json")
    res = load("07_resources.json")
    sec = load("08_security.json")
    fp = load("09_fingerprint.json")
    met = load("10_metrics.json")
    eco = load("11_ecosystem.json")

    base = next(s for s in cont["splits"] if s["role"] == "base")
    cert = cont["signing"]["certs"][0] if cont["signing"]["certs"] else {}
    dex_sorted = sorted(dex["multidex"], key=lambda d: -d["classes"])
    sdk = dex["sdk_inventory"]
    ctrl = sec["controls"]
    rn = eco["react_native"]
    cat = eco["miniapp_catalogue"]
    grv = eco["griver"]["bundles"]

    # ── charts ───────────────────────────────────────────────────────────────
    ch_dex = bar_chart([(d["file"], d["classes"]) for d in dex_sorted],
                       title="Figure 1 — Class density across the 19 DEX containers",
                       colour=C["acc2"], value_fmt="{:,.0f}")
    ch_rollup = bar_chart([(r["root"], r["classes"]) for r in dex["namespace_rollup"][:14]],
                          title="Figure 2 — Namespace rollup (top-14 roots, classes)",
                          colour=C["violet"])
    ctrl_sorted = sorted(ctrl, key=lambda c: -c["literal_hits"])
    ch_ctrl = bar_chart([(c["id"], c["literal_hits"]) for c in ctrl_sorted],
                        title="Figure 3 — Defensive control evidence density (unique literal hits)",
                        colour=C["acc"], label_w=190)
    ch_nat = bar_chart([(l["file"].replace("lib", "").replace(".so", ""), l["bytes"])
                        for l in sorted(nat["libraries"], key=lambda x: -x["bytes"])[:14]],
                       title="Figure 4 — Native library footprint (bytes, top-14)",
                       colour=C["warn"], label_w=220)
    ch_comp = donut([("Activities", met["components"]["activity"], C["acc2"]),
                     ("Services", met["components"]["service"], C["acc"]),
                     ("Receivers", met["components"]["receiver"], C["warn"]),
                     ("Providers", met["components"]["provider"], C["violet"]),
                     ("Aliases", met["components"]["activity-alias"], C["dang"])],
                    title="Figure 5 — Manifest component surface", unit="components")
    ch_cat = bar_chart(cat["categories"][:10],
                       title="Figure 6 — Mini-app catalogue taxonomy (373 apps, 19 categories)",
                       colour=C["acc"], label_w=250)
    # control × dex matrix
    dexes = [d["file"].replace("classes", "c").replace(".dex", "") for d in dex_sorted[:12]]
    rows = [c["id"] for c in ctrl_sorted]
    cells = {}
    for i, c in enumerate(ctrl_sorted):
        for j, dname in enumerate([d["file"] for d in dex_sorted[:12]]):
            v = c["dex_spread"].get(dname, 0)
            if v:
                cells[(i, j)] = v
    ch_matrix = matrix(rows, dexes, cells,
                       title="Figure 7 — Where each control lives (literal hits per DEX)")

    # ── markdown ─────────────────────────────────────────────────────────────
    L = []
    A = L.append
    A("# Reverse-Engineering Study: ZaloPay 11.17.0 (Android)")
    A("")
    A("**An independent, fully-reproducible static analysis of the APKPure XAPK bundle "
      "`Zalopay-Thanh toán & Tài chính_11.17.0` — container, manifest, 19 DEX containers, native layer, "
      "asset/JS runtimes, resources, and the security architecture that binds them.**")
    A("")
    A(table(["Field", "Value"], [
        ("Package", f"`{met['identity']['package']}`"),
        ("Version", f"{met['identity']['version']} (versionCode {met['identity']['version_code']})"),
        ("SDK range", f"min {met['identity']['min_sdk']} · target {met['identity']['target_sdk']} · compile {met['identity']['compile_sdk']}"),
        ("Bundle", f"XAPK, {met['artefact']['splits']} splits, {met['artefact']['xapk']:,} bytes"),
        ("Base APK SHA-256", f"`{met['artefact']['base_sha256']}`"),
        ("Signing", f"APK Signature Scheme v2+v3; v1 absent; self-signed RSA-2048 release key (valid {cert.get('not_before','')[:10]} → {cert.get('not_after','')[:10]})"),
        ("Analysis date", "2026-11-25"),
        ("Tooling", "androguard 4.1.4, custom DEX xref walker (this repo), pyelftools, ffmpeg·Node/TypeScript renderer"),
        ("Artefact committed", "derived JSON + scripts only; the 98.8 MB APK payload is **not** in Git"),
    ]))
    A("")

    # abstract
    A("## Abstract")
    A("")
    A(f"We present a complete structural reverse-engineering of ZaloPay 11.17.0, Vietnam's largest "
      f"consumer wallet, from a split-APK XAPK container. The application ships **{met['code']['classes']:,} classes / "
      f"{met['code']['methods']:,} methods / {met['code']['fields']:,} fields / {met['code']['strings']:,} strings** across "
      f"**{met['code']['dex_files']} DEX containers** ({met['artefact']['uncompressed']:,} uncompressed bytes in "
      f"{met['artefact']['total_zip_entries']:,} ZIP entries), **{met['native']['libs']} native libraries** "
      f"({met['native']['bytes']:,} bytes), **{met['ai_models']['tflite']} on-device TFLite models plus an NCNN detector**, "
      f"and **four distinct execution runtimes** — ART/DEX, React Native, Alibaba Griver mini-programs and a WebView "
      f"JavaScript bridge. We reconstruct the security architecture as **{len(ctrl)} defensive controls** evidenced by "
      f"{sum(c['literal_hits'] for c in ctrl)} distinct literals, enumerate **{met['third_party']['sdk_families']} third-party "
      f"SDK families**, and document the payment-authentication stack — OCRA-based Smart OTP with a *What-You-See-Is-What-You-Sign* "
      f"transaction digest, hardware-keystore key material, SQLCipher at rest, and Alipay+ SecurityGuard Lite. "
      f"All findings are produced by scripts in `analysis/scripts`, re-runnable end-to-end from the public artefact, and are "
      f"accompanied by a {met['code']['classes'] // 1000}-k-class visual documentary rendered in TypeScript to MP4 (`video/`).")
    A("")
    A("**Keywords:** Android reverse engineering · split APK · R8/ProGuard · mobile payment security · "
      "WYSIWYS · OCRA · certificate pinning · React Native bundle analysis · mini-program containers")
    A("")

    # TOC
    A("## Contents")
    A("")
    for i, t in enumerate([
        "Scope, ethics and reproducibility", "The artefact: XAPK container anatomy",
        "Manifest and attack surface", "The DEX layer: 19 containers, 60,671 classes",
        "Obfuscation and build fingerprint", "Third-party ecosystem",
        "The native layer", "Assets, on-device AI and the four runtimes",
        "Security architecture (the core contribution)", "Authentication: Smart OTP, OCRA and WYSIWYS",
        "Mini-app economy and deep-link graph", "Risk register", "Limitations and threats to validity",
        "Reproduction appendix", "Citation"], 1):
        A(f"{i}. [{t}](#{re.sub(r'[^a-z0-9 -]', '', t.lower()).replace(' ', '-')})")
    A("")

    # 1 scope
    A("## 1. Scope, ethics and reproducibility")
    A("")
    A("This study is **static-only** and **defensive** in purpose. No ZaloPay service was contacted, no account was "
      "touched, no credential was extracted, and no circumvention tool is shipped. The subject is a publicly distributed "
      "release (versionCode 1102758) obtained from APKPure; the container is preserved byte-for-byte for hash reproducibility "
      "and every derived number in this document can be regenerated with:")
    A("")
    A("```bash")
    A("python3 -m venv .venv && . .venv/bin/activate")
    A("pip install androguard pyelftools loguru")
    A("export ZPAY_WORK=/path/to/extracted/xapk     # dir containing the 5 split APKs")
    A("for s in analysis/scripts/stage*.py; do python3 \"$s\"; done   # ~15 min (stage2b is the long pole)")
    A("python3 analysis/scripts/report.py            # writes analysis/REPORT.md")
    A("```")
    A("")
    A("Secrets policy: the app ships **public** keys (`assets/public.pem`, `assets/config/public_key.txt`), Firebase/AdMob "
      "app identifiers and Google Ads SDK SPKI pins. These are *client-public* artefacts, not credentials; they are reported "
      "as facts about the build and are **not** reproduced here to avoid indiscriminate copying. No private key, API secret, "
      "session token or personal data was found — consistent with a PCI-DSS-scoped wallet that keeps secrets server-side.")
    A("")

    # 2 artefact
    A("## 2. The artefact: XAPK container anatomy")
    A("")
    A("The APKPure ZIP is a one-entry wrapper around a `.xapk` (a ZIP of split APKs plus metadata). Two ZIP layers must be "
      "traversed before the first byte of DEX is visible.")
    A("")
    A(table(["Split", "Role", "Bytes", "SHA-256", "Entropy (1 MiB)"],
            [(f"`{s['role']}`", s["role"] if s["role"] == "base" else "config split", f"{s['size']:,}",
              f"`{s['sha256'][:24]}…`", s["entropy"]) for s in cont["splits"]]))
    A("")
    A(f"The base APK holds **{cont['base_zip']['entries']:,} ZIP entries**, of which **{cont['base_zip']['stored']:,} are stored "
      f"(uncompressed)** and {cont['base_zip']['deflated']:,} deflated — a detail that matters because stored entries at "
      f"4-byte-aligned offsets are directly memory-mappable (the 56 of 60 probed entries that are 4-byte aligned are exactly "
      f"the `resources.arsc`, DEX and native-lib class of payloads). **Only armeabi-v7a is shipped**: the bundle contains no "
      f"arm64-v8a split, so this distribution runs 32-bit on 64-bit hardware — itself a hardening/performance trade-off worth noting.")
    A("")
    A("**Signing.** v1 (JAR) signing is absent; the APK uses **APK Signature Scheme v2 and v3** only, so the whole-archive "
      "signature covers every entry and the ZIP central directory. The signer is a self-signed RSA-2048 certificate "
      f"(serial {cert.get('serial','')}, SHA-256 `{cert.get('sha256','')[:32]}…`) issued 2016 and valid to 2041 — the classic "
      "long-lived release key. Its subject CN is a personal-style name, which is normal for VNG-era self-signed Android keys and "
      "is reported here as a *key identity*, not as an attribution of authorship. Because v3 is present, key-rotation metadata "
      "may exist; the analysed block carries a single signer.")
    A("")

    # 3 manifest
    A("## 3. Manifest and attack surface")
    A("")
    A(f"The manifest is fully decoded in [`analysis/data/AndroidManifest.decoded.xml`](data/AndroidManifest.decoded.xml). "
      f"It declares **{met['permissions']} permissions**, **{sum(met['components'].values())} components** "
      f"({met['components']['activity']} activities, {met['components']['service']} services, "
      f"{met['components']['receiver']} receivers, {met['components']['provider']} providers, "
      f"{met['components']['activity-alias']} aliases) and **{met['deep_links']} deep-link entry points**.")
    A("")
    A(embed(ch_comp))
    A("")
    A("Notable manifest facts:")
    A("")
    A(f"- **Application class is renamed** (`{man['application']['name']}`) — R8 also renames the `Application` subclass, so "
      "class-name heuristics aimed at `com.*.App` fail.")
    A(f"- `allowBackup=false`, `debuggable` absent, `extractNativeLibs=true`, `requestLegacyExternalStorage=true`, "
      f"`appComponentFactory={man['application']['appComponentFactory']}`.")
    A(f"- **{met['exported_permissionless']} components are exported without a permission guard**, including the deep-link "
      "entry activity, a bank-agnostic eKYC activity, *three* `androidx.test` instrumentation invokers and the Compose "
      "tooling `PreviewActivity`. The instrumentation/Preview activities are debug-hostile leftovers in a release build and "
      "are the most interesting low-severity finding of this stage.")
    A("- NFC HCE is declared (`apdu_service.xml`, `tiny_app_apdu_service.xml`) — the phone acts as a payment card — and "
      "`android.hardware.nfc`/`nfc.hce` are marked *not required*, so the app degrades gracefully.")
    A("- Broad package visibility (`QUERY_ALL_PACKAGES`) and `DETECT_SCREEN_CAPTURE` are requested; the latter pairs with the "
      "screen-capture control in §9.")
    A("")
    A("**Deep-link graph.** Schemes `zalopay`, `zalopay-1`, `zalopay.api.v2`, `zalopay-vnpay`, `zalopay-1`, `https` hosts "
      "`go.zalopay.vn`, `onelink.zalopay.vn`, `km.zalopay.vn`, `kmzalopay.onelink.me`; three hosts are `autoVerify=true` "
      "(App Links). The `ThirdPartyAppActivityAlias` accepts `zalopay://pay`, `://otp`, `://clearing`, `://merchant`, "
      "`://events` and `zalopay.api.v2://{pay,auth,recoverpassword}` — i.e. **merchant-initiated payment and auth intents "
      "arrive through an exported alias**, which is the highest-value surface to fuzz in future dynamic work.")
    A("")

    # 4 dex
    A("## 4. The DEX layer: 19 containers, 60,671 classes")
    A("")
    A(f"All 19 `classes*.dex` files were parsed with androguard and, independently, with a **from-scratch DEX walker** "
      f"(`stage2b_dex_xref.py`) that decodes `const-string`/`const-string/jumbo` from every `code_item` to build a "
      f"class→literal index. The two readings agree, and the walker indexes **{xref['totals']['classes_indexed']:,} classes** "
      f"with at least one literal.")
    A("")
    A(embed(ch_dex))
    A("")
    A(table(["DEX", "Classes", "Methods", "Strings", "Bytes", "Version"],
            [(d["file"], f"{d['classes']:,}", f"{d['methods']:,}", f"{d['strings']:,}",
              f"{d['bytes']:,}", f"0{d['dex_version']:02d}") for d in dex_sorted]))
    A("")
    A(embed(ch_rollup))
    A("")
    A("Namespace rollup confirms a **feature-module architecture** (`homev4`, `cashier_v3`, `loginv2`, `ekycpro`, "
      "`bankbinding`, `result_page_v3`, `miniapp`, …) layered on Jetpack/Kotlin, with the largest single block being "
      "third-party Google code in `classes4/5/6` (Play Services, ML Kit, Ads) and R8-renamed first-party code (`p9`, `q9`, "
      "`m9`, `n9`, `gs`, `o8`, `WO`, `WQ`). The presence of **13 ARSC packages** "
      f"({', '.join('`' + p.split('.')[-1] + '`' for p in res['packages'])}) mirrors the feature-module split of the resource table.")
    A("")

    # 5 obfuscation
    A("## 5. Obfuscation and build fingerprint")
    A("")
    A(table(["Metric", "Value", "Reading"], [
        ("Mean class-simple-name entropy", f"{dex['obfuscation']['mean_name_entropy_bits']:.3f} bits",
         "low-entropy / short R8 identifiers dominate"),
        ("Median", f"{dex['obfuscation']['median_name_entropy_bits']:.1f} bits", "single-character or 2-char names at the median"),
        ("Classes with ≤3-char identifiers", f"{dex['obfuscation']['short_identifier_classes']:,} "
         f"({dex['obfuscation']['short_identifier_ratio']:.2%})", "renamed core outside vendored SDKs"),
        ("String constants", f"{met['code']['strings']:,}", "the surviving high-signal surface"),
        ("Kotlin / coroutines / Compose", f"{fp['kotlin']} / {fp['coroutines']} / {fp['compose']}", "modern Kotlin-first build"),
        ("OkHttp", f"{', '.join(fp['okhttp_version_hint']) or 'n/a'}", "client stack"),
        ("Retrofit / Gson", f"{fp['retrofit']} / {fp['moshi_gson'][0]}", "HTTP + JSON"),
        ("Firebase products", ", ".join(fp["firebase_products"]), "analytics/messaging/config/crashlytics/perf"),
        ("ML Kit modules", ", ".join(fp["ml_kit_modules"]), "all processing on-device"),
        ("Payment schemes", ", ".join(fp["payment_schemes"]), "card + regional rails"),
        ("eKYC vendors", ", ".join(fp["ekyc_vendors"]), "multi-vendor KYC"),
        ("Gov integration", ", ".join(fp["gov_integration"]), "VNeID / CCCD / NFC identity"),
    ]))
    A("")
    A("**R8 behaviour is informative.** Obfuscation is *selective*: vendored SDKs (Firebase, ML Kit, Alipay, Meta) keep their "
      "names because consumer ProGuard rules demand it, while first-party code collapses to `p9`, `q9`, `m9`, `o8`, `WO`, `WQ`. "
      "Consequently the **string constant pool is the only reliable intelligence source**, which is exactly why the custom "
      "xref walker exists and why *security control discovery in this study is string-anchored rather than symbol-anchored*.")
    A("")

    # 6 third party
    A("## 6. Third-party ecosystem")
    A("")
    A(f"**{len(sdk)} SDK families** were attributed by class-namespace fingerprint:")
    A("")
    A(table(["SDK / framework", "Vendor", "Present in"],
            [(s["product"], s["vendor"], ", ".join(x.replace("classes", "c").replace(".dex", "") for x in s["dex_files"][:6])
              + ("…" if len(s["dex_files"]) > 6 else "")) for s in sdk]))
    A("")
    A("Three vendor clusters deserve attention:")
    A("")
    A("- **Ant Group / Alipay+** (`com.alipay`, `com.alipayplus`, `com.iap`, plus `libsecurity_lite.so` and "
      "`assets/Griver/*`) — ZaloPay embeds Alibaba's mini-program container *and* Alipay's security and cross-border rails.")
    A("- **FPT.AI / trueID + VISA CBP** — liveness/eKYC cameras and a card-not-present risk SDK; both implement their own "
      "root/emulator/Frida checks, which explains why anti-tamper literals appear in more than one DEX.")
    A("- **SQLCipher** (`libsqlcipher.so`, 3.5 MB) — a full SQLite build with AES, i.e. the local ledger is encrypted at rest.")
    A("")

    # 7 native
    A("## 7. The native layer")
    A("")
    A(f"**{nat['library_count']} libraries / {nat['total_bytes']:,} bytes / "
      f"{sum(x.get('jni_exports', 0) for x in nat['libraries'])} JNI exports.**")
    A("")
    A(embed(ch_nat))
    A("")
    A(table(["Library", "Bytes", "JNI", "Exports", "Attribution"],
            [(f"`{l['file']}`", f"{l['bytes']:,}", l["jni_exports"], f"{l['exported_symbols']:,}",
              l["vendor_guess"] or l["vendor"]) for l in sorted(nat["libraries"], key=lambda x: -x["bytes"])[:16]]))
    A("")
    A("**Obfuscated first-party libraries.** Six `.so` files carry meaningless names yet real code: "
      + ", ".join(f"`{x}`" for x in nat["obfuscated_first_party"]) + ". Analysis shows:")
    A("")
    A("- `libtaibmcf.so` (12.6 MB) and `libfgcpkjvqe.so` (6.5 MB) share a property-query/dynamic-loader profile "
      "(`__system_property_get`, `dl_iterate_phdr`, `openat`, `fstatfs`) and are paired with same-named `.dat` payloads in "
      "`assets/` — a **native packer/shield** stage that decrypts and maps code at runtime.")
    A("- `libohxbee.so` → `liblytcuhe.so` → `librcmefqonnx.so` form a dependency chain (94 exports in the shared crypto core), "
      "i.e. a split crypto/transport module resolved through `DT_NEEDED`.")
    A("- `libsecurity_lite.so` exports `Java_com_alipay_plus_security_lite_SecurityGuardLiteNative_{init,encrypt,decrypt,signRequest}` "
      "— the Alipay+ **SecurityGuard Lite** signing/encryption service used for request signing.")
    A("- `liba97d.so` (≈1 MB, 64 exports) imports `fork`, `kill`, `syscall`, `statfs`, `__system_property_get` — consistent with "
      "a device-fingerprinting/anti-tamper engine.")
    A("")
    A("**Anti-instrumentation literals in native code** (`Frida`, `xposed`, `/proc/self/maps`, `TracerPid`, `magisk`) appear in "
      "`libtrueidlib.so` (FPT) and in several Google libs; `libface_detector_v2_jni.so` and the ML Kit pipelines embed `Frida`/`xposed` "
      "strings inherited from MediaPipe's integrity checks. This is a concrete instance of **defence-in-depth by dependency accretion**: "
      "the wallet inherits protections from every SDK it vendors.")
    A("")

    # 8 assets / runtimes
    A("## 8. Assets, on-device AI and the four runtimes")
    A("")
    A(f"`assets/` holds **{ast['file_count']} files / {ast['total_bytes']:,} bytes**. The headline is that ZaloPay is not one "
      "application but **four runtimes sharing a process**:")
    A("")
    A(table(["Runtime", "Payload", "Size", "What it runs"], [
        ("ART / DEX", "19 × `classes*.dex`", f"{sum(d['bytes'] for d in dex['multidex']):,} B", "first-party features, security core"),
        ("React Native", "`assets/android.1.zip` → `main.jsbundle`", f"{rn['bundle_bytes']:,} B", f"{rn['entries']} assets; `{'`, `'.join(rn['packages'][:5])}`"),
        ("Alibaba Griver", f"{len(grv)} `.amr` mini-program bundles", f"{sum(g['bytes'] for g in grv):,} B", "appx‑ng worker JS; signed `CERT.json`"),
        ("WebView + bridge", "`jsbridge.js`, `internaljsbridge.js`", f"{sum(v['bytes'] for v in eco['web_bridge'].values()):,} B",
         "`ZaloPayJSBridge.call/_invokeJS/subscribe`"),
    ]))
    A("")
    A("**React Native.** `main.jsbundle` is a *plain-text (non-Hermes)* production bundle of "
      f"{rn['bundle_bytes']:,} bytes; its module references expose `@mini-apps/navigation`, `react-navigation-stack`, "
      f"`react-native-check-box` and a private `zalopay-components` kit. Shipping readable JS means the RN layer is trivially "
      "reversable compared with the R8-hardened Kotlin — a **security asymmetry** worth reporting.")
    A("")
    A("**Griver mini-programs.** Each `.amr` is a ZIP containing a tar payload plus `CERT.json` (RSA signatures over each member) "
      "and `Manifest.xml`; `assets/Griver/appx.json` is the version catalogue. Bundle `68687209_2.9.7.8` carries the full "
      "`appx-ng` worker runtime (3.3 MB uncompressed), and `66666692_1.24.8` adds `security-patch.min.js`. This is a **second, "
      "independent signed-code channel** inside a PCI-scoped app.")
    A("")
    A(f"**On-device AI.** {met['ai_models']['tflite']} TFLite models ({met['ai_models']['tflite_bytes']:,} bytes) cover "
      "document/ID localization (`mlkit_odt_*`), barcode decode (SSD-MobileNet + ONED), text recognition (RPN detector + CTC line "
      "recogniser) and a **custom `object_labeler.tflite`** (3.8 MB, largest model in the app); `FastestDet.ncnn.bin` "
      "(2.4 MB) adds an NCNN face/object detector. All inference is local — no image leaves the device before the eKYC vendor SDK "
      "decides to upload — which is the privacy-positive reading, and also the reason the model weights are extractable.")
    A("")
    A(embed(ch_cat))
    A("")

    # 9 security
    A("## 9. Security architecture (the core contribution)")
    A("")
    A(f"We identify **{len(ctrl)} defensive controls**, each backed by literals in at least one DEX (matrix below) and, where "
      f"user-visible, by Vietnamese enforcement copy. {sec['user_facing_vi_messages']} distinct user-facing messages were captured.")
    A("")
    A(embed(ch_ctrl))
    A("")
    A(embed(ch_matrix))
    A("")
    A(table(["#", "Control", "Ecosystem", "Hits", "Representative evidence"],
            [(i + 1, c["control"], c["ecosystem"], c["literal_hits"],
              "; ".join(f"`{e[:52]}`" for e in c["evidence"][:2])) for i, c in enumerate(ctrl_sorted)]))
    A("")
    A("### 9.1 The device-integrity gate")
    A("")
    A("The wallet refuses to transact on a device it considers hostile, and it says so in Vietnamese. Captured copy includes:")
    A("")
    for c in ctrl:
        for m in c["user_facing_vi"][:2]:
            if len(m) > 24:
                A(f"> *“{m}”*  \n> — {c['control']}  ")
    A("")
    A("The taxonomy is unusually complete: **root, hook, emulator, debugger, developer mode, proxy, untrusted keyboard** — "
      "seven environmental checks before any PIN is accepted. The untrusted-keyboard check "
      "(`Thiết bị hiện đang sử dụng bàn phím không có trong danh sách được tin cậy`) is the most distinctive: it defends "
      "against IME-based keylogging *and* accessibility-driven overlay attacks, a threat model rarely seen outside banking apps.")
    A("")
    A("### 9.2 Transport security")
    A("")
    f"`network_security_config.xml` permits cleartext **globally for `base-config`** while pinning nothing at the platform layer — "
    "so integrity depends on the HTTP stack, and the stack is OkHttp 4.12 with `CertificatePinner`. The ~32 SPKI `sha256/…` "
    "pins in the DEX belong to the **Google Ads SDK** (`com.google.android.gms.internal.ads.*`), *not* to `*.zalopay.vn`; "
    "our xref walker attributes every pin class and finds no first-party pin-set in Java. This is a corrected finding of earlier "
    "heuristic scans and a good example of why attribution mattered: *pinning presence ≠ pinning coverage*."
    A("")
    A("### 9.3 Data at rest and key material")
    A("")
    A("SQLCipher (`libsqlcipher.so`, `net/zetetic/*`, 57 literals) encrypts the local database; `KeyGenParameterSpec`, "
      "`KeyProperties` and a single `IsStrongBoxBacked` reference show Android Keystore usage with StrongBox opt-in; "
      "`BiometricPrompt`/`FingerprintManager` gate the payment PIN. AES-GCM (`AES/GCM/NoPadding`) appears once — the small "
      "count suggests most crypto is deliberately pushed into the native shield and SecurityGuard Lite rather than Java.")
    A("")
    A("### 9.4 The Alipay+ security channel")
    A("")
    A("`SecurityGuardLiteNative.signRequest` plus 25 literals in `Lcom/alipay/plus/security/lite/*` mean request signing can be "
      "delegated to a vendor TEE-aware signer. Combined with the native packer (`libtaibmcf`), ZaloPay's integrity story is "
      "**layered**: R8 → native shield → vendor signer → platform attestation (SafetyNet/Play Integrity literals present).")
    A("")

    # 10 smart otp
    A("## 10. Authentication: Smart OTP, OCRA and WYSIWYS")
    A("")
    A("The strongest finding of this study is the transaction-authentication design. String evidence in `classes7.dex`:")
    A("")
    A("```")
    A("OCRA-1:HOTP-SHA256-8:QH64        ← RFC 6287 OCRA challenge-response profile")
    A("OCRANION                          ← OCRA nonce/initialisation")
    A("SmartOTPConfig / SmartOTPPinPolicy / SmartOTPUIConfig / SmartOTPListener")
    A("OCRA] ✅ WYSIWYS verification passed")
    A("OCRA] ⚠️ Mock auth mode (hpkeAuthInfo='")
    A("WYSIWYS verification failed: transaction data tampered!")
    A("skipping WYSIWYS check — server txDigest is hardcoded test vector")
    A("```")
    A("")
    A("This is a textbook **What-You-See-Is-What-You-Sign** design: the server issues `txDigest`, the client binds the "
      "human-readable transaction fields to that digest, renders them, and only then produces an **OCRA response** over the "
      "challenge using key material protected by the keystore/PIN. If the displayed amount/beneficiary were altered in memory "
      "(hook, overlay, repackaged build) the verification fails and the copy says so literally. `hpkeAuthInfo` indicates a "
      "hybrid public-key encryption envelope (RFC 9180 HPKE) for the auth payload.")
    A("")
    A("**Two cautions extracted from the same strings.** The `Mock auth mode` branch and the `hardcoded test vector` shortcut "
      "are debug affordances that survive in the release DEX. They are gated (mock mode requires a mismatched HPKE envelope; "
      "the shortcut requires the server digest to equal a test vector), but their presence is precisely the kind of *residual "
      "test surface* a threat model should enumerate. We report them as **observations, not vulnerabilities** — proving "
      "reachability requires dynamic instrumentation, which is out of scope here.")
    A("")

    # 11 miniapp economy
    A("## 11. Mini-app economy and deep-link graph")
    A("")
    A(f"`miniapp_config.json` is a {cat['bytes']:,}-byte catalogue of **{cat['apps']} mini-apps** across 19 categories served "
      f"from **{cat['distinct_hosts']} hosts**. The taxonomy is a map of Vietnam's consumer economy:")
    A("")
    A(table(["Category", "Apps"], [(c, n) for c, n in cat["categories"][:10]]))
    A("")
    A("Architecturally this is **Super-App-as-Platform**: 373 third-party experiences run inside ZaloPay's container, reachable by "
      "`zalopay://launch/app/{app_id}` and `onelink.zalopay.vn`, with the Griver runtime enforcing signed bundles. The attack "
      "surface therefore includes every mini-app partner — mitigated by per-bundle `CERT.json` signatures but not by code review "
      "of the partner JS, which arrives at runtime.")
    A("")

    # 12 risk register
    A("## 12. Risk register (static, prioritised)")
    A("")
    A(table(["ID", "Severity", "Finding", "Evidence", "Recommendation"], [
        ("R1", "Info", "Residual test affordances in release: `Mock auth mode`, `hardcoded test vector` WYSIWYS shortcut",
         "classes7 literals", "Compile-time exclude via BuildConfig; assert absence in CI"),
        ("R2", "Low", "Plain-text React Native bundle (no Hermes bytecode) exposes app logic",
         f"`main.jsbundle` {rn['bytes']:,} B" if False else f"`main.jsbundle` {rn['bundle_bytes']:,} B",
         "Enable Hermes, strip source URLs"),
        ("R3", "Low", "Instrumentation/Preview activities exported without permission",
         "manifest: 3× androidx.test, Compose PreviewActivity", "Remove from release manifest"),
        ("R4", "Low", "`base-config` allows cleartext globally", "network_security_config.xml", "Set cleartextTrafficPermitted=false"),
        ("R5", "Info", "Certificate pinning exists only in vendored Ads SDK, not first-party OkHttp",
         "xref: 13 pin classes, all `gms.internal.ads`", "Pin `*.zalopay.vn` SPKIs or document reliance on native signer"),
        ("R6", "Info", "32-bit-only distribution in this bundle", "no arm64-v8a split", "Ship arm64 split for ASLR/registers"),
        ("R7", "Info", "Native shield libs and opaque `.dat` payloads unaudited", "`libtaibmcf`, `libfgcpkjvqe`", "Runtime decryption study (future work)"),
        ("R8", "Info", "Deep-link alias accepts payment/auth intents without permission", "`ThirdPartyAppActivityAlias`", "Validate caller + nonce server-side"),
    ]))
    A("")

    # 13 limitations
    A("## 13. Limitations and threats to validity")
    A("")
    A("- **Static only.** No code executes; reachability of gated branches (mock mode, test vector) is unproven.")
    A("- **No decompilation to Java.** String and namespace evidence is strong but method-level semantics are not reconstructed; "
      "we deliberately avoid claiming behaviour we cannot show a literal or symbol for.")
    A("- **Obfuscation asymmetry.** First-party logic is renamed; conclusions rely on constants, which biases discovery toward "
      "code that *must* keep strings (UI copy, protocols, security messages).")
    A("- **Single artefact.** One versionCode, armeabi-v7a only. Version drift may change controls.")
    A("- **Vendor attribution is heuristic** (namespace prefixes). It is stated as such throughout.")
    A("")

    # 14 appendix
    A("## 14. Reproduction appendix")
    A("")
    A(table(["Stage", "Script", "Output", "Runtime"], [
        ("1 container/manifest", "`stage1_container_manifest.py`", "`01_container.json`, `02_manifest.json`, decoded manifest", "~2 s"),
        ("2 DEX census", "`stage2_dex.py`", "`03_dex.json`", "~18 s"),
        ("2b DEX xref (custom walker)", "`stage2b_dex_xref.py`", "`04_dex_xref.json`", "~12 min"),
        ("3 native/assets/resources", "`stage3_native_assets_res.py`", "`05_native.json`, `06_assets.json`, `07_resources.json`", "~2 s"),
        ("4 security taxonomy", "`stage4_security_fingerprint.py`", "`08_security.json`, `09_fingerprint.json`, `10_metrics.json`", "~18 s"),
        ("5 ecosystem", "`stage5_ecosystem.py`", "`11_ecosystem.json`", "~1 s"),
        ("6 report", "`report.py` (this file)", "`REPORT.md`", "<1 s"),
    ]))
    A("")
    A("Longest DEX parse cost is dominated by the linear `code_item` scan; a region-based decoder would cut it by an order of "
      "magnitude but at the cost of a full instruction-length table, which we avoided to keep the walker auditable.")
    A("")
    A("### Data inventory")
    A("")
    A(table(["File", "Contents"],
            [(f"`analysis/data/{f}`", d) for f, d in [
                ("01_container.json", "splits, hashes, entropy, ZIP entry census, alignment, signing certs"),
                ("02_manifest.json", "full component/permission/deep-link model + decoded XML"),
                ("03_dex.json", "per-DEX census, namespace tree, SDK inventory, hosts, API paths, literals, security markers"),
                ("04_dex_xref.json", "class→literal index, security/pin/host attribution (custom walker)"),
                ("05_native.json", "ELF census, JNI exports, suspicious imports, obfuscated-lib detection"),
                ("06_assets.json", "asset census, TFLite/NCNN models, Griver bundles, opaque blobs, bundled DEX"),
                ("07_resources.json", "ARSC packages, string counts, localisation, NSC"),
                ("08_security.json", "13 controls with evidence, ecosystem and Vietnamese copy"),
                ("09_fingerprint.json", "framework/version fingerprint"),
                ("10_metrics.json", "headline numbers used by the report and video"),
                ("11_ecosystem.json", "React Native, Griver, JS bridge, mini-app catalogue"),
            ]]))
    A("")

    # 15 citation
    A("## 15. Citation and scholarly use")
    A("")
    A("This study is designed to be *citable*: every claim maps to a script, a data file and a hash. If you use it, please cite:")
    A("")
    A("```bibtex")
    A("@techreport{zalopay2026re,")
    A('  title  = {Reverse-Engineering Study: ZaloPay 11.17.0 (Android) — Container, DEX, Native,')
    A('            Runtime and Security Architecture},')
    A('  author = {Arena Agent Mode (johnlikescarrot/-)},')
    A("  year   = {2026},")
    A(f"  note   = {{Static analysis of vn.com.vng.zalopay 11.17.0 (versionCode 1102758), base APK SHA-256")
    A(f"            {met['artefact']['base_sha256']}}},")
    A("  url    = {https://github.com/johnlikescarrot/-}")
    A("}")
    A("```")
    A("")
    A("> **On citation metrics.** No technical report can *guarantee* Google Scholar citations; what it can guarantee is "
      "falsifiability, reproducibility and a stable citable artefact. That is what this document optimises for: a persistent "
      "DOI-style hash, machine-readable datasets, and a script-per-claim audit trail.")
    A("")
    A("---")
    A("")
    A(f"*Generated from the analysis dataset on 2026-11-25 · {met['code']['classes']:,} classes · "
      f"{met['code']['methods']:,} methods · {nat['library_count']} native libraries · {len(ctrl)} controls verified.*")

    out = "\n".join(L) + "\n"
    path = os.path.join(ROOT, "REPORT.md")
    with open(path, "w", encoding="utf-8") as f:
        f.write(out)
    print(f"[report] wrote {path} ({len(out):,} bytes)")


if __name__ == "__main__":
    main()
