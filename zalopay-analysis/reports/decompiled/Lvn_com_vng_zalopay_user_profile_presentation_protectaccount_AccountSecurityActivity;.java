package vn.com.vng.zalopay.user_profile.presentation.protectaccount;
public final class AccountSecurityActivity extends androidx.appcompat.app.AppCompatActivity implements DZ.o$a {
    public static final synthetic int t;
    public KG.a i;
    public KG.b j;
    public Pt.n k;
    public String l;
    public com.airbnb.lottie.network.g m;
    public QP.o n;
    public boolean o;
    public boolean p;
    public boolean q;
    public HD.i r;
    public final O5.b s;

    public AccountSecurityActivity()
    {
        gi.L.a(kotlinx.coroutines.Dispatchers.getDefault().plus(gi.N.c()));
        this.s = new O5.b(kotlin.jvm.internal.M.a.b(QP.D), new QP.h(this, 0), new QP.a(this, 1), new QP.h(this, 1));
        return;
    }

    public final void U0()
    {
        return;
    }

    public final void e1(String p2, boolean p3)
    {
        this.q = 0;
        int v0_1 = this.n;
        if (v0_1 == 0) {
            v0_1 = 0;
        }
        QP.o.a(v0_1, p3);
        if (!p3) {
            if (!kotlin.text.StringsKt.isBlank(p2)) {
                com.google.android.gms.internal.mlkit_vision_barcode.h7.c(this, p2);
            }
            return;
        } else {
            com.google.android.gms.internal.mlkit_vision_barcode.h7.c(this, wmoi.igob.ggcn(14937));
            return;
        }
    }

    public final QP.D g1()
    {
        return ((QP.D) this.s.getValue());
    }

    public final void m0(DZ.d p7)
    {
        try {
            long v0 = p7.b;
            java.util.Map v2_0 = this.n;
        } catch (Exception) {
            return;
        }
        if (v2_0 == null) {
            v2_0 = 0;
        } else {
        }
        String v3_2;
        v2_0.b(new QP.p(v0, p7.a));
        Pt.n v7_1 = wmoi.igob.ggcn(14938);
        java.util.Map v2_1 = wmoi.igob.ggcn(14939);
        if (v0 >= 0) {
            v3_2 = wmoi.igob.ggcn(14941);
        } else {
            v3_2 = wmoi.igob.ggcn(14940);
        }
        JP.a.a(v7_1, kotlin.collections.Z.mapOf(kotlin.TuplesKt.to(v2_1, v3_2)));
        this.g1().c.x(v0);
        return;
    }

