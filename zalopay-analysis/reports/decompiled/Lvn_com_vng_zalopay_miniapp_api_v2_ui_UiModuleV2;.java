package vn.com.vng.zalopay.miniapp.api.v2.ui;
public final class UiModuleV2 implements LR.a, XQ.m {
    public static final EF.n j = None;
    public static final int k = 8;
    public static final String l = "screenshot";
    private final Is.a a;
    private final vn.com.vng.zalopay.navigation.b b;
    private ref.WeakReference c;
    private com.facebook.o d;
    private F6.c e;
    private XQ.n f;
    private MR.b g;
    private final ng.w h;
    private final ng.w i;

    static UiModuleV2()
    {
        vn.com.vng.zalopay.miniapp.api.v2.ui.UiModuleV2.j = new EF.n();
        return;
    }

    public UiModuleV2(Is.a p1, vn.com.vng.zalopay.navigation.b p2)
    {
        this.a = p1;
        this.b = p2;
        this.h = ng.x.b(new EF.k(this, 0));
        this.i = ng.x.b(new EF.k(this, 1));
        return;
    }

    private final void A(MR.b p6)
    {
        Integer v0_0 = this.c;
        Integer v1_0 = 0;
        if (v0_0 == null) {
            v0_0 = 0;
        }
        Integer v0_1;
        Integer v0_10 = ((androidx.fragment.app.Fragment) v0_0.get());
        if (v0_10 == null) {
            v0_1 = 0;
        } else {
            v0_1 = v0_10.getActivity();
        }
        if (v0_1 != null) {
            Integer v0_3 = v0_1.getWindow().getDecorView();
            int v4 = 0;
            if (v0_3.getResources().getConfiguration().orientation != 2) {
                Integer v0_4 = androidx.core.view.i0.a(v0_3);
                if (v0_4 != null) {
                    v1_0 = v0_4.a.f(519);
                }
                Integer v0_6;
                if (v1_0 == null) {
                    v0_6 = 0;
                } else {
                    v0_6 = v1_0.a;
                }
                Integer v2_3;
                Integer v0_7 = Integer.valueOf(v0_6);
                if (v1_0 == null) {
                    v2_3 = 0;
                } else {
                    v2_3 = v1_0.b;
                }
                Integer v3_1;
                Integer v2_4 = Integer.valueOf(v2_3);
                if (v1_0 == null) {
                    v3_1 = 0;
                } else {
                    v3_1 = v1_0.c;
                }
                Integer v3_2 = Integer.valueOf(v3_1);
                if (v1_0 != null) {
                    v4 = v1_0.d;
                }
                vn.com.vng.zalopay.miniapp.api.v2.ui.UiModuleV2.B(p6, v0_7, v2_4, v3_2, Integer.valueOf(v4));
                return;
            } else {
                if (android.os.Build$VERSION.SDK_INT < 28) {
                    vn.com.vng.zalopay.miniapp.api.v2.ui.UiModuleV2.B(p6, 0, 0, 0, 0);
                    return;
                } else {
                    Integer v0_9 = v0_3.getRootWindowInsets();
                    if (v0_9 != null) {
                        v1_0 = v0_9.getDisplayCutout();
                    }
                    Integer v0_11;
                    if (v1_0 == null) {
                        v0_11 = 0;
                    } else {
                        v0_11 = v1_0.getSafeInsetLeft();
                    }
                    Integer v2_6;
                    Integer v0_12 = Integer.valueOf(v0_11);
                    if (v1_0 == null) {
                        v2_6 = 0;
                    } else {
                        v2_6 = v1_0.getSafeInsetTop();
                    }
                    Integer v3_4;
                    Integer v2_7 = Integer.valueOf(v2_6);
                    if (v1_0 == null) {
                        v3_4 = 0;
                    } else {
                        v3_4 = v1_0.getSafeInsetRight();
                    }
                    Integer v3_5 = Integer.valueOf(v3_4);
                    if (v1_0 != null) {
                        v4 = v1_0.getSafeInsetBottom();
                    }
                    vn.com.vng.zalopay.miniapp.api.v2.ui.UiModuleV2.B(p6, v0_12, v2_7, v3_5, Integer.valueOf(v4));
                    return;
                }
            }
        } else {
            if (p6 != null) {
                p6.d();
            }
            return;
        }
    }

