package vn.ai.trueid.lottie.value;
public class LottieRelativePointValueCallback extends vn.ai.trueid.lottie.value.LottieValueCallback {
    private final android.graphics.PointF point;

    public LottieRelativePointValueCallback()
    {
        this.point = new android.graphics.PointF();
        return;
    }

    public LottieRelativePointValueCallback(android.graphics.PointF p1)
    {
        super(p1);
        super.point = new android.graphics.PointF();
        return;
    }

    public android.graphics.PointF getOffset(vn.ai.trueid.lottie.value.LottieFrameInfo p2)
    {
        IllegalArgumentException v2_0 = this.value;
        if (v2_0 == null) {
            throw new IllegalArgumentException("You must provide a static value in the constructor , call setValue, or override getValue.");
        } else {
            return ((android.graphics.PointF) v2_0);
        }
    }

    public final android.graphics.PointF getValue(vn.ai.trueid.lottie.value.LottieFrameInfo p6)
    {
        this.point.set(vn.ai.trueid.lottie.utils.MiscUtils.lerp(((android.graphics.PointF) p6.getStartValue()).x, ((android.graphics.PointF) p6.getEndValue()).x, p6.getInterpolatedKeyframeProgress()), vn.ai.trueid.lottie.utils.MiscUtils.lerp(((android.graphics.PointF) p6.getStartValue()).y, ((android.graphics.PointF) p6.getEndValue()).y, p6.getInterpolatedKeyframeProgress()));
        android.graphics.PointF v6_1 = this.getOffset(p6);
        this.point.offset(v6_1.x, v6_1.y);
        return this.point;
    }

    public bridge synthetic Object getValue(vn.ai.trueid.lottie.value.LottieFrameInfo p1)
    {
        return this.getValue(p1);
    }
}
