package vn.com.vng.zalopay.protect.ui;
public final class ProtectAccountActivity extends vn.com.vng.zalopay.ui.activity.ZPActivity {
    public final ng.S m;
    public final ng.S n;

    public ProtectAccountActivity()
    {
        this.m = com.google.android.gms.internal.mlkit_vision_barcode.F0.a(this, 2131363687);
        this.n = com.google.android.gms.internal.mlkit_vision_barcode.F0.a(this, 2131364476);
        return;
    }

    public final int e1()
    {
        return 2131558438;
    }

    public final OD.a g1()
    {
        return new OD.a(wmoi.igob.ggcn(562), 11852);
    }

    public final void onCreate(android.os.Bundle p3)
    {
        super.onCreate(p3);
        androidx.appcompat.app.f v3_1 = this.m;
        ((vn.zalopay.ui.navigationbar.NavigationBar) v3_1.getValue()).setTitle(this.getTitle().toString());
        ((vn.zalopay.ui.navigationbar.NavigationBar) v3_1.getValue()).setListener(new IK.n(this, 5));
        this.setSupportActionBar(((androidx.appcompat.widget.Toolbar) this.n.getValue()));
        androidx.appcompat.app.f v3_7 = this.getSupportActionBar();
        if (v3_7 != null) {
            v3_7.s(1);
        }
        return;
    }
}
