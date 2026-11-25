/** Shannon entropy + byte-histogram helpers used for packer/compression triage. */

export function shannon(bytes: Uint8Array): number {
  if (bytes.length === 0) return 0;
  const hist = new Uint32Array(256);
  for (let i = 0; i < bytes.length; i++) hist[bytes[i]]++;
  let h = 0;
  const n = bytes.length;
  for (let i = 0; i < 256; i++) {
    if (!hist[i]) continue;
    const p = hist[i] / n;
    h -= p * Math.log2(p);
  }
  return h;
}

/** Chi-square against a uniform byte distribution (packer / cipher detector). */
export function chiSquare(bytes: Uint8Array): number {
  if (bytes.length === 0) return 0;
  const hist = new Uint32Array(256);
  for (let i = 0; i < bytes.length; i++) hist[bytes[i]]++;
  const expected = bytes.length / 256;
  let chi = 0;
  for (let i = 0; i < 256; i++) {
    const d = hist[i] - expected;
    chi += (d * d) / expected;
  }
  return chi;
}

/** Sliding-window entropy profile, useful for spotting encrypted blobs in .so/.dex. */
export function entropyProfile(bytes: Uint8Array, window = 4096, step = 4096): number[] {
  const out: number[] = [];
  for (let i = 0; i + window <= bytes.length; i += step) {
    out.push(Number(shannon(bytes.subarray(i, i + window)).toFixed(4)));
  }
  return out;
}

export function printableRatio(bytes: Uint8Array): number {
  if (!bytes.length) return 0;
  let printable = 0;
  for (let i = 0; i < bytes.length; i++) {
    const c = bytes[i];
    if ((c >= 0x20 && c < 0x7f) || c === 0x09 || c === 0x0a || c === 0x0d) printable++;
  }
  return printable / bytes.length;
}