    public final void onCreate(android.os.Bundle p11)
    {
        AA.e v0_0 = com.google.android.gms.internal.mlkit_vision_barcode.b0.a;
        y0.b v1_5 = v0_0.b();
        p9.n7.c(v1_5);
        this.i = v1_5;
        y0.b v1_8 = v0_0.s();
        p9.n7.c(v1_8);
        this.j = v1_8;
        this.k = v0_0.h();
        y0.b v1_1 = v0_0.r();
        p9.n7.c(v1_1);
        this.l = v1_1;
        this.m = new com.airbnb.lottie.network.g(v0_0.i(), v0_0.j(), 20);
        y0.b v1_7 = v0_0.h();
        int v4_7 = ((wP.c) v0_0.f().create(wP.c));
        p9.n7.c(v4_7);
        QP.f v5_15 = ((wP.b) v0_0.e().create(wP.b));
        p9.n7.c(v5_15);
        int v6_4 = ((dt.c) v0_0.q().create(dt.c));
        p9.n7.c(v6_4);
        int v3_15 = new Qa.w(v4_7, v5_15, v6_4, new n8.i(6, v0_0.a()), v0_0.g(), v0_0.h());
        int v2_15 = ((wP.a) v0_0.m().create(wP.a));
        p9.n7.c(v2_15);
        this.r = new HD.i(v1_7, v3_15, new oP.c(v2_15, v0_0.h()), 2);
        super.onCreate(p11);
        int v11_12 = com.google.android.gms.internal.mlkit_vision_barcode.b0.a;
        v11_12.getClass();
        n9.O.a = new rv.b(17, v11_12);
        int v11_13 = this.k;
        if (v11_13 == 0) {
            v11_13 = 0;
        }
        int v11_1;
        int v2_17 = HS.d.a;
        int v11_15 = v2_17.a(this, v11_13.getUserId());
        int v3_0 = wmoi.igob.ggcn(14942);
        int v4_12 = wmoi.igob.ggcn(14943);
        if (v11_15 == 0) {
            v11_1 = v3_0;
        } else {
            v11_1 = v4_12;
        }
        QP.f v5_0 = this.k;
        if (v5_0 == null) {
            v5_0 = 0;
        }
        if (XS.b.d(v5_0.F())) {
            v3_0 = v4_12;
        }
        int v4_2 = kotlin.TuplesKt.to(wmoi.igob.ggcn(14944), wmoi.igob.ggcn(14945));
        QP.f v5_6 = wmoi.igob.ggcn(14946);
        JP.a.a(wmoi.igob.ggcn(14948), kotlin.collections.a0.mapOf(new kotlin.Pair[] {v4_2, kotlin.TuplesKt.to(v5_6, v11_1), kotlin.TuplesKt.to(wmoi.igob.ggcn(14947), kotlin.collections.Z.mapOf(kotlin.TuplesKt.to(v5_6, v3_0)))})));
        int v11_5 = this.k;
        if (v11_5 == 0) {
            v11_5 = 0;
        }
        int v11_7 = v2_17.a(this, v11_5.getUserId());
        this.g1();
        int v4_3 = this.k;
        if (v4_3 == 0) {
            v4_3 = 0;
        }
        QP.f v5_9 = this.l;
        if (v5_9 != null) {
            kotlin.jvm.internal.Intrinsics.areEqual(v5_9, wmoi.igob.ggcn(14949));
        }
        this.n = new QP.o(new androidx.work.impl.constraints.trackers.g(v4_3, v11_7));
        kotlinx.coroutines.BuildersKt.launch$default(androidx.lifecycle.l0.i(this.getLifecycle()), 0, 0, new QP.f(this, 0, 4), 3, 0);
        kotlinx.coroutines.BuildersKt.launch$default(androidx.lifecycle.l0.i(this.getLifecycle()), 0, 0, new QP.f(this, 0, 1), 3, 0);
        k.e.a(this, new y0.b(new AA.e(17, this), 1, 1992593335));
        return;
    }

    public final void onResume()
    {
        super.onResume();
        kotlinx.coroutines.BuildersKt.launch$default(androidx.lifecycle.l0.i(this.getLifecycle()), 0, 0, new QP.f(this, 0, 5), 3, 0);
        if (this.o) {
            QP.D v0_1 = this.g1();
            QP.a v1_1 = new QP.a(this, 0);
            v0_1.getClass();
            kotlinx.coroutines.BuildersKt.launch$default(androidx.lifecycle.l0.l(v0_1), 0, 0, new JV.J(v0_1, v1_1, 0, 26), 3, 0);
        }
        return;
    }

    public final void onStart()
    {
        super.onStart();
        int v0 = this.m;
        if (v0 == 0) {
            v0 = 0;
        }
        v0.D(this);
        return;
    }

    public final void onStop()
    {
        int v0 = this.m;
        if (v0 == 0) {
            v0 = 0;
        }
        v0.G(this);
        super.onStop();
        return;
    }

    public final void onUserSessionExpiredEvent(eu.f p2)
    {
        String vtmp1 = q9.B4.a(p2.a, this);
        int v0 = this.m;
        if (v0 == 0) {
            v0 = 0;
        }
        v0.A(this, vtmp1);
        return;
    }
}
