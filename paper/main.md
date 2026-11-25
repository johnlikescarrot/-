# PayTear: Measuring a Production Payment Client Without a JVM

### A reproducible static teardown of ZaloPay 11.17.0 — 63,827 classes, 27 native libraries, 62 SDK fingerprints, and ten findings

**PayTear Project** · 25 November 2026 · artifact: `zalopay-11.17.0-teardown` · preprint

---

## Abstract

Mobile payment clients are among the most dependency-dense artefacts in consumer
software, yet the tooling used to study them is largely unchanged since the
Dalvik era: a JVM-based chain of `apktool`, `jadx`, `baksmali` and `apksigner`
that emits human-readable text, depends on pinned tool versions, and is hard to
re-run. We argue that this is a methodological bottleneck, not merely an
ergonomic one: when the observation instrument is itself opaque, measurements of
commercial apps are difficult to reproduce, compare across releases, or subject
to peer review.

We present **PayTear**, a zero-dependency TypeScript pipeline that re-implements
the formats required for a complete static teardown — ZIP, Android binary XML,
`resources.arsc`, Dalvik EXecutable, ELF and the APK Signature Scheme v2/v3 — and
emits a deterministic JSON corpus rather than prose. We apply it to ZaloPay
11.17.0 (`vn.com.vng.zalopay`, versionCode 1102758), a production Vietnamese
wallet distributed as a 105.6 MB XAPK. The subject contains 63,827 class
definitions (60,671 unique), 287,480 methods, 286,224 fields, 404,914 string-pool
entries and 27 native libraries (54.8 MiB) from 17 vendors; our instruction walk
decodes 9,258,758 instructions and 1,729,915 invoke edges in 3.7 s on two vCPUs.

We report ten findings. Four are configuration-level defects that survive across
versions: globally permitted cleartext traffic in a payment client targeting API
36; ten non-production endpoints (including one cleartext Alipay development
gateway) shipped in the release DEX; exported AndroidX Test and Compose tooling
activities in a production build; and seven payment card-emulation AIDs routed
with `requireDeviceUnlock="false"`. Three are structural: a 32-bit-only native
distribution caused by the redistribution channel; rename-only obfuscation that
retains `SourceFile` for 82.6 % of classes; and 3.9 MB of unreachable React
Native dated 2022. Three are architectural: an anti-tamper stack whose Java entry
points are absent from the DEX, a 17-vendor supply chain with three coexisting
payment rails, and a permission set containing nine high-risk capabilities.

We also publish an unresolved measurement question: the v2/v3 signature over the
signed-data block verifies cryptographically for all five splits, but our chunked
content-digest recomputation does not reproduce the recorded digest under nine
tested construction variants. We report the reproduction rather than a
conclusion, and identify the decisive cross-check.

**Keywords:** Android; static analysis; reverse engineering; APK signature
scheme; software supply chain; fintech; reproducibility.

---

## 1. Introduction

A payment application is a high-value target and a high-obligation product. It
must interoperate with card networks, national identity systems and
cross-border acquirers; it embeds machine-learning models for OCR and liveness;
it ships anti-tamper code; and it must do all of this while remaining auditable
by its own security team and legible to regulators.

Studying such an application is therefore valuable in three directions: for
security researchers who want a realistic, hardened target; for the vendor's own
supply-chain governance; and for the ecosystem, because the permission and
network posture of a dominant wallet sets a de facto baseline for a national
market.

The instrument problem is real. The standard toolkit has four properties that
work against measurement:

1. **A runtime dependency.** `apktool`, `jadx`, `baksmali` and `apksigner` all
   require a JVM. Analysis containers frequently lack one.
2. **Version sensitivity.** Output formats and decoding edge cases change
   between releases; two analysts with different versions can obtain different
   "facts" from the same bytes.
3. **Prose output.** The tools are designed for a human reading a decompiled
   tree, not for a pipeline that diffs releases or aggregates across a corpus.
4. **Silent failure.** Decoders frequently degrade to empty or partial output
   instead of failing loudly, which is the worst property for research.

PayTear inverts each of these: it implements the formats natively, fails loudly,
emits JSON, and treats the APK as a dataset. The cost is implementation risk,
which we address with dual implementations and cryptographic closure (§3.5).

**Contributions.**

* **C1** A zero-dependency TypeScript analysis toolkit covering six binary
  formats and an independent implementation of APK signature verification, with
  measured runtime and memory (§3).
