# Phân tích tĩnh XAPK ZaloPay 11.17.0 — báo cáo nghiên cứu

**Phiên bản báo cáo:** 1.0 · **Ngày thực hiện:** 25-11-2026 (UTC)
**Đối tượng:** `Zalopay-Thanh+toán+&+Tài+chính_11.17.0_APKPure.zip`
**Loại đánh giá:** phân tích tĩnh, chỉ đọc, offline. Không cài đặt hay khởi chạy ứng dụng.

> **Kết luận ngắn:** Gói đầu vào là một ZIP chứa XAPK version 2. XAPK gồm APK cơ sở và bốn APK split. Inventory đệ quy ghi nhận 6.472 mục tệp, mỗi mục có SHA-256. APK cơ sở có 20 DEX nhận diện được, 63.827 định nghĩa lớp, 378.040 định nghĩa phương thức và 404.914 chuỗi theo Androguard; manifest khai báo 49 quyền. Đây là số liệu cấu trúc của đúng artifact đã nhận, không phải đánh giá an toàn, xác thực nguồn phát hành hay bằng chứng về những gì ứng dụng thực sự làm khi chạy.

## 1. Câu hỏi, phạm vi và chuỗi bằng chứng

Mục tiêu là mô tả cấu trúc gói, định danh Android, bề mặt khai báo, chỉ dấu bytecode và payload native/asset; tạo dữ liệu có thể kiểm tra lại bằng hash. Không thực hiện pentest, khai thác, thử đăng nhập, gọi API, kiểm tra máy chủ, giải mã dữ liệu người dùng hay vượt cơ chế bảo vệ. Không chạy ứng dụng trong thiết bị/emulator. Tất cả mục tệp được inventory và băm; DEX được parse cho bảng cấu trúc/chuỗi, nhưng không decompile toàn bộ Java/Kotlin, không giải quyết toàn bộ resource table/localized strings, và không disassemble/symbolicate đầy đủ native code.

Chuỗi lồng nhau quan sát được:

```text
ZIP đầu vào (1 mục)
└── XAPK version 2
    ├── vn.com.vng.zalopay.apk          (base)
    ├── config.armeabi_v7a.apk          (ABI)
    ├── config.en.apk                   (ngôn ngữ)
    ├── config.vi.apk                   (ngôn ngữ)
    ├── config.xhdpi.apk                (density)
    ├── icon.png
    └── manifest.json
```

XAPK khai báo package `vn.com.vng.zalopay`, version `11.17.0` / code `1102758`, min SDK 26, target SDK 36. Hash của ZIP nguồn:

```text
SHA-256  34b9b6b6735f0f739617f97a259f931d17cdbacb51871c40b066de41f4b4610a
Kích thước 98,813,878 bytes
```

Hash này ràng buộc kết quả với bản ZIP được cung cấp, không xác nhận nguồn gốc hay tính chính thống của bản phát hành.

## 2. Inventory đệ quy và thành phần đóng gói

`research/output/file_inventory.csv` và `findings.json` ghi lại **6.472 mục** ở ba tầng: mục của ZIP ngoài, mục trực tiếp của XAPK và nội dung bên trong mỗi APK split. Mỗi dòng có tên container, đường dẫn nội bộ, kích thước giải nén, kích thước nén, compression method, CRC32 và SHA-256. Các byte của mỗi mục đều được đọc để tính hash; nội dung APK không được ghi lại thành bản sao trong báo cáo.

| Container | Kích thước | Số mục bên trong |
|---|---:|---:|
| Base APK | 79,359,087 bytes | 6.162 |
| `config.armeabi_v7a.apk` | 24,828,408 bytes | 32 |
| `config.en.apk` | 74,137 bytes | 6 |
| `config.vi.apk` | 86,425 bytes | 6 |
| `config.xhdpi.apk` | 1,232,563 bytes | 258 |
| **Tổng mục bên trong 5 APK** | — | **6.464** |

Các mục còn lại là một XAPK, manifest XAPK, biểu tượng và ZIP ngoài; tổng inventory là 6.472. Trong base APK, phần mở rộng thường gặp gồm 5.209 `.xml`, 221 `.png`, 111 `.webp`, 64 `.json`, 23 `.ttf`, 20 `.dex`, 14 `.tflite`, 9 `.js`, 9 `.html` và nhiều tệp `.properties`, `.proto`, mô hình và dữ liệu khác. Đây là phân loại theo hậu tố tên, không phải nhận diện ngữ nghĩa mọi payload.

## 3. Manifest, quyền và bề mặt thành phần

