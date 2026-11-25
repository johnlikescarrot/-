package vn.ai.trueid.lottie;
public class Lottie {

    private Lottie()
    {
        return;
    }

    public static void initialize(vn.ai.trueid.lottie.LottieConfig p1)
    {
        vn.ai.trueid.lottie.L.setFetcher(p1.networkFetcher);
        vn.ai.trueid.lottie.L.setCacheProvider(p1.cacheProvider);
        vn.ai.trueid.lottie.L.setTraceEnabled(p1.enableSystraceMarkers);
        vn.ai.trueid.lottie.L.setNetworkCacheEnabled(p1.enableNetworkCache);
        vn.ai.trueid.lottie.L.setDisablePathInterpolatorCache(p1.disablePathInterpolatorCache);
        vn.ai.trueid.lottie.L.setDefaultAsyncUpdates(p1.defaultAsyncUpdates);
        vn.ai.trueid.lottie.L.setReducedMotionOption(p1.reducedMotionOption);
        return;
    }
}
