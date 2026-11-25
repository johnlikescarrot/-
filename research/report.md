# Bên trong một bản phân phối Android: Zalopay 11.17.0

**Loại công trình:** nghiên cứu tình huống, phân tích tĩnh một mẫu; ngày phân tích 25/11/2026. Đây không phải bài báo đã được bình duyệt, không phải chứng nhận bảo mật và không khẳng định quan hệ với nhà phát hành. Tệp đầu vào do người dùng cung cấp, tên tệp có nhắc APKPure nhưng nguồn gốc đó chưa được kiểm chứng độc lập.

## Tóm tắt

Mẫu ZIP 98.813.878 byte chứa một XAPK, bao gồm APK chính và bốn APK cấu hình. Kiểm kê đệ quy qua ZIP và TAR nhận diện **6.832 lượt tệp**, tất cả được đọc và băm SHA-256; số này bao gồm cả các tệp chứa kho lưu trữ, không phải số tệp duy nhất hay số chức năng. Đáng chú ý, ba tài nguyên đuôi `.amr` có chữ ký ZIP và chứa TAR; phần mở rộng không phản ánh cấu trúc thực tế. APK chính có 19 DEX ở gốc và một DEX trong `assets`; split `armeabi_v7a` có 27 ELF 32-bit ARM. `apksigner` báo xác minh thành công cho cả năm APK với v2 và v3, và tất cả công bố cùng một vân tay chứng thư. Điều này hỗ trợ nhận định về tính nhất quán của bộ mẫu, **không** chứng minh chủ sở hữu khóa là nhà phát hành chính thức.

Manifest chính khai báo 49 tên quyền và 30 thành phần đặt `exported="true"` tường minh. Network Security Config được tham chiếu có `base-config cleartextTrafficPermitted="true"`; đây là một cấu hình cần xem xét, không phải bằng chứng rằng dữ liệu thực tế đã được truyền qua HTTP. Không có thực thi ứng dụng, đo lưu lượng, kiểm thử xâm nhập hay đánh giá máy chủ. Phân tích ngữ nghĩa chỉ có tính chọn lọc; không có căn cứ để tuyên bố ứng dụng an toàn, không an toàn hoặc có lỗ hổng.

## 1. Nguồn gốc, phạm vi và chuỗi bảo toàn

| Hiện vật | Kích thước (byte) | SHA-256 |
|---|---:|---|
| ZIP người dùng cung cấp | 98.813.878 | `34b9b6b6735f0f739617f97a259f931d17cdbacb51871c40b066de41f4b4610a` |
| XAPK bên trong | 105.608.128 | `686e9459da646c18b53a67d57e5a23c49466da140ec1e3e9682032f453ea7b8d` |
| APK chính | 79.359.087 | `e289d547b45f6224aed8ce7bf72d75ea5545cd50c0e768d1a74a3e8165fe6a03` |
| Split `armeabi_v7a` | 24.828.408 | `456aa08f4fa2e3f952657519ccf0b3f89a22cf8af040312425e926355c01987b` |
| Split `en` | 74.137 | `87699fda97e8a9be06583d9261689966d49a749569fbfa39092d09bd84f49942` |
| Split `vi` | 86.425 | `985fc073b810b6286c116400a88e5552616a05fc57f8a5376599c8bfaa963e33` |
| Split `xhdpi` | 1.232.563 | `f31350c3caed15099d2c2d325d88fef52268960ef66b1496eb099a19203aaa3b` |

SHA-256 định danh chính xác các byte đã phân tích chứ không tự xác định nguồn gốc. Chỉ nghiên cứu mẫu này; không khái quát cho phiên bản khác hoặc mọi biến thể phân phối. Tệp gốc không được sửa đổi. Không cài APK, không đăng nhập, không gửi giao dịch và không liên hệ endpoint của ứng dụng.

## 2. Kiểm kê có giới hạn và kiểm soát

`scripts/inventory.py` duyệt central directory của ZIP và các mục logic của TAR, nhận dạng ZIP/TAR/gzip bằng magic byte (TAR: `ustar`) thay vì chỉ dựa vào hậu tố. Mỗi tệp được đọc hoàn toàn, kiểm tra CRC khi là ZIP, ghi kích thước, phương pháp nén, đường đi có thứ tự từng lớp và SHA-256. Các tên trùng được phân biệt bằng chỉ số mục; đường dẫn trong kho được coi là dữ liệu, không được dùng để giải nén ra hệ thống. Bốn luồng gzip được giải nén có giới hạn và lưu thêm băm của nội dung đã giải nén. Các tệp gốc và các mục thư mục không được lẫn vào nhau.

