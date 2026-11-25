/* RENDER: narrator -> score -> mix -> ffmpeg mux -> MP4 + SRT + poster. */
import { Frame, W, H, fmtTime } from './gfx';
import { SCENES, renderScene } from './scenes';
import { synthesizeNarration, buildMusic, mixdown, toWav, SR } from './audio';

const FPS = 24;
const OUT = 'out';
const fs = require('fs');
const path = require('path');
const { spawn, spawnSync } = require('child_process');

const srtT = (s: number) => {
  const h = Math.floor(s / 3600), m = Math.floor(s / 60) % 60, ss = Math.floor(s) % 60, ms = Math.floor((s % 1) * 1000);
  const p = (n: number, l = 2) => String(n).padStart(l, '0');
  return `${p(h)}:${p(m)}:${p(ss)},${p(ms, 3)}`;
};
const vttT = (s: number) => srtT(s).replace(',', '.');

async function main() {
  fs.mkdirSync(OUT, { recursive: true });
  const t0 = Date.now();

  // 1. narration
  console.log('[1/6] synthesizing narration (mespeak) ...');
  const takes = synthesizeNarration(SCENES.map(s => s.narration));
  takes.forEach((t, i) => console.log(`  sc${i} ${SCENES[i].id}: ${t.dur.toFixed(1)}s${t.estimated ? ' (ESTIMATED, no voice)' : ''}`));

  // 2. timeline
  const LEAD = 1.5, TAIL = 2.2;
  const durs = SCENES.map((s, i) => Math.max(s.minDur, takes[i].dur + LEAD + TAIL));
  const starts: number[] = [];
  let acc = 0;
  durs.forEach(d => { starts.push(acc); acc += d; });
  const totalDur = acc + 1.0;
  const narrStarts = starts.map(s => s + LEAD);
  console.log(`[2/6] timeline: ${SCENES.length} scenes, total ${totalDur.toFixed(1)}s`);

  // 3. score + mix
  console.log('[3/6] synthesizing score ...');
  const music = buildMusic(totalDur, starts);
  console.log('[4/6] mixing + mastering ...');
  const mix = mixdown(music, takes, narrStarts, totalDur);
  const wav = toWav(mix.L, mix.R);
  fs.writeFileSync(path.join(OUT, 'audio.wav'), wav);
  console.log(`  audio.wav ${(wav.length / 1048576).toFixed(1)} MB`);

  // 4. sidecars: srt/vtt/transcript/chapters
  let srt = '', vtt = 'WEBVTT\n\n', md = '# Transcript — ZaloPay 11.17.0 Deep RE (research film)\n\n';
  let meta = ';FFMETADATA1\n';
  SCENES.forEach((s, i) => {
    const a = narrStarts[i], b = Math.min(starts[i] + durs[i] - 0.4, a + takes[i].dur + 0.6);
    srt += `${i + 1}\n${srtT(a)} --> ${srtT(b)}\n[${s.kicker}] ${s.narration}\n\n`;
    vtt += `${vttT(a)} --> ${vttT(b)}\n[${s.kicker}] ${s.narration}\n\n`;
    md += `## SC${String(i + 1).padStart(2, '0')} — ${s.title} (${srtT(a).slice(0, 8)} → ${srtT(starts[i] + durs[i]).slice(0, 8)})\n\n${s.narration}\n\n`;
    meta += `[CHAPTER]\nTIMEBASE=1/1000\nSTART=${Math.round(starts[i] * 1000)}\nEND=${Math.round((starts[i] + durs[i]) * 1000)}\ntitle=${s.kicker} - ${s.title}\n`;
  });
  fs.writeFileSync(path.join(OUT, 'narration.srt'), srt);
  fs.writeFileSync(path.join(OUT, 'narration.vtt'), vtt);
  fs.writeFileSync(path.join(OUT, 'TRANSCRIPT.md'), md);
  fs.writeFileSync(path.join(OUT, 'chapters.ini'), meta);

  // 5. ffmpeg mux
  const FFMPEG = process.env.FFMPEG_BIN || 'ffmpeg';
  const enc = spawnSync(FFMPEG, ['-hide_banner', '-encoders'], { encoding: 'utf8' }).stdout || '';
  const vcodec = enc.includes('libx264') ? ['-c:v', 'libx264', '-preset', 'veryfast', '-crf', '20'] : ['-c:v', 'mpeg4', '-q:v', '3'];
  console.log(`[5/6] encoding (${vcodec[1]}) -> out/film.mp4 ...`);
  const args = ['-y', '-f', 'rawvideo', '-pix_fmt', 'rgba', '-s', `${W}x${H}`, '-r', String(FPS), '-i', 'pipe:0',
    '-i', path.join(OUT, 'audio.wav'), '-map', '0:v', '-map', '1:a',
    ...vcodec, '-pix_fmt', 'yuv420p', '-c:a', 'aac', '-b:a', '160k', '-ar', '48000',
    '-movflags', '+faststart', '-shortest', path.join(OUT, 'film.mp4')];
  const ff = spawn(FFMPEG, args, { stdio: ['pipe', 'ignore', 'pipe'] });
  let ffErr = '';
  ff.stderr.on('data', (d: Buffer) => { ffErr += d.toString().slice(-2000); });
  const write = (chunk: Buffer): Promise<void> => new Promise((res, rej) => {
    if (!ff.stdin.write(chunk)) ff.stdin.once('drain', () => res());
    else res();
    ff.stdin.once('error', rej);
  });
  const f = new Frame();
  let n = 0;
  const totalFrames = Math.round(totalDur * FPS);
  for (let i = 0; i < SCENES.length; i++) {
    const s = SCENES[i], nf = Math.round(durs[i] * FPS);
    for (let k = 0; k < nf; k++) {
      const t = k / FPS, tG = starts[i] + t;
      renderScene(f, s, { t, dur: durs[i], tG, frame: n, idx: i, total: SCENES.length, g01: tG / totalDur });
      await write(f.data);
      n++;
      if (n % 288 === 0) console.log(`  frame ${n}/${totalFrames} (${fmtTime(tG)}), +${((Date.now() - t0) / 1000).toFixed(0)}s`);
    }
  }
  // tail hold (outro fade padding)
  for (; n < totalFrames; n++) {
    renderScene(f, SCENES[SCENES.length - 1], { t: durs[durs.length - 1], dur: durs[durs.length - 1], tG: starts[starts.length - 1] + durs[durs.length - 1], frame: n, idx: SCENES.length - 1, total: SCENES.length, g01: 1 });
    await write(f.data);
  }
  ff.stdin.end();
  await new Promise<void>((res, rej) => ff.on('close', (code: number) => code === 0 ? res() : rej(new Error('ffmpeg exit ' + code + '\n' + ffErr.slice(-3000)))));
  // chapters pass (stream copy)
  spawnSync(FFMPEG, ['-y', '-i', path.join(OUT, 'film.mp4'), '-i', path.join(OUT, 'chapters.ini'), '-map_metadata', '1', '-c', 'copy', path.join(OUT, 'film.chapters.mp4')], { stdio: 'ignore' });
  if (fs.existsSync(path.join(OUT, 'film.chapters.mp4'))) fs.renameSync(path.join(OUT, 'film.chapters.mp4'), path.join(OUT, 'film.mp4'));

  // 6. poster via pngjs
  const { PNG } = require('pngjs');
  const pf = new Frame();
  renderScene(pf, SCENES[0], { t: 6, dur: durs[0], tG: 6, frame: 144, idx: 0, total: SCENES.length, g01: 0.02 });
  const png = new PNG({ width: W, height: H });
  pf.data.copy(png.data);
  await new Promise<void>((res, rej) => {
    const ws = fs.createWriteStream(path.join(OUT, 'poster.png'));
    png.pack().pipe(ws).on('finish', () => res()).on('error', rej);
  });
  fs.writeFileSync(path.join(OUT, 'stats.json'), JSON.stringify({
    fps: FPS, width: W, height: H, totalDur, frames: n,
    scenes: SCENES.map((s, i) => ({ id: s.id, start: starts[i], dur: durs[i], narrDur: takes[i].dur, estimated: takes[i].estimated })),
    renderSec: (Date.now() - t0) / 1000,
  }, null, 2));
  const mp4 = fs.statSync(path.join(OUT, 'film.mp4'));
  console.log(`[6/6] DONE film.mp4 ${(mp4.size / 1048576).toFixed(1)} MB, ${((Date.now() - t0) / 1000 / 60).toFixed(1)} min render`);
}
main().catch(e => { console.error('RENDER FAILED:', e); process.exit(1); });
