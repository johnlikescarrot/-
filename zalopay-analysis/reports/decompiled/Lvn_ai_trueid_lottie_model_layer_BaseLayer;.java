package vn.ai.trueid.lottie.model.layer;
public abstract class BaseLayer implements vn.ai.trueid.lottie.animation.content.DrawingContent, vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation$AnimationListener, vn.ai.trueid.lottie.model.KeyPathElement {
    private static final int CLIP_SAVE_FLAG = 2;
    private static final int CLIP_TO_LAYER_SAVE_FLAG = 16;
    private static final int MATRIX_SAVE_FLAG = 0;
    private static final int SAVE_FLAGS = 19;
    private final java.util.List animations;
    android.graphics.BlurMaskFilter blurMaskFilter;
    float blurMaskFilterRadius;
    protected final android.graphics.Matrix boundsMatrix;
    private final android.graphics.RectF canvasBounds;
    private final android.graphics.Matrix canvasMatrix;
    private final android.graphics.Paint clearPaint;
    private final android.graphics.Paint contentPaint;
    private final String drawTraceName;
    private final android.graphics.Paint dstInPaint;
    private final android.graphics.Paint dstOutPaint;
    private vn.ai.trueid.lottie.animation.keyframe.FloatKeyframeAnimation inOutAnimation;
    final vn.ai.trueid.lottie.model.layer.Layer layerModel;
    final vn.ai.trueid.lottie.LottieDrawable lottieDrawable;
    private vn.ai.trueid.lottie.animation.keyframe.MaskKeyframeAnimation mask;
    private final android.graphics.RectF maskBoundsRect;
    private final android.graphics.Matrix matrix;
    private final android.graphics.RectF matteBoundsRect;
    private vn.ai.trueid.lottie.model.layer.BaseLayer matteLayer;
    private final android.graphics.Paint mattePaint;
    private boolean outlineMasksAndMattes;
    private android.graphics.Paint outlineMasksAndMattesPaint;
    private vn.ai.trueid.lottie.model.layer.BaseLayer parentLayer;
    private java.util.List parentLayers;
    private final android.graphics.Path path;
    private final android.graphics.RectF rect;
    vn.ai.trueid.lottie.animation.LPaint solidWhitePaint;
    private final android.graphics.RectF tempMaskBoundsRect;
    public final vn.ai.trueid.lottie.animation.keyframe.TransformKeyframeAnimation transform;
    private boolean visible;

    static BaseLayer()
    {
        wmoi.igob.jtgo(vn.ai.trueid.lottie.model.layer.BaseLayer, 18234, 18234);
        return;
    }

    public BaseLayer(vn.ai.trueid.lottie.LottieDrawable p7, vn.ai.trueid.lottie.model.layer.Layer p8)
    {
        this.path = new android.graphics.Path();
        this.matrix = new android.graphics.Matrix();
        this.canvasMatrix = new android.graphics.Matrix();
        this.contentPaint = new vn.ai.trueid.lottie.animation.LPaint(1);
        android.graphics.PorterDuff$Mode v2 = android.graphics.PorterDuff$Mode.DST_IN;
        this.dstInPaint = new vn.ai.trueid.lottie.animation.LPaint(1, v2);
        android.graphics.PorterDuff$Mode v3 = android.graphics.PorterDuff$Mode.DST_OUT;
        this.dstOutPaint = new vn.ai.trueid.lottie.animation.LPaint(1, v3);
        vn.ai.trueid.lottie.animation.LPaint v0_9 = new vn.ai.trueid.lottie.animation.LPaint(1);
        this.mattePaint = v0_9;
        this.clearPaint = new vn.ai.trueid.lottie.animation.LPaint(android.graphics.PorterDuff$Mode.CLEAR);
        this.rect = new android.graphics.RectF();
        this.canvasBounds = new android.graphics.RectF();
        this.maskBoundsRect = new android.graphics.RectF();
        this.matteBoundsRect = new android.graphics.RectF();
        this.tempMaskBoundsRect = new android.graphics.RectF();
        this.boundsMatrix = new android.graphics.Matrix();
        this.animations = new java.util.ArrayList();
        this.visible = 1;
        this.blurMaskFilterRadius = 0;
        this.lottieDrawable = p7;
        this.layerModel = p8;
        java.util.Iterator v7_5 = new StringBuilder();
        v7_5.append(p8.getName());
        v7_5.append("#draw");
        this.drawTraceName = v7_5.toString();
        if (p8.getMatteType() != vn.ai.trueid.lottie.model.layer.Layer$MatteType.INVERT) {
            v0_9.setXfermode(new android.graphics.PorterDuffXfermode(v2));
        } else {
            v0_9.setXfermode(new android.graphics.PorterDuffXfermode(v3));
        }
        java.util.Iterator v7_13 = p8.getTransform().createAnimation();
        this.transform = v7_13;
        v7_13.addListener(this);
        if ((p8.getMasks() != null) && (!p8.getMasks().isEmpty())) {
            java.util.Iterator v7_18 = new vn.ai.trueid.lottie.animation.keyframe.MaskKeyframeAnimation(p8.getMasks());
            this.mask = v7_18;
            java.util.Iterator v7_20 = v7_18.getMaskAnimations().iterator();
            while (v7_20.hasNext()) {
                ((vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation) v7_20.next()).addUpdateListener(this);
            }
            java.util.Iterator v7_3 = this.mask.getOpacityAnimations().iterator();
            while (v7_3.hasNext()) {
                vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation v8_3 = ((vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation) v7_3.next());
                this.addAnimation(v8_3);
                v8_3.addUpdateListener(this);
            }
        }
        this.setupInOutAnimations();
        return;
    }

