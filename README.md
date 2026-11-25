# The Anatomy of a Super-App Wallet
### A forensic static-analysis study of **Zalopay 11.17.0** (`vn.com.vng.zalopay`) — and a documentary film about it

<p align="left">
  <img alt="status" src="https://img.shields.io/badge/study-complete-00e5a0">
  <img alt="artefact" src="https://img.shields.io/badge/artefact-XAPK%20%C2%B7%20105.6%20MB-1f6feb">
  <img alt="dex" src="https://img.shields.io/badge/dex-19%20files%20%C2%B7%20264%2C525%20methods-8957e5">
  <img alt="native" src="https://img.shields.io/badge/native-27%20.so%20%C2%B7%2057.5%20MB-f0883e">
  <img alt="film" src="https://img.shields.io/badge/film-TypeScript%20%E2%86%92%20MP4%201080p30-ff7b72">
</p>

This repository contains the complete, reproducible reverse-engineering study of the
**Zalopay 11.17.0** Android release distributed by APKPure as an XAPK, together with a
cinematic documentary film authored entirely in **TypeScript** and rendered to **MP4**
with a fully synthesised score and narration.

Nothing in this repository contains user data, credentials, keys, or exfiltrated
material. Every claim on screen is traceable to a JSON evidence file under
`film/data/`, produced by the collectors in `film/tools/`.

---

## 1. What was reverse engineered

| Layer | Artifact | Volume |
|---|---|---|
| Container | `Zalopay-Thanh toán & Tài chính_11.17.0_APKPure.zip` → `.xapk` → 5 split APKs | 105.6 MB / 7 entries |
| Manifest | binary AXML + `resources.arsc` | 200 activities · 34 services · 22 receivers · 9 providers · 49 permissions |
| Bytecode | 19 `classes*.dex` | 60,671 classes · 264,525 methods · 273,119 fields · 374,276 strings |
| Native | `lib/armeabi-v7a/*.so` | 27 libraries · 57,452,124 bytes · 13 with `JNI_OnLoad` |
| Assets | `assets/**` + `res/**` | 124 assets (30.0 MB) · 5,604 resource files · 1.8 MB `resources.arsc` |
| SDKs | `*.properties` build manifests | 69 attributed third-party SDKs |
| Crypto | JCA / Keystore / BouncyCastle / SQLCipher call graph | 49 encrypted-DB classes · 25 pinning-aware classes |

## 2. Headline findings (each one is machine-verified in `film/data/`)

1. **The container is an APKPure XAPK v2** holding one base APK plus four split
   configs (`armeabi_v7a`, `en`, `vi`, `xhdpi`). The outer ZIP is byte-for-byte a
   single-entry container; the inner XAPK carries `manifest.json` with
   `target_sdk_version: 36`.
2. **Signing identity** — v2+v3 scheme, single self-signed 2048-bit RSA certificate,
   `CN=Vu Manh Hieu`, valid 2016-04-21 → 2041-04-15,
   SHA-256 `59dd31fdf36070a0d7f8c8cde843fac371df7f1a915c0136c9c9b55992e8cdae`.
3. **The release is not debuggable** (`android:debuggable` absent),
   `allowBackup="false"`, `fullBackupContent="false"`, `dataExtractionRules` declared.
4. **Application-wide cleartext is permitted.** `res/xml/network_security_config.xml`
   declares `base-config cleartextTrafficPermitted="true"` and a `debug-overrides`
   block that trusts **user-installed CAs**. No `<pin-set>` is declared anywhere in the
   config — certificate pinning, where it exists, is enforced in code
   (`okhttp3.CertificatePinner`, 25 referencing classes) and in native layers.
5. **DEX obfuscation is aggressive but granular**: 43,968 of 60,671 classes (72.5 %)
   have simple names of ≤ 2 characters. First-party code (`vn.com.vng.zalopay.*`,
   `vn.zalopay.*`) is comparatively readable: 1,138 classes with self-documenting
   feature packages (`bankbinding`, `cashin`, `changepin`, `loginv2`,
   `livenessdetection`, `quickpay_native`, `user_profile.protectaccount`, …).
6. **Native hardening is real.** 27 ELF objects, all ARM32, **all stripped**
   (`symtab = 0` except one Crashlytics trampoline). Twelve are deliberately
   obfuscated: `libtaibmcf.so` (12.6 MB anti-fraud engine with in-process
   `mprotect`, zip inflate and `AAssetManager` unpacking), `libfgcpkjvqe.so`
   (6.5 MB, `.rodata` entropy 7.28), `libljsfrcwmw.so`, `liblytcuhe.so`,
   `librcmefqonnx.so`, `liba97d.so` (JNI bridge), plus Alipay's
   `libsecurity_lite.so` (AES + request signing over a bignum stack, exported
   `__obfuscator_version__`).
