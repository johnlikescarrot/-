const { createFFmpeg } = require('@ffmpeg/ffmpeg');

(async () => {
  const ffmpeg = createFFmpeg({ log: true });
  console.log('loading ffmpeg.wasm...');
  await ffmpeg.load();
  console.log('LOADED OK');
  console.log('running -version...');
  await ffmpeg.run('-version');
  console.log('RUN OK');
  process.exit(0);
})().catch((e) => {
  console.error('ERROR:', e);
  process.exit(1);
});
