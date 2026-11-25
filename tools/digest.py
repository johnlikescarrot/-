#!/usr/bin/env python3
"""Digest /tmp/zp artifacts into durable repo reports under /home/user/-.

Phase 1 checkpoint: inventory, metadata, manifest, network security, assets,
configs, DEX triage, native triage, JS bridge + REA evidence.
"""
import json, os, re, shutil, subprocess, sys, zipfile
from collections import Counter

ZP = '/tmp/zp'
REPO = '/home/user/-'
BASE_APK = f'{ZP}/out/vn.com.vng.zalopay.apk'

def J(path):
    with open(path, encoding='utf-8', errors='replace') as f:
        return json.load(f)

def W(path, text):
    os.makedirs(os.path.dirname(path), exist_ok=True)
    with open(path, 'w', encoding='utf-8') as f:
        f.write(text)

def sha256_file(p):
    import hashlib
    h = hashlib.sha256()
    with open(p, 'rb') as f:
        for b in iter(lambda: f.read(1 << 20), b''):
            h.update(b)
    return h.hexdigest()

# ---------- 0. layout + raw copies ----------
for d in ['reports', 'reports/raw', 'tools', 'evidence']:
    os.makedirs(f'{REPO}/{d}', exist_ok=True)
for f in os.listdir(f'{ZP}/reports'):
    if f.endswith('.json'):
        shutil.copy(f'{ZP}/reports/{f}', f'{REPO}/reports/raw/{f}')
for f in ['stage1.py', 'stage2.py', 'stage3b.py', 'stage4.py']:
    if os.path.exists(f'{ZP}/{f}'):
        shutil.copy(f'{ZP}/{f}', f'{REPO}/tools/{f}')
shutil.copy(f'{ZP}/out/manifest.json', f'{REPO}/evidence/xapk-manifest.json')
shutil.copy(f'{ZP}/out/icon.png', f'{REPO}/evidence/icon.png')

inv = J(f'{ZP}/reports/inventory.json')
meta = J(f'{ZP}/reports/apk-metadata.json')
mf = J(f'{ZP}/reports/manifest-findings.json')
nsc = J(f'{ZP}/reports/network-security.json')
tax = J(f'{ZP}/reports/asset-taxonomy.json')
samples = J(f'{ZP}/reports/asset-samples.json')
dex = J(f'{ZP}/reports/dex-report.json')
nat = J(f'{ZP}/reports/native-report.json')

# ---------- 1. configs deep-dive ----------
z = zipfile.ZipFile(BASE_APK)
cfg_findings = {}
for name in ['assets/config/zalopay_config.json', 'assets/config/miniapp_config.json',
             'assets/client_tracking.json', 'assets/config/public_key.txt',
             'assets/config/signature.txt', 'assets/bundle.json',
             'assets/Griver/appx.json', 'assets/theme/bank/config_v2.json',
             'assets/theme/bank/ui_config.json', 'assets/theme/bank/bank_resource.json',
             'assets/home_data.json', 'assets/taibmcf.json', 'assets/vdfmoer.json']:
    try:
        raw = z.read(name)
    except KeyError:
        cfg_findings[name] = {'present': False}
        continue
    entry = {'present': True, 'bytes': len(raw), 'sha256': __import__('hashlib').sha256(raw).hexdigest()}
    if name.endswith('.json'):
        try:
            d = json.loads(raw)
            entry['json_type'] = type(d).__name__
            if isinstance(d, dict):
                entry['top_keys'] = list(d)[:40]
                entry['top_key_count'] = len(d)
            elif isinstance(d, list):
                entry['list_len'] = len(d)
            txt = raw.decode('utf-8', 'replace')
            urls = re.findall(r'https?://[A-Za-z0-9._\-/:?=&%+~#@]+', txt)
            entry['url_count'] = len(urls)
            entry['url_hosts'] = Counter(re.sub(r'^https?://', '', u).split('/')[0] for u in urls).most_common(15)
            entry['head'] = txt[:1500]
        except Exception as e:
            entry['json_error'] = repr(e)[:200]
    else:
        entry['head'] = raw.decode('utf-8', 'replace')[:800]
    cfg_findings[name] = entry
W(f'{REPO}/evidence/config-findings.json', json.dumps(cfg_findings, indent=2, ensure_ascii=False))

