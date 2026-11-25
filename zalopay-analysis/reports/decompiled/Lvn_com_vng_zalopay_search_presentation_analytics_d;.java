package vn.com.vng.zalopay.search.presentation.analytics;
public final class d extends C3.c {
    private final String c;
    private final String d;
    private final String e;

    public d(String p3, String p4, String p5)
    {
        super(984, 9);
        super.c = p3;
        super.d = p4;
        super.e = p5;
        return;
    }

    public final boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5 instanceof vn.com.vng.zalopay.search.presentation.analytics.d)) {
                if (kotlin.jvm.internal.Intrinsics.areEqual(this.c, ((vn.com.vng.zalopay.search.presentation.analytics.d) p5).c)) {
                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.d, ((vn.com.vng.zalopay.search.presentation.analytics.d) p5).d)) {
                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.e, ((vn.com.vng.zalopay.search.presentation.analytics.d) p5).e)) {
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
        int v1_0;
        int v0_4 = e5.i.c((this.c.hashCode() * 31), 31, this.d);
        int v1_2 = this.e;
        if (v1_2 != 0) {
            v1_0 = v1_2.hashCode();
        } else {
            v1_0 = 0;
        }
        return (v0_4 + v1_0);
    }

    public final java.util.Map n()
    {
        return kotlin.collections.Z.mapOf(kotlin.TuplesKt.to(wmoi.igob.ggcn(15784), wmoi.igob.ggcn(15785)));
    }

    public final String toString()
    {
        return A1.a.l(androidx.camera.camera2.internal.G.i(wmoi.igob.ggcn(15788), this.c, wmoi.igob.ggcn(15786), this.d, wmoi.igob.ggcn(15787)), this.e, wmoi.igob.ggcn(15789));
    }
}
