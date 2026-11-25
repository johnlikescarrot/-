package vn.com.zalopay.balance_liquidation.presentation;
public final class BalanceLiquidationActivity extends androidx.appcompat.app.AppCompatActivity {
    public static final synthetic int k;
    public KG.a i;
    public CT.d j;

    public BalanceLiquidationActivity()
    {
        return;
    }

    public final void onCreate(android.os.Bundle p13)
    {
        super.onCreate(p13);
        y0.b v0_0 = com.google.android.gms.internal.mlkit_vision_barcode.l5.a;
        androidx.lifecycle.A v13_5 = new rv.b(6, v0_0);
        androidx.lifecycle.A v13_2 = dagger.internal.f.b(new nA.w(v13_5, dagger.internal.f.b(new ou.e(20, v13_5)), 29));
        int v1_0 = v0_0.b();
        p9.n7.c(v1_0);
        this.i = v1_0;
        androidx.lifecycle.A v13_4 = ((wT.a) v13_2.get());
        androidx.lifecycle.x v3_2 = new kc.b();
        int v5_2 = ((CC.k) v0_0.b).getContext();
        p9.n7.b(v5_2);
        rx.b v2_3 = new pO.c(v3_2, new AT.a(v5_2));
        androidx.lifecycle.x v3_4 = new CT.r();
        v3_4.a = kotlin.collections.E.emptyList();
        y0.b v0_4 = ((String) ((dagger.internal.o) v0_0.g).get());
        p9.n7.c(v0_4);
        this.j = new CT.d(v13_4, v2_3, v3_4, v0_4);
        androidx.lifecycle.A v13_7 = this.getIntent().getIntExtra("source_id", 0);
        byte vtmp11 = this.getIntent().getByteExtra("skip_result", 0);
        int v1_6 = this.j;
        if (v1_6 == 0) {
            v1_6 = 0;
        }
        int v4_3;
        v1_6.m0(new CT.f(v13_7, vtmp11));
        androidx.lifecycle.A v13_8 = com.google.android.gms.internal.mlkit_vision_barcode.l5.a;
        v13_8.getClass();
        n9.O.a = new j9.q(17, v13_8);
        k.e.a(this, new y0.b(new qH.c(9, this), 1, -1615459893));
        androidx.lifecycle.A v13_11 = this.j;
        if (v13_11 == null) {
            v4_3 = 0;
        } else {
            v4_3 = v13_11;
        }
        androidx.lifecycle.x v3_5 = this.getLifecycle();
        kotlinx.coroutines.BuildersKt.launch$default(androidx.lifecycle.l0.i(v3_5), 0, 0, new rx.b(v3_5, v4_3, 0, this, 15), 3, 0);
        return;
    }
}
