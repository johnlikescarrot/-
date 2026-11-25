# ZaloPay 11.17.0 — Static Reverse-Engineering Report

**Target artefact:** `Zalopay-Thanh toán & Tài chính_11.17.0_APKPure.zip`
**Actual container:** XAPK v2 (a ZIP inside the ZIP) holding **5 split APKs**
**Package:** `vn.com.vng.zalopay` · **versionName** `11.17.0` · **versionCode** `1102758`
**minSdk** 26 (Android 8.0) · **targetSdk / compileSdk** 36 · **main activity** `vn.com.vng.zalopay.splashscreen.presentation.SplashScreenActivityV2`
**Report date:** 2026-11-25 · **Method:** 100 % static (offline). No device or network interaction with ZaloPay infrastructure was performed.

---

## 1. Executive summary

ZaloPay 11.17.0 is a large, aggressively engineered super-app that composes **four independent application runtimes** inside one APK and wraps them in a **commercial-class native hardening shell**.

| Finding | Evidence |
|---|---|
| **6,213 files / 299.6 MB** unpacked from a 105.6 MB XAPK | see §4 |
| **19 DEX files, 64.8 MB, 60,671 classes, 351,248 methods, 243,013 unique strings** | see §5 |
| **28 native libraries, 57.5 MB**, armeabi-v7a only | see §7 |
| **Hardened:** `<application android:name="jwbmqnsll.xvndbxlk">`, two `JNI_OnLoad`-only ELF shells, two maximally-entropic encrypted payloads | see §8 |
| **Four runtimes:** Kotlin/Java native UI · React Native (3.61 MB JSBundle) · Alipay/Alibaba **Griver** mini-app container · H5 web-app shell | see §6, §9 |
| **49 permissions** (15 dangerous) including `QUERY_ALL_PACKAGES`, `NFC`, `DETECT_SCREEN_CAPTURE` | see §10 |
| **335 URLs / 229 hosts / 288 API paths** mapped across the ZaloPay, Alipay+ and Google meshes | see §11 |
| **Zero private keys, zero live secrets** recovered from DEX strings; 3 third-party SDK identifiers and 1 public RSA key are present | see §12 |
| **Network security config permits cleartext (HTTP) app-wide** | see §10.3 |

The engineering signal is unambiguous: this is a **fintech super-app with defence-in-depth on the client**, where the hardening target is *code and key extraction* (§8), while the largest practical attack surface is *the web/mini-app bridge* (§9).

---

## 2. Scope, legality and ethics

This report documents **static structure and configuration**, not user data, and not exploit chains.

* The APK is a **public distribution artefact** obtained from a public mirror (APKPure), equivalent to the Play Store build.
* No user data, credentials, session tokens or PII were extracted or are reproduced here.
* No live endpoints were contacted; all findings derive from strings, headers, resource tables and ELF metadata inside the package.
* Secret-like values shown in §12 are **shipped by the vendor in plaintext inside the APK**. They are reproduced only at the level needed to support the remediation guidance. ZaloPay's public Google Maps key and public AppsFlyer dev key are *by design* embedded in client apps; they are **not** server secrets. The Sentry DSN is a write-only ingest endpoint.
* This document is intended for **defensive** use: vendor engineering, mobile-security research, and comparison with the *rea* reverse-engineering toolkit's methodology.

---

## 3. Container anatomy

```text
Zalopay-Thanh toán & Tài chính_11.17.0_APKPure.zip   105,608,128 B
└── …_11.17.0_APKPure.xapk                            105,608,128 B
    ├── manifest.json                                      2,710 B   (xapk_version 2)
    ├── icon.png                                          23,841 B
    ├── vn.com.vng.zalopay.apk                        79,359,087 B   base
    ├── config.armeabi_v7a.apk                        24,828,408 B   ABI  ← only 32-bit ARM
    ├── config.xhdpi.apk                               1,232,563 B   density
    ├── config.vi.apk                                     86,425 B   locale
    └── config.en.apk                                     74,137 B   locale
```

Two structural observations carry weight:

1. **`armeabi-v7a` is the only ABI shipped.** There is no `arm64-v8a` split in this artefact. That is unusual for a 2026 fintech release and is relevant to both performance and to the reach of the native hardening shell (§8): every device runs the 32-bit path, so the shell is uniformly effective — and uniformly soft-float limited.
2. **Locale splits are only `en` and `vi`** (161 KB total), yet the app synthesises a much wider localisation surface at runtime through the web/mini-app runtimes.

Declared split metadata (from `manifest.json`):

```json
{ "package_name": "vn.com.vng.zalopay", "version_code": "1102758",
  "min_sdk_version": "26", "target_sdk_version": "36",
  "split_configs": ["config.armeabi_v7a","config.en","config.vi","config.xhdpi"],
  "total_size": 105580620 }
```

---

## 4. Full file inventory

All 6,213 extracted files were classified, hashed (SHA-256 prefix) and entropy-profiled; the machine-readable table is [`analysis/out/file_inventory.csv`](analysis/out/file_inventory.csv).

