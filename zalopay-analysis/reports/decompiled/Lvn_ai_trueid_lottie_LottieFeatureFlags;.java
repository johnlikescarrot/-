package vn.ai.trueid.lottie;
 class LottieFeatureFlags {
    private final java.util.HashSet enabledFlags;

    public LottieFeatureFlags()
    {
        this.enabledFlags = new java.util.HashSet();
        return;
    }

    public boolean enableFlag(vn.ai.trueid.lottie.LottieFeatureFlag p2, boolean p3)
    {
        if (p3 == null) {
            return this.enabledFlags.remove(p2);
        } else {
            if (android.os.Build$VERSION.SDK_INT >= p2.minRequiredSdkVersion) {
                return this.enabledFlags.add(p2);
            } else {
                vn.ai.trueid.lottie.utils.Logger.warning(String.format(wmoi.igob.ggcn(247), new Object[] {p2.name(), Integer.valueOf(p2.minRequiredSdkVersion)})));
                return 0;
            }
        }
    }

    public boolean isFlagEnabled(vn.ai.trueid.lottie.LottieFeatureFlag p2)
    {
        return this.enabledFlags.contains(p2);
    }
}
