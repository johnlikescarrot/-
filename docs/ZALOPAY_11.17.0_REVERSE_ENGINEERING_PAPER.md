# Anatomy of a Tier-1 Southeast Asian Fintech Super-App: Comprehensive Static Reverse Engineering of ZaloPay v11.17.0

**Target Artifact:** `Zalopay-Thanh+toán+&+Tài+chính_11.17.0_APKPure.zip` (`98,813,878` bytes, SHA-256: `34b9b6b6735f0f739617f97a259f931d17cdbacb51871c40b066de41f4b4610a`)  
**Package Identity:** `vn.com.vng.zalopay` · Version `11.17.0` (`versionCode: 1102758`) · `minSdk: 26` (Android 8.0) · `targetSdk: 36` (Android 16)  
**Analysis Toolchain:** [REA (`morluto/rea` v6.10.1)](https://github.com/morluto/rea), Androguard 4.1.4, LIEF 1.0.0, PyELFtools 0.33, Capstone 5.0.9, ASN1Crypto 1.5.1  
**Date:** 2026-11-25

---

## Abstract

Mobile financial super-applications must simultaneously balance regulatory cryptographic assurance, multi-engine extensibility, sub-second edge artificial intelligence (eKYC), and strict tamper resistance while remaining compact under Android App Bundle (AAB) split delivery. Through exhaustive, reproducible static reverse engineering of **all 7 members** inside the `Zalopay-Thanh+toán+&+Tài+chính_11.17.0_APKPure.zip` distribution—spanning **5 split APKs**, **20 Dalvik Executable (DEX) files** (`60,671` primary classes, `264,525` defined methods, `8,711,522` Dalvik VM instructions plus a `4.86 MB` dynamic secondary DEX), **27 ARMv7 native ELF shared libraries** (`57.45 MB` uncompressed), **124 embedded assets** (`30.01 MB`), **56 raw resources**, and **4 `resources.arsc` localization/density tables**—we expose the internal engineering morphology of ZaloPay v11.17.0 (`targetSdk=36`).

Our investigation establishes five primary empirical findings:
1. **Tri-Engine Hybrid UI & Execution Architecture:** ZaloPay couples native Kotlin 2.2.10 + Jetpack Compose 1.7.2 (`504` Compose classes) with an embedded **React Native Metro bundle** (`assets/android.1.zip` → `3.61 MB` `main.jsbundle`, `2,346` `__d()` modules) and Ant Group's **Alipay+ Griver/Ariver mini-program engine** (`5,136` DEX classes, `5` isolated `:lite1`–`:lite5` worker processes, and pre-packaged `.amr` TAR+CERT containers).
2. **Multi-Layered Obfuscation & Isolated C++ Runtime Twins:** While R8 obfuscates `68.19%` of DEX leaf class names and `48.14%` of root package namespaces, the native ARMv7 layer pairs named vendor engines with randomized-name native protection modules (`libtaibmcf.so` at `12.62 MB` with `132` `.init_array` entries, `libfgcpkjvqe.so` at `6.52 MB` with `36` constructors paired to an `H = 7.9998` bits/byte `fgcpkjvqe.dat` payload, `liba97d.so`, `libljsfrcwmw.so`, `libohxbee.so`), plus two identical-size (`178,628` byte) private C++ runtime twins (`liblytcuhe.so` and `librcmefqonnx.so`) that isolate the VFace and Griver native stacks from `libc++_shared.so`.
3. **Asymmetric Binary Hardening Across First-Party vs. Telemetry Native Libraries:** Using REA's `inspect-binary-hardening`, `27/27` ARMv7 ELF libraries enforce non-executable stacks (`NX`), `25/27` enforce stack canaries, and `23/27` enforce `RELRO`. Every single `RELRO: disabled` exception (`4/27`) belongs exclusively to Firebase Crashlytics NDK libraries.
4. **Embedded Edge-AI & Contactless Payment Hardware Integration:** ZaloPay ships four distinct on-device vision pipelines (**Tencent NCNN** `FastestDet` `172`-layer CNN, **TensorFlow Lite** `object_labeler.tflite`, **Google ML Kit** OCR/Face/Barhopper native pipelines, and **OpenCV** Haar cascades + `VFaceLib`) alongside **Visa Cloud-Based Payments (CBP)** and EMVCo NFC Host Card Emulation (`2PAY.SYS.DDF01` and Visa AID filters) plus **X25519 SmartOTP cryptographic vault** bindings (`libtrueidlib.so`).
5. **Declarative Transport vs. Programmatic Trust Boundary Divergence:** While `allowBackup="false"`, `debuggable="false"`, `0` exported `ContentProvider`s, and APK Signature Scheme v3 + Google Play SourceStamp demonstrate rigorous baseline posture, `res/xml/network_security_config.xml` declares `<base-config cleartextTrafficPermitted="true">` with system trust anchors—shifting all TLS certificate pinning enforcement into programmatic OkHttp `CertificatePinner` and native cryptographic enclaves.

> **Ethical & Responsible Research Statement:** All findings in this study derive strictly from non-executing static inspection of metadata, ELF headers, bytecode statistics, manifest declarations, and architectural schemas. Proprietary source dumps, raw API credentials/keys, and exploit or bypass payloads are intentionally excluded.

---

## 1. Methodology & Tooling Pipeline

To guarantee artifact-bound verifiability and avoid heuristic guesswork, our static pipeline fuses **REA (`morluto/rea` v6.10.1)** evidence contracts with specialized binary parsers:

| Analysis Layer | Primary Toolchain | Scope Covered | Evidence Artifact |
| :--- | :--- | :--- | :--- |
| Container & Cryptographic Signing | REA `inspect-apk-signature`, `asn1crypto`, `PIL` | Outer ZIP, inner XAPK, `manifest.json`, `icon.png`, all 5 APKs (v1/v2/v3/SourceStamp) | `rea_apk_signatures_and_flutter.json`, `01_bundle_and_signatures.json` |
| Framework Fingerprinting | REA `identify-flutter-build`, `analyze-javascript-application` | Flutter/Dart absence proof; 8 JS Bridge files (`5,361` AST nodes) | `rea_apk_signatures_and_flutter.json`, `rea_js_bridges.json` |
| Manifest & Policy Surface | Androguard `AXMLPrinter` (v4.1.4) | All 5 `AndroidManifest.xml`, `res/xml/*.xml`, permissions, queries, IPC surface | `02_manifest_and_attack_surface.json`, `03_resources_and_localization.json` |
| Resource & Localization Delta | Androguard `ARSCParser` | `resources.arsc` across `base`, `config.vi`, `config.en`, `config.xhdpi` | `03_resources_and_localization.json` |
| Dalvik Bytecode & SBOM | Androguard `DEX`, custom opcode profiler | All 19 primary DEX + `audience_network.dex` (`9.13M` total instructions), `141` `.version` + `69` `.properties` | `04_dex_architecture_and_sbom.json` |
| Native ARMv7 ELF Binaries | REA (`hardening`, `elf-dependencies`, `elf-notes`, `native-exports`, `native-initializers`, `dwarf-units`, `rust`, `go`, `byte-entropy`) + LIEF + PyELFtools + Capstone | All 27 `.so` ELF binaries (`57,452,124` bytes), sections, `DT_NEEDED`, JNI exports, Thumb-2/ARM32 ISA | `rea_native_elf_suite.json`, `05_native_elf_and_assets_deep.json`, `06_surgical_probes.json` |
| Embedded Assets & Models | REA `inspect-byte-entropy`, `tarfile`, `zipfile`, NCNN/TFLite parsers | All 124 `assets/*`, 3 Griver `.amr` bundles, React Native `android.1.zip`, 56 `res/raw/*` | `rea_asset_entropy.json`, `05_native_elf_and_assets_deep.json`, `06_surgical_probes.json` |

### Information-Theoretic Metric
To distinguish compiled executable instructions from encrypted/compressed containers across assets and ELF sections, we compute zero-order byte Shannon entropy:

$$H(X) = -\sum_{i=0}^{255} p(x_i) \log_2 p(x_i) \quad \text{(bits/byte)}$$

Empirical thresholds observed across ZaloPay v11.17.0:
- **Structured JSON / XML / UTF-8 text:** $H \in [4.37, 5.92]$ bits/byte
- **Compiled ARM32 / Thumb-2 `.text` sections:** $H \in [6.04, 7.33]$ bits/byte
- **Compressed archives / encrypted `.dat` / obfuscated byte containers:** $H \in [7.50, 7.9998]$ bits/byte

---

## 2. Distribution Packaging & Cryptographic Signing Parity (All 7 XAPK Members)

Extracting `Zalopay-Thanh+toán+&+Tài+chính_11.17.0_APKPure.zip` (`98,813,878` bytes) yields an XAPK v2 container (`105,608,128` bytes, SHA-256 `686e9459da646c18b53a67d57e5a23c49466da140ec1e3e9682032f453ea7b8d`) encapsulating **exactly 7 members**:

| # | XAPK Member | Compressed / Stored Bytes | Uncompressed Entries Total | Entries | Strongest Scheme | SourceStamp | SHA-256 (Prefix) |
| :-: | :--- | ---: | ---: | ---: | :--- | :--- | :--- |
| 1 | `vn.com.vng.zalopay.apk` (Base) | `79,359,087` | `136,395,610` | `6,162` | **v3** (v2+v3; **no v1**) | Google Inc. | `e38...` (verified) |
| 2 | `config.armeabi_v7a.apk` (ABI) | `24,828,408` | `57,459,525` | `32` | **v3** (v1+v2+v3) | Google Inc. | `456aa08f4fa2e3f9...` |
| 3 | `config.xhdpi.apk` (Density) | `1,232,563` | `1,329,300` | `258` | **v3** (v1+v2+v3) | Google Inc. | verified |
| 4 | `config.vi.apk` (Locale `vi`) | `86,425` | `78,175` | `6` | **v3** (v1+v2+v3) | Google Inc. | verified |
| 5 | `config.en.apk` (Locale `en`) | `74,137` | `64,699` | `6` | **v3** (v1+v2+v3) | Google Inc. | `87699fda97e8a9be...` |
| 6 | `icon.png` (`512×512` RGBA PNG) | `23,841` | `23,841` | — | — | — | `f0b9d7d1a6144ca5...` |
| 7 | `manifest.json` (XAPK v2 Spec) | `2,710` | `2,710` | — | — | — | verified |

### Cryptographic Signing Findings (REA `inspect-apk-signature`)
- **Signer Parity Across All 5 APKs:** Every APK carries a consistent RSA signing block (`CN=Vu Manh Hieu`, serial `153EA9FD`, validity `2016-04-21` to `2041-04-15`, SHA-256 fingerprint `59dd31fdf36070a0d7f8c8cde843fac371df7f1a915c0136c9c9b55992e8cdae`) with no key rotation lineage (`v3.1: false`).
- **v1 JAR Signing Asymmetry:** Because `minSdk=26` exceeds Android 7.0 (API 24), the base APK omits legacy v1 `META-INF/*.RSA` JAR signing altogether (`v1: false`, relying exclusively on APK Signature Schemes v2 and v3), whereas the four Play Store-derived split APKs retain `META-INF/BNDLTOOL.{SF,RSA,MF}` alongside v2/v3 blocks.
- **Play Distribution Provenance:** All 5 APKs embed `stamp-cert-sha256` and an APK Signing Block **SourceStamp** issued by `O=Google Inc., OU=Android, CN=Android` (`SHA-256: 3257d599...`), confirming Google Play App Bundle (`BUNDLETOOL`) provenance (`STAMP_TYPE_DISTRIBUTION_APK`).

---

## 3. AndroidManifest.xml, IPC Topology & Transport Policy

Decoding all five binary `AndroidManifest.xml` files exposes an extensive fintech attack surface targeting **Android 16 (`targetSdk=36`, `compileSdk=36`)** while maintaining backward compatibility to **Android 8.0 (`minSdk=26`)**.

### 3.1 Application Hardening Flags & Multi-Process Sandbox
- **`android:name`:** `jwbmqnsll.xvndbxlk` — an obfuscated custom `Application` subclass invoked before user components, responsible for initializing encrypted native shield layers.
- **Security Flags:** `debuggable="false"`, `allowBackup="false"`, `fullBackupContent="false"`, `dataExtractionRules="@xml/appsflyer_data_extraction_rules"`, `largeHeap="true"`, `extractNativeLibs="true"`, `requestLegacyExternalStorage="true"`.
- **7 Auxiliary Processes:** Beyond the primary process, ZaloPay isolates execution across `:lite1`, `:lite2`, `:lite3`, `:lite4`, `:lite5` (Ant Group Griver mini-app sandbox containers), `:taibmcf` (matching `libtaibmcf.so`), and `:xcmzqsawpqoefcds`.

### 3.2 Permission & Ecosystem Visibility Taxonomy (`49` Declared Permissions)
| Category | Count | Representative Permissions |
| :--- | :---: | :--- |
| Dangerous Runtime | `14` | `CAMERA`, `RECORD_AUDIO`, `ACCESS_FINE_LOCATION`, `READ_CONTACTS`, `WRITE_CONTACTS`, `READ_CALENDAR`, `READ_PHONE_STATE`, `READ_MEDIA_AUDIO`, `POST_NOTIFICATIONS` |
| Normal System | `10` | `INTERNET`, `ACCESS_NETWORK_STATE`, `ACCESS_WIFI_STATE`, `CHANGE_WIFI_STATE`, `VIBRATE`, `FOREGROUND_SERVICE`, `RECEIVE_BOOT_COMPLETED` |
| OEM / Launcher Vendor | `9` | Samsung (`WRITE_USE_APP_FEATURE_SURVEY`, `mapsagent.READ_APP_INFO`), Huawei (`GET_COMMON_DATA`), Vivo/BBK (`com.bbk.launcher2.*`), shortcut managers |
| Special & Security | `6` | `USE_BIOMETRIC`, `USE_FINGERPRINT`, `NFC`, `QUERY_ALL_PACKAGES`, `DETECT_SCREEN_CAPTURE` (Android 14+), `MANAGE_OVERLAY_PERMISSION` |
| Privacy Sandbox / AdServices | `4` | `ACCESS_ADSERVICES_ATTRIBUTION`, `ACCESS_ADSERVICES_AD_ID`, `ACCESS_ADSERVICES_CUSTOM_AUDIENCE`, `ACCESS_ADSERVICES_TOPICS` |
| Third-Party SDKs | `4` | `com.android.vending.BILLING`, `BIND_GET_INSTALL_REFERRER_SERVICE`, `c2dm.RECEIVE`, `AD_ID` |
| First-Party Zalo Ecosystem | `2` | `com.zing.zalo.permission.ACCESS_THIRD_PARTY_APP_AUTHORIZATION`, `vn.com.vng.zalopay.DYNAMIC_RECEIVER_NOT_EXPORTED_PERMISSION` (`signature`) |

Notably, `<queries>` explicitly inspects installed Vietnamese digital identity and social/mapping ecosystems: `com.vnid` (Vietnam National e-ID VNeID), `com.zing.zalo`, `com.facebook.katana`, `com.facebook.lite`, `com.instagram.android`, `com.google.android.apps.maps`, `com.samsung.android.mapsagent`, and `com.xiaomi.mipicks`.

### 3.3 Component Census & IPC Boundary Exposure (`267` Total Components)
| Component Type | Total | Effectively Exported | Exported Without Permission | Key Security Observation |
| :--- | :---: | :---: | :---: | :--- |
| `<activity>` | `200` | `14` | `14` | Deep links (`zalopay://`, `zalopay-vnpay://`, `zalopay.api.v2://`, `https://zalopay.vn`, `go.zalopay.vn`, `onelink.zalopay.vn`) |
| `<activity-alias>` | `2` | `2` | `2` | Launcher & external entry aliases |
| `<service>` | `34` | `6` | `1` | Includes NFC HCE `apdu_service.xml` (`2PAY.SYS.DDF01`, Visa AIDs `A000000003*`) and `tiny_app_apdu_service.xml` |
| `<receiver>` | `22` | `8` | `5` | Boot, install referrer, push, and widget receivers |
| `<provider>` | `9` | **`0`** | **`0`** | `100%` of ContentProviders (incl. `file_paths.xml`, `griverfilepath.xml`) enforce `exported="false"` |

### 3.4 Transport Security Finding (`network_security_config.xml`)
Decoding `res/xml/network_security_config.xml` reveals:
```xml
<network-security-config>
  <domain-config>
    <domain includeSubdomains="true">storage.googleapis.com</domain>
  </domain-config>
  <base-config cleartextTrafficPermitted="true">
    <trust-anchors><certificates src="system"/></trust-anchors>
  </base-config>
  ...
</network-security-config>
```
**Architectural Implication:** Setting `cleartextTrafficPermitted="true"` in `<base-config>` overrides Android 9+ default cleartext blocking, likely to accommodate legacy third-party web mini-apps or local WebView bridges, while omitting declarative XML `<pin-set>` tags means **SSL/TLS pinning is enforced solely in code** (e.g., via OkHttp `CertificatePinner`, `TrueIDSSLPinningConfig`, and native libraries).

---

## 4. Resource Tables, Localization Splits & `res/raw` Ecosystem

Parsing `resources.arsc` across all four resource-bearing APKs quantifies how Play Feature/Config Splits isolate assets:
- **Base APK (`vn.com.vng.zalopay.apk`):** `1,802,300`-byte `resources.arsc` declaring **`14,680` resource keys** across `21` resource types (`3,460` IDs, `3,199` drawables, `2,100` strings, `1,826` attributes, `1,616` layouts, `782` colors, `721` styles, `688` dimens, `46` raw entries, `14` fonts).
- **Locale Splits:** `config.vi.apk` (`75,460` bytes `resources.arsc`, `496` keys / `492` resolved strings) vs. `config.en.apk` (`61,984` bytes, `370` keys / `369` resolved strings) — demonstrating `33%` higher Vietnamese string override coverage (reflecting primary domestic product modules such as VNPAY, taxi booking, and eKYC).
- **Density Split (`config.xhdpi.apk`):** `258` entries (`1.33 MB` uncompressed) housing `251` resource keys (`drawable-xhdpi-v4`, `drawable-anydpi-v21` ExoPlayer vector controls, `mipmap-xhdpi-v4`).
- **`res/raw/` (`56` files, `6.96 MB`):** Dominated by `haarcascade_frontalface_default.xml` (`1.79 MB` OpenCV cascade), high-resolution Lottie onboarding/NFC-eKYC JSONs (`img_guide_back_id_card.json` `1.45 MB`, `anim_location_profile_tab.json` `748 KB`, `img_guide_front_id_card.json` `555 KB`, `img_anim_fds_nfc_guidance_step1..3.json`), plus Rive binary animation `failure.riv` (`1,448` bytes).

---

## 5. Multi-DEX Morphology, Dalvik Bytecode & Dependency SBOM

### 5.1 19 Primary DEX + 1 Dynamic Secondary DEX Scale
ZaloPay's managed runtime compiles under **Gradle 8.14.5 + Kotlin 2.2.10** (JVM target 17) into **19 primary DEX files (`64,837,712` bytes, DEX `038`)** plus one dynamic DEX embedded in assets (`assets/audience_network.dex`, `4,864,036` bytes, DEX `035`, `3,156` classes, `420,370` instructions).

Across the **19 primary DEX files**:
- **Classes (`class_defs`):** `60,671`
- **String / Method / Field IDs:** `374,276` strings · `351,248` method refs · `304,518` field refs · `114,693` type refs
- **Defined / Executable Methods:** `264,525` defined (`244,537` with bytecode, `2,026` native JNI declarations across `405` classes)
- **Total Static Dalvik VM Instructions:** **`8,711,522`** (`9,131,892` including `audience_network.dex`), with `classes2.dex` alone (`16.52 MB`) carrying `3,055,827` instructions (`35.1%` of primary bytecode volume).

### 5.2 Quantitative R8 / ProGuard Obfuscation Profile
- **Short Leaf Class Names (`≤ 2` chars):** **`68.19%`** (`41,374 / 60,671` classes)
- **Obfuscated Root Packages (`≤ 2` chars or default package):** **`48.14%`** (`29,210 / 60,671` classes, e.g., `q9.*` `472`, `p9.*` `454`, `n9.*` `315`, `m9.*` `314`, `Va.*` `253`)
- **Short Method Names (`≤ 2` chars):** **`27.89%`** (`73,786 / 264,525` methods)

### 5.3 Dalvik Instruction Family Distribution (`8,711,522` Instructions)
| Instruction Family | Instruction Count | Share | Dominant Opcode |
| :--- | ---: | ---: | :--- |
| Method Invocations (`invoke-*`) | `1,639,273` | `18.82%` | `invoke-virtual` (`617,585`), `invoke-static` (`518,997`), `invoke-direct` (`256,609`) |
| Register Moves & Results (`move*`) | `1,392,103` | `15.98%` | `move-result-object` (`581,994`), `move-result` (`338,758`) |
| Arithmetic & Bitwise Logic | `1,145,705` | `13.15%` | `or-int/2addr` (`166,377`) |
| Constant Loads (`const*`) | `1,136,005` | `13.04%` | `const/4` (`383,860`), `const/16` (`286,220`), `const-string` (`206,869`) |
| Instance Field Access (`iget`/`iput`) | `694,167` | `7.97%` | `iget-object` (`354,267`) |
| Type Checks & Instantiation | `649,873` | `7.46%` | `check-cast` (`261,823`), `new-instance` (`187,004`) |
| Conditional Branches (`if-*`) | `498,766` | `5.73%` | `if-eqz` (`202,512`) |
| Returns, Conversions, Jumps, Arrays, Statics, Sync | `1,555,630` | `17.85%` | `goto` (`200,828`) |

### 5.4 Software Bill of Materials (141 `META-INF/*.version` + 69 `*.properties` + 16 Services)
- **Modern Android Stack:** Jetpack Compose `1.7.2`, Navigation `2.8.3`, Lifecycle `2.8.7`, CameraX `1.4.0`, Room `2.8.4`, Biometric `1.2.0-alpha05`, Security-Crypto `1.1.0`, WebKit `1.11.0-alpha02`, Coroutines `1.8.1`, Play Billing `8.3.0`, Play Integrity `1.3.0`, SafetyNet `18.0.1`, Play Wallet `19.4.0`.
- **Major Un-obfuscated Ecosystem Namespaces (DEX Classes):** `com/google/android` (`6,465`), `com/alibaba/ariver` (`2,415`) + `com/alibaba/griver` (`2,228`) + `com/iap/ac` (`1,330`), BouncyCastle PQC/JCA (`4,900+` combined across `jcajce`/`pqc`/`crypto`/`asn1`), `com/visa/cbp` (`900`) + `com/visa/visapaysdk` (`337`), `vn/com/vng` (`954`), `vn/ai/trueid` (`622`), `androidx/media3/exoplayer` (`538`), `com/vnpay/vntaxi` (`377`), `com/appsflyer/internal` (`364`), `com/nimbusds/jose` (`360`).

---

## 6. Tri-Engine Hybrid Runtimes & Encrypted Asset Ecosystem (`124` Assets, `30.01 MB`)

1. **Embedded React Native Engine (`assets/android.1.zip`):** A `3.91 MB` bundle containing `326` entries: `main.jsbundle` (`3,613,478` bytes, **`2,346` Metro `__d()` modules**) plus Metro-compiled image assets for transaction history v2 (`transactionhistoryv2_*`), clearing accounts (`clearingaccount_*`), online banking registration, and `zalopaycomponents`.
2. **Alipay+ / Griver Mini-Program Runtime (`assets/Griver/`, `GriverBridge/`):**
   - REA `analyze-javascript-application` parsed all `8` bridge scripts (`33,802` bytes, `5,361` AST nodes, `0` parse failures).
   - Three pre-packaged `.amr` containers (`66666692_1.24.8.amr`, `66666693_2.0.7.amr`, `68687209_2.9.7.8.amr`, entropy `7.98–7.999`) wrap ZIP → signed `CERT.json` + `Manifest.xml` + inner `.tar` containing `appx-ng/af-appx.min.js`, `af-appx.worker.min.js`, and `security-patch.min.js`.
3. **Edge-AI Vision & eKYC Models:**
   - **Tencent NCNN:** `assets/FastestDet.ncnn.param` (`172` layers, `198` blobs: `52` Convolution, `14` DepthWise Conv, `18` HardSwish, `9` HardSigmoid) + `FastestDet.ncnn.bin` (`2.42 MB`, $H = 7.5305$).
   - **TensorFlow Lite:** `assets/custom_models/object_labeler.tflite` (`3.77 MB`, `TFL3` FlatBuffer magic, $H = 5.9658$) + `tflite_langid.tflite` (`315 KB`, $H = 7.8427$).
4. **High-Entropy Encrypted / Obfuscated Payloads (`29` assets with $H \ge 7.5$):**
   - `assets/fgcpkjvqe.dat` (`776,320` B, $H = 7.9998$) & `assets/taibmcf.dat` (`8,307` B, $H = 7.9756$) — companion data payloads for native shields.
   - `assets/bshield.dat` (`929` B, $H = 7.7848$, Protobuf wire-prefix `08 25 10 a4 0d...`).
   - `assets/com/visa/cbp/*` (`4` files, `371 KB`, $H \approx 7.98–7.999$) & `assets/com/appsflyer/internal/*` (`4` files, `31.9 KB`, $H \approx 7.94–7.98$) — each prefixed with literal `java/lang/Object` or `java/lang/String` ASCII signatures before encrypted class/data bytes.
   - `assets/trueidlib.so` (`392` B, $H = 7.5008$) — anomalous stub/seed blob in `assets/` distinct from the real `79.9 KB` ARMv7 ELF `libtrueidlib.so`.
   - `assets/dabc8a718ce8084d304bab46923b374602a37c02804a1938f5200c73dcc9b2be/9f979174-...` (`17,888` B, $H = 7.9895$) — encrypted configuration/policy blob.

---

## 7. Native ARMv7 ELF Architecture & Security Posture (`27` Libraries, `57.45 MB`)

Combining REA's native ELF inspection suite with LIEF, PyELFtools, and Capstone disassembly reveals four distinct functional clusters across `config.armeabi_v7a.apk`:

### 7.1 Obfuscated Shield, Enclave & Runtime-Isolation Libraries
| Library | Size (Bytes) | Overall $H$ | `.text` ($H$) | `.rodata` ($H$) | `.init_array` | Exports / JNI | Architectural Role & Provenance |
| :--- | ---: | :---: | ---: | ---: | :---: | :---: | :--- |
| `libtaibmcf.so` | `12,617,752` | `6.124` | `11.90 MB` (`6.04`) | `211.7 KB` (`7.27`) | `132` | `2` (`JNI_OnLoad/Unload`) | Largest binary; massive compiled protection/control-flow-bloated ARM/Thumb enclave linked to `:taibmcf` & `assets/taibmcf.dat`; string markers for X25519/Visa |
| `libfgcpkjvqe.so` | `6,524,160` | `6.266` | `5.82 MB` (`6.14`) | `206.3 KB` (`7.28`) | `36` | `2` (`JNI_OnLoad/Unload`) | Companion protection/crypto enclave loaded by `classes.dex`; pairs with `assets/fgcpkjvqe.dat` ($H = 7.9998$) |
| `liba97d.so` | `997,572` | `6.221` | `878.6 KB` (`6.34`) | `1.5 KB` (`6.93`) | `2` | `64` (`JNI_OnLoad` + AEABI) | Static-linked `libstdc++.so` protection/obfuscated native bridge |
| `libljsfrcwmw.so` | `600,296` | `6.433` | `222.2 KB` (`6.38`) | `347.3 KB` (`5.90`) | `2` | `1` (`JNI_OnLoad`) | Griver-linked native guard (`DT_NEEDED`: `librcmefqonnx.so`) |
| `libohxbee.so` | `155,412` | `6.246` | `57.0 KB` (`6.26`) | `87.8 KB` (`5.60`) | `2` | `1` (`JNI_OnLoad`) | VFace/OpenCV-linked native guard (`DT_NEEDED`: `liblytcuhe.so`) |
| `liblytcuhe.so` | `178,628` | `6.629` | `119.8 KB` (`6.83`) | `9.4 KB` (`5.29`) | `2` | `94` (`303` dyn) | Private isolated C++ runtime twin #1 (differs from twin #2 by only `8,799` bytes) |
| `librcmefqonnx.so` | `178,628` | `6.630` | `119.8 KB` (`6.83`) | `9.4 KB` (`5.29`) | `2` | `94` (`303` dyn) | Private isolated C++ runtime twin #2 serving `libljsfrcwmw.so` |
| `libsecurity_lite.so` | `154,840` | `7.303` | `120.1 KB` (`7.33`) | `11.8 KB` (**`7.91`**) | `2` | `63` (`4` JNI) | Alipay+ `SecurityGuardLiteNative` (`init`, `encrypt`, `decrypt`, `signRequest`); unusually high `.rodata` entropy (`7.91`) |
| `libtrueidlib.so` | `79,904` | `6.949` | `62.7 KB` (`7.04`) | `4.8 KB` (`6.17`) | `2` | `40` (`11` JNI) | TrueID SmartOTP X25519 vault ceremony (`vaultX25519Pubkey`, `vaultAuthCeremony`, `vaultComputeQReg`, `hmac_sha256`) + anti-hooking probes (`frida`, `magisk`, `ro.debuggable`, `/proc/self/maps`) |

### 7.2 Vision, Graphics, Storage & Telemetry Native Clusters
- **Computer Vision & ML (`27.34 MB` combined):** `libmlkitcommonpipeline.so` (`6.93 MB`, `205` inits, `38` JNI), `libmlkit_google_ocr_pipeline.so` (`6.78 MB`, `296` inits, `33` JNI), `libface_detector_v2_jni.so` (`5.39 MB`, `62` inits), `libbarhopper_v3.so` (`3.24 MB`, `20` inits), `libpicodet_ncnn.so` (`2.99 MB`, `2,152` exports, OpenMP+NCNN), `libVFaceLib.so` (`1.96 MB`, OpenCV `VFaceLib` alignment).
- **Encrypted Storage & Vector Motion:** `libsqlcipher.so` (`3.56 MB`, `426` exports, `1` sampled `SVC` syscall, SQLCipher/OpenSSL), `librive-android.so` (`3.23 MB`, `5,390` exports, embeds Rive + HarfBuzz + `miniaudio` FLAC + GLESv3).
- **Crash Reporting (`625 KB`):** `libcrashlytics-common.so`, `libcrashlytics.so`, `libcrashlytics-handler.so`, `libcrashlytics-trampoline.so`.
- **Image Pipeline & AndroidX:** `libc++_shared.so` (`873 KB`), `libnative-imagetranscoder.so`, `libimage_processing_util_jni.so`, `libnative-filters.so`, `libandroidx.graphics.path.so`, `libimagepipeline.so`.

### 7.3 Binary Hardening Scorecard Across All 27 Libraries (REA)
- **NX (Non-Executable Stack):** `27 / 27` (`100.0%`) enabled.
- **Stack Canary (`__stack_chk_fail`):** `25 / 27` (`92.6%`) enabled (`libc++_shared.so` and `libcrashlytics-trampoline.so` report `unknown`).
- **RELRO (`PT_GNU_RELRO`):** `23 / 27` (`85.2%`) enabled — **all 4 exceptions (`libcrashlytics*.so`) have `RELRO: disabled`**.
- **Stripped Symbols:** `26 / 27` (`96.3%`) stripped (only `libcrashlytics-trampoline.so` retains debug/unstripped symbols).
- **FORTIFY_SOURCE:** `15 / 27` (`55.6%`) confirmed (`*_chk` imports), `12 / 27` `unknown` (absence of fortified call sites in small or custom wrappers).
- **RPATH / RUNPATH:** `27 / 27` (`100.0%`) cleanly disabled.

---

## 8. Synthesis, Limitations & Future Research Directions

### 8.1 Architectural Synthesis
ZaloPay v11.17.0 exemplifies the convergence of **three UI paradigms** (Jetpack Compose, React Native Metro, Alipay+ Griver), **four on-device AI/vision runtimes** (NCNN, TFLite, ML Kit, OpenCV), **two contactless/cryptographic hardware stacks** (Visa CBP NFC HCE + TrueID X25519 SmartOTP), and **multi-tier binary defense** (R8 naming collapse + isolated C++ runtime twins + multi-megabyte native protection enclaves + SQLCipher).

### 8.2 Methodological Limitations
Consistent with REA's evidence contract (`authority: "shipped-artifact"`, `runtime_execution: "not-performed"`):
1. **Static Boundary:** No APK code, Dalvik method, or ARM native constructor was executed on hardware or an emulator; dynamic unpacking of `assets/fgcpkjvqe.dat`, `taibmcf.dat`, or runtime-decrypted Visa/AppsFlyer assets is intentionally out of scope.
2. **Signature Semantics:** REA's APK signature checks verify byte-level structural self-consistency and declared X.509 metadata (`CN=Vu Manh Hieu`, Google Play SourceStamp) without performing external PKI revocation lookups.
3. **Architecture Scope:** The analyzed APKPure XAPK bundles the `armeabi-v7a` (32-bit ARM/Thumb-2) native ABI split; `arm64-v8a` builds may exhibit distinct AArch64 hardening flags (e.g., BTI/PAC).

---

## References
See [`docs/references.bib`](./references.bib) for complete BibTeX entries covering REA (`morluto/rea`), Androguard, LIEF, Capstone, Android APK Signing, mobile super-app mini-program security (Lu et al., ACM CCS 2020), Android TLS security (Fahl et al., ACM CCS 2012), and EMVCo HCE specifications.
