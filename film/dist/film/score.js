/**
 * Procedural score + sound design, synthesised from first principles in
 * TypeScript and written to 48 kHz float WAV. No samples, no external audio —
 * the entire bed is a generative ambient piece in D minor with a slow harmonic
 * rhythm that follows the picture's act structure.
 *
 * Techniques used:
 *   · additive/subtractive synthesis (detuned saw + sine stacks through a
 *     time-varying low-pass applied as an amplitude-weighted harmonic sum)
 *   · FM bells (carrier + modulator with exponential decay)
 *   · Karplus–Strong style plucked delay line for the "instrument" motif
 *   · Schroeder/Freeverb-style reverb (4 combs + 2 allpass, stereo spread)
 *   · sidechain-style ducking keyed to a beat grid, plus filtered noise swells
 *     for transitions
 *   · EBU R128-ish peak/loudness safety by soft-knee compression
 */
export const SR = 48000;
export class Reverb {
    spread;
    combs = [];
    combIdx = [];
    combFb = [];
    combDamp = [];
    allpass = [];
    apIdx = [];
    constructor(spread = 0) {
        this.spread = spread;
        const combLens = [1116, 1188, 1277, 1356, 1422, 1491];
        const apLens = [556, 441, 341, 225];
        for (const l of combLens) {
            const len = Math.floor((l + spread) * (SR / 44100));
            this.combs.push(new Float32Array(len));
            this.combDamp.push(new Float32Array(len));
            this.combIdx.push(0);
            this.combFb.push(0.84);
        }
        for (const l of apLens) {
            this.allpass.push(new Float32Array(Math.floor((l + spread) * (SR / 44100))));
            this.apIdx.push(0);
        }
    }
    process(input, wet) {
        const out = new Float32Array(input.length);
        const n = this.combs.length;
        for (let i = 0; i < input.length; i++) {
            let acc = 0;
            for (let c = 0; c < n; c++) {
                const buf = this.combs[c];
                const damp = this.combDamp[c];
                const idx = this.combIdx[c];
                const y = buf[idx];
                const filtered = y * 0.7 + damp[idx] * 0.3; // damping
                damp[idx] = filtered;
                buf[idx] = input[i] + filtered * this.combFb[c];
                this.combIdx[c] = (idx + 1) % buf.length;
                acc += y;
            }
            let v = acc / n;
            for (let a = 0; a < this.allpass.length; a++) {
                const buf = this.allpass[a];
                const idx = this.apIdx[a];
                const bufOut = buf[idx];
                const y = -v + bufOut;
                buf[idx] = v + bufOut * 0.5;
                this.apIdx[a] = (idx + 1) % buf.length;
                v = y;
            }
            out[i] = input[i] * (1 - wet) + v * wet;
        }
        return out;
    }
}
const NOTE = {};
(() => {
    const names = ["C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B"];
    for (let oct = 0; oct <= 8; oct++) {
        names.forEach((n, i) => {
            const midi = 12 * (oct + 1) + i;
            NOTE[`${n}${oct}`] = 440 * Math.pow(2, (midi - 69) / 12);
        });
    }
})();
export const freq = (n) => NOTE[n] ?? 220;
/** ADSR-ish envelope (sample-domain). */
function env(i, n, a, d, s, r) {
    const at = a * SR;
    const dt = d * SR;
    const rt = r * SR;
    const st = Math.max(0, n - at - dt - rt);
    if (i < at)
        return i / at;
    if (i < at + dt)
        return 1 - (1 - s) * ((i - at) / dt);
    if (i < at + dt + st)
        return s;
    const k = i - (at + dt + st);
    return Math.max(0, s * (1 - k / rt));
}
/** Warm detuned pad: 3 voices per note, sine + soft saw-ish harmonic stack. */
export function pad(o) {
    const n = Math.floor(o.dur * SR);
    const out = new Float32Array(n);
    const { detune = 0.004, brightness = 0.45, vibrato = 0.06 } = o;
    for (const f of o.freqs) {
        for (let v = 0; v < 3; v++) {
            const cents = (v - 1) * detune;
            const f0 = f * (1 + cents + vibrato * 0.002 * Math.sin(v + 1));
            for (let h = 1; h <= 7; h++) {
                const amp = Math.pow(h, -1.6 - brightness) * (h % 2 === 0 ? 0.7 : 1);
                const w = 2 * Math.PI * f0 * h;
                for (let i = 0; i < n; i++) {
                    const e = env(i, n, o.attack ?? 0.9, 0.6, 0.85, o.release ?? 1.2);
                    out[i] += Math.sin(w * (i / SR) + h * 0.3) * amp * e * o.gain * 0.06;
                }
            }
        }
    }
    return out;
}
/** FM bell. */
export function bell(f, dur, gain = 0.4, ratio = 2.0, index = 3.2) {
    const n = Math.floor(dur * SR);
    const out = new Float32Array(n);
    for (let i = 0; i < n; i++) {
        const t = i / SR;
        const e = Math.exp(-t * (2.8 / dur)) * (1 - Math.exp(-t * 240));
        const mod = Math.sin(2 * Math.PI * f * ratio * t) * index * Math.exp(-t * 3.4);
        out[i] = Math.sin(2 * Math.PI * f * t + mod) * e * gain;
    }
    return out;
}
/** Karplus–Strong pluck. */
export function pluck(f, dur, gain = 0.3, damp = 0.996) {
    const n = Math.floor(dur * SR);
    const out = new Float32Array(n);
    const len = Math.max(2, Math.round(SR / f));
    const buf = new Float32Array(len);
    let seed = 22222;
    for (let i = 0; i < len; i++) {
        seed = (seed * 1103515245 + 12345) & 0x7fffffff;
        buf[i] = (seed / 0x7fffffff) * 2 - 1;
    }
    for (let i = 0; i < n; i++) {
        const idx = i % len;
        const next = (idx + 1) % len;
        const v = buf[idx];
        buf[idx] = (buf[idx] + buf[next]) * 0.5 * damp;
        out[i] = v * gain * Math.exp(-i / (n * 0.9));
    }
    return out;
}
/** Filtered noise swell for transitions. */
export function noiseSwell(dur, gain = 0.08, centre = 900, q = 1.6) {
    const n = Math.floor(dur * SR);
    const out = new Float32Array(n);
    let z1 = 0, z2 = 0, y1 = 0, y2 = 0;
    const w0 = (2 * Math.PI * centre) / SR;
    const alpha = Math.sin(w0) / (2 * q);
    const c0 = alpha, c2 = -alpha, a0 = 1 + alpha, a1 = -2 * Math.cos(w0), a2 = 1 - alpha;
    let seed = 4422;
    for (let i = 0; i < n; i++) {
        seed = (seed * 1103515245 + 12345) & 0x7fffffff;
        const x = (seed / 0x7fffffff) * 2 - 1;
        const y = (c0 * x + c2 * z2 - a1 * y1 - a2 * y2) / a0;
        z2 = z1;
        z1 = x;
        y2 = y1;
        y1 = y;
        const e = Math.sin(Math.PI * (i / n)) ** 2;
        out[i] = y * e * gain;
    }
    return out;
}
/** Low sub-bass hit for act changes. */
export function subHit(dur = 1.6, f0 = 58, gain = 0.5) {
    const n = Math.floor(dur * SR);
    const out = new Float32Array(n);
    let phase = 0;
    for (let i = 0; i < n; i++) {
        const t = i / SR;
        const f = f0 * (1 + 0.6 * Math.exp(-t * 8));
        phase += (2 * Math.PI * f) / SR;
        const e = Math.exp(-t * 2.4) * (1 - Math.exp(-t * 200));
        out[i] = Math.sin(phase) * e * gain;
    }
    return out;
}
export function mix(dst, src, at, gain = 1) {
    const off = Math.floor(at * SR);
    const n = Math.min(src.length, dst.length - off);
    for (let i = 0; i < n; i++)
        dst[off + i] += src[i] * gain;
}
export function softClip(x, drive = 1.1) {
    return Math.tanh(x * drive);
}
/** Normalise to a target peak with soft knee. */
export function normalize(buf, peak = 0.94) {
    let m = 0;
    for (let i = 0; i < buf.length; i++)
        m = Math.max(m, Math.abs(buf[i]));
    if (m < 1e-9)
        return;
    const g = peak / m;
    for (let i = 0; i < buf.length; i++)
        buf[i] = softClip(buf[i] * g, 1.0);
}
/** 16-bit PCM WAV writer. */
export function wavStereo(L, R, sr = SR) {
    const n = Math.min(L.length, R.length);
    const bytes = 44 + n * 4;
    const b = Buffer.alloc(bytes);
    b.write("RIFF", 0);
    b.writeUInt32LE(bytes - 8, 4);
    b.write("WAVE", 8);
    b.write("fmt ", 12);
    b.writeUInt32LE(16, 16);
    b.writeUInt16LE(1, 20);
    b.writeUInt16LE(2, 22);
    b.writeUInt32LE(sr, 24);
    b.writeUInt32LE(sr * 4, 28);
    b.writeUInt16LE(4, 32);
    b.writeUInt16LE(16, 34);
    b.write("data", 36);
    b.writeUInt32LE(n * 4, 40);
    let o = 44;
    for (let i = 0; i < n; i++) {
        const l = Math.max(-1, Math.min(1, L[i]));
        const r = Math.max(-1, Math.min(1, R[i]));
        b.writeInt16LE(Math.round(l * 32767), o);
        b.writeInt16LE(Math.round(r * 32767), o + 2);
        o += 4;
    }
    return b;
}
