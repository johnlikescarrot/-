package vn.com.vng.zalopay.homev5.presenter.ui.home_page;
public abstract class P0 {

    public static final void a(D0.q p6, q0.s p7, int p8)
    {
        float v0_14;
        q0.Y0 v7_1 = p7.x(-973769697);
        if (!v7_1.p(p6)) {
            v0_14 = 2;
        } else {
            v0_14 = 4;
        }
        if ((((v0_14 | p8) & 3) == 2) && (v7_1.c())) {
            v7_1.m();
        } else {
            float v0_4 = androidx.compose.foundation.layout.D.a(androidx.compose.foundation.layout.r.c, D0.b.n, v7_1, 48);
            D0.q v1_1 = v7_1.P;
            int v2_1 = v7_1.S();
            int v3_0 = D0.s.c(p6, v7_1);
            c1.m.d2.getClass();
            v7_1.l();
            if (!v7_1.O) {
                v7_1.h();
            } else {
                v7_1.K(c1.l.b);
            }
            q0.d.N(v0_4, c1.l.g, v7_1);
            q0.d.N(v2_1, c1.l.f, v7_1);
            if ((v7_1.O) || (!kotlin.jvm.internal.Intrinsics.areEqual(v7_1.G(), Integer.valueOf(v1_1)))) {
                Kb.e.t(v1_1, v7_1, v1_1, c1.l.i);
            }
            q0.d.N(v3_0, c1.l.d, v7_1);
            D0.q v1_3 = D0.p.a;
            Al.t.a(androidx.compose.foundation.layout.P0.p(v1_3, ((float) 36)), g0.f.b(((float) 12)), v7_1, 6);
            androidx.compose.foundation.layout.c.d(androidx.compose.foundation.layout.P0.i(v1_3, ((float) 16)), v7_1);
            androidx.media3.exoplayer.source.r.l(((float) 16), androidx.compose.foundation.layout.P0.q(v1_3, ((float) 58), ((float) int v3_1)), v7_1, 6, 1);
        }
        q0.Y0 v7_2 = v7_1.Y();
        if (v7_2 != null) {
            v7_2.d = new bN.f(p6, p8, 22);
        }
        return;
    }

    public static final void b(int p8, int p9, D0.q p10, q0.s p11)
    {
        int v3_2;
        q0.Y0 v11_1 = p11.x(-570730740);
        int v0_4 = (p9 & 1);
        if (v0_4 == 0) {
            if ((p8 & 6) != 0) {
                v3_2 = p8;
            } else {
                int v3_1;
                if (!v11_1.p(p10)) {
                    v3_1 = 2;
                } else {
                    v3_1 = 4;
                }
                v3_2 = (v3_1 | p8);
            }
        } else {
            v3_2 = (p8 | 6);
        }
        if (((v3_2 & 3) == 2) && (v11_1.c())) {
            v11_1.m();
        } else {
            int v1_1 = D0.p.a;
            if (v0_4 != 0) {
                p10 = v1_1;
            }
            int v0_3 = androidx.compose.foundation.layout.P0.i(p10, ((float) 80));
            int v3_5 = androidx.compose.foundation.layout.I0.b(androidx.compose.foundation.layout.r.a, D0.b.k, v11_1, 48);
            D0.q v4_1 = v11_1.P;
            boolean v5_1 = v11_1.S();
            int v0_5 = D0.s.c(v0_3, v11_1);
            c1.m.d2.getClass();
            v11_1.l();
            if (!v11_1.O) {
                v11_1.h();
            } else {
                v11_1.K(c1.l.b);
            }
            q0.d.N(v3_5, c1.l.g, v11_1);
            q0.d.N(v5_1, c1.l.f, v11_1);
            if ((v11_1.O) || (!kotlin.jvm.internal.Intrinsics.areEqual(v11_1.G(), Integer.valueOf(v4_1)))) {
                Kb.e.t(v4_1, v11_1, v4_1, c1.l.i);
            }
            q0.d.N(v0_5, c1.l.d, v11_1);
            v11_1.q(-2089180235);
            int v3_9 = 0;
            while (v3_9 < 4) {
                vn.com.vng.zalopay.homev5.presenter.ui.home_page.P0.a(androidx.compose.foundation.layout.L0.a(v1_1, 1065353216), v11_1, 0);
                v3_9++;
            }
            v11_1.W(0);
            v11_1.W(1);
        }
        q0.Y0 v11_2 = v11_1.Y();
        if (v11_2 != null) {
            v11_2.d = new AA.v(p10, p8, p9, 7);
        }
        return;
    }

