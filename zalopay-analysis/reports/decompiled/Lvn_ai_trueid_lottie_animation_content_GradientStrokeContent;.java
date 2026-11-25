package vn.ai.trueid.lottie.animation.content;
public class GradientStrokeContent extends vn.ai.trueid.lottie.animation.content.BaseStrokeContent {
    private static final int CACHE_STEPS_MS = 32;
    private final android.graphics.RectF boundsRect;
    private final int cacheSteps;
    private final vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation colorAnimation;
    private vn.ai.trueid.lottie.animation.keyframe.ValueCallbackKeyframeAnimation colorCallbackAnimation;
    private final vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation endPointAnimation;
    private final boolean hidden;
    private final P.o linearGradientCache;
    private final String name;
    private final P.o radialGradientCache;
    private final vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation startPointAnimation;
    private final vn.ai.trueid.lottie.model.content.GradientType type;

    public GradientStrokeContent(vn.ai.trueid.lottie.LottieDrawable p12, vn.ai.trueid.lottie.model.layer.BaseLayer p13, vn.ai.trueid.lottie.model.content.GradientStroke p14)
    {
        vn.ai.trueid.lottie.animation.content.GradientStrokeContent v1_1 = super(p12, p13, p14.getCapType().toPaintCap(), p14.getJoinType().toPaintJoin(), p14.getMiterLimit(), p14.getOpacity(), p14.getWidth(), p14.getLineDashPattern(), p14.getDashOffset());
        v1_1.linearGradientCache = new P.o(0);
        v1_1.radialGradientCache = new P.o(0);
        v1_1.boundsRect = new android.graphics.RectF();
        v1_1.name = p14.getName();
        v1_1.type = p14.getGradientType();
        v1_1.hidden = p14.isHidden();
        v1_1.cacheSteps = ((int) (p12.getComposition().getDuration() / 1107296256));
        vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation v12_15 = p14.getGradientColor().createAnimation();
        v1_1.colorAnimation = v12_15;
        v12_15.addUpdateListener(super);
        p13.addAnimation(v12_15);
        vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation v12_17 = p14.getStartPoint().createAnimation();
        v1_1.startPointAnimation = v12_17;
        v12_17.addUpdateListener(super);
        p13.addAnimation(v12_17);
        vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation v12_19 = p14.getEndPoint().createAnimation();
        v1_1.endPointAnimation = v12_19;
        v12_19.addUpdateListener(super);
        p13.addAnimation(v12_19);
        return;
    }

    private int[] applyDynamicColorsIfNeeded(int[] p5)
    {
        Integer[] v0_0 = this.colorCallbackAnimation;
        if (v0_0 != null) {
            Integer[] v0_2 = ((Integer[]) v0_0.getValue());
            int v3 = 0;
            if (p5.length != v0_2.length) {
                p5 = new int[v0_2.length];
                while (v3 < v0_2.length) {
                    p5[v3] = v0_2[v3].intValue();
                    v3++;
                }
                return p5;
            }
            while (v3 < p5.length) {
                p5[v3] = v0_2[v3].intValue();
                v3++;
            }
        }
        return p5;
    }

    private int getGradientHash()
    {
        int v3_2;
        int v0_3 = Math.round((this.startPointAnimation.getProgress() * ((float) this.cacheSteps)));
        int v1_2 = Math.round((this.endPointAnimation.getProgress() * ((float) this.cacheSteps)));
        int v2_5 = Math.round((this.colorAnimation.getProgress() * ((float) this.cacheSteps)));
        if (v0_3 == 0) {
            v3_2 = 17;
        } else {
            v3_2 = (527 * v0_3);
        }
        if (v1_2 != 0) {
            v3_2 = ((v3_2 * 31) * v1_2);
        }
        if (v2_5 != 0) {
            v3_2 = ((v3_2 * 31) * v2_5);
        }
        return v3_2;
    }

    private android.graphics.LinearGradient getLinearGradient()
    {
        long v2 = ((long) this.getGradientHash());
        P.o v0_5 = ((android.graphics.LinearGradient) this.linearGradientCache.b(v2));
        if (v0_5 == null) {
            P.o v0_2 = ((android.graphics.PointF) this.startPointAnimation.getValue());
            android.graphics.PointF v1_2 = ((android.graphics.PointF) this.endPointAnimation.getValue());
            vn.ai.trueid.lottie.model.content.GradientColor v4_2 = ((vn.ai.trueid.lottie.model.content.GradientColor) this.colorAnimation.getValue());
            android.graphics.LinearGradient v6_1 = new android.graphics.LinearGradient(v0_2.x, v0_2.y, v1_2.x, v1_2.y, this.applyDynamicColorsIfNeeded(v4_2.getColors()), v4_2.getPositions(), android.graphics.Shader$TileMode.CLAMP);
            this.linearGradientCache.i(v2, v6_1);
            return v6_1;
        } else {
            return v0_5;
        }
    }

    private android.graphics.RadialGradient getRadialGradient()
    {
        long v2 = ((long) this.getGradientHash());
        P.o v0_9 = ((android.graphics.RadialGradient) this.radialGradientCache.b(v2));
        if (v0_9 == null) {
            P.o v0_2 = ((android.graphics.PointF) this.startPointAnimation.getValue());
            float v1_2 = ((android.graphics.PointF) this.endPointAnimation.getValue());
            double v4_2 = ((vn.ai.trueid.lottie.model.content.GradientColor) this.colorAnimation.getValue());
            float v7 = v0_2.x;
            float v8 = v0_2.y;
            android.graphics.RadialGradient v6_1 = new android.graphics.RadialGradient(v7, v8, ((float) Math.hypot(((double) (v1_2.x - v7)), ((double) (v1_2.y - v8)))), this.applyDynamicColorsIfNeeded(v4_2.getColors()), v4_2.getPositions(), android.graphics.Shader$TileMode.CLAMP);
            this.radialGradientCache.i(v2, v6_1);
            return v6_1;
        } else {
            return v0_9;
        }
    }

    public void addValueCallback(Object p2, vn.ai.trueid.lottie.value.LottieValueCallback p3)
    {
        super.addValueCallback(p2, p3);
        if (p2 == vn.ai.trueid.lottie.LottieProperty.GRADIENT_COLOR) {
            vn.ai.trueid.lottie.model.layer.BaseLayer v2_3 = this.colorCallbackAnimation;
            if (v2_3 != null) {
                this.layer.removeAnimation(v2_3);
            }
            if (p3 != null) {
                vn.ai.trueid.lottie.model.layer.BaseLayer v2_5 = new vn.ai.trueid.lottie.animation.keyframe.ValueCallbackKeyframeAnimation(p3);
                this.colorCallbackAnimation = v2_5;
                v2_5.addUpdateListener(this);
                this.layer.addAnimation(this.colorCallbackAnimation);
            } else {
                this.colorCallbackAnimation = 0;
                return;
            }
        }
        return;
    }

    public void draw(android.graphics.Canvas p3, android.graphics.Matrix p4, int p5, vn.ai.trueid.lottie.utils.DropShadow p6)
    {
        if (!this.hidden) {
            android.graphics.RadialGradient v0_1;
            this.getBounds(this.boundsRect, p4, 0);
            if (this.type != vn.ai.trueid.lottie.model.content.GradientType.LINEAR) {
                v0_1 = this.getRadialGradient();
            } else {
                v0_1 = this.getLinearGradient();
            }
            this.paint.setShader(v0_1);
            super.draw(p3, p4, p5, p6);
            return;
        } else {
            return;
        }
    }

    public String getName()
    {
        return this.name;
    }
}
