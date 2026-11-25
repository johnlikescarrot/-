# Anatomy of a Modern Southeast Asian FinTech Super-App: A 100%-File-Coverage Empirical Structural Study of ZaloPay v11.17.0 (Android 16 / `targetSdk 36`)

> **Artifact & Reproducibility Companion** — Includes deterministic pure-Python binary parsers (`AXML`, `DEX`, `ELF32/64`, `ARSC`, `APK Sig Block`, `OpenType`), structured empirical telemetry (`data/analysis_report.json`, `data/video_telemetry.json`), BibTeX bibliography (`paper/references.bib`), and a programmatic TypeScript 1080p MP4 visual research paper (`video/`).

---

## Abstract (English)

Modern mobile financial super-applications in emerging markets have evolved far beyond single-purpose e-wallets into multi-process mini-program containers, real-time computer-vision platforms, and cross-border payment hubs. Yet empirical software-engineering literature lacks fine-grained, 100%-file-coverage architectural studies of production fintech super-apps targeting **Android 16 (`targetSdkVersion = 36`)**.

In this study, we conduct a complete, reproducible static structural and ecosystem analysis covering **all 6,464 files (195.33 MB uncompressed; 104.48 MB compressed entries across 5 Split APKs)** inside the production XAPK v2 release of **ZaloPay v11.17.0** (`vn.com.vng.zalopay`, `versionCode 1102758`). Using custom deterministic binary parsers operating strictly on structural metadata—without proprietary source decompilation, native machine-code disassembly, secret extraction, or vulnerability auditing—we answer five research questions spanning container packaging (RQ1), platform/manifest governance (RQ2), multi-DEX software composition (RQ3), native ELF binary archaeology and 16 KB page-size readiness (RQ4), and resource/AI-model engineering bloat (RQ5).

Our empirical findings reveal five striking structural phenomena in modern mobile fintech engineering:
1. **On-Device AI Dominance & 25-Year CV Stratification:** Native computer-vision/ML runtimes (27.48 MB across 7 `.so` libraries) combined with neural weights (16.01 MB across 46 ML assets, including 20 `.tflite` models and `FastestDet.ncnn.bin`) and legacy OpenCV models (`lbfmodel.yaml`, `haarcascade_frontalface_default.xml`, 5.57 MB) consume **49.06 MB—25.12% of the entire uncompressed application**, spanning 25 years of computer vision history (2001 Viola–Jones Haar to 2022–2026 NCNN/TFLite) inside a single shipping APK.
2. **Triangulated Super-App Mini-Program Architecture:** Manifest multi-process pooling (`:lite1`–`:lite5`), DEX software composition (`com.alibaba` 4,821 classes, `com.iap` 1,599, `com.alipay` 493 = **6,913 classes**), and `assets/Griver/` offline `.amr` bundles independently confirm Ant International's **Griver/Alipay+ Mini-Program** container architecture embedded inside ZaloPay.
3. **Bimodal Multi-DEX Partitioning & Dynamic Legacy DEX:** Across **20 DEX shards (69.70 MB; 63,827 classes; 287,480 defined methods; 60.92% R8-minified)**, shards 1–7 saturate up to **91.5%** of Android's 64K method-reference budget, whereas shards 8–19 form a 12-shard micro-DEX long tail (0.1%–10.9% utilization), accompanied by a dynamically embedded **DEX v035** (`assets/audience_network.dex`, 4.86 MB).
4. **8-Year Native Compiler Stratification & Android 16 (16 KB Page) Split:** Across **27 native ELF `.so` libraries (57.45 MB)**, `.comment` toolchain headers span **five distinct LLVM Clang generations from Clang 7.0.2 (NDK r19, 2018) through Clang 21.0.0 (NDK r29, 2025–2026)**. While **20/27 (74.07%)** already compile `PT_LOAD` segments with **16 KB (`16,384 B`) alignment**, **7/27 (25.93%)**—chiefly prebuilt Google ML Kit binaries, `librive-android.so`, and `libc++_shared.so`—remain 4 KB-aligned.
5. **Quantified Packaging Hygiene & Bloat (17.04 MB Reclaimable):** We identify **25 bundled fonts (7.41 MB)** with duplicated Roboto/SF families, **269 PNGs (7.59 MB)** alongside WebP/VectorDrawables, **2 multi-megabyte SVGs (2.55 MB)**, **1.22 MB of unpruned BouncyCastle Post-Quantum Cryptography (`org/bouncycastle/pqc/`) classpath lookup tables**, **496 redundant byte-identical copies (0.96 MB)**, and even **`junit/runner/` test logos** packaged in production—yielding **17.04 MB** in conservative zero-functionality-loss savings.

