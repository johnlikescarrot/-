# 06 · Native code forensics

> Script: `05_native.py` · Data: `data/05-native.json`

## 6.1 Corpus

27 shared objects, **57 452 124 B** (24 MB compressed inside `config.armeabi_v7a.apk`), 32-bit
ARM EABI5, all `ET_DYN`.

| library | bytes | JNI exports | dynsym | RELRO | NX | stripped |
|---|---:|---:|---:|:---:|:---:|:---:|
| `libtaibmcf.so` | 12 617 752 | 0 (only `JNI_OnLoad`) | 2 | ✓ | ✓ | ✓ |
| `libmlkitcommonpipeline.so` | 6 928 228 | 38 | 42 | ✓ | ✓ | ✓ |
| `libmlkit_google_ocr_pipeline.so` | 6 781 940 | 33 | 37 | ✓ | ✓ | ✓ |
| `libfgcpkjvqe.so` | 6 524 160 | 0 | 2 | ✓ | ✓ | ✓ |
| `libface_detector_v2_jni.so` | 5 388 224 | 6 | 10 | ✓ | ✓ | ✓ |
| `libsqlcipher.so` | 3 555 080 | 0 | 480 | ✓ | ✓ | ✓ |
| `libbarhopper_v3.so` | 3 244 440 | 8 | 12 | ✓ | ✓ | ✓ |
| `librive-android.so` | 3 225 636 | **167** | 5 948 | ✓ | ✓ | ✓ |
| `libpicodet_ncnn.so` | 2 992 584 | 8 | 2 648 | ✓ | ✓ | ✓ |
| `libVFaceLib.so` | 1 963 668 | 4 | 4 | ✓ | ✓ | ✓ |
| `liba97d.so` | 997 572 | 0 | 64 | ✓ | ✓ | ✓ |
| `libc++_shared.so` | 872 872 | 0 | 2 337 | ✓ | ✓ | ✓ |
| `libljsfrcwmw.so` | 600 296 | 0 | 1 | ✓ | ✓ | ✓ |
| `libcrashlytics-common.so` | 435 732 | 0 | 3 | ✗ | ✓ | ✓ |
| `libnative-imagetranscoder.so` | 341 072 | 0 | 299 | ✓ | ✓ | ✓ |
| `liblytcuhe.so` / `librcmefqonnx.so` | 178 628 each | 0 | 303 | ✓ | ✓ | ✓ |
| `libohxbee.so` | 155 412 | 0 | 1 | ✓ | ✓ | ✓ |
| `libsecurity_lite.so` | 154 840 | 4 | 97 | ✓ | ✓ | ✓ |
| `libcrashlytics*.so` (3) | 8 812–94 540 | 0 | 2–8 | ✗ | ✓ | ✓/✗ |
| `libtrueidlib.so` | 79 904 | 10 | 40 | ✓ | ✓ | ✓ |
| `libimage_processing_util_jni.so` | 20 380 | 6 | 6 | ✓ | ✓ | ✓ |
| `libnative-filters.so`, `libandroidx.graphics.path.so`, `libimagepipeline.so` | 5 876–12 588 | 0 | 1 | ✓ | ✓ | ✓ |

## 6.2 Hardening scorecard

| control | result |
|---|---|
| PIE (`ET_DYN`) | **27 / 27** |
| NX stack (`PT_GNU_STACK` non-exec) | **27 / 27** |
| `BIND_NOW` (`DF_BIND_NOW` / `DF_1_NOW`) | **27 / 27** |
| `PT_GNU_RELRO` | 23 / 27 (all four Crashlytics libs lack it or lack RELRO coverage) |
| stripped (no `.symtab`) | 26 / 27 (`libcrashlytics-trampoline.so` keeps one) |
| debug info | 1 / 27 |
| TEXTREL | **0 / 27** — no writable-code segments |
| full RELRO + BIND_NOW | 23 / 27 |

This is a *well-built* native corpus by Android standards. The four non-RELRO libraries are
Google Crashlytics' NDK shims — a known upstream trait, not a ZaloPay regression.

**16 KB page-size readiness (Android 15+):** entries are DEFLATE-compressed inside the split and
`android:extractNativeLibs="true"` is set, so the loader extracts to a filesystem with the
platform's page size. Alignment of uncompressed entries (the usual 16 KB failure mode) therefore
does not apply to this bundle — but neither does the memory-mapping optimisation that makes
uncompressed libs faster to load. It is a legitimate trade-off, worth stating explicitly because
most "16 KB" audits grep for `.so` alignment and would produce a false positive here.

## 6.3 Build-path leakage

