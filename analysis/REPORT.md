# Báo cáo nghiên cứu & dịch ngược (Reverse Engineering) — Zalopay 11.17.0

**Đối tượng:** `Zalopay-Thanh+toán+&+Tài+chính_11.17.0_APKPure.zip` (98.8 MB)
**Phương pháp:** phân tích tĩnh (static analysis) hoàn toàn bằng công cụ tự viết
(`analysis/tools/axml.py`, `analysis/tools/dex.py`, `analysis/tools/analyze.py`),
không cần Java/apktool — parser AXML và DEX được triển khai từ đặc tả định dạng nhị phân.

> ⚠️ **Lưu ý pháp lý & đạo đức:** Báo cáo này chỉ trình bày kết quả phân tích tĩnh
> cấu trúc gói ứng dụng (metadata, danh sách thành phần, thư viện, tài nguyên) phục vụ
> mục đích nghiên cứu/học thuật. Không chứa mã nguồn, khóa, bí mật hay dữ liệu người dùng.
> Việc phân tích tuân thủ nguyên tắc *responsible disclosure* — các quan sát bảo mật được
> nêu ở mức mô tả, không kèm hướng dẫn khai thác.

---

## 1. Cấu trúc gói XAPK

File ZIP ngoài chứa duy nhất một file XAPK (105.6 MB). XAPK là định dạng bundle của
APKPure, gồm 1 APK gốc + 4 APK cấu hình (split APK) + `manifest.json`:

| Thành phần | Dung lượng (nén) | Vai trò |
|---|---|---|
| `vn.com.vng.zalopay.apk` | 79.4 MB | APK gốc (base) — 6.162 tệp, 136.4 MB giải nén |
| `config.armeabi_v7a.apk` | 24.8 MB | Split ABI 32-bit — chứa toàn bộ 27 thư viện native `.so` |
| `config.xhdpi.apk` | 1.2 MB | Split mật độ màn hình xhdpi — 258 tệp drawable |
| `config.vi.apk` | 86 KB | Split ngôn ngữ tiếng Việt |
| `config.en.apk` | 74 KB | Split ngôn ngữ tiếng Anh |
| `manifest.json` | 2.7 KB | Metadata bundle |
| `icon.png` | 24 KB | Icon ứng dụng |

`manifest.json` xác nhận: package `vn.com.vng.zalopay`, tên **Zalopay**,
`versionCode=1102758`, `versionName=11.17.0`, `minSdk=26` (Android 8.0),
`targetSdk=36` (Android 16), 49 quyền, tổng dung lượng 105.6 MB.

**Chữ ký số:** APK gốc **không có** khối chữ ký APK Signing Block v2/v3
(không tìm thấy magic `APK Sig Block 42`) và không có tệp `.RSA/.DSA` trong
`META-INF` — đặc trưng của gói được đóng gói lại (repack) bởi bên thứ ba
(APKPure), chữ ký gốc đã bị loại bỏ. Đây là rủi ro chuỗi cung ứng cần lưu ý
khi cài đặt từ nguồn ngoài Google Play.

## 2. AndroidManifest.xml (đã giải mã từ AXML nhị phân)

- **Package:** `vn.com.vng.zalopay` · **versionCode:** 1102758 · **versionName:** 11.17.0
- **compileSdk:** 36 · **minSdk:** 26 · **targetSdk:** 36
- **Application class:** `jwbmqnsll.xvndbxlk` — tên đã obfuscate (R8/ProGuard)
- **49 quyền**, gồm nhóm nhạy cảm: `CAMERA`, `RECORD_AUDIO`, `READ_CONTACTS`,
  `ACCESS_FINE_LOCATION`, `READ_PHONE_STATE`, `NFC`, `USE_BIOMETRIC`,
  `POST_NOTIFICATIONS`, `QUERY_ALL_PACKAGES`, `DETECT_SCREEN_CAPTURE`,
  `com.android.vending.BILLING`, `FOREGROUND_SERVICE`…
