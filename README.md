# Zalopay 11.17.0 — Reverse Engineering Artifact

Repository này chứa kết quả nghiên cứu/phân tích tĩnh toàn diện cho gói:

- `Zalopay-Thanh+toán+&+Tài+chính_11.17.0_APKPure.zip` (XAPK)

## Đầu ra chính

- `analysis/REVERSE_ENGINEERING_REPORT.md` — báo cáo tổng hợp.
- `analysis/file_inventory.json` — inventory file ngoài (XAPK) kèm SHA256/MD5.
- `analysis/inner_file_inventory.json` — inventory toàn bộ 6.464 file bên trong các APK split/base.
- `analysis/deep_analysis.json` — phân tích sâu: manifest, permissions, components, cert, DEX stats, packages, URLs, native libs.
- `analysis/analyze_apk.py`, `analysis/deep_analyze.py`, `analysis/build_report.py` — script tái lập.
- `video/zalopay-re-11.17.0.mp4` — video tổng hợp (MP4, có âm thanh).
- `video/generate_video.py` + `video/src/composition.ts` — pipeline video (Python/TypeScript).

## Phạm vi

Phân tích tĩnh, kiểm kê và tài liệu hóa. Không chỉnh sửa hành vi ứng dụng, không phân phối lại APK đã sửa.