# verify RSA public key parse + signature length
import base64
try:
    pub = z.read('assets/config/public_key.txt').decode()
    m = re.search(r'-----BEGIN PUBLIC KEY-----(.*?)-----END PUBLIC KEY-----', pub, re.S)
    der = base64.b64decode(m.group(1)) if m else b''
    cfg_extra = {'pubkey_der_len': len(der), 'pubkey_b64_len': len(m.group(1)) if m else 0}
except Exception as e:
    cfg_extra = {'pubkey_error': repr(e)[:200]}
try:
    sig = z.read('assets/config/signature.txt').decode().strip()
    cfg_extra['sig_b64_len'] = len(sig)
    cfg_extra['sig_raw_len'] = len(base64.b64decode(sig))
except Exception as e:
    cfg_extra['sig_error'] = repr(e)[:200]
W(f'{REPO}/evidence/config-crypto.json', json.dumps(cfg_extra, indent=2))

# ---------- 2. JS bridge analysis ----------
jsdir = f'{ZP}/jsapp'
jsfiles = sorted(os.listdir(jsdir))
jsinfo = []
alljs = {}
for fn in jsfiles:
    p = f'{jsdir}/{fn}'
    data = open(p, 'rb').read()
    try:
        txt = data.decode('utf-8')
    except Exception:
        txt = data.decode('utf-8', 'replace')
    alljs[fn] = txt
    funcs = sorted(set(re.findall(r'function\s+([A-Za-z0-9_]+)', txt)))[:30]
    bridges = sorted(set(re.findall(r'(ZaloPay\w*Bridge\w*|AlipayJSBridge|androidEmbed\w*|Griver\w*)', txt)))[:20]
    urls = re.findall(r'https?://[A-Za-z0-9._\-/:?=&%+~#@]+', txt)[:10]
    jsinfo.append({'file': fn, 'bytes': len(data), 'functions': funcs, 'bridges': bridges, 'urls': urls,
                   'lines': txt.count('\n') + 1})
W(f'{REPO}/evidence/jsbridge-inventory.json', json.dumps(jsinfo, indent=2, ensure_ascii=False))

# REA JS evidence trim
rea = J(f'{ZP}/reports/rea-js-analyze.json')
norm = rea.get('normalized_result', {})
graph = norm.get('graph', {})
nodes = graph.get('nodes', [])
edges = graph.get('edges', [])
kinds = Counter(n.get('kind', '?') for n in nodes)
node_files = Counter()
for n in nodes:
    loc = n.get('location') or n.get('path') or ''
    if isinstance(loc, dict):
        loc = loc.get('path', '')
    node_files[str(loc)[:80]] += 1
rea_summary = {
    'evidence_id': rea.get('evidence_id'),
    'subject_sha256': (rea.get('subject') or {}).get('digest', {}).get('sha256'),
    'provider': rea.get('provider'),
    'operation': rea.get('operation'),
    'summary': norm.get('summary'),
    'statistics': norm.get('statistics'),
    'limitations': (norm.get('limitations') or [])[:10],
    'graph_id': graph.get('graph_id'),
    'node_count': len(nodes),
    'edge_count': len(edges),
    'node_kinds': dict(kinds.most_common(30)),
    'node_locations_top': dict(node_files.most_common(30)),
    'coverage': graph.get('coverage'),
    'relationship_coverage': rea.get('relationship_coverage'),
    'rea_limitations': (rea.get('limitations') or [])[:10],
}
W(f'{REPO}/evidence/rea-js-summary.json', json.dumps(rea_summary, indent=2, ensure_ascii=False)[:20000])

