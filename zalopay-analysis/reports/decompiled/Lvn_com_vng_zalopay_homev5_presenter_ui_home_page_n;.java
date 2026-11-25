package vn.com.vng.zalopay.homev5.presenter.ui.home_page;
public final class n extends oA.e {
    public final androidx.lifecycle.r0 r;
    public final vn.com.vng.zalopay.homev5.presenter.ui.home_page.Z0 s;
    public final KG.a t;
    public final ng.w u;
    public final ng.w v;
    public final ng.S w;
    public final YB.d x;
    public final AW.a y;
    public String z;

    public n()
    {
        aA.a v0_6 = new vn.com.vng.zalopay.homev5.presenter.ui.home_page.l(this, 0);
        String v1_0 = ng.x.a(ng.y.c, new o1.p(22, new vn.com.vng.zalopay.homev5.presenter.ui.home_page.m(this, 2)));
        androidx.camera.camera2.impl.a v2_0 = kotlin.jvm.internal.M.a;
        this.u = androidx.fragment.app.FragmentViewModelLazyKt.createViewModelLazy(this, v2_0.b(vn.com.vng.zalopay.homev5.presenter.ui.home_page.g1), new androidx.fragment.app.L0(v1_0, 13), new androidx.fragment.app.L0(v1_0, 14), v0_6);
        this.v = androidx.fragment.app.FragmentViewModelLazyKt.createViewModelLazy(this, v2_0.b(DA.n), new vn.com.vng.zalopay.homev5.presenter.ui.home_page.m(this, 0), new vn.com.vng.zalopay.homev5.presenter.ui.home_page.m(this, 1), new vn.com.vng.zalopay.homev5.presenter.ui.home_page.l(this, 1));
        this.w = ng.x.b(new vn.com.vng.zalopay.homev5.presenter.ui.home_page.l(this, 2));
        aA.a v0_12 = new YB.d();
        v0_12.a = System.currentTimeMillis();
        this.x = v0_12;
        this.y = new AW.a(10);
        this.z = java.util.UUID.randomUUID().toString();
        aA.a v0_17 = com.google.android.gms.internal.mlkit_vision_barcode.i6.b;
        if (v0_17 == null) {
            aA.a v0_18 = com.google.android.gms.internal.mlkit_vision_barcode.i6.a;
            if (v0_18 == null) {
                throw new IllegalStateException("envComponent must be initialized first by calling HomeV5Plugin.install()");
            } else {
                String v1_12 = new XQ.i(9, v0_18);
                aA.a v0_21 = com.google.android.gms.internal.mlkit_vision_barcode.i6.c;
                if (v0_21 == null) {
                    v0_21 = gi.N.d();
                    com.google.android.gms.internal.mlkit_vision_barcode.i6.c = v0_21;
                }
                aA.a v3_9 = new aA.a(v1_12, new androidx.camera.camera2.impl.a(7, gi.L.a(v0_21)), new kX.d());
                com.google.android.gms.internal.mlkit_vision_barcode.i6.b = v3_9;
                v0_17 = v3_9;
            }
        }
        this.r = ((androidx.lifecycle.r0) v0_17.K.get());
        this.s = ((vn.com.vng.zalopay.homev5.presenter.ui.home_page.Z0) v0_17.l0.a);
        aA.a v0_2 = ((aA.c) v0_17.a.b).b();
        p9.n7.c(v0_2);
        this.t = v0_2;
        return;
    }

    public final void m1(int p9, q0.s p10)
    {
        int v0_2;
        q0.Y0 v10_1 = p10.x(944162583);
        if (!v10_1.I(this)) {
            v0_2 = 16;
        } else {
            v0_2 = 32;
        }
        int v0_5 = (v0_2 | p9);
        if (((v0_5 & 19) == 18) && (v10_1.c())) {
            v10_1.m();
        } else {
            this.n1(((v0_5 >> 3) & 14), v10_1);
            int v2_2 = D0.p.a;
            D0.q v1_6 = androidx.compose.ui.platform.r0.q(i1.n.a(v2_2, new qK.a(12), 0), "home_page_root");
            androidx.work.impl.utils.e v3_1 = androidx.compose.foundation.layout.w.e(D0.b.a, 0);
            int v4_2 = v10_1.P;
            boolean v5_0 = v10_1.S();
            D0.q v1_7 = D0.s.c(v1_6, v10_1);
            c1.m.d2.getClass();
            v10_1.l();
            if (!v10_1.O) {
                v10_1.h();
            } else {
                v10_1.K(c1.l.b);
            }
            q0.d.N(v3_1, c1.l.g, v10_1);
            q0.d.N(v5_0, c1.l.f, v10_1);
            if ((v10_1.O) || (!kotlin.jvm.internal.Intrinsics.areEqual(v10_1.G(), Integer.valueOf(v4_2)))) {
                Kb.e.t(v4_2, v10_1, v4_2, c1.l.i);
            }
            q0.d.N(v1_7, c1.l.d, v10_1);
            vn.com.vng.zalopay.homev5.presenter.ui.home_page.N0.c(this.q1(), this.p1().e, v10_1, 384);
            this.o1(androidx.compose.foundation.layout.P0.g(androidx.compose.foundation.layout.c.E(v2_2), 1065353216), v10_1, (v0_5 & 112));
            v10_1.W(1);
        }
        q0.Y0 v10_2 = v10_1.Y();
        if (v10_2 != null) {
            v10_2.d = new qH.c(this, p9, 7);
        }
        return;
    }

