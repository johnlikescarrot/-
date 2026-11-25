# Video source and build

`generate.ts` is the complete TypeScript source for the storyboard frames, a deterministic synthesized stereo music bed, and the MP4 encoder invocation. It reads `research/output/findings.json`, so regenerate the static-analysis report first when the underlying input changes.

## Build environment

- Node.js 22+
- `npm ci`
- The npm-provided FFmpeg installer binary (locked in `package-lock.json`)

From the repository root:

```bash
cd video
npm ci
npm run build
```

The result is `deliverables/Zalopay_Static_Reverse_Engineering.vi.mp4`: 88 seconds, 1920×1080, 24 fps, H.264 High/yuv420p, AAC-LC stereo at 48 kHz with EBU R128 loudness normalization (−18 LUFS target). The score is generated from additive synthesis in the TypeScript source; the film intentionally has no voice-over. PNG frame renders and the temporary WAV live under ignored/intermediate directories; the source storyboards are SVG.

The FFmpeg Linux x64 binary and Sharp native dependency are build-time tools installed from the pinned npm dependency lock. Their upstream licenses should be retained when redistributing those tools separately. This repository does not bundle the build tool binaries.