# ---------- 3. native strings deep dive ----------
lib_pat = re.compile(r'Java_[A-Za-z0-9_]+')
url_pat = re.compile(r'https?://[A-Za-z0-9._\-/:?=&%+~#@]{3,80}')
kw_pat = re.compile(r'(?i)(aes|hmac|sha256|rsa|ssl|tls|pinning|root|frida|xposed|emulator|ptrace|/proc/self|__system_property_get)')
natdeep = []
for so in sorted(os.listdir(f'{ZP}/libs')):
    p = f'{ZP}/libs/{so}'
    try:
        out = subprocess.run(['strings', '-n', '6', p], capture_output=True, text=True, timeout=60).stdout
    except Exception as e:
        natdeep.append({'file': so, 'error': repr(e)[:150]})
        continue
    lines = out.splitlines()
    javas = sorted(set(lib_pat.findall(out)))[:25]
    urls = Counter(url_pat.findall(out)).most_common(10)
    kws = Counter(m.lower() for m in kw_pat.findall(out)).most_common(15)
    interesting = [l[:160] for l in lines if re.search(r'(?i)(zalopay|alipay|sgm_|sg_|securityguard|/data/|/system/|LD_PRELOAD|openat|mmap)', l)][:15]
    natdeep.append({'file': so, 'string_lines': len(lines), 'java_symbols': javas,
                    'urls': urls, 'crypto_tamper_kw': kws, 'interesting': interesting})
W(f'{REPO}/evidence/native-strings.json', json.dumps(natdeep, indent=2, ensure_ascii=False))

# ---------- 4. resource strings retry ----------
resstrings = {'error': 'not attempted'}
try:
    sys.path.insert(0, '/tmp/venv/lib/python3*/site-packages')
    import glob
    for cand in glob.glob('/tmp/venv/lib/python*/site-packages'):
        if cand not in sys.path:
            sys.path.insert(0, cand)
    from androguard.core.apk import APK
    a = APK(BASE_APK)
    arsc = a.get_android_resources()
    strs = None
    for attr in ['get_strings_resources', 'get_resolved_strings', 'get_string_resources']:
        if hasattr(arsc, attr):
            try:
                strs = getattr(arsc, attr)()
                resstrings = {'method': attr, 'type': type(strs).__name__}
                break
            except TypeError as e:
                resstrings = {'method': attr, 'type_error': str(e)[:150]}
                continue
    if isinstance(strs, dict):
        vals = []
        for k, v in strs.items():
            if isinstance(v, dict):
                for kk, vv in v.items():
                    vals.append(str(vv))
            else:
                vals.append(str(v))
        resstrings['count'] = len(vals)
        resstrings['sample'] = vals[:40]
        hosts = Counter(re.findall(r'https?://([A-Za-z0-9.\-]+)', ' '.join(vals))).most_common(20)
        resstrings['url_hosts'] = hosts
    elif isinstance(strs, list):
        resstrings['count'] = len(strs)
        resstrings['sample'] = [str(x)[:120] for x in strs[:40]]
except Exception as e:
    resstrings = {'error': repr(e)[:300]}
W(f'{REPO}/evidence/resource-strings-retry.json', json.dumps(resstrings, indent=2, ensure_ascii=False)[:15000])

# ---------- 5. markdown reports ----------
base_meta = next((m for m in meta if m['file'] == 'vn.com.vng.zalopay.apk'), meta[0])

W(f'{REPO}/reports/00-inventory.md', f"""# 00 — Corpus inventory (all files)

Source: `Zalopay-Thanh+toán+&+Tài+chính_11.17.0_APKPure.zip` ({inv[0]['bytes']:,} bytes,
SHA256 `{inv[0]['sha256']}`).

Outer ZIP contains one XAPK ({inv[1]['bytes']:,} bytes, SHA256 `{inv[1]['sha256']}`) with entries:

| XAPK entry | bytes | SHA256 |
|---|---:|---|
""" + '\n'.join(f"| `{e['file']}` | {e['bytes']:,} | `{e['sha256']}` |" for e in inv[1]['entries']) + f"""

XAPK manifest (`evidence/xapk-manifest.json`): package `vn.com.vng.zalopay`,
versionCode 1102758, versionName 11.17.0, minSdk 26, targetSdk 36,
splits config.armeabi_v7a / config.en / config.vi / config.xhdpi.

Raw evidence: `reports/raw/inventory.json`.
""")

W(f'{REPO}/reports/01-apk-metadata.md', f"""# 01 — APK metadata, signing, components (all 5 APKs)

All five APKs validate with package `vn.com.vng.zalopay`, versionCode 1102758.
Signing cert CN `Vu Manh Hieu`, SHA256 `59dd31fd…`, valid 2016-04-21 → 2041-04-15.
Base APK: v2+v3 signed (no v1); splits: v1+v2+v3. `stamp-cert-sha256` identical
across base and config split (same signer).

Base APK components: {base_meta['components']}.
Permissions requested (AOSP): {len(base_meta['requested_aosp'])}, third-party: {len(base_meta['requested_third_party'])}.
Declared permissions: {base_meta['declared']}.
Multidex: {base_meta['multidex']}. Features: {len(base_meta['features'])} entries.

Raw evidence: `reports/raw/apk-metadata.json`.
""")

