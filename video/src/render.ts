import { createCanvas, GlobalFonts } from '@napi-rs/canvas';
import fs from 'fs';
import path from 'path';
import { spawnSync } from 'child_process';
import { fileURLToPath } from 'url';

const __dirname = path.dirname(fileURLToPath(import.meta.url));
const ROOT = path.resolve(__dirname, '..', '..');
const OUT_DIR = path.resolve(__dirname, '..', 'out');
const FRAMES = path.join(OUT_DIR, 'frames');
const W = 1920, H = 1080, FPS = 30;

function ensureDir(p: string) { fs.mkdirSync(p, { recursive: true }); }
function readJSON(p: string) { return JSON.parse(fs.readFileSync(p, 'utf-8')); }

const summary = readJSON(path.join(ROOT, 'analysis/inventories/summary.json'));
const xapk = readJSON(path.join(ROOT, 'analysis/inventories/xapk_contents.json'));
const dex = readJSON(path.join(ROOT, 'analysis/security/dex_stats.json'));
const certs = readJSON(path.join(ROOT, 'analysis/security/certificates.json'));
const nativeLibs = readJSON(path.join(ROOT, 'analysis/security/native_libs.json'));
const exported = readJSON(path.join(ROOT, 'analysis/security/exported_components.json'));
const meta = readJSON(path.join(ROOT, 'analysis/apk_metadata.json'));
const appInfo = meta['vn.com.vng.zalopay.apk'];

const totalClasses = dex.reduce((s: number, d: any) => s + (d.classes || 0), 0);
const totalMethods = dex.reduce((s: number, d: any) => s + (d.methods || 0), 0);
const totalStrings = dex.reduce((s: number, d: any) => s + (d.strings || 0), 0);
const totalExported = Object.values(exported).reduce((s: number, v: any) => s + v.length, 0);

const BG = '#0b1020';
const CARD = '#141b2d';
const ACCENT = '#00d4aa';
const ACCENT2 = '#5b8cff';
const WARN = '#ffcc66';
const TEXT = '#e8eefc';
const MUTED = '#9fb0d0';

let frameIndex = 0;
function framePath() { return path.join(FRAMES, `frame_${String(frameIndex++).padStart(6, '0')}.png`); }

function drawBg(ctx: any) {
  ctx.fillStyle = BG; ctx.fillRect(0,0,W,H);
  const g = ctx.createLinearGradient(0,0,W,H);
  g.addColorStop(0,'rgba(0,212,170,0.08)'); g.addColorStop(1,'rgba(91,140,255,0.05)');
  ctx.fillStyle = g; ctx.fillRect(0,0,W,H);
  ctx.strokeStyle = 'rgba(255,255,255,0.04)';
  for (let x=0;x<W;x+=60){ ctx.beginPath(); ctx.moveTo(x,0); ctx.lineTo(x,H); ctx.stroke(); }
  for (let y=0;y<H;y+=60){ ctx.beginPath(); ctx.moveTo(0,y); ctx.lineTo(W,y); ctx.stroke(); }
}

function wrapText(ctx: any, text: string, x: number, y: number, maxW: number, lineH: number, maxLines=99) {
  const words = text.split(/\s+/); let line=''; let lines=0;
  for (let n=0;n<words.length;n++) {
    const test = line + words[n] + ' ';
    if (ctx.measureText(test).width > maxW && n>0) {
      ctx.fillText(line.trim(), x, y); line = words[n] + ' '; y += lineH; lines++;
      if (lines >= maxLines-1 && n < words.length-1) { ctx.fillText(line.trim()+'…', x, y); return y+lineH; }
    } else line = test;
  }
  ctx.fillText(line.trim(), x, y); return y + lineH;
}

