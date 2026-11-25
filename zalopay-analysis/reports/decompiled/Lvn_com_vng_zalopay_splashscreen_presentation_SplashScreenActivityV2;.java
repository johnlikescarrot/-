package vn.com.vng.zalopay.splashscreen.presentation;
public class SplashScreenActivityV2 extends androidx.appcompat.app.AppCompatActivity implements DZ.g$a, DH.c {
    public static final synthetic int z;
    public boolean i;
    public final kf.a j;
    public final com.google.firebase.perf.metrics.Trace k;
    public vn.com.vng.zalopay.navigation.b l;
    public Yv.b m;
    public xn.b n;
    public um.d o;
    public um.a p;
    public org.greenrobot.eventbus.e q;
    public Ns.g r;
    public aO.e s;
    public final O5.b t;
    public int u;
    public at.W v;
    public QN.d w;
    public boolean x;
    public String y;

    public SplashScreenActivityV2()
    {
        this.j = new kf.a(0);
        Pb.b.a().getClass();
        this.k = Pb.b.b("SplashScreen");
        this.t = new O5.b(kotlin.jvm.internal.M.a.b(aO.d), new YN.d(this, 0), new TT.a(20, this), new YN.d(this, 1));
        this.u = 1;
        this.v = at.W.d;
        this.w = new QN.b(0);
        return;
    }

