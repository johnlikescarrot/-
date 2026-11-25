package vn.ai.trueid.lottie.parser;
public class ScaleXYParser implements vn.ai.trueid.lottie.parser.ValueParser {
    public static final vn.ai.trueid.lottie.parser.ScaleXYParser INSTANCE;

    static ScaleXYParser()
    {
        vn.ai.trueid.lottie.parser.ScaleXYParser.INSTANCE = new vn.ai.trueid.lottie.parser.ScaleXYParser();
        return;
    }

    private ScaleXYParser()
    {
        return;
    }

    public bridge synthetic Object parse(vn.ai.trueid.lottie.parser.moshi.JsonReader p1, float p2)
    {
        return this.parse(p1, p2);
    }

    public vn.ai.trueid.lottie.value.ScaleXY parse(vn.ai.trueid.lottie.parser.moshi.JsonReader p5, float p6)
    {
        int v0_2;
        if (p5.peek() != vn.ai.trueid.lottie.parser.moshi.JsonReader$Token.BEGIN_ARRAY) {
            v0_2 = 0;
        } else {
            v0_2 = 1;
        }
        if (v0_2 != 0) {
            p5.beginArray();
        }
        float v1_0 = ((float) p5.nextDouble());
        float v2_1 = ((float) p5.nextDouble());
        while (p5.hasNext()) {
            p5.skipValue();
        }
        if (v0_2 != 0) {
            p5.endArray();
        }
        return new vn.ai.trueid.lottie.value.ScaleXY(((v1_0 / 1120403456) * p6), ((v2_1 / 1120403456) * p6));
    }
}
