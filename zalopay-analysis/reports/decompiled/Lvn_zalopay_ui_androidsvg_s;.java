package vn.zalopay.ui.androidsvg;
public final class s {
    public static final vn.zalopay.ui.androidsvg.s c;
    public static final vn.zalopay.ui.androidsvg.s d;
    public final vn.zalopay.ui.androidsvg.r a;
    public final int b;

    static s()
    {
        vn.zalopay.ui.androidsvg.s.c = new vn.zalopay.ui.androidsvg.s(vn.zalopay.ui.androidsvg.r.a, 0);
        vn.zalopay.ui.androidsvg.s.d = new vn.zalopay.ui.androidsvg.s(vn.zalopay.ui.androidsvg.r.f, 1);
        return;
    }

    public s(vn.zalopay.ui.androidsvg.r p1, int p2)
    {
        this.a = p1;
        this.b = p2;
        return;
    }

    public final boolean equals(Object p3)
    {
        if ((this != p3) && ((p3 == 0) || ((vn.zalopay.ui.androidsvg.s != p3.getClass()) || ((this.a != ((vn.zalopay.ui.androidsvg.s) p3).a) || (this.b != ((vn.zalopay.ui.androidsvg.s) p3).b))))) {
            return 0;
        } else {
            return 1;
        }
    }

    public final String toString()
    {
        String v1_1;
        String v0_1 = new StringBuilder();
        v0_1.append(this.a);
        v0_1.append(" ");
        int v2 = this.b;
        if (v2 == 1) {
            v1_1 = "meet";
        } else {
            if (v2 == 2) {
                v1_1 = "slice";
            } else {
                v1_1 = "null";
            }
        }
        v0_1.append(v1_1);
        return v0_1.toString();
    }
}
