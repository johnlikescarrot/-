package vn.zalopay.ui.androidsvg;
public final class l {
    public final vn.zalopay.ui.androidsvg.n a;
    public final vn.zalopay.ui.androidsvg.U b;
    public final int c;

    public l(vn.zalopay.ui.androidsvg.n p1, vn.zalopay.ui.androidsvg.U p2, int p3)
    {
        this.a = p1;
        this.b = p2;
        this.c = p3;
        return;
    }

    public final String toString()
    {
        String v1_1;
        String v0_1 = new StringBuilder();
        v0_1.append(this.a);
        v0_1.append(" {...} (src=");
        int v2 = this.c;
        if (v2 == 1) {
            v1_1 = "Document";
        } else {
            if (v2 == 2) {
                v1_1 = "RenderOptions";
            } else {
                v1_1 = "null";
            }
        }
        v0_1.append(v1_1);
        v0_1.append(")");
        return v0_1.toString();
    }
}
