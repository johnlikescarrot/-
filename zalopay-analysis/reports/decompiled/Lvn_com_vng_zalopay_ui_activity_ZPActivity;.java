package vn.com.vng.zalopay.ui.activity;
public abstract class ZPActivity extends vn.com.vng.zalopay.core.ui.activity.BaseActivity {
    public static final synthetic int l;
    public final ng.S k;

    public ZPActivity()
    {
        this.k = ng.x.b(new IR.c(27));
        return;
    }

    public void onCreate(android.os.Bundle p3)
    {
        super.onCreate(p3);
        java.util.List v3_6 = ((CC.k) this.k.getValue());
        if (v3_6 != null) {
            java.util.List v3_7 = v3_6.i0();
            if (v3_7 != null) {
                java.util.List v3_3 = ((fv.a) v3_7.get());
                if (v3_3 != null) {
                    if (v3_3.a) {
                        q9.h5.a(this, n9.e0.b(this, v3_3.b, wmoi.igob.ggcn(15544)), v3_3.d);
                    }
                    q9.h5.b(this);
                }
            }
        }
        return;
    }

    public final void setSupportActionBar(androidx.appcompat.widget.Toolbar p5)
    {
        int v1_1;
        super.setSupportActionBar(p5);
        android.widget.ImageButton v0_0 = 0;
        if (p5 == null) {
            v1_1 = 0;
        } else {
            v1_1 = ((android.widget.ImageButton) p5.findViewById(2131363583));
        }
        if (v1_1 != 0) {
            v1_1.setOnClickListener(new MO.m(this, 0));
        }
        if (p5 != null) {
            v0_0 = ((android.widget.ImageButton) p5.findViewById(2131363584));
        }
        if (v0_0 != null) {
            v0_0.setOnClickListener(new MO.m(this, 1));
        }
        return;
    }
}
