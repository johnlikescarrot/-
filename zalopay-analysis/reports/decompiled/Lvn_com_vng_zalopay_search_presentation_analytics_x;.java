package vn.com.vng.zalopay.search.presentation.analytics;
public final class x extends C3.c {
    private final String c;
    private final int d;
    private final String e;
    private final String f;
    private final String g;
    private final String h;
    private final String i;

    public x(String p3, String p4, String p5, String p6, int p7, String p8, String p9)
    {
        super(987, 9);
        super.c = p3;
        super.d = p7;
        super.e = p4;
        super.f = p5;
        super.g = p6;
        super.h = p8;
        super.i = p9;
        return;
    }

    public final boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5 instanceof vn.com.vng.zalopay.search.presentation.analytics.x)) {
                if (kotlin.jvm.internal.Intrinsics.areEqual(this.c, ((vn.com.vng.zalopay.search.presentation.analytics.x) p5).c)) {
                    if (this.d == ((vn.com.vng.zalopay.search.presentation.analytics.x) p5).d) {
                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.e, ((vn.com.vng.zalopay.search.presentation.analytics.x) p5).e)) {
                            if (kotlin.jvm.internal.Intrinsics.areEqual(this.f, ((vn.com.vng.zalopay.search.presentation.analytics.x) p5).f)) {
                                if (kotlin.jvm.internal.Intrinsics.areEqual(this.g, ((vn.com.vng.zalopay.search.presentation.analytics.x) p5).g)) {
                                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.h, ((vn.com.vng.zalopay.search.presentation.analytics.x) p5).h)) {
                                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.i, ((vn.com.vng.zalopay.search.presentation.analytics.x) p5).i)) {
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
        int v2_2;
        int v0_2 = e5.i.c(e5.i.c(Kb.e.v(this.d, (this.c.hashCode() * 31), 31), 31, this.e), 31, this.f);
        int v2_1 = this.g;
        int v3 = 0;
        if (v2_1 != 0) {
            v2_2 = v2_1.hashCode();
        } else {
            v2_2 = 0;
        }
        int v2_4;
        int v0_5 = ((v0_2 + v2_2) * 31);
        int v2_3 = this.h;
        if (v2_3 != 0) {
            v2_4 = v2_3.hashCode();
        } else {
            v2_4 = 0;
        }
        int v0_7 = ((v0_5 + v2_4) * 31);
        String v1_0 = this.i;
        if (v1_0 != null) {
            v3 = v1_0.hashCode();
        }
        return (v0_7 + v3);
    }

    public final java.util.Map n()
    {
        return kotlin.collections.Z.mapOf(kotlin.TuplesKt.to("type", "friend_list"));
    }

    public final String toString()
    {
        String v2_1 = this.e;
        String v3 = this.f;
        String v4 = this.g;
        String v5 = this.h;
        String v6 = this.i;
        String v0_1 = androidx.compose.foundation.layout.G0.p(this.d, "TransferAccountClicked(stage=", this.c, ", order=", ", friendName=");
        q9.H.p(v0_1, v2_1, ", resultFrom=", v3, ", keywordInput=");
        q9.H.p(v0_1, v4, ", searchQueries=", v5, ", tab=");
        return A1.a.l(v0_1, v6, ")");
    }
}
