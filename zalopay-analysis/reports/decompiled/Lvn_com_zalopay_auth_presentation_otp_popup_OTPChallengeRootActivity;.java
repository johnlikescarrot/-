package vn.com.zalopay.auth.presentation.otp.popup;
public final class OTPChallengeRootActivity extends androidx.appcompat.app.AppCompatActivity implements iY.b$a {
    public static final synthetic int v;
    public AC.c i;
    public Pt.n j;
    public KG.a k;
    public com.airbnb.lottie.network.g l;
    public RS.u m;
    public String n;
    public final ng.S o;
    public final Object p;
    public final int q;
    public java.util.Map r;
    public boolean s;
    public final ng.S t;
    public final ji.u0 u;

    public OTPChallengeRootActivity()
    {
        this.n = "default";
        this.o = ng.x.b(new RS.b(this, 0));
        this.p = new Object();
        this.q = ZS.a.b.incrementAndGet();
        this.t = ng.x.b(new RS.b(this, 1));
        this.u = ji.j0.c(Boolean.FALSE);
        return;
    }

    public static final void e1(vn.com.zalopay.auth.presentation.otp.popup.OTPChallengeRootActivity p3, QS.a p4)
    {
        if ((!p3.s) && (QS.h.e(p3.g1()))) {
            int v0_1 = new android.content.Intent(p3, vn.com.zalopay.auth.presentation.otp.OTPChallengeActivity);
            v0_1.putExtra("otp_created_request_id", p4.a);
            v0_1.putExtra("otp_created_phone", p4.b);
            v0_1.putExtra("otp_created_default_provider", p4.c);
            v0_1.putExtra("otp_created_allow_providers", kotlin.collections.CollectionsKt.toIntArray(((java.util.Collection) p4.d)));
            v0_1.putExtra("otp_created_otp_ui", p4.e.a);
            OS.P v4_7 = v0_1.putExtra("source_id", p3.n).putExtra("extra_params", p3.getIntent().getStringExtra("extra_params")).putExtra("trans_id", p3.n).putExtra("otp_launch_id", p3.getIntent().getStringExtra("otp_launch_id"));
            p3.s = 1;
            try {
                p3.startActivity(v4_7);
                p3.finish();
                p3.overridePendingTransition(0, 0);
                return;
            } catch (OS.P v4_8) {
                Object[] v2_4 = new Object[0];
                ck.b.c(v4_8, "[OTPChallenge] could not open the legacy OTP screen", v2_4);
                p3.s = 0;
                p3.h1(OS.P.b);
            }
        }
        return;
    }

    public final void d1(String p4)
    {
        RS.x v0_4;
        if (kotlin.text.StringsKt.isBlank(p4)) {
            v0_4 = "01.8200.013";
        } else {
            v0_4 = "01.8200.012";
        }
        int v1 = this.m;
        RS.u v2 = 0;
        if (v1 == 0) {
            v1 = 0;
        }
        RS.u.t0(v1, v0_4);
        RS.x v0_1 = this.m;
        if (v0_1 != null) {
            v2 = v0_1;
        }
        v2.p0(new RS.x(p4));
        return;
    }

    public final String g1()
    {
        return ((String) this.o.getValue());
    }

    public final void h1(OS.P p2)
    {
        this.i1(p2);
        this.finish();
        this.overridePendingTransition(0, 17432577);
        return;
    }

    public final void i1(OS.P p4)
    {
        if (!this.s) {
            this.s = 1;
            OS.P v0_6 = this.m;
            RS.u v1 = 0;
            if (v0_6 == null) {
                v0_6 = 0;
            }
            int v2_5 = v0_6.l;
            v0_6.l = 0;
            if (v2_5 != 0) {
                OS.P v0_1 = q9.y6.h(v2_5);
                if (v0_1 != null) {
                    int v2_0 = this.k;
                    if (v2_0 == 0) {
                        v2_0 = 0;
                    }
                    v2_0.g(this, v0_1);
                }
            }
            OS.P v0_2 = this.g1();
            int v2_1 = this.m;
            if (v2_1 == 0) {
                v2_1 = 0;
            }
            if ((m9.b3.e(p4, v0_2, ((RS.J) v2_1.h0()).a)) && (p4 == OS.P.a)) {
                RS.u v4_1 = this.m;
                if (v4_1 != null) {
                    v1 = v4_1;
                }
                v1.n0();
            }
        }
        return;
    }

