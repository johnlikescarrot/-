package vn.com.vng.zalopay.skewface.presentation.fragment;
public final class TakePhotoFragment extends Ts.b {
    public final android.os.Handler A;
    public final boolean B;
    public com.android.billingclient.api.c0 s;
    public final ng.w t;
    public com.airbnb.lottie.network.g u;
    public java.io.File v;
    public java.util.concurrent.ExecutorService w;
    public F.w0 x;
    public F.c0 y;
    public KN.a z;

    public TakePhotoFragment()
    {
        int v0_4 = new JN.a(this, 0);
        android.os.Looper v1_0 = ng.x.a(ng.y.c, new A0.c(18, new A0.c(17, this)));
        this.t = androidx.fragment.app.FragmentViewModelLazyKt.createViewModelLazy(this, kotlin.jvm.internal.M.a.b(ON.c), new CS.x(v1_0, 10), new CS.x(v1_0, 11), v0_4);
        this.A = new android.os.Handler(android.os.Looper.getMainLooper());
        this.B = 1;
        return;
    }

    public final void m1(int p14, q0.s p15)
    {
        q0.Y0 v15_8;
        q0.v v10 = p15.x(473420347);
        if (!v10.I(this)) {
            v15_8 = 2;
        } else {
            v15_8 = 4;
        }
        if ((((v15_8 | p14) & 3) == 2) && (v10.c())) {
            v10.m();
        } else {
            S8.a.c(androidx.compose.foundation.layout.P0.e(D0.p.a, 1065353216), 0, ql.b.V, 0, 0, 0, y0.c.c(-320981889, new JN.c(this, 1, 0), v10), v10, 1572870, 58);
        }
        q0.Y0 v15_6 = v10.Y();
        if (v15_6 != null) {
            v15_6.d = new JN.c(this, p14);
        }
        return;
    }

    public final boolean n1()
    {
        return this.B;
    }

    public final boolean o1()
    {
        int v0_0 = this.u;
        if (v0_0 == 0) {
            v0_0 = 0;
        }
        int v0_7 = ((vn.com.vng.zalopay.skewface.presentation.args.SkewFaceArgument) v0_0.b);
        int v0_4 = kotlin.collections.a0.mapOf(new kotlin.Pair[] {kotlin.TuplesKt.to("appVer", ((String) v0_0.c)), kotlin.TuplesKt.to("bankCode", v0_7.getBankCode()), kotlin.TuplesKt.to("transID", v0_7.getTransactionId()), kotlin.TuplesKt.to("appSessionId", v0_7.getAppSessionId())}));
        java.util.Objects.toString(v0_4);
        UR.a.e("01.2047.001", v0_4);
        return 0;
    }

    public final void onAttach(android.content.Context p9)
    {
        super.onAttach(p9);
        vn.com.vng.zalopay.skewface.presentation.args.SkewFaceArgument v9_5 = ((k0.r) com.google.android.gms.internal.mlkit_vision_barcode.r7.b.b);
        String v1_2 = ((HN.b) v9_5.b);
        int v2_1 = v1_2.b();
        int v5_2 = ((uN.a) v1_2.A().create(uN.a));
        p9.n7.c(v5_2);
        String v6_1 = v1_2.getAppVersion();
        p9.n7.c(v6_1);
        c6.a v3_0 = new c6.a(4, new g4.a(v5_2, v6_1, 24));
        vn.com.vng.zalopay.skewface.presentation.args.SkewFaceArgument v9_3 = ((vn.com.vng.zalopay.skewface.presentation.args.SkewFaceArgument) v9_5.c);
        p9.n7.c(v9_3);
        this.s = new com.android.billingclient.api.c0(v2_1, v3_0, v9_3, 8);
        String v1_0 = v1_2.getAppVersion();
        p9.n7.c(v1_0);
        this.u = new com.airbnb.lottie.network.g(v9_3, v1_0, 17);
        return;
    }

    public final void onDestroy()
    {
        super.onDestroy();
        this.A.removeCallbacksAndMessages(0);
        return;
    }

    public final void onResume()
    {
        super.onResume();
        ON.c v0_0 = this.u;
        if (v0_0 == null) {
            v0_0 = 0;
        }
        ON.c v0_1 = ((vn.com.vng.zalopay.skewface.presentation.args.SkewFaceArgument) v0_0.b);
        ON.c v0_5 = kotlin.collections.a0.mapOf(new kotlin.Pair[] {kotlin.TuplesKt.to("appVer", ((String) v0_0.c)), kotlin.TuplesKt.to("bankCode", v0_1.getBankCode()), kotlin.TuplesKt.to("transID", v0_1.getTransactionId()), kotlin.TuplesKt.to("appSessionId", v0_1.getAppSessionId())}));
        java.util.Objects.toString(v0_5);
        UR.a.e("01.2047.000", v0_5);
        this.p1().h0();
        return;
    }

    public final ON.c p1()
    {
        return ((ON.c) this.t.getValue());
    }
}
