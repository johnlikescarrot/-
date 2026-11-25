/** Act 5 — signed-data forensics: certificate identity, the non-canonical block, honest verdict. */
import type { Ctx } from '../lib.js';
import { alpha, chapterCard, chrome, code, dot, ease, panel, seg, text, underline } from '../lib.js';
import { C, F, H, LAYOUT, W } from '../theme.js';
import { data } from '../data.js';

export const integrityChapter = { index: 5, title: 'Signing-block forensics · verification verdict', kicker: 'Act V' };
export const integrityDuration = 42;
const CARD = 3.4;

export function drawIntegrity(ctx: Ctx, t: number): void {
  chrome(ctx, t, integrityDuration, integrityChapter);
  if (t < CARD) {
    chapterCard(ctx, t, 5, 'Act V · integrity',
      'The certificate is real. The signature is not.',
      'A signing block can be parsed and still be broken. This act separates identity, structure and cryptographic proof — and states exactly what could be verified.');
    return;
  }
  const lt = t - CARD;
  const s = data.security;

  // ---- 1: certificate identity (0-12) ------------------------------------
  if (lt < 12) {
    const p = ease.outCubic(seg(lt, 0, 0.8));
    text(ctx, 'SIGNER IDENTITY', LAYOUT.margin, 200, { font: F.mono(20), fill: C.teal, spacing: 3 });
    text(ctx, 'Who signed this bundle', LAYOUT.margin, 254, { font: F.sans(52, 700) });
    underline(ctx, LAYOUT.margin, 278, 420, p, C.teal);

    const rows: [string, string][] = [
      ['SUBJECT', s.cert_subject],
      ['ISSUER', s.cert_subject + '  (self-signed)'],
      ['KEY', `${s.key.type} · ${s.key.bits} bit`],
      ['VALID FROM', s.cert_not_before],
      ['VALID TO', s.cert_not_after],
      ['SHA-256', s.cert_sha256],
    ];
    rows.forEach(([k, v], i) => {
      const q = ease.outCubic(seg(lt, 0.3 + i * 0.18, 1.2 + i * 0.18));
      const y = 380 + i * 78;
      ctx.save(); ctx.globalAlpha = q;
      text(ctx, k, LAYOUT.margin, y, { font: F.mono(17), fill: C.faint });
      text(ctx, v, LAYOUT.margin + 200, y, { font: i === 5 ? F.mono(21) : F.sans(28, 600), fill: i === 5 ? C.amber : C.text });
      ctx.restore();
    });

    // validity timeline
    const q = ease.outCubic(seg(lt, 2.2, 3.2));
    ctx.save(); ctx.globalAlpha = q;
    const x0 = 1240, x1 = 1800, y = 400;
    text(ctx, 'CERTIFICATE LIFETIME', x0, y - 30, { font: F.mono(15), fill: C.faint });
    ctx.fillStyle = alpha(C.faint, 0.25); ctx.fillRect(x0, y, x1 - x0, 8);
    const start = new Date(s.cert_not_before).getTime();
    const end = new Date(s.cert_not_after).getTime();
    const now = new Date('2026-11-25').getTime();
    const frac = (now - start) / (end - start);
    ctx.fillStyle = C.teal;
    ctx.fillRect(x0, y, (x1 - x0) * 0.62, 8);
    dot(ctx, x0 + (x1 - x0) * Math.min(1, Math.max(0, frac)), y + 4, 9, C.amber);
    text(ctx, s.cert_not_before, x0, y + 36, { font: F.mono(14), fill: C.dim });
    text(ctx, s.cert_not_after, x1, y + 36, { font: F.mono(14), fill: C.dim, align: 'right' });
    text(ctx, 'today: 2026-11-25 — 14 years after issuance, still inside validity',
      x0, y + 70, { font: F.sans(18), fill: C.faint });
    ctx.restore();

    const q2 = ease.outCubic(seg(lt, 4, 5));
    ctx.save(); ctx.globalAlpha = q2;
    panel(ctx, 1240, 560, 560, 300, { fill: 'rgba(10,20,30,0.7)' });
    text(ctx, 'WHY THIS MATTERS', 1266, 602, { font: F.mono(16), fill: C.cyan, spacing: 2 });
    const lines = [
      'Android trusts a signer by the certificate digest,',
      'not by a CA. A self-signed 2016 key with a 25-year',
      'lifetime is normal for an app that has shipped since',
      'then. The identity is consistent with ZaloPay — but',
      'identity is not integrity: the digest must also match',
      'the code, and here it does not.',
    ];
    lines.forEach((ln, i) => text(ctx, ln, 1266, 644 + i * 30, { font: F.sans(20), fill: i === 5 ? C.amber : C.dim }));
    ctx.restore();
  }

  // ---- 2: the broken block (12-28) --------------------------------------
  if (lt >= 12 && lt < 28) {
    const p = ease.outCubic(seg(lt, 12, 13));
    text(ctx, 'SIGNING BLOCK STRUCTURE', LAYOUT.margin, 200, { font: F.mono(20), fill: C.magenta, spacing: 3 });
    text(ctx, 'Where the framing breaks', LAYOUT.margin, 254, { font: F.sans(52, 700) });
    underline(ctx, LAYOUT.margin, 278, 560, p, C.magenta);

    // hex dump of the block head
    const bytes = s.hex.head.split(' ');
    const bx = LAYOUT.margin, by = 420;
    text(ctx, `offset ${s.hex.block_offset.toLocaleString('en-US')} · first 48 bytes`, bx, by - 40, { font: F.mono(16), fill: C.faint });
    bytes.forEach((b, i) => {
      const col = i % 16, row = Math.floor(i / 16);
      const x = bx + col * 46, y = by + row * 52;
      const q = ease.outCubic(seg(lt, 12.2 + i * 0.012, 12.9 + i * 0.012));
      const hi = i < 8 ? C.amber : (i < 16 ? C.cyan : C.text);
      ctx.save(); ctx.globalAlpha = q;
      text(ctx, b, x, y, { font: F.mono(22), fill: hi });
      if (col % 4 === 0) text(ctx, (s.hex.block_offset + i).toString(16), x, y - 24, { font: F.mono(11), fill: alpha(C.faint, 0.75) });
      ctx.restore();
    });
    // annotations (right of the dump, no overlap)
    const ann: [number, string, string][] = [
      [0, 'bytes 0-7   · block size u64 = 8,184', C.amber],
      [8, 'bytes 8-15  · pair length u64 = 1,383', C.cyan],
      [16, 'bytes 16-19 · pair id 0x7109871a (v2)', C.text],
    ];
    ann.forEach(([, label, color], k) => {
      const q = ease.outCubic(seg(lt, 14 + k * 0.6, 14.7 + k * 0.6));
      ctx.save(); ctx.globalAlpha = q;
      dot(ctx, LAYOUT.margin + 800, 400 + k * 46, 4, color);
      text(ctx, label, LAYOUT.margin + 820, 406 + k * 46, { font: F.mono(18), fill: color });
      ctx.restore();
    });

    // the two defects
    const defects: [string, string][] = [
      ['01 · framing', `The pair declares ${s.v2_pair_length} bytes but its payload runs ${s.hex.declared_vs_actual[1]} — the length field does not cover the record it introduces, so a strict parser desynchronises at the next pair.`],
      ['02 · duplicate', 'A second pair at an unaligned offset (id 0xF0536873) re-embeds the same signed-data blob; it does not parse as a v3 signer and carries no additional signature.'],
      ['03 · digest', 'The embedded SHA-256 digest was recomputed over every plausible content window (1 GiB scanned at 1 MiB chunks, 41 boundaries) and never matched.'],
    ];
    defects.forEach(([k, v], i) => {
      const q = ease.outCubic(seg(lt, 16 + i * 1.2, 17 + i * 1.2));
      const y = 640 + i * 132;
      ctx.save(); ctx.globalAlpha = q;
      text(ctx, k, bx, y, { font: F.mono(18), fill: C.magenta });
      const words = v.split(' ');
      ctx.font = F.sans(22);
      let line = '', yy = y + 34;
      for (const w of words) {
        const test = line ? line + ' ' + w : w;
        if (ctx.measureText(test).width > 1450 && line) { text(ctx, line, bx, yy, { font: F.sans(22), fill: C.dim }); line = w; yy += 30; }
        else line = test;
      }
      if (line) text(ctx, line, bx, yy, { font: F.sans(22), fill: C.dim });
      ctx.restore();
    });
  }

  // ---- 3: verdict + what a defender should do (28-42) --------------------
  if (lt >= 28) {
    const p = ease.outCubic(seg(lt, 28, 29));
    text(ctx, 'VERDICT', LAYOUT.margin, 200, { font: F.mono(20), fill: C.red, spacing: 3 });
    text(ctx, 'Unverified is a finding, not a failure', LAYOUT.margin, 254, { font: F.sans(52, 700) });
    underline(ctx, LAYOUT.margin, 278, 620, p, C.red);

    const q = ease.outCubic(seg(lt, 28.3, 29.3));
    ctx.save(); ctx.globalAlpha = q;
    panel(ctx, LAYOUT.margin, 340, 780, 300, { fill: 'rgba(34,12,20,0.75)', edge: alpha(C.red, 0.45), glow: alpha(C.red, 0.25) });
    text(ctx, 'SIGNATURE VERIFICATION', LAYOUT.margin + 30, 392, { font: F.mono(18), fill: C.red, spacing: 2 });
    text(ctx, 'NOT VERIFIED', LAYOUT.margin + 30, 470, { font: F.sans(72, 700), fill: C.red });
    text(ctx, 'No contiguous byte range in the 78 MiB APK produces the embedded digest,', LAYOUT.margin + 30, 528, { font: F.sans(21), fill: C.dim });
    text(ctx, 'and the RSA signature does not validate over the signed-data blob.', LAYOUT.margin + 30, 560, { font: F.sans(21), fill: C.dim });
    text(ctx, 'Certificate parsing: OK   ·   Content digest: NOT VERIFIED   ·   Signature: NOT VERIFIED',
      LAYOUT.margin + 30, 606, { font: F.mono(16), fill: C.faint });
    ctx.restore();

    // defender checklist
    const items: string[] = [
      'Verify with apksigner against the Play-served split, not a mirror.',
      'Pin the signer digest in your own release pipeline.',
      'Treat structurally valid-but-unverifiable blocks as hostile input.',
      'Re-derive v2 digests over the exact entries + directory window.',
      'Log certificate subject + SPKI, not just the file hash.',
    ];
    text(ctx, 'IF YOU RECEIVE A BUNDLE LIKE THIS', 980, 392, { font: F.mono(17), fill: C.faint });
    items.forEach((it, i) => {
      const q2 = ease.outCubic(seg(lt, 29.6 + i * 0.35, 30.4 + i * 0.35));
      const y = 440 + i * 46;
      ctx.save(); ctx.globalAlpha = q2;
      dot(ctx, 992, y - 7, 5, C.teal);
      text(ctx, it, 1020, y, { font: F.sans(21), fill: C.text });
      ctx.restore();
    });

    const q3 = ease.outCubic(seg(lt, 33, 34));
    ctx.save(); ctx.globalAlpha = q3;
    panel(ctx, LAYOUT.margin, 720, 1700, 216, { fill: 'rgba(10,20,30,0.72)' });
    text(ctx, 'THE HONEST SENTENCE', LAYOUT.margin + 30, 766, { font: F.mono(17), fill: C.amber, spacing: 2 });
    const sentence = 'This bundle carries a certificate consistent with ZaloPay’s long-lived signing key, a well-formed v2 signed-data structure, and a broken container framing that makes the signature unverifiable in place — so the correct report is "structurally parsed, cryptographically unproven".';
    ctx.font = F.sans(28);
    let line = '', yy = 818;
    for (const w of sentence.split(' ')) {
      const test = line ? line + ' ' + w : w;
      if (ctx.measureText(test).width > 1580 && line) { text(ctx, line, LAYOUT.margin + 30, yy, { font: F.sans(28), fill: C.text }); line = w; yy += 40; }
      else line = test;
    }
    if (line) text(ctx, line, LAYOUT.margin + 30, yy, { font: F.sans(28), fill: C.text });
    ctx.restore();
  }
}
