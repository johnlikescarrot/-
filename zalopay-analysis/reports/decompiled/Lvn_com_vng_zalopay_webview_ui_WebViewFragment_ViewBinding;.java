package vn.com.vng.zalopay.webview.ui;
public class WebViewFragment_ViewBinding implements butterknife.Unbinder {
    public vn.com.vng.zalopay.webview.ui.WebViewFragment b;
    public android.view.View c;

    public WebViewFragment_ViewBinding(vn.com.vng.zalopay.webview.ui.WebViewFragment p5, android.view.View p6)
    {
        this.b = p5;
        p5.mLayoutRetry = p6.findViewById(2131363391);
        p5.mImgError = ((android.widget.ImageView) b4.e.a(p6.findViewById(2131363099), 2131363099, "field \'mImgError\'", android.widget.ImageView));
        p5.mTvError = ((android.widget.TextView) b4.e.a(p6.findViewById(2131364632), 2131364632, "field \'mTvError\'", android.widget.TextView));
        p5.mProgressBar = ((android.widget.ProgressBar) b4.e.a(p6.findViewById(2131363919), 2131363919, "field \'mProgressBar\'", android.widget.ProgressBar));
        android.view.View v6_1 = p6.findViewById(2131362112);
        if (v6_1 != null) {
            this.c = v6_1;
            v6_1.setOnClickListener(new jR.k(p5, 2));
        }
        return;
    }

    public final void a()
    {
        IllegalStateException v0_0 = this.b;
        if (v0_0 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        } else {
            this.b = 0;
            v0_0.mLayoutRetry = 0;
            v0_0.mImgError = 0;
            v0_0.mTvError = 0;
            v0_0.mProgressBar = 0;
            IllegalStateException v0_1 = this.c;
            if (v0_1 != null) {
                v0_1.setOnClickListener(0);
                this.c = 0;
            }
            return;
        }
    }
}
