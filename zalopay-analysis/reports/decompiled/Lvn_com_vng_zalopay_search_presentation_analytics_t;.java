package vn.com.vng.zalopay.search.presentation.analytics;
public final class t extends C3.c {
    private final String c;
    private final int d;
    private final Object e;
    private final String f;
    private final String g;
    private final String h;
    private final String i;
    private final String j;
    private final String k;

    public t(String p3, int p4, Object p5, String p6, String p7, String p8, String p9, String p10, String p11)
    {
        super(987, 9);
        super.c = p3;
        super.d = p4;
        super.e = p5;
        super.f = p6;
        super.g = p7;
        super.h = p8;
        super.i = p9;
        super.j = p10;
        super.k = p11;
        return;
    }

    public final boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5 instanceof vn.com.vng.zalopay.search.presentation.analytics.t)) {
                if (kotlin.jvm.internal.Intrinsics.areEqual(this.c, ((vn.com.vng.zalopay.search.presentation.analytics.t) p5).c)) {
                    if (this.d == ((vn.com.vng.zalopay.search.presentation.analytics.t) p5).d) {
                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.e, ((vn.com.vng.zalopay.search.presentation.analytics.t) p5).e)) {
                            if (kotlin.jvm.internal.Intrinsics.areEqual(this.f, ((vn.com.vng.zalopay.search.presentation.analytics.t) p5).f)) {
                                if (kotlin.jvm.internal.Intrinsics.areEqual(this.g, ((vn.com.vng.zalopay.search.presentation.analytics.t) p5).g)) {
                                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.h, ((vn.com.vng.zalopay.search.presentation.analytics.t) p5).h)) {
                                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.i, ((vn.com.vng.zalopay.search.presentation.analytics.t) p5).i)) {
                                            if (kotlin.jvm.internal.Intrinsics.areEqual(this.j, ((vn.com.vng.zalopay.search.presentation.analytics.t) p5).j)) {
                                                if (kotlin.jvm.internal.Intrinsics.areEqual(this.k, ((vn.com.vng.zalopay.search.presentation.analytics.t) p5).k)) {
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
        int v2_4;
        int v0_2 = e5.i.c(((this.e.hashCode() + Kb.e.v(this.d, (this.c.hashCode() * 31), 31)) * String v1_0), 31, this.f);
        int v2_3 = this.g;
        int v3 = 0;
        if (v2_3 != 0) {
            v2_4 = v2_3.hashCode();
        } else {
            v2_4 = 0;
        }
        int v2_7;
        int v0_6 = e5.i.c(((v0_2 + v2_4) * 31), 31, this.h);
        int v2_6 = this.i;
        if (v2_6 != 0) {
            v2_7 = v2_6.hashCode();
        } else {
            v2_7 = 0;
        }
        int v2_9;
        int v0_8 = ((v0_6 + v2_7) * 31);
        int v2_8 = this.j;
        if (v2_8 != 0) {
            v2_9 = v2_8.hashCode();
        } else {
            v2_9 = 0;
        }
        int v0_11 = ((v0_8 + v2_9) * 31);
        String v1_1 = this.k;
        if (v1_1 != null) {
            v3 = v1_1.hashCode();
        }
        return (v0_11 + v3);
    }

    public final java.util.Map n()
    {
        return kotlin.collections.Z.mapOf(kotlin.TuplesKt.to("type", "service"));
    }

    public final String toString()
    {
        String v2_0 = this.e;
        String v3 = this.f;
        String v4 = this.g;
        String v5 = this.h;
        String v6 = this.i;
        String v7 = this.j;
        String v8 = this.k;
        String v0_1 = androidx.compose.foundation.layout.G0.p(this.d, "ServiceClicked(stage=", this.c, ", order=", ", appProfileId=");
        v0_1.append(v2_0);
        v0_1.append(", appProfileName=");
        v0_1.append(v3);
        v0_1.append(", dataSource=");
        q9.H.p(v0_1, v4, ", resultFrom=", v5, ", keywordInput=");
        q9.H.p(v0_1, v6, ", searchQueries=", v7, ", tab=");
        return A1.a.l(v0_1, v8, ")");
    }
}