    public static synthetic void a(vn.ai.trueid.lottie.model.layer.BaseLayer p0)
    {
        p0.lambda$setupInOutAnimations$0();
        return;
    }

    private void applyAddMask(android.graphics.Canvas p2, android.graphics.Matrix p3, vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation p4, vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation p5)
    {
        this.path.set(((android.graphics.Path) p4.getValue()));
        this.path.transform(p3);
        this.contentPaint.setAlpha(((int) (((float) ((Integer) p5.getValue()).intValue()) * 1076048691)));
        p2.drawPath(this.path, this.contentPaint);
        return;
    }

    private void applyIntersectMask(android.graphics.Canvas p3, android.graphics.Matrix p4, vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation p5, vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation p6)
    {
        vn.ai.trueid.lottie.utils.Utils.saveLayerCompat(p3, this.rect, this.dstInPaint);
        this.path.set(((android.graphics.Path) p5.getValue()));
        this.path.transform(p4);
        this.contentPaint.setAlpha(((int) (((float) ((Integer) p6.getValue()).intValue()) * 1076048691)));
        p3.drawPath(this.path, this.contentPaint);
        p3.restore();
        return;
    }

    private void applyInvertedAddMask(android.graphics.Canvas p3, android.graphics.Matrix p4, vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation p5, vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation p6)
    {
        vn.ai.trueid.lottie.utils.Utils.saveLayerCompat(p3, this.rect, this.contentPaint);
        p3.drawRect(this.rect, this.contentPaint);
        this.path.set(((android.graphics.Path) p5.getValue()));
        this.path.transform(p4);
        this.contentPaint.setAlpha(((int) (((float) ((Integer) p6.getValue()).intValue()) * 1076048691)));
        p3.drawPath(this.path, this.dstOutPaint);
        p3.restore();
        return;
    }

    private void applyInvertedIntersectMask(android.graphics.Canvas p3, android.graphics.Matrix p4, vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation p5, vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation p6)
    {
        vn.ai.trueid.lottie.utils.Utils.saveLayerCompat(p3, this.rect, this.dstInPaint);
        p3.drawRect(this.rect, this.contentPaint);
        this.dstOutPaint.setAlpha(((int) (((float) ((Integer) p6.getValue()).intValue()) * 1076048691)));
        this.path.set(((android.graphics.Path) p5.getValue()));
        this.path.transform(p4);
        p3.drawPath(this.path, this.dstOutPaint);
        p3.restore();
        return;
    }

    private void applyInvertedSubtractMask(android.graphics.Canvas p3, android.graphics.Matrix p4, vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation p5, vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation p6)
    {
        vn.ai.trueid.lottie.utils.Utils.saveLayerCompat(p3, this.rect, this.dstOutPaint);
        p3.drawRect(this.rect, this.contentPaint);
        this.dstOutPaint.setAlpha(((int) (((float) ((Integer) p6.getValue()).intValue()) * 1076048691)));
        this.path.set(((android.graphics.Path) p5.getValue()));
        this.path.transform(p4);
        p3.drawPath(this.path, this.dstOutPaint);
        p3.restore();
        return;
    }

