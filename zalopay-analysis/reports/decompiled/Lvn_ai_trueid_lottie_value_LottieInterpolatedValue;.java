package vn.ai.trueid.lottie.value;
abstract class LottieInterpolatedValue extends vn.ai.trueid.lottie.value.LottieValueCallback {
    private final Object endValue;
    private final android.view.animation.Interpolator interpolator;
    private final Object startValue;

    public LottieInterpolatedValue(Object p2, Object p3)
    {
        this(p2, p3, new android.view.animation.LinearInterpolator());
        return;
    }

    public LottieInterpolatedValue(Object p1, Object p2, android.view.animation.Interpolator p3)
    {
        this.startValue = p1;
        this.endValue = p2;
        this.interpolator = p3;
        return;
    }

    public Object getValue(vn.ai.trueid.lottie.value.LottieFrameInfo p3)
    {
        return this.interpolateValue(this.startValue, this.endValue, this.interpolator.getInterpolation(p3.getOverallProgress()));
    }

    public abstract Object interpolateValue(Object p0, Object p1, float p2);
}
