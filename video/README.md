# Research film — TypeScript documentary engine → MP4

`ZaloPay 11.17.0 — Anatomy of a Payment Super-App`: a 9-scene (~6 min),
1280×720 @ 24 fps research documentary renderedentirely from this repo's
evidence by a zero-native-dependency TypeScript pipeline.

## Watch

- `out/film.mp4` — H.264 + AAC 48 kHz stereo, chapters embedded, faststart on
- `out/narration.srt` / `out/narration.vtt` — full subtitles
- `out/TRANSCRIPT.md` — scene-by-scene transcript with timecodes
- `out/poster.png` — title frame
- `out/stats.json` — timeline, per-scene durations, render stats

## How it renders

| Stage | Module | Technique |
|---|---|---|
| Frames | `src/gfx.ts` | Hand-rolled RGBA rasterizer + authored 5×7 bitmap font; no canvas/font deps |
| Chapters | `src/scenes.ts` | 9 data-driven scenes reading `../reports/raw/*.json` at render time |
| Narration | `tools/speak.js` + `mespeak` | Offline eSpeak-class TTS, one fresh process per scene, validated takes |
| Score | `src/audio.ts` | Synthesized 96 BPM A-minor score: detuned saw pads, sub bass, arp, drums, impacts |
| Mix/master | `src/audio.ts` | Generated-room reverb, narration ducking, tanh limiter, −1 dBFS, fades |
| Mux | `src/render.ts` | Raw RGBA piped to ffmpeg (`libx264`/`aac`), chapters injected stream-copy |

## Reproduce

```bash
cd video
npm install            # typescript, pngjs, mespeak, @types/node
pip install imageio-ffmpeg   # provides a static ffmpeg binary
npx tsc
FFMPEG_BIN=$(python3 -c "import imageio_ffmpeg; print(imageio_ffmpeg.get_ffmpeg_exe())") \
  node dist/render.js
# → out/film.mp4 (+ srt/vtt/transcript/poster/stats)
```

Render takes ~2–3 min on 2 CPUs. Numbers on screen are loaded live from
`reports/raw/` and `evidence/`, so any re-analysis automatically updates the film.

## Audio design

48 kHz / 24-bit-equivalent float master path, narration-centered with sidechained
music bed (−80% under voice), chapter impacts whoosh→boom→shimmer, LUFS-style
peak normalization with soft clipping. Voice is a clearly-synthetic offline
narrator (documented limitation); the transcript and SRT allow a human voiceover
drop-in without re-editing.
