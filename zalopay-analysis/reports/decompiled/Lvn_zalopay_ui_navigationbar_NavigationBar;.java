package vn.zalopay.ui.navigationbar;
public final class NavigationBar extends android.widget.FrameLayout {
    public static final synthetic int c;
    public final androidx.lifecycle.T a;
    public QZ.d b;

    public NavigationBar(android.content.Context p8, android.util.AttributeSet p9)
    {
        super(p8, p9);
        super.a = new androidx.lifecycle.T(new RZ.b(super.getContext().getString(2131951830), 0, 0, 1, 0, 0));
        android.view.View.inflate(super.getContext(), 2131560374, super);
        ((androidx.compose.ui.platform.ComposeView) super.findViewById(2131362447)).setContent(new y0.b(new QZ.a(super), 1, -667735325));
        return;
    }

    public final void a(long p20, q0.s p22, int p23)
    {
        int v0_7;
        q0.Y0 v6_0 = p22.x(1044306854);
        if ((p23 & 6) != 0) {
            v0_7 = p23;
        } else {
            int v0_6;
            if (!v6_0.u(p20)) {
                v0_6 = 2;
            } else {
                v0_6 = 4;
            }
            v0_7 = (p23 | v0_6);
        }
        if ((p23 & 48) == 0) {
            P0.c v2_4;
            if (!v6_0.I(this)) {
                v2_4 = 16;
            } else {
                v2_4 = 32;
            }
            v0_7 |= v2_4;
        }
        if (((v0_7 & 19) == 18) && (v6_0.c())) {
            v6_0.m();
        } else {
            P0.c v2_8 = androidx.compose.foundation.layout.w.e(D0.b.a, 0);
            String v3_2 = v6_0.P;
            D0.q v4_1 = v6_0.S();
            long v5_2 = D0.p.a;
            q0.Y0 v7_1 = D0.s.c(v5_2, v6_0);
            c1.m.d2.getClass();
            v6_0.l();
            if (!v6_0.O) {
                v6_0.h();
            } else {
                v6_0.K(c1.l.b);
            }
            q0.d.N(v2_8, c1.l.g, v6_0);
            q0.d.N(v4_1, c1.l.f, v6_0);
            if ((v6_0.O) || (!kotlin.jvm.internal.Intrinsics.areEqual(v6_0.G(), Integer.valueOf(v3_2)))) {
                Kb.e.t(v3_2, v6_0, v3_2, c1.l.i);
            }
            q0.d.N(v7_1, c1.l.d, v6_0);
            D0.q v12_1 = androidx.compose.foundation.layout.P0.p(v5_2, ((float) 24));
            Y.p v13_1 = new Y.p();
            p0.d v14 = p0.m.a(0, 0, 0, v6_0, 6, 6);
            P0.c v2_15 = v6_0.I(this);
            String v3_4 = v6_0.G();
            if ((v2_15 != null) || (v3_4 == q0.r.a)) {
                v3_4 = new QZ.b(this, 1);
                v6_0.B(v3_4);
            }
            q0.Y0 v7_0 = v6_0;
            n0.K0.a(q9.H4.a(2131232920, v6_0, 0), "", androidx.compose.foundation.a.e(v12_1, v13_1, v14, 0, 0, ((kotlin.jvm.functions.Function0) v3_4), 28), p20, v7_0, (((v0_7 << 9) & 7168) | 48), 0);
            v6_0 = v7_0;
            v6_0.W(1);
        }
        q0.Y0 v6_1 = v6_0.Y();
        if (v6_1 != null) {
            v6_1.d = new QZ.c(this, p20, p23, 0);
        }
        return;
    }

