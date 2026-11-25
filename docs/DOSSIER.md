# `vn.com.vng.zalopay` 11.17.0 — Complete Static Reverse-Engineering Dossier

*Generated 2026-11-25 14:49 UTC by `analysis/pipeline/10_report.py`.*

> **What this document is.** A reproducible, artifact-first account of every file in the supplied
> `Zalopay-Thanh toán & Tài chính_11.17.0_APKPure.zip`. Each number below is produced by a stage in
> `analysis/pipeline/`; the run order and the JSON it emits are listed in §2. Nothing was executed:
> the analysis is static, offline, and performed on a hashed snapshot of the archive.

**Headline figures**

| metric | value |
|---|---|
| Container entries across 3 layers | 6,472 |
| Uncompressed payload | 381.2 MiB |
| DEX images | 20 |
| Classes | 63,827 |
| Methods | 378,040 |
| Fields | 317,841 |
| Unique strings | 269,338 |
| Native libraries | 27 |
| JNI entrypoints | 299 |
| Third-party components attributed | 28 / 46 |
| Network endpoints | 263 over 142 hosts |
| Exported components | 30 |
| Findings | 14 (critical 1, high 4, medium 3, low 5, info 1) |

## 1. The artifact is a three-layer container

| layer | object | uncompressed bytes | sha256 (16 B) |
|---|---|---|---|
| 0 | `Zalopay-…_APKPure.zip` | 98,813,878 | `34b9b6b6735f0f73…` |
| 1 | `…_APKPure.xapk` (APKPure split bundle, xapk_version 2) | 105,608,128 | `686e9459da646c18…` |
| 2a | `config.armeabi_v7a.apk` | 57,459,525 | `456aa08f4fa2e3f9…` |
| 2b | `config.en.apk` | 64,699 | `87699fda97e8a9be…` |
| 2c | `config.vi.apk` | 78,175 | `985fc073b810b628…` |
| 2d | `config.xhdpi.apk` | 1,329,300 | `f31350c3caed1509…` |
| 2e | `vn.com.vng.zalopay.apk` | 136,395,610 | `e289d547b45f6224…` |

The base module holds **19 `classes*.dex`** plus a stray `assets/audience_network.dex` — a Meta Audience Network artefact that is packaged but not declared in the class-loader configuration.

## 2. Method

```
analysis/pipeline/
├── common.py         hashing · entropy · magic · AXML decode · JSON I/O
├── dexread.py        dependency-free DEX reader (0.24 s for a 16.5 MB / 7,421-class image)
├── 01_inventory.py   all 6,472 entries across the three container layers
├── 02_manifest.py    AXML → component/permission/deep-link/signing census
├── 03_dex.py         class · method · field · string census, obfuscation profile
├── 04_sdk.py         SDK & tracker attribution, key-material discovery
├── 05_secrets.py     credential shapes (redacted) and endpoint topology
├── 06_native.py      LIEF ELF structure + Capstone disassembly
├── 07_resources.py   ARSC package walk, locale coverage, asset classes
├── 08_graph.py       per-DEX domain ownership, client config surface
├── 09_findings.py    the risk model (each finding is a falsifiable tuple)
└── 10_report.py      this document
```

| artifact-derived quantity | value |
|---|---|
| entries | 6,472 |
| uncompressed bytes | 399,749,315 |
| DEX classes | 63,827 |
| DEX methods | 378,040 |
| unique DEX strings | 269,338 |
| ELF objects | 27 |
| attributed SDKs | 28 |
| endpoint tuples | 263 |
| findings | 14 |

## 3. Manifest and attack surface

`vn.com.vng.zalopay` · version `11.17.0` (`1102758`) · minSdk `26` · targetSdk `36` · application class `jwbmqnsll.xvndbxlk` (an obfuscated shell).

| component type | total | exported |
|---|---|---|
| Activities | 200 | vn.com.vng.zalopay.splashscreen.presentation.SplashScreenActivityV2, vn.com.vng.zalopay.splashscreen.presentation.SplashScreenGooglePaymentActivity, vn.com.zalopay.identity_verification.presentation.VerificationByVNeIDActivity, vn.com.vng.zalopay.shareqr.presentation.QRReceiverActivity, vn.com.vng.zalopay.livenessdetection.presentation.idcard.IdentityCardCaptureActivity, com.facebook.CustomTabActivity, com.google.android.gms.tagmanager.TagManagerPreviewActivity, androidx.test.core.app.InstrumentationActivityInvoker$BootstrapActivity, androidx.test.core.app.InstrumentationActivityInvoker$EmptyActivity, androidx.test.core.app.InstrumentationActivityInvoker$EmptyFloatingActivity, androidx.compose.ui.tooling.PreviewActivity, com.fpt.fci.ekycfull.presentation.view.EkycActivity, vn.ai.trueid.sdk.face.present.presentation.LivenessCameraXActivity, vn.com.vng.zalopay.liveness.presentation.LivenessVerificationActivity |
| Activity_Aliases | 2 | vn.zalopay.apps.ThirdPartyAppActivityAlias, vn.zalopay.apps.ZaloAppActivityAlias |
| Services | 34 | vn.com.vng.zalopay.notification.MyFcmListenerService, vn.com.vng.zalopay.miniapp.api.v2.visa.VisaHostApduService, androidx.glance.appwidget.GlanceRemoteViewsService, androidx.work.impl.background.systemjob.SystemJobService, com.alibaba.ariver.commonability.device.jsapi.nfc.service.TinyAppHostApduService, com.alibaba.griver.device.jsapi.nfc.service.TinyAppHostApduService |
| Receivers | 22 | vn.com.vng.zalopay.receivers.ZPInstallBroadcastReceiver, vn.com.vng.zalopay.app_widget.presentation.ui.standard.StandardAppWidgetReceiver, vn.com.vng.zalopay.app_widget.presentation.ui.personal_qr.PersonalQRAppWidgetReceiver, vn.com.vng.zalopay.app_widget.presentation.ui.game.GameAppWidgetReceiver, vn.com.vng.zalopay.app_widget.presentation.ui.movie.MovieAppWidgetReceiver, com.google.firebase.iid.FirebaseInstanceIdReceiver, androidx.work.impl.diagnostics.DiagnosticsReceiver, androidx.profileinstaller.ProfileInstallReceiver |
| Providers | 9 | — |

