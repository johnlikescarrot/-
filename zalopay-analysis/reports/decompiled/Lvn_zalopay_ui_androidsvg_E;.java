package vn.zalopay.ui.androidsvg;
public final class E implements java.lang.Cloneable {
    public final float a;
    public final int b;

    public E(float p1)
    {
        this.a = p1;
        this.b = 1;
        return;
    }

    public E(float p1, int p2)
    {
        this.a = p1;
        this.b = p2;
        return;
    }

    public final float a(vn.zalopay.ui.androidsvg.C0 p7)
    {
        if (this.b != 9) {
            return this.f(p7);
        } else {
            float v7_5 = p7.c;
            float v0_8 = v7_5.g;
            if (v0_8 == 0) {
                v0_8 = v7_5.f;
            }
            float v7_1 = this.a;
            if (v0_8 != 0) {
                float v7_2;
                float v1_1 = v0_8.d;
                float v0_1 = v0_8.e;
                if (v1_1 != v0_1) {
                    v7_2 = (v7_1 * ((float) (Math.sqrt(((double) ((v0_1 * v0_1) + (v1_1 * v1_1)))) / 4609047870845172684)));
                } else {
                    v7_2 = (v7_1 * v1_1);
                }
                return (v7_2 / 1120403456);
            } else {
                return v7_1;
            }
        }
    }

    public final float b(vn.zalopay.ui.androidsvg.C0 p3, float p4)
    {
        if (this.b != 9) {
            return this.f(p3);
        } else {
            return ((this.a * p4) / 1120403456);
        }
    }

    public final float e()
    {
        int v0_2 = androidx.camera.camera2.internal.G.l(this.b);
        float v3_2 = this.a;
        if (v0_2 == 3) {
            return (v3_2 * 1119879168);
        } else {
            float v3_0;
            int v0_1;
            if (v0_2 == 4) {
                v3_0 = (v3_2 * 1119879168);
                v0_1 = 1076006748;
            } else {
                if (v0_2 == 5) {
                    v3_0 = (v3_2 * 1119879168);
                    v0_1 = 1103835955;
                } else {
                    if (v0_2 == 6) {
                        v3_0 = (v3_2 * 1119879168);
                        v0_1 = 1116733440;
                    } else {
                        if (v0_2 == 7) {
                            v3_0 = (v3_2 * 1119879168);
                            v0_1 = 1086324736;
                        } else {
                            return v3_2;
                        }
                    }
                }
            }
            return (v3_0 / v0_1);
        }
    }

    public final float f(vn.zalopay.ui.androidsvg.C0 p4)
    {
        int v4_10;
        float v2_11 = this.a;
        switch (androidx.camera.camera2.internal.G.l(this.b)) {
            case 1:
                v4_10 = p4.c.d.getTextSize();
                return (v4_10 * v2_11);
            case 2:
                v4_10 = (p4.c.d.getTextSize() / 1073741824);
                return (v4_10 * v2_11);
            case 3:
                p4.getClass();
                return (v2_11 * 1119879168);
            case 4:
                p4.getClass();
                return ((v2_11 * 1119879168) / 1076006748);
            case 5:
                p4.getClass();
                return ((v2_11 * 1119879168) / 1103835955);
            case 6:
                p4.getClass();
                return ((v2_11 * 1119879168) / 1116733440);
            case 7:
                p4.getClass();
                return ((v2_11 * 1119879168) / 1086324736);
            case 8:
                int v4_14 = p4.c;
                J0.b v0_3 = v4_14.g;
                if (v0_3 == null) {
                    v0_3 = v4_14.f;
                }
                if (v0_3 != null) {
                    return ((v2_11 * v0_3.d) / 1120403456);
                } else {
                }
            default:
        }
        return v2_11;
    }

    public final float g(vn.zalopay.ui.androidsvg.C0 p3)
    {
        if (this.b != 9) {
            return this.f(p3);
        } else {
            float v3_5 = p3.c;
            int v0_3 = v3_5.g;
            if (v0_3 == 0) {
                v0_3 = v3_5.f;
            }
            float v3_1 = this.a;
            if (v0_3 != 0) {
                return ((v3_1 * v0_3.e) / 1120403456);
            } else {
                return v3_1;
            }
        }
    }

    public final boolean i()
    {
        if (this.a >= 0) {
            return 0;
        } else {
            return 1;
        }
    }

    public final boolean j()
    {
        if (this.a != 0) {
            return 0;
        } else {
            return 1;
        }
    }

    public final String toString()
    {
        String v1_0;
        String v0_1 = new StringBuilder();
        v0_1.append(String.valueOf(this.a));
        switch (this.b) {
            case 1:
                v1_0 = "px";
                break;
            case 2:
                v1_0 = "em";
                break;
            case 3:
                v1_0 = "ex";
                break;
            case 4:
                v1_0 = "in";
                break;
            case 5:
                v1_0 = "cm";
                break;
            case 6:
                v1_0 = "mm";
                break;
            case 7:
                v1_0 = "pt";
                break;
            case 8:
                v1_0 = "pc";
                break;
            case 9:
                v1_0 = "percent";
                break;
            default:
                v1_0 = "null";
        }
        v0_1.append(v1_0);
        return v0_1.toString();
    }
}
