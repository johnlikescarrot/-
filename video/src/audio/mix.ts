/**
 * Score synthesiser — no samples, no libraries: every voice is generated here.
 *
 * Structure follows the picture: SCENES drives the chord map, so a new cut
 * automatically re-times the music.  Signals are float32 mono stems summed into
 * a stereo bus with a Schroeder reverb, kick-sidechain ducking and a soft
 * limiter before 16-bit PCM export.
 */

import { mkdirSync, writeFileSync } from "node:fs";
import { dirname, join } from "node:path";
import { fileURLToPath } from "node:url";
import { SCENES, TOTAL_DURATION } from "../film/scenes/index.ts";

const HERE = dirname(fileURLToPath(import.meta.url));
const OUT = join(HERE, "..", "..", "out");
const SR = 48000;
const N = Math.ceil(TOTAL_DURATION * SR) + SR; // one second tail

// ---------------------------------------------------------------- utilities
const TAU = Math.PI * 2;
const hz = (midi: number) => 440 * Math.pow(2, (midi - 69) / 12);
const clamp = (x: number, a: number, b: number) => (x < a ? a : x > b ? b : x);
const smooth = (x: number) => x * x * (3 - 2 * x);
/** linear ramp helper in seconds */
const seg = (t: number, a: number, b: number) => clamp((t - a) / Math.max(1e-6, b - a), 0, 1);

class Bus {
  L = new Float32Array(N);
  R = new Float32Array(N);
  add(i: number, v: number, pan = 0) {
    if (i < 0 || i >= N) return;
    const l = Math.cos((pan + 1) * Math.PI / 4);
    const r = Math.sin((pan + 1) * Math.PI / 4);
    this.L[i] += v * l;
    this.R[i] += v * r;
  }
}

// ---------------------------------------------------------------- instruments
interface Note { at: number; dur: number; midi: number; vel: number; pan: number }

/** Warm detuned-saw pad through a per-voice one-pole lowpass. */
function pad(bus: Bus, n: Note, cutoff = 1400, voices = 3) {
  const f0 = hz(n.midi);
  const start = Math.floor(n.at * SR);
  const len = Math.floor((n.dur + 1.6) * SR); // release tail
  let lp = 0;
  const detune = [-6, 0, 7].slice(0, voices);
  for (let k = 0; k < len; k++) {
    const t = k / SR;
    const env = Math.min(smooth(seg(t, 0, 0.5)), 1 - smooth(seg(t, n.dur, n.dur + 1.5)));
    if (env <= 0.0005) continue;
    let v = 0;
    for (let d = 0; d < detune.length; d++) {
      const f = f0 * Math.pow(2, detune[d] / 1200);
      const ph = (f * t) % 1;
      v += (2 * ph - 1) * (d === 1 ? 1 : 0.55);
    }
    v /= detune.length;
    const a = clamp(TAU * cutoff * (0.6 + 0.4 * env) / SR, 0.001, 1);
    lp += a * (v - lp);
    bus.add(start + k, lp * env * n.vel * 0.5, n.pan);
  }
}

/** Sub bass: sine plus a little saw grit, always mono-centred. */
function bass(bus: Bus, n: Note) {
  const f0 = hz(n.midi);
  const start = Math.floor(n.at * SR);
  const len = Math.floor((n.dur + 0.4) * SR);
  for (let k = 0; k < len; k++) {
    const t = k / SR;
    const env = Math.min(smooth(seg(t, 0, 0.06)), 1 - seg(t, n.dur, n.dur + 0.38));
    const ph = (f0 * t) % 1;
    const v = Math.sin(TAU * ph) * 0.85 + (2 * ph - 1) * 0.15;
    bus.add(start + k, v * env * n.vel * 0.42, 0);
  }
}

/** FM pluck for data-like arpeggios. */
function pluck(bus: Bus, n: Note) {
  const f0 = hz(n.midi);
  const start = Math.floor(n.at * SR);
  const len = Math.floor(1.2 * SR);
  for (let k = 0; k < len; k++) {
    const t = k / SR;
    const env = Math.exp(-t * 7.5) * seg(t, 0, 0.004);
    const mod = Math.sin(TAU * f0 * 2 * t) * 1.8 * Math.exp(-t * 9);
    const v = Math.sin(TAU * f0 * t + mod);
    bus.add(start + k, v * env * n.vel * 0.16, n.pan);
  }
}

