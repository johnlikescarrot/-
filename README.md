# Bên trong một bản phân phối Android

Nghiên cứu tình huống có thể kiểm tra về mẫu **Zalopay 11.17.0** được cung cấp, kèm phim đồ họa tiếng Việt dựng bằng TypeScript. Đây là **phân tích tĩnh**, không phải kiểm toán toàn diện mọi hành vi, chứng nhận an toàn hay nghiên cứu đã bình duyệt. Không cài ứng dụng, đăng nhập, gửi giao dịch hoặc đo lưu lượng thật.

## Kết quả

- [Báo cáo và tài liệu tham khảo](research/report.md) • [giao thức](docs/protocol.md) • [BibTeX](research/references.bib) • [gợi ý trích dẫn](CITATION.cff)
- [Tổng quan kiểm kê](research/inventory-summary.json) • [danh mục SHA-256 từng lượt tệp](research/inventory.jsonl.gz)
- [Phân tích Android/DEX/ELF](research/android-analysis.json) • [chữ ký](research/signatures.json) • [đối chứng âm](research/signature-negative-control.json) • [giới hạn công cụ](research/tool-limitations.json)
- Phim MP4, phụ đề và timeline được tạo trong `deliverables/`; mã phim, kịch bản và ghi công ở [`video/`](video/README.md).

Mẫu ZIP gốc có SHA-256 `34b9b6b6735f0f739617f97a259f931d17cdbacb51871c40b066de41f4b4610a`. Tên tệp/nguồn phân phối không chứng minh tính chính thức; cùng chứng thư và chữ ký hợp lệ không xác thực được danh tính nhà phát hành nếu thiếu một nguồn khóa tin cậy độc lập. Không thể bảo đảm số lượt trích dẫn hay việc được Google Scholar lập chỉ mục.

## Tái tạo phân tích

Linux x86-64, Python 3.11+ và Node 24 được khuyến nghị. Công cụ đọc mẫu như dữ liệu không tin cậy, không thực thi nó. Các giới hạn và định dạng mà bộ kiểm kê không hỗ trợ được ghi trong báo cáo. Chạy trong thư mục gốc của dự án:

```bash
SAMPLE='Zalopay-Thanh+toán+&+Tài+chính_11.17.0_APKPure.zip'
python3 -m venv .venv
.venv/bin/pip install -r requirements-analysis.txt
python3 scripts/inventory.py "$SAMPLE" --out research
.venv/bin/python scripts/analyze_android.py "$SAMPLE"
.venv/bin/python -m unittest discover -s tests -v
```

Để tái tạo phép kiểm tra chữ ký, ví dụ sau sử dụng **JAR apksigner được đóng gói bởi một gói npm cộng đồng**, không phải bản tải trực tiếp từ Android SDK. Dấu băm cố định giúp nhận diện chính xác JAR đã dùng, nhưng không tự xác thực nguồn của nó. Có thể thay bằng apksigner từ Android SDK đáng tin cậy để đối chiếu độc lập.

```bash
mkdir -p .work/npmpacks .work/apksigner
npm pack --silent --pack-destination .work/npmpacks @postar/apktool-node@0.3.4
tar xzf .work/npmpacks/postar-apktool-node-0.3.4.tgz -C .work/apksigner --strip-components=2 package/lib/apksigner.jar
echo 'eefdd6aed9db9fb849e4c98a50d8741e19d1b674ba6547220bcb9c3ed152123a  .work/apksigner/apksigner.jar' | sha256sum -c -
JHOME=$(.venv/bin/python -c 'import jdk4py; print(jdk4py.JAVA_HOME)')
.venv/bin/python scripts/verify_signatures.py "$SAMPLE" --java "$JHOME/bin/java" --apksigner "$PWD/.work/apksigner/apksigner.jar"
.venv/bin/python scripts/negative_control.py "$SAMPLE" --java "$JHOME/bin/java" --apksigner "$PWD/.work/apksigner/apksigner.jar"
```

Để bổ sung lại kết quả REA 6.10.1 (cần Node tương thích; kết quả REA là **digest tự nhất quán**, không phải kiểm tra mật mã chữ ký):

```bash
npm install --prefix .work/node node@24.22.3
export PATH="$PWD/.work/node/node_modules/node/bin:$PATH"
npm install --prefix .work/rea rea-agents@6.10.1
.venv/bin/python scripts/verify_signatures.py "$SAMPLE" --java "$JHOME/bin/java" \
  --apksigner "$PWD/.work/apksigner/apksigner.jar" --rea "$PWD/.work/rea/node_modules/.bin/rea"
```

Bản ghi `research/signatures.json` được tạo với tùy chọn `--rea`; chạy không có tùy chọn này sẽ ghi lại tệp mà không có phần REA. Các tệp công cụ và bản sao tạm nằm trong `.work/`, không được đưa vào Git. Để kiểm tra riêng lớp QR bằng JADX 1.1.0: tải `@mishguru/jadx-node@3.1.0` bằng `npm pack` (SHA-256 tarball `1521a7a2d64f399cd5a3926a2ae363e4cdc5a81619fbe94a3f3f5c7d3c0263c7`), lấy thư mục `resources/jadx-1.1.0`, rồi chạy `jadx -r -j 1 --single-class vn.com.vng.zalopay.shareqr.presentation.QRReceiverActivity -d <output> <base.apk>` với `JAVA_HOME=$JHOME`. Lần chạy của chúng tôi báo một lỗi; mã giả chỉ được dùng làm bằng chứng chọn lọc.

## Tái tạo phim

```bash
npm ci
npm run typecheck
npm run check:video
npm run preview -- 0       # một khung xem thử trong .work/
npm run render             # cần 10 clip lời dẫn trong video/audio/
```

Trình dựng ghép lời dẫn với nhạc nền tổng hợp gốc, viết phụ đề SRT có thời điểm **ước lượng** theo độ dài câu, rồi mã hóa H.264/AAC trong MP4 và nhúng thêm phụ đề tiếng Việt. Khung đồ họa 1280×720 được phóng lên 1920×1080 khi xuất. Có thể đặt `FFMPEG_PATH` trỏ tới FFmpeg có libx264 nếu không dùng `imageio-ffmpeg` trong `.venv`. Xem [`video/README.md`](video/README.md) để biết nguồn tư liệu và quy trình âm thanh.

Mọi kết quả được đồng bộ trên PR của nhánh hiện tại; không tự động hợp nhất.
