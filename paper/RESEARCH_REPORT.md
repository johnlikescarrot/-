# Anatomy of a Quad-Hybrid FinTech Super-App: Complete Static Reverse Engineering and Empirical Hardening Telemetry of ZaloPay 11.17.0

**Technical Monograph & Empirical Artifact Study**  
**Target Artifact:** `Zalopay-Thanh+toán+&+Tài+chính_11.17.0_APKPure.zip` (`vn.com.vng.zalopay`, `versionCode=1102758`, `versionName=11.17.0`)  
**Reproducible Toolchain:** `morluto/rea` (`rea-agents@6.10.1` / `rea@6.7.0` CLI & MCP), Androguard `4.1.4`, LIEF `1.0.0`, `pyelftools 0.33`, `cryptography 50.0.2`, GNU `objdump`  

---

## Abstract

Modern mobile payment and financial ("FinTech") applications in emerging digital economies have evolved from single-runtime native clients into dense **multi-runtime super-apps** integrating native Android UI, cross-platform JavaScript frameworks, embedded mini-program containers, on-device deep learning pipelines, and proprietary native runtime-application self-protection (RASP) shields. Yet empirical, full-inventory static measurements spanning *100% of container artifacts* inside production FinTech app bundles remain scarce in peer-reviewed software engineering and security literature.

In this study, we perform an exhaustive, non-destructive, strictly static reverse-engineering audit of **all 6,824 file artifacts** (`195,327,309` uncompressed APK payload bytes plus `360` nested React Native and mini-program container files) packaged inside `Zalopay-Thanh+toán+&+Tài+chính_11.17.0_APKPure.zip` (`vn.com.vng.zalopay` v11.17.0, `targetSdkVersion=36`). Combining deterministic cryptographic artifact graphing from `morluto/rea` with Dalvik bytecode, AXML/ARSC resource, and ELF binary inspection, we uncover four principal findings:

1. **Quad-Hybrid Runtime Convergence:** The application unifies (i) native Kotlin/Java + Jetpack Compose (`1.7.2`) across **20 DEX files** (`66.47 MiB`, `63,827` classes, `378,040` methods), (ii) a **React Native Metro bundle** (`3.61 MiB`, `2,346` `__d(...)` module definitions) inside `assets/android.1.zip`, (iii) an **Ant Group / Alipay+ Griver Mini-Program engine** (`appx` & `appx-ng` across 3 `.amr`/`.tar` bundles) governing **373 configured mini-apps** across **5 sandboxed processes (`:lite1`–`:lite5`)**, and (iv) **27 native ARMv7 ELF `.so` libraries** (`54.79 MiB`).
2. **Compiler Toolchain Fragmentation Across 6 NDK Generations:** ELF `.comment` provenance reveals **6 distinct Android NDK Clang generations** co-existing simultaneously inside `config.armeabi_v7a.apk`—spanning legacy **Clang 7.0.2** (`libsecurity_lite.so`) through **Clang 14.0.x**, **Clang 17.0.2**, **Clang 18.0.x**, and bleeding-edge **Clang 21.0.0** (`libfgcpkjvqe.so`, `libpicodet_ncnn.so`).
3. **Heterogeneous Native Hardening Posture ($H_{\text{bin}}$):** While **100% (`27/27`)** of native ELF binaries enforce **PIE (`ET_DYN`)** and **NX (`PT_GNU_STACK`)**, **Full RELRO** drops to **85.2% (`23/27`)**—missing exclusively in all four Firebase Crashlytics binaries—while **Stack Canaries** cover **92.6% (`25/27`)**, **`FORTIFY_SOURCE`** covers **55.6% (`15/27`)**, and one binary (`libcrashlytics-trampoline.so`) ships unstripped (`.symtab` intact).
4. **Heavyweight Edge-AI & eKYC Footprint:** **51 on-device ML/computer-vision artifacts (`18.83 MiB`)** plus **`26.28 MiB` of CV/AI `.so` engines** (~45 MiB combined) run locally via Google ML Kit, Tencent NCNN, OpenCV LBF (`lbfmodel.yaml`), ONNX/VFace, and TFLite for Vietnamese CCCD NFC/OCR verification and liveness checks.

---

## 1. Research Scope, Methodology, and Responsible Ethics

