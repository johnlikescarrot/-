# Báo cáo phân tích tĩnh — ZaloPay 11.17.0 (APKPure XAPK)

> Static reverse-engineering report (evidence-first, static-only). Machine-readable highlights: `FINDINGS.json`. Full inventory: `file_inventory.csv`. Hashes: `chain_of_custody.json`.

## 0. Tóm tắt / Abstract

- Gói nguồn: `Zalopay-Thanh+toán+&+Tài+chính_11.17.0_APKPure.zip` (94.2 MB, SHA-256 `34b9b6b6735f0f73…`).
- Bên trong: 1 file XAPK → 7 mục (base APK + 4 split configs).
- Base APK: package `vn.com.vng.zalopay`, version `11.17.0` (1102758), minSdk 26, targetSdk 36, mainActivity `vn.com.vng.zalopay.splashscreen.presentation.SplashScreenActivityV2`.
- Permissions: 49 | Activities: 200 | Services: 34 | Receivers: 22 | Providers: 9.
- DEX: 19 file, 61.8 MB | Native: 27 .so trên mọi split (54.8 MB; ABI: armeabi-v7a) | resources.arsc (base): 1760 KB.
- EN Abstract: This report documents a fully static, reproducible analysis of the ZaloPay 11.17.0 split-APK bundle. We inventory every container entry, verify SHA-256 hashes, parse the binary manifest (permissions/components/SDK levels), summarise DEX headers, native libraries, resources and signing certificates, and triage DEX strings into redacted aggregates (domain/package/keyword counts only). No code is executed, no protection is bypassed, and no secrets are published.

## 1. Chuỗi bảo quản (Chain of custody)

| File | Bytes | SHA-256 (rút gọn) |
|---|---|---|
| `Zalopay-Thanh+toán+&+Tài+chính_11.17.0_APKPure.zip` | 98813878 | `34b9b6b6735f0f739617f97a259f931d…` |
| `Zalopay-Thanh+toán+&+Tài+chính_11.17.0_APKPure.xapk` | 105608128 | `686e9459da646c18b53a67d57e5a23c4…` |
| ↳ `vn.com.vng.zalopay.apk` | 79359087 | `e289d547b45f6224aed8ce7bf72d75ea…` |
| ↳ `icon.png` | 23841 | `f0b9d7d1a6144ca550e4d326bc3b6cf9…` |
| ↳ `config.armeabi_v7a.apk` | 24828408 | `456aa08f4fa2e3f952657519ccf0b3f8…` |
| ↳ `config.en.apk` | 74137 | `87699fda97e8a9be06583d9261689966…` |
| ↳ `config.vi.apk` | 86425 | `985fc073b810b6286c116400a88e5552…` |
| ↳ `config.xhdpi.apk` | 1232563 | `f31350c3caed15099d2c2d325d88fef5…` |
| ↳ `manifest.json` | 2710 | `1382fb85261a3f58699396b832c4c68e…` |

## 2. XAPK manifest
```json
{
  "xapk_version": 2,
  "package_name": "vn.com.vng.zalopay",
  "name": "Zalopay",
  "version_code": "1102758",
  "version_name": "11.17.0",
  "min_sdk_version": "26",
  "target_sdk_version": "36",
  "permissions": [
    "android.permission.POST_NOTIFICATIONS",
    "android.permission.READ_CONTACTS",
    "android.permission.INTERNET",
    "android.permission.ACCESS_WIFI_STATE",
    "android.permission.ACCESS_NETWORK_STATE",
    "android.permission.READ_PHONE_STATE",
    "com.google.android.c2dm.permission.RECEIVE",
    "android.permission.WAKE_LOCK",
    "android.permission.NFC",
    "android.settings.action.MANAGE_OVERLAY_PERMISSION",
    "android.permission.WRITE_EXTERNAL_STORAGE",
    "android.permission.READ_EXTERNAL_STORAGE",
    "android.permission.ACCESS_COARSE_LOCATION",
    "android.permission.ACCESS_FINE_LOCATION",
    "android.permission.USE_FINGERPRINT",
    "com.samsung.android.providers.context.permission.WRITE_USE_APP_FEATURE_SURVEY",
    "android.permission.WRITE_CALENDAR",
    "android.permission.READ_CALENDAR",
    "android.permission.USE_BIOMETRIC",
    "android.permission.RECORD_AUDIO",
    "android.permission.MODIFY_AUDIO_SETTINGS",
    "com.android.launcher.permission.INSTALL_SHORTCUT",
    "com.android.launcher.permission.UNINSTALL_SHORTCUT",
    "com.android.launcher.permission.WRITE_SETTINGS",
    "com.android.launcher.permission.READ_SETTINGS",
    "com.bbk.launcher2.permission.READ_SETTINGS",
    "com.bbk.launcher2.permission.WRITE_SETTINGS",
    "android.permission.QUERY_ALL_PACKAGES",
    "android.permission.CAMERA",
    "android.permission.VIBRATE",
    "com.zing.zalo.permission.ACCESS_THIRD_PARTY_APP_AUTHORIZATION",
    "android.permission.CHANGE_WIFI_STATE",
    "android.permission.DETECT_SCREEN_CAPTURE",
    "com.google.android.gms.permission.AD_ID",
    "android.permission.ACCESS_ADSERVICES_ATTRIBUTION",
    "android.permission.ACCESS_ADSERVICES_AD_ID",
    "android.permission.ACCESS_ADSERVICES_CUSTOM_AUDIENCE",
    "android.permission.RECEIVE_BOOT_COMPLETED",
    "com.android.vending.BILLING",
    "com.google.android.finsky.permission.BIND_GET_INSTALL_REFERRER_SERVICE",
    "android.permission.ACCESS_ADSERVICES_TOPICS",
    "android.permission.FOREGROUND_SERVICE",
    "vn.com.vng.zalopay.DYNAMIC_RECEIVER_NOT_EXPORTED_PERMISSION",
    "android.permission.REORDER_TASKS",
    "com.samsung.android.mapsagent.permission.READ_APP_INFO",
    "com.huawei.appmarket.service.commondata.permission.GET_COMMON_DATA",
    "android.permission.READ_BASIC_PHONE_STATE",
    "android.permission.WRITE_CONTACTS",
    "android.permission.READ_MEDIA_AUDIO"
  ],
  "split_configs": [
    "config.armeabi_v7a",
    "config.en",
    "config.vi",
    "config.xhdpi"
  ],
  "total_size": 105580620,
  "icon": "icon.png",
  "split_apks": [
    {
      "file": "vn.com.vng.zalopay.apk",
      "id": "base"
    },
    {
      "file": "config.armeabi_v7a.apk",
      "id": "config.armeabi_v7a"
    },
    {
      "file": "config.en.apk",
      "id": "config.en"
    },
    {
      "file": "config.vi.apk",
      "id": "config.vi"
    },
    {
      "file": "config.xhdpi.apk",
      "id": "config.xhdpi"
    }
  ]
}
```

