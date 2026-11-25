package vn.ai.trueid.sdk.models;
public final class EkycConfig {
    private final String appId;
    private final String appSecret;
    private final String authDomain;
    private vn.ai.trueid.sdk.models.ConfigEndPoint configEndPoint;
    private final String domain;
    private int livenessType;
    private final String sdkKey;
    private final String signVersion;

    public EkycConfig()
    {
        this(0, 0, 0, 0, 0, 0, 0, 0, 255, 0);
        return;
    }

    public EkycConfig(String p12, String p13, String p14)
    {
        this(p12, p12, p13, p14, "", "1", new vn.ai.trueid.sdk.models.ConfigEndPoint(p12), 0, 128, 0);
        return;
    }

    public EkycConfig(String p12, String p13, String p14, String p15, String p16, String p17)
    {
        this(androidx.activity.result.e.n(p12, p13), androidx.activity.result.e.n(p14, p15), p16, p17, "", "1", new vn.ai.trueid.sdk.models.ConfigEndPoint(androidx.activity.result.e.n(p12, p13), androidx.activity.result.e.n(p14, p15)), 0, 128, 0);
        return;
    }

    public EkycConfig(String p12, String p13, String p14, String p15, String p16, String p17, String p18)
    {
        this(p12, p12, p16, p17, p18, "1", new vn.ai.trueid.sdk.models.ConfigEndPoint(androidx.activity.result.e.n(p12, p13), androidx.activity.result.e.n(p14, p15)), 0, 128, 0);
        return;
    }

    public EkycConfig(String p12, String p13, String p14, String p15, String p16, String p17, String p18, vn.ai.trueid.sdk.models.ConfigEndPoint p19)
    {
        this(androidx.activity.result.e.n(p12, p13), androidx.activity.result.e.n(p14, p15), p16, p17, p18, "1", p19, 0, 128, 0);
        return;
    }

    public EkycConfig(String p1, String p2, String p3, String p4, String p5, String p6, vn.ai.trueid.sdk.models.ConfigEndPoint p7, int p8)
    {
        this.domain = p1;
        this.authDomain = p2;
        this.appId = p3;
        this.appSecret = p4;
        this.sdkKey = p5;
        this.signVersion = p6;
        this.configEndPoint = p7;
        this.livenessType = p8;
        return;
    }

    public synthetic EkycConfig(String p2, String p3, String p4, String p5, String p6, String p7, vn.ai.trueid.sdk.models.ConfigEndPoint p8, int p9, int p10, kotlin.jvm.internal.DefaultConstructorMarker p11)
    {
        if ((p10 & 1) != 0) {
            p2 = "";
        }
        if ((p10 & 2) != 0) {
            p3 = "";
        }
        if ((p10 & 4) != 0) {
            p4 = "";
        }
        if ((p10 & 8) != 0) {
            p5 = "";
        }
        if ((p10 & 16) != 0) {
            p6 = "";
        }
        if ((p10 & 32) != 0) {
            p7 = "1";
        }
        if ((p10 & 64) != 0) {
            p8 = new vn.ai.trueid.sdk.models.ConfigEndPoint(p2, p3);
        }
        if ((p10 & 128) != 0) {
            p9 = 0;
        }
        this(p2, p3, p4, p5, p6, p7, p8, p9);
        return;
    }

    public EkycConfig(String p12, String p13, String p14, String p15, String p16, vn.ai.trueid.sdk.models.ConfigEndPoint p17)
    {
        this(androidx.activity.result.e.n(p12, p13), androidx.activity.result.e.n(p12, p13), p14, p15, p16, "1", p17, 0, 128, 0);
        return;
    }

    public EkycConfig(String p12, String p13, String p14, vn.ai.trueid.sdk.models.ConfigEndPoint p15)
    {
        this(p12, p12, p13, p14, "", "1", p15, 0, 128, 0);
        return;
    }

    public EkycConfig(String p12, String p13, String p14, vn.ai.trueid.sdk.models.ConfigEndPoint p15, String p16)
    {
        this(p12, p12, p13, p14, p16, "1", p15, 0, 128, 0);
        return;
    }

    public static synthetic vn.ai.trueid.sdk.models.EkycConfig copy$default(vn.ai.trueid.sdk.models.EkycConfig p0, String p1, String p2, String p3, String p4, String p5, String p6, vn.ai.trueid.sdk.models.ConfigEndPoint p7, int p8, int p9, Object p10)
    {
        if ((p9 & 1) != 0) {
            p1 = p0.domain;
        }
        if ((p9 & 2) != 0) {
            p2 = p0.authDomain;
        }
        if ((p9 & 4) != 0) {
            p3 = p0.appId;
        }
        if ((p9 & 8) != 0) {
            p4 = p0.appSecret;
        }
        if ((p9 & 16) != 0) {
            p5 = p0.sdkKey;
        }
        if ((p9 & 32) != 0) {
            p6 = p0.signVersion;
        }
        if ((p9 & 64) != 0) {
            p7 = p0.configEndPoint;
        }
        if ((p9 & 128) != 0) {
            p8 = p0.livenessType;
        }
        return p0.copy(p1, p2, p3, p4, p5, p6, p7, p8);
    }

