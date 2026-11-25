# Giải phẫu tĩnh ZaloPay 11.17.0 — Báo cáo kỹ thuật đầy đủ

**Bản ghi phân tích:** `Zalopay-Thanh toán & Tài chính_11.17.0_APKPure.zip`
**Đối tượng:** `vn.com.vng.zalopay` 11.17.0 (versionCode `1102758`), minSdk 26 / targetSdk 36
**Ngày phân tích:** 2026-11-25 · **Phương pháp:** phân tích tĩnh hoàn toàn trên tệp lưu trữ
**Phiên bản tài liệu:** 1.0 · **Kèm theo:** phim nghiên cứu `artifacts/zalopay-11.17.0-anatomy.mp4`

---

## 0. Tóm tắt dành cho người đọc bận rộn

Báo cáo này mổ xẻ **toàn bộ** gói phát hành XAPK của ZaloPay 11.17.0: 5 tệp APK, **{{n.entries}} entry ZIP**, **{{n.dex}} tệp DEX**, **{{n.libs}} thư viện native** (cộng một thư viện trong `assets/`), **{{n.models}} mô hình máy học trên thiết bị**, và **{{n.base_entries}} entry** trong APK base. Mọi con số trong báo cáo đều sinh tự động từ pipeline trong `research/scripts/` và có thể tái lập byte-for-byte. Mười kết luận chính:

1. **Gói hàng là bản đóng gói lại (repackaged), không phải bản Play Store.** {{n.misaligned}}/{{n.base_entries}} payload trong APK base không căn lề 4 byte; APK base không có chữ ký v1 (không có `META-INF/*.SF`).
2. **Khối chữ ký APK bị hỏng cấu trúc.** Cặp id-value đầu tiên khai báo {{n.pair_len}} byte nhưng payload thực tế {{n.pair_actual}} byte; cặp thứ hai nằm ở offset lệch và không parse được. Chữ ký RSA **không xác minh được** trên bất kỳ cửa sổ byte liên tục nào.
3. **Danh tính người ký thì đọc được và nhất quán.** Chứng chỉ tự ký `CN=Vu Manh Hieu`, RSA 2048, hiệu lực 2016-04-21 → 2041-04-15, SHA-256 `{{n.cert_sha}}`.
4. **Mật độ mã nguồn:** {{n.classes}} lớp, {{n.methods}} tham chiếu phương thức, {{n.strings}} hằng chuỗi — trong đó {{n.obf_pkg}}% package bị rút gọn tên bởi R8.
5. **Ba tệp DEX chạm trần 16-bit:** `classes.dex`, `classes4.dex`, `classes7.dex` ở mức {{n.cap}}% giới hạn 65.536 method id; multidex là ràng buộc định dạng, không phải kiến trúc.
6. **Trọng lượng phòng thủ nằm ở native:** {{n.libs_mib}} MiB C++ ({{n.stripped}}% đã strip) chứa chống gian lận, liveness, keystore và mã hoá; 7 thư viện mang tên ngẫu nhiên hoá.
7. **Hệ sinh thái SDK rộng:** {{n.sdks}} SDK nhận diện bằng bằng chứng chuỗi, gồm Alibaba mPaaS (runtime mini-app), Google ML Kit, TrueID eKYC (Việt Nam), VNPAY, Visa, AppsFlyer, Meta Audience Network.
8. **Bề mặt mạng:** {{n.hosts}} host duy nhất, {{n.fp_hosts}} first-party; {{n.paths}} đường dẫn REST còn sống dưới dạng chuỗi — đủ để suy ra *hình dạng* giao thức nhưng không đủ để lấy bí mật.
9. **Siêu ứng dụng bằng cấu hình:** {{n.miniapps}} mini-app nằm trong một tệp JSON 883 KiB; runtime là Alibaba ARiver/mPaaS, không phải code first-party.
10. **Quyền riêng tư:** {{n.perms}} quyền, {{n.danger}} quyền nhóm nguy hiểm, {{n.acts}} activity, {{n.svcs}} service, {{n.recv}} receiver, {{n.prov}} provider — bề mặt tấn công cấp hệ điều hành là đáng kể nhưng có thể kiểm kê.

> **Tuyên bố giới hạn:** nghiên cứu này **chỉ phân tích tĩnh**. Không có kết nối mạng tới hạ tầng ZaloPay/VNG; không giải mã, không vượt qua kiểm soát truy cập, không tấn công, không trích xuất bí mật, PIN, khoá hay dữ liệu khách hàng. Những chỗ bằng chứng dừng lại, báo cáo nói rõ là dừng.

