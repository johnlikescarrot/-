package vn.com.vng.zalopay.pdfviewer;
public final class a extends android.webkit.WebViewClient {
    public final vn.com.vng.zalopay.pdfviewer.PdfRendererView$a a;

    public a(vn.com.vng.zalopay.pdfviewer.PdfRendererView$a p1)
    {
        this.a = p1;
        return;
    }

    public final void onPageFinished(android.webkit.WebView p1, String p2)
    {
        super.onPageFinished(p1, p2);
        vn.com.vng.zalopay.pdfviewer.PdfRendererView$a v1_1 = this.a;
        if (v1_1 != null) {
            v1_1.b();
        }
        return;
    }

    public final void onReceivedError(android.webkit.WebView p1, android.webkit.WebResourceRequest p2, android.webkit.WebResourceError p3)
    {
        super.onReceivedError(p1, p2, p3);
        vn.com.vng.zalopay.pdfviewer.PdfRendererView$a v1_1 = this.a;
        if (v1_1 != null) {
            new Throwable("Web resource error");
            v1_1.onError();
        }
        return;
    }
}