exp = mf.get('exported', {})
dl = mf.get('deeplinks', [])
W(f'{REPO}/reports/02-manifest.md', f"""# 02 — Manifest & attack surface

Application class `{mf['application'].get('name')}`, allowBackup=false, largeHeap=true,
requestLegacyExternalStorage=true, resizeableActivity=false,
networkSecurityConfig set, dataExtractionRules set.

Exported: {sum(len(v) for v in exp.values())} components —
activities {len(exp.get('activity', []))}, aliases {len(exp.get('activity-alias', []))},
services {len(exp.get('service', []))}, receivers {len(exp.get('receiver', []))},
providers {len(exp.get('provider', []))}.

Notable exported activities include SplashScreenActivityV2, QRReceiverActivity,
VNeID verification, eKYC (FPT), TrueID liveness, Compose PreviewActivity and
instrumentation bootstrap activities (test scaffolding shipped in release).

Deep links ({len(dl)} filters): schemes zalopay, zalopay-1, zalopay.api.v2,
zalopay-vnpay, fbconnect, tagmanager.c…, https hosts go.zalopay.vn,
onelink.zalopay.vn, km.zalopay.vn, zalopay.vn.

Raw evidence: `reports/raw/manifest-findings.json`.
""")

parsed = nsc.get('parsed', {})
W(f'{REPO}/reports/03-network-security.md', f"""# 03 — Network security & XML configs

Parsed {len(parsed)} XML resource files (raw: `reports/raw/network-security.json`).

- `network_security_config.xml`: base-config **cleartextTrafficPermitted=true**,
  system trust anchors, debug-overrides allow cleartext + user certs, one
  domain-config for `storage.googleapis.com` (includeSubdomains), **no pin-set**.
- `apdu_service.xml`: HCE payment AIDs incl. `325041592E5359532E4444463031`,
  `A0000000980840`, Visa AIDs.
- `file_paths.xml`: broad cache exposure (`cache-path path="/"`,
  `external-cache-path path="."`).
- AppsFlyer data excluded from cloud backup/device transfer.
- `gma_ad_services_config.xml`: attribution/topics `allowAllToAccess=true`.
""")

W(f'{REPO}/reports/04-assets.md', f"""# 04 — Asset taxonomy (base APK, 124 assets)

Extensions: {', '.join(f"{e}×{c}" for e, c in tax.get('extensions', [])[:15])}.

Notable: `assets/config/public_key.txt` (RSA-2048 public key),
`assets/config/signature.txt` (base64 signature, raw {cfg_extra.get('sig_raw_len')} bytes),
`assets/config/zalopay_config.json` ({cfg_findings.get('assets/config/zalopay_config.json', {}).get('bytes')} bytes),
`assets/config/miniapp_config.json` ({cfg_findings.get('assets/config/miniapp_config.json', {}).get('bytes')} bytes),
`assets/client_tracking.json` ({cfg_findings.get('assets/client_tracking.json', {}).get('bytes')} bytes),
Griver JS bridge, container error pages, ML Kit OCR models (.binarypb/.tflite),
bank theme configs, GTM container `GTM-PC9W5BW.json`.

Raw: `reports/raw/asset-taxonomy.json`, `reports/raw/asset-samples.json`,
`evidence/config-findings.json`.
""")

cfglines = []
for k, v in cfg_findings.items():
    if not v.get('present'):
        cfglines.append(f"- `{k}`: MISSING")
        continue
    extra = ''
    if 'top_keys' in v:
        extra = f"keys({v['top_key_count']}): " + ', '.join(f"`{kk}`" for kk in v['top_keys'][:12])
    elif 'list_len' in v:
        extra = f"list[{v['list_len']}]"
    hosts = ''
    if v.get('url_hosts'):
        hosts = ' | hosts: ' + ', '.join(f"{h}×{c}" for h, c in v['url_hosts'][:8])
    cfglines.append(f"- `{k}` ({v['bytes']:,} B, sha256 `{v['sha256'][:16]}…`): {extra}{hosts}")
