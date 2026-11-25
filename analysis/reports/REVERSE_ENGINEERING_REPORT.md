# Báo cáo Reverse Engineering: Zalopay 11.17.0 (APKPure XAPK)

- **Ngày tạo**: 2026-11-25T14:49:23.279621Z
- **Gói**: `vn.com.vng.zalopay`
- **Tên app**: Zalopay
- **Version**: 11.17.0 (1102758)
- **SDK**: min 26 / target 36
- **Main activity**: `vn.com.vng.zalopay.splashscreen.presentation.SplashScreenActivityV2`
- **Kích thước APK base**: 79,359,087 bytes
- **SHA-256 (base APK)**: `e289d547b45f6224aed8ce7bf72d75ea5545cd50c0e768d1a74a3e8165fe6a03`
- **Số class DEX**: 60671
- **DEX files**: classes.dex, classes10.dex, classes11.dex, classes12.dex, classes13.dex, classes14.dex, classes15.dex, classes16.dex, classes17.dex, classes18.dex, classes19.dex, classes2.dex, classes3.dex, classes4.dex, classes5.dex, classes6.dex, classes7.dex, classes8.dex, classes9.dex
- **Ký v1/v2/v3**: False/True/True

## 1. Phạm vi & phương pháp

Nghiên cứu này thực hiện phân tích tĩnh (static reverse engineering) trên toàn bộ nội dung gói `Zalopay-Thanh+toán+&+Tài+chính_11.17.0_APKPure.zip` (XAPK). Quy trình:

1. **Trích xuất** lớp ngoài ZIP → XAPK; kiểm kê manifest XAPK, split APKs, icon.
2. **Kiểm kê APK**: bảng mục lục ZIP, phân loại phần mở rộng, thư viện native, DEX.
3. **Phân tích metadata Android** bằng Androguard: manifest, permissions, components, signing certs.
4. **Phân tích DEX**: liệt kê class, thống kê package, trích mẫu chuỗi.
5. **Tổng hợp báo cáo**: báo cáo kỹ thuật Markdown + JSON có cấu trúc cho tái sử dụng.

> Ghi chú: Phân tích được thực hiện trong môi trường sandbox cục bộ; không thực thi APK, không gửi mã lên dịch vụ bên ngoài. Kết quả là bằng chứng tĩnh (static evidence), không khẳng định hành vi runtime.

## 2. Cấu trúc gói XAPK

| File | Vai trò | Kích thước (bytes) |
|---|---:|---:|
| `vn.com.vng.zalopay.apk` | base | 79,359,087 |
| `config.armeabi_v7a.apk` | config.armeabi_v7a | 24,828,408 |
| `config.en.apk` | config.en | 74,137 |
| `config.vi.apk` | config.vi | 86,425 |
| `config.xhdpi.apk` | config.xhdpi | 1,232,563 |
| `manifest.json` | XAPK manifest | 2,710 |
| `icon.png` | Icon | 23,841 |

## 3. Quyền (Permissions)

Tổng cộng **49** quyền khai báo trong XAPK manifest.

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

### 3.1 Nhận xét bảo mật (static)

- Quyền nhạy cảm/đặc quyền quan sát được: **14**.
  - `android.permission.ACCESS_COARSE_LOCATION`
  - `android.permission.ACCESS_FINE_LOCATION`
  - `android.permission.CAMERA`
  - `android.permission.DETECT_SCREEN_CAPTURE`
  - `android.permission.QUERY_ALL_PACKAGES`
  - `android.permission.READ_CALENDAR`
  - `android.permission.READ_CONTACTS`
  - `android.permission.READ_EXTERNAL_STORAGE`
  - `android.permission.READ_MEDIA_AUDIO`
  - `android.permission.READ_PHONE_STATE`
  - `android.permission.RECORD_AUDIO`
  - `android.permission.WRITE_CALENDAR`
  - `android.permission.WRITE_CONTACTS`
  - `android.permission.WRITE_EXTERNAL_STORAGE`
- `QUERY_ALL_PACKAGES` và quyền vị trí/contacts/media/camera/mic cho thấy bề mặt tấn công rộng; cần đánh giá runtime justification.

## 4. Thành phần Android (Manifest components)

| Loại | Số lượng |
|---|---:|
| Activities | 200 |
| Services | 34 |
| Receivers | 22 |
| Providers | 9 |

