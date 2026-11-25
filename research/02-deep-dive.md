# P2/P3 — Deep dive: layers, SDKs, endpoints, hardening

Method: `scripts/triage.py` (AXML + DEX header/string parsing, stdlib only), `scripts/deep.py`
(ELF `readelf`/`nm` census, config + asset mining), `scripts/hardening.py` (ELF flags, JNI exports),
`scripts/endpoints.py` (host inventory with provenance), `scripts/sdks.py` (vendor census + explicit
version strings), `scripts/evidence.py` (quoted excerpts with `dex-strings.txt` line provenance).
REA (`rea-agents@6.10.1`) contributed `audit-release-exposure` and `inspect-byte-entropy` evidence
records; its Android provider needs `jadx-headless-mcp`, which was unavailable offline (see Limitations).

## 1. Four runtimes in one artifact
| Layer | Evidence | Scale |
|---|---|---|
| L0 Kotlin/Java | 19 `classes*.dex`, `resources.arsc` (1.8 MB) | 110,677 classes · 351,248 methods · 374,276 strings |
| L1 React Native | `assets/android.1.zip` → `main.jsbundle` (3.6 MB, plain JS, not Hermes bytecode) | 22 `NativeModules` bridge entry points |
| L2 Alipay-style mini-programs | `Griver/2.70.0`, `AppContainer/10.5.10`, `assets/Griver/*.amr`, `miniapp_config.json` (884 KB) | 3,741 runtime string hits |
| L3 Native ELF | 27 `.so` in the ABI split | 24.8 MB, all PIE + NX + RELRO + BIND_NOW + stack canary |

The JS bundle is *plain JavaScript* (`var __DEV__=false,__BUNDLE_START_TIME__=…`), so it can be
read directly — no Hermes bytecode disassembly step is required. It carries its own module graph
(`node_modules/fbjs`, `react`, `react-navigation-stack`, `zalopay-components`, `@mini-apps/navigation`).

### React Native bridge surface (first-party)
`ZaloPay`, `ZaloPayApi`, `ZaloPayNotification`, `ZaloPayTracker`, `ZaloPayTransactionLogs`,
`ZaloPayRedPacketApi`, `ZaloPayBillSplitApi`, `ZPBankIntegrationApi`, `AccountClearingApi`,
`BEEPickerManager` — plus third-party bridges `RNCNetInfo`, `RNCWebView`, `RNDeviceInfo`, `RNSentry`,
`RNSVGSvgViewManager`, `RNVectorIcons*`, `NativeAnimatedModule`, `PlatformConstants`.

## 2. Dependency fingerprint (explicit versions only)
`ZaloPayClient/11.17.0` · `ZaloPayWebClient/11.17.0` / `8.30.0` · `okhttp/4.12.0` ·
`Griver/2.70.0` · `AppContainer/10.5.10` · `iapconnectsdk/1.8.0` (Alipay+ MPP) ·
`TrueIDSDK/3.3.18` · `AndroidXMedia3/1.8.0` (+ `1.5.0-beta01`) · `datatransport/3.3.0` ·
`tiktok-business-android-sdk/1.4.1` · `AlipayClient/1.0.0` · `Ariver/1.0.0`.

Non-first-party weight (class counts): `com/google` 11,780 · `org/bouncycastle` 9,177 ·
`com/alibaba` 7,333 · `com/iap` 2,113 · `com/visa` 1,792 · `androidx/media3` 1,761 ·
`androidx/camera` 1,629. First-party `vn/com` 1,616 + `vn/ai` 1,114 + `vn/zalopay` 260.
**Observation:** the shipped first-party code is a thin shell over a very large third-party substrate;
most of the attack surface is inherited, not authored.

## 3. Endpoints (197 hosts; provenance per host in `endpoints.json`)
- **143 production**, of which 57 first-party `*.zalopay.vn` / `*.zalopay.com.vn` hosts, including
  `api`, `sapi`, `openapi`, `token`, `social`, `qrpay`, `scard`, `bnpl-gateway`, `fin-installment`,
  `loyalty`, `mmf-user`, `mmf-redemption`, `miniprogram`, `tracking`, `log`, `mnap-director`,
  and a family of `zlp-ofp-*` (EMVCo / open-payment-platform) gateways.
