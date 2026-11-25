package vn.zalopay.ui.androidsvg;
public class y0 extends m9.z {
    public float a;
    public float b;
    public final synthetic vn.zalopay.ui.androidsvg.C0 c;

    public y0(vn.zalopay.ui.androidsvg.C0 p1, float p2, float p3)
    {
        this.c = p1;
        this.a = p2;
        this.b = p3;
        return;
    }

    public void c(String p6)
    {
        android.graphics.Paint v0_0 = this.c;
        if (v0_0.U()) {
            android.graphics.Paint v1_5 = v0_0.c;
            if (v1_5.b) {
                v0_0.a.drawText(p6, this.a, this.b, v1_5.d);
            }
            android.graphics.Paint v1_1 = v0_0.c;
            if (v1_1.c) {
                v0_0.a.drawText(p6, this.a, this.b, v1_1.e);
            }
        }
        this.a = (v0_0.c.d.measureText(p6) + this.a);
        return;
    }
}
