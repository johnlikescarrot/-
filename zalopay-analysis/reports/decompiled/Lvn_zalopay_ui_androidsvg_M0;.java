package vn.zalopay.ui.androidsvg;
public final class M0 {
    public vn.zalopay.ui.androidsvg.t0 a;
    public vn.zalopay.ui.androidsvg.Y b;
    public boolean c;
    public int d;
    public boolean e;
    public vn.zalopay.ui.androidsvg.K0 f;
    public StringBuilder g;
    public boolean h;
    public StringBuilder i;

    public M0()
    {
        this.a = 0;
        this.b = 0;
        this.c = 0;
        this.e = 0;
        this.f = 0;
        this.g = 0;
        this.h = 0;
        this.i = 0;
        return;
    }

    public static android.graphics.Matrix A(String p19)
    {
        vn.zalopay.ui.androidsvg.SVGParseException v1_11 = new android.graphics.Matrix();
        String v2_2 = new vn.zalopay.ui.androidsvg.L(p19);
        v2_2.y();
        while (!v2_2.m()) {
            boolean v3_0 = ((String) v2_2.c);
            boolean v6_3 = 0;
            if (!v2_2.m()) {
                float v4_11 = v2_2.a;
                int v7_0 = v3_0.charAt(v4_11);
                while (((v7_0 >= 97) && (v7_0 <= 122)) || ((v7_0 >= 65) && (v7_0 <= 90))) {
                    v7_0 = v2_2.g();
                }
                int v8_4 = v2_2.a;
                while (vn.zalopay.ui.androidsvg.L.o(v7_0)) {
                    v7_0 = v2_2.g();
                }
                if (v7_0 != 40) {
                    v2_2.a = v4_11;
                } else {
                    v2_2.a = (v2_2.a + 1);
                    v6_3 = v3_0.substring(v4_11, v8_4);
                }
            }
            if (!v6_3) {
                throw new vn.zalopay.ui.androidsvg.SVGParseException("Bad transform function encountered in transform list: ".concat(p19));
            } else {
                float v11_1 = -1;
                switch (v6_3.hashCode()) {
                    case -1081239615:
                        if (v6_3.equals("matrix")) {
                            v11_1 = 0;
                        } else {
                        }
                        break;
                    case -925180581:
                        if (v6_3.equals("rotate")) {
                            v11_1 = 1;
                        } else {
                        }
                        break;
                    case 109250890:
                        if (v6_3.equals("scale")) {
                            v11_1 = 2;
                        } else {
                        }
                        break;
                    case 109493390:
                        if (v6_3.equals("skewX")) {
                            v11_1 = 3;
                        } else {
                        }
                        break;
                    case 109493391:
                        if (v6_3.equals("skewY")) {
                            v11_1 = 4;
                        } else {
                        }
                        break;
                    case 1052832078:
                        if (v6_3.equals("translate")) {
                            v11_1 = 5;
                        } else {
                        }
                        break;
                    default:
                }
                switch (v11_1) {
                    case 0:
                        v2_2.y();
                        boolean v6_8 = v2_2.q();
                        v2_2.x();
                        float v11_0 = v2_2.q();
                        v2_2.x();
                        float v14 = v2_2.q();
                        v2_2.x();
                        float v15 = v2_2.q();
                        v2_2.x();
                        float v16 = v2_2.q();
                        v2_2.x();
                        float v17 = v2_2.q();
                        v2_2.y();
                        if ((Float.isNaN(v17)) || (!v2_2.j(41))) {
                            throw new vn.zalopay.ui.androidsvg.SVGParseException("Invalid transform list: ".concat(p19));
                        } else {
                            android.graphics.Matrix v12_2 = new android.graphics.Matrix();
                            float[] v13_1 = new float[9];
                            v13_1[0] = v6_8;
                            v13_1[1] = v14;
                            v13_1[2] = v16;
                            v13_1[3] = v11_0;
                            v13_1[4] = v15;
                            v13_1[5] = v17;
                            v13_1[6] = 0;
                            v13_1[7] = 0;
                            v13_1[8] = 1065353216;
                            v12_2.setValues(v13_1);
                            v1_11.preConcat(v12_2);
                        }
                        break;
                    case 1:
                        v2_2.y();
                        boolean v3_2 = v2_2.q();
                        float v4_13 = v2_2.w();
                        boolean v5_8 = v2_2.w();
                        v2_2.y();
                        if ((Float.isNaN(v3_2)) || (!v2_2.j(41))) {
                            throw new vn.zalopay.ui.androidsvg.SVGParseException("Invalid transform list: ".concat(p19));
                        } else {
                            if (!Float.isNaN(v4_13)) {
                                if (Float.isNaN(v5_8)) {
                                    throw new vn.zalopay.ui.androidsvg.SVGParseException("Invalid transform list: ".concat(p19));
                                } else {
                                    v1_11.preRotate(v3_2, v4_13, v5_8);
                                }
                            } else {
                                v1_11.preRotate(v3_2);
                            }
                        }
                        break;
                    case 2:
                        v2_2.y();
                        boolean v3_1 = v2_2.q();
                        float v4_12 = v2_2.w();
                        v2_2.y();
                        if ((Float.isNaN(v3_1)) || (!v2_2.j(41))) {
                            throw new vn.zalopay.ui.androidsvg.SVGParseException("Invalid transform list: ".concat(p19));
                        } else {
                            if (!Float.isNaN(v4_12)) {
                                v1_11.preScale(v3_1, v4_12);
                            } else {
                                v1_11.preScale(v3_1, v3_1);
                            }
                        }
                        break;
                    case 3:
                        v2_2.y();
                        float v4_6 = v2_2.q();
                        v2_2.y();
                        if ((Float.isNaN(v4_6)) || (!v2_2.j(41))) {
                            throw new vn.zalopay.ui.androidsvg.SVGParseException("Invalid transform list: ".concat(p19));
                        } else {
                            v1_11.preSkew(((float) Math.tan(Math.toRadians(((double) v4_6)))), 0);
                        }
                        break;
                    case 4:
                        v2_2.y();
                        float v4_0 = v2_2.q();
                        v2_2.y();
                        if ((Float.isNaN(v4_0)) || (!v2_2.j(41))) {
                            throw new vn.zalopay.ui.androidsvg.SVGParseException("Invalid transform list: ".concat(p19));
                        } else {
                            v1_11.preSkew(0, ((float) Math.tan(Math.toRadians(((double) v4_0)))));
                        }
                        break;
                    case 5:
                        v2_2.y();
                        float v4_18 = v2_2.q();
                        boolean v5_9 = v2_2.w();
                        v2_2.y();
                        if ((Float.isNaN(v4_18)) || (!v2_2.j(41))) {
                            throw new vn.zalopay.ui.androidsvg.SVGParseException("Invalid transform list: ".concat(p19));
                        } else {
                            if (!Float.isNaN(v5_9)) {
                                v1_11.preTranslate(v4_18, v5_9);
                            } else {
                                v1_11.preTranslate(v4_18, 0);
                            }
                        }
                        break;
                    default:
                        throw new vn.zalopay.ui.androidsvg.SVGParseException(A1.a.i("Invalid transform list fn: ", v6_3, ")"));
                }
                if (v2_2.m()) {
                    break;
                }
                v2_2.x();
            }
        }
        return v1_11;
    }

