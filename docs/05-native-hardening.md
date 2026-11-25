# 05 — Native layer and hardening posture

## 1. Inventory

27 shared objects, 57,452,124 bytes (54.8 MiB), all `armeabi-v7a`, all `ET_DYN`
(PIE), all stripped of section-name-rich debug data. The largest are not app code:

| Library | MiB | Function |
|---|---|---|
| `libtaibmcf.so` | 12.03 | BoringSSL/OpenSSL + vendor guard (random name) |
| `libmlkitcommonpipeline.so` | 6.61 | ML Kit infrastructure |
| `libmlkit_google_ocr_pipeline.so` | 6.47 | ML Kit text recognition |
| `libfgcpkjvqe.so` | 6.22 | BoringSSL + vendor guard (random name) |
| `libface_detector_v2_jni.so` | 5.14 | MediaPipe face detection |
| `libsqlcipher.so` | 3.39 | SQLCipher |
| `libbarhopper_v3.so` | 3.09 | ML Kit barcode |
| `libpicodet_ncnn.so` | 2.85 | PaddlePaddle PicoDet on NCNN |
| `libVFaceLib.so` | 1.87 | VNG liveness |
| `libljsfrcwmw.so` | 0.57 | apksig-derived integrity code |

The five randomly named libraries (`taibmcf`, `fgcpkjvqe`, `ljsfrcwmw`, `ohxbee`,
`a97d`) plus two same-sized siblings (`liblytcuhe.so`, `librcmefqonnx.so`,
178,628 B each, different hashes) form the vendor anti-tamper stack. Their names
are stable across the artefact but not meaningful — an analyst must identify them
by content.

## 2. Hardening matrix

| Property | Libraries | Notes |
|---|---|---|
| NX (non-exec stack) | 27/27 | `GNU_STACK` is RW; no executable stack |
| PIE (`ET_DYN`) | 27/27 | required on API 26+ |
| Full RELRO + `BIND_NOW` | 23/27 | the four Crashlytics libraries are partial/none |
| Stack canary | 25/27 | `__stack_chk_fail` present; missing in `libc++_shared.so`, `libcrashlytics-trampoline.so` |
| FORTIFY | 13/27 | mixed: ML Kit and Rive built without `_FORTIFY_SOURCE` |

Aggregate: the vendor's own security libraries are the best-hardened
(`libtaibmcf`, `libfgcpkjvqe`, `libljsfrcwmw` all full RELRO + canary + FORTIFY),
while third-party ML and Crashlytics objects account for every gap. That is a
normal supply-chain pattern, but it means "the app is hardened" is only true for
the subset the vendor compiles itself.

## 3. Exported symbols and JNI

`dynsym` exports total 442 functions across the corpus. Interesting sets:

* `libsecurity_lite.so` — Alipay SecurityGuard Lite:
  `Java_com_alipay_plus_security_lite_SecurityGuardLiteNative_encrypt`,
  `…_decrypt`, plus `AES_*`, `rsa_*_encrypt`, `aes_cbc_encrypt`. A complete
  JNI-addressable crypto surface.
* `libohxbee.so` — references
  `([Ljava/security/cert/X509Certificate;Ljava/lang/String;Landroid/net/http/X509TrustManagerExtensions;)V`
  and `vn.com.vng.bcell.BShieldApplication`: a **native trust-manager hook**.
  The referenced Java class does not exist in any DEX (see §4).
* `libtrueidlib.so` / `libVFaceLib.so` — 14 JNI entry points for liveness/eKYC.
* `librive-android.so` — 167 JNI exports (animation runtime).

## 4. Orphaned native references

`vn.com.vng.bcell.BShieldApplication`, `vn.ai.faceauth.sdk.presentation.domain.configs.Certificate`
and several `vn.ai.faceauth.*` descriptors appear as JNI signatures *inside
`libohxbee.so`* but have **no matching class in the 63,827-class census**. Two
explanations are consistent with the data: (a) the native library is a generic
build shared across VNG products and only part of it is wired here; (b) the
BShield Java layer is loaded from a separate dynamic DEX or resolved reflectively.
Either way the observable consequence for analysis is the same: the anti-tamper
entry point cannot be located by Java-side inspection.

## 5. Entropy and packers

Section-level entropy was computed for every library. `.text` entropy is 6.0–6.6
bits/byte for compiler output, and no library contains a > 4 KiB region above
7.9 bits — i.e. **no encrypted or compressed payload hiding inside `.text`**. The
two `.dat` assets (`taibmcf.dat`, `fgcpkjvqe.dat`, `bshield.dat`) are small
(8 KB, 776 KB, 929 B) and pair with the same-named libraries; they are guard
configuration, not packed code.

This is a useful negative result: the anti-tamper stack protects *execution*
(checks, hooks), not *confidentiality* of code. The libraries remain recoverable
with standard static tooling.

## 6. Compiler fingerprints

`.comment` sections identify the toolchains: NDK clang for ML Kit and MediaPipe,
GCC-era strings in `libsqlcipher.so`, and stripped comment sections in the vendor
guard libraries (a deliberate choice). Build IDs are present for 24/27 libraries
and are recorded in `analysis/report.json` for future diffing.

## 7. Practical implications

1. The native surface is **32-bit only**; on a 64-bit-only device this artefact
   cannot install, and on a 64-bit device the app would run 32-bit code with a
   4 GB address space and a different allocator profile.
2. The vendor guard is the most hardened code in the package and the least
   documented — the correct target for further work, and the one where the
   published artifact (this one) gives the least leverage.
3. ML Kit's two pipelines alone are 13.1 MiB, i.e. 24 % of native bytes, purely
   for OCR/barcode: an argument for `bundletool`-style conditional delivery if
   the vendor's Play pipeline does not already do it.
