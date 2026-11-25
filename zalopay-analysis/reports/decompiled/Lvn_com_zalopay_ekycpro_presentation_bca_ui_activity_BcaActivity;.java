package vn.com.zalopay.ekycpro.presentation.bca.ui.activity;
public final class BcaActivity extends vn.com.vng.zalopay.core.ui.activity.BaseFeatureActivity {
    public static final synthetic int q;
    public Te.g j;
    public final O5.b k;
    public KG.a l;
    public com.airbnb.lottie.network.g m;
    public final q0.N0 n;
    public final androidx.activity.result.d o;
    public final androidx.activity.result.d p;

    public BcaActivity()
    {
        this.k = new O5.b(kotlin.jvm.internal.M.a.b(XU.a), new UU.c(this, 0), new TT.a(7, this), new UU.c(this, 1));
        this.n = q0.d.G(Boolean.FALSE, q0.D0.e);
        this.o = this.registerForActivityResult(new androidx.fragment.app.f0(6), new UU.a(this, 0));
        this.p = this.registerForActivityResult(new androidx.fragment.app.f0(6), new UU.a(this, 1));
        return;
    }

    public final XU.a h1()
    {
        return ((XU.a) this.k.getValue());
    }

    public final void onCreate(android.os.Bundle p11)
    {
        super.onCreate(p11);
        this.getWindow().addFlags(128);
        vn.com.vng.zalopay.core.ui.activity.BaseFeatureActivity.e1(this);
        UU.b v11_12 = n9.W2.a;
        if (v11_12 == null) {
            throw new IllegalStateException("EKycProPlugin is not installed. Call install() first.");
        } else {
            GU.b v1_1 = new AU.b(v11_12);
            s5.g v6_1 = new s5.g();
            s5.f v7_1 = new s5.f();
            com.airbnb.lottie.network.f v8_1 = new com.airbnb.lottie.network.f(1, v11_12);
            dagger.internal.o v9 = dagger.internal.f.b(new AU.c(v1_1, 0));
            int v2_14 = dagger.internal.f.b(new AU.c(v1_1, 5));
            GU.b v3_8 = dagger.internal.f.b(new AU.c(v1_1, 2));
            dagger.internal.f.b(new AU.d(v1_1, v2_14, v3_8, dagger.internal.f.b(new AU.c(v1_1, 3)), 0));
            dagger.internal.f.b(new AU.a(v7_1, 2));
            dagger.internal.f.b(new AB.b(13, v1_1));
            dagger.internal.f.b(new AU.e(v6_1, 5));
            dagger.internal.f.b(new AU.e(v6_1, 3));
            dagger.internal.f.b(new AU.e(v6_1, 2));
            dagger.internal.f.b(new AU.e(v6_1, 7));
            dagger.internal.f.b(new AU.e(v6_1, 1));
            dagger.internal.f.b(new AU.e(v6_1, 4));
            dagger.internal.f.b(new AU.e(v6_1, 6));
            dagger.internal.f.b(new AU.a(v7_1, 5));
            dagger.internal.f.b(new AU.a(v7_1, 1));
            dagger.internal.f.b(new AU.a(v7_1, 4));
            dagger.internal.f.b(new AA.C(v1_1, v2_14, 3));
            dagger.internal.f.b(new AU.a(v7_1, 3));
            dagger.internal.f.b(new AU.a(v7_1, 0));
            dagger.internal.f.b(new AA.C(v8_1, dagger.internal.f.b(new AP.d(1, v8_1)), 5));
            dagger.internal.f.b(new AA.C(v1_1, dagger.internal.f.b(new AU.c(v1_1, 1)), 4));
            dagger.internal.f.b(new AA.C(v1_1, v3_8, 2));
            GU.b v0_12 = dagger.internal.f.b(new AU.c(v1_1, 4));
            v9.get();
            GU.b v0_14 = ((String) v0_12.get());
            GU.b v1_3 = new Te.g();
            v1_3.a = v0_14;
            this.j = v1_3;
            GU.b v0_15 = v11_12.b();
            p9.n7.c(v0_15);
            this.l = v0_15;
            this.m = new com.airbnb.lottie.network.g(v11_12.i(), v11_12.j(), 20);
            UU.b v11_4 = this.h1().c;
            if (((android.os.Parcelable) v11_4.b("bca_activity_args")) == null) {
                GU.b v1_9 = this.getIntent().getParcelableExtra("bca_activity_args");
                if (v1_9 == null) {
                    this.g1();
                    this.finish();
                    return;
                } else {
                    v11_4.d("bca_activity_args", v1_9);
                }
            }
            GU.b v0_19;
            UU.b v11_5 = this.h1();
            if (android.nfc.NfcAdapter.getDefaultAdapter(this) == null) {
                v0_19 = 0;
            } else {
                v0_19 = 1;
            }
            GU.b v3_0 = v11_5.e;
            java.util.Objects.toString(v3_0);
            fV.b v4_2 = v3_0.e;
            if (!v4_2.isEmpty()) {
                if (!v3_0.a) {
                    GU.b v1_13 = ((GU.b) kotlin.collections.CollectionsKt.first(v4_2));
                    if ((v1_13 != GU.b.f) || ((v0_19 != null) || (!v4_2.contains(GU.b.h)))) {
                        v11_5.i0(v1_13, "auto");
                    } else {
                        v11_5.i0(GU.b.e, "auto");
                    }
                }
            } else {
                GU.b v3_2 = new Object[0];
                ck.b.a("[BCA] options is empty", v3_2);
                v11_5.h.l(new Xs.b(new RU.a(0, fV.b.c)));
            }
            this.h1().i.f(this, new cV.y(2, new SM.l0(5, this)));
            k.e.a(this, new y0.b(new UU.b(this, 0), 1, -1200559529));
            return;
        }
    }

    public final void onStart()
    {
        super.onStart();
        int v0 = this.m;
        if (v0 == 0) {
            v0 = 0;
        }
        v0.D(this);
        return;
    }

    public final void onStop()
    {
        int v0 = this.m;
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
        int v0 = this.m;
        if (v0 == 0) {
            v0 = 0;
        }
        v0.A(this, vtmp1);
        return;
    }
}
