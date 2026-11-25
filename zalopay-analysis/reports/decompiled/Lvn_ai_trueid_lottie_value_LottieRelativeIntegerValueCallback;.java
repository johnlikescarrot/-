package vn.ai.trueid.lottie.value;
public class LottieRelativeIntegerValueCallback extends vn.ai.trueid.lottie.value.LottieValueCallback {

    public LottieRelativeIntegerValueCallback()
    {
        return;
    }

    public Integer getOffset(vn.ai.trueid.lottie.value.LottieFrameInfo p2)
    {
        IllegalArgumentException v2_0 = this.value;
        if (v2_0 == null) {
            throw new IllegalArgumentException("You must provide a static value in the constructor , call setValue, or override getValue.");
        } else {
            return ((Integer) v2_0);
        }
    }

    public Integer getValue(vn.ai.trueid.lottie.value.LottieFrameInfo p4)
    {
        return Integer.valueOf((this.getOffset(p4).intValue() + vn.ai.trueid.lottie.utils.MiscUtils.lerp(((Integer) p4.getStartValue()).intValue(), ((Integer) p4.getEndValue()).intValue(), p4.getInterpolatedKeyframeProgress())));
    }

    public bridge synthetic Object getValue(vn.ai.trueid.lottie.value.LottieFrameInfo p1)
    {
        return this.getValue(p1);
    }
}
