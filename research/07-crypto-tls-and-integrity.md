# 07 · Transport security, cryptography and integrity

> Scripts: `06_signals.py`, `05_native.py` · Data: `data/06-signals.json`

## 7.1 Network security config — the one clear own-goal

`res/xml/network_security_config.xml` decodes to:

```xml
<network-security-config>
  <domain-config>
    <domain includeSubdomains="true">storage.googleapis.com</domain>
  </domain-config>
  <base-config cleartextTrafficPermitted="true">
    <trust-anchors><certificates src="system"/></trust-anchors>
  </base-config>
  <debug-overrides cleartextTrafficPermitted="true">
    <trust-anchors>
      <certificates src="system"/><certificates src="user"/>
    </trust-anchors>
  </debug-overrides>
</network-security-config>
```

Reading, in order of importance:

1. **`cleartextTrafficPermitted="true"` applies to the whole app.** Every `http://` request is
   legal unless a library overrides it. The DEX confirms the pattern is real, not vestigial:
   **18 explicit `http://` references** survive the exclusion of XML-schema noise, including
   partner infrastructure such as `http://iopengw-280.sggz00b.dev.alipay.net/` (a **sandbox
   host compiled into a release build**, finding F-08).
2. `debug-overrides` trusting user CAs is standard and only effective on `debuggable` builds —
   and `android:debuggable` is absent (false), so it is inert in production. Its presence is
   still a marker that the file was copied from a template.
3. The `<domain-config>` for `storage.googleapis.com` declares no `cleartextTrafficPermitted`
   attribute, so it inherits `true` from `base-config` — it changes nothing. This is the
   signature of a config that was *intended* to be restrictive and ended up permissive because
   the attribute landed on `base-config` instead of the `domain-config`.

Industry baseline for a payment client is `cleartextTrafficPermitted="false"` plus a short
allow-list of legacy hosts during migration. The static study can prove the policy, not whether
any real endpoint is actually reached over HTTP — that requires traffic capture (see §10.4).

## 7.2 Certificate pinning: present, but only where it was typed

| pinning artefact | hits |
|---|---:|
| `CertificatePinner` (OkHttp API) | 15 |
| `sha256/` literal pin format | 8 |
| `sha1/` (legacy pin format) | 2 |
| `pinning` (generic strings) | 5 |
| `pin-set` (declarative/network-security-config style) | 1 |
| **total** | **31** |

The presence of `sha256/` strings is the strongest evidence: OkHttp pins are expressed as
`sha256/<base64 SPKI digest>`. Eight such literals mean **specific hosts are pinned in code**,
not via `network-security-config` (which contains no `<pin-set>` at all — consistent with the
`pin-set` hit count of 1, a code constant rather than an XML element).

The structural weakness is *coverage*: OkHttp pins apply per `OkHttpClient`. Any component that
builds its own client (Griver's WebView networking, Alipay's `SecurityGuardLite`, VNPAY,
AppsFlyer, Crashlytics) bypasses them. And a WebView cannot be pinned by OkHttp at all. The
correct control is an allow-list *inside the network security config* (which the platform
enforces for every stack) **plus** code pins for critical APIs — this app has the second without
the first.

## 7.3 Cryptography inventory (string-level)

| primitive / API family | hits | comment |
|---|---:|---|
| `crypto_primitives` family | 197 | includes 17 distinct markers |
| `keystore_hardware` family | 20 | `AndroidKeyStore` 3, `KeyGenParameterSpec` 11, `setUserAuthenticationRequired` 1, `setIsStrongBoxBacked` 1 |
| `biometrics` | 25 | `BiometricPrompt`, `CryptoObject`, `setAllowedAuthenticators` |
| `jwt_oauth` | 466 | `Bearer`, `access_token`/`refresh_token`, JOSE (Nimbus) |
| `integrity_attestation` | 96 | `SafetyNet` 50, `Attestation` 29, `IntegrityManager` 10, `requestIntegrityToken` 4 |
| `anti_tamper_packaging` | 622 | `getPackageInfo`, `signatures`, `PackageManager` |

Interpretation:

* **Hardware-backed key use is present but thin** — one `setIsStrongBoxBacked`, one
  `setUserAuthenticationRequired`. For a wallet, biometric-gated keys bound to
  `setUserAuthenticationParameters` are the expected minimum for payment confirmation; here the
  pattern suggests a single dedicated key (likely the PIN/OTP vault) rather than pervasive use.
* **`SafetyNet` outnumbers `PlayIntegrity` 50 : 2.** SafetyNet Attestation was deprecated in 2024
  and shut down for new integrations; 50 residual strings mean the SDK still carries the old path
  and may fall back to it, which will fail on modern devices. The presence of
  `requestIntegrityToken` (4) shows Play Integrity was adopted — the migration is *in progress*,
  not complete.
