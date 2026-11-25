package vn.com.vng.zalopay.cashier.middle;
public final class CashierMiddlePageActivity extends androidx.appcompat.app.AppCompatActivity {
    public static final synthetic int p;
    public final ng.S i;
    public final ng.S j;
    public final ng.S k;
    public St.a l;
    public rp.f m;
    public final O5.b n;
    public Yv.i o;

    public CashierMiddlePageActivity()
    {
        this.i = ng.x.b(new rp.b(this, 0));
        this.j = ng.x.b(new rp.b(this, 1));
        this.k = ng.x.b(new rp.b(this, 2));
        this.n = new O5.b(kotlin.jvm.internal.M.a.b(rp.h), new rp.e(this, 0), new rp.b(this, 3), new rp.e(this, 1));
        return;
    }

    public final void e1(int p3, q0.s p4)
    {
        q0.Y0 v4_2 = p4.x(-1570126804);
        if (((p3 & 1) == 0) && (v4_2.c())) {
            v4_2.m();
        } else {
            androidx.compose.foundation.layout.w.a(androidx.compose.foundation.layout.P0.e(D0.p.a, 1065353216), v4_2, 6);
        }
        q0.Y0 v4_1 = v4_2.Y();
        if (v4_1 != null) {
            v4_1.d = new rp.a(this, p3);
        }
        return;
    }

    public final sp.g g1()
    {
        return ((sp.g) this.i.getValue());
    }

    public final void onCreate(android.os.Bundle p9)
    {
        super.onCreate(p9);
        q9.a5.b().Z1(this);
        p9.U.a(this.getWindow(), 0);
        this.getWindow().setStatusBarColor(0);
        this.getOnBackPressedDispatcher().a(this, new rp.d(1));
        rp.a v9_2 = this.n;
        q9.d5.a(this, ((rp.h) v9_2.getValue()).f, new jV.k(20, this));
        rp.a v9_5 = ((rp.h) v9_2.getValue());
        v9_5.getClass();
        kotlinx.coroutines.BuildersKt.launch$default(androidx.lifecycle.l0.l(v9_5), 0, 0, new rp.g(v9_5, 0, 2), 3, 0);
        k.e.a(this, new y0.b(new rp.a(this), 1, 918401982));
        return;
    }
}
