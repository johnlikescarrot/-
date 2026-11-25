package vn.ai.trueid.sdk.navigation;
public final class AuthenticationID {
    public static final vn.ai.trueid.sdk.navigation.AuthenticationID INSTANCE;
    public static java.util.List a;
    public static vn.ai.trueid.sdk.navigation.AuthenListener b;
    public static org.json.JSONObject c;
    public static org.json.JSONObject d;
    public static int e;
    public static java.util.List f;
    public static java.util.List g;
    public static final Ek.b h;
    public static final android.os.Handler i;
    public static vn.ai.trueid.sdk.models.FaceAuthenConfig sdkConfig;

    static AuthenticationID()
    {
        vn.ai.trueid.sdk.navigation.AuthenticationID.INSTANCE = new vn.ai.trueid.sdk.navigation.AuthenticationID();
        vn.ai.trueid.sdk.navigation.AuthenticationID.f = new java.util.ArrayList();
        vn.ai.trueid.sdk.navigation.AuthenticationID.g = new java.util.ArrayList();
        vn.ai.trueid.sdk.navigation.AuthenticationID.h = new Ek.b(6);
        vn.ai.trueid.sdk.navigation.AuthenticationID.i = new android.os.Handler(android.os.Looper.getMainLooper());
        return;
    }

    public static final void access$closeSuccess(vn.ai.trueid.sdk.navigation.AuthenticationID p3)
    {
        p3.getClass();
        android.os.Handler v0 = vn.ai.trueid.sdk.navigation.AuthenticationID.i;
        v0.removeCallbacksAndMessages(vn.ai.trueid.sdk.navigation.AuthenticationID.h);
        v0.removeCallbacksAndMessages(0);
        Ek.b v3_5 = wk.a.h;
        if (v3_5 != null) {
            v3_5.invoke();
        }
        fk.c.c(0, 10, 0);
        v0.postDelayed(new Ek.b(5), 2000);
        return;
    }

    public static final void access$closeWithErrorCode(vn.ai.trueid.sdk.navigation.AuthenticationID p3, zk.a p4)
    {
        p3.getClass();
        android.os.Handler v0 = vn.ai.trueid.sdk.navigation.AuthenticationID.i;
        v0.removeCallbacksAndMessages(vn.ai.trueid.sdk.navigation.AuthenticationID.h);
        v0.removeCallbacksAndMessages(0);
        Kk.a v3_5 = wk.a.j;
        if (v3_5 != null) {
            v3_5.invoke();
        }
        long v1_0;
        Kk.a v3_6 = p4.a;
        if (v3_6 != null) {
            v1_0 = 0;
        } else {
            v1_0 = 1;
        }
        fk.c.c(v3_6, 5, v1_0);
        v0.postDelayed(new Kk.a(p4, 0), 500);
        return;
    }

    public static final void access$closeWithMaxRetry(vn.ai.trueid.sdk.navigation.AuthenticationID p3)
    {
        p3.getClass();
        android.os.Handler v0 = vn.ai.trueid.sdk.navigation.AuthenticationID.i;
        v0.removeCallbacksAndMessages(vn.ai.trueid.sdk.navigation.AuthenticationID.h);
        v0.removeCallbacksAndMessages(0);
        Ek.b v3_4 = wk.a.j;
        if (v3_4 != null) {
            v3_4.invoke();
        }
        v0.postDelayed(new Ek.b(7), 650);
        return;
    }

    public static final synthetic void access$convertConfig(vn.ai.trueid.sdk.navigation.AuthenticationID p0, android.content.Context p1)
    {
        p0.a();
        return;
    }

    public static final synthetic android.os.Handler access$getHandler$p()
    {
        return vn.ai.trueid.sdk.navigation.AuthenticationID.i;
    }

    public static final boolean access$isUserBlocked(vn.ai.trueid.sdk.navigation.AuthenticationID p0, org.json.JSONObject p1)
    {
        p0.getClass();
        int v0_5 = p1.optJSONObject("data");
        if (v0_5 != 0) {
            int v0_6 = v0_5.optJSONObject("transactionStatus");
            if ((v0_6 != 0) && ((v0_6.optBoolean("blocked")) || (v0_6.optBoolean("isBlocked")))) {
                return 1;
            }
        }
        return 0;
    }