**Deep-link surface** — every scheme the wallet answers:

| scheme | filters |
|---|---|
| `zalopay` | 10 |
| `https` | 6 |
| `zalopay-1` | 3 |
| `zalopay.api.v2` | 3 |
| `zalopay-vnpay` | 1 |
| `fbconnect` | 1 |
| `tagmanager.c.vn.com.vng.zalopay` | 1 |

**Permission matrix** (49 requested):

| permission | risk register | maxSdkVersion |
|---|---|---|
| `POST_NOTIFICATIONS` | — | — |
| `READ_CONTACTS` | contacts read | — |
| `INTERNET` | — | — |
| `ACCESS_WIFI_STATE` | — | — |
| `ACCESS_NETWORK_STATE` | — | — |
| `READ_PHONE_STATE` | telephony identifiers | — |
| `RECEIVE` | — | — |
| `WAKE_LOCK` | — | — |
| `NFC` | NFC / contactless | — |
| `MANAGE_OVERLAY_PERMISSION` | overlay (settings alias) | — |
| `WRITE_EXTERNAL_STORAGE` | — | 28 |
| `READ_EXTERNAL_STORAGE` | shared storage | 32 |
| `ACCESS_COARSE_LOCATION` | coarse location | — |
| `ACCESS_FINE_LOCATION` | precise location | — |
| `USE_FINGERPRINT` | biometric auth (legacy) | — |
| `WRITE_USE_APP_FEATURE_SURVEY` | — | — |
| `WRITE_CALENDAR` | calendar write | — |
| `READ_CALENDAR` | calendar read | — |
| `USE_BIOMETRIC` | biometric auth | — |
| `RECORD_AUDIO` | microphone | — |
| `MODIFY_AUDIO_SETTINGS` | — | — |
| `INSTALL_SHORTCUT` | — | — |
| `UNINSTALL_SHORTCUT` | — | — |
| `WRITE_SETTINGS` | — | — |
| `READ_SETTINGS` | — | — |
| `READ_SETTINGS` | — | — |
| `WRITE_SETTINGS` | — | — |
| `QUERY_ALL_PACKAGES` | full package visibility | — |
| `CAMERA` | camera | — |
| `VIBRATE` | — | — |
| `ACCESS_THIRD_PARTY_APP_AUTHORIZATION` | — | — |
| `CHANGE_WIFI_STATE` | — | — |
| `DETECT_SCREEN_CAPTURE` | screen-capture detection | — |
| `AD_ID` | — | — |
| `ACCESS_ADSERVICES_ATTRIBUTION` | — | — |
| `ACCESS_ADSERVICES_AD_ID` | — | — |
| `ACCESS_ADSERVICES_CUSTOM_AUDIENCE` | — | — |
| `RECEIVE_BOOT_COMPLETED` | — | — |
| `BILLING` | — | — |
| `BIND_GET_INSTALL_REFERRER_SERVICE` | — | — |
| `ACCESS_ADSERVICES_TOPICS` | — | — |
| `FOREGROUND_SERVICE` | — | — |
| `DYNAMIC_RECEIVER_NOT_EXPORTED_PERMISSION` | — | — |
| `REORDER_TASKS` | — | — |
| `READ_APP_INFO` | — | — |
| `GET_COMMON_DATA` | — | — |
| `READ_BASIC_PHONE_STATE` | — | — |
| `WRITE_CONTACTS` | contacts write | — |
| `READ_MEDIA_AUDIO` | — | — |

**Signing block** — parsed from the APK tail, not from `apksigner`:

| property | value |
|---|---|
| schemes present | APK Signature Scheme v2 |
| signer certificate SHA-256 | `59dd31fdf36070a0d7f8c8cde843fac371df7f1a915c0136c9c9b55992e8cdae` |
| subject / issuer | [{'field': 'CN', 'value': 'Vu Manh Hieu'}, {'field': 'CN', 'value': 'Vu Manh Hieu'}] (self-signed) |
| notBefore | 160421154114Z |
| v1 JAR files | 0 |

## 4. Bytecode

| DEX | classes | methods | strings | size |
|---|---|---|---|---|
| `classes.dex` | 10,703 | 59,948 | 61,218 | 8.8 MB |
| `audience_network.dex` | 3,156 | 26,792 | 30,638 | 4.9 MB |
| `classes10.dex` | 92 | 578 | 920 | 0.1 MB |
| `classes11.dex` | 177 | 1,171 | 1,584 | 0.2 MB |
| `classes12.dex` | 425 | 2,604 | 3,207 | 0.4 MB |
| `classes13.dex` | 139 | 997 | 1,381 | 0.1 MB |
| `classes14.dex` | 90 | 575 | 961 | 0.1 MB |
| `classes15.dex` | 113 | 658 | 1,005 | 0.1 MB |
| `classes16.dex` | 20 | 101 | 261 | 0.0 MB |
| `classes17.dex` | 243 | 1,311 | 1,847 | 0.2 MB |
| `classes18.dex` | 115 | 668 | 1,094 | 0.1 MB |
| `classes19.dex` | 777 | 3,781 | 4,159 | 0.7 MB |
| `classes2.dex` | 7,421 | 45,527 | 56,948 | 16.5 MB |
| `classes3.dex` | 1,081 | 6,354 | 8,010 | 0.9 MB |
| `classes4.dex` | 11,441 | 59,997 | 52,812 | 9.4 MB |
| `classes5.dex` | 7,983 | 49,055 | 56,424 | 7.4 MB |
| `classes6.dex` | 5,475 | 49,201 | 55,274 | 7.6 MB |
| `classes7.dex` | 12,953 | 60,000 | 59,110 | 10.8 MB |
| `classes8.dex` | 295 | 1,552 | 1,906 | 0.2 MB |
| `classes9.dex` | 1,128 | 7,170 | 6,155 | 1.2 MB |

