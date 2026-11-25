# Video học thuật — Zalopay 11.17.0 (TypeScript → MP4)

Pipeline TypeScript thuần: **SVG slides → PNG (sharp) → MP4 H.264/AAC (ffmpeg)**,
kèm phụ đề SRT và poster. Mọi con số lấy từ `docs/analysis.json`.

## Yêu cầu

- Node 20+, `npm install` (sharp + typescript; ffmpeg qua `imageio-ffmpeg` hoặc `FFMPEG_PATH`)
- Audio lồng tiếng: `assets/audio/scene-01.mp3` … `scene-10.mp3` (TTS tiếng Việt).
  Thiếu audio → video dùng track im lặng, SRT vẫn đầy đủ (ghi rõ trong log build).

## Build

```bash
cd video
npm install
npm run build
# → out/zalopay-reverse-engineering.mp4
# → out/zalopay-reverse-engineering.srt
# → out/poster.png + out/SHA256.txt
```

## Cấu trúc

- `src/scenes.ts` — 10 scenes (tiêu đề, bullets, charts, narration)
- `src/render.ts` — renderer SVG 1920×1080 (DejaVu Sans, hỗ trợ tiếng Việt)
- `src/build.ts` — sharp + ffmpeg orchestration, SRT, SHA-256

## Lưu ý trích dẫn

Video không thể đảm bảo số trích dẫn. Hãy xuất bản kèm `docs/paper.md`,
`docs/references.bib`, `CITATION.cff` và deposit Zenodo để tối đa hoá khả năng
được lập chỉ mục/trích dẫn một cách trung thực.
