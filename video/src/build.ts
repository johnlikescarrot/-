/**
 * Build pipeline: SVG slides → PNG frames → MP4 (H.264 + AAC) + SRT.
 *
 * Steps:
 *  1. Render each scene SVG with sharp → out/frames/scene-XX.png
 *  2. Probe narration audio (assets/audio/scene-XX.mp3) for durations;
 *     scenes without audio get a 6s silent placeholder (documented).
 *  3. ffmpeg: concat stills with per-scene durations + concatenated audio
 *     → out/zalopay-reverse-engineering.mp4 (libx264, aac, +faststart)
 *  4. Write out/zalopay-reverse-engineering.srt + out/poster.png
 *
 * ffmpeg resolution order: $FFMPEG_PATH → imageio-ffmpeg (python) → PATH → ffmpeg-static.
 */
import { execFileSync } from "node:child_process";
import { createHash } from "node:crypto";
import { existsSync, mkdirSync, readFileSync, writeFileSync } from "node:fs";
import { dirname, join } from "node:path";
import { fileURLToPath } from "node:url";
import sharp from "sharp";
import { SCENES, VIDEO_TITLE } from "./scenes.js";
import { renderPoster, renderScene } from "./render.js";

const __dirname = dirname(fileURLToPath(import.meta.url));
const VIDEO_ROOT = join(__dirname, "..");
const FRAMES = join(VIDEO_ROOT, "out", "frames");
const AUDIO_DIR = join(VIDEO_ROOT, "assets", "audio");
const OUT_MP4 = join(VIDEO_ROOT, "out", "zalopay-reverse-engineering.mp4");
const OUT_SRT = join(VIDEO_ROOT, "out", "zalopay-reverse-engineering.srt");
const OUT_POSTER = join(VIDEO_ROOT, "out", "poster.png");
const FPS = 30;
const PLACEHOLDER_SEC = 6;

function resolveFfmpeg(): string {
  if (process.env.FFMPEG_PATH && existsSync(process.env.FFMPEG_PATH)) {
    return process.env.FFMPEG_PATH;
  }
  try {
    const p = execFileSync(
      "python3",
      ["-c", "import imageio_ffmpeg; print(imageio_ffmpeg.get_ffmpeg_exe())"],
      { encoding: "utf8" }
    ).trim();
    if (p && existsSync(p)) return p;
  } catch {
    /* fall through */
  }
  try {
    execFileSync("ffmpeg", ["-version"], { stdio: "ignore" });
    return "ffmpeg";
  } catch {
    /* fall through */
  }
  const local = join(VIDEO_ROOT, "node_modules", "ffmpeg-static", "ffmpeg");
  if (existsSync(local)) return local;
  throw new Error(
    "ffmpeg not found. Install via: pip install imageio-ffmpeg  (or set FFMPEG_PATH)."
  );
}

function probeDuration(ffmpeg: string, file: string): number {
  const out = execFileSync(
    ffmpeg,
    ["-hide_banner", "-i", file],
    { encoding: "utf8" }
  ).toString();
  // ffmpeg prints Duration to stderr; execFileSync merges? Use -i via spawn alternative:
  return parseDuration(out);
}

function parseDuration(text: string): number {
  const m = text.match(/Duration:\s*(\d+):(\d+):(\d+\.\d+)/);
  if (!m) return PLACEHOLDER_SEC;
  return Number(m[1]) * 3600 + Number(m[2]) * 60 + Number(m[3]);
}

function probeDurationRobust(ffmpeg: string, file: string): number {
  try {
    const res = execFileSync(ffmpeg, ["-hide_banner", "-i", file], {
      encoding: "utf8",
      stdio: ["ignore", "pipe", "pipe"],
    });
    return parseDuration(res);
  } catch (e: unknown) {
    const err = e as { stderr?: string; stdout?: string };
    const text = `${err.stderr ?? ""}\n${err.stdout ?? ""}`;
    const d = parseDuration(text);
    return d > 0 ? d : PLACEHOLDER_SEC;
  }
}

function srtTime(sec: number): string {
  const h = Math.floor(sec / 3600);
  const m = Math.floor((sec % 3600) / 60);
  const s = Math.floor(sec % 60);
  const ms = Math.floor((sec - Math.floor(sec)) * 1000);
  const pad = (n: number, l = 2) => String(n).padStart(l, "0");
  return `${pad(h)}:${pad(m)}:${pad(s)},${pad(ms, 3)}`;
}

