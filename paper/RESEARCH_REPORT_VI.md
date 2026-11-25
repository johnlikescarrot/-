# Giải phẫu một Siêu ứng dụng Tài chính Tứ-lai (Quad-Hybrid FinTech Super-App): Nghiên cứu Dịch ngược Tĩnh Toàn diện và Đo lường Tăng cường Bảo mật Nhị phân trên ZaloPay 11.17.0

**Chuyên khảo Kỹ thuật & Nghiên cứu Thực nghiệm Hiện vật**  
**Mục tiêu nghiên cứu:** `Zalopay-Thanh+toán+&+Tài+chính_11.17.0_APKPure.zip` (`vn.com.vng.zalopay`, `versionCode=1102758`, `versionName=11.17.0`)  
**Chuỗi công cụ tái lập:** `morluto/rea` (`rea-agents@6.10.1` / `rea@6.7.0` CLI & MCP), Androguard `4.1.4`, LIEF `1.0.0`, `pyelftools 0.33`, `cryptography 50.0.2`, GNU `objdump`  

---

## Tóm tắt (Abstract)

Các ứng dụng thanh toán và tài chính di động ("FinTech") hiện đại tại các nền kinh tế số mới nổi đã tiến hóa từ ứng dụng thuần native đơn lẻ thành các **siêu ứng dụng đa môi trường thực thi (multi-runtime super-apps)**, tích hợp đồng thời giao diện Android native, khung JavaScript đa nền tảng, bộ chứa mini-program nhúng, mô hình học sâu trên thiết bị và các lớp tự bảo vệ thời gian chạy (RASP). Tuy nhiên, các phép đo tĩnh thực nghiệm bao phủ **100% toàn bộ tệp tin** bên trong một gói phát hành FinTech thực tế vẫn còn rất khan hiếm trong tài liệu học thuật.

Trong nghiên cứu này, chúng tôi thực hiện kiểm toán dịch ngược tĩnh toàn diện, phi xâm nhập và tuân thủ đạo đức học thuật đối với **toàn bộ 6.824 hiện vật tệp tin** (`195.327.309` byte chưa nén trong 5 APK phân tách cộng thêm `360` tệp lồng trong container React Native và mini-program) nằm trong `Zalopay-Thanh+toán+&+Tài+chính_11.17.0_APKPure.zip` (`targetSdkVersion=36`, Android 16). Kết hợp đồ thị hiện vật xác thực bằng SHA-256 của `morluto/rea` với phân tích mã byte Dalvik, bảng tài nguyên AXML/ARSC và tệp nhị phân ELF, chúng tôi rút ra bốn phát hiện cốt lõi:

1. **Hội tụ Kiến trúc Tứ-lai (Quad-Hybrid Convergence):** Ứng dụng hợp nhất (i) Kotlin/Java native + Jetpack Compose `1.7.2` trải trên **20 tệp DEX** (`66,47 MiB`, `63.827` lớp, `378.040` phương thức), (ii) gói **React Native Metro** (`3,61 MiB`, `2.346` định nghĩa mô-đun `__d(...)`) trong `assets/android.1.zip`, (iii) nền tảng **Mini-Program Ant Group / Alipay+ Griver** (`appx` & `appx-ng` qua 3 gói `.amr`/`.tar`) điều phối **373 mini-app** trong **5 tiến trình cô lập (`:lite1`–`:lite5`)**, và (iv) **27 thư viện ELF ARMv7 `.so`** (`54,79 MiB`).
2. **Phân mảnh Trình biên dịch qua 6 Thế hệ Android NDK Clang:** Phân tích phần `.comment` của các tệp ELF cho thấy **6 thế hệ Clang khác nhau** cùng tồn tại trong `config.armeabi_v7a.apk` — từ **Clang 7.0.2** (`libsecurity_lite.so`) qua **Clang 14.0.x**, **Clang 17.0.2**, **Clang 18.0.x** tới **Clang 21.0.0** (`libfgcpkjvqe.so`, `libpicodet_ncnn.so`).
3. **Độ phủ Bảo vệ Nhị phân Không đồng nhất ($H_{\text{bin}}$):** Trong khi **100% (`27/27`)** thư viện ELF bật **PIE (`ET_DYN`)** và **NX (`PT_GNU_STACK`)**, tỷ lệ **Full RELRO** đạt **85,2% (`23/27`)** — thiếu hụt tập trung hoàn toàn ở cả 4 thư viện Firebase Crashlytics — **Stack Canary** đạt **92,6% (`25/27`)**, **`FORTIFY_SOURCE`** đạt **55,6% (`15/27`)**, và duy nhất `libcrashlytics-trampoline.so` vẫn giữ nguyên bảng ký hiệu `.symtab`.
4. **Hệ sinh thái Edge-AI & eKYC Quy mô lớn:** **51 hiện vật mô hình học máy (`18,83 MiB`)** kết hợp cùng **`26,28 MiB` thư viện `.so` thị giác máy tính** (~45 MiB tổng cộng) chạy hoàn toàn ngoại tuyến qua Google ML Kit, Tencent NCNN, OpenCV LBF (`lbfmodel.yaml`), ONNX/VFace và TFLite để phục vụ định danh CCCD gắn chip (NFC + OCR) và kiểm tra thực thể sống (liveness).

