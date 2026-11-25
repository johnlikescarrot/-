package vn.com.vng.zalopay.quickpay_native.domain.util;
final class a {
    private final String a;
    private final String b;

    public a()
    {
        this.a = 0;
        this.b = 0;
        return;
    }

    public final String a()
    {
        return this.a;
    }

    public final String b()
    {
        return this.b;
    }

    public final boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5 instanceof vn.com.vng.zalopay.quickpay_native.domain.util.a)) {
                if (kotlin.jvm.internal.Intrinsics.areEqual(this.a, ((vn.com.vng.zalopay.quickpay_native.domain.util.a) p5).a)) {
                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.b, ((vn.com.vng.zalopay.quickpay_native.domain.util.a) p5).b)) {
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
        int v0_2;
        int v0_0 = this.a;
        int v1 = 0;
        if (v0_0 != 0) {
            v0_2 = v0_0.hashCode();
        } else {
            v0_2 = 0;
        }
        int v0_3 = (v0_2 * 31);
        String v2 = this.b;
        if (v2 != null) {
            v1 = v2.hashCode();
        }
        return (v0_3 + v1);
    }

    public final String toString()
    {
        return androidx.camera.camera2.internal.G.f("ContextualOrderNoDto(orderNoCamel=", this.a, ", orderNoSnake=", this.b, ")");
    }
}
