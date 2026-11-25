package vn.ai.trueid.lottie.model.animatable;
public class AnimatableGradientColorValue extends vn.ai.trueid.lottie.model.animatable.BaseAnimatableValue {

    public AnimatableGradientColorValue(java.util.List p1)
    {
        super(vn.ai.trueid.lottie.model.animatable.AnimatableGradientColorValue.ensureInterpolatableKeyframes(p1));
        return;
    }

    private static vn.ai.trueid.lottie.value.Keyframe ensureInterpolatableKeyframe(vn.ai.trueid.lottie.value.Keyframe p4)
    {
        vn.ai.trueid.lottie.model.content.GradientColor v0_1 = ((vn.ai.trueid.lottie.model.content.GradientColor) p4.startValue);
        vn.ai.trueid.lottie.model.content.GradientColor v1_2 = ((vn.ai.trueid.lottie.model.content.GradientColor) p4.endValue);
        if ((v0_1 != null) && ((v1_2 != null) && (v0_1.getPositions().length != v1_2.getPositions().length))) {
            float[] v2_1 = vn.ai.trueid.lottie.model.animatable.AnimatableGradientColorValue.mergePositions(v0_1.getPositions(), v1_2.getPositions());
            p4 = p4.copyWith(v0_1.copyWithPositions(v2_1), v1_2.copyWithPositions(v2_1));
        }
        return p4;
    }

    private static java.util.List ensureInterpolatableKeyframes(java.util.List p2)
    {
        int v0 = 0;
        while (v0 < p2.size()) {
            p2.set(v0, vn.ai.trueid.lottie.model.animatable.AnimatableGradientColorValue.ensureInterpolatableKeyframe(((vn.ai.trueid.lottie.value.Keyframe) p2.get(v0))));
            v0++;
        }
        return p2;
    }

    public static float[] mergePositions(float[] p6, float[] p7)
    {
        int v0_1 = (p6.length + p7.length);
        float[] v1_1 = new float[v0_1];
        System.arraycopy(p6, 0, v1_1, 0, p6.length);
        System.arraycopy(p7, 0, v1_1, p6.length, p7.length);
        java.util.Arrays.sort(v1_1);
        float v6_1 = 2143289344;
        int v7_1 = 0;
        int v2_0 = 0;
        while (v7_1 < v0_1) {
            float v4 = v1_1[v7_1];
            if (v4 != v6_1) {
                v1_1[v2_0] = v4;
                v2_0++;
                v6_1 = v1_1[v7_1];
            }
            v7_1++;
        }
        return java.util.Arrays.copyOfRange(v1_1, 0, v2_0);
    }

    public vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation createAnimation()
    {
        return new vn.ai.trueid.lottie.animation.keyframe.GradientColorKeyframeAnimation(this.keyframes);
    }

    public bridge synthetic java.util.List getKeyframes()
    {
        return super.getKeyframes();
    }

    public bridge synthetic boolean isStatic()
    {
        return super.isStatic();
    }

    public bridge synthetic String toString()
    {
        return super.toString();
    }
}
