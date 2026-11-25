package vn.ai.trueid.sdk.facev3.domain.model.exceptions;
public final class LivenessCameraXException$MaxLimitTryAgain extends vn.ai.trueid.sdk.facev3.domain.model.exceptions.LivenessCameraXException {
    public final String a;

    public LivenessCameraXException$MaxLimitTryAgain()
    {
        super(0);
        super.a = "MAX_RETRY";
        return;
    }

    public final boolean equals(Object p4)
    {
        if (this != p4) {
            if ((p4 instanceof vn.ai.trueid.sdk.facev3.domain.model.exceptions.LivenessCameraXException$MaxLimitTryAgain)) {
                if (kotlin.jvm.internal.Intrinsics.areEqual(this.a, ((vn.ai.trueid.sdk.facev3.domain.model.exceptions.LivenessCameraXException$MaxLimitTryAgain) p4).a)) {
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
        return A1.a.i("MaxLimitTryAgain(message=", this.a, ")");
    }
}