    private static final void B(MR.b p2, Integer p3, Integer p4, Integer p5, Integer p6)
    {
        org.json.JSONObject v0_1 = new org.json.JSONObject();
        v0_1.put("left", HF.b.a(p3));
        v0_1.put("top", HF.b.a(p4));
        v0_1.put("right", HF.b.a(p5));
        v0_1.put("bottom", HF.b.a(p6));
        if (p2 != null) {
            p2.b(v0_1);
        }
        return;
    }

    private final void C(org.json.JSONObject p1, MR.b p2)
    {
        androidx.fragment.app.FragmentActivity v1_0 = this.c;
        if (v1_0 == null) {
            v1_0 = 0;
        }
        androidx.fragment.app.FragmentActivity v1_2 = ((androidx.fragment.app.Fragment) v1_0.get());
        if (v1_2 != null) {
            androidx.fragment.app.FragmentActivity v1_3 = v1_2.getActivity();
            if (v1_3 != null) {
                com.google.android.gms.internal.mlkit_vision_barcode.S7.b(v1_3);
            }
        }
        if (p2 != null) {
            p2.success();
        }
        return;
    }

    private final void D(org.json.JSONObject p3, MR.b p4)
    {
        iG.w v3_1;
        if (p3 == null) {
            v3_1 = 0;
        } else {
            v3_1 = p3.getInt("fullScreenMode");
        }
        iG.w v3_2;
        if (v3_1 != 1) {
            v3_2 = iG.w.a;
        } else {
            v3_2 = iG.u.a;
        }
        this.q().a.j(new EF.F(v3_2));
        if (p4 != null) {
            p4.success();
        }
        return;
    }

    private final void E(org.json.JSONObject p2, MR.b p3)
    {
        boolean v2_1;
        if (!p2) {
            v2_1 = "";
        } else {
            v2_1 = p2.optString("event");
            if (!v2_1) {
            }
        }
        if (kotlin.jvm.internal.Intrinsics.areEqual(v2_1, "screenshot")) {
            this.G();
        }
        if (p3 != null) {
            p3.success();
        }
        return;
    }

    private final void G()
    {
        android.content.Context v0_0 = this.c;
        if (v0_0 == null) {
            v0_0 = 0;
        }
        android.content.Context v0_3 = ((androidx.fragment.app.Fragment) v0_0.get());
        if (v0_3 != null) {
            android.content.Context v0_4 = v0_3.getActivity();
            if (v0_4 != null) {
                android.content.Context v0_1 = v0_4.getBaseContext();
                if (v0_1 != null) {
                    XQ.n v1_1 = new XQ.n(v0_1, this);
                    this.f = v1_1;
                    v1_1.a();
                }
            }
        }
        return;
    }

    private final void H(org.json.JSONObject p5, MR.b p6)
    {
        int v1_1;
        String v0 = 0;
        try {
            if (p5 == 0) {
                v1_1 = 0;
            } else {
                v1_1 = p5.optString("backgroundColor");
            }
            try {
                if (p5 != 0) {
                    v0 = p5.optString("titleColor");
                }
            } catch (Exception) {
            }
            int v5_1;
            if (p5 == 0) {
                v5_1 = 0;
            } else {
                v5_1 = p5.optBoolean("hidden");
            }
            this.q().a.j(new EF.z(v0, v0, v1_1, v5_1));
            if (p6 != null) {
                p6.success();
            }
            return;
        } catch (Exception) {
        }
    }

    private final void I(org.json.JSONObject p4, MR.b p5)
    {
        int v1_1;
        String v0 = 0;
        try {
            if (p4 == null) {
                v1_1 = 0;
            } else {
                v1_1 = p4.optString("backgroundColor");
            }
        } catch (Exception) {
        }
        if (p4 != null) {
            v0 = p4.optString("iconColor");
        }
        this.q().a.j(new EF.A(v0, v1_1));
        if (p5 != null) {
            p5.success();
        }
        return;
    }

    private final void J(org.json.JSONObject p3, MR.b p4)
    {
        int v0_0;
        if (p3 == 0) {
            v0_0 = 0;
        } else {
            v0_0 = p3.optString("backgroundColor");
        }
        int v3_1;
        if (p3 == 0) {
            v3_1 = 0;
        } else {
            v3_1 = p3.optBoolean("hidden");
        }
        this.V(v0_0, v3_1);
        if (p4 != null) {
            p4.success();
        }
        return;
    }

