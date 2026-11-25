package vn.ai.trueid.lottie.model.content;
public class PolystarShape implements vn.ai.trueid.lottie.model.content.ContentModel {
    private final boolean hidden;
    private final vn.ai.trueid.lottie.model.animatable.AnimatableFloatValue innerRadius;
    private final vn.ai.trueid.lottie.model.animatable.AnimatableFloatValue innerRoundedness;
    private final boolean isReversed;
    private final String name;
    private final vn.ai.trueid.lottie.model.animatable.AnimatableFloatValue outerRadius;
    private final vn.ai.trueid.lottie.model.animatable.AnimatableFloatValue outerRoundedness;
    private final vn.ai.trueid.lottie.model.animatable.AnimatableFloatValue points;
    private final vn.ai.trueid.lottie.model.animatable.AnimatableValue position;
    private final vn.ai.trueid.lottie.model.animatable.AnimatableFloatValue rotation;
    private final vn.ai.trueid.lottie.model.content.PolystarShape$Type type;

    public PolystarShape(String p1, vn.ai.trueid.lottie.model.content.PolystarShape$Type p2, vn.ai.trueid.lottie.model.animatable.AnimatableFloatValue p3, vn.ai.trueid.lottie.model.animatable.AnimatableValue p4, vn.ai.trueid.lottie.model.animatable.AnimatableFloatValue p5, vn.ai.trueid.lottie.model.animatable.AnimatableFloatValue p6, vn.ai.trueid.lottie.model.animatable.AnimatableFloatValue p7, vn.ai.trueid.lottie.model.animatable.AnimatableFloatValue p8, vn.ai.trueid.lottie.model.animatable.AnimatableFloatValue p9, boolean p10, boolean p11)
    {
        this.name = p1;
        this.type = p2;
        this.points = p3;
        this.position = p4;
        this.rotation = p5;
        this.innerRadius = p6;
        this.outerRadius = p7;
        this.innerRoundedness = p8;
        this.outerRoundedness = p9;
        this.hidden = p10;
        this.isReversed = p11;
        return;
    }

    public vn.ai.trueid.lottie.model.animatable.AnimatableFloatValue getInnerRadius()
    {
        return this.innerRadius;
    }

    public vn.ai.trueid.lottie.model.animatable.AnimatableFloatValue getInnerRoundedness()
    {
        return this.innerRoundedness;
    }

    public String getName()
    {
        return this.name;
    }

    public vn.ai.trueid.lottie.model.animatable.AnimatableFloatValue getOuterRadius()
    {
        return this.outerRadius;
    }

    public vn.ai.trueid.lottie.model.animatable.AnimatableFloatValue getOuterRoundedness()
    {
        return this.outerRoundedness;
    }

    public vn.ai.trueid.lottie.model.animatable.AnimatableFloatValue getPoints()
    {
        return this.points;
    }

    public vn.ai.trueid.lottie.model.animatable.AnimatableValue getPosition()
    {
        return this.position;
    }

    public vn.ai.trueid.lottie.model.animatable.AnimatableFloatValue getRotation()
    {
        return this.rotation;
    }

    public vn.ai.trueid.lottie.model.content.PolystarShape$Type getType()
    {
        return this.type;
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
        return new vn.ai.trueid.lottie.animation.content.PolystarContent(p1, p3, this);
    }
}
