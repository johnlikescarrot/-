package vn.com.vng.zalopay.search.presentation.analytics;
public final class o extends C3.c {
    private final String c;
    private final String d;
    private final String e;
    private final java.util.Map f;
    private final String g;
    private final String h;

    public o(String p3, String p4, String p5, String p6, String p7, java.util.Map p8)
    {
        super(986, 9);
        super.c = p3;
        super.d = p4;
        super.e = p5;
        super.f = p8;
        super.g = p6;
        super.h = p7;
        return;
    }

    public final boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5 instanceof vn.com.vng.zalopay.search.presentation.analytics.o)) {
                if (kotlin.jvm.internal.Intrinsics.areEqual(this.c, ((vn.com.vng.zalopay.search.presentation.analytics.o) p5).c)) {
                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.d, ((vn.com.vng.zalopay.search.presentation.analytics.o) p5).d)) {
                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.e, ((vn.com.vng.zalopay.search.presentation.analytics.o) p5).e)) {
                            if (kotlin.jvm.internal.Intrinsics.areEqual(this.f, ((vn.com.vng.zalopay.search.presentation.analytics.o) p5).f)) {
                                if (kotlin.jvm.internal.Intrinsics.areEqual(this.g, ((vn.com.vng.zalopay.search.presentation.analytics.o) p5).g)) {
                                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.h, ((vn.com.vng.zalopay.search.presentation.analytics.o) p5).h)) {
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
            return 1;
        }
    }

    public final int hashCode()
    {
        int v2_2;
        int v0_2 = q9.H.c(this.f, e5.i.c(e5.i.c((this.c.hashCode() * 31), 31, this.d), 31, this.e), 31);
        int v2_1 = this.g;
        int v3 = 0;
        if (v2_1 != 0) {
            v2_2 = v2_1.hashCode();
        } else {
            v2_2 = 0;
        }
        int v0_5 = ((v0_2 + v2_2) * 31);
        String v1_0 = this.h;
        if (v1_0 != null) {
            v3 = v1_0.hashCode();
        }
        return (v0_5 + v3);
    }

    public final java.util.Map n()
    {
        return kotlin.collections.Z.mapOf(kotlin.TuplesKt.to(wmoi.igob.ggcn(15578), wmoi.igob.ggcn(15579)));
    }

    public final String toString()
    {
        String v2_0 = this.e;
        java.util.Map v3 = this.f;
        String v4 = this.g;
        String v5 = this.h;
        String v0_1 = androidx.camera.camera2.internal.G.i(wmoi.igob.ggcn(15582), this.c, wmoi.igob.ggcn(15580), this.d, wmoi.igob.ggcn(15581));
        com.alibaba.ariver.commonability.device.jsapi.clipboard.a.y(v0_1, v2_0, wmoi.igob.ggcn(15583), v3, wmoi.igob.ggcn(15584));
        return androidx.compose.foundation.layout.G0.n(v0_1, v4, wmoi.igob.ggcn(15585), v5, wmoi.igob.ggcn(15586));
    }
}
