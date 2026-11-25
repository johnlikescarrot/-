package vn.com.vng.zalopay.ui.activity;
public final class HomeActivity extends vn.com.vng.zalopay.user.UserBaseActivity implements Vy.d, ZO.d, SE.a, DZ.g$a, GX.b$a {
    public static final synthetic int I;
    public Yv.i A;
    public com.android.billingclient.api.c0 B;
    public int C;
    public final android.os.Handler D;
    public Wy.i E;
    public final int F;
    public final ng.S G;
    public final ng.S H;
    public final OD.a r;
    public Cx.h s;
    public final O5.b t;
    public dagger.e u;
    public dagger.e v;
    public dagger.e w;
    public androidx.lifecycle.r0 x;
    public O5.b y;
    public St.a z;

    static HomeActivity()
    {
        xq.kowgnpsu.fcafafcaecfcaefeebdd(0);
        return;
    }

    public HomeActivity()
    {
        this.r = new OD.a("", -1);
        this.t = new O5.b(kotlin.jvm.internal.M.a.b(Cx.g), new MO.g(this, 0), new MO.c(this, 0), new MO.g(this, 1));
        this.C = -1;
        this.D = new android.os.Handler(android.os.Looper.getMainLooper());
        this.F = 2131558464;
        this.registerForActivityResult(new androidx.fragment.app.f0(6), new BV.k1(2, this));
        this.G = ng.x.b(new MO.c(this, 1));
        this.H = ng.x.b(new MO.c(this, 2));
        return;
    }

    public final void N(java.util.List p4)
    {
        WO.j v1 = 0;
        if (!VE.c.b(((java.util.ArrayList) p4))) {
            if (VE.c.n(p4)) {
                WO.j v4_7 = this.u;
                if (v4_7 != null) {
                    v1 = v4_7;
                }
                ((WO.j) v1.get()).E0();
            }
        } else {
            WO.j v4_3 = this.u;
            if (v4_3 == null) {
                v4_3 = 0;
            }
            WO.j v4_5 = ((WO.j) v4_3.get());
            IT.b v0_2 = v4_5.C;
            if (v0_2 != null) {
                v4_5.B.a(v0_2.a);
                v4_5.C = 0;
                return;
            }
        }
        return;
    }

    public final void Q(WO.i p4)
    {
        m9.B.c(this, this.getString(2131953586), this.getString(2131951725), this.getString(2131952106), p4);
        return;
    }

    public final void T(OY.b p1)
    {
        try {
            p1.b(this);
        } catch (Exception) {
        }
        return;
    }

    public final void Z(String p3, MO.e p4)
    {
        m9.B.c(this, p3, this.getString(2131952450), this.getString(2131952428), p4);
        return;
    }

    public final void a(int p2)
    {
        if (p2 == 1500) {
            new vn.com.vng.zalopay.authentication.secret.a().c();
        }
        return;
    }

    public final boolean bdbfbucorjil(android.view.MotionEvent p6)
    {
        if (p6.getAction() == 0) {
            android.view.View v0_1 = this.getCurrentFocus();
            if ((v0_1 instanceof android.widget.EditText)) {
                boolean v1_3 = new android.graphics.Rect();
                android.widget.EditText v2_0 = ((android.widget.EditText) v0_1);
                v2_0.getGlobalVisibleRect(v1_3);
                if (!v1_3.contains(((int) p6.getRawX()), ((int) p6.getRawY()))) {
                    v2_0.clearFocus();
                    com.google.android.gms.internal.mlkit_vision_barcode.o0.a(v0_1);
                }
            }
        }
        return super.dispatchTouchEvent(p6);
    }

    public final void c(int p4)
    {
        if (p4 == 1500) {
            new vn.com.vng.zalopay.authentication.d(this, this.getString(2131953384), 0, new MO.f()).a();
        }
        return;
    }

    public native boolean dispatchTouchEvent(android.view.MotionEvent p0);

    public final androidx.fragment.app.Fragment e1()
    {
        return this.getSupportFragmentManager().D(2131362460);
    }

    public final androidx.fragment.app.Fragment g1()
    {
        return 0;
    }

    public final android.app.Activity getActivity()
    {
        return this;
    }