---

## 1. Phạm vi, đạo đức và khung pháp lý

### 1.1 Câu hỏi nghiên cứu

Một ứng dụng thanh toán được quản lý chặt (regulated) ở quy mô hàng chục triệu người dùng thực sự chứa gì, và **ranh giới của plaintext** nằm ở đâu? Cụ thể:

- **RQ1 — Cấu trúc:** gói phát hành được tổ chức thế nào (container, split, định dạng), và cấu trúc đó nói gì về chuỗi cung ứng phần mềm?
- **RQ2 — Phân tầng:** phần nào là first-party, phần nào là SDK bên thứ ba, và tầng nào gánh trách nhiệm bảo mật?
- **RQ3 — Bằng chứng:** với chỉ dữ liệu tĩnh, có thể chứng minh (không phải suy đoán) điều gì về tính toàn vẹn của gói?
- **RQ4 — Giới hạn:** điểm nào phân tích tĩnh *không thể* vượt qua, và vì sao việc nói rõ giới hạn lại quan trọng hơn việc suy diễn?

### 1.2 Nguyên tắc thực hiện

| Nguyên tắc | Cách hiện thực |
|---|---|
| Chỉ tĩnh | Không chạy ứng dụng, không cấp quyền, không mô phỏng tương tác, không gọi mạng |
| Tái lập | Mỗi số liệu sinh từ script; dữ liệu trung gian lưu JSON; video chỉ đọc JSON |
| Không tiết lộ bí mật | Không trích PIN/khoá/token; chỉ nêu *hình dạng* giao thức từ tên endpoint |
| Trung thực về giới hạn | Mọi kết luận "không xác minh được" được ghi rõ, không làm mềm |
| Tôn trọng pháp lý | Phân tích phần mềm đã phát hành công khai cho mục đích nghiên cứu, không vượt qua bảo vệ kỹ thuật |

### 1.3 Những gì báo cáo này **không** làm

- Không deobfuscate bytecode về mã nguồn Java/Kotlin tương đương (chỉ đọc metadata DEX: lớp, phương thức, chuỗi).
- Không reverse-engineer thuật toán mã hoá độc quyền trong native; chỉ kiểm kê thuật toán *được tham chiếu*.
- Không giải mã `assets/fgcpkjvqe.dat`, `assets/taibmcf.dat`, `assets/bshield.dat` (payload của Alibaba Security Guard) — đây là bí mật kỹ thuật của bên thứ ba.
- Không kết luận về an toàn thực tế của giao thức thanh toán; chỉ mô tả bề mặt quan sát được.

---

## 2. Chuỗi hành trình bằng chứng (chain of custody)

Mọi phân tích bắt đầu bằng băm. Bảng dưới là "dấu vân tay" của artefact; bất kỳ ai có cùng tệp đều tái lập được toàn bộ báo cáo.

{{t.hashes}}

**Ghi chú kỹ thuật về container:** tệp ngoài cùng là ZIP chứa **một** tệp `.xapk`; XAPK lại là ZIP chứa 5 APK + `icon.png` + `manifest.json`. Tổng giải nén: **{{n.entries}} entry** trên 5 APK, **{{n.uncompressed}}** byte chưa nén ({{n.ratio}}× dung lượng đĩa).

---

## 3. Giải phẫu container

### 3.1 Năm split APK

{{t.splits}}

Quan sát:

- **Base APK** ({{n.base_mib}} MiB) chứa toàn bộ DEX, `AndroidManifest.xml`, `resources.arsc` và `assets/`.
- **ABI split** chỉ chứa `lib/**` nhưng chiếm tới **{{n.abi_mib}} MiB** — lớn thứ hai trong gói.
- **Locale split** (`config.vi`, `config.en`) chỉ chứa `resources.arsc` đã biên dịch lại + manifest rỗng: 74 KiB và 86 KiB.
- **Density split** (`config.xhdpi`) chứa 258 drawable; base APK không còn drawable đa mật độ.

### 3.2 Pháp y ZIP: căn lề và chữ ký

Trường `header_offset` trong central directory cho phép xác định chính xác vị trí bắt đầu payload từng entry. Với APK được `zipalign` chuẩn, mọi payload phải căn 4 byte (hoặc 16 KiB với `-P 16`) để `mmap()` không cần copy.

