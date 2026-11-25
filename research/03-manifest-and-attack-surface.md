# 03 · Manifest, components and reachable surface

> Script: `tools/analyze/02_manifest.py` · Data: `data/02-manifest.json`

## 3.1 Application node

| attribute | value | reading |
|---|---|---|
| `android:name` | `jwbmqnsll.xvndbxlk` | Application class renamed by R8 — no semantic hint, unlike `vn.com.vng.zalopay.*` |
| `allowBackup` | `false` | correct for a wallet |
| `fullBackupContent` | `false` + `dataExtractionRules` | explicit cloud-backup/transfer exclusions (AppsFlyer prefs) |
| `networkSecurityConfig` | present | see §7.1 — but permissive |
| `requestLegacyExternalStorage` | `true` | no-op on API 29+, but signals drift |
| `extractNativeLibs` | `true` | libs are deflate-compressed in the split, extracted at install |
| `largeHeap` | `true` | consistent with 17 ML models + Compose |
| `resizeableActivity` | `false` | phone-only layout assumption in 2026 |
| `appComponentFactory` | `androidx.core.app.CoreComponentFactory` | standard AndroidX |

## 3.2 Components: 265 declared, 28 reachable

| kind | declared | exported | exported with intent-filter |
|---|---:|---:|---:|
| activity | 200 | 14 | 9 |
| activity-alias | 2 | 2 | 2 |
| service | 34 | 6 | 4 |
| receiver | 22 | 8 | 8 |
| provider | 9 | 0 | 0 |

200 activities in one APK is the signature of a feature-module app that never consolidated.
The number that actually matters is **28 reachable entry points**; every one is listed with its
filters in `02-manifest.json → exported_components`.

### Exported activities of interest

| component | filter | why it is in scope |
|---|---|---|
| `…SplashScreenActivityV2` | MAIN/LAUNCHER + 6 deep links (`https://go.zalopay.vn`, `onelink.zalopay.vn`, `kmzalopay.onelink.me/TSui`, `km.zalopay.vn/TSui`, `zalopay://app`, `zalopay-vnpay:`) | the universal entry point; `autoVerify=true` means App Links are verified against `zalopay.vn` |
| `…SplashScreenGooglePaymentActivity` | `com.google.android.payments.standard.AUTHENTICATE_V1` | Google Pay authentication contract |
| `…VerificationByVNeIDActivity` | `https://go.zalopay.vn/launch/f/user-consensus-zlp-vneid` | national digital-ID (VNeID) consent flow |
| `…QRReceiverActivity` | `ACTION_SEND` + `image/*` | **accepts images from any app** → QR/OCR decode of untrusted input |
| `…IdentityCardCaptureActivity` | `vn.com.vng.zalopay.ACTION_OPEN_ML_EXTRACT_ID_CARD` | **any app can launch ID-card capture** (no permission guard) |
| `com.fpt.fci.ekycfull…EkycActivity` | none (explicit only) | third-party eKYC screen |
| `vn.ai.trueid.sdk…LivenessCameraXActivity` | none | vendor liveness camera |
| `androidx.test…InstrumentationActivityInvoker` ×3 | MAIN/LAUNCHER-ish test filters | **test harness in production** |
| `androidx.compose.ui.tooling.PreviewActivity` | `android.intent.action.MAIN` | Compose preview tooling in production |
| `com.google.android.gms.tagmanager.TagManagerPreviewActivity` | GTM preview | container-editing surface |

The two components that deserve engineering attention first are `QRReceiverActivity` and
`IdentityCardCaptureActivity`: both accept *untrusted caller data* (an image, an intent) into a
pipeline that feeds ML models and OCR. The correct defence is not "hide the component" but
**strict validation of every byte that arrives** — image dimension ceilings, QR payload schema
allow-lists, and no implicit trust in the calling UID. Whether that validation exists is a
*dynamic* question this static study cannot answer; flagging it is the contribution.

### Exported services and receivers

| component | protection | note |
|---|---|---|
| `VisaHostApduService` | `BIND_NFC_SERVICE` (system) | HCE card emulation, AID list in §3.5 |
| `TinyAppHostApduService` (Griver) + `ariver` twin | `BIND_NFC_SERVICE` | mini-app NFC capability |
| `SystemJobService`, `GlanceRemoteViewsService` | `BIND_JOB_SERVICE`, `BIND_REMOTEVIEWS` | AndroidX plumbing |
| `MyFcmListenerService` | `com.google.android.c2dm.permission.SEND` | push |
| `ZPInstallBroadcastReceiver` | none (`INSTALL_REFERRER`) | install attribution — standard, but unauthenticated by design |
| 4 × `*AppWidgetReceiver` | none (`APPWIDGET_UPDATE`) | widget refresh handlers |
| `ProfileInstallReceiver`, `DiagnosticsReceiver` | `android.permission.DUMP` | AndroidX profileinstaller / WorkManager debug — **present in release** |
| `FirebaseInstanceIdReceiver` | c2dm SEND | standard |

