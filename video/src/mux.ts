/**
 * Mux slides + narration audio -> MP4 (H.264 + AAC) via ffmpeg.
 * Finds ffmpeg: $FFMPEG env, system `ffmpeg`, or imageio-ffmpeg binary.
 */
import * as fs from "fs";
import * as path from "path";
import { execSync } from "child_process";

const ROOT = path.resolve(__dirname, "..");
const OUT = path.join(ROOT, "out", "zalopay-re-analysis.mp4");

function findFfmpeg(): string {
  if (process.env.FFMPEG && fs.existsSync(process.env.FFMPEG)) return process.env.FFMPEG;
  try {
    execSync("which ffmpeg", { stdio: "ignore" });
    return "ffmpeg";
  } catch { /* fall through */ }
  const candidates = [
    "/usr/local/lib/python3.11/dist-packages/imageio_ffmpeg/binaries/ffmpeg-linux-x86_64-v9.0.2",
    path.join(ROOT, "tools", "ffmpeg"),
  ];
  for (const c of candidates) if (fs.existsSync(c)) return c;
  throw new Error("ffmpeg not found. Set FFMPEG env or install imageio-ffmpeg (pip install imageio-ffmpeg).");
}

function audioDuration(ffmpeg: string, file: string): number {
  // Use ffprobe if available, else estimate from file size (128kbps ~ 16KB/s)
  try {
    const out = execSync(
      `ffprobe -v error -show_entries format=duration -of default=noprint_wrappers=1:nokey=1 "${file}"`,
      { encoding: "utf-8" }
    ).trim();
    const d = parseFloat(out);
    if (!isNaN(d) && d > 0) return d;
  } catch { /* fall through */ }
  const size = fs.statSync(file).size;
  return Math.max(8, size / 16000); // ~128kbps
}

function main() {
  const ffmpeg = findFfmpeg();
  console.log(`[mux] ffmpeg: ${ffmpeg}`);
  const narration = JSON.parse(fs.readFileSync(path.join(ROOT, "narration.json"), "utf-8")) as Array<{
    id: string; png: string; audio: string; text: string;
  }>;

  fs.mkdirSync(path.join(ROOT, "out"), { recursive: true });
  const tmp = path.join(ROOT, "out", "segments");
  fs.mkdirSync(tmp, { recursive: true });

  const segments: string[] = [];
  narration.forEach((n, i) => {
    const hasAudio = fs.existsSync(n.audio) && fs.statSync(n.audio).size > 1000;
    const seg = path.join(tmp, `seg-${String(i).padStart(2, "0")}-${n.id}.mp4`);
    if (hasAudio) {
      const dur = audioDuration(ffmpeg, n.audio) + 1.2; // padding
      console.log(`[mux] segment ${i + 1}/${narration.length} ${n.id} dur=${dur.toFixed(1)}s (audio)`);
      execSync(
        `"${ffmpeg}" -y -loop 1 -i "${n.png}" -i "${n.audio}" -c:v libx264 -tune stillimage -c:a aac -b:a 128k -pix_fmt yuv420p -r 30 -t ${dur.toFixed(2)} -shortest "${seg}"`,
        { stdio: "inherit" }
      );
    } else {
      console.log(`[mux] segment ${i + 1}/${narration.length} ${n.id} (SILENT placeholder 12s — add audio/*.mp3)`);
      execSync(
        `"${ffmpeg}" -y -loop 1 -i "${n.png}" -f lavfi -i anullsrc=channel_layout=stereo:sample_rate=44100 -c:v libx264 -tune stillimage -c:a aac -b:a 128k -pix_fmt yuv420p -r 30 -t 12 -shortest "${seg}"`,
        { stdio: "inherit" }
      );
    }
    segments.push(seg);
  });

  const listFile = path.join(tmp, "concat.txt");
  fs.writeFileSync(listFile, segments.map((s) => `file '${s}'`).join("\n"), "utf-8");
  console.log(`[mux] concat ${segments.length} segments -> ${OUT}`);
  execSync(`"${ffmpeg}" -y -f concat -safe 0 -i "${listFile}" -c copy "${OUT}"`, { stdio: "inherit" });

  // Embed subtitles if present (soft)
  const srt = path.join(ROOT, "subtitles.srt");
  if (fs.existsSync(srt)) {
    const withSub = path.join(ROOT, "out", "zalopay-re-analysis-sub.mp4");
    try {
      execSync(
        `"${ffmpeg}" -y -i "${OUT}" -i "${srt}" -c:v copy -c:a copy -c:s mov_text -metadata:s:s:0 language=vie "${withSub}"`,
        { stdio: "inherit" }
      );
      fs.renameSync(withSub, OUT);
      console.log("[mux] subtitles embedded (mov_text)");
    } catch (e) {
      console.warn("[mux] subtitle embed failed (continuing without):", e);
    }
  }

  const size = fs.statSync(OUT).size;
  console.log(`[mux] DONE: ${OUT} (${(size / 1024 / 1024).toFixed(1)} MB)`);
}

main();
