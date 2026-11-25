package vn.ai.trueid.sdk.navigation;
public final class FaceLiveness {
    public static final vn.ai.trueid.sdk.navigation.FaceLiveness INSTANCE = None;
    public static java.util.List a = None;
    public static String b = "";
    public static vn.ai.trueid.sdk.navigation.FaceLivenessListener c = None;
    public static org.json.JSONObject d = None;
    public static org.json.JSONObject e = None;
    public static int f = 0;
    public static boolean g = False;
    public static boolean h = True;
    public static boolean i = True;
    public static vn.ai.trueid.sdk.navigation.TrueIDLivenessCaptureListener j;
    public static vn.ai.trueid.sdk.navigation.TrueIDLivenessCaptureInput k;
    public static boolean l;
    public static final Ek.b m;
    public static final android.os.Handler n;
    public static java.util.List o;
    public static java.util.List p;
    public static vn.ai.trueid.sdk.models.FaceLivenessConfig sdkConfig;

    static FaceLiveness()
    {
        wmoi.igob.jtgo(vn.ai.trueid.sdk.navigation.FaceLiveness, 17199, 17199);
        vn.ai.trueid.sdk.navigation.FaceLiveness.INSTANCE = new vn.ai.trueid.sdk.navigation.FaceLiveness();
        vn.ai.trueid.sdk.navigation.FaceLiveness.k = new vn.ai.trueid.sdk.navigation.TrueIDLivenessCaptureInput(0, 0, 3, 0);
        vn.ai.trueid.sdk.navigation.FaceLiveness.m = new Ek.b(12);
        vn.ai.trueid.sdk.navigation.FaceLiveness.n = new android.os.Handler(android.os.Looper.getMainLooper());
        vn.ai.trueid.sdk.navigation.FaceLiveness.o = new java.util.ArrayList();
        vn.ai.trueid.sdk.navigation.FaceLiveness.p = new java.util.ArrayList();
        return;
    }

    public static void a(vn.ai.trueid.sdk.models.FaceLivenessResult p2)
    {
        vn.ai.trueid.sdk.navigation.TrueIDLivenessCaptureListener v0 = vn.ai.trueid.sdk.navigation.FaceLiveness.j;
        vn.ai.trueid.sdk.navigation.FaceLiveness.g = 0;
        vn.ai.trueid.sdk.navigation.FaceLiveness.j = 0;
        if (v0 != null) {
            v0.onComplete(p2);
        }
        vn.ai.trueid.sdk.navigation.FaceLiveness.b = "";
        vn.ai.trueid.sdk.navigation.FaceLiveness.a = 0;
        return;
    }

    public static final void access$captureCloseError(vn.ai.trueid.sdk.navigation.FaceLiveness p1, int p2, String p3)
    {
        p1.getClass();
        vn.ai.trueid.sdk.navigation.FaceLiveness.c();
        Be.a v1_1 = wk.a.j;
        if (v1_1 != null) {
            v1_1.invoke();
        }
        vn.ai.trueid.sdk.navigation.FaceLiveness.n.postDelayed(new Be.a(p2, p3), 650);
        return;
    }

    public static final void access$captureCloseMaxRetry(vn.ai.trueid.sdk.navigation.FaceLiveness p3)
    {
        p3.getClass();
        vn.ai.trueid.sdk.navigation.FaceLiveness.c();
        Ek.b v3_1 = wk.a.j;
        if (v3_1 != null) {
            v3_1.invoke();
        }
        vn.ai.trueid.sdk.navigation.FaceLiveness.n.postDelayed(new Ek.b(8), 650);
        return;
    }

    public static final void access$captureCloseSuccess(vn.ai.trueid.sdk.navigation.FaceLiveness p3)
    {
        p3.getClass();
        vn.ai.trueid.sdk.navigation.FaceLiveness.c();
        Ek.b v3_1 = wk.a.h;
        if (v3_1 != null) {
            v3_1.invoke();
        }
        vn.ai.trueid.sdk.navigation.FaceLiveness.n.postDelayed(new Ek.b(10), 2000);
        return;
    }

    public static final void access$closeSuccess(vn.ai.trueid.sdk.navigation.FaceLiveness p3)
    {
        p3.getClass();
        vn.ai.trueid.sdk.navigation.FaceLiveness.c();
        Ek.b v3_1 = wk.a.h;
        if (v3_1 != null) {
            v3_1.invoke();
        }
        vn.ai.trueid.sdk.navigation.FaceLiveness.n.postDelayed(new Ek.b(11), 2000);
        return;
    }

