package vn.zalopay.ui.androidsvg;
public final class c extends vn.zalopay.ui.androidsvg.L {

    public c(String p3)
    {
        super(p3.replaceAll("(?s)/\\*.*?\\*/", ""));
        return;
    }

    public static int z(int p2)
    {
        if ((p2 < 48) || (p2 > 57)) {
            if ((p2 < 65) || (p2 > 70)) {
                if ((p2 < 97) || (p2 > 102)) {
                    return -1;
                } else {
                    return (p2 - 87);
                }
            } else {
                return (p2 - 55);
            }
        } else {
            return (p2 - 48);
        }
    }

    public final String A()
    {
        if (!this.m()) {
            String v0_5 = ((String) this.c).charAt(this.a);
            if ((v0_5 == 39) || (v0_5 == 34)) {
                StringBuilder v1_2 = new StringBuilder();
                this.a = (this.a + 1);
                int v2_3 = this.p().intValue();
                while ((v2_3 != -1) && (v2_3 != v0_5)) {
                    if (v2_3 == 92) {
                        v2_3 = this.p().intValue();
                        if (v2_3 != -1) {
                            if ((v2_3 != 10) && ((v2_3 != 13) && (v2_3 != 12))) {
                                int v5_4 = vn.zalopay.ui.androidsvg.c.z(v2_3);
                                if (v5_4 != -1) {
                                    int v6 = 1;
                                    while (v6 <= 5) {
                                        v2_3 = this.p().intValue();
                                        int v7_1 = vn.zalopay.ui.androidsvg.c.z(v2_3);
                                        if (v7_1 == -1) {
                                            break;
                                        }
                                        v5_4 = ((v5_4 * 16) + v7_1);
                                        v6++;
                                    }
                                    v1_2.append(((char) v5_4));
                                }
                            } else {
                                v2_3 = this.p().intValue();
                            }
                        }
                    }
                    v1_2.append(((char) v2_3));
                    v2_3 = this.p().intValue();
                }
                return v1_2.toString();
            }
        }
        return 0;
    }

    public final String B()
    {
        int v1_1;
        String v0_1 = ((String) this.c);
        if (!this.m()) {
            int v1_2 = this.a;
            int v2_0 = v0_1.charAt(v1_2);
            if (v2_0 == 45) {
                v2_0 = this.g();
            }
            if (((v2_0 < 65) || (v2_0 > 90)) && (((v2_0 < 97) || (v2_0 > 122)) && (v2_0 != 95))) {
                int v2_1 = v1_2;
            } else {
                int v2_2 = this.g();
                while (((v2_2 >= 65) && (v2_2 <= 90)) || (((v2_2 >= 97) && (v2_2 <= 122)) || (((v2_2 >= 48) && (v2_2 <= 57)) || ((v2_2 == 45) || (v2_2 == 95))))) {
                    v2_2 = this.g();
                }
                v2_1 = this.a;
            }
            this.a = v1_2;
            v1_1 = v2_1;
        } else {
            v1_1 = this.a;
        }
        int v2_3 = this.a;
        if (v1_1 != v2_3) {
            String v0_2 = v0_1.substring(v2_3, v1_1);
            this.a = v1_1;
            return v0_2;
        } else {
            return 0;
        }
    }

