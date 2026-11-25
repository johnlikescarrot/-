package vn.zalopay.ui.androidsvg;
public final class o {
    public final int a;
    public final String b;
    public java.util.ArrayList c;
    public java.util.ArrayList d;

    public o(int p2, String p3)
    {
        this.c = 0;
        this.d = 0;
        if (p2 == 0) {
            p2 = 1;
        }
        this.a = p2;
        this.b = p3;
        return;
    }

    public final void a(int p3, String p4, String p5)
    {
        if (this.c == null) {
            this.c = new java.util.ArrayList();
        }
        this.c.add(new vn.zalopay.ui.androidsvg.b(p4, p3, p5));
        return;
    }

    public final String toString()
    {
        String v0_1 = new StringBuilder();
        vn.zalopay.ui.androidsvg.e v2_4 = this.a;
        if (v2_4 != 2) {
            if (v2_4 == 3) {
                v0_1.append("+ ");
            }
        } else {
            v0_1.append("> ");
        }
        vn.zalopay.ui.androidsvg.e v2_0 = this.b;
        if (v2_0 == null) {
            v2_0 = "*";
        }
        v0_1.append(v2_0);
        vn.zalopay.ui.androidsvg.e v2_1 = this.c;
        if (v2_1 != null) {
            vn.zalopay.ui.androidsvg.e v2_2 = v2_1.iterator();
            while (v2_2.hasNext()) {
                String v4_2 = ((vn.zalopay.ui.androidsvg.b) v2_2.next());
                v0_1.append(91);
                String v6 = v4_2.c;
                v0_1.append(v4_2.a);
                String v4_4 = androidx.camera.camera2.internal.G.l(v4_2.b);
                if (v4_4 == 1) {
                    v0_1.append(61);
                    v0_1.append(v6);
                } else {
                    if (v4_4 == 2) {
                        v0_1.append("~=");
                        v0_1.append(v6);
                    } else {
                        if (v4_4 == 3) {
                            v0_1.append("|=");
                            v0_1.append(v6);
                        }
                    }
                }
                v0_1.append(93);
            }
        }
        java.util.Iterator v1_1 = this.d;
        if (v1_1 != null) {
            java.util.Iterator v1_2 = v1_1.iterator();
            while (v1_2.hasNext()) {
                vn.zalopay.ui.androidsvg.e v2_6 = ((vn.zalopay.ui.androidsvg.e) v1_2.next());
                v0_1.append(58);
                v0_1.append(v2_6);
            }
        }
        return v0_1.toString();
    }
}