    public static final void access$closeWithErrorCode(vn.ai.trueid.sdk.navigation.FaceLiveness p2, zk.a p3)
    {
        p2.getClass();
        vn.ai.trueid.sdk.navigation.FaceLiveness.c();
        vn.ai.trueid.sdk.navigation.FaceLiveness.n.postDelayed(new Kk.a(p3, 1), 500);
        return;
    }

    public static final void access$closeWithMaxRetry(vn.ai.trueid.sdk.navigation.FaceLiveness p3)
    {
        p3.getClass();
        vn.ai.trueid.sdk.navigation.FaceLiveness.c();
        Ek.b v3_1 = wk.a.j;
        if (v3_1 != null) {
            v3_1.invoke();
        }
        vn.ai.trueid.sdk.navigation.FaceLiveness.n.postDelayed(new Ek.b(9), 650);
        return;
    }

    public static final synthetic void access$convertConfig(vn.ai.trueid.sdk.navigation.FaceLiveness p0, android.content.Context p1)
    {
        p0.b();
        return;
    }

    public static final synthetic android.os.Handler access$getHandler$p()
    {
        return vn.ai.trueid.sdk.navigation.FaceLiveness.n;
    }

    public static final synthetic void access$removeTimeOut(vn.ai.trueid.sdk.navigation.FaceLiveness p0)
    {
        p0.getClass();
        vn.ai.trueid.sdk.navigation.FaceLiveness.c();
        return;
    }

    public static final void access$sendCaptureBody(vn.ai.trueid.sdk.navigation.FaceLiveness p12)
    {
        p12.getClass();
        vn.ai.trueid.sdk.navigation.TrueIDLivenessCaptureListener v12_1 = vn.ai.trueid.sdk.navigation.FaceLiveness.a;
        if (v12_1 != null) {
            String v1_1 = vn.ai.trueid.sdk.navigation.FaceLiveness.k.getTransID();
            String v2_0 = vn.ai.trueid.sdk.navigation.FaceLiveness.k.getPersonId();
            String v3_1 = vn.ai.trueid.sdk.navigation.FaceLiveness.INSTANCE.getSDKConfig$trueid_release();
            java.util.HashMap v4_1 = new java.util.HashMap();
            if (!vn.ai.trueid.sdk.navigation.FaceLiveness.h) {
                v4_1.put("faceImages", v12_1);
            } else {
                String v0_8;
                String v0_1 = vn.ai.trueid.sdk.networks.J.d().toString();
                v3_1.getAppId();
                vn.ai.trueid.sdk.navigation.TrueIDLivenessCaptureListener v12_2 = vn.ai.trueid.sdk.utils.j.j(v0_1, v12_1);
                if (!kotlin.jvm.internal.Intrinsics.areEqual(v3_1.getSignVersion(), "2")) {
                    String v0_6 = ((String) vn.ai.trueid.sdk.networks.M.c.get("Authorization"));
                    if (v0_6 == null) {
                        v0_6 = "";
                    }
                    v0_8 = kotlin.text.y.replace$default(v0_6, "Bearer ", "", 0, 4, 0);
                } else {
                    v0_8 = vn.ai.trueid.sdk.networks.J.d().toString();
                }
                String v0_10 = wk.a.c(v0_8, v3_1.getSignVersion());
                v4_1.put("faceImages", v12_2);
                v4_1.put("apiVersion", v3_1.getSignVersion());
                v4_1.put("nonce", v0_10);
                v4_1.put("signature", Pk.b.b(v3_1.getSignVersion(), v0_10, v12_2));
            }
            v4_1.putAll(vn.ai.trueid.sdk.networks.M.b);
            if (v1_1.length() > 0) {
                v4_1.put("transId", v1_1);
            }
            if (v2_0.length() > 0) {
                v4_1.put("personId", v2_0);
            }
            vn.ai.trueid.sdk.navigation.TrueIDLivenessCaptureStepCallback v5_4 = new vn.ai.trueid.sdk.navigation.TrueIDLivenessCaptureStepCallback(Kk.c.e, Kk.d.d, 0, 4, 0);
            vn.ai.trueid.sdk.navigation.TrueIDLivenessCaptureListener v12_9 = vn.ai.trueid.sdk.navigation.FaceLiveness.j;
            if (v12_9 != null) {
                v12_9.onCaptured(v4_1, v5_4);
            }
        }
        return;
    }

