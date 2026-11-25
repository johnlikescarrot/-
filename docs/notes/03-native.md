# Ghi chú lớp 3 — Native

- 27 thư viện trong `config.armeabi_v7a.apk`, 1 trong `assets/` (`trueidlib.so`).
- `liblytcuhe.so` và `librcmefqonnx.so`: cùng 178,628 byte, cùng bảng export, khác 8,799 byte ở `.text` → hai bản alias của cùng runtime.
- `libsecurity_lite.so` export đúng 4 hàm `Java_com_alipay_plus_security_lite_SecurityGuardLiteNative_*`.
- `libtrueidlib.so` chứa `OCRA-1:HOTP-SHA256-8:QH64` và các hàm `SmartOTPHelper_vault*` (kiểm tra debugger/integrity).
- `libohxbee.so` tham chiếu `Lvn/ai/faceauth/sdk/.../Liveness*` → liveness chạy native.
- Hardening đạt mức NDK mặc định; không thấy obfuscator thương mại (OLLVM/VMProtect) trong bảng export.

## Không kết luận
Không decompile thân hàm; không suy đoán thuật toán chấm rủi ro của Security Guard.
