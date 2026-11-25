/** Act 1 — the container: ZIP → XAPK → 5 split APKs, and what is inside. */
import type { Ctx } from '../lib.js';
import { alpha, bars, chapterCard, chrome, code, ease, panel, roundRect, seg, stat, text, underline } from '../lib.js';
import { C, F, H, LAYOUT, W } from '../theme.js';
import { data, fmt, kib, mib } from '../data.js';

export const containerChapter = { index: 1, title: 'Container anatomy · XAPK split graph · ZIP forensics', kicker: 'Act I' };
export const containerDuration = 40;

const CARD = 3.4;

export function drawContainer(ctx: Ctx, t: number): void {
  chrome(ctx, t, containerDuration, containerChapter);
  if (t < CARD) {
    chapterCard(ctx, t, 1, 'Act I · the container',
      'Five APKs, one wallet: how the bundle is wired',
      'The ZIP is not the app. An XAPK wraps a base APK plus ABI, density and locale splits — and each split carries its own signature block.');
    return;
  }
  const lt = t - CARD;
  // ---------- 1: split graph (0-12s) ----------
  if (lt < 12) {
    const p = ease.outCubic(seg(lt, 0, 0.8));
    text(ctx, 'SPLIT GRAPH', LAYOUT.margin, 200, { font: F.mono(20), fill: C.amber, spacing: 3 });
    text(ctx, 'What the bundle actually ships', LAYOUT.margin, 254, { font: F.sans(52, 700) });
    underline(ctx, LAYOUT.margin, 278, 420, p, C.amber);

    const rows = [
      { name: 'vn.com.vng.zalopay.apk', role: 'base', bytes: 79359087, note: 'code · manifest · resources · assets' },
      { name: 'config.armeabi_v7a.apk', role: 'ABI', bytes: 24828408, note: '27 native libraries (arm 32-bit)' },
      { name: 'config.xhdpi.apk', role: 'density', bytes: 1232563, note: '258 drawables @ xhdpi' },
      { name: 'config.vi.apk', role: 'locale', bytes: 86425, note: 'resources.arsc (Vietnamese)' },
      { name: 'config.en.apk', role: 'locale', bytes: 74137, note: 'resources.arsc (English)' },
    ];
    const x0 = LAYOUT.margin, y0 = 330;
    // spine
    ctx.strokeStyle = alpha(C.amber, 0.5);
    ctx.lineWidth = 2;
    ctx.beginPath(); ctx.moveTo(x0 + 26, y0 + 40); ctx.lineTo(x0 + 26, y0 + 40 + 4 * 96); ctx.stroke();
    rows.forEach((r, i) => {
      const pp = ease.outCubic(seg(lt, 0.25 + i * 0.16, 1.05 + i * 0.16));
      const y = y0 + i * 96;
      ctx.save();
      ctx.globalAlpha = pp;
      // node
      ctx.fillStyle = i === 0 ? C.amber : (C.cyan as string);
      ctx.beginPath(); ctx.arc(x0 + 26, y + 26 + 14, i === 0 ? 11 : 7, 0, Math.PI * 2); ctx.fill();
      const nx = x0 + 74 + (1 - pp) * 20;
      text(ctx, r.name, nx, y + 30, { font: F.mono(24), fill: C.text });
      text(ctx, r.role.toUpperCase(), nx, y + 60, { font: F.mono(15), fill: i === 0 ? C.amber : C.cyan });
      text(ctx, r.note, nx + 150, y + 60, { font: F.sans(19), fill: C.faint });
      // size bar
      const bw = 420 * Math.sqrt(r.bytes / rows[0]!.bytes);
      ctx.fillStyle = alpha(i === 0 ? C.amber : C.cyan, 0.22);
      ctx.fillRect(W - LAYOUT.margin - 470, y + 12, 470, 10);
      ctx.fillStyle = alpha(i === 0 ? C.amber : C.cyan, 0.9);
      ctx.fillRect(W - LAYOUT.margin - 470, y + 12, bw, 10);
      text(ctx, mib(r.bytes), W - LAYOUT.margin, y + 34, { font: F.mono(19), fill: C.dim, align: 'right' });
      ctx.restore();
    });
    const statsIn = ease.outCubic(seg(lt, 1.6, 2.6));
    ctx.save();
    ctx.globalAlpha = statsIn;
    stat(ctx, LAYOUT.margin, 916, 'entries across the 5 APKs', data.container.entries, 2.2, lt, { color: C.text, size: 62 });
    stat(ctx, 620, 916, 'uncompressed', data.container.uncompressed, 2.2, lt, { fmt: (n) => mib(n), size: 62 });
    stat(ctx, 1080, 916, 'DEX files', data.container.dex_files, 2.2, lt, { size: 62 });
    stat(ctx, 1380, 916, 'native libs in split (+1 in assets)', 28, 2.2, lt, { size: 62 });
    stat(ctx, 1660, 916, 'XML', data.container.xml, 2.2, lt, { size: 62 });
    ctx.restore();
  }

  // ---------- 2: ZOOM into the ZIP (12-26s) ----------
  if (lt >= 12 && lt < 26) {
    const p = ease.outCubic(seg(lt, 12, 13));
    text(ctx, 'ZIP FORENSICS', LAYOUT.margin, 200, { font: F.mono(20), fill: C.cyan, spacing: 3 });
    text(ctx, 'Where the bytes go', LAYOUT.margin, 254, { font: F.sans(52, 700) });
    underline(ctx, LAYOUT.margin, 278, 420, p, C.cyan);

    // donut-ish treemap of base APK payload classes
    const payload: [string, number, string][] = [
      ['DEX bytecode', data.code.dex_bytes, C.cyan],
      ['native libs (ABI split)', data.native.bytes, C.teal],
      ['ML models', data.resources.ml_bytes, C.violet],
      ['resources.arsc', data.resources.arsc_bytes, C.amber],
      ['everything else', data.container.uncompressed - data.code.dex_bytes - data.native.bytes - data.resources.ml_bytes - data.resources.arsc_bytes, C.faint],
    ];
    const total = payload.reduce((a, b) => a + b[1], 0);
    let acc = 0;
    const bx = LAYOUT.margin, by = 340, bw = 900, bh = 46;
    payload.forEach(([name, val, color], i) => {
      const w = (val / total) * bw * ease.outCubic(seg(lt, 12.2 + i * 0.12, 13.2 + i * 0.12));
      ctx.fillStyle = color as string;
      ctx.fillRect(bx + acc, by, Math.max(0, w), bh);
      acc += w;
    });
    ctx.strokeStyle = C.panelEdge; ctx.strokeRect(bx, by, bw, bh);
    payload.forEach(([name, val, color], i) => {
      const y = by + 96 + i * 52;
      const pp = ease.outCubic(seg(lt, 12.6 + i * 0.14, 13.5 + i * 0.14));
      ctx.save();
      ctx.globalAlpha = pp;
      ctx.fillStyle = color as string;
      ctx.fillRect(bx, y - 15, 14, 14);
      text(ctx, name, bx + 30, y, { font: F.sans(26), fill: C.text });
      text(ctx, `${mib(val)}  ·  ${(100 * val / total).toFixed(1)}%`, bx + 520, y, { font: F.mono(20), fill: C.dim });
      ctx.restore();
    });

    // per-dex bars on the right
    panel(ctx, 1100, 320, 724, 420, { fill: 'rgba(10,18,30,0.6)' });
    text(ctx, 'DEX SIZE BY FILE', 1130, 362, { font: F.mono(17), fill: C.faint });
    const vals = data.code.sections.map((s) => s.bytes);
    bars(ctx, 1130, 390, 664, 300, vals, lt - 11.5, { color: C.cyan, gap: 4 });
    const biggest = data.code.largest_dex;
    text(ctx, `${biggest.dex} · ${mib(biggest.bytes)} · ${fmt(biggest.classes)} classes · ${fmt(biggest.methods)} methods`,
      1130, 726, { font: F.sans(20), fill: C.dim });

    // method table limits callout
    const cp = ease.outCubic(seg(lt, 17, 18));
    ctx.save();
    ctx.globalAlpha = cp;
    panel(ctx, LAYOUT.margin, 700, 1120, 190, { fill: 'rgba(20,12,30,0.75)', edge: alpha(C.magenta, 0.4) });
    text(ctx, 'MULTIDEX AT THE EDGE OF THE FORMAT', LAYOUT.margin + 30, 740, { font: F.mono(18), fill: C.magenta, spacing: 2 });
    text(ctx, `${data.code.method_limit_dexes.length} of ${data.code.dex} DEX files hold 59,000+ method references —`,
      LAYOUT.margin + 30, 786, { font: F.sans(26), fill: C.text });
    text(ctx, 'each sits within 5,600 of the 65,536-address space.',
      LAYOUT.margin + 30, 820, { font: F.sans(26), fill: C.text });
    code(ctx, data.code.method_limit_dexes.map((d) => `${d}:${data.code.sections.find((s) => s.dex === d)?.method_capacity_used}%`).join('   '),
      LAYOUT.margin + 30, 862, 19, C.amber);
    ctx.restore();
  }

  // ---------- 3: alignment forensics (26-40s) ----------
  if (lt >= 26) {
    const p = ease.outCubic(seg(lt, 26, 27));
    text(ctx, 'ALIGNMENT & PACKAGING', LAYOUT.margin, 200, { font: F.mono(20), fill: C.violet, spacing: 3 });
    text(ctx, 'The bundle was re-packed, not store-shipped', LAYOUT.margin, 254, { font: F.sans(52, 700) });
    underline(ctx, LAYOUT.margin, 278, 620, p, C.violet);

    const mis = data.container.misaligned_base;
    const totalEntries = data.container.entries;
    const cp = ease.outCubic(seg(lt, 26.3, 27.3));
    ctx.save();
    ctx.globalAlpha = cp;
    text(ctx, 'ZIP local-header payload alignment', LAYOUT.margin, 370, { font: F.sans(28), fill: C.dim });
    // visual grid: 100 cells representing entries
    const cells = 100, cw = 12, ch = 12;
    const shown = Math.round((mis / 6162) * cells);
    for (let i = 0; i < cells; i++) {
      ctx.fillStyle = i < shown ? alpha(C.magenta, 0.85) : alpha(C.teal, 0.35);
      ctx.fillRect(LAYOUT.margin + i * (cw + 3), 400, cw, ch);
    }
    text(ctx, `${fmt(mis)} of ${fmt(6162)} base-APK entries are not 4-byte aligned`, LAYOUT.margin, 452, { font: F.mono(20), fill: C.magenta });
    ctx.restore();

    const notes: [string, string][] = [
      ['zipalign', 'absent: 4,249 payloads start at unaligned offsets — mmap() cannot map them zero-copy.'],
      ['v1 JAR signature', 'no META-INF/*.SF material in the base APK: verification can only rely on the v2 block.'],
      ['signing block', `${data.security.block_size}-byte block, first pair declares ${data.security.v2_pair_length} bytes for ${data.security.v2_pair_length + 4} bytes of content.`],
      ['provenance', 'XAPK assembled by a third-party mirror; timestamps and padding differ from a Play-served split.'],
    ];
    notes.forEach(([k, v], i) => {
      const pp = ease.outCubic(seg(lt, 27.0 + i * 0.7, 27.9 + i * 0.7));
      const y = 560 + i * 92;
      ctx.save();
      ctx.globalAlpha = pp;
      text(ctx, k, LAYOUT.margin, y, { font: F.mono(19), fill: C.violet });
      text(ctx, v, LAYOUT.margin + 220, y, { font: F.sans(24), fill: C.text, maxWidth: 1180 });
      ctx.restore();
    });
    const q = ease.outCubic(seg(lt, 31, 32));
    ctx.save();
    ctx.globalAlpha = q;
    panel(ctx, 1450, 520, 374, 300, { fill: 'rgba(30,12,24,0.75)', edge: alpha(C.magenta, 0.45) });
    text(ctx, 'TAKEAWAY', 1478, 566, { font: F.mono(17), fill: C.magenta, spacing: 2 });
    const tk = 'Repackaging is detectable from structure alone — before any code is read.';
    let yy = 606;
    for (const ln of (function wrap(): string[] { ctx.font = F.sans(24); const words = tk.split(' '); const out: string[] = []; let line = ''; for (const w of words) { const test = line ? line + ' ' + w : w; if (ctx.measureText(test).width > 310 && line) { out.push(line); line = w; } else line = test; } if (line) out.push(line); return out; })()) {
      text(ctx, ln, 1478, yy, { font: F.sans(24), fill: C.text }); yy += 34;
    }
    ctx.restore();
  }
}
