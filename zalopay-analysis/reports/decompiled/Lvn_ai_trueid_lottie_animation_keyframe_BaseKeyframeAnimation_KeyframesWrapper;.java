package vn.ai.trueid.lottie.animation.keyframe;
interface BaseKeyframeAnimation$KeyframesWrapper {

    public abstract vn.ai.trueid.lottie.value.Keyframe getCurrentKeyframe();

    public abstract float getEndProgress();

    public abstract float getStartDelayProgress();

    public abstract boolean isCachedValueEnabled(float p0);

    public abstract boolean isEmpty();

    public abstract boolean isValueChanged(float p0);
}
