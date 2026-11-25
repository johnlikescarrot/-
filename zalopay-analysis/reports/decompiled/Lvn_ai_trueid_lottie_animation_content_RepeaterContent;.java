package vn.ai.trueid.lottie.animation.content;
public class RepeaterContent implements vn.ai.trueid.lottie.animation.content.DrawingContent, vn.ai.trueid.lottie.animation.content.PathContent, vn.ai.trueid.lottie.animation.content.GreedyContent, vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation$AnimationListener, vn.ai.trueid.lottie.animation.content.KeyPathElementContent {
    private vn.ai.trueid.lottie.animation.content.ContentGroup contentGroup;
    private final vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation copies;
    private final boolean hidden;
    private final vn.ai.trueid.lottie.model.layer.BaseLayer layer;
    private final vn.ai.trueid.lottie.LottieDrawable lottieDrawable;
    private final android.graphics.Matrix matrix;
    private final String name;
    private final vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation offset;
    private final android.graphics.Path path;
    private final vn.ai.trueid.lottie.animation.keyframe.TransformKeyframeAnimation transform;

    public RepeaterContent(vn.ai.trueid.lottie.LottieDrawable p2, vn.ai.trueid.lottie.model.layer.BaseLayer p3, vn.ai.trueid.lottie.model.content.Repeater p4)
    {
        this.matrix = new android.graphics.Matrix();
        this.path = new android.graphics.Path();
        this.lottieDrawable = p2;
        this.layer = p3;
        this.name = p4.getName();
        this.hidden = p4.isHidden();
        vn.ai.trueid.lottie.animation.keyframe.TransformKeyframeAnimation v2_4 = p4.getCopies().createAnimation();
        this.copies = v2_4;
        p3.addAnimation(v2_4);
        v2_4.addUpdateListener(this);
        vn.ai.trueid.lottie.animation.keyframe.TransformKeyframeAnimation v2_6 = p4.getOffset().createAnimation();
        this.offset = v2_6;
        p3.addAnimation(v2_6);
        v2_6.addUpdateListener(this);
        vn.ai.trueid.lottie.animation.keyframe.TransformKeyframeAnimation v2_8 = p4.getTransform().createAnimation();
        this.transform = v2_8;
        v2_8.addAnimationsToLayer(p3);
        v2_8.addListener(this);
        return;
    }

    public void absorbContent(java.util.ListIterator p9)
    {
        if (this.contentGroup != null) {
            return;
        }
        while (p9.hasPrevious()) {
            if (p9.previous() == this) {
                break;
            }
        }
        java.util.ArrayList v6_1 = new java.util.ArrayList();
        while (p9.hasPrevious()) {
            v6_1.add(((vn.ai.trueid.lottie.animation.content.Content) p9.previous()));
            p9.remove();
        }
        java.util.Collections.reverse(v6_1);
        this.contentGroup = new vn.ai.trueid.lottie.animation.content.ContentGroup(this.lottieDrawable, this.layer, wmoi.igob.ggcn(6074), this.hidden, v6_1, 0);
        return;
    }

    public void addValueCallback(Object p2, vn.ai.trueid.lottie.value.LottieValueCallback p3)
    {
        if (!this.transform.applyValueCallback(p2, p3)) {
            if (p2 != vn.ai.trueid.lottie.LottieProperty.REPEATER_COPIES) {
                if (p2 == vn.ai.trueid.lottie.LottieProperty.REPEATER_OFFSET) {
                    this.offset.setValueCallback(p3);
                }
            } else {
                this.copies.setValueCallback(p3);
                return;
            }
        }
        return;
    }

    public void draw(android.graphics.Canvas p10, android.graphics.Matrix p11, int p12, vn.ai.trueid.lottie.utils.DropShadow p13)
    {
        float v0_3 = ((Float) this.copies.getValue()).floatValue();
        float v1_0 = ((Float) this.offset.getValue()).floatValue();
        float v2_5 = (((Float) this.transform.getStartOpacity().getValue()).floatValue() / 1120403456);
        float v4_5 = (((Float) this.transform.getEndOpacity().getValue()).floatValue() / 1120403456);
        int v3_2 = (((int) v0_3) - 1);
        while (v3_2 >= 0) {
            this.matrix.set(p11);
            android.graphics.Matrix v7_0 = ((float) v3_2);
            this.matrix.preConcat(this.transform.getMatrixForRepeater((v7_0 + v1_0)));
            this.contentGroup.draw(p10, this.matrix, ((int) (vn.ai.trueid.lottie.utils.MiscUtils.lerp(v2_5, v4_5, (v7_0 / v0_3)) * ((float) p12))), p13);
            v3_2--;
        }
        return;
    }

    public void getBounds(android.graphics.RectF p2, android.graphics.Matrix p3, boolean p4)
    {
        this.contentGroup.getBounds(p2, p3, p4);
        return;
    }

    public String getName()
    {
        return this.name;
    }

    public android.graphics.Path getPath()
    {
        android.graphics.Path v0_1 = this.contentGroup.getPath();
        this.path.reset();
        int v1_0 = ((Float) this.copies.getValue()).floatValue();
        float v2_3 = ((Float) this.offset.getValue()).floatValue();
        int v1_2 = (((int) v1_0) - 1);
        while (v1_2 >= 0) {
            this.matrix.set(this.transform.getMatrixForRepeater((((float) v1_2) + v2_3)));
            this.path.addPath(v0_1, this.matrix);
            v1_2--;
        }
        return this.path;
    }

    public void onValueChanged()
    {
        this.lottieDrawable.invalidateSelf();
        return;
    }

    public void resolveKeyPath(vn.ai.trueid.lottie.model.KeyPath p4, int p5, java.util.List p6, vn.ai.trueid.lottie.model.KeyPath p7)
    {
        vn.ai.trueid.lottie.utils.MiscUtils.resolveKeyPath(p4, p5, p6, p7, this);
        int v0 = 0;
        while (v0 < this.contentGroup.getContents().size()) {
            vn.ai.trueid.lottie.animation.content.KeyPathElementContent v1_2 = ((vn.ai.trueid.lottie.animation.content.Content) this.contentGroup.getContents().get(v0));
            if ((v1_2 instanceof vn.ai.trueid.lottie.animation.content.KeyPathElementContent)) {
                vn.ai.trueid.lottie.utils.MiscUtils.resolveKeyPath(p4, p5, p6, p7, ((vn.ai.trueid.lottie.animation.content.KeyPathElementContent) v1_2));
            }
            v0++;
        }
        return;
    }

    public void setContents(java.util.List p2, java.util.List p3)
    {
        this.contentGroup.setContents(p2, p3);
        return;
    }
}
