package vn.com.zalopay.ekycpro.presentation.main;
public final class MainEKycActivity extends vn.com.vng.zalopay.core.ui.activity.BaseFeatureActivity {
    public static final synthetic int S;
    public PU.b A;
    public final O5.b B;
    public PU.b C;
    public final O5.b D;
    public PU.b E;
    public final O5.b F;
    public KG.a G;
    public KG.e H;
    public com.airbnb.lottie.network.g I;
    public Cx.h J;
    public final O5.b K;
    public Cx.h L;
    public final O5.b M;
    public Cx.h N;
    public final O5.b O;
    public ZU.e P;
    public final O5.b Q;
    public OU.c R;
    public com.android.billingclient.api.c0 j;
    public KG.a k;
    public XQ.j l;
    public final li.e m;
    public final ji.u0 n;
    public boolean o;
    public boolean p;
    public Ux.S q;
    public final O5.b r;
    public pV.w s;
    public final O5.b t;
    public pV.w u;
    public final O5.b v;
    public MV.U w;
    public final O5.b x;
    public BV.Y0 y;
    public final O5.b z;

    public MainEKycActivity()
    {
        this.m = gi.L.a(kotlinx.coroutines.Dispatchers.getDefault().plus(gi.N.c()));
        this.n = ji.j0.c(Boolean.FALSE);
        kotlin.reflect.d v2_0 = kotlin.jvm.internal.M.a;
        this.r = new O5.b(v2_0.b(xV.l), new vV.t(this, 13), new vV.c(this, 5), new vV.t(this, 19));
        this.t = new O5.b(v2_0.b(tV.t), new vV.t(this, 20), new vV.c(this, 22), new vV.t(this, 21));
        this.v = new O5.b(v2_0.b(pV.v), new vV.t(this, 22), new vV.h(this, 0), new vV.t(this, 23));
        this.x = new O5.b(v2_0.b(MV.T), new vV.t(this, 0), new vV.h(this, 7), new vV.t(this, 1));
        this.z = new O5.b(v2_0.b(ZV.d), new vV.t(this, 2), new vV.h(this, 10), new vV.t(this, 3));
        this.B = new O5.b(v2_0.b(PU.a), new vV.t(this, 4), new vV.h(this, 11), new vV.t(this, 5));
        this.D = new O5.b(v2_0.b(VV.a), new vV.t(this, 6), new vV.c(this, 0), new vV.t(this, 7));
        this.F = new O5.b(v2_0.b(VV.h), new vV.t(this, 8), new vV.c(this, 1), new vV.t(this, 9));
        this.K = new O5.b(v2_0.b(hW.m), new vV.t(this, 10), new vV.c(this, 2), new vV.t(this, 11));
        this.M = new O5.b(v2_0.b(jW.f), new vV.t(this, 12), new vV.c(this, 3), new vV.t(this, 14));
        this.O = new O5.b(v2_0.b(bW.e), new vV.t(this, 15), new vV.c(this, 7), new vV.t(this, 16));
        this.Q = new O5.b(v2_0.b(ZU.d), new vV.t(this, 17), new vV.c(this, 14), new vV.t(this, 18));
        return;
    }

    public static final Object h1(vn.com.zalopay.ekycpro.presentation.main.MainEKycActivity p7, wg.c p8)
    {
        int v0_1;
        p7.getClass();
        if (!(p8 instanceof vV.j)) {
            v0_1 = new vV.j(p7, p8);
        } else {
            v0_1 = ((vV.j) p8);
            Object v1_2 = ((vV.j) p8).f;
            if ((v1_2 & -2147483648) == 0) {
            } else {
                ((vV.j) p8).f = (v1_2 - -2147483648);
            }
        }
        Object v8_1 = v0_1.d;
        Object v1_1 = vg.i.getCOROUTINE_SUSPENDED();
        KG.c v2_0 = v0_1.f;
        if (v2_0 == null) {
            kotlin.ResultKt.throwOnFailure(v8_1);
            if (!p7.o) {
                if (!p7.p) {
                    Object v8_4 = p7.R;
                    if (v8_4 == null) {
                        v8_4 = 0;
                    }
                    Object v8_5 = v8_4.k;
                    if (!kotlin.text.StringsKt.isBlank(v8_5)) {
                        p7.p = 1;
                        int v4_1 = p7.H;
                        if (v4_1 == 0) {
                            v4_1 = 0;
                        }
                        int v5 = p7.G;
                        if (v5 == 0) {
                            v5 = 0;
                        }
                        v0_1.f = 1;
                        v4_1.getClass();
                        v8_1 = v4_1.a(v8_5, new AA.j(v5, p7, 0, 20), new KG.c(v5, p7, 0), v0_1);
                        if (v8_1 == v1_1) {
                            return v1_1;
                        }
                    } else {
                        return Boolean.FALSE;
                    }
                } else {
                    return Boolean.FALSE;
                }
            } else {
                return Boolean.FALSE;
            }
        } else {
            if (v2_0 != 1) {
                throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
            } else {
                kotlin.ResultKt.throwOnFailure(v8_1);
            }
        }
        Object v8_7 = ((Boolean) v8_1);
        if (!v8_7.booleanValue()) {
            p7.p = 0;
        }
        return v8_7;
    }

