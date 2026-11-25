package vn.ai.faceauth.sdk.presentation.domain.model;
public final class LivenessCameraXResult {
    private final vn.ai.faceauth.sdk.presentation.domain.model.LivenessCameraXError error;
    private final vn.ai.faceauth.sdk.presentation.domain.model.LivenessResponse successLivenessResponse;

    static LivenessCameraXResult()
    {
        xq.vinxp.ffcdedcddfafbebfdacaaaab(8);
        return;
    }

    public LivenessCameraXResult()
    {
        this(0, 0, 3, 0);
        return;
    }

    public LivenessCameraXResult(vn.ai.faceauth.sdk.presentation.domain.model.LivenessResponse p1, vn.ai.faceauth.sdk.presentation.domain.model.LivenessCameraXError p2)
    {
        this.successLivenessResponse = p1;
        this.error = p2;
        return;
    }

    public synthetic LivenessCameraXResult(vn.ai.faceauth.sdk.presentation.domain.model.LivenessResponse p2, vn.ai.faceauth.sdk.presentation.domain.model.LivenessCameraXError p3, int p4, kotlin.jvm.internal.DefaultConstructorMarker p5)
    {
        if ((p4 & 1) != 0) {
            p2 = 0;
        }
        if ((p4 & 2) != 0) {
            p3 = 0;
        }
        this(p2, p3);
        return;
    }

    public static synthetic vn.ai.faceauth.sdk.presentation.domain.model.LivenessCameraXResult copy$default(vn.ai.faceauth.sdk.presentation.domain.model.LivenessCameraXResult p0, vn.ai.faceauth.sdk.presentation.domain.model.LivenessResponse p1, vn.ai.faceauth.sdk.presentation.domain.model.LivenessCameraXError p2, int p3, Object p4)
    {
        if ((p3 & 1) != 0) {
            p1 = p0.successLivenessResponse;
        }
        if ((p3 & 2) != 0) {
            p2 = p0.error;
        }
        return p0.copy(p1, p2);
    }

    public final native vn.ai.faceauth.sdk.presentation.domain.model.LivenessResponse component1();

    public final native vn.ai.faceauth.sdk.presentation.domain.model.LivenessCameraXError component2();

    public final native vn.ai.faceauth.sdk.presentation.domain.model.LivenessCameraXResult copy(vn.ai.faceauth.sdk.presentation.domain.model.LivenessResponse p0, vn.ai.faceauth.sdk.presentation.domain.model.LivenessCameraXError p1);

    public native boolean equals(Object p0);

    public final native vn.ai.faceauth.sdk.presentation.domain.model.LivenessCameraXError getError();

    public final native vn.ai.faceauth.sdk.presentation.domain.model.LivenessResponse getSuccessLivenessResponse();

    public native int hashCode();

    public native String toString();
}
