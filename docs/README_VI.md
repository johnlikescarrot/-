# Tóm Tắt Nghiên Cứu Dịch Ngược Chuyên Sâu: ZaloPay v11.17.0 (XAPK)

Tài liệu này tóm tắt bằng tiếng Việt các kết quả nghiên cứu tĩnh toàn diện đối với toàn bộ gói **`Zalopay-Thanh+toán+&+Tài+chính_11.17.0_APKPure.zip`**, kết hợp nền tảng phân tích [REA (`morluto/rea` v6.10.1)](https://github.com/morluto/rea) và bộ công cụ Python chuyên sâu (`Androguard`, `LIEF`, `PyELFtools`, `Capstone`, `ASN1Crypto`).

> **Báo cáo học thuật đầy đủ (tiếng Anh, chuẩn trích dẫn Google Scholar):** [`docs/ZALOPAY_11.17.0_REVERSE_ENGINEERING_PAPER.md`](./ZALOPAY_11.17.0_REVERSE_ENGINEERING_PAPER.md) và [`docs/references.bib`](./references.bib).

---

## Những Phát Hiện Cốt Lõi (100% Từ Bằng Chứng Tĩnh Thực Tế)

1. **Cấu trúc đóng gói 7 thành phần & Ký số chuẩn Google Play (`v3 + SourceStamp`):**
   - Tệp ZIP (`98.81 MB`) chứa XAPK v2 (`105.61 MB`) gồm **5 split APKs** (`vn.com.vng.zalopay.apk`, `config.armeabi_v7a.apk`, `config.xhdpi.apk`, `config.vi.apk`, `config.en.apk`), `icon.png` (`512×512` RGBA) và `manifest.json` (`targetSdk=36` / Android 16, `minSdk=26`).
   - Base APK loại bỏ ký v1 JAR cũ, dùng **APK Signature Scheme v2 + v3 + Google Play SourceStamp**; cả 5 APK dùng chung chứng chỉ `CN=Vu Manh Hieu` (`SHA-256: 59dd31fd...`).

2. **Kiến trúc Siêu Ứng Dụng 3 Engine Lai (Tri-Engine Hybrid):**
   - **Native Kotlin 2.2.10 + Jetpack Compose 1.7.2:** `19` tệp DEX chính (`64.84 MB`, `60,671` lớp, `264,525` phương thức, **`8,711,522` lệnh Dalvik**) + `1` DEX động (`assets/audience_network.dex`, `4.86 MB`, `420,370` lệnh).
   - **React Native Metro Bundle (`assets/android.1.zip`):** Chứa `main.jsbundle` (`3.61 MB`, **`2,346` module `__d()`**) cho lịch sử giao dịch, thanh toán bù trừ, liên kết ngân hàng.
   - **Alipay+ / Ant Group Griver Mini-Program:** `5,136` lớp `ariver/griver/alipay`, `5` tiến trình sandbox `:lite1`..`:lite5`, cùng 3 gói `.amr` (`TAR + CERT.json + Manifest.xml`) và cầu nối JS (`5,361` nút AST được REA phân tích).

3. **Hệ sinh thái AI tại biên (Edge AI), eKYC Sinh trắc học & Thanh toán NFC HCE:**
   - 4 engine thị giác máy tính: **Tencent NCNN** (`FastestDet` 172 tầng, `libpicodet_ncnn.so`), **TensorFlow Lite** (`object_labeler.tflite`), **Google ML Kit** (`libmlkitcommonpipeline.so`, `libmlkit_google_ocr_pipeline.so`, `libface_detector_v2_jni.so`, `libbarhopper_v3.so`) và **OpenCV** (`haarcascade_frontalface_default.xml`, `libVFaceLib.so`).
   - **Thanh toán không tiếp xúc & SmartOTP:** EMVCo HCE (`2PAY.SYS.DDF01` và các Visa AID `A000000003*`) + `assets/com/visa/cbp/*` + trao đổi khóa **X25519 SmartOTP** trong `libtrueidlib.so`.

4. **Phòng thủ Nhị phân Đa tầng & Hiện tượng "Cặp Song Sinh Runtime C++" (`27` thư viện `.so`, `57.45 MB`):**
   - R8 làm rối **`68.19%`** tên lớp (`≤2` ký tự) và **`48.14%`** gói gốc.
   - Tầng native chứa các module bảo vệ tên ngẫu nhiên quy mô lớn: `libtaibmcf.so` (`12.62 MB`, `132` hàm khởi tạo `.init_array`, khớp tiến trình `:taibmcf` & `assets/taibmcf.dat`), `libfgcpkjvqe.so` (`6.52 MB`, `36` constructors, đi kèm `assets/fgcpkjvqe.dat` entropy **`7.9998`**), `liba97d.so`, `libljsfrcwmw.so`, `libohxbee.so`.
   - Phát hiện đặc biệt: `liblytcuhe.so` và `librcmefqonnx.so` có kích thước giống hệt nhau (`178,628` byte, chỉ lệch `8,799` byte) đóng vai trò **hai bản sao C++ runtime cô lập** tách biệt hoàn toàn khỏi `libc++_shared.so`.
   - **Kiểm định bảo mật ELF (REA):** `27/27` bật `NX`, `25/27` bật Stack Canary, `23/27` bật `RELRO` — **duy nhất 4 thư viện Firebase Crashlytics (`libcrashlytics*.so`) tắt `RELRO`**.
   - **Điểm cần lưu ý ở cấu hình mạng:** `network_security_config.xml` đặt `<base-config cleartextTrafficPermitted="true">`, do đó toàn bộ cơ chế TLS pinning dựa vào mã thực thi (OkHttp `CertificatePinner`, `TrueIDSSLPinningConfig`, tầng native).
