package vn.ai.trueid.lottie.animation.content;
public abstract class BaseStrokeContent implements vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation$AnimationListener, vn.ai.trueid.lottie.animation.content.KeyPathElementContent, vn.ai.trueid.lottie.animation.content.DrawingContent {
    private vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation blurAnimation;
    float blurMaskFilterRadius;
    private vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation colorFilterAnimation;
    private final java.util.List dashPatternAnimations;
    private final vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation dashPatternOffsetAnimation;
    private final float[] dashPatternValues;
    protected final vn.ai.trueid.lottie.model.layer.BaseLayer layer;
    private final vn.ai.trueid.lottie.LottieDrawable lottieDrawable;
    private final vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation opacityAnimation;
    final android.graphics.Paint paint;
    private final android.graphics.Path path;
    private final java.util.List pathGroups;
    private final android.graphics.PathMeasure pm;
    private final android.graphics.RectF rect;
    private final android.graphics.Path trimPathPath;
    private final vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation widthAnimation;

    public BaseStrokeContent(vn.ai.trueid.lottie.LottieDrawable p3, vn.ai.trueid.lottie.model.layer.BaseLayer p4, android.graphics.Paint$Cap p5, android.graphics.Paint$Join p6, float p7, vn.ai.trueid.lottie.model.animatable.AnimatableIntegerValue p8, vn.ai.trueid.lottie.model.animatable.AnimatableFloatValue p9, java.util.List p10, vn.ai.trueid.lottie.model.animatable.AnimatableFloatValue p11)
    {
        this.pm = new android.graphics.PathMeasure();
        this.path = new android.graphics.Path();
        this.trimPathPath = new android.graphics.Path();
        this.rect = new android.graphics.RectF();
        this.pathGroups = new java.util.ArrayList();
        vn.ai.trueid.lottie.animation.LPaint v0_6 = new vn.ai.trueid.lottie.animation.LPaint(1);
        this.paint = v0_6;
        this.blurMaskFilterRadius = 0;
        this.lottieDrawable = p3;
        this.layer = p4;
        v0_6.setStyle(android.graphics.Paint$Style.STROKE);
        v0_6.setStrokeCap(p5);
        v0_6.setStrokeJoin(p6);
        v0_6.setStrokeMiter(p7);
        this.opacityAnimation = p8.createAnimation();
        this.widthAnimation = p9.createAnimation();
        if (p11 != null) {
            this.dashPatternOffsetAnimation = p11.createAnimation();
        } else {
            this.dashPatternOffsetAnimation = 0;
        }
        this.dashPatternAnimations = new java.util.ArrayList(p10.size());
        vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation v3_9 = new float[p10.size()];
        this.dashPatternValues = v3_9;
        vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation v3_10 = 0;
        vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation v5_2 = 0;
        while (v5_2 < p10.size()) {
            this.dashPatternAnimations.add(((vn.ai.trueid.lottie.model.animatable.AnimatableFloatValue) p10.get(v5_2)).createAnimation());
            v5_2++;
        }
        p4.addAnimation(this.opacityAnimation);
        p4.addAnimation(this.widthAnimation);
        vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation v5_1 = 0;
        while (v5_1 < this.dashPatternAnimations.size()) {
            p4.addAnimation(((vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation) this.dashPatternAnimations.get(v5_1)));
            v5_1++;
        }
        vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation v5_6 = this.dashPatternOffsetAnimation;
        if (v5_6 != null) {
            p4.addAnimation(v5_6);
        }
        this.opacityAnimation.addUpdateListener(this);
        this.widthAnimation.addUpdateListener(this);
        while (v3_10 < p10.size()) {
            ((vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation) this.dashPatternAnimations.get(v3_10)).addUpdateListener(this);
            v3_10++;
        }
        vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation v3_11 = this.dashPatternOffsetAnimation;
        if (v3_11 != null) {
            v3_11.addUpdateListener(this);
        }
        if (p4.getBlurEffect() != null) {
            vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation v3_15 = p4.getBlurEffect().getBlurriness().createAnimation();
            this.blurAnimation = v3_15;
            v3_15.addUpdateListener(this);
            p4.addAnimation(this.blurAnimation);
        }
        return;
    }

