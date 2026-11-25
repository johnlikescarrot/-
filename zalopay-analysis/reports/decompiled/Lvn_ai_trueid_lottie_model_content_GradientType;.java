package vn.ai.trueid.lottie.model.content;
public final enum class GradientType extends java.lang.Enum {
    private static final synthetic vn.ai.trueid.lottie.model.content.GradientType[] $VALUES;
    public static final enum vn.ai.trueid.lottie.model.content.GradientType LINEAR;
    public static final enum vn.ai.trueid.lottie.model.content.GradientType RADIAL;

    private static synthetic vn.ai.trueid.lottie.model.content.GradientType[] $values()
    {
        return new vn.ai.trueid.lottie.model.content.GradientType[] {vn.ai.trueid.lottie.model.content.GradientType.LINEAR, vn.ai.trueid.lottie.model.content.GradientType.RADIAL});
    }

    static GradientType()
    {
        vn.ai.trueid.lottie.model.content.GradientType.LINEAR = new vn.ai.trueid.lottie.model.content.GradientType("LINEAR", 0);
        vn.ai.trueid.lottie.model.content.GradientType.RADIAL = new vn.ai.trueid.lottie.model.content.GradientType("RADIAL", 1);
        vn.ai.trueid.lottie.model.content.GradientType.$VALUES = vn.ai.trueid.lottie.model.content.GradientType.$values();
        return;
    }

    private GradientType(String p1, int p2)
    {
        super(p1, p2);
        return;
    }

    public static vn.ai.trueid.lottie.model.content.GradientType valueOf(String p1)
    {
        return ((vn.ai.trueid.lottie.model.content.GradientType) Enum.valueOf(vn.ai.trueid.lottie.model.content.GradientType, p1));
    }

    public static vn.ai.trueid.lottie.model.content.GradientType[] values()
    {
        return ((vn.ai.trueid.lottie.model.content.GradientType[]) vn.ai.trueid.lottie.model.content.GradientType.$VALUES.clone());
    }
}
