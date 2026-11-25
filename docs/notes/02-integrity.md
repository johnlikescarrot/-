# Ghi chú lớp 2 — Integrity (khối chữ ký)

## Chuỗi kiểm chứng đã thực hiện
1. **Parse X.509** từ payload signer v2 → thành công; self-signed, RSA 2048, hiệu lực 2016→2041.
2. **So SPKI** khoá nhúng trong signer với SPKI chứng chỉ → khớp.
3. **Giải mã cấu trúc signed-data** (digests / certificates) → đọc được digest SHA-256 `9789…4e07`.
4. **Tái lập digest nội dung** theo chunked SHA-256 của AOSP cho 41 biên khối (offset ±8) → không khớp.
5. **Quét chữ ký**: với mọi cửa sổ 256 byte trong khối, giải mã `m^e mod n`, tìm `00 01 FF…00 30 31 30 0d 06 09 60 86 48 01 65 03 04 02 01` → không có.
6. **Kết luận**: framing khối không chuẩn → không thể xác minh; ghi nhận "unverified".

## Vì sao không kết luận "giả mạo"
Băm chứng chỉ khớp với dữ liệu nhúng; cấu trúc ASN.1 hợp lệ; digest tồn tại. Một kẻ
giả mạo *có chủ đích* thường tạo khối **hợp lệ** với chứng chỉ tự ký của mình — và
điều đó cũng "verify được". Ở đây khối **không verify được**, giống dấu vết của một
công cụ đóng gói lại ghi đè vùng đệm căn lề. Báo cáo không suy đoán động cơ.

## Hệ quả thực tiễn
- Không thể chứng minh APK chưa bị sửa ⇒ không nên cài từ nguồn này cho mục đích thật.
- Nếu cần phân tích sâu hơn: lấy bản split do Play phục vụ cho cùng versionCode và so sánh từng entry.