    public final void b(int p13, q0.s p14)
    {
        Dl.a v14_9;
        q0.v v9 = p14.x(-313370569);
        if (!v9.I(this)) {
            v14_9 = 2;
        } else {
            v14_9 = 4;
        }
        if ((((v14_9 | p13) & 3) == 2) && (v9.c())) {
            v9.m();
        } else {
            int v0_7;
            Dl.a v14_7 = m9.A3.d(this.a, v9);
            int v0_5 = ((RZ.b) v14_7.getValue());
            if (v0_5 == 0) {
                v0_7 = ql.b.a;
            } else {
                int v0_6 = v0_5.c;
                if (v0_6 == 0) {
                } else {
                    v0_7 = v0_6.a;
                }
            }
            Dl.a v2_3;
            Dl.a v2_2 = ((RZ.b) v14_7.getValue());
            if (v2_2 == null) {
                v2_3 = "";
            } else {
                v2_3 = v2_2.a;
                if (v2_3 == null) {
                }
            }
            y0.b v7_1;
            String v3 = v2_3;
            Dl.a v2_5 = ((RZ.b) v14_7.getValue());
            int v6_1 = 0;
            if ((v2_5 == null) || (v2_5.d != 1)) {
                v9.q(1636696198);
                v9.W(0);
                v7_1 = 0;
            } else {
                v9.q(1636598828);
                Dl.a v2_9 = v9.I(this);
                y0.b v7_2 = v9.G();
                if ((v2_9 != null) || (v7_2 == q0.r.a)) {
                    v7_2 = new QZ.b(this, 0);
                    v9.B(v7_2);
                }
                v7_1 = ((kotlin.jvm.functions.Function0) v7_2);
                v9.W(0);
            }
            Dl.a v2_14;
            Dl.a v2_13 = ((RZ.b) v14_7.getValue());
            if (v2_13 == null) {
                v2_14 = 0;
            } else {
                v2_14 = v2_13.f;
            }
            Dl.a v2_16;
            if (v2_14 == null) {
                v9.q(1636926342);
                v9.W(0);
                v2_16 = 0;
            } else {
                v9.q(-501389944);
                v2_16 = y0.c.c(-1712510218, new QZ.e(this, v14_7), v9);
                v9.W(0);
            }
            Dl.a v14_11 = ((RZ.b) v14_7.getValue());
            if ((v14_11 == null) || (v14_11.e != 1)) {
                v9.q(1637033478);
            } else {
                v9.q(-501382954);
                v6_1 = y0.c.c(678994675, new AA.x(1, this), v9);
            }
            v9.W(0);
            com.google.android.gms.internal.mlkit_vision_barcode.m5.c(0, oc.b.e(new K0.f0(v0_7), 0, 6), v3, 0, v7_1, v2_16, v6_1, 0, v9, 64, 137);
        }
        Dl.a v14_4 = v9.Y();
        if (v14_4 != null) {
            v14_4.d = new QZ.a(this, p13);
        }
        return;
    }

    public final void c(RZ.a p17, long p18, q0.s p20, int p21)
    {
        int v0_5;
        q0.v v13 = p20.x(390050965);
        if ((p21 & 6) != 0) {
            v0_5 = p21;
        } else {
            int v0_4;
            if (!v13.I(p17)) {
                v0_4 = 2;
            } else {
                v0_4 = 4;
            }
            v0_5 = (v0_4 | p21);
        }
        if ((p21 & 384) == 0) {
            long v3_3;
            if (!v13.I(this)) {
                v3_3 = 128;
            } else {
                v3_3 = 256;
            }
            v0_5 |= v3_3;
        }
        if (((v0_5 & 131) == 130) && (v13.c())) {
            v13.m();
        } else {
            int v0_10 = androidx.compose.foundation.layout.w.e(D0.b.a, 0);
            t4.g v4_1 = v13.P;
            l4.m v6_4 = v13.S();
            String v7_1 = D0.p.a;
            D0.q v8_0 = D0.s.c(v7_1, v13);
            c1.m.d2.getClass();
            v13.l();
            if (!v13.O) {
                v13.h();
            } else {
                v13.K(c1.l.b);
            }
            q0.d.N(v0_10, c1.l.g, v13);
            q0.d.N(v6_4, c1.l.f, v13);
            if ((v13.O) || (!kotlin.jvm.internal.Intrinsics.areEqual(v13.G(), Integer.valueOf(v4_1)))) {
                Kb.e.t(v4_1, v13, v4_1, c1.l.i);
            }
            q0.d.N(v8_0, c1.l.d, v13);
            int v0_16 = androidx.compose.foundation.layout.P0.p(v7_1, ((float) 24));
            t4.g v4_3 = (v13.I(this) | v13.I(p17));
            l4.m v6_9 = v13.G();
            if ((v4_3 != null) || (v6_9 == q0.r.a)) {
                v6_9 = new QZ.b(this, p17);
                v13.B(v6_9);
            }
            String v7_2 = 0;
            D0.q v8_1 = androidx.compose.foundation.a.f(0, ((kotlin.jvm.functions.Function0) v6_9), 0, v0_16, 7);
            if (p17 != null) {
                v7_2 = p17.b;
            }
            v13.F(604400049);
            int v0_20 = q9.N6.a(l4.o.a, v13);
            v13.F(604401818);
            t4.g v4_0 = new t4.g(((android.content.Context) v13.d(androidx.compose.ui.platform.AndroidCompositionLocals_androidKt.b)));
            v4_0.c = v7_2;
            m9.Q3.a(androidx.media3.exoplayer.source.r.i(v4_0, v0_20, v13, 0, 0), "", v8_1, 0, a1.n.e, 0, 0, v13, 24624, 104);
            v13.W(1);
        }
        l4.m v6_3 = v13.Y();
        if (v6_3 != null) {
            v6_3.d = new AD.f(this, p17, p18, p21);
        }
        return;
    }