---

## Tóm tắt Điều hành (Tiếng Việt)

Nghiên cứu thực nghiệm này phân tích toàn diện cấu trúc tĩnh đối với **100% các tệp tin (6.464 mục, tổng dung lượng giải nén 195,33 MB trên 5 Split APK)** bên trong gói phát hành **ZaloPay v11.17.0** (`vn.com.vng.zalopay`, `targetSdkVersion = 36` — Android 16). Tuân thủ nghiêm ngặt chuẩn mực đạo đức nghiên cứu khoa học phần mềm (chỉ phân tích siêu dữ liệu cấu trúc nhị phân `AXML`, `DEX`, `ELF`, `ARSC`, `APK Signing Block` và thành phần phần mềm — **không** dịch ngược mã nguồn độc quyền, **không** giải mã hợp ngữ nhị phân bảo mật, **không** trích xuất bí mật/API nội bộ và **không** dò tìm lỗ hổng khai thác), công trình làm rõ 5 phát hiện trọng yếu:
1. **AI/Thị giác máy tính trên thiết bị chiếm 25,12% toàn bộ ứng dụng (49,06 MB)**, quy tụ 25 năm lịch sử thị giác máy tính (từ Haar Cascade 2001, LBF 2014 đến ML Kit TFLite 2019–2024 và NCNN FastestDet/PicoDet 2022–2026).
2. **Kiến trúc Siêu ứng dụng Mini-App (Ant International Griver/Alipay+)** được kiểm chứng chéo qua 5 tiến trình cách ly `:lite1`–`:lite5`, 6.913 lớp `com.alibaba`/`com.iap`/`com.alipay` và gói tài nguyên `assets/Griver/`.
3. **Phân mảnh Multi-DEX hai đỉnh (20 phân mảnh DEX, 63.827 lớp, 287.480 phương thức, 60,92% rút gọn R8)** cùng 1 tệp DEX phiên bản cũ `v035` nhúng trong `assets/`.
4. **Phân tầng trình biên dịch 8 năm (Clang 7 đến Clang 21) và quá trình chuyển đổi căn chỉnh trang nhớ 16 KB của Android 16**: 20/27 thư viện `.so` (74,07%) đã căn chỉnh `PT_LOAD = 16 KB`, trong khi 7/27 thư viện bên thứ ba vẫn ở mức `4 KB`.
5. **Tiềm năng tối ưu dung lượng 17,04 MB không mất tính năng**: bao gồm 25 phông chữ (7,41 MB), 269 ảnh PNG (7,59 MB), 2 tệp SVG lớn (2,55 MB), 1,22 MB bảng tra cứu mật mã hậu lượng tử BouncyCastle PQC chưa được cắt tỉa, 496 tệp trùng lặp (0,96 MB) và 2 tệp logo `junit/runner/` còn sót lại trong bản phát hành.

---

## 1. Ethical Scope & Scientific Methodology

