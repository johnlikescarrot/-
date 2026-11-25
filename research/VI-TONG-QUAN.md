# Tổng quan tiếng Việt — ZaloPay 11.17.0

Tài liệu này là bản tóm lược đầy đủ bằng tiếng Việt của toàn bộ nghiên cứu. Bản báo cáo học
thuật chi tiết nằm trong các tệp `research/0*.md` (tiếng Anh, để trích dẫn quốc tế).

## 1. Đối tượng và phạm vi

Chúng tôi phân tích tĩnh **toàn bộ** gói cài đặt ZaloPay 11.17.0 mà người dùng tải từ APKPure:
tệp ZIP → XAPK → 5 APK (1 base + 4 split) → 19 tệp DEX, `resources.arsc`, 124 asset, 27 thư viện
native, và toàn bộ khối chữ ký.

| thông số | giá trị |
|---|---|
| package | `vn.com.vng.zalopay` |
| phiên bản | 11.17.0 (versionCode 1102758) |
| minSdk / targetSdk | 26 / 36 |
| dung lượng XAPK | 105 608 128 B (≈105,6 MB) |
| SHA-256 XAPK | `686e9459da646c18b53a67d57e5a23c49466da140ec1e3e9682032f453ea7b8d` |
| ABI duy nhất | `armeabi-v7a` (32-bit ARM) |
| ngôn ngữ | `vi`, `en` (2 split ngôn ngữ) |

## 2. Chữ ký và nguồn gốc

* Base APK dùng **APK Signature Scheme v2 + v3** (RSA-2048 / SHA-256), **không có chữ ký JAR v1**
  — hợp lý vì minSdk 26.
* Chứng thư ký: `CN=Vu Manh Hieu`, tự ký, hiệu lực 2016-04-21 → 2041-04-15,
  vân tay SHA-256 `59dd31fd…92e8cdae`.
* Có **Google Play source stamp** (chứng thư `CN=Android, O=Google Inc.`) → base APK đã từng được
  phát hành qua Google Play trước khi bị mirror. Đây là bằng chứng nguồn gốc mạnh hơn mọi so sánh hash.
* Có **một block không tài liệu** `0x2146444e` (691 B) nằm trong vùng đã ký — báo cáo ghi nhận
  trung thực là "chưa nhận dạng", không suy diễn thành mã độc.

## 3. Mã nguồn: 60 671 class, 98,4 % là của bên thứ ba

| chỉ số | giá trị |
|---|---:|
| tệp DEX | 19 (format 038) |
| class | 60 671 |
| method | 351 248 |
| chuỗi duy nhất | 243 782 |
| class thuộc `vn.com.vng.zalopay` | **952 (1,57 %)** |
| class thuộc `vn.*` (gồm 2 SDK eKYC) | 1 936 (3,2 %) |
| chỉ số tập trung HHI | 0,036 (phân tán tối đa) |

**Kết luận trung tâm:** với một ví điện tử hiện đại, "bề mặt tấn công" thực chất là **đồ thị phụ
thuộc**, không phải mã của chính chủ. Đọc 952 class đầu tiên chỉ bao phủ 1,6 % binary.

* Obfuscation: **52 713 class** có `SourceFile` bị R8 đổi thành chuỗi `"SourceFile"`;
  32 449 class có tên 1 ký tự; chỉ 15 class giữ tên tệp gốc (đều từ thư viện `apksig` của Google).
* Ba DEX đã chạm ngưỡng tự đặt 60 000 method — R8 chia DEX theo watermark, không theo tính năng.

## 4. Manifest: 265 component, 28 điểm vào reachable

* **200 activity**, 34 service, 22 receiver, 9 provider; **28 component exported**.
* 9 deep link: `https://go.zalopay.vn` (autoVerify), `onelink.zalopay.vn`,
  `kmzalopay.onelink.me/TSui`, `km.zalopay.vn/TSui`, `zalopay://app`, `zalopay-vnpay:`.
* **4 component test/debug bị export trong bản production**: 3 ×
  `androidx.test…InstrumentationActivityInvoker` và `androidx.compose.ui.tooling.PreviewActivity`
  (+ `TagManagerPreviewActivity`). Không rò rỉ dữ liệu, nhưng chứng minh classpath test lọt vào
  bản release — cùng loại lỗi cấu hình có thể mang theo debug menu, logging interceptor.
* **2 activity nhận dữ liệu không tin cậy**: `QRReceiverActivity` (`ACTION_SEND` + `image/*`) và
  `IdentityCardCaptureActivity` (action riêng, không permission). Đây là nơi ảnh lạ đi vào pipeline
  ML/OCR → cần kiểm tra kích thước ảnh, schema QR, và không tin caller.
* HCE: `VisaHostApduService` khai báo AID PPSE + Visa (`A0000000031010`, `A0000000032020`, …),
  `requireDeviceUnlock="false"` — chấp nhận được cho tap-to-pay nhưng đồng nghĩa kiểm soát phải
  nằm ở state machine giao dịch và rule rủi ro của token.
* 49 permission, trong đó đáng chú ý: `QUERY_ALL_PACKAGES`, `DETECT_SCREEN_CAPTURE`,
  `READ/WRITE_CONTACTS`, `READ/WRITE_CALENDAR`, `ACCESS_FINE_LOCATION`, 7 quyền quảng cáo/attribution.

## 5. Native: 27 thư viện, 57,5 MB, hardening tốt

| kiểm soát | kết quả |
|---|---|
| PIE | 27/27 |
| NX stack | 27/27 |
| BIND_NOW (full RELRO) | 27/27 |
| stripped | 26/27 |
| TEXTREL | 0/27 |
| JNI export (`Java_*`) | 284 ký hiệu trên 10 thư viện |

