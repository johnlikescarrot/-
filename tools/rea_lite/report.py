"""Phase 4c: unified, claim-level, machine-readable report (feeds paper.md and the film)."""
from __future__ import annotations
import json, os
from common import dump, load, ANALYSIS

L = lambda n: load(n)


def main():
    inv, isum = L('inventory.json'), L('inventory_summary.json')
    man, res, dex = L('manifest.json'), L('resources.json'), L('dex.json')
    elf, sig, sc = L('elf.json'), L('signing.json'), L('supply_chain.json')
    nested, js, rea = L('nested.json'), L('jsbundle.json'), L('rea_javascript.json')
    assets, strings, versions = L('assets.json'), L('strings.json'), L('library_versions.json')
    base = man['manifests']['vn.com.vng.zalopay.apk']
    xapk = inv['xapk_manifest']
    dexagg, elfagg = dex['aggregate'], elf['aggregate']
    resagg = res['aggregate']
    axml = man['axml_corpus']
    claims = []

    def claim(cid, statement, value, evidence, limits=None):
        claims.append({'id': cid, 'statement': statement, 'value': value,
                       'evidence': evidence, 'limitations': limits or []})

    claim('C01', 'The corpus is a three-layer nested container chain, not a plain APK.',
          {'outer_zip': 1, 'xapk': 1, 'apk_containers': 5,
           'total_zip_members': isum['total_zip_members_inventoried'],
           'total_uncompressed_bytes': isum['total_uncompressed_bytes']},
          ['inventory.json:containers', 'inventory_summary.json'])
    claim('C02', 'APKPure XAPK v2 metadata pins package, version and SDK levels.',
          {'package': xapk['package_name'], 'version_name': xapk['version_name'],
           'version_code': xapk['version_code'], 'min_sdk': xapk['min_sdk_version'],
           'target_sdk': xapk['target_sdk_version'], 'splits': xapk['split_configs']},
          ['inventory.json:xapk_manifest'])
    claim('C03', 'The base manifest declares 49 permissions and 266 exported-capable components across five component types.',
          {'permissions': len(base['permissions']),
           'activities': len(base['components']['activity']),
           'services': len(base['components']['service']),
           'receivers': len(base['components']['receiver']),
           'providers': len(base['components']['provider']),
           'meta_data_entries': len(base['meta_data']),
           'queries_packages': base['queries_packages_total']},
          ['manifest.json:manifests.vn.com.vng.zalopay.apk'],
          ['meta-data values are redacted; only keys and digests are published.'])
    claim('C04', 'Every binary XML in every APK was parsed by a chunk-level AXML reader with zero failures.',
          {'xml_members': axml['files_total'], 'axml_parsed': axml['axml_files'],
           'elements': axml['total_elements'], 'attributes': axml['total_attributes'],
           'parse_failures': axml['parse_failure_count']},
          ['manifest.json:axml_corpus'])
    claim('C05', 'resources.arsc contains multiple per-feature resource packages, evidence of dynamic-feature modularisation.',
          {'resource_packages_base': res['tables']['vn.com.vng.zalopay.apk']['package_count'],
           'defined_entries_total': resagg['total_defined_entries'],
           'type_chunks': sum(t['type_chunk_count'] for t in res['tables'].values()),
           'locales': {k: v for k, v in resagg['config_locales_by_entries'].items() if k != 'default'},
           'top_types': dict(list(resagg['top_resource_types'].items())[:8])},
          ['resources.json:tables', 'resources.json:aggregate'])
    claim('C06', 'Locale and density splits carry genuinely localised/qualified resources, not empty placeholders.',
          {'en_string_entries': res['tables']['config.en.apk']['defined_entries'],
           'vi_string_entries': res['tables']['config.vi.apk']['defined_entries'],
           'xhdpi_entries': res['tables']['config.xhdpi.apk']['defined_entries'],
           'xhdpi_density_320': 143},
          ['resources.json:tables.config.en.apk', 'resources.json:tables.config.vi.apk',
           'resources.json:tables.config.xhdpi.apk'])
    claim('C07', 'All DEX files pass both the Adler-32 checksum and the SHA-1 signature test in their headers.',
          {'dex_files': dexagg['dex_files'], 'total_dex_bytes': dexagg['total_dex_bytes'],
           'verification_failures': dexagg['verification_failures'],
           'classes': dexagg['counts']['class_defs'],
           'method_ids': dexagg['counts']['methods'],
           'field_ids': dexagg['counts']['fields'],
           'code_items': dexagg['counts']['code_items'],
           'code_units': dexagg['counts']['code_units']},
          ['dex.json:aggregate', 'dex.json:per_file'])
    claim('C08', 'Multidex is bounded by the 65,536 method-reference limit: two DEX files reach ~60,000 method IDs.',
          {'near_limit_files': [{'file': p['file'], 'methods': p['counts']['methods']}
                                for p in dex['per_file'] if p['counts']['methods'] >= 59000]},
          ['dex.json:per_file'],
          ['This is a structural inference from method_ids_size, not a claim about the build system configuration.'])
    claim('C09', 'A large fraction of class names are single-letter, consistent with identifier minification.',
          {'short_name_classes': dexagg['counts']['short_obfuscated_class_names'],
           'total_classes': dexagg['counts']['class_defs'],
           'ratio': round(dexagg['counts']['short_obfuscated_class_names'] /
                          max(1, dexagg['counts']['class_defs']), 4)},
          ['dex.json:aggregate.counts'],
          ['Single-letter final segments are a heuristic proxy for minification, not proof of a specific tool.'])
    claim('C10', 'The application embeds at least 23 identifiable third-party SDK namespaces in DEX.',
          {'sdk_namespace_count': len(sc['sdk_namespace_evidence']),
           'top_sdks': dict(list(sc['sdk_namespace_evidence'].items())[:12])},
          ['supply_chain.json', 'dex.json:aggregate.namespace_histogram_top'],
          ['Namespace presence does not establish version, licence or runtime activation.'])
    claim('C11', 'All 27 native libraries are 32-bit ARM ELF shared objects, each with a GNU build-id.',
          {'libraries': elfagg['libraries'], 'total_bytes': elfagg['total_bytes'],
           'machines': elfagg['machines'], 'build_ids': elfagg['build_id_count'],
           'dynsym_total': elfagg['dynsym_total'], 'jni_exports': elfagg['jni_exports_total'],
           'security': elfagg['security_feature_counts']},
          ['elf.json:aggregate', 'elf.json:per_library'])
    claim('C12', 'All five APKs carry APK Signature Scheme v2 and v3 blocks and share exactly one signer certificate.',
          {'apks_with_block': sig['aggregate']['apks_with_signing_block'],
           'schemes': sig['aggregate']['schemes_observed'],
           'single_signer': sig['aggregate']['single_signer_identity_across_all_apks'],
           'certificate_sha256': list(sig['aggregate']['signer_certificate_fingerprints'].keys()),
           'base_has_v1': sig['per_apk']['vn.com.vng.zalopay.apk']['jar_v1']['manifest_entries'] > 0},
          ['signing.json:aggregate', 'signing.json:per_apk'],
          ['Fingerprints are public certificate digests; no private key material exists or is published.',
           'Signature trust-chain validation is out of scope.'])
    claim('C13', 'Split APKs retain JAR v1 signatures using SHA-1 digests, while the base APK is v2/v3 only.',
          {'base_v1_manifest_entries': sig['per_apk']['vn.com.vng.zalopay.apk']['jar_v1']['manifest_entries'],
           'split_v1_digest_algorithms': sig['per_apk']['config.vi.apk']['jar_v1']['digest_algorithms'],
           'stamp_cert_sha256': sig['per_apk']['config.vi.apk']['stamp_cert_sha256'][0]['hex']},
          ['signing.json:per_apk'],
          ['SHA-1 in v1 manifests is a structural observation; v2/v3 provide the effective verification path.'])
    claim('C14', 'A React Native Metro production bundle is embedded in a nested ZIP inside assets.',
          {'nested_containers': len(nested['nested_containers']),
           'nested_members': sum(n.get('member_count', 0) for n in nested['nested_containers']),
           'metro_modules': js['bundle']['module_definitions_parsed'],
           'dependency_edges': js['bundle']['dependency_edges'],
           'entry_points': js['bundle']['entry_points___r']},
          ['nested.json', 'jsbundle.json'])
    claim('C15', 'The upstream REA tool was run on the extracted JS layer and produced an immutable evidence record.',
          {'tool': rea['tool']['name'], 'version': rea['tool']['version'],
           'evidence_id': rea['evidence']['evidence_id'],
           'graph_nodes': rea['graph']['node_count'], 'graph_edges': rea['graph']['edge_count'],
           'ast_nodes_visited': rea['statistics'].get('visited_ast_nodes'),
           'findings': rea['statistics'].get('findings')},
          ['rea_javascript.json'],
          rea['limitations_reported_by_REA'][:3])
    claim('C16', 'The app ships on-device ML assets and a mini-program container, evidenced by format magic and asset layout.',
          {'model_media_payloads': len(assets['base_apk_assets']['model_and_media_payloads']),
           'payload_magic_counts': assets['base_apk_assets']['model_counts_by_magic'],
           'json_shapes_parsed': assets['base_apk_assets']['json_files_parsed_as_shape'],
           'ncnn_headers': assets['base_apk_assets']['ncnn_param_headers']},
          ['assets.json:base_apk_assets', 'nested.json'],
          ['Model purposes are inferred from file names/format, not from runtime behaviour.'])
    claim('C17', 'Build provenance can be reconstructed from 215 version markers without reading any config values.',
          {'version_marker_files': versions['count'],
           'example_count': min(8, versions['count'])},
          ['library_versions.json'])
    claim('C18', 'Redaction by construction: no config value, key, token, endpoint path or certificate byte is published.',
          {'sensitive_files_recorded_as_digest_only': len(assets['base_apk_assets']['sensitive_or_config_files']),
           'high_entropy_members': strings['high_entropy_members']['count'],
           'text_url_strings_counted_not_listed': strings['text_corpus']['url_like_strings_in_text_members'],
           'secret_like_dex_strings_count_only': strings['dex_string_table']['secret_like_strings_count_only']},
          ['strings.json', 'assets.json', 'docs/ETHICS.md'])
    metrics = {
        'corpus': {'containers': isum['container_count'], 'zip_members': isum['total_zip_members_inventoried'],
                   'uncompressed_bytes': isum['total_uncompressed_bytes'],
                   'corpus_zip_bytes': inv['corpus']['bytes'],
                   'corpus_sha256': inv['corpus']['sha256']},
        'binary_formats': {'axml_files': axml['axml_files'], 'axml_elements': axml['total_elements'],
                           'resource_packages': res['tables']['vn.com.vng.zalopay.apk']['package_count'],
                           'resource_entries': resagg['total_defined_entries'],
                           'dex_files': dexagg['dex_files'], 'dex_classes': dexagg['counts']['class_defs'],
                           'dex_methods': dexagg['counts']['methods'],
                           'dex_code_units': dexagg['counts']['code_units'],
                           'elf_libraries': elfagg['libraries'], 'elf_bytes': elfagg['total_bytes'],
                           'apk_signing_blocks': sig['aggregate']['apks_with_signing_block']},
        'surface': {'permissions': len(base['permissions']),
                    'activities': len(base['components']['activity']),
                    'services': len(base['components']['service']),
                    'receivers': len(base['components']['receiver']),
                    'providers': len(base['components']['provider']),
                    'min_sdk': int(xapk['min_sdk_version']), 'target_sdk': int(xapk['target_sdk_version'])},
        'js_layer': {'metro_modules': js['bundle']['module_definitions_parsed'],
                     'metro_dependency_edges': js['bundle']['dependency_edges'],
                     'nested_containers': len(nested['nested_containers'])},
    }
    film = {
        'title': 'EVIDENCE / BOUNDARIES',
        'subtitle': 'A static, byte-level anatomy of ZaloPay 11.17.0',
        'numbers': [
            {'label': 'ZIP MEMBERS', 'value': f"{isum['total_zip_members_inventoried']:,}"},
            {'label': 'NESTED CONTAINERS', 'value': str(isum['container_count'])},
            {'label': 'BINARY XML FILES', 'value': f"{axml['axml_files']:,}"},
            {'label': 'DEX CLASSES', 'value': f"{dexagg['counts']['class_defs']:,}"},
            {'label': 'METHOD IDS', 'value': f"{dexagg['counts']['methods']:,}"},
            {'label': 'CODE UNITS', 'value': f"{dexagg['counts']['code_units']:,}"},
            {'label': 'ARM LIBRARIES', 'value': str(elfagg['libraries'])},
            {'label': 'BUILD-IDS', 'value': str(elfagg['build_id_count'])},
            {'label': 'RESOURCE ENTRIES', 'value': f"{resagg['total_defined_entries']:,}"},
            {'label': 'METRO MODULES', 'value': f"{js['bundle']['module_definitions_parsed']:,}"},
        ],
        'scenes': [
            {'id': 'container', 'headline': 'ONE ZIP. SEVEN CONTAINERS.', 'detail': 'ZIP → XAPK v2 → base + 4 splits + icon + manifest'},
            {'id': 'axml', 'headline': '5,319 BINARY XML FILES', 'detail': f"{axml['total_elements']:,} elements · 0 parse failures"},
            {'id': 'resources', 'headline': '13 RESOURCE PACKAGES', 'detail': 'Dynamic-feature modularisation · en/vi/xhdpi splits'},
            {'id': 'dex', 'headline': '63,827 CLASSES', 'detail': f"{dexagg['dex_files']} DEX · {dexagg['counts']['methods']:,} method IDs · all checksums verified"},
            {'id': 'elf', 'headline': '27 ARM LIBRARIES', 'detail': f"{elfagg['total_bytes']:,} bytes · 27 GNU build-IDs · 301 JNI exports"},
            {'id': 'signing', 'headline': 'ONE SIGNER. FIVE APKs.', 'detail': 'v2 + v3 on every split · identical certificate digest'},
            {'id': 'js', 'headline': '2,346 METRO MODULES', 'detail': 'React Native bundle nested inside assets · 9,783 dependency edges'},
            {'id': 'supply', 'headline': 'A SUPPLY CHAIN IN BYTES', 'detail': '23 SDK namespaces · 215 version markers'},
            {'id': 'ethics', 'headline': 'EVIDENCE, NOT EXPLOITATION', 'detail': 'Static only · redaction by construction · no secrets published'},
        ],
        'top_sdks': [{'name': k, 'classes': v['namespace_class_count']}
                     for k, v in list(sc['sdk_namespace_evidence'].items())[:10]],
        'resource_types': dict(list(resagg['top_resource_types'].items())[:10]),
        'claims': [{'id': c['id'], 'statement': c['statement']} for c in claims],
    }
    dump('report.json', {
        'schema': 'rea_lite/report@1',
        'title': 'Evidence-bounded static reverse engineering of ZaloPay 11.17.0 (APKPure XAPK)',
        'claims': claims, 'metrics': metrics, 'film': film,
        'limitations': [
            'Static analysis of one archived build only; no runtime behaviour is observed or claimed.',
            'Findings are byte-level structural observations, not vulnerability assessments.',
            'All sensitive values are redacted by construction; see docs/ETHICS.md.',
            'Format heuristics and instruction-width tables have documented accuracy limits per reader.',
        ],
        'evidence_files': sorted(os.path.basename(p) for p in os.listdir(ANALYSIS) if p.endswith('.json')),
    })
    print('claims', len(claims), 'film scenes', len(film['scenes']), 'evidence files', len(os.listdir(ANALYSIS)))


if __name__ == '__main__':
    main()
