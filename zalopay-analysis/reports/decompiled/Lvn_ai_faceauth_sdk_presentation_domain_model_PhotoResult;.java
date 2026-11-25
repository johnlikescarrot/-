package vn.ai.faceauth.sdk.presentation.domain.model;
public final class PhotoResult {
    private final String createdAt;
    private final String fileBase64;

    static PhotoResult()
    {
        xq.vinxp.ffcdedcddfafbebfdacaaaab(44);
        return;
    }

    public PhotoResult(String p1, String p2)
    {
        this.createdAt = p1;
        this.fileBase64 = p2;
        return;
    }

    public static synthetic vn.ai.faceauth.sdk.presentation.domain.model.PhotoResult copy$default(vn.ai.faceauth.sdk.presentation.domain.model.PhotoResult p0, String p1, String p2, int p3, Object p4)
    {
        if ((p3 & 1) != 0) {
            p1 = p0.createdAt;
        }
        if ((p3 & 2) != 0) {
            p2 = p0.fileBase64;
        }
        return p0.copy(p1, p2);
    }

    public final native String component1();

    public final native String component2();

    public final native vn.ai.faceauth.sdk.presentation.domain.model.PhotoResult copy(String p0, String p1);

    public native boolean equals(Object p0);

    public final native String getCreatedAt();

    public final native String getFileBase64();

    public native int hashCode();

    public native String toString();
}
