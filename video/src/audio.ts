/**
 * Score synthesiser — no samples, no third-party audio.
 *
 * Design: a slow 4-chord pad cycle (Dm7 – B♭maj7 – Fmaj7 – Csus2) at 48 kHz with
 * additive partials, a sub-bass pulse locked to section boundaries, a filtered
 * noise "air" bed, and a single bell/pluck motif that marks each act opening.
 * Everything is generated deterministically from the film timeline so the score
 * stays in sync when scene durations change.
 */
import { mkdirSync, writeFileSync } from 'node:fs';
import { resolve } from 'node:path';
import { ROOT } from './data.js';
import { introDuration } from './scenes/intro.js';
import { containerDuration } from './scenes/container.js';
import { codeDuration } from './scenes/code.js';
import { nativeDuration } from './scenes/native.js';
import { flowDuration } from './scenes/dataflow.js';
import { integrityDuration } from './scenes/integrity.js';
import { conclusionDuration } from './scenes/conclusion.js';

const SR = 48000;
const OUT = resolve(ROOT, 'work/render');
mkdirSync(OUT, { recursive: true });

const DURATIONS = [introDuration, containerDuration, codeDuration, nativeDuration, flowDuration, integrityDuration, conclusionDuration];
const TOTAL = DURATIONS.reduce((a, b) => a + b, 0);
const N = Math.ceil(TOTAL * SR);

// scene start times
const starts: number[] = [];
DURATIONS.reduce((acc, d) => { starts.push(acc); return acc + d; }, 0);

const L = new Float32Array(N);
const R = new Float32Array(N);

const chord = (root: number, third: number, fifth: number, seventh: number): number[] => [root, third, fifth, seventh];

/** equal temperament from semitones above A4 */
const hz = (semi: number): number => 440 * 2 ** (semi / 12);

// Dm7, B♭maj7, Fmaj7, Csus2 (voiced close, around D3-D4)
const CHORDS: number[][] = [
  chord(hz(-7), hz(-3), hz(0), hz(3)),   // D F A C
  chord(hz(-10), hz(-6), hz(-3), hz(1)), // Bb D F A
  chord(hz(-16), hz(-9), hz(-5), hz(-1)),// F A C E
  chord(hz(-14), hz(-7), hz(-5), hz(-2)),// C G C D-ish
];

const PAD_LEN = 22; // seconds per chord across the whole film

function smooth(x: number): number { return x * x * (3 - 2 * x); }

function add(chan: Float32Array, i: number, v: number): void {
  if (i >= 0 && i < N) chan[i] = (chan[i] as number) + v;
}

// ---- pad ------------------------------------------------------------------
for (let i = 0; i < N; i++) {
  const t = i / SR;
  const cyc = t / PAD_LEN;
  const ci = Math.floor(cyc) % CHORDS.length;
  const ni = (ci + 1) % CHORDS.length;
  const x = cyc - Math.floor(cyc);
  const blend = smooth(Math.min(1, Math.max(0, (x - 0.82) / 0.18)));
  const a = CHORDS[ci] as number[];
  const b = CHORDS[ni] as number[];
  let l = 0, r = 0;
  for (let v = 0; v < 4; v++) {
    const f1 = a[v] as number, f2 = b[v] as number;
    const f = f1 * (1 - blend) + f2 * blend;
    for (let h = 1; h <= 5; h++) {
      const amp = 0.055 / (h * h);
      const det = 1 + (h % 2 === 0 ? 0.0015 : -0.0013);
      const phase = t * f * h;
      const s = Math.sin(2 * Math.PI * phase) + Math.sin(2 * Math.PI * phase * det);
      l += s * amp * (1 - v * 0.08);
      r += s * amp * (0.92 + v * 0.05);
    }
  }
  // slow breathing
  const breathe = 0.78 + 0.22 * Math.sin(2 * Math.PI * t / 15.5);
  const fadeIn = Math.min(1, t / 3);
  const fadeOut = Math.min(1, (TOTAL - t) / 6);
  add(L, i, l * breathe * fadeIn * fadeOut * 0.5);
  add(R, i, r * breathe * fadeIn * fadeOut * 0.5);
}

