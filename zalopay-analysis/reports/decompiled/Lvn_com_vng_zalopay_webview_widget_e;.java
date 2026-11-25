package vn.com.vng.zalopay.webview.widget;
public final class e extends android.webkit.WebViewClient implements vn.com.vng.zalopay.webview.widget.a {
    public String a;
    public boolean b;
    public final vn.com.vng.zalopay.webview.widget.ZPWebView c;
    public vn.com.vng.zalopay.webview.ui.WebViewFragment d;

    public e(vn.com.vng.zalopay.webview.widget.ZPWebView p2, vn.com.vng.zalopay.webview.ui.WebViewFragment p3)
    {
        this.a = "";
        this.b = 0;
        this.c = p2;
        p2.setWebViewClient(this);
        this.d = p3;
        return;
    }

    public final void a(android.webkit.WebView p5, String p6)
    {
        super.onPageFinished(p5, p6);
        if (!this.b) {
            vn.com.vng.zalopay.webview.widget.ZPWebView v5_2 = this.c;
            if (v5_2 != null) {
                try {
                    java.util.ArrayList v0_0 = v5_2.getContext().getAssets().open("webapp.js");
                    Object[] v3_2 = new byte[v0_0.available()];
                    v0_0.read(v3_2);
                    v0_0.close();
                    java.util.ArrayList v0_2 = android.util.Base64.encodeToString(v3_2, 2);
                    Object[] v3_4 = new StringBuilder("javascript:(function() {var parent = document.getElementsByTagName(\'head\').item(0);var script = document.createElement(\'script\');script.type = \'text/javascript\';script.innerHTML = window.atob(\'");
                    v3_4.append(v0_2);
                    v3_4.append("\');parent.appendChild(script);})()");
                    v5_2.loadUrl(v3_4.toString());
                } catch (java.util.ArrayList v0_5) {
                    Object[] v3_5 = new Object[0];
                    ck.b.c(v0_5, "Exception", v3_5);
                }
                v5_2.evaluateJavascript("webapp_hideHeaderZalo()", new YF.e(1));
                v5_2.evaluateJavascript("webapp_getNavigation()", new bG.i(2, this));
                this.a = p6;
                if (!android.text.TextUtils.isEmpty(p6)) {
                    String v6_2 = android.net.Uri.parse(p6).getHost();
                    if ((!android.text.TextUtils.isEmpty(v6_2)) && ((!"null".equals(v6_2)) && (!v5_2.a.contains(v6_2)))) {
                        v5_2.a.add(v6_2);
                    }
                }
                v5_2.setVisibility(0);
            }
        }
        return;
    }

    public final void onPageFinished(android.webkit.WebView p1, String p2)
    {
        this.a(p1, p2);
        return;
    }

    public final void onReceivedError(android.webkit.WebView p1, int p2, String p3, String p4)
    {
        return;
    }

    public final void onReceivedError(android.webkit.WebView p3, android.webkit.WebResourceRequest p4, android.webkit.WebResourceError p5)
    {
        int v4_2;
        this.b = 1;
        if (p5 == 0) {
            v4_2 = -1;
        } else {
            v4_2 = p5.getErrorCode();
        }
        int v5_1;
        if (p5 == 0) {
            v5_1 = 0;
        } else {
            v5_1 = p5.getDescription();
        }
        UR.a.c(22014, String.format("ErrorCode: [%s] ErrorMessage: [%s] Url: [%s]", new Object[] {Integer.valueOf(v4_2), v5_1, p3.getUrl()})), 0, 0);
        ck.b.e("Webview errorCode [%s] errorMessage [%s]", new Object[] {Integer.valueOf(v4_2), v5_1}));
        vn.com.vng.zalopay.webview.ui.WebViewFragment v3_6 = this.c;
        if (v3_6 != null) {
            v3_6.setVisibility(8);
        }
        vn.com.vng.zalopay.webview.ui.WebViewFragment v3_7 = this.d;
        if (v3_7 != null) {
            v3_7.s1();
        }
        return;
    }

    public final void onReceivedSslError(android.webkit.WebView p5, android.webkit.SslErrorHandler p6, android.net.http.SslError p7)
    {
        int v0_1;
        this.b = 1;
        if (p7 == null) {
            v0_1 = -1;
        } else {
            v0_1 = p7.getPrimaryError();
        }
        String v7_2;
        if (p7 == null) {
            v7_2 = "";
        } else {
            v7_2 = p7.toString();
        }
        UR.a.c(22014, A1.a.l(com.alibaba.ariver.commonability.device.jsapi.clipboard.a.m(v0_1, "ErrorCode: [", "] ErrorMessage: [", v7_2, "] Url: ["), p5.getUrl(), "]"), 0, 0);
        ck.b.e("Webview errorCode [%s] errorMessage [%s]", new Object[] {Integer.valueOf(v0_1), v7_2}));
        p6.cancel();
        return;
    }

    public final boolean shouldOverrideUrlLoading(android.webkit.WebView p3, android.webkit.WebResourceRequest p4)
    {
        if (p4 != null) {
            String v4_2 = p4.getUrl().toString();
            if (android.text.TextUtils.isEmpty(v4_2)) {
                return 0;
            } else {
                this.shouldOverrideUrlLoading(p3, v4_2);
                return 1;
            }
        } else {
            return 0;
        }
    }

    public final boolean shouldOverrideUrlLoading(android.webkit.WebView p5, String p6)
    {
        if (!android.text.TextUtils.isEmpty(p6)) {
            if (!p6.equalsIgnoreCase("zalopay-1://backtoapp")) {
                if (!p6.equalsIgnoreCase("zalopay-1://backtologin")) {
                    if (!p6.startsWith("zalopay-1://post")) {
                        vn.com.vng.zalopay.webview.widget.ZPWebView v2 = this.c;
                        if (!p6.contains("https://oauth.zaloapp.com/v2/auth")) {
                            if (((p6.contains("http://www.facebook.com/dialog/oauth")) || (p6.contains("http://www.facebook.com/dialog/oauth"))) && (v2 != null)) {
                                v2.a("facebook.com");
                            }
                        } else {
                            if (v2 != null) {
                                v2.a("oauth.zaloapp.com");
                            }
                        }
                        p5.loadUrl(p6);
                        return 1;
                    } else {
                        return 1;
                    }
                } else {
                    if (this.d != null) {
                        ((CC.f) q9.a5.a()).i().f(new Os.d());
                    }
                }
            } else {
                org.greenrobot.eventbus.e v5_5 = this.d;
                if (v5_5 != null) {
                    v5_5.l0();
                    return 1;
                }
            }
        }
        return 1;
    }
}
