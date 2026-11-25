package vn.ai.trueid.lottie.parser;
 class GradientStrokeParser {
    private static final vn.ai.trueid.lottie.parser.moshi.JsonReader$Options DASH_PATTERN_NAMES;
    private static final vn.ai.trueid.lottie.parser.moshi.JsonReader$Options GRADIENT_NAMES;
    private static final vn.ai.trueid.lottie.parser.moshi.JsonReader$Options NAMES;

    static GradientStrokeParser()
    {
        String v2 = "o";
        String v4 = "s";
        String v6 = "w";
        String v8 = "lj";
        vn.ai.trueid.lottie.parser.GradientStrokeParser.NAMES = vn.ai.trueid.lottie.parser.moshi.JsonReader$Options.of(new String[] {"nm", "d"}));
        vn.ai.trueid.lottie.parser.GradientStrokeParser.GRADIENT_NAMES = vn.ai.trueid.lottie.parser.moshi.JsonReader$Options.of(new String[] {"p", "k"}));
        vn.ai.trueid.lottie.parser.GradientStrokeParser.DASH_PATTERN_NAMES = vn.ai.trueid.lottie.parser.moshi.JsonReader$Options.of(new String[] {"n", "v"}));
        return;
    }

    private GradientStrokeParser()
    {
        return;
    }

    public static vn.ai.trueid.lottie.model.content.GradientStroke parse(vn.ai.trueid.lottie.parser.moshi.JsonReader p22, vn.ai.trueid.lottie.LottieComposition p23)
    {
        java.util.ArrayList v11_1 = new java.util.ArrayList();
        vn.ai.trueid.lottie.model.animatable.AnimatableFloatValue v2_0 = 0;
        vn.ai.trueid.lottie.model.animatable.AnimatablePointValue v5_0 = 0;
        vn.ai.trueid.lottie.model.animatable.AnimatableGradientColorValue v6_0 = 0;
        vn.ai.trueid.lottie.model.animatable.AnimatableFloatValue v7_0 = 0;
        vn.ai.trueid.lottie.model.content.ShapeStroke$LineCapType v8_0 = 0;
        vn.ai.trueid.lottie.model.content.ShapeStroke$LineJoinType v9_0 = 0;
        int v12_0 = 0;
        boolean v13_0 = 0;
        int v14 = 0;
        float v10 = 0;
        boolean v15 = 0;
        int v3_1 = 0;
        while (p22.hasNext()) {
            int v21_1;
            int v18_1 = v2_0;
            switch (p22.selectName(vn.ai.trueid.lottie.parser.GradientStrokeParser.NAMES)) {
                case 0:
                    v5_0 = p22.nextString();
                    break;
                case 1:
                    v21_1 = v3_1;
                    p22.beginObject();
                    int v3_7 = -1;
                    while (p22.hasNext()) {
                        String v4_7 = p22.selectName(vn.ai.trueid.lottie.parser.GradientStrokeParser.GRADIENT_NAMES);
                        if (v4_7 == null) {
                            v3_7 = p22.nextInt();
                        } else {
                            if (v4_7 == 1) {
                                v6_0 = vn.ai.trueid.lottie.parser.AnimatableValueParser.parseGradientColor(p22, p23, v3_7);
                            } else {
                                p22.skipName();
                                p22.skipValue();
                            }
                        }
                    }
                    p22.endObject();
                    v2_0 = v18_1;
                    v3_1 = v21_1;
                    break;
                case 2:
                    v3_1 = vn.ai.trueid.lottie.parser.AnimatableValueParser.parseInteger(p22, p23);
                    break;
                case 3:
                    int v3_6;
                    v21_1 = v3_1;
                    if (p22.nextInt() != 1) {
                        v3_6 = vn.ai.trueid.lottie.model.content.GradientType.RADIAL;
                    } else {
                        v3_6 = vn.ai.trueid.lottie.model.content.GradientType.LINEAR;
                    }
                    v2_0 = v3_6;
                    break;
                case 4:
                    vn.ai.trueid.lottie.model.animatable.AnimatableFloatValue v2 = 0;
                    v7_0 = vn.ai.trueid.lottie.parser.AnimatableValueParser.parsePoint(p22, p23);
                    break;
                case 5:
                    v2 = 0;
                    v8_0 = vn.ai.trueid.lottie.parser.AnimatableValueParser.parsePoint(p22, p23);
                    break;
                case 6:
                    v2 = 0;
                    v9_0 = vn.ai.trueid.lottie.parser.AnimatableValueParser.parseFloat(p22, p23);
                    break;
                case 7:
                    v21_1 = v3_1;
                    v12_0 = vn.ai.trueid.lottie.model.content.ShapeStroke$LineCapType.values()[(p22.nextInt() - 1)];
                    break;
                case 8:
                    v21_1 = v3_1;
                    v13_0 = vn.ai.trueid.lottie.model.content.ShapeStroke$LineJoinType.values()[(p22.nextInt() - 1)];
                    break;
                case 9:
                    v21_1 = v3_1;
                    v10 = ((float) p22.nextDouble());
                    break;
                case 10:
                    v2 = 0;
                    v15 = p22.nextBoolean();
                    break;
                case 11:
                    p22.beginArray();
                    while (p22.hasNext()) {
                        p22.beginObject();
                        String v4_0 = 0;
                        vn.ai.trueid.lottie.model.animatable.AnimatableFloatValue v19 = 0;
                        while (p22.hasNext()) {
                            vn.ai.trueid.lottie.model.animatable.AnimatableFloatValue v2_13 = p22.selectName(vn.ai.trueid.lottie.parser.GradientStrokeParser.DASH_PATTERN_NAMES);
                            if (v2_13 == null) {
                                v4_0 = p22.nextString();
                            } else {
                                int v21_0 = v3_1;
                                if (v2_13 == 1) {
                                    v19 = vn.ai.trueid.lottie.parser.AnimatableValueParser.parseFloat(p22, p23);
                                } else {
                                    p22.skipName();
                                    p22.skipValue();
                                }
                                v3_1 = v21_0;
                            }
                        }
                        int v21_3 = v3_1;
                        p22.endObject();
                        if (!v4_0.equals("o")) {
                            if ((v4_0.equals("d")) || (v4_0.equals("g"))) {
                                p23.setHasDashPattern(1);
                                v11_1.add(v19);
                            }
                        } else {
                            v14 = v19;
                        }
                        v3_1 = v21_3;
                    }
                    v21_1 = v3_1;
                    p22.endArray();
                    if (v11_1.size() != 1) {
                    } else {
                        v11_1.add(((vn.ai.trueid.lottie.model.animatable.AnimatableFloatValue) v11_1.get(0)));
                    }
                    break;
                default:
                    p22.skipName();
                    p22.skipValue();
            }
            v2_0 = v18_1;
        }
        String v4_8;
        int v21_2 = v3_1;
        if (v21_2 != 0) {
            v4_8 = v21_2;
        } else {
            v4_8 = new vn.ai.trueid.lottie.model.animatable.AnimatableIntegerValue(java.util.Collections.singletonList(new vn.ai.trueid.lottie.value.Keyframe(Integer.valueOf(100))));
        }
        return new vn.ai.trueid.lottie.model.content.GradientStroke(v5_0, v2_0, v6_0, v4_8, v7_0, v8_0, v9_0, v12_0, v13_0, v10, v11_1, v14, v15);
    }
}