    private final void K(org.json.JSONObject p6, MR.b p7)
    {
        org.json.JSONArray v6_3;
        java.util.ArrayList v0_1 = new java.util.ArrayList();
        if (p6 == null) {
            v6_3 = 0;
            if (((v6_3 instanceof org.json.JSONArray)) || ((v6_3 instanceof String))) {
                org.json.JSONArray v6_2;
                if (!(v6_3 instanceof org.json.JSONArray)) {
                    v6_2 = new org.json.JSONArray(((String) v6_3));
                } else {
                    v6_2 = ((org.json.JSONArray) v6_3);
                }
                int v1_3 = v6_2.length();
                int v2 = 0;
                while (v2 < v1_3) {
                    v0_1.add(((vn.com.vng.zalopay.domain.webapp.model.WebInternalActionModel) nY.h.b(v6_2.getJSONObject(v2).toString(), new vn.com.vng.zalopay.miniapp.api.v2.ui.UiModuleV2$handleSetToolbarActionsV2$type$1().getType())));
                    v2++;
                }
                this.U(v0_1, p7);
                if (p7 != null) {
                    p7.success();
                }
            } else {
            }
        } else {
            v6_3 = p6.get("data");
        }
        return;
    }

    private final void L(org.json.JSONObject p4, MR.b p5)
    {
        int v1_0;
        String v0_0 = 0;
        if (p4 == null) {
            v1_0 = 0;
        } else {
            v1_0 = p4.optString("url");
        }
        int v2_0;
        if (p4 == null) {
            v2_0 = 0;
        } else {
            v2_0 = p4.optString("hashtag");
        }
        if (p4 != null) {
            v0_0 = p4.optString("quote");
        }
        Jw.g v4_2 = new Jw.g(v1_0, v2_0, v0_0);
        this.g = p5;
        this.X(v4_2);
        return;
    }

