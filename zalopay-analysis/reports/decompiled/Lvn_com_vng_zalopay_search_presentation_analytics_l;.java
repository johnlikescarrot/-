package vn.com.vng.zalopay.search.presentation.analytics;
public final class l extends C3.c {
    private final String c;
    private final int d;

    public l(String p3, int p4)
    {
        super(995, 9);
        super.c = p3;
        super.d = p4;
        return;
    }

    public final boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5 instanceof vn.com.vng.zalopay.search.presentation.analytics.l)) {
                if (kotlin.jvm.internal.Intrinsics.areEqual(this.c, ((vn.com.vng.zalopay.search.presentation.analytics.l) p5).c)) {
                    if (this.d == ((vn.com.vng.zalopay.search.presentation.analytics.l) p5).d) {
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
        return kotlin.collections.a0.mapOf(new kotlin.Pair[] {kotlin.TuplesKt.to("stage", "search"), kotlin.TuplesKt.to("type", "recent_keyword")}));
    }

    public final String toString()
    {
        return androidx.activity.result.e.h(this.d, "RecentKeywordClicked(searchQueries=", this.c, ", order=", ")");
    }
}
