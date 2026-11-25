# ATLAS — ZaloPay 11.17.0 trên một trang

```
                        ┌──────────────────────────────────────────────┐
                        │  Zalopay-...-11.17.0_APKPure.zip   98.8 MB   │
                        │    └── *.xapk                     105.6 MB   │
                        │          ├── vn.com.vng.zalopay.apk  75.7 MiB│  base
                        │          ├── config.armeabi_v7a.apk  23.7 MiB│  27 .so
                        │          ├── config.xhdpi.apk         1.2 MiB│  258 png
                        │          ├── config.vi.apk             85 KiB│  arsc
                        │          └── config.en.apk             73 KiB│  arsc
                        └──────────────────────────────────────────────┘
6,464 ZIP entries · 186.3 MiB uncompressed · 5 APKs · 6,162 entries in base
```

## Tầng 1 — Bytecode
| Chỉ số | Giá trị |
|---|---|
| DEX files | 19 (format 038) |
| Classes | 60,671 |
| Method references | 351,248 |
| String constants | 374,276 |
| Native (JNI) methods | 2,026 |
| Obfuscated packages | 28.5% (25.9% of classes) |
| Saturated DEX (>91% of 65,536 method ids) | classes.dex, classes4.dex, classes7.dex |

## Tầng 2 — Manifest
| Thành phần | Số lượng |
|---|---|
| Activities | 200 |
| Services | 34 |
| Receivers | 22 |
| Providers | 9 |
| Permissions (49 total) | 14 dangerous-class |
| Hardware features | NFC/HCE, camera, BLE, GPS |

## Tầng 3 — Native
| Thư viện lớn nhất | MiB | Vai trò |
|---|---|---|
| libtaibmcf.so | 12.0 | Alibaba Security Guard — risk engine |
| libmlkitcommonpipeline.so | 6.6 | ML Kit pipeline |
| libmlkit_google_ocr_pipeline.so | 6.5 | OCR |
| libfgcpkjvqe.so | 6.2 | Security Guard payload (randomised name) |
| libface_detector_v2_jni.so | 5.1 | ML Kit face |
| libsqlcipher.so | 3.4 | encrypted DB (randomised name) |
| libbarhopper_v3.so | 3.1 | barcode |

Hardening: RELRO 26/27 · BIND_NOW 27/27 · NX 27/27 · PIE 26/27 · canary 25/27.
96.3% stripped. 7 libraries renamed to random strings.

## Tầng 4 — Hệ sinh thái
| Nhóm | Đại diện |
|---|---|
| Runtime siêu app | Alibaba mPaaS / ARiver / Griver — 373 mini-apps |
| eKYC | TrueID (VN), FPT Smart Cloud (VN), ML Kit face/OCR |
| Thanh toán | Visa SDK, Mastercard (không thấy), VNPAY taxi, Google Play Billing |
| Đo lường | Firebase/GA4, AppsFlyer, AdMob, Meta Audience Network, Pangle |
| Bảo mật | Security Guard, Play Integrity, RootBeer, Bouncy Castle (incl. PQC) |

## Tầng 5 — Mạng
168 hosts · 45 first-party (`*.zalopay.vn`, `zalo.me`) · 290 REST paths.
Endpoint tiêu biểu: `/v2/user/public-key`, `/v2/user/salt`, `/v2/user/pin/pre-check-update-pin`,
`/v2/account/otp-verification`, `/v2/ekyc/upload/trueid/face-liveness/v3`, `/v2/cashier/precheck-pay`.

## Tầng 6 — Toàn vẹn
| Kiểm tra | Kết quả |
|---|---|
| Certificate parse | OK — `CN=Vu Manh Hieu`, self-signed, 2016→2041, RSA 2048 |
| v1 JAR signature | **absent** in base APK |
| v2 block framing | **non-canonical** (1,383 declared vs 1,387 actual; duplicate pair) |
| Content digest | **not reproduced** (41 boundaries tested) |
| RSA signature | **not verifiable** (no valid PKCS#1 v1.5 block found) |

> Verdict: *structurally parsed, cryptographically unproven.*

## Ranh giới plaintext
Dừng ở: envelope PIN, trao đổi khoá phiên, PIN block, payload của Security Guard.
Không có bí mật nào bị trích xuất trong nghiên cứu này.