### 1.1 Ethical & Non-Exploitation Boundaries
Following established IEEE S&P / USENIX Security empirical software telemetry norms, this study strictly confines analysis to **static structural, cryptographic, architectural, and defensive-posture measurement**:
- No dynamic instrumentation, tampering, anti-tamper bypass, or exploit synthesis is performed.
- No binary vulnerability discovery for exploitation is pursued, and no cryptographic keys, tokens, or proprietary source code are extracted or redistributed.
- All findings represent verifiable structural observations derived inertly from the packaged artifact (`authority: shipped-artifact`).

### 1.2 Multi-Stage Deterministic Pipeline (`scripts/01`–`05`)
Our reproducible verification pipeline processes 100% of container levels:
1. **Deterministic Graph & JS/ELF Telemetry (`scripts/01_rea_inspect_all.mjs`):** Invokes `morluto/rea` (`rea-agents@6.10.1`, CLI + JSON-RPC MCP server) to compute cryptographic `ArtifactGraphManifest` SHA-256 digests (`inspect-artifact`), Flutter AOT detection (`identify_flutter_build`), AST/semantic graphs for hybrid JavaScript bridges (`analyze_javascript_application`), Rust/Go provenance checks (`inspect_rust_binary`, `inspect_go_binary`), and `objdump` evidence retention (`inspect_with_objdump`).
2. **Full Container Forensics & Signing Audit (`scripts/02_complete_apk_inventory.py`):** Walks every entry across the outer `.zip`, inner `.xapk`, all 5 split `.apk` files, `assets/android.1.zip`, and `assets/Griver/*.amr -> *.tar`, computing per-entry SHA-256, Shannon entropy $H(X) = -\sum_{i=0}^{255} p(x_i)\log_2 p(x_i)$, magic bytes, deduplication waste, and APK Signature Block v2/v3 + X.509 certificate metadata.
3. **Manifest, ARSC & Multi-DEX Bytecode Taxonomy (`scripts/03_manifest_and_dex_analysis.py`):** Decodes all 5 binary `AndroidManifest.xml` files, 4 `resources.arsc` pools, and all 20 `.dex` headers/class tables via Androguard `4.1.4`.
4. **Native ELF Hardening & Toolchain Audit (`scripts/04_native_elf_audit.py`):** Evaluates all 27 ARMv7 `.so` binaries using LIEF `1.0.0` and `pyelftools 0.33` for `ET_DYN` (PIE), `PT_GNU_STACK` (NX), `PT_GNU_RELRO` + `BIND_NOW` (Full RELRO), `__stack_chk_fail` (Stack Canary), `__*_chk` (`FORTIFY_SOURCE`), `.symtab` stripping, `DT_NEEDED` edges, and `.comment` / `.note.android.*` compiler provenance.
5. **Asset, ML Model & Hybrid Schema Synthesis (`scripts/05_assets_configs_and_master.py`):** Aggregates AndroidX/Jetpack `.version` metadata, ML model engines, React Native Metro bundle structure, Griver `.amr` layouts, and `network_security_config.xml` into `data/master_metrics.json`.

---

## 2. Container Architecture, XAPK Modularity, and Cryptographic Signing

### 2.1 Multi-Layer Archive Hierarchy
The top-level distribution `Zalopay-Thanh+toán+&+Tài+chính_11.17.0_APKPure.zip` (`98,813,878` bytes; SHA-256 `34b9b6b6735f0f739617f97a259f931d17cdbacb51871c40b066de41f4b4610a`) wraps a single XAPK v2 bundle (`105,608,128` bytes; SHA-256 `686e9459da646c18b53a67d57e5a23c49466da140ec1e3e9682032f453ea7b8d`), which in turn decomposes into **5 Android App Bundle split APKs** (`6,464` APK entries totaling `195,327,309` bytes uncompressed / `104,477,912` bytes compressed, compression ratio `0.5349`) plus `manifest.json` and `icon.png`.

| Split APK Artifact | Compressed APK Size | Entries | Uncompressed Payload | Mean Entropy (bits/B) | REA Graph SHA-256 Prefix | Role |
|---|---:|---:|---:|---:|---|---|
| `vn.com.vng.zalopay.apk` | 75.68 MiB (79,359,087 B) | 6,162 | 132.25 MiB | 4.313 | `5e4ccc029c664734` | Base DEX, Resources, Assets, AI Models |
| `config.armeabi_v7a.apk` | 23.68 MiB (24,828,408 B) | 32 | 54.83 MiB | 6.380 | `1e9a6706fd8c2651` | 27 ARMv7 Native ELF `.so` Libraries |
| `config.xhdpi.apk` | 1.18 MiB (1,232,563 B) | 258 | 1.48 MiB | 6.003 | `bbda086f7c7de492` | XHDPI Drawables & Adaptive Icons |
| `config.vi.apk` | 0.08 MiB (86,425 B) | 6 | 0.07 MiB | 5.213 | `1661bcf6c985cc0a` | Vietnamese `resources.arsc` Split |
| `config.en.apk` | 0.07 MiB (74,137 B) | 6 | 0.06 MiB | 5.082 | `db79fea24151d5bd` | English `resources.arsc` Split |
| **Total Across 5 Splits** | **100.69 MiB** | **6,464** | **186.28 MiB** | **—** | **0 Contradictions** | **Complete XAPK v2 Split Set** |

