# Reverse Engineering of ZaloPay for Android — v11.17.0

**Target:** `vn.com.vng.zalopay` · **version** `11.17.0` ·
**versionCode** `1102758` · **APK**
`vn.com.vng.zalopay.apk` · **delivered as** XAPK (APKPure bundle)

**Study type:** static reverse engineering for security research / defensive
analysis. All findings are derived from static inspection of the delivered
binary; no dynamic execution, no exploitation, and no credentials are
extracted or reproduced.

---

## 1. Executive summary

This report documents a full static reverse-engineering study of **ZaloPay
11.17.0**, the Vietnamese digital-payment super-app published by
VNG (`vn.com.vng.zalopay`). The delivered artifact is an **APKPure XAPK**
bundle containing a **base APK** plus four split/configuration APKs.

Key results:

- **5 APKs** analysed; **6,464**
  files enumerated inside them; **20** DEX payloads.
- The app is **63,827 classes** of Dalvik bytecode, of which
  **897 classes / 7,079 methods** belong to the
  app's own `vn.com.vng.zalopay` namespace across **73 feature
  modules**. **98.59%** of the bytecode is
  third-party SDK material.
- Signed with **APK Signature Scheme v2 + v3** (plus Source Stamp v2 and a
  **Google Play Frosting** block `0x2146444e`), and **not** with the legacy
  v1/JAR scheme.
- Attack surface: **16** exported activities,
  **6** exported services,
  **8** exported receivers and
  **0** exported providers, with
  **24** deep-link routes.
- Notable configuration finding: the **`network_security_config` base-config
  enables cleartext HTTP** (`cleartextTrafficPermitted="true"`) at
  `targetSdk 36`.

---

## 2. Methodology, tooling & chain of custody

| Phase | Tool / technique | Output |
|---|---|---|
| Seizure & inventory | ZIP enumeration, MD5/SHA-1/SHA-256 | `001`, `002` |
| Bundle metadata | `manifest.json` parsing | `003` |
| Signing block | **hand-written** APK Signing Block parser | `004` |
| Manifest | binary **AXML** decode (androguard 4.x) | `005`, `manifest-xml-*.xml` |
| DEX headers | **hand-written** DEX header parser | `006` |
| Bytecode | `androguard.core.dex.DEX` class/string/method enumeration | `007`, `008`, `010` |
| SDK fingerprinting | namespace-prefix matching | `009` |
| Attack surface | manifest XML + resource AXML decode | `011`, `012`, `013`, `014` |
| Aggregation | scripted fold of all artefacts | `015`, `016` |

The pipeline is **pure Python** (no Java/JADX/apktool required) and fully
reproducible:

```bash
python3 -m venv work/cache/venv && work/cache/venv/bin/pip install androguard
work/cache/venv/bin/python re/evidence/collect_evidence.py
work/cache/venv/bin/python re/analysis/analyze_dex.py
work/cache/venv/bin/python re/analysis/analyze_manifest.py
work/cache/venv/bin/python re/analysis/analyze_summary.py
work/cache/venv/bin/python re/report/build_report.py
```

**Chain of custody** — the delivered bundle is bound by digest:
`sha256:34b9b6b6735f0f739617f97a259f931d17cdbacb51871c40b066de41f4b4610a`. Every derived artefact records its own
digests, so any downstream claim can be traced back to the exact input bytes.

---

## 3. Artifact seizure & inventory

The delivered archive is a single-file XAPK container holding the base APK and
four split APKs (language `en`/`vi`, density `xhdpi`, ABI `armeabi_v7a`).

| APK | Size (B) | Files | DEX | sha256 (prefix) |
|---|---|---|---|---|
| `config.armeabi_v7a.apk` | 24,828,408 | 32 | 0 | `456aa08f4fa2e3f9…` |
| `config.en.apk` | 74,137 | 6 | 0 | `87699fda97e8a9be…` |
| `config.vi.apk` | 86,425 | 6 | 0 | `985fc073b810b628…` |
| `config.xhdpi.apk` | 1,232,563 | 258 | 0 | `f31350c3caed1509…` |
| `vn.com.vng.zalopay.apk` | 79,359,087 | 6,162 | 20 | `e289d547b45f6224…` |

The base APK alone contains **20** DEX payloads totalling
**69,701,748** bytes. Every file inside
every APK is enumerated with its size, compression method and digests in
`002-apk-file-inventory.json`.

