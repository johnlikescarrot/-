package vn.zalopay.ui.androidsvg;
public final class w0 implements vn.zalopay.ui.androidsvg.M {
    public final android.graphics.Path a;
    public float b;
    public float c;

    public w0(vn.zalopay.ui.androidsvg.L p2)
    {
        this.a = new android.graphics.Path();
        if (p2 != null) {
            p2.n(this);
            return;
        } else {
            return;
        }
    }

    public final void a(float p2, float p3)
    {
        this.a.moveTo(p2, p3);
        this.b = p2;
        this.c = p3;
        return;
    }

    public final void b(float p2, float p3)
    {
        this.a.lineTo(p2, p3);
        this.b = p2;
        this.c = p3;
        return;
    }

    public final void c(float p8, float p9, float p10, float p11, float p12, float p13)
    {
        this.a.cubicTo(p8, p9, p10, p11, p12, p13);
        this.b = p12;
        this.c = p13;
        return;
    }

    public final void close()
    {
        this.a.close();
        return;
    }

    public final void d(float p2, float p3, float p4, float p5)
    {
        this.a.quadTo(p2, p3, p4, p5);
        this.b = p4;
        this.c = p5;
        return;
    }

    public final void e(float p11, float p12, float p13, boolean p14, boolean p15, float p16, float p17)
    {
        vn.zalopay.ui.androidsvg.C0.a(this.b, this.c, p11, p12, p13, p14, p15, p16, p17, this);
        this.b = p16;
        this.c = p17;
        return;
    }
}
