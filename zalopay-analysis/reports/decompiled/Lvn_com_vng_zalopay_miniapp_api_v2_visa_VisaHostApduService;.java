package vn.com.vng.zalopay.miniapp.api.v2.visa;
public final class VisaHostApduService extends android.nfc.cardemulation.HostApduService {
    public static final Object d;
    public static vn.com.vng.zalopay.miniapp.api.v2.visa.VisaHostApduService$a e;
    public static String f;
    public static ref.WeakReference g;
    public final li.e a;
    public final java.util.concurrent.atomic.AtomicBoolean b;
    public final java.util.concurrent.atomic.AtomicReference c;

    static VisaHostApduService()
    {
        vn.com.vng.zalopay.miniapp.api.v2.visa.VisaHostApduService.d = new Object();
        return;
    }

    public VisaHostApduService()
    {
        this.a = gi.L.a(kotlin.coroutines.f.a(gi.N.d(), kotlinx.coroutines.Dispatchers.getIO()));
        this.b = new java.util.concurrent.atomic.AtomicBoolean(0);
        this.c = new java.util.concurrent.atomic.AtomicReference(0);
        return;
    }

    public final void a()
    {
        int v2_1;
        android.app.Notification v0_4 = new android.app.NotificationChannel("zalopay_nfc_channel", "Zalopay NFC Interactions", 4);
        v0_4.setDescription("NFC card interactions");
        v0_4.setShowBadge(1);
        int v2_0 = this.getSystemService("notification");
        if (!(v2_0 instanceof android.app.NotificationManager)) {
            v2_1 = 0;
        } else {
            v2_1 = ((android.app.NotificationManager) v2_0);
        }
        if (v2_1 != 0) {
            v2_1.createNotificationChannel(v0_4);
        }
        android.app.Notification v0_3 = android.app.PendingIntent.getActivity(this, 0, new android.content.Intent("android.intent.action.VIEW", android.net.Uri.parse("zalopay://launch/f/pay-by-wallet")), 201326592);
        androidx.core.app.A v4_5 = new androidx.core.app.A(this.getApplicationContext(), "zalopay_nfc_channel");
        v4_5.w.icon = 2131233933;
        v4_5.e = androidx.core.app.A.b("\u0110\u00e3 ph\u00e1t hi\u1ec7n th\u1ebb NFC");
        v4_5.f = androidx.core.app.A.b("\u1ea4n v\u00e0o \u0111\u00e2y \u0111\u1ec3 th\u1ef1c hi\u1ec7n thanh to\u00e1n");
        v4_5.c(16, 1);
        v4_5.g = v0_3;
        v4_5.j = 1;
        v4_5.q = "event";
        android.app.Notification v0_6 = v4_5.a();
        if (v2_1 != 0) {
            v2_1.notify(1, v0_6);
        }
        return;
    }

    public final void onCreate()
    {
        super.onCreate();
        try {
            vn.com.vng.zalopay.miniapp.api.v2.visa.VisaHostApduService.g = new ref.WeakReference(this);
            return;
        } catch (Throwable v1_2) {
            throw v1_2;
        }
    }

    public final void onDeactivated(int p3)
    {
        this.c.set(0);
        try {
            vn.com.vng.zalopay.miniapp.api.v2.visa.VisaHostApduService$a v1_1 = vn.com.vng.zalopay.miniapp.api.v2.visa.VisaHostApduService.e;
        } catch (java.util.concurrent.atomic.AtomicBoolean v3_1) {
            throw v3_1;
        }
        if (v1_1 != null) {
            v1_1.a(p3);
        }
        this.b.set(0);
        return;
    }

    public final void onDestroy()
    {
        android.app.NotificationManager v1_0 = 0;
        gi.L.b(this.a, 0);
        boolean v2_2;
        boolean v2_1 = vn.com.vng.zalopay.miniapp.api.v2.visa.VisaHostApduService.g;
        if (!v2_1) {
            v2_2 = 0;
        } else {
            v2_2 = ((vn.com.vng.zalopay.miniapp.api.v2.visa.VisaHostApduService) v2_1.get());
        }
        if (v2_2 == this) {
            vn.com.vng.zalopay.miniapp.api.v2.visa.VisaHostApduService.g = 0;
        }
        super.onDestroy();
        int v0_2 = this.getSystemService("notification");
        if ((v0_2 instanceof android.app.NotificationManager)) {
            v1_0 = ((android.app.NotificationManager) v0_2);
        }
        if (v1_0 != null) {
            v1_0.cancel(1);
        }
        return;
    }

    public final byte[] processCommandApdu(byte[] p8, android.os.Bundle p9)
    {
        if (p8 != 0) {
            try {
                int v2_0 = vn.com.vng.zalopay.miniapp.api.v2.visa.VisaHostApduService.e;
            } catch (Throwable v0_2) {
                throw v0_2;
            }
            if (v2_0 == 0) {
                int v3_0;
                int v2_4 = this.c;
                int v8_1 = java.util.Arrays.copyOf(p8, p8.length);
                if (p9 == 0) {
                    v3_0 = 0;
                } else {
                    v3_0 = new android.os.Bundle(p9);
                }
                v2_4.set(new IF.b(v8_1, v3_0));
                int v8_3 = vn.com.vng.zalopay.miniapp.api.v2.visa.VisaHostApduService.e;
                if (v8_3 != 0) {
                    int v9_5 = ((IF.b) this.c.getAndSet(0));
                    if (v9_5 != 0) {
                        v8_3.b(v9_5.a, v9_5.b, new IF.a(this, 1));
                    }
                }
                if (!Um.b.c()) {
                    vn.com.vng.zalopay.miniapp.api.v2.visa.c.e.getClass();
                    if ((!vn.com.vng.zalopay.miniapp.api.v2.visa.c.f()) && ((!QH.b.e) && (this.b.compareAndSet(0, 1)))) {
                        kotlinx.coroutines.BuildersKt.launch$default(this.a, 0, 0, new AA.g(this, 0, 16), 3, 0);
                    }
                } else {
                    this.a();
                    return 0;
                }
            } else {
                this.c.set(0);
                v2_0.b(p8, p9, new IF.a(this, 0));
                return 0;
            }
        }
        return 0;
    }
}
