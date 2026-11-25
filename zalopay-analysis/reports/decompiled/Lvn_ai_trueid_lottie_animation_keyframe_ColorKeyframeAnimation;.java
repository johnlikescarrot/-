package vn.ai.trueid.lottie.animation.keyframe;
public class ColorKeyframeAnimation extends vn.ai.trueid.lottie.animation.keyframe.KeyframeAnimation {

    public ColorKeyframeAnimation(java.util.List p1)
    {
        super(p1);
        return;
    }

    public int getIntValue()
    {
        return this.getIntValue(this.getCurrentKeyframe(), this.getInterpolatedCurrentKeyframeProgress());
    }

    public int getIntValue(vn.ai.trueid.lottie.value.Keyframe p10, float p11)
    {
        if ((p10.startValue == null) || (p10.endValue == null)) {
            throw new IllegalStateException("Missing values for keyframe.");
        } else {
            String v6;
            vn.ai.trueid.lottie.value.LottieValueCallback v1 = this.valueCallback;
            if (v1 == null) {
                v6 = p11;
            } else {
                int v0_1 = p10.endFrame;
                if (v0_1 == 0) {
                } else {
                    v6 = p11;
                    float v11_2 = ((Integer) v1.getValueInternal(p10.startFrame, v0_1.floatValue(), ((Integer) p10.startValue), ((Integer) p10.endValue), p11, this.getLinearCurrentKeyframeProgress(), this.getProgress()));
                    if (v11_2 != 0) {
                        return v11_2.intValue();
                    }
                }
            }
            return vn.ai.trueid.lottie.utils.GammaEvaluator.evaluate(vn.ai.trueid.lottie.utils.MiscUtils.clamp(v6, 0, 1065353216), ((Integer) p10.startValue).intValue(), ((Integer) p10.endValue).intValue());
        }
    }

    public Integer getValue(vn.ai.trueid.lottie.value.Keyframe p1, float p2)
    {
        return Integer.valueOf(this.getIntValue(p1, p2));
    }

    public bridge synthetic Object getValue(vn.ai.trueid.lottie.value.Keyframe p1, float p2)
    {
        return this.getValue(p1, p2);
    }
}