/** Bell / marimba for chapter opens. */
function bell(bus: Bus, n: Note) {
  const f0 = hz(n.midi);
  const start = Math.floor(n.at * SR);
  const len = Math.floor(3.2 * SR);
  for (let k = 0; k < len; k++) {
    const t = k / SR;
    const env = Math.exp(-t * 2.1);
    const v =
      Math.sin(TAU * f0 * t) * 0.6 +
      Math.sin(TAU * f0 * 2.76 * t) * 0.24 * Math.exp(-t * 3) +
      Math.sin(TAU * f0 * 5.4 * t) * 0.1 * Math.exp(-t * 6);
    bus.add(start + k, v * env * n.vel * 0.14, n.pan);
  }
}

/** Kick with pitch drop; also returns its envelope for sidechain ducking. */
function kick(bus: Bus, at: number, vel: number, duck: Float32Array) {
  const start = Math.floor(at * SR);
  const len = Math.floor(0.55 * SR);
  for (let k = 0; k < len; k++) {
    const t = k / SR;
    const f = 46 + 70 * Math.exp(-t * 28);
    const env = Math.exp(-t * 9) * seg(t, 0, 0.003);
    const v = Math.sin(TAU * f * t) * env * vel * 0.55;
    bus.add(start + k, v, 0);
    const idx = start + k;
    if (idx < N) duck[idx] = Math.max(duck[idx], Math.exp(-t * 7) * vel);
  }
}

/** Filtered noise riser into a cut. */
function riser(bus: Bus, at: number, dur: number, vel = 1) {
  const start = Math.floor(at * SR);
  const len = Math.floor(dur * SR);
  let lp = 0;
  let seed = 0x2545f491;
  for (let k = 0; k < len; k++) {
    const t = k / SR;
    const p = t / dur;
    seed = (seed * 1664525 + 1013904223) >>> 0;
    const noise = (seed / 4294967296) * 2 - 1;
    const a = clamp(0.02 + 0.5 * p * p, 0.001, 1);
    lp += a * (noise - lp);
    const env = p * p * (1 - seg(p, 0.94, 1));
    bus.add(start + k, lp * env * vel * 0.22, Math.sin(p * 6) * 0.3);
  }
}

/** Ticker: a short bright click used on numbers/columns. */
function tick(bus: Bus, at: number, vel = 1, pan = 0) {
  const start = Math.floor(at * SR);
  const len = Math.floor(0.03 * SR);
  for (let k = 0; k < len; k++) {
    const t = k / SR;
    const v = Math.sin(TAU * 2600 * t) * Math.exp(-t * 260) * vel;
    bus.add(start + k, v * 0.07, pan);
  }
}

/** Long airy noise bed for tension under dense scenes. */
function air(bus: Bus, at: number, dur: number, vel = 1, pan = 0) {
  const start = Math.floor(at * SR);
  const len = Math.floor(dur * SR);
  let lp = 0;
  let hp = 0;
  let seed = 0x9e3779b9;
  for (let k = 0; k < len; k++) {
    const t = k / SR;
    seed = (seed * 1103515245 + 12345) >>> 0;
    const noise = (seed / 4294967296) * 2 - 1;
    lp += 0.08 * (noise - lp);
    hp = lp - hp * 0.002;
    const env = Math.min(smooth(seg(t, 0, 1.4)), 1 - smooth(seg(t, dur - 1.6, dur)));
    bus.add(start + k, hp * env * vel * 0.5, pan);
  }
}

// ---------------------------------------------------------------- harmony
/** One chord per scene, in D minor with modal colour. */
const CHORD: Record<string, number[]> = {
  coldopen: [50, 57, 62, 65],      // Dm
  thesis: [46, 53, 58, 62],        // Bb
  "02-container": [43, 50, 55, 58], // Gm
  "03-manifest": [48, 55, 60, 64],  // C
  "04-dex": [41, 48, 53, 57],       // F
  "05-native": [45, 52, 57, 60],    // Am
  "06-network": [50, 57, 62, 65],   // Dm
  "07-capability": [46, 53, 58, 62],// Bb
  "08-payloads": [48, 55, 60, 64],  // C
  "09-method": [43, 50, 55, 58],    // Gm
  "10-close": [50, 57, 62, 66],     // Dm add9 -> resolve
};