    private void applyDashPatternIfNeeded()
    {
        if (vn.ai.trueid.lottie.L.isTraceEnabled()) {
            vn.ai.trueid.lottie.L.beginSection("StrokeContent#applyDashPattern");
        }
        if (!this.dashPatternAnimations.isEmpty()) {
            boolean v0_7 = 0;
            while (v0_7 < this.dashPatternAnimations.size()) {
                this.dashPatternValues[v0_7] = ((Float) ((vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation) this.dashPatternAnimations.get(v0_7)).getValue()).floatValue();
                if ((v0_7 % 2) != 0) {
                    android.graphics.Paint v2_5 = this.dashPatternValues;
                    if (v2_5[v0_7] < 1036831949) {
                        v2_5[v0_7] = 1036831949;
                    }
                } else {
                    android.graphics.Paint v2_6 = this.dashPatternValues;
                    if (v2_6[v0_7] < 1065353216) {
                        v2_6[v0_7] = 1065353216;
                    }
                }
                v0_7++;
            }
            boolean v0_4;
            boolean v0_1 = this.dashPatternOffsetAnimation;
            if (v0_1) {
                v0_4 = ((Float) v0_1.getValue()).floatValue();
            } else {
                v0_4 = 0;
            }
            this.paint.setPathEffect(new android.graphics.DashPathEffect(this.dashPatternValues, v0_4));
            if (vn.ai.trueid.lottie.L.isTraceEnabled()) {
                vn.ai.trueid.lottie.L.endSection("StrokeContent#applyDashPattern");
            }
        } else {
            if (vn.ai.trueid.lottie.L.isTraceEnabled()) {
                vn.ai.trueid.lottie.L.endSection("StrokeContent#applyDashPattern");
                return;
            }
        }
        return;
    }

    private void applyTrimPath(android.graphics.Canvas p14, vn.ai.trueid.lottie.animation.content.BaseStrokeContent$PathGroup p15)
    {
        if (vn.ai.trueid.lottie.L.isTraceEnabled()) {
            vn.ai.trueid.lottie.L.beginSection("StrokeContent#applyTrimPath");
        }
        if (vn.ai.trueid.lottie.animation.content.BaseStrokeContent$PathGroup.access$200(p15) != null) {
            this.path.reset();
            float v0_3 = (vn.ai.trueid.lottie.animation.content.BaseStrokeContent$PathGroup.access$100(p15).size() - 1);
            while (v0_3 >= 0) {
                this.path.addPath(((vn.ai.trueid.lottie.animation.content.PathContent) vn.ai.trueid.lottie.animation.content.BaseStrokeContent$PathGroup.access$100(p15).get(v0_3)).getPath());
                v0_3--;
            }
            float v0_10 = (((Float) vn.ai.trueid.lottie.animation.content.BaseStrokeContent$PathGroup.access$200(p15).getStart().getValue()).floatValue() / 1120403456);
            int v3_10 = (((Float) vn.ai.trueid.lottie.animation.content.BaseStrokeContent$PathGroup.access$200(p15).getEnd().getValue()).floatValue() / 1120403456);
            float v2_7 = (((Float) vn.ai.trueid.lottie.animation.content.BaseStrokeContent$PathGroup.access$200(p15).getOffset().getValue()).floatValue() / 1135869952);
            if ((v0_10 >= 1008981770) || (v3_10 <= 1065185444)) {
                this.pm.setPath(this.path, 0);
                float v4_0 = this.pm.getLength();
                while (this.pm.nextContour()) {
                    v4_0 += this.pm.getLength();
                }
                float v2_8 = (v2_7 * v4_0);
                float v0_13 = ((v0_10 * v4_0) + v2_8);
                float v2_11 = Math.min(((v3_10 * v4_0) + v2_8), ((v0_13 + v4_0) - 1065353216));
                int v3_0 = (vn.ai.trueid.lottie.animation.content.BaseStrokeContent$PathGroup.access$100(p15).size() - 1);
                float v8 = 0;
                while (v3_0 >= 0) {
                    this.trimPathPath.set(((vn.ai.trueid.lottie.animation.content.PathContent) vn.ai.trueid.lottie.animation.content.BaseStrokeContent$PathGroup.access$100(p15).get(v3_0)).getPath());
                    this.pm.setPath(this.trimPathPath, 0);
                    float v9_0 = this.pm.getLength();
                    if (v2_11 <= v4_0) {
                        android.graphics.Path v10_2 = (v8 + v9_0);
                        if ((v10_2 >= v0_13) && (v8 <= v2_11)) {
                            if ((v10_2 > v2_11) || (v0_13 >= v8)) {
                                android.graphics.Paint v11_9;
                                if (v0_13 >= v8) {
                                    v11_9 = ((v0_13 - v8) / v9_0);
                                } else {
                                    v11_9 = 0;
                                }
                                android.graphics.Path v10_5;
                                if (v2_11 <= v10_2) {
                                    v10_5 = ((v2_11 - v8) / v9_0);
                                } else {
                                    v10_5 = 1065353216;
                                }
                                vn.ai.trueid.lottie.utils.Utils.applyTrimPathIfNeeded(this.trimPathPath, v11_9, v10_5, 0);
                                p14.drawPath(this.trimPathPath, this.paint);
                            } else {
                                p14.drawPath(this.trimPathPath, this.paint);
                            }
                        }
                    } else {
                        android.graphics.Path v10_1 = (v2_11 - v4_0);
                        if ((v10_1 >= (v8 + v9_0)) || (v8 >= v10_1)) {
                        } else {
                            android.graphics.Paint v11_13;
                            if (v0_13 <= v4_0) {
                                v11_13 = 0;
                            } else {
                                v11_13 = ((v0_13 - v4_0) / v9_0);
                            }
                            vn.ai.trueid.lottie.utils.Utils.applyTrimPathIfNeeded(this.trimPathPath, v11_13, Math.min((v10_1 / v9_0), 1065353216), 0);
                            p14.drawPath(this.trimPathPath, this.paint);
                        }
                    }
                    v8 += v9_0;
                    v3_0--;
                }
                if (vn.ai.trueid.lottie.L.isTraceEnabled()) {
                    vn.ai.trueid.lottie.L.endSection("StrokeContent#applyTrimPath");
                }
            } else {
                p14.drawPath(this.path, this.paint);
                if (vn.ai.trueid.lottie.L.isTraceEnabled()) {
                    vn.ai.trueid.lottie.L.endSection("StrokeContent#applyTrimPath");
                    return;
                }
            }
        } else {
            if (vn.ai.trueid.lottie.L.isTraceEnabled()) {
                vn.ai.trueid.lottie.L.endSection("StrokeContent#applyTrimPath");
                return;
            }
        }
        return;
    }

