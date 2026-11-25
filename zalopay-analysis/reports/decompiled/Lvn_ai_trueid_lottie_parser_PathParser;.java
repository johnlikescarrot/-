package vn.ai.trueid.lottie.parser;
public class PathParser implements vn.ai.trueid.lottie.parser.ValueParser {
    public static final vn.ai.trueid.lottie.parser.PathParser INSTANCE;

    static PathParser()
    {
        vn.ai.trueid.lottie.parser.PathParser.INSTANCE = new vn.ai.trueid.lottie.parser.PathParser();
        return;
    }

    private PathParser()
    {
        return;
    }

    public android.graphics.PointF parse(vn.ai.trueid.lottie.parser.moshi.JsonReader p1, float p2)
    {
        return vn.ai.trueid.lottie.parser.JsonUtils.jsonToPoint(p1, p2);
    }

    public bridge synthetic Object parse(vn.ai.trueid.lottie.parser.moshi.JsonReader p1, float p2)
    {
        return this.parse(p1, p2);
    }
}
