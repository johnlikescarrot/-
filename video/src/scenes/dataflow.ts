/** Act 4 — endpoint topology, mini-app ecosystem, PIN-envelope surface, privacy inventory. */
import type { Ctx } from '../lib.js';
import { alpha, chapterCard, chrome, code, dot, ease, panel, rng, seg, text, underline } from '../lib.js';
import { C, F, H, LAYOUT, W } from '../theme.js';
import { data, fmt } from '../data.js';

export const flowChapter = { index: 4, title: 'Endpoint topology · mini-apps · privacy surface', kicker: 'Act IV' };
export const flowDuration = 44;
const CARD = 3.4;

export function drawDataflow(ctx: Ctx, t: number): void {
  chrome(ctx, t, flowDuration, flowChapter);
  if (t < CARD) {
    chapterCard(ctx, t, 4, 'Act IV · the surface',
      'What the app talks to, and what it asks for',
      '168 hosts, 45 of them first-party. Permissions, SDKs and endpoint names sketch the data-flow map without ever leaving the binary.');
    return;
  }
  const lt = t - CARD;

  // ---- 1: host topology (0-14) -------------------------------------------
  if (lt < 14) {
    const p = ease.outCubic(seg(lt, 0, 0.8));
    text(ctx, 'ENDPOINT TOPOLOGY', LAYOUT.margin, 200, { font: F.mono(20), fill: C.cyan, spacing: 3 });
    text(ctx, 'ZaloPay owns the wallet, not the pipe', LAYOUT.margin, 254, { font: F.sans(52, 700) });
    underline(ctx, LAYOUT.margin, 278, 560, p, C.cyan);

    const cx = 520, cy = 620;
    // centre
    dot(ctx, cx, cy, 12, C.amber);
    text(ctx, 'ZaloPay', cx, cy + 44, { font: F.sans(26, 600), fill: C.text, align: 'center' });

    const fp = data.endpoints.first_party.slice(0, 10);
    fp.forEach((h, i) => {
      const ang = -Math.PI / 2 + (i / fp.length) * Math.PI * 2;
      const r = 250 + (h.hits > 30 ? 30 : 0);
      const x = cx + Math.cos(ang) * r, y = cy + Math.sin(ang) * r * 0.78;
      const q = ease.outCubic(seg(lt, 0.4 + i * 0.12, 1.3 + i * 0.12));
      ctx.save(); ctx.globalAlpha = q;
      ctx.strokeStyle = alpha(C.cyan, 0.35);
      ctx.beginPath(); ctx.moveTo(cx, cy); ctx.lineTo(cx + (x - cx) * q, cy + (y - cy) * q); ctx.stroke();
      dot(ctx, x, y, 4 + Math.min(8, h.hits / 10), C.cyan, 0.9);
      text(ctx, h.host, x, y - 16, { font: F.mono(15), fill: C.text, align: 'center' });
      ctx.restore();
    });

    // third-party classes stacked right
    const classes = Object.entries(data.endpoints.classes).filter(([k]) => k.startsWith('third-party'));
    text(ctx, 'THIRD-PARTY DESTINATIONS', 1120, 360, { font: F.mono(17), fill: C.faint });
    classes.slice(0, 10).forEach(([k, v], i) => {
      const q = ease.outCubic(seg(lt, 2.4 + i * 0.16, 3.2 + i * 0.16));
      const y = 404 + i * 40;
      ctx.save(); ctx.globalAlpha = q;
      text(ctx, k.replace('third-party · ', ''), 1120, y, { font: F.sans(20), fill: C.text });
      text(ctx, `${v}`, 1740, y, { font: F.mono(18), fill: C.dim, align: 'right' });
      ctx.fillStyle = alpha(C.magenta, 0.5);
      ctx.fillRect(1400 - Math.min(200, v * 4), y - 14, Math.min(200, v * 4), 8);
      ctx.restore();
    });

    const s = ease.outCubic(seg(lt, 4.2, 5.0));
    ctx.save(); ctx.globalAlpha = s;
    panel(ctx, 1120, 830, 620, 140, { fill: 'rgba(10,26,30,0.7)', edge: alpha(C.teal, 0.4) });
    text(ctx, `${data.endpoints.hosts} unique hosts · ${data.endpoints.first_party_count} first-party`,
      1146, 872, { font: F.sans(25, 600), fill: C.text });
    text(ctx, `${data.endpoints.api_paths} distinct REST paths survive R8 as string constants —`,
      1146, 906, { font: F.sans(19), fill: C.dim });
    text(ctx, 'the protocol surface is legible even when the classes are not.',
      1146, 934, { font: F.sans(19), fill: C.dim });
    ctx.restore();
  }

  // ---- 2: mini-app ecosystem (14-26) -------------------------------------
  if (lt >= 14 && lt < 27) {
    const p = ease.outCubic(seg(lt, 14, 15));
    text(ctx, 'SUPER-APP SURFACE', LAYOUT.margin, 200, { font: F.mono(20), fill: C.amber, spacing: 3 });
    text(ctx, `${data.miniapps.count} mini-apps shipped as configuration`, LAYOUT.margin, 254, { font: F.sans(52, 700) });
    underline(ctx, LAYOUT.margin, 278, 620, p, C.amber);

    // category bars
    const cats = data.miniapps.categories.slice(0, 8);
    cats.forEach(([name, n], i) => {
      const q = ease.outCubic(seg(lt, 14.4 + i * 0.16, 15.3 + i * 0.16));
      const y = 360 + i * 62;
      ctx.save(); ctx.globalAlpha = q;
      text(ctx, name, LAYOUT.margin, y, { font: F.sans(23), fill: C.text });
      ctx.fillStyle = alpha(C.amber, 0.22); ctx.fillRect(560, y - 18, 420, 20);
      ctx.fillStyle = C.amber; ctx.fillRect(560, y - 18, 420 * (n / cats[0]![1]) * q, 20);
      text(ctx, String(n), 1004, y, { font: F.mono(20), fill: C.dim });
      ctx.restore();
    });

    // deep link + host chain
    const q2 = ease.outCubic(seg(lt, 16.5, 17.5));
    ctx.save(); ctx.globalAlpha = q2;
    piece(ctx, 1100, 380, 'DEEP LINK', data.miniapps.deep_link, C.amber);
    data.miniapps.hosts.slice(0, 5).forEach(([host, n], i) => {
      piece(ctx, 1100, 470 + i * 84, `HOST · ${n} APPS`, host, C.cyan);
    });
    ctx.restore();

    const q3 = ease.outCubic(seg(lt, 20, 21));
    ctx.save(); ctx.globalAlpha = q3;
    panel(ctx, LAYOUT.margin, 900, 900, 96, { fill: 'rgba(26,20,10,0.7)', edge: alpha(C.amber, 0.4) });
    text(ctx, 'The catalogue is data, not code: 373 apps live in one 883 KiB JSON — the runtime is Alibaba mPaaS.',
      LAYOUT.margin + 24, 958, { font: F.sans(22), fill: C.text });
    ctx.restore();
  }

  // ---- 3: PIN envelope + privacy (27-44) --------------------------------
  if (lt >= 27) {
    const p = ease.outCubic(seg(lt, 27, 28));
    text(ctx, 'PROTOCOL SHAPE & PRIVACY', LAYOUT.margin, 200, { font: F.mono(20), fill: C.magenta, spacing: 3 });
    text(ctx, 'What the endpoint names reveal — and what they do not', LAYOUT.margin, 254, { font: F.sans(52, 700) });
    underline(ctx, LAYOUT.margin, 278, 900, p, C.magenta);

    // envelope diagram
    const groups: [string, string[], string][] = [
      ['KEY DISCOVERY', data.pin_surface.key_discovery, C.amber],
      ['SESSION / OTP', data.pin_surface.session_and_otp.slice(0, 4), C.cyan],
      ['EKYC', data.pin_surface.ekyc.slice(0, 4), C.violet],
    ];
    groups.forEach(([title, items, color], gi) => {
      const x = LAYOUT.margin + gi * 420;
      const q = ease.outCubic(seg(lt, 27.4 + gi * 0.4, 28.3 + gi * 0.4));
      ctx.save(); ctx.globalAlpha = q;
      text(ctx, title, x, 370, { font: F.mono(17), fill: color, spacing: 2 });
      items.forEach((it, i) => {
        code(ctx, it, x, 406 + i * 27, 14, C.dim);
      });
      ctx.restore();
    });

    // permission donut
    const man = data.manifest;
    const permTotal = man.permissions;
    const danger = man.dangerous.length;
    const cx = 1640, cy = 690, r = 96;
    const q = ease.outCubic(seg(lt, 28.5, 29.5));
    ctx.save();
    ctx.lineWidth = 22;
    ctx.strokeStyle = alpha(C.faint, 0.25);
    ctx.beginPath(); ctx.arc(cx, cy, r, 0, Math.PI * 2); ctx.stroke();
    ctx.strokeStyle = C.red;
    ctx.beginPath(); ctx.arc(cx, cy, r, -Math.PI / 2, -Math.PI / 2 + Math.PI * 2 * (danger / permTotal) * q); ctx.stroke();
    ctx.restore();
    text(ctx, `${danger}`, cx, cy + 2, { font: F.sans(48, 700), fill: C.text, align: 'center' });
    text(ctx, `of ${permTotal} runtime`, cx, cy + 36, { font: F.sans(16), fill: C.dim, align: 'center' });
    text(ctx, `permissions are`, cx, cy + 58, { font: F.sans(16), fill: C.dim, align: 'center' });
    text(ctx, `dangerous-class`, cx, cy + 80, { font: F.sans(16), fill: C.dim, align: 'center' });

    // dangerous list
    text(ctx, 'DANGEROUS-CLASS PERMISSIONS', LAYOUT.margin, 510, { font: F.mono(17), fill: C.faint });
    man.dangerous.forEach((d, i) => {
      const col = i % 2, row = Math.floor(i / 2);
      const q2 = ease.outCubic(seg(lt, 29 + i * 0.1, 29.8 + i * 0.1));
      ctx.save(); ctx.globalAlpha = q2;
      text(ctx, d.replace('android.permission.', ''), LAYOUT.margin + col * 400, 552 + row * 32, { font: F.mono(15), fill: C.dim });
      ctx.restore();
    });

    const q3 = ease.outCubic(seg(lt, 33, 34));
    ctx.save(); ctx.globalAlpha = q3;
    panel(ctx, LAYOUT.margin, 790, 1080, 170, { fill: 'rgba(30,12,24,0.72)', edge: alpha(C.magenta, 0.4) });
    text(ctx, 'METHODOLOGICAL LIMIT', LAYOUT.margin + 26, 830, { font: F.mono(16), fill: C.magenta, spacing: 2 });
    const lines = [
      'Endpoint names show that a PIN is validated server-side against a per-user salt and a',
      'server public key; they do not show the envelope format, the key exchange or the PIN block.',
      'No secret is recovered here — and the report states plainly where the plaintext ends.',
    ];
    lines.forEach((ln, i) => text(ctx, ln, LAYOUT.margin + 26, 868 + i * 30, { font: F.sans(22), fill: C.text }));
    ctx.restore();

    // data flow arrows decoration
    const rr = rng(7);
    for (let i = 0; i < 40; i++) {
      const x = rr() * W, y = 300 + rr() * 500;
      dot(ctx, x, y, 1.5, C.faint, 0.25 * (0.5 + 0.5 * Math.sin(lt * 2 + i)));
    }
  }
}

function piece(ctx: Ctx, x: number, y: number, label: string, value: string, color: string): void {
  text(ctx, label, x, y, { font: F.mono(14), fill: C.faint });
  text(ctx, value, x, y + 30, { font: F.sans(24, 600), fill: color });
  ctx.strokeStyle = alpha(color, 0.3);
  ctx.beginPath(); ctx.moveTo(x, y + 44); ctx.lineTo(x + 700, y + 44); ctx.stroke();
}
