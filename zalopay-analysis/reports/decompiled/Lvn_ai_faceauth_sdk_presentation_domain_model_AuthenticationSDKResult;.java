package vn.ai.faceauth.sdk.presentation.domain.model;
public final class AuthenticationSDKResult {
    private final String authenResult;
    private final int code;
    private final String errorMessage;

    static AuthenticationSDKResult()
    {
        xq.vinxp.ffcdedcddfafbebfdacaaaab(18);
        return;
    }

    public AuthenticationSDKResult()
    {
        this(0, 0, 0, 7, 0);
        return;
    }

    public AuthenticationSDKResult(int p1, String p2, String p3)
    {
        this.code = p1;
        this.errorMessage = p2;
        this.authenResult = p3;
        return;
    }

    public synthetic AuthenticationSDKResult(int p2, String p3, String p4, int p5, kotlin.jvm.internal.DefaultConstructorMarker p6)
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

    public static synthetic vn.ai.faceauth.sdk.presentation.domain.model.AuthenticationSDKResult copy$default(vn.ai.faceauth.sdk.presentation.domain.model.AuthenticationSDKResult p0, int p1, String p2, String p3, int p4, Object p5)
    {
        if ((p4 & 1) != 0) {
            p1 = p0.code;
        }
        if ((p4 & 2) != 0) {
            p2 = p0.errorMessage;
        }
        if ((p4 & 4) != 0) {
            p3 = p0.authenResult;
        }
        return p0.copy(p1, p2, p3);
    }

    public final native int component1();

    public final native String component2();

    public final native String component3();

    public final native vn.ai.faceauth.sdk.presentation.domain.model.AuthenticationSDKResult copy(int p0, String p1, String p2);

    public native boolean equals(Object p0);

    public final native String getAuthenResult();

    public final native int getCode();

    public final native String getErrorMessage();

    public native int hashCode();

    public native String toString();
}
