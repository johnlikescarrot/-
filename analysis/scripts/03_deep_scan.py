import sys, os, json, re, collections
sys.path.insert(0,'analysis/scripts')
from dexparse import Dex

BASE='work/xapk/split/base'
dexes=sorted([f for f in os.listdir(BASE) if re.fullmatch(r'classes\d*\.dex', f)],
             key=lambda x:(0 if x=='classes.dex' else int(re.search(r'(\d+)',x).group(1))))
S=set()
for f in dexes:
    d=Dex(os.path.join(BASE,f)); S.update(d.strings())
# also pull literal strings from resources.arsc + native libs later
print("unique strings:", len(S))

def grab(rx, flags=re.I, limit=400, fullmatch=False):
    r=re.compile(rx, flags)
    out=sorted({m.group(0) if not fullmatch else m.group(1) for s in S if (m:=r.search(s))})
    return out[:limit]

res={}
res['urls']=grab(r'https?://[A-Za-z0-9\-._~:/?#\[\]@!$&\'()*+,;=%]+')
res['hosts']=grab(r'\b(?:[a-z0-9](?:[a-z0-9\-]{0,61}[a-z0-9])?\.)+(?:com|vn|net|io|co|org|dev|app|sg|me|ai|cloud|tech|info)\b', limit=1200)
res['apis']=grab(r'^/?(?:v\d{1,2}|api|gw|gateway|openapi)/[A-Za-z0-9_\-/{}.:]{2,80}$', limit=800)
res['google_keys']=grab(r'AIza[0-9A-Za-z\-_]{35}')
res['jwts']=grab(r'eyJ[A-Za-z0-9_\-]{10,}\.[A-Za-z0-9_\-]{10,}\.[A-Za-z0-9_\-]{5,}')
res['libs']=grab(r'^(?:lib)?[a-z0-9_\-]{2,24}$', limit=0)  # placeholder
res['loadlib']=grab(r'\b(?:taibmcf|fgcpkjvqe|a97d|ohxbee|lytcuhe|rcmefqonnx|sqlcipher|security_lite|trueidlib|rive-android|picodet_ncnn|barhopper_v3|VFaceLib)\b', limit=200)
res['crypto']=sorted({w for w in ['AES','GCM','CBC','ECB','CFB','OFB','CTR','XTS','RSA','ECIES','Ed25519','X25519','EdDSA','P-256','secp256r1','secp256k1','PBKDF2','HKDF','Argon2','scrypt','bcrypt','SHA-256','SHA-1','HMAC','Keccak','BLAKE2','ChaCha20','Poly1305','ECIES','Kyber','Dilithium','SPHINCS','FrodoKEM','X25519'] if any(w.lower() in s.lower() for s in S)})
res['pinning']=grab(r'(?:certificate|ssl|tls|public[_-]?key)[._-]?(?:pin|pinning|hash|sha256|spki)|PinManager|X509TrustManager|TrustManagerImpl|CertificatePinner|NetworkSecurityConfig', limit=200)
res['root']=grab(r'(?:^|[._/])(?:root|su|supersu|magisk|busybox)(?:$|[._/])|isRooted|RootBeer|detectRoot|/system/xbin/su|/system/bin/su|ro\.debuggable|ro\.secure|test-keys', limit=200)
res['frida']=grab(r'frida|gum-js-loop|gmain|xposed|lsposed|substrate|magisk|ptrace|TracerPid|/proc/self/maps|/proc/self/status', limit=200)
res['emulator']=grab(r'goldfish|ranchu|generic_x86|sdk_gphone|bluestacks|nox|genymotion|qemu|vbox|emulator|isEmulator|Build\.FINGERPRINT', limit=200)
res['debug']=grab(r'isDebuggerConnected|Debug\.isDebuggerConnected|waitingForDebugger|JDWP|jdwp|android:debuggable|isDebuggable', limit=200)
res['reflection']=grab(r'java/lang/reflect/(?:Method|Field|Constructor)|setAccessible|dalvik/system/DexClassLoader|dalvik/system/InMemoryDexClassLoader|dalvik/system/PathClassLoader|dalvik/system/BaseDexClassLoader|DexFile|LoadedApk|hiddenApi', limit=200)
res['shell']=grab(r'(?:^|[._/])(?:shell|stub|proxy|loader|packer|protect)(?:$|[._/])|ClassLoader|attachBaseContext|ProxyApplication|StubApp|createPackageContext', limit=200)
res['webview']=grab(r'addJavascriptInterface|@JavascriptInterface|shouldOverrideUrlLoading|WebViewClient|evaluateJavascript|loadUrl|WebChromeClient|onJsPrompt|onJsAlert|postWebMessage', limit=200)
res['sdk']=grab(r'^(?:com|io|org|net)\.(?:google|facebook|appsflyer|adjust|amplitude|mixpanel|segment|braze|onesignal|sentry|bugsnag|newrelic|datadog|crashlytics|ironsource|unity3d|bytedance|pangle|mintegral|vungle|chartboost|adcolony|applovin|mopub|inmobi|startapp|tapjoy|sqflite|realm|hive|flutter|alibaba|alipay|tencent|vng|zalopay|visa|mastercard|nimbusds|bouncycastle|jmrtd|sqlcipher)[a-zA-Z0-9_.]*$', limit=500)
res['persist']=grab(r'SharedPreferences|EncryptedSharedPreferences|KeyStore|AndroidKeyStore|getSharedPreferences|MODE_PRIVATE|SQLiteDatabase|RoomDatabase|sqlcipher|DataStore', limit=40)
res['paths']=grab(r'/data/(?:data|user/\d+|app)/[A-Za-z0-9_./\-]+', limit=200)
res['assets']=grab(r'^[A-Za-z0-9_\-]+\.(?:dat|json|zip|dex|so|bin|param|pem|js)$', limit=300)

# native lib names actually loaded
res['libs']=grab(r'^[a-zA-Z][a-zA-Z0-9_\-]{2,30}$', limit=0)
json.dump(res, open('analysis/out/deep_scan.json','w'), indent=1, ensure_ascii=False)
for k,v in res.items():
    print(f"\n### {k}: {len(v) if isinstance(v,list) else v}")
print("\n--- sample urls ---")
for u in res['urls'][:14]: print("  ", u)
print("\n--- host count:", len(res['hosts']))
