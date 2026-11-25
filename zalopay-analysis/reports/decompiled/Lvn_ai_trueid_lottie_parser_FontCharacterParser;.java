package vn.ai.trueid.lottie.parser;
 class FontCharacterParser {
    private static final vn.ai.trueid.lottie.parser.moshi.JsonReader$Options DATA_NAMES;
    private static final vn.ai.trueid.lottie.parser.moshi.JsonReader$Options NAMES;

    static FontCharacterParser()
    {
        String v2 = "w";
        vn.ai.trueid.lottie.parser.FontCharacterParser.NAMES = vn.ai.trueid.lottie.parser.moshi.JsonReader$Options.of(new String[] {"ch", "data"}));
        vn.ai.trueid.lottie.parser.FontCharacterParser.DATA_NAMES = vn.ai.trueid.lottie.parser.moshi.JsonReader$Options.of(new String[] {"shapes"}));
        return;
    }

    private FontCharacterParser()
    {
        return;
    }

    public static vn.ai.trueid.lottie.model.FontCharacter parse(vn.ai.trueid.lottie.parser.moshi.JsonReader p11, vn.ai.trueid.lottie.LottieComposition p12)
    {
        java.util.ArrayList v1_1 = new java.util.ArrayList();
        p11.beginObject();
        double v5 = 0;
        String v7 = 0;
        String v8 = 0;
        char v2_1 = 0;
        double v3 = 0;
        while (p11.hasNext()) {
            vn.ai.trueid.lottie.model.content.ShapeGroup v9_2 = p11.selectName(vn.ai.trueid.lottie.parser.FontCharacterParser.NAMES);
            if (v9_2 == null) {
                v2_1 = p11.nextString().charAt(0);
            } else {
                if (v9_2 == 1) {
                    v3 = p11.nextDouble();
                } else {
                    if (v9_2 == 2) {
                        v5 = p11.nextDouble();
                    } else {
                        if (v9_2 == 3) {
                            v7 = p11.nextString();
                        } else {
                            if (v9_2 == 4) {
                                v8 = p11.nextString();
                            } else {
                                if (v9_2 == 5) {
                                    p11.beginObject();
                                    while (p11.hasNext()) {
                                        if (p11.selectName(vn.ai.trueid.lottie.parser.FontCharacterParser.DATA_NAMES) == 0) {
                                            p11.beginArray();
                                            while (p11.hasNext()) {
                                                v1_1.add(((vn.ai.trueid.lottie.model.content.ShapeGroup) vn.ai.trueid.lottie.parser.ContentModelParser.parse(p11, p12)));
                                            }
                                            p11.endArray();
                                        } else {
                                            p11.skipName();
                                            p11.skipValue();
                                        }
                                    }
                                    p11.endObject();
                                } else {
                                    p11.skipName();
                                    p11.skipValue();
                                }
                            }
                        }
                    }
                }
            }
        }
        p11.endObject();
        return new vn.ai.trueid.lottie.model.FontCharacter(v1_1, v2_1, v3, v5, v7, v8);
    }
}
