/** Act 3 — native layer: 28 ELF libraries, hardening, obfuscated payloads, crypto inventory. */
import type { Ctx } from '../lib.js';
import { alpha, chapterCard, chrome, code, dot, ease, panel, seg, stat, text, underline, wrap } from '../lib.js';
import { C, F, H, LAYOUT, W } from '../theme.js';
import { data, fmt, mib } from '../data.js';

export const nativeChapter = { index: 3, title: 'Native layer · ELF hardening · PQC inventory', kicker: 'Act III' };
export const nativeDuration = 44;
const CARD = 3.4;

const CAT_COLOR: Record<string, string> = {
  security: C.red, obfuscated: C.magenta, ml: C.cyan, kyc: C.violet, storage: C.teal,
  ui: C.amber, observability: C.faint, media: C.green, runtime: C.dim, unknown: C.faint,
};

export function drawNative(ctx: Ctx, t: number): void {
  chrome(ctx, t, nativeDuration, nativeChapter);
  if (t < CARD) {
    chapterCard(ctx, t, 3, 'Act III · native layer',
      'The secrets are 54.8 MiB of C++',
      'Risk scoring, liveness detection, key handling and encrypted storage all leave the JVM. What is left in Kotlin is orchestration.');
    return;
  }
  const lt = t - CARD;

  // ---- 1: library map (0-13) ---------------------------------------------
  if (lt < 13) {
    const p = ease.outCubic(seg(lt, 0, 0.8));
    text(ctx, 'NATIVE MAP', LAYOUT.margin, 200, { font: F.mono(20), fill: C.teal, spacing: 3 });
    text(ctx, 'Twenty-eight ELF objects, four jobs', LAYOUT.margin, 254, { font: F.sans(52, 700) });
    underline(ctx, LAYOUT.margin, 278, 470, p, C.teal);
    stat(ctx, LAYOUT.margin, 470, 'native libraries (27 in ABI split + 1 in assets)', 28, 1.4, lt, { size: 92 });
    stat(ctx, 620, 470, 'total size', data.native.bytes, 1.7, lt, { size: 92, fmt: (n) => mib(n) });
    stat(ctx, 1180, 470, 'stripped', data.native.stripped_pct, 1.9, lt, { size: 92, fmt: (n) => `${n.toFixed(1)}%` });

    // treemap of libs, sorted by size, category colour
    const libs = [...data.native.libraries].sort((a, b) => b.bytes - a.bytes).slice(0, 14);
    const total = data.native.bytes;
    let x = LAYOUT.margin, y = 600, row = 0;
    const maxRowW = W - 2 * LAYOUT.margin;
    let rowW = 0;
    const cellH = 104;
    libs.forEach((l, i) => {
      const w = Math.min(maxRowW, Math.max(150, (l.bytes / total) * maxRowW * 2.2));
      if (rowW + w > maxRowW) { row++; rowW = 0; }
      const cx = LAYOUT.margin + rowW;
      const cy = 600 + row * (cellH + 10);
      const q = ease.outCubic(seg(lt, 0.6 + i * 0.08, 1.4 + i * 0.08));
      ctx.save();
      ctx.globalAlpha = q;
      ctx.fillStyle = alpha(CAT_COLOR[l.cat] ?? C.faint, 0.16);
      ctx.fillRect(cx, cy, w - 8, cellH);
      ctx.strokeStyle = alpha(CAT_COLOR[l.cat] ?? C.faint, 0.45);
      ctx.strokeRect(cx, cy, w - 8, cellH);
      text(ctx, l.lib.replace(/^lib|\.so$/g, ''), cx + 10, cy + 30, { font: F.mono(15), fill: C.text, maxWidth: w - 26 });
      text(ctx, mib(l.bytes), cx + 10, cy + 54, { font: F.mono(13), fill: C.dim });
      text(ctx, l.cat, cx + 10, cy + 78, { font: F.mono(12), fill: CAT_COLOR[l.cat] ?? C.faint });
      ctx.restore();
      rowW += w;
    });
    text(ctx, `area ≈ file size · colour = inferred role · top ${libs.length} of ${data.native.count} objects`,
      LAYOUT.margin, 600 + (row + 1) * (cellH + 10) + 22, { font: F.mono(15), fill: C.faint });
    return;
  }

  // ---- 2: hardening matrix (13-26) --------------------------------------
  if (lt >= 13 && lt < 27) {
    const p = ease.outCubic(seg(lt, 13, 14));
    text(ctx, 'ELF HARDENING', LAYOUT.margin, 200, { font: F.mono(20), fill: C.cyan, spacing: 3 });
    text(ctx, 'Compiler flags, read from the binaries', LAYOUT.margin, 254, { font: F.sans(52, 700) });
    underline(ctx, LAYOUT.margin, 278, 520, p, C.cyan);

    const checks: [string, string, string][] = [
      ['RELRO', 'relro', 'full/partial relocation read-only'],
      ['BIND_NOW', 'bind_now', 'immediate symbol binding (no lazy PLT)'],
      ['NX', 'nx', 'non-executable stack via GNU_STACK'],
      ['PIE', 'pie', 'position independent executable'],
      ['STACK CANARY', 'stack_canary', 'compiled with -fstack-protector'],
    ];
    checks.forEach(([label, key, note], i) => {
      const n = data.native.hardening[key] ?? 0;
      const y = 372 + i * 92;
      const q = ease.outCubic(seg(lt, 13.2 + i * 0.25, 14.2 + i * 0.25));
      ctx.save(); ctx.globalAlpha = q;
      text(ctx, label, LAYOUT.margin, y, { font: F.mono(23), fill: C.text });
      text(ctx, note, LAYOUT.margin, y + 27, { font: F.sans(17), fill: C.faint });
      const bx = 620, bw = 470;
      ctx.fillStyle = alpha(C.faint, 0.2); ctx.fillRect(bx, y - 19, bw, 20);
      ctx.fillStyle = C.teal;
      ctx.fillRect(bx, y - 19, bw * (n / data.native.count) * q, 20);
      text(ctx, `${n} / ${data.native.count}`, bx + bw + 20, y - 2, { font: F.mono(20), fill: C.teal });
      ctx.restore();
    });
    const q2 = ease.outCubic(seg(lt, 15.5, 16.5));
    ctx.save(); ctx.globalAlpha = q2;
    panel(ctx, LAYOUT.margin, 870, 1010, 96, { fill: 'rgba(10,26,30,0.65)', edge: alpha(C.teal, 0.4) });
    text(ctx, 'The ABI split is 32-bit ARM only: arm64 and x86_64 splits are absent from this mirror.',
      LAYOUT.margin + 24, 930, { font: F.sans(21), fill: C.dim });
    ctx.restore();

    // right column: randomised payload names
    const q3 = ease.outCubic(seg(lt, 16.5, 17.5));
    ctx.save(); ctx.globalAlpha = q3;
    const rx = 1300;
    text(ctx, 'RANDOMISED FILENAMES', rx, 372, { font: F.mono(18), fill: C.magenta, spacing: 2 });
    const rnd = data.native.randomised_names;
    rnd.slice(0, 7).forEach((n, i) => {
      code(ctx, n, rx, 410 + i * 28, 16, C.dim);
    });
    const yText = 410 + 7 * 28 + 24;
    wrap(ctx, 'Each carries a JNI_OnLoad and registers its own entry points, so renaming breaks naive hooking that trusts the library name.',
      500, F.sans(17)).forEach((ln, i) => text(ctx, ln, rx, yText + i * 23, { font: F.sans(17), fill: C.faint }));
    ctx.restore();

    // entropy plot
    const q4 = ease.outCubic(seg(lt, 18, 19));
    ctx.save(); ctx.globalAlpha = q4;
    panel(ctx, rx, 700, 500, 266, { fill: 'rgba(18,10,24,0.7)', edge: alpha(C.magenta, 0.35) });
    text(ctx, 'SHANNON ENTROPY · BITS PER BYTE', rx + 22, 736, { font: F.mono(14), fill: C.faint });
    const sorted = [...data.native.libraries].sort((a, b) => a.entropy - b.entropy);
    sorted.forEach((l, i) => {
      const y = 754 + i * 6.6;
      ctx.fillStyle = alpha(C.magenta, 0.75);
      ctx.fillRect(rx + 22, y, (l.entropy / 8) * 420, 4);
    });
    text(ctx, `peak ${data.native.max_entropy.value.toFixed(2)} · ${data.native.max_entropy.lib}`,
      rx + 22, 950, { font: F.mono(13), fill: C.dim });
    ctx.restore();
  }

  // ---- 3: crypto inventory (27-44) ---------------------------------------
  if (lt >= 27) {
    const p = ease.outCubic(seg(lt, 27, 28));
    text(ctx, 'CRYPTOGRAPHIC INVENTORY', LAYOUT.margin, 200, { font: F.mono(20), fill: C.violet, spacing: 3 });
    text(ctx, 'Classical and post-quantum, side by side', LAYOUT.margin, 254, { font: F.sans(52, 700) });
    underline(ctx, LAYOUT.margin, 278, 660, p, C.violet);

    const classical: [string, string][] = [
      ['AES/GCM/NoPadding', 'AEAD for data at rest'],
      ['RSA/OAEP-SHA256', 'key wrapping'],
      ['HmacSHA256 / SHA512', 'request signing'],
      ['PBKDF2-HMAC-SHA256', 'key derivation'],
      ['ECDH · X25519 · ECIES', 'session key agreement'],
      ['Ed25519', 'signature verification'],
    ];
    const pq: [string, string][] = [
      ['ML-KEM (Kyber)', 'key encapsulation'],
      ['Dilithium', 'post-quantum signatures'],
      ['SPHINCS+', 'stateless hash signatures'],
      ['Picnic', 'zero-knowledge signatures'],
      ['BIKE · HQC', 'code-based KEM'],
      ['FrodoKEM', 'conservative lattice KEM'],
    ];
    const drawList = (items: [string, string][], x: number, title: string, color: string): void => {
      text(ctx, title, x, 360, { font: F.mono(18), fill: color, spacing: 2 });
      items.forEach(([k, v], i) => {
        const q = ease.outCubic(seg(lt, 27.4 + i * 0.18, 28.3 + i * 0.18));
        const y = 410 + i * 72;
        ctx.save(); ctx.globalAlpha = q;
        ctx.fillStyle = alpha(color, 0.9);
        ctx.fillRect(x, y - 18, 3, 40);
        text(ctx, k, x + 20, y, { font: F.sans(26), fill: C.text });
        text(ctx, v, x + 20, y + 26, { font: F.sans(18), fill: C.faint });
        ctx.restore();
      });
    };
    drawList(classical, LAYOUT.margin, 'CLASSICAL — IN USE', C.cyan);
    drawList(pq, 1000, 'POST-QUANTUM — PRESENT IN BINARY', C.violet);

    text(ctx, 'Post-quantum hits are string-level evidence from the bundled Bouncy Castle provider:',
      LAYOUT.margin, 890, { font: F.sans(22), fill: C.dim });
    text(ctx, 'the app links the algorithms, but nothing observed here proves they protect live traffic.',
      LAYOUT.margin, 922, { font: F.sans(22), fill: C.dim });

    // KYC assets
    const q5 = ease.outCubic(seg(lt, 33, 34));
    ctx.save(); ctx.globalAlpha = q5;
    panel(ctx, 1420, 380, 400, 300, { fill: 'rgba(20,14,34,0.7)', edge: alpha(C.violet, 0.35) });
    text(ctx, 'ON-DEVICE EKYC', 1446, 420, { font: F.mono(16), fill: C.violet });
    text(ctx, 'face_detector_v2 (ML Kit)', 1446, 462, { font: F.sans(19), fill: C.text });
    text(ctx, 'barhopper_v3 (barcodes)', 1446, 492, { font: F.sans(19), fill: C.text });
    text(ctx, 'picodet_ncnn (card detect)', 1446, 522, { font: F.sans(19), fill: C.text });
    text(ctx, 'VFaceLib + trueidlib (liveness)', 1446, 552, { font: F.sans(19), fill: C.text });
    text(ctx, 'blazeface · fssd · BCL* (eye state)', 1446, 582, { font: F.sans(19), fill: C.text });
    text(ctx, `14 models · ${mib(data.resources.ml_bytes)}`, 1446, 630, { font: F.mono(16), fill: C.amber });
    ctx.restore();
  }
}
