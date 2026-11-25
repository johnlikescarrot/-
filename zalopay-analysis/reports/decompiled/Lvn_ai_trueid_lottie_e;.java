package vn.ai.trueid.lottie;
public final synthetic class e implements java.lang.Runnable {
    public final synthetic int a;
    public final synthetic Object b;

    public synthetic e(int p1, Object p2)
    {
        this.a = p1;
        this.b = p2;
        return;
    }

    public final void run()
    {
        switch (this.a) {
            case 0:
                vn.ai.trueid.lottie.LottieCompositionFactory.h(((okio.Source) this.b));
                return;
            case 1:
                vn.ai.trueid.lottie.LottieCompositionFactory.n(((vn.ai.trueid.lottie.parser.moshi.JsonReader) this.b));
                return;
            case 2:
                vn.ai.trueid.lottie.LottieCompositionFactory.g(((java.io.InputStream) this.b));
                return;
            default:
                vn.ai.trueid.lottie.LottieTask.a(((vn.ai.trueid.lottie.LottieTask) this.b));
                return;
        }
    }
}
