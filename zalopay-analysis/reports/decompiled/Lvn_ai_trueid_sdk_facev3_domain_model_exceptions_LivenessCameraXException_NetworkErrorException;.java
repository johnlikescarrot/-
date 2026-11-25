package vn.ai.trueid.sdk.facev3.domain.model.exceptions;
public final class LivenessCameraXException$NetworkErrorException extends vn.ai.trueid.sdk.facev3.domain.model.exceptions.LivenessCameraXException {
    public final String a;

    public LivenessCameraXException$NetworkErrorException()
    {
        super(0);
        super.a = "NETWORK_ERROR";
        return;
    }

    public final boolean equals(Object p4)
    {
        if (this != p4) {
            if ((p4 instanceof vn.ai.trueid.sdk.facev3.domain.model.exceptions.LivenessCameraXException$NetworkErrorException)) {
                if (kotlin.jvm.internal.Intrinsics.areEqual(this.a, ((vn.ai.trueid.sdk.facev3.domain.model.exceptions.LivenessCameraXException$NetworkErrorException) p4).a)) {
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
        return A1.a.i("NetworkErrorException(message=", this.a, ")");
    }
}
