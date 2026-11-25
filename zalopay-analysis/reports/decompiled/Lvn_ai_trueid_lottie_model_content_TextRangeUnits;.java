package vn.ai.trueid.lottie.model.content;
public final enum class TextRangeUnits extends java.lang.Enum {
    private static final synthetic vn.ai.trueid.lottie.model.content.TextRangeUnits[] $VALUES;
    public static final enum vn.ai.trueid.lottie.model.content.TextRangeUnits INDEX;
    public static final enum vn.ai.trueid.lottie.model.content.TextRangeUnits PERCENT;

    private static synthetic vn.ai.trueid.lottie.model.content.TextRangeUnits[] $values()
    {
        return new vn.ai.trueid.lottie.model.content.TextRangeUnits[] {vn.ai.trueid.lottie.model.content.TextRangeUnits.PERCENT, vn.ai.trueid.lottie.model.content.TextRangeUnits.INDEX});
    }

    static TextRangeUnits()
    {
        vn.ai.trueid.lottie.model.content.TextRangeUnits.PERCENT = new vn.ai.trueid.lottie.model.content.TextRangeUnits("PERCENT", 0);
        vn.ai.trueid.lottie.model.content.TextRangeUnits.INDEX = new vn.ai.trueid.lottie.model.content.TextRangeUnits("INDEX", 1);
        vn.ai.trueid.lottie.model.content.TextRangeUnits.$VALUES = vn.ai.trueid.lottie.model.content.TextRangeUnits.$values();
        return;
    }

    private TextRangeUnits(String p1, int p2)
    {
        super(p1, p2);
        return;
    }

    public static vn.ai.trueid.lottie.model.content.TextRangeUnits valueOf(String p1)
    {
        return ((vn.ai.trueid.lottie.model.content.TextRangeUnits) Enum.valueOf(vn.ai.trueid.lottie.model.content.TextRangeUnits, p1));
    }

    public static vn.ai.trueid.lottie.model.content.TextRangeUnits[] values()
    {
        return ((vn.ai.trueid.lottie.model.content.TextRangeUnits[]) vn.ai.trueid.lottie.model.content.TextRangeUnits.$VALUES.clone());
    }
}