    public static final void access$removeTimeOut(vn.ai.trueid.sdk.navigation.AuthenticationID p1)
    {
        p1.getClass();
        android.os.Handler v0 = vn.ai.trueid.sdk.navigation.AuthenticationID.i;
        v0.removeCallbacksAndMessages(vn.ai.trueid.sdk.navigation.AuthenticationID.h);
        v0.removeCallbacksAndMessages(0);
        return;
    }

    public static final void access$startFaceSDK(vn.ai.trueid.sdk.navigation.AuthenticationID p0, android.content.Context p1, vn.ai.trueid.sdk.models.FaceAuthenInput p2)
    {
        p0.getClass();
        wk.a.f(p1, new vn.ai.trueid.sdk.navigation.AuthenticationID$startFaceSDK$1(p2));
        return;
    }

    public static boolean b(vn.ai.trueid.sdk.models.FaceAuthenConfig p10)
    {
        if ((p10.getAppId().length() != 0) && (!kotlin.text.StringsKt.isBlank(p10.getAppId()))) {
            if ((p10.getDomain().length() != 0) && ((!kotlin.text.StringsKt.isBlank(p10.getDomain())) && (android.webkit.URLUtil.isValidUrl(p10.getDomain())))) {
                if (p10.getSdkKey().length() > 0) {
                    String v0_28 = p10.getSdkKey();
                    try {
                        if (v0_28.length() != 0) {
                            android.util.Base64.decode(kotlin.text.StringsKt.trim(v0_28).toString(), 2);
                            if ((p10.getSignVersion().length() != 0) && (!kotlin.jvm.internal.Intrinsics.areEqual(p10.getSignVersion(), "1"))) {
                                if (kotlin.jvm.internal.Intrinsics.areEqual(p10.getSignVersion(), "2")) {
                                    if (p10.getCreateRequestEndPoint().length() != 0) {
                                        String v0_39 = p10.getDomain();
                                        org.json.JSONObject v10_17 = p10.getCreateRequestEndPoint();
                                        int v3_12 = new StringBuilder();
                                        v3_12.append(v0_39);
                                        v3_12.append(v10_17);
                                        if (android.webkit.URLUtil.isValidUrl(v3_12.toString())) {
                                        }
                                    }
                                    vn.ai.trueid.sdk.utils.Helpers.Companion.errorLog(new Object[] {"validConfig : createRequestEndPoint "}));
                                    return 0;
                                }
                            } else {
                                int v3_14;
                                String v0_43 = p10.configHeaderJson();
                                if (!v0_43.has("Authorization")) {
                                    v3_14 = "";
                                } else {
                                    v3_14 = v0_43.getString("Authorization");
                                }
                                String v4_0 = v3_14;
                                if ((v4_0.length() > 0) && (kotlin.text.y.replace$default(v4_0, "Bearer ", "", 0, 4, 0).length() > 52)) {
                                    v4_0 = kotlin.text.y.replace$default(v4_0, "Bearer ", "", 0, 4, 0);
                                }
                                if (p10.getAccessTokenEndPoint().length() != 0) {
                                    int v3_4 = p10.getDomain();
                                    org.json.JSONObject v10_1 = p10.getAccessTokenEndPoint();
                                    java.nio.charset.Charset v5_3 = new StringBuilder();
                                    v5_3.append(v3_4);
                                    v5_3.append(v10_1);
                                    if (android.webkit.URLUtil.isValidUrl(v5_3.toString())) {
                                    }
                                }
                                if (v0_43.length() > 0) {
                                    try {
                                        if (v4_0.length() != 0) {
                                            org.json.JSONObject v10_8 = kotlin.text.StringsKt.y(v4_0, new String[] {"."}), 0, 6);
                                            if (v10_8.size() == 3) {
                                                java.nio.charset.Charset v5_4 = kotlin.text.Charsets.UTF_8;
                                                if (new org.json.JSONObject(new String(android.util.Base64.decode(((String) v10_8.get(0)), 11), v5_4)).has("alg")) {
                                                    new org.json.JSONObject(new String(android.util.Base64.decode(((String) v10_8.get(1)), 11), v5_4));
                                                    return 1;
                                                } else {
                                                }
                                            }
                                        }
                                    } catch (Exception) {
                                    }
                                    vn.ai.trueid.sdk.utils.Helpers.Companion.errorLog(new Object[] {"validConfig : accesstokenEndPoint or configHeader"}));
                                    return 0;
                                }
                            }
                        } else {
                            vn.ai.trueid.sdk.utils.Helpers.Companion.errorLog(new Object[] {"validConfig : sdkKey "}));
                            return 0;
                        }
                    } catch (IllegalArgumentException) {
                        vn.ai.trueid.sdk.utils.Helpers.Companion.errorLog(new Object[] {"validConfig : sdkKey "}));
                        return 0;
                    }
                }
                return 1;
            } else {
                vn.ai.trueid.sdk.utils.Helpers.Companion.errorLog(new Object[] {"validConfig : domain "}));
                return 0;
            }
        } else {
            vn.ai.trueid.sdk.utils.Helpers.Companion.errorLog(new Object[] {"validConfig : appId  "}));
            return 0;
        }
    }

    public static final void configure(android.app.Activity p1, vn.ai.trueid.sdk.models.FaceAuthenConfig p2)
    {
        try {
            vn.ai.trueid.sdk.utils.Helpers.Companion.init(p1);
            Throwable v1_1 = vn.ai.trueid.sdk.navigation.AuthenticationID.INSTANCE;
            v1_1.setSdkConfig$trueid_release(p2);
        } catch (UnsatisfiedLinkError) {
            return;
        } catch (Throwable v1_2) {
            throw v1_2;
        }
        if (!vn.ai.trueid.sdk.navigation.AuthenticationID.b(p2)) {
            return;
        } else {
            v1_1.a();
            return;
        }
    }

    public static final String info(android.content.Context p2)
    {
        p2.getApplicationInfo();
        String v2_4 = new org.json.JSONObject();
        v2_4.put("version", "3.3.18");
        v2_4.put("date", "126110614");
        v2_4.put("package", "vn.ai.trueid.sdk");
        return v2_4.toString();
    }

    public static final void setLanguage(android.content.Context p1, String p2)
    {
        if (wk.a.d(p2)) {
            vn.ai.trueid.sdk.navigation.AuthenticationID.d = new org.json.JSONObject(p2);
        }
        wk.a.e(p1, p2);
        return;
    }

    public static final void startFaceAuthen(android.content.Context p29, vn.ai.trueid.sdk.models.FaceAuthenInput p30, vn.ai.trueid.sdk.navigation.AuthenListener p31)
    {
        fk.b.p = kotlin.collections.E.emptyList();
        String v4_18 = vn.ai.trueid.sdk.navigation.TrueIDV3.checkSecurityDevice();
        if (v4_18 == 1) {
            if ((vn.ai.trueid.sdk.navigation.AuthenticationID.sdkConfig == null) || (!vn.ai.trueid.sdk.navigation.AuthenticationID.b(vn.ai.trueid.sdk.navigation.AuthenticationID.INSTANCE.getSdkConfig$trueid_release()))) {
                vn.ai.trueid.sdk.models.FaceAuthenInput v3_9 = 0;
            } else {
                v3_9 = 1;
            }
            if (v3_9 == null) {
                if (v3_9 != null) {
                    androidx.appcompat.app.o v1_2 = new androidx.appcompat.app.o(new androidx.appcompat.view.ContextThemeWrapper(p29, 2132018324));
                    vn.ai.trueid.sdk.models.FaceAuthenInput v3_17 = v1_2.a;
                    v3_17.f = v3_17.a.getText(2131955504);
                    v3_17.k = 1;
                    v1_2.a();
                    return;
                } else {
                    p31.onComplete(new vn.ai.trueid.sdk.models.FaceAuthenResult(300, "Face Authen config invalid: call setConfigFaceAuthen() with valid appId/domain before start", 0, 0, 0, 28, 0));
                    return;
                }
            } else {
                vn.ai.trueid.sdk.navigation.AuthenticationID$startFaceAuthen$1 v2_17 = vn.ai.trueid.sdk.navigation.AuthenticationID.INSTANCE;
                v2_17.getClass();
                if ((p30.getPersonID().length() == 0) || (kotlin.text.StringsKt.isBlank(p30.getPersonID()))) {
                    p31.onComplete(new vn.ai.trueid.sdk.models.FaceAuthenResult(300, "Face Authen input invalid: personID is empty", 0, 0, 0, 28, 0));
                    return;
                } else {
                    String v12_1;
                    vn.ai.trueid.sdk.navigation.AuthenticationID.e = 0;
                    vn.ai.trueid.sdk.navigation.AuthenticationID.a = 0;
                    vn.ai.trueid.sdk.navigation.AuthenticationID.b = 0;
                    vn.ai.trueid.sdk.navigation.AuthenticationID.c = 0;
                    vn.ai.trueid.sdk.navigation.AuthenticationID.f.clear();
                    vn.ai.trueid.sdk.navigation.AuthenticationID.g.clear();
                    vn.ai.trueid.sdk.navigation.AuthenticationID.e = 0;
                    vn.ai.trueid.sdk.navigation.AuthenticationID.i.postDelayed(vn.ai.trueid.sdk.navigation.AuthenticationID.h, ((((long) 90) * 1000) + 1000));
                    vn.ai.trueid.sdk.navigation.AuthenticationID.b = p31;
                    String v6_13 = vn.ai.trueid.sdk.utils.Helpers.Companion;
                    vn.ai.trueid.sdk.networks.g.c = kotlin.collections.a0.mutableMapOf(new kotlin.Pair[] {kotlin.TuplesKt.to("Content-Type", "application/json"), kotlin.TuplesKt.to("User-Agent", v6_13.provideUserAgent()), kotlin.TuplesKt.to("ClientID", v2_17.getSDKConfig$trueid_release().getAppId())}));
                    kotlin.Pair v7_0 = kotlin.TuplesKt.to("client", kotlin.collections.Z.mapOf(kotlin.TuplesKt.to("flowType", "AUTHEN")));
                    kotlin.TuplesKt.to("platform", "android");
                    vn.ai.trueid.sdk.navigation.AuthenticationID$startFaceAuthen$1 v2_5 = android.os.Build$VERSION.SDK_INT;
                    kotlin.TuplesKt.to("os_version", String.valueOf(v2_5));
                    kotlin.Pair v11_0 = vn.ai.trueid.sdk.navigation.TrueIDV3.INSTANCE;
                    if (v11_0.getDeviceInfo() == null) {
                        v12_1 = v6_13.getAdvId();
                    } else {
                        v12_1 = v11_0.getDeviceInfo().getId();
                    }
                    kotlin.Pair v18;
                    vn.ai.trueid.sdk.utils.Helpers$Companion v17;
                    vn.ai.trueid.sdk.models.FaceAuthenInput v3_5;
                    kotlin.TuplesKt.to("device_id", v12_1);
                    kotlin.Pair v13_2 = kotlin.TuplesKt.to("app_version", v6_13.getAppVersion());
                    if (v11_0.getDeviceInfo() == null) {
                        String v14_2 = android.os.Build.MODEL;
                        String v4_1 = android.os.Build.MANUFACTURER;
                        v17 = v6_13;
                        v18 = v7_0;
                        if (!kotlin.text.y.startsWith$default(v14_2.toLowerCase(), v4_1.toLowerCase(), 0, 2, 0)) {
                            v3_5 = androidx.activity.result.e.o(v4_1, " ", v14_2);
                        } else {
                            v3_5 = v14_2;
                        }
                    } else {
                        v3_5 = v11_0.getDeviceInfo().getModel();
                        v17 = v6_13;
                        v18 = v7_0;
                    }
                    String v6_2;
                    kotlin.TuplesKt.to("device_model", v3_5);
                    String v4_3 = v17.getContext();
                    if (v11_0.getDeviceInfo() == null) {
                        v6_2 = v17.getAdvId();
                    } else {
                        v6_2 = v11_0.getDeviceInfo().getId();
                    }
                    kotlin.Pair v20 = kotlin.TuplesKt.to("id", v6_2);
                    kotlin.Pair v8_3 = new StringBuilder();
                    v8_3.append(android.os.Build.MANUFACTURER);
                    v8_3.append(" ");
                    v8_3.append(android.os.Build.MODEL);
                    kotlin.TuplesKt.to("model", v8_3.toString());
                    String v6_6 = android.os.Build.DEVICE;
                    kotlin.Pair v7_5 = "";
                    if (v6_6 == null) {
                        v6_6 = "";
                    }
                    kotlin.TuplesKt.to("name", v6_6);
                    kotlin.TuplesKt.to("appId", v4_3.getPackageName());
                    kotlin.TuplesKt.to("installId", "none");
                    kotlin.TuplesKt.to("platform", "Android");
                    String v4_7 = android.os.Build.BRAND;
                    if (v4_7 != null) {
                        v7_5 = v4_7;
                    }
                    kotlin.TuplesKt.to("brandName", v7_5);
                    StringBuilder v5_4 = new StringBuilder();
                    v5_4.append(android.os.Build$VERSION.RELEASE);
                    v5_4.append(" (SDK ");
                    v5_4.append(v2_5);
                    v5_4.append(")");
                    kotlin.TuplesKt.to("os_version", v5_4.toString());
                    kotlin.Pair v11 = v13_2;
                    vn.ai.trueid.sdk.networks.g.b = kotlin.collections.a0.mutableMapOf(new kotlin.Pair[] {v18, kotlin.TuplesKt.to("device", kotlin.collections.a0.mapOf(new kotlin.Pair[] {v20, kotlin.TuplesKt.to("sdkVersion", "3.3.18")})))}));
                    vn.ai.trueid.sdk.networks.d.a(new vn.ai.trueid.sdk.navigation.AuthenticationID$startFaceAuthen$1(p29, p30, p31));
                    return;
                }
            }
        } else {
            p31.onComplete(new vn.ai.trueid.sdk.models.FaceAuthenResult(v4_18, vn.ai.trueid.sdk.navigation.TrueIDV3.INSTANCE.securityMessage(v4_18), 0, 0, 0, 28, 0));
            return;
        }
    }

    public final void a()
    {
        org.json.JSONObject v0_1 = vn.ai.trueid.sdk.navigation.TrueIDV3.INSTANCE.getConfigUI$trueid_release();
        wk.a.i = this.getSdkConfig$trueid_release().isShowLivenessSuccess();
        String v1_0 = v0_1.optString("ovalColor", "");
        String v4 = 0;
        if (v1_0.length() <= 0) {
            v1_0 = 0;
        }
        vn.ai.trueid.sdk.navigation.AuthenticationID v3_3;
        if (!v0_1.has("isAutoProcess")) {
            v3_3 = this.getSdkConfig$trueid_release().isAutoProcess();
        } else {
            v3_3 = v0_1.optBoolean("isAutoProcess", this.getSdkConfig$trueid_release().isAutoProcess());
        }
        boolean v15 = v3_3;
        this.getSdkConfig$trueid_release();
        vn.ai.trueid.sdk.navigation.AuthenticationID v3_4 = vn.ai.trueid.sdk.navigation.AuthenticationID.INSTANCE;
        String v6 = v3_4.getBackgroundColorHex(Sk.c.f());
        String v7 = v3_4.getBackgroundColorHex(Sk.c.g());
        yk.b v5_7 = v3_4.getSdkConfig$trueid_release().getOvalColor();
        if (v5_7.length() > 0) {
            v4 = v5_7;
        }
        String v8_1;
        if (v4 != null) {
            v8_1 = v4;
        } else {
            if (v1_0 == null) {
                v1_0 = v3_4.getBackgroundColorHex(Sk.c.f());
            }
            v8_1 = v1_0;
        }
        wk.a.a(new yk.b(v6, v7, v8_1, v3_4.getSdkConfig$trueid_release().getAnimationOvalColor(), v3_4.getBackgroundColorHex(Sk.c.e()), v3_4.getBackgroundColorHex(Sk.c.c()), v3_4.getBackgroundColorHex(Sk.c.i()), v3_4.getBackgroundColorHex(Sk.c.h()), v3_4.getBackgroundColorHex(Sk.c.a()), v15, v3_4.getSdkConfig$trueid_release().isShowCloseButton(), 0, v0_1.optString("fontName", ""), v3_4.getSdkConfig$trueid_release().getLivenessType(), 0, 0, 0, 0, 0, 8243200));
        return;
    }

    public final String getBackgroundColorHex(int p2)
    {
        return String.format("#%08X", java.util.Arrays.copyOf(new Object[] {Integer.valueOf(p2)}), 1));
    }

    public final vn.ai.trueid.sdk.navigation.AuthenListener getCallback()
    {
        return vn.ai.trueid.sdk.navigation.AuthenticationID.b;
    }

    public final int getCount()
    {
        return vn.ai.trueid.sdk.navigation.AuthenticationID.e;
    }

    public final java.util.List getCurImages()
    {
        return vn.ai.trueid.sdk.navigation.AuthenticationID.a;
    }

    public final org.json.JSONObject getCurResult()
    {
        return vn.ai.trueid.sdk.navigation.AuthenticationID.c;
    }

    public final org.json.JSONObject getLanguageJSON()
    {
        return vn.ai.trueid.sdk.navigation.AuthenticationID.d;
    }

    public final java.util.List getLsDurationIn()
    {
        return vn.ai.trueid.sdk.navigation.AuthenticationID.f;
    }

    public final java.util.List getLsDurationOut()
    {
        return vn.ai.trueid.sdk.navigation.AuthenticationID.g;
    }

    public final vn.ai.trueid.sdk.models.FaceAuthenConfig getSDKConfig$trueid_release()
    {
        vn.ai.trueid.sdk.models.FaceAuthenConfig v0 = this.getSdkConfig$trueid_release();
        if (v0 != null) {
            return v0;
        } else {
            return new vn.ai.trueid.sdk.models.FaceAuthenConfig(0, 0, 0, 0, 0, 0, 0, 0, 255, 0);
        }
    }

    public final vn.ai.trueid.sdk.models.FaceAuthenConfig getSdkConfig$trueid_release()
    {
        int v0_0 = vn.ai.trueid.sdk.navigation.AuthenticationID.sdkConfig;
        if (v0_0 == 0) {
            return 0;
        } else {
            return v0_0;
        }
    }

    public final String getTAG()
    {
        return "AuthenticationID";
    }

    public final void setCallback(vn.ai.trueid.sdk.navigation.AuthenListener p1)
    {
        vn.ai.trueid.sdk.navigation.AuthenticationID.b = p1;
        return;
    }

    public final void setCount(int p1)
    {
        vn.ai.trueid.sdk.navigation.AuthenticationID.e = p1;
        return;
    }

    public final void setCurImages(java.util.List p1)
    {
        vn.ai.trueid.sdk.navigation.AuthenticationID.a = p1;
        return;
    }

    public final void setCurResult(org.json.JSONObject p1)
    {
        vn.ai.trueid.sdk.navigation.AuthenticationID.c = p1;
        return;
    }

    public final void setLanguageJSON(org.json.JSONObject p1)
    {
        vn.ai.trueid.sdk.navigation.AuthenticationID.d = p1;
        return;
    }

    public final void setLsDurationIn(java.util.List p1)
    {
        vn.ai.trueid.sdk.navigation.AuthenticationID.f = p1;
        return;
    }

    public final void setLsDurationOut(java.util.List p1)
    {
        vn.ai.trueid.sdk.navigation.AuthenticationID.g = p1;
        return;
    }

    public final void setSdkConfig$trueid_release(vn.ai.trueid.sdk.models.FaceAuthenConfig p1)
    {
        vn.ai.trueid.sdk.navigation.AuthenticationID.sdkConfig = p1;
        return;
    }

    public final void showError(vn.ai.trueid.sdk.networks.F p9)
    {
        vn.ai.trueid.sdk.networks.F.b.get(p9.a);
        kotlin.jvm.internal.Lambda v2 = wk.a.k;
        if (v2 != null) {
            int v4;
            String v3 = p9.e();
            int v0_9 = vn.ai.trueid.sdk.navigation.AuthenticationID.a;
            String v1_1 = 0;
            if (v0_9 == 0) {
                v4 = 0;
            } else {
                v4 = ((String) kotlin.collections.CollectionsKt.lastOrNull(v0_9));
            }
            Integer v5 = Integer.valueOf(vn.ai.trueid.sdk.navigation.AuthenticationID.e);
            Integer v6 = Integer.valueOf(this.getSDKConfig$trueid_release().getMaxRetry());
            int v9_1 = p9.ordinal();
            if (v9_1 == 2) {
                v1_1 = "no_mask";
            } else {
                if (v9_1 == 3) {
                    v1_1 = "no_closed_eyes";
                } else {
                    if (v9_1 == 4) {
                        v1_1 = "hold_steady";
                    }
                }
            }
            v2.m(v3, v4, v5, v6, v1_1);
        }
        return;
    }

    public final void showErrorMessage()
    {
        kotlin.jvm.internal.Lambda v1_0;
        int v4_6 = Integer.valueOf(0);
        kotlin.jvm.internal.Lambda v1_8 = vn.ai.trueid.sdk.networks.F.b.get("faceMatch");
        String v3_2 = 0;
        if (!(v1_8 instanceof Boolean)) {
            v1_0 = 0;
        } else {
            v1_0 = ((Boolean) v1_8);
        }
        if (!kotlin.jvm.internal.Intrinsics.areEqual(v1_0, Boolean.FALSE)) {
            kotlin.jvm.internal.Lambda v1_7 = vn.ai.trueid.sdk.networks.F.d.iterator();
            while (v1_7.hasNext()) {
                String v2_6 = ((vn.ai.trueid.sdk.networks.F) v1_7.next());
                int v4_8 = vn.ai.trueid.sdk.networks.F.b.get(v2_6.a);
                if (v4_8 != 0) {
                    switch (v2_6.ordinal()) {
                        case 0:
                        case 5:
                        case 7:
                        case 8:
                            int v4_4;
                            if (!(v4_8 instanceof Boolean)) {
                                v4_4 = 0;
                            } else {
                                v4_4 = ((Boolean) v4_8);
                            }
                            if (kotlin.jvm.internal.Intrinsics.areEqual(v4_4, Boolean.FALSE)) {
                                this.showError(v2_6);
                                return;
                            }
                        case 1:
                            int v4_2;
                            if (!(v4_8 instanceof Integer)) {
                                v4_2 = 0;
                            } else {
                                v4_2 = ((Integer) v4_8);
                            }
                            int v4_3;
                            if (v4_2 == 0) {
                                v4_3 = 0;
                            } else {
                                v4_3 = v4_2.intValue();
                            }
                            if (v4_3 > 1) {
                                this.showError(v2_6);
                                return;
                            }
                        case 2:
                            int v4_0;
                            if (!(v4_8 instanceof Boolean)) {
                                v4_0 = 0;
                            } else {
                                v4_0 = ((Boolean) v4_8);
                            }
                            if (kotlin.jvm.internal.Intrinsics.areEqual(v4_0, Boolean.TRUE)) {
                                this.showError(v2_6);
                                return;
                            }
                        case 3:
                            int v4_13;
                            if (!(v4_8 instanceof Boolean)) {
                                v4_13 = 0;
                            } else {
                                v4_13 = ((Boolean) v4_8);
                            }
                            if (kotlin.jvm.internal.Intrinsics.areEqual(v4_13, Boolean.FALSE)) {
                                this.showError(v2_6);
                                return;
                            }
                        case 4:
                            int v4_11;
                            if (!(v4_8 instanceof Boolean)) {
                                v4_11 = 0;
                            } else {
                                v4_11 = ((Boolean) v4_8);
                            }
                            if (kotlin.jvm.internal.Intrinsics.areEqual(v4_11, Boolean.TRUE)) {
                                this.showError(v2_6);
                                return;
                            }
                        case 6:
                            int v4_9;
                            if (!(v4_8 instanceof Boolean)) {
                                v4_9 = 0;
                            } else {
                                v4_9 = ((Boolean) v4_8);
                            }
                            if (kotlin.jvm.internal.Intrinsics.areEqual(v4_9, Boolean.FALSE)) {
                                this.showError(v2_6);
                                return;
                            }
                        default:
                    }
                }
            }
            int v5_7 = wk.a.k;
            if (v5_7 != 0) {
                int v0_2 = vn.ai.trueid.sdk.navigation.AuthenticationID.a;
                if (v0_2 != 0) {
                    v3_2 = ((String) kotlin.collections.CollectionsKt.lastOrNull(v0_2));
                }
                v5_7.m("", v3_2, Integer.valueOf(vn.ai.trueid.sdk.navigation.AuthenticationID.e), Integer.valueOf(this.getSDKConfig$trueid_release().getMaxRetry()), 0);
            }
        } else {
            kotlin.jvm.internal.Lambda v1_2 = vn.ai.trueid.sdk.networks.F.c;
            if ((v1_2 == null) || (v1_2.intValue() != 0)) {
            } else {
                kotlin.jvm.internal.Lambda v1_4 = wk.a.k;
                if (v1_4 != null) {
                    v1_4.m(vn.ai.trueid.sdk.utils.Helpers.Companion.getString(2131955466), 0, v4_6, v4_6, 0);
                    return;
                }
            }
        }
        return;
    }
}
