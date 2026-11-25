package vn.ai.trueid.lottie.utils;
public class OffscreenLayer {
    private static final android.graphics.Matrix IDENTITY_MATRIX;
    private android.graphics.Bitmap bitmap;
    private android.graphics.Canvas bitmapCanvas;
    private android.graphics.Rect bitmapSrcRect;
    private vn.ai.trueid.lottie.animation.LPaint clearPaint;
    private android.graphics.Paint composePaint;
    private vn.ai.trueid.lottie.utils.OffscreenLayer$RenderStrategy currentStrategy;
    private android.graphics.Rect intRectIncludingShadow;
    private vn.ai.trueid.lottie.utils.DropShadow lastRenderNodeShadow;
    private float lastShadowBlurRadius;
    private vn.ai.trueid.lottie.utils.OffscreenLayer$ComposeOp op;
    private android.graphics.Canvas parentCanvas;
    android.graphics.Matrix parentCanvasMatrix;
    float[] preExistingTransform;
    private android.graphics.RectF rectIncludingShadow;
    private android.graphics.RenderNode renderNode;
    private android.graphics.RectF scaledBounds;
    private android.graphics.RectF scaledRectIncludingShadow;
    private android.graphics.Bitmap shadowBitmap;
    private android.graphics.Canvas shadowBitmapCanvas;
    private android.graphics.Rect shadowBitmapSrcRect;
    private android.graphics.BlurMaskFilter shadowBlurFilter;
    private android.graphics.Bitmap shadowMaskBitmap;
    private android.graphics.Canvas shadowMaskBitmapCanvas;
    private vn.ai.trueid.lottie.animation.LPaint shadowPaint;
    private android.graphics.RenderNode shadowRenderNode;
    private android.graphics.RectF targetRect;
    private android.graphics.RectF tmpRect;

    static OffscreenLayer()
    {
        vn.ai.trueid.lottie.utils.OffscreenLayer.IDENTITY_MATRIX = new android.graphics.Matrix();
        return;
    }

    public OffscreenLayer()
    {
        this.lastShadowBlurRadius = 0;
        return;
    }

    private android.graphics.Bitmap allocateBitmap(android.graphics.RectF p7, android.graphics.Bitmap$Config p8)
    {
        return android.graphics.Bitmap.createBitmap(Math.max(((int) Math.ceil((((double) p7.width()) * 4607407598781385933))), 1), Math.max(((int) Math.ceil((((double) p7.height()) * 4607407598781385933))), 1), p8);
    }

    private android.graphics.RectF calculateRectIncludingShadow(android.graphics.RectF p5, vn.ai.trueid.lottie.utils.DropShadow p6)
    {
        if (this.rectIncludingShadow == null) {
            this.rectIncludingShadow = new android.graphics.RectF();
        }
        if (this.tmpRect == null) {
            this.tmpRect = new android.graphics.RectF();
        }
        this.rectIncludingShadow.set(p5);
        this.rectIncludingShadow.offsetTo((p6.getDx() + p5.left), (p6.getDy() + p5.top));
        this.rectIncludingShadow.inset((- p6.getRadius()), (- p6.getRadius()));
        this.tmpRect.set(p5);
        this.rectIncludingShadow.union(this.tmpRect);
        return this.rectIncludingShadow;
    }

    private vn.ai.trueid.lottie.utils.OffscreenLayer$RenderStrategy chooseRenderStrategy(android.graphics.Canvas p2, vn.ai.trueid.lottie.utils.OffscreenLayer$ComposeOp p3)
    {
        if (!p3.isNoop()) {
            if (p3.hasShadow()) {
                int v3_2 = android.os.Build$VERSION.SDK_INT;
                if ((v3_2 >= 29) && (p2.isHardwareAccelerated())) {
                    if (v3_2 > 31) {
                        return vn.ai.trueid.lottie.utils.OffscreenLayer$RenderStrategy.RENDER_NODE;
                    } else {
                        return vn.ai.trueid.lottie.utils.OffscreenLayer$RenderStrategy.BITMAP;
                    }
                } else {
                    return vn.ai.trueid.lottie.utils.OffscreenLayer$RenderStrategy.BITMAP;
                }
            } else {
                return vn.ai.trueid.lottie.utils.OffscreenLayer$RenderStrategy.SAVE_LAYER;
            }
        } else {
            return vn.ai.trueid.lottie.utils.OffscreenLayer$RenderStrategy.DIRECT;
        }
    }

    private void deallocateBitmap(android.graphics.Bitmap p1)
    {
        p1.recycle();
        return;
    }

