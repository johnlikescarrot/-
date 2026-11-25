package vn.ai.trueid.lottie.parser;
 class CircleShapeParser {
    private static final vn.ai.trueid.lottie.parser.moshi.JsonReader$Options NAMES;

    static CircleShapeParser()
    {
        vn.ai.trueid.lottie.parser.CircleShapeParser.NAMES = vn.ai.trueid.lottie.parser.moshi.JsonReader$Options.of(new String[] {"nm", "p", "s", "hd", "d"}));
        return;
    }

    private CircleShapeParser()
    {
        return;
    }

    public static vn.ai.trueid.lottie.model.content.CircleShape parse(vn.ai.trueid.lottie.parser.moshi.JsonReader p10, vn.ai.trueid.lottie.LottieComposition p11, int p12)
    {
        int v12_5;
        if (p12 != 3) {
            v12_5 = 0;
        } else {
            v12_5 = 1;
        }
        int v8 = v12_5;
        boolean v9 = 0;
        String v5 = 0;
        vn.ai.trueid.lottie.model.animatable.AnimatableValue v6 = 0;
        vn.ai.trueid.lottie.model.animatable.AnimatablePointValue v7 = 0;
        while (p10.hasNext()) {
            int v12_3 = p10.selectName(vn.ai.trueid.lottie.parser.CircleShapeParser.NAMES);
            if (v12_3 == 0) {
                v5 = p10.nextString();
            } else {
                if (v12_3 == 1) {
                    v6 = vn.ai.trueid.lottie.parser.AnimatablePathValueParser.parseSplitPath(p10, p11);
                } else {
                    if (v12_3 == 2) {
                        v7 = vn.ai.trueid.lottie.parser.AnimatableValueParser.parsePoint(p10, p11);
                    } else {
                        if (v12_3 == 3) {
                            v9 = p10.nextBoolean();
                        } else {
                            if (v12_3 == 4) {
                                if (p10.nextInt() != 3) {
                                    v8 = 0;
                                } else {
                                    v8 = 1;
                                }
                            } else {
                                p10.skipName();
                                p10.skipValue();
                            }
                        }
                    }
                }
            }
        }
        return new vn.ai.trueid.lottie.model.content.CircleShape(v5, v6, v7, v8, v9);
    }
}
