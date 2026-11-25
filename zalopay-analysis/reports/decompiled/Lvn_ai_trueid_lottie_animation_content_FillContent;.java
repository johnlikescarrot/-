package vn.ai.trueid.lottie.animation.content;
public class FillContent implements vn.ai.trueid.lottie.animation.content.DrawingContent, vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation$AnimationListener, vn.ai.trueid.lottie.animation.content.KeyPathElementContent {
    private vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation blurAnimation;
    float blurMaskFilterRadius;
    private final vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation colorAnimation;
    private vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation colorFilterAnimation;
    private final boolean hidden;
    private final vn.ai.trueid.lottie.model.layer.BaseLayer layer;
    private final vn.ai.trueid.lottie.LottieDrawable lottieDrawable;
    private final String name;
    private final vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation opacityAnimation;
    private final android.graphics.Paint paint;
    private final android.graphics.Path path;
    private final java.util.List paths;

    public FillContent(vn.ai.trueid.lottie.LottieDrawable p4, vn.ai.trueid.lottie.model.layer.BaseLayer p5, vn.ai.trueid.lottie.model.content.ShapeFill p6)
    {
        android.graphics.Path v0_1 = new android.graphics.Path();
        this.path = v0_1;
        this.paint = new vn.ai.trueid.lottie.animation.LPaint(1);
        this.paths = new java.util.ArrayList();
        this.layer = p5;
        this.name = p6.getName();
        this.hidden = p6.isHidden();
        this.lottieDrawable = p4;
        if (p5.getBlurEffect() != null) {
            vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation v4_4 = p5.getBlurEffect().getBlurriness().createAnimation();
            this.blurAnimation = v4_4;
            v4_4.addUpdateListener(this);
            p5.addAnimation(this.blurAnimation);
        }
        if ((p6.getColor() != null) && (p6.getOpacity() != null)) {
            v0_1.setFillType(p6.getFillType());
            vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation v4_10 = p6.getColor().createAnimation();
            this.colorAnimation = v4_10;
            v4_10.addUpdateListener(this);
            p5.addAnimation(v4_10);
            vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation v4_12 = p6.getOpacity().createAnimation();
            this.opacityAnimation = v4_12;
            v4_12.addUpdateListener(this);
            p5.addAnimation(v4_12);
            return;
        } else {
            this.colorAnimation = 0;
            this.opacityAnimation = 0;
            return;
        }
    }

    public void addValueCallback(Object p2, vn.ai.trueid.lottie.value.LottieValueCallback p3)
    {
        if (p2 != vn.ai.trueid.lottie.LottieProperty.COLOR) {
            if (p2 != vn.ai.trueid.lottie.LottieProperty.OPACITY) {
                if (p2 != vn.ai.trueid.lottie.LottieProperty.COLOR_FILTER) {
                    if (p2 == vn.ai.trueid.lottie.LottieProperty.BLUR_RADIUS) {
                        vn.ai.trueid.lottie.model.layer.BaseLayer v2_11 = this.blurAnimation;
                        if (v2_11 == null) {
                            vn.ai.trueid.lottie.model.layer.BaseLayer v2_2 = new vn.ai.trueid.lottie.animation.keyframe.ValueCallbackKeyframeAnimation(p3);
                            this.blurAnimation = v2_2;
                            v2_2.addUpdateListener(this);
                            this.layer.addAnimation(this.blurAnimation);
                        } else {
                            v2_11.setValueCallback(p3);
                            return;
                        }
                    }
                    return;
                } else {
                    vn.ai.trueid.lottie.model.layer.BaseLayer v2_4 = this.colorFilterAnimation;
                    if (v2_4 != null) {
                        this.layer.removeAnimation(v2_4);
                    }
                    if (p3 != null) {
                        vn.ai.trueid.lottie.model.layer.BaseLayer v2_6 = new vn.ai.trueid.lottie.animation.keyframe.ValueCallbackKeyframeAnimation(p3);
                        this.colorFilterAnimation = v2_6;
                        v2_6.addUpdateListener(this);
                        this.layer.addAnimation(this.colorFilterAnimation);
                        return;
                    } else {
                        this.colorFilterAnimation = 0;
                        return;
                    }
                }
            } else {
                this.opacityAnimation.setValueCallback(p3);
                return;
            }
        } else {
            this.colorAnimation.setValueCallback(p3);
            return;
        }
    }

