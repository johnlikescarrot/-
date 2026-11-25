import sys,os,re,json,collections,zipfile,subprocess,logging
sys.path.insert(0,'analysis/scripts')
from dexparse import Dex
logging.disable(logging.CRITICAL)
BASE='work/xapk/split/base'
dexes=sorted([f for f in os.listdir(BASE) if re.fullmatch(r'classes\d*\.dex',f)],
             key=lambda x:(0 if x=='classes.dex' else int(re.search(r'(\d+)',x).group(1))))
S=set(); classes=[]
for f in dexes:
    d=Dex(os.path.join(BASE,f)); S.update(d.strings()); classes += [c['name'] for c in d.class_defs()]
S=sorted(S); blob='\n'.join(S)
M={}
M['corpus']=dict(dex_files=len(dexes), strings=len(S), classes=len(classes))
libs={'AndroidX':r'^androidx\.','BouncyCastle':r'^org\.bouncycastle','Alibaba ARiver/Griver':r'^com\.alibaba',
 'Google Play Services':r'^com\.google\.android\.gms','Firebase':r'^com\.google\.firebase','OkHttp':r'^okhttp3',
 'RxJava':r'^io\.reactivex','JMRTD (NFC passport)':r'^org\.jmrtd','Nimbus JOSE+JWT':r'^com\.nimbusds',
 'Alipay+ (Ant) SDK':r'^com\.alipay','IAP Connect':r'^(com\.iap|Lcom/iap)','Visa CBP':r'^com\.visa',
 'VNPay SDK':r'^com\.vnpay','TrueID (VN AI)':r'^vn\.ai','Facebook SDK/AN':r'^com\.facebook',
 'AppsFlyer':r'^com\.appsflyer','Kotlin coroutines':r'^kotlinx\.coroutines','Rive':r'^app\.rive',
 'Coil/Glide':r'^(coil|com\.bumptech)','Google MLKit':r'^com\.google\.mlkit','Gson':r'^com\.google\.gson',
 'ZaloPay first-party':r'^vn\.com\.vng\.zalopay','Jsoup':r'^org\.jsoup','Room':r'^androidx\.room'}
M['libraries']={k:sum(1 for c in classes if re.search(p,c)) for k,p in libs.items()}
M['libraries']={k:v for k,v in sorted(M['libraries'].items(), key=lambda x:-x[1]) if v}
# crypto primitives
prim={'AES':r'\bAES\b','RSA':r'\bRSA\b','EC/ECDSA':r'\b(?:ECDSA|ECDH|secp256|P-256)\b','Ed25519/X25519':r'\b(?:Ed25519|EdDSA|X25519|XDH)\b',
 'SHA-2':r'\bSHA-?256\b','SHA-3/Keccak':r'\b(?:SHA-?3|Keccak|SHAKE)\b','HMAC':r'\bHMAC\b','GCM':r'\bGCM\b','ChaCha20':r'\bChaCha20\b',
 'Post-quantum':r'\b(?:Kyber|ML-?KEM|Dilithium|ML-?DSA|SPHINCS|FrodoKEM|NTRU|Falcon|Picnic|XMSS|LMS|McEliece|BIKE|HQC)\b',
 'PBKDF2/Argon2':r'\b(?:PBKDF2|Argon2|scrypt|bcrypt)\b','Tink':r'com\.google\.crypto\.tink'}
M['crypto']={k:len(re.findall(p,blob)) for k,p in prim.items()}
# attack surface
import logging as L
from androguard.core.apk import APK
a=APK(os.path.join('work/xapk/split','vn.com.vng.zalopay.apk'))
mxml=a.get_android_manifest_xml()
A='{http://schemas.android.com/apk/res/android}'
comps={'activity':a.get_activities(),'service':a.get_services(),'receiver':a.get_receivers(),'provider':a.get_providers()}
exp=collections.Counter(); tot=collections.Counter()
for tag,names in comps.items():
    for n in names:
        tot[tag]+=1
        el=[e for e in mxml.iter(tag) if e.get(A+'name')==n]
        if el and el[0].get(A+'exported')=='true': exp[tag]+=1
M['components']=dict(total=dict(tot), exported=dict(exp))
schemes=collections.Counter(); hosts=collections.Counter()
for d in mxml.iter('data'):
    if d.get(A+'scheme'): schemes[d.get(A+'scheme')]+=1
    if d.get(A+'host'): hosts[d.get(A+'host')]+=1
M['deeplinks']=dict(schemes=len(schemes), hosts=len(hosts), top_schemes=schemes.most_common(18), top_hosts=hosts.most_common(12))
# locales + resources
arsc=a.get_android_resources()
try:
    locs=set()
    for loc in arsc.get_locales(): 
        locs.add(loc)
    M['locales']=sorted(locs)
except Exception as e: M['locales']=str(e)
M['permissions']=dict(requested=len(a.get_permissions()), dangerous=[p for p in a.get_permissions() if p.split('.')[-1] in
    ('READ_CONTACTS','CAMERA','RECORD_AUDIO','ACCESS_FINE_LOCATION','ACCESS_COARSE_LOCATION','READ_PHONE_STATE','WRITE_CONTACTS','READ_MEDIA_AUDIO','POST_NOTIFICATIONS','QUERY_ALL_PACKAGES','USE_BIOMETRIC','NFC','WRITE_CALENDAR','READ_CALENDAR','DETECT_SCREEN_CAPTURE')])
# native init arrays / JNI_OnLoad-only libs
LIB='work/xapk/split/arch/lib/armeabi-v7a'
nat={}
for f in sorted(os.listdir(LIB)):
    if not f.endswith('.so'): continue
    p=os.path.join(LIB,f)
    r=subprocess.run(['readelf','-d','-W',p],capture_output=True,text=True).stdout
    needs=re.findall(r'NEEDED\).*?\[(\S+)\]', r)
    init=subprocess.run(['readelf','-S','-W',p],capture_output=True,text=True).stdout
    dyn=subprocess.run(['readelf','--dyn-syms','-W',p],capture_output=True,text=True).stdout
    syms=[l.split()[-1] for l in dyn.splitlines()[3:] if len(l.split())>=8]
    nat[f]=dict(size=os.path.getsize(p), needed=needs, jni_onload='JNI_OnLoad' in syms,
                jni_exports=len([s for s in syms if s.startswith('Java_')]),
                has_init_array='.init_array' in init)
M['native']=nat
M['lib_versions']={'kotlin':re.search(r'kotlin-stdlib/([\d.]+)',blob) and re.search(r'kotlin-stdlib/([\d.]+)',blob).group(1)}
json.dump(M, open('analysis/out/final_metrics.json','w'), indent=1, ensure_ascii=False)
print(json.dumps({k:M[k] for k in ['corpus','libraries','crypto','components','deeplinks','permissions','lib_versions']}, indent=1, ensure_ascii=False)[:4000])
print("\nlocales:", len(M['locales']) if isinstance(M['locales'],list) else M['locales'])
print("\nJNI_OnLoad-only libs (0 Java_ exports):", [k for k,v in nat.items() if v['jni_exports']==0 and v['jni_onload']])
print("init_array present:", [k for k,v in nat.items() if v['has_init_array']])
