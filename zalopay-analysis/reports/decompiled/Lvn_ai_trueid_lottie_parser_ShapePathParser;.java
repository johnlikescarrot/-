package vn.ai.trueid.lottie.parser;
 class ShapePathParser {
    static vn.ai.trueid.lottie.parser.moshi.JsonReader$Options NAMES;

    static ShapePathParser()
    {
        vn.ai.trueid.lottie.parser.ShapePathParser.NAMES = vn.ai.trueid.lottie.parser.moshi.JsonReader$Options.of(new String[] {"nm", "ind", "ks", "hd"}));
        return;
    }

    private ShapePathParser()
    {
        return;
    }

    public static vn.ai.trueid.lottie.model.content.ShapePath parse(vn.ai.trueid.lottie.parser.moshi.JsonReader p6, vn.ai.trueid.lottie.LottieComposition p7)
    {
        String v0 = 0;
        int v2 = 0;
        boolean v3 = 0;
        vn.ai.trueid.lottie.model.animatable.AnimatableShapeValue v1_1 = 0;
        while (p6.hasNext()) {
            int v4_1 = p6.selectName(vn.ai.trueid.lottie.parser.ShapePathParser.NAMES);
            if (v4_1 == 0) {
                v0 = p6.nextString();
            } else {
                if (v4_1 == 1) {
                    v2 = p6.nextInt();
                } else {
                    if (v4_1 == 2) {
                        v1_1 = vn.ai.trueid.lottie.parser.AnimatableValueParser.parseShapeData(p6, p7);
                    } else {
                        if (v4_1 == 3) {
                            v3 = p6.nextBoolean();
                        } else {
                            p6.skipValue();
                        }
                    }
                }
            }
        }
        return new vn.ai.trueid.lottie.model.content.ShapePath(v0, v2, v1_1, v3);
    }
}
