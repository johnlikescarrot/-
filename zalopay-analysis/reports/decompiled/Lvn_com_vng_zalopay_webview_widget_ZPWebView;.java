package vn.com.vng.zalopay.webview.widget;
public class ZPWebView extends android.webkit.WebView {
    public static final synthetic int c;
    public final java.util.ArrayList a;
    public final java.util.HashMap b;

    public ZPWebView(android.content.Context p2, android.util.AttributeSet p3)
    {
        super(p2, p3);
        super.getSettings().setJavaScriptEnabled(1);
        super.getSettings().setDomStorageEnabled(1);
        super.getSettings().setJavaScriptCanOpenWindowsAutomatically(1);
        super.getSettings().setBlockNetworkImage(0);
        super.getSettings().setLoadWithOverviewMode(1);
        super.getSettings().setLoadsImagesAutomatically(1);
        super.getSettings().setSavePassword(0);
        super.getSettings().setSaveFormData(0);
        super.getSettings().setMixedContentMode(2);
        com.google.android.gms.internal.mlkit_vision_barcode.G0.b(super.getContext(), super);
        super.setWebChromeClient(new qm.j(1));
        super.a = new java.util.ArrayList();
        super.b = new java.util.HashMap();
        return;
    }

    public final void a(String p7)
    {
        if (!com.google.android.gms.internal.mlkit_vision_barcode.p0.b(this.a)) {
            String[] v0_9 = this.a.iterator();
            while (v0_9.hasNext()) {
                String v1_1 = ((String) v0_9.next());
                if ((!android.text.TextUtils.isEmpty(v1_1)) && (!v1_1.equals(p7))) {
                    this.b.put(v1_1, android.webkit.CookieManager.getInstance().getCookie(v1_1));
                }
            }
        }
        android.webkit.CookieManager.getInstance().removeAllCookies(0);
        android.webkit.CookieManager.getInstance().removeSessionCookies(new iR.n(1));
        android.webkit.CookieManager.getInstance().flush();
        java.util.HashMap v7_4 = this.b;
        if ((v7_4 != null) && (v7_4.size() > 0)) {
            java.util.HashMap v7_8 = this.b.entrySet().iterator();
            while (v7_8.hasNext()) {
                String[] v0_8 = ((java.util.Map$Entry) v7_8.next());
                if (v0_8 != null) {
                    String v1_4 = ((String) v0_8.getKey());
                    String[] v0_11 = ((String) v0_8.getValue());
                    if ((!android.text.TextUtils.isEmpty(v1_4)) && (!android.text.TextUtils.isEmpty(v0_11))) {
                        if (!v0_11.contains(";")) {
                            android.webkit.CookieManager.getInstance().setCookie(v1_4, v0_11);
                        }
                        String[] v0_12 = v0_11.split(";", -1);
                        if (v0_12.length > 0) {
                            int v2_8 = v0_12.length;
                            int v3_4 = 0;
                            while (v3_4 < v2_8) {
                                String v4_0 = v0_12[v3_4];
                                if (!android.text.TextUtils.isEmpty(v4_0)) {
                                    android.webkit.CookieManager.getInstance().setCookie(v1_4, v4_0.trim());
                                }
                                v3_4++;
                            }
                        }
                    }
                }
            }
            this.b.clear();
        }
        return;
    }
}