- APK base: **{{n.misaligned}}/{{n.base_entries}} entry** lệch căn (≈ {{n.misaligned_pct}}%).
- ABI split: {{n.align_abi}} lệch · density: {{n.align_xhdpi}} lệch · hai locale split: {{n.align_loc}} lệch mỗi tệp.
- APK base **không** có `META-INF/*.SF`, `*.RSA`, `*.DSA`, `*.EC` — tức không có chữ ký JAR v1. Hai split cấu hình *có* `META-INF/BNDLTOOL.SF/.RSA` (dấu vết bundletool).

Hệ quả: trên bản base, Android chỉ có thể xác thực qua **APK Signature Scheme v2**. Nếu khối v2 hỏng (mục 9), gói không còn đường xác thực nào — đúng như quan sát.

---

## 4. Manifest, thành phần và quyền

### 4.1 Nhận dạng

| Trường | Giá trị |
|---|---|
| Package | `vn.com.vng.zalopay` |
| versionName / Code | `11.17.0` / `1102758` |
| minSdk / targetSdk / compileSdk | 26 / 36 / 36 |
| Activity chính | `vn.com.vng.zalopay.splashscreen.presentation.SplashScreenActivityV2` |
| AGP khi build | 8.12.3 (từ `META-INF/com/android/build/gradle/app-metadata.properties`) |
| VCS info | `NO_SUPPORTED_VCS_FOUND` (build không nhúng commit hash) |

### 4.2 Thành phần

| Loại | Số lượng | Ghi chú |
|---|---|---|
| Activity | {{n.acts}} | gồm toàn bộ `com.vnpay.vntaxi.*` (module gọi xe nhúng) |
| Service | {{n.svcs}} | Firebase Messaging, ML Kit, CameraX, WorkManager, GMS Ads |
| Receiver | {{n.recv}} | widget (QR cá nhân, game, phim), cài đặt ứng dụng |
| Provider | {{n.prov}} | FileProvider, FirebaseInitProvider, AudienceNetwork, Griver |

Sự xuất hiện của **module taxi VNPAY** (`com.vnpay.vntaxi.*`) bên trong APK ZaloPay là bằng chứng trực tiếp của mô hình "super-app nhúng đối tác": không phải deep-link sang app khác mà là code của đối tác được biên dịch vào chính gói.

### 4.3 Quyền

{{t.perms}}

Phân loại nhanh:

- **Nhóm nguy hiểm (runtime prompt):** {{n.danger}} quyền — danh bạ (đọc/ghi), camera, micro, vị trí chính xác & tương đối, trạng thái điện thoại, lịch (đọc/ghi), bộ nhớ ngoài, thông báo, âm thanh media.
- **Đặc thù:** `QUERY_ALL_PACKAGES` (nhìn thấy mọi app đã cài — cần cho chống gian lận/liệt kê ví đối thủ), `DETECT_SCREEN_CAPTURE` (phát hiện chụp màn hình), `NFC` (HCE thanh toán), `USE_BIOMETRIC`, `AD_ID`, ba quyền `ACCESS_ADSERVICES_*` (Privacy Sandbox).
- **Quyền riêng của launcher:** `com.bbk.launcher2.*` (BBK/vivo), `com.samsung.android.*`, `com.huawei.appmarket.*` — dấu hiệu tích hợp OEM để tạo shortcut và push.

---

## 5. Tầng bytecode (DEX)

### 5.1 Quy mô

{{t.dex}}

Tổng: **{{n.classes}} lớp**, **{{n.methods}} tham chiếu phương thức**, **{{n.strings}} hằng chuỗi**, **{{n.types}} type id** trên {{n.dex}} tệp, phiên bản định dạng 038.

### 5.2 Giới hạn 16-bit và multidex

Số method id trong một DEX bị giới hạn bởi kiểu 16-bit (65.536). Ba tệp đạt {{n.cap}}% giới hạn:

- `classes.dex` — 59.948 method / 10.703 lớp
- `classes4.dex` — 59.997 method / 11.441 lớp
- `classes7.dex` — 60.000 method / 12.953 lớp

Điều này chứng minh thứ tự sắp xếp DEX là do **bộ đóng gói** quyết định (R8/D8 pack theo giới hạn), không phản ánh ranh giới module. Hệ quả thực tiễn: bất kỳ phân tích nào dựa trên "tệp DEX nào chứa gì" đều sai lệch về mặt kiến trúc.

### 5.3 Obfuscation

