package vn.ai.trueid.lottie;
public final synthetic class i implements java.lang.Runnable {
    public final synthetic int a;
    public final synthetic java.util.zip.ZipInputStream b;

    public synthetic i(java.util.zip.ZipInputStream p1, int p2)
    {
        this.a = p2;
        this.b = p1;
        return;
    }

    public final void run()
    {
        switch (this.a) {
            case 0:
                vn.ai.trueid.lottie.LottieCompositionFactory.f(this.b);
                return;
            default:
                vn.ai.trueid.lottie.LottieCompositionFactory.j(this.b);
                return;
        }
    }
}
