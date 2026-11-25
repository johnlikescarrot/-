import os,re,json,hashlib,math,collections,csv
ROOT='work/xapk/split'
def ent(b):
    if not b: return 0.0
    c=collections.Counter(b); n=len(b)
    return -sum((v/n)*math.log2(v/n) for v in c.values())
def classify(p):
    e=os.path.splitext(p)[1].lower()
    m={'.dex':'dex','.so':'native','.png':'image','.webp':'image','.jpg':'image','.xml':'xml','.json':'json','.js':'js','.dat':'blob','.bin':'blob','.tflite':'ml','.tfl':'ml','.pb':'ml','.emd':'ml','.param':'ml','.zip':'archive','.arsc':'resources','.ttf':'font','.otf':'font','.pem':'key','.txt':'text','.proto':'proto','.rsa':'sig','.sf':'sig','.mf':'sig'}
    return m.get(e,'other')
rows=[]; total=0
for dirpath,_,files in os.walk(ROOT):
    for f in files:
        p=os.path.join(dirpath,f)
        sz=os.path.getsize(p)
        if sz>8_000_000 and not p.endswith(('.so','.dex','.zip','.arsc')): 
            h=''; e=0.0
        else:
            with open(p,'rb') as fh: d=fh.read(2_000_000)
            h=hashlib.sha256(d).hexdigest()[:16]; e=round(ent(d),3) if sz<60_000_000 else 0.0
        total+=sz
        rows.append(dict(path=os.path.relpath(p,ROOT), size=sz, ext=os.path.splitext(p)[1].lower(),
                         kind=classify(p), sha256_16=h, entropy=e))
rows.sort(key=lambda r:-r['size'])
with open('analysis/out/file_inventory.csv','w',newline='') as f:
    w=csv.DictWriter(f, fieldnames=list(rows[0].keys())); w.writeheader(); w.writerows(rows)
kinds=collections.Counter(r['kind'] for r in rows)
sizes=collections.Counter()
for r in rows: sizes[r['kind']]+=r['size']
print(f"files={len(rows)} total={total:,} bytes")
print("by kind (count / bytes):")
for k,c in kinds.most_common(): print(f"  {k:10s} {c:>6}  {sizes[k]:>14,}")
print("\nTop 20 largest:")
for r in rows[:20]: print(f"  {r['size']:>10,}  {r['kind']:9s}  {r['path']}")
print("\nHigh-entropy (>7.9) files >64KB:")
for r in rows:
    if r['entropy']>7.9 and r['size']>65536: print(f"  {r['entropy']:.3f} {r['size']:>10,}  {r['path']}")
json.dump(dict(files=len(rows),total=total,by_kind=kinds.most_common(),bytes_by_kind=sizes.most_common()), open('analysis/out/file_inventory_summary.json','w'), indent=1)
