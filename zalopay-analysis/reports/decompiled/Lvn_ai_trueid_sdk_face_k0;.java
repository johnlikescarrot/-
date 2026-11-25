package vn.ai.trueid.sdk.face;
public final class k0 extends vn.ai.trueid.sdk.face.n0 {
    public final String a;

    public k0(String p2)
    {
        super(0);
        super.a = p2;
        return;
    }

    public final boolean equals(Object p4)
    {
        if (this != p4) {
            if ((p4 instanceof vn.ai.trueid.sdk.face.k0)) {
                if (kotlin.jvm.internal.Intrinsics.areEqual(this.a, ((vn.ai.trueid.sdk.face.k0) p4).a)) {
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

    public final String getMessage()
    {
        return this.a;
    }

    public final int hashCode()
    {
        int v0_0 = this.a;
        if (v0_0 != 0) {
            return v0_0.hashCode();
        } else {
            return 0;
        }
    }

    public final String toString()
    {
        return org.bouncycastle.jcajce.provider.asymmetric.a.d(new StringBuilder("ContextSwitchException(message="), this.a, 41);
    }
}
