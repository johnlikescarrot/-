package vn.com.vng.zalopay.miniapp.api.v2.common;
public final class UserLocalConfigModule implements LR.a {
    public static final int c = 8;
    private final Pt.n a;
    private ref.WeakReference b;

    public UserLocalConfigModule(Pt.n p1)
    {
        this.a = p1;
        return;
    }

    public static final synthetic ref.WeakReference b(vn.com.vng.zalopay.miniapp.api.v2.common.UserLocalConfigModule p0)
    {
        return p0.b;
    }

    public static final synthetic void c(vn.com.vng.zalopay.miniapp.api.v2.common.UserLocalConfigModule p0, android.content.Context p1, MR.b p2)
    {
        p0.s(p1, p2);
        return;
    }

    public static final synthetic void d(vn.com.vng.zalopay.miniapp.api.v2.common.UserLocalConfigModule p0, android.content.Context p1, String p2, MR.b p3, boolean p4)
    {
        p0.t(p1, p2, p3, p4);
        return;
    }

    private final void f()
    {
        if (n9.O.a == null) {
            n9.O.a = new Iz.b(new jQ.B(7), q9.a5.b());
            return;
        } else {
            return;
        }
    }

    private final void g(org.json.JSONObject p5, MR.b p6)
    {
        String v0_5;
        String v1 = 0;
        ZF.x v5_7 = vn.com.vng.zalopay.miniapp.api.v2.common.UserLocalConfigModule.m(this, p5, 0, 2, 0);
        if (v5_7 == null) {
            v0_5 = 0;
        } else {
            v0_5 = v5_7.b();
        }
        if ((kotlin.jvm.internal.Intrinsics.areEqual(v0_5, "bio")) && (kotlin.jvm.internal.Intrinsics.areEqual(v5_7.a(), "on"))) {
            ZF.x v5_3 = this.b;
            if (v5_3 == null) {
                v5_3 = 0;
            }
            ZF.x v5_6;
            ZF.x v5_5 = ((androidx.fragment.app.Fragment) v5_3.get());
            if (v5_5 == null) {
                v5_6 = 0;
            } else {
                v5_6 = v5_5.getContext();
            }
            if (v5_6 != null) {
                this.f();
                if (com.facebook.applinks.b.w(v5_6).m(15) != 0) {
                    ZF.x v5_8 = this.b;
                    if (v5_8 != null) {
                        v1 = v5_8;
                    }
                    ZF.x v5_10 = ((androidx.fragment.app.Fragment) v1.get());
                    if (v5_10 != null) {
                        ZF.x v5_11 = v5_10.getActivity();
                        if (v5_11 != null) {
                            com.google.android.gms.internal.mlkit_vision_barcode.h7.e(v5_11, "Thi\u1ebft b\u1ecb kh\u00f4ng h\u1ed7 tr\u1ee3 x\u00e1c th\u1ef1c v\u00e2n tay.");
                        }
                    }
                    if (p6 != null) {
                        p6.h(ZF.x.f);
                        return;
                    }
                } else {
                    boolean v2_2 = com.google.android.gms.internal.mlkit_vision_barcode.W6.c;
                    if (!v2_2) {
                        v2_2 = "";
                    }
                    if (!kotlin.text.StringsKt.isBlank(v2_2)) {
                        v1 = com.google.android.gms.internal.mlkit_vision_barcode.W6.c;
                        if (v1 == null) {
                            v1 = "";
                        }
                    }
                    if ((v1 != null) && (!kotlin.text.StringsKt.isBlank(v1))) {
                        this.t(v5_6, v1, p6, 1);
                        return;
                    } else {
                        this.s(v5_6, p6);
                        return;
                    }
                }
            } else {
                if (p6 != null) {
                    p6.k("", "Native context error!");
                    return;
                }
            }
        } else {
            if (p6 != null) {
                p6.h(ZF.x.c);
            }
        }
        return;
    }

    private final String h(android.content.Context p2, String p3)
    {
        if (kotlin.jvm.internal.Intrinsics.areEqual(p3, "bio")) {
            String v2_2;
            if (com.facebook.applinks.b.w(p2).m(15) != 0) {
                v2_2 = 0;
            } else {
                v2_2 = 1;
            }
            if (v2_2 == null) {
                return "fail";
            }
        }
        return "success";
    }

    private final boolean i(android.content.Context p2, String p3)
    {
        if (!kotlin.jvm.internal.Intrinsics.areEqual(p3, "bio")) {
            if (!kotlin.jvm.internal.Intrinsics.areEqual(p3, "smart_otp")) {
                return 0;
            } else {
                return XS.b.d(this.a.F());
            }
        } else {
            return HS.d.a.a(p2, this.a.getUserId());
        }
    }

    private final void j(org.json.JSONObject p7, MR.b p8)
    {
        if ((!p7.has("types")) && (p8 != null)) {
            p8.h(ZF.x.c);
        }
        String v0_1 = this.b;
        if (v0_1 == null) {
            v0_1 = 0;
        }
        String v0_4;
        String v0_3 = ((androidx.fragment.app.Fragment) v0_1.get());
        if (v0_3 == null) {
            v0_4 = 0;
        } else {
            v0_4 = v0_3.getContext();
        }
        if (v0_4 != null) {
            this.f();
            String v7_1 = vn.com.vng.zalopay.miniapp.api.v2.common.UserLocalConfigModule.o(this, p7, 0, 2, 0);
            java.util.ArrayList v1_1 = new java.util.ArrayList();
            if (v7_1 != null) {
                String v7_4 = ((Iterable) v7_1.a()).iterator();
                while (v7_4.hasNext()) {
                    String v2_3 = ((String) v7_4.next());
                    v1_1.add(new vn.com.vng.zalopay.miniapp.api.v2.common.UserLocalConfigModule$a(this.h(v0_4, v2_3), v2_3, this.i(v0_4, v2_3)));
                }
            }
            if (p8 != null) {
                p8.b(this.q(v1_1));
            }
        } else {
            if (p8 != null) {
                p8.k("", "Native context error!");
                return;
            }
        }
        return;
    }

