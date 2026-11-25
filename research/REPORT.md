# Nghiên cứu chuyên sâu: Phân tích tĩnh ứng dụng Zalopay 11.17.0 (XAPK)

> **Phạm vi đạo đức:** Nghiên cứu này chỉ thực hiện phân tích tĩnh ở mức cấu trúc (manifest, thành phần, thư viện, kiến trúc) phục vụ mục đích học thuật và phòng thủ. Không trích xuất, công bố hay sử dụng khóa API, token, endpoint nội bộ, hay logic thanh toán để vượt qua cơ chế bảo mật. Nội dung nhạy cảm (ví dụ: `assets/config/public_key.txt`, `signature.txt`, `zalopay_config.json`) chỉ ghi nhận sự tồn tại và kích thước, không công bố nội dung.

## 1. Tổng quan mẫu vật

| Thuộc tính | Giá trị |
|---|---|
| Tệp nguồn | `Zalopay-Thanh+toán+&+Tài+chính_11.17.0_APKPure.zip` (95 MB) |
| Định dạng | XAPK v2 (chứa 5 APK split) |
| Package | `vn.com.vng.zalopay` |
| Phiên bản | 11.17.0 (versionCode 1102758) |
| minSdk / targetSdk | 26 / 36 |
| Tổng kích thước | 105.580.620 bytes |
| Base APK | `vn.com.vng.zalopay.apk` — 79.359.087 bytes, 6.162 entries |

Cấu trúc split:

- `base` (79,3 MB): mã nguồn DEX, tài nguyên, manifest đầy đủ.
- `config.armeabi_v7a` (24,8 MB): 28 thư viện native `.so` cho ARMv7.
- `config.en` / `config.vi` (~74–86 KB): tài nguyên ngôn ngữ.
- `config.xhdpi` (1,2 MB): 258 drawable mật độ xhdpi.

## 2. Phương pháp luận

