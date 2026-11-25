package vn.com.vng.zalopay.search.presentation.analytics;
public final class v extends C3.c {
    private final String c;
    private final String d;
    private final String e;
    private final String f;
    private final int g;
    private final String h;
    private final String i;
    private final String j;
    private final String k;

    public v(String p3, String p4, String p5, String p6, int p7, String p8, String p9, String p10, String p11)
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
            if ((p5 instanceof vn.com.vng.zalopay.search.presentation.analytics.v)) {
                if (kotlin.jvm.internal.Intrinsics.areEqual(this.c, ((vn.com.vng.zalopay.search.presentation.analytics.v) p5).c)) {
                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.d, ((vn.com.vng.zalopay.search.presentation.analytics.v) p5).d)) {
                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.e, ((vn.com.vng.zalopay.search.presentation.analytics.v) p5).e)) {
                            if (kotlin.jvm.internal.Intrinsics.areEqual(this.f, ((vn.com.vng.zalopay.search.presentation.analytics.v) p5).f)) {
                                if (this.g == ((vn.com.vng.zalopay.search.presentation.analytics.v) p5).g) {
                                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.h, ((vn.com.vng.zalopay.search.presentation.analytics.v) p5).h)) {
                                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.i, ((vn.com.vng.zalopay.search.presentation.analytics.v) p5).i)) {
                                            if (kotlin.jvm.internal.Intrinsics.areEqual(this.j, ((vn.com.vng.zalopay.search.presentation.analytics.v) p5).j)) {
                                                if (kotlin.jvm.internal.Intrinsics.areEqual(this.k, ((vn.com.vng.zalopay.search.presentation.analytics.v) p5).k)) {
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
        int v2_0;
        int v0_12 = (this.c.hashCode() * 31);
        int v2_11 = this.d;
        int v3 = 0;
        if (v2_11 != 0) {
            v2_0 = v2_11.hashCode();
        } else {
            v2_0 = 0;
        }
        int v2_2;
        int v0_2 = ((v0_12 + v2_0) * 31);
        int v2_1 = this.e;
        if (v2_1 != 0) {
            v2_2 = v2_1.hashCode();
        } else {
            v2_2 = 0;
        }
        int v2_6;
        int v0_7 = Kb.e.v(this.g, e5.i.c(((v0_2 + v2_2) * 31), 31, this.f), 31);
        int v2_5 = this.h;
        if (v2_5 != 0) {
            v2_6 = v2_5.hashCode();
        } else {
            v2_6 = 0;
        }
        int v2_8;
        int v0_9 = ((v0_7 + v2_6) * 31);
        int v2_7 = this.i;
        if (v2_7 != 0) {
            v2_8 = v2_7.hashCode();
        } else {
            v2_8 = 0;
        }
        int v2_10;
        int v0_11 = ((v0_9 + v2_8) * 31);
        int v2_9 = this.j;
        if (v2_9 != 0) {
            v2_10 = v2_9.hashCode();
        } else {
            v2_10 = 0;
        }
        int v0_14 = ((v0_11 + v2_10) * 31);
        String v1_1 = this.k;
        if (v1_1 != null) {
            v3 = v1_1.hashCode();
        }
        return (v0_14 + v3);
    }

    public final java.util.Map n()
    {
        return kotlin.collections.a0.mapOf(new kotlin.Pair[] {kotlin.TuplesKt.to("stage", "result"), kotlin.TuplesKt.to("type", "highlight_feature"), kotlin.TuplesKt.to("tab", "search_all")}));
    }

    public final String toString()
    {
        String v2_1 = this.e;
        String v3 = this.f;
        int v4 = this.g;
        String v5 = this.h;
        String v6 = this.i;
        String v7 = this.j;
        String v8 = this.k;
        String v0_1 = androidx.camera.camera2.internal.G.i("TelcoClicked(searchQueries=", this.c, ", appProfileId=", this.d, ", appProfileName=");
        q9.H.p(v0_1, v2_1, ", resultFrom=", v3, ", telcoSkuNumber=");
        e5.i.s(v4, ", telcoDataVolume=", v5, ", telcoPrice=", v0_1);
        q9.H.p(v0_1, v6, ", telcoValidity=", v7, ", telcoCashback=");
        return A1.a.l(v0_1, v8, ")");
    }
}
