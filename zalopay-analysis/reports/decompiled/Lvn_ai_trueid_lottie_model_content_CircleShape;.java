package vn.ai.trueid.lottie.model.content;
public class CircleShape implements vn.ai.trueid.lottie.model.content.ContentModel {
    private final boolean hidden;
    private final boolean isReversed;
    private final String name;
    private final vn.ai.trueid.lottie.model.animatable.AnimatableValue position;
    private final vn.ai.trueid.lottie.model.animatable.AnimatablePointValue size;

    public CircleShape(String p1, vn.ai.trueid.lottie.model.animatable.AnimatableValue p2, vn.ai.trueid.lottie.model.animatable.AnimatablePointValue p3, boolean p4, boolean p5)
    {
        this.name = p1;
        this.position = p2;
        this.size = p3;
        this.isReversed = p4;
        this.hidden = p5;
        return;
    }

    public String getName()
    {
        return this.name;
    }

    public vn.ai.trueid.lottie.model.animatable.AnimatableValue getPosition()
    {
        return this.position;
    }

    public vn.ai.trueid.lottie.model.animatable.AnimatablePointValue getSize()
    {
        return this.size;
    }

    public boolean isHidden()
    {
        return this.hidden;
    }

    public boolean isReversed()
    {
        return this.isReversed;
    }

    public vn.ai.trueid.lottie.animation.content.Content toContent(vn.ai.trueid.lottie.LottieDrawable p1, vn.ai.trueid.lottie.LottieComposition p2, vn.ai.trueid.lottie.model.layer.BaseLayer p3)
    {
        return new vn.ai.trueid.lottie.animation.content.EllipseContent(p1, p3, this);
    }
}