    private void applyMasks(android.graphics.Canvas p8, android.graphics.Matrix p9)
    {
        if (vn.ai.trueid.lottie.L.isTraceEnabled()) {
            vn.ai.trueid.lottie.L.beginSection("Layer#saveLayer");
        }
        vn.ai.trueid.lottie.utils.Utils.saveLayerCompat(p8, this.rect, this.dstInPaint, 19);
        if (android.os.Build$VERSION.SDK_INT < 28) {
            this.clearCanvas(p8);
        }
        if (vn.ai.trueid.lottie.L.isTraceEnabled()) {
            vn.ai.trueid.lottie.L.endSection("Layer#saveLayer");
        }
        int v0_1 = 0;
        while (v0_1 < this.mask.getMasks().size()) {
            boolean v1_7 = ((vn.ai.trueid.lottie.model.content.Mask) this.mask.getMasks().get(v0_1));
            android.graphics.Paint v2_4 = ((vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation) this.mask.getMaskAnimations().get(v0_1));
            vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation v3_3 = ((vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation) this.mask.getOpacityAnimations().get(v0_1));
            android.graphics.RectF v4_1 = vn.ai.trueid.lottie.model.layer.BaseLayer$1.$SwitchMap$com$airbnb$lottie$model$content$Mask$MaskMode[v1_7.getMaskMode().ordinal()];
            if (v4_1 == 1) {
                if (this.areAllMasksNone()) {
                    this.contentPaint.setAlpha(255);
                    p8.drawRect(this.rect, this.contentPaint);
                }
            } else {
                if (v4_1 == 2) {
                    if (v0_1 == 0) {
                        this.contentPaint.setColor(-16777216);
                        this.contentPaint.setAlpha(255);
                        p8.drawRect(this.rect, this.contentPaint);
                    }
                    if (!v1_7.isInverted()) {
                        this.applySubtractMask(p8, p9, v2_4);
                    } else {
                        this.applyInvertedSubtractMask(p8, p9, v2_4, v3_3);
                    }
                } else {
                    if (v4_1 == 3) {
                        if (!v1_7.isInverted()) {
                            this.applyIntersectMask(p8, p9, v2_4, v3_3);
                        } else {
                            this.applyInvertedIntersectMask(p8, p9, v2_4, v3_3);
                        }
                    } else {
                        if (v4_1 == 4) {
                            if (!v1_7.isInverted()) {
                                this.applyAddMask(p8, p9, v2_4, v3_3);
                            } else {
                                this.applyInvertedAddMask(p8, p9, v2_4, v3_3);
                            }
                        }
                    }
                }
            }
            v0_1++;
        }
        if (vn.ai.trueid.lottie.L.isTraceEnabled()) {
            vn.ai.trueid.lottie.L.beginSection("Layer#restoreLayer");
        }
        p8.restore();
        if (vn.ai.trueid.lottie.L.isTraceEnabled()) {
            vn.ai.trueid.lottie.L.endSection("Layer#restoreLayer");
        }
        return;
    }

    private void applySubtractMask(android.graphics.Canvas p2, android.graphics.Matrix p3, vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation p4)
    {
        this.path.set(((android.graphics.Path) p4.getValue()));
        this.path.transform(p3);
        p2.drawPath(this.path, this.dstOutPaint);
        return;
    }

    private boolean areAllMasksNone()
    {
        if (!this.mask.getMaskAnimations().isEmpty()) {
            int v0_3 = 0;
            while (v0_3 < this.mask.getMasks().size()) {
                if (((vn.ai.trueid.lottie.model.content.Mask) this.mask.getMasks().get(v0_3)).getMaskMode() == vn.ai.trueid.lottie.model.content.Mask$MaskMode.MASK_MODE_NONE) {
                    v0_3++;
                } else {
                    return 0;
                }
            }
            return 1;
        } else {
            return 0;
        }
    }

    private void buildParentLayerListIfNeeded()
    {
        if (this.parentLayers == null) {
            if (this.parentLayer != null) {
                this.parentLayers = new java.util.ArrayList();
                vn.ai.trueid.lottie.model.layer.BaseLayer v0_1 = this.parentLayer;
                while (v0_1 != null) {
                    this.parentLayers.add(v0_1);
                    v0_1 = v0_1.parentLayer;
                }
            } else {
                this.parentLayers = java.util.Collections.EMPTY_LIST;
                return;
            }
        }
        return;
    }