| Kind | Files | Bytes | Share |
|---|---:|---:|---:|
| XML (compiled resources) | 5,212 | 11,026,540 | 3.7 % |
| Other (APK containers, YAML, ML OCR model) | 327 | 116,243,486 | 38.8 % |
| Text | 136 | 296,882 | 0.1 % |
| JSON | 65 | 6,679,787 | 2.2 % |
| Native ELF `.so` | 28 | 57,452,516 | 19.2 % |
| ML models (`.tflite/.tfl/emd/pb/ncnn`) | 27 | 12,963,308 | 4.3 % |
| Images | 335 | 8,671,229 | 2.9 % |
| Fonts | 25 | 7,412,552 | 2.5 % |
| DEX | 20 | 69,701,748 | 23.3 % |
| Proto / JS / signature / blob / archive / key | 30 | ~3.9 MB | 1.3 % |
| **Total** | **6,213** | **299,605,180** | **100 %** |

**Density of code.** Excluding the two nested APK containers, ~186 MB of the payload is *code and models*, not assets: 64.8 MB DEX + 57.5 MB ELF + 13.0 MB ML + 7.4 MB fonts. This is a code-heavy, not art-heavy, application — consistent with a payment platform where logic (risk, KYC, cashier, mini-app runtime) dominates.

**Highest-entropy objects** (entropy ≥ 7.99 ⇒ encrypted or compressed with no recognisable header):

| Entropy | Bytes | Object | Interpretation |
|---:|---:|---|---|
| 8.000 | 776,320 | `assets/fgcpkjvqe.dat` | **Encrypted payload** (§8) |
| 7.999 | 856,974 | `assets/Griver/68687209_2.9.7.8.amr` | Griver mini-app bundle (encrypted/compressed) |
| 7.997 | 752,652 | `assets/org/bouncycastle/pqc/crypto/picnic/lowmcL5.bin.properties` | BouncyCastle Picnic LM codebook |
| 7.996 | 3,913,966 | `assets/android.1.zip` | React Native bundle (deflated) |
| 7.976 | 8,307 | `assets/taibmcf.dat` | Small encrypted key/signature blob |

---

## 5. DEX layer

Nineteen DEX files (`classes.dex` … `classes19.dex`), parsed with a purpose-built **zero-dependency DEX reader** (`analysis/scripts/dexparse.py`, 0.25 s for the whole corpus).

| Totals | Value |
|---|---|
| DEX files | 19 |
| DEX bytes | 64,837,712 |
| Classes (unique) | **60,671** |
| Methods | **351,248** |
| Fields | **304,518** |
| Unique strings | **243,013** |
| Type IDs | 114,693 |
| Protos | 98,523 |
| Duplicate classes across DEX | 0 |

Per-file distribution — the class loader is loaded to the brim; four DEX files sit at or near the 65,536-method-per-DEX ceiling, the classic multidex signature:

| DEX | Strings | Methods | Classes | Bytes |
|---|---:|---:|---:|---:|
| classes.dex | 61,218 | 59,948 | 10,703 | 8,831,004 |
| classes2.dex | 56,948 | 45,527 | 7,421 | 16,516,960 |
| classes4.dex | 52,812 | 59,997 | 11,441 | 9,418,124 |
| classes5.dex | 56,424 | 49,055 | 7,983 | 7,357,484 |
| classes6.dex | 55,274 | 49,201 | 5,475 | 7,612,432 |
| classes7.dex | 59,110 | 60,000 | 12,953 | 10,803,680 |
| classes3.dex | 8,010 | 6,354 | 1,081 | 850,600 |
| classes9.dex | 6,155 | 7,170 | 1,128 | 1,218,908 |
| classes19.dex | 4,159 | 3,781 | 777 | 719,548 |
| classes8,10–18 | ≤3,207 ea | ≤2,604 ea | ≤425 ea | ≤398 KB ea |

### 5.1 Obfuscation profile

* **3,067 distinct top-level packages.**
* **655** of them are **1–3 lowercase letters**, and they contain **33,281 classes (54.9 % of all classes)** — the fingerprint of **R8 full-mode with package flattening plus class-repackaging**.
* Application entry point survives as `jwbmqnsll.xvndbxlk` (11-letter random package) — see §8.2.
* First-party ZaloPay code that *is* kept readable is the part referenced by reflection/DIs/JS bridges: **952 classes across 73 feature modules**.

Largest first-party modules (by class count): `bankbinding` 200, `homev5` 120, `data` 72, `internal` 47, `webapp` 46, `miniapp` 39, `alipay` 34, `search` 30, `network_monitor` 19, `livenessdetection` 18, `authentication` 17, `webview` 16, `domain` 14, `user_profile` 14, `cashin` 14, `withdraw` 14, `passport` 13, `nfc` 13, `quickpay_native` 13, `app_widget` 12, `loginv2` 12, `pdfviewer` 10, `protect` 10.

