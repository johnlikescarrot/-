package vn.zalopay.ui.androidsvg;
public final class z0 extends m9.z {
    public final synthetic int a;
    public float b;
    public final float c;
    public final synthetic vn.zalopay.ui.androidsvg.C0 d;
    public final Object e;

    public z0(vn.zalopay.ui.androidsvg.C0 p2, float p3, float p4)
    {
        this.a = 1;
        this.d = p2;
        this.e = new android.graphics.RectF();
        this.b = p3;
        this.c = p4;
        return;
    }

    public z0(vn.zalopay.ui.androidsvg.C0 p2, float p3, float p4, android.graphics.Path p5)
    {
        this.a = 0;
        this.d = p2;
        this.b = p3;
        this.c = p4;
        this.e = p5;
        return;
    }

    public final boolean b(vn.zalopay.ui.androidsvg.m0 p5)
    {
        switch (this.a) {
            case 0:
                android.graphics.RectF v5_1;
                if (!(p5 instanceof vn.zalopay.ui.androidsvg.n0)) {
                    v5_1 = 1;
                } else {
                    ck.b.e("SVGAndroidRenderer", new Object[] {"Using <textPath> elements in a clip path is not supported."}));
                    v5_1 = 0;
                }
                return v5_1;
            default:
                int v1 = 1;
                if ((p5 instanceof vn.zalopay.ui.androidsvg.n0)) {
                    android.graphics.RectF v5_4 = p5.a.c(((vn.zalopay.ui.androidsvg.n0) p5).n);
                    if (v5_4 != null) {
                        android.graphics.RectF v5_5 = ((vn.zalopay.ui.androidsvg.K) v5_4);
                        android.graphics.RectF v5_6 = v5_5.n;
                        android.graphics.RectF v0_6 = new vn.zalopay.ui.androidsvg.w0(v5_5.o).a;
                        if (v5_6 != null) {
                            v0_6.transform(v5_6);
                        }
                        android.graphics.RectF v5_8 = new android.graphics.RectF();
                        v0_6.computeBounds(v5_8, 1);
                        ((android.graphics.RectF) this.e).union(v5_8);
                    } else {
                        vn.zalopay.ui.androidsvg.C0.o("TextPath path reference \'%s\' not found", new Object[] {((vn.zalopay.ui.androidsvg.n0) p5).n}));
                    }
                    v1 = 0;
                }
                return v1;
        }
    }

    public final void c(String p10)
    {
        switch (this.a) {
            case 0:
                android.graphics.Path v3_0;
                float v0_1 = this.d;
                if (!v0_1.U()) {
                    v3_0 = p10;
                } else {
                    android.graphics.Path v8_1 = new android.graphics.Path();
                    v3_0 = p10;
                    v0_1.c.d.getTextPath(p10, 0, p10.length(), this.b, this.c, v8_1);
                    ((android.graphics.Path) this.e).addPath(v8_1);
                }
                this.b = (v0_1.c.d.measureText(v3_0) + this.b);
                return;
            default:
                float v0_6 = this.d;
                if (v0_6.U()) {
                    vn.zalopay.ui.androidsvg.A0 v1_2 = new android.graphics.Rect();
                    v0_6.c.d.getTextBounds(p10, 0, p10.length(), v1_2);
                    android.graphics.Paint v2_4 = new android.graphics.RectF(v1_2);
                    v2_4.offset(this.b, this.c);
                    ((android.graphics.RectF) this.e).union(v2_4);
                }
                this.b = (v0_6.c.d.measureText(p10) + this.b);
                return;
        }
    }
}
