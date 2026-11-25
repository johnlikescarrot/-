# Zalopay 11.17.0 (vn.com.vng.zalopay) - Comprehensive Reverse Engineering Report

> Generated: 2026-11-25 | Source: `Zalopay-Thanh+toán+&+Tài+chính_11.17.0_APKPure.zip`  
> Scope: static reverse engineering of **all files** in the ZIP/XAPK/APK splits. No dynamic instrumentation performed.

## 1. Artifact Inventory

- Outer ZIP: `Zalopay-Thanh+toán+&+Tài+chính_11.17.0_APKPure.zip` (98,813,878 bytes)
- XAPK payload files: 7 | Embedded APKs: 5
- Total embedded files across APKs: 6,464 | Uncompressed: 195,327,309 bytes

### XAPK contents

| File | Size (bytes) | SHA-256 | Type |
|---|---:|---|---|
| `vn.com.vng.zalopay.apk` | 79,359,087 | `e289d547b45f6224aed8ce7bf72d75ea5545cd50c0e768d1a74a3e8165fe6a03` | `.apk` |
| `icon.png` | 23,841 | `f0b9d7d1a6144ca550e4d326bc3b6cf93696549675bc624d7774c9879c33d3f0` | `.png` |
| `config.armeabi_v7a.apk` | 24,828,408 | `456aa08f4fa2e3f952657519ccf0b3f89a22cf8af040312425e926355c01987b` | `.apk` |
| `config.en.apk` | 74,137 | `87699fda97e8a9be06583d9261689966d49a749569fbfa39092d09bd84f49942` | `.apk` |
| `config.vi.apk` | 86,425 | `985fc073b810b6286c116400a88e5552616a05fc57f8a5376599c8bfaa963e33` | `.apk` |
| `config.xhdpi.apk` | 1,232,563 | `f31350c3caed15099d2c2d325d88fef52268960ef66b1496eb099a19203aaa3b` | `.apk` |
| `manifest.json` | 2,710 | `1382fb85261a3f58699396b832c4c68e0b206e8188ca835727c4f1f84b511ca0` | `.json` |

### Per-APK summary

| APK | Files | Uncompressed size |
|---|---:|---:|
| `vn.com.vng.zalopay.apk` | 6,162 | 136,395,610 |
| `config.armeabi_v7a.apk` | 32 | 57,459,525 |
| `config.en.apk` | 6 | 64,699 |
| `config.vi.apk` | 6 | 78,175 |
| `config.xhdpi.apk` | 258 | 1,329,300 |

### File type distribution (all APKs)

| Extension | Count |
|---|---:|
| `.xml` | 5,320 |
| `.png` | 318 |
| `.webp` | 159 |
| `.version` | 141 |
| `.txt` | 136 |
| `.properties` | 79 |
| `.json` | 64 |
| `.so` | 28 |
| `.ttf` | 23 |
| `.dex` | 20 |
| `(none)` | 18 |
| `.tflite` | 14 |
| `.kotlin_module` | 11 |
| `.binarypb` | 10 |
| `.proto` | 10 |
| `.js` | 9 |
| `.html` | 9 |
| `.kotlin_builtins` | 8 |
| `.pb` | 6 |
| `.gif` | 5 |
| `.emd` | 4 |
| `.riv` | 4 |
| `.arsc` | 4 |
| `.sf` | 4 |
| `.rsa` | 4 |
| `.mf` | 4 |
| `.h` | 3 |
| `.amr` | 3 |
| `.dat` | 3 |
| `.bin` | 2 |
| `.md` | 2 |
| `.d` | 2 |
| `.m` | 2 |
| `.fb` | 2 |
| `.bincfg` | 2 |
| `.tfl` | 2 |
| `.jpg` | 2 |
| `.otf` | 2 |
| `.mp3` | 2 |
| `.svg` | 2 |
| `.processors` | 1 |
| `.g` | 1 |
| `.c` | 1 |
| `.generatedextensionregistryloader` | 1 |
| `.j` | 1 |
| `.l` | 1 |
| `.p` | 1 |
| `.provider` | 1 |
| `.processor` | 1 |
| `.a` | 1 |
| `.textproto` | 1 |
| `.param` | 1 |
| `.zip` | 1 |
| `.prof` | 1 |
| `.profm` | 1 |
| `.conv_model` | 1 |
| `.lstm_model` | 1 |
| `.pem` | 1 |
| `.public` | 1 |
| `.gz` | 1 |
| `.yaml` | 1 |

