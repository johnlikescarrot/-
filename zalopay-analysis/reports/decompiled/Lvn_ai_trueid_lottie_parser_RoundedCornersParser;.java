package vn.ai.trueid.lottie.parser;
public class RoundedCornersParser {
    private static final vn.ai.trueid.lottie.parser.moshi.JsonReader$Options NAMES;

    static RoundedCornersParser()
    {
        vn.ai.trueid.lottie.parser.RoundedCornersParser.NAMES = vn.ai.trueid.lottie.parser.moshi.JsonReader$Options.of(new String[] {"nm", "r", "hd"}));
        return;
    }

    private RoundedCornersParser()
    {
        return;
    }

    public static vn.ai.trueid.lottie.model.content.RoundedCorners parse(vn.ai.trueid.lottie.parser.moshi.JsonReader p6, vn.ai.trueid.lottie.LottieComposition p7)
    {
        boolean v1 = 0;
        String v2 = 0;
        vn.ai.trueid.lottie.model.animatable.AnimatableFloatValue v3 = 0;
        while (p6.hasNext()) {
            int v4_1 = p6.selectName(vn.ai.trueid.lottie.parser.RoundedCornersParser.NAMES);
            if (v4_1 == 0) {
                v2 = p6.nextString();
            } else {
                if (v4_1 == 1) {
                    v3 = vn.ai.trueid.lottie.parser.AnimatableValueParser.parseFloat(p6, p7, 1);
                } else {
                    if (v4_1 == 2) {
                        v1 = p6.nextBoolean();
                    } else {
                        p6.skipValue();
                    }
                }
            }
        }
        if (!v1) {
            return new vn.ai.trueid.lottie.model.content.RoundedCorners(v2, v3);
        } else {
            return 0;
        }
    }
}
