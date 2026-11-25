package vn.com.vng.zalopay.search.presentation.analytics;
public final class q extends C3.c {
    private final Object c;
    private final Object d;

    public q(Object p3, Object p4)
    {
        super(999, 9);
        super.c = p3;
        super.d = p4;
        return;
    }

    public final boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5 instanceof vn.com.vng.zalopay.search.presentation.analytics.q)) {
                if (kotlin.jvm.internal.Intrinsics.areEqual(this.c, ((vn.com.vng.zalopay.search.presentation.analytics.q) p5).c)) {
                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.d, ((vn.com.vng.zalopay.search.presentation.analytics.q) p5).d)) {
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
        int v0_2;
        int v0_0 = this.c;
        int v1 = 0;
        if (v0_0 != 0) {
            v0_2 = v0_0.hashCode();
        } else {
            v0_2 = 0;
        }
        int v0_3 = (v0_2 * 31);
        Object v2 = this.d;
        if (v2 != null) {
            v1 = v2.hashCode();
        }
        return (v0_3 + v1);
    }

    public final java.util.Map n()
    {
        return kotlin.collections.Z.mapOf(kotlin.TuplesKt.to("stage", "search"));
    }

    public final String toString()
    {
        String v0_0 = this.c;
        Object v1 = this.d;
        StringBuilder v2_1 = new StringBuilder("SearchScreenShown(listRecentKeyword=");
        v2_1.append(v0_0);
        v2_1.append(", listPopularKeyword=");
        v2_1.append(v1);
        v2_1.append(")");
        return v2_1.toString();
    }
}
