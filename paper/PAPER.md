# Anatomy of a Vietnamese Fintech Super-App: A Whole-Bundle Static Study of ZaloPay 11.17.0

**A reproducible, file-complete reverse-engineering methodology for split-APK delivery, applied to `vn.com.vng.zalopay` 11.17.0 (versionCode 1102758)**

---

## Tóm tắt (Vietnamese abstract)

Nghiên cứu này trình bày một phương pháp luận tái lập được để phân tích tĩnh **toàn bộ** gói phát hành của một ứng dụng fintech đang vận hành thương mại: ZaloPay 11.17.0, đóng gói dưới dạng XAPK v2 gồm 5 APK split (~105,6 MB nén, ~195,4 MB sau giải nén). Chúng tôi kiểm kê **từng tệp** trong gói (6.466 mục, trong đó 20 tệp DEX, 27 thư viện native, 5.315 tài nguyên XML nhị phân), xác minh tính toàn vẹn ở ba tầng (ZIP → APK split → từng DEX với Adler-32 và SHA-1), và xây dựng một **đồ thị hệ thống đa tầng** nối mã Java/Kotlin, thư viện native, asset và cấu hình thành 8 phân hệ chức năng có định lượng. Kết quả chính: (i) 45,3 % lớp nằm trong không gian tên đã bị R8 di trú, nhưng chỉ **1,65 %** lớp thuộc mã nội bộ ZaloPay — mô hình "super-app tích hợp SDK"; (ii) một bộ 7 AID EMV được đăng ký cho card emulation NFC với `requireDeviceUnlock="false"`, bao gồm PPSE `2PAY.SYS.DDF01`; (iii) cấu hình bảo mật mạng cho phép **cleartext HTTP toàn ứng dụng** và không có pin-set nào; (iv) 30 thành phần được export, 22 trong số đó không có permission bảo vệ, bao gồm 4 activity chỉ dùng cho test/tooling trong bản release. Toàn bộ pipeline chạy lại được trong **30 giây** chỉ với Python, không cần JVM, và mọi khẳng định đều truy vết được tới tệp + offset. Chúng tôi công bố kèm một **sổ rủi ro 12 mục** với ánh xạ MASVS/MSTG/PCI-DSS và, với mỗi mục, phép thực nghiệm có thể bác bỏ nó.

---

## Abstract (English)

We present a reproducible methodology for whole-bundle static analysis of a production fintech application, and apply it end-to-end to ZaloPay 11.17.0 — Vietnam's highest-grossing e-wallet — delivered as a five-way split XAPK. Unlike per-file "list what is inside" inventories, this study treats the bundle as a *layered system*: every one of its 6,466 files is fingerprinted (SHA-256, Shannon entropy, magic-vs-extension agreement), every DEX is structurally parsed and integrity-verified, every native library is disassembled to a capability vector, and every asset is either identified or explicitly declared opaque. We then join these four evidence layers into eight quantified subsystems and a 12-entry risk register in which each finding names the experiment that would confirm or refute it.

The contribution is threefold. **Methodologically**, we show that a complete audit is achievable without a JVM, without dynamic instrumentation, and in 30 seconds of CPU time — which makes whole-bundle analysis cheap enough to be a *release gate* rather than a one-off investigation. **Empirically**, we quantify the anatomy of a super-app: 63,827 classes over 20 DEX files, of which only 1.65 % is first-party code; 6,112 Bouncy Castle classes including the post-quantum package; 4,821 Alibaba Griver/ARiver classes forming a mini-app runtime that ships 1.67 MB of JavaScript inside `.amr`-named archives; and 17 on-device ML models spanning OCR, barcode, object detection and face liveness, alongside a 2001-era OpenCV Haar cascade retained in `res/raw`. **Practically**, we report four platform-level observations that a static release gate would have surfaced: an NFC HCE payment service registered with `requireDeviceUnlock="false"` and a catch-all Visa AID prefix; a network security config that permits cleartext app-wide with zero pin-sets; 22 unguarded exported components including four test/tooling activities; and a pinned RSA-2048 key that does not authenticate the config payloads shipped beside it.

All work is static. No device was instrumented, no traffic was intercepted, no secret was extracted, and no protection was bypassed — a constraint we treat as a validity requirement rather than a limitation, and which the target's own anti-analysis posture in `libtrueidlib.so` makes practically necessary.

**Keywords:** Android reverse engineering, split APK, XAPK, static analysis, fintech security, eKYC, EMV HCE, software bill of materials, reproducibility, supply-chain composition.

---

## 1. Introduction

### 1.1 Why whole-bundle, and why reproducible

Android security review usually samples. An analyst unzips an APK, runs a decompiler, greps for a handful of patterns, and writes up what they found. That is adequate for triage and inadequate for measurement: sampling cannot answer "what fraction of this binary is first-party code", "which native library actually touches the KYC material", or "is there any file in this bundle that nobody can identify". Those are the questions that matter when the artefact is a payment instrument.

The publication vehicle also matters for reproducibility. A study that ships only prose is unfalsifiable. This study ships:

* the exact input (the 98,813,878-byte APKPure ZIP, tracked in the repository),
* the exact pipeline (seven numbered scripts, `re/run_all.py`),
* the exact outputs (machine-readable JSON/CSV under `data/`),
* a run manifest recording software versions, per-stage wall-clock time and output digests.

A reader can therefore re-run the study, diff the numbers, and locate any disagreement to a stage.

### 1.2 The target

| Property | Value |
|---|---|
| Bundle | `Zalopay-Thanh toán & Tài chính_11.17.0_APKPure.zip` → XAPK v2 |
| Package | `vn.com.vng.zalopay` |
| Version | 11.17.0 (versionCode 1102758) |
| SDK range | minSdk 26 (Android 8.0) → targetSdk 36 (Android 16) |
| Splits | `base`, `config.armeabi_v7a`, `config.en`, `config.vi`, `config.xhdpi` |
| Declared size | 105,580,620 B |
| Measured | 104,504,463 B compressed / 195,353,860 B uncompressed |
| Signer (SHA-256) | `59dd31fd…92e8cdae`, CN-only subject, valid 2016-04-21 → 2041-04-15 |
| Signing | APK Signature Scheme v2 + v3, verity padding, Play SourceStamp v2 |
| Build provenance | Android Gradle Plugin 8.12.3 |

ZaloPay is operated by VNG Corporation and is one of Vietnam's two dominant e-wallets. It supports wallet balances, bank-card binding over NAPAS/VietQR, BNPL and instalments, a mini-app platform, and biometric eKYC for account opening and tier upgrades. That breadth is exactly why a whole-bundle study is interesting: the app is a *marketplace of trust boundaries*, and the boundaries are visible in the file tree if you look at all of it.

### 1.3 Prior work and relation to it

Static Android analysis has a long tooling lineage — `apktool` (resource decoding), `jadx` (decompilation), `androguard` (programmatic DEX/resource access), MobSF (automated triage), and the `morluto/rea` suite of reverse-engineering helpers. Composition studies of the "who wrote this app" question exist for app stores at population scale. What is comparatively rare is a *file-complete* study that (a) accounts for every entry rather than a sample, (b) verifies integrity at every layer, and (c) publishes a pipeline that reproduces the numbers. This paper is offered as that artefact for one app version.

