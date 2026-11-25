package vn.com.zalopay.auth.presentation.smartotp;
public final class SmartOTPActivity extends androidx.appcompat.app.AppCompatActivity {
    public static final synthetic int v;
    public US.h i;
    public KG.a j;
    public Pt.n k;
    public Yu.d l;
    public com.airbnb.lottie.network.g m;
    public String n;
    public String o;
    public String p;
    public String q;
    public String r;
    public int s;
    public java.util.Map t;
    public final int u;

    public SmartOTPActivity()
    {
        this.n = "";
        this.o = "";
        this.p = "";
        this.q = "";
        this.r = "";
        this.t = kotlin.collections.a0.emptyMap();
        this.u = ZS.a.b.incrementAndGet();
        return;
    }

    public static synthetic void g1(vn.com.zalopay.auth.presentation.smartotp.SmartOTPActivity p1, US.e p2, String p3, int p4)
    {
        if ((p4 & 1) != 0) {
            p2 = US.e.b;
        }
        if ((p4 & 2) != 0) {
            p3 = "";
        }
        p1.e1(p2, p3);
        return;
    }

    public final void e1(US.e p4, String p5)
    {
        String v0_0 = this.s;
        US.h v2 = 0;
        if ((v0_0 != 1) && (v0_0 != 2)) {
            rN.c.a(new US.f(p4, 0, p5, 6));
            this.finish();
            return;
        } else {
            US.o v4_1 = this.i;
            if (v4_1 == null) {
                v4_1 = 0;
            }
            v4_1.m0(new US.r(""));
            US.o v4_2 = this.i;
            if (v4_2 != null) {
                v2 = v4_2;
            }
            v2.m0(US.o.a);
            return;
        }
    }

    public final void onCreate(android.os.Bundle p12)
    {
        vn.com.zalopay.auth.presentation.smartotp.SmartOTPActivity v1_7;
        vn.com.zalopay.auth.presentation.smartotp.SmartOTPActivity v1_0 = n9.O.a;
        ZS.a v0_10 = ((gS.a) new androidx.camera.camera2.impl.a(21, v1_0).b);
        int v4_1 = ((eS.g) v0_10.d().create(eS.g));
        p9.n7.c(v4_1);
        String v3_9 = new ES.i(new androidx.media3.extractor.mp4.g(13, v4_1));
        int v4_2 = v1_0.k();
        p9.n7.c(v4_2);
        this.i = new US.h(v3_9, v4_2, new ES.b(v1_0.h(), 2));
        int v2_5 = v1_0.b();
        p9.n7.c(v2_5);
        this.j = v2_5;
        this.k = v1_0.h();
        vn.com.zalopay.auth.presentation.smartotp.SmartOTPActivity v1_1 = v1_0.k();
        p9.n7.c(v1_1);
        this.l = v1_1;
        this.m = new com.airbnb.lottie.network.g(v0_10.i(), v0_10.j(), 20);
        super.onCreate(p12);
        p9.U.a(this.getWindow(), 0);
        int v12_9 = this.getWindow();
        int v2_11 = new androidx.core.view.e(2, this.getWindow().getDecorView());
        vn.com.zalopay.auth.presentation.smartotp.SmartOTPActivity v1_5 = android.os.Build$VERSION.SDK_INT;
        if (v1_5 < 35) {
            if (v1_5 < 30) {
                v1_7 = new androidx.core.view.m1(v12_9, v2_11);
            } else {
                v1_7 = new androidx.core.view.n1(v12_9, v2_11);
            }
        } else {
            v1_7 = new androidx.core.view.o1(v12_9, v2_11);
        }
        String v3_16;
        v1_7.e(1);
        k.e.a(this, new y0.b(new US.a(this, 0), 1, 1356256547));
        int v12_11 = this.i;
        if (v12_11 == 0) {
            v3_16 = 0;
        } else {
            v3_16 = v12_11;
        }
        int v2_15 = this.getLifecycle();
        vn.com.zalopay.auth.presentation.smartotp.SmartOTPActivity v1_14 = this;
        kotlinx.coroutines.BuildersKt.launch$default(androidx.lifecycle.l0.i(v2_15), 0, 0, new QP.C(v2_15, v3_16, 0, this, 22), 3, 0);
        kotlinx.coroutines.BuildersKt.launch$default(androidx.lifecycle.l0.i(this.getLifecycle()), 0, 0, new US.d(this, 0, 3), 3, 0);
        int v12_4 = this.getIntent().getStringExtra("extra_params");
        int v2_1 = "";
        if (v12_4 == 0) {
            v12_4 = "";
        }
        this.n = v12_4;
        int v12_5 = q9.v7.b(v12_4);
        if (v12_5 == 0) {
            v12_5 = kotlin.collections.a0.emptyMap();
        }
        String v3_1 = this.getIntent().getStringExtra("source");
        if (v3_1 == null) {
            v3_1 = "";
        }
        this.q = v3_1;
        String v3_3 = this.getIntent().getStringExtra("request_id");
        if (v3_3 == null) {
            v3_3 = "";
        }
        this.o = v3_3;
        String v3_5 = this.getIntent().getStringExtra("external_request_id");
        if (v3_5 == null) {
            v3_5 = "";
        }
        ZS.a v0_4;
        this.p = v3_5;
        this.s = this.getIntent().getIntExtra("from_zmp", 0);
        ZS.a v0_3 = v12_5.get("skip_pin_for_change_pin");
        if (!(v0_3 instanceof String)) {
            v0_4 = 0;
        } else {
            v0_4 = ((String) v0_3);
        }
        if (v0_4 != null) {
            v2_1 = v0_4;
        }
        this.r = v2_1;
        this.t = v12_5;
        ZS.a.a.c(v1_14.u, v1_14.q, v12_5);
        kotlinx.coroutines.BuildersKt.launch$default(androidx.lifecycle.l0.i(this.getLifecycle()), 0, 0, new US.d(this, 0, 1), 3, 0);
        return;
    }

    public final void onDestroy()
    {
        ZS.a.a.a(this.u);
        super.onDestroy();
        return;
    }

    public final void onResume()
    {
        super.onResume();
        ZS.a.a.c(this.u, this.q, this.t);
        return;
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
