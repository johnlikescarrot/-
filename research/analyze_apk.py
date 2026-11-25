#!/usr/bin/env python3
"""Reproducible, offline-first static inventory and analysis for the supplied XAPK.

This deliberately does not install, execute, emulate, or make network requests for the app.
Requires Python 3.11+ and androguard 4.1.4 (see requirements.txt).
"""
from __future__ import annotations
import argparse, collections, hashlib, json, os, re, zipfile
from pathlib import Path
from xml.etree import ElementTree as ET

ANDROID = '{http://schemas.android.com/apk/res/android}'
URL_RE = re.compile(r'(?i)\bhttps?://[^\s\"\'<>\\]+')
DOMAIN_RE = re.compile(r'(?i)(?<![\w.-])((?:[a-z0-9](?:[a-z0-9-]{0,61}[a-z0-9])?\.)+[a-z]{2,})(?![\w.-])')

def sha(data: bytes) -> str:
    return hashlib.sha256(data).hexdigest()

def sha_stream(stream) -> tuple[str, int]:
    h, n = hashlib.sha256(), 0
    while True:
        block = stream.read(1024 * 1024)
        if not block: break
        h.update(block); n += len(block)
    return h.hexdigest(), n

def inventory_zip(z: zipfile.ZipFile, prefix: str) -> list[dict]:
    rows=[]
    for info in sorted(z.infolist(), key=lambda x: x.filename.casefold()):
        if info.is_dir(): continue
        with z.open(info) as f: digest, size = sha_stream(f)
        rows.append({'container':prefix,'path':info.filename,'size':size,'compressed_size':info.compress_size,
                     'compression':info.compress_type,'crc32':f'{info.CRC:08x}','sha256':digest})
    return rows

def decode_manifest(apk_bytes: bytes):
    from androguard.core.apk import APK
    # APK(raw=True) accepts the archived APK bytes, avoiding extraction to disk.
    a=APK(apk_bytes, raw=True, skip_analysis=False)
    xml=a.get_android_manifest_xml()
    root=ET.fromstring(ET.tostring(xml, encoding='utf-8')) if not isinstance(xml, (bytes, str)) else ET.fromstring(xml)
    def val(el, key): return el.attrib.get(ANDROID+key)
    app=root.find('application')
    comps={}
    if app is not None:
        for tag in ('activity','activity-alias','service','receiver','provider'):
            comps[tag]=[]
            for el in app.findall(tag):
                row={'name':val(el,'name'),'exported':val(el,'exported'),'permission':val(el,'permission'),
                     'enabled':val(el,'enabled')}
                filters=[]
                for f in el.findall('intent-filter'):
                    filters.append({'actions':[x.attrib.get(ANDROID+'name') for x in f.findall('action')],
                                    'categories':[x.attrib.get(ANDROID+'name') for x in f.findall('category')],
                                    'data':[{k:v for k,v in x.attrib.items() if k.endswith(('}scheme','}host','}mimeType'))} for x in f.findall('data')]})
                if filters: row['intent_filters']=filters
                comps[tag].append(row)
    permissions=[]
    for tag in ('uses-permission','uses-permission-sdk-23','uses-permission-sdk-m'):
        for e in root.findall(tag): permissions.append({'name':val(e,'name'),'maxSdkVersion':val(e,'maxSdkVersion')})
    sdk=root.find('uses-sdk')
    return a, {'package':root.attrib.get('package'),'version_name':val(root,'versionName'),
       'version_code':val(root,'versionCode'),'compile_sdk':val(root,'compileSdkVersion'),
       'min_sdk':val(sdk,'minSdkVersion') if sdk is not None else None,
       'target_sdk':val(sdk,'targetSdkVersion') if sdk is not None else None,
       'permissions':permissions,'components':comps,
       'application': ({k:v for k,v in app.attrib.items()} if app is not None else {})}

