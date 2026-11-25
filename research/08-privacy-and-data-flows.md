# 08 · Privacy posture and data flows (static view)

> Script: `06_signals.py` · Data: `data/06-signals.json`

## 8.1 The permission ledger, scored

A payments app legitimately needs: network, camera (QR), NFC, biometrics, notifications,
foreground service. ZaloPay's 49 permissions exceed that set in five clusters:

| cluster | permissions | feature that justifies it | minimisation verdict |
|---|---|---|---|
| contacts + calendar (6) | `READ/WRITE_CONTACTS`, `READ/WRITE_CALENDAR` | P2P transfer picker, bill reminders | **high liability** — both are "all-or-nothing" Android grants; scoped pickers (`ACTION_PICK`) would avoid them |
| device & package enumeration | `READ_PHONE_STATE`, `READ_BASIC_PHONE_STATE`, `QUERY_ALL_PACKAGES`, `ACCESS_WIFI_STATE` | device binding, fraud scoring, risk engine | **medium** — Play restricts `QUERY_ALL_PACKAGES`; justification must be documented |
| location | `ACCESS_COARSE_LOCATION`, `ACCESS_FINE_LOCATION` | agent/merchant network, fraud | **medium** — fine location for a wallet should be geofence-grade or absent |
| audio | `RECORD_AUDIO`, `MODIFY_AUDIO_SETTINGS`, `READ_MEDIA_AUDIO` | voice OTP? customer support? | **needs justification** — no obvious core feature |
| ads / attribution (7) | `ACCESS_ADSERVICES_*`, `AD_ID`, `BIND_GET_INSTALL_REFERRER_SERVICE` | marketing measurement | **expected but optional** — a wallet can ship without AdID |

`DETECT_SCREEN_CAPTURE` and `FLAG_SECURE` are *protective*; `USE_BIOMETRIC`/`USE_FINGERPRINT` are
core. `WRITE_SETTINGS`-family launcher permissions (7 entries from Samsung/Huawei/BBK) are OEM
shortcut plumbing that has been obsolete for years — harmless, but they inflate the permission
list reviewers must read.

## 8.2 Sensitive API surface in the string table

| family | markers with hits | count |
|---|---|---:|
| device identifiers | `getDeviceId` 7, `getImei` 2, `getSubscriberId` 2, `getMacAddress` 1, `getAdvertisingId` 6 | 18 |
| accounts / packages | `getAccounts` 3, `getInstalledPackages` 1 | 4 |
| contacts | `ContactsContract` 4 | 4 |
| clipboard | `ClipboardManager` 4 | 4 |
| location | `getLastKnownLocation` 1 | 1 |

`getImei`/`getSubscriberId` exist in the binary for legacy SDK compatibility; on API 29+ they
throw or return null unless the app holds privileged permissions it cannot get. Their presence is
*technical debt with privacy optics*, and Play's data-safety declaration is where it must be
reconciled. `ClipboardManager` (4 hits) matters for a wallet: reading a copied account number is a
feature; reading arbitrary clipboard content is a privacy violation, and Android 12+ shows a
toast whenever it happens.

## 8.3 Where data can go: 151 hosts, classified

| destination class | hosts | refs | data that plausibly flows |
|---|---:|---:|---|
| ZaloPay first-party | 43 | 122 | account, transactions, KYC artefacts (encrypted, server-controlled) |
| Google | 21 | 54 | ads ID, crash traces, FCM token, Play Integrity verdicts, Maps queries |
| payment schemes/PSPs | 20 | 30 | instrument tokens, 3DS challenges |
| Facebook/Meta | 4 | 10 | app events, ad attribution |
| AppsFlyer | (attribution) | — | install + in-app events |
| VNG group | 5 | 6 | cross-app SSO (`com.zing.zalo` permission) |
| unclassified | 53 | 71 | partner APIs, CDNs, sandboxes (`*.dev.alipay.net`) |

The **53 unclassified hosts** are the honest weak point of any static privacy review: they are
strings without a vendor fingerprint. Turning them into a data-flow graph requires either
dynamic interception or cross-referencing TLS certificates — a well-defined next step (§10.4).

## 8.4 Data-minimisation design signals (positive)

1. `allowBackup="false"` and explicit `dataExtractionRules` exclusion of `appsflyer-data`,
   `appsflyer-purchase-data`, `afpurchases.db` — purchase attribution is deliberately kept out of
   cloud backup and device transfer.
2. **Inference on-device** (17 models) — ID-card OCR and face liveness do not require uploading
   raw images to a server for the common path.
3. Consolidation to **one** attribution SDK (AppsFlyer) with no Adjust/Branch/CleverTap residue.
4. `FLAG_SECURE` + `DETECT_SCREEN_CAPTURE` — anti-screenshot for a payments UI.
5. Rate-scoped `FileProvider` cache sharing (though over-broad, see §7.6).

## 8.5 Privacy risk register (static)

| id | risk | severity | evidence |
|---|---|---|---|
| P-01 | Contact/calendar grants exceed feature need | medium | 6 permissions, no scoped-picker evidence |
| P-02 | Ad-ID + AdServices stack inside a wallet | medium | 7 permissions, `AD_ID`, AdMob provider |
| P-03 | `QUERY_ALL_PACKAGES` inventory capability | medium | permission + `getInstalledPackages` string |
| P-04 | Clipboard access without visible scope | low | 4 `ClipboardManager` hits |
| P-05 | 53 unclassified egress hosts | medium (unknown) | `06-signals.json → domains` |
| P-06 | Partner sandbox host in release config | low | `*.dev.alipay.net` string |

## 8.6 What this study cannot say

It cannot say whether a given permission is *exercised*, whether data leaves the device, or
whether consent strings are honoured. Those are runtime properties. The static contribution is a
**precise, reproducible inventory** of the capability set — the input any DPIA (data protection
impact assessment) needs before the dynamic phase begins.
