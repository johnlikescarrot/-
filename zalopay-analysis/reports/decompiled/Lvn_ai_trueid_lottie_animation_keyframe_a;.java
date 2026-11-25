package vn.ai.trueid.lottie.animation.keyframe;
public final synthetic class a implements vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation$AnimationListener {
    public final synthetic int a;
    public final synthetic Object b;

    public synthetic a(int p1, Object p2)
    {
        this.a = p1;
        this.b = p2;
        return;
    }

    public final void onValueChanged()
    {
        switch (this.a) {
            case 0:
                vn.ai.trueid.lottie.animation.keyframe.TransformKeyframeAnimation.a(((vn.ai.trueid.lottie.animation.keyframe.TransformKeyframeAnimation) this.b));
                return;
            case 1:
                vn.ai.trueid.lottie.animation.keyframe.TransformKeyframeAnimation.b(((vn.ai.trueid.lottie.animation.keyframe.TransformKeyframeAnimation) this.b));
                return;
            case 2:
                vn.ai.trueid.lottie.animation.keyframe.TransformKeyframeAnimation.c(((vn.ai.trueid.lottie.animation.keyframe.TransformKeyframeAnimation) this.b));
                return;
            default:
                vn.ai.trueid.lottie.model.layer.BaseLayer.a(((vn.ai.trueid.lottie.model.layer.BaseLayer) this.b));
                return;
        }
    }
}
