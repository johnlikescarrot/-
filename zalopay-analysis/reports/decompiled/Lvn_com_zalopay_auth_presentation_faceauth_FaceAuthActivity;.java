package vn.com.zalopay.auth.presentation.faceauth;
public final class FaceAuthActivity extends androidx.appcompat.app.AppCompatActivity {
    public static final synthetic int B;
    public Cs.a A;
    public HD.i i;
    public final O5.b j;
    public Pt.n k;
    public String l;
    public String m;
    public Ns.d n;
    public xS.b o;
    public KG.a p;
    public com.airbnb.lottie.network.g q;
    public retrofit2.Retrofit r;
    public tS.x s;
    public String t;
    public pS.j u;
    public zS.a v;
    public String w;
    public String x;
    public boolean y;
    public String z;

    public FaceAuthActivity()
    {
        this.j = new O5.b(kotlin.jvm.internal.M.a.b(tS.C), new tS.k(this, 0), new tS.e(this, 2), new tS.k(this, 1));
        this.t = "";
        this.w = "";
        this.x = "";
        return;
    }

    public final void e1()
    {
        this.i1(0);
        this.g1();
        return;
    }

    public final void g1()
    {
        this.finish();
        this.overridePendingTransition(0, 0);
        return;
    }

    public final tS.C h1()
    {
        return ((tS.C) this.j.getValue());
    }

