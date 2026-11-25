package vn.ai.trueid.sdk.models;
public final class RawImage {
    public static final vn.ai.trueid.sdk.models.RawImage$Companion Companion;
    private final String back;
    private final String front;
    private String selfie;

    static RawImage()
    {
        vn.ai.trueid.sdk.models.RawImage.Companion = new vn.ai.trueid.sdk.models.RawImage$Companion(0);
        return;
    }

    public RawImage()
    {
        this(0, 0, 0, 7, 0);
        return;
    }

    public RawImage(String p1, String p2, String p3)
    {
        this.front = p1;
        this.back = p2;
        this.selfie = p3;
        return;
    }

    public synthetic RawImage(String p2, String p3, String p4, int p5, kotlin.jvm.internal.DefaultConstructorMarker p6)
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

    public static synthetic vn.ai.trueid.sdk.models.RawImage copy$default(vn.ai.trueid.sdk.models.RawImage p0, String p1, String p2, String p3, int p4, Object p5)
    {
        if ((p4 & 1) != 0) {
            p1 = p0.front;
        }
        if ((p4 & 2) != 0) {
            p2 = p0.back;
        }
        if ((p4 & 4) != 0) {
            p3 = p0.selfie;
        }
        return p0.copy(p1, p2, p3);
    }

    public final String component1()
    {
        return this.front;
    }

    public final String component2()
    {
        return this.back;
    }

    public final String component3()
    {
        return this.selfie;
    }

    public final vn.ai.trueid.sdk.models.RawImage copy(String p2, String p3, String p4)
    {
        return new vn.ai.trueid.sdk.models.RawImage(p2, p3, p4);
    }

    public boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5 instanceof vn.ai.trueid.sdk.models.RawImage)) {
                if (kotlin.jvm.internal.Intrinsics.areEqual(this.front, ((vn.ai.trueid.sdk.models.RawImage) p5).front)) {
                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.back, ((vn.ai.trueid.sdk.models.RawImage) p5).back)) {
                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.selfie, ((vn.ai.trueid.sdk.models.RawImage) p5).selfie)) {
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

    public final String getBack()
    {
        return this.back;
    }

    public final String getFront()
    {
        return this.front;
    }

    public final String getSelfie()
    {
        return this.selfie;
    }

    public int hashCode()
    {
        int v0_4;
        int v0_0 = this.front;
        int v1 = 0;
        if (v0_0 != 0) {
            v0_4 = v0_0.hashCode();
        } else {
            v0_4 = 0;
        }
        String v2_0;
        int v0_5 = (v0_4 * 31);
        String v2_2 = this.back;
        if (v2_2 != null) {
            v2_0 = v2_2.hashCode();
        } else {
            v2_0 = 0;
        }
        int v0_2 = ((v0_5 + v2_0) * 31);
        String v2_1 = this.selfie;
        if (v2_1 != null) {
            v1 = v2_1.hashCode();
        }
        return (v0_2 + v1);
    }

    public final void setSelfie(String p1)
    {
        this.selfie = p1;
        return;
    }

    public final java.util.Map toMap()
    {
        java.util.Map v0_0 = this.selfie;
        kotlin.Pair v1_0 = "";
        if (v0_0 == null) {
            v0_0 = "";
        }
        java.util.Map v0_3 = kotlin.TuplesKt.to("selfie", v0_0);
        kotlin.Pair v2_2 = this.front;
        if (v2_2 == null) {
            v2_2 = "";
        }
        kotlin.Pair v2_0 = kotlin.TuplesKt.to("front", v2_2);
        String v3_1 = this.back;
        if (v3_1 != null) {
            v1_0 = v3_1;
        }
        return kotlin.collections.a0.mapOf(new kotlin.Pair[] {v0_3, v2_0, kotlin.TuplesKt.to("back", v1_0)}));
    }

    public String toString()
    {
        return A1.a.l(androidx.camera.camera2.internal.G.i("RawImage(front=", this.front, ", back=", this.back, ", selfie="), this.selfie, ")");
    }
}
