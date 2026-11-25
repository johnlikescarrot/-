package vn.ai.trueid.lottie.parser;
 class RectangleShapeParser {
    private static final vn.ai.trueid.lottie.parser.moshi.JsonReader$Options NAMES;

    static RectangleShapeParser()
    {
        vn.ai.trueid.lottie.parser.RectangleShapeParser.NAMES = vn.ai.trueid.lottie.parser.moshi.JsonReader$Options.of(new String[] {"nm", "p", "s", "r", "hd"}));
        return;
    }

    private RectangleShapeParser()
    {
        return;
    }

    public static vn.ai.trueid.lottie.model.content.RectangleShape parse(vn.ai.trueid.lottie.parser.moshi.JsonReader p8, vn.ai.trueid.lottie.LottieComposition p9)
    {
        String v3 = 0;
        vn.ai.trueid.lottie.model.animatable.AnimatableValue v4 = 0;
        vn.ai.trueid.lottie.model.animatable.AnimatablePointValue v5 = 0;
        vn.ai.trueid.lottie.model.animatable.AnimatableFloatValue v6 = 0;
        boolean v7 = 0;
        while (p8.hasNext()) {
            int v0_2 = p8.selectName(vn.ai.trueid.lottie.parser.RectangleShapeParser.NAMES);
            if (v0_2 == 0) {
                v3 = p8.nextString();
            } else {
                if (v0_2 == 1) {
                    v4 = vn.ai.trueid.lottie.parser.AnimatablePathValueParser.parseSplitPath(p8, p9);
                } else {
                    if (v0_2 == 2) {
                        v5 = vn.ai.trueid.lottie.parser.AnimatableValueParser.parsePoint(p8, p9);
                    } else {
                        if (v0_2 == 3) {
                            v6 = vn.ai.trueid.lottie.parser.AnimatableValueParser.parseFloat(p8, p9);
                        } else {
                            if (v0_2 == 4) {
                                v7 = p8.nextBoolean();
                            } else {
                                p8.skipValue();
                            }
                        }
                    }
                }
            }
        }
        return new vn.ai.trueid.lottie.model.content.RectangleShape(v3, v4, v5, v6, v7);
    }
}
