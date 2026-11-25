package vn.ai.trueid.lottie.model.content;
public class Mask {
    private final boolean inverted;
    private final vn.ai.trueid.lottie.model.content.Mask$MaskMode maskMode;
    private final vn.ai.trueid.lottie.model.animatable.AnimatableShapeValue maskPath;
    private final vn.ai.trueid.lottie.model.animatable.AnimatableIntegerValue opacity;

    public Mask(vn.ai.trueid.lottie.model.content.Mask$MaskMode p1, vn.ai.trueid.lottie.model.animatable.AnimatableShapeValue p2, vn.ai.trueid.lottie.model.animatable.AnimatableIntegerValue p3, boolean p4)
    {
        this.maskMode = p1;
        this.maskPath = p2;
        this.opacity = p3;
        this.inverted = p4;
        return;
    }

    public vn.ai.trueid.lottie.model.content.Mask$MaskMode getMaskMode()
    {
        return this.maskMode;
    }

    public vn.ai.trueid.lottie.model.animatable.AnimatableShapeValue getMaskPath()
    {
        return this.maskPath;
    }

    public vn.ai.trueid.lottie.model.animatable.AnimatableIntegerValue getOpacity()
    {
        return this.opacity;
    }

    public boolean isInverted()
    {
        return this.inverted;
    }
}