    public final QZ.d getListener()
    {
        return this.b;
    }

    public final void setActionBar(java.util.List p11)
    {
        int v11_1;
        androidx.lifecycle.T v0 = this.a;
        RZ.b v2_1 = ((RZ.b) v0.d());
        if (v2_1 == null) {
            v11_1 = 0;
        } else {
            v11_1 = RZ.b.a(v2_1, 0, 0, 0, 0, 0, ((RZ.a) kotlin.collections.CollectionsKt.firstOrNull(p11)), 31);
        }
        v0.l(v11_1);
        return;
    }

    public void setBackgroundColor(int p11)
    {
        int v11_1;
        androidx.lifecycle.T v0 = this.a;
        RZ.b v2_1 = ((RZ.b) v0.d());
        if (v2_1 == null) {
            v11_1 = 0;
        } else {
            v11_1 = RZ.b.a(v2_1, 0, 0, new K0.y(K0.X.c(p11)), 0, 0, 0, 59);
        }
        v0.l(v11_1);
        return;
    }

    public final void setIsShowBackButton(boolean p11)
    {
        int v11_1;
        androidx.lifecycle.T v0 = this.a;
        RZ.b v2_1 = ((RZ.b) v0.d());
        if (v2_1 == null) {
            v11_1 = 0;
        } else {
            v11_1 = RZ.b.a(v2_1, 0, 0, 0, p11, 0, 0, 55);
        }
        v0.l(v11_1);
        return;
    }

    public final void setIsShowCloseButton(boolean p11)
    {
        int v11_1;
        androidx.lifecycle.T v0 = this.a;
        RZ.b v2_1 = ((RZ.b) v0.d());
        if (v2_1 == null) {
            v11_1 = 0;
        } else {
            v11_1 = RZ.b.a(v2_1, 0, 0, 0, 0, p11, 0, 47);
        }
        v0.l(v11_1);
        return;
    }

    public final void setListener(QZ.d p1)
    {
        this.b = p1;
        return;
    }

    public final void setTitle(String p11)
    {
        int v11_1;
        androidx.lifecycle.T v0 = this.a;
        RZ.b v2_1 = ((RZ.b) v0.d());
        if (v2_1 == null) {
            v11_1 = 0;
        } else {
            v11_1 = RZ.b.a(v2_1, p11, 0, 0, 0, 0, 0, 62);
        }
        v0.l(v11_1);
        return;
    }

    public final void setTitleColor(int p11)
    {
        int v11_1;
        androidx.lifecycle.T v0 = this.a;
        RZ.b v2_1 = ((RZ.b) v0.d());
        if (v2_1 == null) {
            v11_1 = 0;
        } else {
            v11_1 = RZ.b.a(v2_1, 0, new K0.y(K0.X.c(p11)), 0, 0, 0, 0, 61);
        }
        v0.l(v11_1);
        return;
    }
}