## 3. Hồ sơ từng APK split

| APK | MB | Entries | DEX (MB) | .so (MB) | ARSC (KB) | Manifest (B) |
|---|---|---|---|---|---|---|
| `config.armeabi_v7a.apk` | 23.7 | 32 | 0.0 | 54.8 | 0 | 1036 |
| `config.en.apk` | 0.1 | 6 | 0.0 | 0.0 | 61 | 1000 |
| `config.vi.apk` | 0.1 | 6 | 0.0 | 0.0 | 74 | 1000 |
| `config.xhdpi.apk` | 1.2 | 258 | 0.0 | 0.0 | 138 | 1032 |
| `vn.com.vng.zalopay.apk` | 75.7 | 6162 | 61.8 | 0.0 | 1760 | 125916 |

## 4. Permissions (49)

| Permission | Nhóm heuristic |
|---|---|
| `android.permission.ACCESS_ADSERVICES_AD_ID` | normal/signature |
| `android.permission.ACCESS_ADSERVICES_ATTRIBUTION` | normal/signature |
| `android.permission.ACCESS_ADSERVICES_CUSTOM_AUDIENCE` | normal/signature |
| `android.permission.ACCESS_ADSERVICES_TOPICS` | normal/signature |
| `android.permission.ACCESS_COARSE_LOCATION` | dangerous |
| `android.permission.ACCESS_FINE_LOCATION` | dangerous |
| `android.permission.ACCESS_NETWORK_STATE` | normal/signature |
| `android.permission.ACCESS_WIFI_STATE` | normal/signature |
| `android.permission.CAMERA` | dangerous |
| `android.permission.CHANGE_WIFI_STATE` | normal/signature |
| `android.permission.DETECT_SCREEN_CAPTURE` | normal/signature |
| `android.permission.FOREGROUND_SERVICE` | normal/signature |
| `android.permission.INTERNET` | normal/signature |
| `android.permission.MODIFY_AUDIO_SETTINGS` | normal/signature |
| `android.permission.NFC` | normal/signature |
| `android.permission.POST_NOTIFICATIONS` | dangerous |
| `android.permission.QUERY_ALL_PACKAGES` | special/sensitive |
| `android.permission.READ_BASIC_PHONE_STATE` | normal/signature |
| `android.permission.READ_CALENDAR` | dangerous |
| `android.permission.READ_CONTACTS` | dangerous |
| `android.permission.READ_EXTERNAL_STORAGE` | dangerous |
| `android.permission.READ_MEDIA_AUDIO` | dangerous |
| `android.permission.READ_PHONE_STATE` | dangerous |
| `android.permission.RECEIVE_BOOT_COMPLETED` | normal/signature |
| `android.permission.RECORD_AUDIO` | dangerous |
| `android.permission.REORDER_TASKS` | normal/signature |
| `android.permission.USE_BIOMETRIC` | special/sensitive |
| `android.permission.USE_FINGERPRINT` | special/sensitive |
| `android.permission.VIBRATE` | normal/signature |
| `android.permission.WAKE_LOCK` | normal/signature |
| `android.permission.WRITE_CALENDAR` | dangerous |
| `android.permission.WRITE_CONTACTS` | dangerous |
| `android.permission.WRITE_EXTERNAL_STORAGE` | dangerous |
| `android.settings.action.MANAGE_OVERLAY_PERMISSION` | normal/signature |
| `com.android.launcher.permission.INSTALL_SHORTCUT` | special/sensitive |
| `com.android.launcher.permission.READ_SETTINGS` | normal/signature |
| `com.android.launcher.permission.UNINSTALL_SHORTCUT` | normal/signature |
| `com.android.launcher.permission.WRITE_SETTINGS` | normal/signature |
| `com.android.vending.BILLING` | normal/signature |
| `com.bbk.launcher2.permission.READ_SETTINGS` | normal/signature |
| `com.bbk.launcher2.permission.WRITE_SETTINGS` | normal/signature |
| `com.google.android.c2dm.permission.RECEIVE` | normal/signature |
| `com.google.android.finsky.permission.BIND_GET_INSTALL_REFERRER_SERVICE` | normal/signature |
| `com.google.android.gms.permission.AD_ID` | normal/signature |
| `com.huawei.appmarket.service.commondata.permission.GET_COMMON_DATA` | normal/signature |
| `com.samsung.android.mapsagent.permission.READ_APP_INFO` | normal/signature |
| `com.samsung.android.providers.context.permission.WRITE_USE_APP_FEATURE_SURVEY` | normal/signature |
| `com.zing.zalo.permission.ACCESS_THIRD_PARTY_APP_AUTHORIZATION` | normal/signature |
| `vn.com.vng.zalopay.DYNAMIC_RECEIVER_NOT_EXPORTED_PERMISSION` | normal/signature |

