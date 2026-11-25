package vn.ai.trueid.lottie.model.layer;
public class CompositionLayer extends vn.ai.trueid.lottie.model.layer.BaseLayer {
    private boolean clipToCompositionBounds;
    private vn.ai.trueid.lottie.animation.keyframe.DropShadowKeyframeAnimation dropShadowAnimation;
    private Boolean hasMasks;
    private Boolean hasMatte;
    private final android.graphics.RectF layerBounds;
    private final java.util.List layers;
    private final android.graphics.RectF newClipRect;
    private final vn.ai.trueid.lottie.utils.OffscreenLayer offscreenLayer;
    private final vn.ai.trueid.lottie.utils.OffscreenLayer$ComposeOp offscreenOp;
    private float progress;
    private final android.graphics.RectF rect;
    private vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation timeRemapping;

    public CompositionLayer(vn.ai.trueid.lottie.LottieDrawable p10, vn.ai.trueid.lottie.model.layer.Layer p11, java.util.List p12, vn.ai.trueid.lottie.LottieComposition p13)
    {
        super(p10, p11);
        super.layers = new java.util.ArrayList();
        super.rect = new android.graphics.RectF();
        super.newClipRect = new android.graphics.RectF();
        super.layerBounds = new android.graphics.RectF();
        super.offscreenLayer = new vn.ai.trueid.lottie.utils.OffscreenLayer();
        super.offscreenOp = new vn.ai.trueid.lottie.utils.OffscreenLayer$ComposeOp();
        super.clipToCompositionBounds = 1;
        vn.ai.trueid.lottie.parser.DropShadowEffect v11_1 = p11.getTimeRemapping();
        if (v11_1 == null) {
            super.timeRemapping = 0;
        } else {
            vn.ai.trueid.lottie.parser.DropShadowEffect v11_2 = v11_1.createAnimation();
            super.timeRemapping = v11_2;
            super.addAnimation(v11_2);
            super.timeRemapping.addUpdateListener(super);
        }
        vn.ai.trueid.lottie.parser.DropShadowEffect v11_5 = new P.o(p13.getLayers().size());
        int v2_3 = (p12.size() - 1);
        vn.ai.trueid.lottie.model.layer.BaseLayer v3 = 0;
        while(true) {
            int v4_0 = 0;
            if (v2_3 < 0) {
                break;
            }
            int v5_1 = ((vn.ai.trueid.lottie.model.layer.Layer) p12.get(v2_3));
            vn.ai.trueid.lottie.model.layer.BaseLayer v6 = vn.ai.trueid.lottie.model.layer.BaseLayer.forModel(super, v5_1, p10, p13);
            if (v6 != null) {
                v11_5.i(v6.getLayerModel().getId(), v6);
                if (v3 == null) {
                    super.layers.add(0, v6);
                    int v4_2 = vn.ai.trueid.lottie.model.layer.CompositionLayer$1.$SwitchMap$com$airbnb$lottie$model$layer$Layer$MatteType[v5_1.getMatteType().ordinal()];
                    if ((v4_2 == 1) || (v4_2 == 2)) {
                        v3 = v6;
                    }
                } else {
                    v3.setMatteLayer(v6);
                    v3 = 0;
                }
            }
            v2_3--;
        }
        while (v4_0 < v11_5.k()) {
            vn.ai.trueid.lottie.animation.keyframe.DropShadowKeyframeAnimation v10_6 = ((vn.ai.trueid.lottie.model.layer.BaseLayer) v11_5.b(v11_5.g(v4_0)));
            if (v10_6 != null) {
                vn.ai.trueid.lottie.model.layer.BaseLayer v12_5 = ((vn.ai.trueid.lottie.model.layer.BaseLayer) v11_5.b(v10_6.getLayerModel().getParentId()));
                if (v12_5 != null) {
                    v10_6.setParentLayer(v12_5);
                }
            }
            v4_0++;
        }
        if (super.getDropShadowEffect() != null) {
            super.dropShadowAnimation = new vn.ai.trueid.lottie.animation.keyframe.DropShadowKeyframeAnimation(super, super, super.getDropShadowEffect());
        }
        return;
    }

