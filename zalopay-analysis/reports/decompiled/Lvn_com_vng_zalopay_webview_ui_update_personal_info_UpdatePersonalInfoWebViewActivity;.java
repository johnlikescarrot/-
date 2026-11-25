package vn.com.vng.zalopay.webview.ui.update_personal_info;
public final class UpdatePersonalInfoWebViewActivity extends vn.com.vng.zalopay.webview.ui.WebViewActivity {

    public UpdatePersonalInfoWebViewActivity()
    {
        return;
    }

    public final bridge synthetic androidx.fragment.app.Fragment g1()
    {
        return this.o1();
    }

    public final Ts.d o1()
    {
        int v0_1;
        int v0_0 = this.getIntent();
        if (v0_0 == 0) {
            v0_1 = 0;
        } else {
            v0_1 = v0_0.getExtras();
        }
        kR.f v1_1 = new kR.f();
        v1_1.setArguments(v0_1);
        return v1_1;
    }
}
