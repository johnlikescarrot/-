# Báo cáo phân tích tĩnh — XAPK ZaloPay 11.17.0

> Nghiên cứu minh bạch, chỉ dùng phân tích tĩnh. Không giải mã nội dung nhạy cảm,
> không trích xuất bí mật, không can thiệp cơ chế bảo vệ của ứng dụng.

*Sinh lúc:* 2026-11-25T15:00:07+00:00  
*Công cụ:* {'androguard': '4.1.4', 'python': '3.11', 'method': 'static-only'}

## 1. Tóm tắt

- Gói: `vn.com.vng.zalopay` — phiên bản 11.17.0 (`versionCode=1102758`).
- SDK: min 26, target 36, compile 36.
- Tổng 5 APK trong XAPK; 19 DEX; ~60,671 class_def;
 ~351,248 method_id; ~374,276 string_id.
- Quyền: 49 (16 thuộc nhóm dangerous theo phân loại nghiên cứu).
- Thành phần (base): 200 activity,
 34 service, 22 receiver,
 9 provider.
- Thư viện native: 28 tệp, 54.8 MiB.

## 2. Kiểm kê tệp tin

| Tệp | Kích thước (bytes) | SHA-256 |
|---|---:|---|
| `Zalopay-Thanh+toán+&+Tài+chính_11.17.0_APKPure.xapk` | 105,608,128 | `686e9459da646c18b53a67d5…` |
| `vn.com.vng.zalopay.apk` | 79,359,087 | `e289d547b45f6224aed8ce7b…` |
| `icon.png` | 23,841 | `f0b9d7d1a6144ca550e4d326…` |
| `config.armeabi_v7a.apk` | 24,828,408 | `456aa08f4fa2e3f952657519…` |
| `config.en.apk` | 74,137 | `87699fda97e8a9be06583d92…` |
| `config.vi.apk` | 86,425 | `985fc073b810b6286c116400…` |
| `config.xhdpi.apk` | 1,232,563 | `f31350c3caed15099d2c2d32…` |
| `manifest.json` | 2,710 | `1382fb85261a3f58699396b8…` |

## 3. Cấu trúc từng APK

| APK | Bytes | DEX | class_def | method_id | .so | Quyền |
|---|---:|---:|---:|---:|---:|---:|
| `config.armeabi_v7a.apk` | 24,828,408 | 0 | 0 | 0 | 27 | 0 |
| `config.en.apk` | 74,137 | 0 | 0 | 0 | 0 | 0 |
| `config.vi.apk` | 86,425 | 0 | 0 | 0 | 0 | 0 |
| `config.xhdpi.apk` | 1,232,563 | 0 | 0 | 0 | 0 | 0 |
| `vn.com.vng.zalopay.apk` | 79,359,087 | 19 | 60,671 | 351,248 | 1 | 49 |

## 4. Bề mặt quyền (base APK)

Các quyền được liệt kê để làm bằng chứng phân loại, không phải để đánh giá
hành vi runtime (vì phân tích tĩnh không quan sát được yêu cầu lúc chạy).

- `android.permission.ACCESS_ADSERVICES_AD_ID`
- `android.permission.ACCESS_ADSERVICES_ATTRIBUTION`
- `android.permission.ACCESS_ADSERVICES_CUSTOM_AUDIENCE`
- `android.permission.ACCESS_ADSERVICES_TOPICS`
- `android.permission.ACCESS_COARSE_LOCATION` *(dangerous)*
- `android.permission.ACCESS_FINE_LOCATION` *(dangerous)*
- `android.permission.ACCESS_NETWORK_STATE`
- `android.permission.ACCESS_WIFI_STATE`
- `android.permission.CAMERA` *(dangerous)*
- `android.permission.CHANGE_WIFI_STATE`
- `android.permission.DETECT_SCREEN_CAPTURE`
- `android.permission.FOREGROUND_SERVICE`
- `android.permission.INTERNET`
- `android.permission.MODIFY_AUDIO_SETTINGS`
- `android.permission.NFC` *(dangerous)*
- `android.permission.POST_NOTIFICATIONS` *(dangerous)*
- `android.permission.QUERY_ALL_PACKAGES`
- `android.permission.READ_BASIC_PHONE_STATE`
- `android.permission.READ_CALENDAR` *(dangerous)*
- `android.permission.READ_CONTACTS` *(dangerous)*
- `android.permission.READ_EXTERNAL_STORAGE` *(dangerous)*
- `android.permission.READ_MEDIA_AUDIO` *(dangerous)*
- `android.permission.READ_PHONE_STATE` *(dangerous)*
- `android.permission.RECEIVE_BOOT_COMPLETED`
- `android.permission.RECORD_AUDIO` *(dangerous)*
- `android.permission.REORDER_TASKS`
- `android.permission.USE_BIOMETRIC` *(dangerous)*
- `android.permission.USE_FINGERPRINT` *(dangerous)*
- `android.permission.VIBRATE`
- `android.permission.WAKE_LOCK`
- `android.permission.WRITE_CALENDAR` *(dangerous)*
- `android.permission.WRITE_CONTACTS` *(dangerous)*
- `android.permission.WRITE_EXTERNAL_STORAGE` *(dangerous)*
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