This module map is effectively a **product architecture diagram read from code**: onboarding/identity (`passport`, `loginv2`, `authentication`, `livenessdetection`, `skewface`), money movement (`cashin`, `withdraw`, `receive_money`, `clearing`, `pay_by_wallet_native`), and commerce (`cashier`, `miniapp`, `homev4/v5`, `bankbinding`).

---

## 6. Third-party SDK composition

Class counts are computed from the actual descriptor prefixes present in the DEX corpus (not from Gradle metadata).

| SDK / framework | Classes | Role |
|---|---:|---|
| **BouncyCastle** | 6,112 | Crypto provider, incl. **post-quantum** package (Picnic, LM, XMSS, Kyber-family) |
| **Google Play services** | 5,933 | Maps, Ads, ML Kit, Identity, App Set, Wallet, Integrity |
| **Alibaba ARiver / Griver (IAP Connect)** | 4,821 | **Mini-program container** (the `com.iap` package adds another 1,599) |
| **AndroidX** | 4,250 | Compose, WorkManager, CameraX, Media3, Room, DataStore, Glance |
| **Visa CBP** | 1,370 | Card-based payment / secure remote commerce |
| **ZaloPay first-party** | 952 | Product logic |
| **TrueID (VN AI)** | 689 | eKYC, liveness, **SmartOTP** with hardware-backed ceremony |
| **Facebook SDK / Audience Network** | 531 | Login, ads |
| **Alipay+ (Ant)** | 493 | Cross-border payment rail |
| **AppsFlyer** | 430 | Attribution |
| **VNPay** | 388 | Domestic payment rail + taxi SDK |
| **Nimbus JOSE+JWT** | 361 | JOSE/JWT |
| **OkHttp** | 335 | Networking |
| **RxJava 2** | 266 | Reactive layer |
| **JMRTD** | 237 | **ICAO e-passport / NFC chip reading** |
| **Firebase** | 221 | FCM, Analytics, Crashlytics, Perf, Config, Installations |
| **Google Tink** | 221 | Managed crypto (AEAD, ECDSA) |
| **Coil / Glide** | 216 | Image loading |
| **Gson** | 207 | JSON |
| **Jsoup** | 175 | HTML parsing (bank portal scraping) |
| **Rive** | 157 | Vector animation |
| **ML Kit** | 50 | Barcode, face, text, subject segmentation |
| **Kotlinx coroutines** | 17 | (counted by descriptor; used pervasively via inlining) |

Runtime metadata pins the toolchain precisely (`kotlin-tooling-metadata.json`): **Kotlin 2.2.10**, **Gradle 7/8.14.5**, **JVM target 17**, HMPP enabled.

---

## 7. Native layer (28 ELF32 ARM libraries)

All libraries are `ELF32 / ARM / DYN`, **stripped** (`.symtab` absent), and export only a `dynsym` table.

| Library | Bytes | JNI exports | `JNI_OnLoad` | Constructor (`.init_array`) | Assessment |
|---|---:|---:|---|---|---|
| `libtaibmcf.so` | **12,617,752** | 0 | ✔ | ✔ | **Hardening shell** — clang 17.0.2, BuildID `a26a37e9…` |
| `libfgcpkjvqe.so` | **6,524,160** | 0 | ✔ | ✔ | **Hardening shell** — clang **21.0.0**, BuildID `f9826ee5…` |
| `libmlkitcommonpipeline.so` | 6,928,228 | 38 | ✔ | ✔ | ML Kit pipeline |
| `libmlkit_google_ocr_pipeline.so` | 6,781,940 | 33 | ✔ | ✔ | ML Kit OCR |
| `libface_detector_v2_jni.so` | 5,388,224 | 6 | ✔ | ✔ | Face detection |
| `libsqlcipher.so` | 3,555,080 | 0 | ✔ | ✔ | SQLCipher (encrypted DB) |
| `libbarhopper_v3.so` | 3,244,440 | 8 | ✔ | ✔ | Barcode/MLKit |
| `librive-android.so` | 3,225,636 | **167** | ✔ | ✔ | Rive runtime |
| `libpicodet_ncnn.so` | 2,992,584 | 8 | ✔ | ✔ | PicoDet detector (NCNN) |
| `libVFaceLib.so` | 1,963,668 | 4 | ✔ | ✔ | Face library |
| `liba97d.so` | 997,572 | 0 | ✔ | — | Obfuscated 3rd-party native |
| `libc++_shared.so` | 872,872 | 0 | — | ✔ | libc++ |
| `libljsfrcwmw.so` | 600,296 | 0 | ✔ | — | Obfuscated 3rd-party native |
| `libcrashlytics-common.so` | 435,732 | 0 | ✔ | — | Crashlytics |
| `libnative-imagetranscoder.so` | 341,072 | 0 | ✔ | — | Fresco |
| `liblytcuhe.so` / `librcmefqonnx.so` | 178,628 each | 0 | ✔ | — | **Same size, different SHA-256** — variant builds of one component |
| `libohxbee.so` | 155,412 | 0 | ✔ | — | Obfuscated; exports `fayjpsel`, `kxffetq`, `sgsmdzzsfi`, `gVm` |
| `libsecurity_lite.so` | 154,840 | 4 | ✔ | — | Alipay `SecurityGuardLiteNative` (`init`, `encrypt`, `decrypt`, `signRequest`) |
| `libtrueidlib.so` | 79,904 | **10** | ✔ | — | TrueID **SmartOTP vault** (`vaultRegisterCeremony`, `vaultAuthCeremony`, `vaultX25519Pubkey`, `vaultVerifyTxDigest`, `vaultComputeQReg`, `vaultComputeQAuth`, `vaultCheckIntegrity`) |
| `libcrashlytics.so` | 94,540 | 0 | ✔ | — | Crashlytics |
| others (trampoline, imagepipeline, native-filters, graphics.path, crashlytics-handler) | ≤87,908 | 0 | mixed | mixed | Support |

