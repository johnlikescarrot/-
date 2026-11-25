package vn.com.zalopay.auth.data.model.smartotp;
public final class SmartOTPRegistrationResponseBody {
    public static final int $stable;
    private final int algorithm;
    private final int digits;
    private final int period;
    private final String secretKey;
    private final String smartOTPId;

    public SmartOTPRegistrationResponseBody()
    {
        this(0, 0, 0, 0, 0, 31, 0);
        return;
    }

    public SmartOTPRegistrationResponseBody(int p1, int p2, int p3, String p4, String p5)
    {
        this.algorithm = p1;
        this.digits = p2;
        this.period = p3;
        this.secretKey = p4;
        this.smartOTPId = p5;
        return;
    }

    public synthetic SmartOTPRegistrationResponseBody(int p2, int p3, int p4, String p5, String p6, int p7, kotlin.jvm.internal.DefaultConstructorMarker p8)
    {
        if ((p7 & 1) != 0) {
            p2 = 0;
        }
        if ((p7 & 2) != 0) {
            p3 = 6;
        }
        if ((p7 & 4) != 0) {
            p4 = 30;
        }
        if ((p7 & 8) != 0) {
            p5 = "";
        }
        int v6_1;
        int v8_1;
        vn.com.zalopay.auth.data.model.smartotp.SmartOTPRegistrationResponseBody v5_1;
        int v4_1;
        vn.com.zalopay.auth.data.model.smartotp.SmartOTPRegistrationResponseBody v3_1;
        int v7_2;
        if ((p7 & 16) == 0) {
            v8_1 = p6;
            v7_2 = p5;
            v5_1 = p3;
            v6_1 = p4;
            v3_1 = this;
            v4_1 = p2;
        } else {
            v8_1 = "";
            v6_1 = p4;
            v7_2 = p5;
            v4_1 = p2;
            v5_1 = p3;
            v3_1 = this;
        }
        v3_1(v4_1, v5_1, v6_1, v7_2, v8_1);
        return;
    }

    public static synthetic vn.com.zalopay.auth.data.model.smartotp.SmartOTPRegistrationResponseBody copy$default(vn.com.zalopay.auth.data.model.smartotp.SmartOTPRegistrationResponseBody p0, int p1, int p2, int p3, String p4, String p5, int p6, Object p7)
    {
        if ((p6 & 1) != 0) {
            p1 = p0.algorithm;
        }
        if ((p6 & 2) != 0) {
            p2 = p0.digits;
        }
        if ((p6 & 4) != 0) {
            p3 = p0.period;
        }
        if ((p6 & 8) != 0) {
            p4 = p0.secretKey;
        }
        if ((p6 & 16) != 0) {
            p5 = p0.smartOTPId;
        }
        return p0.copy(p1, p2, p3, p4, p5);
    }

    public final int component1()
    {
        return this.algorithm;
    }

    public final int component2()
    {
        return this.digits;
    }

    public final int component3()
    {
        return this.period;
    }

    public final String component4()
    {
        return this.secretKey;
    }

    public final String component5()
    {
        return this.smartOTPId;
    }

    public final vn.com.zalopay.auth.data.model.smartotp.SmartOTPRegistrationResponseBody copy(int p7, int p8, int p9, String p10, String p11)
    {
        return new vn.com.zalopay.auth.data.model.smartotp.SmartOTPRegistrationResponseBody(p7, p8, p9, p10, p11);
    }

    public boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5 instanceof vn.com.zalopay.auth.data.model.smartotp.SmartOTPRegistrationResponseBody)) {
                if (this.algorithm == ((vn.com.zalopay.auth.data.model.smartotp.SmartOTPRegistrationResponseBody) p5).algorithm) {
                    if (this.digits == ((vn.com.zalopay.auth.data.model.smartotp.SmartOTPRegistrationResponseBody) p5).digits) {
                        if (this.period == ((vn.com.zalopay.auth.data.model.smartotp.SmartOTPRegistrationResponseBody) p5).period) {
                            if (kotlin.jvm.internal.Intrinsics.areEqual(this.secretKey, ((vn.com.zalopay.auth.data.model.smartotp.SmartOTPRegistrationResponseBody) p5).secretKey)) {
                                if (kotlin.jvm.internal.Intrinsics.areEqual(this.smartOTPId, ((vn.com.zalopay.auth.data.model.smartotp.SmartOTPRegistrationResponseBody) p5).smartOTPId)) {
                                    return 1;
                                } else {
                                    return 0;
                                }
                            } else {
                                return 0;
                            }
                        } else {
                            return 0;
                        }
                    } else {
                        return 0;
                    }
                } else {
                    return 0;
                }
            } else {
                return 0;
            }
        } else {
            return 1;
        }
    }

    public final int getAlgorithm()
    {
        return this.algorithm;
    }

    public final int getDigits()
    {
        return this.digits;
    }

    public final int getPeriod()
    {
        return this.period;
    }

    public final String getSecretKey()
    {
        return this.secretKey;
    }

    public final String getSmartOTPId()
    {
        return this.smartOTPId;
    }

    public int hashCode()
    {
        return (this.smartOTPId.hashCode() + e5.i.c(Kb.e.v(this.period, Kb.e.v(this.digits, (Integer.hashCode(this.algorithm) * 31), 31), 31), 31, this.secretKey));
    }

    public String toString()
    {
        int v2 = this.period;
        String v3 = this.secretKey;
        String v4 = this.smartOTPId;
        String v0_2 = androidx.activity.result.e.s(this.algorithm, this.digits, "SmartOTPRegistrationResponseBody(algorithm=", ", digits=", ", period=");
        e5.i.s(v2, ", secretKey=", v3, ", smartOTPId=", v0_2);
        return A1.a.l(v0_2, v4, ")");
    }
}