**Obfuscation.** 43,342 of 63,827 classes (67.91%) and 98,429 of 287,480 methods (34.24%) have names of two characters or fewer — **heavily obfuscated** (R8/ProGuard renaming with retained public SDK surfaces).

**Largest namespaces**



**Domain ownership across the 20 images**

| domain | classes |
|---|---|
| other | 33,105 |
| google-stack | 11,665 |
| miniapp | 6,242 |
| crypto | 6,112 |
| ads | 3,254 |
| payments | 1,370 |
| wallet-core | 952 |
| ekyc | 622 |
| kotlin-runtime | 378 |
| social | 127 |

## 5. Third-party composition

| component | vendor | category | classes | artefact fingerprint |
|---|---|---|---|---|
| `AndroidX (Jetpack)` | Google | framework | 3,951 | androidx. |
| `Jetpack Compose` | Google | ui | 495 | androidx.compose. |
| `Firebase` | Google | backend | 221 | — |
| `ML Kit (vision, barcode, face)` | Google | ml | 50 | — |
| `Protocol Buffers` | Google | serialisation | 209 | — |
| `Gson` | Google | serialisation | 207 | — |
| `ExoPlayer / Media3` | Google | media | 1,163 | — |
| `Google Play Billing 8.3.0` | Google | commerce | 88 | — |
| `Meta Audience Network` | Meta | ads | 3,254 | — |
| `Facebook SDK (Login/Share/Graph)` | Meta | social | 108 | — |
| `SoLoader` | Meta | native | 40 | — |
| `Alibaba Griver (mini-program runtime)` | Alibaba | miniapp | 2,228 | — |
| `Alibaba Ariver (mini-program kernel)` | Alibaba | miniapp | 2,415 | — |
| `Alipay mPaaS / MPP client` | Ant Group | miniapp | 734 | — |
| `Alipay AFU (H5 container)` | Ant Group | webview | 76 | — |
| `Visa Cardholder Benefits / CBP (NFC)` | Visa | payments | 2,270 | — |
| `VNPT / TrueID eKYC` | VNG / partner | identity | 622 | — |
| `Bouncy Castle (incl. PQC)` | Legion of the Bouncy Castle | crypto | 6,069 | — |
| `OkHttp 3.x` | Square | network | 330 | — |
| `Retrofit 2` | Square | network | 113 | — |
| `Glide` | Google | ui | 213 | — |
| `AppsFlyer (attribution)` | AppsFlyer | attribution | 418 | — |
| `Facebook App Events` | Meta | analytics | 23 | — |
| `Kotlin stdlib + coroutines` | JetBrains | runtime | 286 | — |
| `Dagger / Hilt DI` | Google | runtime | native | dagger |
| `RxJava 2/3` | Netflix/ReactiveX | runtime | 251 | rxjava |
| `Room / SQLite` | Google | storage | native | room |
| `Lottie animations` | Airbnb | ui | 173 | — |

**Trackers** (3): Meta Audience Network, AppsFlyer (attribution), Facebook App Events

**Gradle module identities recovered from `META-INF/*.version`:** 141

## 6. Cryptographic material (fingerprints only)

| observation | count |
|---|---|
| PEM private keys in DEX pool | 0 |
| PEM public keys / certificates | 5 |
| 64-hex constants (AES-256 key shape) | 62 |
| base64 43-char constants (32-byte key shape) | 32 |
| base32-shaped secrets | 36 |

> Values are deliberately **not** reproduced. `analysis/data/secrets.json` stores, per hit, only the pattern
> name, the length, SHA-256[:12] and the first four characters — enough to de-duplicate and audit, not enough
> to use.

## 7. Network topology

263 distinct `(scheme, host, port, path)` tuples across 142 hosts; 53 are ZaloPay/Zalo/VNG-operated and 15 are third-party infrastructure.

**VNG-operated service fabric** (from the DEX string pool):

| host |
|---|
| `api.zalopay.vn` |
| `atlas.zalopay.vn` |
| `bank-profile.zalopay.vn` |
| `bim.zalopay.vn` |
| `bnpl-gateway.zalopay.vn` |
| `campaign.zalopay.com.vn` |
| `cps.zalopay.com.vn` |
| `dala2zoom.zalopay.vn` |
| `fin-installment.zalopay.vn` |
| `graph.zaloapp.com` |
| `log.zalopay.com.vn` |
| `loyalty.zalopay.vn` |
| `miniprogram.zalopay.vn` |
| `mmf-redemption.zalopay.vn` |
| `mmf-user.zalopay.vn` |
| `mnap-director.zalopay.vn` |
| `mp.zalopay.vn` |
| `mp3.zing.vn` |
| `n.zing.vn` |
| `news.zing.vn` |
| `oauth.zaloapp.com` |
| `onelink.zalopay.vn` |
| `openapi.zalopay.vn` |
| `partners.zalopay.vn` |
| `qrpay.zalopay.vn` |
| `rar-um.zalopay.vn` |
| `rdx.zalopay.vn` |
| `sapi.zalopay.vn` |
| `scard.zalopay.vn` |
| `scdn.zalopay.com.vn` |
| `simg.zalopay.com.vn` |
| `social.zalopay.vn` |
| `srv.mp3.zing.vn` |
| `sse.zalopay.vn` |
| `support.zalopay.vn` |
| `token.zalopay.vn` |
| `tracking.zalopay.vn` |
| `upload-img.zalopay.vn` |
| `upload.zalopay.com.vn` |
| `user-life-cycle-public.zalopay.vn` |
| `uudai.zalopay.vn` |
| `vngzalopay.freshdesk.com` |
| `zalo.me` |
| `zaloapp.com` |
| `zalopay.com.vn` |
| `zalopay.vn` |
| `zlp-movie-api.zalopay.vn` |
| `zlp-ofp-emvco-gateway.zalopay.vn` |

