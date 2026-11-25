const { ensureCore, run, FS, resetLogs } = require('./ffmpeg-run.cjs');
const fs = require('fs');
(async () => {
  await ensureCore();
  const disk = fs.readFileSync('/home/user/-/output/zalopay-re.mp4');
  console.log('file size (disk):', disk.length, 'bytes');
  console.log('ftyp:', disk.slice(4, 12).toString('ascii'));
  FS('writeFile', '/out.mp4', new Uint8Array(disk));
  resetLogs();
  const res = await run(['-i', '/out.mp4'], 120000);
  console.log('code', res.code);
  console.log(res.stderr);
  process.exit(0);
})().catch((e) => { console.error('FATAL', e); process.exit(1); });
