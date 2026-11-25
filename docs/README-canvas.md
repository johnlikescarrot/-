# Bộ tài liệu học thuật — gói xuất bản

Thư mục này chứa **ba lớp tài liệu** cho cùng một nghiên cứu, mỗi lớp phục vụ một
cách đọc khác nhau. Mọi con số trong cả ba lớp đều sinh từ `research/data/*.json`.

| Tệp | Vai trò | Cách dùng |
|---|---|---|
| `REPORT.md` | Báo cáo kỹ thuật đầy đủ (tiếng Việt, 15 mục + 3 phụ lục) | đọc trực tiếp trên GitHub / IDE |
| `ATLAS.md` | Bản đồ một trang: mọi tầng trên một màn hình logic | tra cứu nhanh khi đọc code |
| `notes/` | Ghi chú theo từng lớp (container, code, native, integrity) | trích dẫn từng phần |
| `../../artifacts/*.mp4` | Phim nghiên cứu 4:36, 1080p30, có phụ đề SRT/VTT | trình chiếu, giảng dạy |

## Nguyên tắc trích dẫn

Trích dẫn **báo cáo** khi nói về số liệu; trích dẫn **phim** khi nói về cách diễn
giải; trích dẫn **dữ liệu JSON** khi cần tái lập. Không trích dẫn ảnh chụp màn hình
rời rạc — mọi hình đều tái sinh được bằng `npm run render`.

## Giới hạn cần nêu khi trích dẫn

- Chỉ phân tích tĩnh; không có bằng chứng hành vi hay lưu lượng mạng.
- Một phiên bản (11.17.0) và một ABI (`armeabi-v7a`) từ một mirror cụ thể.
- Phán quyết chữ ký là "không xác minh được" cho **tệp này**, không phải cho mọi bản phát hành.
