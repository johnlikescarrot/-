package vn.ai.trueid.sdk.face;
public final enum class b2 extends java.lang.Enum {
    private static final synthetic vn.ai.trueid.sdk.face.b2[] $VALUES;
    public static final enum vn.ai.trueid.sdk.face.b2 IN;
    public static final enum vn.ai.trueid.sdk.face.b2 OUT;

    private static final synthetic vn.ai.trueid.sdk.face.b2[] $values()
    {
        return new vn.ai.trueid.sdk.face.b2[] {vn.ai.trueid.sdk.face.b2.IN, vn.ai.trueid.sdk.face.b2.OUT});
    }

    public static b2()
    {
        vn.ai.trueid.sdk.face.b2.IN = new vn.ai.trueid.sdk.face.b2("IN", 0);
        vn.ai.trueid.sdk.face.b2.OUT = new vn.ai.trueid.sdk.face.b2("OUT", 1);
        vn.ai.trueid.sdk.face.b2.$VALUES = vn.ai.trueid.sdk.face.b2.$values();
        return;
    }

    private b2(String p1, int p2)
    {
        super(p1, p2);
        return;
    }

    public static vn.ai.trueid.sdk.face.b2 valueOf(String p1)
    {
        return ((vn.ai.trueid.sdk.face.b2) Enum.valueOf(vn.ai.trueid.sdk.face.b2, p1));
    }

    public static vn.ai.trueid.sdk.face.b2[] values()
    {
        return ((vn.ai.trueid.sdk.face.b2[]) vn.ai.trueid.sdk.face.b2.$VALUES.clone());
    }
}
