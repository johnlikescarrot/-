const { ensureCore, run, FS, resetLogs } = require('./ffmpeg-run.cjs');

(async () => {
  await ensureCore();
  resetLogs();
  const res = await run([
    '-f', 'lavfi', '-i', 'color=c=blue:s=320x240:d=1:r=30',
    '-f', 'lavfi', '-i', 'sine=f=440:d=1',
    '-c:v', 'libx264', '-pix_fmt', 'yuv420p',
    '-c:a', 'aac', '-b:a', '128k', '-shortest', '-y', 'out.mp4',
  ]);
  console.log('code', res.code);
  console.log('stdout:', JSON.stringify(res.stdout).slice(0, 300));
  console.log('stderr tail:', JSON.stringify(res.stderr).slice(-400));
  function walk(dir, out) {
    try { const ents = FS('readdir', dir); for (const e of ents) { const p = dir === '/' ? '/' + e : dir + '/' + e; try { const st = FS('stat', p); if (st.isDirectory()) walk(p, out); else out.push(p + ' (' + st.size + ')'); } catch (err) { out.push(p + ' [stat err]'); } } } catch (err) { out.push(dir + ' [readdir err]'); }
    return out;
  }
  console.log('MEMFS files:', walk('/', []).join(' | '));
  for (const p of ['out.mp4', '/out.mp4', '/home/out.mp4', '/tmp/out.mp4']) {
    try { const b = FS('readFile', p); console.log('readFile', p, '=>', b.length, 'bytes'); fs.writeFileSync('/tmp/out-test.mp4', Buffer.from(b)); console.log('saved /tmp/out-test.mp4'); process.exit(0); } catch (e) { /* continue */ }
  }
  console.log('out.mp4 not found');
  process.exit(1);
})().catch((e) => { console.error('FATAL', e); process.exit(2); });