    public final void onActivityResult(int p2, int p3, android.content.Intent p4)
    {
        super.onActivityResult(p2, p3, p4);
        ((iY.b) this.t.getValue()).onActivityResult(p2, p3, p4);
        if (p2 == 1510) {
            int v2_1 = this.m;
            if (v2_1 == 0) {
                v2_1 = 0;
            }
            RS.u.t0(v2_1, "01.8200.011");
        }
        return;
    }

    public final void onCreate(android.os.Bundle p28)
    {
        Integer v1_20 = n9.O.a;
        RS.w v0_44 = new androidx.camera.camera2.impl.a(21, v1_20);
        this.i = new AC.c(new WH.e(v0_44, new gS.d(v0_44, 1), 15), new gS.e(v0_44, new gS.d(v0_44, 2), new gS.d(v0_44, 0), 0), 13);
        this.j = v1_20.h();
        Integer v1_17 = v1_20.b();
        p9.n7.c(v1_17);
        this.k = v1_17;
        RS.w v0_29 = ((gS.a) v0_44.b);
        this.l = new com.airbnb.lottie.network.g(v0_29.i(), v0_29.j(), 20);
        void v27_1 = super.onCreate(p28);
        this.overridePendingTransition(0, 0);
        p9.U.a(this.getWindow(), 0);
        RS.w v0_45 = this.getIntent().getStringExtra("source_id");
        if (v0_45 == null) {
            v0_45 = "default";
        }
        this.n = v0_45;
        if (QS.h.a(this.p, this.g1())) {
            RS.w v0_48 = this.g1();
            try {
                String v2_28 = QS.h.b;
            } catch (RS.w v0_49) {
                throw v0_49;
            }
            if ((v2_28 == null) || (!kotlin.jvm.internal.Intrinsics.areEqual(v2_28.a, v0_48))) {
                v2_28 = 0;
            }
            if (v2_28 == null) {
                throw new IllegalArgumentException("Required value was null.");
            } else {
                String v2_30;
                RS.w v0_53 = new Cx.h(4, this);
                Integer v1_34 = v2_28.b;
                if (!(v2_28 instanceof androidx.lifecycle.r)) {
                    v2_30 = S2.a.b;
                } else {
                    v2_30 = ((androidx.lifecycle.r) v2_28).getDefaultViewModelCreationExtras();
                }
                kotlin.jvm.internal.N v9_2 = kotlin.jvm.internal.M.a;
                boolean v5_14 = v9_2.b(RS.u);
                RS.J v10_4 = v5_14.h();
                if (v10_4 == null) {
                    throw new IllegalArgumentException("Local and anonymous classes can not be ViewModels");
                } else {
                    RS.J v10_0 = "androidx.lifecycle.ViewModelProvider.DefaultKey:".concat(v10_4);
                    RS.D v11_1 = v1_34.b(v10_0);
                    if (!v5_14.r(v11_1)) {
                        RS.D v11_3 = new S2.e(v2_30);
                        v11_3.b(U2.d.a, v10_0);
                        RS.w v0_0 = v0_53.create(v5_14, v11_3);
                    }
                    int v14_0;
                    RS.D v11_4 = ((RS.u) v11_1);
                    this.m = v11_4;
                    if (v11_4 != null) {
                        v14_0 = v11_4;
                    } else {
                        v14_0 = 0;
                    }
                    QS.h.f(this.g1(), new BV.a1(0, v14_0, RS.u, "completeSmartOtpRegistration", "completeSmartOtpRegistration$authentication_release()V", 0, 13), new BV.a1(0, v14_0, RS.u, "recoverySnapshot", "recoverySnapshot$authentication_release()Lvn/com/zalopay/auth/presentation/otp/launch/OtpRecoverySnapshot;", 0, 14));
                    RS.w v0_5 = this.m;
                    if (v0_5 == null) {
                        v0_5 = 0;
                    }
                    Integer v1_2 = ((RS.J) v0_5.h0());
                    if ((!v0_5.i) && ((!v1_2.e) && ((!v1_2.f) && ((v1_2.g == null) && (v1_2.c.length() > 0))))) {
                        v0_5.k0(RS.J.a(((RS.J) v0_5.i0().getValue()), 0, 0, "", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 65531));
                    }
                    RS.J v10_3 = this.getIntent().getStringExtra("extra_params");
                    if ((v10_3 != null) && (!kotlin.text.StringsKt.isBlank(v10_3))) {
                        RS.w v0_9 = q9.v7.b(v10_3);
                        if (v0_9 == null) {
                            v0_9 = kotlin.collections.a0.emptyMap();
                        }
                        this.r = v0_9;
                        if (v0_9 != null) {
                            ZS.a.a.c(this.q, this.n, v0_9);
                        }
                        Integer v1_10 = this.j;
                        if (v1_10 == null) {
                            v1_10 = 0;
                        }
                        Integer v1_11 = v1_10.F();
                        String v2_14 = this.getApplicationContext();
                        boolean v5_7 = this.m;
                        if (!v5_7) {
                            v5_7 = 0;
                        }
                        RS.w v0_10 = ZS.a.b(this.n, v0_9);
                        RS.D v11_8 = new HR.c(v1_11, v2_14, 3);
                        v5_7.m = v0_10;
                        v5_7.n = v11_8;
                        if ((p28 == null) && (this.getIntent().getBooleanExtra("otp_root_caller_shows_loading", 0))) {
                            RS.w v0_13 = this.u;
                            v0_13.getClass();
                            v0_13.l(0, Boolean.TRUE);
                            rN.b.c("OTP_ROOT_CALLER_LOADING", v9_2.b(RS.a), androidx.lifecycle.l0.i(this.getLifecycle()), kotlinx.coroutines.Dispatchers.getMain().getImmediate(), 1, new AA.y(this, 0, 12));
                            kotlinx.coroutines.BuildersKt.launch$default(androidx.lifecycle.l0.i(this.getLifecycle()), 0, 0, new D1.f(this, 0), 3, 0);
                        }
                        String v2_17;
                        k.e.a(this, new y0.b(new AA.e(19, this), 1, -882261958));
                        kotlinx.coroutines.BuildersKt.launch$default(androidx.lifecycle.l0.i(this.getLifecycle()), 0, 0, new RS.e(this, 0, 1), 3, 0);
                        RS.w v0_22 = this.m;
                        if (v0_22 != null) {
                            v2_17 = v0_22;
                        } else {
                            v2_17 = 0;
                        }
                        Integer v1_21 = this.getLifecycle();
                        RS.D v11_12 = androidx.lifecycle.l0.i(v1_21);
                        int v14_7 = new QP.C;
                        v14_7(v1_21, v2_17, 0, this, 6);
                        kotlinx.coroutines.BuildersKt.launch$default(v11_12, 0, 0, v14_7, 3, 0);
                        RS.w v0_25 = this.m;
                        if (v0_25 == null) {
                            v0_25 = 0;
                        }
                        RS.D v3_5;
                        RS.w v0_30 = kotlin.text.StringsKt.isBlank(((RS.J) v0_25.h0()).a);
                        if (p28 == null) {
                            v3_5 = 0;
                        } else {
                            String v2_19 = p28.getString("otp_popup_request_id");
                            if (v2_19 == null) {
                            } else {
                                int v12_11;
                                if (kotlin.text.StringsKt.isBlank(v2_19)) {
                                    v12_11 = 0;
                                } else {
                                    v12_11 = v2_19;
                                }
                                if (v12_11 != 0) {
                                    String v13_7 = (p28.getLong("otp_popup_countdown_end_elapsed") - android.os.SystemClock.elapsedRealtime());
                                    String v2_22 = p28.getString("otp_popup_phone");
                                    if (v2_22 == null) {
                                        v2_22 = "";
                                    }
                                    int v14_8;
                                    int v15_8;
                                    if (!p28.containsKey("otp_popup_remaining_send")) {
                                        v15_8 = v13_7;
                                        v14_8 = 0;
                                    } else {
                                        v15_8 = v13_7;
                                        v14_8 = Integer.valueOf(p28.getInt("otp_popup_remaining_send"));
                                    }
                                    v3_5 = new RS.D(v12_11, v2_22, v14_8, Og.x.coerceAtLeast(((int) ((v15_8 + 999) / 1000)), 0), p28.getBoolean("otp_popup_smart_otp_visible"), p28.getBoolean("otp_popup_smart_otp_checked", 1));
                                }
                            }
                        }
                        if (v0_30 != null) {
                            void v4_1 = v27_1;
                            if (v3_5 == null) {
                                RS.D v3_6 = v4_1.m;
                                if (v3_6 == null) {
                                    v3_6 = 0;
                                }
                                v3_6.p0(new RS.w(v10_3, v4_1.n));
                                return;
                            } else {
                                RS.w v0_34 = v4_1.m;
                                if (v0_34 == null) {
                                    v0_34 = 0;
                                }
                                v0_34.p0(new RS.y(v3_5, v4_1.n));
                                ((iY.b) v4_1.t.getValue()).a();
                                return;
                            }
                        } else {
                            ((iY.b) v27_1.t.getValue()).a();
                            return;
                        }
                    } else {
                        this.h1(OS.P.b);
                        return;
                    }
                }
            }
        } else {
            this.s = 1;
            this.finish();
            this.overridePendingTransition(0, 0);
            return;
        }
    }

