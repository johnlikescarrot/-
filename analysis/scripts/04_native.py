import sys, os, json, hashlib, math, collections, subprocess
BASE='work/xapk/split/arch/lib/armeabi-v7a'
LIB='./work/venv/lib/python3.11/site-packages/imageio_ffmpeg/binaries/ffmpeg-linux-x86_64-v9.0.2'
def ent(b):
    if not b: return 0.0
    c=collections.Counter(b); n=len(b)
    return -sum((v/n)*math.log2(v/n) for v in c.values())
import subprocess, re
out={}
for f in sorted(os.listdir(BASE)):
    if not f.endswith('.so'): continue
    p=os.path.join(BASE,f); data=open(p,'rb').read()
    # sections via readelf
    secs={}
    try:
        r=subprocess.run(['readelf','-S','-W',p],capture_output=True,text=True).stdout
        for line in r.splitlines():
            m=re.match(r'\s*\[\s*\d+\]\s+(\S+)\s+(\S+)\s+([0-9a-f]+)\s+([0-9a-f]+)\s+([0-9a-f]+)', line)
            if m:
                name,typ,addr,off,size=m.group(1),m.group(2),m.group(3),int(m.group(4),16),int(m.group(5),16)
                secs[name]=dict(type=typ,off=off,size=size,entropy=round(ent(data[off:off+size]),3))
    except Exception as e: pass
    dyn=subprocess.run(['readelf','--dyn-syms','-W',p],capture_output=True,text=True).stdout
    syms=[l.split()[-1] for l in dyn.splitlines()[3:] if l.strip() and len(l.split())>=8]
    jni=[s for s in syms if s.startswith('Java_')]
    imports=sorted({s.split('@')[0] for s in syms if '@' in s})
    interesting=[s for s in imports if re.search(r'(dlopen|dlsym|ptrace|fork|exec|mprotect|syscall|open|read|mmap|pthread_create|__system_property|stat|access|kill|wait|fopen|getenv|memcpy|dex|zip|inflate)', s)]
    out[f]=dict(size=len(data), sha256=hashlib.sha256(data).hexdigest(),
                dynsym=len(syms), jni_exports=len(jni), jni=jni[:12],
                sections=secs,
                high_entropy_sections=[k for k,v in secs.items() if v['entropy']>7.5 and v['size']>4096],
                imports=len(imports), sensitive_imports=interesting)
    print(f"{f:32s} size={len(data):>10,} dyn={len(syms):>4} jni={len(jni):>3} hi-ent={out[f]['high_entropy_sections'][:4]}")
    print(f"   sensitive imports: {', '.join(interesting[:14])}")
json.dump(out, open('analysis/out/native.json','w'), indent=1)