* **C2** A reproducible corpus for a production payment client: 63,827 classes,
  9.26 M decoded instructions, 27 native libraries, 62 SDK fingerprints, 151
  hosts (§4).
* **C3** Ten findings, each tied to a field-level evidence path, separating
  configuration defects from design observations (§4, Appendix A).
* **C4** An open measurement question (R1) with reproduction steps and competing
  hypotheses, published rather than resolved by assertion (§3.6).
* **C5** An artefact family — JSON, figures, paper, video — generated from a
  single deterministic source of truth, so that prose, chart and narration cannot
  drift.

---

## 2. Background and related work

### 2.1 Android application packaging

An Android application is a ZIP archive with a binary manifest, a resource table,
one or more Dalvik executables, optional native libraries and assets. Since
Android 5.0 (API 21) apps may be delivered as *split APKs* selected by ABI,
density and language; third-party mirrors often redistribute such splits as an
XAPK container (a ZIP of APKs plus a JSON manifest). Analysing "the app" is
therefore ambiguous unless the channel and split set are stated — a point F1
makes concrete.

### 2.2 The decoding stack

`Androguard` [1] pioneered pure-Python parsing of AXML, ARSC and DEX and remains
the reference for scripted analysis. `apktool` and `jadx` provide decompilation
to smali and Java respectively; `baksmali` provides disassembly. MobSF packages
these into a report-oriented workflow. Our work is complementary: PayTear does
not decompile method bodies, and deliberately trades depth for determinism and
portability.

### 2.3 Privacy and permission analysis

Permission analysis has a long lineage from Kirin [2] and PScout [3] through
FlowDroid [4], ICCTA [5] and SuSi [6]; TaintDroid [7] established dynamic
ground truth for data flows. Our permission measurements are descriptive rather
than flow-based: we map 49 declared permissions onto 27 privacy-relevant
capabilities and 3 risk tiers, and we do not claim that a declared permission is
exercised.

### 2.4 Third-party library and supply-chain detection

LibRadar [8] and LibScout [9] established package- and signature-based library
identification; the broader question of SDK-induced risk was framed by
large-scale ecosystem studies [10]. Our fingerprint catalogue is smaller (30
rules) but multi-channel (package prefix, native library, asset path), which we
found necessary because container-style SDKs (mPaaS, ML Kit) expose little Java
surface.

### 2.5 Obfuscation and hardening

Obfuscation in the wild has been characterised at scale, with renaming dominant
and string encryption comparatively rare. Our measurement (67.9 % short names,
82.6 % retained `SourceFile`) is consistent with that literature and quantifies
its consequence: string-level semantics remain available to an analyst.

### 2.6 Signatures

The APK Signature Scheme v2 and v3 replaced JAR signing for modern targets; the
block is stored between the last entry and the central directory and covers the
archive through a chunked digest construction. To our knowledge, independent
re-implementations in TypeScript are not common, and our R1 anomaly (§3.6) shows
why an independent implementation is valuable: it surfaces assumptions that a
single toolchain hides.

### 2.7 Position

Existing tools answer *"what does this app do?"*. This paper answers *"what can
be measured about this app, reproducibly, by someone who does not trust my
toolchain?"* — and, as a side effect, produces the measurements.

---

## 3. Method

### 3.1 Pipeline

Nine pure stages: intake → split classification → manifest decode → resource
decode → DEX census → native census → signature verification → fingerprinting →
reporting. Each stage is a pure function of bytes to JSON; the CLI is a thin
orchestrator. No stage performs network I/O.

### 3.2 Format notes

**AXML.** Chunk walking must respect per-chunk `headerSize` (the string pool
declares 28, elements 16). Attribute values are decoded through the typed union:
`0x03` string, `0x12` boolean, `0x10` integer, `0x01` reference. The
`RES_XML_RESOURCE_MAP` chunk maps attribute-name strings to framework resource
IDs, which allows framework attributes to survive name stripping.

**ARSC.** Type chunks may be sparse (`FLAG_SPARSE`), in which case entries are
`(u16 index, u16 offset/4)` pairs rather than a dense `u32` offset array. The
config struct is decoded by its declared size because AOSP extended it over time;
density lives at +14 and `sdkVersion` at +24 of the config block.

