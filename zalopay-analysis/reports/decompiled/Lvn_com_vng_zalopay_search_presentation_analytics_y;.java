package vn.com.vng.zalopay.search.presentation.analytics;
public final class y extends C3.c {
    private final String c;
    private final int d;
    private final Object e;
    private final String f;
    private final String g;
    private final String h;
    private final String i;
    private final String j;
    private final String k;
    private final String l;
    private final String m;

    public y(String p3, int p4, Integer p5, String p6, String p7, String p8, String p9, String p10, String p11, String p12, String p13)
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
        super.l = p12;
        super.m = p13;
        return;
    }

    public final boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5 instanceof vn.com.vng.zalopay.search.presentation.analytics.y)) {
                if (kotlin.jvm.internal.Intrinsics.areEqual(this.c, ((vn.com.vng.zalopay.search.presentation.analytics.y) p5).c)) {
                    if (this.d == ((vn.com.vng.zalopay.search.presentation.analytics.y) p5).d) {
                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.e, ((vn.com.vng.zalopay.search.presentation.analytics.y) p5).e)) {
                            if (kotlin.jvm.internal.Intrinsics.areEqual(this.f, ((vn.com.vng.zalopay.search.presentation.analytics.y) p5).f)) {
                                if (kotlin.jvm.internal.Intrinsics.areEqual(this.g, ((vn.com.vng.zalopay.search.presentation.analytics.y) p5).g)) {
                                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.h, ((vn.com.vng.zalopay.search.presentation.analytics.y) p5).h)) {
                                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.i, ((vn.com.vng.zalopay.search.presentation.analytics.y) p5).i)) {
                                            if (kotlin.jvm.internal.Intrinsics.areEqual(this.j, ((vn.com.vng.zalopay.search.presentation.analytics.y) p5).j)) {
                                                if (kotlin.jvm.internal.Intrinsics.areEqual(this.k, ((vn.com.vng.zalopay.search.presentation.analytics.y) p5).k)) {
                                                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.l, ((vn.com.vng.zalopay.search.presentation.analytics.y) p5).l)) {
                                                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.m, ((vn.com.vng.zalopay.search.presentation.analytics.y) p5).m)) {
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
        int v2_8;
        String v1_0 = 31;
        int v0_7 = e5.i.c(e5.i.c(e5.i.c(e5.i.c(e5.i.c(((this.e.hashCode() + Kb.e.v(this.d, (this.c.hashCode() * 31), 31)) * v1_0), v1_0, this.f), 31, this.g), 31, this.h), 31, this.i), 31, this.j);
        int v2_7 = this.k;
        int v3 = 0;
        if (v2_7 != 0) {
            v2_8 = v2_7.hashCode();
        } else {
            v2_8 = 0;
        }
        int v2_10;
        int v0_9 = ((v0_7 + v2_8) * 31);
        int v2_9 = this.l;
        if (v2_9 != 0) {
            v2_10 = v2_9.hashCode();
        } else {
            v2_10 = 0;
        }
        int v0_12 = ((v0_9 + v2_10) * 31);
        String v1_1 = this.m;
        if (v1_1 != null) {
            v3 = v1_1.hashCode();
        }
        return (v0_12 + v3);
    }

    public final java.util.Map n()
    {
        return kotlin.collections.Z.mapOf(kotlin.TuplesKt.to(wmoi.igob.ggcn(15564), wmoi.igob.ggcn(15565)));
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
        String v9 = this.l;
        String v10 = this.m;
        String v0_1 = androidx.compose.foundation.layout.G0.p(this.d, wmoi.igob.ggcn(15568), this.c, wmoi.igob.ggcn(15566), wmoi.igob.ggcn(15567));
        v0_1.append(v2_0);
        v0_1.append(wmoi.igob.ggcn(15569));
        v0_1.append(v3);
        v0_1.append(wmoi.igob.ggcn(15570));
        q9.H.p(v0_1, v4, wmoi.igob.ggcn(15571), v5, wmoi.igob.ggcn(15572));
        q9.H.p(v0_1, v6, wmoi.igob.ggcn(15573), v7, wmoi.igob.ggcn(15574));
        q9.H.p(v0_1, v8, wmoi.igob.ggcn(15575), v9, wmoi.igob.ggcn(15576));
        return A1.a.l(v0_1, v10, wmoi.igob.ggcn(15577));
    }
}