---

## 4. Packaging & signing analysis

### 4.1 APK Signing Block

The APK Signing Block immediately preceding the ZIP central directory was
parsed by hand. The base APK contains the following ID-value blocks:

`APK Signature Scheme v2`, `APK Signature Scheme v3`, `Source Stamp v2`, `Google Play Frosting (Play-signed)`, `Verity padding block`

- **v2 / v3** are the operative signature schemes — the APK is verified through
  the whole-file digests they carry.
- **Source Stamp v2** (`0x6dff800d`) records build provenance.
- **Google Play Frosting** (`0x2146444e`) is present: this block is emitted by
  Google Play and proves the APK was signed/distributed through the Play
  signing pipeline, even though the file was later re-distributed via APKPure.
- **Verity padding** (`0x42726577`) is present as a structural block.
- **No v1 (JAR) signature artefacts** exist under `META-INF` — the APK relies
  exclusively on the v2/v3 signing-block schemes.

### 4.2 Signing certificate

The v2 signing certificate (DER, 721 bytes) has the
public fingerprint:

- **SHA-256:** `59dd31fdf36070a0d7f8c8cde843fac371df7f1a915c0136c9c9b55992e8cdae`

Only the public certificate digest is reported; no private material is
involved in or exposed by this study.

---

## 5. Application & manifest configuration

| Attribute | Value |
|---|---|
| Package | `vn.com.vng.zalopay` |
| Application | `Zalopay` |
| versionName / versionCode | `11.17.0` / `1102758` |
| minSdk / targetSdk | `26` / `36` |
| Main activity | `vn.com.vng.zalopay.splashscreen.presentation.SplashScreenActivityV2` |
| Icon | `res/mipmap-anydpi-v26/ic_launcher.xml` |
| Platform build | `16` |

### 5.1 Security-relevant application flags

| Flag | Value |
|---|---|
| `android:allowBackup` | `false` |
| `android:fullBackupContent` | `false` |
| `android:usesCleartextTraffic` | `None` |
| `android:networkSecurityConfig` | `@7F16000D` |
| `android:extractNativeLibs` | `true` |
| `android:requestLegacyExternalStorage` | `true` |
| `android:largeHeap` | `true` |
| `android:debuggable` | `None` |

Cloud backup is disabled (`allowBackup=false`, `fullBackupContent=false`),
which is good practice for a payments app.

### 5.2 Network security configuration

The referenced `network_security_config.xml` decodes to:

```xml
<network-security-config>
  <domain-config>
    <domain includeSubdomains="true">storage.googleapis.com</domain>
  </domain-config>
  <base-config cleartextTrafficPermitted="true">
    <trust-anchors>
      <certificates src="system"/>
    </trust-anchors>
  </base-config>
  <debug-overrides cleartextTrafficPermitted="true">
    <trust-anchors>
      <certificates src="system"/>
      <certificates src="user"/>
    </trust-anchors>
  </debug-overrides>
</network-security-config>

```

The **`<base-config>`** element explicitly sets
`cleartextTrafficPermitted="true"` for the default trust-anchor set (system
CAs). Because the app targets SDK 36 (where cleartext is
opt-in), this configuration **re-enables plaintext HTTP** for the app's
default network policy. The `<debug-overrides>` additionally trusts user
CAs. A `<domain-config>` for `storage.googleapis.com` is the only per-domain
override. This is a concrete, evidence-bounded configuration observation: any
endpoint reached over plaintext would not be protected by the TLS requirement
unless individually pinned in code.

---

## 6. Permissions

- **Requested:** **49** permissions.
- **Declared (custom):** **1**.

The requested set is dominated by payment, identity, connectivity and
telemetry capabilities typical of a super-app (full list in
`012-permissions.json`).

---

## 7. Attack-surface & component analysis

| Component type | Total | Exported |
|---|---|---|
| Activities | 202 | 16 |
| Services | 34 | 6 |
| Receivers | 22 | 8 |
| Providers | 9 | 0 |

**All 9 providers are non-exported**, including
`FileProvider` — a positive hardening signal. The exported surface is
concentrated in the entry-point activities that handle deep links.

### 7.1 Providers (all `exported=false`)

