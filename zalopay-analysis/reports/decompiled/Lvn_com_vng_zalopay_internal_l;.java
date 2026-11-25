package vn.com.vng.zalopay.internal;
public final class l implements vn.com.vng.zalopay.internal.d {
    public final Ew.D a;

    public l(Ew.D p1)
    {
        this.a = p1;
        return;
    }

    public final int a()
    {
        return 1;
    }

    public final boolean b()
    {
        return 1;
    }

    public final void c(android.app.Application p2)
    {
        com.google.firebase.crashlytics.FirebaseCrashlytics.getInstance().setCrashlyticsCollectionEnabled(1);
        String v2_1 = this.a;
        if (v2_1 != null) {
            com.google.firebase.crashlytics.FirebaseCrashlytics.getInstance().setUserId(v2_1.c);
        }
        return;
    }
}