    public final String component1()
    {
        return this.domain;
    }

    public final String component2()
    {
        return this.authDomain;
    }

    public final String component3()
    {
        return this.appId;
    }

    public final String component4()
    {
        return this.appSecret;
    }

    public final String component5()
    {
        return this.sdkKey;
    }

    public final String component6()
    {
        return this.signVersion;
    }

    public final vn.ai.trueid.sdk.models.ConfigEndPoint component7()
    {
        return this.configEndPoint;
    }

    public final int component8()
    {
        return this.livenessType;
    }

    public final vn.ai.trueid.sdk.models.EkycConfig copy(String p10, String p11, String p12, String p13, String p14, String p15, vn.ai.trueid.sdk.models.ConfigEndPoint p16, int p17)
    {
        return new vn.ai.trueid.sdk.models.EkycConfig(p10, p11, p12, p13, p14, p15, p16, p17);
    }

    public boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5 instanceof vn.ai.trueid.sdk.models.EkycConfig)) {
                if (kotlin.jvm.internal.Intrinsics.areEqual(this.domain, ((vn.ai.trueid.sdk.models.EkycConfig) p5).domain)) {
                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.authDomain, ((vn.ai.trueid.sdk.models.EkycConfig) p5).authDomain)) {
                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.appId, ((vn.ai.trueid.sdk.models.EkycConfig) p5).appId)) {
                            if (kotlin.jvm.internal.Intrinsics.areEqual(this.appSecret, ((vn.ai.trueid.sdk.models.EkycConfig) p5).appSecret)) {
                                if (kotlin.jvm.internal.Intrinsics.areEqual(this.sdkKey, ((vn.ai.trueid.sdk.models.EkycConfig) p5).sdkKey)) {
                                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.signVersion, ((vn.ai.trueid.sdk.models.EkycConfig) p5).signVersion)) {
                                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.configEndPoint, ((vn.ai.trueid.sdk.models.EkycConfig) p5).configEndPoint)) {
                                            if (this.livenessType == ((vn.ai.trueid.sdk.models.EkycConfig) p5).livenessType) {
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

    public final String getAppId()
    {
        return this.appId;
    }

    public final String getAppSecret()
    {
        return this.appSecret;
    }

    public final String getAuthDomain()
    {
        return this.authDomain;
    }

    public final vn.ai.trueid.sdk.models.ConfigEndPoint getConfigEndPoint()
    {
        return this.configEndPoint;
    }

    public final String getDomain()
    {
        return this.domain;
    }

    public final int getLivenessType()
    {
        return this.livenessType;
    }

    public final String getSdkKey()
    {
        return this.sdkKey;
    }

    public final String getSignVersion()
    {
        return this.signVersion;
    }

    public int hashCode()
    {
        return (Integer.hashCode(this.livenessType) + ((this.configEndPoint.hashCode() + e5.i.c(e5.i.c(e5.i.c(e5.i.c(e5.i.c((this.domain.hashCode() * 31), 31, this.authDomain), 31, this.appId), 31, this.appSecret), 31, this.sdkKey), 31, this.signVersion)) * 31));
    }

    public final void setConfigEndPoint(vn.ai.trueid.sdk.models.ConfigEndPoint p1)
    {
        this.configEndPoint = p1;
        return;
    }

    public final void setLivenessType(int p1)
    {
        this.livenessType = p1;
        return;
    }

    public String toString()
    {
        String v2_1 = this.appId;
        String v3 = this.appSecret;
        String v4 = this.sdkKey;
        String v5 = this.signVersion;
        vn.ai.trueid.sdk.models.ConfigEndPoint v6 = this.configEndPoint;
        int v7 = this.livenessType;
        String v0_1 = androidx.camera.camera2.internal.G.i("EkycConfig(domain=", this.domain, ", authDomain=", this.authDomain, ", appId=");
        q9.H.p(v0_1, v2_1, ", appSecret=", v3, ", sdkKey=");
        q9.H.p(v0_1, v4, ", signVersion=", v5, ", configEndPoint=");
        v0_1.append(v6);
        v0_1.append(", livenessType=");
        v0_1.append(v7);
        v0_1.append(")");
        return v0_1.toString();
    }
}
