# Zalopay 11.17.0 (APKPure XAPK) — Static Reverse-Engineering Research + Documentary Video

> Nghiên cứu tĩnh (static analysis) có trách nhiệm đối với gói `Zalopay-Thanh toán & Tài chính_11.17.0_APKPure.zip`
> (chứa 1 XAPK → 5 APK split + manifest + icon), kèm video tài liệu MP4 dựng bằng TypeScript.

**Phạm vi & đạo đức (Scope & Ethics):** chỉ phân tích tĩnh, không thực thi mã độc hại, không bẻ khóa,
không vượt qua thanh toán/xác thực, không công bố secrets/API keys (nếu phát hiện sẽ che mờ + mô tả chủng loại),
không sao chép hàng loạt mã nguồn dịch ngược có bản quyền — chỉ công bố số liệu, cấu trúc, và mẫu metadata.
Xem `docs/METHODOLOGY.md`.

**Cấu trúc:**
- `analysis/` — pipeline phân tích tĩnh (Python, tái lập được).
- `reports/` — báo cáo phát hiện (Markdown + JSON máy đọc được).
- `video/` — dự án TypeScript dựng video tài liệu → `video/out/zalopay-11.17.0-RE-documentary.mp4`.
- `paper/` — whitepaper phong cách học thuật + BibTeX/CITATION.cff.
- `docs/` — phương pháp luận, giới hạn, cách tái lập.

**Tái lập nhanh:**
```bash
python3 analysis/extract.py
python3 analysis/static_scan.py
python3 analysis/build_report.py
cd video && npm install && npm run build && npm run render
```
