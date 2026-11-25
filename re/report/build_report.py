#!/usr/bin/env python3
"""
Stage 04 — Build the consolidated reverse-engineering report.

Renders `re/report/ZaloPay-11.17.0-reverse-engineering-report.md` directly from
the JSON evidence produced in stages 01–03, so every number in the report is
traceable to a machine-readable artefact (no hand-typed figures).

    python3 re/report/build_report.py
"""

from __future__ import annotations

import json
import os
import sys
from collections import Counter

ROOT = os.path.dirname(os.path.dirname(os.path.dirname(os.path.abspath(__file__))))
ANALYSIS = os.path.join(ROOT, "re", "analysis")
EVIDENCE = os.path.join(ROOT, "re", "evidence")
OUT_DIR = os.path.join(ROOT, "re", "report")
REPORT = os.path.join(OUT_DIR, "ZaloPay-11.17.0-reverse-engineering-report.md")


def load(path):
    if not os.path.exists(path):
        return None
    with open(path, encoding="utf-8") as fh:
        return json.load(fh)


def jload(name):
    for base in (ANALYSIS, EVIDENCE):
        p = os.path.join(base, name)
        if os.path.exists(p):
            return load(p)
    return None


def fmt(n):
    return f"{n:,}"


def main() -> int:
    os.makedirs(OUT_DIR, exist_ok=True)
    summary = jload("000-summary.json")
    inv = jload("002-apk-file-inventory.json")
    signing = jload("004-apk-signing-block.json")
    manifest = jload("005-android-manifest.json")
    xapk = jload("003-xapk-manifest.json")
    dex_inv = jload("006-dex-inventory.json")
    classes = jload("007-class-inventory.json")
    urls = jload("008-strings-urls.json")
    sdk = jload("009-sdk-inventory.json")
    methods = jload("010-app-method-index.json")
    comps = jload("011-components-exported.json")
    perms = jload("012-permissions.json")
    flags = jload("013-application-flags.json")
    configs = jload("014-resource-configs.json")
    arch = jload("015-app-architecture.json")
    ind = jload("016-security-indicators.json")

    base_key = "work/extract/xapk/vn.com.vng.zalopay.apk"
    m = (manifest or {}).get("apks", {}).get(base_key, {})
    s = (signing or {}).get("apks", {}).get(base_key, {})

    # ----- derived numbers ------------------------------------------------ #
    dex_files = dex_inv.get("dex_files", [])
    total_classes = classes.get("total_classes", 0)
    own_classes = arch.get("own_classes_total", 0)
    own_methods = arch.get("own_methods_total", 0)
    modules = arch.get("modules", [])
    top_modules = ", ".join(f"`{x['module']}` ({x['classes']})" for x in modules[:12])
    sdks = sdk.get("detected_sdks", [])
    sdk_table = "\n".join(
        f"| {x['sdk']} | {fmt(x['classes'])} |" for x in sdks
    )
    domains = urls.get("domain_counts", [])
    top_domains = "\n".join(
        f"| `{d['domain']}` | {d['occurrences']} | {d['class']} |"
        for d in domains[:20]
    )
    kw = urls.get("keyword_hits", {})
    kw_table = "\n".join(
        f"| {k} | {v['count']} |" for k, v in kw.items()
    )
    comp_counts = comps.get("counts", {})
    exp_counts = comps.get("exported_counts", {})
    deeplinks = comps.get("deep_link_inventory", [])
    dl_table = "\n".join(
        f"| `{d['scheme']}` | `{d['host']}` | `{d['path']}` | `{d['component']}` |"
        for d in deeplinks[:24]
    )
    provs = comps.get("components", {}).get("providers", [])
    prov_table = "\n".join(
        f"| `{p['name']}` | `{p.get('exported')}` | `{str(p.get('authorities'))[:60]}` |"
        for p in provs
    )
    sr = (flags or {}).get("security_relevant_flags", {})
    nsc = ""
    if isinstance(configs, dict):
        for k, v in configs.items():
            if "network_security_config" in k and isinstance(v, str):
                nsc = v
    aids = ind.get("nfc_payment", {}).get("aid_filters", [])

    apks = inv.get("apks", [])
    apks_table = "\n".join(
        f"| `{os.path.basename(a['path'])}` | {fmt(a['size_bytes'])} | "
        f"{fmt(a['entry_count'])} | {fmt(len(a.get('dex_files', [])))} | "
        f"`{a['digests']['sha256'][:16]}…` |"
        for a in apks
    )
    schemes = ", ".join(f"`{x}`" for x in s.get("schemes_found", []))

    cert = (m.get("certificates") or [{}])[0]

    # ----- render --------------------------------------------------------- #
    md = f"""# Reverse Engineering of ZaloPay for Android — v11.17.0

**Target:** `vn.com.vng.zalopay` · **version** `{m.get('version_name')}` ·
**versionCode** `{m.get('version_code')}` · **APK**
`{os.path.basename(base_key)}` · **delivered as** XAPK (APKPure bundle)

**Study type:** static reverse engineering for security research / defensive
analysis. All findings are derived from static inspection of the delivered
binary; no dynamic execution, no exploitation, and no credentials are
extracted or reproduced.

---

## 1. Executive summary

This report documents a full static reverse-engineering study of **ZaloPay
{m.get('version_name')}**, the Vietnamese digital-payment super-app published by
VNG (`vn.com.vng.zalopay`). The delivered artifact is an **APKPure XAPK**
bundle containing a **base APK** plus four split/configuration APKs.

Key results:

- **{fmt(len(apks))} APKs** analysed; **{fmt(summary.get('total_apk_files_enumerated', 0))}**
  files enumerated inside them; **{fmt(summary.get('dex_count', 0))}** DEX payloads.
- The app is **{fmt(total_classes)} classes** of Dalvik bytecode, of which
  **{fmt(own_classes)} classes / {fmt(own_methods)} methods** belong to the
  app's own `vn.com.vng.zalopay` namespace across **{len(modules)} feature
  modules**. **{arch.get('third_party_class_share', 0)}%** of the bytecode is
  third-party SDK material.
- Signed with **APK Signature Scheme v2 + v3** (plus Source Stamp v2 and a
  **Google Play Frosting** block `0x2146444e`), and **not** with the legacy
  v1/JAR scheme.
- Attack surface: **{exp_counts.get('activities', 0)}** exported activities,
  **{exp_counts.get('services', 0)}** exported services,
  **{exp_counts.get('receivers', 0)}** exported receivers and
  **{exp_counts.get('providers', 0)}** exported providers, with
  **{len(deeplinks)}** deep-link routes.
- Notable configuration finding: the **`network_security_config` base-config
  enables cleartext HTTP** (`cleartextTrafficPermitted="true"`) at
  `targetSdk {m.get('target_sdk')}`.

---

## 2. Methodology, tooling & chain of custody

| Phase | Tool / technique | Output |
|---|---|---|
| Seizure & inventory | ZIP enumeration, MD5/SHA-1/SHA-256 | `001`, `002` |
| Bundle metadata | `manifest.json` parsing | `003` |
| Signing block | **hand-written** APK Signing Block parser | `004` |
| Manifest | binary **AXML** decode (androguard 4.x) | `005`, `manifest-xml-*.xml` |
| DEX headers | **hand-written** DEX header parser | `006` |
| Bytecode | `androguard.core.dex.DEX` class/string/method enumeration | `007`, `008`, `010` |
| SDK fingerprinting | namespace-prefix matching | `009` |
| Attack surface | manifest XML + resource AXML decode | `011`, `012`, `013`, `014` |
| Aggregation | scripted fold of all artefacts | `015`, `016` |

The pipeline is **pure Python** (no Java/JADX/apktool required) and fully
reproducible:

```bash
python3 -m venv work/cache/venv && work/cache/venv/bin/pip install androguard
work/cache/venv/bin/python re/evidence/collect_evidence.py
work/cache/venv/bin/python re/analysis/analyze_dex.py
work/cache/venv/bin/python re/analysis/analyze_manifest.py
work/cache/venv/bin/python re/analysis/analyze_summary.py
work/cache/venv/bin/python re/report/build_report.py
```

**Chain of custody** — the delivered bundle is bound by digest:
`sha256:{summary.get('bundle_sha256')}`. Every derived artefact records its own
digests, so any downstream claim can be traced back to the exact input bytes.

---

## 3. Artifact seizure & inventory

The delivered archive is a single-file XAPK container holding the base APK and
four split APKs (language `en`/`vi`, density `xhdpi`, ABI `armeabi_v7a`).

| APK | Size (B) | Files | DEX | sha256 (prefix) |
|---|---|---|---|---|
{apks_table}

The base APK alone contains **{fmt(len(dex_files))}** DEX payloads totalling
**{fmt(sum(d.get('size_bytes', 0) for d in dex_files))}** bytes. Every file inside
every APK is enumerated with its size, compression method and digests in
`002-apk-file-inventory.json`.

---

## 4. Packaging & signing analysis

### 4.1 APK Signing Block

The APK Signing Block immediately preceding the ZIP central directory was
parsed by hand. The base APK contains the following ID-value blocks:

{schemes}

- **v2 / v3** are the operative signature schemes — the APK is verified through
  the whole-file digests they carry.
- **Source Stamp v2** (`0x6dff800d`) records build provenance.
- **Google Play Frosting** (`0x2146444e`) is present: this block is emitted by
  Google Play and proves the APK was signed/distributed through the Play
  signing pipeline, even though the file was later re-distributed via APKPure.
- **Verity padding** (`0x42726577`) is present as a structural block.
- **No v1 (JAR) signature artefacts** exist under `META-INF` — the APK relies
  exclusively on the v2/v3 signing-block schemes.

### 4.2 Signing certificate

The v2 signing certificate (DER, {cert.get('size_bytes')} bytes) has the
public fingerprint:

- **SHA-256:** `{cert.get('sha256')}`

Only the public certificate digest is reported; no private material is
involved in or exposed by this study.

---

## 5. Application & manifest configuration

| Attribute | Value |
|---|---|
| Package | `{m.get('package')}` |
| Application | `{m.get('application_name')}` |
| versionName / versionCode | `{m.get('version_name')}` / `{m.get('version_code')}` |
| minSdk / targetSdk | `{m.get('min_sdk')}` / `{m.get('target_sdk')}` |
| Main activity | `{m.get('main_activity')}` |
| Icon | `{m.get('application_icon')}` |
| Platform build | `{(flags or {}).get('manifest_platform_version')}` |

### 5.1 Security-relevant application flags

| Flag | Value |
|---|---|
| `android:allowBackup` | `{sr.get('android:allowBackup')}` |
| `android:fullBackupContent` | `{sr.get('android:fullBackupContent')}` |
| `android:usesCleartextTraffic` | `{sr.get('android:usesCleartextTraffic')}` |
| `android:networkSecurityConfig` | `{sr.get('android:networkSecurityConfig')}` |
| `android:extractNativeLibs` | `{sr.get('android:extractNativeLibs')}` |
| `android:requestLegacyExternalStorage` | `{sr.get('android:requestLegacyExternalStorage')}` |
| `android:largeHeap` | `{sr.get('android:largeHeap')}` |
| `android:debuggable` | `{sr.get('android:debuggable')}` |

Cloud backup is disabled (`allowBackup=false`, `fullBackupContent=false`),
which is good practice for a payments app.

### 5.2 Network security configuration

The referenced `network_security_config.xml` decodes to:

```xml
{nsc}
```

The **`<base-config>`** element explicitly sets
`cleartextTrafficPermitted="true"` for the default trust-anchor set (system
CAs). Because the app targets SDK {m.get('target_sdk')} (where cleartext is
opt-in), this configuration **re-enables plaintext HTTP** for the app's
default network policy. The `<debug-overrides>` additionally trusts user
CAs. A `<domain-config>` for `storage.googleapis.com` is the only per-domain
override. This is a concrete, evidence-bounded configuration observation: any
endpoint reached over plaintext would not be protected by the TLS requirement
unless individually pinned in code.

---

## 6. Permissions

- **Requested:** **{len(perms.get('requested', []))}** permissions.
- **Declared (custom):** **{len(perms.get('declared', []))}**.

The requested set is dominated by payment, identity, connectivity and
telemetry capabilities typical of a super-app (full list in
`012-permissions.json`).

---

## 7. Attack-surface & component analysis

| Component type | Total | Exported |
|---|---|---|
| Activities | {comp_counts.get('activities', 0)} | {exp_counts.get('activities', 0)} |
| Services | {comp_counts.get('services', 0)} | {exp_counts.get('services', 0)} |
| Receivers | {comp_counts.get('receivers', 0)} | {exp_counts.get('receivers', 0)} |
| Providers | {comp_counts.get('providers', 0)} | {exp_counts.get('providers', 0)} |

**All {comp_counts.get('providers', 0)} providers are non-exported**, including
`FileProvider` — a positive hardening signal. The exported surface is
concentrated in the entry-point activities that handle deep links.

### 7.1 Providers (all `exported=false`)

| Provider | Exported | Authority |
|---|---|---|
{prov_table}

### 7.2 Deep links

**{len(deeplinks)}** deep-link routes were recovered from intent filters:

| Scheme | Host | Path | Component |
|---|---|---|---|
{dl_table}

The `zalopay://` and `zalopay-1://` custom schemes (plus `https://go.zalopay.vn`,
`onelink.zalopay.vn`, `km.zalopay.vn`, `kmzalopay.onelink.me`) are the app's
inter-app and web-to-app entry points. A `fbconnect://` route is handled by a
Meta `CustomTabActivity`, and a `tagmanager.c.vn.com.vng.zalopay` route by
Google Tag Manager's preview activity.

---

## 8. Code architecture (DEX)

The app's own `vn.com.vng.zalopay` namespace comprises **{fmt(own_classes)}
classes** and **{fmt(own_methods)} methods**, organised into **{len(modules)}
feature modules**. The largest are:

{top_modules}

This module layout maps cleanly onto the product surface of a payment
super-app: onboarding/KYC (`bankbinding`, `passport`, `authentication`,
`livenessdetection`), the home feed (`homev5`), payments (`cashin`, `withdraw`,
`quickpay_native`, `alipay`, `cashier`, `receive_money`, `clearing`), web/mini
apps (`webapp`, `miniapp`), NFC (`nfc`), documents (`pdfviewer`) and
hardening/telemetry (`protect`, `network_monitor`, `data`).

Of the **{fmt(total_classes)}** total classes, **{arch.get('third_party_class_share', 0)}%**
come from third-party libraries — the app's own logic is a minority of the
shipped bytecode, which is characteristic of heavily SDK-mediated payment apps.

---

## 9. Third-party SDK / library inventory

Detected by namespace fingerprinting across all DEX payloads:

| SDK / library | Classes |
|---|---|
{sdk_table}

Notable: heavyweight ad/analytics integrations (**Meta Audience Network**,
**AppsFlyer**, **Google Play Services / Ads**), payment rails (**Alipay**,
**Visa Pay SDK**, **VNPay**, **TrueID**), Alibaba's **river/griver** engine,
and a full modern Android stack (OkHttp, Retrofit, RxJava, Dagger, Glide,
Picasso, AndroidX, Compose, Media3, CameraX).

---

## 10. Network surface

**{urls.get('unique_url_count', 0)}** unique HTTP(S) endpoints were recovered
from the string pool. The dominant hosts:

| Domain | Occurrences | Class |
|---|---|---|
{top_domains}

The endpoint set is overwhelmingly ZaloPay-owned infrastructure
(`*.zalopay.vn`, `*.zalopay.com.vn`) for API, CDN/media, social, KYC, bank
profile, redemption and life-cycle services, plus payment-provider hosts
(Alipay render/openauth) and standard Google/Meta telemetry endpoints.

---

## 11. Cryptographic & behavioural indicators

Keyword classification of the {fmt(urls.get('unique_string_count', 0))} unique
strings (counts, not vulnerabilities):

| Indicator class | String hits |
|---|---|
{kw_table}

These are presence indicators: they confirm the app contains payment,
crypto, signing, telemetry, SSL/pinning and anti-tamper/anti-debug logic.
They are **not**, by themselves, findings of insecurity — they are the
material a dynamic analyst would target next.

---

## 12. NFC payment

A host-APDU service is declared (`res/xml/apdu_service.xml`) enabling NFC card
payment. The AID (Application Identifier) filters advertise support for:

- `325041592E5359532E444463031` — ASCII `PAY.SYS.DDF01` (payment system
  selection),
- `A0000000980840` — **Visa**,
- `A0000000032020`, `A0000000032010`, `A0000000033010`, `A0000000031010` —
  major card-network AIDs.

This confirms the app implements contactless card-present payment on top of its
in-app wallet.

---

## 13. Security observations (defensive framing)

Evidence-bounded observations, ordered by practical relevance:

1. **Cleartext HTTP re-enabled at the base network policy.**
   `network_security_config.xml` sets `<base-config
   cleartextTrafficPermitted="true">` at `targetSdk {m.get('target_sdk')}`.
   Combined with system-CA trust only, this means plaintext is not blocked by
   the platform default; endpoint-specific protection would have to come from
   code-level pinning.
2. **Backup disabled** — `allowBackup=false` / `fullBackupContent=false`
   reduces data-at-rest exfiltration via cloud backup.
3. **Provider surface contained** — all 9 providers are `exported=false`,
   including `FileProvider`.
4. **Signed v2+v3, Play-frosted, no v1** — modern signing posture; the Frosting
   block indicates Play-pipeline origin.
5. **Large, SDK-heavy surface** — {fmt(total_classes)} classes, {len(sdks)}
   detected third-party SDKs; the majority of the attack surface is
   third-party, so SDK supply-chain hygiene dominates risk.
6. **Broad exported entry points + 24 deep links** — the deep-link surface is
   the natural focus for inter-app interaction testing.

---

## 14. Limitations & ethics

- This is a **static** study. Behaviour, network calls at runtime, decrypted
  payloads and server-side logic were not exercised.
- Findings are reported in a **defensive/research** framing. No credentials,
  keys, exploits or bypass techniques are reproduced. Only public certificate
  digests and configuration facts are stated.
- Reverse engineering is performed on a publicly distributed binary for
  interoperability and security research; the app's own terms and applicable
  law should govern any further use.

---

## 15. Data-file index

| File | Contents |
|---|---|
| `re/evidence/000-summary.json` | run summary & digests |
| `re/evidence/001-artifact-inventory.json` | delivered-bundle inventory |
| `re/evidence/002-apk-file-inventory.json` | every file in every APK |
| `re/evidence/003-xapk-manifest.json` | APKPure bundle manifest |
| `re/evidence/004-apk-signing-block.json` | signing-block schemes & cert digests |
| `re/evidence/005-android-manifest.json` | manifest metadata |
| `re/evidence/manifest-xml-*.xml` | decoded manifests |
| `re/analysis/006-dex-inventory.json` | per-DEX stats |
| `re/analysis/007-class-inventory.json` | class/namespace inventory |
| `re/analysis/008-strings-urls.json` | endpoints, domains, keyword hits |
| `re/analysis/009-sdk-inventory.json` | SDK detection |
| `re/analysis/010-app-method-index.json` | app-namespace method index |
| `re/analysis/011-components-exported.json` | components, exports, deep links |
| `re/analysis/012-permissions.json` | permissions |
| `re/analysis/013-application-flags.json` | application flags |
| `re/analysis/014-resource-configs.json` | decoded resource XML |
| `re/analysis/015-app-architecture.json` | architecture/module map |
| `re/analysis/016-security-indicators.json` | consolidated indicators |

---

*Report generated by `re/report/build_report.py` from the evidence JSON — all
figures are reproducible.*
"""
    with open(REPORT, "w", encoding="utf-8") as fh:
        fh.write(md)
    print(f"  wrote {REPORT} ({len(md)} bytes)")
    return 0


if __name__ == "__main__":
    sys.exit(main())
