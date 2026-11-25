package vn.ai.trueid.lottie.model.layer;
public class SolidLayer extends vn.ai.trueid.lottie.model.layer.BaseLayer {
    private vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation colorAnimation;
    private vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation colorFilterAnimation;
    private final vn.ai.trueid.lottie.model.layer.Layer layerModel;
    private final android.graphics.Paint paint;
    private final android.graphics.Path path;
    private final float[] points;
    private final android.graphics.RectF rect;

    public SolidLayer(vn.ai.trueid.lottie.LottieDrawable p2, vn.ai.trueid.lottie.model.layer.Layer p3)
    {
        super(p2, p3);
        super.rect = new android.graphics.RectF();
        vn.ai.trueid.lottie.animation.LPaint v2_4 = new vn.ai.trueid.lottie.animation.LPaint();
        super.paint = v2_4;
        android.graphics.Paint$Style v0_5 = new float[8];
        super.points = v0_5;
        super.path = new android.graphics.Path();
        super.layerModel = p3;
        v2_4.setAlpha(0);
        v2_4.setStyle(android.graphics.Paint$Style.FILL);
        v2_4.setColor(p3.getSolidColor());
        return;
    }

    public void addValueCallback(Object p3, vn.ai.trueid.lottie.value.LottieValueCallback p4)
    {
        super.addValueCallback(p3, p4);
        if (p3 != vn.ai.trueid.lottie.LottieProperty.COLOR_FILTER) {
            if (p3 == vn.ai.trueid.lottie.LottieProperty.COLOR) {
                if (p4 != 0) {
                    this.colorAnimation = new vn.ai.trueid.lottie.animation.keyframe.ValueCallbackKeyframeAnimation(p4);
                } else {
                    this.colorAnimation = 0;
                    this.paint.setColor(this.layerModel.getSolidColor());
                    return;
                }
            }
            return;
        } else {
            if (p4 != 0) {
                this.colorFilterAnimation = new vn.ai.trueid.lottie.animation.keyframe.ValueCallbackKeyframeAnimation(p4);
                return;
            } else {
                this.colorFilterAnimation = 0;
                return;
            }
        }
    }

    public void drawLayer(android.graphics.Canvas p9, android.graphics.Matrix p10, int p11, vn.ai.trueid.lottie.utils.DropShadow p12)
    {
        float v0_8 = android.graphics.Color.alpha(this.layerModel.getSolidColor());
        if (v0_8 != 0) {
            int v1_0;
            int v1_12 = this.colorAnimation;
            if (v1_12 != 0) {
                v1_0 = ((Integer) v1_12.getValue());
            } else {
                v1_0 = 0;
            }
            if (v1_0 == 0) {
                this.paint.setColor(this.layerModel.getSolidColor());
            } else {
                this.paint.setColor(v1_0.intValue());
            }
            int v1_9;
            if (this.transform.getOpacity() != null) {
                v1_9 = ((Integer) this.transform.getOpacity().getValue()).intValue();
            } else {
                v1_9 = 100;
            }
            android.graphics.Paint v11_14 = ((int) (((((((float) v0_8) / 1132396544) * ((float) v1_9)) / 1120403456) * (((float) p11) / 1132396544)) * 1132396544));
            this.paint.setAlpha(v11_14);
            if (p12 == 0) {
                this.paint.clearShadowLayer();
            } else {
                p12.applyTo(this.paint);
            }
            float v12_3 = this.colorFilterAnimation;
            if (v12_3 != 0) {
                this.paint.setColorFilter(((android.graphics.ColorFilter) v12_3.getValue()));
            }
            if (v11_14 > null) {
                android.graphics.Paint v11_15 = this.points;
                v11_15[0] = 0;
                v11_15[1] = 0;
                v11_15[2] = ((float) this.layerModel.getSolidWidth());
                android.graphics.Paint v11_16 = this.points;
                v11_16[3] = 0;
                v11_16[4] = ((float) this.layerModel.getSolidWidth());
                this.points[5] = ((float) this.layerModel.getSolidHeight());
                android.graphics.Paint v11_18 = this.points;
                v11_18[6] = 0;
                v11_18[7] = ((float) this.layerModel.getSolidHeight());
                p10.mapPoints(this.points);
                this.path.reset();
                android.graphics.Paint v11_1 = this.points;
                this.path.moveTo(v11_1[0], v11_1[1]);
                android.graphics.Paint v11_3 = this.points;
                this.path.lineTo(v11_3[2], v11_3[3]);
                android.graphics.Paint v11_5 = this.points;
                this.path.lineTo(v11_5[4], v11_5[5]);
                android.graphics.Paint v11_7 = this.points;
                this.path.lineTo(v11_7[6], v11_7[7]);
                android.graphics.Paint v11_9 = this.points;
                this.path.lineTo(v11_9[0], v11_9[1]);
                this.path.close();
                p9.drawPath(this.path, this.paint);
            }
        }
        return;
    }

    public void getBounds(android.graphics.RectF p3, android.graphics.Matrix p4, boolean p5)
    {
        super.getBounds(p3, p4, p5);
        this.rect.set(0, 0, ((float) this.layerModel.getSolidWidth()), ((float) this.layerModel.getSolidHeight()));
        this.boundsMatrix.mapRect(this.rect);
        p3.set(this.rect);
        return;
    }
}