    public final void onDestroy()
    {
        if ((this.isFinishing()) && (!this.s)) {
            boolean v0_6 = this.m;
            if (!v0_6) {
                v0_6 = 0;
            }
            boolean v0_1 = ((RS.J) v0_6.h0());
            OS.P v1_0 = v0_1.g;
            if (v1_0 == null) {
                if (!v0_1.f) {
                    v1_0 = OS.P.c;
                } else {
                    v1_0 = OS.P.a;
                }
            }
            this.i1(v1_0);
        }
        ZS.a.a.a(this.q);
        QS.h.c(this.p, this.g1());
        super.onDestroy();
        return;
    }

    public final void onPause()
    {
        super.onPause();
        if ((this.isFinishing()) && (!this.s)) {
            boolean v0_5 = this.m;
            if (!v0_5) {
                v0_5 = 0;
            }
            boolean v0_1 = ((RS.J) v0_5.h0());
            OS.P v1 = v0_1.g;
            if (v1 == null) {
                if (!v0_1.f) {
                    v1 = OS.P.c;
                } else {
                    v1 = OS.P.a;
                }
            }
            this.i1(v1);
        }
        return;
    }

    public final void onResume()
    {
        super.onResume();
        if ((this.s) || (QS.h.e(this.g1()))) {
            int v0_4 = this.r;
            if (v0_4 != 0) {
                ZS.a.a.c(this.q, this.n, v0_4);
                return;
            } else {
                return;
            }
        } else {
            this.s = 1;
            this.finish();
            return;
        }
    }

