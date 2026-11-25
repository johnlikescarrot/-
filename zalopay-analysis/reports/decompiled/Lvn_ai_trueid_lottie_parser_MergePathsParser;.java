package vn.ai.trueid.lottie.parser;
 class MergePathsParser {
    private static final vn.ai.trueid.lottie.parser.moshi.JsonReader$Options NAMES;

    static MergePathsParser()
    {
        vn.ai.trueid.lottie.parser.MergePathsParser.NAMES = vn.ai.trueid.lottie.parser.moshi.JsonReader$Options.of(new String[] {"nm", "mm", "hd"}));
        return;
    }

    private MergePathsParser()
    {
        return;
    }

    public static vn.ai.trueid.lottie.model.content.MergePaths parse(vn.ai.trueid.lottie.parser.moshi.JsonReader p5)
    {
        String v0 = 0;
        boolean v2 = 0;
        vn.ai.trueid.lottie.model.content.MergePaths$MergePathsMode v1_2 = 0;
        while (p5.hasNext()) {
            int v3_1 = p5.selectName(vn.ai.trueid.lottie.parser.MergePathsParser.NAMES);
            if (v3_1 == 0) {
                v0 = p5.nextString();
            } else {
                if (v3_1 == 1) {
                    v1_2 = vn.ai.trueid.lottie.model.content.MergePaths$MergePathsMode.forId(p5.nextInt());
                } else {
                    if (v3_1 == 2) {
                        v2 = p5.nextBoolean();
                    } else {
                        p5.skipName();
                        p5.skipValue();
                    }
                }
            }
        }
        return new vn.ai.trueid.lottie.model.content.MergePaths(v0, v1_2, v2);
    }
}