    public final void n1(int p8, q0.s p9)
    {
        PE.f v0_2;
        q0.Y0 v9_1 = p9.x(878817020);
        if ((p8 & 6) != 0) {
            v0_2 = p8;
        } else {
            PE.f v0_1;
            if (!v9_1.I(this)) {
                v0_1 = 2;
            } else {
                v0_1 = 4;
            }
            v0_2 = (v0_1 | p8);
        }
        if (((v0_2 & 3) == 2) && (v9_1.c())) {
            v9_1.m();
        } else {
            int v1_5;
            android.content.Context v3_1 = ((android.content.Context) v9_1.d(androidx.compose.ui.platform.AndroidCompositionLocals_androidKt.b));
            android.view.View v4_1 = ((android.view.View) v9_1.d(androidx.compose.ui.platform.AndroidCompositionLocals_androidKt.f));
            int v1_2 = ((v9_1.I(this) | v9_1.I(v3_1)) | v9_1.I(v4_1));
            vn.com.vng.zalopay.homev5.presenter.ui.home_page.n v2_2 = v9_1.G();
            if ((v1_2 == 0) && (v2_2 != q0.r.a)) {
                v1_5 = v2_2;
            } else {
                v1_5 = new oW.a(this, v3_1, v4_1, 0, 17);
                v9_1.B(v1_5);
            }
            q0.Z.g(kotlin.Unit.INSTANCE, ((kotlin.jvm.functions.Function2) v1_5), v9_1);
        }
        q0.Y0 v9_2 = v9_1.Y();
        if (v9_2 != null) {
            v9_2.d = new PE.f(this, p8, 10);
        }
        return;
    }