    public static void E(vn.zalopay.ui.androidsvg.U p16, String p17, String p18)
    {
        try {
            if ((p18.length() != 0) && (!p18.equals("inherit"))) {
                java.util.ArrayList v2_4 = vn.zalopay.ui.androidsvg.J0.e(p17).ordinal();
                vn.zalopay.ui.androidsvg.E v4_0 = 5;
                if (v2_4 == 1) {
                    java.util.ArrayList v2_6 = 0;
                    if ((!"auto".equals(p18)) && (p18.startsWith("rect("))) {
                        long v3_15 = new vn.zalopay.ui.androidsvg.L(p18.substring(5));
                        v3_15.y();
                        long v1_38 = vn.zalopay.ui.androidsvg.M0.v(v3_15);
                        v3_15.x();
                        vn.zalopay.ui.androidsvg.E v4_3 = vn.zalopay.ui.androidsvg.M0.v(v3_15);
                        v3_15.x();
                        String v5_7 = vn.zalopay.ui.androidsvg.M0.v(v3_15);
                        v3_15.x();
                        float v6_4 = vn.zalopay.ui.androidsvg.M0.v(v3_15);
                        v3_15.y();
                        if ((v3_15.j(41)) || (v3_15.m())) {
                            v2_6 = new r2.j(v1_38, v4_3, v5_7, v6_4);
                        }
                    }
                    float v6_12 = v2_6;
                    p16.p = v6_12;
                    if (v6_12 != 0) {
                        p16.a = (p16.a | 1048576);
                    }
                } else {
                    if (v2_4 == 2) {
                        p16.x = vn.zalopay.ui.androidsvg.M0.s(p18);
                        p16.a = (p16.a | 268435456);
                        return;
                    } else {
                        if (v2_4 == 4) {
                            String v5_21;
                            if (!"nonzero".equals(p18)) {
                                if (!"evenodd".equals(p18)) {
                                    v5_21 = 0;
                                } else {
                                    v5_21 = 2;
                                }
                            } else {
                                v5_21 = 1;
                            }
                            p16.K = v5_21;
                            p16.a = (p16.a | 536870912);
                            return;
                        } else {
                            if (v2_4 == 5) {
                                p16.k = vn.zalopay.ui.androidsvg.M0.o(p18);
                                p16.a = (p16.a | 4096);
                                return;
                            } else {
                                if (v2_4 == 8) {
                                    String v5_0;
                                    if (p18.equals("ltr")) {
                                        v5_0 = 1;
                                    } else {
                                        if (p18.equals("rtl")) {
                                            v5_0 = 2;
                                        } else {
                                            v5_0 = 0;
                                        }
                                    }
                                    p16.I = v5_0;
                                    if (v5_0 != null) {
                                        p16.a = (p16.a | 68719476736);
                                        return;
                                    }
                                } else {
                                    if (v2_4 == 35) {
                                        p16.y = vn.zalopay.ui.androidsvg.M0.s(p18);
                                        p16.a = (p16.a | 1073741824);
                                        return;
                                    } else {
                                        if (v2_4 == 40) {
                                            p16.j = vn.zalopay.ui.androidsvg.M0.w(p18);
                                            p16.a = (p16.a | 2048);
                                            return;
                                        } else {
                                            if (v2_4 == 42) {
                                                String v5_2;
                                                switch (p18.hashCode()) {
                                                    case -1217487446:
                                                        if (p18.equals("hidden")) {
                                                            v5_2 = 0;
                                                        } else {
                                                            v5_2 = -1;
                                                        }
                                                    case -907680051:
                                                        if (p18.equals("scroll")) {
                                                            v5_2 = 1;
                                                        }
                                                        break;
                                                    case 3005871:
                                                        if (p18.equals("auto")) {
                                                            v5_2 = 2;
                                                        }
                                                        break;
                                                    case 466743410:
                                                        if (p18.equals("visible")) {
                                                            v5_2 = 3;
                                                        }
                                                        break;
                                                    default:
                                                }
                                                float v6_0;
                                                switch (v5_2) {
                                                    case 0:
                                                    case 1:
                                                        v6_0 = Boolean.FALSE;
                                                        break;
                                                    case 2:
                                                    case 3:
                                                        v6_0 = Boolean.TRUE;
                                                        break;
                                                    default:
                                                        v6_0 = 0;
                                                }
                                                p16.o = v6_0;
                                                if (v6_0 != 0) {
                                                    p16.a = (p16.a | 524288);
                                                    return;
                                                }
                                            } else {
                                                if (v2_4 == 78) {
                                                    String v5_4;
                                                    if (p18.equals("none")) {
                                                        v5_4 = 1;
                                                    } else {
                                                        if (p18.equals("non-scaling-stroke")) {
                                                            v5_4 = 2;
                                                        } else {
                                                            v5_4 = 0;
                                                        }
                                                    }
                                                    p16.L = v5_4;
                                                    if (v5_4 != null) {
                                                        p16.a = (p16.a | 34359738368);
                                                        return;
                                                    }
                                                } else {
                                                    int v10_3 = vn.zalopay.ui.androidsvg.w.a;
                                                    if (v2_4 == 58) {
                                                        if (!p18.equals("currentColor")) {
                                                            try {
                                                                p16.z = vn.zalopay.ui.androidsvg.M0.o(p18);
                                                            } catch (Object[] v0_1) {
                                                                ck.b.e("SVGParser", new Object[] {v0_1.getMessage()}));
                                                                return;
                                                            }
                                                        } else {
                                                            p16.z = v10_3;
                                                        }
                                                        p16.a = (p16.a | 2147483648);
                                                        return;
                                                    } else {
                                                        if (v2_4 == 59) {
                                                            p16.A = vn.zalopay.ui.androidsvg.M0.w(p18);
                                                            p16.a = (p16.a | 4294967296);
                                                            return;
                                                        } else {
                                                            if (v2_4 == 74) {
                                                                int v14_1;
                                                                switch (p18.hashCode()) {
                                                                    case -1074341483:
                                                                        if (p18.equals("middle")) {
                                                                            v14_1 = 0;
                                                                        } else {
                                                                            v14_1 = -1;
                                                                        }
                                                                    case 100571:
                                                                        if (p18.equals("end")) {
                                                                            v14_1 = 1;
                                                                        }
                                                                        break;
                                                                    case 109757538:
                                                                        if (p18.equals("start")) {
                                                                            v14_1 = 2;
                                                                        }
                                                                        break;
                                                                    default:
                                                                }
                                                                String v5_5;
                                                                switch (v14_1) {
                                                                    case 0:
                                                                        v5_5 = 2;
                                                                        break;
                                                                    case 1:
                                                                        v5_5 = 3;
                                                                        break;
                                                                    case 2:
                                                                        v5_5 = 1;
                                                                        break;
                                                                    default:
                                                                        v5_5 = 0;
                                                                }
                                                                p16.J = v5_5;
                                                                if (v5_5 != null) {
                                                                    p16.a = (p16.a | 262144);
                                                                    return;
                                                                }
                                                            } else {
                                                                if (v2_4 == 75) {
                                                                    int v14_2;
                                                                    switch (p18.hashCode()) {
                                                                        case -1171789332:
                                                                            if (p18.equals("line-through")) {
                                                                                v14_2 = 0;
                                                                            } else {
                                                                                v14_2 = -1;
                                                                            }
                                                                        case -1026963764:
                                                                            if (p18.equals("underline")) {
                                                                                v14_2 = 1;
                                                                            }
                                                                            break;
                                                                        case 3387192:
                                                                            if (p18.equals("none")) {
                                                                                v14_2 = 2;
                                                                            }
                                                                            break;
                                                                        case 93826908:
                                                                            if (p18.equals("blink")) {
                                                                                v14_2 = 3;
                                                                            }
                                                                            break;
                                                                        case 529818312:
                                                                            if (p18.equals("overline")) {
                                                                                v14_2 = 4;
                                                                            }
                                                                            break;
                                                                        default:
                                                                    }
                                                                    switch (v14_2) {
                                                                        case 0:
                                                                            v4_0 = 4;
                                                                            break;
                                                                        case 1:
                                                                            v4_0 = 2;
                                                                            break;
                                                                        case 2:
                                                                            v4_0 = 1;
                                                                        case 3:
                                                                            break;
                                                                        case 4:
                                                                            v4_0 = 3;
                                                                            break;
                                                                        default:
                                                                            v4_0 = 0;
                                                                    }
                                                                    p16.H = v4_0;
                                                                    if (v4_0 != null) {
                                                                        p16.a = (p16.a | 131072);
                                                                        return;
                                                                    }
                                                                } else {
                                                                    switch (v2_4) {
                                                                        case 14:
                                                                            if (p18.indexOf(124) < 0) {
                                                                                java.util.ArrayList v2_31 = new StringBuilder("|");
                                                                                v2_31.append(p18);
                                                                                v2_31.append(124);
                                                                                if ("|inline|block|list-item|run-in|compact|marker|table|inline-table|table-row-group|table-header-group|table-footer-group|table-row|table-column-group|table-column|table-cell|table-caption|none|".contains(v2_31.toString())) {
                                                                                    p16.t = Boolean.valueOf((p18.equals("none") ^ 1));
                                                                                    p16.a = (p16.a | 16777216);
                                                                                    return;
                                                                                }
                                                                            }
                                                                        case 15:
                                                                            long v1_66 = vn.zalopay.ui.androidsvg.M0.x(p18);
                                                                            p16.b = v1_66;
                                                                            if (v1_66 != 0) {
                                                                                p16.a = (p16.a | 1);
                                                                                return;
                                                                            }
                                                                        case 16:
                                                                            String v5_15;
                                                                            if (!"nonzero".equals(p18)) {
                                                                                if (!"evenodd".equals(p18)) {
                                                                                    v5_15 = 0;
                                                                                } else {
                                                                                    v5_15 = 2;
                                                                                }
                                                                            } else {
                                                                                v5_15 = 1;
                                                                            }
                                                                            p16.D = v5_15;
                                                                            if (v5_15 != null) {
                                                                                p16.a = (p16.a | 2);
                                                                                return;
                                                                            }
                                                                        case 17:
                                                                            long v1_60 = vn.zalopay.ui.androidsvg.M0.w(p18);
                                                                            p16.c = v1_60;
                                                                            if (v1_60 != 0) {
                                                                                p16.a = (p16.a | 4);
                                                                                return;
                                                                            }
                                                                        case 18:
                                                                            long v3_25 = new StringBuilder("|");
                                                                            v3_25.append(p18);
                                                                            v3_25.append(124);
                                                                            if ("|caption|icon|menu|message-box|small-caption|status-bar|".contains(v3_25.toString())) {
                                                                                long v3_29 = new vn.zalopay.ui.androidsvg.L(p18);
                                                                                long v1_53 = 0;
                                                                                float v6_3 = 0;
                                                                                String v5_9 = 0;
                                                                                do {
                                                                                    boolean v8_0 = v3_29.u(47, 0);
                                                                                    v3_29.y();
                                                                                    if (v8_0) {
                                                                                        if ((v1_53 == 0) || (v5_9 == null)) {
                                                                                            if (v8_0.equals("normal")) {
                                                                                                break;
                                                                                            }
                                                                                            if (v1_53 == 0) {
                                                                                                v1_53 = ((Integer) vn.zalopay.ui.androidsvg.I0.a.get(v8_0));
                                                                                                if (v1_53 != 0) {
                                                                                                    break;
                                                                                                }
                                                                                            }
                                                                                            if (v5_9 == null) {
                                                                                                String v5_12;
                                                                                                switch (v8_0.hashCode()) {
                                                                                                    case -1657669071:
                                                                                                        if (v8_0.equals("oblique")) {
                                                                                                            v5_12 = 0;
                                                                                                        } else {
                                                                                                            v5_12 = -1;
                                                                                                        }
                                                                                                        break;
                                                                                                    case -1178781136:
                                                                                                        if (v8_0.equals("italic")) {
                                                                                                            v5_12 = 1;
                                                                                                        }
                                                                                                        break;
                                                                                                    case -1039745817:
                                                                                                        if (v8_0.equals("normal")) {
                                                                                                            v5_12 = 2;
                                                                                                        }
                                                                                                        break;
                                                                                                    default:
                                                                                                }
                                                                                                switch (v5_12) {
                                                                                                    case 0:
                                                                                                        v5_9 = 3;
                                                                                                        break;
                                                                                                    case 1:
                                                                                                        v5_9 = 2;
                                                                                                        break;
                                                                                                    case 2:
                                                                                                        v5_9 = 1;
                                                                                                        break;
                                                                                                    default:
                                                                                                        v5_9 = 0;
                                                                                                }
                                                                                            }
                                                                                            if ((v6_3 == 0) && (v8_0.equals("small-caps"))) {
                                                                                                v6_3 = v8_0;
                                                                                                break;
                                                                                            }
                                                                                        }
                                                                                        try {
                                                                                            vn.zalopay.ui.androidsvg.E v4_6 = ((vn.zalopay.ui.androidsvg.E) vn.zalopay.ui.androidsvg.H0.a.get(v8_0));
                                                                                        } catch (vn.zalopay.ui.androidsvg.SVGParseException) {
                                                                                            v4_6 = 0;
                                                                                            if (v3_29.j(47)) {
                                                                                                v3_29.y();
                                                                                                float v6_8 = v3_29.t();
                                                                                                if (v6_8 != 0) {
                                                                                                    vn.zalopay.ui.androidsvg.M0.t(v6_8);
                                                                                                }
                                                                                                v3_29.y();
                                                                                            }
                                                                                            float v6_11;
                                                                                            if (!v3_29.m()) {
                                                                                                java.util.ArrayList v2_24 = v3_29.a;
                                                                                                v3_29.a = v3_29.b;
                                                                                                v6_11 = ((String) v3_29.c).substring(v2_24);
                                                                                            } else {
                                                                                                v6_11 = 0;
                                                                                            }
                                                                                            long v1_56;
                                                                                            p16.l = vn.zalopay.ui.androidsvg.M0.r(v6_11);
                                                                                            p16.m = v4_6;
                                                                                            if (v1_53 != 0) {
                                                                                                v1_56 = v1_53.intValue();
                                                                                            } else {
                                                                                                v1_56 = 400;
                                                                                            }
                                                                                            p16.n = Integer.valueOf(v1_56);
                                                                                            if (v5_9 == null) {
                                                                                                v5_9 = 1;
                                                                                            }
                                                                                            p16.G = v5_9;
                                                                                            p16.a = (p16.a | 122880);
                                                                                        }
                                                                                        if (v4_6 != null) {
                                                                                        } else {
                                                                                            v4_6 = vn.zalopay.ui.androidsvg.M0.t(v8_0);
                                                                                        }
                                                                                    }
                                                                                } while(v5_9 == null);
                                                                            }
                                                                            break;
                                                                        case 19:
                                                                            long v1_50 = vn.zalopay.ui.androidsvg.M0.r(p18);
                                                                            p16.l = v1_50;
                                                                            if (v1_50 != 0) {
                                                                                p16.a = (p16.a | 8192);
                                                                                return;
                                                                            }
                                                                        case 20:
                                                                            try {
                                                                                float v6_2;
                                                                                long v3_21 = ((vn.zalopay.ui.androidsvg.E) vn.zalopay.ui.androidsvg.H0.a.get(p18));
                                                                            } catch (vn.zalopay.ui.androidsvg.SVGParseException) {
                                                                                v6_2 = 0;
                                                                                p16.m = v6_2;
                                                                                if (v6_2 != 0) {
                                                                                    p16.a = (p16.a | 16384);
                                                                                    return;
                                                                                }
                                                                            }
                                                                            if (v3_21 != 0) {
                                                                                v6_2 = v3_21;
                                                                            } else {
                                                                                v6_2 = vn.zalopay.ui.androidsvg.M0.t(p18);
                                                                            }
                                                                        case 21:
                                                                            long v1_45 = ((Integer) vn.zalopay.ui.androidsvg.I0.a.get(p18));
                                                                            p16.n = v1_45;
                                                                            if (v1_45 != 0) {
                                                                                p16.a = (p16.a | 32768);
                                                                                return;
                                                                            }
                                                                        case 22:
                                                                            int v14_3;
                                                                            switch (p18.hashCode()) {
                                                                                case -1657669071:
                                                                                    if (p18.equals("oblique")) {
                                                                                        v14_3 = 0;
                                                                                    } else {
                                                                                        v14_3 = -1;
                                                                                    }
                                                                                    break;
                                                                                case -1178781136:
                                                                                    if (p18.equals("italic")) {
                                                                                        v14_3 = 1;
                                                                                    }
                                                                                    break;
                                                                                case -1039745817:
                                                                                    if (p18.equals("normal")) {
                                                                                        v14_3 = 2;
                                                                                    }
                                                                                    break;
                                                                                default:
                                                                            }
                                                                            String v5_6;
                                                                            switch (v14_3) {
                                                                                case 0:
                                                                                    v5_6 = 3;
                                                                                    break;
                                                                                case 1:
                                                                                    v5_6 = 2;
                                                                                    break;
                                                                                case 2:
                                                                                    v5_6 = 1;
                                                                                    break;
                                                                                default:
                                                                                    v5_6 = 0;
                                                                            }
                                                                            p16.G = v5_6;
                                                                            if (v5_6 != null) {
                                                                                p16.a = (p16.a | 65536);
                                                                                return;
                                                                            }
                                                                        default:
                                                                            switch (v2_4) {
                                                                                case 27:
                                                                                    int v14_4;
                                                                                    switch (p18.hashCode()) {
                                                                                        case -933002398:
                                                                                            if (p18.equals("optimizeQuality")) {
                                                                                                v14_4 = 0;
                                                                                            } else {
                                                                                                v14_4 = -1;
                                                                                            }
                                                                                            break;
                                                                                        case 3005871:
                                                                                            if (p18.equals("auto")) {
                                                                                                v14_4 = 1;
                                                                                            }
                                                                                            break;
                                                                                        case 362741610:
                                                                                            if (p18.equals("optimizeSpeed")) {
                                                                                                v14_4 = 2;
                                                                                            }
                                                                                            break;
                                                                                        default:
                                                                                    }
                                                                                    String v5_16;
                                                                                    switch (v14_4) {
                                                                                        case 0:
                                                                                            v5_16 = 2;
                                                                                            break;
                                                                                        case 1:
                                                                                            v5_16 = 1;
                                                                                            break;
                                                                                        case 2:
                                                                                            v5_16 = 3;
                                                                                            break;
                                                                                        default:
                                                                                            v5_16 = 0;
                                                                                    }
                                                                                    p16.M = v5_16;
                                                                                    if (v5_16 != null) {
                                                                                        p16.a = (p16.a | 137438953472);
                                                                                        return;
                                                                                    }
                                                                                case 28:
                                                                                    long v1_85 = vn.zalopay.ui.androidsvg.M0.s(p18);
                                                                                    p16.q = v1_85;
                                                                                    p16.r = v1_85;
                                                                                    p16.s = v1_85;
                                                                                    p16.a = (p16.a | 14680064);
                                                                                    return;
                                                                                case 29:
                                                                                    p16.q = vn.zalopay.ui.androidsvg.M0.s(p18);
                                                                                    p16.a = (p16.a | 2097152);
                                                                                    return;
                                                                                case 30:
                                                                                    p16.r = vn.zalopay.ui.androidsvg.M0.s(p18);
                                                                                    p16.a = (p16.a | 4194304);
                                                                                    return;
                                                                                case 31:
                                                                                    p16.s = vn.zalopay.ui.androidsvg.M0.s(p18);
                                                                                    p16.a = (p16.a | 8388608);
                                                                                    return;
                                                                                default:
                                                                                    switch (v2_4) {
                                                                                        case 62:
                                                                                            if (!p18.equals("currentColor")) {
                                                                                                try {
                                                                                                    p16.v = vn.zalopay.ui.androidsvg.M0.o(p18);
                                                                                                } catch (Object[] v0_4) {
                                                                                                    ck.b.e("SVGParser", new Object[] {v0_4.getMessage()}));
                                                                                                    return;
                                                                                                }
                                                                                            } else {
                                                                                                p16.v = v10_3;
                                                                                            }
                                                                                            p16.a = (p16.a | 67108864);
                                                                                            return;
                                                                                        case 63:
                                                                                            p16.w = vn.zalopay.ui.androidsvg.M0.w(p18);
                                                                                            p16.a = (p16.a | 134217728);
                                                                                            return;
                                                                                        case 64:
                                                                                            long v1_129 = vn.zalopay.ui.androidsvg.M0.x(p18);
                                                                                            p16.d = v1_129;
                                                                                            if (v1_129 != 0) {
                                                                                                p16.a = (p16.a | 8);
                                                                                                return;
                                                                                            }
                                                                                        case 65:
                                                                                            if (!"none".equals(p18)) {
                                                                                                float v6_16;
                                                                                                String v5_20 = new vn.zalopay.ui.androidsvg.L(p18);
                                                                                                v5_20.y();
                                                                                                if (!v5_20.m()) {
                                                                                                    long v1_116 = v5_20.r();
                                                                                                    if ((v1_116 != 0) && (!v1_116.i())) {
                                                                                                        float v6_14 = v1_116.a;
                                                                                                        java.util.ArrayList v7_6 = new java.util.ArrayList();
                                                                                                        v7_6.add(v1_116);
                                                                                                        while (!v5_20.m()) {
                                                                                                            v5_20.x();
                                                                                                            long v1_123 = v5_20.r();
                                                                                                            if ((v1_123 != 0) && (!v1_123.i())) {
                                                                                                                v7_6.add(v1_123);
                                                                                                                v6_14 += v1_123.a;
                                                                                                            } else {
                                                                                                                v6_16 = 0;
                                                                                                            }
                                                                                                        }
                                                                                                        if (v6_14 != 0) {
                                                                                                            long v1_121 = new vn.zalopay.ui.androidsvg.E[v7_6.size()];
                                                                                                            v6_16 = ((vn.zalopay.ui.androidsvg.E[]) v7_6.toArray(v1_121));
                                                                                                        }
                                                                                                    }
                                                                                                }
                                                                                                p16.h = v6_16;
                                                                                                if (v6_16 != 0) {
                                                                                                    p16.a = (p16.a | 512);
                                                                                                    return;
                                                                                                }
                                                                                            } else {
                                                                                                p16.h = 0;
                                                                                                p16.a = (p16.a | 512);
                                                                                                return;
                                                                                            }
                                                                                        case 66:
                                                                                            p16.i = vn.zalopay.ui.androidsvg.M0.t(p18);
                                                                                            p16.a = (p16.a | 1024);
                                                                                            return;
                                                                                        case 67:
                                                                                            String v5_18;
                                                                                            if (!"butt".equals(p18)) {
                                                                                                if (!"round".equals(p18)) {
                                                                                                    if (!"square".equals(p18)) {
                                                                                                        v5_18 = 0;
                                                                                                    } else {
                                                                                                        v5_18 = 3;
                                                                                                    }
                                                                                                } else {
                                                                                                    v5_18 = 2;
                                                                                                }
                                                                                            } else {
                                                                                                v5_18 = 1;
                                                                                            }
                                                                                            p16.E = v5_18;
                                                                                            if (v5_18 != null) {
                                                                                                p16.a = (p16.a | 64);
                                                                                                return;
                                                                                            }
                                                                                        case 68:
                                                                                            String v5_17;
                                                                                            if (!"miter".equals(p18)) {
                                                                                                if (!"round".equals(p18)) {
                                                                                                    if (!"bevel".equals(p18)) {
                                                                                                        v5_17 = 0;
                                                                                                    } else {
                                                                                                        v5_17 = 3;
                                                                                                    }
                                                                                                } else {
                                                                                                    v5_17 = 2;
                                                                                                }
                                                                                            } else {
                                                                                                v5_17 = 1;
                                                                                            }
                                                                                            p16.F = v5_17;
                                                                                            if (v5_17 != null) {
                                                                                                p16.a = (p16.a | 128);
                                                                                                return;
                                                                                            }
                                                                                        case 69:
                                                                                            p16.g = Float.valueOf(vn.zalopay.ui.androidsvg.M0.q(p18));
                                                                                            p16.a = (p16.a | 256);
                                                                                            return;
                                                                                        case 70:
                                                                                            long v1_97 = vn.zalopay.ui.androidsvg.M0.w(p18);
                                                                                            p16.e = v1_97;
                                                                                            if (v1_97 != 0) {
                                                                                                p16.a = (p16.a | 16);
                                                                                                return;
                                                                                            }
                                                                                        case 71:
                                                                                            p16.f = vn.zalopay.ui.androidsvg.M0.t(p18);
                                                                                            p16.a = (p16.a | 32);
                                                                                            return;
                                                                                        default:
                                                                                            switch (v2_4) {
                                                                                                case 88:
                                                                                                    if (!p18.equals("currentColor")) {
                                                                                                        try {
                                                                                                            p16.B = vn.zalopay.ui.androidsvg.M0.o(p18);
                                                                                                        } catch (Object[] v0_7) {
                                                                                                            ck.b.e("SVGParser", new Object[] {v0_7.getMessage()}));
                                                                                                            return;
                                                                                                        }
                                                                                                    } else {
                                                                                                        p16.B = v10_3;
                                                                                                    }
                                                                                                    p16.a = (p16.a | 8589934592);
                                                                                                    return;
                                                                                                case 89:
                                                                                                    p16.C = vn.zalopay.ui.androidsvg.M0.w(p18);
                                                                                                    p16.a = (p16.a | 17179869184);
                                                                                                    return;
                                                                                                case 90:
                                                                                                    if (p18.indexOf(124) < 0) {
                                                                                                        java.util.ArrayList v2_53 = new StringBuilder("|");
                                                                                                        v2_53.append(p18);
                                                                                                        v2_53.append(124);
                                                                                                        if ("|visible|hidden|collapse|".contains(v2_53.toString())) {
                                                                                                            p16.u = Boolean.valueOf(p18.equals("visible"));
                                                                                                            p16.a = (p16.a | 33554432);
                                                                                                            return;
                                                                                                        }
                                                                                                    }
                                                                                                default:
                                                                                            }
                                                                                    }
                                                                            }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch (vn.zalopay.ui.androidsvg.SVGParseException) {
        }
        return;
    }

    public static int b(float p1)
    {
        if (p1 >= 0) {
            if (p1 <= 1132396544) {
                return Math.round(p1);
            } else {
                return 255;
            }
        } else {
            return 0;
        }
    }

    public static int d(float p3, float p4, float p5)
    {
        int v0_0 = 0;
        int v3_1 = (p3 % 1135869952);
        if (p3 < 0) {
            v3_1 += 1135869952;
        }
        int v4_1;
        int v3_7 = (v3_1 / 1114636288);
        int v4_6 = (p4 / 1120403456);
        int v5_1 = (p5 / 1120403456);
        if (v4_6 >= 0) {
            v4_1 = Math.min(v4_6, 1065353216);
        } else {
            v4_1 = 0;
        }
        if (v5_1 >= 0) {
            v0_0 = Math.min(v5_1, 1065353216);
        }
        int v4_3;
        if (v0_0 > 1056964608) {
            v4_3 = ((v0_0 + v4_1) - (v4_1 * v0_0));
        } else {
            v4_3 = ((v4_1 + 1065353216) * v0_0);
        }
        int v0_2 = ((v0_0 * 1073741824) - v4_3);
        return (vn.zalopay.ui.androidsvg.M0.b((vn.zalopay.ui.androidsvg.M0.e(v0_2, v4_3, (v3_7 - 1073741824)) * 1132462080)) | ((vn.zalopay.ui.androidsvg.M0.b((vn.zalopay.ui.androidsvg.M0.e(v0_2, v4_3, (v3_7 + 1073741824)) * 1132462080)) << 16) | (vn.zalopay.ui.androidsvg.M0.b((vn.zalopay.ui.androidsvg.M0.e(v0_2, v4_3, v3_7) * 1132462080)) << 8)));
    }

    public static float e(float p2, float p3, float p4)
    {
        if (p4 < 0) {
            p4 += 1086324736;
        }
        if (p4 >= 1086324736) {
            p4 -= 1086324736;
        }
        if (p4 >= 1065353216) {
            if (p4 >= 1077936128) {
                if (p4 < 1082130432) {
                    p2 = androidx.activity.result.e.a(1082130432, p4, (p3 - p2), p2);
                }
                return p2;
            } else {
                return p3;
            }
        } else {
            return androidx.activity.result.e.a(p3, p2, p4, p2);
        }
    }

    public static void g(vn.zalopay.ui.androidsvg.W p7, org.xml.sax.Attributes p8)
    {
        int v1 = 0;
        while (v1 < p8.getLength()) {
            java.util.ArrayList v2_9 = p8.getValue(v1).trim();
            java.util.HashSet v3_0 = q9.H.d(p8, v1);
            if (v3_0 == 73) {
                java.util.HashSet v3_2 = new vn.zalopay.ui.androidsvg.L(v2_9);
                java.util.ArrayList v2_1 = new java.util.HashSet();
                while (!v3_2.m()) {
                    String v4_2 = v3_2.t();
                    int v5_1 = v4_2.indexOf(45);
                    if (v5_1 != -1) {
                        v4_2 = v4_2.substring(0, v5_1);
                    }
                    v2_1.add(new java.util.Locale(v4_2, "", "").getLanguage());
                    v3_2.y();
                }
                p7.h(v2_1);
            } else {
                switch (v3_0) {
                    case 52:
                        java.util.HashSet v3_8 = new vn.zalopay.ui.androidsvg.L(v2_9);
                        java.util.ArrayList v2_7 = new java.util.HashSet();
                        while (!v3_8.m()) {
                            String v4_7 = v3_8.t();
                            if (!v4_7.startsWith("http://www.w3.org/TR/SVG11/feature#")) {
                                v2_7.add("UNSUPPORTED");
                            } else {
                                v2_7.add(v4_7.substring(35));
                            }
                            v3_8.y();
                        }
                        p7.f(v2_7);
                        break;
                    case 53:
                        p7.j(v2_9);
                        break;
                    case 54:
                        java.util.HashSet v3_6 = new vn.zalopay.ui.androidsvg.L(v2_9);
                        java.util.ArrayList v2_5 = new java.util.HashSet();
                        while (!v3_6.m()) {
                            v2_5.add(v3_6.t());
                            v3_6.y();
                        }
                        p7.c(v2_5);
                        break;
                    case 55:
                        java.util.HashSet v3_4;
                        java.util.ArrayList v2_3 = vn.zalopay.ui.androidsvg.M0.r(v2_9);
                        java.util.HashSet v3_3 = new java.util.HashSet;
                        if (v2_3 == null) {
                            v3_4 = v3_3(0);
                        } else {
                            v3_4 = v3_3(v2_3);
                        }
                        p7.i(v3_4);
                        break;
                    default:
                }
            }
            v1++;
        }
        return;
    }

    public static void h(vn.zalopay.ui.androidsvg.a0 p3, org.xml.sax.Attributes p4)
    {
        String v0_0 = 0;
        while (v0_0 < p4.getLength()) {
            boolean v1_2 = p4.getQName(v0_0);
            if ((!v1_2.equals("id")) && (!v1_2.equals("xml:id"))) {
                if (!v1_2.equals("xml:space")) {
                    v0_0++;
                } else {
                    String v4_2 = p4.getValue(v0_0).trim();
                    if (!"default".equals(v4_2)) {
                        if (!"preserve".equals(v4_2)) {
                            throw new vn.zalopay.ui.androidsvg.SVGParseException(androidx.camera.camera2.internal.G.e("Invalid value for \"xml:space\" attribute: ", v4_2));
                        } else {
                            p3.d = Boolean.TRUE;
                            return;
                        }
                    } else {
                        p3.d = Boolean.FALSE;
                        return;
                    }
                }
            } else {
                p3.c = p4.getValue(v0_0).trim();
                break;
            }
        }
        return;
    }

    public static void i(vn.zalopay.ui.androidsvg.z p4, org.xml.sax.Attributes p5)
    {
        String v0_0 = 0;
        while (v0_0 < p5.getLength()) {
            Boolean v1_6 = p5.getValue(v0_0).trim();
            boolean v2_0 = q9.H.d(p5, v0_0);
            if (v2_0 == 23) {
                p4.j = vn.zalopay.ui.androidsvg.M0.A(v1_6);
            } else {
                if (v2_0 == 24) {
                    if (!"objectBoundingBox".equals(v1_6)) {
                        if (!"userSpaceOnUse".equals(v1_6)) {
                            throw new vn.zalopay.ui.androidsvg.SVGParseException("Invalid value for attribute gradientUnits");
                        } else {
                            p4.i = Boolean.TRUE;
                        }
                    } else {
                        p4.i = Boolean.FALSE;
                    }
                } else {
                    if (v2_0 == 26) {
                        if (("".equals(p5.getURI(v0_0))) || ("http://www.w3.org/1999/xlink".equals(p5.getURI(v0_0)))) {
                            p4.l = v1_6;
                        }
                    } else {
                        if (v2_0 == 60) {
                            try {
                                p4.k = q9.H.v(v1_6);
                            } catch (IllegalArgumentException) {
                                throw new vn.zalopay.ui.androidsvg.SVGParseException(A1.a.i("Invalid spreadMethod attribute. \"", v1_6, "\" is not a valid value."));
                            }
                        }
                    }
                }
            }
            v0_0++;
        }
        return;
    }

    public static void j(vn.zalopay.ui.androidsvg.O p8, org.xml.sax.Attributes p9, String p10)
    {
        int v1 = 0;
        while (v1 < p9.getLength()) {
            if (vn.zalopay.ui.androidsvg.J0.e(p9.getLocalName(v1)) == vn.zalopay.ui.androidsvg.J0.e) {
                java.util.Iterator v2_1 = new vn.zalopay.ui.androidsvg.L(p9.getValue(v1));
                int v3_3 = new java.util.ArrayList();
                v2_1.y();
                while (!v2_1.m()) {
                    float v4_5 = v2_1.q();
                    if (Float.isNaN(v4_5)) {
                        throw new vn.zalopay.ui.androidsvg.SVGParseException(A1.a.i("Invalid <", p10, "> points attribute. Non-coordinate content found in list."));
                    } else {
                        v2_1.x();
                        float[] v5_2 = v2_1.q();
                        if (Float.isNaN(v5_2)) {
                            throw new vn.zalopay.ui.androidsvg.SVGParseException(A1.a.i("Invalid <", p10, "> points attribute. There should be an even number of coordinates."));
                        } else {
                            v2_1.x();
                            v3_3.add(Float.valueOf(v4_5));
                            v3_3.add(Float.valueOf(v5_2));
                        }
                    }
                }
                java.util.Iterator v2_3 = new float[v3_3.size()];
                p8.o = v2_3;
                java.util.Iterator v2_4 = v3_3.iterator();
                int v3_4 = 0;
                while (v2_4.hasNext()) {
                    int v6_0 = (v3_4 + 1);
                    p8.o[v3_4] = ((Float) v2_4.next()).floatValue();
                    v3_4 = v6_0;
                }
            }
            v1++;
        }
        return;
    }

    public static void k(vn.zalopay.ui.androidsvg.a0 p7, org.xml.sax.Attributes p8)
    {
        int v1 = 0;
        while (v1 < p8.getLength()) {
            vn.zalopay.ui.androidsvg.U v2_17 = p8.getValue(v1).trim();
            if (v2_17.length() != 0) {
                String v3_1 = q9.H.d(p8, v1);
                if (v3_1 == null) {
                    String v3_3 = new vn.zalopay.ui.androidsvg.c(v2_17);
                    vn.zalopay.ui.androidsvg.U v2_0 = 0;
                    while (!v3_3.m()) {
                        String v4_1 = v3_3.t();
                        if (v4_1 != null) {
                            if (v2_0 == null) {
                                v2_0 = new java.util.ArrayList();
                            }
                            v2_0.add(v4_1);
                            v3_3.y();
                        }
                    }
                    p7.g = v2_0;
                } else {
                    if (v3_1 == 72) {
                        String v3_5 = new vn.zalopay.ui.androidsvg.L(v2_17.replaceAll("/\\*.*?\\*/", ""));
                        while(true) {
                            String v4_4 = v3_5.u(58, 0);
                            v3_5.y();
                            if (!v3_5.j(58)) {
                                break;
                            }
                            v3_5.y();
                            String v5_2 = v3_5.u(59, 1);
                            if (v5_2 == null) {
                                break;
                            }
                            v3_5.y();
                            if ((v3_5.m()) || (v3_5.j(59))) {
                                if (p7.f == null) {
                                    p7.f = new vn.zalopay.ui.androidsvg.U();
                                }
                                vn.zalopay.ui.androidsvg.M0.E(p7.f, v4_4, v5_2);
                                v3_5.y();
                            }
                        }
                    } else {
                        if (p7.e == null) {
                            p7.e = new vn.zalopay.ui.androidsvg.U();
                        }
                        vn.zalopay.ui.androidsvg.M0.E(p7.e, p8.getLocalName(v1), p8.getValue(v1).trim());
                    }
                }
            }
            v1++;
        }
        return;
    }

    public static void l(vn.zalopay.ui.androidsvg.o0 p4, org.xml.sax.Attributes p5)
    {
        int v0 = 0;
        while (v0 < p5.getLength()) {
            java.util.ArrayList v1_6 = p5.getValue(v0).trim();
            int v2 = q9.H.d(p5, v0);
            if (v2 == 9) {
                p4.p = vn.zalopay.ui.androidsvg.M0.u(v1_6);
            } else {
                if (v2 == 10) {
                    p4.q = vn.zalopay.ui.androidsvg.M0.u(v1_6);
                } else {
                    if (v2 == 82) {
                        p4.n = vn.zalopay.ui.androidsvg.M0.u(v1_6);
                    } else {
                        if (v2 == 83) {
                            p4.o = vn.zalopay.ui.androidsvg.M0.u(v1_6);
                        }
                    }
                }
            }
            v0++;
        }
        return;
    }

    public static void m(vn.zalopay.ui.androidsvg.C p3, org.xml.sax.Attributes p4)
    {
        int v0 = 0;
        while (v0 < p4.getLength()) {
            if (vn.zalopay.ui.androidsvg.J0.e(p4.getLocalName(v0)) == vn.zalopay.ui.androidsvg.J0.f) {
                p3.k(vn.zalopay.ui.androidsvg.M0.A(p4.getValue(v0)));
            }
            v0++;
        }
        return;
    }

    public static void n(vn.zalopay.ui.androidsvg.f0 p9, org.xml.sax.Attributes p10)
    {
        int v0 = 0;
        while (v0 < p10.getLength()) {
            float v1_8 = p10.getValue(v0).trim();
            float v2_0 = q9.H.d(p10, v0);
            if (v2_0 == 48) {
                vn.zalopay.ui.androidsvg.M0.y(p9, v1_8);
            } else {
                if (v2_0 == 80) {
                    float v2_2 = new vn.zalopay.ui.androidsvg.L(v1_8);
                    v2_2.y();
                    float v4 = v2_2.q();
                    v2_2.x();
                    float v5 = v2_2.q();
                    v2_2.x();
                    float v6 = v2_2.q();
                    v2_2.x();
                    float v7 = v2_2.q();
                    if ((Float.isNaN(v4)) || ((Float.isNaN(v5)) || ((Float.isNaN(v6)) || (Float.isNaN(v7))))) {
                        throw new vn.zalopay.ui.androidsvg.SVGParseException("Invalid viewBox definition - should have four numbers");
                    } else {
                        if (v6 < 0) {
                            throw new vn.zalopay.ui.androidsvg.SVGParseException("Invalid viewBox. width cannot be negative");
                        } else {
                            if (v7 < 0) {
                                throw new vn.zalopay.ui.androidsvg.SVGParseException("Invalid viewBox. height cannot be negative");
                            } else {
                                p9.o = new J0.b(v4, v5, v6, v7, 2);
                            }
                        }
                    }
                }
            }
            v0++;
        }
        return;
    }

    public static vn.zalopay.ui.androidsvg.v o(String p15)
    {
        int v2_0 = 5;
        if (p15.charAt(0) != 35) {
            int v0_1 = p15.toLowerCase(java.util.Locale.US);
            int v1_3 = v0_1.startsWith("rgba(");
            if ((v1_3 == 0) && (!v0_1.startsWith("rgb("))) {
                int v1_19 = v0_1.startsWith("hsla(");
                if ((v1_19 == 0) && (!v0_1.startsWith("hsl("))) {
                    vn.zalopay.ui.androidsvg.v v15_30 = ((Integer) vn.zalopay.ui.androidsvg.G0.a.get(v0_1));
                    if (v15_30 == null) {
                        throw new vn.zalopay.ui.androidsvg.SVGParseException("Invalid colour keyword: ".concat(v0_1));
                    } else {
                        return new vn.zalopay.ui.androidsvg.v(v15_30.intValue());
                    }
                } else {
                    if (v1_19 == 0) {
                        v2_0 = 4;
                    }
                    int v0_47 = new vn.zalopay.ui.androidsvg.L(p15.substring(v2_0));
                    v0_47.y();
                    int v2_10 = v0_47.q();
                    float v4_4 = v0_47.i(v2_10);
                    if (!Float.isNaN(v4_4)) {
                        v0_47.j(37);
                    }
                    int v8_7 = v0_47.i(v4_4);
                    if (!Float.isNaN(v8_7)) {
                        v0_47.j(37);
                    }
                    if (v1_19 == 0) {
                        v0_47.y();
                        if ((Float.isNaN(v8_7)) || (!v0_47.j(41))) {
                            throw new vn.zalopay.ui.androidsvg.SVGParseException("Bad hsl() colour value: ".concat(p15));
                        } else {
                            return new vn.zalopay.ui.androidsvg.v((vn.zalopay.ui.androidsvg.M0.d(v2_10, v4_4, v8_7) | -16777216));
                        }
                    } else {
                        int v1_34 = v0_47.i(v8_7);
                        v0_47.y();
                        if ((Float.isNaN(v1_34)) || (!v0_47.j(41))) {
                            throw new vn.zalopay.ui.androidsvg.SVGParseException("Bad hsla() colour value: ".concat(p15));
                        } else {
                            return new vn.zalopay.ui.androidsvg.v(((vn.zalopay.ui.androidsvg.M0.b((v1_34 * 1132462080)) << 24) | vn.zalopay.ui.androidsvg.M0.d(v2_10, v4_4, v8_7)));
                        }
                    }
                }
            } else {
                if (v1_3 == 0) {
                    v2_0 = 4;
                }
                int v0_7 = new vn.zalopay.ui.androidsvg.L(p15.substring(v2_0));
                v0_7.y();
                int v2_2 = v0_7.q();
                if ((!Float.isNaN(v2_2)) && (v0_7.j(37))) {
                    v2_2 = ((v2_2 * 1132462080) / 1120403456);
                }
                float v4_2 = v0_7.i(v2_2);
                if ((!Float.isNaN(v4_2)) && (v0_7.j(37))) {
                    v4_2 = ((v4_2 * 1132462080) / 1120403456);
                }
                float v9_2 = v0_7.i(v4_2);
                if ((!Float.isNaN(v9_2)) && (v0_7.j(37))) {
                    v9_2 = ((v9_2 * 1132462080) / 1120403456);
                }
                if (v1_3 == 0) {
                    v0_7.y();
                    if ((Float.isNaN(v9_2)) || (!v0_7.j(41))) {
                        throw new vn.zalopay.ui.androidsvg.SVGParseException("Bad rgb() colour value: ".concat(p15));
                    } else {
                        return new vn.zalopay.ui.androidsvg.v(((((vn.zalopay.ui.androidsvg.M0.b(v2_2) << 16) | -16777216) | (vn.zalopay.ui.androidsvg.M0.b(v4_2) << 8)) | vn.zalopay.ui.androidsvg.M0.b(v9_2)));
                    }
                } else {
                    int v1_9 = v0_7.i(v9_2);
                    v0_7.y();
                    if ((Float.isNaN(v1_9)) || (!v0_7.j(41))) {
                        throw new vn.zalopay.ui.androidsvg.SVGParseException("Bad rgba() colour value: ".concat(p15));
                    } else {
                        return new vn.zalopay.ui.androidsvg.v(((((vn.zalopay.ui.androidsvg.M0.b((v1_9 * 1132462080)) << 24) | (vn.zalopay.ui.androidsvg.M0.b(v2_2) << 16)) | (vn.zalopay.ui.androidsvg.M0.b(v4_2) << 8)) | vn.zalopay.ui.androidsvg.M0.b(v9_2)));
                    }
                }
            }
        } else {
            int v0_25 = p15.length();
            int v5_1 = 0;
            if (1 < v0_25) {
                long v6_1 = 0;
                int v8_3 = 1;
                while (v8_3 < v0_25) {
                    float v9_4 = p15.charAt(v8_3);
                    if ((v9_4 < 48) || (v9_4 > 57)) {
                        if ((v9_4 < 65) || (v9_4 > 70)) {
                            if ((v9_4 < 97) || (v9_4 > 102)) {
                                break;
                            }
                            long v6_2 = (v6_1 * 16);
                            float v9_5 = (v9_4 - 97);
                        } else {
                            v6_2 = (v6_1 * 16);
                            v9_5 = (v9_4 - 65);
                        }
                        v6_1 = ((v6_2 + ((long) v9_5)) + 10);
                    } else {
                        v6_1 = ((v6_1 * 16) + ((long) (v9_4 - 48)));
                    }
                    if (v6_1 <= 4294967295) {
                        v8_3++;
                    } else {
                        if (v5_1 == 0) {
                            throw new vn.zalopay.ui.androidsvg.SVGParseException("Bad hex colour value: ".concat(p15));
                        } else {
                            long v6_5 = v5_1.b;
                            int v1_22 = v5_1.a;
                            if (v1_22 == 4) {
                                vn.zalopay.ui.androidsvg.v v15_10 = ((int) v6_5);
                                int v0_27 = (v15_10 & 3840);
                                int v1_23 = (v15_10 & 240);
                                vn.zalopay.ui.androidsvg.v v15_11 = (v15_10 & 15);
                                return new vn.zalopay.ui.androidsvg.v((v15_11 | (((((v0_27 << 8) | (-16777216 | (v0_27 << 12))) | (v1_23 << 8)) | (v1_23 << 4)) | (v15_11 << 4))));
                            } else {
                                if (v1_22 == 5) {
                                    vn.zalopay.ui.androidsvg.v v15_13 = ((int) v6_5);
                                    int v0_34 = (61440 & v15_13);
                                    int v1_26 = (v15_13 & 3840);
                                    int v2_6 = (v15_13 & 240);
                                    vn.zalopay.ui.androidsvg.v v15_14 = (v15_13 & 15);
                                    return new vn.zalopay.ui.androidsvg.v(((((((((v15_14 << 24) | (v15_14 << 28)) | (v0_34 << 8)) | (v0_34 << 4)) | (v1_26 << 4)) | v1_26) | v2_6) | (v2_6 >> 4)));
                                } else {
                                    if (v1_22 == 7) {
                                        return new vn.zalopay.ui.androidsvg.v((((int) v6_5) | -16777216));
                                    } else {
                                        if (v1_22 != 9) {
                                            throw new vn.zalopay.ui.androidsvg.SVGParseException("Bad hex colour value: ".concat(p15));
                                        } else {
                                            int v0_40 = ((int) v6_5);
                                            return new vn.zalopay.ui.androidsvg.v(((v0_40 >> 8) | (v0_40 << 24)));
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                if (v8_3 != 1) {
                    v5_1 = new androidx.media3.exoplayer.upstream.t(v6_1, v8_3);
                }
            }
        }
    }

    public static float p(int p2, String p3)
    {
        vn.zalopay.ui.androidsvg.SVGParseException v2_2 = new vn.zalopay.ui.androidsvg.q().a(0, p2, p3);
        if (Float.isNaN(v2_2)) {
            throw new vn.zalopay.ui.androidsvg.SVGParseException(androidx.camera.camera2.internal.G.e("Invalid float value: ", p3));
        } else {
            return v2_2;
        }
    }

    public static float q(String p1)
    {
        String v0_0 = p1.length();
        if (v0_0 == null) {
            throw new vn.zalopay.ui.androidsvg.SVGParseException("Invalid float value (empty string)");
        } else {
            return vn.zalopay.ui.androidsvg.M0.p(v0_0, p1);
        }
    }

    public static java.util.ArrayList r(String p3)
    {
        vn.zalopay.ui.androidsvg.L v0_1 = new vn.zalopay.ui.androidsvg.L(p3);
        java.util.ArrayList v3_2 = 0;
        do {
            boolean v1_1 = v0_1.s();
            if (!v1_1) {
                v1_1 = v0_1.u(44, 1);
            }
            if (v1_1) {
                if (v3_2 == null) {
                    v3_2 = new java.util.ArrayList();
                }
                v3_2.add(v1_1);
                v0_1.x();
            } else {
                return v3_2;
            }
        } while(!v0_1.m());
        return v3_2;
    }

    public static String s(String p2)
    {
        if ((!p2.equals("none")) && (p2.startsWith("url("))) {
            if (!p2.endsWith(")")) {
                return p2.substring(4).trim();
            } else {
                return p2.substring(4, (p2.length() - 1)).trim();
            }
        } else {
            return 0;
        }
    }

    public static vn.zalopay.ui.androidsvg.E t(String p3)
    {
        if (p3.length() == 0) {
            throw new vn.zalopay.ui.androidsvg.SVGParseException("Invalid length value (empty string)");
        } else {
            String v1_5;
            vn.zalopay.ui.androidsvg.SVGParseException v0_1 = p3.length();
            String v1_0 = p3.charAt((v0_1 - 1));
            if (v1_0 != 37) {
                if ((v0_1 <= 2) || ((!Character.isLetter(v1_0)) || (!Character.isLetter(p3.charAt((v0_1 - 2)))))) {
                    v1_5 = 1;
                } else {
                    v0_1 -= 2;
                    try {
                        v1_5 = q9.H.w(p3.substring(v0_1).toLowerCase(java.util.Locale.US));
                    } catch (IllegalArgumentException) {
                        throw new vn.zalopay.ui.androidsvg.SVGParseException("Invalid length unit specifier: ".concat(p3));
                    }
                }
            } else {
                v0_1--;
                v1_5 = 9;
            }
            try {
                return new vn.zalopay.ui.androidsvg.E(vn.zalopay.ui.androidsvg.M0.p(v0_1, p3), v1_5);
            } catch (vn.zalopay.ui.androidsvg.SVGParseException v0_6) {
                throw new vn.zalopay.ui.androidsvg.SVGParseException("Invalid length value: ".concat(p3), v0_6);
            }
        }
    }

    public static java.util.ArrayList u(String p6)
    {
        if (p6.length() == 0) {
            throw new vn.zalopay.ui.androidsvg.SVGParseException("Invalid length list (empty string)");
        } else {
            String v0_6 = new java.util.ArrayList(1);
            vn.zalopay.ui.androidsvg.L v2_1 = new vn.zalopay.ui.androidsvg.L(p6);
            v2_1.y();
            while (!v2_1.m()) {
                vn.zalopay.ui.androidsvg.SVGParseException v6_2 = v2_1.q();
                if (!Float.isNaN(v6_2)) {
                    int v3_1 = v2_1.v();
                    if (v3_1 == 0) {
                        v3_1 = 1;
                    }
                    v0_6.add(new vn.zalopay.ui.androidsvg.E(v6_2, v3_1));
                    v2_1.x();
                } else {
                    String v0_2 = new StringBuilder("Invalid length list value: ");
                    int v3_4 = ((String) v2_1.c);
                    vn.zalopay.ui.androidsvg.E v4_2 = v2_1.a;
                    while ((!v2_1.m()) && (!vn.zalopay.ui.androidsvg.L.o(v3_4.charAt(v2_1.a)))) {
                        v2_1.a = (v2_1.a + 1);
                    }
                    String v1_1 = v3_4.substring(v4_2, v2_1.a);
                    v2_1.a = v4_2;
                    v0_2.append(v1_1);
                    throw new vn.zalopay.ui.androidsvg.SVGParseException(v0_2.toString());
                }
            }
            return v0_6;
        }
    }

    public static vn.zalopay.ui.androidsvg.E v(vn.zalopay.ui.androidsvg.L p1)
    {
        if (!p1.k("auto")) {
            return p1.r();
        } else {
            return new vn.zalopay.ui.androidsvg.E(0);
        }
    }

    public static Float w(String p2)
    {
        try {
            Float v2_1 = vn.zalopay.ui.androidsvg.M0.q(p2);
            float v0_0 = 0;
        } catch (vn.zalopay.ui.androidsvg.SVGParseException) {
            return 0;
        }
        if (v2_1 >= 0) {
            v0_0 = Math.min(v2_1, 1065353216);
        } else {
        }
        return Float.valueOf(v0_0);
    }

    public static com.caverock.androidsvg.d0 x(String p8)
    {
        vn.zalopay.ui.androidsvg.v v3 = vn.zalopay.ui.androidsvg.v.c;
        vn.zalopay.ui.androidsvg.w v4 = vn.zalopay.ui.androidsvg.w.a;
        vn.zalopay.ui.androidsvg.v v5 = 0;
        if (!p8.startsWith("url(")) {
            if (p8.equals("none")) {
                return v3;
            } else {
                if (p8.equals("currentColor")) {
                    return v4;
                } else {
                    try {
                        return vn.zalopay.ui.androidsvg.M0.o(p8);
                    } catch (vn.zalopay.ui.androidsvg.SVGParseException) {
                        return 0;
                    }
                }
            }
        } else {
            boolean v0_5 = p8.indexOf(")");
            if (v0_5 == -1) {
                return new vn.zalopay.ui.androidsvg.J(p8.substring(4).trim(), 0);
            } else {
                String v6_2 = p8.substring(4, v0_5).trim();
                vn.zalopay.ui.androidsvg.v v8_5 = p8.substring((v0_5 + 1)).trim();
                if (v8_5.length() > 0) {
                    if (!v8_5.equals("none")) {
                        if (v8_5.equals("currentColor")) {
                            v3 = v4;
                        } else {
                            try {
                                v3 = vn.zalopay.ui.androidsvg.M0.o(v8_5);
                            } catch (vn.zalopay.ui.androidsvg.SVGParseException) {
                                v3 = 0;
                            }
                        }
                    }
                    v5 = v3;
                }
                return new vn.zalopay.ui.androidsvg.J(v6_2, v5);
            }
        }
    }

    public static void y(vn.zalopay.ui.androidsvg.d0 p3, String p4)
    {
        String v0_1 = new vn.zalopay.ui.androidsvg.L(p4);
        v0_1.y();
        vn.zalopay.ui.androidsvg.r v1_0 = v0_1.t();
        if ("defer".equals(v1_0)) {
            v0_1.y();
            v1_0 = v0_1.t();
        }
        String v4_1;
        vn.zalopay.ui.androidsvg.r v1_2 = ((vn.zalopay.ui.androidsvg.r) vn.zalopay.ui.androidsvg.F0.a.get(v1_0));
        v0_1.y();
        if (v0_1.m()) {
            v4_1 = 0;
        } else {
            String v0_2 = v0_1.t();
            v0_2.getClass();
            if (v0_2.equals("meet")) {
                v4_1 = 1;
            } else {
                if (!v0_2.equals("slice")) {
                    throw new vn.zalopay.ui.androidsvg.SVGParseException("Invalid preserveAspectRatio definition: ".concat(p4));
                } else {
                    v4_1 = 2;
                }
            }
        }
        p3.n = new vn.zalopay.ui.androidsvg.s(v1_2, v4_1);
        return;
    }

    public static java.util.HashMap z(vn.zalopay.ui.androidsvg.L p5)
    {
        java.util.HashMap v0_1 = new java.util.HashMap();
        p5.y();
        String v3 = p5.u(61, 0);
        while (v3 != null) {
            p5.j(61);
            v0_1.put(v3, p5.s());
            p5.y();
            v3 = p5.u(61, 0);
        }
        return v0_1;
    }

    public final void B(java.io.InputStream p4)
    {
        try {
            vn.zalopay.ui.androidsvg.SVGParseException v0_0 = javax.xml.parsers.SAXParserFactory.newInstance();
            v0_0.setFeature("http://xml.org/sax/features/external-general-entities", 0);
            v0_0.setFeature("http://xml.org/sax/features/external-parameter-entities", 0);
            vn.zalopay.ui.androidsvg.SVGParseException v0_1 = v0_0.newSAXParser().getXMLReader();
            String v1_1 = new com.caverock.androidsvg.I0(1, this);
            v0_1.setContentHandler(v1_1);
            v0_1.setProperty("http://xml.org/sax/properties/lexical-handler", v1_1);
            v0_1.parse(new org.xml.sax.InputSource(p4));
            return;
        } catch (java.io.IOException v4_3) {
            throw new vn.zalopay.ui.androidsvg.SVGParseException("XML parser problem", v4_3);
        } catch (java.io.IOException v4_2) {
            throw new vn.zalopay.ui.androidsvg.SVGParseException("SVG parse error", v4_2);
        } catch (java.io.IOException v4_1) {
            throw new vn.zalopay.ui.androidsvg.SVGParseException("Stream error", v4_1);
        }
    }

    public final void C(java.io.InputStream p9)
    {
        Object[] v0_0 = android.util.Xml.newPullParser();
        String v1_3 = new vn.zalopay.ui.androidsvg.L0(v0_0);
        v0_0.setFeature("http://xmlpull.org/v1/doc/features.html#process-docdecl", 0);
        v0_0.setFeature("http://xmlpull.org/v1/doc/features.html#process-namespaces", 1);
        v0_0.setInput(p9, 0);
        String v2_3 = v0_0.getEventType();
        while (v2_3 != 1) {
            if (v2_3 == null) {
                this.a = new vn.zalopay.ui.androidsvg.t0();
            } else {
                if (v2_3 == 8) {
                    v0_0.getText();
                    String v2_7 = new vn.zalopay.ui.androidsvg.L(v0_0.getText());
                    String v5_3 = v2_7.t();
                    vn.zalopay.ui.androidsvg.M0.z(v2_7);
                    v5_3.equals("xml-stylesheet");
                } else {
                    if (v2_3 == 10) {
                        if ((this.a.a == null) && (v0_0.getText().contains("<!ENTITY "))) {
                            p9.reset();
                            this.B(p9);
                            return;
                        }
                    } else {
                        if (v2_3 == 2) {
                            String v2_14 = v0_0.getName();
                            if (v0_0.getPrefix() != null) {
                                String v6_3 = new StringBuilder();
                                v6_3.append(v0_0.getPrefix());
                                v6_3.append(58);
                                v6_3.append(v2_14);
                                v2_14 = v6_3.toString();
                            }
                            this.F(v0_0.getNamespace(), v0_0.getName(), v2_14, v1_3);
                        } else {
                            if (v2_3 == 3) {
                                String v2_16 = v0_0.getName();
                                if (v0_0.getPrefix() != null) {
                                    String v6_7 = new StringBuilder();
                                    v6_7.append(v0_0.getPrefix());
                                    v6_7.append(58);
                                    v6_7.append(v2_16);
                                    v2_16 = v6_7.toString();
                                }
                                this.c(v0_0.getNamespace(), v0_0.getName(), v2_16);
                            } else {
                                if (v2_3 == 4) {
                                    String v2_17 = new int[2];
                                    this.H(v0_0.getTextCharacters(v2_17), v2_17[0], v2_17[1]);
                                } else {
                                    if (v2_3 == 5) {
                                        this.G(v0_0.getText());
                                    } else {
                                    }
                                }
                            }
                        }
                    }
                }
            }
            v2_3 = v0_0.nextToken();
        }
        return;
    }

    public final void D(org.xml.sax.Attributes p25)
    {
        Integer v1_0 = p25;
        int v2_6 = this.b;
        if (v2_6 == 0) {
            throw new vn.zalopay.ui.androidsvg.SVGParseException("Invalid document. Root element must be <svg>");
        } else {
            vn.zalopay.ui.androidsvg.K v3_1 = new vn.zalopay.ui.androidsvg.K();
            v3_1.a = this.a;
            v3_1.b = v2_6;
            vn.zalopay.ui.androidsvg.M0.h(v3_1, p25);
            vn.zalopay.ui.androidsvg.M0.k(v3_1, p25);
            vn.zalopay.ui.androidsvg.M0.m(v3_1, p25);
            vn.zalopay.ui.androidsvg.M0.g(v3_1, p25);
            int v2_4 = 0;
            int v4_1 = 0;
            while (v4_1 < v1_0.getLength()) {
                StringBuilder v22_0;
                int v5_8 = v1_0.getValue(v4_1).trim();
                StringBuilder v6_2 = q9.H.d(v1_0, v4_1);
                int v8_0 = 0;
                if (v6_2 == 13) {
                    StringBuilder v6_4 = new vn.zalopay.ui.androidsvg.L(v5_8);
                    vn.zalopay.ui.androidsvg.L v9_1 = new vn.zalopay.ui.androidsvg.L();
                    v9_1.a = v2_4;
                    v9_1.b = v2_4;
                    int v5_12 = new byte[8];
                    v9_1.c = v5_12;
                    int v5_17 = new float[16];
                    v9_1.d = v5_17;
                    if (!v6_4.m()) {
                        int v5_4 = v6_4.p().intValue();
                        if ((v5_4 == 77) || (v5_4 == 109)) {
                            int v7_0 = 0;
                            int v11_2 = 0;
                            float v12_0 = 0;
                            float v13_0 = 0;
                            float v17 = 0;
                            float v18 = 0;
                            while(true) {
                                float v12_1;
                                boolean v14_1;
                                int v15_3;
                                v6_4.y();
                                int v15_5 = 108;
                                int v19 = v8_0;
                                switch (v5_4) {
                                    case 65:
                                    case 97:
                                        float v16_7;
                                        float v10_2 = v6_4.q();
                                        int v11_8 = v6_4.i(v10_2);
                                        float v13_6 = v12_0;
                                        float v12_2 = v6_4.i(v11_8);
                                        boolean v14_8 = v6_4.h(Float.valueOf(v12_2));
                                        int v15_8 = v6_4.h(v14_8);
                                        if (v15_8 != 0) {
                                            v6_4.x();
                                            v16_7 = v6_4.q();
                                        } else {
                                            v16_7 = 2143289344;
                                        }
                                        v22_0 = v4_1;
                                        int v4_2 = v16_7;
                                        float v16_8 = v6_4.i(v4_2);
                                        if ((!Float.isNaN(v16_8)) && ((v10_2 >= v19) && (v11_8 >= v19))) {
                                            int v4_4;
                                            int v23_3 = v4_2;
                                            if (v5_4 != 97) {
                                                v4_4 = v23_3;
                                            } else {
                                                v4_4 = (v23_3 + v7_0);
                                                v16_8 += v13_6;
                                            }
                                            int v15_9 = v4_4;
                                            v9_1.e(v10_2, v11_8, v12_2, v14_8.booleanValue(), v15_8.booleanValue(), v15_9, v16_8);
                                            v7_0 = v15_9;
                                            v11_2 = v7_0;
                                            v12_0 = v16_8;
                                            v13_0 = v12_0;
                                        } else {
                                            int v4_7 = new StringBuilder("Bad path coords for ");
                                            v4_7.append(((char) v5_4));
                                            v4_7.append(" path segment");
                                            ck.b.e("SVGParser", new Object[] {v4_7.toString()}));
                                        }
                                        break;
                                    case 67:
                                    case 99:
                                        int v11 = 109;
                                        float v13_5 = v6_4.q();
                                        boolean v14_6 = v6_4.i(v13_5);
                                        int v15_7 = v6_4.i(v14_6);
                                        int v11_6 = v6_4.i(v15_7);
                                        Integer v1_23 = v6_4.i(v11_6);
                                        float v16_6 = v6_4.i(v1_23);
                                        if (!Float.isNaN(v16_6)) {
                                            if (v5_4 == 99) {
                                                v1_23 += v7_0;
                                                v16_6 += v12_0;
                                                v13_5 += v7_0;
                                                v14_6 += v12_0;
                                                v15_7 += v7_0;
                                                v11_6 += v12_0;
                                            }
                                            v12_1 = v15_7;
                                            v15_3 = v16_6;
                                            v13_0 = v11_6;
                                            v14_1 = v1_23;
                                            v9_1.c(v13_5, v14_6, v12_1, v13_0, v14_1, v15_3);
                                            v11_2 = v12_1;
                                            v7_0 = v14_1;
                                            v12_0 = v15_3;
                                            v22_0 = v4_1;
                                        } else {
                                            Integer v1_25 = new StringBuilder("Bad path coords for ");
                                            v1_25.append(((char) v5_4));
                                            v1_25.append(" path segment");
                                            ck.b.e("SVGParser", new Object[] {v1_25.toString()}));
                                            v22_0 = v4_1;
                                        }
                                        break;
                                    case 72:
                                    case 104:
                                        boolean v14_5 = v6_4.q();
                                        if (!Float.isNaN(v14_5)) {
                                            if (v5_4 == 104) {
                                                v14_5 += v7_0;
                                            }
                                            v7_0 = v14_5;
                                            v9_1.b(v7_0, v12_0);
                                            v22_0 = v4_1;
                                            v11_2 = v7_0;
                                        } else {
                                            Integer v1_20 = new StringBuilder("Bad path coords for ");
                                            v1_20.append(((char) v5_4));
                                            v1_20.append(" path segment");
                                            ck.b.e("SVGParser", new Object[] {v1_20.toString()}));
                                        }
                                        break;
                                    case 76:
                                    case 108:
                                        float v13_4 = v6_4.q();
                                        boolean v14_4 = v6_4.i(v13_4);
                                        if (!Float.isNaN(v14_4)) {
                                            if (v5_4 == 108) {
                                                v13_4 += v7_0;
                                                v14_4 += v12_0;
                                            }
                                            v7_0 = v13_4;
                                            v12_0 = v14_4;
                                            v9_1.b(v7_0, v12_0);
                                            v22_0 = v4_1;
                                            v13_0 = v12_0;
                                        } else {
                                            Integer v1_16 = new StringBuilder("Bad path coords for ");
                                            v1_16.append(((char) v5_4));
                                            v1_16.append(" path segment");
                                            ck.b.e("SVGParser", new Object[] {v1_16.toString()}));
                                        }
                                        break;
                                    case 77:
                                    case 109:
                                        float v13_3 = v6_4.q();
                                        boolean v14_3 = v6_4.i(v13_3);
                                        if (!Float.isNaN(v14_3)) {
                                            if ((v5_4 == 109) && (v9_1.a != 0)) {
                                                v13_3 += v7_0;
                                                v14_3 += v12_0;
                                            }
                                            v7_0 = v13_3;
                                            v12_0 = v14_3;
                                            v9_1.a(v7_0, v12_0);
                                            if (v5_4 != 109) {
                                                v15_5 = 76;
                                            }
                                            v22_0 = v4_1;
                                            v17 = v7_0;
                                            v13_0 = v12_0;
                                            v18 = v13_0;
                                            v5_4 = v15_5;
                                            v11_2 = v17;
                                        } else {
                                            Integer v1_12 = new StringBuilder("Bad path coords for ");
                                            v1_12.append(((char) v5_4));
                                            v1_12.append(" path segment");
                                            ck.b.e("SVGParser", new Object[] {v1_12.toString()}));
                                        }
                                        break;
                                    case 81:
                                    case 113:
                                        float v13_2 = v6_4.q();
                                        boolean v14_2 = v6_4.i(v13_2);
                                        int v15_4 = v6_4.i(v14_2);
                                        float v16_3 = v6_4.i(v15_4);
                                        if (!Float.isNaN(v16_3)) {
                                            if (v5_4 == 113) {
                                                v15_4 += v7_0;
                                                v16_3 += v12_0;
                                                v13_2 += v7_0;
                                                v14_2 += v12_0;
                                            }
                                            v7_0 = v15_4;
                                            v12_0 = v16_3;
                                            v9_1.d(v13_2, v14_2, v7_0, v12_0);
                                            v22_0 = v4_1;
                                            v11_2 = v13_2;
                                            v13_0 = v14_2;
                                        } else {
                                            Integer v1_7 = new StringBuilder("Bad path coords for ");
                                            v1_7.append(((char) v5_4));
                                            v1_7.append(" path segment");
                                            ck.b.e("SVGParser", new Object[] {v1_7.toString()}));
                                        }
                                        break;
                                    case 83:
                                    case 115:
                                        int v15_2 = ((v7_0 * 1073741824) - v11_2);
                                        int v11_0 = ((1073741824 * v12_0) - v13_0);
                                        float v13_1 = v6_4.q();
                                        boolean v14_0 = v6_4.i(v13_1);
                                        Integer v1_1 = v6_4.i(v14_0);
                                        float v21_1 = v6_4.i(v1_1);
                                        if (!Float.isNaN(v21_1)) {
                                            if (v5_4 == 115) {
                                                v1_1 += v7_0;
                                                v21_1 += v12_0;
                                                v13_1 += v7_0;
                                                v14_0 += v12_0;
                                            }
                                            v12_1 = v13_1;
                                            v13_0 = v14_0;
                                            v15_3 = v21_1;
                                            v14_1 = v1_1;
                                            v9_1.c(v15_2, v11_0, v12_1, v13_0, v14_1, v15_3);
                                        } else {
                                            Integer v1_3 = new StringBuilder("Bad path coords for ");
                                            v1_3.append(((char) v5_4));
                                            v1_3.append(" path segment");
                                            ck.b.e("SVGParser", new Object[] {v1_3.toString()}));
                                        }
                                        break;
                                    case 84:
                                    case 116:
                                        v11_2 = ((v7_0 * 1073741824) - v11_2);
                                        v13_0 = ((1073741824 * v12_0) - v13_0);
                                        int v15_0 = v6_4.q();
                                        float v16_1 = v6_4.i(v15_0);
                                        if (!Float.isNaN(v16_1)) {
                                            if (v5_4 == 116) {
                                                v15_0 += v7_0;
                                                v16_1 += v12_0;
                                            }
                                            v7_0 = v15_0;
                                            v12_0 = v16_1;
                                            v9_1.d(v11_2, v13_0, v7_0, v12_0);
                                            v22_0 = v4_1;
                                        } else {
                                            StringBuilder v6_1 = new StringBuilder("Bad path coords for ");
                                            v6_1.append(((char) v5_4));
                                            v6_1.append(" path segment");
                                            ck.b.e("SVGParser", new Object[] {v6_1.toString()}));
                                        }
                                        break;
                                    case 86:
                                    case 118:
                                        float v13_8 = v6_4.q();
                                        if (Float.isNaN(v13_8)) {
                                            break;
                                        }
                                        if (v5_4 == 118) {
                                            v13_8 += v12_0;
                                        }
                                        v12_0 = v13_8;
                                        v9_1.b(v7_0, v12_0);
                                        v22_0 = v4_1;
                                        v13_0 = v12_0;
                                        break;
                                    case 90:
                                    case 122:
                                        v9_1.close();
                                        v22_0 = v4_1;
                                        v7_0 = v17;
                                        v11_2 = v7_0;
                                        v12_0 = v18;
                                        break;
                                    default:
                                }
                                v6_4.x();
                                if (!v6_4.m()) {
                                    Integer v1_30 = v6_4.a;
                                    if (v1_30 != v6_4.b) {
                                        Integer v1_31 = ((String) v6_4.c).charAt(v1_30);
                                        if (((v1_31 >= 97) && (v1_31 <= 122)) || ((v1_31 >= 65) && (v1_31 <= 90))) {
                                            v5_4 = v6_4.p().intValue();
                                        }
                                    }
                                    v8_0 = v19;
                                    v4_1 = v22_0;
                                }
                            }
                            StringBuilder v6_6 = new StringBuilder("Bad path coords for ");
                            v6_6.append(((char) v5_4));
                            v6_6.append(" path segment");
                            ck.b.e("SVGParser", new Object[] {v6_6.toString()}));
                        }
                    }
                    v3_1.o = v9_1;
                } else {
                    if ((v6_2 == 43) && (vn.zalopay.ui.androidsvg.M0.q(v5_8) < 0)) {
                        throw new vn.zalopay.ui.androidsvg.SVGParseException("Invalid <path> element. pathLength cannot be negative");
                    } else {
                        v22_0 = v4_1;
                    }
                }
                v4_1 = (v22_0 + 1);
                v1_0 = p25;
                v2_4 = 0;
            }
            this.b.m(v3_1);
            return;
        }
    }

    public final void F(String p17, String p18, String p19, org.xml.sax.Attributes p20)
    {
        if (!this.c) {
            if (("http://www.w3.org/2000/svg".equals(p17)) || ("".equals(p17))) {
                NumberFormatException v0_89;
                if (p18.length() <= 0) {
                    v0_89 = p19;
                } else {
                    v0_89 = p18;
                }
                NumberFormatException v0_94 = ((vn.zalopay.ui.androidsvg.K0) vn.zalopay.ui.androidsvg.K0.e.get(v0_89));
                if (v0_94 == null) {
                    v0_94 = vn.zalopay.ui.androidsvg.K0.d;
                }
                switch (v0_94.ordinal()) {
                    case 0:
                        NumberFormatException v0_71 = new vn.zalopay.ui.androidsvg.V();
                        v0_71.a = this.a;
                        v0_71.b = this.b;
                        vn.zalopay.ui.androidsvg.M0.h(v0_71, p20);
                        vn.zalopay.ui.androidsvg.M0.k(v0_71, p20);
                        vn.zalopay.ui.androidsvg.M0.g(v0_71, p20);
                        vn.zalopay.ui.androidsvg.M0.n(v0_71, p20);
                        float v6_6 = 0;
                        while (v6_6 < p20.getLength()) {
                            String v3_35 = p20.getValue(v6_6).trim();
                            boolean v4_15 = q9.H.d(p20, v6_6);
                            if (v4_15 == 25) {
                                String v3_36 = vn.zalopay.ui.androidsvg.M0.t(v3_35);
                                v0_71.s = v3_36;
                                if (v3_36.i()) {
                                    throw new vn.zalopay.ui.androidsvg.SVGParseException("Invalid <svg> element. height cannot be negative");
                                }
                            } else {
                                if (v4_15 != 79) {
                                    switch (v4_15) {
                                        case 81:
                                            String v3_40 = vn.zalopay.ui.androidsvg.M0.t(v3_35);
                                            v0_71.r = v3_40;
                                            if (v3_40.i()) {
                                                throw new vn.zalopay.ui.androidsvg.SVGParseException("Invalid <svg> element. width cannot be negative");
                                            }
                                            break;
                                        case 82:
                                            v0_71.p = vn.zalopay.ui.androidsvg.M0.t(v3_35);
                                            break;
                                        case 83:
                                            v0_71.q = vn.zalopay.ui.androidsvg.M0.t(v3_35);
                                            break;
                                        default:
                                    }
                                    float v6_25++;
                                    while (v6_25 < p20.getLength()) {
                                        String v3_146 = p20.getValue(v6_25).trim();
                                        switch (q9.H.d(p20, v6_25)) {
                                            case 84:
                                                NumberFormatException v0_229.m = vn.zalopay.ui.androidsvg.M0.t(v3_146);
                                                break;
                                            case 85:
                                                v0_229.n = vn.zalopay.ui.androidsvg.M0.t(v3_146);
                                                break;
                                            case 86:
                                                v0_229.o = vn.zalopay.ui.androidsvg.M0.t(v3_146);
                                                break;
                                            case 87:
                                                v0_229.p = vn.zalopay.ui.androidsvg.M0.t(v3_146);
                                                break;
                                            default:
                                        }
                                    }
                                    this.b.m(v0_229);
                                    this.b = v0_229;
                                    return;
                                }
                            }
                            v6_6++;
                        }
                        vn.zalopay.ui.androidsvg.SVGParseException v2_8 = this.b;
                        if (v2_8 != null) {
                            v2_8.m(v0_71);
                        } else {
                            this.a.a = v0_71;
                        }
                        this.b = v0_71;
                        return;
                    case 1:
                    case 7:
                        if (this.b == null) {
                            throw new vn.zalopay.ui.androidsvg.SVGParseException("Invalid document. Root element must be <svg>");
                        } else {
                            NumberFormatException v0_68 = new vn.zalopay.ui.androidsvg.B();
                            v0_68.a = this.a;
                            v0_68.b = this.b;
                            vn.zalopay.ui.androidsvg.M0.h(v0_68, p20);
                            vn.zalopay.ui.androidsvg.M0.k(v0_68, p20);
                            vn.zalopay.ui.androidsvg.M0.m(v0_68, p20);
                            vn.zalopay.ui.androidsvg.M0.g(v0_68, p20);
                            this.b.m(v0_68);
                            this.b = v0_68;
                            return;
                        }
                    case 2:
                        NumberFormatException v0_48 = this.b;
                        if (v0_48 == null) {
                            throw new vn.zalopay.ui.androidsvg.SVGParseException("Invalid document. Root element must be <svg>");
                        } else {
                            String v3_28 = new vn.zalopay.ui.androidsvg.t();
                            v3_28.a = this.a;
                            v3_28.b = v0_48;
                            vn.zalopay.ui.androidsvg.M0.h(v3_28, p20);
                            vn.zalopay.ui.androidsvg.M0.k(v3_28, p20);
                            vn.zalopay.ui.androidsvg.M0.m(v3_28, p20);
                            vn.zalopay.ui.androidsvg.M0.g(v3_28, p20);
                            float v6_5 = 0;
                            while (v6_5 < p20.getLength()) {
                                NumberFormatException v0_55 = p20.getValue(v6_5).trim();
                                boolean v4_14 = q9.H.d(p20, v6_5);
                                if (v4_14 == 6) {
                                    float v8 = 49;
                                    v3_28.o = vn.zalopay.ui.androidsvg.M0.t(v0_55);
                                } else {
                                    if (v4_14 == 7) {
                                        v3_28.p = vn.zalopay.ui.androidsvg.M0.t(v0_55);
                                    } else {
                                        if (v4_14 == 49) {
                                            NumberFormatException v0_60 = vn.zalopay.ui.androidsvg.M0.t(v0_55);
                                            v3_28.q = v0_60;
                                            if (v0_60.i()) {
                                                throw new vn.zalopay.ui.androidsvg.SVGParseException("Invalid <circle> element. r cannot be negative");
                                            }
                                        }
                                    }
                                }
                                v6_5++;
                            }
                            this.b.m(v3_28);
                            return;
                        }
                    case 3:
                        if (this.b == null) {
                            throw new vn.zalopay.ui.androidsvg.SVGParseException("Invalid document. Root element must be <svg>");
                        } else {
                            NumberFormatException v0_42 = new vn.zalopay.ui.androidsvg.u();
                            v0_42.a = this.a;
                            v0_42.b = this.b;
                            vn.zalopay.ui.androidsvg.M0.h(v0_42, p20);
                            vn.zalopay.ui.androidsvg.M0.k(v0_42, p20);
                            vn.zalopay.ui.androidsvg.M0.m(v0_42, p20);
                            vn.zalopay.ui.androidsvg.M0.g(v0_42, p20);
                            float v6_3 = 0;
                            while (v6_3 < p20.getLength()) {
                                String v3_23 = p20.getValue(v6_3).trim();
                                if (vn.zalopay.ui.androidsvg.J0.e(p20.getLocalName(v6_3)) == vn.zalopay.ui.androidsvg.J0.b) {
                                    if (!"objectBoundingBox".equals(v3_23)) {
                                        if (!"userSpaceOnUse".equals(v3_23)) {
                                            throw new vn.zalopay.ui.androidsvg.SVGParseException("Invalid value for attribute clipPathUnits");
                                        } else {
                                            v0_42.o = Boolean.TRUE;
                                        }
                                    } else {
                                        v0_42.o = Boolean.FALSE;
                                    }
                                }
                                v6_3++;
                            }
                            this.b.m(v0_42);
                            this.b = v0_42;
                            return;
                        }
                    case 4:
                        if (this.b == null) {
                            throw new vn.zalopay.ui.androidsvg.SVGParseException("Invalid document. Root element must be <svg>");
                        } else {
                            NumberFormatException v0_37 = new vn.zalopay.ui.androidsvg.x();
                            v0_37.a = this.a;
                            v0_37.b = this.b;
                            vn.zalopay.ui.androidsvg.M0.h(v0_37, p20);
                            vn.zalopay.ui.androidsvg.M0.k(v0_37, p20);
                            vn.zalopay.ui.androidsvg.M0.m(v0_37, p20);
                            this.b.m(v0_37);
                            this.b = v0_37;
                            return;
                        }
                    case 5:
                    case 26:
                        this.e = 1;
                        this.f = v0_94;
                        return;
                    case 6:
                        NumberFormatException v0_16 = this.b;
                        if (v0_16 == null) {
                            throw new vn.zalopay.ui.androidsvg.SVGParseException("Invalid document. Root element must be <svg>");
                        } else {
                            String v3_13 = new vn.zalopay.ui.androidsvg.y();
                            v3_13.a = this.a;
                            v3_13.b = v0_16;
                            vn.zalopay.ui.androidsvg.M0.h(v3_13, p20);
                            vn.zalopay.ui.androidsvg.M0.k(v3_13, p20);
                            vn.zalopay.ui.androidsvg.M0.m(v3_13, p20);
                            vn.zalopay.ui.androidsvg.M0.g(v3_13, p20);
                            float v6_2 = 0;
                            while (v6_2 < p20.getLength()) {
                                NumberFormatException v0_22 = p20.getValue(v6_2).trim();
                                boolean v4_9 = q9.H.d(p20, v6_2);
                                if (v4_9 == 6) {
                                    v3_13.o = vn.zalopay.ui.androidsvg.M0.t(v0_22);
                                } else {
                                    if (v4_9 == 7) {
                                        v3_13.p = vn.zalopay.ui.androidsvg.M0.t(v0_22);
                                    } else {
                                        if (v4_9 == 56) {
                                            NumberFormatException v0_25 = vn.zalopay.ui.androidsvg.M0.t(v0_22);
                                            v3_13.q = v0_25;
                                            if (v0_25.i()) {
                                                throw new vn.zalopay.ui.androidsvg.SVGParseException("Invalid <ellipse> element. rx cannot be negative");
                                            }
                                        } else {
                                            if (v4_9 == 57) {
                                                NumberFormatException v0_29 = vn.zalopay.ui.androidsvg.M0.t(v0_22);
                                                v3_13.r = v0_29;
                                                if (v0_29.i()) {
                                                    throw new vn.zalopay.ui.androidsvg.SVGParseException("Invalid <ellipse> element. ry cannot be negative");
                                                }
                                            }
                                        }
                                    }
                                }
                                v6_2++;
                            }
                            this.b.m(v3_13);
                            return;
                        }
                    case 8:
                        if (this.b == null) {
                            throw new vn.zalopay.ui.androidsvg.SVGParseException("Invalid document. Root element must be <svg>");
                        } else {
                            NumberFormatException v0_11 = new vn.zalopay.ui.androidsvg.D();
                            v0_11.a = this.a;
                            v0_11.b = this.b;
                            vn.zalopay.ui.androidsvg.M0.h(v0_11, p20);
                            vn.zalopay.ui.androidsvg.M0.k(v0_11, p20);
                            vn.zalopay.ui.androidsvg.M0.m(v0_11, p20);
                            vn.zalopay.ui.androidsvg.M0.g(v0_11, p20);
                            float v6_1 = 0;
                            while (v6_1 < p20.getLength()) {
                                String v3_4 = p20.getValue(v6_1).trim();
                                boolean v4_3 = q9.H.d(p20, v6_1);
                                if (v4_3 == 25) {
                                    String v3_5 = vn.zalopay.ui.androidsvg.M0.t(v3_4);
                                    v0_11.s = v3_5;
                                    if (v3_5.i()) {
                                        throw new vn.zalopay.ui.androidsvg.SVGParseException("Invalid <use> element. height cannot be negative");
                                    }
                                } else {
                                    if (v4_3 == 26) {
                                        if (("".equals(p20.getURI(v6_1))) || ("http://www.w3.org/1999/xlink".equals(p20.getURI(v6_1)))) {
                                            v0_11.o = v3_4;
                                        }
                                    } else {
                                        if (v4_3 == 48) {
                                            vn.zalopay.ui.androidsvg.M0.y(v0_11, v3_4);
                                        } else {
                                            switch (v4_3) {
                                                case 81:
                                                    String v3_9 = vn.zalopay.ui.androidsvg.M0.t(v3_4);
                                                    v0_11.r = v3_9;
                                                    if (v3_9.i()) {
                                                        throw new vn.zalopay.ui.androidsvg.SVGParseException("Invalid <use> element. width cannot be negative");
                                                    }
                                                    break;
                                                case 82:
                                                    v0_11.p = vn.zalopay.ui.androidsvg.M0.t(v3_4);
                                                    break;
                                                case 83:
                                                    v0_11.q = vn.zalopay.ui.androidsvg.M0.t(v3_4);
                                                    break;
                                                default:
                                            }
                                        }
                                    }
                                }
                                v6_1++;
                            }
                            this.b.m(v0_11);
                            this.b = v0_11;
                            return;
                        }
                    case 9:
                        NumberFormatException v0_230 = this.b;
                        if (v0_230 == null) {
                            throw new vn.zalopay.ui.androidsvg.SVGParseException("Invalid document. Root element must be <svg>");
                        } else {
                            String v3_152 = new vn.zalopay.ui.androidsvg.F();
                            v3_152.a = this.a;
                            v3_152.b = v0_230;
                            vn.zalopay.ui.androidsvg.M0.h(v3_152, p20);
                            vn.zalopay.ui.androidsvg.M0.k(v3_152, p20);
                            vn.zalopay.ui.androidsvg.M0.m(v3_152, p20);
                            vn.zalopay.ui.androidsvg.M0.g(v3_152, p20);
                            float v6_0 = 0;
                            while (v6_0 < p20.getLength()) {
                                NumberFormatException v0_2 = p20.getValue(v6_0).trim();
                                switch (q9.H.d(p20, v6_0)) {
                                    case 84:
                                        v3_152.o = vn.zalopay.ui.androidsvg.M0.t(v0_2);
                                        break;
                                    case 85:
                                        v3_152.p = vn.zalopay.ui.androidsvg.M0.t(v0_2);
                                        break;
                                    case 86:
                                        v3_152.q = vn.zalopay.ui.androidsvg.M0.t(v0_2);
                                        break;
                                    case 87:
                                        v3_152.r = vn.zalopay.ui.androidsvg.M0.t(v0_2);
                                        break;
                                    default:
                                }
                                v6_0++;
                            }
                            this.b.m(v3_152);
                            return;
                        }
                    case 10:
                        if (this.b == null) {
                            throw new vn.zalopay.ui.androidsvg.SVGParseException("Invalid document. Root element must be <svg>");
                        } else {
                            v0_229 = new vn.zalopay.ui.androidsvg.b0();
                            v0_229.a = this.a;
                            v0_229.b = this.b;
                            vn.zalopay.ui.androidsvg.M0.h(v0_229, p20);
                            vn.zalopay.ui.androidsvg.M0.k(v0_229, p20);
                            vn.zalopay.ui.androidsvg.M0.i(v0_229, p20);
                            v6_25 = 0;
                        }
                        break;
                    case 11:
                        if (this.b == null) {
                            throw new vn.zalopay.ui.androidsvg.SVGParseException("Invalid document. Root element must be <svg>");
                        } else {
                            NumberFormatException v0_216 = new vn.zalopay.ui.androidsvg.G();
                            v0_216.a = this.a;
                            v0_216.b = this.b;
                            vn.zalopay.ui.androidsvg.M0.h(v0_216, p20);
                            vn.zalopay.ui.androidsvg.M0.k(v0_216, p20);
                            vn.zalopay.ui.androidsvg.M0.g(v0_216, p20);
                            vn.zalopay.ui.androidsvg.M0.n(v0_216, p20);
                            String v3_139 = 0;
                            while (v3_139 < p20.getLength()) {
                                Float v5_23 = p20.getValue(v3_139).trim();
                                float v6_20 = q9.H.d(p20, v3_139);
                                if (v6_20 == 41) {
                                    if (!"auto".equals(v5_23)) {
                                        v0_216.u = Float.valueOf(vn.zalopay.ui.androidsvg.M0.q(v5_23));
                                    } else {
                                        v0_216.u = Float.valueOf(2143289344);
                                    }
                                } else {
                                    if (v6_20 == 50) {
                                        v0_216.q = vn.zalopay.ui.androidsvg.M0.t(v5_23);
                                    } else {
                                        if (v6_20 == 51) {
                                            v0_216.r = vn.zalopay.ui.androidsvg.M0.t(v5_23);
                                        } else {
                                            switch (v6_20) {
                                                case 32:
                                                    Float v5_34 = vn.zalopay.ui.androidsvg.M0.t(v5_23);
                                                    v0_216.t = v5_34;
                                                    if (v5_34.i()) {
                                                        throw new vn.zalopay.ui.androidsvg.SVGParseException("Invalid <marker> element. markerHeight cannot be negative");
                                                    }
                                                    break;
                                                case 33:
                                                    if (!"strokeWidth".equals(v5_23)) {
                                                        if (!"userSpaceOnUse".equals(v5_23)) {
                                                            throw new vn.zalopay.ui.androidsvg.SVGParseException("Invalid value for attribute markerUnits");
                                                        } else {
                                                            v0_216.p = 1;
                                                        }
                                                    } else {
                                                        v0_216.p = 0;
                                                    }
                                                    break;
                                                case 34:
                                                    Float v5_30 = vn.zalopay.ui.androidsvg.M0.t(v5_23);
                                                    v0_216.s = v5_30;
                                                    if (v5_30.i()) {
                                                        throw new vn.zalopay.ui.androidsvg.SVGParseException("Invalid <marker> element. markerWidth cannot be negative");
                                                    } else {
                                                    }
                                                    break;
                                            }
                                        }
                                    }
                                }
                                v3_139++;
                            }
                            this.b.m(v0_216);
                            this.b = v0_216;
                            return;
                        }
                    case 12:
                        if (this.b == null) {
                            throw new vn.zalopay.ui.androidsvg.SVGParseException("Invalid document. Root element must be <svg>");
                        } else {
                            NumberFormatException v0_203 = new vn.zalopay.ui.androidsvg.H();
                            v0_203.a = this.a;
                            v0_203.b = this.b;
                            vn.zalopay.ui.androidsvg.M0.h(v0_203, p20);
                            vn.zalopay.ui.androidsvg.M0.k(v0_203, p20);
                            vn.zalopay.ui.androidsvg.M0.g(v0_203, p20);
                            float v6_19 = 0;
                            while (v6_19 < p20.getLength()) {
                                String v3_123 = p20.getValue(v6_19).trim();
                                boolean v4_41 = q9.H.d(p20, v6_19);
                                if (v4_41 == 25) {
                                    String v3_124 = vn.zalopay.ui.androidsvg.M0.t(v3_123);
                                    v0_203.q = v3_124;
                                    if (v3_124.i()) {
                                        throw new vn.zalopay.ui.androidsvg.SVGParseException("Invalid <mask> element. height cannot be negative");
                                    }
                                } else {
                                    if (v4_41 == 36) {
                                        if (!"objectBoundingBox".equals(v3_123)) {
                                            if (!"userSpaceOnUse".equals(v3_123)) {
                                                throw new vn.zalopay.ui.androidsvg.SVGParseException("Invalid value for attribute maskContentUnits");
                                            } else {
                                                v0_203.o = Boolean.TRUE;
                                            }
                                        } else {
                                            v0_203.o = Boolean.FALSE;
                                        }
                                    } else {
                                        if (v4_41 == 37) {
                                            if (!"objectBoundingBox".equals(v3_123)) {
                                                if (!"userSpaceOnUse".equals(v3_123)) {
                                                    throw new vn.zalopay.ui.androidsvg.SVGParseException("Invalid value for attribute maskUnits");
                                                } else {
                                                    v0_203.n = Boolean.TRUE;
                                                }
                                            } else {
                                                v0_203.n = Boolean.FALSE;
                                            }
                                        } else {
                                            switch (v4_41) {
                                                case 81:
                                                    String v3_135 = vn.zalopay.ui.androidsvg.M0.t(v3_123);
                                                    v0_203.p = v3_135;
                                                    if (v3_135.i()) {
                                                        throw new vn.zalopay.ui.androidsvg.SVGParseException("Invalid <mask> element. width cannot be negative");
                                                    }
                                                    break;
                                                case 82:
                                                    vn.zalopay.ui.androidsvg.M0.t(v3_123);
                                                    break;
                                                case 83:
                                                    vn.zalopay.ui.androidsvg.M0.t(v3_123);
                                                    break;
                                                default:
                                            }
                                        }
                                    }
                                }
                                v6_19++;
                            }
                            this.b.m(v0_203);
                            this.b = v0_203;
                            return;
                        }
                    case 13:
                        this.D(p20);
                        return;
                    case 14:
                        if (this.b == null) {
                            throw new vn.zalopay.ui.androidsvg.SVGParseException("Invalid document. Root element must be <svg>");
                        } else {
                            NumberFormatException v0_190 = new vn.zalopay.ui.androidsvg.N();
                            v0_190.a = this.a;
                            v0_190.b = this.b;
                            vn.zalopay.ui.androidsvg.M0.h(v0_190, p20);
                            vn.zalopay.ui.androidsvg.M0.k(v0_190, p20);
                            vn.zalopay.ui.androidsvg.M0.g(v0_190, p20);
                            vn.zalopay.ui.androidsvg.M0.n(v0_190, p20);
                            float v6_18 = 0;
                            while (v6_18 < p20.getLength()) {
                                String v3_104 = p20.getValue(v6_18).trim();
                                boolean v4_34 = q9.H.d(p20, v6_18);
                                if (v4_34 == 25) {
                                    String v3_105 = vn.zalopay.ui.androidsvg.M0.t(v3_104);
                                    v0_190.v = v3_105;
                                    if (v3_105.i()) {
                                        throw new vn.zalopay.ui.androidsvg.SVGParseException("Invalid <pattern> element. height cannot be negative");
                                    }
                                } else {
                                    if (v4_34 == 26) {
                                        if (("".equals(p20.getURI(v6_18))) || ("http://www.w3.org/1999/xlink".equals(p20.getURI(v6_18)))) {
                                            v0_190.w = v3_104;
                                        }
                                    } else {
                                        switch (v4_34) {
                                            case 44:
                                                if (!"objectBoundingBox".equals(v3_104)) {
                                                    if (!"userSpaceOnUse".equals(v3_104)) {
                                                        throw new vn.zalopay.ui.androidsvg.SVGParseException("Invalid value for attribute patternContentUnits");
                                                    } else {
                                                        v0_190.q = Boolean.TRUE;
                                                    }
                                                } else {
                                                    v0_190.q = Boolean.FALSE;
                                                }
                                                break;
                                            case 45:
                                                v0_190.r = vn.zalopay.ui.androidsvg.M0.A(v3_104);
                                                break;
                                            case 46:
                                                if (!"objectBoundingBox".equals(v3_104)) {
                                                    if (!"userSpaceOnUse".equals(v3_104)) {
                                                        throw new vn.zalopay.ui.androidsvg.SVGParseException("Invalid value for attribute patternUnits");
                                                    } else {
                                                        v0_190.p = Boolean.TRUE;
                                                    }
                                                } else {
                                                    v0_190.p = Boolean.FALSE;
                                                }
                                                break;
                                            default:
                                                switch (v4_34) {
                                                    case 81:
                                                        String v3_117 = vn.zalopay.ui.androidsvg.M0.t(v3_104);
                                                        v0_190.u = v3_117;
                                                        if (v3_117.i()) {
                                                            throw new vn.zalopay.ui.androidsvg.SVGParseException("Invalid <pattern> element. width cannot be negative");
                                                        }
                                                        break;
                                                    case 82:
                                                        v0_190.s = vn.zalopay.ui.androidsvg.M0.t(v3_104);
                                                        break;
                                                    case 83:
                                                        v0_190.t = vn.zalopay.ui.androidsvg.M0.t(v3_104);
                                                        break;
                                                    default:
                                                }
                                        }
                                    }
                                }
                                v6_18++;
                            }
                            this.b.m(v0_190);
                            this.b = v0_190;
                            return;
                        }
                    case 15:
                        NumberFormatException v0_181 = this.b;
                        if (v0_181 == null) {
                            throw new vn.zalopay.ui.androidsvg.SVGParseException("Invalid document. Root element must be <svg>");
                        } else {
                            String v3_98 = new vn.zalopay.ui.androidsvg.P();
                            v3_98.a = this.a;
                            v3_98.b = v0_181;
                            vn.zalopay.ui.androidsvg.M0.h(v3_98, p20);
                            vn.zalopay.ui.androidsvg.M0.k(v3_98, p20);
                            vn.zalopay.ui.androidsvg.M0.m(v3_98, p20);
                            vn.zalopay.ui.androidsvg.M0.g(v3_98, p20);
                            vn.zalopay.ui.androidsvg.M0.j(v3_98, p20, "polygon");
                            this.b.m(v3_98);
                            return;
                        }
                    case 16:
                        NumberFormatException v0_175 = this.b;
                        if (v0_175 == null) {
                            throw new vn.zalopay.ui.androidsvg.SVGParseException("Invalid document. Root element must be <svg>");
                        } else {
                            String v3_95 = new vn.zalopay.ui.androidsvg.O();
                            v3_95.a = this.a;
                            v3_95.b = v0_175;
                            vn.zalopay.ui.androidsvg.M0.h(v3_95, p20);
                            vn.zalopay.ui.androidsvg.M0.k(v3_95, p20);
                            vn.zalopay.ui.androidsvg.M0.m(v3_95, p20);
                            vn.zalopay.ui.androidsvg.M0.g(v3_95, p20);
                            vn.zalopay.ui.androidsvg.M0.j(v3_95, p20, "polyline");
                            this.b.m(v3_95);
                            return;
                        }
                    case 17:
                        if (this.b == null) {
                            throw new vn.zalopay.ui.androidsvg.SVGParseException("Invalid document. Root element must be <svg>");
                        } else {
                            NumberFormatException v0_168 = new vn.zalopay.ui.androidsvg.e0();
                            v0_168.a = this.a;
                            v0_168.b = this.b;
                            vn.zalopay.ui.androidsvg.M0.h(v0_168, p20);
                            vn.zalopay.ui.androidsvg.M0.k(v0_168, p20);
                            vn.zalopay.ui.androidsvg.M0.i(v0_168, p20);
                            float v6_17 = 0;
                            while (v6_17 < p20.getLength()) {
                                String v3_86 = p20.getValue(v6_17).trim();
                                boolean v4_31 = q9.H.d(p20, v6_17);
                                if (v4_31 == 6) {
                                    v0_168.m = vn.zalopay.ui.androidsvg.M0.t(v3_86);
                                } else {
                                    if (v4_31 == 7) {
                                        v0_168.n = vn.zalopay.ui.androidsvg.M0.t(v3_86);
                                    } else {
                                        if (v4_31 == 11) {
                                            v0_168.p = vn.zalopay.ui.androidsvg.M0.t(v3_86);
                                        } else {
                                            if (v4_31 == 12) {
                                                v0_168.q = vn.zalopay.ui.androidsvg.M0.t(v3_86);
                                            } else {
                                                if (v4_31 == 49) {
                                                    String v3_92 = vn.zalopay.ui.androidsvg.M0.t(v3_86);
                                                    v0_168.o = v3_92;
                                                    if (v3_92.i()) {
                                                        throw new vn.zalopay.ui.androidsvg.SVGParseException("Invalid <radialGradient> element. r cannot be negative");
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                                v6_17++;
                            }
                            this.b.m(v0_168);
                            this.b = v0_168;
                            return;
                        }
                    case 18:
                        NumberFormatException v0_139 = this.b;
                        if (v0_139 == null) {
                            throw new vn.zalopay.ui.androidsvg.SVGParseException("Invalid document. Root element must be <svg>");
                        } else {
                            String v3_81 = new vn.zalopay.ui.androidsvg.Q();
                            v3_81.a = this.a;
                            v3_81.b = v0_139;
                            vn.zalopay.ui.androidsvg.M0.h(v3_81, p20);
                            vn.zalopay.ui.androidsvg.M0.k(v3_81, p20);
                            vn.zalopay.ui.androidsvg.M0.m(v3_81, p20);
                            vn.zalopay.ui.androidsvg.M0.g(v3_81, p20);
                            float v6_16 = 0;
                            while (v6_16 < p20.getLength()) {
                                NumberFormatException v0_145 = p20.getValue(v6_16).trim();
                                boolean v4_30 = q9.H.d(p20, v6_16);
                                if (v4_30 == 25) {
                                    NumberFormatException v0_146 = vn.zalopay.ui.androidsvg.M0.t(v0_145);
                                    v3_81.r = v0_146;
                                    if (v0_146.i()) {
                                        throw new vn.zalopay.ui.androidsvg.SVGParseException("Invalid <rect> element. height cannot be negative");
                                    }
                                } else {
                                    if (v4_30 == 56) {
                                        NumberFormatException v0_150 = vn.zalopay.ui.androidsvg.M0.t(v0_145);
                                        v3_81.s = v0_150;
                                        if (v0_150.i()) {
                                            throw new vn.zalopay.ui.androidsvg.SVGParseException("Invalid <rect> element. rx cannot be negative");
                                        }
                                    } else {
                                        if (v4_30 == 57) {
                                            NumberFormatException v0_154 = vn.zalopay.ui.androidsvg.M0.t(v0_145);
                                            v3_81.t = v0_154;
                                            if (v0_154.i()) {
                                                throw new vn.zalopay.ui.androidsvg.SVGParseException("Invalid <rect> element. ry cannot be negative");
                                            }
                                        } else {
                                            switch (v4_30) {
                                                case 81:
                                                    NumberFormatException v0_160 = vn.zalopay.ui.androidsvg.M0.t(v0_145);
                                                    v3_81.q = v0_160;
                                                    if (v0_160.i()) {
                                                        throw new vn.zalopay.ui.androidsvg.SVGParseException("Invalid <rect> element. width cannot be negative");
                                                    }
                                                    break;
                                                case 82:
                                                    v3_81.o = vn.zalopay.ui.androidsvg.M0.t(v0_145);
                                                    break;
                                                case 83:
                                                    v3_81.p = vn.zalopay.ui.androidsvg.M0.t(v0_145);
                                                    break;
                                                default:
                                            }
                                        }
                                    }
                                }
                                v6_16++;
                            }
                            this.b.m(v3_81);
                            return;
                        }
                    case 19:
                        NumberFormatException v0_135 = this.b;
                        if (v0_135 == null) {
                            throw new vn.zalopay.ui.androidsvg.SVGParseException("Invalid document. Root element must be <svg>");
                        } else {
                            String v3_78 = new vn.zalopay.ui.androidsvg.S();
                            v3_78.a = this.a;
                            v3_78.b = v0_135;
                            vn.zalopay.ui.androidsvg.M0.h(v3_78, p20);
                            vn.zalopay.ui.androidsvg.M0.k(v3_78, p20);
                            this.b.m(v3_78);
                            this.b = v3_78;
                            return;
                        }
                    case 20:
                        NumberFormatException v0_121 = this.b;
                        if (v0_121 == null) {
                            throw new vn.zalopay.ui.androidsvg.SVGParseException("Invalid document. Root element must be <svg>");
                        } else {
                            if (!(v0_121 instanceof vn.zalopay.ui.androidsvg.z)) {
                                throw new vn.zalopay.ui.androidsvg.SVGParseException("Invalid document. <stop> elements are only valid inside <linearGradient> or <radialGradient> elements.");
                            } else {
                                String v3_73 = new vn.zalopay.ui.androidsvg.T();
                                v3_73.a = this.a;
                                v3_73.b = v0_121;
                                vn.zalopay.ui.androidsvg.M0.h(v3_73, p20);
                                vn.zalopay.ui.androidsvg.M0.k(v3_73, p20);
                                NumberFormatException v0_126 = 0;
                                while (v0_126 < p20.getLength()) {
                                    Float v5_12 = p20.getValue(v0_126).trim();
                                    if (vn.zalopay.ui.androidsvg.J0.e(p20.getLocalName(v0_126)) == vn.zalopay.ui.androidsvg.J0.d) {
                                        if (v5_12.length() == 0) {
                                            throw new vn.zalopay.ui.androidsvg.SVGParseException("Invalid offset value in <stop> (empty string)");
                                        } else {
                                            float v7_10;
                                            float v6_14 = v5_12.length();
                                            if (v5_12.charAt((v5_12.length() - 1)) != 37) {
                                                v7_10 = 0;
                                            } else {
                                                v6_14--;
                                                v7_10 = 1;
                                            }
                                            try {
                                                float v6_15 = vn.zalopay.ui.androidsvg.M0.p(v6_14, v5_12);
                                                float v8_4 = 1120403456;
                                            } catch (NumberFormatException v0_134) {
                                                throw new vn.zalopay.ui.androidsvg.SVGParseException("Invalid offset value in <stop>: ".concat(v5_12), v0_134);
                                            }
                                            if (v7_10 != 0) {
                                                v6_15 /= 1120403456;
                                            }
                                            if (v6_15 >= 0) {
                                                if (v6_15 <= 1120403456) {
                                                    v8_4 = v6_15;
                                                } else {
                                                }
                                            } else {
                                                v8_4 = 0;
                                            }
                                            v3_73.h = Float.valueOf(v8_4);
                                        }
                                    }
                                    v0_126++;
                                }
                                this.b.m(v3_73);
                                this.b = v3_73;
                                return;
                            }
                        }
                    case 21:
                        if (this.b == null) {
                            throw new vn.zalopay.ui.androidsvg.SVGParseException("Invalid document. Root element must be <svg>");
                        } else {
                            NumberFormatException v0_117 = "all";
                            String v3_67 = 1;
                            float v6_9 = 0;
                            while (v6_9 < p20.getLength()) {
                                Float v5_8 = p20.getValue(v6_9).trim();
                                float v7_5 = q9.H.d(p20, v6_9);
                                if (v7_5 == 38) {
                                    v0_117 = v5_8;
                                } else {
                                    if (v7_5 == 77) {
                                        v3_67 = v5_8.equals("text/css");
                                    }
                                }
                                v6_9++;
                            }
                            if (v3_67 != null) {
                                vn.zalopay.ui.androidsvg.SVGParseException v2_34 = new vn.zalopay.ui.androidsvg.c(v0_117);
                                v2_34.y();
                                NumberFormatException v0_119 = vn.zalopay.ui.androidsvg.p.c(v2_34).iterator();
                                while (v0_119.hasNext()) {
                                    vn.zalopay.ui.androidsvg.SVGParseException v2_37 = ((vn.zalopay.ui.androidsvg.d) v0_119.next());
                                    if ((v2_37 == vn.zalopay.ui.androidsvg.d.a) || (v2_37 == vn.zalopay.ui.androidsvg.d.b)) {
                                        this.h = 1;
                                        return;
                                    }
                                }
                            }
                            this.c = 1;
                            this.d = 1;
                            return;
                        }
                    case 22:
                        if (this.b == null) {
                            throw new vn.zalopay.ui.androidsvg.SVGParseException("Invalid document. Root element must be <svg>");
                        } else {
                            NumberFormatException v0_113 = new vn.zalopay.ui.androidsvg.g0();
                            v0_113.a = this.a;
                            v0_113.b = this.b;
                            vn.zalopay.ui.androidsvg.M0.h(v0_113, p20);
                            vn.zalopay.ui.androidsvg.M0.k(v0_113, p20);
                            vn.zalopay.ui.androidsvg.M0.m(v0_113, p20);
                            vn.zalopay.ui.androidsvg.M0.g(v0_113, p20);
                            this.b.m(v0_113);
                            this.b = v0_113;
                            return;
                        }
                    case 23:
                        if (this.b == null) {
                            throw new vn.zalopay.ui.androidsvg.SVGParseException("Invalid document. Root element must be <svg>");
                        } else {
                            NumberFormatException v0_108 = new vn.zalopay.ui.androidsvg.h0();
                            v0_108.a = this.a;
                            v0_108.b = this.b;
                            vn.zalopay.ui.androidsvg.M0.h(v0_108, p20);
                            vn.zalopay.ui.androidsvg.M0.k(v0_108, p20);
                            vn.zalopay.ui.androidsvg.M0.g(v0_108, p20);
                            vn.zalopay.ui.androidsvg.M0.n(v0_108, p20);
                            this.b.m(v0_108);
                            this.b = v0_108;
                            return;
                        }
                    case 24:
                        if (this.b == null) {
                            throw new vn.zalopay.ui.androidsvg.SVGParseException("Invalid document. Root element must be <svg>");
                        } else {
                            NumberFormatException v0_103 = new vn.zalopay.ui.androidsvg.k0();
                            v0_103.a = this.a;
                            v0_103.b = this.b;
                            vn.zalopay.ui.androidsvg.M0.h(v0_103, p20);
                            vn.zalopay.ui.androidsvg.M0.k(v0_103, p20);
                            vn.zalopay.ui.androidsvg.M0.m(v0_103, p20);
                            vn.zalopay.ui.androidsvg.M0.g(v0_103, p20);
                            vn.zalopay.ui.androidsvg.M0.l(v0_103, p20);
                            this.b.m(v0_103);
                            this.b = v0_103;
                            return;
                        }
                    case 25:
                        if (this.b == null) {
                            throw new vn.zalopay.ui.androidsvg.SVGParseException("Invalid document. Root element must be <svg>");
                        } else {
                            NumberFormatException v0_98 = new vn.zalopay.ui.androidsvg.n0();
                            v0_98.a = this.a;
                            v0_98.b = this.b;
                            vn.zalopay.ui.androidsvg.M0.h(v0_98, p20);
                            vn.zalopay.ui.androidsvg.M0.k(v0_98, p20);
                            vn.zalopay.ui.androidsvg.M0.g(v0_98, p20);
                            float v6_8 = 0;
                            while (v6_8 < p20.getLength()) {
                                String v3_57 = p20.getValue(v6_8).trim();
                                boolean v4_22 = q9.H.d(p20, v6_8);
                                if (v4_22 == 26) {
                                    if (("".equals(p20.getURI(v6_8))) || ("http://www.w3.org/1999/xlink".equals(p20.getURI(v6_8)))) {
                                        v0_98.n = v3_57;
                                    }
                                } else {
                                    if (v4_22 == 61) {
                                        v0_98.o = vn.zalopay.ui.androidsvg.M0.t(v3_57);
                                    }
                                }
                                v6_8++;
                            }
                            this.b.m(v0_98);
                            this.b = v0_98;
                            vn.zalopay.ui.androidsvg.SVGParseException v2_26 = v0_98.b;
                            if (!(v2_26 instanceof vn.zalopay.ui.androidsvg.p0)) {
                                v0_98.p = ((vn.zalopay.ui.androidsvg.l0) v2_26).b();
                                return;
                            } else {
                                v0_98.p = ((vn.zalopay.ui.androidsvg.p0) v2_26);
                                return;
                            }
                        }
                    case 27:
                        NumberFormatException v0_83 = this.b;
                        if (v0_83 == null) {
                            throw new vn.zalopay.ui.androidsvg.SVGParseException("Invalid document. Root element must be <svg>");
                        } else {
                            if (!(v0_83 instanceof vn.zalopay.ui.androidsvg.m0)) {
                                throw new vn.zalopay.ui.androidsvg.SVGParseException("Invalid document. <tref> elements are only valid inside <text> or <tspan> elements.");
                            } else {
                                NumberFormatException v0_91 = new vn.zalopay.ui.androidsvg.i0();
                                v0_91.a = this.a;
                                v0_91.b = this.b;
                                vn.zalopay.ui.androidsvg.M0.h(v0_91, p20);
                                vn.zalopay.ui.androidsvg.M0.k(v0_91, p20);
                                vn.zalopay.ui.androidsvg.M0.g(v0_91, p20);
                                float v6_7 = 0;
                                while (v6_7 < p20.getLength()) {
                                    String v3_51 = p20.getValue(v6_7).trim();
                                    if ((vn.zalopay.ui.androidsvg.J0.e(p20.getLocalName(v6_7)) == vn.zalopay.ui.androidsvg.J0.c) && (("".equals(p20.getURI(v6_7))) || ("http://www.w3.org/1999/xlink".equals(p20.getURI(v6_7))))) {
                                        v0_91.n = v3_51;
                                    }
                                    v6_7++;
                                }
                                this.b.m(v0_91);
                                vn.zalopay.ui.androidsvg.SVGParseException v2_20 = v0_91.b;
                                if (!(v2_20 instanceof vn.zalopay.ui.androidsvg.p0)) {
                                    v0_91.o = ((vn.zalopay.ui.androidsvg.l0) v2_20).b();
                                    return;
                                } else {
                                    v0_91.o = ((vn.zalopay.ui.androidsvg.p0) v2_20);
                                    return;
                                }
                            }
                        }
                    case 28:
                        NumberFormatException v0_51 = this.b;
                        if (v0_51 == null) {
                            throw new vn.zalopay.ui.androidsvg.SVGParseException("Invalid document. Root element must be <svg>");
                        } else {
                            if (!(v0_51 instanceof vn.zalopay.ui.androidsvg.m0)) {
                                throw new vn.zalopay.ui.androidsvg.SVGParseException("Invalid document. <tspan> elements are only valid inside <text> or other <tspan> elements.");
                            } else {
                                NumberFormatException v0_79 = new vn.zalopay.ui.androidsvg.j0();
                                v0_79.a = this.a;
                                v0_79.b = this.b;
                                vn.zalopay.ui.androidsvg.M0.h(v0_79, p20);
                                vn.zalopay.ui.androidsvg.M0.k(v0_79, p20);
                                vn.zalopay.ui.androidsvg.M0.g(v0_79, p20);
                                vn.zalopay.ui.androidsvg.M0.l(v0_79, p20);
                                this.b.m(v0_79);
                                this.b = v0_79;
                                vn.zalopay.ui.androidsvg.SVGParseException v2_14 = v0_79.b;
                                if (!(v2_14 instanceof vn.zalopay.ui.androidsvg.p0)) {
                                    v0_79.r = ((vn.zalopay.ui.androidsvg.l0) v2_14).b();
                                    return;
                                } else {
                                    v0_79.r = ((vn.zalopay.ui.androidsvg.p0) v2_14);
                                    return;
                                }
                            }
                        }
                    case 29:
                        if (this.b == null) {
                            throw new vn.zalopay.ui.androidsvg.SVGParseException("Invalid document. Root element must be <svg>");
                        } else {
                            NumberFormatException v0_178 = new vn.zalopay.ui.androidsvg.r0();
                            v0_178.a = this.a;
                            v0_178.b = this.b;
                            vn.zalopay.ui.androidsvg.M0.h(v0_178, p20);
                            vn.zalopay.ui.androidsvg.M0.k(v0_178, p20);
                            vn.zalopay.ui.androidsvg.M0.m(v0_178, p20);
                            vn.zalopay.ui.androidsvg.M0.g(v0_178, p20);
                            float v6_4 = 0;
                            while (v6_4 < p20.getLength()) {
                                String v3_132 = p20.getValue(v6_4).trim();
                                boolean v4_44 = q9.H.d(p20, v6_4);
                                if (v4_44 == 25) {
                                    String v3_140 = vn.zalopay.ui.androidsvg.M0.t(v3_132);
                                    v0_178.s = v3_140;
                                    if (v3_140.i()) {
                                        throw new vn.zalopay.ui.androidsvg.SVGParseException("Invalid <use> element. height cannot be negative");
                                    }
                                } else {
                                    if (v4_44 == 26) {
                                        if (("".equals(p20.getURI(v6_4))) || ("http://www.w3.org/1999/xlink".equals(p20.getURI(v6_4)))) {
                                            v0_178.o = v3_132;
                                        }
                                    } else {
                                        switch (v4_44) {
                                            case 81:
                                                String v3_15 = vn.zalopay.ui.androidsvg.M0.t(v3_132);
                                                v0_178.r = v3_15;
                                                if (v3_15.i()) {
                                                    throw new vn.zalopay.ui.androidsvg.SVGParseException("Invalid <use> element. width cannot be negative");
                                                }
                                                break;
                                            case 82:
                                                v0_178.p = vn.zalopay.ui.androidsvg.M0.t(v3_132);
                                                break;
                                            case 83:
                                                v0_178.q = vn.zalopay.ui.androidsvg.M0.t(v3_132);
                                                break;
                                            default:
                                        }
                                    }
                                }
                                v6_4++;
                            }
                            this.b.m(v0_178);
                            this.b = v0_178;
                            return;
                        }
                    case 30:
                        if (this.b == null) {
                            throw new vn.zalopay.ui.androidsvg.SVGParseException("Invalid document. Root element must be <svg>");
                        } else {
                            NumberFormatException v0_133 = new vn.zalopay.ui.androidsvg.s0();
                            v0_133.a = this.a;
                            v0_133.b = this.b;
                            vn.zalopay.ui.androidsvg.M0.h(v0_133, p20);
                            vn.zalopay.ui.androidsvg.M0.g(v0_133, p20);
                            vn.zalopay.ui.androidsvg.M0.n(v0_133, p20);
                            this.b.m(v0_133);
                            this.b = v0_133;
                            return;
                        }
                    default:
                        this.c = 1;
                        this.d = 1;
                        return;
                }
            } else {
                return;
            }
        } else {
            this.d = (this.d + 1);
            return;
        }
    }

    public final void G(String p3)
    {
        if (!this.c) {
            if (!this.e) {
                if (!this.h) {
                    if ((this.b instanceof vn.zalopay.ui.androidsvg.m0)) {
                        this.a(p3);
                    }
                } else {
                    if (this.i == null) {
                        this.i = new StringBuilder(p3.length());
                    }
                    this.i.append(p3);
                    return;
                }
            } else {
                if (this.g == null) {
                    this.g = new StringBuilder(p3.length());
                }
                this.g.append(p3);
                return;
            }
        }
        return;
    }

    public final void H(char[] p2, int p3, int p4)
    {
        if (!this.c) {
            if (!this.e) {
                if (!this.h) {
                    if ((this.b instanceof vn.zalopay.ui.androidsvg.m0)) {
                        this.a(new String(p2, p3, p4));
                    }
                } else {
                    if (this.i == null) {
                        this.i = new StringBuilder(p4);
                    }
                    this.i.append(p2, p3, p4);
                    return;
                }
            } else {
                if (this.g == null) {
                    this.g = new StringBuilder(p4);
                }
                this.g.append(p2, p3, p4);
                return;
            }
        }
        return;
    }

    public final void a(String p4)
    {
        vn.zalopay.ui.androidsvg.Y v0_2;
        vn.zalopay.ui.androidsvg.Y v0_1 = ((vn.zalopay.ui.androidsvg.X) this.b);
        vn.zalopay.ui.androidsvg.q0 v1_6 = v0_1.i.size();
        if (v1_6 != null) {
            v0_2 = ((vn.zalopay.ui.androidsvg.c0) v0_1.i.get((v1_6 - 1)));
        } else {
            v0_2 = 0;
        }
        if (!(v0_2 instanceof vn.zalopay.ui.androidsvg.q0)) {
            vn.zalopay.ui.androidsvg.Y v0_3 = this.b;
            vn.zalopay.ui.androidsvg.q0 v1_2 = new vn.zalopay.ui.androidsvg.q0();
            v1_2.c = p4;
            v0_3.m(v1_2);
            return;
        } else {
            vn.zalopay.ui.androidsvg.Y v0_4 = ((vn.zalopay.ui.androidsvg.q0) v0_2);
            v0_4.c = A1.a.l(new StringBuilder(), v0_4.c, p4);
            return;
        }
    }

    public final void c(String p4, String p5, String p6)
    {
        if (this.c) {
            vn.zalopay.ui.androidsvg.c v0_6 = (this.d - 1);
            this.d = v0_6;
            if (v0_6 == null) {
                this.c = 0;
                return;
            }
        }
        if (("http://www.w3.org/2000/svg".equals(p4)) || ("".equals(p4))) {
            if (p5.length() <= 0) {
                p5 = p6;
            }
            vn.zalopay.ui.androidsvg.t0 v4_5 = ((vn.zalopay.ui.androidsvg.K0) vn.zalopay.ui.androidsvg.K0.e.get(p5));
            if (v4_5 == null) {
                v4_5 = vn.zalopay.ui.androidsvg.K0.d;
            }
            switch (v4_5.ordinal()) {
                case 0:
                case 3:
                case 4:
                case 7:
                case 8:
                case 10:
                case 11:
                case 12:
                case 14:
                case 17:
                case 19:
                case 20:
                case 22:
                case 23:
                case 24:
                case 25:
                case 28:
                case 29:
                case 30:
                    this.b = ((vn.zalopay.ui.androidsvg.c0) this.b).b;
                    return;
                case 1:
                case 2:
                case 6:
                case 9:
                case 13:
                case 15:
                case 16:
                case 18:
                case 27:
                default:
                    break;
                case 5:
                case 26:
                    this.e = 0;
                    if (this.g != null) {
                        vn.zalopay.ui.androidsvg.t0 v4_12 = this.f;
                        if (v4_12 != vn.zalopay.ui.androidsvg.K0.c) {
                            if (v4_12 == vn.zalopay.ui.androidsvg.K0.a) {
                                this.a.getClass();
                            }
                        } else {
                            this.a.getClass();
                        }
                        this.g.setLength(0);
                    }
                    break;
                case 21:
                    vn.zalopay.ui.androidsvg.t0 v4_7 = this.i;
                    if (v4_7 != null) {
                        this.h = 0;
                        vn.zalopay.ui.androidsvg.t0 v4_8 = v4_7.toString();
                        vn.zalopay.ui.androidsvg.K0 v5_2 = new vn.zalopay.ui.androidsvg.p(1);
                        vn.zalopay.ui.androidsvg.t0 v6_1 = this.a;
                        vn.zalopay.ui.androidsvg.c v0_4 = new vn.zalopay.ui.androidsvg.c(v4_8);
                        v0_4.y();
                        v6_1.b.b(v5_2.e(v0_4));
                        this.i.setLength(0);
                        return;
                    }
            }
        }
        return;
    }

    public final vn.zalopay.ui.androidsvg.t0 f(java.io.InputStream p5)
    {
        if (!p5.markSupported()) {
            p5 = new java.io.BufferedInputStream(p5);
        }
        p5.mark(3);
        Throwable v2_1 = (p5.read() + (p5.read() << 8));
        p5.reset();
    }
}
