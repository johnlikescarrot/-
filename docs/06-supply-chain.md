# 06 — Supply chain and SDK inventory

## 1. Method

Fingerprints combine three independent channels so that a single rename cannot
hide a dependency:

1. **DEX package prefixes** — reliable for vendor SDKs, which are rarely renamed
   (28 of 62 matches).
2. **Native library names** — catches SDKs whose Java layer is thin (ML Kit,
   SQLCipher, Rive, MediaPipe).
3. **Asset paths** — catches container-style SDKs (mPaaS `assets/Griver/`,
   `assets/mlkit-google-ocr-models/`, `assets/com/visa/cbp/`).

Thirty fingerprints over 17 vendors produced 62 matches. The full table is
generated in `docs/generated/supply-chain.md`.

## 2. Composition by category

| Category | Representatives | Scale |
|---|---|---|
| Super-app / mini-program | Alibaba mPaaS Ariver + Griver, `com.alipay.plus` | 4,821 classes, 8 containers |
| Cross-border payments | IAP Connect (Ant), Visa CBP + Visa Payment SDK, Tenpay Global endpoints | 2,969 classes |
| Ads | Meta Audience Network (`audience_network.dex`), Google Mobile Ads, IMA | 3,156 (separate DEX) + 158 refs |
| Analytics / attribution | AppsFlyer, Firebase (Crashlytics, Remote Config, FCM), Google Tag Manager | 57 + 364 classes |
| Crypto | BouncyCastle, Nimbus JOSE+JWT, Google Tink, SQLCipher | 6,112 + 360 + 9 |
| Vision / ML | ML Kit OCR+barcode, MediaPipe face, NCNN PicoDet, VNG TrueID, FPT eKYC | 14 `.tflite`, 2 `.ncnn`, 21 OCR model files |
| UI runtime | Compose, CameraX, Media3/ExoPlayer, Lottie, Rive, Glide, Glance | 504 + 813 + 1,198 |
| Security | Alipay SecurityGuard Lite, VNG BShield, Play Integrity, SafetyNet | 5 native libs + 17 classes |

## 3. Observations

**Three payment rails in one client.** IAP Connect (Alipay/Ant), Visa CBP and the
Tenpay Global endpoints implement three different cross-border acquiring
integrations. Each brings its own crypto stack, its own HTTP client configuration
and its own certificate handling. A pinning or TLS policy gap in *one* of them is
invisible to the others, and this is where the cleartext-permitted base config
(F4) becomes relevant.

**Ads in a wallet.** Meta Audience Network ships a 4.86 MB *separate DEX* in
`assets/` (3,156 classes, 22,955 methods) plus `AD_ID` and the four
`ACCESS_ADSERVICES_*` permissions. Ads are not necessarily illegitimate — wallets
monetise loyalty placements — but the ad SDK is 6 % of all classes and is the
single largest component that has no business touching payment flows. Its DEX is
loaded from assets, so it is also invisible to scanners that only inspect
`classes*.dex`.

**Duplicate stacks.** Two OCR pipelines (ML Kit `gocr`, PaddleOCR PicoDet), two
face stacks (MediaPipe face detector, VNG `VFaceLib`/TrueID, FPT eKYC), two
liveness surfaces and two NFC HCE services coexist. This is the normal residue of
A/B vendor migration; it also means the effective attack surface is the *union*
of the two, and the review burden is double.

**BouncyCastle as transitive ballast.** 6,112 classes including `pqc`, `oer`,
`cms`, `pkix` — far more than any first-party need. This is the classic
"dependency of a dependency" amplification: auditors must review a large crypto
library to reason about a handful of call sites.

**Legacy React Native.** `assets/android.1.zip` holds a 2022-era JS bundle that
no runtime can execute in this build (F7). It contributes 3.9 MB and an obsolete
JS toolchain to the SBOM while providing no functionality.

## 4. Licences and obligations

Not extracted here (the corpus contains `META-INF/*.version` markers for 141
AndroidX artifacts and `LICENSE*.txt` files, but no reliable SPDX mapping).
Recommended follow-up: run a licence scanner over `META-INF/` and
`assets/*.zip` manifests, because mPaaS and the Visa SDK both carry
redistribution terms that differ from Apache-2.0.

## 5. SBOM shape

`analysis/report.json → supplyChain` is already CycloneDX-compatible in spirit:
`{name, vendor, category, evidence, value, classes}`. A small adapter would emit
`components[]` with `bom-ref` and `evidence.identity`, which makes this teardown
usable in a CI gate ("fail if a new SDK appears between version N and N+1").
That adapter is the natural next contribution.
