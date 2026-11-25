package vn.com.vng.zalopay.alipay.presentation;
public final class AlipayEmptyScanQRActivity extends androidx.activity.ComponentActivity {
    public static final synthetic int j;
    public final androidx.activity.result.d i;

    public AlipayEmptyScanQRActivity()
    {
        this.i = this.registerForActivityResult(new androidx.fragment.app.f0(6), new BV.k1(3, this));
        return;
    }

    public final void onCreate(android.os.Bundle p2)
    {
        super.onCreate(p2);
        KG.a v2_1 = com.google.android.gms.internal.mlkit_vision_barcode.p5.a;
        if (v2_1 != null) {
            v2_1.p(this, this.i);
        }
        return;
    }
}