function card(ctx: any, x:number,y:number,w:number,h:number,stroke=ACCENT) {
  ctx.fillStyle = CARD; ctx.strokeStyle = stroke; ctx.lineWidth = 2;
  roundRect(ctx,x,y,w,h,18); ctx.fill(); ctx.stroke();
}
function roundRect(ctx:any,x:number,y:number,w:number,h:number,r:number){
  ctx.beginPath(); ctx.moveTo(x+r,y); ctx.arcTo(x+w,y,x+w,y+h,r); ctx.arcTo(x+w,y+h,x,y+h,r); ctx.arcTo(x,y+h,x,y,r); ctx.arcTo(x,y,x+w,y,r); ctx.closePath();
}

function saveFrame(canvas: any) { fs.writeFileSync(framePath(), canvas.toBuffer('image/png')); }

function hold(draw: (p:number)=>void, seconds: number) {
  const n = Math.round(seconds*FPS);
  for (let i=0;i<n;i++){ const c=createCanvas(W,H); const ctx=c.getContext('2d'); draw(i/(n-1||1)); saveFrame(c); }
}

function titleSlide(title: string, subtitle: string, footer: string) {
  hold((p)=>{
    const ctx = createCanvas(W,H).getContext('2d');
    drawBg(ctx);
    ctx.fillStyle = ACCENT; ctx.fillRect(80, 80, 8, 120);
    ctx.fillStyle = TEXT; ctx.font = 'bold 72px sans-serif'; ctx.fillText(title, 110, 150);
    ctx.fillStyle = MUTED; ctx.font = '36px sans-serif'; wrapText(ctx, subtitle, 110, 230, 1600, 48, 3);
    ctx.fillStyle = ACCENT2; ctx.font = '28px sans-serif'; ctx.fillText(footer, 110, 980);
    const alpha = Math.min(1, p*4);
    ctx.globalAlpha = alpha;
    ctx.fillStyle = 'rgba(0,212,170,0.12)'; roundRect(ctx, 110, 360, 1700, 420, 24); ctx.fill();
    ctx.globalAlpha = 1;
  }, 3.5);
}

function statSlide(title: string, stats: Array<[string,string]>, note: string) {
  hold((p)=>{
    const ctx = createCanvas(W,H).getContext('2d');
    drawBg(ctx);
    ctx.fillStyle = ACCENT; ctx.font = 'bold 56px sans-serif'; ctx.fillText(title, 80, 100);
    const cols = 3; const cw = 540; const ch = 140; const gap = 40; const ox=80; const oy=160;
    stats.forEach((s, i) => {
      const col=i%cols, row=Math.floor(i/cols);
      const x=ox+col*(cw+gap), y=oy+row*(ch+gap);
      const show = p*stats.length > i-0.5;
      ctx.globalAlpha = show ? Math.min(1,(p*stats.length-i+0.5)*2) : 0;
      card(ctx,x,y,cw,ch, i%2===0?ACCENT:ACCENT2);
      ctx.fillStyle=MUTED; ctx.font='28px sans-serif'; ctx.fillText(s[0], x+24, y+48);
      ctx.fillStyle=TEXT; ctx.font='bold 44px sans-serif'; ctx.fillText(String(s[1]), x+24, y+105);
    });
    ctx.globalAlpha=1;
    ctx.fillStyle=MUTED; ctx.font='28px sans-serif'; wrapText(ctx,note,80,980,1760,38,2);
  }, 4.5);
}

function listSlide(title: string, items: string[], note: string) {
  hold((p)=>{
    const ctx = createCanvas(W,H).getContext('2d');
    drawBg(ctx);
    ctx.fillStyle = ACCENT; ctx.font = 'bold 52px sans-serif'; ctx.fillText(title, 80, 95);
    card(ctx, 80, 140, 1760, 740, ACCENT2);
    ctx.font='30px sans-serif';
    const visible = Math.floor(p*items.length)+1;
    items.slice(0,visible).forEach((it,i)=>{
      const y = 190 + i*48;
      ctx.fillStyle = i%2===0?TEXT:'#c8d8f0';
      ctx.fillText('• ' + it, 120, y);
    });
    ctx.fillStyle=MUTED; ctx.font='26px sans-serif'; wrapText(ctx,note,80,920,1760,36,2);
  }, 5);
}

