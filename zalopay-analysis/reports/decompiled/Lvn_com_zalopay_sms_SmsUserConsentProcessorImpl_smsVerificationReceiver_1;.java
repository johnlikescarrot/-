package vn.com.zalopay.sms;
public final class SmsUserConsentProcessorImpl$smsVerificationReceiver$1 extends android.content.BroadcastReceiver {
    public final synthetic iY.d a;

    public SmsUserConsentProcessorImpl$smsVerificationReceiver$1(iY.d p1)
    {
        this.a = p1;
        return;
    }

    public final void onReceive(android.content.Context p4, android.content.Intent p5)
    {
        androidx.lifecycle.F v0_3;
        androidx.fragment.app.FragmentActivity v4_0 = 0;
        if (p5 == 0) {
            v0_3 = 0;
        } else {
            v0_3 = p5.getAction();
        }
        if (kotlin.jvm.internal.Intrinsics.areEqual("com.google.android.gms.auth.api.phone.SMS_RETRIEVED", v0_3)) {
            androidx.lifecycle.F v0_0;
            int v5_1 = p5.getExtras();
            if (v5_1 == 0) {
                v0_0 = 0;
            } else {
                v0_0 = v5_1.get("com.google.android.gms.auth.api.phone.EXTRA_STATUS");
            }
            androidx.lifecycle.F v0_2;
            if (!(v0_0 instanceof com.google.android.gms.common.api.Status)) {
                v0_2 = 0;
            } else {
                v0_2 = ((com.google.android.gms.common.api.Status) v0_0);
            }
            boolean v1_1;
            if (v0_2 == null) {
                v1_1 = 0;
            } else {
                v1_1 = Integer.valueOf(v0_2.a);
            }
            if ((!v1_1) || (v1_1.intValue() != 0)) {
                // Both branches of the condition point to the same code.
                // if ((v1_1) && (v1_1.intValue() != 15)) {
                // }
            } else {
                int v5_3 = ((android.content.Intent) v5_1.getParcelable("com.google.android.gms.auth.api.phone.EXTRA_CONSENT_INTENT"));
                if (v5_3 != 0) {
                    boolean v1_4;
                    androidx.lifecycle.F v0_6 = this.a.a;
                    if (!(v0_6 instanceof androidx.fragment.app.Fragment)) {
                        v1_4 = 0;
                    } else {
                        v1_4 = ((androidx.fragment.app.Fragment) v0_6);
                    }
                    if (!v1_4) {
                        if ((v0_6 instanceof androidx.fragment.app.FragmentActivity)) {
                            v4_0 = ((androidx.fragment.app.FragmentActivity) v0_6);
                        }
                        if (v4_0 != null) {
                            v4_0.startActivityForResult(v5_3, 1510);
                            return;
                        }
                    } else {
                        v1_4.startActivityForResult(v5_3, 1510);
                        return;
                    }
                }
            }
        }
        return;
    }
}
