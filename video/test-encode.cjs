const { ensureCore, run, FS, resetLogs } = require('./ffmpeg-run.cjs');

(async () => {
  await ensureCore();
  console.log('core ready');
  resetLogs();
  const res = await Promise.resolve().then(() => {
    // synchronous proxy_main via run()
    return run([
      '-f', 'lavfi', '-i', 'color=c=blue:s=320x240:d=1:r=30',
      '-f', 'lavfi', '-i', 'sine=f=440:d=1',
      '-c:v', 'libx264', '-pix_fmt', 'yuv420p',
      '-c:a', 'aac', '-b:a', '128k',
      '-shortest', '-y', '/out.mp4',
    ]);
  });
  console.log('run code:', res.code);
  console.log('--- STDOUT ---'); console.log(res.stdout.slice(0, 2000));
  console.log('--- STDERR ---'); console.log(res.stderr.slice(0, 3000));
  const buf = FS('readFile', '/out.mp4');
  console.log('out.mp4 size:', buf.length, 'bytes');
  fs.writeFileSync('/tmp/out-test.mp4', Buffer.from(buf));
  console.log('wrote /tmp/out-test.mp4');
  process.exit(res.code === 0 ? 0 : 1);
})().catch((e) => { console.error('FATAL', e); process.exit(2); });
