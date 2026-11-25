package vn.ai.trueid.lottie.animation.content;
public class GradientFillContent implements vn.ai.trueid.lottie.animation.content.DrawingContent, vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation$AnimationListener, vn.ai.trueid.lottie.animation.content.KeyPathElementContent {
    private static final int CACHE_STEPS_MS = 32;
    private vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation blurAnimation;
    float blurMaskFilterRadius;
    private final android.graphics.RectF boundsRect;
    private final int cacheSteps;
    private final vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation colorAnimation;
    private vn.ai.trueid.lottie.animation.keyframe.ValueCallbackKeyframeAnimation colorCallbackAnimation;
    private vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation colorFilterAnimation;
    private final vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation endPointAnimation;
    private final boolean hidden;
    private final vn.ai.trueid.lottie.model.layer.BaseLayer layer;
    private final P.o linearGradientCache;
    private final vn.ai.trueid.lottie.LottieDrawable lottieDrawable;
    private final String name;
    private final vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation opacityAnimation;
    private final android.graphics.Paint paint;
    private final android.graphics.Path path;
    private final java.util.List paths;
    private final P.o radialGradientCache;
    private final vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation startPointAnimation;
    private final vn.ai.trueid.lottie.model.content.GradientType type;

    public GradientFillContent(vn.ai.trueid.lottie.LottieDrawable p4, vn.ai.trueid.lottie.LottieComposition p5, vn.ai.trueid.lottie.model.layer.BaseLayer p6, vn.ai.trueid.lottie.model.content.GradientFill p7)
    {
        this.linearGradientCache = new P.o(0);
        this.radialGradientCache = new P.o(0);
        android.graphics.Path v0_5 = new android.graphics.Path();
        this.path = v0_5;
        this.paint = new vn.ai.trueid.lottie.animation.LPaint(1);
        this.boundsRect = new android.graphics.RectF();
        this.paths = new java.util.ArrayList();
        this.blurMaskFilterRadius = 0;
        this.layer = p6;
        this.name = p7.getName();
        this.hidden = p7.isHidden();
        this.lottieDrawable = p4;
        this.type = p7.getGradientType();
        v0_5.setFillType(p7.getFillType());
        this.cacheSteps = ((int) (p5.getDuration() / 1107296256));
        vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation v4_7 = p7.getGradientColor().createAnimation();
        this.colorAnimation = v4_7;
        v4_7.addUpdateListener(this);
        p6.addAnimation(v4_7);
        vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation v4_9 = p7.getOpacity().createAnimation();
        this.opacityAnimation = v4_9;
        v4_9.addUpdateListener(this);
        p6.addAnimation(v4_9);
        vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation v4_11 = p7.getStartPoint().createAnimation();
        this.startPointAnimation = v4_11;
        v4_11.addUpdateListener(this);
        p6.addAnimation(v4_11);
        vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation v4_13 = p7.getEndPoint().createAnimation();
        this.endPointAnimation = v4_13;
        v4_13.addUpdateListener(this);
        p6.addAnimation(v4_13);
        if (p6.getBlurEffect() != null) {
            vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation v4_17 = p6.getBlurEffect().getBlurriness().createAnimation();
            this.blurAnimation = v4_17;
            v4_17.addUpdateListener(this);
            p6.addAnimation(this.blurAnimation);
        }
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
        long v3 = ((long) this.getGradientHash());
        P.o v1_5 = ((android.graphics.LinearGradient) this.linearGradientCache.b(v3));
        if (v1_5 == null) {
            int[] v16;
            float[] v15;
            P.o v1_1 = ((android.graphics.PointF) this.startPointAnimation.getValue());
            android.graphics.PointF v2_2 = ((android.graphics.PointF) this.endPointAnimation.getValue());
            int[] v5_2 = ((vn.ai.trueid.lottie.model.content.GradientColor) this.colorAnimation.getValue());
            float[] v6_1 = this.applyDynamicColorsIfNeeded(v5_2.getColors());
            int[] v5_3 = v5_2.getPositions();
            if (v6_1.length >= 2) {
                v16 = v5_3;
                v15 = v6_1;
            } else {
                int[] v5_4 = new int[2];
                v5_4[0] = v6_1[0];
                v5_4[1] = v6_1[0];
                float[] v6_3 = new float[2];
                v6_3[0] = 0;
                v6_3[1] = 1065353216;
                v15 = v5_4;
                v16 = v6_3;
            }
            android.graphics.LinearGradient v10_1 = new android.graphics.LinearGradient(v1_1.x, v1_1.y, v2_2.x, v2_2.y, v15, v16, android.graphics.Shader$TileMode.CLAMP);
            this.linearGradientCache.i(v3, v10_1);
            return v10_1;
        } else {
            return v1_5;
        }
    }

