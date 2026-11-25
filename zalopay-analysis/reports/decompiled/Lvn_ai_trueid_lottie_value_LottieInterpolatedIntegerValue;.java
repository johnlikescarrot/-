package vn.ai.trueid.lottie.value;
public class LottieInterpolatedIntegerValue extends vn.ai.trueid.lottie.value.LottieInterpolatedValue {

    public LottieInterpolatedIntegerValue(Integer p1, Integer p2)
    {
        super(p1, p2);
        return;
    }

    public LottieInterpolatedIntegerValue(Integer p1, Integer p2, android.view.animation.Interpolator p3)
    {
        super(p1, p2, p3);
        return;
    }

    public bridge synthetic Object getValue(vn.ai.trueid.lottie.value.LottieFrameInfo p1)
    {
        return super.getValue(p1);
    }

    public Integer interpolateValue(Integer p1, Integer p2, float p3)
    {
        return Integer.valueOf(vn.ai.trueid.lottie.utils.MiscUtils.lerp(p1.intValue(), p2.intValue(), p3));
    }

    public bridge synthetic Object interpolateValue(Object p1, Object p2, float p3)
    {
        return this.interpolateValue(((Integer) p1), ((Integer) p2), p3);
    }
}