### 4.1 Activities (mẫu 60)
- `amcsetxek.dmayljy`
- `androidx.compose.ui.tooling.PreviewActivity`
- `androidx.glance.appwidget.action.ActionTrampolineActivity`
- `androidx.glance.appwidget.action.InvisibleActionTrampolineActivity`
- `androidx.test.core.app.InstrumentationActivityInvoker$BootstrapActivity`
- `androidx.test.core.app.InstrumentationActivityInvoker$EmptyActivity`
- `androidx.test.core.app.InstrumentationActivityInvoker$EmptyFloatingActivity`
- `com.alibaba.griver.core.keepalive.GriverProxyActivity`
- `com.alibaba.griver.core.ui.activity.GriverBaseActivity$Back1`
- `com.alibaba.griver.core.ui.activity.GriverBaseActivity$Back2`
- `com.alibaba.griver.core.ui.activity.GriverBaseActivity$Back3`
- `com.alibaba.griver.core.ui.activity.GriverBaseActivity$Back4`
- `com.alibaba.griver.core.ui.activity.GriverBaseActivity$Back5`
- `com.alibaba.griver.core.ui.activity.GriverBaseActivity$Back6`
- `com.alibaba.griver.core.ui.activity.GriverBaseActivity$Lite1`
- `com.alibaba.griver.core.ui.activity.GriverBaseActivity$Lite2`
- `com.alibaba.griver.core.ui.activity.GriverBaseActivity$Lite3`
- `com.alibaba.griver.core.ui.activity.GriverBaseActivity$Main`
- `com.alibaba.griver.core.ui.activity.GriverFileChooserActivity`
- `com.alibaba.griver.core.ui.activity.GriverTransActivity$Back1`
- `com.alibaba.griver.core.ui.activity.GriverTransActivity$Back2`
- `com.alibaba.griver.core.ui.activity.GriverTransActivity$Back3`
- `com.alibaba.griver.core.ui.activity.GriverTransActivity$Back4`
- `com.alibaba.griver.core.ui.activity.GriverTransActivity$Back5`
- `com.alibaba.griver.core.ui.activity.GriverTransActivity$Back6`
- `com.alibaba.griver.core.ui.activity.GriverTransActivity$Lite1`
- `com.alibaba.griver.core.ui.activity.GriverTransActivity$Lite2`
- `com.alibaba.griver.core.ui.activity.GriverTransActivity$Lite3`
- `com.alibaba.griver.core.ui.activity.GriverTransActivity$Main`
- `com.alibaba.griver.device.ui.GriverOpenSettingActivity`
- `com.alibaba.griver.file.ui.FileViewerActivity`
- `com.alibaba.griver.image.activity.GriverCaptureActivity`
- `com.alibaba.griver.image.activity.GriverCapturePreviewActivity`
- `com.alibaba.griver.image.activity.GriverPhotoPreviewActivity`
- `com.alibaba.griver.image.activity.GriverPhotoSelectActivity`
- `com.alibaba.griver.image.activity.GriverVideoPreviewActivity`
- `com.alibaba.griver.ui.jsapi.picker.nebula.wheelview.multilevel.MultilevelSelectActivity`
- `com.android.billingclient.api.ProxyBillingActivity`
- `com.android.billingclient.api.ProxyBillingActivityV2`
- `com.facebook.CustomTabActivity`
- `com.facebook.CustomTabMainActivity`
- `com.facebook.FacebookActivity`
- `com.facebook.ads.AudienceNetworkActivity`
- `com.fpt.fci.ekycfull.presentation.view.EkycActivity`
- `com.google.android.gms.ads.AdActivity`
- `com.google.android.gms.ads.NotificationHandlerActivity`
- `com.google.android.gms.ads.OutOfContextTestingActivity`
- `com.google.android.gms.common.api.GoogleApiActivity`
- `com.google.android.gms.tagmanager.TagManagerPreviewActivity`
- `com.google.android.play.core.common.PlayCoreDialogWrapperActivity`
- `com.iap.ac.android.acs.plugin.downgrade.ui.ErrorPageActivity`
- `com.iap.android.mppclient.container.activity.ACContainerActivity`
- `com.iap.android.mppclient.container.activity.H5NetworkCheckActivity`
- `com.karumi.dexter.DexterActivity`
- `com.vnpay.vntaxi.ui.activities.DetailBookTaxiActivity`
- `com.vnpay.vntaxi.ui.activities.TaxiBrowserActivity`
- `com.vnpay.vntaxi.ui.activities.TaxiWebviewLinkActivity`
- `com.vnpay.vntaxi.ui.activities.address.SelectAddressActivity`
- `com.vnpay.vntaxi.ui.activities.address.search.ConfirmOnMapActivity`
- `com.vnpay.vntaxi.ui.activities.address.search.SearchAddressActivity`
- ... (+140 nữa)

