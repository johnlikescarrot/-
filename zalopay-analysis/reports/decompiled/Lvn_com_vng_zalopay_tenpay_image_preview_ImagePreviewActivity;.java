package vn.com.vng.zalopay.tenpay.image_preview;
public final class ImagePreviewActivity extends androidx.appcompat.app.AppCompatActivity {
    public static final synthetic int k;
    public final ng.S i;
    public final ng.S j;

    public ImagePreviewActivity()
    {
        this.i = ng.x.b(new CO.a(this, 0));
        this.j = ng.x.b(new CO.a(this, 1));
        return;
    }

    public final void e1(D0.q p12, q0.s p13, int p14)
    {
        int v1_6;
        q0.Y0 v13_1 = p13.x(-1126503441);
        if (!v13_1.I(this)) {
            v1_6 = 16;
        } else {
            v1_6 = 32;
        }
        if (((((p14 | 6) | v1_6) & 19) == 18) && (v13_1.c())) {
            v13_1.m();
        } else {
            D0.p v12_3 = ((java.util.List) this.i.getValue());
            AZ.h v0_5 = ((String) this.j.getValue());
            int v1_1 = v13_1.I(this);
            kotlin.jvm.functions.Function0 v2_0 = v13_1.G();
            if ((v1_1 != 0) || (v2_0 == q0.r.a)) {
                BV.a1 v3_1 = new BV.a1(0, this, vn.com.vng.zalopay.tenpay.image_preview.ImagePreviewActivity, "finish", "finish()V", 0, 6);
                v13_1.B(v3_1);
                v2_0 = v3_1;
            }
            CO.m.b(v12_3, v0_5, ((kotlin.jvm.functions.Function0) ((kotlin.reflect.g) v2_0)), v13_1, 3072);
            p12 = D0.p.a;
        }
        q0.Y0 v13_2 = v13_1.Y();
        if (v13_2 != null) {
            v13_2.d = new AZ.h(this, p12, p14, 6);
        }
        return;
    }

    public final void onCreate(android.os.Bundle p4)
    {
        super.onCreate(p4);
        k.e.a(this, new y0.b(new CO.b(this, 0), 1, 152049565));
        return;
    }
}