**DEX.** `class_data_item` is four ULEB128 counters followed by delta-encoded
field and method indices; each method carries a `code_off`. We decode
`code_item` with a 256-entry instruction-width table, treat
`packed-switch-payload`/`sparse-switch-payload`/`fill-array-data-payload` as
terminators of a linear scan, and attribute `invoke-*` and field opcodes to their
`method_ids`/`field_ids` entries. The resulting edge count is a *lower bound*:
virtual dispatch, reflection and JNI are unresolved.

**ELF.** A 32-bit/64-bit confusion in program-header offsets produces the
spurious conclusion "no NX, no RELRO". We hit this during development; it is
worth stating because the same bug is common in ad-hoc scripts.

**Signing.** The block is found by trailing magic and length prefix; v2/v3 pairs
are nested length-prefixed structures in which *both* digest and signature
entries are themselves length-prefixed. v3 places `minSdkVersion` and
`maxSdkVersion` in the signer, not in the signed data.

### 3.3 Metrics

*Census*: class/method/field/string/instruction/edge counts per DEX and in
aggregate, package histograms at depth 3, largest classes by method count.

*Obfuscation*: fraction of classes with simple names ≤ 2 characters; fraction
retaining `SourceFile`; mean simple-name length.

*Native*: size, `.text` size, `DT_NEEDED`, JNI exports, section entropy,
`PT_GNU_RELRO`/`PT_GNU_STACK`/`DT_BIND_NOW` presence, canary/FORTIFY string
presence, build-id.

*Supply chain*: multi-channel fingerprint matching (28 package-prefix, 22
native-library, 12 asset-path matches).

*Privacy*: mapping of declared permissions to 27 privacy capabilities and three
risk tiers, with the explicit caveat that declaration ≠ use.

*Network*: URL extraction from DEX string constants, registrable-domain roll-up
(two-label heuristic plus a small multi-part-TLD list), cleartext inventory.

### 3.4 Redaction

Every string reaching an artefact passes through a redaction filter (PEM blocks,
`AIza…` API keys, AWS key IDs, JWT-shaped tokens, ≥32-character hex blobs). No
private key material was found in the subject.

### 3.5 Validation

Four independent checks:

1. **Dual implementation.** Manifest facts reproduced with `androguard`; census
   reproduced with a standalone Python DEX scanner. Counts agree exactly.
2. **Internal consistency.** v2 and v3 record the same content digest.
3. **Cryptographic closure.** RSA-PKCS#1-v1.5 verification over signed data with
   the embedded SPKI key; fails loudly on any length-prefix error.
4. **Negative controls.** Malformed inputs raise typed errors; the ELF bug in
   §3.2 was caught by implausible output (27/27 libraries "unhardened").

### 3.6 Open question R1

For all five splits, the signature over the signed-data block verifies, but our
recomputation of the chunked content digest

```
chunk   = H(0xa5 ‖ u32le(len) ‖ bytes)
section = H(0x5a ‖ u32le(nchunks) ‖ chunk_digest*)
content = H(0x5a ‖ u32le(3) ‖ section_digest*)
sections = [0, signing_block) ∪ [central_dir, eocd) ∪ [eocd, EOF)
```

yields a stable value that differs from the recorded one. Nine variants were
tested (section boundaries including the signing block, EOCD offset zeroed or
rewritten to the block offset, EOCD-only third section, two-section layouts,
chunk/top-level prefix permutations, and re-padded block sizes from 4 KiB to
200 KiB in 4 KiB steps). None matched.

Two hypotheses remain: **H1**, our implementation diverges from `apksig` in a
detail we have not found, and the artefact is intact; **H2**, the archive layout
changed after signing in a way that alters only the EOCD offset field (e.g.
re-padding of the signing block), leaving the signature valid but the digest
stale. Structural evidence favours H1: no gaps between local headers, no data
descriptors, consistent local/central sizes, and a page-aligned block. The
decisive experiment is `apksigner verify --verbose`, which requires a JVM we did
not have. We publish the reproduction rather than the conclusion.

---

## 4. Measurements

### 4.1 Artifact

| Property | Value |
|---|---|
| Container | XAPK v2, 5 APKs, 105,607,171 B uncompressed |
| Base APK | 79,359,087 B, 6,162 entries, 136.4 MB uncompressed |
| Package / version | `vn.com.vng.zalopay` / 11.17.0 (1102758) |
| SDK range | minSdk 26, targetSdk 36, compileSdk 36 |
| ABI | `armeabi-v7a` only |
| Input SHA-256 | `34b9b6b6…b4610a` |

