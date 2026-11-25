const { ensureCore, run, FS, resetLogs } = require('./ffmpeg-run.cjs');
const fs = require('fs');
(async () => {
  await ensureCore();
  resetLogs();
  const res = await run([
    '-f', 'lavfi', '-i', 'color=c=steelblue:s=320x240:d=2:r=30',
    '-f', 'lavfi', '-i', 'sine=f=440:d=2',
    '-c:v', 'libx264', '-pix_fmt', 'yuv420p', '-crf', '20',
    '-c:a', 'aac', '-b:a', '128k',
    '-shortest', 'out.mp4',
  ]);
  console.log('code', res.code);
  console.log('stderr tail:', JSON.stringify(res.stderr).slice(-500));
  let got = null;
  for (const p of ['out.mp4', '/out.mp4']) {
    try { got = FS('readFile', p); console.log('readFile', p, '=>', got.length, 'bytes'); break; } catch (e) {}
  }
  if (!got) { console.log('MP4 not found in MEMFS'); process.exit(1); }
  fs.writeFileSync('/tmp/out-final.mp4', Buffer.from(got));
  console.log('saved /tmp/out-final.mp4');
  // verify ftyp box
  const b = Buffer.from(got.slice(0, 12));
  console.log('head hex:', b.toString('hex'));
  process.exit(res.code === 0 && got ? 0 : 1);
})().catch((e) => { console.error('FATAL', e); process.exit(2); });
