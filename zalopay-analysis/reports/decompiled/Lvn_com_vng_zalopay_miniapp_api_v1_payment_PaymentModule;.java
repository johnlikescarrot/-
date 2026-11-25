package vn.com.vng.zalopay.miniapp.api.v1.payment;
public final class PaymentModule implements LR.a {
    public static final gF.a A = None;
    public static final int B = 8;
    public static final int C = 18;
    public static final String D = "order_token";
    private final vn.com.vng.zalopay.navigation.b a;
    private final nY.p b;
    private final wL.l c;
    private final Wo.d d;
    private final fw.b e;
    private final iw.a f;
    private final Is.a g;
    private final Pt.n h;
    private final Yv.i i;
    private final St.a j;
    public ref.WeakReference k;
    private XQ.a l;
    private Ew.H m;
    private final androidx.lifecycle.T n;
    private final androidx.lifecycle.T o;
    private final androidx.lifecycle.T p;
    private final androidx.lifecycle.T q;
    private final androidx.lifecycle.T r;
    private final androidx.lifecycle.T s;
    private final androidx.lifecycle.T t;
    private final androidx.lifecycle.T u;
    private final androidx.lifecycle.T v;
    private String w;
    private final java.util.Map x;
    private L9.p y;
    private sq.r z;

    static PaymentModule()
    {
        vn.com.vng.zalopay.miniapp.api.v1.payment.PaymentModule.A = new gF.a();
        return;
    }

    public PaymentModule(vn.com.vng.zalopay.navigation.b p1, nY.p p2, wL.l p3, Wo.d p4, fw.b p5, iw.a p6, Is.a p7, Pt.n p8, Yv.i p9, St.a p10)
    {
        this.a = p1;
        this.b = p2;
        this.c = p3;
        this.d = p4;
        this.e = p5;
        this.f = p6;
        this.g = p7;
        this.h = p8;
        this.i = p9;
        this.j = p10;
        this.n = new androidx.lifecycle.T();
        this.o = new androidx.lifecycle.T();
        this.p = new androidx.lifecycle.T();
        this.q = new androidx.lifecycle.T();
        this.r = new androidx.lifecycle.T();
        this.s = new androidx.lifecycle.T();
        this.t = new androidx.lifecycle.T();
        this.u = new androidx.lifecycle.T();
        this.v = new androidx.lifecycle.T();
        this.w = "";
        this.x = new java.util.LinkedHashMap();
        return;
    }

    private static final void A(vn.com.vng.zalopay.miniapp.api.v1.payment.PaymentModule p8, android.view.View p9)
    {
        String v9_2 = ((androidx.appcompat.app.AppCompatActivity) p8.C().get());
        if (v9_2 != null) {
            UR.a.d("01.4601.002");
            w5.c v1_0 = new gF.b(p8, v9_2);
            sM.c v8_2 = new sM.c();
            v8_2.H = v1_0;
            v8_2.I = tM.e.a;
            androidx.fragment.app.n0 v0_0 = q9.a5.b();
            w5.c v1_2 = new w5.c();
            dagger.internal.f.b(new WH.e(18, new LQ.a(v0_0, 21)));
            dagger.internal.f.b(new eM.b(v1_2, 0));
            dagger.internal.f.b(new eM.b(v1_2, 5));
            dagger.internal.o v2_8 = dagger.internal.f.b(new eM.b(v1_2, 2));
            dagger.internal.o v3_8 = dagger.internal.f.b(new eM.b(v1_2, 1));
            dagger.internal.o v4_4 = dagger.internal.f.b(new eM.b(v1_2, 3));
            dagger.internal.f.b(new eM.b(v1_2, 4));
            dagger.internal.f.b(new AC.c(v1_2, new CU.b(new LQ.a(v0_0, 22), new LQ.a(v0_0, 23), 8), 27));
            v8_2.J = ((String) v2_8.get());
            v8_2.K = ((String) v3_8.get());
            v8_2.L = ((String) v4_4.get());
            v8_2.v1(v9_2.getSupportFragmentManager(), v9_2.getLocalClassName());
        }
        return;
    }

    private static final org.json.JSONObject A0(String p3, org.json.JSONObject p4)
    {
        org.json.JSONObject v0_1 = new org.json.JSONObject();
        v0_1.put("payment_event", "PAYMENT_COMPLETED");
        org.json.JSONObject v1_2 = new org.json.JSONObject();
        v1_2.put("previous_event", p3);
        v1_2.put("data", p4);
        v0_1.put("data", v1_2);
        return v0_1;
    }

    private final void B0(java.util.List p8, MR.b p9)
    {
        int v0_3 = ((androidx.appcompat.app.AppCompatActivity) this.C().get());
        if (v0_3 != 0) {
            kotlinx.coroutines.BuildersKt.launch$default(androidx.lifecycle.l0.i(v0_3.getLifecycle()), 0, 0, new gF.i(p8, p9, 0, 1), 3, 0);
        }
        return;
    }

    private static final org.json.JSONObject C0(sq.e p4)
    {
        org.json.JSONObject v0_1 = new org.json.JSONObject();
        v0_1.put("app_id", p4.a);
        v0_1.put("trans_id", p4.e);
        v0_1.put("app_trans_id", p4.b);
        v0_1.put("status", p4.c);
        return v0_1;
    }

    private final void D0(androidx.appcompat.app.AppCompatActivity p8, wL.f p9, MR.b p10)
    {
        kotlinx.coroutines.BuildersKt.launch$default(androidx.lifecycle.l0.j(p8), 0, 0, new b0.x(p9, this, p10, 0, 9), 3, 0);
        return;
    }

