package vn.ai.trueid.lottie.animation.content;
public class StrokeContent extends vn.ai.trueid.lottie.animation.content.BaseStrokeContent {
    private final vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation colorAnimation;
    private vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation colorFilterAnimation;
    private final boolean hidden;
    private final vn.ai.trueid.lottie.model.layer.BaseLayer layer;
    private final String name;

    public StrokeContent(vn.ai.trueid.lottie.LottieDrawable p12, vn.ai.trueid.lottie.model.layer.BaseLayer p13, vn.ai.trueid.lottie.model.content.ShapeStroke p14)
    {
        vn.ai.trueid.lottie.animation.content.StrokeContent v1_1 = super(p12, p13, p14.getCapType().toPaintCap(), p14.getJoinType().toPaintJoin(), p14.getMiterLimit(), p14.getOpacity(), p14.getWidth(), p14.getLineDashPattern(), p14.getDashOffset());
        v1_1.layer = p13;
        v1_1.name = p14.getName();
        v1_1.hidden = p14.isHidden();
        vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation v12_4 = p14.getColor().createAnimation();
        v1_1.colorAnimation = v12_4;
        v12_4.addUpdateListener(super);
        p13.addAnimation(v12_4);
        return;
    }

    public void addValueCallback(Object p2, vn.ai.trueid.lottie.value.LottieValueCallback p3)
    {
        super.addValueCallback(p2, p3);
        if (p2 != vn.ai.trueid.lottie.LottieProperty.STROKE_COLOR) {
            if (p2 == vn.ai.trueid.lottie.LottieProperty.COLOR_FILTER) {
                vn.ai.trueid.lottie.model.layer.BaseLayer v2_6 = this.colorFilterAnimation;
                if (v2_6 != null) {
                    this.layer.removeAnimation(v2_6);
                }
                if (p3 != null) {
                    vn.ai.trueid.lottie.model.layer.BaseLayer v2_2 = new vn.ai.trueid.lottie.animation.keyframe.ValueCallbackKeyframeAnimation(p3);
                    this.colorFilterAnimation = v2_2;
                    v2_2.addUpdateListener(this);
                    this.layer.addAnimation(this.colorAnimation);
                } else {
                    this.colorFilterAnimation = 0;
                    return;
                }
            }
            return;
        } else {
            this.colorAnimation.setValueCallback(p3);
            return;
        }
    }

    public void draw(android.graphics.Canvas p3, android.graphics.Matrix p4, int p5, vn.ai.trueid.lottie.utils.DropShadow p6)
    {
        if (!this.hidden) {
            this.paint.setColor(((vn.ai.trueid.lottie.animation.keyframe.ColorKeyframeAnimation) this.colorAnimation).getIntValue());
            android.graphics.ColorFilter v0_4 = this.colorFilterAnimation;
            if (v0_4 != null) {
                this.paint.setColorFilter(((android.graphics.ColorFilter) v0_4.getValue()));
            }
            super.draw(p3, p4, p5, p6);
            return;
        } else {
            return;
        }
    }

    public String getName()
    {
        return this.name;
    }
}
