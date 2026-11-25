# Comprehensive Static Reverse-Engineering Research Plan: ZaloPay 11.17.0

- **Target Archive**: `Zalopay-Thanh+toán+&+Tài+chính_11.17.0_APKPure.zip` (`vn.com.vng.zalopay`, versionCode `1102758`, versionName `11.17.0`, `minSdkVersion: 26`, `targetSdkVersion: 36`)
- **Primary Analysis Engine**: [`morluto/rea`](https://github.com/morluto/rea) (`rea-agents@6.10.1`) combined with `androguard`, `lief`, `pyelftools`, and `cryptography`.
- **Ethical & Methodological Boundaries**: Strictly passive, non-executing static architectural reverse engineering. No dynamic instrumentation, no tampering/bypass scripts, no exploitation, and no extraction of secrets or actionable vulnerabilities.

## Phases & PR Checkpoints

1. **Phase 0 (Baseline Infrastructure & PR Initialization)**: Reproducible tooling wrapper (`scripts/rea-cli.mjs`), Python binary-analysis environment (`requirements.txt`), `.gitignore`, and research plan.
2. **Phase 1 (Complete File Census & `morluto/rea` Artifact Graph)**: Exhaustive inventory, SHA-256 checksums, magic-byte classification, and Shannon entropy across 100% of files inside the outer ZIP, inner XAPK, all 5 APKs, and nested containers (`assets/android.1.zip`).
3. **Phase 2 (Manifest, Signing & Resource Architecture)**: APK v2/v3 signatures & Google Play Source Stamp, AndroidManifest permissions & exported component surface across all 5 split APKs, and `resources.arsc` localization/asset breakdown.
4. **Phase 3 (Multi-DEX Bytecode & SDK Ecology)**: Full structural analysis of all 20 DEX files (`classes.dex`–`classes19.dex` + `assets/audience_network.dex`), `baseline.prof`, Maven/Gradle `.properties`, third-party SDK taxonomy, and R8/ProGuard obfuscation telemetry.
5. **Phase 4 (Native ARM32 ELF Binary Audit & Companion Asset Correlation)**: `morluto/rea` hardening/dependency/export/note/initializer/entropy audit + LIEF/pyelftools section & symbol deep dive across all 27 `.so` libraries and their `assets/*.dat`/`*.ncnn.*` companions, plus JNI binding correlation.
6. **Phase 5 (Hybrid Super-App Mini-Program & On-Device ML Stack)**: Static AST/semantic analysis (`rea analyze-javascript-application`) of the Griver / Alipay+ mini-app JS bridge, `android.1.zip` container internals, and on-device computer vision / OCR / NCNN / TFLite models.
7. **Phase 6 (Publication Monograph & TypeScript Documentary MP4)**: Peer-review-grade monograph (`research/PAPER.md`, `research/COMPLETE_FILE_CATALOG.md`, `CITATION.cff`) and bespoke TypeScript 1080p60 MP4 documentary with neural narration and synthesized ambient audio (`video/`).
