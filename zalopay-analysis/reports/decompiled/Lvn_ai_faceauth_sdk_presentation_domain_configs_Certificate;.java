package vn.ai.faceauth.sdk.presentation.domain.configs;
public final class Certificate {
    private String cert;
    private String domain;

    static Certificate()
    {
        xq.vinxp.ffcdedcddfafbebfdacaaaab(53);
        return;
    }

    public Certificate(String p1, String p2)
    {
        this.domain = p1;
        this.cert = p2;
        return;
    }

    public static synthetic vn.ai.faceauth.sdk.presentation.domain.configs.Certificate copy$default(vn.ai.faceauth.sdk.presentation.domain.configs.Certificate p0, String p1, String p2, int p3, Object p4)
    {
        if ((p3 & 1) != 0) {
            p1 = p0.domain;
        }
        if ((p3 & 2) != 0) {
            p2 = p0.cert;
        }
        return p0.copy(p1, p2);
    }

    public final native String component1();

    public final native String component2();

    public final native vn.ai.faceauth.sdk.presentation.domain.configs.Certificate copy(String p0, String p1);

    public native boolean equals(Object p0);

    public final native String getCert();

    public final native String getDomain();

    public native int hashCode();

    public final native void setCert(String p0);

    public final native void setDomain(String p0);

    public native String toString();
}
