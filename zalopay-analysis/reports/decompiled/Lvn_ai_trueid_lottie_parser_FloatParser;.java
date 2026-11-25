package vn.ai.trueid.lottie.parser;
public class FloatParser implements vn.ai.trueid.lottie.parser.ValueParser {
    public static final vn.ai.trueid.lottie.parser.FloatParser INSTANCE;

    static FloatParser()
    {
        vn.ai.trueid.lottie.parser.FloatParser.INSTANCE = new vn.ai.trueid.lottie.parser.FloatParser();
        return;
    }

    private FloatParser()
    {
        return;
    }

    public Float parse(vn.ai.trueid.lottie.parser.moshi.JsonReader p1, float p2)
    {
        return Float.valueOf((vn.ai.trueid.lottie.parser.JsonUtils.valueFromObject(p1) * p2));
    }

    public bridge synthetic Object parse(vn.ai.trueid.lottie.parser.moshi.JsonReader p1, float p2)
    {
        return this.parse(p1, p2);
    }
}
