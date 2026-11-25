package vn.com.vng.zalopay.miniapp.api.v1.ui;
public final class UiModule implements LR.a, XQ.m {
    public static final kF.d i = None;
    public static final int j = 8;
    public static final String k = "UIModuleProgressDialog";
    public static final String l = "UIModuleErrorDialog";
    public static final String m = "label";
    public static final String n = "screenshot";
    private final Pt.n a;
    private final Pt.c b;
    private final vn.com.vng.zalopay.navigation.b c;
    private kF.a d;
    private ref.WeakReference e;
    private final ng.w f;
    private final ng.w g;
    private XQ.n h;

    static UiModule()
    {
        vn.com.vng.zalopay.miniapp.api.v1.ui.UiModule.i = new kF.d();
        return;
    }

    public UiModule(Pt.n p1, Pt.c p2, vn.com.vng.zalopay.navigation.b p3)
    {
        this.a = p1;
        this.b = p2;
        this.c = p3;
        this.f = ng.x.b(new kF.c(this, 0));
        this.g = ng.x.b(new kF.c(this, 1));
        return;
    }

    private final void A(org.json.JSONObject p8, MR.b p9)
    {
        Object[] v1_0;
        if (p8 == null) {
            v1_0 = 0;
        } else {
            v1_0 = p8.optJSONObject("data");
        }
        Throwable v8_2;
        if (p8 == null) {
            v8_2 = 0;
        } else {
            v8_2 = Integer.valueOf(p8.optInt("code"));
        }
        android.content.Intent v2_1 = new android.content.Intent();
        if (v1_0 == null) {
            this.s(v2_1, v8_2);
            if (p9 != null) {
                p9.f(0);
                return;
            }
        } else {
            java.util.Iterator v3 = v1_0.keys();
            if (v3 == null) {
            }
            while (v3.hasNext()) {
                String v4_2 = ((String) v3.next());
                Object v5 = v1_0.get(v4_2);
                if ((!(v5 instanceof org.json.JSONObject)) && (!(v5 instanceof org.json.JSONArray))) {
                    q9.b5.a(v2_1, v4_2, v5);
                }
            }
        }
        return;
    }

