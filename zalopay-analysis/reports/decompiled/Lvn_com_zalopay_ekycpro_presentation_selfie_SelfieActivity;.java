package vn.com.zalopay.ekycpro.presentation.selfie;
public final class SelfieActivity extends androidx.appcompat.app.AppCompatActivity {
    public static final synthetic int t;
    public androidx.camera.camera2.impl.a i;
    public KG.a j;
    public Z.g k;
    public BV.Y0 l;
    public final O5.b m;
    public Cx.h n;
    public final O5.b o;
    public com.airbnb.lottie.network.g p;
    public gW.j q;
    public fW.a r;
    public String s;

    public SelfieActivity()
    {
        kotlin.reflect.d v2_1 = kotlin.jvm.internal.M.a;
        this.m = new O5.b(v2_1.b(dW.j), new dW.d(this, 0), new dW.a(this, 0), new dW.d(this, 1));
        this.o = new O5.b(v2_1.b(hW.m), new dW.d(this, 2), new dW.a(this, 9), new dW.d(this, 3));
        this.s = "";
        return;
    }

    public final void e1(fV.b p3)
    {
        android.content.Intent v0_1 = new android.content.Intent();
        v0_1.putExtra("arg_result_state", p3.a);
        this.setResult(-1, v0_1);
        this.finish();
        this.overridePendingTransition(0, 0);
        return;
    }

    public final dW.j g1()
    {
        return ((dW.j) this.m.getValue());
    }

    public final void h1(eW.c p8)
    {
        eW.a v0_0 = this.i;
        androidx.media3.container.l v1_0 = 0;
        if (v0_0 == null) {
            v0_0 = 0;
        }
        eW.a v0_4 = ((eW.a) ((com.google.common.collect.y0) v0_0.b).get(p8));
        if (v0_4 != null) {
            String v4_0;
            kotlin.reflect.d v3_0 = this.q;
            if (v3_0 != null) {
                v4_0 = v3_0;
            } else {
                v4_0 = 0;
            }
            String v4_1 = v4_0.d;
            if (v3_0 == null) {
                v3_0 = 0;
            }
            kotlin.reflect.d v3_1 = v3_0.g;
            kotlin.jvm.internal.N v5_0 = p8.name();
            kotlin.reflect.d v6_0 = this.q;
            if (v6_0 == null) {
                v6_0 = 0;
            }
            eW.h v2_1 = new eW.k(v4_1, v3_1, v5_0, v6_0.j);
            kotlin.reflect.d v3_3 = new androidx.camera.video.internal.encoder.u(24, this, p8, 0);
            kotlin.jvm.internal.N v5_2 = kotlin.jvm.internal.M.a;
            if ((kotlin.jvm.internal.Intrinsics.areEqual(v0_4.a(), v5_2.b(eW.k))) && (kotlin.jvm.internal.Intrinsics.areEqual(v0_4.b(), v5_2.b(eW.l)))) {
                v0_4.c(this, v2_1, v3_3);
            }
            String v4_7;
            kotlin.reflect.d v3_4 = this.q;
            if (v3_4 != null) {
                v4_7 = v3_4;
            } else {
                v4_7 = 0;
            }
            if (v3_4 != null) {
                v1_0 = v3_4;
            }
            eW.h v2_3 = new eW.h(v1_0.k, v4_7.a);
            androidx.media3.container.l v1_3 = new androidx.media3.container.l(this, p8, 21);
            if ((kotlin.jvm.internal.Intrinsics.areEqual(v0_4.a(), v5_2.b(eW.h))) && (kotlin.jvm.internal.Intrinsics.areEqual(v0_4.b(), v5_2.b(eW.i)))) {
                v0_4.c(this, v2_3, v1_3);
            }
        }
        return;
    }

    public final void i1()
    {
        dW.j v0_0 = this.r;
        if ((v0_0 != null) && (v0_0.b)) {
            if (com.google.android.gms.internal.mlkit_vision_barcode.Y.a == null) {
                dW.j v0_7 = new Object[0];
                com.google.android.gms.internal.mlkit_vision_barcode.Y.a = SP.n.e("[TrackerManager] getTracker() called before init(). Auto-initializing.", v0_7);
            }
            String v2_0 = this.q;
            gW.j v3 = 0;
            if (v2_0 == null) {
                v2_0 = 0;
            }
            String v2_1 = v2_0.b;
            eW.c.b.getClass();
            com.google.android.gms.internal.mlkit_vision_barcode.Y.a.c(d6.l.g(v2_1).name(), "", 1);
            dW.j vtmp5 = this.g1();
            gW.j v4_1 = this.q;
            if (v4_1 != null) {
                v3 = v4_1;
            }
            vtmp5.p0(new gW.g(this.s, v3));
        }
        return;
    }

