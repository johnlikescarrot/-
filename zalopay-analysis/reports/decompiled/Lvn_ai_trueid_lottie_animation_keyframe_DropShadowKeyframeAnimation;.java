package vn.ai.trueid.lottie.animation.keyframe;
public class DropShadowKeyframeAnimation implements vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation$AnimationListener {
    private static final float AFTER_EFFECT_SOFTNESS_SCALE_FACTOR = 1051260355;
    private static final float DEG_TO_RAD = 1016003125;
    private final vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation color;
    private final vn.ai.trueid.lottie.animation.keyframe.FloatKeyframeAnimation direction;
    private final vn.ai.trueid.lottie.animation.keyframe.FloatKeyframeAnimation distance;
    private final vn.ai.trueid.lottie.model.layer.BaseLayer layer;
    private android.graphics.Matrix layerInvMatrix;
    private final vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation$AnimationListener listener;
    private final vn.ai.trueid.lottie.animation.keyframe.FloatKeyframeAnimation opacity;
    private final vn.ai.trueid.lottie.animation.keyframe.FloatKeyframeAnimation radius;

    public DropShadowKeyframeAnimation(vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation$AnimationListener p1, vn.ai.trueid.lottie.model.layer.BaseLayer p2, vn.ai.trueid.lottie.parser.DropShadowEffect p3)
    {
        this.listener = p1;
        this.layer = p2;
        vn.ai.trueid.lottie.animation.keyframe.FloatKeyframeAnimation v1_10 = p3.getColor().createAnimation();
        this.color = v1_10;
        v1_10.addUpdateListener(this);
        p2.addAnimation(v1_10);
        vn.ai.trueid.lottie.animation.keyframe.FloatKeyframeAnimation v1_2 = p3.getOpacity().createAnimation();
        this.opacity = v1_2;
        v1_2.addUpdateListener(this);
        p2.addAnimation(v1_2);
        vn.ai.trueid.lottie.animation.keyframe.FloatKeyframeAnimation v1_4 = p3.getDirection().createAnimation();
        this.direction = v1_4;
        v1_4.addUpdateListener(this);
        p2.addAnimation(v1_4);
        vn.ai.trueid.lottie.animation.keyframe.FloatKeyframeAnimation v1_6 = p3.getDistance().createAnimation();
        this.distance = v1_6;
        v1_6.addUpdateListener(this);
        p2.addAnimation(v1_6);
        vn.ai.trueid.lottie.animation.keyframe.FloatKeyframeAnimation v1_8 = p3.getRadius().createAnimation();
        this.radius = v1_8;
        v1_8.addUpdateListener(this);
        p2.addAnimation(v1_8);
        return;
    }

    public vn.ai.trueid.lottie.utils.DropShadow evaluate(android.graphics.Matrix p7, int p8)
    {
        float v0_4 = (this.direction.getFloatValue() * 1016003125);
        float v1_0 = ((Float) this.distance.getValue()).floatValue();
        float v2_0 = ((double) v0_4);
        float v0_2 = (((float) Math.sin(v2_0)) * v1_0);
        float v2_4 = (((float) Math.cos((v2_0 + 4614256656552045848))) * v1_0);
        float v1_4 = ((Float) this.radius.getValue()).floatValue();
        vn.ai.trueid.lottie.utils.DropShadow v3_3 = ((Integer) this.color.getValue()).intValue();
        vn.ai.trueid.lottie.utils.DropShadow v3_6 = new vn.ai.trueid.lottie.utils.DropShadow((v1_4 * 1051260355), v0_2, v2_4, android.graphics.Color.argb(Math.round(((((Float) this.opacity.getValue()).floatValue() * ((float) p8)) / 1132396544)), android.graphics.Color.red(v3_3), android.graphics.Color.green(v3_3), android.graphics.Color.blue(v3_3)));
        v3_6.transformBy(p7);
        if (this.layerInvMatrix == null) {
            this.layerInvMatrix = new android.graphics.Matrix();
        }
        this.layer.transform.getMatrix().invert(this.layerInvMatrix);
        v3_6.transformBy(this.layerInvMatrix);
        return v3_6;
    }

    public void onValueChanged()
    {
        this.listener.onValueChanged();
        return;
    }

    public void setColorCallback(vn.ai.trueid.lottie.value.LottieValueCallback p2)
    {
        this.color.setValueCallback(p2);
        return;
    }

    public void setDirectionCallback(vn.ai.trueid.lottie.value.LottieValueCallback p2)
    {
        this.direction.setValueCallback(p2);
        return;
    }

    public void setDistanceCallback(vn.ai.trueid.lottie.value.LottieValueCallback p2)
    {
        this.distance.setValueCallback(p2);
        return;
    }

    public void setOpacityCallback(vn.ai.trueid.lottie.value.LottieValueCallback p3)
    {
        if (p3 != null) {
            this.opacity.setValueCallback(new vn.ai.trueid.lottie.animation.keyframe.DropShadowKeyframeAnimation$1(this, p3));
            return;
        } else {
            this.opacity.setValueCallback(0);
            return;
        }
    }

    public void setRadiusCallback(vn.ai.trueid.lottie.value.LottieValueCallback p2)
    {
        this.radius.setValueCallback(p2);
        return;
    }
}
