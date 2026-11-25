package vn.ai.trueid.lottie;
public class L {
    public static boolean DBG = False;
    public static final String TAG = "LOTTIE";
    private static vn.ai.trueid.lottie.network.LottieNetworkCacheProvider cacheProvider = None;
    private static vn.ai.trueid.lottie.AsyncUpdates defaultAsyncUpdates = None;
    private static boolean disablePathInterpolatorCache = False;
    private static vn.ai.trueid.lottie.network.LottieNetworkFetcher fetcher = None;
    private static ThreadLocal lottieTrace = None;
    private static volatile vn.ai.trueid.lottie.network.NetworkCache networkCache = None;
    private static boolean networkCacheEnabled = True;
    private static volatile vn.ai.trueid.lottie.network.NetworkFetcher networkFetcher;
    private static vn.ai.trueid.lottie.configurations.reducemotion.ReducedMotionOption reducedMotionOption;
    private static boolean traceEnabled;

    static L()
    {
        vn.ai.trueid.lottie.L.defaultAsyncUpdates = vn.ai.trueid.lottie.AsyncUpdates.AUTOMATIC;
        vn.ai.trueid.lottie.L.reducedMotionOption = new vn.ai.trueid.lottie.configurations.reducemotion.SystemReducedMotionOption();
        return;
    }

    private L()
    {
        return;
    }

    public static synthetic java.io.File a(android.content.Context p0)
    {
        return vn.ai.trueid.lottie.L.lambda$networkCache$0(p0);
    }

    public static void beginSection(String p1)
    {
        if (vn.ai.trueid.lottie.L.traceEnabled) {
            vn.ai.trueid.lottie.L.getTrace().beginSection(p1);
            return;
        } else {
            return;
        }
    }

    public static float endSection(String p1)
    {
        if (vn.ai.trueid.lottie.L.traceEnabled) {
            return vn.ai.trueid.lottie.L.getTrace().endSection(p1);
        } else {
            return 0;
        }
    }

    public static vn.ai.trueid.lottie.AsyncUpdates getDefaultAsyncUpdates()
    {
        return vn.ai.trueid.lottie.L.defaultAsyncUpdates;
    }

    public static boolean getDisablePathInterpolatorCache()
    {
        return vn.ai.trueid.lottie.L.disablePathInterpolatorCache;
    }

    public static vn.ai.trueid.lottie.configurations.reducemotion.ReducedMotionOption getReducedMotionOption()
    {
        return vn.ai.trueid.lottie.L.reducedMotionOption;
    }

    private static vn.ai.trueid.lottie.utils.LottieTrace getTrace()
    {
        vn.ai.trueid.lottie.utils.LottieTrace v0_2 = ((vn.ai.trueid.lottie.utils.LottieTrace) vn.ai.trueid.lottie.L.lottieTrace.get());
        if (v0_2 == null) {
            v0_2 = new vn.ai.trueid.lottie.utils.LottieTrace();
            vn.ai.trueid.lottie.L.lottieTrace.set(v0_2);
        }
        return v0_2;
    }

    public static boolean isTraceEnabled()
    {
        return vn.ai.trueid.lottie.L.traceEnabled;
    }

    private static synthetic java.io.File lambda$networkCache$0(android.content.Context p2)
    {
        return new java.io.File(p2.getCacheDir(), wmoi.igob.ggcn(9020));
    }

    public static vn.ai.trueid.lottie.network.NetworkCache networkCache(android.content.Context p3)
    {
        if (vn.ai.trueid.lottie.L.networkCacheEnabled) {
            Throwable v3_3 = p3.getApplicationContext();
            vn.ai.trueid.lottie.network.NetworkCache v0_3 = vn.ai.trueid.lottie.L.networkCache;
            if (v0_3 != null) {
                return v0_3;
            } else {
                vn.ai.trueid.lottie.network.NetworkCache v0_2 = vn.ai.trueid.lottie.L.networkCache;
                if (v0_2 == null) {
                    vn.ai.trueid.lottie.a v2_0 = vn.ai.trueid.lottie.L.cacheProvider;
                    if (v2_0 == null) {
                        v2_0 = new vn.ai.trueid.lottie.a(v3_3);
                    } else {
                    }
                    v0_2 = new vn.ai.trueid.lottie.network.NetworkCache(v2_0);
                    vn.ai.trueid.lottie.L.networkCache = v0_2;
                }
                return v0_2;
            }
        } else {
            return 0;
        }
    }

