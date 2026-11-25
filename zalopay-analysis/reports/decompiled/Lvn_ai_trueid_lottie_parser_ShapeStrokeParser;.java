package vn.ai.trueid.lottie.parser;
 class ShapeStrokeParser {
    private static final vn.ai.trueid.lottie.parser.moshi.JsonReader$Options DASH_PATTERN_NAMES;
    private static final vn.ai.trueid.lottie.parser.moshi.JsonReader$Options NAMES;

    static ShapeStrokeParser()
    {
        wmoi.igob.ggcn(13866);
        String v8_1 = wmoi.igob.ggcn(13867);
        vn.ai.trueid.lottie.parser.moshi.JsonReader$Options v0_7 = wmoi.igob.ggcn(13868);
        wmoi.igob.ggcn(13869);
        wmoi.igob.ggcn(13870);
        wmoi.igob.ggcn(13871);
        wmoi.igob.ggcn(13872);
        wmoi.igob.ggcn(13873);
        wmoi.igob.ggcn(13874);
        vn.ai.trueid.lottie.parser.ShapeStrokeParser.NAMES = vn.ai.trueid.lottie.parser.moshi.JsonReader$Options.of(new String[] {v0_7, v8_1}));
        vn.ai.trueid.lottie.parser.ShapeStrokeParser.DASH_PATTERN_NAMES = vn.ai.trueid.lottie.parser.moshi.JsonReader$Options.of(new String[] {wmoi.igob.ggcn(13875), wmoi.igob.ggcn(13876)}));
        return;
    }

    private ShapeStrokeParser()
    {
        return;
    }

    public static vn.ai.trueid.lottie.model.content.ShapeStroke parse(vn.ai.trueid.lottie.parser.moshi.JsonReader p18, vn.ai.trueid.lottie.LottieComposition p19)
    {
        java.util.ArrayList v3_1 = new java.util.ArrayList();
        String v1 = 0;
        vn.ai.trueid.lottie.model.animatable.AnimatableIntegerValue v5_1 = 0;
        vn.ai.trueid.lottie.model.animatable.AnimatableFloatValue v6_1 = 0;
        vn.ai.trueid.lottie.model.animatable.AnimatableFloatValue v7_1 = 0;
        vn.ai.trueid.lottie.model.content.ShapeStroke$LineJoinType v8_0 = 0;
        boolean v10_0 = 0;
        int v12_0 = 0;
        float v9 = 0;
        boolean v11 = 0;
        int v2_0 = 0;
        while (p18.hasNext()) {
            switch (p18.selectName(vn.ai.trueid.lottie.parser.ShapeStrokeParser.NAMES)) {
                case 0:
                    v1 = p18.nextString();
                    break;
                case 1:
                    v8_0 = vn.ai.trueid.lottie.parser.AnimatableValueParser.parseColor(p18, p19);
                    break;
                case 2:
                    v10_0 = vn.ai.trueid.lottie.parser.AnimatableValueParser.parseFloat(p18, p19);
                    break;
                case 3:
                    v2_0 = vn.ai.trueid.lottie.parser.AnimatableValueParser.parseInteger(p18, p19);
                    break;
                case 4:
                    v5_1 = vn.ai.trueid.lottie.model.content.ShapeStroke$LineCapType.values()[(p18.nextInt() - 1)];
                    break;
                case 5:
                    v6_1 = vn.ai.trueid.lottie.model.content.ShapeStroke$LineJoinType.values()[(p18.nextInt() - 1)];
                    break;
                case 6:
                    v9 = ((float) p18.nextDouble());
                    break;
                case 7:
                    v11 = p18.nextBoolean();
                    break;
                case 8:
                    p18.beginArray();
                    while (p18.hasNext()) {
                        p18.beginObject();
                        String v13_0 = v12_0;
                        vn.ai.trueid.lottie.model.animatable.AnimatableFloatValue v15 = v13_0;
                        while (p18.hasNext()) {
                            int v12_2 = p18.selectName(vn.ai.trueid.lottie.parser.ShapeStrokeParser.DASH_PATTERN_NAMES);
                            if (v12_2 == 0) {
                                v13_0 = p18.nextString();
                            } else {
                                if (v12_2 == 1) {
                                    v15 = vn.ai.trueid.lottie.parser.AnimatableValueParser.parseFloat(p18, p19);
                                } else {
                                    p18.skipName();
                                    p18.skipValue();
                                }
                            }
                        }
                        p18.endObject();
                        v13_0.getClass();
                        int v17 = -1;
                        switch (v13_0.hashCode()) {
                            case 100:
                                if (v13_0.equals(wmoi.igob.ggcn(13879))) {
                                    v17 = 0;
                                } else {
                                }
                                break;
                            case 103:
                                if (v13_0.equals(wmoi.igob.ggcn(13878))) {
                                    v17 = 1;
                                } else {
                                }
                                break;
                            case 111:
                                if (v13_0.equals(wmoi.igob.ggcn(13877))) {
                                    v17 = 2;
                                } else {
                                }
                                break;
                            default:
                        }
                        switch (v17) {
                            case 0:
                            case 1:
                                p19.setHasDashPattern(1);
                                v3_1.add(v15);
                                break;
                            case 2:
                                v7_1 = v15;
                                break;
                            default:
                        }
                        v12_0 = 0;
                    }
                    p18.endArray();
                    if (v3_1.size() != 1) {
                    } else {
                        v3_1.add(((vn.ai.trueid.lottie.model.animatable.AnimatableFloatValue) v3_1.get(0)));
                    }
                    break;
                default:
                    p18.skipValue();
            }
            v12_0 = 0;
        }
        if (v2_0 == 0) {
            v2_0 = new vn.ai.trueid.lottie.model.animatable.AnimatableIntegerValue(java.util.Collections.singletonList(new vn.ai.trueid.lottie.value.Keyframe(Integer.valueOf(100))));
        }
        if (v5_1 == null) {
            v5_1 = vn.ai.trueid.lottie.model.content.ShapeStroke$LineCapType.BUTT;
        }
        if (v6_1 == null) {
            v6_1 = vn.ai.trueid.lottie.model.content.ShapeStroke$LineJoinType.MITER;
        }
        return new vn.ai.trueid.lottie.model.content.ShapeStroke(v1, v7_1, v3_1, v8_0, v2_0, v10_0, v5_1, v6_1, v9, v11);
    }
}
