import { existsSync, mkdirSync, writeFileSync } from "node:fs";
import { dirname, join, resolve } from "node:path";
import { fileURLToPath } from "node:url";
import { execFileSync, spawnSync } from "node:child_process";
import { Worker } from "node:worker_threads";
import { createCanvas } from "@napi-rs/canvas";
import { CHAPTERS } from "./script.js";
import { registerStudioFonts, W, H, FPS } from "./theme.js";
import { loadResearchDataset, loadZalopayIcon } from "./data.js";
import { renderFrame } from "./scenes.js";
import { synthesizeSoundtrackWav } from "./audio/synth.js";
import type { ChapterTiming } from "./types.js";

const __dirname = dirname(fileURLToPath(import.meta.url));
const STUDIO_ROOT = resolve(__dirname, "..");
const FFMPEG_BIN =
  "/tmp/pyvenv/lib/python3.11/site-packages/imageio_ffmpeg/binaries/ffmpeg-linux-x86_64-v9.0.2";

function probeAudioDurationSec(filePath: string): number {
  const res = spawnSync(FFMPEG_BIN, ["-i", filePath], { encoding: "utf-8" });
  const stderr = res.stderr || "";
  const m = stderr.match(/Duration:\s*(\d+):(\d+):(\d+(?:\.\d+)?)/);
  if (!m) throw new Error(`Could not probe duration for ${filePath}`);
  return Number(m[1]) * 3600 + Number(m[2]) * 60 + Number(m[3]);
}

function computeTimings(audioDir?: string): ChapterTiming[] {
  const timings: ChapterTiming[] = [];
  let cursorSec = 0;

  for (const ch of CHAPTERS) {
    let audioPath: string | undefined;
    let audioDur: number | undefined;
    let durSec = ch.defaultDurationSec;

    if (audioDir) {
      for (const ext of ["mp3", "wav", "m4a"]) {
        const cand = join(audioDir, `${ch.id}.${ext}`);
        if (existsSync(cand)) {
          audioPath = cand;
          audioDur = probeAudioDurationSec(cand);
          // 0.45s clean visual lead-in + spoken audio + 1.15s breathing cushion
          durSec = Math.ceil((0.45 + audioDur + 1.15) * 10) / 10;
          break;
        }
      }
    }

    const startSec = cursorSec;
    const endSec = cursorSec + durSec;
    timings.push({
      ...ch,
      durationSec: durSec,
      startSec,
      endSec,
      startFrame: Math.round(startSec * FPS),
      endFrame: Math.round(endSec * FPS),
      audioPath,
      audioDurationSec: audioDur,
    });
    cursorSec = endSec;
  }
  return timings;
}

async function renderPreviews(outDir: string): Promise<void> {
  registerStudioFonts();
  mkdirSync(outDir, { recursive: true });
  const dataset = loadResearchDataset();
  const icon = await loadZalopayIcon();
  const timings = computeTimings();
  const canvas = createCanvas(W, H);
  const ctx = canvas.getContext("2d");

  for (const ch of timings) {
    // Render 55% into each chapter when animations & kinetic highlights are fully visible
    const midFrame = Math.round((ch.startSec + ch.durationSec * 0.55) * FPS);
    renderFrame(ctx, midFrame, FPS, timings, dataset, icon);
    const outPng = join(outDir, `preview_${ch.id}.png`);
    writeFileSync(outPng, canvas.toBuffer("image/png"));
    console.log(`Rendered preview: ${outPng}`);
  }
}

