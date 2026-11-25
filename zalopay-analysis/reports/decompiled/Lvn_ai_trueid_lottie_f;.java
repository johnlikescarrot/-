package vn.ai.trueid.lottie;
public final synthetic class f implements vn.ai.trueid.lottie.LottieListener {
    public final synthetic int a;
    public final synthetic String b;
    public final synthetic java.util.concurrent.atomic.AtomicBoolean c;

    public synthetic f(String p1, java.util.concurrent.atomic.AtomicBoolean p2, int p3)
    {
        this.a = p3;
        this.b = p1;
        this.c = p2;
        return;
    }

    public final void onResult(Object p3)
    {
        switch (this.a) {
            case 0:
                vn.ai.trueid.lottie.LottieCompositionFactory.t(this.b, this.c, ((vn.ai.trueid.lottie.LottieComposition) p3));
                return;
            default:
                vn.ai.trueid.lottie.LottieCompositionFactory.q(this.b, this.c, ((Throwable) p3));
                return;
        }
    }
}
