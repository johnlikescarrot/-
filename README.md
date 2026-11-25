# Reverse-engineering research package: ZaloPay 11.17.0 (XAPK)

Kho lưu trữ chứa một nghiên cứu **tĩnh, có thể tái chạy** về cấu trúc đóng gói của
ứng dụng `vn.com.vng.zalopay` phiên bản `11.17.0` (tệp nguồn: tệp ZIP do người dùng cung cấp).

## Phạm vi và nguyên tắc

- Chỉ phân tích tĩnh: kiểm kê tệp, manifest, quyền, thành phần Android, cấu trúc DEX,
  thư viện native và marker công nghệ.
- **Không** trích xuất bí mật/khóa/token, **không** dịch ngược sang mã nguồn độc quyền,
  **không** can thiệp cơ chế bảo vệ hay mô phỏng hành vi thanh toán.
- Mọi kết luận đều có bằng chứng là số liệu/băm được lưu trong `analysis/findings.json`.

## Cấu trúc

| Đường dẫn | Vai trò |
|---|---|
| `Zalopay-..._APKPure.zip` | Dữ liệu đầu vào (ZIP chứa XAPK). |
| `analysis/analyze.py` | Bộ phân tích tĩnh dùng Androguard + đọc header DEX. |
| `analysis/findings.json` | Dữ liệu kết quả có cấu trúc (máy đọc được). |
| `analysis/REPORT.md` | Báo cáo nghiên cứu bằng Markdown. |
| `analysis/requirements.txt` | Phiên bản công cụ để tái chạy. |
| `video/` | Bộ tạo video trình bày kết quả bằng TypeScript (đang xây dựng). |

## Tái chạy phân tích

```bash
python3 -m venv .venv
.venv/bin/pip install -r analysis/requirements.txt
mkdir -p .work
unzip -o 'Zalopay-Thanh+toán+&+Tài+chính_11.17.0_APKPure.zip' -d .work/
mv .work/*.xapk .work/app.xapk
unzip -o .work/app.xapk -d .work/xapk/
.venv/bin/python analysis/analyze.py \
  --xapk .work/app.xapk \
  --outer 'Zalopay-Thanh+toán+&+Tài+chính_11.17.0_APKPure.zip' \
  --out analysis/findings.json --report analysis/REPORT.md
```

## Tài liệu tham khảo

- Androguard: https://github.com/androguard/androguard
- REA (Reverse Engineer Anything): https://github.com/morluto/rea
- Android app manifests: https://developer.android.com/guide/topics/manifest/manifest-intro