### 4.2 Services (mẫu 60)
- `androidx.camera.core.impl.MetadataHolderService`
- `androidx.core.widget.RemoteViewsCompatService`
- `androidx.glance.appwidget.GlanceRemoteViewsService`
- `androidx.room.MultiInstanceInvalidationService`
- `androidx.work.impl.background.systemalarm.SystemAlarmService`
- `androidx.work.impl.background.systemjob.SystemJobService`
- `androidx.work.impl.foreground.SystemForegroundService`
- `com.alibaba.ariver.commonability.device.jsapi.nfc.service.TinyAppHostApduService`
- `com.alibaba.ariver.ipc.RemoteCallService`
- `com.alibaba.exthub.bigdata.service.BigDataLiteService$BigDataLiteService1`
- `com.alibaba.exthub.bigdata.service.BigDataLiteService$BigDataLiteService2`
- `com.alibaba.exthub.bigdata.service.BigDataLiteService$BigDataLiteService3`
- `com.alibaba.exthub.bigdata.service.BigDataLiteService$BigDataLiteService4`
- `com.alibaba.exthub.bigdata.service.BigDataLiteService$BigDataLiteService5`
- `com.alibaba.exthub.bigdata.service.BigDataMainService`
- `com.alibaba.griver.base.resource.predownload.AppPreDownloadJobSchedulerService`
- `com.alibaba.griver.core.kernel.ipc.client.IpcMsgClientService$Lite1`
- `com.alibaba.griver.core.kernel.ipc.client.IpcMsgClientService$Lite2`
- `com.alibaba.griver.core.kernel.ipc.client.IpcMsgClientService$Lite3`
- `com.alibaba.griver.core.kernel.ipc.server.IpcMsgServerService`
- `com.alibaba.griver.device.jsapi.nfc.service.TinyAppHostApduService`
- `com.google.android.datatransport.runtime.backends.TransportBackendDiscovery`
- `com.google.android.datatransport.runtime.scheduling.jobscheduling.JobInfoSchedulerService`
- `com.google.android.gms.ads.AdService`
- `com.google.android.gms.measurement.AppMeasurementJobService`
- `com.google.android.gms.measurement.AppMeasurementService`
- `com.google.android.gms.tagmanager.TagManagerService`
- `com.google.firebase.components.ComponentDiscoveryService`
- `com.google.firebase.messaging.FirebaseMessagingService`
- `com.google.firebase.sessions.SessionLifecycleService`
- `com.google.mlkit.common.internal.MlKitComponentDiscoveryService`
- `com.vnpay.vntaxi.base.gps.FusedLocationService`
- `vn.com.vng.zalopay.miniapp.api.v2.visa.VisaHostApduService`
- `vn.com.vng.zalopay.notification.MyFcmListenerService`

