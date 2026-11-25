package vn.ai.trueid.lottie.model.content;
public final enum class ShapeStroke$LineJoinType extends java.lang.Enum {
    private static final synthetic vn.ai.trueid.lottie.model.content.ShapeStroke$LineJoinType[] $VALUES;
    public static final enum vn.ai.trueid.lottie.model.content.ShapeStroke$LineJoinType BEVEL;
    public static final enum vn.ai.trueid.lottie.model.content.ShapeStroke$LineJoinType MITER;
    public static final enum vn.ai.trueid.lottie.model.content.ShapeStroke$LineJoinType ROUND;

    private static synthetic vn.ai.trueid.lottie.model.content.ShapeStroke$LineJoinType[] $values()
    {
        return new vn.ai.trueid.lottie.model.content.ShapeStroke$LineJoinType[] {vn.ai.trueid.lottie.model.content.ShapeStroke$LineJoinType.MITER, vn.ai.trueid.lottie.model.content.ShapeStroke$LineJoinType.ROUND, vn.ai.trueid.lottie.model.content.ShapeStroke$LineJoinType.BEVEL});
    }

    static ShapeStroke$LineJoinType()
    {
        vn.ai.trueid.lottie.model.content.ShapeStroke$LineJoinType.MITER = new vn.ai.trueid.lottie.model.content.ShapeStroke$LineJoinType("MITER", 0);
        vn.ai.trueid.lottie.model.content.ShapeStroke$LineJoinType.ROUND = new vn.ai.trueid.lottie.model.content.ShapeStroke$LineJoinType("ROUND", 1);
        vn.ai.trueid.lottie.model.content.ShapeStroke$LineJoinType.BEVEL = new vn.ai.trueid.lottie.model.content.ShapeStroke$LineJoinType("BEVEL", 2);
        vn.ai.trueid.lottie.model.content.ShapeStroke$LineJoinType.$VALUES = vn.ai.trueid.lottie.model.content.ShapeStroke$LineJoinType.$values();
        return;
    }

    private ShapeStroke$LineJoinType(String p1, int p2)
    {
        super(p1, p2);
        return;
    }

    public static vn.ai.trueid.lottie.model.content.ShapeStroke$LineJoinType valueOf(String p1)
    {
        return ((vn.ai.trueid.lottie.model.content.ShapeStroke$LineJoinType) Enum.valueOf(vn.ai.trueid.lottie.model.content.ShapeStroke$LineJoinType, p1));
    }

    public static vn.ai.trueid.lottie.model.content.ShapeStroke$LineJoinType[] values()
    {
        return ((vn.ai.trueid.lottie.model.content.ShapeStroke$LineJoinType[]) vn.ai.trueid.lottie.model.content.ShapeStroke$LineJoinType.$VALUES.clone());
    }

    public android.graphics.Paint$Join toPaintJoin()
    {
        android.graphics.Paint$Join v0_4 = vn.ai.trueid.lottie.model.content.ShapeStroke$1.$SwitchMap$com$airbnb$lottie$model$content$ShapeStroke$LineJoinType[this.ordinal()];
        if (v0_4 == 1) {
            return android.graphics.Paint$Join.BEVEL;
        } else {
            if (v0_4 == 2) {
                return android.graphics.Paint$Join.MITER;
            } else {
                if (v0_4 == 3) {
                    return android.graphics.Paint$Join.ROUND;
                } else {
                    return 0;
                }
            }
        }
    }
}