## 2. Application Identity & SDK

- Package: `vn.com.vng.zalopay` | App: **Zalopay** | Version: `11.17.0` (1102758)
- Min SDK: 26 | Target SDK: 36 | Max SDK: None
- Main activity: `vn.com.vng.zalopay.splashscreen.presentation.SplashScreenActivityV2`
- Signed: True (v1=False, v2=True, v3=True) | Files in main APK: 6,162

## 3. Signing Certificates

- Subject: `Common Name: Vu Manh Hieu` | Issuer: `Common Name: Vu Manh Hieu`
  - SHA-256: `59 dd 31 fd f3 60 70 a0 d7 f8 c8 cd e8 43 fa c3 71 df 7f 1a 91 5c 01 36 c9 c9 b5 59 92 e8 cd ae` | SHA-1: `50 21 95 a8 d3 e7 c0 5c 60 86 db 45 e9 43 b1 6e fc d2 4e 5d`
  - Serial: `356428285` | Validity: `2016-04-21 15:41:14+00:00` → `2041-04-15 15:41:14+00:00`

## 4. Permissions

- `android.permission.ACCESS_ADSERVICES_AD_ID`
- `android.permission.ACCESS_ADSERVICES_ATTRIBUTION`
- `android.permission.ACCESS_ADSERVICES_CUSTOM_AUDIENCE`
- `android.permission.ACCESS_ADSERVICES_TOPICS`
- `android.permission.ACCESS_COARSE_LOCATION`
- `android.permission.ACCESS_FINE_LOCATION`
- `android.permission.ACCESS_NETWORK_STATE`
- `android.permission.ACCESS_WIFI_STATE`
- `android.permission.CAMERA`
- `android.permission.CHANGE_WIFI_STATE`
- `android.permission.DETECT_SCREEN_CAPTURE`
- `android.permission.FOREGROUND_SERVICE`
- `android.permission.INTERNET`
- `android.permission.MODIFY_AUDIO_SETTINGS`
- `android.permission.NFC`
- `android.permission.POST_NOTIFICATIONS`
- `android.permission.QUERY_ALL_PACKAGES`
- `android.permission.READ_BASIC_PHONE_STATE`
- `android.permission.READ_CALENDAR`
- `android.permission.READ_CONTACTS`
- `android.permission.READ_EXTERNAL_STORAGE`
- `android.permission.READ_MEDIA_AUDIO`
- `android.permission.READ_PHONE_STATE`
- `android.permission.RECEIVE_BOOT_COMPLETED`
- `android.permission.RECORD_AUDIO`
- `android.permission.REORDER_TASKS`
- `android.permission.USE_BIOMETRIC`
- `android.permission.USE_FINGERPRINT`
- `android.permission.VIBRATE`
- `android.permission.WAKE_LOCK`
- `android.permission.WRITE_CALENDAR`
- `android.permission.WRITE_CONTACTS`
- `android.permission.WRITE_EXTERNAL_STORAGE`
- `android.settings.action.MANAGE_OVERLAY_PERMISSION`
- `com.android.launcher.permission.INSTALL_SHORTCUT`
- `com.android.launcher.permission.READ_SETTINGS`
- `com.android.launcher.permission.UNINSTALL_SHORTCUT`
- `com.android.launcher.permission.WRITE_SETTINGS`
- `com.android.vending.BILLING`
- `com.bbk.launcher2.permission.READ_SETTINGS`
- `com.bbk.launcher2.permission.WRITE_SETTINGS`
- `com.google.android.c2dm.permission.RECEIVE`
- `com.google.android.finsky.permission.BIND_GET_INSTALL_REFERRER_SERVICE`
- `com.google.android.gms.permission.AD_ID`
- `com.huawei.appmarket.service.commondata.permission.GET_COMMON_DATA`
- `com.samsung.android.mapsagent.permission.READ_APP_INFO`
- `com.samsung.android.providers.context.permission.WRITE_USE_APP_FEATURE_SURVEY`
- `com.zing.zalo.permission.ACCESS_THIRD_PARTY_APP_AUTHORIZATION`
- `vn.com.vng.zalopay.DYNAMIC_RECEIVER_NOT_EXPORTED_PERMISSION`

