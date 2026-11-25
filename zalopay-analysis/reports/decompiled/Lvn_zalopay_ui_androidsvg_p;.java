package vn.zalopay.ui.androidsvg;
public final class p {
    public final vn.zalopay.ui.androidsvg.d a;
    public final int b;
    public boolean c;

    public p(int p2)
    {
        this.c = 0;
        this.a = vn.zalopay.ui.androidsvg.d.b;
        this.b = p2;
        return;
    }

    public static int a(java.util.ArrayList p1, int p2, vn.zalopay.ui.androidsvg.a0 p3)
    {
        int v0 = 0;
        if (p2 >= null) {
            vn.zalopay.ui.androidsvg.c0 v2_4 = p3.b;
            if (p1.get(p2) == v2_4) {
                int v1_4 = v2_4.getChildren().iterator();
                while (v1_4.hasNext()) {
                    if (((vn.zalopay.ui.androidsvg.c0) v1_4.next()) != p3) {
                        v0++;
                    } else {
                        return v0;
                    }
                }
            }
            return -1;
        } else {
            return 0;
        }
    }

    public static java.util.ArrayList c(vn.zalopay.ui.androidsvg.c p9)
    {
        java.util.ArrayList v0_1 = new java.util.ArrayList();
        if ((p9.x()) && (!p9.m())) {
            boolean v1_4 = ((String) p9.c);
            String v3_2 = 0;
            if (!p9.m()) {
                int v2_0 = p9.a;
                char v4 = v1_4.charAt(v2_0);
                if (((v4 < 65) || (v4 > 90)) && ((v4 < 97) || (v4 > 122))) {
                    p9.a = v2_0;
                } else {
                    String v3_0 = p9.g();
                    while (((v3_0 >= 65) && (v3_0 <= 90)) || ((v3_0 >= 97) && (v3_0 <= 122))) {
                        v3_0 = p9.g();
                    }
                    v3_2 = v1_4.substring(v2_0, p9.a);
                }
            }
            if (v3_2 != null) {
                v0_1.add(vn.zalopay.ui.androidsvg.d.valueOf(v3_2));
            }
        }
        return v0_1;
    }

    public static boolean f(vn.zalopay.ui.androidsvg.n p3, int p4, java.util.ArrayList p5, int p6, vn.zalopay.ui.androidsvg.a0 p7)
    {
        int v0_4 = ((vn.zalopay.ui.androidsvg.o) p3.a.get(p4));
        if (vn.zalopay.ui.androidsvg.p.i(v0_4, p7)) {
            int v0_5 = v0_4.a;
            if (v0_5 != 1) {
                if (v0_5 != 2) {
                    int v0_1 = vn.zalopay.ui.androidsvg.p.a(p5, p6, p7);
                    if (v0_1 > 0) {
                        return vn.zalopay.ui.androidsvg.p.f(p3, (p4 - 1), p5, p6, ((vn.zalopay.ui.androidsvg.a0) p7.b.getChildren().get((v0_1 - 1))));
                    }
                } else {
                    return vn.zalopay.ui.androidsvg.p.h(p3, (p4 - 1), p5, p6);
                }
            } else {
                if (p4 != 0) {
                    while (p6 >= 0) {
                        if (!vn.zalopay.ui.androidsvg.p.h(p3, (p4 - 1), p5, p6)) {
                            p6--;
                        }
                    }
                    return 0;
                }
                return 1;
            }
        }
        return 0;
    }

