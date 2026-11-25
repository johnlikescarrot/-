package vn.ai.trueid.lottie.model.content;
public class RectangleShape implements vn.ai.trueid.lottie.model.content.ContentModel {
    private final vn.ai.trueid.lottie.model.animatable.AnimatableFloatValue cornerRadius;
    private final boolean hidden;
    private final String name;
    private final vn.ai.trueid.lottie.model.animatable.AnimatableValue position;
    private final vn.ai.trueid.lottie.model.animatable.AnimatableValue size;

    public RectangleShape(String p1, vn.ai.trueid.lottie.model.animatable.AnimatableValue p2, vn.ai.trueid.lottie.model.animatable.AnimatableValue p3, vn.ai.trueid.lottie.model.animatable.AnimatableFloatValue p4, boolean p5)
    {
        this.name = p1;
        this.position = p2;
        this.size = p3;
        this.cornerRadius = p4;
        this.hidden = p5;
        return;
    }

    public vn.ai.trueid.lottie.model.animatable.AnimatableFloatValue getCornerRadius()
    {
        return this.cornerRadius;
    }

    public String getName()
    {
        return this.name;
    }

    public vn.ai.trueid.lottie.model.animatable.AnimatableValue getPosition()
    {
        return this.position;
    }

    public vn.ai.trueid.lottie.model.animatable.AnimatableValue getSize()
    {
        return this.size;
    }

    public boolean isHidden()
    {
        return this.hidden;
    }

    public vn.ai.trueid.lottie.animation.content.Content toContent(vn.ai.trueid.lottie.LottieDrawable p1, vn.ai.trueid.lottie.LottieComposition p2, vn.ai.trueid.lottie.model.layer.BaseLayer p3)
    {
        return new vn.ai.trueid.lottie.animation.content.RectangleContent(p1, p3, this);
    }

    public String toString()
    {
        String v0_2 = new StringBuilder("RectangleShape{position=");
        v0_2.append(this.position);
        v0_2.append(", size=");
        v0_2.append(this.size);
        v0_2.append(125);
        return v0_2.toString();
    }
}
