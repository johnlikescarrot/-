package vn.ai.faceauth.sdk.presentation.domain.configs;
public final class ConfigEndPoint implements android.os.Parcelable {
    public static final android.os.Parcelable$Creator CREATOR;
    private final String oauthAccessToken;
    private final String requestLiveness;

    static ConfigEndPoint()
    {
        xq.vinxp.ffcdedcddfafbebfdacaaaab(50);
        vn.ai.faceauth.sdk.presentation.domain.configs.ConfigEndPoint.CREATOR = new vn.ai.faceauth.sdk.presentation.domain.configs.ConfigEndPoint$Creator();
        return;
    }

    public ConfigEndPoint()
    {
        this(0, 0, 3, 0);
        return;
    }

    public ConfigEndPoint(String p1, String p2)
    {
        this.oauthAccessToken = p1;
        this.requestLiveness = p2;
        return;
    }

    public synthetic ConfigEndPoint(String p2, String p3, int p4, kotlin.jvm.internal.DefaultConstructorMarker p5)
    {
        if ((p4 & 1) != 0) {
            p2 = "";
        }
        if ((p4 & 2) != 0) {
            p3 = "";
        }
        this(p2, p3);
        return;
    }

    public static synthetic vn.ai.faceauth.sdk.presentation.domain.configs.ConfigEndPoint copy$default(vn.ai.faceauth.sdk.presentation.domain.configs.ConfigEndPoint p0, String p1, String p2, int p3, Object p4)
    {
        if ((p3 & 1) != 0) {
            p1 = p0.oauthAccessToken;
        }
        if ((p3 & 2) != 0) {
            p2 = p0.requestLiveness;
        }
        return p0.copy(p1, p2);
    }

    public final native String component1();

    public final native String component2();

    public final native vn.ai.faceauth.sdk.presentation.domain.configs.ConfigEndPoint copy(String p0, String p1);

    public native int describeContents();

    public native boolean equals(Object p0);

    public final native String getOauthAccessToken();

    public final native String getRequestLiveness();

    public native int hashCode();

    public native String toString();

    public native void writeToParcel(android.os.Parcel p0, int p1);
}