### 4.2 DEX census

20 DEX files (19 `classes*.dex` plus `assets/audience_network.dex`), 69.7 MB,
63,827 class definitions, 60,671 unique names, 287,480 methods, 286,224 fields,
404,914 string-pool entries, 242,266 string constants reached from code,
9,258,758 instructions, 1,729,915 invoke edges.

Vendor code dominates roughly 10:1: `com.google` 7,761 classes,
`org.bouncycastle` 6,112, `com.alibaba` 4,821, `com.facebook` 3,622,
`com.iap` 1,599, `com.visa` 1,370, `androidx.media3` 1,198, `vn.com` 1,054.

### 4.3 Obfuscation

| Metric | Value |
|---|---|
| Classes with ≤ 2-char simple names | 43,342 (67.9 %) |
| Classes retaining `SourceFile` | 52,728 (82.6 %) |
| Mean simple-name length | 6.88 |
| Vendor packages renamed | no (prefix detection 28/28) |

Obfuscation is renaming-only: strings, attribute tables and source-file names
survive. The application class is renamed (`jwbmqnsll.xvndbxlk`) but its
superclass is not (`vn.com.vng.zalopay.AndroidApplication`).

### 4.4 Native layer

27 libraries, 54.8 MiB, all `armeabi-v7a`, all PIE. Hardening: NX 27/27, full
RELRO 23/27, canary 25/27, FORTIFY 13/27. The vendor's own guard libraries are
the best hardened (`libtaibmcf` 12.0 MiB, `libfgcpkjvqe` 6.2 MiB,
`libljsfrcwmw` 0.57 MiB — all full RELRO + canary + FORTIFY); every gap belongs
to third-party ML or Crashlytics objects. No `.text` region exceeds 7.9 bits of
entropy, i.e. no packed or encrypted payload: the guard protects execution, not
confidentiality.

`libohxbee.so` references `vn.com.vng.bcell.BShieldApplication` and
`vn.ai.faceauth.*` JNI signatures; **no such classes exist in any DEX**. The
Java-side entry point of the anti-tamper stack is absent — either dead code from
a shared VNG build or resolved dynamically.

### 4.5 Configuration findings

**Cleartext.** `network_security_config.xml` declares
`cleartextTrafficPermitted="true"` in `<base-config>`, with system trust anchors
only and `debug-overrides` trusting user CAs. Platform default for
`targetSdk ≥ 28` is *false*.

**Exports.** 30 of 267 components exported, including three
`androidx.test.core.app.InstrumentationActivityInvoker*` activities and
`androidx.compose.ui.tooling.PreviewActivity`.

**HCE.** Seven AIDs under a `host-apdu-service` with
`requireDeviceUnlock="false"`: PPSE, Visa credit/debit, Electron, Interlink,
Plus, UnionPay `A0000000980840`, and the Visa scheme root `A000000003`. Two
further HCE services come from the mPaaS container.

**Non-production endpoints.** Ten staging/sandbox strings, including a cleartext
Alipay dev gateway (`http://iopengw-280.sggz00b.dev.alipay.net/`), FPT eKYC
UAT/dev, Tenpay Global UAT, Visa sandbox, and a ZaloPay sandbox URL regex.

**Dead code.** `assets/android.1.zip` (3.9 MB) contains `main.jsbundle` (3.6 MB,
pre-Hermes, timestamped 2022-03-08) and 326 drawables; no RN runtime library and
no DEX reference to the bundle exists.

### 4.6 Supply chain

62 fingerprint matches across 17 vendors. Three payment rails coexist (IAP
Connect 1,599 classes; Visa CBP + Payment SDK 1,370; Tenpay Global endpoints).
Ads contribute a separate 4.86 MB DEX in `assets/` (3,156 classes). Two OCR
pipelines, two face stacks, two liveness surfaces and two HCE services are
present. BouncyCastle (6,112 classes, including post-quantum packages) is the
largest single dependency.

### 4.7 Privacy

49 permissions; 27 privacy-relevant; nine high-risk (contacts read/write,
camera, microphone, fine/coarse location, phone state, `QUERY_ALL_PACKAGES`,
overlay capability). Identifier APIs appear as 37 reachable strings; with
AppsFlyer, Meta Audience Network and Firebase present, advertising-ID collection
is near-certain in practice. Data-at-rest posture is strong (SQLCipher,
`allowBackup=false`, extraction rules).

