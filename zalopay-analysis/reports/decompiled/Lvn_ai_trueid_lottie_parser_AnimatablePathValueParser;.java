package vn.ai.trueid.lottie.parser;
public class AnimatablePathValueParser {
    private static final vn.ai.trueid.lottie.parser.moshi.JsonReader$Options NAMES;

    static AnimatablePathValueParser()
    {
        vn.ai.trueid.lottie.parser.AnimatablePathValueParser.NAMES = vn.ai.trueid.lottie.parser.moshi.JsonReader$Options.of(new String[] {"k", "x", "y"}));
        return;
    }

    private AnimatablePathValueParser()
    {
        return;
    }

    public static vn.ai.trueid.lottie.model.animatable.AnimatablePathValue parse(vn.ai.trueid.lottie.parser.moshi.JsonReader p3, vn.ai.trueid.lottie.LottieComposition p4)
    {
        java.util.ArrayList v0_1 = new java.util.ArrayList();
        if (p3.peek() != vn.ai.trueid.lottie.parser.moshi.JsonReader$Token.BEGIN_ARRAY) {
            v0_1.add(new vn.ai.trueid.lottie.value.Keyframe(vn.ai.trueid.lottie.parser.JsonUtils.jsonToPoint(p3, vn.ai.trueid.lottie.utils.Utils.dpScale())));
        } else {
            p3.beginArray();
            while (p3.hasNext()) {
                v0_1.add(vn.ai.trueid.lottie.parser.PathKeyframeParser.parse(p3, p4));
            }
            p3.endArray();
            vn.ai.trueid.lottie.parser.KeyframesParser.setEndFrames(v0_1);
        }
        return new vn.ai.trueid.lottie.model.animatable.AnimatablePathValue(v0_1);
    }

    public static vn.ai.trueid.lottie.model.animatable.AnimatableValue parseSplitPath(vn.ai.trueid.lottie.parser.moshi.JsonReader p7, vn.ai.trueid.lottie.LottieComposition p8)
    {
        p7.beginObject();
        vn.ai.trueid.lottie.model.animatable.AnimatablePathValue v0 = 0;
        vn.ai.trueid.lottie.model.animatable.AnimatableFloatValue v2 = 0;
        int v3 = 0;
        vn.ai.trueid.lottie.model.animatable.AnimatableFloatValue v1_1 = 0;
        while (p7.peek() != vn.ai.trueid.lottie.parser.moshi.JsonReader$Token.END_OBJECT) {
            vn.ai.trueid.lottie.parser.moshi.JsonReader$Token v4_1 = p7.selectName(vn.ai.trueid.lottie.parser.AnimatablePathValueParser.NAMES);
            if (v4_1 == null) {
                v0 = vn.ai.trueid.lottie.parser.AnimatablePathValueParser.parse(p7, p8);
            } else {
                if (v4_1 == 1) {
                    if (p7.peek() != vn.ai.trueid.lottie.parser.moshi.JsonReader$Token.STRING) {
                        v1_1 = vn.ai.trueid.lottie.parser.AnimatableValueParser.parseFloat(p7, p8);
                    } else {
                        p7.skipValue();
                    }
                } else {
                    if (v4_1 == 2) {
                        if (p7.peek() != vn.ai.trueid.lottie.parser.moshi.JsonReader$Token.STRING) {
                            v2 = vn.ai.trueid.lottie.parser.AnimatableValueParser.parseFloat(p7, p8);
                        } else {
                            p7.skipValue();
                        }
                    } else {
                        p7.skipName();
                        p7.skipValue();
                    }
                }
                v3 = 1;
            }
        }
        p7.endObject();
        if (v3 != 0) {
            p8.addWarning("Lottie doesn\'t support expressions.");
        }
        if (v0 == null) {
            return new vn.ai.trueid.lottie.model.animatable.AnimatableSplitDimensionPathValue(v1_1, v2);
        } else {
            return v0;
        }
    }
}
