package vn.ai.trueid.lottie.animation.content;
public class EllipseContent implements vn.ai.trueid.lottie.animation.content.PathContent, vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation$AnimationListener, vn.ai.trueid.lottie.animation.content.KeyPathElementContent {
    private static final float ELLIPSE_CONTROL_POINT_PERCENTAGE = 1057841721;
    private final vn.ai.trueid.lottie.model.content.CircleShape circleShape;
    private boolean isPathValid;
    private final vn.ai.trueid.lottie.LottieDrawable lottieDrawable;
    private final String name;
    private final android.graphics.Path path;
    private final vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation positionAnimation;
    private final vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation sizeAnimation;
    private final vn.ai.trueid.lottie.animation.content.CompoundTrimPathContent trimPaths;

    public EllipseContent(vn.ai.trueid.lottie.LottieDrawable p2, vn.ai.trueid.lottie.model.layer.BaseLayer p3, vn.ai.trueid.lottie.model.content.CircleShape p4)
    {
        this.path = new android.graphics.Path();
        this.trimPaths = new vn.ai.trueid.lottie.animation.content.CompoundTrimPathContent();
        this.name = p4.getName();
        this.lottieDrawable = p2;
        vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation v2_2 = p4.getSize().createAnimation();
        this.sizeAnimation = v2_2;
        vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation v0_2 = p4.getPosition().createAnimation();
        this.positionAnimation = v0_2;
        this.circleShape = p4;
        p3.addAnimation(v2_2);
        p3.addAnimation(v0_2);
        v2_2.addUpdateListener(this);
        v0_2.addUpdateListener(this);
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
        if (p2 != vn.ai.trueid.lottie.LottieProperty.ELLIPSE_SIZE) {
            if (p2 == vn.ai.trueid.lottie.LottieProperty.POSITION) {
                this.positionAnimation.setValueCallback(p3);
            }
            return;
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
            if (!this.circleShape.isHidden()) {
                android.graphics.Path v1_12 = ((android.graphics.PointF) this.sizeAnimation.getValue());
                float v6_1 = (v1_12.x / 1073741824);
                float v9_1 = (v1_12.y / 1073741824);
                android.graphics.Path v3_3 = (v6_1 * 1057841721);
                android.graphics.Path v1_15 = (1057841721 * v9_1);
                this.path.reset();
                if (!this.circleShape.isReversed()) {
                    float v4_5 = v9_1;
                    android.graphics.Path v7_1 = (- v4_5);
                    this.path.moveTo(0, v7_1);
                    float v9_2 = (0 - v1_15);
                    float v8_2 = v6_1;
                    float v6_2 = (v3_3 + 0);
                    this.path.cubicTo(v6_2, v7_1, v8_2, v9_2, v6_1, 0);
                    float v6_3 = v8_2;
                    float v15_1 = v7_1;
                    float v16_0 = v9_2;
                    android.graphics.Path v7_2 = (v1_15 + 0);
                    float v9_3 = v4_5;
                    this.path.cubicTo(v6_3, v7_2, v6_2, v9_3, 0, v4_5);
                    float v8_4 = (0 - v3_3);
                    float v10_3 = (- v6_3);
                    this.path.cubicTo(v8_4, v9_3, v10_3, v7_2, v10_3, 0);
                    this.path.cubicTo(v10_3, v16_0, v8_4, v15_1, 0, v15_1);
                } else {
                    android.graphics.Path v5_4 = (- v9_1);
                    this.path.moveTo(0, v5_4);
                    float v16_1 = (0 - v3_3);
                    float v8_6 = (- v6_1);
                    float v19 = (0 - v1_15);
                    float v17 = v5_4;
                    this.path.cubicTo(v16_1, v17, v8_6, v19, v8_6, 0);
                    android.graphics.Path v1_20 = (v1_15 + 0);
                    float v11_5 = v9_1;
                    this.path.cubicTo(v8_6, v1_20, v16_1, v11_5, 0, v9_1);
                    float v8_8 = v6_1;
                    float v6_4 = (v3_3 + 0);
                    this.path.cubicTo(v6_4, v11_5, v8_8, v1_20, v6_1, 0);
                    this.path.cubicTo(v8_8, v19, v6_4, v17, 0, v17);
                }
                android.graphics.Path v1_4 = ((android.graphics.PointF) this.positionAnimation.getValue());
                this.path.offset(v1_4.x, v1_4.y);
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

    public void setContents(java.util.List p4, java.util.List p5)
    {
        p5 = 0;
        while (p5 < p4.size()) {
            vn.ai.trueid.lottie.animation.content.TrimPathContent v0_3 = ((vn.ai.trueid.lottie.animation.content.Content) p4.get(p5));
            if ((v0_3 instanceof vn.ai.trueid.lottie.animation.content.TrimPathContent)) {
                vn.ai.trueid.lottie.animation.content.TrimPathContent v0_0 = ((vn.ai.trueid.lottie.animation.content.TrimPathContent) v0_3);
                if (v0_0.getType() == vn.ai.trueid.lottie.model.content.ShapeTrimPath$Type.SIMULTANEOUSLY) {
                    this.trimPaths.addTrimPath(v0_0);
                    v0_0.addListener(this);
                }
            }
            p5++;
        }
        return;
    }
}