    public final void o1(D0.q p18, q0.s p19, int p20)
    {
        boolean v6_4;
        AW.a v4_8 = Integer.valueOf(0);
        int v5_2 = p19.x(1780612471);
        if (!v5_2.p(p18)) {
            v6_4 = 2;
        } else {
            v6_4 = 4;
        }
        Integer v8_3;
        if (!v5_2.I(this)) {
            v8_3 = 16;
        } else {
            v8_3 = 32;
        }
        boolean v6_12 = ((v6_4 | p20) | v8_3);
        if (((v6_12 & 19) == 18) && (v5_2.c())) {
            v5_2.m();
        } else {
            Integer v8_6 = v5_2.G();
            boolean v9_2 = q0.r.a;
            if (v8_6 == v9_2) {
                v8_6 = q0.d.G(new J0.f(0), q0.D0.e);
                v5_2.B(v8_6);
            }
            Integer v8_9 = ((q0.C0) v8_6);
            D0.g v10_2 = v5_2.G();
            if (v10_2 == v9_2) {
                v10_2 = new qK.a(27);
                v5_2.B(v10_2);
            }
            int v7_2 = U0.c.d(v4_8, ((kotlin.jvm.functions.Function1) v10_2), v5_2, 2);
            AW.a v4_11 = kotlin.collections.a0.mapOf(new kotlin.Pair[] {kotlin.TuplesKt.to(Float.valueOf(0), v4_8), kotlin.TuplesKt.to(Float.valueOf((- J0.f.b(((J0.f) v8_9.getValue()).a))), Integer.valueOf(1))}));
            D0.g v10_14 = v5_2.G();
            if (v10_14 == v9_2) {
                v10_14 = new lM.a(v8_9, 21);
                v5_2.B(v10_14);
            }
            Integer v8_10 = D0.p.a;
            D0.g v10_17 = androidx.compose.ui.layout.a.f(v8_10, ((kotlin.jvm.functions.Function1) v10_14));
            c1.j v12_3 = v5_2.G();
            if (v12_3 == v9_2) {
                v12_3 = new vK.a(7);
                v5_2.B(v12_3);
            }
            AW.a v4_12 = U0.c.e(v10_17, v7_2, v4_11, X.e0.a, ((kotlin.jvm.functions.Function2) v12_3));
            D0.g v10_18 = D0.b.a;
            c1.j v12_6 = androidx.compose.foundation.layout.w.e(v10_18, 0);
            int v13_0 = v5_2.P;
            c1.j v14_0 = v5_2.S();
            AW.a v4_0 = D0.s.c(v4_12, v5_2);
            c1.m.d2.getClass();
            c1.k v15_1 = c1.l.b;
            v5_2.l();
            if (!v5_2.O) {
                v5_2.h();
            } else {
                v5_2.K(v15_1);
            }
            Integer v16;
            c1.j v11_1 = c1.l.g;
            q0.d.N(v12_6, v11_1, v5_2);
            c1.j v12_0 = c1.l.f;
            q0.d.N(v14_0, v12_0, v5_2);
            c1.j v14_1 = c1.l.i;
            if (v5_2.O) {
                v16 = v6_12;
                Kb.e.t(v13_0, v5_2, v13_0, v14_1);
            } else {
                v16 = v6_12;
                if (!kotlin.jvm.internal.Intrinsics.areEqual(v5_2.G(), Integer.valueOf(v13_0))) {
                }
            }
            int v3_3 = c1.l.d;
            q0.d.N(v4_0, v3_3, v5_2);
            AW.a v4_1 = v5_2.p(v7_2);
            boolean v6_2 = v5_2.G();
            if ((v4_1 != null) || (v6_2 == v9_2)) {
                v6_2 = new OP.c(v7_2, 6);
                v5_2.B(v6_2);
            }
            AW.a v4_3 = androidx.compose.foundation.layout.c.u(v8_10, ((kotlin.jvm.functions.Function1) v6_2));
            boolean v6_7 = androidx.compose.foundation.layout.w.e(v10_18, 0);
            int v7_1 = v5_2.P;
            Integer v8_0 = v5_2.S();
            AW.a v4_4 = D0.s.c(v4_3, v5_2);
            v5_2.l();
            if (!v5_2.O) {
                v5_2.h();
            } else {
                v5_2.K(v15_1);
            }
            q0.d.N(v6_7, v11_1, v5_2);
            q0.d.N(v8_0, v12_0, v5_2);
            if ((v5_2.O) || (!kotlin.jvm.internal.Intrinsics.areEqual(v5_2.G(), Integer.valueOf(v7_1)))) {
                Kb.e.t(v7_1, v5_2, v7_1, v14_1);
            }
            q0.d.N(v4_4, v3_3, v5_2);
            Hl.g.a(p18, this.y, v5_2, (v16 & 14));
            v5_2.W(1);
            v5_2.W(1);
        }
        int v3_6 = v5_2.Y();
        if (v3_6 != 0) {
            v3_6.d = new kV.e(this, p18, p20, 22);
        }
        return;
    }

    public final void onDestroyView()
    {
        rN.b.d("home_more_highlighted_app");
        super.onDestroyView();
        return;
    }

    public final void onPause()
    {
        if (((vn.com.vng.zalopay.homev5.presenter.ui.home_page.X0) this.q1().j0()).g == null) {
            YB.d v0_4 = this.x;
            v0_4.getClass();
            v0_4.a = System.currentTimeMillis();
        }
        super.onPause();
        return;
    }

    public final void onResume()
    {
        vn.com.vng.zalopay.homev5.presenter.ui.home_page.g1 v0_0 = this.x;
        v0_0.getClass();
        vn.com.vng.zalopay.homev5.presenter.ui.home_page.I v1_2 = System.currentTimeMillis();
        if ((v1_2 - v0_0.a) > 5000) {
            v0_0.a = v1_2;
            this.q1().o0(vn.com.vng.zalopay.homev5.presenter.ui.home_page.e0.a);
        }
        vn.com.vng.zalopay.homev5.presenter.ui.home_page.g1 v0_2 = this.getActivity();
        if (v0_2 != null) {
            HF.b.q(v0_2.getWindow());
        }
        this.q1().o0(vn.com.vng.zalopay.homev5.presenter.ui.home_page.I.a);
        super.onResume();
        return;
    }

    public final void onViewCreated(android.view.View p12, android.os.Bundle p13)
    {
        super.onViewCreated(p12, p13);
        this.getParentFragmentManager().h0("close_button_click", this.getViewLifecycleOwner(), new hJ.g(p12));
        rN.b.c("home_more_highlighted_app", kotlin.jvm.internal.M.a.b(Tx.a), androidx.lifecycle.l0.i(this.getLifecycle()), kotlinx.coroutines.Dispatchers.getMain(), 0, new Ai.l(2, new jV.k(26, this), kotlin.jvm.internal.s, "suspendConversion0", "subscribeHighlightedAppsUpdatedEvent$suspendConversion0(Lkotlin/jvm/functions/Function1;Lvn/com/vng/zalopay/home_more/presentation/ui/contract/HomeMoreHighlightedAppsUpdatedEvent;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", 0, 13));
        kotlinx.coroutines.BuildersKt.launch$default(androidx.lifecycle.l0.j(this.getViewLifecycleOwner()), 0, 0, new cV.Y(this, 0, 29), 3, 0);
        return;
    }

    public final DA.n p1()
    {
        return ((DA.n) this.v.getValue());
    }

    public final vn.com.vng.zalopay.homev5.presenter.ui.home_page.g1 q1()
    {
        return ((vn.com.vng.zalopay.homev5.presenter.ui.home_page.g1) this.u.getValue());
    }
}
