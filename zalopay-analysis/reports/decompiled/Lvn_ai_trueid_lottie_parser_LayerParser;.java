package vn.ai.trueid.lottie.parser;
public class LayerParser {
    private static final vn.ai.trueid.lottie.parser.moshi.JsonReader$Options EFFECTS_NAMES;
    private static final vn.ai.trueid.lottie.parser.moshi.JsonReader$Options NAMES;
    private static final vn.ai.trueid.lottie.parser.moshi.JsonReader$Options TEXT_NAMES;

    static LayerParser()
    {
        wmoi.igob.ggcn(6276);
        String v25_1 = wmoi.igob.ggcn(6277);
        String v1_1 = wmoi.igob.ggcn(6278);
        wmoi.igob.ggcn(6279);
        wmoi.igob.ggcn(6280);
        wmoi.igob.ggcn(6281);
        wmoi.igob.ggcn(6282);
        wmoi.igob.ggcn(6283);
        wmoi.igob.ggcn(6284);
        wmoi.igob.ggcn(6285);
        wmoi.igob.ggcn(6286);
        wmoi.igob.ggcn(6287);
        wmoi.igob.ggcn(6288);
        wmoi.igob.ggcn(6289);
        wmoi.igob.ggcn(6290);
        wmoi.igob.ggcn(6291);
        wmoi.igob.ggcn(6292);
        wmoi.igob.ggcn(6293);
        wmoi.igob.ggcn(6294);
        wmoi.igob.ggcn(6295);
        wmoi.igob.ggcn(6296);
        wmoi.igob.ggcn(6297);
        wmoi.igob.ggcn(6298);
        wmoi.igob.ggcn(6299);
        wmoi.igob.ggcn(6300);
        vn.ai.trueid.lottie.parser.LayerParser.NAMES = vn.ai.trueid.lottie.parser.moshi.JsonReader$Options.of(new String[] {v1_1, v25_1}));
        vn.ai.trueid.lottie.parser.LayerParser.TEXT_NAMES = vn.ai.trueid.lottie.parser.moshi.JsonReader$Options.of(new String[] {wmoi.igob.ggcn(6301), wmoi.igob.ggcn(6302)}));
        vn.ai.trueid.lottie.parser.LayerParser.EFFECTS_NAMES = vn.ai.trueid.lottie.parser.moshi.JsonReader$Options.of(new String[] {wmoi.igob.ggcn(6303), wmoi.igob.ggcn(6304)}));
        return;
    }

    private LayerParser()
    {
        return;
    }

    public static vn.ai.trueid.lottie.model.layer.Layer parse(vn.ai.trueid.lottie.LottieComposition p29)
    {
        float v0_0 = p29.getBounds();
        java.util.List v2 = java.util.Collections.EMPTY_LIST;
        return new vn.ai.trueid.lottie.model.layer.Layer(v2, p29, wmoi.igob.ggcn(6305), -1, vn.ai.trueid.lottie.model.layer.Layer$LayerType.PRE_COMP, -1, 0, v2, new vn.ai.trueid.lottie.model.animatable.AnimatableTransform(), 0, 0, 0, 0, 0, ((float) v0_0.width()), ((float) v0_0.height()), 0, 0, v2, vn.ai.trueid.lottie.model.layer.Layer$MatteType.NONE, 0, 0, 0, 0, vn.ai.trueid.lottie.model.content.LBlendMode.NORMAL);
    }

    public static vn.ai.trueid.lottie.model.layer.Layer parse(vn.ai.trueid.lottie.parser.moshi.JsonReader p40, vn.ai.trueid.lottie.LottieComposition p41)
    {
        vn.ai.trueid.lottie.LottieComposition v2_1 = Float.valueOf(0);
        Float v8 = Float.valueOf(1065353216);
        java.util.ArrayList v10_1 = new java.util.ArrayList();
        String v9_2 = new java.util.ArrayList();
        p40.beginObject();
        int v14_3 = 0;
        vn.ai.trueid.lottie.model.layer.Layer$MatteType v22 = vn.ai.trueid.lottie.model.layer.Layer$MatteType.NONE;
        vn.ai.trueid.lottie.model.content.LBlendMode v27 = vn.ai.trueid.lottie.model.content.LBlendMode.NORMAL;
        int v21_1 = 0;
        boolean v24_1 = 0;
        float v33 = 0;
        float v34 = 0;
        float v35 = 0;
        float v18_1 = v8;
        float v17_1 = 0;
        vn.ai.trueid.lottie.model.animatable.AnimatableTextFrame v19 = 0;
        vn.ai.trueid.lottie.model.animatable.AnimatableTextProperties v20 = 0;
        vn.ai.trueid.lottie.model.animatable.AnimatableFloatValue v23 = 0;
        vn.ai.trueid.lottie.model.content.BlurEffect v25 = 0;
        vn.ai.trueid.lottie.parser.DropShadowEffect v26 = 0;
        long v28 = 0;
        int v30 = 0;
        int v31 = 0;
        int v32 = 0;
        boolean v36 = 0;
        long v7_1 = -1;
        float v15_1 = 1065353216;
        vn.ai.trueid.lottie.model.animatable.AnimatableTransform v11_2 = wmoi.igob.ggcn(6306);
        int v12_1 = 0;
        vn.ai.trueid.lottie.model.animatable.AnimatableTransform v13_1 = 0;
        float v16_1 = 0;
        String v3_2 = 0;
        while (p40.hasNext()) {
            float v39_0;
            switch (p40.selectName(vn.ai.trueid.lottie.parser.LayerParser.NAMES)) {
                case 0:
                    v11_2 = p40.nextString();
                    break;
                case 1:
                    v28 = ((long) p40.nextInt());
                    break;
                case 2:
                    v17_1 = p40.nextString();
                    break;
                case 3:
                    v39_0 = v15_1;
                    long v4_47 = p40.nextInt();
                    v16_1 = vn.ai.trueid.lottie.model.layer.Layer$LayerType.UNKNOWN;
                    if (v4_47 >= v16_1.ordinal()) {
                        v15_1 = v39_0;
                    } else {
                        v16_1 = vn.ai.trueid.lottie.model.layer.Layer$LayerType.values()[v4_47];
                    }
                    break;
                case 4:
                    v7_1 = ((long) p40.nextInt());
                    break;
                case 5:
                    v30 = ((int) (vn.ai.trueid.lottie.utils.Utils.dpScale() * ((float) p40.nextInt())));
                    break;
                case 6:
                    v31 = ((int) (vn.ai.trueid.lottie.utils.Utils.dpScale() * ((float) p40.nextInt())));
                    break;
                case 7:
                    v32 = android.graphics.Color.parseColor(p40.nextString());
                    break;
                case 8:
                    v13_1 = vn.ai.trueid.lottie.parser.AnimatableTransformParser.parse(p40, p41);
                    break;
                case 9:
                    v39_0 = v15_1;
                    long v4_31 = p40.nextInt();
                    if (v4_31 < vn.ai.trueid.lottie.model.layer.Layer$MatteType.values().length) {
                        v22 = vn.ai.trueid.lottie.model.layer.Layer$MatteType.values()[v4_31];
                        long v4_33 = vn.ai.trueid.lottie.parser.LayerParser$1.$SwitchMap$com$airbnb$lottie$model$layer$Layer$MatteType[v22.ordinal()];
                        if (v4_33 == 1) {
                            p41.addWarning(wmoi.igob.ggcn(6311));
                        } else {
                            if (v4_33 == 2) {
                                p41.addWarning(wmoi.igob.ggcn(6310));
                            }
                        }
                        p41.incrementMatteOrMaskCount(1);
                    } else {
                        float v5_9 = new StringBuilder(wmoi.igob.ggcn(6309));
                        v5_9.append(v4_31);
                        p41.addWarning(v5_9.toString());
                    }
                    break;
                case 10:
                    v39_0 = v15_1;
                    p40.beginArray();
                    while (p40.hasNext()) {
                        v10_1.add(vn.ai.trueid.lottie.parser.MaskParser.parse(p40, p41));
                    }
                    p41.incrementMatteOrMaskCount(v10_1.size());
                    p40.endArray();
                    break;
                case 11:
                    v39_0 = v15_1;
                    p40.beginArray();
                    while (p40.hasNext()) {
                        long v4_27 = vn.ai.trueid.lottie.parser.ContentModelParser.parse(p40, p41);
                        if (v4_27 != 0) {
                            v9_2.add(v4_27);
                        }
                    }
                    p40.endArray();
                    break;
                case 12:
                    v39_0 = v15_1;
                    p40.beginObject();
                    while (p40.hasNext()) {
                        long v4_23 = p40.selectName(vn.ai.trueid.lottie.parser.LayerParser.TEXT_NAMES);
                        if (v4_23 == 0) {
                            v19 = vn.ai.trueid.lottie.parser.AnimatableValueParser.parseDocumentData(p40, p41);
                        } else {
                            if (v4_23 == 1) {
                                p40.beginArray();
                                if (p40.hasNext()) {
                                    v20 = vn.ai.trueid.lottie.parser.AnimatableTextPropertiesParser.parse(p40, p41);
                                }
                                while (p40.hasNext()) {
                                    p40.skipValue();
                                }
                                p40.endArray();
                            } else {
                                p40.skipName();
                                p40.skipValue();
                            }
                        }
                    }
                    p40.endObject();
                    break;
                case 13:
                    v39_0 = v15_1;
                    p40.beginArray();
                    long v4_19 = new java.util.ArrayList();
                    while (p40.hasNext()) {
                        p40.beginObject();
                        while (p40.hasNext()) {
                            vn.ai.trueid.lottie.parser.DropShadowEffectParser v6_12 = p40.selectName(vn.ai.trueid.lottie.parser.LayerParser.EFFECTS_NAMES);
                            if (v6_12 == null) {
                                vn.ai.trueid.lottie.parser.DropShadowEffectParser v6_13 = p40.nextInt();
                                if (v6_13 != 29) {
                                    if (v6_13 == 25) {
                                        v26 = new vn.ai.trueid.lottie.parser.DropShadowEffectParser().parse(p40, p41);
                                    }
                                } else {
                                    v25 = vn.ai.trueid.lottie.parser.BlurEffectParser.parse(p40, p41);
                                }
                            } else {
                                if (v6_12 == 1) {
                                    v4_19.add(p40.nextString());
                                } else {
                                    p40.skipName();
                                    p40.skipValue();
                                }
                            }
                        }
                        p40.endObject();
                    }
                    p40.endArray();
                    float v5_7 = new StringBuilder(wmoi.igob.ggcn(6308));
                    v5_7.append(v4_19);
                    p41.addWarning(v5_7.toString());
                    break;
                case 14:
                    v15_1 = ((float) p40.nextDouble());
                    break;
                case 15:
                    v35 = ((float) p40.nextDouble());
                    break;
                case 16:
                    v39_0 = v15_1;
                    v33 = ((float) (p40.nextDouble() * ((double) vn.ai.trueid.lottie.utils.Utils.dpScale())));
                    break;
                case 17:
                    v39_0 = v15_1;
                    v34 = ((float) (p40.nextDouble() * ((double) vn.ai.trueid.lottie.utils.Utils.dpScale())));
                    break;
                case 18:
                    v21_1 = ((float) p40.nextDouble());
                    break;
                case 19:
                    v24_1 = ((float) p40.nextDouble());
                    break;
                case 20:
                    v23 = vn.ai.trueid.lottie.parser.AnimatableValueParser.parseFloat(p40, p41, v14_3);
                    break;
                case 21:
                    v12_1 = p40.nextString();
                    break;
                case 22:
                    v36 = p40.nextBoolean();
                    break;
                case 23:
                    if (p40.nextInt() != 1) {
                        v3_2 = v14_3;
                    } else {
                        v3_2 = 1;
                    }
                    break;
                case 24:
                    long v4_3 = p40.nextInt();
                    if (v4_3 < vn.ai.trueid.lottie.model.content.LBlendMode.values().length) {
                        v27 = vn.ai.trueid.lottie.model.content.LBlendMode.values()[v4_3];
                    } else {
                        float v5_5 = new StringBuilder(wmoi.igob.ggcn(6307));
                        v5_5.append(v4_3);
                        p41.addWarning(v5_5.toString());
                        v27 = vn.ai.trueid.lottie.model.content.LBlendMode.NORMAL;
                    }
                    break;
                default:
                    p40.skipName();
                    p40.skipValue();
                    v39_0 = v15_1;
            }
            v14_3 = 0;
        }
        Float v38;
        float v15_3;
        float v39_1 = v15_1;
        p40.endObject();
        int v14_7 = new java.util.ArrayList();
        if (v21_1 <= 0) {
            v38 = v2_1;
            v15_3 = v3_2;
        } else {
            v15_3 = v3_2;
            v38 = v2_1;
            v14_7.add(new vn.ai.trueid.lottie.value.Keyframe(p41, v2_1, v2_1, 0, 0, Float.valueOf(v21_1)));
        }
        if (v24_1 <= 0) {
            v24_1 = p41.getEndFrame();
        }
        v14_7.add(new vn.ai.trueid.lottie.value.Keyframe(p41, v18_1, v18_1, 0, v21_1, Float.valueOf(v24_1)));
        v14_7.add(new vn.ai.trueid.lottie.value.Keyframe(p41, v38, v38, 0, v24_1, Float.valueOf(2139095039)));
        if ((v11_2.endsWith(wmoi.igob.ggcn(6312))) || (wmoi.igob.ggcn(6313).equals(v12_1))) {
            p41.addWarning(wmoi.igob.ggcn(6314));
        }
        if (v15_3 != 0) {
            if (v13_1 == null) {
                v13_1 = new vn.ai.trueid.lottie.model.animatable.AnimatableTransform();
            }
            v13_1.setAutoOrient(v15_3);
        }
        return new vn.ai.trueid.lottie.model.layer.Layer(v9_2, p41, v11_2, v28, v16_1, v7_1, v17_1, v10_1, v13_1, v30, v31, v32, v39_1, v35, v33, v34, v19, v20, v14_7, v22, v23, v36, v25, v26, v27);
    }
}
