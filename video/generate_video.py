#!/usr/bin/env python3
import json, math, os, wave, struct, tempfile, subprocess, shutil
from pathlib import Path
import numpy as np
from PIL import Image, ImageDraw, ImageFont
import imageio.v2 as imageio

ROOT = Path('/home/user/-')
OUT = ROOT/'analysis'
VID = ROOT/'video'
VID.mkdir(parents=True, exist_ok=True)

# load data
summary = json.load(open(OUT/'summary.json'))
deep = json.load(open(OUT/'deep_analysis.json')) if (OUT/'deep_analysis.json').exists() else {}
inv = json.load(open(OUT/'file_inventory.json'))

W,H = 1920,1080
FPS = 30
DURATION_PER_SLIDE = 5  # seconds
BG = (12, 18, 30)
ACCENT = (0, 200, 170)
ACCENT2 = (80, 140, 255)
WHITE = (240, 245, 250)
MUTED = (160, 175, 190)

def font(size, bold=False):
    # Try common fonts
    for p in [
        '/usr/share/fonts/truetype/dejavu/DejaVuSans-Bold.ttf' if bold else '/usr/share/fonts/truetype/dejavu/DejaVuSans.ttf',
        '/usr/share/fonts/TTF/DejaVuSans-Bold.ttf' if bold else '/usr/share/fonts/TTF/DejaVuSans.ttf',
        '/usr/share/fonts/truetype/liberation/LiberationSans-Bold.ttf' if bold else '/usr/share/fonts/truetype/liberation/LiberationSans-Regular.ttf',
    ]:
        if os.path.exists(p):
            return ImageFont.truetype(p, size)
    return ImageFont.load_default()

F_TITLE = font(64, True)
F_H2 = font(44, True)
F_BODY = font(32)
F_SMALL = font(26)
F_TINY = font(22)

def wrap(draw, text, fnt, max_w):
    words = text.split()
    lines=[]; cur=''
    for w in words:
        t = (cur+' '+w).strip()
        if draw.textlength(t, font=fnt) <= max_w:
            cur=t
        else:
            if cur: lines.append(cur)
            cur=w
    if cur: lines.append(cur)
    return lines

def draw_bg(draw):
    for y in range(H):
        r = int(BG[0] + (y/H)*8)
        g = int(BG[1] + (y/H)*12)
        b = int(BG[2] + (y/H)*18)
        draw.line([(0,y),(W,y)], fill=(r,g,b))
    # accent line
    draw.rectangle([(0,0),(W,8)], fill=ACCENT)

def slide_title(title, subtitle, bullets=None):
    img = Image.new('RGB',(W,H),BG)
    d = ImageDraw.Draw(img)
    draw_bg(d)
    d.text((80,80), 'REVERSE ENGINEERING REPORT', font=F_TINY, fill=ACCENT)
    y=150
    for line in wrap(d, title, F_TITLE, W-160):
        d.text((80,y), line, font=F_TITLE, fill=WHITE)
        y+=74
    y+=10
    for line in wrap(d, subtitle, F_BODY, W-160):
        d.text((80,y), line, font=F_BODY, fill=MUTED)
        y+=42
    if bullets:
        y+=30
        for b in bullets[:7]:
            for i,line in enumerate(wrap(d, '• '+b, F_BODY, W-180)):
                d.text((100,y), line, font=F_BODY, fill=WHITE if i==0 else MUTED)
                y+=40
            y+=6
    d.text((80,H-60), 'Zalopay 11.17.0 APKPure | Arena.ai Agent Mode | 2026-11-25', font=F_TINY, fill=MUTED)
    return np.array(img)

def slide_kv(title, kvs):
    img = Image.new('RGB',(W,H),BG)
    d = ImageDraw.Draw(img)
    draw_bg(d)
    d.text((80,70), title, font=F_H2, fill=ACCENT)
    y=150
    for k,v in kvs[:14]:
        d.text((80,y), str(k), font=F_SMALL, fill=MUTED)
        for i,line in enumerate(wrap(d, str(v), F_SMALL, W-520)):
            d.text((480,y+i*32), line, font=F_SMALL, fill=WHITE)
        y += max(38, 32*len(wrap(d,str(v),F_SMALL,W-520))) + 8
        if y > H-80: break
    return np.array(img)

