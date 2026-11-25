#!/usr/bin/env python3
"""Stage 4: ELF/native library triage with binutils."""
import os, json, re, subprocess, hashlib

OUT = '/tmp/zp/reports'
LIBS = '/tmp/zp/libs'
report = []
for fn in sorted(os.listdir(LIBS)):
    p = os.path.join(LIBS, fn)
    hdr = subprocess.run(['readelf', '-h', p], capture_output=True, text=True).stdout
    dyn = subprocess.run(['readelf', '-d', p], capture_output=True, text=True).stdout
    sym = subprocess.run(['readelf', '--dyn-syms', '-W', p], capture_output=True, text=True).stdout
    prog = subprocess.run(['readelf', '-l', '-W', p], capture_output=True, text=True).stdout
    def grab(pattern, text=hdr):
        m = re.search(pattern, text)
        return m.group(1).strip() if m else None
    needed = re.findall(r'\(NEEDED\)\s+Shared library: \[([^\]]+)\]', dyn)
    soname = re.findall(r'\(SONAME\)\s+Library soname: \[([^\]]+)\]', dyn)
    exported = [m.group(2) for m in re.finditer(r'\d+:\s+[0-9a-f]+\s+\d+\s+FUNC\s+(?:DEFAULT|PROTECTED)\s+\d+\s+(\S+)\s+(\S+)', sym)]
    java_syms = [x for x in exported if x.startswith('Java_') or 'JNI_OnLoad' in x or 'JNI_OnUnload' in x]
    flags = {'PIE': 'DYN' in hdr, 'BIND_NOW': '(BIND_NOW)' in dyn or 'FLAGS_1' in dyn and 'NOW' in dyn,
             'RELRO': 'GNU_RELRO' in prog, 'STACK_EXEC': 'Stack' in prog and 'E' in re.search(r'STACK.*', prog).group(0) if re.search(r'STACK.*', prog) else False}
    report.append({
        'file': fn, 'bytes': os.path.getsize(p),
        'sha256': hashlib.sha256(open(p, 'rb').read()).hexdigest(),
        'class': grab(r'Class:\s+(\S+)'), 'machine': grab(r'Machine:\s+(.+)'),
        'soname': soname, 'needed': needed,
        'dynamic_symbol_count': len(re.findall(r'\d+:\s+[0-9a-f]+\s+\d+\s+FUNC', sym)),
        'java_or_jni_exports': java_syms[:40], 'java_or_jni_count': len(java_syms),
        'flags': flags,
        'build_id': grab(r'Build ID:\s+(\S+)'),
    })
json.dump(report, open(os.path.join(OUT, 'native-report.json'), 'w'), indent=2, ensure_ascii=False)
print('stage4 done', len(report))
for r in report:
    print(r['file'], r['machine'][:22], r['dynamic_symbol_count'], 'jni', r['java_or_jni_count'],
          'soname', r['soname'][:1], 'needed', r['needed'][:4])
