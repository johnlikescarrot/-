package vn.ai.trueid.lottie.model.content;
public class ShapeFill implements vn.ai.trueid.lottie.model.content.ContentModel {
    private final vn.ai.trueid.lottie.model.animatable.AnimatableColorValue color;
    private final boolean fillEnabled;
    private final android.graphics.Path$FillType fillType;
    private final boolean hidden;
    private final String name;
    private final vn.ai.trueid.lottie.model.animatable.AnimatableIntegerValue opacity;

    public ShapeFill(String p1, boolean p2, android.graphics.Path$FillType p3, vn.ai.trueid.lottie.model.animatable.AnimatableColorValue p4, vn.ai.trueid.lottie.model.animatable.AnimatableIntegerValue p5, boolean p6)
    {
        this.name = p1;
        this.fillEnabled = p2;
        this.fillType = p3;
        this.color = p4;
        this.opacity = p5;
        this.hidden = p6;
        return;
    }

    public vn.ai.trueid.lottie.model.animatable.AnimatableColorValue getColor()
    {
        return this.color;
    }

    public android.graphics.Path$FillType getFillType()
    {
        return this.fillType;
    }

    public String getName()
    {
        return this.name;
    }

    public vn.ai.trueid.lottie.model.animatable.AnimatableIntegerValue getOpacity()
    {
        return this.opacity;
    }

    public boolean isHidden()
    {
        return this.hidden;
    }

    public vn.ai.trueid.lottie.animation.content.Content toContent(vn.ai.trueid.lottie.LottieDrawable p1, vn.ai.trueid.lottie.LottieComposition p2, vn.ai.trueid.lottie.model.layer.BaseLayer p3)
    {
        return new vn.ai.trueid.lottie.animation.content.FillContent(p1, p3, this);
    }

    public String toString()
    {
        return androidx.activity.result.e.r(new StringBuilder("ShapeFill{color=, fillEnabled="), this.fillEnabled, 125);
    }
}