| Provider | Exported | Authority |
|---|---|---|
| `androidx.core.content.FileProvider` | `false` | `@7F1304C0` |
| `com.facebook.internal.FacebookInitProvider` | `false` | `vn.com.vng.zalopay.FacebookInitProvider` |
| `com.google.firebase.perf.provider.FirebasePerfProvider` | `false` | `vn.com.vng.zalopay.firebaseperfprovider` |
| `com.google.mlkit.common.internal.MlKitInitProvider` | `false` | `vn.com.vng.zalopay.mlkitinitprovider` |
| `com.google.firebase.provider.FirebaseInitProvider` | `false` | `vn.com.vng.zalopay.firebaseinitprovider` |
| `com.google.android.gms.ads.MobileAdsInitProvider` | `false` | `vn.com.vng.zalopay.mobileadsinitprovider` |
| `androidx.startup.InitializationProvider` | `false` | `vn.com.vng.zalopay.androidx-startup` |
| `com.facebook.ads.AudienceNetworkContentProvider` | `false` | `vn.com.vng.zalopay.AudienceNetworkContentProvider` |
| `com.alibaba.griver.file.utils.GriverFileProvider` | `false` | `vn.com.vng.zalopay.griver.file.provider` |

### 7.2 Deep links

**24** deep-link routes were recovered from intent filters:

| Scheme | Host | Path | Component |
|---|---|---|---|
| `https` | `go.zalopay.vn` | `None` | `vn.com.vng.zalopay.splashscreen.presentation.SplashScreenActivityV2` |
| `https` | `onelink.zalopay.vn` | `None` | `vn.com.vng.zalopay.splashscreen.presentation.SplashScreenActivityV2` |
| `https` | `kmzalopay.onelink.me` | `None` | `vn.com.vng.zalopay.splashscreen.presentation.SplashScreenActivityV2` |
| `https` | `km.zalopay.vn` | `None` | `vn.com.vng.zalopay.splashscreen.presentation.SplashScreenActivityV2` |
| `zalopay` | `app` | `None` | `vn.com.vng.zalopay.splashscreen.presentation.SplashScreenActivityV2` |
| `zalopay-vnpay` | `None` | `None` | `vn.com.vng.zalopay.splashscreen.presentation.SplashScreenActivityV2` |
| `https` | `go.zalopay.vn` | `None` | `vn.com.zalopay.identity_verification.presentation.VerificationByVNeIDActivity` |
| `fbconnect` | `cct.vn.com.vng.zalopay` | `None` | `com.facebook.CustomTabActivity` |
| `tagmanager.c.vn.com.vng.zalopay` | `None` | `None` | `com.google.android.gms.tagmanager.TagManagerPreviewActivity` |
| `zalopay-1` | `post` | `None` | `vn.zalopay.apps.ThirdPartyAppActivityAlias` |
| `zalopay-1` | `backtoapp` | `None` | `vn.zalopay.apps.ThirdPartyAppActivityAlias` |
| `zalopay-1` | `backtologin` | `None` | `vn.zalopay.apps.ThirdPartyAppActivityAlias` |
| `zalopay` | `zalopay.vn` | `None` | `vn.zalopay.apps.ThirdPartyAppActivityAlias` |
| `zalopay` | `otp` | `None` | `vn.zalopay.apps.ThirdPartyAppActivityAlias` |
| `zalopay` | `pay` | `None` | `vn.zalopay.apps.ThirdPartyAppActivityAlias` |
| `zalopay` | `clearing` | `None` | `vn.zalopay.apps.ThirdPartyAppActivityAlias` |
| `zalopay` | `None` | `None` | `vn.zalopay.apps.ThirdPartyAppActivityAlias` |
| `zalopay` | `merchant` | `None` | `vn.zalopay.apps.ThirdPartyAppActivityAlias` |
| `zalopay` | `events` | `None` | `vn.zalopay.apps.ThirdPartyAppActivityAlias` |
| `zalopay.api.v2` | `pay` | `None` | `vn.zalopay.apps.ThirdPartyAppActivityAlias` |
| `zalopay.api.v2` | `auth` | `None` | `vn.zalopay.apps.ThirdPartyAppActivityAlias` |
| `zalopay.api.v2` | `recoverpassword` | `None` | `vn.zalopay.apps.ThirdPartyAppActivityAlias` |
| `zalopay` | `promotions` | `None` | `vn.zalopay.apps.ThirdPartyAppActivityAlias` |
| `zalopay` | `cashier` | `None` | `vn.zalopay.apps.ThirdPartyAppActivityAlias` |

