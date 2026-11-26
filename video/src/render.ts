/**
 * Frame-accurate renderer.
 *
 *   scenes ──(t)──▶ SVG string ──(resvg)──▶ PNG ──(pipe)──▶ libx264
 *
 * Determinism: scenes are pure functions of time; no randomness, no clocks.
 * Distribution: frames are split across `--workers` processes, each encoding
 * its own segment; segments are concatenated with ffmpeg's concat demuxer so
 * the result is bit-identical regardless of worker count.
 */

import { spawn } from "node:child_process";
import { existsSync, mkdirSync, rmSync, writeFileSync, readdirSync, statSync, appendFileSync } from "node:fs";
import { dirname, join } from "node:path";
import { fileURLToPath } from "node:url";
import { Resvg } from "@resvg/resvg-js";
import { FPS, H, W } from "./theme.ts";
import { SCENES, TOTAL_DURATION, sceneAt } from "./film/scenes/index.ts";
import { FONTS } from "./fonts.ts";

const HERE = dirname(fileURLToPath(import.meta.url));
const OUT_DIR = join(HERE, "..", "out");
const SEG_DIR = join(OUT_DIR, "segments");

function parseArgs() {
  const a = process.argv.slice(2);
  const get = (k: string, d: string) => {
    const i = a.indexOf(`--${k}`);
    return i >= 0 && a[i + 1] ? a[i + 1] : d;
  };
  return {
    from: Number(get("from", "0")),
    to: Number(get("to", String(Math.round(TOTAL_DURATION * FPS)))),
    workers: Number(get("workers", "2")),
    out: get("out", join(OUT_DIR, "segment.mp4")),
    crf: get("crf", "17"),
    preset: get("preset", "medium"),
  };
}

export function frameSvg(index: number): string {
  const t = index / FPS;
  const scene = sceneAt(t);
  const local = t - scene.start;
  const svg = scene.render(local, scene.duration, t);
  return svg;
}

function png(svg: string): Buffer {
  const r = new Resvg(svg, {
    fitTo: { mode: "width", value: W },
    font: { fontFiles: FONTS, loadSystemFonts: false, defaultFontFamily: "BVN" },
    shapeRendering: 2,
    textRendering: 1,
    imageRendering: 0,
  });
  return r.render().asPng();
}

async function encodeSegment(from: number, to: number, out: string, crf: string, preset: string): Promise<void> {
  const ffmpeg = process.env.FFMPEG_BIN;
  if (!ffmpeg) throw new Error("FFMPEG_BIN is not set");
  const args = [
    "-hide_banner", "-loglevel", "error", "-y",
    "-f", "image2pipe", "-framerate", String(FPS), "-i", "-",
    "-c:v", "libx264", "-preset", preset, "-crf", crf,
    "-pix_fmt", "yuv420p", "-profile:v", "high", "-level", "4.1",
    "-x264-params", "keyint=60:min-keyint=30:scenecut=0",
    "-color_primaries", "bt709", "-color_trc", "bt709", "-colorspace", "bt709",
    "-r", String(FPS), out,
  ];
  const child = spawn(ffmpeg, args, { stdio: ["pipe", "inherit", "inherit"] });
  const done = new Promise<void>((res, rej) => {
    child.on("error", rej);
    child.on("close", (code) => (code === 0 ? res() : rej(new Error(`ffmpeg exited ${code}`))));
  });
  const started = Date.now();
  for (let i = from; i < to; i++) {
    const buf = png(frameSvg(i));
    if (!child.stdin.write(buf)) {
      await new Promise<void>((r) => child.stdin.once("drain", () => r()));
    }
    const n = i - from + 1;
    if (n % 60 === 0 || i === to - 1) {
      const rate = n / ((Date.now() - started) / 1000);
      const eta = (to - i - 1) / Math.max(0.01, rate);
      process.stdout.write(
        `  frames ${n}/${to - from}  ${rate.toFixed(1)} fps  eta ${Math.round(eta)}s\n`,
      );
    }
  }
  child.stdin.end();
  return done;
}

async function main(): Promise<void> {
  const opt = parseArgs();
  mkdirSync(OUT_DIR, { recursive: true });
  mkdirSync(SEG_DIR, { recursive: true });

  if (process.argv.includes("--list")) {
    SCENES.forEach((s) => console.log(`${s.start.toFixed(2).padStart(8)}  ${s.duration.toFixed(2).padStart(6)}  ${s.id}`));
    console.log(`total ${TOTAL_DURATION.toFixed(2)}s @ ${FPS}fps = ${Math.round(TOTAL_DURATION * FPS)} frames`);
    return;
  }

  if (process.argv.includes("--once")) {
    const i = Number(process.argv[process.argv.indexOf("--once") + 1] ?? 0);
    writeFileSync(join(OUT_DIR, `still-${i}.png`), png(frameSvg(i)));
    return;
  }

  const total = opt.to - opt.from;
  const workers = Math.max(1, Math.min(opt.workers, 8));
  console.log(`rendering ${total} frames (${(total / FPS).toFixed(1)}s) with ${workers} worker(s)`);

  if (workers === 1) {
    await encodeSegment(opt.from, opt.to, opt.out, opt.crf, opt.preset);
    console.log(`wrote ${opt.out}`);
    return;
  }

  const per = Math.ceil(total / workers);
  const segs: string[] = [];
  const jobs: Promise<void>[] = [];
  for (let w = 0; w < workers; w++) {
    const a = opt.from + w * per;
    const b = Math.min(opt.to, a + per);
    if (a >= b) break;
    const seg = join(SEG_DIR, `seg-${w}.mp4`);
    segs.push(seg);
    console.log(`  worker ${w}: frames ${a}..${b - 1} → ${seg}`);
    jobs.push(encodeSegment(a, b, seg, opt.crf, opt.preset));
  }
  await Promise.all(jobs);

  const list = join(SEG_DIR, "concat.txt");
  writeFileSync(list, segs.map((s) => `file '${s}'`).join("\n"));
  await new Promise<void>((res, rej) => {
    const c = spawn(process.env.FFMPEG_BIN!, [
      "-hide_banner", "-loglevel", "error", "-y",
      "-f", "concat", "-safe", "0", "-i", list, "-c", "copy", opt.out,
    ], { stdio: "inherit" });
    c.on("close", (code) => (code === 0 ? res() : rej(new Error(`concat failed ${code}`))));
  });
  console.log(`wrote ${opt.out}`);
}

if (import.meta.url === `file://${process.argv[1]}`) {
  main().catch((e) => {
    console.error(e);
    process.exit(1);
  });
}
