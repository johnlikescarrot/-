"""Phase 1b: resources.arsc of the base APK and both locale splits."""
from __future__ import annotations
import os, zipfile
from collections import Counter
from common import apk_paths, dump, sha256, ensure_extracted
from formats import parse_arsc


def main():
    ensure_extracted()
    out = {'schema': 'rea_lite/resources@1', 'tables': {}, 'aggregate': {},
           'limits': ['No literal resource/string values are published; only names, counts, lengths and types.',
                      'Config decoding covers size, locale, density and SDK fields of ResTable_config.']}
    total_entries = 0
    config_hist, type_hist, value_hist, name_set = Counter(), Counter(), Counter(), set()
    for apk in apk_paths():
        label = os.path.basename(apk)
        with zipfile.ZipFile(apk) as z:
            if 'resources.arsc' not in z.namelist():
                continue
            b = z.read('resources.arsc')
        r = parse_arsc(b)
        packs = []
        for p in r['packages']:
            tcs = []
            for tc in p['type_chunks']:
                tcs.append(tc)
                total_entries += tc['defined_entries']
                config_hist[tc['config']['locale'] or 'default'] += tc['defined_entries']
                type_hist[tc['type_name']] += tc['defined_entries']
                for k, v in tc['value_types'].items(): value_hist[k] += v
                for k in tc['key_names_sample']: name_set.add(k)
            packs.append({k: v for k, v in p.items() if k != 'type_chunks'} | {'type_chunks': tcs})
        out['tables'][label] = {
            'size': len(b), 'sha256': sha256(b)[:16], 'parsed': r['ok'], 'errors': r['errors'],
            'package_count': r['package_count'], 'global_pool': r['global_pool'],
            'packages': packs,
            'type_chunk_count': sum(len(p['type_chunks']) for p in r['packages']),
            'type_spec_chunk_count': sum(len(p['type_spec_chunks']) for p in r['packages']),
            'defined_entries': sum(tc['defined_entries'] for p in r['packages'] for tc in p['type_chunks']),
        }
        print(label, 'packages', len(r['packages']), 'type chunks', out['tables'][label]['type_chunk_count'],
              'entries', out['tables'][label]['defined_entries'])
    out['aggregate'] = {
        'total_defined_entries': total_entries,
        'config_locales_by_entries': dict(config_hist.most_common(40)),
        'top_resource_types': dict(type_hist.most_common(50)),
        'value_type_histogram': dict(value_hist.most_common()),
        'distinct_resource_names_in_samples': len(name_set),
    }
    dump('resources.json', out)


if __name__ == '__main__':
    main()
