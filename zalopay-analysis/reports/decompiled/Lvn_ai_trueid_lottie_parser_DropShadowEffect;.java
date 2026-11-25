package vn.ai.trueid.lottie.parser;
public class DropShadowEffect {
    private final vn.ai.trueid.lottie.model.animatable.AnimatableColorValue color;
    private final vn.ai.trueid.lottie.model.animatable.AnimatableFloatValue direction;
    private final vn.ai.trueid.lottie.model.animatable.AnimatableFloatValue distance;
    private final vn.ai.trueid.lottie.model.animatable.AnimatableFloatValue opacity;
    private final vn.ai.trueid.lottie.model.animatable.AnimatableFloatValue radius;

    public DropShadowEffect(vn.ai.trueid.lottie.model.animatable.AnimatableColorValue p1, vn.ai.trueid.lottie.model.animatable.AnimatableFloatValue p2, vn.ai.trueid.lottie.model.animatable.AnimatableFloatValue p3, vn.ai.trueid.lottie.model.animatable.AnimatableFloatValue p4, vn.ai.trueid.lottie.model.animatable.AnimatableFloatValue p5)
    {
        this.color = p1;
        this.opacity = p2;
        this.direction = p3;
        this.distance = p4;
        this.radius = p5;
        return;
    }

    public vn.ai.trueid.lottie.model.animatable.AnimatableColorValue getColor()
    {
        return this.color;
    }

    public vn.ai.trueid.lottie.model.animatable.AnimatableFloatValue getDirection()
    {
        return this.direction;
    }

    public vn.ai.trueid.lottie.model.animatable.AnimatableFloatValue getDistance()
    {
        return this.distance;
    }

    public vn.ai.trueid.lottie.model.animatable.AnimatableFloatValue getOpacity()
    {
        return this.opacity;
    }

    public vn.ai.trueid.lottie.model.animatable.AnimatableFloatValue getRadius()
    {
        return this.radius;
    }
}
