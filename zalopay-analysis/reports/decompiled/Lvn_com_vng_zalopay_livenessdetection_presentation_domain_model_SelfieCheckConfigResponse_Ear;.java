package vn.com.vng.zalopay.livenessdetection.presentation.domain.model;
public final class SelfieCheckConfigResponse$Ear {
    public static final int $stable;
    private final Integer frames;
    private final Float maxHandRatio;
    private final Float minSkinRatio;

    public SelfieCheckConfigResponse$Ear()
    {
        this(0, 0, 0, 7, 0);
        return;
    }

    public SelfieCheckConfigResponse$Ear(Float p1, Float p2, Integer p3)
    {
        this.minSkinRatio = p1;
        this.maxHandRatio = p2;
        this.frames = p3;
        return;
    }

    public synthetic SelfieCheckConfigResponse$Ear(Float p2, Float p3, Integer p4, int p5, kotlin.jvm.internal.DefaultConstructorMarker p6)
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

    public final Integer a()
    {
        return this.frames;
    }

    public final Float b()
    {
        return this.maxHandRatio;
    }

    public final Float c()
    {
        return this.minSkinRatio;
    }

    public final boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5 instanceof vn.com.vng.zalopay.livenessdetection.presentation.domain.model.SelfieCheckConfigResponse$Ear)) {
                if (kotlin.jvm.internal.Intrinsics.areEqual(this.minSkinRatio, ((vn.com.vng.zalopay.livenessdetection.presentation.domain.model.SelfieCheckConfigResponse$Ear) p5).minSkinRatio)) {
                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.maxHandRatio, ((vn.com.vng.zalopay.livenessdetection.presentation.domain.model.SelfieCheckConfigResponse$Ear) p5).maxHandRatio)) {
                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.frames, ((vn.com.vng.zalopay.livenessdetection.presentation.domain.model.SelfieCheckConfigResponse$Ear) p5).frames)) {
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
        int v0_0 = this.minSkinRatio;
        int v1 = 0;
        if (v0_0 != 0) {
            v0_4 = v0_0.hashCode();
        } else {
            v0_4 = 0;
        }
        Integer v2_0;
        int v0_5 = (v0_4 * 31);
        Integer v2_2 = this.maxHandRatio;
        if (v2_2 != null) {
            v2_0 = v2_2.hashCode();
        } else {
            v2_0 = 0;
        }
        int v0_2 = ((v0_5 + v2_0) * 31);
        Integer v2_1 = this.frames;
        if (v2_1 != null) {
            v1 = v2_1.hashCode();
        }
        return (v0_2 + v1);
    }

    public final String toString()
    {
        String v0_0 = this.minSkinRatio;
        Float v1 = this.maxHandRatio;
        Integer v2 = this.frames;
        StringBuilder v3_1 = new StringBuilder(wmoi.igob.ggcn(9769));
        v3_1.append(v0_0);
        v3_1.append(wmoi.igob.ggcn(9770));
        v3_1.append(v1);
        v3_1.append(wmoi.igob.ggcn(9771));
        return com.alibaba.ariver.commonability.device.jsapi.clipboard.a.l(v3_1, v2, wmoi.igob.ggcn(9772));
    }
}
