package vn.ai.trueid.sdk.facev3.domain.model.exceptions;
public final class LivenessCameraXException$PermissionDenied extends vn.ai.trueid.sdk.facev3.domain.model.exceptions.LivenessCameraXException {
    public final String a;

    public LivenessCameraXException$PermissionDenied()
    {
        this(0);
        return;
    }

    public LivenessCameraXException$PermissionDenied(int p1)
    {
        super(0);
        super.a = "PERMISSION_DENIED";
        return;
    }

    public final boolean equals(Object p4)
    {
        if (this != p4) {
            if ((p4 instanceof vn.ai.trueid.sdk.facev3.domain.model.exceptions.LivenessCameraXException$PermissionDenied)) {
                if (kotlin.jvm.internal.Intrinsics.areEqual(this.a, ((vn.ai.trueid.sdk.facev3.domain.model.exceptions.LivenessCameraXException$PermissionDenied) p4).a)) {
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
        return A1.a.i("PermissionDenied(message=", this.a, ")");
    }
}