    public void addValueCallback(Object p2, vn.ai.trueid.lottie.value.LottieValueCallback p3)
    {
        if (p2 != vn.ai.trueid.lottie.LottieProperty.OPACITY) {
            if (p2 != vn.ai.trueid.lottie.LottieProperty.STROKE_WIDTH) {
                if (p2 != vn.ai.trueid.lottie.LottieProperty.COLOR_FILTER) {
                    if (p2 == vn.ai.trueid.lottie.LottieProperty.BLUR_RADIUS) {
                        vn.ai.trueid.lottie.model.layer.BaseLayer v2_11 = this.blurAnimation;
                        if (v2_11 == null) {
                            vn.ai.trueid.lottie.model.layer.BaseLayer v2_2 = new vn.ai.trueid.lottie.animation.keyframe.ValueCallbackKeyframeAnimation(p3);
                            this.blurAnimation = v2_2;
                            v2_2.addUpdateListener(this);
                            this.layer.addAnimation(this.blurAnimation);
                        } else {
                            v2_11.setValueCallback(p3);
                            return;
                        }
                    }
                    return;
                } else {
                    vn.ai.trueid.lottie.model.layer.BaseLayer v2_4 = this.colorFilterAnimation;
                    if (v2_4 != null) {
                        this.layer.removeAnimation(v2_4);
                    }
                    if (p3 != null) {
                        vn.ai.trueid.lottie.model.layer.BaseLayer v2_6 = new vn.ai.trueid.lottie.animation.keyframe.ValueCallbackKeyframeAnimation(p3);
                        this.colorFilterAnimation = v2_6;
                        v2_6.addUpdateListener(this);
                        this.layer.addAnimation(this.colorFilterAnimation);
                        return;
                    } else {
                        this.colorFilterAnimation = 0;
                        return;
                    }
                }
            } else {
                this.widthAnimation.setValueCallback(p3);
                return;
            }
        } else {
            this.opacityAnimation.setValueCallback(p3);
            return;
        }
    }

