package vn.ai.trueid.lottie.parser;
public class IntegerParser implements vn.ai.trueid.lottie.parser.ValueParser {
    public static final vn.ai.trueid.lottie.parser.IntegerParser INSTANCE;

    static IntegerParser()
    {
        vn.ai.trueid.lottie.parser.IntegerParser.INSTANCE = new vn.ai.trueid.lottie.parser.IntegerParser();
        return;
    }

    private IntegerParser()
    {
        return;
    }

    public Integer parse(vn.ai.trueid.lottie.parser.moshi.JsonReader p1, float p2)
    {
        return Integer.valueOf(Math.round((vn.ai.trueid.lottie.parser.JsonUtils.valueFromObject(p1) * p2)));
    }

    public bridge synthetic Object parse(vn.ai.trueid.lottie.parser.moshi.JsonReader p1, float p2)
    {
        return this.parse(p1, p2);
    }
}
