package vn.ai.trueid.sdk.utils;
public abstract class b {

    public static boolean a()
    {
        if (((android.nfc.NfcManager) vn.ai.trueid.sdk.utils.Helpers.Companion.getContext().getSystemService("nfc")).getDefaultAdapter() == null) {
            return 0;
        } else {
            return 1;
        }
    }

    public static boolean b(boolean p2, android.content.Context p3, android.view.View$OnClickListener p4, android.view.View$OnClickListener p5)
    {
        int v0_4 = ((android.nfc.NfcManager) p3.getSystemService("nfc")).getDefaultAdapter();
        if ((v0_4 == 0) || (!v0_4.isEnabled())) {
            if (v0_4 != 0) {
                if ((p2 != null) && (!m9.z2.a())) {
                    m9.z2.b(p3, vn.ai.trueid.sdk.utils.Helpers.Companion.getString(2131955519), p4, p5);
                }
            } else {
                if ((p2 != null) && (!m9.z2.a())) {
                    m9.z2.b(p3, vn.ai.trueid.sdk.utils.Helpers.Companion.getString(2131955505), p4, 0);
                    return 0;
                }
            }
            return 0;
        } else {
            return 1;
        }
    }

    public static synthetic boolean c(boolean p1, android.app.Activity p2, Ek.e p3, int p4)
    {
        if ((p4 & 8) != 0) {
            p3 = 0;
        }
        return vn.ai.trueid.sdk.utils.b.b(p1, p2, 0, p3);
    }

    public static void d(androidx.fragment.app.FragmentActivity p7)
    {
        android.nfc.NfcAdapter v0 = android.nfc.NfcAdapter.getDefaultAdapter(p7);
        if (v0 != null) {
            android.os.Bundle v2_1;
            android.app.PendingIntent v1_3 = new android.content.Intent(p7, p7.getClass()).addFlags(536870912);
            android.os.Bundle v2_0 = android.os.Build$VERSION.SDK_INT;
            if (v2_0 < 34) {
                v2_1 = 0;
            } else {
                int v3_1 = android.app.ActivityOptions.makeBasic();
                if (v2_0 < 35) {
                    v3_1.setPendingIntentBackgroundActivityStartMode(1);
                } else {
                    v3_1.setPendingIntentCreatorBackgroundActivityStartMode(1);
                }
                v2_1 = v3_1.toBundle();
            }
            v0.enableForegroundDispatch(p7, android.app.PendingIntent.getActivity(p7, 0, v1_3, 33554432, v2_1), 0, 0);
        }
        return;
    }
}
