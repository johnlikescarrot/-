package vn.ai.trueid.lottie.model.content;
public final enum class ShapeStroke$LineCapType extends java.lang.Enum {
    private static final synthetic vn.ai.trueid.lottie.model.content.ShapeStroke$LineCapType[] $VALUES;
    public static final enum vn.ai.trueid.lottie.model.content.ShapeStroke$LineCapType BUTT;
    public static final enum vn.ai.trueid.lottie.model.content.ShapeStroke$LineCapType ROUND;
    public static final enum vn.ai.trueid.lottie.model.content.ShapeStroke$LineCapType UNKNOWN;

    private static synthetic vn.ai.trueid.lottie.model.content.ShapeStroke$LineCapType[] $values()
    {
        return new vn.ai.trueid.lottie.model.content.ShapeStroke$LineCapType[] {vn.ai.trueid.lottie.model.content.ShapeStroke$LineCapType.BUTT, vn.ai.trueid.lottie.model.content.ShapeStroke$LineCapType.ROUND, vn.ai.trueid.lottie.model.content.ShapeStroke$LineCapType.UNKNOWN});
    }

    static ShapeStroke$LineCapType()
    {
        vn.ai.trueid.lottie.model.content.ShapeStroke$LineCapType.BUTT = new vn.ai.trueid.lottie.model.content.ShapeStroke$LineCapType("BUTT", 0);
        vn.ai.trueid.lottie.model.content.ShapeStroke$LineCapType.ROUND = new vn.ai.trueid.lottie.model.content.ShapeStroke$LineCapType("ROUND", 1);
        vn.ai.trueid.lottie.model.content.ShapeStroke$LineCapType.UNKNOWN = new vn.ai.trueid.lottie.model.content.ShapeStroke$LineCapType("UNKNOWN", 2);
        vn.ai.trueid.lottie.model.content.ShapeStroke$LineCapType.$VALUES = vn.ai.trueid.lottie.model.content.ShapeStroke$LineCapType.$values();
        return;
    }

    private ShapeStroke$LineCapType(String p1, int p2)
    {
        super(p1, p2);
        return;
    }

    public static vn.ai.trueid.lottie.model.content.ShapeStroke$LineCapType valueOf(String p1)
    {
        return ((vn.ai.trueid.lottie.model.content.ShapeStroke$LineCapType) Enum.valueOf(vn.ai.trueid.lottie.model.content.ShapeStroke$LineCapType, p1));
    }

    public static vn.ai.trueid.lottie.model.content.ShapeStroke$LineCapType[] values()
    {
        return ((vn.ai.trueid.lottie.model.content.ShapeStroke$LineCapType[]) vn.ai.trueid.lottie.model.content.ShapeStroke$LineCapType.$VALUES.clone());
    }

    public android.graphics.Paint$Cap toPaintCap()
    {
        android.graphics.Paint$Cap v0_3 = vn.ai.trueid.lottie.model.content.ShapeStroke$1.$SwitchMap$com$airbnb$lottie$model$content$ShapeStroke$LineCapType[this.ordinal()];
        if (v0_3 == 1) {
            return android.graphics.Paint$Cap.BUTT;
        } else {
            if (v0_3 == 2) {
                return android.graphics.Paint$Cap.ROUND;
            } else {
                return android.graphics.Paint$Cap.SQUARE;
            }
        }
    }
}
