package vn.com.vng.zalopay.search.presentation.analytics;
public final class h extends C3.c {
    private final String c;
    private final String d;
    private final String e;
    private final String f;

    public h(String p3, String p4, String p5, String p6)
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
            if ((p5 instanceof vn.com.vng.zalopay.search.presentation.analytics.h)) {
                if (kotlin.jvm.internal.Intrinsics.areEqual(this.c, ((vn.com.vng.zalopay.search.presentation.analytics.h) p5).c)) {
                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.d, ((vn.com.vng.zalopay.search.presentation.analytics.h) p5).d)) {
                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.e, ((vn.com.vng.zalopay.search.presentation.analytics.h) p5).e)) {
                            if (kotlin.jvm.internal.Intrinsics.areEqual(this.f, ((vn.com.vng.zalopay.search.presentation.analytics.h) p5).f)) {
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
        return (this.f.hashCode() + e5.i.c(e5.i.c((this.c.hashCode() * 31), 31, this.d), 31, this.e));
    }

    public final java.util.Map n()
    {
        return kotlin.collections.a0.mapOf(new kotlin.Pair[] {kotlin.TuplesKt.to("stage", "result"), kotlin.TuplesKt.to("type", "highlight_feature"), kotlin.TuplesKt.to("tab", "search_all")}));
    }

    public final String toString()
    {
        return androidx.compose.foundation.layout.G0.n(androidx.camera.camera2.internal.G.i("HeroCardClicked(searchQueries=", this.c, ", appProfileId=", this.d, ", appProfileName="), this.e, ", resultFrom=", this.f, ")");
    }
}
