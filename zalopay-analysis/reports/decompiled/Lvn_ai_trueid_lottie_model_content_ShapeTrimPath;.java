package vn.ai.trueid.lottie.model.content;
public class ShapeTrimPath implements vn.ai.trueid.lottie.model.content.ContentModel {
    private final vn.ai.trueid.lottie.model.animatable.AnimatableFloatValue end;
    private final boolean hidden;
    private final String name;
    private final vn.ai.trueid.lottie.model.animatable.AnimatableFloatValue offset;
    private final vn.ai.trueid.lottie.model.animatable.AnimatableFloatValue start;
    private final vn.ai.trueid.lottie.model.content.ShapeTrimPath$Type type;

    public ShapeTrimPath(String p1, vn.ai.trueid.lottie.model.content.ShapeTrimPath$Type p2, vn.ai.trueid.lottie.model.animatable.AnimatableFloatValue p3, vn.ai.trueid.lottie.model.animatable.AnimatableFloatValue p4, vn.ai.trueid.lottie.model.animatable.AnimatableFloatValue p5, boolean p6)
    {
        this.name = p1;
        this.type = p2;
        this.start = p3;
        this.end = p4;
        this.offset = p5;
        this.hidden = p6;
        return;
    }

    public vn.ai.trueid.lottie.model.animatable.AnimatableFloatValue getEnd()
    {
        return this.end;
    }

    public String getName()
    {
        return this.name;
    }

    public vn.ai.trueid.lottie.model.animatable.AnimatableFloatValue getOffset()
    {
        return this.offset;
    }

    public vn.ai.trueid.lottie.model.animatable.AnimatableFloatValue getStart()
    {
        return this.start;
    }

    public vn.ai.trueid.lottie.model.content.ShapeTrimPath$Type getType()
    {
        return this.type;
    }

    public boolean isHidden()
    {
        return this.hidden;
    }

    public vn.ai.trueid.lottie.animation.content.Content toContent(vn.ai.trueid.lottie.LottieDrawable p1, vn.ai.trueid.lottie.LottieComposition p2, vn.ai.trueid.lottie.model.layer.BaseLayer p3)
    {
        return new vn.ai.trueid.lottie.animation.content.TrimPathContent(p3, this);
    }

    public String toString()
    {
        String v0_2 = new StringBuilder("Trim Path: {start: ");
        v0_2.append(this.start);
        v0_2.append(", end: ");
        v0_2.append(this.end);
        v0_2.append(", offset: ");
        v0_2.append(this.offset);
        v0_2.append("}");
        return v0_2.toString();
    }
}