    private android.graphics.RadialGradient getRadialGradient()
    {
        long v3 = ((long) this.getGradientHash());
        P.o v1_10 = ((android.graphics.RadialGradient) this.radialGradientCache.b(v3));
        if (v1_10 == null) {
            float[] v15;
            int[] v16;
            P.o v1_1 = ((android.graphics.PointF) this.startPointAnimation.getValue());
            float v2_2 = ((android.graphics.PointF) this.endPointAnimation.getValue());
            double v5_2 = ((vn.ai.trueid.lottie.model.content.GradientColor) this.colorAnimation.getValue());
            float[] v6_1 = this.applyDynamicColorsIfNeeded(v5_2.getColors());
            double v5_3 = v5_2.getPositions();
            if (v6_1.length >= 2) {
                v16 = v5_3;
                v15 = v6_1;
            } else {
                double v5_4 = new int[2];
                v5_4[0] = v6_1[0];
                v5_4[1] = v6_1[0];
                float[] v6_3 = new float[2];
                v6_3[0] = 0;
                v6_3[1] = 1065353216;
                v15 = v5_4;
                v16 = v6_3;
            }
            float v12 = v1_1.x;
            float v13 = v1_1.y;
            P.o v1_7 = ((float) Math.hypot(((double) (v2_2.x - v12)), ((double) (v2_2.y - v13))));
            if (v1_7 <= 0) {
                v1_7 = 981668463;
            }
            android.graphics.RadialGradient v11_1 = new android.graphics.RadialGradient(v12, v13, v1_7, v15, v16, android.graphics.Shader$TileMode.CLAMP);
            this.radialGradientCache.i(v3, v11_1);
            return v11_1;
        } else {
            return v1_10;
        }
    }

    public void addValueCallback(Object p3, vn.ai.trueid.lottie.value.LottieValueCallback p4)
    {
        if (p3 != vn.ai.trueid.lottie.LottieProperty.OPACITY) {
            if (p3 != vn.ai.trueid.lottie.LottieProperty.COLOR_FILTER) {
                if (p3 != vn.ai.trueid.lottie.LottieProperty.GRADIENT_COLOR) {
                    if (p3 == vn.ai.trueid.lottie.LottieProperty.BLUR_RADIUS) {
                        vn.ai.trueid.lottie.model.layer.BaseLayer v3_15 = this.blurAnimation;
                        if (v3_15 == null) {
                            vn.ai.trueid.lottie.model.layer.BaseLayer v3_2 = new vn.ai.trueid.lottie.animation.keyframe.ValueCallbackKeyframeAnimation(p4);
                            this.blurAnimation = v3_2;
                            v3_2.addUpdateListener(this);
                            this.layer.addAnimation(this.blurAnimation);
                        } else {
                            v3_15.setValueCallback(p4);
                            return;
                        }
                    }
                    return;
                } else {
                    vn.ai.trueid.lottie.model.layer.BaseLayer v3_4 = this.colorCallbackAnimation;
                    if (v3_4 != null) {
                        this.layer.removeAnimation(v3_4);
                    }
                    if (p4 != null) {
                        this.linearGradientCache.a();
                        this.radialGradientCache.a();
                        vn.ai.trueid.lottie.model.layer.BaseLayer v3_8 = new vn.ai.trueid.lottie.animation.keyframe.ValueCallbackKeyframeAnimation(p4);
                        this.colorCallbackAnimation = v3_8;
                        v3_8.addUpdateListener(this);
                        this.layer.addAnimation(this.colorCallbackAnimation);
                        return;
                    } else {
                        this.colorCallbackAnimation = 0;
                        return;
                    }
                }
            } else {
                vn.ai.trueid.lottie.model.layer.BaseLayer v3_10 = this.colorFilterAnimation;
                if (v3_10 != null) {
                    this.layer.removeAnimation(v3_10);
                }
                if (p4 != null) {
                    vn.ai.trueid.lottie.model.layer.BaseLayer v3_12 = new vn.ai.trueid.lottie.animation.keyframe.ValueCallbackKeyframeAnimation(p4);
                    this.colorFilterAnimation = v3_12;
                    v3_12.addUpdateListener(this);
                    this.layer.addAnimation(this.colorFilterAnimation);
                    return;
                } else {
                    this.colorFilterAnimation = 0;
                    return;
                }
            }
        } else {
            this.opacityAnimation.setValueCallback(p4);
            return;
        }
    }