- **Thành phần:**
  - **202 activities** (+ alias), trong đó **16 activity exported** — gồm
    `SplashScreenActivityV2`, `VerificationByVNeIDActivity` (xác thực VNeID),
    `IdentityCardCaptureActivity` (chụp CCCD), `LivenessVerificationActivity`,
    `EkycActivity` (FPT eKYC), `LivenessCameraXActivity` (TrueID), …
  - **34 services**, **22 receivers**, **10 providers**
- **Deep link schemes:** `zalopay` (10), `zalopay-1`, `zalopay.api.v2`,
  `zalopay-vnpay`, `fbconnect`, `tagmanager.c.vn.com.vng.zalopay`…
  **Hosts:** `go.zalopay.vn`, `onelink.zalopay.vn`, `kmzalopay.onelink.me`
  (AppsFlyer OneLink), `km.zalopay.vn`…
- **Tính năng:** NFC (`android.hardware.nfc`)

### Quan sát bảo mật (manifest)
1. **Test/dev artifacts bị đóng gói vào bản production:** các activity exported
   `androidx.test.core.app.InstrumentationActivityInvoker$BootstrapActivity`,
   `androidx.compose.ui.tooling.PreviewActivity`,
   `com.google.android.gms.tagmanager.TagManagerPreviewActivity` — cho thấy
   quy trình build chưa loại bỏ hoàn toàn dependency test/preview.
2. `QUERY_ALL_PACKAGES` + `DETECT_SCREEN_CAPTURE` + `MANAGE_OVERLAY_PERMISSION`
   là các quyền nhạy cảm cần thiết cho tính năng chống gian lận/chụp màn hình
   của ví điện tử, nhưng làm tăng bề mặt tấn công.

## 3. Phân tích DEX (mã Dalvik)

APK gốc chứa **20 file DEX** (`classes.dex` … `classes19.dex`), tổng 63.4 MB:

| Chỉ số | Giá trị |
|---|---|
| Tổng số class | **60.671** |
| Tổng số method | **351.248** |
| Tổng số field | ~400.000 |
| Tổng số chuỗi (string) | **374.276** |
| DEX lớn nhất | `classes7.dex` — 12.953 class / 60.000 method |
| DEX khởi động | `classes.dex` — 10.703 class / 59.948 method |

### Các package lớn nhất (class count)
| Package | Số class | Nhận định |
|---|---|---|
| `com.google` | 7.710 | Google Play Services, Firebase, ML Kit, Gson… |
| `org.bouncycastle` | 6.112 | Thư viện mật mã BouncyCastle (AES, RSA, TLS…) |
| `com.alibaba` | 4.821 | Alibaba/Ant Group SDK (bảo mật, Griver mini-app) |
| `com.iap` | 1.599 | In-App Purchase (Google Play Billing) |
| `com.visa` | 1.370 | Visa SDK — thanh toán thẻ/NFC |
| `androidx.media3` | 1.198 | Media3 (ExoPlayer) |
| `vn.com` | 1.054 | Mã nguồn của chính Zalopay/VNG |
| `androidx.camera` | 813 | CameraX |
| `vn.ai` | 689 | VNG AI — TrueID/TrueFace (nhận diện khuôn mặt) |
| `com.facebook` | 531 | Facebook SDK / Audience Network |
| `androidx.compose` | 504 | Jetpack Compose UI |
| `com.alipay` | 493 | Alipay SDK (mini-app Griver, thanh toán) |

### Hạ tầng mạng suy ra từ chuỗi URL trong DEX
- CDN/hạ tầng Zalopay: `scdn.zalopay.com.vn` (65), `simg.zalopay.com.vn` (45),
  `api.zalopay.vn`, `social.zalopay.vn`, `sapi.zalopay.vn`, `mnap-director.zalopay.vn`
