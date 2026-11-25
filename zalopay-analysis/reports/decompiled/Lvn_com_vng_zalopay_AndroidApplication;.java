package vn.com.vng.zalopay;
public class AndroidApplication extends vn.com.vng.zalopay.BaseApplication {
    public static vn.com.vng.zalopay.AndroidApplication g;
    public com.google.common.collect.Q d;
    public final li.e e;
    public java.util.List f;

    public AndroidApplication()
    {
        this.e = gi.L.a(kotlin.coroutines.f.a(gi.N.d(), kotlinx.coroutines.Dispatchers.getDefault()));
        this.f = kotlin.collections.E.emptyList();
        return;
    }

    public final void f(Ew.D p12)
    {
        Pb.b.a().getClass();
        com.google.firebase.perf.metrics.Trace v1 = Pb.b.b("UserComponent");
        v1.start();
        if (p12 != null) {
            try {
                int v0_1 = this.a;
            } catch (int v0_2) {
                kotlin.Unit v12_1 = v0_2;
                v1.stop();
                throw v12_1;
            }
            if (v0_1 == 0) {
                v0_1 = 0;
            } else {
            }
            this.b = new CC.g(v0_1.d, new mP.a(p12));
            new android.os.Handler(android.os.Looper.getMainLooper()).post(new Yd.b(3, new Yx.a(this)));
            kotlinx.coroutines.BuildersKt.launch$default(this.e, kotlinx.coroutines.Dispatchers.getIO(), 0, new CS.r(2, 16, 0), 2, 0);
            this.k(this.b);
            v1.stop();
            return;
        } else {
            v1.stop();
            return;
        }
    }

    public final void g()
    {
        Pb.b.a().getClass();
        Ew.D v0_9 = Pb.b.b("AppComponent");
        v0_9.start();
        try {
            this.a = CC.a.a(this);
            v0_9.stop();
            Ew.D v0_1 = this.a;
            CC.f v1_0 = 0;
        } catch (CC.f v1_1) {
            v0_1.stop();
            throw v1_1;
        }
        if (v0_1 == null) {
            v0_1 = 0;
        }
        Ew.D v0_2 = v0_1.D();
        Pb.b.a().getClass();
        CC.f v2_2 = Pb.b.b("AppLoadUserConfig");
        v2_2.start();
        try {
            v0_2.r();
            v2_2.stop();
            CC.f v2_3 = v0_2.i();
        } catch (Ew.D v0_4) {
            v2_3.stop();
            throw v0_4;
        }
        if ((v2_3 != null) && (this.b == null)) {
            this.f(v0_2.p());
        }
        Pb.b.a().getClass();
        Ew.D v0_8 = Pb.b.b("AppInjectDependencies");
        v0_8.start();
        try {
            CC.f v2_5 = this.a;
        } catch (CC.f v1_2) {
            v0_8.stop();
            throw v1_2;
        }
        if (v2_5 != null) {
            v1_0 = v2_5;
        }
        v1_0.p(this);
        this.j();
        v0_8.stop();
        return;
    }

    public final void h()
    {
        Pb.b.a().getClass();
        com.google.firebase.perf.metrics.Trace v1 = Pb.b.b("AppInitPlugins");
        v1.start();
        kotlin.Unit v0_1 = this.d;
        if (v0_1 == null) {
            v0_1 = 0;
        } else {
        }
        kotlin.Unit v0_4 = ((Iterable) kotlin.collections.CollectionsKt.sortedWith(v0_1, new BJ.y(17))).iterator();
        while (v0_4.hasNext()) {
            vn.com.vng.zalopay.internal.d v3_4 = ((vn.com.vng.zalopay.internal.d) v0_4.next());
            if (!v3_4.b()) {
                v3_4.d(this);
            } else {
                kotlinx.coroutines.BuildersKt.launch$default(this.e, 0, 0, new pI.c(v3_4, this, 0, 10), 3, 0);
            }
        }
        v1.stop();
        return;
    }

