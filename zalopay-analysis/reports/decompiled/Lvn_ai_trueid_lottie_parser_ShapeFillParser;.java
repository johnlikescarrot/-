package vn.ai.trueid.lottie.parser;
 class ShapeFillParser {
    private static final vn.ai.trueid.lottie.parser.moshi.JsonReader$Options NAMES;

    static ShapeFillParser()
    {
        String v2 = "o";
        vn.ai.trueid.lottie.parser.ShapeFillParser.NAMES = vn.ai.trueid.lottie.parser.moshi.JsonReader$Options.of(new String[] {"nm", "hd"}));
        return;
    }

    private ShapeFillParser()
    {
        return;
    }

    public static vn.ai.trueid.lottie.model.content.ShapeFill parse(vn.ai.trueid.lottie.parser.moshi.JsonReader p10, vn.ai.trueid.lottie.LottieComposition p11)
    {
        vn.ai.trueid.lottie.model.animatable.AnimatableIntegerValue v0_0 = 0;
        String v4 = 0;
        vn.ai.trueid.lottie.model.animatable.AnimatableColorValue v7 = 0;
        boolean v5 = 0;
        boolean v9 = 0;
        int v1_1 = 1;
        while (p10.hasNext()) {
            vn.ai.trueid.lottie.model.content.ShapeFill v3_3 = p10.selectName(vn.ai.trueid.lottie.parser.ShapeFillParser.NAMES);
            if (v3_3 == null) {
                v4 = p10.nextString();
            } else {
                if (v3_3 == 1) {
                    v7 = vn.ai.trueid.lottie.parser.AnimatableValueParser.parseColor(p10, p11);
                } else {
                    if (v3_3 == 2) {
                        v0_0 = vn.ai.trueid.lottie.parser.AnimatableValueParser.parseInteger(p10, p11);
                    } else {
                        if (v3_3 == 3) {
                            v5 = p10.nextBoolean();
                        } else {
                            if (v3_3 == 4) {
                                v1_1 = p10.nextInt();
                            } else {
                                if (v3_3 == 5) {
                                    v9 = p10.nextBoolean();
                                } else {
                                    p10.skipName();
                                    p10.skipValue();
                                }
                            }
                        }
                    }
                }
            }
        }
        if (v0_0 == null) {
            v0_0 = new vn.ai.trueid.lottie.model.animatable.AnimatableIntegerValue(java.util.Collections.singletonList(new vn.ai.trueid.lottie.value.Keyframe(Integer.valueOf(100))));
        }
        android.graphics.Path$FillType v10_4;
        if (v1_1 != 1) {
            v10_4 = android.graphics.Path$FillType.EVEN_ODD;
        } else {
            v10_4 = android.graphics.Path$FillType.WINDING;
        }
        return new vn.ai.trueid.lottie.model.content.ShapeFill(v4, v5, v10_4, v7, v0_0, v9);
    }
}