    public void draw(android.graphics.Canvas p7, android.graphics.Matrix p8, int p9, vn.ai.trueid.lottie.utils.DropShadow p10)
    {
        if (!this.hidden) {
            android.graphics.BlurMaskFilter v0_3 = vn.ai.trueid.lottie.L.isTraceEnabled();
            String v1_1 = wmoi.igob.ggcn(14493);
            if (v0_3 != null) {
                vn.ai.trueid.lottie.L.beginSection(v1_1);
            }
            float v2_5 = (((float) ((Integer) this.opacityAnimation.getValue()).intValue()) / 1120403456);
            int v4 = 0;
            this.paint.setColor(((vn.ai.trueid.lottie.utils.MiscUtils.clamp(((int) (((float) p9) * v2_5)), 0, 255) << 24) | (((vn.ai.trueid.lottie.animation.keyframe.ColorKeyframeAnimation) this.colorAnimation).getIntValue() & 16777215)));
            android.graphics.Paint v9_7 = this.colorFilterAnimation;
            if (v9_7 != null) {
                this.paint.setColorFilter(((android.graphics.ColorFilter) v9_7.getValue()));
            }
            android.graphics.Paint v9_10 = this.blurAnimation;
            if (v9_10 != null) {
                android.graphics.Paint v9_13 = ((Float) v9_10.getValue()).floatValue();
                if (v9_13 != 0) {
                    if (v9_13 != this.blurMaskFilterRadius) {
                        this.paint.setMaskFilter(this.layer.getBlurMaskFilter(v9_13));
                    }
                } else {
                    this.paint.setMaskFilter(0);
                }
                this.blurMaskFilterRadius = v9_13;
            }
            if (p10 == null) {
                this.paint.clearShadowLayer();
            } else {
                p10.applyWithAlpha(((int) (v2_5 * 1132396544)), this.paint);
            }
            this.path.reset();
            while (v4 < this.paths.size()) {
                this.path.addPath(((vn.ai.trueid.lottie.animation.content.PathContent) this.paths.get(v4)).getPath(), p8);
                v4++;
            }
            p7.drawPath(this.path, this.paint);
            if (vn.ai.trueid.lottie.L.isTraceEnabled()) {
                vn.ai.trueid.lottie.L.endSection(v1_1);
            }
        }
        return;
    }

    public void getBounds(android.graphics.RectF p4, android.graphics.Matrix p5, boolean p6)
    {
        this.path.reset();
        float v0_2 = 0;
        while (v0_2 < this.paths.size()) {
            this.path.addPath(((vn.ai.trueid.lottie.animation.content.PathContent) this.paths.get(v0_2)).getPath(), p5);
            v0_2++;
        }
        this.path.computeBounds(p4, 0);
        p4.set((p4.left - 1065353216), (p4.top - 1065353216), (p4.right + 1065353216), (p4.bottom + 1065353216));
        return;
    }

    public String getName()
    {
        return this.name;
    }

    public void onValueChanged()
    {
        this.lottieDrawable.invalidateSelf();
        return;
    }

    public void resolveKeyPath(vn.ai.trueid.lottie.model.KeyPath p1, int p2, java.util.List p3, vn.ai.trueid.lottie.model.KeyPath p4)
    {
        vn.ai.trueid.lottie.utils.MiscUtils.resolveKeyPath(p1, p2, p3, p4, this);
        return;
    }

    public void setContents(java.util.List p3, java.util.List p4)
    {
        p3 = 0;
        while (p3 < p4.size()) {
            vn.ai.trueid.lottie.animation.content.PathContent v0_3 = ((vn.ai.trueid.lottie.animation.content.Content) p4.get(p3));
            if ((v0_3 instanceof vn.ai.trueid.lottie.animation.content.PathContent)) {
                this.paths.add(((vn.ai.trueid.lottie.animation.content.PathContent) v0_3));
            }
            p3++;
        }
        return;
    }
}