    public static final void access$startFaceSDK(vn.ai.trueid.sdk.navigation.FaceLiveness p0, android.content.Context p1)
    {
        p0.getClass();
        wk.a.f(p1, new vn.ai.trueid.sdk.navigation.FaceLiveness$startFaceSDK$1());
        return;
    }

    public static final void access$startFaceSDKCapture(vn.ai.trueid.sdk.navigation.FaceLiveness p0, android.content.Context p1)
    {
        p0.getClass();
        wk.a.f(p1, new vn.ai.trueid.sdk.navigation.FaceLiveness$startFaceSDKCapture$1());
        return;
    }

    public static void c()
    {
        android.os.Handler v1 = vn.ai.trueid.sdk.navigation.FaceLiveness.n;
        v1.removeCallbacksAndMessages(vn.ai.trueid.sdk.navigation.FaceLiveness.m);
        v1.removeCallbacksAndMessages(0);
        return;
    }

    public static final void configure(android.app.Activity p1, vn.ai.trueid.sdk.models.FaceLivenessConfig p2)
    {
        try {
            vn.ai.trueid.sdk.utils.Helpers.Companion.init(p1);
            Throwable v1_1 = vn.ai.trueid.sdk.navigation.FaceLiveness.INSTANCE;
            v1_1.setSdkConfig$trueid_release(p2);
        } catch (UnsatisfiedLinkError) {
            return;
        } catch (Throwable v1_2) {
            throw v1_2;
        }
        if (!vn.ai.trueid.sdk.navigation.FaceLiveness.e(p2)) {
            return;
        } else {
            v1_1.b();
            return;
        }
    }

    public static void d()
    {
        vn.ai.trueid.sdk.navigation.FaceLiveness.a = 0;
        vn.ai.trueid.sdk.navigation.FaceLiveness.b = "";
        vn.ai.trueid.sdk.navigation.FaceLiveness.c = 0;
        vn.ai.trueid.sdk.navigation.FaceLiveness.d = 0;
        vn.ai.trueid.sdk.navigation.FaceLiveness.p.clear();
        vn.ai.trueid.sdk.navigation.FaceLiveness.o.clear();
        vn.ai.trueid.sdk.navigation.FaceLiveness.f = 0;
        vn.ai.trueid.sdk.navigation.FaceLiveness.g = 0;
        vn.ai.trueid.sdk.navigation.FaceLiveness.j = 0;
        return;
    }

