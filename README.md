# Static Reverse Engineering & Architectural Study of ZaloPay v11.17.0 (XAPK Bundle)

An end-to-end, artifact-bound reverse engineering and binary composition study of **`Zalopay-Thanh+toán+&+Tài+chính_11.17.0_APKPure.zip`**, combining [REA (`morluto/rea`)](https://github.com/morluto/rea) static inspection modules with custom Python (`androguard`, `lief`, `pyelftools`, `capstone`, `cryptography`) and TypeScript visualization pipelines.

> **Responsible Research Scope:** This repository documents defensive software composition, packaging anatomy, manifest attack-surface taxonomy, native ELF binary hardening telemetry, SDK provenance, and architectural security engineering patterns from static inspection of a publicly distributed Android package. It intentionally excludes proprietary source dumps, credential/secret extraction, and any exploit or security-bypass tooling.

## Repository Structure

- `research/scripts/` — Reproducible static extraction, REA runner, DEX/Manifest/Resources/Native ELF/Asset/Signature analyzers.
- `research/evidence/` — Structured, verifiable static analysis evidence JSONs and artifact inventories (all 7 XAPK members, all 5 APKs, all native `.so` libraries, all `classes*.dex`, `AndroidManifest.xml`, `resources.arsc`, and signing certificates).
- `docs/` — Comprehensive academic-style technical report (`ZALOPAY_11.17.0_REVERSE_ENGINEERING_PAPER.md` + BibTeX citations).
- `video-studio/` — TypeScript + Skia Canvas (`@napi-rs/canvas`) + D3 + FFmpeg deterministic 1080p publication video synthesizer with mastered narration audio.
- `deliverables/` — Rendered MP4 presentation and reproducible evidence summary.
