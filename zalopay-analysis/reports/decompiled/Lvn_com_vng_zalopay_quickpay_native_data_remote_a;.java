package vn.com.vng.zalopay.quickpay_native.data.remote;
final class a {
    private final long a;
    private final String b;

    public a(long p1, String p3)
    {
        this.a = p1;
        this.b = p3;
        return;
    }

    public final boolean equals(Object p8)
    {
        if (this != p8) {
            if ((p8 instanceof vn.com.vng.zalopay.quickpay_native.data.remote.a)) {
                if (this.a == ((vn.com.vng.zalopay.quickpay_native.data.remote.a) p8).a) {
                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.b, ((vn.com.vng.zalopay.quickpay_native.data.remote.a) p8).b)) {
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
        int v0_2 = (Long.hashCode(this.a) * 31);
        int v1_0 = this.b;
        if (v1_0 != 0) {
            v1_1 = v1_0.hashCode();
        } else {
            v1_1 = 0;
        }
        return (v0_2 + v1_1);
    }

    public final String toString()
    {
        String v0_2 = com.alibaba.ariver.commonability.device.jsapi.clipboard.a.n(wmoi.igob.ggcn(2229), this.a, wmoi.igob.ggcn(2230), this.b);
        v0_2.append(wmoi.igob.ggcn(2231));
        return v0_2.toString();
    }
}
