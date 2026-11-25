package vn.ai.trueid.sdk.models;
public final class FaceLivenessResult {
    private final int code;
    private final String errorMessage;
    private final String livenessResult;
    private final java.util.List rawImage;
    private final String videoBase64;

    public FaceLivenessResult()
    {
        this(0, 0, 0, 0, 0, 31, 0);
        return;
    }

    public FaceLivenessResult(int p1, String p2, String p3, java.util.List p4, String p5)
    {
        this.code = p1;
        this.errorMessage = p2;
        this.livenessResult = p3;
        this.rawImage = p4;
        this.videoBase64 = p5;
        return;
    }

    public synthetic FaceLivenessResult(int p2, String p3, String p4, java.util.List p5, String p6, int p7, kotlin.jvm.internal.DefaultConstructorMarker p8)
    {
        if ((p7 & 1) != 0) {
            p2 = 0;
        }
        if ((p7 & 2) != 0) {
            p3 = "";
        }
        if ((p7 & 4) != 0) {
            p4 = "";
        }
        if ((p7 & 8) != 0) {
            p5 = kotlin.collections.E.emptyList();
        }
        String v8_2;
        int v6_1;
        String v7_2;
        vn.ai.trueid.sdk.models.FaceLivenessResult v3_1;
        int v4_1;
        vn.ai.trueid.sdk.models.FaceLivenessResult v5_1;
        if ((p7 & 16) == 0) {
            v8_2 = p6;
            v7_2 = p5;
            v5_1 = p3;
            v6_1 = p4;
            v3_1 = this;
            v4_1 = p2;
        } else {
            v8_2 = "";
            v6_1 = p4;
            v7_2 = p5;
            v4_1 = p2;
            v5_1 = p3;
            v3_1 = this;
        }
        v3_1(v4_1, v5_1, v6_1, v7_2, v8_2);
        return;
    }

    public static synthetic vn.ai.trueid.sdk.models.FaceLivenessResult copy$default(vn.ai.trueid.sdk.models.FaceLivenessResult p0, int p1, String p2, String p3, java.util.List p4, String p5, int p6, Object p7)
    {
        if ((p6 & 1) != 0) {
            p1 = p0.code;
        }
        if ((p6 & 2) != 0) {
            p2 = p0.errorMessage;
        }
        if ((p6 & 4) != 0) {
            p3 = p0.livenessResult;
        }
        if ((p6 & 8) != 0) {
            p4 = p0.rawImage;
        }
        if ((p6 & 16) != 0) {
            p5 = p0.videoBase64;
        }
        return p0.copy(p1, p2, p3, p4, p5);
    }

    public final int component1()
    {
        return this.code;
    }

    public final String component2()
    {
        return this.errorMessage;
    }

    public final String component3()
    {
        return this.livenessResult;
    }

    public final java.util.List component4()
    {
        return this.rawImage;
    }

    public final String component5()
    {
        return this.videoBase64;
    }

    public final vn.ai.trueid.sdk.models.FaceLivenessResult copy(int p7, String p8, String p9, java.util.List p10, String p11)
    {
        return new vn.ai.trueid.sdk.models.FaceLivenessResult(p7, p8, p9, p10, p11);
    }

    public boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5 instanceof vn.ai.trueid.sdk.models.FaceLivenessResult)) {
                if (this.code == ((vn.ai.trueid.sdk.models.FaceLivenessResult) p5).code) {
                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.errorMessage, ((vn.ai.trueid.sdk.models.FaceLivenessResult) p5).errorMessage)) {
                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.livenessResult, ((vn.ai.trueid.sdk.models.FaceLivenessResult) p5).livenessResult)) {
                            if (kotlin.jvm.internal.Intrinsics.areEqual(this.rawImage, ((vn.ai.trueid.sdk.models.FaceLivenessResult) p5).rawImage)) {
                                if (kotlin.jvm.internal.Intrinsics.areEqual(this.videoBase64, ((vn.ai.trueid.sdk.models.FaceLivenessResult) p5).videoBase64)) {
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

    public final int getCode()
    {
        return this.code;
    }

    public final String getErrorMessage()
    {
        return this.errorMessage;
    }

    public final String getLivenessResult()
    {
        return this.livenessResult;
    }

    public final java.util.List getRawImage()
    {
        return this.rawImage;
    }

    public final String getVideoBase64()
    {
        return this.videoBase64;
    }

    public int hashCode()
    {
        return (this.videoBase64.hashCode() + Kb.e.h(e5.i.c(e5.i.c((Integer.hashCode(this.code) * 31), 31, this.errorMessage), 31, this.livenessResult), 31, this.rawImage));
    }

    public final java.util.Map toMap()
    {
        return kotlin.collections.a0.mapOf(new kotlin.Pair[] {kotlin.TuplesKt.to("code", Integer.valueOf(this.code)), kotlin.TuplesKt.to("errorMessage", this.errorMessage), kotlin.TuplesKt.to("livenessResult", this.livenessResult), kotlin.TuplesKt.to("rawImage", this.rawImage), kotlin.TuplesKt.to("videoBase64", this.videoBase64)}));
    }

    public String toString()
    {
        String v2 = this.livenessResult;
        java.util.List v3 = this.rawImage;
        String v4 = this.videoBase64;
        String v0_2 = com.alibaba.ariver.commonability.device.jsapi.clipboard.a.m(this.code, "FaceLivenessResult(code=", ", errorMessage=", this.errorMessage, ", livenessResult=");
        A1.a.w(v0_2, v2, ", rawImage=", v3, ", videoBase64=");
        return A1.a.l(v0_2, v4, ")");
    }
}
