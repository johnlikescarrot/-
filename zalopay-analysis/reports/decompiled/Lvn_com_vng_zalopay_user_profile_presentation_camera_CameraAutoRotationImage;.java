package vn.com.vng.zalopay.user_profile.presentation.camera;
public final class CameraAutoRotationImage extends androidx.appcompat.app.AppCompatActivity {
    public static final String[] o;
    public String i;
    public F.w0 j;
    public F.c0 k;
    public F.x l;
    public final ng.S m;
    public final ng.S n;

    static CameraAutoRotationImage()
    {
        String[] v0_5 = kotlin.collections.E.mutableListOf(new String[] {"android.permission.CAMERA"}));
        if (android.os.Build$VERSION.SDK_INT <= 28) {
            v0_5.add("android.permission.WRITE_EXTERNAL_STORAGE");
        }
        String[] v1_1 = new String[0];
        vn.com.vng.zalopay.user_profile.presentation.camera.CameraAutoRotationImage.o = ((String[]) ((java.util.Collection) v0_5).toArray(v1_1));
        return;
    }

    public CameraAutoRotationImage()
    {
        this.l = F.x.c;
        this.m = com.google.android.gms.internal.mlkit_vision_barcode.F0.a(this, 2131364098);
        this.n = com.google.android.gms.internal.mlkit_vision_barcode.F0.a(this, 2131365093);
        return;
    }

    public static android.graphics.Bitmap g1(android.graphics.Bitmap p7, float p8)
    {
        android.graphics.Matrix v5_1 = new android.graphics.Matrix();
        v5_1.postRotate(p8);
        android.graphics.Bitmap v0_1 = android.graphics.Bitmap.createScaledBitmap(p7, p7.getWidth(), p7.getHeight(), 1);
        return android.graphics.Bitmap.createBitmap(v0_1, 0, 0, v0_1.getWidth(), v0_1.getHeight(), v5_1, 1);
    }

    public final java.io.File e1()
    {
        java.io.File v0_5 = new java.text.SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-SSS", java.util.Locale.US).format(Long.valueOf(System.currentTimeMillis()));
        java.io.File v1_2 = this.getExternalFilesDir(android.os.Environment.DIRECTORY_PICTURES);
        String v2_1 = new StringBuilder("JPEG_");
        v2_1.append(v0_5);
        v2_1.append("_");
        return java.io.File.createTempFile(v2_1.toString(), ".jpg", v1_2);
    }

    public final void h1()
    {
        java.util.concurrent.Executor v0_3 = new F.I0().d();
        v0_3.F(((androidx.camera.view.PreviewView) this.n.getValue()).getSurfaceProvider());
        androidx.camera.core.impl.utils.futures.b v1_0 = p9.B.a(this);
        v1_0.e(new Bk.v(v1_0, this, v0_3, 4), androidx.core.content.c.c(this));
        return;
    }

    public final void onCreate(android.os.Bundle p5)
    {
        int v0_4 = com.google.android.gms.internal.mlkit_vision_barcode.b0.a.r();
        p9.n7.c(v0_4);
        this.i = v0_4;
        super.onCreate(p5);
        int v1 = 0;
        p9.U.a(this.getWindow(), 0);
        this.setContentView(2131558456);
        if (p5 == null) {
            p5 = this.getIntent().getExtras();
        }
        com.google.android.gms.internal.mlkit_vision_barcode.W7.b(((androidx.constraintlayout.widget.ConstraintLayout) this.m.getValue()), 0);
        if (p5 != null) {
            F.x v5_3;
            if (p5.getInt("zcamera-facing", 1) != 1) {
                v5_3 = F.x.b;
            } else {
                v5_3 = F.x.c;
            }
            this.l = v5_3;
        }
        ((androidx.appcompat.widget.AppCompatImageView) this.findViewById(2131363134)).setOnClickListener(new IP.a(this, 0));
        ((android.widget.ImageView) this.findViewById(2131363154)).setOnClickListener(new IP.a(this, 1));
        F.x v5_10 = vn.com.vng.zalopay.user_profile.presentation.camera.CameraAutoRotationImage.o;
        while (v1 < v5_10.length) {
            if (androidx.core.content.c.a(this.getBaseContext(), v5_10[v1]) != 0) {
                androidx.core.app.d.e(this, v5_10, 10);
                return;
            } else {
                v1++;
            }
        }
        this.h1();
        return;
    }

    public final void onRequestPermissionsResult(int p3, String[] p4, int[] p5)
    {
        if (p3 == 10) {
            String v3_3 = vn.com.vng.zalopay.user_profile.presentation.camera.CameraAutoRotationImage.o;
            p5 = 0;
            while (p5 < v3_3.length) {
                if (androidx.core.content.c.a(this.getBaseContext(), v3_3[p5]) != 0) {
                    com.google.android.gms.internal.mlkit_vision_barcode.h7.c(this, this.getString(2131955141));
                    this.finish();
                    return;
                } else {
                    p5++;
                }
            }
            this.h1();
        }
        return;
    }
}
