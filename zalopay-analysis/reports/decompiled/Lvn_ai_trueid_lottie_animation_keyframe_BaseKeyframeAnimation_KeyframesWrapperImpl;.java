package vn.ai.trueid.lottie.animation.keyframe;
final class BaseKeyframeAnimation$KeyframesWrapperImpl implements vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation$KeyframesWrapper {
    private vn.ai.trueid.lottie.value.Keyframe cachedCurrentKeyframe;
    private float cachedInterpolatedProgress;
    private vn.ai.trueid.lottie.value.Keyframe currentKeyframe;
    private final java.util.List keyframes;

    public BaseKeyframeAnimation$KeyframesWrapperImpl(java.util.List p2)
    {
        this.cachedCurrentKeyframe = 0;
        this.cachedInterpolatedProgress = -1082130432;
        this.keyframes = p2;
        this.currentKeyframe = this.findKeyframe(0);
        return;
    }

    private vn.ai.trueid.lottie.value.Keyframe findKeyframe(float p5)
    {
        int v0_5 = ((vn.ai.trueid.lottie.value.Keyframe) androidx.media3.exoplayer.source.r.b(1, this.keyframes));
        if (p5 < v0_5.getStartProgress()) {
            int v0_2 = (this.keyframes.size() - 2);
            while (v0_2 >= 1) {
                vn.ai.trueid.lottie.value.Keyframe v2_2 = ((vn.ai.trueid.lottie.value.Keyframe) this.keyframes.get(v0_2));
                if ((this.currentKeyframe == v2_2) || (!v2_2.containsProgress(p5))) {
                    v0_2--;
                } else {
                    return v2_2;
                }
            }
            return ((vn.ai.trueid.lottie.value.Keyframe) this.keyframes.get(0));
        } else {
            return v0_5;
        }
    }

    public vn.ai.trueid.lottie.value.Keyframe getCurrentKeyframe()
    {
        return this.currentKeyframe;
    }

    public float getEndProgress()
    {
        return ((vn.ai.trueid.lottie.value.Keyframe) androidx.media3.exoplayer.source.r.b(1, this.keyframes)).getEndProgress();
    }

    public float getStartDelayProgress()
    {
        return ((vn.ai.trueid.lottie.value.Keyframe) this.keyframes.get(0)).getStartProgress();
    }

    public boolean isCachedValueEnabled(float p3)
    {
        vn.ai.trueid.lottie.value.Keyframe v1 = this.currentKeyframe;
        if ((this.cachedCurrentKeyframe != v1) || (this.cachedInterpolatedProgress != p3)) {
            this.cachedCurrentKeyframe = v1;
            this.cachedInterpolatedProgress = p3;
            return 0;
        } else {
            return 1;
        }
    }

    public boolean isEmpty()
    {
        return 0;
    }

    public boolean isValueChanged(float p3)
    {
        if (!this.currentKeyframe.containsProgress(p3)) {
            this.currentKeyframe = this.findKeyframe(p3);
            return 1;
        } else {
            return (this.currentKeyframe.isStatic() ^ 1);
        }
    }
}
