package vn.ai.trueid.lottie.animation.keyframe;
public class MaskKeyframeAnimation {
    private final java.util.List maskAnimations;
    private final java.util.List masks;
    private final java.util.List opacityAnimations;

    public MaskKeyframeAnimation(java.util.List p4)
    {
        this.masks = p4;
        this.maskAnimations = new java.util.ArrayList(p4.size());
        this.opacityAnimations = new java.util.ArrayList(p4.size());
        int v0_1 = 0;
        while (v0_1 < p4.size()) {
            this.maskAnimations.add(((vn.ai.trueid.lottie.model.content.Mask) p4.get(v0_1)).getMaskPath().createAnimation());
            this.opacityAnimations.add(((vn.ai.trueid.lottie.model.content.Mask) p4.get(v0_1)).getOpacity().createAnimation());
            v0_1++;
        }
        return;
    }

    public java.util.List getMaskAnimations()
    {
        return this.maskAnimations;
    }

    public java.util.List getMasks()
    {
        return this.masks;
    }

    public java.util.List getOpacityAnimations()
    {
        return this.opacityAnimations;
    }
}
