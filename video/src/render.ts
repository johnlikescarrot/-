/**
 * Render slides to MP4 using ffmpeg-static + drawtext.
 * No Chrome / canvas needed: ffmpeg draws text directly with DejaVu fonts.
 * Output: video/zalopay-research.mp4 (silent, 1920x1080, H.264)
 */
import { execFileSync } from "node:child_process";
import { existsSync, mkdirSync } from "node:fs";
import { dirname, join } from "node:path";
import { fileURLToPath } from "node:url";
import ffmpegPath from "ffmpeg-static";
import { SLIDES, VIDEO_TITLE, VIDEO_SUBTITLE } from "./timeline.js";

const __dirname = dirname(fileURLToPath(import.meta.url));
const ROOT = join(__dirname, "..");
const OUT = join(ROOT, "zalopay-research.mp4");
const FONT = "/usr/share/fonts/truetype/dejavu/DejaVuSans-Bold.ttf";
const FONT_REG = "/usr/share/fonts/truetype/dejavu/DejaVuSans.ttf";

function esc(s: string): string {
  // ffmpeg drawtext escaping: \ : ' % and newlines
  return s.replace(/\\/g, "\\\\").replace(/:/g, "\\:").replace(/'/g, "\\'").replace(/%/g, "\\%").replace(/\n/g, "\\n");
}

function wrap(text: string, maxLen: number): string[] {
  const words = text.split(" ");
  const lines: string[] = [];
  let cur = "";
  for (const w of words) {
    if ((cur + " " + w).trim().length > maxLen && cur) {
      lines.push(cur);
      cur = w;
    } else {
      cur = (cur + " " + w).trim();
    }
  }
  if (cur) lines.push(cur);
  return lines;
}

function slideFilter(slide: (typeof SLIDES)[number], start: number): string {
  const parts: string[] = [];
  // Background: dark gradient simulation via color + vignette boxes
  parts.push(`drawbox=x=0:y=0:w=1920:h=1080:color=0x0B1020:t=fill:enable='between(t,${start},${start + slide.durationSec})'`);
  parts.push(`drawbox=x=0:y=0:w=1920:h=12:color=0x00C2FF:t=fill:enable='between(t,${start},${start + slide.durationSec})'`);
  parts.push(`drawbox=x=80:y=980:w=1760:h=4:color=0x1E2A44:t=fill:enable='between(t,${start},${start + slide.durationSec})'`);
  // Title
  parts.push(`drawtext=fontfile=${FONT}:text='${esc(slide.title)}':fontsize=72:fontcolor=white:x=80:y=90:enable='between(t,${start},${start + slide.durationSec})'`);
  // Subtitle
  parts.push(`drawtext=fontfile=${FONT_REG}:text='${esc(slide.subtitle)}':fontsize=34:fontcolor=0x8EC5FF:x=80:y=180:enable='between(t,${start},${start + slide.durationSec})'`);
  // Bullets
  let y = 280;
  for (const b of slide.bullets) {
    const lines = wrap(b, 62);
    for (let i = 0; i < lines.length; i++) {
      const prefix = i === 0 ? "•  " : "    ";
      parts.push(`drawtext=fontfile=${FONT_REG}:text='${esc(prefix + lines[i])}':fontsize=32:fontcolor=0xE6EDF7:x=80:y=${y}:enable='between(t,${start},${start + slide.durationSec})'`);
      y += 44;
    }
    y += 18;
  }
  // Footer: video title + slide id
  parts.push(`drawtext=fontfile=${FONT_REG}:text='${esc(VIDEO_TITLE)}':fontsize=22:fontcolor=0x6B7A90:x=80:y=1000:enable='between(t,${start},${start + slide.durationSec})'`);
  parts.push(`drawtext=fontfile=${FONT_REG}:text='${esc(slide.id)}':fontsize=22:fontcolor=0x6B7A90:x=1700:y=1000:enable='between(t,${start},${start + slide.durationSec})'`);
  // Progress bar (width proportional to time within slide)
  parts.push(`drawbox=x=80:y=980:w=1760*min(1\\,(t-${start})/${slide.durationSec}):h=4:color=0x00C2FF:t=fill:enable='between(t,${start},${start + slide.durationSec})'`);
  return parts.join(",");
}

function main() {
  if (!ffmpegPath || !existsSync(ffmpegPath)) {
    console.error("ffmpeg-static not found. Run: npm install");
    process.exit(1);
  }
  if (!existsSync(FONT)) {
    console.error(`Font not found: ${FONT}`);
    process.exit(1);
  }
  mkdirSync(ROOT, { recursive: true });
  const total = SLIDES.reduce((s, x) => s + x.durationSec, 0);
  let t = 0;
  const filters: string[] = [];
  for (const s of SLIDES) {
    filters.push(slideFilter(s, t));
    t += s.durationSec;
  }
  const vf = filters.join(",");
  const args = [
    "-y",
    "-f", "lavfi",
    "-i", `color=c=0x0B1020:s=1920x1080:r=30:d=${total}`,
    "-vf", vf,
    "-c:v", "libx264",
    "-pix_fmt", "yuv420p",
    "-r", "30",
    "-movflags", "+faststart",
    OUT,
  ];
  console.log(`Rendering ${SLIDES.length} slides, total ${total}s -> ${OUT}`);
  console.log(`ffmpeg: ${ffmpegPath}`);
  execFileSync(ffmpegPath, args, { stdio: "inherit" });
  console.log("Done. Silent video ready. Mux audio with render-audio.ts");
  console.log(`Subtitle: ${VIDEO_SUBTITLE}`);
}

main();
