# Ghi chú lớp 1 — Container

## Điều đã chứng minh
- Gói ngoài là ZIP chứa 1 XAPK; XAPK chứa 5 APK + manifest.json + icon.
- SHA-256 của từng tệp (xem `research/data/00_inventory.json`).
- 6,464 entry; 186.3 MiB giải nén; 4,249/6,162 payload base lệch căn 4 byte.
- APK base không có `META-INF/*.SF|RSA|DSA|EC`; 2 locale split có `BNDLTOOL.SF/.RSA`.

## Điều chỉ suy luận
- "Đóng gói lại bởi mirror": suy ra từ lệch căn + thiếu v1 + timestamp. Không có log
  của APKPure để xác nhận.
- "Không phải bản Play": Play phục vụ split theo ABI/density *theo thiết bị*; bundle
  này chỉ có armeabi-v7a, phù hợp với mirror gộp.

## Cách kiểm chứng lại
```bash
.venv/bin/python research/scripts/00_inventory.py
jq '.splits[] | {name, entries, misaligned: .alignment.misaligned_entries}' research/data/00_inventory.json
```