## 5.activities — Activities (200)

- mainActivity: `vn.com.vng.zalopay.splashscreen.presentation.SplashScreenActivityV2` | app label: `Zalopay` | debuggable: `False` | allowBackup: `True`
<details><summary>Danh sách đầy đủ (200)</summary>

- `amcsetxek.dmayljy`
- `androidx.compose.ui.tooling.PreviewActivity`
- `androidx.glance.appwidget.action.ActionTrampolineActivity`
- `androidx.glance.appwidget.action.InvisibleActionTrampolineActivity`
- `androidx.test.core.app.InstrumentationActivityInvoker$BootstrapActivity`
- `androidx.test.core.app.InstrumentationActivityInvoker$EmptyActivity`
- `androidx.test.core.app.InstrumentationActivityInvoker$EmptyFloatingActivity`
- `com.alibaba.griver.core.keepalive.GriverProxyActivity`
- `com.alibaba.griver.core.ui.activity.GriverBaseActivity$Back1`
- `com.alibaba.griver.core.ui.activity.GriverBaseActivity$Back2`
- `com.alibaba.griver.core.ui.activity.GriverBaseActivity$Back3`
- `com.alibaba.griver.core.ui.activity.GriverBaseActivity$Back4`
- `com.alibaba.griver.core.ui.activity.GriverBaseActivity$Back5`
- `com.alibaba.griver.core.ui.activity.GriverBaseActivity$Back6`
- `com.alibaba.griver.core.ui.activity.GriverBaseActivity$Lite1`
- `com.alibaba.griver.core.ui.activity.GriverBaseActivity$Lite2`
- `com.alibaba.griver.core.ui.activity.GriverBaseActivity$Lite3`
- `com.alibaba.griver.core.ui.activity.GriverBaseActivity$Main`
- `com.alibaba.griver.core.ui.activity.GriverFileChooserActivity`
- `com.alibaba.griver.core.ui.activity.GriverTransActivity$Back1`
- `com.alibaba.griver.core.ui.activity.GriverTransActivity$Back2`
- `com.alibaba.griver.core.ui.activity.GriverTransActivity$Back3`
- `com.alibaba.griver.core.ui.activity.GriverTransActivity$Back4`
- `com.alibaba.griver.core.ui.activity.GriverTransActivity$Back5`
- `com.alibaba.griver.core.ui.activity.GriverTransActivity$Back6`
- `com.alibaba.griver.core.ui.activity.GriverTransActivity$Lite1`
- `com.alibaba.griver.core.ui.activity.GriverTransActivity$Lite2`
- `com.alibaba.griver.core.ui.activity.GriverTransActivity$Lite3`
- `com.alibaba.griver.core.ui.activity.GriverTransActivity$Main`
- `com.alibaba.griver.device.ui.GriverOpenSettingActivity`
- `com.alibaba.griver.file.ui.FileViewerActivity`
- `com.alibaba.griver.image.activity.GriverCaptureActivity`
- `com.alibaba.griver.image.activity.GriverCapturePreviewActivity`
- `com.alibaba.griver.image.activity.GriverPhotoPreviewActivity`
- `com.alibaba.griver.image.activity.GriverPhotoSelectActivity`
- `com.alibaba.griver.image.activity.GriverVideoPreviewActivity`
- `com.alibaba.griver.ui.jsapi.picker.nebula.wheelview.multilevel.MultilevelSelectActivity`
- `com.android.billingclient.api.ProxyBillingActivity`
- `com.android.billingclient.api.ProxyBillingActivityV2`
- `com.facebook.CustomTabActivity`
- `com.facebook.CustomTabMainActivity`
- `com.facebook.FacebookActivity`
- `com.facebook.ads.AudienceNetworkActivity`
- `com.fpt.fci.ekycfull.presentation.view.EkycActivity`
- `com.google.android.gms.ads.AdActivity`
- `com.google.android.gms.ads.NotificationHandlerActivity`
- `com.google.android.gms.ads.OutOfContextTestingActivity`
- `com.google.android.gms.common.api.GoogleApiActivity`
- `com.google.android.gms.tagmanager.TagManagerPreviewActivity`
- `com.google.android.play.core.common.PlayCoreDialogWrapperActivity`
- `com.iap.ac.android.acs.plugin.downgrade.ui.ErrorPageActivity`
- `com.iap.android.mppclient.container.activity.ACContainerActivity`
- `com.iap.android.mppclient.container.activity.H5NetworkCheckActivity`
- `com.karumi.dexter.DexterActivity`
- `com.vnpay.vntaxi.ui.activities.DetailBookTaxiActivity`
- `com.vnpay.vntaxi.ui.activities.TaxiBrowserActivity`
- `com.vnpay.vntaxi.ui.activities.TaxiWebviewLinkActivity`
- `com.vnpay.vntaxi.ui.activities.address.SelectAddressActivity`
- `com.vnpay.vntaxi.ui.activities.address.search.ConfirmOnMapActivity`
- `com.vnpay.vntaxi.ui.activities.address.search.SearchAddressActivity`
- `com.vnpay.vntaxi.ui.activities.address.search.SearchOnMapActivity`
- `com.vnpay.vntaxi.ui.activities.address.setting.AddressSettingActivity`
- `com.vnpay.vntaxi.ui.activities.address.setting.ChooseAddressActivity`
- `com.vnpay.vntaxi.ui.activities.address.setting.DetailAddressActivity`
- `com.vnpay.vntaxi.ui.activities.airport.TaxiAirportActivity`
- `com.vnpay.vntaxi.ui.activities.book.BookActivity`
- `com.vnpay.vntaxi.ui.activities.history.DetailHistoryTaxiActivity`
- `com.vnpay.vntaxi.ui.activities.history.TaxiHistoryActivity`
- `com.vnpay.vntaxi.ui.activities.insurance.TaxiInsuranceActivity`
- `com.vnpay.vntaxi.ui.activities.note.invoice.ExtractInvoiceActivity`
- `com.vnpay.vntaxi.ui.activities.note.note.ClientNoteActivity`
- `com.vnpay.vntaxi.ui.activities.report.FastReportTaxiActivity`
- `com.vnpay.vntaxi.ui.activities.selectpartner.TaxiBrandActivity`
- `com.vnpay.vntaxi.ui.activities.splash.SplashActivity`
- `com.vnpay.vntaxi.ui.activities.voucher.detail.VoucherDetailActivity`
- `com.vnpay.vntaxi.ui.activities.voucher.list.VoucherListActivity`
- `com.vnpay.vntaxi.ui.fragments.book.TaxiHelpMethodActivity`
- `com.zing.zalo.zalosdk.oauth.OpenAPIActivity`
- `com.zing.zalo.zalosdk.oauth.WebLoginActivity`
- `vn.ai.trueid.sdk.activity.SotpConfirmActivity`
- `vn.ai.trueid.sdk.activity.TrueIDActivity`
- `vn.ai.trueid.sdk.face.present.presentation.LivenessCameraXActivity`
- `vn.ai.trueid.sdk.facev3.present.presentation.LivenessCameraXActivity`
- `vn.com.vn.zalopay.upgrade_user_profile.presentation.ReformKycActivity`
- `vn.com.vng.resultpage.presentation.newui.ResultPageNewUIActivity`
- `vn.com.vng.resultpage.presentation.newuinative.ResultPageNativeActivity`
- `vn.com.vng.resultpage.presentation.ui.activity.ResultPageV2Activity`
- `vn.com.vng.resultpage.presentation.ui.webview.RPWebActivity`
- `vn.com.vng.zalopay.alipay.presentation.AlipayEmptyScanQRActivity`
- `vn.com.vng.zalopay.alipay.presentation.dialog.AlipayErrorDialogActivity`
- `vn.com.vng.zalopay.appinfo.AppInfoActivity`
- `vn.com.vng.zalopay.bankbinding.presentation.ui.activity.BankBindingActivity`
- `vn.com.vng.zalopay.bankbinding.presentation.ui.activity.BankMediaWebViewActivity`
- `vn.com.vng.zalopay.bankbinding.presentation.ui.activity.FundManagementActivity`
- `vn.com.vng.zalopay.behaviorguideline.LoginGuidelineActivity`
- `vn.com.vng.zalopay.camera.CustomDefaultCamera`
- `vn.com.vng.zalopay.cashier.buffer.CashierBufferActivity`
- `vn.com.vng.zalopay.cashier.middle.CashierMiddlePageActivity`
- `vn.com.vng.zalopay.cashier.presentation.newui.CashierNewUIActivity`
- `vn.com.vng.zalopay.cashier.presentation.ui.main.MainCashierV2Activity`
- `vn.com.vng.zalopay.cashier.presentation.ui.otp.DOtpErrorActivity`
- `vn.com.vng.zalopay.cashier.presentation.ui.web.activity.CashierWebActivity`
- `vn.com.vng.zalopay.cashier_v3.presentation.main.activity.CashierV3Activity`
- `vn.com.vng.zalopay.cashin.presentation.ui.activity.CashInActivity`
- `vn.com.vng.zalopay.changepin.presentation.ui.activity.ChangePinActivity`
- `vn.com.vng.zalopay.clearing.ClearingAccountActivity`
- `vn.com.vng.zalopay.closeaccount.presentation.ui.CloseAccountActivity`
- `vn.com.vng.zalopay.core.ui.activity.TutorialConnectInternetActivity`
- `vn.com.vng.zalopay.feature.ui.FeatureModuleRoutingActivity`
- `vn.com.vng.zalopay.home_more.presentation.ui.home_more.HomeMoreActivity`
- `vn.com.vng.zalopay.homev4.presentation.ui.onboarding.HomeOnBoardingActivity`
- `vn.com.vng.zalopay.homev4.presentation.ui.recommendsystem.RecommendSystemActivity`
- `vn.com.vng.zalopay.homev5.presenter.ui.ekyc_intro.EkycIntroActivity`
- `vn.com.vng.zalopay.homev5.presenter.ui.home.HomeActivity`
- `vn.com.vng.zalopay.homev5.presenter.ui.onboarding.OnboardingActivity`
- `vn.com.vng.zalopay.homev5.presenter.ui.routing.HomeRoutingActivity`
- `vn.com.vng.zalopay.homev5.presenter.ui.story_collections_player.StoryCollectionsPlayerActivity`
- `vn.com.vng.zalopay.inapp_webview.InternalWebViewActivity`
- `vn.com.vng.zalopay.liveness.presentation.LivenessVerificationActivity`
- `vn.com.vng.zalopay.livenessdetection.presentation.FaceLivenessCameraXActivity`
- `vn.com.vng.zalopay.livenessdetection.presentation.face_auth_revamp.FaceLivenessRevampActivity`
- `vn.com.vng.zalopay.livenessdetection.presentation.idcard.IdentityCardCaptureActivity`
- `vn.com.vng.zalopay.livenessdetection.presentation.qrcode.QRCodeScannerActivity`
- `vn.com.vng.zalopay.lockscreen.LockScreenActivity`
- `vn.com.vng.zalopay.logindevice.DeviceVerificationActivity`
- `vn.com.vng.zalopay.logindevice.LoginDevicesActivity`
- `vn.com.vng.zalopay.loginv2.presentation.ui.login.LoginActivity`
- `vn.com.vng.zalopay.loginv2.presentation.ui.otp.OtpVerificationActivity`
- `vn.com.vng.zalopay.loginv2.presentation.ui.upgradeuserk0k1.UpgradeUserK0K1Activity`
- `vn.com.vng.zalopay.loginv2.presentation.ui.webview.LoginWebViewActivity`
- `vn.com.vng.zalopay.miniapp.error.NoWebViewSdkActivity`
- `vn.com.vng.zalopay.miniapp.ui.MiniAppActivity`
- `vn.com.vng.zalopay.my_qr.MyQRActivity`
- `vn.com.vng.zalopay.nfc.FundingSolutionNFCActivity`
- `vn.com.vng.zalopay.passport.ForceUpdatePhoneActivity`
- `vn.com.vng.zalopay.passport.LockAccountActivity`
- `vn.com.vng.zalopay.pay_by_wallet_native.presentation.ui.PayByWalletReadyToPayActivity`
- `vn.com.vng.zalopay.pdfviewer.PdfViewerActivity`
- `vn.com.vng.zalopay.permission.location.presentation.LocationPermissionActivity`
- `vn.com.vng.zalopay.permission.notification.presentation.NotificationPermissionActivity`
- `vn.com.vng.zalopay.profile_tab_v2.presentation.ui.account.AccountHostActivity`
- `vn.com.vng.zalopay.profile_tab_v2.presentation.ui.privacy.PrivacyHubActivity`
- `vn.com.vng.zalopay.protect.ui.ProtectAccountActivity`
- `vn.com.vng.zalopay.quickpay_native.presentation.ui.QuickPayNativeActivity`
- `vn.com.vng.zalopay.receive_money.ReceiveMoneyActivity`
- `vn.com.vng.zalopay.reformkyc.presentation.ui.ReformKycActivity`
- `vn.com.vng.zalopay.resetpin.presentation.ui.activity.ResetPinActivity`
- `vn.com.vng.zalopay.result_page_v3.presentation.main.activity.ResultPageV3Activity`
- `vn.com.vng.zalopay.scancode.presentation.ScanCodeActivity`
- `vn.com.vng.zalopay.scanqr.presentation.v2.ScanQRActivityV2`
- `vn.com.vng.zalopay.search.presentation.ui.search.SearchActivity`
- `vn.com.vng.zalopay.shareqr.presentation.QRReceiverActivity`
- `vn.com.vng.zalopay.skewface.presentation.activity.SkewFaceActivity`
- `vn.com.vng.zalopay.splashscreen.presentation.SplashScreenActivityV2`
- `vn.com.vng.zalopay.splashscreen.presentation.SplashScreenGooglePaymentActivity`
- `vn.com.vng.zalopay.storage_management.presentation.StorageManagementActivity`
- `vn.com.vng.zalopay.tenpay.TPGPaymentActivity`
- `vn.com.vng.zalopay.tenpay.image_preview.ImagePreviewActivity`
- `vn.com.vng.zalopay.ui.activity.HomeActivity`
- `vn.com.vng.zalopay.ui.balance.activity.BalanceActivity`
- `vn.com.vng.zalopay.updatephone.presentation.ui.UpdatePhoneActivity`
- `vn.com.vng.zalopay.user_profile.presentation.additionalinfo.AdditionalProfileInfoActivity`
- `vn.com.vng.zalopay.user_profile.presentation.camera.CameraAutoRotationImage`
- `vn.com.vng.zalopay.user_profile.presentation.editheaderinfo.EditAvatarAndNameActivity`
- `vn.com.vng.zalopay.user_profile.presentation.profiledetails.ProfileDetailsActivity`
- `vn.com.vng.zalopay.user_profile.presentation.profiledetailv2.ProfileDetailsV2Activity`
- `vn.com.vng.zalopay.user_profile.presentation.protectaccount.AccountSecurityActivity`
- `vn.com.vng.zalopay.user_profile.presentation.umminiapps.DebugAuditActivity`
- `vn.com.vng.zalopay.user_profile.presentation.umminiapps.UMMiniAppActivity`
- `vn.com.vng.zalopay.webapp.activity.ManualResetPinWebAppActivity`
- `vn.com.vng.zalopay.webapp.activity.UpdatePhoneWebAppActivity`
- `vn.com.vng.zalopay.webapp.activity.WebAppExternalActivity`
- `vn.com.vng.zalopay.webview.ui.BalanceLiquidationWebViewActivity`
- `vn.com.vng.zalopay.webview.ui.HomeSearchWebViewActivity`
- `vn.com.vng.zalopay.webview.ui.ResetPinWebViewActivity`
- `vn.com.vng.zalopay.webview.ui.VerificationAtBankCounterWebViewActivity`
- `vn.com.vng.zalopay.webview.ui.WebViewActivity`
- `vn.com.vng.zalopay.webview.ui.update_personal_info.UpdatePersonalInfoWebViewActivity`
- `vn.com.vng.zalopay.withdraw.presentation.ui.activity.WithdrawActivity`
- `vn.com.zalopay.auth.presentation.faceauth.FaceAuthActivity`
- `vn.com.zalopay.auth.presentation.faceauth.components.selfdev.ui.SelfDevCameraActivity`
- `vn.com.zalopay.auth.presentation.mfa.view.AuthUIHolderActivity`
- `vn.com.zalopay.auth.presentation.otp.OTPChallengeActivity`
- `vn.com.zalopay.auth.presentation.otp.popup.OTPChallengeRootActivity`
- `vn.com.zalopay.auth.presentation.smartotp.SmartOTPActivity`
- `vn.com.zalopay.balance_liquidation.presentation.BalanceLiquidationActivity`
- `vn.com.zalopay.ce_ticket.presentation.ui.CETicketContainerActivity`
- `vn.com.zalopay.ekycpro.presentation.bca.ui.activity.BcaActivity`
- `vn.com.zalopay.ekycpro.presentation.guardian_consent.GuardianInfoActivity`
- `vn.com.zalopay.ekycpro.presentation.main.MainEKycActivity`
- `vn.com.zalopay.ekycpro.presentation.nfc.MainNFCFeatureActivity`
- `vn.com.zalopay.ekycpro.presentation.selfie.SelfieActivity`
- `vn.com.zalopay.identity_verification.presentation.VerificationByVNeIDActivity`
- `vn.com.zalopay.nfc_sdk.nfc_in_house.BottomSheetNFC`
- `vn.com.zalopay.owner_verification.presentation.ui.OVContainerViewActivity`
- `vn.com.zalopay.reset_pin.presentation.pin_creation.CreatePinActivity`
- `vn.com.zalopay.reset_pin.presentation.ui.ResetPinContainerViewActivity`
- `vn.zalopay.ui.um_custom_loading.CenterLoadingActivity`
- `vn.zalopay.ui.um_custom_loading.FullScreenLoadingActivity`
- `yydamachy.xqcntsk`
</details>

