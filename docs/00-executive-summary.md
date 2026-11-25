# 00 — Executive summary

**Artifact.** `Zalopay-Thanh toán & Tài chính_11.17.0_APKPure.zip`
(SHA-256 `34b9b6b6…b4610a`, 98,813,878 bytes) is an APKPure *XAPK* container
(`xapk_version: 2`) holding five APKs: a 79.4 MB base (`vn.com.vng.zalopay.apk`,
6,162 ZIP entries, 136.4 MB uncompressed), an ABI split
(`config.armeabi_v7a.apk`, 24.8 MB), a density split (`config.xhdpi.apk`, 1.2 MB)
and two language splits (`config.vi.apk`, `config.en.apk`).
The app is `vn.com.vng.zalopay` version 11.17.0 (versionCode 1102758),
`minSdk 26`, `targetSdk 36`, `compileSdk 36`.

**Method.** PayTear 0.4 re-implements the container and binary formats in
TypeScript (ZIP, AXML, `resources.arsc`, DEX, ELF, APK Signature Scheme v2/v3)
and runs a nine-stage pipeline: *intake → split expansion → manifest/resource
decode → DEX census → native census → signature verification → fingerprinting →
privacy/network extraction → report generation*. The whole teardown executes in
**3.7 s** on two vCPUs with no JVM, no Android SDK and no network access. All
results are deterministic JSON.

## Findings

### F1 — 32-bit-only native distribution (structural)
All 27 native libraries (54.8 MiB) exist only under `lib/armeabi-v7a/`. There is
no `arm64-v8a` split. Because Google Play has required 64-bit binaries since
2019, this is an artefact of the APKPure bundle rather than of the vendor's Play
release. *Evidence:* `native.abis = ["armeabi-v7a"]`, `container.apkSplits`.
*Impact:* any analysis (or automated SDLC scanner) that treats this file as "the
app" will over-weight 32-bit code paths and miss ARM64-only vendor hardening.

### F2 — v2 + v3 signature, verified in-process (cryptographic)
Both signature schemes parse and the RSA-2048 signature over the signed-data
block verifies with `node:crypto` for all five splits. Signer identity is a
self-signed certificate `CN=Vu Manh Hieu`, serial `153EA9FD`, valid
2016-04-21 → 2041-04-15, certificate SHA-256 `59dd31fd…8cdae`.
The block additionally carries `source-stamp` (3,061 B), `play-frosting` (691 B)
and `padding` (1,602 B) pairs; v1 JAR signing is absent (consistent with
`minSdk 26`). *Evidence:* `splits[].signing.schemes[].signers[].signatures[].verified = true`.
*Caveat:* the chunked content-digest recomputation (SHA-256 over three archive
sections) does **not** reproduce the recorded digest. See `02-artifact-and-signing.md`
§4 — we log this as open question **R1** rather than as a claim, because the
reference implementation (`apksigner`) needs a JVM unavailable in this sandbox.

### F3 — Renaming-only obfuscation (measurement)
63,827 `class_defs` (60,671 unique names across 20 DEX files) contain 287,480
methods, 286,224 fields and 404,914 string-pool entries; the instruction walker
decodes 9,258,758 instructions and 1,729,915 invoke edges.
**67.9 %** of classes have ≤ 2-character simple names, yet **82.6 %** still carry
a `SourceFile` attribute, and the string pool is fully intact
(242,266 string constants reachable from code). *Evidence:*
`dex.obfuscation`, `dex.totals`. *Impact:* R8/ProGuard renaming was applied
without metadata stripping, so semantic recovery (endpoint, error-message and
class-purpose inference) is cheap for an analyst and expensive to prevent
retroactively.

### F4 — Cleartext traffic permitted globally (configuration)
`res/xml/network_security_config.xml` contains
`<base-config cleartextTrafficPermitted="true">` with `system` trust anchors
only, plus `debug-overrides` that trust user CAs. A single domain-config entry
covers `storage.googleapis.com` (with `includeSubdomains`). For a payment client
on API 36 this departs from the platform default (`cleartextTrafficPermitted`
defaults to *false* for `targetSdk ≥ 28`). *Evidence:* decoded AXML in
`docs/generated/` and `analysis/report.json` (`splits[].appAttrs.networkSecurityConfig`).
*Impact hypothesis:* defence-in-depth loss — a single accidental `http://` URL
becomes a live downgrade path, and the app advertises it to interception proxies.

### F5 — Non-production endpoints inside the release DEX (hygiene)
Seven staging/sandbox hosts are present as string constants:

| Host | Provenance |
|---|---|
| `http://iopengw-280.sggz00b.dev.alipay.net/` | Alipay dev gateway (**cleartext**) |
| `https://mdgwdev.alipay.net` | Alipay dev gateway |
| `https://imgs-sit-sg.dl.alipaydev.com/imgw.htm` | Alipay SIT image CDN |
| `https://imgs-ac-amcs.dl.alipaydev.com/imgw.htm` | Alipay dev CDN |
| `https://googletng.dl.alipaydev.com/…oauth…` | Alipay dev OAuth |
| `https://web-ekyc-uat.fpt.ai/api/v1/` | FPT.AI eKYC UAT |
| `https://ekyc-dev.fpt.ai/api/v1/` | FPT.AI eKYC dev |
| `https://uat.tenpayglobal.com/sg/inbound-payment/merchant-oauth.shtml` | Tenpay Global UAT |
| `https://dpe.sandbox.consumerapi.digital.visa.com` | Visa sandbox |
| `^((.+)\\.)?zpsandbox\\.zing\\.vn` | ZaloPay sandbox URL matcher |

