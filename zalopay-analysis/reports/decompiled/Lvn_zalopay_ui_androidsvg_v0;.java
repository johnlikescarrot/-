package vn.zalopay.ui.androidsvg;
public final class v0 {
    public final float a;
    public final float b;
    public float c;
    public float d;
    public boolean e;

    public v0(float p3, float p4, float p5, float p6)
    {
        this.c = 0;
        this.d = 0;
        this.e = 0;
        this.a = p3;
        this.b = p4;
        float v3_2 = Math.sqrt(((double) ((p6 * p6) + (p5 * p5))));
        if (v3_2 != 0) {
            this.c = ((float) (((double) p5) / v3_2));
            this.d = ((float) (((double) p6) / v3_2));
        }
        return;
    }

    public final void a(float p5, float p6)
    {
        float v5_1 = (p5 - this.a);
        float v6_1 = (p6 - this.b);
        int v0_7 = Math.sqrt(((double) ((v6_1 * v6_1) + (v5_1 * v5_1))));
        if (v0_7 != 0) {
            v5_1 = ((float) (((double) v5_1) / v0_7));
            v6_1 = ((float) (((double) v6_1) / v0_7));
        }
        int v0_1 = this.c;
        if ((v5_1 != (- v0_1)) || (v6_1 != (- this.d))) {
            this.c = (v0_1 + v5_1);
            this.d = (this.d + v6_1);
            return;
        } else {
            this.e = 1;
            this.c = (- v6_1);
            this.d = v5_1;
            return;
        }
    }

    public final void b(vn.zalopay.ui.androidsvg.v0 p5)
    {
        float v0_0 = p5.c;
        float v1_0 = this.c;
        if (v0_0 == (- v1_0)) {
            float v2_2 = p5.d;
            if (v2_2 == (- this.d)) {
                this.e = 1;
                this.c = (- v2_2);
                this.d = p5.c;
                return;
            }
        }
        this.c = (v1_0 + v0_0);
        this.d = (this.d + p5.d);
        return;
    }

    public final String toString()
    {
        String v0_2 = new StringBuilder("(");
        v0_2.append(this.a);
        v0_2.append(",");
        v0_2.append(this.b);
        v0_2.append(" ");
        v0_2.append(this.c);
        v0_2.append(",");
        return androidx.camera.camera2.internal.G.c(this.d, ")", v0_2);
    }
}
