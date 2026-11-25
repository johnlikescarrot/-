package vn.com.vng.zalopay.livenessdetection.domain.model.exceptions;
public final class LivenessCameraXException$UserCanceledException extends vn.com.vng.zalopay.livenessdetection.domain.model.exceptions.LivenessCameraXException {
    public final String a;

    public LivenessCameraXException$UserCanceledException()
    {
        this(0);
        return;
    }

    public LivenessCameraXException$UserCanceledException(int p1)
    {
        super(0);
        super.a = "USER_CANCELED";
        return;
    }

    public final boolean equals(Object p4)
    {
        if (this != p4) {
            if ((p4 instanceof vn.com.vng.zalopay.livenessdetection.domain.model.exceptions.LivenessCameraXException$UserCanceledException)) {
                if (kotlin.jvm.internal.Intrinsics.areEqual(this.a, ((vn.com.vng.zalopay.livenessdetection.domain.model.exceptions.LivenessCameraXException$UserCanceledException) p4).a)) {
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
        return A1.a.i("UserCanceledException(message=", this.a, ")");
    }
}
