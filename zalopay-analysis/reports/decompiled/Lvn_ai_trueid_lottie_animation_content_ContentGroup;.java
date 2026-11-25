package vn.ai.trueid.lottie.animation.content;
public class ContentGroup implements vn.ai.trueid.lottie.animation.content.DrawingContent, vn.ai.trueid.lottie.animation.content.PathContent, vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation$AnimationListener, vn.ai.trueid.lottie.model.KeyPathElement {
    private final java.util.List contents;
    private final boolean hidden;
    private final vn.ai.trueid.lottie.LottieDrawable lottieDrawable;
    private final android.graphics.Matrix matrix;
    private final String name;
    private final android.graphics.RectF offScreenRectF;
    private final vn.ai.trueid.lottie.utils.OffscreenLayer offscreenLayer;
    private final vn.ai.trueid.lottie.utils.OffscreenLayer$ComposeOp offscreenOp;
    private final android.graphics.Path path;
    private java.util.List pathContents;
    private final android.graphics.RectF rect;
    private vn.ai.trueid.lottie.animation.keyframe.TransformKeyframeAnimation transformAnimation;

    public ContentGroup(vn.ai.trueid.lottie.LottieDrawable p2, vn.ai.trueid.lottie.model.layer.BaseLayer p3, String p4, boolean p5, java.util.List p6, vn.ai.trueid.lottie.model.animatable.AnimatableTransform p7)
    {
        this.offscreenOp = new vn.ai.trueid.lottie.utils.OffscreenLayer$ComposeOp();
        this.offScreenRectF = new android.graphics.RectF();
        this.offscreenLayer = new vn.ai.trueid.lottie.utils.OffscreenLayer();
        this.matrix = new android.graphics.Matrix();
        this.path = new android.graphics.Path();
        this.rect = new android.graphics.RectF();
        this.name = p4;
        this.lottieDrawable = p2;
        this.hidden = p5;
        this.contents = p6;
        if (p7 != null) {
            java.util.ArrayList v2_1 = p7.createAnimation();
            this.transformAnimation = v2_1;
            v2_1.addAnimationsToLayer(p3);
            this.transformAnimation.addListener(this);
        }
        java.util.ArrayList v2_4 = new java.util.ArrayList();
        int v3_2 = (p6.size() - 1);
        while (v3_2 >= 0) {
            vn.ai.trueid.lottie.animation.content.GreedyContent v4_4 = ((vn.ai.trueid.lottie.animation.content.Content) p6.get(v3_2));
            if ((v4_4 instanceof vn.ai.trueid.lottie.animation.content.GreedyContent)) {
                v2_4.add(((vn.ai.trueid.lottie.animation.content.GreedyContent) v4_4));
            }
            v3_2--;
        }
        int v3_4 = (v2_4.size() - 1);
        while (v3_4 >= 0) {
            ((vn.ai.trueid.lottie.animation.content.GreedyContent) v2_4.get(v3_4)).absorbContent(p6.listIterator(p6.size()));
            v3_4--;
        }
        return;
    }

    public ContentGroup(vn.ai.trueid.lottie.LottieDrawable p8, vn.ai.trueid.lottie.model.layer.BaseLayer p9, vn.ai.trueid.lottie.model.content.ShapeGroup p10, vn.ai.trueid.lottie.LottieComposition p11)
    {
        this(p8, p9, p10.getName(), p10.isHidden(), vn.ai.trueid.lottie.animation.content.ContentGroup.contentsFromModels(p8, p11, p9, p10.getItems()), vn.ai.trueid.lottie.animation.content.ContentGroup.findTransform(p10.getItems()));
        return;
    }

    private static java.util.List contentsFromModels(vn.ai.trueid.lottie.LottieDrawable p3, vn.ai.trueid.lottie.LottieComposition p4, vn.ai.trueid.lottie.model.layer.BaseLayer p5, java.util.List p6)
    {
        java.util.ArrayList v0_1 = new java.util.ArrayList(p6.size());
        int v1_0 = 0;
        while (v1_0 < p6.size()) {
            vn.ai.trueid.lottie.animation.content.Content v2_2 = ((vn.ai.trueid.lottie.model.content.ContentModel) p6.get(v1_0)).toContent(p3, p4, p5);
            if (v2_2 != null) {
                v0_1.add(v2_2);
            }
            v1_0++;
        }
        return v0_1;
    }

