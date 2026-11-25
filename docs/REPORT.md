# Giải phẫu tĩnh ZaloPay 11.17.0 — Báo cáo kỹ thuật đầy đủ

**Bản ghi phân tích:** `Zalopay-Thanh toán & Tài chính_11.17.0_APKPure.zip`
**Đối tượng:** `vn.com.vng.zalopay` 11.17.0 (versionCode `1102758`), minSdk 26 / targetSdk 36
**Ngày phân tích:** 2026-11-25 · **Phương pháp:** phân tích tĩnh hoàn toàn trên tệp lưu trữ
**Phiên bản tài liệu:** 1.0 · **Kèm theo:** phim nghiên cứu `artifacts/zalopay-11.17.0-anatomy.mp4`

---

## 0. Tóm tắt dành cho người đọc bận rộn

Báo cáo này mổ xẻ **toàn bộ** gói phát hành XAPK của ZaloPay 11.17.0: 5 tệp APK, **6,464 entry ZIP**, **20 tệp DEX**, **27 thư viện native** (cộng một thư viện trong `assets/`), **22 mô hình máy học trên thiết bị**, và **6,162 entry** trong APK base. Mọi con số trong báo cáo đều sinh tự động từ pipeline trong `research/scripts/` và có thể tái lập byte-for-byte. Mười kết luận chính:

1. **Gói hàng là bản đóng gói lại (repackaged), không phải bản Play Store.** 4,249/6,162 payload trong APK base không căn lề 4 byte; APK base không có chữ ký v1 (không có `META-INF/*.SF`).
2. **Khối chữ ký APK bị hỏng cấu trúc.** Cặp id-value đầu tiên khai báo 1,383 byte nhưng payload thực tế 1,387 byte; cặp thứ hai nằm ở offset lệch và không parse được. Chữ ký RSA **không xác minh được** trên bất kỳ cửa sổ byte liên tục nào.
3. **Danh tính người ký thì đọc được và nhất quán.** Chứng chỉ tự ký `CN=Vu Manh Hieu`, RSA 2048, hiệu lực 2016-04-21 → 2041-04-15, SHA-256 `59dd31fdf36070a0d7f8c8cde843fac3…`.
4. **Mật độ mã nguồn:** 60,671 lớp, 351,248 tham chiếu phương thức, 374,276 hằng chuỗi — trong đó 28.5% package bị rút gọn tên bởi R8.
5. **Ba tệp DEX chạm trần 16-bit:** `classes.dex`, `classes4.dex`, `classes7.dex` ở mức 91.5% giới hạn 65.536 method id; multidex là ràng buộc định dạng, không phải kiến trúc.
6. **Trọng lượng phòng thủ nằm ở native:** 54.8 MiB C++ (96.3% đã strip) chứa chống gian lận, liveness, keystore và mã hoá; 7 thư viện mang tên ngẫu nhiên hoá.
7. **Hệ sinh thái SDK rộng:** 56 SDK nhận diện bằng bằng chứng chuỗi, gồm Alibaba mPaaS (runtime mini-app), Google ML Kit, TrueID eKYC (Việt Nam), VNPAY, Visa, AppsFlyer, Meta Audience Network.
8. **Bề mặt mạng:** 168 host duy nhất, 45 first-party; 290 đường dẫn REST còn sống dưới dạng chuỗi — đủ để suy ra *hình dạng* giao thức nhưng không đủ để lấy bí mật.
9. **Siêu ứng dụng bằng cấu hình:** 373 mini-app nằm trong một tệp JSON 883 KiB; runtime là Alibaba ARiver/mPaaS, không phải code first-party.
10. **Quyền riêng tư:** 49 quyền, 14 quyền nhóm nguy hiểm, 200 activity, 34 service, 22 receiver, 9 provider — bề mặt tấn công cấp hệ điều hành là đáng kể nhưng có thể kiểm kê.

> **Tuyên bố giới hạn:** nghiên cứu này **chỉ phân tích tĩnh**. Không có kết nối mạng tới hạ tầng ZaloPay/VNG; không giải mã, không vượt qua kiểm soát truy cập, không tấn công, không trích xuất bí mật, PIN, khoá hay dữ liệu khách hàng. Những chỗ bằng chứng dừng lại, báo cáo nói rõ là dừng.

---

## 1. Phạm vi, đạo đức và khung pháp lý

### 1.1 Câu hỏi nghiên cứu

Một ứng dụng thanh toán được quản lý chặt (regulated) ở quy mô hàng chục triệu người dùng thực sự chứa gì, và **ranh giới của plaintext** nằm ở đâu? Cụ thể:

- **RQ1 — Cấu trúc:** gói phát hành được tổ chức thế nào (container, split, định dạng), và cấu trúc đó nói gì về chuỗi cung ứng phần mềm?
- **RQ2 — Phân tầng:** phần nào là first-party, phần nào là SDK bên thứ ba, và tầng nào gánh trách nhiệm bảo mật?
- **RQ3 — Bằng chứng:** với chỉ dữ liệu tĩnh, có thể chứng minh (không phải suy đoán) điều gì về tính toàn vẹn của gói?
- **RQ4 — Giới hạn:** điểm nào phân tích tĩnh *không thể* vượt qua, và vì sao việc nói rõ giới hạn lại quan trọng hơn việc suy diễn?

### 1.2 Nguyên tắc thực hiện

| Nguyên tắc | Cách hiện thực |
|---|---|
| Chỉ tĩnh | Không chạy ứng dụng, không cấp quyền, không mô phỏng tương tác, không gọi mạng |
| Tái lập | Mỗi số liệu sinh từ script; dữ liệu trung gian lưu JSON; video chỉ đọc JSON |
| Không tiết lộ bí mật | Không trích PIN/khoá/token; chỉ nêu *hình dạng* giao thức từ tên endpoint |
| Trung thực về giới hạn | Mọi kết luận "không xác minh được" được ghi rõ, không làm mềm |
| Tôn trọng pháp lý | Phân tích phần mềm đã phát hành công khai cho mục đích nghiên cứu, không vượt qua bảo vệ kỹ thuật |

### 1.3 Những gì báo cáo này **không** làm

- Không deobfuscate bytecode về mã nguồn Java/Kotlin tương đương (chỉ đọc metadata DEX: lớp, phương thức, chuỗi).
- Không reverse-engineer thuật toán mã hoá độc quyền trong native; chỉ kiểm kê thuật toán *được tham chiếu*.
- Không giải mã `assets/fgcpkjvqe.dat`, `assets/taibmcf.dat`, `assets/bshield.dat` (payload của Alibaba Security Guard) — đây là bí mật kỹ thuật của bên thứ ba.
- Không kết luận về an toàn thực tế của giao thức thanh toán; chỉ mô tả bề mặt quan sát được.

---

## 2. Chuỗi hành trình bằng chứng (chain of custody)