---

## 1. Phạm vi Đạo đức và Phương pháp luận Tái lập

Nghiên cứu tuân thủ nghiêm ngặt chuẩn mực công bố học thuật quốc tế (IEEE S&P / USENIX Security): chỉ đo lường cấu trúc, chữ ký mã hóa, phân bổ SDK và cơ chế phòng vệ trên hiện vật tĩnh (`authority: shipped-artifact`); tuyệt đối không tìm kiếm lỗ hổng để khai thác, không tạo mã vượt cơ chế bảo vệ và không trích xuất khóa bí mật.

Chuỗi tự động hóa trong `scripts/01`–`05` kiểm tra 100% tập tin ở mọi tầng đóng gói:
1. `scripts/01_rea_inspect_all.mjs`: Thực thi `morluto/rea` (CLI & MCP) để lập đồ thị băm SHA-256 (`inspect-artifact`), kiểm tra Flutter AOT, phân tích AST/ngữ nghĩa JavaScript (`analyze_javascript_application`), kiểm tra nhị phân Rust/Go và `objdump`.
2. `scripts/02_complete_apk_inventory.py`: Quét toàn bộ 6.464 mục APK và 360 tệp lồng, tính entropy Shannon, chữ ký magic, phát hiện trùng lặp SHA-256, và giải mã APK Signing Block v2/v3 + chứng thư số v1.
3. `scripts/03_manifest_and_dex_analysis.py`: Giải mã toàn bộ 5 `AndroidManifest.xml`, 4 bảng `resources.arsc`, và 20 tệp `.dex` qua Androguard.
4. `scripts/04_native_elf_audit.py`: Kiểm toán bảo mật nhị phân trên 100% (27/27) tệp `.so` bằng LIEF và `pyelftools`.
5. `scripts/05_assets_configs_and_master.py`: Tổng hợp phiên bản AndroidX/Compose, mô hình AI, cấu trúc hybrid và tạo bộ dữ liệu hợp nhất `data/master_metrics.json`.

---

## 2. Cấu trúc Container XAPK, Phân bổ Dung lượng và Chữ ký Số

Gói gốc `Zalopay-Thanh+toán+&+Tài+chính_11.17.0_APKPure.zip` (`98.813.878 B`) chứa tệp XAPK v2 (`105.608.128 B`), giải nén thành **5 APK phân tách** (`6.464` mục tệp, `195.327.309 B` = `186,28 MiB` chưa nén):
- `vn.com.vng.zalopay.apk` (`75,68 MiB` nén, `6.162` mục, đồ thị REA `5e4ccc029c664734`): Mã Dalvik, tài nguyên, assets và mô hình AI.
- `config.armeabi_v7a.apk` (`23,68 MiB` nén, `32` mục, đồ thị REA `1e9a6706fd8c2651`): Toàn bộ 27 thư viện `.so` ARMv7.
- `config.xhdpi.apk` (`1,18 MiB`, `258` mục), `config.vi.apk` (`86,4 KB`, `6` mục), `config.en.apk` (`74,1 KB`, `6` mục).

