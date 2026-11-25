/** Act 0 — title, provenance, and the research question. */
import type { Ctx } from '../lib.js';
import { badge, chrome, clamp, code, dot, ease, panel, roundRect, seg, text, underline, wrap } from '../lib.js';
import { C, F, H, LAYOUT, W } from '../theme.js';
import { data, fmt, mib, shortHash } from '../data.js';

export const introChapter = { index: 0, title: 'Title · provenance · research question', kicker: 'Opening' };
export const introDuration = 22;

export function drawIntro(ctx: Ctx, t: number): void {
  chrome(ctx, t, introDuration, introChapter);

  const s = data.subject;
  // ---- 0-7s: title ------------------------------------------------------
  const titleOut = 1 - seg(t, 6.0, 7.0);
  if (titleOut > 0) {
    ctx.save();
    ctx.globalAlpha = titleOut;
    const k = ease.outCubic(seg(t, 0.2, 1.2));
    text(ctx, 'STATIC ANATOMY OF A VIETNAMESE SUPER-APP', LAYOUT.margin, 300, { font: F.mono(22), fill: C.amber, spacing: 4, alpha: k });

    const t1 = ease.outCubic(seg(t, 0.35, 1.35));
    text(ctx, 'ZaloPay', LAYOUT.margin, 470 - (1 - t1) * 24, { font: F.sans(150, 700), fill: C.text, alpha: t1 });
    ctx.save();
    ctx.font = F.sans(150, 700);
    const wZ = ctx.measureText('ZaloPay').width;
    ctx.restore();
    text(ctx, '11.17.0', LAYOUT.margin + wZ + 34, 470 - (1 - t1) * 24, {
      font: F.sans(150, 300), fill: C.amber, alpha: t1,
    });

    const p2 = ease.outCubic(seg(t, 0.8, 1.7));
    const lede = 'A complete static reverse-engineering of every file in an APKPure XAPK bundle: 5 split APKs, 6,464 ZIP entries, 20 DEX files and 28 native libraries — measured, not guessed.';
    wrap(ctx, lede, 1180, F.sans(34)).forEach((ln, i) =>
      text(ctx, ln, LAYOUT.margin, 560 + i * 48, { font: F.sans(34), fill: C.dim, alpha: p2 }));

    // spec strip
    const p3 = ease.outCubic(seg(t, 1.3, 2.3));
    const specs: [string, string][] = [
      ['PACKAGE', s.package],
      ['VERSION CODE', s.version_code],
      ['MIN / TARGET SDK', `${s.min_sdk} / ${s.target_sdk}`],
      ['BUNDLE', `${mib(s.bytes_xapk)} XAPK`],
    ];
    const specX = [LAYOUT.margin, LAYOUT.margin + 380, LAYOUT.margin + 700, LAYOUT.margin + 1060];
    specs.forEach(([k, v], i) => {
      const x = specX[i] as number;
      ctx.save();
      ctx.globalAlpha = p3;
      text(ctx, k, x, 800, { font: F.mono(16), fill: C.faint });
      text(ctx, v, x, 838, { font: F.sans(30, 600), fill: C.text });
      ctx.restore();
    });

    // hash
    const p4 = ease.outCubic(seg(t, 1.8, 2.8));
    ctx.save();
    ctx.globalAlpha = p4;
    text(ctx, 'XAPK SHA-256', LAYOUT.margin, 928, { font: F.mono(16), fill: C.faint });
    code(ctx, s.sha256_xapk, LAYOUT.margin, 960, 20, String(C.teal));
    ctx.restore();
    ctx.restore();
  }

  // ---- 7-22s: research question ----------------------------------------
  const qIn = ease.outCubic(seg(t, 7.0, 8.0));
  if (qIn > 0) {
    ctx.save();
    ctx.globalAlpha = qIn;
    text(ctx, 'THE QUESTION', LAYOUT.margin, 290, { font: F.mono(22), fill: C.cyan, spacing: 4 });
    const q = 'What does a regulated, 100-million-user payment app actually contain — and where does the plaintext end?';
    wrap(ctx, q, 1500, F.sans(64, 700)).forEach((ln, i) => {
      const p = ease.outCubic(seg(t, 7.1 + i * 0.15, 8.0 + i * 0.15));
      text(ctx, ln, LAYOUT.margin, 400 + i * 84, { font: F.sans(64, 700), fill: C.text, alpha: p });
    });

    const thesis = [
      ['01', 'Four trust domains', 'first-party payments, an Alibaba mini-app runtime, Google ML/eKYC pipelines and OEM payment rails.', C.amber],
      ['02', 'Defence in depth is native', '54.8 MiB of C++ carries the anti-fraud, liveness and crypto load — 96.3% stripped.', C.cyan],
      ['03', 'Integrity is the weakest link', 'the APK Signing Block in this repackaged bundle is non-canonical; the signature cannot be verified.', C.magenta],
    ];
    thesis.forEach(([n, head, body, color], i) => {
      const p = ease.outCubic(seg(t, 9.2 + i * 0.9, 10.1 + i * 0.9));
      const y = 660 + i * 118;
      ctx.save();
      ctx.globalAlpha = p;
      text(ctx, n as string, LAYOUT.margin, y, { font: F.mono(26), fill: color as string });
      text(ctx, head as string, LAYOUT.margin + 74, y, { font: F.sans(32, 600), fill: C.text });
      text(ctx, body as string, LAYOUT.margin + 74, y + 34, { font: F.sans(24), fill: C.dim });
      ctx.restore();
    });
    ctx.restore();
  }
  // subtle watermark
  dot(ctx, W - 130, H - 150, 5, C.amber, 0.5 * waveSlow(t));
}

const waveSlow = (t: number): number => clamp(0.55 + 0.45 * Math.sin(t * 0.9));
