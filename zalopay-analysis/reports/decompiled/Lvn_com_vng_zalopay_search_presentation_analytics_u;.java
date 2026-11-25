package vn.com.vng.zalopay.search.presentation.analytics;
public final class u extends C3.c {
    private final String c;
    private final String d;
    private final String e;
    private final int f;

    public u(String p3, String p4, String p5, int p6)
    {
        super(987, 9);
        super.c = p3;
        super.d = p4;
        super.e = p5;
        super.f = p6;
        return;
    }

    public final boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5 instanceof vn.com.vng.zalopay.search.presentation.analytics.u)) {
                if (kotlin.jvm.internal.Intrinsics.areEqual(this.c, ((vn.com.vng.zalopay.search.presentation.analytics.u) p5).c)) {
                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.d, ((vn.com.vng.zalopay.search.presentation.analytics.u) p5).d)) {
                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.e, ((vn.com.vng.zalopay.search.presentation.analytics.u) p5).e)) {
                            if (this.f == ((vn.com.vng.zalopay.search.presentation.analytics.u) p5).f) {
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
            return 1;
        }
    }

    public final int hashCode()
    {
        return (Integer.hashCode(this.f) + e5.i.c(e5.i.c((this.c.hashCode() * 31), 31, this.d), 31, this.e));
    }

    public final java.util.Map n()
    {
        return kotlin.collections.a0.mapOf(new kotlin.Pair[] {kotlin.TuplesKt.to("stage", "typing"), kotlin.TuplesKt.to("type", "query")}));
    }

    public final String toString()
    {
        return androidx.compose.foundation.layout.G0.h(this.f, this.e, ", order=", ")", androidx.camera.camera2.internal.G.i("SuggestedKeywordClicked(keywordInput=", this.c, ", searchQueries=", this.d, ", fromStage="));
    }
}
