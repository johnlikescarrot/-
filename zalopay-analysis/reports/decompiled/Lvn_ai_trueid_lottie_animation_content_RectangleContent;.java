package vn.ai.trueid.lottie.animation.content;
public class RectangleContent implements vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation$AnimationListener, vn.ai.trueid.lottie.animation.content.KeyPathElementContent, vn.ai.trueid.lottie.animation.content.PathContent {
    private final vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation cornerRadiusAnimation;
    private final boolean hidden;
    private boolean isPathValid;
    private final vn.ai.trueid.lottie.LottieDrawable lottieDrawable;
    private final String name;
    private final android.graphics.Path path;
    private final vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation positionAnimation;
    private final android.graphics.RectF rect;
    private vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation roundedCornersAnimation;
    private final vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation sizeAnimation;
    private final vn.ai.trueid.lottie.animation.content.CompoundTrimPathContent trimPaths;

    public RectangleContent(vn.ai.trueid.lottie.LottieDrawable p2, vn.ai.trueid.lottie.model.layer.BaseLayer p3, vn.ai.trueid.lottie.model.content.RectangleShape p4)
    {
        this.path = new android.graphics.Path();
        this.rect = new android.graphics.RectF();
        this.trimPaths = new vn.ai.trueid.lottie.animation.content.CompoundTrimPathContent();
        this.roundedCornersAnimation = 0;
        this.name = p4.getName();
        this.hidden = p4.isHidden();
        this.lottieDrawable = p2;
        vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation v2_2 = p4.getPosition().createAnimation();
        this.positionAnimation = v2_2;
        vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation v0_6 = p4.getSize().createAnimation();
        this.sizeAnimation = v0_6;
        vn.ai.trueid.lottie.animation.keyframe.FloatKeyframeAnimation v4_2 = p4.getCornerRadius().createAnimation();
        this.cornerRadiusAnimation = v4_2;
        p3.addAnimation(v2_2);
        p3.addAnimation(v0_6);
        p3.addAnimation(v4_2);
        v2_2.addUpdateListener(this);
        v0_6.addUpdateListener(this);
        v4_2.addUpdateListener(this);
        return;
    }

    private void invalidate()
    {
        this.isPathValid = 0;
        this.lottieDrawable.invalidateSelf();
        return;
    }

    public void addValueCallback(Object p2, vn.ai.trueid.lottie.value.LottieValueCallback p3)
    {
        if (p2 != vn.ai.trueid.lottie.LottieProperty.RECTANGLE_SIZE) {
            if (p2 != vn.ai.trueid.lottie.LottieProperty.POSITION) {
                if (p2 == vn.ai.trueid.lottie.LottieProperty.CORNER_RADIUS) {
                    this.cornerRadiusAnimation.setValueCallback(p3);
                }
                return;
            } else {
                this.positionAnimation.setValueCallback(p3);
                return;
            }
        } else {
            this.sizeAnimation.setValueCallback(p3);
            return;
        }
    }

    public String getName()
    {
        return this.name;
    }

