package vn.ai.trueid.sdk.face;
public final enum class k extends java.lang.Enum {
    private static final synthetic vn.ai.trueid.sdk.face.k[] $VALUES;
    public static final enum vn.ai.trueid.sdk.face.k API_NETWORK_ERROR;
    public static final enum vn.ai.trueid.sdk.face.k CONTEXT_SWITCHED;
    public static final vn.ai.trueid.sdk.face.j Companion;
    public static final enum vn.ai.trueid.sdk.face.k EMULATOR;
    public static final enum vn.ai.trueid.sdk.face.k MAX_RETRY;
    public static final enum vn.ai.trueid.sdk.face.k NETWORK_ERROR;
    public static final enum vn.ai.trueid.sdk.face.k PERMISSION_DENIED;
    public static final enum vn.ai.trueid.sdk.face.k PERMISSION_UNKNOWN;
    public static final enum vn.ai.trueid.sdk.face.k TIME_OUT;
    public static final enum vn.ai.trueid.sdk.face.k USER_CANCELED;
    private final int code;

    private static final synthetic vn.ai.trueid.sdk.face.k[] $values()
    {
        vn.ai.trueid.sdk.face.k v2 = vn.ai.trueid.sdk.face.k.CONTEXT_SWITCHED;
        vn.ai.trueid.sdk.face.k v4 = vn.ai.trueid.sdk.face.k.PERMISSION_DENIED;
        vn.ai.trueid.sdk.face.k v6 = vn.ai.trueid.sdk.face.k.NETWORK_ERROR;
        return new vn.ai.trueid.sdk.face.k[] {vn.ai.trueid.sdk.face.k.USER_CANCELED, vn.ai.trueid.sdk.face.k.EMULATOR});
    }

    public static k()
    {
        vn.ai.trueid.sdk.face.k.USER_CANCELED = new vn.ai.trueid.sdk.face.k("USER_CANCELED", 0, 0);
        vn.ai.trueid.sdk.face.k.TIME_OUT = new vn.ai.trueid.sdk.face.k("TIME_OUT", 1, 301);
        vn.ai.trueid.sdk.face.k.CONTEXT_SWITCHED = new vn.ai.trueid.sdk.face.k("CONTEXT_SWITCHED", 2, 304);
        vn.ai.trueid.sdk.face.k.MAX_RETRY = new vn.ai.trueid.sdk.face.k("MAX_RETRY", 3, 305);
        vn.ai.trueid.sdk.face.k.PERMISSION_DENIED = new vn.ai.trueid.sdk.face.k("PERMISSION_DENIED", 4, 306);
        vn.ai.trueid.sdk.face.k.PERMISSION_UNKNOWN = new vn.ai.trueid.sdk.face.k("PERMISSION_UNKNOWN", 5, 306);
        vn.ai.trueid.sdk.face.k.NETWORK_ERROR = new vn.ai.trueid.sdk.face.k("NETWORK_ERROR", 6, 400);
        vn.ai.trueid.sdk.face.k.API_NETWORK_ERROR = new vn.ai.trueid.sdk.face.k("API_NETWORK_ERROR", 7, 440);
        vn.ai.trueid.sdk.face.k.EMULATOR = new vn.ai.trueid.sdk.face.k("EMULATOR", 8, 300);
        vn.ai.trueid.sdk.face.k.$VALUES = vn.ai.trueid.sdk.face.k.$values();
        vn.ai.trueid.sdk.face.k.Companion = new vn.ai.trueid.sdk.face.j();
        return;
    }

    private k(String p1, int p2, int p3)
    {
        super(p1, p2);
        super.code = p3;
        return;
    }

    public static vn.ai.trueid.sdk.face.k valueOf(String p1)
    {
        return ((vn.ai.trueid.sdk.face.k) Enum.valueOf(vn.ai.trueid.sdk.face.k, p1));
    }

    public static vn.ai.trueid.sdk.face.k[] values()
    {
        return ((vn.ai.trueid.sdk.face.k[]) vn.ai.trueid.sdk.face.k.$VALUES.clone());
    }

    public final int getCode()
    {
        return this.code;
    }
}
