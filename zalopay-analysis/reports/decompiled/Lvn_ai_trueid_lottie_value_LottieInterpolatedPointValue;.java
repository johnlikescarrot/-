package vn.ai.trueid.lottie.value;
public class LottieInterpolatedPointValue extends vn.ai.trueid.lottie.value.LottieInterpolatedValue {
    private final android.graphics.PointF point;

    public LottieInterpolatedPointValue(android.graphics.PointF p1, android.graphics.PointF p2)
    {
        super(p1, p2);
        super.point = new android.graphics.PointF();
        return;
    }

    public LottieInterpolatedPointValue(android.graphics.PointF p1, android.graphics.PointF p2, android.view.animation.Interpolator p3)
    {
        super(p1, p2, p3);
        super.point = new android.graphics.PointF();
        return;
    }

    public bridge synthetic Object getValue(vn.ai.trueid.lottie.value.LottieFrameInfo p1)
    {
        return super.getValue(p1);
    }

    public android.graphics.PointF interpolateValue(android.graphics.PointF p4, android.graphics.PointF p5, float p6)
    {
        this.point.set(vn.ai.trueid.lottie.utils.MiscUtils.lerp(p4.x, p5.x, p6), vn.ai.trueid.lottie.utils.MiscUtils.lerp(p4.y, p5.y, p6));
        return this.point;
    }

    public bridge synthetic Object interpolateValue(Object p1, Object p2, float p3)
    {
        return this.interpolateValue(((android.graphics.PointF) p1), ((android.graphics.PointF) p2), p3);
    }
}