    public static boolean e(vn.ai.trueid.sdk.models.FaceLivenessConfig p9)
    {
        try {
            if ((p9.getAppId().length() != 0) && ((!kotlin.text.StringsKt.isBlank(p9.getAppId())) && ((p9.getDomain().length() != 0) && ((!kotlin.text.StringsKt.isBlank(p9.getDomain())) && (android.webkit.URLUtil.isValidUrl(p9.getDomain())))))) {
                if (p9.getSdkKey().length() > 0) {
                    String v0_28 = p9.getSdkKey();
                    if (v0_28.length() != 0) {
                        android.util.Base64.decode(kotlin.text.StringsKt.trim(v0_28).toString(), 2);
                        String v0_31 = p9.getSignVersion();
                        if ((v0_31.length() != 0) && (!kotlin.jvm.internal.Intrinsics.areEqual(v0_31, "1"))) {
                            if (kotlin.jvm.internal.Intrinsics.areEqual(v0_31, "2")) {
                                if ((p9.getCreateRequestEndPoint() == null) || (p9.getCreateRequestEndPoint().length() != 0)) {
                                    String v0_36 = p9.getDomain();
                                    org.json.JSONObject v9_11 = p9.getCreateRequestEndPoint();
                                    int v3_10 = new StringBuilder();
                                    v3_10.append(v0_36);
                                    v3_10.append(v9_11);
                                    if (!android.webkit.URLUtil.isValidUrl(v3_10.toString())) {
                                    }
                                } else {
                                }
                            }
                        } else {
                            if ((!p9.isConfigHeaderJson()) || (!p9.configHeaderJson().has("Authorization"))) {
                                String v0_41 = "";
                            } else {
                                v0_41 = p9.configHeaderJson().getString("Authorization");
                            }
                            int v3_0 = v0_41;
                            if ((v3_0.length() > 0) && (kotlin.text.y.replace$default(v3_0, "Bearer ", "", 0, 4, 0).length() > 52)) {
                                v3_0 = kotlin.text.y.replace$default(v3_0, "Bearer ", "", 0, 4, 0);
                            }
                            if ((p9.getAccessTokenEndPoint() == null) || (p9.getAccessTokenEndPoint().length() != 0)) {
                                String v0_7 = p9.getDomain();
                                String v4_2 = p9.getAccessTokenEndPoint();
                                java.nio.charset.Charset v5_2 = new StringBuilder();
                                v5_2.append(v0_7);
                                v5_2.append(v4_2);
                                if (android.webkit.URLUtil.isValidUrl(v5_2.toString())) {
                                    return 1;
                                }
                            }
                            if (p9.configHeaderJson().length() > 0) {
                                if (v3_0.length() != 0) {
                                    org.json.JSONObject v9_6 = kotlin.text.StringsKt.y(v3_0, new String[] {"."}), 0, 6);
                                    if (v9_6.size() == 3) {
                                        java.nio.charset.Charset v5_3 = kotlin.text.Charsets.UTF_8;
                                        if (new org.json.JSONObject(new String(android.util.Base64.decode(((String) v9_6.get(0)), 11), v5_3)).has("alg")) {
                                            new org.json.JSONObject(new String(android.util.Base64.decode(((String) v9_6.get(1)), 11), v5_3));
                                            return 1;
                                        } else {
                                        }
                                    } else {
                                    }
                                } else {
                                }
                            }
                        }
                    } else {
                    }
                }
                return 1;
            }
        } catch (Exception) {
        }
        return 0;
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
            vn.ai.trueid.sdk.navigation.FaceLiveness.e = new org.json.JSONObject(p2);
        }
        wk.a.e(p1, p2);
        return;
    }

    public static final void startFaceLiveness(android.content.Context p8, vn.ai.trueid.sdk.models.FaceLivenessInput p9, vn.ai.trueid.sdk.navigation.FaceLivenessListener p10)
    {
        int v9_6 = vn.ai.trueid.sdk.navigation.FaceLiveness.e(vn.ai.trueid.sdk.navigation.FaceLiveness.INSTANCE.getSdkConfig$trueid_release());
        if ((vn.ai.trueid.sdk.navigation.FaceLiveness.sdkConfig == null) || (v9_6 == 0)) {
            if (v9_6 != 0) {
                new android.app.AlertDialog$Builder(new android.view.ContextThemeWrapper(p8, 2132018324)).setMessage(2131955504).setCancelable(1).show();
                return;
            } else {
                p10.onComplete(new vn.ai.trueid.sdk.models.FaceLivenessResult(300, 0, 0, 0, 0, 30, 0));
                return;
            }
        } else {
            vn.ai.trueid.sdk.navigation.FaceLiveness.f = 0;
            vn.ai.trueid.sdk.navigation.FaceLiveness.d();
            vn.ai.trueid.sdk.navigation.FaceLiveness.n.postDelayed(vn.ai.trueid.sdk.navigation.FaceLiveness.m, ((((long) 180) * 1000) + 1000));
            vn.ai.trueid.sdk.navigation.FaceLiveness.c = p10;
            vn.ai.trueid.sdk.networks.J.e();
            vn.ai.trueid.sdk.networks.J.b(new vn.ai.trueid.sdk.navigation.FaceLiveness$startFaceLiveness$2(p8, p10));
            return;
        }
    }

    public static final void startFaceLivenessCapture(android.content.Context p29, vn.ai.trueid.sdk.navigation.TrueIDLivenessCaptureInput p30, vn.ai.trueid.sdk.navigation.TrueIDLivenessCaptureListener p31, boolean p32)
    {
        if (vn.ai.trueid.sdk.navigation.FaceLiveness.sdkConfig != null) {
            android.content.Context v1_2 = vn.ai.trueid.sdk.navigation.FaceLiveness.INSTANCE;
            if (!kotlin.text.StringsKt.isBlank(v1_2.getSdkConfig$trueid_release().getAppId())) {
                if (!vn.ai.trueid.sdk.navigation.TrueIDV3.INSTANCE.isSessionActive$trueid_release()) {
                    vn.ai.trueid.sdk.utils.TrueIDCaptureMetadata.INSTANCE.setFlow("LIVENESS");
                    vn.ai.trueid.sdk.navigation.FaceLiveness.f = 0;
                    vn.ai.trueid.sdk.navigation.FaceLiveness.d();
                    vn.ai.trueid.sdk.navigation.FaceLiveness.n.postDelayed(vn.ai.trueid.sdk.navigation.FaceLiveness.m, ((((long) 180) * 1000) + 1000));
                    vn.ai.trueid.sdk.navigation.FaceLiveness.g = 1;
                    vn.ai.trueid.sdk.navigation.FaceLiveness.h = vn.ai.trueid.sdk.navigation.FaceLiveness.i;
                    vn.ai.trueid.sdk.navigation.FaceLiveness.k = p30;
                    vn.ai.trueid.sdk.navigation.FaceLiveness.j = p31;
                    vn.ai.trueid.sdk.navigation.FaceLiveness.l = 0;
                    Kk.c v4_5 = wk.a.c;
                    if (v4_5 == null) {
                        v4_5 = new yk.b("#26B840", "#42526E", "#26B840", 0, "#FF6234", "#000000", "#253858", "#253858", "#FFFFFF", 0, 1, 1, "", 0, 0, 0, 0, 0, 0, 8126472);
                    }
                    if ((!p32) || (!v1_2.getSdkConfig$trueid_release().isRecordFace())) {
                        int v24_1 = 0;
                    } else {
                        v24_1 = 1;
                    }
                    wk.a.a(new yk.b(v4_5.a, v4_5.b, v4_5.c, v4_5.d, v4_5.e, v4_5.f, v4_5.g, v4_5.h, v4_5.i, v4_5.j, v4_5.k, v4_5.l, v4_5.m, v4_5.n, v4_5.o, v4_5.p, v4_5.q, v4_5.r, v24_1, v4_5.t, v4_5.u, v4_5.v, v4_5.w));
                    vn.ai.trueid.sdk.networks.J.e();
                    p31.onRequestCreate(new vn.ai.trueid.sdk.navigation.TrueIDLivenessCaptureStepCallback(new Kk.e(p29, 0), Kk.c.f, 0, 4, 0));
                    return;
                } else {
                    p31.onComplete(new vn.ai.trueid.sdk.models.FaceLivenessResult(306, "SDK is busy, please wait for the current flow to finish", 0, 0, 0, 28, 0));
                    return;
                }
            }
        }
        p31.onComplete(new vn.ai.trueid.sdk.models.FaceLivenessResult(300, 0, 0, 0, 0, 30, 0));
        return;
    }

    public static synthetic void startFaceLivenessCapture$default(android.content.Context p0, vn.ai.trueid.sdk.navigation.TrueIDLivenessCaptureInput p1, vn.ai.trueid.sdk.navigation.TrueIDLivenessCaptureListener p2, boolean p3, int p4, Object p5)
    {
        if ((p4 & 8) != 0) {
            p3 = 1;
        }
        vn.ai.trueid.sdk.navigation.FaceLiveness.startFaceLivenessCapture(p0, p1, p2, p3);
        return;
    }

    public final void b()
    {
        vn.ai.trueid.sdk.models.FaceLivenessConfig v0_4 = vn.ai.trueid.sdk.navigation.TrueIDV3.INSTANCE.getConfigUI$trueid_release();
        wk.a.i = this.getSdkConfig$trueid_release().isShowLivenessSuccess();
        String v1_0 = v0_4.optString("ovalColor", "");
        String v4 = 0;
        if (v1_0.length() <= 0) {
            v1_0 = 0;
        }
        vn.ai.trueid.sdk.navigation.AuthenticationID v3_3;
        if (!v0_4.has("isAutoProcess")) {
            v3_3 = this.getSdkConfig$trueid_release().isAutoProcess();
        } else {
            v3_3 = v0_4.optBoolean("isAutoProcess", this.getSdkConfig$trueid_release().isAutoProcess());
        }
        boolean v15 = v3_3;
        vn.ai.trueid.sdk.navigation.AuthenticationID v3_4 = vn.ai.trueid.sdk.navigation.AuthenticationID.INSTANCE;
        String v6 = v3_4.getBackgroundColorHex(Sk.c.f());
        String v7 = v3_4.getBackgroundColorHex(Sk.c.g());
        yk.b v5_8 = this.getSdkConfig$trueid_release().getOvalColor();
        if (v5_8.length() > 0) {
            v4 = v5_8;
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
        wk.a.a(new yk.b(v6, v7, v8_1, this.getSdkConfig$trueid_release().getAnimationOvalColor(), v3_4.getBackgroundColorHex(Sk.c.e()), v3_4.getBackgroundColorHex(Sk.c.c()), v3_4.getBackgroundColorHex(Sk.c.i()), v3_4.getBackgroundColorHex(Sk.c.h()), v3_4.getBackgroundColorHex(Sk.c.a()), v15, this.getSdkConfig$trueid_release().isShowCloseButton(), 0, v0_4.optString("fontName", ""), this.getSdkConfig$trueid_release().getLivenessType(), this.getSdkConfig$trueid_release().isRecordFace(), this.getSdkConfig$trueid_release().getRecordMaxDimension(), this.getSdkConfig$trueid_release().getRecordBitRate(), this.getSdkConfig$trueid_release().getRecordFrameRate(), this.getSdkConfig$trueid_release().getRecordStartMode(), 116736));
        return;
    }

    public final vn.ai.trueid.sdk.navigation.FaceLivenessListener getCallback()
    {
        return vn.ai.trueid.sdk.navigation.FaceLiveness.c;
    }

    public final vn.ai.trueid.sdk.navigation.TrueIDLivenessCaptureInput getCaptureInput$trueid_release()
    {
        return vn.ai.trueid.sdk.navigation.FaceLiveness.k;
    }

    public final vn.ai.trueid.sdk.navigation.TrueIDLivenessCaptureListener getCaptureListener$trueid_release()
    {
        return vn.ai.trueid.sdk.navigation.FaceLiveness.j;
    }

    public final boolean getCaptureMode$trueid_release()
    {
        return vn.ai.trueid.sdk.navigation.FaceLiveness.g;
    }

    public final boolean getCaptureNeedsNewRequest$trueid_release()
    {
        return vn.ai.trueid.sdk.navigation.FaceLiveness.l;
    }

    public final boolean getCaptureSealData$trueid_release()
    {
        return vn.ai.trueid.sdk.navigation.FaceLiveness.h;
    }

    public final boolean getCaptureSealDataConfig$trueid_release()
    {
        return vn.ai.trueid.sdk.navigation.FaceLiveness.i;
    }

    public final int getCount()
    {
        return vn.ai.trueid.sdk.navigation.FaceLiveness.f;
    }

    public final java.util.List getCurImages()
    {
        return vn.ai.trueid.sdk.navigation.FaceLiveness.a;
    }

    public final org.json.JSONObject getCurResult()
    {
        return vn.ai.trueid.sdk.navigation.FaceLiveness.d;
    }

    public final String getCurVideoBase64$trueid_release()
    {
        return vn.ai.trueid.sdk.navigation.FaceLiveness.b;
    }

    public final org.json.JSONObject getLanguageJSON()
    {
        return vn.ai.trueid.sdk.navigation.FaceLiveness.e;
    }

    public final java.util.List getLsDurationIn()
    {
        return vn.ai.trueid.sdk.navigation.FaceLiveness.o;
    }

    public final java.util.List getLsDurationOut()
    {
        return vn.ai.trueid.sdk.navigation.FaceLiveness.p;
    }

    public final vn.ai.trueid.sdk.models.FaceLivenessConfig getSDKConfig$trueid_release()
    {
        vn.ai.trueid.sdk.models.FaceLivenessConfig v0 = this.getSdkConfig$trueid_release();
        if (v0 != null) {
            return v0;
        } else {
            return new vn.ai.trueid.sdk.models.FaceLivenessConfig(0, 0, 0, 0, 0, 0, 0, 0, 0, 511, 0);
        }
    }

    public final vn.ai.trueid.sdk.models.FaceLivenessConfig getSdkConfig$trueid_release()
    {
        int v0_0 = vn.ai.trueid.sdk.navigation.FaceLiveness.sdkConfig;
        if (v0_0 == 0) {
            return 0;
        } else {
            return v0_0;
        }
    }

    public final void setCallback(vn.ai.trueid.sdk.navigation.FaceLivenessListener p1)
    {
        vn.ai.trueid.sdk.navigation.FaceLiveness.c = p1;
        return;
    }

    public final void setCaptureInput$trueid_release(vn.ai.trueid.sdk.navigation.TrueIDLivenessCaptureInput p1)
    {
        vn.ai.trueid.sdk.navigation.FaceLiveness.k = p1;
        return;
    }

    public final void setCaptureListener$trueid_release(vn.ai.trueid.sdk.navigation.TrueIDLivenessCaptureListener p1)
    {
        vn.ai.trueid.sdk.navigation.FaceLiveness.j = p1;
        return;
    }

    public final void setCaptureMode$trueid_release(boolean p1)
    {
        vn.ai.trueid.sdk.navigation.FaceLiveness.g = p1;
        return;
    }

    public final void setCaptureNeedsNewRequest$trueid_release(boolean p1)
    {
        vn.ai.trueid.sdk.navigation.FaceLiveness.l = p1;
        return;
    }

    public final void setCaptureSealData$trueid_release(boolean p1)
    {
        vn.ai.trueid.sdk.navigation.FaceLiveness.h = p1;
        return;
    }

    public final void setCaptureSealDataConfig$trueid_release(boolean p1)
    {
        vn.ai.trueid.sdk.navigation.FaceLiveness.i = p1;
        return;
    }

    public final void setCount(int p1)
    {
        vn.ai.trueid.sdk.navigation.FaceLiveness.f = p1;
        return;
    }

    public final void setCurImages(java.util.List p1)
    {
        vn.ai.trueid.sdk.navigation.FaceLiveness.a = p1;
        return;
    }

    public final void setCurResult(org.json.JSONObject p1)
    {
        vn.ai.trueid.sdk.navigation.FaceLiveness.d = p1;
        return;
    }

    public final void setCurVideoBase64$trueid_release(String p1)
    {
        vn.ai.trueid.sdk.navigation.FaceLiveness.b = p1;
        return;
    }

    public final void setLanguageJSON(org.json.JSONObject p1)
    {
        vn.ai.trueid.sdk.navigation.FaceLiveness.e = p1;
        return;
    }

    public final void setLsDurationIn(java.util.List p1)
    {
        vn.ai.trueid.sdk.navigation.FaceLiveness.o = p1;
        return;
    }

    public final void setLsDurationOut(java.util.List p1)
    {
        vn.ai.trueid.sdk.navigation.FaceLiveness.p = p1;
        return;
    }

    public final void setSdkConfig$trueid_release(vn.ai.trueid.sdk.models.FaceLivenessConfig p1)
    {
        vn.ai.trueid.sdk.navigation.FaceLiveness.sdkConfig = p1;
        return;
    }

    public final void setupCapture$trueid_release(android.app.Activity p2, vn.ai.trueid.sdk.models.FaceLivenessConfig p3)
    {
        vn.ai.trueid.sdk.utils.Helpers.Companion.init(p2);
        this.setSdkConfig$trueid_release(p3);
        this.b();
        return;
    }

    public final void showError(vn.ai.trueid.sdk.networks.F p9)
    {
        vn.ai.trueid.sdk.networks.F.b.get(p9.a);
        kotlin.jvm.internal.Lambda v2 = wk.a.k;
        if (v2 != null) {
            int v4;
            String v3 = p9.e();
            int v0_9 = vn.ai.trueid.sdk.navigation.FaceLiveness.a;
            String v1_1 = 0;
            if (v0_9 == 0) {
                v4 = 0;
            } else {
                v4 = ((String) kotlin.collections.CollectionsKt.lastOrNull(v0_9));
            }
            Integer v5 = Integer.valueOf(vn.ai.trueid.sdk.navigation.FaceLiveness.f);
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
        int v0_3 = vn.ai.trueid.sdk.networks.F.d.iterator();
        do {
            int v2_1 = 0;
            if (!v0_3.hasNext()) {
                int v3_5 = wk.a.k;
                if (v3_5 != 0) {
                    int v0_6 = vn.ai.trueid.sdk.navigation.FaceLiveness.a;
                    if (v0_6 != 0) {
                        v2_1 = ((String) kotlin.collections.CollectionsKt.lastOrNull(v0_6));
                    }
                    v3_5.m("", v2_1, Integer.valueOf(vn.ai.trueid.sdk.navigation.FaceLiveness.f), Integer.valueOf(this.getSDKConfig$trueid_release().getMaxRetry()), 0);
                }
                return;
            } else {
                vn.ai.trueid.sdk.networks.F v1_1 = ((vn.ai.trueid.sdk.networks.F) v0_3.next());
                int v3_1 = vn.ai.trueid.sdk.networks.F.b.get(v1_1.a);
            }
        } while(v3_1 != 0);
    }
}
