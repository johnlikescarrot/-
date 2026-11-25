package vn.ai.trueid.sdk.activity;
public final class SotpConfirmActivity extends androidx.appcompat.app.AppCompatActivity {
    public static final synthetic int u;
    public android.os.CountDownTimer i;
    public android.widget.TextView j;
    public boolean k;
    public android.app.Dialog l;
    public Ai.f m;
    public String n;
    public boolean o;
    public android.widget.TextView p;
    public android.widget.TextView q;
    public android.widget.ProgressBar r;
    public android.widget.Button s;
    public String t;

    public SotpConfirmActivity()
    {
        return;
    }

    public static final void e1(vn.ai.trueid.sdk.activity.SotpConfirmActivity p2, javax.crypto.Mac p3)
    {
        StringBuilder v0_0 = vn.ai.trueid.sdk.networks.h0.a;
        if (v0_0 != null) {
            try {
                String v3_6 = vn.ai.trueid.sdk.networks.g0.g(p3.doFinal(((byte[]) v0_0.b)));
                p2.n = v3_6;
                p2.runOnUiThread(new ek.b(p2, v3_6, 1));
                return;
            } catch (String v3_1) {
                String v3_2 = v3_1.getMessage();
                StringBuilder v0_3 = new StringBuilder("L\u1ed7i t\u00ednh OTP: ");
                v0_3.append(v3_2);
                p2.q1(v0_3.toString());
                return;
            }
        } else {
            p2.l1(400, "No pending biometric authentication session found");
            return;
        }
    }

    public static final void g1(kotlin.jvm.internal.H p0, kotlin.jvm.internal.H p1, StringBuilder p2, vn.ai.trueid.sdk.models.sotp.TrueIdSmartOTPPinPolicy p3, android.widget.TextView p4, kotlin.jvm.internal.L p5, kotlin.jvm.internal.L p6, java.util.ArrayList p7, vn.ai.trueid.sdk.models.sotp.TrueIdSmartOTPUIConfig p8, vn.ai.trueid.sdk.activity.SotpConfirmActivity p9, int p10)
    {
        if ((!p0.a) && ((!p1.a) && (p2.length() < p3.getEffectiveMax()))) {
            p4.setText("");
            p2.append(p10);
            vn.ai.trueid.sdk.activity.SotpConfirmActivity.p1(p7, p2, p8, p9);
            ((kotlin.jvm.functions.Function0) p5.a).invoke();
            if (p2.length() == p3.getEffectiveMax()) {
                ((kotlin.jvm.functions.Function0) p6.a).invoke();
            }
        }
        return;
    }

    public static final void h1(kotlin.jvm.internal.H p7, StringBuilder p8, kotlin.jvm.internal.L p9, kotlin.jvm.internal.L p10, android.widget.TextView p11, kotlin.jvm.internal.H p12, android.widget.LinearLayout p13, kotlin.jvm.internal.L p14, java.util.ArrayList p15, vn.ai.trueid.sdk.models.sotp.TrueIdSmartOTPUIConfig p16, vn.ai.trueid.sdk.activity.SotpConfirmActivity p17, long p18, String p20)
    {
        p7.a = 0;
        kotlin.text.v.clear(p8);
        vn.ai.trueid.sdk.activity.SotpConfirmActivity.p1(p15, p8, p16, p17);
        ((kotlin.jvm.functions.Function0) p9.a).invoke();
        ((kotlin.jvm.functions.Function0) p10.a).invoke();
        if (p18 > 0) {
            p12.a = 1;
            p13.setAlpha(1053609165);
            android.os.CountDownTimer v0_4 = ((android.os.CountDownTimer) p14.a);
            if (v0_4 != null) {
                v0_4.cancel();
            }
            p14.a = new ek.k(p18, p13, p11, p20, p12).start();
            return;
        } else {
            p11.setText(p20);
            p11.setVisibility(0);
            return;
        }
    }