- **14 kho được đọc:** ZIP gốc, XAPK, 5 APK, 3 ZIP `.amr`, 3 TAR trong đó và `assets/android.1.zip`.
- **6.832 lượt tệp đã băm / 6.832 lượt tệp**, thêm 7 mục thư mục; không thấy tên trùng trong cùng một kho và không có lỗi trong giới hạn công cụ. Trong số lượt tệp có 13 tệp là chính các kho con; các lượt xuất hiện khác nhau không được khử trùng lặp.
- Phân loại theo magic: 5.319 XML nhị phân Android, 4 bảng tài nguyên, 20 DEX, 27 ELF, 636 PNG, 159 WebP, 5 JPEG, 4 gzip, 10 ZIP, 3 TAR và 645 mục loại khác. Tên loại là phân loại sơ bộ theo byte đầu, không phải phân tích ngữ nghĩa.
- Giới hạn thực thi: tối đa 8 lớp lồng, 128 MiB/tệp, 1 GiB dữ liệu mở rộng cộng dồn, 100.000 mục. Không gặp giới hạn này. Công cụ không tự phát hiện mọi định dạng tùy biến, kho tự giải nén, nội dung được mã hóa hay kho không có magic đã hỗ trợ. “Đầy đủ” trong `coverage` chỉ có nghĩa đầy đủ **trong phạm vi nhận dạng này**, không phải giải mã mọi định dạng hoặc hiểu toàn bộ hành vi.

Bằng chứng có thể đọc bằng máy: [`inventory-summary.json`](inventory-summary.json) và [`inventory.jsonl.gz`](inventory.jsonl.gz). Ví dụ xem một mục: `gzip -dc research/inventory.jsonl.gz | grep 'assets/android.1.zip'`. Tệp `chain` chứa chỉ số và tên từng lớp, còn `path` thuận tiện cho người đọc. Có kiểm thử tệp lồng nhau, đường dẫn bất thường, tên trùng, giới hạn và lỗi CRC.

### Phát hiện về cấu trúc ẩn sau phần mở rộng

`assets/Griver/66666692_1.24.8.amr`, `66666693_2.0.7.amr` và `68687209_2.9.7.8.amr` đều bắt đầu bằng `PK` và đọc được như ZIP. Mỗi ZIP chứa một TAR, một `CERT.json` và một `Manifest.xml`; TAR chứa HTML/JavaScript/CSS và dữ liệu cấu hình. Điều này mô tả nội dung đóng gói, không chứng minh các gói mini-app được tải, tin cậy hay thực thi trong một phiên sử dụng. Kiểm kê ba TAR bổ sung 30 tệp; bỏ qua định dạng bên trong sẽ làm mất các mục này.

## 3. Danh tính gói và chữ ký

Manifest Android chính và manifest XAPK đều ghi package `vn.com.vng.zalopay`, version name `11.17.0`, version code `1102758`; manifest chính ghi min SDK 26 và target SDK 36. Bốn split là `config.armeabi_v7a`, `config.en`, `config.vi` và `config.xhdpi`. Đây là thông tin **mẫu**, không phải xác thực nguồn phân phối. Không thể suy từ split ABI được cung cấp rằng sản phẩm không có bản 64-bit ở nơi khác.

`apksigner` CLI `0.9` (JAR SHA-256 `eefdd6aed9db9fb849e4c98a50d8741e19d1b674ba6547220bcb9c3ed152123a`) báo `Verifies` cho cả 5 APK, với v2 và v3 được xác minh. Bốn split cũng báo v1 được xác minh; APK chính báo v1 là false. Không có sidecar v4 trong gói được cung cấp. Vân tay SHA-256 của chứng thư ký mà công cụ báo là như nhau ở cả năm APK:

`59dd31fdf36070a0d7f8c8cde843fac371df7f1a915c0136c9c9b55992e8cdae`

