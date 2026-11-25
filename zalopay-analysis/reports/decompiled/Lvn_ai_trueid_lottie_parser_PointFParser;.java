package vn.ai.trueid.lottie.parser;
public class PointFParser implements vn.ai.trueid.lottie.parser.ValueParser {
    public static final vn.ai.trueid.lottie.parser.PointFParser INSTANCE;

    static PointFParser()
    {
        vn.ai.trueid.lottie.parser.PointFParser.INSTANCE = new vn.ai.trueid.lottie.parser.PointFParser();
        return;
    }

    private PointFParser()
    {
        return;
    }

    public android.graphics.PointF parse(vn.ai.trueid.lottie.parser.moshi.JsonReader p5, float p6)
    {
        android.graphics.PointF v0_0 = p5.peek();
        if (v0_0 != vn.ai.trueid.lottie.parser.moshi.JsonReader$Token.BEGIN_ARRAY) {
            if (v0_0 != vn.ai.trueid.lottie.parser.moshi.JsonReader$Token.BEGIN_OBJECT) {
                if (v0_0 != vn.ai.trueid.lottie.parser.moshi.JsonReader$Token.NUMBER) {
                    boolean v6_1 = new StringBuilder("Cannot convert json to point. Next token is ");
                    v6_1.append(v0_0);
                    throw new IllegalArgumentException(v6_1.toString());
                } else {
                    android.graphics.PointF v0_2 = new android.graphics.PointF((((float) p5.nextDouble()) * p6), (((float) p5.nextDouble()) * p6));
                    while (p5.hasNext()) {
                        p5.skipValue();
                    }
                    return v0_2;
                }
            } else {
                return vn.ai.trueid.lottie.parser.JsonUtils.jsonToPoint(p5, p6);
            }
        } else {
            return vn.ai.trueid.lottie.parser.JsonUtils.jsonToPoint(p5, p6);
        }
    }

    public bridge synthetic Object parse(vn.ai.trueid.lottie.parser.moshi.JsonReader p1, float p2)
    {
        return this.parse(p1, p2);
    }
}