| library | leaked paths | example |
|---|---:|---|
| `libtaibmcf.so` | **67** | `/home/cpu12313/Documents/BshieldAzure/agent/_work/1/s/libs/boringssl/crypto/asn1/a_bitstr.cc` |
| `libfgcpkjvqe.so` | **67** | `/home/vsts/work/1/s/libs/boringssl/crypto/asn1/a_bitstr.cc` |
| `libcrashlytics-common.so` | 85 | `/home/runner/work/firebase-android-sdk/…` (upstream Google) |
| `libmlkit*.so` | 7 | `/home/build/nonconf/google3/…` (upstream Google) |
| `libsqlcipher.so`, `libsecurity_lite.so`, `libtrueidlib.so`, others | 1–3 | NDK `buildbot` paths (upstream) |

The interesting ones are the two shield libraries: same BoringSSL source tree, **two different
build agents**, which explains why `liblytcuhe.so` and `librcmefqonnx.so` have identical sizes
(178 628 B) yet differ in 8 799 bytes — same code, different build, different randomized name.
Two conclusions:

1. The packer is **Bangcle B-Shield** (asset `bshield.dat`, CI project `BshieldAzure`, Azure
   Pipelines `vsts` agent) — a commercial native shield widely deployed in Vietnamese and
   Chinese fintech.
2. Building the same SDK twice with different agents produces *different* bytes, which is why
   hash-based signature detection of shields fails and why vendors randomise library names.

## 6.4 Anatomy of the shield

`libtaibmcf.so` — 12.6 MB, 2 dynamic symbols (`JNI_OnLoad`, `JNI_OnUnload`), everything else
hidden. Imported surface tells its job:

```
AAssetManager_fromJava/open/read/close   → loads payload from assets (bshield.dat)
deflateInit2_/inflateInit2_              → zlib: compressed payload
__system_property_get, sched_setaffinity, getxattr, dl_iterate_phdr, mprotect
__stack_chk_fail, __memcpy_chk, __strlen_chk
```

That import set is a textbook loader: read a compressed asset, decompress in memory, `mprotect`
to make a region executable, walk loaded libraries (`dl_iterate_phdr`) to inspect the process,
read system properties for root/emulator heuristics, and pin threads to cores for timing checks.
`JNI_OnLoad` — not `java.lang.ClassLoader` — means **the payload never touches the DEX format**,
so decompilers see nothing.

`assets/bshield.dat` is only 929 B: that is the *bootstrap*, not the payload. The 12.6 MB library
is the payload's runtime.

## 6.5 JNI surface: 284 exported `Java_*` symbols

Grouped by owner:

| owner | symbols | examples |
|---|---:|---|
| Rive | 167 | `Java_app_rive_runtime_kotlin_core_Artboard_cppStateMachineByIndex` |
| ML Kit pipeline | 71 | `...NativePipelineImpl_receiveRgbFrame`, `...AndroidSystemDetectionJNI_GetNNAPIInfo` |
| TrueID vault | 10 | `SmartOTPHelper_vaultCheckIntegrity`, `_vaultX25519Pubkey`, `_vaultVerifyTxDigest` |
| Barhopper (barcode) | 8 | `BarhopperV3_recognizeBitmapNative` |
| PicoDet/NCNN | 8 | `PicoDetJNI_detect` |
| Face detector v2 | 6 | `FaceDetectorV2Jni_detectFacesImageByteBufferMultiPlanesJni` |
| CameraX | 6 | `ImageProcessingUtil_nativeRotateYUV` |
| VFaceLib | 4 | `VFaceLib_00024Companion_faceAlignment` |
| Alipay SecurityGuard Lite | 4 | `SecurityGuardLiteNative_signRequest`, `_encrypt`, `_decrypt` |

Two of these cross the trust boundary in a way worth flagging: `SecurityGuardLiteNative_signRequest`
means **request signing happens in native code**, and `SmartOTPHelper_vaultX25519Pubkey` means the
eKYC SDK performs key agreement inside its vault. Both are exactly where you *want* signing to
live (out of reach of a hooked Java layer), and both mean a Java-only review cannot verify them.

## 6.6 What a native reviewer should do next

1. `strings -n 6` per library and diff the two shield builds → confirms the rename-and-rebuild
   hypothesis quantitatively (§6.3).
2. Trace `JNI_OnLoad` under QEMU-ARM with `strace` for `openat` on `assets/bshield.dat` — the
   loader's file access pattern is observable without defeating the shield.
3. Enumerate `__system_property_get` call sites to recover the *exact* root/emulator heuristics
   the packer uses; that is the difference between "detection exists" (§7.5) and "detection is
   effective".
