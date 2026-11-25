/* AUDIO: eSpeak-class synthetic narrator (mespeak, fresh process per scene)
 * + fully synthesized score. 48 kHz stereo master. */
export const SR = 48000;
const TAU = Math.PI * 2;
const midi = (m: number) => 440 * Math.pow(2, (m - 69) / 12);

/* ---------- narrator ---------- */
export interface NarrTake { samples: Float32Array; dur: number; estimated: boolean }
function wavToMono(buf: Buffer): { samples: Float32Array; sr: number } {
  const sr = buf.readUInt32LE(24);
  const ch = buf.readUInt16LE(22);
  const bits = buf.readUInt16LE(34);
  let off = 12;
  while (off + 8 <= buf.length) {
    const id = buf.toString('ascii', off, off + 4);
    const sz = buf.readUInt32LE(off + 4);
    if (id === 'data') { off += 8; break; }
    off += 8 + sz;
  }
  const bytesPer = bits / 8, frames = Math.floor((buf.length - off) / (bytesPer * ch));
  const out = new Float32Array(frames);
  for (let i = 0; i < frames; i++) {
    let acc = 0;
    for (let c = 0; c < ch; c++) {
      const o = off + (i * ch + c) * bytesPer;
      acc += bits === 16 ? buf.readInt16LE(o) / 32768 : (buf[o] - 128) / 128;
    }
    out[i] = acc / ch;
  }
  return { samples: out, sr };
}
function resample(x: Float32Array, from: number, to: number): Float32Array {
  if (from === to) return x;
  const n = Math.round(x.length * to / from);
  const y = new Float32Array(n);
  for (let i = 0; i < n; i++) {
    const p = i * x.length / n, i0 = Math.floor(p), f = p - i0;
    y[i] = x[i0] * (1 - f) + (x[Math.min(x.length - 1, i0 + 1)] * f);
  }
  return y;
}
function synthScene(text: string, idx: number): NarrTake {
  const words = text.split(/\s+/).length;
  const est = words * 0.42 + 1.2;
  try {
    // eslint-disable-next-line @typescript-eslint/no-var-requires
    const cp = require('child_process');
    // eslint-disable-next-line @typescript-eslint/no-var-requires
    const fs = require('fs');
    fs.mkdirSync('out/narr', { recursive: true });
    const txt = `out/narr/sc${idx}.txt`, wav = `out/narr/sc${idx}.wav`;
    fs.writeFileSync(txt, text);
    for (let attempt = 0; attempt < 2; attempt++) {
      const r = cp.spawnSync('node', ['tools/speak.js', txt, wav], { encoding: 'utf8' });
      try {
        const line = (r.stdout || '').trim().split('\n').pop() || '{}';
        const info = JSON.parse(line);
        if (r.status === 0 && info.ok && fs.existsSync(wav)) {
          const { samples } = wavToMono(fs.readFileSync(wav));
          const y = resample(samples, 22050, SR);
          let peak = 0;
          for (let i = 0; i < y.length; i++) peak = Math.max(peak, Math.abs(y[i]));
          if (peak < 0.03 || y.length < SR) throw new Error('invalid take');
          for (let i = 0; i < y.length; i++) y[i] /= peak;
          return { samples: y, dur: y.length / SR, estimated: false };
        }
        throw new Error(info.error || 'speak failed');
      } catch (e) {
        if (attempt === 1) console.error(`scene ${idx} narration failed (${(e as Error).message}); estimated timing`);
      }
    }
  } catch (e) { console.error(`scene ${idx} narration error; estimated timing`); }
  return { samples: new Float32Array(0), dur: est, estimated: true };
}
export function synthesizeNarration(texts: string[]): NarrTake[] {
  return texts.map((t, i) => synthScene(t, i));
}
/* small generated-room reverb */
export function addRoom(x: Float32Array, wet = 0.10): Float32Array {
  const combs = [1116, 1188, 1277, 1356].map(L => ({ b: new Float32Array(L), i: 0, L }));
  const allps = [225, 556].map(L => ({ b: new Float32Array(L), i: 0, L }));
  const y = new Float32Array(x.length);
  for (let n = 0; n < x.length; n++) {
    let acc = 0;
    for (const c of combs) {
      const v = x[n] + c.b[c.i] * 0.82;
      acc += c.b[c.i];
      c.b[c.i] = v; c.i = (c.i + 1) % c.L;
    }
    acc /= combs.length;
    for (const a of allps) {
      const v = acc + a.b[a.i] * 0.5;
      acc = a.b[a.i] - acc * 0.5;
      a.b[a.i] = v; a.i = (a.i + 1) % a.L;
    }
    y[n] = x[n] * (1 - wet) + acc * wet * 2.2;
  }
  return y;
}