- Alipay: `render.alipay.com` (24) — render mini-app Griver
- Marketing/attribution: `play.google.com`, `firebase.google.com`, AppsFlyer OneLink

## 4. Thư viện native (27 file `.so`, split armeabi-v7a)

| Thư viện | Dung lượng | Chức năng suy đoán |
|---|---|---|
| `libtaibmcf.so` | 12.0 MB | Alibaba TAIBMCF — SDK quản trị rủi ro/bảo mật thiết bị |
| `libmlkitcommonpipeline.so` | 6.6 MB | ML Kit common pipeline |
| `libmlkit_google_ocr_pipeline.so` | 6.5 MB | ML Kit OCR (đọc CCCD/giấy tờ) |
| `libfgcpkjvqe.so` | 6.2 MB | Tên obfuscated — SDK bảo mật Alibaba/Ant |
| `libface_detector_v2_jni.so` | 5.1 MB | Phát hiện khuôn mặt |
| `libsqlcipher.so` | 3.4 MB | **SQLCipher — cơ sở dữ liệu mã hóa AES-256** |
| `libbarhopper_v3.so` | 3.1 MB | Quét mã vạch/QR (ML Kit Barcode) |
| `librive-android.so` | 3.1 MB | Rive — animation vector (hướng dẫn tap-to-pay) |
| `libpicodet_ncnn.so` | 2.9 MB | NCNN — suy luận object detection trên thiết bị |
| `libVFaceLib.so` | 1.9 MB | Thư viện khuôn mặt VNG |
| `libsecurity_lite.so` | 0.15 MB | SDK bảo mật nhẹ |
| `libtrueidlib.so` | 0.08 MB | TrueID SDK (eKYC) |
| `libcrashlytics*.so` | 0.6 MB | Firebase Crashlytics |
| `libc++_shared.so` | 0.83 MB | C++ runtime dùng chung |

Các tên lib ngẫu nhiên (`libfgcpkjvqe.so`, `liba97d.so`, `libljsfrcwmw.so`,
`liblytcuhe.so`, `librcmefqonnx.so`, `libohxbee.so`) là kỹ thuật obfuscation
đặc trưng của bộ SDK bảo mật Alibaba/Ant Group nhằm gây khó khăn cho phân tích.

## 5. Assets (124 tệp, ~30 MB)

- **Mô hình ML trên thiết bị:** `object_labeler.tflite` (3.6 MB),
  `FastestDet.ncnn.bin` (2.3 MB), `labeler_with_validation.tflite`,
  `localizer_with_validation.tflite`, `fssd_medium_8bit_v5.tflite`,
  `blazeface.tfl`, `contours.tfl`, các model liveness
  (`BCLjoy_200.emd`, `BCLlefteyeclosed_200.emd`, `BCLrighteyeclosed_200.emd`,
  `LMprec_600.emd`) — phục vụ eKYC/chống giả mạo khuôn mặt.
- **Griver mini-app (Alipay):** `assets/Griver/*.amr` (3 gói mini-app),
  `assets/GriverBridge/`, `assets/GriverErrorPage/` — Zalopay tích hợp nền tảng
  mini-app của Ant Group.
- **DEX nhúng:** `assets/audience_network.dex` (4.6 MB) — Facebook Audience
  Network chạy dưới dạng DEX phụ nạp động.
- **Cấu hình:** `assets/config/miniapp_config.json` (0.84 MB) — danh mục mini-app,
  `assets/getbanklist_zpm_success.json` — danh sách ngân hàng liên kết ví,
  `assets/timezone2region_2023c.json`, `assets/public.pem` — khóa công khai.
- **Dữ liệu SDK bảo mật:** `assets/taibmcf.dat`, `assets/fgcpkjvqe.dat`,
  `assets/bshield.dat` (Alibaba BShield).
- **Font & theme:** 8 font trong `assets/fonts/`, `assets/theme/`.

## 6. Tài nguyên & cấu hình XML

