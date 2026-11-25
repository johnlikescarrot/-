# BÁO CÁO NGHIÊN CỨU VÀ PHÂN TÍCH NGỨC (REVERSE ENGINEERING)
# Zalopay – Thanh toán & Tài chính 11.17.0 (APKPure)

**Mục tiêu:** Nghiên cứu sâu và phân tích kỹ thuật ngược toàn bộ tệp tin trong gói
`Zalopay-Thanh+toán+&+Tài+chính_11.17.0_APKPure.zip`.
**Ngày thực hiện:** 2026-11-25  
**Nguồn:** APKPure XAPK, phiên bản 11.17.0 (mã bản 1102758)  
**Tác phẩm đi kèm:** video trình diễn kỹ thuật (TypeScript, xuất MP4, âm thanhברה) trong cùng kho.

---

## 1. Tổng quan gói tin

| Thuộc tính | Giá trị |
|---|---|
| Định dạng gói | APKPure XAPK (phiên bản 2) |
| Tên gói (package) | `vn.com.vng.zalopay` |
| Tên hiển thị | Zalopay |
| Phiên bản | 11.17.0 |
| Mã bản (versionCode) | 1102758 |
| minSdk / targetSdk | 26 / 36 |
| Tổng dung lượng | ~105,6 MB |
| Số tệp tách (split APK) | 5 |

### Cấu trúc các split APK (từ `manifest.json`)

| Tệp | Nhóm (id) | Dung lượng | Vai trò |
|---|---|---|---|
| `vn.com.vng.zalopay.apk` | base | ~76 MB | Mã chính, tài nguyên, manifest |
| `config.armeabi_v7a.apk` | config.armeabi_v7a | ~24 MB | Thư viện native ARMv7 |
| `config.en.apk` | config.en | ~76 KB | Tiếng Anh |
| `config.vi.apk` | config.vi | ~88 KB | Tiếng Việt |
| `config.xhdpi.apk` | config.xhdpi | ~1.2 MB | Tài nguyên density xhdpi |

Gói được giải nén và phân tích tĩnh bằng các công cụ mã nguồn mở (Node.js, `strings`, trình giải mã AXML/DEX tự viết) vì môi trường không có Java/Android SDK (do đó **không chạy được apktool/jadx/dex2jar/Frida** trực tiếp). Tất cả phân tích dưới đây là **tĩnh (static analysis)**, không cần thiết bị/model Android thực.

---

## 2. AndroidManifest.xml (giải mã nhị phân AXML)

Manifest được mã hóa theo định dạng AXML (Android binary XML). Trình giải mã tự viết đã khôi phục chuỗi (string pool, 627 mục) và cấu trúc đoạn XML.

### Thông tin khai báo
- **Package:** `vn.com.vng.zalopay`
- **versionCode:** 1102758 / **versionName:** `11.17.0`
- **minSdkVersion:** 26 / **targetSdkVersion:** 36

### Quyền manifest (55 quyền) – các quyền nhạy cảm đáng chú ý

Quyền hệ thống / trực tiếp tác động đến dữ liệu người dùng và thiết bị:

- `READ_CONTACTS`, `WRITE_CONTACTS`, `READ_CALENDAR`, `WRITE_CALENDAR`
- `READ_PHONE_STATE`, `READ_BASIC_PHONE_STATE`
- `ACCESS_FINE_LOCATION`, `ACCESS_COARSE_LOCATION`
- `CAMERA`, `RECORD_AUDIO`, `MODIFY_AUDIO_SETTINGS`
- `READ_EXTERNAL_STORAGE`, `WRITE_EXTERNAL_STORAGE`, `READ_MEDIA_AUDIO`
- `NFC`, `CHANGE_WIFI_STATE`, `ACCESS_WIFI_STATE`
- `QUERY_ALL_PACKAGES` (truy vấn tất cả ứng dụng cài trên thiết bị)
- `FOREGROUND_SERVICE`, `WAKE_LOCK`, `RECEIVE_BOOT_COMPLETED`
- `READ_CONTACTS` + `WRITE_CONTACTS` (đăng ký liên hệ)
- `com.android.vending.BILLING` (thanh toán trong ứng dụng)
- `com.google.android.gms.permission.AD_ID` (quảng cáo)
- `android.settings.action.MANAGE_OVERLAY_PERMISSION` (hiển thị trên các ứng dụng khác)
- `com.zing.zalo.permission.ACCESS_THIRD_PARTY_APP_AUTHORIZATION` (tích hợp Zalo)
- `vn.com.vng.zalopay.DYNAMIC_RECEIVER_NOT_EXPORTED_PERMISSION` (quyền nội bộ định nghĩa)
- và nhiều quyền launcher/shortcut cài đặt biểu tượng.