def slide_list(title, items, cols=1):
    img = Image.new('RGB',(W,H),BG)
    d = ImageDraw.Draw(img)
    draw_bg(d)
    d.text((80,70), title, font=F_H2, fill=ACCENT)
    y=150
    col_w = (W-160)//cols
    for idx,it in enumerate(items[:(18*cols)]):
        col = idx % cols
        row = idx // cols
        x = 80 + col*col_w
        yy = 150 + row*44
        if yy > H-80: break
        txt = str(it)
        if d.textlength(txt, font=F_SMALL) > col_w-20:
            while d.textlength(txt+'…', font=F_SMALL) > col_w-20 and len(txt)>0:
                txt=txt[:-1]
            txt+='…'
        d.text((x,yy), txt, font=F_SMALL, fill=WHITE)
    return np.array(img)

slides = []
slides.append(slide_title(
    'Zalopay 11.17.0 — Toàn diện Reverse Engineering',
    'Phân tích tĩnh toàn bộ tệp trong gói XAPK/APK (6.464 tệp bên trong). '
    'Báo cáo được tạo tự động bởi pipeline TypeScript/Python trên Arena.ai Agent Mode.',
    ['Mục tiêu: kiểm kê 100% tệp, trích xuất metadata, phân tích bề mặt tấn công',
     'Công cụ: Androguard, Python, phân tích hash/manifest/DEX/certificate',
     'Đầu ra: báo cáo kỹ thuật, inventory JSON, video MP4']
))

slides.append(slide_kv('Tổng quan gói', [
    ('Package', deep.get('package','vn.com.vng.zalopay')),
    ('App name', deep.get('app_name','Zalopay')),
    ('Version', f"{deep.get('version_name','11.17.0')} ({deep.get('version_code','1102758')})"),
    ('SDK', f"min {deep.get('min_sdk','26')} / target {deep.get('target_sdk','36')}"),
    ('Outer files', summary.get('total_files')),
    ('Inner files', summary.get('inner_file_count')),
    ('Total size', f"{summary.get('total_size',0):,} bytes"),
    ('DEX files', deep.get('dex_stats',{}).get('dex_files')),
    ('Classes', f"{deep.get('dex_stats',{}).get('classes',0):,}"),
    ('Methods', f"{deep.get('dex_stats',{}).get('methods',0):,}"),
    ('Strings', f"{deep.get('dex_stats',{}).get('strings',0):,}"),
]))

slides.append(slide_kv('Inventory ngoại vi (XAPK)', [
    (r['path'].split('/')[-1], f"{r['size']:,} B | {r['type']} | sha256:{r['sha256'][:12]}…")
    for r in inv
]))

by_type = {}
for r in json.load(open(OUT/'inner_file_inventory.json')):
    by_type[r.get('type','other')] = by_type.get(r.get('type','other'),0)+1
slides.append(slide_kv('Phân loại 6.464 tệp bên trong', [
    (k, str(v)) for k,v in sorted(by_type.items(), key=lambda x:-x[1])
]))

perms = deep.get('permissions',[])
slides.append(slide_list('Permissions (nhóm chính)', [p.replace('android.permission.','') for p in perms[:36]], cols=2))

comps = [
    f"Activities: {len(deep.get('activities',[]))}",
    f"Services: {len(deep.get('services',[]))}",
    f"Receivers: {len(deep.get('receivers',[]))}",
    f"Providers: {len(deep.get('providers',[]))}",
] + [a.split('.')[-1] for a in deep.get('activities',[])[:28]]
slides.append(slide_list('Components', comps, cols=2))

certs = deep.get('certificates',[])
cert_kv=[]
for c in certs:
    cert_kv += [('Subject', c.get('subject','')), ('Issuer', c.get('issuer','')), ('SHA256', c.get('sha256_fingerprint','')), ('Validity', f"{c.get('not_before','')} -> {c.get('not_after','')}")]
