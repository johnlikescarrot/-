package vn.ai.trueid.lottie.value;
public class LottieRelativeFloatValueCallback extends vn.ai.trueid.lottie.value.LottieValueCallback {

    public LottieRelativeFloatValueCallback()
    {
        return;
    }

    public LottieRelativeFloatValueCallback(Float p1)
    {
        super(p1);
        return;
    }

    public Float getOffset(vn.ai.trueid.lottie.value.LottieFrameInfo p2)
    {
        IllegalArgumentException v2_0 = this.value;
        if (v2_0 == null) {
            throw new IllegalArgumentException("You must provide a static value in the constructor , call setValue, or override getValue.");
        } else {
            return ((Float) v2_0);
        }
    }

    public Float getValue(vn.ai.trueid.lottie.value.LottieFrameInfo p4)
    {
        return Float.valueOf((this.getOffset(p4).floatValue() + vn.ai.trueid.lottie.utils.MiscUtils.lerp(((Float) p4.getStartValue()).floatValue(), ((Float) p4.getEndValue()).floatValue(), p4.getInterpolatedKeyframeProgress())));
    }

    public bridge synthetic Object getValue(vn.ai.trueid.lottie.value.LottieFrameInfo p1)
    {
        return this.getValue(p1);
    }
}
