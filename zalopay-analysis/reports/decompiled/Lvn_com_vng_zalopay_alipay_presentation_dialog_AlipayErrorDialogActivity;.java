package vn.com.vng.zalopay.alipay.presentation.dialog;
public final class AlipayErrorDialogActivity extends androidx.appcompat.app.AppCompatActivity {
    public static final synthetic int i;

    public AlipayErrorDialogActivity()
    {
        return;
    }

    public final void onCreate(android.os.Bundle p4)
    {
        super.onCreate(p4);
        if (android.os.Build$VERSION.SDK_INT < 34) {
            this.overridePendingTransition(0, 0);
        } else {
            this.overrideActivityTransition(1, 0, 0);
        }
        y0.b v4_2 = this.getIntent().getStringExtra("dialog_title");
        if (v4_2 == null) {
            v4_2 = this.getString(2131952454);
        }
        k.e.a(this, new y0.b(new QP.w(v4_2, this, 4), 1, -1093384906));
        return;
    }
}
