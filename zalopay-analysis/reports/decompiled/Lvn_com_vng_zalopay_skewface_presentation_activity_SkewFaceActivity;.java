package vn.com.vng.zalopay.skewface.presentation.activity;
public final class SkewFaceActivity extends vn.com.vng.zalopay.core.ui.activity.BaseBindingActivity {
    public static final synthetic int l;
    public final ng.S k;

    public SkewFaceActivity()
    {
        this.k = ng.x.b(new DN.a(this, 0));
        return;
    }

    public final yN.a g1(android.view.LayoutInflater p5)
    {
        yN.a v5_6 = p5.inflate(2131558481, 0, 0);
        int v0_3 = 2131363684;
        String v1_3 = ((androidx.fragment.app.FragmentContainerView) l5.s.a(2131363684, v5_6));
        if (v1_3 != null) {
            androidx.compose.ui.platform.ComposeView v3_1 = ((androidx.compose.ui.platform.ComposeView) l5.s.a(2131364497, v5_6));
            if (v3_1 == null) {
                v0_3 = 2131364497;
            } else {
                return new yN.a(((android.widget.LinearLayout) v5_6), v1_3, v3_1);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(v5_6.getResources().getResourceName(v0_3)));
    }

    public final void h1()
    {
        Object vtmp1 = this.k.getValue();
        String v1_1 = com.google.android.gms.internal.mlkit_vision_barcode.r7.a;
        if (v1_1 == null) {
            throw new IllegalStateException("Required value was null.");
        } else {
            com.google.android.gms.internal.mlkit_vision_barcode.r7.b = new androidx.work.impl.utils.e(9, new k0.r(v1_1, ((vn.com.vng.zalopay.skewface.presentation.args.SkewFaceArgument) vtmp1), 13));
            return;
        }
    }

    public final void i1()
    {
        ((androidx.navigation.fragment.NavHostFragment) this.getSupportFragmentManager().D(((yN.a) this.e1()).b.getId())).m1();
        return;
    }

    public final void onCreate(android.os.Bundle p5)
    {
        super.onCreate(p5);
        try {
            com.google.android.gms.internal.mlkit_vision_barcode.n0.c(new java.io.File(String.format("%s%s%s", java.util.Arrays.copyOf(new Object[] {this.getFilesDir().toString(), java.io.File.separator, "skew_faces"}), 3))));
        } catch (androidx.compose.ui.platform.ComposeView v0_1) {
            DN.b v1_5 = new Object[0];
            ck.b.c(v0_1, "[SkewFace] createPhotoDirectory error", v1_5);
            com.google.android.gms.internal.mlkit_vision_barcode.h7.f(this, "Kh\u00f4ng th\u1ec3 truy c\u1eadp v\u00e0o b\u1ed9 nh\u1edb thi\u1ebft b\u1ecb, vui l\u00f2ng ki\u1ec3m tra v\u00e0 th\u1eed l\u1ea1i", 1);
            this.finish();
        }
        ((yN.a) this.e1()).c.setContent(new y0.b(new DN.b(this, 0), 1, -757465041));
        return;
    }

    public final void onDestroy()
    {
        try {
            Cg.o.deleteRecursively(new java.io.File(String.format("%s%s%s", java.util.Arrays.copyOf(new Object[] {this.getFilesDir().toString(), java.io.File.separator, "skew_faces"}), 3))));
        } catch (Throwable v0_2) {
            Object[] v1_4 = new Object[0];
            ck.b.c(v0_2, "[SkewFace] deletePhotoDirectory error", v1_4);
        }
        super.onDestroy();
        return;
    }
}
