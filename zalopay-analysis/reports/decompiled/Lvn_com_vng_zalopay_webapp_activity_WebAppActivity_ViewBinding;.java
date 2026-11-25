package vn.com.vng.zalopay.webapp.activity;
public class WebAppActivity_ViewBinding extends vn.com.vng.zalopay.user.UserBaseToolBarActivity_ViewBinding {
    public vn.com.vng.zalopay.webapp.activity.WebAppActivity c;
    public android.view.View d;
    public android.view.View e;

    public WebAppActivity_ViewBinding(vn.com.vng.zalopay.webapp.activity.WebAppActivity p5, android.view.View p6)
    {
        super(p5, p6);
        super.c = p5;
        p5.tvTitle = ((android.widget.TextView) b4.e.c(p6, 2131364450, "field \'tvTitle\'", android.widget.TextView));
        p5.btnBack = ((vn.zalopay.ui.IconFont) b4.e.a(b4.e.b(p6, 2131362131, "field \'btnBack\'"), 2131362131, "field \'btnBack\'", vn.zalopay.ui.IconFont));
        p5.btnClose = ((vn.zalopay.ui.IconFont) b4.e.a(b4.e.b(p6, 2131362137, "field \'btnClose\'"), 2131362137, "field \'btnClose\'", vn.zalopay.ui.IconFont));
        UQ.a v0_7 = b4.e.b(p6, 2131362412, "method \'onCloseWebAppClicked\'");
        super.d = v0_7;
        v0_7.setOnClickListener(new UQ.a(p5, 0));
        android.view.View v6_1 = b4.e.b(p6, 2131361992, "method \'onBackWebAppClicked\'");
        super.e = v6_1;
        v6_1.setOnClickListener(new UQ.a(p5, 1));
        return;
    }

    public final void a()
    {
        IllegalStateException v0_0 = this.c;
        if (v0_0 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        } else {
            this.c = 0;
            v0_0.tvTitle = 0;
            v0_0.btnBack = 0;
            v0_0.btnClose = 0;
            this.d.setOnClickListener(0);
            this.d = 0;
            this.e.setOnClickListener(0);
            this.e = 0;
            super.a();
            return;
        }
    }
}