### 2.2 Storage Footprint Breakdown & Redundancy Audit
Across all 6,464 APK entries, byte volume is heavily dominated by executable code and on-device intelligence:
- **Dalvik Bytecode (`.dex`):** `66.47 MiB` (**35.68%**, 20 files)
- **Native Libraries (`.so`):** `54.79 MiB` (**29.41%**, 27 ELF files)
- **Manifest & Compiled Resources:** `39.12 MiB` (**21.00%**, 5,865 files)
- **On-Device ML & Vision Models:** `15.23 MiB` (**8.17%**, 46 primary entries; `18.83 MiB` / 51 artifacts including `res/raw/lbfmodel.yaml` & configs)
- **Hybrid JS / React Native / Griver:** `4.95 MiB` (**2.66%**, 23 APK entries unpacking to `360` nested files)
- **Other Assets, Fonts, `META-INF`, ART Profiles:** `5.72 MiB` (**3.08%**)

**Deduplication Waste Discovery:** Exact SHA-256 content hashing across all 6,464 entries reveals `5,968` unique digests and **299 duplicate file groups** wasting **`959,564` bytes (~937 KiB)**—including byte-identical font files (`assets/fonts/UnisectVnu.ttf` $\equiv$ `assets/fonts/ocr-a-tribute.ttf`, `48,216 B`), duplicated BouncyCastle PKIX/X.509 localization bundles (`49,608 B` & `42,868 B`), duplicated Lottie guidance JSONs (`ic_cashier_pay_loading.json` $\equiv$ `ic_cashier_pay_loading_v3.json`), and duplicated NFC guidance PNGs (`106,396 B`).

### 2.3 Cryptographic Signing & Integrity Verification
Every split APK carries an 8,192-byte page-aligned **APK Signing Block (`APK Sig Block 42`)** verified with zero REA integrity contradictions:
- **Base APK (`vn.com.vng.zalopay.apk`):** Signed with **APK Signature Scheme v2 (`0x7109871a`)**, **v3 (`0xf05368c0`)**, **Source Stamp Block v1 (`0x6dff800d`)**, **Verity Padding (`0x42726577`)**, and the Play/AAB **`NDF!` (`0x2146444e`)** block. Because `minSdkVersion=26` exceeds Android 7.0 (API 24), legacy v1 JAR signing is omitted on the base APK.
- **4 Split APKs (`config.*`):** Carry v2 + v3 + Source Stamp v1 + Verity Padding **plus** legacy v1 `META-INF/BNDLTOOL.RSA` (2048-bit `RSAPublicKey`, `sha256WithRSAEncryption`, subject/issuer `CN=Vu Manh Hieu`, serial `0x153ea9fd`, valid `2016-04-21` to `2041-04-15`, SHA-256 fingerprint `59dd31fdf36070a0d7f8c8cde843fac371df7f1a915c0136c9c9b55992e8cdae`) and `stamp-cert-sha256`.

---

## 3. Android Manifest, Permission Surface, and Process Isolation

Decoding the binary AXML `AndroidManifest.xml` of `vn.com.vng.zalopay.apk` exposes a massive application surface targeting **Android 16 (`targetSdkVersion=36`, `compileSdkVersion=36`)** with **`minSdkVersion=26`** (Android 8.0):

- **Application Entrypoint & Security Flags:** `android:name="jwbmqnsll.xvndbxlk"` (obfuscated/shielded custom `Application` wrapper), `android:allowBackup="false"`, `android:largeHeap="true"`, `android:extractNativeLibs="true"`, `android:debuggable` absent (`false`).
- **Component Surface (267 Total Components):**
  - **200 Activities** (`14` `exported="true"`, `29` `exported="false"`, `8` custom-process isolated) + **2 Activity-Aliases** (`2` exported)
  - **34 Services** (`6` `exported="true"`, `24` `exported="false"`, `9` permission-protected, `8` custom-process isolated)
  - **22 BroadcastReceivers** (`8` `exported="true"`, `14` `exported="false"`, `3` permission-protected)
  - **9 ContentProviders** (`0` `exported="true"`, `9` `exported="false"`)
