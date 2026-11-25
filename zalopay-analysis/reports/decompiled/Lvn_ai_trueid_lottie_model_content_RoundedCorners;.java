package vn.ai.trueid.lottie.model.content;
public class RoundedCorners implements vn.ai.trueid.lottie.model.content.ContentModel {
    private final vn.ai.trueid.lottie.model.animatable.AnimatableValue cornerRadius;
    private final String name;

    public RoundedCorners(String p1, vn.ai.trueid.lottie.model.animatable.AnimatableValue p2)
    {
        this.name = p1;
        this.cornerRadius = p2;
        return;
    }

    public vn.ai.trueid.lottie.model.animatable.AnimatableValue getCornerRadius()
    {
        return this.cornerRadius;
    }

    public String getName()
    {
        return this.name;
    }

    public vn.ai.trueid.lottie.animation.content.Content toContent(vn.ai.trueid.lottie.LottieDrawable p1, vn.ai.trueid.lottie.LottieComposition p2, vn.ai.trueid.lottie.model.layer.BaseLayer p3)
    {
        return new vn.ai.trueid.lottie.animation.content.RoundedCornersContent(p1, p3, this);
    }
}
