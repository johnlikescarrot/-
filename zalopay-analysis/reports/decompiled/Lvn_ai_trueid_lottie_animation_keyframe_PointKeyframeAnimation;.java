package vn.ai.trueid.lottie.animation.keyframe;
public class PointKeyframeAnimation extends vn.ai.trueid.lottie.animation.keyframe.KeyframeAnimation {
    private final android.graphics.PointF point;

    public PointKeyframeAnimation(java.util.List p1)
    {
        super(p1);
        super.point = new android.graphics.PointF();
        return;
    }

    public android.graphics.PointF getValue(vn.ai.trueid.lottie.value.Keyframe p1, float p2)
    {
        return this.getValue(p1, p2, p2, p2);
    }

    public android.graphics.PointF getValue(vn.ai.trueid.lottie.value.Keyframe p11, float p12, float p13, float p14)
    {
        float v0_0 = p11.startValue;
        if (v0_0 != 0) {
            Object v1 = p11.endValue;
            if (v1 != null) {
                android.graphics.PointF v5_1 = ((android.graphics.PointF) v0_0);
                android.graphics.PointF v6_1 = ((android.graphics.PointF) v1);
                vn.ai.trueid.lottie.value.LottieValueCallback v2 = this.valueCallback;
                if (v2 != null) {
                    android.graphics.PointF v11_3 = ((android.graphics.PointF) v2.getValueInternal(p11.startFrame, p11.endFrame.floatValue(), v5_1, v6_1, p12, this.getLinearCurrentKeyframeProgress(), this.getProgress()));
                    if (v11_3 != null) {
                        return v11_3;
                    }
                }
                this.point.set(androidx.activity.result.e.a(v6_1.x, v5_1.x, p13, v5_1.x), androidx.activity.result.e.a(v6_1.y, v5_1.y, p14, v5_1.y));
                return this.point;
            }
        }
        throw new IllegalStateException("Missing values for keyframe.");
    }

    public bridge synthetic Object getValue(vn.ai.trueid.lottie.value.Keyframe p1, float p2)
    {
        return this.getValue(p1, p2);
    }

    public bridge synthetic Object getValue(vn.ai.trueid.lottie.value.Keyframe p1, float p2, float p3, float p4)
    {
        return this.getValue(p1, p2, p3, p4);
    }
}
