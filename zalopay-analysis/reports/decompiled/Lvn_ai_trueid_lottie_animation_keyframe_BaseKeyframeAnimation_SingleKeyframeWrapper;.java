package vn.ai.trueid.lottie.animation.keyframe;
final class BaseKeyframeAnimation$SingleKeyframeWrapper implements vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation$KeyframesWrapper {
    private float cachedInterpolatedProgress;
    private final vn.ai.trueid.lottie.value.Keyframe keyframe;

    public BaseKeyframeAnimation$SingleKeyframeWrapper(java.util.List p2)
    {
        this.cachedInterpolatedProgress = -1082130432;
        this.keyframe = ((vn.ai.trueid.lottie.value.Keyframe) p2.get(0));
        return;
    }

    public vn.ai.trueid.lottie.value.Keyframe getCurrentKeyframe()
    {
        return this.keyframe;
    }

    public float getEndProgress()
    {
        return this.keyframe.getEndProgress();
    }

    public float getStartDelayProgress()
    {
        return this.keyframe.getStartProgress();
    }

    public boolean isCachedValueEnabled(float p2)
    {
        if (this.cachedInterpolatedProgress != p2) {
            this.cachedInterpolatedProgress = p2;
            return 0;
        } else {
            return 1;
        }
    }

    public boolean isEmpty()
    {
        return 0;
    }

    public boolean isValueChanged(float p1)
    {
        return (this.keyframe.isStatic() ^ 1);
    }
}
