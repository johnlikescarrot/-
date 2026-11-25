package vn.com.vng.zalopay.pdfviewer;
public final class c extends androidx.recyclerview.widget.m0 {
    public final synthetic vn.com.vng.zalopay.pdfviewer.PdfRendererView a;
    public final synthetic android.content.Context b;

    public c(vn.com.vng.zalopay.pdfviewer.PdfRendererView p1, android.content.Context p2)
    {
        this.a = p1;
        this.b = p2;
        return;
    }

    public final void a(androidx.recyclerview.widget.RecyclerView p3, int p4)
    {
        p3 = 0;
        long v0_0 = this.a;
        if (p4 != null) {
            Runnable v4_2 = v0_0.c;
            if (v4_2 != null) {
                p3 = v4_2;
            }
            p3.removeCallbacks(v0_0.g);
            return;
        } else {
            Runnable v4_4 = v0_0.c;
            if (v4_4 != null) {
                p3 = v4_4;
            }
            p3.postDelayed(v0_0.g, 3000);
            return;
        }
    }

    public final void b(androidx.recyclerview.widget.RecyclerView p8, int p9, int p10)
    {
        int v9_0;
        vn.com.vng.zalopay.pdfviewer.PdfRendererView$a v8_2 = ((androidx.recyclerview.widget.LinearLayoutManager) p8.getLayoutManager());
        int v9_2 = v8_2.N0(0, v8_2.v(), 1, 0);
        if (v9_2 != 0) {
            v9_0 = androidx.recyclerview.widget.j0.E(v9_2);
        } else {
            v9_0 = -1;
        }
        vn.com.vng.zalopay.pdfviewer.PdfRendererView v1 = this.a;
        int v2_0 = v1.c;
        android.widget.TextView v3 = 0;
        if (v2_0 == 0) {
            v2_0 = 0;
        }
        if (v9_0 != -1) {
            v2_0.setText(this.b.getString(2131953984, new Object[] {Integer.valueOf((v9_0 + 1)), Integer.valueOf(v1.getTotalPageCount())})));
        }
        int v2_1 = v1.c;
        if (v2_1 == 0) {
            v2_1 = 0;
        }
        v2_1.setVisibility(0);
        if (v9_0 == 0) {
            pI.e v0_0 = v1.c;
            if (v0_0 != null) {
                v3 = v0_0;
            }
            v3.postDelayed(new pI.e(v1, 1), 3000);
        }
        if (v9_0 == -1) {
            if ((v8_2.J0() != -1) && (v1.getStatusListener() != null)) {
                v1.getTotalPageCount();
            }
        } else {
            if (v1.getStatusListener() != null) {
                v1.getTotalPageCount();
                return;
            }
        }
        return;
    }
}