    private final void M(MR.b p14)
    {
        com.google.android.gms.common.api.Status v0_0 = this.c;
        if (v0_0 == null) {
            v0_0 = 0;
        }
        int v4;
        com.google.android.gms.common.api.Status v0_13 = ((androidx.fragment.app.Fragment) v0_0.get());
        if (v0_13 == null) {
            v4 = 0;
        } else {
            v4 = v0_13.getActivity();
        }
        if (v4 != 0) {
            EF.m v6_1 = new EF.m(p14, 0);
            EF.m v5_1 = new EF.m(p14, 1);
            com.google.android.gms.tasks.q v14_1 = v4.getApplicationContext();
            if (v14_1 == null) {
                v14_1 = v4;
            }
            com.google.android.gms.tasks.q v14_8;
            com.google.android.gms.common.api.Status v0_5 = new ta.g(v14_1);
            n9.i3 v3_1 = new n9.i3();
            v3_1.b = new android.os.Handler(android.os.Looper.getMainLooper());
            v3_1.a = v0_5;
            com.google.android.gms.tasks.q v14_5 = ((ta.g) v3_1.a);
            String v2_2 = ta.g.c;
            v2_2.b("requestInAppReview (%s)", new Object[] {v14_5.b}));
            com.google.android.gms.common.api.Status v0_8 = v14_5.a;
            if (v0_8 != null) {
                com.google.android.gms.tasks.e v1_2 = new com.google.android.gms.tasks.e();
                v0_8.a().post(new ua.h(v0_8, v1_2, v1_2, new ta.e(v14_5, v1_2, v1_2)));
                v14_8 = v1_2.a;
            } else {
                com.google.android.gms.tasks.q v14_10 = new Object[0];
                if (android.util.Log.isLoggable("PlayCore", 6)) {
                    android.util.Log.e("PlayCore", Ew.C.d(v2_2.a, "Play Store app is either not installed or not the official version", v14_10));
                }
                String v9_3;
                String v2_6 = java.util.Locale.getDefault();
                Object[] v8_0 = Integer.valueOf(-1);
                String v9_0 = va.b.a;
                String v10_0 = Integer.valueOf(-1);
                if (v9_0.containsKey(v10_0)) {
                    v9_3 = Kb.e.o(((String) v9_0.get(v10_0)), " (https://developer.android.com/reference/com/google/android/play/core/review/model/ReviewErrorCode.html#", ((String) va.b.b.get(v10_0)), ")");
                } else {
                    v9_3 = "";
                }
                v14_8 = p9.t6.d(new com.google.android.play.core.review.ReviewException(new com.google.android.gms.common.api.Status(-1, String.format(v2_6, "Review Error(%d): %s", new Object[] {v8_0, v9_3})), 0, 0)));
            }
            v14_8.addOnCompleteListener(new IX.a(v3_1, v4, v5_1, v6_1, 0));
            return;
        } else {
            if (p14 != null) {
                p14.d();
            }
            return;
        }
    }

    private static final kotlin.Unit N(MR.b p0)
    {
        if (p0 != null) {
            p0.success();
        }
        return kotlin.Unit.INSTANCE;
    }

    private static final kotlin.Unit O(MR.b p0)
    {
        if (p0 != null) {
            p0.d();
        }
        return kotlin.Unit.INSTANCE;
    }

    private final void P()
    {
        this.d = new com.facebook.internal.i();
        com.facebook.o v0_3 = this.c;
        F6.c v1_1 = 0;
        if (v0_3 == null) {
            v0_3 = 0;
        }
        com.facebook.o v0_5 = ((androidx.fragment.app.Fragment) v0_3.get());
        if (v0_5 != null) {
            v1_1 = new F6.c(v0_5);
        }
        this.e = v1_1;
        com.facebook.o v0_2 = this.d;
        if ((v0_2 != null) && (v1_1 != null)) {
            v1_1.c(v0_2, new AW.a(6, this));
        }
        return;
    }

    private final void U(java.util.List p2, MR.b p3)
    {
        if (!p2.isEmpty()) {
            this.W(p2, p3);
            return;
        } else {
            if (p3 != null) {
                p3.h(ZF.m.c);
            }
            return;
        }
    }

    private final void V(String p2, boolean p3)
    {
        this.q().a.j(new EF.C(p2));
        return;
    }

    private final void W(java.util.List p3, MR.b p4)
    {
        this.q().a.j(new EF.E(p3));
        if (p4 != null) {
            p4.success();
        }
        return;
    }

    private final void X(Jw.g p4)
    {
        if (com.google.android.gms.internal.mlkit_vision_barcode.W5.a()) {
            F6.c v0_6 = this.c;
            Ai.z v1_0 = 0;
            if (v0_6 == null) {
                v0_6 = 0;
            }
            F6.c v0_1;
            F6.c v0_0 = ((androidx.fragment.app.Fragment) v0_6.get());
            if (v0_0 == null) {
                v0_1 = 0;
            } else {
                v0_1 = v0_0.getString(2131953950);
            }
            E6.f v2_1 = this.c;
            if (v2_1 == null) {
                v2_1 = 0;
            }
            E6.f v2_3 = ((androidx.fragment.app.Fragment) v2_1.get());
            if (v2_3 != null) {
                v1_0 = v2_3.getContext();
            }
            if (com.google.android.gms.internal.mlkit_vision_barcode.x0.g(v1_0, v0_1)) {
                F6.c v0_5 = new com.android.billingclient.api.c0();
                v0_5.b = android.net.Uri.parse(p4.a);
                Ai.z v1_4 = new Ai.z();
                v1_4.a = p4.b;
                v0_5.c = new E6.f(v1_4);
                v0_5.d = p4.c;
                E6.g v4_3 = new E6.g(v0_5);
                F6.c v0_7 = this.e;
                if (v0_7 != null) {
                    v0_7.d(v4_3);
                }
            }
        }
        return;
    }

    private static final EF.p Y(vn.com.vng.zalopay.miniapp.api.v2.ui.UiModuleV2 p1)
    {
        return new EF.p(p1);
    }

    public static synthetic kotlin.Unit b(MR.b p0, String p1)
    {
        return vn.com.vng.zalopay.miniapp.api.v2.ui.UiModuleV2.x(p0, p1);
    }

    public static synthetic kotlin.Unit c(MR.b p0)
    {
        return vn.com.vng.zalopay.miniapp.api.v2.ui.UiModuleV2.O(p0);
    }

    public static synthetic kotlin.Unit d(MR.b p0, A1.n p1, A1.l p2)
    {
        return vn.com.vng.zalopay.miniapp.api.v2.ui.UiModuleV2.z(p0, p1, p2);
    }

    public static synthetic EF.p e(vn.com.vng.zalopay.miniapp.api.v2.ui.UiModuleV2 p0)
    {
        return vn.com.vng.zalopay.miniapp.api.v2.ui.UiModuleV2.Y(p0);
    }

    public static synthetic kotlin.Unit f(MR.b p0, int p1)
    {
        return vn.com.vng.zalopay.miniapp.api.v2.ui.UiModuleV2.p(p0, p1);
    }

    public static synthetic kotlin.Unit g(MR.b p0)
    {
        return vn.com.vng.zalopay.miniapp.api.v2.ui.UiModuleV2.N(p0);
    }

    public static synthetic EF.o h(vn.com.vng.zalopay.miniapp.api.v2.ui.UiModuleV2 p0)
    {
        return vn.com.vng.zalopay.miniapp.api.v2.ui.UiModuleV2.i(p0);
    }

    private static final EF.o i(vn.com.vng.zalopay.miniapp.api.v2.ui.UiModuleV2 p0)
    {
        return new EF.o();
    }

    public static final synthetic EF.o j(vn.com.vng.zalopay.miniapp.api.v2.ui.UiModuleV2 p0)
    {
        return p0.q();
    }

    public static synthetic void m(vn.com.vng.zalopay.miniapp.api.v2.ui.UiModuleV2 p1, String p2, org.json.JSONObject p3, MR.b p4, int p5, Object p6)
    {
        if ((p5 & 2) != 0) {
            p3 = 0;
        }
        if ((p5 & 4) != 0) {
            p4 = 0;
        }
        p1.l(p2, p3, p4);
        return;
    }

    private final void o(org.json.JSONObject p4, MR.b p5)
    {
        this.q().a.j(new EF.w(new EF.l(p5, 1)));
        return;
    }

    private static final kotlin.Unit p(MR.b p2, int p3)
    {
        org.json.JSONObject v0_1 = new org.json.JSONObject();
        v0_1.put("topContentPadding", p3);
        if (p2 != null) {
            p2.b(v0_1);
        }
        return kotlin.Unit.INSTANCE;
    }

    private final EF.o q()
    {
        return ((EF.o) this.h.getValue());
    }

    private final void r(org.json.JSONObject p3, MR.b p4)
    {
        String v0 = 0;
        if (p3 != null) {
            v0 = p3.optString("colorHexString");
        }
        this.q().a.j(new EF.B(v0));
        if (p4 != null) {
            p4.success();
        }
        return;
    }

    private final void s(org.json.JSONObject p3, MR.b p4)
    {
        String v0_0 = 1;
        if (p3 == null) {
            this.q().a.j(new EF.D(v0_0));
            if (p4 != null) {
                p4.success();
            }
        } else {
            try {
                v0_0 = p3.optBoolean("isWhiteText", 1);
            } catch (Exception) {
                Object[] v3_4 = new Object[0];
                ck.b.e("Error when parse isWhiteText", v3_4);
                if (p4 != null) {
                    p4.d();
                }
            }
        }
        return;
    }

    private final void t(org.json.JSONObject p3, MR.b p4)
    {
        ZF.m v3_1;
        if (p3 == null) {
            v3_1 = 0;
        } else {
            v3_1 = p3.optString("url");
        }
        if ((v3_1 != null) && (v3_1.length() != 0)) {
            this.q().b.j(new EF.q(v3_1));
            if (p4 != null) {
                p4.success();
                return;
            }
        } else {
            if (p4 != null) {
                p4.h(ZF.m.c);
            }
        }
        return;
    }

    private final void u(org.json.JSONObject p2, MR.b p3)
    {
        this.q().b.j(EF.r.a);
        if (p3 != null) {
            p3.success();
        }
        return;
    }

    private final void v(org.json.JSONObject p2, MR.b p3)
    {
        this.q().b.j(EF.s.a);
        if (p3 != null) {
            p3.success();
        }
        return;
    }

    private final void w(org.json.JSONObject p4, MR.b p5)
    {
        this.q().a.j(new EF.v(new EF.l(p5, 0)));
        return;
    }

    private static final kotlin.Unit x(MR.b p2, String p3)
    {
        if (p2 != null) {
            org.json.JSONObject v0_1 = new org.json.JSONObject();
            v0_1.put("displayMode", p3);
            p2.b(v0_1);
        }
        return kotlin.Unit.INSTANCE;
    }

    private final void y(MR.b p5)
    {
        this.q().a.j(new EF.u(new AA.e(7, p5)));
        return;
    }

    private static final kotlin.Unit z(MR.b p8, A1.n p9, A1.l p10)
    {
        org.json.JSONObject v0_1 = new org.json.JSONObject();
        v0_1.put("left", HF.b.a(Integer.valueOf(((int) (p10.a >> 32)))));
        String v1_0 = p10.a;
        String v10_1 = ((int) (v1_0 & 4294967295));
        v0_1.put("top", HF.b.a(Integer.valueOf(v10_1)));
        v0_1.put("right", HF.b.a(Integer.valueOf((((int) (v1_0 >> 32)) + ((int) (p9.a >> 32))))));
        String v1_7 = p9.a;
        int v9_1 = ((int) (4294967295 & v1_7));
        v0_1.put("bottom", HF.b.a(Integer.valueOf((v10_1 + v9_1))));
        v0_1.put("width", HF.b.a(Integer.valueOf(((int) (v1_7 >> 32)))));
        v0_1.put("height", HF.b.a(Integer.valueOf(v9_1)));
        if (p8 != null) {
            p8.b(v0_1);
        }
        return kotlin.Unit.INSTANCE;
    }

    public final void F(org.json.JSONObject p2, MR.b p3)
    {
        this.q().a.j(EF.x.a);
        if (p3 != null) {
            p3.success();
        }
        return;
    }

    public void Q()
    {
        return;
    }

    public final void R()
    {
        MR.b v0 = this.g;
        if (v0 != null) {
            v0.h(ZF.w.d);
        }
        return;
    }

    public final void S(com.facebook.FacebookException p2)
    {
        p2 = this.g;
        if (p2 != null) {
            p2.h(ZF.w.c);
        }
        return;
    }

    public final void T()
    {
        MR.b v0 = this.g;
        if (v0 != null) {
            v0.success();
        }
        return;
    }

    public void a(String p2, org.json.JSONObject p3, MR.b p4)
    {
        if (p2) {
            switch (p2.hashCode()) {
                case -2105430107:
                    if (p2.equals("hideKeyboardV2")) {
                        this.C(p3, p4);
                    }
                    break;
                case -2051362058:
                    if (p2.equals("getMenuButtonBoundingClientRectV2")) {
                        this.y(p4);
                        return;
                    }
                case -1955408004:
                    if (p2.equals("changeInsetSafeAreaColor")) {
                        this.r(p3, p4);
                        return;
                    }
                case -1881095099:
                    if (p2.equals("changeStatusBarTextColor")) {
                        this.s(p3, p4);
                        return;
                    }
                case -1860521524:
                    if (p2.equals("getSafeAreaV2")) {
                        this.A(p4);
                        return;
                    }
                case -1835100731:
                    if (p2.equals("openFullScreen")) {
                        this.D(p3, p4);
                        return;
                    }
                case -1582490441:
                    if (p2.equals("shareFBV2")) {
                        this.L(p3, p4);
                        return;
                    }
                case -1479610025:
                    if (p2.equals("getTopContentPadding")) {
                        this.o(p3, p4);
                        return;
                    }
                case -1356639328:
                    if (p2.equals("setToolbarActionsV2")) {
                        this.K(p3, p4);
                        return;
                    }
                case -855602281:
                    if (p2.equals("setActionBarV2")) {
                        this.H(p3, p4);
                        return;
                    }
                case -690213213:
                    if (p2.equals("register")) {
                        this.E(p3, p4);
                        return;
                    }
                case -365586360:
                    if (p2.equals("setNavigationBarSystemV2")) {
                        this.I(p3, p4);
                        return;
                    }
                case -339012338:
                    if (p2.equals("getFullScreenModeDisplay")) {
                        this.w(p3, p4);
                        return;
                    }
                case -280593102:
                    if (p2.equals("closeMiniApp")) {
                        this.u(p3, p4);
                        return;
                    }
                case 136541348:
                    if (p2.equals("closeWindowV2")) {
                        this.v(p3, p4);
                        return;
                    }
                case 255005112:
                    if (p2.equals("closeAndOpenApp")) {
                        this.t(p3, p4);
                        return;
                    }
                case 964423515:
                    if (p2.equals("setStatusBarV2")) {
                        this.J(p3, p4);
                        return;
                    }
                case 1491878972:
                    if (p2.equals("showInAppRating")) {
                        this.M(p4);
                        return;
                    }
                case 1774942272:
                    if (p2.equals("reloadWebView")) {
                        this.F(p3, p4);
                        return;
                    }
                default:
            }
        }
        return;
    }

    public final void k(ref.WeakReference p1)
    {
        this.c = p1;
        this.P();
        return;
    }

    public final void l(String p1, org.json.JSONObject p2, MR.b p3)
    {
        this.a(p1, p2, p3);
        return;
    }

    public final EF.p n()
    {
        return ((EF.p) this.i.getValue());
    }

    public void onCreate()
    {
        return;
    }

    public void onScreenCaptured()
    {
        this.q().a.j(EF.y.a);
        return;
    }
}