def analyze_dex(blobs):
    from androguard.core.dex import DEX
    classes=[]; methods=0; fields=0; strings=[]; dex_stats=[]
    for i,blob in enumerate(blobs,1):
        d=DEX(blob)
        cs=d.get_classes(); ms=d.get_methods(); fs=d.get_fields(); ss=d.get_strings()
        dex_stats.append({'index':i,'bytes':len(blob),'classes':len(cs),'methods':len(ms),'fields':len(fs),'strings':len(ss)})
        classes.extend(c.get_name() for c in cs); methods+=len(ms); fields+=len(fs); strings.extend(ss)
    # Preserve raw candidates, but intentionally make no behavioral assertion from strings alone.
    urls=sorted(set(u.rstrip('.,);]') for s in strings for u in URL_RE.findall(s)))
    # Hostnames are derived from URL literals (stronger signal than arbitrary dotted DEX tokens).
    from urllib.parse import urlsplit
    hosts=collections.Counter()
    for u in urls:
        try:
            host=urlsplit(u).hostname
            if host and '.' in host and not any(c in host for c in ('%','*')):
                hosts[host.lower().rstrip('.')]+=1
        except ValueError:
            pass
    dotted_tokens=collections.Counter()
    for s in strings:
        if len(s) < 253 and re.fullmatch(r'[A-Za-z0-9-]+(?:\\.[A-Za-z0-9-]+)+',s):
            dotted_tokens[s.lower()]+=1
    indicators={
      'tls_and_certificate_validation':('javax.net.ssl','sslcontext','trustmanager','hostnameverifier','certificatepinning','certificate_pinner'),
      'webview_and_javascript_bridge':('webview','addjavascriptinterface'),
      'dynamic_code_loading':('runtime.exec','dexclassloader','pathclassloader'),
      'biometrics':('biometricprompt','use_biometric','use_fingerprint'),
      'camera_and_media':('camera','record_audio','mediarecorder'),
      'location_and_telephony':('locationmanager','telephonymanager','access_fine_location','read_phone_state'),
      'nfc':('nfcadapter','nfc'),
      'clipboard':('clipboardmanager','clipboard'),
      'accessibility':('accessibilityservice','accessibilitynodeinfo'),
      'root_and_instrumentation_checks':('rootbeer','frida','xposed','debugger'),
      'android_permission_references':('android.permission.',),
    }
    indicator_counts={k:sum(1 for s in strings if any(q in s.lower() for q in patterns)) for k,patterns in indicators.items()}
    return {'dex_files':dex_stats,'class_count':len(classes),'method_count':methods,'field_count':fields,
            'string_count':len(strings),'package_prefixes':dict(collections.Counter('.'.join(x.split('/')[:4]) for x in classes).most_common(120)),
            'url_literals_redacted':True, 'url_literal_count':len(urls),
            'domain_candidates':[{'host':k,'string_occurrences':v} for k,v in hosts.most_common(1000)],
            'domain_candidate_count':len(hosts),
            'dotted_string_token_count':len(dotted_tokens),
            'api_indicator_counts':indicator_counts,
            'api_indicator_note':'Substring matches in static DEX strings only; neither calls nor runtime behavior are established.'}

