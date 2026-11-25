package vn.ai.trueid.lottie.model.animatable;
public class AnimatableFloatValue extends vn.ai.trueid.lottie.model.animatable.BaseAnimatableValue {

    public AnimatableFloatValue(java.util.List p1)
    {
        super(p1);
        return;
    }

    public bridge synthetic vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation createAnimation()
    {
        return this.createAnimation();
    }

    public vn.ai.trueid.lottie.animation.keyframe.FloatKeyframeAnimation createAnimation()
    {
        return new vn.ai.trueid.lottie.animation.keyframe.FloatKeyframeAnimation(this.keyframes);
    }

    public bridge synthetic java.util.List getKeyframes()
    {
        return super.getKeyframes();
    }

    public bridge synthetic boolean isStatic()
    {
        return super.isStatic();
    }

    public bridge synthetic String toString()
    {
        return super.toString();
    }
}
