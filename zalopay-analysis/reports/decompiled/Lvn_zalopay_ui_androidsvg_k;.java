package vn.zalopay.ui.androidsvg;
public final class k implements vn.zalopay.ui.androidsvg.e {
    public final boolean a;
    public final String b;

    public k(boolean p1, String p2)
    {
        this.a = p1;
        this.b = p2;
        return;
    }

    public final boolean a(vn.zalopay.ui.androidsvg.a0 p6)
    {
        String v1 = this.b;
        if ((this.a) && (v1 == null)) {
            v1 = p6.n();
        }
        int v3;
        java.util.Iterator v6_3 = p6.b;
        if (v6_3 == null) {
            v3 = 1;
        } else {
            java.util.Iterator v6_2 = v6_3.getChildren().iterator();
            v3 = 0;
            while (v6_2.hasNext()) {
                boolean v4_1 = v6_2.next();
                if ((v1 == null) || (((vn.zalopay.ui.androidsvg.a0) ((vn.zalopay.ui.androidsvg.c0) v4_1)).n().equals(v1))) {
                    v3++;
                }
            }
        }
        if (v3 != 1) {
            return 0;
        } else {
            return 1;
        }
    }

    public final String toString()
    {
        if (!this.a) {
            return "only-child";
        } else {
            return A1.a.i("only-of-type <", this.b, ">");
        }
    }
}
