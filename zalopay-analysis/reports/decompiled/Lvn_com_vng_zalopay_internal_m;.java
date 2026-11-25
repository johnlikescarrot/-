package vn.com.vng.zalopay.internal;
public final class m implements vn.com.vng.zalopay.internal.d {
    public final St.a a;
    public final Pt.n b;
    public final retrofit2.Retrofit c;

    public m(retrofit2.Retrofit p1, okhttp3.OkHttpClient p2, St.a p3, Pt.n p4, retrofit2.Retrofit p5, retrofit2.Retrofit p6, retrofit2.Retrofit p7)
    {
        this.a = p3;
        this.b = p4;
        this.c = p5;
        return;
    }

    public final int a()
    {
        return 20;
    }

    public final boolean b()
    {
        return 0;
    }

    public final void c(android.app.Application p10)
    {
        zv.l v0_1 = new MY.a();
        v0_1.j = this.b;
        v0_1.k = this.c;
        cu.g v1_4 = new MY.b(v0_1);
        zv.l v0_4 = new wY.a();
        wY.a.b = v0_4;
        p10.getClass();
        vn.com.vng.zalopay.data.notification.d$a v2_0 = this.a;
        v2_0.getClass();
        v0_4.a = new com.google.android.gms.internal.ads.Xl(new kc.c(), p10, v1_4, v2_0);
        MY.a v10_1 = vn.com.vng.zalopay.AndroidApplication.g;
        if (v10_1 == null) {
            v10_1 = 0;
        }
        MY.a v10_2 = v10_1.b;
        if (v10_2 != null) {
            zv.l v0_2 = v10_2.J();
            cu.g v1_1 = v10_2.d1();
            v10_2.h1();
            vn.com.vng.zalopay.data.notification.d$a v2_1 = v10_2.C0();
            v10_2.g1();
            v10_2.X();
            v10_2.m1();
            v10_2.w0();
            xt.c$b v3_2 = v10_2.s1();
            v10_2.G0();
            Ow.a v4_2 = v10_2.M();
            v10_2.q0();
            dZ.h v5 = v10_2.F();
            uv.c v6 = v10_2.M0();
            Ht.h v7 = v10_2.N();
            kotlin.collections.a0.emptyMap();
            pw.e v8 = v10_2.G1();
            v10_2.c1();
            v10_2.L0();
            v10_2.B1();
            v10_2.f0();
            MY.a v10_7 = ((MY.b) wY.a.a().a.a).a;
            v10_7.b = v0_2;
            v10_7.c = v1_1;
            v10_7.a = v2_1;
            v10_7.g = v3_2;
            v10_7.i = v4_2;
            v10_7.l = v5;
            v10_7.m = v6;
            v10_7.n = v7;
            v10_7.o = v8;
        }
        return;
    }
}
