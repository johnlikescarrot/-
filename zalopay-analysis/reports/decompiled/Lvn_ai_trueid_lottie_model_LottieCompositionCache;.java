package vn.ai.trueid.lottie.model;
public class LottieCompositionCache {
    private static final vn.ai.trueid.lottie.model.LottieCompositionCache INSTANCE;
    private final P.q cache;

    static LottieCompositionCache()
    {
        vn.ai.trueid.lottie.model.LottieCompositionCache.INSTANCE = new vn.ai.trueid.lottie.model.LottieCompositionCache();
        return;
    }

    public LottieCompositionCache()
    {
        this.cache = new P.q(20);
        return;
    }

    public static vn.ai.trueid.lottie.model.LottieCompositionCache getInstance()
    {
        return vn.ai.trueid.lottie.model.LottieCompositionCache.INSTANCE;
    }

    public void clear()
    {
        this.cache.evictAll();
        return;
    }

    public vn.ai.trueid.lottie.LottieComposition get(String p2)
    {
        if (p2 != null) {
            return ((vn.ai.trueid.lottie.LottieComposition) this.cache.get(p2));
        } else {
            return 0;
        }
    }

    public void put(String p2, vn.ai.trueid.lottie.LottieComposition p3)
    {
        if (p2 != null) {
            this.cache.put(p2, p3);
            return;
        } else {
            return;
        }
    }

    public void resize(int p2)
    {
        this.cache.resize(p2);
        return;
    }
}