/* ---------- score ---------- */
const BPM = 96, BEAT = 60 / BPM, BAR = BEAT * 4;
const PROG = [
  [57, 60, 64, 69], [53, 57, 60, 65], [55, 60, 64, 67], [55, 59, 62, 67],
];
const ROOTS = [45, 41, 48, 43];
const saw = (ph: number) => 2 * (ph / TAU - Math.floor(ph / TAU + 0.5));

export function buildMusic(dur: number, impacts: number[]): { L: Float32Array; R: Float32Array } {
  const n = Math.ceil(dur * SR);
  const L = new Float32Array(n), R = new Float32Array(n);
  const loopLen = BAR * 4;
  let lp = 0, prevNz = 0;
  const phases = new Float32Array(16);
  for (let i = 0; i < n; i++) {
    const t = i / SR;
    const chord = PROG[Math.floor((t % loopLen) / BAR) % 4];
    const lfo = 0.5 + 0.5 * Math.sin(TAU * 0.07 * t);
    const cutoff = 0.02 + 0.05 * lfo;
    let v = 0;
    for (let k = 0; k < 8; k++) {
      const m = chord[k % chord.length] + (k % 2 ? 0.06 : -0.06);
      phases[k] += TAU * midi(m) / SR;
      v += saw(phases[k]);
    }
    v *= 0.028;
    lp += cutoff * (v - lp);
    L[i] += lp; R[i] += lp;
    const chordIdx = Math.floor((t % loopLen) / BAR) % 4;
    const eighth = (t % (BEAT / 2)) / (BEAT / 2);
    const benv = Math.exp(-eighth * 5) * (Math.floor(t / (BEAT / 2)) % 2 === 0 ? 1 : 0.55);
    const bv = Math.sin(TAU * midi(ROOTS[chordIdx] - 12) * t) * 0.16 * benv;
    L[i] += bv; R[i] += bv;
    const step = Math.floor(t / (BEAT / 4));
    const st = (t % (BEAT / 4)) / (BEAT / 4);
    const arpNotes = [chord[0] + 24, chord[1] + 24, chord[2] + 24, chord[3] + 24, chord[2] + 24, chord[1] + 24];
    const av = Math.sin(TAU * midi(arpNotes[step % arpNotes.length]) * t) * Math.exp(-st * 9) * 0.10;
    if (step % 2 === 0) L[i] += av; else R[i] += av;
    const bt = t % BEAT, beatIdx = Math.floor(t / BEAT);
    const kickEnv = Math.exp(-bt * 22);
    const kick = Math.sin(TAU * (42 + 70 * Math.exp(-bt * 30)) * t) * kickEnv * 0.5;
    L[i] += kick; R[i] += kick;
    const off = (t + BEAT / 2) % BEAT;
    const hatEnv = Math.exp(-off * 90);
    const nz = Math.random() * 2 - 1;
    const hp = nz - prevNz; prevNz = nz; // cheap highpass for hats
    const hatV = hp * hatEnv * 0.10 * (beatIdx % 2 === 0 ? 1 : 0.6);
    L[i] += hatV; R[i] += hatV * 0.8;
    if (beatIdx % 2 === 1 && bt < 0.12) {
      const rim = Math.sin(TAU * 1900 * t) * Math.exp(-bt * 60) * 0.08 + nz * Math.exp(-bt * 70) * 0.05;
      L[i] += rim; R[i] += rim;
    }
  }
  for (const it of impacts) {
    const start = Math.floor(it * SR);
    for (let i = 0; i < SR * 0.8; i++) {
      const idx = start - Math.round(SR * 0.8) + i;
      if (idx < 0 || idx >= n) continue;
      const f = i / (SR * 0.8);
      const wv = (Math.random() * 2 - 1) * f * f * 0.10;
      L[idx] += wv; R[idx] += wv;
    }
    for (let i = 0; i < SR * 1.6; i++) {
      const idx = start + i;
      if (idx >= n) break;
      const t = i / SR;
      const boom = Math.sin(TAU * (55 - 25 * Math.min(1, t)) * t) * Math.exp(-t * 3.2) * 0.55;
      const shim = (Math.sin(TAU * 2093 * t) + Math.sin(TAU * 2637 * t) * 0.6) * Math.exp(-t * 5) * 0.05;
      const wash = (Math.random() * 2 - 1) * Math.exp(-t * 2.2) * 0.08;
      L[idx] += boom + shim + wash; R[idx] += boom + shim * 0.9 + wash;
    }
  }
  return { L, R };
}