**Eleven libraries export no `Java_*` symbol yet define `JNI_OnLoad`** — i.e. they bind their native methods **dynamically via `RegisterNatives`**, and their real capability is not visible in the symbol table. That is a deliberate anti-static-analysis posture shared by `libtaibmcf.so`, `libfgcpkjvqe.so`, `liba97d.so`, `libohxbee.so`, `libljsfrcwmw.so` and `libsqlcipher.so`.

**`libtrueidlib.so` is the single most interesting native target** for a security review: ten named JNI entry points implement a *vault ceremony* for transaction signing, with an X25519 public-key export. It is small (79.9 KB), unstripped in its `Java_trueid…` surface, and therefore the most tractable reverse-engineering subject in the package. It is paired with a **392-byte `assets/trueidlib.so`** whose entropy is 7.50 — a stub/loader, not the real library.

---

## 8. Hardening and anti-analysis

### 8.1 Two shells, two toolchains

| | `libtaibmcf.so` | `libfgcpkjvqe.so` |
|---|---|---|
| Size | 12,617,752 B | 6,524,160 B |
| Compiler | clang **17.0.2**, LLD 17.0.2 | clang **21.0.0**, LLD 21.0.0 |
| Build ID | `a26a37e9cce21f2a486aa9f8b6abd7d06bb975b2` | `f9826ee5d5243ae7091701e7593e50523a3d32d2` |
| `NEEDED` | libandroid, libc, libdl, liblog, libm, **libz** | libandroid, libc, libdl, liblog, libm, **libz** |
| `JNI_OnLoad` | ✔ (no `Java_*`) | ✔ (no `Java_*`) |
| Encrypted blob | `assets/taibmcf.dat` (8,307 B) | `assets/fgcpkjvqe.dat` (776,320 B) |

The **clang-version mismatch is the strongest single indicator** that these two components are *not* built from the app's own Gradle project: the rest of the APK is Kotlin 2.2.10 / JVM 17 with a contemporary toolchain, whereas the shells were produced by two different vendor releases of an external packer product. Combined with the *.dat ↔ *.so* naming correspondence (`taibmcf.dat` ⇄ `libtaibmcf.so`, `fgcpkjvqe.dat` ⇄ `libfgcpkjvqe.so`), the picture is a **two-stage commercial packer**:

1. **`libfgcpkjvqe.so` (7.3 MB payload)** — likely the *decryptor/VMP core* that inflates and maps the real DEX/ELF payload (`fgcpkjvqe.dat`, entropy literal `8.000`).
2. **`libtaibmcf.so` (12.6 MB)** — likely the *runtime integrity + anti-tamper* stage, given its `libandroid.so`/`libz` dependencies (asset inflation) and its much larger size.

Both use `.init_array` constructors, so they execute **before the first Java instruction**, i.e. before any application-level check could observe them.

### 8.2 The stub Application

```
<application android:name="jwbmqnsll.xvndbxlk" …>
    superclass: vn.com.vng.zalopay.AndroidApplication
```

`Ljwbmqnsll/xvndbxlk;` is the **only** class in the entire 60,671-class corpus whose package root is `jwbmqnsll`. Its superclass is the *real* application. This is the canonical **Application-proxy pattern**: the packer substitutes its own `Application`, loads the native shell in `attachBaseContext()`, decrypts/patches the real code in memory, and then delegates to `vn.com.vng.zalopay.AndroidApplication`.

Corroborating assets: `assets/bshield.dat` (929 B, entropy 7.785) — a small encrypted manifest/signature — and `assets/vdfmoer.json` / `assets/taibmcf.json`, both containing *only* the UI string `{"vi":"Mở cài đặt","en":"Open settings"}`. Those two JSONs are the **friendly error page shown when the integrity check fails** — proof that the shells actively gate startup, rather than merely compressing code.

### 8.3 Anti-analysis marker inventory (string-level, DEX corpus)

