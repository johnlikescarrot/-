# 03 — Manifest and attack surface

## 1. Component inventory

| Component type | Count | Exported |
|---|---|---|
| Activities (incl. aliases) | 202 | 12 |
| Services | 34 | 7 |
| Receivers | 22 | 6 |
| Providers | 9 | 1 (`FileProvider` via grant URIs) |
| **Total** | **267** | **30** |

Thirty exported components is high but not unusual for a super-app that must
answer deep links, app widgets and NFC. What matters is *which* ones are exported
and whether they carry a permission.

## 2. Exports that deserve scrutiny

* `androidx.test.core.app.InstrumentationActivityInvoker{$Bootstrap,Empty,EmptyFloating}Activity`
  — **AndroidX Test artifacts exported in a production build.** These belong to
  `androidx.test:core`, which should be `testImplementation`, not `implementation`.
  It is a packaging-hygiene defect, not a known exploit, but it is exactly the
  kind of export that shows up in "unnecessary exported component" findings.
* `androidx.compose.ui.tooling.PreviewActivity` — Compose tooling in release.
* `vn.com.vng.zalopay.shareqr.presentation.QRReceiverActivity` — exported with no
  permission; receives QR payloads from other apps.
* `com.fpt.fci.ekycfull.presentation.view.EkycActivity`,
  `vn.ai.trueid.sdk.face.present.presentation.LivenessCameraXActivity`,
  `vn.com.vng.zalopay.livenessdetection.presentation.idcard.IdentityCardCaptureActivity`,
  `vn.com.vng.zalopay.liveness.presentation.LivenessVerificationActivity` — four
  distinct KYC/liveness surfaces from three vendors, all exported.
* `vn.com.zalopay.identity_verification.presentation.VerificationByVNeIDActivity`
  — national digital-ID verification, exported, reachable through an HTTPS
  app-link (`go.zalopay.vn/launch/f/user-consensus-zlp-vneid`).
* `VisaHostApduService` and the two mPaaS `TinyAppHostApduService` entries —
  NFC card emulation, protected by `BIND_NFC_SERVICE` (a system permission).

## 3. Deep links

24 `data` elements across 8 components. The pattern is conventional for
fintech — `zalopay://pay`, `zalopay://cashier`, `zalopay://merchant`,
`zalopay.api.v2://auth`, `zalopay.api.v2://recoverpassword`, plus HTTPS app links
on `go.zalopay.vn`, `onelink.zalopay.vn`, `km.zalopay.vn` and the AppsFlyer
`kmzalopay.onelink.me` domain. Two remarks:

* `zalopay.api.v2://recoverpassword` and `…//auth` are account-recovery and
  authorisation entry points. Any exported activity reachable from a third-party
  app is an origin-confusion candidate; the platform's answer is app-link
  verification (`autoVerify`), which is only declared for some filters.
* `fbconnect://cct.vn.com.vng.zalopay` is the Meta custom-tab return path, and
  `tagmanager.c.vn.com.vng.zalopay` is the Google Tag Manager preview path — both
  are SDK-typical but expand the origin set.

## 4. NFC / HCE

`res/xml/apdu_service.xml` is the most security-sensitive resource in the app:

```xml
<host-apdu-service android:requireDeviceUnlock="false" …>
  <aid-group android:category="payment">
    <aid-filter android:name="325041592E5359532E4444463031"/>  <!-- PPSE -->
    <aid-filter android:name="A0000000980840"/>                <!-- UnionPay -->
    <aid-filter android:name="A0000000032020"/>                <!-- Visa Plus -->
    <aid-filter android:name="A0000000032010"/>                <!-- Visa Electron -->
    <aid-filter android:name="A0000000033010"/>                <!-- Visa Interlink -->
    <aid-filter android:name="A0000000031010"/>                <!-- Visa credit/debit -->
    <aid-filter android:name="A000000003"/>                    <!-- Visa scheme root -->
  </aid-group>
</host-apdu-service>
```

Emulating *scheme-level* AIDs (rather than a single issuer AID) is what a wallet
must do to be selected by any POS terminal. `requireDeviceUnlock="false"` means
the OS does not require an unlocked screen before routing APDUs — the app must
enforce its own authorisation policy per transaction. That policy is exactly what
a dynamic analysis could test, and what a static analysis can only flag.

## 5. Storage and sharing

`res/xml/file_paths.xml` exposes eight `FileProvider` roots, including
`<cache-path name="cache" path="/"/>` and `<external-cache-path name="files_share" path="."/>`:
a broad share surface that includes the entire cache directory. Combined with
`requestLegacyExternalStorage="true"` (a flag with no effect on API 30+ but a
signal of legacy storage assumptions) and `largeHeap="true"`, the storage posture
is permissive.

## 6. Application-level flags

| Attribute | Value | Note |
|---|---|---|
| `android:name` | `jwbmqnsll.xvndbxlk` | renamed subclass of `vn.com.vng.zalopay.AndroidApplication` |
| `allowBackup` | `false` | good |
| `fullBackupContent` | `false` + `dataExtractionRules` | good |
| `debuggable` | absent | good |
| `extractNativeLibs` | `true` | libs unpacked at install (larger disk, faster load) |
| `hardwareAccelerated`, `largeHeap` | `true` | expected |
| `resizeableActivity` | `false` | phone-only UI assumptions |
| `enableOnBackInvokedCallback` | `false` | opt-out of predictive back |
| `networkSecurityConfig` | `@0x7f16000d` | see §7 |
| `appComponentFactory` | `androidx.core.app.CoreComponentFactory` | standard |

## 7. Network security configuration

Decoded from `res/xml/network_security_config.xml`:

```xml
<base-config cleartextTrafficPermitted="true">
  <trust-anchors><certificates src="system"/></trust-anchors>
</base-config>
<debug-overrides cleartextTrafficPermitted="true">
  <trust-anchors><certificates src="system"/><certificates src="user"/></trust-anchors>
</debug-overrides>
<domain-config><domain includeSubdomains="true">storage.googleapis.com</domain></domain-config>
```

Three observations: (a) cleartext is permitted *globally*, not just for the one
domain that is explicitly configured; (b) no `<pin-set>` exists anywhere, so
pinning — if it happens at all — is implemented in code (the DEX contains 42
pinning-related strings and OkHttp `CertificatePinner` references, so application
layer pinning is likely); (c) `debug-overrides` trusting user CAs is only
honoured in debuggable builds, so it is benign here but signals a debug workflow
that shipped.

## 8. Surface summary

The attack surface is *large but conventional* for a super-app: many vendors,
many exported entry points, and NFC. The two items that stand out as defects
rather than design choices are the **exported AndroidX Test / Compose tooling
activities** and the **global cleartext permission**. Both are cheap to fix and
neither is mitigated by the app's native hardening stack.
