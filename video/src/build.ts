// Zalopay study video builder (TypeScript).
// Pipeline: script.ts (narration + scenes) -> frames (PNG) -> audio (TTS wav) -> MP4 via ffmpeg.
// This stub is replaced by the full renderer in P5; kept minimal so `npm run build` works early.
import { mkdirSync, writeFileSync } from "node:fs";
mkdirSync("out", { recursive: true });
writeFileSync("out/README.txt", "Video build placeholder — see docs/00-PLAN.md P5.\n");
console.log("[video] placeholder build ok");
