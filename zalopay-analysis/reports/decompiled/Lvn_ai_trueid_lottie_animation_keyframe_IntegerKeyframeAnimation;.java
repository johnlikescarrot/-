package vn.ai.trueid.lottie.animation.keyframe;
public class IntegerKeyframeAnimation extends vn.ai.trueid.lottie.animation.keyframe.KeyframeAnimation {

    public IntegerKeyframeAnimation(java.util.List p1)
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
        if (p10.startValue == null) {
            throw new IllegalStateException("Missing values for keyframe.");
        } else {
            int v0_1;
            if (p10.endValue != null) {
                v0_1 = p10.getEndValueInt();
            } else {
                v0_1 = p10.getStartValueInt();
            }
            Integer v6;
            vn.ai.trueid.lottie.value.LottieValueCallback v1 = this.valueCallback;
            if (v1 == null) {
                v6 = p11;
            } else {
                v6 = p11;
                Integer v11_3 = ((Integer) v1.getValueInternal(p10.startFrame, p10.endFrame.floatValue(), ((Integer) p10.startValue), Integer.valueOf(v0_1), p11, this.getLinearCurrentKeyframeProgress(), this.getProgress()));
                if (v11_3 != null) {
                    return v11_3.intValue();
                }
            }
            return vn.ai.trueid.lottie.utils.MiscUtils.lerp(p10.getStartValueInt(), v0_1, v6);
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