- {{n.obf_pkg}}% package ({{n.obf_pkgs}}/{{n.pkgs}}) có tên thoả mẫu rút gọn cực ngắn (`a`, `Kp`, `q9`, ...).
- {{n.obf_cls}}% lớp nằm trong các package đó.
- `SourceFile` bị chuẩn hoá thành literal `"SourceFile"` cho 100% lớp → **không còn tên tệp nguồn**; không thể tái tạo cấu trúc module từ metadata.
- 11 tệp `.kotlin_module` và 5.476 package còn lại cho thấy codebase gốc là Kotlin-first, dùng Dagger/Hilt.

### 5.4 Vì sao vẫn nhận diện được SDK?

Vì R8 **không thể** xoá: (a) chuỗi hằng dùng cho URL/protocol, (b) tên symbol JNI phải khớp `Java_<package>_<Class>_<method>`, (c) tên lớp trong protobuf descriptor, (d) tài nguyên và asset. Do đó fingerprint theo *bằng chứng chuỗi* đáng tin hơn fingerprint theo tên package.

---

## 6. Tầng native

### 6.1 Kiểm kê

{{t.libs}}

- Tổng **{{n.libs_mib}} MiB**, **{{n.stripped}}%** không còn `.symtab`.
- Hardening: RELRO {{n.relro}}/{{n.libs_n}}, BIND_NOW {{n.bindnow}}/{{n.libs_n}}, NX {{n.nx}}/{{n.libs_n}}, PIE {{n.pie}}/{{n.libs_n}}, stack canary {{n.canary}}/{{n.libs_n}}. Đây là mức hardening "production mặc định" của NDK r2x — không phải gia cố đặc biệt, nhưng cũng không tuỳ tiện.
- Entropy cao nhất: `{{n.maxent}}` = {{n.maxent_val}} bit/byte (gần với dữ liệu nén/mã hoá).

### 6.2 Tên thư viện ngẫu nhiên hoá

7 thư viện có tên dạng `lib[a-z0-9]{6,9}.so`: `{{n.rnd}}`. Đây là kỹ thuật chống hooking theo tên: mỗi thư viện tự đăng ký JNI trong `JNI_OnLoad` (bằng chứng: export `JNI_OnLoad` + `MOR`-style registration), nên rename không làm hỏng chức năng nhưng làm hỏng script hook cứng tên.

Đáng chú ý: `liblytcuhe.so` và `librcmefqonnx.so` có **cùng kích thước 178.628 byte**, cùng bảng export, khác nhau 8.799 byte ở vùng `.text` — mẫu của cùng một build với tên khác nhau (hai "alias" của cùng thư viện runtime). Đây là quan sát định lượng, không phải suy đoán.

### 6.3 Chức năng suy ra từ symbol

- `libsecurity_lite.so` export đúng bốn hàm: `Java_com_alipay_plus_security_lite_SecurityGuardLiteNative_{init,encrypt,decrypt,signRequest}` → **Alibaba Security Guard Lite** (mã hoá + ký request).
- `libtrueidlib.so` export `Java_vn_ai_trueid_sdk_utils_SmartOTPHelper_vault*` và chuỗi `OCRA-1:HOTP-SHA256-8:QH64` → eKYC TrueID có **OCRA** (RFC 6287) cho OTP giao dịch, kèm kiểm tra debugger/integrity.
- `libohxbee.so` chứa tham chiếu `Lvn/ai/faceauth/sdk/presentation/domain/model/Liveness*` → liveness TrueID chạy native, không chỉ Java.
- `libsqlcipher.so` (net.zetetic) → cơ sở dữ liệu mã hoá cục bộ.
- `libtaibmcf.so` (12,0 MiB, lớn nhất) + `libfgcpkjvqe.so` (6,2 MiB) + `assets/taibmcf.dat`/`fgcpkjvqe.dat` → **Alibaba Security Guard (mcf)** — engine đánh giá rủi ro thiết bị.

---

## 7. Mật mã và quản lý khoá (mức kiểm kê)

Các thuật toán **được tham chiếu** trong hằng chuỗi (số lần xuất hiện):

| Thuật toán | Vai trò suy ra | Hits |
|---|---|---|
{{t.crypto}}

**Post-quantum:** Bouncy Castle được nhúng kèm toàn bộ họ PQC (`Kyber/ML-KEM` {{n.pqc.Kyber}}, `Dilithium` {{n.pqc.Dilithium}}, `SPHINCS+` {{n.pqc.SPHINCS}}, `Picnic` {{n.pqc.Picnic}}, `BIKE` {{n.pqc.BIKE}}, `HQC` {{n.pqc.HQC}}, `FrodoKEM` {{n.pqc.FrodoKEM}} hits), và các tệp `org/bouncycastle/pqc/.../lowmcL5.bin.properties`. **Đây là bằng chứng cấp chuỗi**, không chứng minh PQC bảo vệ lưu lượng thật: BC nhúng nguyên thư viện.