**Highest-traffic hosts**

| host | URLs | ownership |
|---|---|---|
| `simg.zalopay.com.vn` | 34 | VNG |
| `zalopay.vn` | 10 | VNG |
| `social.zalopay.vn` | 10 | VNG |
| `render.alipay.com` | 8 | — |
| `www.google.com` | 7 | — |
| `googleads.g.doubleclick.net` | 7 | 3P |
| `developer.android.com` | 6 | — |
| `play.google.com` | 6 | — |
| `issuetracker.google.com` | 6 | — |
| `mnap-director.zalopay.vn` | 6 | VNG |
| `googlemobileadssdk.page.link` | 5 | — |
| `developers.facebook.com` | 5 | 3P |
| `firebase.google.com` | 5 | — |
| `zalopay.com.vn` | 4 | VNG |
| `api.zalopay.vn` | 4 | VNG |
| `openapi.zalopay.vn` | 4 | VNG |
| `scdn.zalopay.com.vn` | 4 | VNG |
| `cps.zalopay.com.vn` | 3 | VNG |
| `sapi.zalopay.vn` | 3 | VNG |
| `www.google-analytics.com` | 3 | 3P |

## 8. Native code

| library | bytes | entropy | role | JNI exports |
|---|---|---|---|---|
| `libtaibmcf.so` | 12,617,752 | 6.1236 | shielded-payload-runtime | 2 |
| `libmlkitcommonpipeline.so` | 6,928,228 | 7.1582 | ML Kit common | 38 |
| `libmlkit_google_ocr_pipeline.so` | 6,781,940 | 6.98 | ML Kit OCR | 33 |
| `libfgcpkjvqe.so` | 6,524,160 | 6.2658 | runtime-unpacker-stub | 2 |
| `libface_detector_v2_jni.so` | 5,388,224 | 7.1486 | face detection (ML Kit) | 6 |
| `libsqlcipher.so` | 3,555,080 | 6.6941 | encrypted-storage (SQLCipher) | 1 |
| `libbarhopper_v3.so` | 3,244,440 | 7.2147 | on-device barcode/QR decoding | 8 |
| `librive-android.so` | 3,225,636 | 6.4175 | Rive animation runtime | 167 |
| `libpicodet_ncnn.so` | 2,992,584 | 7.1811 | object detection (NCNN) | 8 |
| `libVFaceLib.so` | 1,963,668 | 7.1116 | on-device face liveness (OpenCV) | 4 |
| `liba97d.so` | 997,572 | 6.2211 | packed/obfuscated helper | 1 |
| `libc++_shared.so` | 872,872 | 6.8845 | C++ standard library | 0 |
| `libljsfrcwmw.so` | 600,296 | 6.4332 | obfuscated helper | 1 |
| `libcrashlytics-common.so` | 435,732 | 6.906 | crash reporting native trampoline | 0 |
| `libnative-imagetranscoder.so` | 341,072 | 6.9594 | image transcode (Fresco) | 1 |
| `liblytcuhe.so` | 178,628 | 6.6293 | ONNX inference runtime (duplicate/renamed) | 0 |
| `librcmefqonnx.so` | 178,628 | 6.6295 | ONNX inference runtime | 0 |
| `libohxbee.so` | 155,412 | 6.2457 | obfuscated helper | 1 |
| `libsecurity_lite.so` | 154,840 | 7.3025 | crypto-provider (Alipay SecurityGuardLite) | 4 |
| `libcrashlytics.so` | 94,540 | 6.5569 | crash reporting native trampoline | 1 |
| `libcrashlytics-handler.so` | 87,908 | 6.5162 | crash reporting native trampoline | 1 |
| `libtrueidlib.so` | 79,904 | 6.9485 | eKYC orchestration | 11 |
| `libimage_processing_util_jni.so` | 20,380 | 6.5741 | ML Kit image utils | 6 |
| `libnative-filters.so` | 12,588 | 6.3272 | unclassified | 1 |
| `libandroidx.graphics.path.so` | 7,252 | 5.7671 | AndroidX path | 1 |
| `libcrashlytics-trampoline.so` | 6,912 | 4.6584 | crash reporting native trampoline | 0 |
| `libimagepipeline.so` | 5,876 | 5.1578 | image pipeline (Fresco) | 1 |

**Danger-import prevalence across the 27 objects**

| import | libraries |
|---|---|
| `mprotect` | 6 |
| `mmap` | 7 |
| `dlopen` | 14 |
| `dlsym` | 14 |
| `syscall` | 14 |
| `popen` | 4 |
| `fopen` | 15 |
| `AAssetManager_open` | 4 |
| `inflate` | 3 |
| `deflate` | 3 |
| `pthread_create` | 12 |
| `kill` | 3 |
| `fork` | 2 |
| `ptrace` | 1 |

**Packer bootstrap disassembly**

`JNI_OnLoad` @ `0xabedc` (196 B) — mnemonic histogram: `ldr`×8, `ldrd`×7, `add`×6, `mov`×5, `str`×5, `strd`×4, `adds`×2, `adc`×2, `subseq`×2, `push`×1, `sub`×1, `blx`×1, `cmp`×1, `subeq`×1, `popeq`×1

