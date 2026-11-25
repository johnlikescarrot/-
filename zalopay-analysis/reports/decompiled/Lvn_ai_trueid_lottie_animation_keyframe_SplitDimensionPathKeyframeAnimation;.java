package vn.ai.trueid.lottie.animation.keyframe;
public class SplitDimensionPathKeyframeAnimation extends vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation {
    private final android.graphics.PointF point;
    private final android.graphics.PointF pointWithCallbackValues;
    private final vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation xAnimation;
    protected vn.ai.trueid.lottie.value.LottieValueCallback xValueCallback;
    private final vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation yAnimation;
    protected vn.ai.trueid.lottie.value.LottieValueCallback yValueCallback;

    public SplitDimensionPathKeyframeAnimation(vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation p2, vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation p3)
    {
        super(java.util.Collections.EMPTY_LIST);
        super.point = new android.graphics.PointF();
        super.pointWithCallbackValues = new android.graphics.PointF();
        super.xAnimation = p2;
        super.yAnimation = p3;
        super.setProgress(super.getProgress());
        return;
    }

    public android.graphics.PointF getValue()
    {
        return this.getValue(0, 0);
    }

    public android.graphics.PointF getValue(vn.ai.trueid.lottie.value.Keyframe p10, float p11)
    {
        android.graphics.PointF v10_7;
        float v11_0 = 0;
        if (this.xValueCallback == null) {
            v10_7 = 0;
        } else {
            android.graphics.PointF v10_8 = this.xAnimation.getCurrentKeyframe();
            if (v10_8 == null) {
            } else {
                float v3_0;
                float v0_7 = v10_8.endFrame;
                float v2_0 = v10_8.startFrame;
                if (v0_7 != 0) {
                    v3_0 = v0_7.floatValue();
                } else {
                    v3_0 = v2_0;
                }
                v10_7 = ((Float) this.xValueCallback.getValueInternal(v2_0, v3_0, ((Float) v10_8.startValue), ((Float) v10_8.endValue), this.xAnimation.getInterpolatedCurrentKeyframeProgress(), this.xAnimation.getLinearCurrentKeyframeProgress(), this.xAnimation.getProgress()));
            }
        }
        if (this.yValueCallback != null) {
            float v0_4 = this.yAnimation.getCurrentKeyframe();
            if (v0_4 != 0) {
                float v3_1;
                float v11_1 = v0_4.endFrame;
                float v2_1 = v0_4.startFrame;
                if (v11_1 != 0) {
                    v3_1 = v11_1.floatValue();
                } else {
                    v3_1 = v2_1;
                }
                v11_0 = ((Float) this.yValueCallback.getValueInternal(v2_1, v3_1, ((Float) v0_4.startValue), ((Float) v0_4.endValue), this.yAnimation.getInterpolatedCurrentKeyframeProgress(), this.yAnimation.getLinearCurrentKeyframeProgress(), this.yAnimation.getProgress()));
            }
        }
        if (v10_7 != null) {
            this.pointWithCallbackValues.set(v10_7.floatValue(), 0);
        } else {
            this.pointWithCallbackValues.set(this.point.x, 0);
        }
        if (v11_0 != 0) {
            android.graphics.PointF v10_11 = this.pointWithCallbackValues;
            v10_11.set(v10_11.x, v11_0.floatValue());
        } else {
            android.graphics.PointF v10_12 = this.pointWithCallbackValues;
            v10_12.set(v10_12.x, this.point.y);
        }
        return this.pointWithCallbackValues;
    }

    public bridge synthetic Object getValue()
    {
        return this.getValue();
    }

    public bridge synthetic Object getValue(vn.ai.trueid.lottie.value.Keyframe p1, float p2)
    {
        return this.getValue(p1, p2);
    }

    public void setProgress(float p3)
    {
        this.xAnimation.setProgress(p3);
        this.yAnimation.setProgress(p3);
        this.point.set(((Float) this.xAnimation.getValue()).floatValue(), ((Float) this.yAnimation.getValue()).floatValue());
        int v3_1 = 0;
        while (v3_1 < this.listeners.size()) {
            ((vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation$AnimationListener) this.listeners.get(v3_1)).onValueChanged();
            v3_1++;
        }
        return;
    }

    public void setXValueCallback(vn.ai.trueid.lottie.value.LottieValueCallback p3)
    {
        vn.ai.trueid.lottie.value.LottieValueCallback v0 = this.xValueCallback;
        if (v0 != null) {
            v0.setAnimation(0);
        }
        this.xValueCallback = p3;
        if (p3 != null) {
            p3.setAnimation(this);
        }
        return;
    }

    public void setYValueCallback(vn.ai.trueid.lottie.value.LottieValueCallback p3)
    {
        vn.ai.trueid.lottie.value.LottieValueCallback v0 = this.yValueCallback;
        if (v0 != null) {
            v0.setAnimation(0);
        }
        this.yValueCallback = p3;
        if (p3 != null) {
            p3.setAnimation(this);
        }
        return;
    }
}
