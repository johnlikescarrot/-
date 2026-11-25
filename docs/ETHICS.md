# Ethics and scope

This study inspects a **publicly distributed** application package using static
artefact inspection only. It is written as security-education and software-
architecture research.

## What we did

- Hashed the supplied ZIP/XAPK and all seven members before interpretation.
- Parsed binary manifests, DEX metadata tables, ZIP layout and signing metadata.
- Reported aggregate counts and exact manifest attribute values.
- Reduced every URL literal to a hostname; discarded paths, query strings,
  tokens and opaque values. Where an opaque/secret-looking literal matched a
  keyword pattern, only a **count** was kept.

## What we deliberately did not do

- No decompilation of business logic into publishable source.
- No dynamic analysis, emulation, hooking, traffic interception or instrumentation.
- No access to accounts, credentials, OTPs, cards, transactions or backend APIs.
- No bypass of root detection, certificate pinning, integrity checks or anti-fraud.
- No publication of keys, secrets, tokens, private endpoints beyond redacted hosts.

## Interpretation

A permission or SDK presence is **not** evidence of misuse. Exported components can
be intentional (deep links, sharing, push receivers). Obfuscation ratios count
short identifiers; they do not imply maliciousness. All findings are indicators
for further defensive review, not accusations against the vendor.

## Responsible disclosure

If a reviewer identifies a concrete, demonstrable vulnerability from this report,
please follow coordinated disclosure with the vendor rather than publishing
exploitation details. This repository will not accept exploit code or sensitive
data in issues or pull requests.
