package vn.com.vng.zalopay.pdfviewer;
public final class PdfRendererView extends android.widget.FrameLayout {
    public static final synthetic int l;
    public androidx.recyclerview.widget.RecyclerView a;
    public android.webkit.WebView b;
    public android.widget.TextView c;
    public hh.a d;
    public pI.g e;
    public final boolean f;
    public Runnable g;
    public boolean h;
    public android.graphics.Rect i;
    public vn.com.vng.zalopay.pdfviewer.PdfRendererView$a j;
    public final vn.com.vng.zalopay.pdfviewer.c k;

    public PdfRendererView(android.content.Context p2, android.util.AttributeSet p3)
    {
        this(p2, p3, 4);
        return;
    }

    public PdfRendererView(android.content.Context p2, android.util.AttributeSet p3, int p4)
    {
        if ((p4 & 2) != 0) {
            p3 = 0;
        }
        super(p2, p3, 0);
        super.f = 1;
        super.g = new hk.a(9);
        super.i = new android.graphics.Rect(0, 0, 0, 0);
        super.k = new vn.com.vng.zalopay.pdfviewer.c(super, p2);
        return;
    }

    public final void a(java.io.File p3, pI.d p4)
    {
        android.content.Context v0_2 = new hh.a(this.getContext(), p3, p4);
        this.d = v0_2;
        this.e = new pI.g(v0_2, this.i, this.h);
        this.addView(android.view.LayoutInflater.from(this.getContext()).inflate(2131559663, this, 0));
        this.a = ((androidx.recyclerview.widget.RecyclerView) this.findViewById(2131364013));
        this.b = ((android.webkit.WebView) this.findViewById(2131365192));
        this.c = ((android.widget.TextView) this.findViewById(2131363783));
        pI.e v3_13 = this.b;
        int v4_2 = 0;
        if (v3_13 == null) {
            v3_13 = 0;
        }
        v3_13.setVisibility(8);
        pI.e v3_14 = this.a;
        if (v3_14 == null) {
            v3_14 = 0;
        }
        android.content.Context v0_4 = this.e;
        if (v0_4 != null) {
            v4_2 = v0_4;
        }
        v3_14.setAdapter(v4_2);
        v3_14.getContext();
        v3_14.setLayoutManager(new androidx.recyclerview.widget.LinearLayoutManager(1));
        v3_14.setItemAnimator(new androidx.recyclerview.widget.o());
        if (this.f) {
            v3_14.addItemDecoration(new androidx.recyclerview.widget.u(v3_14.getContext()));
        }
        v3_14.addOnScrollListener(this.k);
        this.g = new pI.e(this, 0);
        return;
    }

    public final boolean getEnableLoadingForPages()
    {
        return this.h;
    }

    public final android.graphics.Rect getPageMargin()
    {
        return this.i;
    }

    public final vn.com.vng.zalopay.pdfviewer.PdfRendererView$a getStatusListener()
    {
        return this.j;
    }

    public final int getTotalPageCount()
    {
        int v0_0 = this.d;
        if (v0_0 == 0) {
            v0_0 = 0;
        }
        int v0_2 = ((android.graphics.pdf.PdfRenderer) v0_0.d);
        if (v0_2 == 0) {
            return 0;
        } else {
            return v0_2.getPageCount();
        }
    }

    public final void setEnableLoadingForPages(boolean p1)
    {
        this.h = p1;
        return;
    }

    public final void setPageMargin(android.graphics.Rect p1)
    {
        this.i = p1;
        return;
    }

    public final void setStatusListener(vn.com.vng.zalopay.pdfviewer.PdfRendererView$a p1)
    {
        this.j = p1;
        return;
    }
}
