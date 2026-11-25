package vn.zalopay.ui.webview;
public final class NonClickableWebView extends android.webkit.WebView {

    public NonClickableWebView(android.content.Context p2, android.util.AttributeSet p3)
    {
        super(p2, p3);
        android.webkit.WebSettings v2_1 = super.getSettings();
        v2_1.setJavaScriptEnabled(1);
        super.setVerticalScrollBarEnabled(0);
        v2_1.setUseWideViewPort(1);
        return;
    }

    public final boolean onTouchEvent(android.view.MotionEvent p1)
    {
        return 0;
    }
}
