package vn.ai.trueid.lottie.model.layer;
public class ImageLayer extends vn.ai.trueid.lottie.model.layer.BaseLayer {
    private vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation colorFilterAnimation;
    private vn.ai.trueid.lottie.animation.keyframe.DropShadowKeyframeAnimation dropShadowAnimation;
    private final android.graphics.Rect dst;
    private vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation imageAnimation;
    private final android.graphics.RectF layerBounds;
    private final vn.ai.trueid.lottie.LottieImageAsset lottieImageAsset;
    private vn.ai.trueid.lottie.utils.OffscreenLayer offscreenLayer;
    private vn.ai.trueid.lottie.utils.OffscreenLayer$ComposeOp offscreenOp;
    private final android.graphics.Paint paint;
    private final android.graphics.Rect src;

    public ImageLayer(vn.ai.trueid.lottie.LottieDrawable p3, vn.ai.trueid.lottie.model.layer.Layer p4)
    {
        super(p3, p4);
        super.paint = new vn.ai.trueid.lottie.animation.LPaint(3);
        super.src = new android.graphics.Rect();
        super.dst = new android.graphics.Rect();
        super.layerBounds = new android.graphics.RectF();
        super.lottieImageAsset = p3.getLottieImageAssetForId(p4.getRefId());
        if (super.getDropShadowEffect() != null) {
            super.dropShadowAnimation = new vn.ai.trueid.lottie.animation.keyframe.DropShadowKeyframeAnimation(super, super, super.getDropShadowEffect());
        }
        return;
    }

    private android.graphics.Bitmap getBitmap()
    {
        int v0_0 = this.imageAnimation;
        if (v0_0 != 0) {
            int v0_6 = ((android.graphics.Bitmap) v0_0.getValue());
            if (v0_6 != 0) {
                return v0_6;
            }
        }
        int v0_1 = this.lottieDrawable.getBitmapForId(this.layerModel.getRefId());
        if (v0_1 == 0) {
            int v0_2 = this.lottieImageAsset;
            if (v0_2 == 0) {
                return 0;
            } else {
                return v0_2.getBitmap();
            }
        } else {
            return v0_1;
        }
    }

    public void addValueCallback(Object p3, vn.ai.trueid.lottie.value.LottieValueCallback p4)
    {
        super.addValueCallback(p3, p4);
        if (p3 != vn.ai.trueid.lottie.LottieProperty.COLOR_FILTER) {
            if (p3 != vn.ai.trueid.lottie.LottieProperty.IMAGE) {
                if (p3 == vn.ai.trueid.lottie.LottieProperty.DROP_SHADOW_COLOR) {
                    Float v0_10 = this.dropShadowAnimation;
                    if (v0_10 != null) {
                        v0_10.setColorCallback(p4);
                        return;
                    }
                }
                if (p3 == vn.ai.trueid.lottie.LottieProperty.DROP_SHADOW_OPACITY) {
                    Float v0_2 = this.dropShadowAnimation;
                    if (v0_2 != null) {
                        v0_2.setOpacityCallback(p4);
                        return;
                    }
                }
                if (p3 == vn.ai.trueid.lottie.LottieProperty.DROP_SHADOW_DIRECTION) {
                    Float v0_4 = this.dropShadowAnimation;
                    if (v0_4 != null) {
                        v0_4.setDirectionCallback(p4);
                        return;
                    }
                }
                if (p3 == vn.ai.trueid.lottie.LottieProperty.DROP_SHADOW_DISTANCE) {
                    Float v0_6 = this.dropShadowAnimation;
                    if (v0_6 != null) {
                        v0_6.setDistanceCallback(p4);
                        return;
                    }
                }
                if (p3 == vn.ai.trueid.lottie.LottieProperty.DROP_SHADOW_RADIUS) {
                    vn.ai.trueid.lottie.animation.keyframe.DropShadowKeyframeAnimation v3_1 = this.dropShadowAnimation;
                    if (v3_1 != null) {
                        v3_1.setRadiusCallback(p4);
                    }
                }
                return;
            } else {
                if (p4 != null) {
                    this.imageAnimation = new vn.ai.trueid.lottie.animation.keyframe.ValueCallbackKeyframeAnimation(p4);
                    return;
                } else {
                    this.imageAnimation = 0;
                    return;
                }
            }
        } else {
            if (p4 != null) {
                this.colorFilterAnimation = new vn.ai.trueid.lottie.animation.keyframe.ValueCallbackKeyframeAnimation(p4);
                return;
            } else {
                this.colorFilterAnimation = 0;
                return;
            }
        }
    }