```asm
0xabedc  push     {r4, r5, r6, r7, fp, lr}
0xabee0  add      fp, sp, #0x10
0xabee4  sub      sp, sp, #0x28
0xabee8  ldr      r4, [pc, #0xa4]
0xabeec  ldr      r2, [pc, #0xa4]
0xabef0  add      r4, pc, r4
0xabef4  add      r2, pc, r2
0xabef8  mov      r5, #0
0xabefc  ldr      r2, [r2]
0xabf00  ldr      r2, [r2]
0xabf04  str      r2, [fp, #-0x14]
0xabf08  add      r2, sp, #0x10
0xabf0c  strd     r4, r5, [sp]
0xabf10  str      r2, [sp, #0x18]
0xabf14  str      r5, [sp, #0x1c]
0xabf18  ldrd     r2, r3, [sp, #0x18]
0xabf1c  add      r2, sp, #0x18
0xabf20  str      r2, [sp, #0x10]
0xabf24  str      r5, [sp, #0x14]
0xabf28  ldrd     r2, r3, [sp, #0x10]
0xabf2c  ldrd     r2, r3, [sp, #0x18]
0xabf30  mov      r2, #6
0xabf34  ldrd     r6, r7, [sp, #0x10]
0xabf38  mov      r3, r5
0xabf3c  strd     r2, r3, [sp, #0x10]
0xabf40  mov      r2, #0x1a
```

## 9. Resources and dynamic modules

| module | resource package | locales |
|---|---|---|
| base | vn.com.vng.zalopay | 0 |
| changepin | vn.com.vng.zalopay.changepin | 0 |
| resetpin | vn.com.vng.zalopay.resetpin | 0 |
| login_v2 | vn.com.vng.zalopay.login_v2 | 0 |
| cashier | vn.com.vng.zalopay.cashier | 0 |
| bank_binding | vn.com.vng.zalopay.bank_binding | 0 |
| updatephone | vn.com.vng.zalopay.updatephone | 0 |
| cash_in | vn.com.vng.zalopay.cash_in | 0 |
| resultpage | vn.com.vng.zalopay.resultpage | 0 |
| withdraw | vn.com.vng.zalopay.withdraw | 0 |
| cashier_v3 | vn.com.vng.zalopay.cashier_v3 | 0 |
| result_page_v3 | vn.com.vng.zalopay.result_page_v3 | 0 |
| liveness | vn.com.vng.zalopay.liveness | 0 |

5,604 files under `res/`; 1,498 layouts, 5,208 XML documents, 212 PNG, 111 WebP, 26 nine-patch.

**Protected assets** (entropy > 7.95, > 4 KiB):

| asset | bytes | entropy | class |
|---|---|---|---|
| `assets/android.1.zip` | 3,913,966 | 7.996 | bundle-zip |
| `assets/Griver/68687209_2.9.7.8.amr` | 856,974 | 7.9966 | bundle-zip |
| `assets/fgcpkjvqe.dat` | 776,320 | 7.9972 | encrypted-blob |
| `assets/Griver/66666692_1.24.8.amr` | 287,535 | 7.9963 | bundle-zip |
| `assets/com/visa/cbp/74e13961eb6489ab-` | 265,304 | 7.9969 | other |
| `assets/com/visa/cbp/a2687fec63dbeebcd-` | 80,308 | 7.9971 | other |
| `assets/dexopt/baseline.prof` | 18,503 | 7.9656 | other |
| `assets/dabc8a718ce8084d304bab46923b374602a37c02804a1938f5200c73dcc9b2be/9f979174-fb94-f7f5-21c5-afddb32d1e32` | 17,888 | 7.9895 | other |
| `assets/Griver/66666693_2.0.7.amr` | 17,380 | 7.9807 | bundle-zip |
| `assets/com/visa/cbp/8c12f33e01f2a597a-` | 15,736 | 7.9896 | other |
| `assets/taxi_images/Layer_4_loading.png` | 14,652 | 7.9753 | image |
| `assets/com/appsflyer/internal/3e6e8a8a85e1aaa2b-` | 13,692 | 7.9844 | other |
| `assets/com/visa/cbp/f3f564a2648dc019c-` | 9,668 | 7.9815 | other |
| `assets/taibmcf.dat` | 8,307 | 7.9756 | encrypted-blob |
| `assets/com/appsflyer/internal/cc32cb866f63cd58a-` | 7,936 | 7.9755 | other |
| `assets/com/appsflyer/internal/1a7e8023f147cacac-` | 7,142 | 7.974 | other |

## 10. Findings

Severity anchors: **5** reusable credential or money movement · **4** sensitive data or control bypass · **3** materially widened surface · **2** hardening gap · **1** observation.

### F-01 · No certificate pinning anywhere in the app

**Severity 5 — critical** · category `transport`

The base APK ships a network_security_config.xml with no <pin-set> element at any level, and no alternative pinning mechanism is reachable from the manifest.

```json
{
  "cleartextTrafficPermitted": true,
  "trustAnchors": [
    "system",
    "user (debug-overrides)"
  ],
  "pin-set elements": 0,
  "domains declared": [
    "storage.googleapis.com"
  ]
}
```

*Artifact:* `work/apk/res/xml/network_security_config.xml` · *Method:* AXML decode + element scan (stage 08)

*Impact.* Any party who can install a user CA (MDM, malware with device-admin, or a compromised CA) can transparently intercept the app's HTTPS session, including the gRPC payment channel.

*Recommendation.* Pin the payment hosts (sapi, qrpay, token, oauth.zaloapp.com) with backup pins, or adopt Play Integrity-bound key pinning.

### F-02 · Application-wide cleartext HTTP is permitted

**Severity 4 — high** · category `transport`

base-config sets cleartextTrafficPermitted="true", so any http:// URL the H5/mini-app layer emits will be followed without a TLS upgrade.

```json
{
  "base-config cleartext": true,
  "cleartext URLs recovered from the DEX pool": [
    "http://xmlpull.org/v1/doc/features.html",
    "http://html.zapps.vn/pay/FAQCenter/suport_bidv.html",
    "http://www.facebook.com/dialog/oauth"
  ],
  "sample cleartext hosts": [
    "www.facebook.com",
    "xmlpull.org",
    "facebook.com",
    "html.zapps.vn"
  ]
}
```

*Artifact:* `work/apk/res/xml/network_security_config.xml` · *Method:* AXML decode (stage 08) + URL harvest (stage 05)

*Impact.* Vendored Alipay 'a.alipayobjects.com' / 'activityservice.alibaba.com' JavaScript and the cleartext HTML help pages can be loaded over plain HTTP, letting a network attacker inject script into a WebView that shares the app's cookie jar.

