package vn.com.zalopay.auth.presentation.otp;
public final class OTPChallengeActivity extends androidx.appcompat.app.AppCompatActivity implements iY.b$a {
    public static final synthetic int u;
    public KG.a i;
    public String j;
    public java.util.Map k;
    public final int l;
    public OS.h m;
    public Pt.n n;
    public String o;
    public com.airbnb.lottie.network.g p;
    public final ng.S q;
    public boolean r;
    public final ng.S s;
    public final Object t;

    public OTPChallengeActivity()
    {
        this.j = "";
        this.k = kotlin.collections.a0.emptyMap();
        this.l = ZS.a.b.incrementAndGet();
        this.q = ng.x.b(new OS.d(this, 0));
        this.s = ng.x.b(new OS.d(this, 1));
        this.t = new Object();
        return;
    }

    public static final void e1(vn.com.zalopay.auth.presentation.otp.OTPChallengeActivity p11, fu.j p12)
    {
        OS.h v2_0;
        java.util.Map v0 = p12.d;
        if (v0 == null) {
            v2_0 = "INLINE";
        } else {
            OS.h v2_3 = v0.get("error_show");
            if (v2_3 == null) {
            } else {
                v2_0 = v2_3.toString();
                if (v2_0 == null) {
                }
            }
        }
        OS.a[] v3 = p12.c;
        if (v3 == null) {
            v3 = "H\u1ec7 th\u1ed1ng \u0111ang b\u1eadn. Vui l\u00f2ng th\u1eed l\u1ea1i sau b\u1ea1n nh\u00e9.";
        }
        String v6 = v3;
        OS.h v2_1 = 0;
        if (!kotlin.jvm.internal.Intrinsics.areEqual(v2_0, "INLINE")) {
            String v1_4;
            if (v0 == null) {
                v1_4 = "Kh\u00f4ng th\u1ec3 th\u1ef1c hi\u1ec7n y\u00eau c\u1ea7u";
            } else {
                String v1_3 = v0.get("title");
                if (v1_3 == null) {
                } else {
                    v1_4 = v1_3.toString();
                    if (v1_4 == null) {
                    }
                }
            }
            String v12_3;
            String v5 = v1_4;
            String v7 = e5.b.a(p12);
            if (v0 == null) {
                v12_3 = "T\u00f4i \u0111\u00e3 hi\u1ec3u";
            } else {
                String v12_2 = v0.get("cta");
                if (v12_2 == null) {
                } else {
                    v12_3 = v12_2.toString();
                    if (v12_3 == null) {
                    }
                }
            }
            String v8 = v12_3;
            String v12_6;
            if (v0 == null) {
                v12_6 = "";
            } else {
                String v12_5 = v0.get("cta_action_link");
                if (v12_5 == null) {
                } else {
                    v12_6 = v12_5.toString();
                    if (v12_6 == null) {
                    }
                }
            }
            OS.D v4_1 = new OS.D(v5, v6, v7, v8, v12_6, 130);
            OS.o v11_1 = p11.m;
            if (v11_1 != null) {
                v2_1 = v11_1;
            }
            v2_1.o0(new OS.o(v4_1));
            return;
        } else {
            OS.o v11_4 = p11.m;
            if (v11_4 != null) {
                v2_1 = v11_4;
            }
            v2_1.o0(new OS.B(v6));
            return;
        }
    }

    public final void d1(String p5)
    {
        OS.h v1 = 0;
        if (kotlin.text.StringsKt.isBlank(p5)) {
            OS.A v0_5 = this.m;
            if (v0_5 == null) {
                v0_5 = 0;
            }
            v0_5.o0(new OS.l("01.8200.013"));
        } else {
            OS.A v0_1 = this.m;
            if (v0_1 == null) {
                v0_1 = 0;
            }
            v0_1.o0(new OS.l("01.8200.012"));
        }
        OS.A v0_2 = this.m;
        if (v0_2 != null) {
            v1 = v0_2;
        }
        v1.o0(new OS.A(p5));
        return;
    }

    public final String g1()
    {
        return ((String) this.s.getValue());
    }

