package vn.com.vng.zalopay.receive_money;
public final class ReceiveMoneyActivity extends androidx.appcompat.app.AppCompatActivity implements gY.a {
    public static final synthetic int n;
    public KG.a i;
    public dagger.e j;
    public final O5.b k;
    public final q0.N0 l;
    public final gY.b m;

    public ReceiveMoneyActivity()
    {
        this.k = new O5.b(kotlin.jvm.internal.M.a.b(yK.F0), new WJ.u(this, 0), new WJ.a(this, 0), new WJ.u(this, 1));
        this.l = q0.d.G(kotlin.collections.a0.emptyMap(), q0.D0.e);
        this.m = new gY.b(this, this);
        return;
    }

    public static final Object e1(vn.com.vng.zalopay.receive_money.ReceiveMoneyActivity p17, android.content.Context p18, String p19, String p20, String p21, boolean p22, int p23, String p24, hK.o$b p25, float p26, QP.w p27, PJ.c p28, wg.c p29)
    {
        int v2_1;
        if (!(p29 instanceof WJ.e)) {
            v2_1 = new WJ.e(p17, p29);
        } else {
            v2_1 = ((WJ.e) p29);
            Object v3_5 = ((WJ.e) p29).s;
            if ((v3_5 & -2147483648) == 0) {
            } else {
                ((WJ.e) p29).s = (v3_5 - -2147483648);
            }
        }
        boolean v6_1;
        int v5_0;
        int v5_2;
        String v12_1;
        Object v3_0;
        hK.o$b v9_1;
        String v11_3;
        Object v4_2;
        Exception v0_3;
        Exception v0_8;
        String v10_1;
        android.graphics.Bitmap v1_10;
        android.graphics.Bitmap v14_0;
        Object v3_6;
        int v15_0;
        int v7_0;
        int v2_2;
        IllegalStateException v8_2;
        WJ.e v13 = v2_1;
        android.graphics.Bitmap v1_3 = v13.q;
        int v2_3 = vg.i.getCOROUTINE_SUSPENDED();
        Object v3_3 = v13.s;
        try {
            if (v3_3 == null) {
                kotlin.ResultKt.throwOnFailure(v1_3);
                try {
                    String v12_0 = Kg.c.b((((float) 1080) * p26));
                    v13.d = p18;
                    v13.e = p19;
                    v13.f = p20;
                    v13.g = p21;
                    v13.h = p24;
                    v13.i = p25;
                    v13.j = p27;
                    v13.k = p28;
                    v13.m = p22;
                    v13.n = p23;
                    v13.p = p26;
                    v13.o = 0;
                    v13.s = 1;
                    v14_0 = 0;
                    try {
                        String v11_2 = xK.f.f(p18, p19, p20, p21, p22, p23, p24, p25, 1080, v12_0, v13);
                    } catch (Exception v0_2) {
                        Object v4_0 = v14_0;
                        v15_0 = 0;
                        if (v15_0 == 0) {
                            if (v4_0 != null) {
                                v4_0.recycle();
                            }
                            if (v14_0 != null) {
                                v14_0.recycle();
                            }
                        }
                        throw v0_2;
                    }
                    if (v11_2 != v2_3) {
                        v4_2 = p19;
                        v12_1 = p20;
                        v6_1 = p22;
                        v5_2 = p23;
                        v10_1 = p24;
                        v9_1 = p25;
                        v7_0 = p28;
                        v8_2 = p27;
                        v3_6 = v11_2;
                        v15_0 = 0;
                        v11_3 = p21;
                        v1_10 = p26;
                        v0_8 = p18;
                        try {
                            v3_0 = ((android.graphics.Bitmap) v3_6);
                            try {
                                v13.d = v14_0;
                                v13.e = v14_0;
                                v13.f = v14_0;
                                v13.g = v14_0;
                                v13.h = v14_0;
                                v13.i = v14_0;
                                v13.j = v8_2;
                                v13.k = v7_0;
                                v13.l = v3_0;
                                v13.m = v6_1;
                                v13.n = v5_2;
                                v13.p = v1_10;
                                v13.o = v15_0;
                                v13.s = 2;
                                v1_3 = xK.f.f(v0_8, v4_2, v12_1, v11_3, v6_1, v5_2, v10_1, v9_1, 1080, 1440, v13);
                            } catch (Exception v0_2) {
                                v4_0 = v3_0;
                            }
                            if (v1_3 != v2_3) {
                                v5_0 = v7_0;
                                v0_3 = v8_2;
                                v2_2 = v15_0;
                                v4_0 = v3_0;
                                try {
                                    android.graphics.Bitmap v1_5 = ((android.graphics.Bitmap) v1_3);
                                    try {
                                        v0_3.invoke(v4_0, v1_5);
                                    } catch (Exception v0_2) {
                                        v14_0 = v1_5;
                                        v15_0 = 1;
                                    } catch (Exception v0_1) {
                                        v7_0 = v5_0;
                                        v15_0 = 1;
                                        if (v15_0 == 0) {
                                            v7_0.invoke(v0_1.getMessage());
                                        }
                                    }
                                    return kotlin.Unit.INSTANCE;
                                } catch (Exception v0_2) {
                                    v15_0 = v2_2;
                                }
                            } else {
                                return v2_3;
                            }
                            v14_0 = 0;
                        } catch (Exception v0_2) {
                            v4_0 = v14_0;
                        } catch (Exception v0_1) {
                        }
                    }
                } catch (Exception v0_2) {
                    v14_0 = 0;
                } catch (Exception v0_1) {
                    v7_0 = p28;
                    v15_0 = 0;
                }
            } else {
                if (v3_3 == 1) {
                    Object v3_7 = v13.o;
                    Exception v0_9 = v13.p;
                    v5_2 = v13.n;
                    v6_1 = v13.m;
                    v7_0 = v13.k;
                    v8_2 = v13.j;
                    v9_1 = v13.i;
                    v10_1 = v13.h;
                    v11_3 = v13.g;
                    v12_1 = v13.f;
                    v4_2 = v13.e;
                    android.graphics.Bitmap v14_3 = v13.d;
                    try {
                        kotlin.ResultKt.throwOnFailure(v1_3);
                        v15_0 = v3_7;
                        v3_6 = v1_3;
                        v1_10 = v0_9;
                        v0_8 = v14_3;
                        v14_0 = 0;
                    } catch (Exception v0_2) {
                        v15_0 = v3_6;
                        v4_0 = 0;
                    } catch (Exception v0_1) {
                        v15_0 = v3_6;
                    }
                } else {
                    if (v3_3 != 2) {
                        throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                    } else {
                        v2_2 = v13.o;
                        v3_0 = v13.l;
                        v5_0 = v13.k;
                        v0_3 = v13.j;
                        try {
                            kotlin.ResultKt.throwOnFailure(v1_3);
                            v14_0 = 0;
                        } catch (Exception v0_2) {
                            v15_0 = v2_2;
                            v4_0 = v3_0;
                        }
                    }
                }
            }
        } catch (Exception v0_1) {
            v15_0 = v2_2;
            v7_0 = v5_0;
        }
        return kotlin.Unit.INSTANCE;
    }

