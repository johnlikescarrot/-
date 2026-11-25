# Phân tích tĩnh toàn diện Zalopay 11.17.0 (vn.com.vng.zalopay) — Báo cáo nghiên cứu & Tái lập

> **Tiêu đề học thuật (EN):** *A Comprehensive Static Reverse-Engineering Study of a Production Vietnamese Fintech Android Application (Zalopay 11.17.0): Architecture, Cryptography, On-Device ML, and Privacy Surface*
> **Tác giả / Nhóm:** Arena Agent Mode — Static Analysis Working Group (2026-11-25)
> **Đối tượng:** `Zalopay-Thanh+toán+&+Tài+chính_11.17.0_APKPure.zip` → XAPK v2, package `vn.com.vng.zalopay`, versionName `11.17.0`, versionCode `1102758`, minSdk 26, targetSdk 36.
> **Phương pháp:** Phân tích tĩnh 100% (không thực thi động, không vượt qua bảo mật, không trích xuất bí mật). Công cụ: Androguard (APK/DEX/AXML), `readelf`/`nm`/`strings` (ELF native), Python 3.11, FFmpeg (imageio-ffmpeg). Khung tham chiếu: quy trình dịch ngược Android chuẩn (apktool/jadx/Frida-lab ở mức lý thuyết — xem §2), và các công cụ phân tích tĩnh mã nguồn mở như [morluto/rea](https://github.com/morluto/rea) (minh họa triết lý phân tích tĩnh có thể tái lập; nghiên cứu này không phụ thuộc vào một công cụ duy nhất).
> **Dữ liệu máy đọc được:** `research/data.json` (60.671 lớp, 351.248 phương thức, 243.786 chuỗi duy nhất, 19 DEX, 27 thư viện native, 124 assets, 5.604 resources).
> **Video học thuật đi kèm:** `video/zalopay-11.17.0-study.mp4` (TypeScript + FFmpeg, 1920×1080, H.264/AAC, lồng tiếng tiếng Việt) — xem `video/README.md`.

---

## Tóm tắt (Abstract)

Nghiên cứu này trình bày một phân tích tĩnh toàn diện, có thể tái lập, đối với toàn bộ các tệp trong gói XAPK Zalopay 11.17.0 (tổng ~105,6 MB, 5 APK split: base + `config.armeabi_v7a` + `config.en` + `config.vi` + `config.xhdpi`). Chúng tôi giải nén, lập bản đồ cấu trúc (manifest, DEX, resources, assets, native `.so`), định lượng hệ sinh thái thư viện (Google Android, Alibaba Griver/Ariver mini-app, Bouncy Castle — bao gồm post-quantum `org/bouncycastle/pqc`, Visa CBP, TrueID eKYC, ML Kit, ExoPlayer), trích xuất bề mặt API thanh toán (`um/*`, `v001/tpe/*`, `cpscdn/*` qua gRPC/HTTPS), và đánh giá bề mặt quyền riêng tư/bảo mật ở mức mô tả kiến trúc. Kết quả cho thấy Zalopay là một *super-app* tài chính đa lớp: ví điện tử + ngân hàng liên kết (NAPAS/VietQR) + mini-app platform (Griver) + eKYC sinh trắc học trên thiết bị (face/liveness/OCR) + thanh toán NFC/QR + BNPL/installment. Báo cáo tuân thủ đạo đức: chỉ mô tả kiến trúc công khai từ mã đã đóng gói; không công bố khóa, token, hay hướng dẫn vượt qua cơ chế bảo vệ.

**Từ khóa:** Android reverse engineering, static analysis, fintech, e-wallet, eKYC, post-quantum cryptography, mini-app, Vietnam, reproducibility.

---

## 1. Đối tượng & tính toàn vẹn (Integrity)

| Hạng mục | Giá trị |
|---|---|
| File gốc | `Zalopay-Thanh+toán+&+Tài+chính_11.17.0_APKPure.zip` (98.813.878 bytes) |
| SHA-256 (zip) | `34b9b6b6735f0f739617f97a259f931d17cdbacb51871c40b066de41f4b4610a` |
| SHA-256 (base APK) | `e289d547b45f6224aed8ce7bf72d75ea5545cd50c0e768d1a74a3e8165fe6a03` |
| XAPK manifest | xapk_version 2, total_size 105.580.620, splits: base, config.armeabi_v7a, config.en, config.vi, config.xhdpi |
| Package / Version | `vn.com.vng.zalopay` / 11.17.0 (1102758) |
| SDK | min 26 (Android 8.0), target 36 (Android 16) |
| Main activity | `vn.com.vng.zalopay.splashscreen.presentation.SplashScreenActivityV2` |
| Signing cert SHA-256 | `59dd31fdf36070a0d7f8c8cde843fac371df7f1a915c0136c9c9b55992e8cdae` (CN=Vu Manh Hieu, 2015–2041) |
| Libraries khai báo | androidx.camera.extensions, androidx.window, android.ext.adservices, org.apache.http.legacy |

> Ghi chú: cert subject là thông tin công khai trong APK đã ký; báo cáo chỉ ghi fingerprint để xác thực tính toàn vẹn, không suy luận danh tính cá nhân.

## 2. Phương pháp (Methodology) — có thể tái lập

1. **Giải nén có thứ bậc:** ZIP → XAPK → 5 APK → base APK (DEX/resources/assets) + split native (`lib/armeabi-v7a/*.so`, 27 file, ~55 MB chưa nén).
2. **Manifest & thành phần (Androguard):** 200 activities, 34 services, 22 receivers, 9 providers, 49 permissions. Đối chiếu với `manifest.json` của XAPK (52 permissions khai báo ở cấp XAPK).
3. **DEX:** 19 file `classes*.dex` (tổng ~64 MB). Phân tích lớp/phương thức/chuỗi bằng Androguard DEX parser; histogram gói 3 cấp; grep chuỗi có mẫu (URL, domain, crypto, payment, biometric, Firebase/Ads).
4. **Native:** `readelf -d` (NEEDED), `nm -D` (exports), `strings` cho mỗi `.so`; phân loại theo vai trò (ML, crypto, face, QR, SQLCipher, Rive, Crashlytics…).
5. **Assets & resources:** 124 assets (ML models `.tflite/.pb/.ncnn`, Griver bridge JS, fonts, config JSON, `.dat`); 5.604 resources (`layout` 1.018, `drawable-*`, `font`, `raw` 56…); `resources.arsc` 1,8 MB.
6. **Config JSON:** `zalopay_config.json` (API names, timeouts, phone regex VN, PCI-DSS seal), `miniapp_config.json`, `home_data.json` (deep links `zalopay://`), `getbanklist_zpm_success.json` (bank connectors).
7. **Không thực thi:** Không chạy APK trên thiết bị/giả lập; không hook runtime; không giải mã dữ liệu người dùng. Mọi kết luận đều là *mô tả tĩnh*.

**Công cụ tham chiếu (không bắt buộc):** apktool (giải mã resources), jadx (decompile DEX→Java/Kotlin), MobSF, Frida (chỉ trong lab hợp pháp — ngoài phạm vi nghiên cứu này), và các bộ phân tích tĩnh mã nguồn mở như [morluto/rea](https://github.com/morluto/rea). Nghiên cứu này dùng Androguard + binutils để đảm bảo chạy được trong môi trường sandbox chỉ có Python/Node/FFmpeg.

**Tái lập:**
```bash
pip install --break-system-packages androguard pillow imageio-ffmpeg
python3 work/analyze.py   # → research/data.json
```

## 3. Kết quả

### 3.1 Quy mô mã (DEX)
- **60.671 lớp, 351.248 phương thức, 243.786 chuỗi duy nhất** trên 19 DEX. DEX lớn nhất: `classes2.dex` (16,5 MB, 7.421 lớp), `classes7.dex` (10,8 MB, 12.953 lớp), `classes4.dex` (9,4 MB, 11.441 lớp).
- Histogram gói (top): `com/google/android` (6.465), `com/alibaba/ariver` (2.415), `com/alibaba/griver` (2.228), `org/bouncycastle/jcajce` (1.758), `com/iap/ac` (1.330), `org/bouncycastle/pqc` (975), `vn/com/vng` (958 — mã Zalopay), `com/visa/cbp` (900), `org/bouncycastle/crypto` (836), `vn/ai/trueid` (622), `androidx/media3/exoplayer` (538), `com/vnpay/vntaxi` (377), `com/appsflyer/internal` (364), `com/nimbusds/jose` (360).
- **Diễn giải:** ~1/3 mã là SDK Google; khối Alibaba Griver/Ariver (~4.600 lớp) là nền tảng mini-app; Bouncy Castle (~4.300 lớp incl. PQC) là lớp crypto; mã nội bộ `vn/com/vng` chỉ ~1,6% — mô hình *super-app tích hợp SDK* điển hình.

### 3.2 Thành phần Android
- **Activities (200):** lõi Zalopay + họ `GriverBaseActivity/GriverTransActivity` (Main/Lite1-3/Back1-6) cho mini-app, `PreviewActivity` (Compose), Glance widgets, Facebook/Ads activities.
- **Services (34):** `MyFcmListenerService` (push), `VisaHostApduService` + `TinyAppHostApduService` (NFC HCE), `FusedLocationService` (VNPay taxi), WorkManager, Firebase Messaging/Measurement, ML Kit discovery, Alibaba IPC (`RemoteCallService`, `IpcMsg*Service`), BigData services.
- **Receivers (22):** 4 AppWidget receivers của Zalopay (Game/Movie/PersonalQR/Standard), `ZPInstallBroadcastReceiver`, WorkManager constraints, Facebook token receivers.
- **Providers (9):** FileProvider, GriverFileProvider, Firebase/ML Kit/Ads providers.

### 3.3 Quyền (49) — bề mặt nhạy cảm
Nhóm chính: INTERNET/ACCESS_NETWORK_STATE; CAMERA + RECORD_AUDIO (QR/eKYC); ACCESS_FINE/COARSE_LOCATION; READ_CONTACTS/WRITE_CONTACTS + READ/WRITE_CALENDAR (chuyển tiền theo danh bạ, nhắc lịch); USE_BIOMETRIC/USE_FINGERPRINT; NFC; POST_NOTIFICATIONS; READ_PHONE_STATE/READ_BASIC_PHONE_STATE; QUERY_ALL_PACKAGES (đáng chú ý — cần giải thích chính sách Play); DETECT_SCREEN_CAPTURE; BILLING; AD_ID/ADSERVICES_* (quảng cáo/attribution); FOREGROUND_SERVICE; MANAGE_OVERLAY_PERMISSION; launcher shortcuts; OEM permissions (Samsung/Huawei/BBK). Xem `data.json` để có danh sách đầy đủ.

### 3.4 Native (27 `.so`, armeabi-v7a)
| Nhóm | Thư viện (size) |
|---|---|
| ML/OCR/Face | `libmlkitcommonpipeline.so` (6,9 MB), `libmlkit_google_ocr_pipeline.so` (6,8 MB), `libface_detector_v2_jni.so` (5,4 MB), `libpicodet_ncnn.so` (3,0 MB), `libVFaceLib.so` (2,0 MB), `libtrueidlib.so` (80 KB) |
| Crypto/Security | `libtaibmcf.so` (12,6 MB — lớn nhất, obfuscated name), `libsqlcipher.so` (3,6 MB — DB mã hóa), `libsecurity_lite.so` (155 KB) |
| Obfuscated (tên ngẫu nhiên) | `libfgcpkjvqe.so` (6,5 MB), `libljsfrcwmw.so` (600 KB), `liblytcuhe.so` (179 KB), `libohxbee.so` (155 KB), `librcmefqonnx.so` (179 KB), `liba97d.so` (998 KB) — mẫu số hóa tên `.so` để gây khó reverse; cần phân tích động trong lab để xác định vai trò (ngoài phạm vi tĩnh) |
| QR/Barcode | `libbarhopper_v3.so` (3,2 MB — ML Kit barcode) |
| UI/Media | `librive-android.so` (3,2 MB), `libnative-imagetranscoder.so`, Fresco imagepipeline |
| Crash | `libcrashlytics*.so` (4 file) |

### 3.5 Assets (124) — ML trên thiết bị & mini-app
- **ML Kit OCR:** 21 file trong `mlkit-google-ocr-models/` (aksara/gocr/taser: `.binarypb`, `.tflite`, `.fb`) — OCR tiếng Việt/Latin trên thiết bị (eKYC đọc CCCD/hộ chiếu).
- **Barcode/Object:** `mlkit_barcode_models/` (SSD MobileNet, OneD), `mlkit_odt_*` (object detection), `custom_models/object_labeler.tflite`, `models_bundled/` (Face: blazeface, FSSD, BCL eye models, contours).
- **Face/eKYC:** `FastestDet.ncnn.{bin,param}`, `fgcpkjvqe.dat`, `taibmcf.dat/json`, `bshield.dat` — mô hình phát hiện mặt NCNN + dữ liệu kèm theo.
- **Griver mini-app:** `GriverBridge/*.js` (h5_bridge, workerjs), `Griver/*.amr` (âm thanh), `GriverTheme/theme.json`, `android.1.zip` + `bundle.json` (external bundle), `jsbridge.js`/`internaljsbridge.js`/`android_jsbridge.js`.
- **Config:** `zalopay_config.json`, `miniapp_config.json`, `home_data.json`, `client_tracking.json`, `timezone2region_2023c.json`, `vdfmoer.json`; fonts: Roboto, UnisectVnu, zalopay.ttf, true_font.ttf; `public.pem`, `zoom.public`.

### 3.6 Mạng & API thanh toán
- **ApiDomainConfiguration** (từ DEX strings): ~40 hosts — `api.zalopay.vn`, `sapi.zalopay.vn`, `zpm.zalopay.vn`, `upload.zalopay.com.vn`, `token.zalopay.vn`, `scard.zalopay.vn` (PCI card), `bim.zalopay.vn`, `bnpl-gateway.zalopay.vn`, `fin-installment.zalopay.vn`, `zlp-ofp-*.zalopay.vn` (offline payment EMVCo/QR), `dala2zoom.zalopay.vn/liveness/`, `rar-um.zalopay.vn` (VNeID), `tracking.zalopay.vn`, `onelink.zalopay.vn`, `miniprogram.zalopay.vn`…
- **API names (35)** trong `zalopay_config.json`: `um/*` (user management: getuserinfo, updateprofile, verifyotpprofile, recoverypin, listcardinfo, accesstoken…), `v001/tpe/*` (third-party engine: createorder, submittrans, transhistory, gettransstatus, atmauthenpayer, mapcard…), `cpscdn/app/getbanklist|getplatforminfo`. `connector_mode: grpc`, `api_route: https`, `api_timeout: 10000ms`.
- **Banking:** `getbanklist_zpm_success.json` — bank connectors (VD: Nam Á Bank `123PNAB`, card prefix `970428`), `bankcardprefixmap`/`tpebankcardprefixmap`, OTP qua SMS, maintenance windows.
- **Deep links:** `zalopay://launch/f/transfer|linkcard|wallet`, `zalopay://launch/app/{id}` (mini-app), `https://onelink.zalopay.vn/d/{id}`.
- **Mini-app catalog:** `miniapp_config.json` — app_id, base_url (`social.zalopay.vn`, `mc.zalopay.vn/app`), nav_bar config, category (Tài chính, Dịch vụ phổ biến…).

### 3.7 Crypto & bảo mật (mô tả kiến trúc)
- **Bouncy Castle** đầy đủ (`jcajce`, `crypto`, `asn1`, **`pqc` — post-quantum cryptography**) + `com/nimbusds/jose` (JOSE/JWT) + `libsqlcipher` (SQLCipher) + `libsecurity_lite` → lớp crypto mạnh, có hướng post-quantum.
- **PCI-DSS:** seal ControlCase trong config; `scard.zalopay.vn` cho card data; `bankBindingPciHost`.
- **eKYC:** TrueID (`vn/ai/trueid`, 622 lớp) + VFaceLib + liveness host + VNeID host → xác thực sinh trắc học đa lớp.
- **Integrity/Anti-fraud:** Play Integrity API strings, `bshield.dat`, obfuscated `.so` — dấu hiệu chống giả mạo thiết bị (chi tiết cần lab động, không kết luận vượt quá tĩnh).
- **Privacy:** AppsFlyer, Facebook SDK, Google Measurement/Ads, Firebase Analytics/Crashlytics/Perf — bề mặt tracking quảng cáo cần đối chiếu với chính sách quyền riêng tư (link trong DEX: `zalopay.vn/quy-dinh/chinh-sach-bao-ve-quyen-rieng-tu`).

### 3.8 Resources & UI
- 5.604 resources: `layout` 1.018 + `layout-v31` 150 + `layout-v33` 319 (Material You), `drawable-*` hàng nghìn, `font` 14, `raw` 56, `navigation` 18, `xml` 13. Compose + View system song song (Compose UI packages trong DEX). `resources.arsc` 1,8 MB.

## 4. Thảo luận (Discussion)

Zalopay 11.17.0 là minh chứng của mô hình *super-app fintech*: một APK ~76 MB (base) + 24 MB native split chứa đồng thời ví, ngân hàng, mini-app platform, eKYC ML, và thanh toán offline. Điểm nổi bật học thuật:
1. **Tích hợp sâu Alibaba Griver** — Zalopay không chỉ dùng mini-app mà nhúng cả runtime (IPC services, JS bridges, bundle system).
2. **Crypto post-quantum** — sự hiện diện của `org/bouncycastle/pqc` trong app tài chính tiêu dùng là tín hiệu sớm đáng nghiên cứu thêm (cần xác minh mức độ sử dụng thực tế vs. đóng gói thừa).
3. **ML trên thiết bị toàn diện** — OCR, face, barcode, object detection đều chạy offline (`.tflite/.ncnn/.binarypb`), giảm phụ thuộc mạng cho eKYC/QR.
4. **Tên `.so` obfuscated** (`libfgcpkjvqe`, `libtaibmcf`…) — thực hành security-by-obscurity; nghiên cứu tương lai nên phân tích hành vi trong môi trường lab cô lập.
5. **Bề mặt quyền rộng** (QUERY_ALL_PACKAGES, contacts, location, screen capture) — đặt ra câu hỏi về tối thiểu hóa dữ liệu (data minimization) theo chuẩn bảo vệ dữ liệu cá nhân Việt Nam (Nghị định 13/2023/NĐ-CP).

## 5. Hạn chế (Limitations)
- Chỉ phân tích tĩnh; chưa kiểm chứng hành vi runtime, network traffic, hay hiệu năng.
- Chuỗi DEX chứa nhiều text SDK (Google/Facebook) — số liệu grep là *upper bound*, không phải bằng chứng tính năng Zalopay.
- Các `.so` tên ngẫu nhiên chưa được gán vai trò chắc chắn.
- Không phân tích được mã bị pack/encrypt (nếu có) ở mức tĩnh.

## 6. Kết luận & hướng mở rộng
Nghiên cứu đã lập bản đồ toàn diện 100% tệp XAPK Zalopay 11.17.0 với dữ liệu máy đọc được (`data.json`) và video học thuật (TypeScript/FFmpeg). Hướng mở rộng: (a) decompile jadx để phân tích luồng thanh toán `v001/tpe/submittrans`; (b) lab động cô lập để xác định vai trò `.so` obfuscated; (c) đối chiếu quyền với chính sách quyền riêng tư; (d) so sánh chéo với MoMo/Viettel Money để nghiên cứu so sánh fintech VN.

## 7. Tài liệu tham khảo (References — để trích dẫn)
1. Androguard — *Reverse engineering and analysis of Android applications*, https://github.com/androguard/androguard
2. Morluto et al. — *rea: static analysis toolkit* (tham chiếu phương pháp), https://github.com/morluto/rea
3. jadx — *Dex to Java decompiler*, https://github.com/skylot/jadx
4. apktool — *Android APK resource decoder*, https://github.com/iBotPeaches/Apktool
5. MobSF — *Mobile Security Framework*, https://github.com/MobSF/Mobile-Security-Framework-MobSF
6. Bouncy Castle — *Java cryptography APIs*, https://www.bouncycastle.org
7. Alibaba Griver — *Mini-app framework documentation*.
8. Nghị định 13/2023/NĐ-CP về bảo vệ dữ liệu cá nhân (Việt Nam).
9. Zalopay — *Điều khoản sử dụng & Chính sách quyền riêng tư*, https://zalopay.vn/quy-dinh/thoa-thuan-su-dung-zalopay

## 8. Phụ lục
- `research/data.json` — toàn bộ số liệu máy đọc được.
- `work/analyze.py` — script tái lập phân tích.
- `video/` — mã nguồn TypeScript sinh video MP4 + README tái lập.
- Đạo đức: Nghiên cứu chỉ nhằm mục đích học thuật, tuân thủ pháp luật; không hỗ trợ gian lận, vượt qua bảo mật, hay xâm phạm bản quyền. Mọi nhãn hiệu thuộc về chủ sở hữu tương ứng.

*— Hết báo cáo —*