REA 6.10.1 độc lập đọc cấu trúc và tính lại digest nội dung, báo `self_consistent=true` cho từng APK. **REA không xác minh mật mã chữ ký trong phép kiểm tra này**; kết quả chữ ký ở trên là từ `apksigner`. Công cụ cũng báo xác minh source stamp, nhưng chúng tôi không đối chiếu vân tay signer/source stamp với kênh khóa chính thức độc lập, không kiểm tra chuỗi tin cậy hay quy kết danh tính pháp lý. Cấu trúc/ý nghĩa các sơ đồ v2 được mô tả trong đặc tả AOSP [1]. Chi tiết từng tệp: [`signatures.json`](signatures.json).

**Đối chứng âm:** trên bản sao tạm của APK chính, đảo một bit ở offset 1.024 làm SHA-256 thay đổi thành `c1545446e9b50d661c379ce986085b26134d009859a4f5575a2e7e11eaf1b1ac`; `apksigner verify --verbose` trả mã 1 với `APK integrity check failed` / `CHUNKED_SHA256 digest mismatch`. Đối chứng kiểm tra rằng phép xác minh này phát hiện thay đổi cụ thể đó; nó không chứng minh bao phủ mọi cách sửa hoặc nguồn gốc của mẫu. Bản sao bị sửa không được phân phối.

## 4. Manifest: quyền và bề mặt thành phần

Androguard 4.1.3 giải mã `vn.com.vng.zalopay.apk!/AndroidManifest.xml`; kết quả có cấu trúc nằm trong [`android-analysis.json`](android-analysis.json). Chúng tôi đếm trực tiếp các nút `uses-permission`/`uses-permission-sdk-23`: **49 tên khác nhau**. Ví dụ khai báo bao gồm CAMERA, RECORD_AUDIO, READ_CONTACTS, WRITE_CONTACTS, ACCESS_COARSE_LOCATION, ACCESS_FINE_LOCATION, READ_PHONE_STATE và QUERY_ALL_PACKAGES. READ_EXTERNAL_STORAGE có `maxSdkVersion=32`, WRITE_EXTERNAL_STORAGE có `maxSdkVersion=28`. Một số tên là quyền của nhà cung cấp hoặc tên tùy biến; không coi tất cả là cùng một mức bảo vệ. Manifest không cho biết quyền nào đã được người dùng cấp, chức năng nào thực sự dùng quyền, hay dữ liệu nào đã rời thiết bị [2].

| Loại được khai báo | Số lượng |
|---|---:|
| Activity | 200 |
| Activity alias | 2 |
| Service | 34 |
| Receiver | 22 |
| Provider | 9 |
| **Tổng** | **267** |

Có **30** nút ghi `android:exported="true"` theo nghĩa đếm văn bản tường minh. Con số này không tính giá trị mặc định, sự kế thừa permission, cấu hình ở runtime, điều kiện thiết bị hay tính khả khai thác. Các phần tử `exported` cần được đọc cùng intent filter và kiểm tra quyền/trạng thái [3]. Manifest cũng ghi `allowBackup="false"`, `fullBackupContent="false"`, không ghi một literal `android:debuggable`, và tham chiếu tài nguyên `networkSecurityConfig`; không suy ra trạng thái máy thực tế chỉ từ các giá trị này.

### Một lát cắt mã: chia sẻ ảnh cho QR

Bằng chứng gồm hai lớp khác nhau:

1. Manifest khai báo `vn.com.vng.zalopay.shareqr.presentation.QRReceiverActivity` với `exported="true"`, intent filter `android.intent.action.SEND`, category DEFAULT và MIME `image/*`.
2. Chuỗi định danh lớp xuất hiện trong `classes7.dex`. Decompile **chọn một lớp** bằng JADX 1.1.0 cho thấy phương thức `onCreate` có nhánh kiểm tra kiểu bắt đầu bằng `image/`, lấy một giá trị `Uri` từ Intent, gọi `InputImage.fromFilePath` và chuyển ảnh cho một bộ xử lý mã vạch. Tên hằng số/phụ thuộc bị biến đổi; JADX báo tổng cộng một lỗi trong lần chạy, do đó đây là pseudocode cần đối chiếu bytecode chứ không phải mã nguồn gốc hay bằng chứng thực thi.

