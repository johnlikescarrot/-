import sys,os,re,json,math,collections
sys.path.insert(0,'analysis/scripts')
from dexparse import Dex
BASE='work/xapk/split/base'
dexes=sorted([f for f in os.listdir(BASE) if re.fullmatch(r'classes\d*\.dex',f)])
S=[]
for f in dexes: S += Dex(os.path.join(BASE,f)).strings()
S=sorted(set(S)); blob='\n'.join(S)
pats={
 'google_api_key': r'AIza[0-9A-Za-z_\-]{35}',
 'aws_access_key': r'AKIA[0-9A-Z]{16}',
 'aws_secret': r'(?i)aws_?secret_?access_?key["\']?\s*[:=]\s*["\']?([A-Za-z0-9/+=]{40})',
 'firebase_url': r'https://[a-z0-9\-]+\.firebaseio\.com',
 'firebase_app': r'[a-z0-9\-]+\.firebaseapp\.com',
 'private_key_block': r'-----BEGIN (?:RSA |EC |DSA |OPENSSH )?PRIVATE KEY-----',
 'public_key_block': r'-----BEGIN PUBLIC KEY-----',
 'jwt': r'eyJ[A-Za-z0-9_\-]{8,}\.[A-Za-z0-9_\-]{8,}\.[A-Za-z0-9_\-]{4,}',
 'basic_auth_url': r'https?://[^/\s:@]+:[^/\s:@]+@[^/\s]+',
 'sentry_dsn': r'https://[0-9a-f]{32}@[a-z0-9.\-]+/\d+',
 'maps_key': r'com\.google\.android\.geo\.API_KEY',
 'appsflyer_key': r'com\.appsflyer\.AF_DEV_KEY',
 'fb_client_token': r'[0-9a-f]{32}',
 'slack_webhook': r'https://hooks\.slack\.com/services/[A-Za-z0-9/]+',
 'twilio': r'SK[0-9a-f]{32}',
 'stripe': r'(?:sk|pk)_(?:live|test)_[0-9A-Za-z]{16,}',
}
res={}
for k,p in pats.items():
    m=sorted(set(x if isinstance(x,str) else x[0] for x in re.findall(p, blob)))
    res[k]=m[:20]
    if m: print(f"{k}: {len(m)} -> {m[:4]}")
# credential-ish string contexts
ctx=[]
for i,s in enumerate(S):
    if re.search(r'(?i)(api[_\-]?secret|client[_\-]?secret|private[_\-]?key|access[_\-]?token|password|passwd|pwd)\s*[=:]', s) and len(s)<200:
        ctx.append(s)
res['credential_contexts']=ctx[:60]
print("\ncredential-ish contexts:", len(ctx))
for c in ctx[:18]: print("   ", c[:150])
json.dump(res, open('analysis/out/secrets.json','w'), indent=1, ensure_ascii=False)
