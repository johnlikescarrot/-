package vn.ai.trueid.lottie.animation.keyframe;
public class FloatKeyframeAnimation extends vn.ai.trueid.lottie.animation.keyframe.KeyframeAnimation {

    public FloatKeyframeAnimation(java.util.List p1)
    {
        super(p1);
        return;
    }

    public float getFloatValue()
    {
        return this.getFloatValue(this.getCurrentKeyframe(), this.getInterpolatedCurrentKeyframeProgress());
    }

    public float getFloatValue(vn.ai.trueid.lottie.value.Keyframe p10, float p11)
    {
        if ((p10.startValue == null) || (p10.endValue == null)) {
            throw new IllegalStateException("Missing values for keyframe.");
        } else {
            Float v6;
            vn.ai.trueid.lottie.value.LottieValueCallback v1 = this.valueCallback;
            if (v1 == null) {
                v6 = p11;
            } else {
                v6 = p11;
                float v11_2 = ((Float) v1.getValueInternal(p10.startFrame, p10.endFrame.floatValue(), ((Float) p10.startValue), ((Float) p10.endValue), p11, this.getLinearCurrentKeyframeProgress(), this.getProgress()));
                if (v11_2 != 0) {
                    return v11_2.floatValue();
                }
            }
            return vn.ai.trueid.lottie.utils.MiscUtils.lerp(p10.getStartValueFloat(), p10.getEndValueFloat(), v6);
        }
    }

    public Float getValue(vn.ai.trueid.lottie.value.Keyframe p1, float p2)
    {
        return Float.valueOf(this.getFloatValue(p1, p2));
    }

    public bridge synthetic Object getValue(vn.ai.trueid.lottie.value.Keyframe p1, float p2)
    {
        return this.getValue(p1, p2);
    }
}