**Key management:** `AndroidKeyStore`, `KeyGenParameterSpec`, `StrongBox` (1 tham chiếu), `BiometricPrompt`, `setUserAuthenticationRequired`. Kết hợp với `USE_BIOMETRIC` + `USE_FINGERPRINT` cho thấy khoá được bảo vệ bằng phần cứng/TEE ở thiết bị hỗ trợ.

**Chống chụp màn hình:** quyền `DETECT_SCREEN_CAPTURE` (Android 14+) và các chuỗi `FLAG_SECURE`.

---

## 8. eKYC, máy học trên thiết bị và tài nguyên

### 8.1 Mô hình

{{t.models}}

**{{n.models}} tệp / {{n.models_mib}} MiB** — nhiều hơn hẳn một app ví thông thường, và tập trung vào ba bài toán: (1) OCR giấy tờ (ML Kit text recognition + layout), (2) phát hiện khuôn mặt & trạng thái mắt (blazeface, `BCLlefteyeclosed`, `BCLrighteyeclosed`, `BCLjoy` — mô hình phân loại trạng thái mắt/miệng cho **liveness**), (3) định vị thẻ/giấy tờ trong khung hình (PicoDet trên NCNN, object_labeler).

Đáng chú ý: `libVFaceLib.so` + `libohxbee.so` + `assets/models_bundled/*.emd` (EMD = model encrypted/compiled của TrueID) tạo thành pipeline liveness khép kín, chạy offline.

### 8.2 Tài nguyên

- `resources.arsc`: **{{n.arsc_mib}} MiB**, {{n.arsc_strings}} chuỗi toàn cục, 13 package tài nguyên (do nhiều AAR đóng gói).
- 5.209 tệp XML trong base: layout, drawable vector, animator, font, raw JSON animation (Lottie/Rive).
- 4 tệp `.riv` (Rive) + nhiều `.json` Lottie → animation runtime phong phú.

---

## 9. Pháp y khối chữ ký — trung tâm của báo cáo

### 9.1 Cấu trúc chuẩn (APK Signature Scheme v2)

Một APK được ký v2 có layout:

```
[ Contents of ZIP entries ][ APK Signing Block ][ Central Directory ][ EOCD ]
```

Khối chữ ký: `uint64 size` · các cặp `(uint64 length, uint32 id, payload)` · `uint64 size` · magic `APK Sig Block 42`. Mỗi cặp phải căn 8 byte; id v2 = `0x7109871a`, v3 = `0xf0536873`, v3.1 = `0x1b93ad61`.

### 9.2 Quan sát trên APK base

```
offset 78.807.040   f8 1f 00 00 00 00 00 00   → size = 8.184 byte
offset 78.807.048   67 05 00 00 00 00 00 00   → length = 1.383 byte
offset 78.807.056   1a 87 09 71               → id = 0x7109871a (v2)
offset 78.807.060   5f 05 00 00               → 1.375  (độ dài sequence signer)
offset 78.807.064   5b 05 00 00               → 1.371  (độ dài 1 signer)
offset 78.807.068   1d 03 00 00               → 797    (độ dài signed data)
```

Ba bất thường định lượng:

1. **Độ dài cặp không khớp nội dung.** Khai báo 1.383 byte, nhưng payload trường bắt đầu ngay sau **4 byte id** (không có prefix `uint64` riêng cho payload như chuẩn quy định cho cặp) — thực tế chiếm **1.387 byte**. Bộ parse nghiêm ngặt sẽ đọc lệch 4 byte và đồng bộ sai ở cặp kế tiếp.
2. **Cặp thứ hai không hợp lệ.** Tại offset 78.808.448 xuất hiện id `0xf0536873` (v3) tại vị trí **không căn 8**, với "length" đọc ra là 6.557.241.080.535.864.168 — vô nghĩa. Payload của cặp này lặp lại đúng khối signed-data 797 byte của cặp v2. Không có signer v3 nào parse được.
3. **Digest không tái lập.** Digest nhúng sẵn: `{{n.digest}}` (thuật toán `0x0103` = RSASSA-PKCS1-v1_5 / SHA-256). Chúng tôi đã tính lại digest nội dung theo đúng thuật toán chunked của AOSP cho **41 biên khối khác nhau** (offset khối chữ ký từ 78.807.000 đến 78.807.080, mỗi biên thử thêm ±8) — **không khớp**. Quét toàn bộ 78 MiB theo cửa sổ 256 byte bằng cách giải mã RSA (`m^e mod n`) tìm cấu trúc PKCS#1 v1.5 hợp lệ cũng **không tìm thấy** chữ ký nào.