### 1.1 Non-Exploitative Empirical Research Boundary
Following established academic mobile software-engineering conventions (Allix et al., MSR '16 [allix2016androzoo]; Tang et al., IEEE TSE '25 [tang2025appbundles]; Xu et al., WWW '19 [xu2019dlsmartphones]), this study evaluates a publicly distributed commercial APK strictly at the **structural, packaging, architectural, and software-composition** level:
- **Included:** Container & split-APK layout, magic-byte file taxonomy, SHA-256 deduplication telemetry, APK Signing Block scheme IDs, binary `AndroidManifest.xml` declarations, DEX header/table/package-prefix metrics, ELF header/`PT_LOAD`/section/`DT_NEEDED`/`.comment` telemetry, `resources.arsc` table counts, image/font/Lottie/ML-model inventories, and `META-INF/*.version` build provenance.
- **Explicitly Excluded:** Proprietary Dalvik bytecode decompilation or source reconstruction; native machine-code disassembly of cryptographic, anti-tamper, anti-root, or certificate-pinning modules; secret/token/credential/private-endpoint extraction; and offensive vulnerability discovery or attack-surface exploitation analysis.

### 1.2 Automated 100%-Coverage Binary Parsing Pipeline
All measurements are deterministically produced by `analysis/analyze_archive.py` and `analysis/build_video_dataset.py` using pure Python 3.11 parsers with zero external native dependencies:
- `analysis/parsers/axml_parser.py` — Binary `RES_XML_TYPE (0x0003)` parser for all 5 split manifests.
- `analysis/parsers/dex_parser.py` — Binary DEX header, `type_ids`, `class_defs`, and ULEB128 `class_data_item` count reader + ecosystem SCA classifier.
- `analysis/parsers/elf_parser.py` — Binary ELF32/ELF64 header, `PT_LOAD` alignment, section table, `DT_NEEDED`, and `.comment` toolchain reader.
- `analysis/parsers/arsc_and_assets_parser.py` — Binary `RES_TABLE_TYPE (0x0002)` resource parser, APK Signing Block (`APK Sig Block 42`) inspector, OpenType/TrueType `name` table reader, and Bodymovin/Lottie JSON structural profiler.

---

## 2. Empirical Results

### 2.1 RQ1 — XAPK v2 Container & Split-APK Packaging Anatomy

Consistent with Tang et al. (IEEE TSE 2025) [tang2025appbundles], who observed >90% Android App Bundle adoption among apps exceeding 150 MB, ZaloPay v11.17.0 is distributed as an **XAPK v2** multi-split bundle containing **5 Split APKs** plus icon and manifest metadata:

| Split APK | Role | Files | Disk Size (MB) | Uncompressed (MB) | Compression Savings | Signing Schemes |
| :--- | :--- | ---: | ---: | ---: | ---: | :--- |
| `vn.com.vng.zalopay.apk` | Base (DEX, res, assets) | 6,162 | 79.36 | 136.40 | 42.6% | v2, v3, Source Stamp v2, `0x2146444e` |
| `config.armeabi_v7a.apk` | 32-bit ARM Native ABI | 32 | 24.83 | 57.46 | 56.8% | v1, v2, v3, Source Stamp v2 |
| `config.xhdpi.apk` | 320 dpi Screen Density | 258 | 1.23 | 1.33 | 11.4% | v1, v2, v3, Source Stamp v2 |
| `config.vi.apk` | Vietnamese Locale | 6 | 0.09 | 0.08 | 1.0% | v1, v2, v3, Source Stamp v2 |
| `config.en.apk` | English Locale | 6 | 0.07 | 0.06 | 1.2% | v1, v2, v3, Source Stamp v2 |
| **Total (100% Coverage)** | **5 Split APKs** | **6,464** | **105.58** | **195.33** | **46.51%** | **All v2 + v3 + Verity Padding** |

**File-Type Composition (Magic-Byte Verified across all 6,464 entries):**

| File Type (Magic-Byte Classification) | Count | Uncompressed (MB) | Share (%) | Compressed (MB) |
| :--- | ---: | ---: | ---: | ---: |
| Dalvik Executable (`.dex`) | 20 | 69.70 | 35.68% | 31.21 |
| ELF Native Shared Library (`.so`) | 27 | 57.45 | 29.41% | 24.81 |
| TensorFlow Lite Model (`.tflite` / `.tfl` / `.fb`) | 20 | 12.23 | 6.26% | 11.90 |
| Binary Android XML (`AXML`) | 5,319 | 9.43 | 4.83% | 3.63 |
| Other Binary / Data Asset (incl. `lbfmodel.yaml`, `FastestDet.ncnn.bin`) | 65 | 8.84 | 4.52% | 6.27 |
| PNG Raster Image | 269 | 7.59 | 3.88% | 7.59 |
| TrueType / OpenType Font | 25 | 7.41 | 3.79% | 3.25 |
| JSON Data / Lottie / Config | 64 | 6.68 | 3.42% | 2.87 |
| Nested ZIP / JAR / `.amr` Bundle | 4 | 5.08 | 2.60% | 5.01 |
| SVG Vector Graphic | 2 | 2.55 | 1.31% | 1.90 |
| Android Compiled Resource Table (`resources.arsc`) | 4 | 2.08 | 1.07% | 2.08 |
| WebP Image | 159 | 1.92 | 0.99% | 1.92 |
| Remaining Types (XML, metadata, GIF, Protobuf, etc.) | 486 | 4.37 | 2.24% | 1.99 |

- **Deduplication Finding:** Exactly **5,968 unique SHA-256 hashes** exist among 6,464 file entries, revealing **496 redundant byte-identical file copies** wasting **0.96 MB** uncompressed.
- **Signing Architecture:** Base APK drops legacy JAR v1 signing (`minSdkVersion = 26` >= Android 7.0 Nougat) while carrying v2, v3, Source Stamp v2, Verity Padding, and Google Play's `0x2146444e` metadata block.

---

### 2.2 RQ2 — Android 16 Platform Governance, Permissions & Multi-Process Architecture

Parsing `AndroidManifest.xml` across the base APK yields:
- **Platform Lifecycle:** `minSdkVersion = 26` (Android 8.0), `targetSdkVersion = 36` (**Android 16**), `compileSdkVersion = 36`.
- **Application Engineering Flags:** `extractNativeLibs = true`, `largeHeap = true`, `hardwareAccelerated = true`, `supportsRtl = true`, `allowBackup = false`, `appComponentFactory = androidx.core.app.CoreComponentFactory`.
- **Declared Component Topology (267 Components):** `200 Activities`, `2 Activity-Aliases`, `34 Services`, `22 BroadcastReceivers`, and `9 ContentProviders`, plus `27 <meta-data>` entries and `<queries>` covering 10 packages, 17 intents, and 1 provider.
- **Multi-Process Mini-App Isolation:** Seven distinct auxiliary processes are declared, centered on a 5-slot lightweight worker pool **`:lite1`–`:lite5`** (mirroring classic super-app mini-program process pooling for memory isolation and fast warm starts).
- **Permission Taxonomy (49 Permissions):**
  - *By Protection Class:* 18 Normal/Platform, 13 Runtime (Dangerous), 5 OEM Vendor Integration (Samsung, Huawei, BBK/Vivo), 4 Google/Play Ecosystem (including Android Privacy Sandbox `ACCESS_ADSERVICES_*`), 4 Third-Party/Custom, 3 Special/Restricted (`QUERY_ALL_PACKAGES`, `DETECT_SCREEN_CAPTURE`, `MANAGE_OVERLAY_PERMISSION`), 2 First-Party/VNG Ecosystem.
  - *By Functional Domain:* Launcher/Shortcuts/UI (8), Attribution & Privacy Sandbox (7), Networking & Connectivity (6), Push & Background Lifecycle (6), Social P2P & Bill Reminders (4), Super-App Discovery (4), eKYC/QR/Audio (3), Identity/Biometrics/Anti-Screen-Capture (3), Geolocation (3), Media/Storage (3), NFC & Payments (2).
- **Hardware Matrix:** Only OpenGL ES 2.0 (`0x20000`) and `camera.any` are marked `required=true`; NFC, NFC HCE (Host Card Emulation), GPS, BLE, and camera autofocus are gracefully optional (`required=false`).

---

### 2.3 RQ3 — Multi-DEX Architecture, Software Composition (SCA) & R8 Minification

Across **20 DEX files (69.70 MB uncompressed)**, ZaloPay defines **63,827 classes** and **287,480 methods** (with **60.92%** of class descriptors showing R8/ProGuard identifier minification):

1. **Bimodal Multi-DEX Partitioning ("Heavy Core + 12-Shard Micro-DEX Long Tail"):**
   - **Primary Core Shards (`classes.dex`, `classes2.dex`, `classes4.dex`–`classes7.dex`):** 6 massive shards totaling **60.54 MB** and **55,976 classes**, with `classes.dex` (91.5%), `classes4.dex` (91.5%), and `classes7.dex` (exact 60,000 `method_ids`, 91.5%) tightly packing Android's 65,536-method index ceiling.
   - **Long-Tail Micro-DEX Shards (`classes3.dex`, `classes8.dex`–`classes19.dex`):** 13 small shards ranging from **0.02 MB** (`classes16.dex`: 20 classes, 101 method refs, 0.1% utilization) to **1.22 MB** (`classes9.dex`), overwhelmingly R8-minified (82.6%–99.7%), reflecting modular/feature-isolated compilation or post-shrinking shard layout.
   - **Dynamically Embedded Legacy DEX (`assets/audience_network.dex`):** A **4.86 MB** secondary DEX encoded in **legacy DEX `v035`** (all 19 root shards use **DEX `v038`**), containing 3,156 classes and 22,955 defined methods (Meta Audience Network runtime loader).

2. **Software Composition Analysis (Top Unminified Ecosystem Pillars):**
   Building on Li et al. (ICSE '17) [li2017libd], Ma et al. (ICSE '16) [ma2016libradar], and Derr et al. (CCS '17) [derr2017keepmeupdated], namespace classification reveals a layered fintech super-app stack:
   - **Google Play Services / Firebase / ML Kit (`com.google.*`):** 7,761 classes
   - **Ant International / Alipay+ Griver Mini-App Stack (`com.alibaba.*` 4,821 + `com.iap.*` 1,599 + `com.alipay.*` 493):** **6,913 classes** — directly corroborating `:lite1`–`:lite5` and `assets/Griver/`
   - **BouncyCastle Cryptography (`org.bouncycastle.*`):** 6,112 classes
   - **AndroidX / Jetpack & Material (`androidx.*`):** 4,543 classes across **131 versioned AndroidX artifacts** in `META-INF/*.version` (including **Jetpack Compose** 504 classes, **Media3** 1,198, **CameraX 1.4.0** 813, **Biometric `1.2.0-alpha05`**, **Dagger 2.59.2**, **Kotlin Coroutines 1.8.1**)
   - **Meta / Facebook & Fresco (`com.facebook.*`):** 3,622 classes
   - **Global & Vietnamese Financial / Identity Standards:** Visa EMV/Card (`com.visa.*`: 1,370), VNPay QR (`com.vnpay.*`: 388), Nimbus JOSE/JWT (`com.nimbusds.*`: 361), **ICAO 9303 eID/CCCD NFC reader (`org.jmrtd.*`: 237)**, AppsFlyer (`com.appsflyer.*`: 407)
   - **First-Party Unminified ZaloPay Core (`vn.com.vng.zalopay.*`):** 1,233 preserved entrypoints/models (with the majority of first-party classes residing in the 60.92% R8-minified pool).

---

### 2.4 RQ4 — Native ELF Binary Archaeology & Android 16 (16 KB Page-Size) Transition

All **27 native `.so` binaries (57.45 MB uncompressed; 24.81 MB compressed; 59.47 MB virtual memory)** reside in `config.armeabi_v7a.apk`:

1. **Functional Breakdown of Native Code:**
   - **On-Device AI / ML / Computer Vision (7 libs, 27.48 MB — 47.8% of native code):** `libmlkitcommonpipeline.so` (6.93 MB), `libmlkit_google_ocr_pipeline.so` (6.78 MB), `libface_detector_v2_jni.so` (5.39 MB), `libbarhopper_v3.so` (3.24 MB), `libpicodet_ncnn.so` (2.99 MB — Tencent NCNN + PicoDet), `libVFaceLib.so` (1.96 MB), `librcmefqonnx.so` (0.18 MB — ONNX runtime).
   - **Domain / Vendor / Application Modules (10 libs, 21.33 MB — 37.1%).**
   - **Media, Graphics & Interactive Animation (4 libs, 3.59 MB — 6.3%):** led by **`librive-android.so` (3.23 MB)** (Rive real-time C++ vector animation engine) and Fresco's `libnative-imagetranscoder.so` (0.34 MB).
   - **Embedded Encrypted Storage (1 lib, 3.56 MB — 6.2%):** **`libsqlcipher.so`** (SQLCipher).
   - **C++ Runtime (1 lib, 0.87 MB):** `libc++_shared.so`; **Observability (4 libs, 0.63 MB):** Firebase Crashlytics native handlers.

2. **8-Year Compiler Toolchain Stratification (ELF `.comment` Forensics):**
   A single production release integrates native artifacts compiled across **five major LLVM Clang generations spanning 8 years**:
   - **Clang 7.0.2** (NDK r19, ~2018): 1 binary
   - **Clang 14.0.6 / 14.0.7** (NDK r25, 2022): 12 binaries
   - **Clang 17.0.2** (NDK r26, 2023): 1 binary
   - **Clang 18.0.1 / 18.0.2 / 18.0.3** (NDK r27, 2024): 6 binaries (with `+pgo, +bolt, +lto, +mlgo`)
   - **Clang 21.0.0** (NDK r29, 2025–2026): 2 binaries
   - **Stripped / Unknown `.comment`**: 5 binaries

3. **Android 16 (`targetSdk=36`) 16 KB `PT_LOAD` Alignment Readiness:**
   Effective November 1, 2025, Google Play requires 64-bit apps targeting Android 15+ to support **16 KB memory page sizes** [android2025page16k]. Although this APKPure capture packages the 32-bit `armeabi-v7a` split (where Android kernels remain 4 KB or run compat translation), our ELF program-header audit reveals a striking supply-chain divergence:
   - **20 of 27 libraries (74.07%)** already exhibit `PT_LOAD p_align = 16,384 bytes`, demonstrating first-party and actively maintained SDKs (SQLCipher, NCNN, Crashlytics, Fresco, AndroidX) have migrated to NDK r27/r28+ 16 KB link flags.
   - **7 of 27 libraries (25.93%)** remain at `4,096 bytes`: all **4 prebuilt Google ML Kit binaries** (`libmlkitcommonpipeline`, `libmlkit_google_ocr_pipeline`, `libface_detector_v2_jni`, `libbarhopper_v3`), **`librive-android.so`**, **`libc++_shared.so`**, and **`libsecurity_lite.so`**—highlighting how prebuilt third-party SDK binaries lag behind host-app toolchain migrations [derr2017keepmeupdated].

---

### 2.5 RQ5 — On-Device AI Stratification, Resource Engineering & Quantified Bloat

1. **One Quarter of the App is On-Device AI/CV (49.06 MB = 25.12%):**
   Extending Xu et al. (WWW '19) [xu2019dlsmartphones] and Huang et al. (ICSE-SEIP '21) [huang2021robustness], ZaloPay illustrates how modern eKYC, QR payment, and document-capture requirements turn fintech wallets into heterogeneous edge-AI platforms:
   - **2001 Era:** `res/raw/haarcascade_frontalface_default.xml` (1.79 MB) — Viola–Jones Haar cascade [viola2001rapid]
   - **2014 Era:** `res/raw/lbfmodel.yaml` (3.78 MB) — Ren et al. Local Binary Features face landmarking [ren2014face]
   - **2019–2024 Era:** 20 TensorFlow Lite / FlatBuffer models (12.23 MB) + 4 ML Kit native engines (22.34 MB) covering BlazeFace, 8-bit FSSD face detection (RGB & grayscale), Taser RPN MobileNetV2 OCR + CTC LSTM recognition, SSD MobileNetV1 barcode scanning, and custom `object_labeler.tflite` (3.77 MB)
   - **2022–2026 Era:** `assets/FastestDet.ncnn.bin` (2.42 MB) + `libpicodet_ncnn.so` (2.99 MB) + `libVFaceLib.so` (1.96 MB) + `librcmefqonnx.so` (0.18 MB)

2. **Resource Architecture (`resources.arsc` & UI Assets):**
   - Base `resources.arsc` (1.80 MB) indexes **21,020 resource IDs** (`4,434 drawables`, `4,324 strings`, `3,460 IDs`, `2,017 XML layouts`, `1,912 dimens`, `1,411 colors`, `1,186 styles`) across 78 configuration chunks, with **`night_mode_type_chunks = 0`** (dark mode is either unimplemented via Android `-night` resource qualifiers or managed dynamically at runtime).
   - **Multi-Engine Motion Stack:** **44 Lottie JSON animations (5.39 MB; avg 6.34 layers, 3.1s)** coexist alongside **Rive (`librive-android.so`, 3.23 MB)** and **5 legacy GIFs (0.22 MB)**.
   - **Image Codec Gap:** **269 PNGs (7.59 MB, avg 28.2 KB)** outweigh **159 WebPs (1.92 MB, avg 12.1 KB)** by nearly 4× in bytes, while **316 binary VectorDrawables** consume just **0.26 MB (avg 0.8 KB)**—contrasted starkly with **2 raw SVGs (`required_kyc.svg` 1.44 MB, `phone_pending.svg` 1.12 MB = 2.55 MB)**!
   - **Typography Overload:** **25 bundled `.ttf`/`.otf` fonts (7.41 MB)** including Apple's `SF Compact Rounded` (3.57 MB), `SF Pro`, `SF UI`, `Inter`, `Noto Sans`, `zalopay.ttf`, `ocr-a-tribute.ttf`, and **5 duplicated Roboto files** split across `res/font/` and `assets/fonts/`.

3. **Zero-Functionality-Loss Bloat Reduction Matrix (17.04 MB Reclaimable):**

| Optimization Opportunity | Current Size (MB) | Est. Reclaimable (MB) | Engineering Root Cause |
| :--- | ---: | ---: | :--- |
| Prune legacy OpenCV Haar + LBF models | 5.57 | 5.57 | Superseded by bundled ML Kit + NCNN CV pipelines |
| Deduplicate 25 bundled font files | 7.41 | 3.71 | Overlapping Roboto/SF/Inter families across `res/` & `assets/` |
| Migrate 269 PNGs to WebP (~45% savings) | 7.59 | 3.42 | Legacy raster PNG pipeline not converted by AAPT2/cwebp |
| Replace 2 multi-MB SVGs with VectorDrawable/WebP | 2.55 | 2.17 | Embedded raster bitmaps inside raw SVG wrappers |
| Prune unreferenced BouncyCastle PQC resources | 1.64 | 1.22 | `org/bouncycastle/pqc/` lookup tables missed by R8 shrinking |
| Deduplicate 496 byte-identical file copies | 0.96 | 0.96 | Identical assets/resources packaged redundantly |
| Remove `junit/runner/` production artifacts | <0.01 | <0.01 | Transitive JUnit JAR resources unexcluded in Gradle `packaging` |
| **Total Conservative Savings** | **25.72** | **17.04** | **8.7% of total uncompressed footprint** |

---

## 3. Threats to Validity

- **Single-ABI XAPK Capture (External Validity):** APKPure's XAPK capture includes `config.armeabi_v7a.apk` (32-bit ARM) rather than `config.arm64_v8a.apk` (64-bit ARM). Consequently, our 16 KB `PT_LOAD` findings measure toolchain adoption within the 32-bit ABI split; 64-bit splits delivered by Google Play to Android 15+/16 devices may exhibit higher 16 KB compliance on third-party SDKs if separate ABI artifacts were rebuilt.
- **Static Structural Scope (Construct Validity):** Package-prefix SCA cannot attribute the 42.23% of classes whose top-level package names were minified by R8/ProGuard into single/two-letter packages (`a.*`, `o.*`, `p9.*`). We explicitly report the minified pool separately rather than imputing ownership.
- **Dynamic Feature / Server-Side Mini-Apps:** Ant International's Griver container downloads additional `.amr` mini-program bundles at runtime; static APK analysis captures only pre-bundled offline mini-app assets (`assets/Griver/`).

---

## 4. Reproducibility & Artifacts

Run the entire deterministic analysis pipeline from the repository root:

```bash
python3 analysis/analyze_archive.py
python3 analysis/build_video_dataset.py
```

Outputs:
- `data/analysis_report.json` — Complete 100%-file-coverage empirical dataset (RQ1–RQ5).
- `data/video_telemetry.json` — Verified, zero-double-counting structured metrics for visual reproduction.
- `paper/references.bib` — Verified BibTeX bibliography.
- `video/` — TypeScript programmatic documentary engine producing the 1080p MP4 visual research paper.

---

## References

- [allix2016androzoo] K. Allix, T. F. Bissyandé, J. Klein, and Y. Le Traon, "AndroZoo: Collecting Millions of Android Apps for the Research Community," *Proc. MSR '16*, ACM, pp. 468–471, 2016.
- [allix2024androzoo] M. Alecci, J. Samhi, K. Allix, T. F. Bissyandé, and J. Klein, "AndroZoo: A Retrospective with a Glimpse into the Future," *Proc. MSR '24*, ACM, 2024.
- [tang2025appbundles] Y. Tang, X. Luo, and Y. Zhou, "A Systematic Study on Real-World Android App Bundles," *IEEE Transactions on Software Engineering*, vol. 51, no. 5, pp. 1538–1553, May 2025.
- [xu2019dlsmartphones] M. Xu et al., "A First Look at Deep Learning Apps on Smartphones," *Proc. WWW '19*, ACM, pp. 2125–2136, 2019.
- [huang2021robustness] Y. Huang, H. Hu, and C. Chen, "Robustness of On-Device Models: Adversarial Attack to Deep Learning Models on Android Apps," *Proc. IEEE/ACM ICSE-SEIP*, pp. 101–110, 2021.
- [li2017libd] M. Li et al., "LibD: Scalable and Precise Third-Party Library Detection in Android Markets," *Proc. IEEE/ACM ICSE*, pp. 335–346, 2017.
- [ma2016libradar] Z. Ma, H. Wang, Y. Guo, and X. Chen, "LibRadar: Fast and Accurate Detection of Third-Party Libraries in Android Apps," *Proc. ACM ICSE '16 Companion*, pp. 653–656, 2016.
- [derr2017keepmeupdated] E. Derr et al., "Keep Me Updated: An Empirical Study of Third-Party Library Updatability on Android," *Proc. ACM CCS '17*, pp. 2187–2200, 2017.
- [viola2001rapid] P. Viola and M. Jones, "Rapid Object Detection Using a Boosted Cascade of Simple Features," *Proc. IEEE CVPR*, 2001.
- [ren2014face] S. Ren et al., "Face Alignment at 3000 FPS via Regressing Local Binary Features," *Proc. IEEE CVPR*, pp. 1685–1692, 2014.
- [android2025page16k] Android Developers, "Support 16 KB Page Sizes on Android 15+ Devices," Google Play Requirement effective Nov 1, 2025.
