package vn.com.vng.zalopay.camera;
public final class CustomDefaultCamera extends vn.com.vng.zalopay.core.ui.activity.BaseActivity {
    public static final String[] o;
    public F.w0 k;
    public androidx.camera.core.impl.utils.futures.b l;
    public CZ.b m;
    public F.x n;

    static CustomDefaultCamera()
    {
        String[] v0_5 = kotlin.collections.E.mutableListOf(new String[] {"android.permission.CAMERA"}));
        if (android.os.Build$VERSION.SDK_INT <= 28) {
            v0_5.add("android.permission.WRITE_EXTERNAL_STORAGE");
        }
        String[] v1_1 = new String[0];
        vn.com.vng.zalopay.camera.CustomDefaultCamera.o = ((String[]) ((java.util.Collection) v0_5).toArray(v1_1));
        return;
    }

    public CustomDefaultCamera()
    {
        this.n = F.x.c;
        return;
    }

    public final int e1()
    {
        return 2131558455;
    }

    public final OD.a g1()
    {
        return new OD.a(0, -1);
    }

    public final void h1()
    {
        CZ.b v0_6 = this.getLayoutInflater().inflate(2131558455, 0, 0);
        int v1_0 = 2131363134;
        if (((android.widget.ImageView) l5.s.a(2131363134, v0_6)) != null) {
            v1_0 = 2131363154;
            if (((android.widget.ImageView) l5.s.a(2131363154, v0_6)) != null) {
                int v1_2 = ((androidx.constraintlayout.widget.ConstraintLayout) v0_6);
                androidx.camera.view.PreviewView v3_1 = ((androidx.camera.view.PreviewView) l5.s.a(2131365093, v0_6));
                if (v3_1 == null) {
                    v1_0 = 2131365093;
                } else {
                    this.m = new CZ.b(v1_2, v1_2, v3_1, 2);
                    this.setContentView(v1_2);
                    return;
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(v0_6.getResources().getResourceName(v1_0)));
    }

    public final java.io.File j1()
    {
        java.io.File v0_5 = new java.text.SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-SSS", java.util.Locale.US).format(Long.valueOf(System.currentTimeMillis()));
        java.io.File v1_2 = this.getExternalFilesDir(android.os.Environment.DIRECTORY_PICTURES);
        String v2_1 = new StringBuilder("JPEG_");
        v2_1.append(v0_5);
        v2_1.append("_");
        return java.io.File.createTempFile(v2_1.toString(), ".jpg", v1_2);
    }

    public final void k1()
    {
        java.util.concurrent.Executor v0_3 = new F.I0().d();
        Kk.f v1_4 = this.m;
        androidx.camera.core.impl.utils.futures.b v2 = 0;
        if (v1_4 == null) {
            v1_4 = 0;
        }
        v0_3.F(((androidx.camera.view.PreviewView) v1_4.d).getSurfaceProvider());
        Kk.f v1_1 = this.l;
        if (v1_1 != null) {
            v2 = v1_1;
        }
        v2.e(new Kk.f(this, v0_3, 12), androidx.core.content.c.c(this));
        return;
    }

    public final void onCreate(android.os.Bundle p5)
    {
        super.onCreate(p5);
        if (p5 == null) {
            p5 = this.getIntent().getExtras();
        }
        if (p5 != null) {
            F.x v5_2;
            if (p5.getInt("zcamera-facing", 1) != 1) {
                v5_2 = F.x.b;
            } else {
                v5_2 = F.x.c;
            }
            this.n = v5_2;
        }
        int v2 = 0;
        ((androidx.appcompat.widget.AppCompatImageView) this.findViewById(2131363134)).setOnClickListener(new Vo.a(this, 0));
        ((android.widget.ImageView) this.findViewById(2131363154)).setOnClickListener(new Vo.a(this, 1));
        this.l = p9.B.a(this);
        F.x v5_11 = vn.com.vng.zalopay.camera.CustomDefaultCamera.o;
        while (v2 < v5_11.length) {
            if (androidx.core.content.c.a(this.getBaseContext(), v5_11[v2]) != 0) {
                androidx.core.app.d.e(this, v5_11, 10);
                return;
            } else {
                v2++;
            }
        }
        this.k1();
        return;
    }

    public final void onRequestPermissionsResult(int p3, String[] p4, int[] p5)
    {
        if (p3 == 10) {
            String v3_3 = vn.com.vng.zalopay.camera.CustomDefaultCamera.o;
            p5 = 0;
            while (p5 < v3_3.length) {
                if (androidx.core.content.c.a(this.getBaseContext(), v3_3[p5]) != 0) {
                    com.google.android.gms.internal.mlkit_vision_barcode.h7.c(this, this.getString(2131955082));
                    this.finish();
                    return;
                } else {
                    p5++;
                }
            }
            this.k1();
        }
        return;
    }
}
