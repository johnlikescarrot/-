package vn.com.vng.zalopay.webapp.activity;
public final synthetic class a implements java.lang.Runnable {
    public final synthetic int a;
    public final synthetic vn.com.vng.zalopay.webapp.activity.WebAppBaseToolbarActivity b;
    public final synthetic Object c;

    public synthetic a(vn.com.vng.zalopay.webapp.activity.WebAppBaseToolbarActivity p1, Object p2, int p3)
    {
        this.a = p3;
        this.b = p1;
        this.c = p2;
        return;
    }

    public final void run()
    {
        android.content.res.ColorStateList v1_0 = this.c;
        vn.com.vng.zalopay.webapp.activity.WebAppExternalActivity v2_1 = this.b;
        switch (this.a) {
            case 0:
                android.widget.ImageButton v0_4 = v2_1.viewShare;
                if (v0_4 != null) {
                    v2_1.t = ((Ew.y) v1_0);
                    v0_4.setVisibility(0);
                }
                return;
            default:
                vn.com.vng.zalopay.webapp.activity.WebAppExternalActivity v2_0 = ((vn.com.vng.zalopay.webapp.activity.WebAppExternalActivity) v2_1);
                android.content.res.ColorStateList v1_1 = ((String) v1_0);
                android.widget.ImageButton v0_1 = v2_0.tvTitle;
                if ((v0_1 != null) && ((v2_0.btnBack != null) && (v2_0.btnClose != null))) {
                    v0_1.setTextColor(android.graphics.Color.parseColor(v1_1));
                    v2_0.btnBack.setImageTintList(android.content.res.ColorStateList.valueOf(android.graphics.Color.parseColor(v1_1)));
                    v2_0.btnClose.setImageTintList(android.content.res.ColorStateList.valueOf(android.graphics.Color.parseColor(v1_1)));
                }
                return;
        }
    }
}
