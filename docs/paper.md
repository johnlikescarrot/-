# Static Reverse-Engineering of a Vietnamese E-Wallet at Scale: A Reproducible, Evidence-Based Study of Zalopay 11.17.0 (XAPK)

**Authors:** Arena Agent (automated research pipeline) · **Date:** 2026-11-25
**Artifact:** `Zalopay-Thanh+toán+&+Tài+chính_11.17.0_APKPure.zip` → XAPK `vn.com.vng.zalopay` 11.17.0 (1102758)
**Code:** `tools/analyze_apk.py` · **Data:** `docs/analysis.json` · **Video:** `video/` (TypeScript → MP4)

## Abstract

We present a fully reproducible static reverse-engineering study of a production
Vietnamese e-wallet (Zalopay 11.17.0, ~105.6 MB XAPK, 6.162-entry base APK).
Using a dependency-free Python pipeline (ZIP/XAPK inventory, binary-XML string
decoding, DEX string mining, native-library JNI fingerprinting, and embedded-config
analysis), we map the complete architecture: 20 DEX files (~69.7 MB, ~793k strings),
~212 activities, 50+ permissions, 27 ARMv7 native libraries, 373 mini-apps,
57-key runtime configuration, a React Native bundle, Alipay/Griver mini-program
bridges, and an on-device ML stack (OCR, face/liveness, barcode, NCNN detection).
We document security controls (gRPC/HTTPS, RSA signing, SQLCipher, Keystore/biometrics,
WebView allow-lists) and residual risks (broad JS bridges, tracking SDKs, wide
permissions). All findings are machine-readable in `analysis.json` and narrated in a
TypeScript-generated MP4 video. We release methods, data, and video for verification.

**Keywords:** Android reverse engineering, XAPK, e-wallet, static analysis, reproducible research, fintech security

## 1. Introduction

Mobile wallets concentrate payment, identity (eKYC), and personal data in a single
binary. Understanding their architecture without source code requires careful static
analysis. Prior work often relies on heavyweight decompilers (jadx, apktool) that are
hard to reproduce in constrained sandboxes. We show that a stdlib-only pipeline can
recover a comprehensive architectural map, following the evidence-based philosophy of
agent-assisted RE frameworks such as [morluto/rea](https://github.com/morluto/rea)
(inventory → extract → analyze → verifiable evidence).

**Contributions.** (1) A reproducible analyzer (`tools/analyze_apk.py`) producing
`analysis.json`. (2) A complete architectural inventory of Zalopay 11.17.0
(see `REVERSE_ENGINEERING_REPORT.md`). (3) A TypeScript video pipeline
(SVG → PNG → H.264/AAC MP4) with Vietnamese narration and subtitles.
(4) Citation-ready metadata (BibTeX, Zenodo/CITATION.cff) for reuse.

## 2. Method

We unpack ZIP → XAPK → 5 split APKs. For each split we inventory entries, extensions,
and sizes. From the base APK we decode `AndroidManifest.xml` binary strings
(activities, permissions, deep links), mine DEX strings (SDK markers, URLs/domains,
crypto APIs), parse `zalopay_config.json` / `miniapp_config.json` / `home_data.json`
(structure, counts, allow-lists), inspect JS bridges and the React Native bundle,
and fingerprint native `.so` files via JNI symbols and keyword counts. No dynamic
execution is performed. Hashes (SHA-256 prefixes) anchor every artifact.

## 3. Findings (summary)

- **Packaging:** XAPK v2, package `vn.com.vng.zalopay`, minSdk 26, targetSdk 36,
  splits: base, armeabi-v7a, en, vi, xhdpi.
- **Code:** 20 DEX (~69.7 MB); Kotlin/Compose, OkHttp/Retrofit, Protobuf/gRPC,
  Room, WorkManager, Dagger, Coroutines (versions in `analysis.json`).
- **UI:** ~212 activities spanning home, auth/KYC/eKYC, cashier, banking, QR/NFC,
  mini-apps, widgets; 1.498 layouts; Rive animations; 25 fonts.
- **Native:** 27 ARMv7 `.so` — ML Kit OCR/face/barcode, OpenCV face auth, TrueID,
  liveness, NCNN PicoDet, Rive, SQLCipher, SecurityGuard Lite, plus obfuscated
  commercial protectors (`libtaibmcf`, `libfgcpkjvqe`, …).
- **Runtime config:** gRPC over HTTPS; `um/*` + `v001/tpe/*` APIs; 43 banks;
  KYC-tiered limits; WebView allow-list (`*.zalopay.vn` …); deep-link router.
- **Ecosystem:** 373 mini-apps (316 on `social.zalopay.vn`); React Native
  (`main.jsbundle` 3.6 MB, 5 envs, Sentry); Alipay Griver AppX bridges;
  `ZaloPayJSBridge` WebView bridge.
- **Security:** RSA-2048 config signing, SQLCipher, Keystore/biometrics, SmartOTP
  (X25519), obfuscation, no RN source-map. Risks: broad bridges, tracking SDKs,
  wide permissions — detailed in the report.

## 4. Discussion

The wallet is a hybrid super-app: native Kotlin/Compose shell + React Native
transaction history + Alipay Griver mini-programs + 373 web mini-apps. On-device ML
covers the full eKYC loop (ID OCR, face/liveness, NFC chip, VNeID). The static
evidence suggests defense-in-depth, but bridge/origin enforcement and tracking
consent require dynamic verification beyond this study's scope.

## 5. Limitations & Ethics

Static-only; no decompilation to Java, no dynamic/API testing. We do not bypass
payment or security controls and do not redistribute copyrighted binaries.
Extracted contents are git-ignored; only derived metadata is published.

## 6. Reproducibility

```bash
python3 tools/analyze_apk.py --zip "Zalopay-Thanh+toán+&+Tài+chính_11.17.0_APKPure.zip"
cd video && npm install && npm run build
```

## References

- Morluto et al., *rea: Reverse engineer anything with agents*, GitHub, 2024–2026.
  https://github.com/morluto/rea
- Skysan, *jadx: Dex to Java decompiler*, GitHub. https://github.com/skylot/jadx
- iBotPeaches, *Apktool*, GitHub. https://github.com/iBotPeaches/Apktool
- Android Developers, *App bundles & XAPK*, developer.android.com.
- Google, *ML Kit*, developers.google.com/ml-kit.
- Alipay, *Griver / Nebula mini-program container*, opendocs.alipay.com.
- Rive, *Rive runtime*, rive.app.

## Citation

See `CITATION.cff` and `docs/references.bib`. If you cite this work, please cite
both the paper and the video (MP4) with its SHA-256 in `video/out/`.
