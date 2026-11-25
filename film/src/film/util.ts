/** Deterministic math, easing and pseudo-random helpers (no external deps). */

export const clamp = (v: number, a = 0, b = 1): number => (v < a ? a : v > b ? b : v);
export const lerp = (a: number, b: number, t: number): number => a + (b - a) * t;
export const inv = (t: number, a: number, b: number): number => (b === a ? (t >= b ? 1 : 0) : clamp((t - a) / (b - a)));
export const smooth = (t: number): number => {
  const x = clamp(t);
  return x * x * (3 - 2 * x);
};
export const smoother = (t: number): number => {
  const x = clamp(t);
  return x * x * x * (x * (x * 6 - 15) + 10);
};

export const ease = {
  out: (t: number) => 1 - Math.pow(1 - clamp(t), 3),
  outQuint: (t: number) => 1 - Math.pow(1 - clamp(t), 5),
  inOut: (t: number) => (clamp(t) < 0.5 ? 4 * Math.pow(clamp(t), 3) : 1 - Math.pow(-2 * clamp(t) + 2, 3) / 2),
  inOutQuint: (t: number) => {
    const x = clamp(t);
    return x < 0.5 ? 16 * x ** 5 : 1 - Math.pow(-2 * x + 2, 5) / 2;
  },
  expo: (t: number) => (clamp(t) === 1 ? 1 : 1 - Math.pow(2, -10 * clamp(t))),
  back: (t: number, s = 1.70158) => {
    const x = clamp(t) - 1;
    return x * x * ((s + 1) * x + s) + 1;
  },
};

/** mulberry32 — deterministic RNG so every render is byte-stable. */
export function rng(seed: number): () => number {
  let a = seed >>> 0;
  return () => {
    a |= 0;
    a = (a + 0x6d2b79f5) | 0;
    let t = Math.imul(a ^ (a >>> 15), 1 | a);
    t = (t + Math.imul(t ^ (t >>> 7), 61 | t)) ^ t;
    return ((t ^ (t >>> 14)) >>> 0) / 4294967296;
  };
}

/** Hash a string into a stable 32-bit integer. */
export function hash(s: string): number {
  let h = 2166136261 >>> 0;
  for (let i = 0; i < s.length; i++) {
    h ^= s.charCodeAt(i);
    h = Math.imul(h, 16777619);
  }
  return h >>> 0;
}

export function mixHex(a: string, b: string, t: number): string {
  const pa = hexParts(a);
  const pb = hexParts(b);
  const f = clamp(t);
  const r = Math.round(lerp(pa[0], pb[0], f));
  const g = Math.round(lerp(pa[1], pb[1], f));
  const bl = Math.round(lerp(pa[2], pb[2], f));
  return `rgb(${r},${g},${bl})`;
}

export function hexParts(h: string): [number, number, number] {
  const s = h.replace("#", "");
  const n = parseInt(s.length === 3 ? s.split("").map((c) => c + c).join("") : s, 16);
  return [(n >> 16) & 255, (n >> 8) & 255, n & 255];
}

export function rgba(hex: string, alpha: number): string {
  const [r, g, b] = hexParts(hex);
  return `rgba(${r},${g},${b},${clamp(alpha)})`;
}

/** Number formatting that matches the on-screen evidence typography. */
export const nf = (n: number): string => Math.round(n).toLocaleString("en-US");
export const nf1 = (n: number): string => n.toFixed(1);
export const pad2 = (n: number): string => String(n).padStart(2, "0");

export const tc = (t: number): string => {
  const m = Math.floor(t / 60);
  const s = Math.floor(t % 60);
  const f = Math.floor((t % 1) * 30);
  return `${pad2(m)}:${pad2(s)}:${pad2(f)}`;
};

/** Pseudo hand-held camera drift, in pixels, deterministic per frame. */
export function drift(t: number, seed = 1, amp = 1): { x: number; y: number } {
  return {
    x: (Math.sin(t * 0.37 * seed) + Math.sin(t * 0.91 * seed + 1.7)) * amp,
    y: (Math.cos(t * 0.29 * seed + 0.4) + Math.sin(t * 0.67 * seed)) * amp,
  };
}
