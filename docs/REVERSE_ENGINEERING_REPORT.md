# Báo cáo dịch ngược tĩnh toàn diện — Zalopay 11.17.0 (XAPK)

> **Phạm vi & đạo đức.** Nghiên cứu tĩnh, phòng thủ/học thuật trên tệp do người dùng cung cấp
> (`Zalopay-Thanh+toán+&+Tài+chính_11.17.0_APKPure.zip`). Không thực thi động, không vượt qua
> kiểm soát thanh toán/bảo mật, không phát tán lại binary bản quyền. Chỉ công bố hash/size/count
> và trích dẫn thực tế nhỏ. Mọi con số đều tái tạo được bằng `tools/analyze_apk.py`
> (stdlib-only) → `docs/analysis.json`.
>
> **Phương pháp luận.** Lấy cảm hứng từ [morluto/rea](https://github.com/morluto/rea)
> (“Reverse engineer anything with agents”): kiểm kê bằng chứng (inventory) → trích xuất
> (extract) → phân tích (analyze) → bằng chứng có thể kiểm chứng. Các công cụ tham chiếu
> tương đương: `jadx` (DEX→Java), `apktool`/`rea`-style resource decoding (binary XML/ARSC),
> `aapt2`, `strings`/`rabin2`, `tflite`/`ncnn` inspection. Trong sandbox không có Java nên
> pipeline chính dùng Python stdlib để giải mã binary XML strings, DEX strings, ZIP/XAPK —
> tương đương về mặt kiểm kê, không thay thế decompilation đầy đủ.

## 1. Kiểm kê gói (XAPK)

| Hạng mục | Giá trị |
|---|---|
| Tệp nguồn | `Zalopay-Thanh+toán+&+Tài+chính_11.17.0_APKPure.zip` (~95 MB) |
| Nội dung | 1 tệp `.xapk` (~105.6 MB) |
| Package | `vn.com.vng.zalopay` |
| Tên / mã phiên bản | Zalopay `11.17.0` / `1102758` |
| minSdk / targetSdk | 26 / 36 |
| XAPK splits | base + `config.armeabi_v7a` + `config.en` + `config.vi` + `config.xhdpi` |
| Tổng kích thước khai báo | ~105.6 MB |

`manifest.json` của XAPK liệt kê 50+ quyền (xem §3) và 5 split APK. Đây là phân phối
**split-APK (App Bundle-derived)**: base chứa code + resources chung, các split chứa
native libs theo ABI, locale resources và density drawables.

## 2. Base APK (`vn.com.vng.zalopay.apk`, ~79.4 MB, 6.162 entries, ~136 MB uncompressed)

### 2.1 DEX — mã Dalvik/Kotlin

- **20 tệp DEX** (19 `classes*.dex` + `assets/audience_network.dex`), tổng **~69.7 MB**.
  Lớn nhất: `classes2.dex` (~16.5 MB), `classes7.dex` (~10.8 MB), `classes4.dex` (~9.4 MB),
  `classes.dex` (~8.8 MB). Phân mảnh DEX cho thấy multidex + tách module (payment, Visa/VNPay,
  ML Kit, Alipay/Griver, v.v.).
- **~793.000 chuỗi ASCII** trích từ DEX. Dấu hiệu SDK (đếm chuỗi, xem `analysis.json`):
  `rive` (animation runtime), `griver`/`ariver` (Alipay mini-program container),
  `facebook`, `mlkit`, `visa`, `firebase`, `appsflyer`, `alipay`, `kotlin`, `compose`,
  `okhttp`, `protobuf`, `crashlytics`, `vnpay`, `gtm`, `tiktok`, `adjust`.
- **URL**: ~233 chuỗi URL (146 duy nhất). Domain nổi bật: `api.zalopay.vn`,
  `sapi.zalopay.vn`, `openapi.zalopay.vn`, `social.zalopay.vn`, `scard.zalopay.vn`,
  `upload-img.zalopay.vn`, `simg.zalopay.com.vn`, `graph.zaloapp.com`, `oauth.zaloapp.com`,
  `ztevents.zaloapp.com`, `api.trueid.vn`, `api.fpt.ai`, `icl.visa.com`.
- **Crypto**: `AES/GCM`, `AES/CBC`, `AES/CTR`, `AndroidKeyStore`, `BiometricPrompt`,
  Tink, Nimbus JOSE — phù hợp ví điện tử (khóa trong Keystore, sinh trắc học).

### 2.2 AndroidManifest.xml (binary XML, ~126 KB)

- **~212 Activity** (đếm chuỗi `*Activity` trong binary XML). Các cụm:
  - Home/onboarding: `HomeActivity` (homev5), `SplashScreenActivityV2`, `OnboardingActivity`
  - Auth/KYC/eKYC: `LoginActivity`, `OtpVerificationActivity`, `MainEKycActivity`,
    `LivenessVerificationActivity`, `FaceLivenessCameraXActivity`, `IdentityCardCaptureActivity`,
    `ReformKycActivity`, `VerificationByVNeIDActivity`, `SmartOTPActivity`
  - Cashier/thanh toán: `CashierV3Activity`, `MainCashierV2Activity`, `CashierWebActivity`,
    `ResultPageV3Activity`, `PayByWalletReadyToPayActivity`, `QuickPayNativeActivity`
  - Ngân hàng: `BankBindingActivity`, `FundManagementActivity`, `WithdrawActivity`, `CashInActivity`
  - QR/NFC: `ScanQRActivityV2`, `QRCodeScannerActivity`, `FundingSolutionNFCActivity`,
    `VisaHostApduService`, `MyQRActivity`
  - Mini-app/Griver: `MiniAppActivity`, `GriverBaseActivity*`, `GriverTransActivity*`,
    `GriverCaptureActivity`, `UMMiniAppActivity`
  - Khác: `LockScreenActivity`, `ChangePinActivity`, `ResetPinActivity`, `CreatePinActivity`,
    `AccountHostActivity`, `PrivacyHubActivity`, `SearchActivity`, `PdfViewerActivity`,
    App Widgets (`Game/Movie/PersonalQR/StandardAppWidgetReceiver`), FCM (`MyFcmListenerService`).
- Deep-link schemes: `zalopay://`, `zalopay-vnpay`, `zalopay.api.v2`, `go.zalopay.vn`,
  `km.zalopay.vn`, `onelink.zalopay.vn`, `kmzalopay.onelink.me`, `cct.vn.com.vng.zalopay`.

### 2.3 Resources (`res/`, ~5.604 entries)

- `drawable` 3.675, `layout` 1.498 (gồm `layout-v33` 319, `layout-v31` 150 — Material You /
  Android 13+), `color` 82, `anim` 78, `raw` 56, `font` 14, `xml` 13.
- `res/raw`: Rive animations (`tap2pay_payment_guide.riv` ~93 KB, `user_guide.riv` ~64 KB,
  `loading.riv`, `failure.riv`), âm thanh (`pay_success_sound.mp3`, `zalopay_sound.mp3`).
- Fonts: Roboto, Inter, Noto Sans, SF Pro/Compact, UnisectVnu, OCR-A — phục vụ OCR/eKYC.

### 2.4 Assets (`assets/`, 124 tệp)

| Nhóm | Nội dung chính |
|---|---|
| Cấu hình | `zalopay_config.json` (~106 KB), `miniapp_config.json` (~884 KB), `home_data.json`, `bundle.json`, `public_key.txt` (RSA-2048), `signature.txt` |
| React Native | `android.1.zip` (~3.9 MB) → `main.jsbundle` (~3.6 MB, production, không source-map) + 325 assets |
| JS Bridge | `jsbridge.js` (`ZaloPayJSBridge`), `internaljsbridge.js`, `android_jsbridge.js`, Griver bridges (`h5_bridge.js`, `workerjs.js`) |
| ML | `FastestDet.ncnn.bin` (~2.4 MB), `custom_models/object_labeler.tflite` (~3.8 MB), 14 TFLite (ML Kit OCR/barcode/face), NCNN PicoDet |
| Mini-program | `Griver/*.amr` (3 gói Alipay AppX), `GriverTheme/theme.json`, `containers/GTM-PC9W5BW.json` (Google Tag Manager) |
| Khác | `audience_network.dex` (Facebook Audience Network), AppsFlyer internal, Visa CBP, `client_tracking.json` (dữ liệu mẫu/tracking), `dexopt/baseline.prof` |

### 2.5 META-INF / versions (141 tệp `.version`)

AndroidX (Activity 1.9.3, Compose 1.7.2, CameraX 1.4.0, Lifecycle 2.8.7, Room 2.8.4,
WorkManager 2.7.1, Biometric 1.2.0-alpha05, WebKit 1.11.0-alpha02), Material 1.4.0,
Dagger 2.59.2, Coroutines 1.8.1, AGP 8.12.3, DataBinding 8.12.3. ML Kit properties:
barcode-scanning 17.3.0, face-detection 16.1.7, common 18.11.0, billing 8.3.0.

## 3. Quyền (permissions)

Từ `manifest.json` XAPK (~50 quyền). Nhóm nhạy cảm:

- **Định danh/thiết bị**: `READ_PHONE_STATE`, `READ_BASIC_PHONE_STATE`, `QUERY_ALL_PACKAGES`, `AD_ID`
- **Vị trí**: `ACCESS_COARSE_LOCATION`, `ACCESS_FINE_LOCATION`
- **Camera/mic**: `CAMERA`, `RECORD_AUDIO` (eKYC, QR, liveness)
- **Danh bạ/lịch**: `READ_CONTACTS`, `WRITE_CONTACTS`, `READ/WRITE_CALENDAR`
- **Lưu trữ**: `READ/WRITE_EXTERNAL_STORAGE`, `READ_MEDIA_AUDIO`
- **Sinh trắc học**: `USE_BIOMETRIC`, `USE_FINGERPRINT`
- **NFC**: `NFC` (tap-to-pay, eKYC NFC, Visa Host APDU)
- **Thông báo/FCM**: `POST_NOTIFICATIONS`, `RECEIVE_BOOT_COMPLETED`, `FOREGROUND_SERVICE`
- **Khác**: `INTERNET`, `VIBRATE`, `WAKE_LOCK`, `DETECT_SCREEN_CAPTURE`, `REORDER_TASKS`,
  `BILLING`, `INSTALL_SHORTCUT`, launcher/OEM permissions (Samsung/Huawei/BBK),
  `ACCESS_THIRD_PARTY_APP_AUTHORIZATION` (Zalo).

## 4. Split native (`config.armeabi_v7a.apk`, ~24.8 MB, 27 `.so`)

| Thư viện | Vai trò suy ra từ JNI/strings |
|---|---|
| `libtaibmcf.so` (12.6 MB), `libfgcpkjvqe.so` (6.5 MB) | Obfuscated/protected (tên ngẫu nhiên, ít JNI rõ ràng) — lớp bảo vệ thương mại |
| `libmlkit_google_ocr_pipeline.so`, `libmlkitcommonpipeline.so` | ML Kit OCR (TFLite, OpenCV) |
| `libface_detector_v2_jni.so` | Face detection (TFLite) |
| `libbarhopper_v3.so` | Barcode/QR (ML Kit) |
| `libVFaceLib.so` | Face auth (OpenCV Cascade) — `vn.ai.faceauth` |
| `libtrueidlib.so` | TrueID SDK (SmartOTP, X25519, vault) |
| `libohxbee.so` | Liveness (EXTRAS_LIVENESS_*) |
| `libpicodet_ncnn.so` + `FastestDet.ncnn` | Object detection NCNN (PicoDet) |
| `librive-android.so` | Rive vector animation runtime |
| `libsqlcipher.so` | SQLCipher — DB mã hoá |
| `libsecurity_lite.so` | Alipay SecurityGuard Lite (encrypt/decrypt/sign) |
| `libljsfrcwmw.so` | Griver/Alipay container (obfuscated) |
| `liba97d.so`, `liblytcuhe.so`, `librcmefqonnx.so` | Obfuscated nhỏ (ít strings) |
| Crashlytics, CameraX, Fresco, `libc++_shared` | Hạ tầng chuẩn |

Nhận xét: chỉ có **armeabi-v7a** (32-bit) trong gói này — thiết bị 64-bit cần split khác
(không có trong XAPK). Obfuscation tên `.so` + ít JNI là dấu hiệu anti-tamper/DRM.

## 5. Splits cấu hình

- `config.vi.apk` (~86 KB): `resources.arsc` tiếng Việt.
- `config.en.apk` (~74 KB): `resources.arsc` tiếng Anh.
- `config.xhdpi.apk` (~1.2 MB, 258 entries): drawables mật độ xhdpi (108 XML, 97 PNG, 48 WebP).

## 6. Cấu hình runtime (`zalopay_config.json`, 57 top-level keys)

- `api`: `api_route=https`, `connector_mode=grpc`, danh sách `api_names` gồm
  `um/*` (user management: create/verify/remove access token, get/update profile, OTP),
  `v001/tpe/*` (third-party payment engine: create order, submit trans, ATM auth, card mapping),
  `cpscdn/app/*` (bank list/platform info).
- `grpc`: ping 30s, timeout 60s, reconnect backoff — kênh gRPC chính.
- `banks_information`: **43 ngân hàng** (VTB, VCB, BIDV, …). `bank`: giới hạn liên kết thẻ,
  OTP timeout 300s, ngưỡng số dư 50.000đ, cấu hình Visa/Master/JCB, Napas.
- `kyc`/`bank_kyc`/`merchant_token`: giới hạn theo cấp độ (Lv2 ≤ 5tr, Lv3 ≤ 20tr),
  deep-link `zalopay://launch/f/kyc`.
- `deeplink`: prefix `zalopay://launch/f/`, blacklist web nội bộ.
- `webapp.allow_urls`: regex chỉ cho phép `*.zalopay.vn`, `*.zalopay.com.vn`,
  `*.zalopay.zing.vn`, sandbox, Freshdesk.
- `ssl_consideration`: ngoại lệ SSL cho 3D-Secure của các ngân hàng.
- `web_config`: tracking bank-link theo URL regex (VCB, BIDV, VPBank…), script Napas.
- `remote_config`: fetch 3600s, loop 300s, default 86400s.
- Khác: `phone_format` (regex SĐT VN 9–12 chữ số), `otp` (6 chữ số, 60s, auto-fill),
  `lockscreen`, `bill_split` (tối đa 25 người, tối thiểu 50.000đ), `keyboard` gợi ý
  [200k, 500k, 1tr], `theming` (#0033C9), CSKH `1900545436`, PCI-DSS seal.

## 7. Mini-apps & home (`miniapp_config.json`, `home_data.json`)

- **373 mini-apps** theo danh mục: Dịch vụ phổ biến 88, Giải trí–Cộng đồng 77,
  Tài chính 65, Ăn uống 32, Dịch vụ đối tác 18, Mua sắm 15, Giao thông 14,
  Thanh toán hoá đơn 12, còn lại rải rác.
- **316/373** dùng `social.zalopay.vn`, 38 dùng `mnap-director.zalopay.vn`.
  Deep-link chuẩn `zalopay://launch/app/{app_id}`, OneLink `onelink.zalopay.vn/d/{id}`.
- `home_data.json`: shortcut bar (Chuyển tiền, Ngân hàng, Nạp/Rút, Ưu đãi),
  highlight (Nạp ĐT, hoá đơn, Mã thanh toán, Tích lũy, 3G/4G…), bottom bar, balance.

## 8. Web/JS bridges & React Native

- `ZaloPayJSBridge` / `ZaloPayInternalJSBridge`: mẫu `call(func, param, callback)` +
  `callbackPool` + `subscribe/fire/unsubscribe`, sự kiện `ZaloPayJSBridgeReady`.
  Đây là mặt tấn công WebView cổ điển: web nội bộ gọi native qua bridge — cần allow-list
  URL (§6) và kiểm soát `func` ở native side.
- Alipay/Griver bridge (`AlipayJSBridge`, `__RENDER_WORKER_IPC_MP__`, MessagePort):
  mini-program AppX chạy trong worker, giao tiếp qua `postMessage` JSON.
- React Native bundle (`main.jsbundle`, production): React + Redux + React Navigation,
  Sentry DSN, 5 môi trường (`production→sapi.zalopay.vn`, `staging→socialstg`,
  `dev/qc-sandbox→socialdev`, `mc-sandbox`). Không source-map → khó debug, tốt cho IP.

## 9. ML / nhận dạng (eKYC)

- **OCR**: ML Kit Google OCR pipeline + TFLite (line recognition, layout, script ID,
  Aksara), `object_labeler.tflite`, NCNN FastestDet/PicoDet.
- **Face/liveness**: Face Detector v2, VFaceLib (OpenCV), TrueID, liveness extras.
- **Barcode/QR**: Barhopper v3 + ML Kit barcode models (SSD MobileNet, OneD regressor).
- **NFC/eKYC**: chip CCCD qua NFC (`MainNFCFeatureActivity`, `BottomSheetNFC`),
  VNeID verification, FPT AI API (`api.fpt.ai`).

## 10. Bảo mật & riêng tư (quan sát tĩnh)

**Điểm mạnh (suy ra):**
- HTTPS + gRPC, RSA-2048 public key + signature trong assets (ký cấu hình),
  SQLCipher cho DB, Android Keystore + Biometric, SecurityGuard Lite (sign/encrypt),
  TrueID SmartOTP (X25519), giới hạn KYC theo cấp độ, allow-list WebView,
  obfuscation native + DEX, không source-map RN.

**Rủi ro / cần kiểm chứng động (ngoài phạm vi tĩnh):**
- Mặt WebView/JS bridge rộng (373 mini-apps, deep-link) — cần kiểm tra origin check,
  `func` allow-list, và rò rỉ token qua `app_info_policy` (một số app được phép nhận
  `userid/muid/maccesstoken/phonenumber/sessionid`).
- Quyền rộng (`QUERY_ALL_PACKAGES`, contacts, location, screen-capture detect).
- SDK tracking: Firebase Analytics/Crashlytics, AppsFlyer, Facebook, TikTok, GTM,
  Audience Network — cần chính sách riêng tư + consent.
- `client_tracking.json` trong assets chứa dữ liệu cá nhân mẫu (tên, SĐT, email,
  biển số, toạ độ) — nếu là dữ liệu thật thì đây là rò rỉ nghiêm trọng; khả năng cao là
  fixture test. Cần xác minh nguồn gốc (ngoài phạm vi).
- Chỉ có armeabi-v7a — cân nhắc split 64-bit cho thiết bị mới.

## 11. Tái tạo

```bash
python3 tools/analyze_apk.py --zip "Zalopay-Thanh+toán+&+Tài+chính_11.17.0_APKPure.zip" --out docs/analysis.json
```

Video TypeScript: `cd video && npm install && npm run build` (xem `video/README.md`).

## 12. Hạn chế

- Không decompile DEX→Java (thiếu Java/jadx trong sandbox); phân tích dựa trên strings,
  manifest binary, ZIP inventory và config JSON — đủ cho kiến trúc, chưa đủ cho logic chi tiết.
- Không phân tích động, không kiểm tra TLS pinning, không fuzz API.
- Mọi suy luận “vai trò” của `.so` obfuscated là có căn cứ JNI/strings nhưng cần xác minh thêm.