    public final void h1()
    {
        this.r = 1;
        OS.h v0_2 = this.m;
        OS.h v1 = 0;
        if (v0_2 == null) {
            v0_2 = 0;
        }
        OS.h v0_1 = v0_2.p0().a;
        if (v0_1 == null) {
            v0_1 = OS.P.c;
        }
        OS.P v2_0 = this.g1();
        String v3_0 = this.m;
        if (v3_0 == null) {
            v3_0 = 0;
        }
        if ((m9.b3.e(v0_1, v2_0, ((OS.S) v3_0.h0()).r.a)) && (v0_1 == OS.P.a)) {
            OS.h v0_3 = this.m;
            if (v0_3 != null) {
                v1 = v0_3;
            }
            v1.n0();
        }
        return;
    }

    public final void onActivityResult(int p2, int p3, android.content.Intent p4)
    {
        super.onActivityResult(p2, p3, p4);
        iY.b v0_2 = ((iY.b) this.q.getValue());
        if (v0_2 != null) {
            v0_2.onActivityResult(p2, p3, p4);
        }
        if (p2 == 1510) {
            int v2_1 = this.m;
            if (v2_1 == 0) {
                v2_1 = 0;
            }
            v2_1.o0(new OS.l("01.8200.011"));
        }
        return;
    }

