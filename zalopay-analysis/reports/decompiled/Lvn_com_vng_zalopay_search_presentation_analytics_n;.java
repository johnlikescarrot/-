package vn.com.vng.zalopay.search.presentation.analytics;
public final class n extends C3.c {
    private final String c;
    private final String d;
    private final java.util.List e;
    private final String f;
    private final java.util.Map g;

    public n(String p3, String p4, java.util.ArrayList p5, String p6, java.util.Map p7)
    {
        super(991, 9);
        super.c = p3;
        super.d = p4;
        super.e = p5;
        super.f = p6;
        super.g = p7;
        return;
    }

    public final boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5 instanceof vn.com.vng.zalopay.search.presentation.analytics.n)) {
                if (kotlin.jvm.internal.Intrinsics.areEqual(this.c, ((vn.com.vng.zalopay.search.presentation.analytics.n) p5).c)) {
                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.d, ((vn.com.vng.zalopay.search.presentation.analytics.n) p5).d)) {
                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.e, ((vn.com.vng.zalopay.search.presentation.analytics.n) p5).e)) {
                            if (kotlin.jvm.internal.Intrinsics.areEqual(this.f, ((vn.com.vng.zalopay.search.presentation.analytics.n) p5).f)) {
                                if (kotlin.jvm.internal.Intrinsics.areEqual(this.g, ((vn.com.vng.zalopay.search.presentation.analytics.n) p5).g)) {
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
        String v2_0;
        int v0_7 = e5.i.c((this.c.hashCode() * 31), 31, this.d);
        String v2_3 = this.e;
        int v3 = 0;
        if (v2_3 != null) {
            v2_0 = v2_3.hashCode();
        } else {
            v2_0 = 0;
        }
        int v0_3 = e5.i.c(((v0_7 + v2_0) * 31), 31, this.f);
        java.util.Map v1_0 = this.g;
        if (v1_0 != null) {
            v3 = v1_0.hashCode();
        }
        return (v0_3 + v3);
    }

    public final java.util.Map n()
    {
        return kotlin.collections.Z.mapOf(kotlin.TuplesKt.to(wmoi.igob.ggcn(15600), wmoi.igob.ggcn(15601)));
    }

    public final String toString()
    {
        java.util.List v2 = this.e;
        String v3 = this.f;
        java.util.Map v4 = this.g;
        String v0_1 = androidx.camera.camera2.internal.G.i(wmoi.igob.ggcn(15604), this.c, wmoi.igob.ggcn(15602), this.d, wmoi.igob.ggcn(15603));
        v0_1.append(v2);
        v0_1.append(wmoi.igob.ggcn(15605));
        v0_1.append(v3);
        v0_1.append(wmoi.igob.ggcn(15606));
        v0_1.append(v4);
        v0_1.append(wmoi.igob.ggcn(15607));
        return v0_1.toString();
    }
}
