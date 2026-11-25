# ZaloPay 11.17.0 — Static Teardown, Toolchain and Citation Artifact

> **A reproducible, toolchain-independent reverse-engineering study of the ZaloPay
> Android artifact (`vn.com.vng.zalopay` 11.17.0, versionCode 1102758) distributed as
> `Zalopay-Thanh toán & Tài chính_11.17.0_APKPure.zip` — plus the TypeScript toolkit
> that produced every number in this repository, a peer-review-shaped paper, and a
> 1080p documentary video.**

| | |
|---|---|
| **Subject** | XAPK (split APK bundle) containing 5 APKs, 105.6 MB uncompressed |
| **Package** | `vn.com.vng.zalopay` — ZaloPay (VNG Corporation) |
| **Version** | 11.17.0 / 1102758 · minSdk 26 · targetSdk 36 · compileSdk 36 |
| **Input SHA-256** | `34b9b6b6735f0f739617f97a259f931d17cdbacb51871c40b066de41f4b4610a` |
| **Toolkit** | **PayTear 0.4** — TypeScript, zero runtime dependencies, no JVM, no Android SDK, no network |
| **Corpus** | `analysis/report.json` (machine-readable), `analysis/figures/*.svg`, `docs/` |
| **Video** | `video/out/zalopay-teardown-1080p.mp4` (rendered from the same JSON) |

## Why this repository exists

Commercial Android apps are the densest artefacts in consumer software: a single
79.4 MB APK (130 MiB uncompressed, 6,162 ZIP entries) in this study contains **63,827 class definitions**, **287,480 methods**,
**9.26 million decoded instructions**, **27 native libraries** and **25+ third-party
SDKs** from nine countries. Yet the dominant analysis pipeline (apktool → jadx →
grep) is *stateful, JVM-bound, and only weakly reproducible*: two analysts with
different tool versions routinely obtain different "facts".

This project takes the opposite route. It re-implements the necessary binary
formats — ZIP, Android binary XML (AXML), `resources.arsc`, Dalvik EXecutable
(DEX), ELF and the APK Signature Scheme v2/v3 — **directly in TypeScript**, and
then treats the APK as a dataset rather than as a reverse-engineering session.
The result is a teardown that anyone can re-run with `node`, that emits stable
JSON/CSV/figures, and that is safe to publish because the pipeline is static-only
and redacts credentials by construction.

## Headline findings

1. **A 32-bit-only artefact.** Every native payload (55.1 MB, 27 libraries) exists
   only for `armeabi-v7a`. The XAPK ships no `arm64-v8a` split, although the app
   targets API 36 and the Play Store has required 64-bit support since 2019 — the
   bundling is a property of this *distribution channel*, not of the vendor.
   Analysts who reason about "the ZaloPay app" from this file alone will
   systematically mis-model the shipped native attack surface.
2. **Signature verified without apksigner.** The signature blocks parse as
   APK Signature Scheme **v2 + v3** (RSASSA-PKCS1-v1_5-SHA256, RSA-2048,
   self-signed `CN=Vu Manh Hieu`, valid 2016-04 → 2041-04, certificate SHA-256
   `59dd31fd…`), with `source-stamp` and `play-frosting` pairs present and **no
   v1 (JAR) signature**. PayTear verifies the crypto itself via `node:crypto`:
   the signed-data digests verify for all five splits.
3. **No platform hardening at the manifest level.** The application class is
   renamed (`jwbmqnsll.xvndbxlk` extends `vn.com.vng.zalopay.AndroidApplication`),
   yet **82.6 % of classes still carry a `SourceFile` attribute** and **67.9 % have
   ≤ 2-character simple names** — i.e. off-the-shelf R8 renaming without
   `-renamesourcefileattribute`. Obfuscation is *naming-only* for most of the
   corpus, and string-level semantics survive intact.
4. **Cleartext is globally permitted.** `res/xml/network_security_config.xml`
   declares `<base-config cleartextTrafficPermitted="true">` with system-only
   trust anchors, and `debug-overrides` additionally trusts user-installed CAs.
   For a payment app on API 36 this is a policy outlier; the only domain-scoped
   entry is `storage.googleapis.com`.