### 4.8 Signatures

v2 + v3, RSASSA-PKCS1-v1_5-SHA256, RSA-2048, self-signed `CN=Vu Manh Hieu`
(2016→2041, cert SHA-256 `59dd31fd…`), no v1, plus `source-stamp`,
`play-frosting` and 1,602 B of padding. Signature-over-signed-data verifies for
all five splits; content-digest recomputation is R1.

---

## 5. Discussion

**The channel is part of the artefact.** F1 is the clearest example: a claim
about "ZaloPay's native attack surface" derived from this file would be wrong,
because the file is 32-bit only by packaging accident. Measurement papers should
treat distribution channel as a first-class variable.

**Renaming is not obfuscation.** F3 and F5 interact: because strings and source
file names survive, staging endpoints and error messages are recoverable with
`grep`-level effort. Vendors who pay for R8 often do not pay for metadata
stripping; the marginal cost is one flag, the marginal benefit is that crash
symbolication stops leaking internal names.

**Configuration defects outlive code fixes.** Cleartext permission, exported
tooling activities and non-production endpoints are one-line fixes that persist
across releases because no test asserts their absence. This is precisely what a
deterministic JSON corpus enables: a CI gate can assert
`network.cleartextUrls ⊆ known-benign` and fail the build.

**HCE is a software obligation.** Wallet AIDs must be scheme-level to be selected
by any terminal; `requireDeviceUnlock=false` moves the entire authorisation
burden into the app. Static analysis cannot evaluate that policy, but it can name
the exact surface a dynamic study should target.

**Native hardening is vendor-shaped.** The best-hardened libraries are the least
documented; the best-documented are third-party and the least hardened. A
"hardened app" claim needs a per-library matrix, not an average.

**R1 is a methodological result.** An independent implementation produced a
verifiable signature and an unreproducible digest. Publishing that asymmetry —
rather than quietly dropping the check — is the difference between a tool demo
and a measurement.

---

## 6. Threats to validity

* **Channel bias.** APKPure redistribution; split set, signature retention and
  assets may differ from Play delivery. All claims are about *this file*.
* **Static-only.** Strings prove presence, not execution. Indicators are labelled
  as indicators.
* **No decompilation.** No control-flow claims; call-graph edges are a lower
  bound.
* **Heuristics.** SDK prefix matching over/under-matches; domain roll-up is a
  two-label heuristic, not the Public Suffix List.
* **Single artefact.** n = 1; no temporal or cross-vendor comparison.
* **R1 unresolved.** Content-digest mismatch is reported, not interpreted.
* **No dynamic confirmation** of cleartext usage, HCE policy or identifier
  collection.

---

## 7. Availability

Repository: toolkit, `analysis/report.json`, figures, generated tables, this
paper's source and the video (`video/out/`, 1080p MP4). Reproduction requires
Node ≥ 20 and 1 GB RAM; the full teardown runs in 3.7 s. The analysed APK is not
redistributed.

## 8. Ethics

Static analysis only; no instrumentation, no traffic interception, no bypass of
authentication or anti-tamper controls, no exploit development, no redistribution
of vendor binaries. Credentials are redacted at extraction time. Findings of the
"configuration hygiene" class are published as measurements; design observations
are framed as hypotheses; R1 is published with reproduction steps and without
accusation. The authors are independent of VNG Corporation.

## 9. Conclusion

A production payment client can be measured reproducibly without a JVM, and the
measurement is cheap: four seconds and one gigabyte for 63,827 classes, 9.26
million instructions and 54.8 MiB of native code. What the measurement buys is
not only the ten findings reported here but a *diffable* representation of a
large commercial artefact — the substrate on which release-over-release supply
chain monitoring, permission drift detection and configuration regression tests
can be built. The instrument, not the anecdote, is the contribution.

---

## Appendix A — Evidence map

