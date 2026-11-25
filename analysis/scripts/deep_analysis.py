import json, os, zipfile, hashlib, logging, re
from collections import Counter, defaultdict
logging.disable(logging.CRITICAL)

BASE = '/home/user/-'
WORK = '/home/user/-/work'
OUT = '/home/user/-/analysis'
xapk = os.path.join(WORK,'extracted/xapk_contents')
main_apk = os.path.join(xapk,'vn.com.vng.zalopay.apk')

# 1. Decode manifest via androguard
from androguard.core.apk import APK
a = APK(main_apk)
manifest_xml = a.get_android_manifest_axml().get_xml()
with open(OUT+'/manifests/AndroidManifest.decoded.xml','wb') as f:
    f.write(manifest_xml)

# 2. Component export/security analysis
exported = {'activities':[], 'services':[], 'receivers':[], 'providers':[]}
manifest = a.get_android_manifest_xml()
ns = '{http://schemas.android.com/apk/res/android}'
app = manifest.find('application')
if app is not None:
    for tag, key in [('activity','activities'),('service','services'),('receiver','receivers'),('provider','providers')]:
        for comp in app.findall(tag):
            name = comp.get(ns+'name','')
            exp = comp.get(ns+'exported')
            has_intent = len(comp.findall('intent-filter')) > 0
            perm = comp.get(ns+'permission','')
            # exported default: true if has intent-filter on older behavior, but explicit matters
            is_exported = (exp == 'true') or (exp is None and has_intent)
            if is_exported:
                exported[key].append({'name':name,'exported':exp,'has_intent_filter':has_intent,'permission':perm})
with open(OUT+'/security/exported_components.json','w') as f:
    json.dump(exported,f,indent=2,ensure_ascii=False)

# 3. DEX file stats - parse each DEX individually
from androguard.core.dex import DEX
dex_stats = []
all_strings = []
url_pattern = re.compile(rb'https?://[a-zA-Z0-9\-._~:/?#\[\]@!$&\'()*+,;=%]+')
urls = set()
with zipfile.ZipFile(main_apk) as z:
    dex_files = [n for n in z.namelist() if n.endswith('.dex')]
    for dn in sorted(dex_files):
        data = z.read(dn)
        # URL/string scan on raw bytes (cheap)
        for m in url_pattern.finditer(data):
            try:
                u = m.group(0).decode('utf-8', 'ignore').rstrip('\'"\\')
                if len(u) < 300:
                    urls.add(u)
            except:
                pass
        try:
            d = DEX(data)
            classes = list(d.get_classes())
            methods = sum(len(list(c.get_methods())) for c in classes)
            fields = sum(len(list(c.get_fields())) for c in classes)
            strings = len(list(d.get_strings()))
            dex_stats.append({
                'name': dn, 'size': len(data), 'classes': len(classes),
                'methods': methods, 'fields': fields, 'strings': strings
            })
        except Exception as e:
            dex_stats.append({'name': dn, 'size': len(data), 'error': str(e)})
        del data

with open(OUT+'/security/dex_stats.json','w') as f:
    json.dump(dex_stats,f,indent=2,ensure_ascii=False)
with open(OUT+'/security/url_endpoints.json','w') as f:
    json.dump(sorted(urls),f,indent=2,ensure_ascii=False)

# 4. Native libs
native = []
with zipfile.ZipFile(main_apk) as z:
    for i in z.infolist():
        if i.filename.startswith('lib/') and i.filename.endswith('.so'):
            native.append({'path':i.filename,'size':i.file_size})
# also check split
abi_apk = os.path.join(xapk,'config.armeabi_v7a.apk')
with zipfile.ZipFile(abi_apk) as z:
    for i in z.infolist():
        if i.filename.endswith('.so'):
            native.append({'path':'config.armeabi_v7a.apk:'+i.filename,'size':i.file_size})
with open(OUT+'/security/native_libs.json','w') as f:
    json.dump(native,f,indent=2,ensure_ascii=False)

# 5. Signing/cert details
certs = []
for c in a.get_certificates():
    certs.append({
        'sha256': c.sha256_fingerprint.replace(':','').lower(),
        'sha1': c.sha1_fingerprint.replace(':','').lower(),
        'subject': c.subject.human_friendly,
        'issuer': c.issuer.human_friendly,
        'serial': str(c.serial_number),
        'not_before': str(c.not_valid_before),
        'not_after': str(c.not_valid_after),
    })
with open(OUT+'/security/certificates.json','w') as f:
    json.dump(certs,f,indent=2,ensure_ascii=False)

print('deep done', len(dex_stats), 'dex', len(urls), 'urls')
