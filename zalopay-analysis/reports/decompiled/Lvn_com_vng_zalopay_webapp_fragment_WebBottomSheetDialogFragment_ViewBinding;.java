package vn.com.vng.zalopay.webapp.fragment;
public class WebBottomSheetDialogFragment_ViewBinding implements butterknife.Unbinder {
    public vn.com.vng.zalopay.webapp.fragment.WebBottomSheetDialogFragment b;
    public android.view.View c;
    public android.view.View d;
    public android.view.View e;
    public android.view.View f;

    public WebBottomSheetDialogFragment_ViewBinding(vn.com.vng.zalopay.webapp.fragment.WebBottomSheetDialogFragment p4, android.view.View p5)
    {
        this.b = p4;
        WQ.v v0_8 = b4.e.b(p5, 2131363393, "method \'handleClickShareOnZalo\'");
        this.c = v0_8;
        v0_8.setOnClickListener(new WQ.v(p4, 0));
        WQ.v v0_1 = b4.e.b(p5, 2131363366, "method \'handleClickCopyURL\'");
        this.d = v0_1;
        v0_1.setOnClickListener(new WQ.v(p4, 1));
        WQ.v v0_4 = b4.e.b(p5, 2131363389, "method \'handleClickRefreshWeb\'");
        this.e = v0_4;
        v0_4.setOnClickListener(new WQ.v(p4, 2));
        android.view.View v5_1 = b4.e.b(p5, 2131363382, "method \'handleClickOpenInBrowser\'");
        this.f = v5_1;
        v5_1.setOnClickListener(new WQ.v(p4, 3));
        return;
    }

    public final void a()
    {
        if (this.b == null) {
            throw new IllegalStateException("Bindings already cleared.");
        } else {
            this.b = 0;
            this.c.setOnClickListener(0);
            this.c = 0;
            this.d.setOnClickListener(0);
            this.d = 0;
            this.e.setOnClickListener(0);
            this.e = 0;
            this.f.setOnClickListener(0);
            this.f = 0;
            return;
        }
    }
}
