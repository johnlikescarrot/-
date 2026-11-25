package vn.ai.trueid.lottie.model.animatable;
public interface AnimatableValue {

    public abstract vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation createAnimation();

    public abstract java.util.List getKeyframes();

    public abstract boolean isStatic();
}
