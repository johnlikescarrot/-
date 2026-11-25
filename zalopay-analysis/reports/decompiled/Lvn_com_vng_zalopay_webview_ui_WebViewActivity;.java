package vn.com.vng.zalopay.webview.ui;
public class WebViewActivity extends vn.com.vng.zalopay.user.UserBaseToolBarActivity {
    public final OD.a r;

    public WebViewActivity()
    {
        this.r = new OD.a("", -1);
        return;
    }

    public bridge synthetic androidx.fragment.app.Fragment g1()
    {
        return this.o1();
    }

    public final int h1()
    {
        return 2131560390;
    }

    public final OD.a i1()
    {
        return this.r;
    }

    public Ts.d o1()
    {
        android.os.Bundle v0_1 = this.getIntent().getExtras();
        vn.com.vng.zalopay.webview.ui.WebViewFragment v1_1 = new vn.com.vng.zalopay.webview.ui.WebViewFragment();
        v1_1.setArguments(v0_1);
        return v1_1;
    }

    public final void onCreate(android.os.Bundle p4)
    {
        super.onCreate(p4);
        this.mNavigationBar.setIsShowCloseButton(0);
        android.widget.FrameLayout v4_4 = ((android.widget.FrameLayout) this.findViewById(2131362806));
        androidx.core.view.h0.l(v4_4, new Ek.l(v4_4.getPaddingBottom(), 1));
        if (!v4_4.isAttachedToWindow()) {
            v4_4.addOnAttachStateChangeListener(new GZ.f(1));
            return;
        } else {
            androidx.core.view.f0.c(v4_4);
            return;
        }
    }

    public final void p1(String p1, String p2)
    {
        if (!android.text.TextUtils.isEmpty(p1)) {
            this.mNavigationBar.setTitle(p1);
        }
        return;
    }

    public final void setTitle(int p2)
    {
        this.mNavigationBar.setTitle(this.getString(p2));
        return;
    }

    public final void setTitle(CharSequence p2)
    {
        this.mNavigationBar.setTitle(p2.toString());
        return;
    }
}