    private void clearCanvas(android.graphics.Canvas p11)
    {
        if (vn.ai.trueid.lottie.L.isTraceEnabled()) {
            vn.ai.trueid.lottie.L.beginSection("Layer#clearLayer");
        }
        float v0_2 = this.rect;
        p11.drawRect((v0_2.left - 1065353216), (v0_2.top - 1065353216), (v0_2.right + 1065353216), (v0_2.bottom + 1065353216), this.clearPaint);
        if (vn.ai.trueid.lottie.L.isTraceEnabled()) {
            vn.ai.trueid.lottie.L.endSection("Layer#clearLayer");
        }
        return;
    }

    public static vn.ai.trueid.lottie.model.layer.BaseLayer forModel(vn.ai.trueid.lottie.model.layer.CompositionLayer p2, vn.ai.trueid.lottie.model.layer.Layer p3, vn.ai.trueid.lottie.LottieDrawable p4, vn.ai.trueid.lottie.LottieComposition p5)
    {
        switch (vn.ai.trueid.lottie.model.layer.BaseLayer$1.$SwitchMap$com$airbnb$lottie$model$layer$Layer$LayerType[p3.getLayerType().ordinal()]) {
            case 1:
                return new vn.ai.trueid.lottie.model.layer.ShapeLayer(p4, p3, p2, p5);
            case 2:
                return new vn.ai.trueid.lottie.model.layer.CompositionLayer(p4, p3, p5.getPrecomps(p3.getRefId()), p5);
            case 3:
                return new vn.ai.trueid.lottie.model.layer.SolidLayer(p4, p3);
            case 4:
                return new vn.ai.trueid.lottie.model.layer.ImageLayer(p4, p3);
            case 5:
                return new vn.ai.trueid.lottie.model.layer.NullLayer(p4, p3);
            case 6:
                return new vn.ai.trueid.lottie.model.layer.TextLayer(p4, p3);
            default:
                vn.ai.trueid.lottie.model.layer.TextLayer v2_10 = new StringBuilder("Unknown layer type ");
                v2_10.append(p3.getLayerType());
                vn.ai.trueid.lottie.utils.Logger.warning(v2_10.toString());
                return 0;
        }
    }

    private void intersectBoundsWithMask(android.graphics.RectF p11, android.graphics.Matrix p12)
    {
        this.maskBoundsRect.set(0, 0, 0, 0);
        if (this.hasMasksOnThisLayer()) {
            int v0_1 = this.mask.getMasks().size();
            int v3 = 0;
            while (v3 < v0_1) {
                android.graphics.RectF v4_3 = ((vn.ai.trueid.lottie.model.content.Mask) this.mask.getMasks().get(v3));
                float v5_5 = ((android.graphics.Path) ((vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation) this.mask.getMaskAnimations().get(v3)).getValue());
                if (v5_5 != 0) {
                    this.path.set(v5_5);
                    this.path.transform(p12);
                    float v5_8 = vn.ai.trueid.lottie.model.layer.BaseLayer$1.$SwitchMap$com$airbnb$lottie$model$content$Mask$MaskMode[v4_3.getMaskMode().ordinal()];
                    if (((v5_8 != 1) && (v5_8 != 2)) && (((v5_8 != 3) && (v5_8 != 4)) || (!v4_3.isInverted()))) {
                        this.path.computeBounds(this.tempMaskBoundsRect, 0);
                        if (v3 != 0) {
                            android.graphics.RectF v4_6 = this.maskBoundsRect;
                            v4_6.set(Math.min(v4_6.left, this.tempMaskBoundsRect.left), Math.min(this.maskBoundsRect.top, this.tempMaskBoundsRect.top), Math.max(this.maskBoundsRect.right, this.tempMaskBoundsRect.right), Math.max(this.maskBoundsRect.bottom, this.tempMaskBoundsRect.bottom));
                        } else {
                            this.maskBoundsRect.set(this.tempMaskBoundsRect);
                        }
                    } else {
                        return;
                    }
                }
                v3++;
            }
            if (!p11.intersect(this.maskBoundsRect)) {
                p11.set(0, 0, 0, 0);
            }
        }
        return;
    }

    private void intersectBoundsWithMatte(android.graphics.RectF p5, android.graphics.Matrix p6)
    {
        if ((this.hasMatteOnThisLayer()) && (this.layerModel.getMatteType() != vn.ai.trueid.lottie.model.layer.Layer$MatteType.INVERT)) {
            this.matteBoundsRect.set(0, 0, 0, 0);
            this.matteLayer.getBounds(this.matteBoundsRect, p6, 1);
            if (!p5.intersect(this.matteBoundsRect)) {
                p5.set(0, 0, 0, 0);
            }
        }
        return;
    }

