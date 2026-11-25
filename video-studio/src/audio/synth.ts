import { writeFileSync } from "node:fs";
import type { ChapterTiming } from "../types.js";

const SAMPLE_RATE = 48_000;

/**
 * Pure TypeScript 48kHz stereo PCM synthesizer generating:
 * 1) Warm D-Dorian documentary harmonic pad (Dm9 -> G13 -> Bbmaj7 -> C6)
 * 2) Soft felt sub-harmonic pulse
 * 3) Crystalline bell-harmonic chimes synchronized precisely to chapter transitions
 */
export function synthesizeSoundtrackWav(chapters: ChapterTiming[], outWavPath: string): void {
  const totalSec = chapters[chapters.length - 1].endSec;
  const totalSamples = Math.ceil(totalSec * SAMPLE_RATE);
  const left = new Float32Array(totalSamples);
  const right = new Float32Array(totalSamples);

  // Warm chord cycle (frequencies in Hz, D Dorian academic aesthetic)
  const chords = [
    [146.83, 220.0, 261.63, 329.63, 440.0], // Dm9
    [130.81, 196.0, 246.94, 293.66, 392.0], // Cmaj9
    [116.54, 174.61, 220.0, 293.66, 349.23], // Bbmaj7
    [110.0, 164.81, 220.0, 261.63, 329.63], // Am7
  ];

  const chordLenSec = 8.0;
  const twoPi = Math.PI * 2;

  for (let i = 0; i < totalSamples; i++) {
    const t = i / SAMPLE_RATE;
    const cyclePos = t / chordLenSec;
    const cIdx = Math.floor(cyclePos) % chords.length;
    const nIdx = (cIdx + 1) % chords.length;
    const frac = cyclePos - Math.floor(cyclePos);
    // Smooth 2.5s crossfade at end of each chord bar
    const blend = frac > 0.6875 ? (frac - 0.6875) / 0.3125 : 0;
    const sBlend = blend * blend * (3 - 2 * blend);

    const curChord = chords[cIdx];
    const nxtChord = chords[nIdx];

    let sampleL = 0;
    let sampleR = 0;

    // Pad voices with gentle stereo detune & warm LFO breath
    const breath = 0.72 + 0.28 * Math.sin(twoPi * 0.09 * t);
    for (let v = 0; v < curChord.length; v++) {
      const f = curChord[v] * (1 - sBlend) + nxtChord[v] * sBlend;
      const pan = (v / (curChord.length - 1)) * 0.7 - 0.35;
      const detune = 1 + (v % 2 === 0 ? 0.0018 : -0.0018);
      const osc1 = Math.sin(twoPi * f * t + v * 1.1);
      const osc2 = Math.sin(twoPi * f * detune * t + v * 2.3) * 0.45;
      const harmonic2 = Math.sin(twoPi * f * 2 * t) * 0.08;
      const amp = (0.14 / curChord.length) * breath;
      const val = (osc1 + osc2 + harmonic2) * amp;
      sampleL += val * (0.5 - pan * 0.5);
      sampleR += val * (0.5 + pan * 0.5);
    }

    // Subtle soft telemetry pulse every 1.0s (filtered warm sine thump)
    const beatT = t % 1.0;
    const pulseEnv = Math.exp(-beatT * 9.5) * Math.min(1, beatT / 0.012);
    const rootHz = (curChord[0] * (1 - sBlend) + nxtChord[0] * sBlend) * 0.5;
    const pulse = Math.sin(twoPi * rootHz * t) * pulseEnv * 0.095;
    sampleL += pulse;
    sampleR += pulse;

    // Master fade-in (1.5s) & fade-out (2.5s)
    const fadeIn = Math.min(1, t / 1.5);
    const fadeOut = Math.min(1, Math.max(0, (totalSec - t) / 2.5));
    const env = fadeIn * fadeOut;

    left[i] = sampleL * env;
    right[i] = sampleR * env;
  }

  // Layer delicate crystalline bell chimes at each chapter start
  const chimeNotes = [587.33, 659.25, 880.0, 1046.5];
  chapters.forEach((ch, idx) => {
    const startSample = Math.floor(ch.startSec * SAMPLE_RATE);
    const durSamples = Math.floor(2.6 * SAMPLE_RATE);
    const baseFreq = chimeNotes[idx % chimeNotes.length];
    for (let k = 0; k < durSamples && startSample + k < totalSamples; k++) {
      const kt = k / SAMPLE_RATE;
      const att = Math.min(1, kt / 0.015);
      const dec = Math.exp(-kt * 2.4);
      const bell =
        (Math.sin(twoPi * baseFreq * kt) * 0.7 +
          Math.sin(twoPi * baseFreq * 1.5 * kt) * 0.28 * Math.exp(-kt * 4.0) +
          Math.sin(twoPi * baseFreq * 2.0 * kt) * 0.14 * Math.exp(-kt * 5.5)) *
        att *
        dec *
        0.11;
      const pan = idx % 2 === 0 ? 0.58 : 0.42;
      left[startSample + k] += bell * (1 - pan * 0.3);
      right[startSample + k] += bell * (0.7 + pan * 0.3);
    }
  });

  // Encode 16-bit PCM Stereo WAV
  const dataBytes = totalSamples * 4;
  const buf = Buffer.alloc(44 + dataBytes);
  buf.write("RIFF", 0);
  buf.writeUInt32LE(36 + dataBytes, 4);
  buf.write("WAVE", 8);
  buf.write("fmt ", 12);
  buf.writeUInt32LE(16, 16);
  buf.writeUInt16LE(1, 20); // PCM
  buf.writeUInt16LE(2, 22); // Stereo
  buf.writeUInt32LE(SAMPLE_RATE, 24);
  buf.writeUInt32LE(SAMPLE_RATE * 4, 28);
  buf.writeUInt16LE(4, 32);
  buf.writeUInt16LE(16, 34);
  buf.write("data", 36);
  buf.writeUInt32LE(dataBytes, 40);

  let offset = 44;
  for (let i = 0; i < totalSamples; i++) {
    const l = Math.max(-0.98, Math.min(0.98, left[i]));
    const r = Math.max(-0.98, Math.min(0.98, right[i]));
    buf.writeInt16LE(Math.round(l * 32767), offset);
    buf.writeInt16LE(Math.round(r * 32767), offset + 2);
    offset += 4;
  }
  writeFileSync(outWavPath, buf);
}