const bus = new Bus();
const duck = new Float32Array(N);

// --- pads + bass per scene, with a crossfade into the next chord -----------
SCENES.forEach((sc, i) => {
  const chord = CHORD[sc.id] ?? CHORD.thesis;
  const next = SCENES[(i + 1) % SCENES.length];
  const chordLen = Math.min(8, sc.duration);
  const reps = Math.ceil((sc.duration + 1) / chordLen);
  for (let r = 0; r < reps; r++) {
    const at = sc.start + r * chordLen;
    if (at > TOTAL_DURATION) break;
    const dur = Math.min(chordLen, sc.start + sc.duration - at + 0.8);
    if (dur < 0.8) continue;
    const build = smooth(seg(sc.start, 0, 2.4));
    chord.forEach((m, vi) => {
      // melody voice drifts up a step on the 3rd repeat for movement
      const midi = vi === chord.length - 1 && r % 2 === 1 ? m + 2 : m;
      pad(bus, { at, dur, midi: midi + 12, vel: 0.5 * build, pan: (vi - 1.5) * 0.28 }, 900 + vi * 260);
    });
    bass(bus, { at, dur: Math.min(dur, 3.2), midi: chord[0] - 12, vel: 0.8 * build, pan: 0 });
  }
  // chapter lift: bell on the chord root + riser into the cut
  bell(bus, { at: sc.start + 0.12, dur: 1, midi: chord[0] + 24, vel: 0.9, pan: -0.2 });
  if (i > 0) {
    riser(bus, sc.start - 1.5, 1.5, 0.9);
    bell(bus, { at: sc.start, dur: 1, midi: chord[3] + 24, vel: 0.5, pan: 0.3 });
  }
  air(bus, sc.start, sc.duration, 0.35, Math.sin(i) * 0.4);
});

// --- heartbeat: 58 BPM pulse, doubles in dense chapters -------------------
const beat = 60 / 58;
for (let t = 4; t < TOTAL_DURATION - 2; t += beat) {
  const sc = SCENES.find((s) => t >= s.start && t < s.start + s.duration);
  const dense = sc && ["04-dex", "05-native", "07-capability"].includes(sc.id);
  kick(bus, t, dense ? 0.75 : 0.45, duck);
  if (dense) kick(bus, t + beat * 0.5, 0.32, duck);
}

// --- data arpeggio: 16ths that follow the chord, gated by scene ----------
const arpScenes = new Set(["04-dex", "05-native", "07-capability", "08-payloads"]);
SCENES.forEach((sc) => {
  if (!arpScenes.has(sc.id)) return;
  const chord = CHORD[sc.id] ?? CHORD.thesis;
  const step = 60 / 58 / 4;
  let i = 0;
  for (let t = sc.start + 1.2; t < sc.start + sc.duration - 0.5; t += step, i++) {
    const m = chord[i % chord.length] + 24 + (i % 8 === 7 ? 12 : 0);
    const fade = smooth(seg(t, sc.start + 1.2, sc.start + 3)) * (1 - smooth(seg(t, sc.start + sc.duration - 2, sc.start + sc.duration)));
    pluck(bus, { at: t, dur: 0.3, midi: m, vel: 0.85 * fade, pan: ((i % 4) - 1.5) * 0.35 });
  }
});

// --- number ticks on the big reveals -------------------------------------
const ticks: [number, number][] = [
  [2.6, 4], [3.4, 4], [4.2, 4], [5.0, 4],          // cold open stats
  [27.6, 5], [28.0, 5], [28.4, 5], [28.8, 5],      // container rows
  [53.9, 3], [54.3, 3], [54.7, 3],                 // manifest rings
  [78.4, 6], [79.0, 6], [79.6, 6], [80.2, 6],      // dex stats
  [104.6, 4], [105.2, 4], [105.8, 4], [106.4, 4],  // native checks
  [131.2, 7], [131.8, 7], [132.4, 7],              // hosts
  [156.6, 8], [157.0, 8], [157.4, 8],              // capability bars
];
ticks.forEach(([t, n]) => {
  for (let i = 0; i < n; i++) tick(bus, t + i * 0.07, 1 - i * 0.1, (i % 2 ? 0.4 : -0.4));
});

