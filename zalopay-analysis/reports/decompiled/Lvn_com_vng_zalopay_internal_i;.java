package vn.com.vng.zalopay.internal;
public final class i implements vn.com.vng.zalopay.internal.d {
    public final jN.d a;
    public final org.greenrobot.eventbus.e b;
    public final As.e c;
    public final St.a d;
    public final kf.a e;
    public final li.e f;

    public i(jN.d p1, org.greenrobot.eventbus.e p2, As.e p3, St.a p4)
    {
        this.a = p1;
        this.b = p2;
        this.c = p3;
        this.d = p4;
        this.e = new kf.a(0);
        this.f = gi.L.a(kotlinx.coroutines.Dispatchers.getIO().plus(gi.N.d()));
        return;
    }

    public final int a()
    {
        return 60;
    }

    public final boolean b()
    {
        return 1;
    }

    public final void c(android.app.Application p8)
    {
        Thread.setDefaultUncaughtExceptionHandler(this.a);
        kotlinx.coroutines.BuildersKt.launch$default(this.f, kotlinx.coroutines.Dispatchers.getIO(), 0, new tS.v(this, p8, 0, 20), 2, 0);
        kf.a v1_12 = this.e;
        v1_12.c(q9.w6.a(com.google.android.gms.internal.mlkit_vision_barcode.t0.d(new com.facebook.t(2)).map(new vn.com.vng.zalopay.internal.e(6, new vn.com.vng.zalopay.internal.g(p8, 1))).map(new vn.com.vng.zalopay.internal.e(1, new vn.com.vng.zalopay.internal.g(p8, 2))).doOnError(new vn.com.vng.zalopay.internal.e(2, new vn.com.vng.zalopay.internal.g(p8, 0)))).subscribe(new vn.com.vng.zalopay.internal.e(3, new jV.k(29, this)), new vn.com.vng.zalopay.internal.e(5, new vn.com.vng.zalopay.homev5.presenter.ui.home_page.Y0(4))));
        v1_12.c(q9.w6.a(com.google.android.gms.internal.mlkit_vision_barcode.t0.d(new Gv.n(this, p8))).subscribe(new nY.s(21, new vn.com.vng.zalopay.homev5.presenter.ui.home_page.Y0(5)), new vn.com.vng.zalopay.internal.e(4, new vn.com.vng.zalopay.homev5.presenter.ui.home_page.Y0(6))));
        return;
    }
}