    public static void e1(vn.com.vng.zalopay.splashscreen.presentation.SplashScreenActivityV2 p23, android.os.Bundle p24)
    {
        gi.F v3_0;
        p23.k.start();
        androidx.activity.q.a(p23, 0, 3);
        if (android.os.Build$VERSION.SDK_INT < 31) {
            v3_0 = new XQ.i(23, p23);
        } else {
            v3_0 = new h2.b(p23);
        }
        v3_0.D();
        gi.F v3_7 = ((CC.f) q9.a5.a());
        p23.l = ((vn.com.vng.zalopay.navigation.b) v3_7.e0.get());
        p23.m = v3_7.K();
        p23.n = v3_7.g();
        p23.o = ((um.d) v3_7.j0.get());
        p23.p = ((um.a) v3_7.e1.get());
        v3_7.d1.get();
        p23.q = ((org.greenrobot.eventbus.e) v3_7.g.get());
        p23.r = ((Ns.g) v3_7.k.get());
        int v6_14 = ((Um.c) v3_7.y0.get());
        int v7_17 = ((Pt.n) v3_7.N.get());
        java.util.concurrent.atomic.AtomicBoolean v8_11 = ((Ju.b) v3_7.X.get());
        kc.h v9_13 = ((org.greenrobot.eventbus.e) v3_7.g.get());
        dagger.internal.o v10_16 = ((St.a) v3_7.I.get());
        Xv.c v11_13 = ((Xv.b) v3_7.f1.get());
        int v12_7 = new AW.a(26, ((org.greenrobot.eventbus.e) v3_7.g.get()));
        gX.j v13_5 = new uc.i(29, ((Xt.b) v3_7.g1.get()));
        wx.a v14_0 = new Ae.r(24, ((org.greenrobot.eventbus.e) v3_7.g.get()));
        ou.e v15_3 = new l9.w();
        v3_7.g.get();
        p23.s = new aO.e(v6_14, v7_17, v8_11, v9_13, v10_16, v11_13, v12_7, v13_5, v14_0, v15_3, new m6.t(16), new lc.a(), ((Xt.b) v3_7.g1.get()), ((nY.p) v3_7.v.get()), v3_7.q(), new la.f(), new c6.a(16, ((Lw.a) v3_7.m1.get())));
        super.onCreate(p24);
        k.e.a(p23, YN.a.a);
        androidx.lifecycle.A v2_4 = p23.getIntent().getIntExtra("on_app_from_source", 0);
        at.W.b.getClass();
        p23.v = D5.f.d(v2_4);
        kotlinx.coroutines.BuildersKt.launch$default(androidx.lifecycle.l0.i(p23.getLifecycle()), 0, 0, new YN.c(p23, 0, 1), 3, 0);
        if (p23.isTaskRoot()) {
            kotlinx.coroutines.BuildersKt.launch$default(androidx.lifecycle.l0.i(p23.getLifecycle()), kotlinx.coroutines.Dispatchers.getIO(), 0, new YN.c(p23, 0, 0), 2, 0);
        }
        if (!p23.getIntent().hasExtra("splash_screen_data")) {
            androidx.lifecycle.A v2_13;
            androidx.lifecycle.A v2_11 = p23.getIntent();
            if (((v2_11.getFlags() & 1048576) != 1048576) && (!kotlin.jvm.internal.Intrinsics.areEqual(v2_11.getAction(), "android.intent.action.MAIN"))) {
                if (!v2_11.hasExtra("notification_source")) {
                    gi.F v3_15 = v2_11.getData();
                    androidx.lifecycle.A v2_12 = v2_11.getAction();
                    if (v2_12 == null) {
                        v2_12 = "";
                    }
                    v2_13 = new QN.a(v3_15, v2_12);
                } else {
                    v2_13 = QN.c.a;
                }
            } else {
                v2_13 = new QN.b(0);
            }
            androidx.lifecycle.A v2_19;
            p23.w = v2_13;
            kotlinx.coroutines.BuildersKt.launch$default(androidx.lifecycle.l0.i(p23.getLifecycle()), kotlinx.coroutines.Dispatchers.getIO(), 0, new YN.c(p23, 0, 3), 2, 0);
            androidx.core.view.h0.l(p23.getWindow().getDecorView(), new MO.j(28, p23));
            if (p24 == null) {
                v2_19 = 0;
            } else {
                v2_19 = 1;
            }
            p23.x = v2_19;
            p23.y = p23.getCallingPackage();
            kotlinx.coroutines.BuildersKt.launch$default(androidx.lifecycle.l0.i(p23.getLifecycle()), kotlinx.coroutines.Dispatchers.getIO(), 0, new YN.c(p23, 0, 2), 2, 0);
            if (p23.isTaskRoot()) {
                com.google.android.gms.internal.mlkit_vision_barcode.W6.d = new androidx.core.app.e(28, q9.a5.a());
                androidx.lifecycle.A v2_27 = new com.google.android.gms.internal.ads.rp();
                gi.F v3_20 = p23.getApplication();
                int v7_11 = kotlin.collections.y.toSet(new String[] {vn.com.vng.zalopay.ui.activity.HomeActivity.getName(), vn.com.vng.zalopay.homev5.presenter.ui.home.HomeActivity.getName(), "vn.com.vng.zalopay.loginv2.presentation.ui.login.LoginActivity"}));
                java.util.concurrent.atomic.AtomicBoolean v8_7 = ((java.util.concurrent.atomic.AtomicBoolean) v2_27.c);
                if (v8_7.compareAndSet(0, 1)) {
                    gX.j v13_2;
                    kc.h v9_8 = com.google.android.gms.internal.mlkit_vision_barcode.W6.d;
                    if (v9_8 != null) {
                        dagger.internal.o v10_6 = new rv.b(13, v9_8);
                        kc.h v9_10 = new kc.h(8);
                        int v12_3 = new kc.g(8);
                        Xv.c v11_8 = new kc.f();
                        v13_2 = new gX.j();
                        v13_2.a = dagger.internal.f.b(new ou.e(v12_3, new wx.a(v10_6, 1), 25));
                        int v6_7 = dagger.internal.f.b(new ou.e(v11_8, new wx.a(v10_6, 0), 24));
                        v13_2.b = v6_7;
                        v13_2.c = dagger.internal.f.b(new Xv.c(v9_10, ((dagger.internal.o) v13_2.a), v6_7, 26));
                    } else {
                        v13_2 = 0;
                    }
                    if (v13_2 != null) {
                        v2_27.b = ((yx.a) ((dagger.internal.o) v13_2.c).get());
                        v2_27.f = v3_20;
                        v2_27.i = v7_11;
                        rx.a v4_19 = new rx.a(v2_27);
                        v2_27.g = v4_19;
                        v3_20.registerActivityLifecycleCallbacks(v4_19);
                    } else {
                        v8_7.set(0);
                    }
                }
                p9.C5.a(p23);
                androidx.lifecycle.A v2_29 = new androidx.work.f();
                gi.F v3_22 = new androidx.work.d();
                v3_22.a = 1;
                v3_22.f = -1;
                v3_22.g = -1;
                new java.util.HashSet();
                v3_22.b = 0;
                v3_22.c = 0;
                v3_22.a = 2;
                v3_22.d = 0;
                v3_22.e = 0;
                v3_22.h = v2_29;
                v3_22.f = -1;
                v3_22.g = -1;
                V3.l.e(p23).a("first_tracking_vitals", 1, ((androidx.work.u) ((androidx.work.t) new androidx.work.t(vn.com.vng.zalopay.splashscreen.worker_manager.FirstTrackingVitalsWorker).e(v3_22)).b()));
                kotlinx.coroutines.BuildersKt.launch$default(androidx.lifecycle.l0.i(p23.getLifecycle()), kotlinx.coroutines.Dispatchers.getIO(), 0, new CQ.a(2, 5, 0), 2, 0);
                if (kotlin.jvm.internal.Intrinsics.areEqual(p23.getPackageName(), "vn.com.vng.zalopay")) {
                    kotlinx.coroutines.BuildersKt.launch$default(androidx.lifecycle.l0.i(p23.getLifecycle()), kotlinx.coroutines.Dispatchers.getIO(), 0, new YN.c(p23, 0, 4), 2, 0);
                }
            }
            return;
        } else {
            androidx.lifecycle.A v2_43;
            androidx.lifecycle.A v2_41 = p23.getIntent().getExtras();
            if (v2_41 == null) {
                v2_43 = p23.w;
            } else {
                v2_43 = ((QN.d) p9.M.a(v2_41, "splash_screen_data", QN.d));
                if (v2_43 == null) {
                }
            }
            p23.w = v2_43;
            if ((v2_43 instanceof QN.b)) {
                p23.w = new QN.b(1);
            }
            kotlinx.coroutines.BuildersKt.launch$default(androidx.lifecycle.l0.i(p23.getLifecycle()), kotlinx.coroutines.Dispatchers.getIO(), 0, new YN.c(p23, 0, 3), 2, 0);
            p23.h1();
            kotlinx.coroutines.BuildersKt.launch$default(androidx.lifecycle.l0.i(p23.getLifecycle()), kotlinx.coroutines.Dispatchers.getIO(), 0, new CQ.a(2, 6, 0), 2, 0);
            return;
        }
    }

    public static void j1(vn.com.vng.zalopay.splashscreen.presentation.SplashScreenActivityV2 p3)
    {
        p3.i = 1;
        int v1 = p3.l;
        if (v1 == 0) {
            v1 = 0;
        }
        v1.c(p3, 0, 1);
        p3.overridePendingTransition(0, 0);
        p3.k1();
        return;
    }

    public final void M(String p3)
    {
        int v0 = this.l;
        if (v0 == 0) {
            v0 = 0;
        }
        v0.j(this, 1, p3);
        return;
    }

    public final void X0(android.net.Uri p3)
    {
        android.content.Intent v0_0 = this.l;
        if (v0_0 == null) {
            v0_0 = 0;
        }
        v0_0.getClass();
        android.content.Intent v0_2 = new android.content.Intent("android.intent.action.VIEW");
        v0_2.setData(p3);
        this.startActivity(v0_2);
        return;
    }

    public final void a(int p2)
    {
        if (p2 == 1500) {
            new vn.com.vng.zalopay.authentication.secret.a().c();
        }
        return;
    }

    public final void c(int p4)
    {
        if (p4 == 1500) {
            new vn.com.vng.zalopay.authentication.d(this, this.getString(2131953384), 0, new MO.d(2, this)).a();
        }
        return;
    }

    public final void g1()
    {
        if (!this.isTaskRoot()) {
            android.content.Intent v0_2 = this.getPackageManager().getLaunchIntentForPackage(this.getPackageName());
            if (v0_2 == null) {
                vn.com.vng.zalopay.splashscreen.presentation.SplashScreenActivityV2.j1(this);
                return;
            } else {
                this.startActivity(v0_2);
                this.k1();
                return;
            }
        } else {
            vn.com.vng.zalopay.splashscreen.presentation.SplashScreenActivityV2.j1(this);
            return;
        }
    }

    public final void h(String p8)
    {
        if (com.google.android.gms.internal.mlkit_vision_barcode.l0.c(p8)) {
            int v2;
            android.net.Uri v4 = android.net.Uri.parse(p8);
            int v8_2 = this.m;
            if (v8_2 == 0) {
                v2 = 0;
            } else {
                v2 = v8_2;
            }
            kotlinx.coroutines.BuildersKt.runBlocking$default(0, new b0.x(v2, this, v4, 0, 10), 1, 0);
            return;
        } else {
            q9.r6.b(this, p8);
            return;
        }
    }

    public final void h1()
    {
        this.i1().j0(new ZN.a(0));
        return;
    }

    public final aO.d i1()
    {
        return ((aO.d) this.t.getValue());
    }

    public final void k1()
    {
        this.k.stop();
        this.finish();
        return;
    }

    public final void l1()
    {
        String v0 = eQ.e.c(this);
        String v1_0 = this.r;
        if (v1_0 == null) {
            v1_0 = 0;
        }
        this.i1().i0(new ZN.j(this.getIntent(), v0, v1_0.c(this)));
        return;
    }

    public final void onActivityResult(int p4, int p5, android.content.Intent p6)
    {
        super.onActivityResult(p4, p5, p6);
        if (p4 != 151) {
            if ((p4 != 142) || (p5 != -1)) {
                if (p4 != 161) {
                    this.x = 0;
                    return;
                } else {
                    this.setResult(p5, p6);
                    this.k1();
                    return;
                }
            } else {
                this.i1().j0(new ZN.b(0));
                return;
            }
        } else {
            ZN.b v5_9 = new kf.b[1];
            v5_9[0] = gf.A.timer(500, java.util.concurrent.TimeUnit.MILLISECONDS).observeOn(hf.b.a()).subscribe(new MO.j(26, new YN.b(this, 0)), new MO.j(27, new VM.c(21)));
            this.j.d(v5_9);
            return;
        }
    }

    public final void onCreate(android.os.Bundle p2)
    {
        Pb.b.a().getClass();
        com.google.firebase.perf.metrics.Trace v0_2 = Pb.b.b("SplashOnCreate");
        try {
            v0_2.start();
            vn.com.vng.zalopay.splashscreen.presentation.SplashScreenActivityV2.e1(this, p2);
            v0_2.stop();
            return;
        } catch (Throwable v2_1) {
            v0_2.stop();
            throw v2_1;
        }
    }

    public final void onDestroy()
    {
        super.onDestroy();
        this.k.stop();
        this.j.e();
        return;
    }

    public final void onNewIntent(android.content.Intent p3)
    {
        super.onNewIntent(p3);
        if (this.i1().x.d() == ZN.l.a) {
            this.setIntent(p3);
            this.l1();
        }
        return;
    }

    public final void onResume()
    {
        super.onResume();
        if (this.i) {
            vn.com.vng.zalopay.splashscreen.presentation.SplashScreenActivityV2.j1(this);
        }
        if ((this.x) && (!android.text.TextUtils.isEmpty(this.y))) {
            this.k1();
        }
        return;
    }

    public final void onStart()
    {
        super.onStart();
        org.greenrobot.eventbus.e v0_0 = this.q;
        org.greenrobot.eventbus.e v1 = 0;
        if (v0_0 == null) {
            v0_0 = 0;
        }
        if (!v0_0.e(this)) {
            org.greenrobot.eventbus.e v0_2 = this.q;
            if (v0_2 != null) {
                v1 = v0_2;
            }
            v1.k(this);
        }
        return;
    }

    public final void onStop()
    {
        org.greenrobot.eventbus.e v0_0 = this.q;
        org.greenrobot.eventbus.e v1 = 0;
        if (v0_0 == null) {
            v0_0 = 0;
        }
        if (v0_0.e(this)) {
            org.greenrobot.eventbus.e v0_2 = this.q;
            if (v0_2 != null) {
                v1 = v0_2;
            }
            v1.p(this);
        }
        super.onStop();
        return;
    }

    public final void onThrowToLoginScreen(eu.f p5)
    {
        String v0_1 = new java.util.HashMap();
        v0_1.put("timestamp", Long.valueOf(System.currentTimeMillis()));
        int v1_0 = this.r;
        org.greenrobot.eventbus.e v2_0 = 0;
        if (v1_0 == 0) {
            v1_0 = 0;
        }
        v0_1.put("device_id", v1_0.c(this));
        UR.a.e("01.1729.005", v0_1);
        org.greenrobot.eventbus.e v5_1 = p5.a;
        String v0_2 = q9.g5.b(v5_1, this);
        int v1_3 = this.q;
        if (v1_3 == 0) {
            v1_3 = 0;
        }
        v1_3.i(new eu.e(v0_2, (v5_1 instanceof fu.a)));
        org.greenrobot.eventbus.e v5_3 = this.q;
        if (v5_3 == null) {
            v5_3 = 0;
        }
        if (v5_3.e(this)) {
            org.greenrobot.eventbus.e v5_5 = this.q;
            if (v5_5 != null) {
                v2_0 = v5_5;
            }
            v2_0.p(this);
        }
        return;
    }

    public final void t(android.os.Bundle p7, long p8)
    {
        eQ.a.g(new MO.i(this, p8, p7, 1), 3000);
        return;
    }
}
