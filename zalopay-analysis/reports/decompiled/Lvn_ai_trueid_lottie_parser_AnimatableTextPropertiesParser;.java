package vn.ai.trueid.lottie.parser;
public class AnimatableTextPropertiesParser {
    private static final vn.ai.trueid.lottie.parser.moshi.JsonReader$Options ANIMATABLE_PROPERTIES_NAMES;
    private static final vn.ai.trueid.lottie.parser.moshi.JsonReader$Options ANIMATABLE_RANGE_PROPERTIES_NAMES;
    private static final vn.ai.trueid.lottie.parser.moshi.JsonReader$Options PROPERTIES_NAMES;

    static AnimatableTextPropertiesParser()
    {
        vn.ai.trueid.lottie.parser.AnimatableTextPropertiesParser.PROPERTIES_NAMES = vn.ai.trueid.lottie.parser.moshi.JsonReader$Options.of(new String[] {"s", "a"}));
        vn.ai.trueid.lottie.parser.AnimatableTextPropertiesParser.ANIMATABLE_RANGE_PROPERTIES_NAMES = vn.ai.trueid.lottie.parser.moshi.JsonReader$Options.of(new String[] {"s", "e", "o", "r"}));
        vn.ai.trueid.lottie.parser.AnimatableTextPropertiesParser.ANIMATABLE_PROPERTIES_NAMES = vn.ai.trueid.lottie.parser.moshi.JsonReader$Options.of(new String[] {"fc", "sc", "sw", "t", "o"}));
        return;
    }

    private AnimatableTextPropertiesParser()
    {
        return;
    }

    public static vn.ai.trueid.lottie.model.animatable.AnimatableTextProperties parse(vn.ai.trueid.lottie.parser.moshi.JsonReader p4, vn.ai.trueid.lottie.LottieComposition p5)
    {
        p4.beginObject();
        vn.ai.trueid.lottie.model.animatable.AnimatableTextStyle v0 = 0;
        vn.ai.trueid.lottie.model.animatable.AnimatableTextRangeSelector v1 = 0;
        while (p4.hasNext()) {
            int v2_1 = p4.selectName(vn.ai.trueid.lottie.parser.AnimatableTextPropertiesParser.PROPERTIES_NAMES);
            if (v2_1 == 0) {
                v1 = vn.ai.trueid.lottie.parser.AnimatableTextPropertiesParser.parseAnimatableTextRangeSelector(p4, p5);
            } else {
                if (v2_1 == 1) {
                    v0 = vn.ai.trueid.lottie.parser.AnimatableTextPropertiesParser.parseAnimatableTextStyle(p4, p5);
                } else {
                    p4.skipName();
                    p4.skipValue();
                }
            }
        }
        p4.endObject();
        return new vn.ai.trueid.lottie.model.animatable.AnimatableTextProperties(v0, v1);
    }

    private static vn.ai.trueid.lottie.model.animatable.AnimatableTextRangeSelector parseAnimatableTextRangeSelector(vn.ai.trueid.lottie.parser.moshi.JsonReader p8, vn.ai.trueid.lottie.LottieComposition p9)
    {
        p8.beginObject();
        vn.ai.trueid.lottie.model.animatable.AnimatableIntegerValue v0_0 = 0;
        vn.ai.trueid.lottie.model.animatable.AnimatableIntegerValue v1 = 0;
        vn.ai.trueid.lottie.model.animatable.AnimatableIntegerValue v2 = 0;
        vn.ai.trueid.lottie.model.content.TextRangeUnits v3_0 = 0;
        while (p8.hasNext()) {
            StringBuilder v4_1 = p8.selectName(vn.ai.trueid.lottie.parser.AnimatableTextPropertiesParser.ANIMATABLE_RANGE_PROPERTIES_NAMES);
            if (v4_1 == null) {
                v0_0 = vn.ai.trueid.lottie.parser.AnimatableValueParser.parseInteger(p8, p9);
            } else {
                if (v4_1 == 1) {
                    v1 = vn.ai.trueid.lottie.parser.AnimatableValueParser.parseInteger(p8, p9);
                } else {
                    if (v4_1 == 2) {
                        v2 = vn.ai.trueid.lottie.parser.AnimatableValueParser.parseInteger(p8, p9);
                    } else {
                        if (v4_1 == 3) {
                            vn.ai.trueid.lottie.model.content.TextRangeUnits v3_1 = p8.nextInt();
                            if ((v3_1 == 1) || (v3_1 == 2)) {
                                if (v3_1 != 1) {
                                    v3_0 = vn.ai.trueid.lottie.model.content.TextRangeUnits.INDEX;
                                } else {
                                    v3_0 = vn.ai.trueid.lottie.model.content.TextRangeUnits.PERCENT;
                                }
                            } else {
                                StringBuilder v4_3 = new StringBuilder("Unsupported text range units: ");
                                v4_3.append(v3_1);
                                p9.addWarning(v4_3.toString());
                                v3_0 = vn.ai.trueid.lottie.model.content.TextRangeUnits.INDEX;
                            }
                        } else {
                            p8.skipName();
                            p8.skipValue();
                        }
                    }
                }
            }
        }
        p8.endObject();
        if ((v0_0 == null) && (v1 != null)) {
            v0_0 = new vn.ai.trueid.lottie.model.animatable.AnimatableIntegerValue(java.util.Collections.singletonList(new vn.ai.trueid.lottie.value.Keyframe(Integer.valueOf(0))));
        }
        return new vn.ai.trueid.lottie.model.animatable.AnimatableTextRangeSelector(v0_0, v1, v2, v3_0);
    }

    private static vn.ai.trueid.lottie.model.animatable.AnimatableTextStyle parseAnimatableTextStyle(vn.ai.trueid.lottie.parser.moshi.JsonReader p7, vn.ai.trueid.lottie.LottieComposition p8)
    {
        p7.beginObject();
        vn.ai.trueid.lottie.model.animatable.AnimatableColorValue v2 = 0;
        vn.ai.trueid.lottie.model.animatable.AnimatableColorValue v3 = 0;
        vn.ai.trueid.lottie.model.animatable.AnimatableFloatValue v4 = 0;
        vn.ai.trueid.lottie.model.animatable.AnimatableFloatValue v5 = 0;
        vn.ai.trueid.lottie.model.animatable.AnimatableIntegerValue v6 = 0;
        while (p7.hasNext()) {
            int v0_2 = p7.selectName(vn.ai.trueid.lottie.parser.AnimatableTextPropertiesParser.ANIMATABLE_PROPERTIES_NAMES);
            if (v0_2 == 0) {
                v2 = vn.ai.trueid.lottie.parser.AnimatableValueParser.parseColor(p7, p8);
            } else {
                if (v0_2 == 1) {
                    v3 = vn.ai.trueid.lottie.parser.AnimatableValueParser.parseColor(p7, p8);
                } else {
                    if (v0_2 == 2) {
                        v4 = vn.ai.trueid.lottie.parser.AnimatableValueParser.parseFloat(p7, p8);
                    } else {
                        if (v0_2 == 3) {
                            v5 = vn.ai.trueid.lottie.parser.AnimatableValueParser.parseFloat(p7, p8);
                        } else {
                            if (v0_2 == 4) {
                                v6 = vn.ai.trueid.lottie.parser.AnimatableValueParser.parseInteger(p7, p8);
                            } else {
                                p7.skipName();
                                p7.skipValue();
                            }
                        }
                    }
                }
            }
        }
        p7.endObject();
        return new vn.ai.trueid.lottie.model.animatable.AnimatableTextStyle(v2, v3, v4, v5, v6);
    }
}
