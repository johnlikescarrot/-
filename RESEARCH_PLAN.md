# Research plan — ZaloPay 11.17.0 XAPK reverse engineering

## Corpus
`Zalopay-Thanh+toán+&+Tài+chính_11.17.0_APKPure.zip` → 1 XAPK → 5 APKs + manifest + icon.
100% entry coverage: every file enumerated by SHA256 (reports/00).

## Toolchain (pinned)
- Python 3 + androguard 4.1.4 (DEX/AXML/ARSC), stdlib zipfile/hashlib
- rea-agents@6.5.0 (JS app graph; latest needs newer Node) — morluto/rea methodology
- binutils readelf/strings/nm/objdump; openssl
- Node 22.22.3 / npm 10.9.8 (sandbox)
- Video: TypeScript + pure-TS PNG/WAV synthesis + ffmpeg_mux to MP4

## Phases
1. ✅ Inventory/hash/metadata (stage1) 2. ✅ Manifest/NSC/assets/strings (stage2)
3. ✅ DEX triage lightweight (stage3b; full AnalyzeAPK OOM) 4. ✅ Native triage (stage4)
5. ✅ JS/HTML + REA evidence 6. ✅ Config deep-dive 7. ▶ TypeScript MP4 video
8. Final citation package (BibTeX, transcript, chapters)

## Sync policy
Every phase commits + pushes to `arena/753157fb-repo` and syncs the open PR.
PRs are opened, never merged, without waiting for approval.
