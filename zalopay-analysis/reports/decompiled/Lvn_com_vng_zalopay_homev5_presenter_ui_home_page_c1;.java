package vn.com.vng.zalopay.homev5.presenter.ui.home_page;
public final class c1 implements ji.i {
    public final synthetic int a;
    public final synthetic ji.i b;
    public final synthetic vn.com.vng.zalopay.homev5.presenter.ui.home_page.g1 c;

    public synthetic c1(ji.i p1, vn.com.vng.zalopay.homev5.presenter.ui.home_page.g1 p2, int p3)
    {
        this.a = p3;
        this.b = p1;
        this.c = p2;
        return;
    }

    public final Object e(ji.j p4, kotlin.coroutines.Continuation p5)
    {
        switch (this.a) {
            case 0:
                kotlin.Unit v4_1 = this.b.e(new vn.com.vng.zalopay.homev5.presenter.ui.home_page.b1(p4, this.c, 0), p5);
                if (v4_1 != vg.i.getCOROUTINE_SUSPENDED()) {
                    v4_1 = kotlin.Unit.INSTANCE;
                }
                return v4_1;
            default:
                kotlin.Unit v4_3 = this.b.e(new vn.com.vng.zalopay.homev5.presenter.ui.home_page.b1(p4, this.c, 1), p5);
                if (v4_3 != vg.i.getCOROUTINE_SUSPENDED()) {
                    v4_3 = kotlin.Unit.INSTANCE;
                }
                return v4_3;
        }
    }
}
