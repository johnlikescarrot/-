package vn.ai.trueid.lottie.model;
public final enum class DocumentData$Justification extends java.lang.Enum {
    private static final synthetic vn.ai.trueid.lottie.model.DocumentData$Justification[] $VALUES;
    public static final enum vn.ai.trueid.lottie.model.DocumentData$Justification CENTER;
    public static final enum vn.ai.trueid.lottie.model.DocumentData$Justification LEFT_ALIGN;
    public static final enum vn.ai.trueid.lottie.model.DocumentData$Justification RIGHT_ALIGN;

    private static synthetic vn.ai.trueid.lottie.model.DocumentData$Justification[] $values()
    {
        return new vn.ai.trueid.lottie.model.DocumentData$Justification[] {vn.ai.trueid.lottie.model.DocumentData$Justification.LEFT_ALIGN, vn.ai.trueid.lottie.model.DocumentData$Justification.RIGHT_ALIGN, vn.ai.trueid.lottie.model.DocumentData$Justification.CENTER});
    }

    static DocumentData$Justification()
    {
        vn.ai.trueid.lottie.model.DocumentData$Justification.LEFT_ALIGN = new vn.ai.trueid.lottie.model.DocumentData$Justification("LEFT_ALIGN", 0);
        vn.ai.trueid.lottie.model.DocumentData$Justification.RIGHT_ALIGN = new vn.ai.trueid.lottie.model.DocumentData$Justification("RIGHT_ALIGN", 1);
        vn.ai.trueid.lottie.model.DocumentData$Justification.CENTER = new vn.ai.trueid.lottie.model.DocumentData$Justification("CENTER", 2);
        vn.ai.trueid.lottie.model.DocumentData$Justification.$VALUES = vn.ai.trueid.lottie.model.DocumentData$Justification.$values();
        return;
    }

    private DocumentData$Justification(String p1, int p2)
    {
        super(p1, p2);
        return;
    }

    public static vn.ai.trueid.lottie.model.DocumentData$Justification valueOf(String p1)
    {
        return ((vn.ai.trueid.lottie.model.DocumentData$Justification) Enum.valueOf(vn.ai.trueid.lottie.model.DocumentData$Justification, p1));
    }

    public static vn.ai.trueid.lottie.model.DocumentData$Justification[] values()
    {
        return ((vn.ai.trueid.lottie.model.DocumentData$Justification[]) vn.ai.trueid.lottie.model.DocumentData$Justification.$VALUES.clone());
    }
}
