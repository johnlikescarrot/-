package vn.ai.faceauth.sdk.presentation.domain.configs;
public final class AuthenInput implements android.os.Parcelable {
    public static final android.os.Parcelable$Creator CREATOR;
    private final String personId;
    private final String rawImage;
    private final String transId;

    static AuthenInput()
    {
        xq.vinxp.ffcdedcddfafbebfdacaaaab(66);
        vn.ai.faceauth.sdk.presentation.domain.configs.AuthenInput.CREATOR = new vn.ai.faceauth.sdk.presentation.domain.configs.AuthenInput$Creator();
        return;
    }

    public AuthenInput()
    {
        this(0, 0, 0, 7, 0);
        return;
    }

    public AuthenInput(String p1, String p2, String p3)
    {
        this.transId = p1;
        this.personId = p2;
        this.rawImage = p3;
        return;
    }

    public synthetic AuthenInput(String p2, String p3, String p4, int p5, kotlin.jvm.internal.DefaultConstructorMarker p6)
    {
        if ((p5 & 1) != 0) {
            p2 = "";
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

    public static synthetic vn.ai.faceauth.sdk.presentation.domain.configs.AuthenInput copy$default(vn.ai.faceauth.sdk.presentation.domain.configs.AuthenInput p0, String p1, String p2, String p3, int p4, Object p5)
    {
        if ((p4 & 1) != 0) {
            p1 = p0.transId;
        }
        if ((p4 & 2) != 0) {
            p2 = p0.personId;
        }
        if ((p4 & 4) != 0) {
            p3 = p0.rawImage;
        }
        return p0.copy(p1, p2, p3);
    }

    public final native String component1();

    public final native String component2();

    public final native String component3();

    public final native vn.ai.faceauth.sdk.presentation.domain.configs.AuthenInput copy(String p0, String p1, String p2);

    public native int describeContents();

    public native boolean equals(Object p0);

    public final native String getPersonId();

    public final native String getRawImage();

    public final native String getTransId();

    public native int hashCode();

    public native String toString();

    public native void writeToParcel(android.os.Parcel p0, int p1);
}
