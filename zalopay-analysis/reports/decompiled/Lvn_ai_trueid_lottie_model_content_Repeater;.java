package vn.ai.trueid.lottie.model.content;
public class Repeater implements vn.ai.trueid.lottie.model.content.ContentModel {
    private final vn.ai.trueid.lottie.model.animatable.AnimatableFloatValue copies;
    private final boolean hidden;
    private final String name;
    private final vn.ai.trueid.lottie.model.animatable.AnimatableFloatValue offset;
    private final vn.ai.trueid.lottie.model.animatable.AnimatableTransform transform;

    public Repeater(String p1, vn.ai.trueid.lottie.model.animatable.AnimatableFloatValue p2, vn.ai.trueid.lottie.model.animatable.AnimatableFloatValue p3, vn.ai.trueid.lottie.model.animatable.AnimatableTransform p4, boolean p5)
    {
        this.name = p1;
        this.copies = p2;
        this.offset = p3;
        this.transform = p4;
        this.hidden = p5;
        return;
    }

    public vn.ai.trueid.lottie.model.animatable.AnimatableFloatValue getCopies()
    {
        return this.copies;
    }

    public String getName()
    {
        return this.name;
    }

    public vn.ai.trueid.lottie.model.animatable.AnimatableFloatValue getOffset()
    {
        return this.offset;
    }

    public vn.ai.trueid.lottie.model.animatable.AnimatableTransform getTransform()
    {
        return this.transform;
    }

    public boolean isHidden()
    {
        return this.hidden;
    }

    public vn.ai.trueid.lottie.animation.content.Content toContent(vn.ai.trueid.lottie.LottieDrawable p1, vn.ai.trueid.lottie.LottieComposition p2, vn.ai.trueid.lottie.model.layer.BaseLayer p3)
    {
        return new vn.ai.trueid.lottie.animation.content.RepeaterContent(p1, p3, this);
    }
}