    private void invalidateSelf()
    {
        this.lottieDrawable.invalidateSelf();
        return;
    }

    private synthetic void lambda$setupInOutAnimations$0()
    {
        int v0_3;
        if (this.inOutAnimation.getFloatValue() != 1065353216) {
            v0_3 = 0;
        } else {
            v0_3 = 1;
        }
        this.setVisible(v0_3);
        return;
    }

    private void recordRenderTime(float p3)
    {
        this.lottieDrawable.getComposition().getPerformanceTracker().recordRenderTime(this.layerModel.getName(), p3);
        return;
    }

    private void setVisible(boolean p2)
    {
        if (p2 != this.visible) {
            this.visible = p2;
            this.invalidateSelf();
        }
        return;
    }

    private void setupInOutAnimations()
    {
        int v1 = 1;
        if (this.layerModel.getInOutKeyframes().isEmpty()) {
            this.setVisible(1);
            return;
        } else {
            vn.ai.trueid.lottie.animation.keyframe.FloatKeyframeAnimation v0_1 = new vn.ai.trueid.lottie.animation.keyframe.FloatKeyframeAnimation(this.layerModel.getInOutKeyframes());
            this.inOutAnimation = v0_1;
            v0_1.setIsDiscrete();
            this.inOutAnimation.addUpdateListener(new vn.ai.trueid.lottie.animation.keyframe.a(3, this));
            if (((Float) this.inOutAnimation.getValue()).floatValue() != 1065353216) {
                v1 = 0;
            }
            this.setVisible(v1);
            this.addAnimation(this.inOutAnimation);
            return;
        }
    }

    public void addAnimation(vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation p2)
    {
        if (p2 != null) {
            this.animations.add(p2);
            return;
        } else {
            return;
        }
    }

    public void addValueCallback(Object p2, vn.ai.trueid.lottie.value.LottieValueCallback p3)
    {
        this.transform.applyValueCallback(p2, p3);
        return;
    }