    public static final void n1(android.widget.LinearLayout p16, android.widget.TextView p17, StringBuilder p18, java.util.ArrayList p19, kotlin.jvm.internal.H p20, kotlin.jvm.internal.H p21, kotlin.jvm.internal.L p22, kotlin.jvm.internal.L p23, kotlin.jvm.internal.L p24, vn.ai.trueid.sdk.activity.SotpConfirmActivity p25, vn.ai.trueid.sdk.models.sotp.TrueIdSmartOTPPinPolicy p26, vn.ai.trueid.sdk.models.sotp.TrueIdSmartOTPUIConfig p27)
    {
        p16.removeAllViews();
        String v2_3 = ((java.util.List) p22.a);
        p16.addView(vn.ai.trueid.sdk.activity.SotpConfirmActivity.o1(p25, p27, kotlin.collections.E.listOf(new kotlin.Pair[] {kotlin.TuplesKt.to(String.valueOf(((Number) v2_3.get(0)).intValue()), new ek.i(v2_3, p20, p21, p18, p26, p17, p23, p24, p19, p27, p25, 1)), kotlin.TuplesKt.to(String.valueOf(((Number) v2_3.get(1)).intValue()), new ek.i(v2_3, p20, p21, p18, p26, p17, p23, p24, p19, p27, p25, 2)), kotlin.TuplesKt.to(String.valueOf(((Number) v2_3.get(2)).intValue()), new ek.i(v2_3, p20, p21, p18, p26, p17, p23, p24, p19, p27, p25, 3))}))));
        p16.addView(vn.ai.trueid.sdk.activity.SotpConfirmActivity.o1(p25, p27, kotlin.collections.E.listOf(new kotlin.Pair[] {kotlin.TuplesKt.to(String.valueOf(((Number) v2_3.get(3)).intValue()), new ek.i(v2_3, p20, p21, p18, p26, p17, p23, p24, p19, p27, p25, 4)), kotlin.TuplesKt.to(String.valueOf(((Number) v2_3.get(4)).intValue()), new ek.i(v2_3, p20, p21, p18, p26, p17, p23, p24, p19, p27, p25, 5)), kotlin.TuplesKt.to(String.valueOf(((Number) v2_3.get(5)).intValue()), new ek.i(v2_3, p20, p21, p18, p26, p17, p23, p24, p19, p27, p25, 6))}))));
        p16.addView(vn.ai.trueid.sdk.activity.SotpConfirmActivity.o1(p25, p27, kotlin.collections.E.listOf(new kotlin.Pair[] {kotlin.TuplesKt.to(String.valueOf(((Number) v2_3.get(6)).intValue()), new ek.i(v2_3, p20, p21, p18, p26, p17, p23, p24, p19, p27, p25, 7)), kotlin.TuplesKt.to(String.valueOf(((Number) v2_3.get(7)).intValue()), new ek.i(v2_3, p20, p21, p18, p26, p17, p23, p24, p19, p27, p25, 8)), kotlin.TuplesKt.to(String.valueOf(((Number) v2_3.get(8)).intValue()), new ek.i(v2_3, p20, p21, p18, p26, p17, p23, p24, p19, p27, p25, 9))}))));
        p16.addView(vn.ai.trueid.sdk.activity.SotpConfirmActivity.o1(p25, p27, kotlin.collections.E.listOf(new kotlin.Pair[] {kotlin.TuplesKt.to("", 0), kotlin.TuplesKt.to(String.valueOf(((Number) v2_3.get(9)).intValue()), new ek.i(v2_3, p20, p21, p18, p26, p17, p23, p24, p19, p27, p25, 0)), kotlin.TuplesKt.to("\u232b", new A0.b(p21, p18, p23, p19, p27, p25, 1))}))));
        return;
    }

    public static final android.widget.LinearLayout o1(vn.ai.trueid.sdk.activity.SotpConfirmActivity p9, vn.ai.trueid.sdk.models.sotp.TrueIdSmartOTPUIConfig p10, java.util.List p11)
    {
        android.widget.LinearLayout v0_1 = new android.widget.LinearLayout(p9);
        v0_1.setOrientation(0);
        java.util.Iterator v11_1 = p11.iterator();
        while (v11_1.hasNext()) {
            android.widget.Button v2_5;
            android.widget.Button v2_1 = ((kotlin.Pair) v11_1.next());
            int v3_1 = ((String) v2_1.component1());
            android.widget.Button v2_3 = ((kotlin.jvm.functions.Function0) v2_1.component2());
            android.widget.LinearLayout$LayoutParams v4_1 = new android.widget.LinearLayout$LayoutParams(0, p9.j1(56), 1065353216);
            v4_1.setMargins(p9.j1(6), p9.j1(6), p9.j1(6), p9.j1(6));
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
                v7_6.setCornerRadius(((float) p9.j1(v8_2)));
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
                v5_6.setOnClickListener(new Kk.h(1, v2_3));
                v2_5 = v5_6;
            } else {
                v2_5 = new android.view.View(p9);
            }
            v0_1.addView(v2_5, v4_1);
        }
        return v0_1;
    }

    public static final void p1(java.util.ArrayList p7, StringBuilder p8, vn.ai.trueid.sdk.models.sotp.TrueIdSmartOTPUIConfig p9, vn.ai.trueid.sdk.activity.SotpConfirmActivity p10)
    {
        int v0 = p7.size();
        int v1 = 0;
        while (v1 < v0) {
            int v5_2;
            android.view.View v2_1 = ((android.view.View) p7.get(v1));
            android.graphics.drawable.GradientDrawable v3_1 = new android.graphics.drawable.GradientDrawable();
            v3_1.setShape(1);
            if (v1 >= p8.length()) {
                try {
                    v5_2 = android.graphics.Color.parseColor(p9.getPinDotEmptyColor());
                } catch (Exception) {
                    v5_2 = -12303292;
                }
            } else {
                try {
                    v5_2 = android.graphics.Color.parseColor(p9.getPinDotFilledColor());
                } catch (Exception) {
                    v5_2 = -1;
                }
            }
            v3_1.setColor(v5_2);
            try {
            } catch (Exception) {
            }
            v3_1.setStroke(p10.j1(1), android.graphics.Color.parseColor(p9.getPinDotBorderColor()));
            v2_1.setBackground(v3_1);
            v1++;
        }
        return;
    }

    public final void i1(String p6)
    {
        long v0_0 = this.r;
        android.widget.Button v1 = 0;
        if (v0_0 == 0) {
            v0_0 = 0;
        }
        v0_0.setVisibility(8);
        long v0_6 = this.q;
        if (v0_6 == 0) {
            v0_6 = 0;
        }
        if (p6.length() == 8) {
            p6 = androidx.activity.result.e.o(p6.substring(0, 4), " ", p6.substring(4));
        }
        v0_6.setText(p6);
        android.os.CountDownTimer v6_2 = this.q;
        if (v6_2 == null) {
            v6_2 = 0;
        }
        v6_2.setVisibility(0);
        android.os.CountDownTimer v6_3 = this.p;
        if (v6_3 == null) {
            v6_3 = 0;
        }
        v6_3.setText("M\u00e3 x\u00e1c th\u1ef1c SmartOTP:");
        android.os.CountDownTimer v6_4 = this.s;
        if (v6_4 == null) {
            v6_4 = 0;
        }
        v6_4.setEnabled(1);
        android.os.CountDownTimer v6_5 = this.s;
        if (v6_5 != null) {
            v1 = v6_5;
        }
        v1.setAlpha(1065353216);
        if (this.i == null) {
            this.i = new Ae.P(((System.currentTimeMillis() + 120000) - System.currentTimeMillis()), this, 3).start();
            return;
        } else {
            return;
        }
    }

    public final int j1(int p2)
    {
        return ((int) (((float) p2) * this.getResources().getDisplayMetrics().density));
    }

    public final void k1(String p4)
    {
        Ai.f v1_1 = new Ai.f(this, androidx.core.content.c.c(this), new ek.d(p4, this));
        B8.a v4_3 = new B8.a();
        v4_3.a = this.getString(2131955565);
        v4_3.d = "Nh\u1eadp m\u1eadt kh\u1ea9u thi\u1ebft b\u1ecb \u0111\u1ec3 x\u00e1c th\u1ef1c";
        v4_3.c = 32768;
        B8.a v4_1 = v4_3.a();
        this.m = v1_1;
        v1_1.j(v4_1, 0);
        return;
    }

    public final void l1(int p10, String p11)
    {
        if (!this.k) {
            this.k = 1;
            String v0_9 = this.l;
            if ((v0_9 != null) && (v0_9.isShowing())) {
                v0_9.dismiss();
            }
            String v0_1 = this.m;
            if (v0_1 != null) {
                String v0_3 = ((androidx.fragment.app.n0) v0_1.c);
                if (v0_3 != null) {
                    String v0_6 = ((androidx.biometric.o) v0_3.E("androidx.biometric.BiometricFragment"));
                    if (v0_6 != null) {
                        v0_6.m1(3);
                    } else {
                        android.util.Log.e("BiometricPromptCompat", "Unable to cancel authentication. BiometricFragment not found.");
                    }
                } else {
                    android.util.Log.e("BiometricPromptCompat", "Unable to start authentication. Client fragment manager was null.");
                }
                this.m = 0;
            }
            String v3_2 = new vn.ai.trueid.sdk.models.sotp.TrueIdSmartOTPResult(p10, p11, 0, 4, 0);
            vn.ai.trueid.sdk.navigation.TrueIDV3 v10_1 = vn.ai.trueid.sdk.navigation.TrueIDV3.INSTANCE;
            vn.ai.trueid.sdk.navigation.TrueIdSmartOTPListener v11_1 = v10_1.getActivePushListener$trueid_release();
            if (v11_1 != null) {
                v11_1.onComplete(v3_2);
            }
            v10_1.setActivePushListener$trueid_release(0);
            this.finish();
            return;
        } else {
            return;
        }
    }

    public final void m1(String p8, String p9, AA.i p10)
    {
        int v0_0 = vn.ai.trueid.sdk.utils.SmartOTPHelper.vaultCheckDebugger();
        if (v0_0 == 0) {
            int v0_1 = vn.ai.trueid.sdk.utils.SmartOTPHelper.vaultCheckIntegrity();
            if (v0_1 == 0) {
                this.runOnUiThread(new F.k0(this, p10, p9, p8, 17));
                return;
            } else {
                this.l1(v0_1, "Security threat: Integrity scan failed");
                return;
            }
        } else {
            this.l1(v0_0, "Security threat: Debugger detected");
            return;
        }
    }

    public final void onBackPressed()
    {
        this.l1(-1, "User cancelled the transaction confirmation.");
        return;
    }

    public final void onCreate(android.os.Bundle p23)
    {
        super.onCreate(p23);
        this.getWindow().addFlags(8192);
        this.getIntent().getStringExtra("challengeId");
        kotlin.NoWhenBranchMatchedException v1_1 = this.getIntent().getStringExtra("txData");
        if (v1_1 == null) {
            v1_1 = "";
        }
        this.t = v1_1;
        this.getIntent().getStringExtra("username");
        this.getIntent().getLongExtra("expiresAt", (System.currentTimeMillis() + 120000));
        try {
            ng.T v3_3 = this.t;
        } catch (Exception) {
            this.l1(400, "Invalid transaction data JSON format");
            return;
        }
        if (v3_3 == null) {
            v3_3 = 0;
        }
        android.graphics.Typeface v14_3;
        kotlin.NoWhenBranchMatchedException v1_11 = vn.ai.trueid.sdk.models.sotp.TransactionDisplayModel.Companion.fromJson(v3_3);
        ng.T v3_17 = vn.ai.trueid.sdk.navigation.TrueIDV3.INSTANCE.getSmartOtpUIConfig$trueid_release();
        android.widget.LinearLayout v5_3 = new android.widget.LinearLayout(this);
        String v6_9 = 1;
        v5_3.setOrientation(1);
        v5_3.setGravity(1);
        v5_3.setBackground(new android.graphics.drawable.GradientDrawable(android.graphics.drawable.GradientDrawable$Orientation.TOP_BOTTOM, new int[] {android.graphics.Color.parseColor(v3_17.getBackgroundColor()), android.graphics.Color.parseColor(v3_17.getBackgroundColor())})));
        v5_3.setPadding(this.j1(24), this.j1(32), this.j1(24), this.j1(32));
        int v8_24 = new android.widget.LinearLayout(this);
        v8_24.setOrientation(0);
        int v10_13 = new android.widget.LinearLayout$LayoutParams(-1, -2);
        v10_13.bottomMargin = this.j1(24);
        v8_24.setLayoutParams(v10_13);
        v8_24.setGravity(16);
        Integer v13_24 = new android.widget.TextView(this);
        v13_24.setText(v1_11.getTransactionType().getDisplayTitle());
        v13_24.setTextColor(android.graphics.Color.parseColor(v3_17.getTitleColor()));
        v13_24.setTextSize(1101004800);
        if (v3_17.getFontName().length() <= 0) {
            v14_3 = android.graphics.Typeface.create("sans-serif-medium", 1);
        } else {
            try {
                v14_3 = android.graphics.Typeface.create(v3_17.getFontName(), 1);
            } catch (Exception) {
                v14_3 = android.graphics.Typeface.create("sans-serif-medium", 1);
            }
        }
        android.graphics.Typeface v14_11;
        int v16_2;
        v13_24.setTypeface(v14_3);
        v13_24.setLayoutParams(new android.widget.LinearLayout$LayoutParams(0, -2, 1065353216));
        v8_24.addView(v13_24);
        Integer v13_1 = new android.widget.TextView(this);
        v13_1.setText("[\u23f1 02:00]");
        v13_1.setTextColor(android.graphics.Color.parseColor(v3_17.getTimerColor()));
        v13_1.setTextSize(1097859072);
        if (v3_17.getFontName().length() <= 0) {
            v16_2 = 1097859072;
            v14_11 = android.graphics.Typeface.create("sans-serif-medium", 1);
        } else {
            v16_2 = 1097859072;
            try {
                v14_11 = android.graphics.Typeface.create(v3_17.getFontName(), 1);
            } catch (Exception) {
                v14_11 = android.graphics.Typeface.create("sans-serif-medium", 1);
            }
        }
        Integer v13_13;
        v13_1.setTypeface(v14_11);
        v13_1.setGravity(8388613);
        this.j = v13_1;
        v8_24.addView(v13_1);
        v5_3.addView(v8_24);
        int v8_1 = new android.view.View(this);
        Integer v13_3 = new android.widget.LinearLayout$LayoutParams(-1, this.j1(3));
        v13_3.bottomMargin = this.j1(20);
        v8_1.setLayoutParams(v13_3);
        v8_1.setBackgroundColor(android.graphics.Color.parseColor(v3_17.getAccentBarColor()));
        v5_3.addView(v8_1);
        int v8_3 = new android.widget.LinearLayout(this);
        v8_3.setOrientation(1);
        v8_3.setGravity(1);
        Integer v13_7 = new android.widget.LinearLayout$LayoutParams(-1, -2);
        v13_7.bottomMargin = this.j1(16);
        v8_3.setLayoutParams(v13_7);
        Integer v13_9 = new android.graphics.drawable.GradientDrawable();
        v13_9.setColor(android.graphics.Color.parseColor(v3_17.getCardBackgroundColor()));
        v13_9.setCornerRadius(((float) this.j1(v3_17.getCornerRadius())));
        v13_9.setStroke(this.j1(1), android.graphics.Color.parseColor(v3_17.getCardBorderColor()));
        v8_3.setBackground(v13_9);
        v8_3.setPadding(this.j1(16), this.j1(16), this.j1(16), this.j1(16));
        android.widget.LinearLayout v7_6 = new android.widget.TextView(this);
        v7_6.setText("\u0110ang x\u00e1c th\u1ef1c sinh tr\u1eafc h\u1ecdc...");
        v7_6.setTextColor(android.graphics.Color.parseColor(v3_17.getOtpStatusColor()));
        v7_6.setTextSize(1095761920);
        if (v3_17.getFontName().length() <= 0) {
            v13_13 = android.graphics.Typeface.create("sans-serif", 0);
        } else {
            try {
                v13_13 = android.graphics.Typeface.create(v3_17.getFontName(), 0);
            } catch (Exception) {
                v13_13 = android.graphics.Typeface.create("sans-serif", 0);
            }
        }
        ek.a v2_7;
        v7_6.setTypeface(v13_13);
        v7_6.setGravity(1);
        Integer v13_16 = new android.widget.LinearLayout$LayoutParams(-1, -2);
        v13_16.bottomMargin = this.j1(8);
        v7_6.setLayoutParams(v13_16);
        this.p = v7_6;
        v8_3.addView(v7_6);
        android.widget.LinearLayout v7_8 = new android.widget.FrameLayout(this);
        v7_8.setLayoutParams(new android.widget.LinearLayout$LayoutParams(-2, -2));
        Float v9_4 = new android.widget.ProgressBar(this, 0, 16842873);
        int v4_1 = new android.widget.FrameLayout$LayoutParams(this.j1(24), this.j1(24));
        v4_1.gravity = 17;
        v9_4.setLayoutParams(v4_1);
        this.r = v9_4;
        v7_8.addView(v9_4);
        int v4_3 = new android.widget.TextView(this);
        v4_3.setText("");
        v4_3.setTextColor(android.graphics.Color.parseColor(v3_17.getOtpCodeColor()));
        v4_3.setTextSize(1107296256);
        if (v3_17.getFontName().length() <= 0) {
            v2_7 = android.graphics.Typeface.create("monospace", 1);
        } else {
            try {
                v2_7 = android.graphics.Typeface.create(v3_17.getFontName(), 1);
            } catch (Exception) {
                v2_7 = android.graphics.Typeface.create("monospace", 1);
            }
        }
        v4_3.setTypeface(v2_7);
        v4_3.setGravity(17);
        v4_3.setVisibility(8);
        ek.a v2_10 = new android.widget.FrameLayout$LayoutParams(-2, -2);
        v2_10.gravity = 17;
        v4_3.setLayoutParams(v2_10);
        this.q = v4_3;
        v7_8.addView(v4_3);
        v8_3.addView(v7_8);
        v5_3.addView(v8_3);
        ek.a v2_12 = new android.widget.LinearLayout(this);
        v2_12.setOrientation(1);
        int v4_5 = new android.widget.LinearLayout$LayoutParams(-1, 0, 1065353216);
        v4_5.bottomMargin = this.j1(24);
        v2_12.setLayoutParams(v4_5);
        int v4_7 = new android.graphics.drawable.GradientDrawable();
        v4_7.setColor(android.graphics.Color.parseColor(v3_17.getCardBackgroundColor()));
        v4_7.setCornerRadius(((float) this.j1(v3_17.getCornerRadius())));
        v4_7.setStroke(this.j1(1), android.graphics.Color.parseColor(v3_17.getCardBorderColor()));
        v2_12.setBackground(v4_7);
        v2_12.setPadding(this.j1(20), this.j1(24), this.j1(20), this.j1(24));
        int v4_12 = new android.widget.ScrollView(this);
        v4_12.setLayoutParams(new android.widget.LinearLayout$LayoutParams(-1, -1));
        v4_12.setVerticalScrollBarEnabled(0);
        android.widget.LinearLayout v7_21 = new android.widget.LinearLayout(this);
        v7_21.setOrientation(1);
        kotlin.NoWhenBranchMatchedException v1_8 = v1_11.getFields().iterator();
        while (v1_8.hasNext()) {
            int v21_1;
            android.widget.Button v20;
            int v8_16 = ((vn.ai.trueid.sdk.models.sotp.DisplayField) v1_8.next());
            int v12_1 = Float.valueOf(v16_2);
            Integer v13_21 = Integer.valueOf(0);
            int v10_6 = Integer.valueOf(v6_9);
            if (v8_16.getValue().length() != 0) {
                Float v9_19;
                vn.ai.trueid.sdk.models.sotp.TrueIdSmartOTPUIConfig v17_3 = vn.ai.trueid.sdk.navigation.TrueIDV3.INSTANCE.getSmartOtpUIConfig$trueid_release();
                int v11_3 = new android.widget.LinearLayout(this);
                v11_3.setOrientation(v6_9);
                v20 = v1_8;
                v11_3.setLayoutParams(new android.widget.LinearLayout$LayoutParams(-1, -2));
                kotlin.NoWhenBranchMatchedException v1_16 = new android.widget.TextView(this);
                v1_16.setText(v8_16.getLabel());
                v1_16.setTextColor(android.graphics.Color.parseColor(v17_3.getLabelColor()));
                v1_16.setTextSize(1095761920);
                if (v17_3.getFontName().length() <= 0) {
                    v9_19 = android.graphics.Typeface.create("sans-serif", 0);
                } else {
                    try {
                        String v6_16 = 0;
                    } catch (Exception) {
                        v6_16 = 0;
                        v9_19 = android.graphics.Typeface.create("sans-serif", v6_16);
                    } catch (Exception) {
                    }
                    v9_19 = android.graphics.Typeface.create(v17_3.getFontName(), 0);
                }
                ng.T v3_32;
                v1_16.setTypeface(v9_19);
                v21_1 = v3_17;
                String v6_18 = new android.widget.LinearLayout$LayoutParams(-1, -2);
                v6_18.bottomMargin = this.j1(4);
                v1_16.setLayoutParams(v6_18);
                v11_3.addView(v1_16);
                kotlin.NoWhenBranchMatchedException v1_18 = new android.widget.TextView(this);
                v1_18.setText(v8_16.getValue());
                String v6_22 = ek.c.a[v8_16.getStyle().ordinal()];
                if (v6_22 == 1) {
                    v3_32 = new ng.T(v17_3.getValueColor(), v12_1, v13_21);
                } else {
                    if (v6_22 == 2) {
                        v3_32 = new ng.T(v17_3.getOtpCodeColor(), Float.valueOf(1099956224), v10_6);
                    } else {
                        if (v6_22 == 3) {
                            v3_32 = new ng.T("#FFB300", v12_1, v10_6);
                        } else {
                            if (v6_22 == 4) {
                                v3_32 = new ng.T("#FF5252", v12_1, v10_6);
                            } else {
                                if (v6_22 != 5) {
                                    throw new kotlin.NoWhenBranchMatchedException();
                                } else {
                                    v3_32 = new ng.T(v17_3.getLabelColor(), Float.valueOf(1094713344), v13_21);
                                }
                            }
                        }
                    }
                }
                ng.T v3_41;
                String v6_29 = ((String) v3_32.a);
                Float v9_30 = ((Number) v3_32.b).floatValue();
                ng.T v3_40 = ((Number) v3_32.c).intValue();
                v1_18.setTextColor(android.graphics.Color.parseColor(v6_29));
                v1_18.setTextSize(v9_30);
                if (v17_3.getFontName().length() <= 0) {
                    v3_41 = android.graphics.Typeface.create("sans-serif-medium", v3_40);
                } else {
                    try {
                        v3_41 = android.graphics.Typeface.create(v17_3.getFontName(), v3_40);
                    } catch (Exception) {
                        v3_41 = android.graphics.Typeface.create("sans-serif-medium", v3_41);
                    }
                }
                v1_18.setTypeface(v3_41);
                v1_18.setLayoutParams(new android.widget.LinearLayout$LayoutParams(-1, -2));
                v11_3.addView(v1_18);
                kotlin.NoWhenBranchMatchedException v1_22 = new android.view.View(this);
                ng.T v3_45 = new android.widget.LinearLayout$LayoutParams(-1, this.j1(1));
                v3_45.topMargin = this.j1(12);
                v3_45.bottomMargin = this.j1(12);
                v1_22.setLayoutParams(v3_45);
                v1_22.setBackgroundColor(android.graphics.Color.parseColor(v17_3.getCardBorderColor()));
                v7_21.addView(v11_3);
                v7_21.addView(v1_22);
            } else {
                v20 = v1_8;
                v21_1 = v3_17;
                Float v9 = -2;
            }
            v1_8 = v20;
            v3_17 = v21_1;
            v6_9 = 1;
            int v11 = 17;
        }
        ng.T v3_6;
        int v21_0 = v3_17;
        v4_12.addView(v7_21);
        v2_12.addView(v4_12);
        v5_3.addView(v2_12);
        kotlin.NoWhenBranchMatchedException v1_10 = new android.widget.Button(this);
        v1_10.setText(this.getString(2131955561));
        v1_10.setAllCaps(0);
        v1_10.setTextColor(android.graphics.Color.parseColor(v21_0.getConfirmButtonTextColor()));
        v1_10.setTextSize(1098907648);
        if (v21_0.getFontName().length() <= 0) {
            v3_6 = android.graphics.Typeface.create("sans-serif-medium", 1);
        } else {
            try {
                int v8_11 = 1;
            } catch (Exception) {
                v8_11 = 1;
                v3_6 = android.graphics.Typeface.create("sans-serif-medium", v8_11);
            } catch (Exception) {
            }
            v3_6 = android.graphics.Typeface.create(v21_0.getFontName(), 1);
        }
        ek.a v2_22;
        v1_10.setTypeface(v3_6);
        v1_10.setGravity(17);
        ng.T v3_10 = new android.widget.LinearLayout$LayoutParams(-1, this.j1(52));
        v3_10.bottomMargin = this.j1(12);
        v1_10.setLayoutParams(v3_10);
        ng.T v3_12 = new android.graphics.drawable.GradientDrawable();
        v3_12.setColor(android.graphics.Color.parseColor(v21_0.getConfirmButtonColor()));
        v3_12.setCornerRadius(((float) this.j1(v21_0.getCornerRadius())));
        v1_10.setBackground(v3_12);
        v1_10.setEnabled(0);
        v1_10.setAlpha(1056964608);
        v1_10.setOnClickListener(new ek.a(this, 0));
        this.s = v1_10;
        v5_3.addView(v1_10);
        kotlin.NoWhenBranchMatchedException v1_13 = new android.widget.Button(this);
        v1_13.setText(this.getString(2131955560));
        Float v9_12 = 0;
        v1_13.setAllCaps(0);
        v1_13.setTextColor(android.graphics.Color.parseColor(v21_0.getCancelButtonTextColor()));
        v1_13.setTextSize(1098907648);
        if (v21_0.getFontName().length() <= 0) {
            v2_22 = android.graphics.Typeface.create("sans-serif-medium", 0);
        } else {
            try {
                v2_22 = android.graphics.Typeface.create(v21_0.getFontName(), 0);
            } catch (Exception) {
                v2_22 = android.graphics.Typeface.create("sans-serif-medium", 0);
            }
        }
        v1_13.setTypeface(v2_22);
        v1_13.setGravity(17);
        v1_13.setLayoutParams(new android.widget.LinearLayout$LayoutParams(-1, this.j1(52)));
        ek.a v2_27 = new android.graphics.drawable.GradientDrawable();
        v2_27.setColor(0);
        v2_27.setCornerRadius(((float) this.j1(v21_0.getCornerRadius())));
        v2_27.setStroke(this.j1(1), android.graphics.Color.parseColor(v21_0.getCancelButtonBorderColor()));
        v1_13.setBackground(v2_27);
        if (!v21_0.isShowCloseButton()) {
            v9_12 = 8;
        }
        v1_13.setVisibility(v9_12);
        v1_13.setOnClickListener(new ek.a(this, 1));
        v5_3.addView(v1_13);
        this.setContentView(v5_3);
        return;
    }

    public final void onDestroy()
    {
        android.os.CountDownTimer v0 = this.i;
        if (v0 != null) {
            v0.cancel();
        }
        super.onDestroy();
        return;
    }

    public final void onResume()
    {
        super.onResume();
        if ((this.n == null) && (!this.o)) {
            B8.a v0_17 = 1;
            this.o = 1;
            this.r1();
            int v1_10 = vn.ai.trueid.sdk.networks.h0.a;
            if (v1_10 != 0) {
                O5.b v2_3 = vn.ai.trueid.sdk.navigation.TrueIDV3.INSTANCE.getSmartOtpConfig$trueid_release();
                String v3_5 = this.getIntent().getStringExtra("credentialId");
                if (v3_5 == null) {
                    v3_5 = ((String) v1_10.g);
                    if (v3_5 == null) {
                        v3_5 = "";
                    }
                }
                Ai.f v5_3;
                Ai.f v5_2 = this.getSharedPreferences("sotp_pin_seed_store", 0);
                if (!v5_2.contains("sotp_type_".concat(v3_5))) {
                    v5_3 = 0;
                } else {
                    v5_3 = Integer.valueOf(v5_2.getInt("sotp_type_".concat(v3_5), 1));
                }
                O5.b v2_7;
                if (v5_3 == null) {
                    v2_7 = v2_3.getSotpType();
                } else {
                    v2_7 = v5_3.intValue();
                }
                if (v2_7 != 1) {
                    v0_17 = 0;
                }
                if (v2_7 != 2) {
                    Ai.f v5_7;
                    O5.b v2_9 = new kotlin.jvm.internal.L();
                    if (v3_5.length() <= 0) {
                        v5_7 = 0;
                    } else {
                        v5_7 = v3_5;
                    }
                    v2_9.a = v5_7;
                    if (v5_7 == null) {
                        Ai.f v5_9 = java.security.KeyStore.getInstance("AndroidKeyStore");
                        v5_9.load(0);
                        Ai.f v5_10 = v5_9.aliases();
                        while (v5_10.hasMoreElements()) {
                            CS.b v6_6 = ((String) v5_10.nextElement());
                            if ((!kotlin.jvm.internal.Intrinsics.areEqual(v6_6, "wrappingKeyAlias")) && ((!kotlin.jvm.internal.Intrinsics.areEqual(v6_6, "wrappingKeyAlias")) && (!kotlin.jvm.internal.Intrinsics.areEqual(v6_6, "hmacKeyAlias")))) {
                                v2_9.a = v6_6;
                                break;
                            }
                        }
                    }
                    Ai.f v5_11 = v2_9.a;
                    if (v5_11 != null) {
                        Ai.f v5_13 = vn.ai.trueid.sdk.utils.SmartOTPHelper.g(((String) v5_11));
                        if (v5_13 != null) {
                            if (vn.ai.trueid.sdk.utils.SmartOTPHelper.l(this)) {
                                B8.a v0_1;
                                int v1_12 = javax.crypto.Mac.getInstance("HmacSHA256");
                                v1_12.init(v5_13);
                                Ai.f v5_1 = new Ai.f(this, androidx.core.content.c.c(this), new CS.b(this, v0_17, v3_5, v2_9));
                                O5.b v2_2 = new B8.a();
                                v2_2.a = this.getString(2131955565);
                                v2_2.d = this.getString(2131955559);
                                if (v0_17 == null) {
                                    v0_1 = "D\u00f9ng m\u1eadt kh\u1ea9u";
                                } else {
                                    v0_1 = "D\u00f9ng m\u00e3 PIN";
                                }
                                v2_2.f = v0_1;
                                B8.a v0_5 = v2_2.a();
                                this.m = v5_1;
                                v5_1.i(v0_5, new O5.b(v1_12));
                                return;
                            } else {
                                if ((v0_17 == null) || (this.getSharedPreferences("sotp_pin_seed_store", 0).getString("pin_seed_".concat(v3_5), 0) == null)) {
                                    try {
                                        if (com.facebook.applinks.b.w(this).m(32768) != 0) {
                                            this.l1(423, "Thi\u1ebft b\u1ecb ch\u01b0a b\u1eadt sinh tr\u1eafc h\u1ecdc m\u1ea1nh (v\u00e2n tay) ho\u1eb7c kho\u00e1 m\u00e0n h\u00ecnh. Vui l\u00f2ng b\u1eadt v\u00e2n tay/kho\u00e1 m\u00e0n h\u00ecnh \u0111\u1ec3 x\u00e1c th\u1ef1c.");
                                        } else {
                                            this.k1(((String) v2_9.a));
                                            return;
                                        }
                                    } catch (Exception) {
                                    }
                                } else {
                                    this.m1(v3_5, 0, 0);
                                    return;
                                }
                            }
                        } else {
                            this.l1(404, "HMAC credentials not found inside KeyStore");
                            return;
                        }
                    } else {
                        this.l1(404, "Security key credentials not found");
                        return;
                    }
                } else {
                    this.m1(v3_5, 0, 0);
                    return;
                }
            } else {
                this.l1(400, "No pending biometric authentication session found");
                return;
            }
        }
        return;
    }

    public final void q1(String p4)
    {
        int v0_0 = this.r;
        android.widget.Button v1 = 0;
        if (v0_0 == 0) {
            v0_0 = 0;
        }
        v0_0.setVisibility(8);
        int v0_3 = this.q;
        if (v0_3 == 0) {
            v0_3 = 0;
        }
        v0_3.setVisibility(8);
        int v0_1 = this.p;
        if (v0_1 == 0) {
            v0_1 = 0;
        }
        v0_1.setText(p4);
        int v4_1 = this.s;
        if (v4_1 == 0) {
            v4_1 = 0;
        }
        v4_1.setEnabled(0);
        int v4_2 = this.s;
        if (v4_2 != 0) {
            v1 = v4_2;
        }
        v1.setAlpha(1056964608);
        return;
    }

    public final void r1()
    {
        int v0_0 = this.r;
        android.widget.Button v1 = 0;
        if (v0_0 == 0) {
            v0_0 = 0;
        }
        v0_0.setVisibility(0);
        int v0_5 = this.q;
        if (v0_5 == 0) {
            v0_5 = 0;
        }
        v0_5.setVisibility(8);
        int v0_1 = this.p;
        if (v0_1 == 0) {
            v0_1 = 0;
        }
        v0_1.setText("\u0110ang x\u00e1c th\u1ef1c sinh tr\u1eafc h\u1ecdc...");
        int v0_2 = this.s;
        if (v0_2 == 0) {
            v0_2 = 0;
        }
        v0_2.setEnabled(0);
        int v0_3 = this.s;
        if (v0_3 != 0) {
            v1 = v0_3;
        }
        v1.setAlpha(1056964608);
        return;
    }
}
