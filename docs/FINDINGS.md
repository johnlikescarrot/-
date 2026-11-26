# Kết quả

Mỗi mục dưới đây có: **khẳng định**, **bằng chứng** (truy về `data/*.json`), và
**giới hạn** (điều không thể suy ra từ bằng chứng đó).

---

## F1 — Chuỗi đóng gói ba lớp

**Khẳng định.** Artifact là ZIP → XAPK → 5 APK; 96,9% khối lượng nằm trong hai
tệp (base 79,36 MB và split native 24,83 MB).

**Bằng chứng.** `data/01_provenance.json → container, apks`.
Chỉ có một ABI split: `armeabi-v7a`. Không có `arm64-v8a`.

**Giới hạn.** Đây là gói APKPure phục vụ thiết bị 32-bit; bản Play Store của cùng
version có thể có split arm64.

---

## F2 — Bề mặt khai báo

**Khẳng định.** Manifest khai báo 49 quyền, 267 component (202 activity, 34
service, 22 receiver, 9 provider), 30 component `exported=true`.

**Bằng chứng.** `data/01_provenance.json → manifest.components`.

**Giới hạn.** `exported=true` là hợp đồng API, không phải lỗ hổng. Đánh giá rủi ro
cần kiểm tra từng `onCreate`/`onBind` xem có kiểm tra quyền hay không — việc này
cần đọc bytecode từng lớp, nằm ngoài phạm vi.

---

## F3 — Bytecode: 1,6% là mã riêng

**Khẳng định.** 19 tệp DEX chứa 60 671 class; chỉ 952 class thuộc
`vn.com.vng.zalopay` (1,6%). Hệ sinh thái lớn nhất: Google (6 465), AndroidX
(4 250), Alipay Ariver/Griver (4 643).

**Bằng chứng.** `data/02_dex.json → totals, third_party_roots`.

**Giới hạn.** Đếm theo tiền tố package; một số lớp của ZaloPay có thể nằm trong
package trung tính sau khi R8 dịch chuyển.

---

## F4 — Tầng native

**Khẳng định.** 27 thư viện ELF (57,45 MB). 85,2% có đủ PIE + NX + full RELRO +
BIND_NOW. 100% bị stripped. 4 ngoại lệ RELRO đều là Crashlytics của Google.

**Bằng chứng.** `data/03_native.json → totals, libs[].relro`.

**Giới hạn.** RELRO/BIND_NOW đọc từ `PT_GNU_RELRO` và `DT_*`; một số linker không
đặt `DT_BIND_NOW` dù có `-z now`, nên tỉ lệ có thể thấp hơn thực tế.

---

## F5 — Họ thư viện bị bóc tên

**Khẳng định.** Sáu thư viện (8,63 MB) có tên 8 ký tự, bảng export gần rỗng,
nhưng payload chứa đầy descriptor JNI và tên lớp Java. Hai thư viện đạt mật độ
descriptor 0,40–0,44.

**Bằng chứng.** `data/03_native.json → libs[].descriptor_density`,
`totals.obfuscated_libs`. Đồ thị phụ thuộc cho thấy `libljsfrcwmw.so` →
`librcmefqonnx.so` và `libohxbee.so` → `liblytcuhe.so` — cặp "driver + payload".

**Giới hạn.** Không giải được vùng mã: đây là mô tả **hình dạng** của một lớp bảo
vệ (packer/VMP), không phải phân tích chức năng. Không có bằng chứng đây là mã
độc hại; nhiều ngân hàng và ví dùng cùng kỹ thuật.

---

## F6 — Mạng: tập trung first-party

**Khẳng định.** 1 370 URL duy nhất / 3 114 lần xuất hiện. 84,5% hit trỏ về hạ
tầng ZaloPay (`*.zalopay.vn`, `*.zalopay.com.vn`). 53 URL dùng `http://`.

**Bằng chứng.** `data/04_secrets.json → hosts, host_owners, urls.schemes`.

**Giới hạn.** URL trong string pool có thể là hằng số chết. Việc "có http://" không
chứng minh có traffic cleartext.

---

## F7 — Cleartext được bật trong NSC

**Khẳng định.** `res/xml/network_security_config.xml` đặt
`<base-config cleartextTrafficPermitted="true">`.

**Bằng chứng.** Trích nguyên văn trong `data/04_secrets.json →
network_security_configs`.

**Giới hạn.** Đây là cấu hình mặc định của nhiều app; rủi ro thực tế phụ thuộc
vào việc có endpoint nào thực sự dùng HTTP. Không tìm thấy URL `http://` trỏ tới
host first-party trong mẫu top.

---

## F8 — Không có bí mật lộ trong DEX

**Khẳng định.** Quét 13 chữ ký trên 243 786 chuỗi: không có Google API key, AWS
key, PEM private key, JWT, Stripe/Slack token. Cái tìm thấy là **3 public key PEM**
trong `assets/` (`public.pem`, `zoom.public`, `config/public_key.txt`).

**Bằng chứng.** `data/04_secrets.json → signatures`, `data/06_payloads.json →
public_keys`.

**Giới hạn.** Bí mật có thể nằm trong native payload đã mã hoá hoặc trong tài
nguyên nhị phân chưa quét (fonts, ảnh). Kết luận chỉ đúng cho corpus chuỗi.

---

## F9 — Nội dung thật khác phần mở rộng

**Khẳng định.** Trong 6 464 entry: 27 ELF, 20 DEX, 4 ZIP lồng, 4 gzip, 3 PEM, và
`assets/trueidlib.so` — 392 byte, entropy 7,50 — **không phải** ELF dù mang đuôi
`.so`.

**Bằng chứng.** `data/06_payloads.json → content_types, hidden_executables`.

**Giới hạn.** Entropy cao có thể là dữ liệu nén hợp lệ. Không kết luận đây là
payload độc hại; chỉ kết luận nó không thể `dlopen`.

---

## F10 — Năng lực nổi bật

| Chủ đề | Chuỗi khớp |
|---|---|
| Nạp mã động / mini-app runtime | 12 322 |
| Thanh toán · EMV · OTP · 3DS | 2 517 |
| eKYC · liveness · giấy tờ VN | 2 232 |
| Đo lường & quảng cáo | 1 176 |
| TLS pinning | 92 |
| Dò hook / debugger | 56 |
| Dò root / emulator | 27 |

**Bằng chứng.** `data/05_security.json → themes`.
**Giới hạn.** Như F4–F9: hiện diện chuỗi ≠ hành vi.