    public void addValueCallback(Object p2, vn.ai.trueid.lottie.value.LottieValueCallback p3)
    {
        super.addValueCallback(p2, p3);
        if (p2 != vn.ai.trueid.lottie.LottieProperty.TIME_REMAP) {
            if (p2 == vn.ai.trueid.lottie.LottieProperty.DROP_SHADOW_COLOR) {
                Float v0_7 = this.dropShadowAnimation;
                if (v0_7 != null) {
                    v0_7.setColorCallback(p3);
                    return;
                }
            }
            if (p2 == vn.ai.trueid.lottie.LottieProperty.DROP_SHADOW_OPACITY) {
                Float v0_9 = this.dropShadowAnimation;
                if (v0_9 != null) {
                    v0_9.setOpacityCallback(p3);
                    return;
                }
            }
            if (p2 == vn.ai.trueid.lottie.LottieProperty.DROP_SHADOW_DIRECTION) {
                Float v0_2 = this.dropShadowAnimation;
                if (v0_2 != null) {
                    v0_2.setDirectionCallback(p3);
                    return;
                }
            }
            if (p2 == vn.ai.trueid.lottie.LottieProperty.DROP_SHADOW_DISTANCE) {
                Float v0_4 = this.dropShadowAnimation;
                if (v0_4 != null) {
                    v0_4.setDistanceCallback(p3);
                    return;
                }
            }
            if (p2 == vn.ai.trueid.lottie.LottieProperty.DROP_SHADOW_RADIUS) {
                vn.ai.trueid.lottie.animation.keyframe.DropShadowKeyframeAnimation v2_1 = this.dropShadowAnimation;
                if (v2_1 != null) {
                    v2_1.setRadiusCallback(p3);
                }
            }
        } else {
            if (p3 != 0) {
                vn.ai.trueid.lottie.animation.keyframe.DropShadowKeyframeAnimation v2_3 = new vn.ai.trueid.lottie.animation.keyframe.ValueCallbackKeyframeAnimation(p3);
                this.timeRemapping = v2_3;
                v2_3.addUpdateListener(this);
                this.addAnimation(this.timeRemapping);
                return;
            } else {
                vn.ai.trueid.lottie.animation.keyframe.DropShadowKeyframeAnimation v2_5 = this.timeRemapping;
                if (v2_5 != null) {
                    v2_5.setValueCallback(0);
                    return;
                }
            }
        }
        return;
    }

    public void drawLayer(android.graphics.Canvas p9, android.graphics.Matrix p10, int p11, vn.ai.trueid.lottie.utils.DropShadow p12)
    {
        if (vn.ai.trueid.lottie.L.isTraceEnabled()) {
            vn.ai.trueid.lottie.L.beginSection("CompositionLayer#draw");
        }
        int v3_0;
        int v0_1 = 0;
        if ((p12 == 0) && (this.dropShadowAnimation == null)) {
            v3_0 = 0;
        } else {
            v3_0 = 1;
        }
        int v5 = 255;
        if (((this.lottieDrawable.isApplyingOpacityToLayersEnabled()) && ((this.layers.size() > 1) && (p11 != 255))) || ((v3_0 != 0) && (this.lottieDrawable.isApplyingShadowToLayersEnabled()))) {
            v0_1 = 1;
        }
        if (v0_1 == 0) {
            v5 = p11;
        }
        int v3_4 = this.dropShadowAnimation;
        if (v3_4 != 0) {
            p12 = v3_4.evaluate(p10, v5);
        }
        if ((this.clipToCompositionBounds) || (!"__container".equals(this.layerModel.getName()))) {
            this.newClipRect.set(0, 0, this.layerModel.getPreCompWidth(), this.layerModel.getPreCompHeight());
            p10.mapRect(this.newClipRect);
        } else {
            this.newClipRect.setEmpty();
            int v3_13 = this.layers.iterator();
            while (v3_13.hasNext()) {
                ((vn.ai.trueid.lottie.model.layer.BaseLayer) v3_13.next()).getBounds(this.layerBounds, p10, 1);
                this.newClipRect.union(this.layerBounds);
            }
        }
        android.graphics.Canvas v11_1;
        if (v0_1 == 0) {
            v11_1 = p9;
        } else {
            this.offscreenOp.reset();
            int v3_15 = this.offscreenOp;
            v3_15.alpha = p11;
            if (p12 != 0) {
                p12.applyTo(v3_15);
                p12 = 0;
            }
            v11_1 = this.offscreenLayer.start(p9, this.newClipRect, this.offscreenOp);
        }
        p9.save();
        if (p9.clipRect(this.newClipRect)) {
            int v3_1 = (this.layers.size() - 1);
            while (v3_1 >= 0) {
                ((vn.ai.trueid.lottie.model.layer.BaseLayer) this.layers.get(v3_1)).draw(v11_1, p10, v5, p12);
                v3_1--;
            }
        }
        if (v0_1 != 0) {
            this.offscreenLayer.finish();
        }
        p9.restore();
        if (vn.ai.trueid.lottie.L.isTraceEnabled()) {
            vn.ai.trueid.lottie.L.endSection("CompositionLayer#draw");
        }
        return;
    }

