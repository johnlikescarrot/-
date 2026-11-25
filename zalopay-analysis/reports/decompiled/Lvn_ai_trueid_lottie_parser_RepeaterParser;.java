package vn.ai.trueid.lottie.parser;
 class RepeaterParser {
    private static final vn.ai.trueid.lottie.parser.moshi.JsonReader$Options NAMES;

    static RepeaterParser()
    {
        vn.ai.trueid.lottie.parser.RepeaterParser.NAMES = vn.ai.trueid.lottie.parser.moshi.JsonReader$Options.of(new String[] {"nm", "c", "o", "tr", "hd"}));
        return;
    }

    private RepeaterParser()
    {
        return;
    }

    public static vn.ai.trueid.lottie.model.content.Repeater parse(vn.ai.trueid.lottie.parser.moshi.JsonReader p8, vn.ai.trueid.lottie.LottieComposition p9)
    {
        String v3 = 0;
        vn.ai.trueid.lottie.model.animatable.AnimatableFloatValue v4 = 0;
        vn.ai.trueid.lottie.model.animatable.AnimatableFloatValue v5 = 0;
        vn.ai.trueid.lottie.model.animatable.AnimatableTransform v6 = 0;
        boolean v7 = 0;
        while (p8.hasNext()) {
            int v0_2 = p8.selectName(vn.ai.trueid.lottie.parser.RepeaterParser.NAMES);
            if (v0_2 == 0) {
                v3 = p8.nextString();
            } else {
                if (v0_2 == 1) {
                    v4 = vn.ai.trueid.lottie.parser.AnimatableValueParser.parseFloat(p8, p9, 0);
                } else {
                    if (v0_2 == 2) {
                        v5 = vn.ai.trueid.lottie.parser.AnimatableValueParser.parseFloat(p8, p9, 0);
                    } else {
                        if (v0_2 == 3) {
                            v6 = vn.ai.trueid.lottie.parser.AnimatableTransformParser.parse(p8, p9);
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
        return new vn.ai.trueid.lottie.model.content.Repeater(v3, v4, v5, v6, v7);
    }
}
