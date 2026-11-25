# ZaloPay 11.17.0 — reproducible static-analysis study & video essay

This repository contains a **reproducible, evidence-first research pipeline** for the
`Zalopay-Thanh+toán+&+Tài+chính_11.17.0_APKPure.zip` artefact, plus a TypeScript
pipeline that turns the measured findings into a narrated MP4 video essay.

> Scope: static inspection of a publicly distributed package. No live account,
> credentials, payment flow, backend, or third-party service was accessed or tested.
> Findings are aggregated and redacted; no exploit, credential, or bypass material is
> published. See [`docs/ETHICS.md`](docs/ETHICS.md).

## Layout (grows with each PR checkpoint)

| Path | Purpose |
| --- | --- |
| `docs/RESEARCH_PLAN.md` | Questions, method, evidence standard, limitations |
| `tools/inventory.py` | Chain-of-custody hashing of the XAPK container and members |
| `tools/analyze_apk.py` | Low-memory DEX/manifest/resource scanner (stage 2) |
| `research/` | Machine-readable findings and generated report (stage 2) |
| `video/` | TypeScript scene renderer + FFmpeg encode pipeline (stage 3) |
| `release/` | Final MP4, transcript, BibTeX, reproducibility notes (stage 3) |

## Reproduce

```bash
python3 -m venv .venv && . .venv/bin/activate
pip install androguard imageio-ffmpeg
python tools/inventory.py --archive 'Zalopay-Thanh+toán+&+Tài+chính_11.17.0_APKPure.zip' --out research
python tools/analyze_apk.py --xapk <extracted.xapk> --out research
npm ci && npm run video
```

Every stage is pushed to this branch and recorded in a pull request checkpoint;
the PRs are deliberately not merged.