    public void draw(android.graphics.Canvas p6, android.graphics.Matrix p7, int p8, vn.ai.trueid.lottie.utils.DropShadow p9)
    {
        if (vn.ai.trueid.lottie.L.isTraceEnabled()) {
            vn.ai.trueid.lottie.L.beginSection("StrokeContent#draw");
        }
        if (!vn.ai.trueid.lottie.utils.Utils.hasZeroScaleAxis(p7)) {
            android.graphics.Path v0_6 = (((float) ((Integer) this.opacityAnimation.getValue()).intValue()) / 1120403456);
            int v3 = 0;
            this.paint.setAlpha(vn.ai.trueid.lottie.utils.MiscUtils.clamp(((int) (((float) p8) * v0_6)), 0, 255));
            this.paint.setStrokeWidth(((vn.ai.trueid.lottie.animation.keyframe.FloatKeyframeAnimation) this.widthAnimation).getFloatValue());
            if (this.paint.getStrokeWidth() > 0) {
                this.applyDashPatternIfNeeded();
                String v8_12 = this.colorFilterAnimation;
                if (v8_12 != null) {
                    this.paint.setColorFilter(((android.graphics.ColorFilter) v8_12.getValue()));
                }
                String v8_15 = this.blurAnimation;
                if (v8_15 != null) {
                    String v8_18 = ((Float) v8_15.getValue()).floatValue();
                    if (v8_18 != 0) {
                        if (v8_18 != this.blurMaskFilterRadius) {
                            this.paint.setMaskFilter(this.layer.getBlurMaskFilter(v8_18));
                        }
                    } else {
                        this.paint.setMaskFilter(0);
                    }
                    this.blurMaskFilterRadius = v8_18;
                }
                if (p9 != null) {
                    p9.applyWithAlpha(((int) (v0_6 * 1132396544)), this.paint);
                }
                p6.save();
                p6.concat(p7);
                while (v3 < this.pathGroups.size()) {
                    boolean v7_8 = ((vn.ai.trueid.lottie.animation.content.BaseStrokeContent$PathGroup) this.pathGroups.get(v3));
                    if (vn.ai.trueid.lottie.animation.content.BaseStrokeContent$PathGroup.access$200(v7_8) == null) {
                        if (vn.ai.trueid.lottie.L.isTraceEnabled()) {
                            vn.ai.trueid.lottie.L.beginSection("StrokeContent#buildPath");
                        }
                        this.path.reset();
                        String v8_2 = (vn.ai.trueid.lottie.animation.content.BaseStrokeContent$PathGroup.access$100(v7_8).size() - 1);
                        while (v8_2 >= null) {
                            this.path.addPath(((vn.ai.trueid.lottie.animation.content.PathContent) vn.ai.trueid.lottie.animation.content.BaseStrokeContent$PathGroup.access$100(v7_8).get(v8_2)).getPath());
                            v8_2--;
                        }
                        if (vn.ai.trueid.lottie.L.isTraceEnabled()) {
                            vn.ai.trueid.lottie.L.endSection("StrokeContent#buildPath");
                            vn.ai.trueid.lottie.L.beginSection("StrokeContent#drawPath");
                        }
                        p6.drawPath(this.path, this.paint);
                        if (vn.ai.trueid.lottie.L.isTraceEnabled()) {
                            vn.ai.trueid.lottie.L.endSection("StrokeContent#drawPath");
                        }
                    } else {
                        this.applyTrimPath(p6, v7_8);
                    }
                    v3++;
                }
                p6.restore();
                if (vn.ai.trueid.lottie.L.isTraceEnabled()) {
                    vn.ai.trueid.lottie.L.endSection("StrokeContent#draw");
                }
            } else {
                if (vn.ai.trueid.lottie.L.isTraceEnabled()) {
                    vn.ai.trueid.lottie.L.endSection("StrokeContent#draw");
                    return;
                }
            }
        } else {
            if (vn.ai.trueid.lottie.L.isTraceEnabled()) {
                vn.ai.trueid.lottie.L.endSection("StrokeContent#draw");
                return;
            }
        }
        return;
    }

