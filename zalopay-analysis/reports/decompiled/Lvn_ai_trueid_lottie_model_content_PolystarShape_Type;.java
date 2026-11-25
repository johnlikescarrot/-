package vn.ai.trueid.lottie.model.content;
public final enum class PolystarShape$Type extends java.lang.Enum {
    private static final synthetic vn.ai.trueid.lottie.model.content.PolystarShape$Type[] $VALUES;
    public static final enum vn.ai.trueid.lottie.model.content.PolystarShape$Type POLYGON;
    public static final enum vn.ai.trueid.lottie.model.content.PolystarShape$Type STAR;
    private final int value;

    private static synthetic vn.ai.trueid.lottie.model.content.PolystarShape$Type[] $values()
    {
        return new vn.ai.trueid.lottie.model.content.PolystarShape$Type[] {vn.ai.trueid.lottie.model.content.PolystarShape$Type.STAR, vn.ai.trueid.lottie.model.content.PolystarShape$Type.POLYGON});
    }

    static PolystarShape$Type()
    {
        vn.ai.trueid.lottie.model.content.PolystarShape$Type.STAR = new vn.ai.trueid.lottie.model.content.PolystarShape$Type("STAR", 0, 1);
        vn.ai.trueid.lottie.model.content.PolystarShape$Type.POLYGON = new vn.ai.trueid.lottie.model.content.PolystarShape$Type("POLYGON", 1, 2);
        vn.ai.trueid.lottie.model.content.PolystarShape$Type.$VALUES = vn.ai.trueid.lottie.model.content.PolystarShape$Type.$values();
        return;
    }

    private PolystarShape$Type(String p1, int p2, int p3)
    {
        super(p1, p2);
        super.value = p3;
        return;
    }

    public static vn.ai.trueid.lottie.model.content.PolystarShape$Type forValue(int p5)
    {
        vn.ai.trueid.lottie.model.content.PolystarShape$Type[] v0 = vn.ai.trueid.lottie.model.content.PolystarShape$Type.values();
        int v2 = 0;
        while (v2 < v0.length) {
            vn.ai.trueid.lottie.model.content.PolystarShape$Type v3 = v0[v2];
            if (v3.value != p5) {
                v2++;
            } else {
                return v3;
            }
        }
        return 0;
    }

    public static vn.ai.trueid.lottie.model.content.PolystarShape$Type valueOf(String p1)
    {
        return ((vn.ai.trueid.lottie.model.content.PolystarShape$Type) Enum.valueOf(vn.ai.trueid.lottie.model.content.PolystarShape$Type, p1));
    }

    public static vn.ai.trueid.lottie.model.content.PolystarShape$Type[] values()
    {
        return ((vn.ai.trueid.lottie.model.content.PolystarShape$Type[]) vn.ai.trueid.lottie.model.content.PolystarShape$Type.$VALUES.clone());
    }
}
