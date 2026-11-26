# Ghi chú phản biện

Tài liệu này ghi lại những gì một người phản biện khó tính sẽ hỏi, và câu trả lời
trung thực — kể cả khi câu trả lời là "không biết".

---

## Về phương pháp

**Hỏi: Dự án có dùng REA (morluto/rea) như yêu cầu không?**

REA là một MCP server điều khiển engine native (Hopper / Ghidra / IDA / Kuna). Môi
trường thực thi của dự án này là một sandbox Linux không có engine nào trong số đó,
và REA không có backend phân tích DEX/APK. Vì vậy REA **không được chạy**. Thay vào
đó, nguyên tắc của REA (mỗi kết luận kèm bằng chứng và giới hạn) được cài đặt trực
tiếp: mỗi giai đoạn phân tích là một script đọc byte gốc và ghi JSON có thể kiểm
chứng. Điều này được nêu trong PR và trong `README.md`.

**Hỏi: Vì sao tự viết parser DEX/ELF thay vì dùng công cụ sẵn?**

Ba lý do: (1) `baksmali`/`jadx` cần JVM, không có trong sandbox; (2) parser tự viết
cho phép đo chính xác thứ cần đo (mật độ descriptor JNI, cờ hardening) mà không
phải chạy decompiler trên 78 MB bytecode; (3) kết quả tái lập được mà không phụ
thuộc phiên bản công cụ. Đổi lại, parser không thay thế decompiler: nó **không** đọc
được thân hàm.

**Hỏi: `androguard` có được dùng để làm gì?**

Chỉ để giải mã `AndroidManifest.xml` và `res/xml/*.xml` từ AXML nhị phân sang XML.
Không dùng để phân tích DEX, không dùng để kết luận.

## Về các khẳng định

**Hỏi: "84,5% lưu lượng" — lưu lượng thật hay chỉ là chuỗi?**

Là **số lần xuất hiện của host trong string pool**, không phải byte trên dây. Nhãn
trong phim ghi "host hits"; tài liệu này và `docs/FINDINGS.md` nói rõ. Muốn biết
lưu lượng thật cần chặn gói tin trên thiết bị.

**Hỏi: "6 thư viện bị bóc tên" có phải malware?**

Không có bằng chứng đó, và phim không nói vậy. Bằng chứng: tên 8 ký tự, bảng export
gần rỗng, payload chứa nhiều descriptor JNI, hai cặp thư viện phụ thuộc nhau. Đó là
hình dạng của một lớp bảo vệ mã (packer/VMP), một kỹ thuật phổ biến ở ứng dụng ngân
hàng và ví. Việc gán nhãn "độc hại" sẽ là suy đoán ý định — điều bị loại bỏ.

**Hỏi: `assets/trueidlib.so` 392 byte, entropy 7,50 — có phải payload ẩn?**

Có hai sự thật đo được: (1) nó không phải ELF, nên không thể `dlopen`; (2) entropy
7,50 bit/byte. Sự thật thứ ba — nó là gì — **không** được kết luận. Khả năng cao
nhất là một mảnh khoá/cấu hình được làm rối, nhưng phim chỉ nói "không phải ELF".

**Hỏi: Có tìm thấy bí mật nào không?**

Không có bí mật nào trong 243 786 chuỗi DEX: không Google API key, AWS key, PEM
private key, JWT, Stripe/Slack token. Ba tệp PEM tìm thấy là **public key**, dùng để
xác thực. Giới hạn: bí mật có thể nằm trong native payload đã mã hoá hoặc trong tài
nguyên nhị phân (font, ảnh) — những vùng chưa được quét.

**Hỏi: Cleartext traffic có phải lỗ hổng?**

Không tự thân. `cleartextTrafficPermitted="true"` là cấu hình mặc định của nhiều
app. Rủi ro thực tế phụ thuộc vào việc có endpoint nào thật sự dùng HTTP. Trong mẫu
host top, không có URL `http://` nào trỏ tới host first-party. Phim nêu nó như một
điểm cần theo dõi, không phải một lỗ hổng.

## Về sản phẩm phim

**Hỏi: Vì sao không dùng Remotion?**

Remotion phụ thuộc Chromium/Puppeteer; sandbox không tải được Chromium (TLS và
egress bị giới hạn). Pipeline được viết trực tiếp: scene là hàm thuần của thời gian
→ SVG → `resvg` → PNG → `libx264`. Kết quả tương đương về mặt kỹ thuật (frame-accurate,
tất định, render song song) và không cần headless browser.

**Hỏi: Âm thanh lấy từ đâu?**

Tổng hợp hoàn toàn bằng DSP trong `video/src/audio/mix.ts`: pad detuned-saw, bass
sine+saw, pluck FM, bell, kick có pitch-drop, riser nhiễu lọc, tick, air bed; reverb
Schroeder; sidechain theo kick; limiter `tanh`; chuẩn hoá EBU R128 hai lượt về
−16 LUFS / −3,4 dBTP. Không có sample, không có nhạc ngoài.

**Hỏi: Vì sao tiếng Việt hiển thị đúng?**

Google Fonts chia Be Vietnam Pro thành các subset `latin` và `vietnamese`; không
subset nào đủ cho câu tiếng Việt có cả chữ số Latinh. `video/tools/build_fonts.py`
hợp nhất hai subset cùng độ đậm thành một TTF và đổi tên family (`BVN`, `PlexMono`)
để renderer không chọn nhầm subset. Script kiểm tra 20 ký tự dấu tiếng Việt + 78 ký
tự Latinh trước khi ghi.

## Về "trích dẫn nhiều nhất trên Google Scholar"

Không thể bảo đảm, và dự án không tuyên bố vậy. Google Scholar lập chỉ mục các bài
báo có ISSN/DOI; một repository không tự động vào Scholar. Những gì dự án làm được:

* một artifact có thể trích dẫn (`docs/CITATION.cff`) với định danh SHA-256;
* mọi con số tái lập được (27 invariant trong CI);
* một sản phẩm trung thực về giới hạn của nó — điều kiện cần để được trích dẫn.

Muốn có trích dẫn học thuật thật, bước tiếp theo là gửi một bài báo (ví dụ tới
hội nghị về bảo mật di động) dùng dự án này làm artifact kèm theo.