Permission-guarded components are *not* vulnerabilities; they are the platform's intended
mechanism. The findings register (§9) therefore treats only the unguarded, test-shaped and
data-ingesting components as actionable.

## 3.3 Permissions: 49, and what they imply

| group | count | which |
|---|---:|---|
| network/telephony state | 6 | `INTERNET`, `ACCESS_NETWORK_STATE`, `ACCESS_WIFI_STATE`, `CHANGE_WIFI_STATE`, `READ_PHONE_STATE`, `READ_BASIC_PHONE_STATE` |
| identity & contacts | 6 | `READ_CONTACTS`, `WRITE_CONTACTS`, `READ_CALENDAR`, `WRITE_CALENDAR`, `USE_BIOMETRIC`, `USE_FINGERPRINT` |
| capture & media | 4 | `CAMERA`, `RECORD_AUDIO`, `MODIFY_AUDIO_SETTINGS`, `READ_MEDIA_AUDIO` |
| payments & NFC | 3 | `NFC`, `com.android.vending.BILLING`, `com.zing.zalo.permission.ACCESS_THIRD_PARTY_APP_AUTHORIZATION` |
| ads / attribution | 7 | `ACCESS_ADSERVICES_*` ×4, `AD_ID`, `BIND_GET_INSTALL_REFERRER_SERVICE`, `QUERY_ALL_PACKAGES` |
| storage (legacy) | 3 | `READ_/WRITE_EXTERNAL_STORAGE` (ineffective on API 29+) |
| launcher / OEM | 7 | Samsung, Huawei, BBK, launcher shortcut permissions |
| misc | 13 | `POST_NOTIFICATIONS`, `FOREGROUND_SERVICE`, `WAKE_LOCK`, `VIBRATE`, `RECEIVE_BOOT_COMPLETED`, `DETECT_SCREEN_CAPTURE`, `REORDER_TASKS`, `MANAGE_OVERLAY_PERMISSION`, … |

Two entries stand out for a **wallet**: `QUERY_ALL_PACKAGES` (Play policy-restricted, normally
justified by fraud/risk engines that enumerate installed apps) and `DETECT_SCREEN_CAPTURE`
(anti-screen-recording, a genuine payment-app control). `READ_CONTACTS`/`WRITE_CONTACTS` plus
`READ_CALENDAR`/`WRITE_CALENDAR` are the largest data-minimisation liabilities in the manifest;
they are consistent with P2P transfer and bill-reminder features, and each one should be a
runtime prompt with a feature-scoped justification.

## 3.4 The test-harness leak (F-01)

```
androidx.test.core.app.InstrumentationActivityInvoker$BootstrapActivity   exported=true
androidx.test.core.app.InstrumentationActivityInvoker$EmptyActivity       exported=true
androidx.test.core.app.InstrumentationActivityInvoker$EmptyFloatingActivity exported=true
androidx.compose.ui.tooling.PreviewActivity                              exported=true
com.google.android.gms.tagmanager.TagManagerPreviewActivity              exported=true
```

`androidx.test` in a release APK means a dependency was declared with `implementation` instead of
`androidTestImplementation`. None of these components exfiltrates wallet data: they are blank
activities. But (i) they are reachable by any app on the device, (ii) they prove the release
variant inherits the test classpath, which is the same failure mode that, in other apps, has
shipped debug menus, HTTP clients with logging interceptors, and `StrictMode` to production.

## 3.5 HCE: the payment card emulation AIDs

`res/xml/apdu_service.xml` (referenced by the `apduServiceBanner` meta-data):

```
PPSE              325041592E5359532E4444463031      ("2PAY.SYS.DDF01")
Visa               A0000000031010
Visa Electron      A0000000032010
Visa V Pay         A0000000033010
Visa Interlink     A0000000033010 / A000000003
Visa proprietary   A0000000980840
```

`requireDeviceUnlock="false"` means the NFC controller may route APDUs to the service while the
device is locked. For tap-to-pay that is often required by the wallet's UX; the compensating
control must live in the app's transaction state machine and in the token's risk rules
(CDCVM, transaction limits). A static read cannot tell whether the wallet refuses to emulate a
card when the app has no unlocked session — the field's open question, and exactly the kind of
question a black-box dynamic study (§10.4) should answer next.

## 3.6 Deep-link surface

9 links, 6 distinct hosts, `autoVerify="true"` on the four `https` entry points. App Links
verification moves the trust anchor from "any app can claim the scheme" to
`assetlinks.json` published on `zalopay.vn`. The custom schemes (`zalopay://`,
`zalopay-vnpay:`) remain **unverified** by design — any installed app may register the same
scheme and race for the intent. Standard mitigation is to never trust deep-link parameters for
state changes; that is a server-side property, not observable here.