    public final android.content.Context getContext()
    {
        return this;
    }

    public final int h1()
    {
        return this.F;
    }

    public final void hideLoading()
    {
        m9.B.a();
        return;
    }

    public final void i0(String p1, String p2, String p3, WO.i p4)
    {
        m9.B.c(this, p1, p2, p3, p4);
        return;
    }

    public final OD.a i1()
    {
        return this.r;
    }

    public final void k1()
    {
        return;
    }

    public final void l0()
    {
        android.app.FragmentManager v0_0 = this.w;
        if (v0_0 == null) {
            v0_0 = 0;
        }
        if (!q9.Z.a(this, ((Pt.n) v0_0.get()))) {
            this.r1();
            return;
        } else {
            android.app.FragmentManager v0_2 = new MO.d(0, this);
            vn.com.vng.zalopay.authentication.AuthenticationDialog v1_1 = vn.com.vng.zalopay.authentication.AuthenticationDialog.a();
            v1_1.g = 1;
            v1_1.e = v0_2;
            v1_1.show(this.getFragmentManager(), wmoi.igob.ggcn(4573));
            return;
        }
    }

    public final CC.k m1()
    {
        return super.m1();
    }

    public final void n1(CC.k p1)
    {
        p1.w1(this);
        return;
    }

    public final void o()
    {
        com.google.android.gms.internal.mlkit_vision_barcode.T7.d(this, new DZ.e(2131234257, this.getString(2131952849), this.getString(2131952848), this.getString(2131952058), this.getString(2131955646), 1, 17), wmoi.igob.ggcn(4574));
        return;
    }

    public final void o0()
    {
        GX.b v0_0 = this.H;
        ((GX.b) v0_0.getValue()).d(this);
        ((GX.b) v0_0.getValue()).b();
        return;
    }

    public final void o1(android.content.Intent p7)
    {
        String v0_1 = wmoi.igob.ggcn(4575);
        int v1_4 = p7.getStringExtra(v0_1);
        if (v1_4 != 0) {
            int v1_1;
            String v2_14 = v1_4.hashCode();
            if (v2_14 == -934326481) {
                if (v1_4.equals(wmoi.igob.ggcn(4579))) {
                    v1_1 = 3;
                    this.s1(v1_1);
                    String v2_8 = this.y;
                    if (v2_8 != null) {
                        String v2_12 = ((Wy.d) kotlin.collections.CollectionsKt.getOrNull(((B0.z) v2_8.d), v1_1));
                        if (v2_12 != null) {
                            int v3_3 = this.y;
                            if (v3_3 == 0) {
                                v3_3 = 0;
                            }
                            v3_3.B(new Wy.c(v1_1, v2_12.e, v2_12.c));
                        }
                    }
                }
            } else {
                if (v2_14 == 3480) {
                    if (v1_4.equals(wmoi.igob.ggcn(4578))) {
                        v1_1 = 4;
                    }
                } else {
                    if (v2_14 == 3208415) {
                        if (v1_4.equals(wmoi.igob.ggcn(4577))) {
                            v1_1 = 0;
                        }
                    } else {
                        if ((v2_14 == 2141246174) && (v1_4.equals(wmoi.igob.ggcn(4576)))) {
                            v1_1 = 1;
                        }
                    }
                }
            }
            p7.removeExtra(v0_1);
            return;
        } else {
            return;
        }
    }

    public final void onActivityResult(int p2, int p3, android.content.Intent p4)
    {
        super.onActivityResult(p2, p3, p4);
        if (p2 == 131) {
            this.setResult(p3, p4);
            this.finish();
        }
        return;
    }

    public final void onConfigAppResourceReady(Os.b p1)
    {
        return;
    }

