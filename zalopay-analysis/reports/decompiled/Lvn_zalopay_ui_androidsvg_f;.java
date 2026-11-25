package vn.zalopay.ui.androidsvg;
public final class f implements vn.zalopay.ui.androidsvg.e {
    public final int a;
    public final int b;
    public final boolean c;
    public final boolean d;
    public final String e;

    public f(String p1, int p2, int p3, boolean p4, boolean p5)
    {
        this.a = p2;
        this.b = p3;
        this.c = p4;
        this.d = p5;
        this.e = p1;
        return;
    }

    public final boolean a(vn.zalopay.ui.androidsvg.a0 p8)
    {
        String v1 = this.e;
        if ((this.d) && (v1 == null)) {
            v1 = p8.n();
        }
        int v4_0;
        int v5;
        int v0_7 = p8.b;
        if (v0_7 == 0) {
            v4_0 = 0;
            v5 = 1;
        } else {
            int v0_2 = v0_7.getChildren().iterator();
            v4_0 = 0;
            v5 = 0;
            while (v0_2.hasNext()) {
                boolean v6_3 = ((vn.zalopay.ui.androidsvg.a0) ((vn.zalopay.ui.androidsvg.c0) v0_2.next()));
                if (v6_3 == p8) {
                    v4_0 = v5;
                }
                if ((v1 == null) || (v6_3.n().equals(v1))) {
                    v5++;
                }
            }
        }
        int v4_1;
        if (!this.c) {
            v4_1 = (v5 - v4_0);
        } else {
            v4_1 = (v4_0 + 1);
        }
        int v8_2 = this.a;
        int v0_3 = this.b;
        if (v8_2 != 0) {
            int v4_2 = (v4_1 - v0_3);
            if (((v4_2 % v8_2) == 0) && ((Integer.signum(v4_2) == 0) || (Integer.signum(v4_2) == Integer.signum(v8_2)))) {
                return 1;
            }
        } else {
            if (v4_1 == v0_3) {
                return 1;
            }
        }
        return 0;
    }

    public final String toString()
    {
        String v0_3;
        if (!this.c) {
            v0_3 = "last-";
        } else {
            v0_3 = "";
        }
        Integer v2_2 = this.b;
        String v3_1 = this.a;
        if (!this.d) {
            return String.format("nth-%schild(%dn%+d)", new Object[] {v0_3, Integer.valueOf(v3_1), Integer.valueOf(v2_2)}));
        } else {
            return String.format("nth-%schild(%dn%+d of type <%s>)", new Object[] {v0_3, Integer.valueOf(v3_1), Integer.valueOf(v2_2), this.e}));
        }
    }
}
