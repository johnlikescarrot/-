package vn.ai.trueid.lottie.model.animatable;
public class AnimatableSplitDimensionPathValue implements vn.ai.trueid.lottie.model.animatable.AnimatableValue {
    private final vn.ai.trueid.lottie.model.animatable.AnimatableFloatValue animatableXDimension;
    private final vn.ai.trueid.lottie.model.animatable.AnimatableFloatValue animatableYDimension;

    public AnimatableSplitDimensionPathValue(vn.ai.trueid.lottie.model.animatable.AnimatableFloatValue p1, vn.ai.trueid.lottie.model.animatable.AnimatableFloatValue p2)
    {
        this.animatableXDimension = p1;
        this.animatableYDimension = p2;
        return;
    }

    public vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation createAnimation()
    {
        return new vn.ai.trueid.lottie.animation.keyframe.SplitDimensionPathKeyframeAnimation(this.animatableXDimension.createAnimation(), this.animatableYDimension.createAnimation());
    }

    public java.util.List getKeyframes()
    {
        throw new UnsupportedOperationException("Cannot call getKeyframes on AnimatableSplitDimensionPathValue.");
    }

    public boolean isStatic()
    {
        if ((!this.animatableXDimension.isStatic()) || (!this.animatableYDimension.isStatic())) {
            return 0;
        } else {
            return 1;
        }
    }
}