    public void draw(android.graphics.Canvas p12, android.graphics.Matrix p13, int p14, vn.ai.trueid.lottie.utils.DropShadow p15)
    {
        vn.ai.trueid.lottie.L.beginSection(this.drawTraceName);
        if ((this.visible) && (!this.layerModel.isHidden())) {
            this.buildParentLayerListIfNeeded();
            if (vn.ai.trueid.lottie.L.isTraceEnabled()) {
                vn.ai.trueid.lottie.L.beginSection("Layer#parentMatrix");
            }
            this.matrix.reset();
            this.matrix.set(p13);
            boolean v1_44 = (this.parentLayers.size() - 1);
            while (v1_44) {
                this.matrix.preConcat(((vn.ai.trueid.lottie.model.layer.BaseLayer) this.parentLayers.get(v1_44)).transform.getMatrix());
                v1_44--;
            }
            if (vn.ai.trueid.lottie.L.isTraceEnabled()) {
                vn.ai.trueid.lottie.L.endSection("Layer#parentMatrix");
            }
            boolean v1_60;
            boolean v1_57 = this.transform.getOpacity();
            if (!v1_57) {
                v1_60 = 100;
            } else {
                boolean v1_59 = ((Integer) v1_57.getValue());
                if (!v1_59) {
                } else {
                    v1_60 = v1_59.intValue();
                }
            }
            int v8 = ((int) ((((((float) p14) / 1132396544) * ((float) v1_60)) / 1120403456) * 1132396544));
            if ((this.hasMatteOnThisLayer()) || ((this.hasMasksOnThisLayer()) || (this.getBlendMode() != vn.ai.trueid.lottie.model.content.LBlendMode.NORMAL))) {
                if (vn.ai.trueid.lottie.L.isTraceEnabled()) {
                    vn.ai.trueid.lottie.L.beginSection("Layer#computeBounds");
                }
                this.getBounds(this.rect, this.matrix, 0);
                this.intersectBoundsWithMatte(this.rect, p13);
                this.matrix.preConcat(this.transform.getMatrix());
                this.intersectBoundsWithMask(this.rect, this.matrix);
                this.canvasBounds.set(0, 0, ((float) p12.getWidth()), ((float) p12.getHeight()));
                p12.getMatrix(this.canvasMatrix);
                if (!this.canvasMatrix.isIdentity()) {
                    this.canvasMatrix.invert(this.canvasMatrix);
                    this.canvasMatrix.mapRect(this.canvasBounds);
                }
                if (!this.rect.intersect(this.canvasBounds)) {
                    this.rect.set(0, 0, 0, 0);
                }
                if (vn.ai.trueid.lottie.L.isTraceEnabled()) {
                    vn.ai.trueid.lottie.L.endSection("Layer#computeBounds");
                }
                if ((this.rect.width() >= 1065353216) && (this.rect.height() >= 1065353216)) {
                    if (vn.ai.trueid.lottie.L.isTraceEnabled()) {
                        vn.ai.trueid.lottie.L.beginSection("Layer#saveLayer");
                    }
                    this.contentPaint.setAlpha(255);
                    androidx.core.graphics.f.a(this.contentPaint, this.getBlendMode().toNativeBlendMode());
                    vn.ai.trueid.lottie.utils.Utils.saveLayerCompat(p12, this.rect, this.contentPaint);
                    if (vn.ai.trueid.lottie.L.isTraceEnabled()) {
                        vn.ai.trueid.lottie.L.endSection("Layer#saveLayer");
                    }
                    if (this.getBlendMode() == vn.ai.trueid.lottie.model.content.LBlendMode.MULTIPLY) {
                        if (android.os.Build$VERSION.SDK_INT < 29) {
                            if (this.solidWhitePaint == null) {
                                boolean v1_19 = new vn.ai.trueid.lottie.animation.LPaint();
                                this.solidWhitePaint = v1_19;
                                v1_19.setColor(-1);
                            }
                            boolean v1_20 = this.rect;
                            p12.drawRect((v1_20.left - 1065353216), (v1_20.top - 1065353216), (v1_20.right + 1065353216), (v1_20.bottom + 1065353216), this.solidWhitePaint);
                        }
                    } else {
                        this = this.clearCanvas(p12);
                    }
                    if (vn.ai.trueid.lottie.L.isTraceEnabled()) {
                        vn.ai.trueid.lottie.L.beginSection("Layer#drawLayer");
                    }
                    this.drawLayer(p12, this.matrix, v8, p15);
                    if (vn.ai.trueid.lottie.L.isTraceEnabled()) {
                        vn.ai.trueid.lottie.L.endSection("Layer#drawLayer");
                    }
                    if (this.hasMasksOnThisLayer()) {
                        this.applyMasks(p12, this.matrix);
                    }
                    if (this.hasMatteOnThisLayer()) {
                        if (vn.ai.trueid.lottie.L.isTraceEnabled()) {
                            vn.ai.trueid.lottie.L.beginSection("Layer#drawMatte");
                            vn.ai.trueid.lottie.L.beginSection("Layer#saveLayer");
                        }
                        vn.ai.trueid.lottie.utils.Utils.saveLayerCompat(p12, this.rect, this.mattePaint, 19);
                        if (vn.ai.trueid.lottie.L.isTraceEnabled()) {
                            vn.ai.trueid.lottie.L.endSection("Layer#saveLayer");
                        }
                        this = this.clearCanvas(p12);
                        this.matteLayer.draw(p12, p13, p14, 0);
                        if (vn.ai.trueid.lottie.L.isTraceEnabled()) {
                            vn.ai.trueid.lottie.L.beginSection("Layer#restoreLayer");
                        }
                        p12.restore();
                        if (vn.ai.trueid.lottie.L.isTraceEnabled()) {
                            vn.ai.trueid.lottie.L.endSection("Layer#restoreLayer");
                            vn.ai.trueid.lottie.L.endSection("Layer#drawMatte");
                        }
                    }
                    if (vn.ai.trueid.lottie.L.isTraceEnabled()) {
                        vn.ai.trueid.lottie.L.beginSection("Layer#restoreLayer");
                    }
                    p12.restore();
                    if (vn.ai.trueid.lottie.L.isTraceEnabled()) {
                        vn.ai.trueid.lottie.L.endSection("Layer#restoreLayer");
                    }
                }
                if (this.outlineMasksAndMattes) {
                    boolean v1_45 = this.outlineMasksAndMattesPaint;
                    if (v1_45) {
                        v1_45.setStyle(android.graphics.Paint$Style.STROKE);
                        this.outlineMasksAndMattesPaint.setColor(-251901);
                        this.outlineMasksAndMattesPaint.setStrokeWidth(1082130432);
                        p12.drawRect(this.rect, this.outlineMasksAndMattesPaint);
                        this.outlineMasksAndMattesPaint.setStyle(android.graphics.Paint$Style.FILL);
                        this.outlineMasksAndMattesPaint.setColor(1357638635);
                        p12.drawRect(this.rect, this.outlineMasksAndMattesPaint);
                    }
                }
                this.recordRenderTime(vn.ai.trueid.lottie.L.endSection(this.drawTraceName));
                return;
            } else {
                this.matrix.preConcat(this.transform.getMatrix());
                if (vn.ai.trueid.lottie.L.isTraceEnabled()) {
                    vn.ai.trueid.lottie.L.beginSection("Layer#drawLayer");
                }
                this.drawLayer(p12, this.matrix, v8, p15);
                if (vn.ai.trueid.lottie.L.isTraceEnabled()) {
                    vn.ai.trueid.lottie.L.endSection("Layer#drawLayer");
                }
                this.recordRenderTime(vn.ai.trueid.lottie.L.endSection(this.drawTraceName));
                return;
            }
        } else {
            vn.ai.trueid.lottie.L.endSection(this.drawTraceName);
            return;
        }
    }