slides.append(slide_kv('Chữ ký / Certificate', cert_kv[:12]))

top_pkgs = deep.get('top_packages',[])[:24]
slides.append(slide_list('Top packages (theo số class)', [f"{p} ({c})" for p,c in top_pkgs], cols=2))

urls = deep.get('urls',[])[:24]
slides.append(slide_list('Network endpoints (sample)', urls, cols=1))

natives = deep.get('native_libs',[])[:24]
slides.append(slide_list('Native libraries (sample)', natives, cols=1))

slides.append(slide_title(
    'Kết luận & Khuyến nghị',
    'Phân tích tĩnh toàn diện đã lập bản đồ bề mặt ứng dụng: permissions, components, DEX, native libs, cert, endpoints.',
    ['Kiểm tra permission nhạy cảm (CAMERA, LOCATION, CONTACTS, RECORD_AUDIO)',
     'Rà soát endpoint/secret hint trong chuỗi; xác minh TLS/pinning ở runtime',
     'Đối chiếu chữ ký với bản phát hành chính thức trước khi tin cậy',
     'Toàn bộ artifact được đồng bộ qua Git/PR trên branch arena/f26fab9d-repo']
))

# Audio - layered synth ambient track
sr = 44100
total_dur = len(slides)*DURATION_PER_SLIDE
t = np.linspace(0, total_dur, int(sr*total_dur), endpoint=False)
audio = np.zeros_like(t)
# base drone
for freq,amp in [(55,0.08),(110,0.05),(220,0.03)]:
    audio += amp*np.sin(2*np.pi*freq*t)
# slow pad
audio += 0.04*np.sin(2*np.pi*130.81*t + 0.2*np.sin(2*np.pi*0.1*t))
audio += 0.03*np.sin(2*np.pi*196.00*t + 0.3*np.sin(2*np.pi*0.07*t))
# gentle arpeggio pulses per slide
for i in range(len(slides)):
    start = i*DURATION_PER_SLIDE
    for j,note in enumerate([261.63,329.63,392.00,523.25]):
        nstart = start + j*0.5
        idx0 = int(nstart*sr); idx1 = int((nstart+0.4)*sr)
        if idx1 > len(audio): break
        tt = np.arange(idx1-idx0)/sr
        env = np.exp(-3*tt)
        audio[idx0:idx1] += 0.06*np.sin(2*np.pi*note*tt)*env
# subtle noise texture
rng = np.random.default_rng(42)
audio += 0.005*rng.standard_normal(len(audio))
# normalize
audio = audio / (np.max(np.abs(audio))+1e-6) * 0.85
# fade in/out
fade = int(sr*1.5)
ramp = np.linspace(0,1,fade)
audio[:fade] *= ramp
audio[-fade:] *= ramp[::-1]
audio_i16 = (audio*32767).astype(np.int16)
wav_path = VID/'bgm.wav'
with wave.open(str(wav_path),'w') as wf:
    wf.setnchannels(1); wf.setsampwidth(2); wf.setframerate(sr)
    wf.writeframes(audio_i16.tobytes())

# Write silent video first
tmp_video = VID/'tmp_silent.mp4'
writer = imageio.get_writer(str(tmp_video), fps=FPS, codec='libx264', quality=8, pixelformat='yuv420p', macro_block_size=None)
for i,frame in enumerate(slides):
    for _ in range(DURATION_PER_SLIDE*FPS):
        writer.append_data(frame)
    print(f'slide {i+1}/{len(slides)}', flush=True)
writer.close()

# Mux with ffmpeg
import imageio_ffmpeg
ff = imageio_ffmpeg.get_ffmpeg_exe()
video_path = VID/'zalopay-re-11.17.0.mp4'
cmd = [ff,'-y','-i',str(tmp_video),'-i',str(wav_path),'-c:v','copy','-c:a','aac','-b:a','192k','-shortest',str(video_path)]
subprocess.run(cmd, check=True, stdout=subprocess.PIPE, stderr=subprocess.PIPE)
try: tmp_video.unlink()
except: pass
print('video', video_path, video_path.stat().st_size, flush=True)