async function buildFullMp4(audioDir: string, outMp4: string): Promise<void> {
  const distDir = resolve(STUDIO_ROOT, "dist");
  const framesDir = resolve(STUDIO_ROOT, "frames");
  mkdirSync(distDir, { recursive: true });
  mkdirSync(framesDir, { recursive: true });

  const timings = computeTimings(audioDir);
  const totalSec = timings[timings.length - 1].endSec;
  const totalFrames = Math.round(totalSec * FPS);
  console.log(`Timeline synchronized: ${timings.length} chapters, ${totalSec.toFixed(2)}s (${totalFrames} frames @ ${FPS}fps)`);

  // 1. Synthesize custom TypeScript ambient soundtrack + transition chimes
  const soundtrackWav = join(distDir, "soundtrack.wav");
  synthesizeSoundtrackWav(timings, soundtrackWav);
  console.log(`Synthesized stereo TypeScript score: ${soundtrackWav}`);

  // 2. Align and mix narration tracks + sidechain-ducked soundtrack via FFmpeg
  const hasAllNarration = timings.every((t) => Boolean(t.audioPath));
  const masterAudio = join(distDir, "master_audio.m4a");
  if (hasAllNarration) {
    const inputs: string[] = ["-i", soundtrackWav];
    const filterParts: string[] = [];
    const voiceLabels: string[] = [];

    timings.forEach((ch, idx) => {
      inputs.push("-i", ch.audioPath!);
      const inIdx = idx + 1;
      // 0.45s lead-in delay per chapter
      const delayMs = Math.round((ch.startSec + 0.45) * 1000);
      filterParts.push(
        `[${inIdx}:a]aresample=48000,aformat=sample_fmts=fltp:channel_layouts=stereo,adelay=${delayMs}|${delayMs},apad=whole_dur=${totalSec.toFixed(3)}[v${idx}]`
      );
      voiceLabels.push(`[v${idx}]`);
    });

    filterParts.push(
      `${voiceLabels.join("")}amix=inputs=${timings.length}:duration=longest:normalize=0,` +
        `highpass=f=85,equalizer=f=2800:width_type=q:width=1.2:g=2.2,` +
        `acompressor=threshold=-18dB:ratio=3:attack=8:release=140[voice_raw]`
    );
    filterParts.push(`[voice_raw]asplit=2[voice_sc][voice_mix]`);
    filterParts.push(
      `[0:a]volume=0.38[music_bed];` +
        `[music_bed][voice_sc]sidechaincompress=threshold=0.025:ratio=5:attack=40:release=450[music_ducked]`
    );
    filterParts.push(
      `[music_ducked][voice_mix]amix=inputs=2:duration=first:normalize=0,` +
        `loudnorm=I=-14:TP=-1.5:LRA=9[master]`
    );

    execFileSync(
      FFMPEG_BIN,
      [
        "-y",
        ...inputs,
        "-filter_complex",
        filterParts.join(";"),
        "-map",
        "[master]",
        "-t",
        totalSec.toFixed(3),
        "-c:a",
        "aac",
        "-b:a",
        "256k",
        masterAudio,
      ],
      { stdio: "inherit" }
    );
  } else {
    execFileSync(
      FFMPEG_BIN,
      [
        "-y",
        "-i",
        soundtrackWav,
        "-af",
        "loudnorm=I=-16:TP=-1.5:LRA=9",
        "-c:a",
        "aac",
        "-b:a",
        "256k",
        masterAudio,
      ],
      { stdio: "inherit" }
    );
  }

  // 3. Render 1080p frames in parallel across 4 workers
  const numWorkers = 4;
  const workerFile = resolve(__dirname, "worker.ts");
  const chunk = Math.ceil(totalFrames / numWorkers);
  const promises: Array<Promise<void>> = [];

  for (let w = 0; w < numWorkers; w++) {
    const startFrame = w * chunk;
    const endFrame = Math.min(totalFrames, (w + 1) * chunk);
    if (startFrame >= endFrame) break;
    promises.push(
      new Promise((res, rej) => {
        const worker = new Worker(workerFile, {
          workerData: { startFrame, endFrame, framesDir, chapters: timings },
          execArgv: ["--import", "tsx"],
        });
        worker.on("message", (msg: { worker?: number; frame?: number }) => {
          if (msg.frame !== undefined) {
            console.log(`  Worker start=${msg.worker} -> frame ${msg.frame}/${totalFrames}`);
          }
        });
        worker.on("error", rej);
        worker.on("exit", (code: number) => {
          if (code === 0) res();
          else rej(new Error(`Worker exited with code ${code}`));
        });
      })
    );
  }
  await Promise.all(promises);
  console.log(`All ${totalFrames} frames rendered cleanly. Encoding H.264 MP4...`);

  // 4. Mux high-bitrate H.264 + AAC MP4
  execFileSync(
    FFMPEG_BIN,
    [
      "-y",
      "-framerate",
      String(FPS),
      "-i",
      join(framesDir, "frame_%06d.png"),
      "-i",
      masterAudio,
      "-c:v",
      "libx264",
      "-preset",
      "slow",
      "-crf",
      "16",
      "-tune",
      "animation",
      "-pix_fmt",
      "yuv420p",
      "-c:a",
      "copy",
      "-movflags",
      "+faststart",
      "-shortest",
      outMp4,
    ],
    { stdio: "inherit" }
  );
  console.log(`Master MP4 complete: ${outMp4}`);
}

const args = process.argv.slice(2);
if (args.includes("--preview")) {
  await renderPreviews("/tmp/zalopay_preview_frames");
} else if (args.includes("--build")) {
  const audioDir = resolve(STUDIO_ROOT, "audio_clips");
  const outMp4 = resolve(STUDIO_ROOT, "dist/ZaloPay_11.17.0_Static_Reverse_Engineering.mp4");
  await buildFullMp4(audioDir, outMp4);
} else {
  console.log("Usage: tsx src/render.ts [--preview | --build]");
}
