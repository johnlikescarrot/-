package vn.zalopay.ui.androidsvg;
public final class C0 {
    public static java.util.HashSet g;
    public final android.graphics.Canvas a;
    public vn.zalopay.ui.androidsvg.t0 b;
    public vn.zalopay.ui.androidsvg.A0 c;
    public java.util.Stack d;
    public java.util.Stack e;
    public java.util.Stack f;

    public C0(android.graphics.Canvas p1)
    {
        this.a = p1;
        return;
    }

    public static void M(vn.zalopay.ui.androidsvg.A0 p2, boolean p3, com.caverock.androidsvg.d0 p4)
    {
        float v0_1;
        float v0_0 = p2.a;
        if (!p3) {
            v0_1 = v0_0.e;
        } else {
            v0_1 = v0_0.c;
        }
        int v4_3;
        float v0_2 = v0_1.floatValue();
        if (!(p4 instanceof vn.zalopay.ui.androidsvg.v)) {
            if ((p4 instanceof vn.zalopay.ui.androidsvg.w)) {
                v4_3 = p2.a.k.a;
                int v4_5 = vn.zalopay.ui.androidsvg.C0.i(v0_2, v4_3);
                if (!p3) {
                    p2.e.setColor(v4_5);
                } else {
                    p2.d.setColor(v4_5);
                    return;
                }
            }
        } else {
            v4_3 = ((vn.zalopay.ui.androidsvg.v) p4).a;
        }
        return;
    }

    public static void a(float p27, float p28, float p29, float p30, float p31, boolean p32, boolean p33, float p34, float p35, vn.zalopay.ui.androidsvg.M p36)
    {
        if ((p27 != p34) || (p28 != p35)) {
            if ((p29 != 0) && (p30 != 0)) {
                float v4_7;
                int v19_3;
                float v5_1 = Math.abs(p29);
                float v6 = Math.abs(p30);
                float v7_17 = ((double) ((float) Math.toRadians((((double) p31) % 4645040803167600640))));
                float v11_4 = ((float) Math.cos(v7_17));
                float v7_25 = ((float) Math.sin(v7_17));
                double v8_14 = ((p27 - p34) / 1073741824);
                float v13_5 = ((p28 - p35) / 1073741824);
                int v15_5 = ((v7_25 * v13_5) + (v11_4 * v8_14));
                float v13_7 = ((v13_5 * v11_4) + ((- v7_25) * v8_14));
                double v8_15 = (v5_1 * v5_1);
                float[] v14_17 = (v6 * v6);
                double v16_4 = (v15_5 * v15_5);
                float v17 = (v13_7 * v13_7);
                float v4_5 = ((v17 / v14_17) + (v16_4 / v8_15));
                if (v4_5 <= 1065353216) {
                    v4_7 = 1073741824;
                    v19_3 = v13_7;
                } else {
                    double v9_15 = ((double) v4_5);
                    v4_7 = 1073741824;
                    v19_3 = v13_7;
                    v5_1 *= ((float) Math.sqrt(v9_15));
                    v6 *= ((float) Math.sqrt(v9_15));
                    v8_15 = (v5_1 * v5_1);
                    v14_17 = (v6 * v6);
                }
                int v10_6;
                if (p32 != p33) {
                    v10_6 = 1065353216;
                } else {
                    v10_6 = -1082130432;
                }
                double v8_19 = (v8_15 * v17);
                float[] v14_18 = (v14_17 * v16_4);
                double v12_15 = ((((v8_15 * v14_17) - v8_19) - v14_18) / (v8_19 + v14_18));
                if (v12_15 < 0) {
                    v12_15 = 0;
                }
                double v9_2;
                double v9_19 = ((float) (Math.sqrt(((double) v12_15)) * ((double) v10_6)));
                float v13_11 = (((v5_1 * v19_3) / v6) * v9_19);
                double v9_20 = (v9_19 * (- ((v6 * v15_5) / v5_1)));
                float v4_1 = (((v11_4 * v13_11) - (v7_25 * v9_20)) + ((p27 + p34) / v4_7));
                float v11_2 = (((v11_4 * v9_20) + (v7_25 * v13_11)) + ((p28 + p35) / v4_7));
                float v7_2 = ((v15_5 - v13_11) / v5_1);
                int v10_1 = ((v19_3 - v9_20) / v6);
                double v12_2 = (((- v15_5) - v13_11) / v5_1);
                float v13_3 = (((- v19_3) - v9_20) / v6);
                float[] v14_2 = ((v10_1 * v10_1) + (v7_2 * v7_2));
                double v8_1 = Math.sqrt(((double) v14_2));
                if (v10_1 >= 0) {
                    v9_2 = 1065353216;
                } else {
                    v9_2 = -1082130432;
                }
                double v9_11;
                vn.zalopay.ui.androidsvg.M v27_1 = v7_2;
                float v7_8 = ((float) Math.toDegrees((Math.acos(((double) (v27_1 / ((float) v8_1)))) * ((double) v9_2))));
                double vtmp12 = Math.sqrt(((double) (((v13_3 * v13_3) + (v12_2 * v12_2)) * v14_2)));
                if (((v27_1 * v13_3) - (v10_1 * v12_2)) >= 0) {
                    v9_11 = 1065353216;
                } else {
                    v9_11 = -1082130432;
                }
                double v8_7 = Math.toDegrees((Math.acos(((double) (((v10_1 * v13_3) + (v27_1 * v12_2)) / ((float) vtmp12)))) * ((double) v9_11)));
                if ((p33 != 0) || (v8_7 <= 0)) {
                    if ((p33 != 0) && (v8_7 < 0)) {
                        v8_7 += 4645040803167600640;
                    }
                } else {
                    v8_7 -= 4645040803167600640;
                }
                double v8_8 = (v8_7 % 4645040803167600640);
                float v1_3 = ((int) Math.ceil((Math.abs(v8_8) / 4636033603912859648)));
                double v12_8 = Math.toRadians(((double) (v7_8 % 1135869952)));
                float v7_14 = ((float) (Math.toRadians(v8_8) / ((double) v1_3)));
                double v8_9 = ((double) v7_14);
                float[] v14_10 = (v8_9 / 4611686018427387904);
                double v16_3 = ((Math.sin(v14_10) * 4608683618675807573) / (Math.cos(v14_10) + 4607182418800017408));
                int v10_4 = (v1_3 * 6);
                float[] v14_13 = new float[v10_4];
                int v15_2 = 0;
                int v18_2 = 0;
                while (v15_2 < v1_3) {
                    int v19_0 = v1_3;
                    float v20_0 = v7_14;
                    float v28_2 = v8_9;
                    float v7_19 = (((double) (((float) v15_2) * v7_14)) + v12_8);
                    double v21_0 = Math.cos(v7_19);
                    double v23_0 = Math.sin(v7_19);
                    float v1_10 = (v18_2 + 1);
                    float v32_2 = v7_19;
                    v14_13[v18_2] = ((float) (v21_0 - (v16_3 * v23_0)));
                    float v7_22 = (v18_2 + 2);
                    v14_13[v1_10] = ((float) ((v21_0 * v16_3) + v23_0));
                    float v30_2 = v7_22;
                    float v32_3 = (v32_2 + v28_2);
                    float v7_23 = Math.cos(v32_3);
                    double v21_2 = v12_8;
                    double v12_9 = Math.sin(v32_3);
                    float v32_4 = (v18_2 + 3);
                    v14_13[v30_2] = ((float) ((v16_3 * v12_9) + v7_23));
                    float v30_3 = (v18_2 + 4);
                    v14_13[v32_4] = ((float) (v12_9 - (v16_3 * v7_23)));
                    float v1_17 = (v18_2 + 5);
                    v14_13[v30_3] = ((float) v7_23);
                    v18_2 += 6;
                    v14_13[v1_17] = ((float) v12_9);
                    v15_2++;
                    v8_9 = v28_2;
                    v1_3 = v19_0;
                    v7_14 = v20_0;
                    v12_8 = v21_2;
                }
                float v1_5 = new android.graphics.Matrix();
                v1_5.postScale(v5_1, v6);
                v1_5.postRotate(p31);
                v1_5.postTranslate(v4_1, v11_2);
                v1_5.mapPoints(v14_13);
                v14_13[(v10_4 - 2)] = p34;
                v14_13[(v10_4 - 1)] = p35;
                int v15_3 = 0;
                while (v15_3 < v10_4) {
                    p36.c(v14_13[v15_3], v14_13[(v15_3 + 1)], v14_13[(v15_3 + 2)], v14_13[(v15_3 + 3)], v14_13[(v15_3 + 4)], v14_13[(v15_3 + 5)]);
                    v15_3 += 6;
                }
            } else {
                p36.b(p34, p35);
                return;
            }
        }
        return;
    }

    public static J0.b c(android.graphics.Path p8)
    {
        android.graphics.RectF v0_1 = new android.graphics.RectF();
        p8.computeBounds(v0_1, 1);
        return new J0.b(v0_1.left, v0_1.top, v0_1.width(), v0_1.height(), 2);
    }

    public static android.graphics.Matrix e(J0.b p9, J0.b p10, vn.zalopay.ui.androidsvg.s p11)
    {
        android.graphics.Matrix v0_1 = new android.graphics.Matrix();
        if (p11 != 0) {
            int v1_0 = p11.a;
            if (v1_0 != 0) {
                float v2_3 = (p9.d / p10.d);
                float v3_0 = (p9.e / p10.e);
                float v4_1 = (- p10.b);
                float v5_1 = (- p10.c);
                if (!p11.equals(vn.zalopay.ui.androidsvg.s.c)) {
                    float v11_2;
                    if (p11.b != 2) {
                        v11_2 = Math.min(v2_3, v3_0);
                    } else {
                        v11_2 = Math.max(v2_3, v3_0);
                    }
                    int v6_9;
                    float v2_1 = (p9.d / v11_2);
                    float v3_2 = (p9.e / v11_2);
                    int v7 = v1_0.ordinal();
                    if (v7 == 2) {
                        v6_9 = ((p10.d - v2_1) / 1073741824);
                        v4_1 -= v6_9;
                    } else {
                        if (v7 != 3) {
                            if (v7 == 5) {
                            } else {
                                if (v7 != 6) {
                                    if (v7 == 8) {
                                    } else {
                                        if (v7 != 9) {
                                            float v10_2;
                                            switch (v1_0.ordinal()) {
                                                case 4:
                                                case 5:
                                                case 6:
                                                    v10_2 = ((p10.e - v3_2) / 1073741824);
                                                    v5_1 -= v10_2;
                                                    break;
                                                case 7:
                                                case 8:
                                                case 9:
                                                    v10_2 = (p10.e - v3_2);
                                                    break;
                                                default:
                                            }
                                            v0_1.preTranslate(p9.b, p9.c);
                                            v0_1.preScale(v11_2, v11_2);
                                            v0_1.preTranslate(v4_1, v5_1);
                                            return v0_1;
                                        }
                                    }
                                }
                            }
                        }
                        v6_9 = (p10.d - v2_1);
                    }
                } else {
                    v0_1.preTranslate(p9.b, p9.c);
                    v0_1.preScale(v2_3, v3_0);
                    v0_1.preTranslate(v4_1, v5_1);
                    return v0_1;
                }
            }
        }
        return v0_1;
    }

    public static android.graphics.Typeface h(String p5, int p6, Integer p7)
    {
        int v6_2;
        int v0 = 0;
        if (p6 != 2) {
            v6_2 = 0;
        } else {
            v6_2 = 1;
        }
        int v6_1;
        if (p7.intValue() <= 500) {
            if (v6_2 == 0) {
                v6_1 = 0;
            } else {
                v6_1 = 2;
            }
        } else {
            if (v6_2 == 0) {
                v6_1 = 1;
            } else {
                v6_1 = 3;
            }
        }
        p5.getClass();
        switch (p5.hashCode()) {
            case -1536685117:
                if (p5.equals("sans-serif")) {
                } else {
                    v0 = -1;
                }
                break;
            case -1431958525:
                if (p5.equals("monospace")) {
                    v0 = 1;
                } else {
                }
                break;
            case -1081737434:
                if (p5.equals("fantasy")) {
                    v0 = 2;
                } else {
                }
                break;
            case 109326717:
                if (p5.equals("serif")) {
                    v0 = 3;
                } else {
                }
                break;
            case 1126973893:
                if (p5.equals("cursive")) {
                    v0 = 4;
                } else {
                }
                break;
            default:
        }
        switch (v0) {
            case 0:
            case 2:
            case 4:
                return android.graphics.Typeface.create(android.graphics.Typeface.SANS_SERIF, v6_1);
            case 1:
                return android.graphics.Typeface.create(android.graphics.Typeface.MONOSPACE, v6_1);
            case 3:
                return android.graphics.Typeface.create(android.graphics.Typeface.SERIF, v6_1);
            default:
                return 0;
        }
    }

    public static int i(float p2, int p3)
    {
        int v2_1;
        int v2_4 = Math.round((((float) ((p3 >> 24) & 255)) * p2));
        if (v2_4 >= 0) {
            v2_1 = Math.min(v2_4, 255);
        } else {
            v2_1 = 0;
        }
        return ((v2_1 << 24) | (p3 & 16777215));
    }