> **Đánh giá:** Gói yêu cầu nhiều quyền nhạy cảm (liên hệ, vị trí, microphone, máy ảnh, lưu trữ, truy vấn tất cả ứng dụng) phù hợp với một ứng dụng Ví điện tử/Tài chính hoàn chỉnh (eKYC, quét thẻ, thanh toán, gửi tiền), nhưng cũng mở rộng bề rộng bề mặt tấn công (attack surface).

### Thành phần khai báo (dịch từ chuỗi string pool)

**Activities đại diện (vn.com.vng.zalopay.*):**  
`SplashScreenActivityV2`, `HomeActivity`, `LoginActivity`, `OtpVerificationActivity`, `CashierV3Activity`, `MainCashierV2Activity`, `CashInActivity`, `WithdrawActivity`, `ReceiveMoneyActivity`, `PayByWalletReadyToPayActivity`, `ScanQRActivity`, `ScanQRActivityV2`, `BankBindingActivity`, `FundManagementActivity`, `ChangePinActivity`, `ResetPinActivity`, `UpdatePhoneActivity`, `ProfileDetailsActivity`, `AccountSecurityActivity`, `MyQRActivity`, `QuickPayNativeActivity`, `ProtectAccountActivity`, `CloseAccountActivity`, `StorageManagementActivity`, `PdfViewerActivity`, `WebViewActivity`, `InternalWebViewActivity`, `LoginWebViewActivity`, `CashierWebActivity`, v.v.

**eKYC / xác thực danh tính:**
- `com.fpt.fci.ekycfull.presentation.view.EkycActivity` (FPT eKYC)
- `vn.ai.trueid.sdk.activity.TrueIDActivity`, `vn.ai.trueid.sdk.activity.SotpConfirmActivity`
- `vn.ai.trueid.sdk.face.present.presentation.LivenessCameraXActivity`
- `vn.ai.trueid.sdk.facev3.present.presentation.LivenessCameraXActivity`
- `vn.com.vng.zalopay.liveness.presentation.LivenessVerificationActivity`
- `vn.com.vng.zalopay.livenessdetection.presentation.FaceLivenessCameraXActivity`
- `vn.com.vng.zalopay.livenessdetection.presentation.face_auth_revamp.FaceLivenessRevampActivity`
- `vn.com.zalopay.auth.presentation.faceauth.FaceAuthActivity`
- `vn.com.zalopay.identity_verification.presentation.VerificationByVNeIDActivity`
- `vn.com.zalopay.ekycpro.presentation.main.MainEKycActivity`

**Thanh toán / ví / ngân hàng:**
- `vn.com.vng.zalopay.tenpay.TPGPaymentActivity` (Tenpay / WeChat Pay)
- `vn.com.vng.zalopay.alipay.presentation.AlipayEmptyScanQRActivity` (Alipay)
- `vn.com.vng.zalopay.nfc.FundingSolutionNFCActivity`, `vn.com.zalopay.nfc_sdk.nfc_in_house.BottomSheetNFC`
- `vn.com.vng.zalopay.miniapp.api.v2.visa.VisaHostApduService` (Visa NFC/Host Apdu)
- `vn.com.vnpay.vntaxi.*` (đặt taxi qua VNPay)

