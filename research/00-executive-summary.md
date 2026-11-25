# ZaloPay 11.17.0 — Full-Stack Static Reverse Engineering
### Executive summary / Tóm tắt điều hành

**Artefact** `Zalopay-Thanh+toán+&+Tài+chính_11.17.0_APKPure.zip`
→ `vn.com.vng.zalopay` · versionName **11.17.0** · versionCode **1102758** · minSdk **26** · targetSdk **36**
**Container SHA-256** `34b9b6b6735f0f739617f97a259f931d17cdbacb51871c40b066de41f4b4610a`
**XAPK SHA-256** `686e9459da646c18b53a67d57e5a23c49466da140ec1e3e9682032f453ea7b8d`
**Base APK SHA-256** `e289d547b45f6224aed8ce7bf72d75ea5545cd50c0e768d1…`

---

## VI — Tóm tắt

Nghiên cứu này mổ xẻ **toàn bộ** gói cài đặt ZaloPay 11.17.0 ở mức byte: container XAPK, 5 APK
(1 base + 4 split), 19 tệp DEX, bảng tài nguyên `resources.arsc`, 124 asset, 27 thư viện native
và toàn bộ khối chữ ký APK. Mọi kết luận đều tái lập được bằng 9 script trong `tools/analyze/`
(Python 3.11, không cần apksigner, jadx, emulator, thiết bị hay mạng).

**Mười phát hiện định lượng quan trọng nhất**

| # | Phát hiện | Bằng chứng |
|---|---|---|
| 1 | Chữ ký **APK Signature Scheme v2 + v3** (RSA-2048/SHA-256), chứng thư tự ký `CN=Vu Manh Hieu` hiệu lực 2016→2041, **không** có chữ ký JAR v1 trong base APK | `data/01-provenance.json` |
| 2 | Có **Google Play source stamp** → base APK từng được phát hành qua Play trước khi bị mirror | `01-provenance.json` → `source-stamp.certificate` |
| 3 | Khối chữ ký có **1 block không tài liệu** `0x2146444e` (691 B, payload dạng protobuf) + padding 1 602 B zero để căn 8 192 B | `01-provenance.json` |
| 4 | Mã nguồn: **60 671 class / 351 248 method / 243 782 chuỗi duy nhất**, DEX format 038, checksum SHA-1 + Adler-32 **hợp lệ 19/19** | `03-dex.json` |
| 5 | Chỉ **952 class (1,57 %)** thuộc namespace `vn.com.vng.zalopay` — 98,4 % binary là mã bên thứ ba | `03-dex.json`, `08-summary.json` |
| 6 | **200 activity / 34 service / 22 receiver / 9 provider**, trong đó **28 component exported**; 9 deep link (gồm `go.zalopay.vn` autoVerify, scheme `zalopay://`) | `02-manifest.json` |
| 7 | **4 component test/debug bị export** trong bản production: 3 × `androidx.test…InstrumentationActivityInvoker`, `androidx.compose.ui.tooling.PreviewActivity` (+ `TagManagerPreviewActivity`) | `02-manifest.json` |
| 8 | `network_security_config` đặt **`cleartextTrafficPermitted="true"` cho toàn bộ domain** (`base-config`) | `res/xml/network_security_config.xml` |
| 9 | Lớp chống dịch ngược **native-only** (Bangcle *BShield*): `assets/bshield.dat`, `libtaibmcf.so` 12,6 MB, các lib tên ngẫu nhiên, 67 đường dẫn build `/home/cpu12313/Documents/BshieldAzure/…` bị rò rỉ | `05-native.json` |
| 10 | **17 model ML** chạy trên thiết bị (OCR, barcode, face, object detection) + eKYC TrueID (`libtrueidlib.so` với `SmartOTPHelper_vaultCheckIntegrity`, đường dẫn Magisk) | `07-resources.json`, `05-native.json` |

**Điểm tư thế bảo mật (rubric 8 chiều, có trọng số): 83,2 / 100.**
Mạnh: chữ ký, hardening native (PIE/NX/BIND_NOW 27/27), TLS pinning, Play Integrity.
Yếu: tối thiểu hoá dữ liệu (55), vệ sinh mã nguồn (45) — do component test bị export, cờ manifest
legacy, và rò rỉ đường dẫn build.

**Sản phẩm kèm theo:** bộ phim tài liệu kỹ thuật 1080p dựng bằng pipeline TypeScript
(`video/`), xuất MP4, thuyết minh tiếng Việt, phụ đề song ngữ.

---

## EN — Abstract

We present a reproducible, file-complete static study of the ZaloPay 11.17.0 Android payments
client as distributed through an APKPure XAPK mirror. The paper contributes:

1. **A dependency-free parsing stack.** `dexmeta.py` decodes DEX string/type/proto/field/method
   tables and `class_def` records directly from the byte layout; `arscmeta.py` walks
   `resources.arsc` chunks (package, type, type-spec, config, entry census, locale configs).
   Together they audit 64.8 MB of bytecode and 1.8 MB of resource table in under two seconds on
   two vCPUs — one to two orders of magnitude faster than full-featured frameworks, because no
   instruction decoding is performed.
2. **A byte-level provenance reconstruction** of the APK Signing Block (v2, v3, Play source
   stamp, one undocumented block id, padding), including X.509 metadata and fingerprints, without
   `apksigner` or a JVM.
3. **A quantitative ownership census** of the binary: 60 671 classes mapped to vendor roots,
   Herfindahl–Hirschman index 0.036, top-10 namespace share 43.8 %, first-party share 1.57 %.
   The measurable trust boundary of a modern payment app is therefore *its dependency graph*.
4. **A static signal taxonomy** across 22 families (transport security, keystore, attestation,
   root/hook/emulator detection, WebView/JSBridge, data collection, secret-shaped material) with
   explicit epistemic caveats: string-table prevalence measures *capability presence*, not
   behaviour.
5. **A prioritised findings register** (9 entries, 1 medium/3 low/5 informational-positive) that
   is deliberately non-exploitative: each entry names the risk class, the evidence and the
   remediation, never an attack recipe.
6. **A motion-picture dissemination artefact** generated from the same JSON datasets, so the film
   cannot drift from the measurements it narrates.

**Keywords:** Android security · APK signature scheme v2/v3 · static analysis · multidex ·
obfuscation · supply-chain risk · mobile payments · HCE · on-device ML · reproducible research.
