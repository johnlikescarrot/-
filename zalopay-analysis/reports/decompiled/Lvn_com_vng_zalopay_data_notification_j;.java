package vn.com.vng.zalopay.data.notification;
public final class j implements vn.com.vng.zalopay.data.notification.k$b {
    public final vn.com.vng.zalopay.data.notification.k$a a;
    public final vn.com.vng.zalopay.data.notification.k$c b;
    public final vn.com.vng.zalopay.data.notification.k$d c;
    public final vn.com.vng.zalopay.data.notification.b d;
    public final Ns.a e;

    public j(vn.com.vng.zalopay.data.notification.k$a p1, vn.com.vng.zalopay.data.notification.k$c p2, vn.com.vng.zalopay.data.notification.k$d p3, Ew.D p4, Ns.a p5, vn.com.vng.zalopay.data.notification.b p6)
    {
        this.a = p1;
        this.b = p2;
        this.d = p6;
        this.e = p5;
        this.c = p3;
        return;
    }

    public final void a(String p4)
    {
        String v1_0 = this.e;
        this.c.b(new kt.a(p4, v1_0.a(), v1_0.c())).subscribeOn(Ef.g.c).subscribe(new Bw.b());
        return;
    }

    public final gf.A b(Rt.k p3)
    {
        return com.google.android.gms.internal.mlkit_vision_barcode.t0.c(new JY.c(this, p3, 5));
    }

    public final gf.A c()
    {
        gf.A v0_0 = this.a;
        java.util.Objects.requireNonNull(v0_0);
        return com.google.android.gms.internal.mlkit_vision_barcode.t0.c(new vn.com.vng.zalopay.data.notification.g(v0_0, 0));
    }

    public final gf.A d()
    {
        return this.c.a("10", "").flatMap(new nY.s(20));
    }

    public final gf.A e()
    {
        gf.A v0_0 = this.a;
        java.util.Objects.requireNonNull(v0_0);
        return com.google.android.gms.internal.mlkit_vision_barcode.t0.c(new vn.com.vng.zalopay.data.notification.g(v0_0, 2));
    }

    public final gf.A f(long p9, long p11, long p13)
    {
        return com.google.android.gms.internal.mlkit_vision_barcode.t0.c(new vn.com.vng.zalopay.data.notification.i(this, p9, p11, p13));
    }

    public final gf.A g()
    {
        return this.c.c().flatMap(new nY.s(18));
    }

    public final gf.A h(hH.a p3)
    {
        return com.google.android.gms.internal.mlkit_vision_barcode.t0.d(new vn.com.vng.zalopay.data.notification.f(this, p3, 0));
    }

    public final vn.com.vng.zalopay.data.notification.a i()
    {
        return ((vn.com.vng.zalopay.data.notification.a) this.d.get());
    }

    public final gf.A j()
    {
        gf.A v0_0 = this.a;
        java.util.Objects.requireNonNull(v0_0);
        return com.google.android.gms.internal.mlkit_vision_barcode.t0.d(new vn.com.vng.zalopay.data.notification.g(v0_0, 1));
    }

    public final gf.A k(long p3)
    {
        return com.google.android.gms.internal.mlkit_vision_barcode.t0.c(new vn.com.vng.zalopay.data.notification.h(this, p3, 1));
    }

    public final gf.A l(hH.a p3)
    {
        return com.google.android.gms.internal.mlkit_vision_barcode.t0.d(new vn.com.vng.zalopay.data.notification.f(this, p3, 1));
    }

    public final gf.A m(long p3)
    {
        return com.google.android.gms.internal.mlkit_vision_barcode.t0.c(new vn.com.vng.zalopay.data.notification.h(this, p3, 0)).map(new nY.s(19));
    }
}
