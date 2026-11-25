package vn.ai.faceauth.sdk.presentation.domain.model;
public final class FaceMatchResult {
    private final int code;
    private final String errorMessage;
    private final String faceMatchResult;

    static FaceMatchResult()
    {
        xq.vinxp.ffcdedcddfafbebfdacaaaab(39);
        return;
    }

    public FaceMatchResult()
    {
        this(0, 0, 0, 7, 0);
        return;
    }

    public FaceMatchResult(int p1, String p2, String p3)
    {
        this.code = p1;
        this.errorMessage = p2;
        this.faceMatchResult = p3;
        return;
    }

    public synthetic FaceMatchResult(int p2, String p3, String p4, int p5, kotlin.jvm.internal.DefaultConstructorMarker p6)
    {
        if ((p5 & 1) != 0) {
            p2 = 0;
        }
        if ((p5 & 2) != 0) {
            p3 = "";
        }
        if ((p5 & 4) != 0) {
            p4 = "";
        }
        this(p2, p3, p4);
        return;
    }

    public static synthetic vn.ai.faceauth.sdk.presentation.domain.model.FaceMatchResult copy$default(vn.ai.faceauth.sdk.presentation.domain.model.FaceMatchResult p0, int p1, String p2, String p3, int p4, Object p5)
    {
        if ((p4 & 1) != 0) {
            p1 = p0.code;
        }
        if ((p4 & 2) != 0) {
            p2 = p0.errorMessage;
        }
        if ((p4 & 4) != 0) {
            p3 = p0.faceMatchResult;
        }
        return p0.copy(p1, p2, p3);
    }

    public final native int component1();

    public final native String component2();

    public final native String component3();

    public final native vn.ai.faceauth.sdk.presentation.domain.model.FaceMatchResult copy(int p0, String p1, String p2);

    public native boolean equals(Object p0);

    public final native int getCode();

    public final native String getErrorMessage();

    public final native String getFaceMatchResult();

    public native int hashCode();

    public native String toString();
}
