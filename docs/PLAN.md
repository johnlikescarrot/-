# Execution plan & PR checkpoint protocol

The corpus is a **nested container chain**, not a plain APK:

```
Zalopay-...APKPure.zip            (1 entry, deflate)
└── Zalopay-...APKPure.xapk      (7 entries, stored)   ← APKPure XAPK v2 bundle
    ├── vn.com.vng.zalopay.apk   (6162 entries)         ← base APK
    ├── config.armeabi_v7a.apk   (32 entries)           ← ABI split
    ├── config.en.apk            (6 entries)            ← locale split
    ├── config.vi.apk            (6 entries)            ← locale split
    ├── config.xhdpi.apk         (258 entries)          ← density split
    ├── icon.png                 (23841 bytes)
    └── manifest.json            (2710 bytes)
```

"All files" therefore means **all ~6,465 ZIP members across 7 nested archives**, plus the ZIP
central directories, the APK Signing Blocks and the binary payload formats inside the members
(AXML, DEX, ARSC, ELF, protobuf/TFLite/Rive/ncnn/JSON). Each layer gets its own reader.

## Phases (each ends with a pushed commit → PR synchronisation)

| # | Phase | Evidence produced | PR action |
| --- | --- | --- | --- |
| 0 | Scaffold + corpus inventory | `analysis/inventory.json`, `analysis/inventory_summary.json` | open **PR #1** |
| 1 | Binary format readers: AXML + resources.arsc | `analysis/manifest.json`, `analysis/resources.json` | push commit to PR |
| 2 | DEX structural analysis (20 dex files) | `analysis/dex.json`, `analysis/supply_chain.json` | push commit to PR |
| 3 | Native ELF analysis (27 `.so`), APK signing blocks, certificates metadata | `analysis/elf.json`, `analysis/signing.json` | push commit to PR |
| 4 | Asset/config/string statistics with redaction | `analysis/assets.json`, `analysis/strings.json`, `analysis/report.json` | push commit to PR |
| 5 | Citable preprint + citation metadata + captions/narration script | `paper/*`, `media/script.md`, `media/subtitles.*` | push commit to PR |
| 6 | TypeScript film engine (renderer, scenes, audio synthesiser, muxer) | `tools/film/*` | push commit to PR |
| 7 | Render + encode + QC of final `MP4` + poster | `media/zalopay-rea.mp4`, `media/poster.png`, `docs/QC.md` | push commit to PR |

Because every phase is pushed, the run is resumable: if the agent hits a tool-call limit, the
next run simply reads the last commit on the branch and continues at the next phase. PRs are
**not merged**, per the request.

## Why one PR with many commits (and not N parallel PRs)

GitHub permits only one *open* pull request per `(head branch, base branch)` pair, and this
session is bound to a single head branch. The robust interpretation of "sync everything into PRs"
is therefore: **open the PR at phase 0 and push every subsequent phase as a commit**, so the PR
is a complete, ordered, reviewable record of the entire operation. Additional PRs are opened at
major milestones where GitHub permits it (see `docs/PR_LOG.md`).
