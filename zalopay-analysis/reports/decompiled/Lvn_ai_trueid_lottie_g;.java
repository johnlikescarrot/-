package vn.ai.trueid.lottie;
public final synthetic class g implements java.util.concurrent.Callable {
    public final synthetic java.io.InputStream a;
    public final synthetic String b;
    public final synthetic boolean c;

    public synthetic g(java.io.InputStream p1, String p2, boolean p3)
    {
        this.a = p1;
        this.b = p2;
        this.c = p3;
        return;
    }

    public final Object call()
    {
        return vn.ai.trueid.lottie.LottieCompositionFactory.l(this.a, this.b, this.c);
    }
}