### 9.3 Danh tính người ký (phần đọc được)

{{t.cert}}

Chứng chỉ parse được đầy đủ, tự ký, `sha256WithRSAEncryption`. Khoá công khai nhúng trong signer khớp đúng SPKI của chứng chỉ (`3ed316c8…036d`). Nghĩa là: **khối dữ liệu chứng chỉ nguyên vẹn**, chỉ có *framing* và *chữ ký* là không kiểm chứng được.

### 9.4 Phán quyết

> **Trạng thái: cấu trúc parse được, mật mã không chứng minh được.**
>
> - Chứng chỉ: **hợp lệ về cấu trúc**, tự ký, còn hiệu lực đến 2041.
> - Digest nội dung: **KHÔNG xác minh được** (không tái lập được trên bất kỳ biên khối nào).
> - Chữ ký RSA: **KHÔNG xác minh được** (không tìm thấy chữ ký PKCS#1 v1.5 hợp lệ trong khối).
>
> Cách diễn đạt đúng là "unverified", **không** phải "invalid signature", và tuyệt đối **không** phải "valid". Báo cáo giữ nguyên văn: *structurally parsed, cryptographically unproven*.

### 9.5 Vì sao điều này quan trọng

Với người dùng cuối, APK từ một mirror (APKPure) không có chuỗi tin cậy tới Play. Nếu khối v2 hỏng, hệ thống vẫn có thể cài (tuỳ ROM: một số chấp nhận v1/v2 thiếu nếu `unknown sources`), nhưng **không ai chứng minh được gói chưa bị sửa**. Với nhà nghiên cứu, đây là ví dụ sạch về một lớp "integrity theatre": giao diện có vẻ có chữ ký (có block, có certificate, có digest) nhưng không có bất kỳ bước nào kiểm chứng được.

**Khuyến nghị phòng thủ (cho đội phát hành):**
1. Xác minh bằng `apksigner verify --print-certs` trên **split do Play phục vụ**, không dùng mirror.
2. Pin signer digest (không chỉ cert subject) trong pipeline phát hành.
3. Coi khối chữ ký "parse được nhưng không verify được" là **input thù địch**.
4. Tái lập digest v2 trên đúng cửa sổ `[entries][CD][EOCD]`; đừng tin độ dài khai báo.
5. Log cả SPKI SHA-256, không chỉ hash tệp.

---

## 10. Bề mặt mạng và cấu trúc giao thức

### 10.1 Phân lớp host

{{t.hosts}}

**{{n.hosts}} host** phân thành bốn nhóm: first-party ({{n.fp_hosts}}), hạ tầng Alibaba/Alipay (render, openauth, worker, CDN mini-app), Google (Play, Firebase, ads, app-measurement), và các nhà cung cấp Việt Nam (FPT Smart Cloud eKYC, TrueID, VNPAY) + Meta/ByteDance/AppsFlyer cho quảng cáo & attribution.

### 10.2 Hình dạng giao thức (không phải nội dung)

Từ 290 đường dẫn REST còn sống dưới dạng chuỗi, có thể mô tả *hình dạng* luồng xác thực:

- `GET /v2/user/public-key`, `GET /v2/user/salt` → phân phối khoá công khai + salt theo người dùng.
- `POST /v2/user/pin/pre-check-update-pin` → kiểm tra PIN phía server **trước khi** cập nhật.
- `POST /v2/account/otp`, `/v2/account/otp-verification`, `/v2/user/pin/pin-reset/otp` → OTP là yếu tố thứ hai cho đặt lại PIN.
- `POST /v2/ekyc/upload/trueid/face-liveness[/v3]`, `/v2/user-liveness/{create,submit,status}` → liveness theo phiên, có versioning.
- `POST /v2/cashier/{precheck-pay,pay,order/status,result}`, `/v3/cashier/state/set` → máy trạng thái thanh toán có precheck.

**Ranh giới bằng chứng:** tên endpoint cho biết *có* salt, *có* khoá công khai, *có* bước pre-check — nhưng **không** cho biết định dạng envelope, thuật toán trao đổi khoá, hay cấu trúc PIN block. Báo cáo dừng ở đây; mọi tuyên bố xa hơn sẽ là suy diễn không có bằng chứng.

### 10.3 Mini-app

{{t.miniapps}}

`zalopay://launch/app/<id>` là deep link chuẩn cho mọi mini-app; 316/373 app trỏ `social.zalopay.vn`, 38 trỏ `mnap-director.zalopay.vn` (director/registry của mPaaS). Danh mục tiếng Việt: "Dịch vụ phổ biến" (88), "Giải trí – Cộng đồng" (77), "Tài chính" (65)…

---

## 11. Đánh giá quyền riêng tư (ánh xạ quyền → SDK)

| Quyền | SDK/bên nhận khả dĩ | Nhận xét |
|---|---|---|
| `AD_ID`, `ACCESS_ADSERVICES_*` | AdMob/GMA, Meta Audience Network, Pangle, AppsFlyer | 4 mạng quảng cáo + attribution trong một app ví |
| `READ_CONTACTS`, `WRITE_CONTACTS` | first-party (chuyển tiền P2P) | cần cho chọn người nhận; cũng là bề mặt dữ liệu nhạy cảm |
| `CAMERA`, `RECORD_AUDIO` | CameraX, ML Kit, TrueID liveness | eKYC + quét QR + video KYC |
| `ACCESS_FINE_LOCATION` | VNPAY taxi module, Maps | module gọi xe nhúng |
| `QUERY_ALL_PACKAGES` | Alibaba Security Guard, RootBeer, Play Integrity | liệt kê app để phát hiện môi trường rủi ro |
| `READ_PHONE_STATE`, `READ_BASIC_PHONE_STATE` | Security Guard (device fingerprint) | SIM/thiết bị làm tín hiệu chống gian lận |
| `DETECT_SCREEN_CAPTURE` | first-party | chống chụp màn hình màn hình nhạy cảm |
| `NFC` | HCE (`HostApduService`) | thanh toán chạm / nạp thẻ |

Nhận xét tổng: dữ liệu nhạy cảm **có** rời thiết bị (attribution + ads + eKYC cloud), nhưng phần lớn tín hiệu chống gian lận được xử lý trong native và gửi tới endpoint first-party. Đây là mô hình "thu tối đa tín hiệu, giảm thiểu chia sẻ" — nhưng không thể kiểm chứng tĩnh.

---

## 12. Kiến trúc tổng hợp (mô hình 4 miền tin cậy)

```
┌───────────────────────────── ZaloPay 11.17.0 ─────────────────────────────┐
│  Miền 1: Ví first-party (Kotlin, R8)                                      │
│    splash · cashier · transfer · loyalty · settings · widget              │
│    ↳ điều phối; gần như không giữ bí mật                                   │
├───────────────────────────────────────────────────────────────────────────┤
│  Miền 2: Runtime siêu ứng dụng (Alibaba mPaaS / ARiver / Griver)          │
│    373 mini-app từ JSON · render.alipay.com · appx worker · JSBridge      │
│    ↳ code tải động, ngoài phạm vi phân tích tĩnh của APK                  │
├───────────────────────────────────────────────────────────────────────────┤
│  Miền 3: eKYC & ML trên thiết bị (Google ML Kit + TrueID)                 │
│    22 model · liveness · OCR · face · barcode · NFC                       │
├───────────────────────────────────────────────────────────────────────────┤
│  Miền 4: Bảo mật native (Alibaba Security Guard + SQLCipher + BC)          │
│    libtaibmcf (12,0 MiB) · libfgcpkjvqe (6,2) · libsecurity_lite          │
│    ↳ nơi bí mật thực sự sống: ký request, chấm rủi ro, mã hoá CSDL        │
└───────────────────────────────────────────────────────────────────────────┘
                              ▲
                              │  container: 5 split, v2 block hỏng  ← điểm yếu
```

Hệ quả: bảo mật của app **không** nằm ở Kotlin (dễ đọc, dễ sửa) mà ở native + server. Kẻ tấn công có động cơ sẽ nhắm miền 4 và miền 2 (code tải động), trong khi nhà nghiên cứu chỉ có thể kiểm kê miền 1 và 3 một cách đầy đủ.

---

## 13. Phương pháp và tái lập

### 13.1 Pipeline

| Bước | Script | Đầu ra |
|---|---|---|
| 0 | `00_inventory.py` | `00_inventory.json` — băm, entry, căn lề |
| 1 | `01_manifest.py` | `01_manifest.json` — manifest, quyền, chứng chỉ |
| 2 | `02_dex.py` (`dex_parser.py`) | `02_dex.json` — lớp/phương thức/chuỗi/native method |
| 3 | `03_sdk.py` | `03_sdk.json` — 56 SDK, host, path |
| 4 | `04_native.py` | `04_native.json` — ELF, hardening, entropy |
| 5 | `05_resources.py` | `05_resources.json` — ARSC, model, asset bảo mật |
| 6 | `06_security.py` | `06_security.json` — khối chữ ký, phán quyết |
| 7 | `07_insights.py` | `07_insights.json` — obfuscation, mini-app, PIN surface |
| 8 | `08_film_data.py` | `film.json` — nguồn duy nhất cho phim |
| 9 | `video/` (TypeScript) | `artifacts/*.mp4`, `.srt`, `.vtt` |

### 13.2 Môi trường

- Python 3.11 + `pyelftools`, `cryptography`, `fonttools`, `imageio-ffmpeg` (FFmpeg 9.0.2, libx264)
- Node 22 + TypeScript 5, `@napi-rs/canvas` (Skia), `@resvg/resvg-js`
- Không dùng decompiler bên thứ ba (jadx/ghidra) cho số liệu chính: DEX reader tự viết để kiểm soát ngữ nghĩa offset.

### 13.3 Lệnh tái lập

```bash
python3 -m venv .venv && .venv/bin/pip install -r research/requirements.txt
.venv/bin/python research/scripts/00_inventory.py   # ... đến 08_film_data.py
npm ci && npm run render   # → artifacts/zalopay-11.17.0-anatomy.mp4
```

---

## 14. Giới hạn và mối đe doạ tới tính hợp lệ

1. **Tính đại diện:** một phiên bản (11.17.0) từ một mirror. Không suy rộng cho mọi phiên bản/ABI.
2. **ABI:** bundle chỉ có `armeabi_v7a`; kết luận native không áp dụng cho arm64/x86_64.
3. **Fingerprint SDK:** dựa trên chuỗi đặc trưng; một SDK có thể bị nhận nhầm nếu chuỗi trùng (đã ghi rõ "confidence" trong JSON).
4. **Framing khối chữ ký:** kết luận "không xác minh được" phản ánh *tệp này*. APK split do Play phục vụ (cùng version) rất có thể verify được — nhưng đó là tệp khác, không phải tệp đang phân tích.
5. **Không có ground truth động:** không chạy app, không quan sát traffic, nên mọi suy luận về giao thức là **cấu trúc**, không phải hành vi.
6. **Code tải động:** mini-app và JSBridge có thể thay đổi hành vi sau khi cài; phân tích tĩnh chỉ bao phủ phần nhúng.

---

## 15. Trích dẫn

```bibtex
@misc{zalopay2026anatomy,
  title        = {Static Anatomy of a Vietnamese Super-App: A Complete
                  Reverse-Engineering Atlas of the vn.com.vng.zalopay 11.17.0 XAPK,
                  and the Limits of Container-Level Integrity Proofs},
  author       = {ZaloPay 11.17.0 Static Anatomy Working Group},
  year         = {2026},
  month        = nov,
  note         = {Research film (4:36, 1080p30) + reproducible static-analysis pipeline},
  howpublished = {GitHub repository johnlikescarrot/-},
  url          = {https://github.com/johnlikescarrot/-}
}
```

**Artefact kèm theo:** `artifacts/zalopay-11.17.0-anatomy.mp4` (MP4/H.264 + AAC), `artifacts/zalopay-11.17.0-anatomy.srt`, `research/data/*.json`.

---

## Phụ lục A — bảng viết tắt

| Viết tắt | Nghĩa |
|---|---|
| XAPK | gói chia tách (base + config splits) dùng cho sideload |
| DEX | Dalvik Executable — định dạng bytecode của Android |
| R8 | trình shrink/obfuscate của Android Gradle Plugin |
| JNI | Java Native Interface |
| HCE | Host Card Emulation (NFC) |
| eKYC | electronic Know-Your-Customer |
| PQC | post-quantum cryptography |
| mPaaS/ARiver | nền tảng mini-app của Alibaba |
| RELRO/PIE/NX | thuộc tính gia cố ELF |

## Phụ lục B — toàn bộ 56 SDK phát hiện

{{t.sdks_full}}

## Phụ lục C — host first-party

{{t.fp_hosts_table}}
