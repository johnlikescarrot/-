#!/usr/bin/env python3
"""Generate an original, deterministic ambient score (no external samples)."""
import argparse
import json
import math
import wave
from pathlib import Path

import numpy as np

p = argparse.ArgumentParser(description=__doc__)
p.add_argument("timeline", type=Path)
p.add_argument("output", type=Path)
a = p.parse_args()
timeline = json.loads(a.timeline.read_text(encoding="utf-8"))
sr = 48000
duration = float(timeline["duration"])
n = math.ceil(duration * sr)
left = np.zeros(n, dtype=np.float32)
right = np.zeros(n, dtype=np.float32)
rng = np.random.default_rng(20261125)
beat = 60.0 / 76.0
bar = beat * 8
chords = [
    [146.832, 174.614, 220.000],  # D minor
    [130.813, 164.814, 195.998],  # C major
    [116.541, 146.832, 174.614],  # B flat major
    [110.000, 130.813, 164.814],  # A minor
]
# Airy harmonic pads. Each segment has a gentle window, avoiding hard loops.
for block, start in enumerate(np.arange(0, duration, bar)):
    first = int(start * sr)
    count = min(int(bar * sr), n - first)
    if count <= 0:
        break
    t = np.arange(count, dtype=np.float32) / sr
    envelope = np.sin(np.pi * np.minimum(t / bar, 1.0)) ** 1.3
    for j, f in enumerate(chords[block % len(chords)]):
        phase = j * 1.17 + block * 0.24
        a1 = np.sin(2 * np.pi * f * t + phase)
        a2 = np.sin(2 * np.pi * (f * 2.002) * t + phase * 0.4)
        b1 = np.sin(2 * np.pi * (f * 1.002) * t + phase)
        b2 = np.sin(2 * np.pi * (f * 1.998) * t + phase * 0.6)
        left[first:first+count] += (0.035 * a1 + 0.008 * a2) * envelope
        right[first:first+count] += (0.035 * b1 + 0.008 * b2) * envelope

# Quiet, alternating plucks with a soft electric-piano-like harmonic.
for step, start in enumerate(np.arange(0, duration, beat / 2)):
    first = int(start * sr)
    count = min(int(1.3 * sr), n - first)
    if count <= 0:
        break
    t = np.arange(count, dtype=np.float32) / sr
    chord = chords[(int(start / bar)) % len(chords)]
    f = chord[[0, 2, 1, 2, 0, 1, 2, 1][step % 8]] * 2
    envelope = np.exp(-4.2 * t) * (1 - np.exp(-60 * t))
    tone = (np.sin(2*np.pi*f*t) + 0.22*np.sin(2*np.pi*2*f*t)) * envelope * 0.028
    pan = 0.28 + 0.44 * ((step % 4) / 3)
    left[first:first+count] += tone * (1 - pan)
    right[first:first+count] += tone * pan

# Soft low pulses, not a sampled drum loop.
for start in np.arange(0, duration, beat * 2):
    first = int(start * sr)
    count = min(int(0.48 * sr), n - first)
    if count <= 0:
        break
    t = np.arange(count, dtype=np.float32) / sr
    pulse = 0.035 * np.sin(2*np.pi*(65*t - 15*t*t)) * np.exp(-7*t)
    left[first:first+count] += pulse
    right[first:first+count] += pulse

# Restrained noise swells signal chapter transitions; seeded for reproducibility.
for chapter in timeline["chapters"][1:]:
    center = float(chapter["start"])
    first = max(0, int((center - 0.35) * sr))
    count = min(int(0.9 * sr), n - first)
    if count <= 0:
        continue
    t = np.arange(count, dtype=np.float32) / max(1, count - 1)
    noise = rng.standard_normal(count).astype(np.float32)
    # Two-tap smoothing softens the high-frequency noise.
    noise[1:] = 0.35 * noise[1:] + 0.65 * noise[:-1]
    swell = noise * (np.sin(np.pi*t) ** 2) * 0.006
    left[first:first+count] += swell
    right[first:first+count] += swell[::-1]

fade = min(int(3 * sr), n // 2)
if fade:
    ramp = np.linspace(0, 1, fade, dtype=np.float32)
    left[:fade] *= ramp; right[:fade] *= ramp
    left[-fade:] *= ramp[::-1]; right[-fade:] *= ramp[::-1]
peak = max(float(np.max(np.abs(left))), float(np.max(np.abs(right))), 1e-9)
scale = min(0.8 / peak, 1.0)
interleaved = np.empty((n, 2), dtype=np.int16)
interleaved[:, 0] = np.clip(left * scale * 32767, -32768, 32767).astype(np.int16)
interleaved[:, 1] = np.clip(right * scale * 32767, -32768, 32767).astype(np.int16)
a.output.parent.mkdir(parents=True, exist_ok=True)
with wave.open(str(a.output), "wb") as f:
    f.setnchannels(2); f.setsampwidth(2); f.setframerate(sr)
    f.writeframes(interleaved.tobytes())
print(f"Generated {duration:.2f}s original stereo score at 48 kHz: {a.output}")
