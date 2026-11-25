package vn.com.vng.zalopay.search.presentation.analytics;
public final class p extends C3.c {
    private final String c;
    private final String d;
    private final String e;
    private final java.util.Map f;

    public p(String p3, String p4, String p5, java.util.Map p6)
    {
        super(989, 9);
        super.c = p3;
        super.d = p4;
        super.e = p5;
        super.f = p6;
        return;
    }

    public final boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5 instanceof vn.com.vng.zalopay.search.presentation.analytics.p)) {
                if (kotlin.jvm.internal.Intrinsics.areEqual(this.c, ((vn.com.vng.zalopay.search.presentation.analytics.p) p5).c)) {
                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.d, ((vn.com.vng.zalopay.search.presentation.analytics.p) p5).d)) {
                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.e, ((vn.com.vng.zalopay.search.presentation.analytics.p) p5).e)) {
                            if (kotlin.jvm.internal.Intrinsics.areEqual(this.f, ((vn.com.vng.zalopay.search.presentation.analytics.p) p5).f)) {
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
        int v1_5;
        int v0_6 = (this.c.hashCode() * 31);
        int v1_4 = this.d;
        int v2 = 0;
        if (v1_4 != 0) {
            v1_5 = v1_4.hashCode();
        } else {
            v1_5 = 0;
        }
        int v0_2 = ((v0_6 + v1_5) * 31);
        int v1_0 = this.e;
        if (v1_0 != 0) {
            v2 = v1_0.hashCode();
        }
        return (this.f.hashCode() + ((v0_2 + v2) * 31));
    }

    public final String toString()
    {
        String v2 = this.e;
        java.util.Map v3 = this.f;
        String v0_2 = androidx.camera.camera2.internal.G.i("SearchResultsScrolled(stage=", this.c, ", keywordInput=", this.d, ", searchQueries=");
        v0_2.append(v2);
        v0_2.append(", visibleZone=");
        v0_2.append(v3);
        v0_2.append(")");
        return v0_2.toString();
    }
}
