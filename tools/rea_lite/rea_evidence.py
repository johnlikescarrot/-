"""Integrate the real REA CLI evidence (rea-agents 6.5.0) into our evidence set.

The full 2.5 MB immutable REA Evidence JSON is regenerable with the command in
`command`; this file publishes a compact, citable digest of it.
"""
from __future__ import annotations
import json, os, hashlib
from collections import Counter
from common import ROOT, WORK, dump

CMD = "npx -y rea-agents@6.5.0 analyze-javascript-application <work/jsapp> --json"


def main():
    p = os.path.join(WORK, 'rea_cli_output.json')
    if not os.path.exists(p):
        print('REA output missing; run the command in docs/REPRODUCIBILITY.md first')
        return
    raw = open(p, 'rb').read()
    r = json.loads(raw)
    n = r['normalized_result']; g = n['graph']; sg = n.get('semantic_graph', {})
    nodes = g.get('nodes', []); edges = g.get('edges', [])
    modules, assets = [], []
    for nd in nodes:
        path = None
        for ob in nd.get('observations', []):
            path = ob.get('properties', {}).get('path') or path
        if nd.get('kind') == 'javascript-module': modules.append(path)
        if nd.get('kind') == 'javascript-asset': assets.append(path)
    out = {
        'schema': 'rea_lite/rea_javascript_evidence@1',
        'tool': {'name': 'REA — Reverse Engineer Anything (rea-agents)', 'version': '6.5.0',
                 'provider': r.get('provider'), 'command': CMD,
                 'upstream': 'https://github.com/morluto/rea',
                 'note': 'rea-agents@6.10.1 requires Node >=22.23.4; 6.5.0 accepts the available Node 22.22.3.'},
        'evidence': {'evidence_id': r.get('evidence_id'),
                     'subject_sha256': r.get('subject', {}).get('digest', {}).get('sha256'),
                     'root_artifact_sha256': n.get('root_artifact_sha256'),
                     'inventory_graph_sha256': n.get('inventory_graph_sha256'),
                     'raw_output_bytes': len(raw), 'raw_output_sha256': hashlib.sha256(raw).hexdigest(),
                     'confidence': r.get('confidence'), 'authority': r.get('authority'),
                     'integrity_contradictions': n.get('integrity_contradictions', [])},
        'graph': {'schema': g.get('schema'), 'node_count': len(nodes), 'edge_count': len(edges),
                  'node_kinds': dict(Counter(nd.get('kind') for nd in nodes)),
                  'edge_relations': dict(Counter(ed.get('relation') for ed in edges)),
                  'coverage': g.get('coverage'), 'graph_id': g.get('graph_id'),
                  'javascript_modules': modules, 'javascript_assets': assets,
                  'semantic_graph_node_count': len(sg.get('nodes', [])),
                  'semantic_graph_relation_count': len(sg.get('relations', []))},
        'statistics': n.get('statistics', {}),
        'summary': n.get('summary', {}),
        'limitations_reported_by_REA': r.get('limitations', []) + n.get('limitations', []),
        'interpretation': [
            'REA reconstructed an inert, AST-derived application graph of the four JS bridge files.',
            'Zero findings/modules reported by REA reflect that these bridges are plain scripts, not a bundled CommonJS/ESM module graph.',
            'The 3.6 MB Metro bundle (main.jsbundle) is analysed separately in jsbundle.json.',
        ],
    }
    dump('rea_javascript.json', out)


if __name__ == '__main__':
    main()
