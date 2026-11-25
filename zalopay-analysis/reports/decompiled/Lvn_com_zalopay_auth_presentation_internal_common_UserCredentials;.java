package vn.com.zalopay.auth.presentation.internal.common;
public final class UserCredentials {
    public static final int $stable;
    private final String pinHash;
    private final String uid;

    public UserCredentials(String p1, String p2)
    {
        this.uid = p1;
        this.pinHash = p2;
        return;
    }

    public final String a()
    {
        return this.pinHash;
    }

    public final String b()
    {
        return this.uid;
    }

    public final boolean equals(Object p5)
    {
        if (this != p5) {
            String v1_3;
            if (!p5) {
                v1_3 = 0;
            } else {
                v1_3 = p5.getClass();
            }
            if (kotlin.jvm.internal.Intrinsics.areEqual(vn.com.zalopay.auth.presentation.internal.common.UserCredentials, v1_3)) {
                if (kotlin.jvm.internal.Intrinsics.areEqual(this.uid, ((vn.com.zalopay.auth.presentation.internal.common.UserCredentials) p5).uid)) {
                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.pinHash, ((vn.com.zalopay.auth.presentation.internal.common.UserCredentials) p5).pinHash)) {
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
        return (this.pinHash.hashCode() + (this.uid.hashCode() * 31));
    }

    public final String toString()
    {
        return androidx.camera.camera2.internal.G.f("UserCredentials(uid=", this.uid, ", pinHash=", this.pinHash, ")");
    }
}
