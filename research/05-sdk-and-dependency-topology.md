# 05 · SDK inventory and dependency topology

> Scripts: `04_sdks.py`, `06_signals.py` · Data: `data/04-sdks.json`, `data/06-signals.json`

## 5.1 Method: fingerprint by *namespace, asset, library and string*

R8 renames first-party code, but it keeps the public surface of libraries that ship consumer
ProGuard rules — so **package prefixes are a reliable SDK oracle** for third-party code. Each
vendor entry in `04_sdks.py` requires at least one class-prefix hit and is corroborated by a
marker (an asset path, a native library name, or a distinctive string). Overlapping prefixes are
reported honestly: `androidx.compose` is a subset of `androidx`, `firebase` is a subset of `gms`.

## 5.2 The inventory (39 matched vendors)

### Payments & commerce — 5 SDKs, 3 941 classes

| SDK | classes | role in the app |
|---|---:|---|
| **Vis**a CyberSource (CBP) | 1 370 | card-not-present processing UI (`RecipientPaymentInstrument`, `getProtectionType`) |
| **IAP** (VNG in-app purchase) | 1 599 | ZaloPay's own store/billing rails |
| **Alipay** SDK | 493 | cross-border acceptance + `SecurityGuardLite` native signer |
| **VNPAY** SDK | 388 | domestic gateway interop |
| Google Play Billing | 88 | Play-managed purchases |

Five competing payment SDKs in one binary is not redundancy for its own sake: each is a
*contract* with a different scheme, and each carries its own crypto and network stack. It is also
five separate supply chains that can invalidate a PCI/EMV assessment independently.

### Platform & UI

| SDK | classes | note |
|---|---:|---|
| Google Play services (GMS) | 5 933 | ads 40 · tagmanager 4 · location 36 · maps 5 · wallet 3 · auth 1 · the rest base/phenotype |
| AndroidX / Jetpack | 4 250 | media3 1 198 · camera 813 · compose 504 · work 152 · room 1 |
| Kotlin stdlib + coroutines | 369 | Kotlin-first codebase |
| OkHttp + Okio | 442 | the only HTTP client worth noting |
| Retrofit | 137 | typed API layer |
| Glide / Fresco | 307 | images |
| RxJava | 266 | alongside coroutines — two async paradigms |
| Dagger/Hilt | 51 | DI |
| Lottie / Rive | 330 | motion assets (`res/raw/failure.riv`) |
| Media3/ExoPlayer | 1 198 | video (movie-ticket feature) |

### Cryptography

| SDK | classes | consequence |
|---|---:|---|
| **BouncyCastle** | 6 112 | a full JCA provider ships in-app — 10 % of the binary |
| Nimbus JOSE+JWT | 361 | JWS/JWE handling |
| SQLCipher (`net.zetetic`) | 57 + `libsqlcipher.so` 3.5 MB | encrypted local database |

Three independent crypto stacks plus a white-box shield. The risk is not "which is weak" but
*which one an individual code path chose*. A single canonical crypto facade is the control that
prevents algorithm drift.

### WebView container: two mini-app runtimes, 4 643 classes

| component | classes |
|---|---:|
| `com.alibaba.griver.*` | 2 228 |
| `com.alibaba.ariver.*` | 2 415 |

Griver/Ariver is Alibaba's mini-program runtime, and it is the single largest *behavioural*
subsystem after GMS. Evidence in `assets/`: `GriverBridge/h5_bridge.js`,
`GriverBridge/workerjs.js`, `jsbridge.js`, `internaljsbridge.js`, `GriverErrorPage/`,
`containererrorpage/`, plus Java-side `com.alibaba.griver…jsapi.nfc.TinyAppHostApduService`.
**A mini-app runtime with an NFC bridge means third-party JavaScript can reach an HCE channel.**
The permission-guarding is on the Android side; the JS-to-native authorisation is a policy
question that lives inside the container and is not visible statically at this granularity.

### Attribution, ads, social

