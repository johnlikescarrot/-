const { ensureCore, run, resetLogs } = require('./ffmpeg-run.cjs');
(async () => {
  await ensureCore();
  for (const cmd of [
    ['-encoders'],
    ['-demuxers'],
    ['-filters'],
  ]) {
    resetLogs();
    const res = await run(cmd);
    const all = (res.stderr + '\n' + res.stdout);
    console.log('==========', cmd.join(' '), ' code', res.code, '==========');
    const want = ['libx264','h264','aac','libfdk_aac','mp3','libmp3lame','ac3','flac',
      'image2','concat','mp4','mov','mjpeg','png',
      'drawtext','overlay','fade','zoompan','trim','fps','format','color','sine','anullsrc',
      'nullsrc','src','movie','concat','x264','libvpx','libtheora','libvorbis','libopus',
      'bink','av1','libsvtav1','pnms','ppm','yuv4'];
    const lines = all.split('\n').filter(l => want.some(w => l.toLowerCase().includes(w.toLowerCase())));
    console.log(lines.slice(0, 60).join('\n'));
  }
  process.exit(0);
})().catch((e) => { console.error('FATAL', e); process.exit(1); });
