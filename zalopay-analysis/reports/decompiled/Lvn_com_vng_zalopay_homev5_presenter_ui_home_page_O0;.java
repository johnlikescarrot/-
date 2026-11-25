package vn.com.vng.zalopay.homev5.presenter.ui.home_page;
public final synthetic class O0 implements kotlin.jvm.functions.Function2 {
    public final synthetic int a;
    public final synthetic float b;
    public final synthetic Object c;
    public final synthetic int d;

    public synthetic O0(float p1, D0.q p2, int p3, int p4)
    {
        this.a = p4;
        this.b = p1;
        this.c = p2;
        this.d = p3;
        return;
    }

    public synthetic O0(uA.m p2, float p3, int p4)
    {
        this.a = 2;
        this.c = p2;
        this.b = p3;
        this.d = p4;
        return;
    }

    public final Object invoke(Object p3, Object p4)
    {
        switch (this.a) {
            case 0:
                D0.q v0_2 = ((D0.q) this.c);
                ((Integer) p4).getClass();
                vn.com.vng.zalopay.homev5.presenter.ui.home_page.P0.c(this.b, q0.d.S((this.d | 1)), v0_2, ((q0.s) p3));
                return kotlin.Unit.INSTANCE;
            case 1:
                D0.q v0_6 = ((D0.q) this.c);
                ((Integer) p4).getClass();
                vn.com.vng.zalopay.homev5.presenter.ui.home_page.P0.e(this.b, q0.d.S((this.d | 1)), v0_6, ((q0.s) p3));
                return kotlin.Unit.INSTANCE;
            default:
                D0.q v0_5 = ((uA.m) this.c);
                ((Integer) p4).getClass();
                v0_5.a(this.b, ((q0.s) p3), q0.d.S((this.d | 1)));
                return kotlin.Unit.INSTANCE;
        }
    }
}
