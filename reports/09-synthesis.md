# 09 — Synthesis: architecture, risks, reproducibility

Architecture: multi-split XAPK (base + ABI + density + 2 locales), 20 DEX
(63,827 classes / 378,040 methods), heavyweight third-party SDK mesh
(Google, BouncyCastle, Alibaba/Griver/Alipay mini-app runtime, Meta Audience
Network, Visa, VNPay, AppsFlyer, ML Kit OCR, TrueID/FPT eKYC), ARM32-only
native split with SecurityGuard lite + SQLCipher + face/OCR stack.

Risk notes (static-only, to verify dynamically): global cleartext permitted
with no pin-set in NSC while pinning strings exist in code (policy/code gap);
broad FileProvider cache paths; exported test/preview activities; HCE AID
surface; signed-config envelope present (RSA-2048) — verify trust chain.

Reproducibility: all hashes in `00-inventory`; tool versions pinned in
`RESEARCH_PLAN.md`; raw JSON in `reports/raw/`; scripts in `tools/`;
REA provider gaps documented in `08-js-bridge`.

Next: TypeScript video (`video/`) rendering these findings to MP4.
