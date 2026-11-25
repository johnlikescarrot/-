package vn.ai.trueid.lottie;
 class LottieConfig$Builder$1 implements vn.ai.trueid.lottie.network.LottieNetworkCacheProvider {
    final synthetic vn.ai.trueid.lottie.LottieConfig$Builder this$0;
    final synthetic java.io.File val$file;

    public LottieConfig$Builder$1(vn.ai.trueid.lottie.LottieConfig$Builder p1, java.io.File p2)
    {
        this.this$0 = p1;
        this.val$file = p2;
        return;
    }

    public java.io.File getCacheDir()
    {
        if (!this.val$file.isDirectory()) {
            throw new IllegalArgumentException("cache file must be a directory");
        } else {
            return this.val$file;
        }
    }
}