    public void drawLayer(android.graphics.Canvas p7, android.graphics.Matrix p8, int p9, vn.ai.trueid.lottie.utils.DropShadow p10)
    {
        android.graphics.Bitmap v0 = this.getBitmap();
        if ((v0 != null) && ((!v0.isRecycled()) && (this.lottieImageAsset != null))) {
            vn.ai.trueid.lottie.utils.OffscreenLayer$ComposeOp v1_14 = vn.ai.trueid.lottie.utils.Utils.dpScale();
            this.paint.setAlpha(p9);
            float v2_1 = this.colorFilterAnimation;
            if (v2_1 != 0) {
                this.paint.setColorFilter(((android.graphics.ColorFilter) v2_1.getValue()));
            }
            float v2_4 = this.dropShadowAnimation;
            if (v2_4 != 0) {
                p10 = v2_4.evaluate(p8, p9);
            }
            int v5 = 0;
            this.src.set(0, 0, v0.getWidth(), v0.getHeight());
            if (!this.lottieDrawable.getMaintainOriginalImageBounds()) {
                this.dst.set(0, 0, ((int) (((float) v0.getWidth()) * v1_14)), ((int) (((float) v0.getHeight()) * v1_14)));
            } else {
                this.dst.set(0, 0, ((int) (((float) this.lottieImageAsset.getWidth()) * v1_14)), ((int) (((float) this.lottieImageAsset.getHeight()) * v1_14)));
            }
            if (p10 != null) {
                v5 = 1;
            }
            if (v5 != 0) {
                if (this.offscreenLayer == null) {
                    this.offscreenLayer = new vn.ai.trueid.lottie.utils.OffscreenLayer();
                }
                if (this.offscreenOp == null) {
                    this.offscreenOp = new vn.ai.trueid.lottie.utils.OffscreenLayer$ComposeOp();
                }
                this.offscreenOp.reset();
                p10.applyWithAlpha(p9, this.offscreenOp);
                android.graphics.RectF v10_1 = this.dst;
                this.layerBounds.set(((float) v10_1.left), ((float) v10_1.top), ((float) v10_1.right), ((float) v10_1.bottom));
                p8.mapRect(this.layerBounds);
                p7 = this.offscreenLayer.start(p7, this.layerBounds, this.offscreenOp);
            }
            p7.save();
            p7.concat(p8);
            p7.drawBitmap(v0, this.src, this.dst, this.paint);
            if (v5 != 0) {
                this.offscreenLayer.finish();
                if (this.offscreenLayer.finishDecrementsCanvasSaveCount()) {
                    return;
                }
            }
            p7.restore();
        }
        return;
    }

    public void getBounds(android.graphics.RectF p3, android.graphics.Matrix p4, boolean p5)
    {
        super.getBounds(p3, p4, p5);
        if (this.lottieImageAsset != null) {
            android.graphics.Matrix v4_2 = vn.ai.trueid.lottie.utils.Utils.dpScale();
            if (!this.lottieDrawable.getMaintainOriginalImageBounds()) {
                float v5_1 = this.getBitmap();
                if (v5_1 == 0) {
                    p3.set(0, 0, (((float) this.lottieImageAsset.getWidth()) * v4_2), (((float) this.lottieImageAsset.getHeight()) * v4_2));
                } else {
                    p3.set(0, 0, (((float) v5_1.getWidth()) * v4_2), (((float) v5_1.getHeight()) * v4_2));
                }
            } else {
                p3.set(0, 0, (((float) this.lottieImageAsset.getWidth()) * v4_2), (((float) this.lottieImageAsset.getHeight()) * v4_2));
            }
            this.boundsMatrix.mapRect(p3);
        }
        return;
    }
}
