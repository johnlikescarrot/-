package vn.com.vng.zalopay.miniapp.api.v2.common;
public final class UserLocalConfigModule$c {
    private final java.util.List a;

    public UserLocalConfigModule$c(java.util.List p1)
    {
        this.a = p1;
        return;
    }

    public final java.util.List a()
    {
        return this.a;
    }

    public final boolean equals(Object p4)
    {
        if (this != p4) {
            if ((p4 instanceof vn.com.vng.zalopay.miniapp.api.v2.common.UserLocalConfigModule$c)) {
                if (kotlin.jvm.internal.Intrinsics.areEqual(this.a, ((vn.com.vng.zalopay.miniapp.api.v2.common.UserLocalConfigModule$c) p4).a)) {
                    return 1;
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
        return this.a.hashCode();
    }

    public final String toString()
    {
        return com.alibaba.ariver.commonability.device.jsapi.clipboard.a.k("WebParamUserLocalConfigRequest(types=", ")", this.a);
    }
}
