package vn.ai.trueid.lottie;
public final synthetic class a implements vn.ai.trueid.lottie.network.LottieNetworkCacheProvider {
    public final synthetic android.content.Context a;

    public synthetic a(android.content.Context p1)
    {
        this.a = p1;
        return;
    }

    public final java.io.File getCacheDir()
    {
        return vn.ai.trueid.lottie.L.a(this.a);
    }
}
