package vn.ai.trueid.lottie.animation.keyframe;
public class GradientColorKeyframeAnimation extends vn.ai.trueid.lottie.animation.keyframe.KeyframeAnimation {
    private final vn.ai.trueid.lottie.model.content.GradientColor gradientColor;

    public GradientColorKeyframeAnimation(java.util.List p4)
    {
        super(p4);
        int v0_0 = 0;
        int v1_0 = 0;
        while (v0_0 < p4.size()) {
            int v2_3 = ((vn.ai.trueid.lottie.model.content.GradientColor) ((vn.ai.trueid.lottie.value.Keyframe) p4.get(v0_0)).startValue);
            if (v2_3 != 0) {
                v1_0 = Math.max(v1_0, v2_3.getSize());
            }
            v0_0++;
        }
        int v0_1 = new float[v1_0];
        int v1_1 = new int[v1_0];
        super.gradientColor = new vn.ai.trueid.lottie.model.content.GradientColor(v0_1, v1_1);
        return;
    }

    public bridge synthetic Object getValue(vn.ai.trueid.lottie.value.Keyframe p1, float p2)
    {
        return this.getValue(p1, p2);
    }

    public vn.ai.trueid.lottie.model.content.GradientColor getValue(vn.ai.trueid.lottie.value.Keyframe p3, float p4)
    {
        this.gradientColor.lerp(((vn.ai.trueid.lottie.model.content.GradientColor) p3.startValue), ((vn.ai.trueid.lottie.model.content.GradientColor) p3.endValue), p4);
        return this.gradientColor;
    }
}
