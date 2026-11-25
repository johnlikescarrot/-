# ZaloPay 11.17.0 — nghiên cứu dịch ngược tĩnh & phim tài liệu kỹ thuật

Kho này chứa một nghiên cứu **tĩnh, có thể tái lập** về toàn bộ nội dung của
`Zalopay-Thanh+toán+&+Tài+chính_11.17.0_APKPure.zip` (một gói XAPK gồm APK cơ sở
và bốn APK cấu hình), cùng một đường ốngvideo được viết bằng TypeScript để biến
bằng chứng thành phim tài liệu MP4 có thuyết minh.

## Phạm vi và đạo đức

- **Chỉ phân tích tĩnh.** Không cài đặt, không thực thi mã của ứng dụng, không
  kết nối tới máy chủ của bên thứ ba, không thu thập dữ liệu người dùng, không
  bẻ khóa/khử bảo mật và không tái sử dụng mã hay tài sản thương hiệu của ứng
  dụng vào sản phẩm khác.
- Mục tiêu là **minh bạch kỹ thuật**: mô tả cấu trúc gói, bề mặt quyền, kiến
  trúc DEX/native và tư thế ký số để phục vụ giáo dục và nghiên cứu bảo mật.
- Mọi kết luận được gắn với tệp bằng chứng JSON trong `analysis/evidence/` và
  được giới hạn bởi những gì có thể quan sát được từ tệp nhị phân.
- Không đưa ra tuyên bố về hành vi runtime, máy chủ, dữ liệu người dùng hay các
  lỗ hổng chưa được xác minh. Không trích xuất hay đăng tải bí mật, khóa hay
  thông tin cá nhân.

> **Về trích dẫn:** không dự án hay tác giả nào có thể đảm bảo số lượt trích dẫn
> trên Google Scholar. Những gì có thể kiểm soát được là chất lượng phương pháp,
> dữ liệu công khai có thể kiểm chứng, siêu dữ liệu trích dẫn đầy đủ và khả năng
> tái lập — những yếu tố đó được ưu tiên ở đây.

## Cấu trúc

```
analysis/
  requirements.txt                 phụ thuộc Python cho bộ thu thập bằng chứng
  scripts/collect_evidence.py      thu thập bằng chứng tĩnh từ ZIP/XAPK/APK
  evidence/container.json          thực thể, kích thước, CRC-32, SHA-256 của từng tệp
  evidence/manifest.json           manifest nhị phân đã giải mã, quyền, thành phần
  evidence/signature.json          danh tính chứng thư v2/v3 (siêu dữ liệu công khai)
  evidence/dex.json                19 DEX: số lượt, ký hiệu framework, host URI
  evidence/native.json             27 thư viện ELF ARMv7 + phụ thuộc DT_NEEDED
  evidence/assets.json            124 tài sản, danh sách .properties của thư viện
  evidence/rea/                    bằng chứng REA 6.7.0 (inspect-artifact, Flutter)
  evidence/summary.json            tóm tắt hợp nhất, dùng làm nguồn dữ liệu video
  REPORT.md                        báo cáo nghiên cứu + phương pháp + hạn chế
video/
  package.json, tsconfig.json      dự án TypeScript
  src/                             bộ render hoạt hình, bố cục, âm thanh, dựng phim
  script/                          kịch bản thuyết minh có trích dẫn nguồn
  output/zalopay-reverse-engineering.mp4   kết quả MP4
```

## Tái lập phân tích

```bash
python3 -m venv .venv && . .venv/bin/activate
pip install -r analysis/requirements.txt
# Giải nén XAPK từ ZIP vào một thư mục tạm (không commitartifact lớn):
mkdir -p .work/extracted
python3 - <<'PY'
import glob, zipfile
z = zipfile.ZipFile(glob.glob('*.zip')[0]); z.extractall('.work')
zipfile.ZipFile(glob.glob('.work/*.xapk')[0]).extractall('.work/extracted')
PY
python3 analysis/scripts/collect_evidence.py \
  --xapk "$(echo .work/*.xapk)" --outer-zip "$(echo *.zip)" --out analysis/evidence
```

Công cụ REA ([morluto/rea](https://github.com/morluto/rea)) được dùng ở chế độ
CLI để kiểm kê artifact và nhận diện Flutter:

```bash
npm install rea-agents
npx rea inspect-artifact <đường-dẫn-tuyệt-đối/apk> --json
npx rea identify-flutter-build <đường-dẫn-tuyệt-đối/apk> --json
```

`inspect_signature` trong REA 6.7.0 chỉ hỗ trợ macOS codesign; chữ ký APK vì thế
được trích xuất bằng androguard và ghi trong `evidence/signature.json`.

## Tái lập video

```bash
cd video && npm install && npm run build && npm run render
```

Xem `video/README.md` để biết chi tiết kịch bản, nguồn giọng nói và cờ dựng phim.