W(f'{REPO}/reports/05-configs.md', "# 05 — Bundled configs deep-dive\n\n" + '\n'.join(cfglines) + f"""

Crypto envelope: RSA public key DER {cfg_extra.get('pubkey_der_len')} bytes
(RSA-2048 pattern), signature raw {cfg_extra.get('sig_raw_len')} bytes
(RSA-2048 signature length) — consistent with signed-config verification.

Raw: `evidence/config-findings.json`, `evidence/config-crypto.json`.
""")

W(f'{REPO}/reports/06-dex.md', f"""# 06 — DEX triage (20 dex, androguard lightweight parse)

Totals: classes {dex['counts']['classes']:,}, methods {dex['counts']['methods']:,}.
Per-dex breakdown in `reports/raw/dex-report.json`.

Top packages: {', '.join(f"{p} ({c:,})" for p, c in dex['top_packages'][:20])}.

String signals: {', '.join(f"{k} {v:,}" for k, v in sorted(dex['string_keywords'].items(), key=lambda x: -x[1])[:14])}.
Top URL hosts: {', '.join(f"{h}×{c}" for h, c in dex['url_hosts'][:15])}.

Pinning strings include "Certificate pinning failure!",
"Cannot find certificatest. Disable ssl pinning.",
`EVENT_SSLPINNING_*`; deeplinks include `zalopay://launch/f/changepin`,
`zalopay://launch/f/resetpin`, mini-app launch URLs.

Symbol families + 200 sampled native-method refs in raw report.
Method: per-DEX `androguard.core.dex.DEX` parse (full AnalyzeAPK OOM-killed,
exit 137 — documented limitation).
""")

rows = []
for n in nat:
    rows.append(f"| `{n['file']}` | {n['bytes']:,} | {n.get('machine')} | {n.get('dynamic_symbol_count')} | {'PIE' if n.get('flags', {}).get('PIE') else ''} |")
W(f'{REPO}/reports/07-native.md', f"""# 07 — Native libraries (28 .so triage)

27× ARM32 libs from `config.armeabi_v7a.apk` + 1 tiny non-ELF blob
`vn.com.vng.zalopay__trueidlib.so` (392 B) in base APK.

| file | bytes | arch | dynsyms | flags |
|---|---:|---|---|---|
""" + '\n'.join(rows) + """

Key libs: `libsecurity_lite.so` (Alipay SecurityGuard — JNI
`Java_com_alipay_plus_security_lite_SecurityGuardLiteNative_{init,encrypt,decrypt,signRequest}`
found via strings, not dynamic symbols), `libsqlcipher.so`, `libtaibmcf.so`,
`libVFaceLib.so`, face/OCR/vision stack (`libface_detector_v2_jni`,
`libbarhopper_v3`, `libmlkit_google_ocr_pipeline`, `libpicodet_ncnn`),
`librive-android.so`, Crashlytics trio.

All ELF libs: PIE + RELRO + BIND_NOW, no executable stack.
`readelf --dyn-syms` shows no `Java_*` exports (all JNI via RegisterNatives or
stripped); strings-level JNI evidence in `evidence/native-strings.json`.

Raw: `reports/raw/native-report.json`.
""")

jsrows = '\n'.join(f"| `{j['file']}` | {j['bytes']:,} | {j['lines']} | {', '.join(j['bridges'][:4]) or '—'} |" for j in jsinfo)
W(f'{REPO}/reports/08-js-bridge.md', f"""# 08 — JS/HTML bridge + REA evidence

19 extracted web assets (100,878 bytes total), analyzed with
`rea-agents@6.5.0 analyze-javascript-application` (latest 6.10.x requires
Node ≥22.23.4; sandbox runs 22.22.3) + manual review.

| file | bytes | lines | bridge symbols |
|---|---:|---:|---|
{jsrows}

Findings: `ZaloPayJSBridge` / `ZaloPayInternalJSBridge` (callbackPool,
subscribers, initializeWebBridge), Griver/Alipay `AlipayJSBridge` compat shims
(`appx_inline_bootstrap_min`, `h5_bridge`, `workerjs`, `cover_component`
androidEmbedViewManager), error-page templates, `webapp.js`
`webapp_getNavigation` util probes.

REA evidence: id `{rea_summary.get('evidence_id')}', {rea_summary.get('node_count')} semantic nodes,
{rea_summary.get('edge_count')} edges; kinds: {rea_summary.get('node_kinds')}.
Full 5.8 MB evidence: `reports/raw/rea-js-analyze.json`; trimmed:
`evidence/rea-js-summary.json`, `evidence/jsbridge-inventory.json`.

REA gaps (documented): `analyze` needs Ghidra/Hopper/IDA providers,
`inspect-android-class` needs caller-supplied jadx-headless-mcp 0.7.1 JAR —
see `reports/raw/rea-apk-analyze.json`, `reports/raw/rea-app-class.json`.
""")

