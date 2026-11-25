# 10 · Methodology, toolchain, limits and reproducibility

## 10.1 Philosophy

Three rules govern this study:

1. **Rebuild everything from the artefact.** No figure in this report comes from a tool's summary
   screen; every number is emitted by a script in `tools/analyze/` from the ZIP committed in the
   repository.
2. **Prefer the smallest tool that answers the question.** `dexmeta.py` (180 lines) and
   `arscmeta.py` (150 lines) replaced two heavyweight frameworks for the census stage and run
   20–40× faster, because they never decode an instruction.
3. **Publish the epistemics with the claim.** A string in the DEX proves a *capability string
   exists*; it does not prove a code path executes it. Every signal table carries that caveat,
   and §9.5 states what evidence would overturn each conclusion.

## 10.2 Toolchain

| stage | tool | why this and not the usual |
|---|---|---|
| container | `zipfile`, `hashlib` | deterministic, no extraction ordering assumptions |
| signing | **custom** APK Signing Block parser + `cryptography` for X.509/PKCS#7 | `apksigner` needs a JVM; the block format is 60 lines of `struct` |
| manifest/ARSC | androguard 4.1.4 (AXML) + **custom** `arscmeta.py` | androguard's ARSC decoder logs thousands of noise lines and mis-decodes some config chunks; the custom parser is 150 lines and exact |
| bytecode | **custom** `dexmeta.py`, stdlib only | full framework startup costs minutes per DEX; we only need tables |
| native | `pyelftools` | correct, maintained ELF reader |
| signals | Python `re` over the extracted string table | reproducible, auditable, no ML heuristics |
| figures | matplotlib (Agg) | 16:9 dark plates shared by report and film |
| film | TypeScript 5 + Node 22, ffmpeg 9 (libass/libfreetype) | see `video/README.md` |

**Not used, deliberately:** `jadx`/`apktool` (no JVM in the pipeline; and decompiled source is not
needed for a structural census), `Frida`/emulator (out of scope for a pure-static study), any
network service (the pipeline is offline and re-runnable in CI).

## 10.3 Determinism

```bash
bash tools/analyze/run_all.sh   # 01 → 09, ~45 s wall clock on 2 vCPU
```

* Inputs: the ZIP in the repo root. Nothing else.
* Outputs: `data/*.json` (all sorted), `data/figures/*.png`, `data/dataset.csv`.
* Every JSON carries `generated_at`; all other fields are order-stable, so two runs diff to
  exactly one line.
* Secrets are redacted **at parse time** (`02_manifest.py` masks credential-shaped meta-data
  before serialisation), so a data leak cannot happen downstream of the parser.

## 10.4 Limits, and the dynamic plan that closes them

| limit | consequence | dynamic follow-up |
|---|---|---|
| no bytecode decoding | cannot attribute a string to a method or a call graph | `jadx` + `FlowDroid`/`Soot` call-graph on the 952 first-party classes only |
| string prevalence ≠ behaviour | "pinning exists" ≠ "pinning is enforced on the payment host" | transparent proxy + objection/Frida `CertificatePinner` trace |
| no runtime | cannot see HCE APDU handling, WebView bridge authorisation, or ML input validation | rooted ARMv7 emulator, `adb shell am start` fuzzing of the 28 exported components with a null-payload corpus |
| 32-bit-only split | arm64 behaviour unknown | request the Play-delivered arm64 bundle and repeat §6 |
| shield is opaque | `bshield.dat` payload unanalysed | dump `JNI_OnLoad` under QEMU, memory-map the decompressed region, diff against public BShield samples |
| no server | fraud logic invisible | out of scope by design |

## 10.5 Threats to validity

* **Construct validity.** Vendor attribution by namespace prefix can over-count (e.g.
  `com.alibaba` mixes Griver with fastjson). §5.1 states the corroboration rule; the CSV keeps
  raw counts so a reader can re-aggregate.
* **Internal validity.** The `0x2146444e` block is reported as unrecognised, not as malicious.
  Any interpretation beyond "a signed region contains 691 undocumented bytes" would be speculation.
* **External validity.** One version, one mirror, one ABI split. Conclusions are version-pinned;
  the *method* transfers, the numbers do not.
* **Reproducibility.** Different `zipfile`/zlib versions do not change CRC or SHA-256, so hashes
  are stable; PNG figures may differ in anti-aliasing across matplotlib versions — the JSON is
  the artefact of record, the figures are renderings of it.

## 10.6 File map

```
tools/analyze/dexmeta.py        DEX table parser (stdlib only)
tools/analyze/arscmeta.py       resources.arsc chunk parser
tools/analyze/01_provenance.py  hashes, signing block, v1 PKCS#7, source stamp
tools/analyze/02_manifest.py    manifest, components, deep links, redaction
tools/analyze/03_dex.py         multidex census, obfuscation metrics
tools/analyze/04_sdks.py        vendor fingerprints, host inventory
tools/analyze/05_native.py      ELF hardening, JNI, build-path leaks
tools/analyze/06_signals.py     22 signal families, secret scan (masked)
tools/analyze/07_resources.py   per-split ARSC, assets, ML models
tools/analyze/08_assembly.py    concentration, rubric, findings register, CSV
tools/analyze/09_figures.py     16:9 plates
data/*.json                     results (committed)
video/                          TypeScript documentary pipeline
```
