package vn.com.vng.zalopay.skewface.presentation.fragment;
public final class UploadPhotoFragment extends Ts.b {
    public final a9.f s;
    public final ng.S t;
    public com.google.firebase.iid.i u;
    public final ng.w v;
    public KG.a w;
    public vn.com.vng.zalopay.skewface.presentation.args.SkewFaceArgument x;
    public com.google.android.gms.internal.firebase-perf.z y;
    public final boolean z;

    public UploadPhotoFragment()
    {
        kotlin.reflect.d v1_0 = kotlin.jvm.internal.M.a;
        this.s = new a9.f(v1_0.b(JN.j), new JN.i(this, 0));
        this.t = ng.x.b(new JN.f(this, 0));
        int v0_5 = new JN.f(this, 1);
        ng.w v2_4 = ng.x.a(ng.y.c, new A0.c(19, new JN.i(this, 1)));
        this.v = androidx.fragment.app.FragmentViewModelLazyKt.createViewModelLazy(this, v1_0.b(ON.f), new CS.x(v2_4, 12), new CS.x(v2_4, 13), v0_5);
        this.z = 1;
        return;
    }

    public final void m1(int p17, q0.s p18)
    {
        y0.b v0_0;
        q0.v v8 = p18.x(-329988485);
        if (!v8.I(this)) {
            v0_0 = 2;
        } else {
            v0_0 = 4;
        }
        if ((((v0_0 | p17) & 3) == 2) && (v8.c())) {
            v8.m();
        } else {
            q0.C0 v5 = q0.d.q(this.q1().f, v8, 0);
            v8.q(-1003410150);
            v8.q(212064437);
            v8.W(0);
            y0.b v0_15 = ((A1.c) v8.d(androidx.compose.ui.platform.m0.f));
            int v1_5 = v8.G();
            G1.k v2_4 = q0.r.a;
            if (v1_5 == v2_4) {
                v1_5 = new G1.A(v0_15);
                v8.B(v1_5);
            }
            G1.A v12_1 = ((G1.A) v1_5);
            y0.b v0_16 = v8.G();
            if (v0_16 == v2_4) {
                v0_16 = new G1.k();
                v8.B(v0_16);
            }
            y0.b v0_18 = ((G1.k) v0_16);
            int v1_7 = v8.G();
            if (v1_7 == v2_4) {
                v1_7 = q0.d.G(Boolean.FALSE, q0.D0.e);
                v8.B(v1_7);
            }
            q0.C0 v14_1 = ((q0.C0) v1_7);
            int v1_9 = v8.G();
            if (v1_9 == v2_4) {
                v1_9 = new G1.o(v0_18);
                v8.B(v1_9);
            }
            G1.o v13_1 = ((G1.o) v1_9);
            int v1_11 = v8.G();
            if (v1_11 == v2_4) {
                v1_11 = q0.d.G(kotlin.Unit.INSTANCE, q0.D0.b);
                v8.B(v1_11);
            }
            int v1_13 = ((q0.C0) v1_11);
            kotlin.jvm.functions.Function0 v3_5 = (v8.I(v12_1) | v8.t(257));
            int v6_4 = v8.G();
            if ((v3_5 != null) || (v6_4 == v2_4)) {
                a1.U v10_1 = new DZ.B(v1_13, v12_1, v13_1, v14_1, 3);
                v8.B(v10_1);
                v6_4 = v10_1;
            }
            a1.U v10_3 = ((a1.U) v6_4);
            kotlin.jvm.functions.Function0 v3_0 = v8.G();
            if (v3_0 == v2_4) {
                v3_0 = new DZ.C(v14_1, v13_1, 3);
                v8.B(v3_0);
            }
            kotlin.jvm.functions.Function0 v3_1 = ((kotlin.jvm.functions.Function0) v3_0);
            int v6_0 = v8.I(v12_1);
            D0.q v11_0 = v8.G();
            if ((v6_0 != 0) || (v11_0 == v2_4)) {
                v11_0 = new DZ.D(v12_1, 9);
                v8.B(v11_0);
            }
            a1.x0.a(i1.n.a(D0.p.a, ((kotlin.jvm.functions.Function1) v11_0), 0), y0.c.c(1200550679, new FN.a(v1_13, v0_18, v3_1, this, v5, 2), v8), v10_3, v8, 48);
            v8.W(0);
        }
        y0.b v0_5 = v8.Y();
        if (v0_5 != null) {
            v0_5.d = new AA.e(this, p17, 10);
        }
        return;
    }