### 4.3 Receivers (mẫu 60)
- `androidx.glance.appwidget.MyPackageReplacedReceiver`
- `androidx.glance.appwidget.UnmanagedSessionReceiver`
- `androidx.glance.appwidget.action.ActionCallbackBroadcastReceiver`
- `androidx.profileinstaller.ProfileInstallReceiver`
- `androidx.work.impl.background.systemalarm.ConstraintProxy$BatteryChargingProxy`
- `androidx.work.impl.background.systemalarm.ConstraintProxy$BatteryNotLowProxy`
- `androidx.work.impl.background.systemalarm.ConstraintProxy$NetworkStateProxy`
- `androidx.work.impl.background.systemalarm.ConstraintProxy$StorageNotLowProxy`
- `androidx.work.impl.background.systemalarm.ConstraintProxyUpdateReceiver`
- `androidx.work.impl.background.systemalarm.RescheduleReceiver`
- `androidx.work.impl.diagnostics.DiagnosticsReceiver`
- `androidx.work.impl.utils.ForceStopRunnable$BroadcastReceiver`
- `com.facebook.AuthenticationTokenManager$CurrentAuthenticationTokenChangedBroadcastReceiver`
- `com.facebook.CurrentAccessTokenExpirationBroadcastReceiver`
- `com.google.android.datatransport.runtime.scheduling.jobscheduling.AlarmManagerSchedulerBroadcastReceiver`
- `com.google.android.gms.measurement.AppMeasurementReceiver`
- `com.google.firebase.iid.FirebaseInstanceIdReceiver`
- `vn.com.vng.zalopay.app_widget.presentation.ui.game.GameAppWidgetReceiver`
- `vn.com.vng.zalopay.app_widget.presentation.ui.movie.MovieAppWidgetReceiver`
- `vn.com.vng.zalopay.app_widget.presentation.ui.personal_qr.PersonalQRAppWidgetReceiver`
- `vn.com.vng.zalopay.app_widget.presentation.ui.standard.StandardAppWidgetReceiver`
- `vn.com.vng.zalopay.receivers.ZPInstallBroadcastReceiver`

### 4.4 Providers (mẫu 60)
- `androidx.core.content.FileProvider`
- `androidx.startup.InitializationProvider`
- `com.alibaba.griver.file.utils.GriverFileProvider`
- `com.facebook.ads.AudienceNetworkContentProvider`
- `com.facebook.internal.FacebookInitProvider`
- `com.google.android.gms.ads.MobileAdsInitProvider`
- `com.google.firebase.perf.provider.FirebasePerfProvider`
- `com.google.firebase.provider.FirebaseInitProvider`
- `com.google.mlkit.common.internal.MlKitInitProvider`

## 5. Chữ ký & chứng chỉ

### Cert #1
- **error**: `'Name' object has no attribute 'rfc4514_string'`

## 6. Thư viện native

| File | Size | Compressed |
|---|---:|---:|

## 7. DEX / Package inventory

- Tổng class: **60671**

### 7.1 Top package (theo prefix 3 cấp)

| Package prefix | Class count |
|---|---:|
| `com.google.android` | 6465 |
| `com.alibaba.ariver` | 2415 |
| `com.alibaba.griver` | 2228 |
| `org.bouncycastle.jcajce` | 1758 |
| `com.iap.ac` | 1330 |
| `org.bouncycastle.pqc` | 975 |
| `vn.com.vng` | 958 |
| `com.visa.cbp` | 900 |
| `org.bouncycastle.crypto` | 836 |
| `org.bouncycastle.asn1` | 748 |
| `vn.ai.trueid` | 622 |
| `androidx.media3.exoplayer` | 538 |
| `q9` | 472 |
| `p9` | 454 |
| `com.vnpay.vntaxi` | 377 |
| `com.appsflyer.internal` | 364 |
| `com.nimbusds.jose` | 360 |
| `com.visa.visapaysdk` | 337 |
| `n9` | 315 |
| `org.bouncycastle.oer` | 315 |
| `m9` | 314 |
| `androidx.media3.extractor` | 309 |
| `androidx.camera.core` | 306 |
| `com.xq.cxzmoiwuegvp` | 299 |
| `com.google.common` | 297 |
| `com.iap.android` | 269 |
| `org.bouncycastle.math` | 264 |
| `androidx.compose.ui` | 261 |
| `Va` | 253 |
| `io.reactivex.internal` | 251 |
| `org.bouncycastle.cms` | 240 |
| `androidx.compose.foundation` | 234 |
| `androidx.camera.camera2` | 234 |
| `com.alipay.plus` | 230 |
| `com.google.firebase` | 221 |
| `com.google.crypto` | 221 |
| `com.bumptech.glide` | 213 |
| `n0` | 212 |
| `com.google.protobuf` | 209 |
| `com.google.gson` | 207 |
| `androidx.media3.common` | 202 |
| `vn.zalopay.ui` | 186 |
| `androidx.appcompat.widget` | 178 |
| `com.airbnb.lottie` | 173 |
| `androidx.glance.appwidget` | 170 |
| `com.facebook.ads` | 166 |
| `androidx.camera.video` | 159 |
| `org.bouncycastle.cert` | 158 |
| `app.rive.runtime` | 157 |
| `org.jmrtd.lds` | 151 |
| `androidx.datastore.preferences` | 140 |
| `q0` | 137 |
| `org.bouncycastle.operator` | 135 |
| `org.bouncycastle.jce` | 131 |
| `androidx.core.view` | 127 |
| `BV` | 124 |
| `F` | 120 |
| `B2` | 117 |
| `androidx.fragment.app` | 115 |
| `uo` | 115 |