Two open pull requests in the same repository (PR #2, PR #3) previously produced an inventory and a prose report for this same bundle. This study was written after them, and its numbers differ from theirs because the measurement is different: they counted 60,671 classes over 19 DEX files, whereas the file-complete census here finds **63,827 classes over 20 DEX files**, including `assets/audience_network.dex` (3,156 classes, 4.9 MB) which sits outside the `classes*.dex` namespace and is therefore invisible to a glob on `classes*.dex`. We state this explicitly because the discrepancy is itself a finding: *the obvious sampling heuristic misses 5 % of the code.*

---

## 2. Methodology

### 2.1 Layer model

The bundle is analysed as five nested layers, each verified before the next is trusted.

```
L0  ZIP container        1 file     →  XAPK
L1  XAPK bundle          6 files    →  split APKs + descriptor + icon
L2  split APK            5 ZIPs     →  6,466 entries
L3  code                20 DEX      →  63,827 classes
L4  native              27 ELF      →  284 JNI exports
L5  data                assets/res  →  17 ML models, 56-section config
```

Integrity is asserted at each boundary. L0→L2 is checked by SHA-256 of every split against `data/unpack.json`; L3 by each DEX header's own Adler-32 (`bytes[12:]`) and SHA-1 (`bytes[32:]`); L4 by ELF header sanity plus a GNU build-id on all 27 objects; L5 by magic-vs-extension agreement on all 6,466 entries, with the four disagreements individually adjudicated (§4.1).

### 2.2 Tools, and why they are these tools

The environment provides Python 3.11, Node.js 22 and a static FFmpeg build, and **no JVM**. Rather than installing a JDK to run `apktool`/`jadx`, we implemented the DEX reader directly (`re/dex.py`, 470 lines, mmap + `struct`). This is not merely a workaround; it is a measurement decision:

* a full class-model build in androguard 4.x peaks above 2.5 GB RSS on the 16.5 MB `classes2.dex`, against a 3 GB budget;
* the census only needs *structurally exact* header and table facts, which a bounds-checked reader provides at 25 MB/s;
* a 470-line reader is auditable, which matters when the paper's numbers rest on it.

`androguard` is retained for what it does better: AXML and `resources.arsc` decoding, and PKCS#7 signer extraction. A separate `re/arsc.py` reader handles the resource table because we need package/type/locale facts, not resolved values.

### 2.3 The seven stages

| Stage | Script | Output | Time |
|---|---|---|---|
| 00 | `re/00_unpack.py` | `data/unpack.json` — split digests, entry counts | 2.8 s |
| 01 | `re/01_census.py` | `inventory.csv`, `census.json`, `native.csv`, `secrets.json` | 16.1 s |
| 02 | `re/02_dex.py` | `dex.json` — 20-DEX structure, owners, obfuscation, indicators | 6.6 s |
| 03 | `re/03_sbom.py` | `sbom.json/.csv` — 141 Jetpack components, provenance, SPI | 0.1 s |
| 04 | `re/04_assets.py` | `assets.json`, `keys.json`, `miniapps.json` | 0.7 s |
| 05 | `re/05_native.py` | `native.json` — per-library capability vector | 4.1 s |
| 06 | `re/06_surface.py` | `surface.json`, `correlation.json`, `risk.json`, `summary.json` | 0.2 s |
| | **total** | | **30.4 s** |

### 2.4 Measurement definitions

Precision here depends on definitions being stated, so:

**Owner attribution.** A class is attributed to the longest matching vendor prefix in a 60-entry table (`vn/com/vng/` → ZaloPay, `org/bouncycastle/` → Bouncy Castle, …). If no prefix matches and the root package is a non-dictionary token of 1–3 characters, it is attributed to `R8-obfuscated (relocated)`. Real 1–2 letter namespaces (`com`, `io`, `me`) are excluded from that pool by an explicit vocabulary, because `com.example.Foo` legitimately puts `com` at depth 1 — an unguarded version of this metric over-reports obfuscation (we measured 91.4 % with the naive rule versus 45.7 % with the guarded one, and only the guarded figure is reported).

**Entropy.** Shannon entropy over the byte histogram of the first 4 MiB (whole file when smaller). It distinguishes code (~6.0–6.6 bits/byte), compressed media (~7.9–8.0) and plaintext (~4.1 for the 5,315 binary XML resources).

**Obfuscation.** Reported as the share of classes whose root package is a relocated token (45.7 %), plus the R8-mandated presence/absence of debug attributes: `source_file` survives for only 12 distinct values across 63,827 classes, 52,713 of which carry none.

**Risk severity.** `high` = a platform-level control genuinely weakened in the shipped artefact; `medium` = a control deferred to application code with a plausible bypass path; `low` = build hygiene; `info` = a measurement that has no defect character but that a defender should know.

### 2.5 Validity and ethics

This is a fully static study of a publicly distributed, signed, production artefact. It does not decrypt user data, does not extract keys (three public keys are reported, which is what a public key *is*), does not bypass any protection, and does not include exploit payloads. Where a finding has a security-relevant edge (HCE unlock policy, cleartext permission), the report states the *mechanism* and the *confirmation experiment* rather than a procedure. The target's own defences make the alternative impractical anyway: `libtrueidlib.so` contains probes for `frida-agent`, `re.frida.server`, `TracerPid`, `de.robv.android.xposed` and eight Magisk paths, and the eKYC path additionally calls `vaultCheckIntegrity`/`vaultCheckDebugger` (§4.5).

---

## 3. Bundle composition

### 3.1 The file census

All 6,466 entries were fingerprinted. 5,970 are byte-distinct; 299 duplicate groups carry 795 entries and an estimated 1.28 MB of redundancy, of which 134 entries are *tautological* — 129 AndroidX `.version` markers (one per module, all byte-identical by construction) and the 5 copies of the Play SourceStamp digest, which is *supposed* to be identical across splits. Cross-split deduplication is structurally impossible: each split owns a separate ZIP namespace.

Composition by category, largest first:

| Category | Files | Uncompressed | Share |
|---|---:|---:|---:|
| DEX code | 20 | 69.7 MB | 35.7 % |
| Native ELF | 27 | 57.5 MB | 29.4 % |
| Other resources | 55 | 11.5 MB | 5.9 % |
| Binary XML | 5,315 | 11.1 MB | 5.7 % |
| ML models | 30 | 10.4 MB | 5.3 % |
| Images | 473 | 9.7 MB | 5.0 % |
| Fonts | 13 + 12 | 7.4 MB | 3.8 % |
| Archives | 1 | 3.9 MB | 2.0 % |
| NCNN models | 2 | 2.4 MB | 1.2 % |
| `resources.arsc` | 4 | 2.1 MB | 1.1 % |
| Config JSON | 17 | 1.8 MB | 0.9 % |
| Build metadata | 231 | 1.4 MB | 0.7 % |
| Opaque `.dat` | 3 | 0.8 MB | 0.4 % |
| Everything else | 264 | 8.9 MB | 4.6 % |

**Reading:** code and native code are 65 % of the uncompressed bundle; *resources* are only 13 %. This is the opposite of a typical consumer app and is the first quantitative signal of what the app actually is — an engine, not a screen.

### 3.2 Zip forensics

The base split's central directory sits at offset 78,815,232 with a 543,833-byte directory and zero trailing bytes. Immediately before it lies an **8,184-byte APK Signing Block**, whose leading and trailing size fields agree (a consistency check that a naive tail-scan fails: searching the last 4 KiB for `APK Sig Block 42` returns nothing, because the magic sits 8,184 bytes back). Inside the block:

| Pair | Payload bytes |
|---|---:|
| APK Signature Scheme **v2** (`0x7109871a`) | 1,379 |
| APK Signature Scheme **v3** (`0xf05368c0`) | 1,379 |
| Play **SourceStamp v2** (`0x6dff800d`) | 3,057 |
| `<x>…4e` (`0x2146444e`) | 687 |
| verity padding | 1,598 |

The unknown pair is a protobuf with field 1 = 1 and a 64-byte value — consistent with a per-split digest. We report it as unidentified rather than guessing.

**Signer consistency.** All five splits carry exactly one signer fingerprint, `59dd31fd…92e8cdae`, extracted independently from each split's v3 block. This is the check that matters for split delivery: a split with the same `package` but a different signer is the split-injection attack class, and it is clean here. The certificate is self-signed, CN-only (`CN=Vu Manh Hieu`), issued 2016-04-21 and expiring 2041-04-15 — a 24.98-year validity, which is normal for an Android app identity and would be indefensible for a TLS certificate (R-06).

### 3.3 Dynamic-feature topology

`resources.arsc` contains **13 packages**: `vn.com.vng.zalopay` (122,610 entries) plus twelve feature modules that are independently addressable resource namespaces: `changepin`, `resetpin`, `login_v2`, `cashier`, `bank_binding`, `updatephone`, `cash_in`, `resultpage`, `withdraw`, `cashier_v3`, `result_page_v3`, `liveness`. This is Android Dynamic Feature / conditional-delivery structure, and it tells us where the product's own engineers drew module boundaries: **the payment-flow modules are separate deployable units**, with `liveness` — the biometric gate — as its own module containing 51 entries and, per the DEX, only one class in the base.

---

## 4. Code layer

### 4.1 Twenty DEX files, not nineteen

The 20 DEX files total 69,701,748 bytes and 63,827 classes. Every one verifies its own Adler-32 and SHA-1. The largest are not the most numerous:

| DEX | Bytes | Classes | Methods |
|---|---:|---:|---:|
| `classes2.dex` | 16.5 MB | 7,421 | 45,527 |
| `classes7.dex` | 10.8 MB | 12,953 | 60,000 |
| `classes4.dex` | 9.4 MB | 11,441 | 59,997 |
| `classes.dex` | 8.8 MB | 10,703 | 59,948 |
| `classes6.dex` | 7.6 MB | 5,475 | 49,201 |
| `classes5.dex` | 7.4 MB | 7,983 | 49,055 |
| `assets/audience_network.dex` | 4.9 MB | 3,156 | 26,792 |
| 13 × `classes{8..19,3}.dex` | ≤1.2 MB each | 4,795 | 23,338 |

Two observations. First, `classes2.dex` has few classes but the most methods: **45,527 methods for 7,421 classes (6.1 per class)** against `classes7.dex`'s 4.6 — a sign of large generated or inherited hierarchies (the Visa CBP classes below confirm it). Second, `assets/audience_network.dex` is a *secondary* DEX shipped as an asset and loaded at runtime, which is how Meta's Audience Network SDK dodges the 64 K method-reference limit. It is 5 % of the code and it is invisible to `glob("classes*.dex")` — the single most consequential sampling error available in this bundle.

Program totals: **287,480 methods, 286,224 fields, 39.3 MB of bytecode, 59,068 try/catch ranges, 2,026 native methods across 405 classes, 11,251 interfaces, 1,558 enums, 4,029 synthetic classes, 7,856 inner classes.**

Two classes deserve a note because they would confuse any naive size heuristic: `Lcom/visa/cbp/setEmail;` (636 KB, 60 methods, 1,383 try ranges) and `Lcom/visa/cbp/getLastName;` (597 KB, 50 methods, 1,703 try ranges). They are not hand-written monsters; they are **protocol marshallers** — one method per field with a full try/catch per branch — which is what a card-data SDK looks like after code generation. Together they are 1.23 MB, 3 % of all bytecode, in two classes.

### 4.2 Who wrote this app

This is the study's headline number. Attribution over all 63,827 classes:

| Owner | Classes | Share |
|---|---:|---:|
| R8-relocated (first party + some third party, renamed) | 28,915 | 45.30 % |
| Google (AndroidX / GMS / Firebase) | 7,761 | 12.16 % |
| Bouncy Castle | 6,112 | 9.58 % |
| Alibaba (Griver / ARiver) | 4,821 | 7.55 % |
| AndroidX | 4,250 | 6.66 % |
| Meta SDK | 3,622 | 5.67 % |
| Alipay IAPConnect | 1,599 | 2.51 % |
| Visa CBP | 1,370 | 2.15 % |
| **ZaloPay first-party** | **1,053** | **1.65 %** |
| TrueID eKYC | 622 | 0.97 % |
| Alipay | 493 | 0.77 % |
| AppsFlyer | 430 | 0.67 % |
| VNPAY | 388 | 0.61 % |
| Kotlin stdlib | 378 | 0.59 % |
| Nimbus JOSE+JWT | 361 | 0.57 % |
| OkHttp | 335 | 0.52 % |

**Interpretation.** Only 1,053 classes — 1.65 % — still live under `vn/com/vng/…`. Everything else is either a named third-party SDK or relocated into the 28,915-class R8 pool. ZaloPay is therefore an **integration product**: its differentiators are configuration, server contracts, orchestration and UX, not code volume. Whatever security property the app has is largely a property of the components it assembled — which is precisely why a software bill of materials (§5) is the right lens, and why the risk register is mostly about *policies* rather than *algorithms*.

The largest non-first-party populations are instructive in their own right: 6,112 Bouncy Castle classes (9.58 % of the entire app is a cryptography library — §4.4) and 4,821 Alibaba classes forming the mini-app runtime (§4.3).

### 4.3 The mini-app runtime, and a filename that lies

4,821 Alibaba Griver/ARiver classes plus 1,599 IAPConnect classes plus 493 Alipay classes — **6,913 classes, 10.8 %** — implement an Alipay-derived mini-app container inside a Vietnamese wallet. That runtime needs content, and the content is in `assets/Griver/` under names like `68687209_2.9.7.8.amr`.

An `.amr` file is an Adaptive Multi-Rate audio codec stream. These are not. All four Griver entries begin `PK\x03\x04` and open as ZIPs whose members are `Manifest.xml`, `CERT.json` and an inner `.tar`:

| Container | Reported | Actual members | Inner payload | Framework |
|---|---:|---:|---:|---|
| `68687209_2.9.7.8.amr` | 856,974 B | 14 | 3.29 MB | `appx-ng` (modern) |
| `66666692_1.24.8.amr` | 287,535 B | 11 | 0.97 MB | `appx` (legacy) |
| `66666693_2.0.7.amr` | 17,380 B | 7 | 0.06 MB | flat package |

The AppX-NG bundle carries `af-appx.min.js` (1.67 MB), `af-appx.worker.min.js` (1.37 MB), `appx-portal.min.js`, `web-view.min.js` and a `security-patch.min.js` — a complete JavaScript application runtime. `CERT.json` holds only SHA-style digests of the members, not signatures of them.

This is the classic **extension-disguise** pattern: the `.amr` extension is presumably a build-tool artifact or an intentional filename filter, and it means an `assets/**.amr` deny-list or a media-type classifier will not see the JavaScript. A release gate that keys on extension would pass this bundle; the magic-byte check in stage 01 is what caught it (it is one of the four magic-vs-extension disagreements, all four of which were true positives).

### 4.4 Cryptography

The crypto surface is broad and, at the algorithm level, healthy:

* **Bouncy Castle, complete** — `org/bouncycastle/jcajce` (1,758 classes), `crypto` (836), `asn1`, `pkix`, and **`pqc`** (post-quantum: 975 classes across Kyber/NewHope/NTRU/Picnic/Falcon etc.). The PQC tree is real code, not a stub: high-entropy `lowmcL3/L5.bin.properties` tables for Picnic's LowMC instances sit in the APK at 350 KB and 752 KB.
* **Two JCA providers are registered via SPI** — `org.bouncycastle.jce.provider.BouncyCastleProvider` and `org.bouncycastle.pqc.jcajce.provider.BouncyCastlePQCProvider`, listed in `META-INF/services/java.security.Provider`. A JCA provider registration is an *activation*, not a dependency: BC is installed into the runtime, so any `Cipher.getInstance(...)` anywhere in the app can resolve to it.
* **`com/nimbusds/jose`** (361 classes) — JOSE/JWT/JWS/JWE.
* **SQLCipher** (`libsqlcipher.so`, 3.56 MB, 169 `sqlcipher` strings, 325 `sqlite3_` symbols) built against **OpenSSL 3.0.16** — and carrying the Chinese SM national algorithms: `SM4-CBC` with OID `1.2.156.10197.1.104.2`, SM4-CFB variants, 27 SM2/SM3/SM4 references. This is the standard Beijing-compliant OpenSSL fork bundled inside SQLCipher; it is inert unless called, but it is present on Vietnamese payment infrastructure.
* **String-level suites** mined from the DEX literal pool: `AES/GCM/NoPadding`, `AES/CBC/PKCS5Padding`, `HmacSHA256`, `Ed25519`, `secp256r1`.

The one genuinely uncomfortable item is `AES/CBC/PKCS5Padding` appearing as a literal *with* its own diagnostic strings (`AES/GCM/NoPadding decryption failed:`, `Couldn't create AES/GCM/NoPadding cipher:`). In the same binary, GCM and CBC-with-PKCS#5 coexist. CBC without a MAC is malleable and is the classic padding-oracle substrate; whether that path is reachable for attacker-influenced data is not statically decidable, and we do not claim it is (R-12, info).

### 4.5 Anti-analysis, and what it forces

Probes recovered from literals and from native rodata:

| Probe family | Evidence |
|---|---|
| Root | `/system/bin/su`, `/system/xbin/su`, `/su/bin/`, `/system/app/Superuser.apk`, `magisk`, `test-keys`, `ro.debuggable` |
| Emulator | `goldfish`, `ranchu`, `ro.kernel.qemu`, `Genymotion`, `isEmulator`, plus a **scored** detector (`buildPropsMatch isEmulator: strong build property indicator matched (+5) current score = `) |
| Hooking | `frida-agent`, `re.frida.server`, `de.robv.android.xposed`, `de.robv.android.xposed.installer` |
| Debugger | `TracerPid`, `/proc/self/maps`, `ptrace` |
| Integrity | SafetyNet (`com/google/android/gms/safetynet`), Visa `DeviceAttestationCallback`/`getVisaIntegrityCertId`, `BShield`, TrueID `vaultCheckIntegrity` |

The scored emulator detector is worth a sentence: it does not answer yes/no, it accumulates a score against named build properties and logs at `+5`. That is a stronger design than a boolean, and it is the kind of detail that only appears when you read literals rather than grep for "root".

`libtrueidlib.so` — 80 KB, 10 JNI exports — is the densest defensive object in the bundle: Frida, Xposed, ptrace, Magisk (8 paths) and integrity probes in 80 KB, wired to `SmartOTPHelper_vaultAuthCeremony`, `vaultCheckIntegrity`, `vaultCheckDebugger`, `vaultComputeQAuth` and `face/opencv/DataSignService` (nonce + signature). The VNG eKYC SDK is doing the right thing. It is also the reason this study is static: instrumenting that library is a fight, and it is a fight the library is designed to win.

---

## 5. Software bill of materials

### 5.1 What can be recovered exactly

Build-time evidence beats inference, and this bundle carries three kinds:

1. **`META-INF/<group>_<artifact>.version`** — 141 Jetpack/AndroidX modules with exact semantic versions, written by AGP (`androidx.core_core-ktx.version` → e.g. `1.16.0`). This is a *precise* SBOM for the AndroidX half.
2. **`META-INF/com/android/build/gradle/app-metadata.properties`** — `androidGradlePluginVersion=8.12.3`, `appMetadataVersion=1.1`. The build toolchain is pinned, which lets a reader correlate obfuscation behaviour with an AGP/R8 generation.
3. **`META-INF/*.properties`** — full implementation metadata where the library ships it. `rxkotlin.properties` yields `Implementation-Title=io.reactivex.rxjava2#rxkotlin;2.2.0`, `Module-Origin=https://github.com/ReactiveX/RxKotlin.git`, `Change=4e5cc2e`, `Built-By=travis`, `Build-Date=2017-11-27`. A 2017 library, still shipping in 2026, with its origin commit recorded inside the APK.

`META-INF/version-control-info.textproto` reports `generate_error_reason: NO_SUPPORTED_VCS_FOUND` — the build could not resolve a VCS revision, so **there is no commit attestation** for the app's own source. That is a real provenance gap for a financial artefact (R-05 adjacent).

### 5.2 What has to be inferred, and how confidently

The remaining components are identified by namespace presence with measured class counts (§4.2) and carry `confidence: namespace` rather than `exact` in `data/sbom.csv`. Namespace identification cannot distinguish a library that is *used* from one that is merely *linked* — a distinction that matters for the tracking SDKs in particular (R-08).

Notable absences are as informative as presences: no Flutter (no `libflutter.so`, no `io/flutter` classes), no React Native, no Realm use, no Bugsnag/Sentry/New Relic, no Huawei HMS in DEX. The app is natively Kotlin/Compose + Java, not cross-platform.

---

## 6. Native layer

### 6.1 Hardening posture

Of 27 ARM32 objects:

| Property | Count | Note |
|---|---:|---|
| PIE (`ET_DYN`) | 27/27 | |
| NX stack (`PT_GNU_STACK` without `X`) | 27/27 | |
| `PT_GNU_RELRO` | 23/27 | the 4 exceptions are small leaf libraries |
| `.symtab` present | 1/27 | `libcrashlytics-trampoline.so`; the rest are stripped |
| `.debug*` sections | 1/27 | |
| GNU build-id | 27/27 | enables binary correlation across versions |
| JNI exports | 284 total | `librive-android.so` alone has 167 |

Compilers recovered from rodata span three toolchains: `clang 12.0.5`, `clang 14.0.6`, and `GCC: (GNU) 4.9.` — the GCC remnant belongs to an older statically-linked component, not to the NDK build.

### 6.2 Attribution by capability vector

Each library gets a vector from three channels — ELF structure, dynamic symbols, rodata — so that a claim like "this library does X" has a symbol or a string behind it:

| Library | Size | JNI | Evidence-backed role |
|---|---:|---:|---|
| `libtaibmcf.so` | 12.62 MB | 0 | static runtime; MLS/SELinux policy strings; **no JNI surface** — largest file after the DEX pool, purpose withheld |
| `libmlkitcommonpipeline.so` | 6.93 MB | 38 | Google ML Kit common pipeline |
| `libmlkit_google_ocr_pipeline.so` | 6.78 MB | 33 | ML Kit OCR (Vietnamese) |
| `libfgcpkjvqe.so` | 6.52 MB | 0 | sibling of `libtaibmcf.so` (near-identical rodata profile) |
| `libface_detector_v2_jni.so` | 5.39 MB | 6 | ML Kit face detection |
| `libsqlcipher.so` | 3.56 MB | 0 | SQLCipher + OpenSSL 3.0.16 + SM2/3/4 |
| `libbarhopper_v3.so` | 3.24 MB | 8 | ML Kit barcode |
| `librive-android.so` | 3.23 MB | 167 | Rive animation runtime (needs `libc++_shared.so`) |
| `libpicodet_ncnn.so` | 2.99 MB | 8 | PicoDet object detection over NCNN (`Java_com_example_picodet_PicoDetJNI_*`) |
| `libVFaceLib.so` | 1.96 MB | 4 | VNG FaceAuth (`vn.ai.faceauth.sdk.opencv.VFaceLib`) |
| `libsecurity_lite.so` | 0.15 MB | 4 | **Alipay SecurityGuard-Lite** |
| `libtrueidlib.so` | 0.08 MB | 10 | VNG TrueID eKYC + SmartOTP |
| `libohxbee.so` | 0.16 MB | 0 | VNG FaceAuth liveness glue (OpenCV + liveness models) |

**`libsecurity_lite.so` is the most security-consequential small file in the bundle.** Four JNI exports — `init`, `encrypt`, `decrypt`, `signRequest` — under `com.alipay_plus_security_lite.SecurityGuardLiteNative`. Every secret the mini-app platform needs to sign with transits this 155 KB object. Its clang build string is present, and its rodata is obfuscation-processed (2 OLLVM-style markers).

`libtaibmcf.so` and `libfgcpkjvqe.so` are 19.1 MB together — one third of all native code — with **zero JNI exports** and near-identical MLS/SELinux rodata. A library with no Java boundary and no exported symbols cannot be called from the app's own Kotlin; it either runs as a static payload behind another library's exports, or it is dead weight. We report the observation and the byte evidence and decline to name a vendor, because the honest answer is "not determinable statically" (contrast: over-claiming here would be the easiest error in this paper to make).

---

## 7. Attack surface

### 7.1 Components

267 declared components: 202 activities, 34 services, 22 receivers, 9 providers; 44 intent filters. **30 are effectively exported; 22 of those carry no permission guard.** Categorised:

| Category | Count | Examples |
|---|---:|---|
| NFC HCE payment endpoints | 3 | `VisaHostApduService`, `ariver`/`griver` `TinyAppHostApduService` |
| Test/tooling stubs in a release build | 4 | `androidx.test.core.app.InstrumentationActivityInvoker{,Empty,EmptyFloating}Activity`, `androidx.compose.ui.tooling.PreviewActivity` |
| Vendor tooling | 1 | `com.google.android.gms.tagmanager.TagManagerPreviewActivity` |
| Launcher / identity entry | 4 | `SplashScreenActivityV2`, `SplashScreenGooglePaymentActivity`, `VerificationByVNeIDActivity`, `QRReceiverActivity` |
| Cross-app entry aliases | 2 | `ZaloAppActivityAlias`, `ThirdPartyAppActivityAlias` |
| Broadcast receivers | 5 | `ZPInstallBroadcastReceiver`, 4 Glance app-widget receivers |

Deep-link schemes reachable from any app or browser: `zalopay`, `zalopay-1`, `zalopay-vnpay`, `zalopay.api.v2`, `fbconnect`, `tagmanager.c.vn.com.vng.zalopay`. `QUERY_ALL_PACKAGES` is declared (a Play-policy-sensitive permission) along with 10 explicit `<queries><package>` entries.

The four exported test activities are the clearest process defect: `androidx.test` and `compose.ui.tooling` are `androidTest`/`debug` dependencies. Their presence means the release manifest was not stripped, and `PreviewActivity` in particular exists to render arbitrary Compose previews. Severity is low (they grant nothing by themselves), but it is a one-line fix and it failed to happen across at least this release.

### 7.2 NFC card emulation — the finding that matters

ZaloPay implements Host Card Emulation. `res/xml/apdu_service.xml`:

```xml
<host-apdu-service android:requireDeviceUnlock="false" ...>
  <aid-group android:category="payment">
    <aid-filter android:name="325041592E5359532E4444463031"/>  <!-- "2PAY.SYS.DDF01" EMV PPSE -->
    <aid-filter android:name="A0000000980840"/>                <!-- private, not in EMV registry -->
    <aid-filter android:name="A0000000032020"/>                <!-- Visa V Pay -->
    <aid-filter android:name="A0000000032010"/>                <!-- Visa Electron -->
    <aid-filter android:name="A0000000033010"/>                <!-- Visa Interlink -->
    <aid-filter android:name="A0000000031010"/>                <!-- Visa classic -->
    <aid-filter android:name="A000000003"/>                    <!-- 3-byte catch-all Visa prefix -->
  </aid-group>
</host-apdu-service>
```

Three things are true here and they compound.

**`requireDeviceUnlock="false"`.** The Android HCE attribute tells the platform whether the device must be unlocked for the service to receive APDUs. `false` means an NFC reader in proximity can SELECT the PPSE and begin a session while the phone is locked. EMVCo's contactless requirements and every major HCE wallet's design put a user-intent barrier (unlock, or an explicit tap-to-pay activation) between the card and the terminal, because otherwise a reader in a bag or a crowd can transact with no user action. This is R-03, the study's only `high`.

**The 3-byte AID `A000000003`.** AID matching is prefix-based. Registering the bare RID beside the five specific AIDs means *any* Visa applet — including ones ZaloPay has not provisioned — resolves to this service. In a well-formed HCE wallet the AID list is exactly the AIDs the app can service.

**`2PAY.SYS.DDF01` (PPSE).** Registering the payment-system environment makes the device answer the standard contactless discovery SELECT, i.e. it announces itself to any terminal as a payment card. That is legitimate for a tap-to-pay wallet and is exactly why the unlock policy has to be right.

We do not claim exploitability. Tokenisation, cryptogram generation, and server-side risk scoring all sit between a SELECT and money moving, and none of them is visible here. What is visible is that a platform control (`requireDeviceUnlock`) was switched off and that the AID list is broader than the wallet's own applets. R-03 names the dynamic experiment that would settle it: present a reader to a locked device and observe whether a session establishes.

### 7.3 Transport

`res/xml/network_security_config.xml`:

```xml
<network-security-config>
  <domain-config><domain includeSubdomains="true">storage.googleapis.com</domain></domain-config>
  <base-config cleartextTrafficPermitted="true">
    <trust-anchors><certificates src="system"/></trust-anchors>
  </base-config>
  <debug-overrides cleartextTrafficPermitted="true">
    <trust-anchors><certificates src="system"/><certificates src="user"/></trust-anchors>
  </debug-overrides>
</network-security-config>
```

Three facts: **cleartext is permitted app-wide**; there is **no `<pin-set>`** and **no `<certificate-transparency>`**; and the only domain-scoped rule is a *grant*, not a restriction. So all transport hardening lives in application code — OkHttp `CertificatePinner` (22 literal references, `Sokhttp3/CertificatePinner`), IAPConnect's `IAPSslPinner`/`SslPinningPlugin`, and TrueID's `TrueIDSSLPinningConfig`. Those are real and the app clearly takes pinning seriously.

The gap is coverage, not intent. A platform-level `cleartextTrafficPermitted="false"` with per-domain pin-sets would make the *default* secure and any deviation a build error. As shipped, the default is permissive and every new HTTP client — a WebView, a native stack inside an SDK, a debug path — is secure only if its author remembers. For an app that runs 6,913 mini-app-runtime classes and 1.67 MB of third-party JavaScript inside a WebView, "remember" is a thin control (R-02).

`debug-overrides` trusting user CAs is correct behaviour (it is the documented way to permit proxy debugging on debuggable builds) and would only matter if `debuggable=true`, which it is not.

### 7.4 Permissions

49 declared: 33 `android.*` permissions plus 16 third-party/OEM ones, and the XAPK descriptor's 49-entry list reconciles exactly with the merged manifest (a clean consistency check, since APKPure descriptors are frequently stale). 16 are `dangerous`, 3 are policy-special:

| Tier | Permissions |
|---|---|
| Dangerous | CAMERA, RECORD_AUDIO, READ/WRITE_CONTACTS, READ/WRITE_CALENDAR, ACCESS_FINE/COARSE_LOCATION, READ_PHONE_STATE, READ_BASIC_PHONE_STATE, READ/WRITE_EXTERNAL_STORAGE, READ_MEDIA_AUDIO, POST_NOTIFICATIONS, USE_BIOMETRIC, USE_FINGERPRINT |
| Policy-special | QUERY_ALL_PACKAGES, DETECT_SCREEN_CAPTURE, MANAGE_OVERLAY_PERMISSION (`android.settings.action.…`) |
| Custom | `vn.com.vng.zalopay.DYNAMIC_RECEIVER_NOT_EXPORTED_PERMISSION` |
| OEM | Samsung `WRITE_USE_APP_FEATURE_SURVEY`, BBK launcher read/write, Huawei appmarket `GET_COMMON_DATA`, Zalo `ACCESS_THIRD_PARTY_APP_AUTHORIZATION` |

The set is coherent with the product: camera+audio for eKYC liveness, contacts+calendar for transfer-by-contact and reminders, location for the taxi module, biometric for payment authorisation, NFC for HCE. `QUERY_ALL_PACKAGES` is the outlier that a reviewer should ask about, since nothing visible here needs universal package enumeration.

### 7.5 Application-level flags

`allowBackup="false"`, `fullBackupContent="false"`, `dataExtractionRules` present, `debuggable` absent, `extractNativeLibs="true"` (so native code is extracted to disk and `mmap`-executed rather than loaded from the APK directly — a small hardening loss, normal for 32-bit), `largeHeap="true"`, `appComponentFactory` set to AndroidX's, `enableOnBackInvokedCallback="false"`.

`requestLegacyExternalStorage="true"` on a **targetSdk 36** build is inert at runtime (ignored from API 30 unless the app opts out of scoped storage through other means) but signals legacy file paths still exist in the code, which is R-01.

---

## 8. On-device machine learning

17 models, 10.4 MB:

| Family | Models | Purpose |
|---|---:|---|
| ML Kit OCR (`mlkit-google-ocr-models/`) | 21 files: `taser` RPN detector (341 KB), `gocr` layout line-clustering + line-splitting custom-op models, `tflite_langid` (316 KB) | Vietnamese/Latin text recognition for CCCD/ID capture |
| ML Kit barcode (`mlkit_barcode_models/`) | `barcode_ssd_mobilenet_v1_dmp25_quant`, `oned_auto_regressor_mobile`, `oned_feature_extractor_mobile` | QR/payment-code scanning |
| ML Kit ODT | `labeler_with_validation` (1.71 MB), `localizer_with_validation` (1.25 MB) | object detection/tracking |
| Face (`models_bundled/`) | 4 × FSSD, 8-bit, gray and colour variants (`fssd_25_8bit_v2`, `fssd_medium_8bit_v5`, +gray) | face detection for liveness |
| Face (NCNN) | `FastestDet.ncnn.{param,bin}` (2.42 MB) | fast single-class detector |
| Object (custom) | `custom_models/object_labeler.tflite` (3.77 MB) | scene labelling |

All TFLite files verify the `TFL3` identifier (bytes 4–8); operator fingerprints are recorded per model in `data/assets.json`, with `QUANTIZE`/`DEQUANTIZE` presence indicating int8 quantisation — consistent with the "mobile" and "quant" suffixes in the filenames.

And then there is `res/raw/haarcascade_frontalface_default.xml` — **1,788,328 bytes of OpenCV Viola-Jones frontal-face cascade**: `<cascade type_id="opencv-cascade-classifier">`, `HAAR` feature type, 24×24 window, 25 stages, 2,913 sampled `<rects>` features, header comment "Stump-based 24x24 discrete(?) adaboost frontal face detector. Created by Rainer Lienhart." That is the *2001* detector, shipping inside a 2026 biometric-KYC pipeline beside four quantised FSSD networks and a 2.4 MB NCNN model. The likely reason is a fallback path for devices where the DNN fails to load, or a legacy code path retained behind a flag. It is the single most legible artefact of the app's history in the whole bundle, and a good reminder that "modern app" and "modern every-path" are different claims.

Also present: `assets/fgcpkjvqe.dat` (776 KB, Shannon entropy 8.000 — indistinguishable from random), `taibmcf.dat/json`, `bshield.dat`. Entropy 8.000 means compressed or encrypted; with no container header and no readable strings, we classify them as opaque and stop. `assets/android.1.zip` (3.9 MB, entropy 7.996) and the three Picnic `lowmcL*.bin.properties` tables are high-entropy for benign reasons (deflate and LowMC constants respectively).

---

## 9. Configuration as code

`assets/config/zalopay_config.json` is 105,659 bytes with **56 top-level sections, 403 boolean/integer feature flags and 35 named API methods**, plus a separate `miniapp_config.json` (883,684 bytes) holding the mini-app catalogue.

```json
"api": { "api_route": "https", "connector_mode": "grpc", "api_names": [...35...] }
"general": { "api_timeout": 10000, "cskh_phonenumber": "1900545436",
             "pcidss": "https://seal.controlcase.com/index.php?page=showCert&cId=…",
             "otp_sender": "ZaloPay", "phone_format": { "min_length": 9, "max_length": 12 } }
"grpc": { "first_ping_interval": 1000, "ping_interval": 30000, "ping_timeout": 60000,
          "connection_timeout": 10000, "reconnect_multiply": 5000 }
```

The 35 API names split into `um/*` (15 — user management: `createaccesstoken`, `verifyaccesstoken`, `getuserinfo`, `getuserprofilelevel`, `updatezalopayname`, `recoverypin`, `verifyotpprofile`, …), `v001/*` (15 — third-party engine: `createorder`, `submittrans`, `gettransstatus`, `atmauthenpayer`, `mapcard`, …), `ummerchant/*` (2), `cpscdn/*` (2) and `umupload/*` (1). `connector_mode: grpc` with `api_route: https` means gRPC over TLS — and the DEX contains zero `application/grpc` literals — the HTTP/2 framing strings live inside the connector library, not the app's own code, so the gRPC path is entirely procured.

15 hosts appear in the config, including `scard.zalopay.vn` (card data), `mnap-director.zalopay.vn` (mini-app director), `ebank.bidv.com.vn` / `ebanking.vietinbank.vn` / `www.sacombank.com.vn` / `www.vietcombank.com.vn` (bank deep-link targets) and `seal.controlcase.com` (the PCI-DSS seal). Across the whole bundle the DEX literal pool yields **203 distinct hosts**, led by `simg.zalopay.com.vn` (1,326 references), `onelink.zalopay.vn` (539), `social.zalopay.vn` (357), `scdn.zalopay.com.vn` (290) and `mnap-director.zalopay.vn` (47).

### 9.1 Three keys, and a signature that authenticates none of them

`assets/config/` ships `public_key.txt` (RSA-2048), `signature.txt` (256 bytes) and two config payloads. The natural hypothesis is that the signature authenticates the payloads. We tested it exhaustively for the two payloads × {SHA-1, SHA-256, SHA-512} × {PKCS#1 v1.5, PSS(salt=32), PSS(max)} = **18 combinations. None validates.**

That is a result, not a failure. It identifies the key's *purpose*: it guards **runtime-fetched** config (which the server re-signs on each push), while the bootstrap copy baked into the APK is protected by the APK signature itself. Two further pinned keys exist — `assets/public.pem` and `assets/zoom.public`, neither authenticating a bundled payload either. Reporting the purpose rather than alleging a weakness is the difference between analysis and alarmism; the three public keys are published in `data/keys.json` as SPKI SHA-256 fingerprints, which is also the form a defender would pin.

---

## 10. Cross-layer correlation

Joined across layers, eight subsystems with measured weight:

| Subsystem | DEX classes | Native bytes | JNI | Anchor evidence |
|---|---:|---:|---:|---|
| Mini-app platform | 5,973 | 0.60 MB | 0 | Griver/ARiver/IAPConnect + AppX archives + 8 bridge JS |
| Cryptography | 6,473 | 3.71 MB | 4 | Bouncy Castle (+PQC) + Nimbus + SQLCipher/SecurityGuard |
| Analytics / attribution | 4,198 | 0.53 MB | 0 | AppsFlyer, Meta, GMS measurement + Crashlytics |
| Payments / cashier | 1,852 | 3.71 MB | 4 | first-party + Visa CBP + cashier feature modules |
| eKYC / liveness | 710 | 7.61 MB | 26 | TrueID + FaceAuth + FSSD/PicoDet/FastestDet + Haar |
| Banking connectors | 1,340 | — | — | VNPAY + `bank_binding` module + bank deep links |
| UI / rendering | 677 | 3.57 MB | 167 | Compose + Lottie + Rive + Fresco |
| NFC card emulation | 11 | — | — | 3 HCE services + 2 AID XML + 7 AIDs |

The shape is the argument. **eKYC has 710 Java classes but 7.6 MB of native code and 26 JNI entry points** — the security-relevant work happens below the language boundary, which is why "read the Kotlin" is the wrong methodology for this app. **Mini-app has 5,973 classes and almost no native code** — it is a language runtime (JavaScript) inside a Java container. **NFC has 11 classes** and is the highest-severity finding in the study. Class count is not risk, and a study that ranked subsystems by class count would rank the NFC card emulation last.

---

## 11. Risk register

Full register in `data/risk.json`; summary:

| ID | Severity | Finding | Standard |
|---|---|---|---|
| R-03 | **high** | HCE payment service with `requireDeviceUnlock="false"`, 7 AIDs incl. PPSE | MASVS-PLATFORM-1 / EMVCo |
| R-02 | medium | Cleartext permitted app-wide, no pin-set in platform config | MASVS-NETWORK-1/2 |
| R-04 | medium | Catch-all 3-byte Visa AID beside 5 specific ones | EMV AID allocation |
| R-01 | medium | `requestLegacyExternalStorage="true"` on targetSdk 36 | MASVS-STORAGE-1 |
| R-11 | medium | 1.67 MB AppX JavaScript in WebView with a JS bridge | MASVS-PLATFORM-2 |
| R-05 | low | 4 exported test/tooling activities in a release build | build hygiene |
| R-09 | low | armeabi-v7a-only native split (no arm64) | Play targeting |
| R-06 | info | CN-only self-signed cert, 25-year validity | context |
| R-07 | info | Anti-analysis probes in native eKYC (working as intended) | MASVS-RESILIENCE-3/4 |
| R-08 | info | Broad ad-tech data collection (AppsFlyer, Meta, AD_ID) | PDPD/Decree 13 |
| R-10 | info | Pinned key does not authenticate shipped config | key purpose |
| R-12 | info | 45.7 % relocated classes, CBC+PKCS5 literals alongside GCM | MASVS-RESILIENCE-2 |

Every entry names the experiment that would confirm or refute it, and none was executed. We regard this as the register's main virtue: a static finding presented without its falsification test is an opinion with a severity label.

---

## 12. Discussion

### 12.1 What a release gate would have caught

Three of the twelve findings are one-attribute fixes: `requireDeviceUnlock="true"`, `cleartextTrafficPermitted="false"` with per-domain pin-sets, and excluding `androidTest` from the release variant. None requires re-architecture. All three are invisible to a human reviewer of the Kotlin source, because none of them lives in the Kotlin source — one is in an AXML attribute, one is in an XML resource, one is a Gradle configuration outcome. That is the case for whole-bundle static analysis as a *gate*: these are exactly the defects that reading code cannot find, and exactly the ones a 30-second pipeline can.

### 12.2 What static analysis cannot do

It cannot decide whether the cleartext permission is *reached* by any client, whether the HCE unlock policy is compensated by server-side risk scoring, whether the CBC site processes attacker-influenced data, or whether the analytics SDKs run before consent. Those need dynamic confirmation, and §11 says so per item. Static analysis produces a *map of hypotheses with evidence pointers*; dynamic analysis adjudicates. A study that blurred the line would be more quotable and less true.

### 12.3 On the 1.65 %

The most-cited-line candidate in this paper is the composition ratio: 1.65 % first-party classes. It should be read carefully. R8 relocated 45.3 % of classes, and some of that pool is first-party code under renamed packages — we cannot separate renamed-first-party from renamed-third-party, so 1.65 % is a **lower bound** on first-party share and 47 % an upper bound. What survives regardless is the qualitative conclusion: the majority of this payment instrument is code ZaloPay did not write, and its security properties are therefore largely *procured* rather than *built*. That is not a criticism — it is how modern fintech works, and it is the reason an SBOM with measured class counts (§5) is more useful than a dependency list.

### 12.4 Threats to validity

* **Owner attribution** depends on a hand-maintained prefix table; a namespace not in the table falls to "third-party (other)" rather than being guessed.
* **Entropy** is capped at the first 4 MiB, so a file that is plaintext-then-compressed is measured on its head.
* **Namespace counts** cannot distinguish linked-but-unused.
* **APKPure provenance**: the input is a third-party mirror, not a Play download. The signer fingerprint is internally consistent across all five splits, but *matching the official Play signing identity* is a check we cannot perform here. This is stated once, prominently, because every number in this paper inherits it.
* **Single version, single ABI.** 11.17.0, armeabi-v7a. No claim generalises across versions or to the arm64 split that this bundle does not contain.

---

## 13. Reproducibility

```bash
python3 -m venv .venv && .venv/bin/pip install -r re/requirements.txt
.venv/bin/python re/run_all.py          # 7 stages, ~30 s
```

Outputs: `data/unpack.json`, `inventory.csv` (6,466 rows), `census.json`, `dex.json`, `sbom.json` + `sbom.csv`, `assets.json`, `keys.json`, `miniapps.json`, `native.json`, `surface.json`, `correlation.json`, `risk.json`, `summary.json`, `run_manifest.json` (per-stage timing + output digests).

`data/run_manifest.json` records the Python version, per-stage wall-clock and the SHA-256 of every output, so a re-run can be diffed field by field. The pipeline is dependency-light (androguard, pyelftools, cryptography, pillow, numpy) and JVM-free.

---

## 14. Conclusion

We analysed every one of the 6,466 files in a production Vietnamese fintech bundle, verified integrity at four boundaries, and turned the result into eight quantified subsystems and a twelve-item risk register whose top entry is an NFC card-emulation policy, not a cryptographic weakness. The methodological claim is that whole-bundle static analysis is now cheap enough — 30 seconds, no JVM — to be a routine release gate. The empirical claim is that such a gate would have caught three one-attribute defects that source review structurally cannot see. The engineering claim is that the four layers (DEX, ELF, assets, config) only become an explanation when they are joined, and that joining them changes the ranking of what matters.

---

## References

1. Android Open Source Project. *Dalvik Executable format* (DEX 035/038/039) and `libdex` `DexFile.h`. source.android.com.
2. Android Open Source Project. *APK Signature Scheme v2/v3* and the APK Signing Block layout (`APK Sig Block 42`). source.android.com/security/apksigning.
3. Android Open Source Project. *Host-based card emulation* — `HostApduService`, `requireDeviceUnlock`, AID registration. developer.android.com/guide/topics/connectivity/nfc/hce.
4. Android Open Source Project. *Network security configuration* — `base-config`, `domain-config`, `pin-set`, `debug-overrides`. developer.android.com/privacy-and-security/security-config.
5. Android Open Source Project. *Resource types* — `ResTable` chunk layout (`ResourceTypes.h`), configuration qualifiers.
6. EMVCo. *EMV Contactless Specifications for Payment Systems*, Book B (Entry Point) — PPSE `2PAY.SYS.DDF01` and AID selection.
7. ISO/IEC 7816-4 — interindustry commands for integrated circuit cards; SELECT by AID/DF name.
8. ISO/IEC 18004 — QR Code bar code symbology specification.
9. P. Lienhart, J. Maydt. *An extended set of Haar-like features for rapid object detection.* ICIP 2002. (Origin of the `haarcascade_frontalface_default` cascade shipped in `res/raw`.)
10. Viola, Jones. *Rapid object detection using a boosted cascade of simple features.* CVPR 2001.
11. OWASP. *Mobile Application Security Verification Standard (MASVS)* v2 — STORAGE, CRYPTO, NETWORK, PLATFORM, RESILIENCE.
12. OWASP. *Mobile Security Testing Guide (MSTG)* — MSTG-STORAGE, MSTG-NETWORK, MSTG-PLATFORM.
13. PCI Security Standards Council. *PCI DSS v4.0* — Req. 2 (secure configuration), Req. 4 (strong cryptography in transit), Req. 6 (secure development).
14. Legion of the Bouncy Castle. *Bouncy Castle Crypto APIs* — `jcajce`, `crypto`, `pqc` packages.
15. Zetetic. *SQLCipher* — encrypted SQLite, page-level AES.
16. OpenSSL Project. *OpenSSL 3.0* — provider architecture, SM2/SM3/SM4 support.
17. Alibaba Group. *Griver / ARiver* mini-program container; *AppX* (`.amr`-packed JS bundles) packaging format.
18. Alipay / Ant Group. *SecurityGuard* (SecurityGuard-Lite native `encrypt`/`decrypt`/`signRequest`).
19. VNG. *TrueID* eKYC SDK and *FaceAuth* (`VFaceLib`) — on-device liveness, SmartOTP vault.
20. Google. *ML Kit* — OCR, barcode, object detection; *TensorFlow Lite* flatbuffer schema (`TFL3`).
21. Tencent. *ncnn* — mobile neural network inference; *PicoDet* detector.
22. Rive. *Rive runtime* (`librive-android.so`).
23. Square. *OkHttp* `CertificatePinner`; *Okio*.
24. Androguard. *androguard 4.x* — AXML, ARSC, certificate parsing. github.com/androguard/androguard.
25. morluto. *rea* — reverse-engineering helper suite. github.com/morluto/rea.
26. A. Zeller et al. on delta debugging and reproducible analysis; general reproducible-research practice per ACM Artifact Review and Badging.
27. Vietnam Government. *Decree 13/2023/ND-CP* on Personal Data Protection.
28. State Bank of Vietnam. *Circular 09/2020/TT-NHNN* and successors on information-system security for payment intermediaries.
29. J. Oberheide, E. Cooke, F. Jahanian. *Rethinking anti-emulation techniques for large-scale software deployment.* (Emulator-detection scoring, cf. §4.5.)
30. NIST. *Post-Quantum Cryptography* standardization (FIPS 203/204/205) — context for the shipped `org.bouncycastle.pqc` tree.

---

## Appendix A — Artefact index

| Path | Contents |
|---|---|
| `re/dex.py` | dependency-free DEX reader (header integrity, class_data, protos) |
| `re/arsc.py` | `resources.arsc` reader (packages, types, configs, density) |
| `re/manifest.py` | split topology, AXML component graph, v2/v3 signing block, signer consistency |
| `re/01_census.py` | per-file census: entropy, magic agreement, ELF hardening, model probe, domains, secrets |
| `re/02_dex.py` | 20-DEX intelligence: owners, obfuscation, security families, literal indicators |
| `re/03_sbom.py` | SBOM from build provenance + measured namespace counts |
| `re/04_assets.py` | keys, AppX containers, ML registry, Haar cascade, remote-config knowledge |
| `re/05_native.py` | per-library capability vector from symbols + rodata |
| `re/06_surface.py` | attack surface, HCE decode, correlation graph, risk register |
| `data/*` | all machine-readable results (see §13) |
| `paper/figures/*` | figures 1–6 |
| `video/` | TypeScript video engine and the rendered MP4 |

## Appendix B — Figure list

* **F1** Bundle anatomy: 6,466 entries by category and size (log scale).
* **F2** Code ownership donut: 63,827 classes across 16 owner buckets.
* **F3** 20-DEX treemap: bytes vs class count (showing `audience_network.dex`).
* **F4** Native capability matrix: 27 libraries × 9 capability channels.
* **F5** Subsystem graph: 8 subsystems, measured DEX classes vs native bytes.
* **F6** Risk register heat strip: 12 findings by severity and standard.