    public final void i1(boolean p9)
    {
        BS.b v9_5;
        BS.c v0_0 = 0;
        if (p9 == null) {
            BS.b v9_6 = this.s;
            if (v9_6 == null) {
                v9_6 = 0;
            }
            if (tS.g.a[((tS.y) v9_6.g.b.getValue()).h.a.ordinal()] != 1) {
                v9_5 = BS.b.b;
            } else {
                v9_5 = BS.b.a;
            }
        } else {
            v9_5 = BS.b.c;
        }
        String v1_2 = this.s;
        if (v1_2 == null) {
            v1_2 = 0;
        }
        String v1_8 = ((tS.y) v1_2.g.b.getValue()).a.a;
        if (kotlin.text.StringsKt.isBlank(v1_8)) {
            String v3_1 = new Object[0];
            ck.b.a(wmoi.igob.ggcn(6623), v3_1);
        }
        int v2_3 = this.v;
        if (v2_3 == 0) {
            v2_3 = 0;
        }
        String v3_2 = this.s;
        if (v3_2 != null) {
            v0_0 = v3_2;
        }
        BS.c v0_6 = ((tS.y) v0_0.g.b.getValue()).h.e;
        v2_3.getClass();
        v2_3.g(wmoi.igob.ggcn(6629), kotlin.collections.a0.mapOf(new kotlin.Pair[] {kotlin.TuplesKt.to(wmoi.igob.ggcn(6624), v9_5.e()), kotlin.TuplesKt.to(wmoi.igob.ggcn(6625), v0_6), kotlin.TuplesKt.to(wmoi.igob.ggcn(6626), v2_3.b), kotlin.TuplesKt.to(wmoi.igob.ggcn(6627), v2_3.c), kotlin.TuplesKt.to(wmoi.igob.ggcn(6628), Float.valueOf(v2_3.f))})));
        rN.c.a(new BS.c(v9_5, v1_8));
        return;
    }

    public final void j1()
    {
        this.y = 1;
        java.util.Map v2_5 = this.s;
        zS.a v6 = 0;
        if (v2_5 == null) {
            v2_5 = 0;
        }
        int v16;
        java.util.Map v2_4 = ((tS.y) v2_5.g.b.getValue()).a;
        String v12 = v2_4.b.e();
        if (v2_4.i != 2) {
            v16 = 0;
        } else {
            v16 = 1;
        }
        long v3_4;
        if (v16 == 0) {
            v3_4 = wmoi.igob.ggcn(6631);
        } else {
            v3_4 = wmoi.igob.ggcn(6630);
        }
        wD.c v13_0 = v3_4;
        long v3_6 = v2_4.f;
        java.util.Objects.toString(v3_6);
        java.util.ArrayList v14_1 = new java.util.ArrayList();
        if (v3_6.isEmpty()) {
            v14_1.add(wD.m.d);
        }
        long v3_7 = ((Iterable) v3_6);
        java.util.ArrayList v4_4 = new java.util.ArrayList();
        long v3_8 = v3_7.iterator();
        while (v3_8.hasNext()) {
            int v5_3 = ((Number) v3_8.next()).intValue();
            wD.m.b.getClass();
            int v5_4 = kO.t.a(v5_3);
            if (v5_4 != 0) {
                v4_4.add(v5_4);
            }
        }
        v14_1.addAll(v4_4);
        BZ.a v7_1 = com.google.android.gms.internal.mlkit_vision_barcode.L7.c();
        VI.d v8_0 = String.valueOf(1);
        String v0_6 = this.n;
        if (v0_6 == null) {
            v0_6 = 0;
        }
        v7_1.e(v8_0, v0_6.e, wmoi.igob.ggcn(6632), this.x, v12, v13_0);
        long v3_9 = System.currentTimeMillis();
        String v0_7 = this.r;
        if (v0_7 == null) {
            v0_7 = 0;
        }
        int v5_6 = this.k;
        if (v5_6 == 0) {
            v5_6 = 0;
        }
        BZ.a v7_2 = this.n;
        if (v7_2 == null) {
            v7_2 = 0;
        }
        vD.a.d = v0_7;
        vD.a.e = v5_6;
        vD.a.f = v7_2;
        FD.b.b(this, new wD.c(v14_1, new wD.e(wD.h.a, v2_4.a, v2_4.h), v16, v2_4.b.e(), v2_4.j, v2_4.l, 251), new BZ.a(this, v3_9, 5), new VI.d(this, v12, v3_9), new tS.f(this, v12, v3_9, 0));
        String v0_3 = this.v;
        if (v0_3 != null) {
            v6 = v0_3;
        }
        v6.g(wmoi.igob.ggcn(6633), v6.a(v12));
        return;
    }

    public final void k1()
    {
        this.y = 1;
        String v0_34 = this.s;
        zS.a v6 = 0;
        if (v0_34 == null) {
            v0_34 = 0;
        }
        java.util.Map v2_0 = ((tS.y) v0_34.g.b.getValue()).a.b.e();
        vn.ai.trueid.sdk.models.FaceAuthenInput v3_0 = System.currentTimeMillis();
        String v0_24 = this.s;
        if (v0_24 == null) {
            v0_24 = 0;
        }
        Integer v7 = ((tS.y) v0_24.g.b.getValue()).a.k;
        String v0_33 = this.k;
        if (v0_33 == null) {
            v0_33 = 0;
        }
        String v8 = v0_33.F();
        org.json.JSONObject v14_1 = new org.json.JSONObject();
        String v0_35 = this.k;
        if (v0_35 == null) {
            v0_35 = 0;
        }
        String v0_36 = v0_35.d();
        int v5_12 = new StringBuilder(wmoi.igob.ggcn(6634));
        v5_12.append(v0_36);
        v14_1.put(wmoi.igob.ggcn(6635), v5_12.toString());
        String v0_38 = this.k;
        if (v0_38 == null) {
            v0_38 = 0;
        }
        v14_1.put(wmoi.igob.ggcn(6636), v0_38.c());
        String v0_40 = this.k;
        if (v0_40 == null) {
            v0_40 = 0;
        }
        v14_1.put(wmoi.igob.ggcn(6637), v0_40.F());
        String v0_43 = this.n;
        if (v0_43 == null) {
            v0_43 = 0;
        }
        v14_1.put(wmoi.igob.ggcn(6638), v0_43.c);
        String v0_45 = this.n;
        if (v0_45 == null) {
            v0_45 = 0;
        }
        v14_1.put(wmoi.igob.ggcn(6639), v0_45.b);
        String v0_48 = this.n;
        if (v0_48 == null) {
            v0_48 = 0;
        }
        v14_1.put(wmoi.igob.ggcn(6640), v0_48.f);
        v14_1.put(wmoi.igob.ggcn(6641), wmoi.igob.ggcn(6642));
        String v0_2 = this.n;
        if (v0_2 == null) {
            v0_2 = 0;
        }
        v14_1.put(wmoi.igob.ggcn(6643), v0_2.e);
        String v0_4 = this.k;
        if (v0_4 == null) {
            v0_4 = 0;
        }
        v14_1.put(wmoi.igob.ggcn(6644), v0_4.a());
        String v0_8 = this.k;
        if (v0_8 == null) {
            v0_8 = 0;
        }
        v14_1.put(wmoi.igob.ggcn(6645), v0_8.F());
        String v0_11 = this.n;
        if (v0_11 == null) {
            v0_11 = 0;
        }
        v14_1.put(wmoi.igob.ggcn(6646), v0_11.h);
        String v0_14 = this.s;
        if (v0_14 == null) {
            v0_14 = 0;
        }
        int v16;
        v14_1.put(wmoi.igob.ggcn(6647), ((tS.y) v0_14.g.b.getValue()).a.a);
        nY.h.d(v14_1);
        String v0_21 = this.m;
        if (v0_21 == null) {
            v16 = 0;
        } else {
            v16 = v0_21;
        }
        String v11_1 = wmoi.igob.ggcn(6648);
        String v17_1 = wmoi.igob.ggcn(6649);
        String v18_1 = wmoi.igob.ggcn(6650);
        LD.a v9_2 = new LD.a(v16, v11_1, v17_1, v18_1, v14_1);
        String v0_23 = new tS.f(this, v2_0, v3_0, 1);
        if ((v7 == null) || (v7.intValue() != 2)) {
            eb.b.e(this, v9_2, v0_23);
        } else {
            vn.ai.trueid.sdk.navigation.TrueIDV3.setConfigFaceAuthen(this, new vn.ai.trueid.sdk.models.FaceAuthenConfig(v16, v17_1, v18_1, "", wmoi.igob.ggcn(6651), "", "", wmoi.igob.ggcn(6652), v14_1.toString(), 3, 1, 0, wmoi.igob.ggcn(6653), wmoi.igob.ggcn(6654), wmoi.igob.ggcn(6655), wmoi.igob.ggcn(6656), wmoi.igob.ggcn(6657), wmoi.igob.ggcn(6658), 0, 2, 1, 0, wmoi.igob.ggcn(6659), wmoi.igob.ggcn(6660), 0, 0, 52692992, 0));
            vn.ai.trueid.sdk.navigation.TrueIDV3.setLanguage(this, wmoi.igob.ggcn(6661));
            vn.ai.trueid.sdk.navigation.TrueIDV3.setVendor(this, wmoi.igob.ggcn(6662));
            vn.ai.trueid.sdk.navigation.TrueIDV3.startFaceAuthen(this, new vn.ai.trueid.sdk.models.FaceAuthenInput("", v8, new org.json.JSONObject()), new androidx.work.impl.utils.e(12, v0_23));
        }
        String v0_28 = this.v;
        if (v0_28 != null) {
            v6 = v0_28;
        }
        v6.g(wmoi.igob.ggcn(6663), v6.a(v2_0));
        return;
    }

    public final void onCreate(android.os.Bundle p13)
    {
        uS.a v1_0 = n9.O.a;
        int v2_5 = new XQ.j(new androidx.camera.camera2.impl.a(21, v1_0));
        int v3_4 = v2_5.C();
        androidx.lifecycle.A v4_0 = v1_0.h();
        int v5_1 = v1_0.c();
        p9.n7.c(v5_1);
        this.i = new HD.i(v3_4, v4_0, v5_1, 5);
        this.k = v1_0.h();
        tS.m v0_14 = v1_0.n();
        p9.n7.c(v0_14);
        this.l = v0_14;
        tS.m v0_20 = v1_0.o();
        p9.n7.c(v0_20);
        this.m = v0_20;
        tS.m v0_26 = v1_0.c();
        p9.n7.c(v0_26);
        this.n = v0_26;
        v2_5.C();
        tS.m v0_32 = new xS.b();
        v0_32.a = ji.j0.c(xS.c.b);
        this.o = v0_32;
        tS.m v0_33 = v1_0.b();
        p9.n7.c(v0_33);
        this.p = v0_33;
        tS.m v0_37 = ((gS.a) ((androidx.camera.camera2.impl.a) v2_5.b).b);
        this.q = new com.airbnb.lottie.network.g(v0_37.i(), v0_37.j(), 20);
        this.r = v1_0.l();
        this.getWindow().getDecorView().setSystemUiVisibility(1280);
        super.onCreate(p13);
        p9.U.a(this.getWindow(), 0);
        this.getWindow().setStatusBarColor(0);
        this.getWindow().setNavigationBarColor(0);
        int v13_28 = this.getIntent().getStringExtra(wmoi.igob.ggcn(6664));
        int v2_0 = "";
        if (v13_28 == 0) {
            v13_28 = "";
        }
        this.t = v13_28;
        try {
            int v13_32 = nY.h.b(this.t, new vn.com.zalopay.auth.presentation.faceauth.FaceAuthActivity$getExtraParamRequest$type$1().getType());
        } catch (tS.m v0_45) {
            ck.b.b(v0_45);
            v13_32 = 0;
        }
        androidx.lifecycle.A v4_1;
        int v13_34 = ((java.util.Map) v13_32);
        tS.m v0_1 = wmoi.igob.ggcn(6665);
        if (v13_34 == 0) {
            v4_1 = 0;
        } else {
            v4_1 = v13_34.get(v0_1);
        }
        androidx.lifecycle.A v4_2;
        if (!(v4_1 instanceof String)) {
            v4_2 = 0;
        } else {
            v4_2 = ((String) v4_1);
        }
        if (v4_2 == null) {
            v4_2 = String.valueOf(this.getIntent().getIntExtra(v0_1, 0));
        }
        uS.a v1_1;
        this.w = v4_2;
        tS.m v0_3 = wmoi.igob.ggcn(6666);
        if (v13_34 == 0) {
            v1_1 = 0;
        } else {
            v1_1 = v13_34.get(v0_3);
        }
        uS.a v1_2;
        if (!(v1_1 instanceof String)) {
            v1_2 = 0;
        } else {
            v1_2 = ((String) v1_1);
        }
        if (v1_2 == null) {
            v1_2 = this.getIntent().getStringExtra(v0_3);
            if (v1_2 == null) {
                v1_2 = "";
            }
        }
        uS.a v1_4;
        this.z = v1_2;
        tS.m v0_4 = wmoi.igob.ggcn(6667);
        if (v13_34 == 0) {
            v1_4 = 0;
        } else {
            v1_4 = v13_34.get(v0_4);
        }
        uS.a v1_5;
        if (!(v1_4 instanceof String)) {
            v1_5 = 0;
        } else {
            v1_5 = ((String) v1_4);
        }
        if (v1_5 != null) {
            v2_0 = v1_5;
        } else {
            tS.m v0_6 = this.getIntent().getStringExtra(v0_4);
            if (v0_6 != null) {
                v2_0 = v0_6;
            }
        }
        int v13_1;
        this.x = v2_0;
        tS.m v0_7 = wmoi.igob.ggcn(6668);
        if (v13_34 == 0) {
            v13_1 = 0;
        } else {
            v13_1 = v13_34.get(v0_7);
        }
        int v13_2;
        if (!(v13_1 instanceof String)) {
            v13_2 = 0;
        } else {
            v13_2 = ((String) v13_1);
        }
        if (v13_2 == 0) {
            v13_2 = this.getIntent().getStringExtra(v0_7);
        }
        Cs.a.b.getClass();
        this.A = d6.l.j(v13_2);
        tS.m v0_11 = this.getIntent().getIntExtra(wmoi.igob.ggcn(6669), 1);
        pS.j.b.getClass();
        this.u = jQ.B.a(v0_11);
        tS.m v0_12 = this.n;
        if (v0_12 == null) {
            v0_12 = 0;
        }
        androidx.lifecycle.A v4_6 = this.z;
        if (v4_6 == null) {
            v4_6 = 0;
        }
        this.v = new zS.a(v0_12, this.w, v4_6, this.x);
        int v13_9 = this.h1();
        tS.m v0_13 = this.v;
        if (v0_13 == null) {
            v0_13 = 0;
        }
        v13_9.k = v0_13;
        tS.m v0_15 = this.p;
        if (v0_15 == null) {
            v0_15 = 0;
        }
        int v13_11 = new tS.x(this, v0_15);
        this.s = v13_11;
        androidx.lifecycle.A v4_7 = this.z;
        if (v4_7 == null) {
            v4_7 = 0;
        }
        v13_11.h0(new tS.l(this.w, v4_7));
        if (kotlin.text.StringsKt.isBlank(this.t)) {
            androidx.lifecycle.A v4_9 = new yS.d(wmoi.igob.ggcn(6670), wmoi.igob.ggcn(6671), wmoi.igob.ggcn(6672), wmoi.igob.ggcn(6673), 0, 195);
            int v13_14 = this.s;
            if (v13_14 == 0) {
                v13_14 = 0;
            }
            v13_14.h0(new tS.n(v4_9));
            int v13_15 = this.s;
            if (v13_15 == 0) {
                v13_15 = 0;
            }
            v13_15.h0(new tS.m(uS.a.e));
        }
        int v13_16 = this.h1();
        tS.m v0_23 = this.t;
        v13_16.getClass();
        kotlinx.coroutines.BuildersKt.launch$default(androidx.lifecycle.l0.l(v13_16), kotlinx.coroutines.Dispatchers.getIO(), 0, new tS.z(v13_16, v0_23, 0, 1), 2, 0);
        int v13_17 = this.s;
        if (v13_17 == 0) {
            v13_17 = 0;
        }
        v13_17.h0(new tS.m(uS.a.b));
        kotlinx.coroutines.BuildersKt.launch$default(androidx.lifecycle.l0.i(this.getLifecycle()), 0, 0, new tS.i(this, 0, 0), 3, 0);
        k.e.a(this, new y0.b(new tS.b(this, 0), 1, 882997626));
        kotlinx.coroutines.BuildersKt.launch$default(androidx.lifecycle.l0.i(this.getLifecycle()), 0, 0, new tS.i(this, 0, 1), 3, 0);
        return;
    }

    public final void onResume()
    {
        super.onResume();
        if (this.y) {
            this.overridePendingTransition(2130772073, 2130772077);
            this.y = 0;
        }
        return;
    }

    public final void onStart()
    {
        super.onStart();
        int v0 = this.q;
        if (v0 == 0) {
            v0 = 0;
        }
        v0.D(this);
        return;
    }

    public final void onStop()
    {
        int v0 = this.q;
        if (v0 == 0) {
            v0 = 0;
        }
        v0.G(this);
        super.onStop();
        return;
    }

    public final void onUserSessionExpiredEvent(eu.f p2)
    {
        String vtmp1 = q9.B4.a(p2.a, this);
        int v0 = this.q;
        if (v0 == 0) {
            v0 = 0;
        }
        v0.A(this, vtmp1);
        return;
    }
}