W(f'{REPO}/reports/09-synthesis.md', """# 09 — Synthesis: architecture, risks, reproducibility

Architecture: multi-split XAPK (base + ABI + density + 2 locales), 20 DEX
(63,827 classes / 378,040 methods), heavyweight third-party SDK mesh
(Google, BouncyCastle, Alibaba/Griver/Alipay mini-app runtime, Meta Audience
Network, Visa, VNPay, AppsFlyer, ML Kit OCR, TrueID/FPT eKYC), ARM32-only
native split with SecurityGuard lite + SQLCipher + face/OCR stack.

Risk notes (static-only, to verify dynamically): global cleartext permitted
with no pin-set in NSC while pinning strings exist in code (policy/code gap);
broad FileProvider cache paths; exported test/preview activities; HCE AID
surface; signed-config envelope present (RSA-2048) — verify trust chain.

Reproducibility: all hashes in `00-inventory`; tool versions pinned in
`RESEARCH_PLAN.md`; raw JSON in `reports/raw/`; scripts in `tools/`;
REA provider gaps documented in `08-js-bridge`.

Next: TypeScript video (`video/`) rendering these findings to MP4.
""")

W(f'{REPO}/RESEARCH_PLAN.md', """# Research plan — ZaloPay 11.17.0 XAPK reverse engineering

## Corpus
`Zalopay-Thanh+toán+&+Tài+chính_11.17.0_APKPure.zip` → 1 XAPK → 5 APKs + manifest + icon.
100% entry coverage: every file enumerated by SHA256 (reports/00).

## Toolchain (pinned)
- Python 3 + androguard 4.1.4 (DEX/AXML/ARSC), stdlib zipfile/hashlib
- rea-agents@6.5.0 (JS app graph; latest needs newer Node) — morluto/rea methodology
- binutils readelf/strings/nm/objdump; openssl
- Node 22.22.3 / npm 10.9.8 (sandbox)
- Video: TypeScript + pure-TS PNG/WAV synthesis + ffmpeg_mux to MP4

## Phases
1. ✅ Inventory/hash/metadata (stage1) 2. ✅ Manifest/NSC/assets/strings (stage2)
3. ✅ DEX triage lightweight (stage3b; full AnalyzeAPK OOM) 4. ✅ Native triage (stage4)
5. ✅ JS/HTML + REA evidence 6. ✅ Config deep-dive 7. ▶ TypeScript MP4 video
8. Final citation package (BibTeX, transcript, chapters)

## Sync policy
Every phase commits + pushes to `arena/753157fb-repo` and syncs the open PR.
PRs are opened, never merged, without waiting for approval.
""")

W(f'{REPO}/README.md', """# ZaloPay 11.17.0 (APKPure XAPK) — Deep Reverse-Engineering Research + Documentary Video

Corpus: `Zalopay-Thanh+toán+&+Tài+chính_11.17.0_APKPure.zip` (98,813,878 bytes).

- `RESEARCH_PLAN.md` — methodology, toolchain pins, phases
- `reports/00–09` — full findings (inventory → synthesis)
- `reports/raw/` — machine-readable JSON evidence
- `evidence/` — trimmed digests (configs, JS bridges, native strings, REA summary)
- `tools/` — analysis scripts (stage1/2/3b/4)
- `video/` — TypeScript documentary generator → MP4 (in progress)

All work syncs to the open PR on branch `arena/753157fb-repo`.
""")
print('digest complete')
print('repo files:', sum(len(f) for _, _, f in os.walk(REPO) if True))