    public static boolean g(vn.zalopay.ui.androidsvg.n p5, vn.zalopay.ui.androidsvg.a0 p6)
    {
        java.util.ArrayList v0_1 = new java.util.ArrayList();
        int v1_0 = p6.b;
        while(true) {
            int v2_0 = 0;
            if (v1_0 == 0) {
                break;
            }
            v0_1.add(0, v1_0);
            v1_0 = ((vn.zalopay.ui.androidsvg.c0) v1_0).b;
        }
        java.util.ArrayList v4_0;
        int v1_3 = (v0_1.size() - 1);
        java.util.ArrayList v4_2 = p5.a;
        if (v4_2 != null) {
            v4_0 = v4_2.size();
        } else {
            v4_0 = 0;
        }
        if (v4_0 != 1) {
            java.util.ArrayList v4_1 = p5.a;
            if (v4_1 != null) {
                v2_0 = v4_1.size();
            }
            return vn.zalopay.ui.androidsvg.p.f(p5, (v2_0 - 1), v0_1, v1_3, p6);
        } else {
            return vn.zalopay.ui.androidsvg.p.i(((vn.zalopay.ui.androidsvg.o) p5.a.get(0)), p6);
        }
    }

    public static boolean h(vn.zalopay.ui.androidsvg.n p4, int p5, java.util.ArrayList p6, int p7)
    {
        vn.zalopay.ui.androidsvg.a0 v0_7 = ((vn.zalopay.ui.androidsvg.o) p4.a.get(p5));
        java.util.List v1_3 = ((vn.zalopay.ui.androidsvg.a0) p6.get(p7));
        if (vn.zalopay.ui.androidsvg.p.i(v0_7, v1_3)) {
            vn.zalopay.ui.androidsvg.a0 v0_1 = v0_7.a;
            if (v0_1 != 1) {
                if (v0_1 != 2) {
                    vn.zalopay.ui.androidsvg.a0 v0_2 = vn.zalopay.ui.androidsvg.p.a(p6, p7, v1_3);
                    if (v0_2 > null) {
                        return vn.zalopay.ui.androidsvg.p.f(p4, (p5 - 1), p6, p7, ((vn.zalopay.ui.androidsvg.a0) v1_3.b.getChildren().get((v0_2 - 1))));
                    }
                } else {
                    return vn.zalopay.ui.androidsvg.p.h(p4, (p5 - 1), p6, (p7 - 1));
                }
            } else {
                if (p5 != 0) {
                    while (p7 > 0) {
                        p7--;
                        if (vn.zalopay.ui.androidsvg.p.h(p4, (p5 - 1), p6, p7)) {
                        }
                    }
                    return 0;
                }
                return 1;
            }
        }
        return 0;
    }

    public static boolean i(vn.zalopay.ui.androidsvg.o p4, vn.zalopay.ui.androidsvg.a0 p5)
    {
        boolean v0_0 = p4.b;
        if ((!v0_0) || (v0_0.equals(p5.n().toLowerCase(java.util.Locale.US)))) {
            boolean v0_1 = p4.c;
            if (v0_1) {
                boolean v0_2 = v0_1.iterator();
                while (v0_2.hasNext()) {
                    boolean v1_3 = ((vn.zalopay.ui.androidsvg.b) v0_2.next());
                    java.util.ArrayList v2_0 = v1_3.a;
                    boolean v1_4 = v1_3.c;
                    if (v2_0.equals("id")) {
                        if (!v1_4.equals(p5.c)) {
                            return 0;
                        }
                    } else {
                        if (v2_0.equals("class")) {
                            java.util.ArrayList v2_4 = p5.g;
                            if ((v2_4 == null) || (!v2_4.contains(v1_4))) {
                                return 0;
                            }
                        }
                    }
                }
            }
            int v4_1 = p4.d;
            if (v4_1 != 0) {
                int v4_2 = v4_1.iterator();
                while (v4_2.hasNext()) {
                    if (!((vn.zalopay.ui.androidsvg.e) v4_2.next()).a(p5)) {
                    }
                    return 0;
                }
            }
            return 1;
        }
        return 0;
    }

