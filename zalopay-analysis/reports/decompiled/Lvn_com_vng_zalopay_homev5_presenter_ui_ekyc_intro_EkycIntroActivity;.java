package vn.com.vng.zalopay.homev5.presenter.ui.ekyc_intro;
public final class EkycIntroActivity extends vn.com.vng.zalopay.homev5.presenter.base.BaseComposeActivity {
    public static final synthetic int n;
    public final androidx.lifecycle.r0 j;
    public final m6.c k;
    public final O5.b l;
    public final ng.S m;

    public EkycIntroActivity()
    {
        this.l = new O5.b(kotlin.jvm.internal.M.a.b(AA.B), new AA.h(this, 0), new AA.c(this, 0), new AA.h(this, 1));
        this.m = ng.x.b(new AA.c(this, 1));
        aA.a v0_4 = com.google.android.gms.internal.mlkit_vision_barcode.i6.b;
        if (v0_4 == null) {
            aA.a v0_5 = com.google.android.gms.internal.mlkit_vision_barcode.i6.a;
            if (v0_5 == null) {
                throw new IllegalStateException("envComponent must be initialized first by calling HomeV5Plugin.install()");
            } else {
                String v1_5 = new XQ.i(9, v0_5);
                aA.a v0_9 = com.google.android.gms.internal.mlkit_vision_barcode.i6.c;
                if (v0_9 == null) {
                    v0_9 = gi.N.d();
                    com.google.android.gms.internal.mlkit_vision_barcode.i6.c = v0_9;
                }
                aA.a v3_3 = new aA.a(v1_5, new androidx.camera.camera2.impl.a(7, gi.L.a(v0_9)), new kX.d());
                com.google.android.gms.internal.mlkit_vision_barcode.i6.b = v3_3;
                v0_4 = v3_3;
            }
        }
        this.j = ((androidx.lifecycle.r0) v0_4.K.get());
        aA.a v0_16 = ((aA.c) v0_4.a.b).b();
        p9.n7.c(v0_16);
        this.k = new m6.c(9, v0_16);
        return;
    }

    public final void e1(int p5, q0.s p6)
    {
        AA.e v0_8;
        q0.Y0 v6_1 = p6.x(-1867581263);
        if (!v6_1.I(this)) {
            v0_8 = 16;
        } else {
            v0_8 = 32;
        }
        if ((((v0_8 | p5) & 19) == 18) && (v6_1.c())) {
            v6_1.m();
        } else {
            AA.e v0_4 = ((AA.B) this.l.getValue());
            int v1_3 = ((BA.a) this.m.getValue());
            if (v1_3 == 0) {
                v1_3 = new BA.a(5000, 1000, 1000);
            }
            AA.A.b(v0_4, v1_3, v6_1, 384);
        }
        q0.Y0 v6_2 = v6_1.Y();
        if (v6_2 != null) {
            v6_2.d = new AA.e(this, p5, 0);
        }
        return;
    }

    public final void onCreate(android.os.Bundle p10)
    {
        super.onCreate(p10);
        androidx.activity.q.a(this, 0, 3);
        n9.s4.a(this.getOnBackPressedDispatcher(), this, new AA.d(0));
        kotlinx.coroutines.BuildersKt.launch$default(androidx.lifecycle.l0.i(this.getLifecycle()), 0, 0, new AA.g(this, 0, 0), 3, 0);
        rN.b.c("ekyc_process_result_subscriber", kotlin.jvm.internal.M.a.b(sN.c), androidx.lifecycle.l0.i(this.getLifecycle()), kotlinx.coroutines.Dispatchers.getMain(), 0, new AA.k(this, 0, 0));
        return;
    }

    public final void onDestroy()
    {
        super.onDestroy();
        rN.b.d("ekyc_process_result_subscriber");
        return;
    }
}
