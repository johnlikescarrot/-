package vn.com.vng.zalopay.search.presentation.analytics;
public final class b extends C3.c {
    private final String c;
    private final String d;

    public b(String p3, String p4)
    {
        super(998, 9);
        super.c = p3;
        super.d = p4;
        return;
    }

    public final boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5 instanceof vn.com.vng.zalopay.search.presentation.analytics.b)) {
                if (kotlin.jvm.internal.Intrinsics.areEqual(this.c, ((vn.com.vng.zalopay.search.presentation.analytics.b) p5).c)) {
                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.d, ((vn.com.vng.zalopay.search.presentation.analytics.b) p5).d)) {
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
            return 1;
        }
    }

    public final int hashCode()
    {
        int v1_1;
        int v0_2 = (this.c.hashCode() * 31);
        int v1_0 = this.d;
        if (v1_0 != 0) {
            v1_1 = v1_0.hashCode();
        } else {
            v1_1 = 0;
        }
        return (v0_2 + v1_1);
    }

    public final String toString()
    {
        return androidx.camera.camera2.internal.G.f(wmoi.igob.ggcn(15835), this.c, wmoi.igob.ggcn(15833), this.d, wmoi.igob.ggcn(15834));
    }
}