    public void getBounds(android.graphics.RectF p4, android.graphics.Matrix p5, boolean p6)
    {
        super.getBounds(p4, p5, p6);
        int v5_2 = (this.layers.size() - 1);
        while (v5_2 >= 0) {
            this.rect.set(0, 0, 0, 0);
            ((vn.ai.trueid.lottie.model.layer.BaseLayer) this.layers.get(v5_2)).getBounds(this.rect, this.boundsMatrix, 1);
            p4.union(this.rect);
            v5_2--;
        }
        return;
    }

    public float getProgress()
    {
        return this.progress;
    }

    public boolean hasMasks()
    {
        if (this.hasMasks == null) {
            Boolean v0_5 = (this.layers.size() - 1);
            while (v0_5 >= null) {
                boolean v2_2 = ((vn.ai.trueid.lottie.model.layer.BaseLayer) this.layers.get(v0_5));
                if (!(v2_2 instanceof vn.ai.trueid.lottie.model.layer.ShapeLayer)) {
                    if (((v2_2 instanceof vn.ai.trueid.lottie.model.layer.CompositionLayer)) && (((vn.ai.trueid.lottie.model.layer.CompositionLayer) v2_2).hasMasks())) {
                        this.hasMasks = Boolean.TRUE;
                        return 1;
                    }
                } else {
                    if (v2_2.hasMasksOnThisLayer()) {
                        this.hasMasks = Boolean.TRUE;
                        return 1;
                    }
                }
                v0_5--;
            }
            this.hasMasks = Boolean.FALSE;
        }
        return this.hasMasks.booleanValue();
    }

    public boolean hasMatte()
    {
        if (this.hasMatte == null) {
            if (!this.hasMatteOnThisLayer()) {
                int v0_4 = (this.layers.size() - 1);
                while (v0_4 >= 0) {
                    if (!((vn.ai.trueid.lottie.model.layer.BaseLayer) this.layers.get(v0_4)).hasMatteOnThisLayer()) {
                        v0_4--;
                    } else {
                        this.hasMatte = Boolean.TRUE;
                        return 1;
                    }
                }
                this.hasMatte = Boolean.FALSE;
            } else {
                this.hasMatte = Boolean.TRUE;
                return 1;
            }
        }
        return this.hasMatte.booleanValue();
    }

    public void resolveChildKeyPath(vn.ai.trueid.lottie.model.KeyPath p3, int p4, java.util.List p5, vn.ai.trueid.lottie.model.KeyPath p6)
    {
        int v0 = 0;
        while (v0 < this.layers.size()) {
            ((vn.ai.trueid.lottie.model.layer.BaseLayer) this.layers.get(v0)).resolveKeyPath(p3, p4, p5, p6);
            v0++;
        }
        return;
    }

    public void setClipToCompositionBounds(boolean p1)
    {
        this.clipToCompositionBounds = p1;
        return;
    }

    public void setOutlineMasksAndMattes(boolean p3)
    {
        super.setOutlineMasksAndMattes(p3);
        java.util.Iterator v0_1 = this.layers.iterator();
        while (v0_1.hasNext()) {
            ((vn.ai.trueid.lottie.model.layer.BaseLayer) v0_1.next()).setOutlineMasksAndMattes(p3);
        }
        return;
    }

    public void setProgress(float p5)
    {
        if (vn.ai.trueid.lottie.L.isTraceEnabled()) {
            vn.ai.trueid.lottie.L.beginSection("CompositionLayer#setProgress");
        }
        this.progress = p5;
        super.setProgress(p5);
        if (this.timeRemapping != null) {
            p5 = (((this.layerModel.getComposition().getFrameRate() * ((Float) this.timeRemapping.getValue()).floatValue()) - this.layerModel.getComposition().getStartFrame()) / (this.lottieDrawable.getComposition().getDurationFrames() + 1008981770));
        }
        if (this.timeRemapping == null) {
            p5 -= this.layerModel.getStartProgress();
        }
        if ((this.layerModel.getTimeStretch() != 0) && (!"__container".equals(this.layerModel.getName()))) {
            p5 /= this.layerModel.getTimeStretch();
        }
        int v0_18 = (this.layers.size() - 1);
        while (v0_18 >= 0) {
            ((vn.ai.trueid.lottie.model.layer.BaseLayer) this.layers.get(v0_18)).setProgress(p5);
            v0_18--;
        }
        if (vn.ai.trueid.lottie.L.isTraceEnabled()) {
            vn.ai.trueid.lottie.L.endSection("CompositionLayer#setProgress");
        }
        return;
    }
}
