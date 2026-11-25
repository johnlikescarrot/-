package vn.com.vng.zalopay.webapp.activity;
public class WebAppExternalActivity_ViewBinding extends vn.com.vng.zalopay.webapp.activity.WebAppBaseToolbarActivity_ViewBinding {
    public vn.com.vng.zalopay.webapp.activity.WebAppExternalActivity e;
    public android.view.View f;
    public android.view.View g;

    public WebAppExternalActivity_ViewBinding(vn.com.vng.zalopay.webapp.activity.WebAppExternalActivity p5, android.view.View p6)
    {
        super(p5, p6);
        super.e = p5;
        p5.toolbar = ((androidx.appcompat.widget.Toolbar) b4.e.c(p6, 2131364476, "field \'toolbar\'", androidx.appcompat.widget.Toolbar));
        int v0_0 = b4.e.b(p6, 2131365198, "field \'btnBack\' and method \'onBackWebAppClicked\'");
        p5.btnBack = ((android.widget.ImageButton) b4.e.a(v0_0, 2131365198, "field \'btnBack\'", android.widget.ImageButton));
        super.f = v0_0;
        v0_0.setOnClickListener(new UQ.e(p5, 0));
        int v0_3 = b4.e.b(p6, 2131365199, "field \'btnClose\' and method \'onCloseWebAppClicked\'");
        p5.btnClose = ((android.widget.ImageButton) b4.e.a(v0_3, 2131365199, "field \'btnClose\'", android.widget.ImageButton));
        super.g = v0_3;
        v0_3.setOnClickListener(new UQ.e(p5, 1));
        p5.closeView = b4.e.b(p6, 2131365202, "field \'closeView\'");
        p5.backView = b4.e.b(p6, 2131365197, "field \'backView\'");
        p5.externalButtonView = b4.e.b(p6, 2131365201, "field \'externalButtonView\'");
        return;
    }

    public final void a()
    {
        IllegalStateException v0_0 = this.e;
        if (v0_0 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        } else {
            this.e = 0;
            v0_0.toolbar = 0;
            v0_0.btnBack = 0;
            v0_0.btnClose = 0;
            v0_0.closeView = 0;
            v0_0.backView = 0;
            v0_0.externalButtonView = 0;
            this.f.setOnClickListener(0);
            this.f = 0;
            this.g.setOnClickListener(0);
            this.g = 0;
            super.a();
            return;
        }
    }
}
