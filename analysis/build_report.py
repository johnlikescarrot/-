#!/usr/bin/env python3
import json, os, datetime
from pathlib import Path
OUT = Path('/home/user/-/analysis')
summary = json.load(open(OUT/'summary.json'))
inv = json.load(open(OUT/'file_inventory.json'))
inner = json.load(open(OUT/'inner_file_inventory.json'))
deep = json.load(open(OUT/'deep_analysis.json')) if (OUT/'deep_analysis.json').exists() else {}

lines = []
lines.append('# Báo cáo Reverse Engineering Toàn Diện')
lines.append('')
lines.append(f'- **Đối tượng**: `Zalopay-Thanh+toán+&+Tài+chính_11.17.0_APKPure.zip`')
lines.append(f'- **Ngày phân tích**: {datetime.date.today().isoformat()}')
lines.append(f'- **Branch**: `arena/f26fab9d-repo`')
lines.append('')
lines.append('## 1. Tổng quan gói')
lines.append('')
lines.append(f'- Tổng file ngoài (trong XAPK): **{summary["total_files"]}**')
lines.append(f'- Tổng file bên trong các APK: **{summary["inner_file_count"]}**')
lines.append(f'- Tổng dung lượng ngoài: **{summary["total_size"]:,} bytes**')
lines.append('')
lines.append('### File ngoài (XAPK)')
lines.append('| Path | Size | SHA256 | Type |')
lines.append('|---|---:|---|---|')
for r in inv:
    lines.append(f"| `{r['path']}` | {r['size']:,} | `{r['sha256'][:16]}…` | {r['type']} |")
lines.append('')
lines.append('## 2. Phân loại file bên trong APK')
lines.append('| Loại | Số lượng |')
lines.append('|---|---:|')
from collections import Counter
by_type = Counter([r.get('type','?') for r in inner])
for k,v in by_type.most_common():
    lines.append(f'| {k} | {v} |')
lines.append('')
lines.append('## 3. Phân tích sâu APK chính (base)')
if deep:
    lines.append(f"- Package: `{deep.get('package')}`")
    lines.append(f"- App name: **{deep.get('app_name')}**")
    lines.append(f"- Version: **{deep.get('version_name')}** ({deep.get('version_code')})")
    lines.append(f"- SDK: min {deep.get('min_sdk')} / target {deep.get('target_sdk')} / max {deep.get('max_sdk')}")
    lines.append(f"- Main activity: `{deep.get('main_activity')}`")
    ds = deep.get('dex_stats',{})
    lines.append(f"- DEX stats: {ds}")
    lines.append('')
    lines.append('### Permissions')
    for p in deep.get('permissions',[]):
        lines.append(f'- `{p}`')
    lines.append('')
    lines.append('### Components')
    lines.append(f"- Activities: {len(deep.get('activities',[]))}")
    lines.append(f"- Services: {len(deep.get('services',[]))}")
    lines.append(f"- Receivers: {len(deep.get('receivers',[]))}")
    lines.append(f"- Providers: {len(deep.get('providers',[]))}")
    lines.append('')
    lines.append('### Certificates')
    for c in deep.get('certificates',[]):
        lines.append(f"- Subject: `{c.get('subject')}`")
        lines.append(f"  - Issuer: `{c.get('issuer')}`")
        lines.append(f"  - SHA256: `{c.get('sha256_fingerprint')}`")
        lines.append(f"  - Valid: {c.get('not_before')} → {c.get('not_after')}")
    lines.append('')
    lines.append('### Top packages')
    lines.append('| Package | Classes |')
    lines.append('|---|---:|')
    for pkg,cnt in deep.get('top_packages',[])[:40]:
        lines.append(f'| `{pkg}` | {cnt} |')
    lines.append('')
    lines.append('### URLs (sample)')
    for u in deep.get('urls',[])[:60]:
        lines.append(f'- `{u}`')
    lines.append('')
    lines.append('### Native libs')
    for n in deep.get('native_libs',[])[:60]:
        lines.append(f'- `{n}`')
    lines.append('')
    lines.append('### Secret/string hints (sample)')
    lines.append(f"Total hints: {deep.get('secret_string_hints_count',0)}")
    for s in deep.get('secret_string_hints_sample',[])[:40]:
        lines.append(f'- `{s}`')
else:
    lines.append('_Deep analysis pending._')

lines.append('')
lines.append('## 4. Ghi chú phương pháp')
lines.append('- Inventory toàn bộ file ngoài/ trong container bằng hash SHA256/MD5, kích thước, loại.')
lines.append('- Phân tích metadata APK bằng Androguard (manifest, permissions, components, cert).')
lines.append('- Trích xuất thống kê DEX (classes/methods/fields/strings), phân bố package, URL/IP, dấu hiệu secret.')
lines.append('- Phân tích split APKs (abi, ngôn ngữ, mật độ).')
lines.append('- Không giải mã/patch mã độc; mục đích nghiên cứu phòng thủ và tài liệu hóa.')
lines.append('')
(OUT/'REVERSE_ENGINEERING_REPORT.md').write_text('\n'.join(lines), encoding='utf-8')
print('wrote', len(lines))
