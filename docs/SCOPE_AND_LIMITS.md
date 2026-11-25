# Phạm vi & Giới hạn (Scope & Limits)

**Trong phạm vi (static-only):**
- Cấu trúc container, hash, chữ ký (fingerprints, không private key).
- Manifest: permissions, components, intent-filters (đếm + liệt kê tên), SDK versions.
- DEX: số file, kích thước, counts (strings/types/methods/fields/classes) từ header/map.
- Native: danh sách .so theo ABI, kích thước, exported symbols đếm (không dump).
- Resources: kích thước ARSC, thống kê res/assets theo loại.
- Thư viện: suy luận từ package prefix (ví dụ com.google.*, io.reactivex.*) — mức độ tin cậy "heuristic".

**Ngoài phạm vi:**
- Decompile toàn bộ → tái xuất bản source; dynamic analysis; frida/hook; bypass.
- Mọi phát hiện "lỗ hổng khai thác được" chỉ mô tả ở mức hardening khuyến nghị, không PoC.

**Độ tin cậy:** static-only ⇒ không khẳng định hành vi runtime; mọi suy luận đều gắn nhãn confidence.
