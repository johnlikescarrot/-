# ZaloPay 11.17.0 — Academic Video (TypeScript pipeline)

**Output:** `out/zalopay-re-analysis.mp4` (1280×720, 30 fps, H.264 + AAC 128 kbps, Vietnamese narration + subtitles)

## Architecture

```
src/scenes.ts  — 14 scenes (title, bullets, chart data, narration script)
src/svg.ts     — SVG slide generator (DejaVu Sans, Vietnamese-safe)
src/render.ts  — SVG → PNG via sharp → frames/*.png + narration.json + subtitles.srt
src/mux.ts     — PNG + audio/*.mp3 → MP4 via ffmpeg (concat segments)
```

## Build

```bash
cd video
npm install
npm run render   # generates frames/, narration.json, subtitles.srt
# Place narration MP3s in audio/ (01-title.mp3 … 14-conclusion.mp3)
#   — generated via Arena TTS (add_voice + generate_speech), Vietnamese
npm run mux      # assembles out/zalopay-re-analysis.mp4
# or: npm run all
```

Requires: Node 22+, `sharp` (prebuilt), ffmpeg. In sandbox, ffmpeg comes from
`pip install imageio-ffmpeg` (`/usr/local/lib/python3.11/dist-packages/imageio_ffmpeg/binaries/ffmpeg-*`).
Set `$FFMPEG` to override.

## Narration

Vietnamese academic narration, ~45 s/scene, 14 scenes ≈ 10 min.
Script: `narration.txt` (auto-generated from `src/scenes.ts`).
Audio: `audio/*.mp3` (128 kbps). Without audio, `mux` emits 12 s silent
placeholders so the pipeline is testable end-to-end.

## Citation

> ZaloPay 11.17.0 XAPK Reverse Engineering Study (ZP-RE-2026-11-17.0).
> Video: `out/zalopay-re-analysis.mp4`. Report: `docs/REVERSE_ENGINEERING_REPORT.md`.
> Repo: https://github.com/johnlikescarrot/-
