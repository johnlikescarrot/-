package vn.ai.faceauth.sdk.presentation.domain.model;
public final class LivenessCameraXError {
    private final String cause;
    private final Exception exception;
    private final String message;

    static LivenessCameraXError()
    {
        xq.vinxp.ffcdedcddfafbebfdacaaaab(71);
        return;
    }

    public LivenessCameraXError(String p1, String p2, Exception p3)
    {
        this.message = p1;
        this.cause = p2;
        this.exception = p3;
        return;
    }

    public static synthetic vn.ai.faceauth.sdk.presentation.domain.model.LivenessCameraXError copy$default(vn.ai.faceauth.sdk.presentation.domain.model.LivenessCameraXError p0, String p1, String p2, Exception p3, int p4, Object p5)
    {
        if ((p4 & 1) != 0) {
            p1 = p0.message;
        }
        if ((p4 & 2) != 0) {
            p2 = p0.cause;
        }
        if ((p4 & 4) != 0) {
            p3 = p0.exception;
        }
        return p0.copy(p1, p2, p3);
    }

    public final native String component1();

    public final native String component2();

    public final native Exception component3();

    public final native vn.ai.faceauth.sdk.presentation.domain.model.LivenessCameraXError copy(String p0, String p1, Exception p2);

    public native boolean equals(Object p0);

    public final native String getCause();

    public final native Exception getException();

    public final native String getMessage();

    public native int hashCode();

    public native String toString();
}