Sự kết hợp này gợi ý một luồng chia sẻ ảnh cần nghiên cứu tiếp, **không** chứng minh cách hệ thống xác thực, dữ liệu nào được xử lý tại runtime, hay một lỗ hổng. Không thử gửi Intent cho ứng dụng thật. Lệnh và điều kiện tái tạo có trong README.

## 5. Cấu hình mạng

Manifest tham chiếu `@7F16000D`, được bảng tài nguyên phân giải thành `res/xml/network_security_config.xml`. Bản XML giải mã có:

```xml
<base-config cleartextTrafficPermitted="true">
  <trust-anchors><certificates src="system"/></trust-anchors>
</base-config>
<debug-overrides>
  <trust-anchors><certificates src="system"/><certificates src="user"/></trust-anchors>
</debug-overrides>
```

Đây là trích đoạn phục vụ diễn giải; bản đầy đủ và SHA-256 byte nguồn được lưu trong `android-analysis.json`. Chính sách cơ sở cho phép cleartext đối với các thành phần tuân thủ chính sách Android; tài liệu Android cũng nêu việc thực thi phụ thuộc thành phần và không phải mọi socket đều chịu cùng cơ chế [4]. `debug-overrides` là cấu hình áp dụng cho ứng dụng debuggable theo Android, không chứng minh CA người dùng được tin cậy trong bản phát hành. XML còn có một domain-config cho `storage.googleapis.com`, nhưng sự xuất hiện của tên miền không chứng minh đã kết nối. Không có capture TLS/HTTP, không có kiểm tra certificate pinning hiệu lực, và không có kết luận về rò rỉ.

## 6. Mã DEX, native, mô hình và JavaScript

- 20 tệp DEX gồm `classes.dex` đến `classes19.dex` và `assets/audience_network.dex`. Tổng **63.827 class definition** và **378.040 method ID** là tổng các bảng theo tệp, không khử trùng lặp; DEX trong `assets` có thể có cách nạp khác với DEX ở gốc. Cả 20 DEX có SHA-1 header và Adler-32 khớp byte nội bộ. Hai phép kiểm tra này không thay thế chữ ký APK.
- Bộ đếm prefix class definition nhận được 4.250 `androidx/`, 2.228 `com/alibaba/griver/`, 952 `vn/com/vng/zalopay/`, 335 `okhttp3/`, 221 `com/google/firebase/` và 50 `com/google/mlkit/`. Đây chỉ là tên lớp trong DEX; obfuscation, các namespace khác và việc đặt tên làm cho các số này không đại diện cho mọi thư viện hay quyền sở hữu mã.
- Method ID được chọn có tên `WebView.loadUrl`, `WebView.addJavascriptInterface`, `WebSettings.setJavaScriptEnabled`, `ContentResolver.query`, `System.loadLibrary` và `DexClassLoader.<init>`. Đây là **tham chiếu trong bảng method ID**, không phải bằng chứng lệnh gọi trực tiếp, tính reachable, dòng dữ liệu hoặc hành vi. Các ID có thể lặp giữa các DEX.
- Split ABI chứa 27 ELF `EM_ARM`, 32-bit; 1 có `.symtab` và các section `.debug_*`. Phân tích chỉ đọc header, `DT_NEEDED` và số dynamic symbol, không decompile hay kiểm toán mọi hàm native. Tệp `assets/trueidlib.so` không bắt đầu bằng magic ELF và không bị tính là ELF chỉ vì đuôi tên.
- Có 16 đường dẫn tài nguyên kết thúc `.tflite`/`.tfl`; tên và phần mở rộng gợi ý hiện vật mô hình, không xác nhận chất lượng, cách sử dụng hay dữ liệu đầu vào của mô hình.
- `assets/android.1.zip!/main.jsbundle` dài 3.613.478 byte, SHA-256 `c6a70c55c4f99567db2911c13ec63359b80c526261d19882a5837444e4970ca1`; chuỗi byte `__d(` xuất hiện 2.346 lần, `ReactNative` 48 lần và `NativeModules` 93 lần. Đây là đếm marker, không phải số module reachable. Không thấy `sourceMappingURL` trong chính bundle; điều này không loại trừ source map ở nơi khác.
- Thử REA `analyze-javascript-application` trên bản sao bundle với giới hạn 60 giây cho một nguồn dẫn tới `provider_timeout`; có kết quả từng phần nhưng không coi là phân tích ngữ nghĩa hoàn tất. Không thực thi bundle. [`tool-limitations.json`](tool-limitations.json) ghi rõ các bước chưa hoàn tất.

