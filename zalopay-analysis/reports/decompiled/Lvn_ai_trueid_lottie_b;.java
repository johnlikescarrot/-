package vn.ai.trueid.lottie;
public final synthetic class b implements java.util.concurrent.Callable {
    public final synthetic vn.ai.trueid.lottie.LottieAnimationView a;
    public final synthetic int b;

    public synthetic b(vn.ai.trueid.lottie.LottieAnimationView p1, int p2)
    {
        this.a = p1;
        this.b = p2;
        return;
    }

    public final Object call()
    {
        return vn.ai.trueid.lottie.LottieAnimationView.d(this.a, this.b);
    }
}