- **Multi-Process Sandbox Architecture (7 Custom Processes):** `:lite1`, `:lite2`, `:lite3`, `:lite4`, `:lite5` (5 parallel mini-program container worker processes), `:taibmcf` (isolated native security/shield process matching `libtaibmcf.so`), and `:xcmzqsawpqoefcds` (obfuscated isolated service process).
- **49 Declared Permissions:** `14` Android dangerous runtime permissions (Camera, Contacts R/W, Fine/Coarse Location, Record Audio, Calendar R/W, Phone State, Storage, Notifications, Media Audio), `10` normal permissions, and `25` sensitive/OEM/ecosystem permissions—notably **`DETECT_SCREEN_CAPTURE`** (Android 14+ anti-capture protection), **`NFC`** (Visa CBP HCE & Vietnamese CCCD chip reading), **`USE_BIOMETRIC`** / **`USE_FINGERPRINT`**, **`QUERY_ALL_PACKAGES`**, **`MANAGE_OVERLAY_PERMISSION`**, **`com.zing.zalo.permission.ACCESS_THIRD_PARTY_APP_AUTHORIZATION`**, and Android AdServices permissions.
- **Network Security Config (`res/xml/network_security_config.xml`):** Configures `base-config` with `cleartextTrafficPermitted="true"` using `system` trust anchors (plus an explicit `<domain-config>` for `storage.googleapis.com` and `debug-overrides` only active in debuggable builds). Maintaining `cleartextTrafficPermitted="true"` in `base-config` on a payment application represents a noteworthy legacy compatibility posture relative to strict HTTPS-only FinTech hardening best practices.

---

## 4. Multi-DEX Bytecode Anatomy & Third-Party SDK Ecosystem

ZaloPay 11.17.0 compiles into **20 Dalvik Executable (`.dex`) files** (`classes.dex` through `classes19.dex`, plus dynamically loaded **`assets/audience_network.dex`**) totaling **`69,701,748` bytes (`66.47 MiB`)**:

- **Aggregate Bytecode Scale:** **`63,827` `class_defs`**, **`378,040` `method_ids`** (summed across DEX headers), **`317,841` `field_ids`**, and **`404,914` `string_ids`**.
- **60,000-Method DEX Splitting Signature:** `classes.dex` (`59,948`), `classes4.dex` (`59,997`), and `classes7.dex` (`60,000`) align sharply against Android D8/R8's `60,000`-method soft packing threshold (leaving headroom below the `65,536` `uint16` method index ceiling), whereas `classes10.dex`–`classes19.dex` act as small overflow/sharded shards (`20` to `777` classes each).
- **R8/ProGuard Obfuscation Footprint:** Across all 63,827 classes, **`44,452` classes (`69.64%`)** exhibit short 1–2 character class or package identifiers. Obfuscation exceeds **97%** in `assets/audience_network.dex` (`97.3%`) and overflow DEX shards (`classes8`, `classes10`, `classes15`, `classes18`, `classes19` at `99.1%–99.7%`), while remaining lower in framework-preserved DEX partitions like `classes5.dex` (`32.5%`) and `classes2.dex` (`48.0%`).
- **SDK Ecosystem Class Distribution:**
  - *R8/ProGuard Short-Obfuscated Namespaces:* `26,977` classes (`42.27%`)
  - *Google / GMS / Firebase / ML Kit:* `7,861` classes (`12.32%`)
  - *Payments / Crypto / eKYC / Biometrics (BouncyCastle, Visa CBP, SQLCipher, TrueID):* `7,842` classes (`12.29%`)
  - *Ant Group / Alipay+ / Griver (`com.alibaba`, `com.alipay`, `com.iap`):* `6,913` classes (`10.83%`)
  - *AndroidX / Jetpack / Kotlin (incl. `141` `META-INF/*.version` artifacts, Compose `1.7.2`, CameraX `1.4.0`, `509` Compose classes):* `4,673` classes (`7.32%`)
  - *React Native / Hermes / Meta (`com.facebook`, `com.swmansion`, etc.):* `3,556` classes (`5.57%`)
  - *Other Third-Party Libraries:* `3,459` classes (`5.42%`)
  - *Unobfuscated ZaloPay First-Party Entrypoints:* `1,138` classes (`1.78%`)
  - *Networking / Serialization / Concurrency (OkHttp3, Retrofit2, Rive, Lottie):* `1,005` classes (`1.57%`)
  - *Analytics / Attribution / Crash (AppsFlyer, CleverTap, Sentry):* `376` classes (`0.59%`)
  - *VNG / Zalo Ecosystem & Root:* `27` classes (`0.04%`)