## 5.services — Services (34)

- mainActivity: `vn.com.vng.zalopay.splashscreen.presentation.SplashScreenActivityV2` | app label: `Zalopay` | debuggable: `False` | allowBackup: `True`
<details><summary>Danh sách đầy đủ (34)</summary>

- `androidx.camera.core.impl.MetadataHolderService`
- `androidx.core.widget.RemoteViewsCompatService`
- `androidx.glance.appwidget.GlanceRemoteViewsService`
- `androidx.room.MultiInstanceInvalidationService`
- `androidx.work.impl.background.systemalarm.SystemAlarmService`
- `androidx.work.impl.background.systemjob.SystemJobService`
- `androidx.work.impl.foreground.SystemForegroundService`
- `com.alibaba.ariver.commonability.device.jsapi.nfc.service.TinyAppHostApduService`
- `com.alibaba.ariver.ipc.RemoteCallService`
- `com.alibaba.exthub.bigdata.service.BigDataLiteService$BigDataLiteService1`
- `com.alibaba.exthub.bigdata.service.BigDataLiteService$BigDataLiteService2`
- `com.alibaba.exthub.bigdata.service.BigDataLiteService$BigDataLiteService3`
- `com.alibaba.exthub.bigdata.service.BigDataLiteService$BigDataLiteService4`
- `com.alibaba.exthub.bigdata.service.BigDataLiteService$BigDataLiteService5`
- `com.alibaba.exthub.bigdata.service.BigDataMainService`
- `com.alibaba.griver.base.resource.predownload.AppPreDownloadJobSchedulerService`
- `com.alibaba.griver.core.kernel.ipc.client.IpcMsgClientService$Lite1`
- `com.alibaba.griver.core.kernel.ipc.client.IpcMsgClientService$Lite2`
- `com.alibaba.griver.core.kernel.ipc.client.IpcMsgClientService$Lite3`
- `com.alibaba.griver.core.kernel.ipc.server.IpcMsgServerService`
- `com.alibaba.griver.device.jsapi.nfc.service.TinyAppHostApduService`
- `com.google.android.datatransport.runtime.backends.TransportBackendDiscovery`
- `com.google.android.datatransport.runtime.scheduling.jobscheduling.JobInfoSchedulerService`
- `com.google.android.gms.ads.AdService`
- `com.google.android.gms.measurement.AppMeasurementJobService`
- `com.google.android.gms.measurement.AppMeasurementService`
- `com.google.android.gms.tagmanager.TagManagerService`
- `com.google.firebase.components.ComponentDiscoveryService`
- `com.google.firebase.messaging.FirebaseMessagingService`
- `com.google.firebase.sessions.SessionLifecycleService`
- `com.google.mlkit.common.internal.MlKitComponentDiscoveryService`
- `com.vnpay.vntaxi.base.gps.FusedLocationService`
- `vn.com.vng.zalopay.miniapp.api.v2.visa.VisaHostApduService`
- `vn.com.vng.zalopay.notification.MyFcmListenerService`
</details>

