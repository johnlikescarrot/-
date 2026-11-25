package vn.com.vng.zalopay.scanqr.presentation.v2;
public final class ScanQRActivityV2 extends androidx.activity.ComponentActivity {
    public static final synthetic int u;
    public KG.a i;
    public sm.a j;
    public dagger.e k;
    public dagger.e l;
    public c6.a m;
    public final O5.b n;
    public final O5.b o;
    public final q0.N0 p;
    public kotlinx.coroutines.Job q;
    public final androidx.activity.result.d r;
    public final androidx.activity.result.d s;
    public final androidx.activity.result.d t;

    public ScanQRActivityV2()
    {
        int v2_2 = kotlin.jvm.internal.M.a;
        this.n = new O5.b(v2_2.b(nM.Z), new nM.s(this, 0), new nM.a(this, 0), new nM.s(this, 1));
        this.o = new O5.b(v2_2.b(IL.v), new nM.s(this, 2), new nM.a(this, 2), new nM.s(this, 3));
        this.p = q0.d.G(Boolean.FALSE, q0.D0.e);
        this.r = this.registerForActivityResult(new androidx.fragment.app.f0(3), new nM.d(this, 0));
        this.s = this.registerForActivityResult(new androidx.fragment.app.f0(6), new nM.d(this, 1));
        this.t = this.registerForActivityResult(new androidx.fragment.app.f0(4), new nM.d(this, 2));
        return;
    }

    public static final void b1(vn.com.vng.zalopay.scanqr.presentation.v2.ScanQRActivityV2 p4)
    {
        String v0_4 = java.util.TimeZone.getDefault().getID();
        qM.r v1_4 = ((String) uM.a.a.get(v0_4));
        if (v1_4 == null) {
            v1_4 = "";
        }
        if (!kotlin.text.StringsKt.isBlank(v1_4)) {
            p4.c1().g0(new qM.y(v1_4, qM.i.b));
            p4.c1().g0(new qM.v(0));
            p4.g1(v1_4, "timezone");
            return;
        } else {
            p4.c1().g0(new qM.r(v0_4));
            return;
        }
    }

    public final nM.Z c1()
    {
        return ((nM.Z) this.n.getValue());
    }

    public final void e1(qM.b p14)
    {
        Object[] v1_0 = p14.a;
        int v0_2 = p14.d.ordinal();
        android.graphics.Bitmap v3 = 0;
        if (v0_2 == 0) {
            android.graphics.drawable.Drawable v14_11 = this.i;
            if (v14_11 != null) {
                v3 = v14_11;
            }
            v3.k(this, v1_0, 0);
            return;
        } else {
            if (v0_2 == 3) {
                android.graphics.drawable.Drawable v14_1 = this.j;
                if (v14_1 == null) {
                    v14_1 = 0;
                }
                android.content.Intent v10 = v14_1.a(this);
                String v8 = this.getString(2131951835);
                android.graphics.drawable.Drawable v14_4 = this.getResources().getDrawable(2131234904, 0);
                if (v14_4 != null) {
                    v3 = n9.i4.a(v14_4);
                }
                q9.p5.a(this, 0, v8, v3, v10, new nM.c(this, 1), 3);
                return;
            } else {
                int v0_3 = android.net.Uri.parse(v1_0);
                if (nM.e.a[p14.d.ordinal()] != 1) {
                    android.graphics.drawable.Drawable v14_8 = this.i;
                    if (v14_8 != null) {
                        v3 = v14_8;
                    }
                    v3.s(this, v0_3);
                    return;
                } else {
                    android.graphics.drawable.Drawable v14_9 = this.i;
                    if (v14_9 != null) {
                        v3 = v14_9;
                    }
                    v3.g(this, v0_3);
                    return;
                }
            }
        }
    }

