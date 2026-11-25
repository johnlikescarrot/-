package vn.com.vng.zalopay.bankbinding.presentation.ui.webapp;
public final class BankWebView extends vn.com.vng.webapp.framework.ZPWebViewApp {
    public static final synthetic int c;
    public final androidx.lifecycle.T a;
    public final androidx.lifecycle.T b;

    public BankWebView(android.content.Context p2, android.util.AttributeSet p3)
    {
        super(p2, p3, 0);
        super.getSettings().setJavaScriptEnabled(1);
        super.getSettings().setDomStorageEnabled(1);
        super.getSettings().setSupportZoom(1);
        super.getSettings().setDisplayZoomControls(0);
        super.getSettings().setBuiltInZoomControls(1);
        super.getSettings().setMixedContentMode(2);
        super.getSettings().setUserAgentString("Mozilla/5.0 (Linux; Android 5.1.1; Galaxy Nexus Build/IMM76B) AppleWebKit/535.19 (KHTML, like Gecko) Chrome/18.0.1025.133 Mobile Safari/535.19 ZaloPayClient/11.17.0");
        super.getSettings().setMediaPlaybackRequiresUserGesture(0);
        androidx.lifecycle.T v2_9 = new androidx.lifecycle.T();
        super.a = v2_9;
        super.b = v2_9;
        return;
    }

    public final androidx.lifecycle.N getWebJsEvent()
    {
        return this.b;
    }
}