**Dịch vụ (Services):**
- `vn.com.vng.zalopay.notification.MyFcmListenerService` (Firebase Cloud Messaging)
- `com.google.firebase.messaging.FirebaseMessagingService`
- `androidx.work.impl.background.systemalarm.SystemAlarmService`
- `androidx.work.impl.background.systemjob.SystemJobService`
- `androidx.work.impl.foreground.SystemForegroundService`
- `com.alibaba.griver.core.kernel.ipc.client.IpcMsgClientService*` (Alibaba Griver mini-app)
- `com.iap.android.mppclient.*` (Alipay mini-program container)

**BroadcastReceiver:**
- `vn.com.vng.zalopay.receivers.ZPInstallBroadcastReceiver`
- `com.google.firebase.iid.FirebaseInstanceIdReceiver`
- `androidx.profileinstaller.ProfileInstallReceiver`
- `com.facebook.*` receivers (AuthenticationToken, CurrentAccessToken)
- `androidx.glance.appwidget.*` widget receivers

**ContentProvider:** `vn.com.vng.zalopay.AudienceNetworkContentProvider`, `androidx.core.content.FileProvider`, `com.facebook.sdk.AdvertiserIDCollectionEnabled`, `com.google.android.gms.ads.MobileAdsInitProvider`, `com.google.firebase.provider.FirebaseInitProvider`.

---

## 3. Mã bytecode DEX (Dalvik Executable)

Gói chứa **20 tệp DEX** (`classes.dex` … `classes19.dex` + `assets/audience_network.dex`), cấu trúc multidex chia sẻ ở giới hạn ~65.536 phương thức/lớp mỗi tệp.

| Chỉ số | Giá trị |
|---|---|
| Tổng số lớp (class_def) | ~351.248 lớp |
| Tổng số method_id | ~304.518 phương thức |
| Tệp lớn nhất | `classes.dex` (8,8 MB), `classes2.dex` (16,5 MB), `classes4.dex` (9,4 MB), `classes7.dex` (10,8 MB) |

Các nhóm lớp đáng chú ý được trích xuất từ string pool và symbol reference:

- `vn.com.vng.zalopay.*` + `vn.com.zalopay.*` (logic kinh doanh chính chủ: ví, thanh toán, eKYC, xác thực, tài khoản)
- `com.vnpay.vntaxi.*` (dịch vụ taxi VNPay)
- `com.fpt.fci.ekycfull.*` (eKYC FPT AI)
- `vn.ai.trueid.sdk.*` (TrueID xác thực khuôn mặt/liveness)
- `com.alibaba.griver.*` (khung mini-app Alibaba / Alipay)
- `com.iap.android.mppclient.*` (container mini-program Alipay)
- `com.facebook.*`, `com.facebook.ads.*` (Facebook SDK / Audience Network)
- `com.google.firebase.*`, `com.google.android.gms.*` (Firebase, Google Play Services, Ads)
- `com.appsflyer.*` (thống kê gán thuộc cài đặt)
- `androidx.*` (Jetpack)

---

## 4. Chuỗi quan trọng: Domain và Endpoint API

### 41 domain chính thức (trích từ chuỗi DEX)

```
api.zalopay.vn, atlas.zalopay.vn, bank-profile.zalopay.vn, bim.zalopay.vn,
bnpl-gateway.zalopay.vn, dala2zoom.zalopay.vn, fin-installment.zalopay.vn,
go.zalopay.vn, go.onelink.me, km.zalopay.vn, kmzalopay.onelink.me,
loyalty.zalopay.vn, miniprogram.zalopay.vn, mmf-redemption.zalopay.vn,
mmf-user.zalopay.vn, mnap-director.zalopay.vn, mp.zalopay.vn,
oauth2.zalopay.vn, onelink.zalopay.vn, openapi.zalopay.vn,
partners.zalopay.vn, promotion.zalopay.vn, qrpay.zalopay.vn,
rar-um.zalopay.vn, rdx.zalopay.vn, sapi.zalopay.vn, scard.zalopay.vn,
social.zalopay.vn, sse.zalopay.vn, support.zalopay.vn, ticket.zalopay.vn,
token.zalopay.vn, tracking.zalopay.vn, upload-img.zalopay.vn,
user-life-cycle-public.zalopay.vn, uudai.zalopay.vn,
zlp-movie-api.zalopay.vn, zlp-ofp-emvco-gateway.zalopay.vn,
zlp-ofp-integration-hub.zalopay.vn, zlp-ofp-qp-public-api.zalopay.vn,
zlp-ofp-zpa-camera-config-be.zalopay.vn
```

