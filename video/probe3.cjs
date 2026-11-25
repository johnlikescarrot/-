const { ensureCore, run, resetLogs } = require('./ffmpeg-run.cjs');
(async () => {
  await ensureCore();
  const out = {};
  for (const name of ['-encoders','-decoders','-muxers','-demuxers','-filters','-protocols']) {
    resetLogs();
    const res = await run([name]);
    out[name] = (res.stderr + '\n' + res.stdout);
  }
  const show = (name, keyword) => {
    const lines = (out[name] || '').split('\n').filter(l => l.toLowerCase().includes(keyword.toLowerCase()));
    console.log(`### ${name} [${keyword}]`);
    console.log(lines.join('\n'));
    console.log();
  };
  for (const kw of ['libx264','libx265','libvpx','libfdk-aac','aac','libmp3lame','mp3','libvorbis','libopus','libass','libfreetype','libfribidi']) show('-encoders', kw);
  for (const kw of ['drawtext','overlay','fade','zoompan','trim','fps','format','color','sine','anullsrc','nullsrc','movie','concat','text','blend','alphamerge','split','pad','crop','scale','rotate','transpose','hflip','vflip','eq','curves','colorbalance','chromakey','lumakey','fade','fade=t=in','fade=t=out']) show('-filters', kw);
  for (const kw of ['image2','concat','mp4','mov','mjpeg','png','webp','yuv4','rawvideo']) show('-demuxers', kw);
  for (const kw of ['mp4','mov','webm','ogg','mkv','mpegts','image2','concat','wav','aac','mp3']) show('-muxers', kw);
  process.exit(0);
})().catch((e) => { console.error('FATAL', e); process.exit(1); });
