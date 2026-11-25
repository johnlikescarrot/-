package vn.ai.trueid.lottie;
public class LottieConfig {
    final vn.ai.trueid.lottie.network.LottieNetworkCacheProvider cacheProvider;
    final vn.ai.trueid.lottie.AsyncUpdates defaultAsyncUpdates;
    final boolean disablePathInterpolatorCache;
    final boolean enableNetworkCache;
    final boolean enableSystraceMarkers;
    final vn.ai.trueid.lottie.network.LottieNetworkFetcher networkFetcher;
    final vn.ai.trueid.lottie.configurations.reducemotion.ReducedMotionOption reducedMotionOption;

    private LottieConfig(vn.ai.trueid.lottie.network.LottieNetworkFetcher p1, vn.ai.trueid.lottie.network.LottieNetworkCacheProvider p2, boolean p3, boolean p4, boolean p5, vn.ai.trueid.lottie.AsyncUpdates p6, vn.ai.trueid.lottie.configurations.reducemotion.ReducedMotionOption p7)
    {
        this.networkFetcher = p1;
        this.cacheProvider = p2;
        this.enableSystraceMarkers = p3;
        this.enableNetworkCache = p4;
        this.disablePathInterpolatorCache = p5;
        this.defaultAsyncUpdates = p6;
        this.reducedMotionOption = p7;
        return;
    }

    public synthetic LottieConfig(vn.ai.trueid.lottie.network.LottieNetworkFetcher p1, vn.ai.trueid.lottie.network.LottieNetworkCacheProvider p2, boolean p3, boolean p4, boolean p5, vn.ai.trueid.lottie.AsyncUpdates p6, vn.ai.trueid.lottie.configurations.reducemotion.ReducedMotionOption p7, vn.ai.trueid.lottie.LottieConfig$1 p8)
    {
        this(p1, p2, p3, p4, p5, p6, p7);
        return;
    }
}
