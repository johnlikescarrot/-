package vn.com.vng.zalopay.homev5.presenter.ui.home;
public final class HomeActivity extends vn.com.vng.zalopay.homev5.presenter.base.BaseActivity {
    public static final synthetic int z;
    public final androidx.lifecycle.r0 i;
    public final pz.c j;
    public final pz.d k;
    public final pz.b l;
    public final pz.e m;
    public final pz.a n;
    public final KG.a o;
    public final org.greenrobot.eventbus.e p;
    public final dagger.e q;
    public final O5.b r;
    public final O5.b s;
    public final ng.S t;
    public final ng.S u;
    public boolean v;
    public boolean w;
    public final ng.S x;
    public String y;

    public HomeActivity()
    {
        androidx.camera.camera2.impl.a v2_3 = kotlin.jvm.internal.M.a;
        this.r = new O5.b(v2_3.b(CA.Z), new CA.r(this, 0), new CA.a(this, 0), new CA.r(this, 1));
        this.s = new O5.b(v2_3.b(DA.n), new CA.r(this, 2), new CA.a(this, 2), new CA.r(this, 3));
        this.t = ng.x.b(new CA.a(this, 8));
        this.u = ng.x.b(new CA.a(this, 9));
        this.x = ng.x.b(new AB.a(7));
        aA.a v0_15 = com.google.android.gms.internal.mlkit_vision_barcode.i6.b;
        if (v0_15 == null) {
            aA.a v0_16 = com.google.android.gms.internal.mlkit_vision_barcode.i6.a;
            if (v0_16 == null) {
                throw new IllegalStateException("envComponent must be initialized first by calling HomeV5Plugin.install()");
            } else {
                String v1_12 = new XQ.i(9, v0_16);
                aA.a v0_19 = com.google.android.gms.internal.mlkit_vision_barcode.i6.c;
                if (v0_19 == null) {
                    v0_19 = gi.N.d();
                    com.google.android.gms.internal.mlkit_vision_barcode.i6.c = v0_19;
                }
                aA.a v3_6 = new aA.a(v1_12, new androidx.camera.camera2.impl.a(7, gi.L.a(v0_19)), new kX.d());
                com.google.android.gms.internal.mlkit_vision_barcode.i6.b = v3_6;
                v0_15 = v3_6;
            }
        }
        this.i = ((androidx.lifecycle.r0) v0_15.K.get());
        String v1_18 = ((aA.c) v0_15.a.b);
        androidx.camera.camera2.impl.a v2_7 = v1_18.F();
        p9.n7.c(v2_7);
        this.j = v2_7;
        androidx.camera.camera2.impl.a v2_8 = v1_18.w();
        p9.n7.c(v2_8);
        this.k = v2_8;
        androidx.camera.camera2.impl.a v2_9 = v1_18.x();
        p9.n7.c(v2_9);
        this.l = v2_9;
        androidx.camera.camera2.impl.a v2_10 = v1_18.z();
        p9.n7.c(v2_10);
        this.m = v2_10;
        androidx.camera.camera2.impl.a v2_0 = v1_18.B();
        p9.n7.c(v2_0);
        this.n = v2_0;
        androidx.camera.camera2.impl.a v2_1 = v1_18.b();
        p9.n7.c(v2_1);
        this.o = v2_1;
        this.p = v1_18.r();
        this.q = dagger.internal.f.a(v0_15.M);
        return;
    }

    public static final void i1(vn.com.vng.zalopay.homev5.presenter.ui.home.HomeActivity p7, String p8, boolean p9, kotlin.jvm.functions.Function0 p10)
    {
        java.util.Iterator v9_1;
        androidx.lifecycle.w v0_0 = p7.getSupportFragmentManager();
        androidx.fragment.app.a v1 = androidx.activity.result.e.d(v0_0, v0_0);
        v1.p = 1;
        int v3_0 = v0_0.E(p8);
        if (v3_0 == 0) {
            v9_1 = 0;
        } else {
            if (p9 == null) {
                v9_1 = 0;
            } else {
                v9_1 = v3_0;
            }
            if (v9_1 == null) {
            } else {
                v1.l(v9_1);
            }
        }
        if (v3_0 == 0) {
            v3_0 = ((androidx.fragment.app.Fragment) p10.invoke());
            v1.i(2131363785, v3_0, p8, 1);
        } else {
            if (v3_0 == v9_1) {
                v3_0 = 0;
            }
            if (v3_0 == 0) {
            }
        }
        androidx.fragment.app.Fragment v10_4 = ((Iterable) v0_0.c.f());
        androidx.lifecycle.w v0_2 = new java.util.ArrayList();
        androidx.fragment.app.Fragment v10_5 = v10_4.iterator();
        while (v10_5.hasNext()) {
            Object v2_2 = v10_5.next();
            androidx.fragment.app.Fragment v4_1 = ((androidx.fragment.app.Fragment) v2_2);
            if ((v4_1.isAdded()) && ((v4_1.getId() == 2131363785) && (v4_1 != v9_1))) {
                v0_2.add(v2_2);
            }
        }
        java.util.Iterator v9_2 = v0_2.iterator();
        while (v9_2.hasNext()) {
            androidx.fragment.app.Fragment v10_8 = ((androidx.fragment.app.Fragment) v9_2.next());
            if (v10_8 != v3_0) {
                v1.k(v10_8);
                v1.o(v10_8, androidx.lifecycle.w.d);
            } else {
                v1.q(v10_8);
                v1.o(v10_8, androidx.lifecycle.w.e);
                v1.p(v10_8);
            }
        }
        v1.f();
        p7.y = p8;
        return;
    }

