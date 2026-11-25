package vn.ai.trueid.sdk.models;
public final class NFCConfig {
    private final String accesstokenEndPoint;
    private final String appId;
    private final String appSecret;
    private final org.json.JSONObject configHeader;
    private final String createRequestEndPoint;
    private final String domain;
    private final String nfcEndPoint;

    public NFCConfig()
    {
        this(0, 0, 0, 0, 0, 0, 0, 127, 0);
        return;
    }

    public NFCConfig(String p1, String p2, String p3, String p4, String p5, String p6, org.json.JSONObject p7)
    {
        this.domain = p1;
        this.nfcEndPoint = p2;
        this.createRequestEndPoint = p3;
        this.accesstokenEndPoint = p4;
        this.appId = p5;
        this.appSecret = p6;
        this.configHeader = p7;
        return;
    }

    public synthetic NFCConfig(String p2, String p3, String p4, String p5, String p6, String p7, org.json.JSONObject p8, int p9, kotlin.jvm.internal.DefaultConstructorMarker p10)
    {
        if ((p9 & 1) != 0) {
            p2 = "https://";
        }
        if ((p9 & 2) != 0) {
            p3 = "";
        }
        if ((p9 & 4) != 0) {
            p4 = "";
        }
        if ((p9 & 8) != 0) {
            p5 = "";
        }
        if ((p9 & 16) != 0) {
            p6 = "";
        }
        if ((p9 & 32) != 0) {
            p7 = "";
        }
        if ((p9 & 64) != 0) {
            p8 = new org.json.JSONObject();
        }
        this(p2, p3, p4, p5, p6, p7, p8);
        return;
    }

    public static synthetic vn.ai.trueid.sdk.models.NFCConfig copy$default(vn.ai.trueid.sdk.models.NFCConfig p0, String p1, String p2, String p3, String p4, String p5, String p6, org.json.JSONObject p7, int p8, Object p9)
    {
        if ((p8 & 1) != 0) {
            p1 = p0.domain;
        }
        if ((p8 & 2) != 0) {
            p2 = p0.nfcEndPoint;
        }
        if ((p8 & 4) != 0) {
            p3 = p0.createRequestEndPoint;
        }
        if ((p8 & 8) != 0) {
            p4 = p0.accesstokenEndPoint;
        }
        if ((p8 & 16) != 0) {
            p5 = p0.appId;
        }
        if ((p8 & 32) != 0) {
            p6 = p0.appSecret;
        }
        if ((p8 & 64) != 0) {
            p7 = p0.configHeader;
        }
        return p0.copy(p1, p2, p3, p4, p5, p6, p7);
    }

    public final String component1()
    {
        return this.domain;
    }

    public final String component2()
    {
        return this.nfcEndPoint;
    }

    public final String component3()
    {
        return this.createRequestEndPoint;
    }

    public final String component4()
    {
        return this.accesstokenEndPoint;
    }

    public final String component5()
    {
        return this.appId;
    }

    public final String component6()
    {
        return this.appSecret;
    }

    public final org.json.JSONObject component7()
    {
        return this.configHeader;
    }

    public final vn.ai.trueid.sdk.models.NFCConfig copy(String p9, String p10, String p11, String p12, String p13, String p14, org.json.JSONObject p15)
    {
        return new vn.ai.trueid.sdk.models.NFCConfig(p9, p10, p11, p12, p13, p14, p15);
    }

    public boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5 instanceof vn.ai.trueid.sdk.models.NFCConfig)) {
                if (kotlin.jvm.internal.Intrinsics.areEqual(this.domain, ((vn.ai.trueid.sdk.models.NFCConfig) p5).domain)) {
                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.nfcEndPoint, ((vn.ai.trueid.sdk.models.NFCConfig) p5).nfcEndPoint)) {
                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.createRequestEndPoint, ((vn.ai.trueid.sdk.models.NFCConfig) p5).createRequestEndPoint)) {
                            if (kotlin.jvm.internal.Intrinsics.areEqual(this.accesstokenEndPoint, ((vn.ai.trueid.sdk.models.NFCConfig) p5).accesstokenEndPoint)) {
                                if (kotlin.jvm.internal.Intrinsics.areEqual(this.appId, ((vn.ai.trueid.sdk.models.NFCConfig) p5).appId)) {
                                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.appSecret, ((vn.ai.trueid.sdk.models.NFCConfig) p5).appSecret)) {
                                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.configHeader, ((vn.ai.trueid.sdk.models.NFCConfig) p5).configHeader)) {
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

    public final String getAccesstokenEndPoint()
    {
        return this.accesstokenEndPoint;
    }

    public final String getAppId()
    {
        return this.appId;
    }

    public final String getAppSecret()
    {
        return this.appSecret;
    }

    public final org.json.JSONObject getConfigHeader()
    {
        return this.configHeader;
    }

    public final String getCreateRequestEndPoint()
    {
        return this.createRequestEndPoint;
    }

    public final String getDomain()
    {
        return this.domain;
    }

    public final String getNfcEndPoint()
    {
        return this.nfcEndPoint;
    }

    public int hashCode()
    {
        return (this.configHeader.hashCode() + e5.i.c(e5.i.c(e5.i.c(e5.i.c(e5.i.c((this.domain.hashCode() * 31), 31, this.nfcEndPoint), 31, this.createRequestEndPoint), 31, this.accesstokenEndPoint), 31, this.appId), 31, this.appSecret));
    }

    public final org.json.JSONObject toJson()
    {
        return new org.json.JSONObject(this.toMap());
    }

    public final java.util.Map toMap()
    {
        kotlin.Pair v2 = kotlin.TuplesKt.to("domain", this.domain);
        kotlin.TuplesKt.to("nfcEndPoint", this.nfcEndPoint);
        kotlin.TuplesKt.to("createRequestEndPoint", this.createRequestEndPoint);
        kotlin.TuplesKt.to("accesstokenEndPoint", this.accesstokenEndPoint);
        kotlin.TuplesKt.to("appId", this.appId);
        kotlin.TuplesKt.to("appSecret", this.appSecret);
        return kotlin.collections.a0.mapOf(new kotlin.Pair[] {v2, kotlin.TuplesKt.to("configHeader", this.configHeader.toString())}));
    }

    public String toString()
    {
        String v2_1 = this.createRequestEndPoint;
        String v3 = this.accesstokenEndPoint;
        String v4 = this.appId;
        String v5 = this.appSecret;
        org.json.JSONObject v6 = this.configHeader;
        String v0_1 = androidx.camera.camera2.internal.G.i("NFCConfig(domain=", this.domain, ", nfcEndPoint=", this.nfcEndPoint, ", createRequestEndPoint=");
        q9.H.p(v0_1, v2_1, ", accesstokenEndPoint=", v3, ", appId=");
        q9.H.p(v0_1, v4, ", appSecret=", v5, ", configHeader=");
        v0_1.append(v6);
        v0_1.append(")");
        return v0_1.toString();
    }
}