---

## 5. Native ELF Binary Ecosystem, Toolchain Provenance, and Hardening Audit

All **27 native `.so` libraries** (`57,452,124` bytes / `54.79 MiB`) inside `config.armeabi_v7a.apk` are **ARM 32-bit little-endian ELF (`ET_DYN`)** shared objects. Running `morluto/rea` (`inspect_rust_binary`, `inspect_go_binary`, `inspect_with_objdump`) confirms **zero Go `buildinfo` binaries** and **zero `cargo-auditable` Rust `.dep-v0` binaries**, establishing a C/C++/NDK native codebase (plus `assets/trueidlib.so`, a `392`-byte non-ELF high-entropy companion file with Shannon entropy `7.54` bits/B).

### 5.1 Six-Generation Android NDK Clang Fragmentation
Inspecting `.comment` sections exposes **6 distinct LLVM/Clang toolchain generations** shipped concurrently inside one APK:
1. **Clang 7.0.2 (`r328903`, NDK r18 era):** `libsecurity_lite.so` (Shannon entropy `7.30`, highest among `.so` files).
2. **Clang 14.0.6 (`r450784d`, NDK r25):** `libcrashlytics*.so` (4 libraries), `libljsfrcwmw.so`, `liblytcuhe.so`, `libohxbee.so`, `librcmefqonnx.so`, `libtrueidlib.so`.
3. **Clang 14.0.7 (`r450784d1`, LLD 14.0.7):** `libsqlcipher.so` (`3.39 MiB`), `libandroidx.graphics.path.so`, `libimage_processing_util_jni.so`.
4. **Clang 17.0.2 (`r487747e`, +PGO/+BOLT/+LTO):** `libtaibmcf.so` (`12.03 MiB`, the largest native binary in the APK!).
5. **Clang 18.0.1–18.0.3 (`r522817`, +PGO/+BOLT/+LTO/+MLGO):** `libVFaceLib.so`, `libc++_shared.so`, `librive-android.so` (`3.08 MiB`, `167` JNI exports!), `libimagepipeline.so`, `libnative-filters.so`, `libnative-imagetranscoder.so`.
6. **Clang 21.0.0 (`r563880c`, bleeding-edge NDK):** `libfgcpkjvqe.so` (`6.22 MiB`, paired with `assets/fgcpkjvqe.dat`) and `libpicodet_ncnn.so` (`2.85 MiB`).

### 5.2 Empirical Binary Hardening Score ($H_{\text{bin}}$)
We evaluate five canonical binary exploit-mitigation flags across all 27 native ELF libraries:
- **PIE (`ET_DYN`):** **27 / 27 (100.0%)**
- **NX Stack (`PT_GNU_STACK` without `PF_X`):** **27 / 27 (100.0%)**
- **Full RELRO (`PT_GNU_RELRO` + `BIND_NOW`):** **23 / 27 (85.2%)** — *4 libraries have `RELRO=none` (`libcrashlytics.so`, `libcrashlytics-common.so`, `libcrashlytics-handler.so`, `libcrashlytics-trampoline.so`)*
- **Stack Canary (`__stack_chk_fail` / `__stack_chk_guard`):** **25 / 27 (92.6%)** — *absent in `libc++_shared.so` and `libcrashlytics-trampoline.so`*
- **`FORTIFY_SOURCE` (`__*_chk`):** **15 / 27 (55.6%)**
- **Stripped `.symtab`:** **26 / 27 (96.3%)** — *`libcrashlytics-trampoline.so` uniquely retains an unstripped `.symtab` (`4.66` entropy)*

**Notable Structural Observation (`DT_NEEDED` Sibling Chains):** `libljsfrcwmw.so` (`0.57 MiB`) explicitly links via `DT_NEEDED` against `librcmefqonnx.so` (`178,628 B`), while `libohxbee.so` (`0.15 MiB`) explicitly links against `liblytcuhe.so` (also *exactly* `178,628 B`, SHA-256 `a8e59c1d49...` vs `9120dd02a8...`, both compiled with Clang 14.0.6), illustrating modular obfuscated native library chains within the security/eKYC subsystem.

