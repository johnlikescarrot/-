package vn.ai.trueid.lottie.parser;
 class BlurEffectParser {
    private static final vn.ai.trueid.lottie.parser.moshi.JsonReader$Options BLUR_EFFECT_NAMES;
    private static final vn.ai.trueid.lottie.parser.moshi.JsonReader$Options INNER_BLUR_EFFECT_NAMES;

    static BlurEffectParser()
    {
        vn.ai.trueid.lottie.parser.BlurEffectParser.BLUR_EFFECT_NAMES = vn.ai.trueid.lottie.parser.moshi.JsonReader$Options.of(new String[] {"ef"}));
        vn.ai.trueid.lottie.parser.BlurEffectParser.INNER_BLUR_EFFECT_NAMES = vn.ai.trueid.lottie.parser.moshi.JsonReader$Options.of(new String[] {"ty", "v"}));
        return;
    }

    public BlurEffectParser()
    {
        return;
    }

    private static vn.ai.trueid.lottie.model.content.BlurEffect maybeParseInnerEffect(vn.ai.trueid.lottie.parser.moshi.JsonReader p5, vn.ai.trueid.lottie.LottieComposition p6)
    {
        p5.beginObject();
        vn.ai.trueid.lottie.model.content.BlurEffect v0_0 = 0;
        do {
            int v2_1 = 0;
            if (!p5.hasNext()) {
                p5.endObject();
                return v0_0;
            } else {
                vn.ai.trueid.lottie.model.animatable.AnimatableFloatValue v3_0 = p5.selectName(vn.ai.trueid.lottie.parser.BlurEffectParser.INNER_BLUR_EFFECT_NAMES);
                if (v3_0 == null) {
                } else {
                    if (v3_0 == 1) {
                        if (v2_1 == 0) {
                            p5.skipValue();
                            while (p5.hasNext()) {
                            }
                        } else {
                            v0_0 = new vn.ai.trueid.lottie.model.content.BlurEffect(vn.ai.trueid.lottie.parser.AnimatableValueParser.parseFloat(p5, p6));
                        }
                    } else {
                        p5.skipName();
                        p5.skipValue();
                    }
                }
                p5.endObject();
                return v0_0;
            }
        } while(p5.nextInt() != 0);
        v2_1 = 1;
    }

    public static vn.ai.trueid.lottie.model.content.BlurEffect parse(vn.ai.trueid.lottie.parser.moshi.JsonReader p2, vn.ai.trueid.lottie.LottieComposition p3)
    {
        vn.ai.trueid.lottie.model.content.BlurEffect v0 = 0;
        while (p2.hasNext()) {
            if (p2.selectName(vn.ai.trueid.lottie.parser.BlurEffectParser.BLUR_EFFECT_NAMES) == 0) {
                p2.beginArray();
                while (p2.hasNext()) {
                    vn.ai.trueid.lottie.model.content.BlurEffect v1_1 = vn.ai.trueid.lottie.parser.BlurEffectParser.maybeParseInnerEffect(p2, p3);
                    if (v1_1 != null) {
                        v0 = v1_1;
                    }
                }
                p2.endArray();
            } else {
                p2.skipName();
                p2.skipValue();
            }
        }
        return v0;
    }
}