### Endpoint API đại diện (dịch ngược từ chuỗi)

**Xác thực / tài khoản:** `/v1/auth/getauthcode`, `/v1/auth/getpublicprofile`, `/v2/auth`, `/v2/auth-challenge/nfc/mb-support`, `/v2/account/*`, `/v2/zalo/access-token`, `/v3/auth`

**eKYC / danh tính:** `/v2/ekyc/*`, `/v2/ekyc/upload/trueid/face-liveness`, `/v2/ekyc/upload/vneid/*`, `/v2/user/face-authen/*`, `/v2/user/ekyc-nfc/*`, `/v2/user-liveness/*`, `/v1/camera/detect-qr`, `/v1/emvco/verify`

**Thẻ ngân hàng / ví:** `/v2/bank/*`, `/v2/bank-mapping-pci/web/*` (ánh xạ thẻ PCI), `/v2/topup-withdraw/*`, `/v2/topup/vietQR`, `/v3/cashier/pay`, `/v3/cashier/verify-otp`, `/v001/zte/wallet/*` (Ví ZTE), `/v1/wallet/*`

**Mã PIN / OTP / bảo mật:** `/v2/user/pin/*`, `/v2/pin/reset/*`, `/v2/user/otp/*`, `/v2/user-smartotp/*`, `/v2/user-consent/*`

**Khác:** `/v1/merchant/*` (OAuth đối tác), `/v1/travel/vnpay_taxi/condition` (taxi), `/v3/homepage/*`, `/v4/home/*`, `/api.fpt.ai/vision/ekyc-be/` (FPT AI eKYC), `/api.trueid.vn` (TrueID)

**Mẫu telemetry/quảng cáo (có chỗ chèn %s):** Facebook Ads (`%sadrevenue.%s`, `%sapp.%s`, `%sinapps.%s`, `%simpression.%s`), App Store validation (`%sars.%s`), v.v.

### Domain nghi ngờ (chuỗi có dạng obfuscation / không típPICAL)

- `amcsetxek.dmayljy`
- `jwbmqnsll.xvndbxlk`
- `yydamachy.xqcntsk`

Ba chuỗi trên có cấu trúc ngẫu nhiên, không giống domain thương mại thông thường, có thể là chuỗi đã được biến đổi tên (obfuscated string) dùng trong phần mềm bảo vệ / theo dõi, và cần được quan sát thêm trong phân tích động.

---

## 5. Thư viện native (.so) – trong `config.armeabi_v7a.apk`

Thư viện native được cung cấp riêng cho kiến trúc `armeabi-v7a`. Các tệp đáng chú ý:

| Thư viện | Dung lượng | Đánh giá vai trò |
|---|---|---|
| `libVFaceLib.so` | ~1,96 MB | Nhận dạng khuôn mặt (face) |
| `libtaibmcf.so` | ~12,6 MB | Tên biến đổi, kích thước lớn (bảo vệ mã) |
| `libfgcpkjvqe.so`, `libljsfrcwmw.so`, `liblytcuhe.so`, `libohxbee.so`, `librcmefqonnx.so` | 0,18–6,9 MB | Tên obfuscated (biến đổi tên) |
| `libsqlcipher.so` | ~3,55 MB | Cơ sở dữ liệu mã hóa SQLCipher |
| `libsecurity_lite.so` | ~155 KB | Thư viện bảo vệ ứng dụng |
| `libtrueidlib.so` | ~79,9 KB | TrueID xác thực danh tính |
| `libmlkit_google_ocr_pipeline.so`, `libmlkitcommonpipeline.so` | ~6–6,9 MB | ML Kit OCR (Google) |
| `libpicodet_ncnn.so` | ~3,0 MB | Phát hiện đối tượng (ncnn) |
| `librive-android.so` | ~3,2 MB | Hiệu ứng Rive |
| `libbarhopper_v3.so` | ~3,2 MB | Tên obfuscated |
| `liba97d.so`, `libcrashlytics.so`, `libc++_shared.so`, v.v. | – | Crashlytics, C++ runtime, bản đồ, … |

