package vn.com.vng.zalopay.bankbinding.presentation.broadcastreceiver;
public final class BankSmsBroadcastReceiver extends android.content.BroadcastReceiver {
    public final androidx.activity.result.d a;
    public final kotlin.jvm.functions.Function0 b;
    public final kotlin.jvm.functions.Function0 c;

    public BankSmsBroadcastReceiver(androidx.activity.result.d p1, kotlin.jvm.functions.Function0 p2, kotlin.jvm.functions.Function0 p3)
    {
        this.a = p1;
        this.b = p2;
        this.c = p3;
        return;
    }

    public final void onReceive(android.content.Context p3, android.content.Intent p4)
    {
        androidx.activity.result.d v0_5;
        android.content.ActivityNotFoundException v3_0 = p4.getExtras();
        if (v3_0 == null) {
            v0_5 = 0;
        } else {
            v0_5 = ((com.google.android.gms.common.api.Status) v3_0.getParcelable("com.google.android.gms.auth.api.phone.EXTRA_STATUS"));
        }
        androidx.activity.result.d v0_0;
        if (v0_5 == null) {
            v0_0 = 0;
        } else {
            v0_0 = Integer.valueOf(v0_5.a);
        }
        if ((v0_0 == null) || (v0_0.intValue() != 0)) {
            if ((v0_0 != null) && (v0_0.intValue() == 15)) {
                this.b.invoke();
            }
        } else {
            this.c.invoke();
            android.content.ActivityNotFoundException v3_2 = ((android.content.Intent) v3_0.getParcelable("com.google.android.gms.auth.api.phone.EXTRA_CONSENT_INTENT"));
            if (v3_2 != null) {
                try {
                    this.a.a(v3_2, 0);
                    return;
                } catch (android.content.ActivityNotFoundException v3_3) {
                    Ao.c.b("[Otp] Start consent retrieve sms error", v3_3);
                    return;
                }
            }
        }
        return;
    }
}
