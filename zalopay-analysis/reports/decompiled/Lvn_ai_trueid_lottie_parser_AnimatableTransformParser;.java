package vn.ai.trueid.lottie.parser;
public class AnimatableTransformParser {
    private static final vn.ai.trueid.lottie.parser.moshi.JsonReader$Options ANIMATABLE_NAMES;
    private static final vn.ai.trueid.lottie.parser.moshi.JsonReader$Options NAMES;

    static AnimatableTransformParser()
    {
        String v2 = "s";
        String v4 = "r";
        String v6 = "so";
        String v8 = "sk";
        vn.ai.trueid.lottie.parser.AnimatableTransformParser.NAMES = vn.ai.trueid.lottie.parser.moshi.JsonReader$Options.of(new String[] {"a", "ry"}));
        vn.ai.trueid.lottie.parser.AnimatableTransformParser.ANIMATABLE_NAMES = vn.ai.trueid.lottie.parser.moshi.JsonReader$Options.of(new String[] {"k"}));
        return;
    }

    private AnimatableTransformParser()
    {
        return;
    }

    private static void ensureValidRotationKeyframes(vn.ai.trueid.lottie.model.animatable.AnimatableFloatValue p8, vn.ai.trueid.lottie.LottieComposition p9)
    {
        Float v3 = Float.valueOf(0);
        if (!p8.getKeyframes().isEmpty()) {
            if (((vn.ai.trueid.lottie.value.Keyframe) p8.getKeyframes().get(0)).startValue == null) {
                p8.getKeyframes().set(0, new vn.ai.trueid.lottie.value.Keyframe(p9, v3, v3, 0, 0, Float.valueOf(p9.getEndFrame())));
            }
            return;
        } else {
            p8.getKeyframes().add(new vn.ai.trueid.lottie.value.Keyframe(p9, v3, v3, 0, 0, Float.valueOf(p9.getEndFrame())));
            return;
        }
    }

    private static boolean isAnchorPointIdentity(vn.ai.trueid.lottie.model.animatable.AnimatablePathValue p2)
    {
        if ((p2) && ((!p2.isStatic()) || (!((android.graphics.PointF) ((vn.ai.trueid.lottie.value.Keyframe) p2.getKeyframes().get(0)).startValue).equals(0, 0)))) {
            return 0;
        } else {
            return 1;
        }
    }

    private static boolean isPositionIdentity(vn.ai.trueid.lottie.model.animatable.AnimatableValue p2)
    {
        if ((p2) && (((p2 instanceof vn.ai.trueid.lottie.model.animatable.AnimatableSplitDimensionPathValue)) || ((!p2.isStatic()) || (!((android.graphics.PointF) ((vn.ai.trueid.lottie.value.Keyframe) p2.getKeyframes().get(0)).startValue).equals(0, 0))))) {
            return 0;
        } else {
            return 1;
        }
    }

    private static boolean isRotationIdentity(vn.ai.trueid.lottie.model.animatable.AnimatableFloatValue p2)
    {
        if ((p2 != 0) && ((!p2.isStatic()) || (((Float) ((vn.ai.trueid.lottie.value.Keyframe) p2.getKeyframes().get(0)).startValue).floatValue() != 0))) {
            return 0;
        } else {
            return 1;
        }
    }

    private static boolean isScaleIdentity(vn.ai.trueid.lottie.model.animatable.AnimatableScaleValue p2)
    {
        if ((p2) && ((!p2.isStatic()) || (!((vn.ai.trueid.lottie.value.ScaleXY) ((vn.ai.trueid.lottie.value.Keyframe) p2.getKeyframes().get(0)).startValue).equals(1065353216, 1065353216)))) {
            return 0;
        } else {
            return 1;
        }
    }

    private static boolean isSkewAngleIdentity(vn.ai.trueid.lottie.model.animatable.AnimatableFloatValue p2)
    {
        if ((p2 != 0) && ((!p2.isStatic()) || (((Float) ((vn.ai.trueid.lottie.value.Keyframe) p2.getKeyframes().get(0)).startValue).floatValue() != 0))) {
            return 0;
        } else {
            return 1;
        }
    }

    private static boolean isSkewIdentity(vn.ai.trueid.lottie.model.animatable.AnimatableFloatValue p2)
    {
        if ((p2 != 0) && ((!p2.isStatic()) || (((Float) ((vn.ai.trueid.lottie.value.Keyframe) p2.getKeyframes().get(0)).startValue).floatValue() != 0))) {
            return 0;
        } else {
            return 1;
        }
    }

    public static vn.ai.trueid.lottie.model.animatable.AnimatableTransform parse(vn.ai.trueid.lottie.parser.moshi.JsonReader p27, vn.ai.trueid.lottie.LottieComposition p28)
    {
        int v2_1;
        if (p27.peek() != vn.ai.trueid.lottie.parser.moshi.JsonReader$Token.BEGIN_OBJECT) {
            v2_1 = 0;
        } else {
            v2_1 = 1;
        }
        if (v2_1 != 0) {
            p27.beginObject();
        }
        vn.ai.trueid.lottie.model.animatable.AnimatablePathValue v5 = 0;
        vn.ai.trueid.lottie.model.animatable.AnimatableValue v6 = 0;
        vn.ai.trueid.lottie.model.animatable.AnimatableFloatValue v7 = 0;
        vn.ai.trueid.lottie.model.animatable.AnimatableScaleValue v8 = 0;
        vn.ai.trueid.lottie.model.animatable.AnimatableFloatValue v9 = 0;
        vn.ai.trueid.lottie.model.animatable.AnimatableFloatValue v10 = 0;
        vn.ai.trueid.lottie.model.animatable.AnimatableFloatValue v11 = 0;
        vn.ai.trueid.lottie.model.animatable.AnimatableFloatValue v12 = 0;
        vn.ai.trueid.lottie.model.animatable.AnimatableFloatValue v13 = 0;
        vn.ai.trueid.lottie.model.animatable.AnimatableIntegerValue v19 = 0;
        vn.ai.trueid.lottie.model.animatable.AnimatableFloatValue v20 = 0;
        vn.ai.trueid.lottie.model.animatable.AnimatableFloatValue v21 = 0;
        while (p27.hasNext()) {
            switch (p27.selectName(vn.ai.trueid.lottie.parser.AnimatableTransformParser.NAMES)) {
                case 0:
                    p27.beginObject();
                    while (p27.hasNext()) {
                        if (p27.selectName(vn.ai.trueid.lottie.parser.AnimatableTransformParser.ANIMATABLE_NAMES) == 0) {
                            v5 = vn.ai.trueid.lottie.parser.AnimatablePathValueParser.parse(p27, p28);
                        } else {
                            p27.skipName();
                            p27.skipValue();
                        }
                    }
                    p27.endObject();
                    break;
                case 1:
                    v6 = vn.ai.trueid.lottie.parser.AnimatablePathValueParser.parseSplitPath(p27, p28);
                    break;
                case 2:
                    v8 = vn.ai.trueid.lottie.parser.AnimatableValueParser.parseScale(p27, p28);
                    break;
                case 3:
                    v13 = vn.ai.trueid.lottie.parser.AnimatableValueParser.parseFloat(p27, p28, 0);
                    vn.ai.trueid.lottie.parser.AnimatableTransformParser.ensureValidRotationKeyframes(v13, p28);
                    break;
                case 4:
                    v7 = vn.ai.trueid.lottie.parser.AnimatableValueParser.parseFloat(p27, p28, 0);
                    vn.ai.trueid.lottie.parser.AnimatableTransformParser.ensureValidRotationKeyframes(v7, p28);
                    break;
                case 5:
                    v19 = vn.ai.trueid.lottie.parser.AnimatableValueParser.parseInteger(p27, p28);
                    break;
                case 6:
                    v20 = vn.ai.trueid.lottie.parser.AnimatableValueParser.parseFloat(p27, p28, 0);
                    break;
                case 7:
                    v21 = vn.ai.trueid.lottie.parser.AnimatableValueParser.parseFloat(p27, p28, 0);
                    break;
                case 8:
                    v9 = vn.ai.trueid.lottie.parser.AnimatableValueParser.parseFloat(p27, p28, 0);
                    break;
                case 9:
                    v10 = vn.ai.trueid.lottie.parser.AnimatableValueParser.parseFloat(p27, p28, 0);
                    break;
                case 10:
                    v11 = vn.ai.trueid.lottie.parser.AnimatableValueParser.parseFloat(p27, p28, 0);
                    vn.ai.trueid.lottie.parser.AnimatableTransformParser.ensureValidRotationKeyframes(v11, p28);
                    break;
                case 11:
                    v12 = vn.ai.trueid.lottie.parser.AnimatableValueParser.parseFloat(p27, p28, 0);
                    vn.ai.trueid.lottie.parser.AnimatableTransformParser.ensureValidRotationKeyframes(v12, p28);
                    break;
                default:
                    p27.skipName();
                    p27.skipValue();
            }
        }
        if (v2_1 != 0) {
            p27.endObject();
        }
        int v15;
        if (!vn.ai.trueid.lottie.parser.AnimatableTransformParser.isAnchorPointIdentity(v5)) {
            v15 = v5;
        } else {
            v15 = 0;
        }
        vn.ai.trueid.lottie.model.animatable.AnimatableValue v16;
        if (!vn.ai.trueid.lottie.parser.AnimatableTransformParser.isPositionIdentity(v6)) {
            v16 = v6;
        } else {
            v16 = 0;
        }
        vn.ai.trueid.lottie.model.animatable.AnimatableFloatValue v18;
        if (!vn.ai.trueid.lottie.parser.AnimatableTransformParser.isRotationIdentity(v7)) {
            v18 = v7;
        } else {
            v18 = 0;
        }
        vn.ai.trueid.lottie.model.animatable.AnimatableScaleValue v17;
        if (!vn.ai.trueid.lottie.parser.AnimatableTransformParser.isScaleIdentity(v8)) {
            v17 = v8;
        } else {
            v17 = 0;
        }
        vn.ai.trueid.lottie.model.animatable.AnimatableFloatValue v22;
        if (!vn.ai.trueid.lottie.parser.AnimatableTransformParser.isSkewIdentity(v9)) {
            v22 = v9;
        } else {
            v22 = 0;
        }
        vn.ai.trueid.lottie.model.animatable.AnimatableFloatValue v23;
        if (!vn.ai.trueid.lottie.parser.AnimatableTransformParser.isSkewAngleIdentity(v10)) {
            v23 = v10;
        } else {
            v23 = 0;
        }
        vn.ai.trueid.lottie.model.animatable.AnimatableFloatValue v24;
        if (!vn.ai.trueid.lottie.parser.AnimatableTransformParser.isRotationIdentity(v11)) {
            v24 = v11;
        } else {
            v24 = 0;
        }
        vn.ai.trueid.lottie.model.animatable.AnimatableFloatValue v25;
        if (!vn.ai.trueid.lottie.parser.AnimatableTransformParser.isRotationIdentity(v12)) {
            v25 = v12;
        } else {
            v25 = 0;
        }
        vn.ai.trueid.lottie.model.animatable.AnimatableFloatValue v26;
        if (!vn.ai.trueid.lottie.parser.AnimatableTransformParser.isRotationIdentity(v13)) {
            v26 = v13;
        } else {
            v26 = 0;
        }
        return new vn.ai.trueid.lottie.model.animatable.AnimatableTransform(v15, v16, v17, v18, v19, v20, v21, v22, v23, v24, v25, v26);
    }
}
