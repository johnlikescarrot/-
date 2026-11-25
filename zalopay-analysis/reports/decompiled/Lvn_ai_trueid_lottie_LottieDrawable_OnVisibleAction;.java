package vn.ai.trueid.lottie;
final enum class LottieDrawable$OnVisibleAction extends java.lang.Enum {
    private static final synthetic vn.ai.trueid.lottie.LottieDrawable$OnVisibleAction[] $VALUES;
    public static final enum vn.ai.trueid.lottie.LottieDrawable$OnVisibleAction NONE;
    public static final enum vn.ai.trueid.lottie.LottieDrawable$OnVisibleAction PLAY;
    public static final enum vn.ai.trueid.lottie.LottieDrawable$OnVisibleAction RESUME;

    private static synthetic vn.ai.trueid.lottie.LottieDrawable$OnVisibleAction[] $values()
    {
        return new vn.ai.trueid.lottie.LottieDrawable$OnVisibleAction[] {vn.ai.trueid.lottie.LottieDrawable$OnVisibleAction.NONE, vn.ai.trueid.lottie.LottieDrawable$OnVisibleAction.PLAY, vn.ai.trueid.lottie.LottieDrawable$OnVisibleAction.RESUME});
    }

    static LottieDrawable$OnVisibleAction()
    {
        vn.ai.trueid.lottie.LottieDrawable$OnVisibleAction.NONE = new vn.ai.trueid.lottie.LottieDrawable$OnVisibleAction("NONE", 0);
        vn.ai.trueid.lottie.LottieDrawable$OnVisibleAction.PLAY = new vn.ai.trueid.lottie.LottieDrawable$OnVisibleAction("PLAY", 1);
        vn.ai.trueid.lottie.LottieDrawable$OnVisibleAction.RESUME = new vn.ai.trueid.lottie.LottieDrawable$OnVisibleAction("RESUME", 2);
        vn.ai.trueid.lottie.LottieDrawable$OnVisibleAction.$VALUES = vn.ai.trueid.lottie.LottieDrawable$OnVisibleAction.$values();
        return;
    }

    private LottieDrawable$OnVisibleAction(String p1, int p2)
    {
        super(p1, p2);
        return;
    }

    public static vn.ai.trueid.lottie.LottieDrawable$OnVisibleAction valueOf(String p1)
    {
        return ((vn.ai.trueid.lottie.LottieDrawable$OnVisibleAction) Enum.valueOf(vn.ai.trueid.lottie.LottieDrawable$OnVisibleAction, p1));
    }

    public static vn.ai.trueid.lottie.LottieDrawable$OnVisibleAction[] values()
    {
        return ((vn.ai.trueid.lottie.LottieDrawable$OnVisibleAction[]) vn.ai.trueid.lottie.LottieDrawable$OnVisibleAction.$VALUES.clone());
    }
}
