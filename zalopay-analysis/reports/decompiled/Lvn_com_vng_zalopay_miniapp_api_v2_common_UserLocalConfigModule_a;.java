package vn.com.vng.zalopay.miniapp.api.v2.common;
public final class UserLocalConfigModule$a {
    private final String a;
    private final String b;
    private final boolean c;

    public UserLocalConfigModule$a(String p1, String p2, boolean p3)
    {
        this.a = p1;
        this.b = p2;
        this.c = p3;
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

    public final boolean c()
    {
        return this.c;
    }

    public final boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5 instanceof vn.com.vng.zalopay.miniapp.api.v2.common.UserLocalConfigModule$a)) {
                if (kotlin.jvm.internal.Intrinsics.areEqual(this.a, ((vn.com.vng.zalopay.miniapp.api.v2.common.UserLocalConfigModule$a) p5).a)) {
                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.b, ((vn.com.vng.zalopay.miniapp.api.v2.common.UserLocalConfigModule$a) p5).b)) {
                        if (this.c == ((vn.com.vng.zalopay.miniapp.api.v2.common.UserLocalConfigModule$a) p5).c) {
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
        return (Boolean.hashCode(this.c) + e5.i.c((this.a.hashCode() * 31), 31, this.b));
    }

    public final String toString()
    {
        return androidx.activity.result.e.q(")", androidx.camera.camera2.internal.G.i("ResultItem(status=", this.a, ", type=", this.b, ", is_enable="), this.c);
    }
}
