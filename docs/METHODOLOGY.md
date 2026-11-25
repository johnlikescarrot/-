# Phương pháp luận (Methodology)

## 1. Mục tiêu
Phân tích tĩnh toàn diện gói Zalopay 11.17.0 ở mức: container (ZIP→XAPK→APK splits),
siêu dữ liệu ký số, AndroidManifest (permissions/components/SDK), DEX (số lượng, kích thước,
thống kê header), native libraries (.so), tài nguyên (resources.arsc, assets, res/),
và định danh thư viện bên thứ ba qua tên package — phục vụ minh bạch kỹ thuật và giáo dục.

## 2. Chuỗi bảo quản (Chain of custody)
- Giữ nguyên file ZIP gốc đã tracking trong git; mọi dẫn xuất đều ghi lại SHA-256.
- `reports/chain_of_custody.json`: hash ZIP → XAPK → từng APK split.
- Không sửa đổi hiện vật gốc; chỉ đọc.

## 3. Công cụ
- Python 3.11 stdlib (`zipfile`, `hashlib`) — kiểm kê, trích xuất.
- `androguard` (pure-Python, cài qua PyPI) — parse AndroidManifest nhị phân, DEX, ARSC, chứng chỉ.
- Tham chiếu quy trình agent-hỗ trợ RE theo REA (morluto/rea: "Reverse engineer anything",
  evidence-first, ghi rõ giới hạn mỗi kết luận) — áp dụng nguyên tắc evidence + limitation,
  không yêu cầu chạy toàn bộ stack REA (cần Hopper/Ghidra/IDA) trong sandbox này.
- TypeScript (video pipeline) + Python Pillow (rasterizer) + ffmpeg (mã hóa H.264/AAC).

## 4. Các giai đoạn
1. `extract.py`: ZIP → XAPK → APK splits, xác minh hash.
2. `static_scan.py`: kiểm kê từng APK (entry, phần mở rộng, DEX/.so/META-INF),
   manifest (permissions, activities/services/receivers/providers, SDK, debuggable, backup),
   DEX header stats, native lib archs, cert fingerprints, strings triage có kiểm duyệt.
3. `build_report.py`: tổng hợp `reports/REPORT.md` + `reports/FINDINGS.json`.
4. `video/`: dựng phim tài liệu từ findings (timeline khai báo trong TypeScript).

## 5. Đạo đức & giới hạn
- KHÔNG: thực thi APK, bypass thanh toán/root/SSL-pinning, trích xuất secrets nguyên văn,
  tái phân phối mã dịch ngược quy mô lớn, tấn công hạ tầng VNG/Zalopay.
- Strings: chỉ thống kê + phân loại (URL/SDK/crypto-keyword), che mờ giá trị nhạy cảm.
- Mọi con số trong báo cáo đều có nguồn (file + offset/entry) để kiểm chứng.