    public abstract void drawLayer(android.graphics.Canvas p0, android.graphics.Matrix p1, int p2, vn.ai.trueid.lottie.utils.DropShadow p3);

    public vn.ai.trueid.lottie.model.content.LBlendMode getBlendMode()
    {
        return this.layerModel.getBlendMode();
    }

    public vn.ai.trueid.lottie.model.content.BlurEffect getBlurEffect()
    {
        return this.layerModel.getBlurEffect();
    }

    public android.graphics.BlurMaskFilter getBlurMaskFilter(float p4)
    {
        if (this.blurMaskFilterRadius != p4) {
            android.graphics.BlurMaskFilter v0_3 = new android.graphics.BlurMaskFilter((p4 / 1073741824), android.graphics.BlurMaskFilter$Blur.NORMAL);
            this.blurMaskFilter = v0_3;
            this.blurMaskFilterRadius = p4;
            return v0_3;
        } else {
            return this.blurMaskFilter;
        }
    }

    public void getBounds(android.graphics.RectF p2, android.graphics.Matrix p3, boolean p4)
    {
        this.rect.set(0, 0, 0, 0);
        this.buildParentLayerListIfNeeded();
        this.boundsMatrix.set(p3);
        if (p4 != null) {
            int v2_7 = this.parentLayers;
            if (v2_7 == 0) {
                int v2_8 = this.parentLayer;
                if (v2_8 != 0) {
                    this.boundsMatrix.preConcat(v2_8.transform.getMatrix());
                }
            } else {
                int v2_4 = (v2_7.size() - 1);
                while (v2_4 >= 0) {
                    this.boundsMatrix.preConcat(((vn.ai.trueid.lottie.model.layer.BaseLayer) this.parentLayers.get(v2_4)).transform.getMatrix());
                    v2_4--;
                }
            }
        }
        this.boundsMatrix.preConcat(this.transform.getMatrix());
        return;
    }

    public vn.ai.trueid.lottie.parser.DropShadowEffect getDropShadowEffect()
    {
        return this.layerModel.getDropShadowEffect();
    }

    public vn.ai.trueid.lottie.model.layer.Layer getLayerModel()
    {
        return this.layerModel;
    }

    public String getName()
    {
        return this.layerModel.getName();
    }

    public boolean hasMasksOnThisLayer()
    {
        int v0_0 = this.mask;
        if ((v0_0 == 0) || (v0_0.getMaskAnimations().isEmpty())) {
            return 0;
        } else {
            return 1;
        }
    }

    public boolean hasMatteOnThisLayer()
    {
        if (this.matteLayer == null) {
            return 0;
        } else {
            return 1;
        }
    }

    public void onValueChanged()
    {
        this.invalidateSelf();
        return;
    }

    public void removeAnimation(vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation p2)
    {
        this.animations.remove(p2);
        return;
    }

    public void resolveChildKeyPath(vn.ai.trueid.lottie.model.KeyPath p1, int p2, java.util.List p3, vn.ai.trueid.lottie.model.KeyPath p4)
    {
        return;
    }

