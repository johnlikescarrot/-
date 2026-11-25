# P4 — Synthesis: architecture, measurements, and where this study sits

## 3.1 The "substrate shell" model
Zalopay 11.17.0 is not a monolithic payment app; it is a **shell over a substrate**. 97.3 % of its
110,677 classes are third-party (Google Play services/ML Kit, Alipay/Griver, Bouncy Castle,
Visa CBP, Facebook, AppsFlyer). First-party code (≈2,990 classes) owns orchestration and UX, not
primitives: it wires biometrics, NFC, eKYC and cashier flows onto vendor SDKs.

That has a testable consequence for security review: the *inherited* attack surface dominates.
27 native libraries, all hardened (25/27 with PIE+NX+BIND_NOW+canary), but 9 of them export no JNI
symbols at all and are loaded by `dlopen` indirection (`librcmefqonnx` ← `libljsfrcwmw`,
`liblytcuhe` ← `libohxbee`; `libtaibmcf`, `libfgcpkjvqe` pair with opaque `.dat` blobs of entropy 8.0).
Transparency, not hardening, is the weak axis.

## 3.2 Five metrics (reproducible: `python3 scripts/metrics.py` → `research/metrics.json`)
| Metric | Definition | Value |
|---|---|---|
| **SR** Substrate Ratio | third-party classes ÷ all classes | **0.973** (first-party 2,990 / 110,677) |
| **EDI** Environment Divergence Index | non-production hosts reachable only from the JS layer ÷ all non-production hosts | **0.391** (9 of 23) |
| **CBB** Cross-layer Bridge Breadth | React Native `NativeModules`, first-party share | **22 modules, 8 first-party** (36 %) |
| **NTS** Native Transparency Score | libs exporting ≥1 JNI symbol ÷ all libs | **10 / 27 = 0.370** |
| **PSS** Privilege Surface Score | permissions + exported components | **49 permissions, 30 exported components (14 activities)** |

EDI is the study's most actionable number: nine staging/sandbox hosts (`staging.zalopay.vn`,
`socialstg.zalopay.vn`, `sandbox.zalopay.vn`, `stgcdn/stcstg/stgcps.zalopay.com.vn`, `devcps`, …) exist
**only** in `main.jsbundle`. The Kotlin layer was cleanly environment-scoped; the JS layer was not.
Whether these hosts resolve is a server-side question and is deliberately **not** probed here.

## 3.3 Comparison with the literature (what is new)
Static Android malware/measurement work is mature: Drebin (NDSS 2014) and MaMaDroid (NDSS 2017) for
feature-based classification; AndroZoo (MSR 2017) for corpus construction; FlowDroid (PLDI 2014) for
taint analysis; MalloDroid (CCS 2012) and Sokolova et al. (2017) for TLS/pinning failures at scale;
Küster & Rensburg (2018) for static analysis of *banking* apps specifically. Ecosystem studies such as
Alrawi et al. (CCS 2019, "The Betrayal of Advertising") and Reardon et al. (USENIX Sec 2019, "50 Ways
to Leak Your Data") measure third-party SDK behaviour at population scale.

This study is deliberately **n = 1 but layer-complete**: one production payment artifact dissected
across four runtimes (Kotlin, React Native, mini-program, ELF) with per-host provenance. Population
studies tell you that inherited SDK risk exists; this design tells you *where in the stack* it lives
and how to attribute it to a layer. The gap the metrics target is the absence of **cross-layer**
indicators: SR, EDI, CBB and NTS are cheap to compute from one artifact, are defined on observable
strings/symbols (no decompiler needed), and are falsifiable — any third party can recompute them from
the hashes published here.

## 3.4 Threats to validity
1. **Repack provenance** — APKPure build; base APK unsigned at the JAR level. Absolute counts are
   valid for *this* artifact; mapping to the Play build is not claimed.
2. **No decompiler** — JADX/Ghidra unavailable, so SR/CBB are class/string-level, not call-graph-level.
3. **String provenance is line-addressed** — every quoted string is addressable as
   `research/dex-strings.txt:<line>`, which is deterministic for a given artifact hash but not a
   semantic locator (no method attribution). Label: *weak provenance*.
4. **Single ABI** — `armeabi-v7a` only (the XAPK ships no arm64 split), so NTS is computed on 32-bit
   builds; arm64 builds commonly differ.
5. **No dynamic execution** — nothing was run; no request was made to any discovered host.
6. **One-time snapshot** — version 11.17.0 (versionCode 1102758); EDI in particular is expected to drift.

## 3.5 Responsible disclosure
The PII-bearing `assets/client_tracking.json` (and the staging hosts above) are reported here without
reproduction of personal fields, and are suitable for a vendor notice. Recommended fixes: strip captured
payloads from release assets; gate environment branches at bundle time; ship arm64 with the same
hardening profile.