    private boolean needNewBitmap(android.graphics.Bitmap p5, android.graphics.RectF p6)
    {
        if (p5 != 0) {
            if ((p6.width() < ((float) p5.getWidth())) && ((p6.height() < ((float) p5.getHeight())) && ((p6.width() >= (((float) p5.getWidth()) * 1061158912)) && (p6.height() >= (((float) p5.getHeight()) * 1061158912))))) {
                return 0;
            } else {
                return 1;
            }
        } else {
            return 1;
        }
    }

    private void renderBitmapShadow(android.graphics.Canvas p10, vn.ai.trueid.lottie.utils.DropShadow p11)
    {
        android.graphics.Rect v0_0 = this.targetRect;
        if ((v0_0 == null) || (this.bitmap == null)) {
            throw new IllegalStateException("Cannot render to bitmap outside a start()/finish() block");
        } else {
            android.graphics.Rect v0_20 = this.calculateRectIncludingShadow(v0_0, p11);
            if (this.intRectIncludingShadow == null) {
                this.intRectIncludingShadow = new android.graphics.Rect();
            }
            int v4_4;
            this.intRectIncludingShadow.set(((int) Math.floor(((double) v0_20.left))), ((int) Math.floor(((double) v0_20.top))), ((int) Math.ceil(((double) v0_20.right))), ((int) Math.ceil(((double) v0_20.bottom))));
            android.graphics.Rect v1_28 = this.preExistingTransform;
            android.graphics.Paint v2_6 = 1065353216;
            if (v1_28 == null) {
                v4_4 = 1065353216;
            } else {
                v4_4 = v1_28[0];
            }
            if (v1_28 != null) {
                v2_6 = v1_28[4];
            }
            if (this.scaledRectIncludingShadow == null) {
                this.scaledRectIncludingShadow = new android.graphics.RectF();
            }
            this.scaledRectIncludingShadow.set((v0_20.left * v4_4), (v0_20.top * v2_6), (v0_20.right * v4_4), (v0_20.bottom * v2_6));
            if (this.shadowBitmapSrcRect == null) {
                this.shadowBitmapSrcRect = new android.graphics.Rect();
            }
            this.shadowBitmapSrcRect.set(0, 0, Math.round(this.scaledRectIncludingShadow.width()), Math.round(this.scaledRectIncludingShadow.height()));
            if (!this.needNewBitmap(this.shadowBitmap, this.scaledRectIncludingShadow)) {
                android.graphics.Rect v1_39 = this.shadowBitmapCanvas;
                if ((v1_39 != null) && (this.shadowMaskBitmapCanvas != null)) {
                    float v3_16 = this.clearPaint;
                    if (v3_16 != 0) {
                        v1_39.drawRect(this.shadowBitmapSrcRect, v3_16);
                        this.shadowMaskBitmapCanvas.drawRect(this.shadowBitmapSrcRect, this.clearPaint);
                        if (this.shadowMaskBitmap == null) {
                            throw new IllegalStateException("Expected to have allocated a shadow mask bitmap");
                        } else {
                            if (this.shadowPaint == null) {
                                this.shadowPaint = new vn.ai.trueid.lottie.animation.LPaint(1);
                            }
                            android.graphics.Rect v1_16 = this.targetRect;
                            this.shadowMaskBitmapCanvas.drawBitmap(this.bitmap, ((float) Math.round(((v1_16.left - v0_20.left) * v4_4))), ((float) Math.round(((v1_16.top - v0_20.top) * v2_6))), 0);
                            if ((this.shadowBlurFilter == null) || (this.lastShadowBlurRadius != p11.getRadius())) {
                                int v5_8 = (((v4_4 + v2_6) * p11.getRadius()) / 1073741824);
                                if (v5_8 <= 0) {
                                    this.shadowBlurFilter = 0;
                                } else {
                                    this.shadowBlurFilter = new android.graphics.BlurMaskFilter(v5_8, android.graphics.BlurMaskFilter$Blur.NORMAL);
                                }
                                this.lastShadowBlurRadius = p11.getRadius();
                            }
                            this.shadowPaint.setColor(p11.getColor());
                            if (p11.getRadius() <= 0) {
                                this.shadowPaint.setMaskFilter(0);
                            } else {
                                this.shadowPaint.setMaskFilter(this.shadowBlurFilter);
                            }
                            this.shadowPaint.setFilterBitmap(1);
                            this.shadowBitmapCanvas.drawBitmap(this.shadowMaskBitmap, ((float) Math.round((p11.getDx() * v4_4))), ((float) Math.round((p11.getDy() * v2_6))), this.shadowPaint);
                            p10.drawBitmap(this.shadowBitmap, this.shadowBitmapSrcRect, this.intRectIncludingShadow, this.composePaint);
                            return;
                        }
                    }
                }
                throw new IllegalStateException("If needNewBitmap() returns true, we should have a canvas and bitmap ready");
            } else {
                android.graphics.Rect v1_41 = this.shadowBitmap;
                if (v1_41 != null) {
                    this.deallocateBitmap(v1_41);
                }
                android.graphics.Rect v1_1 = this.shadowMaskBitmap;
                if (v1_1 != null) {
                    this.deallocateBitmap(v1_1);
                }
                this.shadowBitmap = this.allocateBitmap(this.scaledRectIncludingShadow, android.graphics.Bitmap$Config.ARGB_8888);
                this.shadowMaskBitmap = this.allocateBitmap(this.scaledRectIncludingShadow, android.graphics.Bitmap$Config.ALPHA_8);
                this.shadowBitmapCanvas = new android.graphics.Canvas(this.shadowBitmap);
                this.shadowMaskBitmapCanvas = new android.graphics.Canvas(this.shadowMaskBitmap);
            }
        }
    }