    public final DA.n e1()
    {
        return ((DA.n) this.s.getValue());
    }

    public final CA.Z g1()
    {
        return ((CA.Z) this.r.getValue());
    }

    public final void h1(MA.l p6)
    {
        int v1 = 0;
        if (!(p6 instanceof MA.i)) {
            if (!(p6 instanceof MA.j)) {
                if (!kotlin.jvm.internal.Intrinsics.areEqual(p6, MA.k.a)) {
                    throw new kotlin.NoWhenBranchMatchedException();
                } else {
                    if (!kotlin.jvm.internal.Intrinsics.areEqual(this.y, "PersonalPage")) {
                        boolean v6_4 = this.k;
                        if (!v6_4) {
                            v6_4 = 0;
                        }
                        if (v6_4.a()) {
                            v1 = 1;
                        }
                    }
                    vn.com.vng.zalopay.homev5.presenter.ui.home.HomeActivity.i1(this, "PersonalPage", v1, new CA.a(this, 11));
                    return;
                }
            } else {
                String v0_5 = ((MA.j) p6).a;
                vn.com.vng.zalopay.homev5.presenter.ui.home.HomeActivity.i1(this, androidx.camera.camera2.internal.G.e("MiniAppWithId", v0_5), 0, new CA.e(this, p6, v0_5, 1));
                return;
            }
        } else {
            vn.com.vng.zalopay.homev5.presenter.ui.home.HomeActivity.i1(this, "HomePage", 0, new CA.a(this, 10));
            return;
        }
    }

    public final void onCreate(android.os.Bundle p12)
    {
        android.content.Intent v12_3;
        super.onCreate(p12);
        if (p12 == null) {
            v12_3 = 0;
        } else {
            v12_3 = p12.getBoolean("key_consumed_migrate_to_new_home_gateway", 0);
        }
        this.v = v12_3;
        android.content.Intent v12_1 = 0;
        androidx.activity.q.a(this, 0, 3);
        if ((!this.v) && (((Boolean) this.t.getValue()).booleanValue())) {
            this.v = 1;
            this.g1().o0(CA.N.a);
        }
        this.setContentView(2131558462);
        CA.Z v2_15 = this.u;
        this.w = ((Boolean) v2_15.getValue()).booleanValue();
        if (((Boolean) v2_15.getValue()).booleanValue()) {
            this.g1().o0(CA.M.a);
        }
        n9.s4.a(this.getOnBackPressedDispatcher(), this, new Am.b(1, this));
        kotlinx.coroutines.BuildersKt.launch$default(androidx.lifecycle.l0.i(this.getLifecycle()), 0, 0, new CA.k(this, 0, 2), 3, 0);
        kotlinx.coroutines.BuildersKt.launch$default(androidx.lifecycle.l0.i(this.getLifecycle()), 0, 0, new CA.k(this, 0, 1), 3, 0);
        kotlinx.coroutines.BuildersKt.launch$default(androidx.lifecycle.l0.i(this.getLifecycle()), 0, 0, new CA.k(this, 0, 3), 3, 0);
        String v1_22 = this.findViewById(2131364105);
        Class v4_11 = android.os.Build$VERSION.SDK_INT;
        if (v4_11 < 30) {
            String v5_18 = new androidx.core.view.z0();
            v1_22.setTag(2131364359, v5_18);
            v1_22.setOnApplyWindowInsetsListener(v5_18);
            androidx.core.view.A0.b = 1;
        }
        String v1_25 = this.findViewById(2131363785);
        androidx.core.view.h0.l(v1_25, new hJ.g(v1_25));
        if (!v1_25.isAttachedToWindow()) {
            v1_25.addOnAttachStateChangeListener(new nY.e());
        } else {
            v1_25.requestApplyInsets();
        }
        CA.Z v2_5;
        this.h1(new MA.i(0));
        String v1_4 = ((androidx.compose.ui.platform.ComposeView) this.findViewById(2131362445));
        v1_4.setContent(new y0.b(new CA.b(this, v1_4, 0), 1, -496975766));
        kotlinx.coroutines.BuildersKt.launch$default(androidx.lifecycle.l0.i(this.getLifecycle()), 0, 0, new CA.k(this, 0, 5), 3, 0);
        String v1_9 = ((androidx.compose.ui.platform.ComposeView) this.findViewById(2131362442));
        v1_9.setContent(new y0.b(new CA.b(this, v1_9, 4), 1, -1103759703));
        String v1_12 = ((androidx.compose.ui.platform.ComposeView) this.findViewById(2131362444));
        v1_12.setContent(new y0.b(new CA.b(this, v1_12, 1), 1, 1494414413));
        String v1_15 = ((androidx.compose.ui.platform.ComposeView) this.findViewById(2131362443));
        v1_15.setContent(new y0.b(new CA.b(this, v1_15, 2), 1, 3796351));
        String v1_16 = this.p;
        if (v1_16 == null) {
            v2_5 = 0;
        } else {
            v2_5 = v1_16;
        }
        v2_5.k(this);
        CA.Z v2_6 = this.m;
        if (v2_6 == null) {
            v2_6 = 0;
        }
        v2_6.b();
        if (v4_11 >= 33) {
            String v0_1 = new String[0];
            com.google.android.gms.internal.mlkit_vision_barcode.h5.e(this, "android.permission.POST_NOTIFICATIONS", v0_1).g().b();
        }
        String v0_4 = this.n;
        if (v0_4 == null) {
            v0_4 = 0;
        }
        if (v1_16 != null) {
            v12_1 = v1_16;
        }
        v0_4.a(v12_1, new AA.z(1, this, vn.com.vng.zalopay.homev5.presenter.ui.home.HomeActivity, "onAddCalendarEventEvent", "onAddCalendarEventEvent(Lvn/com/zalopay/calendar/model/Event;)V", 0, 14));
        android.content.Intent v12_2 = this.getIntent();
        String v1_19 = v12_2.getStringExtra("tab");
        if (v1_19 != null) {
            this.g1().o0(new CA.L(v1_19));
            v12_2.removeExtra("tab");
        }
        return;
    }

