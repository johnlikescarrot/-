package vn.ai.trueid.sdk.navigation;
public final class TrueIDEKYCCaptureConfig {
    private final String appId;
    private final vn.ai.trueid.sdk.models.ConfigEndPoint configEndPoint;
    private final String configHeader;
    private final String configUI;
    private final int livenessType;
    private final String sdkKey;
    private final String signVersion;

    public TrueIDEKYCCaptureConfig(String p1, String p2, String p3, String p4, int p5, vn.ai.trueid.sdk.models.ConfigEndPoint p6, String p7)
    {
        this.appId = p1;
        this.configHeader = p2;
        this.sdkKey = p3;
        this.signVersion = p4;
        this.livenessType = p5;
        this.configEndPoint = p6;
        this.configUI = p7;
        return;
    }

    public synthetic TrueIDEKYCCaptureConfig(String p20, String p21, String p22, String p23, int p24, vn.ai.trueid.sdk.models.ConfigEndPoint p25, String p26, int p27, kotlin.jvm.internal.DefaultConstructorMarker p28)
    {
        String v5;
        if ((p27 & 4) == 0) {
            v5 = p22;
        } else {
            v5 = "";
        }
        String v6;
        if ((p27 & 8) == 0) {
            v6 = p23;
        } else {
            v6 = "2";
        }
        int v7;
        if ((p27 & 16) == 0) {
            v7 = p24;
        } else {
            v7 = 0;
        }
        vn.ai.trueid.sdk.models.ConfigEndPoint v8_0;
        if ((p27 & 32) == 0) {
            v8_0 = p25;
        } else {
            v8_0 = new vn.ai.trueid.sdk.models.ConfigEndPoint("", 0, 0, 0, 0, 0, 0, 0, 254, 0);
        }
        String v9_1;
        if ((p27 & 64) == 0) {
            v9_1 = p26;
        } else {
            v9_1 = "";
        }
        this(p20, p21, v5, v6, v7, v8_0, v9_1);
        return;
    }

    public static synthetic vn.ai.trueid.sdk.navigation.TrueIDEKYCCaptureConfig copy$default(vn.ai.trueid.sdk.navigation.TrueIDEKYCCaptureConfig p0, String p1, String p2, String p3, String p4, int p5, vn.ai.trueid.sdk.models.ConfigEndPoint p6, String p7, int p8, Object p9)
    {
        if ((p8 & 1) != 0) {
            p1 = p0.appId;
        }
        if ((p8 & 2) != 0) {
            p2 = p0.configHeader;
        }
        if ((p8 & 4) != 0) {
            p3 = p0.sdkKey;
        }
        if ((p8 & 8) != 0) {
            p4 = p0.signVersion;
        }
        if ((p8 & 16) != 0) {
            p5 = p0.livenessType;
        }
        if ((p8 & 32) != 0) {
            p6 = p0.configEndPoint;
        }
        if ((p8 & 64) != 0) {
            p7 = p0.configUI;
        }
        return p0.copy(p1, p2, p3, p4, p5, p6, p7);
    }

    public final String component1()
    {
        return this.appId;
    }

    public final String component2()
    {
        return this.configHeader;
    }

    public final String component3()
    {
        return this.sdkKey;
    }

    public final String component4()
    {
        return this.signVersion;
    }

    public final int component5()
    {
        return this.livenessType;
    }

    public final vn.ai.trueid.sdk.models.ConfigEndPoint component6()
    {
        return this.configEndPoint;
    }

    public final String component7()
    {
        return this.configUI;
    }

    public final vn.ai.trueid.sdk.navigation.TrueIDEKYCCaptureConfig copy(String p9, String p10, String p11, String p12, int p13, vn.ai.trueid.sdk.models.ConfigEndPoint p14, String p15)
    {
        return new vn.ai.trueid.sdk.navigation.TrueIDEKYCCaptureConfig(p9, p10, p11, p12, p13, p14, p15);
    }

    public boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5 instanceof vn.ai.trueid.sdk.navigation.TrueIDEKYCCaptureConfig)) {
                if (kotlin.jvm.internal.Intrinsics.areEqual(this.appId, ((vn.ai.trueid.sdk.navigation.TrueIDEKYCCaptureConfig) p5).appId)) {
                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.configHeader, ((vn.ai.trueid.sdk.navigation.TrueIDEKYCCaptureConfig) p5).configHeader)) {
                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.sdkKey, ((vn.ai.trueid.sdk.navigation.TrueIDEKYCCaptureConfig) p5).sdkKey)) {
                            if (kotlin.jvm.internal.Intrinsics.areEqual(this.signVersion, ((vn.ai.trueid.sdk.navigation.TrueIDEKYCCaptureConfig) p5).signVersion)) {
                                if (this.livenessType == ((vn.ai.trueid.sdk.navigation.TrueIDEKYCCaptureConfig) p5).livenessType) {
                                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.configEndPoint, ((vn.ai.trueid.sdk.navigation.TrueIDEKYCCaptureConfig) p5).configEndPoint)) {
                                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.configUI, ((vn.ai.trueid.sdk.navigation.TrueIDEKYCCaptureConfig) p5).configUI)) {
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
            return 1;
        }
    }

    public final String getAppId()
    {
        return this.appId;
    }

    public final vn.ai.trueid.sdk.models.ConfigEndPoint getConfigEndPoint()
    {
        return this.configEndPoint;
    }

    public final String getConfigHeader()
    {
        return this.configHeader;
    }

    public final String getConfigUI()
    {
        return this.configUI;
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
        return (this.configUI.hashCode() + ((this.configEndPoint.hashCode() + Kb.e.v(this.livenessType, e5.i.c(e5.i.c(e5.i.c((this.appId.hashCode() * 31), 31, this.configHeader), 31, this.sdkKey), 31, this.signVersion), 31)) * 31));
    }

    public String toString()
    {
        String v2 = this.sdkKey;
        String v3 = this.signVersion;
        int v4 = this.livenessType;
        vn.ai.trueid.sdk.models.ConfigEndPoint v5 = this.configEndPoint;
        String v6 = this.configUI;
        String v0_1 = androidx.camera.camera2.internal.G.i("TrueIDEKYCCaptureConfig(appId=", this.appId, ", configHeader=", this.configHeader, ", sdkKey=");
        q9.H.p(v0_1, v2, ", signVersion=", v3, ", livenessType=");
        v0_1.append(v4);
        v0_1.append(", configEndPoint=");
        v0_1.append(v5);
        v0_1.append(", configUI=");
        return A1.a.l(v0_1, v6, ")");
    }
}
