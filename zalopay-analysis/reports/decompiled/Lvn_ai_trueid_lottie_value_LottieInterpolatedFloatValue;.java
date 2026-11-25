package vn.ai.trueid.lottie.value;
public class LottieInterpolatedFloatValue extends vn.ai.trueid.lottie.value.LottieInterpolatedValue {

    public LottieInterpolatedFloatValue(Float p1, Float p2)
    {
        super(p1, p2);
        return;
    }

    public LottieInterpolatedFloatValue(Float p1, Float p2, android.view.animation.Interpolator p3)
    {
        super(p1, p2, p3);
        return;
    }

    public bridge synthetic Object getValue(vn.ai.trueid.lottie.value.LottieFrameInfo p1)
    {
        return super.getValue(p1);
    }

    public Float interpolateValue(Float p1, Float p2, float p3)
    {
        return Float.valueOf(vn.ai.trueid.lottie.utils.MiscUtils.lerp(p1.floatValue(), p2.floatValue(), p3));
    }

    public bridge synthetic Object interpolateValue(Object p1, Object p2, float p3)
    {
        return this.interpolateValue(((Float) p1), ((Float) p2), p3);
    }
}
