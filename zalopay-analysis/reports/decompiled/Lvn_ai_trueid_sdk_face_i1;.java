package vn.ai.trueid.sdk.face;
public final enum class i1 extends java.lang.Enum {
    private static final synthetic vn.ai.trueid.sdk.face.i1[] $VALUES;
    public static final enum vn.ai.trueid.sdk.face.i1 EXTERNAL;
    public static final enum vn.ai.trueid.sdk.face.i1 INTERNAL;

    private static final synthetic vn.ai.trueid.sdk.face.i1[] $values()
    {
        return new vn.ai.trueid.sdk.face.i1[] {vn.ai.trueid.sdk.face.i1.INTERNAL, vn.ai.trueid.sdk.face.i1.EXTERNAL});
    }

    public static i1()
    {
        vn.ai.trueid.sdk.face.i1.INTERNAL = new vn.ai.trueid.sdk.face.i1("INTERNAL", 0);
        vn.ai.trueid.sdk.face.i1.EXTERNAL = new vn.ai.trueid.sdk.face.i1("EXTERNAL", 1);
        vn.ai.trueid.sdk.face.i1.$VALUES = vn.ai.trueid.sdk.face.i1.$values();
        return;
    }

    private i1(String p1, int p2)
    {
        super(p1, p2);
        return;
    }

    public static vn.ai.trueid.sdk.face.i1 valueOf(String p1)
    {
        return ((vn.ai.trueid.sdk.face.i1) Enum.valueOf(vn.ai.trueid.sdk.face.i1, p1));
    }

    public static vn.ai.trueid.sdk.face.i1[] values()
    {
        return ((vn.ai.trueid.sdk.face.i1[]) vn.ai.trueid.sdk.face.i1.$VALUES.clone());
    }
}