Mọi phân tích bắt đầu bằng băm. Bảng dưới là "dấu vân tay" của artefact; bất kỳ ai có cùng tệp đều tái lập được toàn bộ báo cáo.

| Tệp | Bytes | SHA-256 |
|---|---|---|
| ZIP ngoài | 98,813,878 | `34b9b6b6735f0f739617f97a259f931d17cdbacb51871c40b066de41f4b4610a` |
| XAPK | 105,608,128 | `686e9459da646c18b53a67d57e5a23c49466da140ec1e3e9682032f453ea7b8d` |
| config.armeabi_v7a.apk | 24,828,408 | `456aa08f4fa2e3f952657519ccf0b3f8…` |
| config.en.apk | 74,137 | `87699fda97e8a9be06583d9261689966…` |
| config.vi.apk | 86,425 | `985fc073b810b6286c116400a88e5552…` |
| config.xhdpi.apk | 1,232,563 | `f31350c3caed15099d2c2d325d88fef5…` |
| vn.com.vng.zalopay.apk | 79,359,087 | `e289d547b45f6224aed8ce7bf72d75ea…` |

**Ghi chú kỹ thuật về container:** tệp ngoài cùng là ZIP chứa **một** tệp `.xapk`; XAPK lại là ZIP chứa 5 APK + `icon.png` + `manifest.json`. Tổng giải nén: **6,464 entry** trên 5 APK, **195,327,309** byte chưa nén (1.8× dung lượng đĩa).

---

## 3. Giải phẫu container

### 3.1 Năm split APK

| Split | Vai trò | Bytes | Entry | Giải nén | Lệch căn |
|---|---|---|---|---|---|
| config.armeabi_v7a.apk | config | 24,828,408 | 32 | 57,459,525 | 23 |
| config.en.apk | config | 74,137 | 6 | 64,699 | 3 |
| config.vi.apk | config | 86,425 | 6 | 78,175 | 3 |
| config.xhdpi.apk | config | 1,232,563 | 258 | 1,329,300 | 83 |
| vn.com.vng.zalopay.apk | base | 79,359,087 | 6162 | 136,395,610 | 4249 |

Quan sát:

- **Base APK** (75.7 MiB) chứa toàn bộ DEX, `AndroidManifest.xml`, `resources.arsc` và `assets/`.
- **ABI split** chỉ chứa `lib/**` nhưng chiếm tới **23.7 MiB** — lớn thứ hai trong gói.
- **Locale split** (`config.vi`, `config.en`) chỉ chứa `resources.arsc` đã biên dịch lại + manifest rỗng: 74 KiB và 86 KiB.
- **Density split** (`config.xhdpi`) chứa 258 drawable; base APK không còn drawable đa mật độ.

### 3.2 Pháp y ZIP: căn lề và chữ ký

Trường `header_offset` trong central directory cho phép xác định chính xác vị trí bắt đầu payload từng entry. Với APK được `zipalign` chuẩn, mọi payload phải căn 4 byte (hoặc 16 KiB với `-P 16`) để `mmap()` không cần copy.

- APK base: **4,249/6,162 entry** lệch căn (≈ 69.0%).
- ABI split: 23 lệch · density: 83 lệch · hai locale split: 3 lệch mỗi tệp.
- APK base **không** có `META-INF/*.SF`, `*.RSA`, `*.DSA`, `*.EC` — tức không có chữ ký JAR v1. Hai split cấu hình *có* `META-INF/BNDLTOOL.SF/.RSA` (dấu vết bundletool).

Hệ quả: trên bản base, Android chỉ có thể xác thực qua **APK Signature Scheme v2**. Nếu khối v2 hỏng (mục 9), gói không còn đường xác thực nào — đúng như quan sát.

---

## 4. Manifest, thành phần và quyền

### 4.1 Nhận dạng

| Trường | Giá trị |
|---|---|
| Package | `vn.com.vng.zalopay` |
| versionName / Code | `11.17.0` / `1102758` |
| minSdk / targetSdk / compileSdk | 26 / 36 / 36 |
| Activity chính | `vn.com.vng.zalopay.splashscreen.presentation.SplashScreenActivityV2` |
| AGP khi build | 8.12.3 (từ `META-INF/com/android/build/gradle/app-metadata.properties`) |
| VCS info | `NO_SUPPORTED_VCS_FOUND` (build không nhúng commit hash) |

### 4.2 Thành phần

| Loại | Số lượng | Ghi chú |
|---|---|---|
| Activity | 200 | gồm toàn bộ `com.vnpay.vntaxi.*` (module gọi xe nhúng) |
| Service | 34 | Firebase Messaging, ML Kit, CameraX, WorkManager, GMS Ads |
| Receiver | 22 | widget (QR cá nhân, game, phim), cài đặt ứng dụng |
| Provider | 9 | FileProvider, FirebaseInitProvider, AudienceNetwork, Griver |

Sự xuất hiện của **module taxi VNPAY** (`com.vnpay.vntaxi.*`) bên trong APK ZaloPay là bằng chứng trực tiếp của mô hình "super-app nhúng đối tác": không phải deep-link sang app khác mà là code của đối tác được biên dịch vào chính gói.

### 4.3 Quyền

| Nhóm | Quyền |
|---|---|
| Requested | 49 |
| Dangerous | ACCESS_COARSE_LOCATION, ACCESS_FINE_LOCATION, CAMERA, POST_NOTIFICATIONS, READ_BASIC_PHONE_STATE, READ_CALENDAR, READ_CONTACTS, READ_EXTERNAL_STORAGE, READ_MEDIA_AUDIO, READ_PHONE_STATE, RECORD_AUDIO, WRITE_CALENDAR, WRITE_CONTACTS, WRITE_EXTERNAL_STORAGE |
| Desc | android.hardware.bluetooth_le, android.hardware.camera, android.hardware.camera.any, android.hardware.camera.autofocus, android.hardware.location.gps, android.hardware.nfc, android.hardware.nfc.hce |

Phân loại nhanh:

- **Nhóm nguy hiểm (runtime prompt):** 14 quyền — danh bạ (đọc/ghi), camera, micro, vị trí chính xác & tương đối, trạng thái điện thoại, lịch (đọc/ghi), bộ nhớ ngoài, thông báo, âm thanh media.
- **Đặc thù:** `QUERY_ALL_PACKAGES` (nhìn thấy mọi app đã cài — cần cho chống gian lận/liệt kê ví đối thủ), `DETECT_SCREEN_CAPTURE` (phát hiện chụp màn hình), `NFC` (HCE thanh toán), `USE_BIOMETRIC`, `AD_ID`, ba quyền `ACCESS_ADSERVICES_*` (Privacy Sandbox).
- **Quyền riêng của launcher:** `com.bbk.launcher2.*` (BBK/vivo), `com.samsung.android.*`, `com.huawei.appmarket.*` — dấu hiệu tích hợp OEM để tạo shortcut và push.

---

