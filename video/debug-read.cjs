const { ensureCore, run, FS, resetLogs } = require('./ffmpeg-run.cjs');
const fs = require('fs');
(async () => {
  await ensureCore();
  // ghi slide00 vao MEMFS
  const buf = fs.readFileSync('/home/user/-/video/assets/slides/slide00-title.png');
  FS('writeFile', '/test.png', new Uint8Array(buf));
  console.log('wrote test.png', buf.length);
  resetLogs();
  const res = await run(['-i', '/test.png', '-f', 'null', '-'], 120000);
  console.log('code', res.code);
  console.log('stderr:', JSON.stringify(res.stderr).slice(0, 1500));
  console.log('stdout:', JSON.stringify(res.stdout).slice(0, 500));
  process.exit(res.code === 0 ? 0 : 1);
})().catch((e) => { console.error('FATAL', e); process.exit(2); });