    private void renderHardwareShadow(android.graphics.Canvas p9, vn.ai.trueid.lottie.utils.DropShadow p10)
    {
        if ((this.renderNode == null) || (this.shadowRenderNode == null)) {
            throw new IllegalStateException("Cannot render to render node outside a start()/finish() block");
        } else {
            if (android.os.Build$VERSION.SDK_INT < 31) {
                throw new RuntimeException("RenderEffect is not supported on API level <31");
            } else {
                int v3;
                android.graphics.RenderEffect v0_2 = this.preExistingTransform;
                float v1_0 = 1065353216;
                if (v0_2 == null) {
                    v3 = 1065353216;
                } else {
                    v3 = v0_2[0];
                }
                if (v0_2 != null) {
                    v1_0 = v0_2[4];
                }
                android.graphics.RenderEffect v0_4 = this.lastRenderNodeShadow;
                if ((v0_4 == null) || (!p10.sameAs(v0_4))) {
                    android.graphics.RenderEffect v0_8 = android.graphics.RenderEffect.createColorFilterEffect(new android.graphics.PorterDuffColorFilter(p10.getColor(), android.graphics.PorterDuff$Mode.SRC_IN));
                    if (p10.getRadius() > 0) {
                        v0_8 = android.graphics.RenderEffect.createBlurEffect((((v3 + v1_0) * p10.getRadius()) / 1073741824), (((v3 + v1_0) * p10.getRadius()) / 1073741824), v0_8, android.graphics.Shader$TileMode.CLAMP);
                    }
                    this.shadowRenderNode.setRenderEffect(v0_8);
                    this.lastRenderNodeShadow = p10;
                }
                android.graphics.RenderEffect v0_10 = this.calculateRectIncludingShadow(this.targetRect, p10);
                android.graphics.RenderNode v4_8 = new android.graphics.RectF((v0_10.left * v3), (v0_10.top * v1_0), (v0_10.right * v3), (v0_10.bottom * v1_0));
                this.shadowRenderNode.setPosition(0, 0, ((int) v4_8.width()), ((int) v4_8.height()));
                android.graphics.RenderEffect v0_16 = this.shadowRenderNode.beginRecording(((int) v4_8.width()), ((int) v4_8.height()));
                v0_16.translate(((p10.getDx() * v3) + (- v4_8.left)), ((p10.getDy() * v1_0) + (- v4_8.top)));
                v0_16.drawRenderNode(this.renderNode);
                this.shadowRenderNode.endRecording();
                p9.save();
                p9.translate(v4_8.left, v4_8.top);
                p9.drawRenderNode(this.shadowRenderNode);
                p9.restore();
                return;
            }
        }
    }

