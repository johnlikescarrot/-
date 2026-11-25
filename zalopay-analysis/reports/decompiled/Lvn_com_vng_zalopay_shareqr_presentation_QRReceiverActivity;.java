package vn.com.vng.zalopay.shareqr.presentation;
public final class QRReceiverActivity extends androidx.activity.ComponentActivity {
    public static final synthetic int l;
    public final q0.N0 i;
    public final q0.N0 j;
    public final q0.N0 k;

    public QRReceiverActivity()
    {
        q0.N0 v0_0 = q0.D0.e;
        this.i = q0.d.G(0, v0_0);
        this.j = q0.d.G(nN.a.a, v0_0);
        this.k = q0.d.G("", v0_0);
        return;
    }

    public final void onCreate(android.os.Bundle p7)
    {
        y0.b v7_9;
        super.onCreate(p7);
        y0.b v7_4 = this.getIntent();
        if (v7_4 == null) {
            v7_9 = 0;
        } else {
            v7_9 = v7_4.getAction();
        }
        if (kotlin.jvm.internal.Intrinsics.areEqual(v7_9, "android.intent.action.SEND")) {
            y0.b v7_3 = this.getIntent().getType();
            if ((v7_3 != null) && (kotlin.text.y.startsWith$default(v7_3, "image/", 0, 2, 0) == 1)) {
                y0.b v7_8;
                y0.b v7_7 = this.getIntent().getParcelableExtra("android.intent.extra.STREAM");
                if (!(v7_7 instanceof android.net.Uri)) {
                    v7_8 = 0;
                } else {
                    v7_8 = ((android.net.Uri) v7_7);
                }
                if (v7_8 != null) {
                    int v2_4 = new jV.k(9, this);
                    try {
                        lN.a v0_1 = com.google.mlkit.vision.common.InputImage.fromFilePath(this, v7_8);
                        java.io.IOException v3_4 = ((com.google.mlkit.vision.barcode.internal.b) nc.i.c().a(com.google.mlkit.vision.barcode.internal.b));
                        v3_4.getClass();
                        lN.a v0_3 = v3_4.a(com.google.mlkit.vision.barcode.internal.c.g).process(v0_1).addOnSuccessListener(new ju.c(10, new jV.k(10, v2_4)));
                        v0_3.addOnFailureListener(new ju.c(11, v2_4));
                    } catch (java.io.IOException v3_10) {
                        v3_10.printStackTrace();
                        v2_4.invoke(v0_3);
                    }
                    k.e.a(this, new y0.b(new lN.a(this, v7_8, 0), 1, 394121104));
                    return;
                } else {
                    this.finish();
                    return;
                }
            }
        }
        this.finish();
        return;
    }
}