    public static void k1(vn.com.zalopay.ekycpro.presentation.main.MainEKycActivity p3, hV.a p4, hV.a p5, String p6, int p7)
    {
        sN.f v1_0 = 0;
        if ((p7 & 2) != 0) {
            p5 = 0;
        }
        if ((p7 & 4) != 0) {
            p6 = "";
        }
        if ((p7 & 8) != 0) {
            sN.f v0_5;
            String v7_4 = ((xV.f) p3.m1().h0()).d();
            if (!((xV.f) p3.m1().h0()).o()) {
                v0_5 = v7_4.i();
            } else {
                v0_5 = v7_4.d();
            }
            if ((!kotlin.text.StringsKt.isBlank(v7_4.r())) && (!v0_5.isEmpty())) {
                String v7_5 = v7_4.r();
                sN.f v0_6 = ((Iterable) v0_5);
                sN.f v1_2 = new java.util.ArrayList(kotlin.collections.F.collectionSizeOrDefault(v0_6, 10));
                sN.f v0_8 = v0_6.iterator();
                while (v0_8.hasNext()) {
                    v1_2.add(((cW.b) v0_8.next()).a);
                }
                v1_0 = new sN.f(v7_5, v1_2);
            }
        }
        p3.j1(p4, p5, p6, v1_0);
        return;
    }

