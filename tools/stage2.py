#!/usr/bin/env python3
"""Stage 2: manifest attack surface, network policy, asset taxonomy, resources."""
import os, json, zipfile, collections, re, xml.etree.ElementTree as ET
from androguard.core.apk import APK
from androguard.core.axml import AXMLPrinter

ROOT = '/tmp/zp/out'
OUT = '/tmp/zp/reports'
BASE = os.path.join(ROOT, 'vn.com.vng.zalopay.apk')

meta = json.load(open(os.path.join(OUT, 'apk-metadata.json')))
base = next(a for a in meta if a['file'].startswith('vn.com'))
AND = '{http://schemas.android.com/apk/res/android}'
root = ET.fromstring(base['manifest_xml'])
app = root.find('application')

def clean(a):
    return a.replace(AND, '')

findings = {
    'application': {clean(k): v for k, v in app.attrib.items()},
    'exported': {},
    'deeplinks': [],
    'intent_filters': {},
}
for tag in ('activity', 'activity-alias', 'service', 'receiver', 'provider'):
    items = app.findall(tag)
    findings['exported'][tag] = [i.get(AND + 'name') for i in items if i.get(AND + 'exported') == 'true']
    findings['intent_filters'][tag] = sum(1 for i in items if i.find('intent-filter') is not None)
    for i in items:
        for filt in i.findall('intent-filter'):
            datas = []
            for dat in filt.findall('data'):
                datas.append({clean(k): v for k, v in dat.attrib.items()})
            if datas:
                findings['deeplinks'].append({'component': i.get(AND + 'name'), 'tag': tag,
                                              'actions': [a.get(AND + 'name') for a in filt.findall('action')],
                                              'categories': [c.get(AND + 'name') for c in filt.findall('category')],
                                              'data': datas})
json.dump(findings, open(os.path.join(OUT, 'manifest-findings.json'), 'w'), indent=2, ensure_ascii=False)

apk = APK(BASE)
# Network security config: find candidate resource entries
candidates = [n for n in apk.get_files() if 'network_security' in n.lower() or n.startswith('res/xml/')]
nsc = {'candidates': candidates, 'parsed': {}}
for n in candidates[:12]:
    try:
        raw = apk.get_file(n)
        nsc['parsed'][n] = AXMLPrinter(raw).get_xml().decode('utf-8', 'replace')
    except Exception as e:
        nsc['parsed'][n] = 'ERR ' + repr(e)
json.dump(nsc, open(os.path.join(OUT, 'network-security.json'), 'w'), indent=2, ensure_ascii=False)

# Asset taxonomy across base APK
with zipfile.ZipFile(BASE) as z:
    names = z.namelist()
    assets = [n for n in names if n.startswith('assets/')]
    tax = collections.Counter(os.path.splitext(n)[1].lower() or '(none)' for n in assets)
    interesting = [n for n in assets if os.path.splitext(n)[1].lower() in
                   ('.json', '.js', '.txt', '.properties', '.version', '.proto', '.binarypb', '.tflite', '.xml')]
    json.dump({'asset_count': len(assets), 'extensions': tax.most_common(), 'interesting': interesting},
              open(os.path.join(OUT, 'asset-taxonomy.json'), 'w'), indent=2, ensure_ascii=False)
    # Extract a small text corpus of configuration-like assets for evidence (no secrets printed here)
    samples = {}
    for n in interesting:
        if os.path.getsize if False else False:
            pass
        try:
            data = z.read(n)
            if len(data) > 60000:
                continue
            text = data.decode('utf-8', 'replace')
            samples[n] = text[:8000]
        except Exception:
            pass
    json.dump(samples, open(os.path.join(OUT, 'asset-samples.json'), 'w'), indent=2, ensure_ascii=False)

# Resources: string values that look like endpoints / configuration keys (redaction applied later)
res = apk.get_android_resources()
strings = {}
try:
    for pkg in res.get_packages_names():
        for locale, _ in res.get_locales(pkg):
            vals = res.get_strings_resources(pkg, locale)
            strings[pkg + '@' + str(locale)] = vals[:200] if isinstance(vals, list) else str(vals)[:20000]
except Exception as e:
    strings['error'] = repr(e)
json.dump(strings, open(os.path.join(OUT, 'resource-strings.json'), 'w'), indent=2, ensure_ascii=False)
print('stage2 done; deeplinks', len(findings['deeplinks']), 'assets', len(assets), 'nsc candidates', len(candidates))