    public final void b(vn.zalopay.ui.androidsvg.m p12, vn.zalopay.ui.androidsvg.c p13)
    {
        int v0_0 = p13.B();
        p13.y();
        if (v0_0 == 0) {
            throw new vn.zalopay.ui.androidsvg.a("Invalid \'@\' rule");
        } else {
            int v4_0 = 0;
            if ((this.c) || (!v0_0.equals("media"))) {
                if ((this.c) || (!v0_0.equals("import"))) {
                    ck.b.e("AndroidSVG CSSParser", new Object[] {A1.a.i("Ignoring @", v0_0, " rule")}));
                    while (!p13.m()) {
                        vn.zalopay.ui.androidsvg.a v12_22 = p13.p().intValue();
                        if ((v12_22 == 59) && (v4_0 == 0)) {
                            break;
                        }
                        if (v12_22 != 123) {
                            if ((v12_22 == 125) && (v4_0 > 0)) {
                                v4_0--;
                                if (v4_0 == 0) {
                                    break;
                                }
                            }
                        } else {
                            v4_0++;
                        }
                    }
                } else {
                    int v0_1 = 0;
                    if (!p13.m()) {
                        vn.zalopay.ui.androidsvg.a v12_24 = p13.a;
                        if (p13.k("url(")) {
                            p13.y();
                            String v3_2 = p13.A();
                            if (v3_2 == null) {
                                String v3_6 = ((String) p13.c);
                                int v4_5 = new StringBuilder();
                                while (!p13.m()) {
                                    int v5_8 = v3_6.charAt(p13.a);
                                    if ((v5_8 == 39) || ((v5_8 == 34) || ((v5_8 == 40) || ((v5_8 == 41) || ((vn.zalopay.ui.androidsvg.L.o(v5_8)) || (Character.isISOControl(v5_8))))))) {
                                        break;
                                    }
                                    p13.a = (p13.a + 1);
                                    if (v5_8 == 92) {
                                        if (!p13.m()) {
                                            int v5_10 = p13.a;
                                            p13.a = (v5_10 + 1);
                                            v5_8 = v3_6.charAt(v5_10);
                                            if ((v5_8 != 10) && ((v5_8 != 13) && (v5_8 != 12))) {
                                                int v7_2 = vn.zalopay.ui.androidsvg.c.z(v5_8);
                                                if (v7_2 != -1) {
                                                    int v5_1 = 1;
                                                    while ((v5_1 <= 5) && (!p13.m())) {
                                                        int v9_4 = vn.zalopay.ui.androidsvg.c.z(v3_6.charAt(p13.a));
                                                        if (v9_4 == -1) {
                                                            break;
                                                        }
                                                        p13.a = (p13.a + 1);
                                                        v7_2 = ((v7_2 * 16) + v9_4);
                                                        v5_1++;
                                                    }
                                                    v4_5.append(((char) v7_2));
                                                }
                                            }
                                        }
                                    }
                                    v4_5.append(((char) v5_8));
                                }
                                if (v4_5.length() != 0) {
                                    v3_2 = v4_5.toString();
                                } else {
                                    v3_2 = 0;
                                }
                            }
                            if (v3_2 != null) {
                                p13.y();
                                if ((!p13.m()) && (!p13.k(")"))) {
                                    p13.a = v12_24;
                                } else {
                                    v0_1 = v3_2;
                                }
                            } else {
                                p13.a = v12_24;
                            }
                        }
                    }
                    if (v0_1 == 0) {
                        v0_1 = p13.A();
                    }
                    if (v0_1 == 0) {
                        throw new vn.zalopay.ui.androidsvg.a("Invalid @import rule: expected string or url()");
                    } else {
                        p13.y();
                        vn.zalopay.ui.androidsvg.p.c(p13);
                        if ((!p13.m()) && (!p13.j(59))) {
                            throw new vn.zalopay.ui.androidsvg.a("Invalid @media rule: expected \'}\' at end of rule set");
                        }
                    }
                }
            } else {
                int v0_2 = vn.zalopay.ui.androidsvg.p.c(p13);
                if (!p13.j(123)) {
                    throw new vn.zalopay.ui.androidsvg.a("Invalid @media rule: missing rule set");
                } else {
                    p13.y();
                    int v0_3 = v0_2.iterator();
                    while (v0_3.hasNext()) {
                        vn.zalopay.ui.androidsvg.d v1_5 = ((vn.zalopay.ui.androidsvg.d) v0_3.next());
                        if ((v1_5 == vn.zalopay.ui.androidsvg.d.a) || (v1_5 == this.a)) {
                            this.c = 1;
                            p12.b(this.e(p13));
                            this.c = 0;
                        }
                        if ((!p13.m()) && (!p13.j(125))) {
                            throw new vn.zalopay.ui.androidsvg.a("Invalid @media rule: expected \'}\' at end of rule set");
                        }
                    }
                    this.e(p13);
                }
            }
            p13.y();
            return;
        }
    }

    public final boolean d(vn.zalopay.ui.androidsvg.m p14, vn.zalopay.ui.androidsvg.c p15)
    {
        vn.zalopay.ui.androidsvg.n v0_0 = p15.C();
        if ((v0_0 == null) || (v0_0.isEmpty())) {
            return 0;
        } else {
            if (!p15.j(123)) {
                throw new vn.zalopay.ui.androidsvg.a("Malformed rule block: expected \'{\'");
            } else {
                p15.y();
                vn.zalopay.ui.androidsvg.U v1_2 = new vn.zalopay.ui.androidsvg.U();
                do {
                    vn.zalopay.ui.androidsvg.l v2_2 = p15.B();
                    p15.y();
                    if (!p15.j(58)) {
                        throw new vn.zalopay.ui.androidsvg.a("Expected \':\'");
                    } else {
                        p15.y();
                        int v3_4 = ((String) p15.c);
                        String v9 = 0;
                        if (!p15.m()) {
                            int v4_1 = p15.a;
                            int v10_0 = v3_4.charAt(v4_1);
                            int v11 = v4_1;
                            while ((v10_0 != -1) && ((v10_0 != 59) && ((v10_0 != 125) && ((v10_0 != 33) && ((v10_0 != 10) && (v10_0 != 13)))))) {
                                if (!vn.zalopay.ui.androidsvg.L.o(v10_0)) {
                                    v11 = (p15.a + 1);
                                }
                                v10_0 = p15.g();
                            }
                            if (p15.a <= v4_1) {
                                p15.a = v4_1;
                            } else {
                                v9 = v3_4.substring(v4_1, v11);
                            }
                        }
                        if (v9 == null) {
                            throw new vn.zalopay.ui.androidsvg.a("Expected property value");
                        } else {
                            p15.y();
                            if (p15.j(33)) {
                                p15.y();
                                if (!p15.k("important")) {
                                    throw new vn.zalopay.ui.androidsvg.a("Malformed rule set: found unexpected \'!\'");
                                } else {
                                    p15.y();
                                }
                            }
                            p15.j(59);
                            vn.zalopay.ui.androidsvg.M0.E(v1_2, v2_2, v9);
                            p15.y();
                        }
                    }
                } while((!p15.m()) && (!p15.j(125)));
                p15.y();
                java.util.Iterator v15_5 = v0_0.iterator();
                while (v15_5.hasNext()) {
                    p14.a(new vn.zalopay.ui.androidsvg.l(((vn.zalopay.ui.androidsvg.n) v15_5.next()), v1_2, this.b));
                }
                return 1;
            }
        }
    }

    public final vn.zalopay.ui.androidsvg.m e(vn.zalopay.ui.androidsvg.c p4)
    {
        vn.zalopay.ui.androidsvg.m v0_1 = new vn.zalopay.ui.androidsvg.m();
        while (!p4.m()) {
            if (!p4.k("<!--")) {
                if (!p4.k("-->")) {
                    if (!p4.j(64)) {
                        if (!this.d(v0_1, p4)) {
                            break;
                        }
                    } else {
                        this.b(v0_1, p4);
                    }
                } else {
                }
            } else {
            }
        }
        return v0_1;
    }
}
