export function clamp01(x: number): number {
  return Math.max(0, Math.min(1, x));
}

export function easeOutCubic(t: number): number {
  const u = clamp01(t);
  return 1 - Math.pow(1 - u, 3);
}

export function easeInOutCubic(t: number): number {
  const u = clamp01(t);
  return u < 0.5 ? 4 * u * u * u : 1 - Math.pow(-2 * u + 2, 3) / 2;
}

export function easeOutExpo(t: number): number {
  const u = clamp01(t);
  return u === 1 ? 1 : 1 - Math.pow(2, -10 * u);
}

export function subProgress(progress: number, start: number, end: number): number {
  if (end <= start) return progress >= end ? 1 : 0;
  return clamp01((progress - start) / (end - start));
}

export function lerp(a: number, b: number, t: number): number {
  return a + (b - a) * clamp01(t);
}
