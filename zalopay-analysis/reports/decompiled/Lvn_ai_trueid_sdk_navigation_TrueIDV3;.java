package vn.ai.trueid.sdk.navigation;
public final class TrueIDV3 {
    public static final vn.ai.trueid.sdk.navigation.TrueIDV3 INSTANCE;
    public static vn.ai.trueid.sdk.models.DeviceInfo a;
    public static org.json.JSONObject b;
    public static org.json.JSONObject c;
    public static org.json.JSONObject d;
    public static vn.ai.trueid.sdk.models.EkycConfig e;
    public static vn.ai.trueid.sdk.models.FaceEnrollConfig f;
    public static vn.ai.trueid.sdk.models.NFCConfig g;
    public static vn.ai.trueid.sdk.models.sotp.TrueIdSmartOTPConfig h;
    public static vn.ai.trueid.sdk.models.sotp.TrueIdSmartOTPUIConfig i;
    public static vn.ai.trueid.sdk.navigation.TrueIdSmartOTPListener j;
    public static vn.ai.trueid.sdk.navigation.TrueIDListener k;
    public static boolean l;
    public static vn.ai.trueid.sdk.navigation.TrueIDEKYCCaptureListener m;
    public static boolean n;
    public static vn.ai.trueid.sdk.navigation.TrueIDFaceEnrollCaptureListener o;
    public static boolean p;
    public static vn.ai.trueid.sdk.navigation.NFCListener q;
    public static vn.ai.trueid.sdk.navigation.FaceEnrollListener r;
    public static vn.ai.trueid.sdk.navigation.TrueIDListener s;
    public static boolean t;
    public static boolean u;
    public static final ng.S v;

    static TrueIDV3()
    {
        vn.ai.trueid.sdk.navigation.TrueIDV3.INSTANCE = new vn.ai.trueid.sdk.navigation.TrueIDV3();
        try {
            System.loadLibrary("trueidlib");
        } catch (Exception) {
        }
        vn.ai.trueid.sdk.navigation.TrueIDV3.c = new org.json.JSONObject("{}");
        vn.ai.trueid.sdk.navigation.TrueIDV3.d = new org.json.JSONObject("{ \"stepVerification\":[\"FRONT\",\"BACK\",\"SELFIE\",\"SCANNFC\",\"SCANQR\"]}");
        vn.ai.trueid.sdk.navigation.TrueIDV3.i = new vn.ai.trueid.sdk.models.sotp.TrueIdSmartOTPUIConfig(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 67108863, 0);
        vn.ai.trueid.sdk.navigation.TrueIDV3.v = ng.x.b(Kk.r.e);
        return;
    }

    public static final void access$changeSmartOtpPinWithUI$presentNewPinStep(android.app.Activity p9, vn.ai.trueid.sdk.models.sotp.TrueIdSmartOTPResult p10, vn.ai.trueid.sdk.navigation.TrueIdSmartOTPListener p11, String p12, vn.ai.trueid.sdk.models.sotp.TrueIdSmartOTPPinPolicy p13, String p14)
    {
        vn.ai.trueid.sdk.navigation.TrueIDV3.INSTANCE.h(p9, p14, new Kk.q(p10, p11, p9, p14, p12, p13, 0));
        return;
    }

    public static final synthetic void access$presentPinKeypadDialog$lambda$26$buildKeypad(android.widget.LinearLayout p0, java.util.List p1, kotlin.jvm.internal.H p2, StringBuilder p3, vn.ai.trueid.sdk.models.sotp.TrueIdSmartOTPPinPolicy p4, android.widget.TextView p5, kotlin.jvm.internal.L p6, android.widget.LinearLayout p7, android.app.Activity p8, vn.ai.trueid.sdk.models.sotp.TrueIdSmartOTPUIConfig p9)
    {
        vn.ai.trueid.sdk.navigation.TrueIDV3.d(p0, p1, p2, p3, p4, p5, p6, p7, p8, p9);
        return;
    }

    public static final void access$presentPinKeypadDialog$lambda$26$onDelete(kotlin.jvm.internal.H p0, StringBuilder p1, kotlin.jvm.internal.L p2, android.widget.LinearLayout p3, vn.ai.trueid.sdk.models.sotp.TrueIdSmartOTPPinPolicy p4, android.app.Activity p5, vn.ai.trueid.sdk.models.sotp.TrueIdSmartOTPUIConfig p6)
    {
        if ((!p0.a) && (p1.length() > 0)) {
            p1.deleteCharAt((p1.length() - 1));
            vn.ai.trueid.sdk.navigation.TrueIDV3.g(p3, p4, p5, p1, p6);
            ((kotlin.jvm.functions.Function0) p2.a).invoke();
        }
        return;
    }

    public static final void access$presentPinKeypadDialog$lambda$26$onDigit(kotlin.jvm.internal.H p1, StringBuilder p2, vn.ai.trueid.sdk.models.sotp.TrueIdSmartOTPPinPolicy p3, android.widget.TextView p4, kotlin.jvm.internal.L p5, android.widget.LinearLayout p6, android.app.Activity p7, vn.ai.trueid.sdk.models.sotp.TrueIdSmartOTPUIConfig p8, int p9)
    {
        if ((!p1.a) && (p2.length() < p3.getEffectiveMax())) {
            p4.setVisibility(8);
            p2.append(p9);
            vn.ai.trueid.sdk.navigation.TrueIDV3.g(p6, p3, p7, p2, p8);
            ((kotlin.jvm.functions.Function0) p5.a).invoke();
        }
        return;
    }

    public static final void access$presentPinKeypadDialog$lambda$26$resetInput(StringBuilder p1, kotlin.jvm.internal.H p2, kotlin.jvm.internal.L p3, android.widget.LinearLayout p4, vn.ai.trueid.sdk.models.sotp.TrueIdSmartOTPPinPolicy p5, android.app.Activity p6, vn.ai.trueid.sdk.models.sotp.TrueIdSmartOTPUIConfig p7)
    {
        kotlin.text.v.clear(p1);
        p2.a = 0;
        vn.ai.trueid.sdk.navigation.TrueIDV3.g(p4, p5, p6, p1, p7);
        ((kotlin.jvm.functions.Function0) p3.a).invoke();
        return;
    }

    public static final void access$presentPinKeypadDialog$lambda$26$showError(android.widget.TextView p0, String p1)
    {
        p0.setText(p1);
        p0.setVisibility(0);
        return;
    }

    public static final void access$presentPinKeypadDialog$lambda$26$startRetryCountdown(kotlin.jvm.internal.H p1, android.widget.LinearLayout p2, kotlin.jvm.internal.L p3, StringBuilder p4, kotlin.jvm.internal.H p5, kotlin.jvm.internal.L p6, android.widget.LinearLayout p7, vn.ai.trueid.sdk.models.sotp.TrueIdSmartOTPPinPolicy p8, android.app.Activity p9, vn.ai.trueid.sdk.models.sotp.TrueIdSmartOTPUIConfig p10, android.widget.TextView p11, long p12, String p14)
    {
        kotlin.text.v.clear(p4);
        p5.a = 0;
        vn.ai.trueid.sdk.navigation.TrueIDV3.g(p7, p8, p9, p4, p10);
        ((kotlin.jvm.functions.Function0) p6.a).invoke();
        if (p12 > 0) {
            p1.a = 1;
            p2.setAlpha(1053609165);
            android.os.CountDownTimer v4_4 = ((android.os.CountDownTimer) p3.a);
            if (v4_4 != null) {
                v4_4.cancel();
            }
            p3.a = new vn.ai.trueid.sdk.navigation.TrueIDV3$presentPinKeypadDialog$1$startRetryCountdown$1(p12, p2, p11, p14, p1).start();
            return;
        } else {
            p11.setText(p14);
            p11.setVisibility(0);
            return;
        }
    }

    public static boolean b(org.json.JSONObject p4)
    {
        if (!p4.isNull("stepVerification")) {
            int v4_2 = p4.optJSONArray("stepVerification");
            if (v4_2 != 0) {
                Og.m v0_1 = Og.x.until(0, v4_2.length());
                if ((!(v0_1 instanceof java.util.Collection)) || (!((java.util.Collection) v0_1).isEmpty())) {
                    Og.m v0_2 = v0_1.x();
                    while (v0_2.c) {
                        if (kotlin.jvm.internal.Intrinsics.areEqual(v4_2.optString(v0_2.nextInt()), "SINGLENFC")) {
                            return 1;
                        }
                    }
                }
            }
        }
        return 0;
    }

    public static void c(vn.ai.trueid.sdk.navigation.TrueIDV3 p3, android.app.Activity p4, int p5, String p6, String p7, kotlin.jvm.functions.Function1 p8, Kk.p p9, String p10, int p11)
    {
        if ((p11 & 32) != 0) {
            p9 = 0;
        }
        if ((p11 & 64) != 0) {
            p10 = 0;
        }
        p3.getClass();
        p4.runOnUiThread(new Kk.g(p4, p6, p7, p8, p5, p9, p10));
        return;
    }

    public static final void changeSmartOtpPinWithUI(android.app.Activity p14, vn.ai.trueid.sdk.navigation.TrueIdSmartOTPListener p15)
    {
        String v1_1 = new vn.ai.trueid.sdk.models.sotp.TrueIdSmartOTPResult(0, 0, 0, 7, 0);
        vn.ai.trueid.sdk.navigation.TrueIDV3 v2_2 = vn.ai.trueid.sdk.utils.SmartOTPHelper.a(p14);
        if (v2_2 != null) {
            String v3_3 = vn.ai.trueid.sdk.utils.SmartOTPHelper.s(p14, v2_2);
            Long v7_14;
            Kk.p v0_4 = ((Iterable) p14.getSharedPreferences("sotp_pin_seed_store", 0).getAll().keySet());
            int v8_2 = new java.util.ArrayList();
            Kk.p v0_5 = v0_4.iterator();
            do {
                int v10_1 = 0;
                if (!v0_5.hasNext()) {
                    Kk.p v0_6 = v8_2.iterator();
                    if (v0_6.hasNext()) {
                        Long v7_8 = Long.valueOf(vn.ai.trueid.sdk.utils.SmartOTPHelper.s(p14, kotlin.text.StringsKt.r(((String) v0_6.next()), "sotp_lock_")));
                        while(true) {
                            v10_1 = v7_8;
                            if (!v0_6.hasNext()) {
                                break;
                            }
                            v7_8 = Long.valueOf(vn.ai.trueid.sdk.utils.SmartOTPHelper.s(p14, kotlin.text.StringsKt.r(((String) v0_6.next()), "sotp_lock_")));
                            if (v10_1.compareTo(v7_8) < 0) {
                            } else {
                                while (v0_6.hasNext()) {
                                }
                            }
                        }
                    } else {
                    }
                    if (v10_1 == 0) {
                        v7_14 = 0;
                        String v3_5 = Math.max(v3_3, v7_14);
                        if (v3_5 <= 0) {
                            String v6_2 = vn.ai.trueid.sdk.navigation.TrueIDV3.INSTANCE;
                            int v4_2 = v1_1;
                            vn.ai.trueid.sdk.navigation.TrueIDV3.c(v6_2, p14, 1, "Nh\u1eadp m\u00e3 PIN hi\u1ec7n t\u1ea1i", "Nh\u1eadp m\u00e3 PIN hi\u1ec7n t\u1ea1i \u0111\u1ec3 ti\u1ebfp t\u1ee5c.", new Bk.m(v4_2, p15, 10), new Kk.p(p14, v2_2, v6_2.getSmartOtpConfig$trueid_release().getPinPolicy(), v4_2, p15), 0, 64);
                            return;
                        } else {
                            v1_1.setCode(423);
                            v1_1.setErrorMessage(vn.ai.trueid.sdk.networks.g0.e(Integer.valueOf(((int) ((v3_5 + 999) / 1000)))));
                            p15.onComplete(v1_1);
                            return;
                        }
                    } else {
                        v7_14 = v10_1.longValue();
                    }
                } else {
                    int v9_2 = v0_5.next();
                }
            } while(!kotlin.text.y.startsWith$default(((String) v9_2), "sotp_lock_", 0, 2, 0));
            v8_2.add(v9_2);
        } else {
            v1_1.setCode(404);
            v1_1.setErrorMessage("PIN not set for this credential");
            p15.onComplete(v1_1);
            return;
        }
    }

    public static final void checkSOTPCredentialStatus(android.app.Activity p6, String p7, String p8, String p9, vn.ai.trueid.sdk.navigation.TrueIdSmartOTPListener p10)
    {
        if (p9.length() > 0) {
            vn.ai.trueid.sdk.navigation.TrueIDV3$checkSOTPCredentialStatus$1 v6_3 = vn.ai.trueid.sdk.navigation.TrueIDV3.h;
            if (v6_3 != null) {
                v6_3.setUserId(p9);
            }
        }
        vn.ai.trueid.sdk.networks.g0.a(new h3.I(p7, p8, new vn.ai.trueid.sdk.navigation.TrueIDV3$checkSOTPCredentialStatus$1(new vn.ai.trueid.sdk.models.sotp.TrueIdSmartOTPResult(0, 0, 0, 7, 0), p10), 15));
        return;
    }

    public static synthetic void checkSOTPCredentialStatus$default(android.app.Activity p0, String p1, String p2, String p3, vn.ai.trueid.sdk.navigation.TrueIdSmartOTPListener p4, int p5, Object p6)
    {
        if ((p5 & 8) != 0) {
            p3 = "";
        }
        vn.ai.trueid.sdk.navigation.TrueIDV3.checkSOTPCredentialStatus(p0, p1, p2, p3, p4);
        return;
    }

    public static final int checkSecurityDevice()
    {
        int v0_0 = vn.ai.trueid.sdk.navigation.TrueIDV3.checkSecurityDeviceNative();
        if (v0_0 == 1) {
            return vn.ai.trueid.sdk.utils.Helpers.Companion.checkSecurityDevice();
        } else {
            return v0_0;
        }
    }

    public static final native int checkSecurityDeviceNative();

    public static final boolean checkSeed()
    {
        return vn.ai.trueid.sdk.utils.SmartOTPHelper.k();
    }

    public static final int checkVaultStatus()
    {
        String v0_3;
        String v0_8 = new vn.ai.trueid.sdk.TrueShield.SecureVault(vn.ai.trueid.sdk.utils.Helpers.Companion.getContext()).getVaultData();
        if (v0_8 != null) {
            String v0_1 = vn.ai.trueid.sdk.utils.facedetector.DataSignService.a("trueid-vault-selftest", 0, v0_8);
            if ((v0_1 == null) || (v0_1.length != 32)) {
                v0_3 = "ERR_SECURITY_VAULT_INVALID";
            } else {
                v0_3 = "";
            }
        } else {
            v0_3 = "ERR_SECURITY_VAULT_MISSING";
        }
        String v0_4 = vn.ai.trueid.sdk.models.TrueIDVaultStatus.INSTANCE.codeForSentinel(v0_3);
        if (v0_4 == null) {
            return 1;
        } else {
            return v0_4.intValue();
        }
    }

