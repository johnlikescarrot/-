package vn.ai.trueid.lottie;
public final class LottieConfig$Builder {
    private vn.ai.trueid.lottie.network.LottieNetworkCacheProvider cacheProvider;
    private vn.ai.trueid.lottie.AsyncUpdates defaultAsyncUpdates;
    private boolean disablePathInterpolatorCache;
    private boolean enableNetworkCache;
    private boolean enableSystraceMarkers;
    private vn.ai.trueid.lottie.network.LottieNetworkFetcher networkFetcher;
    private vn.ai.trueid.lottie.configurations.reducemotion.ReducedMotionOption reducedMotionOption;

    public LottieConfig$Builder()
    {
        this.enableSystraceMarkers = 0;
        this.enableNetworkCache = 1;
        this.disablePathInterpolatorCache = 0;
        this.defaultAsyncUpdates = vn.ai.trueid.lottie.AsyncUpdates.AUTOMATIC;
        this.reducedMotionOption = new vn.ai.trueid.lottie.configurations.reducemotion.SystemReducedMotionOption();
        return;
    }

    public vn.ai.trueid.lottie.LottieConfig build()
    {
        return new vn.ai.trueid.lottie.LottieConfig(this.networkFetcher, this.cacheProvider, this.enableSystraceMarkers, this.enableNetworkCache, this.disablePathInterpolatorCache, this.defaultAsyncUpdates, this.reducedMotionOption, 0);
    }

    public vn.ai.trueid.lottie.LottieConfig$Builder setDefaultAsyncUpdates(vn.ai.trueid.lottie.AsyncUpdates p1)
    {
        this.defaultAsyncUpdates = p1;
        return this;
    }

    public vn.ai.trueid.lottie.LottieConfig$Builder setDisablePathInterpolatorCache(boolean p1)
    {
        this.disablePathInterpolatorCache = p1;
        return this;
    }

    public vn.ai.trueid.lottie.LottieConfig$Builder setEnableNetworkCache(boolean p1)
    {
        this.enableNetworkCache = p1;
        return this;
    }

    public vn.ai.trueid.lottie.LottieConfig$Builder setEnableSystraceMarkers(boolean p1)
    {
        this.enableSystraceMarkers = p1;
        return this;
    }

    public vn.ai.trueid.lottie.LottieConfig$Builder setNetworkCacheDir(java.io.File p2)
    {
        if (this.cacheProvider != null) {
            throw new IllegalStateException("There is already a cache provider!");
        } else {
            this.cacheProvider = new vn.ai.trueid.lottie.LottieConfig$Builder$1(this, p2);
            return this;
        }
    }

    public vn.ai.trueid.lottie.LottieConfig$Builder setNetworkCacheProvider(vn.ai.trueid.lottie.network.LottieNetworkCacheProvider p2)
    {
        if (this.cacheProvider != null) {
            throw new IllegalStateException("There is already a cache provider!");
        } else {
            this.cacheProvider = new vn.ai.trueid.lottie.LottieConfig$Builder$2(this, p2);
            return this;
        }
    }

    public vn.ai.trueid.lottie.LottieConfig$Builder setNetworkFetcher(vn.ai.trueid.lottie.network.LottieNetworkFetcher p1)
    {
        this.networkFetcher = p1;
        return this;
    }

    public vn.ai.trueid.lottie.LottieConfig$Builder setReducedMotionOption(vn.ai.trueid.lottie.configurations.reducemotion.ReducedMotionOption p1)
    {
        this.reducedMotionOption = p1;
        return this;
    }
}