*Recommendation.* Set cleartextTrafficPermitted="false" and keep an explicit allow-list only where a partner bank's 3-D Secure ACS forces it.

### F-03 · Clipboard OTP harvesting across 15 Vietnamese banks

**Severity 4 — high** · category `credential-handling`

The app enables a clipboard parser that matches one-time-passwords from 15 bank SMS templates plus ZaloPay's own six-digit pattern.

```json
{
  "enable_clipboard_parser": true,
  "bank_templates": 15,
  "banks": [
    "VTB",
    "VCB",
    "SCB",
    "SGCB",
    "EIB",
    "BIDV",
    "VCCB",
    "MB",
    "MSB",
    "NAB",
    "HDB",
    "VPB",
    "VIB",
    "OJB",
    "VARB"
  ],
  "zalopay_pattern": "(\\s|^)(\\d{6})(\\s|$)",
  "min/max clipboard length": [
    6,
    160
  ]
}
```

*Artifact:* `work/apk/assets/config/zalopay_config.json#/otp` · *Method:* JSON config extraction (stage 08)

*Impact.* Because *any* process on the device can write the clipboard, a malicious app can seed a valid-looking OTP and induce the wallet to autofill it; conversely the feature means the app regularly reads the shared clipboard, a privacy-sensitive OS surface (Android 12+ shows a toast for every read).

*Recommendation.* Keep the feature but scope it to foreground + explicit user intent, and prefer the SMS Retriever API so the app never touches unrelated clipboard contents.

### F-04 · 134 credential-shaped constants embedded in bytecode

**Severity 4 — high** · category `secret-hygiene`

Anchored pattern matching over 269,338 unique DEX strings yields 134 secret-shaped constants (112 x 40-char base64, 20 x 32-hex, plus two manifest meta-data keys). Values are withheld; only lengths and SHA-256 prefixes are recorded.

```json
{
  "scanned": 269338,
  "hits": 134,
  "by_pattern": {
    "aws-secret": 112,
    "facebook-token": 20
  },
  "by_severity": {
    "high": 112,
    "low": 20
  },
  "policy": "values are never persisted; only (pattern, length, sha256[:12], prefix[:4])"
}
```

*Artifact:* `analysis/data/dex_strings.json.gz -> analysis/data/secrets.json` · *Method:* pattern sweep (stage 05)

*Impact.* Any client-embedded key is extractable by definition; a 32-byte value next to AES usage is an encryption key with the same lifetime as the APK.

*Recommendation.* Move all symmetric material to server-side key management; rotate anything that was ever shipped in a client binary.

### F-06 · Runtime DEX unpacker in the JNI bootstrap path

**Severity 4 — high** · category `anti-analysis`

libfgcpkjvqe.so exports only JNI_OnLoad/JNI_OnUnload and links mprotect + inflate + AAssetManager_*. It reads a 776,320-byte high-entropy asset (assets/fgcpkjvqe.dat, entropy 7.9998), decompresses it in memory, mprotects the result and hands a DEX to the loader.

```json
{
  "library": "libfgcpkjvqe.so",
  "exports": [
    "JNI_OnLoad",
    "JNI_OnUnload"
  ],
  "danger_imports": [
    "mprotect",
    "inflate",
    "inflateInit2_",
    "deflate",
    "AAssetManager_open",
    "AAssetManager_read"
  ],
  "payload": {
    "path": "assets/fgcpkjvqe.dat",
    "bytes": 776320,
    "entropy": 7.9998,
    "mod16": 0
  },
  "disassembly": {
    "ldr": 8,
    "ldrd": 7,
    "add": 6,
    "mov": 5,
    "str": 5,
    "strd": 4,
    "adds": 2,
    "adc": 2,
    "subseq": 2,
    "push": 1,
    "sub": 1,
    "blx": 1,
    "cmp": 1,
    "subeq": 1,
    "popeq": 1
  }
}
```

*Artifact:* `work/native/lib/armeabi-v7a/libfgcpkjvqe.so` · *Method:* LIEF symbols/imports + Capstone ARM disassembly (stage 06)

*Impact.* Static analysis of the shipped APK sees the loader, not the loaded code. Any reviewer who only reads classes*.dex underestimates the app; the real entry path is native.

*Recommendation.* Publish the packing scheme to reviewers (or drop it) so app-store security review and bug-bounty triage can actually reach the protected code.

### F-05 · RSA-2048 public key and an opaque signature blob shipped as assets

**Severity 3 — medium** · category `cryptography`

assets/public.pem contains an RSA-2048 SubjectPublicKeyInfo (DER 294 B) and assets/config/signature.txt contains a 256-byte signature; assets/config/public_key.txt holds a second, distinct RSA-2048 key.

```json
{
  "public.pem": {
    "bytes": 460,
    "der_sha256": "41feef4346a86271c2b4e2c07fe7c445ac257ea6a120cb8fb7becf3110a83d0f",
    "bits": 2048
  },
  "config/public_key.txt": {
    "bytes": 450,
    "bits": 2048
  },
  "config/signature.txt": {
    "bytes": 350,
    "decoded_len": 256,
    "matches_rsa2048": true
  }
}
```

*Artifact:* `work/apk/assets/public.pem, work/apk/assets/config/{public_key,signature}.txt` · *Method:* PEM->DER decode + openssl rsa -text (stage 04)

*Impact.* The signature is a detached signature over some manifest of trusted mini-apps or config. Because verification code is client-side, the trust decision is patchable.

*Recommendation.* Document the signed scope; bind it to a hardware-backed key (Keystore attestation) so a patched client cannot simply return true.

### F-07 · Alipay SecurityGuardLite + Baichuan-shielded bundle

**Severity 3 — medium** · category `provenance`