function barSlide(title: string, data: Array<[string,number]>, note: string) {
  const max = Math.max(...data.map(d=>d[1]),1);
  hold((p)=>{
    const ctx = createCanvas(W,H).getContext('2d');
    drawBg(ctx);
    ctx.fillStyle=ACCENT; ctx.font='bold 52px sans-serif'; ctx.fillText(title,80,95);
    const ox=320, oy=160, bw=1300, bh=44, gap=18;
    data.forEach((d,i)=>{
      const y=oy+i*(bh+gap);
      const val = d[1]*Math.min(1,p*1.5);
      ctx.fillStyle=MUTED; ctx.font='24px sans-serif'; ctx.fillText(d[0].slice(0,18), 80, y+30);
      ctx.fillStyle='rgba(255,255,255,0.08)'; roundRect(ctx,ox,y,bw,bh,8); ctx.fill();
      ctx.fillStyle=i%2===0?ACCENT:ACCENT2; roundRect(ctx,ox,y,Math.max(2,bw*(val/max)),bh,8); ctx.fill();
      ctx.fillStyle=TEXT; ctx.font='22px sans-serif'; ctx.fillText(String(d[1].toLocaleString()), ox + bw*(val/max) + 12, y+30);
    });
    ctx.fillStyle=MUTED; ctx.font='26px sans-serif'; wrapText(ctx,note,80,980,1760,36,2);
  }, 5);
}

