package vn.ai.trueid.lottie;
final enum class LottieAnimationView$UserActionTaken extends java.lang.Enum {
    private static final synthetic vn.ai.trueid.lottie.LottieAnimationView$UserActionTaken[] $VALUES;
    public static final enum vn.ai.trueid.lottie.LottieAnimationView$UserActionTaken PLAY_OPTION;
    public static final enum vn.ai.trueid.lottie.LottieAnimationView$UserActionTaken SET_ANIMATION;
    public static final enum vn.ai.trueid.lottie.LottieAnimationView$UserActionTaken SET_IMAGE_ASSETS;
    public static final enum vn.ai.trueid.lottie.LottieAnimationView$UserActionTaken SET_PROGRESS;
    public static final enum vn.ai.trueid.lottie.LottieAnimationView$UserActionTaken SET_REPEAT_COUNT;
    public static final enum vn.ai.trueid.lottie.LottieAnimationView$UserActionTaken SET_REPEAT_MODE;

    private static synthetic vn.ai.trueid.lottie.LottieAnimationView$UserActionTaken[] $values()
    {
        vn.ai.trueid.lottie.LottieAnimationView$UserActionTaken v2 = vn.ai.trueid.lottie.LottieAnimationView$UserActionTaken.SET_REPEAT_MODE;
        vn.ai.trueid.lottie.LottieAnimationView$UserActionTaken v4 = vn.ai.trueid.lottie.LottieAnimationView$UserActionTaken.SET_IMAGE_ASSETS;
        return new vn.ai.trueid.lottie.LottieAnimationView$UserActionTaken[] {vn.ai.trueid.lottie.LottieAnimationView$UserActionTaken.SET_ANIMATION, vn.ai.trueid.lottie.LottieAnimationView$UserActionTaken.PLAY_OPTION});
    }

    static LottieAnimationView$UserActionTaken()
    {
        vn.ai.trueid.lottie.LottieAnimationView$UserActionTaken.SET_ANIMATION = new vn.ai.trueid.lottie.LottieAnimationView$UserActionTaken("SET_ANIMATION", 0);
        vn.ai.trueid.lottie.LottieAnimationView$UserActionTaken.SET_PROGRESS = new vn.ai.trueid.lottie.LottieAnimationView$UserActionTaken("SET_PROGRESS", 1);
        vn.ai.trueid.lottie.LottieAnimationView$UserActionTaken.SET_REPEAT_MODE = new vn.ai.trueid.lottie.LottieAnimationView$UserActionTaken("SET_REPEAT_MODE", 2);
        vn.ai.trueid.lottie.LottieAnimationView$UserActionTaken.SET_REPEAT_COUNT = new vn.ai.trueid.lottie.LottieAnimationView$UserActionTaken("SET_REPEAT_COUNT", 3);
        vn.ai.trueid.lottie.LottieAnimationView$UserActionTaken.SET_IMAGE_ASSETS = new vn.ai.trueid.lottie.LottieAnimationView$UserActionTaken("SET_IMAGE_ASSETS", 4);
        vn.ai.trueid.lottie.LottieAnimationView$UserActionTaken.PLAY_OPTION = new vn.ai.trueid.lottie.LottieAnimationView$UserActionTaken("PLAY_OPTION", 5);
        vn.ai.trueid.lottie.LottieAnimationView$UserActionTaken.$VALUES = vn.ai.trueid.lottie.LottieAnimationView$UserActionTaken.$values();
        return;
    }

    private LottieAnimationView$UserActionTaken(String p1, int p2)
    {
        super(p1, p2);
        return;
    }

    public static vn.ai.trueid.lottie.LottieAnimationView$UserActionTaken valueOf(String p1)
    {
        return ((vn.ai.trueid.lottie.LottieAnimationView$UserActionTaken) Enum.valueOf(vn.ai.trueid.lottie.LottieAnimationView$UserActionTaken, p1));
    }

    public static vn.ai.trueid.lottie.LottieAnimationView$UserActionTaken[] values()
    {
        return ((vn.ai.trueid.lottie.LottieAnimationView$UserActionTaken[]) vn.ai.trueid.lottie.LottieAnimationView$UserActionTaken.$VALUES.clone());
    }
}
