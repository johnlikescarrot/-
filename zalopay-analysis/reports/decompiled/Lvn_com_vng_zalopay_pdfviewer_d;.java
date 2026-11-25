package vn.com.vng.zalopay.pdfviewer;
public final class d implements vn.com.vng.zalopay.pdfviewer.PdfRendererView$a {
    public final synthetic q0.C0 a;

    public d(q0.C0 p1)
    {
        this.a = p1;
        return;
    }

    public final void a()
    {
        this.a.setValue(Boolean.TRUE);
        return;
    }

    public final void b()
    {
        this.a.setValue(Boolean.FALSE);
        return;
    }

    public final void onError()
    {
        this.a.setValue(Boolean.FALSE);
        return;
    }
}
