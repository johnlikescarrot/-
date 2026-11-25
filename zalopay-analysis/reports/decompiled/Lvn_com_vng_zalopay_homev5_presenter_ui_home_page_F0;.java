package vn.com.vng.zalopay.homev5.presenter.ui.home_page;
public final class F0 implements ji.i {
    public final synthetic int a;
    public final synthetic ji.c0 b;

    public synthetic F0(ji.c0 p1, int p2)
    {
        this.a = p2;
        this.b = p1;
        return;
    }

    public final Object e(ji.j p3, kotlin.coroutines.Continuation p4)
    {
        switch (this.a) {
            case 0:
                kotlin.Unit v3_1 = this.b.b.e(new nM.W(p3, 19), p4);
                if (v3_1 != vg.i.getCOROUTINE_SUSPENDED()) {
                    v3_1 = kotlin.Unit.INSTANCE;
                }
                return v3_1;
            default:
                kotlin.Unit v3_4 = this.b.b.e(new nM.W(p3, 21), p4);
                if (v3_4 != vg.i.getCOROUTINE_SUSPENDED()) {
                    v3_4 = kotlin.Unit.INSTANCE;
                }
                return v3_4;
        }
    }
}