    public static final void c(float p5, int p6, D0.q p7, q0.s p8)
    {
        vn.com.vng.zalopay.homev5.presenter.ui.home_page.O0 v0_2;
        q0.Y0 v8_1 = p8.x(-1309959389);
        if ((p6 & 6) != 0) {
            v0_2 = p6;
        } else {
            vn.com.vng.zalopay.homev5.presenter.ui.home_page.O0 v0_1;
            if (!v8_1.s(p5)) {
                v0_1 = 2;
            } else {
                v0_1 = 4;
            }
            v0_2 = (v0_1 | p6);
        }
        if ((((v0_2 | 48) & 19) == 18) && (v8_1.c())) {
            v8_1.m();
        } else {
            vn.com.vng.zalopay.homev5.presenter.ui.home_page.O0 v0_6 = D0.p.a;
            D0.p v7_5 = androidx.compose.foundation.layout.P0.g(androidx.compose.foundation.layout.P0.i(v0_6, (((float) 304) + p5)), 1065353216);
            int v1_2 = v8_1.G();
            if (v1_2 == q0.r.a) {
                v1_2 = w5.c.o(kotlin.collections.E.listOf(new K0.y[] {new K0.y(ql.b.d), new K0.y(K0.X.d(4293523455))})), 0, 0, 14);
                v8_1.B(v1_2);
            }
            androidx.compose.foundation.layout.w.a(androidx.compose.foundation.a.a(v7_5, ((K0.t) v1_2), 0, 6), v8_1, 0);
            p7 = v0_6;
        }
        q0.Y0 v8_2 = v8_1.Y();
        if (v8_2 != null) {
            v8_2.d = new vn.com.vng.zalopay.homev5.presenter.ui.home_page.O0(p5, p7, p6, 0);
        }
        return;
    }

    public static final void d(float p27, int p28, D0.q p29, q0.s p30)
    {
        D0.q v4_0;
        q0.Y0 v3_2 = p30.x(1353186876);
        if (!v3_2.p(p29)) {
            v4_0 = 2;
        } else {
            v4_0 = 4;
        }
        int v6_4;
        if (!v3_2.s(p27)) {
            v6_4 = 16;
        } else {
            v6_4 = 32;
        }
        D0.q v4_14 = ((v4_0 | p28) | v6_4);
        if (((v4_14 & 19) == 18) && (v3_2.c())) {
            v3_2.m();
        } else {
            v3_2.q0();
            if (((p28 & 1) != 0) && (!v3_2.d0())) {
                v3_2.m();
            }
            v3_2.X();
            int v8_5 = androidx.compose.foundation.layout.P0.e(p29, 1065353216);
            float v9_6 = androidx.compose.foundation.layout.w.e(D0.b.a, 0);
            int v11_5 = v3_2.P;
            int v12_2 = v3_2.S();
            int v8_6 = D0.s.c(v8_5, v3_2);
            c1.m.d2.getClass();
            float v13_2 = c1.l.b;
            v3_2.l();
            if (!v3_2.O) {
                v3_2.h();
            } else {
                v3_2.K(v13_2);
            }
            c1.j v14_1 = c1.l.g;
            q0.d.N(v9_6, v14_1, v3_2);
            float v9_7 = c1.l.f;
            q0.d.N(v12_2, v9_7, v3_2);
            int v12_3 = c1.l.i;
            if ((v3_2.O) || (!kotlin.jvm.internal.Intrinsics.areEqual(v3_2.G(), Integer.valueOf(v11_5)))) {
                Kb.e.t(v11_5, v3_2, v11_5, v12_3);
            }
            int v5_9 = c1.l.d;
            q0.d.N(v8_6, v5_9, v3_2);
            D0.q v4_19 = ((v4_14 >> 3) & 14);
            vn.com.vng.zalopay.homev5.presenter.ui.home_page.P0.c(p27, v4_19, 0, v3_2);
            vn.com.vng.zalopay.homev5.presenter.ui.home_page.P0.e(p27, v4_19, 0, v3_2);
            D0.p v15_2 = D0.p.a;
            D0.q v4_20 = androidx.compose.foundation.layout.P0.e(v15_2, 1065353216);
            int v6_11 = androidx.compose.foundation.layout.D.a(androidx.compose.foundation.layout.r.c, D0.b.m, v3_2, 0);
            int v11_7 = v3_2.P;
            int v8_8 = v3_2.S();
            D0.q v4_22 = D0.s.c(v4_20, v3_2);
            v3_2.l();
            if (!v3_2.O) {
                v3_2.h();
            } else {
                v3_2.K(v13_2);
            }
            q0.d.N(v6_11, v14_1, v3_2);
            q0.d.N(v8_8, v9_7, v3_2);
            if ((v3_2.O) || (!kotlin.jvm.internal.Intrinsics.areEqual(v3_2.G(), Integer.valueOf(v11_7)))) {
                Kb.e.t(v11_7, v3_2, v11_7, v12_3);
            }
            q0.d.N(v4_22, v5_9, v3_2);
            D0.q v7_0 = androidx.compose.foundation.layout.c.A(((float) 16), 0, v15_2, 2);
            androidx.compose.foundation.layout.c.d(androidx.compose.foundation.layout.P0.i(v15_2, p27), v3_2);
            vn.com.vng.zalopay.homev5.presenter.ui.home_page.P0.l(androidx.compose.foundation.layout.c.C(v7_0, 0, ((float) 12), 0, 0, 13), v3_2, 6);
            vn.com.vng.zalopay.homev5.presenter.ui.home_page.P0.f(androidx.compose.foundation.layout.c.C(v7_0, 0, ((float) 20), 0, 0, 13), v3_2, 6);
            vn.com.vng.zalopay.homev5.presenter.ui.home_page.P0.h(androidx.compose.foundation.layout.c.C(v7_0, 0, ((float) 16), 0, 0, 13), v3_2, 6);
            vn.com.vng.zalopay.homev5.presenter.ui.home_page.P0.g(androidx.compose.foundation.layout.c.C(v7_0, 0, ((float) 8), 0, 0, 13), v3_2, 6);
            androidx.compose.foundation.layout.c.d(androidx.compose.foundation.layout.P0.i(v15_2, ((float) 24)), v3_2);
            v3_2.q(-599257968);
            D0.q v4_9 = 0;
            while (v4_9 < 2) {
                vn.com.vng.zalopay.homev5.presenter.ui.home_page.P0.b(0, 1, 0, v3_2);
                v4_9++;
            }
            v3_2.W(0);
            vn.com.vng.zalopay.homev5.presenter.ui.home_page.P0.k(androidx.compose.foundation.layout.c.C(v7_0, 0, ((float) 16), 0, 0, 13), v3_2, 6);
            vn.com.vng.zalopay.homev5.presenter.ui.home_page.P0.b(6, 0, androidx.compose.foundation.layout.c.C(v15_2, 0, ((float) 12), 0, 0, 13), v3_2);
            vn.com.vng.zalopay.homev5.presenter.ui.home_page.P0.k(androidx.compose.foundation.layout.c.C(v7_0, 0, ((float) 24), 0, 0, 13), v3_2, 6);
            vn.com.vng.zalopay.homev5.presenter.ui.home_page.P0.j(androidx.compose.foundation.layout.c.C(v7_0, 0, ((float) 20), 0, 0, 13), v3_2, 6);
            v3_2.W(1);
            v3_2.W(1);
        }
        q0.Y0 v3_0 = v3_2.Y();
        if (v3_0 != null) {
            v3_0.d = new SB.b(p29, p27, p28);
        }
        return;
    }

    public static final void e(float p4, int p5, D0.q p6, q0.s p7)
    {
        vn.com.vng.zalopay.homev5.presenter.ui.home_page.O0 v0_2;
        q0.Y0 v7_1 = p7.x(-2050892870);
        if ((p5 & 6) != 0) {
            v0_2 = p5;
        } else {
            vn.com.vng.zalopay.homev5.presenter.ui.home_page.O0 v0_1;
            if (!v7_1.s(p4)) {
                v0_1 = 2;
            } else {
                v0_1 = 4;
            }
            v0_2 = (v0_1 | p5);
        }
        if ((((v0_2 | 48) & 19) == 18) && (v7_1.c())) {
            v7_1.m();
        } else {
            int v1_1 = D0.p.a;
            androidx.compose.foundation.layout.w.a(androidx.compose.foundation.a.b(com.google.android.gms.internal.mlkit_vision_barcode.I6.b(androidx.compose.foundation.layout.P0.e(androidx.compose.foundation.layout.c.w(0, (((float) 288) + p4), v1_1, 1), 1065353216), g0.f.d(((float) 16), ((float) 16), 0, 0, 12)), ql.b.d, K0.X.a), v7_1, 0);
            p6 = v1_1;
        }
        q0.Y0 v7_2 = v7_1.Y();
        if (v7_2 != null) {
            v7_2.d = new vn.com.vng.zalopay.homev5.presenter.ui.home_page.O0(p4, p6, p5, 1);
        }
        return;
    }

    public static final void f(D0.q p3, q0.s p4, int p5)
    {
        q0.Y0 v4_1 = p4.x(-948517687);
        if (((p5 & 3) == 2) && (v4_1.c())) {
            v4_1.m();
        } else {
            Al.t.a(androidx.compose.foundation.layout.P0.i(androidx.compose.foundation.layout.P0.g(p3, 1065353216), ((float) 36)), g0.f.b(((float) 8)), v4_1, 0);
        }
        q0.Y0 v4_2 = v4_1.Y();
        if (v4_2 != null) {
            v4_2.d = new bN.f(p3, p5, 25);
        }
        return;
    }

    public static final void g(D0.q p10, q0.s p11, int p12)
    {
        q0.Y0 v11_1 = p11.x(-1412310165);
        if (((p12 & 3) == 2) && (v11_1.c())) {
            v11_1.m();
        } else {
            int v0_3 = androidx.compose.foundation.layout.I0.b(androidx.compose.foundation.layout.r.a, D0.b.j, v11_1, 0);
            D0.q v1_10 = v11_1.P;
            int v3_0 = v11_1.S();
            g0.e v4_3 = D0.s.c(p10, v11_1);
            c1.m.d2.getClass();
            v11_1.l();
            if (!v11_1.O) {
                v11_1.h();
            } else {
                v11_1.K(c1.l.b);
            }
            q0.d.N(v0_3, c1.l.g, v11_1);
            q0.d.N(v3_0, c1.l.f, v11_1);
            if ((v11_1.O) || (!kotlin.jvm.internal.Intrinsics.areEqual(v11_1.G(), Integer.valueOf(v1_10)))) {
                Kb.e.t(v1_10, v11_1, v1_10, c1.l.i);
            }
            q0.d.N(v4_3, c1.l.d, v11_1);
            v11_1.q(-1915534920);
            int v0_2 = 0;
            while (v0_2 < 4) {
                g0.e v4_4 = D0.p.a;
                D0.q v1_16 = androidx.compose.foundation.layout.c.A(0, ((float) 8), androidx.compose.foundation.layout.L0.a(v4_4, 1065353216), 1);
                float v5_12 = androidx.compose.foundation.layout.D.a(androidx.compose.foundation.layout.r.c, D0.b.n, v11_1, 48);
                int v6_4 = v11_1.P;
                boolean v7_2 = v11_1.S();
                D0.q v1_17 = D0.s.c(v1_16, v11_1);
                c1.m.d2.getClass();
                v11_1.l();
                if (!v11_1.O) {
                    v11_1.h();
                } else {
                    v11_1.K(c1.l.b);
                }
                q0.d.N(v5_12, c1.l.g, v11_1);
                q0.d.N(v7_2, c1.l.f, v11_1);
                if ((v11_1.O) || (!kotlin.jvm.internal.Intrinsics.areEqual(v11_1.G(), Integer.valueOf(v6_4)))) {
                    Kb.e.t(v6_4, v11_1, v6_4, c1.l.i);
                }
                q0.d.N(v1_17, c1.l.d, v11_1);
                Al.t.a(androidx.compose.foundation.layout.P0.p(v4_4, ((float) 42)), g0.f.b(((float) 12)), v11_1, 6);
                androidx.compose.foundation.layout.c.d(androidx.compose.foundation.layout.P0.i(v4_4, ((float) 26)), v11_1);
                Al.t.a(androidx.compose.foundation.layout.P0.q(v4_4, ((float) 70), ((float) 6)), g0.f.b(((float) 16)), v11_1, 6);
                v11_1.W(1);
                v0_2++;
            }
            v11_1.W(0);
            v11_1.W(1);
        }
        q0.Y0 v11_2 = v11_1.Y();
        if (v11_2 != null) {
            v11_2.d = new bN.f(p10, p12, 27);
        }
        return;
    }

    public static final void h(D0.q p12, q0.s p13, int p14)
    {
        q0.Y0 v13_1 = p13.x(1079652104);
        if (((p14 & 3) == 2) && (v13_1.c())) {
            v13_1.m();
        } else {
            D0.q v0_4 = g0.f.b(((float) 12));
            IA.i v3_3 = K0.X.a;
            D0.q v0_8 = androidx.compose.foundation.layout.c.A(uA.l.a(((float) 16), v13_1), 0, androidx.compose.foundation.a.b(m9.M3.b(com.google.android.gms.internal.mlkit_vision_barcode.I6.b(androidx.compose.foundation.layout.P0.i(androidx.compose.foundation.layout.P0.g(p12, 1065353216), ((float) 72)), v0_4), ((float) 1), ql.b.K, v0_4), ql.b.d, v3_3), 2);
            float v6_3 = androidx.compose.foundation.layout.I0.b(androidx.compose.foundation.layout.r.a, D0.b.k, v13_1, 48);
            int v8_1 = v13_1.P;
            boolean v9_1 = v13_1.S();
            D0.q v0_9 = D0.s.c(v0_8, v13_1);
            c1.m.d2.getClass();
            v13_1.l();
            if (!v13_1.O) {
                v13_1.h();
            } else {
                v13_1.K(c1.l.b);
            }
            q0.d.N(v6_3, c1.l.g, v13_1);
            q0.d.N(v9_1, c1.l.f, v13_1);
            if ((v13_1.O) || (!kotlin.jvm.internal.Intrinsics.areEqual(v13_1.G(), Integer.valueOf(v8_1)))) {
                Kb.e.t(v8_1, v13_1, v8_1, c1.l.i);
            }
            q0.d.N(v0_9, c1.l.d, v13_1);
            vn.com.vng.zalopay.homev5.presenter.ui.home_page.P0.i(v13_1);
            androidx.compose.foundation.layout.w.a(androidx.compose.foundation.a.b(androidx.compose.foundation.layout.c.A(((float) 16), 0, androidx.compose.foundation.layout.P0.c(androidx.compose.foundation.layout.P0.t(androidx.compose.foundation.layout.c.A(0, ((float) 20), D0.p.a, 1), ((float) 33)), 1065353216), 2), ql.b.W, v3_3), v13_1, 0);
            vn.com.vng.zalopay.homev5.presenter.ui.home_page.P0.i(v13_1);
            v13_1.W(1);
        }
        q0.Y0 v13_2 = v13_1.Y();
        if (v13_2 != null) {
            v13_2.d = new bN.f(p12, p14, 26);
        }
        return;
    }

    public static final void i(q0.s p14)
    {
        D0.p v1 = D0.p.a;
        D0.q v0_6 = androidx.compose.foundation.layout.P0.t(v1, ((float) 139));
        g0.e v3_0 = androidx.compose.foundation.layout.D.a(androidx.compose.foundation.layout.r.h(((float) 16)), D0.b.m, p14, 6);
        c1.j v4_0 = p14.L();
        c1.j v6_0 = p14.g();
        D0.q v0_5 = D0.s.c(v0_6, p14);
        c1.m.d2.getClass();
        c1.k v7_1 = c1.l.b;
        if (p14.y() == null) {
            q0.d.B();
            throw 0;
        } else {
            p14.l();
            if (!p14.v()) {
                p14.h();
            } else {
                p14.K(v7_1);
            }
            c1.j v8_2 = c1.l.g;
            q0.d.N(v3_0, v8_2, p14);
            g0.e v3_1 = c1.l.f;
            q0.d.N(v6_0, v3_1, p14);
            c1.j v6_1 = c1.l.i;
            if ((p14.v()) || (!kotlin.jvm.internal.Intrinsics.areEqual(p14.G(), Integer.valueOf(v4_0)))) {
                Kb.e.s(v4_0, p14, v4_0, v6_1);
            }
            c1.j v4_1 = c1.l.d;
            q0.d.N(v0_5, v4_1, p14);
            D0.q v0_10 = androidx.compose.foundation.layout.I0.b(androidx.compose.foundation.layout.r.h(((float) 15)), D0.b.k, p14, 54);
            int v10_4 = p14.L();
            q0.R0 v11_2 = p14.g();
            D0.q v12 = D0.s.c(v1, p14);
            if (p14.y() == null) {
                q0.d.B();
                throw 0;
            } else {
                p14.l();
                if (!p14.v()) {
                    p14.h();
                } else {
                    p14.K(v7_1);
                }
                q0.d.N(v0_10, v8_2, p14);
                q0.d.N(v11_2, v3_1, p14);
                if ((p14.v()) || (!kotlin.jvm.internal.Intrinsics.areEqual(p14.G(), Integer.valueOf(v10_4)))) {
                    Kb.e.s(v10_4, p14, v10_4, v6_1);
                }
                q0.d.N(v12, v4_1, p14);
                g0.e v3_5 = g0.f.a;
                Al.t.a(androidx.compose.foundation.layout.P0.q(v1, ((float) 108), ((float) 6)), v3_5, p14, 6);
                Al.t.a(androidx.compose.foundation.layout.P0.p(v1, ((float) 16)), g0.f.b(((float) 16)), p14, 6);
                p14.i();
                Al.t.a(androidx.compose.foundation.layout.P0.q(v1, ((float) 76), ((float) 8)), v3_5, p14, 6);
                p14.i();
                return;
            }
        }
    }

    public static final void j(D0.q p16, q0.s p17, int p18)
    {
        q0.Y0 v2_2 = p17.x(-938340432);
        if (((p18 & 3) == 2) && (v2_2.c())) {
            v2_2.m();
        } else {
            int v3_6 = androidx.compose.foundation.layout.P0.i(p16, ((float) 206));
            D0.q v6_3 = androidx.compose.foundation.layout.I0.b(androidx.compose.foundation.layout.r.h(((float) 12)), D0.b.j, v2_2, 6);
            g0.e v7_2 = v2_2.P;
            c1.j v9_0 = v2_2.S();
            int v3_7 = D0.s.c(v3_6, v2_2);
            c1.m.d2.getClass();
            c1.k v10_1 = c1.l.b;
            v2_2.l();
            if (!v2_2.O) {
                v2_2.h();
            } else {
                v2_2.K(v10_1);
            }
            c1.j v11_1 = c1.l.g;
            q0.d.N(v6_3, v11_1, v2_2);
            D0.q v6_4 = c1.l.f;
            q0.d.N(v9_0, v6_4, v2_2);
            c1.j v9_1 = c1.l.i;
            if ((v2_2.O) || (!kotlin.jvm.internal.Intrinsics.areEqual(v2_2.G(), Integer.valueOf(v7_2)))) {
                Kb.e.t(v7_2, v2_2, v7_2, v9_1);
            }
            g0.e v7_3 = c1.l.d;
            q0.d.N(v3_7, v7_3, v2_2);
            int v3_9 = D0.p.a;
            Al.t.a(androidx.compose.foundation.layout.P0.c(androidx.compose.foundation.layout.L0.a(v3_9, 1065353216), 1065353216), g0.f.b(((float) 16)), v2_2, 0);
            D0.q v13_3 = androidx.compose.foundation.layout.L0.a(v3_9, 1065353216);
            int v5_4 = androidx.compose.foundation.layout.D.a(androidx.compose.foundation.layout.r.h(((float) 12)), D0.b.m, v2_2, 6);
            int v8_3 = v2_2.P;
            q0.R0 v15_2 = v2_2.S();
            D0.q v13_4 = D0.s.c(v13_3, v2_2);
            v2_2.l();
            if (!v2_2.O) {
                v2_2.h();
            } else {
                v2_2.K(v10_1);
            }
            q0.d.N(v5_4, v11_1, v2_2);
            q0.d.N(v15_2, v6_4, v2_2);
            if ((v2_2.O) || (!kotlin.jvm.internal.Intrinsics.areEqual(v2_2.G(), Integer.valueOf(v8_3)))) {
                Kb.e.t(v8_3, v2_2, v8_3, v9_1);
            }
            q0.d.N(v13_4, v7_3, v2_2);
            v2_2.q(461866958);
            int v4_1 = 0;
            while (v4_1 < 2) {
                Al.t.a(androidx.compose.foundation.layout.F.a(androidx.compose.foundation.layout.P0.g(v3_9, 1065353216), 1065353216), g0.f.b(((float) 16)), v2_2, 0);
                v4_1++;
            }
            q9.H.r(v2_2, 0, 1, 1);
        }
        q0.Y0 v2_0 = v2_2.Y();
        if (v2_0 != null) {
            v2_0.d = new bN.f(p16, p18, 23);
        }
        return;
    }

    public static final void k(D0.q p3, q0.s p4, int p5)
    {
        q0.Y0 v4_1 = p4.x(-1672005546);
        if (((p5 & 3) == 2) && (v4_1.c())) {
            v4_1.m();
        } else {
            Al.t.a(androidx.compose.foundation.layout.P0.q(p3, ((float) 164), ((float) 6)), g0.f.a, v4_1, 0);
        }
        q0.Y0 v4_2 = v4_1.Y();
        if (v4_2 != null) {
            v4_2.d = new bN.f(p3, p5, 28);
        }
        return;
    }

    public static final void l(D0.q p7, q0.s p8, int p9)
    {
        q0.Y0 v8_1 = p8.x(-1545196206);
        if (((p9 & 3) == 2) && (v8_1.c())) {
            v8_1.m();
        } else {
            D0.q v1_0 = androidx.compose.foundation.layout.P0.g(p7, 1065353216);
            float v2_1 = androidx.compose.foundation.layout.I0.b(androidx.compose.foundation.layout.r.a, D0.b.k, v8_1, 48);
            D0.p v3_1 = v8_1.P;
            g0.e v4_1 = v8_1.S();
            D0.q v1_1 = D0.s.c(v1_0, v8_1);
            c1.m.d2.getClass();
            v8_1.l();
            if (!v8_1.O) {
                v8_1.h();
            } else {
                v8_1.K(c1.l.b);
            }
            q0.d.N(v2_1, c1.l.g, v8_1);
            q0.d.N(v4_1, c1.l.f, v8_1);
            if ((v8_1.O) || (!kotlin.jvm.internal.Intrinsics.areEqual(v8_1.G(), Integer.valueOf(v3_1)))) {
                Kb.e.t(v3_1, v8_1, v3_1, c1.l.i);
            }
            q0.d.N(v1_1, c1.l.d, v8_1);
            D0.p v3_2 = D0.p.a;
            g0.e v4_5 = g0.f.a;
            Al.t.a(androidx.compose.foundation.layout.P0.q(v3_2, ((float) 157), ((float) 32)), v4_5, v8_1, 6);
            androidx.compose.foundation.layout.c.d(androidx.compose.foundation.layout.L0.a(v3_2, 1065353216), v8_1);
            Al.t.a(androidx.compose.foundation.layout.P0.p(v3_2, ((float) 32)), v4_5, v8_1, 6);
            androidx.compose.foundation.layout.c.d(androidx.compose.foundation.layout.P0.t(v3_2, ((float) 12)), v8_1);
            Al.t.a(androidx.compose.foundation.layout.P0.p(v3_2, ((float) 32)), v4_5, v8_1, 6);
            v8_1.W(1);
        }
        q0.Y0 v8_2 = v8_1.Y();
        if (v8_2 != null) {
            v8_2.d = new bN.f(p7, p9, 24);
        }
        return;
    }
}
