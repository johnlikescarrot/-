package vn.com.vng.zalopay.quickpay_native.domain.util;
public abstract class c {
    public static final java.util.Set a;

    static c()
    {
        vn.com.vng.zalopay.quickpay_native.domain.util.c.a = kotlin.collections.y.toSet(new Integer[] {Integer.valueOf(-217), Integer.valueOf(-111997), Integer.valueOf(-111994)}));
        return;
    }

    public static final wJ.K a(wJ.W p11, String p12)
    {
        boolean v0_0 = p11.a;
        Integer v1_0 = p11.f;
        String v2_1 = p11.k;
        wJ.I v4_1 = wJ.F.a;
        if (v0_0 != 1) {
            long v5_1 = 0;
            if (v0_0 != 1110) {
                if ((v0_0 != 1111) && (v0_0 != 17)) {
                    if (v0_0 != -63) {
                        if (v0_0 != -309) {
                            if (!vn.com.vng.zalopay.quickpay_native.domain.util.c.a.contains(Integer.valueOf(v0_0))) {
                                if (v0_0 != -322) {
                                    if (v0_0 != -111998) {
                                        if (p11.b != null) {
                                            wJ.H v11_9 = vn.com.vng.zalopay.quickpay_native.domain.util.c.b(p11, wJ.X.b);
                                            if (v11_9 != null) {
                                                return new wJ.J(v11_9);
                                            }
                                        }
                                    } else {
                                        if ((v2_1 == null) || (kotlin.text.StringsKt.isBlank(v2_1))) {
                                            v2_1 = 0;
                                        }
                                        if (v2_1 == null) {
                                            v2_1 = "V\u1ecb tr\u00ed c\u1ee7a b\u1ea1n kh\u00f4ng thu\u1ed9c khu v\u1ef1c thanh to\u00e1n c\u1ee7a \u0111\u01a1n h\u00e0ng n\u00e0y. Vui l\u00f2ng \u0111\u1ebfn c\u1eeda h\u00e0ng \u0111\u1ec3 thanh to\u00e1n.";
                                        }
                                        return new wJ.H(v2_1);
                                    }
                                } else {
                                    if ((v2_1 == null) || (kotlin.text.StringsKt.isBlank(v2_1))) {
                                        v2_1 = 0;
                                    }
                                    if (v2_1 == null) {
                                        v2_1 = "M\u00e3 thanh to\u00e1n \u0111\u00e3 h\u1ebft h\u1ea1n. Vui l\u00f2ng th\u1eed l\u1ea1i.";
                                    }
                                    return new wJ.E(v2_1);
                                }
                            } else {
                                if (v2_1 == null) {
                                    v2_1 = "";
                                }
                                return new wJ.G(wJ.g.c, v2_1, 0);
                            }
                        } else {
                            if (v2_1 == null) {
                                v2_1 = "";
                            }
                            return new wJ.G(wJ.g.b, v2_1, 0);
                        }
                    } else {
                        if (v2_1 == null) {
                            v2_1 = "";
                        }
                        long v7_2;
                        if (v1_0 == null) {
                            v7_2 = 0;
                        } else {
                            v7_2 = v1_0.longValue();
                        }
                        long v5_2;
                        if (p12 != null) {
                            v5_2 = p12;
                        } else {
                            v5_2 = "";
                        }
                        long v7_0;
                        long v9_2 = Og.x.coerceAtLeast((v7_2 - m9.q.d(v5_2)), 0);
                        long v5_3 = m9.q.c(v7_2);
                        long v7_3 = m9.q.c(v9_2);
                        StringBuilder v8_1 = new StringBuilder();
                        v8_1.append(v2_1);
                        v8_1.append(" S\u1ed1 ti\u1ec1n c\u1ea7n thanh to\u00e1n l\u00e0 ");
                        v8_1.append(v5_3);
                        v8_1.append("\u0111. S\u1ed1 ti\u1ec1n t\u1ed1i thi\u1ec3u c\u1ea7n ph\u1ea3i n\u1ea1p th\u00eam l\u00e0 ");
                        v8_1.append(v7_3);
                        v8_1.append("\u0111.");
                        String v2_0 = v8_1.toString();
                        if (v1_0 == null) {
                            v7_0 = 0;
                        } else {
                            v7_0 = v1_0.longValue();
                        }
                        if (p12 == null) {
                            p12 = "";
                        }
                        return new wJ.G(wJ.g.a, v2_0, Long.valueOf(Og.x.coerceAtLeast((v7_0 - m9.q.d(p12)), 0)));
                    }
                }
            } else {
                wJ.H v11_2 = p11.l;
                if (v11_2 != null) {
                    if (kotlin.text.StringsKt.isBlank(v11_2)) {
                        v11_2 = 0;
                    }
                    if (v11_2 != null) {
                        try {
                            wJ.H v11_4 = ((vn.com.vng.zalopay.quickpay_native.domain.util.a) vn.com.vng.zalopay.quickpay_native.domain.util.b.a.fromJson(v11_2, vn.com.vng.zalopay.quickpay_native.domain.util.a));
                        } catch (wJ.H v11_5) {
                            v11_4 = kotlin.ResultKt.createFailure(v11_5);
                        }
                        if ((v11_4 instanceof ng.K)) {
                            v11_4 = 0;
                        }
                        wJ.H v11_6 = ((vn.com.vng.zalopay.quickpay_native.domain.util.a) v11_4);
                        if (v11_6 != null) {
                            Long v12_5 = v11_6.a();
                            if (v12_5 != null) {
                                if (kotlin.text.StringsKt.isBlank(v12_5)) {
                                    v12_5 = 0;
                                }
                                if (v12_5 != null) {
                                    v5_1 = v12_5;
                                    if (v5_1 != 0) {
                                        v4_1 = new wJ.I(v5_1);
                                    }
                                    return v4_1;
                                }
                            }
                            wJ.H v11_7 = v11_6.b();
                            if ((v11_7 != null) && (!kotlin.text.StringsKt.isBlank(v11_7))) {
                                v5_1 = v11_7;
                            }
                        }
                    }
                }
            }
        } else {
            wJ.H v11_8 = vn.com.vng.zalopay.quickpay_native.domain.util.c.b(p11, wJ.X.a);
            if (v11_8 != null) {
                return new wJ.J(v11_8);
            }
        }
        return v4_1;
    }

    public static final wJ.V b(wJ.W p21, wJ.X p22)
    {
        String v1_0 = p21.b;
        if (v1_0 != null) {
            int v18;
            if (kotlin.text.StringsKt.isBlank(v1_0)) {
                v18 = 0;
            } else {
                v18 = v1_0;
            }
            if (v18 != 0) {
                String v6;
                String v1_1 = p21.c;
                if (v1_1 != null) {
                    v6 = v1_1;
                } else {
                    v6 = "";
                }
                String v9_0;
                String v1_2 = p21.d;
                long v7_0 = 0;
                if (v1_2 == null) {
                    v9_0 = 0;
                } else {
                    v9_0 = v1_2.longValue();
                }
                String v1_3 = p21.e;
                if (v1_3 == null) {
                    v1_3 = "";
                }
                long v11;
                String v3_0 = p21.f;
                if (v3_0 == null) {
                    v11 = 0;
                } else {
                    v11 = v3_0.longValue();
                }
                String v3_1 = p21.g;
                if (v3_1 != null) {
                    v7_0 = v3_1.longValue();
                }
                String v14;
                if (p22 != wJ.X.b) {
                    v14 = "";
                } else {
                    String v3_3 = p21.k;
                    if (v3_3 != null) {
                        v14 = v3_3;
                    }
                }
                String v2_4;
                String v2_2 = p21.h;
                if (v2_2 == null) {
                    v2_4 = 0;
                } else {
                    String v2_3 = kotlin.text.StringsKt.toIntOrNull(v2_2);
                    if (v2_3 == null) {
                    } else {
                        v2_4 = v2_3.intValue();
                    }
                }
                return new wJ.V(p22, v6, v9_0, v1_3, v11, v7_0, v14, v2_4, p21.i, p21.j, v18, 0, 0);
            }
        }
        return 0;
    }
}