| Category | Markers found | Samples |
|---|---:|---|
| Root / superuser | **23** | `/system/bin/su`, `/system/xbin/su`, `/supersu`, `/magisk`, `isRooted`, `ro.debuggable`, `ro.secure`, `test-keys` |
| Hooking / dynamic instrumentation | **10** | `frida`, `gMain`, `XPOSED`, `substrate`, `magisk`, `/proc/self/maps`, `/proc/self/status`, `TracerPid` |
| Emulator / virtualisation | **13** | `goldfish`, `ranchu`, `qemu`, `Genymotion`, `NOX`, `VBOX`, `sdk_gphone`, `isEmulator` |
| Debugger | **4** | `isDebuggerConnected`, `waitingForDebugger`, `isDebuggable` |
| Dynamic code loading / reflection | **11** | `DexClassLoader`, `InMemoryDexClassLoader`, `PathClassLoader`, `BaseDexClassLoader`, `LoadedApk`, `java/lang/reflect/{Method,Field,Constructor}`, `setAccessible` |

`QUERY_ALL_PACKAGES` (§10) plus the `magisk`/`/su` probes means the app can **enumerate every installed package** and correlate it with a root/hooking blacklist. That is a materially larger privacy surface than the permission's stated purpose.

### 8.4 Signing configuration

| Property | Value |
|---|---|
| v1 (JAR) | **absent** |
| v2 (APK Signature Scheme) | present |
| v3 | present |
| Certificate subject/issuer | `CN=Vu Manh Hieu` (self-signed) |
| Serial | `0x153ea9fd` |
| Validity | 2016-04-21 → **2041-04-15** (~25 years) |
| SHA-256 | `59:DD:31:FD:F3:60:70:A0:D7:F8:C8:CD:E8:43:FA:C3:71:DF:7F:1A:91:5C:01:36:C9:C9:B5:59:92:E8:CD:AE` |

The signing key dates to **2016** and is valid until 2041 — an unusually long-lived, single-identity release key, consistent with a title signed by the same organisation for a decade. Presence of v3 without v1 is expected for a modern `minSdk 26` build. **Key-rotation/`v4` lineage** is not asserted here because the APKPure artefact does not carry the Play signing-lineage proof.

---

## 9. The four runtimes

The single most important architectural finding is that **ZaloPay is not one app but four co-resident runtimes**, each with its own trust boundary:

### 9.1 Native Kotlin/Java UI (dominant by class count)
Compose + Views, CameraX, Media3, WorkManager, Room/DataStore. 952 first-party classes across 73 modules (§5.1).

### 9.2 React Native (legacy but live)
`assets/android.1.zip` contains **`main.jsbundle`, 3,613,478 B**, timestamped **2022-03-08**, plus 325 drawable resources and a module map referencing `react-navigation`, `@react-native-async-storage`, `react-native-webview`, `react-native-masked-view`, `react-native-netinfo`, `react-native-slider`, `react-native-viewpager`, `zalopaycomponents` and a `miniapps` navigator.

The bundle is **plain, non-Hermes JS** (`var __DE…`), and therefore fully inspectable. It contains an explicit **environment table**:

| Environment | Base URL | CDN | Service rules |
|---|---|---|---|
| Development | `https://devcps.zalopay.com.vn/` | — | — |
| QC | `https://qccps.zalopay.com.vn/` | `https://qccdn.zalopay.com.vn/` | `https://sandbox.zalopay.vn/quy-dinh` |
| Staging | `https://stgcps.zalopay.com.vn/` | `https://stgcdn.zalopay.com.vn/` | `https://staging.zalopay.vn/quy-dinh` |
| **Production** | `https://cps.zalopay.com.vn/` | `https://cdn.zalopay.com.vn/` | `https://zalopay.vn/quy-dinh` |

…and a hard-coded telemetry credential: `sentryDSN: https://d6ce683467984ff0b96c8e228b56c9a5@o529616.ingest.sentry.io/5666380`.

**Four environments shipped in one client** is a recurring source of accidental cross-environment traffic if a build-time constant is wrong; here the table is explicit and gated by an `Environments` enum, which is the safer pattern.

### 9.3 Alibaba Griver / IAP Connect — mini-program container
`assets/` carries the full **Ariver/Griver** runtime: `Griver/`, `GriverBridge/`, `GriverErrorPage/`, `GriverTheme/`, `griverIconDefault/`, `containers/`, `containererrorpage/`, `webapp.js`, `jsbridge.js`, `internaljsbridge.js`, `android_jsbridge.js`, `workerjs.js`, plus pre-downloaded mini-app bundles with `.amr` containers at entropy ≈ 7.999.

Four `*HostApduService` implementations (§10.2) expose an **NFC APDU channel to mini-apps** — that is, third-party mini-programs can reach the secure element path through a mediated service.

### 9.4 H5 web-app shell
`vn.com.vng.zalopay.webapp.activity.*` plus 20+ `WebViewActivity` subclasses (`ResetPinWebViewActivity`, `BalanceLiquidationWebViewActivity`, `VerificationAtBankCounterWebViewActivity`, `UpdatePersonalInfoWebViewActivity`, `HomeSearchWebViewActivity` …). Bank-binding flows are **JS-driven**: `assets/` ships `vcb_login_page_tracking_event.js`, `vpb_login_tracking_event.js`, `bidv_login_page_tracking_event.js`, `tpb_tracking_event.js`, `js_napas_observer_step_tracking.js`, `getNapasErrorMessage.js`, `zp_vcb_auto_choose_account.js`, `zp_vcb_direct_service.js`.

