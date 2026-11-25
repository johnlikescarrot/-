package vn.com.vng.zalopay.webapp.activity;
public class WebAppBaseToolbarActivity_ViewBinding extends vn.com.vng.zalopay.user.UserBaseToolBarActivity_ViewBinding {
    public vn.com.vng.zalopay.webapp.activity.WebAppBaseToolbarActivity c;
    public android.view.View d;

    public WebAppBaseToolbarActivity_ViewBinding(vn.com.vng.zalopay.webapp.activity.WebAppBaseToolbarActivity p5, android.view.View p6)
    {
        super(p5, p6);
        super.c = p5;
        p5.tvTitle = ((android.widget.TextView) b4.e.c(p6, 2131365204, "field \'tvTitle\'", android.widget.TextView));
        p5.llActionViews = ((android.widget.LinearLayout) b4.e.a(b4.e.b(p6, 2131365196, "field \'llActionViews\'"), 2131365196, "field \'llActionViews\'", android.widget.LinearLayout));
        Class v0_4 = p6.findViewById(2131365203);
        p5.viewShare = v0_4;
        if (v0_4 != null) {
            super.d = v0_4;
            v0_4.setOnClickListener(new UQ.d(0, p5));
        }
        p5.navigationBar = ((vn.zalopay.ui.navigationbar.NavigationBar) b4.e.a(b4.e.b(p6, 2131363687, "field \'navigationBar\'"), 2131363687, "field \'navigationBar\'", vn.zalopay.ui.navigationbar.NavigationBar));
        return;
    }

    public void a()
    {
        IllegalStateException v0_0 = this.c;
        if (v0_0 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        } else {
            this.c = 0;
            v0_0.tvTitle = 0;
            v0_0.llActionViews = 0;
            v0_0.viewShare = 0;
            v0_0.navigationBar = 0;
            IllegalStateException v0_1 = this.d;
            if (v0_1 != null) {
                v0_1.setOnClickListener(0);
                this.d = 0;
            }
            super.a();
            return;
        }
    }
}
