package vn.zalopay.ui.androidsvg;
public class L implements vn.zalopay.ui.androidsvg.M {
    public int a;
    public int b;
    public java.io.Serializable c;
    public Object d;

    public L(String p2)
    {
        this.a = 0;
        this.d = new vn.zalopay.ui.androidsvg.q();
        int v2_2 = p2.trim();
        this.c = v2_2;
        this.b = v2_2.length();
        return;
    }

    public static boolean o(int p1)
    {
        if ((p1 != 32) && ((p1 != 10) && ((p1 != 13) && (p1 != 9)))) {
            return 0;
        } else {
            return 1;
        }
    }

    public void a(float p5, float p6)
    {
        this.f(0);
        this.l(2);
        float[] v1_1 = ((float[]) this.d);
        int v2_1 = this.b;
        int v3 = (v2_1 + 1);
        this.b = v3;
        v1_1[v2_1] = p5;
        this.b = (v2_1 + 2);
        v1_1[v3] = p6;
        return;
    }

    public void b(float p5, float p6)
    {
        this.f(1);
        this.l(2);
        float[] v1_1 = ((float[]) this.d);
        int v2_1 = this.b;
        int v3 = (v2_1 + 1);
        this.b = v3;
        v1_1[v2_1] = p5;
        this.b = (v2_1 + 2);
        v1_1[v3] = p6;
        return;
    }

    public void c(float p5, float p6, float p7, float p8, float p9, float p10)
    {
        this.f(2);
        this.l(6);
        float[] v1_1 = ((float[]) this.d);
        int v2_1 = this.b;
        int v3 = (v2_1 + 1);
        this.b = v3;
        v1_1[v2_1] = p5;
        int v5_1 = (v2_1 + 2);
        this.b = v5_1;
        v1_1[v3] = p6;
        int v6_1 = (v2_1 + 3);
        this.b = v6_1;
        v1_1[v5_1] = p7;
        int v5_2 = (v2_1 + 4);
        this.b = v5_2;
        v1_1[v6_1] = p8;
        int v6_2 = (v2_1 + 5);
        this.b = v6_2;
        v1_1[v5_2] = p9;
        this.b = (v2_1 + 6);
        v1_1[v6_2] = p10;
        return;
    }

    public void close()
    {
        this.f(8);
        return;
    }

    public void d(float p5, float p6, float p7, float p8)
    {
        this.f(3);
        this.l(4);
        float[] v1_1 = ((float[]) this.d);
        int v2_1 = this.b;
        int v3 = (v2_1 + 1);
        this.b = v3;
        v1_1[v2_1] = p5;
        int v5_1 = (v2_1 + 2);
        this.b = v5_1;
        v1_1[v3] = p6;
        int v6_1 = (v2_1 + 3);
        this.b = v6_1;
        v1_1[v5_1] = p7;
        this.b = (v2_1 + 4);
        v1_1[v6_1] = p8;
        return;
    }

    public void e(float p3, float p4, float p5, boolean p6, boolean p7, float p8, float p9)
    {
        int v6_1;
        if (p6 == 0) {
            v6_1 = 0;
        } else {
            v6_1 = 2;
        }
        this.f(((byte) ((v6_1 | 4) | p7)));
        this.l(5);
        float[] v7_1 = ((float[]) this.d);
        int v0_0 = this.b;
        int v1 = (v0_0 + 1);
        this.b = v1;
        v7_1[v0_0] = p3;
        int v3_1 = (v0_0 + 2);
        this.b = v3_1;
        v7_1[v1] = p4;
        int v4_1 = (v0_0 + 3);
        this.b = v4_1;
        v7_1[v3_1] = p5;
        int v3_2 = (v0_0 + 4);
        this.b = v3_2;
        v7_1[v4_1] = p8;
        this.b = (v0_0 + 5);
        v7_1[v3_2] = p9;
        return;
    }

    public void f(byte p5)
    {
        int v1_2 = ((byte[]) this.c);
        if (this.a == v1_2.length) {
            byte[] v0_5 = new byte[(v1_2.length * 2)];
            System.arraycopy(v1_2, 0, v0_5, 0, v1_2.length);
            this.c = v0_5;
        }
        byte[] v0_2 = ((byte[]) this.c);
        int v1_1 = this.a;
        this.a = (v1_1 + 1);
        v0_2[v1_1] = p5;
        return;
    }