    public final boolean n1()
    {
        return this.z;
    }

    public final boolean o1()
    {
        int v0_0 = this.y;
        if (v0_0 == 0) {
            v0_0 = 0;
        }
        String v1_0;
        String v1_4 = this.q1().g0();
        kotlin.Pair v2_0 = kotlin.TuplesKt.to("appVer", ((String) v0_0.c));
        int v0_2 = ((vn.com.vng.zalopay.skewface.presentation.args.SkewFaceArgument) v0_0.b);
        kotlin.Pair v3_1 = kotlin.TuplesKt.to("bankCode", v0_2.getBankCode());
        kotlin.Pair v4_2 = kotlin.TuplesKt.to("transID", v0_2.getTransactionId());
        int v0_4 = kotlin.TuplesKt.to("appSessionId", v0_2.getAppSessionId());
        if (v1_4 == null) {
            v1_0 = "fail";
        } else {
            v1_0 = "success";
        }
        int v0_6 = kotlin.collections.a0.mapOf(new kotlin.Pair[] {v2_0, v3_1, v4_2, v0_4, kotlin.TuplesKt.to("status", v1_0)}));
        java.util.Objects.toString(v0_6);
        UR.a.e("01.2048.003", v0_6);
        return 0;
    }

    public final void onAttach(android.content.Context p8)
    {
        super.onAttach(p8);
        vn.com.vng.zalopay.skewface.presentation.args.SkewFaceArgument v8_5 = ((k0.r) com.google.android.gms.internal.mlkit_vision_barcode.r7.b.b);
        String v1_2 = ((HN.b) v8_5.b);
        int v2_1 = v1_2.b();
        int v5_2 = ((uN.b) v1_2.m().create(uN.b));
        p9.n7.c(v5_2);
        this.u = new com.google.firebase.iid.i(v2_1, new com.airbnb.lottie.network.f(5, new wN.c(v5_2)), 16);
        com.google.android.gms.internal.firebase-perf.z v0_2 = v1_2.a();
        p9.n7.c(v0_2);
        this.w = v0_2;
        vn.com.vng.zalopay.skewface.presentation.args.SkewFaceArgument v8_3 = ((vn.com.vng.zalopay.skewface.presentation.args.SkewFaceArgument) v8_5.c);
        p9.n7.c(v8_3);
        this.x = v8_3;
        String v1_0 = v1_2.getAppVersion();
        p9.n7.c(v1_0);
        this.y = new com.google.android.gms.internal.firebase-perf.z(v8_3, v1_0, 14);
        return;
    }

    public final void onResume()
    {
        super.onResume();
        java.util.Map v0_0 = this.y;
        if (v0_0 == null) {
            v0_0 = 0;
        }
        java.util.Map v0_1 = ((vn.com.vng.zalopay.skewface.presentation.args.SkewFaceArgument) v0_0.b);
        java.util.Map v0_5 = kotlin.collections.a0.mapOf(new kotlin.Pair[] {kotlin.TuplesKt.to("appVer", ((String) v0_0.c)), kotlin.TuplesKt.to("bankCode", v0_1.getBankCode()), kotlin.TuplesKt.to("transID", v0_1.getTransactionId()), kotlin.TuplesKt.to("appSessionId", v0_1.getAppSessionId())}));
        java.util.Objects.toString(v0_5);
        UR.a.e("01.2048.000", v0_5);
        return;
    }

    public final void onViewCreated(android.view.View p12, android.os.Bundle p13)
    {
        super.onViewCreated(p12, p13);
        ON.f v1 = this.q1();
        U2.a v12_2 = this.x;
        if (v12_2 == null) {
            v12_2 = 0;
        }
        String v3 = v12_2.getSource();
        vn.com.vng.zalopay.skewface.presentation.args.UploadPhotoFragmentArgument v2 = this.p1();
        v1.getClass();
        kotlinx.coroutines.BuildersKt.launch$default(androidx.lifecycle.l0.l(v1), 0, 0, new AA.j(v1, v2, v3, 0, 25), 3, 0);
        return;
    }

    public final vn.com.vng.zalopay.skewface.presentation.args.UploadPhotoFragmentArgument p1()
    {
        return ((vn.com.vng.zalopay.skewface.presentation.args.UploadPhotoFragmentArgument) this.t.getValue());
    }

    public final ON.f q1()
    {
        return ((ON.f) this.v.getValue());
    }
}
