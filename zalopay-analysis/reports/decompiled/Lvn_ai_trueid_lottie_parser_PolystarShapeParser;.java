package vn.ai.trueid.lottie.parser;
 class PolystarShapeParser {
    private static final vn.ai.trueid.lottie.parser.moshi.JsonReader$Options NAMES;

    static PolystarShapeParser()
    {
        String v2 = "pt";
        String v4 = "r";
        String v6 = "os";
        String v8 = "is";
        vn.ai.trueid.lottie.parser.PolystarShapeParser.NAMES = vn.ai.trueid.lottie.parser.moshi.JsonReader$Options.of(new String[] {"nm", "d"}));
        return;
    }

    private PolystarShapeParser()
    {
        return;
    }

    public static vn.ai.trueid.lottie.model.content.PolystarShape parse(vn.ai.trueid.lottie.parser.moshi.JsonReader p19, vn.ai.trueid.lottie.LottieComposition p20, int p21)
    {
        int v5_6;
        if (p21 != 3) {
            v5_6 = 0;
        } else {
            v5_6 = 1;
        }
        boolean v17 = 0;
        int v18 = v5_6;
        String v8 = 0;
        vn.ai.trueid.lottie.model.content.PolystarShape$Type v9 = 0;
        vn.ai.trueid.lottie.model.animatable.AnimatableFloatValue v10 = 0;
        vn.ai.trueid.lottie.model.animatable.AnimatableValue v11 = 0;
        vn.ai.trueid.lottie.model.animatable.AnimatableFloatValue v12 = 0;
        vn.ai.trueid.lottie.model.animatable.AnimatableFloatValue v13 = 0;
        vn.ai.trueid.lottie.model.animatable.AnimatableFloatValue v14 = 0;
        vn.ai.trueid.lottie.model.animatable.AnimatableFloatValue v15 = 0;
        vn.ai.trueid.lottie.model.animatable.AnimatableFloatValue v16 = 0;
        while (p19.hasNext()) {
            switch (p19.selectName(vn.ai.trueid.lottie.parser.PolystarShapeParser.NAMES)) {
                case 0:
                    v8 = p19.nextString();
                    break;
                case 1:
                    v9 = vn.ai.trueid.lottie.model.content.PolystarShape$Type.forValue(p19.nextInt());
                    break;
                case 2:
                    v10 = vn.ai.trueid.lottie.parser.AnimatableValueParser.parseFloat(p19, p20, 0);
                    break;
                case 3:
                    v11 = vn.ai.trueid.lottie.parser.AnimatablePathValueParser.parseSplitPath(p19, p20);
                    break;
                case 4:
                    v12 = vn.ai.trueid.lottie.parser.AnimatableValueParser.parseFloat(p19, p20, 0);
                    break;
                case 5:
                    v14 = vn.ai.trueid.lottie.parser.AnimatableValueParser.parseFloat(p19, p20);
                    break;
                case 6:
                    v16 = vn.ai.trueid.lottie.parser.AnimatableValueParser.parseFloat(p19, p20, 0);
                    break;
                case 7:
                    v13 = vn.ai.trueid.lottie.parser.AnimatableValueParser.parseFloat(p19, p20);
                    break;
                case 8:
                    v15 = vn.ai.trueid.lottie.parser.AnimatableValueParser.parseFloat(p19, p20, 0);
                    break;
                case 9:
                    v17 = p19.nextBoolean();
                    break;
                case 10:
                    if (p19.nextInt() != 3) {
                        v18 = 0;
                    } else {
                        v18 = 1;
                    }
                    break;
                default:
                    p19.skipName();
                    p19.skipValue();
            }
        }
        return new vn.ai.trueid.lottie.model.content.PolystarShape(v8, v9, v10, v11, v12, v13, v14, v15, v16, v17, v18);
    }
}
