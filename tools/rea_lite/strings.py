"""Phase 4b: corpus-wide string statistics (aggregate only, redaction by construction)."""
from __future__ import annotations
import re, json, zipfile, os
from collections import Counter
from common import dump, load, ensure_extracted, apk_paths

URL_RE = re.compile(r'https?://[A-Za-z0-9._~:/?#\[\]@!$&\'()*+,;=%-]+', re.I)
B64_RE = re.compile(r'(?<![A-Za-z0-9+/])[A-Za-z0-9+/]{40,}={0,2}(?![A-Za-z0-9+/])')


def main():
    ensure_extracted()
    inv, dex, assets = load('inventory.json'), load('dex.json'), load('assets.json')
    high = [e for c in inv['containers'] for e in c['entries']
            if e['entropy'] >= 7.5 and e['size'] > 8192]
    by_kind = Counter(e['kind'] for c in inv['containers'] for e in c['entries'])
    # text corpus over non-secret textual members
    text_ext = ('.json', '.js', '.html', '.xml', '.txt', '.md', '.proto', '.properties', '.version', '.param')
    text_members, text_bytes, lines, url_strings, b64_candidates, vi_files = 0, 0, 0, 0, 0, 0
    for apk in apk_paths():
        z = zipfile.ZipFile(apk)
        for i in z.infolist():
            if not i.filename.lower().endswith(text_ext): continue
            b = z.read(i)
            text_members += 1; text_bytes += len(b)
            lines += b.count(b'\n')
            s = b.decode('utf-8', 'replace')
            url_strings += len(URL_RE.findall(s))
            b64_candidates += len(B64_RE.findall(s[:200000]))
            if re.search(r'[ăâđêôơưĂÂĐÊÔƠƯ]', s): vi_files += 1
    agg = dex['aggregate']
    out = {
        'schema': 'rea_lite/strings@1',
        'dex_string_table': {
            'total_string_ids': agg['counts']['strings'],
            'secret_like_strings_count_only': sum(p['secret_like_string_count_not_published'] for p in dex['per_file']),
            'url_string_hits_total': sum(p['url_string_hits'] for p in dex['per_file']),
            'domains_top': agg['domains_top'],
            'length_histogram_sample': dex['per_file'][0]['string_length_histogram'],
        },
        'text_corpus': {
            'textual_members': text_members, 'textual_bytes': text_bytes,
            'line_count': lines, 'url_like_strings_in_text_members': url_strings,
            'opaque_base64_like_token_candidates': b64_candidates,
            'files_with_vietnamese_diacritics': vi_files,
        },
        'high_entropy_members': {
            'count': len(high),
            'bytes': sum(e['size'] for e in high),
            'top_by_size': [{'container': '', 'path': e['path'], 'size': e['size'],
                             'entropy': e['entropy'], 'sha256_short': e['sha256_short']}
                            for e in sorted(high, key=lambda x: -x['size'])[:20]],
        },
        'member_kinds': dict(by_kind.most_common(30)),
        'redaction': {
            'published': 'counts, lengths, hashes, magic, key names, last-two-label domains from DEX strings',
            'not_published': 'literal keys/tokens/credentials, full URLs with paths/queries, config values, certificate bytes, personal data',
            'policy': 'docs/ETHICS.md',
        },
        'limits': ['URL-like strings in text assets are COUNTED, not listed.',
                   'High entropy is a heuristic hint of compression/encryption, not proof; no decryption attempted.'],
    }
    dump('strings.json', out)
    print('high entropy members', len(high), 'text bytes', text_bytes, 'url strings', url_strings)


if __name__ == '__main__':
    main()