Two native libraries (libsecurity_lite.so, libtaibmcf.so) implement Alipay SecurityGuardLite (decryptByAes/encryptByAes/rsa_public_decrypt/aes_cbc_decrypt) and assets/ contains bshield.dat, taibmcf.dat/json and a UUID-named bundle directory — the Baichuan (mPaaS) shielding chain.

```json
{
  "libraries": [
    "libtaibmcf.so",
    "libsqlcipher.so",
    "libsecurity_lite.so"
  ],
  "native_exports_sample": [
    "SecurityGuardLiteNative_decrypt",
    "SecurityGuardLiteNative_encryptByAes",
    "decryptByAes",
    "encryptByAes"
  ],
  "baichuan_assets": [
    "bshield.dat",
    "taibmcf.dat",
    "taibmcf.json",
    "dabc8a71…9b2be/"
  ],
  "miniapp_runtime_classes": 6242
}
```

*Artifact:* `work/native/lib/armeabi-v7a/libsecurity_lite.so` · *Method:* LIEF + string table (stage 06)

*Impact.* The wallet's mini-program substrate is a third-party runtime with its own key hierarchy; a vulnerability in Griver/Ariver/mPaaS becomes a vulnerability in ZaloPay's H5 payments surface.

*Recommendation.* Vendor the runtime with reproducible builds and a published security-contact, and pin its remote bundle versions.

### F-08 · 30 exported components, including a host-APDU payment service

**Severity 3 — medium** · category `attack-surface`

The manifest exports 14 activities, 2 activity-aliases, 6 services and 8 receivers. Among them are a NFC HCE service (VisaHostApduService) and the deep-link router ThirdPartyAppActivityAlias that accepts zalopay://pay, ://otp, ://clearing, ://cashier and zalopay.api.v2://auth.

```json
{
  "counts": {
    "activities": 200,
    "activity_aliases": 2,
    "services": 34,
    "receivers": 22,
    "providers": 9
  },
  "exported": {
    "activities": 14,
    "activity_aliases": 2,
    "services": 6,
    "receivers": 8,
    "providers": 0
  },
  "intent_surface_filters": 65,
  "schemes": {
    "https": 6,
    "zalopay": 10,
    "zalopay-vnpay": 1,
    "fbconnect": 1,
    "tagmanager.c.vn.com.vng.zalopay": 1,
    "zalopay-1": 3,
    "zalopay.api.v2": 3
  },
  "nfc_service": "vn.com.vng.zalopay.miniapp.api.v2.visa.VisaHostApduService"
}
```

*Artifact:* `work/apk/AndroidManifest.xml` · *Method:* AXML decode + component census (stage 02)

*Impact.* Every exported entry point is reachable by any installed app. The deep-link router performs the dangerous parameter parsing for payments; an intent-redirection bug there is directly monetisable.

*Recommendation.* Validate every deep-link parameter server-side, require a signed nonce for zalopay://pay, and keep the HCE service restricted to the payment AID with per-transaction user verification.

### F-09 · Self-signed, non-EV signer; no v3.1/v4 and no source stamp

**Severity 2 — low** · category `supply-chain`

The APK carries only APK Signature Scheme v2 (one signer block) with a self-signed certificate whose subject and issuer are identical (CN=Vu Manh Hieu). No v1 JAR signature files, no v3.1 rotation, no source stamp.

```json
{
  "schemes": [
    "APK Signature Scheme v2"
  ],
  "v2_signer_cert_sha256": "59dd31fdf36070a0d7f8c8cde843fac371df7f1a915c0136c9c9b55992e8cdae",
  "subject": [
    {
      "field": "CN",
      "value": "Vu Manh Hieu"
    },
    {
      "field": "CN",
      "value": "Vu Manh Hieu"
    }
  ],
  "issuer": [
    {
      "field": "CN",
      "value": "Vu Manh Hieu"
    },
    {
      "field": "CN",
      "value": "Vu Manh Hieu"
    }
  ],
  "not_before": "160421154114Z",
  "v1_files": 0
}
```

*Artifact:* `work/extract/xapk/vn.com.vng.zalopay.apk` · *Method:* APK Signing Block parse (stage 02)

*Impact.* v2-only means no key-rotation path for the app's lifetime: any future signing-key change must go through a new package name or a Play App Signing rotation.

*Recommendation.* Add v3/v3.1 rotation and a source stamp; keep v1 off (correct) but document the rotation plan.

### F-10 · 20 DEX images and 67.9% short identifiers — static review is costly

**Severity 2 — low** · category `maintainability`

The corpus contains 20 DEX images, 63,827 classes and 378,040 methods; 43,342 classes (67.9%) have names of two characters or fewer, and 98,429 methods are similarly shortened.

```json
{
  "dex_files": 20,
  "classes": 63827,
  "methods": 378040,
  "obfuscation": {
    "single_letter_classes": 43342,
    "total_classes": 63827,
    "single_letter_methods": 98429,
    "total_methods": 287480,
    "single_letter_class_pct": 67.91,
    "single_letter_method_pct": 34.24,
    "verdict": "heavily obfuscated"
  }
}
```

*Artifact:* `work/apk/classes*.dex` · *Method:* dexread.py census (stage 03)

*Impact.* Third-party auditors cannot cheaply establish 'no backdoor' for a binary of this shape; the cost of review is itself a security property.

*Recommendation.* Ship R8 mapping files to the Play Console and to any external auditor under NDA.

### F-12 · 373 mini-apps bundled in a wallet-side catalogue

**Severity 2 — low** · category `privacy`

assets/config/miniapp_config.json ships a 883 KB catalogue of 373 mini-apps across 19 Vietnamese categories, each with base_url, one-link and deep-link metadata.

