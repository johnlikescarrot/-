/**
 * Film pipeline: scene timeline → canvas frames → FFmpeg (H.264 1080p) → MP4,
 * then mux with the synthesised score and write SRT/VTT + a chapter manifest.
 */
import { createCanvas } from '@napi-rs/canvas';
import { spawn } from 'node:child_process';
import { createWriteStream, mkdirSync, writeFileSync, existsSync } from 'node:fs';
import { resolve } from 'node:path';
import { ROOT } from './data.js';
import { FPS, H, W, C } from './theme.js';
import { drawIntro, introDuration, introChapter } from './scenes/intro.js';
import { drawContainer, containerDuration, containerChapter } from './scenes/container.js';
import { drawCode, codeDuration, codeChapter } from './scenes/code.js';
import { drawNative, nativeDuration, nativeChapter } from './scenes/native.js';
import { drawDataflow, flowDuration, flowChapter } from './scenes/dataflow.js';
import { drawIntegrity, integrityDuration, integrityChapter } from './scenes/integrity.js';
import { drawConclusion, conclusionDuration, conclusionChapter } from './scenes/conclusion.js';
import type { Ctx } from './lib.js';
import { CUES } from './cc.js';

const FFMPEG = process.env['FFMPEG_BIN'] ?? resolve(ROOT, '.venv/lib/python3.11/site-packages/imageio_ffmpeg/binaries/ffmpeg-linux-x86_64-v9.0.2');
const OUT = resolve(ROOT, 'artifacts');
const TMP = resolve(ROOT, 'work/render');
mkdirSync(OUT, { recursive: true });
mkdirSync(TMP, { recursive: true });

interface Scene {
  id: string;
  index: number;
  title: string;
  duration: number;
  draw: (ctx: Ctx, t: number) => void;
}

const scenes: Scene[] = [
  { id: 'intro', index: introChapter.index, title: introChapter.title, duration: introDuration, draw: drawIntro },
  { id: 'container', index: containerChapter.index, title: containerChapter.title, duration: containerDuration, draw: drawContainer },
  { id: 'code', index: codeChapter.index, title: codeChapter.title, duration: codeDuration, draw: drawCode },
  { id: 'native', index: nativeChapter.index, title: nativeChapter.title, duration: nativeDuration, draw: drawNative },
  { id: 'dataflow', index: flowChapter.index, title: flowChapter.title, duration: flowDuration, draw: drawDataflow },
  { id: 'integrity', index: integrityChapter.index, title: integrityChapter.title, duration: integrityDuration, draw: drawIntegrity },
  { id: 'conclusion', index: conclusionChapter.index, title: conclusionChapter.title, duration: conclusionDuration, draw: drawConclusion },
];

const TOTAL = scenes.reduce((a, s) => a + s.duration, 0);

function ffmpegRaw(args: string[]): Promise<void> {
  return new Promise((res, rej) => {
    const p = spawn(FFMPEG, args, { stdio: ['pipe', 'ignore', 'pipe'] });
    let err = '';
    p.stderr.on('data', (d: Buffer) => { err += d.toString(); if (err.length > 8000) err = err.slice(-4000); });
    p.on('error', rej);
    p.on('close', (code) => (code === 0 ? res() : rej(new Error(`ffmpeg ${code}: ${err.slice(-1500)}`))));
  });
}

