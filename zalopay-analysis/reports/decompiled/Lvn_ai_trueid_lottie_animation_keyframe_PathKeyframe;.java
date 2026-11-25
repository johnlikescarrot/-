package vn.ai.trueid.lottie.animation.keyframe;
public class PathKeyframe extends vn.ai.trueid.lottie.value.Keyframe {
    private android.graphics.Path path;
    private final vn.ai.trueid.lottie.value.Keyframe pointKeyFrame;

    public PathKeyframe(vn.ai.trueid.lottie.LottieComposition p11, vn.ai.trueid.lottie.value.Keyframe p12)
    {
        super(p11, ((android.graphics.PointF) p12.startValue), ((android.graphics.PointF) p12.endValue), p12.interpolator, p12.xInterpolator, p12.yInterpolator, p12.startFrame, p12.endFrame).pointKeyFrame = p12;
        super.createPath();
        return;
    }

    public void createPath()
    {
        android.graphics.Path v0_2;
        android.graphics.Path v0_0 = this.endValue;
        if (v0_0 == null) {
            v0_2 = 0;
        } else {
            android.graphics.PointF v1_1 = this.startValue;
            if ((v1_1 == null) || (!((android.graphics.PointF) v1_1).equals(((android.graphics.PointF) v0_0).x, ((android.graphics.PointF) v0_0).y))) {
            } else {
                v0_2 = 1;
            }
        }
        android.graphics.PointF v1_0 = this.startValue;
        if (v1_0 != null) {
            android.graphics.PointF v2_0 = this.endValue;
            if ((v2_0 != null) && (v0_2 == null)) {
                android.graphics.Path v0_3 = this.pointKeyFrame;
                this.path = vn.ai.trueid.lottie.utils.Utils.createPath(((android.graphics.PointF) v1_0), ((android.graphics.PointF) v2_0), v0_3.pathCp1, v0_3.pathCp2);
            }
        }
        return;
    }

    public android.graphics.Path getPath()
    {
        return this.path;
    }
}
