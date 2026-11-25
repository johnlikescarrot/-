package vn.com.vng.zalopay.webapp;
public final class c implements qm.e {
    public final synthetic int a;
    public final java.util.List b;
    public final Object c;

    public c(XQ.k p5)
    {
        this.a = 1;
        this.c = p5;
        this.b = kotlin.collections.E.listOf(new String[] {"openDeepLinkV2", "openInternalBrowserV2", "openExternalBrowserV2", "openAppByPackageV2", "putExtraIntent"}));
        return;
    }

    public c(XQ.l p9)
    {
        this.a = 2;
        this.c = p9;
        String v3 = "setToolbarActionsV2";
        String v5 = "setActionBarV2";
        this.b = kotlin.collections.E.listOf(new String[] {"openShareDialogV2", "hideKeyboardV2"}));
        return;
    }

    public c(XQ.q p3)
    {
        this.a = 0;
        this.c = p3;
        this.b = kotlin.collections.E.listOf(new String[] {"openEKyc", "launchDeeplink", "openPinAuthentication"}));
        return;
    }

    public c(XQ.q p1, byte p2)
    {
        this.a = 3;
        this.c = p1;
        this.b = kotlin.collections.C.listOf("getUserInfoV2");
        return;
    }

    public c(Yu.c p2)
    {
        this.a = 4;
        this.c = p2;
        this.b = kotlin.collections.C.listOf("saveSmartOtpConfig");
        return;
    }

    public static Object c(String p1, String p2)
    {
        try {
            org.json.JSONObject v0_1 = new org.json.JSONObject(p1);
        } catch (org.json.JSONException) {
            return 0;
        }
        if (!v0_1.has(p2)) {
            return 0;
        } else {
            Object v1_3 = v0_1.get(p2);
            if (!(v1_3 instanceof String)) {
                if (!(v1_3 instanceof Integer)) {
                    if (!(v1_3 instanceof Boolean)) {
                        return 0;
                    }
                } else {
                }
            } else {
            }
            return v1_3;
        }
    }

    public static void d(String p3, String p4)
    {
        UR.a.e("01.8207.444", kotlin.collections.a0.mapOf(new kotlin.Pair[] {kotlin.TuplesKt.to("registered_from", "reset_pin"), kotlin.TuplesKt.to("otp_config", p3), kotlin.TuplesKt.to("status", "fail"), kotlin.TuplesKt.to("error_message", p4)})));
        return;
    }

    public final void a(String p18, org.json.JSONObject p19, qm.g p20)
    {
        switch (this.a) {
            case 0:
                String v3_6 = ((XQ.q) this.c).a;
                if (p18 != null) {
                    String v4_14 = p18.hashCode();
                    if (v4_14 == -1675121085) {
                        if (p18.equals("openPinAuthentication")) {
                            kotlin.jvm.internal.M.a.b(vn.com.vng.zalopay.webapp.c).t();
                            java.util.Objects.toString(p19);
                            int v0_38 = ((iR.o) v3_6.get());
                            if (v0_38 != 0) {
                                v0_38.p.j(new Xs.b(p20));
                            }
                        }
                    } else {
                        if (v4_14 == -505120902) {
                            if (p18.equals("openEKyc")) {
                                String v4_17;
                                int v0_41;
                                if (p19 == null) {
                                    v0_41 = "";
                                    v4_17 = 18;
                                } else {
                                    v0_41 = p19.getString("callbackurl");
                                    v4_17 = p19.getInt("flowid");
                                }
                                kotlin.jvm.internal.M.a.b(vn.com.vng.zalopay.webapp.c).t();
                                java.util.Objects.toString(p19);
                                String v2_20 = new YQ.b(v0_41, v4_17);
                                int v0_45 = ((iR.o) v3_6.get());
                                if (v0_45 != 0) {
                                    v0_45.n.j(new Xs.b(v2_20));
                                }
                            }
                        } else {
                            if ((v4_14 == 87784377) && (p18.equals("launchDeeplink"))) {
                                if (p19 != null) {
                                    String v4_21 = p19.optString("url");
                                    if (!kotlin.text.StringsKt.isBlank(v4_21)) {
                                        kotlin.jvm.internal.M.a.b(vn.com.vng.zalopay.webapp.c).t();
                                        p19.toString();
                                        int v0_53 = ((iR.o) v3_6.get());
                                        if (v0_53 != 0) {
                                            v0_53.l.j(new Xs.b(android.net.Uri.parse(v4_21)));
                                        }
                                        p20.f(0);
                                    } else {
                                        p20.a(2, "Url is blank");
                                    }
                                } else {
                                    p20.a(2, "request param cannot null or empty");
                                }
                            }
                        }
                    }
                }
                return;
            case 1:
                String v3_1 = ((dR.b) this.c);
                if (p18 != null) {
                    switch (p18.hashCode()) {
                        case -313937196:
                            if ((p18.equals("openAppByPackageV2")) && (p19 != null)) {
                                int v0_32 = p19.optString("packageName");
                                if (!android.text.TextUtils.isEmpty(v0_32)) {
                                    v3_1.f(v0_32);
                                    p20.success();
                                } else {
                                    p20.g(aR.a.n);
                                }
                            }
                            break;
                        case -238939377:
                            if ((p18.equals("openExternalBrowserV2")) && (p19 != null)) {
                                int v0_28 = p19.optString("url");
                                if (!android.text.TextUtils.isEmpty(v0_28)) {
                                    v3_1.g(v0_28);
                                    p20.success();
                                } else {
                                    p20.g(aR.a.m);
                                }
                            }
                            break;
                        case -220909507:
                            if ((p18.equals("putExtraIntent")) && (p19 != null)) {
                                int v0_26 = p19.optJSONObject("data");
                                int vtmp38 = p19.optInt("code");
                                if (v0_26 != 0) {
                                    v3_1.q(v0_26, Integer.valueOf(vtmp38));
                                }
                                p20.success();
                            }
                            break;
                        case 1452694028:
                            if ((p18.equals("openDeepLinkV2")) && (p19 != null)) {
                                int v0_22 = p19.optString("url");
                                if (!android.text.TextUtils.isEmpty(v0_22)) {
                                    if (!com.google.android.gms.internal.mlkit_vision_barcode.l0.c(v0_22)) {
                                        v3_1.d(v0_22);
                                        p20.success();
                                    } else {
                                        v3_1.c(v0_22);
                                        p20.success();
                                    }
                                } else {
                                    p20.g(aR.a.k);
                                }
                            }
                            break;
                        case 1689706589:
                            if ((p18.equals("openInternalBrowserV2")) && (p19 != null)) {
                                int v0_19 = p19.optString("url");
                                if (!android.text.TextUtils.isEmpty(v0_19)) {
                                    v3_1.e(v0_19);
                                    p20.success();
                                } else {
                                    p20.g(aR.a.l);
                                }
                            }
                            break;
                        default:
                    }
                }
                return;
            case 2:
                String v3_3 = ((dR.c) this.c);
                if (p18 != null) {
                    switch (p18.hashCode()) {
                        case -2105430107:
                            if (p18.equals("hideKeyboardV2")) {
                                v3_3.e();
                                p20.success();
                            }
                            break;
                        case -1582490441:
                            if ((p18.equals("shareFBV2")) && (p19 != null)) {
                                v3_3.c(p19.optString("url"), p19.optString("hashtag"), p19.optString("quote"), p20);
                            }
                            break;
                        case -1356639328:
                            if ((p18.equals("setToolbarActionsV2")) && (p19 != null)) {
                                String v4_2 = new java.util.ArrayList();
                                int v0_4 = p19.get("data");
                                if (((v0_4 instanceof org.json.JSONArray)) || ((v0_4 instanceof String))) {
                                    int v0_6;
                                    if (!(v0_4 instanceof org.json.JSONArray)) {
                                        v0_6 = new org.json.JSONArray(((String) v0_4));
                                    } else {
                                        v0_6 = ((org.json.JSONArray) v0_4);
                                    }
                                    String v2_6 = v0_6.length();
                                    String v5_0 = 0;
                                    while (v5_0 < v2_6) {
                                        v4_2.add(((vn.com.vng.zalopay.domain.webapp.model.WebInternalActionModel) nY.h.b(v0_6.getJSONObject(v5_0).toString(), new vn.com.vng.zalopay.webapp.v2.nativemodule.WebAppUINativeModuleV2$setToolbarActions$type$1().getType())));
                                        v5_0++;
                                    }
                                    if (!com.google.android.gms.internal.mlkit_vision_barcode.p0.b(v4_2)) {
                                        v3_3.b(v4_2, p20);
                                    } else {
                                        p20.g(aR.a.c);
                                    }
                                    p20.success();
                                } else {
                                }
                            }
                            break;
                        case -855602281:
                            if ((p18.equals("setActionBarV2")) && (p19 != null)) {
                                v3_3.h(p19.optString("backgroundColor"), p19.optBoolean("pullToRefresh"), p19.optString("titleColor"), p19.optBoolean("hidden"));
                                p20.success();
                            }
                            break;
                        case 136541348:
                            if (p18.equals("closeWindowV2")) {
                                v3_3.closeWindow();
                                p20.success();
                            }
                            break;
                        case 964423515:
                            if ((p18.equals("setStatusBarV2")) && (p19 != null)) {
                                v3_3.a(p19.optString("backgroundColor"), p19.optBoolean("hidden"));
                                p20.success();
                            }
                            break;
                        case 1195527353:
                            if ((p18.equals("openShareDialogV2")) && (p19 != null)) {
                                String v3_12 = p19.optInt("type");
                                String v5_11 = p19.optString("caption");
                                String v4_23 = p19.optString("content");
                                Yu.c v6_9 = p19.optString("link");
                                if (!kotlin.collections.E.listOf(new Integer[] {Integer.valueOf(1), Integer.valueOf(2)})).contains(Integer.valueOf(v3_12))) {
                                    p20.g(aR.a.c);
                                }
                                ((dR.c) this.c).f(v3_12, v4_23, v5_11, v6_9, p20);
                            }
                            break;
                        default:
                    }
                }
                return;
            case 3:
                if (kotlin.jvm.internal.Intrinsics.areEqual(p18, "getUserInfoV2")) {
                    try {
                        String v2_5 = p19.optLong("appId");
                    } catch (int v0_7) {
                        v0_7.getMessage();
                        v2_5 = 0;
                    }
                    ((dR.d) this.c).d(v2_5, p20);
                }
                return;
            default:
                if (kotlin.jvm.internal.Intrinsics.areEqual(p18, "saveSmartOtpConfig")) {
                    String v5_10 = "";
                    if (p19 != null) {
                        kotlin.Pair v8_4 = p19.optString("config");
                        if (!kotlin.text.StringsKt.isBlank(v8_4)) {
                            kotlin.Pair v9_2 = p19.optString("userId");
                            if (!kotlin.text.StringsKt.isBlank(v9_2)) {
                                kotlin.Pair v13_3;
                                kotlin.Pair v12_1 = new org.json.JSONObject(v8_4);
                                int v15 = 0;
                                if ((!v12_1.has("algorithm")) || (!(v12_1.get("algorithm") instanceof Integer))) {
                                    v13_3 = 0;
                                } else {
                                    v13_3 = 1;
                                }
                                if ((!v12_1.has("digits")) || (!(v12_1.get("digits") instanceof Integer))) {
                                    kotlin.Pair v11_3 = 0;
                                } else {
                                    v11_3 = 1;
                                }
                                if ((!v12_1.has("period")) || (!(v12_1.get("period") instanceof Integer))) {
                                    kotlin.Pair v10_4 = 0;
                                } else {
                                    v10_4 = 1;
                                }
                                if ((!v12_1.has("secret_key")) || (!(v12_1.get("secret_key") instanceof String))) {
                                    int v14_2 = 0;
                                } else {
                                    v14_2 = 1;
                                }
                                if ((v12_1.has("smart_otp_id")) && ((v12_1.get("smart_otp_id") instanceof String))) {
                                    v15 = 1;
                                }
                                if ((v13_3 == null) || ((v11_3 == null) || ((v10_4 == null) || ((v14_2 == 0) || (v15 == 0))))) {
                                    vn.com.vng.zalopay.webapp.c.d(p19.toString(), "The structure of a JSON string is not match or not correct with our predefined format.");
                                    p20.a(2, "The structure of a JSON string is not match or not correct with our predefined format.");
                                } else {
                                    String v2_28;
                                    ((Yu.c) this.c).a(m9.p4.a(v9_2), v8_4);
                                    String v2_27 = vn.com.vng.zalopay.webapp.c.c(v8_4, "secret_key");
                                    if (v2_27 == null) {
                                        v2_28 = "";
                                    } else {
                                        v2_28 = v2_27.toString();
                                        if (v2_28 == null) {
                                        }
                                    }
                                    int v0_64;
                                    int v0_63 = vn.com.vng.zalopay.webapp.c.c(v8_4, "algorithm");
                                    if (v0_63 == 0) {
                                        v0_64 = "";
                                    } else {
                                        v0_64 = v0_63.toString();
                                        if (v0_64 == 0) {
                                        }
                                    }
                                    String v3_15 = vn.com.vng.zalopay.webapp.c.c(v8_4, "smart_otp_id");
                                    if (v3_15 != null) {
                                        String v3_16 = v3_15.toString();
                                        if (v3_16 != null) {
                                            v5_10 = v3_16;
                                        }
                                    }
                                    kotlin.Pair v8_5 = kotlin.TuplesKt.to("hsk", q9.v7.a(v2_28));
                                    kotlin.TuplesKt.to("registered_from", "reset_pin");
                                    kotlin.TuplesKt.to("alg", v0_64);
                                    kotlin.TuplesKt.to("hsid", q9.v7.a(v5_10));
                                    kotlin.TuplesKt.to("status", "success");
                                    UR.a.e("01.8207.005", kotlin.collections.a0.mapOf(new kotlin.Pair[] {v8_5, kotlin.TuplesKt.to("source", "reset_pin")})));
                                    p20.f(0);
                                }
                            } else {
                                vn.com.vng.zalopay.webapp.c.d(p19.toString(), "userId is blank.");
                                p20.a(2, "userId is blank.");
                            }
                        } else {
                            vn.com.vng.zalopay.webapp.c.d(p19.toString(), "config is blank.");
                            p20.a(2, "config is blank.");
                        }
                    } else {
                        vn.com.vng.zalopay.webapp.c.d("", "request param cannot null or empty.");
                        p20.a(2, "request param cannot null or empty.");
                    }
                }
                return;
        }
    }

    public final String[] b()
    {
        switch (this.a) {
            case 0:
                String[] v1_5 = new String[0];
                return ((String[]) ((java.util.Collection) this.b).toArray(v1_5));
            case 1:
                String[] v1_3 = new String[0];
                return ((String[]) ((java.util.Collection) this.b).toArray(v1_3));
            case 2:
                String[] v1_1 = new String[0];
                return ((String[]) ((java.util.Collection) this.b).toArray(v1_1));
            case 3:
                String[] v1_9 = new String[0];
                return ((String[]) ((java.util.Collection) this.b).toArray(v1_9));
            default:
                String[] v1_7 = new String[0];
                return ((String[]) ((java.util.Collection) this.b).toArray(v1_7));
        }
    }
}