    public static vn.ai.trueid.lottie.network.NetworkFetcher networkFetcher(android.content.Context p3)
    {
        vn.ai.trueid.lottie.network.NetworkFetcher v0_0 = vn.ai.trueid.lottie.L.networkFetcher;
        if (v0_0 != null) {
            return v0_0;
        } else {
            vn.ai.trueid.lottie.network.NetworkFetcher v0_1 = vn.ai.trueid.lottie.L.networkFetcher;
            if (v0_1 == null) {
                vn.ai.trueid.lottie.network.NetworkCache v3_2 = vn.ai.trueid.lottie.L.networkCache(p3);
                vn.ai.trueid.lottie.network.DefaultLottieNetworkFetcher v2_1 = vn.ai.trueid.lottie.L.fetcher;
                if (v2_1 == null) {
                    v2_1 = new vn.ai.trueid.lottie.network.DefaultLottieNetworkFetcher();
                } else {
                }
                v0_1 = new vn.ai.trueid.lottie.network.NetworkFetcher(v3_2, v2_1);
                vn.ai.trueid.lottie.L.networkFetcher = v0_1;
            }
            return v0_1;
        }
    }

    public static void setCacheProvider(vn.ai.trueid.lottie.network.LottieNetworkCacheProvider p1)
    {
        boolean v0_0 = vn.ai.trueid.lottie.L.cacheProvider;
        if (((v0_0) || (p1 != 0)) && ((!v0_0) || (!v0_0.equals(p1)))) {
            vn.ai.trueid.lottie.L.cacheProvider = p1;
            vn.ai.trueid.lottie.L.networkCache = 0;
            return;
        } else {
            return;
        }
    }

    public static void setDefaultAsyncUpdates(vn.ai.trueid.lottie.AsyncUpdates p0)
    {
        vn.ai.trueid.lottie.L.defaultAsyncUpdates = p0;
        return;
    }

    public static void setDisablePathInterpolatorCache(boolean p0)
    {
        vn.ai.trueid.lottie.L.disablePathInterpolatorCache = p0;
        return;
    }

    public static void setFetcher(vn.ai.trueid.lottie.network.LottieNetworkFetcher p1)
    {
        boolean v0_0 = vn.ai.trueid.lottie.L.fetcher;
        if (((v0_0) || (p1 != 0)) && ((!v0_0) || (!v0_0.equals(p1)))) {
            vn.ai.trueid.lottie.L.fetcher = p1;
            vn.ai.trueid.lottie.L.networkFetcher = 0;
            return;
        } else {
            return;
        }
    }

    public static void setNetworkCacheEnabled(boolean p0)
    {
        vn.ai.trueid.lottie.L.networkCacheEnabled = p0;
        return;
    }

    public static void setReducedMotionOption(vn.ai.trueid.lottie.configurations.reducemotion.ReducedMotionOption p0)
    {
        vn.ai.trueid.lottie.L.reducedMotionOption = p0;
        return;
    }

    public static void setTraceEnabled(boolean p1)
    {
        if (vn.ai.trueid.lottie.L.traceEnabled != p1) {
            vn.ai.trueid.lottie.L.traceEnabled = p1;
            if ((p1 != null) && (vn.ai.trueid.lottie.L.lottieTrace == null)) {
                vn.ai.trueid.lottie.L.lottieTrace = new ThreadLocal();
            }
        }
        return;
    }
}
