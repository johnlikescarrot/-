package vn.com.vng.zalopay.livenessdetection.presentation.domain.model;
public abstract class a {

    public static final float a(Float p2, float p3)
    {
        if (p2 != 0) {
            float v0_1 = p2.floatValue();
            if ((Float.isNaN(v0_1)) || ((0 > v0_1) || (v0_1 > 1065353216))) {
                p2 = 0;
            }
            if (p2 != 0) {
                return p2.floatValue();
            }
        }
        return p3;
    }

    public static final vn.com.vng.zalopay.livenessdetection.presentation.domain.model.SelfieCheckConfig b(vn.com.vng.zalopay.livenessdetection.presentation.domain.model.SelfieCheckConfigResponse p13)
    {
        if (p13 != null) {
            int v0_11;
            int v0_3 = p13.c();
            int v1_0 = 0;
            if (v0_3 == 0) {
                v0_11 = 0;
            } else {
                v0_11 = v0_3.b();
            }
            int v0_2;
            float v4_0 = vn.com.vng.zalopay.livenessdetection.presentation.domain.model.a.a(v0_11, 1048576000);
            int v0_1 = p13.c();
            if (v0_1 == 0) {
                v0_2 = 0;
            } else {
                v0_2 = v0_1.a();
            }
            int v0_5;
            float v5_1 = vn.com.vng.zalopay.livenessdetection.presentation.domain.model.a.a(v0_2, 1045220557);
            int v0_4 = p13.c();
            if (v0_4 == 0) {
                v0_5 = 0;
            } else {
                v0_5 = v0_4.c();
            }
            int v0_7;
            float v6_1 = vn.com.vng.zalopay.livenessdetection.presentation.domain.model.a.a(v0_5, 1036831949);
            int v0_6 = p13.a();
            if (v0_6 == 0) {
                v0_7 = 0;
            } else {
                v0_7 = v0_6.c();
            }
            int v0_9;
            float v7_1 = vn.com.vng.zalopay.livenessdetection.presentation.domain.model.a.a(v0_7, 1036831949);
            int v0_8 = p13.a();
            if (v0_8 == 0) {
                v0_9 = 0;
            } else {
                v0_9 = v0_8.b();
            }
            int v0_13;
            float v8_1 = vn.com.vng.zalopay.livenessdetection.presentation.domain.model.a.a(v0_9, 1043878380);
            int v0_10 = p13.a();
            if (v0_10 == 0) {
                v0_13 = 3;
            } else {
                int v0_12 = v0_10.a();
                if (v0_12 == 0) {
                } else {
                    if (v0_12.intValue() < 1) {
                        v0_12 = 0;
                    }
                    if (v0_12 == 0) {
                    } else {
                        v0_13 = v0_12.intValue();
                    }
                }
            }
            int v0_15;
            int v9_2 = v0_13;
            int v0_14 = p13.b();
            if (v0_14 == 0) {
                v0_15 = 1053609165;
            } else {
                vn.com.vng.zalopay.livenessdetection.presentation.domain.model.SelfieCheckConfig v3_4 = v0_14.floatValue();
                if ((!Float.isNaN(v3_4)) && (v3_4 <= 1065353216)) {
                    v1_0 = v0_14;
                }
                if (v1_0 == 0) {
                } else {
                    v0_15 = v1_0.floatValue();
                }
            }
            int v0_17;
            int v10_2 = v0_15;
            int v0_16 = p13.e();
            if (v0_16 == 0) {
                v0_17 = 0;
            } else {
                v0_17 = v0_16.booleanValue();
            }
            return new vn.com.vng.zalopay.livenessdetection.presentation.domain.model.SelfieCheckConfig(v4_0, v5_1, v6_1, v7_1, v8_1, v9_2, v10_2, v0_17, vn.com.vng.zalopay.livenessdetection.presentation.domain.model.a.a(p13.d(), 1048576000));
        } else {
            return new vn.com.vng.zalopay.livenessdetection.presentation.domain.model.SelfieCheckConfig(0, 0, 0, 0, 0, 0, 0, 0, 0, 511, 0);
        }
    }
}
