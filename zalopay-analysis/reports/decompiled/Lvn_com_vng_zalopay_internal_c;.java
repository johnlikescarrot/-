package vn.com.vng.zalopay.internal;
public final class c implements vn.com.vng.zalopay.internal.d {
    public final wt.d$c a;
    public final Ns.g b;
    public final gs.f c;
    public final ju.f$b d;

    public c(wt.d$c p1, Ns.g p2, gs.f p3, ju.f$b p4)
    {
        this.a = p1;
        this.b = p2;
        this.c = p3;
        this.d = p4;
        return;
    }

    public final int a()
    {
        return 70;
    }

    public final boolean b()
    {
        return 1;
    }

    public final void c(android.app.Application p7)
    {
        Throwable v0_5 = this.b.c(p7);
        boolean v1_3 = eQ.a.d(p7);
        UR.a.a(new b());
        ju.f$b v3 = this.d;
        UR.a.a(new GO.k(v3, new GO.b(v3, v0_5, v1_3)));
        UR.a.a(new GO.h(this.a));
        UR.a.a(new GO.f());
        UR.a.a(new GO.g(this.b, p7));
        UR.a.a(GO.e.a);
        p7.getSharedPreferences(wmoi.igob.ggcn(2329), 0);
        try {
            new Fs.c();
            Throwable v0_11 = UR.a.b;
        } catch (Throwable v0_12) {
            throw v0_12;
        }
        v0_11.d = 1;
        if (!v0_11.c) {
            v0_11.c = 1;
            v0_11.b.execute(v0_11);
        }
        return;
    }
}
