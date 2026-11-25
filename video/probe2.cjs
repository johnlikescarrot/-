const { ensureCore, run, resetLogs } = require('./ffmpeg-run.cjs');
(async () => {
  await ensureCore();
  resetLogs();
  const res = await run(['-encoders']);
  const all = (res.stderr + '\n' + res.stdout);
  console.log('CODE', res.code, 'TOTAL_LEN', all.length);
  console.log('--- FIRST 150 LINES ---');
  console.log(all.split('\n').slice(0, 150).join('\n'));
  console.log('--- contains libx264?', all.includes('libx264'), '| aac?', all.includes(' aac '));
  process.exit(0);
})().catch((e) => { console.error('FATAL', e); process.exit(1); });
