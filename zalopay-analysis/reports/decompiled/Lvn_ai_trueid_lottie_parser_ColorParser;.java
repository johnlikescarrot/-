package vn.ai.trueid.lottie.parser;
public class ColorParser implements vn.ai.trueid.lottie.parser.ValueParser {
    public static final vn.ai.trueid.lottie.parser.ColorParser INSTANCE;

    static ColorParser()
    {
        vn.ai.trueid.lottie.parser.ColorParser.INSTANCE = new vn.ai.trueid.lottie.parser.ColorParser();
        return;
    }

    private ColorParser()
    {
        return;
    }

    public Integer parse(vn.ai.trueid.lottie.parser.moshi.JsonReader p11, float p12)
    {
        int v12_2;
        if (p11.peek() != vn.ai.trueid.lottie.parser.moshi.JsonReader$Token.BEGIN_ARRAY) {
            v12_2 = 0;
        } else {
            v12_2 = 1;
        }
        if (v12_2 != 0) {
            p11.beginArray();
        }
        double v6_1;
        double v0_1 = p11.nextDouble();
        double v2 = p11.nextDouble();
        double v4 = p11.nextDouble();
        if (p11.peek() != vn.ai.trueid.lottie.parser.moshi.JsonReader$Token.NUMBER) {
            v6_1 = 4607182418800017408;
        } else {
            v6_1 = p11.nextDouble();
        }
        if (v12_2 != 0) {
            p11.endArray();
        }
        if ((v0_1 <= 4607182418800017408) && ((v2 <= 4607182418800017408) && (v4 <= 4607182418800017408))) {
            v0_1 *= 4643176031446892544;
            v2 *= 4643176031446892544;
            v4 *= 4643176031446892544;
            if (v6_1 <= 4607182418800017408) {
                v6_1 *= 4643176031446892544;
            }
        }
        return Integer.valueOf(android.graphics.Color.argb(((int) v6_1), ((int) v0_1), ((int) v2), ((int) v4)));
    }

    public bridge synthetic Object parse(vn.ai.trueid.lottie.parser.moshi.JsonReader p1, float p2)
    {
        return this.parse(p1, p2);
    }
}