/* ---------- mix ---------- */
export function mixdown(music: { L: Float32Array; R: Float32Array },
  takes: NarrTake[], starts: number[], totalDur: number): { L: Float32Array; R: Float32Array } {
  const n = Math.ceil(totalDur * SR);
  const L = new Float32Array(n), R = new Float32Array(n);
  const envN = Math.ceil(totalDur * 100);
  const env = new Float32Array(envN);
  takes.forEach((tk, i) => {
    if (!tk.samples.length) return;
    const voiced = addRoom(tk.samples, 0.10);
    const off = Math.floor(starts[i] * SR);
    for (let k = 0; k < voiced.length && off + k < n; k++) {
      const v = voiced[k] * 0.85;
      L[off + k] += v; R[off + k] += v;
      const e = Math.floor((off + k) / SR * 100);
      if (e < envN) env[e] = Math.max(env[e], Math.abs(v));
    }
  });
  let sm = 0;
  const duck = new Float32Array(n);
  for (let i = 0; i < n; i++) {
    const e = env[Math.floor(i / SR * 100)] || 0;
    const target = e > 0.03 ? 1 : 0;
    sm += (target - sm) * (target > sm ? 0.002 : 0.0004);
    duck[i] = 1 - 0.80 * sm;
  }
  for (let i = 0; i < n; i++) {
    const m = Math.min(music.L.length - 1, i);
    L[i] += music.L[m] * duck[i] * 0.9;
    R[i] += (music.R.length ? music.R[m] : music.L[m]) * duck[i] * 0.9;
  }
  let peak = 0;
  for (let i = 0; i < n; i++) {
    L[i] = Math.tanh(L[i] * 1.05); R[i] = Math.tanh(R[i] * 1.05);
    peak = Math.max(peak, Math.abs(L[i]), Math.abs(R[i]));
  }
  const g = peak > 0 ? 0.89 / peak : 1;
  const fIn = Math.floor(SR * 0.6), fOut = Math.floor(SR * 1.8);
  for (let i = 0; i < n; i++) {
    let f = 1;
    if (i < fIn) f = i / fIn;
    if (i > n - fOut) f = Math.min(f, (n - i) / fOut);
    L[i] *= g * f; R[i] *= g * f;
  }
  return { L, R };
}
export function toWav(L: Float32Array, R: Float32Array): Buffer {
  const n = L.length;
  const buf = Buffer.alloc(44 + n * 4);
  buf.write('RIFF', 0); buf.writeUInt32LE(36 + n * 4, 4); buf.write('WAVE', 8);
  buf.write('fmt ', 12); buf.writeUInt32LE(16, 16); buf.writeUInt16LE(1, 20);
  buf.writeUInt16LE(2, 22); buf.writeUInt32LE(SR, 24); buf.writeUInt32LE(SR * 4, 28);
  buf.writeUInt16LE(4, 32); buf.writeUInt16LE(16, 34); buf.write('data', 36);
  buf.writeUInt32LE(n * 4, 40);
  for (let i = 0; i < n; i++) {
    buf.writeInt16LE(Math.max(-1, Math.min(1, L[i])) * 32767 | 0, 44 + i * 4);
    buf.writeInt16LE(Math.max(-1, Math.min(1, R[i])) * 32767 | 0, 44 + i * 4 + 2);
  }
  return buf;
}
