package vn.ai.trueid.lottie;
public class LottieComposition$Factory {

    private LottieComposition$Factory()
    {
        return;
    }

    public static vn.ai.trueid.lottie.Cancellable fromAssetFileName(android.content.Context p2, String p3, vn.ai.trueid.lottie.OnCompositionLoadedListener p4)
    {
        vn.ai.trueid.lottie.LottieComposition$Factory$ListenerAdapter v0_1 = new vn.ai.trueid.lottie.LottieComposition$Factory$ListenerAdapter(p4, 0);
        vn.ai.trueid.lottie.LottieCompositionFactory.fromAsset(p2, p3).addListener(v0_1);
        return v0_1;
    }

    public static vn.ai.trueid.lottie.LottieComposition fromFileSync(android.content.Context p0, String p1)
    {
        return ((vn.ai.trueid.lottie.LottieComposition) vn.ai.trueid.lottie.LottieCompositionFactory.fromAssetSync(p0, p1).getValue());
    }

    public static vn.ai.trueid.lottie.Cancellable fromInputStream(java.io.InputStream p2, vn.ai.trueid.lottie.OnCompositionLoadedListener p3)
    {
        vn.ai.trueid.lottie.LottieComposition$Factory$ListenerAdapter v0_1 = new vn.ai.trueid.lottie.LottieComposition$Factory$ListenerAdapter(p3, 0);
        vn.ai.trueid.lottie.LottieCompositionFactory.fromJsonInputStream(p2, 0).addListener(v0_1);
        return v0_1;
    }

    public static vn.ai.trueid.lottie.LottieComposition fromInputStreamSync(java.io.InputStream p1)
    {
        return ((vn.ai.trueid.lottie.LottieComposition) vn.ai.trueid.lottie.LottieCompositionFactory.fromJsonInputStreamSync(p1, 0).getValue());
    }

    public static vn.ai.trueid.lottie.LottieComposition fromInputStreamSync(java.io.InputStream p0, boolean p1)
    {
        if (p1 != 0) {
            vn.ai.trueid.lottie.utils.Logger.warning(wmoi.igob.ggcn(12606));
        }
        return ((vn.ai.trueid.lottie.LottieComposition) vn.ai.trueid.lottie.LottieCompositionFactory.fromJsonInputStreamSync(p0, 0).getValue());
    }

    public static vn.ai.trueid.lottie.Cancellable fromJsonReader(vn.ai.trueid.lottie.parser.moshi.JsonReader p2, vn.ai.trueid.lottie.OnCompositionLoadedListener p3)
    {
        vn.ai.trueid.lottie.LottieComposition$Factory$ListenerAdapter v0_1 = new vn.ai.trueid.lottie.LottieComposition$Factory$ListenerAdapter(p3, 0);
        vn.ai.trueid.lottie.LottieCompositionFactory.fromJsonReader(p2, 0).addListener(v0_1);
        return v0_1;
    }

    public static vn.ai.trueid.lottie.Cancellable fromJsonString(String p2, vn.ai.trueid.lottie.OnCompositionLoadedListener p3)
    {
        vn.ai.trueid.lottie.LottieComposition$Factory$ListenerAdapter v0_1 = new vn.ai.trueid.lottie.LottieComposition$Factory$ListenerAdapter(p3, 0);
        vn.ai.trueid.lottie.LottieCompositionFactory.fromJsonString(p2, 0).addListener(v0_1);
        return v0_1;
    }

    public static vn.ai.trueid.lottie.LottieComposition fromJsonSync(android.content.res.Resources p0, org.json.JSONObject p1)
    {
        return ((vn.ai.trueid.lottie.LottieComposition) vn.ai.trueid.lottie.LottieCompositionFactory.fromJsonSync(p1, 0).getValue());
    }

    public static vn.ai.trueid.lottie.LottieComposition fromJsonSync(String p1)
    {
        return ((vn.ai.trueid.lottie.LottieComposition) vn.ai.trueid.lottie.LottieCompositionFactory.fromJsonStringSync(p1, 0).getValue());
    }

    public static vn.ai.trueid.lottie.LottieComposition fromJsonSync(vn.ai.trueid.lottie.parser.moshi.JsonReader p1)
    {
        return ((vn.ai.trueid.lottie.LottieComposition) vn.ai.trueid.lottie.LottieCompositionFactory.fromJsonReaderSync(p1, 0).getValue());
    }

    public static vn.ai.trueid.lottie.Cancellable fromRawFile(android.content.Context p2, int p3, vn.ai.trueid.lottie.OnCompositionLoadedListener p4)
    {
        vn.ai.trueid.lottie.LottieComposition$Factory$ListenerAdapter v0_1 = new vn.ai.trueid.lottie.LottieComposition$Factory$ListenerAdapter(p4, 0);
        vn.ai.trueid.lottie.LottieCompositionFactory.fromRawRes(p2, p3).addListener(v0_1);
        return v0_1;
    }
}
