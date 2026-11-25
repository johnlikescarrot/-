#!/usr/bin/env python3
"""Synthesise a licence-free ambient bed + transition ticks for the video.
Usage: python3 scripts/music.py <seconds> <out.wav>
Deterministic: same input -> same bytes. Additive synthesis, no samples."""
import math, struct, sys, wave, random

def wav_write(path, frames, sr=48000):
    with wave.open(path, "wb") as w:
        w.setnchannels(2); w.setsampwidth(2); w.setframerate(sr)
        w.writeframes(b"".join(struct.pack("<hh", int(max(-1, min(1, l)) * 32767), int(max(-1, min(1, r)) * 32767)) for l, r in frames))

def main():
    dur = float(sys.argv[1]); out = sys.argv[2]
    sr = 48000
    n = int(dur * sr)
    # A minor 9 pad: Am9 -> Fmaj7 -> Cmaj9 -> G6, 8 s per chord
    chords = [(220.00, 261.63, 329.63, 493.88), (174.61, 220.00, 261.63, 329.63),
              (261.63, 329.63, 392.00, 493.88), (196.00, 246.94, 293.66, 392.00)]
    seg = 8.0
    frames = []
    random.seed(7)
    lfo_phase = 0.0
    for i in range(n):
        t = i / sr
        ci = int(t / seg) % len(chords)
        nxt = (ci + 1) % len(chords)
        fade = min(1.0, (t % seg) / 0.9, (seg - (t % seg)) / 0.9)
        l = r = 0.0
        for k, f in enumerate(chords[ci]):
            a = 0.030 / (1 + k * 0.35)
            det = 1.0 + 0.0007 * math.sin(2 * math.pi * 0.11 * t + k)
            ph = 2 * math.pi * f * det * t
            l += a * math.sin(ph + 0.12 * k)
            r += a * math.sin(ph - 0.12 * k)
        for k, f in enumerate(chords[nxt]):
            a = 0.014 / (1 + k * 0.4) * (1 - fade)
            l += a * math.sin(2 * math.pi * f * 0.5 * t)
            r += a * math.sin(2 * math.pi * f * 0.5 * t + 0.7)
        l *= fade; r *= fade
        # slow swell + airy noise floor
        swell = 0.75 + 0.25 * math.sin(2 * math.pi * t / 47.0)
        nz = (random.random() - 0.5) * 0.0016
        l = l * swell + nz; r = r * swell - nz
        frames.append((l, r))
    # transition ticks every 8 bars of the plan are added by build.ts, not here
    wav_write(out, frames)
    print(f"wrote {out} ({dur:.1f}s, peak {max(max(abs(a), abs(b)) for a, b in frames):.3f})")

if __name__ == "__main__":
    main()
