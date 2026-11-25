package vn.com.vng.zalopay.cashier.buffer;
public final class CashierBufferActivity extends androidx.appcompat.app.AppCompatActivity {
    public static final synthetic int n;
    public Yo.d i;
    public final O5.b j;
    public Wo.d k;
    public Yv.i l;
    public final ng.S m;

    public CashierBufferActivity()
    {
        this.j = new O5.b(kotlin.jvm.internal.M.a.b(Yo.f), new Yo.c(this, 0), new Yo.a(this, 0), new Yo.c(this, 1));
        this.m = ng.x.b(new Yo.a(this, 1));
        return;
    }

    public final void e1(int p13, q0.s p14)
    {
        q0.v v9 = p14.x(1623941143);
        if (((p13 & 1) == 0) && (v9.c())) {
            v9.m();
        } else {
            int v14_6 = D0.p.a;
            D0.q v1_2 = androidx.compose.foundation.a.b(androidx.compose.foundation.layout.c.E(androidx.compose.foundation.layout.P0.e(v14_6, 1065353216)), K0.y.f, K0.X.a);
            int v2_2 = androidx.compose.foundation.layout.D.a(androidx.compose.foundation.layout.r.c, D0.b.m, v9, 0);
            String v3_1 = v9.P;
            int v4_2 = v9.S();
            D0.q v1_3 = D0.s.c(v1_2, v9);
            c1.m.d2.getClass();
            v9.l();
            if (!v9.O) {
                v9.h();
            } else {
                v9.K(c1.l.b);
            }
            q0.d.N(v2_2, c1.l.g, v9);
            q0.d.N(v4_2, c1.l.f, v9);
            if ((v9.O) || (!kotlin.jvm.internal.Intrinsics.areEqual(v9.G(), Integer.valueOf(v3_1)))) {
                Kb.e.t(v3_1, v9, v3_1, c1.l.i);
            }
            q0.d.N(v1_3, c1.l.d, v9);
            com.google.android.gms.internal.mlkit_vision_barcode.m5.c(androidx.compose.foundation.layout.P0.i(androidx.compose.foundation.layout.P0.g(v14_6, 1065353216), ((float) 44)), 0, "X\u00e1c nh\u1eadn giao d\u1ecbch", 0, 0, 0, 0, 0, v9, 390, 250);
            v9.W(1);
        }
        int v14_5 = v9.Y();
        if (v14_5 != 0) {
            v14_5.d = new Yo.b(this, p13);
        }
        return;
    }

    public final void onBackPressed()
    {
        return;
    }

    public final void onCreate(android.os.Bundle p13)
    {
        int v4;
        super.onCreate(p13);
        q9.a5.b().I(this);
        k.e.a(this, new y0.b(new Yo.b(this), 1, 692650409));
        com.google.android.gms.internal.mlkit_vision_barcode.T7.g(this, "Loading");
        androidx.lifecycle.T v13_2 = this.j;
        Yo.h v3_1 = ((Yo.f) v13_2.getValue());
        String v5_1 = ((String) this.m.getValue());
        v3_1.getClass();
        if (v5_1 == null) {
            v4 = 0;
        } else {
            v4 = ((java.util.Map) q9.s6.a(v5_1, java.util.Map));
        }
        SM.l0 v0_6;
        if (v4 != 0) {
            v0_6 = v4;
        } else {
            v0_6 = kotlin.collections.a0.emptyMap();
        }
        Vv.c v2_1;
        UR.a.e("01.1124.001", v0_6);
        SM.l0 v0_7 = n9.a0.a();
        if (v0_7 == null) {
            v2_1 = 0;
        } else {
            v2_1 = v0_7.g1();
        }
        Vv.c v2_3;
        if (v2_1 != null) {
            v2_3 = Yo.e.a[v2_1.ordinal()];
        } else {
            v2_3 = -1;
        }
        if (v2_3 != 1) {
            kotlinx.coroutines.BuildersKt.launch$default(androidx.lifecycle.l0.l(v3_1), 0, 0, new Vv.c(v3_1, v4, v5_1, 0, 16), 3, 0);
        } else {
            if (v5_1 == null) {
                v5_1 = "";
            }
            v3_1.e.j(new Xs.b(new Yo.h(v0_7, v5_1)));
        }
        q9.d5.a(this, ((Yo.f) v13_2.getValue()).f, new SM.l0(12, this));
        return;
    }

    public final void onDestroy()
    {
        com.google.android.gms.internal.mlkit_vision_barcode.T7.c(this, "Loading");
        super.onDestroy();
        return;
    }
}
