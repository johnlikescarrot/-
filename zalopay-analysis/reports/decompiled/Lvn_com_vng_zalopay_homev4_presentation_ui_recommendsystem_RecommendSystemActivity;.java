package vn.com.vng.zalopay.homev4.presentation.ui.recommendsystem;
public final class RecommendSystemActivity extends androidx.activity.ComponentActivity {
    public static final synthetic int l;
    public ZU.e i;
    public final O5.b j;
    public KG.a k;

    public RecommendSystemActivity()
    {
        this.j = new O5.b(kotlin.jvm.internal.M.a.b(ez.m), new ez.j(this, 0), new bJ.d(10, this), new ez.j(this, 1));
        return;
    }

    public final void onCreate(android.os.Bundle p5)
    {
        super.onCreate(p5);
        if (p9.F4.c == null) {
            p9.F4.c = gi.N.d();
        }
        ez.i v5_3 = p9.F4.b;
        if (v5_3 == null) {
            St.a v3_1 = new vy.a(new wy.b(p9.F4.a), new jQ.B(9), new pO.c(15, gi.L.a(p9.F4.c)), new l2.j());
            p9.F4.b = v3_1;
            v5_3 = v3_1;
        }
        y0.b v1_5 = v5_3.a.a;
        this.i = new ZU.e(v1_5.n(), v1_5.a(), ((Cy.a) v5_3.n.get()));
        ez.i v5_8 = v1_5.b();
        p9.n7.c(v5_8);
        this.k = v5_8;
        p9.U.a(this.getWindow(), 1);
        ((ez.m) this.j.getValue()).getClass();
        k.e.a(this, new y0.b(new ez.i(this, 0), 1, 205903282));
        return;
    }
}
