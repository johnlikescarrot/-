package vn.com.vng.zalopay.search.presentation.analytics;
public final class e extends C3.c {
    private final String c;
    private final String d;
    private final String e;
    private final String f;

    public e(String p3, String p4, String p5, String p6)
    {
        super(990, 9);
        super.c = p3;
        super.d = p4;
        super.e = p5;
        super.f = p6;
        return;
    }

    public final boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5 instanceof vn.com.vng.zalopay.search.presentation.analytics.e)) {
                if (kotlin.jvm.internal.Intrinsics.areEqual(this.c, ((vn.com.vng.zalopay.search.presentation.analytics.e) p5).c)) {
                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.d, ((vn.com.vng.zalopay.search.presentation.analytics.e) p5).d)) {
                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.e, ((vn.com.vng.zalopay.search.presentation.analytics.e) p5).e)) {
                            if (kotlin.jvm.internal.Intrinsics.areEqual(this.f, ((vn.com.vng.zalopay.search.presentation.analytics.e) p5).f)) {
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
        int v2_0;
        int v0_6 = e5.i.c((this.c.hashCode() * 31), 31, this.d);
        int v2_2 = this.e;
        int v3 = 0;
        if (v2_2 != 0) {
            v2_0 = v2_2.hashCode();
        } else {
            v2_0 = 0;
        }
        int v0_2 = ((v0_6 + v2_0) * 31);
        String v1_0 = this.f;
        if (v1_0 != null) {
            v3 = v1_0.hashCode();
        }
        return (v0_2 + v3);
    }

    public final String toString()
    {
        return androidx.compose.foundation.layout.G0.n(androidx.camera.camera2.internal.G.i("ClearKeywordClicked(stage=", this.c, ", resultFrom=", this.d, ", keywordInput="), this.e, ", searchQueries=", this.f, ")");
    }
}