| Finding | Primary evidence path in `analysis/report.json` |
|---|---|
| F1 32-bit only | `app.abis`, `native.abis`, `container.apkSplits` |
| F2 v2+v3 verified | `splits[].signing.schemes[].signers[].signatures[].verified` |
| F3 rename-only | `dex.obfuscation.shortNames`, `.sourceFilePresent` |
| F4 cleartext | decoded `res/xml/network_security_config.xml`; `splits[].appAttrs` |
| F5 endpoints | `network.cleartextUrls`, `indicators[cleartext]` |
| F6 HCE | decoded `res/xml/apdu_service.xml`; exported service list |
| F7 dead RN | `assets.notable`, native library list, DEX string probes |
| F8 orphan guard | `native.libs[].jniExports`; absence of `vn/com/vng/bcell/*` in class index |
| F9 supply chain | `supplyChain.sdkMatches`, `docs/generated/supply-chain.md` |
| F10 permissions | `privacy.dangerous`, `docs/generated/permissions.md` |
| R1 digest | `splits[].signing.schemes[].contentDigest{Expected,Computed}` |

## Appendix B — Toolkit modules

| Module | Responsibility | LOC |
|---|---|---|
| `zip.ts` | central-directory reader, EOCD/ZIP64, CRC | ~150 |
| `axml.ts` | binary XML → typed tree | ~260 |
| `arsc.ts` | resource table, sparse types, configs, resolution | ~290 |
| `dex.ts` | DEX census + instruction walk + edges | ~380 |
| `elf.ts` | ELF identity, hardening, JNI exports, entropy | ~300 |
| `signing.ts` | v2/v3 block parse + crypto verification | ~330 |
| `strings.ts` | extraction, indicators, URL/domain, redaction | ~250 |
| `fingerprints.ts` | SDK catalogue, permission risk map | ~180 |
| `chart.ts`, `report.ts`, `docs.ts` | SVG figures, tables, doc generation | ~330 |

## References

[1] A. Desnos and G. Gueguen, "Android: From Reversing to Decompilation," *SSTIC*, 2011.

[2] W. Enck, M. Ongtang, and P. McDaniel, "On Lightweight Mobile Phone Application Certification," *ACM CCS*, 2009.

[3] K. W. Y. Au, Y. F. Zhou, Z. Huang, and D. Lie, "PScout: Analyzing the Android Permission Specification," *ACM CCS*, 2012.

[4] S. Arzt et al., "FlowDroid: Precise Context, Flow, Field, Object-sensitive and Lifecycle-aware Taint Analysis for Android Apps," *PLDI*, 2014.

[5] D. Octeau et al., "Composite Constant Propagation and its Application to Android Program Analysis," *IEEE TSE*, 2016.

[6] S. Rasthofer, S. Arzt, and E. Bodden, "A Machine-learning Approach for Classifying and Categorizing Android Sources and Sinks," *NDSS*, 2014.

[7] W. Enck et al., "TaintDroid: An Information-flow Tracking System for Realtime Privacy Monitoring on Smartphones," *OSDI*, 2010.

[8] Z. Ma, H. Wang, Y. Guo, and X. Chen, "LibRadar: Fast and Accurate Detection of Third-party Libraries in Android Apps," *ICSE Companion*, 2016.

[9] M. Backes, S. Bugiel, and E. Derr, "Reliable Third-Party Library Detection in Android and its Security Applications," *ACM CCS*, 2016.

[10] K. Allix, T. F. Bissyandé, J. Klein, and Y. Le Traon, "AndroZoo: Collecting Millions of Android Apps for the Research Community," *MSR*, 2016.

[11] W. Enck, D. Octeau, P. McDaniel, and S. Chaudhuri, "A Study of Android Application Security," *USENIX Security*, 2011.

[12] Y. Zhou and X. Jiang, "Dissecting Android Malware: Characterization and Evolution," *IEEE S&P*, 2012.

[13] D. Arp et al., "DREBIN: Effective and Explainable Detection of Android Malware in Your Pocket," *NDSS*, 2014.

[14] A. P. Felt, E. Chin, S. Hanna, D. Song, and D. Wagner, "Android Permissions Demystified," *ACM CCS*, 2011.

[15] Android Open Source Project, "APK Signature Scheme v2," *Android Developers Documentation*.

[16] Android Open Source Project, "Dalvik Executable format," *Android Open Source Project Documentation*.

[17] Android Open Source Project, "Network security configuration," *Android Developers Documentation*.

[18] Google Play, "Android 64-bit requirement," *Play Console Help* (2019).

[19] Android Open Source Project, "Host-based card emulation," *Android Developers Documentation*.

[20] MITRE, "CWE-295: Improper Certificate Validation," *Common Weakness Enumeration*.
