# Giải phẫu một ví điện tử

### Phân tích tĩnh toàn bộ artifact ZaloPay 11.17.0 (XAPK)

**Bản phát hành phim:** 4 phút 04, 1920×1080, H.264 High@4.1, 30 fps, AAC 48 kHz.
**Artifact:** `Zalopay-Thanh+toán+&+Tài+chính_11.17.0_APKPure.zip` — 98 813 878 byte,
SHA-256 `34b9b6b6735f0f73…`.
**Phạm vi:** 6 464 entry, 19 DEX, 27 thư viện ELF, 0 lần chạy ứng dụng.

---

## Một câu

> Một chiếc ví điện tử là một hệ thống phòng thủ nhiều lớp, trong đó chỉ 1,6% bytecode
> là mã của chính nó; phần còn lại là hệ sinh thái SDK, và những chỗ thú vị nhất
> nằm ở ranh giới giữa hai phần đó.

## Bốn con số

| | |
|---|---|
| **84,5%** | lưu lượng trỏ về hạ tầng first-party (1 370 URL duy nhất) |
| **85,2%** | thư viện native hardened đầy đủ (23/27) |
| **1,6%** | bytecode thuộc `vn.com.vng.zalopay` (952/60 671 class) |
| **0** | khoá bí mật lộ trong 243 786 chuỗi DEX |

## Cấu trúc phim

| # | Chương | Nội dung |
|---|---|---|
| 00 | Mở đầu | vật chứa như một đối tượng vật lý |
| 01 | Luận đề | câu hỏi, vật liệu, công cụ, giới hạn |
| 02 | Vật chứa | ZIP → XAPK → 5 APK; khoảng trống arm64 |
| 03 | Bề mặt | manifest, 49 quyền, 30 component exported |
| 04 | Bytecode | treemap 19 DEX, hệ sinh thái SDK |
| 05 | Native | hardening, họ thư viện bị bóc tên, JNI surface |
| 06 | Mạng | 1 370 URL, 84,5% first-party, cleartext trong NSC |
| 07 | Năng lực | 14 chủ đề, census chuỗi |
| 08 | Nội dung thật | 6 464 magic byte, `trueidlib.so` không phải ELF |
| 09 | Phương pháp | pipeline 6 script, 4 giới hạn nêu trước |
| 10 | Kết | kết luận và khối trích dẫn |

## Cách tái lập

```bash
# 1. phân tích (Python 3.11 + androguard)
python3 -m venv .venv
.venv/bin/pip install androguard fonttools brotli numpy imageio-ffmpeg
unzip -o "Zalopay-Thanh+toán+&+Tài+chính_11.17.0_APKPure.zip" -d work
mv work/*.xapk work/payload.xapk && unzip -o work/payload.xapk -d work/xapk
for s in analysis/tools/0*.py; do .venv/bin/python "$s"; done

# 2. phim (Node 22 + TypeScript)
cd video && npm ci
npm run audio     # tổng hợp score.wav bằng DSP
npm run render    # SVG → PNG → H.264
```

## Cấu trúc repository

```
analysis/tools/      6 script phân tích, mỗi script một giai đoạn
analysis/tools/dexlib.py   parser DEX + ELF + APK Signing Block (không phụ thuộc)
data/                đầu ra JSON — nguồn duy nhất cho mọi con số trong phim
data/dex_strings/    string pool 19 DEX, giải mã sẵn
video/src/           engine TypeScript: scene, component, mix âm thanh
video/tools/         build font tiếng Việt từ subset Google Fonts
docs/                METHOD.md (cách làm) · FINDINGS.md (kết quả + giới hạn)
```

## Nguyên tắc

1. **Bằng chứng khép kín.** Mỗi con số trong phim đọc từ `data/*.json`; mỗi trường
   trong JSON sinh từ byte gốc.
2. **Giới hạn được nêu trước.** Mọi kết luận đi kèm điều không thể suy ra.
3. **Không công bố bí mật.** Giá trị nghi vấn chỉ được lưu dưới dạng
   `{prefix, length, sha256}`.
4. **Không suy đoán ý định.** "Có khả năng" không đồng nghĩa với "đang làm".

## Ghi công công cụ

Phương pháp lấy cảm hứng từ [morluto/rea](https://github.com/morluto/rea)
(evidence-first reverse engineering). REA cần engine native (Hopper/Ghidra/IDA)
không cài được trong môi trường này, nên lớp bằng chứng tương đương được cài đặt
trực tiếp trong `analysis/tools/`.

Thư viện dùng: `androguard` (chỉ giải mã AXML), `fonttools`, `@resvg/resvg-js`,
`ffmpeg`/`libx264`. Âm thanh được tổng hợp hoàn toàn bằng DSP trong repo.