### 7.2 Mẫu class names (100)
- `LA/a;`
- `LA/b;`
- `LA0/a;`
- `LA0/b;`
- `LA0/c;`
- `LA0/u;`
- `Lq0/h1;`
- `LA0/d;`
- `LA0/e;`
- `LA0/f;`
- `LA0/g;`
- `LA0/h;`
- `LA0/i;`
- `LA0/j;`
- `LA0/k;`
- `LA0/l;`
- `LA0/m;`
- `LA0/n;`
- `LA0/o$a;`
- `LA0/o;`
- `LA0/p;`
- `LA0/q;`
- `LA0/r;`
- `LA0/s;`
- `LA0/t;`
- `LA1/c;`
- `LA1/i;`
- `LA1/k;`
- `LA2/a;`
- `Lz2/z;`
- `Lz2/z$a;`
- `LA2/b;`
- `LA2/c;`
- `LA2/d;`
- `LA2/e;`
- `LA2/f;`
- `LA2/g;`
- `LA3/a;`
- `LA3/b;`
- `LA4/d;`
- `LA4/a;`
- `LA4/b;`
- `LA4/c;`
- `LA4/e;`
- `LB0/A;`
- `LB0/N;`
- `LB0/B;`
- `LB0/L;`
- `LB0/C;`
- `LB0/D;`
- `LB0/E;`
- `LB0/F;`
- `LB0/G;`
- `LB0/H;`
- `LB0/I;`
- `LB0/J;`
- `LB0/K;`
- `LB0/M;`
- `LB0/O;`
- `LB0/P;`
- `LB0/k;`
- `LB0/f;`
- `LB0/Q;`
- `LB0/S;`
- `LB0/a;`
- `LB0/b;`
- `LB0/c;`
- `LB0/d;`
- `LB0/e;`
- `LB0/g;`
- `LB0/h;`
- `LB0/i;`
- `LB0/j;`
- `LB0/l;`
- `LB0/m;`
- `LB0/n;`
- `LB0/o;`
- `LB0/p;`
- `LB0/q;`
- `LB0/r;`
- `LB0/s;`
- `LB0/t;`
- `LB0/u;`
- `Lq0/C1;`
- `Lq0/C0;`
- `LB0/v;`
- `LB0/w;`
- `LB0/x;`
- `LB0/y;`
- `LB0/z;`
- `LB2/A0;`
- `Lz2/q;`
- `LB2/A;`
- `LB2/B0;`
- `Lz2/t;`
- `LB2/B;`
- `LB2/C0;`
- `LB2/C;`
- `LB2/D0;`
- `LB2/D;`

## 8. Dấu hiệu chuỗi (sample)

Mẫu chuỗi trích từ DEX string pool (tối đa 300, phục vụ nhận diện thư viện/công nghệ):