## 7. Cách diễn giải, giới hạn và chương trình nghiên cứu tiếp

| Bằng chứng tĩnh | Có thể nói | Không thể nói chỉ từ bằng chứng này |
|---|---|---|
| Khai báo quyền | Tên quyền xuất hiện trong manifest | Quyền đã được cấp, dữ liệu đã truy cập hoặc chia sẻ |
| `exported=true` | Thành phần được khai báo công khai tường minh | Không có kiểm soát khác hoặc có lỗ hổng |
| Cleartext được cho phép | Chính sách cơ sở cho phép cleartext ở lớp áp dụng | Đã có request HTTP mang dữ liệu nhạy cảm |
| Cùng chứng thư, chữ ký v2/v3 được công cụ kiểm | Tính nhất quán và kết quả kiểm tra với bộ byte này | Khóa thuộc tổ chức nào hoặc app chính thức/an toàn |
| Tên API trong method ID | Định danh được tham chiếu trong bảng | API thực sự chạy hoặc dữ liệu đi tới đâu |
| JS/ELF/tài nguyên hiện diện | Byte có trong gói | Mọi thành phần được tải và dùng ở runtime |

Mã obfuscated, reflection, JNI, tài nguyên nhị phân, code tải từ xa, trạng thái server, khả năng của thiết bị, feature flag và đường đi chưa được quan sát là các nguồn bất định. Đánh giá của Reaves và cộng sự [5] chỉ ra những thách thức thực tế của công cụ phân tích Android; FlowDroid [6] minh họa một lớp phân tích luồng dữ liệu phức tạp hơn, nhưng **chưa được chạy trong nghiên cứu này**. OWASP MASVS [7] là khung định hướng câu hỏi kiểm thử, không phải tuyên bố tuân thủ.

Nếu có quyền và môi trường thử nghiệm phù hợp, bước tiếp theo là đối chiếu chứng thư với kênh công bố chính thức, dùng thiết bị cách ly/tài khoản thử và dữ liệu giả, kiểm thử từng luồng cấp quyền, thành phần xuất, các stack mạng và TLS, đối chiếu static với trace, mở rộng phân tích JNI/JS, và kiểm tra các điều khiển liên quan của MASVS. Bất kỳ kết luận bảo mật nào đều cần điều kiện tái hiện, mức độ ảnh hưởng và xác nhận của chủ sở hữu trước khi công bố.

## Tài liệu tham khảo

1. Android Open Source Project. *APK Signature Scheme v2*. https://source.android.com/docs/security/features/apksigning/v2 (truy cập 25/11/2026).
2. Android Developers. *Request app permissions*. https://developer.android.com/training/permissions/requesting (truy cập 25/11/2026).
3. Android Developers. *`<activity>` manifest element*. https://developer.android.com/guide/topics/manifest/activity-element (truy cập 25/11/2026).
4. Android Developers. *Network security configuration*. https://developer.android.com/privacy-and-security/security-config (truy cập 25/11/2026).
5. Reaves, B. và cộng sự. “*droid: Assessment and Evaluation of Android Application Analysis Tools.” *ACM Computing Surveys* 49(3), bài 55, 2016. https://doi.org/10.1145/2996358.
6. Arzt, S. và cộng sự. “FlowDroid: Precise Context, Flow, Field, Object-sensitive and Lifecycle-aware Taint Analysis for Android Apps.” *PLDI*, 2014. https://doi.org/10.1145/2594291.2594299.
7. OWASP. *Mobile Application Security Verification Standard*. https://mas.owasp.org/MASVS/ (truy cập 25/11/2026).
8. REA. *APK signature schemes* và *Static Android APK analysis*. https://github.com/morluto/rea/blob/main/docs/apk-signature.md ; https://github.com/morluto/rea/blob/main/docs/guides/android/android-analysis.md.
9. Androguard. https://github.com/androguard/androguard ; JADX. https://github.com/skylot/jadx. Các phiên bản thực dùng được ghi trong hướng dẫn tái tạo.
