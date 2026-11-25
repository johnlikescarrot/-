# Research Plan — Zalopay 11.17.0 (vn.com.vng.zalopay)

## Phases
1. **P0 Scaffold + PR** (this commit): repo layout, PR opened, progress log started.
2. **P1 Triage**: XAPK manifest, split APKs, hashes, signing cert, SDK levels, permissions, components (activities/services/receivers/providers), exported surface.
3. **P2 Static deep-dive**: AndroidManifest (AXML), resources.arsc, DEX inventory (classes, packages, SDKs), native libs (.so) per ABI, assets (ML models, configs, Griver/miniapp), network endpoints & SDK fingerprints from strings.
4. **P3 REA integration**: install `rea-agents`, run Android/Jadx bridge analysis, record evidence + limitations per REA contract.
5. **P4 Synthesis**: architecture diagram, attack-surface & privacy analysis, comparison with literature, bibliography (BibTeX) for citability.
6. **P5 Video (TypeScript)**: script → slides/animation → narration (TTS) → mix → MP4 (1080p H.264/AAC, chapters, subtitles). Target: a self-contained, citable academic video essay.
7. **P6 Verification**: ffprobe checks, transcript, captions, checksum manifest, final PR update.

## PR-sync policy
Every phase ends with `git add -A && git commit && git push` on this branch; the PR updates automatically.
No merge requested. If the agent hits a tool-call limit, the next session resumes from `docs/PROGRESS.md`.
