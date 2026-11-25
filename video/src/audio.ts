/*
 * Sinh am thanh dien tu (WAV PCM 16-bit stereo, 44.1 kHz) toan bo trong JS.
 * Nhac nen dau tien (pad + bass + arpeggio + melody) + dau nhac (kick/snare/hihat).
 * Khong can te dich ngoai / khong can may chu.
 */

const SR = 44100;
const TWO_PI = 2 * Math.PI;

function midiToFreq(m: number): number {
  return 440 * Math.pow(2, (m - 69) / 12);
}

function clamp(v: number, lo: number, hi: number): number {
  return Math.max(lo, Math.min(hi, v));
}

function envADSR(t: number, start: number, end: number, a: number, d: number, s: number, r: number): number {
  let x = t - start;
  if (x < 0) return 0;
  const dur = end - start;
  const relStart = end - r;
  if (x < a) return x / a;
  if (x < a + d) return 1 - (1 - s) * (x - a) / d;
  if (t < end) return s;
  if (t < relStart + r) return s * (1 - (t - relStart) / r);
  return 0;
}

function osc(inst: string, f: number, t: number, vib: number = 0): number {
  const ft = vib ? f * (1 + 0.006 * Math.sin(TWO_PI * 5.5 * t)) : f;
  const s = Math.sin(TWO_PI * ft * t);
  const s2 = Math.sin(TWO_PI * ft * 2 * t);
  const s3 = Math.sin(TWO_PI * ft * 0.5 * t);
  const sq = s >= 0 ? 1 : -1;
  const saw = 2 * ((t * ft) % 1) - 1;
  const noise = () => Math.random() * 2 - 1;

  switch (inst) {
    case 'pad':
      return 0.45 * s + 0.25 * s2 + 0.15 * s3;
    case 'pad2':
      return 0.4 * s + 0.3 * s2 + 0.2 * sq;
    case 'bass':
      return 0.7 * s + 0.45 * Math.sin(TWO_PI * (f / 2) * t) + 0.15 * sq;
    case 'arp':
      return 0.5 * s + 0.25 * s2;
    case 'melody':
      return 0.55 * s + 0.2 * s2 + 0.1 * Math.sin(TWO_PI * ft * 3 * t);
    case 'kick': {
      // toc do giam tu 140 Hz ve 40 Hz
      const fk = 140 * Math.pow(40 / 140, t * 6);
      return 0.9 * Math.sin(TWO_PI * fk * t);
    }
    case 'snare':
      return 0.35 * Math.sin(TWO_PI * 180 * t) + 0.55 * noise();
    case 'hihat':
      return 0.5 * noise();
    default:
      return s;
  }
}

/**
 * Tat ca su kien am thanh: {start, end, midi, vel, inst, vib?}
 */
interface NoteEvent {
  start: number;
  end: number;
  midi: number;
  vel: number;
  inst: string;
  vib?: boolean;
}

interface PercEvent {
  t: number;
  dur: number;
  inst: string;
  vel: number;
}

/**
 * Xay dung danh sach su kien am nhac theo thoi gian (giay).
 */
function buildEvents(durationSec: number): { notes: NoteEvent[]; percs: PercEvent[] } {
  const notes: NoteEvent[] = [];
  const percs: PercEvent[] = [];

  // Chu trinh hop am (A minor): Am -> F -> C -> G, moi hop 4 giay
  const progression: number[][] = [
    [57, 60, 64], // Am (A C E)
    [65, 69, 72], // F (F A C)
    [60, 64, 67], // C (C E G)
    [67, 71, 74], // G (G B D)
  ];

  const chordDur = 4;
  const numChords = Math.ceil(durationSec / chordDur);

  // Nhac kep (percussion)
  for (let t = 0; t < durationSec; t += 0.5) {
    const beat = Math.floor(t * 2); // 0,1,2,... moi 0.5s
    if (beat % 4 === 0) percs.push({ t, dur: 0.6, inst: 'kick', vel: 1 });
    else if (beat % 4 === 2) percs.push({ t, dur: 0.4, inst: 'snare', vel: 0.9 });
    percs.push({ t, dur: 0.12, inst: 'hihat', vel: 0.5 });
  }

  for (let i = 0; i < numChords; i++) {
    const t0 = i * chordDur;
    const t1 = Math.min(t0 + chordDur, durationSec);
    const chord = progression[i % progression.length];
    const [root, third, fifth] = chord;

    // Pad: hop am dan, cong dan cham
    for (const m of chord) {
      notes.push({ start: t0, end: t1, midi: m, vel: 0.5, inst: 'pad' });
      notes.push({ start: t0, end: t1, midi: m - 12, vel: 0.35, inst: 'pad2' });
    }

    // Bass: nut goc, pluck moi 1 giay
    for (let bt = t0; bt < t1; bt += 1) {
      notes.push({ start: bt, end: Math.min(bt + 0.9, t1), midi: root - 24, vel: 0.8, inst: 'bass' });
    }

    // Arpeggio: goc, nam, goc, thu, nam, goc (8nhac)
    const arpPattern = [root, fifth, root + 12, third + 12, fifth + 12, root + 12];
    for (let j = 0; j < arpPattern.length; j++) {
      const at = t0 + j * 0.5;
      if (at >= t1) break;
      notes.push({ start: at, end: Math.min(at + 0.42, t1), midi: arpPattern[j], vel: 0.55, inst: 'arp' });
    }

    // Melody: mot buoc don lap lai tren moi hop
    const melody = [root + 12, third + 12, fifth + 12, root + 12, fifth + 12, third + 12, root + 12];
    for (let j = 0; j < melody.length; j++) {
      const mt = t0 + j * 0.57;
      if (mt >= t1) break;
      notes.push({ start: mt, end: Math.min(mt + 0.5, t1), midi: melody[j], vel: 0.75, inst: 'melody', vib: true });
    }
  }

  return { notes, percs };
}

