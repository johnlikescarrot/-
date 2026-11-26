# Phương pháp

Tài liệu này mô tả chính xác cách mọi con số trong phim được sinh ra, để bất kỳ ai
cũng có thể chạy lại và thu được kết quả giống hệt.

## 0. Phạm vi

| Hạng mục | Giá trị |
|---|---|
| Artifact | `Zalopay-Thanh+toán+&+Tài+chính_11.17.0_APKPure.zip` |
| Kích thước | 98 813 878 byte |
| SHA-256 | `34b9b6b6735f0f73…` (đầy đủ trong `data/01_provenance.json`) |
| Bên trong | `payload.xapk` (105 608 128 byte) |
| Thành phần | 1 base APK + 4 split APK (`armeabi_v7a`, `en`, `vi`, `xhdpi`) |
| Package | `vn.com.vng.zalopay`, versionName `11.17.0`, versionCode `1102758` |
| SDK | min 26, target 36, compile 36 |

Phân tích **tĩnh hoàn toàn**: không cài đặt, không chạy, không kết nối mạng tới
máy chủ của ứng dụng.

## 1. Vật chứa (provenance)

`analysis/tools/01_provenance.py`

* Băm SHA-256 cả ZIP ngoài và XAPK trong.
* Đọc `manifest.json` của XAPK để lấy danh sách split.
* Giải mã `AndroidManifest.xml` nhị phân (AXML) rồi census: quyền, component,
  `android:exported`, provider, `<queries>`.
* Tự viết parser **APK Signing Block** (`dexlib.apk_signing_block`) để xác nhận
  scheme v2/v3 thay vì tin vào `META-INF/*.SF`.

> Ghi chú: androguard chỉ được dùng để giải mã AXML. Mọi thứ còn lại là code
> trong repo.

## 2. DEX census

`analysis/tools/02_dex.py` + `analysis/tools/dexlib.py`

Parser DEX thuần `struct`:

1. Đọc header (string_ids, type_ids, proto_ids, field_ids, method_ids, class_defs).
2. Giải mã MUTF-8 cho toàn bộ string pool.
3. Duyệt `class_def_item` → `class_data_item` → danh sách method, đánh dấu
   `ACC_NATIVE` để lập chỉ mục các phương thức native.

Đầu ra: 19 tệp DEX, 60 671 class, 243 786 chuỗi, 970 phương thức native.
Toàn bộ string pool được ghi ra `data/dex_strings/*.txt` để các giai đoạn sau
tái sử dụng mà không phải giải nén lại APK.

## 3. Native ELF

`analysis/tools/03_native.py`

Parser ELF32/ELF64 đọc: header, section headers, `.dynamic`, `.dynsym`, program
headers. Từ đó suy ra:

| Kiểm tra | Cách xác định |
|---|---|
| PIE | `e_type == ET_DYN` |
| NX | `PT_GNU_STACK` không có cờ `PF_X` |
| RELRO đầy đủ | có `PT_GNU_RELRO` **và** `DT_BIND_NOW` / `DF_BIND_NOW` / `DF_1_NOW` |
| Stack canary | có ký hiệu `__stack_chk_fail` |
| Stripped | không có section `.symtab` |
| JNI surface | ký hiệu export bắt đầu bằng `Java_`, giải mã `_00024` → `$` |

Phát hiện phụ: một **họ thư viện bị bóc tên** (`liba97d.so`, `libfgcpkjvqe.so`,
`libljsfrcwmw.so`, `liblytcuhe.so`, `libohxbee.so`, `librcmefqonnx.so`) — tên 8
ký tự, bảng export gần như rỗng, nhưng payload chứa đầy descriptor JNI. Đó là
dấu hiệu của một lớp bảo vệ mã, không phải SDK.

Độ đo `descriptor_density` = tỉ lệ chuỗi trông giống chữ ký JNI (`(...)L...;`)
trên tổng số chuỗi ASCII. Hai thư viện đạt ~0,40–0,44; các thư viện còn lại ≈ 0.

## 4. Quét bí mật & endpoint

`analysis/tools/04_secrets.py`

* Nguồn: string pool 19 DEX, `res/xml/*.xml`, `assets/*.{json,js,html,xml,txt}`.
* Trích URL, host, email, và khớp 13 chữ ký (Google API key, AWS, PEM, JWT,
  Firebase, Sentry DSN, Stripe, Slack, pin SHA-256, …).
* Phân loại chủ sở hữu host theo bảng tiền tố (ZaloPay, VNG, Google, Ant Group,
  Visa, NAPAS, …).

**Chính sách bí mật:** không giá trị nghi vấn nào được ghi nguyên văn. Mỗi phát
hiện chỉ lưu `{prefix, length, sha256[:32]}` — đủ để kiểm chứng, không đủ để lạm
dụng.

## 5. Census năng lực

`analysis/tools/05_security.py`

14 chủ đề (mã hoá đối xứng/bất đối xứng, KDF, pinning, dò root/emulator/hook, nạp
mã động, cầu WebView, sinh trắc, thanh toán/EMV, eKYC, chứng thực thiết bị, đo
lường, obfuscation). Với mỗi chủ đề: số chuỗi khớp, số chuỗi duy nhất, tệp DEX
chứa nhiều nhất, và mẫu.

Đây là **census khả năng**, không phải kết luận hành vi. Giới hạn được nêu trong
chính tệp JSON.

## 6. Phát hiện payload ẩn

`analysis/tools/06_payloads.py`

Đọc 16 byte đầu của **mọi** entry trong cả 5 APK (6 464 entry) và phân loại lại
theo magic byte, bỏ qua phần mở rộng. Tính entropy Shannon để phát hiện nội dung
mã hoá/nén.

Kết quả đáng chú ý: `assets/trueidlib.so` (392 byte, entropy 7,50) mang tên thư
viện nhưng không phải ELF. Bốn gói ZIP lồng nhau, ba public key PEM, 17 mô hình
TensorFlow Lite / ncnn.

## 7. Bộ phim

`video/src/**` (TypeScript, strict).

```
data/*.json ──▶ facts (src/data.ts) ──▶ scene(t) → SVG ──▶ resvg → PNG ──▶ libx264
```

* Mỗi scene là **hàm thuần của thời gian** — không random, không đồng hồ hệ thống.
* Tổng thời lượng suy ra từ mảng scene, không khai báo tay.
* Render song song nhiều worker rồi concat, nên số frame không phụ thuộc số worker.
* Âm thanh tổng hợp hoàn toàn bằng DSP trong `src/audio/mix.ts` (không sample).

## 8. Kiểm chứng

```bash
python3 -m venv .venv && .venv/bin/pip install androguard fonttools brotli numpy imageio-ffmpeg
unzip -o "Zalopay-…zip" -d work && unzip -o work/payload.xapk -d work/xapk
for s in analysis/tools/0*.py; do .venv/bin/python "$s"; done
cd video && npm ci && npm run build
```

Mọi tệp trong `data/` được commit, nên có thể so sánh trực tiếp với kết quả chạy
lại mà không cần tải lại artifact.