    private final void B()
    {
        String v0_0 = this.d;
        if (v0_0 == null) {
            v0_0 = 0;
        }
        String v0_3;
        String v0_2 = v0_0.getAppId();
        if (v0_2 == null) {
            v0_3 = -1;
        } else {
            v0_3 = v0_2.longValue();
        }
        if (v0_3 >= 0) {
            this.b.put(String.format(java.util.Locale.getDefault(), "%s_%d_ToolTip", java.util.Arrays.copyOf(new Object[] {this.a.getUserId(), Long.valueOf(v0_3)}), 2)), String.valueOf(v0_3));
            return;
        } else {
            return;
        }
    }

    private final void C(vn.com.vng.zalopay.domain.webapp.model.WebInternalActionModel p1)
    {
        nY.h.d(p1);
        return;
    }

    private final void D(java.util.List p1)
    {
        return;
    }

    private final void E(org.json.JSONObject p6, MR.b p7)
    {
        try {
            String v1_1;
            if (p6 == 0) {
                v1_1 = 0;
                if (v1_1 != null) {
                    v1_1.optString("backgroundColor");
                    v1_1.optString("titleColor");
                    v1_1.optBoolean("pullToRefresh");
                    v1_1.optBoolean("hidden");
                }
                int v6_1;
                if (p6 == 0) {
                    v6_1 = 0;
                } else {
                    v6_1 = p6.optJSONObject("statusBar");
                }
                if (v6_1 != 0) {
                    String v1_2 = v6_1.optString("backgroundColor");
                    v6_1.optBoolean("hidden");
                    if ((v1_2 != null) && (v1_2.length() == 0)) {
                        if (p7 == null) {
                        } else {
                            p7.f(0);
                            return;
                        }
                    }
                }
                if (p7 != null) {
                    p7.f(0);
                }
            } else {
                v1_1 = p6.optJSONObject("navigation");
            }
        } catch (int v6_2) {
            v6_2.getMessage();
            if (p7 == null) {
            } else {
                p7.f(0);
            }
        } catch (int v6_3) {
            if (p7 != null) {
                p7.f(0);
            }
            throw v6_3;
        }
        return;
    }

    private final void F(org.json.JSONObject p8, MR.b p9)
    {
        org.json.JSONArray v8_4;
        java.util.ArrayList v0_1 = new java.util.ArrayList();
        if (p8 == null) {
            v8_4 = 0;
            if (((v8_4 instanceof org.json.JSONArray)) || ((v8_4 instanceof String))) {
                org.json.JSONArray v8_1;
                if (!(v8_4 instanceof org.json.JSONArray)) {
                    v8_1 = new org.json.JSONArray(v8_4);
                } else {
                    v8_1 = ((org.json.JSONArray) v8_4);
                }
                int v2_4 = v8_1.length();
                int v4 = 0;
                while (v4 < v2_4) {
                    v0_1.add(((vn.com.vng.zalopay.domain.webapp.model.WebInternalActionModel) nY.h.b(v8_1.getJSONObject(0).toString(), new vn.com.vng.zalopay.miniapp.api.v1.ui.UiModule$setToolbarActions$type$1().getType())));
                    v4++;
                }
                if (!com.google.android.gms.internal.mlkit_vision_barcode.p0.b(v0_1)) {
                    this.D(v0_1);
                    if (p9 != null) {
                        p9.f(0);
                    }
                } else {
                    if (p9 != null) {
                        p9.f(0);
                    }
                }
            } else {
            }
        } else {
            v8_4 = p8.opt("data");
        }
        return;
    }

    private final boolean G()
    {
        int v0_0 = java.util.Locale.getDefault();
        Long v1_7 = this.a.getUserId();
        long v2_5 = this.d;
        kF.a v3 = 0;
        if (v2_5 == 0) {
            v2_5 = 0;
        }
        int v0_2 = this.b.a(String.format(v0_0, "%s_%d_ToolTip", java.util.Arrays.copyOf(new Object[] {v1_7, v2_5.getAppId()}), 2)), "");
        if (v0_2.length() != 0) {
            Long v1_5 = this.d;
            if (v1_5 != null) {
                v3 = v1_5;
            }
            Long v1_6 = v3.getAppId();
            long v2_4 = Long.parseLong(v0_2);
            if ((v1_6 != null) && (v1_6.longValue() == v2_4)) {
                return 0;
            }
        }
        return 1;
    }

    private final void H(org.json.JSONObject p9, MR.b p10)
    {
        int v4;
        String[] v0 = 0;
        if (p9 == null) {
            v4 = 0;
        } else {
            v4 = p9.optString("message");
        }
        androidx.fragment.app.FragmentActivity v9_1;
        if (p9 == null) {
            v9_1 = 0;
        } else {
            v9_1 = p9.optString("button");
        }
        String[] v5;
        if (v9_1 == null) {
            v5 = 0;
        } else {
            androidx.fragment.app.FragmentActivity v9_2 = new kotlin.text.Regex(",").split(v9_1, 0);
            if (v9_2 == null) {
            } else {
                androidx.fragment.app.FragmentActivity v9_3;
                if (v9_2.isEmpty()) {
                    v9_3 = kotlin.collections.E.emptyList();
                } else {
                    int v1_4 = v9_2.listIterator(v9_2.size());
                    while (v1_4.hasPrevious()) {
                        if (((String) v1_4.previous()).length() != 0) {
                            v9_3 = kotlin.collections.CollectionsKt.take(((Iterable) v9_2), (v1_4.nextIndex() + 1));
                        }
                    }
                }
                if (v9_3 == null) {
                } else {
                    int v1_8 = new String[0];
                    v5 = ((String[]) ((java.util.Collection) v9_3).toArray(v1_8));
                }
            }
        }
        if ((v4 != 0) && (v5 != null)) {
            androidx.fragment.app.FragmentActivity v9_8 = this.e;
            if (v9_8 != null) {
                v0 = v9_8;
            }
            androidx.fragment.app.FragmentActivity v9_10 = ((androidx.fragment.app.Fragment) v0.get());
            if (v9_10 != null) {
                androidx.fragment.app.FragmentActivity v9_11 = v9_10.getActivity();
                if (v9_11 != null) {
                    v9_11.runOnUiThread(new F.k0(this, v4, v5, p10, 18));
                    return;
                }
            }
        } else {
            if (p10 != null) {
                p10.a(2, "showDialog");
            }
        }
        return;
    }

    private static final void I(vn.com.vng.zalopay.miniapp.api.v1.ui.UiModule p9, String p10, String[] p11, MR.b p12)
    {
        Throwable v0_0 = p9.e;
        androidx.fragment.app.FragmentActivity v1 = 0;
        if (v0_0 == null) {
            v0_0 = 0;
        }
        Throwable v0_4 = ((androidx.fragment.app.Fragment) v0_0.get());
        if (v0_4 != null) {
            v1 = v0_4.getActivity();
        }
        Class v9_2;
        androidx.fragment.app.FragmentActivity v2 = v1;
        kF.g v8_1 = new kF.g(p12, p9, p11);
        if (p11.length < 2) {
            v9_2 = p11[0];
        } else {
            v9_2 = p11[1];
        }
        Class v9_5;
        if (p11.length < 2) {
            v9_5 = "";
        } else {
            v9_5 = p11[0];
        }
        p9 = DZ.i;
        DZ.i.c(v2, p10, 0, v9_2, v9_5, 0, v8_1);
        return;
    }

    private final void K(org.json.JSONObject p5, MR.b p6)
    {
        int v5_1;
        if (p5 == 0) {
            v5_1 = 0;
        } else {
            v5_1 = p5.optString("message");
        }
        if (v5_1 != 0) {
            androidx.fragment.app.FragmentActivity v1_4 = this.e;
            if (v1_4 == null) {
                v1_4 = 0;
            }
            androidx.fragment.app.FragmentActivity v1_1 = ((androidx.fragment.app.Fragment) v1_4.get());
            if (v1_1 != null) {
                androidx.fragment.app.FragmentActivity v1_2 = v1_1.getActivity();
                if (v1_2 != null) {
                    v1_2.runOnUiThread(new com.facebook.appevents.i(this, v5_1, 29));
                }
            }
        }
        if (p6 != null) {
            p6.f(0);
        }
        return;
    }

    private static final void L(vn.com.vng.zalopay.miniapp.api.v1.ui.UiModule p1, String p2)
    {
        androidx.fragment.app.Fragment v1_1 = p1.e;
        androidx.fragment.app.FragmentActivity v0 = 0;
        if (v1_1 == null) {
            v1_1 = 0;
        }
        androidx.fragment.app.Fragment v1_3 = ((androidx.fragment.app.Fragment) v1_1.get());
        if (v1_3 != null) {
            v0 = v1_3.getActivity();
        }
        com.google.android.gms.internal.mlkit_vision_barcode.h7.e(v0, p2);
        return;
    }

    private final void M(org.json.JSONObject p2, MR.b p3)
    {
        if (p2) {
            p2.optInt("position");
        }
        if (p2) {
            p2.optString("message");
        }
        if (p2) {
            p2.optString("iconLink");
        }
        if (p2) {
            p2.optString("iconName");
        }
        if (this.G()) {
            this.B();
            if (p3 != null) {
                p3.f(0);
            }
        } else {
            if (p3 != null) {
                p3.f(0);
                return;
            }
        }
        return;
    }

    private static final kF.f N(vn.com.vng.zalopay.miniapp.api.v1.ui.UiModule p1)
    {
        return new kF.f(p1);
    }

    public static synthetic void b(vn.com.vng.zalopay.miniapp.api.v1.ui.UiModule p0, String p1, String[] p2, MR.b p3)
    {
        vn.com.vng.zalopay.miniapp.api.v1.ui.UiModule.I(p0, p1, p2, p3);
        return;
    }

    public static synthetic kF.f c(vn.com.vng.zalopay.miniapp.api.v1.ui.UiModule p0)
    {
        return vn.com.vng.zalopay.miniapp.api.v1.ui.UiModule.N(p0);
    }

    public static synthetic void d(vn.com.vng.zalopay.miniapp.api.v1.ui.UiModule p0, String p1)
    {
        vn.com.vng.zalopay.miniapp.api.v1.ui.UiModule.L(p0, p1);
        return;
    }

    public static synthetic kF.e e(vn.com.vng.zalopay.miniapp.api.v1.ui.UiModule p0)
    {
        return vn.com.vng.zalopay.miniapp.api.v1.ui.UiModule.f(p0);
    }

    private static final kF.e f(vn.com.vng.zalopay.miniapp.api.v1.ui.UiModule p0)
    {
        return new kF.e();
    }

    public static final synthetic org.json.JSONObject g(vn.com.vng.zalopay.miniapp.api.v1.ui.UiModule p0, String p1)
    {
        return p0.n(p1);
    }

    public static final synthetic kF.e h(vn.com.vng.zalopay.miniapp.api.v1.ui.UiModule p0)
    {
        return p0.q();
    }

    private final void j(MR.b p3)
    {
        this.q().b.j(EF.s.a);
        if (p3 != null) {
            p3.f(0);
        }
        return;
    }

    private final void k()
    {
        androidx.fragment.app.FragmentActivity v0_0 = this.e;
        boolean v1 = 0;
        if (v0_0 == null) {
            v0_0 = 0;
        }
        androidx.fragment.app.FragmentActivity v0_7 = ((androidx.fragment.app.Fragment) v0_0.get());
        if (v0_7 != null) {
            androidx.fragment.app.FragmentActivity v0_8 = v0_7.getActivity();
            if ((v0_8 != null) && (v0_8.isFinishing() == 1)) {
                return;
            }
        }
        androidx.fragment.app.FragmentActivity v0_2 = this.e;
        if (v0_2 != null) {
            v1 = v0_2;
        }
        androidx.fragment.app.FragmentActivity v0_4 = ((androidx.fragment.app.Fragment) v1.get());
        if (v0_4 != null) {
            androidx.fragment.app.FragmentActivity v0_5 = v0_4.getActivity();
            if (v0_5 != null) {
                v0_5.finish();
            }
        }
        return;
    }

    private final org.json.JSONObject n(String p3)
    {
        org.json.JSONObject v0_1 = new org.json.JSONObject();
        try {
            v0_1.put("label", p3);
        } catch (org.json.JSONException) {
        }
        return v0_1;
    }

    private final kF.e q()
    {
        return ((kF.e) this.f.getValue());
    }

    private final void r(Ew.y p1, android.app.Activity p2)
    {
        return;
    }

    private final void s(android.content.Intent p3, Integer p4)
    {
        ref.WeakReference v0 = 0;
        if ((p4 == null) || (p4.intValue() != -1)) {
            androidx.fragment.app.FragmentActivity v4_4 = this.e;
            if (v4_4 == null) {
                v4_4 = 0;
            }
            androidx.fragment.app.FragmentActivity v4_6 = ((androidx.fragment.app.Fragment) v4_4.get());
            if (v4_6 != null) {
                androidx.fragment.app.FragmentActivity v4_7 = v4_6.getActivity();
                if (v4_7 != null) {
                    v4_7.setResult(1, p3);
                }
            }
        } else {
            androidx.fragment.app.FragmentActivity v4_9 = this.e;
            if (v4_9 == null) {
                v4_9 = 0;
            }
            androidx.fragment.app.FragmentActivity v4_2 = ((androidx.fragment.app.Fragment) v4_9.get());
            if (v4_2 != null) {
                androidx.fragment.app.FragmentActivity v4_3 = v4_2.getActivity();
                if (v4_3 != null) {
                    v4_3.setResult(-1, p3);
                }
            }
        }
        androidx.fragment.app.FragmentActivity v3_1 = this.e;
        if (v3_1 != null) {
            v0 = v3_1;
        }
        androidx.fragment.app.FragmentActivity v3_3 = ((androidx.fragment.app.Fragment) v0.get());
        if (v3_3 != null) {
            androidx.fragment.app.FragmentActivity v3_4 = v3_3.getActivity();
            if (v3_4 != null) {
                v3_4.finish();
            }
        }
        return;
    }

    private final void t(org.json.JSONObject p2, MR.b p3)
    {
        int v2_1;
        if (p2 == 0) {
            v2_1 = 0;
        } else {
            v2_1 = p2.optString("event");
        }
        if (!kotlin.jvm.internal.Intrinsics.areEqual(v2_1, "screenshot")) {
            if ((v2_1 == 0) && (p3 != null)) {
                p3.a(2, "register");
            }
            return;
        } else {
            this.u();
            return;
        }
    }

    private final void u()
    {
        android.content.Context v0_0 = this.e;
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
                    this.h = v1_1;
                    v1_1.a();
                }
            }
        }
        return;
    }

    private final void z(org.json.JSONObject p3, MR.b p4)
    {
        int v3_2;
        if (p3 == 0) {
            v3_2 = 0;
        } else {
            v3_2 = p3.optString("url");
        }
        if ((v3_2 != 0) && (v3_2.length() != 0)) {
            String v1_0 = this.d;
            if (v1_0 == null) {
                v1_0 = 0;
            }
            v1_0.p(v3_2);
            if (p4 != null) {
                p4.f(0);
                return;
            }
        } else {
            if (p4 != null) {
                p4.a(2, "pushView");
            }
        }
        return;
    }

    public final void J()
    {
        androidx.fragment.app.Fragment v0_0 = this.e;
        if (v0_0 == null) {
            v0_0 = 0;
        }
        androidx.fragment.app.Fragment v0_2 = ((androidx.fragment.app.Fragment) v0_0.get());
        if (v0_2 != null) {
            com.google.android.gms.internal.mlkit_vision_barcode.T7.l(v0_2, "UIModuleProgressDialog");
        }
        return;
    }

    public void a(String p3, org.json.JSONObject p4, MR.b p5)
    {
        if (p3) {
            switch (p3.hashCode()) {
                case -1913642710:
                    if (p3.equals("showToast")) {
                        this.K(p4, p5);
                    }
                    break;
                case -1297499644:
                    if (p3.equals("setToolbarActions")) {
                        this.F(p4, p5);
                        return;
                    }
                case -751917498:
                    if (p3.equals("showTooltip")) {
                        this.M(p4, p5);
                        return;
                    }
                case -690213213:
                    if (p3.equals("register")) {
                        this.t(p4, p5);
                        return;
                    }
                case -220909507:
                    if (p3.equals("putExtraIntent")) {
                        this.A(p4, p5);
                        return;
                    }
                case 216239514:
                    if (p3.equals("hideLoading")) {
                        this.v();
                        if (p5 != null) {
                            p5.f(0);
                            return;
                        }
                    }
                case 277236744:
                    if (p3.equals("closeWindow")) {
                        this.j(p5);
                        return;
                    }
                case 343003813:
                    if (p3.equals("showDialog")) {
                        this.H(p4, p5);
                        return;
                    }
                case 724809599:
                    if (p3.equals("showLoading")) {
                        this.J();
                        if (p5 != null) {
                            p5.f(0);
                            return;
                        }
                    }
                case 996179031:
                    if (p3.equals("setProperty")) {
                        this.E(p4, p5);
                        return;
                    }
                case 1776042207:
                    if (p3.equals("pushView")) {
                        this.z(p4, p5);
                        return;
                    }
                default:
            }
        }
        return;
    }

    public final void i(ref.WeakReference p1, kF.a p2)
    {
        this.e = p1;
        this.d = p2;
        return;
    }

    public final Pt.c l()
    {
        return this.b;
    }

    public final vn.com.vng.zalopay.navigation.b m()
    {
        return this.c;
    }

    public final kF.f o()
    {
        return ((kF.f) this.g.getValue());
    }

    public void onCreate()
    {
        return;
    }

    public void onScreenCaptured()
    {
        int v0 = this.d;
        if (v0 == 0) {
            v0 = 0;
        }
        v0.M();
        return;
    }

    public final Pt.n p()
    {
        return this.a;
    }

    public final void v()
    {
        androidx.fragment.app.Fragment v0_0 = this.e;
        if (v0_0 == null) {
            v0_0 = 0;
        }
        androidx.fragment.app.Fragment v0_2 = ((androidx.fragment.app.Fragment) v0_0.get());
        if (v0_2 != null) {
            com.google.android.gms.internal.mlkit_vision_barcode.T7.b(v0_2, "UIModuleProgressDialog");
        }
        return;
    }

    public void w()
    {
        XQ.n v0 = this.h;
        if (v0 != null) {
            v0.b();
        }
        return;
    }

    public final void x()
    {
        XQ.n v0 = this.h;
        if (v0 != null) {
            v0.b();
        }
        return;
    }

    public final void y()
    {
        XQ.n v0 = this.h;
        if (v0 != null) {
            v0.a();
        }
        return;
    }
}