/**
 * Render am thanh thanh Float32Array mono (SR * durationSec).
 */
function renderAudio(durationSec: number): Float32Array {
  const totalSamples = Math.floor(durationSec * SR);
  const buffer = new Float32Array(totalSamples);
  const { notes, percs } = buildEvents(durationSec);

  const add = (t: number, value: number) => {
    const i = Math.floor(t * SR);
    if (i >= 0 && i < totalSamples) buffer[i] += value;
  };

  // Su kien am thanh (co bao dieu khien, tong qua tat ca sample)
  for (const ev of notes) {
    const f = midiToFreq(ev.midi);
    const start = ev.start;
    const end = ev.end;
    const s0 = Math.max(0, Math.floor(start * SR));
    const s1 = Math.min(totalSamples, Math.floor(end * SR));
    const a = 0.06, d = 0.12, s = 0.6, r = 0.25;
    for (let s = s0; s < s1; s++) {
      const t = s / SR;
      const e = envADSR(t, start, end, a, d, s, r);
      if (e <= 0) continue;
      let sample = osc(ev.inst, f, t, ev.vib ? 1 : 0) * e * ev.vel;
      // giong lai phu hop voi hop am (khong loc thuc te, tam giac nhuan)
      sample = Math.tanh(sample * 1.4);
      buffer[s] += sample * 0.5;
    }
  }

  // Dau nhac (ngan, khong bao phuc tap)
  for (const ev of percs) {
    const s0 = Math.max(0, Math.floor(ev.t * SR));
    const s1 = Math.min(totalSamples, Math.floor((ev.t + ev.dur) * SR));
    const f = ev.inst === 'kick' ? 140 : ev.inst === 'snare' ? 220 : 6000;
    for (let s = s0; s < s1; s++) {
      const t = s / SR;
      const x = t - ev.t;
      const e = Math.exp(-x * 18); // giam dan nhanh
      if (e <= 0.01) continue;
      let sample = 0;
      if (ev.inst === 'kick') {
        const fk = 150 * Math.pow(35 / 150, x * 10);
        sample = 1.0 * Math.sin(TWO_PI * fk * t) * e;
      } else if (ev.inst === 'snare') {
        sample = (0.4 * Math.sin(TWO_PI * 200 * t) + 0.8 * (Math.random() * 2 - 1)) * e;
      } else {
        // hihat: nhieu va tan cao
        sample = (0.9 * (Math.random() * 2 - 1)) * e * (x < 0.05 ? 1 : 0.15);
      }
      buffer[s] += sample * ev.vel * 0.35;
    }
  }

  // gian nam (master gain nhan nhat, limiter nhan)
  const peak = 1.0;
  let max = 0;
  for (let i = 0; i < totalSamples; i++) max = Math.max(max, Math.abs(buffer[i]));
  const gain = max > peak ? peak / max : 1.0;
  for (let i = 0; i < totalSamples; i++) buffer[i] = clamp(buffer[i] * gain, -1, 1);

  return buffer;
}

/**
 * Xay dung WAV header (PCM 16-bit stereo) + nhan du lieu.
 */
export function synthesizeWav(durationSec: number): Buffer {
  const mono = renderAudio(durationSec);
  const totalSamples = mono.length;
  // stereo: copy mono sang hai kenh
  const samples = new Int16Array(totalSamples * 2);
  const maxVal = 32767 * 0.9;
  for (let i = 0; i < totalSamples; i++) {
    const v = Math.floor(clamp(mono[i], -1, 1) * maxVal);
    samples[i * 2] = v;
    samples[i * 2 + 1] = v;
  }

  const byteRate = SR * 2 * 2; // sampleRate * blockAlign
  const blockAlign = 4;
  const dataSize = samples.length * 2;
  const header = Buffer.alloc(44);
  header.write('RIFF', 0, 'ascii');
  header.writeUInt32LE(36 + dataSize, 4);
  header.write('WAVE', 8, 'ascii');
  header.write('fmt ', 12, 'ascii');
  header.writeUInt32LE(16, 16);
  header.writeUInt16LE(1, 20); // PCM
  header.writeUInt16LE(2, 22); // stereo
  header.writeUInt32LE(SR, 24);
  header.writeUInt32LE(byteRate, 28);
  header.writeUInt16LE(blockAlign, 32);
  header.writeUInt16LE(16, 34); // bits per sample
  header.write('data', 36, 'ascii');
  header.writeUInt32LE(dataSize, 40);

  const out = Buffer.concat([header, Buffer.from(samples.buffer)]);
  return out;
}
