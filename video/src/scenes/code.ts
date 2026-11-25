/** Act 2 — the code layer: DEX anatomy, obfuscation, and the SDK supply chain. */
import type { Ctx } from '../lib.js';
import { alpha, badge, bars, chapterCard, chrome, code, ease, panel, seg, stat, text, underline, wrap } from '../lib.js';
import { C, F, LAYOUT, W } from '../theme.js';
import { data, fmt, kib } from '../data.js';

export const codeChapter = { index: 2, title: 'Bytecode anatomy · obfuscation · third-party SDK graph', kicker: 'Act II' };
export const codeDuration = 44;
const CARD = 3.4;

export function drawCode(ctx: Ctx, t: number): void {
  chrome(ctx, t, codeDuration, codeChapter);
  if (t < CARD) {
    chapterCard(ctx, t, 2, 'Act II · the code',
      'Twenty DEX files and 351,248 method references',
      'A custom DEX reader decodes every class, method, field and string. The interesting part is not the size — it is the seam between first-party, framework and vendor code.');
    return;
  }
  const lt = t - CARD;

  // ---- 1: scale (0-10) ---------------------------------------------------
  if (lt < 10) {
    const p = ease.outCubic(seg(lt, 0, 0.8));
    text(ctx, 'CODE SCALE', LAYOUT.margin, 200, { font: F.mono(20), fill: C.cyan, spacing: 3 });
    text(ctx, 'A codebase measured, not estimated', LAYOUT.margin, 254, { font: F.sans(52, 700) });
    underline(ctx, LAYOUT.margin, 278, 470, p, C.cyan);
    stat(ctx, LAYOUT.margin, 470, 'classes', data.code.classes, 1.6, lt, { size: 96 });
    stat(ctx, 640, 470, 'method references', data.code.methods, 1.9, lt, { size: 96 });
    stat(ctx, 1240, 470, 'string constants', data.code.strings, 2.3, lt, { size: 96 });
    stat(ctx, LAYOUT.margin, 720, 'type ids', data.code.types, 2.6, lt, { size: 76 });
    stat(ctx, 640, 720, 'native (JNI) methods', data.code.native_methods, 2.9, lt, { size: 76 });
    stat(ctx, 1240, 720, 'DEX payload', data.code.dex_bytes, 3.2, lt, { size: 76, fmt: (n) => `${(n / 2 ** 20).toFixed(1)}M` });

    // informational: stdlib vs vendor share
    const fam = data.families.slice(0, 6);
    text(ctx, 'TOP-LEVEL NAMESPACES (classes)', LAYOUT.margin, 900, { font: F.mono(17), fill: C.faint });
    let x = LAYOUT.margin;
    fam.forEach(([name, n], i) => {
      const w = 1200 * Math.sqrt(n / fam[0]![1]) * ease.outCubic(seg(lt, 2.2 + i * 0.1, 3.0 + i * 0.1));
      const colors = [C.cyan, C.teal, C.violet, C.amber, C.magenta, C.faint];
      ctx.fillStyle = alpha(colors[i % 6] as string, 0.85);
      ctx.fillRect(x, 920, Math.max(2, w / fam.length - 8), 22);
      text(ctx, name, x, 972, { font: F.mono(17), fill: C.dim });
      text(ctx, fmt(n), x, 998, { font: F.mono(15), fill: C.faint });
      x += w / fam.length;
    });
    return;
  }

  // ---- 2: DEX anatomy (10-24) -------------------------------------------
  if (lt >= 10 && lt < 25) {
    const p = ease.outCubic(seg(lt, 10, 11));
    text(ctx, 'DEX ANATOMY', LAYOUT.margin, 200, { font: F.mono(20), fill: C.teal, spacing: 3 });
    text(ctx, 'Index tables first, code payload last', LAYOUT.margin, 254, { font: F.sans(52, 700) });
    underline(ctx, LAYOUT.margin, 278, 470, p, C.teal);

    // stacked bar for largest dex: index vs code
    const dex = data.code.sections.reduce((a, b) => (b.bytes > a.bytes ? b : a));
    const bx = LAYOUT.margin, by = 360, bw = 1060;
    const idxW = (dex.index_bytes / dex.bytes) * bw * ease.outCubic(seg(lt, 10.2, 11.2));
    const codeW = (dex.code / dex.bytes) * bw * ease.outCubic(seg(lt, 10.4, 11.4));
    ctx.fillStyle = alpha(C.violet, 0.85); ctx.fillRect(bx, by, idxW, 56);
    ctx.fillStyle = alpha(C.teal, 0.9); ctx.fillRect(bx + idxW, by, codeW, 56);
    ctx.strokeStyle = C.panelEdge; ctx.strokeRect(bx, by, bw, 56);
    text(ctx, `index tables ${kib(dex.index_bytes)}`, bx, by - 12, { font: F.mono(17), fill: C.violet });
    text(ctx, `code payload ${kib(dex.code)} · ${dex.dex}`, bx + bw, by - 12, { font: F.mono(17), fill: C.teal, align: 'right' });

    const bullets: [string, string][] = [
      ['header (112 B)', 'magic, checksum, SHA-1, section offsets and counts'],
      ['id tables', 'string / type / proto / field / method ids, all 4-byte records'],
      ['class_defs', `60,671 class definitions across ${data.code.dex} files`],
      ['data', 'MUTF-8 strings, code items, class_data, annotations, debug info'],
    ];
    bullets.forEach(([k, v], i) => {
      const pp = ease.outCubic(seg(lt, 11.4 + i * 0.5, 12.2 + i * 0.5));
      const y = 500 + i * 74;
      ctx.save(); ctx.globalAlpha = pp;
      text(ctx, k, bx, y, { font: F.mono(19), fill: C.teal });
      text(ctx, v, bx + 260, y, { font: F.sans(24), fill: C.text });
      ctx.restore();
    });

    // method capacity ladder
    panel(ctx, 1180, 340, 644, 430, { fill: 'rgba(10,18,30,0.62)' });
    text(ctx, 'METHOD IDS PER DEX (LIMIT 65,536)', 1210, 382, { font: F.mono(15), fill: C.faint });
    const caps = data.code.sections.slice(0, 14);
    caps.forEach((s, i) => {
      const y = 410 + i * 25;
      const p2 = ease.outCubic(seg(lt, 12 + i * 0.05, 12.8 + i * 0.05));
      ctx.fillStyle = alpha(C.faint, 0.22); ctx.fillRect(1250, y, 440, 11);
      const w = Math.min(1, s.method_capacity_used / 100) * 440 * p2;
      ctx.fillStyle = s.method_capacity_used > 88 ? C.amber : C.cyan;
      ctx.fillRect(1250, y, w, 11);
      text(ctx, s.dex.replace('classes', 'c').replace('.dex', ''), 1240, y + 10, { font: F.mono(11), fill: C.faint, align: 'right' });
      if (s.method_capacity_used > 88) {
        text(ctx, `${s.method_capacity_used}%`, 1700, y + 10, { font: F.mono(12), fill: C.amber });
      }
    });
    const note = ease.outCubic(seg(lt, 16, 17));
    ctx.save(); ctx.globalAlpha = note;
    wrap(ctx, 'Three files are saturated: multidex is forced by the 16-bit index, not by module boundaries — a packaging constraint, not architecture.', 560, F.sans(20))
      .forEach((ln, i) => text(ctx, ln, 1210, 790 + i * 28, { font: F.sans(20), fill: C.dim }));
    ctx.restore();
  }

  // ---- 3: obfuscation + SDK supply chain (25-44) -------------------------
  if (lt >= 25) {
    const p = ease.outCubic(seg(lt, 25, 26));
    text(ctx, 'OBFUSCATION & SUPPLY CHAIN', LAYOUT.margin, 200, { font: F.mono(20), fill: C.magenta, spacing: 3 });
    text(ctx, 'R8 hides names; dependencies give the app away', LAYOUT.margin, 254, { font: F.sans(52, 700) });
    underline(ctx, LAYOUT.margin, 278, 780, p, C.magenta);

    // obfuscation donut
    const cx = 300, cy = 600, r = 130;
    const ratio = data.code.obf_pkg_pct / 100;
    const pp = ease.inOutCubic ? ease.inOutCubic(seg(lt, 25.2, 26.6)) : 1;
    ctx.save();
    ctx.lineWidth = 26;
    ctx.strokeStyle = alpha(C.faint, 0.25);
    ctx.beginPath(); ctx.arc(cx, cy, r, 0, Math.PI * 2); ctx.stroke();
    ctx.strokeStyle = C.magenta;
    ctx.beginPath(); ctx.arc(cx, cy, r, -Math.PI / 2, -Math.PI / 2 + Math.PI * 2 * ratio * pp); ctx.stroke();
    ctx.restore();
    text(ctx, `${data.code.obf_pkg_pct.toFixed(1)}%`, cx, cy - 6, { font: F.sans(56, 700), fill: C.text, align: 'center' });
    text(ctx, 'of packages are', cx, cy + 30, { font: F.sans(20), fill: C.dim, align: 'center' });
    text(ctx, 'obfuscated names', cx, cy + 54, { font: F.sans(20), fill: C.dim, align: 'center' });
    text(ctx, `${data.code.obf_class_pct.toFixed(1)}% of all classes`, cx, cy + 96, { font: F.mono(17), fill: C.faint, align: 'center' });

    // SDK list
    const x0 = 560;
    text(ctx, `THIRD-PARTY SURFACE — ${data.sdk_count} SDKs fingerprinted from string evidence`, x0, 350, { font: F.mono(17), fill: C.faint });
    data.sdks.slice(0, 10).forEach((s, i) => {
      const y = 396 + i * 50;
      const q = ease.outCubic(seg(lt, 25.6 + i * 0.07, 26.4 + i * 0.07));
      ctx.save(); ctx.globalAlpha = q;
      const bw = Math.min(300, 44 * Math.log10(s.evidence + 10)) * q;
      ctx.fillStyle = alpha(i < 4 ? C.amber : C.cyan, 0.75);
      ctx.fillRect(x0, y - 20, bw, 20);
      text(ctx, s.sdk, x0 + 320, y - 2, { font: F.sans(23), fill: C.text });
      text(ctx, s.vendor, x0 + 320, y + 20, { font: F.mono(14), fill: C.faint });
      text(ctx, `${fmt(s.evidence)} refs`, W - LAYOUT.margin, y - 2, { font: F.mono(16), fill: C.dim, align: 'right' });
      ctx.restore();
    });

    const q2 = ease.outCubic(seg(lt, 33, 34));
    ctx.save(); ctx.globalAlpha = q2;
    panel(ctx, LAYOUT.margin, 850, 1700, 100, { fill: 'rgba(10,26,30,0.7)', edge: alpha(C.teal, 0.4) });
    text(ctx, 'OBSERVATION', LAYOUT.margin + 26, 890, { font: F.mono(16), fill: C.teal, spacing: 2 });
    text(ctx, 'Package names are stripped, but vendor URL templates, protobuf schemas and JNI symbol names survive —',
      LAYOUT.margin + 190, 892, { font: F.sans(23), fill: C.text });
    text(ctx, 'and they are enough to identify the whole stack.',
      LAYOUT.margin + 190, 924, { font: F.sans(23), fill: C.text });
    ctx.restore();
  }
}