// ---------------------------------------------------------------- reverb
function reverb(L: Float32Array, R: Float32Array) {
  // Schroeder: 4 parallel combs + 2 series allpasses per channel
  const combs = [1557, 1617, 1491, 1422];
  const aps = [556, 441];
  const outL = new Float32Array(N);
  const outR = new Float32Array(N);
  const process = (src: Float32Array, dst: Float32Array, spread: number) => {
    const acc = new Float32Array(N);
    for (const c of combs) {
      const d = Math.floor((c + spread) * SR / 44100);
      const buf = new Float32Array(d);
      let idx = 0;
      for (let i = 0; i < N; i++) {
        const y = buf[idx];
        buf[idx] = src[i] + y * 0.79;
        acc[i] += y * 0.25;
        idx = (idx + 1) % d;
      }
    }
    for (const a of aps) {
      const d = Math.floor((a + spread) * 0.7 * SR / 44100);
      const buf = new Float32Array(d);
      let idx = 0;
      for (let i = 0; i < N; i++) {
        const b = buf[idx];
        const y = -acc[i] + b;
        buf[idx] = acc[i] + b * 0.5;
        acc[i] = y;
        idx = (idx + 1) % d;
      }
    }
    dst.set(acc);
  };
  process(L, outL, 0);
  process(R, outR, 23);
  return { L: outL, R: outR };
}

// ---------------------------------------------------------------- master
const wet = reverb(bus.L, bus.R);
const mixL = new Float32Array(N);
const mixR = new Float32Array(N);
let peak = 0;
for (let i = 0; i < N; i++) {
  const t = i / SR;
  // sidechain: pull the pad/bass bus down on each kick
  const d = 1 - 0.32 * clamp(duck[i], 0, 1);
  const fadeIn = smooth(seg(t, 0, 2.5));
  const fadeOut = 1 - smooth(seg(t, TOTAL_DURATION - 3.2, TOTAL_DURATION + 0.8));
  const master = fadeIn * fadeOut;
  const l = (bus.L[i] * 0.82 + wet.L[i] * 0.42) * d * master;
  const r = (bus.R[i] * 0.82 + wet.R[i] * 0.42) * d * master;
  mixL[i] = Math.tanh(l * 1.25) * 0.92;
  mixR[i] = Math.tanh(r * 1.25) * 0.92;
  peak = Math.max(peak, Math.abs(mixL[i]), Math.abs(mixR[i]));
}
const gain = 0.89 / Math.max(1e-6, peak);

function writeWav(path: string) {
  const frames = Math.floor((TOTAL_DURATION + 1) * SR);
  const buf = Buffer.alloc(44 + frames * 4);
  buf.write("RIFF", 0);
  buf.writeUInt32LE(36 + frames * 4, 4);
  buf.write("WAVE", 8);
  buf.write("fmt ", 12);
  buf.writeUInt32LE(16, 16);
  buf.writeUInt16LE(1, 20);
  buf.writeUInt16LE(2, 22);
  buf.writeUInt32LE(SR, 24);
  buf.writeUInt32LE(SR * 4, 28);
  buf.writeUInt16LE(4, 32);
  buf.writeUInt16LE(16, 34);
  buf.write("data", 36);
  buf.writeUInt32LE(frames * 4, 40);
  for (let i = 0; i < frames; i++) {
    const l = clamp(mixL[i] * gain, -1, 1);
    const r = clamp(mixR[i] * gain, -1, 1);
    buf.writeInt16LE(Math.round(l * 32767), 44 + i * 4);
    buf.writeInt16LE(Math.round(r * 32767), 46 + i * 4);
  }
  writeFileSync(path, buf);
  return buf.length;
}

mkdirSync(OUT, { recursive: true });
const p = join(OUT, "score.wav");
const bytes = writeWav(p);
console.log(`score: ${p} · ${(bytes / 1e6).toFixed(1)} MB · ${(TOTAL_DURATION + 1).toFixed(1)}s @ ${SR} Hz stereo`);