    public final void g1(String p4, String p5)
    {
        this.c1().g0(new qM.B("01.4604.015", kotlin.collections.a0.mapOf(new kotlin.Pair[] {kotlin.TuplesKt.to("region", p4), kotlin.TuplesKt.to("detectedBy", p5)}))));
        return;
    }

    public final void onCreate(android.os.Bundle p32)
    {
        void v31_1 = super.onCreate(p32);
        if (android.os.Build$VERSION.SDK_INT >= 34) {
            this.overrideActivityTransition(0, 2130772074, 0);
            this.overrideActivityTransition(1, 0, 2130772076);
        }
        CA.X v3_0;
        androidx.activity.q.a(this, 0, 3);
        WJ.J v1_1 = this.getIntent().getExtras();
        if (v1_1 == null) {
            v3_0 = 0;
        } else {
            v3_0 = v1_1.getInt("needResult");
        }
        WJ.J v1_3;
        if (v1_1 == null) {
            v1_3 = "scanqr";
        } else {
            v1_3 = v1_1.getString("scanMode");
            if (v1_3 == null) {
            }
        }
        int v6_8 = n9.P3.a;
        w5.c v11 = n9.P3.c;
        String v5_16 = new com.google.common.reflect.v(v6_8, n9.P3.b, v11, 29);
        int v7_8 = new la.f();
        gM.a v14_8 = new gM.c();
        int v13_8 = new w5.c();
        gM.a v23_2 = new CL.b();
        int v15_19 = new gM.a(v5_16, 2);
        int v10_11 = new gM.a(v5_16, 8);
        int v12_7 = new gM.a(v5_16, 1);
        AU.d v22_3 = v15_19;
        int v15_21 = new gM.a(v5_16, 4);
        int v24_1 = new gM.a(v5_16, 13);
        gM.a v25 = new gM.a(v5_16, 10);
        gM.a v26 = new gM.a(v5_16, 5);
        Yt.w v27 = new Yt.w(new gM.a(v5_16, 14), 16);
        gM.a v18_6 = new gM.a(v5_16, 3);
        dagger.internal.o v19_6 = new gM.a(v5_16, 6);
        gM.a v17_7 = v12_7;
        WJ.J v8_25 = v18_6;
        int v12_8 = v19_6;
        gM.a v28_2 = v17_7;
        androidx.lifecycle.A v0_67 = v13_8;
        gM.a v21_0 = new gM.a(v5_16, 15);
        int v9_0 = v22_3;
        AU.d v22_1 = v15_21;
        CL.b v29 = v23_2;
        gM.a v23_0 = v21_0;
        gM.a v17_2 = dagger.internal.f.b(new CL.d(v14_8, v15_21, v24_1, v25, v27, v26, new AU.d(v17_7, v18_6, v19_6, new gM.a(v5_16, 9), 14), v21_0, 10));
        int v13_2 = new gM.a(v5_16, 12);
        gM.a v21_1 = v8_25;
        dagger.internal.o v19_1 = v9_0;
        WJ.J v8_1 = new gM.a(v5_16, 11);
        int v15_4 = v10_11;
        int v10_1 = v13_2;
        CL.b v16_2 = v28_2;
        gM.a v28_1 = v16_2;
        gM.a v30 = v15_4;
        gM.a v17_3 = dagger.internal.f.b(new eH.e(v7_8, dagger.internal.f.b(new eH.e(v14_8, dagger.internal.f.b(new gM.e(v14_8, v15_4, v16_2, v17_2, dagger.internal.f.b(new gM.d(v14_8, v13_2, v8_1)))), 10)), 9));
        int v7_1 = new gM.a(v5_16, 0);
        int v9_8 = new AU.d(v19_1, v17_3, v7_1, new CU.b(v12_8, new Yt.i(20, v5_16), 27), 15);
        CL.b v16_6 = v29;
        String v5_0 = v16_6;
        String v5_2 = dagger.internal.f.b(new AA.C(v0_67, dagger.internal.f.b(new AA.C(v5_0, dagger.internal.f.b(new CL.e(v16_6, v30, v28_1, dagger.internal.f.b(new CL.d(v16_6, v22_1, v24_1, v25, v27, v26, new AU.d(v28_1, v21_1, v12_8, new gM.a(v5_16, 7), 3), v23_0, 0)), dagger.internal.f.b(new CL.c(v5_0, v10_1, v8_1)))), 21)), 24));
        String v5_4 = new AC.b(dagger.internal.f.b(new AA.C(v0_67, v5_2, 22)), dagger.internal.f.b(new AA.C(v0_67, v5_2, 23)), v7_1, 4);
        androidx.lifecycle.A v0_6 = ((KG.a) ((dagger.internal.o) v6_8.j).get());
        p9.n7.c(v0_6);
        this.i = v0_6;
        p9.n7.c(v11);
        this.j = v11;
        this.k = dagger.internal.f.a(v9_8);
        this.l = dagger.internal.f.a(v5_4);
        v6_8.D();
        androidx.lifecycle.A v0_9 = v6_8.getContext();
        v6_8.D();
        com.google.android.gms.location.i.a(v0_9);
        String v5_6 = ((w5.c) v6_8.k);
        int v6_2 = ((CC.k) v6_8.b).x();
        v5_6.getClass();
        this.m = new c6.a(29, new androidx.media3.extractor.mp4.g(11, v6_2));
        androidx.lifecycle.A v0_12 = this.c1();
        WJ.J v1_2 = v1_3.toLowerCase(java.util.Locale.ROOT);
        int v6_5 = qM.k.a;
        if ((!kotlin.jvm.internal.Intrinsics.areEqual(v1_2, "scanqr")) && (kotlin.jvm.internal.Intrinsics.areEqual(v1_2, "scanai"))) {
            v6_5 = qM.j.a;
        }
        CA.X v3_2;
        v0_12.g0(new qM.z(v6_5));
        androidx.lifecycle.A v0_13 = this.c1();
        if (v3_0 != 1) {
            v3_2 = 0;
        } else {
            v3_2 = 1;
        }
        v0_13.g0(new qM.x(v3_2));
        this.c1().g0(qM.q.a);
        kotlinx.coroutines.BuildersKt.launch$default(androidx.lifecycle.l0.i(this.getLifecycle()), 0, 0, new nM.m(this, 0, 2), 3, 0);
        k.e.a(this, new y0.b(new nM.b(this, 2), 1, -850332476));
        ji.j0.z(new CA.X(new WJ.J(this.c1().l, 8), new IL.a(2, this, vn.com.vng.zalopay.scanqr.presentation.v2.ScanQRActivityV2, "navigateToMiniApp", "navigateToMiniApp(Lvn/com/vng/zalopay/scanqr/presentation/v2/model/Destination$DeepLink;)V", 4, 7), 9), androidx.lifecycle.l0.i(v31_1.getLifecycle()));
        void v2_1 = v31_1;
        ji.j0.z(new CA.X(new WJ.J(v31_1.c1().l, 9), new IL.a(2, v2_1, vn.com.vng.zalopay.scanqr.presentation.v2.ScanQRActivityV2, "navigateToAliMiniApp", "navigateToAliMiniApp(Lvn/com/vng/zalopay/scanqr/presentation/v2/model/Destination$AliDeepLink;)V", 4, 8), 9), androidx.lifecycle.l0.i(v2_1.getLifecycle()));
        ji.j0.z(new CA.X(new WJ.J(v2_1.c1().l, 10), new nM.l(v2_1, 0, 0), 9), androidx.lifecycle.l0.i(v2_1.getLifecycle()));
        ji.j0.z(new CA.X(new WJ.J(v2_1.c1().n, 11), new nM.m(v2_1, 0, 0), 9), androidx.lifecycle.l0.i(v2_1.getLifecycle()));
        ji.j0.z(new CA.X(new WJ.J(v2_1.c1().n, 12), new IL.a(2, v2_1, vn.com.vng.zalopay.scanqr.presentation.v2.ScanQRActivityV2, "getLocalBanner", "getLocalBanner(Lvn/com/vng/zalopay/scanqr/presentation/v2/model/ScanQRUiEvent$GetLocalBanner;)V", 4, 9), 9), androidx.lifecycle.l0.i(v2_1.getLifecycle()));
        ji.j0.z(new CA.X(new WJ.J(v2_1.c1().n, 13), new nM.m(v2_1, 0, 1), 9), androidx.lifecycle.l0.i(v2_1.getLifecycle()));
        return;
    }

    public final void onPause()
    {
        super.onPause();
        if (android.os.Build$VERSION.SDK_INT < 34) {
            this.overridePendingTransition(0, 2130772076);
        }
        return;
    }

    public final void onResume()
    {
        super.onResume();
        if (android.os.Build$VERSION.SDK_INT < 34) {
            this.overridePendingTransition(2130772074, 0);
        }
        this.c1().g0(qM.t.a);
        return;
    }
}