## 5. Tầng bytecode (DEX)

### 5.1 Quy mô

| DEX | MiB | Strings | Types | Methods | Classes |
|---|---|---|---|---|---|
| classes.dex | 8.42 | 61,218 | 17,196 | 59,948 | 10,703 |
| classes10.dex | 0.07 | 920 | 461 | 578 | 92 |
| classes11.dex | 0.16 | 1,584 | 740 | 1,171 | 177 |
| classes12.dex | 0.38 | 3,207 | 1,191 | 2,604 | 425 |
| classes13.dex | 0.14 | 1,381 | 590 | 997 | 139 |
| classes14.dex | 0.08 | 961 | 404 | 575 | 90 |
| classes15.dex | 0.08 | 1,005 | 436 | 658 | 113 |
| classes16.dex | 0.02 | 261 | 116 | 101 | 20 |
| classes17.dex | 0.20 | 1,847 | 848 | 1,311 | 243 |
| classes18.dex | 0.09 | 1,094 | 553 | 668 | 115 |
| classes19.dex | 0.69 | 4,159 | 1,782 | 3,781 | 777 |
| classes2.dex | 15.75 | 56,948 | 11,243 | 45,527 | 7,421 |
| classes3.dex | 0.81 | 8,010 | 3,827 | 6,354 | 1,081 |
| classes4.dex | 8.98 | 52,812 | 19,105 | 59,997 | 11,441 |
| classes5.dex | 7.02 | 56,424 | 14,079 | 49,055 | 7,983 |
| classes6.dex | 7.26 | 55,274 | 18,399 | 49,201 | 5,475 |
| classes7.dex | 10.30 | 59,110 | 20,633 | 60,000 | 12,953 |
| classes8.dex | 0.23 | 1,906 | 915 | 1,552 | 295 |
| classes9.dex | 1.16 | 6,155 | 2,175 | 7,170 | 1,128 |

Tổng: **60,671 lớp**, **351,248 tham chiếu phương thức**, **374,276 hằng chuỗi**, **114,693 type id** trên 20 tệp, phiên bản định dạng 038.

### 5.2 Giới hạn 16-bit và multidex

Số method id trong một DEX bị giới hạn bởi kiểu 16-bit (65.536). Ba tệp đạt 91.5% giới hạn:

- `classes.dex` — 59.948 method / 10.703 lớp
- `classes4.dex` — 59.997 method / 11.441 lớp
- `classes7.dex` — 60.000 method / 12.953 lớp

Điều này chứng minh thứ tự sắp xếp DEX là do **bộ đóng gói** quyết định (R8/D8 pack theo giới hạn), không phản ánh ranh giới module. Hệ quả thực tiễn: bất kỳ phân tích nào dựa trên "tệp DEX nào chứa gì" đều sai lệch về mặt kiến trúc.

### 5.3 Obfuscation

- 28.5% package (1558/5476) có tên thoả mẫu rút gọn cực ngắn (`a`, `Kp`, `q9`, ...).
- 25.9% lớp nằm trong các package đó.
- `SourceFile` bị chuẩn hoá thành literal `"SourceFile"` cho 100% lớp → **không còn tên tệp nguồn**; không thể tái tạo cấu trúc module từ metadata.
- 11 tệp `.kotlin_module` và 5.476 package còn lại cho thấy codebase gốc là Kotlin-first, dùng Dagger/Hilt.

### 5.4 Vì sao vẫn nhận diện được SDK?

Vì R8 **không thể** xoá: (a) chuỗi hằng dùng cho URL/protocol, (b) tên symbol JNI phải khớp `Java_<package>_<Class>_<method>`, (c) tên lớp trong protobuf descriptor, (d) tài nguyên và asset. Do đó fingerprint theo *bằng chứng chuỗi* đáng tin hơn fingerprint theo tên package.

---

## 6. Tầng native

### 6.1 Kiểm kê

| Thư viện | MiB | Vai trò | Entropy | Hardening |
|---|---|---|---|---|
| libtaibmcf.so | 12.03 | Alibaba Security Guard (mcf) — risk & anti-fraud engine | 6.12 | RBNPS |
| libmlkitcommonpipeline.so | 6.61 | ML Kit shared pipeline | 7.16 | RBNPS |
| libmlkit_google_ocr_pipeline.so | 6.47 | ML Kit Google OCR pipeline | 6.98 | RBNPS |
| libfgcpkjvqe.so | 6.22 | Randomised-name obfuscated payload (paired with assets/fgcpkjvqe.dat) | 6.27 | RBNPS |
| libface_detector_v2_jni.so | 5.14 | ML Kit face detection | 7.15 | RBNPS |
| libsqlcipher.so | 3.39 | SQLCipher encrypted database | 6.69 | RBNPS |
| libbarhopper_v3.so | 3.09 | ML Kit barcode scanning (barhopper) | 7.21 | RBNPS |
| librive-android.so | 3.08 | Rive animation runtime | 6.42 | RBNPS |
| libpicodet_ncnn.so | 2.85 | PicoDet detector on NCNN (card/document localisation) | 7.18 | RBNPS |
| libVFaceLib.so | 1.87 | TrueID eKYC liveness/face | 7.11 | RBNPS |
| liba97d.so | 0.95 | Randomised-name support library | 6.22 | RBNPS |
| libc++_shared.so | 0.83 | libc++ runtime | 6.88 | RBNP |
| libljsfrcwmw.so | 0.57 | Randomised-name support library | 6.43 | RBNPS |
| libcrashlytics-common.so | 0.42 | Crashlytics native handler | 6.91 | RBNPS |
| libnative-imagetranscoder.so | 0.33 | Fresco image transcoder | 6.96 | RBNPS |
| liblytcuhe.so | 0.17 | Randomised-name support library | 6.63 | RBNPS |
| librcmefqonnx.so | 0.17 | ONNX Runtime shim (randomised name) | 6.63 | RBNPS |
| libohxbee.so | 0.15 | Randomised-name support library | 6.25 | RBNPS |
| libsecurity_lite.so | 0.15 | Vendor security lite | 7.30 | RBNPS |
| libcrashlytics.so | 0.09 | Crashlytics native handler | 6.56 | RBNPS |
| libcrashlytics-handler.so | 0.08 | Crashlytics native handler | 6.52 | RBNPS |
| libtrueidlib.so | 0.08 | TrueID eKYC SDK | 6.95 | RBNPS |
| libimage_processing_util_jni.so | 0.02 | CameraX image processing | 6.57 | RBNPS |
| libnative-filters.so | 0.01 | Fresco filters | 6.33 | RBNPS |
| libandroidx.graphics.path.so | 0.01 | AndroidX path native | 5.77 | RBNPS |
| libcrashlytics-trampoline.so | 0.01 | unclassified | 4.66 | BN |
| libimagepipeline.so | 0.01 | unclassified | 5.16 | RBNPS |

