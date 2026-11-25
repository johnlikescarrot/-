package vn.zalopay.ui.androidsvg;
public final class A0 {
    public final vn.zalopay.ui.androidsvg.U a;
    public boolean b;
    public boolean c;
    public final android.graphics.Paint d;
    public final android.graphics.Paint e;
    public J0.b f;
    public J0.b g;
    public boolean h;

    public A0()
    {
        vn.zalopay.ui.androidsvg.U v0_4 = new android.graphics.Paint();
        this.d = v0_4;
        v0_4.setFlags(385);
        v0_4.setStyle(android.graphics.Paint$Style.FILL);
        android.graphics.Typeface v2_1 = android.graphics.Typeface.DEFAULT;
        v0_4.setTypeface(v2_1);
        vn.zalopay.ui.androidsvg.U v0_2 = new android.graphics.Paint();
        this.e = v0_2;
        v0_2.setFlags(385);
        v0_2.setStyle(android.graphics.Paint$Style.STROKE);
        v0_2.setTypeface(v2_1);
        this.a = vn.zalopay.ui.androidsvg.U.a();
        return;
    }

    public A0(vn.zalopay.ui.androidsvg.A0 p4)
    {
        this.b = p4.b;
        this.c = p4.c;
        this.d = new android.graphics.Paint(p4.d);
        this.e = new android.graphics.Paint(p4.e);
        String v0_2 = p4.f;
        if (v0_2 != null) {
            this.f = new J0.b(v0_2, 0);
        }
        String v0_3 = p4.g;
        if (v0_3 != null) {
            this.g = new J0.b(v0_3, 0);
        }
        this.h = p4.h;
        try {
            this.a = ((vn.zalopay.ui.androidsvg.U) p4.a.clone());
            return;
        } catch (vn.zalopay.ui.androidsvg.U v4_4) {
            ck.b.e("SVGAndroidRenderer", new Object[] {"Unexpected clone error", v4_4}));
            this.a = vn.zalopay.ui.androidsvg.U.a();
            return;
        }
    }
}
