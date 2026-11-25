package vn.ai.trueid.lottie;
public final synthetic class o implements vn.ai.trueid.lottie.LottieDrawable$LazyCompositionTask {
    public final synthetic vn.ai.trueid.lottie.LottieDrawable a;
    public final synthetic vn.ai.trueid.lottie.model.KeyPath b;
    public final synthetic Object c;
    public final synthetic vn.ai.trueid.lottie.value.LottieValueCallback d;

    public synthetic o(vn.ai.trueid.lottie.LottieDrawable p1, vn.ai.trueid.lottie.model.KeyPath p2, Object p3, vn.ai.trueid.lottie.value.LottieValueCallback p4)
    {
        this.a = p1;
        this.b = p2;
        this.c = p3;
        this.d = p4;
        return;
    }

    public final void run(vn.ai.trueid.lottie.LottieComposition p5)
    {
        vn.ai.trueid.lottie.LottieDrawable.k(this.a, this.b, this.c, this.d, p5);
        return;
    }
}
