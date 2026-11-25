package vn.com.vng.zalopay.internal;
public final class f implements vn.com.vng.zalopay.internal.d {
    public final Ns.g a;
    public final Xv.d b;
    public final BK.a c;
    public final pO.c d;

    public f(Ns.g p1, Xv.d p2, BK.a p3)
    {
        this.a = p1;
        this.b = p2;
        this.c = p3;
        this.d = new pO.c(14, this);
        return;
    }

    public final int a()
    {
        return 3;
    }

    public final boolean b()
    {
        return 1;
    }

    public final void c(android.app.Application p5)
    {
        vn.com.vng.zalopay.internal.e v0_0 = eQ.e.d(p5);
        if (v0_0 != null) {
            if (kotlin.jvm.internal.Intrinsics.areEqual(p5.getPackageName(), "vn.com.vng.zalopay")) {
                com.appsflyer.AppsFlyerLib.getInstance().setOneLinkCustomDomain(new String[] {"km.zalopay.vn"}));
                com.appsflyer.AppsFlyerLib.getInstance().init(v0_0, this.d, p5.getApplicationContext());
                com.appsflyer.AppsFlyerLib.getInstance().start(p5);
                com.google.firebase.analytics.FirebaseAnalytics.getInstance(p5);
                this.c.a(p5);
            }
            com.appsflyer.AppsFlyerLib.getInstance().setCollectIMEI(0);
            vn.com.vng.zalopay.internal.e v0_5 = eQ.e.c(p5.getApplicationContext());
            if (v0_5 != null) {
                UR.a.e = v0_5;
                UR.a.f = this.a.c(p5);
                com.appsflyer.AppsFlyerLib.getInstance().subscribeForDeepLink(new vn.com.vng.zalopay.internal.e(0, this));
                return;
            }
        }
        return;
    }
}
