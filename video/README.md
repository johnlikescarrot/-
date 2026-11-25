# Phim nghiên cứu: thiết kế và tái tạo

## Cấu trúc

`script.json` là kịch bản lời dẫn và nguồn tại 10 chương. `validate.ts` kiểm tra các con số hiển thị so với những tệp bằng chứng trước khi dựng. `render.ts` dùng TypeScript và Canvas để tạo đồ họa, chuyển cảnh và chuyển động; FFmpeg ghép video H.264, âm thanh AAC và phụ đề `mov_text` vào MP4. `music.py` tổng hợp nhạc ambient stereo gốc từ sóng điều hòa, xung và nhiễu có seed cố định, không sử dụng bản ghi âm thương mại. Âm thanh lời dẫn được trộn với nhạc, lọc và chuẩn hóa bằng FFmpeg.

Tệp `deliverables/subtitles.vi.srt` và track phụ đề trong MP4 chia câu theo tỷ lệ số ký tự so với thời lượng từng clip: **thời điểm chỉ là ước lượng**, không phải alignment từ nhận dạng giọng nói. `deliverables/timeline.json` ghi thời gian chương và clip thực tế để dễ hiệu chỉnh. `script.json` cũng là bản chép lời đầy đủ.

## Dựng

Cài môi trường như ở README gốc, chạy `npm ci`, rồi đặt 10 clip MP3 lời dẫn tương ứng vào `video/audio/01.mp3` đến `video/audio/10.mp3`. Các clip hoàn thành sẽ được lưu cùng dự án để không cần gọi dịch vụ giọng nói khi dựng lại.

```bash
npm run typecheck
npm run check:video
npm run preview -- 0       # chỉ số 0–9; ghi PNG vào .work/
npm run render             # deliverables/zalopay-evidence-film.mp4
```

Nếu dùng FFmpeg ngoài, đặt `FFMPEG_PATH=/path/to/ffmpeg`; bản dựng cần `libx264`, `aac` và `mov_text`. Có thể dùng `--audio-dir DIR --out FILE --quick` để kiểm thử nhanh ở 720p/12 fps; bản thông thường xuất 1080p/24 fps, với đồ họa cơ sở 720p được upscale. Đầu ra và thời gian phụ thuộc phiên bản FFmpeg, bộ font, kiến trúc và các clip; việc băm nguyên vẹn video đầu ra không được hứa là giống nhau trên mọi hệ thống.

## Nguồn và ghi công

- Lời dẫn do hệ thống tổng hợp giọng nói tạo từ `script.json`; đây không phải bản ghi một người thật hay tiếng nói của nhà phát hành.
- `assets/forensic-hero.png` là hình minh họa khái niệm do AI tạo cho dự án, **không phải ảnh chụp màn hình** của ứng dụng được phân tích.
- Các đồ họa, nhạc nền thuật toán và văn bản bằng chứng được tạo cho dự án này. Không sử dụng logo hay âm thanh từ APK trong phim.
- Be Vietnam Pro và IBM Plex Mono được lấy từ [Google Fonts](https://github.com/google/fonts), giấy phép SIL Open Font License đi kèm trong `assets/fonts/`.
- Các nguồn học thuật và tài liệu Android/OWASP nằm trong `research/report.md` và `research/references.bib`. Hình minh họa không phải bằng chứng về cấu trúc thực tế; các sơ đồ dựa trên kết quả phân tích đã ghi nguồn.
