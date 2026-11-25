package vn.com.vng.zalopay.webview.ui;
public final class a extends android.webkit.WebChromeClient {
    public final synthetic vn.com.vng.zalopay.webview.ui.WebViewFragment a;

    public a(vn.com.vng.zalopay.webview.ui.WebViewFragment p1)
    {
        this.a = p1;
        return;
    }

    public final void onProgressChanged(android.webkit.WebView p5, int p6)
    {
        android.widget.ProgressBar v5_0 = this.a;
        android.widget.ProgressBar v0_0 = v5_0.mProgressBar;
        if (v0_0 != null) {
            if ((p6 < 100) && (v0_0.getVisibility() == 8)) {
                v5_0.mProgressBar.setVisibility(0);
            }
            v5_0.mProgressBar.setProgress(p6);
            if (p6 >= 100) {
                v5_0.mProgressBar.setVisibility(8);
            }
        }
        return;
    }

    public final void onReceivedTitle(android.webkit.WebView p4, String p5)
    {
        super.onReceivedTitle(p4, p5);
        android.os.Bundle v4_1 = this.a;
        androidx.fragment.app.FragmentActivity v0 = v4_1.getActivity();
        if ((v0 != null) && (!v0.isFinishing())) {
            if (!v4_1.y.a()) {
                p5 = v0.getString(2131951830);
            }
            if ((v4_1.getArguments() != null) && (v4_1.getArguments().containsKey("title"))) {
                p5 = v4_1.getArguments().getString("title");
            }
            v0.setTitle(p5);
        }
        return;
    }
}