* **Three crypto providers coexist**: BouncyCastle (6 112 classes), platform JCA
  (`AES/GCM/NoPadding`), and native white-box (`libtaibmcf.so`, Alipay `SecurityGuardLite`,
  TrueID vault). Correctness depends on every call site picking the right one; there is no
  static evidence of a single facade enforcing that.

## 7.4 Application integrity and anti-tamper

| family | markers present | hits |
|---|---|---:|
| root detection | `/system/bin/su`, `/system/xbin/su`, `/sbin/su`, `superuser`, `Superuser.apk`, `test-keys`, `ro.debuggable`, `isRooted`, `Magisk` | 11 |
| hook / debug detection | `frida`, `xposed`, `substrate`, `TracerPid`, `isDebuggerConnected`, `de.robv` | 14 |
| emulator detection | `goldfish`, `qemu`, `ro.kernel.qemu`, `Genymotion`, `sdk_gphone`, `generic_x86` (in libs) | 17 |
| app shielding | `bshield`, `Bshield`, `taibmcf` | 7 |
| screenshot protection | `FLAG_SECURE`, `DETECT_SCREEN_CAPTURE` (permission) | 2 |

`libtrueidlib.so` adds native-level checks the DEX cannot show:

```
/data/adb/magisk        /data/adb/magisk.db     /sbin/.magisk
/cache/.disable_magisk  /dev/.magisk.unblock
SmartOTPHelper_vaultCheckDebugger   vaultCheckIntegrity
26_Secure_Vault_TrueID_Shield_20TrueID_Shield_V6
```

The `Magisk` path list is a 2023-era detection set; current Magisk hides `/data/adb` from
unprivileged apps via `DenyList`/Zygisk, so this SDK's check is **capability present, efficacy
degrading** — the exact nuance a "string count" study must not flatten into "protected".

## 7.5 On-device ML: the models and their threat surface

17 models, 12.9 MB total:

| model | bytes | job |
|---|---:|---|
| `custom_models/object_labeler.tflite` | 3 770 391 | object classification (likely ID-document type) |
| `FastestDet.ncnn.bin` + `.param` | 2 420 588 | NCNN detector |
| `mlkit_odt_default_classifier/…` | 1 706 957 | ML Kit object detection/classification |
| `mlkit_odt_localizer/…` | 1 254 848 | ML Kit localiser |
| `mlkit_barcode_models/barcode_ssd…tflite` | 390 456 | barcode localisation |
| `mlkit-google-ocr-models/taser/rpn_text_detector…` | 340 760 | OCR text detection |
| `mlkit-google-ocr-models/gocr/…tflite_langid` | 315 520 | OCR language ID |
| `models_bundled/fssd_*` (4 files) | 232–613 KB each | face/object SSD variants |
| `res/raw/haarcascade_frontalface_default.xml` | — | classic Haar face cascade |

Design reading: **inference is on-device and two vendors deep** (Google ML Kit + a Vietnamese
face stack). That is the privacy-preferable architecture — ID-card images need not leave the
phone for OCR. It also means the models themselves are attack surface: a malformed image that
crashes the TFLite interpreter inside a payment app is a denial-of-service primitive, and
`IdentityCardCaptureActivity` is exported (§3.2), so the input can come from any app.

## 7.6 Keystore and credential storage

* `SQLCipher` (`net.zetetic`, `libsqlcipher.so`) encrypts local DBs — the correct baseline; the
  key must come from Android Keystore.
* `allowBackup=false` + `dataExtractionRules` excluding AppsFlyer DBs → no cloud exfiltration of
  purchase attribution data.
* `file_paths.xml` exposes **`cache-path name="cache" path="/"`** (the whole cache directory) and
  `external-cache-path name="files_share" path="."` through a `FileProvider` with
  `grantUriPermissions="true"`. Provider `exported=false` keeps it out of reach unless a grant is
  issued, but the *breadth* of `path="/"` means any future grant of that authority can expose
  every cached file, including images and downloaded PDFs. Scoping to `images/` (as the
  `zpimages` entry already does) would be strictly better.

## 7.7 Scorecard for this domain

| control | verdict |
|---|---|
| APK signature v2+v3, no v1 | ✅ |
| Play source stamp preserved | ✅ |
| OkHttp TLS pinning on some hosts | ✅ (coverage unknown) |
| platform-level pin-set / cleartext denial | ❌ (`cleartextTrafficPermitted="true"`) |
| hardware-backed keys, biometric-gated | ⚠️ present but narrow |
| Play Integrity adopted | ⚠️ migration incomplete (SafetyNet residue 50:2) |
| root/hook/emulator detection | ✅ capability, ⚠️ efficacy |
| app shielding | ✅ native-only (Bangcle BShield) |
| on-device inference | ✅ privacy-positive, ⚠️ input surface |
| encrypted local storage | ✅ SQLCipher |
