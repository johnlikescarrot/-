package vn.com.vng.zalopay.search.presentation.analytics;
public final class m extends C3.c {
    private final String c;
    private final String d;
    private final String e;

    public m(String p3, String p4, String p5)
    {
        super(997, 9);
        super.c = p3;
        super.d = p4;
        super.e = p5;
        return;
    }

    public final boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5 instanceof vn.com.vng.zalopay.search.presentation.analytics.m)) {
                if (kotlin.jvm.internal.Intrinsics.areEqual(this.c, ((vn.com.vng.zalopay.search.presentation.analytics.m) p5).c)) {
                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.d, ((vn.com.vng.zalopay.search.presentation.analytics.m) p5).d)) {
                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.e, ((vn.com.vng.zalopay.search.presentation.analytics.m) p5).e)) {
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
        String v1_2;
        int v0_5 = (this.c.hashCode() * 31);
        String v1_1 = this.d;
        int v2 = 0;
        if (v1_1 != null) {
            v1_2 = v1_1.hashCode();
        } else {
            v1_2 = 0;
        }
        int v0_2 = ((v0_5 + v1_2) * 31);
        String v1_0 = this.e;
        if (v1_0 != null) {
            v2 = v1_0.hashCode();
        }
        return (v0_2 + v2);
    }

    public final String toString()
    {
        return A1.a.l(androidx.camera.camera2.internal.G.i(wmoi.igob.ggcn(15610), this.c, wmoi.igob.ggcn(15608), this.d, wmoi.igob.ggcn(15609)), this.e, wmoi.igob.ggcn(15611));
    }
}
