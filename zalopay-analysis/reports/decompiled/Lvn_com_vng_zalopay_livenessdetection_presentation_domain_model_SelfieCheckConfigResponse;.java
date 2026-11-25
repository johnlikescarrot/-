package vn.com.vng.zalopay.livenessdetection.presentation.domain.model;
public final class SelfieCheckConfigResponse {
    public static final int $stable;
    private final vn.com.vng.zalopay.livenessdetection.presentation.domain.model.SelfieCheckConfigResponse$Ear ear;
    private final Float eyeOpenThreshold;
    private final vn.com.vng.zalopay.livenessdetection.presentation.domain.model.SelfieCheckConfigResponse$Glare glare;
    private final Float otherFaceMinRatio;
    private final Boolean portraitCrop;

    public SelfieCheckConfigResponse()
    {
        this(0, 0, 0, 0, 0, 31, 0);
        return;
    }

    public SelfieCheckConfigResponse(vn.com.vng.zalopay.livenessdetection.presentation.domain.model.SelfieCheckConfigResponse$Glare p1, vn.com.vng.zalopay.livenessdetection.presentation.domain.model.SelfieCheckConfigResponse$Ear p2, Float p3, Boolean p4, Float p5)
    {
        this.glare = p1;
        this.ear = p2;
        this.eyeOpenThreshold = p3;
        this.portraitCrop = p4;
        this.otherFaceMinRatio = p5;
        return;
    }

    public synthetic SelfieCheckConfigResponse(vn.com.vng.zalopay.livenessdetection.presentation.domain.model.SelfieCheckConfigResponse$Glare p2, vn.com.vng.zalopay.livenessdetection.presentation.domain.model.SelfieCheckConfigResponse$Ear p3, Float p4, Boolean p5, Float p6, int p7, kotlin.jvm.internal.DefaultConstructorMarker p8)
    {
        if ((p7 & 1) != 0) {
            p2 = 0;
        }
        if ((p7 & 2) != 0) {
            p3 = 0;
        }
        if ((p7 & 4) != 0) {
            p4 = 0;
        }
        if ((p7 & 8) != 0) {
            p5 = 0;
        }
        Float v7_2;
        if ((p7 & 16) == 0) {
            v7_2 = p6;
        } else {
            v7_2 = 0;
        }
        this(p2, p3, p4, p5, v7_2);
        return;
    }

    public final vn.com.vng.zalopay.livenessdetection.presentation.domain.model.SelfieCheckConfigResponse$Ear a()
    {
        return this.ear;
    }

    public final Float b()
    {
        return this.eyeOpenThreshold;
    }

    public final vn.com.vng.zalopay.livenessdetection.presentation.domain.model.SelfieCheckConfigResponse$Glare c()
    {
        return this.glare;
    }

    public final Float d()
    {
        return this.otherFaceMinRatio;
    }

    public final Boolean e()
    {
        return this.portraitCrop;
    }

    public final boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5 instanceof vn.com.vng.zalopay.livenessdetection.presentation.domain.model.SelfieCheckConfigResponse)) {
                if (kotlin.jvm.internal.Intrinsics.areEqual(this.glare, ((vn.com.vng.zalopay.livenessdetection.presentation.domain.model.SelfieCheckConfigResponse) p5).glare)) {
                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.ear, ((vn.com.vng.zalopay.livenessdetection.presentation.domain.model.SelfieCheckConfigResponse) p5).ear)) {
                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.eyeOpenThreshold, ((vn.com.vng.zalopay.livenessdetection.presentation.domain.model.SelfieCheckConfigResponse) p5).eyeOpenThreshold)) {
                            if (kotlin.jvm.internal.Intrinsics.areEqual(this.portraitCrop, ((vn.com.vng.zalopay.livenessdetection.presentation.domain.model.SelfieCheckConfigResponse) p5).portraitCrop)) {
                                if (kotlin.jvm.internal.Intrinsics.areEqual(this.otherFaceMinRatio, ((vn.com.vng.zalopay.livenessdetection.presentation.domain.model.SelfieCheckConfigResponse) p5).otherFaceMinRatio)) {
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

    public final int hashCode()
    {
        int v0_8;
        int v0_0 = this.glare;
        int v1 = 0;
        if (v0_0 != 0) {
            v0_8 = v0_0.hashCode();
        } else {
            v0_8 = 0;
        }
        Float v2_0;
        int v0_9 = (v0_8 * 31);
        Float v2_6 = this.ear;
        if (v2_6 != null) {
            v2_0 = v2_6.hashCode();
        } else {
            v2_0 = 0;
        }
        Float v2_2;
        int v0_2 = ((v0_9 + v2_0) * 31);
        Float v2_1 = this.eyeOpenThreshold;
        if (v2_1 != null) {
            v2_2 = v2_1.hashCode();
        } else {
            v2_2 = 0;
        }
        Float v2_4;
        int v0_4 = ((v0_2 + v2_2) * 31);
        Float v2_3 = this.portraitCrop;
        if (v2_3 != null) {
            v2_4 = v2_3.hashCode();
        } else {
            v2_4 = 0;
        }
        int v0_6 = ((v0_4 + v2_4) * 31);
        Float v2_5 = this.otherFaceMinRatio;
        if (v2_5 != null) {
            v1 = v2_5.hashCode();
        }
        return (v0_6 + v1);
    }

    public final String toString()
    {
        String v0_0 = this.glare;
        vn.com.vng.zalopay.livenessdetection.presentation.domain.model.SelfieCheckConfigResponse$Ear v1 = this.ear;
        Float v2 = this.eyeOpenThreshold;
        Boolean v3 = this.portraitCrop;
        Float v4 = this.otherFaceMinRatio;
        StringBuilder v5_1 = new StringBuilder("SelfieCheckConfigResponse(glare=");
        v5_1.append(v0_0);
        v5_1.append(", ear=");
        v5_1.append(v1);
        v5_1.append(", eyeOpenThreshold=");
        v5_1.append(v2);
        v5_1.append(", portraitCrop=");
        v5_1.append(v3);
        v5_1.append(", otherFaceMinRatio=");
        v5_1.append(v4);
        v5_1.append(")");
        return v5_1.toString();
    }
}