async function main() {
  mkdirSync(FRAMES, { recursive: true });
  mkdirSync(join(VIDEO_ROOT, "out"), { recursive: true });
  const ffmpeg = resolveFfmpeg();
  console.log(`ffmpeg: ${ffmpeg}`);
  console.log(`title: ${VIDEO_TITLE}`);

  // 1. Render frames
  for (let i = 0; i < SCENES.length; i++) {
    const scene = SCENES[i]!;
    const svg = renderScene(scene, i, SCENES.length);
    const png = join(FRAMES, `scene-${String(i + 1).padStart(2, "0")}.png`);
    await sharp(Buffer.from(svg)).png().toFile(png);
    console.log(`frame ${i + 1}/${SCENES.length}: ${png}`);
  }
  await sharp(Buffer.from(renderPoster())).png().toFile(OUT_POSTER);
  console.log(`poster: ${OUT_POSTER}`);

  // 2. Durations + SRT
  const durations: number[] = [];
  const audioFiles: string[] = [];
  let cursor = 0;
  const srtBlocks: string[] = [];
  for (let i = 0; i < SCENES.length; i++) {
    const scene = SCENES[i]!;
    const audio = join(AUDIO_DIR, `scene-${String(i + 1).padStart(2, "0")}.mp3`);
    let dur = PLACEHOLDER_SEC;
    if (existsSync(audio)) {
      dur = probeDurationRobust(ffmpeg, audio) + 0.6; // tail padding
      audioFiles.push(audio);
    } else {
      console.warn(`no narration for ${scene.id} — using ${PLACEHOLDER_SEC}s silence`);
      audioFiles.push("");
    }
    durations.push(dur);
    srtBlocks.push(
      `${i + 1}\n${srtTime(cursor)} --> ${srtTime(cursor + dur)}\n${scene.narration}\n`
    );
    cursor += dur;
  }
  writeFileSync(OUT_SRT, srtBlocks.join("\n"), "utf8");
  console.log(`srt: ${OUT_SRT} (total ${cursor.toFixed(1)}s)`);

  // 3. Build concat list for video (still image per scene)
  const listPath = join(FRAMES, "concat.txt");
  const list = SCENES.map((_, i) => {
    const png = join(FRAMES, `scene-${String(i + 1).padStart(2, "0")}.png`);
    return `file '${png}'\nduration ${durations[i]!.toFixed(3)}`;
  }).join("\n") + `\nfile '${join(FRAMES, `scene-${String(SCENES.length).padStart(2, "0")}.png`)}'\n`;
  writeFileSync(listPath, list, "utf8");

  // 4. Audio: concat narration or silence
  const audioListPath = join(FRAMES, "audio.txt");
  const present = audioFiles.filter((f) => f);
  let audioInput: string[];
  if (present.length === SCENES.length) {
    writeFileSync(
      audioListPath,
      audioFiles.map((f) => `file '${f}'`).join("\n") + "\n",
      "utf8"
    );
    audioInput = ["-f", "concat", "-safe", "0", "-i", audioListPath];
  } else if (present.length > 0) {
    // Mixed: pad missing with silence via anullsrc is complex; fall back to silence
    // and keep SRT. Documented in README.
    console.warn("partial narration — using silence track (SRT carries text)");
    audioInput = ["-f", "lavfi", "-i", "anullsrc=channel_layout=stereo:sample_rate=44100"];
  } else {
    audioInput = ["-f", "lavfi", "-i", "anullsrc=channel_layout=stereo:sample_rate=44100"];
  }

  // 5. Encode MP4
  const args = [
    "-y",
    "-f", "concat", "-safe", "0", "-i", listPath,
    ...audioInput,
    "-vf", `fps=${FPS},format=yuv420p`,
    "-c:v", "libx264", "-preset", "medium", "-crf", "20",
    "-c:a", "aac", "-b:a", "160k", "-ar", "44100", "-ac", "2",
    "-shortest",
    "-movflags", "+faststart",
    OUT_MP4,
  ];
  console.log(`encoding: ffmpeg ${args.join(" ")}`);
  execFileSync(ffmpeg, args, { stdio: "inherit" });

  const buf = readFileSync(OUT_MP4);
  const sha = createHash("sha256").update(buf).digest("hex");
  console.log(`mp4: ${OUT_MP4} (${(buf.length / 1e6).toFixed(1)} MB) sha256=${sha}`);
  writeFileSync(join(VIDEO_ROOT, "out", "SHA256.txt"), `${sha}  zalopay-reverse-engineering.mp4\n`, "utf8");
}

main().catch((e) => {
  console.error(e);
  process.exit(1);
});