    public final void onSaveInstanceState(android.os.Bundle p11)
    {
        super.onSaveInstanceState(p11);
        String v0_0 = this.m;
        if ((v0_0 != null) && (!this.s)) {
            RS.D v3_1;
            boolean v1_7 = ((RS.J) v0_0.h0());
            if ((v0_0.i) || (kotlin.text.StringsKt.isBlank(v1_7.a))) {
                v3_1 = 0;
            } else {
                String v0_3 = v1_7.d;
                if ((v0_3 != RS.q.a) && (v0_3 != RS.q.e)) {
                    v3_1 = new RS.D(v1_7.a, v1_7.b, v1_7.k, v1_7.j, v1_7.n, v1_7.o);
                }
            }
            if (v3_1 != null) {
                p11.putString("otp_popup_request_id", v3_1.a);
                p11.putString("otp_popup_phone", v3_1.b);
                String v0_6 = v3_1.c;
                if (v0_6 != null) {
                    p11.putInt("otp_popup_remaining_send", v0_6.intValue());
                }
                p11.putLong("otp_popup_countdown_end_elapsed", ((((long) v3_1.d) * 1000) + android.os.SystemClock.elapsedRealtime()));
                p11.putBoolean("otp_popup_smart_otp_visible", v3_1.e);
                p11.putBoolean("otp_popup_smart_otp_checked", v3_1.f);
            }
        }
        return;
    }

    public final void onStart()
    {
        super.onStart();
        int v0 = this.l;
        if (v0 == 0) {
            v0 = 0;
        }
        v0.D(this);
        return;
    }

    public final void onStop()
    {
        int v0 = this.l;
        if (v0 == 0) {
            v0 = 0;
        }
        v0.G(this);
        super.onStop();
        return;
    }

    public final void onUserSessionExpiredEvent(eu.f p2)
    {
        int v0 = this.l;
        if (v0 == 0) {
            v0 = 0;
        }
        v0.A(this, q9.B4.a(p2.a, this));
        return;
    }
}