def main():
    import logging
    logging.disable(logging.CRITICAL)
    try:
        from loguru import logger
        logger.remove()
    except ImportError:
        pass
    ap=argparse.ArgumentParser(); ap.add_argument('archive',type=Path); ap.add_argument('--out',type=Path,default=Path('research/output'))
    args=ap.parse_args(); args.out.mkdir(parents=True,exist_ok=True)
    with args.archive.open('rb') as f: outer_hash, outer_size=sha_stream(f)
    inventory=[]; apk_bytes={}; xapk_meta=None
    with zipfile.ZipFile(args.archive) as outer:
        for oi in outer.infolist():
            with outer.open(oi) as f: raw=f.read()
            inventory.append({'container':'outer-zip','path':oi.filename,'size':len(raw),'compressed_size':oi.compress_size,
                              'compression':oi.compress_type,'crc32':f'{oi.CRC:08x}','sha256':sha(raw)})
            if oi.filename.lower().endswith('.xapk'):
                import io
                with zipfile.ZipFile(io.BytesIO(raw)) as x:
                    inventory.extend(inventory_zip(x,oi.filename))
                    xapk_meta=json.loads(x.read('manifest.json'))
                    for n in x.namelist():
                        if n.lower().endswith('.apk'):
                            apk_bytes[n]=x.read(n)
                            inventory.extend(inventory_zip(zipfile.ZipFile(__import__('io').BytesIO(apk_bytes[n])),n))
    analyses=[]
    for name, blob in apk_bytes.items():
        try:
            a,m=decode_manifest(blob)
            files=a.get_files()
            types=collections.Counter((Path(n).suffix.lower() or '[no extension]') for n in files)
            native=[]
            with zipfile.ZipFile(__import__('io').BytesIO(blob)) as z:
                for zi in z.infolist():
                    if zi.filename.startswith('lib/') and zi.filename.endswith('.so'):
                        b=z.read(zi); arch=zi.filename.split('/')[1]
                        machine=None
                        if b[:4]==b'\x7fELF' and len(b)>20:
                            em=int.from_bytes(b[18:20],'little' if b[5]==1 else 'big')
                            machine={40:'ARM',183:'AArch64',3:'x86',62:'x86_64'}.get(em,f'EM_{em}')
                        native.append({'path':zi.filename,'size':len(b),'sha256':sha(b),'elf_machine':machine,'abi_dir':arch})
            with zipfile.ZipFile(__import__('io').BytesIO(blob)) as z:
                dex_entries=[(n,z.read(n)) for n in sorted((n for n in z.namelist() if n.lower().endswith('.dex')),key=lambda n:(n!='classes.dex',n))]
            dex_entries=[(n,b) for n,b in dex_entries if b[:4]==b'dex\n']
            dex=analyze_dex([b for _,b in dex_entries]) if dex_entries else {'dex_files':[],'class_count':0,'method_count':0,'field_count':0,'string_count':0,'package_prefixes':{},'url_literals':[],'url_literal_count':0,'domain_candidates':[],'domain_candidate_count':0,'api_indicator_counts':{},'api_indicator_note':'No DEX entries detected.'}
            for ds,(dn,_) in zip(dex['dex_files'],dex_entries): ds['entry_name']=dn
            certs=[]
            for cert in a.get_certificates():
                der=cert.dump(); certs.append({'sha256':sha(der),'sha1':hashlib.sha1(der).hexdigest(),'subject':str(cert.subject.native)})
            analyses.append({'apk':name,'apk_size':len(blob),'apk_sha256':sha(blob),'manifest':m,
              'entry_count':len(files),'extension_counts':dict(types.most_common()),'native_libraries':native,
              'dex':dex,'signing_certificates_seen_by_androguard':certs,
              'signing_scheme_flags':{'v1':a.is_signed_v1(),'v2':a.is_signed_v2(),'v3':a.is_signed_v3(),'v3.1':a.is_signed_v31()}})
        except Exception as e:
            analyses.append({'apk':name,'apk_size':len(blob),'apk_sha256':sha(blob),'parse_error':repr(e)})
    report={'input':{'filename':args.archive.name,'size':outer_size,'sha256':outer_hash},'xapk_manifest':xapk_meta,
            'inventory_count':len(inventory),'inventory':inventory,'apk_analyses':analyses,
            'methodology':{'mode':'static only, offline','execution':'none','network_requests':'none',
             'tools':'Python zipfile/hashlib + Androguard 4.1.4 (DEX / Android binary manifest / certificate metadata)',
             'limitations':['No dynamic execution, emulator, traffic capture, backend access, or source-code confirmation.',
             'All files are inventoried and hashed; DEX tables/strings are parsed, but complete Java/Kotlin decompilation, resource-table/string resolution, and native instruction/symbol analysis were not performed.',
             'DEX strings, requested permissions and certificate metadata are indicators, not evidence of runtime use or safety.',
             'URL paths/query strings are deliberately not retained in the shareable JSON; only counts and candidate hostnames are recorded.',
             'A third-party XAPK mirror is not an authenticated official distribution channel; signer identity must be checked against a trusted official reference before attribution.',
             'Resource values may be localized or resolved dynamically; only the packaged split set was examined.']}}
    out=args.out/'findings.json'; out.write_text(json.dumps(report,ensure_ascii=False,indent=2)+'\n')
    inv=args.out/'file_inventory.csv'
    import csv
    with inv.open('w',newline='',encoding='utf-8') as f:
        w=csv.DictWriter(f,fieldnames=['container','path','size','compressed_size','compression','crc32','sha256'],lineterminator='\n'); w.writeheader(); w.writerows(inventory)
    print(json.dumps({'inventory_count':len(inventory),'apk_summaries':[{'apk':x['apk'],'entries':x.get('entry_count'),'dex':x.get('dex',{}).get('dex_files'),'components':{k:len(v) for k,v in x.get('manifest',{}).get('components',{}).items()},'permissions':len(x.get('manifest',{}).get('permissions',[])),'certs':x.get('signing_certificates_seen_by_androguard'),'urls':x.get('dex',{}).get('url_literal_count'),'domains':x.get('dex',{}).get('domain_candidate_count'),'native':len(x.get('native_libraries',[]))} for x in analyses]},indent=2))
if __name__=='__main__': main()