// ---- sub-bass pulse on section boundaries --------------------------------
for (const s of starts) {
  for (let k = 0; k < Math.floor(SR * 1.8); k++) {
    const t = k / SR;
    const env = Math.exp(-t * 3.2);
    const f = hz(-31) * (1 - 0.02 * Math.sin(2 * Math.PI * t * 6));
    const v = Math.sin(2 * Math.PI * f * t) * env * 0.13;
    add(L, Math.round(s * SR) + k, v);
    add(R, Math.round(s * SR) + k, v);
  }
}

// ---- bells: one motif per act opening + soft off-beat plucks --------------
const PENT = [0, 2, 4, 7, 9, 12, 14, 16, 19, 21];
starts.forEach((s, act) => {
  const notes = act === 0 ? [0, 1, 2, 3] : [act % 5, (act + 2) % 6, (act + 4) % 7, 3];
  notes.forEach((n, j) => {
    const t0 = s + 0.4 + j * 0.62;
    const f = hz(-12 + (PENT[n % PENT.length] as number));
    for (let k = 0; k < Math.floor(SR * 2.4); k++) {
      const tt = k / SR;
      const env = Math.exp(-tt * 2.6) * (1 - Math.exp(-tt * 220));
      const v = (Math.sin(2 * Math.PI * f * tt) * 0.7 + Math.sin(2 * Math.PI * f * 2.01 * tt) * 0.22
        + Math.sin(2 * Math.PI * f * 3.98 * tt) * 0.08) * env * 0.075;
      add(L, Math.round(t0 * SR) + k, v * (j % 2 ? 0.8 : 1));
      add(R, Math.round(t0 * SR) + k, v * (j % 2 ? 1 : 0.8));
    }
  });
});

// ---- air bed: filtered noise, gently widening ----------------------------
let lp = 0, hp = 0;
for (let i = 0; i < N; i++) {
  const t = i / SR;
  const white = Math.random() * 2 - 1;
  lp += (white - lp) * 0.03;
  hp += (lp - hp) * 0.004;
  const band = lp - hp;
  const breath = 0.5 + 0.5 * Math.sin(2 * Math.PI * t / 23);
  add(L, i, band * 0.05 * breath);
  add(R, i, band * 0.05 * (1 - breath));
}

// ---- master: soft-knee limiter + normalise ------------------------------
let peak = 0;
for (let i = 0; i < N; i++) {
  const l = Math.tanh((L[i] as number) * 1.3);
  const r = Math.tanh((R[i] as number) * 1.3);
  L[i] = l; R[i] = r;
  peak = Math.max(peak, Math.abs(l), Math.abs(r));
}
const gain = 0.72 / (peak || 1);

// ---- write 24-bit WAV ---------------------------------------------------
const bytesPerSample = 3;
const dataSize = N * 2 * bytesPerSample;
const buf = Buffer.alloc(44 + dataSize);
buf.write('RIFF', 0);
buf.writeUInt32LE(36 + dataSize, 4);
buf.write('WAVE', 8);
buf.write('fmt ', 12);
buf.writeUInt32LE(16, 16);
buf.writeUInt16LE(1, 20);
buf.writeUInt16LE(2, 22);
buf.writeUInt32LE(SR, 24);
buf.writeUInt32LE(SR * 2 * bytesPerSample, 28);
buf.writeUInt16LE(2 * bytesPerSample, 32);
buf.writeUInt16LE(24, 34);
buf.write('data', 36);
buf.writeUInt32LE(dataSize, 40);
let p = 44;
for (let i = 0; i < N; i++) {
  for (const ch of [L, R]) {
    const v = Math.max(-1, Math.min(1, (ch[i] as number) * gain));
    const s = Math.round(v * 8388607);
    buf.writeIntLE(s, p, 3);
    p += 3;
  }
}
const path = resolve(OUT, 'score.wav');
writeFileSync(path, buf);
console.log(`score: ${TOTAL}s · ${SR} Hz · 24-bit stereo · ${(dataSize / 2 ** 20).toFixed(1)} MiB → ${path}`);
