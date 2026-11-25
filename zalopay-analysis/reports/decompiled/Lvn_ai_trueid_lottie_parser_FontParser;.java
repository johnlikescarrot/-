package vn.ai.trueid.lottie.parser;
 class FontParser {
    private static final vn.ai.trueid.lottie.parser.moshi.JsonReader$Options NAMES;

    static FontParser()
    {
        vn.ai.trueid.lottie.parser.FontParser.NAMES = vn.ai.trueid.lottie.parser.moshi.JsonReader$Options.of(new String[] {"fFamily", "fName", "fStyle", "ascent"}));
        return;
    }

    private FontParser()
    {
        return;
    }

    public static vn.ai.trueid.lottie.model.Font parse(vn.ai.trueid.lottie.parser.moshi.JsonReader p6)
    {
        p6.beginObject();
        String v0 = 0;
        String v2 = 0;
        float v3_1 = 0;
        String v1_1 = 0;
        while (p6.hasNext()) {
            int v4_1 = p6.selectName(vn.ai.trueid.lottie.parser.FontParser.NAMES);
            if (v4_1 == 0) {
                v0 = p6.nextString();
            } else {
                if (v4_1 == 1) {
                    v1_1 = p6.nextString();
                } else {
                    if (v4_1 == 2) {
                        v2 = p6.nextString();
                    } else {
                        if (v4_1 == 3) {
                            v3_1 = ((float) p6.nextDouble());
                        } else {
                            p6.skipName();
                            p6.skipValue();
                        }
                    }
                }
            }
        }
        p6.endObject();
        return new vn.ai.trueid.lottie.model.Font(v0, v1_1, v2, v3_1);
    }
}
