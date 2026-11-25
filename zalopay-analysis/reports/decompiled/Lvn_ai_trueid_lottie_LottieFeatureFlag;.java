package vn.ai.trueid.lottie;
public final enum class LottieFeatureFlag extends java.lang.Enum {
    private static final synthetic vn.ai.trueid.lottie.LottieFeatureFlag[] $VALUES;
    public static final enum vn.ai.trueid.lottie.LottieFeatureFlag MergePathsApi19;
    public final int minRequiredSdkVersion;

    private static synthetic vn.ai.trueid.lottie.LottieFeatureFlag[] $values()
    {
        return new vn.ai.trueid.lottie.LottieFeatureFlag[] {vn.ai.trueid.lottie.LottieFeatureFlag.MergePathsApi19});
    }

    static LottieFeatureFlag()
    {
        vn.ai.trueid.lottie.LottieFeatureFlag.MergePathsApi19 = new vn.ai.trueid.lottie.LottieFeatureFlag("MergePathsApi19", 0, 19);
        vn.ai.trueid.lottie.LottieFeatureFlag.$VALUES = vn.ai.trueid.lottie.LottieFeatureFlag.$values();
        return;
    }

    private LottieFeatureFlag(String p1, int p2, int p3)
    {
        super(p1, p2);
        super.minRequiredSdkVersion = p3;
        return;
    }

    public static vn.ai.trueid.lottie.LottieFeatureFlag valueOf(String p1)
    {
        return ((vn.ai.trueid.lottie.LottieFeatureFlag) Enum.valueOf(vn.ai.trueid.lottie.LottieFeatureFlag, p1));
    }

    public static vn.ai.trueid.lottie.LottieFeatureFlag[] values()
    {
        return ((vn.ai.trueid.lottie.LottieFeatureFlag[]) vn.ai.trueid.lottie.LottieFeatureFlag.$VALUES.clone());
    }
}