---

## 6. Hybrid Mini-Program Containers & On-Device Edge AI Stack

### 6.1 React Native + Ant Group Griver Co-Existence
- **React Native (`assets/android.1.zip`):** Contains a `3,613,478`-byte (`2,358`-line) Metro `main.jsbundle` defining **`2,346` `__d(function...)` modules** plus `325` pre-bundled image assets covering vertical modules (`notifications`: `108` assets, `transactionhistoryv2`: `60`, `about`: `41`, `clearingaccount`: `27`, `registeronlinebanking`: `15`, and `@zalopay/components`: `45`).
- **Ant Group Griver Mini-Program Runtime (`assets/Griver/*.amr`, `assets/GriverBridge/*.js`):** Ships three pre-bundled `.amr` ZIP->TAR mini-program frameworks (`66666692_1.24.8.amr` carrying legacy `appx`, `68687209_2.9.7.8.amr` carrying next-gen `appx-ng` at `3.29 MiB`, and `66666693_2.0.7.amr` sample mini-app), orchestrated by `assets/config/miniapp_config.json` (`883,684 B`, registering **`373` mini-app entries**). `morluto/rea`'s `analyze_javascript_application` verified `16` graph nodes and `16` AST edges across `GriverBridge` (`appx_inline_bootstrap_min.js`, `h5_bridge.js`, `workerjs.js`, `cover_component.js`) with zero integrity contradictions.

### 6.2 On-Device Edge AI & eKYC Pipeline (`18.83 MiB` Models + `26.28 MiB` Engines)
ZaloPay ships **51 model artifacts (`19,746,112` bytes)** paired with `27,557,616` bytes of vision `.so` runtimes:
- **Face Detection & Liveness (`6.30 MiB` models):** OpenCV `lbfmodel.yaml` (`3.60 MiB`), MediaPipe `contours.tfl` (`1.07 MiB`), `LMprec_600.emd` (`1.03 MiB`), `fssd_medium_8bit_v5.tflite` + gray (`1.17 MiB`), `blazeface.tfl`, plus `BCLjoy_200.emd` / `BCLlefteyeclosed_200.emd` / `BCLrighteyeclosed_200.emd` (expression & blink anti-spoofing!).
- **Google ML Kit Object Detection & Custom TFLite (`6.42 MiB` models):** `object_labeler.tflite` (`3.60 MiB`), `labeler_with_validation.tflite` (`1.63 MiB`), `localizer_with_validation.tflite` (`1.20 MiB`).
- **Tencent NCNN (`2.33 MiB` models):** `FastestDet.ncnn.bin` (`2.31 MiB`) + `.param` executed by `libpicodet_ncnn.so` (`2.85 MiB`).
- **Google ML Kit OCR & Barcode (`3.78 MiB` models):** `GOCR`, `Taser`, `Aksara`, and `barcode_ssd_mobilenet_v1_dmp25_quant.tflite`, backed by `libmlkitcommonpipeline.so` (`6.61 MiB`), `libmlkit_google_ocr_pipeline.so` (`6.47 MiB`), and `libbarhopper_v3.so` (`3.09 MiB`).

---

## 7. Threats to Validity & Limitations

1. **Single-ABI Snapshot Scope:** The APKPure XAPK snapshot provides `config.armeabi_v7a.apk` (32-bit ARMv7). `arm64-v8a` builds compiled with NDK Clang 18+/21+ may exhibit additional ARM64 hardware mitigations (e.g., PAC/BTI) not present in 32-bit ARMv7 ELF headers.
2. **Static-Only Evidence:** Per `morluto/rea` evidence contracts, static analysis observes shipped artifact structure (`shipped-artifact`) and does not execute runtime payloads, dynamic DEX loaders (`audience_network.dex`), or obfuscated `.dat` shield blobs (`bshield.dat`, `fgcpkjvqe.dat`, `taibmcf.dat`).
3. **Name-Based Obfuscation Heuristic:** Short-name class ratio (`69.64%`) approximates R8/ProGuard identifier minification and excludes control-flow flattening or string encryption inside native libraries.

---

## 8. Reproducibility & Citation

All extraction scripts (`scripts/01`–`05`), deterministic JSON evidence files (`data/*.json`), TypeScript motion-graphics video renderer (`video/src/`), and `CITATION.cff` are archived in this repository. Please cite via `CITATION.cff` and `paper/references.bib`.