The `zalopay://` and `zalopay-1://` custom schemes (plus `https://go.zalopay.vn`,
`onelink.zalopay.vn`, `km.zalopay.vn`, `kmzalopay.onelink.me`) are the app's
inter-app and web-to-app entry points. A `fbconnect://` route is handled by a
Meta `CustomTabActivity`, and a `tagmanager.c.vn.com.vng.zalopay` route by
Google Tag Manager's preview activity.

---

## 8. Code architecture (DEX)

The app's own `vn.com.vng.zalopay` namespace comprises **897
classes** and **7,079 methods**, organised into **73
feature modules**. The largest are:

`bankbinding` (200), `homev5` (116), `data` (72), `webapp` (43), `miniapp` (39), `alipay` (34), `search` (30), `livenessdetection` (18), `network_monitor` (18), `authentication` (17), `cashin` (14), `withdraw` (14)

This module layout maps cleanly onto the product surface of a payment
super-app: onboarding/KYC (`bankbinding`, `passport`, `authentication`,
`livenessdetection`), the home feed (`homev5`), payments (`cashin`, `withdraw`,
`quickpay_native`, `alipay`, `cashier`, `receive_money`, `clearing`), web/mini
apps (`webapp`, `miniapp`), NFC (`nfc`), documents (`pdfviewer`) and
hardening/telemetry (`protect`, `network_monitor`, `data`).

Of the **63,827** total classes, **98.59%**
come from third-party libraries — the app's own logic is a minority of the
shipped bytecode, which is characteristic of heavily SDK-mediated payment apps.

---

## 9. Third-party SDK / library inventory

Detected by namespace fingerprinting across all DEX payloads:

| SDK / library | Classes |
|---|---|
| Google Play Services | 5,933 |
| Alibaba SDK | 4,821 |
| AndroidX | 4,250 |
| Meta / Facebook SDK | 3,622 |
| VNG internal | 958 |
| Alipay SDK | 493 |
| OkHttp | 335 |
| RxJava | 266 |
| Google Material Components | 234 |
| Firebase | 221 |
| Glide | 213 |
| Retrofit | 137 |
| Picasso | 81 |
| Dagger | 51 |
| Kotlin stdlib / coroutines | 23 |
| EventBus | 20 |
| ZXing | 17 |

Notable: heavyweight ad/analytics integrations (**Meta Audience Network**,
**AppsFlyer**, **Google Play Services / Ads**), payment rails (**Alipay**,
**Visa Pay SDK**, **VNPay**, **TrueID**), Alibaba's **river/griver** engine,
and a full modern Android stack (OkHttp, Retrofit, RxJava, Dagger, Glide,
Picasso, AndroidX, Compose, Media3, CameraX).

---

## 10. Network surface

**405** unique HTTP(S) endpoints were recovered
from the string pool. The dominant hosts:

| Domain | Occurrences | Class |
|---|---|---|
| `scdn.zalopay.com.vn` | 65 | ZaloPay / Zalo domain |
| `simg.zalopay.com.vn` | 45 | ZaloPay / Zalo domain |
| `render.alipay.com` | 24 | other |
| `zalopay.vn` | 20 | ZaloPay / Zalo domain |
| `api.zalopay.vn` | 15 | ZaloPay production API |
| `social.zalopay.vn` | 13 | ZaloPay / Zalo domain |
| `play.google.com` | 11 | Google Play |
| `appx` | 10 | other |
| `sapi.zalopay.vn` | 10 | ZaloPay / Zalo domain |
| `appx-ng` | 9 | other |
| `developer.android.com` | 8 | other |
| `mnap-director.zalopay.vn` | 8 | ZaloPay / Zalo domain |
| `www.w3.org` | 8 | other |
| `www.google.com` | 8 | Google |
| `github.com` | 7 | other |
| `firebase.google.com` | 7 | Google domain |
| `googleads.g.doubleclick.net` | 7 | Google domain |
| `issuetracker.google.com` | 6 | Google domain |
| `xml.org` | 6 | other |
| `googlemobileadssdk.page.link` | 6 | Google domain |

The endpoint set is overwhelmingly ZaloPay-owned infrastructure
(`*.zalopay.vn`, `*.zalopay.com.vn`) for API, CDN/media, social, KYC, bank
profile, redemption and life-cycle services, plus payment-provider hosts
(Alipay render/openauth) and standard Google/Meta telemetry endpoints.

