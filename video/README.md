# Zalopay Research Video — TypeScript + ffmpeg

Video nghiên cứu (MP4, 1920×1080, H.264 + AAC) được phát triển bằng **TypeScript**,
render bằng **ffmpeg-static** (drawtext, không cần Chrome/canvas).

## Cấu trúc

```
video/
  src/timeline.ts       # 8 slides: tiêu đề, bullets, narration tiếng Việt
  src/render.ts         # Render video silent (drawtext → H.264)
  src/render-audio.ts   # Mux TTS audio từng slide → MP4 cuối
  audio/                # 01-title.mp3 … 08-conclusion.mp3 (TTS tiếng Việt)
  zalopay-research.mp4  # Video silent
  zalopay-research-final.mp4  # Video cuối (video + âm thanh)
```

## Cách chạy

```bash
cd video
npm install
npm run build
npm run render          # → zalopay-research.mp4
npm run render:audio    # → zalopay-research-final.mp4 (cần audio/*.mp3)
```

## Âm thanh

Narration tiếng Việt được tổng hợp bằng TTS (generate_speech), mỗi slide một file
`audio/<slide-id>.mp3`, sau đó mux với `adelay` + `concat` và encode AAC 192 kbps / 48 kHz.

## Slides (tổng ~3 phút)

1. `01-title` — Zalopay 11.17.0, tổng quan XAPK
2. `02-xapk` — Cấu trúc 5 split APK
3. `03-dex` — 19 DEX, multidex, obfuscation
4. `04-components` — 212 Activities, 37 Services, 39 Permissions
5. `05-stack` — Kotlin, Compose, Dagger, Firebase, ML Kit, Griver, Visa, TrueID
6. `06-native` — 28 thư viện .so, SQLCipher, obfuscation native
7. `07-security` — Điểm mạnh & điểm cần giám sát
8. `08-conclusion` — Kết luận + tham khảo (rea, OWASP MASVS)

## Yêu cầu

- Node.js ≥ 20, font DejaVu (`/usr/share/fonts/truetype/dejavu/`)
- `ffmpeg-static` cung cấp binary ffmpeg qua npm (không cần cài hệ thống)

## Đạo đức

Video chỉ trình bày kết quả phân tích tĩnh ở mức cấu trúc (manifest, thành phần,
thư viện, kiến trúc). Không công bố khóa, token, endpoint nội bộ hay logic thanh toán.
