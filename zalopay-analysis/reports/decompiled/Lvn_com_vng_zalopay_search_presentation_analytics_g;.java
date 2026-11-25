package vn.com.vng.zalopay.search.presentation.analytics;
public final class g extends C3.c {
    private final java.util.List c;
    private final int d;

    public g(int p3, java.util.List p4)
    {
        super(992, 9);
        super.c = p4;
        super.d = p3;
        return;
    }

    public final boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5 instanceof vn.com.vng.zalopay.search.presentation.analytics.g)) {
                if (kotlin.jvm.internal.Intrinsics.areEqual(this.c, ((vn.com.vng.zalopay.search.presentation.analytics.g) p5).c)) {
                    if (this.d == ((vn.com.vng.zalopay.search.presentation.analytics.g) p5).d) {
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
            return 1;
        }
    }

    public final int hashCode()
    {
        return (Integer.hashCode(this.d) + (this.c.hashCode() * 31));
    }

    public final java.util.Map n()
    {
        return kotlin.collections.Z.mapOf(kotlin.TuplesKt.to(wmoi.igob.ggcn(15642), wmoi.igob.ggcn(15643)));
    }

    public final String toString()
    {
        String v0_0 = this.c;
        int v1 = this.d;
        StringBuilder v2_1 = new StringBuilder(wmoi.igob.ggcn(15644));
        v2_1.append(v0_0);
        v2_1.append(wmoi.igob.ggcn(15645));
        v2_1.append(v1);
        v2_1.append(wmoi.igob.ggcn(15646));
        return v2_1.toString();
    }
}