---

## 11. Cryptographic & behavioural indicators

Keyword classification of the 269,342 unique
strings (counts, not vulnerabilities):

| Indicator class | String hits |
|---|---|
| api_endpoint | 5092 |
| auth_token | 111 |
| crypto | 4577 |
| signing | 854 |
| root_anti_debug | 336 |
| ssl_pinning | 2757 |
| payment | 5865 |
| storage | 446 |
| telemetry | 4063 |
| native_bridge | 1917 |
| obfuscation | 637 |

These are presence indicators: they confirm the app contains payment,
crypto, signing, telemetry, SSL/pinning and anti-tamper/anti-debug logic.
They are **not**, by themselves, findings of insecurity — they are the
material a dynamic analyst would target next.

---

## 12. NFC payment

A host-APDU service is declared (`res/xml/apdu_service.xml`) enabling NFC card
payment. The AID (Application Identifier) filters advertise support for:

- `325041592E5359532E444463031` — ASCII `PAY.SYS.DDF01` (payment system
  selection),
- `A0000000980840` — **Visa**,
- `A0000000032020`, `A0000000032010`, `A0000000033010`, `A0000000031010` —
  major card-network AIDs.

This confirms the app implements contactless card-present payment on top of its
in-app wallet.

---

## 13. Security observations (defensive framing)

Evidence-bounded observations, ordered by practical relevance:

1. **Cleartext HTTP re-enabled at the base network policy.**
   `network_security_config.xml` sets `<base-config
   cleartextTrafficPermitted="true">` at `targetSdk 36`.
   Combined with system-CA trust only, this means plaintext is not blocked by
   the platform default; endpoint-specific protection would have to come from
   code-level pinning.
2. **Backup disabled** — `allowBackup=false` / `fullBackupContent=false`
   reduces data-at-rest exfiltration via cloud backup.
3. **Provider surface contained** — all 9 providers are `exported=false`,
   including `FileProvider`.
4. **Signed v2+v3, Play-frosted, no v1** — modern signing posture; the Frosting
   block indicates Play-pipeline origin.
5. **Large, SDK-heavy surface** — 63,827 classes, 17
   detected third-party SDKs; the majority of the attack surface is
   third-party, so SDK supply-chain hygiene dominates risk.
6. **Broad exported entry points + 24 deep links** — the deep-link surface is
   the natural focus for inter-app interaction testing.

---

## 14. Limitations & ethics

- This is a **static** study. Behaviour, network calls at runtime, decrypted
  payloads and server-side logic were not exercised.
- Findings are reported in a **defensive/research** framing. No credentials,
  keys, exploits or bypass techniques are reproduced. Only public certificate
  digests and configuration facts are stated.
- Reverse engineering is performed on a publicly distributed binary for
  interoperability and security research; the app's own terms and applicable
  law should govern any further use.

---

## 15. Data-file index

| File | Contents |
|---|---|
| `re/evidence/000-summary.json` | run summary & digests |
| `re/evidence/001-artifact-inventory.json` | delivered-bundle inventory |
| `re/evidence/002-apk-file-inventory.json` | every file in every APK |
| `re/evidence/003-xapk-manifest.json` | APKPure bundle manifest |
| `re/evidence/004-apk-signing-block.json` | signing-block schemes & cert digests |
| `re/evidence/005-android-manifest.json` | manifest metadata |
| `re/evidence/manifest-xml-*.xml` | decoded manifests |
| `re/analysis/006-dex-inventory.json` | per-DEX stats |
| `re/analysis/007-class-inventory.json` | class/namespace inventory |
| `re/analysis/008-strings-urls.json` | endpoints, domains, keyword hits |
| `re/analysis/009-sdk-inventory.json` | SDK detection |
| `re/analysis/010-app-method-index.json` | app-namespace method index |
| `re/analysis/011-components-exported.json` | components, exports, deep links |
| `re/analysis/012-permissions.json` | permissions |
| `re/analysis/013-application-flags.json` | application flags |
| `re/analysis/014-resource-configs.json` | decoded resource XML |
| `re/analysis/015-app-architecture.json` | architecture/module map |
| `re/analysis/016-security-indicators.json` | consolidated indicators |

---

*Report generated by `re/report/build_report.py` from the evidence JSON — all
figures are reproducible.*
