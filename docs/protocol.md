# Giao thức và ranh giới nghiên cứu

## Câu hỏi

Bản phân phối được cung cấp chứa những thành phần gì? Những cấu hình, quyền, mã và tài nguyên nào có thể quan sát từ các tệp? Điều gì **không** thể suy ra từ một phép phân tích tĩnh?

## Nguyên tắc

1. Bảo toàn tệp gốc, ghi kích thước và SHA-256 của các lớp chứa; kiểm kê các mục và dấu băm của từng tệp, với đường dẫn đầy đủ qua các lớp ZIP/XAPK/APK.
2. Đọc dữ liệu như dữ liệu không tin cậy: không chạy mã, không đăng nhập, không tương tác với dịch vụ thanh toán và không giải nén đường dẫn không kiểm soát. Giới hạn việc phân tích các lớp lồng nhau.
3. Tách **khai báo trong manifest**, **chuỗi/hiện vật có trong tệp** và **hành vi quan sát khi chạy**. Quyền được khai báo không chứng minh quyền được cấp hoặc đang sử dụng; tên miền/chuỗi không chứng minh kết nối thực tế.
4. Nêu xuất xứ, phương pháp, phiên bản công cụ, bằng chứng theo đường dẫn và các giới hạn; không công bố thông tin nhạy cảm không cần thiết. Không suy diễn lỗ hổng hay đánh giá an toàn tổng thể từ chỉ một chỉ báo.
5. Chữ ký: sự hiện diện của tệp chữ ký hoặc chứng thư không đồng nghĩa xác minh mật mã thành công; ngay cả chữ ký hợp lệ cũng cần so sánh với nguồn khóa đáng tin cậy để quy kết nhà phát hành.
6. Mục tiêu là kết quả có thể kiểm tra và trích dẫn; số lượt trích dẫn, bình duyệt và xếp hạng Google Scholar không thể bảo đảm.

## Phạm vi dự kiến

Kiểm kê toàn bộ tệp trong các lớp ZIP/XAPK/APK có thể đọc; phân tích manifest Android và cấu hình mạng; thống kê DEX, thư viện native, tài nguyên, chứng thư và dấu hiệu công nghệ; đối chiếu có chọn lọc tài liệu Android/OWASP và công trình khoa học. Phân tích tĩnh không bao quát mã tải từ xa, phản chiếu, mã được mã hóa, hành vi máy chủ, mọi đường thực thi hoặc mọi khả năng của mã native. Nếu bước nào không thể thực hiện, báo cáo phải nói rõ thay vì coi là đã hoàn tất.