> Có ít nhất **5–6 thư viện có tên bị biến đổi hoàn toàn** (ngẫu nhiên), là dấu hiệu điển hình của công cụ bảo vệ mã (obfuscator/packer) nhằm khó khoán phân tích ngược.

---

## 6. Đánh giá bảo mật (tóm tắt)

| Mục | Quan sát |
|---|---|
| Mã hoá/bảo vệ | Tên thư viện native obfuscated, multidex, string pool được khai thác, có khả năng dùng trình bảo vệ thương mại |
| Lưu trữ nhạy cảm | `SQLCipher` → cơ sở dữ liệu local được mã hóa |
| Xác thực sinh trắc học | Face liveness, TrueID, VNeID, eKYC FPT, quét CMCD/CCCD qua NFC |
| Thống kê / quảng cáo | Facebook SDK, Ads, AppsFlyer, Google Firebase/Ads → gửi sự kiện thiết bị ra bên ngoài |
| Miền nghi ngờ | 3 domain dạng ngẫu nhiên (`amcsetxek.dmayljy`, `jwbmqnsll.xvndbxlk`, `yydamachy.xqcntsk`) |
| Quyền | Nhiều quyền nhạy cảm (liên hệ, vị trí, microphone, máy ảnh, lưu trữ, truy vấn tất cả ứng dụng) |

---

## 7. Phương pháp công cụ

Do môi trường phân tích không cài Java/Android SDK, nhóm đã tự xây dựng:

1. `axml.js` – trình giải mã AXML (Android binary XML) thuần Node.js, khôi phục `AndroidManifest.xml` (string pool, namespace, phần tử, thuộc tính).
2. `dex_parser.js` – trình phân tích DEX thuần Node.js (header, string_ids, type_ids, method_ids, class_defs), đếm lớp/phương thức, trích xuất chuỗi/API endpoint.
3. `strings` (GNU Binutils) – quét chuỗi in được trên DEX và thư viện native.
4. Giải nén XAPK/APK (zip) và split APK thủ công.

Tất cả mã phân tích và kết quả trung gian được lưu trong thư mục `zalopay_re/` (không commit vào Git do dung lượng lớn).

---

## 8. Tác phẩm đi kèm

Một video trình diễn kỹ thuật được phát triển bằng **TypeScript**, xuất định dạng **MP4**, có âm thanh thanh trầm (narration) và hình ảnh tổng hợp toàn bộ kết quả phân tích trên. Video được quản lý trong cùng kho Git dưới dạng pull request (xem phần Git workflow).

---

## 9. Kết luận

Zalopay 11.17.0 là một ứng dụng tài chính phức tạp, kết hợp nhiều khung sdk (Alibaba Griver, Alipay, Visa, Facebook, Google Firebase/AppsFlyer), xác thực đa yếu tố (OTP, face liveness, VNeID, eKYC FPT/TrueID), và lưu trữ mã hóa (SQLCipher). Bề mặt tấn công rộng do nhiều quyền và nhiều域名/endpoint API. Những tên thư viện bị obfuscated và các domain dạng ngẫu nhiên là điểm nên được ưu tiên trong phân tích động / kiểm tra thâm nhập sau này.

**Tất cả thao tác trên repo được đồng bộ qua pull request (không merge, không cần phê duyệt).**

---
*Báo cáo được tạo tự động từ quá trình phân tích ngược tĩnh của tệp APKPure XAPK.*
