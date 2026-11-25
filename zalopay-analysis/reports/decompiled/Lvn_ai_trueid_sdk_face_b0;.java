package vn.ai.trueid.sdk.face;
public final enum class b0 extends java.lang.Enum {
    private static final synthetic vn.ai.trueid.sdk.face.b0[] $VALUES;
    public static final enum vn.ai.trueid.sdk.face.b0 FIRST;
    public static final enum vn.ai.trueid.sdk.face.b0 FLASH_FIRST;
    public static final enum vn.ai.trueid.sdk.face.b0 FLASH_SECOND;

    private static final synthetic vn.ai.trueid.sdk.face.b0[] $values()
    {
        return new vn.ai.trueid.sdk.face.b0[] {vn.ai.trueid.sdk.face.b0.FIRST, vn.ai.trueid.sdk.face.b0.FLASH_FIRST, vn.ai.trueid.sdk.face.b0.FLASH_SECOND});
    }

    public static b0()
    {
        vn.ai.trueid.sdk.face.b0.FIRST = new vn.ai.trueid.sdk.face.b0("FIRST", 0);
        vn.ai.trueid.sdk.face.b0.FLASH_FIRST = new vn.ai.trueid.sdk.face.b0("FLASH_FIRST", 1);
        vn.ai.trueid.sdk.face.b0.FLASH_SECOND = new vn.ai.trueid.sdk.face.b0("FLASH_SECOND", 2);
        vn.ai.trueid.sdk.face.b0.$VALUES = vn.ai.trueid.sdk.face.b0.$values();
        return;
    }

    private b0(String p1, int p2)
    {
        super(p1, p2);
        return;
    }

    public static vn.ai.trueid.sdk.face.b0 valueOf(String p1)
    {
        return ((vn.ai.trueid.sdk.face.b0) Enum.valueOf(vn.ai.trueid.sdk.face.b0, p1));
    }

    public static vn.ai.trueid.sdk.face.b0[] values()
    {
        return ((vn.ai.trueid.sdk.face.b0[]) vn.ai.trueid.sdk.face.b0.$VALUES.clone());
    }
}