These scripts are **injected into bank pages** to automate/observe login — a design with legitimate UX motives (pre-filling the bank form inside the app) and non-trivial risk (see §13, R-3).

---

## 10. Manifest surface

### 10.1 Components

| Type | Total | Exported |
|---|---:|---:|
| Activities | 200 | **14** |
| Services | 34 | **6** |
| Receivers | 22 | **8** |
| Providers | 9 | 0 |

Notable exported services are the four NFC APDU hosts and the Griver IPC endpoints:

* `com.alibaba.griver.device.jsapi.nfc.service.TinyAppHostApduService`
* `com.alibaba.ariver.commonability.device.jsapi.nfc.service.TinyAppHostApduService`
* `vn.com.vng.zalopay.miniapp.api.v2.visa.VisaHostApduService`
* `com.alibaba.griver.core.kernel.ipc.{server.IpcMsgServerService,client.IpcMsgClientService$Lite1..3}`

### 10.2 Application attributes

```
android:name="jwbmqnsll.xvndbxlk"        android:allowBackup="false"
android:hardwareAccelerated="true"       android:largeHeap="true"
android:extractNativeLibs="true"         android:fullBackupContent="false"
android:networkSecurityConfig=@xml/network_security_config
android:appComponentFactory="androidx.core.app.CoreComponentFactory"
android:requestLegacyExternalStorage="true"
android:enableOnBackInvokedCallback="false"
```

`extractNativeLibs="true"` matters here: the 57 MB of native libraries are **written to disk on install**, which is what makes on-device forensic acquisition of the shells (and of the decrypted payload, post-load) feasible.

### 10.3 Network security config — cleartext permitted

```xml
<base-config cleartextTrafficPermitted="true">
  <trust-anchors><certificates src="system"/></trust-anchors>
</base-config>
<domain-config><domain includeSubdomains="true">storage.googleapis.com</domain></domain-config>
<debug-overrides cleartextTrafficPermitted="true">
  <trust-anchors><certificates src="system"/><certificates src="user"/></trust-anchors>
</debug-overrides>
```

For a payment app this is the **single most conspicuous configuration weakness**: `cleartextTrafficPermitted="true"` at `base-config` level permits **HTTP to any host**, and the DEX corpus does contain `http://` endpoints (§11). There is **no `<pin-set>`** anywhere — certificate pinning, if present at all, must be implemented in code (OkHttp `CertificatePinner`) rather than declared. Trust of `src="user"` under `debug-overrides` is acceptable *only* because `debuggable` is false in this release; a debuggable variant of this manifest would be trivially MITM-able.

### 10.4 Permissions — 49 requested, 15 dangerous

```
READ_CONTACTS · WRITE_CONTACTS · CAMERA · RECORD_AUDIO · NFC · USE_BIOMETRIC
ACCESS_FINE_LOCATION · ACCESS_COARSE_LOCATION · READ_PHONE_STATE · READ_MEDIA_AUDIO
POST_NOTIFICATIONS · READ_CALENDAR · WRITE_CALENDAR · DETECT_SCREEN_CAPTURE
QUERY_ALL_PACKAGES
```

`DETECT_SCREEN_CAPTURE` (Android 14+) + `QUERY_ALL_PACKAGES` + `NFC` + `READ_CONTACTS` is an **unusually wide** fintech permission profile. `DETECT_SCREEN_CAPTURE` is a legitimate anti-fraud control (block remote-access/screen-sharing scams, a major VN fraud vector), and `NFC` supports the e-passport/eKYC path (JMRTD §6). `QUERY_ALL_PACKAGES` remains the least-justified.

### 10.5 Deep links — 10 schemes / 20 hosts

| Scheme | Count | Hosts |
|---|---:|---|
| `zalopay` | 10 | — |
| `https` | 6 | `onelink.zalopay.vn`, `kmzalopay.onelink.me`, `km.zalopay.vn`, `zalopay.vn` |
| `zalopay-1` | 3 | — |
| `zalopay.api.v2` | 3 | — |
| `http` | 1 | — |
| `tel`, `sms`, `fbconnect`, `zalopay-vnpay` | 1 each | — |
| `tagmanager.c.vn.com.vng.zalopay` | 1 | (GTM install-referrer) |

Path tokens include `pay`, `app`, `post`, `backtoapp`, `backtologin`, `otp`, `clearing`. **`backtoapp`, `backtologin` and `otp` as deep-link paths** are exactly the primitives used in payment-return flows — and in link-hijack attacks. Every one of these should be assumed attacker-reachable and validated server-side.

---

## 11. Network footprint

Extraction from DEX strings (243,013) yielded **335 URLs**, **229 distinct hosts** and **288 API-shaped paths**:

| Family | Hosts | Notes |
|---|---:|---|
| **ZaloPay** | 50 | `api`, `openapi`, `sapi`, `token`, `social`, `mp`, `loyalty`, `bnpl-gateway`, `fin-installment`, `mmf-user`, `mmf-redemption`, `rdx`, `tracking`, `sse`, `upload-img`, `atlas`, `qrpay`, `scard`, `cps`, `social`, `mnap-director`, `zlp-ofp-*`, `zlp-movie-api`, `bim`, `bank-profile`, `campaign`, `uudai`, `user-life-cycle-public`, `log`, `dalazoom` |
| **Alipay+ / Ant** | 41 | `mobilegw.alipay.com`, `mdgw.alipay.com`, `open-sea.alipayplus.com`, `hpmweb.alipay.com`, `render.alipay.com`, `imgs-ac.alipay.com`, `miniprogram.alipay-eco.com`, `a.alipayobjects.com`, `iopengw-280.sggz00b.dev.alipay.net` |
| **Google** | 35 | FCM, Analytics, DoubleClick/AdMob, Crashlytics, `app-measurement.com`, `firebaseinstallations.googleapis.com` |
| **Trackers** | 6 | AppsFlyer OneLink/`privacy-sandbox`, TikTok analytics SDK, Sentry ingest |
| **Other** | 98 | Visa (`dpe.consumerapi.digital.visa.com`, `icl.visa.com`, `visapay.as1.juspay.io`), TrueID (`api.trueid.vn`, `pad.trueid.ai`), FPT.AI eKYC (`api.fpt.ai/vision/ekyc-be/`, `web-ekyc-uat.fpt.ai`), Tenpay Global (`weremit-static.tenpay.com`, `uat.tenpayglobal.com`), VNPay (`demomb.vnpaytest.vn`, `design.vnpay.vn`), NAPAS, BIDV (`ebank.bidv.com.vn/DKNHDT/`, `/EWALLET/pay.jsp`), Zalo (`oauth.zaloapp.com`, `graph.zaloapp.com`, `ztevents.zaloapp.com`), government (`universal.vneid.gov.vn`) |

Representative API paths (from 288): `/v2/cashier/pre-check`, `/v2/cashier/sources-of-funds`, `/v2/cashier/order/payment`, `/v2/account/otp-verification`, `/v2/account/phone/forgot-pin-session`, `/v2/account/outapp-ticket/create`, `/v2/bank-mapping-pci/web/link`, `/v2/bank-mapping-pci/web/check-bank-number-v2`, `/v2/ekyc/check`, `/v2/ekyc/check-identity-limit`, `/v2/camera/decode/alipay`, `/v1/merchant/getaccesstoken`, `/v1/oauth/accessToken`, `/v1/emvco/verify`, `/v1/mt/flex-qrcode/generate`, `/gw/v1/session/init`, `/api/app/v2/accounts/unlock`.

**Reading:** the API namespace reveals a mature **money-movement state machine** (`pre-check → sources-of-funds → order/payment → result`) with explicit **PIN-recovery** and **device-unlock** endpoints. Those two — `forgot-pin-session` and `accounts/unlock` — are the highest-risk server endpoints reachable from this client, because they move an account from *locked* to *usable*, i.e. they are the natural target for account-takeover.

---

## 12. Secret and key material

| Item | Location | Classification |
|---|---|---|
| Google Maps API key `AIzaSyBBynYIi93D36Jbq9LsfLou_enqA3dbRFc` | `<meta-data android:name="com.google.android.geo.API_KEY">` | **Public by design** (restrict by SHA-1 + package) |
| AppsFlyer dev key `k5nfUwyL6J6Fe6jvBbPcKG` | `<meta-data android:name="com.appsflyer.AF_DEV_KEY">` | **Public by design** |
| Sentry DSN `…@o529616.ingest.sentry.io/5666380` | `main.jsbundle` | Write-only ingest; low risk |
| RSA-2048 public key | `assets/config/public_key.txt` | Public half only — no private key present |
| Signature | `assets/config/signature.txt` (344 B, base64) | Signature over the config bundle; verifies against the key above |
| Private keys / JWT / cloud credentials / `.env` | — | **None found** in 243,013 strings |

**No exploitable secret was recovered.** The `secrets.json` scan produced hits only for these expected client-side identifiers; the 588 "`[0-9a-f]{32}`" matches are test vectors and hash constants, not keys. The `assets/config/public_key.txt` + `signature.txt` pair is a **config-signing scheme**, which is good practice; its weakness is that the *verifier* lives in the hardened shell, so a patched client could skip verification (see R-1).

---

## 13. Risk register (defensive)

