package vn.ai.trueid.sdk.models;
public final class ConfigEndPoint {
    private final String accesstoken;
    private final String back;
    private final String complete;
    private final String createrequest;
    private final String front;
    private final String nfcrar;
    private final String ocr;
    private final String selfie;

    public ConfigEndPoint()
    {
        this(0, 0, 0, 0, 0, 0, 0, 0, 255, 0);
        return;
    }

    public ConfigEndPoint(String p11)
    {
        this(androidx.activity.result.e.n(p11, "/accessToken"), androidx.activity.result.e.n(p11, "/request/create"), androidx.activity.result.e.n(p11, "/id/verify/front"), androidx.activity.result.e.n(p11, "/id/verify/back"), androidx.activity.result.e.n(p11, "/face/liveness"), androidx.activity.result.e.n(p11, "/id/ocr"), androidx.activity.result.e.n(p11, "/complete"), androidx.activity.result.e.n(p11, "/ekyc/v2.0/nfcqr/verify"));
        return;
    }

    public ConfigEndPoint(String p11, String p12)
    {
        this(androidx.activity.result.e.n(p12, "/accessToken"), androidx.activity.result.e.n(p11, "/request/create"), androidx.activity.result.e.n(p11, "/id/verify/front"), androidx.activity.result.e.n(p11, "/id/verify/back"), androidx.activity.result.e.n(p11, "/face/liveness"), androidx.activity.result.e.n(p11, "/id/ocr"), androidx.activity.result.e.n(p11, "/complete"), androidx.activity.result.e.n(p11, "/ekyc/v2.0/nfcqr/verify"));
        return;
    }

    public ConfigEndPoint(String p1, String p2, String p3, String p4, String p5, String p6, String p7, String p8)
    {
        this.accesstoken = p1;
        this.createrequest = p2;
        this.front = p3;
        this.back = p4;
        this.selfie = p5;
        this.ocr = p6;
        this.complete = p7;
        this.nfcrar = p8;
        return;
    }

    public synthetic ConfigEndPoint(String p1, String p2, String p3, String p4, String p5, String p6, String p7, String p8, int p9, kotlin.jvm.internal.DefaultConstructorMarker p10)
    {
        if ((p9 & 1) != 0) {
            p1 = "/v1/oauth/accessToken";
        }
        if ((p9 & 2) != 0) {
            p2 = "/ekyc/v1.6/request/create";
        }
        if ((p9 & 4) != 0) {
            p3 = "/ekyc/v1.3/id/verify/front";
        }
        if ((p9 & 8) != 0) {
            p4 = "/ekyc/v1.3/id/verify/back";
        }
        if ((p9 & 16) != 0) {
            p5 = "/ekyc/v1.3/face/liveness";
        }
        if ((p9 & 32) != 0) {
            p6 = "/ekyc/v1.3/id/ocr";
        }
        if ((p9 & 64) != 0) {
            p7 = "/ekyc/v1.3/complete";
        }
        if ((p9 & 128) != 0) {
            p8 = "/ekyc/v2.0/nfcqr/verify";
        }
        this(p1, p2, p3, p4, p5, p6, p7, p8);
        return;
    }

    public static synthetic vn.ai.trueid.sdk.models.ConfigEndPoint copy$default(vn.ai.trueid.sdk.models.ConfigEndPoint p0, String p1, String p2, String p3, String p4, String p5, String p6, String p7, String p8, int p9, Object p10)
    {
        if ((p9 & 1) != 0) {
            p1 = p0.accesstoken;
        }
        if ((p9 & 2) != 0) {
            p2 = p0.createrequest;
        }
        if ((p9 & 4) != 0) {
            p3 = p0.front;
        }
        if ((p9 & 8) != 0) {
            p4 = p0.back;
        }
        if ((p9 & 16) != 0) {
            p5 = p0.selfie;
        }
        if ((p9 & 32) != 0) {
            p6 = p0.ocr;
        }
        if ((p9 & 64) != 0) {
            p7 = p0.complete;
        }
        if ((p9 & 128) != 0) {
            p8 = p0.nfcrar;
        }
        return p0.copy(p1, p2, p3, p4, p5, p6, p7, p8);
    }

