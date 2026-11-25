package vn.ai.trueid.lottie.model.layer;
public class ShapeLayer extends vn.ai.trueid.lottie.model.layer.BaseLayer {
    private final vn.ai.trueid.lottie.model.layer.CompositionLayer compositionLayer;
    private final vn.ai.trueid.lottie.animation.content.ContentGroup contentGroup;
    private vn.ai.trueid.lottie.animation.keyframe.DropShadowKeyframeAnimation dropShadowAnimation;

    public ShapeLayer(vn.ai.trueid.lottie.LottieDrawable p3, vn.ai.trueid.lottie.model.layer.Layer p4, vn.ai.trueid.lottie.model.layer.CompositionLayer p5, vn.ai.trueid.lottie.LottieComposition p6)
    {
        super(p3, p4);
        super.compositionLayer = p5;
        vn.ai.trueid.lottie.parser.DropShadowEffect v4_4 = new vn.ai.trueid.lottie.animation.content.ContentGroup(p3, super, new vn.ai.trueid.lottie.model.content.ShapeGroup("__container", p4.getShapes(), 0), p6);
        super.contentGroup = v4_4;
        v4_4.setContents(java.util.Collections.EMPTY_LIST, java.util.Collections.EMPTY_LIST);
        if (super.getDropShadowEffect() != null) {
            super.dropShadowAnimation = new vn.ai.trueid.lottie.animation.keyframe.DropShadowKeyframeAnimation(super, super, super.getDropShadowEffect());
        }
        return;
    }

    public void addValueCallback(Object p2, vn.ai.trueid.lottie.value.LottieValueCallback p3)
    {
        super.addValueCallback(p2, p3);
        if (p2 == vn.ai.trueid.lottie.LottieProperty.DROP_SHADOW_COLOR) {
            Float v0_5 = this.dropShadowAnimation;
            if (v0_5 != null) {
                v0_5.setColorCallback(p3);
                return;
            }
        }
        if (p2 == vn.ai.trueid.lottie.LottieProperty.DROP_SHADOW_OPACITY) {
            Float v0_7 = this.dropShadowAnimation;
            if (v0_7 != null) {
                v0_7.setOpacityCallback(p3);
                return;
            }
        }
        if (p2 == vn.ai.trueid.lottie.LottieProperty.DROP_SHADOW_DIRECTION) {
            Float v0_1 = this.dropShadowAnimation;
            if (v0_1 != null) {
                v0_1.setDirectionCallback(p3);
                return;
            }
        }
        if (p2 == vn.ai.trueid.lottie.LottieProperty.DROP_SHADOW_DISTANCE) {
            Float v0_3 = this.dropShadowAnimation;
            if (v0_3 != null) {
                v0_3.setDistanceCallback(p3);
                return;
            }
        }
        if (p2 == vn.ai.trueid.lottie.LottieProperty.DROP_SHADOW_RADIUS) {
            vn.ai.trueid.lottie.animation.keyframe.DropShadowKeyframeAnimation v2_1 = this.dropShadowAnimation;
            if (v2_1 != null) {
                v2_1.setRadiusCallback(p3);
            }
        }
        return;
    }

    public void drawLayer(android.graphics.Canvas p2, android.graphics.Matrix p3, int p4, vn.ai.trueid.lottie.utils.DropShadow p5)
    {
        vn.ai.trueid.lottie.animation.content.ContentGroup v0_0 = this.dropShadowAnimation;
        if (v0_0 != null) {
            p5 = v0_0.evaluate(p3, p4);
        }
        this.contentGroup.draw(p2, p3, p4, p5);
        return;
    }

    public vn.ai.trueid.lottie.model.content.BlurEffect getBlurEffect()
    {
        vn.ai.trueid.lottie.model.content.BlurEffect v0_0 = super.getBlurEffect();
        if (v0_0 == null) {
            return this.compositionLayer.getBlurEffect();
        } else {
            return v0_0;
        }
    }

    public void getBounds(android.graphics.RectF p2, android.graphics.Matrix p3, boolean p4)
    {
        super.getBounds(p2, p3, p4);
        this.contentGroup.getBounds(p2, this.boundsMatrix, p4);
        return;
    }

    public void resolveChildKeyPath(vn.ai.trueid.lottie.model.KeyPath p2, int p3, java.util.List p4, vn.ai.trueid.lottie.model.KeyPath p5)
    {
        this.contentGroup.resolveKeyPath(p2, p3, p4, p5);
        return;
    }
}
