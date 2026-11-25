package vn.ai.trueid.lottie;
 class LottieConfig$Builder$2 implements vn.ai.trueid.lottie.network.LottieNetworkCacheProvider {
    final synthetic vn.ai.trueid.lottie.LottieConfig$Builder this$0;
    final synthetic vn.ai.trueid.lottie.network.LottieNetworkCacheProvider val$fileCacheProvider;

    public LottieConfig$Builder$2(vn.ai.trueid.lottie.LottieConfig$Builder p1, vn.ai.trueid.lottie.network.LottieNetworkCacheProvider p2)
    {
        this.this$0 = p1;
        this.val$fileCacheProvider = p2;
        return;
    }

    public java.io.File getCacheDir()
    {
        IllegalArgumentException v0_1 = this.val$fileCacheProvider.getCacheDir();
        if (!v0_1.isDirectory()) {
            throw new IllegalArgumentException("cache file must be a directory");
        } else {
            return v0_1;
        }
    }
}
