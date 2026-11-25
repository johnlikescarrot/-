package vn.zalopay.ui.androidsvg;
public final class q {
    public static final float[] b;
    public static final float[] c;
    public int a;

    static q()
    {
        float[] v1 = new float[39];
        v1 = {1065353216, 1092616192, 1120403456, 1148846080, 1176256512, 1203982336, 1232348160, 1259902592, 1287568416, 1315859240, 1343554297, 1371161527, 1399379109, 1427211495, 1454761505, 1482907561, 1510874058, 1538368188, 1566444395, 1594541859, 1621981420, 1649989415, 1678214776, 1705601046, 1733542428, 1761892689, 1789226918, 1817103247, 1845575481, 1872858888, 1900671690, 1929146236, 1956496814, 1984247577, 2012644575, 2040140556, 2067830734, 2096152002, 2123789977};
        vn.zalopay.ui.androidsvg.q.b = v1;
        float[] v0_1 = new float[39];
        v0_1 = {1065353216, 1036831949, 1008981770, 981668463, 953267991, 925353388, 897988541, 869711765, 841731191, 814313567, 786163455, 758115327, 730643660, 702623251, 674505948, 646978941, 619091349, 590903210, 563319535, 535567946, 507307272, 479665568, 452053249, 423718298, 396017173, 368547464, 340136454, 312374482, 285050806, 256561912, 228737632, 201445043, 172994847, 145106764, 117763133, 89435438, 61482021, 34086122, 7136238};
        vn.zalopay.ui.androidsvg.q.c = v0_1;
        return;
    }

    public final float a(int p24, int p25, String p26)
    {
        this.a = p24;
        if (p24 < p25) {
            float v1_2;
            float v1_3 = p26.charAt(p24);
            if (v1_3 == 43) {
                v1_2 = 0;
                this.a = (this.a + 1);
            } else {
                if (v1_3 == 45) {
                    v1_2 = 1;
                } else {
                    v1_2 = 0;
                }
            }
            int v9_5 = this.a;
            long v7_2 = 0;
            int v12_2 = 0;
            int v13 = 0;
            long v14_2 = 0;
            int v15 = 0;
            int v16_0 = 0;
            while(true) {
                int v4_6 = this.a;
                if (v4_6 >= p25) {
                    break;
                }
                int v4_7 = p26.charAt(v4_6);
                if (v4_7 != 48) {
                    if ((v4_7 < 49) || (v4_7 > 57)) {
                        if ((v4_7 != 46) || (v15 != 0)) {
                            break;
                        }
                        v16_0 = (this.a - v9_5);
                        v15 = 1;
                    } else {
                        int v12_1 = (v12_2 + v13);
                        while (v13 > 0) {
                            if (v7_2 <= 922337203685477580) {
                                v7_2 *= 10;
                                v13--;
                            } else {
                                return 2143289344;
                            }
                        }
                        if (v7_2 <= 922337203685477580) {
                            v7_2 = ((v7_2 * 10) + ((long) (v4_7 - 48)));
                            v12_2 = (v12_1 + 1);
                            if (v7_2 < 0) {
                                return 2143289344;
                            }
                        } else {
                            return 2143289344;
                        }
                    }
                } else {
                    if (v12_2 != 0) {
                        v13++;
                    } else {
                        v14_2++;
                    }
                }
                this.a = (this.a + 1);
            }
            if ((v15 == 0) || (this.a != (v16_0 + 1))) {
                if (v12_2 == 0) {
                    if (v14_2 != 0) {
                        v12_2 = 1;
                    } else {
                        return 2143289344;
                    }
                }
                if (v15 != 0) {
                    v13 = ((v16_0 - v14_2) - v12_2);
                }
                int v4_11 = this.a;
                if (v4_11 < p25) {
                    int v4_12 = p26.charAt(v4_11);
                    if ((v4_12 == 69) || (v4_12 == 101)) {
                        int v4_14 = (this.a + 1);
                        this.a = v4_14;
                        if (v4_14 != p25) {
                            int v4_16;
                            int v5_2;
                            int v4_15 = p26.charAt(v4_14);
                            if (v4_15 == 43) {
                                v4_16 = 0;
                                this.a = (this.a + 1);
                                v5_2 = 0;
                            } else {
                                if (v4_15 == 45) {
                                    v4_16 = 1;
                                } else {
                                    switch (v4_15) {
                                        case 48:
                                        case 49:
                                        case 50:
                                        case 51:
                                        case 52:
                                        case 53:
                                        case 54:
                                        case 55:
                                        case 56:
                                        case 57:
                                            v4_16 = 0;
                                        default:
                                            this.a = (this.a - 1);
                                            v5_2 = 1;
                                            v4_16 = 0;
                                    }
                                }
                            }
                            if (v5_2 == 0) {
                                int v5_3 = this.a;
                                int v6_2 = 0;
                                while(true) {
                                    int v9_9 = this.a;
                                    if (v9_9 < p25) {
                                        int v9_10 = p26.charAt(v9_9);
                                        if ((v9_10 >= 48) && (v9_10 <= 57)) {
                                            if (((long) v6_2) > 922337203685477580) {
                                                break;
                                            }
                                            v6_2 = ((v6_2 * 10) + (v9_10 - 48));
                                            this.a = (this.a + 1);
                                        }
                                    }
                                    if (this.a != v5_3) {
                                        if (v4_16 == 0) {
                                            v13 += v6_2;
                                        } else {
                                            v13 -= v6_2;
                                        }
                                    } else {
                                        return 2143289344;
                                    }
                                }
                                return 2143289344;
                            }
                        } else {
                            return 2143289344;
                        }
                    }
                }
                int v12_0 = (v12_2 + v13);
                if ((v12_0 <= 39) && (v12_0 >= -44)) {
                    int v2_2 = ((float) v7_2);
                    if (v7_2 != 0) {
                        float v3_3;
                        if (v13 <= 0) {
                            if (v13 >= 0) {
                                if (v1_2 == 0) {
                                    return v2_2;
                                } else {
                                    return (- v2_2);
                                }
                            } else {
                                if (v13 < -38) {
                                    v2_2 = ((float) (((double) v2_2) * 4307583784117748259));
                                    v13 += 20;
                                }
                                v3_3 = vn.zalopay.ui.androidsvg.q.c[(- v13)];
                            }
                        } else {
                            v3_3 = vn.zalopay.ui.androidsvg.q.b[v13];
                        }
                        v2_2 *= v3_3;
                    }
                } else {
                    return 2143289344;
                }
            } else {
                return 2143289344;
            }
        } else {
            return 2143289344;
        }
    }
}