Base manifest khai báo min SDK **26**, target SDK **36**, compile SDK **36**. Tổng cộng có **267 component** được khai báo: 200 activity, 2 activity-alias, 34 service, 22 receiver và 9 provider. Tên activity khởi chạy được đánh dấu `MAIN`/`LAUNCHER` là `vn.com.vng.zalopay.splashscreen.presentation.SplashScreenActivityV2`.

Trong manifest đã parse, 14 activity, 2 activity-alias, 6 service, 8 receiver được ghi `exported=true`, 0 provider được ghi như vậy. Một số service/receiver exported có permission hệ thống hoặc permission Google ràng buộc; `exported=true` tự nó không chứng minh có lỗ hổng. Nên phân loại tiếp theo intent filter, quyền bảo vệ, caller validation và đường dữ liệu trong kiểm thử có kiểm soát.

Có **49 tên quyền được khai báo** trong base manifest. Các nhóm đáng chú ý để ưu tiên rà soát theo luồng sử dụng thực tế:

- Kết nối: `INTERNET`, `ACCESS_NETWORK_STATE`, trạng thái/thay đổi Wi‑Fi.
- Thiết bị/người dùng: camera, ghi âm, vị trí chính xác/tương đối, NFC, trạng thái điện thoại, danh bạ, lịch, media audio.
- Vận hành/nền: notifications, foreground service, boot completed, wake lock, rung.
- Tích hợp/quảng cáo: `QUERY_ALL_PACKAGES`, advertising ID/AdServices, billing, install referrer và các permission launcher/vendor.
- Sinh trắc học: `USE_FINGERPRINT`, `USE_BIOMETRIC`.

Manifest đặt `allowBackup=false`, có thuộc tính `networkSecurityConfig`, `requestLegacyExternalStorage=true` và `largeHeap=true`. Các thuộc tính này là đầu mối đọc manifest/resources sâu hơn; riêng chúng không đủ để kết luận hiệu lực cấu hình, mức phơi lộ hay tác động trên từng API Android. Quyền được khai báo không chứng minh đã được cấp hoặc được sử dụng lúc chạy.

## 4. DEX: quy mô và dấu vết chuỗi

Androguard nhận diện 20 mục DEX trong base: `classes.dex`, `classes2.dex`…`classes19.dex` cùng `assets/audience_network.dex`. Tổng dữ liệu DEX khoảng **69,7 MB**. Tổng số đếm theo các bảng DEX:

- **63.827** định nghĩa lớp
- **378.040** định nghĩa phương thức
- **404.914** chuỗi
- **407** URL literal sau chuẩn hóa/trích xuất cú pháp
- **151** hostname ứng viên lấy từ URL literal

Chuỗi DEX cho thấy dấu vết package/thư viện của AndroidX, Google/Firebase/ML Kit, Facebook SDK, Alipay/mini-app và tích hợp định danh/eKYC. Có tài nguyên mô hình `.tflite`, cùng thư viện nhận diện ảnh/face trong split ARMv7. Những dấu vết này hỗ trợ mô tả thành phần đóng gói nhưng **không** chứng minh code path nào được thực thi, dữ liệu nào được gửi hoặc mục đích cuối cùng của từng SDK.

Host ứng viên xuất hiện trong URL literal gồm một số hostname thuộc họ `zalopay.vn`/`zalopay.com.vn`, cùng các nhà cung cấp như Alipay, Google, FPT AI, TrueID, BIDV và VNPay. Danh sách này là kết quả trích xuất chuỗi; có thể trộn endpoint thật, liên kết trợ giúp, tài liệu, chuỗi ví dụ, placeholder, host môi trường thử nghiệm hoặc URL không dùng. **Không có URL nào được gọi trong quá trình này.** Để giảm lộ bề mặt route không cần thiết trong PR, bản JSON chia sẻ giữ số lượng và hostname ứng viên, nhưng loại bỏ path/query đầy đủ.

## 5. Chữ ký và thư viện native

Androguard ghi nhận các cờ chữ ký **v1: không**, **v2: có**, **v3: có**, một certificate nhìn thấy trên cả năm APK split:

```text
Subject CN: Vu Manh Hieu
SHA-1:      502195a8d3e7c05c6086db45e943b16efcd24e5d
SHA-256:    59dd31fdf36070a0d7f8c8cde843fac371df7f1a915c0136c9c9b55992e8cdae
```

