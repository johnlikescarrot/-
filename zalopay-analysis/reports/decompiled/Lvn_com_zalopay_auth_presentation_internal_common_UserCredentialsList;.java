package vn.com.zalopay.auth.presentation.internal.common;
public final class UserCredentialsList {
    public static final int $stable = 8;
    private final java.util.List credentials;

    public UserCredentialsList(java.util.List p1)
    {
        this.credentials = p1;
        return;
    }

    public final java.util.List a()
    {
        return this.credentials;
    }

    public final boolean equals(Object p4)
    {
        if (this != p4) {
            if ((p4 instanceof vn.com.zalopay.auth.presentation.internal.common.UserCredentialsList)) {
                if (kotlin.jvm.internal.Intrinsics.areEqual(this.credentials, ((vn.com.zalopay.auth.presentation.internal.common.UserCredentialsList) p4).credentials)) {
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
        return this.credentials.hashCode();
    }

    public final String toString()
    {
        return com.alibaba.ariver.commonability.device.jsapi.clipboard.a.k("UserCredentialsList(credentials=", ")", this.credentials);
    }
}
