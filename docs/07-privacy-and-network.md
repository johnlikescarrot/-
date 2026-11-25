# 07 — Privacy, permissions and network posture

## 1. Permission set

49 declared permissions; 27 map to privacy-relevant Android capabilities.

| Risk | Permissions |
|---|---|
| **High (9)** | `READ_CONTACTS`, `WRITE_CONTACTS`, `CAMERA`, `RECORD_AUDIO`, `ACCESS_FINE_LOCATION`, `ACCESS_COARSE_LOCATION`, `READ_PHONE_STATE`, `QUERY_ALL_PACKAGES`, overlay capability (`MANAGE_OVERLAY_PERMISSION`) |
| **Medium (12)** | `READ/WRITE_CALENDAR`, `READ/WRITE_EXTERNAL_STORAGE`, `NFC`, `RECEIVE_BOOT_COMPLETED`, `AD_ID`, `ACCESS_ADSERVICES_*`, `FOREGROUND_SERVICE`, Zalo SSO authorisation |
| **Low (6)** | `POST_NOTIFICATIONS`, `USE_BIOMETRIC`/`USE_FINGERPRINT`, `DETECT_SCREEN_CAPTURE`, `READ_MEDIA_AUDIO`, OEM survey permissions |

Two are worth highlighting:

* **`QUERY_ALL_PACKAGES`** grants visibility of *every installed app*. For a
  wallet, the defensible subset is a `<queries>` allow-list for the specific
  partner apps (Zalo, banks) — and indeed four `<queries>` blocks exist. Holding
  both indicates the broad permission is a convenience fallback.
* **`READ_CONTACTS` + `WRITE_CONTACTS` + `READ/WRITE_CALENDAR`.** Writing contacts
  and calendar events is unusual for a payment app and is the kind of capability
  that Play's sensitive-permission policy expects to be justified by a core
  feature (e.g. bill splitting with contact sync). We do not assert misuse; we
  note the disproportion.

## 2. Identifier access

The DEX contains 37 strings matching device-identifier APIs
(`getDeviceId`, `ANDROID_ID`, `AdvertisingIdClient`, `getMacAddress`,
`getImei`, `getSubscriberId`, `getSimSerialNumber`). Because strings are not
stripped (F3), these are *reachable* names, not proof of invocation. The
combination of AppsFlyer + Meta Audience Network + Firebase makes advertising-ID
and device-identifier collection near-certain in practice.

## 3. Network endpoints

151 distinct hosts, 1,584 URLs, 242k reachable string constants. After
registrable-domain roll-up (two-label heuristic + multi-part TLD list):

| Domain | URLs |
|---|---|
| `zalopay.vn` | 35 |
| `alipay.com` | 14 |
| `google.com` | 8 |
| `zalopay.com.vn` | 8 |
| `visa.com` | 6 |
| `alipaydev.com` | 4 |
| `zaloapp.com` | 4 |
| `zing.vn` | 4 |
| `alipayobjects.com` | 3 |
| `googleapis.com` | 3 |
| `facebook.com` | 3 |
| `fpt.ai` | 3 |

The long tail (139 domains) is SDK infrastructure: CDNs, crash endpoints,
attribution endpoints, OEM app-market hosts.

## 4. Non-production hosts (F5)

Ten staging/sandbox strings ship in the release build, including one **cleartext**
Alipay development gateway
(`http://iopengw-280.sggz00b.dev.alipay.net/`), an FPT eKYC UAT base URL, a
Tenpay Global UAT OAuth page, a Visa sandbox host and a ZaloPay sandbox regex
(`^((.+)\.)?zpsandbox\.zing\.vn`). None are referenced by user-facing flows we
can observe statically, but their presence is exactly what a staging-environment
scanner is designed to find, and shipping them is avoidable with resource-level
configuration.

## 5. Screen capture and overlay

`DETECT_SCREEN_CAPTURE` (API 34+) plus `FLAG_SECURE` strings indicate awareness of
screen-recording attacks on QR/payment screens. The overlay-capability permission
(`MANAGE_OVERLAY_PERMISSION`, `SYSTEM_ALERT_WINDOW`-adjacent) is the opposite
direction: drawing over other apps is a known tapjacking vector, and a wallet
holding it must justify the feature (likely floating mini-app or chat-head).

## 6. Data-at-rest

`SQLCipher` is present (3.39 MiB native + `net.sqlcipher` classes) and the DEX
contains `PRAGMA key` and `SQLiteDatabase.loadLibs` strings, so local databases
are encrypted. `allowBackup=false` and `dataExtractionRules` block cloud/ADB
extraction. `requestLegacyExternalStorage=true` is inert on modern targets but
signals legacy assumptions. Net: data-at-rest posture is good — better than the
network posture.

## 7. Comparative reading

For a Vietnamese wallet the permission set is not unusual (MoMo, VNPay and Shopee
carry comparable sets), which is precisely why it is worth measuring: the
industry baseline is permissive, and regulators increasingly ask for
proportionality rather than mere disclosure. Our contribution is the machine
-readable baseline, not a verdict.
