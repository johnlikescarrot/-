# Research corpus — ZaloPay 11.17.0

Read in this order. Bản tiếng Việt: [`VI-TONG-QUAN.md`](VI-TONG-QUAN.md).

| # | document | what it establishes |
|---|---|---|
| 00 | [Executive summary](00-executive-summary.md) | VI + EN abstract, 10 headline findings, rubric |
| 01 | [Provenance & integrity](01-provenance-and-integrity.md) | container chain, v2/v3 block decoded by hand, Play source stamp, undocumented block |
| 02 | [Container & composition](02-container-and-composition.md) | 6 162 ZIP entries, splits, ARSC census, why 30 MB of assets matters |
| 03 | [Manifest & attack surface](03-manifest-and-attack-surface.md) | 265 components, 28 reachable, HCE AIDs, deep links, test-harness leak |
| 04 | [Bytecode & obfuscation](04-bytecode-and-obfuscation.md) | 60 671 classes, HHI 0.036, R8 evidence, multidex watermark |
| 05 | [SDK topology](05-sdk-and-dependency-topology.md) | 39 vendors, 151 hosts, mini-app runtime, five payment SDKs |
| 06 | [Native forensics](06-native-code.md) | 27 ELF, hardening 27/27, shield anatomy, 284 JNI symbols, build-path leaks |
| 07 | [Crypto, TLS, integrity](07-crypto-tls-and-integrity.md) | cleartext policy, pinning coverage, keystore, attestation, on-device ML |
| 08 | [Privacy & data flows](08-privacy-and-data-flows.md) | permission ledger, sensitive APIs, 151-host classification |
| 09 | [Threat model & findings](09-threat-model-and-findings.md) | 7 adversaries, 9-entry register, rubric arithmetic, remediation, falsifiers |
| 10 | [Methodology](10-methodology.md) | toolchain choices, determinism, threats to validity, dynamic plan |
| 11 | [Ethics & disclosure](11-ethics-and-disclosure.md) | what is excluded, legal framing, 90-day disclosure, citation strategy |

Machine-readable companions: `data/*.json` (nine datasets), `data/dataset.csv` (30 metrics),
`data/figures/*.png` (8 plates).

## Cite this work

```bibtex
@misc{zalopay_re_2026,
  title  = {Full-Stack Static Reverse Engineering of the ZaloPay 11.17.0 Android Client},
  author = {{Arena Agent Research Collective}},
  year   = {2026},
  note   = {Version 1.0.0, dataset and TypeScript documentary},
  url    = {https://github.com/johnlikescarrot/-}
}
```

`CITATION.cff` carries the same metadata in machine-readable form.