    public void draw(android.graphics.Canvas p6, android.graphics.Matrix p7, int p8, vn.ai.trueid.lottie.utils.DropShadow p9)
    {
        if (!this.hidden) {
            if (vn.ai.trueid.lottie.L.isTraceEnabled()) {
                vn.ai.trueid.lottie.L.beginSection("GradientFillContent#draw");
            }
            this.path.reset();
            android.graphics.BlurMaskFilter v2_0 = 0;
            while (v2_0 < this.paths.size()) {
                this.path.addPath(((vn.ai.trueid.lottie.animation.content.PathContent) this.paths.get(v2_0)).getPath(), p7);
                v2_0++;
            }
            android.graphics.BlurMaskFilter v2_3;
            this.path.computeBounds(this.boundsRect, 0);
            if (this.type != vn.ai.trueid.lottie.model.content.GradientType.LINEAR) {
                v2_3 = this.getRadialGradient();
            } else {
                v2_3 = this.getLinearGradient();
            }
            v2_3.setLocalMatrix(p7);
            this.paint.setShader(v2_3);
            android.graphics.Path v7_2 = this.colorFilterAnimation;
            if (v7_2 != null) {
                this.paint.setColorFilter(((android.graphics.ColorFilter) v7_2.getValue()));
            }
            android.graphics.Path v7_5 = this.blurAnimation;
            if (v7_5 != null) {
                android.graphics.Path v7_8 = ((Float) v7_5.getValue()).floatValue();
                if (v7_8 != 0) {
                    if (v7_8 != this.blurMaskFilterRadius) {
                        this.paint.setMaskFilter(new android.graphics.BlurMaskFilter(v7_8, android.graphics.BlurMaskFilter$Blur.NORMAL));
                    }
                } else {
                    this.paint.setMaskFilter(0);
                }
                this.blurMaskFilterRadius = v7_8;
            }
            android.graphics.Path v7_14 = (((float) ((Integer) this.opacityAnimation.getValue()).intValue()) / 1120403456);
            this.paint.setAlpha(vn.ai.trueid.lottie.utils.MiscUtils.clamp(((int) (((float) p8) * v7_14)), 0, 255));
            if (p9 != null) {
                p9.applyWithAlpha(((int) (v7_14 * 1132396544)), this.paint);
            }
            p6.drawPath(this.path, this.paint);
            if (vn.ai.trueid.lottie.L.isTraceEnabled()) {
                vn.ai.trueid.lottie.L.endSection("GradientFillContent#draw");
            }
        }
        return;
    }

    public void getBounds(android.graphics.RectF p4, android.graphics.Matrix p5, boolean p6)
    {
        this.path.reset();
        float v0_2 = 0;
        while (v0_2 < this.paths.size()) {
            this.path.addPath(((vn.ai.trueid.lottie.animation.content.PathContent) this.paths.get(v0_2)).getPath(), p5);
            v0_2++;
        }
        this.path.computeBounds(p4, 0);
        p4.set((p4.left - 1065353216), (p4.top - 1065353216), (p4.right + 1065353216), (p4.bottom + 1065353216));
        return;
    }

    public String getName()
    {
        return this.name;
    }

    public void onValueChanged()
    {
        this.lottieDrawable.invalidateSelf();
        return;
    }

    public void resolveKeyPath(vn.ai.trueid.lottie.model.KeyPath p1, int p2, java.util.List p3, vn.ai.trueid.lottie.model.KeyPath p4)
    {
        vn.ai.trueid.lottie.utils.MiscUtils.resolveKeyPath(p1, p2, p3, p4, this);
        return;
    }

    public void setContents(java.util.List p3, java.util.List p4)
    {
        p3 = 0;
        while (p3 < p4.size()) {
            vn.ai.trueid.lottie.animation.content.PathContent v0_3 = ((vn.ai.trueid.lottie.animation.content.Content) p4.get(p3));
            if ((v0_3 instanceof vn.ai.trueid.lottie.animation.content.PathContent)) {
                this.paths.add(((vn.ai.trueid.lottie.animation.content.PathContent) v0_3));
            }
            p3++;
        }
        return;
    }
}