## 5.receivers — Receivers (22)

- mainActivity: `vn.com.vng.zalopay.splashscreen.presentation.SplashScreenActivityV2` | app label: `Zalopay` | debuggable: `False` | allowBackup: `True`
<details><summary>Danh sách đầy đủ (22)</summary>

- `androidx.glance.appwidget.MyPackageReplacedReceiver`
- `androidx.glance.appwidget.UnmanagedSessionReceiver`
- `androidx.glance.appwidget.action.ActionCallbackBroadcastReceiver`
- `androidx.profileinstaller.ProfileInstallReceiver`
- `androidx.work.impl.background.systemalarm.ConstraintProxy$BatteryChargingProxy`
- `androidx.work.impl.background.systemalarm.ConstraintProxy$BatteryNotLowProxy`
- `androidx.work.impl.background.systemalarm.ConstraintProxy$NetworkStateProxy`
- `androidx.work.impl.background.systemalarm.ConstraintProxy$StorageNotLowProxy`
- `androidx.work.impl.background.systemalarm.ConstraintProxyUpdateReceiver`
- `androidx.work.impl.background.systemalarm.RescheduleReceiver`
- `androidx.work.impl.diagnostics.DiagnosticsReceiver`
- `androidx.work.impl.utils.ForceStopRunnable$BroadcastReceiver`
- `com.facebook.AuthenticationTokenManager$CurrentAuthenticationTokenChangedBroadcastReceiver`
- `com.facebook.CurrentAccessTokenExpirationBroadcastReceiver`
- `com.google.android.datatransport.runtime.scheduling.jobscheduling.AlarmManagerSchedulerBroadcastReceiver`
- `com.google.android.gms.measurement.AppMeasurementReceiver`
- `com.google.firebase.iid.FirebaseInstanceIdReceiver`
- `vn.com.vng.zalopay.app_widget.presentation.ui.game.GameAppWidgetReceiver`
- `vn.com.vng.zalopay.app_widget.presentation.ui.movie.MovieAppWidgetReceiver`
- `vn.com.vng.zalopay.app_widget.presentation.ui.personal_qr.PersonalQRAppWidgetReceiver`
- `vn.com.vng.zalopay.app_widget.presentation.ui.standard.StandardAppWidgetReceiver`
- `vn.com.vng.zalopay.receivers.ZPInstallBroadcastReceiver`
</details>

