package vn.ai.trueid.lottie.parser;
 class PathKeyframeParser {

    private PathKeyframeParser()
    {
        return;
    }

    public static vn.ai.trueid.lottie.animation.keyframe.PathKeyframe parse(vn.ai.trueid.lottie.parser.moshi.JsonReader p7, vn.ai.trueid.lottie.LottieComposition p8)
    {
        int v0_1;
        if (p7.peek() != vn.ai.trueid.lottie.parser.moshi.JsonReader$Token.BEGIN_OBJECT) {
            v0_1 = 0;
        } else {
            v0_1 = 1;
        }
        return new vn.ai.trueid.lottie.animation.keyframe.PathKeyframe(p8, vn.ai.trueid.lottie.parser.KeyframeParser.parse(p7, p8, vn.ai.trueid.lottie.utils.Utils.dpScale(), vn.ai.trueid.lottie.parser.PathParser.INSTANCE, v0_1, 0));
    }
}
