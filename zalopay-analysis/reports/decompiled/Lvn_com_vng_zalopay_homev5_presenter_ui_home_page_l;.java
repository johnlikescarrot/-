package vn.com.vng.zalopay.homev5.presenter.ui.home_page;
public final synthetic class l implements kotlin.jvm.functions.Function0 {
    public final synthetic int a;
    public final synthetic vn.com.vng.zalopay.homev5.presenter.ui.home_page.n b;

    public synthetic l(vn.com.vng.zalopay.homev5.presenter.ui.home_page.n p1, int p2)
    {
        this.a = p2;
        this.b = p1;
        return;
    }

    public final Object invoke()
    {
        switch (this.a) {
            case 0:
                return new Cx.h(16, this.b);
            case 1:
                Boolean v0_7 = this.b.r;
                if (v0_7 == null) {
                    v0_7 = 0;
                }
                return v0_7;
            default:
                Boolean v0_4;
                Boolean v0_3 = this.b.getArguments();
                if (v0_3 == null) {
                    v0_4 = 0;
                } else {
                    v0_4 = v0_3.getBoolean("popup_blocked");
                }
                return Boolean.valueOf(v0_4);
        }
    }
}
