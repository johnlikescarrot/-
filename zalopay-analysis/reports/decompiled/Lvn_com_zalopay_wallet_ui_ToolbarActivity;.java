package vn.com.zalopay.wallet.ui;
public abstract class ToolbarActivity extends vn.com.zalopay.wallet.ui.BaseActivity {
    public androidx.appcompat.widget.Toolbar l;
    public android.widget.TextView m;
    public android.widget.TextView n;

    public ToolbarActivity()
    {
        return;
    }

    public final void onCreate(android.os.Bundle p2)
    {
        super.onCreate(p2);
        this.l = ((androidx.appcompat.widget.Toolbar) this.findViewById(2131364476));
        this.m = ((android.widget.TextView) this.findViewById(2131364482));
        this.n = ((android.widget.TextView) this.findViewById(2131364480));
        this.findViewById(2131363638);
        this.setSupportActionBar(this.l);
        androidx.appcompat.app.f v2_8 = this.getSupportActionBar();
        if (v2_8 != null) {
            v2_8.s(1);
        }
        androidx.appcompat.app.f v2_9 = this.getSupportActionBar();
        if (v2_9 != null) {
            v2_9.u();
        }
        return;
    }
}