## 5. Components

- Activities: 200 | Services: 34 | Receivers: 22 | Providers: 9
- Exported (explicit/intent-filter implied): 28

### Exported activities (14)

- `vn.com.vng.zalopay.splashscreen.presentation.SplashScreenActivityV2` | exported=true | intent-filter=True | permission=``
- `vn.com.vng.zalopay.splashscreen.presentation.SplashScreenGooglePaymentActivity` | exported=true | intent-filter=True | permission=``
- `vn.com.zalopay.identity_verification.presentation.VerificationByVNeIDActivity` | exported=true | intent-filter=True | permission=``
- `vn.com.vng.zalopay.shareqr.presentation.QRReceiverActivity` | exported=true | intent-filter=True | permission=``
- `vn.com.vng.zalopay.livenessdetection.presentation.idcard.IdentityCardCaptureActivity` | exported=true | intent-filter=True | permission=``
- `com.facebook.CustomTabActivity` | exported=true | intent-filter=True | permission=``
- `com.google.android.gms.tagmanager.TagManagerPreviewActivity` | exported=true | intent-filter=True | permission=``
- `androidx.test.core.app.InstrumentationActivityInvoker$BootstrapActivity` | exported=true | intent-filter=True | permission=``
- `androidx.test.core.app.InstrumentationActivityInvoker$EmptyActivity` | exported=true | intent-filter=True | permission=``
- `androidx.test.core.app.InstrumentationActivityInvoker$EmptyFloatingActivity` | exported=true | intent-filter=True | permission=``
- `androidx.compose.ui.tooling.PreviewActivity` | exported=true | intent-filter=False | permission=``
- `com.fpt.fci.ekycfull.presentation.view.EkycActivity` | exported=true | intent-filter=False | permission=``
- `vn.ai.trueid.sdk.face.present.presentation.LivenessCameraXActivity` | exported=true | intent-filter=False | permission=``
- `vn.com.vng.zalopay.liveness.presentation.LivenessVerificationActivity` | exported=true | intent-filter=False | permission=``
### Exported services (6)

- `vn.com.vng.zalopay.notification.MyFcmListenerService` | exported=true | intent-filter=True | permission=``
- `vn.com.vng.zalopay.miniapp.api.v2.visa.VisaHostApduService` | exported=true | intent-filter=True | permission=`android.permission.BIND_NFC_SERVICE`
- `androidx.glance.appwidget.GlanceRemoteViewsService` | exported=true | intent-filter=False | permission=`android.permission.BIND_REMOTEVIEWS`
- `androidx.work.impl.background.systemjob.SystemJobService` | exported=true | intent-filter=False | permission=`android.permission.BIND_JOB_SERVICE`
- `com.alibaba.ariver.commonability.device.jsapi.nfc.service.TinyAppHostApduService` | exported=true | intent-filter=True | permission=`android.permission.BIND_NFC_SERVICE`
- `com.alibaba.griver.device.jsapi.nfc.service.TinyAppHostApduService` | exported=true | intent-filter=True | permission=`android.permission.BIND_NFC_SERVICE`
### Exported receivers (8)