async function encode(only?: string): Promise<void> {
  const canvas = createCanvas(W, H);
  const ctx = canvas.getContext('2d');
  ctx.fillStyle = C.bg;
  ctx.fillRect(0, 0, W, H);

  const videoPath = resolve(TMP, 'film_video.mp4');
  const args = [
    '-y', '-hide_banner', '-loglevel', 'warning',
    '-f', 'rawvideo', '-pix_fmt', 'rgba', '-s', `${W}x${H}`, '-r', String(FPS), '-i', 'pipe:0',
    '-c:v', 'libx264', '-preset', 'slow', '-crf', '17', '-tune', 'animation',
    '-pix_fmt', 'yuv420p', '-x264-params', 'keyint=60:min-keyint=30:scenecut=0',
    '-movflags', '+faststart', videoPath,
  ];
  const proc = spawn(FFMPEG, args, { stdio: ['pipe', 'ignore', 'inherit'] });
  const done = new Promise<void>((res, rej) => {
    proc.on('error', rej);
    proc.on('close', (c) => (c === 0 ? res() : rej(new Error(`encode failed ${c}`))));
  });

  const started = Date.now();
  let frame = 0;
  for (const scene of scenes) {
    if (only && scene.id !== only) continue;
    const frames = Math.round(scene.duration * FPS);
    for (let f = 0; f < frames; f++) {
      const t = f / FPS;
      scene.draw(ctx, t);
      const buf = ctx.getImageData(0, 0, W, H).data;
      if (!proc.stdin.write(Buffer.from(buf.buffer, buf.byteOffset, buf.byteLength))) {
        await new Promise<void>((r) => proc.stdin.once('drain', () => r()));
      }
      frame++;
      if (frame % 150 === 0) {
        const el = (Date.now() - started) / 1000;
        const rate = frame / el;
        process.stdout.write(`\r  ${frame} frames · ${rate.toFixed(1)} fps · eta ${((TOTAL * FPS - frame) / rate).toFixed(0)}s   `);
      }
    }
  }
  proc.stdin.end();
  await done;
  process.stdout.write('\n');
}

function subtitles(): void {
  const srt: string[] = [];
  const vtt: string[] = ['WEBVTT', ''];
  const ts = (x: number, comma: boolean): string => {
    const h = Math.floor(x / 3600), m = Math.floor((x % 3600) / 60), s = Math.floor(x % 60), ms = Math.round((x % 1) * 1000);
    return `${String(h).padStart(2, '0')}:${String(m).padStart(2, '0')}:${String(s).padStart(2, '0')}${comma ? ',' : '.'}${String(ms).padStart(3, '0')}`;
  };
  const cues: [number, number, string][] = CUES.map((c) => [c.from, c.to, c.text]);
  cues.forEach(([a, b, txt], i) => {
    srt.push(`${i + 1}`, `${ts(a, true)} --> ${ts(b, true)}`, txt, '');
    vtt.push(`${ts(a, false)} --> ${ts(b, false)}`, txt, '');
  });
  writeFileSync(resolve(OUT, 'zalopay-11.17.0-anatomy.srt'), srt.join('\n'));
  writeFileSync(resolve(OUT, 'zalopay-11.17.0-anatomy.vtt'), vtt.join('\n'));
  writeFileSync(resolve(OUT, 'chapters.json'), JSON.stringify(scenes.map((s, i) => ({
    index: s.index, title: s.title, duration: s.duration,
    start: scenes.slice(0, i).reduce((a, b) => a + b.duration, 0),
  })), null, 2));
}

async function mux(): Promise<void> {
  const video = resolve(TMP, 'film_video.mp4');
  const audio = resolve(TMP, 'score.wav');
  const out = resolve(OUT, 'zalopay-11.17.0-anatomy.mp4');
  if (!existsSync(audio)) {
    console.log('audio missing — video-only output');
    await ffmpegRaw(['-y', '-hide_banner', '-loglevel', 'warning', '-i', video, '-c', 'copy', out]);
    return;
  }
  await ffmpegRaw([
    '-y', '-hide_banner', '-loglevel', 'warning',
    '-i', video, '-i', audio,
    '-map', '0:v:0', '-map', '1:a:0',
    '-c:v', 'copy', '-c:a', 'aac', '-b:a', '256k', '-ar', '48000',
    '-shortest', '-movflags', '+faststart',
    out,
  ]);
}

async function main(): Promise<void> {
  const only = process.argv[2];
  const skipEncode = process.argv.includes('--mux-only');
  if (!skipEncode) await encode(only);
  subtitles();
  await mux();
  console.log(`done · ${TOTAL}s · ${W}x${H}@${FPS} → artifacts/`);
}

main().catch((e) => { console.error(e); process.exit(1); });
