/**
 * Mux per-slide TTS audio (audio/01-title.mp3 ...) into final MP4.
 * Usage: node dist/render-audio.js
 * Expects: zalopay-research.mp4 (silent) + audio/*.mp3 in slide order.
 * Output: zalopay-research-final.mp4 (H.264 + AAC)
 */
import { execFileSync } from "node:child_process";
import { existsSync, readdirSync } from "node:fs";
import { dirname, join } from "node:path";
import { fileURLToPath } from "node:url";
import ffmpegPath from "ffmpeg-static";
import { SLIDES } from "./timeline.js";

const __dirname = dirname(fileURLToPath(import.meta.url));
const ROOT = join(__dirname, "..");
const SILENT = join(ROOT, "zalopay-research.mp4");
const AUDIO_DIR = join(ROOT, "audio");
const FINAL = join(ROOT, "zalopay-research-final.mp4");

function main() {
  if (!ffmpegPath || !existsSync(ffmpegPath)) {
    console.error("ffmpeg-static not found. Run: npm install");
    process.exit(1);
  }
  if (!existsSync(SILENT)) {
    console.error(`Silent video not found: ${SILENT}. Run npm run render first.`);
    process.exit(1);
  }
  const files = SLIDES.map((s) => join(AUDIO_DIR, `${s.id}.mp3`));
  const missing = files.filter((f) => !existsSync(f));
  if (missing.length) {
    console.error("Missing audio files:\n" + missing.join("\n"));
    console.error("Generate TTS audio first (see README).");
    process.exit(1);
  }
  // Concat audio with adelay per slide start time
  let t = 0;
  const inputs: string[] = ["-y", "-i", SILENT];
  const filters: string[] = [];
  const labels: string[] = [];
  SLIDES.forEach((s, i) => {
    inputs.push("-i", files[i]);
    filters.push(`[${i + 1}:a]adelay=${Math.round(t * 1000)}|${Math.round(t * 1000)},apad[a${i}]`);
    labels.push(`[a${i}]`);
    t += s.durationSec;
  });
  filters.push(`${labels.join("")}concat=n=${SLIDES.length}:v=0:a=1[aout]`);
  const args = [
    ...inputs,
    "-filter_complex", filters.join(";"),
    "-map", "0:v",
    "-map", "[aout]",
    "-c:v", "copy",
    "-c:a", "aac",
    "-b:a", "192k",
    "-ar", "48000",
    "-shortest",
    "-movflags", "+faststart",
    FINAL,
  ];
  console.log(`Muxing ${files.length} audio clips -> ${FINAL}`);
  execFileSync(ffmpegPath, args, { stdio: "inherit" });
  console.log("Done.");
}

main();