- `vn.com.vng.zalopay.receivers.ZPInstallBroadcastReceiver` | exported=true | intent-filter=True | permission=``
- `vn.com.vng.zalopay.app_widget.presentation.ui.standard.StandardAppWidgetReceiver` | exported=true | intent-filter=True | permission=``
- `vn.com.vng.zalopay.app_widget.presentation.ui.personal_qr.PersonalQRAppWidgetReceiver` | exported=true | intent-filter=True | permission=``
- `vn.com.vng.zalopay.app_widget.presentation.ui.game.GameAppWidgetReceiver` | exported=true | intent-filter=True | permission=``
- `vn.com.vng.zalopay.app_widget.presentation.ui.movie.MovieAppWidgetReceiver` | exported=true | intent-filter=True | permission=``
- `com.google.firebase.iid.FirebaseInstanceIdReceiver` | exported=true | intent-filter=True | permission=`com.google.android.c2dm.permission.SEND`
- `androidx.work.impl.diagnostics.DiagnosticsReceiver` | exported=true | intent-filter=True | permission=`android.permission.DUMP`
- `androidx.profileinstaller.ProfileInstallReceiver` | exported=true | intent-filter=True | permission=`android.permission.DUMP`
### Exported providers (0)


## 6. DEX / Code Surface

- DEX files: 20 | Classes: 63,827 | Methods: 287,480 | Fields: 286,224 | Strings: 404,914

| DEX | Size | Classes | Methods | Fields | Strings |
|---|---:|---:|---:|---:|---:|
| `assets/audience_network.dex` | 4,864,036 | 3,156 | 22,955 | 13,105 | 30,638 |
| `classes.dex` | 8,831,004 | 10,703 | 47,228 | 42,944 | 61,218 |
| `classes10.dex` | 78,204 | 92 | 253 | 222 | 920 |
| `classes11.dex` | 163,420 | 177 | 592 | 521 | 1,584 |
| `classes12.dex` | 398,276 | 425 | 1,560 | 1,497 | 3,207 |
| `classes13.dex` | 145,824 | 139 | 532 | 445 | 1,381 |
| `classes14.dex` | 81,748 | 90 | 256 | 306 | 961 |
| `classes15.dex` | 82,152 | 113 | 290 | 369 | 1,005 |
| `classes16.dex` | 15,916 | 20 | 34 | 29 | 261 |
| `classes17.dex` | 208,476 | 243 | 629 | 821 | 1,847 |
| `classes18.dex` | 95,920 | 115 | 250 | 328 | 1,094 |
| `classes19.dex` | 719,548 | 777 | 2,601 | 2,508 | 4,159 |
| `classes2.dex` | 16,516,960 | 7,421 | 38,472 | 58,267 | 56,948 |
| `classes3.dex` | 850,600 | 1,081 | 2,980 | 2,400 | 8,010 |
| `classes4.dex` | 9,418,124 | 11,441 | 47,711 | 34,608 | 52,812 |
| `classes5.dex` | 7,357,484 | 7,983 | 37,294 | 56,147 | 56,424 |
| `classes6.dex` | 7,612,432 | 5,475 | 29,029 | 28,379 | 55,274 |
| `classes7.dex` | 10,803,680 | 12,953 | 48,030 | 38,191 | 59,110 |
| `classes8.dex` | 239,036 | 295 | 912 | 907 | 1,906 |
| `classes9.dex` | 1,218,908 | 1,128 | 5,872 | 4,230 | 6,155 |

## 7. Native Libraries