- **23 non-production** hosts appear **only in the React Native bundle**, never in DEX:
  `staging.zalopay.vn`, `sandbox.zalopay.com.vn`, `socialdev.zalopay.vn`, `socialstg.zalopay.vn`,
  `stgcdn/stcstg/stgcps`, `devcps`. This is a measurable release-hygiene finding: the JS bundle ships
  environment branches that the compiled Kotlin layer does not.
- **Third-party**: Alipay (`alipayobjects.com`, `mobilegw`, `render`), Visa (`dpe.consumerapi.digital.visa.com`),
  FPT.AI (`api.fpt.ai`), TrueID (`api.trueid.vn`), AppsFlyer, Firebase/Crashlytics, Sentry
  (`o529616.ingest.sentry.io`), Zalo (`graph.zaloapp.com`).
- **6 local/runtime placeholders** (`appx`, `appx-ng`, `resource`, `shared`, `usr`, `localhost`) are
  WebView/mini-program origin shims, not reachable services.

## 4. Integrity / anti-tamper posture
Root probes (`/system/bin/su`, `/system/xbin/su`, `/system/app/Superuser.apk`, `/magisk`,
`/proc/self/maps`), emulator probes (`Genymotion`, `Android Emulator (AVD) detected (+10)`,
`Emulator Detected`), hook probes (`FRIDA`), `isRooted=`/`isEmulator=` telemetry keys,
SafetyNet (`com/google/android/gms/safetynet/*`, `AttestationResult`) and Play Integrity attestation
callbacks. TLS: OkHttp `CertificatePinner` plumbing plus a first-party `TrueIDSSLPinningConfig`
(`vn/ai/trueid/sdk/models/sotp/TrueIDSSLPinningConfig`). **No embedded client secret, private key or
hard-coded bearer token was found in the string table** — signing keys are not recoverable statically
from this artifact, which is the expected (and correct) outcome for a payment client.

## 5. Sensitive-data finding (handled responsibly)
`assets/client_tracking.json` (146 KB) is a **captured production trip-tracking document** shipped in
the release: customer name, mobile number, email, tax ID and a driver record. It is not a template —
it is data. It was found during asset enumeration, **excluded from the working set and never
reproduced** in this repository; only its existence, size and schema key names are reported here so the
vendor can remove it. No other asset contained comparable personal data.

## 6. Native hardening (all 27 libraries)
Every shipped ELF is a position-independent executable with non-executable stack, GNU_RELRO,
`BIND_NOW` (full RELRO), stack canary and `_chk` fortification; no `TEXTREL`. Crashlytics NDK objects
(`libcrashlytics*`) are the only ones without RELRO/canary, which matches upstream AAR builds.
Obfuscated module names (`liba97d`, `libljsfrcwmw`, `liblytcuhe`, `libohxbee`, `librcmefqonnx`,
`libfgcpkjvqe`, `libtaibmcf`) each depend on one another (`libljsfrcwmw → librcmefqonnx`,
`libohxbee → liblytcuhe`) and export **zero** JNI symbols — they are loaded through `dlopen`/`dlsym`
indirection from `libsecurity_lite.so`-style shims rather than `System.loadLibrary` name binding.
`libfgcpkjvqe.so` (6.5 MB) pairs with `assets/fgcpkjvqe.dat` (776 KB, entropy 8.00 → encrypted blob).

## Limitations (explicit)
1. No JADX/Ghidra in the environment ⇒ no method-level decompilation; all Java statements are
   string/symbol-level, not source-level.
2. This is an **APKPure repack**: the base APK carries no JAR signature; only the split APKs carry
   `BNDLTOOL` certs. Integrity claims about the original Play build cannot be made.
3. `resources.arsc` was parsed only for its string pool; layout/resource graph not reconstructed.
4. `assets/android.1.zip` was read as a JS bundle, not executed; behaviour claims are static.
5. Obfuscated native modules were not decompiled (no decompiler available); their role is inferred
   from link graph and companion asset names, and is labelled as inference.
