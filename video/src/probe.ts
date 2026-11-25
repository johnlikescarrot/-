import { createCanvas, GlobalFonts } from '@napi-rs/canvas';
import { writeFileSync } from 'node:fs';
import { Resvg } from '@resvg/resvg-js';

GlobalFonts.registerFromPath('video/assets/fonts/Inter-Medium.otf', 'Inter');
const W = 1920, H = 1080;
const c = createCanvas(W, H);
const ctx = c.getContext('2d');
ctx.fillStyle = '#0a0e14';
ctx.fillRect(0, 0, W, H);
ctx.fillStyle = '#f5c451';
ctx.font = '700 96px Inter';
ctx.fillText('ZaloPay 11.17.0', 120, 300);
ctx.font = '400 40px Inter';
ctx.fillStyle = '#9fb0c6';
ctx.fillText('Điện thoại · ỨNG DỤNG · ₫ 0 — native canvas', 120, 380);
// vector test
ctx.strokeStyle = '#38bdf8';
ctx.lineWidth = 3;
ctx.beginPath();
for (let i = 0; i <= 600; i++) {
  const x = 120 + i, y = 600 + Math.sin(i / 40) * 60;
  if (i === 0) ctx.moveTo(x, y); else ctx.lineTo(x, y);
}
ctx.stroke();
writeFileSync('work/probe_canvas.png', c.toBuffer('image/png'));

const svg = `<svg xmlns="http://www.w3.org/2000/svg" width="${W}" height="${H}">
<rect width="100%" height="100%" fill="#08131f"/>
<text x="120" y="200" font-family="Inter" font-size="88" fill="#7dd3fc">SVG → resvg → PNG</text>
<circle cx="900" cy="600" r="220" fill="none" stroke="#f5c451" stroke-width="6"/>
<text x="120" y="320" font-family="Inter" font-size="36" fill="#e2e8f0">Helvetica-style metrics · harfbuzz shaping in FFmpeg</text>
</svg>`;
const r = new Resvg(svg, { fitTo: { mode: 'width', value: W }, font: { fontDirs: ['video/assets/fonts'], loadSystemFonts: true } });
writeFileSync('work/probe_svg.png', r.render().asPng());
console.log('probe written');