    public int g()
    {
        char v0_0 = this.a;
        String v1_0 = this.b;
        if (v0_0 != v1_0) {
            char v0_2 = (v0_0 + 1);
            this.a = v0_2;
            if (v0_2 >= v1_0) {
                return -1;
            } else {
                return ((String) this.c).charAt(v0_2);
            }
        } else {
            return -1;
        }
    }

    public Boolean h(Object p4)
    {
        if (p4 != null) {
            this.x();
            Boolean v4_1 = this.a;
            if (v4_1 != this.b) {
                Boolean v4_4 = ((String) this.c).charAt(v4_1);
                if ((v4_4 == 48) || (v4_4 == 49)) {
                    int v2 = 1;
                    this.a = (this.a + 1);
                    if (v4_4 != 49) {
                        v2 = 0;
                    }
                    return Boolean.valueOf(v2);
                }
            }
        }
        return 0;
    }

    public float i(float p1)
    {
        if (!Float.isNaN(p1)) {
            this.x();
            return this.q();
        } else {
            return 2143289344;
        }
    }

    public boolean j(char p4)
    {
        int v4_1;
        int v0_0 = this.a;
        if ((v0_0 >= this.b) || (((String) this.c).charAt(v0_0) != p4)) {
            v4_1 = 0;
        } else {
            v4_1 = 1;
        }
        if (v4_1 != 0) {
            this.a = (this.a + 1);
        }
        return v4_1;
    }

    public boolean k(String p5)
    {
        int v5_2;
        int v0 = p5.length();
        int v1_3 = this.a;
        if ((v1_3 > (this.b - v0)) || (!((String) this.c).substring(v1_3, (v1_3 + v0)).equals(p5))) {
            v5_2 = 0;
        } else {
            v5_2 = 1;
        }
        if (v5_2 != 0) {
            this.a = (this.a + v0);
        }
        return v5_2;
    }

    public void l(int p4)
    {
        float[] v0_1 = ((float[]) this.d);
        if (v0_1.length < (this.b + p4)) {
            float[] v4_3 = new float[(v0_1.length * 2)];
            System.arraycopy(v0_1, 0, v4_3, 0, v0_1.length);
            this.d = v4_3;
        }
        return;
    }

    public boolean m()
    {
        if (this.a != this.b) {
            return 0;
        } else {
            return 1;
        }
    }

    public void n(vn.zalopay.ui.androidsvg.M p13)
    {
        int v0_0 = 0;
        int v9 = 0;
        while (v9 < this.a) {
            float v1_8 = ((byte[]) this.c)[v9];
            if (v1_8 == 0) {
                float v2_1 = ((float[]) this.d);
                float v3_0 = (v0_0 + 1);
                int v4_0 = v2_1[v0_0];
                v0_0 += 2;
                p13.a(v4_0, v2_1[v3_0]);
            } else {
                if (v1_8 == 1) {
                    float v2_5 = ((float[]) this.d);
                    float v3_1 = (v0_0 + 1);
                    int v4_1 = v2_5[v0_0];
                    v0_0 += 2;
                    p13.b(v4_1, v2_5[v3_1]);
                } else {
                    if (v1_8 == 2) {
                        float v2_8 = ((float[]) this.d);
                        int v10_0 = (v0_0 + 6);
                        p13.c(v2_8[v0_0], v2_8[(v0_0 + 1)], v2_8[(v0_0 + 2)], v2_8[(v0_0 + 3)], v2_8[(v0_0 + 4)], v2_8[(v0_0 + 5)]);
                        v0_0 = v10_0;
                    } else {
                        if (v1_8 == 3) {
                            float v2_11 = ((float[]) this.d);
                            int v4_5 = v2_11[v0_0];
                            float v3_8 = v2_11[(v0_0 + 1)];
                            float v6_3 = (v0_0 + 3);
                            int v5_4 = v2_11[(v0_0 + 2)];
                            v0_0 += 4;
                            p13.d(v4_5, v3_8, v5_4, v2_11[v6_3]);
                        } else {
                            if (v1_8 == 8) {
                                p13.close();
                            } else {
                                int v4_6;
                                if ((v1_8 & 2) == 0) {
                                    v4_6 = 0;
                                } else {
                                    v4_6 = 1;
                                }
                                int v5_5;
                                if ((v1_8 & 1) == 0) {
                                    v5_5 = 0;
                                } else {
                                    v5_5 = 1;
                                }
                                float v3_11 = ((float[]) this.d);
                                int v11 = (v0_0 + 5);
                                p13.e(v3_11[v0_0], v3_11[(v0_0 + 1)], v3_11[(v0_0 + 2)], v4_6, v5_5, v3_11[(v0_0 + 3)], v3_11[(v0_0 + 4)]);
                                v0_0 = v11;
                            }
                        }
                    }
                }
            }
            v9++;
        }
        return;
    }

