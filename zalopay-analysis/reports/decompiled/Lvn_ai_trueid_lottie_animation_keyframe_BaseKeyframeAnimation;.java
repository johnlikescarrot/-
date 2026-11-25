package vn.ai.trueid.lottie.animation.keyframe;
public abstract class BaseKeyframeAnimation {
    private float cachedEndProgress;
    private Object cachedGetValue;
    private float cachedStartDelayProgress;
    private boolean isDiscrete;
    private final vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation$KeyframesWrapper keyframesWrapper;
    final java.util.List listeners;
    protected float progress;
    protected vn.ai.trueid.lottie.value.LottieValueCallback valueCallback;

    public BaseKeyframeAnimation(java.util.List p3)
    {
        this.listeners = new java.util.ArrayList(1);
        this.isDiscrete = 0;
        this.progress = 0;
        this.cachedGetValue = 0;
        this.cachedStartDelayProgress = -1082130432;
        this.cachedEndProgress = -1082130432;
        this.keyframesWrapper = vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation.wrap(p3);
        return;
    }

    private float getStartDelayProgress()
    {
        if (this.cachedStartDelayProgress == -1082130432) {
            this.cachedStartDelayProgress = this.keyframesWrapper.getStartDelayProgress();
        }
        return this.cachedStartDelayProgress;
    }

    private static vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation$KeyframesWrapper wrap(java.util.List p2)
    {
        if (!p2.isEmpty()) {
            if (p2.size() != 1) {
                return new vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation$KeyframesWrapperImpl(p2);
            } else {
                return new vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation$SingleKeyframeWrapper(p2);
            }
        } else {
            return new vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation$EmptyKeyframeWrapper(0);
        }
    }

    public void addUpdateListener(vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation$AnimationListener p2)
    {
        this.listeners.add(p2);
        return;
    }

    public vn.ai.trueid.lottie.value.Keyframe getCurrentKeyframe()
    {
        if (vn.ai.trueid.lottie.L.isTraceEnabled()) {
            vn.ai.trueid.lottie.L.beginSection("BaseKeyframeAnimation#getCurrentKeyframe");
        }
        vn.ai.trueid.lottie.value.Keyframe v0_2 = this.keyframesWrapper.getCurrentKeyframe();
        if (vn.ai.trueid.lottie.L.isTraceEnabled()) {
            vn.ai.trueid.lottie.L.endSection("BaseKeyframeAnimation#getCurrentKeyframe");
        }
        return v0_2;
    }

    public float getEndProgress()
    {
        if (this.cachedEndProgress == -1082130432) {
            this.cachedEndProgress = this.keyframesWrapper.getEndProgress();
        }
        return this.cachedEndProgress;
    }

    public float getInterpolatedCurrentKeyframeProgress()
    {
        float v0_0 = this.getCurrentKeyframe();
        if ((v0_0 != 0) && (!v0_0.isStatic())) {
            float v0_3 = v0_0.interpolator;
            if (v0_3 != 0) {
                return v0_3.getInterpolation(this.getLinearCurrentKeyframeProgress());
            }
        }
        return 0;
    }

    public float getLinearCurrentKeyframeProgress()
    {
        if (!this.isDiscrete) {
            float v0_2 = this.getCurrentKeyframe();
            if (!v0_2.isStatic()) {
                return ((this.progress - v0_2.getStartProgress()) / (v0_2.getEndProgress() - v0_2.getStartProgress()));
            } else {
                return 0;
            }
        } else {
            return 0;
        }
    }

    public float getProgress()
    {
        return this.progress;
    }

    public Object getValue()
    {
        Object v0_0 = this.getLinearCurrentKeyframeProgress();
        if ((this.valueCallback != null) || ((!this.keyframesWrapper.isCachedValueEnabled(v0_0)) || (this.skipCache()))) {
            Object v0_2;
            boolean v1_0 = this.getCurrentKeyframe();
            float v2_0 = v1_0.xInterpolator;
            if ((v2_0 == 0) || (v1_0.yInterpolator == null)) {
                v0_2 = this.getValue(v1_0, this.getInterpolatedCurrentKeyframeProgress());
            } else {
                v0_2 = this.getValue(v1_0, v0_0, v2_0.getInterpolation(v0_0), v1_0.yInterpolator.getInterpolation(v0_0));
            }
            this.cachedGetValue = v0_2;
            return v0_2;
        } else {
            return this.cachedGetValue;
        }
    }

    public abstract Object getValue(vn.ai.trueid.lottie.value.Keyframe p0, float p1);

    public Object getValue(vn.ai.trueid.lottie.value.Keyframe p1, float p2, float p3, float p4)
    {
        throw new UnsupportedOperationException("This animation does not support split dimensions!");
    }

    public boolean hasValueCallback()
    {
        if (this.valueCallback == null) {
            return 0;
        } else {
            return 1;
        }
    }

    public void notifyListeners()
    {
        if (vn.ai.trueid.lottie.L.isTraceEnabled()) {
            vn.ai.trueid.lottie.L.beginSection("BaseKeyframeAnimation#notifyListeners");
        }
        boolean v0_2 = 0;
        while (v0_2 < this.listeners.size()) {
            ((vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation$AnimationListener) this.listeners.get(v0_2)).onValueChanged();
            v0_2++;
        }
        if (vn.ai.trueid.lottie.L.isTraceEnabled()) {
            vn.ai.trueid.lottie.L.endSection("BaseKeyframeAnimation#notifyListeners");
        }
        return;
    }

    public void setIsDiscrete()
    {
        this.isDiscrete = 1;
        return;
    }

    public void setProgress(float p3)
    {
        if (vn.ai.trueid.lottie.L.isTraceEnabled()) {
            vn.ai.trueid.lottie.L.beginSection("BaseKeyframeAnimation#setProgress");
        }
        if (!this.keyframesWrapper.isEmpty()) {
            if (p3 >= this.getStartDelayProgress()) {
                if (p3 > this.getEndProgress()) {
                    p3 = this.getEndProgress();
                }
            } else {
                p3 = this.getStartDelayProgress();
            }
            if (p3 != this.progress) {
                this.progress = p3;
                if (this.keyframesWrapper.isValueChanged(p3)) {
                    this.notifyListeners();
                }
                if (vn.ai.trueid.lottie.L.isTraceEnabled()) {
                    vn.ai.trueid.lottie.L.endSection("BaseKeyframeAnimation#setProgress");
                }
            } else {
                if (vn.ai.trueid.lottie.L.isTraceEnabled()) {
                    vn.ai.trueid.lottie.L.endSection("BaseKeyframeAnimation#setProgress");
                    return;
                }
            }
        } else {
            if (vn.ai.trueid.lottie.L.isTraceEnabled()) {
                vn.ai.trueid.lottie.L.endSection("BaseKeyframeAnimation#setProgress");
                return;
            }
        }
        return;
    }

    public void setValueCallback(vn.ai.trueid.lottie.value.LottieValueCallback p3)
    {
        vn.ai.trueid.lottie.value.LottieValueCallback v0 = this.valueCallback;
        if (v0 != null) {
            v0.setAnimation(0);
        }
        this.valueCallback = p3;
        if (p3 != null) {
            p3.setAnimation(this);
        }
        return;
    }

    public boolean skipCache()
    {
        return 0;
    }
}
