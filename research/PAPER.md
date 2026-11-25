# Anatomy of a Super-App Mobile Wallet: Comprehensive Evidence-Bounded Static Reverse Engineering of ZaloPay 11.17.0

**Authors:** Mobile Systems & Binary Telemetry Group (Arena Research Collective)  
**Target Artifact:** `Zalopay-Thanh+toán+&+Tài+chính_11.17.0_APKPure.zip` (`vn.com.vng.zalopay`, `versionCode 1102758`, `versionName 11.17.0`)  
**Primary Analysis Engine:** [`morluto/rea`](https://github.com/morluto/rea) (`rea-agents@6.10.1`) + Deterministic Python Bytecode/ELF Telemetry (`androguard 4.1.4`, `LIEF 1.0.0`, `pyelftools`, `cryptography`)  
**Scope & Ethics:** Strictly non-executing, evidence-bounded static architectural reverse engineering. Zero runtime instrumentation, zero anti-tamper bypasses, zero secret extraction, and zero exploit generation.

---

## Abstract

Modern financial "super-apps" integrate regulated payment rails, cryptographic tokenization, biometric identity verification (eKYC), third-party mini-program runtimes, and native runtime application self-protection (RASP) inside a single mobile distribution bundle. Yet empirical, whole-corpus architectural studies covering **100% of shipped files** across split-APK bundles remain scarce because prior literature focuses selectively on Dalvik bytecode or isolated native libraries.

We present a comprehensive, evidence-bounded static reverse-engineering study of **all 6,832 indexed files** across the nested packaging hierarchy of **ZaloPay 11.17.0** (`Zalopay-Thanh+toán+&+Tài+chính_11.17.0_APKPure.zip`, `98,813,878` bytes compressed; `195,327,309` bytes uncompressed across 5 APKs). Combining the cryptographic provenance pipeline of [`morluto/rea`](https://github.com/morluto/rea) (`rea-agents@6.10.1`) with cross-layer Dalvik/ELF/ARSC/ML telemetry, we establish five generalizable empirical findings:

1. **Split-APK Signing & Resource-Fusion Asymmetry:** Although the XAPK v2 container carries a Google Play Source Stamp (`O=Google Inc.`) and uniform RSA-2048 signer (`CN=Vu Manh Hieu`), the base APK (`vn.com.vng.zalopay.apk`) omits APK Signature Scheme v1 (`v1=false, v2=true, v3=true`) while all four config splits retain `v1=true` alongside `v2`/`v3`. Furthermore, base `resources.arsc` fuses **13 distinct package namespaces** (`16,475` resource keys: 1 base + 12 Dynamic Feature Modules), mirrored directly by 12 small, **100.0%-obfuscated DEX feature shards** (`classes8.dex`–`classes19.dex`).
2. **Compiler Toolchain & Hardening Heterogeneity:** Across 27 ARM32 (`armeabi-v7a`) ELF shared objects (`57,452,124` bytes), `morluto/rea` reveals near-universal stack canaries (`25/27`) and non-executable stacks (`27/27`), yet **4/4 Firebase Crashlytics binaries omit `RELRO`** (`libcrashlytics-trampoline.so` additionally ships as an unstripped `ET_DYN` PIE binary with DWARF `.debug_*` sections). Meanwhile, Alipay's `libsecurity_lite.so` embeds a proprietary `Alipay clang version 7.0.1` tag, a custom `.llvm` section, and the corpus's highest `.rodata` Shannon entropy (`7.909` bits/byte).
3. **Dual Native Entropy Regimes & Private Runtime Shims:** Quantitative section profiling separates standard LLVM C++ binaries (`.text` `7.12–7.24 > .rodata` `5.41–6.19`) from 7 randomized-name native shield modules (`libtaibmcf.so`, `libfgcpkjvqe.so`, `liba97d.so`, `libljsfrcwmw.so`, `liblytcuhe.so`, `libohxbee.so`, `librcmefqonnx.so`), which exhibit suppressed `.text` entropy (`6.04–6.38`) alongside elevated `.rodata` entropy (`6.94–7.28`) and high-entropy `.dat` companions (`assets/fgcpkjvqe.dat` at `7.9998` bits/byte). Crucially, `liblytcuhe.so` and `librcmefqonnx.so` are **95.074% byte-identical `178,628`-byte private C++ ABI shims** (303 exports each) linked exclusively via `DT_NEEDED` by `libohxbee.so` and `libljsfrcwmw.so`.
4. **Dynamic JNI Dominance in RASP Stubs:** Across 20 DEX files (`69.70` MB, `63,827` classes, `287,480` methods), **`89.3%` (`1,810 / 2,026`) of `native` methods bind dynamically via `JNI_OnLoad`/`RegisterNatives`**, led by `375` RASP/shield classes (`1,351` native methods = `66.7%` of all native declarations), including manifest entry class `jwbmqnsll.xvndbxlk` (implementing `DisplayManager.DisplayListener`) and `com.xq.cxzmoiwuegvp` (`1,060` native methods).
5. **Super-App Software Archaeology & Tri-Engine On-Device AI:** We uncover **four concurrent UI/runtime strata**, including a **3.91 MB dormant React Native legacy bundle** (`assets/android.1.zip`, `2,346` Metro modules dated `2022-03-08`) with **zero references** across all 20 DEX string pools and zero RN `.so` libraries; an active **Alipay+/Griver mini-program engine** cataloging **373 mini-apps** across 19 categories backed by 3 signed `.amr`/`.tar` runtimes; and **18.19 MB of on-device vision/ML models** spanning 14 TensorFlow Lite FlatBuffers, NCNN `FastestDet` (172 layers), and OpenCV Haar/LBF cascades paired with ICAO 9303 chip-ID NFC (`org.jmrtd`) and Visa CBP HCE (`assets/com/visa/cbp/`).

---

## 1. Introduction & Research Motivation

Mobile payment applications in high-growth digital economies evolve rapidly from single-purpose wallets into multi-sided super-apps. Over multi-year release cycles, engineering teams layer new rendering frameworks, regulatory eKYC pipelines, partner mini-app sandboxes, and binary anti-tamper protections atop legacy subsystems. Despite their societal importance—handling identity verification, NFC chip-card cryptography, and retail payments for tens of millions of users—the internal binary composition of production super-app wallets is rarely measured at **whole-archive granularity**.

Existing mobile reverse-engineering studies typically isolate a single dimension: permission graphs, DEX call-graphs, or specific native vulnerabilities. Such narrow slicing misses **cross-layer structural correlations**: how `resources.arsc` dynamic feature tables map onto multidex shards, how compiler provenance varies across third-party SDKs within the same `lib/` folder, or how obsolete hybrid bundles persist inside production APKs long after their native runtime has been removed.

To close this gap, we conduct an exhaustive static dissection of **ZaloPay v11.17.0** (`vn.com.vng.zalopay`, `targetSdk=36` / Android 16) anchored in cryptographic provenance via `morluto/rea` (`rea-agents@6.10.1`) [1]. Our contributions are:

- **C1 — Whole-Corpus Reproducible Audit:** Every single member file (`6,832` indexed entries across 4 nested container layers: `.zip` -> `.xapk` -> 5 `.apk`s -> `android.1.zip` + 3 `.amr`/`.tar` mini-app archives) is cryptographically hashed (SHA-256, CRC32), signature-typed, and entropy-measured (`data/full_file_census.csv.gz`).
- **C2 — Cross-Layer Static Correlation:** We link AndroidManifest declarations, `resources.arsc` package tables, 20-DEX bytecode hierarchies, `2,026` `ACC_NATIVE` methods, and 27 ARM32 ELF binaries via deterministic Python parsers (`scripts/02`–`07`) and 250+ `morluto/rea` Evidence v2 inspections (`evidence/`).
- **C3 — Generalizable Empirical Signals for Binary Telemetry:** We formalize quantitative fingerprints for (a) split-APK signing asymmetry, (b) shielded vs. standard LLVM native section entropy inversion, (c) duplicated private C++ ABI shims, and (d) dormant cross-platform bundle detection.

---

## 2. Methodology & Ethical Threat-Model Boundaries

### 2.1 Strictly Non-Executing Evidence-Bounded Protocol

Every measurement in this study is derived **strictly from inert bytes on disk**:
- **No execution or dynamic instrumentation:** No emulator, Android device, Frida hook, debugger, or dynamic unpacking was executed.
- **No vulnerability exploitation or bypass:** We measure binary hardening posture (`NX`, `RELRO`, stack protector, `FORTIFY`, stripping) and RASP structural footprints as descriptive software-engineering telemetry. We deliberately exclude vulnerability hunting, PoC generation, anti-tamper bypass scripts, or secret extraction.
- **Provenance preservation:** Every `morluto/rea` inspection records a deterministic `evidence_id`, SHA-256 artifact identity, `confidence` (`observed` vs. `derived`), and explicit `limitations` in `evidence/*.json`.

### 2.2 Multi-Stage Static Pipeline

| Stage | Script / Engine | Target Layer | Primary Artifact Output |
| :--- | :--- | :--- | :--- |
| 0–1 | `scripts/01_rea_corpus_audit.mjs` (`rea-agents@6.10.1`) | Outer ZIP, XAPK, 5 APKs, 27 `.so`, JS bridges | `evidence/rea_*.json` |
| 1 | `scripts/02_full_file_census.py` | 100% of 6,832 nested archive members | `data/full_file_census.csv.gz`, `census_summary.json` |
| 2 | `scripts/03_manifest_and_resources.py` (`androguard`) | All 5 `AndroidManifest.xml` & 4 `resources.arsc` | `data/manifest_and_resources.json` |
| 3 | `scripts/04_dex_and_sdk_ecology.py` (`androguard`) | 20 DEX files, `.properties`, `META-INF/*.version`, ART profile | `data/dex_ecology.json` |
| 4 | `scripts/05_native_elf_deep_dive.py` (`LIEF`, `pyelftools`) | 27 ELF32 `.so` binaries + DEX JNI cross-reference | `data/native_elf_analysis.json` |
| 5 | `scripts/06_js_assets_and_ml_models.py` | `android.1.zip`, 3 `.amr`/`.tar`, 14 `.tflite`, NCNN, OpenCV | `data/assets_and_ml.json` |
| 6 | `scripts/07_synthesize_findings.py` | Cross-layer checksum & invariant validation | `data/synthesis_metrics.json` |

---

## 3. Container Hierarchy & 100% Whole-Corpus File Census

### 3.1 Four-Layer Packaging Topology

`Zalopay-Thanh+toán+&+Tài+chính_11.17.0_APKPure.zip` (`98,813,878` B, SHA-256 `34b9b6b6...f4b4610a`) wraps a single APKPure **XAPK v2** archive (`105,608,128` B, SHA-256 `686e9459...ea7b8d`), which in turn contains 7 direct members: `manifest.json` (`2,710` B), `icon.png` (`23,841` B), and **5 split APKs** totaling `6,464` APK member files (`195,327,309` B uncompressed; `104,477,912` B compressed inside the APKs). Two asset sub-containers—`assets/android.1.zip` (`321` files) and 3 Alipay+/Griver `.amr` packages (`39` ZIP/TAR members)—contribute an additional `360` nested files, yielding **`6,832` total indexed records** and **`6,285` unique SHA-256 digests**.

| Container Layer | Files | Uncompressed Bytes | Compressed Bytes | Primary Role |
| :--- | ---: | ---: | ---: | :--- |
| `outer_zip` | 1 | 105,608,128 | 98,813,516 | Outer ZIP transport wrapper |
| `inner_xapk` | 7 | 105,607,171 | 105,607,171 | APKPure XAPK v2 bundle (`STORED` APKs) |
| `vn.com.vng.zalopay.apk` (Base) | 6,162 | 136,395,610 | 78,345,127 | Bytecode (19 DEX), resources, ML models, assets |
| `config.armeabi_v7a.apk` (ABI Split) | 32 | 57,459,525 | 24,813,347 | 27 ARM32 native `.so` libraries + signing |
| `config.xhdpi.apk` (Density Split) | 258 | 1,329,300 | 1,178,129 | 252 `xhdpi` drawables + split ARSC |
| `config.vi.apk` (Locale Split) | 6 | 78,175 | 77,394 | Vietnamese locale `resources.arsc` (`496` keys) |
| `config.en.apk` (Locale Split) | 6 | 64,699 | 63,915 | English locale `resources.arsc` (`370` keys) |
| `assets/android.1.zip` (Nested) | 321 | 9,877,453 | 3,830,160 | Dormant React Native Metro bundle + PNGs |
| `amr:68687209_2.9.7.8.amr` (Nested) | 16 | 6,597,412 | 4,149,275 | Griver `appx-ng` 2.9.7.8 mini-program runtime |
| `amr:66666692_1.24.8.amr` (Nested) | 13 | 1,947,719 | 1,256,160 | Griver `appx` 1.24.8 mini-program runtime |
| `amr:66666693_2.0.7.amr` (Nested) | 10 | 120,727 | 73,446 | Griver error/fallback mini-app package |
| **All 5 APKs Combined** | **6,464** | **195,327,309** | **104,477,912** | **100% of direct installable split payload** |
| **Total Corpus Indexed** | **6,832** | — | — | **Complete recursive audit (`6,285` unique SHA-256)** |

### 3.2 Subsystem Composition & Redundancy Audit

Across the 5 APKs (`195.33` MB uncompressed), **Dalvik bytecode** (`33.19%`, `64.84` MB across 19 primary DEX files + `2.49%`, `4.86` MB in `assets/audience_network.dex`) and **ARM32 native `.so` libraries** (`29.41%`, `57.45` MB) together constitute **65.09%** of total app payload. On-device ML/CV models (`15.97` MB in `assets/` plus `5.62` MB OpenCV models in `res/raw/`), compiled resources (`res/` + `resources.arsc` = `40.89` MB across XML, raster, fonts, Lottie/Rive, audio, OpenCV), and BouncyCastle post-quantum lookup tables (`1.22` MB gzipped `.properties` blobs!) make up the remainder.

Content-addressable deduplication reveals **311 duplicate SHA-256 groups** totaling **`1,114,449` redundant bytes**—principally duplicated raster banners (`img_fds_nfc_guidance.png` == `img_nfc_pro_guidance.png` at `106,396` B), duplicated `org/bouncycastle/pkix/` vs. `x509/` `.properties` (`49,608` B + `42,868` B), duplicated font payload (`UnisectVnu.ttf` == `ocr-a-tribute.ttf` at `48,216` B), and repeated Jetpack `LICENSE.txt` files.

---

## 4. Signing Asymmetry, Manifest Attack Surface & Resource Fusion

### 4.1 Google Play Source Stamp & Signing Scheme Asymmetry

`rea inspect-apk-signature` establishes uniform cryptographic lineage across all five APKs while uncovering a striking scheme divergence:

- **Common Signer & Provenance:** Every APK carries RSA-2048 signatures under `CN=Vu Manh Hieu` (serial `153EA9FD`, valid `2016-04-21` to `2041-04-15`, SHA-256 fingerprint `59dd31fd...8cdae`) AND a **Google Play Source Stamp** (`O=Google Inc., OU=Android, CN=Android`, valid `2020-05-19` to `2050-05-19`), proving the APKPure XAPK was re-packaged from an authentic Google Play App Bundle distribution (`com.android.vending.derived.apk.id = 2`). All 5 APKs are `self_consistent=true`.
- **Base vs. Split Scheme Divergence:** `vn.com.vng.zalopay.apk` disables JAR v1 signing (`v1=false, v2=true, v3=true`), whereas **all 4 split APKs (`config.armeabi_v7a`, `config.xhdpi`, `config.en`, `config.vi`) retain `v1=true` alongside `v2` and `v3`**. This asymmetry reflects Play Store split-signing pipeline templates rather than Android API requirements (`minSdk=26` mandates v2 support natively).

### 4.2 Manifest Posture, Permissions & Network Security

- **SDK & Build Provenance:** `minSdkVersion=26` (Android 8.0), `targetSdkVersion=36` (Android 16), `compileSdkVersion=36`, built with **Gradle `8.14.5`**, **Kotlin `2.2.10`**, and **Java 17** (`kotlin-tooling-metadata.json`), embedding `141` Jetpack/AndroidX `META-INF/*.version` files and `69` Maven `.properties` descriptors.
- **Obfuscated Application Stub:** `<application android:name="jwbmqnsll.xvndbxlk">` routes app initialization through a RASP-hardened stub subclassing `vn.com.vng.zalopay.AndroidApplication` (`allowBackup=false`, `largeHeap=true`, `extractNativeLibs=true`, `requestLegacyExternalStorage=true`).
- **Cleartext Policy Anomaly:** Despite `targetSdk=36` (where cleartext HTTP is disabled by default since API 28), `res/xml/network_security_config.xml` explicitly configures `<base-config cleartextTrafficPermitted="true">` trusting system CAs, alongside a `<domain-config>` entry for `storage.googleapis.com` and `<debug-overrides>` trusting user certificates.
- **Permissions (`49` total) & Ecosystem Queries:** Permissions span OEM vendor extensions (`9`), contacts/calendar/storage (`7`), background/push/billing (`6`), advertising/attribution (`6`), network (`4`), payments/NFC/biometrics (`3`: `NFC`, `USE_BIOMETRIC`, `USE_FINGERPRINT`), camera/audio eKYC (`3`), anti-fraud (`2`: `DETECT_SCREEN_CAPTURE`, `MANAGE_OVERLAY_PERMISSION`), location (`2`), device identity (`2`), and ecosystem visibility (`2`: `QUERY_ALL_PACKAGES` plus `com.zing.zalo.permission.ACCESS_THIRD_PARTY_APP_AUTHORIZATION`). `<queries>` explicitly targets `com.vnid` (Vietnam's national VNeID), `com.zing.zalo`, `com.facebook.katana`/`lite`, `com.instagram.android`, `com.google.android.apps.maps`, `com.samsung.android.mapsagent`, and `com.xiaomi.mipicks`.
- **Component Topology:** `200` Activities (`14` exported), `2` Activity-Aliases (`2` exported), `34` Services (`6` exported), `22` Receivers (`8` exported), `9` Providers (`0` exported), handling 7 deep-link schemes (`zalopay`, `https`, `zalopay-1`, `zalopay.api.v2`, `zalopay-vnpay`, `fbconnect`, `tagmanager.c.vn.com.vng.zalopay`).

### 4.3 Fused Dynamic Feature Modules in `resources.arsc`

Base `resources.arsc` (`1,802,300` B) houses **`16,475` unique resource keys across `13` distinct package tables**: `vn.com.vng.zalopay` (`14,680` keys) plus **12 Dynamic Feature Module sub-packages** fused into the base artifact (`bank_binding`: 633, `login_v2`: 368, `cashier`: 326, `resetpin`: 103, `cashier_v3`: 70, `withdraw`: 58, `liveness`: 49, `changepin`: 47, `cash_in`: 46, `resultpage`: 40, `result_page_v3`: 31, `updatephone`: 24). This resource taxonomy documents live multi-generation feature coexistence (`cashier` alongside `cashier_v3`, `resultpage` alongside `result_page_v3`).

---

## 5. Multi-DEX Bytecode Ecology & R8 Obfuscation Gradients

Across all **20 DEX files** (`19` DEX `038` multidex files totaling `64,837,712` B + `assets/audience_network.dex` `035` at `4,864,036` B = **`69,701,748` bytes**), we enumerate **`63,827` classes**, **`287,480` methods**, **`286,224` fields**, **`2,026` `ACC_NATIVE` methods** across **`405` classes**, and **`5,554` unique packages**.

### 5.1 Ecosystem Breakdown

| Software Ecosystem Category | Classes | Share (%) | Methods | Native Methods |
| :--- | ---: | ---: | ---: | ---: |
| R8 Short-Identifier / Minified Packages | 27,105 | 42.47% | 88,166 | 8 |
| Google Play Services, Firebase & Google SDKs | 7,467 | 11.70% | 33,633 | 41 |
| Alipay+ / Griver Mini-Program & IAP Container | 6,913 | 10.83% | 36,554 | 33 |
| Cryptography, JOSE & Encrypted Storage (BouncyCastle / SQLCipher / Nimbus) | 6,530 | 10.23% | 36,942 | 50 |
| AndroidX / Jetpack / Compose / Media3 | 4,543 | 7.12% | 19,921 | 32 |
| Analytics, Attribution & Ad Networks (incl. `audience_network.dex`) | 3,852 | 6.04% | 26,506 | 10 |
| Payment Rails & Tokenization (Visa CBP / VNPAY / Play Billing) | 1,846 | 2.89% | 10,363 | 5 |
| First-Party ZaloPay / VNG / Zalo Ecosystem | 1,147 | 1.80% | 7,901 | 7 |
| Other Third-Party Libraries | 1,042 | 1.63% | 3,948 | 25 |
| On-Device eKYC, Vision, ML & NFC e-ID (TrueID / MLKit / JMRTD) | 986 | 1.54% | 7,574 | 283 |
| Networking, Reactive Streams & Serialization | 954 | 1.49% | 6,687 | 0 |
| Vector Animation & Rendering (Rive / Lottie / Glide) | 543 | 0.85% | 2,727 | 166 |
| Kotlin Standard Library & Coroutines | 378 | 0.59% | 3,721 | 0 |
| App Shield / RASP & Native Integrity Stubs (`com.xq.cxzmoiwuegvp`, `jwbmqnsll`, `wmoi`, `jipm`) | 375 | 0.59% | 2,304 | **1,351** |
| React Native & Fresco Media Stack (`com.facebook.fresco`/`imagepipeline` ONLY) | 134 | 0.21% | 487 | 15 |
| R8 Default-Root Package | 12 | 0.02% | 46 | 0 |

### 5.2 The 12-Shard Feature Module Obfuscation Cliff

Inspecting obfuscation per DEX file exposes an exact structural correspondence:
- `classes.dex`–`classes7.dex` hold core frameworks and third-party SDKs with R8 obfuscation ratios between **`31.45%` (`classes5.dex`) and `99.63%` (`classes3.dex`, `vn.zalopay.ui`)**.
- **`classes8.dex`–`classes19.dex`** comprise **12 small DEX shards** (`15,916` B to `1,218,908` B, `20` to `1,128` classes each, `0` native methods), **every single one exhibiting `100.0%` R8 identifier obfuscation**—corresponding 1-to-1 with the 12 Dynamic Feature Modules identified in `resources.arsc`.
- **BouncyCastle Post-Quantum & Java Cryptography:** Notably, BouncyCastle (`org.bouncycastle.*`) spans `~5,060+` classes including `org.bouncycastle.pqc` (`975` classes) paired with three gzipped LowMC tables (`lowmcL1`/`L3`/`L5.bin.properties`, `1.22` MB total) disguised under `.properties` extensions.

---

## 6. Native ARM32 ELF Audit, Hardening Disparities & JNI Correlation

Auditing all **27 ARM32 ELF32 shared objects** (`57,452,124` bytes) in `config.armeabi_v7a.apk` via 9 `morluto/rea` commands each (`243` Evidence v2 checks) plus LIEF/pyelftools yields a granular view of native compilation, hardening, and shielding.

### 6.1 Hardening Telemetry & Crashlytics Disparity

| Hardening / Structural Property | Enabled | Disabled | Unknown / N/A | Key Empirical Observations |
| :--- | :---: | :---: | :---: | :--- |
| Non-Executable Stack (`NX`) | **27** | 0 | 0 | Universal across all 27 libraries (`PT_GNU_STACK` lacks `PF_X`) |
| Full `RELRO` (`PT_GNU_RELRO` + `BIND_NOW`) | **23** | **4** | 0 | **All 4 `libcrashlytics*.so` binaries lack `RELRO`** |
| Stack Protector (`__stack_chk_fail`) | **25** | 0 | 2 | Absent only in `libc++_shared.so` and 6.9 KB `libcrashlytics-trampoline.so` |
| `FORTIFY_SOURCE` (`*_chk`) | **15** | 0 | 12 | Present in `libtaibmcf`, `libfgcpkjvqe`, `libsqlcipher`, `libtrueidlib`, `libVFaceLib`, etc. |
| Stripped (`SHT_SYMTAB` absent) | **26** | **1** | 0 | Only `libcrashlytics-trampoline.so` retains `.symtab` + DWARF `.debug_*` |
| `PIE` Executable (`ET_DYN` + `DF_1_PIE`/`PT_INTERP`) | **1** | 0 | 26 (`N/A` SO) | `libcrashlytics-trampoline.so` is an out-of-process Crashpad PIE executable (`_start`) |
| `DT_RPATH` / `DT_RUNPATH` | 0 | **27** | 0 | Clean; zero hardcoded rpath/runpath |

### 6.2 Toolchain Provenance & Custom Compiler Sections

Reading `.comment` and non-standard ELF sections across all 27 binaries exposes **five distinct build ecosystems**:
1. **Alipay Proprietary Obfuscating Clang:** `libsecurity_lite.so` (`154,840` B) reports `Alipay clang version 7.0.1` alongside a custom `.llvm` (`54` B) section, an `__obfuscator_version__` export, and the corpus's highest native `.rodata` entropy (**`7.909`** bits/byte).
2. **Google Bazel/Blaze ML Kit Pipeline:** `libbarhopper_v3.so`, `libface_detector_v2_jni.so`, `libmlkitcommonpipeline.so`, and `libmlkit_google_ocr_pipeline.so` (`22.34` MB combined) share custom Bazel sections `malloc_hook` (`284` B) and `pb_defaults` (`12` B), stripped `.comment`, and normal LLVM `.text` entropy (`6.98–7.24`).
3. **Shielded/Randomized Native Modules (7 libraries, `21.25` MB):** `libtaibmcf.so` (`12.62` MB, `clang 17.0.2`), `libfgcpkjvqe.so` (`6.52` MB, `clang 21.0.0`), `liba97d.so` (`998` KB), `libljsfrcwmw.so` (`600` KB, `clang 14.0.6`), `libohxbee.so` (`155` KB, `clang 14.0.6`), `liblytcuhe.so` (`179` KB, `clang 14.0.6`), and `librcmefqonnx.so` (`179` KB, `clang 14.0.6`).
4. **Duplicated Private C++ ABI Shims (`liblytcuhe.so` vs. `librcmefqonnx.so`):** Byte-by-byte comparison proves both `178,628`-byte libraries export identical `303` C++ runtime/ABI symbols (`_ZdaPvRKSt9nothrow_t`, `__cxa_rethrow`, etc.) and are **95.074% byte-identical** (`8,799` differing bytes from independent linking/SONAMEs). `DT_NEEDED` graphs confirm `libohxbee.so -> liblytcuhe.so` and `libljsfrcwmw.so -> librcmefqonnx.so`—private randomized shims isolating shield modules from `libc++_shared.so`.
5. **Biometric Vault & GPU Vector Runtime:** `libtrueidlib.so` exports X25519 key-agreement and memory-locking primitives (`vaultX25519Pubkey`, `vaultAuthCeremony`, `vault_mlock`, `vault_secure_zero`), while `librive-android.so` (`3.23` MB) links `libEGL.so` + `libGLESv3.so` with `5,948` exports (`167` JNI methods).

### 6.3 Two Quantitative Entropy Regimes & Dynamic JNI Dominance

Comparing section-level Shannon entropy separates native binaries cleanly:
- **Standard Compiled C++ Modules** (`libmlkit*`, `libface_detector*`, `libbarhopper_v3`, `libpicodet_ncnn`, `libVFaceLib`): `.text` entropy clusters tightly in **`7.04–7.24`** bits/byte, exceeding `.rodata` (`5.41–6.19`).
- **Shielded / Obfuscated Native Modules** (`libtaibmcf.so`, `libfgcpkjvqe.so`, `liba97d.so`, `libljsfrcwmw.so`, `libohxbee.so`): `.text` entropy drops sharply to **`6.04–6.38`** bits/byte (consistent with repetitive VM/dispatcher/control-flow-flattened instruction templates), while `.rodata` surges to **`6.94–7.28`** (reaching **`7.909`** in `libsecurity_lite.so`) and companion assets hit near-maximal randomness (`assets/fgcpkjvqe.dat`: `7.9998`, `assets/taibmcf.dat` paired with `libtaibmcf.so`).

Across all `2,026` DEX `native` methods (`405` classes), only **`216` methods (`44` classes, `10.7%`)** resolve to static `Java_*` symbol exports (`284` total `Java_*` exports across 10 `.so` files), whereas **`1,810` methods (`361` classes, `89.3%`)** bind dynamically via `JNI_OnLoad` (`13` `.so` libraries) and `RegisterNatives`—dominated by **`1,351` RASP/shield native methods** (`com.xq.cxzmoiwuegvp`: `1,060`, `jipm.njcx`: `53`, `wmoi.igob`: `50`, `jwbmqnsll.xvndbxlk`: `12` including `DisplayListener`, `KeyEvent`, and `MotionEvent` telemetry hooks).

---

## 7. Hybrid Super-App Architecture, On-Device ML & Software Archaeology

### 7.1 Dormant React Native Legacy Fossil (`assets/android.1.zip`)

`assets/android.1.zip` (`3,913,966` B compressed; `9,877,453` B uncompressed) holds `321` files—a `3,613,478`-byte Metro React Native bundle (`main.jsbundle`, **2,346 module definitions**, IDs `0`–`2345`, stamped `2022-03-08`) plus `node_modules_zalopaycomponents_*` and `transactionhistoryv2_*` drawables, referenced by `assets/bundle.json` (`1.0.20160523.001`). However:
1. All **20 DEX string pools contain `0` occurrences** of `android.1.zip`, `main.jsbundle`, `ReactInstanceManager`, `CatalystInstance`, or `facebook/react/`.
2. The `134` `com.facebook.*` classes in DEX belong exclusively to **Fresco/ImagePipeline/SoLoader** (`has_facebook_react_prefix_in_dex = false`).
3. **Zero** React Native/Hermes/JSC `.so` binaries ship in `config.armeabi_v7a.apk`.

Thus, `assets/android.1.zip` is empirically verified as an **orphaned legacy artifact (`2.0%` of base APK size)** left behind during ZaloPay's migration to native Jetpack Compose + Alipay+ Griver.

### 7.2 Active Alipay+ / Griver Super-App & JS Bridge Layer

Replacing React Native, ZaloPay embeds the **Alipay+ / Griver / Ariver** runtime (`6,913` DEX classes) and `assets/config/miniapp_config.json` (`883,684` B), cataloging **373 mini-apps across 19 categories** (led by *Popular Services*: 88, *Entertainment/Community*: 77, *Finance*: 65, *Food & Dining*: 32). Three signed `.amr` ZIP-over-TAR packages (`66666692_1.24.8.amr`, `66666693_2.0.7.amr`, `68687209_2.9.7.8.amr`) supply the `appx` and `appx-ng` WebView/Worker runtimes (`CERT.json` RSA-signed `.tar` payloads). Running `rea analyze-javascript-application` across `assets/` extracts **`5,361` AST nodes**, **`49` graph nodes / `51` edges**, **`3,881` semantic nodes**, and **`2,942` semantic relations** across 8 JS bridge scripts (`GriverBridge/*`, `jsbridge.js`, `internaljsbridge.js`, `android_jsbridge.js`, `webapp.js`).

### 7.3 Tri-Engine On-Device Vision/ML & Regulated Payment Stack (`18.19` MB)

ZaloPay integrates **three independent computer-vision/ML inference engines** totaling **`18,191,516` bytes** of model weights alongside cryptographic NFC & payment rails:
- **Google ML Kit (`14` `.tflite` FlatBuffers, `10,203,704` B):** Object labeler (`3.77` MB), ODT localizer/classifier (`2.96` MB), 4 face detection models (`fssd_*`, `1.69` MB), 4 OCR models (`900` KB), and 3 barcode models (`881` KB).
- **Tencent NCNN (`FastestDet.ncnn.bin`, `2,420,588` B + `.param`):** `172` layers (`52` Convolution, `18` HardSwish, `14` DepthwiseConv) paired with `libpicodet_ncnn.so` (OpenMP + NEON) for document/card edge & object detection.
- **OpenCV Classical Vision (`5,567,224` B in `res/raw/`):** `lbfmodel.yaml` (`3.78` MB facial landmark alignment) + `haarcascade_frontalface_default.xml` (`1.79` MB) paired with `libVFaceLib.so` and `vn.ai.faceauth` (`263` native methods).
- **Regulated Identity & Tokenization:** ICAO 9303 e-Passport/Chip-CCCD NFC (`org.jmrtd.lds`, `151` classes), **Visa Cloud-Based Payments HCE** (`com.visa.cbp`, `900` classes + high-entropy `assets/com/visa/cbp/*` blobs + `tap2pay_payment_guide.riv`), two RSA-2048 public key assets (`public.pem`, `zoom.public`), and SQLCipher (`libsqlcipher.so`, `3.56` MB).

---

## 8. Limitations & Threats to Validity

1. **ABI Coverage:** The captured APKPure XAPK includes only the `armeabi-v7a` ABI split (`config.armeabi_v7a.apk`) and `xhdpi` density split; `arm64-v8a` or `x86_64` binaries may exhibit different AArch64 hardening flags (`BTI`/`PAC`).
2. **Static Observation Bounds:** As explicitly surfaced by `morluto/rea` limitations, static ELF and DEX inspections do not verify runtime `RegisterNatives` target addresses inside obfuscated/packed native modules (`libtaibmcf.so`, `libfgcpkjvqe.so`), nor do APK digest self-consistency checks replace cryptographic chain-of-trust verification.
3. **Identifier Obfuscation Heuristic:** Our R8 class obfuscation metric flags short identifiers (`len <= 2` or short non-semantic lowercase names) and default packages; libraries retaining semantic class names while obfuscating internal method bodies may be undercounted, whereas short un-obfuscated vendor names are checked against a curated allowlist.

---

## 9. Reproducibility & Citation

All analysis scripts (`scripts/01`–`07`), `morluto/rea` Evidence v2 summaries (`evidence/`), structured datasets (`data/`), complete file catalog (`research/COMPLETE_FILE_CATALOG.md`), and the programmatic TypeScript 1080p MP4 documentary (`video/`) are preserved in this repository.

```bibtex
@misc{zalopay_re_2026,
  author       = {{Mobile Systems \& Binary Telemetry Group}},
  title        = {Anatomy of a Super-App Mobile Wallet: Comprehensive Evidence-Bounded Static Reverse Engineering of ZaloPay 11.17.0},
  year         = {2026},
  month        = {11},
  howpublished = {\url{https://github.com/johnlikescarrot/-}},
  note         = {Dataset of 6,832 indexed files, morluto/rea Evidence v2 audit, and TypeScript MP4 documentary}
}
```

### References
[1] `morluto/rea` (`rea-agents@6.10.1`), *Reverse Engineer Anything: CLI and MCP Server for Evidence-Bounded Binary and Application Analysis*, https://github.com/morluto/rea (2026).  
[2] Androguard 4.1.4, *Reverse Engineering, Malware and Goodware Analysis of Android Applications*, https://github.com/androguard/androguard.  
[3] Quarkslab LIEF 1.0.0 & Eli Bendersky `pyelftools`, *Library to Instrument Executable Formats & Pure-Python ELF Analysis*.  
[4] ICAO Doc 9303 / JMRTD (`org.jmrtd.lds`), *Machine Readable Travel Documents & NFC e-ID Cryptographic Protocols*.
