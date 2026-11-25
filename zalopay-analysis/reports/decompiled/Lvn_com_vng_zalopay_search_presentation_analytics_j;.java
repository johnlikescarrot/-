package vn.com.vng.zalopay.search.presentation.analytics;
public final class j extends C3.c {
    private final String c;
    private final Integer d;
    private final String e;
    private final String f;
    private final String g;
    private final int h;
    private final String i;
    private final String j;

    public j(String p3, Integer p4, String p5, String p6, String p7, int p8, String p9, String p10)
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
        return;
    }

    public final boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5 instanceof vn.com.vng.zalopay.search.presentation.analytics.j)) {
                if (kotlin.jvm.internal.Intrinsics.areEqual(this.c, ((vn.com.vng.zalopay.search.presentation.analytics.j) p5).c)) {
                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.d, ((vn.com.vng.zalopay.search.presentation.analytics.j) p5).d)) {
                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.e, ((vn.com.vng.zalopay.search.presentation.analytics.j) p5).e)) {
                            if (kotlin.jvm.internal.Intrinsics.areEqual(this.f, ((vn.com.vng.zalopay.search.presentation.analytics.j) p5).f)) {
                                if (kotlin.jvm.internal.Intrinsics.areEqual(this.g, ((vn.com.vng.zalopay.search.presentation.analytics.j) p5).g)) {
                                    if (this.h == ((vn.com.vng.zalopay.search.presentation.analytics.j) p5).h) {
                                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.i, ((vn.com.vng.zalopay.search.presentation.analytics.j) p5).i)) {
                                            if (kotlin.jvm.internal.Intrinsics.areEqual(this.j, ((vn.com.vng.zalopay.search.presentation.analytics.j) p5).j)) {
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
            return 1;
        }
    }

    public final int hashCode()
    {
        int v2_0;
        int v0_11 = (this.c.hashCode() * 31);
        int v2_7 = this.d;
        int v3 = 0;
        if (v2_7 != 0) {
            v2_0 = v2_7.hashCode();
        } else {
            v2_0 = 0;
        }
        int v2_6;
        int v0_7 = Kb.e.v(this.h, e5.i.c(e5.i.c(e5.i.c(((v0_11 + v2_0) * 31), 31, this.e), 31, this.f), 31, this.g), 31);
        int v2_5 = this.i;
        if (v2_5 != 0) {
            v2_6 = v2_5.hashCode();
        } else {
            v2_6 = 0;
        }
        int v0_9 = ((v0_7 + v2_6) * 31);
        String v1_1 = this.j;
        if (v1_1 != null) {
            v3 = v1_1.hashCode();
        }
        return (v0_9 + v3);
    }

    public final java.util.Map n()
    {
        return kotlin.collections.a0.mapOf(new kotlin.Pair[] {kotlin.TuplesKt.to("stage", "result"), kotlin.TuplesKt.to("type", "highlight_feature"), kotlin.TuplesKt.to("tab", "search_all")}));
    }

    public final String toString()
    {
        String v0_0 = this.c;
        String v1_0 = this.d;
        String v2 = this.e;
        String v3 = this.f;
        String v4 = this.g;
        int v5 = this.h;
        String v6 = this.i;
        String v7 = this.j;
        StringBuilder v8_0 = new StringBuilder("MovieClicked(searchQueries=");
        v8_0.append(v0_0);
        v8_0.append(", order=");
        v8_0.append(v1_0);
        v8_0.append(", appProfileId=");
        q9.H.p(v8_0, v2, ", appProfileName=", v3, ", resultFrom=");
        org.bouncycastle.jcajce.provider.asymmetric.a.s(v5, v4, ", filmNumber=", ", filmName=", v8_0);
        return androidx.compose.foundation.layout.G0.n(v8_0, v6, ", filmCategory=", v7, ")");
    }
}