**Phân bổ dung lượng chưa nén (`186,28 MiB`):**
- Mã byte Dalvik (`.dex`): `66,47 MiB` (**35,68%**, 20 tệp)
- Thư viện native ELF (`.so`): `54,79 MiB` (**29,41%**, 27 tệp)
- Manifest & Tài nguyên biên dịch: `39,12 MiB` (**21,00%**, 5.865 tệp)
- Mô hình AI & Thị giác máy tính: `15,23 MiB` (**8,17%** ở nhóm AI thuần; `18,83 MiB` / 51 tệp tính cả `lbfmodel.yaml`)
- Lớp Hybrid JS / React Native / Griver: `4,95 MiB` (**2,66%**, giải nén thành 360 tệp con)

**Phát hiện Trùng lặp Nội dung:** Trong số 6.464 tệp, có 5.968 mã SHA-256 duy nhất và **299 nhóm tệp trùng lặp** gây lãng phí **`959.564` byte (~937 KiB)** (điển hình: `UnisectVnu.ttf` trùng hoàn toàn với `ocr-a-tribute.ttf` `48.216 B`, tệp bản địa hóa BouncyCastle `49.608 B`, và các ảnh/Lottie hướng dẫn eKYC trùng lặp).

**Chữ ký mật mã:** Cả 5 APK đều có khối `APK Sig Block 42` căn lề `8.192 B` chứa **APK Signature Scheme v2, v3, Source Stamp v1, Verity Padding** (riêng base APK có thêm khối `0x2146444e` `NDF!`), với chứng thư v1 trên các split APK do `CN=Vu Manh Hieu` (RSA 2048-bit, SHA-256 `59dd31fd...2e8cdae`) ký hợp lệ đến năm 2041.

---

## 3. Bề mặt Manifest, Quyền hạn và Cô lập Tiến trình

- **Mức SDK:** `minSdkVersion=26` (Android 8.0), `targetSdkVersion=36` & `compileSdkVersion=36` (Android 16).
- **Thành phần ứng dụng (267 thành phần):** 200 Activities (14 exported), 2 Activity-Aliases, 34 Services (6 exported), 22 BroadcastReceivers (8 exported), 9 ContentProviders (0 exported).
- **Cô lập Đa tiến trình (7 tiến trình riêng):** `:lite1`–`:lite5` (5 sandbox chạy mini-app), `:taibmcf` (tiến trình bảo mật gắn với `libtaibmcf.so`), và `:xcmzqsawpqoefcds`.
- **49 Quyền hệ thống:** 14 quyền nguy hiểm (Camera, Danh bạ, Vị trí, Ghi âm, Bộ nhớ, Lịch, Điện thoại), 10 quyền thường, và 25 quyền nhạy cảm/OEM bao gồm `DETECT_SCREEN_CAPTURE`, `NFC`, `USE_BIOMETRIC`, `QUERY_ALL_PACKAGES`, và `ACCESS_THIRD_PARTY_APP_AUTHORIZATION` (Zalo SSO).
- **Cấu hình mạng (`network_security_config.xml`):** Cho phép `cleartextTrafficPermitted="true"` tại `<base-config>` (một điểm đáng lưu ý về kế thừa tương thích so với khuyến nghị HTTPS nghiêm ngặt cho FinTech).

---

## 4. Giải phẫu 20 Tệp DEX & Hệ sinh thái SDK

