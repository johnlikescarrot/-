package vn.ai.trueid.lottie.animation.content;
public class ShapeContent implements vn.ai.trueid.lottie.animation.content.PathContent, vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation$AnimationListener, vn.ai.trueid.lottie.animation.content.KeyPathElementContent {
    private final boolean hidden;
    private boolean isPathValid;
    private final vn.ai.trueid.lottie.LottieDrawable lottieDrawable;
    private final String name;
    private final android.graphics.Path path;
    private final vn.ai.trueid.lottie.animation.keyframe.ShapeKeyframeAnimation shapeAnimation;
    private java.util.List shapeModifierContents;
    private final vn.ai.trueid.lottie.animation.content.CompoundTrimPathContent trimPaths;

    public ShapeContent(vn.ai.trueid.lottie.LottieDrawable p2, vn.ai.trueid.lottie.model.layer.BaseLayer p3, vn.ai.trueid.lottie.model.content.ShapePath p4)
    {
        this.path = new android.graphics.Path();
        this.trimPaths = new vn.ai.trueid.lottie.animation.content.CompoundTrimPathContent();
        this.name = p4.getName();
        this.hidden = p4.isHidden();
        this.lottieDrawable = p2;
        vn.ai.trueid.lottie.animation.keyframe.ShapeKeyframeAnimation v2_2 = p4.getShapePath().createAnimation();
        this.shapeAnimation = v2_2;
        p3.addAnimation(v2_2);
        v2_2.addUpdateListener(this);
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
        if (p2 == vn.ai.trueid.lottie.LottieProperty.PATH) {
            this.shapeAnimation.setValueCallback(p3);
        }
        return;
    }

    public String getName()
    {
        return this.name;
    }

    public android.graphics.Path getPath()
    {
        if ((!this.isPathValid) || (this.shapeAnimation.hasValueCallback())) {
            this.path.reset();
            if (!this.hidden) {
                android.graphics.Path v0_3 = ((android.graphics.Path) this.shapeAnimation.getValue());
                if (v0_3 != null) {
                    this.path.set(v0_3);
                    this.path.setFillType(android.graphics.Path$FillType.EVEN_ODD);
                    this.trimPaths.apply(this.path);
                    this.isPathValid = 1;
                    return this.path;
                } else {
                    return this.path;
                }
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

    public void setContents(java.util.List p6, java.util.List p7)
    {
        java.util.ArrayList v7_0 = 0;
        int v0 = 0;
        while (v0 < p6.size()) {
            vn.ai.trueid.lottie.animation.content.ShapeModifierContent v1_0 = ((vn.ai.trueid.lottie.animation.content.Content) p6.get(v0));
            if (!(v1_0 instanceof vn.ai.trueid.lottie.animation.content.TrimPathContent)) {
                if ((v1_0 instanceof vn.ai.trueid.lottie.animation.content.ShapeModifierContent)) {
                    if (v7_0 == null) {
                        v7_0 = new java.util.ArrayList();
                    }
                    vn.ai.trueid.lottie.animation.content.ShapeModifierContent v1_1 = ((vn.ai.trueid.lottie.animation.content.ShapeModifierContent) v1_0);
                    v1_1.addUpdateListener(this);
                    v7_0.add(v1_1);
                }
            } else {
                boolean v2_2 = ((vn.ai.trueid.lottie.animation.content.TrimPathContent) v1_0);
                if (v2_2.getType() != vn.ai.trueid.lottie.model.content.ShapeTrimPath$Type.SIMULTANEOUSLY) {
                } else {
                    this.trimPaths.addTrimPath(v2_2);
                    v2_2.addListener(this);
                }
            }
            v0++;
        }
        this.shapeAnimation.setShapeModifiers(v7_0);
        return;
    }
}