*Evidence:* `network.cleartextUrls`, `indicators[cleartext]`. *Impact:* configuration
and dependency leakage; these hosts are frequently unprotected by production WAF
policy and are attractive to attackers enumerating staging surfaces.

### F6 — Card emulation without device-unlock requirement (design)
`apdu_service.xml` registers seven AIDs — `325041592E5359532E4444463031` (PPSE),
`A0000000031010` (Visa credit/debit), `A0000000032010` (Visa Electron),
`A0000000033010` (Visa Interlink), `A0000000032020` (Visa Plus),
`A0000000980840` (UnionPay), `A000000003` (Visa) — under a
`host-apdu-service` with `android:requireDeviceUnlock="false"`, category
`payment`. Two further HCE services come from the mPaaS container
(`TinyAppHostApduService`, both Alibaba packages). *Evidence:* decoded
`res/xml/apdu_service.xml`, `manifest` service list. *Impact hypothesis:* the OS
will route APDUs to the app while the device is locked; the app must therefore
gate token provisioning/transaction authorisation entirely in software.

### F7 — 3.9 MB of unreachable React Native (dead code)
`assets/android.1.zip` (3.9 MB) contains `main.jsbundle` (3.6 MB, plain JS for a
pre-Hermes RN runtime) and 326 drawable entries with timestamps
`2022-03-08 12:24`. The APK contains **no** `libreactnativejni.so`, no Hermes
runtime, and no DEX string references to `main.jsbundle`/`android.1.zip`; only
the class name `com.facebook.react.ReactRootView` survives. *Evidence:*
`assets.notable`, `splits[].nativeLibs`, DEX string probes. *Impact:* 4.6 % of
base-APK bytes are unexecutable legacy surface that still must be reviewed,
fuzzed and attributed by SBOM tooling.

### F8 — A native-first anti-tamper stack that is partly orphaned (architecture)
Five randomly named libraries — `libtaibmcf.so` (12.0 MiB), `libfgcpkjvqe.so`
(6.2 MiB), `libljsfrcwmw.so` (0.57 MiB), `libohxbee.so` and `liba97d.so` — embed
OpenSSL/BoringSSL X.509 and `X509TrustManager` symbols, with `libohxbee.so`
referencing `vn.com.vng/bcell/BShieldApplication` and `vn/ai/faceauth/...`.
**No `vn.com.vng.bcell` class exists in any DEX**, so the anti-tamper entry points
that the native layer expects are either dead or resolved dynamically.
`libsecurity_lite.so` exports the Alipay SecurityGuard Lite JNI surface
(`Java_com_alipay_plus_security_lite_SecurityGuardLiteNative_encrypt`, AES/RSA
primitives). *Evidence:* `native.libs[].jniExports`, DEX class index.
*Impact:* a defender cannot infer the protection stack from Java code alone —
and an attacker learns that at least one control is not wired to Java.

### F9 — 62 SDK fingerprints / 17 vendors (supply chain)
Largest by class count: Google (7,761), BouncyCastle (6,112), Alibaba
mPaaS+Griver (4,821), Meta (3,622), IAP Connect (1,599), Visa (1,370),
AndroidX Media3 (1,198), ZaloPay/VNG (1,054), CameraX (813), TrueID (689),
Compose (504), Alipay (493). Native-only members: MediaPipe face detector, ML Kit
OCR/barcode pipelines, PaddlePaddle PicoDet on NCNN, SQLCipher, Rive, Glide
transcoders, Crashlytics. *Evidence:* `supplyChain.sdkMatches`,
`docs/generated/supply-chain.md`.

### F10 — 27 privacy-relevant permissions (governance)
Of 49 declared permissions, 27 map to runtime-privacy categories and 9 are
high-risk: `READ_CONTACTS`, `WRITE_CONTACTS`, `CAMERA`, `RECORD_AUDIO`,
`ACCESS_FINE_LOCATION`, `ACCESS_COARSE_LOCATION`, `READ_PHONE_STATE`,
`QUERY_ALL_PACKAGES`, plus overlay-capability (`MANAGE_OVERLAY_PERMISSION`).
Also present: `READ/WRITE_CALENDAR`, `DETECT_SCREEN_CAPTURE`, `NFC`,
`READ_MEDIA_AUDIO`, `AD_ID` and the four `ACCESS_ADSERVICES_*` permissions.
*Evidence:* `privacy.dangerous`, `docs/generated/permissions.md`. *Impact:*
contact and calendar write access in a wallet are disproportionate to the core
function and are the kind of permission set that Play policy review and privacy
regulators scrutinise.

## What this work contributes

1. **A reproducible measurement pipeline** for commercial Android artefacts that
   does not depend on a JVM, an SDK, a decompiler version or network access.
2. **A quantified census** of a large production fintech client (63,827 classes,
   9.26 M instructions, 27 native libraries, 62 SDK fingerprints) that other
   researchers can diff, extend or contest.
3. **Ten falsifiable claims** with field-level evidence pointers, several of which
   are configuration-level defects that survive version updates.
4. **An artefact family** — JSON corpus, SVG figures, paper, video — generated from
   one deterministic source of truth, so prose, chart and narration cannot drift.
