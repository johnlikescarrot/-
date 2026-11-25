package vn.zalopay.ui.androidsvg;
public final class u0 implements vn.zalopay.ui.androidsvg.M {
    public final java.util.ArrayList a;
    public float b;
    public float c;
    public vn.zalopay.ui.androidsvg.v0 d;
    public boolean e;
    public boolean f;
    public int g;
    public boolean h;

    public u0(vn.zalopay.ui.androidsvg.L p4)
    {
        java.util.ArrayList v0_1 = new java.util.ArrayList();
        this.a = v0_1;
        this.d = 0;
        this.e = 0;
        this.f = 1;
        this.g = -1;
        if (p4 != null) {
            p4.n(this);
            if (this.h) {
                this.d.b(((vn.zalopay.ui.androidsvg.v0) v0_1.get(this.g)));
                v0_1.set(this.g, this.d);
                this.h = 0;
            }
            vn.zalopay.ui.androidsvg.v0 v4_4 = this.d;
            if (v4_4 != null) {
                v0_1.add(v4_4);
            }
        }
        return;
    }

    public final void a(float p4, float p5)
    {
        java.util.ArrayList v1 = this.a;
        if (this.h) {
            this.d.b(((vn.zalopay.ui.androidsvg.v0) v1.get(this.g)));
            v1.set(this.g, this.d);
            this.h = 0;
        }
        vn.zalopay.ui.androidsvg.v0 v0_2 = this.d;
        if (v0_2 != null) {
            v1.add(v0_2);
        }
        this.b = p4;
        this.c = p5;
        this.d = new vn.zalopay.ui.androidsvg.v0(p4, p5, 0, 0);
        this.g = v1.size();
        return;
    }

    public final void b(float p4, float p5)
    {
        this.d.a(p4, p5);
        this.a.add(this.d);
        float v1_2 = this.d;
        this.d = new vn.zalopay.ui.androidsvg.v0(p4, p5, (p4 - v1_2.a), (p5 - v1_2.b));
        this.h = 0;
        return;
    }

    public final void c(float p3, float p4, float p5, float p6, float p7, float p8)
    {
        if ((this.f) || (this.e)) {
            this.d.a(p3, p4);
            this.a.add(this.d);
            this.e = 0;
        }
        this.d = new vn.zalopay.ui.androidsvg.v0(p7, p8, (p7 - p5), (p8 - p6));
        this.h = 0;
        return;
    }

    public final void close()
    {
        this.a.add(this.d);
        this.b(this.b, this.c);
        this.h = 1;
        return;
    }

    public final void d(float p3, float p4, float p5, float p6)
    {
        this.d.a(p3, p4);
        this.a.add(this.d);
        this.d = new vn.zalopay.ui.androidsvg.v0(p5, p6, (p5 - p3), (p6 - p4));
        this.h = 0;
        return;
    }

    public final void e(float p13, float p14, float p15, boolean p16, boolean p17, float p18, float p19)
    {
        this.e = 1;
        this.f = 0;
        float v0_0 = this.d;
        vn.zalopay.ui.androidsvg.C0.a(v0_0.a, v0_0.b, p13, p14, p15, p16, p17, p18, p19, this);
        this.f = 1;
        this.h = 0;
        return;
    }
}
