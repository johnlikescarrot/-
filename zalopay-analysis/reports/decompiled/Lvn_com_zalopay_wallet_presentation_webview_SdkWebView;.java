package vn.com.zalopay.wallet.presentation.webview;
public final class SdkWebView extends android.webkit.WebView {

    public SdkWebView(android.content.Context p2, android.util.AttributeSet p3)
    {
        super(p2, p3);
        super.getSettings().setJavaScriptEnabled(1);
        super.getSettings().setDomStorageEnabled(1);
        super.getSettings().setSupportZoom(1);
        super.getSettings().setBuiltInZoomControls(1);
        super.getSettings().setDisplayZoomControls(0);
        super.getSettings().setJavaScriptCanOpenWindowsAutomatically(1);
        super.getSettings().setBlockNetworkImage(0);
        super.getSettings().setLoadsImagesAutomatically(1);
        super.getSettings().setLoadWithOverviewMode(1);
        super.getSettings().setMixedContentMode(2);
        super.getSettings().setUserAgentString("Mozilla/5.0 (Linux; Android 4.0.4; Galaxy Nexus Build/IMM76B) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/46.0.2490.76 Mobile Safari/537.36");
        return;
    }
}
