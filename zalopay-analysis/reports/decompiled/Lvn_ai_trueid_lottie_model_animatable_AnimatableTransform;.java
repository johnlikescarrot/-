package vn.ai.trueid.lottie.model.animatable;
public class AnimatableTransform implements vn.ai.trueid.lottie.animation.content.ModifierContent, vn.ai.trueid.lottie.model.content.ContentModel {
    private final vn.ai.trueid.lottie.model.animatable.AnimatablePathValue anchorPoint;
    private boolean autoOrient;
    private final vn.ai.trueid.lottie.model.animatable.AnimatableFloatValue endOpacity;
    private final vn.ai.trueid.lottie.model.animatable.AnimatableIntegerValue opacity;
    private final vn.ai.trueid.lottie.model.animatable.AnimatableValue position;
    private final vn.ai.trueid.lottie.model.animatable.AnimatableFloatValue rotation;
    private final vn.ai.trueid.lottie.model.animatable.AnimatableFloatValue rotationX;
    private final vn.ai.trueid.lottie.model.animatable.AnimatableFloatValue rotationY;
    private final vn.ai.trueid.lottie.model.animatable.AnimatableFloatValue rotationZ;
    private final vn.ai.trueid.lottie.model.animatable.AnimatableScaleValue scale;
    private final vn.ai.trueid.lottie.model.animatable.AnimatableFloatValue skew;
    private final vn.ai.trueid.lottie.model.animatable.AnimatableFloatValue skewAngle;
    private final vn.ai.trueid.lottie.model.animatable.AnimatableFloatValue startOpacity;

    public AnimatableTransform()
    {
        this(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        return;
    }

    public AnimatableTransform(vn.ai.trueid.lottie.model.animatable.AnimatablePathValue p14, vn.ai.trueid.lottie.model.animatable.AnimatableValue p15, vn.ai.trueid.lottie.model.animatable.AnimatableScaleValue p16, vn.ai.trueid.lottie.model.animatable.AnimatableFloatValue p17, vn.ai.trueid.lottie.model.animatable.AnimatableIntegerValue p18, vn.ai.trueid.lottie.model.animatable.AnimatableFloatValue p19, vn.ai.trueid.lottie.model.animatable.AnimatableFloatValue p20, vn.ai.trueid.lottie.model.animatable.AnimatableFloatValue p21, vn.ai.trueid.lottie.model.animatable.AnimatableFloatValue p22)
    {
        this(p14, p15, p16, p17, p18, p19, p20, p21, p22, 0, 0, 0);
        return;
    }

    public AnimatableTransform(vn.ai.trueid.lottie.model.animatable.AnimatablePathValue p2, vn.ai.trueid.lottie.model.animatable.AnimatableValue p3, vn.ai.trueid.lottie.model.animatable.AnimatableScaleValue p4, vn.ai.trueid.lottie.model.animatable.AnimatableFloatValue p5, vn.ai.trueid.lottie.model.animatable.AnimatableIntegerValue p6, vn.ai.trueid.lottie.model.animatable.AnimatableFloatValue p7, vn.ai.trueid.lottie.model.animatable.AnimatableFloatValue p8, vn.ai.trueid.lottie.model.animatable.AnimatableFloatValue p9, vn.ai.trueid.lottie.model.animatable.AnimatableFloatValue p10, vn.ai.trueid.lottie.model.animatable.AnimatableFloatValue p11, vn.ai.trueid.lottie.model.animatable.AnimatableFloatValue p12, vn.ai.trueid.lottie.model.animatable.AnimatableFloatValue p13)
    {
        this.autoOrient = 0;
        this.anchorPoint = p2;
        this.position = p3;
        this.scale = p4;
        this.rotation = p5;
        this.opacity = p6;
        this.startOpacity = p7;
        this.endOpacity = p8;
        this.skew = p9;
        this.skewAngle = p10;
        this.rotationX = p11;
        this.rotationY = p12;
        this.rotationZ = p13;
        return;
    }

    public vn.ai.trueid.lottie.animation.keyframe.TransformKeyframeAnimation createAnimation()
    {
        return new vn.ai.trueid.lottie.animation.keyframe.TransformKeyframeAnimation(this);
    }

    public vn.ai.trueid.lottie.model.animatable.AnimatablePathValue getAnchorPoint()
    {
        return this.anchorPoint;
    }

    public vn.ai.trueid.lottie.model.animatable.AnimatableFloatValue getEndOpacity()
    {
        return this.endOpacity;
    }

    public vn.ai.trueid.lottie.model.animatable.AnimatableIntegerValue getOpacity()
    {
        return this.opacity;
    }

    public vn.ai.trueid.lottie.model.animatable.AnimatableValue getPosition()
    {
        return this.position;
    }

    public vn.ai.trueid.lottie.model.animatable.AnimatableFloatValue getRotation()
    {
        return this.rotation;
    }

    public vn.ai.trueid.lottie.model.animatable.AnimatableFloatValue getRotationX()
    {
        return this.rotationX;
    }

    public vn.ai.trueid.lottie.model.animatable.AnimatableFloatValue getRotationY()
    {
        return this.rotationY;
    }

    public vn.ai.trueid.lottie.model.animatable.AnimatableFloatValue getRotationZ()
    {
        return this.rotationZ;
    }

    public vn.ai.trueid.lottie.model.animatable.AnimatableScaleValue getScale()
    {
        return this.scale;
    }

    public vn.ai.trueid.lottie.model.animatable.AnimatableFloatValue getSkew()
    {
        return this.skew;
    }

    public vn.ai.trueid.lottie.model.animatable.AnimatableFloatValue getSkewAngle()
    {
        return this.skewAngle;
    }

    public vn.ai.trueid.lottie.model.animatable.AnimatableFloatValue getStartOpacity()
    {
        return this.startOpacity;
    }

    public boolean has3DRotation()
    {
        if ((this.rotationX == null) && ((this.rotationY == null) && (this.rotationZ == null))) {
            return 0;
        } else {
            return 1;
        }
    }

    public boolean isAutoOrient()
    {
        return this.autoOrient;
    }

    public void setAutoOrient(boolean p1)
    {
        this.autoOrient = p1;
        return;
    }

    public vn.ai.trueid.lottie.animation.content.Content toContent(vn.ai.trueid.lottie.LottieDrawable p1, vn.ai.trueid.lottie.LottieComposition p2, vn.ai.trueid.lottie.model.layer.BaseLayer p3)
    {
        return 0;
    }
}