- `config.armeabi_v7a.apk:lib/armeabi-v7a/libVFaceLib.so` (1,963,668 bytes)
- `config.armeabi_v7a.apk:lib/armeabi-v7a/liba97d.so` (997,572 bytes)
- `config.armeabi_v7a.apk:lib/armeabi-v7a/libandroidx.graphics.path.so` (7,252 bytes)
- `config.armeabi_v7a.apk:lib/armeabi-v7a/libbarhopper_v3.so` (3,244,440 bytes)
- `config.armeabi_v7a.apk:lib/armeabi-v7a/libc++_shared.so` (872,872 bytes)
- `config.armeabi_v7a.apk:lib/armeabi-v7a/libcrashlytics-common.so` (435,732 bytes)
- `config.armeabi_v7a.apk:lib/armeabi-v7a/libcrashlytics-handler.so` (87,908 bytes)
- `config.armeabi_v7a.apk:lib/armeabi-v7a/libcrashlytics-trampoline.so` (6,912 bytes)
- `config.armeabi_v7a.apk:lib/armeabi-v7a/libcrashlytics.so` (94,540 bytes)
- `config.armeabi_v7a.apk:lib/armeabi-v7a/libface_detector_v2_jni.so` (5,388,224 bytes)
- `config.armeabi_v7a.apk:lib/armeabi-v7a/libfgcpkjvqe.so` (6,524,160 bytes)
- `config.armeabi_v7a.apk:lib/armeabi-v7a/libimage_processing_util_jni.so` (20,380 bytes)
- `config.armeabi_v7a.apk:lib/armeabi-v7a/libimagepipeline.so` (5,876 bytes)
- `config.armeabi_v7a.apk:lib/armeabi-v7a/libljsfrcwmw.so` (600,296 bytes)
- `config.armeabi_v7a.apk:lib/armeabi-v7a/liblytcuhe.so` (178,628 bytes)
- `config.armeabi_v7a.apk:lib/armeabi-v7a/libmlkit_google_ocr_pipeline.so` (6,781,940 bytes)
- `config.armeabi_v7a.apk:lib/armeabi-v7a/libmlkitcommonpipeline.so` (6,928,228 bytes)
- `config.armeabi_v7a.apk:lib/armeabi-v7a/libnative-filters.so` (12,588 bytes)
- `config.armeabi_v7a.apk:lib/armeabi-v7a/libnative-imagetranscoder.so` (341,072 bytes)
- `config.armeabi_v7a.apk:lib/armeabi-v7a/libohxbee.so` (155,412 bytes)
- `config.armeabi_v7a.apk:lib/armeabi-v7a/libpicodet_ncnn.so` (2,992,584 bytes)
- `config.armeabi_v7a.apk:lib/armeabi-v7a/librcmefqonnx.so` (178,628 bytes)
- `config.armeabi_v7a.apk:lib/armeabi-v7a/librive-android.so` (3,225,636 bytes)
- `config.armeabi_v7a.apk:lib/armeabi-v7a/libsecurity_lite.so` (154,840 bytes)
- `config.armeabi_v7a.apk:lib/armeabi-v7a/libsqlcipher.so` (3,555,080 bytes)
- `config.armeabi_v7a.apk:lib/armeabi-v7a/libtaibmcf.so` (12,617,752 bytes)
- `config.armeabi_v7a.apk:lib/armeabi-v7a/libtrueidlib.so` (79,904 bytes)

## 8. Network/URL Endpoints (static DEX strings)

Total unique URL-like strings: 446

