package vn.com.vng.zalopay.livenessdetection.domain.model.exceptions;
public final class LivenessCameraXException$StartCameraException extends vn.com.vng.zalopay.livenessdetection.domain.model.exceptions.LivenessCameraXException {
    public final String a;
    public final Throwable b;

    public LivenessCameraXException$StartCameraException(String p2, Throwable p3)
    {
        super(0);
        super.a = p2;
        super.b = p3;
        return;
    }

    public final boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5 instanceof vn.com.vng.zalopay.livenessdetection.domain.model.exceptions.LivenessCameraXException$StartCameraException)) {
                if (kotlin.jvm.internal.Intrinsics.areEqual(this.a, ((vn.com.vng.zalopay.livenessdetection.domain.model.exceptions.LivenessCameraXException$StartCameraException) p5).a)) {
                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.b, ((vn.com.vng.zalopay.livenessdetection.domain.model.exceptions.LivenessCameraXException$StartCameraException) p5).b)) {
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

    public final Throwable getCause()
    {
        return this.b;
    }

    public final String getMessage()
    {
        return this.a;
    }

    public final int hashCode()
    {
        int v1_2;
        int v0 = 0;
        int v1_0 = this.a;
        if (v1_0 != 0) {
            v1_2 = v1_0.hashCode();
        } else {
            v1_2 = 0;
        }
        int v1_3 = (v1_2 * 31);
        Throwable v2 = this.b;
        if (v2 != null) {
            v0 = v2.hashCode();
        }
        return (v1_3 + v0);
    }

    public final String toString()
    {
        String v0_2 = new StringBuilder("StartCameraException(message=");
        v0_2.append(this.a);
        v0_2.append(", cause=");
        v0_2.append(this.b);
        v0_2.append(")");
        return v0_2.toString();
    }
}
