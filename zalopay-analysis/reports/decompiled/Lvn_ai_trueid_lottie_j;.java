package vn.ai.trueid.lottie;
public final synthetic class j implements vn.ai.trueid.lottie.LottieDrawable$LazyCompositionTask {
    public final synthetic int a;
    public final synthetic vn.ai.trueid.lottie.LottieDrawable b;
    public final synthetic int c;

    public synthetic j(vn.ai.trueid.lottie.LottieDrawable p1, int p2, int p3)
    {
        this.a = p3;
        this.b = p1;
        this.c = p2;
        return;
    }

    public final void run(vn.ai.trueid.lottie.LottieComposition p3)
    {
        switch (this.a) {
            case 0:
                vn.ai.trueid.lottie.LottieDrawable.m(this.b, this.c, p3);
                return;
            case 1:
                vn.ai.trueid.lottie.LottieDrawable.c(this.b, this.c, p3);
                return;
            default:
                vn.ai.trueid.lottie.LottieDrawable.f(this.b, this.c, p3);
                return;
        }
    }
}
