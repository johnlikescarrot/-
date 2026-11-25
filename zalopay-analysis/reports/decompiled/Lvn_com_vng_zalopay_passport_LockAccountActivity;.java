package vn.com.vng.zalopay.passport;
public final class LockAccountActivity extends vn.com.vng.zalopay.ui.activity.ZPActivity {
    public static final synthetic int q;
    public final ng.S m;
    public final ng.S n;
    public final ng.S o;
    public final ng.S p;

    public LockAccountActivity()
    {
        this.m = com.google.android.gms.internal.mlkit_vision_barcode.F0.a(this, 2131364787);
        this.n = com.google.android.gms.internal.mlkit_vision_barcode.F0.a(this, 2131364686);
        this.o = com.google.android.gms.internal.mlkit_vision_barcode.F0.a(this, 2131363097);
        this.p = com.google.android.gms.internal.mlkit_vision_barcode.F0.a(this, 2131362079);
        return;
    }

    public final int e1()
    {
        return 2131558465;
    }

    public final OD.a g1()
    {
        return new OD.a("", -1);
    }

    public final void onCreate(android.os.Bundle p5)
    {
        android.widget.ImageView v5_2;
        super.onCreate(p5);
        android.widget.ImageView v5_10 = this.getIntent().getExtras();
        OH.e v0_2 = 0;
        if (v5_10 == null) {
            v5_2 = 0;
        } else {
            v5_2 = ((OH.f) v5_10.getParcelable("lock_account_arguments"));
        }
        if (v5_2 == null) {
            throw new IllegalArgumentException("Required value was null.");
        } else {
            int v1_0 = v5_2.a;
            android.widget.TextView v2_2 = ((android.widget.TextView) this.m.getValue());
            android.widget.ImageView v5_6 = v5_2.b;
            if (v5_6 == null) {
                v0_2 = this.getString(2131953696);
            } else {
                android.widget.ImageView v5_7 = is.T.g(v5_6);
                if (v5_7.length() <= 0) {
                    v5_7 = 0;
                }
                if (v5_7 != null) {
                    v0_2 = this.getString(2131953695, new Object[] {v5_7}));
                }
                if (v0_2 == null) {
                }
            }
            v2_2.setText(v0_2);
            android.widget.ImageView v5_13 = ((android.widget.TextView) this.n.getValue());
            if (v1_0.length() <= 0) {
                v1_0 = this.getString(2131953697);
            }
            v5_13.setText(v1_0);
            ((android.widget.ImageView) this.o.getValue()).setOnClickListener(new OH.e(this, 0));
            ((android.widget.ImageView) this.p.getValue()).setOnClickListener(new OH.e(this, 1));
            return;
        }
    }
}
