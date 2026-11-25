package vn.com.vng.zalopay.search.presentation.analytics;
public final class r extends C3.c {
    private final String c;

    public r(String p3)
    {
        super(988, 9);
        super.c = p3;
        return;
    }

    public final boolean equals(Object p4)
    {
        if (this != p4) {
            if ((p4 instanceof vn.com.vng.zalopay.search.presentation.analytics.r)) {
                if (kotlin.jvm.internal.Intrinsics.areEqual(this.c, ((vn.com.vng.zalopay.search.presentation.analytics.r) p4).c)) {
                    return 1;
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
        return this.c.hashCode();
    }

    public final java.util.Map n()
    {
        return kotlin.collections.Z.mapOf(kotlin.TuplesKt.to("stage", "typing"));
    }

    public final String toString()
    {
        return A1.a.i("SeeAllResultsClicked(keywordInput=", this.c, ")");
    }
}