    public final java.util.ArrayList C()
    {
        int v2_3 = 0;
        if (!this.m()) {
            int v3_6 = 1;
            vn.zalopay.ui.androidsvg.a v1_18 = new java.util.ArrayList(1);
            vn.zalopay.ui.androidsvg.n v4_2 = new vn.zalopay.ui.androidsvg.n();
            while ((!this.m()) && (!this.m())) {
                int v6_5;
                int v5_2 = this.a;
                int v6_0 = v4_2.a;
                boolean v8_0 = 2;
                vn.zalopay.ui.androidsvg.o v9_0 = 0;
                if ((v6_0 != 0) && (!v6_0.isEmpty())) {
                    if (!this.j(62)) {
                        if (!this.j(43)) {
                            v6_5 = 0;
                        } else {
                            this.y();
                            v6_5 = 3;
                        }
                    } else {
                        this.y();
                        v6_5 = 2;
                    }
                }
                int v11_7;
                if (!this.j(42)) {
                    int v11_6 = this.B();
                    if (v11_6 == 0) {
                        v11_7 = v2_3;
                    } else {
                        boolean v12_19 = new vn.zalopay.ui.androidsvg.o(v6_5, v11_6);
                        v4_2.b = (v4_2.b + v3_6);
                        v11_7 = v12_19;
                    }
                } else {
                    v11_7 = new vn.zalopay.ui.androidsvg.o(v6_5, v2_3);
                }
                while (!this.m()) {
                    if (!this.j(46)) {
                        if (!this.j(35)) {
                            if (!this.j(91)) {
                                if (!this.j(58)) {
                                    break;
                                }
                                if (v11_7 == 0) {
                                    v11_7 = new vn.zalopay.ui.androidsvg.o(v6_5, v2_3);
                                }
                                String v7_13 = this.B();
                                if (v7_13 == null) {
                                    throw new vn.zalopay.ui.androidsvg.a("Invalid pseudo class");
                                } else {
                                    boolean v12_2 = ((vn.zalopay.ui.androidsvg.h) vn.zalopay.ui.androidsvg.h.e.get(v7_13));
                                    if (!v12_2) {
                                        v12_2 = vn.zalopay.ui.androidsvg.h.d;
                                    }
                                    long v10_2;
                                    vn.zalopay.ui.androidsvg.f v18_1;
                                    vn.zalopay.ui.androidsvg.o v9_1;
                                    switch (v12_2.ordinal()) {
                                        case 0:
                                            v9_1 = v11_7;
                                            v10_2 = new vn.zalopay.ui.androidsvg.g(2);
                                            v4_2.a();
                                            break;
                                        case 1:
                                            v9_1 = v11_7;
                                            v10_2 = new vn.zalopay.ui.androidsvg.g(1);
                                            v4_2.a();
                                            break;
                                        case 2:
                                        case 3:
                                        case 4:
                                        case 5:
                                            if ((v12_2 != vn.zalopay.ui.androidsvg.h.a) && (v12_2 != vn.zalopay.ui.androidsvg.h.b)) {
                                                int v22_4 = v9_0;
                                            } else {
                                                v22_4 = v3_6;
                                            }
                                            if ((v12_2 != vn.zalopay.ui.androidsvg.h.b) && (v12_2 != vn.zalopay.ui.androidsvg.h.c)) {
                                                int v23_4 = v9_0;
                                            } else {
                                                v23_4 = v3_6;
                                            }
                                            boolean v8_13;
                                            boolean v8_9 = this.b;
                                            boolean v12_13 = ((String) this.c);
                                            if (!this.m()) {
                                                java.util.Iterator v13_10 = this.a;
                                                if (this.j(40)) {
                                                    this.y();
                                                    if (!this.k("odd")) {
                                                        if (!this.k("even")) {
                                                            if ((this.j(43)) || (!this.j(45))) {
                                                                int v17_2 = v3_6;
                                                            } else {
                                                                v17_2 = -1;
                                                            }
                                                            int v3_1 = androidx.media3.exoplayer.upstream.t.b(this.a, v8_9, v12_13);
                                                            if (v3_1 != 0) {
                                                                this.a = v3_1.a;
                                                            }
                                                            if ((!this.j(110)) && (!this.j(78))) {
                                                                boolean v8_10 = v3_1;
                                                                v9_1 = v11_7;
                                                                int v2_6 = v17_2;
                                                                v3_1 = 0;
                                                                v17_2 = 1;
                                                            } else {
                                                                if (v3_1 == 0) {
                                                                    v9_1 = v11_7;
                                                                    v3_1 = new androidx.media3.exoplayer.upstream.t(1, this.a);
                                                                } else {
                                                                    v9_1 = v11_7;
                                                                }
                                                                this.y();
                                                                int v11_0 = this.j(43);
                                                                if (v11_0 != 0) {
                                                                    v2_6 = 1;
                                                                } else {
                                                                    v11_0 = this.j(45);
                                                                    if (v11_0 == 0) {
                                                                    } else {
                                                                        v2_6 = -1;
                                                                    }
                                                                }
                                                                if (v11_0 == 0) {
                                                                    v8_10 = 0;
                                                                } else {
                                                                    this.y();
                                                                    v8_10 = androidx.media3.exoplayer.upstream.t.b(this.a, v8_9, v12_13);
                                                                    if (!v8_10) {
                                                                        this.a = v13_10;
                                                                        v8_13 = 0;
                                                                        if (!v8_13) {
                                                                            throw new vn.zalopay.ui.androidsvg.a("Invalid or missing parameter section for pseudo class: ".concat(v7_13));
                                                                        } else {
                                                                            v18_1 = new vn.zalopay.ui.androidsvg.f(v9_1.b, v8_13.b, v8_13.c, v22_4, v23_4);
                                                                            v4_2.a();
                                                                            v10_2 = v18_1;
                                                                        }
                                                                        int v2_23 = v4_2.a;
                                                                        if ((v2_23 != 0) && (!v2_23.isEmpty())) {
                                                                            v1_18.add(v4_2);
                                                                        }
                                                                        return v1_18;
                                                                    } else {
                                                                        this.a = v8_10.a;
                                                                    }
                                                                }
                                                            }
                                                            int v3_4;
                                                            boolean v12_14;
                                                            int v11_3 = new Jj.a;
                                                            if (v3_1 != 0) {
                                                                v12_14 = v11_3;
                                                                v3_4 = (v17_2 * ((int) v3_1.b));
                                                            } else {
                                                                v12_14 = v11_3;
                                                                v3_4 = 0;
                                                            }
                                                            int v2_7;
                                                            if (v8_10) {
                                                                v2_7 = (v2_6 * ((int) v8_10.b));
                                                            } else {
                                                                v2_7 = 0;
                                                            }
                                                            v8_13 = v12_14(v3_4, v2_7, 20);
                                                            this.y();
                                                            if (!this.j(41)) {
                                                                this.a = v13_10;
                                                            }
                                                        } else {
                                                            v8_13 = new Jj.a(2, v9_0, 20);
                                                            v9_1 = v11_7;
                                                        }
                                                    } else {
                                                        v8_13 = new Jj.a(2, v3_6, 20);
                                                    }
                                                } else {
                                                    v8_13 = v2_3;
                                                    v9_1 = v11_7;
                                                }
                                            }
                                        case 6:
                                            int v21_4 = new vn.zalopay.ui.androidsvg.f(0, 0, 1, 1, 0);
                                            v4_2.a();
                                            v9_1 = v11_7;
                                            v10_2 = v21_4;
                                            break;
                                        case 7:
                                            int v20_3 = new vn.zalopay.ui.androidsvg.f(0, 0, 1, 0, 0);
                                            v4_2.a();
                                            v9_1 = v11_7;
                                            v10_2 = v20_3;
                                            break;
                                        case 8:
                                            String v19_2 = new vn.zalopay.ui.androidsvg.f(v11_7.b, 0, 1, 1, 1);
                                            v4_2.a();
                                            v9_1 = v11_7;
                                            v10_2 = v19_2;
                                            break;
                                        case 9:
                                            v18_1 = new vn.zalopay.ui.androidsvg.f(v11_7.b, 0, 1, 0, 1);
                                            v4_2.a();
                                            v9_1 = v11_7;
                                            break;
                                        case 10:
                                            v10_2 = new vn.zalopay.ui.androidsvg.k(v9_0, v2_3);
                                            v4_2.a();
                                            v9_1 = v11_7;
                                            break;
                                        case 11:
                                            v10_2 = new vn.zalopay.ui.androidsvg.k(v3_6, v11_7.b);
                                            v4_2.a();
                                            break;
                                        case 12:
                                            v10_2 = new vn.zalopay.ui.androidsvg.g(0);
                                            v4_2.a();
                                            break;
                                        case 13:
                                            long v10_9;
                                            if (!this.m()) {
                                                boolean v12_6 = this.a;
                                                if (this.j(40)) {
                                                    this.y();
                                                    v10_9 = this.C();
                                                    if (v10_9 != 0) {
                                                        if (this.j(41)) {
                                                            boolean v12_7 = v10_9.iterator();
                                                            while (v12_7.hasNext()) {
                                                                java.util.Iterator v13_7 = ((vn.zalopay.ui.androidsvg.n) v12_7.next()).a;
                                                                if (v13_7 == null) {
                                                                    break;
                                                                }
                                                                java.util.Iterator v13_8 = v13_7.iterator();
                                                                while (v13_8.hasNext()) {
                                                                    int v15_4 = ((vn.zalopay.ui.androidsvg.o) v13_8.next()).d;
                                                                    if (v15_4 == 0) {
                                                                        break;
                                                                    }
                                                                    int v15_5 = v15_4.iterator();
                                                                    while (v15_5.hasNext()) {
                                                                        if ((((vn.zalopay.ui.androidsvg.e) v15_5.next()) instanceof vn.zalopay.ui.androidsvg.i)) {
                                                                            v10_9 = v2_3;
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        } else {
                                                            this.a = v12_6;
                                                        }
                                                    } else {
                                                        this.a = v12_6;
                                                    }
                                                }
                                            }
                                            if (v10_9 == 0) {
                                                throw new vn.zalopay.ui.androidsvg.a("Invalid or missing parameter section for pseudo class: ".concat(v7_13));
                                            } else {
                                                String v7_1 = new vn.zalopay.ui.androidsvg.i(v10_9);
                                                boolean v8_4 = v10_9.iterator();
                                                long v10_11 = -2147483648;
                                                while (v8_4.hasNext()) {
                                                    boolean v12_11 = ((vn.zalopay.ui.androidsvg.n) v8_4.next()).b;
                                                    if (v12_11 > v10_11) {
                                                        v10_11 = v12_11;
                                                    }
                                                }
                                                v4_2.b = v10_11;
                                                v10_2 = v7_1;
                                            }
                                            break;
                                        case 14:
                                            if (!this.m()) {
                                                boolean v12_4 = this.a;
                                                if (this.j(40)) {
                                                    this.y();
                                                    long v10_4 = v2_3;
                                                    do {
                                                        java.util.Iterator v13_1 = this.B();
                                                        if (v13_1 != null) {
                                                            if (v10_4 == 0) {
                                                                v10_4 = new java.util.ArrayList();
                                                            }
                                                            v10_4.add(v13_1);
                                                            this.y();
                                                        } else {
                                                            this.a = v12_4;
                                                        }
                                                    } while(this.x());
                                                    if (!this.j(41)) {
                                                        this.a = v12_4;
                                                    }
                                                }
                                            }
                                            v10_2 = new vn.zalopay.ui.androidsvg.j(v7_13);
                                            v4_2.a();
                                            break;
                                        case 15:
                                        case 16:
                                        case 17:
                                        case 18:
                                        case 19:
                                        case 20:
                                        case 21:
                                        case 22:
                                        case 23:
                                            v10_2 = new vn.zalopay.ui.androidsvg.j(v7_13);
                                            v4_2.a();
                                            break;
                                        default:
                                            throw new vn.zalopay.ui.androidsvg.a("Unsupported pseudo class: ".concat(v7_13));
                                    }
                                    if (v9_1.d == null) {
                                        v9_1.d = new java.util.ArrayList();
                                    }
                                    v9_1.d.add(v10_2);
                                    v11_7 = v9_1;
                                    v2_3 = 0;
                                    v3_6 = 1;
                                    v8_0 = 2;
                                    v9_0 = 0;
                                }
                            } else {
                                if (v11_7 == 0) {
                                    v11_7 = new vn.zalopay.ui.androidsvg.o(v6_5, v2_3);
                                }
                                this.y();
                                boolean v12_17 = this.B();
                                if (!v12_17) {
                                    throw new vn.zalopay.ui.androidsvg.a("Invalid attribute simpleSelectors");
                                } else {
                                    int v14_7;
                                    this.y();
                                    if (!this.j(61)) {
                                        if (!this.k("~=")) {
                                            if (!this.k("|=")) {
                                                v14_7 = v9_0;
                                            } else {
                                                v14_7 = 4;
                                            }
                                        } else {
                                            v14_7 = 3;
                                        }
                                    } else {
                                        v14_7 = v8_0;
                                    }
                                    int v15_7;
                                    if (v14_7 == 0) {
                                        v15_7 = v2_3;
                                    } else {
                                        this.y();
                                        if (!this.m()) {
                                            v15_7 = this.s();
                                            if (v15_7 == 0) {
                                                v15_7 = this.B();
                                            }
                                        } else {
                                            v15_7 = v2_3;
                                        }
                                        if (v15_7 == 0) {
                                            throw new vn.zalopay.ui.androidsvg.a("Invalid attribute simpleSelectors");
                                        } else {
                                            this.y();
                                        }
                                    }
                                    if (!this.j(93)) {
                                        throw new vn.zalopay.ui.androidsvg.a("Invalid attribute simpleSelectors");
                                    } else {
                                        if (v14_7 == 0) {
                                            v14_7 = v3_6;
                                        }
                                        v11_7.a(v14_7, v12_17, v15_7);
                                        v4_2.a();
                                    }
                                }
                            }
                        } else {
                            if (v11_7 == 0) {
                                v11_7 = new vn.zalopay.ui.androidsvg.o(v6_5, v2_3);
                            }
                            boolean v12_20 = this.B();
                            if (!v12_20) {
                                throw new vn.zalopay.ui.androidsvg.a("Invalid \"#id\" simpleSelectors");
                            } else {
                                v11_7.a(v8_0, "id", v12_20);
                                v4_2.b = (v4_2.b + 1000000);
                            }
                        }
                    } else {
                        if (v11_7 == 0) {
                            v11_7 = new vn.zalopay.ui.androidsvg.o(v6_5, v2_3);
                        }
                        boolean v12_23 = this.B();
                        if (!v12_23) {
                            throw new vn.zalopay.ui.androidsvg.a("Invalid \".class\" simpleSelectors");
                        } else {
                            v11_7.a(v8_0, "class", v12_23);
                            v4_2.a();
                        }
                    }
                }
                if (v11_7 == 0) {
                    this.a = v5_2;
                    break;
                } else {
                    if (v4_2.a == null) {
                        v4_2.a = new java.util.ArrayList();
                    }
                    v4_2.a.add(v11_7);
                    if (this.x()) {
                        v1_18.add(v4_2);
                        v4_2 = new vn.zalopay.ui.androidsvg.n();
                    }
                    v2_3 = 0;
                    v3_6 = 1;
                }
            }
        } else {
            return 0;
        }
    }
}