| # | Finding | Severity | Rationale |
|---|---|---|---|
| **R-1** | Hardening is **client-side only**; decrypted payload and keys exist in process memory | Medium | Defeats automated repackaging, not a determined analyst (§8). Acceptable *only if* the server independently authenticates every operation |
| **R-2** | `cleartextTrafficPermitted="true"` at base-config | **High** | Any `http://` URL reachable from the app is MITM-able; DEX contains `http://` endpoints |
| **R-3** | JS injected into **third-party bank login pages** (`vcb_*`, `vpb_*`, `bidv_*`, `tpb_*`) | **High** | In-page script can read/observe credentials unless the WebView is fully isolated and the page is pinned. Requires strict `evaluateJavascript` review |
| **R-4** | 14 exported activities / 6 exported services, incl. 4 NFC APDU hosts and Griver IPC | **High** | Exported NFC + IPC services are reachable by other apps; must enforce signature permissions and caller validation |
| **R-5** | Deep-link paths `pay`, `otp`, `backtologin`, `backtoapp` | Medium | Link-hijack / intent-redirection; validate every parameter server-side |
| **R-6** | `QUERY_ALL_PACKAGES` + root/hook probing | Medium | Installed-package enumeration is a fingerprinting vector |
| **R-7** | No `<pin-set>`; pinning, if any, is code-only | Medium | Rotating pins without a declared network-security-config policy is fragile and unverifiable statically |
| **R-8** | BouncyCastle **post-quantum** provider shipped (181 hits) | Info | Forward-looking crypto agility — *good* practice; verify it is actually used for key exchange, not merely on the classpath |
| **R-9** | React Native bundle dated **2022** still shipping | Low | Unmaintained third-party JS in a payment path; every dependency is a 4-year-old pin |
| **R-10** | `requestLegacyExternalStorage="true"` | Low | Ignored on API 30+, but signals legacy storage assumptions |

---

## 14. What *rea* contributes, and what this analysis adds

`morluto/rea` frames reverse engineering as a **method**: inventory → parse every container → recover structure → map trust boundaries. This report follows that shape and adds three things the toolkit format alone does not give:

1. **A zero-dependency DEX parser** (`analysis/scripts/dexparse.py`) that reads the string/type/proto/field/method/class tables of all 19 DEX files in **0.25 s**, versus minutes for a full decompiler — the right tool for *corpus-level* statistics (which decompilers are bad at).
2. **Native-shell attribution by toolchain forensics** (§8.1): the clang 17-vs-21 mismatch plus *.dat ↔ *.so* pairing identifies a *two-vendor* packer without executing a single instruction.
3. **Runtime-topology reasoning** (§9): the security-relevant claim is not "there are 60k classes" but "there are four runtimes with different trust boundaries, and the JS bridge is where they meet."

### Reproduce

```bash
python3 -m venv work/venv && work/venv/bin/pip install androguard
unzip "Zalopay-….zip" -d work/xapk                 # unwrap the outer ZIP
unzip work/xapk/*.xapk -d work/xapk/split          # unwrap XAPK → 5 APKs
work/venv/bin/python analysis/scripts/02_dex_inventory.py   # 60,671 classes
work/venv/bin/python analysis/scripts/03_deep_scan.py       # URLs / anti-analysis markers
work/venv/bin/python analysis/scripts/05_secrets.py         # secret scan
work/venv/bin/python analysis/scripts/06_fileinventory.py   # 6,213 files
work/venv/bin/python analysis/scripts/08_summary.py         # consolidated JSON
```

Machine-readable outputs live in [`analysis/out/`](analysis/out): `summary.json` (the dataset that drives the film), `file_inventory.csv` (all 6,213 files), `dex_inventory.json` (all 60,671 class names), `native.json`, `secrets.json`, `deep_scan.json`.

---

## 15. The film

The accompanying film (`video/`, TypeScript + `@napi-rs/canvas` + ffmpeg, 1920×1080, 48 kHz stereo, H.264/AAC MP4) renders **only numbers produced by this analysis**. Every figure on screen is sourced from `analysis/out/summary.json`; the citation metadata is in [`CITATION.cff`](CITATION.cff).

Layout: **VISUAL_SYSTEM.md** · Script: **video/scripts/build.ts** · Output: **video/out/zalopay-11.17.0-reverse-engineering.mp4**

---

## 16. Limitations

* **Static only.** No dynamic instrumentation, no Frida/emulator run. The decrypted contents of `fgcpkjvqe.dat` / `taibmcf.dat` are *inferred*, not observed; the two-shell attribution in §8.1 is a **well-supported hypothesis**, not a proof.
* **No server-side verification.** Endpoint behaviour, pinning implementation, and token formats are out of scope.
* **One artefact.** This is the APKPure XAPK (armeabi-v7a only). The Play Store AAB may ship `arm64-v8a`, changing the native profile.
* **Toolchain constraints.** Two CPU cores and no JDK in the analysis environment precluded `jadx`/`apktool` full decompilation; the purpose-built parser substitutes for corpus statistics but does **not** produce Java source.
* **Post-quantum count (181)** is a *string occurrence* count in the BouncyCastle PQC namespace; it does not prove PQC is negotiated at runtime.

---

*Prepared 2026-11-25. Static analysis of a public distribution artefact. No ZaloPay systems were accessed. No user data was collected.*
