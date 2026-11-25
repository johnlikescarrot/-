package vn.com.vng.zalopay.pdfviewer;
public final class e extends kotlin.coroutines.jvm.internal.SuspendLambda implements kotlin.jvm.functions.Function2 {
    public final synthetic vn.com.vng.zalopay.pdfviewer.PdfRendererView d;
    public final synthetic String e;
    public final synthetic androidx.lifecycle.A f;
    public final synthetic q0.C0 g;

    public e(vn.com.vng.zalopay.pdfviewer.PdfRendererView p1, String p2, androidx.lifecycle.A p3, q0.C0 p4, kotlin.coroutines.Continuation p5)
    {
        this.d = p1;
        this.e = p2;
        this.f = p3;
        this.g = p4;
        super(2, p5);
        return;
    }

    public final kotlin.coroutines.Continuation create(Object p7, kotlin.coroutines.Continuation p8)
    {
        return new vn.com.vng.zalopay.pdfviewer.e(this.d, this.e, this.f, this.g, p8);
    }

    public final Object invoke(Object p1, Object p2)
    {
        return ((vn.com.vng.zalopay.pdfviewer.e) this.create(((kotlinx.coroutines.CoroutineScope) p1), ((kotlin.coroutines.Continuation) p2))).invokeSuspend(kotlin.Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object p5)
    {
        vg.i.getCOROUTINE_SUSPENDED();
        kotlin.ResultKt.throwOnFailure(p5);
        vn.com.vng.zalopay.pdfviewer.PdfRendererView v0_1 = this.d;
        v0_1.setStatusListener(new vn.com.vng.zalopay.pdfviewer.d(this.g));
        new lS.b(this.e, new vn.com.vng.zalopay.pdfviewer.b(v0_1, pI.d.b, this.f));
        return kotlin.Unit.INSTANCE;
    }
}
