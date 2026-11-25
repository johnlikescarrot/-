package vn.zalopay.ui.androidsvg;
public final class t0 {
    public vn.zalopay.ui.androidsvg.V a;
    public final vn.zalopay.ui.androidsvg.m b;
    public final java.util.HashMap c;

    public t0()
    {
        this.a = 0;
        this.b = new vn.zalopay.ui.androidsvg.m();
        this.c = new java.util.HashMap();
        return;
    }

    public static vn.zalopay.ui.androidsvg.a0 a(vn.zalopay.ui.androidsvg.Y p3, String p4)
    {
        if (!p4.equals(((vn.zalopay.ui.androidsvg.a0) p3).c)) {
            int v3_3 = p3.getChildren().iterator();
            while (v3_3.hasNext()) {
                vn.zalopay.ui.androidsvg.a0 v0_4 = ((vn.zalopay.ui.androidsvg.c0) v3_3.next());
                if ((v0_4 instanceof vn.zalopay.ui.androidsvg.a0)) {
                    boolean v1_3 = ((vn.zalopay.ui.androidsvg.a0) v0_4);
                    if (!p4.equals(v1_3.c)) {
                        if ((v0_4 instanceof vn.zalopay.ui.androidsvg.Y)) {
                            vn.zalopay.ui.androidsvg.a0 v0_6 = vn.zalopay.ui.androidsvg.t0.a(((vn.zalopay.ui.androidsvg.Y) v0_4), p4);
                            if (v0_6 != null) {
                                return v0_6;
                            }
                        }
                    } else {
                        return v1_3;
                    }
                }
            }
            return 0;
        } else {
            return ((vn.zalopay.ui.androidsvg.a0) p3);
        }
    }

    public final android.graphics.Picture b(int p10, int p11, h3.j p12)
    {
        android.graphics.Picture v0_1 = new android.graphics.Picture();
        J0.b v1_0 = v0_1.beginRecording(p10, p11);
        if ((p12 == 0) || (((J0.b) p12.b) == null)) {
            if (p12 != 0) {
                J0.b v3_1 = new h3.j();
                v3_1.a = 0;
                v3_1.b = 0;
                v3_1.a = ((vn.zalopay.ui.androidsvg.m) p12.a);
                v3_1.b = ((J0.b) p12.b);
                p12 = v3_1;
            } else {
                p12 = new h3.j(20);
            }
            p12.b = new J0.b(0, 0, ((float) p10), ((float) p11), 2);
        }
        java.util.ArrayList v10_9 = new vn.zalopay.ui.androidsvg.C0(v1_0);
        v10_9.b = this;
        hs.c v11_4 = this.a;
        if (v11_4 != null) {
            float v4_8;
            J0.b v1_1 = v11_4.o;
            J0.b v3_5 = v11_4.n;
            float v4_5 = ((vn.zalopay.ui.androidsvg.m) p12.a);
            float v5_0 = 1;
            if (v4_5 == 0) {
                v4_8 = 0;
            } else {
                float v4_7;
                float v4_6 = v4_5.a;
                if (v4_6 == 0) {
                    v4_7 = 0;
                } else {
                    v4_7 = v4_6.size();
                }
                if (v4_7 <= 0) {
                } else {
                    v4_8 = 1;
                }
            }
            vn.zalopay.ui.androidsvg.m v7_1 = this.b;
            if (v4_8 != 0) {
                v7_1.b(((vn.zalopay.ui.androidsvg.m) p12.a));
            }
            v10_9.c = new vn.zalopay.ui.androidsvg.A0();
            v10_9.d = new java.util.Stack();
            v10_9.R(v10_9.c, vn.zalopay.ui.androidsvg.U.a());
            float v4_16 = v10_9.c;
            v4_16.f = 0;
            v4_16.h = 0;
            v10_9.d.push(new vn.zalopay.ui.androidsvg.A0(v4_16));
            v10_9.f = new java.util.Stack();
            v10_9.e = new java.util.Stack();
            J0.b v2_6 = v11_4.d;
            if (v2_6 != null) {
                v10_9.c.h = v2_6.booleanValue();
            }
            v10_9.O();
            J0.b v2_9 = new J0.b(((J0.b) p12.b), 0);
            float v4_20 = v11_4.r;
            if (v4_20 != 0) {
                v2_9.d = v4_20.b(v10_9, v2_9.d);
            }
            float v4_22 = v11_4.s;
            if (v4_22 != 0) {
                v2_9.e = v4_22.b(v10_9, v2_9.e);
            }
            v10_9.F(v11_4, v2_9, v1_1, v3_5);
            v10_9.N();
            java.util.ArrayList v10_2 = ((vn.zalopay.ui.androidsvg.m) p12.a);
            if (v10_2 == null) {
                v5_0 = 0;
            } else {
                java.util.ArrayList v10_4;
                java.util.ArrayList v10_3 = v10_2.a;
                if (v10_3 == null) {
                    v10_4 = 0;
                } else {
                    v10_4 = v10_3.size();
                }
                if (v10_4 <= null) {
                }
            }
            if (v5_0 != 0) {
                java.util.ArrayList v10_5 = v7_1.a;
                if (v10_5 != null) {
                    v10_5.removeIf(new hs.c(1));
                }
            }
        } else {
            ck.b.e("SVGAndroidRenderer", new Object[] {"Nothing to render. Document is empty."}));
        }
        v0_1.endRecording();
        return v0_1;
    }

    public final vn.zalopay.ui.androidsvg.a0 c(String p4)
    {
        if (p4 != null) {
            if ((!p4.startsWith("\"")) || (!p4.endsWith("\""))) {
                if ((p4.startsWith("\'")) && (p4.endsWith("\'"))) {
                    p4 = p4.substring(1, (p4.length() - int v2)).replace("\\\'", "\'");
                }
            } else {
                p4 = p4.substring(1, (p4.length() - v2)).replace("\\\"", "\"");
            }
            vn.zalopay.ui.androidsvg.a0 v4_4 = p4.replace("\\\n", "").replace("\\A", "\n");
            if ((v4_4.length() > 1) && (v4_4.startsWith("#"))) {
                vn.zalopay.ui.androidsvg.a0 v4_6;
                vn.zalopay.ui.androidsvg.a0 v4_5 = v4_4.substring(1);
                if ((v4_5 != null) && (v4_5.length() != 0)) {
                    if (!v4_5.equals(this.a.c)) {
                        java.util.HashMap v0_10 = this.c;
                        if (!v0_10.containsKey(v4_5)) {
                            vn.zalopay.ui.androidsvg.a0 v1_13 = vn.zalopay.ui.androidsvg.t0.a(this.a, v4_5);
                            v0_10.put(v4_5, v1_13);
                            v4_6 = v1_13;
                        } else {
                            v4_6 = ((vn.zalopay.ui.androidsvg.a0) v0_10.get(v4_5));
                        }
                    } else {
                        v4_6 = this.a;
                    }
                } else {
                    v4_6 = 0;
                }
                return v4_6;
            }
        }
        return 0;
    }
}