- Tổng **54.8 MiB**, **96.3%** không còn `.symtab`.
- Hardening: RELRO 26/27, BIND_NOW 27/27, NX 27/27, PIE 26/27, stack canary 25/27. Đây là mức hardening "production mặc định" của NDK r2x — không phải gia cố đặc biệt, nhưng cũng không tuỳ tiện.
- Entropy cao nhất: `libsecurity_lite.so` = 7.30 bit/byte (gần với dữ liệu nén/mã hoá).

### 6.2 Tên thư viện ngẫu nhiên hoá

7 thư viện có tên dạng `lib[a-z0-9]{6,9}.so`: `libfgcpkjvqe.so, libljsfrcwmw.so, liblytcuhe.so, libohxbee.so, libsqlcipher.so, libtaibmcf.so, libtrueidlib.so`. Đây là kỹ thuật chống hooking theo tên: mỗi thư viện tự đăng ký JNI trong `JNI_OnLoad` (bằng chứng: export `JNI_OnLoad` + `MOR`-style registration), nên rename không làm hỏng chức năng nhưng làm hỏng script hook cứng tên.

Đáng chú ý: `liblytcuhe.so` và `librcmefqonnx.so` có **cùng kích thước 178.628 byte**, cùng bảng export, khác nhau 8.799 byte ở vùng `.text` — mẫu của cùng một build với tên khác nhau (hai "alias" của cùng thư viện runtime). Đây là quan sát định lượng, không phải suy đoán.

### 6.3 Chức năng suy ra từ symbol

- `libsecurity_lite.so` export đúng bốn hàm: `Java_com_alipay_plus_security_lite_SecurityGuardLiteNative_{init,encrypt,decrypt,signRequest}` → **Alibaba Security Guard Lite** (mã hoá + ký request).
- `libtrueidlib.so` export `Java_vn_ai_trueid_sdk_utils_SmartOTPHelper_vault*` và chuỗi `OCRA-1:HOTP-SHA256-8:QH64` → eKYC TrueID có **OCRA** (RFC 6287) cho OTP giao dịch, kèm kiểm tra debugger/integrity.
- `libohxbee.so` chứa tham chiếu `Lvn/ai/faceauth/sdk/presentation/domain/model/Liveness*` → liveness TrueID chạy native, không chỉ Java.
- `libsqlcipher.so` (net.zetetic) → cơ sở dữ liệu mã hoá cục bộ.
- `libtaibmcf.so` (12,0 MiB, lớn nhất) + `libfgcpkjvqe.so` (6,2 MiB) + `assets/taibmcf.dat`/`fgcpkjvqe.dat` → **Alibaba Security Guard (mcf)** — engine đánh giá rủi ro thiết bị.

---

## 7. Mật mã và quản lý khoá (mức kiểm kê)

Các thuật toán **được tham chiếu** trong hằng chuỗi (số lần xuất hiện):

| Thuật toán | Vai trò suy ra | Hits |
|---|---|---|
| Thuật toán | Vai trò | Hits |
|---|---|---|
| AES/GCM/NoPadding | AEAD dữ liệu at-rest | 7 |
| RSA/OAEP-SHA256 | bọc khoá | 2 |
| HmacSHA256 / SHA512 | ký request | 14 |
| PBKDF2-HMAC-SHA256 | dẫn xuất khoá | 1 |
| ECDH/X25519/ECIES | thoả thuận khoá | 379 |
| Ed25519 | xác minh chữ ký | 113 |
| AndroidKeyStore | khoá trong TEE/StrongBox | 8 |

**Post-quantum:** Bouncy Castle được nhúng kèm toàn bộ họ PQC (`Kyber/ML-KEM` 103, `Dilithium` 124, `SPHINCS+` 205, `Picnic` 36, `BIKE` 57, `HQC` 80, `FrodoKEM` 2 hits), và các tệp `org/bouncycastle/pqc/.../lowmcL5.bin.properties`. **Đây là bằng chứng cấp chuỗi**, không chứng minh PQC bảo vệ lưu lượng thật: BC nhúng nguyên thư viện.

**Key management:** `AndroidKeyStore`, `KeyGenParameterSpec`, `StrongBox` (1 tham chiếu), `BiometricPrompt`, `setUserAuthenticationRequired`. Kết hợp với `USE_BIOMETRIC` + `USE_FINGERPRINT` cho thấy khoá được bảo vệ bằng phần cứng/TEE ở thiết bị hỗ trợ.

**Chống chụp màn hình:** quyền `DETECT_SCREEN_CAPTURE` (Android 14+) và các chuỗi `FLAG_SECURE`.

---

## 8. eKYC, máy học trên thiết bị và tài nguyên

### 8.1 Mô hình

| Tệp | KiB |
|---|---|
| assets/custom_models/object_labeler.tflite | 3682 |
| assets/FastestDet.ncnn.bin | 2364 |
| assets/mlkit_odt_default_classifier/labeler_with_validation.tflite | 1667 |
| assets/mlkit_odt_localizer/localizer_with_validation.tflite | 1225 |
| assets/models_bundled/contours.tfl | 1098 |
| assets/models_bundled/LMprec_600.emd | 1053 |
| assets/models_bundled/fssd_medium_8bit_v5.tflite | 599 |
| assets/models_bundled/fssd_medium_8bit_gray_v5.tflite | 599 |
| assets/mlkit_barcode_models/barcode_ssd_mobilenet_v1_dmp25_quant.tflite | 381 |
| assets/models_bundled/blazeface.tfl | 340 |
| assets/mlkit-google-ocr-models/taser/detector/rpn_text_detector_mobile_space_to_depth_quantized_mbv2_v1.tflite | 333 |
| assets/mlkit-google-ocr-models/gocr/gocr_models/line_recognition_legacy_mobile/tflite_langid.tflite | 308 |
| assets/mlkit_barcode_models/oned_feature_extractor_mobile.tflite | 270 |
| assets/models_bundled/fssd_25_8bit_v2.tflite | 227 |
| assets/models_bundled/fssd_25_8bit_gray_v2.tflite | 226 |
| assets/mlkit_barcode_models/oned_auto_regressor_mobile.tflite | 209 |
| assets/mlkit-google-ocr-models/gocr/layout/line_clustering_custom_ops/model.tflite | 127 |
| assets/mlkit-google-ocr-models/gocr/layout/line_splitting_custom_ops/model.tflite | 112 |
| assets/models_bundled/BCLjoy_200.emd | 17 |
| assets/FastestDet.ncnn.param | 15 |
| assets/models_bundled/BCLrighteyeclosed_200.emd | 8 |
| assets/models_bundled/BCLlefteyeclosed_200.emd | 8 |

