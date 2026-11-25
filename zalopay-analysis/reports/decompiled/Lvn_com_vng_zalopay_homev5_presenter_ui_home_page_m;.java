package vn.com.vng.zalopay.homev5.presenter.ui.home_page;
public final class m extends kotlin.jvm.internal.Lambda implements kotlin.jvm.functions.Function0 {
    public final synthetic int d;
    public final synthetic vn.com.vng.zalopay.homev5.presenter.ui.home_page.n e;

    public synthetic m(vn.com.vng.zalopay.homev5.presenter.ui.home_page.n p1, int p2)
    {
        this.d = p2;
        this.e = p1;
        super(0);
        return;
    }

    public final Object invoke()
    {
        switch (this.d) {
            case 0:
                return this.e.requireActivity().getViewModelStore();
            case 1:
                return this.e.requireActivity().getDefaultViewModelCreationExtras();
            default:
                return this.e;
        }
    }
}