    public final void g1(android.content.Intent p5)
    {
        java.util.Map v5_1;
        if (p5 == null) {
            v5_1 = kotlin.collections.a0.emptyMap();
        } else {
            java.util.Map v5_2 = p5.getExtras();
            if (v5_2 != null) {
                q0.N0 v0_1 = kotlin.collections.Z.createMapBuilder();
                java.util.Iterator v1_1 = v5_2.keySet().iterator();
                while (v1_1.hasNext()) {
                    String v2_2 = ((String) v1_1.next());
                    String v3 = v5_2.getString(v2_2);
                    if (v3 != null) {
                        v0_1.put(v2_2, v3);
                    }
                }
                v5_1 = kotlin.collections.Z.build(v0_1);
            }
        }
        this.l.setValue(v5_1);
        return;
    }

    public final yK.F0 h1()
    {
        return ((yK.F0) this.k.getValue());
    }

    public final void onCreate(android.os.Bundle p41)
    {
        super.onCreate(p41);
        androidx.activity.q.a(this, 0, 3);
        BG.a.h(this.getWindow());
        AA.e v2_32 = n9.L.a;
        int v1_0 = new androidx.camera.video.internal.encoder.u(26, v2_32, n9.L.b, 0);
        jK.x v7_1 = new fK.c();
        y0.b v3_3 = new fK.a(v1_0, 6);
        jK.x v9_4 = new fK.a(v1_0, 0);
        int v4_11 = new fK.a(v1_0, 12);
        eH.e v5_23 = new fK.a(v1_0, 2);
        jK.x v6_14 = new fK.a(v1_0, 10);
        eH.e v14_3 = new fK.a(v1_0, 7);
        eH.e v15_3 = new Yt.w(new fK.a(v1_0, 13), 16);
        jK.x v8_8 = new fK.a(v1_0, 3);
        eH.e v10_12 = new fK.a(v1_0, 1);
        jK.x v11_5 = new fK.a(v1_0, 4);
        eH.e v17_1 = v9_4;
        eH.e v5_24 = v11_5;
        jK.x v11_7 = dagger.internal.f.b(new CL.d(v7_1, v5_23, v6_14, v14_3, v15_3, v8_8, new AU.d(v9_4, v10_12, v11_5, new fK.a(v1_0, 5), 12), new fK.a(v1_0, 14), 9));
        jK.x v12_9 = dagger.internal.f.b(new fK.e(v7_1, new fK.a(v1_0, 9), new fK.a(v1_0, 8)));
        jK.x v8_15 = v3_3;
        jK.x v9_10 = v17_1;
        y0.b v3_1 = dagger.internal.f.b(new Yt.w(dagger.internal.f.b(new eH.e(v7_1, dagger.internal.f.b(new fK.f(v7_1, v8_15, v9_10, v4_11, v11_7, v12_9)), 5)), 13));
        eH.e v14_1 = new eH.e(v3_1, 27);
        int v4_4 = dagger.internal.f.b(new WH.e(9, new XJ.b(v5_24)));
        eH.e v15_1 = new eH.e(v4_4, 22);
        eH.e v5_4 = new Xv.c(v3_1, v4_4, 13);
        yK.G0 v13_1 = new eH.e(v4_4, 26);
        jK.x v6_4 = new Xv.c(v3_1, v4_4, 15);
        dK.a v41_1 = v2_32;
        eH.e v10_2 = new eH.e(v4_4, 23);
        Xv.c v16_0 = v5_4;
        Xv.c v20 = new Xv.c(v3_1, v4_4, 11);
        eH.e v21 = new eH.e(v4_4, 25);
        Xv.c v22 = new Xv.c(v3_1, v4_4, 14);
        Xv.c v23 = new Xv.c(v3_1, v4_4, 12);
        Xv.c v24 = new Xv.c(v3_1, v4_4, 9);
        Xv.c v25 = new Xv.c(v3_1, v4_4, 10);
        jK.x v26 = new jK.x(v3_1, 8);
        jK.x v27 = new jK.x(v3_1, 9);
        eH.e v28 = new eH.e(v3_1, 28);
        eH.e v5_20 = new eH.e(v3_1, 21);
        eH.e v19 = v10_2;
        Xv.c v18 = v6_4;
        int v1_3 = dagger.internal.f.b(new Yt.w(dagger.internal.f.b(new eH.e(v7_1, dagger.internal.f.b(new fK.d(v7_1, v8_15, v9_10, new fK.a(v1_0, 11), v11_7, v12_9)), 4)), 12));
        yK.G0 v13_3 = new yK.G0(v14_1, v15_1, v16_0, v13_1, v18, v19, v20, v21, v22, v23, v24, v25, v26, v27, v28, v5_20, new eH.e(v1_3, 29), new jK.x(v1_3, 7), new jK.x(v4_4, 2), new jK.x(v4_4, 4), new jK.x(v4_4, 1), new jK.x(v4_4, 3), new eH.e(v4_4, 24), new jK.x(v4_4, 6), new jK.x(v3_1, 0), new jK.x(v3_1, 5));
        AA.e v2_23 = p9.m7.a(1);
        v2_23.put(yK.F0, v13_3);
        AA.e v2_26 = dagger.internal.f.b(new WH.e(1, new dagger.internal.n(v2_23)));
        y0.b v3_7 = v41_1.b();
        p9.n7.c(v3_7);
        this.i = v3_7;
        this.j = dagger.internal.f.a(v2_26);
        this.g1(this.getIntent());
        k.e.a(this, new y0.b(new AA.e(23, this), 1, 1266586000));
        return;
    }

    public final void onNewIntent(android.content.Intent p1)
    {
        super.onNewIntent(p1);
        this.setIntent(p1);
        this.g1(p1);
        return;
    }

    public final void onPause()
    {
        this.m.b();
        super.onPause();
        return;
    }

    public final void onResume()
    {
        super.onResume();
        this.m.a();
        return;
    }

    public final void onScreenCaptured()
    {
        yK.F0 v0_7 = ((mK.q0) this.h1().G.b.getValue());
        if ((!v0_7.s) && ((!v0_7.t) && (v0_7.u == mK.B.b))) {
            yK.F0 v0_1 = v0_7.d;
            if ((!v0_1.b) && (v0_1.c == null)) {
                this.h1().g0(new mK.c0(mK.v0.q));
            }
        }
        return;
    }

    public final void onWindowFocusChanged(boolean p1)
    {
        super.onWindowFocusChanged(p1);
        if (p1 != null) {
            BG.a.h(this.getWindow());
        }
        return;
    }
}
