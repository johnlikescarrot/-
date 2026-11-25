# ZaloPay 11.17.0 — Evidence-Bounded Static Reverse Engineering & Cinematic Research Film

A fully reproducible, **offline, static-only** reverse-engineering study of every byte container in
`Zalopay-Thanh+toán+&+Tài+chính_11.17.0_APKPure.zip`, plus a TypeScript-built research film
(`media/zalopay-rea.mp4`) designed to be citable, verifiable and re-runnable.

> **Status / checkpointing.** Every phase of this work is committed and pushed to the branch
> `arena/e160031d-repo` and therefore synchronised into a pull request. No phase depends on
> un-pushed local state, so an interrupted agent run can always resume from the last PR commit.
> See [`docs/PLAN.md`](docs/PLAN.md).

## What is inside this repository

| Path | Purpose |
| --- | --- |
| `tools/rea_lite/` | Offline artifact readers (ZIP/XAPK/APK, AXML, DEX, ARSC, ELF, APK signing block, strings). A small, auditable analogue of REA's `inspect-artifact` / `extract-artifact` evidence model. |
| `analysis/` | Machine-readable evidence: full per-entry inventory (CRC, SHA-256, entropy, compression), parsed manifests, DEX/ARSC/ELF/signature reports, aggregated `report.json`. |
| `paper/` | Citable preprint (`paper.md`, `CITATION.cff`, BibTeX), with numbered claims, tables, limitations and a redaction policy. |
| `docs/` | Methodology, ethics/scope, reproducibility, checkpoint/PR log. |
| `tools/film/` | **TypeScript** film engine: software rasteriser, timeline/scene graph, procedural audio synthesiser, ffmpeg muxer. |
| `media/` | Final `MP4` (H.264/AAC), poster frame, WebVTT/SRT captions, narration script. |

## Scope and ethics (read this first)

* Static analysis only. No execution, no instrumentation, no runtime hooking, no network calls to
  the app's backends, no bypass or circumvention of integrity/anti-tamper controls.
* **Redaction by construction**: configuration values, keys, tokens, identifiers and endpoints are
  never copied into this repository. Only shapes, lengths, types, counts and salted digests are
  published. See [`docs/ETHICS.md`](docs/ETHICS.md).
* Findings describe architecture and build provenance, not vulnerabilities, credentials or attack
  surfaces. Nothing here enables fraud, tampering or unauthorised access.

## Quick reproduction

```bash
python3 -m pip install --break-system-packages numpy pillow imageio imageio-ffmpeg
python3 tools/rea_lite/run_all.py              # regenerates analysis/*.json
cd tools/film && npm ci && npm run build && npm run film   # regenerates media/*.mp4
```

The original ZIP stays the immutable corpus. All large intermediate files live in the ignored
`work/` directory; every committed artefact is either source, evidence (small JSON) or the final film.

## Citation

If you use the dataset, the parsers or the film, cite `paper/CITATION.cff`. A DOI/Google Scholar
citation count cannot be guaranteed by any technical means; what this project *can* guarantee is
open methods, hashed evidence, versioned artefacts and a machine-readable citation record.
