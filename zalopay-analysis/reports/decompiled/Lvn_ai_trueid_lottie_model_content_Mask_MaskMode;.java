package vn.ai.trueid.lottie.model.content;
public final enum class Mask$MaskMode extends java.lang.Enum {
    private static final synthetic vn.ai.trueid.lottie.model.content.Mask$MaskMode[] $VALUES;
    public static final enum vn.ai.trueid.lottie.model.content.Mask$MaskMode MASK_MODE_ADD;
    public static final enum vn.ai.trueid.lottie.model.content.Mask$MaskMode MASK_MODE_INTERSECT;
    public static final enum vn.ai.trueid.lottie.model.content.Mask$MaskMode MASK_MODE_NONE;
    public static final enum vn.ai.trueid.lottie.model.content.Mask$MaskMode MASK_MODE_SUBTRACT;

    private static synthetic vn.ai.trueid.lottie.model.content.Mask$MaskMode[] $values()
    {
        return new vn.ai.trueid.lottie.model.content.Mask$MaskMode[] {vn.ai.trueid.lottie.model.content.Mask$MaskMode.MASK_MODE_ADD, vn.ai.trueid.lottie.model.content.Mask$MaskMode.MASK_MODE_SUBTRACT, vn.ai.trueid.lottie.model.content.Mask$MaskMode.MASK_MODE_INTERSECT, vn.ai.trueid.lottie.model.content.Mask$MaskMode.MASK_MODE_NONE});
    }

    static Mask$MaskMode()
    {
        vn.ai.trueid.lottie.model.content.Mask$MaskMode.MASK_MODE_ADD = new vn.ai.trueid.lottie.model.content.Mask$MaskMode(wmoi.igob.ggcn(9861), 0);
        vn.ai.trueid.lottie.model.content.Mask$MaskMode.MASK_MODE_SUBTRACT = new vn.ai.trueid.lottie.model.content.Mask$MaskMode(wmoi.igob.ggcn(9862), 1);
        vn.ai.trueid.lottie.model.content.Mask$MaskMode.MASK_MODE_INTERSECT = new vn.ai.trueid.lottie.model.content.Mask$MaskMode(wmoi.igob.ggcn(9863), 2);
        vn.ai.trueid.lottie.model.content.Mask$MaskMode.MASK_MODE_NONE = new vn.ai.trueid.lottie.model.content.Mask$MaskMode(wmoi.igob.ggcn(9864), 3);
        vn.ai.trueid.lottie.model.content.Mask$MaskMode.$VALUES = vn.ai.trueid.lottie.model.content.Mask$MaskMode.$values();
        return;
    }

    private Mask$MaskMode(String p1, int p2)
    {
        super(p1, p2);
        return;
    }

    public static vn.ai.trueid.lottie.model.content.Mask$MaskMode valueOf(String p1)
    {
        return ((vn.ai.trueid.lottie.model.content.Mask$MaskMode) Enum.valueOf(vn.ai.trueid.lottie.model.content.Mask$MaskMode, p1));
    }

    public static vn.ai.trueid.lottie.model.content.Mask$MaskMode[] values()
    {
        return ((vn.ai.trueid.lottie.model.content.Mask$MaskMode[]) vn.ai.trueid.lottie.model.content.Mask$MaskMode.$VALUES.clone());
    }
}
