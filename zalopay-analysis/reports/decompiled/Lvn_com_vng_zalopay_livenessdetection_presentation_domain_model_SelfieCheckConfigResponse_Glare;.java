package vn.com.vng.zalopay.livenessdetection.presentation.domain.model;
public final class SelfieCheckConfigResponse$Glare {
    public static final int $stable;
    private final Float cellRatio;
    private final Float faceRatio;
    private final Float hairRatio;

    public SelfieCheckConfigResponse$Glare()
    {
        this(0, 0, 0, 7, 0);
        return;
    }

    public SelfieCheckConfigResponse$Glare(Float p1, Float p2, Float p3)
    {
        this.faceRatio = p1;
        this.cellRatio = p2;
        this.hairRatio = p3;
        return;
    }

    public synthetic SelfieCheckConfigResponse$Glare(Float p2, Float p3, Float p4, int p5, kotlin.jvm.internal.DefaultConstructorMarker p6)
    {
        if ((p5 & 1) != 0) {
            p2 = 0;
        }
        if ((p5 & 2) != 0) {
            p3 = 0;
        }
        if ((p5 & 4) != 0) {
            p4 = 0;
        }
        this(p2, p3, p4);
        return;
    }

    public final Float a()
    {
        return this.cellRatio;
    }

    public final Float b()
    {
        return this.faceRatio;
    }

    public final Float c()
    {
        return this.hairRatio;
    }

    public final boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5 instanceof vn.com.vng.zalopay.livenessdetection.presentation.domain.model.SelfieCheckConfigResponse$Glare)) {
                if (kotlin.jvm.internal.Intrinsics.areEqual(this.faceRatio, ((vn.com.vng.zalopay.livenessdetection.presentation.domain.model.SelfieCheckConfigResponse$Glare) p5).faceRatio)) {
                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.cellRatio, ((vn.com.vng.zalopay.livenessdetection.presentation.domain.model.SelfieCheckConfigResponse$Glare) p5).cellRatio)) {
                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.hairRatio, ((vn.com.vng.zalopay.livenessdetection.presentation.domain.model.SelfieCheckConfigResponse$Glare) p5).hairRatio)) {
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
            return 1;
        }
    }

    public final int hashCode()
    {
        int v0_4;
        int v0_0 = this.faceRatio;
        int v1 = 0;
        if (v0_0 != 0) {
            v0_4 = v0_0.hashCode();
        } else {
            v0_4 = 0;
        }
        Float v2_0;
        int v0_5 = (v0_4 * 31);
        Float v2_2 = this.cellRatio;
        if (v2_2 != null) {
            v2_0 = v2_2.hashCode();
        } else {
            v2_0 = 0;
        }
        int v0_2 = ((v0_5 + v2_0) * 31);
        Float v2_1 = this.hairRatio;
        if (v2_1 != null) {
            v1 = v2_1.hashCode();
        }
        return (v0_2 + v1);
    }

    public final String toString()
    {
        String v0_0 = this.faceRatio;
        Float v1 = this.cellRatio;
        Float v2 = this.hairRatio;
        StringBuilder v3_1 = new StringBuilder("Glare(faceRatio=");
        v3_1.append(v0_0);
        v3_1.append(", cellRatio=");
        v3_1.append(v1);
        v3_1.append(", hairRatio=");
        v3_1.append(v2);
        v3_1.append(")");
        return v3_1.toString();
    }
}