    public final void onCreate(android.os.Bundle p25)
    {
        vn.com.zalopay.auth.presentation.otp.OTPChallengeActivity v1 = this;
        androidx.lifecycle.x v0_0 = U2.d.a;
        int v3_0 = n9.O.a;
        java.util.Map v2_35 = new androidx.camera.camera2.impl.a(21, v3_0);
        OS.l v4_31 = v3_0.b();
        p9.n7.c(v4_31);
        this.i = v4_31;
        String v6_6 = ((eS.e) v3_0.d().create(eS.e));
        p9.n7.c(v6_6);
        int v5_6 = new OS.V(new WQ.e(v6_6));
        String v6_8 = v3_0.h();
        String v7_8 = v3_0.c();
        p9.n7.c(v7_8);
        this.m = new OS.h(v5_6, new a9.f(v7_8, v6_8));
        this.n = v3_0.h();
        int v3_1 = v3_0.n();
        p9.n7.c(v3_1);
        this.o = v3_1;
        java.util.Map v2_30 = ((gS.a) v2_35.b);
        this.p = new com.airbnb.lottie.network.g(v2_30.i(), v2_30.j(), 20);
        super.onCreate(p25);
        if (QS.h.a(v1.t, v1.g1())) {
            int v5_11 = this.g1();
            try {
                String v7_11 = QS.h.b;
            } catch (androidx.lifecycle.x v0_34) {
                throw v0_34;
            }
            if ((v7_11 == null) || (!kotlin.jvm.internal.Intrinsics.areEqual(v7_11.a, v5_11))) {
                v7_11 = 0;
            }
            if (v7_11 == null) {
                throw new IllegalArgumentException("Required value was null.");
            } else {
                int v5_13 = this.m;
                if (v5_13 == 0) {
                    v5_13 = 0;
                }
                int v11_10;
                String v6_17 = new Cx.h(2, v5_13);
                int v5_14 = v7_11.b;
                int v10_8 = (v7_11 instanceof androidx.lifecycle.r);
                if (v10_8 == 0) {
                    v11_10 = S2.a.b;
                } else {
                    v11_10 = ((androidx.lifecycle.r) v7_11).getDefaultViewModelCreationExtras();
                }
                int v13_6 = kotlin.jvm.internal.M.a;
                OS.g v12_12 = v13_6.b(OS.h);
                int v14_6 = v12_12.h();
                if (v14_6 == 0) {
                    throw new IllegalArgumentException("Local and anonymous classes can not be ViewModels");
                } else {
                    int v14_0 = "androidx.lifecycle.ViewModelProvider.DefaultKey:".concat(v14_6);
                    int v15_0 = v5_14.b(v14_0);
                    if (!v12_12.r(v15_0)) {
                        int v15_2 = new S2.e(v11_10);
                        v15_2.b(v0_0, v14_0);
                        String v6_0 = v6_17.create(v12_12, v15_2);
                    }
                    int v15_3 = ((OS.h) v15_0);
                    this.m = v15_3;
                    if (v15_3 == 0) {
                        v15_3 = 0;
                    }
                    String v7_1;
                    String v6_4 = new Cx.h(3, v15_3.e);
                    int v5_5 = v7_11.b;
                    if (v10_8 == 0) {
                        v7_1 = S2.a.b;
                    } else {
                        v7_1 = ((androidx.lifecycle.r) v7_11).getDefaultViewModelCreationExtras();
                    }
                    int v10_1 = v13_6.b(OS.V);
                    int v11_4 = v10_1.h();
                    if (v11_4 == 0) {
                        throw new IllegalArgumentException("Local and anonymous classes can not be ViewModels");
                    } else {
                        java.util.Map v2_0 = "androidx.lifecycle.ViewModelProvider.DefaultKey:".concat(v11_4);
                        if (!v10_1.r(v5_5.b(v2_0))) {
                            OS.l v4_4 = new S2.e(v7_1);
                            v4_4.b(v0_0, v2_0);
                            androidx.lifecycle.x v0_3 = v6_4.create(v10_1, v4_4);
                        }
                        androidx.lifecycle.ViewModel v18 = v15_3;
                        QS.h.f(this.g1(), new BV.a1(0, v18, OS.h, "completeSmartOtpRegistration", "completeSmartOtpRegistration$authentication_release()V", 0, 11), new BV.a1(0, v18, OS.h, "recoverySnapshot", "recoverySnapshot$authentication_release()Lvn/com/zalopay/auth/presentation/otp/launch/OtpRecoverySnapshot;", 0, 12));
                        java.util.Map v2_4 = this.getIntent().getStringExtra("source_id");
                        if (v2_4 == null) {
                            v2_4 = "default";
                        }
                        this.j = v2_4;
                        java.util.Map v2_6 = this.getIntent().getStringExtra("extra_params");
                        if (v2_6 == null) {
                            v2_6 = "";
                        }
                        java.util.Map v2_7 = q9.v7.b(v2_6);
                        if (v2_7 == null) {
                            v2_7 = kotlin.collections.a0.emptyMap();
                        }
                        this.k = v2_7;
                        ZS.a.a.c(this.l, this.j, v2_7);
                        java.util.Map v2_8 = this.n;
                        if (v2_8 == null) {
                            v2_8 = 0;
                        }
                        java.util.Map v2_9 = v2_8.F();
                        OS.l v4_10 = this.getApplicationContext();
                        int v5_8 = this.m;
                        if (v5_8 == 0) {
                            v5_8 = 0;
                        }
                        String v6_10 = ZS.a.b(this.j, this.k);
                        String v7_7 = new HR.c(v2_9, v4_10, 2);
                        v5_8.j = v6_10;
                        v5_8.k = v7_7;
                        if (!v5_8.m) {
                            v5_8.m = 1;
                            kotlinx.coroutines.BuildersKt.launch$default(androidx.lifecycle.l0.l(v5_8), 0, 0, new AA.g(v5_8, 0, 26), 3, 0);
                        }
                        try {
                            this.getPackageManager().getApplicationInfo("com.zing.zalo", 0);
                            java.util.Map v2_14 = 1;
                        } catch (android.content.pm.PackageManager$NameNotFoundException) {
                            v2_14 = 0;
                        }
                        OS.l v4_12 = this.m;
                        if (v4_12 == null) {
                            v4_12 = 0;
                        }
                        androidx.lifecycle.A v9_2;
                        v4_12.o0(new OS.t(v2_14));
                        java.util.Map v2_15 = this.getIntent();
                        OS.l v4_14 = v2_15.getStringExtra("otp_created_request_id");
                        if (v4_14 == null) {
                            v9_2 = 0;
                        } else {
                            int v10_4;
                            if (kotlin.text.StringsKt.isBlank(v4_14)) {
                                v10_4 = 0;
                            } else {
                                v10_4 = v4_14;
                            }
                            if (v10_4 != 0) {
                                int v11_6;
                                OS.l v4_16 = v2_15.getStringExtra("otp_created_phone");
                                if (v4_16 != null) {
                                    v11_6 = v4_16;
                                } else {
                                    v11_6 = "";
                                }
                                androidx.lifecycle.x v0_15;
                                OS.g v12_3 = v2_15.getIntExtra("otp_created_default_provider", 1);
                                androidx.lifecycle.x v0_14 = v2_15.getIntArrayExtra("otp_created_allow_providers");
                                if (v0_14 == null) {
                                    v0_15 = kotlin.collections.C.listOf(Integer.valueOf(1));
                                } else {
                                    v0_15 = kotlin.collections.y.toList(v0_14);
                                    if (v0_15 == null) {
                                    }
                                }
                                int v13_1 = v0_15;
                                java.util.Map v2_17 = Integer.valueOf(v2_15.getIntExtra("otp_created_otp_ui", 1));
                                QS.j.b.getClass();
                                v9_2 = new QS.a(v10_4, v11_6, v12_3, v13_1, lc.a.s(v2_17));
                            }
                        }
                        if (v9_2 != null) {
                            java.util.Map v2_18 = v9_2.c;
                            OS.W.b.getClass();
                            androidx.lifecycle.x v0_21 = ((OS.W) OS.W.c.get(Integer.valueOf(v2_18)));
                            if (v0_21 == null) {
                                v0_21 = OS.W.d;
                            }
                            try {
                                this.getPackageManager().getApplicationInfo("com.zing.zalo", 0);
                            } catch (android.content.pm.PackageManager$NameNotFoundException) {
                                v0_21 = OS.W.d;
                            }
                            java.util.Map v2_22 = this.m;
                            if (v2_22 == null) {
                                v2_22 = 0;
                            }
                            androidx.lifecycle.A v9_4 = new TS.a(v9_2.a, v9_2.b, v0_21, v9_2.d);
                            if (!v2_22.h) {
                                v2_22.h = 1;
                                v2_22.o0(new OS.k(v9_4));
                                kotlinx.coroutines.BuildersKt.launch$default(androidx.lifecycle.l0.l(v2_22), 0, 0, new JV.J(v2_22, v9_4, 0, 25), 3, 0);
                            }
                        } else {
                            androidx.lifecycle.x v0_26 = this.m;
                            if (v0_26 == null) {
                                v0_26 = 0;
                            }
                            v0_26.o0(new OS.v(OS.P.b));
                            androidx.lifecycle.x v0_27 = this.m;
                            if (v0_27 == null) {
                                v0_27 = 0;
                            }
                            v0_27.o0(OS.j.a);
                        }
                        k.e.a(this, new y0.b(new OS.c(this, 0), 1, -766354696));
                        androidx.lifecycle.x v0_30 = this.m;
                        if (v0_30 == null) {
                            v0_30 = 0;
                        }
                        java.util.Map v2_29 = this.getLifecycle();
                        kotlinx.coroutines.BuildersKt.launch$default(androidx.lifecycle.l0.i(v2_29), 0, 0, new AA.j(v2_29, v0_30, 0, this), 3, 0);
                        androidx.lifecycle.x v0_31 = this.m;
                        if (v0_31 == null) {
                            v0_31 = 0;
                        }
                        java.util.Map v2_31 = this.j;
                        if (!v0_31.i) {
                            v0_31.i = 1;
                            v0_31.o0(new OS.l("01.8200.000", kotlin.collections.Z.mapOf(kotlin.TuplesKt.to("source", v2_31))));
                        }
                        kotlinx.coroutines.BuildersKt.launch$default(androidx.lifecycle.l0.i(this.getLifecycle()), 0, 0, new OS.g(this, 0, 0), 3, 0);
                        kotlinx.coroutines.BuildersKt.launch$default(androidx.lifecycle.l0.i(this.getLifecycle()), 0, 0, new OS.g(this, 0, 1), 3, 0);
                        return;
                    }
                }
            }
        } else {
            this.r = 1;
            this.finish();
            return;
        }
    }

