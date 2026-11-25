package vn.ai.trueid.lottie;
public final synthetic class m implements vn.ai.trueid.lottie.LottieDrawable$LazyCompositionTask {
    public final synthetic int a;
    public final synthetic vn.ai.trueid.lottie.LottieDrawable b;

    public synthetic m(vn.ai.trueid.lottie.LottieDrawable p1, int p2)
    {
        this.a = p2;
        this.b = p1;
        return;
    }

    public final void run(vn.ai.trueid.lottie.LottieComposition p2)
    {
        switch (this.a) {
            case 0:
                vn.ai.trueid.lottie.LottieDrawable.e(this.b, p2);
                return;
            default:
                vn.ai.trueid.lottie.LottieDrawable.q(this.b, p2);
                return;
        }
    }
}
