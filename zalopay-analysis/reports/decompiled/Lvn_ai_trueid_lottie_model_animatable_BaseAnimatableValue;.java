package vn.ai.trueid.lottie.model.animatable;
abstract class BaseAnimatableValue implements vn.ai.trueid.lottie.model.animatable.AnimatableValue {
    final java.util.List keyframes;

    public BaseAnimatableValue(Object p2)
    {
        this(java.util.Collections.singletonList(new vn.ai.trueid.lottie.value.Keyframe(p2)));
        return;
    }

    public BaseAnimatableValue(java.util.List p1)
    {
        this.keyframes = p1;
        return;
    }

    public java.util.List getKeyframes()
    {
        return this.keyframes;
    }

    public boolean isStatic()
    {
        if ((!this.keyframes.isEmpty()) && ((this.keyframes.size() != 1) || (!((vn.ai.trueid.lottie.value.Keyframe) this.keyframes.get(0)).isStatic()))) {
            return 0;
        } else {
            return 1;
        }
    }

    public String toString()
    {
        String v0_1 = new StringBuilder();
        if (!this.keyframes.isEmpty()) {
            v0_1.append(wmoi.igob.ggcn(9294));
            v0_1.append(java.util.Arrays.toString(this.keyframes.toArray()));
        }
        return v0_1.toString();
    }
}
