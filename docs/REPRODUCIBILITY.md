# Reproducibility

## Environment

* Python 3.11, Node.js 22.22.3, TypeScript 5.9, ffmpeg 9.0.2 (libx264 + AAC), OpenSSL 3.0.
* No network access to the analysed application's backends at any point.
* The immutable corpus is `Zalopay-Thanh+toán+&+Tài+chính_11.17.0_APKPure.zip`
  (SHA-256 in `analysis/inventory.json`).

## One-command pipeline

```bash
python3 -m pip install --break-system-packages numpy pillow imageio imageio-ffmpeg
python3 tools/rea_lite/run_all.py                 # phases 0-4 -> analysis/*.json
cd tools/film && npm ci && npm run build && npm run film   # phases 6-7 -> media/*.mp4
```

`run_all.py` calls each reader in order; every reader is idempotent and reads only from the
corpus plus `work/xapk/` (git-ignored), writing only small JSON evidence into `analysis/`.

## REA tool usage

We used the upstream tool from [morluto/rea](https://github.com/morluto/rea) directly on the
JavaScript layer extracted from the APK:

```bash
# The embedded JS bundle + four JS bridge files are unpacked into work/jsapp/
npx -y rea-agents@6.5.0 analyze-javascript-application "$(pwd)/work/jsapp" --json \
  > work/rea_cli_output.json
```

`rea-agents@6.10.1` refuses Node 22.22.3 (`requires >=22.23.4`), so we pinned 6.5.0, whose
engine range (`^22.19.0`) accepts the available runtime. REA returned an immutable Evidence
record with `evidence_id`, a hashed application graph, AST-derived module relationships and an
explicit limitations list. Its compact, citable digest is `analysis/rea_javascript.json`; the
full 2.58 MB evidence is regenerable with the command above (its SHA-256 is recorded there).

Our own `tools/rea_lite/` readers implement a compatible **offline artifact-reader** model
(`inspect-artifact` / `extract-artifact` style evidence: offsets, digests, coverage limits)
for the Android-specific binary formats REA does not itself parse (AXML, ARSC, DEX, ELF, APK
Signing Block).

## Determinism

* All readers are pure functions of the corpus bytes; no timestamps or randomness enter the
  evidence (the film's procedural audio uses a fixed PRNG seed).
* Every JSON is written with sorted keys and stable indentation.
* The film renders from `analysis/report.json`, so re-running the analysis changes the film
  deterministically.

## Out of scope

Dynamic analysis, instrumentation, traffic capture, decryption of opaque/protected payloads,
signature bypass, and any publication of config values, keys, tokens or identifiers.
