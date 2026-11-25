import sys,os,re,json,collections
sys.path.insert(0,'analysis/scripts')
from dexparse import Dex
BASE='work/xapk/split/base'
dexes=sorted([f for f in os.listdir(BASE) if re.fullmatch(r'classes\d*\.dex',f)],
             key=lambda x:(0 if x=='classes.dex' else int(re.search(r'(\d+)',x).group(1))))
cls=[]; S=set()
for f in dexes:
    d=Dex(os.path.join(BASE,f)); cls += [(c['name'] or '').lstrip('L').rstrip(';').replace('/','.') for c in d.class_defs()]
    S.update(d.strings())
blob='\n'.join(sorted(S))
libs={'AndroidX':r'^androidx\.','BouncyCastle':r'^org\.bouncycastle','Alibaba ARiver/Griver':r'^com\.alibaba',
 'Google Play services':r'^com\.google\.android\.gms','Firebase':r'^com\.google\.firebase','OkHttp':r'^okhttp3',
 'RxJava':r'^io\.reactivex','JMRTD (e-passport NFC)':r'^org\.jmrtd','Nimbus JOSE+JWT':r'^com\.nimbusds',
 'Alipay+ / Ant SDK':r'^com\.alipay','IAP Connect (Griver)':r'^com\.iap','Visa CBP':r'^com\.visa',
 'VNPay SDK':r'^com\.vnpay','TrueID (vn.ai)':r'^vn\.ai','Facebook SDK/AN':r'^com\.facebook',
 'AppsFlyer':r'^com\.appsflyer','Kotlinx coroutines':r'^kotlinx\.coroutines','Rive':r'^app\.rive',
 'Coil/Glide':r'^(coil|com\.bumptech)','ML Kit':r'^com\.google\.mlkit','Gson':r'^com\.google\.gson',
 'ZaloPay first-party':r'^vn\.com\.vng\.zalopay','Jsoup':r'^org\.jsoup','Room':r'^androidx\.room',
 'Skew (camera doc rect)':r'^com\.wonderkillet','Tink':r'^com\.google\.crypto\.tink'}