    public void resolveKeyPath(vn.ai.trueid.lottie.model.KeyPath p4, int p5, java.util.List p6, vn.ai.trueid.lottie.model.KeyPath p7)
    {
        int v0_0 = this.matteLayer;
        if (v0_0 != 0) {
            int v0_7 = p7.addKey(v0_0.getName());
            if (p4.fullyResolvesTo(this.matteLayer.getName(), p5)) {
                p6.add(v0_7.resolve(this.matteLayer));
            }
            if ((p4.matches(this.matteLayer.getName(), p5)) && (p4.propagateToChildren(this.getName(), p5))) {
                this.matteLayer.resolveChildKeyPath(p4, (p4.incrementDepthBy(this.matteLayer.getName(), p5) + p5), p6, v0_7);
            }
        }
        if (p4.matches(this.getName(), p5)) {
            if (!"__container".equals(this.getName())) {
                p7 = p7.addKey(this.getName());
                if (p4.fullyResolvesTo(this.getName(), p5)) {
                    p6.add(p7.resolve(this));
                }
            }
            if (p4.propagateToChildren(this.getName(), p5)) {
                this.resolveChildKeyPath(p4, (p4.incrementDepthBy(this.getName(), p5) + p5), p6, p7);
            }
        }
        return;
    }

    public void setContents(java.util.List p1, java.util.List p2)
    {
        return;
    }

    public void setMatteLayer(vn.ai.trueid.lottie.model.layer.BaseLayer p1)
    {
        this.matteLayer = p1;
        return;
    }

    public void setOutlineMasksAndMattes(boolean p2)
    {
        if ((p2) && (this.outlineMasksAndMattesPaint == null)) {
            this.outlineMasksAndMattesPaint = new vn.ai.trueid.lottie.animation.LPaint();
        }
        this.outlineMasksAndMattes = p2;
        return;
    }

    public void setParentLayer(vn.ai.trueid.lottie.model.layer.BaseLayer p1)
    {
        this.parentLayer = p1;
        return;
    }

    public void setProgress(float p6)
    {
        if (vn.ai.trueid.lottie.L.isTraceEnabled()) {
            vn.ai.trueid.lottie.L.beginSection("BaseLayer#setProgress");
            vn.ai.trueid.lottie.L.beginSection("BaseLayer#setProgress.transform");
        }
        this.transform.setProgress(p6);
        if (vn.ai.trueid.lottie.L.isTraceEnabled()) {
            vn.ai.trueid.lottie.L.endSection("BaseLayer#setProgress.transform");
        }
        int v1_0 = 0;
        if (this.mask != null) {
            if (vn.ai.trueid.lottie.L.isTraceEnabled()) {
                vn.ai.trueid.lottie.L.beginSection("BaseLayer#setProgress.mask");
            }
            int v0_2 = 0;
            while (v0_2 < this.mask.getMaskAnimations().size()) {
                ((vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation) this.mask.getMaskAnimations().get(v0_2)).setProgress(p6);
                v0_2++;
            }
            if (vn.ai.trueid.lottie.L.isTraceEnabled()) {
                vn.ai.trueid.lottie.L.endSection("BaseLayer#setProgress.mask");
            }
        }
        if (this.inOutAnimation != null) {
            if (vn.ai.trueid.lottie.L.isTraceEnabled()) {
                vn.ai.trueid.lottie.L.beginSection("BaseLayer#setProgress.inout");
            }
            this.inOutAnimation.setProgress(p6);
            if (vn.ai.trueid.lottie.L.isTraceEnabled()) {
                vn.ai.trueid.lottie.L.endSection("BaseLayer#setProgress.inout");
            }
        }
        if (this.matteLayer != null) {
            if (vn.ai.trueid.lottie.L.isTraceEnabled()) {
                vn.ai.trueid.lottie.L.beginSection("BaseLayer#setProgress.matte");
            }
            this.matteLayer.setProgress(p6);
            if (vn.ai.trueid.lottie.L.isTraceEnabled()) {
                vn.ai.trueid.lottie.L.endSection("BaseLayer#setProgress.matte");
            }
        }
        if (vn.ai.trueid.lottie.L.isTraceEnabled()) {
            int v0_16 = new StringBuilder("BaseLayer#setProgress.animations.");
            v0_16.append(this.animations.size());
            vn.ai.trueid.lottie.L.beginSection(v0_16.toString());
        }
        while (v1_0 < this.animations.size()) {
            ((vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation) this.animations.get(v1_0)).setProgress(p6);
            v1_0++;
        }
        if (vn.ai.trueid.lottie.L.isTraceEnabled()) {
            String v6_3 = new StringBuilder("BaseLayer#setProgress.animations.");
            v6_3.append(this.animations.size());
            vn.ai.trueid.lottie.L.endSection(v6_3.toString());
            vn.ai.trueid.lottie.L.endSection("BaseLayer#setProgress");
        }
        return;
    }
}
