/* speak.js — one scene of narration in a FRESH process (avoids emscripten heap
 * accumulation that breaks long single-process mespeak runs).
 * usage: node tools/speak.js <textfile> <outwav> ; prints one JSON line. */
const fs = require('fs');
const path = require('path');
const [,, textFile, outWav] = process.argv;
try {
  const text = fs.readFileSync(textFile, 'utf8');
  const root = path.join(__dirname, '..', 'node_modules', 'mespeak');
  const mespeak = require(path.join(root, 'src', 'index.js'));
  mespeak.loadConfig(require(path.join(root, 'src', 'mespeak_config.json')));
  const cand = [path.join(root, 'voices', 'en', 'en.json'),
                path.join(root, 'voices', 'en.json')].find(p => fs.existsSync(p));
  if (!cand) throw new Error('no english voice found under ' + root);
  mespeak.loadVoice(require(cand));
  const sentences = (text.replace(/\s+/g, ' ').match(/[^.!?;]+[.!?;]?/g) || [text])
    .map(s => s.trim()).filter(Boolean);
  const SR = 22050;
  const gap = new Int16Array(Math.round(SR * 0.30));
  const pcm = [];
  const speakChunk = (c) => {
    const ab = mespeak.speak(c, { rawdata: 'ArrayBuffer', speed: 152, amplitude: 100, pitch: 50 });
    if (!ab || !ab.byteLength) throw new Error('empty speak buffer: ' + c.slice(0, 60));
    const b = Buffer.from(ab);
    let off = 12;
    while (off + 8 <= b.length) {
      const id = b.toString('ascii', off, off + 4);
      const sz = b.readUInt32LE(off + 4);
      if (id === 'data') { off += 8; break; }
      off += 8 + sz;
    }
    const n = Math.floor((b.length - off) / 2);
    const arr = new Int16Array(n);
    for (let i = 0; i < n; i++) arr[i] = b.readInt16LE(off + i * 2);
    let a = 0, e = n;
    while (a < e && Math.abs(arr[a]) < 650) a++;
    while (e > a && Math.abs(arr[e - 1]) < 650) e--;
    const core = arr.slice(Math.max(0, a - 400), Math.min(n, e + 1200));
    if (core.length < 100) throw new Error('silent speak buffer: ' + c.slice(0, 60));
    pcm.push(core, gap);
  };
  for (const s of sentences) {
    const chunks = s.length > 200 ? (s.match(/.{1,190}[\s,]|.{1,190}/g) || [s]) : [s];
    for (const c of chunks) speakChunk(c);
  }
  const total = pcm.reduce((x, y) => x + y.length, 0);
  const out = Buffer.alloc(44 + total * 2);
  out.write('RIFF', 0); out.writeUInt32LE(36 + total * 2, 4); out.write('WAVE', 8);
  out.write('fmt ', 12); out.writeUInt32LE(16, 16); out.writeUInt16LE(1, 20);
  out.writeUInt16LE(1, 22); out.writeUInt32LE(SR, 24); out.writeUInt32LE(SR * 2, 28);
  out.writeUInt16LE(2, 32); out.writeUInt16LE(16, 34); out.write('data', 36);
  out.writeUInt32LE(total * 2, 40);
  let o = 44, peak = 0;
  for (const arr of pcm) for (let i = 0; i < arr.length; i++) {
    out.writeInt16LE(arr[i], o); o += 2;
    const v = Math.abs(arr[i]) / 32768;
    if (v > peak) peak = v;
  }
  fs.writeFileSync(outWav, out);
  console.log(JSON.stringify({ ok: true, sr: SR, frames: total, peak: +peak.toFixed(3) }));
} catch (e) {
  console.log(JSON.stringify({ ok: false, error: String((e && e.message) || e) }));
  process.exit(1);
}
