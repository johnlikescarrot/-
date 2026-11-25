# Static census

| Metric | Value |
| --- | --- |
| APK splits | 5 |
| DEX files | 20 |
| Classes | 63,827 |
| Methods | 287,480 |
| Fields | 286,224 |
| String constants | 242,266 / 404,914 in pool |
| Instructions decoded | 9,258,758 |
| Invoke edges | 1,729,915 |
| DEX bytes | 66.47 MiB |
| Native libraries | 27 |
| Native bytes | 54.79 MiB |
| Assets | 124 |
| Resource entries | 16,864 |

## Native hardening

| Library | MiB | NX | RELRO | Canary | FORTIFY | JNI exports |
| --- | --- | --- | --- | --- | --- | --- |
| libtaibmcf.so | 12.03 | yes | full | yes | yes | 0 |
| libmlkitcommonpipeline.so | 6.61 | yes | full | yes | no | 38 |
| libmlkit_google_ocr_pipeline.so | 6.47 | yes | full | yes | no | 33 |
| libfgcpkjvqe.so | 6.22 | yes | full | yes | yes | 0 |
| libface_detector_v2_jni.so | 5.14 | yes | full | yes | no | 6 |
| libsqlcipher.so | 3.39 | yes | full | yes | yes | 0 |
| libbarhopper_v3.so | 3.09 | yes | full | yes | no | 8 |
| librive-android.so | 3.08 | yes | full | yes | no | 167 |
| libpicodet_ncnn.so | 2.85 | yes | full | yes | yes | 8 |
| libVFaceLib.so | 1.87 | yes | full | yes | yes | 4 |
| liba97d.so | 0.95 | yes | full | yes | no | 0 |
| libc++_shared.so | 0.83 | yes | full | no | no | 0 |
| libljsfrcwmw.so | 0.57 | yes | full | yes | yes | 0 |
| libcrashlytics-common.so | 0.42 | yes | none | yes | yes | 0 |
| libnative-imagetranscoder.so | 0.33 | yes | full | yes | yes | 0 |
| liblytcuhe.so | 0.17 | yes | full | yes | yes | 0 |
| librcmefqonnx.so | 0.17 | yes | full | yes | yes | 0 |
| libohxbee.so | 0.15 | yes | full | yes | yes | 0 |
| libsecurity_lite.so | 0.15 | yes | full | yes | no | 4 |
| libcrashlytics.so | 0.09 | yes | none | yes | no | 0 |
| libcrashlytics-handler.so | 0.08 | yes | none | yes | no | 0 |
| libtrueidlib.so | 0.08 | yes | full | yes | yes | 10 |
| libimage_processing_util_jni.so | 0.02 | yes | full | yes | yes | 6 |
| libnative-filters.so | 0.01 | yes | full | yes | no | 0 |
| libandroidx.graphics.path.so | 0.01 | yes | full | yes | no | 0 |
| libcrashlytics-trampoline.so | 0.01 | yes | none | no | no | 0 |
| libimagepipeline.so | 0.01 | yes | full | yes | no | 0 |