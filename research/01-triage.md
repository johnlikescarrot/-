# P1 Triage — Zalopay 11.17.0 (vn.com.vng.zalopay)

## Artifact identity
| Item | Value |
|---|---|
| Outer ZIP | `Zalopay-Thanh+toán+&+Tài+chính_11.17.0_APKPure.zip` — sha256 `34b9b6b6…4610a` |
| XAPK | sha256 `686e9459…ea7b8d`, xapk_version 2 |
| Package | `vn.com.vng.zalopay`, versionName `11.17.0`, versionCode `1102758` |
| SDK | min 26 (Android 8.0), target/compile 36 (Android 16) |
| Splits | base (79.4 MB) + `config.armeabi_v7a` (24.8 MB, native libs) + `config.en`/`config.vi` (locales) + `config.xhdpi` (density) |
| Base APK | 6,162 entries; 19 DEX files; 0 native libs (all in ABI split) |
| Signing | Base APK has **no META-INF JAR signatures** (APKPure repack); splits carry `BNDLTOOL` certs. Treat as repackaged artifact — do not trust signature chain. |

## DEX scale
- **110,677 classes, 351,248 methods, 374,276 strings** across 19 DEX files (largest: `classes2.dex` 16.5 MB).
- Top packages: `com/google` (11,780), `org/bouncycastle` (9,177 — crypto), `com/alibaba` (7,333 — Alipay/Griver), `com/iap`, `com/visa`, `androidx/media3`, `androidx/camera`, `vn/com` (1,616 — first-party), `androidx/compose`, `vn/ai` (1,114 — TrueID eKYC), `com/facebook`, `com/alipay`, `com/vnpay`, `com/appsflyer`, `com/nimbusds` (JOSE/JWT), `org/jmrtd` (eMRTD passport reading).

## Components (AndroidManifest.xml, 608 elements)
- Activities 200 (14 exported), activity-alias 2 (2 exported), services 34 (6 exported), receivers 22 (8 exported), providers 10 (0 exported). **30 exported components total** — see `components.json`.
- Notable exported: `SplashScreenActivityV2`, `SplashScreenGooglePaymentActivity`, `VerificationByVNeIDActivity`, `QRReceiverActivity`, `IdentityCardCaptureActivity`, `EkycActivity` (FPT), `LivenessCameraXActivity` (TrueID), `LivenessVerificationActivity`, Zalo/ThirdParty app aliases, app-widget receivers, `FirebaseInstanceIdReceiver`.
- 49 permissions incl. `QUERY_ALL_PACKAGES`, `READ_CONTACTS`+`WRITE_CONTACTS`, `CAMERA`, `RECORD_AUDIO`, `NFC`, `USE_BIOMETRIC`, `DETECT_SCREEN_CAPTURE`, `REORDER_TASKS`, `BILLING`, `AD_ID` family, `READ_PHONE_STATE`.

## Native (config.armeabi_v7a.apk, 27 .so)
Largest: `libtaibmcf.so` (12.6 MB), `libmlkitcommonpipeline.so` (6.9 MB), `libmlkit_google_ocr_pipeline.so` (6.8 MB), `libface_detector_v2_jni.so` (5.4 MB), `libfgcpkjvqe.so` (6.5 MB), `librive-android.so` (3.2 MB), `libpicodet_ncnn.so` (3.0 MB), `libsqlcipher.so` (3.6 MB — encrypted DB), `libbarhopper_v3.so` (barcode), `libVFaceLib.so`, `libtrueidlib.so`, `libsecurity_lite.so`.
Obfuscated names (`liba97d`, `libljsfrcwmw`, `liblytcuhe`, `libohxbee`, `librcmefqonnx`, `libfgcpkjvqe`, `libtaibmcf`) indicate deliberate anti-analysis naming; `fgcpkjvqe` also appears as `assets/fgcpkjvqe.dat` (776 KB).

## Assets (124 files)
- ML: TFLite object labeler/localizer, barcode models, OCR pipeline models (`mlkit-google-ocr-models`), `FastestDet.ncnn` face detection, `models_bundled` (FSSD, BlazeFace, contours), `lbfmodel.yaml` (3.8 MB), Haar cascade XML.
- Miniapp: `assets/Griver/*.amr` (Alipay Griver runtime), `GriverBridge/workerjs.js`, `assets/config/miniapp_config.json` (884 KB), `zalopay_config.json`.
- Embedded: `assets/audience_network.dex` (4.9 MB — Facebook Audience Network), `assets/android.1.zip` (3.9 MB), `assets/fgcpkjvqe.dat`.
- KYC UX: `res/raw/img_guide_front/back_id_card.json` (Lottie), `required_kyc.svg`, fonts (`sf_pro`, `notosans`, `zalopay.ttf`).

## Network (DEX string mining, 443 unique URLs)
First-party hosts: `api.zalopay.vn`, `sapi.zalopay.vn`, `openapi.zalopay.vn`, `social.zalopay.vn`, `simg.zalopay.com.vn`, `upload-img.zalopay.vn`, `uudai.zalopay.vn`, `bank-profile.zalopay.vn`, `mmf-redemption.zalopay.vn`, `scard.zalopay.vn`, `user-life-cycle-public.zalopay.vn`, `mnap-director.zalopay.vn`, `zalopay.vn` (terms/privacy).
Third-party: Alipay (`mobilegw.alipay.com`, `openauth.alipay.com`, `render.alipay.com`), Firebase/GA (`app-measurement.com`), Play Store links. Full list: `urls.json`; all strings: `dex-strings.txt` (243,782 unique).

## Evidence & limitations
- Evidence: `research/*.json`, `dex-strings.txt`, `triage.log`. All derived locally from the artifact.
- Limitations: AXML parser is minimal (no resource-ID resolution); DEX parse is header-level (no code disassembly yet); repackaged artifact — signature analysis limited to noting absence of JAR signatures.
