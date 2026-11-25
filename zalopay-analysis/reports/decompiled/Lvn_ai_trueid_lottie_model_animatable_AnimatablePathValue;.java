package vn.ai.trueid.lottie.model.animatable;
public class AnimatablePathValue implements vn.ai.trueid.lottie.model.animatable.AnimatableValue {
    private final java.util.List keyframes;

    public AnimatablePathValue(java.util.List p1)
    {
        this.keyframes = p1;
        return;
    }

    public vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation createAnimation()
    {
        if (!((vn.ai.trueid.lottie.value.Keyframe) this.keyframes.get(0)).isStatic()) {
            return new vn.ai.trueid.lottie.animation.keyframe.PathKeyframeAnimation(this.keyframes);
        } else {
            return new vn.ai.trueid.lottie.animation.keyframe.PointKeyframeAnimation(this.keyframes);
        }
    }

    public java.util.List getKeyframes()
    {
        return this.keyframes;
    }

    public boolean isStatic()
    {
        if ((this.keyframes.size() != 1) || (!((vn.ai.trueid.lottie.value.Keyframe) this.keyframes.get(0)).isStatic())) {
            return 0;
        } else {
            return 1;
        }
    }
}