    public static vn.ai.trueid.lottie.model.animatable.AnimatableTransform findTransform(java.util.List p3)
    {
        int v0 = 0;
        while (v0 < p3.size()) {
            vn.ai.trueid.lottie.model.animatable.AnimatableTransform v1_3 = ((vn.ai.trueid.lottie.model.content.ContentModel) p3.get(v0));
            if (!(v1_3 instanceof vn.ai.trueid.lottie.model.animatable.AnimatableTransform)) {
                v0++;
            } else {
                return ((vn.ai.trueid.lottie.model.animatable.AnimatableTransform) v1_3);
            }
        }
        return 0;
    }

    private boolean hasTwoOrMoreDrawableContent()
    {
        int v1 = 0;
        int v2 = 0;
        while (v1 < this.contents.size()) {
            if ((this.contents.get(v1) instanceof vn.ai.trueid.lottie.animation.content.DrawingContent)) {
                v2++;
                if (v2 >= 2) {
                    return 1;
                }
            }
            v1++;
        }
        return 0;
    }

    public void addValueCallback(Object p2, vn.ai.trueid.lottie.value.LottieValueCallback p3)
    {
        vn.ai.trueid.lottie.animation.keyframe.TransformKeyframeAnimation v0 = this.transformAnimation;
        if (v0 != null) {
            v0.applyValueCallback(p2, p3);
        }
        return;
    }

    public void draw(android.graphics.Canvas p6, android.graphics.Matrix p7, int p8, vn.ai.trueid.lottie.utils.DropShadow p9)
    {
        if (!this.hidden) {
            this.matrix.set(p7);
            boolean v0_17 = this.transformAnimation;
            if (v0_17) {
                boolean v0_8;
                this.matrix.preConcat(v0_17.getMatrix());
                if (this.transformAnimation.getOpacity() != null) {
                    v0_8 = ((Integer) this.transformAnimation.getOpacity().getValue()).intValue();
                } else {
                    v0_8 = 100;
                }
                p8 = ((int) ((((((float) v0_8) / 1120403456) * ((float) p8)) / 1132396544) * 1132396544));
            }
            boolean v0_21;
            int v1_1 = 255;
            if (((!this.lottieDrawable.isApplyingOpacityToLayersEnabled()) || ((!this.hasTwoOrMoreDrawableContent()) || (p8 == 255))) && ((p9 == null) || ((!this.lottieDrawable.isApplyingShadowToLayersEnabled()) || (!this.hasTwoOrMoreDrawableContent())))) {
                v0_21 = 0;
            } else {
                v0_21 = 1;
            }
            if (!v0_21) {
                v1_1 = p8;
            }
            if (!v0_21) {
                if (p9 != null) {
                    int v7_2 = new vn.ai.trueid.lottie.utils.DropShadow(p9);
                    v7_2.multiplyOpacity(v1_1);
                    p9 = v7_2;
                }
            } else {
                this.offScreenRectF.set(0, 0, 0, 0);
                this.getBounds(this.offScreenRectF, p7, 1);
                int v7_3 = this.offscreenOp;
                v7_3.alpha = p8;
                if (p9 == null) {
                    v7_3.shadow = 0;
                } else {
                    p9.applyTo(v7_3);
                    p9 = 0;
                }
                p6 = this.offscreenLayer.start(p6, this.offScreenRectF, this.offscreenOp);
            }
            int v7_7 = (this.contents.size() - 1);
            while (v7_7 >= 0) {
                vn.ai.trueid.lottie.animation.content.DrawingContent v8_6 = this.contents.get(v7_7);
                if ((v8_6 instanceof vn.ai.trueid.lottie.animation.content.DrawingContent)) {
                    ((vn.ai.trueid.lottie.animation.content.DrawingContent) v8_6).draw(p6, this.matrix, v1_1, p9);
                }
                v7_7--;
            }
            if (v0_21) {
                this.offscreenLayer.finish();
            }
        }
        return;
    }

