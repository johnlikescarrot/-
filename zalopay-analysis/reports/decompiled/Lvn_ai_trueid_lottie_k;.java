package vn.ai.trueid.lottie;
public final synthetic class k implements vn.ai.trueid.lottie.LottieDrawable$LazyCompositionTask {
    public final synthetic int a;
    public final synthetic vn.ai.trueid.lottie.LottieDrawable b;
    public final synthetic String c;

    public synthetic k(vn.ai.trueid.lottie.LottieDrawable p1, String p2, int p3)
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
                vn.ai.trueid.lottie.LottieDrawable.g(this.b, this.c, p3);
                return;
            case 1:
                vn.ai.trueid.lottie.LottieDrawable.n(this.b, this.c, p3);
                return;
            default:
                vn.ai.trueid.lottie.LottieDrawable.l(this.b, this.c, p3);
                return;
        }
    }
}