7. **Storage is encrypted at the page level** — SQLCipher (`libsqlcipher.so`,
   3.6 MB) with 49 referencing classes, alongside the Android Keystore
   (43 classes) and hardware biometrics (53 classes).
8. **No trojan primitives were found.** Across all 60,671 classes there is **no
   `AccessibilityService` subclass, no device-admin receiver, no
   `NotificationListenerService`, and no `SmsManager` send path.** The capabilities
   that *are* present map one-to-one onto product functions: eKYC (camera2, ML Kit
   OCR + barcode, face detection, native liveness), NFC/EMVCo top-up (`org.jmrtd`,
   `org.ejbca`), the Alipay+ miniapp container (`com.alibaba.ariver`/`griver`,
   4,777 classes), Visa SRC, VNPAY, AppsFlyer and Firebase.
9. **The app is a container platform, not a monolith.** Alipay's ARiver/Griver
   miniapp runtime (4,777 classes) plus `com.iap.ac.android` (1,450) means a large
   share of the visible product is delivered as signed, remotely-updated mini
   programs — the single most important architectural fact about the APK.

## 3. Repository map

```
.
├── Zalopay-..._APKPure.zip        # the artefact under study (untouched, 98.8 MB)
├── film/
│   ├── tools/                     # reproducible collectors (Python + androguard)
│   │   ├── collect.py             #   8-stage evidence collector
│   │   ├── extras.py              #   NSC / exported components / native / crypto
│   │   └── triage.py              #   capability + permission risk model
│   ├── data/                      # evidence JSON (committed, auditable)
│   ├── src/                       # the film, in TypeScript
│   │   ├── film/                  #   scenes, timeline, renderer, motion system
│   │   ├── audio/                 #   score synthesis + narration timeline
│   │   └── research/              #   typed access layer over film/data
│   ├── out/                       # rendered MP4 / WAV (git-ignored)
│   └── package.json, tsconfig.json
├── research/
│   ├── PAPER.md                   # the written study (citable, with appendix)
│   ├── METHOD.md                  # exact toolchain + reproduction steps
│   └── FINDINGS.md                # finding-by-finding evidence index
└── .gitignore
```

## 4. Reproduce

```bash
# 1. toolchain
python3 -m venv .venv && . .venv/bin/activate
pip install androguard
npm --prefix film install

# 2. unpack the container (outer zip -> xapk -> split APKs -> raw trees)
unzip "Zalopay-Thanh+toán+&+Tài+chính_11.17.0_APKPure.zip" -d work/extract
unzip work/extract/*.xapk -d work/xapk
for f in work/xapk/*.apk; do unzip -o "$f" -d "work/unpacked/$(basename "$f" .apk)"; done

# 3. regenerate every number used in the film
ZP_BASE=work/unpacked/vn.com.vng.zalopay python3 film/tools/collect.py work/xapk/vn.com.vng.zalopay.apk film/data
ZP_BASE=work/unpacked/vn.com.vng.zalopay python3 film/tools/extras.py  work/xapk/vn.com.vng.zalopay.apk film/data
ZP_BASE=work/unpacked/vn.com.vng.zalopay python3 film/tools/triage.py  work/xapk/vn.com.vng.zalopay.apk film/data

# 4. build + render the film
npm --prefix film run build
npm --prefix film run film        # -> film/out/*.mp4
```

## 5. Ethics & scope

This is **static** analysis of a publicly distributed artifact. No runtime
instrumentation, no traffic interception, no credential extraction, no attempt to
defeat the app's protections was performed. Findings describe *structure and
posture*; they contain no keys extracted from the binary, no user data, and no
step-by-step bypass instructions. See `research/METHOD.md` for the full limitations
section.

## 6. Citation

If this study or the film is useful in your work, cite it as:

```bibtex
@misc{zalopay2026anatomy,
  title  = {The Anatomy of a Super-App Wallet: A Forensic Static Analysis of Zalopay 11.17.0},
  author = {Arena Agent Mode Research},
  year   = {2026},
  note   = {Artifact study + TypeScript-rendered documentary, vn.com.vng.zalopay 11.17.0 (APKPure XAPK)},
  url    = {https://github.com/johnlikescarrot/-}
}
```

> The badge numbers in this README are regenerated from `film/data/` — they are not
> hand-written. If the artifact changes, the README rots loudly.