## 5.providers — Providers (9)

- mainActivity: `vn.com.vng.zalopay.splashscreen.presentation.SplashScreenActivityV2` | app label: `Zalopay` | debuggable: `False` | allowBackup: `True`
<details><summary>Danh sách đầy đủ (9)</summary>

- `androidx.core.content.FileProvider`
- `androidx.startup.InitializationProvider`
- `com.alibaba.griver.file.utils.GriverFileProvider`
- `com.facebook.ads.AudienceNetworkContentProvider`
- `com.facebook.internal.FacebookInitProvider`
- `com.google.android.gms.ads.MobileAdsInitProvider`
- `com.google.firebase.perf.provider.FirebasePerfProvider`
- `com.google.firebase.provider.FirebaseInitProvider`
- `com.google.mlkit.common.internal.MlKitInitProvider`
</details>

## 6. Chữ ký (Certificates — fingerprints)

- SHA-256: `59dd31fdf36070a0d7f8c8cde843fac3…` | subject: `common_name=Vu Manh Hieu` | issuer: `common_name=Vu Manh Hieu`

## 7. DEX (header + ids counts)

| File | MB | strings | types | protos | fields | methods | classes | size_match |
|---|---|---|---|---|---|---|---|---|
| `classes2.dex` | 15.8 | 56948 | 11243 | 12070 | 60000 | 45527 | 7421 | True |
| `classes7.dex` | 10.3 | 59110 | 20633 | 16081 | 43164 | 60000 | 12953 | True |
| `classes4.dex` | 9.0 | 52812 | 19105 | 15731 | 40269 | 59997 | 11441 | True |
| `classes.dex` | 8.4 | 61218 | 17196 | 15953 | 46956 | 59948 | 10703 | True |
| `classes6.dex` | 7.3 | 55274 | 18399 | 15543 | 36617 | 49201 | 5475 | True |
| `classes5.dex` | 7.0 | 56424 | 14079 | 13049 | 60002 | 49055 | 7983 | True |
| `classes9.dex` | 1.2 | 6155 | 2175 | 1827 | 4520 | 7170 | 1128 | True |
| `classes3.dex` | 0.8 | 8010 | 3827 | 2361 | 3619 | 6354 | 1081 | True |
| `classes19.dex` | 0.7 | 4159 | 1782 | 1445 | 2804 | 3781 | 777 | True |
| `classes12.dex` | 0.4 | 3207 | 1191 | 916 | 1723 | 2604 | 425 | True |
| `classes8.dex` | 0.2 | 1906 | 915 | 697 | 1056 | 1552 | 295 | True |
| `classes17.dex` | 0.2 | 1847 | 848 | 611 | 972 | 1311 | 243 | True |
| `classes11.dex` | 0.2 | 1584 | 740 | 523 | 694 | 1171 | 177 | True |
| `classes13.dex` | 0.1 | 1381 | 590 | 420 | 561 | 997 | 139 | True |
| `classes18.dex` | 0.1 | 1094 | 553 | 373 | 428 | 668 | 115 | True |
| `classes15.dex` | 0.1 | 1005 | 436 | 305 | 410 | 658 | 113 | True |
| `classes14.dex` | 0.1 | 961 | 404 | 249 | 371 | 575 | 90 | True |
| `classes10.dex` | 0.1 | 920 | 461 | 300 | 314 | 578 | 92 | True |
| `classes16.dex` | 0.0 | 261 | 116 | 69 | 38 | 101 | 20 | True |
| **Tổng** | **61.8** | **374276** | **114693** | **98523** | **304518** | **351248** | **60671** | — |