    public android.graphics.Path getPath()
    {
        if (!this.isPathValid) {
            this.path.reset();
            if (!this.hidden) {
                float v5_3;
                android.graphics.Path v1_2 = ((android.graphics.PointF) this.sizeAnimation.getValue());
                android.graphics.Path v3_1 = (v1_2.x / 1073741824);
                android.graphics.Path v1_10 = (v1_2.y / 1073741824);
                float v5_1 = this.cornerRadiusAnimation;
                if (v5_1 != 0) {
                    v5_3 = ((vn.ai.trueid.lottie.animation.keyframe.FloatKeyframeAnimation) v5_1).getFloatValue();
                } else {
                    v5_3 = 0;
                }
                if (v5_3 == 0) {
                    float v7_2 = this.roundedCornersAnimation;
                    if (v7_2 != 0) {
                        v5_3 = Math.min(((Float) v7_2.getValue()).floatValue(), Math.min(v3_1, v1_10));
                    }
                }
                float v7_4 = Math.min(v3_1, v1_10);
                if (v5_3 > v7_4) {
                    v5_3 = v7_4;
                }
                float v7_7 = ((android.graphics.PointF) this.positionAnimation.getValue());
                this.path.moveTo((v7_7.x + v3_1), ((v7_7.y - v1_10) + v5_3));
                this.path.lineTo((v7_7.x + v3_1), ((v7_7.y + v1_10) - v5_3));
                float v8_5 = v5_3 cmp 0;
                if (v8_5 > 0) {
                    float v12_7 = v7_7.x;
                    float v14_1 = (v5_3 * 1073741824);
                    float v15_1 = v7_7.y;
                    this.rect.set(((v12_7 + v3_1) - v14_1), ((v15_1 + v1_10) - v14_1), (v12_7 + v3_1), (v15_1 + v1_10));
                    this.path.arcTo(this.rect, 0, 1119092736, 0);
                }
                this.path.lineTo(((v7_7.x - v3_1) + v5_3), (v7_7.y + v1_10));
                if (v8_5 > 0) {
                    float v11_16 = v7_7.x;
                    float v13_5 = v7_7.y;
                    float v15_3 = (v5_3 * 1073741824);
                    this.rect.set((v11_16 - v3_1), ((v13_5 + v1_10) - v15_3), ((v11_16 - v3_1) + v15_3), (v13_5 + v1_10));
                    this.path.arcTo(this.rect, 1119092736, 1119092736, 0);
                }
                this.path.lineTo((v7_7.x - v3_1), ((v7_7.y - v1_10) + v5_3));
                if (v8_5 > 0) {
                    float v11_2 = v7_7.x;
                    float v13_0 = v7_7.y;
                    float v15_0 = (v5_3 * 1073741824);
                    this.rect.set((v11_2 - v3_1), (v13_0 - v1_10), ((v11_2 - v3_1) + v15_0), ((v13_0 - v1_10) + v15_0));
                    this.path.arcTo(this.rect, 1127481344, 1119092736, 0);
                }
                this.path.lineTo(((v7_7.x + v3_1) - v5_3), (v7_7.y - v1_10));
                if (v8_5 > 0) {
                    float v8_0 = v7_7.x;
                    float v5_0 = (v5_3 * 1073741824);
                    int v4_1 = v7_7.y;
                    this.rect.set(((v8_0 + v3_1) - v5_0), (v4_1 - v1_10), (v8_0 + v3_1), ((v4_1 - v1_10) + v5_0));
                    this.path.arcTo(this.rect, 1132920832, 1119092736, 0);
                }
                this.path.close();
                this.trimPaths.apply(this.path);
                this.isPathValid = 1;
                return this.path;
            } else {
                this.isPathValid = 1;
                return this.path;
            }
        } else {
            return this.path;
        }
    }

    public void onValueChanged()
    {
        this.invalidate();
        return;
    }

    public void resolveKeyPath(vn.ai.trueid.lottie.model.KeyPath p1, int p2, java.util.List p3, vn.ai.trueid.lottie.model.KeyPath p4)
    {
        vn.ai.trueid.lottie.utils.MiscUtils.resolveKeyPath(p1, p2, p3, p4, this);
        return;
    }

    public void setContents(java.util.List p5, java.util.List p6)
    {
        p6 = 0;
        while (p6 < p5.size()) {
            vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation v0_5 = ((vn.ai.trueid.lottie.animation.content.Content) p5.get(p6));
            if (!(v0_5 instanceof vn.ai.trueid.lottie.animation.content.TrimPathContent)) {
                if ((v0_5 instanceof vn.ai.trueid.lottie.animation.content.RoundedCornersContent)) {
                    this.roundedCornersAnimation = ((vn.ai.trueid.lottie.animation.content.RoundedCornersContent) v0_5).getRoundedCorners();
                }
            } else {
                boolean v1_1 = ((vn.ai.trueid.lottie.animation.content.TrimPathContent) v0_5);
                if (v1_1.getType() != vn.ai.trueid.lottie.model.content.ShapeTrimPath$Type.SIMULTANEOUSLY) {
                } else {
                    this.trimPaths.addTrimPath(v1_1);
                    v1_1.addListener(this);
                }
            }
            p6++;
        }
        return;
    }
}