    public static varargs void o(String p0, Object[] p1)
    {
        ck.b.e("SVGAndroidRenderer", new Object[] {String.format(p0, p1)}));
        return;
    }

    public static void q(vn.zalopay.ui.androidsvg.z p3, String p4)
    {
        vn.zalopay.ui.androidsvg.E v0_1 = p3.a.c(p4);
        if (v0_1 != null) {
            if ((v0_1 instanceof vn.zalopay.ui.androidsvg.z)) {
                if (v0_1 != p3) {
                    String v4_6 = ((vn.zalopay.ui.androidsvg.z) v0_1);
                    if (p3.i == null) {
                        p3.i = v4_6.i;
                    }
                    if (p3.j == null) {
                        p3.j = v4_6.j;
                    }
                    if (p3.k == 0) {
                        p3.k = v4_6.k;
                    }
                    if (p3.h.isEmpty()) {
                        p3.h = v4_6.h;
                    }
                    try {
                        if (!(p3 instanceof vn.zalopay.ui.androidsvg.b0)) {
                            vn.zalopay.ui.androidsvg.C0.r(((vn.zalopay.ui.androidsvg.e0) p3), ((vn.zalopay.ui.androidsvg.e0) v0_1));
                        } else {
                            vn.zalopay.ui.androidsvg.E v0_3 = ((vn.zalopay.ui.androidsvg.b0) v0_1);
                            if (((vn.zalopay.ui.androidsvg.b0) p3).m == null) {
                                ((vn.zalopay.ui.androidsvg.b0) p3).m = v0_3.m;
                            }
                            if (((vn.zalopay.ui.androidsvg.b0) p3).n == null) {
                                ((vn.zalopay.ui.androidsvg.b0) p3).n = v0_3.n;
                            }
                            if (((vn.zalopay.ui.androidsvg.b0) p3).o == null) {
                                ((vn.zalopay.ui.androidsvg.b0) p3).o = v0_3.o;
                            }
                            if (((vn.zalopay.ui.androidsvg.b0) p3).p == null) {
                                ((vn.zalopay.ui.androidsvg.b0) p3).p = v0_3.p;
                            }
                        }
                    } catch (ClassCastException) {
                    }
                    String v4_1 = v4_6.l;
                    if (v4_1 != null) {
                        vn.zalopay.ui.androidsvg.C0.q(p3, v4_1);
                    }
                    return;
                } else {
                    vn.zalopay.ui.androidsvg.C0.o("Circular reference in gradient href attribute \'%s\'", new Object[] {p4}));
                    return;
                }
            } else {
                String v3_3 = new Object[0];
                vn.zalopay.ui.androidsvg.C0.o("Gradient href attributes must point to other gradient elements", v3_3);
                return;
            }
        } else {
            ck.b.e("SVGAndroidRenderer", new Object[] {A1.a.i("Gradient reference \'", p4, "\' not found")}));
            return;
        }
    }

    public static void r(vn.zalopay.ui.androidsvg.e0 p1, vn.zalopay.ui.androidsvg.e0 p2)
    {
        if (p1.m == null) {
            p1.m = p2.m;
        }
        if (p1.n == null) {
            p1.n = p2.n;
        }
        if (p1.o == null) {
            p1.o = p2.o;
        }
        if (p1.p == null) {
            p1.p = p2.p;
        }
        if (p1.q == null) {
            p1.q = p2.q;
        }
        return;
    }

    public static void s(vn.zalopay.ui.androidsvg.N p2, String p3)
    {
        vn.zalopay.ui.androidsvg.N v0_1 = p2.a.c(p3);
        if (v0_1 != null) {
            if ((v0_1 instanceof vn.zalopay.ui.androidsvg.N)) {
                if (v0_1 != p2) {
                    vn.zalopay.ui.androidsvg.N v0_3 = ((vn.zalopay.ui.androidsvg.N) v0_1);
                    if (p2.p == null) {
                        p2.p = v0_3.p;
                    }
                    if (p2.q == null) {
                        p2.q = v0_3.q;
                    }
                    if (p2.r == null) {
                        p2.r = v0_3.r;
                    }
                    if (p2.s == null) {
                        p2.s = v0_3.s;
                    }
                    if (p2.t == null) {
                        p2.t = v0_3.t;
                    }
                    if (p2.u == null) {
                        p2.u = v0_3.u;
                    }
                    if (p2.v == null) {
                        p2.v = v0_3.v;
                    }
                    if (p2.i.isEmpty()) {
                        p2.i = v0_3.i;
                    }
                    if (p2.o == null) {
                        p2.o = v0_3.o;
                    }
                    if (p2.n == null) {
                        p2.n = v0_3.n;
                    }
                    String v3_21 = v0_3.w;
                    if (v3_21 != null) {
                        vn.zalopay.ui.androidsvg.C0.s(p2, v3_21);
                    }
                    return;
                } else {
                    vn.zalopay.ui.androidsvg.C0.o("Circular reference in pattern href attribute \'%s\'", new Object[] {p3}));
                    return;
                }
            } else {
                String v2_3 = new Object[0];
                vn.zalopay.ui.androidsvg.C0.o("Pattern href attributes must point to other pattern elements", v2_3);
                return;
            }
        } else {
            ck.b.e("SVGAndroidRenderer", new Object[] {A1.a.i("Pattern reference \'", p3, "\' not found")}));
            return;
        }
    }

    public static boolean w(vn.zalopay.ui.androidsvg.U p2, long p3)
    {
        if ((p2.a & p3) == 0) {
            return 0;
        } else {
            return 1;
        }
    }

    public static android.graphics.Path z(vn.zalopay.ui.androidsvg.O p5)
    {
        android.graphics.Path v0_1 = new android.graphics.Path();
        J0.b v1_3 = p5.o;
        v0_1.moveTo(v1_3[0], v1_3[1]);
        J0.b v1_4 = 2;
        while(true) {
            float v2_3 = p5.o;
            if (v1_4 >= v2_3.length) {
                break;
            }
            v0_1.lineTo(v2_3[v1_4], v2_3[(v1_4 + 1)]);
            v1_4 += 2;
        }
        if ((p5 instanceof vn.zalopay.ui.androidsvg.P)) {
            v0_1.close();
        }
        if (p5.h == null) {
            p5.h = vn.zalopay.ui.androidsvg.C0.c(v0_1);
        }
        return v0_1;
    }

    public final android.graphics.Path A(vn.zalopay.ui.androidsvg.Q p20)
    {
        float v2_0;
        float v4_1;
        float v2_2 = p20.s;
        if ((v2_2 != 0) || (p20.t != null)) {
            if (v2_2 != 0) {
                if (p20.t != null) {
                    v2_0 = v2_2.f(this);
                    v4_1 = p20.t.g(this);
                } else {
                    v2_0 = v2_2.f(this);
                    v4_1 = v2_0;
                }
            } else {
                v2_0 = p20.t.g(this);
            }
        } else {
            v2_0 = 0;
        }
        int v7_2;
        float v2_3 = Math.min(v2_0, (p20.q.f(this) / 1073741824));
        float v4_2 = Math.min(v4_1, (p20.r.g(this) / 1073741824));
        float v5_6 = p20.o;
        if (v5_6 == 0) {
            v7_2 = 0;
        } else {
            v7_2 = v5_6.f(this);
        }
        float v10_0;
        float v5_8 = p20.p;
        if (v5_8 == 0) {
            v10_0 = 0;
        } else {
            v10_0 = v5_8.g(this);
        }
        int v9_1 = p20.q.f(this);
        float v5_12 = p20.r.g(this);
        if (p20.h == null) {
            float v8_1 = v10_0;
            v10_0 = v8_1;
            p20.h = new J0.b(v7_2, v8_1, v9_1, v5_12, 2);
        }
        float v1_1 = (v7_2 + v9_1);
        float v15 = (v10_0 + v5_12);
        android.graphics.Path v6_5 = new android.graphics.Path();
        if ((v2_3 != 0) && (v4_2 != 0)) {
            float v5_14 = (v2_3 * 1057841801);
            float v3_3 = (1057841801 * v4_2);
            float v14_0 = (v10_0 + v4_2);
            v6_5.moveTo(v7_2, v14_0);
            float v8_2 = (v14_0 - v3_3);
            int v11_4 = (v7_2 + v2_3);
            int v9_2 = (v11_4 - v5_14);
            v6_5.cubicTo(v7_2, v8_2, v9_2, v10_0, v11_4, v10_0);
            float v18 = v9_2;
            float v2_4 = (v1_1 - v2_3);
            v6_5.lineTo(v2_4, v10_0);
            int v9_3 = (v2_4 + v5_14);
            int v11_5 = v1_1;
            float v1_2 = v11_4;
            v6_5.cubicTo(v9_3, v10_0, v11_5, v8_2, v1_1, v14_0);
            float v5_15 = v14_0;
            float v14_1 = v9_3;
            float v4_5 = (v15 - v4_2);
            v6_5.lineTo(v11_5, v4_5);
            float v10_2 = (v4_5 + v3_3);
            v6_5.cubicTo(v11_5, v10_2, v14_1, v15, v2_4, v15);
            v6_5.lineTo(v1_2, v15);
            int v9_0 = v7_2;
            v6_5.cubicTo(v18, v15, v9_0, v10_2, v7_2, v4_5);
            v6_5.lineTo(v9_0, v5_15);
        } else {
            int v11_2 = v1_1;
            v6_5.moveTo(v7_2, v10_0);
            v6_5.lineTo(v11_2, v10_0);
            v6_5.lineTo(v11_2, v15);
            v6_5.lineTo(v7_2, v15);
            v6_5.lineTo(v7_2, v10_0);
        }
        v6_5.close();
        return v6_5;
    }

    public final J0.b B(vn.zalopay.ui.androidsvg.E p8, vn.zalopay.ui.androidsvg.E p9, vn.zalopay.ui.androidsvg.E p10, vn.zalopay.ui.androidsvg.E p11)
    {
        int v2;
        float v0 = 0;
        if (p8 == 0) {
            v2 = 0;
        } else {
            v2 = p8.f(this);
        }
        if (p9 != null) {
            v0 = p9.g(this);
        }
        float v3 = v0;
        float v8_1 = this.c;
        J0.b v9_1 = v8_1.g;
        if (v9_1 == null) {
            v9_1 = v8_1.f;
        }
        float v8_2;
        if (p10 == null) {
            v8_2 = v9_1.d;
        } else {
            v8_2 = p10.f(this);
        }
        float v4 = v8_2;
        float v8_3;
        if (p11 == null) {
            v8_3 = v9_1.e;
        } else {
            v8_3 = p11.g(this);
        }
        return new J0.b(v2, v3, v4, v8_3, 2);
    }

    public final android.graphics.Path C(vn.zalopay.ui.androidsvg.Z p14, boolean p15)
    {
        this.d.push(this.c);
        int v0_10 = new vn.zalopay.ui.androidsvg.A0(this.c);
        this.c = v0_10;
        this.S(v0_10, p14);
        if ((this.k()) && (this.U())) {
            int v0_3;
            if (!(p14 instanceof vn.zalopay.ui.androidsvg.r0)) {
                android.graphics.Path v2_1;
                if (!(p14 instanceof vn.zalopay.ui.androidsvg.A)) {
                    if (!(p14 instanceof vn.zalopay.ui.androidsvg.k0)) {
                        vn.zalopay.ui.androidsvg.C0.o("Invalid %s element found in clipPath definition", new Object[] {p14.getClass().getSimpleName()}));
                        return 0;
                    } else {
                        float v1_13;
                        float v1_7 = ((vn.zalopay.ui.androidsvg.k0) p14).n;
                        float v3_5 = 0;
                        if ((v1_7 != 0) && (v1_7.size() != 0)) {
                            v1_13 = ((vn.zalopay.ui.androidsvg.E) ((vn.zalopay.ui.androidsvg.k0) p14).n.get(0)).f(this);
                        } else {
                            v1_13 = 0;
                        }
                        float v4_6;
                        float v4_1 = ((vn.zalopay.ui.androidsvg.k0) p14).o;
                        if ((v4_1 != 0) && (v4_1.size() != 0)) {
                            v4_6 = ((vn.zalopay.ui.androidsvg.E) ((vn.zalopay.ui.androidsvg.k0) p14).o.get(0)).g(this);
                        } else {
                            v4_6 = 0;
                        }
                        float v5_5;
                        float v5_0 = ((vn.zalopay.ui.androidsvg.k0) p14).p;
                        if ((v5_0 != 0) && (v5_0.size() != 0)) {
                            v5_5 = ((vn.zalopay.ui.androidsvg.E) ((vn.zalopay.ui.androidsvg.k0) p14).p.get(0)).f(this);
                        } else {
                            v5_5 = 0;
                        }
                        vn.zalopay.ui.androidsvg.z0 v6_4 = ((vn.zalopay.ui.androidsvg.k0) p14).q;
                        if ((v6_4 != null) && (v6_4.size() != 0)) {
                            v3_5 = ((vn.zalopay.ui.androidsvg.E) ((vn.zalopay.ui.androidsvg.k0) p14).q.get(0)).g(this);
                        }
                        if (this.c.a.J != 1) {
                            android.graphics.Path v2_26 = this.d(((vn.zalopay.ui.androidsvg.k0) p14));
                            if (this.c.a.J == 2) {
                                v2_26 /= 1073741824;
                            }
                            v1_13 -= v2_26;
                        }
                        if (((vn.zalopay.ui.androidsvg.k0) p14).h == null) {
                            android.graphics.Path v2_29 = new vn.zalopay.ui.androidsvg.z0(this, v1_13, v4_6);
                            vn.zalopay.ui.androidsvg.z0 v6_1 = ((android.graphics.RectF) v2_29.e);
                            this.n(((vn.zalopay.ui.androidsvg.k0) p14), v2_29);
                            ((vn.zalopay.ui.androidsvg.k0) p14).h = new J0.b(v6_1.left, v6_1.top, v6_1.width(), v6_1.height(), 2);
                        }
                        v2_1 = new android.graphics.Path();
                        this.n(((vn.zalopay.ui.androidsvg.k0) p14), new vn.zalopay.ui.androidsvg.z0(this, (v1_13 + v5_5), (v4_6 + v3_5), v2_1));
                        android.graphics.Path$FillType v15_1 = ((vn.zalopay.ui.androidsvg.k0) p14).r;
                        if (v15_1 != null) {
                            v2_1.transform(v15_1);
                        }
                        android.graphics.Path$FillType v15_5;
                        android.graphics.Path$FillType v15_4 = this.c.a.K;
                        if ((v15_4 == null) || (v15_4 != 2)) {
                            v15_5 = android.graphics.Path$FillType.WINDING;
                        } else {
                            v15_5 = android.graphics.Path$FillType.EVEN_ODD;
                        }
                        v2_1.setFillType(v15_5);
                    }
                } else {
                    if (!(p14 instanceof vn.zalopay.ui.androidsvg.K)) {
                        if (!(p14 instanceof vn.zalopay.ui.androidsvg.Q)) {
                            if (!(p14 instanceof vn.zalopay.ui.androidsvg.t)) {
                                if (!(p14 instanceof vn.zalopay.ui.androidsvg.y)) {
                                    if (!(p14 instanceof vn.zalopay.ui.androidsvg.O)) {
                                        v2_1 = 0;
                                    } else {
                                        v2_1 = vn.zalopay.ui.androidsvg.C0.z(((vn.zalopay.ui.androidsvg.O) p14));
                                    }
                                } else {
                                    v2_1 = this.y(((vn.zalopay.ui.androidsvg.y) p14));
                                }
                            } else {
                                v2_1 = this.x(((vn.zalopay.ui.androidsvg.t) p14));
                            }
                        } else {
                            v2_1 = this.A(((vn.zalopay.ui.androidsvg.Q) p14));
                        }
                    } else {
                        float v3_2 = new vn.zalopay.ui.androidsvg.w0(((vn.zalopay.ui.androidsvg.K) p14).o).a;
                        if (p14.h == null) {
                            p14.h = vn.zalopay.ui.androidsvg.C0.c(v3_2);
                        }
                        v2_1 = v3_2;
                    }
                    if (v2_1 != null) {
                        if (((vn.zalopay.ui.androidsvg.A) p14).h == null) {
                            ((vn.zalopay.ui.androidsvg.A) p14).h = vn.zalopay.ui.androidsvg.C0.c(v2_1);
                        }
                        android.graphics.Path$FillType v15_8 = ((vn.zalopay.ui.androidsvg.A) p14).n;
                        if (v15_8 != null) {
                            v2_1.transform(v15_8);
                        }
                        android.graphics.Path$FillType v15_13;
                        android.graphics.Path$FillType v15_12 = this.c.a.K;
                        if ((v15_12 == null) || (v15_12 != 2)) {
                            v15_13 = android.graphics.Path$FillType.WINDING;
                        } else {
                            v15_13 = android.graphics.Path$FillType.EVEN_ODD;
                        }
                        v2_1.setFillType(v15_13);
                    } else {
                        return 0;
                    }
                }
                v0_3 = v2_1;
            } else {
                if (p15 == null) {
                    int v0_5 = new Object[0];
                    vn.zalopay.ui.androidsvg.C0.o("<use> elements inside a <clipPath> cannot reference another <use>", v0_5);
                }
                int v0_7 = p14.a.c(((vn.zalopay.ui.androidsvg.r0) p14).o);
                if (v0_7 != 0) {
                    if ((v0_7 instanceof vn.zalopay.ui.androidsvg.Z)) {
                        v0_3 = this.C(((vn.zalopay.ui.androidsvg.Z) v0_7), 0);
                        if (v0_3 != 0) {
                            if (((vn.zalopay.ui.androidsvg.r0) p14).h == null) {
                                ((vn.zalopay.ui.androidsvg.r0) p14).h = vn.zalopay.ui.androidsvg.C0.c(v0_3);
                            }
                            android.graphics.Path$FillType v15_18 = ((vn.zalopay.ui.androidsvg.r0) p14).n;
                            if (v15_18 != null) {
                                v0_3.transform(v15_18);
                            }
                        }
                    } else {
                        this.c = ((vn.zalopay.ui.androidsvg.A0) this.d.pop());
                        return 0;
                    }
                } else {
                    vn.zalopay.ui.androidsvg.C0.o("Use reference \'%s\' not found", new Object[] {((vn.zalopay.ui.androidsvg.r0) p14).o}));
                    this.c = ((vn.zalopay.ui.androidsvg.A0) this.d.pop());
                    return 0;
                }
            }
            if (this.c.a.x != null) {
                vn.zalopay.ui.androidsvg.A0 v14_1 = this.b(p14, p14.h);
                if (v14_1 != null) {
                    v0_3.op(v14_1, android.graphics.Path$Op.INTERSECT);
                }
            }
            this.c = ((vn.zalopay.ui.androidsvg.A0) this.d.pop());
            return v0_3;
        } else {
            this.c = ((vn.zalopay.ui.androidsvg.A0) this.d.pop());
            return 0;
        }
    }

    public final void D(vn.zalopay.ui.androidsvg.Z p8)
    {
        if (this.c.a.y != null) {
            vn.zalopay.ui.androidsvg.H v0_9 = new android.graphics.Paint();
            android.graphics.PorterDuff$Mode v2 = android.graphics.PorterDuff$Mode.DST_IN;
            v0_9.setXfermode(new android.graphics.PorterDuffXfermode(v2));
            android.graphics.Canvas v1_0 = this.a;
            v1_0.saveLayer(0, v0_9, 31);
            vn.zalopay.ui.androidsvg.H v0_3 = new android.graphics.Paint();
            android.graphics.PorterDuffXfermode v6_1 = new float[20];
            v6_1 = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1046072839, 1060573387, 1033100696, 0, 0};
            v0_3.setColorFilter(new android.graphics.ColorMatrixColorFilter(new android.graphics.ColorMatrix(v6_1)));
            v1_0.saveLayer(0, v0_3, 31);
            vn.zalopay.ui.androidsvg.H v0_7 = ((vn.zalopay.ui.androidsvg.H) this.b.c(this.c.a.y));
            this.K(v0_7, p8);
            v1_0.restore();
            android.graphics.Paint v5_6 = new android.graphics.Paint();
            v5_6.setXfermode(new android.graphics.PorterDuffXfermode(v2));
            v1_0.saveLayer(0, v5_6, 31);
            this.K(v0_7, p8);
            v1_0.restore();
            v1_0.restore();
        }
        this.N();
        return;
    }

    public final boolean E()
    {
        int v1_1 = 0;
        if ((this.c.a.j.floatValue() >= 1065353216) && (this.c.a.y == null)) {
            return 0;
        } else {
            vn.zalopay.ui.androidsvg.U v0_10 = ((int) (this.c.a.j.floatValue() * 1132462080));
            if (v0_10 >= null) {
                v1_1 = Math.min(v0_10, 255);
            }
            this.a.saveLayerAlpha(0, v1_1, 31);
            this.d.push(this.c);
            vn.zalopay.ui.androidsvg.U v0_14 = new vn.zalopay.ui.androidsvg.A0(this.c);
            this.c = v0_14;
            vn.zalopay.ui.androidsvg.U v0_16 = v0_14.a.y;
            if ((v0_16 != null) && (!(this.b.c(v0_16) instanceof vn.zalopay.ui.androidsvg.H))) {
                vn.zalopay.ui.androidsvg.C0.o("Mask reference \'%s\' not found", new Object[] {this.c.a.y}));
                this.c.a.y = 0;
            }
            return 1;
        }
    }

    public final void F(vn.zalopay.ui.androidsvg.V p4, J0.b p5, J0.b p6, vn.zalopay.ui.androidsvg.s p7)
    {
        if ((p5.d != 0) && (p5.e != 0)) {
            if (p7 == 0) {
                p7 = p4.n;
                if (p7 == 0) {
                    p7 = vn.zalopay.ui.androidsvg.s.d;
                }
            }
            this.S(this.c, p4);
            if (this.k()) {
                J0.b v0_3 = this.c;
                v0_3.f = p5;
                if (!v0_3.a.o.booleanValue()) {
                    boolean v5_5 = this.c.f;
                    this.L(v5_5.b, v5_5.c, v5_5.d, v5_5.e);
                }
                this.f(p4, this.c.f);
                boolean v5_9 = this.a;
                if (p6 == 0) {
                    int v6_2 = this.c.f;
                    v5_9.translate(v6_2.b, v6_2.c);
                } else {
                    v5_9.concat(vn.zalopay.ui.androidsvg.C0.e(this.c.f, p6, p7));
                    this.c.g = p4.o;
                }
                boolean v5_11 = this.E();
                this.T();
                this.H(p4, 1);
                if (v5_11) {
                    this.D(p4);
                }
                this.Q(p4);
            }
        }
        return;
    }

    public final void G(vn.zalopay.ui.androidsvg.c0 p21)
    {
        if (!(p21 instanceof vn.zalopay.ui.androidsvg.I)) {
            this.O();
            if ((p21 instanceof vn.zalopay.ui.androidsvg.a0)) {
                float v2_112 = ((vn.zalopay.ui.androidsvg.a0) p21).d;
                if (v2_112 != 0) {
                    this.c.h = v2_112.booleanValue();
                }
            }
            if (!(p21 instanceof vn.zalopay.ui.androidsvg.V)) {
                float v3_10 = 0;
                float v6_1 = 0;
                if (!(p21 instanceof vn.zalopay.ui.androidsvg.r0)) {
                    if (!(p21 instanceof vn.zalopay.ui.androidsvg.g0)) {
                        if (!(p21 instanceof vn.zalopay.ui.androidsvg.B)) {
                            float v11_1 = 0;
                            if (!(p21 instanceof vn.zalopay.ui.androidsvg.D)) {
                                if (!(p21 instanceof vn.zalopay.ui.androidsvg.K)) {
                                    if (!(p21 instanceof vn.zalopay.ui.androidsvg.Q)) {
                                        if (!(p21 instanceof vn.zalopay.ui.androidsvg.t)) {
                                            if (!(p21 instanceof vn.zalopay.ui.androidsvg.y)) {
                                                if (!(p21 instanceof vn.zalopay.ui.androidsvg.F)) {
                                                    if (!(p21 instanceof vn.zalopay.ui.androidsvg.P)) {
                                                        if (!(p21 instanceof vn.zalopay.ui.androidsvg.O)) {
                                                            if ((p21 instanceof vn.zalopay.ui.androidsvg.k0)) {
                                                                this.S(this.c, ((vn.zalopay.ui.androidsvg.k0) p21));
                                                                if (this.k()) {
                                                                    float v2_84 = ((vn.zalopay.ui.androidsvg.k0) p21).r;
                                                                    if (v2_84 != 0) {
                                                                        this.a.concat(v2_84);
                                                                    }
                                                                    float v2_0;
                                                                    float v2_85 = ((vn.zalopay.ui.androidsvg.k0) p21).n;
                                                                    if ((v2_85 != 0) && (v2_85.size() != 0)) {
                                                                        v2_0 = ((vn.zalopay.ui.androidsvg.E) ((vn.zalopay.ui.androidsvg.k0) p21).n.get(0)).f(this);
                                                                    } else {
                                                                        v2_0 = 0;
                                                                    }
                                                                    float v3_48;
                                                                    float v3_43 = ((vn.zalopay.ui.androidsvg.k0) p21).o;
                                                                    if ((v3_43 != 0) && (v3_43.size() != 0)) {
                                                                        v3_48 = ((vn.zalopay.ui.androidsvg.E) ((vn.zalopay.ui.androidsvg.k0) p21).o.get(0)).g(this);
                                                                    } else {
                                                                        v3_48 = 0;
                                                                    }
                                                                    android.graphics.Path v5_36;
                                                                    android.graphics.Path v5_28 = ((vn.zalopay.ui.androidsvg.k0) p21).p;
                                                                    if ((v5_28 != null) && (v5_28.size() != 0)) {
                                                                        v5_36 = ((vn.zalopay.ui.androidsvg.E) ((vn.zalopay.ui.androidsvg.k0) p21).p.get(0)).f(this);
                                                                    } else {
                                                                        v5_36 = 0;
                                                                    }
                                                                    vn.zalopay.ui.androidsvg.y0 v7_34 = ((vn.zalopay.ui.androidsvg.k0) p21).q;
                                                                    if ((v7_34 != null) && (v7_34.size() != 0)) {
                                                                        v6_1 = ((vn.zalopay.ui.androidsvg.E) ((vn.zalopay.ui.androidsvg.k0) p21).q.get(0)).g(this);
                                                                    }
                                                                    vn.zalopay.ui.androidsvg.y0 v7_0 = this.v();
                                                                    if (v7_0 != 1) {
                                                                        boolean v4_0 = this.d(((vn.zalopay.ui.androidsvg.k0) p21));
                                                                        if (v7_0 == 2) {
                                                                            v4_0 /= 1073741824;
                                                                        }
                                                                        v2_0 -= v4_0;
                                                                    }
                                                                    if (((vn.zalopay.ui.androidsvg.k0) p21).h == null) {
                                                                        boolean v4_3 = new vn.zalopay.ui.androidsvg.z0(this, v2_0, v3_48);
                                                                        vn.zalopay.ui.androidsvg.y0 v7_3 = ((android.graphics.RectF) v4_3.e);
                                                                        this.n(((vn.zalopay.ui.androidsvg.k0) p21), v4_3);
                                                                        ((vn.zalopay.ui.androidsvg.k0) p21).h = new J0.b(v7_3.left, v7_3.top, v7_3.width(), v7_3.height(), 2);
                                                                    }
                                                                    this.Q(((vn.zalopay.ui.androidsvg.k0) p21));
                                                                    this.g(((vn.zalopay.ui.androidsvg.k0) p21));
                                                                    this.f(((vn.zalopay.ui.androidsvg.k0) p21), ((vn.zalopay.ui.androidsvg.k0) p21).h);
                                                                    boolean v4_5 = this.E();
                                                                    this.n(((vn.zalopay.ui.androidsvg.k0) p21), new vn.zalopay.ui.androidsvg.y0(this, (v2_0 + v5_36), (v3_48 + v6_1)));
                                                                    if (v4_5) {
                                                                        this.D(((vn.zalopay.ui.androidsvg.k0) p21));
                                                                    }
                                                                }
                                                            }
                                                        } else {
                                                            this.S(this.c, ((vn.zalopay.ui.androidsvg.O) p21));
                                                            if ((this.k()) && (this.U())) {
                                                                float v2_7 = this.c;
                                                                if ((v2_7.c) || (v2_7.b)) {
                                                                    float v2_9 = ((vn.zalopay.ui.androidsvg.O) p21).n;
                                                                    if (v2_9 != 0) {
                                                                        this.a.concat(v2_9);
                                                                    }
                                                                    if (((vn.zalopay.ui.androidsvg.O) p21).o.length >= 2) {
                                                                        float v3_7;
                                                                        float v2_12 = vn.zalopay.ui.androidsvg.C0.z(((vn.zalopay.ui.androidsvg.O) p21));
                                                                        this.Q(((vn.zalopay.ui.androidsvg.O) p21));
                                                                        float v3_6 = this.c.a.D;
                                                                        if ((v3_6 == 0) || (v3_6 != 2)) {
                                                                            v3_7 = android.graphics.Path$FillType.WINDING;
                                                                        } else {
                                                                            v3_7 = android.graphics.Path$FillType.EVEN_ODD;
                                                                        }
                                                                        v2_12.setFillType(v3_7);
                                                                        this.g(((vn.zalopay.ui.androidsvg.O) p21));
                                                                        this.f(((vn.zalopay.ui.androidsvg.O) p21), ((vn.zalopay.ui.androidsvg.O) p21).h);
                                                                        float v3_9 = this.E();
                                                                        if (this.c.b) {
                                                                            this.l(((vn.zalopay.ui.androidsvg.O) p21), v2_12);
                                                                        }
                                                                        if (this.c.c) {
                                                                            this.m(v2_12);
                                                                        }
                                                                        this.J(((vn.zalopay.ui.androidsvg.O) p21));
                                                                        if (v3_9 != 0) {
                                                                            this.D(((vn.zalopay.ui.androidsvg.O) p21));
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    } else {
                                                        this.S(this.c, ((vn.zalopay.ui.androidsvg.P) p21));
                                                        if ((this.k()) && (this.U())) {
                                                            float v2_17 = this.c;
                                                            if ((v2_17.c) || (v2_17.b)) {
                                                                float v2_20 = ((vn.zalopay.ui.androidsvg.P) p21).n;
                                                                if (v2_20 != 0) {
                                                                    this.a.concat(v2_20);
                                                                }
                                                                if (((vn.zalopay.ui.androidsvg.P) p21).o.length >= 2) {
                                                                    float v2_23 = vn.zalopay.ui.androidsvg.C0.z(((vn.zalopay.ui.androidsvg.P) p21));
                                                                    this.Q(((vn.zalopay.ui.androidsvg.P) p21));
                                                                    this.g(((vn.zalopay.ui.androidsvg.P) p21));
                                                                    this.f(((vn.zalopay.ui.androidsvg.P) p21), ((vn.zalopay.ui.androidsvg.P) p21).h);
                                                                    float v3_14 = this.E();
                                                                    if (this.c.b) {
                                                                        this.l(((vn.zalopay.ui.androidsvg.P) p21), v2_23);
                                                                    }
                                                                    if (this.c.c) {
                                                                        this.m(v2_23);
                                                                    }
                                                                    this.J(((vn.zalopay.ui.androidsvg.P) p21));
                                                                    if (v3_14 != 0) {
                                                                        this.D(((vn.zalopay.ui.androidsvg.P) p21));
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                } else {
                                                    this.S(this.c, ((vn.zalopay.ui.androidsvg.F) p21));
                                                    if ((this.k()) && ((this.U()) && (this.c.c))) {
                                                        float v2_31 = ((vn.zalopay.ui.androidsvg.F) p21).n;
                                                        if (v2_31 != 0) {
                                                            this.a.concat(v2_31);
                                                        }
                                                        float v2_33;
                                                        float v2_32 = ((vn.zalopay.ui.androidsvg.F) p21).o;
                                                        if (v2_32 != 0) {
                                                            v2_33 = v2_32.f(this);
                                                        } else {
                                                            v2_33 = 0;
                                                        }
                                                        float v3_17;
                                                        float v3_16 = ((vn.zalopay.ui.androidsvg.F) p21).p;
                                                        if (v3_16 != 0) {
                                                            v3_17 = v3_16.g(this);
                                                        } else {
                                                            v3_17 = 0;
                                                        }
                                                        boolean v4_16;
                                                        boolean v4_15 = ((vn.zalopay.ui.androidsvg.F) p21).q;
                                                        if (v4_15) {
                                                            v4_16 = v4_15.f(this);
                                                        } else {
                                                            v4_16 = 0;
                                                        }
                                                        android.graphics.Path v5_0 = ((vn.zalopay.ui.androidsvg.F) p21).r;
                                                        if (v5_0 != null) {
                                                            v6_1 = v5_0.g(this);
                                                        }
                                                        if (((vn.zalopay.ui.androidsvg.F) p21).h == null) {
                                                            ((vn.zalopay.ui.androidsvg.F) p21).h = new J0.b(Math.min(v2_33, v4_16), Math.min(v3_17, v6_1), Math.abs((v4_16 - v2_33)), Math.abs((v6_1 - v3_17)), 2);
                                                        }
                                                        android.graphics.Path v5_5 = new android.graphics.Path();
                                                        v5_5.moveTo(v2_33, v3_17);
                                                        v5_5.lineTo(v4_16, v6_1);
                                                        this.Q(((vn.zalopay.ui.androidsvg.F) p21));
                                                        this.g(((vn.zalopay.ui.androidsvg.F) p21));
                                                        this.f(((vn.zalopay.ui.androidsvg.F) p21), ((vn.zalopay.ui.androidsvg.F) p21).h);
                                                        float v2_36 = this.E();
                                                        this.m(v5_5);
                                                        this.J(((vn.zalopay.ui.androidsvg.F) p21));
                                                        if (v2_36 != 0) {
                                                            this.D(((vn.zalopay.ui.androidsvg.F) p21));
                                                        }
                                                    }
                                                }
                                            } else {
                                                float v2_37 = ((vn.zalopay.ui.androidsvg.y) p21).q;
                                                if ((v2_37 != 0) && ((((vn.zalopay.ui.androidsvg.y) p21).r != null) && ((!v2_37.j()) && (!((vn.zalopay.ui.androidsvg.y) p21).r.j())))) {
                                                    this.S(this.c, ((vn.zalopay.ui.androidsvg.y) p21));
                                                    if ((this.k()) && (this.U())) {
                                                        float v2_45 = ((vn.zalopay.ui.androidsvg.y) p21).n;
                                                        if (v2_45 != 0) {
                                                            this.a.concat(v2_45);
                                                        }
                                                        float v2_47 = this.y(((vn.zalopay.ui.androidsvg.y) p21));
                                                        this.Q(((vn.zalopay.ui.androidsvg.y) p21));
                                                        this.g(((vn.zalopay.ui.androidsvg.y) p21));
                                                        this.f(((vn.zalopay.ui.androidsvg.y) p21), ((vn.zalopay.ui.androidsvg.y) p21).h);
                                                        float v3_21 = this.E();
                                                        if (this.c.b) {
                                                            this.l(((vn.zalopay.ui.androidsvg.y) p21), v2_47);
                                                        }
                                                        if (this.c.c) {
                                                            this.m(v2_47);
                                                        }
                                                        if (v3_21 != 0) {
                                                            this.D(((vn.zalopay.ui.androidsvg.y) p21));
                                                        }
                                                    }
                                                }
                                            }
                                        } else {
                                            float v2_48 = ((vn.zalopay.ui.androidsvg.t) p21).q;
                                            if ((v2_48 != 0) && (!v2_48.j())) {
                                                this.S(this.c, ((vn.zalopay.ui.androidsvg.t) p21));
                                                if ((this.k()) && (this.U())) {
                                                    float v2_54 = ((vn.zalopay.ui.androidsvg.t) p21).n;
                                                    if (v2_54 != 0) {
                                                        this.a.concat(v2_54);
                                                    }
                                                    float v2_55 = this.x(((vn.zalopay.ui.androidsvg.t) p21));
                                                    this.Q(((vn.zalopay.ui.androidsvg.t) p21));
                                                    this.g(((vn.zalopay.ui.androidsvg.t) p21));
                                                    this.f(((vn.zalopay.ui.androidsvg.t) p21), ((vn.zalopay.ui.androidsvg.t) p21).h);
                                                    float v3_24 = this.E();
                                                    if (this.c.b) {
                                                        this.l(((vn.zalopay.ui.androidsvg.t) p21), v2_55);
                                                    }
                                                    if (this.c.c) {
                                                        this.m(v2_55);
                                                    }
                                                    if (v3_24 != 0) {
                                                        this.D(((vn.zalopay.ui.androidsvg.t) p21));
                                                    }
                                                }
                                            }
                                        }
                                    } else {
                                        float v2_57 = ((vn.zalopay.ui.androidsvg.Q) p21).q;
                                        if ((v2_57 != 0) && ((((vn.zalopay.ui.androidsvg.Q) p21).r != null) && ((!v2_57.j()) && (!((vn.zalopay.ui.androidsvg.Q) p21).r.j())))) {
                                            this.S(this.c, ((vn.zalopay.ui.androidsvg.Q) p21));
                                            if ((this.k()) && (this.U())) {
                                                float v2_65 = ((vn.zalopay.ui.androidsvg.Q) p21).n;
                                                if (v2_65 != 0) {
                                                    this.a.concat(v2_65);
                                                }
                                                float v2_66 = this.A(((vn.zalopay.ui.androidsvg.Q) p21));
                                                this.Q(((vn.zalopay.ui.androidsvg.Q) p21));
                                                this.g(((vn.zalopay.ui.androidsvg.Q) p21));
                                                this.f(((vn.zalopay.ui.androidsvg.Q) p21), ((vn.zalopay.ui.androidsvg.Q) p21).h);
                                                float v3_28 = this.E();
                                                if (this.c.b) {
                                                    this.l(((vn.zalopay.ui.androidsvg.Q) p21), v2_66);
                                                }
                                                if (this.c.c) {
                                                    this.m(v2_66);
                                                }
                                                if (v3_28 != 0) {
                                                    this.D(((vn.zalopay.ui.androidsvg.Q) p21));
                                                }
                                            }
                                        }
                                    }
                                } else {
                                    if (((vn.zalopay.ui.androidsvg.K) p21).o != null) {
                                        this.S(this.c, ((vn.zalopay.ui.androidsvg.K) p21));
                                        if ((this.k()) && (this.U())) {
                                            float v2_73 = this.c;
                                            if ((v2_73.c) || (v2_73.b)) {
                                                float v2_75 = ((vn.zalopay.ui.androidsvg.K) p21).n;
                                                if (v2_75 != 0) {
                                                    this.a.concat(v2_75);
                                                }
                                                float v2_78 = new vn.zalopay.ui.androidsvg.w0(((vn.zalopay.ui.androidsvg.K) p21).o).a;
                                                if (((vn.zalopay.ui.androidsvg.K) p21).h == null) {
                                                    ((vn.zalopay.ui.androidsvg.K) p21).h = vn.zalopay.ui.androidsvg.C0.c(v2_78);
                                                }
                                                this.Q(((vn.zalopay.ui.androidsvg.K) p21));
                                                this.g(((vn.zalopay.ui.androidsvg.K) p21));
                                                this.f(((vn.zalopay.ui.androidsvg.K) p21), ((vn.zalopay.ui.androidsvg.K) p21).h);
                                                float v3_35 = this.E();
                                                boolean v4_29 = this.c;
                                                if (v4_29.b) {
                                                    boolean v4_32;
                                                    boolean v4_31 = v4_29.a.D;
                                                    if ((!v4_31) || (v4_31 != 2)) {
                                                        v4_32 = android.graphics.Path$FillType.WINDING;
                                                    } else {
                                                        v4_32 = android.graphics.Path$FillType.EVEN_ODD;
                                                    }
                                                    v2_78.setFillType(v4_32);
                                                    this.l(((vn.zalopay.ui.androidsvg.K) p21), v2_78);
                                                }
                                                if (this.c.c) {
                                                    this.m(v2_78);
                                                }
                                                this.J(((vn.zalopay.ui.androidsvg.K) p21));
                                                if (v3_35 != 0) {
                                                    this.D(((vn.zalopay.ui.androidsvg.K) p21));
                                                }
                                            }
                                        }
                                    }
                                }
                            } else {
                                float v2_80 = this.a;
                                android.graphics.Path v5_7 = ((vn.zalopay.ui.androidsvg.D) p21).r;
                                if ((v5_7 != null) && (!v5_7.j())) {
                                    android.graphics.Path v5_9 = ((vn.zalopay.ui.androidsvg.D) p21).s;
                                    if ((v5_9 != null) && (!v5_9.j())) {
                                        android.graphics.Path v5_11 = ((vn.zalopay.ui.androidsvg.D) p21).o;
                                        if (v5_11 != null) {
                                            vn.zalopay.ui.androidsvg.y0 v7_8 = ((vn.zalopay.ui.androidsvg.D) p21).n;
                                            if (v7_8 == null) {
                                                v7_8 = vn.zalopay.ui.androidsvg.s.d;
                                            }
                                            int v13_1 = v7_8;
                                            if ((v5_11.startsWith("data:")) && (v5_11.length() >= 14)) {
                                                vn.zalopay.ui.androidsvg.y0 v7_13 = v5_11.indexOf(44);
                                                if ((v7_13 >= 12) && (";base64".equals(v5_11.substring((v7_13 - 7), v7_13)))) {
                                                    float v3_37 = android.util.Base64.decode(v5_11.substring((v7_13 + 1)), 0);
                                                    v3_10 = android.graphics.BitmapFactory.decodeByteArray(v3_37, 0, v3_37.length);
                                                }
                                            }
                                            if (v3_10 != 0) {
                                                android.graphics.Path v5_13 = new J0.b(0, 0, ((float) v3_10.getWidth()), ((float) v3_10.getHeight()), 2);
                                                this.S(vn.zalopay.ui.androidsvg.C0 v1.c, ((vn.zalopay.ui.androidsvg.D) p21));
                                                if ((this.k()) && (this.U())) {
                                                    boolean v4_41 = ((vn.zalopay.ui.androidsvg.D) p21).t;
                                                    if (v4_41) {
                                                        v2_80.concat(v4_41);
                                                    }
                                                    vn.zalopay.ui.androidsvg.s v15;
                                                    boolean v4_42 = ((vn.zalopay.ui.androidsvg.D) p21).p;
                                                    if (!v4_42) {
                                                        v15 = 0;
                                                    } else {
                                                        v15 = v4_42.f(this);
                                                    }
                                                    vn.zalopay.ui.androidsvg.s v16;
                                                    boolean v4_44 = ((vn.zalopay.ui.androidsvg.D) p21).q;
                                                    if (!v4_44) {
                                                        v16 = 0;
                                                    } else {
                                                        v16 = v4_44.g(this);
                                                    }
                                                    boolean v4_48 = this.c;
                                                    v4_48.f = new J0.b(v15, v16, ((vn.zalopay.ui.androidsvg.D) p21).r.f(this), ((vn.zalopay.ui.androidsvg.D) p21).s.f(this), 2);
                                                    if (!v4_48.a.o.booleanValue()) {
                                                        boolean v4_53 = this.c.f;
                                                        this.L(v4_53.b, v4_53.c, v4_53.d, v4_53.e);
                                                    }
                                                    ((vn.zalopay.ui.androidsvg.D) p21).h = this.c.f;
                                                    this.Q(((vn.zalopay.ui.androidsvg.D) p21));
                                                    this.f(((vn.zalopay.ui.androidsvg.D) p21), ((vn.zalopay.ui.androidsvg.D) p21).h);
                                                    boolean v4_58 = this.E();
                                                    this.T();
                                                    v2_80.save();
                                                    v2_80.concat(vn.zalopay.ui.androidsvg.C0.e(this.c.f, v5_13, v13_1));
                                                    if (this.c.a.M != 3) {
                                                        v11_1 = 2;
                                                    }
                                                    v2_80.drawBitmap(v3_10, 0, 0, new android.graphics.Paint(v11_1));
                                                    v2_80.restore();
                                                    if (v4_58) {
                                                        this.D(((vn.zalopay.ui.androidsvg.D) p21));
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        } else {
                            this.S(this.c, ((vn.zalopay.ui.androidsvg.B) p21));
                            if (this.k()) {
                                float v2_89 = ((vn.zalopay.ui.androidsvg.B) p21).n;
                                if (v2_89 != 0) {
                                    this.a.concat(v2_89);
                                }
                                this.f(((vn.zalopay.ui.androidsvg.B) p21), ((vn.zalopay.ui.androidsvg.B) p21).h);
                                float v2_92 = this.E();
                                this.H(((vn.zalopay.ui.androidsvg.B) p21), 1);
                                if (v2_92 != 0) {
                                    this.D(((vn.zalopay.ui.androidsvg.B) p21));
                                }
                                this.Q(((vn.zalopay.ui.androidsvg.B) p21));
                            }
                        }
                    } else {
                        this.S(this.c, ((vn.zalopay.ui.androidsvg.g0) p21));
                        if (this.k()) {
                            float v2_95 = ((vn.zalopay.ui.androidsvg.g0) p21).n;
                            if (v2_95 != 0) {
                                this.a.concat(v2_95);
                            }
                            this.f(((vn.zalopay.ui.androidsvg.g0) p21), ((vn.zalopay.ui.androidsvg.g0) p21).h);
                            float v2_97 = this.E();
                            float v3_42 = java.util.Locale.getDefault().getLanguage();
                            boolean v4_60 = ((vn.zalopay.ui.androidsvg.g0) p21).i.iterator();
                            while (v4_60.hasNext()) {
                                android.graphics.Path v5_19 = ((vn.zalopay.ui.androidsvg.c0) v4_60.next());
                                if ((v5_19 instanceof vn.zalopay.ui.androidsvg.W)) {
                                    float v6_4 = ((vn.zalopay.ui.androidsvg.W) v5_19);
                                    if (v6_4.a() == null) {
                                        vn.zalopay.ui.androidsvg.y0 v7_23 = v6_4.e();
                                        if ((v7_23 == null) || ((!v7_23.isEmpty()) && (v7_23.contains(v3_42)))) {
                                            vn.zalopay.ui.androidsvg.y0 v7_25 = v6_4.g();
                                            if (v7_25 != null) {
                                                if (vn.zalopay.ui.androidsvg.C0.g == null) {
                                                    float v9_7 = new java.util.HashSet();
                                                    vn.zalopay.ui.androidsvg.C0.g = v9_7;
                                                    v9_7.add("Structure");
                                                    vn.zalopay.ui.androidsvg.C0.g.add("BasicStructure");
                                                    vn.zalopay.ui.androidsvg.C0.g.add("ConditionalProcessing");
                                                    vn.zalopay.ui.androidsvg.C0.g.add("Image");
                                                    vn.zalopay.ui.androidsvg.C0.g.add("Style");
                                                    vn.zalopay.ui.androidsvg.C0.g.add("ViewportAttribute");
                                                    vn.zalopay.ui.androidsvg.C0.g.add("Shape");
                                                    vn.zalopay.ui.androidsvg.C0.g.add("BasicText");
                                                    vn.zalopay.ui.androidsvg.C0.g.add("PaintAttribute");
                                                    vn.zalopay.ui.androidsvg.C0.g.add("BasicPaintAttribute");
                                                    vn.zalopay.ui.androidsvg.C0.g.add("OpacityAttribute");
                                                    vn.zalopay.ui.androidsvg.C0.g.add("BasicGraphicsAttribute");
                                                    vn.zalopay.ui.androidsvg.C0.g.add("Marker");
                                                    vn.zalopay.ui.androidsvg.C0.g.add("Gradient");
                                                    vn.zalopay.ui.androidsvg.C0.g.add("Pattern");
                                                    vn.zalopay.ui.androidsvg.C0.g.add("Clip");
                                                    vn.zalopay.ui.androidsvg.C0.g.add("BasicClip");
                                                    vn.zalopay.ui.androidsvg.C0.g.add("Mask");
                                                    vn.zalopay.ui.androidsvg.C0.g.add("View");
                                                }
                                                if ((v7_25.isEmpty()) || (!vn.zalopay.ui.androidsvg.C0.g.containsAll(v7_25))) {
                                                }
                                            }
                                            vn.zalopay.ui.androidsvg.y0 v7_27 = v6_4.d();
                                            if (v7_27 == null) {
                                                float v6_5 = v6_4.l();
                                                if (v6_5 == 0) {
                                                    this.G(v5_19);
                                                    break;
                                                } else {
                                                    v6_5.isEmpty();
                                                }
                                            } else {
                                                v7_27.isEmpty();
                                            }
                                        }
                                    }
                                }
                            }
                            if (v2_97 != 0) {
                                this.D(((vn.zalopay.ui.androidsvg.g0) p21));
                            }
                            this.Q(((vn.zalopay.ui.androidsvg.g0) p21));
                        }
                    }
                } else {
                    float v2_101 = this.a;
                    android.graphics.Path v5_20 = ((vn.zalopay.ui.androidsvg.r0) p21).r;
                    if ((v5_20 == null) || (!v5_20.j())) {
                        android.graphics.Path v5_22 = ((vn.zalopay.ui.androidsvg.r0) p21).s;
                        if ((v5_22 == null) || (!v5_22.j())) {
                            this.S(this.c, ((vn.zalopay.ui.androidsvg.r0) p21));
                            if (this.k()) {
                                android.graphics.Path v5_27 = ((vn.zalopay.ui.androidsvg.r0) p21).a.c(((vn.zalopay.ui.androidsvg.r0) p21).o);
                                if (v5_27 != null) {
                                    vn.zalopay.ui.androidsvg.y0 v7_29 = ((vn.zalopay.ui.androidsvg.r0) p21).n;
                                    if (v7_29 != null) {
                                        v2_101.concat(v7_29);
                                    }
                                    vn.zalopay.ui.androidsvg.y0 v7_31;
                                    vn.zalopay.ui.androidsvg.y0 v7_30 = ((vn.zalopay.ui.androidsvg.r0) p21).p;
                                    if (v7_30 == null) {
                                        v7_31 = 0;
                                    } else {
                                        v7_31 = v7_30.f(this);
                                    }
                                    J0.b v8_15;
                                    J0.b v8_14 = ((vn.zalopay.ui.androidsvg.r0) p21).q;
                                    if (v8_14 == null) {
                                        v8_15 = 0;
                                    } else {
                                        v8_15 = v8_14.g(this);
                                    }
                                    v2_101.translate(v7_31, v8_15);
                                    this.f(((vn.zalopay.ui.androidsvg.r0) p21), ((vn.zalopay.ui.androidsvg.r0) p21).h);
                                    vn.zalopay.ui.androidsvg.y0 v7_33 = this.E();
                                    this.e.push(((vn.zalopay.ui.androidsvg.r0) p21));
                                    this.f.push(this.a.getMatrix());
                                    if (!(v5_27 instanceof vn.zalopay.ui.androidsvg.V)) {
                                        if (!(v5_27 instanceof vn.zalopay.ui.androidsvg.h0)) {
                                            this.G(v5_27);
                                        } else {
                                            J0.b v8_20 = ((vn.zalopay.ui.androidsvg.r0) p21).r;
                                            if (v8_20 == null) {
                                                v8_20 = new vn.zalopay.ui.androidsvg.E(1120403456, 9);
                                            }
                                            float v11_3 = ((vn.zalopay.ui.androidsvg.r0) p21).s;
                                            if (v11_3 == 0) {
                                                v11_3 = new vn.zalopay.ui.androidsvg.E(1120403456, 9);
                                            }
                                            float v3_49 = this.B(0, 0, v8_20, v11_3);
                                            this.O();
                                            android.graphics.Path v5_29 = ((vn.zalopay.ui.androidsvg.h0) v5_27);
                                            if ((v3_49.d != 0) && (v3_49.e != 0)) {
                                                float v6_7 = v5_29.n;
                                                if (v6_7 == 0) {
                                                    v6_7 = vn.zalopay.ui.androidsvg.s.d;
                                                }
                                                this.S(this.c, v5_29);
                                                J0.b v8_26 = this.c;
                                                v8_26.f = v3_49;
                                                if (!v8_26.a.o.booleanValue()) {
                                                    float v3_54 = this.c.f;
                                                    this.L(v3_54.b, v3_54.c, v3_54.d, v3_54.e);
                                                }
                                                float v3_56 = v5_29.o;
                                                if (v3_56 == 0) {
                                                    float v3_58 = this.c.f;
                                                    v2_101.translate(v3_58.b, v3_58.c);
                                                } else {
                                                    v2_101.concat(vn.zalopay.ui.androidsvg.C0.e(this.c.f, v3_56, v6_7));
                                                    this.c.g = v5_29.o;
                                                }
                                                float v2_104 = this.E();
                                                this.H(v5_29, 1);
                                                if (v2_104 != 0) {
                                                    this.D(v5_29);
                                                }
                                                this.Q(v5_29);
                                            }
                                            this.N();
                                        }
                                    } else {
                                        android.graphics.Path v5_32 = ((vn.zalopay.ui.androidsvg.V) v5_27);
                                        float v2_106 = this.B(0, 0, ((vn.zalopay.ui.androidsvg.r0) p21).r, ((vn.zalopay.ui.androidsvg.r0) p21).s);
                                        this.O();
                                        this.F(v5_32, v2_106, v5_32.o, v5_32.n);
                                        this.N();
                                    }
                                    this.e.pop();
                                    this.f.pop();
                                    if (v7_33 != null) {
                                        this.D(((vn.zalopay.ui.androidsvg.r0) p21));
                                    }
                                    this.Q(((vn.zalopay.ui.androidsvg.r0) p21));
                                } else {
                                    vn.zalopay.ui.androidsvg.C0.o("Use reference \'%s\' not found", new Object[] {((vn.zalopay.ui.androidsvg.r0) p21).o}));
                                }
                            }
                        }
                    }
                }
            } else {
                this.F(((vn.zalopay.ui.androidsvg.V) p21), this.B(((vn.zalopay.ui.androidsvg.V) p21).p, ((vn.zalopay.ui.androidsvg.V) p21).q, ((vn.zalopay.ui.androidsvg.V) p21).r, ((vn.zalopay.ui.androidsvg.V) p21).s), Throwable v0_16.o, ((vn.zalopay.ui.androidsvg.V) p21).n);
            }
            this.N();
            return;
        } else {
            return;
        }
    }

    public final void H(vn.zalopay.ui.androidsvg.X p3, boolean p4)
    {
        if (p4) {
            this.e.push(p3);
            this.f.push(this.a.getMatrix());
        }
        java.util.Stack v3_1 = p3.i.iterator();
        while (v3_1.hasNext()) {
            this.G(((vn.zalopay.ui.androidsvg.c0) v3_1.next()));
        }
        if (p4) {
            this.e.pop();
            this.f.pop();
        }
        return;
    }

    public final void I(vn.zalopay.ui.androidsvg.G p13, vn.zalopay.ui.androidsvg.v0 p14)
    {
        float v0_5;
        this.O();
        float v0_0 = p13.u;
        float v1 = 0;
        if (v0_0 == 0) {
            v0_5 = 0;
        } else {
            if (!Float.isNaN(v0_0.floatValue())) {
                v0_5 = p13.u.floatValue();
            } else {
                float v0_6 = p14.c;
                if ((v0_6 == 0) && (p14.d == 0)) {
                } else {
                    v0_5 = ((float) Math.toDegrees(Math.atan2(((double) p14.d), ((double) v0_6))));
                }
            }
        }
        int v2_11;
        if (!p13.p) {
            v2_11 = this.c.a.f.e();
        } else {
            v2_11 = 1065353216;
        }
        boolean v14_15;
        this.c = this.t(p13);
        android.graphics.Matrix v3_2 = new android.graphics.Matrix();
        v3_2.preTranslate(p14.a, p14.b);
        v3_2.preRotate(v0_5);
        v3_2.preScale(v2_11, v2_11);
        boolean v14_14 = p13.q;
        if (!v14_14) {
            v14_15 = 0;
        } else {
            v14_15 = v14_14.f(this);
        }
        float v0_13;
        float v0_12 = p13.r;
        if (v0_12 == 0) {
            v0_13 = 0;
        } else {
            v0_13 = v0_12.g(this);
        }
        int v2_13;
        int v2_12 = p13.s;
        float v4_2 = 1077936128;
        if (v2_12 == 0) {
            v2_13 = 1077936128;
        } else {
            v2_13 = v2_12.f(this);
        }
        float v5_0 = p13.t;
        if (v5_0 != 0) {
            v4_2 = v5_0.g(this);
        }
        float v5_1 = p13.o;
        android.graphics.Canvas v6 = this.a;
        if (v5_1 == 0) {
            v3_2.preTranslate((- v14_15), (- v0_13));
            v6.concat(v3_2);
            if (!this.c.a.o.booleanValue()) {
                this.L(0, 0, v2_13, v4_2);
            }
        } else {
            float v7_1 = (v2_13 / v5_1.d);
            float v5_3 = (v4_2 / v5_1.e);
            int v8_2 = p13.n;
            if (v8_2 == 0) {
                v8_2 = vn.zalopay.ui.androidsvg.s.d;
            }
            vn.zalopay.ui.androidsvg.r v10 = v8_2.a;
            if (!v8_2.equals(vn.zalopay.ui.androidsvg.s.c)) {
                float v5_4;
                if (v8_2.b != 2) {
                    v5_4 = Math.min(v7_1, v5_3);
                } else {
                    v5_4 = Math.max(v7_1, v5_3);
                }
                v7_1 = v5_4;
                v5_3 = v7_1;
            }
            float v0_8;
            float v0_9;
            v3_2.preTranslate(((- v14_15) * v7_1), ((- v0_13) * v5_3));
            v6.concat(v3_2);
            boolean v14_3 = p13.o;
            float v0_4 = (v14_3.d * v7_1);
            boolean v14_5 = (v14_3.e * v5_3);
            int v8_0 = v10.ordinal();
            if (v8_0 == 2) {
                v0_8 = ((v2_13 - v0_4) / 1073741824);
                v0_9 = (0 - v0_8);
            } else {
                if (v8_0 != 3) {
                    if (v8_0 == 5) {
                    } else {
                        if (v8_0 != 6) {
                            if (v8_0 == 8) {
                            } else {
                                if (v8_0 != 9) {
                                    v0_9 = 0;
                                    boolean v14_6;
                                    switch (v10.ordinal()) {
                                        case 4:
                                        case 5:
                                        case 6:
                                            v14_6 = ((v4_2 - v14_5) / 1073741824);
                                            v1 = (0 - v14_6);
                                            break;
                                        case 7:
                                        case 8:
                                        case 9:
                                            v14_6 = (v4_2 - v14_5);
                                            break;
                                        default:
                                    }
                                    if (!this.c.a.o.booleanValue()) {
                                        this.L(v0_9, v1, v2_13, v4_2);
                                    }
                                    v3_2.reset();
                                    v3_2.preScale(v7_1, v5_3);
                                    v6.concat(v3_2);
                                    boolean v14_12 = this.E();
                                    this.H(p13, 0);
                                    if (v14_12) {
                                        this.D(p13);
                                    }
                                    this.N();
                                    return;
                                }
                            }
                        }
                    }
                }
                v0_8 = (v2_13 - v0_4);
            }
        }
    }

    public final void J(vn.zalopay.ui.androidsvg.A p20)
    {
        vn.zalopay.ui.androidsvg.v0 v2_10 = this.c.a;
        vn.zalopay.ui.androidsvg.v0 v3_10 = v2_10.q;
        if ((v3_10 != null) || ((v2_10.r != null) || (v2_10.s != null))) {
            vn.zalopay.ui.androidsvg.v0 v3_9;
            if (v3_10 == null) {
                v3_9 = 0;
            } else {
                vn.zalopay.ui.androidsvg.v0 v3_0 = p20.a.c(v3_10);
                if (v3_0 == null) {
                    vn.zalopay.ui.androidsvg.C0.o("Marker reference \'%s\' not found", new Object[] {this.c.a.q}));
                } else {
                    v3_9 = ((vn.zalopay.ui.androidsvg.G) v3_0);
                }
            }
            Object[] v5_9;
            Object[] v5_3 = this.c.a.r;
            if (v5_3 == null) {
                v5_9 = 0;
            } else {
                Object[] v5_4 = p20.a.c(v5_3);
                if (v5_4 == null) {
                    vn.zalopay.ui.androidsvg.C0.o("Marker reference \'%s\' not found", new Object[] {this.c.a.r}));
                } else {
                    v5_9 = ((vn.zalopay.ui.androidsvg.G) v5_4);
                }
            }
            Object[] v6_9;
            Object[] v6_3 = this.c.a.s;
            if (v6_3 == null) {
                v6_9 = 0;
            } else {
                Object[] v6_4 = p20.a.c(v6_3);
                if (v6_4 == null) {
                    vn.zalopay.ui.androidsvg.C0.o("Marker reference \'%s\' not found", new Object[] {this.c.a.s}));
                } else {
                    v6_9 = ((vn.zalopay.ui.androidsvg.G) v6_4);
                }
            }
            java.util.ArrayList v1_3;
            float v17;
            int v16;
            float v9_2 = 0;
            if (!(p20 instanceof vn.zalopay.ui.androidsvg.K)) {
                if (!(p20 instanceof vn.zalopay.ui.androidsvg.F)) {
                    v16 = 1;
                    vn.zalopay.ui.androidsvg.v0 v2_14 = ((vn.zalopay.ui.androidsvg.O) p20).o.length;
                    if (v2_14 >= 2) {
                        float v10_3 = new java.util.ArrayList();
                        float v12_7 = ((vn.zalopay.ui.androidsvg.O) p20).o;
                        float v11_1 = new vn.zalopay.ui.androidsvg.v0(v12_7[0], v12_7[1], 0, 0);
                        float v12_0 = 2;
                        java.util.ArrayList v13_2 = 0;
                        vn.zalopay.ui.androidsvg.v0 v14_1 = 0;
                        while(true) {
                            float v15_2 = v11_1.b;
                            v17 = v9_2;
                            float v9_7 = v11_1.a;
                            if (v12_0 >= v2_14) {
                                break;
                            }
                            java.util.ArrayList v13_0 = ((vn.zalopay.ui.androidsvg.O) p20).o;
                            vn.zalopay.ui.androidsvg.v0 v14_0 = v13_0[v12_0];
                            java.util.ArrayList v13_1 = v13_0[(v12_0 + 1)];
                            v11_1.a(v14_0, v13_1);
                            v10_3.add(v11_1);
                            v11_1 = new vn.zalopay.ui.androidsvg.v0(v14_0, v13_1, (v14_0 - v9_7), (v13_1 - v15_2));
                            v12_0 += 2;
                            v14_1 = v13_1;
                            v13_2 = v14_0;
                            v9_2 = v17;
                        }
                        if (!(((vn.zalopay.ui.androidsvg.O) p20) instanceof vn.zalopay.ui.androidsvg.P)) {
                            v10_3.add(v11_1);
                        } else {
                            java.util.ArrayList v1_12 = ((vn.zalopay.ui.androidsvg.O) p20).o;
                            vn.zalopay.ui.androidsvg.v0 v2_17 = v1_12[0];
                            if (v13_2 != v2_17) {
                                java.util.ArrayList v1_13 = v1_12[1];
                                if (v14_1 != v1_13) {
                                    v11_1.a(v2_17, v1_13);
                                    v10_3.add(v11_1);
                                    float v11_10 = new vn.zalopay.ui.androidsvg.v0(v2_17, v1_13, (v2_17 - v9_7), (v1_13 - v15_2));
                                    v11_10.b(((vn.zalopay.ui.androidsvg.v0) v10_3.get(0)));
                                    v10_3.add(v11_10);
                                    v10_3.set(0, v11_10);
                                }
                            }
                        }
                        v1_3 = v10_3;
                    } else {
                        v1_3 = 0;
                        v17 = 0;
                    }
                } else {
                    vn.zalopay.ui.androidsvg.v0 v2_2;
                    vn.zalopay.ui.androidsvg.v0 v2_1 = ((vn.zalopay.ui.androidsvg.F) p20).o;
                    if (v2_1 == null) {
                        v2_2 = 0;
                    } else {
                        v2_2 = v2_1.f(this);
                    }
                    float v11_3;
                    float v11_2 = ((vn.zalopay.ui.androidsvg.F) p20).p;
                    if (v11_2 == 0) {
                        v11_3 = 0;
                    } else {
                        v11_3 = v11_2.g(this);
                    }
                    float v12_2;
                    float v12_1 = ((vn.zalopay.ui.androidsvg.F) p20).q;
                    if (v12_1 == 0) {
                        v12_2 = 0;
                    } else {
                        v12_2 = v12_1.f(this);
                    }
                    java.util.ArrayList v1_6;
                    java.util.ArrayList v1_5 = ((vn.zalopay.ui.androidsvg.F) p20).r;
                    if (v1_5 == null) {
                        v1_6 = 0;
                    } else {
                        v1_6 = v1_5.g(this);
                    }
                    java.util.ArrayList v13_4 = new java.util.ArrayList(2);
                    float v15_1 = (v12_2 - v2_2);
                    v16 = 1;
                    float v10_0 = (v1_6 - v11_3);
                    v13_4.add(new vn.zalopay.ui.androidsvg.v0(v2_2, v11_3, v15_1, v10_0));
                    v13_4.add(new vn.zalopay.ui.androidsvg.v0(v12_2, v1_6, v15_1, v10_0));
                    v17 = 0;
                    v1_3 = v13_4;
                }
            } else {
                v1_3 = new vn.zalopay.ui.androidsvg.u0(((vn.zalopay.ui.androidsvg.K) p20).o).a;
                v17 = 0;
                v16 = 1;
            }
            if (v1_3 != null) {
                vn.zalopay.ui.androidsvg.v0 v2_7 = v1_3.size();
                if (v2_7 != null) {
                    float v9_4 = this.c.a;
                    v9_4.s = 0;
                    v9_4.r = 0;
                    v9_4.q = 0;
                    if (v3_9 != null) {
                        this.I(v3_9, ((vn.zalopay.ui.androidsvg.v0) v1_3.get(0)));
                    }
                    if ((v5_9 != null) && (v1_3.size() > 2)) {
                        float v4_4 = ((vn.zalopay.ui.androidsvg.v0) v1_3.get(0));
                        vn.zalopay.ui.androidsvg.v0 v3_7 = ((vn.zalopay.ui.androidsvg.v0) v1_3.get(v16));
                        int v7_2 = 1;
                        while (v7_2 < (v2_7 - 1)) {
                            v7_2++;
                            vn.zalopay.ui.androidsvg.v0 v8_2 = ((vn.zalopay.ui.androidsvg.v0) v1_3.get(v7_2));
                            if (v3_7.e) {
                                float v9_6 = v3_7.c;
                                float v10_1 = v3_7.d;
                                float v11_4 = v3_7.a;
                                java.util.ArrayList v13_5 = v3_7.b;
                                float v4_8 = (((v13_5 - v4_4.b) * v10_1) + ((v11_4 - v4_4.a) * v9_6));
                                if (v4_8 == v17) {
                                    v4_8 = (((v8_2.a - v11_4) * v9_6) + ((v8_2.b - v13_5) * v10_1));
                                }
                                float v4_12 = v4_8 cmp v17;
                                if ((v4_12 <= 0) && ((v4_12 != 0) || ((v9_6 <= v17) && (v10_1 < v17)))) {
                                    v3_7.c = (- v9_6);
                                    v3_7.d = (- v10_1);
                                }
                            }
                            this.I(v5_9, v3_7);
                            v4_4 = v3_7;
                            v3_7 = v8_2;
                        }
                    }
                    if (v6_9 != null) {
                        this.I(v6_9, ((vn.zalopay.ui.androidsvg.v0) v1_3.get((v2_7 - 1))));
                    }
                }
            }
        }
        return;
    }

    public final void K(vn.zalopay.ui.androidsvg.H p6, vn.zalopay.ui.androidsvg.Z p7)
    {
        android.graphics.Canvas v2_1;
        float v0_2;
        float v0_0 = p6.n;
        if ((v0_0 == 0) || (!v0_0.booleanValue())) {
            float v0_1;
            float v0_15 = p6.p;
            android.graphics.Canvas v2_0 = 1067030938;
            if (v0_15 == 0) {
                v0_1 = 1067030938;
            } else {
                v0_1 = v0_15.b(this, 1065353216);
            }
            int v3_0 = p6.q;
            if (v3_0 != 0) {
                v2_0 = v3_0.b(this, 1065353216);
            }
            int v3_1 = p7.h;
            v0_2 = (v0_1 * v3_1.d);
            v2_1 = (v2_0 * v3_1.e);
        } else {
            float v0_3 = p6.p;
            if (v0_3 == 0) {
                v0_2 = p7.h.d;
            } else {
                v0_2 = v0_3.f(this);
            }
            android.graphics.Canvas v2_2 = p6.q;
            if (v2_2 == null) {
                v2_1 = p7.h.e;
            } else {
                v2_1 = v2_2.g(this);
            }
        }
        if ((v0_2 != 0) && (v2_1 != 0)) {
            this.O();
            float v0_8 = this.t(p6);
            this.c = v0_8;
            v0_8.a.j = Float.valueOf(1065353216);
            float v0_10 = p6.o;
            if ((v0_10 != 0) && (!v0_10.booleanValue())) {
                float v0_12 = p7.h;
                android.graphics.Canvas v2_4 = this.a;
                v2_4.translate(v0_12.b, v0_12.c);
                float v7_1 = p7.h;
                v2_4.scale(v7_1.d, v7_1.e);
            }
            this.H(p6, 0);
            this.N();
        }
        return;
    }

    public final void L(float p2, float p3, float p4, float p5)
    {
        float v4_1 = (p4 + p2);
        float v5_1 = (p5 + p3);
        android.graphics.Canvas v0_21 = this.c.a.p;
        if (v0_21 != null) {
            p2 += ((vn.zalopay.ui.androidsvg.E) v0_21.d).f(this);
            p3 += ((vn.zalopay.ui.androidsvg.E) this.c.a.p.a).g(this);
            v4_1 -= ((vn.zalopay.ui.androidsvg.E) this.c.a.p.b).f(this);
            v5_1 -= ((vn.zalopay.ui.androidsvg.E) this.c.a.p.c).g(this);
        }
        this.a.clipRect(p2, p3, v4_1, v5_1);
        return;
    }

    public final void N()
    {
        this.a.restore();
        this.c = ((vn.zalopay.ui.androidsvg.A0) this.d.pop());
        return;
    }

    public final void O()
    {
        this.a.save();
        this.d.push(this.c);
        this.c = new vn.zalopay.ui.androidsvg.A0(this.c);
        return;
    }

    public final String P(String p4, boolean p5, boolean p6)
    {
        if (!this.c.h) {
            String v4_1 = p4.replaceAll("\\n", "").replaceAll("\\t", " ");
            if (p5 != null) {
                v4_1 = v4_1.replaceAll("^\\s+", "");
            }
            if (p6) {
                v4_1 = v4_1.replaceAll("\\s+$", "");
            }
            return v4_1.replaceAll("\\s{2,}", " ");
        } else {
            return p4.replaceAll("[\\n\\t]", " ");
        }
    }

    public final void Q(vn.zalopay.ui.androidsvg.Z p11)
    {
        if ((p11.b != null) && (p11.h != null)) {
            float v0_6 = new android.graphics.Matrix();
            if (((android.graphics.Matrix) this.f.peek()).invert(v0_6)) {
                float v1_1 = p11.h;
                float v2_9 = v1_1.b;
                float v3_1 = v1_1.c;
                float v1_2 = v1_1.c();
                float v4_0 = p11.h;
                float v5_1 = v4_0.c;
                float v4_1 = v4_0.c();
                float v6_2 = p11.h.d();
                float v11_1 = p11.h;
                int v7_1 = v11_1.b;
                float v11_2 = v11_1.d();
                float[] v8_1 = new float[8];
                v8_1[0] = v2_9;
                v8_1[1] = v3_1;
                float v3_0 = 2;
                v8_1[2] = v1_2;
                v8_1[3] = v5_1;
                v8_1[4] = v4_1;
                v8_1[5] = v6_2;
                v8_1[6] = v7_1;
                v8_1[7] = v11_2;
                v0_6.preConcat(this.a.getMatrix());
                v0_6.mapPoints(v8_1);
                float v11_6 = new android.graphics.RectF(v8_1[0], v8_1[1], v8_1[0], v8_1[1]);
                while (v3_0 <= 6) {
                    float v0_1 = v8_1[v3_0];
                    if (v0_1 < v11_6.left) {
                        v11_6.left = v0_1;
                    }
                    if (v0_1 > v11_6.right) {
                        v11_6.right = v0_1;
                    }
                    float v0_3 = v8_1[(v3_0 + 1)];
                    if (v0_3 < v11_6.top) {
                        v11_6.top = v0_3;
                    }
                    if (v0_3 > v11_6.bottom) {
                        v11_6.bottom = v0_3;
                    }
                    v3_0 += 2;
                }
                float v0_10 = ((vn.zalopay.ui.androidsvg.Z) this.e.peek());
                float v1_8 = v0_10.h;
                if (v1_8 != 0) {
                    float v0_11 = v11_6.left;
                    float v2_12 = v11_6.top;
                    float v3_3 = (v11_6.right - v0_11);
                    float v11_8 = (v11_6.bottom - v2_12);
                    if (v0_11 < v1_8.b) {
                        v1_8.b = v0_11;
                    }
                    if (v2_12 < v1_8.c) {
                        v1_8.c = v2_12;
                    }
                    if ((v0_11 + v3_3) > v1_8.c()) {
                        v1_8.d = ((v0_11 + v3_3) - v1_8.b);
                    }
                    if ((v2_12 + v11_8) > v1_8.d()) {
                        v1_8.e = ((v2_12 + v11_8) - v1_8.c);
                    }
                } else {
                    float v3_6 = v11_6.left;
                    float v4_9 = v11_6.top;
                    v0_10.h = new J0.b(v3_6, v4_9, (v11_6.right - v3_6), (v11_6.bottom - v4_9), 2);
                    return;
                }
            }
        }
        return;
    }

    public final void R(vn.zalopay.ui.androidsvg.A0 p18, vn.zalopay.ui.androidsvg.U p19)
    {
        if (vn.zalopay.ui.androidsvg.C0.w(p19, 4096)) {
            p18.a.k = p19.k;
        }
        if (vn.zalopay.ui.androidsvg.C0.w(p19, 2048)) {
            p18.a.j = p19.j;
        }
        Float v4_2 = vn.zalopay.ui.androidsvg.v.c;
        int v5 = 0;
        if (vn.zalopay.ui.androidsvg.C0.w(p19, 1)) {
            boolean v3_59;
            p18.a.b = p19.b;
            boolean v3_52 = p19.b;
            if ((!v3_52) || (v3_52 == v4_2)) {
                v3_59 = 0;
            } else {
                v3_59 = 1;
            }
            p18.b = v3_59;
        }
        if (vn.zalopay.ui.androidsvg.C0.w(p19, 4)) {
            p18.a.c = p19.c;
        }
        if (vn.zalopay.ui.androidsvg.C0.w(p19, 6149)) {
            vn.zalopay.ui.androidsvg.C0.M(p18, 1, p18.a.b);
        }
        if (vn.zalopay.ui.androidsvg.C0.w(p19, 2)) {
            p18.a.D = p19.D;
        }
        if (vn.zalopay.ui.androidsvg.C0.w(p19, 8)) {
            boolean v3_133;
            p18.a.d = p19.d;
            boolean v3_132 = p19.d;
            if ((!v3_132) || (v3_132 == v4_2)) {
                v3_133 = 0;
            } else {
                v3_133 = 1;
            }
            p18.c = v3_133;
        }
        if (vn.zalopay.ui.androidsvg.C0.w(p19, 16)) {
            p18.a.e = p19.e;
        }
        if (vn.zalopay.ui.androidsvg.C0.w(p19, 6168)) {
            vn.zalopay.ui.androidsvg.C0.M(p18, 0, p18.a.d);
        }
        if (vn.zalopay.ui.androidsvg.C0.w(p19, 34359738368)) {
            p18.a.L = p19.L;
        }
        if (vn.zalopay.ui.androidsvg.C0.w(p19, 32)) {
            Float v4_22 = p19.f;
            p18.a.f = v4_22;
            p18.e.setStrokeWidth(v4_22.a(this));
        }
        if (vn.zalopay.ui.androidsvg.C0.w(p19, 64)) {
            android.graphics.Typeface v7_0 = p18.e;
            p18.a.E = p19.E;
            boolean v3_4 = androidx.camera.camera2.internal.G.l(p19.E);
            if (!v3_4) {
                v7_0.setStrokeCap(android.graphics.Paint$Cap.BUTT);
            } else {
                if (v3_4 == 1) {
                    v7_0.setStrokeCap(android.graphics.Paint$Cap.ROUND);
                } else {
                    if (v3_4 == 2) {
                        v7_0.setStrokeCap(android.graphics.Paint$Cap.SQUARE);
                    }
                }
            }
        }
        if (vn.zalopay.ui.androidsvg.C0.w(p19, 128)) {
            android.graphics.Typeface v7_2 = p18.e;
            p18.a.F = p19.F;
            boolean v3_12 = androidx.camera.camera2.internal.G.l(p19.F);
            if (!v3_12) {
                v7_2.setStrokeJoin(android.graphics.Paint$Join.MITER);
            } else {
                if (v3_12 == 1) {
                    v7_2.setStrokeJoin(android.graphics.Paint$Join.ROUND);
                } else {
                    if (v3_12 == 2) {
                        v7_2.setStrokeJoin(android.graphics.Paint$Join.BEVEL);
                    }
                }
            }
        }
        if (vn.zalopay.ui.androidsvg.C0.w(p19, 256)) {
            p18.a.g = p19.g;
            p18.e.setStrokeMiter(p19.g.floatValue());
        }
        if (vn.zalopay.ui.androidsvg.C0.w(p19, 512)) {
            p18.a.h = p19.h;
        }
        if (vn.zalopay.ui.androidsvg.C0.w(p19, 1024)) {
            p18.a.i = p19.i;
        }
        android.graphics.Typeface v7_12 = 0;
        if (vn.zalopay.ui.androidsvg.C0.w(p19, 1536)) {
            boolean v3_26 = p18.a;
            String v8_2 = p18.e;
            Integer v9_0 = v3_26.h;
            if (v9_0 != null) {
                int v10_1;
                Integer v9_1 = v9_0.length;
                if ((v9_1 % 2) != 0) {
                    v10_1 = (v9_1 * 2);
                } else {
                    v10_1 = v9_1;
                }
                float[] v11 = new float[v10_1];
                int v13 = 0;
                float v14 = 0;
                while (v13 < v10_1) {
                    float v15_2 = v3_26.h[(v13 % v9_1)].a(this);
                    v11[v13] = v15_2;
                    v14 += v15_2;
                    v13++;
                }
                if (v14 != 0) {
                    boolean v3_28 = v3_26.i.a(this);
                    if (v3_28 < 0) {
                        v3_28 = ((v3_28 % v14) + v14);
                    }
                    v8_2.setPathEffect(new android.graphics.DashPathEffect(v11, v3_28));
                } else {
                    v8_2.setPathEffect(0);
                }
            } else {
                v8_2.setPathEffect(0);
            }
        }
        if (vn.zalopay.ui.androidsvg.C0.w(p19, 16384)) {
            boolean v3_34 = this.c.d.getTextSize();
            p18.a.m = p19.m;
            p18.d.setTextSize(p19.m.b(this, v3_34));
            p18.e.setTextSize(p19.m.b(this, v3_34));
        }
        if (vn.zalopay.ui.androidsvg.C0.w(p19, 8192)) {
            p18.a.l = p19.l;
        }
        if (vn.zalopay.ui.androidsvg.C0.w(p19, 32768)) {
            if ((p19.n.intValue() != -1) || (p18.a.n.intValue() <= 100)) {
                if ((p19.n.intValue() != 1) || (p18.a.n.intValue() >= 900)) {
                    p18.a.n = p19.n;
                } else {
                    boolean v3_51 = p18.a;
                    v3_51.n = Integer.valueOf((v3_51.n.intValue() + 100));
                }
            } else {
                boolean v3_53 = p18.a;
                v3_53.n = Integer.valueOf((v3_53.n.intValue() - 100));
            }
        }
        if (vn.zalopay.ui.androidsvg.C0.w(p19, 65536)) {
            p18.a.G = p19.G;
        }
        if (vn.zalopay.ui.androidsvg.C0.w(p19, 106496)) {
            boolean v3_57 = p18.a;
            String v8_24 = v3_57.l;
            if ((v8_24 != null) && (this.b != null)) {
                String v8_25 = v8_24.iterator();
                while (v8_25.hasNext()) {
                    v7_12 = vn.zalopay.ui.androidsvg.C0.h(((String) v8_25.next()), v3_57.G, v3_57.n);
                    if (v7_12 != null) {
                        break;
                    }
                }
            }
            if (v7_12 == null) {
                v7_12 = vn.zalopay.ui.androidsvg.C0.h("serif", v3_57.G, v3_57.n);
            }
            p18.d.setTypeface(v7_12);
            p18.e.setTypeface(v7_12);
        }
        if (vn.zalopay.ui.androidsvg.C0.w(p19, 131072)) {
            boolean v3_66;
            android.graphics.Typeface v7_18 = p18.e;
            String v8_27 = p18.d;
            p18.a.H = p19.H;
            if (p19.H != 4) {
                v3_66 = 0;
            } else {
                v3_66 = 1;
            }
            boolean v3_68;
            v8_27.setStrikeThruText(v3_66);
            if (p19.H != 2) {
                v3_68 = 0;
            } else {
                v3_68 = 1;
            }
            boolean v3_70;
            v8_27.setUnderlineText(v3_68);
            if (p19.H != 4) {
                v3_70 = 0;
            } else {
                v3_70 = 1;
            }
            v7_18.setStrikeThruText(v3_70);
            if (p19.H == 2) {
                v5 = 1;
            }
            v7_18.setUnderlineText(v5);
        }
        if (vn.zalopay.ui.androidsvg.C0.w(p19, 68719476736)) {
            p18.a.I = p19.I;
        }
        if (vn.zalopay.ui.androidsvg.C0.w(p19, 262144)) {
            p18.a.J = p19.J;
        }
        if (vn.zalopay.ui.androidsvg.C0.w(p19, 524288)) {
            p18.a.o = p19.o;
        }
        if (vn.zalopay.ui.androidsvg.C0.w(p19, 2097152)) {
            p18.a.q = p19.q;
        }
        if (vn.zalopay.ui.androidsvg.C0.w(p19, 4194304)) {
            p18.a.r = p19.r;
        }
        if (vn.zalopay.ui.androidsvg.C0.w(p19, 8388608)) {
            p18.a.s = p19.s;
        }
        if (vn.zalopay.ui.androidsvg.C0.w(p19, 16777216)) {
            p18.a.t = p19.t;
        }
        if (vn.zalopay.ui.androidsvg.C0.w(p19, 33554432)) {
            p18.a.u = p19.u;
        }
        if (vn.zalopay.ui.androidsvg.C0.w(p19, 1048576)) {
            p18.a.p = p19.p;
        }
        if (vn.zalopay.ui.androidsvg.C0.w(p19, 268435456)) {
            p18.a.x = p19.x;
        }
        if (vn.zalopay.ui.androidsvg.C0.w(p19, 536870912)) {
            p18.a.K = p19.K;
        }
        if (vn.zalopay.ui.androidsvg.C0.w(p19, 1073741824)) {
            p18.a.y = p19.y;
        }
        if (vn.zalopay.ui.androidsvg.C0.w(p19, 67108864)) {
            p18.a.v = p19.v;
        }
        if (vn.zalopay.ui.androidsvg.C0.w(p19, 134217728)) {
            p18.a.w = p19.w;
        }
        if (vn.zalopay.ui.androidsvg.C0.w(p19, 8589934592)) {
            p18.a.B = p19.B;
        }
        if (vn.zalopay.ui.androidsvg.C0.w(p19, 17179869184)) {
            p18.a.C = p19.C;
        }
        if (vn.zalopay.ui.androidsvg.C0.w(p19, 137438953472)) {
            p18.a.M = p19.M;
        }
        return;
    }

    public final void S(vn.zalopay.ui.androidsvg.A0 p6, vn.zalopay.ui.androidsvg.a0 p7)
    {
        java.util.Iterator v0_3;
        if (p7.b != null) {
            v0_3 = 0;
        } else {
            v0_3 = 1;
        }
        boolean v2_1 = p6.a;
        Float v3_1 = Float.valueOf(1065353216);
        vn.zalopay.ui.androidsvg.v v4_0 = Boolean.TRUE;
        v2_1.t = v4_0;
        if (v0_3 == null) {
            v4_0 = Boolean.FALSE;
        }
        v2_1.o = v4_0;
        v2_1.p = 0;
        v2_1.x = 0;
        v2_1.j = v3_1;
        v2_1.v = vn.zalopay.ui.androidsvg.v.b;
        v2_1.w = v3_1;
        v2_1.y = 0;
        v2_1.z = 0;
        v2_1.A = v3_1;
        v2_1.B = 0;
        v2_1.C = v3_1;
        v2_1.L = 1;
        java.util.Iterator v0_2 = p7.e;
        if (v0_2 != null) {
            this.R(p6, v0_2);
        }
        java.util.Iterator v0_6 = this.b.b.a;
        if ((v0_6 != null) && (!v0_6.isEmpty())) {
            java.util.Iterator v0_11 = this.b.b.a.iterator();
            while (v0_11.hasNext()) {
                vn.zalopay.ui.androidsvg.U v1_3 = ((vn.zalopay.ui.androidsvg.l) v0_11.next());
                if (vn.zalopay.ui.androidsvg.p.g(v1_3.a, p7)) {
                    this.R(p6, v1_3.b);
                }
            }
        }
        vn.zalopay.ui.androidsvg.U v7_1 = p7.f;
        if (v7_1 != null) {
            this.R(p6, v7_1);
        }
        return;
    }

    public final void T()
    {
        int v1_0;
        android.graphics.Canvas v0_1 = this.c.a;
        int v1_1 = v0_1.B;
        if (!(v1_1 instanceof vn.zalopay.ui.androidsvg.v)) {
            if ((v1_1 instanceof vn.zalopay.ui.androidsvg.w)) {
                v1_0 = v0_1.k.a;
                android.graphics.Canvas v0_2 = v0_1.C;
                if (v0_2 != null) {
                    v1_0 = vn.zalopay.ui.androidsvg.C0.i(v0_2.floatValue(), v1_0);
                }
                this.a.drawColor(v1_0);
            }
        } else {
            v1_0 = ((vn.zalopay.ui.androidsvg.v) v1_1).a;
        }
        return;
    }

    public final boolean U()
    {
        int v0_2 = this.c.a.u;
        if (v0_2 == 0) {
            return 1;
        } else {
            return v0_2.booleanValue();
        }
    }

    public final android.graphics.Path b(vn.zalopay.ui.androidsvg.Z p6, J0.b p7)
    {
        android.graphics.Path v6_2 = p6.a.c(this.c.a.x);
        if (v6_2 != null) {
            android.graphics.Path$Op v0_5;
            android.graphics.Path v6_7 = ((vn.zalopay.ui.androidsvg.u) v6_2);
            this.d.push(this.c);
            this.c = this.t(v6_7);
            android.graphics.Path$Op v0_2 = v6_7.o;
            if ((v0_2 != null) && (!v0_2.booleanValue())) {
                v0_5 = 0;
            } else {
                v0_5 = 1;
            }
            android.graphics.Matrix v2_1 = new android.graphics.Matrix();
            if (v0_5 == null) {
                v2_1.preTranslate(p7.b, p7.c);
                v2_1.preScale(p7.d, p7.e);
            }
            android.graphics.Path v7_2 = v6_7.n;
            if (v7_2 != null) {
                v2_1.preConcat(v7_2);
            }
            android.graphics.Path v7_4 = new android.graphics.Path();
            android.graphics.Path$Op v0_10 = v6_7.i.iterator();
            while (v0_10.hasNext()) {
                android.graphics.Path v3_3 = ((vn.zalopay.ui.androidsvg.c0) v0_10.next());
                if ((v3_3 instanceof vn.zalopay.ui.androidsvg.Z)) {
                    android.graphics.Path v3_5 = this.C(((vn.zalopay.ui.androidsvg.Z) v3_3), 1);
                    if (v3_5 != null) {
                        v7_4.op(v3_5, android.graphics.Path$Op.UNION);
                    }
                }
            }
            if (this.c.a.x != null) {
                if (v6_7.h == null) {
                    v6_7.h = vn.zalopay.ui.androidsvg.C0.c(v7_4);
                }
                android.graphics.Path v6_3 = this.b(v6_7, v6_7.h);
                if (v6_3 != null) {
                    v7_4.op(v6_3, android.graphics.Path$Op.INTERSECT);
                }
            }
            v7_4.transform(v2_1);
            this.c = ((vn.zalopay.ui.androidsvg.A0) this.d.pop());
            return v7_4;
        } else {
            vn.zalopay.ui.androidsvg.C0.o("ClipPath reference \'%s\' not found", new Object[] {this.c.a.x}));
            return 0;
        }
    }

    public final float d(vn.zalopay.ui.androidsvg.m0 p2)
    {
        vn.zalopay.ui.androidsvg.B0 v0_1 = new vn.zalopay.ui.androidsvg.B0(this);
        this.n(p2, v0_1);
        return v0_1.a;
    }

    public final void f(vn.zalopay.ui.androidsvg.Z p2, J0.b p3)
    {
        if (this.c.a.x != null) {
            android.graphics.Path v2_1 = this.b(p2, p3);
            if (v2_1 != null) {
                this.a.clipPath(v2_1);
            }
        }
        return;
    }

    public final void g(vn.zalopay.ui.androidsvg.Z p4)
    {
        vn.zalopay.ui.androidsvg.J v0_5 = this.c.a.b;
        if ((v0_5 instanceof vn.zalopay.ui.androidsvg.J)) {
            this.j(1, p4.h, ((vn.zalopay.ui.androidsvg.J) v0_5));
        }
        vn.zalopay.ui.androidsvg.J v0_3 = this.c.a.d;
        if ((v0_3 instanceof vn.zalopay.ui.androidsvg.J)) {
            this.j(0, p4.h, ((vn.zalopay.ui.androidsvg.J) v0_3));
        }
        return;
    }

    public final void j(boolean p27, J0.b p28, vn.zalopay.ui.androidsvg.J p29)
    {
        int v2_5 = p28;
        int v4_7 = this.b.c(p29.a);
        if (v4_7 != 0) {
            float v10_0 = vn.zalopay.ui.androidsvg.v.b;
            Float v14 = 1;
            if (!(v4_7 instanceof vn.zalopay.ui.androidsvg.b0)) {
                if (!(v4_7 instanceof vn.zalopay.ui.androidsvg.e0)) {
                    if ((v4_7 instanceof vn.zalopay.ui.androidsvg.S)) {
                        int v4_1 = ((vn.zalopay.ui.androidsvg.S) v4_7);
                        if (!p27) {
                            if (vn.zalopay.ui.androidsvg.C0.w(v4_1.e, 2147483648)) {
                                int v2_11 = this.c;
                                float v10_3 = v4_1.e.z;
                                v2_11.a.d = v10_3;
                                if (v10_3 == 0) {
                                    v14 = 0;
                                }
                                v2_11.c = v14;
                            }
                            if (vn.zalopay.ui.androidsvg.C0.w(v4_1.e, 4294967296)) {
                                this.c.a.e = v4_1.e.A;
                            }
                            if (vn.zalopay.ui.androidsvg.C0.w(v4_1.e, 6442450944)) {
                                int v2_28 = this.c;
                                vn.zalopay.ui.androidsvg.C0.M(v2_28, p27, v2_28.a.d);
                            }
                        } else {
                            if (vn.zalopay.ui.androidsvg.C0.w(v4_1.e, 2147483648)) {
                                int v2_31 = this.c;
                                float v10_5 = v4_1.e.z;
                                v2_31.a.b = v10_5;
                                if (v10_5 == 0) {
                                    v14 = 0;
                                }
                                v2_31.b = v14;
                            }
                            if (vn.zalopay.ui.androidsvg.C0.w(v4_1.e, 4294967296)) {
                                this.c.a.c = v4_1.e.A;
                            }
                            if (vn.zalopay.ui.androidsvg.C0.w(v4_1.e, 6442450944)) {
                                int v2_38 = this.c;
                                vn.zalopay.ui.androidsvg.C0.M(v2_38, p27, v2_38.a.b);
                                return;
                            }
                        }
                    }
                    return;
                } else {
                    int v4_8 = ((vn.zalopay.ui.androidsvg.e0) v4_7);
                    float[] v3_25 = v4_8.l;
                    if (v3_25 != null) {
                        vn.zalopay.ui.androidsvg.C0.q(v4_8, v3_25);
                    }
                    float[] v3_28;
                    float[] v3_26 = v4_8.i;
                    if ((v3_26 == null) || (!v3_26.booleanValue())) {
                        v3_28 = 0;
                    } else {
                        v3_28 = 1;
                    }
                    int v5_1;
                    int v5_0 = this.c;
                    if (!p27) {
                        v5_1 = v5_0.e;
                    } else {
                        v5_1 = v5_0.d;
                    }
                    int v19_0;
                    float v11_1;
                    float v21_0;
                    if (v3_28 == null) {
                        android.graphics.Matrix v6_1;
                        android.graphics.Matrix v6_0 = v4_8.m;
                        java.util.Iterator v7_0 = 1056964608;
                        if (v6_0 == null) {
                            v6_1 = 1056964608;
                        } else {
                            v6_1 = v6_0.b(this, 1065353216);
                        }
                        float v11_0 = v4_8.n;
                        if (v11_0 == 0) {
                            v11_1 = 1056964608;
                        } else {
                            v11_1 = v11_0.b(this, 1065353216);
                        }
                        int v12_0 = v4_8.o;
                        if (v12_0 != 0) {
                            v7_0 = v12_0.b(this, 1065353216);
                        }
                        v19_0 = v6_1;
                        v21_0 = v7_0;
                    } else {
                        java.util.Iterator v7_3;
                        android.graphics.Matrix v6_3 = new vn.zalopay.ui.androidsvg.E(1112014848, 9);
                        java.util.Iterator v7_2 = v4_8.m;
                        if (v7_2 == null) {
                            v7_3 = v6_3.f(this);
                        } else {
                            v7_3 = v7_2.f(this);
                        }
                        float v11_3 = v4_8.n;
                        if (v11_3 == 0) {
                            v11_1 = v6_3.g(this);
                        } else {
                            v11_1 = v11_3.g(this);
                        }
                        android.graphics.Matrix v6_4;
                        int v12_1 = v4_8.o;
                        if (v12_1 == 0) {
                            v6_4 = v6_3.a(this);
                        } else {
                            v6_4 = v12_1.a(this);
                        }
                        v21_0 = v6_4;
                        v19_0 = v7_3;
                    }
                    int v20_0 = v11_1;
                    this.O();
                    this.c = this.t(v4_8);
                    android.graphics.Matrix v6_7 = new android.graphics.Matrix();
                    if (v3_28 == null) {
                        v6_7.preTranslate(p28.b, p28.c);
                        v6_7.preScale(v2_5.d, v2_5.e);
                    }
                    int v2_1 = v4_8.j;
                    if (v2_1 != 0) {
                        v6_7.preConcat(v2_1);
                    }
                    int v2_3 = v4_8.h.size();
                    if (v2_3 != 0) {
                        int v1_1 = new int[v2_3];
                        java.util.Iterator v7_5 = new float[v2_3];
                        float v11_5 = v4_8.h.iterator();
                        int v12_2 = 0;
                        float v16_0 = -1082130432;
                        while (v11_5.hasNext()) {
                            int v15_1;
                            int v13_4 = ((vn.zalopay.ui.androidsvg.T) ((vn.zalopay.ui.androidsvg.c0) v11_5.next()));
                            int v15_0 = v13_4.h;
                            if (v15_0 == 0) {
                                v15_1 = 0;
                            } else {
                                v15_1 = v15_0.floatValue();
                            }
                            if ((v12_2 != 0) && (v15_1 < v16_0)) {
                                v7_5[v12_2] = v16_0;
                            } else {
                                v7_5[v12_2] = v15_1;
                                v16_0 = v15_1;
                            }
                            this.O();
                            this.S(this.c, v13_4);
                            int v13_6 = this.c.a;
                            int v15_4 = ((vn.zalopay.ui.androidsvg.v) v13_6.v);
                            if (v15_4 == 0) {
                                v15_4 = v10_0;
                            }
                            v1_1[v12_2] = vn.zalopay.ui.androidsvg.C0.i(v13_6.w.floatValue(), v15_4.a);
                            v12_2++;
                            this.N();
                        }
                        if ((v21_0 != 0) && (v2_3 != 1)) {
                            int v2_4 = android.graphics.Shader$TileMode.CLAMP;
                            int v4_0 = v4_8.k;
                            if (v4_0 != 0) {
                                if (v4_0 != 2) {
                                    if (v4_0 == 3) {
                                        v2_4 = android.graphics.Shader$TileMode.REPEAT;
                                    }
                                } else {
                                    v2_4 = android.graphics.Shader$TileMode.MIRROR;
                                }
                            }
                            int v1_9;
                            float[] v24_0 = v2_4;
                            this.N();
                            int v1_2 = new android.graphics.RadialGradient(v19_0, v20_0, v21_0, v1_1, v7_5, v24_0);
                            v1_2.setLocalMatrix(v6_7);
                            v5_1.setShader(v1_2);
                            int v1_8 = ((int) (this.c.a.c.floatValue() * 1132462080));
                            if (v1_8 >= 0) {
                                v1_9 = Math.min(v1_8, 255);
                            } else {
                                v1_9 = 0;
                            }
                            v5_1.setAlpha(v1_9);
                            return;
                        } else {
                            vn.zalopay.ui.androidsvg.A0 v22_1 = v1_1;
                            this.N();
                            v5_1.setColor(v22_1[(v2_3 - 1)]);
                            return;
                        }
                    } else {
                        this.N();
                        if (!p27) {
                            this.c.c = 0;
                            return;
                        } else {
                            this.c.b = 0;
                            return;
                        }
                    }
                }
            } else {
                int v4_2 = ((vn.zalopay.ui.androidsvg.b0) v4_7);
                float[] v3_8 = v4_2.l;
                if (v3_8 != null) {
                    vn.zalopay.ui.androidsvg.C0.q(v4_2, v3_8);
                }
                float[] v3_11;
                float[] v3_9 = v4_2.i;
                if ((v3_9 == null) || (!v3_9.booleanValue())) {
                    v3_11 = 0;
                } else {
                    v3_11 = 1;
                }
                int v15_7;
                int v15_6 = this.c;
                if (!p27) {
                    v15_7 = v15_6.e;
                } else {
                    v15_7 = v15_6.d;
                }
                int v29_2;
                int v17_1;
                float v21_1;
                vn.zalopay.ui.androidsvg.A0 v22_2;
                float v11_7;
                int v19_1;
                if (v3_11 == null) {
                    java.util.Iterator v7_8;
                    v29_2 = 1132462080;
                    v17_1 = 0;
                    java.util.Iterator v7_7 = v4_2.m;
                    if (v7_7 == null) {
                        v7_8 = 0;
                    } else {
                        v7_8 = v7_7.b(this, 1065353216);
                    }
                    float v11_6 = v4_2.n;
                    if (v11_6 == 0) {
                        v11_7 = 0;
                    } else {
                        v11_7 = v11_6.b(this, 1065353216);
                    }
                    int v12_4;
                    int v12_3 = v4_2.o;
                    if (v12_3 == 0) {
                        v12_4 = 1065353216;
                    } else {
                        v12_4 = v12_3.b(this, 1065353216);
                    }
                    android.graphics.Matrix v6_9;
                    android.graphics.Matrix v6_8 = v4_2.p;
                    if (v6_8 == null) {
                        v6_9 = 0;
                    } else {
                        v6_9 = v6_8.b(this, 1065353216);
                    }
                    v22_2 = v6_9;
                    v19_1 = v7_8;
                    v21_1 = v12_4;
                } else {
                    int v13_10 = this.c;
                    v29_2 = 1132462080;
                    java.util.Iterator v7_9 = v13_10.g;
                    if (v7_9 == null) {
                        v7_9 = v13_10.f;
                    }
                    int v13_12;
                    int v13_11 = v4_2.m;
                    if (v13_11 == 0) {
                        v13_12 = 0;
                    } else {
                        v13_12 = v13_11.f(this);
                    }
                    float v11_8 = v4_2.n;
                    if (v11_8 == 0) {
                        v11_7 = 0;
                    } else {
                        v11_7 = v11_8.g(this);
                    }
                    java.util.Iterator v7_10;
                    v17_1 = 0;
                    int v12_5 = v4_2.o;
                    if (v12_5 == 0) {
                        v7_10 = v7_9.d;
                    } else {
                        v7_10 = v12_5.f(this);
                    }
                    int v12_7;
                    int v12_6 = v4_2.p;
                    if (v12_6 == 0) {
                        v12_7 = 0;
                    } else {
                        v12_7 = v12_6.g(this);
                    }
                    v21_1 = v7_10;
                    v22_2 = v12_7;
                    v19_1 = v13_12;
                }
                int v20_1 = v11_7;
                this.O();
                this.c = this.t(v4_2);
                android.graphics.Matrix v6_12 = new android.graphics.Matrix();
                if (v3_11 == null) {
                    v6_12.preTranslate(p28.b, p28.c);
                    v6_12.preScale(v2_5.d, v2_5.e);
                }
                int v2_13 = v4_2.j;
                if (v2_13 != 0) {
                    v6_12.preConcat(v2_13);
                }
                int v2_15 = v4_2.h.size();
                if (v2_15 != 0) {
                    int v1_13 = new int[v2_15];
                    float[] v3_15 = new float[v2_15];
                    java.util.Iterator v7_13 = v4_2.h.iterator();
                    int v12_8 = 0;
                    float v11_9 = -1082130432;
                    while (v7_13.hasNext()) {
                        int v5_7;
                        int v13_16 = ((vn.zalopay.ui.androidsvg.T) ((vn.zalopay.ui.androidsvg.c0) v7_13.next()));
                        int v5_6 = v13_16.h;
                        if (v5_6 == 0) {
                            v5_7 = v17_1;
                        } else {
                            v5_7 = v5_6.floatValue();
                        }
                        if ((v12_8 != 0) && (v5_7 < v11_9)) {
                            v3_15[v12_8] = v11_9;
                        } else {
                            v3_15[v12_8] = v5_7;
                            v11_9 = v5_7;
                        }
                        this.O();
                        this.S(this.c, v13_16);
                        int v5_10 = this.c.a;
                        int v13_18 = ((vn.zalopay.ui.androidsvg.v) v5_10.v);
                        if (v13_18 == 0) {
                            v13_18 = v10_0;
                        }
                        v1_13[v12_8] = vn.zalopay.ui.androidsvg.C0.i(v5_10.w.floatValue(), v13_18.a);
                        v12_8++;
                        this.N();
                    }
                    if (((v19_1 != v21_1) || (v20_1 != v22_2)) && (v2_15 != 1)) {
                        int v2_16 = android.graphics.Shader$TileMode.CLAMP;
                        int v4_3 = v4_2.k;
                        if (v4_3 != 0) {
                            if (v4_3 != 2) {
                                if (v4_3 == 3) {
                                    v2_16 = android.graphics.Shader$TileMode.REPEAT;
                                }
                            } else {
                                v2_16 = android.graphics.Shader$TileMode.MIRROR;
                            }
                        }
                        int v5_5;
                        android.graphics.Shader$TileMode v25 = v2_16;
                        this.N();
                        int v1_14 = new android.graphics.LinearGradient(v19_1, v20_1, v21_1, v22_2, v1_13, v3_15, v25);
                        v1_14.setLocalMatrix(v6_12);
                        v15_7.setShader(v1_14);
                        int v1_20 = ((int) (this.c.a.c.floatValue() * v29_2));
                        if (v1_20 >= 0) {
                            v5_5 = Math.min(v1_20, 255);
                        } else {
                            v5_5 = 0;
                        }
                        v15_7.setAlpha(v5_5);
                        return;
                    } else {
                        this.N();
                        v15_7.setColor(v1_13[(v2_15 - 1)]);
                        return;
                    }
                } else {
                    this.N();
                    if (!p27) {
                        this.c.c = 0;
                        return;
                    } else {
                        this.c.b = 0;
                        return;
                    }
                }
            }
        } else {
            int v2_21;
            if (!p27) {
                v2_21 = "Stroke";
            } else {
                v2_21 = "Fill";
            }
            vn.zalopay.ui.androidsvg.C0.o("%s reference \'%s\' not found", new Object[] {v2_21, p29.a}));
            int v2_24 = p29.b;
            if (v2_24 == 0) {
                if (!p27) {
                    this.c.c = 0;
                    return;
                } else {
                    this.c.b = 0;
                    return;
                }
            } else {
                vn.zalopay.ui.androidsvg.C0.M(this.c, p27, v2_24);
                return;
            }
        }
    }

    public final boolean k()
    {
        int v0_2 = this.c.a.t;
        if (v0_2 == 0) {
            return 1;
        } else {
            return v0_2.booleanValue();
        }
    }

    public final void l(vn.zalopay.ui.androidsvg.Z p27, android.graphics.Path p28)
    {
        vn.zalopay.ui.androidsvg.N v3_6 = this.c.a.b;
        android.graphics.Canvas v5 = this.a;
        if ((v3_6 instanceof vn.zalopay.ui.androidsvg.J)) {
            vn.zalopay.ui.androidsvg.N v3_2 = this.b.c(((vn.zalopay.ui.androidsvg.J) v3_6).a);
            if ((v3_2 instanceof vn.zalopay.ui.androidsvg.N)) {
                float v4_16;
                vn.zalopay.ui.androidsvg.N v3_3 = ((vn.zalopay.ui.androidsvg.N) v3_2);
                float v4_12 = v3_3.p;
                if ((v4_12 == 0) || (!v4_12.booleanValue())) {
                    v4_16 = 0;
                } else {
                    v4_16 = 1;
                }
                boolean v8_1 = v3_3.w;
                if (v8_1) {
                    vn.zalopay.ui.androidsvg.C0.s(v3_3, v8_1);
                }
                float v12_3;
                float v4_23;
                boolean v8_5;
                float v13_2;
                if (v4_16 == 0) {
                    float v4_21;
                    float v4_20 = v3_3.s;
                    if (v4_20 == 0) {
                        v4_21 = 0;
                    } else {
                        v4_21 = v4_20.b(this, 1065353216);
                    }
                    float v9_17;
                    float v9_16 = v3_3.t;
                    if (v9_16 == 0) {
                        v9_17 = 0;
                    } else {
                        v9_17 = v9_16.b(this, 1065353216);
                    }
                    int v11_13;
                    int v11_12 = v3_3.u;
                    if (v11_12 == 0) {
                        v11_13 = 0;
                    } else {
                        v11_13 = v11_12.b(this, 1065353216);
                    }
                    boolean v8_3;
                    float v12_0 = v3_3.v;
                    if (v12_0 == 0) {
                        v8_3 = 0;
                    } else {
                        v8_3 = v12_0.b(this, 1065353216);
                    }
                    float v12_1 = p27.h;
                    float v14_4 = v12_1.d;
                    v4_23 = ((v4_21 * v14_4) + v12_1.b);
                    float v12_2 = v12_1.e;
                    v13_2 = (v8_3 * v12_2);
                    v8_5 = ((v9_17 * v12_2) + v12_1.c);
                    v12_3 = (v11_13 * v14_4);
                } else {
                    float v4_25 = v3_3.s;
                    if (v4_25 == 0) {
                        v4_23 = 0;
                    } else {
                        v4_23 = v4_25.f(this);
                    }
                    boolean v8_6 = v3_3.t;
                    if (!v8_6) {
                        v8_5 = 0;
                    } else {
                        v8_5 = v8_6.g(this);
                    }
                    float v9_21;
                    float v9_20 = v3_3.u;
                    if (v9_20 == 0) {
                        v9_21 = 0;
                    } else {
                        v9_21 = v9_20.f(this);
                    }
                    int v11_16;
                    int v11_15 = v3_3.v;
                    if (v11_15 == 0) {
                        v11_16 = 0;
                    } else {
                        v11_16 = v11_15.g(this);
                    }
                    v12_3 = v9_21;
                    v13_2 = v11_16;
                }
                if ((v12_3 != 0) && (v13_2 != 0)) {
                    float v9_24 = v3_3.n;
                    if (v9_24 == 0) {
                        v9_24 = vn.zalopay.ui.androidsvg.s.d;
                    }
                    float v19;
                    int v16;
                    float v18_0;
                    int v17;
                    vn.zalopay.ui.androidsvg.s v15 = v9_24;
                    this.O();
                    v5.clipPath(p28);
                    float v2_1 = new vn.zalopay.ui.androidsvg.A0();
                    this.R(v2_1, vn.zalopay.ui.androidsvg.U.a());
                    v2_1.a.o = Boolean.FALSE;
                    this.u(v3_3, v2_1);
                    this.c = v2_1;
                    float v2_2 = p27.h;
                    float v9_2 = v3_3.r;
                    if (v9_2 == 0) {
                        v19 = v4_23;
                        v18_0 = 0;
                        v16 = 1;
                        v17 = 0;
                    } else {
                        v5.concat(v9_2);
                        float v9_4 = new android.graphics.Matrix();
                        if (!v3_3.r.invert(v9_4)) {
                        } else {
                            float v2_3 = p27.h;
                            int v11_3 = v2_3.b;
                            float v14_0 = v2_3.c;
                            float v2_4 = v2_3.c();
                            v16 = 1;
                            float v6_0 = p27.h;
                            v17 = 0;
                            J0.b v7_0 = v6_0.c;
                            float v6_1 = v6_0.c();
                            v18_0 = 0;
                            float v10_1 = p27.h.d();
                            float v28_1 = v2_4;
                            float v2_5 = p27.h;
                            v19 = v4_23;
                            float v4_1 = v2_5.b;
                            J0.b v20_0 = v2_5.d();
                            float v2_8 = new float[8];
                            v2_8[0] = v11_3;
                            v2_8[1] = v14_0;
                            int v11_4 = 2;
                            v2_8[2] = v28_1;
                            v2_8[3] = v7_0;
                            v2_8[4] = v6_1;
                            v2_8[5] = v10_1;
                            v2_8[6] = v4_1;
                            v2_8[7] = v20_0;
                            v9_4.mapPoints(v2_8);
                            float v4_4 = new android.graphics.RectF(v2_8[0], v2_8[1], v2_8[0], v2_8[1]);
                            while (v11_4 <= 6) {
                                J0.b v7_5 = v2_8[v11_4];
                                if (v7_5 < v4_4.left) {
                                    v4_4.left = v7_5;
                                }
                                if (v7_5 > v4_4.right) {
                                    v4_4.right = v7_5;
                                }
                                J0.b v7_7 = v2_8[(v11_4 + 1)];
                                if (v7_7 < v4_4.top) {
                                    v4_4.top = v7_7;
                                }
                                if (v7_7 > v4_4.bottom) {
                                    v4_4.bottom = v7_7;
                                }
                                v11_4 += 2;
                            }
                            float v2_9 = v4_4.left;
                            float v6_4 = v4_4.top;
                            v2_2 = new J0.b(v2_9, v6_4, (v4_4.right - v2_9), (v4_4.bottom - v6_4), 2);
                        }
                    }
                    float v4_11 = ((((float) Math.floor(((double) ((v2_2.b - v19) / v12_3)))) * v12_3) + v19);
                    float v6_14 = ((((float) Math.floor(((double) ((v2_2.c - v8_5) / v13_2)))) * v13_2) + v8_5);
                    J0.b v7_4 = v2_2.c();
                    float v2_11 = v2_2.d();
                    float v9_7 = new J0.b(v18_0, v18_0, v12_3, v13_2, 2);
                    boolean v8_0 = this.E();
                    while (v6_14 < v2_11) {
                        float v10_4 = v4_11;
                        while (v10_4 < v7_4) {
                            float v28_2;
                            float v18_1;
                            v9_7.b = v10_4;
                            v9_7.c = v6_14;
                            this.O();
                            if (this.c.a.o.booleanValue()) {
                                v28_2 = v2_11;
                                v18_1 = v4_11;
                            } else {
                                v28_2 = v2_11;
                                v18_1 = v4_11;
                                this.L(v9_7.b, v9_7.c, v9_7.d, v9_7.e);
                            }
                            float v2_13 = v3_3.o;
                            if (v2_13 == 0) {
                                float v2_16;
                                float v2_14 = v3_3.q;
                                if ((v2_14 != 0) && (!v2_14.booleanValue())) {
                                    v2_16 = v17;
                                } else {
                                    v2_16 = v16;
                                }
                                v5.translate(v10_4, v6_14);
                                if (v2_16 == 0) {
                                    float v2_17 = p27.h;
                                    v5.scale(v2_17.d, v2_17.e);
                                }
                            } else {
                                v5.concat(vn.zalopay.ui.androidsvg.C0.e(v9_7, v2_13, v15));
                            }
                            float v2_21 = v3_3.i.iterator();
                            while (v2_21.hasNext()) {
                                this.G(((vn.zalopay.ui.androidsvg.c0) v2_21.next()));
                            }
                            this.N();
                            v10_4 += v12_3;
                            v2_11 = v28_2;
                            v4_11 = v18_1;
                        }
                        float v18 = v4_11;
                        v6_14 += v13_2;
                    }
                    if (v8_0) {
                        this.D(v3_3);
                    }
                    this.N();
                }
                return;
            }
        }
        v5.drawPath(p28, this.c.d);
        return;
    }

    public final void m(android.graphics.Path p6)
    {
        android.graphics.Paint v0_0 = this.c;
        android.graphics.Canvas v3 = this.a;
        if (v0_0.a.L != 2) {
            v3.drawPath(p6, v0_0.e);
            return;
        } else {
            android.graphics.Paint v0_1 = v3.getMatrix();
            android.graphics.Path v1_2 = new android.graphics.Path();
            p6.transform(v0_1, v1_2);
            v3.setMatrix(new android.graphics.Matrix());
            android.graphics.Shader v6_5 = this.c.e.getShader();
            android.graphics.Matrix v2_1 = new android.graphics.Matrix();
            if (v6_5 != null) {
                v6_5.getLocalMatrix(v2_1);
                android.graphics.Paint v4_1 = new android.graphics.Matrix(v2_1);
                v4_1.postConcat(v0_1);
                v6_5.setLocalMatrix(v4_1);
            }
            v3.drawPath(v1_2, this.c.e);
            v3.setMatrix(v0_1);
            if (v6_5 != null) {
                v6_5.setLocalMatrix(v2_1);
            }
            return;
        }
    }

    public final void n(vn.zalopay.ui.androidsvg.m0 p14, m9.z p15)
    {
        if (this.k()) {
            java.util.Iterator v14_2 = p14.i.iterator();
            boolean v1_27 = 1;
            while (v14_2.hasNext()) {
                String v2_1 = ((vn.zalopay.ui.androidsvg.c0) v14_2.next());
                if (!(v2_1 instanceof vn.zalopay.ui.androidsvg.q0)) {
                    if (p15.b(((vn.zalopay.ui.androidsvg.m0) v2_1))) {
                        float v6_3 = 0;
                        if (!(v2_1 instanceof vn.zalopay.ui.androidsvg.n0)) {
                            if (!(v2_1 instanceof vn.zalopay.ui.androidsvg.j0)) {
                                if ((v2_1 instanceof vn.zalopay.ui.androidsvg.i0)) {
                                    this.O();
                                    boolean v1_31 = ((vn.zalopay.ui.androidsvg.i0) v2_1);
                                    this.S(this.c, v1_31);
                                    if (this.k()) {
                                        this.g(((vn.zalopay.ui.androidsvg.Z) v1_31.o));
                                        String v2_7 = v2_1.a.c(v1_31.n);
                                        if (!(v2_7 instanceof vn.zalopay.ui.androidsvg.m0)) {
                                            vn.zalopay.ui.androidsvg.C0.o("Tref reference \'%s\' not found", new Object[] {v1_31.n}));
                                        } else {
                                            boolean v1_35 = new StringBuilder();
                                            this.p(((vn.zalopay.ui.androidsvg.m0) v2_7), v1_35);
                                            if (v1_35.length() > 0) {
                                                p15.c(v1_35.toString());
                                            }
                                        }
                                    }
                                    this.N();
                                }
                            } else {
                                this.O();
                                String v2_11 = ((vn.zalopay.ui.androidsvg.j0) v2_1);
                                this.S(this.c, v2_11);
                                if (this.k()) {
                                    boolean v1_41;
                                    boolean v1_39 = v2_11.n;
                                    if ((!v1_39) || (v1_39.size() <= 0)) {
                                        v1_41 = 0;
                                    } else {
                                        v1_41 = 1;
                                    }
                                    float v8_0;
                                    float v10_5;
                                    float v9_2;
                                    boolean v7_4 = (p15 instanceof vn.zalopay.ui.androidsvg.y0);
                                    if (!v7_4) {
                                        v8_0 = 0;
                                        v9_2 = 0;
                                        v10_5 = 0;
                                    } else {
                                        float v8_7;
                                        if (v1_41) {
                                            v8_7 = ((vn.zalopay.ui.androidsvg.E) v2_11.n.get(0)).f(this);
                                        } else {
                                            v8_7 = ((vn.zalopay.ui.androidsvg.y0) p15).a;
                                        }
                                        float v9_6 = v2_11.o;
                                        if ((v9_6 != 0) && (v9_6.size() != 0)) {
                                            v9_2 = ((vn.zalopay.ui.androidsvg.E) v2_11.o.get(0)).g(this);
                                        } else {
                                            v9_2 = ((vn.zalopay.ui.androidsvg.y0) p15).b;
                                        }
                                        float v10_0 = v2_11.p;
                                        if ((v10_0 != 0) && (v10_0.size() != 0)) {
                                            v10_5 = ((vn.zalopay.ui.androidsvg.E) v2_11.p.get(0)).f(this);
                                        } else {
                                            v10_5 = 0;
                                        }
                                        int v11_0 = v2_11.q;
                                        if ((v11_0 != 0) && (v11_0.size() != 0)) {
                                            v6_3 = ((vn.zalopay.ui.androidsvg.E) v2_11.q.get(0)).g(this);
                                        }
                                        v8_0 = v6_3;
                                        v6_3 = v8_7;
                                    }
                                    if (v1_41) {
                                        boolean v1_0 = this.v();
                                        if (v1_0 != 1) {
                                            int v11_2 = this.d(v2_11);
                                            if (v1_0 == 2) {
                                                v11_2 /= 1073741824;
                                            }
                                            v6_3 -= v11_2;
                                        }
                                    }
                                    this.g(((vn.zalopay.ui.androidsvg.Z) v2_11.r));
                                    if (v7_4) {
                                        ((vn.zalopay.ui.androidsvg.y0) p15).a = (v6_3 + v10_5);
                                        ((vn.zalopay.ui.androidsvg.y0) p15).b = (v9_2 + v8_0);
                                    }
                                    boolean v1_5 = this.E();
                                    this.n(v2_11, p15);
                                    if (v1_5) {
                                        this.D(v2_11);
                                    }
                                }
                                this.N();
                            }
                        } else {
                            this.O();
                            String v2_2 = ((vn.zalopay.ui.androidsvg.n0) v2_1);
                            this.S(this.c, v2_2);
                            if ((this.k()) && (this.U())) {
                                boolean v1_11 = v2_2.a.c(v2_2.n);
                                if (v1_11) {
                                    boolean v1_12 = ((vn.zalopay.ui.androidsvg.K) v1_11);
                                    boolean v1_13 = v1_12.n;
                                    boolean v7_3 = new vn.zalopay.ui.androidsvg.w0(v1_12.o).a;
                                    if (v1_13) {
                                        v7_3.transform(v1_13);
                                    }
                                    boolean v1_16 = new android.graphics.PathMeasure(v7_3, 0);
                                    float v8_2 = v2_2.o;
                                    if (v8_2 != 0) {
                                        v6_3 = v8_2.b(this, v1_16.getLength());
                                    }
                                    boolean v1_18 = this.v();
                                    if (v1_18 != 1) {
                                        float v8_3 = this.d(v2_2);
                                        if (v1_18 == 2) {
                                            v8_3 /= 1073741824;
                                        }
                                        v6_3 -= v8_3;
                                    }
                                    this.g(((vn.zalopay.ui.androidsvg.Z) v2_2.p));
                                    boolean v1_22 = this.E();
                                    this.n(v2_2, new vn.zalopay.ui.androidsvg.x0(this, v7_3, v6_3));
                                    if (v1_22) {
                                        this.D(v2_2);
                                    }
                                } else {
                                    vn.zalopay.ui.androidsvg.C0.o("TextPath reference \'%s\' not found", new Object[] {v2_2.n}));
                                }
                            }
                            this.N();
                        }
                    }
                } else {
                    p15.c(this.P(((vn.zalopay.ui.androidsvg.q0) v2_1).c, v1_27, (v14_2.hasNext() ^ 1)));
                }
                v1_27 = 0;
            }
        }
        return;
    }

    public final void p(vn.zalopay.ui.androidsvg.m0 p5, StringBuilder p6)
    {
        java.util.Iterator v5_2 = p5.i.iterator();
        String v1_1 = 1;
        while (v5_2.hasNext()) {
            String v2_1 = ((vn.zalopay.ui.androidsvg.c0) v5_2.next());
            if (!(v2_1 instanceof vn.zalopay.ui.androidsvg.m0)) {
                if ((v2_1 instanceof vn.zalopay.ui.androidsvg.q0)) {
                    p6.append(this.P(((vn.zalopay.ui.androidsvg.q0) v2_1).c, v1_1, (v5_2.hasNext() ^ 1)));
                }
            } else {
                this.p(((vn.zalopay.ui.androidsvg.m0) v2_1), p6);
            }
            v1_1 = 0;
        }
        return;
    }

    public final vn.zalopay.ui.androidsvg.A0 t(vn.zalopay.ui.androidsvg.a0 p3)
    {
        vn.zalopay.ui.androidsvg.A0 v0_1 = new vn.zalopay.ui.androidsvg.A0();
        this.R(v0_1, vn.zalopay.ui.androidsvg.U.a());
        this.u(p3, v0_1);
        return v0_1;
    }

    public final void u(vn.zalopay.ui.androidsvg.c0 p4, vn.zalopay.ui.androidsvg.A0 p5)
    {
        J0.b v0_1 = new java.util.ArrayList();
        while(true) {
            if ((p4 instanceof vn.zalopay.ui.androidsvg.a0)) {
                v0_1.add(0, ((vn.zalopay.ui.androidsvg.a0) p4));
            }
            J0.b v4_4 = p4.b;
            if (v4_4 == null) {
                break;
            }
            p4 = ((vn.zalopay.ui.androidsvg.c0) v4_4);
        }
        J0.b v4_1 = v0_1.iterator();
        while (v4_1.hasNext()) {
            this.S(p5, ((vn.zalopay.ui.androidsvg.a0) v4_1.next()));
        }
        J0.b v4_2 = this.c;
        p5.g = v4_2.g;
        p5.f = v4_2.f;
        return;
    }

    public final int v()
    {
        int v0_1 = this.c.a;
        if (v0_1.I != 1) {
            int v1_1 = v0_1.J;
            if (v1_1 != 2) {
                if (v1_1 != 1) {
                    return 1;
                } else {
                    return 3;
                }
            }
        }
        return v0_1.J;
    }

    public final android.graphics.Path x(vn.zalopay.ui.androidsvg.t p21)
    {
        int v9;
        float v2_2 = p21.o;
        J0.b v3_0 = 0;
        if (v2_2 == 0) {
            v9 = 0;
        } else {
            v9 = v2_2.f(this);
        }
        float v2_5 = p21.p;
        if (v2_5 != 0) {
            v3_0 = v2_5.g(this);
        }
        J0.b v3_2;
        float v16 = v3_0;
        float v2_1 = p21.q.a(this);
        android.graphics.Path v4_0 = (v9 - v2_1);
        J0.b v5_0 = (v16 - v2_1);
        J0.b v13_0 = (v9 + v2_1);
        float v17 = (v16 + v2_1);
        if (p21.h != null) {
            v3_2 = v4_0;
        } else {
            float v6_1 = (1073741824 * v2_1);
            v3_2 = v4_0;
            p21.h = new J0.b(v4_0, v5_0, v6_1, v6_1, 2);
        }
        float v2_3 = (v2_1 * 1057841801);
        android.graphics.Path v4_3 = new android.graphics.Path();
        v4_3.moveTo(v9, v5_0);
        float v7_1 = (v9 + v2_3);
        float v14_0 = (v16 - v2_3);
        float v12_0 = v5_0;
        v4_3.cubicTo(v7_1, v12_0, v13_0, v14_0, v13_0, v16);
        float v1_2 = v12_0;
        float v18 = v14_0;
        float v14_1 = (v16 + v2_3);
        float v8_1 = v17;
        v4_3.cubicTo(v13_0, v14_1, v7_1, v8_1, v9, v17);
        float v7_2 = (v9 - v2_3);
        v4_3.cubicTo(v7_2, v8_1, v3_2, v14_1, v3_2, v16);
        v4_3.cubicTo(v3_2, v18, v7_2, v1_2, v9, v1_2);
        v4_3.close();
        return v4_3;
    }

    public final android.graphics.Path y(vn.zalopay.ui.androidsvg.y p20)
    {
        int v9;
        float v2_2 = p20.o;
        float v3_0 = 0;
        if (v2_2 == 0) {
            v9 = 0;
        } else {
            v9 = v2_2.f(this);
        }
        float v2_5 = p20.p;
        if (v2_5 != 0) {
            v3_0 = v2_5.g(this);
        }
        float v17;
        float v16 = v3_0;
        float v2_1 = p20.q.f(this);
        float v3_2 = p20.r.g(this);
        float v5_0 = (v9 - v2_1);
        float v12_0 = (v16 - v3_2);
        android.graphics.Path v4_0 = (v9 + v2_1);
        float v8_0 = (v16 + v3_2);
        if (p20.h != null) {
            v17 = v5_0;
        } else {
            float v11_0 = v5_0;
            v17 = v11_0;
            p20.h = new J0.b(v11_0, v12_0, (v2_1 * 1073741824), (v3_2 * 1073741824), 2);
        }
        float v2_3 = (v2_1 * 1057841801);
        float v3_3 = (v3_2 * 1057841801);
        float v10_3 = new android.graphics.Path();
        v10_3.moveTo(v9, v12_0);
        float v7_0 = (v9 + v2_3);
        float v14_1 = (v16 - v3_3);
        float v13_1 = v4_0;
        v10_3.cubicTo(v7_0, v12_0, v13_1, v14_1, v4_0, v16);
        android.graphics.Path v4_1 = v10_3;
        float v1_2 = v12_0;
        float v18 = v14_1;
        float v14_2 = (v16 + v3_3);
        v4_1.cubicTo(v13_1, v14_2, v7_0, v8_0, v9, v8_0);
        float v7_1 = (v9 - v2_3);
        v4_1.cubicTo(v7_1, v8_0, v17, v14_2, v17, v16);
        v4_1.cubicTo(v17, v18, v7_1, v1_2, v9, v1_2);
        v4_1.close();
        return v4_1;
    }
}
