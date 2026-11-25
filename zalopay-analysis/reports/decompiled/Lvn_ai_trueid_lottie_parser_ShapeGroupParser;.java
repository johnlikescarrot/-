package vn.ai.trueid.lottie.parser;
 class ShapeGroupParser {
    private static final vn.ai.trueid.lottie.parser.moshi.JsonReader$Options NAMES;

    static ShapeGroupParser()
    {
        vn.ai.trueid.lottie.parser.ShapeGroupParser.NAMES = vn.ai.trueid.lottie.parser.moshi.JsonReader$Options.of(new String[] {"nm", "hd", "it"}));
        return;
    }

    private ShapeGroupParser()
    {
        return;
    }

    public static vn.ai.trueid.lottie.model.content.ShapeGroup parse(vn.ai.trueid.lottie.parser.moshi.JsonReader p5, vn.ai.trueid.lottie.LottieComposition p6)
    {
        java.util.ArrayList v0_1 = new java.util.ArrayList();
        String v1 = 0;
        boolean v2 = 0;
        while (p5.hasNext()) {
            vn.ai.trueid.lottie.model.content.ContentModel v3_1 = p5.selectName(vn.ai.trueid.lottie.parser.ShapeGroupParser.NAMES);
            if (v3_1 == null) {
                v1 = p5.nextString();
            } else {
                if (v3_1 == 1) {
                    v2 = p5.nextBoolean();
                } else {
                    if (v3_1 == 2) {
                        p5.beginArray();
                        while (p5.hasNext()) {
                            vn.ai.trueid.lottie.model.content.ContentModel v3_3 = vn.ai.trueid.lottie.parser.ContentModelParser.parse(p5, p6);
                            if (v3_3 != null) {
                                v0_1.add(v3_3);
                            }
                        }
                        p5.endArray();
                    } else {
                        p5.skipValue();
                    }
                }
            }
        }
        return new vn.ai.trueid.lottie.model.content.ShapeGroup(v1, v0_1, v2);
    }
}
