package vn.ai.trueid.lottie;
public final synthetic class h implements java.util.concurrent.Callable {
    public final synthetic int a;
    public final synthetic android.content.Context b;
    public final synthetic java.util.zip.ZipInputStream c;
    public final synthetic String d;

    public synthetic h(android.content.Context p1, java.util.zip.ZipInputStream p2, String p3, int p4)
    {
        this.a = p4;
        this.b = p1;
        this.c = p2;
        this.d = p3;
        return;
    }

    public final Object call()
    {
        switch (this.a) {
            case 0:
                return vn.ai.trueid.lottie.LottieCompositionFactory.o(this.b, this.c, this.d);
            default:
                return vn.ai.trueid.lottie.LottieCompositionFactory.i(this.b, this.c, this.d);
        }
    }
}