    public void getBounds(android.graphics.RectF p4, android.graphics.Matrix p5, boolean p6)
    {
        this.matrix.set(p5);
        int v5_4 = this.transformAnimation;
        if (v5_4 != 0) {
            this.matrix.preConcat(v5_4.getMatrix());
        }
        this.rect.set(0, 0, 0, 0);
        int v5_3 = (this.contents.size() - 1);
        while (v5_3 >= 0) {
            android.graphics.RectF v0_3 = ((vn.ai.trueid.lottie.animation.content.Content) this.contents.get(v5_3));
            if ((v0_3 instanceof vn.ai.trueid.lottie.animation.content.DrawingContent)) {
                ((vn.ai.trueid.lottie.animation.content.DrawingContent) v0_3).getBounds(this.rect, this.matrix, p6);
                p4.union(this.rect);
            }
            v5_3--;
        }
        return;
    }

    public java.util.List getContents()
    {
        return this.contents;
    }

    public String getName()
    {
        return this.name;
    }

    public android.graphics.Path getPath()
    {
        this.matrix.reset();
        int v0_6 = this.transformAnimation;
        if (v0_6 != 0) {
            this.matrix.set(v0_6.getMatrix());
        }
        this.path.reset();
        if (!this.hidden) {
            int v0_4 = (this.contents.size() - 1);
            while (v0_4 >= 0) {
                android.graphics.Path v1_2 = ((vn.ai.trueid.lottie.animation.content.Content) this.contents.get(v0_4));
                if ((v1_2 instanceof vn.ai.trueid.lottie.animation.content.PathContent)) {
                    this.path.addPath(((vn.ai.trueid.lottie.animation.content.PathContent) v1_2).getPath(), this.matrix);
                }
                v0_4--;
            }
            return this.path;
        } else {
            return this.path;
        }
    }

    public java.util.List getPathList()
    {
        if (this.pathContents == null) {
            this.pathContents = new java.util.ArrayList();
            int v0_3 = 0;
            while (v0_3 < this.contents.size()) {
                vn.ai.trueid.lottie.animation.content.PathContent v1_2 = ((vn.ai.trueid.lottie.animation.content.Content) this.contents.get(v0_3));
                if ((v1_2 instanceof vn.ai.trueid.lottie.animation.content.PathContent)) {
                    this.pathContents.add(((vn.ai.trueid.lottie.animation.content.PathContent) v1_2));
                }
                v0_3++;
            }
        }
        return this.pathContents;
    }

    public android.graphics.Matrix getTransformationMatrix()
    {
        android.graphics.Matrix v0_0 = this.transformAnimation;
        if (v0_0 == null) {
            this.matrix.reset();
            return this.matrix;
        } else {
            return v0_0.getMatrix();
        }
    }

    public void onValueChanged()
    {
        this.lottieDrawable.invalidateSelf();
        return;
    }

    public void resolveKeyPath(vn.ai.trueid.lottie.model.KeyPath p4, int p5, java.util.List p6, vn.ai.trueid.lottie.model.KeyPath p7)
    {
        if ((p4.matches(this.getName(), p5)) || ("__container".equals(this.getName()))) {
            if (!"__container".equals(this.getName())) {
                p7 = p7.addKey(this.getName());
                if (p4.fullyResolvesTo(this.getName(), p5)) {
                    p6.add(p7.resolve(this));
                }
            }
            if (p4.propagateToChildren(this.getName(), p5)) {
                int v0_11 = p4.incrementDepthBy(this.getName(), p5);
                int v5_1 = 0;
                while (v5_1 < this.contents.size()) {
                    vn.ai.trueid.lottie.model.KeyPathElement v1_5 = ((vn.ai.trueid.lottie.animation.content.Content) this.contents.get(v5_1));
                    if ((v1_5 instanceof vn.ai.trueid.lottie.model.KeyPathElement)) {
                        ((vn.ai.trueid.lottie.model.KeyPathElement) v1_5).resolveKeyPath(p4, (v0_11 + p5), p6, p7);
                    }
                    v5_1++;
                }
            }
        }
        return;
    }

    public void setContents(java.util.List p4, java.util.List p5)
    {
        java.util.ArrayList v5_1 = new java.util.ArrayList((this.contents.size() + p4.size()));
        v5_1.addAll(p4);
        int v4_2 = (this.contents.size() - 1);
        while (v4_2 >= 0) {
            vn.ai.trueid.lottie.animation.content.Content v0_2 = ((vn.ai.trueid.lottie.animation.content.Content) this.contents.get(v4_2));
            v0_2.setContents(v5_1, this.contents.subList(0, v4_2));
            v5_1.add(v0_2);
            v4_2--;
        }
        return;
    }
}
