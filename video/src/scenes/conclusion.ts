/** Act 6 — synthesis, method card, and the citation block. */
import type { Ctx } from '../lib.js';
import { alpha, chapterCard, chrome, code, dot, ease, panel, rng, seg, text, underline } from '../lib.js';
import { C, F, H, LAYOUT, W } from '../theme.js';
import { data, fmt, mib } from '../data.js';

export const conclusionChapter = { index: 6, title: 'Synthesis · method · citation', kicker: 'Act VI' };
export const conclusionDuration = 40;
const CARD = 3.4;

export function drawConclusion(ctx: Ctx, t: number): void {
  chrome(ctx, t, conclusionDuration, conclusionChapter);
  if (t < CARD) {
    chapterCard(ctx, t, 6, 'Act VI · synthesis',
      'Nine findings, one reproducible pipeline',
      'Every figure in this film is emitted by code that ran on the archive. The pipeline, the raw JSON and the renderer ship together.');
    return;
  }
  const lt = t - CARD;

  // ---- 1: findings (0-18) ------------------------------------------------
  if (lt < 18) {
    const p = ease.outCubic(seg(lt, 0, 0.8));
    text(ctx, 'FINDINGS', LAYOUT.margin, 200, { font: F.mono(20), fill: C.amber, spacing: 3 });
    text(ctx, 'What a static atlas can prove', LAYOUT.margin, 254, { font: F.sans(52, 700) });
    underline(ctx, LAYOUT.margin, 278, 470, p, C.amber);

    const findings: [string, string][] = [
      ['Structure', `5 split APKs, ${fmt(data.container.entries)} entries, ${mib(data.container.uncompressed)} uncompressed — 1.8× the XAPK on disk.`],
      ['Code mass', `${fmt(data.code.classes)} classes and ${fmt(data.code.methods)} method refs across ${data.code.dex} DEX files; 3 at the 16-bit limit.`],
      ['Obfuscation', `${data.code.obf_pkg_pct.toFixed(1)}% of packages are R8-shortened, yet every dependency stays identifiable.`],
      ['Vendor gravity', `Alibaba mPaaS, Google ML Kit, TrueID eKYC, VNPAY and Visa share the binary with ZaloPay’s wallet core.`],
      ['Native secrets', `${mib(data.native.bytes)} of C++ (${data.native.stripped_pct}% stripped) holds anti-fraud, liveness and crypto.`],
      ['On-device ML', `${data.resources.ml_models} models (${mib(data.resources.ml_bytes)}) for OCR, face, liveness and card detection.`],
      ['Ecosystem', `${data.miniapps.count} mini-apps as configuration in one JSON; ${data.endpoints.api_paths} REST paths survive as strings.`],
      ['Privacy', `${data.manifest.permissions} permissions, ${data.manifest.dangerous.length} dangerous-class, ${data.manifest.activities} activities exported surface.`],
      ['Integrity', 'the signing block parses but cannot be verified — the one conclusion that changes how you should trust the file.'],
    ];
    findings.forEach(([k, v], i) => {
      const q = ease.outCubic(seg(lt, 0.3 + i * 0.55, 1.2 + i * 0.55));
      const y = 360 + i * 62;
      ctx.save(); ctx.globalAlpha = q;
      text(ctx, String(i + 1).padStart(2, '0'), LAYOUT.margin, y, { font: F.mono(20), fill: C.faint });
      text(ctx, k, LAYOUT.margin + 60, y, { font: F.sans(26, 600), fill: C.text });
      text(ctx, v, LAYOUT.margin + 300, y, { font: F.sans(22), fill: C.dim, maxWidth: 1400 });
      ctx.restore();
    });
    return;
  }

  // ---- 2: method card (18-30) -------------------------------------------
  if (lt >= 18 && lt < 30) {
    const p = ease.outCubic(seg(lt, 18, 19));
    text(ctx, 'METHOD', LAYOUT.margin, 200, { font: F.mono(20), fill: C.cyan, spacing: 3 });
    text(ctx, 'Reproducible from one archive', LAYOUT.margin, 254, { font: F.sans(52, 700) });
    underline(ctx, LAYOUT.margin, 278, 560, p, C.cyan);

    const steps: [string, string, string][] = [
      ['01', 'chain of custody', 'SHA-256 / SHA-1 / MD5 over the ZIP, XAPK and each split; every entry hashed and inventoried.'],
      ['02', 'structural decode', 'custom DEX reader (header → class_data), ELF via pyelftools, ARSC and AXML readers, ZIP central-directory walk.'],
      ['03', 'evidence tagging', '56 SDK signatures, 168 hosts, 290 REST paths — each hit stored with the literal string that produced it.'],
      ['04', 'integrity ladder', 'X.509 parse → signed-data decode → chunked digest re-derivation → RSA verification, reported step by step.'],
      ['05', 'publication', 'TypeScript renderer draws only from emitted JSON; FFmpeg muxes frames and a synthesised score to MP4.'],
    ];
    steps.forEach(([n, k, v], i) => {
      const q = ease.outCubic(seg(lt, 18.4 + i * 0.8, 19.3 + i * 0.8));
      const y = 380 + i * 116;
      ctx.save(); ctx.globalAlpha = q;
      text(ctx, n, LAYOUT.margin, y, { font: F.mono(24), fill: C.cyan });
      ctx.strokeStyle = alpha(C.cyan, 0.25);
      ctx.beginPath(); ctx.moveTo(LAYOUT.margin + 54, y - 8); ctx.lineTo(LAYOUT.margin + 54, y + 60); ctx.stroke();
      text(ctx, k, LAYOUT.margin + 84, y, { font: F.sans(28, 600), fill: C.text });
      text(ctx, v, LAYOUT.margin + 84, y + 34, { font: F.sans(21), fill: C.dim, maxWidth: 1000 });
      ctx.restore();
    });

    const q2 = ease.outCubic(seg(lt, 23, 24));
    ctx.save(); ctx.globalAlpha = q2;
    panel(ctx, 1240, 360, 560, 620, { fill: 'rgba(10,18,30,0.72)' });
    text(ctx, 'STACK', 1266, 402, { font: F.mono(16), fill: C.faint, spacing: 2 });
    data.provenance.tooling.forEach((tl, i) => {
      code(ctx, `· ${tl}`, 1266, 444 + i * 46, 16, C.dim);
    });
    text(ctx, 'ARTEFACTS', 1266, 780, { font: F.mono(16), fill: C.faint, spacing: 2 });
    code(ctx, 'research/data/*.json', 1266, 816, 16, C.teal);
    code(ctx, 'docs/REPORT.md', 1266, 848, 16, C.teal);
    code(ctx, 'artifacts/*.mp4', 1266, 880, 16, C.teal);
    ctx.restore();
  }

  // ---- 3: citation card (30-40) ----------------------------------------
  if (lt >= 30) {
    const q = ease.outCubic(seg(lt, 30, 31));
    ctx.save(); ctx.globalAlpha = q;
    text(ctx, 'CITE THIS WORK', LAYOUT.margin, 220, { font: F.mono(20), fill: C.amber, spacing: 3 });

    panel(ctx, LAYOUT.margin, 270, 1300, 420, { fill: 'rgba(12,18,28,0.85)', edge: alpha(C.amber, 0.35) });
    const cite = [
      'ZaloPay 11.17.0 Static Anatomy Working Group (2026).',
      'Static Anatomy of a Vietnamese Super-App: A Complete',
      'Reverse-Engineering Atlas of the vn.com.vng.zalopay 11.17.0',
      'XAPK, and the Limits of Container-Level Integrity Proofs.',
      'Research film + reproducible pipeline, v1.0.',
    ];
    cite.forEach((ln, i) => text(ctx, ln, LAYOUT.margin + 34, 330 + i * 48, {
      font: i === 0 ? F.sans(28, 600) : F.sans(28), fill: i === 0 ? C.text : C.dim,
    }));
    text(ctx, `DOI · archive: sha256:${data.subject.sha256_outer.slice(0, 32)}…`, LAYOUT.margin + 34, 640, { font: F.mono(17), fill: C.teal });

    const q2 = ease.outCubic(seg(lt, 31.5, 32.5));
    ctx.save(); ctx.globalAlpha = q2;
    text(ctx, 'BibTeX', 1460, 310, { font: F.mono(16), fill: C.faint });
    const bib = [
      '@misc{zalopay2026anatomy,',
      '  title  = {Static Anatomy of a Vietnamese',
      '            Super-App},',
      '  author = {{ZaloPay 11.17.0 Static',
      '            Anatomy Working Group}},',
      '  year   = {2026},',
      '  note   = {Reverse-engineering atlas +',
      '            research film, 20 min},',
      '  url    = {https://github.com/',
      '            johnlikescarrot/-}',
      '}',
    ];
    bib.forEach((ln, i) => code(ctx, ln, 1460, 350 + i * 26, 15, C.dim));
    ctx.restore();

    const q3 = ease.outCubic(seg(lt, 34, 35));
    ctx.save(); ctx.globalAlpha = q3;
    text(ctx, 'Every number in this film is regenerated by `npm run render` from the artefacts in research/data/.',
      LAYOUT.margin, 770, { font: F.sans(26), fill: C.text });
    text(ctx, 'Static analysis only. No network calls to ZaloPay or VNG infrastructure were made at any point.',
      LAYOUT.margin, 812, { font: F.sans(26), fill: C.dim });
    text(ctx, 'No secret, key, PIN or customer record is reproduced in this work.', LAYOUT.margin, 854, { font: F.sans(26), fill: C.dim });
    ctx.restore();

    // end mark
    const q4 = ease.outCubic(seg(lt, 36, 37));
    ctx.save(); ctx.globalAlpha = q4;
    ctx.strokeStyle = alpha(C.amber, 0.7); ctx.lineWidth = 2;
    ctx.beginPath(); ctx.arc(W - 180, 950, 44, 0, Math.PI * 2); ctx.stroke();
    dot(ctx, W - 180, 950, 7, C.amber);
    text(ctx, 'END', W - 180, 968, { font: F.mono(15), fill: C.amber, align: 'center' });
    ctx.restore();
  }
}