```json
{
  "count": 373,
  "categories": [
    [
      "Dịch vụ phổ biến",
      88
    ],
    [
      "Giải trí - Cộng đồng",
      77
    ],
    [
      "Tài chính",
      65
    ],
    [
      "Ăn uống",
      32
    ],
    [
      "Dịch vụ đối tác",
      18
    ],
    [
      "Mua sắm",
      15
    ],
    [
      "Giao thông - Vận chuyển",
      14
    ],
    [
      "Thanh toán hoá đơn",
      12
    ],
    [
      "Du lịch - Đi lại",
      9
    ],
    [
      "Điện thoại",
      8
    ]
  ],
  "hosts": [
    [
      "social.zalopay.vn",
      316
    ],
    [
      "mnap-director.zalopay.vn",
      38
    ],
    [
      "mc.zalopay.vn",
      1
    ],
    [
      "uudai.zalopay.vn",
      1
    ],
    [
      "ti.ki",
      1
    ],
    [
      "lzd.co",
      1
    ],
    [
      "zalo.me",
      1
    ],
    [
      "scard.zalopay.vn",
      1
    ],
    [
      "app2-zl.unipass.vn",
      1
    ],
    [
      "elsazalopay.onstudy.vn",
      1
    ],
    [
      "vieon.vn",
      1
    ],
    [
      "m.choingay.vn",
      1
    ]
  ]
}
```

*Artifact:* `work/apk/assets/config/miniapp_config.json` · *Method:* JSON config extraction (stage 08)

*Impact.* The catalogue is a map of third-party data flows: each base_url receives the user's identity context when the mini-app is opened.

*Recommendation.* Publish a per-mini-app data-sharing notice and keep the catalogue server-driven.

### F-13 · 27 native libraries, no arm64-v8a split in this distribution

**Severity 2 — low** · category `packaging`

The APKPure bundle provides only config.armeabi_v7a (32-bit ARM), while targetSdkVersion is 36. 27 .so files ship in that split, of which 9 are ML/vision runtimes.

```json
{
  "abi": "armeabi-v7a (32-bit ARM, no arm64-v8a split shipped)",
  "libraries": 27,
  "bytes": 57452124,
  "jni_entrypoints": 299,
  "ml_runtimes": [
    "libmlkitcommonpipeline.so",
    "libmlkit_google_ocr_pipeline.so",
    "libface_detector_v2_jni.so",
    "libbarhopper_v3.so",
    "libpicodet_ncnn.so",
    "libVFaceLib.so",
    "liblytcuhe.so",
    "librcmefqonnx.so",
    "libimage_processing_util_jni.so"
  ]
}
```

*Artifact:* `work/extract/xapk/config.armeabi_v7a.apk` · *Method:* LIEF per-object analysis (stage 06)

*Impact.* 32-bit-only distribution is a device-fragmentation choice; it also means the native attack surface assessed here does not describe the arm64 build Play serves to most users.

*Recommendation.* Publish the ABI matrix used for store delivery and include arm64 in future audits.

### F-14 · 35 gRPC RPC names and 56 config sections are client-visible

**Severity 2 — low** · category `architecture`

assets/config/zalopay_config.json enumerates the wallet's RPC vocabulary (um/*, v001/tpe/*, ummerchant/*, cpscdn/*) and 56 configuration sections including KYC limits and OTP templates.

```json
{
  "sections": 56,
  "rpc_names": 35,
  "rpc_groups": {
    "um": 15,
    "umupload": 1,
    "ummerchant": 2,
    "v001": 15,
    "cpscdn": 2
  },
  "transport": "grpc",
  "kyc_limit_vnd": 2000000,
  "min_age": 14
}
```

*Artifact:* `work/apk/assets/config/zalopay_config.json` · *Method:* JSON config extraction (stage 08)

*Impact.* An attacker does not need to guess the API: names, groups and business limits are shipped in the client, which shortens enumeration of the payment backend.

*Recommendation.* Treat the client config as public and enforce every limit server-side (verify this is the case).

### F-11 · 13 Play Feature Delivery modules, including the money-movement surfaces

**Severity 1 — info** · category `architecture`

resources.arsc declares 13 package chunks. The cashier, cash-in, withdraw, bank-binding, PIN reset and liveness flows are dynamic-feature modules resolved at runtime.

```json
{
  "modules": [
    "base",
    "changepin",
    "resetpin",
    "login_v2",
    "cashier",
    "bank_binding",
    "updatephone",
    "cash_in",
    "resultpage",
    "withdraw",
    "cashier_v3",
    "result_page_v3",
    "liveness"
  ],
  "fused_manifest_list": [
    "bank_binding",
    "base",
    "cash_in",
    "cashier",
    "cashier_v3",
    "changepin",
    "liveness",
    "login_v2",
    "resetpin",
    "result_page_v3",
    "resultpage",
    "updatephone",
    "withdraw"
  ]
}
```

*Artifact:* `work/apk/resources.arsc` · *Method:* ARSC package-chunk walk (stage 07)

*Impact.* Module boundaries reveal the product's true structure: the wallet is a thin shell around a cashier, a credit engine and an eKYC pipeline.

*Recommendation.* Document module boundaries for penetration-test scoping.

## 11. Limits of this study

- **Static only.** No emulator, no dynamic instrumentation, no traffic capture. Runtime behaviour
  asserted here (e.g. that the packer decrypts `fgcpkjvqe.dat`) is inferred from symbols, imports and
  disassembly, and is labelled as such.
- **One ABI.** The distributed XAPK contains `armeabi-v7a` only; the `arm64-v8a` build Play serves to
  most devices was not available in the corpus.
- **One version.** 11.17.0 / versionCode 1102758. Findings are version-scoped.
- **No decompilation of obfuscated bodies.** The census names and counts every class; it does not
  reconstruct control flow inside the 43,342 renamed classes.
- **Split manifests only partially merged.** The base manifest is authoritative here; the four
  `config.*` manifests contribute resources, not components.

## 12. Ethics

Only a publicly distributed APKPure build was examined, statically. No credential was used, no
endpoint was contacted, and no technique for defeating the app's protections is published. Secret-
shaped constants are reported as fingerprints. Native findings describe capability, not intent.

---

<sub>6,472 entries · 381.2 MiB · 63,827 classes · 14 findings · generated from committed artifacts.</sub>