**22 tệp / 14.5 MiB** — nhiều hơn hẳn một app ví thông thường, và tập trung vào ba bài toán: (1) OCR giấy tờ (ML Kit text recognition + layout), (2) phát hiện khuôn mặt & trạng thái mắt (blazeface, `BCLlefteyeclosed`, `BCLrighteyeclosed`, `BCLjoy` — mô hình phân loại trạng thái mắt/miệng cho **liveness**), (3) định vị thẻ/giấy tờ trong khung hình (PicoDet trên NCNN, object_labeler).

Đáng chú ý: `libVFaceLib.so` + `libohxbee.so` + `assets/models_bundled/*.emd` (EMD = model encrypted/compiled của TrueID) tạo thành pipeline liveness khép kín, chạy offline.

### 8.2 Tài nguyên

- `resources.arsc`: **1.7 MiB**, 7,763 chuỗi toàn cục, 13 package tài nguyên (do nhiều AAR đóng gói).
- 5.209 tệp XML trong base: layout, drawable vector, animator, font, raw JSON animation (Lottie/Rive).
- 4 tệp `.riv` (Rive) + nhiều `.json` Lottie → animation runtime phong phú.

---

## 9. Pháp y khối chữ ký — trung tâm của báo cáo

### 9.1 Cấu trúc chuẩn (APK Signature Scheme v2)

Một APK được ký v2 có layout:

```
[ Contents of ZIP entries ][ APK Signing Block ][ Central Directory ][ EOCD ]
```

Khối chữ ký: `uint64 size` · các cặp `(uint64 length, uint32 id, payload)` · `uint64 size` · magic `APK Sig Block 42`. Mỗi cặp phải căn 8 byte; id v2 = `0x7109871a`, v3 = `0xf0536873`, v3.1 = `0x1b93ad61`.

### 9.2 Quan sát trên APK base

```
offset 78.807.040   f8 1f 00 00 00 00 00 00   → size = 8.184 byte
offset 78.807.048   67 05 00 00 00 00 00 00   → length = 1.383 byte
offset 78.807.056   1a 87 09 71               → id = 0x7109871a (v2)
offset 78.807.060   5f 05 00 00               → 1.375  (độ dài sequence signer)
offset 78.807.064   5b 05 00 00               → 1.371  (độ dài 1 signer)
offset 78.807.068   1d 03 00 00               → 797    (độ dài signed data)
```

Ba bất thường định lượng:

1. **Độ dài cặp không khớp nội dung.** Khai báo 1.383 byte, nhưng payload trường bắt đầu ngay sau **4 byte id** (không có prefix `uint64` riêng cho payload như chuẩn quy định cho cặp) — thực tế chiếm **1.387 byte**. Bộ parse nghiêm ngặt sẽ đọc lệch 4 byte và đồng bộ sai ở cặp kế tiếp.
2. **Cặp thứ hai không hợp lệ.** Tại offset 78.808.448 xuất hiện id `0xf0536873` (v3) tại vị trí **không căn 8**, với "length" đọc ra là 6.557.241.080.535.864.168 — vô nghĩa. Payload của cặp này lặp lại đúng khối signed-data 797 byte của cặp v2. Không có signer v3 nào parse được.
3. **Digest không tái lập.** Digest nhúng sẵn: `308202cd308201b5a0030201020204153ea9fd300d06092a864886f70d01010b05003017311530130603550403130c5675204d616e682048696575301e170d3136303432313135343131345a170d3431303431353135343131345a3017311530130603550403130c5675204d616e68204869657530820122300d06092a864886f70d01010105000382010f003082010a0282010100befc34ab1b001eb93689feaf725fac597d414b6be558550707b47fa6680b420099ba75f78f80c504a769bc85148b80ab8504bf48e93a3ab38ac43db0b9d4c4bba631ac305f5226cf1036446fa7cb41b20c3beee05d9c84e6937727fbceecf93e7c48a5b35c9291dbf38c6d71a2e8658ab685fbe33f973dec8712d957545e08e0b156e13d6c2e0db2b48d093a9c3e9f1e6604933c2a3799c644cad1eb9f404c30aee0497ca955b6cbeecd2d22bd696f68642ad8c33072e4445128e2e3fd6bdf16596721f84542d5b016e6c6c76d1690389334d290a95dfe973b6031c6ceaa3b47c56904ce9f5a68650fe1f673011c767c20219b6c0e9947154e16b0ac79c782bf0203010001a321301f301d0603551d0e04160414ab4e99383a8eba9875d3afd2b0a7e2d2d1adb1a4300d06092a864886f70d01010b050003820101007149aa049c4a33004ec7c2dac2b51c40aa2e70ba3a1dbab3a53cbf8c389d1f330c2948c84a2059c18f7eb718651a7783afbdea39a357ac360d589717acbf872a3bbce930d187690056f7ef9f59527b0c88015495b81503c21a58280a138e3433a45379b277ba2eb315a3ab855f602131993fbce5500081d0be6ba3f7a3fdc8de3e0a0a1ba02a59ce841539cc7a24123b8b070a4dd1151ff1a182e95172f9ff601b183c384af7bb8dadce591c2ab26e80b342cd966558096a5ca4fa54aea01f73c6b607ce413bb30c307665b19133d3665a6d942f4322fb0d2532b4b0bed6d08d86abe6dcd6cbd2513781bb6c12e3cde3f51d4c167d16f6526895c005be54bceb` (thuật toán `0x0103` = RSASSA-PKCS1-v1_5 / SHA-256). Chúng tôi đã tính lại digest nội dung theo đúng thuật toán chunked của AOSP cho **41 biên khối khác nhau** (offset khối chữ ký từ 78.807.000 đến 78.807.080, mỗi biên thử thêm ±8) — **không khớp**. Quét toàn bộ 78 MiB theo cửa sổ 256 byte bằng cách giải mã RSA (`m^e mod n`) tìm cấu trúc PKCS#1 v1.5 hợp lệ cũng **không tìm thấy** chữ ký nào.

### 9.3 Danh tính người ký (phần đọc được)

| Trường | Giá trị |
|---|---|
| Subject | `CN=Vu Manh Hieu` |
| Issuer | `CN=Vu Manh Hieu` (tự ký) |
| Serial | `0x153ea9fd` |
| Hiệu lực | 2016-04-21 15:41:14+00:00 → 2041-04-15 15:41:14+00:00 |
| Khoá | RSAPublicKey 2048 bit |
| Sig alg | sha256WithRSAEncryption |
| SHA-256 | `59dd31fdf36070a0d7f8c8cde843fac371df7f1a915c0136c9c9b55992e8cdae` |
| SPKI SHA-256 | `3ed316c8ee1524a8b1d224f52d6c132c8f29d69392544ed1ae3c8e6c6929036d` |

Chứng chỉ parse được đầy đủ, tự ký, `sha256WithRSAEncryption`. Khoá công khai nhúng trong signer khớp đúng SPKI của chứng chỉ (`3ed316c8…036d`). Nghĩa là: **khối dữ liệu chứng chỉ nguyên vẹn**, chỉ có *framing* và *chữ ký* là không kiểm chứng được.

