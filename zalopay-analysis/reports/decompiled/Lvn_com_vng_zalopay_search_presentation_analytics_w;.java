package vn.com.vng.zalopay.search.presentation.analytics;
public final class w extends C3.c {
    private final String c;
    private final java.util.List d;
    private final int e;

    public w(String p3, java.util.List p4, int p5)
    {
        super(994, 9);
        super.c = p3;
        super.d = p4;
        super.e = p5;
        return;
    }

    public final boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5 instanceof vn.com.vng.zalopay.search.presentation.analytics.w)) {
                if (kotlin.jvm.internal.Intrinsics.areEqual(this.c, ((vn.com.vng.zalopay.search.presentation.analytics.w) p5).c)) {
                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.d, ((vn.com.vng.zalopay.search.presentation.analytics.w) p5).d)) {
                        if (this.e == ((vn.com.vng.zalopay.search.presentation.analytics.w) p5).e) {
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
            return 1;
        }
    }

    public final int hashCode()
    {
        return (Integer.hashCode(this.e) + Kb.e.h((this.c.hashCode() * 31), 31, this.d));
    }

    public final java.util.Map n()
    {
        return kotlin.collections.Z.mapOf(kotlin.TuplesKt.to(wmoi.igob.ggcn(15555), wmoi.igob.ggcn(15556)));
    }

    public final String toString()
    {
        return A1.a.h(this.e, wmoi.igob.ggcn(15560), com.alibaba.ariver.commonability.device.jsapi.clipboard.a.p(wmoi.igob.ggcn(15559), this.c, wmoi.igob.ggcn(15557), wmoi.igob.ggcn(15558), this.d));
    }
}