    public final String component1()
    {
        return this.accesstoken;
    }

    public final String component2()
    {
        return this.createrequest;
    }

    public final String component3()
    {
        return this.front;
    }

    public final String component4()
    {
        return this.back;
    }

    public final String component5()
    {
        return this.selfie;
    }

    public final String component6()
    {
        return this.ocr;
    }

    public final String component7()
    {
        return this.complete;
    }

    public final String component8()
    {
        return this.nfcrar;
    }

    public final vn.ai.trueid.sdk.models.ConfigEndPoint copy(String p10, String p11, String p12, String p13, String p14, String p15, String p16, String p17)
    {
        return new vn.ai.trueid.sdk.models.ConfigEndPoint(p10, p11, p12, p13, p14, p15, p16, p17);
    }

    public boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5 instanceof vn.ai.trueid.sdk.models.ConfigEndPoint)) {
                if (kotlin.jvm.internal.Intrinsics.areEqual(this.accesstoken, ((vn.ai.trueid.sdk.models.ConfigEndPoint) p5).accesstoken)) {
                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.createrequest, ((vn.ai.trueid.sdk.models.ConfigEndPoint) p5).createrequest)) {
                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.front, ((vn.ai.trueid.sdk.models.ConfigEndPoint) p5).front)) {
                            if (kotlin.jvm.internal.Intrinsics.areEqual(this.back, ((vn.ai.trueid.sdk.models.ConfigEndPoint) p5).back)) {
                                if (kotlin.jvm.internal.Intrinsics.areEqual(this.selfie, ((vn.ai.trueid.sdk.models.ConfigEndPoint) p5).selfie)) {
                                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.ocr, ((vn.ai.trueid.sdk.models.ConfigEndPoint) p5).ocr)) {
                                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.complete, ((vn.ai.trueid.sdk.models.ConfigEndPoint) p5).complete)) {
                                            if (kotlin.jvm.internal.Intrinsics.areEqual(this.nfcrar, ((vn.ai.trueid.sdk.models.ConfigEndPoint) p5).nfcrar)) {
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

    public final String getAccesstoken()
    {
        return this.accesstoken;
    }

    public final String getBack()
    {
        return this.back;
    }

    public final String getComplete()
    {
        return this.complete;
    }

    public final String getCreaterequest()
    {
        return this.createrequest;
    }

    public final String getFront()
    {
        return this.front;
    }

    public final String getNfcrar()
    {
        return this.nfcrar;
    }

    public final String getOcr()
    {
        return this.ocr;
    }

    public final String getSelfie()
    {
        return this.selfie;
    }

    public int hashCode()
    {
        return (this.nfcrar.hashCode() + e5.i.c(e5.i.c(e5.i.c(e5.i.c(e5.i.c(e5.i.c((this.accesstoken.hashCode() * 31), 31, this.createrequest), 31, this.front), 31, this.back), 31, this.selfie), 31, this.ocr), 31, this.complete));
    }

    public String toString()
    {
        String v2_1 = this.front;
        String v3 = this.back;
        String v4 = this.selfie;
        String v5 = this.ocr;
        String v6 = this.complete;
        String v7 = this.nfcrar;
        String v0_1 = androidx.camera.camera2.internal.G.i("ConfigEndPoint(accesstoken=", this.accesstoken, ", createrequest=", this.createrequest, ", front=");
        q9.H.p(v0_1, v2_1, ", back=", v3, ", selfie=");
        q9.H.p(v0_1, v4, ", ocr=", v5, ", complete=");
        return androidx.compose.foundation.layout.G0.n(v0_1, v6, ", nfcrar=", v7, ")");
    }
}
