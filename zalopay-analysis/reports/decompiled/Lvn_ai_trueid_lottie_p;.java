package vn.ai.trueid.lottie;
public final synthetic class p implements java.lang.Runnable {
    public final synthetic int a;
    public final synthetic vn.ai.trueid.lottie.LottieDrawable b;

    public synthetic p(vn.ai.trueid.lottie.LottieDrawable p1, int p2)
    {
        this.a = p2;
        this.b = p1;
        return;
    }

    public final void run()
    {
        switch (this.a) {
            case 0:
                vn.ai.trueid.lottie.LottieDrawable.b(this.b);
                return;
            default:
                vn.ai.trueid.lottie.LottieDrawable.i(this.b);
                return;
        }
    }
}
