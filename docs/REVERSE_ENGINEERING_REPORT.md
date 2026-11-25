# Phân tích dịch ngược toàn diện ứng dụng ZaloPay 11.17.0 (XAPK) — Báo cáo nghiên cứu chuyên sâu

> **Mã định danh nghiên cứu:** ZP-RE-2026-11-17.0
> **Đối tượng:** `Zalopay-Thanh+toán+&+Tài+chính_11.17.0_APKPure.zip` → `*.xapk` (package `vn.com.vng.zalopay`, version `11.17.0`, versionCode `1102758`)
> **Phương pháp:** Phân tích tĩnh ngoại tuyến (offline static analysis), không thực thi mã, không kết nối mạng tới máy chủ ZaloPay
> **Ngày phân tích:** 2026-11-25
> **Trạng thái:** Bản 1.0 — đồng bộ qua Pull Request

---

## Tóm tắt (Abstract)

Báo cáo này trình bày kết quả phân tích tĩnh toàn diện đối với gói cài đặt ZaloPay 11.17.0 được đóng gói dưới dạng XAPK (APKPure). Nghiên cứu kiểm kê **6.471 mục** trên toàn bộ chuỗi đóng gói (ZIP → XAPK → 5 APK chia tách), trong đó APK cơ sở chứa **6.162 mục**, **20 tệp DEX (66,5 MB)**, **27 thư viện native ARMv7**, **16 mô hình học máy on-device** và **373 mini-app** được cấu hình sẵn. Chúng tôi khôi phục kiến trúc tổng thể (Kotlin + Jetpack Compose + React Native + Griver/Alipay mini-program runtime), bề mặt API (142 URL, 35 endpoint `api_names`, gRPC), hệ sinh thái thanh toán (NFC, Visa Host APDU, Napas/3-D Secure), và các cơ chế bảo mật (SQLCipher, Alipay SecurityGuardLite, RSA/AES, JS Bridge). Mọi phát hiện đều có thể tái lập từ các script trong `scripts/` và bảng kiểm kê `docs/INVENTORY.csv`.

**Từ khóa:** Android reverse engineering, XAPK, ZaloPay, fintech, static analysis, REA, mini-program, on-device ML

**Abstract (EN):** This report presents a comprehensive offline static analysis of ZaloPay 11.17.0 (XAPK, APKPure). We inventory 6,471 entries across the full packaging chain (ZIP → XAPK → 5 split APKs), including 6,162 entries in the base APK, 20 DEX files (66.5 MB), 27 ARMv7 native libraries, 16 on-device ML models, and 373 pre-configured mini-apps. We reconstruct the overall architecture (Kotlin + Jetpack Compose + React Native + Griver/Alipay runtime), API surface (142 URLs, 35 `api_names`, gRPC), payment ecosystem (NFC, Visa Host APDU, Napas/3-D Secure), and security mechanisms (SQLCipher, Alipay SecurityGuardLite, RSA/AES, JS Bridge). All findings are reproducible from `scripts/` and `docs/INVENTORY.csv`.

---

## Mục lục