| SDK | classes | domains observed |
|---|---:|---|
| AppsFlyer | 430 | `appsflyer` markers, install-referrer receiver, `dataExtractionRules` exclusions |
| Facebook SDK + Audience Network | 531 (166 ads) | `fbconnect`, `CustomTabActivity` |
| Google Ads / AdMob | 40 + `MobileAdsInitProvider` | `googleads.g.doubleclick.net` |
| Adjust / Branch / CleverTap | 0 | **absent** — attribution is AppsFlyer-only |

### On-device ML (see §7.3 for models)

| SDK | evidence |
|---|---|
| ML Kit barcode (`libbarhopper_v3.so`, 3.2 MB) | `BarhopperV3_recognizeBitmapNative` |
| ML Kit OCR pipeline (`libmlkit_google_ocr_pipeline.so`, 6.8 MB) | 33 JNI exports |
| ML Kit face (`libface_detector_v2_jni.so`, 5.4 MB) | `FaceDetectorV2Jni_detectFaces…` |
| NCNN + PicoDet (`libpicodet_ncnn.so`) | `com.example.picodet.PicoDetJNI` — note the *example* package name |
| TrueID eKYC (`libtrueidlib.so`, `libVFaceLib.so`) | `vn.ai.trueid`, `vn.ai.faceauth` |
| Rive (`librive-android.so`, 167 JNI exports) | runtime animation VM |

`libtrueidlib.so` deserves a call-out for engineering quality: it exposes
`SmartOTPHelper_vaultCheckDebugger`, `_vaultCheckIntegrity`, `_vaultAuthCeremony`,
`_vaultComputeQReg`, `_vaultX25519Pubkey`, `_vaultVerifyTxDigest` — a vault with X25519 key
agreement and integrity self-checks, i.e. an SDK that took the threat model seriously.

## 5.3 Concentration analysis

```
namespace roots observed : 28 (with ≥1 class)
HHI (sum of squared shares): 0.0364
top-10 root share         : 43.8 %
largest single vendor     : 12.7 % (com.google)
first-party share         : 1.57 %
```

For comparison, a monolith with no third-party code would score HHI = 1.0. ZaloPay's 0.036 is
close to the *theoretical floor* for 28 roots (1/28 = 0.036), i.e. the binary is as
dependency-diverse as its root count allows. Operationally that means: **no single vendor
compromise takes the app down, but no single vendor upgrade fixes it either.**

## 5.4 Network reach: 151 hosts

| class | hosts | references | examples |
|---|---:|---:|---|
| ZaloPay first-party | 43 | 122 | `api.zalopay.vn`, `openapi.zalopay.vn`, `simg.zalopay.com.vn` (34 refs), `scdn`, `cps`, `mnap-director` |
| unclassified | 53 | 71 | partner endpoints, regional CDNs |
| Google | 21 | 54 | `play.google.com`, `googleads.g.doubleclick.net`, `app-measurement` |
| payment schemes/PSPs | 20 | 30 | Visa, Napas, Alipay (`render.alipay.com` × 8), VNPAY |
| Facebook/Meta | 4 | 10 | `graph.facebook.com`, `fbcdn` |
| VNG group | 5 | 6 | `zing.vn`, `zalo.me` |
| developer/docs noise | 4 | 11 | `w3.org`, `xml.org`, `github.com` |
| ads | 1 | 1 | one ad host survived (most ad traffic is SDK-internal) |

`render.alipay.com` appearing 8 times is the cross-border mini-program renderer; it is a
first-class *code execution* origin inside the WebView container and therefore belongs in the
threat model alongside `go.zalopay.vn`.

## 5.5 Reading of the topology

1. **The app is a shell around three runtimes**: a mini-program WebView (Griver/Ariver), an ML
   inference stack (ML Kit + NCNN), and a payments switchboard (Visa, Alipay, VNPAY, IAP, Play).
2. **Two async paradigms, three crypto stacks, two UI toolkits, five payment SDKs** — every one
   of these is a defensible local decision that becomes a global consistency risk.
3. **Attribution is consolidated** (AppsFlyer only, no Adjust/Branch/CleverTap) — a good sign:
   someone actively removed redundant trackers.
4. **The dependency surface is the audit surface.** 39 vendors × 151 hosts × 284 JNI symbols is
   the real perimeter; §9 turns this into a prioritised register.
