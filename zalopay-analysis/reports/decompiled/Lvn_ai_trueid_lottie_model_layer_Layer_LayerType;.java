package vn.ai.trueid.lottie.model.layer;
public final enum class Layer$LayerType extends java.lang.Enum {
    private static final synthetic vn.ai.trueid.lottie.model.layer.Layer$LayerType[] $VALUES;
    public static final enum vn.ai.trueid.lottie.model.layer.Layer$LayerType IMAGE;
    public static final enum vn.ai.trueid.lottie.model.layer.Layer$LayerType NULL;
    public static final enum vn.ai.trueid.lottie.model.layer.Layer$LayerType PRE_COMP;
    public static final enum vn.ai.trueid.lottie.model.layer.Layer$LayerType SHAPE;
    public static final enum vn.ai.trueid.lottie.model.layer.Layer$LayerType SOLID;
    public static final enum vn.ai.trueid.lottie.model.layer.Layer$LayerType TEXT;
    public static final enum vn.ai.trueid.lottie.model.layer.Layer$LayerType UNKNOWN;

    private static synthetic vn.ai.trueid.lottie.model.layer.Layer$LayerType[] $values()
    {
        vn.ai.trueid.lottie.model.layer.Layer$LayerType v2 = vn.ai.trueid.lottie.model.layer.Layer$LayerType.IMAGE;
        vn.ai.trueid.lottie.model.layer.Layer$LayerType v4 = vn.ai.trueid.lottie.model.layer.Layer$LayerType.SHAPE;
        return new vn.ai.trueid.lottie.model.layer.Layer$LayerType[] {vn.ai.trueid.lottie.model.layer.Layer$LayerType.PRE_COMP, vn.ai.trueid.lottie.model.layer.Layer$LayerType.UNKNOWN});
    }

    static Layer$LayerType()
    {
        vn.ai.trueid.lottie.model.layer.Layer$LayerType.PRE_COMP = new vn.ai.trueid.lottie.model.layer.Layer$LayerType("PRE_COMP", 0);
        vn.ai.trueid.lottie.model.layer.Layer$LayerType.SOLID = new vn.ai.trueid.lottie.model.layer.Layer$LayerType("SOLID", 1);
        vn.ai.trueid.lottie.model.layer.Layer$LayerType.IMAGE = new vn.ai.trueid.lottie.model.layer.Layer$LayerType("IMAGE", 2);
        vn.ai.trueid.lottie.model.layer.Layer$LayerType.NULL = new vn.ai.trueid.lottie.model.layer.Layer$LayerType("NULL", 3);
        vn.ai.trueid.lottie.model.layer.Layer$LayerType.SHAPE = new vn.ai.trueid.lottie.model.layer.Layer$LayerType("SHAPE", 4);
        vn.ai.trueid.lottie.model.layer.Layer$LayerType.TEXT = new vn.ai.trueid.lottie.model.layer.Layer$LayerType("TEXT", 5);
        vn.ai.trueid.lottie.model.layer.Layer$LayerType.UNKNOWN = new vn.ai.trueid.lottie.model.layer.Layer$LayerType("UNKNOWN", 6);
        vn.ai.trueid.lottie.model.layer.Layer$LayerType.$VALUES = vn.ai.trueid.lottie.model.layer.Layer$LayerType.$values();
        return;
    }

    private Layer$LayerType(String p1, int p2)
    {
        super(p1, p2);
        return;
    }

    public static vn.ai.trueid.lottie.model.layer.Layer$LayerType valueOf(String p1)
    {
        return ((vn.ai.trueid.lottie.model.layer.Layer$LayerType) Enum.valueOf(vn.ai.trueid.lottie.model.layer.Layer$LayerType, p1));
    }

    public static vn.ai.trueid.lottie.model.layer.Layer$LayerType[] values()
    {
        return ((vn.ai.trueid.lottie.model.layer.Layer$LayerType[]) vn.ai.trueid.lottie.model.layer.Layer$LayerType.$VALUES.clone());
    }
}
