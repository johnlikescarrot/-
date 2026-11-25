package vn.ai.trueid.lottie.model.content;
public class GradientFill implements vn.ai.trueid.lottie.model.content.ContentModel {
    private final vn.ai.trueid.lottie.model.animatable.AnimatablePointValue endPoint;
    private final android.graphics.Path$FillType fillType;
    private final vn.ai.trueid.lottie.model.animatable.AnimatableGradientColorValue gradientColor;
    private final vn.ai.trueid.lottie.model.content.GradientType gradientType;
    private final boolean hidden;
    private final vn.ai.trueid.lottie.model.animatable.AnimatableFloatValue highlightAngle;
    private final vn.ai.trueid.lottie.model.animatable.AnimatableFloatValue highlightLength;
    private final String name;
    private final vn.ai.trueid.lottie.model.animatable.AnimatableIntegerValue opacity;
    private final vn.ai.trueid.lottie.model.animatable.AnimatablePointValue startPoint;

    public GradientFill(String p1, vn.ai.trueid.lottie.model.content.GradientType p2, android.graphics.Path$FillType p3, vn.ai.trueid.lottie.model.animatable.AnimatableGradientColorValue p4, vn.ai.trueid.lottie.model.animatable.AnimatableIntegerValue p5, vn.ai.trueid.lottie.model.animatable.AnimatablePointValue p6, vn.ai.trueid.lottie.model.animatable.AnimatablePointValue p7, vn.ai.trueid.lottie.model.animatable.AnimatableFloatValue p8, vn.ai.trueid.lottie.model.animatable.AnimatableFloatValue p9, boolean p10)
    {
        this.gradientType = p2;
        this.fillType = p3;
        this.gradientColor = p4;
        this.opacity = p5;
        this.startPoint = p6;
        this.endPoint = p7;
        this.name = p1;
        this.highlightLength = p8;
        this.highlightAngle = p9;
        this.hidden = p10;
        return;
    }

    public vn.ai.trueid.lottie.model.animatable.AnimatablePointValue getEndPoint()
    {
        return this.endPoint;
    }

    public android.graphics.Path$FillType getFillType()
    {
        return this.fillType;
    }

    public vn.ai.trueid.lottie.model.animatable.AnimatableGradientColorValue getGradientColor()
    {
        return this.gradientColor;
    }

    public vn.ai.trueid.lottie.model.content.GradientType getGradientType()
    {
        return this.gradientType;
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

    public boolean isHidden()
    {
        return this.hidden;
    }

    public vn.ai.trueid.lottie.animation.content.Content toContent(vn.ai.trueid.lottie.LottieDrawable p2, vn.ai.trueid.lottie.LottieComposition p3, vn.ai.trueid.lottie.model.layer.BaseLayer p4)
    {
        return new vn.ai.trueid.lottie.animation.content.GradientFillContent(p2, p3, p4, this);
    }
}