    public static final void d(android.widget.LinearLayout p14, java.util.List p15, kotlin.jvm.internal.H p16, StringBuilder p17, vn.ai.trueid.sdk.models.sotp.TrueIdSmartOTPPinPolicy p18, android.widget.TextView p19, kotlin.jvm.internal.L p20, android.widget.LinearLayout p21, android.app.Activity p22, vn.ai.trueid.sdk.models.sotp.TrueIdSmartOTPUIConfig p23)
    {
        p14.removeAllViews();
        p14.addView(vn.ai.trueid.sdk.navigation.TrueIDV3.f(p22, p23, kotlin.collections.E.listOf(new kotlin.Pair[] {kotlin.TuplesKt.to(String.valueOf(((Number) p15.get(0)).intValue()), new Kk.w(p15, p16, p17, p18, p19, p20, p21, p22, p23, 1)), kotlin.TuplesKt.to(String.valueOf(((Number) p15.get(1)).intValue()), new Kk.w(p15, p16, p17, p18, p19, p20, p21, p22, p23, 2)), kotlin.TuplesKt.to(String.valueOf(((Number) p15.get(2)).intValue()), new Kk.w(p15, p16, p17, p18, p19, p20, p21, p22, p23, 3))}))));
        p14.addView(vn.ai.trueid.sdk.navigation.TrueIDV3.f(p22, p23, kotlin.collections.E.listOf(new kotlin.Pair[] {kotlin.TuplesKt.to(String.valueOf(((Number) p15.get(3)).intValue()), new Kk.w(p15, p16, p17, p18, p19, p20, p21, p22, p23, 4)), kotlin.TuplesKt.to(String.valueOf(((Number) p15.get(4)).intValue()), new Kk.w(p15, p16, p17, p18, p19, p20, p21, p22, p23, 5)), kotlin.TuplesKt.to(String.valueOf(((Number) p15.get(5)).intValue()), new Kk.w(p15, p16, p17, p18, p19, p20, p21, p22, p23, 6))}))));
        p14.addView(vn.ai.trueid.sdk.navigation.TrueIDV3.f(p22, p23, kotlin.collections.E.listOf(new kotlin.Pair[] {kotlin.TuplesKt.to(String.valueOf(((Number) p15.get(6)).intValue()), new Kk.w(p15, p16, p17, p18, p19, p20, p21, p22, p23, 7)), kotlin.TuplesKt.to(String.valueOf(((Number) p15.get(7)).intValue()), new Kk.w(p15, p16, p17, p18, p19, p20, p21, p22, p23, 8)), kotlin.TuplesKt.to(String.valueOf(((Number) p15.get(8)).intValue()), new Kk.w(p15, p16, p17, p18, p19, p20, p21, p22, p23, 9))}))));
        p14.addView(vn.ai.trueid.sdk.navigation.TrueIDV3.f(p22, p23, kotlin.collections.E.listOf(new kotlin.Pair[] {kotlin.TuplesKt.to("", 0), kotlin.TuplesKt.to(String.valueOf(((Number) p15.get(9)).intValue()), new Kk.w(p15, p16, p17, p18, p19, p20, p21, p22, p23, 0)), kotlin.TuplesKt.to("\u232b", new Kk.x(p16, p17, p20, p21, p18, p22, p23))}))));
        return;
    }

    public static final int e(android.app.Activity p0, int p1)
    {
        return ((int) (((float) p1) * p0.getResources().getDisplayMetrics().density));
    }

    public static final native int embedSecurityNative(byte[] p0, int p1, int p2, String p3, String p4, byte[] p5);

    public static final android.widget.LinearLayout f(android.app.Activity p9, vn.ai.trueid.sdk.models.sotp.TrueIdSmartOTPUIConfig p10, java.util.List p11)
    {
        android.widget.LinearLayout v0_1 = new android.widget.LinearLayout(p9);
        v0_1.setOrientation(0);
        java.util.Iterator v11_1 = p11.iterator();
        while (v11_1.hasNext()) {
            android.widget.Button v2_5;
            android.widget.Button v2_1 = ((kotlin.Pair) v11_1.next());
            int v3_1 = ((String) v2_1.component1());
            android.widget.Button v2_3 = ((kotlin.jvm.functions.Function0) v2_1.component2());
            android.widget.LinearLayout$LayoutParams v4_1 = new android.widget.LinearLayout$LayoutParams(0, vn.ai.trueid.sdk.navigation.TrueIDV3.e(p9, 56), 1065353216);
            v4_1.setMargins(vn.ai.trueid.sdk.navigation.TrueIDV3.e(p9, 6), vn.ai.trueid.sdk.navigation.TrueIDV3.e(p9, 6), vn.ai.trueid.sdk.navigation.TrueIDV3.e(p9, 6), vn.ai.trueid.sdk.navigation.TrueIDV3.e(p9, 6));
            if (v3_1.length() != 0) {
                android.graphics.drawable.GradientDrawable v7_2;
                android.widget.Button v5_6 = new android.widget.Button(p9);
                v5_6.setText(v3_1);
                v5_6.setAllCaps(0);
                v5_6.setTextSize(1101004800);
                if (!kotlin.jvm.internal.Intrinsics.areEqual(v3_1, "\u232b")) {
                    v7_2 = p10.getKeypadButtonTextColor();
                } else {
                    v7_2 = p10.getKeypadDeleteIconColor();
                }
                try {
                    android.graphics.drawable.GradientDrawable v7_3 = android.graphics.Color.parseColor(v7_2);
                } catch (Exception) {
                    v7_3 = -1;
                }
                float v8_2;
                v5_6.setTextColor(v7_3);
                v5_6.setStateListAnimator(0);
                android.graphics.drawable.GradientDrawable v7_6 = new android.graphics.drawable.GradientDrawable();
                if (p10.getCornerRadius() <= 0) {
                    v8_2 = 12;
                } else {
                    v8_2 = p10.getCornerRadius();
                }
                int v3_4;
                v7_6.setCornerRadius(((float) vn.ai.trueid.sdk.navigation.TrueIDV3.e(p9, v8_2)));
                if (!kotlin.jvm.internal.Intrinsics.areEqual(v3_1, "\u232b")) {
                    try {
                        v3_4 = android.graphics.Color.parseColor(p10.getKeypadButtonColor());
                    } catch (Exception) {
                        v3_4 = -12303292;
                    }
                } else {
                    v3_4 = 0;
                }
                v7_6.setColor(v3_4);
                v5_6.setBackground(v7_6);
                v5_6.setOnClickListener(new Kk.h(0, v2_3));
                v2_5 = v5_6;
            } else {
                v2_5 = new android.view.View(p9);
            }
            v0_1.addView(v2_5, v4_1);
        }
        return v0_1;
    }

    public static final void g(android.widget.LinearLayout p6, vn.ai.trueid.sdk.models.sotp.TrueIdSmartOTPPinPolicy p7, android.app.Activity p8, StringBuilder p9, vn.ai.trueid.sdk.models.sotp.TrueIdSmartOTPUIConfig p10)
    {
        p6.removeAllViews();
        int v7_1 = p7.getEffectiveMax();
        int v0 = 0;
        while (v0 < v7_1) {
            int v4_2;
            android.view.View v1_1 = new android.view.View(p8);
            android.widget.LinearLayout$LayoutParams v2_3 = new android.graphics.drawable.GradientDrawable();
            v2_3.setShape(1);
            if (v0 >= p9.length()) {
                try {
                    v4_2 = android.graphics.Color.parseColor(p10.getPinDotEmptyColor());
                } catch (Exception) {
                    v4_2 = -12303292;
                }
            } else {
                try {
                    v4_2 = android.graphics.Color.parseColor(p10.getPinDotFilledColor());
                } catch (Exception) {
                    v4_2 = -1;
                }
            }
            v2_3.setColor(v4_2);
            try {
            } catch (Exception) {
            }
            v2_3.setStroke(vn.ai.trueid.sdk.navigation.TrueIDV3.e(p8, 1), android.graphics.Color.parseColor(p10.getPinDotBorderColor()));
            v1_1.setBackground(v2_3);
            android.widget.LinearLayout$LayoutParams v2_1 = new android.widget.LinearLayout$LayoutParams(vn.ai.trueid.sdk.navigation.TrueIDV3.e(p8, 14), vn.ai.trueid.sdk.navigation.TrueIDV3.e(p8, 14));
            v2_1.setMarginStart(vn.ai.trueid.sdk.navigation.TrueIDV3.e(p8, 7));
            v2_1.setMarginEnd(vn.ai.trueid.sdk.navigation.TrueIDV3.e(p8, 7));
            p6.addView(v1_1, v2_1);
            v0++;
        }
        return;
    }

    public static final void generateOtpOCRA(android.app.Activity p7, String p8, String p9, String p10, vn.ai.trueid.sdk.navigation.TrueIdSmartOTPListener p11)
    {
        if (p10.length() > 0) {
            vn.ai.trueid.sdk.models.sotp.TrueIdSmartOTPConfig v0_1 = vn.ai.trueid.sdk.navigation.TrueIDV3.h;
            if (v0_1 != null) {
                v0_1.setUserId(p10);
            }
        }
        vn.ai.trueid.sdk.navigation.TrueIDV3$generateOtpOCRA$1 v10_2 = new vn.ai.trueid.sdk.navigation.TrueIDV3$generateOtpOCRA$1(new vn.ai.trueid.sdk.models.sotp.TrueIdSmartOTPResult(0, 0, 0, 7, 0), p11);
        vn.ai.trueid.sdk.networks.g0.f();
        vn.ai.trueid.sdk.networks.g0.b(new pS.e(p8, p9, v10_2, p7));
        return;
    }

    public static synthetic void generateOtpOCRA$default(android.app.Activity p0, String p1, String p2, String p3, vn.ai.trueid.sdk.navigation.TrueIdSmartOTPListener p4, int p5, Object p6)
    {
        if ((p5 & 8) != 0) {
            p3 = "";
        }
        vn.ai.trueid.sdk.navigation.TrueIDV3.generateOtpOCRA(p0, p1, p2, p3, p4);
        return;
    }

    public static final native String getChallengeColorNative(String p0, int p1);

    public static final String getDeviceId()
    {
        String v0_0 = vn.ai.trueid.sdk.navigation.TrueIDV3.a;
        if (v0_0 == null) {
            return vn.ai.trueid.sdk.utils.Helpers.Companion.getAdvId();
        } else {
            return v0_0.getId();
        }
    }

    public static final void handleSmartOtpPush(android.app.Activity p5, String p6, String p7, String p8, String p9, vn.ai.trueid.sdk.navigation.TrueIdSmartOTPListener p10)
    {
        if (p9.length() > 0) {
            long v0_2 = vn.ai.trueid.sdk.navigation.TrueIDV3.h;
            if (v0_2 != 0) {
                v0_2.setUserId(p9);
            }
        }
        long v2_6;
        vn.ai.trueid.sdk.navigation.TrueIDV3.j = p10;
        vn.ai.trueid.sdk.navigation.TrueIDV3$handleSmartOtpPush$1 v9_2 = new vn.ai.trueid.sdk.navigation.TrueIDV3$handleSmartOtpPush$1(p5, p7, p8, p6);
        if (p8 != null) {
            if (p8.getBytes(kotlin.text.Charsets.UTF_8).length <= 32768) {
                try {
                    long v2_4 = new org.json.JSONObject(p8);
                } catch (Exception) {
                    v2_6 = new Ok.x(4001, "transactionData validation failed: all values must be strings", 0);
                }
                if ((v2_4.has("transactionType")) && (v2_4.optString("transactionType", "").length() != 0)) {
                    if (m9.I2.a(v2_4)) {
                        v2_6 = new Ok.x(1, "Success", 1);
                    } else {
                        v2_6 = new Ok.x(4001, "transactionData validation failed: all values must be strings", 0);
                    }
                } else {
                    v2_6 = new Ok.x(4002, "transactionData must contain non-empty \'transactionType\' field", 0);
                }
            } else {
                v2_6 = new Ok.x(4003, "transactionData exceeds 32 KiB limit", 0);
            }
        } else {
            v2_6 = new Ok.x(4001, "transactionData validation failed: all values must be strings", 0);
        }
        if (v2_6.a) {
            String v10_6 = 0;
            try {
                org.json.JSONArray v1_3;
                String v8_2 = kotlin.text.StringsKt.trim(p8).toString();
            } catch (Exception) {
                if (v10_6 != null) {
                    String v8_3 = vn.ai.trueid.sdk.utils.SmartOTPHelper.vaultCheckDebugger();
                    if (v8_3 == null) {
                        String v8_4 = vn.ai.trueid.sdk.utils.SmartOTPHelper.vaultCheckIntegrity();
                        if (v8_4 == null) {
                            String v8_5 = vn.ai.trueid.sdk.utils.SmartOTPHelper.a(p5);
                            if (v8_5 != null) {
                                long v0_3 = vn.ai.trueid.sdk.utils.SmartOTPHelper.s(p5, v8_5);
                                if (v0_3 > 0) {
                                    v9_2.fail(new vn.ai.trueid.sdk.models.TrueError(423, vn.ai.trueid.sdk.networks.g0.e(Integer.valueOf(((int) ((v0_3 + 999) / 1000))))));
                                    return;
                                }
                            }
                            vn.ai.trueid.sdk.networks.g0.f();
                            vn.ai.trueid.sdk.networks.g0.b(new r2.j(p6, p7, v9_2, v10_6));
                        } else {
                            v9_2.fail(new vn.ai.trueid.sdk.models.TrueError(v8_4, "Security threat: Integrity scan failed"));
                        }
                    } else {
                        v9_2.fail(new vn.ai.trueid.sdk.models.TrueError(v8_3, "Security threat: Debugger detected"));
                    }
                } else {
                    v9_2.fail(new vn.ai.trueid.sdk.models.TrueError(4001, "transactionData JCS canonicalization failed"));
                }
            }
            if (!kotlin.text.y.startsWith$default(v8_2, "{", 0, 2, 0)) {
                if (!kotlin.text.y.startsWith$default(v8_2, "[", 0, 2, 0)) {
                } else {
                    v1_3 = new org.json.JSONArray(v8_2);
                }
            } else {
                v1_3 = new org.json.JSONObject(v8_2);
            }
            v10_6 = m9.C2.a(v1_3);
        } else {
            v9_2.fail(new vn.ai.trueid.sdk.models.TrueError(v2_6.b, v2_6.c));
        }
        return;
    }

    public static synthetic void handleSmartOtpPush$default(android.app.Activity p6, String p7, String p8, String p9, String p10, vn.ai.trueid.sdk.navigation.TrueIdSmartOTPListener p11, int p12, Object p13)
    {
        if ((p12 & 16) != 0) {
            p10 = "";
        }
        vn.ai.trueid.sdk.navigation.TrueIDV3.handleSmartOtpPush(p6, p7, p8, p9, p10, p11);
        return;
    }

    public static boolean i(vn.ai.trueid.sdk.models.FaceEnrollConfig p10)
    {
        try {
            if ((p10.getDomain().length() != 0) && ((!kotlin.text.StringsKt.isBlank(p10.getDomain())) && (android.webkit.URLUtil.isValidUrl(p10.getDomain())))) {
                if (p10.getSdkKey().length() > 0) {
                    String v0_15 = p10.getSdkKey();
                    if (v0_15.length() != 0) {
                        android.util.Base64.decode(kotlin.text.StringsKt.trim(v0_15).toString(), 2);
                        if ((p10.getSignVersion().length() != 0) && (!kotlin.jvm.internal.Intrinsics.areEqual(p10.getSignVersion(), "1"))) {
                            if (kotlin.jvm.internal.Intrinsics.areEqual(p10.getSignVersion(), "2")) {
                                if (p10.getCreateRequestEndPoint().length() != 0) {
                                    String v0_27 = p10.getDomain();
                                    org.json.JSONObject v10_13 = p10.getCreateRequestEndPoint();
                                    int v3_10 = new StringBuilder();
                                    v3_10.append(v0_27);
                                    v3_10.append(v10_13);
                                    if (!android.webkit.URLUtil.isValidUrl(v3_10.toString())) {
                                    }
                                } else {
                                }
                            }
                        } else {
                            int v3_12;
                            String v0_28 = p10.configHeaderJson();
                            if (!v0_28.has("Authorization")) {
                                v3_12 = "";
                            } else {
                                v3_12 = v0_28.getString("Authorization");
                            }
                            String v4_4 = v3_12;
                            if ((v4_4.length() > 0) && (kotlin.text.y.replace$default(v4_4, "Bearer ", "", 0, 4, 0).length() > 52)) {
                                v4_4 = kotlin.text.y.replace$default(v4_4, "Bearer ", "", 0, 4, 0);
                            }
                            if (p10.getAccessTokenEndPoint().length() != 0) {
                                int v3_2 = p10.getDomain();
                                org.json.JSONObject v10_1 = p10.getAccessTokenEndPoint();
                                java.nio.charset.Charset v5_1 = new StringBuilder();
                                v5_1.append(v3_2);
                                v5_1.append(v10_1);
                                if (android.webkit.URLUtil.isValidUrl(v5_1.toString())) {
                                    return 1;
                                }
                            }
                            if (v0_28.length() > 0) {
                                if (v4_4.length() != 0) {
                                    org.json.JSONObject v10_8 = kotlin.text.StringsKt.y(v4_4, new String[] {"."}), 0, 6);
                                    if (v10_8.size() == 3) {
                                        java.nio.charset.Charset v5_2 = kotlin.text.Charsets.UTF_8;
                                        if (new org.json.JSONObject(new String(android.util.Base64.decode(((String) v10_8.get(0)), 11), v5_2)).has("alg")) {
                                            new org.json.JSONObject(new String(android.util.Base64.decode(((String) v10_8.get(1)), 11), v5_2));
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

    public static synthetic org.json.JSONObject importColor$default(vn.ai.trueid.sdk.navigation.TrueIDV3 p0, String p1, org.json.JSONObject p2, boolean p3, int p4, Object p5)
    {
        if ((p4 & 4) != 0) {
            p3 = 1;
        }
        return p0.importColor(p1, p2, p3);
    }

    public static final String info(android.content.Context p2)
    {
        try {
            String v2_3 = vn.ai.trueid.sdk.navigation.TrueIDV3.INSTANCE.getNfccccdReader().d(p2);
            StringBuilder v1_3 = new StringBuilder("|NFC: ");
            v1_3.append(v2_3);
            String v2_1 = v1_3.toString();
        } catch (Throwable) {
            v2_1 = "";
        }
        return androidx.camera.camera2.internal.G.e("BUILD:AND|PACKAGE:vn.ai.trueid.sdk|SDK:3.3.18", v2_1);
    }

    public static final boolean isDeviceHasNFC()
    {
        vn.ai.trueid.sdk.navigation.TrueIDV3.INSTANCE.getClass();
        android.nfc.NfcAdapter v0_8 = vn.ai.trueid.sdk.navigation.TrueIDV3.d.optJSONArray("stepVerification");
        if (v0_8 != null) {
            String v1_1 = Og.x.until(0, v0_8.length());
            if ((!(v1_1 instanceof java.util.Collection)) || (!((java.util.Collection) v1_1).isEmpty())) {
                String v1_2 = v1_1.x();
                while (v1_2.c) {
                    if (kotlin.jvm.internal.Intrinsics.areEqual(v0_8.optString(v1_2.nextInt()), "SCANNFC")) {
                        if (((android.nfc.NfcManager) vn.ai.trueid.sdk.utils.Helpers.Companion.getContext().getSystemService("nfc")).getDefaultAdapter() != null) {
                            break;
                        }
                        return 0;
                    }
                }
            }
        }
        return 1;
    }

    public static final boolean isSmartOtpPinSet(android.content.Context p1)
    {
        if (vn.ai.trueid.sdk.utils.SmartOTPHelper.a(p1) == null) {
            return 0;
        } else {
            return 1;
        }
    }

    public static final boolean isStrongBiometricAvailable(android.content.Context p1)
    {
        return vn.ai.trueid.sdk.utils.SmartOTPHelper.l(p1);
    }

    public static boolean j(vn.ai.trueid.sdk.models.NFCConfig p1)
    {
        if ((p1.getDomain().length() != 0) && ((!kotlin.text.StringsKt.isBlank(p1.getDomain())) && (android.webkit.URLUtil.isValidUrl(p1.getDomain())))) {
            return 1;
        } else {
            return 0;
        }
    }

    public static void k(vn.ai.trueid.sdk.models.FaceEnrollInput p1)
    {
        if ((kotlin.text.StringsKt.trim(p1.getIdNumber()).toString().length() != 0) || (kotlin.text.StringsKt.trim(p1.getPersonID()).toString().length() != 0)) {
            kotlin.text.StringsKt.trim(p1.getPersonID()).toString().getClass();
            return;
        } else {
            return;
        }
    }

    public static final boolean reconcileAppInstance(android.content.Context p11)
    {
        String v4_2 = vn.ai.trueid.sdk.utils.Helpers.Companion;
        v4_2.setContext(p11);
        if (vn.ai.trueid.sdk.utils.SmartOTPHelper.k()) {
            String v5_0 = vn.ai.trueid.sdk.utils.SmartOTPHelper.h(p11);
            android.content.SharedPreferences$Editor v8_1 = p11.getSharedPreferences("trueid_sotp_instance", 0).getString("credential_instance_id", 0);
            if (v8_1 != null) {
                if (!kotlin.jvm.internal.Intrinsics.areEqual(v8_1, v5_0)) {
                    String v4_1 = vn.ai.trueid.sdk.utils.SmartOTPHelper.a(p11);
                    if (v4_1 == null) {
                        v4_1 = "";
                    }
                    p11.getSharedPreferences("sotp_pin_seed_store", 0).edit().remove("pin_seed_".concat(v4_1)).remove("pin_meta_".concat(v4_1)).remove("sotp_type_".concat(v4_1)).remove("sotp_lock_".concat(v4_1)).apply();
                    Object[] v0_7 = java.security.KeyStore.getInstance("AndroidKeyStore");
                    v0_7.load(0);
                    Object[] v1_6 = kotlin.collections.E.listOf(new String[] {v4_1, "hmacKeyAlias"})).iterator();
                    while (v1_6.hasNext()) {
                        String v2_6 = ((String) v1_6.next());
                        if (v0_7.containsAlias(v2_6)) {
                            v0_7.deleteEntry(v2_6);
                        }
                    }
                    Object[] v1_8 = new StringBuilder();
                    v1_8.append("\u1f9f9 Wiped SOTP credential for ");
                    v1_8.append(v4_1);
                    vn.ai.trueid.sdk.utils.Helpers.Companion.printLog(new Object[] {"SmartOTPHelper", v1_8.toString()}));
                    p11.getSharedPreferences("trueid_sotp_instance", 0).edit().remove("credential_instance_id").apply();
                    vn.ai.trueid.sdk.utils.Helpers.Companion.trueIdLog(new Object[] {"\u1f501 Reinstall detected (appInstanceId changed) \u2014 wiped stale SOTP credential"}));
                    return 1;
                }
            } else {
                p11.getSharedPreferences("trueid_sotp_instance", 0).edit().putString("credential_instance_id", vn.ai.trueid.sdk.utils.SmartOTPHelper.h(p11)).apply();
                v4_2.trueIdLog(new Object[] {"\u1f516 Bound appInstanceId to existing SOTP credential (legacy migration)"}));
                return 0;
            }
        }
        return 0;
    }

    public static final void registerSeed(android.app.Activity p7, String p8, String p9, vn.ai.trueid.sdk.navigation.TrueIdSmartOTPListener p10)
    {
        if (p9.length() > 0) {
            vn.ai.trueid.sdk.navigation.TrueIDV3$registerSeed$2 v0_4 = vn.ai.trueid.sdk.navigation.TrueIDV3.h;
            if (v0_4 != null) {
                v0_4.setUserId(p9);
            }
        }
        int v1_0 = new vn.ai.trueid.sdk.models.sotp.TrueIdSmartOTPResult(0, 0, 0, 7, 0);
        String v9_2 = new A0.h(18, p7);
        vn.ai.trueid.sdk.navigation.TrueIDV3$registerSeed$2 v0_3 = new vn.ai.trueid.sdk.navigation.TrueIDV3$registerSeed$2(v1_0, p10);
        vn.ai.trueid.sdk.networks.e0 v10_1 = vn.ai.trueid.sdk.utils.SmartOTPHelper.vaultCheckDebugger();
        if (v10_1 == null) {
            vn.ai.trueid.sdk.networks.e0 v10_2 = vn.ai.trueid.sdk.utils.SmartOTPHelper.vaultCheckIntegrity();
            if (v10_2 == null) {
                if ((vn.ai.trueid.sdk.navigation.TrueIDV3.INSTANCE.getSmartOtpConfig$trueid_release().getSotpType() != 3) || (vn.ai.trueid.sdk.utils.SmartOTPHelper.l(p7))) {
                    vn.ai.trueid.sdk.networks.g0.f();
                    vn.ai.trueid.sdk.networks.g0.b(new vn.ai.trueid.sdk.networks.e0(v0_3, p8, p7, v9_2));
                    return;
                } else {
                    v0_3.fail(new vn.ai.trueid.sdk.models.TrueError(345, "Thi\u1ebft b\u1ecb ch\u01b0a b\u1eadt sinh tr\u1eafc h\u1ecdc m\u1ea1nh (v\u00e2n tay). Vui l\u00f2ng b\u1eadt v\u00e2n tay ho\u1eb7c ch\u1ecdn ph\u01b0\u01a1ng th\u1ee9c m\u00e3 PIN."));
                    return;
                }
            } else {
                v0_3.fail(new vn.ai.trueid.sdk.models.TrueError(v10_2, "Security threat: Integrity scan failed"));
                return;
            }
        } else {
            v0_3.fail(new vn.ai.trueid.sdk.models.TrueError(v10_1, "Security threat: Debugger detected"));
            return;
        }
    }

    public static synthetic void registerSeed$default(android.app.Activity p0, String p1, String p2, vn.ai.trueid.sdk.navigation.TrueIdSmartOTPListener p3, int p4, Object p5)
    {
        if ((p4 & 4) != 0) {
            p2 = "";
        }
        vn.ai.trueid.sdk.navigation.TrueIDV3.registerSeed(p0, p1, p2, p3);
        return;
    }

    public static final void revokeSOTPCredentialByDeviceId(android.app.Activity p6, String p7, String p8, String p9, vn.ai.trueid.sdk.navigation.TrueIdSmartOTPListener p10)
    {
        if (p9.length() > 0) {
            vn.ai.trueid.sdk.navigation.TrueIDV3$revokeSOTPCredentialByDeviceId$1 v6_3 = vn.ai.trueid.sdk.navigation.TrueIDV3.h;
            if (v6_3 != null) {
                v6_3.setUserId(p9);
            }
        }
        vn.ai.trueid.sdk.networks.g0.a(new h3.J(p7, p8, new vn.ai.trueid.sdk.navigation.TrueIDV3$revokeSOTPCredentialByDeviceId$1(new vn.ai.trueid.sdk.models.sotp.TrueIdSmartOTPResult(0, 0, 0, 7, 0), p10), 14));
        return;
    }

    public static synthetic void revokeSOTPCredentialByDeviceId$default(android.app.Activity p0, String p1, String p2, String p3, vn.ai.trueid.sdk.navigation.TrueIdSmartOTPListener p4, int p5, Object p6)
    {
        if ((p5 & 8) != 0) {
            p3 = "";
        }
        vn.ai.trueid.sdk.navigation.TrueIDV3.revokeSOTPCredentialByDeviceId(p0, p1, p2, p3, p4);
        return;
    }

    public static final void revokeSmartOtpDevice(android.app.Activity p7, String p8, String p9, vn.ai.trueid.sdk.navigation.TrueIdSmartOTPListener p10)
    {
        if (p9.length() > 0) {
            int v0_2 = vn.ai.trueid.sdk.navigation.TrueIDV3.h;
            if (v0_2 != 0) {
                v0_2.setUserId(p9);
            }
        }
        vn.ai.trueid.sdk.navigation.TrueIDV3$revokeSmartOtpDevice$1 v9_2 = new vn.ai.trueid.sdk.navigation.TrueIDV3$revokeSmartOtpDevice$1(new vn.ai.trueid.sdk.models.sotp.TrueIdSmartOTPResult(0, 0, 0, 7, 0), p10);
        vn.ai.trueid.sdk.networks.g0.f();
        vn.ai.trueid.sdk.networks.g0.b(new hh.a(p8, p7, v9_2, 15));
        return;
    }

    public static synthetic void revokeSmartOtpDevice$default(android.app.Activity p0, String p1, String p2, vn.ai.trueid.sdk.navigation.TrueIdSmartOTPListener p3, int p4, Object p5)
    {
        if ((p4 & 4) != 0) {
            p2 = "";
        }
        vn.ai.trueid.sdk.navigation.TrueIDV3.revokeSmartOtpDevice(p0, p1, p2, p3);
        return;
    }

    public static final void saveData(String p1, String p2)
    {
        vn.ai.trueid.sdk.utils.Helpers.Companion.saveData(p1, p2);
        return;
    }

    public static final void setConfigEKYC(android.content.Context p12, String p13, String p14, String p15, vn.ai.trueid.sdk.models.ConfigEndPoint p16, String p17, String p18, String p19, int p20, String p21)
    {
        if ((p21 != null) && (vn.ai.trueid.sdk.utils.j.f(p21))) {
            vn.ai.trueid.sdk.navigation.TrueIDV3.d = new org.json.JSONObject(p21);
        }
        if (p14 == null) {
            p14 = "";
        }
        vn.ai.trueid.sdk.navigation.TrueIDV3.INSTANCE.setConfigEKYC(p12, new vn.ai.trueid.sdk.models.EkycConfig(p15, 0, p13, p14, p18, p19, p16, p20, 2, 0));
        if ((vn.ai.trueid.sdk.navigation.TrueIDV3.t) && ((p17.length() > 0) && (vn.ai.trueid.sdk.utils.j.f(p17)))) {
            vn.ai.trueid.sdk.navigation.TrueIDV3.c = new org.json.JSONObject(p17);
        }
        return;
    }

    public static synthetic void setConfigEKYC$default(android.content.Context p1, String p2, String p3, String p4, vn.ai.trueid.sdk.models.ConfigEndPoint p5, String p6, String p7, String p8, int p9, String p10, int p11, Object p12)
    {
        if ((p11 & 2) != 0) {
            p2 = "";
        }
        if ((p11 & 4) != 0) {
            p3 = "";
        }
        if ((p11 & 8) != 0) {
            p4 = "";
        }
        if ((p11 & 16) != 0) {
            p5 = new vn.ai.trueid.sdk.models.ConfigEndPoint(p4);
        }
        if ((p11 & 32) != 0) {
            p6 = "";
        }
        if ((p11 & 64) != 0) {
            p7 = "";
        }
        if ((p11 & 128) != 0) {
            p8 = "1";
        }
        if ((p11 & 256) != 0) {
            p9 = 0;
        }
        if ((p11 & 512) != 0) {
            p10 = 0;
        }
        vn.ai.trueid.sdk.navigation.TrueIDV3.setConfigEKYC(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10);
        return;
    }

    public static final void setConfigEKYCCapture(android.content.Context p20, vn.ai.trueid.sdk.navigation.TrueIDEKYCCaptureConfig p21)
    {
        try {
            vn.ai.trueid.sdk.utils.Helpers.Companion.init(p20);
        } catch (UnsatisfiedLinkError) {
            return;
        }
        if ((p21.getConfigUI().length() > 0) && (vn.ai.trueid.sdk.utils.j.f(p21.getConfigUI()))) {
            vn.ai.trueid.sdk.navigation.TrueIDV3.d = new org.json.JSONObject(p21.getConfigUI());
        }
        vn.ai.trueid.sdk.navigation.TrueIDV3.e = new vn.ai.trueid.sdk.models.EkycConfig("", 0, p21.getAppId(), "", p21.getSdkKey(), p21.getSignVersion(), vn.ai.trueid.sdk.models.ConfigEndPoint.copy$default(p21.getConfigEndPoint(), "", 0, 0, 0, 0, 0, 0, 0, 254, 0), p21.getLivenessType(), 2, 0);
        if ((p21.getConfigHeader().length() > 0) && (vn.ai.trueid.sdk.utils.j.f(p21.getConfigHeader()))) {
            vn.ai.trueid.sdk.navigation.TrueIDV3.c = new org.json.JSONObject(p21.getConfigHeader());
        }
        fk.a.a();
        return;
    }

    public static final void setConfigFaceAuthen(android.app.Activity p7, vn.ai.trueid.sdk.models.FaceAuthenConfig p8)
    {
        String v0_11 = p7.getSharedPreferences("vendor_prefs", 0).getString("vendor_id", 0);
        if (v0_11 == null) {
            v0_11 = "TRUEID";
        }
        String v0_1;
        vn.ai.trueid.sdk.navigation.TrueIDV3 v2_2 = v0_11.hashCode();
        if (v2_2 == -296101550) {
            if (v0_11.equals("moonbase")) {
                v0_1 = "\n                                    {\n                                      \"primaryColor\":  \"#2BAA50\",\n                                      \"secondaryColor\":\"#2BAA50\",\n                                      \"titleColor\":    \"#FFFFFF\",\n                                      \"errorColor\":    \"#EF4444\",\n                                      \"textColor\":     \"#FFFFFF\",\n                                      \"borderInputColor\":\"#2BAA50\",\n                                      \"backgroundColor\":\"#000000\",\n                                      \"closeColor\":    \"#FFFFFF\",\n                                      \"stepBarColor\":  \"#26B840\",\n                                      \"stepBarBackgroundColor\":\"#1A1A1A\",\n                                      \"borderRadius\":  16,\n                                      \"ovalColor\":     \"#2BAA50\",\n                                      \"animationOvalColor\":\"#2BAA50\",\n                                      \"successColor\":  \"#26B840\",\n                                      \"trueidWatermark\":false,\n                                      \"isShowStepBar\": false,\n                                      \"isAutoProcess\": true,\n                                      \"overlayAlpha\":  0.9\n                                    }\n                                    ";
            } else {
                String v0_10 = new org.json.JSONObject();
                vn.ai.trueid.sdk.navigation.TrueIDV3 v2_8 = p8.getPrimaryColor();
                if (v2_8 != null) {
                    v0_10.put("primaryColor", v2_8);
                }
                vn.ai.trueid.sdk.navigation.TrueIDV3 v2_10 = p8.getSecondaryColor();
                if (v2_10 != null) {
                    v0_10.put("secondaryColor", v2_10);
                }
                vn.ai.trueid.sdk.navigation.TrueIDV3 v2_11 = p8.getErrorColor();
                if (v2_11 != null) {
                    v0_10.put("errorColor", v2_11);
                }
                vn.ai.trueid.sdk.navigation.TrueIDV3 v2_12 = p8.getTextColor();
                if (v2_12 != null) {
                    v0_10.put("textColor", v2_12);
                }
                vn.ai.trueid.sdk.navigation.TrueIDV3 v2_13 = p8.getBackgroundColor();
                if (v2_13 != null) {
                    v0_10.put("backgroundColor", v2_13);
                }
                vn.ai.trueid.sdk.navigation.TrueIDV3 v2_14 = p8.getCloseColor();
                if (v2_14 != null) {
                    v0_10.put("closeColor", v2_14);
                }
                vn.ai.trueid.sdk.navigation.TrueIDV3 v2_15 = p8.getTextButtonColor();
                if (v2_15 != null) {
                    v0_10.put("textButtonColor", v2_15);
                }
                v0_10.put("maxRetry", p8.getMaxRetry());
                v0_10.put("isAutoProcess", p8.isAutoProcess());
                vn.ai.trueid.sdk.navigation.TrueIDV3 v2_18 = p8.getOvalColor();
                vn.ai.trueid.sdk.navigation.TrueIDV3 v4_1 = "";
                if (v2_18.length() == 0) {
                    v2_18 = p8.getPrimaryColor();
                    if (v2_18 == null) {
                        v2_18 = "";
                    }
                }
                v0_10.put("ovalColor", v2_18);
                vn.ai.trueid.sdk.navigation.TrueIDV3 v2_19 = p8.getAnimationOvalColor();
                if (v2_19.length() == 0) {
                    vn.ai.trueid.sdk.navigation.TrueIDV3 v2_20 = p8.getPrimaryColor();
                    if (v2_20 != null) {
                        v4_1 = v2_20;
                    }
                    v2_19 = v4_1;
                }
                v0_10.put("animationOvalColor", v2_19);
                vn.ai.trueid.sdk.navigation.TrueIDV3.INSTANCE.importColor("\n                                    {\n                                      \"primaryColor\":\"#26B840\",\n                                      \"secondaryColor\":\"#42526E\",\n                                      \"titleColor\":\"#253858\",\n                                      \"errorColor\":\"#FF6234\",\n                                      \"textColor\":\"#253858\",\n                                      \"borderInputColor\":\"#D3D9E0\",\n                                      \"backgroundColor\":\"#FFFFFF\",\n                                      \"closeColor\":\"#000000\",\n                                      \"stepBarColor\":\"#26B840\",\n                                      \"stepBarBackgroundColor\":\"#E4F4FF\",\n                                      \"fontName\":\"Lato Italic\",\n                                      \"borderRadius\":8,\n                                      \"maxRetry\":1000,\n                                      \"defaultSpacing\":20,\n                                      \"imageConfirmation\":true,\n                                      \"trueidWatermark\":true,\n                                      \"isDisableSecurity\":false,\n                                      \"isShowStepBar\":false,\n                                      \"isShowSkipNFC\":false,\n                                      \"returnNFC\":true,\n                                      \"nfcSkipMaxRetry\":true,\n                                      \"ovalColor\": \"#26B840\"\n                                    }\n                                    ", v0_10, 0);
                v0_1 = v0_10.toString();
            }
        } else {
            if (v2_2 == 103390) {
                if (v0_11.equals("hlb")) {
                    v0_1 = "{\n  \"primaryColor\": \"#000000\",\n  \"titleColor\": \"#00CCC6\",\n  \"textButtonColor\": \"#FFFFFF\",\n  \"errorColor\": \"#FF6234\",\n  \"textColor\": \"#091E42\",\n  \"borderInputColor\": \"#00CCC6\",\n  \"backgroundColor\": \"#FFFFFF\",\n  \"closeColor\": \"#253858\",\n  \"borderRadius\": 16,\n  \"maxRetry\": 4,\n  \"defaultSpacing\": 26,\n  \"imageConfirmation\": false,\n  \"trueidWatermark\": true,\n  \"isDisableSecurity\": false,\n  \"isShowStepBar\": false,\n  \"isShowSkipNFC\": false,\n  \"returnNFC\": true,\n  \"nfcSkipMaxRetry\": true\n}\n";
                }
            } else {
                if ((v2_2 == 120702) && (v0_11.equals("zlp"))) {
                    v0_1 = "{\n  \"primaryColor\": \"#0033C9\",\n  \"titleColor\": \"#FFFFFF\",\n  \"textButtonColor\": \"#FFFFFF\",\n  \"borderInputColor\": \"#00CCC6\",\n  \"textColor\": \"#FFFFFF\",\n  \"animationOvalColor\": \"#FFFFFF\",\n  \"ovalColor\": \"#FFFFFF\",\n  \"successColor\": \"#00CF6A\",\n  \"backgroundColor\": \"#000000\",\n  \"closeColor\": \"#FFFFFF\",\n  \"borderRadius\": 24,\n  \"defaultSpacing\": 26,\n  \"trueidWatermark\": false,\n  \"isAutoProcess\": true,\n  \"isShowCloseButton\": true\n}\n";
                }
            }
        }
        vn.ai.trueid.sdk.navigation.TrueIDV3.d = vn.ai.trueid.sdk.navigation.TrueIDV3.importColor$default(vn.ai.trueid.sdk.navigation.TrueIDV3.INSTANCE, v0_1, vn.ai.trueid.sdk.navigation.TrueIDV3.d, 0, 4, 0);
        String v0_3 = p8.getFontName();
        if (v0_3 != null) {
            vn.ai.trueid.sdk.navigation.TrueIDV3.d.put("fontName", v0_3);
        }
        vn.ai.trueid.sdk.navigation.TrueIDV3.d.put("trueidWatermark", p8.getTrueidWatermark());
        vn.ai.trueid.sdk.navigation.AuthenticationID.configure(p7, p8);
        return;
    }

    public static final void setConfigFaceEnroll(android.content.Context p1, vn.ai.trueid.sdk.models.FaceEnrollConfig p2)
    {
        try {
            vn.ai.trueid.sdk.utils.Helpers.Companion.init(p1);
            vn.ai.trueid.sdk.navigation.TrueIDV3.f = p2;
            fk.a.a();
            return;
        } catch (UnsatisfiedLinkError) {
            return;
        } catch (Throwable v1_1) {
            throw v1_1;
        }
    }

    public static final void setConfigFaceLiveness(android.app.Activity p7, vn.ai.trueid.sdk.models.FaceLivenessConfig p8)
    {
        org.json.JSONObject v0_11 = p7.getSharedPreferences("vendor_prefs", 0).getString("vendor_id", 0);
        if (v0_11 == null) {
            v0_11 = "TRUEID";
        }
        org.json.JSONObject v0_5;
        boolean v2_1 = v0_11.hashCode();
        if (v2_1 == -296101550) {
            if (v0_11.equals("moonbase")) {
                v0_5 = "\n                                    {\n                                      \"primaryColor\":  \"#2BAA50\",\n                                      \"secondaryColor\":\"#2BAA50\",\n                                      \"titleColor\":    \"#FFFFFF\",\n                                      \"errorColor\":    \"#EF4444\",\n                                      \"textColor\":     \"#FFFFFF\",\n                                      \"borderInputColor\":\"#2BAA50\",\n                                      \"backgroundColor\":\"#000000\",\n                                      \"closeColor\":    \"#FFFFFF\",\n                                      \"stepBarColor\":  \"#26B840\",\n                                      \"stepBarBackgroundColor\":\"#1A1A1A\",\n                                      \"borderRadius\":  16,\n                                      \"ovalColor\":     \"#2BAA50\",\n                                      \"animationOvalColor\":\"#2BAA50\",\n                                      \"successColor\":  \"#26B840\",\n                                      \"trueidWatermark\":false,\n                                      \"isShowStepBar\": false,\n                                      \"isAutoProcess\": true,\n                                      \"overlayAlpha\":  0.9\n                                    }\n                                    ";
            } else {
                org.json.JSONObject v0_10 = new org.json.JSONObject();
                v0_10.put("primaryColor", p8.getPrimaryColor());
                v0_10.put("secondaryColor", p8.getSecondaryColor());
                v0_10.put("errorColor", p8.getErrorColor());
                v0_10.put("textColor", p8.getTextColor());
                v0_10.put("backgroundColor", p8.getBackgroundColor());
                v0_10.put("closeColor", p8.getCloseColor());
                v0_10.put("textButtonColor", p8.getTextButtonColor());
                v0_10.put("maxRetry", p8.getMaxRetry());
                v0_10.put("isAutoProcess", p8.isAutoProcess());
                boolean v2_16 = p8.getOvalColor();
                if (v2_16.length() == 0) {
                    v2_16 = p8.getPrimaryColor();
                }
                v0_10.put("ovalColor", v2_16);
                boolean v2_17 = p8.getAnimationOvalColor();
                if (v2_17.length() == 0) {
                    v2_17 = p8.getPrimaryColor();
                }
                v0_10.put("animationOvalColor", v2_17);
                vn.ai.trueid.sdk.navigation.TrueIDV3.INSTANCE.importColor("\n                                    {\n                                      \"primaryColor\":\"#26B840\",\n                                      \"secondaryColor\":\"#42526E\",\n                                      \"titleColor\":\"#253858\",\n                                      \"errorColor\":\"#FF6234\",\n                                      \"textColor\":\"#253858\",\n                                      \"borderInputColor\":\"#D3D9E0\",\n                                      \"backgroundColor\":\"#FFFFFF\",\n                                      \"closeColor\":\"#000000\",\n                                      \"stepBarColor\":\"#26B840\",\n                                      \"stepBarBackgroundColor\":\"#E4F4FF\",\n                                      \"fontName\":\"Lato Italic\",\n                                      \"borderRadius\":8,\n                                      \"maxRetry\":1000,\n                                      \"defaultSpacing\":20,\n                                      \"imageConfirmation\":true,\n                                      \"trueidWatermark\":true,\n                                      \"isDisableSecurity\":false,\n                                      \"isShowStepBar\":false,\n                                      \"isShowSkipNFC\":false,\n                                      \"returnNFC\":true,\n                                      \"nfcSkipMaxRetry\":true,\n                                      \"ovalColor\": \"#26B840\"\n                                    }\n                                    ", v0_10, 0);
                v0_5 = v0_10.toString();
            }
        } else {
            if (v2_1 == 103390) {
                if (v0_11.equals("hlb")) {
                    v0_5 = "{\n  \"primaryColor\": \"#000000\",\n  \"titleColor\": \"#00CCC6\",\n  \"textButtonColor\": \"#FFFFFF\",\n  \"errorColor\": \"#FF6234\",\n  \"textColor\": \"#091E42\",\n  \"borderInputColor\": \"#00CCC6\",\n  \"backgroundColor\": \"#FFFFFF\",\n  \"closeColor\": \"#253858\",\n  \"borderRadius\": 16,\n  \"maxRetry\": 4,\n  \"defaultSpacing\": 26,\n  \"imageConfirmation\": false,\n  \"trueidWatermark\": true,\n  \"isDisableSecurity\": false,\n  \"isShowStepBar\": false,\n  \"isShowSkipNFC\": false,\n  \"returnNFC\": true,\n  \"nfcSkipMaxRetry\": true\n}\n";
                }
            } else {
                if ((v2_1 == 120702) && (v0_11.equals("zlp"))) {
                    v0_5 = "{\n  \"primaryColor\": \"#0033C9\",\n  \"titleColor\": \"#FFFFFF\",\n  \"textButtonColor\": \"#FFFFFF\",\n  \"borderInputColor\": \"#00CCC6\",\n  \"textColor\": \"#FFFFFF\",\n  \"animationOvalColor\": \"#FFFFFF\",\n  \"ovalColor\": \"#FFFFFF\",\n  \"successColor\": \"#00CF6A\",\n  \"backgroundColor\": \"#000000\",\n  \"closeColor\": \"#FFFFFF\",\n  \"borderRadius\": 24,\n  \"defaultSpacing\": 26,\n  \"trueidWatermark\": false,\n  \"isAutoProcess\": true,\n  \"isShowCloseButton\": true\n}\n";
                }
            }
        }
        vn.ai.trueid.sdk.navigation.TrueIDV3.d = vn.ai.trueid.sdk.navigation.TrueIDV3.importColor$default(vn.ai.trueid.sdk.navigation.TrueIDV3.INSTANCE, v0_5, vn.ai.trueid.sdk.navigation.TrueIDV3.d, 0, 4, 0);
        org.json.JSONObject v0_2 = p8.getFontName();
        if (v0_2 != null) {
            vn.ai.trueid.sdk.navigation.TrueIDV3.d.put("fontName", v0_2);
        }
        vn.ai.trueid.sdk.navigation.TrueIDV3.d.put("trueidWatermark", p8.getTrueidWatermark());
        vn.ai.trueid.sdk.navigation.FaceLiveness.configure(p7, p8);
        return;
    }

    public static final void setConfigLivenessCapture(android.app.Activity p35, vn.ai.trueid.sdk.navigation.TrueIDLivenessCaptureConfig p36)
    {
        vn.ai.trueid.sdk.navigation.FaceLiveness v0_1 = new org.json.JSONObject();
        v0_1.put("primaryColor", p36.getPrimaryColor());
        v0_1.put("secondaryColor", p36.getSecondaryColor());
        v0_1.put("errorColor", p36.getErrorColor());
        v0_1.put("textColor", p36.getTextColor());
        v0_1.put("backgroundColor", p36.getBackgroundColor());
        v0_1.put("closeColor", p36.getCloseColor());
        v0_1.put("textButtonColor", p36.getTextButtonColor());
        v0_1.put("maxRetry", p36.getMaxRetry());
        v0_1.put("isAutoProcess", p36.isAutoProcess());
        vn.ai.trueid.sdk.models.FaceLivenessConfig v1_10 = p36.getOvalColor();
        if (v1_10.length() == 0) {
            v1_10 = p36.getPrimaryColor();
        }
        v0_1.put("ovalColor", v1_10);
        vn.ai.trueid.sdk.models.FaceLivenessConfig v1_11 = p36.getAnimationOvalColor();
        if (v1_11.length() == 0) {
            v1_11 = p36.getPrimaryColor();
        }
        v0_1.put("animationOvalColor", v1_11);
        v0_1.put("trueidWatermark", p36.getTrueidWatermark());
        String v3_1 = vn.ai.trueid.sdk.navigation.TrueIDV3.INSTANCE;
        v3_1.importColor("\n                                    {\n                                      \"primaryColor\":\"#26B840\",\n                                      \"secondaryColor\":\"#42526E\",\n                                      \"titleColor\":\"#253858\",\n                                      \"errorColor\":\"#FF6234\",\n                                      \"textColor\":\"#253858\",\n                                      \"borderInputColor\":\"#D3D9E0\",\n                                      \"backgroundColor\":\"#FFFFFF\",\n                                      \"closeColor\":\"#000000\",\n                                      \"stepBarColor\":\"#26B840\",\n                                      \"stepBarBackgroundColor\":\"#E4F4FF\",\n                                      \"fontName\":\"Lato Italic\",\n                                      \"borderRadius\":8,\n                                      \"maxRetry\":1000,\n                                      \"defaultSpacing\":20,\n                                      \"imageConfirmation\":true,\n                                      \"trueidWatermark\":true,\n                                      \"isDisableSecurity\":false,\n                                      \"isShowStepBar\":false,\n                                      \"isShowSkipNFC\":false,\n                                      \"returnNFC\":true,\n                                      \"nfcSkipMaxRetry\":true,\n                                      \"ovalColor\": \"#26B840\"\n                                    }\n                                    ", v0_1, 0);
        vn.ai.trueid.sdk.navigation.TrueIDV3.d = vn.ai.trueid.sdk.navigation.TrueIDV3.importColor$default(v3_1, v0_1.toString(), vn.ai.trueid.sdk.navigation.TrueIDV3.d, 0, 4, 0);
        vn.ai.trueid.sdk.navigation.FaceLiveness.INSTANCE.setupCapture$trueid_release(p35, new vn.ai.trueid.sdk.models.FaceLivenessConfig("", "", 0, 0, p36.getConfigHeader(), p36.getAppId(), "", p36.getSdkKey(), p36.getSignVersion(), p36.getMaxRetry(), p36.isShowCloseButton(), 0, p36.getPrimaryColor(), p36.getSecondaryColor(), p36.getErrorColor(), p36.getTextColor(), p36.getBackgroundColor(), p36.getCloseColor(), 0, p36.getLivenessType(), p36.getErrorDetails(), p36.isAutoProcess(), p36.getOvalColor(), p36.getAnimationOvalColor(), p36.isRecordFace(), p36.getRecordMaxDimension(), p36.getRecordBitRate(), p36.getRecordFrameRate(), p36.getRecordStartMode(), 0, p36.isShowLivenessSuccess(), 537135104, 0));
        return;
    }

    public static final void setConfigNFC(android.app.Activity p1, vn.ai.trueid.sdk.models.NFCConfig p2, String p3)
    {
        try {
            vn.ai.trueid.sdk.utils.Helpers.Companion.init(p1);
            try {
                if ((p3 != 0) && (new org.json.JSONObject(p3).length() > 0)) {
                    vn.ai.trueid.sdk.navigation.TrueIDV3.d = new org.json.JSONObject(p3);
                }
            } catch (org.json.JSONException) {
            }
            if (p3 == 0) {
                vn.ai.trueid.sdk.navigation.TrueIDV3.INSTANCE.getClass();
                if (vn.ai.trueid.sdk.navigation.TrueIDV3.j(p2)) {
                    vn.ai.trueid.sdk.navigation.TrueIDV3.g = p2;
                }
            } else {
                vn.ai.trueid.sdk.navigation.TrueIDV3.INSTANCE.getClass();
                if ((vn.ai.trueid.sdk.navigation.TrueIDV3.j(p2)) && (vn.ai.trueid.sdk.navigation.TrueIDV3.d.length() > 0)) {
                    vn.ai.trueid.sdk.navigation.TrueIDV3.g = p2;
                }
            }
            fk.a.a();
            vn.ai.trueid.sdk.utils.Helpers.Companion.setContext(p1);
            return;
        } catch (UnsatisfiedLinkError) {
            return;
        } catch (Throwable v1_1) {
            throw v1_1;
        }
    }

    public static synthetic void setConfigNFC$default(android.app.Activity p0, vn.ai.trueid.sdk.models.NFCConfig p1, String p2, int p3, Object p4)
    {
        if ((p3 & 4) != 0) {
            p2 = 0;
        }
        vn.ai.trueid.sdk.navigation.TrueIDV3.setConfigNFC(p0, p1, p2);
        return;
    }

    public static final void setLanguage(android.app.Activity p1, String p2)
    {
        if (!vn.ai.trueid.sdk.utils.j.f(p2)) {
            android.content.SharedPreferences$Editor v1_6 = android.preference.PreferenceManager.getDefaultSharedPreferences(p1).edit();
            v1_6.putString("Locale.Helper.Selected.Language", p2);
            v1_6.apply();
            v1_6.commit();
            return;
        } else {
            vn.ai.trueid.sdk.navigation.TrueIDV3.b = new org.json.JSONObject(p2);
            wk.a.g = new org.json.JSONObject(p2);
            return;
        }
    }

    public static final void setLanguage(android.app.Activity p1, String p2, String p3)
    {
        org.json.JSONObject v1_5 = android.preference.PreferenceManager.getDefaultSharedPreferences(p1).edit();
        if (p2 == null) {
            p2 = "en";
        }
        v1_5.putString("Locale.Helper.Selected.Language", p2);
        v1_5.apply();
        v1_5.commit();
        if (vn.ai.trueid.sdk.utils.j.f(p3)) {
            vn.ai.trueid.sdk.navigation.TrueIDV3.b = new org.json.JSONObject(p3);
        }
        return;
    }

    public static final void setSmartOtpConfig(android.app.Activity p1, vn.ai.trueid.sdk.models.sotp.TrueIdSmartOTPConfig p2)
    {
        vn.ai.trueid.sdk.utils.Helpers.Companion.init(p1);
        vn.ai.trueid.sdk.navigation.TrueIDV3.h = p2;
        return;
    }

    public static final void setVendor(android.app.Activity p4, String p5)
    {
        switch (p5.hashCode()) {
            case -95136461:
                if (p5.equals("193e78a9d1a5eb185a4c5ca7f892660a")) {
                    p4.getSharedPreferences("vendor_prefs", 0).edit().putString("vendor_id", "bvb").apply();
                    return;
                } else {
                }
            case 252260717:
                if (p5.equals("bd30d76ec50efcbb2395692aadad22d7")) {
                    p4.getSharedPreferences("vendor_prefs", 0).edit().putString("vendor_id", "hlb").apply();
                    return;
                } else {
                }
            case 911937303:
                if (p5.equals("03c51dbba8312aaa08b581c167a5c5bd")) {
                    p4.getSharedPreferences("vendor_prefs", 0).edit().putString("vendor_id", "moonbase").apply();
                    return;
                } else {
                }
            case 1071228349:
                if (p5.equals("8072eab9e8900fdf0d8923cceda0eda3")) {
                    p4.getSharedPreferences("vendor_prefs", 0).edit().putString("vendor_id", "zlp").apply();
                    return;
                } else {
                }
            default:
        }
        p4.getSharedPreferences("vendor_prefs", 0).edit().putString("vendor_id", "TRUEID").apply();
        return;
    }

    public static synthetic void setVendor$default(android.app.Activity p0, String p1, int p2, Object p3)
    {
        if ((p2 & 2) != 0) {
            p1 = "TRUEID";
        }
        vn.ai.trueid.sdk.navigation.TrueIDV3.setVendor(p0, p1);
        return;
    }

    public static final native String solveChallengeNative(String p0, String p1, byte[] p2);

    public static final void startEKYCCapture(android.app.Activity p2, vn.ai.trueid.sdk.navigation.TrueIDEKYCCaptureListener p3)
    {
        vn.ai.trueid.sdk.navigation.TrueIDV3.INSTANCE.a(p2, 0, p3);
        return;
    }

    public static final void startEKYCCaptureSimplify(android.app.Activity p1, String p2, vn.ai.trueid.sdk.navigation.TrueIDEKYCCaptureListener p3)
    {
        vn.ai.trueid.sdk.navigation.TrueIDV3.INSTANCE.a(p1, p2, p3);
        return;
    }

    public static final void startEKYCNFC(android.app.Activity p7, vn.ai.trueid.sdk.navigation.TrueIDListener p8)
    {
        vn.ai.trueid.sdk.navigation.TrueIDV3.info(p7);
        Class v0_10 = vn.ai.trueid.sdk.navigation.TrueIDV3.checkSecurityDevice();
        if (v0_10 == 1) {
            if (!vn.ai.trueid.sdk.navigation.TrueIDV3.p) {
                vn.ai.trueid.sdk.utils.TrueIDCaptureMetadata.INSTANCE.setFlow("EKYC");
                if (vn.ai.trueid.sdk.navigation.TrueIDV3.e != null) {
                    if (vn.ai.trueid.sdk.navigation.TrueIDV3.k == null) {
                        if (!vn.ai.trueid.sdk.navigation.TrueIDV3.t) {
                            p7.runOnUiThread(new CS.F(p7, 1));
                            return;
                        } else {
                            Class v0_11 = vn.ai.trueid.sdk.navigation.TrueIDV3.INSTANCE;
                            org.json.JSONObject v2_0 = v0_11.nfcRequire$trueid_release();
                            boolean v3 = vn.ai.trueid.sdk.utils.b.a();
                            boolean v4_1 = vn.ai.trueid.sdk.utils.b.c(0, p7, 0, 8);
                            if ((v2_0 != 1) || (v3)) {
                                if ((v2_0 != 1) || (v4_1)) {
                                    if (((v2_0 == null) && (!v3)) || ((v2_0 != null) || (v4_1))) {
                                        String v1_10 = p7.getSharedPreferences("vendor_prefs", 0).getString("vendor_id", 0);
                                        if (v1_10 == null) {
                                            v1_10 = "TRUEID";
                                        }
                                        String v1_12;
                                        if (!kotlin.jvm.internal.Intrinsics.areEqual(v1_10, "hlb")) {
                                            if (!kotlin.jvm.internal.Intrinsics.areEqual(v1_10, "moonbase")) {
                                                v1_12 = "\n                                    {\n                                      \"primaryColor\":\"#26B840\",\n                                      \"secondaryColor\":\"#42526E\",\n                                      \"titleColor\":\"#253858\",\n                                      \"errorColor\":\"#FF6234\",\n                                      \"textColor\":\"#253858\",\n                                      \"borderInputColor\":\"#D3D9E0\",\n                                      \"backgroundColor\":\"#FFFFFF\",\n                                      \"closeColor\":\"#000000\",\n                                      \"stepBarColor\":\"#26B840\",\n                                      \"stepBarBackgroundColor\":\"#E4F4FF\",\n                                      \"fontName\":\"Lato Italic\",\n                                      \"borderRadius\":8,\n                                      \"maxRetry\":1000,\n                                      \"defaultSpacing\":20,\n                                      \"imageConfirmation\":true,\n                                      \"trueidWatermark\":true,\n                                      \"isDisableSecurity\":false,\n                                      \"isShowStepBar\":false,\n                                      \"isShowSkipNFC\":false,\n                                      \"returnNFC\":true,\n                                      \"nfcSkipMaxRetry\":true,\n                                      \"ovalColor\": \"#26B840\"\n                                    }\n                                    ";
                                            } else {
                                                v1_12 = "\n                                    {\n                                      \"primaryColor\":  \"#2BAA50\",\n                                      \"secondaryColor\":\"#2BAA50\",\n                                      \"titleColor\":    \"#FFFFFF\",\n                                      \"errorColor\":    \"#EF4444\",\n                                      \"textColor\":     \"#FFFFFF\",\n                                      \"borderInputColor\":\"#2BAA50\",\n                                      \"backgroundColor\":\"#000000\",\n                                      \"closeColor\":    \"#FFFFFF\",\n                                      \"stepBarColor\":  \"#26B840\",\n                                      \"stepBarBackgroundColor\":\"#1A1A1A\",\n                                      \"borderRadius\":  16,\n                                      \"ovalColor\":     \"#2BAA50\",\n                                      \"animationOvalColor\":\"#2BAA50\",\n                                      \"successColor\":  \"#26B840\",\n                                      \"trueidWatermark\":false,\n                                      \"isShowStepBar\": false,\n                                      \"isAutoProcess\": true,\n                                      \"overlayAlpha\":  0.9\n                                    }\n                                    ";
                                            }
                                        } else {
                                            v1_12 = "{\n  \"primaryColor\": \"#000000\",\n  \"titleColor\": \"#00CCC6\",\n  \"textButtonColor\": \"#FFFFFF\",\n  \"errorColor\": \"#FF6234\",\n  \"textColor\": \"#091E42\",\n  \"borderInputColor\": \"#00CCC6\",\n  \"backgroundColor\": \"#FFFFFF\",\n  \"closeColor\": \"#253858\",\n  \"borderRadius\": 16,\n  \"maxRetry\": 4,\n  \"defaultSpacing\": 26,\n  \"imageConfirmation\": false,\n  \"trueidWatermark\": true,\n  \"isDisableSecurity\": false,\n  \"isShowStepBar\": false,\n  \"isShowSkipNFC\": false,\n  \"returnNFC\": true,\n  \"nfcSkipMaxRetry\": true\n}\n";
                                        }
                                        vn.ai.trueid.sdk.navigation.TrueIDV3.d = v0_11.importColor(v1_12, vn.ai.trueid.sdk.navigation.TrueIDV3.d, 0);
                                        vn.ai.trueid.sdk.utils.Helpers.Companion.setContext(p7);
                                        v0_11.clearListener();
                                        vn.ai.trueid.sdk.navigation.TrueIDV3.k = p8;
                                        fk.a.a();
                                        vn.ai.trueid.sdk.networks.q.g();
                                        p7.startActivity(new android.content.Intent(p7, vn.ai.trueid.sdk.activity.TrueIDActivity));
                                        return;
                                    } else {
                                        Class v0_15 = vn.ai.trueid.sdk.models.TrueError.Companion;
                                        p8.completion(new vn.ai.trueid.sdk.models.VerificationResult(v0_15.getNFC_NOT_ENABLED().getCode(), v0_15.getNFC_NOT_ENABLED().getMessage()));
                                        return;
                                    }
                                } else {
                                    Class v0_18 = vn.ai.trueid.sdk.models.TrueError.Companion;
                                    p8.completion(new vn.ai.trueid.sdk.models.VerificationResult(v0_18.getNFC_NOT_ENABLED().getCode(), v0_18.getNFC_NOT_ENABLED().getMessage()));
                                    return;
                                }
                            } else {
                                Class v0_1 = vn.ai.trueid.sdk.models.TrueError.Companion;
                                p8.completion(new vn.ai.trueid.sdk.models.VerificationResult(v0_1.getNFC_NOT_AVAILABLE().getCode(), v0_1.getNFC_NOT_AVAILABLE().getMessage()));
                                return;
                            }
                        }
                    } else {
                        return;
                    }
                } else {
                    Class v0_4 = vn.ai.trueid.sdk.models.TrueError.Companion;
                    p8.completion(new vn.ai.trueid.sdk.models.VerificationResult(v0_4.getERROR_CONFIG_SDK().getCode(), v0_4.getERROR_CONFIG_SDK().getMessage()));
                    return;
                }
            } else {
                p8.completion(new vn.ai.trueid.sdk.models.VerificationResult(306, "SDK is busy, please wait for the current flow to finish"));
                return;
            }
        } else {
            p8.completion(new vn.ai.trueid.sdk.models.VerificationResult(v0_10, vn.ai.trueid.sdk.navigation.TrueIDV3.INSTANCE.securityMessage(v0_10)));
            return;
        }
    }

    public static final void startEKYCSimplify(android.app.Activity p22, String p23, vn.ai.trueid.sdk.navigation.TrueIDListener p24)
    {
        vn.ai.trueid.sdk.navigation.TrueIDV3.info(p22);
        Class v2_16 = vn.ai.trueid.sdk.navigation.TrueIDV3.checkSecurityDevice();
        if (v2_16 == 1) {
            if (!vn.ai.trueid.sdk.navigation.TrueIDV3.p) {
                vn.ai.trueid.sdk.utils.TrueIDCaptureMetadata.INSTANCE.setFlow("EKYC");
                if (vn.ai.trueid.sdk.navigation.TrueIDV3.e != null) {
                    if (vn.ai.trueid.sdk.navigation.TrueIDV3.k == null) {
                        if (!vn.ai.trueid.sdk.navigation.TrueIDV3.t) {
                            p22.runOnUiThread(new CS.F(p22, 3));
                            return;
                        } else {
                            Class v2_15 = vn.ai.trueid.sdk.navigation.TrueIDV3.INSTANCE;
                            v2_15.nfcRequire$trueid_release();
                            String v3_10 = vn.ai.trueid.sdk.utils.b.a();
                            boolean vtmp10 = vn.ai.trueid.sdk.utils.b.c(0, p22, 0, 8);
                            if (v3_10 != null) {
                                if (vtmp10) {
                                    vn.ai.trueid.sdk.navigation.TrueIDV3.d.put("stepVerification", new org.json.JSONArray(((java.util.Collection) kotlin.collections.E.listOf(new String[] {"SCANNFC", "SELFIE"})))));
                                    String v3_15 = p22.getSharedPreferences("vendor_prefs", 0).getString("vendor_id", 0);
                                    if (v3_15 == null) {
                                        v3_15 = "TRUEID";
                                    }
                                    String v3_17;
                                    if (!kotlin.jvm.internal.Intrinsics.areEqual(v3_15, "hlb")) {
                                        if (!kotlin.jvm.internal.Intrinsics.areEqual(v3_15, "moonbase")) {
                                            v3_17 = "\n                                    {\n                                      \"primaryColor\":\"#26B840\",\n                                      \"secondaryColor\":\"#42526E\",\n                                      \"titleColor\":\"#253858\",\n                                      \"errorColor\":\"#FF6234\",\n                                      \"textColor\":\"#253858\",\n                                      \"borderInputColor\":\"#D3D9E0\",\n                                      \"backgroundColor\":\"#FFFFFF\",\n                                      \"closeColor\":\"#000000\",\n                                      \"stepBarColor\":\"#26B840\",\n                                      \"stepBarBackgroundColor\":\"#E4F4FF\",\n                                      \"fontName\":\"Lato Italic\",\n                                      \"borderRadius\":8,\n                                      \"maxRetry\":1000,\n                                      \"defaultSpacing\":20,\n                                      \"imageConfirmation\":true,\n                                      \"trueidWatermark\":true,\n                                      \"isDisableSecurity\":false,\n                                      \"isShowStepBar\":false,\n                                      \"isShowSkipNFC\":false,\n                                      \"returnNFC\":true,\n                                      \"nfcSkipMaxRetry\":true,\n                                      \"ovalColor\": \"#26B840\"\n                                    }\n                                    ";
                                        } else {
                                            v3_17 = "\n                                    {\n                                      \"primaryColor\":  \"#2BAA50\",\n                                      \"secondaryColor\":\"#2BAA50\",\n                                      \"titleColor\":    \"#FFFFFF\",\n                                      \"errorColor\":    \"#EF4444\",\n                                      \"textColor\":     \"#FFFFFF\",\n                                      \"borderInputColor\":\"#2BAA50\",\n                                      \"backgroundColor\":\"#000000\",\n                                      \"closeColor\":    \"#FFFFFF\",\n                                      \"stepBarColor\":  \"#26B840\",\n                                      \"stepBarBackgroundColor\":\"#1A1A1A\",\n                                      \"borderRadius\":  16,\n                                      \"ovalColor\":     \"#2BAA50\",\n                                      \"animationOvalColor\":\"#2BAA50\",\n                                      \"successColor\":  \"#26B840\",\n                                      \"trueidWatermark\":false,\n                                      \"isShowStepBar\": false,\n                                      \"isAutoProcess\": true,\n                                      \"overlayAlpha\":  0.9\n                                    }\n                                    ";
                                        }
                                    } else {
                                        v3_17 = "{\n  \"primaryColor\": \"#000000\",\n  \"titleColor\": \"#00CCC6\",\n  \"textButtonColor\": \"#FFFFFF\",\n  \"errorColor\": \"#FF6234\",\n  \"textColor\": \"#091E42\",\n  \"borderInputColor\": \"#00CCC6\",\n  \"backgroundColor\": \"#FFFFFF\",\n  \"closeColor\": \"#253858\",\n  \"borderRadius\": 16,\n  \"maxRetry\": 4,\n  \"defaultSpacing\": 26,\n  \"imageConfirmation\": false,\n  \"trueidWatermark\": true,\n  \"isDisableSecurity\": false,\n  \"isShowStepBar\": false,\n  \"isShowSkipNFC\": false,\n  \"returnNFC\": true,\n  \"nfcSkipMaxRetry\": true\n}\n";
                                    }
                                    vn.ai.trueid.sdk.navigation.TrueIDV3.d = v2_15.importColor(v3_17, vn.ai.trueid.sdk.navigation.TrueIDV3.d, 0);
                                    vn.ai.trueid.sdk.utils.Helpers.Companion.setContext(p22);
                                    v2_15.clearListener();
                                    vn.ai.trueid.sdk.navigation.TrueIDV3.k = p24;
                                    fk.a.a();
                                    fk.b.f = 5;
                                    fk.b.d = new vn.ai.trueid.sdk.models.Person(p23, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 131070, 0);
                                    vn.ai.trueid.sdk.networks.q.g();
                                    p22.startActivity(new android.content.Intent(p22, vn.ai.trueid.sdk.activity.TrueIDActivity));
                                    return;
                                } else {
                                    Class v2_1 = vn.ai.trueid.sdk.models.TrueError.Companion;
                                    p24.completion(new vn.ai.trueid.sdk.models.VerificationResult(v2_1.getNFC_NOT_ENABLED().getCode(), v2_1.getNFC_NOT_ENABLED().getMessage()));
                                    return;
                                }
                            } else {
                                Class v2_5 = vn.ai.trueid.sdk.models.TrueError.Companion;
                                p24.completion(new vn.ai.trueid.sdk.models.VerificationResult(v2_5.getNFC_NOT_AVAILABLE().getCode(), v2_5.getNFC_NOT_AVAILABLE().getMessage()));
                                return;
                            }
                        }
                    } else {
                        return;
                    }
                } else {
                    Class v2_8 = vn.ai.trueid.sdk.models.TrueError.Companion;
                    p24.completion(new vn.ai.trueid.sdk.models.VerificationResult(v2_8.getERROR_CONFIG_SDK().getCode(), v2_8.getERROR_CONFIG_SDK().getMessage()));
                    return;
                }
            } else {
                p24.completion(new vn.ai.trueid.sdk.models.VerificationResult(306, "SDK is busy, please wait for the current flow to finish"));
                return;
            }
        } else {
            p24.completion(new vn.ai.trueid.sdk.models.VerificationResult(v2_16, vn.ai.trueid.sdk.navigation.TrueIDV3.INSTANCE.securityMessage(v2_16)));
            return;
        }
    }

    public static final void startEnrollSelfie(android.app.Activity p13, vn.ai.trueid.sdk.models.FaceEnrollInput p14, vn.ai.trueid.sdk.navigation.FaceEnrollListener p15)
    {
        int v1_0 = vn.ai.trueid.sdk.navigation.TrueIDV3.checkSecurityDevice();
        String v0_9 = 1;
        if (v1_0 == 1) {
            if (!vn.ai.trueid.sdk.navigation.TrueIDV3.p) {
                vn.ai.trueid.sdk.utils.TrueIDCaptureMetadata.INSTANCE.setFlow("ENROLL");
                int v1_23 = vn.ai.trueid.sdk.navigation.TrueIDV3.f;
                if (v1_23 != 0) {
                    String v2_0 = vn.ai.trueid.sdk.navigation.TrueIDV3.INSTANCE;
                    v2_0.getClass();
                    if (vn.ai.trueid.sdk.navigation.TrueIDV3.i(v1_23)) {
                        if (kotlin.text.StringsKt.trim(p14.getPersonID()).toString().length() != 0) {
                            vn.ai.trueid.sdk.navigation.TrueIDV3.d = new org.json.JSONObject("{ \"stepVerification\":[\"SELFIE\"]}");
                            v2_0.clearListener();
                            vn.ai.trueid.sdk.navigation.TrueIDV3.r = p15;
                            fk.a.a();
                            fk.b.a = p14;
                            fk.b.f = 5;
                            String v14_9 = vn.ai.trueid.sdk.navigation.TrueIDV3.f;
                            if (v14_9 != null) {
                                v0_9 = v14_9.isShowLivenessSuccess();
                            }
                            wk.a.i = v0_9;
                            vn.ai.trueid.sdk.networks.B.d();
                            String v14_12 = p13.getSharedPreferences("vendor_prefs", 0).getString("vendor_id", 0);
                            if (v14_12 == null) {
                                v14_12 = "TRUEID";
                            }
                            String v14_14;
                            if (!kotlin.jvm.internal.Intrinsics.areEqual(v14_12, "hlb")) {
                                if (!kotlin.jvm.internal.Intrinsics.areEqual(v14_12, "moonbase")) {
                                    v14_14 = "\n                                    {\n                                      \"primaryColor\":\"#26B840\",\n                                      \"secondaryColor\":\"#42526E\",\n                                      \"titleColor\":\"#253858\",\n                                      \"errorColor\":\"#FF6234\",\n                                      \"textColor\":\"#253858\",\n                                      \"borderInputColor\":\"#D3D9E0\",\n                                      \"backgroundColor\":\"#FFFFFF\",\n                                      \"closeColor\":\"#000000\",\n                                      \"stepBarColor\":\"#26B840\",\n                                      \"stepBarBackgroundColor\":\"#E4F4FF\",\n                                      \"fontName\":\"Lato Italic\",\n                                      \"borderRadius\":8,\n                                      \"maxRetry\":1000,\n                                      \"defaultSpacing\":20,\n                                      \"imageConfirmation\":true,\n                                      \"trueidWatermark\":true,\n                                      \"isDisableSecurity\":false,\n                                      \"isShowStepBar\":false,\n                                      \"isShowSkipNFC\":false,\n                                      \"returnNFC\":true,\n                                      \"nfcSkipMaxRetry\":true,\n                                      \"ovalColor\": \"#26B840\"\n                                    }\n                                    ";
                                } else {
                                    v14_14 = "\n                                    {\n                                      \"primaryColor\":  \"#2BAA50\",\n                                      \"secondaryColor\":\"#2BAA50\",\n                                      \"titleColor\":    \"#FFFFFF\",\n                                      \"errorColor\":    \"#EF4444\",\n                                      \"textColor\":     \"#FFFFFF\",\n                                      \"borderInputColor\":\"#2BAA50\",\n                                      \"backgroundColor\":\"#000000\",\n                                      \"closeColor\":    \"#FFFFFF\",\n                                      \"stepBarColor\":  \"#26B840\",\n                                      \"stepBarBackgroundColor\":\"#1A1A1A\",\n                                      \"borderRadius\":  16,\n                                      \"ovalColor\":     \"#2BAA50\",\n                                      \"animationOvalColor\":\"#2BAA50\",\n                                      \"successColor\":  \"#26B840\",\n                                      \"trueidWatermark\":false,\n                                      \"isShowStepBar\": false,\n                                      \"isAutoProcess\": true,\n                                      \"overlayAlpha\":  0.9\n                                    }\n                                    ";
                                }
                            } else {
                                v14_14 = "{\n  \"primaryColor\": \"#000000\",\n  \"titleColor\": \"#00CCC6\",\n  \"textButtonColor\": \"#FFFFFF\",\n  \"errorColor\": \"#FF6234\",\n  \"textColor\": \"#091E42\",\n  \"borderInputColor\": \"#00CCC6\",\n  \"backgroundColor\": \"#FFFFFF\",\n  \"closeColor\": \"#253858\",\n  \"borderRadius\": 16,\n  \"maxRetry\": 4,\n  \"defaultSpacing\": 26,\n  \"imageConfirmation\": false,\n  \"trueidWatermark\": true,\n  \"isDisableSecurity\": false,\n  \"isShowStepBar\": false,\n  \"isShowSkipNFC\": false,\n  \"returnNFC\": true,\n  \"nfcSkipMaxRetry\": true\n}\n";
                            }
                            boolean v3_6 = v14_14;
                            String v14_15 = vn.ai.trueid.sdk.navigation.TrueIDV3.f;
                            if (v14_15 != null) {
                                org.json.JSONObject v15_15 = v14_15.getPrimaryColor();
                                if (v15_15 != null) {
                                    vn.ai.trueid.sdk.navigation.TrueIDV3.d.put("primaryColor", v15_15);
                                }
                                org.json.JSONObject v15_16 = v14_15.getSecondaryColor();
                                if (v15_16 != null) {
                                    vn.ai.trueid.sdk.navigation.TrueIDV3.d.put("secondaryColor", v15_16);
                                }
                                org.json.JSONObject v15_17 = v14_15.getErrorColor();
                                if (v15_17 != null) {
                                    vn.ai.trueid.sdk.navigation.TrueIDV3.d.put("errorColor", v15_17);
                                }
                                org.json.JSONObject v15_18 = v14_15.getTextColor();
                                if (v15_18 != null) {
                                    vn.ai.trueid.sdk.navigation.TrueIDV3.d.put("textColor", v15_18);
                                }
                                org.json.JSONObject v15_19 = v14_15.getBackgroundColor();
                                if (v15_19 != null) {
                                    vn.ai.trueid.sdk.navigation.TrueIDV3.d.put("backgroundColor", v15_19);
                                }
                                org.json.JSONObject v15_20 = v14_15.getCloseColor();
                                if (v15_20 != null) {
                                    vn.ai.trueid.sdk.navigation.TrueIDV3.d.put("closeColor", v15_20);
                                }
                                vn.ai.trueid.sdk.navigation.TrueIDV3.d.put("maxRetry", v14_15.getMaxRetry());
                                vn.ai.trueid.sdk.navigation.TrueIDV3.d.put("isAutoProcess", v14_15.isAutoProcess());
                                vn.ai.trueid.sdk.navigation.TrueIDV3.d.put("isShowCloseButton", v14_15.isShowCloseButton());
                                vn.ai.trueid.sdk.navigation.TrueIDV3.d.put("isHideQRScan", v14_15.isHideQRScan());
                                String v0_5 = v14_15.getOvalColor();
                                String v4_0 = "";
                                if (v0_5.length() == 0) {
                                    v0_5 = v14_15.getPrimaryColor();
                                    if (v0_5 == null) {
                                        v0_5 = "";
                                    }
                                }
                                vn.ai.trueid.sdk.navigation.TrueIDV3.d.put("ovalColor", v0_5);
                                String v0_6 = v14_15.getAnimationOvalColor();
                                if (v0_6.length() == 0) {
                                    String v14_1 = v14_15.getPrimaryColor();
                                    if (v14_1 != null) {
                                        v4_0 = v14_1;
                                    }
                                    v0_6 = v4_0;
                                }
                                vn.ai.trueid.sdk.navigation.TrueIDV3.d.put("animationOvalColor", v0_6);
                            }
                            String v14_3 = vn.ai.trueid.sdk.navigation.TrueIDV3.importColor$default(v2_0, v3_6, vn.ai.trueid.sdk.navigation.TrueIDV3.d, 0, 4, 0);
                            vn.ai.trueid.sdk.navigation.TrueIDV3.d = v14_3;
                            org.json.JSONObject v15_7 = vn.ai.trueid.sdk.navigation.TrueIDV3.f;
                            if (v15_7 != null) {
                                v14_3.put("trueidWatermark", v15_7.getTrueidWatermark());
                            }
                            p13.startActivity(new android.content.Intent(p13, vn.ai.trueid.sdk.activity.TrueIDActivity));
                            return;
                        } else {
                            vn.ai.trueid.sdk.models.TrueError v13_1 = vn.ai.trueid.sdk.models.TrueError.Companion;
                            p15.onComplete(new vn.ai.trueid.sdk.models.FaceEnrollResult(v13_1.getERROR_CONFIG_SDK().getCode(), v13_1.getERROR_CONFIG_SDK().getMessage(), 0, 0, 0, 0, 0, 124, 0));
                            return;
                        }
                    }
                }
                vn.ai.trueid.sdk.models.TrueError v13_3 = vn.ai.trueid.sdk.models.TrueError.Companion;
                p15.onComplete(new vn.ai.trueid.sdk.models.FaceEnrollResult(v13_3.getERROR_CONFIG_SDK().getCode(), v13_3.getERROR_CONFIG_SDK().getMessage(), 0, 0, 0, 0, 0, 124, 0));
                return;
            } else {
                p15.onComplete(new vn.ai.trueid.sdk.models.FaceEnrollResult(306, "SDK is busy, please wait for the current flow to finish", 0, 0, 0, 0, 0, 124, 0));
                return;
            }
        } else {
            p15.onComplete(new vn.ai.trueid.sdk.models.FaceEnrollResult(v1_0, vn.ai.trueid.sdk.navigation.TrueIDV3.INSTANCE.securityMessage(v1_0), 0, 0, 0, 0, 0, 124, 0));
            return;
        }
    }

    public static final void startFaceAuthen(android.content.Context p9, vn.ai.trueid.sdk.models.FaceAuthenInput p10, vn.ai.trueid.sdk.navigation.AuthenListener p11)
    {
        String v1_0 = vn.ai.trueid.sdk.navigation.TrueIDV3.checkSecurityDevice();
        if (v1_0 == 1) {
            if (!vn.ai.trueid.sdk.navigation.TrueIDV3.p) {
                vn.ai.trueid.sdk.utils.TrueIDCaptureMetadata.INSTANCE.setFlow("AUTHEN");
                vn.ai.trueid.sdk.navigation.AuthenticationID.startFaceAuthen(p9, p10, p11);
                return;
            } else {
                p11.onComplete(new vn.ai.trueid.sdk.models.FaceAuthenResult(306, "SDK is busy, please wait for the current flow to finish", 0, 0, 0, 28, 0));
                return;
            }
        } else {
            p11.onComplete(new vn.ai.trueid.sdk.models.FaceAuthenResult(v1_0, vn.ai.trueid.sdk.navigation.TrueIDV3.INSTANCE.securityMessage(v1_0), 0, 0, 0, 28, 0));
            return;
        }
    }

    public static final void startFaceAuthenCapture(android.app.Activity p8, vn.ai.trueid.sdk.navigation.TrueIDLivenessCaptureInput p9, vn.ai.trueid.sdk.navigation.TrueIDLivenessCaptureListener p10)
    {
        int v1 = vn.ai.trueid.sdk.navigation.TrueIDV3.checkSecurityDevice();
        if (v1 == 1) {
            vn.ai.trueid.sdk.navigation.FaceLiveness.startFaceLivenessCapture(p8, p9, p10, 0);
            return;
        } else {
            p10.onComplete(new vn.ai.trueid.sdk.models.FaceLivenessResult(v1, vn.ai.trueid.sdk.navigation.TrueIDV3.INSTANCE.securityMessage(v1), 0, 0, 0, 28, 0));
            return;
        }
    }

    public static synthetic void startFaceAuthenCapture$default(android.app.Activity p0, vn.ai.trueid.sdk.navigation.TrueIDLivenessCaptureInput p1, vn.ai.trueid.sdk.navigation.TrueIDLivenessCaptureListener p2, int p3, Object p4)
    {
        if ((p3 & 2) != 0) {
            p1 = new vn.ai.trueid.sdk.navigation.TrueIDLivenessCaptureInput(0, 0, 3, 0);
        }
        vn.ai.trueid.sdk.navigation.TrueIDV3.startFaceAuthenCapture(p0, p1, p2);
        return;
    }

    public static final void startFaceEnroll(android.app.Activity p22, vn.ai.trueid.sdk.models.FaceEnrollInput p23, vn.ai.trueid.sdk.navigation.FaceEnrollListener p24)
    {
        String v3_18 = vn.ai.trueid.sdk.navigation.TrueIDV3.checkSecurityDevice();
        org.json.JSONObject v2_0 = 1;
        if (v3_18 == 1) {
            if (!vn.ai.trueid.sdk.navigation.TrueIDV3.p) {
                vn.ai.trueid.sdk.utils.TrueIDCaptureMetadata.INSTANCE.setFlow("ENROLL");
                String v3_0 = vn.ai.trueid.sdk.navigation.TrueIDV3.f;
                if (v3_0 != null) {
                    vn.ai.trueid.sdk.navigation.TrueIDV3.INSTANCE.getClass();
                    if (vn.ai.trueid.sdk.navigation.TrueIDV3.i(v3_0)) {
                        vn.ai.trueid.sdk.navigation.TrueIDV3.k(p23);
                        if (vn.ai.trueid.sdk.utils.b.a()) {
                            if (vn.ai.trueid.sdk.utils.b.c(0, p22, 0, 8)) {
                                vn.ai.trueid.sdk.navigation.TrueIDV3.d = new org.json.JSONObject("{ \"stepVerification\":[\"SCANMRZ\",\"SCANNFC\",\"SCANQR\",\"SELFIE\"]}");
                                if ((p23.getIdNumber().length() > 0) && (p23.getIdNumber().length() == 12)) {
                                    String v3_29 = p23.getIdNumber();
                                    int v4_18 = 0;
                                    while (v4_18 < v3_29.length()) {
                                        if (Character.isDigit(v3_29.charAt(v4_18))) {
                                            v4_18++;
                                        }
                                    }
                                    vn.ai.trueid.sdk.navigation.TrueIDV3.d = new org.json.JSONObject("{ \"stepVerification\":[\"SCANNFC\",\"SCANQR\",\"SELFIE\"]}");
                                }
                                int v7_11 = vn.ai.trueid.sdk.navigation.TrueIDV3.INSTANCE;
                                v7_11.clearListener();
                                vn.ai.trueid.sdk.navigation.TrueIDV3.r = p24;
                                fk.a.a();
                                fk.b.a = p23;
                                fk.b.f = 5;
                                String v1_19 = vn.ai.trueid.sdk.navigation.TrueIDV3.f;
                                if (v1_19 != null) {
                                    v2_0 = v1_19.isShowLivenessSuccess();
                                }
                                wk.a.i = v2_0;
                                vn.ai.trueid.sdk.networks.B.d();
                                String v1_3 = p22.getSharedPreferences("vendor_prefs", 0).getString("vendor_id", 0);
                                if (v1_3 == null) {
                                    v1_3 = "TRUEID";
                                }
                                String v1_5;
                                if (!kotlin.jvm.internal.Intrinsics.areEqual(v1_3, "hlb")) {
                                    if (!kotlin.jvm.internal.Intrinsics.areEqual(v1_3, "moonbase")) {
                                        v1_5 = "\n                                    {\n                                      \"primaryColor\":\"#26B840\",\n                                      \"secondaryColor\":\"#42526E\",\n                                      \"titleColor\":\"#253858\",\n                                      \"errorColor\":\"#FF6234\",\n                                      \"textColor\":\"#253858\",\n                                      \"borderInputColor\":\"#D3D9E0\",\n                                      \"backgroundColor\":\"#FFFFFF\",\n                                      \"closeColor\":\"#000000\",\n                                      \"stepBarColor\":\"#26B840\",\n                                      \"stepBarBackgroundColor\":\"#E4F4FF\",\n                                      \"fontName\":\"Lato Italic\",\n                                      \"borderRadius\":8,\n                                      \"maxRetry\":1000,\n                                      \"defaultSpacing\":20,\n                                      \"imageConfirmation\":true,\n                                      \"trueidWatermark\":true,\n                                      \"isDisableSecurity\":false,\n                                      \"isShowStepBar\":false,\n                                      \"isShowSkipNFC\":false,\n                                      \"returnNFC\":true,\n                                      \"nfcSkipMaxRetry\":true,\n                                      \"ovalColor\": \"#26B840\"\n                                    }\n                                    ";
                                    } else {
                                        v1_5 = "\n                                    {\n                                      \"primaryColor\":  \"#2BAA50\",\n                                      \"secondaryColor\":\"#2BAA50\",\n                                      \"titleColor\":    \"#FFFFFF\",\n                                      \"errorColor\":    \"#EF4444\",\n                                      \"textColor\":     \"#FFFFFF\",\n                                      \"borderInputColor\":\"#2BAA50\",\n                                      \"backgroundColor\":\"#000000\",\n                                      \"closeColor\":    \"#FFFFFF\",\n                                      \"stepBarColor\":  \"#26B840\",\n                                      \"stepBarBackgroundColor\":\"#1A1A1A\",\n                                      \"borderRadius\":  16,\n                                      \"ovalColor\":     \"#2BAA50\",\n                                      \"animationOvalColor\":\"#2BAA50\",\n                                      \"successColor\":  \"#26B840\",\n                                      \"trueidWatermark\":false,\n                                      \"isShowStepBar\": false,\n                                      \"isAutoProcess\": true,\n                                      \"overlayAlpha\":  0.9\n                                    }\n                                    ";
                                    }
                                } else {
                                    v1_5 = "{\n  \"primaryColor\": \"#000000\",\n  \"titleColor\": \"#00CCC6\",\n  \"textButtonColor\": \"#FFFFFF\",\n  \"errorColor\": \"#FF6234\",\n  \"textColor\": \"#091E42\",\n  \"borderInputColor\": \"#00CCC6\",\n  \"backgroundColor\": \"#FFFFFF\",\n  \"closeColor\": \"#253858\",\n  \"borderRadius\": 16,\n  \"maxRetry\": 4,\n  \"defaultSpacing\": 26,\n  \"imageConfirmation\": false,\n  \"trueidWatermark\": true,\n  \"isDisableSecurity\": false,\n  \"isShowStepBar\": false,\n  \"isShowSkipNFC\": false,\n  \"returnNFC\": true,\n  \"nfcSkipMaxRetry\": true\n}\n";
                                }
                                int v8_0 = v1_5;
                                String v1_6 = vn.ai.trueid.sdk.navigation.TrueIDV3.f;
                                if (v1_6 != null) {
                                    org.json.JSONObject v2_5 = v1_6.getPrimaryColor();
                                    if (v2_5 != null) {
                                        vn.ai.trueid.sdk.navigation.TrueIDV3.d.put("primaryColor", v2_5);
                                    }
                                    org.json.JSONObject v2_6 = v1_6.getSecondaryColor();
                                    if (v2_6 != null) {
                                        vn.ai.trueid.sdk.navigation.TrueIDV3.d.put("secondaryColor", v2_6);
                                    }
                                    org.json.JSONObject v2_7 = v1_6.getErrorColor();
                                    if (v2_7 != null) {
                                        vn.ai.trueid.sdk.navigation.TrueIDV3.d.put("errorColor", v2_7);
                                    }
                                    org.json.JSONObject v2_8 = v1_6.getTextColor();
                                    if (v2_8 != null) {
                                        vn.ai.trueid.sdk.navigation.TrueIDV3.d.put("textColor", v2_8);
                                    }
                                    org.json.JSONObject v2_9 = v1_6.getBackgroundColor();
                                    if (v2_9 != null) {
                                        vn.ai.trueid.sdk.navigation.TrueIDV3.d.put("backgroundColor", v2_9);
                                    }
                                    org.json.JSONObject v2_10 = v1_6.getCloseColor();
                                    if (v2_10 != null) {
                                        vn.ai.trueid.sdk.navigation.TrueIDV3.d.put("closeColor", v2_10);
                                    }
                                    org.json.JSONObject v2_11 = v1_6.getTextButtonColor();
                                    if (v2_11 != null) {
                                        vn.ai.trueid.sdk.navigation.TrueIDV3.d.put("textButtonColor", v2_11);
                                    }
                                    vn.ai.trueid.sdk.navigation.TrueIDV3.d.put("isAutoProcess", v1_6.isAutoProcess());
                                    vn.ai.trueid.sdk.navigation.TrueIDV3.d.put("isShowCloseButton", v1_6.isShowCloseButton());
                                    vn.ai.trueid.sdk.navigation.TrueIDV3.d.put("isHideQRScan", v1_6.isHideQRScan());
                                    String v3_12 = v1_6.getOvalColor();
                                    String v5_1 = "";
                                    if (v3_12.length() == 0) {
                                        v3_12 = v1_6.getPrimaryColor();
                                        if (v3_12 == null) {
                                            v3_12 = "";
                                        }
                                    }
                                    vn.ai.trueid.sdk.navigation.TrueIDV3.d.put("ovalColor", v3_12);
                                    String v3_13 = v1_6.getAnimationOvalColor();
                                    if (v3_13.length() == 0) {
                                        String v1_7 = v1_6.getPrimaryColor();
                                        if (v1_7 != null) {
                                            v5_1 = v1_7;
                                        }
                                        v3_13 = v5_1;
                                    }
                                    vn.ai.trueid.sdk.navigation.TrueIDV3.d.put("animationOvalColor", v3_13);
                                }
                                String v1_9 = vn.ai.trueid.sdk.navigation.TrueIDV3.importColor$default(v7_11, v8_0, vn.ai.trueid.sdk.navigation.TrueIDV3.d, 0, 4, 0);
                                vn.ai.trueid.sdk.navigation.TrueIDV3.d = v1_9;
                                org.json.JSONObject v2_17 = vn.ai.trueid.sdk.navigation.TrueIDV3.f;
                                if (v2_17 != null) {
                                    v1_9.put("maxRetry", v2_17.getMaxRetry());
                                }
                                String v1_10 = vn.ai.trueid.sdk.navigation.TrueIDV3.f;
                                if (v1_10 != null) {
                                    String v1_11 = v1_10.getFontName();
                                    if (v1_11 != null) {
                                        vn.ai.trueid.sdk.navigation.TrueIDV3.d.put("fontName", v1_11);
                                    }
                                }
                                String v1_12 = vn.ai.trueid.sdk.navigation.TrueIDV3.f;
                                if (v1_12 != null) {
                                    vn.ai.trueid.sdk.navigation.TrueIDV3.d.put("trueidWatermark", v1_12.getTrueidWatermark());
                                }
                                if (p23.getIdNumber().length() > 0) {
                                    fk.b.d = new vn.ai.trueid.sdk.models.Person(p23.getIdNumber(), 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 131070, 0);
                                }
                                p22.startActivity(new android.content.Intent(p22, vn.ai.trueid.sdk.activity.TrueIDActivity));
                                return;
                            } else {
                                vn.ai.trueid.sdk.models.TrueError v0_5 = vn.ai.trueid.sdk.models.TrueError.Companion;
                                p24.onComplete(new vn.ai.trueid.sdk.models.FaceEnrollResult(v0_5.getNFC_NOT_ENABLED().getCode(), v0_5.getNFC_NOT_ENABLED().getMessage(), 0, 0, 0, 0, 0, 124, 0));
                                return;
                            }
                        } else {
                            vn.ai.trueid.sdk.models.TrueError v0_7 = vn.ai.trueid.sdk.models.TrueError.Companion;
                            p24.onComplete(new vn.ai.trueid.sdk.models.FaceEnrollResult(v0_7.getNFC_NOT_AVAILABLE().getCode(), v0_7.getNFC_NOT_AVAILABLE().getMessage(), 0, 0, 0, 0, 0, 124, 0));
                            return;
                        }
                    }
                }
                if ((p23.getIdNumber().length() == 0) || (p23.getIdNumber().length() != 12)) {
                    vn.ai.trueid.sdk.models.TrueError v0_9 = vn.ai.trueid.sdk.models.TrueError.Companion;
                    p24.onComplete(new vn.ai.trueid.sdk.models.FaceEnrollResult(v0_9.getERROR_CONFIG_SDK().getCode(), v0_9.getERROR_CONFIG_SDK().getMessage(), 0, 0, 0, 0, 0, 124, 0));
                }
                return;
            } else {
                p24.onComplete(new vn.ai.trueid.sdk.models.FaceEnrollResult(306, "SDK is busy, please wait for the current flow to finish", 0, 0, 0, 0, 0, 124, 0));
                return;
            }
        } else {
            p24.onComplete(new vn.ai.trueid.sdk.models.FaceEnrollResult(v3_18, vn.ai.trueid.sdk.navigation.TrueIDV3.INSTANCE.securityMessage(v3_18), 0, 0, 0, 0, 0, 124, 0));
            return;
        }
    }

    public static final void startFaceEnrollCapture(android.app.Activity p37, vn.ai.trueid.sdk.models.FaceEnrollInput p38, vn.ai.trueid.sdk.navigation.TrueIDFaceEnrollCaptureListener p39)
    {
        org.json.JSONObject v3_25 = vn.ai.trueid.sdk.navigation.TrueIDV3.checkSecurityDevice();
        Class v2_1 = 1;
        if (v3_25 == 1) {
            if (!vn.ai.trueid.sdk.navigation.TrueIDV3.p) {
                vn.ai.trueid.sdk.utils.TrueIDCaptureMetadata.INSTANCE.setFlow("ENROLL");
                if (vn.ai.trueid.sdk.navigation.TrueIDV3.f == null) {
                    vn.ai.trueid.sdk.models.TrueError v0_1 = vn.ai.trueid.sdk.models.TrueError.Companion;
                    p39.onComplete(new vn.ai.trueid.sdk.models.FaceEnrollResult(v0_1.getERROR_CONFIG_SDK().getCode(), v0_1.getERROR_CONFIG_SDK().getMessage(), 0, 0, 0, 0, 0, 124, 0));
                    return;
                } else {
                    vn.ai.trueid.sdk.navigation.TrueIDV3.INSTANCE.getClass();
                    vn.ai.trueid.sdk.navigation.TrueIDV3.k(p38);
                    if (vn.ai.trueid.sdk.utils.b.a()) {
                        if (vn.ai.trueid.sdk.utils.b.c(0, p37, 0, 8)) {
                            vn.ai.trueid.sdk.navigation.TrueIDV3.f = vn.ai.trueid.sdk.models.FaceEnrollConfig.copy$default(vn.ai.trueid.sdk.navigation.TrueIDV3.f, "", 0, 0, "", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 134217718, 0);
                            vn.ai.trueid.sdk.navigation.TrueIDV3.d = new org.json.JSONObject("{ \"stepVerification\":[\"SCANMRZ\",\"SCANNFC\",\"SCANQR\",\"SELFIE\"]}");
                            if ((p38.getIdNumber().length() > 0) && (p38.getIdNumber().length() == 12)) {
                                org.json.JSONObject v3_40 = p38.getIdNumber();
                                String v6_1 = 0;
                                while (v6_1 < v3_40.length()) {
                                    if (Character.isDigit(v3_40.charAt(v6_1))) {
                                        v6_1++;
                                    }
                                }
                                vn.ai.trueid.sdk.navigation.TrueIDV3.d = new org.json.JSONObject("{ \"stepVerification\":[\"SCANNFC\",\"SCANQR\",\"SELFIE\"]}");
                            }
                            int v7_2 = vn.ai.trueid.sdk.navigation.TrueIDV3.INSTANCE;
                            v7_2.clearListener();
                            vn.ai.trueid.sdk.navigation.TrueIDV3.n = 1;
                            vn.ai.trueid.sdk.navigation.TrueIDV3.o = p39;
                            fk.a.a();
                            fk.b.a = p38;
                            fk.b.f = 5;
                            String v1_4 = p37.getSharedPreferences("vendor_prefs", 0).getString("vendor_id", 0);
                            if (v1_4 == null) {
                                v1_4 = "TRUEID";
                            }
                            String v1_6;
                            if (!kotlin.jvm.internal.Intrinsics.areEqual(v1_4, "hlb")) {
                                if (!kotlin.jvm.internal.Intrinsics.areEqual(v1_4, "moonbase")) {
                                    v1_6 = "\n                                    {\n                                      \"primaryColor\":\"#26B840\",\n                                      \"secondaryColor\":\"#42526E\",\n                                      \"titleColor\":\"#253858\",\n                                      \"errorColor\":\"#FF6234\",\n                                      \"textColor\":\"#253858\",\n                                      \"borderInputColor\":\"#D3D9E0\",\n                                      \"backgroundColor\":\"#FFFFFF\",\n                                      \"closeColor\":\"#000000\",\n                                      \"stepBarColor\":\"#26B840\",\n                                      \"stepBarBackgroundColor\":\"#E4F4FF\",\n                                      \"fontName\":\"Lato Italic\",\n                                      \"borderRadius\":8,\n                                      \"maxRetry\":1000,\n                                      \"defaultSpacing\":20,\n                                      \"imageConfirmation\":true,\n                                      \"trueidWatermark\":true,\n                                      \"isDisableSecurity\":false,\n                                      \"isShowStepBar\":false,\n                                      \"isShowSkipNFC\":false,\n                                      \"returnNFC\":true,\n                                      \"nfcSkipMaxRetry\":true,\n                                      \"ovalColor\": \"#26B840\"\n                                    }\n                                    ";
                                } else {
                                    v1_6 = "\n                                    {\n                                      \"primaryColor\":  \"#2BAA50\",\n                                      \"secondaryColor\":\"#2BAA50\",\n                                      \"titleColor\":    \"#FFFFFF\",\n                                      \"errorColor\":    \"#EF4444\",\n                                      \"textColor\":     \"#FFFFFF\",\n                                      \"borderInputColor\":\"#2BAA50\",\n                                      \"backgroundColor\":\"#000000\",\n                                      \"closeColor\":    \"#FFFFFF\",\n                                      \"stepBarColor\":  \"#26B840\",\n                                      \"stepBarBackgroundColor\":\"#1A1A1A\",\n                                      \"borderRadius\":  16,\n                                      \"ovalColor\":     \"#2BAA50\",\n                                      \"animationOvalColor\":\"#2BAA50\",\n                                      \"successColor\":  \"#26B840\",\n                                      \"trueidWatermark\":false,\n                                      \"isShowStepBar\": false,\n                                      \"isAutoProcess\": true,\n                                      \"overlayAlpha\":  0.9\n                                    }\n                                    ";
                                }
                            } else {
                                v1_6 = "{\n  \"primaryColor\": \"#000000\",\n  \"titleColor\": \"#00CCC6\",\n  \"textButtonColor\": \"#FFFFFF\",\n  \"errorColor\": \"#FF6234\",\n  \"textColor\": \"#091E42\",\n  \"borderInputColor\": \"#00CCC6\",\n  \"backgroundColor\": \"#FFFFFF\",\n  \"closeColor\": \"#253858\",\n  \"borderRadius\": 16,\n  \"maxRetry\": 4,\n  \"defaultSpacing\": 26,\n  \"imageConfirmation\": false,\n  \"trueidWatermark\": true,\n  \"isDisableSecurity\": false,\n  \"isShowStepBar\": false,\n  \"isShowSkipNFC\": false,\n  \"returnNFC\": true,\n  \"nfcSkipMaxRetry\": true\n}\n";
                            }
                            int v8_0 = v1_6;
                            String v1_7 = vn.ai.trueid.sdk.navigation.TrueIDV3.f;
                            if (v1_7 != null) {
                                org.json.JSONObject v3_7 = v1_7.getPrimaryColor();
                                if (v3_7 != null) {
                                    vn.ai.trueid.sdk.navigation.TrueIDV3.d.put("primaryColor", v3_7);
                                }
                                org.json.JSONObject v3_8 = v1_7.getSecondaryColor();
                                if (v3_8 != null) {
                                    vn.ai.trueid.sdk.navigation.TrueIDV3.d.put("secondaryColor", v3_8);
                                }
                                org.json.JSONObject v3_9 = v1_7.getErrorColor();
                                if (v3_9 != null) {
                                    vn.ai.trueid.sdk.navigation.TrueIDV3.d.put("errorColor", v3_9);
                                }
                                org.json.JSONObject v3_10 = v1_7.getTextColor();
                                if (v3_10 != null) {
                                    vn.ai.trueid.sdk.navigation.TrueIDV3.d.put("textColor", v3_10);
                                }
                                org.json.JSONObject v3_11 = v1_7.getBackgroundColor();
                                if (v3_11 != null) {
                                    vn.ai.trueid.sdk.navigation.TrueIDV3.d.put("backgroundColor", v3_11);
                                }
                                org.json.JSONObject v3_12 = v1_7.getCloseColor();
                                if (v3_12 != null) {
                                    vn.ai.trueid.sdk.navigation.TrueIDV3.d.put("closeColor", v3_12);
                                }
                                org.json.JSONObject v3_13 = v1_7.getTextButtonColor();
                                if (v3_13 != null) {
                                    vn.ai.trueid.sdk.navigation.TrueIDV3.d.put("textButtonColor", v3_13);
                                }
                                vn.ai.trueid.sdk.navigation.TrueIDV3.d.put("isAutoProcess", v1_7.isAutoProcess());
                                vn.ai.trueid.sdk.navigation.TrueIDV3.d.put("isShowCloseButton", v1_7.isShowCloseButton());
                                vn.ai.trueid.sdk.navigation.TrueIDV3.d.put("isHideQRScan", v1_7.isHideQRScan());
                                String v4_11 = v1_7.getOvalColor();
                                String v6_2 = "";
                                if (v4_11.length() == 0) {
                                    v4_11 = v1_7.getPrimaryColor();
                                    if (v4_11 == null) {
                                        v4_11 = "";
                                    }
                                }
                                vn.ai.trueid.sdk.navigation.TrueIDV3.d.put("ovalColor", v4_11);
                                String v4_12 = v1_7.getAnimationOvalColor();
                                if (v4_12.length() == 0) {
                                    String v1_8 = v1_7.getPrimaryColor();
                                    if (v1_8 != null) {
                                        v6_2 = v1_8;
                                    }
                                    v4_12 = v6_2;
                                }
                                vn.ai.trueid.sdk.navigation.TrueIDV3.d.put("animationOvalColor", v4_12);
                            }
                            String v1_10 = vn.ai.trueid.sdk.navigation.TrueIDV3.importColor$default(v7_2, v8_0, vn.ai.trueid.sdk.navigation.TrueIDV3.d, 0, 4, 0);
                            vn.ai.trueid.sdk.navigation.TrueIDV3.d = v1_10;
                            org.json.JSONObject v3_19 = vn.ai.trueid.sdk.navigation.TrueIDV3.f;
                            if (v3_19 != null) {
                                v1_10.put("maxRetry", v3_19.getMaxRetry());
                            }
                            String v1_11 = vn.ai.trueid.sdk.navigation.TrueIDV3.f;
                            if (v1_11 != null) {
                                String v1_12 = v1_11.getFontName();
                                if (v1_12 != null) {
                                    vn.ai.trueid.sdk.navigation.TrueIDV3.d.put("fontName", v1_12);
                                }
                            }
                            String v1_13 = vn.ai.trueid.sdk.navigation.TrueIDV3.f;
                            if (v1_13 != null) {
                                vn.ai.trueid.sdk.navigation.TrueIDV3.d.put("trueidWatermark", v1_13.getTrueidWatermark());
                            }
                            if (p38.getIdNumber().length() > 0) {
                                fk.b.d = new vn.ai.trueid.sdk.models.Person(p38.getIdNumber(), 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 131070, 0);
                            }
                            String v1_17 = vn.ai.trueid.sdk.navigation.TrueIDV3.f;
                            if (v1_17 != null) {
                                v2_1 = v1_17.isShowLivenessSuccess();
                            }
                            wk.a.i = v2_1;
                            vn.ai.trueid.sdk.networks.B.d();
                            p37.startActivity(new android.content.Intent(p37, vn.ai.trueid.sdk.activity.TrueIDActivity));
                            return;
                        } else {
                            vn.ai.trueid.sdk.models.TrueError v0_3 = vn.ai.trueid.sdk.models.TrueError.Companion;
                            p39.onComplete(new vn.ai.trueid.sdk.models.FaceEnrollResult(v0_3.getNFC_NOT_ENABLED().getCode(), v0_3.getNFC_NOT_ENABLED().getMessage(), 0, 0, 0, 0, 0, 124, 0));
                            return;
                        }
                    } else {
                        vn.ai.trueid.sdk.models.TrueError v0_5 = vn.ai.trueid.sdk.models.TrueError.Companion;
                        p39.onComplete(new vn.ai.trueid.sdk.models.FaceEnrollResult(v0_5.getNFC_NOT_AVAILABLE().getCode(), v0_5.getNFC_NOT_AVAILABLE().getMessage(), 0, 0, 0, 0, 0, 124, 0));
                        return;
                    }
                }
            } else {
                p39.onComplete(new vn.ai.trueid.sdk.models.FaceEnrollResult(306, "SDK is busy, please wait for the current flow to finish", 0, 0, 0, 0, 0, 124, 0));
                return;
            }
        } else {
            p39.onComplete(new vn.ai.trueid.sdk.models.FaceEnrollResult(v3_25, vn.ai.trueid.sdk.navigation.TrueIDV3.INSTANCE.securityMessage(v3_25), 0, 0, 0, 0, 0, 124, 0));
            return;
        }
    }

    public static final void startFaceEnrollCapture(android.app.Activity p8, vn.ai.trueid.sdk.navigation.TrueIDLivenessCaptureInput p9, vn.ai.trueid.sdk.navigation.TrueIDLivenessCaptureListener p10)
    {
        int v1 = vn.ai.trueid.sdk.navigation.TrueIDV3.checkSecurityDevice();
        if (v1 == 1) {
            vn.ai.trueid.sdk.navigation.FaceLiveness.startFaceLivenessCapture(p8, p9, p10, 0);
            return;
        } else {
            p10.onComplete(new vn.ai.trueid.sdk.models.FaceLivenessResult(v1, vn.ai.trueid.sdk.navigation.TrueIDV3.INSTANCE.securityMessage(v1), 0, 0, 0, 28, 0));
            return;
        }
    }

    public static synthetic void startFaceEnrollCapture$default(android.app.Activity p0, vn.ai.trueid.sdk.navigation.TrueIDLivenessCaptureInput p1, vn.ai.trueid.sdk.navigation.TrueIDLivenessCaptureListener p2, int p3, Object p4)
    {
        if ((p3 & 2) != 0) {
            p1 = new vn.ai.trueid.sdk.navigation.TrueIDLivenessCaptureInput(0, 0, 3, 0);
        }
        vn.ai.trueid.sdk.navigation.TrueIDV3.startFaceEnrollCapture(p0, p1, p2);
        return;
    }

    public static final void startFaceLiveness(android.app.Activity p9, vn.ai.trueid.sdk.models.FaceLivenessInput p10, vn.ai.trueid.sdk.navigation.FaceLivenessListener p11)
    {
        String v1_0 = vn.ai.trueid.sdk.navigation.TrueIDV3.checkSecurityDevice();
        if (v1_0 == 1) {
            if (!vn.ai.trueid.sdk.navigation.TrueIDV3.p) {
                vn.ai.trueid.sdk.utils.TrueIDCaptureMetadata.INSTANCE.setFlow("LIVENESS");
                vn.ai.trueid.sdk.navigation.FaceLiveness.startFaceLiveness(p9, p10, p11);
                return;
            } else {
                p11.onComplete(new vn.ai.trueid.sdk.models.FaceLivenessResult(306, "SDK is busy, please wait for the current flow to finish", 0, 0, 0, 28, 0));
                return;
            }
        } else {
            p11.onComplete(new vn.ai.trueid.sdk.models.FaceLivenessResult(v1_0, vn.ai.trueid.sdk.navigation.TrueIDV3.INSTANCE.securityMessage(v1_0), 0, 0, 0, 28, 0));
            return;
        }
    }

    public static synthetic void startFaceLiveness$default(android.app.Activity p0, vn.ai.trueid.sdk.models.FaceLivenessInput p1, vn.ai.trueid.sdk.navigation.FaceLivenessListener p2, int p3, Object p4)
    {
        if ((p3 & 2) != 0) {
            p1 = new vn.ai.trueid.sdk.models.FaceLivenessInput(0, 0, 3, 0);
        }
        vn.ai.trueid.sdk.navigation.TrueIDV3.startFaceLiveness(p0, p1, p2);
        return;
    }

    public static final void startLivenessCapture(android.app.Activity p8, vn.ai.trueid.sdk.navigation.TrueIDLivenessCaptureInput p9, vn.ai.trueid.sdk.navigation.TrueIDLivenessCaptureListener p10)
    {
        vn.ai.trueid.sdk.navigation.TrueIDV3 v1_0 = vn.ai.trueid.sdk.navigation.TrueIDV3.checkSecurityDevice();
        if (v1_0 == 1) {
            vn.ai.trueid.sdk.navigation.FaceLiveness.startFaceLivenessCapture$default(p8, p9, p10, 0, 8, 0);
            return;
        } else {
            p10.onComplete(new vn.ai.trueid.sdk.models.FaceLivenessResult(v1_0, vn.ai.trueid.sdk.navigation.TrueIDV3.INSTANCE.securityMessage(v1_0), 0, 0, 0, 28, 0));
            return;
        }
    }

    public static synthetic void startLivenessCapture$default(android.app.Activity p0, vn.ai.trueid.sdk.navigation.TrueIDLivenessCaptureInput p1, vn.ai.trueid.sdk.navigation.TrueIDLivenessCaptureListener p2, int p3, Object p4)
    {
        if ((p3 & 2) != 0) {
            p1 = new vn.ai.trueid.sdk.navigation.TrueIDLivenessCaptureInput(0, 0, 3, 0);
        }
        vn.ai.trueid.sdk.navigation.TrueIDV3.startLivenessCapture(p0, p1, p2);
        return;
    }

    public static final void startNFC(android.app.Activity p24, vn.ai.trueid.sdk.models.NFCInput p25, vn.ai.trueid.sdk.navigation.NFCListener p26)
    {
        androidx.fragment.app.FragmentActivity v3_8 = vn.ai.trueid.sdk.navigation.TrueIDV3.checkSecurityDevice();
        if (v3_8 == 1) {
            if (!vn.ai.trueid.sdk.navigation.TrueIDV3.p) {
                vn.ai.trueid.sdk.utils.TrueIDCaptureMetadata.INSTANCE.setFlow("NFC");
                if (vn.ai.trueid.sdk.navigation.TrueIDV3.d.length() == 0) {
                    vn.ai.trueid.sdk.navigation.TrueIDV3.d = new org.json.JSONObject("{ \"stepVerification\":[\"SCANMRZ\",\"SCANNFC\",\"SCANQR\"]}");
                }
                Ek.n v4_4 = vn.ai.trueid.sdk.navigation.TrueIDV3.INSTANCE;
                vn.ai.trueid.sdk.models.NFCInput v2_24 = p24.getSharedPreferences("vendor_prefs", 0).getString("vendor_id", 0);
                if (v2_24 == null) {
                    v2_24 = "TRUEID";
                }
                vn.ai.trueid.sdk.models.NFCInput v2_26;
                if (!kotlin.jvm.internal.Intrinsics.areEqual(v2_24, "hlb")) {
                    if (!kotlin.jvm.internal.Intrinsics.areEqual(v2_24, "moonbase")) {
                        v2_26 = "\n                                    {\n                                      \"primaryColor\":\"#26B840\",\n                                      \"secondaryColor\":\"#42526E\",\n                                      \"titleColor\":\"#253858\",\n                                      \"errorColor\":\"#FF6234\",\n                                      \"textColor\":\"#253858\",\n                                      \"borderInputColor\":\"#D3D9E0\",\n                                      \"backgroundColor\":\"#FFFFFF\",\n                                      \"closeColor\":\"#000000\",\n                                      \"stepBarColor\":\"#26B840\",\n                                      \"stepBarBackgroundColor\":\"#E4F4FF\",\n                                      \"fontName\":\"Lato Italic\",\n                                      \"borderRadius\":8,\n                                      \"maxRetry\":1000,\n                                      \"defaultSpacing\":20,\n                                      \"imageConfirmation\":true,\n                                      \"trueidWatermark\":true,\n                                      \"isDisableSecurity\":false,\n                                      \"isShowStepBar\":false,\n                                      \"isShowSkipNFC\":false,\n                                      \"returnNFC\":true,\n                                      \"nfcSkipMaxRetry\":true,\n                                      \"ovalColor\": \"#26B840\"\n                                    }\n                                    ";
                    } else {
                        v2_26 = "\n                                    {\n                                      \"primaryColor\":  \"#2BAA50\",\n                                      \"secondaryColor\":\"#2BAA50\",\n                                      \"titleColor\":    \"#FFFFFF\",\n                                      \"errorColor\":    \"#EF4444\",\n                                      \"textColor\":     \"#FFFFFF\",\n                                      \"borderInputColor\":\"#2BAA50\",\n                                      \"backgroundColor\":\"#000000\",\n                                      \"closeColor\":    \"#FFFFFF\",\n                                      \"stepBarColor\":  \"#26B840\",\n                                      \"stepBarBackgroundColor\":\"#1A1A1A\",\n                                      \"borderRadius\":  16,\n                                      \"ovalColor\":     \"#2BAA50\",\n                                      \"animationOvalColor\":\"#2BAA50\",\n                                      \"successColor\":  \"#26B840\",\n                                      \"trueidWatermark\":false,\n                                      \"isShowStepBar\": false,\n                                      \"isAutoProcess\": true,\n                                      \"overlayAlpha\":  0.9\n                                    }\n                                    ";
                    }
                } else {
                    v2_26 = "{\n  \"primaryColor\": \"#000000\",\n  \"titleColor\": \"#00CCC6\",\n  \"textButtonColor\": \"#FFFFFF\",\n  \"errorColor\": \"#FF6234\",\n  \"textColor\": \"#091E42\",\n  \"borderInputColor\": \"#00CCC6\",\n  \"backgroundColor\": \"#FFFFFF\",\n  \"closeColor\": \"#253858\",\n  \"borderRadius\": 16,\n  \"maxRetry\": 4,\n  \"defaultSpacing\": 26,\n  \"imageConfirmation\": false,\n  \"trueidWatermark\": true,\n  \"isDisableSecurity\": false,\n  \"isShowStepBar\": false,\n  \"isShowSkipNFC\": false,\n  \"returnNFC\": true,\n  \"nfcSkipMaxRetry\": true\n}\n";
                }
                vn.ai.trueid.sdk.navigation.TrueIDV3.d = vn.ai.trueid.sdk.navigation.TrueIDV3.importColor$default(v4_4, v2_26, vn.ai.trueid.sdk.navigation.TrueIDV3.d, 0, 4, 0);
                if (vn.ai.trueid.sdk.navigation.TrueIDV3.q == null) {
                    vn.ai.trueid.sdk.models.NFCInput v2_30 = vn.ai.trueid.sdk.navigation.TrueIDV3.g;
                    if (v2_30 != null) {
                        v4_4.getClass();
                        if (vn.ai.trueid.sdk.navigation.TrueIDV3.j(v2_30)) {
                            if (vn.ai.trueid.sdk.utils.b.a()) {
                                if (vn.ai.trueid.sdk.utils.b.c(0, p24, 0, 8)) {
                                    v4_4.clearListener();
                                    vn.ai.trueid.sdk.navigation.TrueIDV3.q = p26;
                                    fk.a.a();
                                    fk.b.f = 5;
                                    if (p25.getIdNumber().length() == 12) {
                                        fk.b.d = new vn.ai.trueid.sdk.models.Person(p25.getIdNumber(), 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 131070, 0);
                                    }
                                    if (!vn.ai.trueid.sdk.navigation.TrueIDV3.b(vn.ai.trueid.sdk.navigation.TrueIDV3.d)) {
                                        p24.startActivity(new android.content.Intent(p24, vn.ai.trueid.sdk.activity.TrueIDActivity));
                                        return;
                                    } else {
                                        if ((p25.getIdNumber().length() == 12) || (!vn.ai.trueid.sdk.navigation.TrueIDV3.b(vn.ai.trueid.sdk.navigation.TrueIDV3.d))) {
                                            if (!(p24 instanceof androidx.fragment.app.FragmentActivity)) {
                                                return;
                                            } else {
                                                m9.D2.b(p24);
                                                Ek.n v4_2 = new Ek.n();
                                                v4_2.P = new Kk.y(p26, v4_2);
                                                v4_2.O = Kk.r.i;
                                                v4_2.v1(((androidx.fragment.app.FragmentActivity) p24).getSupportFragmentManager(), "BottomDialogNFC");
                                                new android.os.Handler(android.os.Looper.getMainLooper()).postDelayed(new F.k0(p26, p25, ((androidx.fragment.app.FragmentActivity) p24), v4_2, 3), 500);
                                                return;
                                            }
                                        } else {
                                            F.k0 v0_5 = vn.ai.trueid.sdk.models.TrueError.Companion;
                                            p26.onComplete(new vn.ai.trueid.sdk.models.NfcResult(v0_5.getID_NUMBER_TOO_SHORT().getCode(), v0_5.getID_NUMBER_TOO_SHORT().getMessage(), 0, 0, 0, 0, 0, 124, 0));
                                            return;
                                        }
                                    }
                                } else {
                                    F.k0 v0_7 = vn.ai.trueid.sdk.models.TrueError.Companion;
                                    p26.onComplete(new vn.ai.trueid.sdk.models.NfcResult(v0_7.getNFC_NOT_ENABLED().getCode(), v0_7.getNFC_NOT_ENABLED().getMessage(), 0, 0, 0, 0, 0, 124, 0));
                                    return;
                                }
                            } else {
                                F.k0 v0_9 = vn.ai.trueid.sdk.models.TrueError.Companion;
                                p26.onComplete(new vn.ai.trueid.sdk.models.NfcResult(v0_9.getNFC_NOT_AVAILABLE().getCode(), v0_9.getNFC_NOT_AVAILABLE().getMessage(), 0, 0, 0, 0, 0, 124, 0));
                                return;
                            }
                        }
                    }
                    F.k0 v0_12 = vn.ai.trueid.sdk.models.TrueError.Companion;
                    p26.onComplete(new vn.ai.trueid.sdk.models.NfcResult(v0_12.getERROR_CONFIG_SDK().getCode(), v0_12.getERROR_CONFIG_SDK().getMessage(), 0, 0, 0, 0, 0, 124, 0));
                    return;
                }
                return;
            } else {
                p26.onComplete(new vn.ai.trueid.sdk.models.NfcResult(306, "SDK is busy, please wait for the current flow to finish", 0, 0, 0, 0, 0, 124, 0));
                return;
            }
        } else {
            p26.onComplete(new vn.ai.trueid.sdk.models.NfcResult(v3_8, vn.ai.trueid.sdk.navigation.TrueIDV3.INSTANCE.securityMessage(v3_8), 0, 0, 0, 0, 0, 124, 0));
            return;
        }
    }

    public static final void verifyOtpOCRA(android.app.Activity p7, String p8, String p9, String p10, String p11, vn.ai.trueid.sdk.navigation.TrueIdSmartOTPListener p12)
    {
        if (p11.length() > 0) {
            kotlin.Pair v0_4 = vn.ai.trueid.sdk.navigation.TrueIDV3.h;
            if (v0_4 != null) {
                v0_4.setUserId(p11);
            }
        }
        Mk.f.l(vn.ai.trueid.sdk.navigation.TrueIDV3.INSTANCE.getSmartOtpConfig$trueid_release().getVerifyEndpoint(), vn.ai.trueid.sdk.networks.h0.d, kotlin.collections.a0.mutableMapOf(new kotlin.Pair[] {kotlin.TuplesKt.to("personID", p8), kotlin.TuplesKt.to("requestID", vn.ai.trueid.sdk.networks.g0.d()), kotlin.TuplesKt.to("sessionID", p9), kotlin.TuplesKt.to("responseCode", p10)})), new h3.j(new vn.ai.trueid.sdk.navigation.TrueIDV3$verifyOtpOCRA$1(new vn.ai.trueid.sdk.models.sotp.TrueIdSmartOTPResult(0, 0, 0, 7, 0), p12), p7));
        return;
    }

    public static synthetic void verifyOtpOCRA$default(android.app.Activity p6, String p7, String p8, String p9, String p10, vn.ai.trueid.sdk.navigation.TrueIdSmartOTPListener p11, int p12, Object p13)
    {
        if ((p12 & 16) != 0) {
            p10 = "";
        }
        vn.ai.trueid.sdk.navigation.TrueIDV3.verifyOtpOCRA(p6, p7, p8, p9, p10, p11);
        return;
    }

    public final void a(android.app.Activity p24, String p25, vn.ai.trueid.sdk.navigation.TrueIDEKYCCaptureListener p26)
    {
        vn.ai.trueid.sdk.navigation.TrueIDV3.info(p24);
        vn.ai.trueid.sdk.models.TrueError v3_30 = vn.ai.trueid.sdk.navigation.TrueIDV3.checkSecurityDevice();
        if (v3_30 == 1) {
            if (!vn.ai.trueid.sdk.navigation.TrueIDV3.p) {
                vn.ai.trueid.sdk.utils.TrueIDCaptureMetadata.INSTANCE.setFlow("EKYC");
                if ((vn.ai.trueid.sdk.navigation.TrueIDV3.m == null) && (vn.ai.trueid.sdk.navigation.TrueIDV3.k == null)) {
                    if (vn.ai.trueid.sdk.navigation.TrueIDV3.e != null) {
                        if (p25 == null) {
                            java.util.List v9_4 = vn.ai.trueid.sdk.navigation.TrueIDV3.d;
                            int v10_5 = vn.ai.trueid.sdk.utils.Helpers.Companion.getContext().getResources();
                            if (m9.B2.c("primaryColor", v9_4) == null) {
                                v10_5.getString(2131955530);
                            }
                            if (m9.B2.c("secondaryColor", v9_4) == null) {
                                v10_5.getString(2131955540);
                            }
                            if (m9.B2.c("titleColor", v9_4) == null) {
                                v10_5.getString(2131955572);
                            }
                            if (m9.B2.c("backgroundColor", v9_4) == null) {
                                v10_5.getString(2131955429);
                            }
                            if (m9.B2.c("textColor", v9_4) == null) {
                                v10_5.getString(2131955571);
                            }
                            if (m9.B2.c("textButtonColor", v9_4) == null) {
                                v10_5.getString(2131955570);
                            }
                            if (m9.B2.c("borderInputColor", v9_4) == null) {
                                v10_5.getString(2131955431);
                            }
                            if (m9.B2.c("errorColor", v9_4) == null) {
                                v10_5.getString(2131955469);
                            }
                            if (m9.B2.c("closeColor", v9_4) == null) {
                                v10_5.getString(2131955437);
                            }
                            if (m9.B2.c("stepBarColor", v9_4) == null) {
                                v10_5.getString(2131955568);
                            }
                            if (m9.B2.c("stepBarBackgroundColor", v9_4) == null) {
                                v10_5.getString(2131955567);
                            }
                            if (m9.B2.c("fontName", v9_4) == null) {
                                v10_5.getString(2131955482);
                            }
                            if (m9.B2.a("imageConfirmation", v9_4) == null) {
                                v10_5.getBoolean(2131034131);
                            }
                            if (m9.B2.a("isShowStepBar", v9_4) == null) {
                                v10_5.getBoolean(2131034135);
                            }
                            if (m9.B2.a("idGuide", v9_4) == null) {
                                v10_5.getBoolean(2131034130);
                            }
                            if (m9.B2.a("isDisableSecurity", v9_4) == null) {
                                v10_5.getBoolean(2131034132);
                            }
                            if (m9.B2.a("isShowSkipNFC", v9_4) == null) {
                                v10_5.getBoolean(2131034134);
                            }
                            if (m9.B2.a("trueidWatermark", v9_4) == null) {
                                v10_5.getBoolean(2131034138);
                            }
                            if (m9.B2.a("nfcSkipMaxRetry", v9_4) == null) {
                                v10_5.getBoolean(2131034136);
                            }
                            if (m9.B2.a("returnNFC", v9_4) == null) {
                                v10_5.getBoolean(2131034137);
                            }
                            if ((v9_4.has("nfcRequire")) && (!v9_4.isNull("nfcRequire"))) {
                                v9_4.optInt("nfcRequire");
                            }
                            if (m9.B2.a("isAutoProcess", v9_4) == null) {
                                v10_5.getBoolean(2131034128);
                            }
                            if (m9.B2.a("isShowCloseButton", v9_4) == null) {
                                v10_5.getBoolean(2131034133);
                            }
                            m9.B2.a("isHideQRScan", v9_4);
                            if (v9_4.has("maxRetry") != 1) {
                                v10_5.getInteger(2131427395);
                            } else {
                                v9_4.optInt("maxRetry");
                            }
                            if ((v9_4.has("stepVerification") != 1) || (v9_4.isNull("stepVerification"))) {
                                java.util.List v9_0 = kotlin.collections.y.toList(v10_5.getStringArray(2130903066));
                            } else {
                                boolean v6_1 = v9_4.optJSONArray("stepVerification");
                                if (!v6_1) {
                                    v9_0 = kotlin.collections.y.toList(v10_5.getStringArray(2130903066));
                                } else {
                                    v9_0 = new java.util.ArrayList();
                                    int v10_0 = v6_1.length();
                                    int v11_37 = 0;
                                    while (v11_37 < v10_0) {
                                        v9_0.add(v6_1.optString(v11_37));
                                        v11_37++;
                                    }
                                }
                            }
                            if (v9_0.contains("SCANNFC")) {
                                vn.ai.trueid.sdk.models.TrueError v3_7;
                                int v5_3 = this.nfcRequire$trueid_release();
                                if ((v5_3 == 0) || (v5_3 == 1)) {
                                    boolean v6_4 = vn.ai.trueid.sdk.utils.b.a();
                                    if ((v5_3 != 1) || (v6_4)) {
                                        if ((!v6_4) || (vn.ai.trueid.sdk.utils.b.c(0, p24, 0, 8))) {
                                            v3_7 = 0;
                                        } else {
                                            v3_7 = vn.ai.trueid.sdk.models.TrueError.Companion.getNFC_NOT_ENABLED();
                                        }
                                    } else {
                                        v3_7 = vn.ai.trueid.sdk.models.TrueError.Companion.getNFC_NOT_AVAILABLE();
                                    }
                                }
                                if (v3_7 != null) {
                                    p26.onComplete(new vn.ai.trueid.sdk.models.VerificationResult(v3_7.getCode(), v3_7.getMessage()));
                                    return;
                                }
                            }
                        } else {
                            if (vn.ai.trueid.sdk.utils.b.a()) {
                                if (vn.ai.trueid.sdk.utils.b.c(0, p24, 0, 8)) {
                                    vn.ai.trueid.sdk.navigation.TrueIDV3.d.put("stepVerification", new org.json.JSONArray(((java.util.Collection) kotlin.collections.E.listOf(new String[] {"SCANNFC", "SELFIE"})))));
                                } else {
                                    vn.ai.trueid.sdk.models.TrueError v3_22 = vn.ai.trueid.sdk.models.TrueError.Companion;
                                    p26.onComplete(new vn.ai.trueid.sdk.models.VerificationResult(v3_22.getNFC_NOT_ENABLED().getCode(), v3_22.getNFC_NOT_ENABLED().getMessage()));
                                    return;
                                }
                            } else {
                                vn.ai.trueid.sdk.models.TrueError v3_25 = vn.ai.trueid.sdk.models.TrueError.Companion;
                                p26.onComplete(new vn.ai.trueid.sdk.models.VerificationResult(v3_25.getNFC_NOT_AVAILABLE().getCode(), v3_25.getNFC_NOT_AVAILABLE().getMessage()));
                                return;
                            }
                        }
                        vn.ai.trueid.sdk.models.TrueError v3_14 = p24.getSharedPreferences("vendor_prefs", 0).getString("vendor_id", 0);
                        if (v3_14 == null) {
                            v3_14 = "TRUEID";
                        }
                        vn.ai.trueid.sdk.models.TrueError v3_16;
                        if (!kotlin.jvm.internal.Intrinsics.areEqual(v3_14, "hlb")) {
                            if (!kotlin.jvm.internal.Intrinsics.areEqual(v3_14, "moonbase")) {
                                v3_16 = "\n                                    {\n                                      \"primaryColor\":\"#26B840\",\n                                      \"secondaryColor\":\"#42526E\",\n                                      \"titleColor\":\"#253858\",\n                                      \"errorColor\":\"#FF6234\",\n                                      \"textColor\":\"#253858\",\n                                      \"borderInputColor\":\"#D3D9E0\",\n                                      \"backgroundColor\":\"#FFFFFF\",\n                                      \"closeColor\":\"#000000\",\n                                      \"stepBarColor\":\"#26B840\",\n                                      \"stepBarBackgroundColor\":\"#E4F4FF\",\n                                      \"fontName\":\"Lato Italic\",\n                                      \"borderRadius\":8,\n                                      \"maxRetry\":1000,\n                                      \"defaultSpacing\":20,\n                                      \"imageConfirmation\":true,\n                                      \"trueidWatermark\":true,\n                                      \"isDisableSecurity\":false,\n                                      \"isShowStepBar\":false,\n                                      \"isShowSkipNFC\":false,\n                                      \"returnNFC\":true,\n                                      \"nfcSkipMaxRetry\":true,\n                                      \"ovalColor\": \"#26B840\"\n                                    }\n                                    ";
                            } else {
                                v3_16 = "\n                                    {\n                                      \"primaryColor\":  \"#2BAA50\",\n                                      \"secondaryColor\":\"#2BAA50\",\n                                      \"titleColor\":    \"#FFFFFF\",\n                                      \"errorColor\":    \"#EF4444\",\n                                      \"textColor\":     \"#FFFFFF\",\n                                      \"borderInputColor\":\"#2BAA50\",\n                                      \"backgroundColor\":\"#000000\",\n                                      \"closeColor\":    \"#FFFFFF\",\n                                      \"stepBarColor\":  \"#26B840\",\n                                      \"stepBarBackgroundColor\":\"#1A1A1A\",\n                                      \"borderRadius\":  16,\n                                      \"ovalColor\":     \"#2BAA50\",\n                                      \"animationOvalColor\":\"#2BAA50\",\n                                      \"successColor\":  \"#26B840\",\n                                      \"trueidWatermark\":false,\n                                      \"isShowStepBar\": false,\n                                      \"isAutoProcess\": true,\n                                      \"overlayAlpha\":  0.9\n                                    }\n                                    ";
                            }
                        } else {
                            v3_16 = "{\n  \"primaryColor\": \"#000000\",\n  \"titleColor\": \"#00CCC6\",\n  \"textButtonColor\": \"#FFFFFF\",\n  \"errorColor\": \"#FF6234\",\n  \"textColor\": \"#091E42\",\n  \"borderInputColor\": \"#00CCC6\",\n  \"backgroundColor\": \"#FFFFFF\",\n  \"closeColor\": \"#253858\",\n  \"borderRadius\": 16,\n  \"maxRetry\": 4,\n  \"defaultSpacing\": 26,\n  \"imageConfirmation\": false,\n  \"trueidWatermark\": true,\n  \"isDisableSecurity\": false,\n  \"isShowStepBar\": false,\n  \"isShowSkipNFC\": false,\n  \"returnNFC\": true,\n  \"nfcSkipMaxRetry\": true\n}\n";
                        }
                        vn.ai.trueid.sdk.navigation.TrueIDV3.d = this.importColor(v3_16, vn.ai.trueid.sdk.navigation.TrueIDV3.d, 0);
                        vn.ai.trueid.sdk.utils.Helpers.Companion.setContext(p24);
                        this.clearListener();
                        vn.ai.trueid.sdk.navigation.TrueIDV3.l = 1;
                        vn.ai.trueid.sdk.navigation.TrueIDV3.m = p26;
                        fk.a.a();
                        if (p25 != null) {
                            fk.b.f = 5;
                            fk.b.d = new vn.ai.trueid.sdk.models.Person(p25, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 131070, 0);
                        }
                        vn.ai.trueid.sdk.networks.q.g();
                        p24.startActivity(new android.content.Intent(p24, vn.ai.trueid.sdk.activity.TrueIDActivity));
                    } else {
                        p24.runOnUiThread(new CS.F(p24, 2));
                        return;
                    }
                }
                return;
            } else {
                p26.onComplete(new vn.ai.trueid.sdk.models.VerificationResult(306, "SDK is busy, please wait for the current flow to finish"));
                return;
            }
        } else {
            p26.onComplete(new vn.ai.trueid.sdk.models.VerificationResult(v3_30, this.securityMessage(v3_30)));
            return;
        }
    }

    public final void clearConfig()
    {
        vn.ai.trueid.sdk.navigation.TrueIDV3.b = 0;
        vn.ai.trueid.sdk.navigation.TrueIDV3.d = new org.json.JSONObject("{}");
        return;
    }

    public final void clearListener()
    {
        vn.ai.trueid.sdk.navigation.TrueIDV3.k = 0;
        vn.ai.trueid.sdk.navigation.TrueIDV3.r = 0;
        vn.ai.trueid.sdk.navigation.TrueIDV3.q = 0;
        vn.ai.trueid.sdk.navigation.TrueIDV3.m = 0;
        vn.ai.trueid.sdk.navigation.TrueIDV3.l = 0;
        vn.ai.trueid.sdk.navigation.TrueIDV3.o = 0;
        vn.ai.trueid.sdk.navigation.TrueIDV3.n = 0;
        return;
    }

    public final vn.ai.trueid.sdk.navigation.TrueIdSmartOTPListener getActivePushListener$trueid_release()
    {
        return vn.ai.trueid.sdk.navigation.TrueIDV3.j;
    }

    public final org.json.JSONObject getConfigHeader$trueid_release()
    {
        return vn.ai.trueid.sdk.navigation.TrueIDV3.c;
    }

    public final org.json.JSONObject getConfigUI$trueid_release()
    {
        return vn.ai.trueid.sdk.navigation.TrueIDV3.d;
    }

    public final vn.ai.trueid.sdk.models.DeviceInfo getDeviceInfo()
    {
        return vn.ai.trueid.sdk.navigation.TrueIDV3.a;
    }

    public final vn.ai.trueid.sdk.navigation.TrueIDEKYCCaptureListener getEkycCaptureListener$trueid_release()
    {
        return vn.ai.trueid.sdk.navigation.TrueIDV3.m;
    }

    public final vn.ai.trueid.sdk.models.EkycConfig getEkycConfig$trueid_release()
    {
        return vn.ai.trueid.sdk.navigation.TrueIDV3.e;
    }

    public final vn.ai.trueid.sdk.navigation.TrueIDListener getEkycListener$trueid_release()
    {
        return vn.ai.trueid.sdk.navigation.TrueIDV3.k;
    }

    public final vn.ai.trueid.sdk.navigation.TrueIDFaceEnrollCaptureListener getEnrollCaptureListener$trueid_release()
    {
        return vn.ai.trueid.sdk.navigation.TrueIDV3.o;
    }

    public final vn.ai.trueid.sdk.navigation.FaceEnrollListener getEnrollListener$trueid_release()
    {
        return vn.ai.trueid.sdk.navigation.TrueIDV3.r;
    }

    public final vn.ai.trueid.sdk.models.FaceEnrollConfig getFaceEnrollConfig$trueid_release()
    {
        return vn.ai.trueid.sdk.navigation.TrueIDV3.f;
    }

    public final vn.ai.trueid.sdk.models.FaceEnrollConfig getFaceSDKConfig$trueid_release()
    {
        vn.ai.trueid.sdk.models.FaceEnrollConfig v0 = vn.ai.trueid.sdk.navigation.TrueIDV3.f;
        if (v0 != null) {
            return v0;
        } else {
            return new vn.ai.trueid.sdk.models.FaceEnrollConfig(0, 0, 0, 0, 0, 0, 0, 0, 255, 0);
        }
    }

    public final org.json.JSONObject getLanguageJSON()
    {
        return vn.ai.trueid.sdk.navigation.TrueIDV3.b;
    }

    public final vn.ai.trueid.sdk.models.NFCConfig getNFCConfig$trueid_release()
    {
        vn.ai.trueid.sdk.models.NFCConfig v0 = vn.ai.trueid.sdk.navigation.TrueIDV3.g;
        if (v0 != null) {
            return v0;
        } else {
            return new vn.ai.trueid.sdk.models.NFCConfig(0, 0, 0, 0, 0, 0, 0, 127, 0);
        }
    }

    public final vn.ai.trueid.sdk.models.NFCConfig getNfcConfig$trueid_release()
    {
        return vn.ai.trueid.sdk.navigation.TrueIDV3.g;
    }

    public final vn.ai.trueid.sdk.navigation.NFCListener getNfcListener$trueid_release()
    {
        return vn.ai.trueid.sdk.navigation.TrueIDV3.q;
    }

    public final Nk.c getNfccccdReader()
    {
        return ((Nk.c) vn.ai.trueid.sdk.navigation.TrueIDV3.v.getValue());
    }

    public final vn.ai.trueid.sdk.models.sotp.TrueIdSmartOTPConfig getSmartOTPConfig$trueid_release()
    {
        return vn.ai.trueid.sdk.navigation.TrueIDV3.h;
    }

    public final vn.ai.trueid.sdk.navigation.TrueIDListener getSmartOTPListener$trueid_release()
    {
        return vn.ai.trueid.sdk.navigation.TrueIDV3.s;
    }

    public final vn.ai.trueid.sdk.models.sotp.TrueIdSmartOTPConfig getSmartOtpConfig$trueid_release()
    {
        vn.ai.trueid.sdk.models.sotp.TrueIdSmartOTPConfig v0 = vn.ai.trueid.sdk.navigation.TrueIDV3.h;
        if (v0 != null) {
            return v0;
        } else {
            return new vn.ai.trueid.sdk.models.sotp.TrueIdSmartOTPConfig(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8388607, 0);
        }
    }

    public final vn.ai.trueid.sdk.models.sotp.TrueIdSmartOTPUIConfig getSmartOtpUIConfig$trueid_release()
    {
        return vn.ai.trueid.sdk.navigation.TrueIDV3.i;
    }

    public final void h(android.app.Activity p15, String p16, kotlin.jvm.functions.Function1 p17)
    {
        int v0_1 = this.getSmartOtpConfig$trueid_release().getPinPolicy();
        vn.ai.trueid.sdk.navigation.TrueIDV3.c(this, p15, 2, "Thi\u1ebft l\u1eadp m\u00e3 PIN", androidx.activity.result.e.f(v0_1.getEffectiveMin(), v0_1.getEffectiveMax(), "T\u1ea1o m\u00e3 PIN ", "\u2013", " ch\u1eef s\u1ed1 \u0111\u1ec3 b\u1ea3o v\u1ec7 Smart OTP."), p17, 0, p16, 32);
        return;
    }

    public final org.json.JSONObject importColor(String p4, org.json.JSONObject p5, boolean p6)
    {
        org.json.JSONObject v0_1 = new org.json.JSONObject(p4);
        java.util.Iterator v4_1 = v0_1.keys();
        while (v4_1.hasNext()) {
            String v1_0 = ((String) v4_1.next());
            if ((p6) || (!p5.has(v1_0))) {
                Object v2_1 = v0_1.opt(v1_0);
                if (v2_1 != null) {
                    p5.put(v1_0, v2_1);
                }
            }
        }
        return p5;
    }

    public final boolean isAutoProcess()
    {
        String[] v0_0 = vn.ai.trueid.sdk.navigation.TrueIDV3.d;
        java.util.ArrayList v1_4 = vn.ai.trueid.sdk.utils.Helpers.Companion.getContext().getResources();
        if (m9.B2.c("primaryColor", v0_0) == null) {
            v1_4.getString(2131955530);
        }
        if (m9.B2.c("secondaryColor", v0_0) == null) {
            v1_4.getString(2131955540);
        }
        if (m9.B2.c("titleColor", v0_0) == null) {
            v1_4.getString(2131955572);
        }
        if (m9.B2.c("backgroundColor", v0_0) == null) {
            v1_4.getString(2131955429);
        }
        if (m9.B2.c("textColor", v0_0) == null) {
            v1_4.getString(2131955571);
        }
        if (m9.B2.c("textButtonColor", v0_0) == null) {
            v1_4.getString(2131955570);
        }
        if (m9.B2.c("borderInputColor", v0_0) == null) {
            v1_4.getString(2131955431);
        }
        if (m9.B2.c("errorColor", v0_0) == null) {
            v1_4.getString(2131955469);
        }
        if (m9.B2.c("closeColor", v0_0) == null) {
            v1_4.getString(2131955437);
        }
        if (m9.B2.c("stepBarColor", v0_0) == null) {
            v1_4.getString(2131955568);
        }
        if (m9.B2.c("stepBarBackgroundColor", v0_0) == null) {
            v1_4.getString(2131955567);
        }
        if (m9.B2.c("fontName", v0_0) == null) {
            v1_4.getString(2131955482);
        }
        if (m9.B2.a("imageConfirmation", v0_0) == null) {
            v1_4.getBoolean(2131034131);
        }
        if (m9.B2.a("isShowStepBar", v0_0) == null) {
            v1_4.getBoolean(2131034135);
        }
        if (m9.B2.a("idGuide", v0_0) == null) {
            v1_4.getBoolean(2131034130);
        }
        if (m9.B2.a("isDisableSecurity", v0_0) == null) {
            v1_4.getBoolean(2131034132);
        }
        if (m9.B2.a("isShowSkipNFC", v0_0) == null) {
            v1_4.getBoolean(2131034134);
        }
        if (m9.B2.a("trueidWatermark", v0_0) == null) {
            v1_4.getBoolean(2131034138);
        }
        if (m9.B2.a("nfcSkipMaxRetry", v0_0) == null) {
            v1_4.getBoolean(2131034136);
        }
        if (m9.B2.a("returnNFC", v0_0) == null) {
            v1_4.getBoolean(2131034137);
        }
        if ((v0_0.has("nfcRequire")) && (!v0_0.isNull("nfcRequire"))) {
            v0_0.optInt("nfcRequire");
        }
        boolean v2_20;
        boolean v2_17 = m9.B2.a("isAutoProcess", v0_0);
        if (!v2_17) {
            v2_20 = v1_4.getBoolean(2131034128);
        } else {
            v2_20 = v2_17.booleanValue();
        }
        if (m9.B2.a("isShowCloseButton", v0_0) == null) {
            v1_4.getBoolean(2131034133);
        }
        m9.B2.a("isHideQRScan", v0_0);
        if (v0_0.has("maxRetry") != 1) {
            v1_4.getInteger(2131427395);
        } else {
            v0_0.optInt("maxRetry");
        }
        if ((v0_0.has("stepVerification") != 1) || (v0_0.isNull("stepVerification"))) {
            kotlin.collections.y.toList(v1_4.getStringArray(2130903066));
        } else {
            String[] v0_2 = v0_0.optJSONArray("stepVerification");
            if (v0_2 == null) {
                kotlin.collections.y.toList(v1_4.getStringArray(2130903066));
            } else {
                java.util.ArrayList v1_2 = new java.util.ArrayList();
                int v3_9 = v0_2.length();
                int v4_3 = 0;
                while (v4_3 < v3_9) {
                    v1_2.add(v0_2.optString(v4_3));
                    v4_3++;
                }
            }
        }
        return v2_20;
    }

    public final boolean isBVBSupported()
    {
        boolean v0_1 = vn.ai.trueid.sdk.utils.Helpers.Companion.getContext().getSharedPreferences("vendor_prefs", 0).getString("vendor_id", 0);
        if (!v0_1) {
            v0_1 = "TRUEID";
        }
        return kotlin.jvm.internal.Intrinsics.areEqual(v0_1, "bvb");
    }

    public final boolean isDisableSecurity()
    {
        String[] v0_0 = vn.ai.trueid.sdk.navigation.TrueIDV3.d;
        java.util.ArrayList v1_4 = vn.ai.trueid.sdk.utils.Helpers.Companion.getContext().getResources();
        if (m9.B2.c("primaryColor", v0_0) == null) {
            v1_4.getString(2131955530);
        }
        if (m9.B2.c("secondaryColor", v0_0) == null) {
            v1_4.getString(2131955540);
        }
        if (m9.B2.c("titleColor", v0_0) == null) {
            v1_4.getString(2131955572);
        }
        if (m9.B2.c("backgroundColor", v0_0) == null) {
            v1_4.getString(2131955429);
        }
        if (m9.B2.c("textColor", v0_0) == null) {
            v1_4.getString(2131955571);
        }
        if (m9.B2.c("textButtonColor", v0_0) == null) {
            v1_4.getString(2131955570);
        }
        if (m9.B2.c("borderInputColor", v0_0) == null) {
            v1_4.getString(2131955431);
        }
        if (m9.B2.c("errorColor", v0_0) == null) {
            v1_4.getString(2131955469);
        }
        if (m9.B2.c("closeColor", v0_0) == null) {
            v1_4.getString(2131955437);
        }
        if (m9.B2.c("stepBarColor", v0_0) == null) {
            v1_4.getString(2131955568);
        }
        if (m9.B2.c("stepBarBackgroundColor", v0_0) == null) {
            v1_4.getString(2131955567);
        }
        if (m9.B2.c("fontName", v0_0) == null) {
            v1_4.getString(2131955482);
        }
        if (m9.B2.a("imageConfirmation", v0_0) == null) {
            v1_4.getBoolean(2131034131);
        }
        if (m9.B2.a("isShowStepBar", v0_0) == null) {
            v1_4.getBoolean(2131034135);
        }
        if (m9.B2.a("idGuide", v0_0) == null) {
            v1_4.getBoolean(2131034130);
        }
        boolean v2_2;
        boolean v2_48 = m9.B2.a("isDisableSecurity", v0_0);
        if (!v2_48) {
            v2_2 = v1_4.getBoolean(2131034132);
        } else {
            v2_2 = v2_48.booleanValue();
        }
        if (m9.B2.a("isShowSkipNFC", v0_0) == null) {
            v1_4.getBoolean(2131034134);
        }
        if (m9.B2.a("trueidWatermark", v0_0) == null) {
            v1_4.getBoolean(2131034138);
        }
        if (m9.B2.a("nfcSkipMaxRetry", v0_0) == null) {
            v1_4.getBoolean(2131034136);
        }
        if (m9.B2.a("returnNFC", v0_0) == null) {
            v1_4.getBoolean(2131034137);
        }
        if ((v0_0.has("nfcRequire")) && (!v0_0.isNull("nfcRequire"))) {
            v0_0.optInt("nfcRequire");
        }
        if (m9.B2.a("isAutoProcess", v0_0) == null) {
            v1_4.getBoolean(2131034128);
        }
        if (m9.B2.a("isShowCloseButton", v0_0) == null) {
            v1_4.getBoolean(2131034133);
        }
        m9.B2.a("isHideQRScan", v0_0);
        if (v0_0.has("maxRetry") != 1) {
            v1_4.getInteger(2131427395);
        } else {
            v0_0.optInt("maxRetry");
        }
        if ((v0_0.has("stepVerification") != 1) || (v0_0.isNull("stepVerification"))) {
            kotlin.collections.y.toList(v1_4.getStringArray(2130903066));
        } else {
            String[] v0_2 = v0_0.optJSONArray("stepVerification");
            if (v0_2 == null) {
                kotlin.collections.y.toList(v1_4.getStringArray(2130903066));
            } else {
                java.util.ArrayList v1_2 = new java.util.ArrayList();
                int v3_23 = v0_2.length();
                int v4_5 = 0;
                while (v4_5 < v3_23) {
                    v1_2.add(v0_2.optString(v4_5));
                    v4_5++;
                }
            }
        }
        return v2_2;
    }

    public final boolean isEkycCaptureMode$trueid_release()
    {
        return vn.ai.trueid.sdk.navigation.TrueIDV3.l;
    }

    public final boolean isEnrollCaptureMode$trueid_release()
    {
        return vn.ai.trueid.sdk.navigation.TrueIDV3.n;
    }

    public final boolean isGuide$trueid_release()
    {
        String[] v0_0 = vn.ai.trueid.sdk.navigation.TrueIDV3.d;
        java.util.ArrayList v1_4 = vn.ai.trueid.sdk.utils.Helpers.Companion.getContext().getResources();
        if (m9.B2.c("primaryColor", v0_0) == null) {
            v1_4.getString(2131955530);
        }
        if (m9.B2.c("secondaryColor", v0_0) == null) {
            v1_4.getString(2131955540);
        }
        if (m9.B2.c("titleColor", v0_0) == null) {
            v1_4.getString(2131955572);
        }
        if (m9.B2.c("backgroundColor", v0_0) == null) {
            v1_4.getString(2131955429);
        }
        if (m9.B2.c("textColor", v0_0) == null) {
            v1_4.getString(2131955571);
        }
        if (m9.B2.c("textButtonColor", v0_0) == null) {
            v1_4.getString(2131955570);
        }
        if (m9.B2.c("borderInputColor", v0_0) == null) {
            v1_4.getString(2131955431);
        }
        if (m9.B2.c("errorColor", v0_0) == null) {
            v1_4.getString(2131955469);
        }
        if (m9.B2.c("closeColor", v0_0) == null) {
            v1_4.getString(2131955437);
        }
        if (m9.B2.c("stepBarColor", v0_0) == null) {
            v1_4.getString(2131955568);
        }
        if (m9.B2.c("stepBarBackgroundColor", v0_0) == null) {
            v1_4.getString(2131955567);
        }
        if (m9.B2.c("fontName", v0_0) == null) {
            v1_4.getString(2131955482);
        }
        if (m9.B2.a("imageConfirmation", v0_0) == null) {
            v1_4.getBoolean(2131034131);
        }
        if (m9.B2.a("isShowStepBar", v0_0) == null) {
            v1_4.getBoolean(2131034135);
        }
        boolean v2_45;
        boolean v2_43 = m9.B2.a("idGuide", v0_0);
        if (!v2_43) {
            v2_45 = v1_4.getBoolean(2131034130);
        } else {
            v2_45 = v2_43.booleanValue();
        }
        if (m9.B2.a("isDisableSecurity", v0_0) == null) {
            v1_4.getBoolean(2131034132);
        }
        if (m9.B2.a("isShowSkipNFC", v0_0) == null) {
            v1_4.getBoolean(2131034134);
        }
        if (m9.B2.a("trueidWatermark", v0_0) == null) {
            v1_4.getBoolean(2131034138);
        }
        if (m9.B2.a("nfcSkipMaxRetry", v0_0) == null) {
            v1_4.getBoolean(2131034136);
        }
        if (m9.B2.a("returnNFC", v0_0) == null) {
            v1_4.getBoolean(2131034137);
        }
        if ((v0_0.has("nfcRequire")) && (!v0_0.isNull("nfcRequire"))) {
            v0_0.optInt("nfcRequire");
        }
        if (m9.B2.a("isAutoProcess", v0_0) == null) {
            v1_4.getBoolean(2131034128);
        }
        if (m9.B2.a("isShowCloseButton", v0_0) == null) {
            v1_4.getBoolean(2131034133);
        }
        m9.B2.a("isHideQRScan", v0_0);
        if (v0_0.has("maxRetry") != 1) {
            v1_4.getInteger(2131427395);
        } else {
            v0_0.optInt("maxRetry");
        }
        if ((v0_0.has("stepVerification") != 1) || (v0_0.isNull("stepVerification"))) {
            kotlin.collections.y.toList(v1_4.getStringArray(2130903066));
        } else {
            String[] v0_2 = v0_0.optJSONArray("stepVerification");
            if (v0_2 == null) {
                kotlin.collections.y.toList(v1_4.getStringArray(2130903066));
            } else {
                java.util.ArrayList v1_2 = new java.util.ArrayList();
                int v3_25 = v0_2.length();
                int v4_5 = 0;
                while (v4_5 < v3_25) {
                    v1_2.add(v0_2.optString(v4_5));
                    v4_5++;
                }
            }
        }
        return v2_45;
    }

    public final boolean isHlbSupported$trueid_release()
    {
        boolean v0_1 = vn.ai.trueid.sdk.utils.Helpers.Companion.getContext().getSharedPreferences("vendor_prefs", 0).getString("vendor_id", 0);
        if (!v0_1) {
            v0_1 = "TRUEID";
        }
        return kotlin.jvm.internal.Intrinsics.areEqual(v0_1, "hlb");
    }

    public final boolean isIDConfirmation()
    {
        return 0;
    }

    public final boolean isImageConfirmation$trueid_release()
    {
        String[] v0_0 = vn.ai.trueid.sdk.navigation.TrueIDV3.d;
        java.util.ArrayList v1_4 = vn.ai.trueid.sdk.utils.Helpers.Companion.getContext().getResources();
        if (m9.B2.c("primaryColor", v0_0) == null) {
            v1_4.getString(2131955530);
        }
        if (m9.B2.c("secondaryColor", v0_0) == null) {
            v1_4.getString(2131955540);
        }
        if (m9.B2.c("titleColor", v0_0) == null) {
            v1_4.getString(2131955572);
        }
        if (m9.B2.c("backgroundColor", v0_0) == null) {
            v1_4.getString(2131955429);
        }
        if (m9.B2.c("textColor", v0_0) == null) {
            v1_4.getString(2131955571);
        }
        if (m9.B2.c("textButtonColor", v0_0) == null) {
            v1_4.getString(2131955570);
        }
        if (m9.B2.c("borderInputColor", v0_0) == null) {
            v1_4.getString(2131955431);
        }
        if (m9.B2.c("errorColor", v0_0) == null) {
            v1_4.getString(2131955469);
        }
        if (m9.B2.c("closeColor", v0_0) == null) {
            v1_4.getString(2131955437);
        }
        if (m9.B2.c("stepBarColor", v0_0) == null) {
            v1_4.getString(2131955568);
        }
        if (m9.B2.c("stepBarBackgroundColor", v0_0) == null) {
            v1_4.getString(2131955567);
        }
        if (m9.B2.c("fontName", v0_0) == null) {
            v1_4.getString(2131955482);
        }
        boolean v2_39;
        boolean v2_37 = m9.B2.a("imageConfirmation", v0_0);
        if (!v2_37) {
            v2_39 = v1_4.getBoolean(2131034131);
        } else {
            v2_39 = v2_37.booleanValue();
        }
        if (m9.B2.a("isShowStepBar", v0_0) == null) {
            v1_4.getBoolean(2131034135);
        }
        if (m9.B2.a("idGuide", v0_0) == null) {
            v1_4.getBoolean(2131034130);
        }
        if (m9.B2.a("isDisableSecurity", v0_0) == null) {
            v1_4.getBoolean(2131034132);
        }
        if (m9.B2.a("isShowSkipNFC", v0_0) == null) {
            v1_4.getBoolean(2131034134);
        }
        if (m9.B2.a("trueidWatermark", v0_0) == null) {
            v1_4.getBoolean(2131034138);
        }
        if (m9.B2.a("nfcSkipMaxRetry", v0_0) == null) {
            v1_4.getBoolean(2131034136);
        }
        if (m9.B2.a("returnNFC", v0_0) == null) {
            v1_4.getBoolean(2131034137);
        }
        if ((v0_0.has("nfcRequire")) && (!v0_0.isNull("nfcRequire"))) {
            v0_0.optInt("nfcRequire");
        }
        if (m9.B2.a("isAutoProcess", v0_0) == null) {
            v1_4.getBoolean(2131034128);
        }
        if (m9.B2.a("isShowCloseButton", v0_0) == null) {
            v1_4.getBoolean(2131034133);
        }
        m9.B2.a("isHideQRScan", v0_0);
        if (v0_0.has("maxRetry") != 1) {
            v1_4.getInteger(2131427395);
        } else {
            v0_0.optInt("maxRetry");
        }
        if ((v0_0.has("stepVerification") != 1) || (v0_0.isNull("stepVerification"))) {
            kotlin.collections.y.toList(v1_4.getStringArray(2130903066));
        } else {
            String[] v0_2 = v0_0.optJSONArray("stepVerification");
            if (v0_2 == null) {
                kotlin.collections.y.toList(v1_4.getStringArray(2130903066));
            } else {
                java.util.ArrayList v1_2 = new java.util.ArrayList();
                int v3_25 = v0_2.length();
                int v4_5 = 0;
                while (v4_5 < v3_25) {
                    v1_2.add(v0_2.optString(v4_5));
                    v4_5++;
                }
            }
        }
        return v2_39;
    }

    public final boolean isLockSimulator()
    {
        return 0;
    }

    public final boolean isNeedScanMRZ$trueid_release()
    {
        int v0_1;
        int v0_0 = fk.b.d;
        if (v0_0 == 0) {
            v0_1 = 0;
        } else {
            int v0_2 = v0_0.getIdNumber();
            if (v0_2 == 0) {
            } else {
                int v3_0 = 0;
                while (v3_0 < v0_2.length()) {
                    if (Character.isDigit(v0_2.charAt(v3_0))) {
                        v3_0++;
                    }
                }
                v0_1 = 1;
            }
        }
        int v3_1 = fk.b.d;
        if ((v3_1 != 0) && ((v3_1.getIdNumber().length() == 12) && (v0_1 != 0))) {
            return 0;
        } else {
            return 1;
        }
    }

    public final boolean isPackageInstalled$trueid_release(android.content.Context p2, String p3)
    {
        try {
            p2.getPackageManager().getPackageInfo(p3, 0);
            return 1;
        } catch (android.content.pm.PackageManager$NameNotFoundException) {
            return 0;
        }
    }

    public final boolean isPassport$trueid_release()
    {
        if (fk.b.f != 2) {
            return 0;
        } else {
            return 1;
        }
    }

    public final boolean isReturnNFC$trueid_release()
    {
        String[] v0_0 = vn.ai.trueid.sdk.navigation.TrueIDV3.d;
        java.util.ArrayList v1_4 = vn.ai.trueid.sdk.utils.Helpers.Companion.getContext().getResources();
        if (m9.B2.c("primaryColor", v0_0) == null) {
            v1_4.getString(2131955530);
        }
        if (m9.B2.c("secondaryColor", v0_0) == null) {
            v1_4.getString(2131955540);
        }
        if (m9.B2.c("titleColor", v0_0) == null) {
            v1_4.getString(2131955572);
        }
        if (m9.B2.c("backgroundColor", v0_0) == null) {
            v1_4.getString(2131955429);
        }
        if (m9.B2.c("textColor", v0_0) == null) {
            v1_4.getString(2131955571);
        }
        if (m9.B2.c("textButtonColor", v0_0) == null) {
            v1_4.getString(2131955570);
        }
        if (m9.B2.c("borderInputColor", v0_0) == null) {
            v1_4.getString(2131955431);
        }
        if (m9.B2.c("errorColor", v0_0) == null) {
            v1_4.getString(2131955469);
        }
        if (m9.B2.c("closeColor", v0_0) == null) {
            v1_4.getString(2131955437);
        }
        if (m9.B2.c("stepBarColor", v0_0) == null) {
            v1_4.getString(2131955568);
        }
        if (m9.B2.c("stepBarBackgroundColor", v0_0) == null) {
            v1_4.getString(2131955567);
        }
        if (m9.B2.c("fontName", v0_0) == null) {
            v1_4.getString(2131955482);
        }
        if (m9.B2.a("imageConfirmation", v0_0) == null) {
            v1_4.getBoolean(2131034131);
        }
        if (m9.B2.a("isShowStepBar", v0_0) == null) {
            v1_4.getBoolean(2131034135);
        }
        if (m9.B2.a("idGuide", v0_0) == null) {
            v1_4.getBoolean(2131034130);
        }
        if (m9.B2.a("isDisableSecurity", v0_0) == null) {
            v1_4.getBoolean(2131034132);
        }
        if (m9.B2.a("isShowSkipNFC", v0_0) == null) {
            v1_4.getBoolean(2131034134);
        }
        if (m9.B2.a("trueidWatermark", v0_0) == null) {
            v1_4.getBoolean(2131034138);
        }
        if (m9.B2.a("nfcSkipMaxRetry", v0_0) == null) {
            v1_4.getBoolean(2131034136);
        }
        boolean v2_15;
        boolean v2_13 = m9.B2.a("returnNFC", v0_0);
        if (!v2_13) {
            v2_15 = v1_4.getBoolean(2131034137);
        } else {
            v2_15 = v2_13.booleanValue();
        }
        if ((v0_0.has("nfcRequire")) && (!v0_0.isNull("nfcRequire"))) {
            v0_0.optInt("nfcRequire");
        }
        if (m9.B2.a("isAutoProcess", v0_0) == null) {
            v1_4.getBoolean(2131034128);
        }
        if (m9.B2.a("isShowCloseButton", v0_0) == null) {
            v1_4.getBoolean(2131034133);
        }
        m9.B2.a("isHideQRScan", v0_0);
        if (v0_0.has("maxRetry") != 1) {
            v1_4.getInteger(2131427395);
        } else {
            v0_0.optInt("maxRetry");
        }
        if ((v0_0.has("stepVerification") != 1) || (v0_0.isNull("stepVerification"))) {
            kotlin.collections.y.toList(v1_4.getStringArray(2130903066));
        } else {
            String[] v0_2 = v0_0.optJSONArray("stepVerification");
            if (v0_2 == null) {
                kotlin.collections.y.toList(v1_4.getStringArray(2130903066));
            } else {
                java.util.ArrayList v1_2 = new java.util.ArrayList();
                int v3_11 = v0_2.length();
                int v4_5 = 0;
                while (v4_5 < v3_11) {
                    v1_2.add(v0_2.optString(v4_5));
                    v4_5++;
                }
            }
        }
        return v2_15;
    }

    public final boolean isRunEnroll$trueid_release()
    {
        if ((vn.ai.trueid.sdk.navigation.TrueIDV3.r == null) && (vn.ai.trueid.sdk.navigation.TrueIDV3.o == null)) {
            return 0;
        } else {
            return 1;
        }
    }

    public final boolean isRunOnlyNFC$trueid_release()
    {
        if (vn.ai.trueid.sdk.navigation.TrueIDV3.q == null) {
            return 0;
        } else {
            return 1;
        }
    }

    public final boolean isSessionActive$trueid_release()
    {
        return vn.ai.trueid.sdk.navigation.TrueIDV3.p;
    }

    public final boolean isShowSkipNFC$trueid_release()
    {
        String[] v0_0 = vn.ai.trueid.sdk.navigation.TrueIDV3.d;
        java.util.ArrayList v1_4 = vn.ai.trueid.sdk.utils.Helpers.Companion.getContext().getResources();
        if (m9.B2.c("primaryColor", v0_0) == null) {
            v1_4.getString(2131955530);
        }
        if (m9.B2.c("secondaryColor", v0_0) == null) {
            v1_4.getString(2131955540);
        }
        if (m9.B2.c("titleColor", v0_0) == null) {
            v1_4.getString(2131955572);
        }
        if (m9.B2.c("backgroundColor", v0_0) == null) {
            v1_4.getString(2131955429);
        }
        if (m9.B2.c("textColor", v0_0) == null) {
            v1_4.getString(2131955571);
        }
        if (m9.B2.c("textButtonColor", v0_0) == null) {
            v1_4.getString(2131955570);
        }
        if (m9.B2.c("borderInputColor", v0_0) == null) {
            v1_4.getString(2131955431);
        }
        if (m9.B2.c("errorColor", v0_0) == null) {
            v1_4.getString(2131955469);
        }
        if (m9.B2.c("closeColor", v0_0) == null) {
            v1_4.getString(2131955437);
        }
        if (m9.B2.c("stepBarColor", v0_0) == null) {
            v1_4.getString(2131955568);
        }
        if (m9.B2.c("stepBarBackgroundColor", v0_0) == null) {
            v1_4.getString(2131955567);
        }
        if (m9.B2.c("fontName", v0_0) == null) {
            v1_4.getString(2131955482);
        }
        if (m9.B2.a("imageConfirmation", v0_0) == null) {
            v1_4.getBoolean(2131034131);
        }
        if (m9.B2.a("isShowStepBar", v0_0) == null) {
            v1_4.getBoolean(2131034135);
        }
        if (m9.B2.a("idGuide", v0_0) == null) {
            v1_4.getBoolean(2131034130);
        }
        if (m9.B2.a("isDisableSecurity", v0_0) == null) {
            v1_4.getBoolean(2131034132);
        }
        boolean v2_5;
        boolean v2_3 = m9.B2.a("isShowSkipNFC", v0_0);
        if (!v2_3) {
            v2_5 = v1_4.getBoolean(2131034134);
        } else {
            v2_5 = v2_3.booleanValue();
        }
        if (m9.B2.a("trueidWatermark", v0_0) == null) {
            v1_4.getBoolean(2131034138);
        }
        if (m9.B2.a("nfcSkipMaxRetry", v0_0) == null) {
            v1_4.getBoolean(2131034136);
        }
        if (m9.B2.a("returnNFC", v0_0) == null) {
            v1_4.getBoolean(2131034137);
        }
        if ((v0_0.has("nfcRequire")) && (!v0_0.isNull("nfcRequire"))) {
            v0_0.optInt("nfcRequire");
        }
        if (m9.B2.a("isAutoProcess", v0_0) == null) {
            v1_4.getBoolean(2131034128);
        }
        if (m9.B2.a("isShowCloseButton", v0_0) == null) {
            v1_4.getBoolean(2131034133);
        }
        m9.B2.a("isHideQRScan", v0_0);
        if (v0_0.has("maxRetry") != 1) {
            v1_4.getInteger(2131427395);
        } else {
            v0_0.optInt("maxRetry");
        }
        if ((v0_0.has("stepVerification") != 1) || (v0_0.isNull("stepVerification"))) {
            kotlin.collections.y.toList(v1_4.getStringArray(2130903066));
        } else {
            String[] v0_2 = v0_0.optJSONArray("stepVerification");
            if (v0_2 == null) {
                kotlin.collections.y.toList(v1_4.getStringArray(2130903066));
            } else {
                java.util.ArrayList v1_2 = new java.util.ArrayList();
                int v3_20 = v0_2.length();
                int v4_5 = 0;
                while (v4_5 < v3_20) {
                    v1_2.add(v0_2.optString(v4_5));
                    v4_5++;
                }
            }
        }
        return v2_5;
    }

    public final boolean isShowStepBar$trueid_release()
    {
        String[] v0_0 = vn.ai.trueid.sdk.navigation.TrueIDV3.d;
        java.util.ArrayList v1_4 = vn.ai.trueid.sdk.utils.Helpers.Companion.getContext().getResources();
        if (m9.B2.c("primaryColor", v0_0) == null) {
            v1_4.getString(2131955530);
        }
        if (m9.B2.c("secondaryColor", v0_0) == null) {
            v1_4.getString(2131955540);
        }
        if (m9.B2.c("titleColor", v0_0) == null) {
            v1_4.getString(2131955572);
        }
        if (m9.B2.c("backgroundColor", v0_0) == null) {
            v1_4.getString(2131955429);
        }
        if (m9.B2.c("textColor", v0_0) == null) {
            v1_4.getString(2131955571);
        }
        if (m9.B2.c("textButtonColor", v0_0) == null) {
            v1_4.getString(2131955570);
        }
        if (m9.B2.c("borderInputColor", v0_0) == null) {
            v1_4.getString(2131955431);
        }
        if (m9.B2.c("errorColor", v0_0) == null) {
            v1_4.getString(2131955469);
        }
        if (m9.B2.c("closeColor", v0_0) == null) {
            v1_4.getString(2131955437);
        }
        if (m9.B2.c("stepBarColor", v0_0) == null) {
            v1_4.getString(2131955568);
        }
        if (m9.B2.c("stepBarBackgroundColor", v0_0) == null) {
            v1_4.getString(2131955567);
        }
        if (m9.B2.c("fontName", v0_0) == null) {
            v1_4.getString(2131955482);
        }
        if (m9.B2.a("imageConfirmation", v0_0) == null) {
            v1_4.getBoolean(2131034131);
        }
        boolean v2_42;
        boolean v2_40 = m9.B2.a("isShowStepBar", v0_0);
        if (!v2_40) {
            v2_42 = v1_4.getBoolean(2131034135);
        } else {
            v2_42 = v2_40.booleanValue();
        }
        if (m9.B2.a("idGuide", v0_0) == null) {
            v1_4.getBoolean(2131034130);
        }
        if (m9.B2.a("isDisableSecurity", v0_0) == null) {
            v1_4.getBoolean(2131034132);
        }
        if (m9.B2.a("isShowSkipNFC", v0_0) == null) {
            v1_4.getBoolean(2131034134);
        }
        if (m9.B2.a("trueidWatermark", v0_0) == null) {
            v1_4.getBoolean(2131034138);
        }
        if (m9.B2.a("nfcSkipMaxRetry", v0_0) == null) {
            v1_4.getBoolean(2131034136);
        }
        if (m9.B2.a("returnNFC", v0_0) == null) {
            v1_4.getBoolean(2131034137);
        }
        if ((v0_0.has("nfcRequire")) && (!v0_0.isNull("nfcRequire"))) {
            v0_0.optInt("nfcRequire");
        }
        if (m9.B2.a("isAutoProcess", v0_0) == null) {
            v1_4.getBoolean(2131034128);
        }
        if (m9.B2.a("isShowCloseButton", v0_0) == null) {
            v1_4.getBoolean(2131034133);
        }
        m9.B2.a("isHideQRScan", v0_0);
        if (v0_0.has("maxRetry") != 1) {
            v1_4.getInteger(2131427395);
        } else {
            v0_0.optInt("maxRetry");
        }
        if ((v0_0.has("stepVerification") != 1) || (v0_0.isNull("stepVerification"))) {
            kotlin.collections.y.toList(v1_4.getStringArray(2130903066));
        } else {
            String[] v0_2 = v0_0.optJSONArray("stepVerification");
            if (v0_2 == null) {
                kotlin.collections.y.toList(v1_4.getStringArray(2130903066));
            } else {
                java.util.ArrayList v1_2 = new java.util.ArrayList();
                int v3_25 = v0_2.length();
                int v4_5 = 0;
                while (v4_5 < v3_25) {
                    v1_2.add(v0_2.optString(v4_5));
                    v4_5++;
                }
            }
        }
        return v2_42;
    }

    public final boolean isShowWatermark$trueid_release()
    {
        String[] v0_0 = vn.ai.trueid.sdk.navigation.TrueIDV3.d;
        java.util.ArrayList v1_4 = vn.ai.trueid.sdk.utils.Helpers.Companion.getContext().getResources();
        if (m9.B2.c("primaryColor", v0_0) == null) {
            v1_4.getString(2131955530);
        }
        if (m9.B2.c("secondaryColor", v0_0) == null) {
            v1_4.getString(2131955540);
        }
        if (m9.B2.c("titleColor", v0_0) == null) {
            v1_4.getString(2131955572);
        }
        if (m9.B2.c("backgroundColor", v0_0) == null) {
            v1_4.getString(2131955429);
        }
        if (m9.B2.c("textColor", v0_0) == null) {
            v1_4.getString(2131955571);
        }
        if (m9.B2.c("textButtonColor", v0_0) == null) {
            v1_4.getString(2131955570);
        }
        if (m9.B2.c("borderInputColor", v0_0) == null) {
            v1_4.getString(2131955431);
        }
        if (m9.B2.c("errorColor", v0_0) == null) {
            v1_4.getString(2131955469);
        }
        if (m9.B2.c("closeColor", v0_0) == null) {
            v1_4.getString(2131955437);
        }
        if (m9.B2.c("stepBarColor", v0_0) == null) {
            v1_4.getString(2131955568);
        }
        if (m9.B2.c("stepBarBackgroundColor", v0_0) == null) {
            v1_4.getString(2131955567);
        }
        if (m9.B2.c("fontName", v0_0) == null) {
            v1_4.getString(2131955482);
        }
        if (m9.B2.a("imageConfirmation", v0_0) == null) {
            v1_4.getBoolean(2131034131);
        }
        if (m9.B2.a("isShowStepBar", v0_0) == null) {
            v1_4.getBoolean(2131034135);
        }
        if (m9.B2.a("idGuide", v0_0) == null) {
            v1_4.getBoolean(2131034130);
        }
        if (m9.B2.a("isDisableSecurity", v0_0) == null) {
            v1_4.getBoolean(2131034132);
        }
        if (m9.B2.a("isShowSkipNFC", v0_0) == null) {
            v1_4.getBoolean(2131034134);
        }
        boolean v2_8;
        boolean v2_6 = m9.B2.a("trueidWatermark", v0_0);
        if (!v2_6) {
            v2_8 = v1_4.getBoolean(2131034138);
        } else {
            v2_8 = v2_6.booleanValue();
        }
        if (m9.B2.a("nfcSkipMaxRetry", v0_0) == null) {
            v1_4.getBoolean(2131034136);
        }
        if (m9.B2.a("returnNFC", v0_0) == null) {
            v1_4.getBoolean(2131034137);
        }
        if ((v0_0.has("nfcRequire")) && (!v0_0.isNull("nfcRequire"))) {
            v0_0.optInt("nfcRequire");
        }
        if (m9.B2.a("isAutoProcess", v0_0) == null) {
            v1_4.getBoolean(2131034128);
        }
        if (m9.B2.a("isShowCloseButton", v0_0) == null) {
            v1_4.getBoolean(2131034133);
        }
        m9.B2.a("isHideQRScan", v0_0);
        if (v0_0.has("maxRetry") != 1) {
            v1_4.getInteger(2131427395);
        } else {
            v0_0.optInt("maxRetry");
        }
        if ((v0_0.has("stepVerification") != 1) || (v0_0.isNull("stepVerification"))) {
            kotlin.collections.y.toList(v1_4.getStringArray(2130903066));
        } else {
            String[] v0_2 = v0_0.optJSONArray("stepVerification");
            if (v0_2 == null) {
                kotlin.collections.y.toList(v1_4.getStringArray(2130903066));
            } else {
                java.util.ArrayList v1_2 = new java.util.ArrayList();
                int v3_17 = v0_2.length();
                int v4_5 = 0;
                while (v4_5 < v3_17) {
                    v1_2.add(v0_2.optString(v4_5));
                    v4_5++;
                }
            }
        }
        return v2_8;
    }

    public final boolean isSupportScanNFC$trueid_release()
    {
        if (fk.b.f < 5) {
            return 0;
        } else {
            return 1;
        }
    }

    public final boolean isSupportScanQR$trueid_release()
    {
        boolean v0_0 = vn.ai.trueid.sdk.navigation.TrueIDV3.d;
        java.util.Date v1_8 = vn.ai.trueid.sdk.utils.Helpers.Companion.getContext().getResources();
        if (m9.B2.c("primaryColor", v0_0) == null) {
            v1_8.getString(2131955530);
        }
        if (m9.B2.c("secondaryColor", v0_0) == null) {
            v1_8.getString(2131955540);
        }
        if (m9.B2.c("titleColor", v0_0) == null) {
            v1_8.getString(2131955572);
        }
        if (m9.B2.c("backgroundColor", v0_0) == null) {
            v1_8.getString(2131955429);
        }
        if (m9.B2.c("textColor", v0_0) == null) {
            v1_8.getString(2131955571);
        }
        if (m9.B2.c("textButtonColor", v0_0) == null) {
            v1_8.getString(2131955570);
        }
        if (m9.B2.c("borderInputColor", v0_0) == null) {
            v1_8.getString(2131955431);
        }
        if (m9.B2.c("errorColor", v0_0) == null) {
            v1_8.getString(2131955469);
        }
        if (m9.B2.c("closeColor", v0_0) == null) {
            v1_8.getString(2131955437);
        }
        if (m9.B2.c("stepBarColor", v0_0) == null) {
            v1_8.getString(2131955568);
        }
        if (m9.B2.c("stepBarBackgroundColor", v0_0) == null) {
            v1_8.getString(2131955567);
        }
        if (m9.B2.c("fontName", v0_0) == null) {
            v1_8.getString(2131955482);
        }
        if (m9.B2.a("imageConfirmation", v0_0) == null) {
            v1_8.getBoolean(2131034131);
        }
        if (m9.B2.a("isShowStepBar", v0_0) == null) {
            v1_8.getBoolean(2131034135);
        }
        if (m9.B2.a("idGuide", v0_0) == null) {
            v1_8.getBoolean(2131034130);
        }
        if (m9.B2.a("isDisableSecurity", v0_0) == null) {
            v1_8.getBoolean(2131034132);
        }
        if (m9.B2.a("isShowSkipNFC", v0_0) == null) {
            v1_8.getBoolean(2131034134);
        }
        if (m9.B2.a("trueidWatermark", v0_0) == null) {
            v1_8.getBoolean(2131034138);
        }
        if (m9.B2.a("nfcSkipMaxRetry", v0_0) == null) {
            v1_8.getBoolean(2131034136);
        }
        if (m9.B2.a("returnNFC", v0_0) == null) {
            v1_8.getBoolean(2131034137);
        }
        if ((v0_0.has("nfcRequire")) && (!v0_0.isNull("nfcRequire"))) {
            v0_0.optInt("nfcRequire");
        }
        if (m9.B2.a("isAutoProcess", v0_0) == null) {
            v1_8.getBoolean(2131034128);
        }
        if (m9.B2.a("isShowCloseButton", v0_0) == null) {
            v1_8.getBoolean(2131034133);
        }
        String v2_25;
        String v2_24 = m9.B2.a("isHideQRScan", v0_0);
        if (v2_24 == null) {
            v2_25 = 0;
        } else {
            v2_25 = v2_24.booleanValue();
        }
        if (v0_0.has("maxRetry") != 1) {
            v1_8.getInteger(2131427395);
        } else {
            v0_0.optInt("maxRetry");
        }
        if ((v0_0.has("stepVerification") != 1) || (v0_0.isNull("stepVerification"))) {
            kotlin.collections.y.toList(v1_8.getStringArray(2130903066));
        } else {
            boolean v0_2 = v0_0.optJSONArray("stepVerification");
            if (!v0_2) {
                kotlin.collections.y.toList(v1_8.getStringArray(2130903066));
            } else {
                java.util.Date v1_2 = new java.util.ArrayList();
                int v4_3 = v0_2.length();
                int v5_3 = 0;
                while (v5_3 < v4_3) {
                    v1_2.add(v0_2.optString(v5_3));
                    v5_3++;
                }
            }
        }
        if (v2_25 == null) {
            if (fk.b.f < 8) {
                boolean v0_5 = fk.b.q;
                try {
                    if (v0_5) {
                        if (v0_5.getIssueDate().length() != 0) {
                            boolean v0_9 = new java.text.SimpleDateFormat("dd/MM/yyyy", java.util.Locale.getDefault());
                            java.util.Date v1_6 = v0_9.parse("01/07/2024");
                            boolean v0_10 = v0_9.parse(fk.b.q.getIssueDate());
                            if ((v0_10) && ((v1_6 != null) && (!v0_10.before(v1_6)))) {
                                return 1;
                            }
                        } else {
                            return 1;
                        }
                    }
                } catch (Exception) {
                }
                return 0;
            } else {
                return 1;
            }
        } else {
            return 0;
        }
    }

    public final boolean isTablet$trueid_release()
    {
        return vn.ai.trueid.sdk.navigation.TrueIDV3.u;
    }

    public final boolean isZlpSupported$trueid_release()
    {
        boolean v0_1 = vn.ai.trueid.sdk.utils.Helpers.Companion.getContext().getSharedPreferences("vendor_prefs", 0).getString("vendor_id", 0);
        if (!v0_1) {
            v0_1 = "TRUEID";
        }
        return kotlin.jvm.internal.Intrinsics.areEqual(v0_1, "zlp");
    }

    public final int maxRetry$trueid_release()
    {
        int v1 = 0;
        if (vn.ai.trueid.sdk.navigation.TrueIDV3.g == null) {
            String[] v0_4 = vn.ai.trueid.sdk.navigation.TrueIDV3.d;
            java.util.ArrayList v2_4 = vn.ai.trueid.sdk.utils.Helpers.Companion.getContext().getResources();
            if (m9.B2.c("primaryColor", v0_4) == null) {
                v2_4.getString(2131955530);
            }
            if (m9.B2.c("secondaryColor", v0_4) == null) {
                v2_4.getString(2131955540);
            }
            if (m9.B2.c("titleColor", v0_4) == null) {
                v2_4.getString(2131955572);
            }
            if (m9.B2.c("backgroundColor", v0_4) == null) {
                v2_4.getString(2131955429);
            }
            if (m9.B2.c("textColor", v0_4) == null) {
                v2_4.getString(2131955571);
            }
            if (m9.B2.c("textButtonColor", v0_4) == null) {
                v2_4.getString(2131955570);
            }
            if (m9.B2.c("borderInputColor", v0_4) == null) {
                v2_4.getString(2131955431);
            }
            if (m9.B2.c("errorColor", v0_4) == null) {
                v2_4.getString(2131955469);
            }
            if (m9.B2.c("closeColor", v0_4) == null) {
                v2_4.getString(2131955437);
            }
            if (m9.B2.c("stepBarColor", v0_4) == null) {
                v2_4.getString(2131955568);
            }
            if (m9.B2.c("stepBarBackgroundColor", v0_4) == null) {
                v2_4.getString(2131955567);
            }
            if (m9.B2.c("fontName", v0_4) == null) {
                v2_4.getString(2131955482);
            }
            if (m9.B2.a("imageConfirmation", v0_4) == null) {
                v2_4.getBoolean(2131034131);
            }
            if (m9.B2.a("isShowStepBar", v0_4) == null) {
                v2_4.getBoolean(2131034135);
            }
            if (m9.B2.a("idGuide", v0_4) == null) {
                v2_4.getBoolean(2131034130);
            }
            if (m9.B2.a("isDisableSecurity", v0_4) == null) {
                v2_4.getBoolean(2131034132);
            }
            if (m9.B2.a("isShowSkipNFC", v0_4) == null) {
                v2_4.getBoolean(2131034134);
            }
            if (m9.B2.a("trueidWatermark", v0_4) == null) {
                v2_4.getBoolean(2131034138);
            }
            if (m9.B2.a("nfcSkipMaxRetry", v0_4) == null) {
                v2_4.getBoolean(2131034136);
            }
            if (m9.B2.a("returnNFC", v0_4) == null) {
                v2_4.getBoolean(2131034137);
            }
            if ((v0_4.has("nfcRequire")) && (!v0_4.isNull("nfcRequire"))) {
                v0_4.optInt("nfcRequire");
            }
            if (m9.B2.a("isAutoProcess", v0_4) == null) {
                v2_4.getBoolean(2131034128);
            }
            if (m9.B2.a("isShowCloseButton", v0_4) == null) {
                v2_4.getBoolean(2131034133);
            }
            int v3_27;
            m9.B2.a("isHideQRScan", v0_4);
            if (v0_4.has("maxRetry") != 1) {
                v3_27 = v2_4.getInteger(2131427395);
            } else {
                v3_27 = v0_4.optInt("maxRetry");
            }
            if ((v0_4.has("stepVerification") != 1) || (v0_4.isNull("stepVerification"))) {
                kotlin.collections.y.toList(v2_4.getStringArray(2130903066));
            } else {
                String[] v0_2 = v0_4.optJSONArray("stepVerification");
                if (v0_2 == null) {
                    kotlin.collections.y.toList(v2_4.getStringArray(2130903066));
                } else {
                    java.util.ArrayList v2_1 = new java.util.ArrayList();
                    int v4_4 = v0_2.length();
                    while (v1 < v4_4) {
                        v2_1.add(v0_2.optString(v1));
                        v1++;
                    }
                }
            }
            return v3_27;
        } else {
            return 0;
        }
    }

    public final int nfcRequire$trueid_release()
    {
        String[] v0_0 = vn.ai.trueid.sdk.navigation.TrueIDV3.d;
        java.util.ArrayList v1_4 = vn.ai.trueid.sdk.utils.Helpers.Companion.getContext().getResources();
        if (m9.B2.c("primaryColor", v0_0) == null) {
            v1_4.getString(2131955530);
        }
        if (m9.B2.c("secondaryColor", v0_0) == null) {
            v1_4.getString(2131955540);
        }
        if (m9.B2.c("titleColor", v0_0) == null) {
            v1_4.getString(2131955572);
        }
        if (m9.B2.c("backgroundColor", v0_0) == null) {
            v1_4.getString(2131955429);
        }
        if (m9.B2.c("textColor", v0_0) == null) {
            v1_4.getString(2131955571);
        }
        if (m9.B2.c("textButtonColor", v0_0) == null) {
            v1_4.getString(2131955570);
        }
        if (m9.B2.c("borderInputColor", v0_0) == null) {
            v1_4.getString(2131955431);
        }
        if (m9.B2.c("errorColor", v0_0) == null) {
            v1_4.getString(2131955469);
        }
        if (m9.B2.c("closeColor", v0_0) == null) {
            v1_4.getString(2131955437);
        }
        if (m9.B2.c("stepBarColor", v0_0) == null) {
            v1_4.getString(2131955568);
        }
        if (m9.B2.c("stepBarBackgroundColor", v0_0) == null) {
            v1_4.getString(2131955567);
        }
        if (m9.B2.c("fontName", v0_0) == null) {
            v1_4.getString(2131955482);
        }
        if (m9.B2.a("imageConfirmation", v0_0) == null) {
            v1_4.getBoolean(2131034131);
        }
        if (m9.B2.a("isShowStepBar", v0_0) == null) {
            v1_4.getBoolean(2131034135);
        }
        if (m9.B2.a("idGuide", v0_0) == null) {
            v1_4.getBoolean(2131034130);
        }
        if (m9.B2.a("isDisableSecurity", v0_0) == null) {
            v1_4.getBoolean(2131034132);
        }
        if (m9.B2.a("isShowSkipNFC", v0_0) == null) {
            v1_4.getBoolean(2131034134);
        }
        if (m9.B2.a("trueidWatermark", v0_0) == null) {
            v1_4.getBoolean(2131034138);
        }
        if (m9.B2.a("nfcSkipMaxRetry", v0_0) == null) {
            v1_4.getBoolean(2131034136);
        }
        if (m9.B2.a("returnNFC", v0_0) == null) {
            v1_4.getBoolean(2131034137);
        }
        if ((!v0_0.has("nfcRequire")) || (v0_0.isNull("nfcRequire"))) {
            int v2_16 = 0;
        } else {
            v2_16 = Integer.valueOf(v0_0.optInt("nfcRequire"));
        }
        int v2_18;
        if (v2_16 == 0) {
            v2_18 = -1;
        } else {
            v2_18 = v2_16.intValue();
        }
        if (m9.B2.a("isAutoProcess", v0_0) == null) {
            v1_4.getBoolean(2131034128);
        }
        if (m9.B2.a("isShowCloseButton", v0_0) == null) {
            v1_4.getBoolean(2131034133);
        }
        m9.B2.a("isHideQRScan", v0_0);
        if (v0_0.has("maxRetry") != 1) {
            v1_4.getInteger(2131427395);
        } else {
            v0_0.optInt("maxRetry");
        }
        if ((v0_0.has("stepVerification") != 1) || (v0_0.isNull("stepVerification"))) {
            kotlin.collections.y.toList(v1_4.getStringArray(2130903066));
        } else {
            String[] v0_2 = v0_0.optJSONArray("stepVerification");
            if (v0_2 == null) {
                kotlin.collections.y.toList(v1_4.getStringArray(2130903066));
            } else {
                java.util.ArrayList v1_2 = new java.util.ArrayList();
                int v3_12 = v0_2.length();
                int v4_3 = 0;
                while (v4_3 < v3_12) {
                    v1_2.add(v0_2.optString(v4_3));
                    v4_3++;
                }
            }
        }
        return v2_18;
    }

    public final void releaseSession$trueid_release()
    {
        vn.ai.trueid.sdk.navigation.TrueIDV3.p = 0;
        return;
    }

    public final void scanNFCWithRAR(vn.ai.trueid.sdk.navigation.NFCListener p4, vn.ai.trueid.sdk.models.NFCInput p5, android.app.Activity p6, Ek.n p7)
    {
        this.getNfccccdReader().a(p6, p6.getResources().getInteger(2131427396), new vn.ai.trueid.sdk.navigation.TrueIDV3$scanNFCWithRAR$1(p7, p4, p6));
        this.getNfccccdReader().b(p5.getIdNumber());
        new android.os.Handler(android.os.Looper.getMainLooper()).postDelayed(new Kk.i(p7, p4, 0), 90000);
        return;
    }

    public final String securityMessage(int p2)
    {
        switch (p2) {
            case 450:
                return "Device is Rooted";
            case 451:
                return "Security Hook Detected";
            case 452:
                return "SDK does not support emulator";
            case 453:
                return "Security Environment Mismatch";
            case 454:
            case 455:
                return vn.ai.trueid.sdk.models.TrueIDVaultStatus.INSTANCE.message(p2);
            default:
                return "Security check failed";
        }
    }

    public final void setActivePushListener$trueid_release(vn.ai.trueid.sdk.navigation.TrueIdSmartOTPListener p1)
    {
        vn.ai.trueid.sdk.navigation.TrueIDV3.j = p1;
        return;
    }

    public final void setConfigEKYC(android.content.Context p3, vn.ai.trueid.sdk.models.EkycConfig p4)
    {
        vn.ai.trueid.sdk.navigation.TrueIDV3.u = p3.getResources().getBoolean(2131034122);
        boolean v0_3 = vn.ai.trueid.sdk.utils.Helpers.Companion;
        if (v0_3.validConfig(p4)) {
            v0_3.init(p3);
            try {
                vn.ai.trueid.sdk.navigation.TrueIDV3.t = 1;
                vn.ai.trueid.sdk.navigation.TrueIDV3.e = p4;
                return;
            } catch (boolean v3_4) {
                if (vn.ai.trueid.sdk.navigation.TrueIDV3.t) {
                    vn.ai.trueid.sdk.navigation.TrueIDV3.e = p4;
                }
                throw v3_4;
            }
        }
        return;
    }

    public final void setConfigHeader$trueid_release(org.json.JSONObject p1)
    {
        vn.ai.trueid.sdk.navigation.TrueIDV3.c = p1;
        return;
    }

    public final void setConfigUI$trueid_release(org.json.JSONObject p1)
    {
        vn.ai.trueid.sdk.navigation.TrueIDV3.d = p1;
        return;
    }

    public final void setDeviceInfo(vn.ai.trueid.sdk.models.DeviceInfo p1)
    {
        vn.ai.trueid.sdk.navigation.TrueIDV3.a = p1;
        return;
    }

    public final void setEkycCaptureListener$trueid_release(vn.ai.trueid.sdk.navigation.TrueIDEKYCCaptureListener p1)
    {
        vn.ai.trueid.sdk.navigation.TrueIDV3.m = p1;
        return;
    }

    public final void setEkycCaptureMode$trueid_release(boolean p1)
    {
        vn.ai.trueid.sdk.navigation.TrueIDV3.l = p1;
        return;
    }

    public final void setEkycConfig$trueid_release(vn.ai.trueid.sdk.models.EkycConfig p1)
    {
        vn.ai.trueid.sdk.navigation.TrueIDV3.e = p1;
        return;
    }

    public final void setEkycListener$trueid_release(vn.ai.trueid.sdk.navigation.TrueIDListener p1)
    {
        vn.ai.trueid.sdk.navigation.TrueIDV3.k = p1;
        return;
    }

    public final void setEnrollCaptureListener$trueid_release(vn.ai.trueid.sdk.navigation.TrueIDFaceEnrollCaptureListener p1)
    {
        vn.ai.trueid.sdk.navigation.TrueIDV3.o = p1;
        return;
    }

    public final void setEnrollCaptureMode$trueid_release(boolean p1)
    {
        vn.ai.trueid.sdk.navigation.TrueIDV3.n = p1;
        return;
    }

    public final void setEnrollListener$trueid_release(vn.ai.trueid.sdk.navigation.FaceEnrollListener p1)
    {
        vn.ai.trueid.sdk.navigation.TrueIDV3.r = p1;
        return;
    }

    public final void setFaceEnrollConfig$trueid_release(vn.ai.trueid.sdk.models.FaceEnrollConfig p1)
    {
        vn.ai.trueid.sdk.navigation.TrueIDV3.f = p1;
        return;
    }

    public final void setLanguageJSON(org.json.JSONObject p1)
    {
        vn.ai.trueid.sdk.navigation.TrueIDV3.b = p1;
        return;
    }

    public final void setNfcConfig$trueid_release(vn.ai.trueid.sdk.models.NFCConfig p1)
    {
        vn.ai.trueid.sdk.navigation.TrueIDV3.g = p1;
        return;
    }

    public final void setNfcListener$trueid_release(vn.ai.trueid.sdk.navigation.NFCListener p1)
    {
        vn.ai.trueid.sdk.navigation.TrueIDV3.q = p1;
        return;
    }

    public final void setSessionActive$trueid_release(boolean p1)
    {
        vn.ai.trueid.sdk.navigation.TrueIDV3.p = p1;
        return;
    }

    public final void setSmartOTPConfig$trueid_release(vn.ai.trueid.sdk.models.sotp.TrueIdSmartOTPConfig p1)
    {
        vn.ai.trueid.sdk.navigation.TrueIDV3.h = p1;
        return;
    }

    public final void setSmartOTPListener$trueid_release(vn.ai.trueid.sdk.navigation.TrueIDListener p1)
    {
        vn.ai.trueid.sdk.navigation.TrueIDV3.s = p1;
        return;
    }

    public final void setSmartOtpUIConfig$trueid_release(vn.ai.trueid.sdk.models.sotp.TrueIdSmartOTPUIConfig p1)
    {
        vn.ai.trueid.sdk.navigation.TrueIDV3.i = p1;
        return;
    }

    public final void setTablet$trueid_release(boolean p1)
    {
        vn.ai.trueid.sdk.navigation.TrueIDV3.u = p1;
        return;
    }
}