1. [1. Giới thiệu và câu hỏi nghiên cứu](#1-giới-thiệu-và-câu-hỏi-nghiên-cứu)
2. [2. Phương pháp luận (REA-inspired)](#2-phương-pháp-luận-rea-inspired)
3. [3. Chuỗi đóng gói và kiểm kê toàn diện](#3-chuỗi-đóng-gói-và-kiểm-kê-toàn-diện)
4. [4. Manifest, quyền và thành phần](#4-manifest-quyền-và-thành-phần)
5. [5. Mã DEX và kiến trúc phần mềm](#5-mã-dex-và-kiến-trúc-phần-mềm)
6. [6. Thư viện native (.so)](#6-thư-viện-native-so)
7. [7. Tài nguyên, assets và cấu hình](#7-tài-nguyên-assets-và-cấu-hình)
8. [8. Bề mặt mạng và API](#8-bề-mặt-mạng-và-api)
9. [9. Hệ sinh thái Mini-App và WebView](#9-hệ-sinh-thái-mini-app-và-webview)
10. [10. Học máy on-device (ML Kit, OCR, Face, Barcode)](#10-học-máy-on-device-ml-kit-ocr-face-barcode)
11. [11. Bảo mật, mã hóa và JS Bridge](#11-bảo-mật-mã-hóa-và-js-bridge)
12. [12. React Native bundle (android.1.zip)](#12-react-native-bundle-android1zip)
13. [13. Thảo luận, hạn chế và đạo đức](#13-thảo-luận-hạn-chế-và-đạo-đức)
14. [14. Kết luận và hướng nghiên cứu tiếp theo](#14-kết-luận-và-hướng-nghiên-cứu-tiếp-theo)
15. [15. Tài liệu tham khảo](#15-tài-liệu-tham-khảo)
16. [Phụ lục A — Hash SHA-256](#phụ-lục-a--hash-sha-256)
17. [Phụ lục B — Danh sách quyền đầy đủ](#phụ-lục-b--danh-sách-quyền-đầy-duy)
18. [Phụ lục C — Tái lập phân tích](#phụ-lục-c--tái-lập-phân-tích)

---

## 1. Giới thiệu và câu hỏi nghiên cứu

ZaloPay là ví điện tử và nền tảng thanh toán của VNG tại Việt Nam. Phiên bản 11.17.0 là một ứng dụng Android phức tạp, tích hợp thanh toán QR, chuyển tiền, liên kết ngân hàng, eKYC (định danh điện tử), NFC, mini-app và quảng cáo. Việc phân tích tĩnh gói cài đặt giúp hiểu kiến trúc, bề mặt tấn công và các quyết định kỹ thuật — phục vụ nghiên cứu học thuật, kiểm toán bảo mật và giáo dục.

**RQ1.** Cấu trúc đóng gói XAPK/APK của ZaloPay 11.17.0 gồm những thành phần nào và phân bổ dung lượng ra sao?
**RQ2.** Kiến trúc phần mềm (ngôn ngữ, framework, DEX, native) có thể khôi phục ở mức nào từ phân tích tĩnh?
**RQ3.** Bề mặt mạng/API và hệ sinh thái mini-app có đặc điểm gì?
**RQ4.** Các cơ chế bảo mật, mã hóa và học máy on-device được triển khai như thế nào?
**RQ5.** Những giới hạn phương pháp và vấn đề đạo đức nào cần lưu ý?

> **Phạm vi:** Chỉ phân tích tĩnh. Không giải nén để thực thi, không gọi API thật, không trích xuất bí mật để khai thác, không hướng dẫn vượt qua cơ chế bảo mật. Dữ liệu cá nhân (nếu có trong assets mẫu) được che giấu (redaction).

---

## 2. Phương pháp luận (REA-inspired)

Nghiên cứu áp dụng quy trình lấy cảm hứng từ **[REA — Reverse Engineer Anything with Agents](https://github.com/morluto/rea)** (morluto/rea), một khung tác nhân (agent) để kiểm kê và trích xuất artifact ngoại tuyến, nhưng được rút gọn cho môi trường sandbox không có jadx/apktool/ffmpeg hệ thống:

| Bước REA | Áp dụng trong nghiên cứu này | Công cụ |
|---|---|---|
| Inspect artifact (kiểm kê) | Liệt kê ZIP → XAPK → APK, hash SHA-256, thống kê theo loại | Python `zipfile`, `hashlib` (`scripts/inventory.py`) |
| Extract offline | Giải nén vào `/tmp`, không mount, không thực thi | Python `zipfile` |
| Static strings / metadata | Trích chuỗi UTF-8/UTF-16 từ DEX, Manifest, `.so`, `resources.arsc` | Regex trên bytes (`scripts/dex_strings.py`, `scripts/manifest_strings.py`) |
| Config & asset review | Đọc JSON/JS/HTML trong `assets/` | Python `json`, đọc trực tiếp |
| Native triage | Kiểm tra ELF magic, chuỗi JNI, toolchain clang | Python + regex |
| Evidence & reproducibility | Lưu `INVENTORY.csv`, `hashes.json`, script tái lập | Git + PR |

**Nguyên tắc:** Mọi kết luận đều dẫn tới bằng chứng cụ thể (tên tệp, kích thước, hash, chuỗi). Không suy đoán về hành vi runtime nếu không có bằng chứng tĩnh.

**Công cụ không khả dụng trong sandbox** (ghi nhận minh bạch): `jadx`, `apktool`, `ffmpeg` hệ thống, `file`. Chúng tôi thay thế bằng Python thuần + `imageio-ffmpeg` cho video. Việc decompile đầy đủ (jadx) nằm ngoài phạm vi bản 1.0 và được đề xuất cho nghiên cứu tiếp theo.

---

## 3. Chuỗi đóng gói và kiểm kê toàn diện

### 3.1. Chuỗi đóng gói

```
Zalopay-..._APKPure.zip (98.813.878 bytes, SHA-256 34b9b6b6…)
  └─ Zalopay-..._APKPure.xapk (105.608.128 bytes, SHA-256 686e9459…)
       ├─ vn.com.vng.zalopay.apk        (79.359.087 bytes) — base APK
       ├─ config.armeabi_v7a.apk        (24.828.408 bytes) — native libs ARMv7
       ├─ config.en.apk                 (74.137 bytes)     — English resources
       ├─ config.vi.apk                 (86.425 bytes)     — Vietnamese resources
       ├─ config.xhdpi.apk              (1.232.563 bytes)  — xhdpi drawables
       ├─ manifest.json                 (2.710 bytes)      — XAPK manifest
       └─ icon.png                      (23.841 bytes)     — launcher icon
```

`manifest.json` (XAPK v2) khai báo: `package_name=vn.com.vng.zalopay`, `version_name=11.17.0`, `version_code=1102758`, `min_sdk=26`, `target_sdk=36`, 55 quyền, 4 split config, `total_size=105.580.620`.

### 3.2. Tổng quan kiểm kê

- **Tổng số mục toàn chuỗi:** 6.471 (6.162 base + 32 armeabi + 6 en + 6 vi + 258 xhdpi + 7 XAPK).
- **Base APK uncompressed:** 136.395.610 bytes (~130 MB); compressed ~78,3 MB.
- **Phân bổ base APK:** `res/` ~37,7 MB (35,9 MB thực tế theo uncompressed), `assets/` ~30,0 MB, DEX ~69,7 MB (66,5 MB).
- **Theo đuôi tệp (base):** `.xml` 5.209, `.png` 221, `.version` 141, `.txt` 136, `.webp` 111, `.properties` 79, `.json` 64, `.ttf` 23, `.dex` 20, `.tflite` 14 (+2 `.tfl`), `.kotlin_module` 11, `.proto`/`.binarypb` mỗi 10, `.js` 9, `.html` 9.

Xem đầy đủ tại `docs/INVENTORY.csv` (6.472 dòng gồm header) và `docs/stats.json`.

### 3.3. Hash SHA-256 (provenance)

| Artifact | Kích thước | SHA-256 (rút gọn) |
|---|---|---|
| ZIP gốc | 98.813.878 | `34b9b6b6735f0f73…` |
| XAPK | 105.608.128 | `686e9459da646c18…` |
| base APK | 79.359.087 | `e289d547b45f6224…` |
| config.armeabi_v7a | 24.828.408 | `456aa08f4fa2e3f9…` |
| config.en | 74.137 | `87699fda97e8a9be…` |
| config.vi | 86.425 | `985fc073b810b628…` |
| config.xhdpi | 1.232.563 | `f31350c3caed1509…` |

Hash đầy đủ trong `docs/hashes.json`.

### 3.4. Các APK chia tách (splits)

- **config.armeabi_v7a.apk (32 mục):** Chỉ chứa `AndroidManifest.xml` (1.036 bytes), 27 tệp `lib/armeabi-v7a/*.so`, `stamp-cert-sha256`, và `META-INF/BNDLTOOL.*`. Không chứa DEX hay resources — đúng chuẩn Android App Bundle split theo ABI.
- **config.en.apk / config.vi.apk (mỗi 6 mục):** Chỉ `AndroidManifest.xml`, `resources.arsc` (61.984 / 75.460 bytes), stamp và BNDLTOOL. Đây là split ngôn ngữ.
- **config.xhdpi.apk (258 mục):** `AndroidManifest.xml`, `resources.arsc` (141.056 bytes), ~250 drawable XML/PNG/WebP cho mật độ xhdpi (bao gồm icon thanh toán, NFC, KYC, Griver, ExoPlayer), stamp và BNDLTOOL.

---

## 4. Manifest, quyền và thành phần

### 4.1. AndroidManifest.xml (base, 125.916 bytes, binary XML)

Manifest là binary XML (magic `03 00 08 00`), chuỗi lưu UTF-16LE. Trích xuất 618 chuỗi cho thấy:

- **SDK:** `minSdkVersion`, `targetSdkVersion`, `compileSdkVersion`, `platformBuildVersionCode/Name`, `versionCode=1102758`, `versionName=11.17.0`, `8.3.0` (có thể là version của một SDK nhúng).
- **Split:** `splitTypes`, `requiredSplitTypes`, `base__abi`, `base__density`, `bank_binding,base,cash_in,cashier,cashier_v3,changepin,liveness,login_v2,resetpin,result_page_v3,resultpage,updatephone,withdraw` — cho thấy các dynamic feature / module hóa theo tính năng.
- **Process:** `:lite1` … `:lite5`, `:taibmcf`, `:xcmzqsawpqoefcds` — các tiến trình phụ cho Griver IPC và dịch vụ native.
- **Deep link / scheme:** `zalopay`, `zalopay-1`, `zalopay-vnpay`, `zalopay.api.v2`, `go.zalopay.vn`, `km.zalopay.vn`, `kmzalopay.onelink.me`, `onelink.zalopay.vn`, `zalopay.vn`, `http/https`, `tel:`, `vnd.android.cursor.*`.
- **NFC:** `android.nfc.action.NDEF_DISCOVERED`, `HOST_APDU_SERVICE`, `VisaHostApduService`, `TinyAppHostApduService`.
- **Google API key (công khai trong manifest):** `AIzaSyBBynYIi93D36Jbq9LsfLou_enqA3dbRFc` — đây là khóa Android restricted thông thường nhúng trong client; báo cáo chỉ ghi nhận sự tồn tại, không khuyến khích lạm dụng.
- **Firebase:** `cct.vn.com.vng.zalopay`, `backend:com.google.android.datatransport.cct.CctBackendFactory`.

### 4.2. Quyền (55 quyền từ XAPK manifest)

Phân loại theo rủi ro:

- **Mạng & thiết bị:** `INTERNET`, `ACCESS_NETWORK_STATE`, `ACCESS_WIFI_STATE`, `CHANGE_WIFI_STATE`, `READ_PHONE_STATE`, `READ_BASIC_PHONE_STATE`, `QUERY_ALL_PACKAGES`.
- **Định vị & cảm biến:** `ACCESS_COARSE_LOCATION`, `ACCESS_FINE_LOCATION`, `CAMERA`, `RECORD_AUDIO`, `MODIFY_AUDIO_SETTINGS`, `VIBRATE`, `NFC`.
- **Danh bạ, lịch, lưu trữ:** `READ_CONTACTS`, `WRITE_CONTACTS`, `READ_CALENDAR`, `WRITE_CALENDAR`, `READ_EXTERNAL_STORAGE`, `WRITE_EXTERNAL_STORAGE`, `READ_MEDIA_AUDIO`.
- **Sinh trắc & bảo mật:** `USE_BIOMETRIC`, `USE_FINGERPRINT`, `DETECT_SCREEN_CAPTURE`, `REORDER_TASKS`, `WAKE_LOCK`, `FOREGROUND_SERVICE`, `RECEIVE_BOOT_COMPLETED`, `POST_NOTIFICATIONS`.
- **Quảng cáo & thanh toán:** `AD_ID`, `ACCESS_ADSERVICES_*` (4 quyền), `BILLING`, `BIND_GET_INSTALL_REFERRER_SERVICE`.
- **OEM / launcher:** Samsung, Huawei, BBK launcher permissions, `INSTALL_SHORTCUT`, `WRITE_SETTINGS`, v.v.
- **Zalo nội bộ:** `com.zing.zalo.permission.ACCESS_THIRD_PARTY_APP_AUTHORIZATION`, `vn.com.vng.zalopay.DYNAMIC_RECEIVER_NOT_EXPORTED_PERMISSION`.

**Nhận xét:** Tập quyền rộng (đặc biệt `QUERY_ALL_PACKAGES`, vị trí chính xác, camera, mic, danh bạ) là điển hình của ví điện tử đa năng, nhưng cũng làm tăng bề mặt rủi ro quyền riêng tư — cần chính sách minh bạch và kiểm soát runtime permission.

### 4.3. Thành phần (Activities/Services/Receivers/Providers)

Trích xuất cho thấy **hàng trăm Activity**, bao gồm:

- **Luồng chính:** `SplashScreenActivityV2`, `HomeActivity` (homev5), `ScanQRActivityV2`, `ScanCodeActivity`, `QuickPayNativeActivity`, `ReceiveMoneyActivity`, `MyQRActivity`.
- **Cashier/Thanh toán:** `CashierNewUIActivity`, `MainCashierV2Activity`, `CashierV3Activity`, `CashierWebActivity`, `PayByWalletReadyToPayActivity`, `TPGPaymentActivity` (TenPay).
- **Ngân hàng/Ví:** `BankBindingActivity`, `CashInActivity`, `WithdrawActivity`, `BalanceActivity`, `ClearingAccountActivity`, `FundManagementActivity`.
- **Xác thực/eKYC:** `LoginActivity`, `OtpVerificationActivity`, `OTPChallengeActivity`, `SmartOTPActivity`, `FaceAuthActivity`, `LivenessVerificationActivity`, `FaceLivenessCameraXActivity`, `IdentityCardCaptureActivity`, `MainEKycActivity`, `VerificationByVNeIDActivity`, `ReformKycActivity`, `TrueIDActivity`, `SotpConfirmActivity`.
- **Mini-app/Web:** `MiniAppActivity`, `WebAppExternalActivity`, `InternalWebViewActivity`, `WebViewActivity`, `NoWebViewSdkActivity`.
- **Griver (Alipay):** `GriverBaseActivity`, `GriverTransActivity`, `GriverFileChooserActivity`, `GriverProxyActivity`, `IpcMsgClientService$Lite1..3`, `IpcMsgServerService`, `TinyAppHostApduService`, `BigDataLiteService1..5`.
- **Khác:** Widget (`GameAppWidgetReceiver`, `MovieAppWidgetReceiver`, `PersonalQRAppWidgetReceiver`, `StandardAppWidgetReceiver`), `MyFcmListenerService`, `ZPInstallBroadcastReceiver`, CameraX, WorkManager, Glance, Room, v.v.

Danh sách đầy đủ có thể tái tạo bằng `scripts/manifest_strings.py`.

---

## 5. Mã DEX và kiến trúc phần mềm

### 5.1. DEX

- **20 tệp DEX** trong base APK (19 `classes*.dex` + `assets/audience_network.dex`):
  - `classes.dex` 8.831.004, `classes2.dex` 16.516.960 (lớn nhất), `classes3.dex` 850.600, `classes4.dex` 9.418.124, `classes5.dex` 7.357.484, `classes6.dex` 7.612.432, `classes7.dex` 10.803.680, `classes8.dex` 239.036, `classes9.dex` 1.218.908, `classes10.dex` 78.204 … `classes19.dex` 719.548.
  - Tổng DEX ~69,7 MB — cho thấy ứng dụng rất lớn, đa module, có thể dùng multidex + dynamic feature.
- **Ngôn ngữ/framework (suy từ chuỗi và META-INF):** Kotlin (`.kotlin_module`, `kotlinx.coroutines 1.8.1`), Jetpack Compose `1.7.2`, AndroidX (Activity `1.9.3`, Core `1.16.0`, CameraX `1.4.0`, Room `2.8.4`, Work `2.7.1`, Biometric `1.2.0-alpha05`, v.v.), Dagger `2.59.2`, Navigation Compose `2.8.3`, Paging `3.2.1`, Material `1.4.0`.
- **Package nội bộ:** `vn.com.vng.zalopay.*` (bankbinding, cashier, cashier_v3, cashin, changepin, login_v2, resetpin, updatephone, withdraw, clearing, webapp, miniapp, receive_money, liveness, v.v.), `vn.com.zalopay.auth.*`, `vn.com.vn.zalopay.*`, `com.zing.zalo.*`, `vn.ai.trueid.*` (TrueID eKYC), `com.alibaba.griver.*` / `com.alibaba.ariver.*` (Alipay mini-program).
- **Số lượng chuỗi:** `classes.dex` ~112.058 chuỗi in được; toàn bộ DEX chứa 142 URL duy nhất (xem §8).

### 5.2. Native loading (`System.loadLibrary`)

- `classes.dex`: `fgcpkjvqe`, `ljsfrcwmw`, `rive-android`
- `classes3.dex`: `ohxbee`, `taibmcf`
- `classes4.dex`: `barhopper_v3`, `face_detector_v2_jni`, `mlkit_google_ocr_pipeline`, `rive-android`
- `classes5.dex`: `picodet_ncnn`, `security_lite`
- `classes7.dex`: `sqlcipher`, `trueidlib`

Các tên như `taibmcf`, `fgcpkjvqe`, `a97d`, `ljsfrcwmw`, `lytcuhe`, `ohxbee`, `rcmefqonnx` là **tên đã obfuscate/đổi tên** (không phải tên thư viện chuẩn) — một kỹ thuật làm khó phân tích tĩnh. Nội dung của chúng được phân tích ở §6.

---

## 6. Thư viện native (.so)

27 tệp ELF 32-bit ARM (`armeabi-v7a`), tất cả magic `7F 45 4C 46`:

| Thư viện | Kích thước | Vai trò (suy từ chuỗi/JNI) |
|---|---|---|
| `libtaibmcf.so` | 12.617.752 | BoringSSL (FIPS) + Protobuf; toolchain clang 17.0.2; JNI_OnLoad; tên obfuscated |
| `libfgcpkjvqe.so` | 6.524.160 | BoringSSL + Protobuf; clang 21.0.0; JNI_OnLoad; tên obfuscated |
| `libmlkitcommonpipeline.so` | 6.928.228 | ML Kit common pipeline |
| `libmlkit_google_ocr_pipeline.so` | 6.781.940 | ML Kit OCR (MediaPipe, TFLite); 35 JNI exports `Java_com_google_android_libraries_vision_*` |
| `libface_detector_v2_jni.so` | 5.388.224 | Face detection |
| `libsqlcipher.so` | 3.555.080 | SQLCipher (SQLite mã hóa, AES); 77 JNI-related strings |
| `libbarhopper_v3.so` | 3.244.440 | Barcode (ML Kit Barhopper) |
| `librive-android.so` | 3.225.636 | Rive animation runtime |
| `libpicodet_ncnn.so` | 2.992.584 | NCNN object detection (PicoDet) |
| `libVFaceLib.so` | 1.963.668 | OpenCV face (clang 18); JNI `Java_vn_ai_faceauth_sdk_opencv_VFaceLib_*` |
| `liba97d.so` | 997.572 | Tên obfuscated; cần phân tích sâu hơn |
| `libc++_shared.so` | 872.872 | C++ STL |
| `libljsfrcwmw.so` | 600.296 | Tên obfuscated |
| `libcrashlytics*.so` (4) | ~618 KB | Firebase Crashlytics |
| `libnative-imagetranscoder.so` | 341.072 | Fresco image transcoder |
| `liblytcuhe.so` / `librcmefqonnx.so` | mỗi 178.628 | Tên obfuscated (cùng kích thước — có thể là bản sao/biến thể) |
| `libohxbee.so` | 155.412 | Tên obfuscated |
| `libsecurity_lite.so` | 154.840 | **Alipay SecurityGuardLite** (xem §11) |
| `libtrueidlib.so` | 79.904 | TrueID SDK |
| `libandroidx.graphics.path.so` | 7.252 | AndroidX graphics path |
| `libimage_processing_util_jni.so` | 20.380 | ML Kit image util |
| `libnative-filters.so` | 12.588 | Fresco filters |
| `libimagepipeline.so` | 5.876 | Fresco pipeline |

**Điểm nhấn:**
- Hai thư viện lớn nhất (`libtaibmcf`, `libfgcpkjvqe`) đều chứa BoringSSL + Protobuf và chỉ export `JNI_OnLoad` — đặc trưng của thư viện bảo mật/mã hóa được obfuscate, load động và giải mã logic bên trong.
- `libsecurity_lite.so` chứa chuỗi `Alipay clang version 7.0.1 (based on LLVM 7.0.1.Alipay.Obfuscator)` và `__obfuscator_version__` — bằng chứng rõ ràng về obfuscator của Alipay.
- Không có thư viện `arm64-v8a` trong gói này — chỉ ARMv7. Người dùng ARM64 sẽ cần split khác (APKPure có thể cung cấp bản ARM64 riêng).

---

## 7. Tài nguyên, assets và cấu hình

### 7.1. `res/` (5.604 mục)

- 5.209 tệp `.xml` (layout, drawable, values, v.v.), 221 `.png`, 111 `.webp`, 23 `.ttf` (font), 14 `.riv` (Rive), 10 `.proto`, v.v.
- `resources.arsc` 1.802.300 bytes — bảng tài nguyên biên dịch.
- Font nổi bật: `sf_compact_rounded_*`, `sf_pro_*`, `notosans_regular`, `roboto_*`, `ds_roboto_*` — cho thấy thiết kế theo ngôn ngữ Apple (SF) trên Android, kết hợp Roboto/Noto.
- Raw assets: `lbfmodel.yaml` (3,7 MB), `haarcascade_frontalface_default.xml` (OpenCV Haar cascade), `img_guide_*_id_card.json` (hướng dẫn chụp CCCD), `required_kyc.svg`, `phone_pending.svg`, Lottie JSON (`anim_location_profile_tab.json`, `taxi_splash_loading.json`, v.v.).

### 7.2. `assets/` (124 mục, ~30 MB)

- **Cấu hình:** `config/zalopay_config.json` (105.659 bytes, 55+ nhóm cấu hình), `config/miniapp_config.json` (883.684 bytes, 373 mini-app), `config/public_key.txt` (RSA 2048 public key), `config/signature.txt` (chữ ký Base64), `bundle.json`, `home_data.json`, `client_tracking.json` (dữ liệu mẫu chuyến đi — **chứa PII, đã redaction trong báo cáo**), `iapconnect_config_full`, `dexopt/baseline.prof`.
- **JS Bridge:** `jsbridge.js`, `android_jsbridge.js`, `internaljsbridge.js`, `GriverBridge/*` (xem §11).
- **ML:** `custom_models/object_labeler.tflite`, `mlkit_*`, `models_bundled/*` (xem §10), `FastestDet.ncnn.*`, `fgcpkjvqe.dat` (776.320 bytes, entropy cao — có thể là dữ liệu mã hóa cho `libfgcpkjvqe`), `bshield.dat` (929 bytes, entropy cao — BShield của Alipay), `android.1.zip` (React Native bundle, xem §12), `audience_network.dex` (Facebook Audience Network SDK), `com/visa/cbp/*`, `com/appsflyer/internal/*`.
- **Griver:** `Griver/*.amr` (âm thanh), `GriverErrorPage/*.html`, `GriverTheme/theme.json`, `containererrorpage/*`, `containers/GTM-PC9W5BW.json` (Google Tag Manager container).
- **Font:** `zalopay.ttf`, `zalopay.json` (icon font), `Roboto-*`, `UnisectVnu.ttf`, `ocr-a-tribute.ttf`, `true_font.ttf`.

### 7.3. `zalopay_config.json` — 55+ nhóm

Các nhóm chính: `general` (phone regex Việt Nam, timeout, CSKH 1900545436, PCI DSS seal), `zpc` (Zalo contact sync), `api` (35 `api_names`, gRPC), `webapp` (allow_urls), `bank` (43 ngân hàng, giới hạn thẻ), `kyc` (pattern CMND/CCCD/hộ chiếu, giới hạn giao dịch), `otp` (auto-fill OTP), `ibft` (chuyển tiền liên ngân hàng), `grpc` (ping/reconnect), `banks_information` (43 ngân hàng), `ssl_consideration` (3-D Secure allowlist: SHB, OCB, Agribank, Napas, Citibank, Woori, Banknet, SCB, v.v.), `deeplink`, `lockscreen`, `verify_device`, `merchant_token`, `remote_config`, `theming`, v.v.

### 7.4. `META-INF/` (180 mục)

- 141 tệp `.version` — danh mục SDK chính xác (xem §5.1). Đây là nguồn bằng chứng tin cậy nhất về phiên bản thư viện.
- `CHANGES`, `NOTICE.md`, `README.md`, license của AndroidX, v.v.

---

## 8. Bề mặt mạng và API

### 8.1. URL (142 URL duy nhất từ DEX)

Nhóm chính:

- **API lõi:** `https://api.zalopay.vn`, `https://openapi.zalopay.vn`, `https://token.zalopay.vn`, `https://sapi.zalopay.vn`, `https://rar-um.zalopay.vn`, `https://mmf-user.zalopay.vn`, `https://scard.zalopay.vn`, `https://bnpl-gateway.zalopay.vn`, `https://loyalty.zalopay.vn`, `https://qrpay.zalopay.vn`, `https://mp.zalopay.vn`, `https://rdx.zalopay.vn`, `https://atlas.zalopay.vn`, `https://bim.zalopay.vn`, `https://dala2zoom.zalopay.vn`, `https://miniprogram.zalopay.vn`, `https://social.zalopay.vn`, `https://tracking.zalopay.vn`, `https://sse.zalopay.vn`, `https://zpm.zalopay.com.vn`, `https://log.zalopay.com.vn`, `https://upload.zalopay.com.vn`, `https://upload-img.zalopay.vn`, `https://uudai.zalopay.vn`, `https://cps.zalopay.com.vn`, `https://onelink.zalopay.vn`, `https://zalopay.vn`, `https://zalopay.com.vn`.
- **Zalo:** `https://graph.zaloapp.com`, `https://oauth.zaloapp.com`, `https://ztevents.zaloapp.com`.
- **Alipay/Griver:** `https://appx/`, `https://appx-ng/`, `https://render.alipay.com`, `https://opendocs.alipay.com`, `https://yuque.antfin-inc.com`, `https://alipay.kylinBridge`.
- **Khác:** Sentry (`o529616.ingest.sentry.io`), CDN (`cdn.zalopay.com.vn`, `qccdn`, `qccps`), sandbox (`sandbox.zalopay.com.vn`).

### 8.2. API names (35 endpoint trong `zalopay_config.json`)

- **User Management (um/):** `createaccesstoken`, `removeaccesstoken`, `verifyaccesstoken`, `getuserinfo`, `updateprofile`, `verifyotpprofile`, `recoverypin`, `getuserinfobyzalopayid`, `sendnotification`, `checkzalopaynameexist`, `updatezalopayname`, `getuserinfobyzalopayname`, `listcardinfo`, `listcardinfoforclient`, `listbankaccountforclient`, `getuserprofilelevel`, `preupdateprofilelevel3` (upload), `getmerchantuserinfo`, `getlistmerchantuserinfo`.
- **Transaction (v001/tpe/):** `transhistory`, `gettransstatus`, `getorderinfo`, `createorder`, `createwalletorder`, `getappinfo`, `getappinfov1`, `submittrans`, `atmauthenpayer`, `authcardholderformapping`, `getstatusmapcard`, `verifycardformapping`, `getstatusbyapptransidforclient`, `removemapcard`, `submitmapaccount`.
- **CDN/App:** `cpscdn/app/getplatforminfo`, `cpscdn/app/getbanklist`.
- **Connector:** `api_route=https`, `connector_mode=grpc` — cho thấy chuyển dịch sang gRPC cho một số kênh.

### 8.3. SDK mạng & serialization

Đếm chuỗi trong DEX: `okhttp` 1.403, `retrofit` 403, `protobuf` 1.055, `grpc` 22, `bouncycastle` 10.435 (bao gồm cả BoringSSL strings), `firebase` 1.075, `facebook` 1.251, `appsflyer` 812, `exoplayer` 800, `glide` 407, `coil` 17, `sentry` 9. Đây là hệ sinh thái mạng đầy đủ: OkHttp + Retrofit + Protobuf/gRPC + Firebase + Facebook/AppsFlyer analytics/ads.

---

## 9. Hệ sinh thái Mini-App và WebView

### 9.1. Mini-app config (373 mục)

`assets/config/miniapp_config.json` chứa 373 mini-app với các trường: `app_id`, `app_name`, `app_logo`, `path`, `category_name`, `base_url`, `one_link`, `deep_link` (`zalopay://launch/app/{id}`), `is_web_v1`, `is_active`, `nav_bar`, v.v.

Phân bố theo danh mục (tiêu biểu):

- Dịch vụ phổ biến: 88
- Giải trí - Cộng đồng: 77
- Tài chính: 65
- Ăn uống: 32
- Dịch vụ đối tác: 18
- Giao thông - Vận chuyển: 14
- Thanh toán hoá đơn: 12
- Du lịch - Đi lại: 9
- Điện thoại: 8, Khám phá Zalopay: 8, Tích điểm: 7, Chuyển - Nhận tiền: 6, Ví của tôi: 5, Bảo hiểm: 4, v.v.

`base_url` chủ yếu là `https://social.zalopay.vn` (mini-app v2) và `https://mc.zalopay.vn/app` (merchant). Mỗi mini-app có `deep_link` riêng và cấu hình `nav_bar` (FULLSCREEN/NORMAL, màu sắc, status bar).

### 9.2. Home shortcuts

`assets/home_data.json` định nghĩa màn hình chính: shortcut bar (Chuyển tiền `zalopay://launch/f/transfer`, Ngân hàng `.../linkcard`, Nạp/Rút `.../wallet`, Ưu đãi `.../app/1653`), highlight apps (Nạp tiền ĐT, Thanh toán hoá đơn, Mã thanh toán, Tài khoản tích lũy, Nạp 3G/4G, v.v.).

### 9.3. WebView & Griver

- `WebAppExternalActivity`, `InternalWebViewActivity`, `WebViewActivity`, `MiniAppActivity`, `NoWebViewSdkActivity`.
- Griver runtime (Alipay) cung cấp `GriverBaseActivity`, IPC service, `TinyAppHostApduService` (NFC cho mini-app), `BigDataLiteService`.
- `webapp.allow_urls` trong config kiểm soát URL được phép mở trong WebView nội bộ.

---

## 10. Học máy on-device (ML Kit, OCR, Face, Barcode)

### 10.1. Mô hình TFLite (16 tệp, ~11,7 MB)

| Mô hình | Kích thước | Mục đích |
|---|---|---|
| `custom_models/object_labeler.tflite` | 3.770.391 | Object labeling tùy chỉnh |
| `mlkit_odt_default_classifier/labeler_with_validation.tflite` | 1.706.957 | Object detection classifier |
| `mlkit_odt_localizer/localizer_with_validation.tflite` | 1.254.848 | Object localizer |
| `models_bundled/contours.tfl` | 1.124.144 | Contour detection |
| `models_bundled/fssd_medium_8bit_v5.tflite` (+ gray) | mỗi ~613 KB | FSSD face/object detection 8-bit |
| `mlkit_barcode_models/barcode_ssd_mobilenet_v1_dmp25_quant.tflite` | 390.456 | Barcode SSD MobileNet |
| `models_bundled/blazeface.tfl` | 347.928 | BlazeFace |
| `mlkit-google-ocr-models/taser/detector/rpn_text_detector_*.tflite` | 340.760 | Text detection (RPN) |
| `mlkit-google-ocr-models/gocr/.../tflite_langid.tflite` | 315.520 | Language ID |
| `mlkit_barcode_models/oned_feature_extractor_mobile.tflite` | 276.552 | 1D barcode features |
| `models_bundled/fssd_25_8bit_v2.tflite` (+ gray) | mỗi ~232 KB | FSSD nhẹ |
| `mlkit_barcode_models/oned_auto_regressor_mobile.tflite` | 213.880 | 1D barcode regressor |
| `mlkit-google-ocr-models/gocr/layout/*/model.tflite` | 129.612 / 114.384 | Layout clustering/splitting |

Kèm theo: `FastestDet.ncnn.bin/param` (NCNN), `haarcascade_frontalface_default.xml` (OpenCV), `lbfmodel.yaml`, ML Kit OCR pipeline (`.binarypb`, `.fb`, `.bincfg`, `.conv_model`, `.lstm_model`).

### 10.2. Native ML

- `libmlkit_google_ocr_pipeline.so` + `libmlkitcommonpipeline.so` (~13,7 MB): MediaPipe + TFLite OCR tiếng Việt (aksara, gocr, taser).
- `libface_detector_v2_jni.so` (5,3 MB), `libVFaceLib.so` (OpenCV), `libpicodet_ncnn.so` (NCNN), `libbarhopper_v3.so` (barcode).
- `libtrueidlib.so` + TrueID SDK: eKYC, liveness, OCR CCCD.

**Ý nghĩa:** ZaloPay thực hiện OCR CCCD, nhận diện khuôn mặt/liveness, quét QR/barcode hoàn toàn on-device — giảm tải máy chủ, tăng tốc độ và bảo vệ dữ liệu sinh trắc (không gửi ảnh thô lên server nếu không cần).

---

## 11. Bảo mật, mã hóa và JS Bridge

### 11.1. Mã hóa & lưu trữ

- **SQLCipher** (`libsqlcipher.so`, 3,5 MB): SQLite mã hóa AES cho cơ sở dữ liệu cục bộ.
- **BoringSSL** trong `libtaibmcf.so` / `libfgcpkjvqe.so`: TLS, RSA, AES (hàng chục thuật toán: AES-128/192/256 CBC/CFB/OFB/ECB, RSA-MD5/SHA1/SHA256, RSAES-OAEP, v.v.).
- **RSA public key** (`assets/config/public_key.txt`, 450 bytes, RSA 2048) + `signature.txt` (350 bytes Base64): dùng để ký/xác thực cấu hình hoặc request.
- **Alipay SecurityGuardLite** (`libsecurity_lite.so`): JNI `encrypt`, `decrypt`, `signRequest`, `init`; thuật toán `encryptByAes`/`decryptByAes`, `rsa_private_encrypt/decrypt`, `aes_cbc_decrypt`; obfuscator Alipay.
- **BShield** (`assets/bshield.dat`): dữ liệu bảo vệ của Alipay (entropy cao).
- **fgcpkjvqe.dat** (776 KB, entropy cao): có thể là dữ liệu mã hóa đi kèm `libfgcpkjvqe.so`.
- **Biometric:** `USE_BIOMETRIC`, `USE_FINGERPRINT`, `FaceAuthActivity`, `SmartOTPActivity`.
- **Root/emulator detection:** `ic_warning_rooted.png`, `DETECT_SCREEN_CAPTURE`, `REORDER_TASKS` — cho thấy có kiểm tra thiết bị root và chống chụp màn hình.
- **PCI DSS:** `pcidss` seal URL, `ic_pci_dss.webp` — tuân thủ chuẩn thẻ quốc tế.

### 11.2. JS Bridge (WebView ↔ Native)

Ba bridge gần như giống hệt nhau, chỉ khác tên đối tượng:

- `ZaloPayJSBridge` (`jsbridge.js`, `android_jsbridge.js`)
- `ZaloPayInternalJSBridge` (`internaljsbridge.js`)
- `AlipayJSBridge` + `__alipayConsole__` (Griver `h5_bridge.js`)

Giao thức: `call(func, param, callback)` → JSON `{func, param, msgType:'call', clientId}` → `callNativeFunction("call", invokeMsg)`; chiều ngược: `callback(clientId, param)`, `trigger(name, param, clientId)` (sự kiện DOM, hỗ trợ `back`, `firePullToRefresh`). Đây là bề mặt tấn công XSS → native nếu WebView load URL không tin cậy — do đó `webapp.allow_urls` và `deeplink.external_black_list` là kiểm soát quan trọng.

### 11.3. Lưu ý PII

`assets/client_tracking.json` (146 KB) chứa dữ liệu mẫu chuyến xe (tên khách, số điện thoại, email, địa chỉ, biển số, tọa độ, v.v.). Báo cáo này **không trích dẫn** nội dung PII; chỉ ghi nhận sự tồn tại và khuyến nghị nhà phát triển không đóng gói dữ liệu người thật vào APK phân phối.

---

## 12. React Native bundle (android.1.zip)

- `assets/android.1.zip` (3.913.966 bytes) chứa 326 mục, trong đó `main.jsbundle` (3.613.478 bytes) là bundle React Native production (`__DEV__=false`, `NODE_ENV=production`).
- Bundle sử dụng Metro bundler (`__d`, `__r`, `require`), React Navigation, Redux, Moment, LocalForage, `react-native-*` (async-storage, masked-view, netinfo, slider, viewpager, webview), `zalopaycomponents`, `transactionhistoryv2`, `clearingaccount`, `registeronlinebanking`, v.v.
- URL trong bundle: `https://cdn.zalopay.com.vn`, `https://cps.zalopay.com.vn`, `https://qccdn.zalopay.com.vn`, Sentry DSN (`o529616.ingest.sentry.io/5666380`), `https://sandbox.zalopay.com.vn/static`, `http://localhost:8081` (dev server còn sót trong production bundle — nên loại bỏ).
- `assets/bundle.json` khai báo `external_bundle` (`android.1.zip`, checksum `An3SoswJ6ujv2Qk6UqYxcw`, version `1.0.0`) và `internal_bundle` (`ba910kal`, `1.0.20160523.001`).

---

## 13. Thảo luận, hạn chế và đạo đức

### 13.1. Thảo luận

ZaloPay 11.17.0 là một **super-app** điển hình: ví điện tử + ngân hàng + mini-app platform + eKYC + quảng cáo + analytics, tất cả trong một gói ~105 MB (XAPK). Kiến trúc lai (native Kotlin/Compose + React Native + Griver mini-program + WebView) cho phép phát triển nhanh các tính năng mới (373 mini-app) mà vẫn giữ hiệu năng cho luồng thanh toán lõi. Việc thực hiện ML on-device (OCR, face, barcode) và mã hóa cục bộ (SQLCipher, SecurityGuardLite) thể hiện sự trưởng thành về bảo mật và riêng tư.

Tuy nhiên, kích thước lớn, tên thư viện obfuscated, và tập quyền rộng đặt ra thách thức cho kiểm toán độc lập và người dùng cuối. Việc đóng gói dữ liệu mẫu có PII vào APK là một thực hành cần chấm dứt.

### 13.2. Hạn chế

1. **Chỉ phân tích tĩnh:** Không có decompile (jadx), không phân tích luồng điều khiển, không kiểm tra runtime. Các suy luận về vai trò thư viện obfuscated là có cơ sở nhưng chưa chắc chắn 100%.
2. **Chỉ ARMv7:** Gói này không chứa ARM64; kết quả không áp dụng trực tiếp cho thiết bị ARM64.
3. **Manifest binary:** Trích xuất chuỗi UTF-16 chưa khôi phục đầy đủ cây XML (cần apktool để giải mã chính xác).
4. **Không kiểm tra chữ ký số:** Chưa xác minh chữ ký APK/XAPK bằng `apksigner` (không có trong sandbox).
5. **Dữ liệu động:** Không gọi API, không quan sát lưu lượng; bề mặt API chỉ dựa trên config và chuỗi.

### 13.3. Đạo đức và pháp lý

- Phân tích tĩnh cho mục đích nghiên cứu, giáo dục và tương tác (interoperability) là hợp pháp tại nhiều quốc gia, nhưng việc phân phối lại APK, trích xuất bí mật để khai thác, hoặc vượt qua cơ chế bảo mật thanh toán là **không được phép**.
- Báo cáo này không chứa khóa riêng, token, hay hướng dẫn khai thác. Khóa Google API trong manifest là khóa client công khai; chúng tôi chỉ ghi nhận.
- Dữ liệu cá nhân trong assets mẫu đã được che giấu.
- Người dùng muốn tái lập nghiên cứu nên sử dụng bản APK hợp pháp từ nguồn chính thức và tuân thủ Điều khoản của VNG/ZaloPay.

---

## 14. Kết luận và hướng nghiên cứu tiếp theo

Chúng tôi đã kiểm kê và phân tích toàn diện ZaloPay 11.17.0: từ chuỗi đóng gói XAPK, manifest và quyền, DEX và kiến trúc, native libs, assets và cấu hình, API và mini-app, ML on-device, đến bảo mật và React Native bundle. Kết quả cho thấy một super-app fintech phức tạp, hiện đại, với nhiều lớp bảo vệ nhưng cũng có các điểm cần cải thiện (PII trong assets, dev URL trong bundle, quyền rộng).

**Hướng tiếp theo:**
1. Decompile bằng jadx + apktool để khôi phục mã nguồn giả và cây manifest chính xác.
2. Phân tích động trong máy ảo cô lập (Frida, mitmproxy) để xác thực bề mặt API — với ủy quyền hợp pháp.
3. So sánh liên phiên bản (11.17.0 vs các bản trước/sau) để nghiên cứu tiến hóa kiến trúc.
4. Đánh giá bảo mật chính thức (penetration test) với sự phối hợp của VNG.
5. Mở rộng phương pháp REA: tự động hóa kiểm kê ISO/APK/XAPK và sinh báo cáo học thuật kèm video (dự án này đã khởi tạo pipeline TypeScript trong `video/`).

---

## 15. Tài liệu tham khảo

Xem `docs/REFERENCES.bib` (BibTeX) và `paper/` (bản thảo học thuật). Các nguồn chính:

- morluto/rea — *Reverse engineer anything with agents* — https://github.com/morluto/rea
- Android Developers — *App Bundle*, *APK splits*, *Binary XML* — https://developer.android.com
- OWASP — *Mobile Application Security Testing Guide (MASTG)* — https://owasp.org/www-project-mobile-testing-guide/
- Google ML Kit — *On-device OCR, Barcode, Face Detection* — https://developers.google.com/ml-kit
- Alipay Griver / Ariver — *Mini-program runtime* — https://opendocs.alipay.com/mini
- SQLCipher — https://www.zetetic.net/sqlcipher/
- BoringSSL — https://boringssl.googlesource.com/boringssl/
- React Native — https://reactnative.dev
- Jetpack Compose — https://developer.android.com/jetpack/compose

---

## Phụ lục A — Hash SHA-256

Xem `docs/hashes.json`. Tóm tắt trong §3.3.

## Phụ lục B — Danh sách quyền đầy đủ

55 quyền từ `manifest.json` (XAPK). Xem §4.2 để phân loại. Danh sách gốc:

`POST_NOTIFICATIONS`, `READ_CONTACTS`, `INTERNET`, `ACCESS_WIFI_STATE`, `ACCESS_NETWORK_STATE`, `READ_PHONE_STATE`, `com.google.android.c2dm.permission.RECEIVE`, `WAKE_LOCK`, `NFC`, `MANAGE_OVERLAY_PERMISSION`, `WRITE_EXTERNAL_STORAGE`, `READ_EXTERNAL_STORAGE`, `ACCESS_COARSE_LOCATION`, `ACCESS_FINE_LOCATION`, `USE_FINGERPRINT`, `WRITE_USE_APP_FEATURE_SURVEY` (Samsung), `WRITE_CALENDAR`, `READ_CALENDAR`, `USE_BIOMETRIC`, `RECORD_AUDIO`, `MODIFY_AUDIO_SETTINGS`, `INSTALL_SHORTCUT`, `UNINSTALL_SHORTCUT`, `WRITE_SETTINGS`, `READ_SETTINGS`, `bbk.launcher2 READ/WRITE_SETTINGS`, `QUERY_ALL_PACKAGES`, `CAMERA`, `VIBRATE`, `ACCESS_THIRD_PARTY_APP_AUTHORIZATION` (Zalo), `CHANGE_WIFI_STATE`, `DETECT_SCREEN_CAPTURE`, `AD_ID`, `ACCESS_ADSERVICES_*` (4), `RECEIVE_BOOT_COMPLETED`, `BILLING`, `BIND_GET_INSTALL_REFERRER_SERVICE`, `FOREGROUND_SERVICE`, `DYNAMIC_RECEIVER_NOT_EXPORTED_PERMISSION`, `REORDER_TASKS`, `READ_APP_INFO` (Samsung Maps), `GET_COMMON_DATA` (Huawei), `READ_BASIC_PHONE_STATE`, `WRITE_CONTACTS`, `READ_MEDIA_AUDIO`.

## Phụ lục C — Tái lập phân tích

```bash
# 1. Kiểm kê
python3 scripts/inventory.py
# 2. Chuỗi DEX
python3 scripts/dex_strings.py
# 3. Chuỗi Manifest
python3 scripts/manifest_strings.py
# 4. Video (TypeScript)
cd video && npm install && npm run build && npm run render
```

Tất cả script đều chạy offline, chỉ cần Python 3.11+ (stdlib) và Node 22+.

---

*Báo cáo được tạo bởi quy trình phân tích tĩnh tự động + kiểm chứng thủ công. Mọi con số đều có thể kiểm chứng từ `docs/INVENTORY.csv` và `docs/hashes.json`.*