    public final void onCreate(android.os.Bundle p15)
    {
        YW.h v15_5;
        y0.b v0_0 = n9.W2.a;
        v0_0.getClass();
        int v2_13 = n9.W2.a;
        int v1_16 = new AU.b(v2_13);
        int v3_4 = new com.airbnb.lottie.network.f(1, v2_13);
        int v2_4 = dagger.internal.f.b(new DU.a(v1_16, 0));
        int v1_15 = dagger.internal.f.b(new DC.W(v1_16, dagger.internal.f.b(new DU.a(v1_16, 1)), 29));
        int v3_2 = dagger.internal.f.b(new AA.C(v3_4, dagger.internal.f.b(new AP.d(1, v3_4)), 5));
        new eW.j(((Ns.d) v2_4.get()));
        new eW.j(((Ns.d) v2_4.get()));
        b0.x v11_2 = v0_0.h();
        int v12_3 = ((Ns.d) v2_4.get());
        int v13_6 = ((eW.g) ((dagger.internal.o) v0_0.p).get());
        p9.n7.b(v13_6);
        this.i = new androidx.camera.camera2.impl.a(20, com.google.common.collect.y0.d(3, new Object[] {eW.c.e, new eW.f(v11_2, v12_3, v13_6)}), 0));
        N4.b v4_10 = v0_0.b();
        p9.n7.b(v4_10);
        this.j = v4_10;
        N4.b v4_12 = new Z.g(20, 0);
        fW.b v5_10 = fW.b.c;
        v4_12.b = ji.j0.c(v5_10);
        this.k = v4_12;
        this.l = new BV.Y0(((KU.i) v1_15.get()), v0_0.h(), ((Ns.d) v2_4.get()), new XQ.i(new D5.f(26), new N4.b(v0_0.getAppContext(), 0)), 2);
        v2_4.get();
        this.n = new Cx.h(13, ((KU.h) v3_2.get()));
        this.p = new com.airbnb.lottie.network.g(v0_0.i(), v0_0.j(), 20);
        super.onCreate(p15);
        this.getWindow().addFlags(128);
        p9.U.a(this.getWindow(), 0);
        YW.h v15_4 = this.getIntent().getExtras();
        if (v15_4 == null) {
            v15_5 = 0;
        } else {
            v15_5 = ((android.os.Parcelable) p9.M.a(v15_4, "arg_selfie_request_param", gW.j));
        }
        if (v15_5 == null) {
            throw new IllegalStateException("Error: Missing required Parcelable for key \'arg_selfie_request_param\'");
        } else {
            this.q = ((gW.j) v15_5);
            YW.h v15_10 = this.g1();
            int v1_6 = this.q;
            // Both branches of the condition point to the same code.
            // if (v1_6 != 0) {
                // Both branches of the condition point to the same code.
                // if (v1_6 != 0) {
                    if (v1_6 == 0) {
                        v1_6 = 0;
                    }
                    int v1_7 = v1_6.i;
                    xV.e.b.getClass();
                    xV.e.c.get(Integer.valueOf(v1_7));
                    // Both branches of the condition point to the same code.
                    // if (this.q != null) {
                        Class vtmp33 = v15_10.getClass();
                        if (this.q != null) {
                            YW.h v15_12 = this.g1();
                            int v2_10 = this.q;
                            if (v2_10 == 0) {
                                v2_10 = 0;
                            }
                            v15_12.p0(new gW.b(v2_10));
                        }
                        YW.h v15_14;
                        YW.h v15_13 = BW.a.a;
                        if (v15_13 == null) {
                            v15_14 = 0;
                        } else {
                            v15_14 = v15_13.a;
                        }
                        if (v15_14 == null) {
                            v15_14 = "";
                        }
                        YW.h v15_16;
                        this.s = v15_14;
                        if (BW.a.a == null) {
                            v15_16 = 0;
                        } else {
                            v15_16 = 1;
                        }
                        if ((v15_16 == null) || (kotlin.text.StringsKt.isBlank(this.s))) {
                            YW.h v15_19 = new fW.a(v5_10, 0);
                        } else {
                            v15_19 = new fW.a(fW.b.b, 1);
                        }
                        this.r = v15_19;
                        y0.b v0_6 = this.k;
                        if (v0_6 == null) {
                            v0_6 = 0;
                        }
                        v0_6.l(v15_19.a);
                        this.i1();
                        YW.h v15_22 = this.g1();
                        y0.b v0_7 = this.getLifecycle();
                        kotlinx.coroutines.BuildersKt.launch$default(androidx.lifecycle.l0.i(v0_7), 0, 0, new b0.x(v0_7, v15_22, 0, this), 3, 0);
                        k.e.a(this, new y0.b(new YW.h(11, this), 1, 856251338));
                        return;
                    // }
                // }
            // }
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
        String vtmp1 = q9.d0.a(p2.a, this);
        int v0 = this.p;
        if (v0 == 0) {
            v0 = 0;
        }
        v0.A(this, vtmp1);
        return;
    }
}