async function main() {
  ensureDir(FRAMES);
  for (const f of fs.readdirSync(FRAMES)) if (f.endsWith('.png')) fs.unlinkSync(path.join(FRAMES,f));

  titleSlide('Zalopay 11.17.0', 'Comprehensive Static Reverse Engineering of All Files in the XAPK/APK Artifact', 'Arena.ai Agent Mode • TypeScript + Node.js + ffmpeg • 2026-11-25');

  statSlide('Artifact Inventory', [
    ['Outer ZIP size', `${(summary.outer_zip_size/1024/1024).toFixed(1)} MB`],
    ['XAPK payload files', String(summary.xapk_file_count)],
    ['Embedded APK splits', String(summary.apk_count)],
    ['Total embedded files', summary.total_embedded_files.toLocaleString()],
    ['Uncompressed size', `${(summary.total_embedded_uncompressed_size/1024/1024).toFixed(1)} MB`],
    ['File types', String(Object.keys(summary.file_type_counts).length)],
  ], 'All files enumerated across outer ZIP, XAPK, and each APK split with sizes, hashes, and type classification.');

  statSlide('App Identity & Signing', [
    ['Package', appInfo.package],
    ['Version', `${appInfo.version_name} (${appInfo.version_code})`],
    ['Target SDK', String(appInfo.target_sdk)],
    ['Min SDK', String(appInfo.min_sdk)],
    ['Signature', `v2=${appInfo.is_signed_v2} v3=${appInfo.is_signed_v3}`],
    ['Cert SHA-256', certs[0].sha256.slice(0,16)+'…'],
  ], `Subject: ${certs[0].subject} | Validity: ${certs[0].not_before} → ${certs[0].not_after}`);

  statSlide('Code Surface (DEX)', [
    ['DEX files', String(dex.length)],
    ['Classes', totalClasses.toLocaleString()],
    ['Methods', totalMethods.toLocaleString()],
    ['Fields', dex.reduce((s:number,d:any)=>s+(d.fields||0),0).toLocaleString()],
    ['Strings', totalStrings.toLocaleString()],
    ['Native .so libs', String(nativeLibs.length)],
  ], 'Per-DEX parsing via Androguard; full cross-reference analysis avoided due sandbox memory constraints.');

  barSlide('Top DEX Files by Class Count', dex.slice(0,10).map((d:any)=>[d.name.replace('classes','c').replace('.dex',''), d.classes||0]), 'Class distribution indicates a large multi-DEX application with heavy SDK/framework integration.');

  statSlide('Component & Attack Surface', [
    ['Activities', String(appInfo.activities.length)],
    ['Services', String(appInfo.services.length)],
    ['Receivers', String(appInfo.receivers.length)],
    ['Providers', String(appInfo.providers.length)],
    ['Exported comps', String(totalExported)],
    ['Permissions', String(appInfo.permissions.length)],
  ], 'Exported components and sensitive permissions define the static attack surface for further manual review.');

  listSlide('Security Observations (Static)', [
    'Large exported surface: review permission guards and intent validation on exported components.',
    'Sensitive permissions: location, camera, mic, contacts, NFC, billing, overlay, QUERY_ALL_PACKAGES.',
    'Obfuscation indicators: short/randomized component names (R8/ProGuard/hardening).',
    'Signing: APK Signature Scheme v2+v3; v1 absent (modern signing).',
    '20 native libraries in ABI split require binary RE (Ghidra/radare2/IDA).',
    'Static URL scan: 446 unique URL-like strings extracted from DEX byte content.',
    'Limitation: no JADX/JRE, no dynamic instrumentation, no runtime unpacking in sandbox.',
  ], 'Static findings should be validated with dedicated tooling and controlled dynamic analysis.');

  listSlide('Methodology & Reproducibility', [
    'Enumerate all files in ZIP/XAPK/APK splits via Python zipfile; record SHA-256 hashes.',
    'Decode AndroidManifest.xml and extract metadata/components via Androguard APK parser.',
    'Parse each DEX independently for class/method/field/string counts (memory-safe).',
    'Regex-scan DEX bytes for URL-like endpoints; collect native libs and cert fingerprints.',
    'Generate structured JSON artifacts + Markdown report + TypeScript video pipeline.',
    'Render MP4 with Node.js (@napi-rs/canvas) + ffmpeg (H.264/AAC).',
  ], 'Repository: analysis/scripts/*.py reproduce the static extraction; video/src/render.ts renders this MP4.');

  titleSlide('Thank You', 'Comprehensive static RE artifacts, inventories, and reproducible pipeline are committed to the repository.', 'Zalopay 11.17.0 • vn.com.vng.zalopay • Static RE Report');

  console.log(`Frames: ${frameIndex}`);

  const ffmpeg = process.env.FFMPEG_PATH || 'ffmpeg';
  // Build audio: ambient pad + subtle pulse
  const audio = path.join(OUT_DIR, 'audio.m4a');
  const duration = frameIndex / FPS;
  const af = `sine=frequency=110:sample_rate=44100:duration=${duration},volume=0.03,lowpass=f=900,afade=t=in:st=0:d=2,afade=t=out:st=${Math.max(0,duration-3)}:d=3`;
  const audioCmd = [ffmpeg,'-y','-f','lavfi','-i',af,'-c:a','aac','-b:a','192k',audio];
  console.log('Generating audio...');
  let r = spawnSync(audioCmd[0], audioCmd.slice(1), {stdio:'inherit'});
  if (r.status !== 0) { console.error('audio failed, continuing without'); }

  const outMp4 = path.join(OUT_DIR, 'zalopay-11.17.0-reverse-engineering.mp4');
  const hasAudio = fs.existsSync(audio) && fs.statSync(audio).size > 1000;
  const args = ['-y','-framerate', String(FPS), '-i', path.join(FRAMES,'frame_%06d.png')];
  if (hasAudio) args.push('-i', audio);
  args.push('-vf','scale=1920:1080,format=yuv420p','-c:v','libx264','-preset','medium','-crf','20');
  if (hasAudio) args.push('-c:a','aac','-b:a','192k','-shortest');
  args.push('-movflags','+faststart', outMp4);
  console.log('Encoding video...');
  r = spawnSync(ffmpeg, args, {stdio:'inherit'});
  if (r.status !== 0) { console.error('ffmpeg failed'); process.exit(1); }
  console.log('Video:', outMp4);
}

main().catch(e=>{ console.error(e); process.exit(1); });
