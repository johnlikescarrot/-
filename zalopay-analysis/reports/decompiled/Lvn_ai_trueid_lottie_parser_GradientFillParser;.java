package vn.ai.trueid.lottie.parser;
 class GradientFillParser {
    private static final vn.ai.trueid.lottie.parser.moshi.JsonReader$Options GRADIENT_NAMES;
    private static final vn.ai.trueid.lottie.parser.moshi.JsonReader$Options NAMES;

    static GradientFillParser()
    {
        String v2 = "o";
        String v4 = "s";
        vn.ai.trueid.lottie.parser.GradientFillParser.NAMES = vn.ai.trueid.lottie.parser.moshi.JsonReader$Options.of(new String[] {"nm", "hd"}));
        vn.ai.trueid.lottie.parser.GradientFillParser.GRADIENT_NAMES = vn.ai.trueid.lottie.parser.moshi.JsonReader$Options.of(new String[] {"p", "k"}));
        return;
    }

    private GradientFillParser()
    {
        return;
    }

    public static vn.ai.trueid.lottie.model.content.GradientFill parse(vn.ai.trueid.lottie.parser.moshi.JsonReader p14, vn.ai.trueid.lottie.LottieComposition p15)
    {
        vn.ai.trueid.lottie.model.animatable.AnimatableIntegerValue v1_0 = 0;
        android.graphics.Path$FillType v6 = android.graphics.Path$FillType.WINDING;
        String v4 = 0;
        android.graphics.Path$FillType v5 = 0;
        vn.ai.trueid.lottie.model.animatable.AnimatableGradientColorValue v7 = 0;
        vn.ai.trueid.lottie.model.animatable.AnimatablePointValue v9 = 0;
        vn.ai.trueid.lottie.model.animatable.AnimatablePointValue v10 = 0;
        boolean v13 = 0;
        while (p14.hasNext()) {
            switch (p14.selectName(vn.ai.trueid.lottie.parser.GradientFillParser.NAMES)) {
                case 0:
                    v4 = p14.nextString();
                    break;
                case 1:
                    p14.beginObject();
                    int v0_8 = -1;
                    while (p14.hasNext()) {
                        int v3_4 = p14.selectName(vn.ai.trueid.lottie.parser.GradientFillParser.GRADIENT_NAMES);
                        if (v3_4 == 0) {
                            v0_8 = p14.nextInt();
                        } else {
                            if (v3_4 == 1) {
                                v7 = vn.ai.trueid.lottie.parser.AnimatableValueParser.parseGradientColor(p14, p15, v0_8);
                            } else {
                                p14.skipName();
                                p14.skipValue();
                            }
                        }
                    }
                    p14.endObject();
                    break;
                case 2:
                    v1_0 = vn.ai.trueid.lottie.parser.AnimatableValueParser.parseInteger(p14, p15);
                    break;
                case 3:
                    int v0_7;
                    if (p14.nextInt() != 1) {
                        v0_7 = vn.ai.trueid.lottie.model.content.GradientType.RADIAL;
                    } else {
                        v0_7 = vn.ai.trueid.lottie.model.content.GradientType.LINEAR;
                    }
                    v5 = v0_7;
                    break;
                case 4:
                    v9 = vn.ai.trueid.lottie.parser.AnimatableValueParser.parsePoint(p14, p15);
                    break;
                case 5:
                    v10 = vn.ai.trueid.lottie.parser.AnimatableValueParser.parsePoint(p14, p15);
                    break;
                case 6:
                    int v0_5;
                    if (p14.nextInt() != 1) {
                        v0_5 = android.graphics.Path$FillType.EVEN_ODD;
                    } else {
                        v0_5 = android.graphics.Path$FillType.WINDING;
                    }
                    v6 = v0_5;
                    break;
                case 7:
                    v13 = p14.nextBoolean();
                    break;
                default:
                    p14.skipName();
                    p14.skipValue();
            }
        }
        if (v1_0 == null) {
            v1_0 = new vn.ai.trueid.lottie.model.animatable.AnimatableIntegerValue(java.util.Collections.singletonList(new vn.ai.trueid.lottie.value.Keyframe(Integer.valueOf(100))));
        }
        return new vn.ai.trueid.lottie.model.content.GradientFill(v4, v5, v6, v7, v1_0, v9, v10, 0, 0, v13);
    }
}