## 8. Native libraries (gộp mọi split — base không chứa .so, .so nằm ở split ABI)

| APK | .so | ABI | Bytes | ELF | machine | type |
|---|---|---|---|---|---|---|
| `config.armeabi_v7a.apk` | `libtaibmcf.so` | armeabi-v7a | 12617752 | True | armeabi | DYN |
| `config.armeabi_v7a.apk` | `libmlkitcommonpipeline.so` | armeabi-v7a | 6928228 | True | armeabi | DYN |
| `config.armeabi_v7a.apk` | `libmlkit_google_ocr_pipeline.so` | armeabi-v7a | 6781940 | True | armeabi | DYN |
| `config.armeabi_v7a.apk` | `libfgcpkjvqe.so` | armeabi-v7a | 6524160 | True | armeabi | DYN |
| `config.armeabi_v7a.apk` | `libface_detector_v2_jni.so` | armeabi-v7a | 5388224 | True | armeabi | DYN |
| `config.armeabi_v7a.apk` | `libsqlcipher.so` | armeabi-v7a | 3555080 | True | armeabi | DYN |
| `config.armeabi_v7a.apk` | `libbarhopper_v3.so` | armeabi-v7a | 3244440 | True | armeabi | DYN |
| `config.armeabi_v7a.apk` | `librive-android.so` | armeabi-v7a | 3225636 | True | armeabi | DYN |
| `config.armeabi_v7a.apk` | `libpicodet_ncnn.so` | armeabi-v7a | 2992584 | True | armeabi | DYN |
| `config.armeabi_v7a.apk` | `libVFaceLib.so` | armeabi-v7a | 1963668 | True | armeabi | DYN |
| `config.armeabi_v7a.apk` | `liba97d.so` | armeabi-v7a | 997572 | True | armeabi | DYN |
| `config.armeabi_v7a.apk` | `libc++_shared.so` | armeabi-v7a | 872872 | True | armeabi | DYN |
| `config.armeabi_v7a.apk` | `libljsfrcwmw.so` | armeabi-v7a | 600296 | True | armeabi | DYN |
| `config.armeabi_v7a.apk` | `libcrashlytics-common.so` | armeabi-v7a | 435732 | True | armeabi | DYN |
| `config.armeabi_v7a.apk` | `libnative-imagetranscoder.so` | armeabi-v7a | 341072 | True | armeabi | DYN |
| `config.armeabi_v7a.apk` | `liblytcuhe.so` | armeabi-v7a | 178628 | True | armeabi | DYN |
| `config.armeabi_v7a.apk` | `librcmefqonnx.so` | armeabi-v7a | 178628 | True | armeabi | DYN |
| `config.armeabi_v7a.apk` | `libohxbee.so` | armeabi-v7a | 155412 | True | armeabi | DYN |
| `config.armeabi_v7a.apk` | `libsecurity_lite.so` | armeabi-v7a | 154840 | True | armeabi | DYN |
| `config.armeabi_v7a.apk` | `libcrashlytics.so` | armeabi-v7a | 94540 | True | armeabi | DYN |
| `config.armeabi_v7a.apk` | `libcrashlytics-handler.so` | armeabi-v7a | 87908 | True | armeabi | DYN |
| `config.armeabi_v7a.apk` | `libtrueidlib.so` | armeabi-v7a | 79904 | True | armeabi | DYN |
| `config.armeabi_v7a.apk` | `libimage_processing_util_jni.so` | armeabi-v7a | 20380 | True | armeabi | DYN |
| `config.armeabi_v7a.apk` | `libnative-filters.so` | armeabi-v7a | 12588 | True | armeabi | DYN |
| `config.armeabi_v7a.apk` | `libandroidx.graphics.path.so` | armeabi-v7a | 7252 | True | armeabi | DYN |
| `config.armeabi_v7a.apk` | `libcrashlytics-trampoline.so` | armeabi-v7a | 6912 | True | armeabi | DYN |
| `config.armeabi_v7a.apk` | `libimagepipeline.so` | armeabi-v7a | 5876 | True | armeabi | DYN |