- `resources.arsc`: **7.763 chuỗi** — giao diện tiếng Việt (ưu đãi, xác thực,
  nạp tiền…), tên drawable `logo_zalopay*`, `ic_vector_zalopay_ring*`…
- `res/xml/network_security_config.xml`: **`cleartextTrafficPermitted="true"`**
  ở base-config — ứng dụng cho phép lưu lượng HTTP không mã hóa (điểm cần
  lưu ý bảo mật); debug-overrides tin cậy chứng chỉ người dùng.
- `res/xml/apdu_service.xml`: Host APDU Service cho **NFC tap-to-pay** với các
  AID thẻ Visa: `325041592E5359532E4444463031` (2PAY.SYS.DDF01),
  `A000000003101/201/301/202` (Visa credit/debit), `A0000000980840` (EMV).
- `res/xml/tiny_app_apdu_service.xml`: APDU cho mini-app.
- `res/xml/file_paths.xml`: FileProvider — cache `zpimage`, `scan360`, `Picture`…
- `res/xml/griverfilepath.xml`, `appsflyer_data_extraction_rules.xml`,
  `gma_ad_services_config.xml`, 4 file `*_app_widget_info.xml` (widget Android).

## 7. Thư viện & phiên bản (141 file `.version`)

| Thư viện | Phiên bản |
|---|---|
| Android Gradle Plugin (databinding) | 8.12.3 |
| Jetpack Compose BOM | 1.7.2 (Material 1.6.0) |
| CameraX | 1.4.0 |
| Lifecycle | 2.8.7 |
| Navigation Compose | 2.8.3 |
| Room | 2.8.4 |
| Paging 3 | 3.2.1 |
| Dagger | 2.59.2 |
| Kotlin Coroutines | 1.8.1 |
| Biometric | 1.2.0-alpha05 |
| AndroidX WebKit | 1.11.0-alpha02 |
| WorkManager | 2.7.1 |
| Security-Crypto | 1.1.0 |
| Material Components | 1.4.0 |
| Activity | 1.9.3 |

## 8. Kết luận kiến trúc

Zalopay 11.17.0 là ứng dụng fintech quy mô lớn với kiến trúc nhiều lớp:

1. **Lớp giao diện:** Jetpack Compose + Views truyền thống, CameraX, Media3, Rive.
2. **Lớp nghiệp vụ:** mã nguồn `vn.com.*` (~1.000 class) — ví, chuyển tiền, QR,
   mini-app, widget, NFC tap-to-pay (Visa SDK + Host APDU).
3. **Lớp AI/eKYC trên thiết bị:** ML Kit OCR + barcode, NCNN object detection,
   TrueID/TrueFace liveness, FPT eKYC, VNeID — xác thực danh tính đa kênh.
4. **Lớp bảo mật:** SQLCipher (DB mã hóa), BouncyCastle (mật mã), bộ SDK
   Alibaba/Ant (TAIBMCF, BShield, lib tên obfuscated), certificate pinning
   suy đoán qua OkHttp, chống chụp màn hình (`DETECT_SCREEN_CAPTURE`).
5. **Lớp nền tảng mở rộng:** Griver mini-app (Alipay), Facebook Audience Network
   (DEX nhúng), AppsFlyer attribution, Firebase (Messaging/Analytics/Crashlytics),
   Google Play Billing.

**Điểm mạnh:** mã hóa dữ liệu tại chỗ (SQLCipher), xác thực sinh trắc học đa lớp,
obfuscation mạnh (R8 + tên lib ngẫu nhiên), targetSdk mới nhất (36).

**Điểm cần cải thiện:** cho phép cleartext HTTP trong network security config,
test/preview artifacts lọt vào bản production, gói phân phối lại mất chữ ký gốc.

---

*Dữ liệu máy đọc được: `analysis/summary.json` · Manifest đã giải mã:
`analysis/AndroidManifest.decoded.xml` · Công cụ: `analysis/tools/`*
