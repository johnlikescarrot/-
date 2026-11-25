package vn.ai.trueid.lottie.model.content;
public class ShapePath implements vn.ai.trueid.lottie.model.content.ContentModel {
    private final boolean hidden;
    private final int index;
    private final String name;
    private final vn.ai.trueid.lottie.model.animatable.AnimatableShapeValue shapePath;

    public ShapePath(String p1, int p2, vn.ai.trueid.lottie.model.animatable.AnimatableShapeValue p3, boolean p4)
    {
        this.name = p1;
        this.index = p2;
        this.shapePath = p3;
        this.hidden = p4;
        return;
    }

    public String getName()
    {
        return this.name;
    }

    public vn.ai.trueid.lottie.model.animatable.AnimatableShapeValue getShapePath()
    {
        return this.shapePath;
    }

    public boolean isHidden()
    {
        return this.hidden;
    }

    public vn.ai.trueid.lottie.animation.content.Content toContent(vn.ai.trueid.lottie.LottieDrawable p1, vn.ai.trueid.lottie.LottieComposition p2, vn.ai.trueid.lottie.model.layer.BaseLayer p3)
    {
        return new vn.ai.trueid.lottie.animation.content.ShapeContent(p1, p3, this);
    }

    public String toString()
    {
        String v0_2 = new StringBuilder("ShapePath{name=");
        v0_2.append(this.name);
        v0_2.append(", index=");
        return SP.n.l(v0_2, this.index, 125);
    }
}