    public static synthetic vn.com.vng.zalopay.miniapp.api.v2.common.UserLocalConfigModule$b m(vn.com.vng.zalopay.miniapp.api.v2.common.UserLocalConfigModule p0, org.json.JSONObject p1, com.google.gson.Gson p2, int p3, Object p4)
    {
        if ((p3 & 2) != 0) {
            p2 = new com.google.gson.Gson();
        }
        return p0.l(p1, p2);
    }

    public static synthetic vn.com.vng.zalopay.miniapp.api.v2.common.UserLocalConfigModule$c o(vn.com.vng.zalopay.miniapp.api.v2.common.UserLocalConfigModule p0, org.json.JSONObject p1, com.google.gson.Gson p2, int p3, Object p4)
    {
        if ((p3 & 2) != 0) {
            p2 = new com.google.gson.Gson();
        }
        return p0.n(p1, p2);
    }

    private final void p(org.json.JSONObject p1, MR.b p2)
    {
        if (p1 == null) {
            if (p2 != null) {
                p2.h(ZF.m.c);
            }
            return;
        } else {
            this.j(p1, p2);
            return;
        }
    }

    private final String q(java.util.List p6)
    {
        java.util.Base64$Encoder v0_1 = new org.json.JSONArray();
        String v6_5 = ((Iterable) p6).iterator();
        while (v6_5.hasNext()) {
            java.nio.charset.Charset v1_3 = ((vn.com.vng.zalopay.miniapp.api.v2.common.UserLocalConfigModule$a) v6_5.next());
            org.json.JSONObject v2_1 = new org.json.JSONObject();
            v2_1.put("status", v1_3.a());
            v2_1.put("type", v1_3.b());
            v2_1.put("is_enable", v1_3.c());
            v0_1.put(v2_1);
        }
        String v6_7 = new org.json.JSONObject();
        v6_7.put("results", v0_1);
        return java.util.Base64.getUrlEncoder().encodeToString(v6_7.toString().getBytes(kotlin.text.Charsets.UTF_8));
    }

    private final void r(org.json.JSONObject p1, MR.b p2)
    {
        if (p1 == null) {
            if (p2 != null) {
                p2.h(ZF.m.c);
            }
            return;
        } else {
            this.g(p1, p2);
            return;
        }
    }

    private final void s(android.content.Context p8, MR.b p9)
    {
        String v0_1;
        if (!(p8 instanceof android.app.Activity)) {
            v0_1 = 0;
        } else {
            v0_1 = ((android.app.Activity) p8);
        }
        if (v0_1 != null) {
            n9.O.c(v0_1, new pS.f(HS.c.b, "setup_bio", 0, kotlin.collections.Z.mapOf(kotlin.TuplesKt.to("show_bio_checkbox", Boolean.FALSE)), 20), new h3.J(this, p8, p9, 8));
            return;
        } else {
            if (p9 != null) {
                p9.k("", "Activity context required!");
            }
            return;
        }
    }

    private final void t(android.content.Context p3, String p4, MR.b p5, boolean p6)
    {
        n9.O.h(p3, p4, this.a.getUserId(), new ES.l(p6, this, p5, p3));
        return;
    }

    public static synthetic void u(vn.com.vng.zalopay.miniapp.api.v2.common.UserLocalConfigModule p0, android.content.Context p1, String p2, MR.b p3, boolean p4, int p5, Object p6)
    {
        if ((p5 & 8) != 0) {
            p4 = 0;
        }
        p0.t(p1, p2, p3, p4);
        return;
    }

    public void a(String p2, org.json.JSONObject p3, MR.b p4)
    {
        if (!kotlin.jvm.internal.Intrinsics.areEqual(p2, "precheckUserLocalConfig")) {
            if (kotlin.jvm.internal.Intrinsics.areEqual(p2, "setupUserLocalConfig")) {
                this.r(p3, p4);
            }
            return;
        } else {
            this.p(p3, p4);
            return;
        }
    }

    public final void e(ref.WeakReference p1)
    {
        this.b = p1;
        return;
    }

    public void k()
    {
        return;
    }

    public final vn.com.vng.zalopay.miniapp.api.v2.common.UserLocalConfigModule$b l(org.json.JSONObject p2, com.google.gson.Gson p3)
    {
        try {
            return ((vn.com.vng.zalopay.miniapp.api.v2.common.UserLocalConfigModule$b) p3.fromJson(p2.toString(), vn.com.vng.zalopay.miniapp.api.v2.common.UserLocalConfigModule$b));
        } catch (Exception) {
            return 0;
        }
    }

    public final vn.com.vng.zalopay.miniapp.api.v2.common.UserLocalConfigModule$c n(org.json.JSONObject p2, com.google.gson.Gson p3)
    {
        try {
            java.util.List v2_4 = p2.optJSONArray("types");
        } catch (Exception) {
            return 0;
        }
        if (v2_4 == null) {
            return 0;
        } else {
            java.util.List v2_5 = v2_4.toString();
            if (v2_5 != null) {
                return new vn.com.vng.zalopay.miniapp.api.v2.common.UserLocalConfigModule$c(((java.util.List) p3.fromJson(v2_5, new vn.com.vng.zalopay.miniapp.api.v2.common.UserLocalConfigModule$parseWebParamUserLocalConfigRequest$listType$1().getType())));
            } else {
                return 0;
            }
        }
    }

    public void onCreate()
    {
        return;
    }
}
