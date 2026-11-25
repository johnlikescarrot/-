package vn.ai.trueid.lottie;
public final synthetic class c implements java.util.concurrent.Callable {
    public final synthetic int a;
    public final synthetic String b;
    public final synthetic Object c;

    public synthetic c(String p1, int p2, Object p3)
    {
        this.a = p2;
        this.c = p3;
        this.b = p1;
        return;
    }

    public synthetic c(String p2, String p3)
    {
        this.a = 5;
        this.b = p2;
        this.c = p3;
        return;
    }

    public final Object call()
    {
        switch (this.a) {
            case 0:
                return vn.ai.trueid.lottie.LottieAnimationView.f(((vn.ai.trueid.lottie.LottieAnimationView) this.c), this.b);
            case 1:
                return vn.ai.trueid.lottie.LottieCompositionFactory.r(((java.io.InputStream) this.c), this.b);
            case 2:
                return vn.ai.trueid.lottie.LottieCompositionFactory.c(((okio.Source) this.c), this.b);
            case 3:
                return vn.ai.trueid.lottie.LottieCompositionFactory.d(((vn.ai.trueid.lottie.parser.moshi.JsonReader) this.c), this.b);
            case 4:
                return vn.ai.trueid.lottie.LottieCompositionFactory.m(this.b, ((org.json.JSONObject) this.c));
            default:
                return vn.ai.trueid.lottie.LottieCompositionFactory.k(this.b, ((String) this.c));
        }
    }
}