Nghiên cứu áp dụng quy trình phân tích tĩnh chuẩn cho Android, tham chiếu các công cụ mã nguồn mở như [morluto/rea](https://github.com/morluto/rea) (trình phân tích APK), Apktool (giải mã tài nguyên), JADX (decompiler DEX → Java/Kotlin) và `aapt2` (phân tích manifest). Trong môi trường sandbox, quy trình được tái hiện bằng Python (`zipfile`, phân tích binary XML) để đảm bảo tính tái lập mà không cần cài đặt thêm:

1. **Giải nén XAPK** → liệt kê split APK, đọc `manifest.json`.
2. **Phân tích base APK**: đếm entries, phân loại DEX / `lib/` / `assets/` / `res/` / `META-INF/`.
3. **Phân tích `AndroidManifest.xml` (binary XML)**: trích xuất activities, services, receivers, permissions, intent actions.
4. **Fingerprint SDK** qua `META-INF/*.version`, `*.properties`, `kotlin_module`.
5. **Đánh giá obfuscation**: phân tích tên package DEX và tên thư viện native.
6. **Kiểm kê assets**: ghi nhận danh mục (Griver mini-app, ML models, config) mà không công bố khóa.

## 3. Kết quả chi tiết

### 3.1. Mã DEX (19 tệp)

Base APK chứa **19 tệp DEX** (`classes.dex` + `classes2.dex` … `classes19.dex` + `assets/audience_network.dex`), tổng chưa nén ~79 MB. Tệp lớn nhất là `classes2.dex` (16,5 MB), tiếp theo là `classes7.dex` (10,8 MB), `classes4.dex` (9,4 MB). Số lượng DEX lớn cho thấy ứng dụng sử dụng **multidex** và tích hợp nhiều module (SDK thanh toán, mini-app, ML).

Phân tích tiền tố package (chuỗi trong DEX) cho thấy các cụm lớn:

- `com/alibaba/griver`, `com/alibaba/ariver` — nền tảng mini-app Griver của Alibaba.
- `com/visa/cbp`, `com/visa/visapaysdk` — Visa SDK (thanh toán thẻ).
- `com/iap/android`, `com/iap/ac` — In-App Purchase.
- `com/vnpay/vntaxi` — module taxi/VNPay.
- `androidx/compose/ui`, `androidx/camera/core`, `androidx/media3/*` — Jetpack Compose, CameraX, Media3.
- `com/xq/cxzmoiwuegvp` — package bị obfuscate (R8), dấu hiệu obfuscation mạnh.

### 3.2. Thành phần Android (Manifest)

- **212 Activities**: bao phủ các luồng chính — `HomeActivity`, `CashierNewUIActivity`, `ScanCodeActivity`, `WithdrawActivity`, `BalanceActivity`, `EkycActivity`/`MainEKycActivity`/`SelfieActivity`, `LivenessVerificationActivity`, `OTPChallengeActivity`, `LockScreenActivity`, `MiniAppActivity`, `WebViewActivity`, `PdfViewerActivity`, `FundingSolutionNFCActivity`, `Taxi*` (VNPay), `Griver*` (mini-app), `TrueID` (eKYC).
- **37 Services**: `FirebaseMessagingService`, `MyFcmListenerService`, `AppMeasurementService`, `SystemJobService`, `SystemForegroundService`, `BigDataMainService`/`BigDataLiteService*`, `IpcMsgClientService`/`IpcMsgServerService` (Griver IPC), `VisaHostApduService` (NFC), `TinyAppHostApduService`.
- **19 Receivers**: `FirebaseInstanceIdReceiver`, `AppMeasurementReceiver`, `ZPInstallBroadcastReceiver`, widget receivers (`GameAppWidgetReceiver`, `MovieAppWidgetReceiver`, `StandardAppWidgetReceiver`, `PersonalQRAppWidgetReceiver`), `QRReceiverActivity`.
- **14 Intent actions**: `MAIN`, `VIEW`, `SEND`, `DIAL`, `GET_CONTENT`, `INSERT`, `MY_PACKAGE_REPLACED`, `TIME_SET`, `TIMEZONE_CHANGED`, `BATTERY_LOW/OKAY`, `DEVICE_STORAGE_LOW/OKAY`, `ACTION_POWER_CONNECTED`.

### 3.3. Quyền (39 permissions)

Nhóm nhạy cảm:

- **Định danh & thiết bị**: `READ_PHONE_STATE`, `READ_BASIC_PHONE_STATE`, `QUERY_ALL_PACKAGES`, `AD_ID`, `ACCESS_ADSERVICES_*`.
- **Vị trí**: `ACCESS_FINE_LOCATION`, `ACCESS_COARSE_LOCATION`.
- **Camera / Mic**: `CAMERA`, `RECORD_AUDIO` (phục vụ eKYC, quét QR, selfie liveness).
- **Sinh trắc học**: `USE_BIOMETRIC`, `USE_FINGERPRINT`.
- **NFC**: `NFC`, `BIND_NFC_SERVICE`.
- **Lưu trữ & danh bạ**: `READ/WRITE_EXTERNAL_STORAGE`, `READ_MEDIA_AUDIO`, `READ/WRITE_CONTACTS`, `READ/WRITE_CALENDAR`.
- **Hệ thống**: `POST_NOTIFICATIONS`, `FOREGROUND_SERVICE`, `RECEIVE_BOOT_COMPLETED`, `WAKE_LOCK`, `VIBRATE`, `REORDER_TASKS`, `DETECT_SCREEN_CAPTURE`, `DUMP`, `MODIFY_AUDIO_SETTINGS`.
- **Tùy chỉnh**: `com.zing.zalo.permission.ACCESS_THIRD_PARTY_APP_AUTHORIZATION`, `vn.com.vng.zalopay.DYNAMIC_RECEIVER_NOT_EXPORTED_PERMISSION`, `com.google.android.c2dm.permission.RECEIVE/SEND`, `com.bbk.launcher2.permission.*`, `com.android.vending.BILLING`.

Đánh giá: tập quyền rộng nhưng nhất quán với ví điện tử đa năng (thanh toán, eKYC, NFC, mini-app, widget). `QUERY_ALL_PACKAGES` và `DETECT_SCREEN_CAPTURE` là điểm cần lưu ý trong đánh giá quyền riêng tư.

### 3.4. Thư viện native (28 `.so` cho armeabi-v7a)

Split `config.armeabi_v7a` chứa 28 thư viện, gồm:

- **ML / Vision**: `libface_detector_v2_jni.so` (5,3 MB), `libmlkit_google_ocr_pipeline.so` (6,7 MB), `libmlkitcommonpipeline.so` (6,9 MB), `libpicodet_ncnn.so` (2,9 MB), `libbarhopper_v3.so` (3,2 MB, quét barcode).
- **Face / eKYC**: `libVFaceLib.so` (1,9 MB), `libtrueidlib.so`, `liba97d.so`.
- **Bảo mật / DB**: `libsqlcipher.so` (3,5 MB — mã hóa cơ sở dữ liệu), `libsecurity_lite.so`.
- **Obfuscated**: `libfgcpkjvqe.so` (6,5 MB), `libtaibmcf.so` (12,6 MB — lớn nhất), `libljsfrcwmw.so`, `liblytcuhe.so`, `librcmefqonnx.so`, `libohxbee.so` — tên ngẫu nhiên, dấu hiệu obfuscation native mạnh.
- **Khác**: `librive-android.so` (Rive animation), `libcrashlytics*.so`, `libc++_shared.so`, `libimagepipeline.so`, `libnative-imagetranscoder.so`.

### 3.5. Assets (124 tệp)

- **Griver mini-app**: `assets/Griver/`, `assets/GriverBridge/` (`h5_bridge.js`, `workerjs.js`, `cover_component.js`), `assets/GriverTheme/theme.json`, `assets/GriverErrorPage/`.
- **ML models**: `assets/FastestDet.ncnn.bin/.param` (phát hiện đối tượng NCNN), `assets/custom_models/object_labeler.tflite`.
- **Config (chỉ ghi nhận tồn tại)**: `assets/config/zalopay_config.json` (105 KB), `assets/config/miniapp_config.json` (883 KB), `assets/config/public_key.txt` (450 bytes), `assets/config/signature.txt` (350 bytes), `assets/bundle.json`, `assets/client_tracking.json` (146 KB), `assets/containers/GTM-PC9W5BW.json` (Google Tag Manager).
- **SDK assets**: `assets/com/appsflyer/internal/*`, `assets/com/visa/cbp/*`, `assets/android.1.zip`, `assets/bshield.dat`, `assets/client_tracking.json`.
- **Khác**: `assets/trueidlib.so` (stub 392 bytes), `assets/audience_network.dex` (4,8 MB — Facebook Audience Network), `getbanklist_zpm_success.json` (danh sách ngân hàng).

### 3.6. Stack công nghệ (fingerprint)

Qua `META-INF/*.version` và `*.properties`:

- **Ngôn ngữ / UI**: Kotlin (`kotlin-stdlib`, `kotlinx.coroutines`), Jetpack Compose (`androidx.compose.*`), Material, CameraX, Glance (widget), Lottie, Rive.
- **DI / DB / Network**: Dagger (`com.google.dagger.*`), Room (`androidx.room.*`), SQLite, DataStore, OkHttp (`okhttp3`), Retrofit, RxJava/RxKotlin, Protobuf, Nimbus JOSE (`com.nimbusds.jose` — JWT/JWE).
- **Firebase / Google**: Firebase Analytics/Messaging/Perf/Config, Play Services (Ads, Location, Maps, Wallet, ML Kit, SafetyNet), Billing KTX, Review KTX, TagManager.
- **Thanh toán / eKYC**: Visa SDK, IAP, TrueID (`trueid_release.kotlin_module`, `trueface_release.kotlin_module`), FPT eKYC (`com.fpt.fci.ekycfull`), VNPay Taxi, Alibaba Griver/Ariver.
- **Tracking**: AppsFlyer, Facebook SDK, Google Measurement.

### 3.7. Chữ ký & đóng gói

- Base APK không chứa `META-INF/MANIFEST.MF` dạng v1 truyền thống; chữ ký dùng **APK Signature Scheme v2/v3** (xác thực qua `stamp-cert-sha256` 32 bytes).
- Các split config chứa `META-INF/BNDLTOOL.SF/.RSA` (bundletool), xác nhận phân phối qua **Android App Bundle → split APK**.
- `resources.arsc` 1,8 MB, `AndroidManifest.xml` 125 KB — quy mô tài nguyên lớn, phù hợp ứng dụng siêu ứng dụng (super-app).

## 4. Đánh giá bảo mật (phòng thủ)

**Điểm mạnh quan sát được:**

1. **Mã hóa DB**: `libsqlcipher.so` + Room/SQLite → cơ sở dữ liệu mã hóa tại chỗ.
2. **Sinh trắc học & NFC**: `USE_BIOMETRIC`, `USE_FINGERPRINT`, `NFC` + `VisaHostApduService` → xác thực và thanh toán không chạm.
3. **Phát hiện chụp màn hình**: `DETECT_SCREEN_CAPTURE` → bảo vệ thông tin nhạy cảm khi hiển thị.
4. **Obfuscation hai lớp**: R8 cho DEX (`com/xq/...`) và tên `.so` ngẫu nhiên (`libtaibmcf.so`, `libfgcpkjvqe.so`) → tăng chi phí phân tích tĩnh.
5. **eKYC liveness**: TrueID + FPT eKYC + ML Kit face/OCR → chống giả mạo danh tính.
6. **Target SDK 36** (mới nhất) và minSdk 26 → tuân thủ yêu cầu bảo mật hiện đại của Play.

**Rủi ro / lưu ý:**

1. **Bề mặt quyền rộng**: 39 permissions, gồm `QUERY_ALL_PACKAGES`, vị trí chính xác, camera/mic, danh bạ — cần minh bạch và tối thiểu hóa.
2. **WebView + mini-app**: `WebViewActivity`, `InternalWebViewActivity`, Griver bridge (`h5_bridge.js`) → cần kiểm soát JS bridge và URL allowlist.
3. **Config chứa khóa**: `public_key.txt`, `signature.txt`, `zalopay_config.json` trong assets → dù là khóa công khai, vẫn nên đánh giá nguy cơ lộ cấu hình.
4. **SDK tracking**: AppsFlyer, Facebook, Google Measurement → cần tuân thủ chính sách quảng cáo và quyền riêng tư.
5. **DEX lớn**: 19 DEX, 79 MB → bề mặt tấn công lớn, cần cập nhật thư viện thường xuyên (đặc biệt OkHttp, BouncyCastle, Firebase).

## 5. Hạn chế nghiên cứu

- Chỉ phân tích tĩnh cấu trúc; không thực thi, không dynamic analysis, không kiểm thử xâm nhập.
- Không decompile toàn bộ DEX (giới hạn tài nguyên sandbox); kết quả obfuscation dựa trên thống kê tên package.
- Không công bố nội dung config, khóa, endpoint, hay logic nghiệp vụ thanh toán.
- Số liệu có thể thay đổi theo kênh phân phối (APKPure) và thiết bị (split theo ABI/DPI/ngôn ngữ).

## 6. Kết luận

Zalopay 11.17.0 là một **super-app** điển hình: 6.162 entries, 212 activities, 19 DEX, 28 thư viện native, tích hợp sâu Griver mini-app, Visa, TrueID eKYC, ML Kit và Firebase. Kiến trúc Kotlin + Compose + Dagger + Room + SQLCipher cho thấy đầu tư nghiêm túc vào hiệu năng và bảo mật. Obfuscation mạnh (DEX + native) và các cơ chế sinh trắc học, NFC, phát hiện chụp màn hình cho thấy tư duy phòng thủ tốt. Đồng thời, bề mặt quyền rộng, WebView/JS bridge và SDK tracking là các điểm cần tiếp tục giám sát trong nghiên cứu bảo mật ứng dụng tài chính.

## 7. Tài liệu tham khảo

1. Morluto, *rea* — APK analysis toolkit. https://github.com/morluto/rea
2. Android Developers, *APK Signature Scheme v2/v3*, *App Bundle & Split APKs*.
3. OWASP, *Mobile Application Security Verification Standard (MASVS)*.
4. Google, *Android Security Best Practices*, *Play Services / ML Kit documentation*.
5. Alibaba, *Griver Mini-App Platform Documentation*.

---
*Báo cáo được tạo tự động từ phân tích tĩnh XAPK Zalopay 11.17.0. Dữ liệu cấu trúc đầy đủ trong `research/findings.json`.*