    public final void i()
    {
        mH.c v1_2 = ((mH.c) ((CC.f) q9.a5.a()).Z0.get());
        ((CC.f) q9.a5.a()).d().a.addIfAbsent(((vn.com.vng.zalopay.network_monitor.data.monitor.q) ((CC.f) q9.a5.a()).Y0.get()));
        try {
            if (!v1_2.m) {
                v1_2.a.d();
                vn.com.vng.zalopay.AndroidApplication v3_1 = kotlinx.coroutines.BuildersKt.launch$default(v1_2.c, 0, 0, new mH.a(v1_2, 0, 1), 3, 0);
                kotlinx.coroutines.Job v5_1 = kotlinx.coroutines.BuildersKt.launch$default(v1_2.c, 0, 0, new mH.a(v1_2, 0, 2), 3, 0);
                kotlinx.coroutines.Job v7_1 = kotlinx.coroutines.BuildersKt.launch$default(v1_2.c, 0, 0, new mH.a(v1_2, 0, 3), 3, 0);
                kotlinx.coroutines.Job[] v6_3 = new kotlinx.coroutines.Job[3];
                v6_3[0] = v3_1;
                v6_3[1] = v5_1;
                v6_3[2] = v7_1;
                v1_2.n = kotlin.collections.CollectionsKt.plus(((java.util.Collection) kotlin.collections.E.listOf(v6_3)), ((Iterable) v1_2.b()));
                v1_2.m = 1;
            }
        } catch (nH.a v0_8) {
            throw v0_8;
        }
        this.registerActivityLifecycleCallbacks(nH.a.a);
        nH.a.b = v1_2;
        return;
    }

    public final void j()
    {
        kc.g v0_0 = this.a;
        CC.f v1 = 0;
        if (v0_0 == null) {
            v0_0 = 0;
        }
        v0_0.getClass();
        m9.P2.a = new lS.b(1, v0_0);
        kc.g v0_1 = this.a;
        if (v0_1 == null) {
            v0_1 = 0;
        }
        v0_1.getClass();
        lO.o v2_1 = new an.b(v0_1);
        kc.g v0_3 = new PG.a();
        m9.Q4.b = v2_1;
        m9.Q4.c = v0_3;
        kc.g v0_4 = this.a;
        if (v0_4 != null) {
            v1 = v0_4;
        }
        v1.getClass();
        KT.b.a = new lO.o(new kc.g(4), v1);
        return;
    }

