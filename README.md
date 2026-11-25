# Dịch ngược và báo cáo kỹ thuật — ZaloPay 11.17.0 (APKPure)

Dự án thực hiện **nghiên cứu và dịch ngược toàn diện** tập tin `Zalopay-Thanh+toán+&+Tài+chính_11.17.0_APKPure.zip` (định dạng XAPK của APKPure chứa base APK `vn.com.vng.zalopay.apk` plus các split APK), sau đó xây dựng một **video trình bày kỹ thuật (MP4)** hoàn toàn bằng TypeScript/Node.js, xuất định dạng MP4.

## Cấu trúc
- `reverse-engineering/` — kết quả dịch ngược: `REPORT.md`, `analysis.json`, `apk_inventory.json`, `endpoints.json` và script `analyze_final.py`.
- `video/` — dự án TypeScript tạo video: `src/` (canvas, font bitmap, encoder PNG, sinh âm thanh, slide, chính lệnh), `ffmpeg-run.cjs` (trình bao ffmpeg.wasm 0.11 chạy trong Node, không cần ffmpeg hệ thống), `dist/` (biên dịch).
- `output/zalopay-re.mp4` — video MP4 xuất ra (khoảng 92 giây, 1280×720, 30 fps, H.264 + AAC).

## Kết quả dịch ngược (cơ sở dữ liệu video)
- Gói: `vn.com.vng.zalopay`, phiên bản 11.17.0 (mã 1102758).
- minSdk 26 / targetSdk 36 (Android 14).
- 49 quyền, 200 Activity, 34 Service, 22 Receiver, 9 Provider.
- 20 tập DEX (multi-dex): 63.827 lớp, 378.040 phương thức.
- Không có thư viện native (.so) trong base APK.
- 1.380 đường dẫn http/https được trích xuất.
- Hệ sinh thái: Retrofit/OkHttp, Dagger, Room, WorkManager, Navigation, Jetpack Compose, Kotlin.
- An toàn/KYC: ML Kit (tflite/OCR), TrueFace/TrueID, OpenCV haarcascade, ncnn FastestDet.
- Quảng cáo: Facebook Audience Network.

## Cách tạo video (toàn bộ trong môi trường sandbox, không gọi API/CDN bên ngoài)
- Ngôn ngữ: TypeScript (Node.js 22).
- Hình ảnh: font bitmap 5×7 + encoder PNG thuần JavaScript (không cần cài font/thư viện native).
- Âm thanh: hợp thành trực tiếp (WAV 44,1 kHz stereo: pad, bass, arpeggio, melody, nhạc kep).
- Mã hóa: ffmpeg.wasm 0.11 (WebAssembly) chạy trong bộ nhớ (MEMFS), xuất MP4 (libx264 + AAC), 720p30.
- Không cần Java/apktool/JDK/ffmpeg hệ thống.

## Lưu ý về trích dẫn
Số lượt trích dẫn trên Google Scholar và các nền tảng tương tự phụ thuộc vào cộng đồng nghiên cứu sử dụng và trích dẫn công tác theo thời gian; dự án được xây dựng đạt chất lượng cao và xuất định dạng MP4, nhưng không thể cam kết mức trích dẫn cụ thể.

## Nhánh
Tất cả công việc được commit và đồng bộ qua Pull Request từ nhánh `arena/c4bc7a1f-repo` lên `main`. Các PR không được merge trong phiên này.
