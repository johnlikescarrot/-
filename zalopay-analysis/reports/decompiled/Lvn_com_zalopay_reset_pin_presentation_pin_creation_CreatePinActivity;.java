package vn.com.zalopay.reset_pin.presentation.pin_creation;
public final class CreatePinActivity extends androidx.activity.ComponentActivity {
    public static final synthetic int m;
    public YX.y i;
    public final O5.b j;
    public KG.a k;
    public com.airbnb.lottie.network.g l;

    public CreatePinActivity()
    {
        this.j = new O5.b(kotlin.jvm.internal.M.a.b(YX.x), new XX.h(this, 0), new TT.a(17, this), new XX.h(this, 1));
        return;
    }

    public final YX.x b1()
    {
        return ((YX.x) this.j.getValue());
    }

    public final void onCreate(android.os.Bundle p10)
    {
        super.onCreate(p10);
        XX.f v10_1 = com.google.android.gms.internal.mlkit_vision_barcode.R7.a;
        if (v10_1 == null) {
            throw new IllegalStateException("ResetPinPlugin is not installed. Call install() first.");
        } else {
            y0.b v0_6 = ((CC.k) v10_1.a);
            int v2_1 = v0_6.h();
            p9.n7.b(v2_1);
            YX.h v3_3 = ((Ns.d) ((dagger.internal.o) v10_1.c).get());
            p9.n7.c(v3_3);
            com.airbnb.lottie.network.g v5_2 = ((OX.a) v10_1.c().create(OX.a));
            p9.n7.c(v5_2);
            c6.a v4_1 = new c6.a(24, new Ae.r(14, v5_2));
            aY.a v6_2 = ((OX.a) v10_1.c().create(OX.a));
            p9.n7.c(v6_2);
            com.airbnb.lottie.network.g v5_5 = new com.airbnb.lottie.network.g(new Ae.r(14, v6_2), new m6.t(14), 23);
            aY.a v6_5 = v10_1.a();
            p9.n7.c(v6_5);
            Yu.d v7_8 = ((Yu.d) ((dagger.internal.o) v10_1.h).get());
            p9.n7.c(v7_8);
            this.i = new YX.y(v2_1, v3_3, v4_1, v5_5, v6_5, v7_8);
            XX.f v10_4 = v10_1.b();
            p9.n7.c(v10_4);
            this.k = v10_4;
            XX.f v10_5 = v0_6.t();
            p9.n7.b(v10_5);
            y0.b v0_2 = v0_6.j();
            p9.n7.b(v0_2);
            this.l = new com.airbnb.lottie.network.g(v10_5, v0_2, 20);
            XX.f v10_7 = this.getIntent().getStringExtra("ovRequestId");
            y0.b v0_5 = "";
            if (v10_7 == null) {
                v10_7 = "";
            }
            int v1_5 = this.getIntent().getStringExtra("resetPinRequestId");
            if (v1_5 != 0) {
                v0_5 = v1_5;
            }
            this.b1().p0(new YX.h(v10_7, v0_5, this.getIntent().getBooleanExtra("skipResultScreen", 0)));
            k.e.a(this, new y0.b(new XX.f(this, 0), 1, 246258743));
            return;
        }
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
        String vtmp1 = q9.B4.a(p2.a, this);
        int v0 = this.l;
        if (v0 == 0) {
            v0 = 0;
        }
        v0.A(this, vtmp1);
        return;
    }
}