    private final void E(org.json.JSONObject p4, MR.b p5)
    {
        String v0_0;
        if (p4 == null) {
            v0_0 = 0;
        } else {
            v0_0 = p4.optString("id");
        }
        if (v0_0 == null) {
            v0_0 = "";
        }
        String v0_1 = ((String) this.x.get(v0_0));
        UR.a.e("01.1103.999", kotlin.collections.a0.mapOf(new kotlin.Pair[] {kotlin.TuplesKt.to("event", "getCashierWebData"), kotlin.TuplesKt.to("params", String.valueOf(p4)), kotlin.TuplesKt.to("data", v0_1)})));
        if (p5 != null) {
            p5.f(v0_1);
        }
        return;
    }

    private final void F0(Ew.c p3, OH.g p4, MR.b p5)
    {
        com.google.android.gms.internal.mlkit_vision_barcode.y6.c(p3.a(), p3.b());
        if ((p3.a() != -117) && (p3.a() != -161)) {
            if (p5 != null) {
                try {
                    p5.f(new org.json.JSONObject(nY.h.d(p3)));
                } catch (Throwable v3_2) {
                    Object[] v1_2 = new Object[0];
                    ck.b.c(v3_2, "Exception while parsing binding response", v1_2);
                    p5.c(v3_2);
                    if (p4 == null) {
                        return;
                    } else {
                        p4.b();
                        return;
                    }
                } catch (Throwable v3_3) {
                    if (p4 != null) {
                        p4.b();
                    }
                    throw v3_3;
                }
            }
            if (p4 != null) {
                p4.b();
                return;
            }
        } else {
            if (p4 != null) {
                p4.c(p3.b());
            }
        }
        return;
    }

    private final void G0(MR.b p3)
    {
        if (p3 != null) {
            p3.a(-3, "Ng\u01b0\u1eddi d\u00f9ng hu\u1ef7 \u0111\u0103ng k\u00fd");
        }
        return;
    }

    private final void I0(Ew.o p3, Ew.H p4)
    {
        long v0 = p3.b;
        is.T.a(p3.d);
        p3 = Ew.m.b;
        return;
    }

    private final void K0(org.json.JSONObject p2, MR.b p3)
    {
        UR.a.e("01.1103.999", kotlin.collections.Z.mapOf(kotlin.TuplesKt.to("event", "onPageLoadCompleted")));
        if (p3 != null) {
            p3.f(0);
        }
        this.p.j(Boolean.TRUE);
        return;
    }

    private final void L0(org.json.JSONObject p3, MR.b p4)
    {
        UR.a.e("01.1103.999", kotlin.collections.Z.mapOf(kotlin.TuplesKt.to("event", "onPrePaymentCancel")));
        this.q.j(new Xs.b(sp.n.c));
        if (p4 != null) {
            p4.f(0);
        }
        return;
    }

    private final void M0(org.json.JSONObject p3, MR.b p4)
    {
        UR.a.e("01.1103.999", kotlin.collections.Z.mapOf(kotlin.TuplesKt.to("event", "onPrePaymentComplete")));
        this.q.j(new Xs.b(sp.n.a));
        if (p4 != null) {
            p4.f(0);
        }
        return;
    }

    private final L9.p N()
    {
        L9.p v0_0 = this.y;
        if (v0_0 == null) {
            L9.p v0_3 = new androidx.work.s(2);
            v0_3.b = 1;
            L9.s v6_1 = new L9.s(v0_3);
            L9.p v0_1 = vn.com.vng.zalopay.AndroidApplication.g;
            if (v0_1 == null) {
                v0_1 = 0;
            }
            v0_0 = new L9.p(v0_1.getApplicationContext(), 0, L9.t.a, v6_1, com.google.android.gms.common.api.j.c);
        }
        this.y = v0_0;
        return v0_0;
    }

    private final void N0(org.json.JSONObject p3, MR.b p4)
    {
        UR.a.e("01.1103.999", kotlin.collections.Z.mapOf(kotlin.TuplesKt.to("event", "onPrePaymentFailed")));
        this.q.j(new Xs.b(sp.n.b));
        if (p4 != null) {
            p4.f(0);
        }
        return;
    }

    private final void O0(org.json.JSONObject p2, MR.b p3)
    {
        androidx.appcompat.app.AppCompatActivity v0_2 = ((androidx.appcompat.app.AppCompatActivity) this.C().get());
        if (v0_2 != null) {
            try {
                wL.k.a(v0_2, p2);
            } catch (int v2_2) {
                if (p3 != null) {
                    p3.c(v2_2);
                }
            }
            if (p3 != null) {
                p3.f(0);
                return;
            }
        }
        return;
    }

    private final void P(org.json.JSONObject p10, MR.b p11)
    {
        UR.a.e("01.1103.999", kotlin.collections.Z.mapOf(kotlin.TuplesKt.to("event", "getPaymentData")));
        byte[] v2_2 = ((androidx.appcompat.app.AppCompatActivity) this.C().get());
        java.nio.charset.Charset v4_0 = "";
        if (v2_2 == null) {
            if (p11 != null) {
                p11.a(5, "");
            }
        } else {
            byte[] v2_3 = v2_2.getIntent();
            if (v2_3 == null) {
            } else {
                byte[] v2_4 = v2_3.getExtras();
                if (v2_4 != null) {
                    String v7_2;
                    org.json.JSONObject v5_1 = v2_4.getParcelableArrayList("order");
                    kotlin.Unit v6 = 0;
                    if (v5_1 == null) {
                        v7_2 = 0;
                    } else {
                        v7_2 = new java.util.ArrayList(kotlin.collections.F.collectionSizeOrDefault(v5_1, 10));
                        org.json.JSONObject v5_2 = v5_1.iterator();
                        while (v5_2.hasNext()) {
                            v7_2.add(com.google.android.gms.internal.mlkit_vision_barcode.o7.a(((sp.g) v5_2.next())));
                        }
                    }
                    byte[] v2_7 = ((java.util.HashMap) p9.M.b(v2_4, "options", java.util.HashMap));
                    if (v2_7 == null) {
                        v2_7 = new java.util.HashMap();
                    }
                    org.json.JSONObject v5_6;
                    org.json.JSONObject v5_5 = this.C().get();
                    if (!(v5_5 instanceof vn.com.vng.zalopay.cashier.CashierActivity)) {
                        v5_6 = 0;
                    } else {
                        v5_6 = ((vn.com.vng.zalopay.cashier.CashierActivity) v5_5);
                    }
                    org.json.JSONObject v5_7;
                    if (v5_6 == null) {
                        v5_7 = 0;
                    } else {
                        v5_7 = v5_6.j;
                    }
                    if (v5_7 != null) {
                        v4_0 = v5_7;
                    } else {
                    }
                    v2_7.put("nativeId", v4_0);
                    java.nio.charset.Charset v4_2 = new org.json.JSONArray();
                    if (v7_2 != null) {
                        org.json.JSONObject v5_9 = v7_2.iterator();
                        while (v5_9.hasNext()) {
                            v4_2.put(new org.json.JSONObject(nY.h.d(((sp.i) v5_9.next()))));
                        }
                    }
                    org.json.JSONObject v5_11 = new org.json.JSONObject();
                    v5_11.put("orders", v4_2);
                    v5_11.put("options", new org.json.JSONObject(v2_7));
                    String v10_5 = java.util.Base64.getUrlEncoder().encodeToString(v5_11.toString().getBytes(kotlin.text.Charsets.UTF_8));
                    UR.a.e("01.1103.999", kotlin.collections.a0.mapOf(new kotlin.Pair[] {kotlin.TuplesKt.to("event", "getPaymentData"), kotlin.TuplesKt.to("data", v10_5)})));
                    if (p11 == null) {
                        String v10_2 = ng.L.a(v6);
                        if ((v10_2 != null) && (p11 != null)) {
                            p11.c(v10_2);
                        }
                    } else {
                        p11.f(v10_5);
                        v6 = kotlin.Unit.INSTANCE;
                    }
                }
            }
        }
        return;
    }

    private final void P0(org.json.JSONObject p4, MR.b p5)
    {
        UR.a.e("01.1103.999", kotlin.collections.a0.mapOf(new kotlin.Pair[] {kotlin.TuplesKt.to("event", "open_tray"), kotlin.TuplesKt.to("data", String.valueOf(p4))})));
        if (p4 != 0) {
            this.u.j(new Xs.b(p4));
            return;
        } else {
            if (p5 != null) {
                p5.a(2, "Invalid parameters");
            }
            return;
        }
    }

    private final boolean Q0(org.json.JSONObject p11, MR.b p12)
    {
        if (p11 != 0) {
            String v3 = p11.optString("order_token");
            if (v3.length() != 0) {
                boolean v8 = p11.optBoolean("needthirdpartystatus");
                reflect.Type v0_2 = p11.optString("redirect_url");
                if (v0_2 == null) {
                    v0_2 = "";
                }
                sp.b v5_0 = v0_2;
                java.util.Map v6_0 = p11.optInt("completion_redirect_type");
                int v11_3 = ((java.util.Map) q9.s6.a(p11.optString("options"), new vn.com.vng.zalopay.miniapp.api.v1.payment.PaymentModule$orderTokenV2$mapType$1().getType()));
                if (v11_3 == 0) {
                    v11_3 = kotlin.collections.a0.emptyMap();
                }
                this.x0(v3, p12);
                androidx.appcompat.app.AppCompatActivity v2_1 = ((androidx.appcompat.app.AppCompatActivity) this.C().get());
                if (v2_1 != null) {
                    this.d.d(v2_1, v3, 3, new sp.b(v5_0, v6_0, 17, v8, 0), v11_3);
                }
                return 1;
            }
        }
        return 0;
    }

    private final void R(org.json.JSONObject p6, MR.b p7)
    {
        UR.a.e("01.1103.999", kotlin.collections.Z.mapOf(kotlin.TuplesKt.to("event", "getResultPageOpenData")));
        String v1_1 = ((androidx.appcompat.app.AppCompatActivity) this.C().get());
        if (v1_1 == null) {
            if (p7 != null) {
                p7.a(5, "");
            }
        } else {
            String v1_2 = v1_1.getIntent();
            if (v1_2 == null) {
            } else {
                String v1_3 = v1_2.getExtras();
                if (v1_3 != null) {
                    try {
                        Throwable v6_5;
                        String v1_8 = java.util.Base64.getUrlEncoder().encodeToString(String.valueOf(v1_3.getString("params_str")).getBytes(kotlin.text.Charsets.UTF_8));
                        UR.a.e("01.1103.999", kotlin.collections.a0.mapOf(new kotlin.Pair[] {kotlin.TuplesKt.to("event", "getResultPageOpenData"), kotlin.TuplesKt.to("data", v1_8)})));
                    } catch (Throwable v6_4) {
                        v6_5 = kotlin.ResultKt.createFailure(v6_4);
                        Throwable v6_6 = ng.L.a(v6_5);
                        if ((v6_6 != null) && (p7 != null)) {
                            p7.c(v6_6);
                        }
                    }
                    if (p7 == null) {
                        v6_5 = 0;
                    } else {
                        p7.f(v1_8);
                        v6_5 = kotlin.Unit.INSTANCE;
                    }
                }
            }
        }
        return;
    }

    private final boolean R0(org.json.JSONObject p7, MR.b p8)
    {
        if (p7) {
            Ew.n v2_1 = new Ew.n(p7);
            boolean v7_2 = v2_1.d();
            if (v7_2) {
                this.u0(v2_1, p8);
                androidx.appcompat.app.AppCompatActivity v1_1 = ((androidx.appcompat.app.AppCompatActivity) this.C().get());
                if (v1_1 != null) {
                    Wo.d.f(this.d, v1_1, v2_1, 3, 0, 24);
                }
            }
            return v7_2;
        } else {
            return 0;
        }
    }

    private final An.a S()
    {
        return new An.a();
    }

    private final boolean S0(org.json.JSONObject p14, MR.b p15)
    {
        if (p14 != null) {
            Ew.n v2_1 = new Ew.n(p14);
            boolean v6 = v2_1.d();
            boolean v11 = p14.optBoolean("needthirdpartystatus");
            Wo.d v0_0 = p14.optString("redirect_url");
            if (v0_0 == null) {
                v0_0 = "";
            }
            String v8 = v0_0;
            int v9 = p14.optInt("completion_redirect_type");
            Object v14_3 = ((java.util.Map) q9.s6.a(p14.optString("options"), new vn.com.vng.zalopay.miniapp.api.v1.payment.PaymentModule$orderTransactionV2$mapType$1().getType()));
            if (v14_3 == null) {
                v14_3 = kotlin.collections.a0.emptyMap();
            }
            java.util.Map v5 = v14_3;
            if (v6) {
                this.w0(v2_1, p15);
                androidx.appcompat.app.AppCompatActivity v1_2 = ((androidx.appcompat.app.AppCompatActivity) this.C().get());
                if (v1_2 != null) {
                    Wo.d v0_5 = this.d;
                    sp.b v4 = new sp.b;
                    v4(v8, v9, 17, v11, 0);
                    v0_5.c(v1_2, v2_1, 3, v4, v5);
                }
            }
            return v6;
        } else {
            return 0;
        }
    }

    private final void T(Ew.H p8)
    {
        int v0_3 = ((androidx.appcompat.app.AppCompatActivity) this.C().get());
        if (v0_3 != 0) {
            kotlinx.coroutines.BuildersKt.launch$default(androidx.lifecycle.l0.i(v0_3.getLifecycle()), 0, 0, new gF.c(this, p8, 0, 1), 3, 0);
        }
        return;
    }

    private final void T0(org.json.JSONObject p4, MR.b p5)
    {
        UR.a.e("01.1103.999", kotlin.collections.a0.mapOf(new kotlin.Pair[] {kotlin.TuplesKt.to("event", "resize custom block"), kotlin.TuplesKt.to("data", p4)})));
        if (p4 != null) {
            int v0_6;
            int v0_5 = kotlin.text.StringsKt.toIntOrNull(p4.get("width").toString());
            int v1_2 = 0;
            if (v0_5 == 0) {
                v0_6 = 0;
            } else {
                v0_6 = v0_5.intValue();
            }
            androidx.lifecycle.T v4_3 = kotlin.text.StringsKt.toIntOrNull(p4.get("height").toString());
            if (v4_3 != null) {
                v1_2 = v4_3.intValue();
            }
            if ((v0_6 > 0) && (v1_2 > 0)) {
                if (p5 != null) {
                    p5.f(0);
                }
                this.n.j(new Xs.b(new hF.b(v0_6, v1_2)));
            }
        }
        return;
    }

    private final void U0(org.json.JSONObject p5, MR.b p6)
    {
        int v1_2;
        UR.a.e("01.1103.999", kotlin.collections.a0.mapOf(new kotlin.Pair[] {kotlin.TuplesKt.to("event", "resize webview"), kotlin.TuplesKt.to("data", p5)})));
        int v0_2 = 0;
        if (p5 == 0) {
            v1_2 = 0;
        } else {
            v1_2 = p5.optInt("height");
        }
        if (p5 != 0) {
            v0_2 = p5.optInt("width");
        }
        if (((v1_2 <= 0) || (v0_2 <= 0)) && (p6 != null)) {
            p6.a(2, "Invalid parameters");
        }
        this.n.j(new Xs.b(new hF.b(v0_2, v1_2)));
        if (p6 != null) {
            p6.f(0);
        }
        return;
    }

    private final void V(org.json.JSONObject p2, MR.b p3)
    {
        int v2_1;
        int v2_4 = nY.h.b(String.valueOf(p2), new vn.com.vng.zalopay.miniapp.api.v1.payment.PaymentModule$handleConfirmBinding$type$1().getType());
        if (!kotlin.jvm.internal.T.f(v2_4)) {
            v2_1 = 0;
        } else {
            v2_1 = ((java.util.Map) v2_4);
        }
        if ((v2_1 != 0) && (!v2_1.isEmpty())) {
            this.c1(v2_1, p3);
            return;
        } else {
            if (p3 != null) {
                p3.a(2, "Invalid parameters");
            }
            return;
        }
    }

    private final void V0(org.json.JSONObject p9, MR.b p10)
    {
        UR.a.e("01.1103.999", kotlin.collections.a0.mapOf(new kotlin.Pair[] {kotlin.TuplesKt.to("event", "saveConfigData"), kotlin.TuplesKt.to("data", p9)})));
        if (p9 != 0) {
            java.util.Iterator v0_2 = p9.keys();
            if (v0_2 == null) {
                if (p10 != null) {
                    p10.f(0);
                }
                return;
            }
            while (v0_2.hasNext()) {
                java.util.Map v3_2 = ((String) v0_2.next());
                float v4_0 = p9.get(v3_2);
                if (!(v4_0 instanceof String)) {
                    if (!(v4_0 instanceof Integer)) {
                        if (!(v4_0 instanceof Boolean)) {
                            if (!(v4_0 instanceof Long)) {
                                if (!(v4_0 instanceof Float)) {
                                    if (!(v4_0 instanceof Double)) {
                                        UR.a.e("01.1103.999", kotlin.collections.Z.mapOf(kotlin.TuplesKt.to("saveConfigData", "unhandled data type")));
                                    } else {
                                        this.j.f(((float) ((Number) v4_0).doubleValue()), v3_2);
                                    }
                                } else {
                                    this.j.f(((Number) v4_0).floatValue(), v3_2);
                                }
                            } else {
                                this.j.c(((Number) v4_0).longValue(), v3_2);
                            }
                        } else {
                            this.j.d(v3_2, ((Boolean) v4_0).booleanValue());
                        }
                    } else {
                        this.j.b(((Number) v4_0).intValue(), v3_2);
                    }
                } else {
                    this.j.a(v3_2, ((String) v4_0));
                }
            }
        }
    }

    private final void W(org.json.JSONObject p5, MR.b p6)
    {
        if (p5 != null) {
            androidx.appcompat.app.AppCompatActivity v1_0 = p5.optString("action");
            if (v1_0 != null) {
                UR.a.e("01.1103.999", kotlin.collections.a0.mapOf(new kotlin.Pair[] {kotlin.TuplesKt.to("action", v1_0), kotlin.TuplesKt.to("data", p5)})));
                if (!kotlin.jvm.internal.Intrinsics.areEqual(v1_0, "PROCESS_PAYMENT")) {
                    if (!kotlin.jvm.internal.Intrinsics.areEqual(v1_0, "PAYMENT_INFO")) {
                        if (kotlin.jvm.internal.Intrinsics.areEqual(v1_0, "ADD_NEW_SOF")) {
                            java.util.Map v5_1 = p5.optString("sof_token");
                            if (v5_1.length() != 0) {
                                q9.H.o(v5_1, this.t);
                            }
                        }
                    } else {
                        java.util.Map v5_3 = new org.json.JSONObject();
                        v5_3.put("is_supported_multi_order", 1);
                        if (p6 != null) {
                            p6.f(v5_3);
                            return;
                        }
                    }
                } else {
                    java.util.Map v5_5;
                    this.c.b = "";
                    java.util.Map v5_4 = p5.opt("payload");
                    if (v5_4 == null) {
                        v5_5 = 0;
                    } else {
                        v5_5 = v5_4.toString();
                    }
                    java.util.Map v5_7 = ((sp.m) q9.s6.a(v5_5, sp.m));
                    if (v5_7 != null) {
                        String v0_12 = v5_7.b();
                        if (v0_12 != null) {
                            androidx.appcompat.app.AppCompatActivity v1_5 = ((Iterable) v0_12).iterator();
                            while (v1_5.hasNext()) {
                                ((sp.g) v1_5.next()).e = 3;
                            }
                            this.B0(v0_12, p6);
                            Wo.d v6_6 = this.d;
                            androidx.appcompat.app.AppCompatActivity v1_8 = ((androidx.appcompat.app.AppCompatActivity) this.C().get());
                            if (v1_8 != null) {
                                java.util.Map v5_8 = v5_7.a();
                                if (v5_8 == null) {
                                    v5_8 = kotlin.collections.a0.emptyMap();
                                }
                                v6_6.g(v1_8, v0_12, v5_8);
                                return;
                            } else {
                                return;
                            }
                        }
                    }
                    if (p6 != null) {
                        p6.a(2, "Invalid parameters");
                        return;
                    }
                }
            }
        }
        return;
    }

    private final void W0(org.json.JSONObject p3, MR.b p4)
    {
        sq.b v3_3;
        UR.a.e("01.1103.999", kotlin.collections.a0.mapOf(new kotlin.Pair[] {kotlin.TuplesKt.to("event", "sendPaymentComplete"), kotlin.TuplesKt.to("params", String.valueOf(p3))})));
        if (p3 == null) {
            v3_3 = new sq.b(0);
        } else {
            sq.b v3_1 = p3.toString();
            if (v3_1 == null) {
            } else {
                v3_3 = ((sq.b) q9.s6.a(v3_1, sq.b));
                if (v3_3 == null) {
                }
            }
        }
        this.r.j(new Xs.b(v3_3));
        return;
    }

    private final void X(org.json.JSONObject p4, MR.b p5)
    {
        if (p4 != null) {
            String v1 = p4.optString("action");
            if (v1 != null) {
                UR.a.e("01.1103.999", kotlin.collections.a0.mapOf(new kotlin.Pair[] {kotlin.TuplesKt.to("action", v1), kotlin.TuplesKt.to("data", p4)})));
                if (!kotlin.jvm.internal.Intrinsics.areEqual(v1, "SHOW_RESULT_PAGE")) {
                    this.c.b(v1, p4);
                } else {
                    this.O0(p4, p5);
                    return;
                }
            }
        }
        return;
    }

    private final void X0(org.json.JSONObject p3, MR.b p4)
    {
        UR.a.e("01.1103.999", kotlin.collections.a0.mapOf(new kotlin.Pair[] {kotlin.TuplesKt.to("event", "sendPaymentEvent"), kotlin.TuplesKt.to("params", String.valueOf(p3))})));
        if (p3 != null) {
            sq.r v3_1 = p3.toString();
            if (v3_1 != null) {
                sq.r v3_3 = ((sq.r) q9.s6.a(v3_1, sq.r));
                if (v3_3 != null) {
                    this.z = v3_3;
                }
            }
        }
        return;
    }

    private final void Y(org.json.JSONObject p12, MR.b p13)
    {
        String v4_2;
        androidx.appcompat.app.AppCompatActivity v0 = "";
        if (p12 == null) {
            v4_2 = "";
        } else {
            int v1_3 = p12.optString("order_token");
            if (v1_3 != 0) {
                v4_2 = v1_3;
            }
        }
        int v9;
        int v1_1 = 0;
        if (p12 == null) {
            v9 = 0;
        } else {
            v9 = p12.optBoolean("needthirdpartystatus");
        }
        if (p12 != null) {
            v1_1 = p12.optInt("completion_redirect_type");
        }
        if (p12 != null) {
            androidx.appcompat.app.AppCompatActivity v12_1 = p12.optString("redirect_url");
            if (v12_1 != null) {
                v0 = v12_1;
            }
        }
        androidx.appcompat.app.AppCompatActivity v12_4 = ((androidx.appcompat.app.AppCompatActivity) this.C().get());
        if (v12_4 != null) {
            sp.b v6_0 = v4_2;
            String v4_1 = v6_0;
            this.B0(kotlin.collections.C.listOf(new sp.g(0, 0, 0, v6_0, 4, 7)), p13);
            this.d.d(v12_4, v4_1, 4, new sp.b(v0, v1_1, 17, v9, 0), kotlin.collections.a0.emptyMap());
        }
        return;
    }

    private final void Z(org.json.JSONObject p3, MR.b p4)
    {
        this.c.b = "";
        if (this.b.a()) {
            try {
                if (!this.m1(p3, p4)) {
                    if (!this.R0(p3, p4)) {
                        if (p4 != null) {
                            p4.a(2, VJ.a.e(VJ.a.e));
                            return;
                        }
                    } else {
                    }
                } else {
                }
            } catch (String v3_4) {
                v3_4.getMessage();
                return;
            }
        } else {
            if (p4 != null) {
                p4.a(3, VJ.a.e(VJ.a.f));
                return;
            }
        }
        return;
    }

    private final void a0(org.json.JSONObject p3, MR.b p4)
    {
        UR.a.e("01.1103.999", kotlin.collections.Z.mapOf(kotlin.TuplesKt.to("pay v2", p3)));
        this.c.b = "";
        if (this.b.a()) {
            try {
                if (!this.n1(p3, p4)) {
                    if (!this.S0(p3, p4)) {
                        if (!this.Q0(p3, p4)) {
                            if (p4 != null) {
                                p4.a(2, VJ.a.e(VJ.a.e));
                                return;
                            }
                        } else {
                        }
                    } else {
                    }
                } else {
                }
            } catch (String v3_4) {
                v3_4.getMessage();
                return;
            }
        } else {
            if (p4 != null) {
                p4.a(3, VJ.a.e(VJ.a.f));
                return;
            }
        }
        return;
    }

    public static synthetic void b(vn.com.vng.zalopay.miniapp.api.v1.payment.PaymentModule p0, android.view.View p1)
    {
        vn.com.vng.zalopay.miniapp.api.v1.payment.PaymentModule.A(p0, p1);
        return;
    }

    private final void b0(sq.j p7, MR.b p8)
    {
        if (!(p7 instanceof sq.f)) {
            if ((p7 instanceof sq.g)) {
                java.util.Map v7_1 = ((sq.e) kotlin.collections.CollectionsKt.firstOrNull(((sq.g) p7).b));
                if (v7_1 != null) {
                    if (v7_1.c != 1) {
                        p8.a(0, "");
                    } else {
                        java.util.Map v7_2 = vn.com.vng.zalopay.miniapp.api.v1.payment.PaymentModule.c0(v7_1);
                        p8.f(v7_2);
                        StringBuilder v8_2 = new StringBuilder("data ");
                        v8_2.append(v7_2);
                        UR.a.e("01.1103.999", kotlin.collections.Z.mapOf(kotlin.TuplesKt.to("callback", v8_2.toString())));
                        return;
                    }
                } else {
                    p8.a(0, "");
                    return;
                }
            }
            return;
        } else {
            String v0_4 = new org.json.JSONObject();
            v0_4.put("reason", ((sq.f) p7).b);
            p8.i(v0_4);
            UR.a.e("01.1103.999", kotlin.collections.Z.mapOf(kotlin.TuplesKt.to("callback", "code 4")));
            return;
        }
    }

    private final void b1(float p2)
    {
        android.view.WindowManager$LayoutParams v0_1;
        android.view.WindowManager$LayoutParams v0_3 = ((androidx.appcompat.app.AppCompatActivity) this.C().get());
        if (v0_3 == null) {
            v0_1 = 0;
        } else {
            android.view.WindowManager$LayoutParams v0_4 = v0_3.getWindow();
            if (v0_4 == null) {
            } else {
                v0_1 = v0_4.getAttributes();
            }
        }
        if (v0_1 != null) {
            v0_1.screenBrightness = p2;
        }
        android.view.Window v2_3 = ((androidx.appcompat.app.AppCompatActivity) this.C().get());
        if (v2_3 != null) {
            android.view.Window v2_4 = v2_3.getWindow();
            if (v2_4 != null) {
                v2_4.setAttributes(v0_1);
            }
        }
        return;
    }

    public static final synthetic iw.a c(vn.com.vng.zalopay.miniapp.api.v1.payment.PaymentModule p0)
    {
        return p0.f;
    }

    private static final org.json.JSONObject c0(sq.e p3)
    {
        org.json.JSONObject v0_1 = new org.json.JSONObject();
        v0_1.put("transid", p3.e);
        v0_1.put("apptransid", p3.b);
        v0_1.put("resultstatus", p3.c);
        return v0_1;
    }

    private final void c1(java.util.Map p4, MR.b p5)
    {
        vn.com.vng.zalopay.authentication.AuthenticationDialog v0_3;
        vn.com.vng.zalopay.authentication.AuthenticationDialog v0_0 = this.C();
        if (v0_0 == null) {
            v0_3 = 0;
        } else {
            v0_3 = ((androidx.appcompat.app.AppCompatActivity) v0_0.get());
        }
        if (q9.Z.a(v0_3, this.h)) {
            vn.com.vng.zalopay.authentication.AuthenticationDialog v0_1 = vn.com.vng.zalopay.authentication.AuthenticationDialog.a();
            v0_1.e = new gF.d(this, p4, p5, 1);
            v0_1.b(((android.content.Context) this.C().get()));
            return;
        } else {
            this.e1(p4, 1, p5);
            return;
        }
    }

    public static final synthetic Is.a d(vn.com.vng.zalopay.miniapp.api.v1.payment.PaymentModule p0)
    {
        return p0.g;
    }

    private final void d0(sq.j p11, MR.b p12, java.util.concurrent.atomic.AtomicBoolean p13)
    {
        if (!(p11 instanceof sq.f)) {
            if (!(p11 instanceof sq.i)) {
                if ((p11 instanceof sq.g)) {
                    java.util.Map v11_4 = ((sq.e) kotlin.collections.CollectionsKt.firstOrNull(((sq.g) p11).b));
                    if ((v11_4 != null) && (v11_4.a == 2253)) {
                        java.util.Map v11_5 = vn.com.vng.zalopay.miniapp.api.v1.payment.PaymentModule.e0(v11_4);
                        if (p13.compareAndSet(0, 1)) {
                            if (p12 != null) {
                                p12.f(v11_5);
                            }
                            StringBuilder v12_2 = new StringBuilder("data ");
                            v12_2.append(v11_5);
                            UR.a.e("01.1103.999", kotlin.collections.Z.mapOf(kotlin.TuplesKt.to("callback", v12_2.toString())));
                        }
                    }
                }
            } else {
                String v7;
                java.util.Map v11_12 = ((sq.e) kotlin.collections.CollectionsKt.firstOrNull(((sq.i) p11).b));
                org.json.JSONObject v6_1 = 0;
                if (v11_12 == null) {
                    v7 = 0;
                } else {
                    v7 = v11_12.b;
                }
                if (v7 == null) {
                    v7 = "";
                }
                this.c.b = v7;
                if (v11_12 != null) {
                    v6_1 = vn.com.vng.zalopay.miniapp.api.v1.payment.PaymentModule.e0(v11_12);
                }
                if (p13.compareAndSet(0, 1)) {
                    if (p12 != null) {
                        p12.f(v6_1);
                    }
                    java.util.Map v11_15 = new StringBuilder("data ");
                    v11_15.append(v6_1);
                    UR.a.e("01.1103.999", kotlin.collections.Z.mapOf(kotlin.TuplesKt.to("callback", v11_15.toString())));
                    return;
                }
            }
        } else {
            long v0_5 = new org.json.JSONObject();
            v0_5.put("reason", ((sq.f) p11).b);
            if (p13.compareAndSet(0, 1)) {
                if (p12 != null) {
                    p12.i(v0_5);
                }
                UR.a.e("01.1103.999", kotlin.collections.Z.mapOf(kotlin.TuplesKt.to("callback", "code 4")));
                return;
            }
        }
        return;
    }

    private final void d1(boolean p2)
    {
        if (p2 == null) {
            androidx.appcompat.app.AppCompatActivity v2_6 = ((androidx.appcompat.app.AppCompatActivity) this.C().get());
            if (v2_6 != null) {
                com.google.android.gms.internal.mlkit_vision_barcode.T7.c(v2_6, "UIModuleProgressDialog");
            }
        } else {
            androidx.appcompat.app.AppCompatActivity v2_3 = ((androidx.appcompat.app.AppCompatActivity) this.C().get());
            if (v2_3 != null) {
                com.google.android.gms.internal.mlkit_vision_barcode.T7.m(v2_3, "UIModuleProgressDialog");
                return;
            }
        }
        return;
    }

    public static final synthetic fw.b e(vn.com.vng.zalopay.miniapp.api.v1.payment.PaymentModule p0)
    {
        return p0.e;
    }

    private static final org.json.JSONObject e0(sq.e p3)
    {
        org.json.JSONObject v0_1 = new org.json.JSONObject();
        v0_1.put("transid", p3.e);
        v0_1.put("apptransid", p3.b);
        v0_1.put("resultstatus", p3.c);
        return v0_1;
    }

    private final void e1(java.util.Map p5, boolean p6, MR.b p7)
    {
        int v2_1;
        android.content.Context v1_2 = ((android.content.Context) this.C().get());
        int v2_0 = ((androidx.appcompat.app.AppCompatActivity) this.C().get());
        if (v2_0 == 0) {
            v2_1 = 0;
        } else {
            v2_1 = v2_0.getString(2131951899);
        }
        OH.g v0_1 = new OH.g(v1_2, v2_1, p6);
        v0_1.d = new gF.j(this, p5, v0_1, p7);
        v0_1.a();
        return;
    }

    public static final synthetic vn.com.vng.zalopay.navigation.b f(vn.com.vng.zalopay.miniapp.api.v1.payment.PaymentModule p0)
    {
        return p0.a;
    }

    private final void f0(org.json.JSONObject p4, MR.b p5)
    {
        android.content.Context v4_1;
        android.content.Context v4_7 = nY.h.b(String.valueOf(p4), new vn.com.vng.zalopay.miniapp.api.v1.payment.PaymentModule$handleQuickPayBioPopup$type$1().getType());
        if (!kotlin.jvm.internal.T.f(v4_7)) {
            v4_1 = 0;
        } else {
            v4_1 = ((java.util.Map) v4_7);
        }
        if (v4_1 != null) {
            if (q9.Z.a(((android.content.Context) this.C().get()), this.h)) {
                vn.com.vng.zalopay.authentication.AuthenticationDialog v0_6 = vn.com.vng.zalopay.authentication.AuthenticationDialog.a();
                v0_6.e = new gF.d(v4_1, this, p5, 0);
                v0_6.b(((android.content.Context) this.C().get()));
                return;
            } else {
                this.f1(v4_1, 1, p5);
                return;
            }
        } else {
            if (p5 != null) {
                p5.a(2, "Invalid parameters");
            }
            return;
        }
    }

    private final void f1(java.util.Map p11, boolean p12, MR.b p13)
    {
        androidx.lifecycle.x v0_2 = ((androidx.appcompat.app.AppCompatActivity) this.C().get());
        if (v0_2 != null) {
            kotlinx.coroutines.BuildersKt.launch$default(androidx.lifecycle.l0.i(v0_2.getLifecycle()), kotlinx.coroutines.Dispatchers.getMain(), 0, new JV.p(this, p12, p11, p13, 0, 4), 2, 0);
        }
        return;
    }

    public static final synthetic XQ.a g(vn.com.vng.zalopay.miniapp.api.v1.payment.PaymentModule p0)
    {
        return p0.l;
    }

    private final void g0(org.json.JSONObject p3, MR.b p4)
    {
        // Both branches of the condition point to the same code.
        // if (!kotlin.jvm.internal.T.f(nY.h.b(String.valueOf(p3), new vn.com.vng.zalopay.miniapp.api.v1.payment.PaymentModule$handleQuickPayKycPopup$type$1().getType()))) {
            if (p3 != 0) {
                q9.X4.b(this.i, ((android.content.Context) this.C().get()), q9.y6.h("zalopay://launch/f/user-level-migration?fund_type=0"));
                int v3_4 = ((androidx.appcompat.app.AppCompatActivity) this.C().get());
                if (v3_4 != 0) {
                    v3_4.finish();
                }
            } else {
                if (p4 != null) {
                    p4.a(2, "Invalid parameters");
                    return;
                }
            }
            return;
        // }
    }

    private final void g1(java.util.Map p4)
    {
        android.net.Uri v0_3 = new android.net.Uri$Builder().scheme("zalopay").authority("launch").appendPath("f").appendPath("qrcode");
        Yv.i v4_2 = p4.entrySet().iterator();
        while (v4_2.hasNext()) {
            android.content.Context v1_7 = ((java.util.Map$Entry) v4_2.next());
            v0_3.appendQueryParameter(((String) v1_7.getKey()), ((String) v1_7.getValue()));
        }
        q9.X4.b(this.i, ((android.content.Context) this.C().get()), v0_3.build());
        return;
    }

    public static final synthetic L9.p h(vn.com.vng.zalopay.miniapp.api.v1.payment.PaymentModule p0)
    {
        return p0.N();
    }

    private final void h0(org.json.JSONObject p6, MR.b p7)
    {
        if (p6 != null) {
            String v0 = p6.optString("url");
            double v2 = p6.optDouble("ratio");
            UR.a.e("01.1103.999", kotlin.collections.a0.mapOf(new kotlin.Pair[] {kotlin.TuplesKt.to("action", "render_custom"), kotlin.TuplesKt.to("url", v0), kotlin.TuplesKt.to("ratio", Double.valueOf(v2))})));
            this.c.c("", v0, v2);
            return;
        } else {
            return;
        }
    }

    private final void h1(org.json.JSONObject p4, MR.b p5)
    {
        if (p4 != null) {
            com.google.android.gms.tasks.Task v4_4 = p4.toString();
            if (v4_4 != null) {
                com.google.android.gms.tasks.Task v4_8 = ((qp.b) q9.s6.a(v4_4, qp.b));
                if (v4_8 != null) {
                    com.google.android.gms.tasks.Task v4_2 = qp.a.b(v4_8).toString();
                    androidx.lifecycle.T v0_1 = new L9.m();
                    H8.w.i(v4_2, "paymentDataRequestJson cannot be null!");
                    v0_1.j = v4_2;
                    this.s.j(new Xs.b(new kotlin.Pair(this.N().c(v0_1), p5)));
                    return;
                }
            }
        }
        if (p5 != null) {
            p5.a(2, "");
        }
        return;
    }

    public static final synthetic void i(vn.com.vng.zalopay.miniapp.api.v1.payment.PaymentModule p0, sq.j p1, MR.b p2)
    {
        p0.b0(p1, p2);
        return;
    }

    private final void i0(org.json.JSONObject p2, MR.b p3)
    {
        int v2_1;
        int v2_4 = nY.h.b(String.valueOf(p2), new vn.com.vng.zalopay.miniapp.api.v1.payment.PaymentModule$handleSetupQuickPay$type$1().getType());
        if (!kotlin.jvm.internal.T.f(v2_4)) {
            v2_1 = 0;
        } else {
            v2_1 = ((java.util.Map) v2_4);
        }
        if (v2_1 != 0) {
            this.z();
            return;
        } else {
            if (p3 != null) {
                p3.a(2, "Invalid parameters");
            }
            return;
        }
    }

    private final org.json.JSONObject i1(wL.f p4, sq.e p5, boolean p6)
    {
        int v6_3;
        org.json.JSONObject v0_1 = new org.json.JSONObject();
        if (p6 == 0) {
            v6_3 = this.k1(p5);
        } else {
            v6_3 = "cancel";
        }
        v0_1.put("status", v6_3);
        v0_1.put("appId", p4.a());
        int v6_1 = p4.b();
        if (v6_1 == 0) {
            v6_1 = "";
        }
        int v5_1;
        v0_1.put("appTransId", v6_1);
        if (p5 == 0) {
            v5_1 = p4.d();
        } else {
            v5_1 = p5.e;
            if (v5_1 == 0) {
            } else {
                if (v5_1.length() <= 0) {
                    v5_1 = 0;
                }
                if (v5_1 == 0) {
                }
            }
        }
        if ((v5_1 != 0) && (v5_1.length() != 0)) {
            v0_1.put("zpTransId", v5_1);
        }
        return v0_1;
    }

    public static final synthetic void j(vn.com.vng.zalopay.miniapp.api.v1.payment.PaymentModule p0, sq.j p1, MR.b p2, java.util.concurrent.atomic.AtomicBoolean p3)
    {
        p0.d0(p1, p2, p3);
        return;
    }

    private final void j0(org.json.JSONObject p5, MR.b p6)
    {
        java.util.Map v5_2;
        UR.a.e("01.1130.999", kotlin.collections.a0.mapOf(new kotlin.Pair[] {kotlin.TuplesKt.to("action", "startCashier"), kotlin.TuplesKt.to("data", p5)})));
        this.c.b = "";
        if (p5 == null) {
            v5_2 = 0;
        } else {
            java.util.Map v5_1 = p5.opt("payload");
            if (v5_1 == null) {
            } else {
                v5_2 = v5_1.toString();
            }
        }
        java.util.Map v5_4 = ((sp.m) q9.s6.a(v5_2, sp.m));
        if (v5_4 == null) {
            if (p6 != null) {
                p6.a(2, "Invalid parameters");
            }
        } else {
            String v0_6 = v5_4.b();
            if (v0_6 != null) {
                androidx.appcompat.app.AppCompatActivity v1_5 = ((Iterable) v0_6).iterator();
                while (v1_5.hasNext()) {
                    ((sp.g) v1_5.next()).e = 3;
                }
                this.y0(v0_6, p6);
                Wo.d v6_1 = this.d;
                androidx.appcompat.app.AppCompatActivity v1_9 = ((androidx.appcompat.app.AppCompatActivity) this.C().get());
                if (v1_9 != null) {
                    java.util.Map v5_5 = v5_4.a();
                    if (v5_5 == null) {
                        v5_5 = kotlin.collections.a0.emptyMap();
                    }
                    v6_1.g(v1_9, v0_6, v5_5);
                    return;
                }
            }
        }
        return;
    }

    public static synthetic org.json.JSONObject j1(vn.com.vng.zalopay.miniapp.api.v1.payment.PaymentModule p0, wL.f p1, sq.e p2, boolean p3, int p4, Object p5)
    {
        if ((p4 & 4) != 0) {
            p3 = 0;
        }
        return p0.i1(p1, p2, p3);
    }

    public static final synthetic org.json.JSONObject k(sq.e p0)
    {
        return vn.com.vng.zalopay.miniapp.api.v1.payment.PaymentModule.z0(p0);
    }

    private final void k0(org.json.JSONObject p22, MR.b p23)
    {
        kotlin.Unit v0_8 = ((androidx.appcompat.app.AppCompatActivity) this.C().get());
        if (v0_8 == null) {
            throw new IllegalStateException("activity is not available");
        } else {
            if (p22 != null) {
                Long v2_1 = p22.toString();
                if (v2_1 != null) {
                    Long v2_3 = ((hF.a) q9.s6.a(v2_1, hF.a));
                    if (v2_3 != null) {
                        void v3_1 = v2_3.b();
                        if (v3_1 != 0) {
                            long v6 = 0;
                            if (v3_1.a() <= 0) {
                                v3_1 = 0;
                            } else {
                                boolean v4_2 = v3_1.b();
                                if ((!v4_2) || (kotlin.text.StringsKt.isBlank(v4_2))) {
                                }
                            }
                            int v15 = v3_1;
                            if (v15 != 0) {
                                this.D0(v0_8, v15, p23);
                                int v14 = v2_3.c();
                                Long v2_6 = v2_3.a();
                                sp.g v11 = m9.C2.c(v15);
                                if (v2_6 == null) {
                                    v2_6 = kotlin.collections.a0.emptyMap();
                                }
                                java.util.Map v13 = v2_6;
                                Long v2_7 = v15.c();
                                if (v2_7 != null) {
                                    Long v2_8 = kotlin.text.StringsKt.toLongOrNull(v2_7);
                                    if (v2_8 != null) {
                                        v6 = v2_8.longValue();
                                    }
                                }
                                m9.p.h(v0_8, new wL.n("", 0, v11, 0, v13, v14, v15, v6, 0, 0, 776));
                                kotlin.Unit v0_9 = kotlin.Unit.INSTANCE;
                                kotlin.Unit v0_10 = ng.L.a(v0_9);
                                if ((v0_10 != null) && (p23 != null)) {
                                    kotlin.Unit v0_12 = v0_10.getMessage();
                                    if (v0_12 == null) {
                                        v0_12 = "";
                                    }
                                    p23.a(-1, v0_12);
                                }
                                return;
                            }
                        }
                        throw new IllegalArgumentException("appId and appTransId are required");
                    }
                }
            }
            throw new IllegalArgumentException("cannot parse parameters");
        }
    }

    private final String k1(sq.e p3)
    {
        String v3_1;
        if (p3 == null) {
            v3_1 = 0;
        } else {
            v3_1 = Integer.valueOf(p3.c);
        }
        if ((v3_1 == null) || (v3_1.intValue() != 1)) {
            if ((v3_1 == null) || (v3_1.intValue() != 0)) {
                if ((v3_1 == null) || (v3_1.intValue() != 3)) {
                    return "processing";
                } else {
                    return "pending";
                }
            } else {
                return "failed";
            }
        } else {
            return "success";
        }
    }

    public static final synthetic org.json.JSONObject l(String p0, org.json.JSONObject p1)
    {
        return vn.com.vng.zalopay.miniapp.api.v1.payment.PaymentModule.A0(p0, p1);
    }

    private final void l0(org.json.JSONObject p9, MR.b p10)
    {
        int v9_1;
        int v9_13 = nY.h.b(String.valueOf(p9), new vn.com.vng.zalopay.miniapp.api.v1.payment.PaymentModule$handleTopUp$type$1().getType());
        if (!kotlin.jvm.internal.T.f(v9_13)) {
            v9_1 = 0;
        } else {
            v9_1 = ((java.util.Map) v9_13);
        }
        if (v9_1 != 0) {
            if (!v9_1.isEmpty()) {
                int v9_5;
                int v0_4 = ((android.app.Activity) this.C().get());
                this.S().getClass();
                if (!(v0_4 instanceof androidx.lifecycle.F)) {
                    v9_5 = 0;
                } else {
                    v9_5 = ((androidx.lifecycle.F) v0_4);
                }
                if (v9_5 == 0) {
                    return;
                } else {
                    kotlinx.coroutines.BuildersKt.launch$default(androidx.lifecycle.l0.i(v9_5.getLifecycle()), 0, 0, new AA.y(v0_4, 0, 1), 3, 0);
                    return;
                }
            } else {
            }
        }
        if (p10 == null) {
            return;
        } else {
            p10.a(2, "Invalid parameters");
            return;
        }
    }

    private final boolean l1(org.json.JSONObject p5)
    {
        int v5_1;
        Ew.H v0_1 = new Ew.H(p5);
        this.m = v0_1;
        if ((android.text.TextUtils.isEmpty(v0_1.a)) || (v0_1.b <= 0)) {
            v5_1 = 0;
        } else {
            v5_1 = 1;
        }
        if (v5_1 != 0) {
            Ew.H v0_2 = this.m;
            if (v0_2 != null) {
                this.T(v0_2);
            }
        }
        return v5_1;
    }

    public static final synthetic org.json.JSONObject m(sq.e p0)
    {
        return vn.com.vng.zalopay.miniapp.api.v1.payment.PaymentModule.C0(p0);
    }

    private final void m0(org.json.JSONObject p2, MR.b p3)
    {
        this.l = new gF.e(p3);
        if (this.b.a()) {
            this.d1(1);
            if (!this.l1(p2)) {
                this.d1(0);
                XQ.a v2_2 = this.l;
                if (v2_2 != null) {
                    v2_2.e(2, VJ.a.e(VJ.a.e));
                }
            } else {
                this.d1(0);
                return;
            }
        } else {
            XQ.a v2_4 = this.l;
            if (v2_4 != null) {
                v2_4.e(3, VJ.a.e(VJ.a.f));
                return;
            }
        }
        return;
    }

    private final boolean m1(org.json.JSONObject p10, MR.b p11)
    {
        Ew.G v0_1 = new Ew.G(p10);
        boolean v10_1 = v0_1.a();
        this.C().get();
        if (v10_1) {
            Wo.d v1_4 = v0_1.c;
            UR.a.c(25002, 0, Long.valueOf(v1_4), nY.h.d(kotlin.collections.a0.mapOf(new kotlin.Pair[] {kotlin.TuplesKt.to("appid", Long.valueOf(v1_4)), kotlin.TuplesKt.to("zptranstoken", v0_1.b)}))));
            this.t0(v0_1.c, v0_1.b, p11);
            androidx.appcompat.app.AppCompatActivity v2_2 = ((androidx.appcompat.app.AppCompatActivity) this.C().get());
            if (v2_2 != null) {
                Wo.d.e(this.d, v2_2, v0_1.c, v0_1.b, 0, 0, 48);
            }
        }
        return v10_1;
    }

    public static final synthetic void n(vn.com.vng.zalopay.miniapp.api.v1.payment.PaymentModule p0, Ew.c p1, OH.g p2, MR.b p3)
    {
        p0.F0(p1, p2, p3);
        return;
    }

    private final void n0(int p4, android.content.Intent p5)
    {
        if ((p4 == -1) && (p5 != 0)) {
            XQ.a v4_3 = p5.getExtras();
            if (v4_3 != null) {
                int v5_1 = v4_3.getInt("code");
                XQ.a v4_1 = v4_3.getString("param");
                if (v5_1 != 1) {
                    boolean v1_0 = this.m;
                    if (!v1_0) {
                        v5_1 = 5;
                    } else {
                        boolean v1_1 = v1_0.d;
                        if ((!v1_1) || (kotlin.collections.y.contains(v1_1, Integer.valueOf(v5_1)) != 1)) {
                        }
                    }
                    XQ.a v0_2 = this.l;
                    if (v0_2 != null) {
                        v0_2.e(v5_1, v4_1);
                    }
                } else {
                    XQ.a v4_2 = this.l;
                    if (v4_2 != null) {
                        v4_2.d();
                        return;
                    }
                }
            }
        }
        return;
    }

    private final boolean n1(org.json.JSONObject p19, MR.b p20)
    {
        Ew.G v2_1 = new Ew.G(p19);
        boolean v3 = v2_1.a();
        this.C().get();
        if (v3) {
            long v4_0 = v2_1.c;
            UR.a.c(25002, 0, Long.valueOf(v4_0), nY.h.d(kotlin.collections.a0.mapOf(new kotlin.Pair[] {kotlin.TuplesKt.to("appid", Long.valueOf(v4_0)), kotlin.TuplesKt.to("zptranstoken", v2_1.b)}))));
            boolean v9 = p19.optBoolean("needthirdpartystatus");
            int v7_3 = p19.optInt("completion_redirect_type");
            long v4_5 = p19.optString("redirect_url");
            if (v4_5 == 0) {
                v4_5 = "";
            }
            String v6_5 = v4_5;
            Wo.d v1_3 = ((java.util.Map) q9.s6.a(p19.optString("options"), new vn.com.vng.zalopay.miniapp.api.v1.payment.PaymentModule$zpTransactionV2$mapType$1().getType()));
            if (v1_3 == null) {
                v1_3 = kotlin.collections.a0.emptyMap();
            }
            java.util.Map v17 = v1_3;
            void v0_1 = this.v0(v2_1.c, v2_1.b, p20);
            androidx.appcompat.app.AppCompatActivity v11_1 = ((androidx.appcompat.app.AppCompatActivity) v0_1.C().get());
            if (v11_1 != null) {
                Wo.d v1_7 = v0_1.d;
                long v12 = v2_1.c;
                String v14 = v2_1.b;
                sp.b v16 = new sp.b;
                v16(v6_5, v7_3, 17, v9, 0);
                v1_7.b(v11_1, v12, v14, 3, v16, v17);
            }
        }
        return v3;
    }

    public static final synthetic void o(vn.com.vng.zalopay.miniapp.api.v1.payment.PaymentModule p0, MR.b p1)
    {
        p0.G0(p1);
        return;
    }

    private final void o0(org.json.JSONObject p5, MR.b p6)
    {
        if (p5 != null) {
            int v0 = p5.optInt("status");
            String v5_1 = p5.optString("message");
            UR.a.e("01.1103.999", kotlin.collections.a0.mapOf(new kotlin.Pair[] {kotlin.TuplesKt.to("action", "update_status"), kotlin.TuplesKt.to("status", Integer.valueOf(v0)), kotlin.TuplesKt.to("message", v5_1)})));
            this.c.e(v0, v5_1);
            return;
        } else {
            return;
        }
    }

    public static final synthetic void p(vn.com.vng.zalopay.miniapp.api.v1.payment.PaymentModule p0, Ew.o p1, Ew.H p2)
    {
        p0.I0(p1, p2);
        return;
    }

    private final void p0(org.json.JSONObject p5, MR.b p6)
    {
        if (p5 != null) {
            String v0 = p5.optString("title");
            String v5_1 = p5.optString("deeplink");
            UR.a.e("01.1103.999", kotlin.collections.a0.mapOf(new kotlin.Pair[] {kotlin.TuplesKt.to("action", "render_cta"), kotlin.TuplesKt.to("title", v0), kotlin.TuplesKt.to("deeplink", v5_1)})));
            this.c.d("", v0, v5_1);
            return;
        } else {
            return;
        }
    }

    public static final synthetic void q(vn.com.vng.zalopay.miniapp.api.v1.payment.PaymentModule p0, boolean p1)
    {
        p0.d1(p1);
        return;
    }

    private final void q0(org.json.JSONObject p2, MR.b p3)
    {
        int v2_1;
        int v2_4 = nY.h.b(String.valueOf(p2), new vn.com.vng.zalopay.miniapp.api.v1.payment.PaymentModule$handlerScanQr$type$1().getType());
        if (!kotlin.jvm.internal.T.f(v2_4)) {
            v2_1 = 0;
        } else {
            v2_1 = ((java.util.Map) v2_4);
        }
        if (v2_1 != 0) {
            this.g1(v2_1);
            return;
        } else {
            if (p3 != null) {
                p3.a(2, "Invalid parameters");
            }
            return;
        }
    }

    public static final synthetic void r(vn.com.vng.zalopay.miniapp.api.v1.payment.PaymentModule p0, java.util.Map p1, boolean p2, MR.b p3)
    {
        p0.e1(p1, p2, p3);
        return;
    }

    private final void r0(org.json.JSONObject p12, MR.b p13)
    {
        try {
            li.e v12_1 = qp.a.c.put("allowedPaymentMethods", new org.json.JSONArray().put(qp.a.a()));
        } catch (org.json.JSONException) {
            v12_1 = 0;
        }
        li.e v12_2 = String.valueOf(v12_1);
        L9.h v2_1 = new L9.h();
        v2_1.f = v12_2;
        kotlinx.coroutines.BuildersKt.launch$default(gi.L.a(this.g.c), 0, 0, new bc.j(this, v2_1, p13, 0, 4), 3, 0);
        return;
    }

    public static final synthetic void s(vn.com.vng.zalopay.miniapp.api.v1.payment.PaymentModule p0, java.util.Map p1, boolean p2, MR.b p3)
    {
        p0.f1(p1, p2, p3);
        return;
    }

    private final void s0(org.json.JSONObject p4, MR.b p5)
    {
        android.content.Intent v1_2;
        UR.a.e("01.1103.999", kotlin.collections.a0.mapOf(new kotlin.Pair[] {kotlin.TuplesKt.to("event", "launchCashierWebAuthen"), kotlin.TuplesKt.to("params", String.valueOf(p4))})));
        if (p4 == 0) {
            v1_2 = 0;
        } else {
            v1_2 = p4.optString("id");
        }
        if (v1_2 == null) {
            v1_2 = "";
        }
        int v4_1;
        this.w = v1_2;
        if (p4 == 0) {
            v4_1 = 0;
        } else {
            v4_1 = p4.optString("deeplink");
        }
        if ((v4_1 != 0) && (v4_1.length() != 0)) {
            android.content.Intent v1_7 = new android.content.Intent("android.intent.action.VIEW", android.net.Uri.parse(v4_1));
            v1_7.addFlags(268435456);
            int v4_6 = ((androidx.appcompat.app.AppCompatActivity) this.C().get());
            if (v4_6 != 0) {
                v4_6.startActivity(v1_7);
            }
            if (p5 != null) {
                p5.f(0);
                return;
            }
        } else {
            if (p5 != null) {
                p5.a(2, "");
            }
        }
        return;
    }

    public static final synthetic org.json.JSONObject t(vn.com.vng.zalopay.miniapp.api.v1.payment.PaymentModule p0, wL.f p1, sq.e p2, boolean p3)
    {
        return p0.i1(p1, p2, p3);
    }

    private final void t0(long p11, String p13, MR.b p14)
    {
        if (p14 != null) {
            androidx.lifecycle.x v0_2 = ((androidx.appcompat.app.AppCompatActivity) this.C().get());
            if (v0_2 != null) {
                kotlinx.coroutines.BuildersKt.launch$default(androidx.lifecycle.l0.i(v0_2.getLifecycle()), 0, 0, new Fm.b(p11, p13, this, p14, 0, 6), 3, 0);
            }
        }
        return;
    }

    private final void u0(Ew.n p9, MR.b p10)
    {
        if (p10 != null) {
            androidx.lifecycle.x v0_2 = ((androidx.appcompat.app.AppCompatActivity) this.C().get());
            if (v0_2 != null) {
                kotlinx.coroutines.BuildersKt.launch$default(androidx.lifecycle.l0.i(v0_2.getLifecycle()), 0, 0, new b0.x(p9, this, p10, 0, 8), 3, 0);
            }
        }
        return;
    }

    private final void v0(long p14, String p16, MR.b p17)
    {
        java.util.concurrent.atomic.AtomicBoolean v6_1 = new java.util.concurrent.atomic.AtomicBoolean(0);
        VE.h v0_6 = ((androidx.appcompat.app.AppCompatActivity) this.C().get());
        if (v0_6 != null) {
            kotlinx.coroutines.BuildersKt.launch$default(androidx.lifecycle.l0.i(v0_6.getLifecycle()), 0, 0, new VE.h(p14, p16, this, p17, v6_1, 0), 3, 0);
        }
        return;
    }

    private final void w(org.json.JSONObject p2, MR.b p3)
    {
        UR.a.e("01.1103.999", kotlin.collections.a0.mapOf(new kotlin.Pair[] {kotlin.TuplesKt.to("event", "close_tray"), kotlin.TuplesKt.to("data", String.valueOf(p2))})));
        this.v.j(new Xs.b(kotlin.Unit.INSTANCE));
        return;
    }

    private final void w0(Ew.n p12, MR.b p13)
    {
        java.util.concurrent.atomic.AtomicBoolean v4_1 = new java.util.concurrent.atomic.AtomicBoolean(0);
        b3.q v0_6 = ((androidx.appcompat.app.AppCompatActivity) this.C().get());
        if (v0_6 != null) {
            kotlinx.coroutines.BuildersKt.launch$default(androidx.lifecycle.l0.i(v0_6.getLifecycle()), 0, 0, new b3.q(p12, this, p13, v4_1, 0, 6), 3, 0);
        }
        return;
    }

    private final void x(org.json.JSONObject p2, MR.b p3)
    {
        UR.a.e("01.1103.999", kotlin.collections.Z.mapOf(kotlin.TuplesKt.to("event", "close Viet QR")));
        if (p3 != null) {
            p3.f(0);
        }
        this.o.j(new Xs.b(kotlin.Unit.INSTANCE));
        return;
    }

    private final void x0(String p12, MR.b p13)
    {
        java.util.concurrent.atomic.AtomicBoolean v4_1 = new java.util.concurrent.atomic.AtomicBoolean(0);
        b3.q v0_6 = ((androidx.appcompat.app.AppCompatActivity) this.C().get());
        if (v0_6 != null) {
            kotlinx.coroutines.BuildersKt.launch$default(androidx.lifecycle.l0.i(v0_6.getLifecycle()), 0, 0, new b3.q(p12, this, p13, v4_1, 0, 7), 3, 0);
        }
        return;
    }

    private final void y0(java.util.List p8, MR.b p9)
    {
        int v0_3 = ((androidx.appcompat.app.AppCompatActivity) this.C().get());
        if (v0_3 != 0) {
            kotlinx.coroutines.BuildersKt.launch$default(androidx.lifecycle.l0.i(v0_3.getLifecycle()), 0, 0, new gF.i(p8, p9, 0, 0), 3, 0);
        }
        return;
    }

    private final void z()
    {
        Object v0_1;
        this.b1(1065353216);
        Object v0_8 = this.C().get();
        vn.com.vng.zalopay.webapp.activity.WebAppExternalActivity v2_1 = 0;
        if (!(v0_8 instanceof vn.com.vng.zalopay.webapp.activity.WebAppExternalActivity)) {
            v0_1 = 0;
        } else {
            v0_1 = ((vn.com.vng.zalopay.webapp.activity.WebAppExternalActivity) v0_8);
        }
        if (v0_1 != null) {
            v0_1.s1(0);
        }
        Object v0_4;
        Object v0_3 = this.C().get();
        if (!(v0_3 instanceof vn.com.vng.zalopay.webapp.activity.WebAppExternalActivity)) {
            v0_4 = 0;
        } else {
            v0_4 = ((vn.com.vng.zalopay.webapp.activity.WebAppExternalActivity) v0_3);
        }
        if (v0_4 != null) {
            v0_4.t1();
        }
        float v4_5;
        Object v0_7 = new android.widget.ImageButton(((android.content.Context) this.C().get()));
        v0_7.setBackgroundResource(2131234076);
        java.util.ArrayList v1_7 = new android.widget.LinearLayout$LayoutParams(-2, -2);
        float v4_2 = ((androidx.appcompat.app.AppCompatActivity) this.C().get());
        if (v4_2 == 0) {
            v4_5 = 0;
        } else {
            float v4_3 = v4_2.getResources();
            if (v4_3 == 0) {
            } else {
                float v4_4 = v4_3.getDisplayMetrics();
                if (v4_4 == 0) {
                } else {
                    v4_5 = v4_4.density;
                }
            }
        }
        v1_7.setMarginEnd(((int) (((float) 10) * v4_5)));
        v0_7.setLayoutParams(v1_7);
        v0_7.setOnClickListener(new androidx.media3.ui.h(15, this));
        java.util.ArrayList v1_12 = new java.util.ArrayList();
        v1_12.add(v0_7);
        Object v0_10 = this.C().get();
        if ((v0_10 instanceof vn.com.vng.zalopay.webapp.activity.WebAppExternalActivity)) {
            v2_1 = ((vn.com.vng.zalopay.webapp.activity.WebAppExternalActivity) v0_10);
        }
        if (v2_1 != null) {
            v2_1.q1(v1_12);
        }
        return;
    }

    private static final org.json.JSONObject z0(sq.e p4)
    {
        org.json.JSONObject v0_1 = new org.json.JSONObject();
        v0_1.put("app_id", p4.a);
        v0_1.put("zp_trans_id", p4.e);
        v0_1.put("app_trans_id", p4.b);
        v0_1.put("status", p4.c);
        return v0_1;
    }

    public final void B(String p4, java.util.Map p5, OH.g p6, MR.b p7)
    {
        java.util.LinkedHashMap v0_1 = new java.util.LinkedHashMap();
        String v5_8 = ((String) p5.get("error"));
        if (v5_8 != null) {
            String v1_2 = v5_8.hashCode();
            if (v1_2 == 48) {
                if ((v5_8.equals("0")) && (p7 != null)) {
                    p7.a(0, "Cancel");
                }
            } else {
                if (v1_2 == 49) {
                    if (v5_8.equals("1")) {
                        v0_1.put("pin", p4);
                        v0_1.put("message", "success");
                        if (p7 != null) {
                            p7.f(new org.json.JSONObject(nY.h.d(v0_1)));
                        }
                    }
                } else {
                    if ((v1_2 == 1444) && ((v5_8.equals("-1")) && (p7 != null))) {
                        p7.a(-1, "Failed to authenticate PIN");
                    }
                }
            }
        }
        if (p6 != null) {
            p6.b();
        }
        return;
    }

    public final ref.WeakReference C()
    {
        int v0_0 = this.k;
        if (v0_0 == 0) {
            return 0;
        } else {
            return v0_0;
        }
    }

    public final java.util.Map D()
    {
        return this.x;
    }

    public final void E0(int p2, int p3, android.content.Intent p4)
    {
        if (p2 == 18) {
            this.n0(p3, p4);
        }
        return;
    }

    public final androidx.lifecycle.T F()
    {
        return this.v;
    }

    public final androidx.lifecycle.T G()
    {
        return this.o;
    }

    public final String H()
    {
        return this.w;
    }

    public void H0()
    {
        return;
    }

    public final sq.r I()
    {
        return this.z;
    }

    public final androidx.lifecycle.T J()
    {
        return this.s;
    }

    public final void J0(N9.a p7, MR.b p8)
    {
        com.google.android.gms.common.api.Status v1 = p7.b;
        kotlin.Pair v0_3 = p7.b.a;
        String v4 = "";
        if (v0_3 == null) {
            UR.a.e("01.1103.999", kotlin.collections.Z.mapOf(kotlin.TuplesKt.to("event", "GooglePay success")));
            kotlin.Pair v0_2 = java.util.Base64.getUrlEncoder();
            int v7_2 = ((L9.l) p7.a);
            if (v7_2 != 0) {
                int v7_3 = v7_2.g;
                if (v7_3 != 0) {
                    v4 = v7_3;
                }
            }
            String vtmp10 = v0_2.encodeToString(v4.getBytes(kotlin.text.Charsets.UTF_8));
            if (p8 != null) {
                p8.f(vtmp10);
            }
        } else {
            if (v0_3 == 16) {
                UR.a.e("01.1103.999", kotlin.collections.Z.mapOf(kotlin.TuplesKt.to("event", "GooglePay cancel")));
                if (p8 != null) {
                    p8.a(4, "");
                    return;
                }
            } else {
                int v7_13 = kotlin.TuplesKt.to("event", "GooglePay error");
                kotlin.Pair vtmp6 = kotlin.TuplesKt.to("code", Integer.valueOf(v1.a));
                kotlin.Pair v2_2 = v1.b;
                if (v2_2 == null) {
                    v2_2 = "";
                }
                UR.a.e("01.1103.999", kotlin.collections.a0.mapOf(new kotlin.Pair[] {v7_13, vtmp6, kotlin.TuplesKt.to("message", v2_2)})));
                if (p8 != null) {
                    int v7_16 = v1.b;
                    if (v7_16 != 0) {
                        v4 = v7_16;
                    }
                    p8.a(0, v4);
                    return;
                }
            }
        }
        return;
    }

    public final androidx.lifecycle.T K()
    {
        return this.p;
    }

    public final androidx.lifecycle.T L()
    {
        return this.t;
    }

    public final androidx.lifecycle.T M()
    {
        return this.u;
    }

    public final androidx.lifecycle.T O()
    {
        return this.r;
    }

    public final androidx.lifecycle.T Q()
    {
        return this.q;
    }

    public final androidx.lifecycle.T U()
    {
        return this.n;
    }

    public final void Y0(ref.WeakReference p1)
    {
        this.k = p1;
        return;
    }

    public final void Z0(String p1)
    {
        this.w = p1;
        return;
    }

    public void a(String p2, org.json.JSONObject p3, MR.b p4)
    {
        if (p2) {
            switch (p2.hashCode()) {
                case -2030718059:
                    if (p2.equals("transferMoney")) {
                        this.m0(p3, p4);
                    }
                    break;
                case -1757340549:
                    if (p2.equals("onPrePaymentComplete")) {
                        this.M0(p3, p4);
                        return;
                    }
                case -1600878883:
                    if (p2.equals("openResultTray")) {
                        this.P0(p3, p4);
                        return;
                    }
                case -1527626587:
                    if (p2.equals("resizeWebview")) {
                        this.U0(p3, p4);
                        return;
                    }
                case -1299425418:
                    if (p2.equals("getResultPageOpenData")) {
                        this.R(p3, p4);
                        return;
                    }
                case -1261834857:
                    if (p2.equals("onPageLoadCompleted")) {
                        this.K0(p3, p4);
                        return;
                    }
                case -1145227932:
                    if (p2.equals("executeCashierAction")) {
                        this.W(p3, p4);
                        return;
                    }
                case -1085691585:
                    if (p2.equals("quickpayBioPopup")) {
                        this.f0(p3, p4);
                        return;
                    }
                case -1061237995:
                    if (p2.equals("launchCashierWebAuthen")) {
                        this.s0(p3, p4);
                        return;
                    }
                case -927065239:
                    if (p2.equals("saveConfigData")) {
                        this.V0(p3, p4);
                        return;
                    }
                case -908187298:
                    if (p2.equals("scanqr")) {
                        this.q0(p3, p4);
                        return;
                    }
                case -813722609:
                    if (p2.equals("renderResultExtraButton")) {
                        this.p0(p3, p4);
                        return;
                    }
                case -808670008:
                    if (p2.equals("executeResultAction")) {
                        this.X(p3, p4);
                        return;
                    }
                case -798560604:
                    if (p2.equals("isGooglePayAvailable")) {
                        this.r0(p3, p4);
                        return;
                    }
                case -784932392:
                    if (p2.equals("updateResultStatus")) {
                        this.o0(p3, p4);
                        return;
                    }
                case -358874564:
                    if (p2.equals("onPrePaymentCancel")) {
                        this.L0(p3, p4);
                        return;
                    }
                case -346965028:
                    if (p2.equals("sendPaymentEvent")) {
                        this.X0(p3, p4);
                        return;
                    }
                case -312573672:
                    if (p2.equals("setupQuickPay")) {
                        this.i0(p3, p4);
                        return;
                    }
                case -273127425:
                    if (p2.equals("onPrePaymentFailed")) {
                        this.N0(p3, p4);
                        return;
                    }
                case -102147118:
                    if (p2.equals("payMoneyTransferTokenV2")) {
                        this.Y(p3, p4);
                        return;
                    }
                case -67175157:
                    if (p2.equals("closeResultTray")) {
                        this.w(p3, p4);
                        return;
                    }
                case 59208082:
                    if (p2.equals("quickPayKycPopup")) {
                        this.g0(p3, p4);
                        return;
                    }
                case 110546608:
                    if (p2.equals("topup")) {
                        this.l0(p3, p4);
                        return;
                    }
                case 420539393:
                    if (p2.equals("startCashier")) {
                        this.j0(p3, p4);
                        return;
                    }
                case 673663159:
                    if (p2.equals("sendPaymentComplete")) {
                        this.W0(p3, p4);
                        return;
                    }
                case 856960337:
                    if (p2.equals("renderResultCustomArea")) {
                        this.h0(p3, p4);
                        return;
                    }
                case 893863845:
                    if (p2.equals("confirmBinding")) {
                        this.V(p3, p4);
                        return;
                    }
                case 943345105:
                    if (p2.equals("getCashierWebData")) {
                        this.E(p3, p4);
                        return;
                    }
                case 1120096418:
                    if (p2.equals("payOrderV2")) {
                        this.a0(p3, p4);
                        return;
                    }
                case 1120152941:
                    if (p2.equals("startGooglePay")) {
                        this.h1(p3, p4);
                        return;
                    }
                case 1182673776:
                    if (p2.equals("customBlockResize")) {
                        this.T0(p3, p4);
                        return;
                    }
                case 1328305742:
                    if (p2.equals("startResultPage")) {
                        this.k0(p3, p4);
                        return;
                    }
                case 1355353990:
                    if (p2.equals("payOrder")) {
                        this.Z(p3, p4);
                        return;
                    }
                case 2087735463:
                    if (p2.equals("closeVietQRScreen")) {
                        this.x(p3, p4);
                        return;
                    }
                case 2113825178:
                    if (p2.equals("getPaymentData")) {
                        this.P(p3, p4);
                        return;
                    }
                default:
            }
        }
        return;
    }

    public final void a1(sq.r p1)
    {
        this.z = p1;
        return;
    }

    public void onCreate()
    {
        return;
    }

    public final void u(ref.WeakReference p1)
    {
        this.Y0(p1);
        return;
    }

    public final void v(String p11, java.util.Map p12, OH.g p13, MR.b p14)
    {
        androidx.lifecycle.x v0_2 = ((androidx.appcompat.app.AppCompatActivity) this.C().get());
        if (v0_2 != null) {
            kotlinx.coroutines.BuildersKt.launch$default(androidx.lifecycle.l0.i(v0_2.getLifecycle()), 0, 0, new CJ.m(this, p13, p14, p11, p12, 0, 27), 3, 0);
        }
        return;
    }

    public final void y(android.graphics.Bitmap p9)
    {
        androidx.appcompat.app.AppCompatActivity v1_1 = ((androidx.appcompat.app.AppCompatActivity) this.C().get());
        if (v1_1 != null) {
            String v0_8;
            android.content.Intent v5 = new android.content.Intent("android.intent.action.VIEW", android.net.Uri.parse("zalopay://launch/f/qrcode")).setPackage("vn.com.vng.zalopay").setClass(v1_1, vn.com.vng.zalopay.splashscreen.presentation.SplashScreenActivityV2).setFlags(67108864);
            String v0_6 = ((androidx.appcompat.app.AppCompatActivity) this.C().get());
            if (v0_6 == null) {
                v0_8 = "";
            } else {
                v0_8 = v0_6.getString(2131951835);
                if (v0_8 == null) {
                }
            }
            q9.p5.a(v1_1, 0, v0_8, p9, v5, 0, 35);
            return;
        } else {
            return;
        }
    }
}
