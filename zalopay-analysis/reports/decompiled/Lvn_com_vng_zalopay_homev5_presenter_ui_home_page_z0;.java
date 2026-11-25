package vn.com.vng.zalopay.homev5.presenter.ui.home_page;
public final synthetic class z0 implements kotlin.jvm.functions.Function0 {
    public final synthetic int a;
    public final synthetic i3.b b;

    public synthetic z0(i3.b p1, int p2)
    {
        this.a = p2;
        this.b = p1;
        return;
    }

    public final Object invoke()
    {
        switch (this.a) {
            case 0:
                kotlin.Pair v0_5 = this.b;
                return kotlin.TuplesKt.to(v0_5.e().a, v0_5.e().c);
            default:
                kotlin.Pair v0_2 = this.b;
                return kotlin.TuplesKt.to(Integer.valueOf(v0_2.d()), v0_2.e().c);
        }
    }
}
