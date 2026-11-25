package vn.ai.trueid.lottie.model.content;
public final enum class ShapeTrimPath$Type extends java.lang.Enum {
    private static final synthetic vn.ai.trueid.lottie.model.content.ShapeTrimPath$Type[] $VALUES;
    public static final enum vn.ai.trueid.lottie.model.content.ShapeTrimPath$Type INDIVIDUALLY;
    public static final enum vn.ai.trueid.lottie.model.content.ShapeTrimPath$Type SIMULTANEOUSLY;

    private static synthetic vn.ai.trueid.lottie.model.content.ShapeTrimPath$Type[] $values()
    {
        return new vn.ai.trueid.lottie.model.content.ShapeTrimPath$Type[] {vn.ai.trueid.lottie.model.content.ShapeTrimPath$Type.SIMULTANEOUSLY, vn.ai.trueid.lottie.model.content.ShapeTrimPath$Type.INDIVIDUALLY});
    }

    static ShapeTrimPath$Type()
    {
        vn.ai.trueid.lottie.model.content.ShapeTrimPath$Type.SIMULTANEOUSLY = new vn.ai.trueid.lottie.model.content.ShapeTrimPath$Type("SIMULTANEOUSLY", 0);
        vn.ai.trueid.lottie.model.content.ShapeTrimPath$Type.INDIVIDUALLY = new vn.ai.trueid.lottie.model.content.ShapeTrimPath$Type("INDIVIDUALLY", 1);
        vn.ai.trueid.lottie.model.content.ShapeTrimPath$Type.$VALUES = vn.ai.trueid.lottie.model.content.ShapeTrimPath$Type.$values();
        return;
    }

    private ShapeTrimPath$Type(String p1, int p2)
    {
        super(p1, p2);
        return;
    }

    public static vn.ai.trueid.lottie.model.content.ShapeTrimPath$Type forId(int p2)
    {
        if (p2 == 1) {
            return vn.ai.trueid.lottie.model.content.ShapeTrimPath$Type.SIMULTANEOUSLY;
        } else {
            if (p2 != 2) {
                throw new IllegalArgumentException(androidx.activity.result.e.g(p2, "Unknown trim path type "));
            } else {
                return vn.ai.trueid.lottie.model.content.ShapeTrimPath$Type.INDIVIDUALLY;
            }
        }
    }

    public static vn.ai.trueid.lottie.model.content.ShapeTrimPath$Type valueOf(String p1)
    {
        return ((vn.ai.trueid.lottie.model.content.ShapeTrimPath$Type) Enum.valueOf(vn.ai.trueid.lottie.model.content.ShapeTrimPath$Type, p1));
    }

    public static vn.ai.trueid.lottie.model.content.ShapeTrimPath$Type[] values()
    {
        return ((vn.ai.trueid.lottie.model.content.ShapeTrimPath$Type[]) vn.ai.trueid.lottie.model.content.ShapeTrimPath$Type.$VALUES.clone());
    }
}