    public final void onDestroy()
    {
        if ((this.isFinishing()) && (!this.r)) {
            this.h1();
        }
        ZS.a.a.a(this.l);
        QS.h.c(this.t, this.g1());
        super.onDestroy();
        return;
    }

    public final void onPause()
    {
        super.onPause();
        if ((this.isFinishing()) && (!this.r)) {
            this.h1();
        }
        return;
    }

    public final void onResume()
    {
        super.onResume();
        if ((this.r) || (QS.h.e(this.g1()))) {
            ZS.a.a.c(this.l, this.j, this.k);
            return;
        } else {
            this.r = 1;
            this.finish();
            return;
        }
    }

    public final void onStart()
    {
        super.onStart();
        int v0 = this.p;
        if (v0 == 0) {
            v0 = 0;
        }
        v0.D(this);
        return;
    }

    public final void onStop()
    {
        int v0 = this.p;
        if (v0 == 0) {
            v0 = 0;
        }
        v0.G(this);
        super.onStop();
        return;
    }

    public final void onUserSessionExpiredEvent(eu.f p2)
    {
        String vtmp1 = q9.B4.a(p2.a, this);
        int v0 = this.p;
        if (v0 == 0) {
            v0 = 0;
        }
        v0.A(this, vtmp1);
        return;
    }
}