    public final java.util.LinkedHashMap i1(sN.o p7)
    {
        java.util.Iterator v7_20;
        if (p7 != null) {
            v7_20 = vV.i.a[p7.ordinal()];
        } else {
            v7_20 = -1;
        }
        java.util.Iterator v7_4;
        java.util.ArrayList v0_7 = 1;
        if (v7_20 == 1) {
            v7_4 = "processing";
        } else {
            if (v7_20 == 2) {
                v7_4 = "auto_approve";
            } else {
                if (v7_20 == 3) {
                    v7_4 = "auto_reject";
                } else {
                    if (v7_20 == 4) {
                        v7_4 = "cancel";
                    } else {
                        v7_4 = 0;
                    }
                }
            }
        }
        int v1_3 = new java.util.LinkedHashMap();
        if (v7_4 != null) {
            v1_3.put("result", v7_4);
        }
        this.m1().getClass();
        if (android.nfc.NfcAdapter.getDefaultAdapter(this) == null) {
            v0_7 = 0;
        }
        v1_3.put("nfc_support", String.valueOf(v0_7));
        v1_3.put("vneid_installed", Boolean.valueOf(H.b.h(this)));
        nY.h.d(((xV.f) this.m1().i0().b.getValue()).d());
        java.util.Iterator v7_33 = ((xV.f) this.m1().i0().b.getValue()).d().e();
        if (v7_33 != null) {
            java.util.Iterator v7_34 = v7_33.f();
            if (v7_34 != null) {
                java.util.Iterator v7_35 = ((Iterable) v7_34);
                java.util.ArrayList v0_11 = new java.util.ArrayList(kotlin.collections.F.collectionSizeOrDefault(v7_35, 10));
                java.util.Iterator v7_36 = v7_35.iterator();
                while (v7_36.hasNext()) {
                    v0_11.add(((GU.b) v7_36.next()).name().toLowerCase(java.util.Locale.ROOT));
                }
                java.util.ArrayList v0_14;
                java.util.Iterator v7_37 = v0_11.toString();
                if (v7_37 == null) {
                    v0_14 = kotlin.collections.E.emptyList();
                } else {
                    java.util.Iterator v7_38 = kotlin.text.StringsKt.w(v7_37);
                    if (v7_38 == null) {
                    } else {
                        java.util.Iterator v7_39 = kotlin.text.StringsKt.y(v7_38, new String[] {","}), 0, 6);
                        if (v7_39 == null) {
                        } else {
                            java.util.Iterator v7_40 = ((Iterable) v7_39);
                            v0_14 = new java.util.ArrayList(kotlin.collections.F.collectionSizeOrDefault(v7_40, 10));
                            java.util.Iterator v7_1 = v7_40.iterator();
                            while (v7_1.hasNext()) {
                                v0_14.add(kotlin.text.StringsKt.trim(((String) v7_1.next())).toString());
                            }
                        }
                    }
                }
                v1_3.put("bca_options", v0_14);
            }
        }
        java.util.Iterator v7_7;
        java.util.Iterator v7_5 = this.m1().h0();
        if (v7_5 != null) {
            try {
                v7_7 = com.google.gson.JsonParser.parseString(aV.b.a.toJson(v7_5));
            } catch (java.util.Iterator v7_11) {
                System.out.println(androidx.camera.camera2.internal.G.e("JsonUtils: Invalid JSON syntax \u2192 ", v7_11.getMessage()));
                v7_7 = com.google.gson.JsonNull.INSTANCE;
            } catch (java.util.Iterator v7_8) {
                System.out.println(androidx.camera.camera2.internal.G.e("JsonUtils: Failed to convert \u2192 ", v7_8.getMessage()));
                v7_7 = com.google.gson.JsonNull.INSTANCE;
            }
        } else {
            v7_7 = com.google.gson.JsonNull.INSTANCE;
        }
        v1_3.put("extra_info", v7_7);
        return v1_3;
    }

    public final void j1(hV.a p12, hV.a p13, String p14, sN.f p15)
    {
        kotlinx.coroutines.BuildersKt.launch$default(androidx.lifecycle.l0.i(this.getLifecycle()), kotlinx.coroutines.Dispatchers.getMain(), 0, new h0.w(p12, p13, this, p14, p15, 0, 17), 2, 0);
        return;
    }

    public final ZU.d l1()
    {
        return ((ZU.d) this.Q.getValue());
    }

    public final xV.l m1()
    {
        return ((xV.l) this.r.getValue());
    }

    public final bW.e n1()
    {
        return ((bW.e) this.O.getValue());
    }

    public final void onCreate(android.os.Bundle p30)
    {
        vV.n v0_1;
        void v29_1 = super.onCreate(p30);
        this.getWindow().addFlags(128);
        vn.com.vng.zalopay.core.ui.activity.BaseFeatureActivity.e1(this);
        if (p30 != null) {
            v0_1 = p30;
        } else {
            v0_1 = this.getIntent().getExtras();
        }
        vV.n v0_2;
        if (v0_1 == null) {
            v0_2 = 0;
        } else {
            v0_2 = ((OU.c) v0_1.getParcelable("EKYC_PRP_REQUEST_ARGS"));
        }
        if (v0_2 != null) {
            this.R = v0_2;
            vV.n v0_4 = n9.W2.a;
            if (v0_4 == null) {
                throw new IllegalStateException("EKycProPlugin is not installed. Call install() first.");
            } else {
                int v8_2 = new wU.a(new AU.b(v0_4), new s5.g(), new s5.f(), new com.airbnb.lottie.network.f(1, v0_4));
                this.j = new com.android.billingclient.api.c0(v0_4.h(), v0_4.b());
                androidx.lifecycle.x v1_35 = v0_4.b();
                p9.n7.c(v1_35);
                this.k = v1_35;
                androidx.lifecycle.x v1_37 = new XQ.j(13, 0);
                v1_37.b = ji.j0.c(cW.b.d);
                this.l = v1_37;
                androidx.lifecycle.x v1_38 = v8_2.b;
                v1_38.get();
                int v6_12 = v8_2.f;
                int v11_7 = v8_2.g;
                dagger.internal.o v17 = v11_7;
                this.q = new Ux.S(((KU.b) v6_12.get()), ((aV.d) v11_7.get()), ((cW.a) v8_2.h.get()), v8_2.a(), ((Ns.d) v1_38.get()), v0_4.h(), 3);
                this.s = new pV.w(((KU.b) v6_12.get()), v0_4.h(), ((Ns.d) v1_38.get()), v8_2.a(), 1);
                this.u = new pV.w(((KU.b) v6_12.get()), v0_4.h(), ((Ns.d) v1_38.get()), v8_2.a(), 0);
                this.w = new MV.U(((NU.j) v8_2.i.get()), new l9.w(), ((NU.h) v8_2.j.get()), ((NU.g) v8_2.k.get()), ((NU.l) v8_2.l.get()), ((NU.e) v8_2.m.get()), ((NU.i) v8_2.n.get()), ((NU.k) v8_2.o.get()), new MV.m(v0_4.h(), ((KU.b) v6_12.get()), ((aV.d) v8_2.p.get()), ((aV.d) v8_2.q.get()), ((aV.d) v8_2.r.get()), ((KU.f) v8_2.s.get())), ((Ns.d) v1_38.get()));
                this.y = new BV.Y0(((KU.b) v6_12.get()), ((aV.d) v17.get()), ((aV.d) v8_2.t.get()), ((aV.d) v8_2.u.get()), ((Ns.d) v1_38.get()));
                this.A = new PU.b(((KU.b) v6_12.get()), 0);
                this.C = new PU.b(((KU.b) v6_12.get()), 1);
                this.E = new PU.b(((KU.b) v6_12.get()), 2);
                androidx.lifecycle.x v1_8 = v0_4.b();
                p9.n7.c(v1_8);
                this.G = v1_8;
                this.H = new KG.e();
                this.I = new com.airbnb.lottie.network.g(v0_4.i(), v0_4.j(), 20);
                this.J = new Cx.h(13, ((KU.h) v8_2.w.get()));
                this.L = new Cx.h(14, ((KU.g) v8_2.y.get()));
                this.N = new Cx.h(10, ((KU.a) v8_2.z.get()));
                this.P = new ZU.e(((KU.b) v6_12.get()), v8_2.a(), 0);
                vV.n v0_16 = this.R;
                if (v0_16 == null) {
                    v0_16 = 0;
                }
                java.util.Objects.toString(v0_16);
                if (com.google.android.gms.internal.mlkit_vision_barcode.Y.a == null) {
                    com.google.android.gms.internal.mlkit_vision_barcode.Y.a = new AV.b();
                }
                vV.n v0_20 = this.j;
                if (v0_20 == null) {
                    v0_20 = 0;
                }
                v0_20.B(this, new vV.c(this, 4), new vA.b(7), new vV.b(androidx.lifecycle.A v4_0, 2));
                k.e.a(this, new y0.b(new vV.b(this, 0), 1, 969966457));
                xV.l v2_9 = this.l1();
                androidx.lifecycle.x v1_30 = this.getLifecycle();
                kotlinx.coroutines.BuildersKt.launch$default(androidx.lifecycle.l0.i(v1_30), 0, 0, new vV.n(v1_30, v2_9, 0, this, 1), 3, 0);
                xV.l v2_10 = v29_1.m1();
                androidx.lifecycle.x v1_31 = v29_1.getLifecycle();
                kotlinx.coroutines.BuildersKt.launch$default(androidx.lifecycle.l0.i(v1_31), 0, 0, new vV.n(v1_31, v2_10, 0, v29_1, 0), 3, 0);
                return;
            }
        } else {
            this.g1();
            this.finish();
            return;
        }
    }

    public final void onPause()
    {
        YW.l.e(this);
        super.onPause();
        return;
    }

    public final void onResume()
    {
        super.onResume();
        YW.l.f(this, 0, new qK.a(23));
        return;
    }

    public final void onSaveInstanceState(android.os.Bundle p3)
    {
        super.onSaveInstanceState(p3);
        int v0 = this.R;
        if (v0 != 0) {
            if (v0 == 0) {
                v0 = 0;
            }
            p3.putParcelable("EKYC_PRP_REQUEST_ARGS", v0);
        }
        return;
    }

    public final void onStart()
    {
        super.onStart();
        int v0 = this.I;
        if (v0 == 0) {
            v0 = 0;
        }
        v0.D(this);
        return;
    }

    public final void onStop()
    {
        int v0 = this.I;
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
        int v0 = this.I;
        if (v0 == 0) {
            v0 = 0;
        }
        v0.A(this, vtmp1);
        return;
    }
}