Điểm đáng chú ý:

1. **Lớp chống dịch ngược là native-only**: `assets/bshield.dat` (929 B) + `libtaibmcf.so`
   (12,6 MB) + các lib tên ngẫu nhiên (`libfgcpkjvqe.so`, `libohxbee.so`, `libljsfrcwmw.so`, …).
   `libtaibmcf.so` chỉ export `JNI_OnLoad`/`JNI_OnUnload` và import `AAssetManager_*`, `inflateInit2_`,
   `mprotect`, `dl_iterate_phdr` — đúng chữ ký của một loader giải nén payload trong bộ nhớ.
2. **Rò rỉ đường dẫn build**: 67 đường dẫn trong `libtaibmcf.so`
   (`/home/cpu12313/Documents/BshieldAzure/agent/_work/1/s/libs/boringssl/…`) và 67 trong
   `libfgcpkjvqe.so` (`/home/vsts/work/1/s/…`). Cùng một cây BoringSSL, **hai CI agent khác nhau** →
   giải thích vì sao hai lib cùng kích thước 178 628 B lại khác nhau 8 799 byte: cùng mã, build
   khác, tên ngẫu nhiên khác. Đây là dấu vết xác nhận sản phẩm **Bangcle B-Shield**.
3. **eKYC TrueID** (`libtrueidlib.so`) mang vault riêng: `SmartOTPHelper_vaultCheckIntegrity`,
   `vaultX25519Pubkey`, `vaultVerifyTxDigest`, và danh sách đường dẫn Magisk (`/data/adb/magisk`,
   `/sbin/.magisk`). Đây là SDK "làm bảo mật nghiêm túc" — nhưng danh sách chống root kiểu 2023
   đã giảm hiệu lực trước Magisk DenyList/Zygisk.
4. `libsecurity_lite.so` (Alipay SecurityGuard Lite) export `signRequest`/`encrypt`/`decrypt` →
   **ký request nằm ở native**, không thể xác minh chỉ bằng đọc Java.
5. 16 KB page-size: các `.so` nằm trong split dưới dạng **nén deflate** và `extractNativeLibs="true"`,
   nên bài kiểm tra "căn lề 16 KB cho entry không nén" **không áp dụng** — nhiều báo cáo tự động
   sẽ cho kết quả dương tính giả ở đây.

## 6. TLS, crypto, integrity

* `network_security_config.xml`: **`cleartextTrafficPermitted="true"` cho toàn bộ app**
  (`base-config`), `debug-overrides` tin CA người dùng (vô hại vì app không debuggable).
  DEX còn **18 chuỗi `http://` thực** (đã loại nhiễu XML-schema), trong đó có host sandbox
  đối tác `http://iopengw-280.sggz00b.dev.alipay.net/` → lọt cấu hình dev vào bản release.
* **Certificate pinning có tồn tại**: 15 `CertificatePinner`, 8 literal `sha256/`, 2 `sha1/`.
  Nhưng pinning nằm trong code OkHttp, **không** trong `<pin-set>` của network-security-config →
  WebView, Griver, Alipay, VNPAY, AppsFlyer… đều đi vòng qua.
* `SafetyNet` xuất hiện 50 lần so với `PlayIntegrity` 2 lần — di trú chưa xong; SafetyNet đã bị khai tử.
* Keystore: có `StrongBox`, `setUserAuthenticationRequired` nhưng mỏng (1–3 hit) → khoá gắn sinh trắc
  học chưa phổ quát.
* 17 model ML on-device (12,9 MB): OCR, barcode, face, object detection + `haarcascade` cổ điển.
  Suy luận tại thiết bị là lựa chọn **tốt cho quyền riêng tư**; đổi lại model trở thành bề mặt tấn công
  (ảnh dị dạng → DoS bộ giải mã TFLite), và `IdentityCardCaptureActivity` đang export.

## 7. Điểm tư thế bảo mật: 83,2/100

| chiều | trọng số | điểm |
|---|---:|---:|
| chữ ký & nguồn gốc | 0,14 | 100 |
| hardening native | 0,16 | 99,6 |
| bảo mật đường truyền | 0,16 | 90 |
| toàn vẹn thiết bị | 0,14 | 95 |
| chống can thiệp runtime | 0,10 | 100 |
| tối thiểu hoá dữ liệu | 0,14 | 55 |
| vệ sinh mã nguồn | 0,10 | 45 |
| chuỗi cung ứng | 0,06 | 56,2 |

## 8. Khuyến nghị ưu tiên

1. **P0**: đưa `androidx.test`, Compose tooling, GTM preview về `debugImplementation`; diff
   manifest merge trong CI; chặn endpoint `*.dev.*`/`http://` bằng lint.
2. **P1**: `cleartextTrafficPermitted="false"` + allow-list tạm thời; chuyển pin vào `<pin-set>`;
   hoàn tất Play Integrity, xoá SafetyNet.
3. **P2**: thay quyền contacts/calendar bằng picker scoped; thu hẹp `FileProvider` (`cache-path "/"`);
   rà soát `QUERY_ALL_PACKAGES`.
4. **P3**: SBOM (CycloneDX) cho mỗi bản phát hành; `-ffile-prefix-map` để xoá đường dẫn build.

## 9. Giới hạn của nghiên cứu

Đây là nghiên cứu **tĩnh**. Chúng tôi chứng minh *khả năng tồn tại* (capability), không chứng minh
*luồng thực thi*. Các bước tiếp theo được nêu cụ thể: call graph trên 952 class first-party, trace
`CertificatePinner` bằng Frida, fuzz 28 component exported bằng `adb shell am start` trên emulator
ARMv7, và dump vùng nhớ `JNI_OnLoad` của shield dưới QEMU.