- `http://...)`
- `http://127.0.0.1:`
- `http://g.co/dev/packagevisibility.`
- `http://goo.gl/8Rd3yj`
- `http://goo.gl/naFqQk`
- `http://hostname/?`
- `http://html.zapps.vn/pay/FAQCenter/suport_bidv.html`
- `http://iopengw-280.sggz00b.dev.alipay.net/`
- `http://ns.adobe.com/xap/1.0/`
- `http://openauth.alipay.com/`
- `http://schemas.android.com/apk/res-auto`
- `http://schemas.android.com/apk/res/android`
- `http://tfsimg.alipay.com/images/partner/T1IVXXXb0bXXXXXXXX`
- `http://trueid.net`
- `http://vnpay.vn`
- `http://www.android.com/`
- `http://www.example.com`
- `http://www.facebook.com/dialog/oauth`
- `http://www.google-analytics.com`
- `http://www.google.com`
- `http://www.w3.org/1999/xlink`
- `http://www.w3.org/2000/svg`
- `http://www.w3.org/TR/SVG11/feature#`
- `http://www.w3.org/ns/ttml#parameter`
- `http://xml.org/sax/features/external-general-entities`
- `http://xml.org/sax/features/external-parameter-entities`
- `http://xml.org/sax/properties/lexical-handler`
- `http://xmlpull.org/v1/doc/features.html#process-docdecl`
- `http://xmlpull.org/v1/doc/features.html#process-namespaces`
- `https://%sadrevenue.%s/api/v2/generic/v6.17.4/android?app_id=`
- `https://%sapp.%s`
- `https://%sars.%s/api/v2/android/validate_subscription?app_id=`
- `https://%sars.%s/api/v2/android/validate_subscription_v2?app_id=`
- `https://%sattr.%s/api/v`
- `https://%sconversions.%s/api/v`
- `https://%sdlsdk.%s/v1.0/android/`
- `https://%sgcdsdk.%s/install_data/v5.0/`
- `https://%simpression.%s`
- `https://%sinapps.%s/api/v`
- `https://%slaunches.%s/api/v`
- `https://%smonitorsdk.%s/api/remote-debug/v2.0?app_id=`
- `https://%smonitorsdk.%s/remote-debug/exception-manager`
- `https://%sonelink.%s/shortlink-sdk/v2`
- `https://%spia.%s/api/v1.0/pia-android-event?app_id=`
- `https://%sregister.%s/api/v`
- `https://%ssdk-services.%s/validate-android-signature`
- `https://%svalidate-and-log.%s/api/v4.0/android/one_time_purchase/validateAndLog?app_id=`
- `https://%svalidate-and-log.%s/api/v4.0/android/subscription/validateAndLog?app_id=`
- `https://%svalidate.%s/api/v`
- `https://%sviap.%s/api/v1/android/validate_purchase?app_id=`
- `https://%sviap.%s/api/v1/android/validate_purchase_v2?app_id=`
- `https://.*.zalopay.vn/.*$`
- `https://.facebook.com`
- `https://a.alipayobjects.com/bridgeapi/1.0/jsready.js`
- `https://abc.com&ZPA_EXTRA_DATA=`
- `https://activityservice.alibaba.com/openapi/v1/version?name=`
- `https://admob-gmats.uc.r.appspot.com/`
- `https://alipay.com/h5container/un_safe.html`
- `https://alipay.kylinBridge`
- `https://alipay.worker.com`
- `https://alipay.worker.com/worker`
- `https://analytics.us.tiktok.com/api/v1/app_sdk/config`
- `https://aomedia.org/emsg/ID3`
- `https://api.fpt.ai/`
- `https://api.fpt.ai/vision/ekyc-be/`
- `https://api.trueid.vn`
- `https://api.zalopay.vn`
- `https://api.zalopay.vn,`
- `https://api.zalopay.vn/`
- `https://api.zalopay.vn/,`
- `https://api.zalopay.vn/v2/user/session/check`
- `https://app-measurement.com/a`
- `https://app-measurement.com/s`
- `https://appx`
- `https://appx-ng/`
- `https://appx-ng/af-appx.min.js`
- `https://appx-ng/af-appx.worker.min.js`
- `https://appx-ng/index.prerender.html`
- `https://appx/`
- `https://appx/af-appx.min.js`
- `https://appx/af-appx.worker.min.js`
- `https://atlas.zalopay.vn`
- `https://bank-profile.zalopay.vn/`
- `https://bank-profile.zalopay.vn/,`
- `https://bim.zalopay.vn/`
- `https://bim.zalopay.vn/,`
- `https://bim.zalopay.vn/oss/`
- `https://bim.zalopay.vn/oss/,`
- `https://bnpl-gateway.zalopay.vn`
- `https://bnpl-gateway.zalopay.vn,`
- `https://campaign.zalopay.com.vn/`
- `https://campaign.zalopay.com.vn/,`
- `https://cdn.marmot-cloud.com/page/g_diagnostic_tool/index.html`
- `https://cdn.marmot-cloud.com/page/miniprogram-about/index.html`
- `https://clients3.google.com/generate_204`
- `https://connectivitycheck.gstatic.com/generate_204`
- `https://cps.zalopay.com.vn/`
- `https://cps.zalopay.com.vn/,`
- `https://cps.zalopay.com.vn/wallet-topup-client/`
- `https://csi.gstatic.com/csi`
- `https://d.alipay.com.*scheme=alipays.*`
- `https://dala2zoom.zalopay.vn`
- `https://dala2zoom.zalopay.vn/liveness/`
- `https://dala2zoom.zalopay.vn/liveness/,`
- `https://demomb.vnpaytest.vn/vnptoken/api/v1/getToken`
- `https://design.vnpay.vn/web/sdk/sdk-taxi/tro-giup-vi-vnpay/page/`
- `https://developer.android.com/google/play/integrity/reference/com/google/android/play/core/integrity/model/IntegrityErrorCode.html#`
- `https://developer.android.com/google/play/integrity/reference/com/google/android/play/core/integrity/model/StandardIntegrityErrorCode.html#`
- `https://developer.android.com/guide/topics/media/issues/cleartext-not-permitted`
- `https://developer.android.com/guide/topics/media/issues/player-accessed-on-wrong-thread`
- `https://developer.android.com/reference/com/google/android/play/core/review/model/ReviewErrorCode.html#`
- `https://developer.android.com/reference/com/google/android/play/core/splitinstall/model/SplitInstallErrorCode.html#`
- `https://developer.apple.com/streaming/emsg-id3`
- `https://developers.facebook.com/docs/android/getting-started`
- `https://developers.facebook.com/docs/android/getting-started#add-app_id`
- `https://developers.facebook.com/docs/android/getting-started#client-token`
- `https://developers.facebook.com/docs/android/troubleshooting/#faq_267321845055988`
- `https://developers.facebook.com/docs/app-events/getting-started-app-events-android#disable-auto-events.`
- `https://developers.google.com/admob/android/test-ads#enable_test_devices`
- `https://developers.google.com/tink/faq/registration_errors`
- ... (326 more; see `analysis/security/url_endpoints.json`)

## 9. Security Observations (static)

- **Attack surface**: Large exported component set; verify permission guards and intent validation on exported activities/services/receivers/providers.
- **Sensitive permissions**: location, camera, mic, contacts, calendar, phone, NFC, billing, overlay, QUERY_ALL_PACKAGES - high-risk surface typical of e-wallet super-app.
- **Obfuscation**: obfuscated component names observed (e.g. `amcsetxek.dmayljy`, `yydamachy.xqcntsk`) indicating R8/ProGuard/hardening.
- **Signing**: v2+v3 signed; v1 absent.
- **Native code**: 20 `.so` libs in ABI split; need binary RE for full coverage.
- **Limitation**: static-only; no JADX decompilation (no JRE in sandbox), no dynamic instrumentation, no runtime unpacking.

## 10. Reproducibility

```bash
python3 -m pip install --break-system-packages androguard==4.1.2
python3 work/do_analysis.py
python3 work/deep_analysis.py
```

## Artifacts
- `analysis/inventories/*.json` - per-archive file inventories
- `analysis/manifests/AndroidManifest.decoded.xml`
- `analysis/security/*.json` - DEX stats, exported components, certs, native libs, URLs
- `analysis/apk_metadata.json`