    public void finish()
    {
        if ((this.parentCanvas == null) || ((this.op == null) || ((this.preExistingTransform == null) || (this.targetRect == null)))) {
            throw new IllegalStateException("OffscreenBitmap: finish() call without matching start()");
        } else {
            android.graphics.Canvas v0_7 = vn.ai.trueid.lottie.utils.OffscreenLayer$1.$SwitchMap$com$airbnb$lottie$utils$OffscreenLayer$RenderStrategy[this.currentStrategy.ordinal()];
            if (v0_7 == 1) {
                this.parentCanvas.restore();
            } else {
                if (v0_7 == 2) {
                    this.parentCanvas.restore();
                } else {
                    if (v0_7 == 3) {
                        if (this.bitmap == null) {
                            throw new IllegalStateException("Bitmap is not ready; should\'ve been initialized at start() time");
                        } else {
                            if (this.op.hasShadow()) {
                                this.renderBitmapShadow(this.parentCanvas, this.op.shadow);
                            }
                            if (this.bitmapSrcRect == null) {
                                this.bitmapSrcRect = new android.graphics.Rect();
                            }
                            this.bitmapSrcRect.set(0, 0, ((int) (this.targetRect.width() * this.preExistingTransform[0])), ((int) (this.targetRect.height() * this.preExistingTransform[4])));
                            this.parentCanvas.drawBitmap(this.bitmap, this.bitmapSrcRect, this.targetRect, this.composePaint);
                        }
                    } else {
                        if (v0_7 == 4) {
                            if (this.renderNode == null) {
                                throw new IllegalStateException("RenderNode is not ready; should\'ve been initialized at start() time");
                            } else {
                                if (android.os.Build$VERSION.SDK_INT < 29) {
                                    throw new IllegalStateException("RenderNode not supported but we chose it as render strategy");
                                } else {
                                    this.parentCanvas.save();
                                    android.graphics.RenderNode v1_19 = this.preExistingTransform;
                                    this.parentCanvas.scale((1065353216 / v1_19[0]), (1065353216 / v1_19[4]));
                                    this.renderNode.endRecording();
                                    if (this.op.hasShadow()) {
                                        this.renderHardwareShadow(this.parentCanvas, this.op.shadow);
                                    }
                                    this.parentCanvas.drawRenderNode(this.renderNode);
                                    this.parentCanvas.restore();
                                }
                            }
                        }
                    }
                }
            }
            this.parentCanvas = 0;
            return;
        }
    }

    public boolean finishDecrementsCanvasSaveCount()
    {
        if (this.currentStrategy != vn.ai.trueid.lottie.utils.OffscreenLayer$RenderStrategy.RENDER_NODE) {
            return 0;
        } else {
            return 1;
        }
    }

