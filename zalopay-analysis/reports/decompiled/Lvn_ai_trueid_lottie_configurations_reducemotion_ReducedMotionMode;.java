package vn.ai.trueid.lottie.configurations.reducemotion;
public final enum class ReducedMotionMode extends java.lang.Enum {
    private static final synthetic vn.ai.trueid.lottie.configurations.reducemotion.ReducedMotionMode[] $VALUES;
    public static final enum vn.ai.trueid.lottie.configurations.reducemotion.ReducedMotionMode REDUCED_MOTION;
    public static final enum vn.ai.trueid.lottie.configurations.reducemotion.ReducedMotionMode STANDARD_MOTION;

    private static synthetic vn.ai.trueid.lottie.configurations.reducemotion.ReducedMotionMode[] $values()
    {
        return new vn.ai.trueid.lottie.configurations.reducemotion.ReducedMotionMode[] {vn.ai.trueid.lottie.configurations.reducemotion.ReducedMotionMode.STANDARD_MOTION, vn.ai.trueid.lottie.configurations.reducemotion.ReducedMotionMode.REDUCED_MOTION});
    }

    static ReducedMotionMode()
    {
        vn.ai.trueid.lottie.configurations.reducemotion.ReducedMotionMode.STANDARD_MOTION = new vn.ai.trueid.lottie.configurations.reducemotion.ReducedMotionMode("STANDARD_MOTION", 0);
        vn.ai.trueid.lottie.configurations.reducemotion.ReducedMotionMode.REDUCED_MOTION = new vn.ai.trueid.lottie.configurations.reducemotion.ReducedMotionMode("REDUCED_MOTION", 1);
        vn.ai.trueid.lottie.configurations.reducemotion.ReducedMotionMode.$VALUES = vn.ai.trueid.lottie.configurations.reducemotion.ReducedMotionMode.$values();
        return;
    }

    private ReducedMotionMode(String p1, int p2)
    {
        super(p1, p2);
        return;
    }

    public static vn.ai.trueid.lottie.configurations.reducemotion.ReducedMotionMode valueOf(String p1)
    {
        return ((vn.ai.trueid.lottie.configurations.reducemotion.ReducedMotionMode) Enum.valueOf(vn.ai.trueid.lottie.configurations.reducemotion.ReducedMotionMode, p1));
    }

    public static vn.ai.trueid.lottie.configurations.reducemotion.ReducedMotionMode[] values()
    {
        return ((vn.ai.trueid.lottie.configurations.reducemotion.ReducedMotionMode[]) vn.ai.trueid.lottie.configurations.reducemotion.ReducedMotionMode.$VALUES.clone());
    }
}
