package vn.ai.trueid.lottie.model.content;
public class ShapeStroke implements vn.ai.trueid.lottie.model.content.ContentModel {
    private final vn.ai.trueid.lottie.model.content.ShapeStroke$LineCapType capType;
    private final vn.ai.trueid.lottie.model.animatable.AnimatableColorValue color;
    private final boolean hidden;
    private final vn.ai.trueid.lottie.model.content.ShapeStroke$LineJoinType joinType;
    private final java.util.List lineDashPattern;
    private final float miterLimit;
    private final String name;
    private final vn.ai.trueid.lottie.model.animatable.AnimatableFloatValue offset;
    private final vn.ai.trueid.lottie.model.animatable.AnimatableIntegerValue opacity;
    private final vn.ai.trueid.lottie.model.animatable.AnimatableFloatValue width;

    public ShapeStroke(String p1, vn.ai.trueid.lottie.model.animatable.AnimatableFloatValue p2, java.util.List p3, vn.ai.trueid.lottie.model.animatable.AnimatableColorValue p4, vn.ai.trueid.lottie.model.animatable.AnimatableIntegerValue p5, vn.ai.trueid.lottie.model.animatable.AnimatableFloatValue p6, vn.ai.trueid.lottie.model.content.ShapeStroke$LineCapType p7, vn.ai.trueid.lottie.model.content.ShapeStroke$LineJoinType p8, float p9, boolean p10)
    {
        this.name = p1;
        this.offset = p2;
        this.lineDashPattern = p3;
        this.color = p4;
        this.opacity = p5;
        this.width = p6;
        this.capType = p7;
        this.joinType = p8;
        this.miterLimit = p9;
        this.hidden = p10;
        return;
    }

    public vn.ai.trueid.lottie.model.content.ShapeStroke$LineCapType getCapType()
    {
        return this.capType;
    }

    public vn.ai.trueid.lottie.model.animatable.AnimatableColorValue getColor()
    {
        return this.color;
    }

    public vn.ai.trueid.lottie.model.animatable.AnimatableFloatValue getDashOffset()
    {
        return this.offset;
    }

    public vn.ai.trueid.lottie.model.content.ShapeStroke$LineJoinType getJoinType()
    {
        return this.joinType;
    }

    public java.util.List getLineDashPattern()
    {
        return this.lineDashPattern;
    }

    public float getMiterLimit()
    {
        return this.miterLimit;
    }

    public String getName()
    {
        return this.name;
    }

    public vn.ai.trueid.lottie.model.animatable.AnimatableIntegerValue getOpacity()
    {
        return this.opacity;
    }

    public vn.ai.trueid.lottie.model.animatable.AnimatableFloatValue getWidth()
    {
        return this.width;
    }

    public boolean isHidden()
    {
        return this.hidden;
    }

    public vn.ai.trueid.lottie.animation.content.Content toContent(vn.ai.trueid.lottie.LottieDrawable p1, vn.ai.trueid.lottie.LottieComposition p2, vn.ai.trueid.lottie.model.layer.BaseLayer p3)
    {
        return new vn.ai.trueid.lottie.animation.content.StrokeContent(p1, p3, this);
    }
}