    public final void onCreate(android.os.Bundle p10)
    {
        this.setTheme(2132017582);
        super.onCreate(p10);
        if (this.p) {
            int v0_11 = this.u;
            com.android.billingclient.api.c0 v1_0 = 0;
            if (v0_11 == 0) {
                v0_11 = 0;
            }
            int v0_2 = ((WO.j) v0_11.get());
            v0_2.b = this;
            int v2_0 = v0_2.d;
            if (!v2_0.e(v0_2)) {
                v2_0.k(v0_2);
            }
            v0_2.j.a = 6;
            int v0_4 = this.v;
            if (v0_4 == 0) {
                v0_4 = 0;
            }
            int v0_6 = ((RE.c) v0_4.get());
            v0_6.b = this;
            v0_6.f.k(v0_6);
            Pb.b.a().getClass();
            int v0_10 = Pb.b.b(wmoi.igob.ggcn(4580));
            try {
                v0_10.start();
                int v2_3 = this.u;
            } catch (com.android.billingclient.api.c0 v10_24) {
                v0_10.stop();
                throw v10_24;
            }
            if (v2_3 == 0) {
                v2_3 = 0;
            } else {
            }
            ((WO.j) v2_3.get()).i.a();
            v0_10.stop();
            n9.c0.a(wmoi.igob.ggcn(4581), new MO.a(this, 0));
            if (p10 == null) {
                p10 = this.getIntent().getExtras();
            }
            n9.c0.a(wmoi.igob.ggcn(4582), new BH.m(this, p10, 12));
            n9.c0.a(wmoi.igob.ggcn(4583), new MO.a(this, 1));
            if (android.os.Build$VERSION.SDK_INT >= 33) {
                com.android.billingclient.api.c0 v10_30 = this.G;
                String v3_35 = ((GX.b) v10_30.getValue());
                if (v3_35 != null) {
                    v3_35.d(this);
                }
                com.android.billingclient.api.c0 v10_32 = ((GX.b) v10_30.getValue());
                if (v10_32 != null) {
                    v10_32.b();
                }
            } else {
                com.android.billingclient.api.c0 v10_33 = this.u;
                if (v10_33 == null) {
                    v10_33 = 0;
                }
                ((WO.j) v10_33.get()).E0();
            }
            com.android.billingclient.api.c0 v10_1 = this.w;
            if (v10_1 == null) {
                v10_1 = 0;
            }
            com.android.billingclient.api.c0 v10_5 = kotlin.TuplesKt.to(wmoi.igob.ggcn(4584), ((Pt.n) v10_1.get()).getUserId());
            String v3_1 = this.w;
            if (v3_1 == null) {
                v3_1 = 0;
            }
            String v3_7 = kotlin.TuplesKt.to(wmoi.igob.ggcn(4585), Long.valueOf(((Pt.n) v3_1.get()).p().b));
            int v4_1 = this.w;
            if (v4_1 == 0) {
                v4_1 = 0;
            }
            nz.b.a(wmoi.igob.ggcn(4590), kotlin.collections.a0.mapOf(new kotlin.Pair[] {v10_5, v3_7, kotlin.TuplesKt.to(wmoi.igob.ggcn(4586), Integer.valueOf(((Pt.n) v4_1.get()).p().r)), kotlin.TuplesKt.to(wmoi.igob.ggcn(4587), wmoi.igob.ggcn(4588)), kotlin.TuplesKt.to(wmoi.igob.ggcn(4589), "")})));
            androidx.activity.q.a(this, 0, 3);
            com.android.billingclient.api.c0 v10_10 = this.findViewById(2131362961);
            androidx.core.view.h0.l(v10_10, new hJ.g(v10_10));
            if (!v10_10.isAttachedToWindow()) {
                v10_10.addOnAttachStateChangeListener(new nY.e());
            } else {
                v10_10.requestApplyInsets();
            }
            String v3_19;
            com.android.billingclient.api.c0 v10_11 = this.getWindow();
            int v4_9 = new androidx.core.view.e(2, this.getWindow().getDecorView());
            String v3_17 = android.os.Build$VERSION.SDK_INT;
            if (v3_17 < 35) {
                if (v3_17 < 30) {
                    v3_19 = new androidx.core.view.m1(v10_11, v4_9);
                } else {
                    v3_19 = new androidx.core.view.n1(v10_11, v4_9);
                }
            } else {
                v3_19 = new androidx.core.view.o1(v10_11, v4_9);
            }
            v3_19.e(0);
            if (this.B != null) {
                com.android.billingclient.api.c0 v10_14 = Wy.h.getSimpleName();
                String v3_22 = this.B;
                if (v3_22 == null) {
                    v3_22 = 0;
                }
                if (!kotlin.jvm.internal.Intrinsics.areEqual(v10_14, v3_22.w(0).getClass().getSimpleName())) {
                    com.android.billingclient.api.c0 v10_16 = this.B;
                    if (v10_16 != null) {
                        v1_0 = v10_16;
                    }
                    v1_0.F(0);
                }
            }
            String v3_27 = new com.android.billingclient.api.c0(this, ((android.widget.FrameLayout) this.findViewById(2131362960)), this.getSupportFragmentManager());
            this.B = v3_27;
            v3_27.l(0);
            com.android.billingclient.api.c0 v10_22 = ((androidx.compose.ui.platform.ComposeView) this.findViewById(2131362963));
            v10_22.setViewCompositionStrategy(androidx.compose.ui.platform.e1.c);
            v10_22.setContent(new y0.b(new MO.b(this, 0), 1, -899995628));
            this.q1(0);
            this.o1(this.getIntent());
            return;
        } else {
            return;
        }
    }

    public final void onDestroy()
    {
        ck.a v0_0 = this.n;
        if ((v0_0 != null) && (v0_0.e(this))) {
            v0_0.p(this);
        }
        if (this.p) {
            ck.a v0_1 = this.u;
            if (v0_1 == null) {
                v0_1 = 0;
            }
            ((WO.j) v0_1.get()).e0();
            ck.a v0_4 = this.v;
            if (v0_4 == null) {
                v0_4 = 0;
            }
            ((RE.c) v0_4.get()).e0();
            ck.a v0_7 = this.u;
            if (v0_7 == null) {
                v0_7 = 0;
            }
            ((WO.j) v0_7.get()).e0();
            this.D.removeCallbacksAndMessages(0);
            if ((this.isFinishing()) && (!this.isChangingConfigurations())) {
                ck.a v0_13 = p9.F4.c;
                if (v0_13 != null) {
                    v0_13.cancel(0);
                }
                p9.F4.c = 0;
                p9.F4.b = 0;
                Object[] v1_2 = new Object[0];
                ck.b.d(wmoi.igob.ggcn(4591)).f(wmoi.igob.ggcn(4592), v1_2);
            }
            super.onDestroy();
            return;
        } else {
            super.onDestroy();
            return;
        }
    }

    public final void onNewIntent(android.content.Intent p1)
    {
        super.onNewIntent(p1);
        this.o1(p1);
        return;
    }

    public final void onPause()
    {
        RE.c v0_0 = this.u;
        int v1_0 = 0;
        if (v0_0 == null) {
            v0_0 = 0;
        }
        ((WO.j) v0_0.get()).getClass();
        RE.c v0_5 = this.v;
        if (v0_5 != null) {
            v1_0 = v0_5;
        }
        ((RE.c) v1_0.get()).m = 0;
        super.onPause();
        return;
    }

    public final void onResourceReady(eu.h p1)
    {
        return;
    }

    public final void onResume()
    {
        super.onResume();
        vn.com.vng.zalopay.AndroidApplication v7 = 0;
        kotlinx.coroutines.BuildersKt.launch$default(androidx.lifecycle.l0.i(this.getLifecycle()), kotlinx.coroutines.Dispatchers.getIO(), 0, new MO.h(this, 0, 1), 2, 0);
        android.app.Activity v0_5 = this.u;
        if (v0_5 == null) {
            v0_5 = 0;
        }
        android.app.Activity v0_8 = ((WO.j) v0_5.get());
        vn.com.vng.zalopay.navigation.b v1_13 = v0_8.b;
        if ((v1_13 == null) || (!q9.Z.a(((ZO.d) v1_13).getActivity(), v0_8.p))) {
            v0_8.i.h.start();
            vn.com.vng.zalopay.navigation.b v1_23 = v0_8.k.c();
            if (v1_23 != null) {
                DZ.x v3_3 = v0_8.b;
                if ((v3_3 != null) && ((((ZO.d) v3_3).getActivity() != null) && (!((ZO.d) v0_8.b).getActivity().isFinishing()))) {
                    DZ.x v3_11 = new DZ.x(((ZO.d) v0_8.b).getActivity(), v1_23.c, 2132018254);
                    v3_11.d(v1_23.a);
                    v3_11.f(v1_23.b);
                    v3_11.show();
                }
            }
        } else {
            new vn.com.vng.zalopay.authentication.secret.a();
            try {
                vn.com.vng.zalopay.navigation.b v1_18 = vn.com.vng.zalopay.authentication.secret.a.b;
            } catch (vn.com.vng.zalopay.exception.KeyInvalidatedException) {
                ((ZO.d) v0_8.b).o();
            }
            if (v1_18 == null) {
            } else {
                v1_18.b(1, wmoi.igob.ggcn(4593));
            }
        }
        android.app.Activity v0_16 = this.v;
        if (v0_16 == null) {
            v0_16 = 0;
        }
        android.app.Activity v0_19 = ((RE.c) v0_16.get());
        v0_19.m = 1;
        vn.com.vng.zalopay.navigation.b v1_24 = v0_19.j;
        if (v1_24 != null) {
            v0_19.verifyPinToPayOrderMerchant(v1_24);
            v0_19.j = 0;
        }
        android.app.Activity v0_20 = this.u;
        if (v0_20 == null) {
            v0_20 = 0;
        }
        android.app.Activity v0_23 = ((WO.j) v0_20.get()).j;
        if (v0_23 != null) {
            v0_23.a = 4;
        }
        android.app.Activity v0_24 = this.u;
        if (v0_24 == null) {
            v0_24 = 0;
        }
        android.app.Activity v0_1 = ((WO.j) v0_24.get());
        vn.com.vng.zalopay.navigation.b v1_0 = v0_1.b;
        if ((v1_0 != null) && ((((ZO.d) v1_0).getActivity() != null) && (v0_1.p.w()))) {
            vn.com.vng.zalopay.navigation.b v1_5 = vn.com.vng.zalopay.AndroidApplication.g;
            if (v1_5 == null) {
                v1_5 = 0;
            }
            if (v1_5.a.j().a == 1) {
                vn.com.vng.zalopay.navigation.b v1_9 = vn.com.vng.zalopay.AndroidApplication.g;
                if (v1_9 != null) {
                    v7 = v1_9;
                }
                v7.a.j().a = 2;
                v0_1.c.d(((ZO.d) v0_1.b).getActivity());
            }
        }
        return;
    }

    public final void onStart()
    {
        super.onStart();
        org.greenrobot.eventbus.e v0 = this.n;
        if ((v0 != null) && (!v0.e(this))) {
            v0.k(this);
        }
        return;
    }

    public final void onStop()
    {
        Um.c v0_0 = this.u;
        if (v0_0 == null) {
            v0_0 = 0;
        }
        Um.c v0_3 = ((WO.j) v0_0.get()).j;
        if (v0_3 != null) {
            v0_3.a = 3;
        }
        super.onStop();
        return;
    }

    public final void p1(int p3)
    {
        dagger.e v1 = 0;
        if ((this.C != p3) && (super.m1() != null)) {
            int v0_7 = this.B;
            if (v0_7 == 0) {
                v0_7 = 0;
            }
            v0_7.l(p3);
            if (p3 == null) {
                UR.a.c(11306, 0, 0, 0);
            } else {
                if (p3 == 1) {
                    UR.a.c(11307, 0, 0, 0);
                } else {
                    if (p3 == 3) {
                        UR.a.c(11308, 0, 0, 0);
                    }
                }
            }
        }
        this.C = p3;
        WO.j v3_1 = this.u;
        if (v3_1 != null) {
            v1 = v3_1;
        }
        ((WO.j) v1.get()).getClass();
        return;
    }

    public final void q1(boolean p4)
    {
        android.view.View v0_0 = this.getWindow();
        if (v0_0 != null) {
            if (android.os.Build$VERSION.SDK_INT < 30) {
                int v4_1;
                android.view.View v0_2 = v0_0.getDecorView();
                int v1_2 = v0_2.getSystemUiVisibility();
                if (p4 == 0) {
                    v4_1 = (v1_2 | 8192);
                } else {
                    v4_1 = (v1_2 & -8193);
                }
                v0_2.setSystemUiVisibility(v4_1);
                return;
            } else {
                android.view.View v0_1 = v0_0.getInsetsController();
                if (v0_1 != null) {
                    if (p4 == 0) {
                        v0_1.setSystemBarsAppearance(8, 8);
                    } else {
                        v0_1.setSystemBarsAppearance(0, 8);
                        return;
                    }
                }
            }
        }
        return;
    }

    public final void r(String p2)
    {
        m9.B.e(this, p2, 0);
        return;
    }

    public final void r1()
    {
        WQ.s v0_1 = this.getString(2131954506);
        int v2_3 = this.w;
        if (v2_3 == 0) {
            v2_3 = 0;
        }
        int v2_1;
        int v2_5 = ((Pt.n) v2_3.get());
        if ((new N4.b(this, 6).g()) && (v2_5 != 0)) {
            v2_1 = (q9.Z.a(this, v2_5) ^ 1);
        } else {
            v2_1 = 0;
        }
        OH.g v1_0 = new OH.g(this, v0_1, v2_1);
        v1_0.d = new WQ.s(this, v1_0, 3);
        v1_0.a();
        return;
    }

    public final void s1(int p11)
    {
        if (p11 == null) {
            this.q1(0);
            this.p1(0);
            return;
        } else {
            if (p11 == 1) {
                HF.b.p(this, 0);
                this.q1(1);
                this.p1(1);
                return;
            } else {
                Boolean v3 = 0;
                if (p11 == 2) {
                    kotlinx.coroutines.BuildersKt.launch$default(androidx.lifecycle.l0.i(this.getLifecycle()), 0, 0, new MO.h(this, 0, 0), 3, 0);
                    return;
                } else {
                    if (p11 == 3) {
                        HF.b.p(this, 0);
                        this.q1(1);
                        this.p1(2);
                        return;
                    } else {
                        if (p11 == 4) {
                            HF.b.p(this, 0);
                            this.q1(1);
                            if ((this.C != 3) && (super.m1() != null)) {
                                Boolean v11_6;
                                Boolean v11_5 = vC.q.a();
                                if (v11_5 != null) {
                                    boolean v0_1 = vC.q.d;
                                    if (v0_1) {
                                        if (!kotlin.jvm.internal.Intrinsics.areEqual(v0_1.a, v11_5)) {
                                            v0_1 = 0;
                                        }
                                        if (v0_1) {
                                            v11_6 = Boolean.valueOf(v0_1.b);
                                            if (v11_6 != null) {
                                                Boolean v11_8 = v11_6.booleanValue();
                                                boolean v0_4 = vC.q.b();
                                                if ((v0_4) && (v11_8 != v0_4.booleanValue())) {
                                                    Boolean v11_9 = this.B;
                                                    if (v11_9 != null) {
                                                        v3 = v11_9;
                                                    }
                                                    v3.F(3);
                                                }
                                            }
                                            this.p1(3);
                                            return;
                                        }
                                    }
                                    v11_6 = vC.q.c(wmoi.igob.ggcn(4594), v11_5);
                                } else {
                                    v11_6 = 0;
                                }
                            }
                            this.p1(3);
                            return;
                        } else {
                            return;
                        }
                    }
                }
            }
        }
    }

    public final void showLoading()
    {
        if (!this.isFinishing()) {
            DZ.x v0_1;
            DZ.x v0_8 = m9.B.a;
            if ((v0_8 == null) || (!v0_8.isShowing())) {
                v0_1 = 0;
            } else {
                v0_1 = 1;
            }
            if (v0_1 != null) {
                if (m9.B.a.getOwnerActivity() != this) {
                    m9.B.a();
                } else {
                    return;
                }
            }
            DZ.x v0_5 = new DZ.x(this, 5, 2132018255);
            m9.B.a = v0_5;
            v0_5.setOwnerActivity(this);
            m9.B.a.setCancelable(0);
            m9.B.a.setOnKeyListener(new DZ.t(3, this));
            if (!this.isFinishing()) {
                m9.B.a.show();
            }
        } else {
        }
        return;
    }

    public final ji.d0 u0()
    {
        ji.d0 v0_0 = this.y;
        if (v0_0 == null) {
            v0_0 = 0;
        }
        return ((ji.d0) v0_0.f);
    }

    public final void v()
    {
        m9.p.f(this);
        return;
    }
}
