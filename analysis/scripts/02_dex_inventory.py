import sys, os, json, re, collections
sys.path.insert(0,'analysis/scripts')
from dexparse import Dex

BASE='work/xapk/split/base'
dexes=sorted([f for f in os.listdir(BASE) if re.fullmatch(r'classes\d*\.dex', f)],
             key=lambda x: (0 if x=='classes.dex' else int(re.search(r'(\d+)',x).group(1))))
tot=dict(strings=0,types=0,protos=0,fields=0,methods=0,classes=0,size=0)
pkgs=collections.Counter(); allclasses=[]
per=[]
for f in dexes:
    d=Dex(os.path.join(BASE,f)); s=d.stats()
    for k in tot:
        if k=='size': continue
        tot[k]+= s[k]
    tot['size']+=s['file_size']
    cls=d.class_defs()
    for c in cls:
        n=c['name'] or ''
        base=n.split('/')[0] if '/' in n else n
        parts=n.split('/')
        pkgs['/'.join(parts[:2]) if len(parts)>1 else base]+=1
        allclasses.append(n)
    per.append(dict(file=f, **{k:s[k] for k in ('strings','types','methods','fields','classes','file_size')}))
    print(f"{f:16s} strings={s['strings']:>6} types={s['types']:>6} methods={s['methods']:>6} classes={s['classes']:>6} size={s['file_size']:>9,}", flush=True)
print('\nTOTAL', json.dumps(tot))
print('classes total:', len(allclasses), 'unique:', len(set(allclasses)))
print('dupes:', len(allclasses)-len(set(allclasses)))
print('\nTOP 30 PACKAGES (class count):')
for p,c in pkgs.most_common(30): print(f"  {c:>6}  {p}")
json.dump(dict(per_dex=per, total=tot, n_classes=len(allclasses),
               packages=pkgs.most_common(200), classes=allclasses),
          open('analysis/out/dex_inventory.json','w'), indent=1)