5. **Seven non-production endpoints ship in the release DEX.** Including
   `http://iopengw-280.sggz00b.dev.alipay.net/`, `https://mdgwdev.alipay.net`,
   `https://web-ekyc-uat.fpt.ai/api/v1/`, `https://ekyc-dev.fpt.ai/api/v1/`,
   `https://uat.tenpayglobal.com/…`, `https://imgs-sit-sg.dl.alipaydev.com/…` and
   `https://dpe.sandbox.consumerapi.digital.visa.com`.
6. **Card emulation while locked.** `VisaHostApduService` registers seven payment
   AIDs (Visa credit/debit/Electron/Interlink/Plus, UnionPay `A0000000980840`, and
   the PPSE `2PAY.SYS.DDF01`) with `android:requireDeviceUnlock="false"`.
7. **3.9 MB of dead React Native.** `assets/android.1.zip` contains a 3.6 MB
   `main.jsbundle` plus 326 drawable entries dated **2022-03-08**; no
   `libreactnativejni.so`, no Hermes runtime and no DEX string references to the
   bundle exist. A legacy JS layer is shipped but unreachable.
8. **A security stack that is mostly native and partly orphaned.** Five
   randomly-named libraries (`libtaibmcf.so` 12.0 MB, `libfgcpkjvqe.so`,
   `libljsfrcwmw.so`, `libohxbee.so`, `liba97d.so`) carry OpenSSL/X.509
   trust-manager strings and reference `vn.com.vng.bcell.BShieldApplication` — a
   class that **does not exist in any DEX**. Alipay's `SecurityGuardLite` JNI
   surface is present in `libsecurity_lite.so` (`Java_com_alipay_plus_security_lite_*`).
9. **A real dependency surface of 62 SDK fingerprints across 17 vendors**, including Alibaba mPaaS/Ariver
   (4,821 classes) and Griver mini-program containers, IAP Connect (1,599),
   Visa CBP (900 + 1,370), Meta Audience Network (3,156 classes in a 4.6 MB `audience_network.dex` shipped under `assets/`, plus 3,622 `com.facebook.*` classes),
   AppsFlyer, Firebase, ML Kit OCR/barcode, MediaPipe face detection, PaddlePaddle
   PicoDet on NCNN, SQLCipher, BouncyCastle (6,112) and Nimbus JOSE.
10. **Nine high-risk Android permissions** among 49 — contacts (read *and* write),
    calendar, microphone, camera, precise location, telephony state,
    `QUERY_ALL_PACKAGES`, and overlay (`SYSTEM_ALERT_WINDOW`-adjacent) capability.

Each claim above maps to a reproducible field in `analysis/report.json`; see
`docs/00-executive-summary.md` for the evidence table.

## Repository layout

```
analysis/            machine-readable results (JSON, SVG figures, CSV/MD tables)
docs/                narrative teardown (00…09) + generated tables
paper/               the paper (Markdown source, PDF, BibTeX)
tools/paytear/       the TypeScript toolkit (AXML, ARSC, DEX, ELF, signing, charts)
video/               the TypeScript video engine + narration script + MP4
scripts/             one-shot reproduction scripts
```

## Reproduce

```bash
# 1. toolkit (Node ≥ 20, no Java)
cd tools/paytear && npm ci

# 2. full static teardown of the archive (≈4 s for 105 MB)
npx tsx src/cli.ts analyze "../../Zalopay-Thanh+toán+&+Tài+chính_11.17.0_APKPure.zip" \
    --out ../../analysis --deep

# 3. regenerate the documentation tables from the JSON
npx tsx src/docs.ts ../../analysis/report.json ../../docs/generated

# 4. optional: rebuild the video (needs ffmpeg on PATH)
cd ../../video && npm ci && npm run render
```

## Research ethics and scope

* **Static only.** No packet capture, no runtime instrumentation, no interaction
  with ZaloPay production systems, no attempt to bypass authentication, signing,
  root detection or any other control.
* **No offensive output.** The repository publishes *structural measurements*,
  public metadata and defanged strings. Credentials, keys, tokens and customer
  data are redacted at extraction time (`strings.ts → redact`), and no material
  that could be replayed against the service is included.
* **No exploitation.** Findings are described as observations with impact
  hypotheses, not as proof-of-concept exploits. No CVE is claimed.
* **Independent.** Not affiliated with, endorsed by, or coordinated with VNG
  Corporation. The artefact is a third-party redistribution (APKPure), and the
  paper is explicit about what that implies for representativeness.

## Licence

Code: MIT. Prose, figures and video: CC BY 4.0. The analysed APK is **not**
redistributed here.
