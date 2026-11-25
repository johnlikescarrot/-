package vn.ai.trueid.lottie.model.animatable;
public class AnimatableColorValue extends vn.ai.trueid.lottie.model.animatable.BaseAnimatableValue {

    public AnimatableColorValue(java.util.List p1)
    {
        super(p1);
        return;
    }

    public vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation createAnimation()
    {
        return new vn.ai.trueid.lottie.animation.keyframe.ColorKeyframeAnimation(this.keyframes);
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