    public final void k(CC.k p6)
    {
        if (p6 != null) {
            kc.b v0_9 = kotlin.collections.CollectionsKt.sortedWith(p6.U0(), new BJ.y(18));
            this.f = v0_9;
            kc.b v0_0 = ((Iterable) v0_9).iterator();
            while (v0_0.hasNext()) {
                ((GC.a) v0_0.next()).a(p6);
            }
            p9.F4.a = new uy.a(new kX.d(), p6);
            com.google.android.gms.internal.mlkit_vision_barcode.i6.a = new Yx.a(new w5.c(), p6);
            com.google.android.gms.internal.mlkit_vision_barcode.i6.b = 0;
            d6.u v1_19 = vC.q.a();
            if (v1_19 != null) {
                new android.os.Handler(android.os.Looper.getMainLooper()).post(new tZ.a(1, new dG.h(v1_19, 2)));
            }
            com.google.android.gms.internal.ads.mk v2_22 = new Yx.a(new w5.c(), p6, 0);
            d6.u v1_26 = new PG.a();
            ub.c v3_24 = new w5.c();
            n9.P3.a = v2_22;
            n9.P3.b = v1_26;
            n9.P3.c = v3_24;
            com.google.android.gms.internal.ads.mk v2_25 = new tG.a(new kc.g(6), p6);
            d6.u v1_31 = new PG.a();
            com.google.android.gms.internal.mlkit_vision_barcode.i5.a = v2_25;
            com.google.android.gms.internal.mlkit_vision_barcode.i5.b = v1_31;
            com.google.android.gms.internal.ads.mk v2_27 = new dK.a(new l9.w(), p6);
            d6.u v1_35 = new PG.a();
            n9.L.a = v2_27;
            n9.L.b = v1_35;
            com.google.android.gms.internal.ads.mk v2_29 = new androidx.camera.camera2.internal.S0(new kc.b(), p6);
            q9.l6.a = v2_29;
            q9.l6.b = new tJ.a(new io.reactivex.internal.operators.observable.f1(16, v2_29));
            com.google.android.gms.internal.ads.mk v2_33 = new IH.a(new d6.u(), p6);
            d6.u v1_43 = new PG.a();
            com.google.android.gms.internal.mlkit_vision_barcode.f6.a = v2_33;
            com.google.android.gms.internal.mlkit_vision_barcode.f6.b = v1_43;
            com.google.android.gms.internal.mlkit_vision_barcode.r7.a = new tG.a(new kc.d(), p6);
            com.google.android.gms.internal.mlkit_vision_barcode.d7.a = new pS.e(new kc.g(5), p6);
            com.google.android.gms.internal.mlkit_vision_barcode.b0.a = new com.google.android.gms.internal.gtm.I(new d6.u(), q9.a5.b());
            n9.O.a = new Iz.b(new jQ.B(7), q9.a5.b());
            q9.A6.a = new h3.I(new kc.f(), q9.a5.b());
            d6.u v1_3 = n9.O.a;
            if (v1_3 != null) {
                n9.P.a = new g4.a(new m6.t(28), v1_3);
            }
            n9.W2.a = new com.google.android.gms.internal.ads.Xl(new kc.d(), q9.a5.b());
            q9.a7.a = new dK.a(new kX.d(), q9.a5.b());
            com.google.android.gms.internal.mlkit_vision_barcode.R7.a = new AM.f(new jb.b(), q9.a5.b());
            p9.W4.a = new ub.c(new kc.c(), q9.a5.b());
            if (!kotlin.jvm.internal.Intrinsics.areEqual(com.google.android.gms.internal.mlkit_vision_barcode.y0.b, p6)) {
                com.google.android.gms.internal.mlkit_vision_barcode.y0.b = 0;
                com.google.android.gms.internal.mlkit_vision_barcode.y0.c = 0;
                com.google.android.gms.internal.mlkit_vision_barcode.y0.a = 0;
                com.google.android.gms.internal.mlkit_vision_barcode.y0.c = new com.google.android.gms.internal.ads.mk(new kc.b(), new d6.u(), p6);
                com.google.android.gms.internal.mlkit_vision_barcode.y0.b = p6;
                return;
            }
        }
        return;
    }

    public void onCreate()
    {
        Pb.b.a().getClass();
        com.google.firebase.perf.metrics.Trace v0_2 = Pb.b.b("ApplicationLaunch");
        v0_2.start();
        try {
            Throwable v1_1 = java.util.concurrent.Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
            v1_1.execute(new hk.a(5));
            v1_1.shutdown();
            super.onCreate();
            vn.com.vng.zalopay.AndroidApplication.g = this;
            Pb.b.a().getClass();
            Throwable v1_3 = Pb.b.b("AppRegisterCallbacks");
            v1_3.start();
            try {
                this.registerActivityLifecycleCallbacks(new Um.b());
                this.registerActivityLifecycleCallbacks(Um.a.a);
            } catch (Throwable v2_6) {
                v1_3.stop();
                throw v2_6;
            }
            v1_3.stop();
            this.g();
            this.h();
            this.i();
            v0_2.stop();
            return;
        } catch (Throwable v1_4) {
            v0_2.stop();
            throw v1_4;
        }
    }

    public final void onTerminate()
    {
        super.onTerminate();
        com.google.android.gms.internal.mlkit_vision_barcode.W6.a = 0;
        com.google.android.gms.internal.mlkit_vision_barcode.W6.b = 0;
        com.google.android.gms.internal.mlkit_vision_barcode.W6.c = 0;
        return;
    }
}
