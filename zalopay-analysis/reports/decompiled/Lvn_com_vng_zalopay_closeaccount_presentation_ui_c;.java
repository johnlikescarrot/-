package vn.com.vng.zalopay.closeaccount.presentation.ui;
public final class c extends android.app.Dialog {
    public final synthetic vn.com.vng.zalopay.closeaccount.presentation.ui.b a;

    public c(vn.com.vng.zalopay.closeaccount.presentation.ui.b p1, androidx.fragment.app.FragmentActivity p2, int p3)
    {
        this.a = p1;
        super(p2, p3);
        return;
    }

    public final void onBackPressed()
    {
        vn.com.vng.zalopay.closeaccount.presentation.ui.a v0_1 = this.a.H;
        if (v0_1 != null) {
            v0_1.a();
        }
        return;
    }
}
