package vn.com.vng.zalopay.homev5.presenter.ui.home_page;
public abstract class N0 {

    public static final void a(vn.com.vng.zalopay.homev5.presenter.ui.home_page.X0 p31, i3.b p32, Z.H p33, q0.C1 p34, com.google.android.gms.internal.firebase-perf.z p35, kotlin.jvm.functions.Function1 p36, q0.s p37, int p38)
    {
        vn.com.vng.zalopay.homev5.presenter.ui.home_page.X0 v0_0;
        boolean v11_1 = p37.x(-340918061);
        if (!v11_1.p(p31)) {
            v0_0 = 2;
        } else {
            v0_0 = 4;
        }
        D0.q v2_1;
        if (!v11_1.I(p32)) {
            v2_1 = 16;
        } else {
            v2_1 = 32;
        }
        D0.q v2_8;
        if (!v11_1.p(p33)) {
            v2_8 = 128;
        } else {
            v2_8 = 256;
        }
        D0.q v2_25;
        if (!v11_1.p(p34)) {
            v2_25 = 1024;
        } else {
            v2_25 = 2048;
        }
        D0.q v2_30;
        if (!v11_1.p(p35)) {
            v2_30 = 8192;
        } else {
            v2_30 = 16384;
        }
        D0.q v2_40;
        if (!v11_1.I(p36)) {
            v2_40 = 65536;
        } else {
            v2_40 = 131072;
        }
        kotlin.jvm.functions.Function0 v7_7;
        D0.q v2_42 = D0.p.a;
        if (!v11_1.p(v2_42)) {
            v7_7 = 524288;
        } else {
            v7_7 = 1048576;
        }
        kotlin.jvm.functions.Function1 v10_0;
        q0.v v8_1;
        vn.com.vng.zalopay.homev5.presenter.ui.home_page.X0 v0_32 = (((((((p38 | v0_0) | v2_1) | v2_8) | v2_25) | v2_30) | v2_40) | v7_7);
        if (((599187 & v0_32) == 599186) && (v11_1.c())) {
            v11_1.m();
            v10_0 = p36;
            v8_1 = v11_1;
        } else {
            kotlin.jvm.functions.Function0 v7_11 = ql.b.d;
            int v14_9 = androidx.compose.ui.platform.m0.f;
            int v12_4 = ((A1.c) v11_1.d(v14_9));
            int v13_13 = androidx.compose.foundation.layout.g.e(v11_1).f;
            v11_1.d(v14_9);
            int v13_15 = v13_13.e().b;
            q0.D1 v17_1 = v11_1.t(v13_15);
            kotlin.jvm.functions.Function1 v3_13 = v11_1.G();
            int v15_5 = q0.r.a;
            if ((v17_1 != null) || (v3_13 == v15_5)) {
                int v13_17 = new A1.f(v12_4.w(v13_15));
                v11_1.B(v13_17);
                v3_13 = v13_17;
            }
            int v19_1;
            q0.D0 v21;
            kotlin.jvm.functions.Function1 v3_16 = ((A1.f) v3_13).a;
            int v13_5 = v11_1.G();
            q0.D1 v17_2 = v14_9;
            if (v13_5 != v15_5) {
                v19_1 = v0_32;
                v21 = v15_5;
            } else {
                v21 = v15_5;
                v19_1 = v0_32;
                v13_5 = w5.c.o(kotlin.collections.E.listOf(new K0.y[] {new K0.y(v7_11), new K0.y(K0.X.d(4293523455))})), v12_4.s1(((float) 64)), v12_4.s1(((float) 290)), 8);
                v11_1.B(v13_5);
            }
            int v13_6 = ((K0.t) v13_5);
            vn.com.vng.zalopay.homev5.presenter.ui.home_page.X0 v0_6 = v11_1.G();
            int v12_1 = v21;
            if (v0_6 == v12_1) {
                v0_6 = q0.d.G(Boolean.FALSE, q0.D0.e);
                v11_1.B(v0_6);
            }
            int v14_6 = ((q0.C0) v0_6);
            vn.com.vng.zalopay.homev5.presenter.ui.home_page.X0 v0_9 = ((Boolean) v14_6.getValue());
            v0_9.getClass();
            int v15_0 = v11_1.G();
            K0.t v20 = v13_6;
            if (v15_0 == v12_1) {
                v15_0 = new uK.N(v14_6, 0, 1);
                v11_1.B(v15_0);
            }
            kotlin.Unit v1_3;
            q0.Z.g(v0_9, ((kotlin.jvm.functions.Function2) v15_0), v11_1);
            int v15_3 = Al.o.c(((Boolean) v14_6.getValue()).booleanValue(), v11_1);
            vn.com.vng.zalopay.homev5.presenter.ui.home_page.X0 v0_14 = (v19_1 & 458752);
            if (v0_14 != 131072) {
                v1_3 = 0;
            } else {
                v1_3 = 1;
            }
            vn.com.vng.zalopay.homev5.presenter.ui.home_page.X0 v0_17;
            int v19_2 = v0_14;
            if (((v19_1 & 112) != 32) && (!v11_1.I(p32))) {
                v0_17 = 0;
            } else {
                v0_17 = 1;
            }
            vn.com.vng.zalopay.homev5.presenter.ui.home_page.X0 v0_18 = (v0_17 | v1_3);
            kotlin.Unit v1_4 = v11_1.G();
            if ((v0_18 != null) || (v1_4 == v12_1)) {
                v1_4 = new bY.d(p36, p32, v14_6, 12);
                v11_1.B(v1_4);
            }
            kotlin.jvm.functions.Function0 v7_1;
            int v9_1;
            int v25_0 = v2_42;
            int v23_0 = v19_2;
            v10_0 = p36;
            kotlin.jvm.functions.Function0 v7_0 = v11_1;
            Al.o.a(v15_3, ((kotlin.jvm.functions.Function0) v1_4), androidx.compose.foundation.layout.P0.e(v2_42, 1065353216), 0, 0, y0.c.c(891639006, new vn.com.vng.zalopay.homev5.presenter.ui.home_page.u0(p34, v3_16, v20, p31, p33, p36, v7_11, p32, p35), v11_1), v7_0, 196608);
            v8_1 = v7_0;
            kotlin.Unit v1_9 = p31.f;
            boolean v11_0 = p31.c;
            int v15_4 = p31.e;
            if (v1_9 != null) {
                kotlin.Unit v1_11;
                v8_1.q(1312981354);
                kotlin.jvm.functions.Function1 v3_2 = v1_9.c;
                v7_1 = v23_0;
                if (v7_1 != 131072) {
                    v1_11 = 0;
                } else {
                    v1_11 = 1;
                }
                D0.q v2_7 = v8_1.G();
                if ((v1_11 != null) || (v2_7 == v12_1)) {
                    v2_7 = new vS.j(2, p36);
                    v8_1.B(v2_7);
                }
                kotlin.Unit v1_14;
                int v4_3 = ((kotlin.jvm.functions.Function0) v2_7);
                if (v7_1 != 131072) {
                    v1_14 = 0;
                } else {
                    v1_14 = 1;
                }
                D0.q v2_10 = v8_1.G();
                if ((v1_14 != null) || (v2_10 == v12_1)) {
                    v2_10 = new vS.j(3, p36);
                    v8_1.B(v2_10);
                }
                kotlin.Unit v1_16 = v25_0;
                v9_1 = 1065353216;
                v25_0 = v1_16;
                NA.i.a(0, androidx.compose.foundation.layout.c.C(androidx.compose.foundation.layout.c.E(androidx.compose.ui.platform.r0.q(androidx.compose.foundation.layout.P0.e(v1_16, 1065353216), "home_floating_campaign")), 0, 0, 0, ((A1.c) v8_1.d(v17_2)).w(v15_4), 7), v3_2, v4_3, ((kotlin.jvm.functions.Function0) v2_10), v8_1);
                v8_1.W(0);
            } else {
                v8_1.q(1312981353);
                v8_1.W(0);
                v7_1 = v23_0;
                v9_1 = 1065353216;
            }
            int v14_7;
            int v12_2;
            kotlin.Unit v1_19 = p31.g;
            if (v1_19 != null) {
                v8_1.q(1313518677);
                if (!((Boolean) v14_6.getValue()).booleanValue()) {
                    D0.q v2_24;
                    if (v7_1 != 131072) {
                        v2_24 = 0;
                    } else {
                        v2_24 = 1;
                    }
                    kotlin.jvm.functions.Function1 v3_3 = v8_1.G();
                    if ((v2_24 != null) || (v3_3 == v12_1)) {
                        v3_3 = new sK.b(17, p36);
                        v8_1.B(v3_3);
                    }
                    kotlin.jvm.functions.Function1 v3_6;
                    D0.q v2_28 = ((kotlin.jvm.functions.Function1) v3_3);
                    if (v7_1 != 131072) {
                        v3_6 = 0;
                    } else {
                        v3_6 = 1;
                    }
                    int v4_5 = v8_1.G();
                    if ((v3_6 != null) || (v4_5 == v12_1)) {
                        v4_5 = new sK.b(18, p36);
                        v8_1.B(v4_5);
                    }
                    int v4_8;
                    kotlin.jvm.functions.Function1 v3_9 = ((kotlin.jvm.functions.Function1) v4_5);
                    if (v7_1 != 131072) {
                        v4_8 = 0;
                    } else {
                        v4_8 = 1;
                    }
                    int v5_4 = v8_1.G();
                    if ((v4_8 != 0) || (v5_4 == v12_1)) {
                        v5_4 = new vS.j(4, p36);
                        v8_1.B(v5_4);
                    }
                    int v4_11;
                    kotlin.jvm.functions.Function0 v6_5 = ((kotlin.jvm.functions.Function0) v5_4);
                    if (v7_1 != 131072) {
                        v4_11 = 0;
                    } else {
                        v4_11 = 1;
                    }
                    int v5_6 = v8_1.G();
                    if ((v4_11 != 0) || (v5_6 == v12_1)) {
                        v5_6 = new vS.j(5, p36);
                        v8_1.B(v5_6);
                    }
                    v12_2 = v9_1;
                    v14_7 = v25_0;
                    l9.u.a(v1_19, v2_28, v3_9, 0, 0, v6_5, ((kotlin.jvm.functions.Function0) v5_6), v8_1, 3072);
                } else {
                    v12_2 = v9_1;
                    v14_7 = v25_0;
                }
                v8_1.W(0);
            } else {
                v8_1.q(1313518676);
                v8_1.W(0);
                v12_2 = v9_1;
                v14_7 = v25_0;
            }
            if (!v11_0) {
                v8_1.q(1284386799);
            } else {
                v8_1.q(1314239458);
                kotlin.Unit v1_23 = androidx.compose.foundation.layout.P0.e(v14_7, v12_2);
                D0.q v2_32 = androidx.compose.foundation.layout.w.e(D0.b.a, 0);
                kotlin.jvm.functions.Function1 v3_10 = v8_1.P;
                int v4_14 = v8_1.S();
                kotlin.Unit v1_24 = D0.s.c(v1_23, v8_1);
                c1.m.d2.getClass();
                v8_1.l();
                if (!v8_1.O) {
                    v8_1.h();
                } else {
                    v8_1.K(c1.l.b);
                }
                q0.d.N(v2_32, c1.l.g, v8_1);
                q0.d.N(v4_14, c1.l.f, v8_1);
                if ((v8_1.O) || (!kotlin.jvm.internal.Intrinsics.areEqual(v8_1.G(), Integer.valueOf(v3_10)))) {
                    Kb.e.t(v3_10, v8_1, v3_10, c1.l.i);
                }
                q0.d.N(v1_24, c1.l.d, v8_1);
                EA.b.a(v11_0, androidx.compose.foundation.layout.c.C(androidx.compose.foundation.layout.P0.g(androidx.compose.ui.graphics.a.b(androidx.compose.foundation.layout.A.a.a(v14_7, D0.b.h), 0, 0, 0, (- ((float) v15_4)), 0, 0, 0, 131055), v12_2), ((float) 16), 0, ((float) 16), ((float) 8), 2), 0, v8_1, 0);
                v8_1.W(1);
            }
            v8_1.W(0);
        }
        q0.v v8_2 = v8_1.Y();
        if (v8_2 != null) {
            v8_2.d = new vn.com.vng.zalopay.homev5.presenter.ui.home_page.v0(p31, p32, p33, p34, p35, v10_0, p38);
        }
        return;
    }

    public static final void b(vn.com.vng.zalopay.homev5.presenter.ui.home_page.X0 p16, i3.b p17, Z.H p18, q0.C1 p19, com.google.android.gms.internal.firebase-perf.z p20, kotlin.jvm.functions.Function1 p21, q0.s p22, int p23)
    {
        vn.com.vng.zalopay.homev5.presenter.ui.home_page.t0 v0_4;
        q0.v v13 = p22.x(1005810372);
        if ((p23 & 6) != 0) {
            v0_4 = p23;
        } else {
            vn.com.vng.zalopay.homev5.presenter.ui.home_page.t0 v0_3;
            if (!v13.p(p16)) {
                v0_3 = 2;
            } else {
                v0_3 = 4;
            }
            v0_4 = (v0_3 | p23);
        }
        if ((p23 & 48) == 0) {
            Z.H v3_3;
            if ((p23 & 64) != 0) {
                v3_3 = v13.I(p17);
            } else {
                v3_3 = v13.p(p17);
            }
            Z.H v3_4;
            if (v3_3 == null) {
                v3_4 = 16;
            } else {
                v3_4 = 32;
            }
            v0_4 |= v3_4;
        }
        Z.H v3_6;
        if ((p23 & 384) != 0) {
            v3_6 = p18;
        } else {
            q0.C1 v4_2;
            v3_6 = p18;
            if (!v13.p(p18)) {
                v4_2 = 128;
            } else {
                v4_2 = 256;
            }
            v0_4 |= v4_2;
        }
        q0.C1 v4_4;
        if ((p23 & 3072) != 0) {
            v4_4 = p19;
        } else {
            com.google.android.gms.internal.firebase-perf.z v5_2;
            v4_4 = p19;
            if (!v13.p(p19)) {
                v5_2 = 1024;
            } else {
                v5_2 = 2048;
            }
            v0_4 |= v5_2;
        }
        com.google.android.gms.internal.firebase-perf.z v5_4;
        if ((p23 & 24576) != 0) {
            v5_4 = p20;
        } else {
            kotlin.jvm.functions.Function1 v6_2;
            v5_4 = p20;
            if (!v13.p(p20)) {
                v6_2 = 8192;
            } else {
                v6_2 = 16384;
            }
            v0_4 |= v6_2;
        }
        kotlin.jvm.functions.Function1 v6_5;
        if ((196608 & p23) != 0) {
            v6_5 = p21;
        } else {
            vn.com.vng.zalopay.homev5.presenter.ui.home_page.a v8_2;
            v6_5 = p21;
            if (!v13.I(p21)) {
                v8_2 = 65536;
            } else {
                v8_2 = 131072;
            }
            v0_4 |= v8_2;
        }
        if ((1572864 & p23) == 0) {
            vn.com.vng.zalopay.homev5.presenter.ui.home_page.a v8_7;
            if (!v13.p(D0.p.a)) {
                v8_7 = 524288;
            } else {
                v8_7 = 1048576;
            }
            v0_4 |= v8_7;
        }
        if (((v0_4 & 599187) == 599186) && (v13.c())) {
            v13.m();
        } else {
            vn.com.vng.zalopay.homev5.presenter.ui.home_page.t0 v0_11;
            if (!p16.a.isEmpty()) {
                v0_11 = vn.com.vng.zalopay.homev5.presenter.ui.home_page.a.a;
            } else {
                v0_11 = vn.com.vng.zalopay.homev5.presenter.ui.home_page.b.a;
            }
            m9.j3.b(v0_11, 0, 0, 0, y0.c.c(-190131361, new vn.com.vng.zalopay.homev5.presenter.ui.home_page.t0(p16, p17, v3_6, v4_4, v5_4, v6_5), v13), v13, 24576, 14);
        }
        vn.com.vng.zalopay.homev5.presenter.ui.home_page.a v8_0 = v13.Y();
        if (v8_0 != null) {
            v8_0.d = new KW.b(p16, p17, p18, p19, p20, p21, p23);
        }
        return;
    }

    public static final void c(vn.com.vng.zalopay.homev5.presenter.ui.home_page.g1 p25, androidx.work.impl.utils.e p26, q0.s p27, int p28)
    {
        vn.com.vng.zalopay.homev5.presenter.ui.home_page.g1 v1_0;
        Z.H v3_0 = p25;
        int v0_9 = q0.D0.e;
        vn.com.vng.zalopay.homev5.presenter.ui.home_page.X0 v12_2 = p27.x(1984813594);
        if ((p28 & 6) != 0) {
            v1_0 = p28;
        } else {
            vn.com.vng.zalopay.homev5.presenter.ui.home_page.g1 v1_2;
            if (!v12_2.p(p25)) {
                v1_2 = 2;
            } else {
                v1_2 = 4;
            }
            v1_0 = (v1_2 | p28);
        }
        if ((p28 & 48) == 0) {
            vn.com.vng.zalopay.homev5.presenter.ui.home_page.X0 v2_17;
            if (!v12_2.p(p26)) {
                v2_17 = 16;
            } else {
                v2_17 = 32;
            }
            v1_0 |= v2_17;
        }
        if ((p28 & 384) == 0) {
            vn.com.vng.zalopay.homev5.presenter.ui.home_page.X0 v2_24;
            if (!v12_2.p(D0.p.a)) {
                v2_24 = 128;
            } else {
                v2_24 = 256;
            }
            v1_0 |= v2_24;
        }
        vn.com.vng.zalopay.homev5.presenter.ui.home_page.g1 v1_23;
        q0.v v6_6;
        androidx.work.impl.utils.e v11_2;
        int v15_5 = v1_0;
        if (((v15_5 & 147) == 146) && (v12_2.c())) {
            v12_2.m();
            v1_23 = p25;
            v11_2 = p26;
            v6_6 = v12_2;
        } else {
            vn.com.vng.zalopay.homev5.presenter.ui.home_page.g1 v1_29 = m9.k3.a(p25.k0(), v12_2, 0);
            vn.com.vng.zalopay.homev5.presenter.ui.home_page.g1 v4_8 = p25.H;
            v12_2.F(388053246);
            q0.C0 v5_17 = kotlin.coroutines.h.a;
            v12_2.F(1157296644);
            q0.v v6_9 = v12_2.p(v4_8);
            kotlin.jvm.functions.Function1 v7_19 = v12_2.G();
            q0.v v8_3 = q0.r.a;
            if ((v6_9 != null) || (v7_19 == v8_3)) {
                v7_19 = new i3.b(v4_8);
                v12_2.B(v7_19);
            }
            v12_2.W(0);
            vn.com.vng.zalopay.homev5.presenter.ui.home_page.g1 v4_10 = ((i3.b) v7_19);
            q0.Z.g(v4_10, new i3.d(v5_17, v4_10, 0, 0), v12_2);
            q0.Z.g(v4_10, new i3.d(v5_17, v4_10, 0, 1), v12_2);
            v12_2.W(0);
            q0.v v6_16 = q0.d.J(((vn.com.vng.zalopay.homev5.presenter.ui.home_page.X0) v1_29.getValue()), v12_2);
            int v9_4 = q0.d.J(v4_10, v12_2);
            Z.H v14_2 = Z.J.a(0, 0, 3, v12_2);
            vn.com.vng.zalopay.homev5.presenter.ui.home_page.X0 v2_42 = v12_2.G();
            if (v2_42 == v8_3) {
                v2_42 = q0.d.G(Boolean.FALSE, v0_9);
                v12_2.B(v2_42);
            }
            q0.C0 v18_1 = ((q0.C0) v2_42);
            vn.com.vng.zalopay.homev5.presenter.ui.home_page.X0 v2_0 = v12_2.G();
            if (v2_0 == v8_3) {
                v2_0 = q0.d.G(0, v0_9);
                v12_2.B(v2_0);
            }
            vn.com.vng.zalopay.homev5.presenter.ui.home_page.X0 v2_1 = ((q0.C0) v2_0);
            int v0_2 = ((A1.c) v12_2.d(androidx.compose.ui.platform.m0.f));
            kotlin.jvm.functions.Function1 v7_0 = v12_2.G();
            if (v7_0 == v8_3) {
                v7_0 = e5.i.q(q0.Z.i(v5_17, v12_2), v12_2);
            }
            q0.C0 v5_1 = ((q0.E) v7_0).a;
            kotlin.jvm.functions.Function1 v7_6 = v12_2.t(((vn.com.vng.zalopay.homev5.presenter.ui.home_page.X0) v1_29.getValue()).a.size());
            com.google.android.gms.internal.firebase-perf.z v13_0 = v12_2.G();
            if ((v7_6 != null) || (v13_0 == v8_3)) {
                v13_0 = Integer.valueOf(((vn.com.vng.zalopay.homev5.presenter.ui.home_page.X0) v1_29.getValue()).a.size());
                v12_2.B(v13_0);
            }
            kotlin.jvm.functions.Function1 v7_11 = ((Number) v13_0).intValue();
            q0.C0 v20 = v2_1;
            vn.com.vng.zalopay.homev5.presenter.ui.home_page.X0 v2_7 = ((v12_2.p(((h3.x) v4_10.d.getValue())) | v12_2.p(((vn.com.vng.zalopay.homev5.presenter.ui.home_page.X0) v1_29.getValue()).d)) | v12_2.t(v7_11));
            com.google.android.gms.internal.firebase-perf.z v13_7 = v12_2.G();
            int v21 = v15_5;
            if ((v2_7 != null) || (v13_7 == v8_3)) {
                vn.com.vng.zalopay.homev5.presenter.ui.home_page.X0 v2_10 = vn.com.vng.zalopay.homev5.presenter.ui.home_page.N0.f(v4_10, new lM.a(v1_29, 22));
                if (v2_10 == null) {
                    v13_7 = 0;
                } else {
                    v13_7 = Integer.valueOf((v2_10.intValue() + v7_11));
                }
                v12_2.B(v13_7);
            }
            vn.com.vng.zalopay.homev5.presenter.ui.home_page.X0 v2_15 = q0.d.J(((Integer) v13_7), v12_2);
            kotlin.jvm.functions.Function1 v7_13 = q0.d.J(Integer.valueOf(v7_11), v12_2);
            com.google.android.gms.internal.firebase-perf.z v13_12 = ((vn.com.vng.zalopay.homev5.presenter.ui.home_page.X0) v1_29.getValue()).a.iterator();
            vn.com.vng.zalopay.homev5.presenter.ui.home_page.g1 v22_0 = 0;
            while (v13_12.hasNext()) {
                if (!(((BB.F) v13_12.next()) instanceof BB.E)) {
                    v22_0++;
                }
                com.google.android.gms.internal.firebase-perf.z v13_13 = Integer.valueOf(v22_0);
                if (v22_0 < null) {
                    v13_13 = 0;
                }
                com.google.android.gms.internal.firebase-perf.z v13_17;
                if (v13_13 == null) {
                    v13_17 = kotlin.collections.E.getLastIndex(((vn.com.vng.zalopay.homev5.presenter.ui.home_page.X0) v1_29.getValue()).a);
                } else {
                    v13_17 = v13_13.intValue();
                }
                vn.com.vng.zalopay.homev5.presenter.ui.home_page.g1 v1_4;
                com.google.android.gms.internal.firebase-perf.z v13_19 = q0.d.J(Integer.valueOf(v13_17), v12_2);
                int v15_4 = (v21 & 14);
                vn.com.vng.zalopay.homev5.presenter.ui.home_page.g1 v22_1 = v1_29;
                if (v15_4 != 4) {
                    v1_4 = 0;
                } else {
                    v1_4 = 1;
                }
                int v17;
                androidx.work.impl.utils.e v11_1;
                com.google.android.gms.internal.firebase-perf.z v13_20;
                q0.C0 v24_0;
                vn.com.vng.zalopay.homev5.presenter.ui.home_page.g1 v10_1;
                Z.H v14_0;
                vn.com.vng.zalopay.homev5.presenter.ui.home_page.g1 v4_0;
                q0.D0 v27_2;
                int v19;
                q0.v v6_0;
                q0.C0 v5_2;
                vn.com.vng.zalopay.homev5.presenter.ui.home_page.g1 v1_9 = (((((v1_4 | v12_2.p(v14_2)) | v12_2.p(v9_4)) | v12_2.p(v2_15)) | v12_2.p(v0_2)) | v12_2.p(v7_13));
                A1.c v23_7 = v0_2;
                int v0_3 = v12_2.G();
                if ((v1_9 == null) && (v0_3 != v8_3)) {
                    v14_0 = v4_10;
                    v4_0 = v14_2;
                    v24_0 = v5_1;
                    v11_1 = v6_16;
                    v5_2 = v9_4;
                    v27_2 = v13_19;
                    v10_1 = 0;
                    v17 = 0;
                    v19 = 1;
                    v6_0 = v2_15;
                    v13_20 = v8_3;
                } else {
                    v24_0 = v5_1;
                    v5_2 = v9_4;
                    v14_0 = v4_10;
                    v4_0 = v14_2;
                    v11_1 = v6_16;
                    v27_2 = v13_19;
                    v10_1 = 0;
                    v17 = 0;
                    v19 = 1;
                    v13_20 = v8_3;
                    v6_0 = v2_15;
                    v0_3 = new GW.c(p25, v20, v23_7, v4_0, v5_2, v6_0, v7_13, 0, 4);
                    v3_0 = p25;
                    v12_2.B(v0_3);
                }
                vn.com.vng.zalopay.homev5.presenter.ui.home_page.X0 v2_21;
                q0.Z.g(v3_0, ((kotlin.jvm.functions.Function2) v0_3), v12_2);
                int v0_6 = v12_2.I(v14_0);
                if (v15_4 != 4) {
                    v2_21 = v17;
                } else {
                    v2_21 = v19;
                }
                int v0_7 = (v0_6 | v2_21);
                vn.com.vng.zalopay.homev5.presenter.ui.home_page.g1 v1_13 = v12_2.G();
                if ((v0_7 != 0) || (v1_13 == v13_20)) {
                    v1_13 = new tS.v(v14_0, v3_0, v10_1, 15);
                    v12_2.B(v1_13);
                }
                vn.com.vng.zalopay.homev5.presenter.ui.home_page.X0 v2_23;
                q0.Z.g(v14_0, ((kotlin.jvm.functions.Function2) v1_13), v12_2);
                int v0_10 = v12_2.p(v4_0);
                if (v15_4 != 4) {
                    v2_23 = v17;
                } else {
                    v2_23 = v19;
                }
                int v0_15;
                vn.com.vng.zalopay.homev5.presenter.ui.home_page.g1 v4_1;
                vn.com.vng.zalopay.homev5.presenter.ui.home_page.g1 v1_21;
                q0.v v8_1;
                int v0_14 = ((((v0_10 | v2_23) | v12_2.p(v6_0)) | v12_2.p(v11_1)) | v12_2.p(v5_2));
                vn.com.vng.zalopay.homev5.presenter.ui.home_page.g1 v1_20 = v12_2.G();
                if ((v0_14 == 0) && (v1_20 != v13_20)) {
                    v0_15 = v1_20;
                    v1_21 = v4_0;
                    v4_1 = v11_1;
                    v8_1 = v18_1;
                } else {
                    v1_21 = v4_0;
                    vn.com.vng.zalopay.homev5.presenter.ui.home_page.X0 v2_25 = v18_1;
                    q0.C0 v5_3 = v11_1;
                    v0_15 = new ES.d(v1_21, v2_25, v3_0, v6_0, v5_3, v5_2, 0);
                    v8_1 = v2_25;
                    v4_1 = v5_3;
                    v12_2.B(v0_15);
                }
                q0.Z.g(v1_21, ((kotlin.jvm.functions.Function2) v0_15), v12_2);
                int v0_21 = ((Iterable) ((vn.com.vng.zalopay.homev5.presenter.ui.home_page.X0) v22_1.getValue()).a);
                vn.com.vng.zalopay.homev5.presenter.ui.home_page.X0 v2_27 = new java.util.ArrayList();
                int v0_22 = v0_21.iterator();
                while (v0_22.hasNext()) {
                    Z.H v3_21 = v0_22.next();
                    if ((v3_21 instanceof BB.m)) {
                        v2_27.add(v3_21);
                    }
                }
                q0.C0 v5_8;
                vn.com.vng.zalopay.homev5.presenter.ui.home_page.X0 v2_29 = ((BB.m) kotlin.collections.CollectionsKt.firstOrNull(v2_27));
                int v0_24 = v24_0;
                Z.H v3_5 = ((v12_2.I(v0_24) | v12_2.p(v4_1)) | v12_2.p(v2_29));
                q0.C0 v5_6 = v12_2.p(v1_21);
                if (v15_4 != 4) {
                    v5_8 = v17;
                } else {
                    v5_8 = v19;
                }
                vn.com.vng.zalopay.homev5.presenter.ui.home_page.g1 v4_3;
                Z.H v3_7 = ((v3_5 | v5_6) | v5_8);
                q0.C0 v5_9 = v12_2.G();
                if ((v3_7 == null) && (v5_9 != v13_20)) {
                    v4_3 = v1_21;
                    v1_23 = p25;
                } else {
                    q0.C0 v5_10 = v1_21;
                    int v0_26 = new Am.a(v0_24, v2_29, p25, v4_1, v5_10, 10);
                    v1_23 = p25;
                    v4_3 = v5_10;
                    v12_2.B(v0_26);
                    v5_9 = v0_26;
                }
                Z.H v3_12;
                Z.H v3_9 = v4_3;
                q0.v v6_3 = v12_2;
                m9.n3.c(v2_29, v3_9, 0, ((kotlin.jvm.functions.Function1) v5_9), v6_3, 0);
                vn.com.vng.zalopay.homev5.presenter.ui.home_page.g1 v10_2 = v3_9;
                int v9_1 = v6_3;
                int v0_27 = v9_1.p(v10_2);
                vn.com.vng.zalopay.homev5.presenter.ui.home_page.X0 v2_31 = v27_2;
                Z.H v3_10 = v9_1.p(v2_31);
                if ((v21 & 112) != 32) {
                    v3_12 = v17;
                } else {
                    v3_12 = v19;
                }
                int v0_29 = ((v0_27 | v3_10) | v3_12);
                Z.H v3_13 = v9_1.G();
                if ((v0_29 == 0) && (v3_13 != v13_20)) {
                    v11_2 = p26;
                } else {
                    v11_2 = p26;
                    v3_13 = new XA.e(p26, v10_2, v2_31, 22);
                    v9_1.B(v3_13);
                }
                vn.com.vng.zalopay.homev5.presenter.ui.home_page.X0 v2_32;
                q0.Z.b(v11_2, v10_2, ((kotlin.jvm.functions.Function1) v3_13), v9_1);
                vn.com.vng.zalopay.homev5.presenter.ui.home_page.X0 v12_1 = ((vn.com.vng.zalopay.homev5.presenter.ui.home_page.X0) v22_1.getValue());
                int v0_32 = v1_23.G;
                if (v15_4 != 4) {
                    v2_32 = v17;
                } else {
                    v2_32 = v19;
                }
                com.google.android.gms.internal.firebase-perf.z v13_21;
                Z.H v3_16 = v9_1.G();
                if ((v2_32 == null) && (v3_16 != v13_20)) {
                    v13_21 = v0_32;
                } else {
                    v13_21 = v0_32;
                    int v0_34 = new qI.b(1, p25, vn.com.vng.zalopay.homev5.presenter.ui.home_page.g1, "processIntent", "processIntent(Ljava/lang/Object;)V", 0, 12);
                    v1_23 = p25;
                    v9_1.B(v0_34);
                    v3_16 = v0_34;
                }
                q0.v v8_2 = v9_1;
                vn.com.vng.zalopay.homev5.presenter.ui.home_page.N0.b(v12_1, v14_0, v10_2, v8_1, v13_21, ((kotlin.jvm.functions.Function1) ((kotlin.reflect.g) v3_16)), v8_2, (((v21 << 12) & 3670016) | 3136));
                v6_6 = v8_2;
            }
            v22_0 = -1;
        }
        int v0_38 = v6_6.Y();
        if (v0_38 != 0) {
            v0_38.d = new eJ.h(v1_23, v11_2, p28);
        }
        return;
    }

    public static final void d(int p8, q0.s p9)
    {
        int v1_10;
        q0.Y0 v9_1 = p9.x(2058185306);
        vK.a v0_1 = D0.p.a;
        if (!v9_1.p(v0_1)) {
            v1_10 = 2;
        } else {
            v1_10 = 4;
        }
        if ((((v1_10 | p8) & 3) == 2) && (v9_1.c())) {
            v9_1.m();
        } else {
            int v1_8;
            v9_1.q(-2063834071);
            int v1_6 = ((A1.c) v9_1.d(androidx.compose.ui.platform.m0.f));
            if (!((Boolean) v9_1.d(androidx.compose.ui.platform.B0.a)).booleanValue()) {
                v9_1.q(191412812);
                v9_1.W(0);
                String v2_7 = ((android.content.Context) v9_1.d(androidx.compose.ui.platform.AndroidCompositionLocals_androidKt.b));
                v1_8 = v1_6.w(v2_7.getResources().getDimensionPixelSize(v2_7.getResources().getIdentifier("status_bar_height", "dimen", "android")));
            } else {
                v9_1.q(222097263);
                v1_8 = v1_6.w(((D7.r) v9_1.d(D7.w.a)).c().l());
                v9_1.W(0);
            }
            v9_1.W(0);
            vn.com.vng.zalopay.homev5.presenter.ui.home_page.P0.d(v1_8, 0, androidx.compose.ui.platform.r0.q(androidx.compose.foundation.layout.P0.e(v0_1, 1065353216), "home_page_loading"), v9_1);
        }
        q0.Y0 v9_2 = v9_1.Y();
        if (v9_2 != null) {
            v9_2.d = new vK.a(p8, 8);
        }
        return;
    }

    public static final Object e(i3.b p9, qK.a p10, wg.c p11)
    {
        vn.com.vng.zalopay.homev5.presenter.ui.home_page.M0 v0_2;
        if (!(p11 instanceof vn.com.vng.zalopay.homev5.presenter.ui.home_page.M0)) {
            v0_2 = new vn.com.vng.zalopay.homev5.presenter.ui.home_page.M0(p11);
        } else {
            v0_2 = ((vn.com.vng.zalopay.homev5.presenter.ui.home_page.M0) p11);
            Object v1_2 = ((vn.com.vng.zalopay.homev5.presenter.ui.home_page.M0) p11).g;
            if ((v1_2 & -2147483648) == 0) {
            } else {
                ((vn.com.vng.zalopay.homev5.presenter.ui.home_page.M0) p11).g = (v1_2 - -2147483648);
            }
        }
        Object v11_1 = v0_2.f;
        Object v1_1 = vg.i.getCOROUTINE_SUSPENDED();
        CT.c v2_0 = v0_2.g;
        if (v2_0 == null) {
            kotlin.ResultKt.throwOnFailure(v11_1);
            Object v11_3 = vn.com.vng.zalopay.homev5.presenter.ui.home_page.N0.f(p9, p10);
            if (v11_3 == null) {
                Object v11_4 = p9.d();
                CT.c v2_2 = p9.e().c;
                if ((v11_4 != null) && ((!(v2_2 instanceof h3.A)) && (!v2_2.a))) {
                    p9.c((v11_4 - 1));
                    long v5_2 = q9.K6.g(3000, kotlin.time.f.c);
                    CT.c v2_8 = new CT.c(p9, v11_4, 0, 9);
                    v0_2.d = p9;
                    v0_2.e = p10;
                    v0_2.g = 1;
                    v11_1 = gi.N.v(gi.U.d(v5_2), v2_8, v0_2);
                    if (v11_1 == v1_1) {
                        return v1_1;
                    } else {
                        if (((kotlin.Pair) v11_1) != null) {
                        }
                    }
                }
            } else {
                return new Integer(v11_3.intValue());
            }
        } else {
            if (v2_0 != 1) {
                throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
            } else {
                int v9_3 = v0_2.e;
                Integer v10_2 = v0_2.d;
                kotlin.ResultKt.throwOnFailure(v11_1);
                p10 = v9_3;
                p9 = v10_2;
            }
        }
        return 0;
    }

    public static final Integer f(i3.b p4, kotlin.jvm.functions.Function1 p5)
    {
        Og.m v0_2 = Og.x.until(0, p4.d()).x();
        while (v0_2.c) {
            Integer v1_0 = v0_2.next();
            boolean v2_4 = ((DB.h) ((h3.x) p4.d.getValue()).get(((Number) v1_0).intValue()));
            if ((v2_4) && (((Boolean) p5.invoke(v2_4)).booleanValue() == 1)) {
            }
            return ((Integer) v1_0);
        }
        v1_0 = 0;
        return ((Integer) v1_0);
    }

    public static final boolean g(int p4, Z.v p5)
    {
        int v0_4 = ((Iterable) p5.h()).iterator();
        while (v0_4.hasNext()) {
            int v1_0 = v0_4.next();
            if (((Z.l) v1_0).getIndex() == p4) {
            }
            int v1_1 = ((Z.l) v1_0);
            if (v1_1 != 0) {
                int v0_3;
                int v0_2 = v1_1.getOffset();
                int v1_3 = (v1_1.getSize() + v1_1.getOffset());
                if (v0_2 >= p5.g()) {
                    v0_3 = 0;
                } else {
                    v0_3 = 1;
                }
                int v5_2;
                if (v1_3 <= p5.i()) {
                    v5_2 = 0;
                } else {
                    v5_2 = 1;
                }
                if ((v0_3 == 0) && (v5_2 == 0)) {
                    return 0;
                }
            }
            return 1;
        }
        v1_0 = 0;
    }
}
