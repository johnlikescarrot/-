import type { ChapterSpec } from "./types.js";

export const CHAPTERS: ChapterSpec[] = [
  {
    id: "ch01_thesis",
    index: 1,
    tag: "01 · THESIS & PROVENANCE",
    title: "Anatomy of a Tier-1 Fintech Super-App",
    subtitle: "Static Reverse Engineering of ZaloPay v11.17.0 (targetSdk 36 / Android 16)",
    defaultDurationSec: 25,
    narrationText:
      "Modern mobile payment super-apps merge banking cryptography, edge artificial intelligence, and multi-engine extensibility inside a single binary. We present an exhaustive static reverse engineering study of ZaloPay version eleven point seventeen point zero, analyzing every packaged artifact across five split APKs, twenty Dalvik executables, twenty-seven ARMv7 native libraries, and one hundred twenty-four embedded assets.",
    captions: [
      {
        startRatio: 0.0,
        endRatio: 0.5,
        text: "Empirical static audit of ZaloPay v11.17.0 (vn.com.vng.zalopay, versionCode 1102758, Android 16 targetSdk 36).",
      },
      {
        startRatio: 0.5,
        endRatio: 1.0,
        text: "Complete coverage: 5 split APKs · 20 DEX files · 27 ARMv7 ELF libraries · 124 assets · 56 raw resources.",
      },
    ],
  },
  {
    id: "ch02_packaging",
    index: 2,
    tag: "02 · DISTRIBUTION & SIGNING",
    title: "Split-APK Delivery & Cryptographic Signing Parity",
    subtitle: "REA inspect-apk-signature & ASN.1 Audit Across All 7 XAPK Members",
    defaultDurationSec: 26,
    narrationText:
      "Inspecting the one hundred five megabyte XAPK bundle reveals five split packages bound by a shared RSA signer and Google Play SourceStamp provenance. Because the minimum SDK targets Android eight, the seventy-nine megabyte base APK drops legacy version-one JAR signatures entirely in favor of APK Signature Schemes v2 and v3, while localization splits isolate four hundred ninety-six Vietnamese keys from three hundred seventy English overrides.",
    captions: [
      {
        startRatio: 0.0,
        endRatio: 0.52,
        text: "Base APK drops v1 JAR signatures (v2 + v3 + Google Play SourceStamp); all 5 APKs match RSA signer CN=Vu Manh Hieu.",
      },
      {
        startRatio: 0.52,
        endRatio: 1.0,
        text: "resources.arsc segmentation: 14,680 base keys · 496 vi locale overrides (+34% vs 370 en) · 251 xhdpi density assets.",
      },
    ],
  },
  {
    id: "ch03_manifest",
    index: 3,
    tag: "03 · IPC & TRANSPORT POLICY",
    title: "AndroidManifest Surface & Trust Boundary Divergence",
    subtitle: "267 Declared Components, 7 Isolated Processes & Network Security Configuration",
    defaultDurationSec: 27,
    narrationText:
      "Decoding the binary manifest exposes two hundred sixty-seven components and forty-nine declared permissions spanning biometrics, NFC, and national digital ID queries for VNeID. Encouragingly, backups are disabled and zero content providers are exported. However, network security configuration explicitly permits cleartext traffic on its base config without declarative XML certificate pins, delegating all transport pinning enforcement to programmatic runtime code.",
    captions: [
      {
        startRatio: 0.0,
        endRatio: 0.52,
        text: "267 IPC components across 7 isolated worker processes (:lite1–:lite5, :taibmcf, :xcmzqsawpqoefcds); 0 exported providers.",
      },
      {
        startRatio: 0.52,
        endRatio: 1.0,
        text: "Trust divergence: allowBackup=false & debuggable=false, yet network_security_config.xml enables cleartextTrafficPermitted=true.",
      },
    ],
  },
  {
    id: "ch04_dex",
    index: 4,
    tag: "04 · MANAGED BYTECODE",
    title: "20-DEX Morphology & Quantitative R8 Obfuscation",
    subtitle: "9.13 Million Dalvik Instructions & 141-Artifact SBOM Census",
    defaultDurationSec: 28,
    narrationText:
      "Compiled under Kotlin two point two and Gradle eight point fourteen, nineteen primary DEX archives plus an embedded Audience Network dynamic DEX yield sixty thousand six hundred seventy-one primary classes and over nine point one million Dalvik instructions. Quantitative R8 profiling shows sixty-eight percent of class leaf names collapsed to one or two characters, yet method invocations still account for nearly nineteen percent of all bytecode operations.",
    captions: [
      {
        startRatio: 0.0,
        endRatio: 0.52,
        text: "19 primary DEXs (64.84 MB, 8.71M instructions) + dynamic assets/audience_network.dex (4.86 MB, 420k instructions).",
      },
      {
        startRatio: 0.52,
        endRatio: 1.0,
        text: "R8 obfuscation index: 68.19% short leaf classes · 48.14% obfuscated root packages · 2,026 native JNI methods in DEX.",
      },
    ],
  },
  {
    id: "ch05_hybrid",
    index: 5,
    tag: "05 · TRI-ENGINE SUPER-APP",
    title: "Three Coexisting Execution & UI Paradigms",
    subtitle: "Jetpack Compose + Embedded React Native Metro + Ant Group Griver Mini-Apps",
    defaultDurationSec: 27,
    narrationText:
      "Static asset unpacking exposes a striking tri-engine architecture inside one financial app. Alongside five hundred four Jetpack Compose classes, assets dot zip hides a three point six megabyte React Native Metro bundle comprising two thousand three hundred forty-six modules, while Ant Group's Griver runtime powers five thousand one hundred thirty-six mini-program classes across five isolated processes with signed A-M-R bundles.",
    captions: [
      {
        startRatio: 0.0,
        endRatio: 0.5,
        text: "Engine 1: Kotlin 2.2.10 + Jetpack Compose 1.7.2 (504 classes) | Engine 2: React Native main.jsbundle (2,346 Metro modules).",
      },
      {
        startRatio: 0.5,
        endRatio: 1.0,
        text: "Engine 3: Alipay+ Griver/Ariver (5,136 classes, 3 signed .amr TAR bundles, 5,361 JS bridge AST nodes parsed by REA).",
      },
    ],
  },
  {
    id: "ch06_ai_hce",
    index: 6,
    tag: "06 · EDGE AI & CRYPTOGRAPHIC HCE",
    title: "On-Device eKYC Vision & Contactless Payment Hardware",
    subtitle: "Four Vision Runtimes, EMVCo Visa HCE & X25519 SmartOTP Vault",
    defaultDurationSec: 27,
    narrationText:
      "For offline identity verification and tap-to-pay, ZaloPay integrates four distinct computer vision stacks: Tencent NCNN running a one hundred seventy-two layer FastestDet detector, TensorFlow Lite object labeling, Google ML Kit OCR, and OpenCV facial alignment. Payment and authentication bind directly to Visa Cloud-Based Payments via EMVCo host card emulation and an X25519 SmartOTP cryptographic ceremony in libtrueidlib.",
    captions: [
      {
        startRatio: 0.0,
        endRatio: 0.52,
        text: "Four embedded vision pipelines: Tencent NCNN FastestDet (172 layers), TFLite, Google ML Kit, and OpenCV VFaceLib.",
      },
      {
        startRatio: 0.52,
        endRatio: 1.0,
        text: "EMVCo HCE (2PAY.SYS.DDF01 + Visa AIDs A000000003*) paired with X25519 SmartOTP vault & anti-hooking probes.",
      },
    ],
  },
  {
    id: "ch07_native_elf",
    index: 7,
    tag: "07 · NATIVE ELF & HARDENING",
    title: "Obfuscated Shields, C++ Runtime Twins & ELF Posture",
    subtitle: "27 ARMv7 Shared Libraries (57.45 MB) Audited via REA, LIEF & Capstone",
    defaultDurationSec: 29,
    narrationText:
      "Dissecting fifty-seven megabytes of native ARMv7 code uncovered two multi-megabyte obfuscated enclaves, libtaibmcf and libfgcpkjvqe, backed by near-maximum eight-bit entropy encrypted assets, plus two identically sized C-plus-plus runtime twins isolating VFace and Griver from shared STL state. Across all twenty-seven binaries, REA verifies one hundred percent non-executable stack enforcement, while every RELRO omission traces strictly to Firebase Crashlytics.",
    captions: [
      {
        startRatio: 0.0,
        endRatio: 0.52,
        text: "libtaibmcf.so (12.62 MB, 132 inits) & libfgcpkjvqe.so (6.52 MB) + twin 178,628 B C++ runtimes (liblytcuhe vs librcmefqonnx).",
      },
      {
        startRatio: 0.52,
        endRatio: 1.0,
        text: "REA Hardening Scorecard: 27/27 NX · 25/27 Stack Canary · 23/27 RELRO (all 4 RELRO gaps belong to libcrashlytics*.so).",
      },
    ],
  },
  {
    id: "ch08_synthesis",
    index: 8,
    tag: "08 · SYNTHESIS & REPRODUCIBILITY",
    title: "Defensive Recommendations & Scholarly Artifacts",
    subtitle: "Reproducible Open Toolchain · Citation & Verification Boundaries",
    defaultDurationSec: 23,
    narrationText:
      "Three engineering upgrades emerge from this evidence: tighten network security config to forbid ambient cleartext traffic, adopt declarative TLS certificate pinning alongside native checks, and rebuild native crash telemetry with full RELRO protection. Complete SHA-256 verified REA evidence contracts, static Python scripts, and our scholarly manuscript are archived for full academic reproducibility.",
    captions: [
      {
        startRatio: 0.0,
        endRatio: 0.52,
        text: "Actionable hardening: enforce cleartextTrafficPermitted=false, add XML pin-set backup, and enable RELRO on Crashlytics NDK.",
      },
      {
        startRatio: 0.52,
        endRatio: 1.0,
        text: "Reproducible corpus: docs/ZALOPAY_11.17.0_REVERSE_ENGINEERING_PAPER.md · research/evidence/ · morluto/rea v6.10.1.",
      },
    ],
  },
];