## 5. Thành phần và intent

- **activity**: 200 tổng, 14 exported/ẩn định, 11 có intent-filter.
- **service**: 34 tổng, 6 exported/ẩn định, 5 có intent-filter.
- **receiver**: 22 tổng, 8 exported/ẩn định, 17 có intent-filter.
- **provider**: 9 tổng, 0 exported/ẩn định, 0 có intent-filter.

- Scheme deep link phổ biến: `zalopay` (10), `https` (5), `zalopay-1` (3), `zalopay.api.v2` (3), `zalopay-vnpay` (1), `fbconnect` (1), `tagmanager.c.vn.com.vng.zalopay` (1).
- Số host duy nhất trong intent-filter: 20.
- Action phổ biến: `VIEW`, `MAIN`, `APPWIDGET_UPDATE`, `HOST_APDU_SERVICE`, `MESSAGING_EVENT`, `NDEF_DISCOVERED`, `SEND_MONEY`, `AUTHENTICATE_V1`.

Cờ ứng dụng: `allowBackup=false`, `debuggable=None`, `usesCleartextTraffic=None`, `requestLegacyExternalStorage=true`, `largeHeap=true`, `networkSecurityConfig=@7F16000D`.

## 6. Công nghệ và mức độ tách lớp

Số liệu là số lần xuất hiện của marker byte trong DEX — thước định lượng mức độ
hiện diện của framework, **không** phải số lời gọi hay mức độ sử dụng thực tế.

| Marker | Số lần xuất hiện |
|---|---:|
| Google Play services | 9,434 |
| WebView / Chromium | 8,649 |
| AndroidX | 8,335 |
| Kotlin stdlib | 2,060 |
| Facebook SDK | 927 |
| OkHttp | 924 |
| Firebase | 429 |
| Gson | 400 |
| RxJava | 399 |
| WorkManager | 382 |
| Protobuf | 372 |
| Retrofit | 366 |
| Glide | 345 |
| Dagger / Hilt | 202 |
| Kotlin coroutines | 140 |
| Crashlytics | 115 |
| Biometric prompt | 64 |
| Zalo SDK | 12 |
| Coil | 3 |
| Room | 2 |

Tỷ lệ descriptor cực ngắn (gợi ý tên lớp bị rút gọn): 6.11% — chỉ là tín hiệu định tính.

## 7. Ký số

- Signature scheme V1 (META-INF): không ghi nhận.
- Chứng thư: `Common Name: Vu Manh Hieu` — SHA-256 `59dd31fdf36070a0d7f8c8cde843fac3…` — hạn đến 2041-04-15 15:41:14+00:00.

## 8. Phương pháp, giới hạn và đạo đức

1. ZIP → XAPK → APK được liệt kê và băm SHA-256 để bảo chứng chuỗi bằng chứng (chain of evidence).
2. AndroidManifest.xml (binary XML) được đọc bằng Androguard; DEX chỉ đọc header.
3. Marker công nghệ được đếm trực tiếp trên byte DEX; không dịch ngược mã máy sang nguồn.
4. Giới hạn: phân tích tĩnh không quan sát được luồng dữ liệu runtime, mã hóa động,
   hay hành vi phụ thuộc máy chủ; số lớp/method là thước cấu trúc, không phải độ phức tạp
   hay chất lượng mã nguồn.
5. Đạo đức: báo cáo không chứa bí mật, khóa, token, endpoint nhạy cảm hay mã nguồn
   tái tạo được của bên thứ ba; mọi kết luận chỉ dùng cho nghiên cứu giáo dục.

## 9. Tài liệu tham khảo

- Androguard — https://github.com/androguard/androguard
- Android developers — App manifests / App signing: https://developer.android.com/guide/topics/manifest/manifest-intro
- REA (Reverse Engineer Anything) — https://github.com/morluto/rea
