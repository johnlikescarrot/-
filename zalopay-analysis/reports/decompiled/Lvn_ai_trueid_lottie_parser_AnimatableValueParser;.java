package vn.ai.trueid.lottie.parser;
public class AnimatableValueParser {

    private AnimatableValueParser()
    {
        return;
    }

    private static java.util.List parse(vn.ai.trueid.lottie.parser.moshi.JsonReader p1, float p2, vn.ai.trueid.lottie.LottieComposition p3, vn.ai.trueid.lottie.parser.ValueParser p4)
    {
        return vn.ai.trueid.lottie.parser.KeyframesParser.parse(p1, p3, p2, p4, 0);
    }

    private static java.util.List parse(vn.ai.trueid.lottie.parser.moshi.JsonReader p2, vn.ai.trueid.lottie.LottieComposition p3, vn.ai.trueid.lottie.parser.ValueParser p4)
    {
        return vn.ai.trueid.lottie.parser.KeyframesParser.parse(p2, p3, 1065353216, p4, 0);
    }

    public static vn.ai.trueid.lottie.model.animatable.AnimatableColorValue parseColor(vn.ai.trueid.lottie.parser.moshi.JsonReader p2, vn.ai.trueid.lottie.LottieComposition p3)
    {
        return new vn.ai.trueid.lottie.model.animatable.AnimatableColorValue(vn.ai.trueid.lottie.parser.AnimatableValueParser.parse(p2, p3, vn.ai.trueid.lottie.parser.ColorParser.INSTANCE));
    }

    public static vn.ai.trueid.lottie.model.animatable.AnimatableTextFrame parseDocumentData(vn.ai.trueid.lottie.parser.moshi.JsonReader p3, vn.ai.trueid.lottie.LottieComposition p4)
    {
        return new vn.ai.trueid.lottie.model.animatable.AnimatableTextFrame(vn.ai.trueid.lottie.parser.AnimatableValueParser.parse(p3, vn.ai.trueid.lottie.utils.Utils.dpScale(), p4, vn.ai.trueid.lottie.parser.DocumentDataParser.INSTANCE));
    }

    public static vn.ai.trueid.lottie.model.animatable.AnimatableFloatValue parseFloat(vn.ai.trueid.lottie.parser.moshi.JsonReader p1, vn.ai.trueid.lottie.LottieComposition p2)
    {
        return vn.ai.trueid.lottie.parser.AnimatableValueParser.parseFloat(p1, p2, 1);
    }

    public static vn.ai.trueid.lottie.model.animatable.AnimatableFloatValue parseFloat(vn.ai.trueid.lottie.parser.moshi.JsonReader p2, vn.ai.trueid.lottie.LottieComposition p3, boolean p4)
    {
        int v4_1;
        if (p4 == 0) {
            v4_1 = 1065353216;
        } else {
            v4_1 = vn.ai.trueid.lottie.utils.Utils.dpScale();
        }
        return new vn.ai.trueid.lottie.model.animatable.AnimatableFloatValue(vn.ai.trueid.lottie.parser.AnimatableValueParser.parse(p2, v4_1, p3, vn.ai.trueid.lottie.parser.FloatParser.INSTANCE));
    }

    public static vn.ai.trueid.lottie.model.animatable.AnimatableGradientColorValue parseGradientColor(vn.ai.trueid.lottie.parser.moshi.JsonReader p2, vn.ai.trueid.lottie.LottieComposition p3, int p4)
    {
        return new vn.ai.trueid.lottie.model.animatable.AnimatableGradientColorValue(vn.ai.trueid.lottie.parser.AnimatableValueParser.parse(p2, p3, new vn.ai.trueid.lottie.parser.GradientColorParser(p4)));
    }

    public static vn.ai.trueid.lottie.model.animatable.AnimatableIntegerValue parseInteger(vn.ai.trueid.lottie.parser.moshi.JsonReader p2, vn.ai.trueid.lottie.LottieComposition p3)
    {
        return new vn.ai.trueid.lottie.model.animatable.AnimatableIntegerValue(vn.ai.trueid.lottie.parser.AnimatableValueParser.parse(p2, p3, vn.ai.trueid.lottie.parser.IntegerParser.INSTANCE));
    }

    public static vn.ai.trueid.lottie.model.animatable.AnimatablePointValue parsePoint(vn.ai.trueid.lottie.parser.moshi.JsonReader p4, vn.ai.trueid.lottie.LottieComposition p5)
    {
        return new vn.ai.trueid.lottie.model.animatable.AnimatablePointValue(vn.ai.trueid.lottie.parser.KeyframesParser.parse(p4, p5, vn.ai.trueid.lottie.utils.Utils.dpScale(), vn.ai.trueid.lottie.parser.PointFParser.INSTANCE, 1));
    }

    public static vn.ai.trueid.lottie.model.animatable.AnimatableScaleValue parseScale(vn.ai.trueid.lottie.parser.moshi.JsonReader p2, vn.ai.trueid.lottie.LottieComposition p3)
    {
        return new vn.ai.trueid.lottie.model.animatable.AnimatableScaleValue(vn.ai.trueid.lottie.parser.AnimatableValueParser.parse(p2, p3, vn.ai.trueid.lottie.parser.ScaleXYParser.INSTANCE));
    }

    public static vn.ai.trueid.lottie.model.animatable.AnimatableShapeValue parseShapeData(vn.ai.trueid.lottie.parser.moshi.JsonReader p3, vn.ai.trueid.lottie.LottieComposition p4)
    {
        return new vn.ai.trueid.lottie.model.animatable.AnimatableShapeValue(vn.ai.trueid.lottie.parser.AnimatableValueParser.parse(p3, vn.ai.trueid.lottie.utils.Utils.dpScale(), p4, vn.ai.trueid.lottie.parser.ShapeDataParser.INSTANCE));
    }
}
