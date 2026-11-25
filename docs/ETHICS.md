# Scope, ethics and redaction policy

## What this study is

An academic, **static, offline** characterisation of a publicly distributed Android application
package: container topology, build provenance, component/resource inventory, binary-format
structure, native-library provenance, and supply-chain surface. It is the kind of work REA
("Reverse Engineer Anything") describes as *evidence with limitations behind each conclusion*.

## What this study is not

* No dynamic analysis, emulation, instrumentation, hooking, MITM or traffic capture.
* No reverse engineering of authentication, cryptographic protocols, PIN/biometric flows or
  payment authorisation logic; no guidance on bypassing integrity, root, certificate-pinning or
  anti-tamper controls, and no repackaging/modification of the application.
* No extraction or publication of secrets. We deliberately do **not** dump the contents of
  `assets/config/*`, `assets/*.json` configuration values, `META-INF` certificate private
  material, or any string that looks like a key, token, credential, account identifier or
  personal data.
* No claims about live server behaviour: all statements are about bytes in a specific archived
  build (`versionCode 1102758`, `versionName 11.17.0`), SHA-256 pinned in `analysis/inventory.json`.

## Redaction by construction

For sensitive-looking artifacts the reader emits only:

| Published | Never published |
| --- | --- |
| file path, byte length, SHA-256 (or salted digest), MIME/format guess, JSON key *paths*, value types, array lengths, count of distinct values | literal key/token/credential/URL-with-query/identifier values, certificate bytes, full config file contents |

URLs are reduced to registrable domains + counts, and only where the domain is a well-known SDK
or infrastructure provider; query strings, paths with identifiers and any credential-bearing
fragment are discarded. Strings from DEX are aggregated into structural statistics (length
histograms, namespace histograms, SDK-name frequencies) rather than literal dumps.

## Reproducibility without redistribution

The upstream ZIP remains the corpus and is already present in the repository history; the readers
take its path as input and emit only derived, small, non-sensitive evidence. Extracted payloads
live in `work/` and are git-ignored, so this repository does not become a redistribution point
for a third-party application.

## On "most-cited video on Google Scholar"

No engineering step can guarantee a citation count or an indexing outcome — that depends on the
research community, publication venue, peer review and time. The attainable, honest objectives
implemented here are: a genuinely novel dataset (complete nested-container + binary-format
evidence for this build), falsifiable numbered claims, a permanent citation record
(`CITATION.cff`, BibTeX, Zenodo-style metadata), fully reproducible code, a versioned film with
captions and a script, and an explicit limitations section. These are the inputs that make work
*citable*; the citation count itself is out of scope.