    public Integer p()
    {
        Integer v0_0 = this.a;
        if (v0_0 != this.b) {
            String v1_2 = ((String) this.c);
            this.a = (v0_0 + 1);
            return Integer.valueOf(v1_2.charAt(v0_0));
        } else {
            return 0;
        }
    }

    public float q()
    {
        int v0_1 = ((vn.zalopay.ui.androidsvg.q) this.d);
        float v1_2 = v0_1.a(this.a, this.b, ((String) this.c));
        if (!Float.isNaN(v1_2)) {
            this.a = v0_1.a;
        }
        return v1_2;
    }

    public vn.zalopay.ui.androidsvg.E r()
    {
        int v0_0 = this.q();
        if (!Float.isNaN(v0_0)) {
            vn.zalopay.ui.androidsvg.E v1_3 = this.v();
            if (v1_3 != null) {
                return new vn.zalopay.ui.androidsvg.E(v0_0, v1_3);
            } else {
                return new vn.zalopay.ui.androidsvg.E(v0_0, 1);
            }
        } else {
            return 0;
        }
    }

    public String s()
    {
        String v0_1 = ((String) this.c);
        if (!this.m()) {
            int v1_2 = this.a;
            int v3_1 = v0_1.charAt(v1_2);
            if ((v3_1 == 39) || (v3_1 == 34)) {
                int v4_1 = this.g();
                while ((v4_1 != -1) && (v4_1 != v3_1)) {
                    v4_1 = this.g();
                }
                if (v4_1 != -1) {
                    int v2_0 = this.a;
                    this.a = (v2_0 + 1);
                    return v0_1.substring((v1_2 + 1), v2_0);
                } else {
                    this.a = v1_2;
                    return 0;
                }
            } else {
                return 0;
            }
        } else {
            return 0;
        }
    }

    public String t()
    {
        return this.u(32, 0);
    }

    public String u(char p5, boolean p6)
    {
        String v0_1 = ((String) this.c);
        if (!this.m()) {
            int v1_3 = v0_1.charAt(this.a);
            if (((p6) || (!vn.zalopay.ui.androidsvg.L.o(v1_3))) && (v1_3 != p5)) {
                int v1_0 = this.a;
                int v2_1 = this.g();
                while ((v2_1 != -1) && ((v2_1 != p5) && ((p6) || (!vn.zalopay.ui.androidsvg.L.o(v2_1))))) {
                    v2_1 = this.g();
                }
                return v0_1.substring(v1_0, this.a);
            }
        }
        return 0;
    }

    public int v()
    {
        int v0_1 = ((String) this.c);
        if (!this.m()) {
            if (v0_1.charAt(this.a) != 37) {
                int v1_0 = this.a;
                if (v1_0 <= (this.b - 2)) {
                    try {
                        int v0_4 = q9.H.w(v0_1.substring(v1_0, (v1_0 + 2)).toLowerCase(java.util.Locale.US));
                        this.a = (this.a + 2);
                        return v0_4;
                    } catch (IllegalArgumentException) {
                        return 0;
                    }
                } else {
                    return 0;
                }
            } else {
                this.a = (this.a + 1);
                return 9;
            }
        } else {
            return 0;
        }
    }

    public float w()
    {
        this.x();
        int v0_2 = ((vn.zalopay.ui.androidsvg.q) this.d);
        float v1_2 = v0_2.a(this.a, this.b, ((String) this.c));
        if (!Float.isNaN(v1_2)) {
            this.a = v0_2.a;
        }
        return v1_2;
    }

    public boolean x()
    {
        this.y();
        int v0_0 = this.a;
        if (v0_0 != this.b) {
            if (((String) this.c).charAt(v0_0) == 44) {
                this.a = (this.a + 1);
                this.y();
                return 1;
            } else {
                return 0;
            }
        } else {
            return 0;
        }
    }

    public void y()
    {
        while(true) {
            int v0_0 = this.a;
            if ((v0_0 >= this.b) || (!vn.zalopay.ui.androidsvg.L.o(((String) this.c).charAt(v0_0)))) {
                break;
            }
            this.a = (this.a + 1);
        }
        return;
    }
}
