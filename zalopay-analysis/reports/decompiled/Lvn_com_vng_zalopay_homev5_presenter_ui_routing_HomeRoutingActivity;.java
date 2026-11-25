package vn.com.vng.zalopay.homev5.presenter.ui.routing;
public final class HomeRoutingActivity extends vn.com.vng.zalopay.homev5.presenter.base.BaseComposeActivity {
    public static final synthetic int p;
    public final androidx.lifecycle.r0 j;
    public final KG.a k;
    public final m6.c l;
    public final sA.g m;
    public final O5.b n;
    public String o;

    public HomeRoutingActivity()
    {
        this.n = new O5.b(kotlin.jvm.internal.M.a.b(PB.j), new PB.a(this, 0), new BF.a(20, this), new PB.a(this, 1));
        aA.a v0_1 = com.google.android.gms.internal.mlkit_vision_barcode.i6.b;
        if (v0_1 == null) {
            aA.a v0_2 = com.google.android.gms.internal.mlkit_vision_barcode.i6.a;
            if (v0_2 == null) {
                throw new IllegalStateException("envComponent must be initialized first by calling HomeV5Plugin.install()");
            } else {
                String v1_4 = new XQ.i(9, v0_2);
                aA.a v0_5 = com.google.android.gms.internal.mlkit_vision_barcode.i6.c;
                if (v0_5 == null) {
                    v0_5 = gi.N.d();
                    com.google.android.gms.internal.mlkit_vision_barcode.i6.c = v0_5;
                }
                aA.a v3_3 = new aA.a(v1_4, new androidx.camera.camera2.impl.a(7, gi.L.a(v0_5)), new kX.d());
                com.google.android.gms.internal.mlkit_vision_barcode.i6.b = v3_3;
                v0_1 = v3_3;
            }
        }
        this.j = ((androidx.lifecycle.r0) v0_1.K.get());
        String v1_11 = ((aA.c) v0_1.a.b);
        androidx.camera.camera2.impl.a v2_4 = v1_11.b();
        p9.n7.c(v2_4);
        this.k = v2_4;
        String v1_12 = v1_11.b();
        p9.n7.c(v1_12);
        this.l = new m6.c(9, v1_12);
        this.m = ((sA.g) v0_1.L.get());
        return;
    }

    public final void e1(int p3, q0.s p4)
    {
        q0.Y0 v4_2 = p4.x(-1646501768);
        if (((p3 & 3) == 2) && (v4_2.c())) {
            v4_2.m();
        } else {
            m9.W2.a(6, v4_2);
        }
        q0.Y0 v4_1 = v4_2.Y();
        if (v4_1 != null) {
            v4_1.d = new AA.e(this, p3, 16);
        }
        return;
    }

    public final void onCreate(android.os.Bundle p8)
    {
        if (p8 == null) {
            boolean v1_2 = this.m;
            if (!v1_2) {
                v1_2 = 0;
            }
            v1_2.start();
        }
        super.onCreate(p8);
        androidx.activity.q.a(this, 0, 3);
        String v8_2 = this.getIntent().getExtras();
        if (v8_2 != null) {
            String v8_3 = v8_2.get("tab");
            if ((v8_3 != null) && ((v8_3 instanceof String))) {
                this.o = ((String) v8_3);
            }
        }
        kotlinx.coroutines.BuildersKt.launch$default(androidx.lifecycle.l0.i(this.getLifecycle()), 0, 0, new AA.g(this, 0, 27), 3, 0);
        return;
    }

    public final void onResume()
    {
        super.onResume();
        ((PB.j) this.n.getValue()).o0(PB.g.a);
        return;
    }
}
