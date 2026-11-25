package vn.ai.trueid.lottie.parser;
 class KeyframesParser {
    static vn.ai.trueid.lottie.parser.moshi.JsonReader$Options NAMES;

    static KeyframesParser()
    {
        vn.ai.trueid.lottie.parser.KeyframesParser.NAMES = vn.ai.trueid.lottie.parser.moshi.JsonReader$Options.of(new String[] {wmoi.igob.ggcn(600)}));
        return;
    }

    private KeyframesParser()
    {
        return;
    }

    public static java.util.List parse(vn.ai.trueid.lottie.parser.moshi.JsonReader p9, vn.ai.trueid.lottie.LottieComposition p10, float p11, vn.ai.trueid.lottie.parser.ValueParser p12, boolean p13)
    {
        java.util.ArrayList v0_1 = new java.util.ArrayList();
        if (p9.peek() != vn.ai.trueid.lottie.parser.moshi.JsonReader$Token.STRING) {
            p9.beginObject();
            while (p9.hasNext()) {
                if (p9.selectName(vn.ai.trueid.lottie.parser.KeyframesParser.NAMES) == 0) {
                    if (p9.peek() != vn.ai.trueid.lottie.parser.moshi.JsonReader$Token.BEGIN_ARRAY) {
                        v0_1.add(vn.ai.trueid.lottie.parser.KeyframeParser.parse(p9, p10, p11, p12, 0, p13));
                    } else {
                        vn.ai.trueid.lottie.parser.ValueParser v4_1;
                        boolean v6_1;
                        vn.ai.trueid.lottie.LottieComposition v2_3;
                        vn.ai.trueid.lottie.value.Keyframe v1_7;
                        float v3_1;
                        p9.beginArray();
                        if (p9.peek() != vn.ai.trueid.lottie.parser.moshi.JsonReader$Token.NUMBER) {
                            v1_7 = p9;
                            v2_3 = p10;
                            v3_1 = p11;
                            v4_1 = p12;
                            v6_1 = p13;
                            while (p9.hasNext()) {
                                v0_1.add(vn.ai.trueid.lottie.parser.KeyframeParser.parse(p9, p10, p11, p12, 1, p13));
                            }
                        } else {
                            v1_7 = p9;
                            v2_3 = p10;
                            v3_1 = p11;
                            v4_1 = p12;
                            v6_1 = p13;
                            v0_1.add(vn.ai.trueid.lottie.parser.KeyframeParser.parse(p9, p10, p11, p12, 0, p13));
                        }
                        v1_7.endArray();
                        p9 = v1_7;
                        p10 = v2_3;
                        p11 = v3_1;
                        p12 = v4_1;
                        p13 = v6_1;
                    }
                } else {
                    p9.skipValue();
                }
            }
            p9.endObject();
            vn.ai.trueid.lottie.parser.KeyframesParser.setEndFrames(v0_1);
            return v0_1;
        } else {
            p10.addWarning(wmoi.igob.ggcn(601));
            return v0_1;
        }
    }

    public static void setEndFrames(java.util.List p5)
    {
        vn.ai.trueid.lottie.value.Keyframe v0_0 = p5.size();
        int v1_2 = 0;
        do {
            boolean v3_4 = (v0_0 - 1);
            if (v1_2 >= v3_4) {
                vn.ai.trueid.lottie.value.Keyframe v0_2 = ((vn.ai.trueid.lottie.value.Keyframe) p5.get(v3_4));
                if (((v0_2.startValue == null) || (v0_2.endValue == null)) && (p5.size() > 1)) {
                    p5.remove(v0_2);
                }
                return;
            } else {
                vn.ai.trueid.lottie.animation.keyframe.PathKeyframe v2_1 = ((vn.ai.trueid.lottie.value.Keyframe) p5.get(v1_2));
                v1_2++;
                boolean v3_1 = ((vn.ai.trueid.lottie.value.Keyframe) p5.get(v1_2));
                v2_1.endFrame = Float.valueOf(v3_1.startFrame);
                if (v2_1.endValue == null) {
                    boolean v3_2 = v3_1.startValue;
                    if (v3_2) {
                        v2_1.endValue = v3_2;
                    }
                }
            }
        } while(!(v2_1 instanceof vn.ai.trueid.lottie.animation.keyframe.PathKeyframe));
        ((vn.ai.trueid.lottie.animation.keyframe.PathKeyframe) v2_1).createPath();
    }
}
