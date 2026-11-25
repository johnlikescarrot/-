# 07 — Native libraries (28 .so triage)

27× ARM32 libs from `config.armeabi_v7a.apk` + 1 tiny non-ELF blob
`vn.com.vng.zalopay__trueidlib.so` (392 B) in base APK.

| file | bytes | arch | dynsyms | flags |
|---|---:|---|---|---|
| `config.armeabi_v7a__libVFaceLib.so` | 1,963,668 | ARM | 173 | PIE |
| `config.armeabi_v7a__liba97d.so` | 997,572 | ARM | 143 | PIE |
| `config.armeabi_v7a__libandroidx.graphics.path.so` | 7,252 | ARM | 8 | PIE |
| `config.armeabi_v7a__libbarhopper_v3.so` | 3,244,440 | ARM | 196 | PIE |
| `config.armeabi_v7a__libc++_shared.so` | 872,872 | ARM | 1742 | PIE |
| `config.armeabi_v7a__libcrashlytics-common.so` | 435,732 | ARM | 172 | PIE |
| `config.armeabi_v7a__libcrashlytics-handler.so` | 87,908 | ARM | 39 | PIE |
| `config.armeabi_v7a__libcrashlytics-trampoline.so` | 6,912 | ARM | 6 | PIE |
| `config.armeabi_v7a__libcrashlytics.so` | 94,540 | ARM | 52 | PIE |
| `config.armeabi_v7a__libface_detector_v2_jni.so` | 5,388,224 | ARM | 215 | PIE |
| `config.armeabi_v7a__libfgcpkjvqe.so` | 6,524,160 | ARM | 187 | PIE |
| `config.armeabi_v7a__libimage_processing_util_jni.so` | 20,380 | ARM | 28 | PIE |
| `config.armeabi_v7a__libimagepipeline.so` | 5,876 | ARM | 10 | PIE |
| `config.armeabi_v7a__libljsfrcwmw.so` | 600,296 | ARM | 16 | PIE |
| `config.armeabi_v7a__liblytcuhe.so` | 178,628 | ARM | 127 | PIE |
| `config.armeabi_v7a__libmlkit_google_ocr_pipeline.so` | 6,781,940 | ARM | 324 | PIE |
| `config.armeabi_v7a__libmlkitcommonpipeline.so` | 6,928,228 | ARM | 336 | PIE |
| `config.armeabi_v7a__libnative-filters.so` | 12,588 | ARM | 13 | PIE |
| `config.armeabi_v7a__libnative-imagetranscoder.so` | 341,072 | ARM | 143 | PIE |
| `config.armeabi_v7a__libohxbee.so` | 155,412 | ARM | 16 | PIE |
| `config.armeabi_v7a__libpicodet_ncnn.so` | 2,992,584 | ARM | 2324 | PIE |
| `config.armeabi_v7a__librcmefqonnx.so` | 178,628 | ARM | 127 | PIE |
| `config.armeabi_v7a__librive-android.so` | 3,225,636 | ARM | 5658 | PIE |
| `config.armeabi_v7a__libsecurity_lite.so` | 154,840 | ARM | 96 | PIE |
| `config.armeabi_v7a__libsqlcipher.so` | 3,555,080 | ARM | 599 | PIE |
| `config.armeabi_v7a__libtaibmcf.so` | 12,617,752 | ARM | 191 | PIE |
| `config.armeabi_v7a__libtrueidlib.so` | 79,904 | ARM | 79 | PIE |
| `vn.com.vng.zalopay__trueidlib.so` | 392 | None | 0 |  |

Key libs: `libsecurity_lite.so` (Alipay SecurityGuard — JNI
`Java_com_alipay_plus_security_lite_SecurityGuardLiteNative_{init,encrypt,decrypt,signRequest}`
found via strings, not dynamic symbols), `libsqlcipher.so`, `libtaibmcf.so`,
`libVFaceLib.so`, face/OCR/vision stack (`libface_detector_v2_jni`,
`libbarhopper_v3`, `libmlkit_google_ocr_pipeline`, `libpicodet_ncnn`),
`librive-android.so`, Crashlytics trio.

All ELF libs: PIE + RELRO + BIND_NOW, no executable stack.
`readelf --dyn-syms` shows no `Java_*` exports (all JNI via RegisterNatives or
stripped); strings-level JNI evidence in `evidence/native-strings.json`.

Raw: `reports/raw/native-report.json`.
