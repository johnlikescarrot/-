package vn.com.vng.zalopay.navigation;
public final class c {

    public static void a(android.content.Context p1, android.os.Bundle p2, int p3)
    {
        if (com.google.android.gms.internal.mlkit_vision_barcode.G0.c().booleanValue()) {
            Class v3_1;
            switch (androidx.camera.camera2.internal.G.l(p3)) {
                case 0:
                    v3_1 = vn.com.vng.zalopay.inapp_webview.InternalWebViewActivity;
                    break;
                case 1:
                    v3_1 = vn.com.vng.zalopay.webview.ui.ResetPinWebViewActivity;
                    break;
                case 2:
                    v3_1 = vn.com.vng.zalopay.webapp.activity.UpdatePhoneWebAppActivity;
                    break;
                case 3:
                    v3_1 = vn.com.vng.zalopay.webapp.activity.ManualResetPinWebAppActivity;
                    break;
                case 4:
                    v3_1 = vn.com.vng.zalopay.webview.ui.HomeSearchWebViewActivity;
                    break;
                case 5:
                    v3_1 = vn.com.vng.zalopay.webview.ui.update_personal_info.UpdatePersonalInfoWebViewActivity;
                    break;
                case 6:
                    v3_1 = vn.com.vng.zalopay.webview.ui.VerificationAtBankCounterWebViewActivity;
                    break;
                default:
                    throw new kotlin.NoWhenBranchMatchedException();
            }
            android.content.Intent v0_2 = new android.content.Intent(p1, v3_1);
            if (p2 != null) {
                v0_2.putExtras(p2);
            }
            p1.startActivity(v0_2);
            return;
        } else {
            return;
        }
    }
}
