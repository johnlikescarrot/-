# Zalopay 11.17.0 — Deep Reverse-Engineering Study + Academic Video

> **Target:** `Zalopay-Thanh+toán+&+Tài+chính_11.17.0_APKPure.zip` → XAPK `vn.com.vng.zalopay` v11.17.0 (versionCode 1102758)
> **Method:** REA ([morluto/rea](https://github.com/morluto/rea), `rea-agents` v6.10.1) + Jadx + Apktool + Androguard + custom DEX/ELF analysis
> **Deliverable:** TypeScript-generated academic video (MP4, 1080p, H.264/AAC) + full reproducible research corpus

## Status: IN PROGRESS (PR-synced)

All work is synchronized to this branch's pull request incrementally, so progress survives
tool-call limits. See `docs/00-PLAN.md` for the phased plan and `docs/PROGRESS.md` for the log.

## Layout

| Path | Contents |
|---|---|
| `docs/` | Plan, method, progress log, bibliography |
| `research/` | Machine-readable findings (JSON/CSV/MD) — every claim traceable to evidence |
| `video/` | TypeScript video project (`npm run build` → `video/out/zalopay-study.mp4`) |
| `work/` | Local extraction workspace (git-ignored; reproducible via `scripts/extract.py`) |
| `scripts/` | Extraction / analysis automation |

## Quick start

```bash
python3 scripts/extract.py          # unzip XAPK → work/extracted/
pip install -r requirements.txt     # androguard etc.
npm --prefix video ci && npm --prefix video run build
```

## Ethics & scope

Static analysis only. No credential extraction, no bypass of payment security, no redistribution
of the APK. Findings describe architecture and publicly observable behavior; sensitive strings
(keys, endpoints with secrets) are redacted in the report. This is defensive / academic research.
