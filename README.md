# ZaloPay 11.17.0 — Deep Reverse-Engineering Study & Research Film

> Một nghiên cứu giải phẫu tĩnh toàn diện (full static anatomy) của gói phát hành
> `Zalopay-Thanh toán & Tài chính_11.17.0_APKPure.zip` (XAPK split-APK, package
> `vn.com.vng.zalopay`, versionCode `1102758`), kèm một phim nghiên cứu học thuật
> được sản xuất bằng TypeScript và xuất ra MP4.

**Phạm vi:** chỉ phân tích tĩnh (static analysis) trên tệp đã tải về. Không có kết nối
tới hạ tầng của ZaloPay/VNG, không giải mã bí mật của bên thứ ba, không vượt qua
kiểm soát truy cập, không tấn công. Mọi phát hiện đều trình bày ở mức cấu trúc và
có thể tái lập (reproducible).

## Cấu trúc

| Đường dẫn | Nội dung |
|---|---|
| `research/` | Pipeline phân tích (TypeScript + Python/androguard), bộ dữ liệu JSON sinh tự động |
| `research/data/` | `inventory.json` (toàn bộ 6.162 entry), `components.json`, `findings.json`, … |
| `docs/` | Báo cáo kỹ thuật đầy đủ (tiếng Việt) + phụ lục |
| `video/` | Phim nghiên cứu: engine render TypeScript thuần + FFmpeg, audio mix, MP4 |
| `artifacts/` | MP4 thành phẩm, phụ đề, poster |

## Tái lập

```bash
./scripts/01_analyze.sh      # băm + giải nén + phân tích tĩnh
npm ci && npm run render     # render video 1080p từ dữ liệu phân tích
```

Xem `docs/REPORT.md` để đọc báo cáo, `video/README.md` để biết kiến trúc render.
