package vn.com.vng.zalopay.miniapp.api.v2.common;
public final class UserLocalConfigModule$b {
    private final String a;
    private final String b;

    public final String a()
    {
        return this.b;
    }

    public final String b()
    {
        return this.a;
    }

    public final boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5 instanceof vn.com.vng.zalopay.miniapp.api.v2.common.UserLocalConfigModule$b)) {
                if (kotlin.jvm.internal.Intrinsics.areEqual(this.a, ((vn.com.vng.zalopay.miniapp.api.v2.common.UserLocalConfigModule$b) p5).a)) {
                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.b, ((vn.com.vng.zalopay.miniapp.api.v2.common.UserLocalConfigModule$b) p5).b)) {
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
        return (this.b.hashCode() + (this.a.hashCode() * 31));
    }

    public final String toString()
    {
        return androidx.camera.camera2.internal.G.f("SetupItem(type=", this.a, ", mode=", this.b, ")");
    }
}
