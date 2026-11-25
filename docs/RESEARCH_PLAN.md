# Research design — ZaloPay 11.17.0 (XAPK)

## Research questions

1. **RQ1 – Packaging.** How is the distributed artefact assembled (base + split APKs),
   and what does that reveal about delivery, localisation and ABI targeting?
2. **RQ2 – Attack surface.** What declared components and permissions are exposed at
   the manifest boundary, and which are exported?
3. **RQ3 – Dependency graph.** Which third-party SDK families are present in the DEX
   bytecode, and what do their relative class counts say about the architecture?
4. **RQ4 – Hardening.** Which manifest-level and packaging-level defences are visible
   (signing scheme, backup policy, network security configuration, debuggability)?
5. **RQ5 – Communicability.** Can these measurements be turned into a rigorous,
   reproducible narrated video essay with an evidence trail a reader can audit?

## Method

- **Corpus.** One distributor ZIP containing one XAPK (APKPure split-package format).
- **Integrity first.** SHA-256 of the outer archive, inner XAPK and every member is
  recorded before interpretation (`tools/inventory.py`).
- **Static, low-memory parsing.** Full whole-program data-flow analysis of a ~100 MB
  multi-DEX app is memory-prohibitive in this environment. We therefore parse the DEX
  *headers and string/type/class tables directly* (no CFG construction), and use
  `androguard.core.apk.APK` for binary AndroidManifest.xml and component metadata.
- **Measurement, not accusation.** Every claim in the report cites a counter or an
  exact manifest value produced by a committed script. Nothing is inferred from
  marketing material or from the app's runtime behaviour.
- **Redaction.** URLs are reduced to registrable domains; query strings, tokens,
  hashes and long opaque values are discarded. No code is decompiled into a
  publishable form beyond class/package names needed for SDK identification.

## Evidence standard

| Claim type | Required evidence |
| --- | --- |
| File/package facts | SHA-256 + byte size from `research/provenance.json` |
| Manifest facts | Exact attribute value from `androguard` API, stored in `findings.json` |
| SDK presence | Class-name prefix match with count, stored per DEX |
| Endpoint claims | Domain only, frequency count, no path/query/secret |
| Security posture | Presence/absence of a named manifest attribute, with raw value |

## Explicit non-goals

- No dynamic instrumentation, hooking, emulation or traffic interception.
- No authentication, payment, OTP, card, or account data handling.
- No bypass of integrity checks, root detection, certificate pinning or anti-fraud.
- No attempt to influence real-world citation counts; see `docs/CITATION_POLICY.md`.

## Limitations

- Split APKs are analysed independently; cross-split resolution of resources is out
  of scope.
- Class-prefix SDK detection is a heuristic: it can over-count shaded/relocated
  libraries and under-count obfuscated ones. Counts are reported as *indicators*,
  not as a license audit.
- Absence of a manifest flag does not prove the absence of the underlying behaviour;
  server-side controls are not observable from a package alone.
