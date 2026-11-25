/**
 * The score, composed against the actual picture timeline.
 *
 * Structure — one continuous piece in D minor, 8 acts:
 *   I    overture  : sub drone + sparse bell motif, no pulse
 *   II   artifact  : pulse enters at 50 BPM, plucked arpeggio
 *   III  identity  : brighter voicing, adds minor 9th
 *   IV   code mass : densest harmony, moving bass
 *   V    native    : darker, dissonant cluster, filtered noise
 *   VI   trust     : open 5ths, wide stereo, tension
 *   VII  verdict   : resolution to relative major (F), warm
 *   VIII coda      : tails off to a single bell
 *
 * Every cue is placed from the scene plan, so re-timing the picture re-times the
 * score automatically.
 */
import { mix, pad, pluck, bell, noiseSwell, subHit, Reverb, freq, normalize, wavStereo, SR, softClip } from "./score.js";
import type { PlannedScene } from "./render.js";
import { ACTS, type ActId } from "./timeline.js";

const CHORDS: Record<ActId, string[][]> = {
  overture: [["D2", "A2", "D3", "F3"], ["A#1", "F2", "A2", "D3"]],
  artifact: [["D2", "F2", "A2", "D3"], ["A#1", "F2", "A2", "C3"]],
  identity: [["G2", "D3", "A#3", "D4"], ["A2", "E3", "C4", "G4"]],
  code: [["D3", "F3", "A3", "C4", "E4"], ["A#2", "F3", "A3", "D4"], ["C3", "G3", "A#3", "E4"]],
  native: [["D2", "D3", "G#3", "B3"], ["C#2", "G#2", "E3", "A#3"]],
  trust: [["A1", "E2", "A2", "E3"], ["F1", "C2", "F2", "C3"]],
  verdict: [["F2", "C3", "F3", "A3"], ["A#2", "F3", "A3", "D4"]],
  coda: [["D2", "A2", "F3", "A3"], ["D2", "A2", "D3", "F3"]],
};

const BELL_SCALE = ["D4", "F4", "A4", "C5", "D5", "F5", "A5"];
const PLUCK_SCALE = ["D3", "F3", "A3", "D4", "F4", "A4", "C5", "D5"];

export interface ScoreResult {
  left: Float32Array;
  right: Float32Array;
  duration: number;
}

export function compose(plan: PlannedScene[], total: number): ScoreResult {
  const n = Math.ceil((total + 4) * SR);
  const L = new Float32Array(n);
  const R = new Float32Array(n);

  // ---- act-level harmonic pad
  const actWindows: { act: ActId; start: number; end: number }[] = [];
  for (const p of plan) {
    const last = actWindows[actWindows.length - 1];
    if (last && last.act === p.scene.act) last.end = p.start + p.dur;
    else actWindows.push({ act: p.scene.act, start: p.start, end: p.start + p.dur });
  }

  for (const w of actWindows) {
    const chords = CHORDS[w.act];
    const segLen = (w.end - w.start) / chords.length;
    chords.forEach((ch, ci) => {
      const start = w.start + ci * segLen - 0.4;
      const dur = segLen + 0.8;
      const gain = w.act === "native" ? 0.5 : w.act === "code" ? 0.55 : 0.6;
      const notes = ch.map(freq);
      const a = pad({ dur, freqs: notes, gain, attack: 1.4, release: 2.4, brightness: w.act === "native" ? 0.72 : 0.5 });
      const b = pad({ dur, freqs: notes.map((f) => f * 1.0018), gain: gain * 0.9, attack: 1.6, release: 2.6, brightness: 0.42 });
      mix(L, a, start, 1);
      mix(R, b, start, 1);
      // sub line
      const sub = subHit(Math.min(dur, 2.2), notes[0]! / 2, w.act === "native" ? 0.34 : 0.24);
      mix(L, sub, start, 1);
      mix(R, sub, start, 1);
    });
  }

  // ---- pulse section: acts II..VII
  const pulseActs: ActId[] = ["artifact", "identity", "code", "native", "trust", "verdict"];
  const beat = 60 / 50; // 50 BPM — slow, deliberate
  for (const p of plan) {
    if (!pulseActs.includes(p.scene.act)) continue;
    const local = p.start - Math.floor(p.start / beat) * beat;
    let t = p.start + (local > 0.001 ? beat - local : 0);
    let idx = Math.floor(t / beat);
    while (t < p.start + p.dur) {
      const accent = idx % 4 === 0;
      const f = PLUCK_SCALE[(idx * 3) % PLUCK_SCALE.length]!;
      const g = accent ? 0.16 : 0.085;
      const pl = pluck(freq(f), accent ? 2.6 : 1.9, g, 0.9972);
      mix(L, pl, t, 0.92);
      mix(R, pl, t + 0.008, 0.88);
      idx++;
      t += beat;
    }
  }

  // ---- bell motif: one bell every ~7 s during quiet acts, on scene starts
  for (const p of plan) {
    if (p.scene.act === "overture" || p.scene.act === "coda" || p.scene.act === "verdict") {
      const f = BELL_SCALE[Math.floor(p.start * 3) % BELL_SCALE.length]!;
      const b = bell(freq(f) * 2, 3.4, 0.16, 2.01, 2.6);
      mix(L, b, p.start + 0.15, 1);
      mix(R, b, p.start + 0.22, 1);
    }
  }

  // ---- transitions: noise swell + sub hit at each act boundary
  actWindows.forEach((w, i) => {
    const s = noiseSwell(2.6, 0.13, 620 + i * 130, 1.1);
    mix(L, s, w.start - 1.2, 1);
    mix(R, s, w.start - 1.1, 1);
    const hit = subHit(2.4, freq(CHORDS[w.act][0]![0]!) / 2, 0.4);
    mix(L, hit, w.start - 0.02, 1);
    mix(R, hit, w.start + 0.01, 1);
  });

  // ---- reverb send
  const revL = new Reverb(0).process(L, 0.34);
  const revR = new Reverb(23).process(R, 0.34);
  for (let i = 0; i < n; i++) {
    L[i] = softClip(revL[i]! * 0.92 + L[i]! * 0.55, 1.02);
    R[i] = softClip(revR[i]! * 0.92 + R[i]! * 0.55, 1.02);
  }

  // ---- master fade in/out
  const fadeIn = 2.4 * SR;
  const fadeOut = 3.4 * SR;
  for (let i = 0; i < n; i++) {
    let g = 1;
    if (i < fadeIn) g = i / fadeIn;
    const tailStart = n - fadeOut;
    if (i > tailStart) g *= Math.max(0, 1 - (i - tailStart) / fadeOut);
    L[i]! *= g;
    R[i]! *= g;
  }
  normalize(L, 0.9);
  normalize(R, 0.9);
  return { left: L, right: R, duration: total };
}

export function scoreToWav(plan: PlannedScene[], total: number): Buffer {
  const s = compose(plan, total);
  return wavStereo(s.left, s.right);
}

/** Duck the score under narration (called with the voice buffer). */
export function sidechainDuck(score: Float32Array, voice: Float32Array, depth = 0.55, smooth = 0.02): void {
  const n = Math.min(score.length, voice.length);
  const alpha = 1 - Math.exp(-1 / (smooth * SR));
  let env = 0;
  for (let i = 0; i < n; i++) {
    const v = Math.abs(voice[i]!);
    env += (v - env) * (v > env ? 0.004 : alpha);
    const g = 1 - depth * Math.min(1, env * 6);
    score[i]! *= g;
  }
}

export { ACTS };