    public android.graphics.Canvas start(android.graphics.Canvas p10, android.graphics.RectF p11, vn.ai.trueid.lottie.utils.OffscreenLayer$ComposeOp p12)
    {
        if (this.parentCanvas != null) {
            throw new IllegalStateException("Cannot nest start() calls on a single OffscreenBitmap - call finish() first");
        } else {
            if (this.preExistingTransform == null) {
                android.graphics.Paint v0_12 = new float[9];
                this.preExistingTransform = v0_12;
            }
            if (this.parentCanvasMatrix == null) {
                this.parentCanvasMatrix = new android.graphics.Matrix();
            }
            p10.getMatrix(this.parentCanvasMatrix);
            this.parentCanvasMatrix.getValues(this.preExistingTransform);
            android.graphics.Paint v0_6 = this.preExistingTransform;
            android.graphics.ColorFilter v1_2 = v0_6[0];
            android.graphics.Paint v0_7 = v0_6[4];
            if (this.scaledBounds == null) {
                this.scaledBounds = new android.graphics.RectF();
            }
            this.scaledBounds.set((p11.left * v1_2), (p11.top * v0_7), (p11.right * v1_2), (p11.bottom * v0_7));
            this.parentCanvas = p10;
            this.op = p12;
            this.currentStrategy = this.chooseRenderStrategy(p10, p12);
            if (this.targetRect == null) {
                this.targetRect = new android.graphics.RectF();
            }
            this.targetRect.set(((float) ((int) p11.left)), ((float) ((int) p11.top)), ((float) ((int) p11.right)), ((float) ((int) p11.bottom)));
            if (this.composePaint == null) {
                this.composePaint = new vn.ai.trueid.lottie.animation.LPaint();
            }
            this.composePaint.reset();
            android.graphics.Paint v3_25 = vn.ai.trueid.lottie.utils.OffscreenLayer$1.$SwitchMap$com$airbnb$lottie$utils$OffscreenLayer$RenderStrategy[this.currentStrategy.ordinal()];
            if (v3_25 == 1) {
                p10.save();
                return p10;
            } else {
                if (v3_25 == 2) {
                    this.composePaint.setAlpha(p12.alpha);
                    this.composePaint.setColorFilter(p12.colorFilter);
                    if (p12.hasBlendMode()) {
                        androidx.core.graphics.f.a(this.composePaint, p12.blendMode);
                    }
                    vn.ai.trueid.lottie.utils.Utils.saveLayerCompat(p10, p11, this.composePaint);
                    return p10;
                } else {
                    if (v3_25 == 3) {
                        if (this.clearPaint == null) {
                            IllegalStateException v10_3 = new vn.ai.trueid.lottie.animation.LPaint();
                            this.clearPaint = v10_3;
                            v10_3.setXfermode(new android.graphics.PorterDuffXfermode(android.graphics.PorterDuff$Mode.CLEAR));
                        }
                        if (!this.needNewBitmap(this.bitmap, this.scaledBounds)) {
                            IllegalStateException v10_6 = this.bitmapCanvas;
                            if (v10_6 == null) {
                                throw new IllegalStateException("If needNewBitmap() returns true, we should have a canvas ready");
                            } else {
                                v10_6.setMatrix(vn.ai.trueid.lottie.utils.OffscreenLayer.IDENTITY_MATRIX);
                                this.bitmapCanvas.drawRect(-1082130432, -1082130432, (this.scaledBounds.width() + 1065353216), (this.scaledBounds.height() + 1065353216), this.clearPaint);
                            }
                        } else {
                            IllegalStateException v10_13 = this.bitmap;
                            if (v10_13 != null) {
                                this.deallocateBitmap(v10_13);
                            }
                            this.bitmap = this.allocateBitmap(this.scaledBounds, android.graphics.Bitmap$Config.ARGB_8888);
                            this.bitmapCanvas = new android.graphics.Canvas(this.bitmap);
                        }
                        androidx.core.graphics.f.a(this.composePaint, p12.blendMode);
                        this.composePaint.setColorFilter(p12.colorFilter);
                        this.composePaint.setAlpha(p12.alpha);
                        IllegalStateException v10_21 = this.bitmapCanvas;
                        v10_21.scale(v1_2, v0_7);
                        v10_21.translate((- p11.left), (- p11.top));
                        return v10_21;
                    } else {
                        if (v3_25 != 4) {
                            throw new RuntimeException("Invalid render strategy for OffscreenLayer");
                        } else {
                            if (android.os.Build$VERSION.SDK_INT < 29) {
                                throw new IllegalStateException("RenderNode not supported but we chose it as render strategy");
                            } else {
                                if (this.renderNode == null) {
                                    this.renderNode = new android.graphics.RenderNode("OffscreenLayer.main");
                                }
                                if ((p12.hasShadow()) && (this.shadowRenderNode == null)) {
                                    this.shadowRenderNode = new android.graphics.RenderNode("OffscreenLayer.shadow");
                                    this.lastRenderNodeShadow = 0;
                                }
                                if ((p12.hasBlendMode()) || (p12.hasColorFilter())) {
                                    if (this.composePaint == null) {
                                        this.composePaint = new vn.ai.trueid.lottie.animation.LPaint();
                                    }
                                    this.composePaint.reset();
                                    androidx.core.graphics.f.a(this.composePaint, p12.blendMode);
                                    this.composePaint.setColorFilter(p12.colorFilter);
                                    this.renderNode.setUseCompositingLayer(1, this.composePaint);
                                    if (p12.hasShadow()) {
                                        IllegalStateException v10_46 = this.shadowRenderNode;
                                        if (v10_46 == null) {
                                            throw new IllegalStateException("Must initialize shadowRenderNode when we have shadow");
                                        } else {
                                            v10_46.setUseCompositingLayer(1, this.composePaint);
                                        }
                                    }
                                }
                                this.renderNode.setAlpha((((float) p12.alpha) / 1132396544));
                                if (p12.hasShadow()) {
                                    IllegalStateException v10_51 = this.shadowRenderNode;
                                    if (v10_51 == null) {
                                        throw new IllegalStateException("Must initialize shadowRenderNode when we have shadow");
                                    } else {
                                        v10_51.setAlpha((((float) p12.alpha) / 1132396544));
                                    }
                                }
                                this.renderNode.setHasOverlappingRendering(1);
                                float v12_7 = this.scaledBounds;
                                this.renderNode.setPosition(((int) v12_7.left), ((int) v12_7.top), ((int) v12_7.right), ((int) v12_7.bottom));
                                IllegalStateException v10_57 = this.renderNode.beginRecording(((int) this.scaledBounds.width()), ((int) this.scaledBounds.height()));
                                v10_57.setMatrix(vn.ai.trueid.lottie.utils.OffscreenLayer.IDENTITY_MATRIX);
                                v10_57.scale(v1_2, v0_7);
                                v10_57.translate((- p11.left), (- p11.top));
                                return v10_57;
                            }
                        }
                    }
                }
            }
        }
    }
}
