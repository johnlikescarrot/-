package vn.zalopay.ui.androidsvg;
public final class x0 extends vn.zalopay.ui.androidsvg.y0 {
    public final android.graphics.Path d;
    public final synthetic vn.zalopay.ui.androidsvg.C0 e;

    public x0(vn.zalopay.ui.androidsvg.C0 p2, android.graphics.Path p3, float p4)
    {
        this.e = p2;
        super(p2, p4, 0);
        super.d = p3;
        return;
    }

    public final void c(String p17)
    {
        float v1_0 = this.e;
        if (v1_0.U()) {
            vn.zalopay.ui.androidsvg.A0 v2_3 = v1_0.c;
            if (v2_3.b) {
                v1_0.a.drawTextOnPath(p17, this.d, this.a, this.b, v2_3.d);
            }
            vn.zalopay.ui.androidsvg.A0 v2_0 = v1_0.c;
            if (v2_0.c) {
                v1_0.a.drawTextOnPath(p17, this.d, this.a, this.b, v2_0.e);
            }
        }
        this.a = (v1_0.c.d.measureText(p17) + this.a);
        return;
    }
}
