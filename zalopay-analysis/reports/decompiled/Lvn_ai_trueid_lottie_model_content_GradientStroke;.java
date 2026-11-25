package vn.ai.trueid.lottie.model.content;
public class GradientStroke implements vn.ai.trueid.lottie.model.content.ContentModel {
    private final vn.ai.trueid.lottie.model.content.ShapeStroke$LineCapType capType;
    private final vn.ai.trueid.lottie.model.animatable.AnimatableFloatValue dashOffset;
    private final vn.ai.trueid.lottie.model.animatable.AnimatablePointValue endPoint;
    private final vn.ai.trueid.lottie.model.animatable.AnimatableGradientColorValue gradientColor;
    private final vn.ai.trueid.lottie.model.content.GradientType gradientType;
    private final boolean hidden;
    private final vn.ai.trueid.lottie.model.content.ShapeStroke$LineJoinType joinType;
    private final java.util.List lineDashPattern;
    private final float miterLimit;
    private final String name;
    private final vn.ai.trueid.lottie.model.animatable.AnimatableIntegerValue opacity;
    private final vn.ai.trueid.lottie.model.animatable.AnimatablePointValue startPoint;
    private final vn.ai.trueid.lottie.model.animatable.AnimatableFloatValue width;

    public GradientStroke(String p1, vn.ai.trueid.lottie.model.content.GradientType p2, vn.ai.trueid.lottie.model.animatable.AnimatableGradientColorValue p3, vn.ai.trueid.lottie.model.animatable.AnimatableIntegerValue p4, vn.ai.trueid.lottie.model.animatable.AnimatablePointValue p5, vn.ai.trueid.lottie.model.animatable.AnimatablePointValue p6, vn.ai.trueid.lottie.model.animatable.AnimatableFloatValue p7, vn.ai.trueid.lottie.model.content.ShapeStroke$LineCapType p8, vn.ai.trueid.lottie.model.content.ShapeStroke$LineJoinType p9, float p10, java.util.List p11, vn.ai.trueid.lottie.model.animatable.AnimatableFloatValue p12, boolean p13)
    {
        this.name = p1;
        this.gradientType = p2;
        this.gradientColor = p3;
        this.opacity = p4;
        this.startPoint = p5;
        this.endPoint = p6;
        this.width = p7;
        this.capType = p8;
        this.joinType = p9;
        this.miterLimit = p10;
        this.lineDashPattern = p11;
        this.dashOffset = p12;
        this.hidden = p13;
        return;
    }

    public vn.ai.trueid.lottie.model.content.ShapeStroke$LineCapType getCapType()
    {
        return this.capType;
    }

    public vn.ai.trueid.lottie.model.animatable.AnimatableFloatValue getDashOffset()
    {
        return this.dashOffset;
    }

    public vn.ai.trueid.lottie.model.animatable.AnimatablePointValue getEndPoint()
    {
        return this.endPoint;
    }

    public vn.ai.trueid.lottie.model.animatable.AnimatableGradientColorValue getGradientColor()
    {
        return this.gradientColor;
    }

    public vn.ai.trueid.lottie.model.content.GradientType getGradientType()
    {
        return this.gradientType;
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

    public vn.ai.trueid.lottie.model.animatable.AnimatablePointValue getStartPoint()
    {
        return this.startPoint;
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
        return new vn.ai.trueid.lottie.animation.content.GradientStrokeContent(p1, p3, this);
    }
}