    public void getBounds(android.graphics.RectF p7, android.graphics.Matrix p8, boolean p9)
    {
        if (vn.ai.trueid.lottie.L.isTraceEnabled()) {
            vn.ai.trueid.lottie.L.beginSection("StrokeContent#getBounds");
        }
        this.path.reset();
        int v1_5 = 0;
        while (v1_5 < this.pathGroups.size()) {
            float v2_8 = ((vn.ai.trueid.lottie.animation.content.BaseStrokeContent$PathGroup) this.pathGroups.get(v1_5));
            int v3_4 = 0;
            while (v3_4 < vn.ai.trueid.lottie.animation.content.BaseStrokeContent$PathGroup.access$100(v2_8).size()) {
                this.path.addPath(((vn.ai.trueid.lottie.animation.content.PathContent) vn.ai.trueid.lottie.animation.content.BaseStrokeContent$PathGroup.access$100(v2_8).get(v3_4)).getPath(), p8);
                v3_4++;
            }
            v1_5++;
        }
        this.path.computeBounds(this.rect, 0);
        int v9_1 = this.rect;
        int v1_1 = v9_1.left;
        float v8_5 = (((vn.ai.trueid.lottie.animation.keyframe.FloatKeyframeAnimation) this.widthAnimation).getFloatValue() / 1073741824);
        v9_1.set((v1_1 - v8_5), (v9_1.top - v8_5), (v9_1.right + v8_5), (v9_1.bottom + v8_5));
        p7.set(this.rect);
        p7.set((p7.left - 1065353216), (p7.top - 1065353216), (p7.right + 1065353216), (p7.bottom + 1065353216));
        if (vn.ai.trueid.lottie.L.isTraceEnabled()) {
            vn.ai.trueid.lottie.L.endSection("StrokeContent#getBounds");
        }
        return;
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

    public void setContents(java.util.List p8, java.util.List p9)
    {
        vn.ai.trueid.lottie.animation.content.BaseStrokeContent$PathGroup v0_2 = (p8.size() - 1);
        vn.ai.trueid.lottie.animation.content.TrimPathContent v2 = 0;
        while (v0_2 >= null) {
            vn.ai.trueid.lottie.animation.content.PathContent v3_5 = ((vn.ai.trueid.lottie.animation.content.Content) p8.get(v0_2));
            if ((v3_5 instanceof vn.ai.trueid.lottie.animation.content.TrimPathContent)) {
                vn.ai.trueid.lottie.animation.content.PathContent v3_6 = ((vn.ai.trueid.lottie.animation.content.TrimPathContent) v3_5);
                if (v3_6.getType() == vn.ai.trueid.lottie.model.content.ShapeTrimPath$Type.INDIVIDUALLY) {
                    v2 = v3_6;
                }
            }
            v0_2--;
        }
        if (v2 != null) {
            v2.addListener(this);
        }
        int v8_1 = (p9.size() - 1);
        vn.ai.trueid.lottie.animation.content.BaseStrokeContent$PathGroup v0_1 = 0;
        while (v8_1 >= 0) {
            vn.ai.trueid.lottie.animation.content.PathContent v3_1 = ((vn.ai.trueid.lottie.animation.content.Content) p9.get(v8_1));
            if (!(v3_1 instanceof vn.ai.trueid.lottie.animation.content.TrimPathContent)) {
                if ((v3_1 instanceof vn.ai.trueid.lottie.animation.content.PathContent)) {
                    if (v0_1 == null) {
                        v0_1 = new vn.ai.trueid.lottie.animation.content.BaseStrokeContent$PathGroup(v2, 0);
                    }
                    vn.ai.trueid.lottie.animation.content.BaseStrokeContent$PathGroup.access$100(v0_1).add(((vn.ai.trueid.lottie.animation.content.PathContent) v3_1));
                }
            } else {
                java.util.List v4_2 = ((vn.ai.trueid.lottie.animation.content.TrimPathContent) v3_1);
                if (v4_2.getType() != vn.ai.trueid.lottie.model.content.ShapeTrimPath$Type.INDIVIDUALLY) {
                } else {
                    if (v0_1 != null) {
                        this.pathGroups.add(v0_1);
                    }
                    v0_1 = new vn.ai.trueid.lottie.animation.content.BaseStrokeContent$PathGroup(v4_2, 0);
                    v4_2.addListener(this);
                }
            }
            v8_1--;
        }
        if (v0_1 != null) {
            this.pathGroups.add(v0_1);
        }
        return;
    }
}
