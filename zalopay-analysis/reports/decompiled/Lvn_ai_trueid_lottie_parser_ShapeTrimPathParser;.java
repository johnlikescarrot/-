package vn.ai.trueid.lottie.parser;
 class ShapeTrimPathParser {
    private static final vn.ai.trueid.lottie.parser.moshi.JsonReader$Options NAMES;

    static ShapeTrimPathParser()
    {
        String v2 = "o";
        vn.ai.trueid.lottie.parser.ShapeTrimPathParser.NAMES = vn.ai.trueid.lottie.parser.moshi.JsonReader$Options.of(new String[] {"s", "hd"}));
        return;
    }

    private ShapeTrimPathParser()
    {
        return;
    }

    public static vn.ai.trueid.lottie.model.content.ShapeTrimPath parse(vn.ai.trueid.lottie.parser.moshi.JsonReader p9, vn.ai.trueid.lottie.LottieComposition p10)
    {
        String v3 = 0;
        vn.ai.trueid.lottie.model.content.ShapeTrimPath$Type v4 = 0;
        vn.ai.trueid.lottie.model.animatable.AnimatableFloatValue v5 = 0;
        vn.ai.trueid.lottie.model.animatable.AnimatableFloatValue v6 = 0;
        vn.ai.trueid.lottie.model.animatable.AnimatableFloatValue v7 = 0;
        boolean v8 = 0;
        while (p9.hasNext()) {
            int v0_2 = p9.selectName(vn.ai.trueid.lottie.parser.ShapeTrimPathParser.NAMES);
            if (v0_2 == 0) {
                v5 = vn.ai.trueid.lottie.parser.AnimatableValueParser.parseFloat(p9, p10, 0);
            } else {
                if (v0_2 == 1) {
                    v6 = vn.ai.trueid.lottie.parser.AnimatableValueParser.parseFloat(p9, p10, 0);
                } else {
                    if (v0_2 == 2) {
                        v7 = vn.ai.trueid.lottie.parser.AnimatableValueParser.parseFloat(p9, p10, 0);
                    } else {
                        if (v0_2 == 3) {
                            v3 = p9.nextString();
                        } else {
                            if (v0_2 == 4) {
                                v4 = vn.ai.trueid.lottie.model.content.ShapeTrimPath$Type.forId(p9.nextInt());
                            } else {
                                if (v0_2 == 5) {
                                    v8 = p9.nextBoolean();
                                } else {
                                    p9.skipValue();
                                }
                            }
                        }
                    }
                }
            }
        }
        return new vn.ai.trueid.lottie.model.content.ShapeTrimPath(v3, v4, v5, v6, v7, v8);
    }
}
