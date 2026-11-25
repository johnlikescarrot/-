# Zalopay 11.17.0 — Static Reverse-Engineering Study & Research Film

This repository contains:

| Path | What it is |
|---|---|
| `analysis/` | Full static reverse-engineering of the XAPK: pure-Python AXML + DEX parsers, machine-readable `summary.json`, decoded `AndroidManifest.xml`, and the written study `REPORT.md`. |
| `video/` | A TypeScript-built research documentary (MP4) presenting the study, with a cited companion paper (`video/paper/`). |
| `artifacts/` | Rendered deliverables (MP4, poster, citation BibTeX). |

> **Scope & ethics.** This is *static analysis of package structure* (metadata, component
> inventory, libraries, resources). It contains no source code, keys, secrets, or user data,
> and no exploitation instructions. See the ethics section of `analysis/REPORT.md`.

## Reproduce the analysis

```bash
python3 tools/decode_manifest.py    # AXML -> XML (no Java/apktool required)
python3 tools/analyze.py            # -> analysis/summary.json
```

## Reproduce the film

```bash
cd video && npm ci && npm run render
```
