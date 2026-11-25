package vn.ai.trueid.lottie.animation.keyframe;
public class PathKeyframeAnimation extends vn.ai.trueid.lottie.animation.keyframe.KeyframeAnimation {
    private final android.graphics.PathMeasure pathMeasure;
    private vn.ai.trueid.lottie.animation.keyframe.PathKeyframe pathMeasureKeyframe;
    private final android.graphics.PointF point;
    private final float[] pos;
    private final float[] tangent;

    public PathKeyframeAnimation(java.util.List p2)
    {
        super(p2);
        super.point = new android.graphics.PointF();
        float[] v0 = new float[2];
        super.pos = v0;
        android.graphics.PathMeasure v2_5 = new float[2];
        super.tangent = v2_5;
        super.pathMeasure = new android.graphics.PathMeasure();
        return;
    }

    public android.graphics.PointF getValue(vn.ai.trueid.lottie.value.Keyframe p11, float p12)
    {
        float v8;
        android.graphics.PointF v1_0 = ((vn.ai.trueid.lottie.animation.keyframe.PathKeyframe) p11).getPath();
        float[] v2_1 = this.valueCallback;
        if ((v2_1 == null) || (p11.endFrame == null)) {
            v8 = p12;
        } else {
            v8 = p12;
            float v12_2 = ((android.graphics.PointF) v2_1.getValueInternal(((vn.ai.trueid.lottie.animation.keyframe.PathKeyframe) p11).startFrame, ((vn.ai.trueid.lottie.animation.keyframe.PathKeyframe) p11).endFrame.floatValue(), ((android.graphics.PointF) ((vn.ai.trueid.lottie.animation.keyframe.PathKeyframe) p11).startValue), ((android.graphics.PointF) ((vn.ai.trueid.lottie.animation.keyframe.PathKeyframe) p11).endValue), this.getLinearCurrentKeyframeProgress(), p12, this.getProgress()));
            if (v12_2 != 0) {
                return v12_2;
            }
        }
        if (v1_0 != null) {
            if (this.pathMeasureKeyframe != ((vn.ai.trueid.lottie.animation.keyframe.PathKeyframe) p11)) {
                this.pathMeasure.setPath(v1_0, 0);
                this.pathMeasureKeyframe = ((vn.ai.trueid.lottie.animation.keyframe.PathKeyframe) p11);
            }
            float v11_4 = this.pathMeasure.getLength();
            float v0_2 = (v8 * v11_4);
            this.pathMeasure.getPosTan(v0_2, this.pos, this.tangent);
            float[] v2_2 = this.pos;
            this.point.set(v2_2[0], v2_2[1]);
            if (v0_2 >= 0) {
                if (v0_2 > v11_4) {
                    float[] v2_4 = this.tangent;
                    float v0_3 = (v0_2 - v11_4);
                    this.point.offset((v2_4[0] * v0_3), (v2_4[1] * v0_3));
                }
            } else {
                android.graphics.PointF v1_7 = this.tangent;
                this.point.offset((v1_7[0] * v0_2), (v1_7[1] * v0_2));
            }
            return this.point;
        } else {
            return ((android.graphics.PointF) p11.startValue);
        }
    }

    public bridge synthetic Object getValue(vn.ai.trueid.lottie.value.Keyframe p1, float p2)
    {
        return this.getValue(p1, p2);
    }
}
