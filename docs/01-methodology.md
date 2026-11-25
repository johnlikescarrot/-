# 01 — Methodology

## 1. Design constraints

Android reverse-engineering is normally mediated by a stack of JVM tools:
`apktool` (AXML/ARSC), `jadx`/`baksmali` (DEX), `apksigner` (signatures),
`readelf` (ELF). That stack has three properties that are hostile to
reproducibility: it requires a JDK, it changes behaviour across versions, and it
mostly emits *text for humans* rather than *data for analysis*.

PayTear inverts the dependency: it implements the formats natively and emits
JSON. The consequences are deliberate:

| Constraint | Consequence |
|---|---|
| No JVM, no Android SDK | runs in any CI container with Node ≥ 20 |
| No network | analysis of an untrusted artefact cannot exfiltrate data |
| Zero npm runtime dependencies | no supply-chain risk in the analysis tool itself |
| Deterministic output | `report.json` is diffable between app versions |
| Static only | no interaction with the vendor's production systems |

## 2. Pipeline

```
ZIP (container) ──► XAPK expansion ──► per-split analysis ──► aggregation ──► artefacts
                                          │
   ┌──────────────────────────────────────┼───────────────────────────────────┐
   │ AXML   binary XML → tree + typed attrs (resource-id aware)               │
   │ ARSC   resource table → packages/types/configs/entries, locale buckets   │
   │ DEX    header → ids → class_defs → class_data → code_item → instructions │
   │ ELF    phdrs/shdrs/dynamic/dynsym/.text entropy + hardening flags        │
   │ APK Sig Block  v2/v3 pairs → signed data → certs → RSA/EC verify         │
   └─────────────────────────────────────────────────────────────────────────┘
```

Nine stages, each pure:

1. **Intake** — SHA-256 the input, read the central directory, expand nested
   `.xapk`/`.apk` members in memory.
2. **Split classification** — `base` vs `config.<qualifier>` by name and by the
   presence of `AndroidManifest.xml` + `package` attribute.
3. **Manifest decode** — AXML tree; permissions, components, intent filters,
   exported flags, deep links, `<application>` attributes.
4. **Resource decode** — `resources.arsc`; entry counts, type histogram, locale
   buckets, and symbolic resolution of `@0x7f…` references (e.g. app label).
5. **DEX census** — for every class: access flags, superclass, interfaces,
   source file, method/field counts, decoded instruction count, invoke edges and
   string-constant count.
6. **Native census** — ELF identity, `DT_NEEDED`, JNI exports, section entropy,
   RELRO/NX/PIE/canary/FORTIFY posture, `.comment` compiler strings.
7. **Signature verification** — APK Signing Block parse (v2/v3/v3.1), signed-data
   and certificate extraction, RSA/EC verification via `node:crypto`.
8. **Fingerprinting** — 30 SDK fingerprints across package prefixes, native
   library names and asset paths; 27 permission-to-risk mappings.
9. **Reporting** — stable JSON, SVG figures, markdown tables, and the fact file
   that drives the paper and the video.

## 3. Format details that matter

**AXML.** Chunks are walked with `headerSize` respected per chunk (the string
pool is `0x0001`, resource map `0x0180`, elements `0x0102`). Attribute values are
decoded through the typed-value union (`0x03` string, `0x12` boolean, `0x10`
int, `0x01` reference…), so `cleartextTrafficPermitted="true"` is a *boolean*,
not the literal string "true". Resource IDs from `RES_XML_RESOURCE_MAP` are
retained, which makes it possible to resolve framework attributes even when the
name string is stripped.

**ARSC.** Type chunks may be *sparse* (`FLAG_SPARSE`); both offset encodings are
handled. Configs are decoded by their declared size (density at +14, sdkVersion
at +24), which is required because AOSP has grown the struct across releases.

**DEX.** `class_data_item` uses four ULEB128 counters (static/instance fields,
direct/virtual methods) with delta-encoded indices. `code_item` is decoded with a
256-entry instruction-width table, with switch/array payload pseudo-instructions
handled explicitly; invoke and field opcodes are attributed to their `method_ids`
/ `field_ids` entries to build the edge counts.

**ELF.** Program headers are parsed with the correct offsets for 32-bit
(`e_phoff` at 28) versus 64-bit (at 32) — a classic source of wrong "no
hardening" conclusions, which we hit during development and fixed (see §6).

**Signing.** The APK Signing Block is located by its trailing magic
(`APK Sig Block 42`) and 8-byte length prefix; v2/v3 pairs are length-prefixed
nested structures, and *both* digest and signature entries are themselves
length-prefixed — a detail that shifts every subsequent field by four bytes if
missed.

## 4. Validation strategy

Because we re-implement formats, correctness cannot be assumed. We used four
independent checks:

1. **Dual implementation.** The final numbers were reproduced by an independent
   Python implementation built on `androguard` (AXML/ARSC/APK) for the manifest
   and by a standalone Python DEX scanner for the census. Class, method, field
   and string counts agree exactly (63,827 / 287,480 / 286,224 / 404,914).
2. **Internal consistency.** v2 and v3 blocks carry independent digest records
   over the same file; they agree with each other byte-for-byte.
3. **Cryptographic closure.** Signature verification is not asserted from
   metadata: we verify RSA-PKCS#1-v1.5 over the signed-data bytes with the
   embedded SPKI key, which fails loudly if any length prefix is off.
4. **Negative controls.** Known-bad inputs (truncated chunks, unsupported
   compression, malformed certificate) raise typed errors instead of silently
   producing zeros; the ELF bug in §6 was caught this way.

## 5. Reproducibility

```bash
cd tools/paytear && npm ci
npx tsx src/cli.ts analyze <artifact.zip> --out analysis --deep
npx tsx src/docs.ts analysis/report.json docs/generated
```

Determinism notes: no timestamps are embedded in the measurements (only in
`meta.generatedAt`), map iteration is sorted before serialisation, and the SVG
figures are pure functions of the JSON. Two runs on the same input produce
byte-identical `report.json` except for the `generatedAt` and `durationMs`
fields.

## 6. Threats to validity

* **Channel bias.** The artefact is an APKPure redistribution, not the Play
  bundle. Split composition (32-bit only), signature retention and asset set may
  differ from what Play delivers. All claims are claims about *this file*.
* **Static-only.** No dynamic confirmation: an indicator (e.g. a `frida` string)
  proves *presence of a string*, not that the code path executes. We label
  string-corroborated signals as *indicators*, not as behaviour.
* **No decompilation.** Method bodies are counted and scanned for references, not
  lifted to Java/Kotlin. Control-flow-level claims are out of scope, which limits
  statements about cryptographic protocol logic.
* **Heuristic layers.** SDK fingerprinting by package prefix can both over-match
  (vendored forks) and under-match (renamed packages). Domain roll-up uses a
  two-label heuristic with a small multi-part-TLD list rather than a full Public
  Suffix List.
* **Digest anomaly R1.** See `02-artifact-and-signing.md` §4. We do not convert
  an unreproduced digest into an accusation; we publish the reproduction steps.

## 7. Cost

| Stage | Time (2 vCPU) | Peak RSS |
|---|---|---|
| Container expansion + AXML/ARSC | 0.4 s | 180 MB |
| DEX census (20 files, deep scan) | 2.9 s | 900 MB |
| ELF census (27 libraries) | 0.2 s | 60 MB |
| Signature verification (5 splits) | 0.2 s | 40 MB |
| Reporting + figures | 0.1 s | 30 MB |
| **Total** | **3.7 s** | **~1 GB** |
