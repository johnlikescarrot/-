/*
 * Chinh lenh video: doc slide PNG + am thah WAV tu MEMFS, hoa anh bang ffmpeg.wasm,
 * xuat MP4 (libx264 + aac) 720p30.
 */
import { buildSlides } from './slides';
import { synthesizeWav } from './audio';

declare const require: any;
const ffmpeg = require('../ffmpeg-run.cjs');
const { ensureCore, run, FS, resetLogs, getLogs } = ffmpeg;
const fs = require('fs');

const MEM_ROOT = '/home/user/-/video';
const OUTPUT = '/home/user/-/output/zalopay-re.mp4';

async function main() {
  await ensureCore();
  console.log('ffmpeg.wasm core da san sang');

  const slides = buildSlides();
  const totalDur = slides.reduce((acc, s) => acc + s.durationSec, 0);
  console.log(`${slides.length} slide, tong thoi luong ${totalDur.toFixed(1)}s`);

  // 1) Ghi tat ca slide PNG vao MEMFS
  for (const s of slides) {
    const path = `/${s.name}`;
    const buf = fs.readFileSync(`${MEM_ROOT}/assets/slides/${s.name}`);
    FS('writeFile', path, new Uint8Array(buf));
    console.log('ghi MEMFS', path, buf.length, 'byte');
  }

  // 2) Sinh am thanh va ghi vao MEMFS
  const wav = synthesizeWav(totalDur);
  FS('writeFile', '/audio.wav', new Uint8Array(wav));
  console.log('ghi MEMFS /audio.wav', wav.length, 'byte');

  // 3) Tao danh sach noi chuyen (concat demuxer, slideshow) va ghi vao MEMFS
  const listLines: string[] = [];
  for (const s of slides) {
    listLines.push(`file '/${s.name}'`);
    listLines.push(`duration ${s.durationSec.toFixed(3)}`);
  }
  FS('writeFile', '/list.txt', new Uint8Array(Buffer.from(listLines.join('\n'))));
  console.log('ghi MEMFS /list.txt');

  const args: string[] = [
    '-f', 'concat', '-safe', '0', '-i', '/list.txt',
    '-i', '/audio.wav',
    '-c:v', 'libx264', '-pix_fmt', 'yuv420p', '-r', '30',
    '-preset', 'fast', '-crf', '20',
    '-c:a', 'aac', '-b:a', '128k', '-movflags', '+faststart',
    '-shortest', '-y', '/output.mp4',
  ];

  resetLogs();
  console.log('Chay ffmpeg.wasm ...');
  const t0 = Date.now();
  const res = await run(args, 600000);
  const dt = ((Date.now() - t0) / 1000).toFixed(1);
  console.log(`ffmpeg xong trong ${dt}s, code ${res.code}`);
  console.log('STDERR cuoi:', getLogs().stderr.slice(-1000));

  if (res.code !== 0) {
    throw new Error('ffmpeg that bai: ' + getLogs().stderr.slice(-600));
  }

  // 6) Doc MP4 tu MEMFS va luu ra output
  const out = FS('readFile', '/output.mp4');
  const outBuf = Buffer.from(out);
  fs.mkdirSync('/home/user/-/output', { recursive: true });
  fs.writeFileSync(OUTPUT, outBuf);
  console.log('Da xuat:', OUTPUT, outBuf.length, 'byte');
  process.exit(0);
}

main().catch((e) => {
  console.error('LOI:', e);
  process.exit(1);
});