## 9. Tài nguyên (Resources)

- `resources.arsc`: 1760 KB; `res/`: 5604 file (35.9 MB); `assets/`: 124 file (28.6 MB).
- Top phần mở rộng (theo số lượng): `xml`×5209, `png`×221, `version`×141, `txt`×136, `webp`×111, `properties`×79, `json`×64, `ttf`×23, `dex`×20, `(none)`×14, `tflite`×14, `kotlin_module`×11, `binarypb`×10, `proto`×10, `js`×9

## 10. Strings triage (tổng hợp đã kiểm duyệt — không công bố giá trị nhạy cảm)

- ASCII strings (DEX): 404590 (đã quét 400000); URL: 238.
- Top domain tails: `com.vn`×74, `zalopay.vn`×59, `alipay.com`×21, `google.com`×18, `android.com`×9, `page.link`×6, `goo.gl`×4, `github.com`×3, `zalo.me`×3, `alipayplus.com`×2, `g.co`×2, `marmot-cloud.com`×2, `bouncycastle.org`×1, `jetbrains.com`×1, `alibaba.com`×1, `adobe.com`×1, `doubleclick.net`×1, `antfin-inc.com`×1, `goo.gle`×1, `worker.com`×1
- Top package prefix: `android.intent.action`×44, `kotlin.jvm.functions`×26, `android.intent.extra`×20, `com.alibaba.ariver`×17, `com.google.android`×17, `vn.com.vng`×17, `android.media.metadata`×14, `com.facebook.sdk`×8, `com.visa.cbp`×8, `android.permission.CAMERA`×8, `com.zing.zalo`×7, `kotlin.jvm.PlatformType`×7, `com.google.firebase`×7, `org.bouncycastle.ec`×7, `android.view.View`×6, `com.alibaba.griver`×6, `com.alipay.mobile`×6, `com.tiktok.sdk`×6, `com.facebook.appevents`×6, `android.intent.category`×5, `android.os.SystemProperties`×5, `com.android.vending`×5, `com.iap.ac`×5, `android.hardware.type`×5, `android.resource`×4
- Crypto keywords: `aes`×550, `rsa`×737, `hmac`×237, `sha256`×383, `ssl`×619, `pinning`×34, `keystore`×135, `pbkdf2`×40, `oauth`×312
- Sensitive-pattern hits (chỉ đếm, đã che giá trị): `api[_-]?key`×25, `secret`×229, `passwd|password`×207, `private[_-]?key`×357, `access[_-]?token`×77, `auth[_-]?token`×13, `bearer `×5

## 11. Nhận định hardening (mức khuyến nghị, không PoC)

- Kiểm tra `debuggable=false`, `allowBackup` chính sách, exported components tối thiểu, targetSdk hiện đại, và networkSecurityConfig (nếu có). Chi tiết xem FINDINGS.json.

## 12. Giới hạn & Tái lập

- Static-only: không suy luận hành vi runtime; package-prefix ⇒ heuristic.
- Tái lập: `python3 analysis/extract.py && python3 analysis/static_scan.py && python3 analysis/build_report.py`.