Tổng cộng 20 tệp `.dex` (`classes.dex`–`classes19.dex` và `assets/audience_network.dex` tải động) chứa **`63.827` lớp**, **`378.040` phương thức**, **`317.841` trường** và **`404.914` chuỗi**.
- Ngưỡng chia DEX `60.000` phương thức thể hiện rõ tại `classes.dex` (`59.948`), `classes4.dex` (`59.997`) và `classes7.dex` (`60.000`).
- **Tỷ lệ làm rối R8/ProGuard:** **69,64%** (`44.452 / 63.827` lớp) mang tên rút gọn 1–2 ký tự.
- **Phân bổ SDK chính:** R8/ProGuard rút gọn (`42,27%`), Google/Firebase/MLKit (`12,32%`), Thanh toán/Mật mã/eKYC như BouncyCastle/Visa CBP/SQLCipher (`12,29%`), Ant Group/Alipay+/Griver (`10,83%`), AndroidX/Jetpack/Kotlin gồm Compose `1.7.2` & CameraX `1.4.0` (`7,32%`), React Native/Meta (`5,57%`), ZaloPay first-party không làm rối (`1,78%`).

---

## 5. Kiểm toán 27 Thư viện Native ELF (`.so`)

Toàn bộ 27 tệp `.so` (`54,79 MiB`) thuộc kiến trúc ARM 32-bit (`ET_DYN`), không chứa nhị phân Go hay Rust `cargo-auditable`:
- **6 thế hệ NDK Clang song hành:** Từ **Clang 7.0.2** (`libsecurity_lite.so`) tới **Clang 14.0.6/14.0.7**, **Clang 17.0.2** (`libtaibmcf.so` `12,03 MiB`), **Clang 18.0.x** (`librive-android.so` `167` hàm JNI, `libVFaceLib.so`), và **Clang 21.0.0** (`libfgcpkjvqe.so` `6,22 MiB`, `libpicodet_ncnn.so` `2,85 MiB`).
- **Tỷ lệ phòng vệ nhị phân:** PIE **100% (27/27)**, NX **100% (27/27)**, Full RELRO **85,2% (23/27)** (thiếu ở 4 tệp Crashlytics), Stack Canary **92,6% (25/27)**, `FORTIFY_SOURCE` **55,6% (15/27)**, Stripped `.symtab` **96,3% (26/27)**.
- **Liên kết chuỗi `DT_NEEDED` đáng chú ý:** `libljsfrcwmw.so` liên kết tới `librcmefqonnx.so` (`178.628 B`), còn `libohxbee.so` liên kết tới `liblytcuhe.so` (cũng đúng `178.628 B`, biên dịch bằng Clang 14.0.6).

---

## 6. Nền tảng Hybrid & Trí tuệ Nhân tạo Trên thiết bị

- **React Native (`assets/android.1.zip`):** Gói `main.jsbundle` (`3,61 MiB`, `2.346` mô-đun `__d`) phục vụ các luồng nghiệp vụ như `notifications`, `transactionhistoryv2`, `clearingaccount`, `registeronlinebanking`.
- **Ant Group Griver (`assets/Griver/*.amr`):** 3 gói `.amr` chứa nhân `appx` & `appx-ng` (`68687209` `3,29 MiB`) điều khiển **373 mini-app** trong `miniapp_config.json`.
- **Edge AI (`18,83 MiB` mô hình):** Hợp nhất OpenCV LBF (`lbfmodel.yaml` `3,60 MiB`), TFLite/BlazeFace/FSSD/EMD nhận diện khuôn mặt & chớp mắt chống giả mạo, Tencent NCNN (`FastestDet`), và Google ML Kit OCR/Barcode.

---

## 7. Hạn chế và Khả năng Tái lập

Nghiên cứu giới hạn trên bản phân tách `armeabi-v7a` và phân tích tĩnh không thực thi mã. Toàn bộ mã nguồn kiểm toán (`scripts/`), dữ liệu JSON (`data/`), hệ thống dựng video TypeScript (`video/`) và tệp trích dẫn (`CITATION.cff`, `paper/references.bib`) đều được đồng bộ đầy đủ để cộng đồng học thuật tái lập.