lib_counts={k:sum(1 for c in cls if re.search(p,c)) for k,p in libs.items()}
lib_counts={k:v for k,v in sorted(lib_counts.items(), key=lambda x:-x[1]) if v>0}
inv=json.load(open('analysis/out/dex_inventory.json'))
fm=json.load(open('analysis/out/final_metrics.json'))
ds=json.load(open('analysis/out/deep_scan.json'))
sec=json.load(open('analysis/out/secrets.json'))
fi=json.load(open('analysis/out/file_inventory_summary.json'))
tax=json.load(open('analysis/out/taxonomy.json'))
# host categorisation of discovered endpoints
hosts=ds['hosts']
zc=[h for h in hosts if re.search(r'zalopay', h)]
ac=[h for h in hosts if re.search(r'alipay|alibaba|antfin|alipayobjects', h)]
gc=[h for h in hosts if re.search(r'(google|gstatic|firebase|doubleclick|googlesyndication|app-measurement|crashlytics|gvt1)', h)]
tc=[h for h in hosts if re.search(r'(facebook|fbcdn|tiktok|appsflyer|adjust|sentry|amplitude|mxpnl|braze)', h)]
oth=[h for h in hosts if h not in set(zc+ac+gc+tc)]
summary=dict(
  meta=dict(package='vn.com.vng.zalopay', version_name='11.17.0', version_code=1102758, min_sdk=26, target_sdk=36,
            compile_sdk=36, kotlin='2.2.10', gradle='8.14.5', jvm_target=17, build_date_hint='2026-11',
            source='APKPure XAPK (split APK bundle)', xapk_bytes=105608128),
  signing=dict(subject='CN=Vu Manh Hieu', serial='0x153ea9fd', not_before='2016-04-21', not_after='2041-04-15',
               sha256='59:DD:31:FD:F3:60:70:A0:D7:F8:C8:CD:E8:43:FA:C3:71:DF:7F:1A:91:5C:01:36:C9:C9:B5:59:92:E8:CD:AE',
               v1=False, v2=True, v3=True, self_signed=True, key='RSA-2048+ (per cert)'),
  splits=[{'file':'vn.com.vng.zalopay.apk','role':'base','bytes':79359087},
          {'file':'config.armeabi_v7a.apk','role':'native ABI (armeabi-v7a only)','bytes':24828408},
          {'file':'config.en.apk','role':'locale en','bytes':74137},
          {'file':'config.vi.apk','role':'locale vi','bytes':86425},
          {'file':'config.xhdpi.apk','role':'density xhdpi','bytes':1232563}],
  corpus=dict(files=fi['files'], bytes=fi['total'], dex_files=19, dex_bytes=fm['corpus'] and sum(d['file_size'] for d in inv['per_dex']),
              classes=len(cls), methods=inv['total']['methods'], fields=inv['total']['fields'],
              strings=len(S), types=inv['total']['types']+0, protos=inv['total']['protos'],
              native_libs=28, native_bytes=57452516),
  file_kinds=fi['by_kind'], bytes_by_kind=fi['bytes_by_kind'],
  libraries=lib_counts, crypto=fm['crypto'], components=fm['components'],
  permissions=fm['permissions'], deeplinks=fm['deeplinks'],
  endpoints=dict(urls=len(ds['urls']), hosts=len(hosts), api_paths=len(ds['apis']),
                 zalopay=len(zc), alipay=len(ac), google=len(gc), tracker=len(tc), other=len(oth),
                 host_list=hosts[:400], zalopay_hosts=sorted(set(zc))[:120], api_sample=ds['apis'][:60]),
  hardening=dict(stub_app='jwbmqnsll.xvndbxlk extends vn.com.vng.zalopay.AndroidApplication',
                 encrypted_blobs=[{'path':'assets/fgcpkjvqe.dat','bytes':776320,'entropy':8.0},
                                  {'path':'assets/taibmcf.dat','bytes':8307,'entropy':7.976},
                                  {'path':'assets/bshield.dat','bytes':929,'entropy':7.785},
                                  {'path':'assets/trueidlib.so','bytes':392,'entropy':7.501}],
                 shells=['libtaibmcf.so','libfgcpkjvqe.so'],
                 jni_onload_only=[k for k,v in fm['native'].items() if v['jni_exports']==0 and v['jni_onload']],
                 anti_analysis=dict(root_markers=len(ds['root']), frida_markers=len(ds['frida']),
                                    emulator_markers=len(ds['emulator']), debug_markers=len(ds['debug']),
                                    samples=ds['frida'][:10]),
                 reflection=ds['reflection'], dynamic_loading=ds['loadlib']),
  obfuscation=dict(top_packages=len(tax['top_packages']), short_packages=len(tax['short_tops']),
                   short_package_classes=33281,
                   r8_note='Single-letter package roots consistent with R8 full-mode + class repackaging'),
  zp_modules=tax['zp_modules'][:40],
  mobile_stack=dict(react_native_bundle='assets/android.1.zip:main.jsbundle (3.61 MB, 2022-03-08)',
                    griver='com.alibaba.griver/IAP Connect miniapp container',
                    webapp_js=['webapp.js','jsbridge.js','internaljsbridge.js','android_jsbridge.js','workerjs.js']),
  trackers=dict(appsflyer='k5nfUwyL6J6Fe6jvBbPcKG (dev key)', sentry='o529616.ingest.sentry.io/5666380',
                firebase=True, facebook=True, google_ads=True, tiktok_analytics=True),
  security_config=dict(cleartext_permitted=True, trust_anchors='system',
                       user_certs_in_debug_overrides=True, allow_backup=False,
                       network_security_config='res/xml/network_security_config.xml',
                       storage_googleapis_domain_config=True),
)
json.dump(summary, open('analysis/out/summary.json','w'), indent=1, ensure_ascii=False)
print("wrote summary.json", os.path.getsize('analysis/out/summary.json'), "bytes")
print("\nLIBRARIES:"); [print(f"  {v:>5}  {k}") for k,v in lib_counts.items()]
print("\ncorpus:", summary['corpus'])
print("\nendpoints:", {k:v for k,v in summary['endpoints'].items() if k not in ('host_list','zalopay_hosts','api_sample')})
print("\nfile kinds:", fi['by_kind'])
