package vn.ai.faceauth.sdk.presentation.domain.configs;
public final class SDKConfig {
    private String nonce;
    private int processType;

    static SDKConfig()
    {
        xq.vinxp.ffcdedcddfafbebfdacaaaab(47);
        return;
    }

    public SDKConfig(String p2)
    {
        this(p2, 1);
        return;
    }

    public SDKConfig(String p1, int p2)
    {
        this.nonce = p1;
        this.processType = p2;
        return;
    }

    public static synthetic vn.ai.faceauth.sdk.presentation.domain.configs.SDKConfig copy$default(vn.ai.faceauth.sdk.presentation.domain.configs.SDKConfig p0, String p1, int p2, int p3, Object p4)
    {
        if ((p3 & 1) != 0) {
            p1 = p0.nonce;
        }
        if ((p3 & 2) != 0) {
            p2 = p0.processType;
        }
        return p0.copy(p1, p2);
    }

    public final native String component1();

    public final native int component2();

    public final native vn.ai.faceauth.sdk.presentation.domain.configs.SDKConfig copy(String p0, int p1);

    public native boolean equals(Object p0);

    public final native String getNonce();

    public final native int getProcessType();

    public native int hashCode();

    public final native void setNonce(String p0);

    public final native void setProcessType(int p0);

    public native String toString();
}
