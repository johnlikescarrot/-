package vn.ai.trueid.sdk.face;
public final enum class i extends java.lang.Enum {
    private static final synthetic vn.ai.trueid.sdk.face.i[] $VALUES;
    public static final enum vn.ai.trueid.sdk.face.i LEFT;
    public static final enum vn.ai.trueid.sdk.face.i NONE;
    public static final enum vn.ai.trueid.sdk.face.i RIGHT;

    private static final synthetic vn.ai.trueid.sdk.face.i[] $values()
    {
        return new vn.ai.trueid.sdk.face.i[] {vn.ai.trueid.sdk.face.i.LEFT, vn.ai.trueid.sdk.face.i.RIGHT, vn.ai.trueid.sdk.face.i.NONE});
    }

    public static i()
    {
        vn.ai.trueid.sdk.face.i.LEFT = new vn.ai.trueid.sdk.face.i(wmoi.igob.ggcn(14982), 0);
        vn.ai.trueid.sdk.face.i.RIGHT = new vn.ai.trueid.sdk.face.i(wmoi.igob.ggcn(14983), 1);
        vn.ai.trueid.sdk.face.i.NONE = new vn.ai.trueid.sdk.face.i(wmoi.igob.ggcn(14984), 2);
        vn.ai.trueid.sdk.face.i.$VALUES = vn.ai.trueid.sdk.face.i.$values();
        return;
    }

    private i(String p1, int p2)
    {
        super(p1, p2);
        return;
    }

    public static vn.ai.trueid.sdk.face.i valueOf(String p1)
    {
        return ((vn.ai.trueid.sdk.face.i) Enum.valueOf(vn.ai.trueid.sdk.face.i, p1));
    }

    public static vn.ai.trueid.sdk.face.i[] values()
    {
        return ((vn.ai.trueid.sdk.face.i[]) vn.ai.trueid.sdk.face.i.$VALUES.clone());
    }
}
