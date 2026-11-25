package vn.ai.trueid.lottie.model.layer;
public final enum class Layer$MatteType extends java.lang.Enum {
    private static final synthetic vn.ai.trueid.lottie.model.layer.Layer$MatteType[] $VALUES;
    public static final enum vn.ai.trueid.lottie.model.layer.Layer$MatteType ADD;
    public static final enum vn.ai.trueid.lottie.model.layer.Layer$MatteType INVERT;
    public static final enum vn.ai.trueid.lottie.model.layer.Layer$MatteType LUMA;
    public static final enum vn.ai.trueid.lottie.model.layer.Layer$MatteType LUMA_INVERTED;
    public static final enum vn.ai.trueid.lottie.model.layer.Layer$MatteType NONE;
    public static final enum vn.ai.trueid.lottie.model.layer.Layer$MatteType UNKNOWN;

    private static synthetic vn.ai.trueid.lottie.model.layer.Layer$MatteType[] $values()
    {
        vn.ai.trueid.lottie.model.layer.Layer$MatteType v2 = vn.ai.trueid.lottie.model.layer.Layer$MatteType.INVERT;
        vn.ai.trueid.lottie.model.layer.Layer$MatteType v4 = vn.ai.trueid.lottie.model.layer.Layer$MatteType.LUMA_INVERTED;
        return new vn.ai.trueid.lottie.model.layer.Layer$MatteType[] {vn.ai.trueid.lottie.model.layer.Layer$MatteType.NONE, vn.ai.trueid.lottie.model.layer.Layer$MatteType.UNKNOWN});
    }

    static Layer$MatteType()
    {
        vn.ai.trueid.lottie.model.layer.Layer$MatteType.NONE = new vn.ai.trueid.lottie.model.layer.Layer$MatteType("NONE", 0);
        vn.ai.trueid.lottie.model.layer.Layer$MatteType.ADD = new vn.ai.trueid.lottie.model.layer.Layer$MatteType("ADD", 1);
        vn.ai.trueid.lottie.model.layer.Layer$MatteType.INVERT = new vn.ai.trueid.lottie.model.layer.Layer$MatteType("INVERT", 2);
        vn.ai.trueid.lottie.model.layer.Layer$MatteType.LUMA = new vn.ai.trueid.lottie.model.layer.Layer$MatteType("LUMA", 3);
        vn.ai.trueid.lottie.model.layer.Layer$MatteType.LUMA_INVERTED = new vn.ai.trueid.lottie.model.layer.Layer$MatteType("LUMA_INVERTED", 4);
        vn.ai.trueid.lottie.model.layer.Layer$MatteType.UNKNOWN = new vn.ai.trueid.lottie.model.layer.Layer$MatteType("UNKNOWN", 5);
        vn.ai.trueid.lottie.model.layer.Layer$MatteType.$VALUES = vn.ai.trueid.lottie.model.layer.Layer$MatteType.$values();
        return;
    }

    private Layer$MatteType(String p1, int p2)
    {
        super(p1, p2);
        return;
    }

    public static vn.ai.trueid.lottie.model.layer.Layer$MatteType valueOf(String p1)
    {
        return ((vn.ai.trueid.lottie.model.layer.Layer$MatteType) Enum.valueOf(vn.ai.trueid.lottie.model.layer.Layer$MatteType, p1));
    }

    public static vn.ai.trueid.lottie.model.layer.Layer$MatteType[] values()
    {
        return ((vn.ai.trueid.lottie.model.layer.Layer$MatteType[]) vn.ai.trueid.lottie.model.layer.Layer$MatteType.$VALUES.clone());
    }
}