    public final void onDeepLinkEvent(ex.c p5)
    {
        KG.a v0 = 0;
        KG.a v1_0 = this.p;
        if (v1_0 == null) {
            v1_0 = 0;
        }
        v1_0.n(kotlin.jvm.internal.M.a.b(ex.c));
        KG.a v1_1 = this.o;
        if (v1_1 != null) {
            v0 = v1_1;
        }
        v0.o(this, p5.a);
        return;
    }

    public final void onDestroy()
    {
        super.onDestroy();
        Object[] v1_3 = this.p;
        if (v1_3 == null) {
            v1_3 = 0;
        }
        v1_3.p(this);
        Object[] v1_7 = this.m;
        if (v1_7 == null) {
            v1_7 = 0;
        }
        v1_7.c();
        Object[] v1_0 = this.n;
        if (v1_0 == null) {
            v1_0 = 0;
        }
        v1_0.unregister();
        if ((this.isFinishing()) && (!this.isChangingConfigurations())) {
            Object[] v1_4 = com.google.android.gms.internal.mlkit_vision_barcode.i6.c;
            if (v1_4 != null) {
                v1_4.cancel(0);
            }
            com.google.android.gms.internal.mlkit_vision_barcode.i6.c = 0;
            com.google.android.gms.internal.mlkit_vision_barcode.i6.b = 0;
            Object[] v1_6 = new Object[0];
            ck.b.d("HomeActivity").f("Released HomeV5Plugin", v1_6);
        }
        return;
    }

    public final void onNetworkChange(YG.a p2)
    {
        if (p2.a) {
            this.g1().o0(CA.K.a);
        }
        return;
    }

    public final void onNewIntent(android.content.Intent p5)
    {
        super.onNewIntent(p5);
        String v1 = p5.getStringExtra("tab");
        if (v1 != null) {
            this.g1().o0(new CA.L(v1));
            p5.removeExtra("tab");
        }
        return;
    }

    public final void onSaveInstanceState(android.os.Bundle p3)
    {
        p3.putBoolean("key_consumed_migrate_to_new_home_gateway", this.v);
        super.onSaveInstanceState(p3);
        return;
    }

    public final void onUserSessionExpiredEvent(eu.e p3)
    {
        pz.e v0 = 0;
        pz.e v1_0 = this.p;
        if (v1_0 == null) {
            v1_0 = 0;
        }
        v1_0.l();
        pz.e v1_1 = this.m;
        if (v1_1 != null) {
            v0 = v1_1;
        }
        v0.a(p3);
        return;
    }
}