- `     `
- `     ဉ `
- `      *  "020¨`
- `        20¨`
- `       0H ¢`
- `   0 H ¢`
- `       \` 20¨`
- `       \` 20ø  !0¨À`
- `       a 20¨`
- `       a 20ø  !0¨À`
- `       b 20ø  !0¨À`
- `       c 20ø  !0¨À`
- `       f 20¨`
- `       f 20ø  !0¨À`
- `       f 20ø  !0¨À`
- `       g 20¨`
- `       g 20ø  !0¨À`
- `       g 20ø  !0¨À`
- `       p 20ø  !0¨À`
- `       v 20ø  !0¨À`
- `       w 20ø  !0¨À`
- `       à 20ø  !0¨À`
- `       á 20ø  !0¨À`
- `       æ 20¨`
- `       æ 20ø  !0¨À`
- `       ç 20¨`
- `       ç 20ø  !0¨À`
- `       20B ¨`
- `      & 20¨`
- `      \` 20ø  !0¨À`
- `      a 20ø  !0¨À`
- `      a 20ø  !0¨À`
- `      b 20ø  !0¨À`
- `      f 20¨`
- `      f 20ø  !0¨À`
- `      f 20ø  !0¨À`
- `      g 20¨`
- `      g 20ø  !0¨À`
- `      g 20ø  !0¨À`
- `      p 20ø  !0¨À`
- `      æ 20ø  !0¨À`
- `       20¨`
- `     	 `
- `       * "00200¨`
- `         200¨`
- `    0"  2  *8 8 2  *8 8 H ¢¨`
- `        \` * 20¨`
- `        \` * 20ø  !0¨À`
- `        a * 20ø  !0¨À`
- `        f * 20¨`
- `        f * 20ø  !0¨À`
- `        f * 20ø  !0¨À`
- `        f *20ø  !0¨À`
- `        f *20ø  !0¨À`
- `        g * 20ø  !0¨À`
- `        g *  20ø  !0¨À`
- `        æ * 20ø  !0¨À`
- `       \` *  28 0ø  !0¨À`
- `       f *280ø  !0¨À`
- `       \` * 20ø  !0¨À`
- `       f * 20ø  !0¨À`
- `       f * 28 0ø  !0¨À`
- `       g * 20ø  !0¨À`
- `       g * 28 0ø  !0¨À`
- `      20B¢¨`
- `     \` * *020ø  !0¨À`
- `     \` **020ø  !0¨À`
- `     \` 20:¨`
- `     \` 20:ø  !0¨À`
- `     a 20:¨`
- `     a 20:ø  !0¨À`
- `     f * *020¨`
- `     f * *020ø  !0¨À`
- `     f 20:¨`
- `     f 20:ø  !0¨À`
- `     f 20:ø  !0¨À`
- `     g **020ø  !0¨À`
- `     g 20:ø  !0¨À`
- `     à 20:ø  !0¨À`
- `     æ 20:ø  !0¨À`
- `     ç 20:¨`
- `     ç 20:ø  !0¨À`
- `       20:B¢¨`
- `     \` * *0**020ø  !0¨À`
- `     f * *0**020¨`
- `     g 20:ø  !0¨À`
- `     v 20:¨`
- `     f 20:ø  !0¨À`
- `     g 20:ø  !0¨À`
- `     v 20:ø  !0¨À`
- `     ç 20:ø  !0¨À`
- `     	 20:	B¢¨ `
- `     20 0B¢jjj¨`
- `     20 0B¢jjjjj¨`
- `      20 0:B¢jjjjjjj	j ¨`
- `     20B¢¨`
- `     20B¢¨`
- `     20B¢¨`
- `    \` 20:ø  !0¨À`
- `    f 20:¨`
- `    f 20:ø  !0¨À`
- `    f 20:ø  !0¨À`
- `    g 20:ø  !0¨À`
- `    à 20:ø  !0¨À`
- `      20B¢¨`
- `     20B¢¨`
- `     20B¢¨`
- `    f 20:ø  !0¨À`
- `    w 20:ø  !0¨À`
- `     20B¢¨`
- `      20:B¢¨`
- `     20:B¢¨`
- `    & 20:B	¢¨`
- `    f 20:ø  !0¨À`
- `    g 20:ø  !0¨À`
- `     20:B¢¨`
- `          020H ¢`
- `          020H ¢`
- `          020H ¢`
- `      À 200B	¢¨`

## 9. Hạn chế & hướng tiếp tục

- **Chưa decompile toàn bộ** sang Java/Kotlin nguồn đọc được do giới hạn Java/JADX trong sandbox hiện tại; đã có inventory class/package và metadata đầy đủ.
- **Chưa phân tích động** (runtime hooking, network capture, Frida) vì không thực thi APK.
- **Hướng tiếp tục**: cài JADX/headless, decompile có chọn lọc (payment, auth, crypto, network, anti-tamper), dựng graph call, đối chiếu strings/URLs/endpoints, tạo video Remotion minh họa pipeline + findings.

## 10. Tái lập

```bash
python3 -m pip install --break-system-packages androguard==4.1.2 lxml pyaxmlparser
unzip -q "Zalopay-Thanh+toán+&+Tài+chính_11.17.0_APKPure.zip" -d analysis/extracted/outer
unzip -q analysis/extracted/outer/*.xapk -d analysis/extracted/xapk
python3 tools/analyze_apk.py
```
