package vn.ai.trueid.lottie.animation.keyframe;
public class ScaleKeyframeAnimation extends vn.ai.trueid.lottie.animation.keyframe.KeyframeAnimation {
    private final vn.ai.trueid.lottie.value.ScaleXY scaleXY;

    public ScaleKeyframeAnimation(java.util.List p1)
    {
        super(p1);
        super.scaleXY = new vn.ai.trueid.lottie.value.ScaleXY();
        return;
    }

    public bridge synthetic Object getValue(vn.ai.trueid.lottie.value.Keyframe p1, float p2)
    {
        return this.getValue(p1, p2);
    }

    public vn.ai.trueid.lottie.value.ScaleXY getValue(vn.ai.trueid.lottie.value.Keyframe p11, float p12)
    {
        float v0_0 = p11.startValue;
        if (v0_0 != 0) {
            float v1_0 = p11.endValue;
            if (v1_0 != 0) {
                String v7;
                vn.ai.trueid.lottie.value.ScaleXY v5_1 = ((vn.ai.trueid.lottie.value.ScaleXY) v0_0);
                vn.ai.trueid.lottie.value.ScaleXY v6_1 = ((vn.ai.trueid.lottie.value.ScaleXY) v1_0);
                vn.ai.trueid.lottie.value.LottieValueCallback v2 = this.valueCallback;
                if (v2 == null) {
                    v7 = p12;
                } else {
                    v7 = p12;
                    vn.ai.trueid.lottie.value.ScaleXY v11_3 = ((vn.ai.trueid.lottie.value.ScaleXY) v2.getValueInternal(p11.startFrame, p11.endFrame.floatValue(), v5_1, v6_1, p12, this.getLinearCurrentKeyframeProgress(), this.getProgress()));
                    if (v11_3 != null) {
                        return v11_3;
                    }
                }
                this.scaleXY.set(vn.ai.trueid.lottie.utils.MiscUtils.lerp(v5_1.getScaleX(), v6_1.getScaleX(), v7), vn.ai.trueid.lottie.utils.MiscUtils.lerp(v5_1.getScaleY(), v6_1.getScaleY(), v7));
                return this.scaleXY;
            }
        }
        throw new IllegalStateException("Missing values for keyframe.");
    }
}