### 9.4 Phán quyết

> **Trạng thái: cấu trúc parse được, mật mã không chứng minh được.**
>
> - Chứng chỉ: **hợp lệ về cấu trúc**, tự ký, còn hiệu lực đến 2041.
> - Digest nội dung: **KHÔNG xác minh được** (không tái lập được trên bất kỳ biên khối nào).
> - Chữ ký RSA: **KHÔNG xác minh được** (không tìm thấy chữ ký PKCS#1 v1.5 hợp lệ trong khối).
>
> Cách diễn đạt đúng là "unverified", **không** phải "invalid signature", và tuyệt đối **không** phải "valid". Báo cáo giữ nguyên văn: *structurally parsed, cryptographically unproven*.

### 9.5 Vì sao điều này quan trọng

Với người dùng cuối, APK từ một mirror (APKPure) không có chuỗi tin cậy tới Play. Nếu khối v2 hỏng, hệ thống vẫn có thể cài (tuỳ ROM: một số chấp nhận v1/v2 thiếu nếu `unknown sources`), nhưng **không ai chứng minh được gói chưa bị sửa**. Với nhà nghiên cứu, đây là ví dụ sạch về một lớp "integrity theatre": giao diện có vẻ có chữ ký (có block, có certificate, có digest) nhưng không có bất kỳ bước nào kiểm chứng được.

**Khuyến nghị phòng thủ (cho đội phát hành):**
1. Xác minh bằng `apksigner verify --print-certs` trên **split do Play phục vụ**, không dùng mirror.
2. Pin signer digest (không chỉ cert subject) trong pipeline phát hành.
3. Coi khối chữ ký "parse được nhưng không verify được" là **input thù địch**.
4. Tái lập digest v2 trên đúng cửa sổ `[entries][CD][EOCD]`; đừng tin độ dài khai báo.
5. Log cả SPKI SHA-256, không chỉ hash tệp.

---

## 10. Bề mặt mạng và cấu trúc giao thức

### 10.1 Phân lớp host

| Nhóm | Số host | Ví dụ |
|---|---|---|
| first-party · ZaloPay | 45 | scdn.zalopay.com.vn, simg.zalopay.com.vn, zalopay.vn, api.zalopay.vn |
| unclassified | 58 | appx, appx-ng, developer.android.com, www.w3.org |
| third-party · Alipay | 27 | render.alipay.com, openauth.alipay.com, open-sea.alipayplus.com, render-intl.alipay.com |
| third-party · Google | 20 | play.google.com, www.google.com, firebase.google.com, googleads.g.doubleclick.net |
| third-party · Meta | 4 | developers.facebook.com, facebook.com, www.facebook.com, .facebook.com |
| third-party · Google Analytics | 1 | app-measurement.com |
| third-party · FPT Smart Cloud (VN eKYC) | 3 | api.fpt.ai, ekyc-dev.fpt.ai, web-ekyc-uat.fpt.ai |
| third-party · VNPAY | 3 | vnpay.vn, demomb.vnpaytest.vn, design.vnpay.vn |
| third-party · Marmot (Alibaba mini-program CDN) | 1 | cdn.marmot-cloud.com |
| third-party · TrueID (VN eKYC) | 2 | trueid.net, api.trueid.vn |
| third-party · Alibaba | 1 | activityservice.alibaba.com |
| third-party · ByteDance | 1 | analytics.us.tiktok.com |
| third-party · AppsFlyer | 1 | privacy-sandbox.appsflyersdk.com |
| third-party · Crashlytics | 1 | firebase-settings.crashlytics.com |

**168 host** phân thành bốn nhóm: first-party (45), hạ tầng Alibaba/Alipay (render, openauth, worker, CDN mini-app), Google (Play, Firebase, ads, app-measurement), và các nhà cung cấp Việt Nam (FPT Smart Cloud eKYC, TrueID, VNPAY) + Meta/ByteDance/AppsFlyer cho quảng cáo & attribution.

### 10.2 Hình dạng giao thức (không phải nội dung)

Từ 290 đường dẫn REST còn sống dưới dạng chuỗi, có thể mô tả *hình dạng* luồng xác thực:

- `GET /v2/user/public-key`, `GET /v2/user/salt` → phân phối khoá công khai + salt theo người dùng.
- `POST /v2/user/pin/pre-check-update-pin` → kiểm tra PIN phía server **trước khi** cập nhật.
- `POST /v2/account/otp`, `/v2/account/otp-verification`, `/v2/user/pin/pin-reset/otp` → OTP là yếu tố thứ hai cho đặt lại PIN.
- `POST /v2/ekyc/upload/trueid/face-liveness[/v3]`, `/v2/user-liveness/{create,submit,status}` → liveness theo phiên, có versioning.
- `POST /v2/cashier/{precheck-pay,pay,order/status,result}`, `/v3/cashier/state/set` → máy trạng thái thanh toán có precheck.

**Ranh giới bằng chứng:** tên endpoint cho biết *có* salt, *có* khoá công khai, *có* bước pre-check — nhưng **không** cho biết định dạng envelope, thuật toán trao đổi khoá, hay cấu trúc PIN block. Báo cáo dừng ở đây; mọi tuyên bố xa hơn sẽ là suy diễn không có bằng chứng.

### 10.3 Mini-app

| Danh mục | Số mini-app |
|---|---|
| Dịch vụ phổ biến | 88 |
| Giải trí - Cộng đồng | 77 |
| Tài chính | 65 |
| Ăn uống | 32 |
| Dịch vụ đối tác | 18 |
| Mua sắm | 15 |
| Giao thông - Vận chuyển | 14 |
| Thanh toán hoá đơn | 12 |
| Du lịch - Đi lại | 9 |
| Điện thoại | 8 |
| Khám phá Zalopay | 8 |
| Tích điểm thành viên đối tác | 7 |
| Chuyển - Nhận tiền | 6 |
| Ví của tôi | 5 |
| Bảo hiểm | 4 |
| Giáo dục | 2 |
| Ưu đãi khuyến mãi | 1 |
| Dịch vụ khác | 1 |
| Dịch vụ y tế | 1 |

`zalopay://launch/app/<id>` là deep link chuẩn cho mọi mini-app; 316/373 app trỏ `social.zalopay.vn`, 38 trỏ `mnap-director.zalopay.vn` (director/registry của mPaaS). Danh mục tiếng Việt: "Dịch vụ phổ biến" (88), "Giải trí – Cộng đồng" (77), "Tài chính" (65)…

---

## 11. Đánh giá quyền riêng tư (ánh xạ quyền → SDK)

| Quyền | SDK/bên nhận khả dĩ | Nhận xét |
|---|---|---|
| `AD_ID`, `ACCESS_ADSERVICES_*` | AdMob/GMA, Meta Audience Network, Pangle, AppsFlyer | 4 mạng quảng cáo + attribution trong một app ví |
| `READ_CONTACTS`, `WRITE_CONTACTS` | first-party (chuyển tiền P2P) | cần cho chọn người nhận; cũng là bề mặt dữ liệu nhạy cảm |
| `CAMERA`, `RECORD_AUDIO` | CameraX, ML Kit, TrueID liveness | eKYC + quét QR + video KYC |
| `ACCESS_FINE_LOCATION` | VNPAY taxi module, Maps | module gọi xe nhúng |
| `QUERY_ALL_PACKAGES` | Alibaba Security Guard, RootBeer, Play Integrity | liệt kê app để phát hiện môi trường rủi ro |
| `READ_PHONE_STATE`, `READ_BASIC_PHONE_STATE` | Security Guard (device fingerprint) | SIM/thiết bị làm tín hiệu chống gian lận |
| `DETECT_SCREEN_CAPTURE` | first-party | chống chụp màn hình màn hình nhạy cảm |
| `NFC` | HCE (`HostApduService`) | thanh toán chạm / nạp thẻ |

Nhận xét tổng: dữ liệu nhạy cảm **có** rời thiết bị (attribution + ads + eKYC cloud), nhưng phần lớn tín hiệu chống gian lận được xử lý trong native và gửi tới endpoint first-party. Đây là mô hình "thu tối đa tín hiệu, giảm thiểu chia sẻ" — nhưng không thể kiểm chứng tĩnh.

---

## 12. Kiến trúc tổng hợp (mô hình 4 miền tin cậy)

```
┌───────────────────────────── ZaloPay 11.17.0 ─────────────────────────────┐
│  Miền 1: Ví first-party (Kotlin, R8)                                      │
│    splash · cashier · transfer · loyalty · settings · widget              │
│    ↳ điều phối; gần như không giữ bí mật                                   │
├───────────────────────────────────────────────────────────────────────────┤
│  Miền 2: Runtime siêu ứng dụng (Alibaba mPaaS / ARiver / Griver)          │
│    373 mini-app từ JSON · render.alipay.com · appx worker · JSBridge      │
│    ↳ code tải động, ngoài phạm vi phân tích tĩnh của APK                  │
├───────────────────────────────────────────────────────────────────────────┤
│  Miền 3: eKYC & ML trên thiết bị (Google ML Kit + TrueID)                 │
│    22 model · liveness · OCR · face · barcode · NFC                       │
├───────────────────────────────────────────────────────────────────────────┤
│  Miền 4: Bảo mật native (Alibaba Security Guard + SQLCipher + BC)          │
│    libtaibmcf (12,0 MiB) · libfgcpkjvqe (6,2) · libsecurity_lite          │
│    ↳ nơi bí mật thực sự sống: ký request, chấm rủi ro, mã hoá CSDL        │
└───────────────────────────────────────────────────────────────────────────┘
                              ▲
                              │  container: 5 split, v2 block hỏng  ← điểm yếu
```

Hệ quả: bảo mật của app **không** nằm ở Kotlin (dễ đọc, dễ sửa) mà ở native + server. Kẻ tấn công có động cơ sẽ nhắm miền 4 và miền 2 (code tải động), trong khi nhà nghiên cứu chỉ có thể kiểm kê miền 1 và 3 một cách đầy đủ.

---

## 13. Phương pháp và tái lập

### 13.1 Pipeline

| Bước | Script | Đầu ra |
|---|---|---|
| 0 | `00_inventory.py` | `00_inventory.json` — băm, entry, căn lề |
| 1 | `01_manifest.py` | `01_manifest.json` — manifest, quyền, chứng chỉ |
| 2 | `02_dex.py` (`dex_parser.py`) | `02_dex.json` — lớp/phương thức/chuỗi/native method |
| 3 | `03_sdk.py` | `03_sdk.json` — 56 SDK, host, path |
| 4 | `04_native.py` | `04_native.json` — ELF, hardening, entropy |
| 5 | `05_resources.py` | `05_resources.json` — ARSC, model, asset bảo mật |
| 6 | `06_security.py` | `06_security.json` — khối chữ ký, phán quyết |
| 7 | `07_insights.py` | `07_insights.json` — obfuscation, mini-app, PIN surface |
| 8 | `08_film_data.py` | `film.json` — nguồn duy nhất cho phim |
| 9 | `video/` (TypeScript) | `artifacts/*.mp4`, `.srt`, `.vtt` |

### 13.2 Môi trường

- Python 3.11 + `pyelftools`, `cryptography`, `fonttools`, `imageio-ffmpeg` (FFmpeg 9.0.2, libx264)
- Node 22 + TypeScript 5, `@napi-rs/canvas` (Skia), `@resvg/resvg-js`
- Không dùng decompiler bên thứ ba (jadx/ghidra) cho số liệu chính: DEX reader tự viết để kiểm soát ngữ nghĩa offset.

### 13.3 Lệnh tái lập

```bash
python3 -m venv .venv && .venv/bin/pip install -r research/requirements.txt
.venv/bin/python research/scripts/00_inventory.py   # ... đến 08_film_data.py
npm ci && npm run render   # → artifacts/zalopay-11.17.0-anatomy.mp4
```

---

## 14. Giới hạn và mối đe doạ tới tính hợp lệ

1. **Tính đại diện:** một phiên bản (11.17.0) từ một mirror. Không suy rộng cho mọi phiên bản/ABI.
2. **ABI:** bundle chỉ có `armeabi_v7a`; kết luận native không áp dụng cho arm64/x86_64.
3. **Fingerprint SDK:** dựa trên chuỗi đặc trưng; một SDK có thể bị nhận nhầm nếu chuỗi trùng (đã ghi rõ "confidence" trong JSON).
4. **Framing khối chữ ký:** kết luận "không xác minh được" phản ánh *tệp này*. APK split do Play phục vụ (cùng version) rất có thể verify được — nhưng đó là tệp khác, không phải tệp đang phân tích.
5. **Không có ground truth động:** không chạy app, không quan sát traffic, nên mọi suy luận về giao thức là **cấu trúc**, không phải hành vi.
6. **Code tải động:** mini-app và JSBridge có thể thay đổi hành vi sau khi cài; phân tích tĩnh chỉ bao phủ phần nhúng.

---

## 15. Trích dẫn

```bibtex
@misc{zalopay2026anatomy,
  title        = {Static Anatomy of a Vietnamese Super-App: A Complete
                  Reverse-Engineering Atlas of the vn.com.vng.zalopay 11.17.0 XAPK,
                  and the Limits of Container-Level Integrity Proofs},
  author       = {ZaloPay 11.17.0 Static Anatomy Working Group},
  year         = {2026},
  month        = nov,
  note         = {Research film (4:36, 1080p30) + reproducible static-analysis pipeline},
  howpublished = {GitHub repository johnlikescarrot/-},
  url          = {https://github.com/johnlikescarrot/-}
}
```

**Artefact kèm theo:** `artifacts/zalopay-11.17.0-anatomy.mp4` (MP4/H.264 + AAC), `artifacts/zalopay-11.17.0-anatomy.srt`, `research/data/*.json`.

---

## Phụ lục A — bảng viết tắt

| Viết tắt | Nghĩa |
|---|---|
| XAPK | gói chia tách (base + config splits) dùng cho sideload |
| DEX | Dalvik Executable — định dạng bytecode của Android |
| R8 | trình shrink/obfuscate của Android Gradle Plugin |
| JNI | Java Native Interface |
| HCE | Host Card Emulation (NFC) |
| eKYC | electronic Know-Your-Customer |
| PQC | post-quantum cryptography |
| mPaaS/ARiver | nền tảng mini-app của Alibaba |
| RELRO/PIE/NX | thuộc tính gia cố ELF |

## Phụ lục B — toàn bộ 56 SDK phát hiện

| # | SDK | Vendor / loại | Bằng chứng (số chuỗi) |
|---|---|---|---|
| 1 | Firebase / Google Play services | Google · platform | 9,870 |
| 2 | Bouncy Castle | crypto · library | 9,448 |
| 3 | Alibaba mPaaS / ARiver (mini-app runtime) | super-app runtime | 3,836 |
| 4 | Visa SDK | card tokenization | 1,848 |
| 5 | ExoPlayer / Media3 | media | 1,827 |
| 6 | CameraX | media | 1,669 |
| 7 | Jetpack Compose | UI | 1,362 |
| 8 | TrueID eKYC | KYC · VN | 1,270 |
| 9 | VNPAY QR / tokenization | payments · VN | 700 |
| 10 | AppsFlyer | attribution | 685 |
| 11 | Kotlin stdlib | language | 597 |
| 12 | AndroidX core | platform | 555 |
| 13 | JWT (jjwt / nimbus) | crypto · tokens | 553 |
| 14 | OkHttp 4 | networking | 474 |
| 15 | Glide / Picasso | UI · imaging | 467 |
| 16 | Moshi / Gson | serialization | 401 |
| 17 | RxJava 2 | async | 399 |
| 18 | WorkManager | background | 382 |
| 19 | Protocol Buffers | serialization | 372 |
| 20 | Rive | UI · animation | 341 |
| 21 | Lottie | UI · animation | 331 |
| 22 | jsoup (HTML parsing) | data | 311 |
| 23 | Tink | crypto · library | 291 |
| 24 | Meta Audience Network | Meta · ads | 205 |
| 25 | DataStore (Preferences/Proto) | persistence | 154 |
| 26 | TikTok / Pangle ads | ByteDance · ads | 148 |
| 27 | Kotlin coroutines | async | 140 |
| 28 | Google Play Billing | Google · payments | 138 |
| 29 | Alipay SDK | payments · CN | 131 |
| 30 | Google Play Integrity | Google · anti-tamper | 118 |
| 31 | ML Kit (barcode, OCR, face, object) | Google · on-device ML | 117 |
| 32 | Crashlytics | observability | 115 |
| 33 | Dagger / Hilt | DI | 112 |
| 34 | AdMob / GMA | Google · ads | 93 |
| 35 | BiometricPrompt | identity · local auth | 73 |
| 36 | SQLCipher | persistence · encrypted | 72 |
| 37 | Huawei HMS / Push | push · OEM | 66 |
| 38 | Facebook Login / Graph | Meta · identity | 62 |
| 39 | Facebook App Events | Meta · analytics | 60 |
| 40 | Google Maps / Places | maps | 30 |
| 41 | Retrofit 2 | networking | 28 |
| 42 | Play Integrity + SafetyNet legacy | security | 24 |
| 43 | NFC / HCE host card emulation | payments · NFC | 21 |
| 44 | PaddlePaddle Lite / PicoDet (NCNN) | on-device ML | 17 |
| 45 | Zalo SDK (ZaloLogin / ZaloPay share) | identity · VN | 12 |
| 46 | Google Analytics 4 (Firebase) | product analytics | 10 |
| 47 | TensorFlow Lite | on-device ML | 9 |
| 48 | Navigation Compose | UI | 8 |
| 49 | Google Play In-App Update / Review | Google · lifecycle | 4 |
| 50 | Accompanist / Coil | UI · imaging | 4 |
| 51 | Conscrypt | crypto · TLS | 3 |
| 52 | NCNN | on-device ML | 3 |
| 53 | FIDO / WebAuthn / passkeys | identity · standard | 3 |
| 54 | Apache Commons | utility | 3 |
| 55 | Room | persistence | 2 |
| 56 | TrueFace liveness | KYC · liveness | 2 |

## Phụ lục C — host first-party

| Host | Hits |
|---|---|
| scdn.zalopay.com.vn | 65 |
| simg.zalopay.com.vn | 45 |
| zalopay.vn | 20 |
| api.zalopay.vn | 15 |
| social.zalopay.vn | 13 |
| sapi.zalopay.vn | 10 |
| mnap-director.zalopay.vn | 8 |
| zalo.me | 5 |
| upload-img.zalopay.vn | 5 |
| openapi.zalopay.vn | 5 |
| uudai.zalopay.vn | 5 |
| zalopay.com.vn | 4 |
| bim.zalopay.vn | 4 |
| dala2zoom.zalopay.vn | 3 |
| user-life-cycle-public.zalopay.vn | 3 |
| bank-profile.zalopay.vn | 3 |
| mmf-redemption.zalopay.vn | 3 |
| scard.zalopay.vn | 3 |
| support.zalopay.vn | 3 |
| zlp-ofp-emvco-gateway.zalopay.vn | 3 |
| zlp-ofp-zpa-camera-config-be.zalopay.vn | 3 |
| cps.zalopay.com.vn | 3 |
| mmf-user.zalopay.vn | 3 |
| tracking.zalopay.vn | 3 |
| rdx.zalopay.vn | 2 |
| upload.zalopay.com.vn | 2 |
| log.zalopay.com.vn | 2 |
| zpm.zalopay.com.vn | 2 |
| token.zalopay.vn | 2 |
| partners.zalopay.vn | 2 |
| zlp-ofp-integration-hub.zalopay.vn | 2 |
| campaign.zalopay.com.vn | 2 |
| loyalty.zalopay.vn | 2 |
| mp.zalopay.vn | 2 |
| onelink.zalopay.vn | 2 |
| miniprogram.zalopay.vn | 2 |
| zlp-ofp-qp-public-api.zalopay.vn | 2 |
| rar-um.zalopay.vn | 2 |
| fin-installment.zalopay.vn | 2 |
| bnpl-gateway.zalopay.vn | 2 |
| sse.zalopay.vn | 2 |
| atlas.zalopay.vn | 1 |
| html.zapps.vn | 1 |
| qrpay.zalopay.vn | 1 |
| zlp-movie-api.zalopay.vn | 1 |