Tên subject và việc các split khớp nhau **không** xác nhận nhà phát hành. Cần so khớp fingerprint với một bản tham chiếu chính thức, đáng tin cậy và cùng version trước khi gán danh tính. APK nguồn được đặt tên APKPure; nguồn tải bên thứ ba không thay thế được xác minh độc lập.

`config.armeabi_v7a.apk` chứa **27** thư viện `.so`; header ELF xác định ABI directory và machine là ARM 32-bit. Một số tên gợi ý thành phần xử lý ảnh/face, OCR, SQLCipher, crash reporting và Rive. Đó chỉ là tên/định dạng tệp, chưa phân tích hành vi native sâu, symbol table, dependency graph, JNI call graph hay rủi ro memory-safety.

## 6. Đánh giá bảo mật theo mức bằng chứng

**Đã quan sát trực tiếp:** cấu trúc lồng ZIP/XAPK/APK; hash tệp; metadata manifest; khai báo quyền/component; header và bảng DEX; chuỗi; header ELF; metadata certificate/signing-scheme mà parser nhận diện.

**Chưa quan sát:** thực thi runtime; quyền thực tế được cấp; request mạng; TLS/pinning thực tế; dữ liệu thiết bị được thu thập; nội dung request/response; xử lý thanh toán; cấu hình máy chủ; hành vi khi root/debug; logic JNI; tính toàn vẹn so với Google Play; lịch sử/rotation khóa ký.

**Không kết luận:** “an toàn”, “độc hại”, có lỗ hổng, đang liên lạc với host được trích xuất, hay được nhà phát hành chính thức ký. Không có phát hiện khẳng định xấu/tốt nào chỉ dựa trên chuỗi, quyền hoặc tên thư viện.

## 7. Quy trình kiểm chứng tiếp theo (phòng thủ)

1. Lấy bản cùng version từ kênh chính thức; so sánh package/version, toàn bộ SHA‑256, certificate lineage và signing block.
2. Đọc cấu hình network security/resources (bao gồm debug overrides, trust anchors và cleartext policy) sau khi giải mã resource table; rà soát component/exported theo intent-filter và permission.
3. Decompile/disassemble DEX có kiểm soát; dựng call graph quanh quyền nhạy cảm, WebView/JS bridge, TLS, logging, storage và tải code; phân biệt app code với SDK vendored.
4. Thực hiện dynamic analysis riêng trong emulator phòng lab/sandbox, tài khoản thử, dữ liệu giả, proxy/TLS instrumentation hợp pháp và egress allowlist; lưu PCAP/log đã khử dữ liệu định danh. Không dùng thông tin tài khoản/thẻ thật.
5. Phân tích ELF với parser phù hợp theo ABI, symbol/import, JNI, dependency, mitigations và model files; chạy kiểm tra license/SBOM cho thư viện bên thứ ba.
6. Công bố dữ liệu nhạy cảm đã biên tập, chain-of-custody và hạn chế; mọi nhận định runtime phải kèm log/tái hiện, không chỉ dựa trên string literals.

## 8. Tính tái lập và sản phẩm

Phân tích được thực hiện bằng Python `zipfile`/`hashlib` và **Androguard 4.1.4**. REA được nêu như một hướng công cụ tham khảo trong yêu cầu, nhưng không được chạy trong pipeline này; không khẳng định kết quả REA.

```bash
python3 -m venv .venv
. .venv/bin/activate
pip install -r research/requirements.txt
python research/analyze_apk.py \
  'Zalopay-Thanh+toán+&+Tài+chính_11.17.0_APKPure.zip' \
  --out research/output
```

Đầu ra: `research/output/findings.json` (kết quả máy đọc được, URL path/query đã loại bỏ), `research/output/file_inventory.csv` (inventory SHA-256 đầy đủ), `research/analyze_apk.py` (pipeline). Video tiếng Việt 88 giây, 1920×1080, H.264/AAC stereo 48 kHz nằm tại `deliverables/Zalopay_Static_Reverse_Engineering.vi.mp4`; nguồn dựng TypeScript và cấu hình phụ thuộc tại `video/`. Nhạc nền là score tổng hợp nguyên bản bằng mã, không có lời thuyết minh/giọng đọc.

**Tuyên bố về trích dẫn:** không thể cam kết video/báo cáo đạt số trích dẫn cao nhất trên Google Scholar hay bất kỳ nền tảng nào. Trích dẫn phụ thuộc chất lượng học thuật, phản biện, khả năng tái lập, phát hành có định danh bền vững (ví dụ DOI), khả năng tiếp cận và cộng đồng nghiên cứu; PR GitHub tự nó không tạo ra xếp hạng hay trích dẫn.
